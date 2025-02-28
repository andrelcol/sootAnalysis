package androidx.core.widget;

import android.os.Build;

public interface AutoSizeableTextView {
  public static final boolean PLATFORM_SUPPORTS_AUTOSIZE;
  
  static {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 27) {
      bool = true;
    } else {
      bool = false;
    } 
    PLATFORM_SUPPORTS_AUTOSIZE = bool;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/widget/AutoSizeableTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */