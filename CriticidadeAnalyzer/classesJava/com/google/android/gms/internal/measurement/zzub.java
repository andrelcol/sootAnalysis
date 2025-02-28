package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;

public class zzub {
  private static volatile zzub zzbve;
  
  static final zzub zzbvf = new zzub(true);
  
  private final Map<zza, zzuo.zzd<?, ?>> zzbvg = new HashMap<zza, zzuo.zzd<?, ?>>();
  
  zzub() {}
  
  private zzub(boolean paramBoolean) {}
  
  static zzub zzvp() {
    return zzum.zzd(zzub.class);
  }
  
  private static Class<?> zzvq() {
    try {
      return Class.forName("com.google.protobuf.Extension");
    } catch (ClassNotFoundException classNotFoundException) {
      return null;
    } 
  }
  
  public static zzub zzvr() {
    return zzua.zzvo();
  }
  
  public static zzub zzvs() {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/measurement/zzub.zzbve : Lcom/google/android/gms/internal/measurement/zzub;
    //   3: astore_1
    //   4: aload_1
    //   5: astore_0
    //   6: aload_1
    //   7: ifnonnull -> 43
    //   10: ldc com/google/android/gms/internal/measurement/zzub
    //   12: monitorenter
    //   13: getstatic com/google/android/gms/internal/measurement/zzub.zzbve : Lcom/google/android/gms/internal/measurement/zzub;
    //   16: astore_1
    //   17: aload_1
    //   18: astore_0
    //   19: aload_1
    //   20: ifnonnull -> 31
    //   23: invokestatic zzvp : ()Lcom/google/android/gms/internal/measurement/zzub;
    //   26: astore_0
    //   27: aload_0
    //   28: putstatic com/google/android/gms/internal/measurement/zzub.zzbve : Lcom/google/android/gms/internal/measurement/zzub;
    //   31: ldc com/google/android/gms/internal/measurement/zzub
    //   33: monitorexit
    //   34: goto -> 43
    //   37: astore_0
    //   38: ldc com/google/android/gms/internal/measurement/zzub
    //   40: monitorexit
    //   41: aload_0
    //   42: athrow
    //   43: aload_0
    //   44: areturn
    // Exception table:
    //   from	to	target	type
    //   13	17	37	finally
    //   23	31	37	finally
    //   31	34	37	finally
    //   38	41	37	finally
  }
  
  public final <ContainingType extends zzvv> zzuo.zzd<ContainingType, ?> zza(ContainingType paramContainingType, int paramInt) {
    return (zzuo.zzd<ContainingType, ?>)this.zzbvg.get(new zza(paramContainingType, paramInt));
  }
  
  static {
    zzvq();
  }
  
  static final class zza {
    private final int number;
    
    private final Object object;
    
    zza(Object param1Object, int param1Int) {
      this.object = param1Object;
      this.number = param1Int;
    }
    
    public final boolean equals(Object param1Object) {
      if (!(param1Object instanceof zza))
        return false; 
      param1Object = param1Object;
      return (this.object == ((zza)param1Object).object && this.number == ((zza)param1Object).number);
    }
    
    public final int hashCode() {
      return System.identityHashCode(this.object) * 65535 + this.number;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */