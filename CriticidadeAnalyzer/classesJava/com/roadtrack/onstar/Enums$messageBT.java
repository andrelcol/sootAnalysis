package com.roadtrack.onstar;

public enum Enums$messageBT {
  MESSAGE_BLUETOOTH_FAILED,
  MESSAGE_DEVICE_NAME,
  MESSAGE_READ,
  MESSAGE_STATE_CHANGE,
  MESSAGE_TOAST,
  MESSAGE_WRITE,
  NONE(0);
  
  private static final Enums$messageBT[] $VALUES;
  
  private int opcode;
  
  static {
    MESSAGE_STATE_CHANGE = new Enums$messageBT("MESSAGE_STATE_CHANGE", 1, 1);
    MESSAGE_READ = new Enums$messageBT("MESSAGE_READ", 2, 2);
    MESSAGE_WRITE = new Enums$messageBT("MESSAGE_WRITE", 3, 3);
    MESSAGE_DEVICE_NAME = new Enums$messageBT("MESSAGE_DEVICE_NAME", 4, 4);
    MESSAGE_TOAST = new Enums$messageBT("MESSAGE_TOAST", 5, 5);
    MESSAGE_BLUETOOTH_FAILED = new Enums$messageBT("MESSAGE_BLUETOOTH_FAILED", 6, 6);
    $VALUES = new Enums$messageBT[] { NONE, MESSAGE_STATE_CHANGE, MESSAGE_READ, MESSAGE_WRITE, MESSAGE_DEVICE_NAME, MESSAGE_TOAST, MESSAGE_BLUETOOTH_FAILED };
  }
  
  Enums$messageBT(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$messageBT GetValue(int paramInt) {
    Enums$messageBT[] arrayOfEnums$messageBT = values();
    for (byte b = 0; b < arrayOfEnums$messageBT.length; b++) {
      if (arrayOfEnums$messageBT[b].GetOpCode() == paramInt)
        return arrayOfEnums$messageBT[b]; 
    } 
    return NONE;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$messageBT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */