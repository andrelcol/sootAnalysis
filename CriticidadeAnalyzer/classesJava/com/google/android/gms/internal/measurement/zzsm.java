package com.google.android.gms.internal.measurement;

final class zzsm extends zzsi<Double> {
  zzsm(zzso paramzzso, String paramString, Double paramDouble) {
    super(paramzzso, paramString, paramDouble, null);
  }
  
  private final Double zzv(Object paramObject) {
    if (paramObject instanceof Double)
      return (Double)paramObject; 
    if (paramObject instanceof Float)
      return Double.valueOf(((Float)paramObject).doubleValue()); 
    if (paramObject instanceof String)
      try {
        double d = Double.parseDouble((String)paramObject);
        return Double.valueOf(d);
      } catch (NumberFormatException numberFormatException) {} 
    String str = zztr();
    paramObject = String.valueOf(paramObject);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(paramObject).length());
    stringBuilder.append("Invalid double value for ");
    stringBuilder.append(str);
    stringBuilder.append(": ");
    stringBuilder.append((String)paramObject);
    stringBuilder.toString();
    return null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzsm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */