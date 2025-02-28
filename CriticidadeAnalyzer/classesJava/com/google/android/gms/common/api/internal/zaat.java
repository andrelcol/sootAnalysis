package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.signin.internal.zad;
import com.google.android.gms.signin.zad;

final class zaat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
  private final zaak zagj;
  
  private zaat(zaak paramzaak) {}
  
  public final void onConnected(Bundle paramBundle) {
    if (zaak.zai(this.zagj).isSignInClientDisconnectFixEnabled()) {
      zaak.zac(this.zagj).lock();
      try {
        zad zad1 = zaak.zaf(this.zagj);
        if (zad1 == null)
          return; 
        zad zad2 = zaak.zaf(this.zagj);
        zaar zaar = new zaar();
        this(this.zagj);
        zad2.zaa((zad)zaar);
        return;
      } finally {
        zaak.zac(this.zagj).unlock();
      } 
    } 
    zaak.zaf(this.zagj).zaa((zad)new zaar(this.zagj));
  }
  
  public final void onConnectionFailed(ConnectionResult paramConnectionResult) {
    zaak.zac(this.zagj).lock();
    try {
      if (zaak.zab(this.zagj, paramConnectionResult)) {
        zaak.zaj(this.zagj);
        zaak.zak(this.zagj);
      } else {
        zaak.zaa(this.zagj, paramConnectionResult);
      } 
      return;
    } finally {
      zaak.zac(this.zagj).unlock();
    } 
  }
  
  public final void onConnectionSuspended(int paramInt) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */