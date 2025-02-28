package com.google.android.gms.common.api.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Map;

final class zaz implements OnCompleteListener<Map<zai<?>, String>> {
  private final zax zafi;
  
  private zaz(zax paramzax) {}
  
  public final void onComplete(Task<Map<zai<?>, String>> paramTask) {
    zax.zaa(this.zafi).lock();
    try {
      boolean bool = zax.zab(this.zafi);
      if (!bool)
        return; 
      if (paramTask.isSuccessful()) {
        zax zax1 = this.zafi;
        ArrayMap arrayMap = new ArrayMap();
        this(zax.zac(this.zafi).size());
        zax.zaa(zax1, (Map)arrayMap);
        for (zaw zaw : zax.zac(this.zafi).values())
          zax.zad(this.zafi).put(zaw.zak(), ConnectionResult.RESULT_SUCCESS); 
      } else {
        AvailabilityException availabilityException;
        if (zaw.getException() instanceof AvailabilityException) {
          availabilityException = (AvailabilityException)zaw.getException();
          if (zax.zae(this.zafi)) {
            zax zax1 = this.zafi;
            ArrayMap arrayMap = new ArrayMap();
            this(zax.zac(this.zafi).size());
            zax.zaa(zax1, (Map)arrayMap);
            for (zaw zaw1 : zax.zac(this.zafi).values()) {
              Map<zai, ConnectionResult> map;
              zai zai = zaw1.zak();
              ConnectionResult connectionResult = availabilityException.getConnectionResult(zaw1);
              if (zax.zaa(this.zafi, zaw1, connectionResult)) {
                map = zax.zad(this.zafi);
                ConnectionResult connectionResult1 = new ConnectionResult();
                this(16);
                map.put(zai, connectionResult1);
                continue;
              } 
              zax.zad(this.zafi).put(zai, map);
            } 
          } else {
            zax.zaa(this.zafi, (Map)availabilityException.zaj());
          } 
          zax.zaa(this.zafi, zax.zaf(this.zafi));
        } else {
          availabilityException.getException();
          zax.zaa(this.zafi, Collections.emptyMap());
          zax zax1 = this.zafi;
          ConnectionResult connectionResult = new ConnectionResult();
          this(8);
          zax.zaa(zax1, connectionResult);
        } 
      } 
      if (zax.zag(this.zafi) != null) {
        zax.zad(this.zafi).putAll(zax.zag(this.zafi));
        zax.zaa(this.zafi, zax.zaf(this.zafi));
      } 
      if (zax.zah(this.zafi) == null) {
        zax.zai(this.zafi);
        zax.zaj(this.zafi);
      } else {
        zax.zaa(this.zafi, false);
        zax.zak(this.zafi).zac(zax.zah(this.zafi));
      } 
      zax.zal(this.zafi).signalAll();
      return;
    } finally {
      zax.zaa(this.zafi).unlock();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */