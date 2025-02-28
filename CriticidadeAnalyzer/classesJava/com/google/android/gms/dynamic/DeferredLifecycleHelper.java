package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import java.util.LinkedList;

public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
  private T zarf;
  
  private Bundle zarg;
  
  private LinkedList<zaa> zarh;
  
  private final OnDelegateCreatedListener<T> zari = new zaa(this);
  
  public static void showGooglePlayUnavailableMessage(FrameLayout paramFrameLayout) {
    GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
    Context context = paramFrameLayout.getContext();
    int i = googleApiAvailability.isGooglePlayServicesAvailable(context);
    String str2 = ConnectionErrorMessages.getErrorMessage(context, i);
    String str1 = ConnectionErrorMessages.getErrorDialogButtonMessage(context, i);
    LinearLayout linearLayout = new LinearLayout(paramFrameLayout.getContext());
    linearLayout.setOrientation(1);
    linearLayout.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.addView((View)linearLayout);
    TextView textView = new TextView(paramFrameLayout.getContext());
    textView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
    textView.setText(str2);
    linearLayout.addView((View)textView);
    Intent intent = googleApiAvailability.getErrorResolutionIntent(context, i, null);
    if (intent != null) {
      Button button = new Button(context);
      button.setId(16908313);
      button.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
      button.setText(str1);
      linearLayout.addView((View)button);
      button.setOnClickListener(new zae(context, intent));
    } 
  }
  
  private final void zaa(Bundle paramBundle, zaa paramzaa) {
    T t = this.zarf;
    if (t != null) {
      paramzaa.zaa((LifecycleDelegate)t);
      return;
    } 
    if (this.zarh == null)
      this.zarh = new LinkedList<zaa>(); 
    this.zarh.add(paramzaa);
    if (paramBundle != null) {
      Bundle bundle = this.zarg;
      if (bundle == null) {
        this.zarg = (Bundle)paramBundle.clone();
      } else {
        bundle.putAll(paramBundle);
      } 
    } 
    createDelegate(this.zari);
  }
  
  private final void zal(int paramInt) {
    while (!this.zarh.isEmpty() && ((zaa)this.zarh.getLast()).getState() >= paramInt)
      this.zarh.removeLast(); 
  }
  
  protected abstract void createDelegate(OnDelegateCreatedListener<T> paramOnDelegateCreatedListener);
  
  public T getDelegate() {
    return this.zarf;
  }
  
  protected void handleGooglePlayUnavailable(FrameLayout paramFrameLayout) {
    showGooglePlayUnavailableMessage(paramFrameLayout);
  }
  
  public void onCreate(Bundle paramBundle) {
    zaa(paramBundle, new zac(this, paramBundle));
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    FrameLayout frameLayout = new FrameLayout(paramLayoutInflater.getContext());
    zaa(paramBundle, new zad(this, frameLayout, paramLayoutInflater, paramViewGroup, paramBundle));
    if (this.zarf == null)
      handleGooglePlayUnavailable(frameLayout); 
    return (View)frameLayout;
  }
  
  public void onDestroy() {
    T t = this.zarf;
    if (t != null) {
      t.onDestroy();
      return;
    } 
    zal(1);
  }
  
  public void onDestroyView() {
    T t = this.zarf;
    if (t != null) {
      t.onDestroyView();
      return;
    } 
    zal(2);
  }
  
  public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2) {
    zaa(paramBundle2, new zab(this, paramActivity, paramBundle1, paramBundle2));
  }
  
  public void onLowMemory() {
    T t = this.zarf;
    if (t != null)
      t.onLowMemory(); 
  }
  
  public void onPause() {
    T t = this.zarf;
    if (t != null) {
      t.onPause();
      return;
    } 
    zal(5);
  }
  
  public void onResume() {
    zaa((Bundle)null, new zag(this));
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    T t = this.zarf;
    if (t != null) {
      t.onSaveInstanceState(paramBundle);
      return;
    } 
    Bundle bundle = this.zarg;
    if (bundle != null)
      paramBundle.putAll(bundle); 
  }
  
  public void onStart() {
    zaa((Bundle)null, new zaf(this));
  }
  
  public void onStop() {
    T t = this.zarf;
    if (t != null) {
      t.onStop();
      return;
    } 
    zal(4);
  }
  
  private static interface zaa {
    int getState();
    
    void zaa(LifecycleDelegate param1LifecycleDelegate);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamic/DeferredLifecycleHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */