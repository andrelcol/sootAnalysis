package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfi;
import com.google.android.gms.internal.measurement.zzfj;
import com.google.android.gms.internal.measurement.zzfk;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfp;
import com.google.android.gms.internal.measurement.zzfq;
import com.google.android.gms.internal.measurement.zzxz;
import com.google.android.gms.internal.measurement.zzya;
import java.io.IOException;
import java.util.Map;

public final class zzbq extends zzfm implements zzs {
  private static int zzaol = 65535;
  
  private static int zzaom = 2;
  
  private final Map<String, Map<String, String>> zzaon = (Map<String, Map<String, String>>)new ArrayMap();
  
  private final Map<String, Map<String, Boolean>> zzaoo = (Map<String, Map<String, Boolean>>)new ArrayMap();
  
  private final Map<String, Map<String, Boolean>> zzaop = (Map<String, Map<String, Boolean>>)new ArrayMap();
  
  private final Map<String, zzfp> zzaoq = (Map<String, zzfp>)new ArrayMap();
  
  private final Map<String, Map<String, Integer>> zzaor = (Map<String, Map<String, Integer>>)new ArrayMap();
  
  private final Map<String, String> zzaos = (Map<String, String>)new ArrayMap();
  
  zzbq(zzfn paramzzfn) {
    super(paramzzfn);
  }
  
  private final zzfp zza(String paramString, byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return new zzfp(); 
    zzxz zzxz = zzxz.zzj(paramArrayOfbyte, 0, paramArrayOfbyte.length);
    zzfp zzfp = new zzfp();
    try {
      zzfp.zza(zzxz);
      zzgt().zzjo().zze("Parsed config. version, gmp_app_id", zzfp.zzawm, zzfp.zzafi);
      return zzfp;
    } catch (IOException iOException) {
      zzgt().zzjj().zze("Unable to merge remote config. appId", zzas.zzbw(paramString), iOException);
      return new zzfp();
    } 
  }
  
  private static Map<String, String> zza(zzfp paramzzfp) {
    ArrayMap<String, String> arrayMap = new ArrayMap();
    if (paramzzfp != null) {
      zzfq[] arrayOfZzfq = paramzzfp.zzawo;
      if (arrayOfZzfq != null) {
        int i = arrayOfZzfq.length;
        for (byte b = 0; b < i; b++) {
          zzfq zzfq = arrayOfZzfq[b];
          if (zzfq != null)
            arrayMap.put(zzfq.zzoj, zzfq.value); 
        } 
      } 
    } 
    return (Map<String, String>)arrayMap;
  }
  
  private final void zza(String paramString, zzfp paramzzfp) {
    ArrayMap<String, Boolean> arrayMap1 = new ArrayMap();
    ArrayMap<String, Boolean> arrayMap2 = new ArrayMap();
    ArrayMap<String, Integer> arrayMap = new ArrayMap();
    if (paramzzfp != null) {
      zzfo[] arrayOfZzfo = paramzzfp.zzawp;
      if (arrayOfZzfo != null) {
        int i = arrayOfZzfo.length;
        for (byte b = 0; b < i; b++) {
          zzfo zzfo = arrayOfZzfo[b];
          if (TextUtils.isEmpty(zzfo.name)) {
            zzgt().zzjj().zzby("EventConfig contained null event name");
          } else {
            String str = zzcu.zzco(zzfo.name);
            if (!TextUtils.isEmpty(str))
              zzfo.name = str; 
            arrayMap1.put(zzfo.name, zzfo.zzawj);
            arrayMap2.put(zzfo.name, zzfo.zzawk);
            Integer integer = zzfo.zzawl;
            if (integer != null)
              if (integer.intValue() < zzaom || zzfo.zzawl.intValue() > zzaol) {
                zzgt().zzjj().zze("Invalid sampling rate. Event name, sample rate", zzfo.name, zzfo.zzawl);
              } else {
                arrayMap.put(zzfo.name, zzfo.zzawl);
              }  
          } 
        } 
      } 
    } 
    this.zzaoo.put(paramString, arrayMap1);
    this.zzaop.put(paramString, arrayMap2);
    this.zzaor.put(paramString, arrayMap);
  }
  
  private final void zzcf(String paramString) {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    if (this.zzaoq.get(paramString) == null) {
      byte[] arrayOfByte = zzjt().zzbo(paramString);
      if (arrayOfByte == null) {
        this.zzaon.put(paramString, null);
        this.zzaoo.put(paramString, null);
        this.zzaop.put(paramString, null);
        this.zzaoq.put(paramString, null);
        this.zzaos.put(paramString, null);
        this.zzaor.put(paramString, null);
        return;
      } 
      zzfp zzfp = zza(paramString, arrayOfByte);
      this.zzaon.put(paramString, zza(zzfp));
      zza(paramString, zzfp);
      this.zzaoq.put(paramString, zzfp);
      this.zzaos.put(paramString, null);
    } 
  }
  
  protected final boolean zza(String paramString1, byte[] paramArrayOfbyte, String paramString2) {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString1);
    zzfp zzfp = zza(paramString1, paramArrayOfbyte);
    if (zzfp == null)
      return false; 
    zza(paramString1, zzfp);
    this.zzaoq.put(paramString1, zzfp);
    this.zzaos.put(paramString1, paramString2);
    this.zzaon.put(paramString1, zza(zzfp));
    zzm zzm = zzjs();
    zzfi[] arrayOfZzfi = zzfp.zzawq;
    Preconditions.checkNotNull(arrayOfZzfi);
    int i = arrayOfZzfi.length;
    for (byte b = 0; b < i; b++) {
      zzfi zzfi = arrayOfZzfi[b];
      for (zzfj zzfj : zzfi.zzavi) {
        String str = zzcu.zzco(zzfj.zzavn);
        if (str != null)
          zzfj.zzavn = str; 
        for (zzfk zzfk : zzfj.zzavo) {
          String str1 = zzcv.zzco(zzfk.zzavv);
          if (str1 != null)
            zzfk.zzavv = str1; 
        } 
      } 
      for (zzfm zzfm1 : zzfi.zzavh) {
        String str = zzcw.zzco(zzfm1.zzawc);
        if (str != null)
          zzfm1.zzawc = str; 
      } 
    } 
    zzm.zzjt().zza(paramString1, arrayOfZzfi);
    try {
      zzfp.zzawq = null;
      byte[] arrayOfByte = new byte[zzfp.zzvx()];
      zzfp.zza(zzya.zzk(arrayOfByte, 0, arrayOfByte.length));
      paramArrayOfbyte = arrayOfByte;
    } catch (IOException iOException) {
      zzgt().zzjj().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzas.zzbw(paramString1), iOException);
    } 
    zzt zzt = zzjt();
    Preconditions.checkNotEmpty(paramString1);
    zzt.zzaf();
    zzt.zzcl();
    ContentValues contentValues = new ContentValues();
    contentValues.put("remote_config", paramArrayOfbyte);
    try {
      if (zzt.getWritableDatabase().update("apps", contentValues, "app_id = ?", new String[] { paramString1 }) == 0L)
        zzt.zzgt().zzjg().zzg("Failed to update remote config (got 0). appId", zzas.zzbw(paramString1)); 
    } catch (SQLiteException sQLiteException) {
      zzt.zzgt().zzjg().zze("Error storing remote config. appId", zzas.zzbw(paramString1), sQLiteException);
    } 
    return true;
  }
  
  protected final zzfp zzcg(String paramString) {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    zzcf(paramString);
    return this.zzaoq.get(paramString);
  }
  
  protected final String zzch(String paramString) {
    zzaf();
    return this.zzaos.get(paramString);
  }
  
  protected final void zzci(String paramString) {
    zzaf();
    this.zzaos.put(paramString, null);
  }
  
  final void zzcj(String paramString) {
    zzaf();
    this.zzaoq.remove(paramString);
  }
  
  final long zzck(String paramString) {
    String str = zzf(paramString, "measurement.account.time_zone_offset_minutes");
    if (!TextUtils.isEmpty(str))
      try {
        return Long.parseLong(str);
      } catch (NumberFormatException numberFormatException) {
        zzgt().zzjj().zze("Unable to parse timezone offset. appId", zzas.zzbw(paramString), numberFormatException);
      }  
    return 0L;
  }
  
  final boolean zzcl(String paramString) {
    return "1".equals(zzf(paramString, "measurement.upload.blacklist_internal"));
  }
  
  final boolean zzcm(String paramString) {
    return "1".equals(zzf(paramString, "measurement.upload.blacklist_public"));
  }
  
  public final String zzf(String paramString1, String paramString2) {
    zzaf();
    zzcf(paramString1);
    Map map = this.zzaon.get(paramString1);
    return (map != null) ? (String)map.get(paramString2) : null;
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  final boolean zzo(String paramString1, String paramString2) {
    zzaf();
    zzcf(paramString1);
    if (zzcl(paramString1) && zzfx.zzcy(paramString2))
      return true; 
    if (zzcm(paramString1) && zzfx.zzct(paramString2))
      return true; 
    Map map = this.zzaoo.get(paramString1);
    if (map != null) {
      Boolean bool = (Boolean)map.get(paramString2);
      return (bool == null) ? false : bool.booleanValue();
    } 
    return false;
  }
  
  final boolean zzp(String paramString1, String paramString2) {
    zzaf();
    zzcf(paramString1);
    if ("ecommerce_purchase".equals(paramString2))
      return true; 
    Map map = this.zzaop.get(paramString1);
    if (map != null) {
      Boolean bool = (Boolean)map.get(paramString2);
      return (bool == null) ? false : bool.booleanValue();
    } 
    return false;
  }
  
  final int zzq(String paramString1, String paramString2) {
    zzaf();
    zzcf(paramString1);
    Map map = this.zzaor.get(paramString1);
    if (map != null) {
      Integer integer = (Integer)map.get(paramString2);
      return (integer == null) ? 1 : integer.intValue();
    } 
    return 1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */