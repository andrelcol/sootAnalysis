package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.base.zap;

final class zabg extends zap {
  private final zabe zahv;
  
  zabg(zabe paramzabe, Looper paramLooper) {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage) {
    StringBuilder stringBuilder;
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        stringBuilder = new StringBuilder(31);
        stringBuilder.append("Unknown message id: ");
        stringBuilder.append(i);
        stringBuilder.toString();
        return;
      } 
      throw (RuntimeException)stringBuilder.obj;
    } 
    ((zabf)((Message)stringBuilder).obj).zac(this.zahv);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */