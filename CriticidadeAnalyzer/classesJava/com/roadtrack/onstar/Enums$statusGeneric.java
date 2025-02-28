package com.roadtrack.onstar;

public enum Enums$statusGeneric {
  Failure,
  Success(1);
  
  private static final Enums$statusGeneric[] $VALUES;
  
  private int opcode;
  
  static {
    Failure = new Enums$statusGeneric("Failure", 1, 2);
    $VALUES = new Enums$statusGeneric[] { Success, Failure };
  }
  
  Enums$statusGeneric(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static Enums$statusGeneric GetValue(int paramInt) {
    Enums$statusGeneric[] arrayOfEnums$statusGeneric = values();
    for (byte b = 0; b < arrayOfEnums$statusGeneric.length; b++) {
      if (arrayOfEnums$statusGeneric[b].GetOpCode() == paramInt)
        return arrayOfEnums$statusGeneric[b]; 
    } 
    return Failure;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$statusGeneric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */