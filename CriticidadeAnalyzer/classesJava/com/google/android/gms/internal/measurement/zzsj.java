package com.google.android.gms.internal.measurement;

final class zzsj extends zzsi<Long> {
  zzsj(zzso paramzzso, String paramString, Long paramLong) {
    super(paramzzso, paramString, paramLong, null);
  }
  
  private final Long zzt(Object paramObject) {
    if (paramObject instanceof Long)
      return (Long)paramObject; 
    if (paramObject instanceof String)
      try {
        long l = Long.parseLong((String)paramObject);
        return Long.valueOf(l);
      } catch (NumberFormatException numberFormatException) {} 
    String str = zztr();
    paramObject = String.valueOf(paramObject);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 25 + String.valueOf(paramObject).length());
    stringBuilder.append("Invalid long value for ");
    stringBuilder.append(str);
    stringBuilder.append(": ");
    stringBuilder.append((String)paramObject);
    stringBuilder.toString();
    return null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzsj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */