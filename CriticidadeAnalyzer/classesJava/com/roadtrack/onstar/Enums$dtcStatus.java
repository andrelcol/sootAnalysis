package com.roadtrack.onstar;

public enum Enums$dtcStatus {
  DtcError,
  DtcNotValid(0),
  DtcOK(1),
  DtcWarning(2);
  
  private static final Enums$dtcStatus[] $VALUES;
  
  private final int code;
  
  static {
    DtcError = new Enums$dtcStatus("DtcError", 3, 3);
    $VALUES = new Enums$dtcStatus[] { DtcNotValid, DtcOK, DtcWarning, DtcError };
  }
  
  Enums$dtcStatus(int paramInt1) {
    this.code = paramInt1;
  }
  
  public static Enums$dtcStatus getValue(int paramInt) {
    Enums$dtcStatus[] arrayOfEnums$dtcStatus = values();
    for (byte b = 0; b < arrayOfEnums$dtcStatus.length; b++) {
      if (arrayOfEnums$dtcStatus[b].value() == paramInt)
        return arrayOfEnums$dtcStatus[b]; 
    } 
    return DtcError;
  }
  
  public String toString() {
    return String.valueOf(this.code);
  }
  
  public int value() {
    return this.code;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$dtcStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */