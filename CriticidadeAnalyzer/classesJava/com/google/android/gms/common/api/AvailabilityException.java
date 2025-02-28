package com.google.android.gms.common.api;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;

public class AvailabilityException extends Exception {
  private final ArrayMap<zai<?>, ConnectionResult> zaay;
  
  public AvailabilityException(ArrayMap<zai<?>, ConnectionResult> paramArrayMap) {
    this.zaay = paramArrayMap;
  }
  
  public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> paramGoogleApi) {
    boolean bool;
    zai<? extends Api.ApiOptions> zai = paramGoogleApi.zak();
    if (this.zaay.get(zai) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "The given API was not part of the availability request.");
    return (ConnectionResult)this.zaay.get(zai);
  }
  
  public String getMessage() {
    ArrayList<String> arrayList = new ArrayList();
    Iterator<zai> iterator = this.zaay.keySet().iterator();
    boolean bool = true;
    while (iterator.hasNext()) {
      zai zai = iterator.next();
      ConnectionResult connectionResult = (ConnectionResult)this.zaay.get(zai);
      if (connectionResult.isSuccess())
        bool = false; 
      String str1 = zai.zan();
      String str2 = String.valueOf(connectionResult);
      StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(str1).length() + 2 + String.valueOf(str2).length());
      stringBuilder1.append(str1);
      stringBuilder1.append(": ");
      stringBuilder1.append(str2);
      arrayList.add(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    if (bool) {
      stringBuilder.append("None of the queried APIs are available. ");
    } else {
      stringBuilder.append("Some of the queried APIs are unavailable. ");
    } 
    stringBuilder.append(TextUtils.join("; ", arrayList));
    return stringBuilder.toString();
  }
  
  public final ArrayMap<zai<?>, ConnectionResult> zaj() {
    return this.zaay;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/AvailabilityException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */