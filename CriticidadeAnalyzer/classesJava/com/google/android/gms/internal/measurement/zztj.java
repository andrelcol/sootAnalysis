package com.google.android.gms.internal.measurement;

final class zztj extends zzto {
  private final int zzbtw;
  
  private final int zzbtx;
  
  zztj(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    super(paramArrayOfbyte);
    zzte.zzb(paramInt1, paramInt1 + paramInt2, paramArrayOfbyte.length);
    this.zzbtw = paramInt1;
    this.zzbtx = paramInt2;
  }
  
  public final int size() {
    return this.zzbtx;
  }
  
  public final byte zzam(int paramInt) {
    int i = size();
    if ((i - paramInt + 1 | paramInt) < 0) {
      if (paramInt < 0) {
        StringBuilder stringBuilder1 = new StringBuilder(22);
        stringBuilder1.append("Index < 0: ");
        stringBuilder1.append(paramInt);
        throw new ArrayIndexOutOfBoundsException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder(40);
      stringBuilder.append("Index > length: ");
      stringBuilder.append(paramInt);
      stringBuilder.append(", ");
      stringBuilder.append(i);
      throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
    } 
    return this.zzbtz[this.zzbtw + paramInt];
  }
  
  final byte zzan(int paramInt) {
    return this.zzbtz[this.zzbtw + paramInt];
  }
  
  protected final int zzug() {
    return this.zzbtw;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zztj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */