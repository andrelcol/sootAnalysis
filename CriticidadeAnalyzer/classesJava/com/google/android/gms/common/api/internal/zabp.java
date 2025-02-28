package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;

public final class zabp<O extends Api.ApiOptions> extends zaag {
  private final GoogleApi<O> zajh;
  
  public zabp(GoogleApi<O> paramGoogleApi) {
    super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
    this.zajh = paramGoogleApi;
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(T paramT) {
    this.zajh.doWrite((BaseImplementation$ApiMethodImpl)paramT);
    return paramT;
  }
  
  public final Looper getLooper() {
    return this.zajh.getLooper();
  }
  
  public final void zab(zacm paramzacm) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */