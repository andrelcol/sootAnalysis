package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzbw;
import com.google.android.gms.measurement.internal.zzn;
import com.google.firebase.iid.FirebaseInstanceId;

public final class FirebaseAnalytics {
  private static volatile FirebaseAnalytics zzbsm;
  
  private final zzbw zzada;
  
  private FirebaseAnalytics(zzbw paramzzbw) {
    Preconditions.checkNotNull(paramzzbw);
    this.zzada = paramzzbw;
  }
  
  @Keep
  public static FirebaseAnalytics getInstance(Context paramContext) {
    // Byte code:
    //   0: getstatic com/google/firebase/analytics/FirebaseAnalytics.zzbsm : Lcom/google/firebase/analytics/FirebaseAnalytics;
    //   3: ifnonnull -> 46
    //   6: ldc com/google/firebase/analytics/FirebaseAnalytics
    //   8: monitorenter
    //   9: getstatic com/google/firebase/analytics/FirebaseAnalytics.zzbsm : Lcom/google/firebase/analytics/FirebaseAnalytics;
    //   12: ifnonnull -> 34
    //   15: aload_0
    //   16: aconst_null
    //   17: invokestatic zza : (Landroid/content/Context;Lcom/google/android/gms/measurement/internal/zzan;)Lcom/google/android/gms/measurement/internal/zzbw;
    //   20: astore_1
    //   21: new com/google/firebase/analytics/FirebaseAnalytics
    //   24: astore_0
    //   25: aload_0
    //   26: aload_1
    //   27: invokespecial <init> : (Lcom/google/android/gms/measurement/internal/zzbw;)V
    //   30: aload_0
    //   31: putstatic com/google/firebase/analytics/FirebaseAnalytics.zzbsm : Lcom/google/firebase/analytics/FirebaseAnalytics;
    //   34: ldc com/google/firebase/analytics/FirebaseAnalytics
    //   36: monitorexit
    //   37: goto -> 46
    //   40: astore_0
    //   41: ldc com/google/firebase/analytics/FirebaseAnalytics
    //   43: monitorexit
    //   44: aload_0
    //   45: athrow
    //   46: getstatic com/google/firebase/analytics/FirebaseAnalytics.zzbsm : Lcom/google/firebase/analytics/FirebaseAnalytics;
    //   49: areturn
    // Exception table:
    //   from	to	target	type
    //   9	34	40	finally
    //   34	37	40	finally
    //   41	44	40	finally
  }
  
  @Keep
  public final String getFirebaseInstanceId() {
    return FirebaseInstanceId.getInstance().getId();
  }
  
  public final void logEvent(String paramString, Bundle paramBundle) {
    this.zzada.zzkm().logEvent(paramString, paramBundle);
  }
  
  @Keep
  public final void setCurrentScreen(Activity paramActivity, String paramString1, String paramString2) {
    if (!zzn.isMainThread()) {
      this.zzada.zzgt().zzjj().zzby("setCurrentScreen must be called from the main thread");
      return;
    } 
    this.zzada.zzgm().setCurrentScreen(paramActivity, paramString1, paramString2);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/analytics/FirebaseAnalytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */