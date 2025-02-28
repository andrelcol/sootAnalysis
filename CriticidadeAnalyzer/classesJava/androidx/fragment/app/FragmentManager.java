package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public abstract class FragmentManager {
  public abstract FragmentTransaction beginTransaction();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  public abstract Fragment findFragmentById(int paramInt);
  
  public abstract Fragment findFragmentByTag(String paramString);
  
  public abstract Fragment getFragment(Bundle paramBundle, String paramString);
  
  public abstract List<Fragment> getFragments();
  
  public abstract boolean isStateSaved();
  
  public abstract void popBackStack(int paramInt1, int paramInt2);
  
  public abstract boolean popBackStackImmediate();
  
  public abstract void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment);
  
  public abstract Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment);
  
  public static interface BackStackEntry {}
  
  public static abstract class FragmentLifecycleCallbacks {
    public abstract void onFragmentActivityCreated(FragmentManager param1FragmentManager, Fragment param1Fragment, Bundle param1Bundle);
    
    public abstract void onFragmentAttached(FragmentManager param1FragmentManager, Fragment param1Fragment, Context param1Context);
    
    public abstract void onFragmentCreated(FragmentManager param1FragmentManager, Fragment param1Fragment, Bundle param1Bundle);
    
    public abstract void onFragmentDestroyed(FragmentManager param1FragmentManager, Fragment param1Fragment);
    
    public abstract void onFragmentDetached(FragmentManager param1FragmentManager, Fragment param1Fragment);
    
    public abstract void onFragmentPaused(FragmentManager param1FragmentManager, Fragment param1Fragment);
    
    public abstract void onFragmentPreAttached(FragmentManager param1FragmentManager, Fragment param1Fragment, Context param1Context);
    
    public abstract void onFragmentPreCreated(FragmentManager param1FragmentManager, Fragment param1Fragment, Bundle param1Bundle);
    
    public abstract void onFragmentResumed(FragmentManager param1FragmentManager, Fragment param1Fragment);
    
    public abstract void onFragmentSaveInstanceState(FragmentManager param1FragmentManager, Fragment param1Fragment, Bundle param1Bundle);
    
    public abstract void onFragmentStarted(FragmentManager param1FragmentManager, Fragment param1Fragment);
    
    public abstract void onFragmentStopped(FragmentManager param1FragmentManager, Fragment param1Fragment);
    
    public abstract void onFragmentViewCreated(FragmentManager param1FragmentManager, Fragment param1Fragment, View param1View, Bundle param1Bundle);
    
    public abstract void onFragmentViewDestroyed(FragmentManager param1FragmentManager, Fragment param1Fragment);
  }
  
  public static interface OnBackStackChangedListener {
    void onBackStackChanged();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/fragment/app/FragmentManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */