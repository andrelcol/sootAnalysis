package androidx.core.view;

import android.os.Build;
import android.view.WindowInsets;

public class WindowInsetsCompat {
  private final Object mInsets;
  
  private WindowInsetsCompat(Object paramObject) {
    this.mInsets = paramObject;
  }
  
  static Object unwrap(WindowInsetsCompat paramWindowInsetsCompat) {
    Object object;
    if (paramWindowInsetsCompat == null) {
      paramWindowInsetsCompat = null;
    } else {
      object = paramWindowInsetsCompat.mInsets;
    } 
    return object;
  }
  
  static WindowInsetsCompat wrap(Object paramObject) {
    if (paramObject == null) {
      paramObject = null;
    } else {
      paramObject = new WindowInsetsCompat(paramObject);
    } 
    return (WindowInsetsCompat)paramObject;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || WindowInsetsCompat.class != paramObject.getClass())
      return false; 
    WindowInsetsCompat windowInsetsCompat = (WindowInsetsCompat)paramObject;
    paramObject = this.mInsets;
    Object object = windowInsetsCompat.mInsets;
    if (paramObject == null) {
      if (object != null)
        bool = false; 
    } else {
      bool = paramObject.equals(object);
    } 
    return bool;
  }
  
  public int getSystemWindowInsetBottom() {
    return (Build.VERSION.SDK_INT >= 20) ? ((WindowInsets)this.mInsets).getSystemWindowInsetBottom() : 0;
  }
  
  public int getSystemWindowInsetLeft() {
    return (Build.VERSION.SDK_INT >= 20) ? ((WindowInsets)this.mInsets).getSystemWindowInsetLeft() : 0;
  }
  
  public int getSystemWindowInsetRight() {
    return (Build.VERSION.SDK_INT >= 20) ? ((WindowInsets)this.mInsets).getSystemWindowInsetRight() : 0;
  }
  
  public int getSystemWindowInsetTop() {
    return (Build.VERSION.SDK_INT >= 20) ? ((WindowInsets)this.mInsets).getSystemWindowInsetTop() : 0;
  }
  
  public int hashCode() {
    int i;
    Object object = this.mInsets;
    if (object == null) {
      i = 0;
    } else {
      i = object.hashCode();
    } 
    return i;
  }
  
  public boolean isConsumed() {
    return (Build.VERSION.SDK_INT >= 21) ? ((WindowInsets)this.mInsets).isConsumed() : false;
  }
  
  public WindowInsetsCompat replaceSystemWindowInsets(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return (Build.VERSION.SDK_INT >= 20) ? new WindowInsetsCompat(((WindowInsets)this.mInsets).replaceSystemWindowInsets(paramInt1, paramInt2, paramInt3, paramInt4)) : null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/view/WindowInsetsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */