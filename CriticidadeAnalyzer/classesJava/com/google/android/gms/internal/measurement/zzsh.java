package com.google.android.gms.internal.measurement;

import android.net.Uri;

public final class zzsh {
  public static Uri zzfq(String paramString) {
    paramString = String.valueOf(Uri.encode(paramString));
    if (paramString.length() != 0) {
      paramString = "content://com.google.android.gms.phenotype/".concat(paramString);
    } else {
      paramString = new String("content://com.google.android.gms.phenotype/");
    } 
    return Uri.parse(paramString);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzsh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */