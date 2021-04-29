package vn.techmaster.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vn.techmaster.bank.service.BankService;


@Component
public class AppRunner implements CommandLineRunner {
  @Autowired
  private BankService bankService;

  @Override
  public void run(String... args) throws Exception {
    bankService.generateSampleData();
  }
}
