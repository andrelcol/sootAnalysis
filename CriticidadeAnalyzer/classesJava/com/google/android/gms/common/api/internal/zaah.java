package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import java.util.Iterator;

public final class zaah implements zabd {
  private final zabe zaft;
  
  private boolean zafu = false;
  
  public zaah(zabe paramzabe) {
    this.zaft = paramzabe;
  }
  
  public final void begin() {}
  
  public final void connect() {
    if (this.zafu) {
      this.zafu = false;
      this.zaft.zaa(new zaaj(this, this));
    } 
  }
  
  public final boolean disconnect() {
    if (this.zafu)
      return false; 
    if (this.zaft.zaee.zaax()) {
      this.zafu = true;
      Iterator<zacm> iterator = this.zaft.zaee.zahe.iterator();
      while (iterator.hasNext())
        ((zacm)iterator.next()).zabv(); 
      return false;
    } 
    this.zaft.zaf(null);
    return true;
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends Result, A>> T execute(T paramT) {
    try {
      this.zaft.zaee.zahf.zab((BasePendingResult<? extends Result>)paramT);
      zaaw zaaw = this.zaft.zaee;
      Api.AnyClientKey anyClientKey = paramT.getClientKey();
      Api.Client client = zaaw.zagz.get(anyClientKey);
      Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
      if (!client.isConnected() && this.zaft.zahp.containsKey(paramT.getClientKey())) {
        Status status = new Status();
        this(17);
        paramT.setFailedResult(status);
      } else {
        Api.SimpleClient simpleClient;
        Api.Client client1 = client;
        if (client instanceof SimpleClientAdapter)
          simpleClient = ((SimpleClientAdapter)client).getClient(); 
        paramT.run(simpleClient);
      } 
    } catch (DeadObjectException deadObjectException) {
      this.zaft.zaa(new zaai(this, this));
    } 
    return paramT;
  }
  
  public final void onConnected(Bundle paramBundle) {}
  
  public final void onConnectionSuspended(int paramInt) {
    this.zaft.zaf(null);
    this.zaft.zaht.zab(paramInt, this.zafu);
  }
  
  public final void zaa(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {}
  
  final void zaam() {
    if (this.zafu) {
      this.zafu = false;
      this.zaft.zaee.zahf.release();
      disconnect();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */