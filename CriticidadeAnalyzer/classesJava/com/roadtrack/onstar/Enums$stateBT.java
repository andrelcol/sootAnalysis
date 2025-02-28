package com.roadtrack.onstar;

public enum Enums$stateBT {
  STATE_CONNECTED,
  STATE_CONNECTING,
  STATE_LISTEN,
  STATE_NONE(0);
  
  private static final Enums$stateBT[] $VALUES;
  
  private int opcode;
  
  static {
    STATE_LISTEN = new Enums$stateBT("STATE_LISTEN", 1, 1);
    STATE_CONNECTING = new Enums$stateBT("STATE_CONNECTING", 2, 2);
    STATE_CONNECTED = new Enums$stateBT("STATE_CONNECTED", 3, 3);
    $VALUES = new Enums$stateBT[] { STATE_NONE, STATE_LISTEN, STATE_CONNECTING, STATE_CONNECTED };
  }
  
  Enums$stateBT(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$stateBT GetValue(int paramInt) {
    Enums$stateBT[] arrayOfEnums$stateBT = values();
    for (byte b = 0; b < arrayOfEnums$stateBT.length; b++) {
      if (arrayOfEnums$stateBT[b].GetOpCode() == paramInt)
        return arrayOfEnums$stateBT[b]; 
    } 
    return STATE_NONE;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$stateBT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */