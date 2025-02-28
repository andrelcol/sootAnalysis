package com.google.android.gms.common.api.internal;

abstract class zaau implements Runnable {
  private final zaak zagj;
  
  private zaau(zaak paramzaak) {}
  
  public void run() {
    Exception exception;
    zaak.zac(this.zagj).lock();
    try {
      boolean bool = Thread.interrupted();
      if (bool) {
        zaak.zac(this.zagj).unlock();
        return;
      } 
      zaan();
      zaak.zac(this.zagj).unlock();
      return;
    } catch (RuntimeException null) {
      zaak.zad(this.zagj).zab((RuntimeException)exception);
      zaak.zac(this.zagj).unlock();
      return;
    } finally {}
    zaak.zac(this.zagj).unlock();
    throw exception;
  }
  
  protected abstract void zaan();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaau.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */