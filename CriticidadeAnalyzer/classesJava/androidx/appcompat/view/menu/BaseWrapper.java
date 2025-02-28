package androidx.appcompat.view.menu;

class BaseWrapper<T> {
  final T mWrappedObject;
  
  BaseWrapper(T paramT) {
    if (paramT != null) {
      this.mWrappedObject = paramT;
      return;
    } 
    throw new IllegalArgumentException("Wrapped Object can not be null.");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/view/menu/BaseWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */