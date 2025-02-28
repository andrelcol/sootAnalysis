package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zztq {
  int zzbua;
  
  int zzbub = 100;
  
  zztt zzbud;
  
  private zztq() {}
  
  static zztq zza(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    zzts zzts = new zzts(paramArrayOfbyte, paramInt1, paramInt2, false, null);
    try {
      zzts.zzas(paramInt2);
      return zzts;
    } catch (zzuv zzuv) {
      throw new IllegalArgumentException(zzuv);
    } 
  }
  
  public static zztq zzd(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return zza(paramArrayOfbyte, paramInt1, paramInt2, false);
  }
  
  public abstract double readDouble() throws IOException;
  
  public abstract float readFloat() throws IOException;
  
  public abstract String readString() throws IOException;
  
  public abstract <T extends zzvv> T zza(zzwf<T> paramzzwf, zzub paramzzub) throws IOException;
  
  public abstract void zzap(int paramInt) throws zzuv;
  
  public abstract boolean zzaq(int paramInt) throws IOException;
  
  public final int zzar(int paramInt) {
    if (paramInt >= 0) {
      int i = this.zzbub;
      this.zzbub = paramInt;
      return i;
    } 
    StringBuilder stringBuilder = new StringBuilder(47);
    stringBuilder.append("Recursion limit cannot be negative: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public abstract int zzas(int paramInt) throws zzuv;
  
  public abstract void zzat(int paramInt);
  
  public abstract void zzau(int paramInt) throws IOException;
  
  public abstract int zzuj() throws IOException;
  
  public abstract long zzuk() throws IOException;
  
  public abstract long zzul() throws IOException;
  
  public abstract int zzum() throws IOException;
  
  public abstract long zzun() throws IOException;
  
  public abstract int zzuo() throws IOException;
  
  public abstract boolean zzup() throws IOException;
  
  public abstract String zzuq() throws IOException;
  
  public abstract zzte zzur() throws IOException;
  
  public abstract int zzus() throws IOException;
  
  public abstract int zzut() throws IOException;
  
  public abstract int zzuu() throws IOException;
  
  public abstract long zzuv() throws IOException;
  
  public abstract int zzuw() throws IOException;
  
  public abstract long zzux() throws IOException;
  
  public abstract boolean zzuz() throws IOException;
  
  public abstract int zzva();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zztq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */