package com.google.android.gms.common.api.internal;

abstract class zabf {
  private final zabd zahu;
  
  protected zabf(zabd paramzabd) {
    this.zahu = paramzabd;
  }
  
  protected abstract void zaan();
  
  public final void zac(zabe paramzabe) {
    zabe.zaa(paramzabe).lock();
    try {
      zabd zabd1 = zabe.zab(paramzabe);
      zabd zabd2 = this.zahu;
      if (zabd1 != zabd2)
        return; 
      zaan();
      return;
    } finally {
      zabe.zaa(paramzabe).unlock();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */