package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public final class zzbr extends zzcs {
  private static final AtomicLong zzapc = new AtomicLong(Long.MIN_VALUE);
  
  private zzbv zzaot;
  
  private zzbv zzaou;
  
  private final PriorityBlockingQueue<zzbu<?>> zzaov = new PriorityBlockingQueue<zzbu<?>>();
  
  private final BlockingQueue<zzbu<?>> zzaow = new LinkedBlockingQueue<zzbu<?>>();
  
  private final Thread.UncaughtExceptionHandler zzaox = new zzbt(this, "Thread death: Uncaught exception on worker thread");
  
  private final Thread.UncaughtExceptionHandler zzaoy = new zzbt(this, "Thread death: Uncaught exception on network thread");
  
  private final Object zzaoz = new Object();
  
  private final Semaphore zzapa = new Semaphore(2);
  
  private volatile boolean zzapb;
  
  zzbr(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private final void zza(zzbu<?> paramzzbu) {
    synchronized (this.zzaoz) {
      this.zzaov.add(paramzzbu);
      if (this.zzaot == null) {
        zzbv zzbv1 = new zzbv();
        this(this, "Measurement Worker", this.zzaov);
        this.zzaot = zzbv1;
        this.zzaot.setUncaughtExceptionHandler(this.zzaox);
        this.zzaot.start();
      } else {
        this.zzaot.zzki();
      } 
      return;
    } 
  }
  
  public final void zzaf() {
    if (Thread.currentThread() == this.zzaot)
      return; 
    throw new IllegalStateException("Call expected from worker thread");
  }
  
  public final <V> Future<V> zzb(Callable<V> paramCallable) throws IllegalStateException {
    zzcl();
    Preconditions.checkNotNull(paramCallable);
    zzbu<V> zzbu = new zzbu<V>(this, paramCallable, false, "Task exception on worker thread");
    if (Thread.currentThread() == this.zzaot) {
      if (!this.zzaov.isEmpty())
        zzgt().zzjj().zzby("Callable skipped the worker queue."); 
      zzbu.run();
    } else {
      zza(zzbu);
    } 
    return zzbu;
  }
  
  public final <V> Future<V> zzc(Callable<V> paramCallable) throws IllegalStateException {
    zzcl();
    Preconditions.checkNotNull(paramCallable);
    zzbu<V> zzbu = new zzbu<V>(this, paramCallable, true, "Task exception on worker thread");
    if (Thread.currentThread() == this.zzaot) {
      zzbu.run();
    } else {
      zza(zzbu);
    } 
    return zzbu;
  }
  
  public final void zzc(Runnable paramRunnable) throws IllegalStateException {
    zzcl();
    Preconditions.checkNotNull(paramRunnable);
    zza(new zzbu(this, paramRunnable, false, "Task exception on worker thread"));
  }
  
  public final void zzd(Runnable paramRunnable) throws IllegalStateException {
    zzcl();
    Preconditions.checkNotNull(paramRunnable);
    null = new zzbu(this, paramRunnable, false, "Task exception on network thread");
    synchronized (this.zzaoz) {
      this.zzaow.add(null);
      if (this.zzaou == null) {
        zzbv zzbv1 = new zzbv();
        this(this, "Measurement Network", this.zzaow);
        this.zzaou = zzbv1;
        this.zzaou.setUncaughtExceptionHandler(this.zzaoy);
        this.zzaou.start();
      } else {
        this.zzaou.zzki();
      } 
      return;
    } 
  }
  
  public final void zzgh() {
    if (Thread.currentThread() == this.zzaou)
      return; 
    throw new IllegalStateException("Call expected from network thread");
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  public final boolean zzkf() {
    return (Thread.currentThread() == this.zzaot);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */