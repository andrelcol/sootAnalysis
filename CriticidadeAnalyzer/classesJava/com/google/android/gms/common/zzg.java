package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzg extends zze {
  private static final WeakReference<byte[]> zzw = (WeakReference)new WeakReference<byte>(null);
  
  private WeakReference<byte[]> zzv = zzw;
  
  zzg(byte[] paramArrayOfbyte) {
    super(paramArrayOfbyte);
  }
  
  final byte[] getBytes() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzv : Ljava/lang/ref/WeakReference;
    //   6: invokevirtual get : ()Ljava/lang/Object;
    //   9: checkcast [B
    //   12: astore_2
    //   13: aload_2
    //   14: astore_1
    //   15: aload_2
    //   16: ifnonnull -> 38
    //   19: aload_0
    //   20: invokevirtual zzd : ()[B
    //   23: astore_1
    //   24: new java/lang/ref/WeakReference
    //   27: astore_2
    //   28: aload_2
    //   29: aload_1
    //   30: invokespecial <init> : (Ljava/lang/Object;)V
    //   33: aload_0
    //   34: aload_2
    //   35: putfield zzv : Ljava/lang/ref/WeakReference;
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: areturn
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	42	finally
    //   19	38	42	finally
    //   38	40	42	finally
    //   43	45	42	finally
  }
  
  protected abstract byte[] zzd();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */