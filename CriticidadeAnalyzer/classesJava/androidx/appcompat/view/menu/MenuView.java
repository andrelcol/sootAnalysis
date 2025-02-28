package androidx.appcompat.view.menu;

public interface MenuView {
  void initialize(MenuBuilder paramMenuBuilder);
  
  public static interface ItemView {
    MenuItemImpl getItemData();
    
    void initialize(MenuItemImpl param1MenuItemImpl, int param1Int);
    
    boolean prefersCondensedTitle();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/view/menu/MenuView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */