package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.zzy;

final class zzax extends zzy {
  private final ListenerHolder<LocationListener> zzda;
  
  zzax(ListenerHolder<LocationListener> paramListenerHolder) {
    this.zzda = paramListenerHolder;
  }
  
  public final void onLocationChanged(Location paramLocation) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzda : Lcom/google/android/gms/common/api/internal/ListenerHolder;
    //   6: astore_2
    //   7: new com/google/android/gms/internal/location/zzay
    //   10: astore_3
    //   11: aload_3
    //   12: aload_0
    //   13: aload_1
    //   14: invokespecial <init> : (Lcom/google/android/gms/internal/location/zzax;Landroid/location/Location;)V
    //   17: aload_2
    //   18: aload_3
    //   19: invokevirtual notifyListener : (Lcom/google/android/gms/common/api/internal/ListenerHolder$Notifier;)V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	25	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */