package com.google.android.gms.common;

import android.content.Context;
import android.content.res.Resources;

public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight {
  @Deprecated
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  
  public static Context getRemoteContext(Context paramContext) {
    return GooglePlayServicesUtilLight.getRemoteContext(paramContext);
  }
  
  public static Resources getRemoteResource(Context paramContext) {
    return GooglePlayServicesUtilLight.getRemoteResource(paramContext);
  }
  
  @Deprecated
  public static int isGooglePlayServicesAvailable(Context paramContext, int paramInt) {
    return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(paramContext, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/GooglePlayServicesUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */