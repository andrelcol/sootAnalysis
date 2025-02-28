package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.api.Api;

public class SimpleClientAdapter<T extends IInterface> extends GmsClient<T> {
  private final Api.SimpleClient<T> zapg;
  
  protected T createServiceInterface(IBinder paramIBinder) {
    return (T)this.zapg.createServiceInterface(paramIBinder);
  }
  
  public Api.SimpleClient<T> getClient() {
    return this.zapg;
  }
  
  public int getMinApkVersion() {
    return super.getMinApkVersion();
  }
  
  protected String getServiceDescriptor() {
    return this.zapg.getServiceDescriptor();
  }
  
  protected String getStartServiceAction() {
    return this.zapg.getStartServiceAction();
  }
  
  protected void onSetConnectState(int paramInt, T paramT) {
    this.zapg.setState(paramInt, (IInterface)paramT);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/SimpleClientAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */