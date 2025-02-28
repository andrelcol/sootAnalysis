package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

public final class zai<O extends Api.ApiOptions> {
  private final Api<O> mApi;
  
  private final O zabh;
  
  private final boolean zacu = true;
  
  private final int zacv;
  
  private zai(Api<O> paramApi) {
    this.mApi = paramApi;
    this.zabh = null;
    this.zacv = System.identityHashCode(this);
  }
  
  public static <O extends Api.ApiOptions> zai<O> zaa(Api<O> paramApi) {
    return new zai<O>(paramApi);
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zai))
      return false; 
    paramObject = paramObject;
    return (!this.zacu && !((zai)paramObject).zacu && Objects.equal(this.mApi, ((zai)paramObject).mApi) && Objects.equal(this.zabh, ((zai)paramObject).zabh));
  }
  
  public final int hashCode() {
    return this.zacv;
  }
  
  public final String zan() {
    return this.mApi.getName();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */