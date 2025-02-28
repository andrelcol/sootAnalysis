package com.google.android.gms.internal.location;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzx;
import java.util.HashMap;
import java.util.Map;

public final class zzas {
  private final zzbj<zzao> zzcb;
  
  private boolean zzcw = false;
  
  private final Map<ListenerHolder.ListenerKey<LocationListener>, zzax> zzcx = new HashMap<ListenerHolder.ListenerKey<LocationListener>, zzax>();
  
  private final Map<ListenerHolder.ListenerKey<Object>, zzaw> zzcy = new HashMap<ListenerHolder.ListenerKey<Object>, zzaw>();
  
  private final Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> zzcz = new HashMap<ListenerHolder.ListenerKey<LocationCallback>, zzat>();
  
  public zzas(Context paramContext, zzbj<zzao> paramzzbj) {
    this.zzcb = paramzzbj;
  }
  
  private final zzax zza(ListenerHolder<LocationListener> paramListenerHolder) {
    synchronized (this.zzcx) {
      zzax zzax2 = this.zzcx.get(paramListenerHolder.getListenerKey());
      zzax zzax1 = zzax2;
      if (zzax2 == null) {
        zzax1 = new zzax();
        this(paramListenerHolder);
      } 
      this.zzcx.put(paramListenerHolder.getListenerKey(), zzax1);
      return zzax1;
    } 
  }
  
  public final void removeAllListeners() throws RemoteException {
    synchronized (this.zzcx) {
      for (zzax zzax : this.zzcx.values()) {
        if (zzax != null)
          ((zzao)this.zzcb.getService()).zza(zzbf.zza((zzx)zzax, (zzaj)null)); 
      } 
      this.zzcx.clear();
      synchronized (this.zzcz) {
        for (zzat zzat : this.zzcz.values()) {
          if (zzat != null)
            ((zzao)this.zzcb.getService()).zza(zzbf.zza((zzu)zzat, (zzaj)null)); 
        } 
        this.zzcz.clear();
        synchronized (this.zzcy) {
          for (zzaw zzaw : this.zzcy.values()) {
            if (zzaw != null) {
              zzao zzao = this.zzcb.getService();
              zzo zzo = new zzo();
              this(2, null, zzaw.asBinder(), null);
              zzao.zza(zzo);
            } 
          } 
          this.zzcy.clear();
          return;
        } 
      } 
    } 
  }
  
  public final void zza(LocationRequest paramLocationRequest, ListenerHolder<LocationListener> paramListenerHolder, zzaj paramzzaj) throws RemoteException {
    this.zzcb.checkConnected();
    zzax zzax = zza(paramListenerHolder);
    zzao zzao = this.zzcb.getService();
    zzbd zzbd = zzbd.zza(paramLocationRequest);
    IBinder iBinder = zzax.asBinder();
    if (paramzzaj != null) {
      IBinder iBinder1 = paramzzaj.asBinder();
    } else {
      paramLocationRequest = null;
    } 
    zzao.zza(new zzbf(1, zzbd, iBinder, null, null, (IBinder)paramLocationRequest));
  }
  
  public final void zza(boolean paramBoolean) throws RemoteException {
    this.zzcb.checkConnected();
    ((zzao)this.zzcb.getService()).zza(paramBoolean);
    this.zzcw = paramBoolean;
  }
  
  public final void zzb() throws RemoteException {
    if (this.zzcw)
      zza(false); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */