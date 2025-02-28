package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberedThreadFactory implements ThreadFactory {
  private final ThreadFactory zzhr = Executors.defaultThreadFactory();
  
  private final String zzhs;
  
  private final AtomicInteger zzht = new AtomicInteger();
  
  public NumberedThreadFactory(String paramString) {
    this(paramString, 0);
  }
  
  private NumberedThreadFactory(String paramString, int paramInt) {
    Preconditions.checkNotNull(paramString, "Name must not be null");
    this.zzhs = paramString;
  }
  
  public Thread newThread(Runnable paramRunnable) {
    Thread thread = this.zzhr.newThread(new zza(paramRunnable, 0));
    String str = this.zzhs;
    int i = this.zzht.getAndIncrement();
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 13);
    stringBuilder.append(str);
    stringBuilder.append("[");
    stringBuilder.append(i);
    stringBuilder.append("]");
    thread.setName(stringBuilder.toString());
    return thread;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/concurrent/NumberedThreadFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */