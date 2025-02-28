package com.roadtrack.onstar.utils;

public class Converters {
  public static String mtsToKm(String paramString) {
    double d = Double.parseDouble(paramString) / 1000.0D;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Double.toString(d));
    stringBuilder.append(" KM");
    return stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/Converters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */