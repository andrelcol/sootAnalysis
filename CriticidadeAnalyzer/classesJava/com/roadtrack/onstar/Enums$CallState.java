package com.roadtrack.onstar;

public enum Enums$CallState {
  Active(0),
  Alerting_Ringing(0),
  Dialing(0),
  Ended(0),
  Hold(1),
  Incoming(1),
  Waiting(1);
  
  private static final Enums$CallState[] $VALUES;
  
  private int opcode;
  
  static {
    Dialing = new Enums$CallState("Dialing", 2, 2);
    Alerting_Ringing = new Enums$CallState("Alerting_Ringing", 3, 3);
    Incoming = new Enums$CallState("Incoming", 4, 4);
    Waiting = new Enums$CallState("Waiting", 5, 5);
    Ended = new Enums$CallState("Ended", 6, 6);
    $VALUES = new Enums$CallState[] { Active, Hold, Dialing, Alerting_Ringing, Incoming, Waiting, Ended };
  }
  
  Enums$CallState(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$CallState GetValue(int paramInt) {
    Enums$CallState[] arrayOfEnums$CallState = values();
    for (byte b = 0; b < arrayOfEnums$CallState.length; b++) {
      if (arrayOfEnums$CallState[b].GetOpCode() == paramInt)
        return arrayOfEnums$CallState[b]; 
    } 
    return Active;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$CallState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */