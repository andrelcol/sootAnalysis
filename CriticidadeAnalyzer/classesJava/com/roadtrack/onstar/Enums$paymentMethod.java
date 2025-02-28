package com.roadtrack.onstar;

public enum Enums$paymentMethod {
  BankTicket,
  CreditCard(0),
  NotValid(0);
  
  private static final Enums$paymentMethod[] $VALUES;
  
  private final int code;
  
  static {
    BankTicket = new Enums$paymentMethod("BankTicket", 1, 1);
    NotValid = new Enums$paymentMethod("NotValid", 2, 2);
    $VALUES = new Enums$paymentMethod[] { CreditCard, BankTicket, NotValid };
  }
  
  Enums$paymentMethod(int paramInt1) {
    this.code = paramInt1;
  }
  
  public String toString() {
    return String.valueOf(this.code);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$paymentMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */