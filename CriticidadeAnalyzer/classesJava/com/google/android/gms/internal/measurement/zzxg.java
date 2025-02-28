package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzxg extends AbstractList<String> implements zzve, RandomAccess {
  private final zzve zzccj;
  
  public zzxg(zzve paramzzve) {
    this.zzccj = paramzzve;
  }
  
  public final Iterator<String> iterator() {
    return new zzxi(this);
  }
  
  public final ListIterator<String> listIterator(int paramInt) {
    return new zzxh(this, paramInt);
  }
  
  public final int size() {
    return this.zzccj.size();
  }
  
  public final Object zzbp(int paramInt) {
    return this.zzccj.zzbp(paramInt);
  }
  
  public final void zzc(zzte paramzzte) {
    throw new UnsupportedOperationException();
  }
  
  public final List<?> zzxb() {
    return this.zzccj.zzxb();
  }
  
  public final zzve zzxc() {
    return this;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */