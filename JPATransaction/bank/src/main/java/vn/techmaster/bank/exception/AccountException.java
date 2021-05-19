package vn.techmaster.bank.exception;

public class AccountException extends Exception{
  private BankErrorCode errorCode;
  public AccountException(BankErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public BankErrorCode getErrorCode() {
    return this.errorCode;
  }  
}
