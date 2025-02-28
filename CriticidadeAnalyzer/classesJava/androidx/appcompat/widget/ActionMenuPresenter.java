package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPopup;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ActionProvider;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
  private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
  
  ActionButtonSubmenu mActionButtonPopup;
  
  private int mActionItemWidthLimit;
  
  private boolean mExpandedActionViewsExclusive;
  
  private int mMaxItems;
  
  private boolean mMaxItemsSet;
  
  private int mMinCellSize;
  
  int mOpenSubMenuId;
  
  OverflowMenuButton mOverflowButton;
  
  OverflowPopup mOverflowPopup;
  
  private Drawable mPendingOverflowIcon;
  
  private boolean mPendingOverflowIconSet;
  
  private ActionMenuPopupCallback mPopupCallback;
  
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
  
  OpenOverflowRunnable mPostedOpenRunnable;
  
  private boolean mReserveOverflow;
  
  private boolean mReserveOverflowSet;
  
  private View mScrapActionButtonView;
  
  private boolean mStrictWidthLimit;
  
  private int mWidthLimit;
  
  private boolean mWidthLimitSet;
  
  public ActionMenuPresenter(Context paramContext) {
    super(paramContext, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
  }
  
  private View findViewForItem(MenuItem paramMenuItem) {
    ViewGroup viewGroup = (ViewGroup)this.mMenuView;
    if (viewGroup == null)
      return null; 
    int i = viewGroup.getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = viewGroup.getChildAt(b);
      if (view instanceof MenuView.ItemView && ((MenuView.ItemView)view).getItemData() == paramMenuItem)
        return view; 
    } 
    return null;
  }
  
  public void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView) {
    paramItemView.initialize(paramMenuItemImpl, 0);
    ActionMenuView actionMenuView = (ActionMenuView)this.mMenuView;
    ActionMenuItemView actionMenuItemView = (ActionMenuItemView)paramItemView;
    actionMenuItemView.setItemInvoker(actionMenuView);
    if (this.mPopupCallback == null)
      this.mPopupCallback = new ActionMenuPopupCallback(); 
    actionMenuItemView.setPopupCallback(this.mPopupCallback);
  }
  
  public boolean dismissPopupMenus() {
    return hideOverflowMenu() | hideSubMenus();
  }
  
  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt) {
    return (paramViewGroup.getChildAt(paramInt) == this.mOverflowButton) ? false : super.filterLeftoverView(paramViewGroup, paramInt);
  }
  
  public boolean flagActionItems() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mMenu : Landroidx/appcompat/view/menu/MenuBuilder;
    //   4: astore #15
    //   6: aload #15
    //   8: ifnull -> 28
    //   11: aload #15
    //   13: invokevirtual getVisibleItems : ()Ljava/util/ArrayList;
    //   16: astore #15
    //   18: aload #15
    //   20: invokevirtual size : ()I
    //   23: istore #4
    //   25: goto -> 34
    //   28: aconst_null
    //   29: astore #15
    //   31: iconst_0
    //   32: istore #4
    //   34: aload_0
    //   35: getfield mMaxItems : I
    //   38: istore_1
    //   39: aload_0
    //   40: getfield mActionItemWidthLimit : I
    //   43: istore #8
    //   45: iconst_0
    //   46: iconst_0
    //   47: invokestatic makeMeasureSpec : (II)I
    //   50: istore #10
    //   52: aload_0
    //   53: getfield mMenuView : Landroidx/appcompat/view/menu/MenuView;
    //   56: checkcast android/view/ViewGroup
    //   59: astore #16
    //   61: iconst_0
    //   62: istore #5
    //   64: iconst_0
    //   65: istore #6
    //   67: iconst_0
    //   68: istore_3
    //   69: iconst_0
    //   70: istore_2
    //   71: iload #5
    //   73: iload #4
    //   75: if_icmpge -> 154
    //   78: aload #15
    //   80: iload #5
    //   82: invokevirtual get : (I)Ljava/lang/Object;
    //   85: checkcast androidx/appcompat/view/menu/MenuItemImpl
    //   88: astore #17
    //   90: aload #17
    //   92: invokevirtual requiresActionButton : ()Z
    //   95: ifeq -> 104
    //   98: iinc #3, 1
    //   101: goto -> 121
    //   104: aload #17
    //   106: invokevirtual requestsActionButton : ()Z
    //   109: ifeq -> 118
    //   112: iinc #2, 1
    //   115: goto -> 121
    //   118: iconst_1
    //   119: istore #6
    //   121: iload_1
    //   122: istore #7
    //   124: aload_0
    //   125: getfield mExpandedActionViewsExclusive : Z
    //   128: ifeq -> 145
    //   131: iload_1
    //   132: istore #7
    //   134: aload #17
    //   136: invokevirtual isActionViewExpanded : ()Z
    //   139: ifeq -> 145
    //   142: iconst_0
    //   143: istore #7
    //   145: iinc #5, 1
    //   148: iload #7
    //   150: istore_1
    //   151: goto -> 71
    //   154: iload_1
    //   155: istore #5
    //   157: aload_0
    //   158: getfield mReserveOverflow : Z
    //   161: ifeq -> 184
    //   164: iload #6
    //   166: ifne -> 179
    //   169: iload_1
    //   170: istore #5
    //   172: iload_2
    //   173: iload_3
    //   174: iadd
    //   175: iload_1
    //   176: if_icmple -> 184
    //   179: iload_1
    //   180: iconst_1
    //   181: isub
    //   182: istore #5
    //   184: iload #5
    //   186: iload_3
    //   187: isub
    //   188: istore_1
    //   189: aload_0
    //   190: getfield mActionButtonGroups : Landroid/util/SparseBooleanArray;
    //   193: astore #17
    //   195: aload #17
    //   197: invokevirtual clear : ()V
    //   200: aload_0
    //   201: getfield mStrictWidthLimit : Z
    //   204: ifeq -> 230
    //   207: aload_0
    //   208: getfield mMinCellSize : I
    //   211: istore_2
    //   212: iload #8
    //   214: iload_2
    //   215: idiv
    //   216: istore_3
    //   217: iload_2
    //   218: iload #8
    //   220: iload_2
    //   221: irem
    //   222: iload_3
    //   223: idiv
    //   224: iadd
    //   225: istore #6
    //   227: goto -> 235
    //   230: iconst_0
    //   231: istore #6
    //   233: iconst_0
    //   234: istore_3
    //   235: iload #8
    //   237: istore #5
    //   239: iconst_0
    //   240: istore #7
    //   242: iconst_0
    //   243: istore_2
    //   244: iload #4
    //   246: istore #8
    //   248: iload #7
    //   250: iload #8
    //   252: if_icmpge -> 757
    //   255: aload #15
    //   257: iload #7
    //   259: invokevirtual get : (I)Ljava/lang/Object;
    //   262: checkcast androidx/appcompat/view/menu/MenuItemImpl
    //   265: astore #18
    //   267: aload #18
    //   269: invokevirtual requiresActionButton : ()Z
    //   272: ifeq -> 388
    //   275: aload_0
    //   276: aload #18
    //   278: aload_0
    //   279: getfield mScrapActionButtonView : Landroid/view/View;
    //   282: aload #16
    //   284: invokevirtual getItemView : (Landroidx/appcompat/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    //   287: astore #19
    //   289: aload_0
    //   290: getfield mScrapActionButtonView : Landroid/view/View;
    //   293: ifnonnull -> 302
    //   296: aload_0
    //   297: aload #19
    //   299: putfield mScrapActionButtonView : Landroid/view/View;
    //   302: aload_0
    //   303: getfield mStrictWidthLimit : Z
    //   306: ifeq -> 326
    //   309: iload_3
    //   310: aload #19
    //   312: iload #6
    //   314: iload_3
    //   315: iload #10
    //   317: iconst_0
    //   318: invokestatic measureChildForCells : (Landroid/view/View;IIII)I
    //   321: isub
    //   322: istore_3
    //   323: goto -> 335
    //   326: aload #19
    //   328: iload #10
    //   330: iload #10
    //   332: invokevirtual measure : (II)V
    //   335: aload #19
    //   337: invokevirtual getMeasuredWidth : ()I
    //   340: istore #9
    //   342: iload #5
    //   344: iload #9
    //   346: isub
    //   347: istore #4
    //   349: iload_2
    //   350: ifne -> 359
    //   353: iload #9
    //   355: istore_2
    //   356: goto -> 359
    //   359: aload #18
    //   361: invokevirtual getGroupId : ()I
    //   364: istore #5
    //   366: iload #5
    //   368: ifeq -> 379
    //   371: aload #17
    //   373: iload #5
    //   375: iconst_1
    //   376: invokevirtual put : (IZ)V
    //   379: aload #18
    //   381: iconst_1
    //   382: invokevirtual setIsActionButton : (Z)V
    //   385: goto -> 747
    //   388: aload #18
    //   390: invokevirtual requestsActionButton : ()Z
    //   393: ifeq -> 737
    //   396: aload #18
    //   398: invokevirtual getGroupId : ()I
    //   401: istore #11
    //   403: aload #17
    //   405: iload #11
    //   407: invokevirtual get : (I)Z
    //   410: istore #14
    //   412: iload_1
    //   413: ifgt -> 421
    //   416: iload #14
    //   418: ifeq -> 443
    //   421: iload #5
    //   423: ifle -> 443
    //   426: aload_0
    //   427: getfield mStrictWidthLimit : Z
    //   430: ifeq -> 437
    //   433: iload_3
    //   434: ifle -> 443
    //   437: iconst_1
    //   438: istore #12
    //   440: goto -> 446
    //   443: iconst_0
    //   444: istore #12
    //   446: iload #12
    //   448: istore #13
    //   450: iload #12
    //   452: ifeq -> 601
    //   455: aload_0
    //   456: aload #18
    //   458: aload_0
    //   459: getfield mScrapActionButtonView : Landroid/view/View;
    //   462: aload #16
    //   464: invokevirtual getItemView : (Landroidx/appcompat/view/menu/MenuItemImpl;Landroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    //   467: astore #19
    //   469: aload_0
    //   470: getfield mScrapActionButtonView : Landroid/view/View;
    //   473: ifnonnull -> 482
    //   476: aload_0
    //   477: aload #19
    //   479: putfield mScrapActionButtonView : Landroid/view/View;
    //   482: aload_0
    //   483: getfield mStrictWidthLimit : Z
    //   486: ifeq -> 525
    //   489: aload #19
    //   491: iload #6
    //   493: iload_3
    //   494: iload #10
    //   496: iconst_0
    //   497: invokestatic measureChildForCells : (Landroid/view/View;IIII)I
    //   500: istore #9
    //   502: iload_3
    //   503: iload #9
    //   505: isub
    //   506: istore #4
    //   508: iload #4
    //   510: istore_3
    //   511: iload #9
    //   513: ifne -> 534
    //   516: iconst_0
    //   517: istore #13
    //   519: iload #4
    //   521: istore_3
    //   522: goto -> 534
    //   525: aload #19
    //   527: iload #10
    //   529: iload #10
    //   531: invokevirtual measure : (II)V
    //   534: aload #19
    //   536: invokevirtual getMeasuredWidth : ()I
    //   539: istore #9
    //   541: iload #5
    //   543: iload #9
    //   545: isub
    //   546: istore #4
    //   548: iload_2
    //   549: istore #5
    //   551: iload_2
    //   552: ifne -> 559
    //   555: iload #9
    //   557: istore #5
    //   559: aload_0
    //   560: getfield mStrictWidthLimit : Z
    //   563: ifeq -> 574
    //   566: iload #4
    //   568: iflt -> 587
    //   571: goto -> 582
    //   574: iload #4
    //   576: iload #5
    //   578: iadd
    //   579: ifle -> 587
    //   582: iconst_1
    //   583: istore_2
    //   584: goto -> 589
    //   587: iconst_0
    //   588: istore_2
    //   589: iload #13
    //   591: iload_2
    //   592: iand
    //   593: istore #12
    //   595: iload #5
    //   597: istore_2
    //   598: goto -> 605
    //   601: iload #5
    //   603: istore #4
    //   605: iload #12
    //   607: ifeq -> 629
    //   610: iload #11
    //   612: ifeq -> 629
    //   615: aload #17
    //   617: iload #11
    //   619: iconst_1
    //   620: invokevirtual put : (IZ)V
    //   623: iload_1
    //   624: istore #5
    //   626: goto -> 714
    //   629: iload_1
    //   630: istore #5
    //   632: iload #14
    //   634: ifeq -> 714
    //   637: aload #17
    //   639: iload #11
    //   641: iconst_0
    //   642: invokevirtual put : (IZ)V
    //   645: iconst_0
    //   646: istore #9
    //   648: iload_1
    //   649: istore #5
    //   651: iload #9
    //   653: iload #7
    //   655: if_icmpge -> 714
    //   658: aload #15
    //   660: iload #9
    //   662: invokevirtual get : (I)Ljava/lang/Object;
    //   665: checkcast androidx/appcompat/view/menu/MenuItemImpl
    //   668: astore #19
    //   670: iload_1
    //   671: istore #5
    //   673: aload #19
    //   675: invokevirtual getGroupId : ()I
    //   678: iload #11
    //   680: if_icmpne -> 705
    //   683: iload_1
    //   684: istore #5
    //   686: aload #19
    //   688: invokevirtual isActionButton : ()Z
    //   691: ifeq -> 699
    //   694: iload_1
    //   695: iconst_1
    //   696: iadd
    //   697: istore #5
    //   699: aload #19
    //   701: iconst_0
    //   702: invokevirtual setIsActionButton : (Z)V
    //   705: iinc #9, 1
    //   708: iload #5
    //   710: istore_1
    //   711: goto -> 648
    //   714: iload #5
    //   716: istore_1
    //   717: iload #12
    //   719: ifeq -> 727
    //   722: iload #5
    //   724: iconst_1
    //   725: isub
    //   726: istore_1
    //   727: aload #18
    //   729: iload #12
    //   731: invokevirtual setIsActionButton : (Z)V
    //   734: goto -> 385
    //   737: aload #18
    //   739: iconst_0
    //   740: invokevirtual setIsActionButton : (Z)V
    //   743: iload #5
    //   745: istore #4
    //   747: iinc #7, 1
    //   750: iload #4
    //   752: istore #5
    //   754: goto -> 248
    //   757: iconst_1
    //   758: ireturn
  }
  
  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup) {
    boolean bool;
    View view = paramMenuItemImpl.getActionView();
    if (view == null || paramMenuItemImpl.hasCollapsibleActionView())
      view = super.getItemView(paramMenuItemImpl, paramView, paramViewGroup); 
    if (paramMenuItemImpl.isActionViewExpanded()) {
      bool = true;
    } else {
      bool = false;
    } 
    view.setVisibility(bool);
    ActionMenuView actionMenuView = (ActionMenuView)paramViewGroup;
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    if (!actionMenuView.checkLayoutParams(layoutParams))
      view.setLayoutParams((ViewGroup.LayoutParams)actionMenuView.generateLayoutParams(layoutParams)); 
    return view;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup) {
    MenuView menuView2 = this.mMenuView;
    MenuView menuView1 = super.getMenuView(paramViewGroup);
    if (menuView2 != menuView1)
      ((ActionMenuView)menuView1).setPresenter(this); 
    return menuView1;
  }
  
  public Drawable getOverflowIcon() {
    OverflowMenuButton overflowMenuButton = this.mOverflowButton;
    return (overflowMenuButton != null) ? overflowMenuButton.getDrawable() : (this.mPendingOverflowIconSet ? this.mPendingOverflowIcon : null);
  }
  
  public boolean hideOverflowMenu() {
    OpenOverflowRunnable openOverflowRunnable = this.mPostedOpenRunnable;
    if (openOverflowRunnable != null) {
      MenuView menuView = this.mMenuView;
      if (menuView != null) {
        ((View)menuView).removeCallbacks(openOverflowRunnable);
        this.mPostedOpenRunnable = null;
        return true;
      } 
    } 
    OverflowPopup overflowPopup = this.mOverflowPopup;
    if (overflowPopup != null) {
      overflowPopup.dismiss();
      return true;
    } 
    return false;
  }
  
  public boolean hideSubMenus() {
    ActionButtonSubmenu actionButtonSubmenu = this.mActionButtonPopup;
    if (actionButtonSubmenu != null) {
      actionButtonSubmenu.dismiss();
      return true;
    } 
    return false;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder) {
    super.initForMenu(paramContext, paramMenuBuilder);
    Resources resources = paramContext.getResources();
    ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(paramContext);
    if (!this.mReserveOverflowSet)
      this.mReserveOverflow = actionBarPolicy.showsOverflowMenuButton(); 
    if (!this.mWidthLimitSet)
      this.mWidthLimit = actionBarPolicy.getEmbeddedMenuWidthLimit(); 
    if (!this.mMaxItemsSet)
      this.mMaxItems = actionBarPolicy.getMaxActionButtons(); 
    int i = this.mWidthLimit;
    if (this.mReserveOverflow) {
      if (this.mOverflowButton == null) {
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
        if (this.mPendingOverflowIconSet) {
          this.mOverflowButton.setImageDrawable(this.mPendingOverflowIcon);
          this.mPendingOverflowIcon = null;
          this.mPendingOverflowIconSet = false;
        } 
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mOverflowButton.measure(j, j);
      } 
      i -= this.mOverflowButton.getMeasuredWidth();
    } else {
      this.mOverflowButton = null;
    } 
    this.mActionItemWidthLimit = i;
    this.mMinCellSize = (int)((resources.getDisplayMetrics()).density * 56.0F);
    this.mScrapActionButtonView = null;
  }
  
  public boolean isOverflowMenuShowPending() {
    return (this.mPostedOpenRunnable != null || isOverflowMenuShowing());
  }
  
  public boolean isOverflowMenuShowing() {
    boolean bool;
    OverflowPopup overflowPopup = this.mOverflowPopup;
    if (overflowPopup != null && overflowPopup.isShowing()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {
    dismissPopupMenus();
    super.onCloseMenu(paramMenuBuilder, paramBoolean);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    if (!this.mMaxItemsSet)
      this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons(); 
    MenuBuilder menuBuilder = this.mMenu;
    if (menuBuilder != null)
      menuBuilder.onItemsChanged(true); 
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder) {
    boolean bool = paramSubMenuBuilder.hasVisibleItems();
    boolean bool1 = false;
    if (!bool)
      return false; 
    SubMenuBuilder subMenuBuilder;
    for (subMenuBuilder = paramSubMenuBuilder; subMenuBuilder.getParentMenu() != this.mMenu; subMenuBuilder = (SubMenuBuilder)subMenuBuilder.getParentMenu());
    View view = findViewForItem(subMenuBuilder.getItem());
    if (view == null)
      return false; 
    paramSubMenuBuilder.getItem().getItemId();
    int i = paramSubMenuBuilder.size();
    byte b = 0;
    while (true) {
      bool = bool1;
      if (b < i) {
        MenuItem menuItem = paramSubMenuBuilder.getItem(b);
        if (menuItem.isVisible() && menuItem.getIcon() != null) {
          bool = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    this.mActionButtonPopup = new ActionButtonSubmenu(this.mContext, paramSubMenuBuilder, view);
    this.mActionButtonPopup.setForceShowIcon(bool);
    this.mActionButtonPopup.show();
    super.onSubMenuSelected(paramSubMenuBuilder);
    return true;
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean) {
    this.mExpandedActionViewsExclusive = paramBoolean;
  }
  
  public void setMenuView(ActionMenuView paramActionMenuView) {
    this.mMenuView = paramActionMenuView;
    paramActionMenuView.initialize(this.mMenu);
  }
  
  public void setOverflowIcon(Drawable paramDrawable) {
    OverflowMenuButton overflowMenuButton = this.mOverflowButton;
    if (overflowMenuButton != null) {
      overflowMenuButton.setImageDrawable(paramDrawable);
    } else {
      this.mPendingOverflowIconSet = true;
      this.mPendingOverflowIcon = paramDrawable;
    } 
  }
  
  public void setReserveOverflow(boolean paramBoolean) {
    this.mReserveOverflow = paramBoolean;
    this.mReserveOverflowSet = true;
  }
  
  public boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl) {
    return paramMenuItemImpl.isActionButton();
  }
  
  public boolean showOverflowMenu() {
    if (this.mReserveOverflow && !isOverflowMenuShowing()) {
      MenuBuilder menuBuilder = this.mMenu;
      if (menuBuilder != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !menuBuilder.getNonActionItems().isEmpty()) {
        this.mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, (View)this.mOverflowButton, true));
        ((View)this.mMenuView).post(this.mPostedOpenRunnable);
        super.onSubMenuSelected(null);
        return true;
      } 
    } 
    return false;
  }
  
  public void updateMenuView(boolean paramBoolean) {
    super.updateMenuView(paramBoolean);
    ((View)this.mMenuView).requestLayout();
    MenuBuilder<MenuItemImpl> menuBuilder = this.mMenu;
    byte b = 0;
    if (menuBuilder != null) {
      ArrayList<MenuItemImpl> arrayList = menuBuilder.getActionItems();
      int j = arrayList.size();
      for (byte b1 = 0; b1 < j; b1++) {
        ActionProvider actionProvider = ((MenuItemImpl)arrayList.get(b1)).getSupportActionProvider();
        if (actionProvider != null)
          actionProvider.setSubUiVisibilityListener(this); 
      } 
    } 
    menuBuilder = this.mMenu;
    if (menuBuilder != null) {
      ArrayList arrayList = menuBuilder.getNonActionItems();
    } else {
      menuBuilder = null;
    } 
    int i = b;
    if (this.mReserveOverflow) {
      i = b;
      if (menuBuilder != null) {
        int j = menuBuilder.size();
        if (j == 1) {
          i = ((MenuItemImpl)menuBuilder.get(0)).isActionViewExpanded() ^ true;
        } else {
          i = b;
          if (j > 0)
            i = 1; 
        } 
      } 
    } 
    if (i != 0) {
      if (this.mOverflowButton == null)
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext); 
      ViewGroup viewGroup = (ViewGroup)this.mOverflowButton.getParent();
      if (viewGroup != this.mMenuView) {
        if (viewGroup != null)
          viewGroup.removeView((View)this.mOverflowButton); 
        viewGroup = (ActionMenuView)this.mMenuView;
        viewGroup.addView((View)this.mOverflowButton, (ViewGroup.LayoutParams)viewGroup.generateOverflowButtonLayoutParams());
      } 
    } else {
      OverflowMenuButton overflowMenuButton = this.mOverflowButton;
      if (overflowMenuButton != null) {
        ViewParent viewParent = overflowMenuButton.getParent();
        MenuView menuView = this.mMenuView;
        if (viewParent == menuView)
          ((ViewGroup)menuView).removeView((View)this.mOverflowButton); 
      } 
    } 
    ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
  }
  
  private class ActionButtonSubmenu extends MenuPopupHelper {
    final ActionMenuPresenter this$0;
    
    public ActionButtonSubmenu(Context param1Context, SubMenuBuilder param1SubMenuBuilder, View param1View) {
      super(param1Context, (MenuBuilder)param1SubMenuBuilder, param1View, false, R.attr.actionOverflowMenuStyle);
      if (!((MenuItemImpl)param1SubMenuBuilder.getItem()).isActionButton()) {
        View view;
        ActionMenuPresenter.OverflowMenuButton overflowMenuButton2 = ActionMenuPresenter.this.mOverflowButton;
        ActionMenuPresenter.OverflowMenuButton overflowMenuButton1 = overflowMenuButton2;
        if (overflowMenuButton2 == null)
          view = (View)ActionMenuPresenter.this.mMenuView; 
        setAnchorView(view);
      } 
      setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }
    
    protected void onDismiss() {
      ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
      actionMenuPresenter.mActionButtonPopup = null;
      actionMenuPresenter.mOpenSubMenuId = 0;
      super.onDismiss();
    }
  }
  
  private class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
    final ActionMenuPresenter this$0;
    
    public ShowableListMenu getPopup() {
      ActionMenuPresenter.ActionButtonSubmenu actionButtonSubmenu = ActionMenuPresenter.this.mActionButtonPopup;
      if (actionButtonSubmenu != null) {
        MenuPopup menuPopup = actionButtonSubmenu.getPopup();
      } else {
        actionButtonSubmenu = null;
      } 
      return (ShowableListMenu)actionButtonSubmenu;
    }
  }
  
  private class OpenOverflowRunnable implements Runnable {
    private ActionMenuPresenter.OverflowPopup mPopup;
    
    final ActionMenuPresenter this$0;
    
    public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup param1OverflowPopup) {
      this.mPopup = param1OverflowPopup;
    }
    
    public void run() {
      if (ActionMenuPresenter.this.mMenu != null)
        ActionMenuPresenter.this.mMenu.changeMenuMode(); 
      View view = (View)ActionMenuPresenter.this.mMenuView;
      if (view != null && view.getWindowToken() != null && this.mPopup.tryShow())
        ActionMenuPresenter.this.mOverflowPopup = this.mPopup; 
      ActionMenuPresenter.this.mPostedOpenRunnable = null;
    }
  }
  
  private class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
    final ActionMenuPresenter this$0;
    
    public OverflowMenuButton(Context param1Context) {
      super(param1Context, null, R.attr.actionOverflowButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
      TooltipCompat.setTooltipText((View)this, getContentDescription());
      setOnTouchListener(new ForwardingListener((View)this, ActionMenuPresenter.this) {
            final ActionMenuPresenter.OverflowMenuButton this$1;
            
            public ShowableListMenu getPopup() {
              ActionMenuPresenter.OverflowPopup overflowPopup = ActionMenuPresenter.this.mOverflowPopup;
              return (ShowableListMenu)((overflowPopup == null) ? null : overflowPopup.getPopup());
            }
            
            public boolean onForwardingStarted() {
              ActionMenuPresenter.this.showOverflowMenu();
              return true;
            }
            
            public boolean onForwardingStopped() {
              ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
              if (actionMenuPresenter.mPostedOpenRunnable != null)
                return false; 
              actionMenuPresenter.hideOverflowMenu();
              return true;
            }
          });
    }
    
    public boolean needsDividerAfter() {
      return false;
    }
    
    public boolean needsDividerBefore() {
      return false;
    }
    
    public boolean performClick() {
      if (super.performClick())
        return true; 
      playSoundEffect(0);
      ActionMenuPresenter.this.showOverflowMenu();
      return true;
    }
    
    protected boolean setFrame(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      boolean bool = super.setFrame(param1Int1, param1Int2, param1Int3, param1Int4);
      Drawable drawable1 = getDrawable();
      Drawable drawable2 = getBackground();
      if (drawable1 != null && drawable2 != null) {
        int k = getWidth();
        param1Int4 = getHeight();
        param1Int1 = Math.max(k, param1Int4) / 2;
        int j = getPaddingLeft();
        int i = getPaddingRight();
        param1Int3 = getPaddingTop();
        param1Int2 = getPaddingBottom();
        i = (k + j - i) / 2;
        param1Int2 = (param1Int4 + param1Int3 - param1Int2) / 2;
        DrawableCompat.setHotspotBounds(drawable2, i - param1Int1, param1Int2 - param1Int1, i + param1Int1, param1Int2 + param1Int1);
      } 
      return bool;
    }
  }
  
  class null extends ForwardingListener {
    final ActionMenuPresenter.OverflowMenuButton this$1;
    
    null(View param1View, ActionMenuPresenter param1ActionMenuPresenter) {
      super(param1View);
    }
    
    public ShowableListMenu getPopup() {
      ActionMenuPresenter.OverflowPopup overflowPopup = ActionMenuPresenter.this.mOverflowPopup;
      return (ShowableListMenu)((overflowPopup == null) ? null : overflowPopup.getPopup());
    }
    
    public boolean onForwardingStarted() {
      ActionMenuPresenter.this.showOverflowMenu();
      return true;
    }
    
    public boolean onForwardingStopped() {
      ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
      if (actionMenuPresenter.mPostedOpenRunnable != null)
        return false; 
      actionMenuPresenter.hideOverflowMenu();
      return true;
    }
  }
  
  private class OverflowPopup extends MenuPopupHelper {
    final ActionMenuPresenter this$0;
    
    public OverflowPopup(Context param1Context, MenuBuilder param1MenuBuilder, View param1View, boolean param1Boolean) {
      super(param1Context, param1MenuBuilder, param1View, param1Boolean, R.attr.actionOverflowMenuStyle);
      setGravity(8388613);
      setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }
    
    protected void onDismiss() {
      if (ActionMenuPresenter.this.mMenu != null)
        ActionMenuPresenter.this.mMenu.close(); 
      ActionMenuPresenter.this.mOverflowPopup = null;
      super.onDismiss();
    }
  }
  
  private class PopupPresenterCallback implements MenuPresenter.Callback {
    final ActionMenuPresenter this$0;
    
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {
      if (param1MenuBuilder instanceof SubMenuBuilder)
        param1MenuBuilder.getRootMenu().close(false); 
      MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
      if (callback != null)
        callback.onCloseMenu(param1MenuBuilder, param1Boolean); 
    }
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      boolean bool = false;
      if (param1MenuBuilder == null)
        return false; 
      ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)param1MenuBuilder).getItem().getItemId();
      MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
      if (callback != null)
        bool = callback.onOpenSubMenu(param1MenuBuilder); 
      return bool;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/ActionMenuPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */