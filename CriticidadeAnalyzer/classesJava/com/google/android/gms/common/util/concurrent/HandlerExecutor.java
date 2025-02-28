package com.google.android.gms.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.common.zze;
import java.util.concurrent.Executor;

public class HandlerExecutor implements Executor {
  private final Handler handler;
  
  public HandlerExecutor(Looper paramLooper) {
    this.handler = (Handler)new zze(paramLooper);
  }
  
  public void execute(Runnable paramRunnable) {
    this.handler.post(paramRunnable);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/concurrent/HandlerExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */