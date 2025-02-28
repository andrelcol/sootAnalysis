package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

final class zzbv extends Thread {
  private final zzbr zzape;
  
  private final Object zzaph;
  
  private final BlockingQueue<zzbu<?>> zzapi;
  
  public zzbv(zzbr paramzzbr, String paramString, BlockingQueue<zzbu<?>> paramBlockingQueue) {
    Preconditions.checkNotNull(paramString);
    Preconditions.checkNotNull(paramBlockingQueue);
    this.zzaph = new Object();
    this.zzapi = paramBlockingQueue;
    setName(paramString);
  }
  
  private final void zza(InterruptedException paramInterruptedException) {
    this.zzape.zzgt().zzjj().zzg(String.valueOf(getName()).concat(" was interrupted"), paramInterruptedException);
  }
  
  public final void run() {
    int i = 0;
    while (!i) {
      try {
        zzbr.zza(this.zzape).acquire();
        i = 1;
      } catch (InterruptedException interruptedException) {
        zza(interruptedException);
      } 
    } 
    try {
      int j = Process.getThreadPriority(Process.myTid());
      while (true) {
        zzbu zzbu = this.zzapi.poll();
        if (zzbu != null) {
          if (zzbu.zzapg) {
            i = j;
          } else {
            i = 10;
          } 
          Process.setThreadPriority(i);
          zzbu.run();
          continue;
        } 
        synchronized (this.zzaph) {
          if (this.zzapi.peek() == null) {
            boolean bool = zzbr.zzb(this.zzape);
            if (!bool)
              try {
                this.zzaph.wait(30000L);
              } catch (InterruptedException interruptedException) {
                zza(interruptedException);
              }  
          } 
          synchronized (zzbr.zzc(this.zzape)) {
            if (this.zzapi.peek() == null)
              synchronized (zzbr.zzc(this.zzape)) {
                zzbr.zza(this.zzape).release();
                zzbr.zzc(this.zzape).notifyAll();
                return;
              }  
          } 
        } 
      } 
    } finally {
      null = null;
    } 
  }
  
  public final void zzki() {
    synchronized (this.zzaph) {
      this.zzaph.notifyAll();
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */