package com.google.android.gms.common;

import java.util.concurrent.Callable;

final class zzo extends zzm {
  private final Callable<String> zzaf;
  
  private zzo(Callable<String> paramCallable) {
    super(false, null, null);
    this.zzaf = paramCallable;
  }
  
  final String getErrorMessage() {
    try {
      return this.zzaf.call();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */