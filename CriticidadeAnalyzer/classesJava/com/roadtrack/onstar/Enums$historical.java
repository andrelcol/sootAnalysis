package com.roadtrack.onstar;

public enum Enums$historical {
  alerts,
  commands,
  complete(0),
  dealer(0),
  diagnosis(0),
  navigation(0);
  
  private static final Enums$historical[] $VALUES;
  
  private int opcode;
  
  static {
    commands = new Enums$historical("commands", 1, 1);
    diagnosis = new Enums$historical("diagnosis", 2, 2);
    alerts = new Enums$historical("alerts", 3, 3);
    navigation = new Enums$historical("navigation", 4, 4);
    dealer = new Enums$historical("dealer", 5, 5);
    $VALUES = new Enums$historical[] { complete, commands, diagnosis, alerts, navigation, dealer };
  }
  
  Enums$historical(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$historical.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */