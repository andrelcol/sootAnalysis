package com.roadtrack.onstar;

public enum Enums$DeviceCall {
  BT,
  GSM(0);
  
  private static final Enums$DeviceCall[] $VALUES;
  
  private int opcode;
  
  static {
    BT = new Enums$DeviceCall("BT", 1, 1);
    $VALUES = new Enums$DeviceCall[] { GSM, BT };
  }
  
  Enums$DeviceCall(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$DeviceCall GetValue(int paramInt) {
    Enums$DeviceCall[] arrayOfEnums$DeviceCall = values();
    for (byte b = 0; b < arrayOfEnums$DeviceCall.length; b++) {
      if (arrayOfEnums$DeviceCall[b].GetOpCode() == paramInt)
        return arrayOfEnums$DeviceCall[b]; 
    } 
    return GSM;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$DeviceCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */