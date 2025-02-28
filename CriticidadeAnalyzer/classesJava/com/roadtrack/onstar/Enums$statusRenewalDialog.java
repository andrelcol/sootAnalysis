package com.roadtrack.onstar;

public enum Enums$statusRenewalDialog {
  NeverShowAgain,
  NotValid,
  ShowAlways(0);
  
  private static final Enums$statusRenewalDialog[] $VALUES;
  
  private final int code;
  
  static {
    NeverShowAgain = new Enums$statusRenewalDialog("NeverShowAgain", 1, 1);
    NotValid = new Enums$statusRenewalDialog("NotValid", 2, 3);
    $VALUES = new Enums$statusRenewalDialog[] { ShowAlways, NeverShowAgain, NotValid };
  }
  
  Enums$statusRenewalDialog(int paramInt1) {
    this.code = paramInt1;
  }
  
  public String toString() {
    return String.valueOf(this.code);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$statusRenewalDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */