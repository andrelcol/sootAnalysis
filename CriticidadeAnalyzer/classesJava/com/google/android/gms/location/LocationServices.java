package com.google.android.gms.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.location.zzaf;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzbk;
import com.google.android.gms.internal.location.zzq;

public class LocationServices {
  public static final Api<Object> API;
  
  private static final Api.AbstractClientBuilder<zzaz, Object> CLIENT_BUILDER;
  
  private static final Api.ClientKey<zzaz> CLIENT_KEY = new Api.ClientKey();
  
  @Deprecated
  public static final FusedLocationProviderApi FusedLocationApi;
  
  static {
    CLIENT_BUILDER = new zzad();
    API = new Api("LocationServices.API", CLIENT_BUILDER, CLIENT_KEY);
    FusedLocationApi = (FusedLocationProviderApi)new zzq();
    new zzaf();
    new zzbk();
  }
  
  public static abstract class zza<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzaz> {
    public zza(GoogleApiClient param1GoogleApiClient) {
      super(LocationServices.API, param1GoogleApiClient);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/location/LocationServices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */