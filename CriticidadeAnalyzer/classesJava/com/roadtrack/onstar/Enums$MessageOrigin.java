package com.roadtrack.onstar;

public enum Enums$MessageOrigin {
  A,
  B,
  C,
  D,
  GenericMsg,
  None(0),
  PLT_Alert(0),
  PLT_Application(0),
  RT(1),
  SMS(1),
  TBD(1),
  Ubiko(2),
  WebFleet(3);
  
  private static final Enums$MessageOrigin[] $VALUES;
  
  private int opcode;
  
  static {
    SMS = new Enums$MessageOrigin("SMS", 4, 4);
    TBD = new Enums$MessageOrigin("TBD", 5, 5);
    GenericMsg = new Enums$MessageOrigin("GenericMsg", 6, 6);
    PLT_Alert = new Enums$MessageOrigin("PLT_Alert", 7, 7);
    PLT_Application = new Enums$MessageOrigin("PLT_Application", 8, 8);
    A = new Enums$MessageOrigin("A", 9, 1001);
    B = new Enums$MessageOrigin("B", 10, 1002);
    C = new Enums$MessageOrigin("C", 11, 1003);
    D = new Enums$MessageOrigin("D", 12, 1004);
    $VALUES = new Enums$MessageOrigin[] { 
        None, RT, Ubiko, WebFleet, SMS, TBD, GenericMsg, PLT_Alert, PLT_Application, A, 
        B, C, D };
  }
  
  Enums$MessageOrigin(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$MessageOrigin GetValue(int paramInt) {
    Enums$MessageOrigin[] arrayOfEnums$MessageOrigin = values();
    for (byte b = 0; b < arrayOfEnums$MessageOrigin.length; b++) {
      if (arrayOfEnums$MessageOrigin[b].GetOpCode() == paramInt)
        return arrayOfEnums$MessageOrigin[b]; 
    } 
    return None;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$MessageOrigin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */