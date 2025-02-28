package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import com.google.android.gms.internal.base.zam;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

public final class zabh {
  private static final ExecutorService zahw = zam.zacv().zaa(2, (ThreadFactory)new NumberedThreadFactory("GAC_Executor"), 9);
  
  public static ExecutorService zabb() {
    return zahw;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */