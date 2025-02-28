package com.google.firebase.components;

import com.google.firebase.inject.Provider;

final class zzj<T> implements Provider<T> {
  private static final Object zza = new Object();
  
  private volatile Object zzb = zza;
  
  private volatile Provider<T> zzc;
  
  zzj(ComponentFactory<T> paramComponentFactory, ComponentContainer paramComponentContainer) {
    this.zzc = zzk.zza(paramComponentFactory, paramComponentContainer);
  }
  
  public final T get() {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzb : Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: astore_1
    //   7: aload_2
    //   8: getstatic com/google/firebase/components/zzj.zza : Ljava/lang/Object;
    //   11: if_acmpne -> 60
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: getfield zzb : Ljava/lang/Object;
    //   20: astore_2
    //   21: aload_2
    //   22: astore_1
    //   23: aload_2
    //   24: getstatic com/google/firebase/components/zzj.zza : Ljava/lang/Object;
    //   27: if_acmpne -> 50
    //   30: aload_0
    //   31: getfield zzc : Lcom/google/firebase/inject/Provider;
    //   34: invokeinterface get : ()Ljava/lang/Object;
    //   39: astore_1
    //   40: aload_0
    //   41: aload_1
    //   42: putfield zzb : Ljava/lang/Object;
    //   45: aload_0
    //   46: aconst_null
    //   47: putfield zzc : Lcom/google/firebase/inject/Provider;
    //   50: aload_0
    //   51: monitorexit
    //   52: goto -> 60
    //   55: astore_1
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_1
    //   59: athrow
    //   60: aload_1
    //   61: areturn
    // Exception table:
    //   from	to	target	type
    //   16	21	55	finally
    //   23	50	55	finally
    //   50	52	55	finally
    //   56	58	55	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */