package com.roadtrack.onstar;

public enum Enums$CallNumberType {
  Accident,
  Agendamiento,
  Assistance,
  CallFromAssistance,
  CallFromEmergency,
  Dialing,
  Emergency,
  General(0),
  Home(1),
  Mobile(1),
  Office(2),
  Other(2),
  Redial(2),
  Silence(2),
  VR(2),
  Ziltok(2);
  
  private static final Enums$CallNumberType[] $VALUES;
  
  private int opcode;
  
  static {
    Mobile = new Enums$CallNumberType("Mobile", 3, 3);
    Other = new Enums$CallNumberType("Other", 4, 4);
    Emergency = new Enums$CallNumberType("Emergency", 5, 5);
    Assistance = new Enums$CallNumberType("Assistance", 6, 6);
    Silence = new Enums$CallNumberType("Silence", 7, 7);
    Ziltok = new Enums$CallNumberType("Ziltok", 8, 8);
    Accident = new Enums$CallNumberType("Accident", 9, 9);
    VR = new Enums$CallNumberType("VR", 10, 10);
    Redial = new Enums$CallNumberType("Redial", 11, 11);
    Dialing = new Enums$CallNumberType("Dialing", 12, 12);
    CallFromEmergency = new Enums$CallNumberType("CallFromEmergency", 13, 13);
    CallFromAssistance = new Enums$CallNumberType("CallFromAssistance", 14, 14);
    Agendamiento = new Enums$CallNumberType("Agendamiento", 15, 15);
    $VALUES = new Enums$CallNumberType[] { 
        General, Home, Office, Mobile, Other, Emergency, Assistance, Silence, Ziltok, Accident, 
        VR, Redial, Dialing, CallFromEmergency, CallFromAssistance, Agendamiento };
  }
  
  Enums$CallNumberType(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$CallNumberType GetValue(int paramInt) {
    Enums$CallNumberType[] arrayOfEnums$CallNumberType = values();
    for (byte b = 0; b < arrayOfEnums$CallNumberType.length; b++) {
      if (arrayOfEnums$CallNumberType[b].GetOpCode() == paramInt)
        return arrayOfEnums$CallNumberType[b]; 
    } 
    return General;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$CallNumberType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */