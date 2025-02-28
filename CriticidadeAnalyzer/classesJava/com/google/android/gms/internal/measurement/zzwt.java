package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzwt implements Iterator<Object> {
  public final boolean hasNext() {
    return false;
  }
  
  public final Object next() {
    throw new NoSuchElementException();
  }
  
  public final void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */