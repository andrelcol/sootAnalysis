package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;

public final class UnsupportedApiCallException extends UnsupportedOperationException {
  private final Feature zzas;
  
  public UnsupportedApiCallException(Feature paramFeature) {
    this.zzas = paramFeature;
  }
  
  public final String getMessage() {
    String str = String.valueOf(this.zzas);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 8);
    stringBuilder.append("Missing ");
    stringBuilder.append(str);
    return stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/UnsupportedApiCallException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */