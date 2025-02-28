package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzf extends WeakReference<Throwable> {
  private final int zzg;
  
  public zzf(Throwable paramThrowable, ReferenceQueue<Throwable> paramReferenceQueue) {
    super(paramThrowable, paramReferenceQueue);
    if (paramThrowable != null) {
      this.zzg = System.identityHashCode(paramThrowable);
      return;
    } 
    throw new NullPointerException("The referent cannot be null");
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject != null && paramObject.getClass() == zzf.class) {
      if (this == paramObject)
        return true; 
      paramObject = paramObject;
      if (this.zzg == ((zzf)paramObject).zzg && get() == paramObject.get())
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return this.zzg;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/firebase_messaging/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */