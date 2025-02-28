package com.roadtrack.onstar;

public enum Enums$MessageType {
  AppointmentSchedule,
  HMITextMessage,
  Inbox,
  None(0),
  PopupAutoDism(0),
  PopupOk(0);
  
  private static final Enums$MessageType[] $VALUES;
  
  private int opcode;
  
  static {
    Inbox = new Enums$MessageType("Inbox", 1, 1);
    PopupOk = new Enums$MessageType("PopupOk", 2, 2);
    PopupAutoDism = new Enums$MessageType("PopupAutoDism", 3, 3);
    AppointmentSchedule = new Enums$MessageType("AppointmentSchedule", 4, 4);
    HMITextMessage = new Enums$MessageType("HMITextMessage", 5, 5);
    $VALUES = new Enums$MessageType[] { None, Inbox, PopupOk, PopupAutoDism, AppointmentSchedule, HMITextMessage };
  }
  
  Enums$MessageType(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$MessageType GetValue(int paramInt) {
    Enums$MessageType[] arrayOfEnums$MessageType = values();
    for (byte b = 0; b < arrayOfEnums$MessageType.length; b++) {
      if (arrayOfEnums$MessageType[b].GetOpCode() == paramInt)
        return arrayOfEnums$MessageType[b]; 
    } 
    return None;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$MessageType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */