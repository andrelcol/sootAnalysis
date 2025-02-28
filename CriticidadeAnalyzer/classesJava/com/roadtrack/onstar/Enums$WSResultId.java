package com.roadtrack.onstar;

public enum Enums$WSResultId {
  CertificateFail,
  NetworkFail(0),
  NoResult(0),
  OK(1),
  ServiceErrorCode(2),
  Timeout(2);
  
  private static final Enums$WSResultId[] $VALUES;
  
  private final int code;
  
  static {
    NoResult = new Enums$WSResultId("NoResult", 3, 2);
    Timeout = new Enums$WSResultId("Timeout", 4, 408);
    CertificateFail = new Enums$WSResultId("CertificateFail", 5, 500);
    $VALUES = new Enums$WSResultId[] { NetworkFail, OK, ServiceErrorCode, NoResult, Timeout, CertificateFail };
  }
  
  Enums$WSResultId(int paramInt1) {
    this.code = paramInt1;
  }
  
  public String toString() {
    return String.valueOf(this.code);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$WSResultId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */