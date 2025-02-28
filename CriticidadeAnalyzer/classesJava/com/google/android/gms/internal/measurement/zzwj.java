package com.google.android.gms.internal.measurement;

final class zzwj implements zzvt {
  private final int flags;
  
  private final String info;
  
  private final Object[] zzcat;
  
  private final zzvv zzcaw;
  
  zzwj(zzvv paramzzvv, String paramString, Object[] paramArrayOfObject) {
    this.zzcaw = paramzzvv;
    this.info = paramString;
    this.zzcat = paramArrayOfObject;
    char c = paramString.charAt(0);
    if (c < '?') {
      this.flags = c;
      return;
    } 
    int i = c & 0x1FFF;
    byte b = 13;
    c = '\001';
    while (true) {
      char c1 = paramString.charAt(c);
      if (c1 >= '?') {
        i |= (c1 & 0x1FFF) << b;
        b += 13;
        c++;
        continue;
      } 
      this.flags = i | c1 << b;
      return;
    } 
  }
  
  public final int zzxm() {
    return ((this.flags & 0x1) == 1) ? zzuo.zze.zzbyu : zzuo.zze.zzbyv;
  }
  
  public final boolean zzxn() {
    return ((this.flags & 0x2) == 2);
  }
  
  public final zzvv zzxo() {
    return this.zzcaw;
  }
  
  final String zzxv() {
    return this.info;
  }
  
  final Object[] zzxw() {
    return this.zzcat;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */