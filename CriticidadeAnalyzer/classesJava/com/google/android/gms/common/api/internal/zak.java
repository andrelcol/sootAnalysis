package com.google.android.gms.common.api.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import java.util.Set;

public final class zak {
  private final ArrayMap<zai<?>, ConnectionResult> zaay = new ArrayMap();
  
  private final ArrayMap<zai<?>, String> zadb = new ArrayMap();
  
  private final TaskCompletionSource<Map<zai<?>, String>> zadc = new TaskCompletionSource();
  
  private int zadd;
  
  private boolean zade = false;
  
  public zak(Iterable<? extends GoogleApi<?>> paramIterable) {
    for (GoogleApi<?> googleApi : paramIterable)
      this.zaay.put(googleApi.zak(), null); 
    this.zadd = this.zaay.keySet().size();
  }
  
  public final Task<Map<zai<?>, String>> getTask() {
    return this.zadc.getTask();
  }
  
  public final void zaa(zai<?> paramzai, ConnectionResult paramConnectionResult, String paramString) {
    this.zaay.put(paramzai, paramConnectionResult);
    this.zadb.put(paramzai, paramString);
    this.zadd--;
    if (!paramConnectionResult.isSuccess())
      this.zade = true; 
    if (this.zadd == 0) {
      if (this.zade) {
        AvailabilityException availabilityException = new AvailabilityException(this.zaay);
        this.zadc.setException((Exception)availabilityException);
        return;
      } 
      this.zadc.setResult(this.zadb);
    } 
  }
  
  public final Set<zai<?>> zap() {
    return this.zaay.keySet();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */