package com.roadtrack.onstar;

public enum Enums$AudioChannelCall {
  Off(0),
  On(1);
  
  private static final Enums$AudioChannelCall[] $VALUES;
  
  private int opcode;
  
  static {
    $VALUES = new Enums$AudioChannelCall[] { Off, On };
  }
  
  Enums$AudioChannelCall(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$AudioChannelCall GetValue(int paramInt) {
    Enums$AudioChannelCall[] arrayOfEnums$AudioChannelCall = values();
    for (byte b = 0; b < arrayOfEnums$AudioChannelCall.length; b++) {
      if (arrayOfEnums$AudioChannelCall[b].GetOpCode() == paramInt)
        return arrayOfEnums$AudioChannelCall[b]; 
    } 
    return On;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$AudioChannelCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */