package androidx.appcompat.widget;

import android.view.Menu;
import android.view.Window;
import androidx.appcompat.view.menu.MenuPresenter;

public interface DecorContentParent {
  boolean canShowOverflowMenu();
  
  void dismissPopups();
  
  boolean hideOverflowMenu();
  
  void initFeature(int paramInt);
  
  boolean isOverflowMenuShowPending();
  
  boolean isOverflowMenuShowing();
  
  void setMenu(Menu paramMenu, MenuPresenter.Callback paramCallback);
  
  void setMenuPrepared();
  
  void setWindowCallback(Window.Callback paramCallback);
  
  void setWindowTitle(CharSequence paramCharSequence);
  
  boolean showOverflowMenu();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/DecorContentParent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */