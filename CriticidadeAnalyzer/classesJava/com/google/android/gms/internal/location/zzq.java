package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

public final class zzq implements FusedLocationProviderApi {
  public final PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationListener paramLocationListener) {
    Preconditions.checkNotNull(Looper.myLooper(), "Calling thread must be a prepared Looper thread.");
    return (PendingResult<Status>)paramGoogleApiClient.execute((BaseImplementation.ApiMethodImpl)new zzr(this, paramGoogleApiClient, paramLocationRequest, paramLocationListener));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */