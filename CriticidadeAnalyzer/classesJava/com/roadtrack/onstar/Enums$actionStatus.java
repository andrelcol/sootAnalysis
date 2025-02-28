package com.roadtrack.onstar;

public enum Enums$actionStatus {
  Failure,
  None(0),
  Success(1);
  
  private static final Enums$actionStatus[] $VALUES;
  
  private int code;
  
  static {
    Failure = new Enums$actionStatus("Failure", 2, 2);
    $VALUES = new Enums$actionStatus[] { None, Success, Failure };
  }
  
  Enums$actionStatus(int paramInt1) {
    this.code = paramInt1;
  }
  
  public int GetCode() {
    return this.code;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$actionStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */