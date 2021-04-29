package vn.techmaster.bank.exception;

public enum BankErrorCode {
  SUCCESS(200),
  UNKNOWN_ERROR(400),
  ACCOUNT_DISABLED(401),
  ID_NOT_FOUND(404),
  BALANCE_NOT_ENOUGH(405),
  DATABASE_ERROR(500);

  private int value;

  BankErrorCode(int value) { 
    this.value = value; 
  }

  public int getValue() { 
    return value; 
  }
}
