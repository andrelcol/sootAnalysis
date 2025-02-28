package com.roadtrack.onstar;

public enum Enums$GpsStatusCode {
  NoStatus(0),
  Ok(2);
  
  private static final Enums$GpsStatusCode[] $VALUES;
  
  private final int code;
  
  static {
    $VALUES = new Enums$GpsStatusCode[] { NoStatus, Ok };
  }
  
  Enums$GpsStatusCode(int paramInt1) {
    this.code = paramInt1;
  }
  
  public String toString() {
    return String.valueOf(this.code);
  }
  
  public int value() {
    return this.code;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$GpsStatusCode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */