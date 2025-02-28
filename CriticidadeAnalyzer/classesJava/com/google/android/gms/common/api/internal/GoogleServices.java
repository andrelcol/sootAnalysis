package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzp;

@Deprecated
public final class GoogleServices {
  private static final Object sLock = new Object();
  
  private static GoogleServices zzay;
  
  private final String zzaz;
  
  private final Status zzba;
  
  private final boolean zzbc;
  
  GoogleServices(Context paramContext) {
    Resources resources = paramContext.getResources();
    int i = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
    boolean bool = false;
    if (i != 0) {
      if (resources.getInteger(i) != 0)
        bool = true; 
      this.zzbc = bool ^ true;
    } else {
      this.zzbc = false;
    } 
    String str2 = zzp.zzc(paramContext);
    String str1 = str2;
    if (str2 == null)
      str1 = (new StringResourceValueReader(paramContext)).getString("google_app_id"); 
    if (TextUtils.isEmpty(str1)) {
      this.zzba = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
      this.zzaz = null;
      return;
    } 
    this.zzaz = str1;
    this.zzba = Status.RESULT_SUCCESS;
  }
  
  private static GoogleServices checkInitialized(String paramString) {
    synchronized (sLock) {
      GoogleServices googleServices;
      if (zzay != null) {
        googleServices = zzay;
        return googleServices;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      int i = String.valueOf(googleServices).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(i + 34);
      stringBuilder.append("Initialize must be called before ");
      stringBuilder.append((String)googleServices);
      stringBuilder.append(".");
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
  
  public static String getGoogleAppId() {
    return (checkInitialized("getGoogleAppId")).zzaz;
  }
  
  public static Status initialize(Context paramContext) {
    Preconditions.checkNotNull(paramContext, "Context must not be null.");
    synchronized (sLock) {
      if (zzay == null) {
        GoogleServices googleServices = new GoogleServices();
        this(paramContext);
        zzay = googleServices;
      } 
      return zzay.zzba;
    } 
  }
  
  public static boolean isMeasurementExplicitlyDisabled() {
    return (checkInitialized("isMeasurementExplicitlyDisabled")).zzbc;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/GoogleServices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */