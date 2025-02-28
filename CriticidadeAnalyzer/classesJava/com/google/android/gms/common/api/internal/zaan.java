package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Map;

final class zaan extends zaau {
  final zaak zagj;
  
  private final Map<Api.Client, zaam> zagl;
  
  public zaan(zaak paramzaak, Map<Api.Client, zaam> paramMap) {
    super(paramzaak, null);
    this.zagl = paramMap;
  }
  
  public final void zaan() {
    ConnectionResult connectionResult;
    Api.Client<ArrayList<Api.Client>> client;
    GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(zaak.zab(this.zagj));
    ArrayList<Api.Client> arrayList2 = new ArrayList();
    ArrayList<Api.Client> arrayList1 = new ArrayList();
    for (Api.Client client1 : this.zagl.keySet()) {
      if (client1.requiresGooglePlayServices() && !zaam.zaa(this.zagl.get(client1))) {
        arrayList2.add(client1);
        continue;
      } 
      arrayList1.add(client1);
    } 
    int i = -1;
    boolean bool = arrayList2.isEmpty();
    int j = 0;
    int k = 0;
    if (bool) {
      int m = arrayList1.size();
      j = k;
      while (j < m) {
        arrayList2 = (ArrayList<Api.Client>)arrayList1.get(j);
        j++;
        client = (Api.Client)arrayList2;
        k = googleApiAvailabilityCache.getClientAvailability(zaak.zaa(this.zagj), client);
        i = k;
        if (k == 0) {
          i = k;
          break;
        } 
      } 
    } else {
      int m = client.size();
      while (j < m) {
        arrayList1 = client.get(j);
        j++;
        Api.Client client1 = (Api.Client)arrayList1;
        k = googleApiAvailabilityCache.getClientAvailability(zaak.zaa(this.zagj), client1);
        i = k;
        if (k != 0) {
          i = k;
          break;
        } 
      } 
    } 
    if (i != 0) {
      connectionResult = new ConnectionResult(i, null);
      zaak.zad(this.zagj).zaa(new zaao(this, this.zagj, connectionResult));
      return;
    } 
    if (zaak.zae(this.zagj) && zaak.zaf(this.zagj) != null)
      zaak.zaf(this.zagj).connect(); 
    for (Api.Client client1 : this.zagl.keySet()) {
      BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks = this.zagl.get(client1);
      if (client1.requiresGooglePlayServices() && connectionResult.getClientAvailability(zaak.zaa(this.zagj), client1) != 0) {
        zaak.zad(this.zagj).zaa(new zaap(this, this.zagj, connectionProgressReportCallbacks));
        continue;
      } 
      client1.connect(connectionProgressReportCallbacks);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */