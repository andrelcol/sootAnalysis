package androidx.loader.app;

import android.os.Bundle;
import androidx.collection.SparseArrayCompat;
import androidx.core.util.DebugUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

class LoaderManagerImpl extends LoaderManager {
  static boolean DEBUG = false;
  
  private final LifecycleOwner mLifecycleOwner;
  
  private final LoaderViewModel mLoaderViewModel;
  
  LoaderManagerImpl(LifecycleOwner paramLifecycleOwner, ViewModelStore paramViewModelStore) {
    this.mLifecycleOwner = paramLifecycleOwner;
    this.mLoaderViewModel = LoaderViewModel.getInstance(paramViewModelStore);
  }
  
  @Deprecated
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    this.mLoaderViewModel.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public void markForRedelivery() {
    this.mLoaderViewModel.markForRedelivery();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("LoaderManager{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" in ");
    DebugUtils.buildShortClassTag(this.mLifecycleOwner, stringBuilder);
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
  
  public static class LoaderInfo<D> extends MutableLiveData<D> implements Loader.OnLoadCompleteListener<D> {
    private final Bundle mArgs;
    
    private final int mId;
    
    private LifecycleOwner mLifecycleOwner;
    
    private final Loader<D> mLoader;
    
    private LoaderManagerImpl.LoaderObserver<D> mObserver;
    
    private Loader<D> mPriorLoader;
    
    Loader<D> destroy(boolean param1Boolean) {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Destroying: ");
        stringBuilder.append(this);
        stringBuilder.toString();
      } 
      this.mLoader.cancelLoad();
      throw null;
    }
    
    public void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mId=");
      param1PrintWriter.print(this.mId);
      param1PrintWriter.print(" mArgs=");
      param1PrintWriter.println(this.mArgs);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mLoader=");
      param1PrintWriter.println(this.mLoader);
      Loader<D> loader = this.mLoader;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("  ");
      loader.dump(stringBuilder.toString(), param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      throw null;
    }
    
    void markForRedelivery() {
      LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
      LoaderManagerImpl.LoaderObserver<D> loaderObserver = this.mObserver;
      if (lifecycleOwner != null && loaderObserver != null) {
        super.removeObserver(loaderObserver);
        observe(lifecycleOwner, loaderObserver);
      } 
    }
    
    protected void onActive() {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Starting: ");
        stringBuilder.append(this);
        stringBuilder.toString();
      } 
      this.mLoader.startLoading();
      throw null;
    }
    
    protected void onInactive() {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Stopping: ");
        stringBuilder.append(this);
        stringBuilder.toString();
      } 
      this.mLoader.stopLoading();
      throw null;
    }
    
    public void removeObserver(Observer<? super D> param1Observer) {
      super.removeObserver(param1Observer);
      this.mLifecycleOwner = null;
      this.mObserver = null;
    }
    
    public void setValue(D param1D) {
      super.setValue(param1D);
      Loader<D> loader = this.mPriorLoader;
      if (loader == null)
        return; 
      loader.reset();
      throw null;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(64);
      stringBuilder.append("LoaderInfo{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" #");
      stringBuilder.append(this.mId);
      stringBuilder.append(" : ");
      DebugUtils.buildShortClassTag(this.mLoader, stringBuilder);
      stringBuilder.append("}}");
      return stringBuilder.toString();
    }
  }
  
  static class LoaderObserver<D> implements Observer<D> {}
  
  static class LoaderViewModel extends ViewModel {
    private static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory() {
        public <T extends ViewModel> T create(Class<T> param2Class) {
          return (T)new LoaderManagerImpl.LoaderViewModel();
        }
      };
    
    private SparseArrayCompat<LoaderManagerImpl.LoaderInfo> mLoaders = new SparseArrayCompat();
    
    static LoaderViewModel getInstance(ViewModelStore param1ViewModelStore) {
      return (LoaderViewModel)(new ViewModelProvider(param1ViewModelStore, FACTORY)).get(LoaderViewModel.class);
    }
    
    public void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      if (this.mLoaders.size() > 0) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.println("Loaders:");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(param1String);
        stringBuilder.append("    ");
        String str = stringBuilder.toString();
        if (this.mLoaders.size() > 0) {
          LoaderManagerImpl.LoaderInfo loaderInfo = (LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(0);
          param1PrintWriter.print(param1String);
          param1PrintWriter.print("  #");
          param1PrintWriter.print(this.mLoaders.keyAt(0));
          param1PrintWriter.print(": ");
          param1PrintWriter.println(loaderInfo.toString());
          loaderInfo.dump(str, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
          throw null;
        } 
      } 
    }
    
    void markForRedelivery() {
      int i = this.mLoaders.size();
      for (byte b = 0; b < i; b++)
        ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(b)).markForRedelivery(); 
    }
    
    protected void onCleared() {
      super.onCleared();
      if (this.mLoaders.size() <= 0) {
        this.mLoaders.clear();
        return;
      } 
      ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(0)).destroy(true);
      throw null;
    }
  }
  
  static final class null implements ViewModelProvider.Factory {
    public <T extends ViewModel> T create(Class<T> param1Class) {
      return (T)new LoaderManagerImpl.LoaderViewModel();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/loader/app/LoaderManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */