package androidx.lifecycle;

public class ViewModelProvider {
  private final Factory mFactory;
  
  private final ViewModelStore mViewModelStore;
  
  public ViewModelProvider(ViewModelStore paramViewModelStore, Factory paramFactory) {
    this.mFactory = paramFactory;
    this.mViewModelStore = paramViewModelStore;
  }
  
  public <T extends ViewModel> T get(Class<T> paramClass) {
    String str = paramClass.getCanonicalName();
    if (str != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("androidx.lifecycle.ViewModelProvider.DefaultKey:");
      stringBuilder.append(str);
      return get(stringBuilder.toString(), paramClass);
    } 
    throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
  }
  
  public <T extends ViewModel> T get(String paramString, Class<T> paramClass) {
    ViewModel viewModel = this.mViewModelStore.get(paramString);
    if (paramClass.isInstance(viewModel))
      return (T)viewModel; 
    paramClass = this.mFactory.create((Class)paramClass);
    this.mViewModelStore.put(paramString, (ViewModel)paramClass);
    return (T)paramClass;
  }
  
  public static interface Factory {
    <T extends ViewModel> T create(Class<T> param1Class);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/lifecycle/ViewModelProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */