package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zacp {
  public static final Status zakx = new Status(8, "The connection to Google Play services was lost");
  
  private static final BasePendingResult<?>[] zaky = (BasePendingResult<?>[])new BasePendingResult[0];
  
  private final Map<Api.AnyClientKey<?>, Api.Client> zagz;
  
  final Set<BasePendingResult<?>> zakz = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap<BasePendingResult<?>, Boolean>()));
  
  private final zacs zala = new zacq(this);
  
  public zacp(Map<Api.AnyClientKey<?>, Api.Client> paramMap) {
    this.zagz = paramMap;
  }
  
  public final void release() {
    for (BasePendingResult basePendingResult : (BasePendingResult[])this.zakz.<BasePendingResult>toArray((BasePendingResult[])zaky)) {
      basePendingResult.zaa((zacs)null);
      if (basePendingResult.zam() == null) {
        if (basePendingResult.zat())
          this.zakz.remove(basePendingResult); 
      } else {
        basePendingResult.setResultCallback(null);
        IBinder iBinder = ((Api.Client)this.zagz.get(((BaseImplementation$ApiMethodImpl)basePendingResult).getClientKey())).getServiceBrokerBinder();
        if (basePendingResult.isReady()) {
          basePendingResult.zaa(new zacr(basePendingResult, null, iBinder, null));
        } else if (iBinder != null && iBinder.isBinderAlive()) {
          zacr zacr = new zacr(basePendingResult, null, iBinder, null);
          basePendingResult.zaa(zacr);
          try {
            iBinder.linkToDeath(zacr, 0);
          } catch (RemoteException remoteException) {
            basePendingResult.cancel();
            basePendingResult.zam().intValue();
            throw new NullPointerException();
          } 
        } else {
          basePendingResult.zaa((zacs)null);
          basePendingResult.cancel();
          basePendingResult.zam().intValue();
          throw new NullPointerException();
        } 
        this.zakz.remove(basePendingResult);
      } 
    } 
  }
  
  final void zab(BasePendingResult<? extends Result> paramBasePendingResult) {
    this.zakz.add(paramBasePendingResult);
    paramBasePendingResult.zaa(this.zala);
  }
  
  public final void zabx() {
    BasePendingResult[] arrayOfBasePendingResult = this.zakz.<BasePendingResult>toArray((BasePendingResult[])zaky);
    int i = arrayOfBasePendingResult.length;
    for (byte b = 0; b < i; b++)
      arrayOfBasePendingResult[b].zab(zakx); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zacp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */