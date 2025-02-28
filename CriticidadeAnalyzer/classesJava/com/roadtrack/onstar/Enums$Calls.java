package com.roadtrack.onstar;

public enum Enums$Calls {
  AgendamientoCallAndMessage,
  AssistanceCallAndMessage,
  EmergencyCallAndMessage,
  EmergencyMessage,
  IVR,
  None(0),
  Redial(0),
  ServicePlanRenewal(0),
  UserDefined(0);
  
  private static final Enums$Calls[] $VALUES;
  
  private int opcode;
  
  static {
    EmergencyMessage = new Enums$Calls("EmergencyMessage", 1, 1);
    EmergencyCallAndMessage = new Enums$Calls("EmergencyCallAndMessage", 2, 2);
    AssistanceCallAndMessage = new Enums$Calls("AssistanceCallAndMessage", 3, 3);
    Redial = new Enums$Calls("Redial", 4, 4);
    IVR = new Enums$Calls("IVR", 5, 5);
    UserDefined = new Enums$Calls("UserDefined", 6, 6);
    AgendamientoCallAndMessage = new Enums$Calls("AgendamientoCallAndMessage", 7, 7);
    ServicePlanRenewal = new Enums$Calls("ServicePlanRenewal", 8, 8);
    $VALUES = new Enums$Calls[] { None, EmergencyMessage, EmergencyCallAndMessage, AssistanceCallAndMessage, Redial, IVR, UserDefined, AgendamientoCallAndMessage, ServicePlanRenewal };
  }
  
  Enums$Calls(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$Calls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */