package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
  private final String name;
  
  private final ThreadFactory zzhr = Executors.defaultThreadFactory();
  
  public NamedThreadFactory(String paramString) {
    this(paramString, 0);
  }
  
  private NamedThreadFactory(String paramString, int paramInt) {
    Preconditions.checkNotNull(paramString, "Name must not be null");
    this.name = paramString;
  }
  
  public Thread newThread(Runnable paramRunnable) {
    paramRunnable = this.zzhr.newThread(new zza(paramRunnable, 0));
    paramRunnable.setName(this.name);
    return (Thread)paramRunnable;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/concurrent/NamedThreadFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */