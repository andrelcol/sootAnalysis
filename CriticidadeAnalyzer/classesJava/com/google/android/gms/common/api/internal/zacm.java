package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;

public final class zacm<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
  private final Object zado;
  
  private final WeakReference<GoogleApiClient> zadq;
  
  private ResultTransform<? super R, ? extends Result> zako;
  
  private zacm<? extends Result> zakp;
  
  private volatile ResultCallbacks<? super R> zakq;
  
  private Status zaks;
  
  private final zaco zakt;
  
  private static void zab(Result paramResult) {
    if (paramResult instanceof Releasable)
      try {
        ((Releasable)paramResult).release();
        return;
      } catch (RuntimeException runtimeException) {
        String str = String.valueOf(paramResult);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 18);
        stringBuilder.append("Unable to release ");
        stringBuilder.append(str);
        stringBuilder.toString();
      }  
  }
  
  private final boolean zabw() {
    GoogleApiClient googleApiClient = this.zadq.get();
    return (this.zakq != null && googleApiClient != null);
  }
  
  private final void zad(Status paramStatus) {
    synchronized (this.zado) {
      this.zaks = paramStatus;
      zae(this.zaks);
      return;
    } 
  }
  
  private final void zae(Status paramStatus) {
    synchronized (this.zado) {
      if (this.zako != null) {
        paramStatus = this.zako.onFailure(paramStatus);
        Preconditions.checkNotNull(paramStatus, "onFailure must not return null");
        this.zakp.zad(paramStatus);
      } else if (zabw()) {
        this.zakq.onFailure(paramStatus);
      } 
      return;
    } 
  }
  
  public final void onResult(R paramR) {
    synchronized (this.zado) {
      if (paramR.getStatus().isSuccess()) {
        if (this.zako != null) {
          ExecutorService executorService = zacc.zabb();
          zacn zacn = new zacn();
          this(this, (Result)paramR);
          executorService.submit(zacn);
        } else if (zabw()) {
          this.zakq.onSuccess((Result)paramR);
        } 
      } else {
        zad(paramR.getStatus());
        zab((Result)paramR);
      } 
      return;
    } 
  }
  
  final void zabv() {
    this.zakq = null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zacm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */