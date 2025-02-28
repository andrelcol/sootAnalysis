package com.roadtrack.onstar;

public enum Enums$DirectionCall {
  Inbound,
  NoneDummy,
  Outbound(0);
  
  private static final Enums$DirectionCall[] $VALUES;
  
  private int opcode;
  
  static {
    Inbound = new Enums$DirectionCall("Inbound", 1, 1);
    NoneDummy = new Enums$DirectionCall("NoneDummy", 2, 255);
    $VALUES = new Enums$DirectionCall[] { Outbound, Inbound, NoneDummy };
  }
  
  Enums$DirectionCall(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$DirectionCall GetValue(int paramInt) {
    Enums$DirectionCall[] arrayOfEnums$DirectionCall = values();
    for (byte b = 0; b < arrayOfEnums$DirectionCall.length; b++) {
      if (arrayOfEnums$DirectionCall[b].GetOpCode() == paramInt)
        return arrayOfEnums$DirectionCall[b]; 
    } 
    return NoneDummy;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$DirectionCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */