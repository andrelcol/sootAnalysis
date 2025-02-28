package com.roadtrack.onstar;

public enum Enums$playingStatus {
  NoSupportInformation,
  Paused,
  Playing(1),
  Stopped(1),
  StoppedNoSupport(1);
  
  private static final Enums$playingStatus[] $VALUES;
  
  private int opcode;
  
  static {
    Paused = new Enums$playingStatus("Paused", 1, 2);
    Stopped = new Enums$playingStatus("Stopped", 2, 3);
    NoSupportInformation = new Enums$playingStatus("NoSupportInformation", 3, 4);
    StoppedNoSupport = new Enums$playingStatus("StoppedNoSupport", 4, 5);
    $VALUES = new Enums$playingStatus[] { Playing, Paused, Stopped, NoSupportInformation, StoppedNoSupport };
  }
  
  Enums$playingStatus(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$playingStatus GetValue(int paramInt) {
    Enums$playingStatus[] arrayOfEnums$playingStatus = values();
    for (byte b = 0; b < arrayOfEnums$playingStatus.length; b++) {
      if (arrayOfEnums$playingStatus[b].GetOpCode() == paramInt)
        return arrayOfEnums$playingStatus[b]; 
    } 
    return NoSupportInformation;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$playingStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */