package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

public final class zzab {
  final String name;
  
  private final String origin;
  
  final long timestamp;
  
  final long zzaht;
  
  final zzad zzahu;
  
  final String zztt;
  
  zzab(zzbw paramzzbw, String paramString1, String paramString2, String paramString3, long paramLong1, long paramLong2, Bundle paramBundle) {
    zzad zzad1;
    Preconditions.checkNotEmpty(paramString2);
    Preconditions.checkNotEmpty(paramString3);
    this.zztt = paramString2;
    this.name = paramString3;
    paramString3 = paramString1;
    if (TextUtils.isEmpty(paramString1))
      paramString3 = null; 
    this.origin = paramString3;
    this.timestamp = paramLong1;
    this.zzaht = paramLong2;
    paramLong1 = this.zzaht;
    if (paramLong1 != 0L && paramLong1 > this.timestamp)
      paramzzbw.zzgt().zzjj().zzg("Event created with reverse previous/current timestamps. appId", zzas.zzbw(paramString2)); 
    if (paramBundle != null && !paramBundle.isEmpty()) {
      Bundle bundle = new Bundle(paramBundle);
      Iterator<String> iterator = bundle.keySet().iterator();
      while (iterator.hasNext()) {
        String str = iterator.next();
        if (str == null) {
          paramzzbw.zzgt().zzjg().zzby("Param name can't be null");
          iterator.remove();
          continue;
        } 
        Object object = paramzzbw.zzgr().zzh(str, bundle.get(str));
        if (object == null) {
          paramzzbw.zzgt().zzjj().zzg("Param value can't be null", paramzzbw.zzgq().zzbu(str));
          iterator.remove();
          continue;
        } 
        paramzzbw.zzgr().zza(bundle, str, object);
      } 
      zzad1 = new zzad(bundle);
    } else {
      zzad1 = new zzad(new Bundle());
    } 
    this.zzahu = zzad1;
  }
  
  private zzab(zzbw paramzzbw, String paramString1, String paramString2, String paramString3, long paramLong1, long paramLong2, zzad paramzzad) {
    Preconditions.checkNotEmpty(paramString2);
    Preconditions.checkNotEmpty(paramString3);
    Preconditions.checkNotNull(paramzzad);
    this.zztt = paramString2;
    this.name = paramString3;
    String str = paramString1;
    if (TextUtils.isEmpty(paramString1))
      str = null; 
    this.origin = str;
    this.timestamp = paramLong1;
    this.zzaht = paramLong2;
    paramLong1 = this.zzaht;
    if (paramLong1 != 0L && paramLong1 > this.timestamp)
      paramzzbw.zzgt().zzjj().zze("Event created with reverse previous/current timestamps. appId, name", zzas.zzbw(paramString2), zzas.zzbw(paramString3)); 
    this.zzahu = paramzzad;
  }
  
  public final String toString() {
    String str3 = this.zztt;
    String str1 = this.name;
    String str2 = String.valueOf(this.zzahu);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str3).length() + 33 + String.valueOf(str1).length() + String.valueOf(str2).length());
    stringBuilder.append("Event{appId='");
    stringBuilder.append(str3);
    stringBuilder.append("', name='");
    stringBuilder.append(str1);
    stringBuilder.append("', params=");
    stringBuilder.append(str2);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  final zzab zza(zzbw paramzzbw, long paramLong) {
    return new zzab(paramzzbw, this.origin, this.zztt, this.name, this.timestamp, paramLong, this.zzahu);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */