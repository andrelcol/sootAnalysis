package com.roadtrack.onstar;

public enum Enums$BTCallStatus {
  Active, Alerting, Dialing, Ended, Held, Incoming, Waiting;
  
  private static final Enums$BTCallStatus[] $VALUES;
  
  static {
    Dialing = new Enums$BTCallStatus("Dialing", 2);
    Alerting = new Enums$BTCallStatus("Alerting", 3);
    Incoming = new Enums$BTCallStatus("Incoming", 4);
    Waiting = new Enums$BTCallStatus("Waiting", 5);
    Ended = new Enums$BTCallStatus("Ended", 6);
    $VALUES = new Enums$BTCallStatus[] { Active, Held, Dialing, Alerting, Incoming, Waiting, Ended };
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$BTCallStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */