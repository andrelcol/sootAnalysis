package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbm {
  public static Looper zzc() {
    boolean bool;
    if (Looper.myLooper() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "Can't create handler inside thread that has not called Looper.prepare()");
    return Looper.myLooper();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzbm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */