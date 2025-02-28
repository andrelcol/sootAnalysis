package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

final class zzr<TResult> {
  private final Object mLock = new Object();
  
  private Queue<zzq<TResult>> zzt;
  
  private boolean zzu;
  
  public final void zza(Task<TResult> paramTask) {
    synchronized (this.mLock) {
      if (this.zzt == null || this.zzu)
        return; 
      this.zzu = true;
      while (true) {
        synchronized (this.mLock) {
          zzq<TResult> zzq = this.zzt.poll();
          if (zzq == null) {
            this.zzu = false;
            return;
          } 
          zzq.onComplete(paramTask);
        } 
      } 
    } 
  }
  
  public final void zza(zzq<TResult> paramzzq) {
    synchronized (this.mLock) {
      if (this.zzt == null) {
        ArrayDeque<zzq<TResult>> arrayDeque = new ArrayDeque();
        this();
        this.zzt = arrayDeque;
      } 
      this.zzt.add(paramzzq);
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */