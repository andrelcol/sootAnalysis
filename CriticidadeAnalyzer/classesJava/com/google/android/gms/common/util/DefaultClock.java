package com.google.android.gms.common.util;

import android.os.SystemClock;

public class DefaultClock implements Clock {
  private static final DefaultClock zzgm = new DefaultClock();
  
  public static Clock getInstance() {
    return zzgm;
  }
  
  public long currentTimeMillis() {
    return System.currentTimeMillis();
  }
  
  public long elapsedRealtime() {
    return SystemClock.elapsedRealtime();
  }
  
  public long nanoTime() {
    return System.nanoTime();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/DefaultClock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */