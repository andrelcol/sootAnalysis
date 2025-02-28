package com.roadtrack.onstar.utils;

import java.text.DecimalFormat;

public class RubenUltimaAlgorithm {
  public final String formatLinearDistance(double paramDouble) {
    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    paramDouble *= 1000.0D;
    if (paramDouble >= 1000.0D) {
      paramDouble = Math.round(paramDouble / 100.0D) / 10.0D;
    } else {
      double d = Math.round(paramDouble / 10.0D) * 10.0D;
      paramDouble = d;
      if (d == 10000.0D)
        paramDouble = d / 1000.0D; 
    } 
    return decimalFormat.format(paramDouble);
  }
  
  public final String formatLinearDistanceWithString(double paramDouble) {
    paramDouble *= 1000.0D;
    String str = "km";
    if (paramDouble < 1000.0D && paramDouble != 10000.0D)
      str = "m"; 
    return str;
  }
  
  public final double linearDistance(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    paramDouble1 = Math.pow(Math.sin((paramDouble3 - paramDouble1) * 0.0174532925199433D / 2.0D), 2.0D) + Math.cos(paramDouble1 * 0.0174532925199433D) * Math.cos(paramDouble3 * 0.0174532925199433D) * Math.pow(Math.sin((paramDouble4 - paramDouble2) * 0.0174532925199433D / 2.0D), 2.0D);
    return Math.atan2(Math.sqrt(paramDouble1), Math.sqrt(1.0D - paramDouble1)) * 2.0D;
  }
  
  public final double linearDistanceToKilometers(double paramDouble) {
    return paramDouble * 6367.45D;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/RubenUltimaAlgorithm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */