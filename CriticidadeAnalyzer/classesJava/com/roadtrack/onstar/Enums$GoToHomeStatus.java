package com.roadtrack.onstar;

public enum Enums$GoToHomeStatus {
  ACK(1),
  Execute(1),
  NACK(2),
  UndefinedAddress(2),
  WaitingGPS(2);
  
  private static final Enums$GoToHomeStatus[] $VALUES;
  
  private int opcode;
  
  static {
    Execute = new Enums$GoToHomeStatus("Execute", 2, 3);
    UndefinedAddress = new Enums$GoToHomeStatus("UndefinedAddress", 3, 4);
    WaitingGPS = new Enums$GoToHomeStatus("WaitingGPS", 4, 5);
    $VALUES = new Enums$GoToHomeStatus[] { ACK, NACK, Execute, UndefinedAddress, WaitingGPS };
  }
  
  Enums$GoToHomeStatus(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$GoToHomeStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */