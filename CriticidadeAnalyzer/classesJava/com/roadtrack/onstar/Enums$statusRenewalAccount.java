package com.roadtrack.onstar;

public enum Enums$statusRenewalAccount {
  AlmostExpired,
  Expired,
  NoDate,
  Normal(0),
  NotValid(0);
  
  private static final Enums$statusRenewalAccount[] $VALUES;
  
  private final int code;
  
  static {
    Expired = new Enums$statusRenewalAccount("Expired", 1, 2);
    AlmostExpired = new Enums$statusRenewalAccount("AlmostExpired", 2, 1);
    NoDate = new Enums$statusRenewalAccount("NoDate", 3, -1);
    NotValid = new Enums$statusRenewalAccount("NotValid", 4, 3);
    $VALUES = new Enums$statusRenewalAccount[] { Normal, Expired, AlmostExpired, NoDate, NotValid };
  }
  
  Enums$statusRenewalAccount(int paramInt1) {
    this.code = paramInt1;
  }
  
  public String toString() {
    return String.valueOf(this.code);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$statusRenewalAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */