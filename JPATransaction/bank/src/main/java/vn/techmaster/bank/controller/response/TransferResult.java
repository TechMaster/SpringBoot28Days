package vn.techmaster.bank.controller.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import vn.techmaster.bank.exception.BankErrorCode;

@Data
@AllArgsConstructor
public class TransferResult {
  private BankErrorCode resultCode; //200 success, 404 account not found, 405 balance not enought
  private String message;
  private Date transferDate;
}
