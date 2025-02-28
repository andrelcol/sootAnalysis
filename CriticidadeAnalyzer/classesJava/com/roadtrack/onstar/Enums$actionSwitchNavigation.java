package com.roadtrack.onstar;

public enum Enums$actionSwitchNavigation {
  CancelRoute(0),
  RequestRoute(1);
  
  private static final Enums$actionSwitchNavigation[] $VALUES;
  
  private int opcode;
  
  static {
    $VALUES = new Enums$actionSwitchNavigation[] { CancelRoute, RequestRoute };
  }
  
  Enums$actionSwitchNavigation(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$actionSwitchNavigation GetValue(int paramInt) {
    Enums$actionSwitchNavigation[] arrayOfEnums$actionSwitchNavigation = values();
    for (byte b = 0; b < arrayOfEnums$actionSwitchNavigation.length; b++) {
      if (arrayOfEnums$actionSwitchNavigation[b].GetOpCode() == paramInt)
        return arrayOfEnums$actionSwitchNavigation[b]; 
    } 
    return CancelRoute;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$actionSwitchNavigation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */