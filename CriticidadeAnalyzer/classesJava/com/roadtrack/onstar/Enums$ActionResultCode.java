package com.roadtrack.onstar;

import java.util.HashMap;
import java.util.Map;

public enum Enums$ActionResultCode {
  Activated,
  Fail,
  NoResult(0),
  OnMovement(0),
  Unknown_2(2);
  
  private static final Enums$ActionResultCode[] $VALUES;
  
  private static final Map<Integer, Enums$ActionResultCode> typesByValue;
  
  private final int code;
  
  static {
    Fail = new Enums$ActionResultCode("Fail", 2, 3);
    Activated = new Enums$ActionResultCode("Activated", 3, 15);
    OnMovement = new Enums$ActionResultCode("OnMovement", 4, 16);
    $VALUES = new Enums$ActionResultCode[] { NoResult, Unknown_2, Fail, Activated, OnMovement };
    typesByValue = new HashMap<Integer, Enums$ActionResultCode>();
    Enums$ActionResultCode[] arrayOfEnums$ActionResultCode = values();
    int i = arrayOfEnums$ActionResultCode.length;
    while (b < i) {
      Enums$ActionResultCode enums$ActionResultCode = arrayOfEnums$ActionResultCode[b];
      typesByValue.put(Integer.valueOf(enums$ActionResultCode.value()), enums$ActionResultCode);
      b++;
    } 
  }
  
  Enums$ActionResultCode(int paramInt1) {
    this.code = paramInt1;
  }
  
  public static Enums$ActionResultCode forValue(int paramInt) {
    return typesByValue.get(Integer.valueOf(paramInt));
  }
  
  public String toString() {
    return String.valueOf(this.code);
  }
  
  public int value() {
    return this.code;
  }
  
  static {
    byte b = 0;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$ActionResultCode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */