package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BlockingServiceConnection implements ServiceConnection {
  private boolean zze = false;
  
  private final BlockingQueue<IBinder> zzf = new LinkedBlockingQueue<IBinder>();
  
  public IBinder getServiceWithTimeout(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException, TimeoutException {
    Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
    if (!this.zze) {
      this.zze = true;
      IBinder iBinder = this.zzf.poll(paramLong, paramTimeUnit);
      if (iBinder != null)
        return iBinder; 
      throw new TimeoutException("Timed out waiting for the service connection");
    } 
    throw new IllegalStateException("Cannot call get on this connection more than once");
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    this.zzf.add(paramIBinder);
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/BlockingServiceConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */