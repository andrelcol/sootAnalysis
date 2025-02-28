package com.google.android.gms.internal.measurement;

final class zzsk extends zzsi<Integer> {
  zzsk(zzso paramzzso, String paramString, Integer paramInteger) {
    super(paramzzso, paramString, paramInteger, null);
  }
  
  private final Integer zzu(Object paramObject) {
    if (paramObject instanceof Integer)
      return (Integer)paramObject; 
    if (paramObject instanceof Long)
      return Integer.valueOf(((Long)paramObject).intValue()); 
    if (paramObject instanceof String)
      try {
        int i = Integer.parseInt((String)paramObject);
        return Integer.valueOf(i);
      } catch (NumberFormatException numberFormatException) {} 
    String str = zztr();
    paramObject = String.valueOf(paramObject);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 24 + String.valueOf(paramObject).length());
    stringBuilder.append("Invalid int value for ");
    stringBuilder.append(str);
    stringBuilder.append(": ");
    stringBuilder.append((String)paramObject);
    stringBuilder.toString();
    return null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzsk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */