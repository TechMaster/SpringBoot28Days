package vn.techmaster.restannotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.restannotation.request.TransferRequest;

@RestController
public class RESTController {
  @GetMapping("/book/{id}")
  public String getBookByPath(@PathVariable("id") int id) {
    return "Book id = " + id;
  }

  @GetMapping("/bookquery")
  public String getBookByQuery(
    @RequestParam(value = "id", required = true) int id) {
    return "Book id = " + id;
  }



  @PostMapping("/transferform")
  public String transferform(@ModelAttribute TransferRequest request) {
    return request.toString();
  }

  @PostMapping("/transferjson")
  public String transferjson(@RequestBody TransferRequest request) {
    return request.toString();
  }
}
