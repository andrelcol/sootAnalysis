package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.base.zap;

final class zabb extends zap {
  private final zaaw zahh;
  
  zabb(zaaw paramzaaw, Looper paramLooper) {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        StringBuilder stringBuilder = new StringBuilder(31);
        stringBuilder.append("Unknown message id: ");
        stringBuilder.append(i);
        stringBuilder.toString();
        return;
      } 
      zaaw.zaa(this.zahh);
      return;
    } 
    zaaw.zab(this.zahh);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */