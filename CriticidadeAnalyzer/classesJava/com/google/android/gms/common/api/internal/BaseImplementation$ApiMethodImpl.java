package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;

public abstract class BaseImplementation$ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements BaseImplementation$ResultHolder<R> {
  private final Api<?> mApi;
  
  private final Api.AnyClientKey<A> mClientKey;
  
  protected BaseImplementation$ApiMethodImpl(Api<?> paramApi, GoogleApiClient paramGoogleApiClient) {
    super(paramGoogleApiClient);
    Preconditions.checkNotNull(paramApi, "Api must not be null");
    this.mClientKey = paramApi.getClientKey();
    this.mApi = paramApi;
  }
  
  private void setFailedResult(RemoteException paramRemoteException) {
    setFailedResult(new Status(8, paramRemoteException.getLocalizedMessage(), null));
  }
  
  protected abstract void doExecute(A paramA) throws RemoteException;
  
  public final Api<?> getApi() {
    return this.mApi;
  }
  
  public final Api.AnyClientKey<A> getClientKey() {
    return this.mClientKey;
  }
  
  protected void onSetFailedResult(R paramR) {}
  
  public final void run(A paramA) throws DeadObjectException {
    Api.SimpleClient simpleClient;
    A a = paramA;
    if (paramA instanceof SimpleClientAdapter)
      simpleClient = ((SimpleClientAdapter)paramA).getClient(); 
    try {
      doExecute((A)simpleClient);
      return;
    } catch (DeadObjectException deadObjectException) {
      setFailedResult((RemoteException)deadObjectException);
      throw deadObjectException;
    } catch (RemoteException remoteException) {
      setFailedResult(remoteException);
      return;
    } 
  }
  
  public final void setFailedResult(Status paramStatus) {
    Preconditions.checkArgument(paramStatus.isSuccess() ^ true, "Failed result must not be success");
    createFailedResult(paramStatus);
    setResult((R)paramStatus);
    onSetFailedResult((R)paramStatus);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/BaseImplementation$ApiMethodImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */