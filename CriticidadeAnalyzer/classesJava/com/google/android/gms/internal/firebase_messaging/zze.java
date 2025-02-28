package com.google.android.gms.internal.firebase_messaging;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class zze {
  private final ConcurrentHashMap<zzf, List<Throwable>> zze = new ConcurrentHashMap<zzf, List<Throwable>>(16, 0.75F, 10);
  
  private final ReferenceQueue<Throwable> zzf = new ReferenceQueue<Throwable>();
  
  public final List<Throwable> zza(Throwable paramThrowable, boolean paramBoolean) {
    Reference<? extends Throwable> reference;
    for (reference = this.zzf.poll(); reference != null; reference = this.zzf.poll())
      this.zze.remove(reference); 
    reference = new zzf(paramThrowable, null);
    List<Throwable> list2 = this.zze.get(reference);
    if (list2 != null)
      return list2; 
    list2 = new Vector<Throwable>(2);
    List<Throwable> list1 = this.zze.putIfAbsent(new zzf(paramThrowable, this.zzf), list2);
    return (list1 == null) ? list2 : list1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/firebase_messaging/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */