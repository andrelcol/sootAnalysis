package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import java.io.IOException;

final class zzay implements Runnable {
  private final zzan zzan;
  
  private final zzba zzaq;
  
  private final long zzdh;
  
  private final PowerManager.WakeLock zzdi;
  
  private final FirebaseInstanceId zzdj;
  
  zzay(FirebaseInstanceId paramFirebaseInstanceId, zzan paramzzan, zzba paramzzba, long paramLong) {
    this.zzdj = paramFirebaseInstanceId;
    this.zzan = paramzzan;
    this.zzaq = paramzzba;
    this.zzdh = paramLong;
    this.zzdi = ((PowerManager)getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
    this.zzdi.setReferenceCounted(false);
  }
  
  private final boolean zzam() {
    try {
      if (!this.zzdj.zzo())
        this.zzdj.zzp(); 
      return true;
    } catch (IOException iOException) {
      String str = String.valueOf(iOException.getMessage());
      if (str.length() != 0) {
        "Build channel failed: ".concat(str);
      } else {
        new String("Build channel failed: ");
      } 
      return false;
    } 
  }
  
  private final boolean zzan() {
    zzax zzax = this.zzdj.zzj();
    if (zzax != null && !zzax.zzj(this.zzan.zzad()))
      return true; 
    try {
      String str1 = this.zzdj.zzk();
      if (str1 == null)
        return false; 
      Log.isLoggable("FirebaseInstanceId", 3);
      if (zzax == null || (zzax != null && !str1.equals(zzax.zzbq))) {
        Context context = getContext();
        Intent intent2 = new Intent();
        this("com.google.firebase.messaging.NEW_TOKEN");
        intent2.putExtra("token", str1);
        zzav.zzc(context, intent2);
        Intent intent1 = new Intent();
        this("com.google.firebase.iid.TOKEN_REFRESH");
        zzav.zzb(context, intent1);
      } 
      return true;
    } catch (IOException iOException) {
    
    } catch (SecurityException securityException) {}
    String str = String.valueOf(securityException.getMessage());
    if (str.length() != 0) {
      "Token retrieval failed: ".concat(str);
    } else {
      new String("Token retrieval failed: ");
    } 
    return false;
  }
  
  final Context getContext() {
    return this.zzdj.zzh().getApplicationContext();
  }
  
  public final void run() {
    this.zzdi.acquire();
    try {
      this.zzdj.zza(true);
      if (!this.zzdj.zzn()) {
        this.zzdj.zza(false);
        return;
      } 
      if (!zzao()) {
        zzaz zzaz = new zzaz();
        this(this);
        zzaz.zzap();
        return;
      } 
      if (zzam() && zzan() && this.zzaq.zzc(this.zzdj)) {
        this.zzdj.zza(false);
      } else {
        this.zzdj.zza(this.zzdh);
      } 
      return;
    } finally {
      this.zzdi.release();
    } 
  }
  
  final boolean zzao() {
    ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService("connectivity");
    if (connectivityManager != null) {
      NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    } else {
      connectivityManager = null;
    } 
    return (connectivityManager != null && connectivityManager.isConnected());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */