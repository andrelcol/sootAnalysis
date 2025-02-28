package com.google.firebase.iid;

import android.text.TextUtils;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

final class zzax {
  private static final long zzdf = TimeUnit.DAYS.toMillis(7L);
  
  private final long timestamp;
  
  final String zzbq;
  
  private final String zzdg;
  
  private zzax(String paramString1, String paramString2, long paramLong) {
    this.zzbq = paramString1;
    this.zzdg = paramString2;
    this.timestamp = paramLong;
  }
  
  static String zza(zzax paramzzax) {
    return (paramzzax == null) ? null : paramzzax.zzbq;
  }
  
  static String zza(String paramString1, String paramString2, long paramLong) {
    try {
      JSONObject jSONObject = new JSONObject();
      this();
      jSONObject.put("token", paramString1);
      jSONObject.put("appVersion", paramString2);
      jSONObject.put("timestamp", paramLong);
      return jSONObject.toString();
    } catch (JSONException jSONException) {
      paramString2 = String.valueOf(jSONException);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString2).length() + 24);
      stringBuilder.append("Failed to encode token: ");
      stringBuilder.append(paramString2);
      stringBuilder.toString();
      return null;
    } 
  }
  
  static zzax zzi(String paramString) {
    String str;
    if (TextUtils.isEmpty(paramString))
      return null; 
    if (paramString.startsWith("{"))
      try {
        JSONObject jSONObject = new JSONObject();
        this(paramString);
        return new zzax(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong("timestamp"));
      } catch (JSONException jSONException) {
        str = String.valueOf(jSONException);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 23);
        stringBuilder.append("Failed to parse token: ");
        stringBuilder.append(str);
        stringBuilder.toString();
        return null;
      }  
    return new zzax(str, null, 0L);
  }
  
  final boolean zzj(String paramString) {
    return (System.currentTimeMillis() > this.timestamp + zzdf || !paramString.equals(this.zzdg));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */