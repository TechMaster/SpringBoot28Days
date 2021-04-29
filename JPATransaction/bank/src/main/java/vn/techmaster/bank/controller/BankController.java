package vn.techmaster.bank.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.bank.controller.request.TransferRequest;
import vn.techmaster.bank.controller.response.TransferResult;
import vn.techmaster.bank.exception.BankException;
import vn.techmaster.bank.service.BankService;

@RestController
public class BankController {

  @Autowired
  private BankService bankService;

  @PostMapping("/transfer")
  public ResponseEntity<TransferResult> transfer(@ModelAttribute TransferRequest transferRequest) {

    try {
      TransferResult result = bankService.transfer(
      transferRequest.getFrom(), 
      transferRequest.getTo(), 
      transferRequest.getAmount());

      return ResponseEntity.ok().body(result);
    } catch (BankException e) {
      TransferResult transerError = new TransferResult(
        e.getErrorCode(), 
        e.getMessage() + " : " + e.getDetail(),
        new Date());
      return ResponseEntity.badRequest().body(transerError);
    }   
  }
}
