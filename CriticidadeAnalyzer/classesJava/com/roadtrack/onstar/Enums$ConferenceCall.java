package com.roadtrack.onstar;

public enum Enums$ConferenceCall {
  Off(0),
  On(1);
  
  private static final Enums$ConferenceCall[] $VALUES;
  
  private int opcode;
  
  static {
    $VALUES = new Enums$ConferenceCall[] { Off, On };
  }
  
  Enums$ConferenceCall(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$ConferenceCall GetValue(int paramInt) {
    Enums$ConferenceCall[] arrayOfEnums$ConferenceCall = values();
    for (byte b = 0; b < arrayOfEnums$ConferenceCall.length; b++) {
      if (arrayOfEnums$ConferenceCall[b].GetOpCode() == paramInt)
        return arrayOfEnums$ConferenceCall[b]; 
    } 
    return Off;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$ConferenceCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */