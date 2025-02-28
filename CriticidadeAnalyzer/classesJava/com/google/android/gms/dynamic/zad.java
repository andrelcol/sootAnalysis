package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

final class zad implements DeferredLifecycleHelper.zaa {
  private final ViewGroup val$container;
  
  private final DeferredLifecycleHelper zarj;
  
  private final Bundle zarl;
  
  private final FrameLayout zarm;
  
  private final LayoutInflater zarn;
  
  zad(DeferredLifecycleHelper paramDeferredLifecycleHelper, FrameLayout paramFrameLayout, LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup) {}
  
  public final int getState() {
    return 2;
  }
  
  public final void zaa(LifecycleDelegate paramLifecycleDelegate) {
    this.zarm.removeAllViews();
    this.zarm.addView(DeferredLifecycleHelper.zab(this.zarj).onCreateView(this.zarn, container, this.zarl));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamic/zad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */