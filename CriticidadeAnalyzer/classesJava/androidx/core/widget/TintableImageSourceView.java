package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

public interface TintableImageSourceView {
  ColorStateList getSupportImageTintList();
  
  PorterDuff.Mode getSupportImageTintMode();
  
  void setSupportImageTintList(ColorStateList paramColorStateList);
  
  void setSupportImageTintMode(PorterDuff.Mode paramMode);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/widget/TintableImageSourceView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */