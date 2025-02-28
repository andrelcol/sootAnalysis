package com.roadtrack.onstar;

public enum Enums$statusSwitchNavigation {
  Failure,
  NoActiveRoute,
  Success(1);
  
  private static final Enums$statusSwitchNavigation[] $VALUES;
  
  private int opcode;
  
  static {
    Failure = new Enums$statusSwitchNavigation("Failure", 1, 2);
    NoActiveRoute = new Enums$statusSwitchNavigation("NoActiveRoute", 2, 3);
    $VALUES = new Enums$statusSwitchNavigation[] { Success, Failure, NoActiveRoute };
  }
  
  Enums$statusSwitchNavigation(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$statusSwitchNavigation GetValue(int paramInt) {
    Enums$statusSwitchNavigation[] arrayOfEnums$statusSwitchNavigation = values();
    for (byte b = 0; b < arrayOfEnums$statusSwitchNavigation.length; b++) {
      if (arrayOfEnums$statusSwitchNavigation[b].GetOpCode() == paramInt)
        return arrayOfEnums$statusSwitchNavigation[b]; 
    } 
    return Failure;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$statusSwitchNavigation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */