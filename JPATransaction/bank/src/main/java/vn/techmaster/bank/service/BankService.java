package vn.techmaster.bank.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.bank.controller.response.TransferResult;
import vn.techmaster.bank.exception.BankErrorCode;
import vn.techmaster.bank.exception.BankException;
import vn.techmaster.bank.model.Account;
import vn.techmaster.bank.model.AccountState;

import vn.techmaster.bank.model.TransactLog;
import vn.techmaster.bank.repository.AccountRepo;

import vn.techmaster.bank.repository.TransactLogRepo;

@Service
public class BankService {
  @Autowired
  private AccountRepo accountRepo;

  @Autowired
  private TransactLogRepo transactLogRepo;

  @Autowired
  private LoggingService loggingService;

  public void generateSampleData() {
    Account johnAccount = new Account("John", 1000L);
    Account bobAccount = new Account("Bob", 2000L);
    Account aliceAccount = new Account("Alice", 1500L);

    Account tomAccount = new Account("Tom", 100L);
    tomAccount.setState(AccountState.DISABLED);

    accountRepo.save(johnAccount);
    accountRepo.save(bobAccount);
    accountRepo.save(aliceAccount);
    accountRepo.save(tomAccount);
    accountRepo.flush();
  }

  @Transactional(rollbackOn = { BankException.class })
  public TransferResult transfer(long fromAccID, long toAccID, long amount) {
    Optional<Account> o_fromAccount = accountRepo.findById(fromAccID);
    Optional<Account> o_toAccount = accountRepo.findById(toAccID);
    Account fromAccount;
    Account toAccount;
    

    if (o_fromAccount.isPresent()) {
      fromAccount = o_fromAccount.get();
    } else {
      String detail = "From Account id " + fromAccID + " does not exist";
      loggingService.saveLog(fromAccID, toAccID, amount, BankErrorCode.ID_NOT_FOUND, detail);
      throw new BankException(BankErrorCode.ID_NOT_FOUND, "Invalid bank account",
        detail);
    }

    if (o_toAccount.isPresent()) {
      toAccount = o_toAccount.get();
    } else {
      String detail = "To Account id " + toAccID + " does not exist";
      loggingService.saveLog(fromAccID, toAccID, amount, BankErrorCode.ID_NOT_FOUND, detail);
      throw new BankException(BankErrorCode.ID_NOT_FOUND, "Invalid bank account", detail);
    }

    if (fromAccount.getBalance() < amount) {
      String detail = "Account " + fromAccount.getId() + " of " + fromAccount.getOwner() + " does not have enough balance";
      loggingService.saveLog(fromAccID, toAccID, amount, BankErrorCode.BALANCE_NOT_ENOUGH, detail);
      throw new BankException(BankErrorCode.BALANCE_NOT_ENOUGH, "Not enough balance", detail);
    }

    if (toAccount.getState() == AccountState.DISABLED) {
      String detail = "Account " + toAccount.getId() + " of " + toAccount.getOwner() + " is disabled";
      loggingService.saveLog(fromAccID, toAccID, amount, BankErrorCode.ACCOUNT_DISABLED, detail);
      throw new BankException(BankErrorCode.ACCOUNT_DISABLED, "Account is disabled", detail);      
    }
    

    fromAccount.setBalance(fromAccount.getBalance() - amount);
    toAccount.setBalance(toAccount.getBalance() + amount);
    Date transferDate = new Date();
    TransactLog transactLog = new TransactLog(fromAccount, toAccount, amount, transferDate);

    accountRepo.save(fromAccount);
    accountRepo.save(toAccount);
    transactLogRepo.save(transactLog);
    
    loggingService.saveLog(fromAccID, toAccID, amount, BankErrorCode.SUCCESS, "Success");

    return new TransferResult(BankErrorCode.SUCCESS, "Transfer success", transferDate);
  }  
}
