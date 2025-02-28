package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzsx<MessageType extends zzsx<MessageType, BuilderType>, BuilderType extends zzsy<MessageType, BuilderType>> implements zzvv {
  private static boolean zzbtl = false;
  
  protected int zzbtk = 0;
  
  void zzai(int paramInt) {
    throw new UnsupportedOperationException();
  }
  
  public final zzte zztw() {
    try {
      zztm zztm = zzte.zzao(zzvx());
      zzb(zztm.zzui());
      return zztm.zzuh();
    } catch (IOException iOException) {
      String str = getClass().getName();
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 62 + "ByteString".length());
      stringBuilder.append("Serializing ");
      stringBuilder.append(str);
      stringBuilder.append(" to a ");
      stringBuilder.append("ByteString");
      stringBuilder.append(" threw an IOException (should never happen).");
      throw new RuntimeException(stringBuilder.toString(), iOException);
    } 
  }
  
  int zztx() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzsx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */