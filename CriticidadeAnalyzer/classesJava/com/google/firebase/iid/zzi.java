package com.google.firebase.iid;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class zzi {
  private static final Executor zzad = zzk.zzaf;
  
  static Executor zze() {
    return zzad;
  }
  
  static Executor zzf() {
    return new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), zzj.zzae);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */