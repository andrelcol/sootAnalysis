package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

public class GoogleApiAvailabilityCache {
  private final SparseIntArray zaos = new SparseIntArray();
  
  private GoogleApiAvailabilityLight zaot;
  
  public GoogleApiAvailabilityCache(GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight) {
    Preconditions.checkNotNull(paramGoogleApiAvailabilityLight);
    this.zaot = paramGoogleApiAvailabilityLight;
  }
  
  public void flush() {
    this.zaos.clear();
  }
  
  public int getClientAvailability(Context paramContext, Api.Client paramClient) {
    int i;
    Preconditions.checkNotNull(paramContext);
    Preconditions.checkNotNull(paramClient);
    if (!paramClient.requiresGooglePlayServices())
      return 0; 
    int m = paramClient.getMinApkVersion();
    int k = this.zaos.get(m, -1);
    if (k != -1)
      return k; 
    int j = 0;
    while (true) {
      i = k;
      if (j < this.zaos.size()) {
        i = this.zaos.keyAt(j);
        if (i > m && this.zaos.get(i) == 0) {
          i = 0;
          break;
        } 
        j++;
        continue;
      } 
      break;
    } 
    j = i;
    if (i == -1)
      j = this.zaot.isGooglePlayServicesAvailable(paramContext, m); 
    this.zaos.put(m, j);
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/GoogleApiAvailabilityCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */