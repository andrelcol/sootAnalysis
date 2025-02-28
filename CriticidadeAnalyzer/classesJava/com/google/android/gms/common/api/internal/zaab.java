package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class zaab {
  private final Map<BasePendingResult<?>, Boolean> zafk = Collections.synchronizedMap(new WeakHashMap<BasePendingResult<?>, Boolean>());
  
  private final Map<TaskCompletionSource<?>, Boolean> zafl = Collections.synchronizedMap(new WeakHashMap<TaskCompletionSource<?>, Boolean>());
  
  private final void zaa(boolean paramBoolean, Status paramStatus) {
    synchronized (this.zafk) {
      Map<TaskCompletionSource<?>, Boolean> map;
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      this((Map)this.zafk);
      synchronized (this.zafl) {
        null = new HashMap<BasePendingResult<?>, Boolean>();
        super((Map)this.zafl);
        for (Map.Entry<Object, Object> entry1 : hashMap.entrySet()) {
          if (paramBoolean || ((Boolean)entry1.getValue()).booleanValue())
            ((BasePendingResult)entry1.getKey()).zab(paramStatus); 
        } 
        for (Map.Entry<BasePendingResult<?>, Boolean> entry : null.entrySet()) {
          if (paramBoolean || ((Boolean)entry.getValue()).booleanValue())
            ((TaskCompletionSource)entry.getKey()).trySetException((Exception)new ApiException(paramStatus)); 
        } 
        return;
      } 
    } 
  }
  
  final void zaa(BasePendingResult<? extends Result> paramBasePendingResult, boolean paramBoolean) {
    this.zafk.put(paramBasePendingResult, Boolean.valueOf(paramBoolean));
    paramBasePendingResult.addStatusListener(new zaac(this, paramBasePendingResult));
  }
  
  final boolean zaag() {
    return (!this.zafk.isEmpty() || !this.zafl.isEmpty());
  }
  
  public final void zaah() {
    zaa(false, GoogleApiManager.zahx);
  }
  
  public final void zaai() {
    zaa(true, zacp.zakx);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */