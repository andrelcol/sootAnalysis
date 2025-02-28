package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import java.security.KeyPair;

final class zzz {
  private final KeyPair zzbr;
  
  private final long zzbs;
  
  zzz(KeyPair paramKeyPair, long paramLong) {
    this.zzbr = paramKeyPair;
    this.zzbs = paramLong;
  }
  
  private final String zzv() {
    return Base64.encodeToString(this.zzbr.getPublic().getEncoded(), 11);
  }
  
  private final String zzw() {
    return Base64.encodeToString(this.zzbr.getPrivate().getEncoded(), 11);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof zzz))
      return false; 
    paramObject = paramObject;
    return (this.zzbs == ((zzz)paramObject).zzbs && this.zzbr.getPublic().equals(((zzz)paramObject).zzbr.getPublic()) && this.zzbr.getPrivate().equals(((zzz)paramObject).zzbr.getPrivate()));
  }
  
  final KeyPair getKeyPair() {
    return this.zzbr;
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { this.zzbr.getPublic(), this.zzbr.getPrivate(), Long.valueOf(this.zzbs) });
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */