package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.ViewPropertyAnimatorCompat;

public interface DecorToolbar {
  boolean canShowOverflowMenu();
  
  void collapseActionView();
  
  void dismissPopupMenus();
  
  Context getContext();
  
  int getDisplayOptions();
  
  int getNavigationMode();
  
  CharSequence getTitle();
  
  ViewGroup getViewGroup();
  
  boolean hasExpandedActionView();
  
  boolean hideOverflowMenu();
  
  void initIndeterminateProgress();
  
  void initProgress();
  
  boolean isOverflowMenuShowPending();
  
  boolean isOverflowMenuShowing();
  
  void setCollapsible(boolean paramBoolean);
  
  void setDisplayOptions(int paramInt);
  
  void setEmbeddedTabView(ScrollingTabContainerView paramScrollingTabContainerView);
  
  void setHomeButtonEnabled(boolean paramBoolean);
  
  void setIcon(int paramInt);
  
  void setIcon(Drawable paramDrawable);
  
  void setLogo(int paramInt);
  
  void setMenu(Menu paramMenu, MenuPresenter.Callback paramCallback);
  
  void setMenuPrepared();
  
  void setVisibility(int paramInt);
  
  void setWindowCallback(Window.Callback paramCallback);
  
  void setWindowTitle(CharSequence paramCharSequence);
  
  ViewPropertyAnimatorCompat setupAnimatorToVisibility(int paramInt, long paramLong);
  
  boolean showOverflowMenu();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/DecorToolbar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */