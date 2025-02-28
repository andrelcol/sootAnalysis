package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import androidx.core.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class FragmentHostCallback<E> extends FragmentContainer {
  private final Activity mActivity;
  
  private final Context mContext;
  
  final FragmentManagerImpl mFragmentManager = new FragmentManagerImpl();
  
  private final Handler mHandler;
  
  FragmentHostCallback(Activity paramActivity, Context paramContext, Handler paramHandler, int paramInt) {
    this.mActivity = paramActivity;
    Preconditions.checkNotNull(paramContext, "context == null");
    this.mContext = paramContext;
    Preconditions.checkNotNull(paramHandler, "handler == null");
    this.mHandler = paramHandler;
  }
  
  FragmentHostCallback(FragmentActivity paramFragmentActivity) {
    this((Activity)paramFragmentActivity, (Context)paramFragmentActivity, paramFragmentActivity.mHandler, 0);
  }
  
  Activity getActivity() {
    return this.mActivity;
  }
  
  Context getContext() {
    return this.mContext;
  }
  
  FragmentManagerImpl getFragmentManagerImpl() {
    return this.mFragmentManager;
  }
  
  Handler getHandler() {
    return this.mHandler;
  }
  
  abstract void onAttachFragment(Fragment paramFragment);
  
  public abstract void onDump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  public abstract LayoutInflater onGetLayoutInflater();
  
  public abstract int onGetWindowAnimations();
  
  public abstract boolean onHasWindowAnimations();
  
  public abstract boolean onShouldSaveFragmentState(Fragment paramFragment);
  
  public abstract void onStartActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle);
  
  public abstract void onSupportInvalidateOptionsMenu();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/fragment/app/FragmentHostCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */