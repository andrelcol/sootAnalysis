package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.MenuItem;
import androidx.core.internal.view.SupportMenuItem;

public final class MenuItemCompat {
  public static MenuItem setActionProvider(MenuItem paramMenuItem, ActionProvider paramActionProvider) {
    SupportMenuItem supportMenuItem;
    MenuItem menuItem = paramMenuItem;
    if (paramMenuItem instanceof SupportMenuItem)
      supportMenuItem = ((SupportMenuItem)paramMenuItem).setSupportActionProvider(paramActionProvider); 
    return (MenuItem)supportMenuItem;
  }
  
  public static void setAlphabeticShortcut(MenuItem paramMenuItem, char paramChar, int paramInt) {
    if (paramMenuItem instanceof SupportMenuItem) {
      ((SupportMenuItem)paramMenuItem).setAlphabeticShortcut(paramChar, paramInt);
    } else if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setAlphabeticShortcut(paramChar, paramInt);
    } 
  }
  
  public static void setContentDescription(MenuItem paramMenuItem, CharSequence paramCharSequence) {
    if (paramMenuItem instanceof SupportMenuItem) {
      ((SupportMenuItem)paramMenuItem).setContentDescription(paramCharSequence);
    } else if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setContentDescription(paramCharSequence);
    } 
  }
  
  public static void setIconTintList(MenuItem paramMenuItem, ColorStateList paramColorStateList) {
    if (paramMenuItem instanceof SupportMenuItem) {
      ((SupportMenuItem)paramMenuItem).setIconTintList(paramColorStateList);
    } else if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setIconTintList(paramColorStateList);
    } 
  }
  
  public static void setIconTintMode(MenuItem paramMenuItem, PorterDuff.Mode paramMode) {
    if (paramMenuItem instanceof SupportMenuItem) {
      ((SupportMenuItem)paramMenuItem).setIconTintMode(paramMode);
    } else if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setIconTintMode(paramMode);
    } 
  }
  
  public static void setNumericShortcut(MenuItem paramMenuItem, char paramChar, int paramInt) {
    if (paramMenuItem instanceof SupportMenuItem) {
      ((SupportMenuItem)paramMenuItem).setNumericShortcut(paramChar, paramInt);
    } else if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setNumericShortcut(paramChar, paramInt);
    } 
  }
  
  public static void setTooltipText(MenuItem paramMenuItem, CharSequence paramCharSequence) {
    if (paramMenuItem instanceof SupportMenuItem) {
      ((SupportMenuItem)paramMenuItem).setTooltipText(paramCharSequence);
    } else if (Build.VERSION.SDK_INT >= 26) {
      paramMenuItem.setTooltipText(paramCharSequence);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/view/MenuItemCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */