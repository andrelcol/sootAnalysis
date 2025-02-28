package com.google.android.gms.internal.measurement;

public class zzvc {
  private volatile zzvv zzbzx;
  
  private volatile zzte zzbzy;
  
  static {
    zzub.zzvr();
  }
  
  private final zzvv zzh(zzvv paramzzvv) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzbzx : Lcom/google/android/gms/internal/measurement/zzvv;
    //   4: ifnonnull -> 59
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield zzbzx : Lcom/google/android/gms/internal/measurement/zzvv;
    //   13: ifnull -> 21
    //   16: aload_0
    //   17: monitorexit
    //   18: goto -> 59
    //   21: aload_0
    //   22: aload_1
    //   23: putfield zzbzx : Lcom/google/android/gms/internal/measurement/zzvv;
    //   26: aload_0
    //   27: getstatic com/google/android/gms/internal/measurement/zzte.zzbts : Lcom/google/android/gms/internal/measurement/zzte;
    //   30: putfield zzbzy : Lcom/google/android/gms/internal/measurement/zzte;
    //   33: goto -> 49
    //   36: astore_2
    //   37: aload_0
    //   38: aload_1
    //   39: putfield zzbzx : Lcom/google/android/gms/internal/measurement/zzvv;
    //   42: aload_0
    //   43: getstatic com/google/android/gms/internal/measurement/zzte.zzbts : Lcom/google/android/gms/internal/measurement/zzte;
    //   46: putfield zzbzy : Lcom/google/android/gms/internal/measurement/zzte;
    //   49: aload_0
    //   50: monitorexit
    //   51: goto -> 59
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    //   59: aload_0
    //   60: getfield zzbzx : Lcom/google/android/gms/internal/measurement/zzvv;
    //   63: areturn
    // Exception table:
    //   from	to	target	type
    //   9	18	54	finally
    //   21	33	36	com/google/android/gms/internal/measurement/zzuv
    //   21	33	54	finally
    //   37	49	54	finally
    //   49	51	54	finally
    //   55	57	54	finally
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzvc))
      return false; 
    zzvc zzvc1 = (zzvc)paramObject;
    zzvv zzvv1 = this.zzbzx;
    paramObject = zzvc1.zzbzx;
    return (zzvv1 == null && paramObject == null) ? zztw().equals(zzvc1.zztw()) : ((zzvv1 != null && paramObject != null) ? zzvv1.equals(paramObject) : ((zzvv1 != null) ? zzvv1.equals(zzvc1.zzh(zzvv1.zzwj())) : zzh(paramObject.zzwj()).equals(paramObject)));
  }
  
  public int hashCode() {
    return 1;
  }
  
  public final zzvv zzi(zzvv paramzzvv) {
    zzvv zzvv1 = this.zzbzx;
    this.zzbzy = null;
    this.zzbzx = paramzzvv;
    return zzvv1;
  }
  
  public final zzte zztw() {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzbzy : Lcom/google/android/gms/internal/measurement/zzte;
    //   4: ifnull -> 12
    //   7: aload_0
    //   8: getfield zzbzy : Lcom/google/android/gms/internal/measurement/zzte;
    //   11: areturn
    //   12: aload_0
    //   13: monitorenter
    //   14: aload_0
    //   15: getfield zzbzy : Lcom/google/android/gms/internal/measurement/zzte;
    //   18: ifnull -> 30
    //   21: aload_0
    //   22: getfield zzbzy : Lcom/google/android/gms/internal/measurement/zzte;
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: areturn
    //   30: aload_0
    //   31: getfield zzbzx : Lcom/google/android/gms/internal/measurement/zzvv;
    //   34: ifnonnull -> 47
    //   37: aload_0
    //   38: getstatic com/google/android/gms/internal/measurement/zzte.zzbts : Lcom/google/android/gms/internal/measurement/zzte;
    //   41: putfield zzbzy : Lcom/google/android/gms/internal/measurement/zzte;
    //   44: goto -> 60
    //   47: aload_0
    //   48: aload_0
    //   49: getfield zzbzx : Lcom/google/android/gms/internal/measurement/zzvv;
    //   52: invokeinterface zztw : ()Lcom/google/android/gms/internal/measurement/zzte;
    //   57: putfield zzbzy : Lcom/google/android/gms/internal/measurement/zzte;
    //   60: aload_0
    //   61: getfield zzbzy : Lcom/google/android/gms/internal/measurement/zzte;
    //   64: astore_1
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: areturn
    //   69: astore_1
    //   70: aload_0
    //   71: monitorexit
    //   72: aload_1
    //   73: athrow
    // Exception table:
    //   from	to	target	type
    //   14	28	69	finally
    //   30	44	69	finally
    //   47	60	69	finally
    //   60	67	69	finally
    //   70	72	69	finally
  }
  
  public final int zzvx() {
    return (this.zzbzy != null) ? this.zzbzy.size() : ((this.zzbzx != null) ? this.zzbzx.zzvx() : 0);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */