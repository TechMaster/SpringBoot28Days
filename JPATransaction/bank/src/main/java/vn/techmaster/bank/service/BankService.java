package vn.techmaster.bank.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.bank.controller.response.TransferResult;
import vn.techmaster.bank.exception.AccountErrorCode;
import vn.techmaster.bank.exception.AccountException;
import vn.techmaster.bank.exception.BankErrorCode;
import vn.techmaster.bank.exception.BankException;
import vn.techmaster.bank.exception.DummyException;
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


  @Transactional(rollbackOn = { Exception.class })
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

  public Account getAccountById(long accountID) throws AccountException{
    Optional<Account> oAccount = accountRepo.findById(accountID);
    
    if (oAccount.isPresent()) {
      Account account = oAccount.get();
      if (account.getState() == AccountState.DISABLED) {        
        throw new AccountException(BankErrorCode.ACCOUNT_DISABLED, 
        "Account " + account.getId() + " of " + account.getOwner() + " is disabled");    
      } else {
        return account;
      }
    } else {
      throw new AccountException(BankErrorCode.ID_NOT_FOUND, 
      "Account id " + accountID + " does not exist");
    }
  }

  @Transactional(rollbackOn = { BankException.class })
  public TransferResult transfer(long fromAccID, long toAccID, long amount) {
    Account fromAccount;
    Account toAccount;
    
    try {
      fromAccount = getAccountById(fromAccID);
    } catch (AccountException accountException){
      loggingService.saveLog(fromAccID, toAccID, amount, accountException.getErrorCode(), accountException.getMessage());
      throw new BankException(accountException.getErrorCode(), "From Account Error", accountException.getMessage());
    }

    try {
      toAccount = getAccountById(toAccID);
    } catch (AccountException accountException){
      loggingService.saveLog(fromAccID, toAccID, amount, accountException.getErrorCode(), accountException.getMessage());
      throw new BankException(accountException.getErrorCode(), "To Account Error", accountException.getMessage());
    }
   

    if (fromAccount.getBalance() < amount) {
      String detail = "Account " + fromAccount.getId() + " of " + fromAccount.getOwner() + " does not have enough balance";
      loggingService.saveLog(fromAccID, toAccID, amount, BankErrorCode.BALANCE_NOT_ENOUGH, detail);
      throw new BankException(BankErrorCode.BALANCE_NOT_ENOUGH, "Not enough balance", detail);
    }
    
    fromAccount.setBalance(fromAccount.getBalance() - amount);
    /* Thử nghiệm chức năng dontRollbackOn 
    if (true) {
      throw new DummyException();
    } */
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
