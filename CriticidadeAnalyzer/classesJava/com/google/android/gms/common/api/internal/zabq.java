package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class zabq extends BroadcastReceiver {
  private Context mContext;
  
  private final zabr zaji;
  
  public zabq(zabr paramzabr) {
    this.zaji = paramzabr;
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    Uri uri = paramIntent.getData();
    if (uri != null) {
      String str = uri.getSchemeSpecificPart();
    } else {
      uri = null;
    } 
    if ("com.google.android.gms".equals(uri)) {
      this.zaji.zas();
      unregister();
    } 
  }
  
  public final void unregister() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mContext : Landroid/content/Context;
    //   6: ifnull -> 17
    //   9: aload_0
    //   10: getfield mContext : Landroid/content/Context;
    //   13: aload_0
    //   14: invokevirtual unregisterReceiver : (Landroid/content/BroadcastReceiver;)V
    //   17: aload_0
    //   18: aconst_null
    //   19: putfield mContext : Landroid/content/Context;
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	25	finally
    //   17	22	25	finally
  }
  
  public final void zac(Context paramContext) {
    this.mContext = paramContext;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */