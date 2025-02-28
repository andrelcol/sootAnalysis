package com.google.android.gms.common.api.internal;

import androidx.annotation.Keep;

public class LifecycleCallback {
  @Keep
  private static LifecycleFragment getChimeraLifecycleFragmentImpl(LifecycleActivity paramLifecycleActivity) {
    throw new IllegalStateException("Method not available in SDK.");
  }
  
  protected static LifecycleFragment getFragment(LifecycleActivity paramLifecycleActivity) {
    paramLifecycleActivity.isSupport();
    throw null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/LifecycleCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */