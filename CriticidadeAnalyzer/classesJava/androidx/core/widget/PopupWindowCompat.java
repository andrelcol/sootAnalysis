package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat {
  private static Field sOverlapAnchorField;
  
  private static boolean sOverlapAnchorFieldAttempted;
  
  private static Method sSetWindowLayoutTypeMethod;
  
  private static boolean sSetWindowLayoutTypeMethodAttempted;
  
  public static void setOverlapAnchor(PopupWindow paramPopupWindow, boolean paramBoolean) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      paramPopupWindow.setOverlapAnchor(paramBoolean);
    } else if (i >= 21) {
      if (!sOverlapAnchorFieldAttempted) {
        try {
          sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
          sOverlapAnchorField.setAccessible(true);
        } catch (NoSuchFieldException noSuchFieldException) {}
        sOverlapAnchorFieldAttempted = true;
      } 
      Field field = sOverlapAnchorField;
      if (field != null)
        try {
          field.set(paramPopupWindow, Boolean.valueOf(paramBoolean));
        } catch (IllegalAccessException illegalAccessException) {} 
    } 
  }
  
  public static void setWindowLayoutType(PopupWindow paramPopupWindow, int paramInt) {
    if (Build.VERSION.SDK_INT >= 23) {
      paramPopupWindow.setWindowLayoutType(paramInt);
      return;
    } 
    if (!sSetWindowLayoutTypeMethodAttempted) {
      try {
        sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", new Class[] { int.class });
        sSetWindowLayoutTypeMethod.setAccessible(true);
      } catch (Exception exception) {}
      sSetWindowLayoutTypeMethodAttempted = true;
    } 
    Method method = sSetWindowLayoutTypeMethod;
    if (method != null)
      try {
        method.invoke(paramPopupWindow, new Object[] { Integer.valueOf(paramInt) });
      } catch (Exception exception) {} 
  }
  
  public static void showAsDropDown(PopupWindow paramPopupWindow, View paramView, int paramInt1, int paramInt2, int paramInt3) {
    if (Build.VERSION.SDK_INT >= 19) {
      paramPopupWindow.showAsDropDown(paramView, paramInt1, paramInt2, paramInt3);
    } else {
      int i = paramInt1;
      if ((GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection(paramView)) & 0x7) == 5)
        i = paramInt1 - paramPopupWindow.getWidth() - paramView.getWidth(); 
      paramPopupWindow.showAsDropDown(paramView, i, paramInt2);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/widget/PopupWindowCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */