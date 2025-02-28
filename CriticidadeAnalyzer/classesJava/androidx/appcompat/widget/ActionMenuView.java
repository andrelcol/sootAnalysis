package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;

public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
  private MenuPresenter.Callback mActionMenuPresenterCallback;
  
  private boolean mFormatItems;
  
  private int mFormatItemsWidth;
  
  private int mGeneratedItemPadding;
  
  private MenuBuilder mMenu;
  
  MenuBuilder.Callback mMenuBuilderCallback;
  
  private int mMinCellSize;
  
  OnMenuItemClickListener mOnMenuItemClickListener;
  
  private Context mPopupContext;
  
  private int mPopupTheme;
  
  private ActionMenuPresenter mPresenter;
  
  private boolean mReserveOverflow;
  
  public ActionMenuView(Context paramContext) {
    this(paramContext, null);
  }
  
  public ActionMenuView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    setBaselineAligned(false);
    float f = (paramContext.getResources().getDisplayMetrics()).density;
    this.mMinCellSize = (int)(56.0F * f);
    this.mGeneratedItemPadding = (int)(f * 4.0F);
    this.mPopupContext = paramContext;
    this.mPopupTheme = 0;
  }
  
  static int measureChildForCells(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    ActionMenuItemView actionMenuItemView;
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt3) - paramInt4, View.MeasureSpec.getMode(paramInt3));
    if (paramView instanceof ActionMenuItemView) {
      actionMenuItemView = (ActionMenuItemView)paramView;
    } else {
      actionMenuItemView = null;
    } 
    boolean bool = true;
    if (actionMenuItemView != null && actionMenuItemView.hasText()) {
      paramInt3 = 1;
    } else {
      paramInt3 = 0;
    } 
    paramInt4 = 2;
    if (paramInt2 > 0 && (paramInt3 == 0 || paramInt2 >= 2)) {
      paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt2 * paramInt1, -2147483648), i);
      int k = paramView.getMeasuredWidth();
      int j = k / paramInt1;
      paramInt2 = j;
      if (k % paramInt1 != 0)
        paramInt2 = j + 1; 
      if (paramInt3 != 0 && paramInt2 < 2)
        paramInt2 = paramInt4; 
    } else {
      paramInt2 = 0;
    } 
    if (layoutParams.isOverflowButton || paramInt3 == 0)
      bool = false; 
    layoutParams.expandable = bool;
    layoutParams.cellsUsed = paramInt2;
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1 * paramInt2, 1073741824), i);
    return paramInt2;
  }
  
  private void onMeasureExactFormat(int paramInt1, int paramInt2) {
    int i2 = View.MeasureSpec.getMode(paramInt2);
    int k = View.MeasureSpec.getSize(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt2);
    int j = getPaddingLeft();
    paramInt1 = getPaddingRight();
    int i3 = getPaddingTop() + getPaddingBottom();
    int i6 = ViewGroup.getChildMeasureSpec(paramInt2, i3, -2);
    int i5 = k - j + paramInt1;
    paramInt2 = this.mMinCellSize;
    paramInt1 = i5 / paramInt2;
    if (paramInt1 == 0) {
      setMeasuredDimension(i5, 0);
      return;
    } 
    int i7 = paramInt2 + i5 % paramInt2 / paramInt1;
    int i8 = getChildCount();
    int i1 = 0;
    paramInt2 = 0;
    j = 0;
    byte b = 0;
    int n = 0;
    k = 0;
    long l = 0L;
    while (i1 < i8) {
      View view = getChildAt(i1);
      if (view.getVisibility() != 8) {
        boolean bool = view instanceof ActionMenuItemView;
        b++;
        if (bool) {
          i9 = this.mGeneratedItemPadding;
          view.setPadding(i9, 0, i9, 0);
        } 
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        layoutParams.expanded = false;
        layoutParams.extraPixels = 0;
        layoutParams.cellsUsed = 0;
        layoutParams.expandable = false;
        layoutParams.leftMargin = 0;
        layoutParams.rightMargin = 0;
        if (bool && ((ActionMenuItemView)view).hasText()) {
          bool = true;
        } else {
          bool = false;
        } 
        layoutParams.preventEdgeOffset = bool;
        if (layoutParams.isOverflowButton) {
          i9 = 1;
        } else {
          i9 = paramInt1;
        } 
        int i10 = measureChildForCells(view, i7, i9, i6, i3);
        n = Math.max(n, i10);
        int i9 = k;
        if (layoutParams.expandable)
          i9 = k + 1; 
        if (layoutParams.isOverflowButton)
          j = 1; 
        paramInt1 -= i10;
        paramInt2 = Math.max(paramInt2, view.getMeasuredHeight());
        if (i10 == 1)
          l |= (1 << i1); 
        k = i9;
      } 
      i1++;
    } 
    if (j != 0 && b == 2) {
      i3 = 1;
    } else {
      i3 = 0;
    } 
    int m = 0;
    int i4 = paramInt1;
    paramInt1 = m;
    i1 = i5;
    while (k > 0 && i4 > 0) {
      i5 = Integer.MAX_VALUE;
      byte b1 = 0;
      int i9 = 0;
      long l1;
      for (l1 = 0L; b1 < i8; l1 = l2) {
        int i10;
        long l2;
        LayoutParams layoutParams = (LayoutParams)getChildAt(b1).getLayoutParams();
        if (!layoutParams.expandable) {
          i10 = i5;
          m = i9;
          l2 = l1;
        } else {
          int i11 = layoutParams.cellsUsed;
          if (i11 < i5) {
            i10 = i11;
            l2 = 1L << b1;
            m = 1;
          } else {
            i10 = i5;
            m = i9;
            l2 = l1;
            if (i11 == i5) {
              l2 = l1 | 1L << b1;
              m = i9 + 1;
              i10 = i5;
            } 
          } 
        } 
        b1++;
        i5 = i10;
        i9 = m;
      } 
      m = paramInt1;
      paramInt1 = paramInt2;
      l |= l1;
      if (i9 > i4) {
        paramInt2 = paramInt1;
        paramInt1 = m;
        break;
      } 
      paramInt2 = i5 + 1;
      for (m = 0; m < i8; m++) {
        View view = getChildAt(m);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        long l2 = (1 << m);
        if ((l1 & l2) == 0L) {
          long l3 = l;
          if (layoutParams.cellsUsed == paramInt2)
            l3 = l | l2; 
          l = l3;
        } else {
          if (i3 != 0 && layoutParams.preventEdgeOffset && i4 == 1) {
            i5 = this.mGeneratedItemPadding;
            view.setPadding(i5 + i7, 0, i5, 0);
          } 
          layoutParams.cellsUsed++;
          layoutParams.expanded = true;
          i4--;
        } 
      } 
      paramInt2 = paramInt1;
      paramInt1 = 1;
    } 
    if (j == 0 && b == 1) {
      j = 1;
    } else {
      j = 0;
    } 
    if (i4 > 0 && l != 0L && (i4 < b - 1 || j != 0 || n > 1)) {
      float f = Long.bitCount(l);
      if (j == 0) {
        float f1 = f;
        if ((l & 0x1L) != 0L) {
          f1 = f;
          if (!((LayoutParams)getChildAt(0).getLayoutParams()).preventEdgeOffset)
            f1 = f - 0.5F; 
        } 
        j = i8 - 1;
        f = f1;
        if ((l & (1 << j)) != 0L) {
          f = f1;
          if (!((LayoutParams)getChildAt(j).getLayoutParams()).preventEdgeOffset)
            f = f1 - 0.5F; 
        } 
      } 
      if (f > 0.0F) {
        j = (int)((i4 * i7) / f);
      } else {
        j = 0;
      } 
      k = 0;
      while (true) {
        m = paramInt1;
        if (k < i8) {
          if ((l & (1 << k)) == 0L) {
            m = paramInt1;
          } else {
            View view = getChildAt(k);
            LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            if (view instanceof ActionMenuItemView) {
              layoutParams.extraPixels = j;
              layoutParams.expanded = true;
              if (k == 0 && !layoutParams.preventEdgeOffset)
                layoutParams.leftMargin = -j / 2; 
            } else if (layoutParams.isOverflowButton) {
              layoutParams.extraPixels = j;
              layoutParams.expanded = true;
              layoutParams.rightMargin = -j / 2;
            } else {
              if (k != 0)
                layoutParams.leftMargin = j / 2; 
              m = paramInt1;
              if (k != i8 - 1) {
                layoutParams.rightMargin = j / 2;
                m = paramInt1;
              } 
              k++;
              paramInt1 = m;
            } 
            m = 1;
          } 
        } else {
          break;
        } 
        k++;
        paramInt1 = m;
      } 
    } else {
      m = paramInt1;
    } 
    paramInt1 = 0;
    if (m != 0)
      while (paramInt1 < i8) {
        View view = getChildAt(paramInt1);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.expanded)
          view.measure(View.MeasureSpec.makeMeasureSpec(layoutParams.cellsUsed * i7 + layoutParams.extraPixels, 1073741824), i6); 
        paramInt1++;
      }  
    if (i2 == 1073741824)
      paramInt2 = i; 
    setMeasuredDimension(i1, paramInt2);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    boolean bool;
    if (paramLayoutParams != null && paramLayoutParams instanceof LayoutParams) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void dismissPopupMenus() {
    ActionMenuPresenter actionMenuPresenter = this.mPresenter;
    if (actionMenuPresenter != null)
      actionMenuPresenter.dismissPopupMenus(); 
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    return false;
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    LayoutParams layoutParams = new LayoutParams(-2, -2);
    layoutParams.gravity = 16;
    return layoutParams;
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    if (paramLayoutParams != null) {
      LayoutParams layoutParams;
      if (paramLayoutParams instanceof LayoutParams) {
        layoutParams = new LayoutParams((LayoutParams)paramLayoutParams);
      } else {
        layoutParams = new LayoutParams((ViewGroup.LayoutParams)layoutParams);
      } 
      if (layoutParams.gravity <= 0)
        layoutParams.gravity = 16; 
      return layoutParams;
    } 
    return generateDefaultLayoutParams();
  }
  
  public LayoutParams generateOverflowButtonLayoutParams() {
    LayoutParams layoutParams = generateDefaultLayoutParams();
    layoutParams.isOverflowButton = true;
    return layoutParams;
  }
  
  public Menu getMenu() {
    if (this.mMenu == null) {
      Context context = getContext();
      this.mMenu = new MenuBuilder(context);
      this.mMenu.setCallback(new MenuBuilderCallback());
      this.mPresenter = new ActionMenuPresenter(context);
      this.mPresenter.setReserveOverflow(true);
      ActionMenuPresenter actionMenuPresenter = this.mPresenter;
      MenuPresenter.Callback callback = this.mActionMenuPresenterCallback;
      if (callback == null)
        callback = new ActionMenuPresenterCallback(); 
      actionMenuPresenter.setCallback(callback);
      this.mMenu.addMenuPresenter((MenuPresenter)this.mPresenter, this.mPopupContext);
      this.mPresenter.setMenuView(this);
    } 
    return (Menu)this.mMenu;
  }
  
  public Drawable getOverflowIcon() {
    getMenu();
    return this.mPresenter.getOverflowIcon();
  }
  
  public int getPopupTheme() {
    return this.mPopupTheme;
  }
  
  public int getWindowAnimations() {
    return 0;
  }
  
  protected boolean hasSupportDividerBeforeChildAt(int paramInt) {
    boolean bool;
    int j = 0;
    if (paramInt == 0)
      return false; 
    View view1 = getChildAt(paramInt - 1);
    View view2 = getChildAt(paramInt);
    int i = j;
    if (paramInt < getChildCount()) {
      i = j;
      if (view1 instanceof ActionMenuChildView)
        i = false | ((ActionMenuChildView)view1).needsDividerAfter(); 
    } 
    j = i;
    if (paramInt > 0) {
      j = i;
      if (view2 instanceof ActionMenuChildView)
        bool = i | ((ActionMenuChildView)view2).needsDividerBefore(); 
    } 
    return bool;
  }
  
  public boolean hideOverflowMenu() {
    boolean bool;
    ActionMenuPresenter actionMenuPresenter = this.mPresenter;
    if (actionMenuPresenter != null && actionMenuPresenter.hideOverflowMenu()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void initialize(MenuBuilder paramMenuBuilder) {
    this.mMenu = paramMenuBuilder;
  }
  
  public boolean invokeItem(MenuItemImpl paramMenuItemImpl) {
    return this.mMenu.performItemAction((MenuItem)paramMenuItemImpl, 0);
  }
  
  public boolean isOverflowMenuShowPending() {
    boolean bool;
    ActionMenuPresenter actionMenuPresenter = this.mPresenter;
    if (actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowPending()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOverflowMenuShowing() {
    boolean bool;
    ActionMenuPresenter actionMenuPresenter = this.mPresenter;
    if (actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowing()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isOverflowReserved() {
    return this.mReserveOverflow;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    ActionMenuPresenter actionMenuPresenter = this.mPresenter;
    if (actionMenuPresenter != null) {
      actionMenuPresenter.updateMenuView(false);
      if (this.mPresenter.isOverflowMenuShowing()) {
        this.mPresenter.hideOverflowMenu();
        this.mPresenter.showOverflowMenu();
      } 
    } 
  }
  
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    dismissPopupMenus();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (!this.mFormatItems) {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    } 
    int j = getChildCount();
    int i = (paramInt4 - paramInt2) / 2;
    int m = getDividerWidth();
    int k = paramInt3 - paramInt1;
    paramInt2 = getPaddingRight();
    paramInt1 = getPaddingLeft();
    paramBoolean = ViewUtils.isLayoutRtl((View)this);
    paramInt1 = k - paramInt2 - paramInt1;
    paramInt3 = 0;
    paramInt4 = 0;
    paramInt2 = 0;
    while (paramInt3 < j) {
      View view = getChildAt(paramInt3);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.isOverflowButton) {
          int i1;
          int n = view.getMeasuredWidth();
          paramInt4 = n;
          if (hasSupportDividerBeforeChildAt(paramInt3))
            paramInt4 = n + m; 
          int i2 = view.getMeasuredHeight();
          if (paramBoolean) {
            n = getPaddingLeft() + layoutParams.leftMargin;
            i1 = n + paramInt4;
          } else {
            i1 = getWidth() - getPaddingRight() - layoutParams.rightMargin;
            n = i1 - paramInt4;
          } 
          int i3 = i - i2 / 2;
          view.layout(n, i3, i1, i2 + i3);
          paramInt1 -= paramInt4;
          paramInt4 = 1;
        } else {
          paramInt1 -= view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
          hasSupportDividerBeforeChildAt(paramInt3);
          paramInt2++;
        } 
      } 
      paramInt3++;
    } 
    if (j == 1 && paramInt4 == 0) {
      View view = getChildAt(0);
      paramInt2 = view.getMeasuredWidth();
      paramInt1 = view.getMeasuredHeight();
      paramInt3 = k / 2 - paramInt2 / 2;
      paramInt4 = i - paramInt1 / 2;
      view.layout(paramInt3, paramInt4, paramInt2 + paramInt3, paramInt1 + paramInt4);
      return;
    } 
    paramInt2 -= paramInt4 ^ 0x1;
    if (paramInt2 > 0) {
      paramInt1 /= paramInt2;
    } else {
      paramInt1 = 0;
    } 
    paramInt2 = 0;
    paramInt3 = 0;
    paramInt4 = Math.max(0, paramInt1);
    if (paramBoolean) {
      paramInt2 = getWidth() - getPaddingRight();
      paramInt1 = paramInt3;
      while (paramInt1 < j) {
        View view = getChildAt(paramInt1);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        paramInt3 = paramInt2;
        if (view.getVisibility() != 8)
          if (layoutParams.isOverflowButton) {
            paramInt3 = paramInt2;
          } else {
            int n = paramInt2 - layoutParams.rightMargin;
            paramInt2 = view.getMeasuredWidth();
            paramInt3 = view.getMeasuredHeight();
            int i1 = i - paramInt3 / 2;
            view.layout(n - paramInt2, i1, n, paramInt3 + i1);
            paramInt3 = n - paramInt2 + layoutParams.leftMargin + paramInt4;
          }  
        paramInt1++;
        paramInt2 = paramInt3;
      } 
    } else {
      paramInt3 = getPaddingLeft();
      paramInt1 = paramInt2;
      while (paramInt1 < j) {
        View view = getChildAt(paramInt1);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        paramInt2 = paramInt3;
        if (view.getVisibility() != 8)
          if (layoutParams.isOverflowButton) {
            paramInt2 = paramInt3;
          } else {
            paramInt3 += layoutParams.leftMargin;
            paramInt2 = view.getMeasuredWidth();
            int i1 = view.getMeasuredHeight();
            int n = i - i1 / 2;
            view.layout(paramInt3, n, paramInt3 + paramInt2, i1 + n);
            paramInt2 = paramInt3 + paramInt2 + layoutParams.rightMargin + paramInt4;
          }  
        paramInt1++;
        paramInt3 = paramInt2;
      } 
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    boolean bool;
    boolean bool1 = this.mFormatItems;
    if (View.MeasureSpec.getMode(paramInt1) == 1073741824) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mFormatItems = bool;
    if (bool1 != this.mFormatItems)
      this.mFormatItemsWidth = 0; 
    int i = View.MeasureSpec.getSize(paramInt1);
    if (this.mFormatItems) {
      MenuBuilder menuBuilder = this.mMenu;
      if (menuBuilder != null && i != this.mFormatItemsWidth) {
        this.mFormatItemsWidth = i;
        menuBuilder.onItemsChanged(true);
      } 
    } 
    int j = getChildCount();
    if (this.mFormatItems && j > 0) {
      onMeasureExactFormat(paramInt1, paramInt2);
    } else {
      for (i = 0; i < j; i++) {
        LayoutParams layoutParams = (LayoutParams)getChildAt(i).getLayoutParams();
        layoutParams.rightMargin = 0;
        layoutParams.leftMargin = 0;
      } 
      super.onMeasure(paramInt1, paramInt2);
    } 
  }
  
  public MenuBuilder peekMenu() {
    return this.mMenu;
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean) {
    this.mPresenter.setExpandedActionViewsExclusive(paramBoolean);
  }
  
  public void setMenuCallbacks(MenuPresenter.Callback paramCallback, MenuBuilder.Callback paramCallback1) {
    this.mActionMenuPresenterCallback = paramCallback;
    this.mMenuBuilderCallback = paramCallback1;
  }
  
  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener) {
    this.mOnMenuItemClickListener = paramOnMenuItemClickListener;
  }
  
  public void setOverflowIcon(Drawable paramDrawable) {
    getMenu();
    this.mPresenter.setOverflowIcon(paramDrawable);
  }
  
  public void setOverflowReserved(boolean paramBoolean) {
    this.mReserveOverflow = paramBoolean;
  }
  
  public void setPopupTheme(int paramInt) {
    if (this.mPopupTheme != paramInt) {
      this.mPopupTheme = paramInt;
      if (paramInt == 0) {
        this.mPopupContext = getContext();
      } else {
        this.mPopupContext = (Context)new ContextThemeWrapper(getContext(), paramInt);
      } 
    } 
  }
  
  public void setPresenter(ActionMenuPresenter paramActionMenuPresenter) {
    this.mPresenter = paramActionMenuPresenter;
    this.mPresenter.setMenuView(this);
  }
  
  public boolean showOverflowMenu() {
    boolean bool;
    ActionMenuPresenter actionMenuPresenter = this.mPresenter;
    if (actionMenuPresenter != null && actionMenuPresenter.showOverflowMenu()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static interface ActionMenuChildView {
    boolean needsDividerAfter();
    
    boolean needsDividerBefore();
  }
  
  private static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {}
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      return false;
    }
  }
  
  public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
    @ExportedProperty
    public int cellsUsed;
    
    @ExportedProperty
    public boolean expandable;
    
    boolean expanded;
    
    @ExportedProperty
    public int extraPixels;
    
    @ExportedProperty
    public boolean isOverflowButton;
    
    @ExportedProperty
    public boolean preventEdgeOffset;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.isOverflowButton = false;
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super((ViewGroup.LayoutParams)param1LayoutParams);
      this.isOverflowButton = param1LayoutParams.isOverflowButton;
    }
  }
  
  private class MenuBuilderCallback implements MenuBuilder.Callback {
    final ActionMenuView this$0;
    
    public boolean onMenuItemSelected(MenuBuilder param1MenuBuilder, MenuItem param1MenuItem) {
      boolean bool;
      ActionMenuView.OnMenuItemClickListener onMenuItemClickListener = ActionMenuView.this.mOnMenuItemClickListener;
      if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(param1MenuItem)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onMenuModeChange(MenuBuilder param1MenuBuilder) {
      MenuBuilder.Callback callback = ActionMenuView.this.mMenuBuilderCallback;
      if (callback != null)
        callback.onMenuModeChange(param1MenuBuilder); 
    }
  }
  
  public static interface OnMenuItemClickListener {
    boolean onMenuItemClick(MenuItem param1MenuItem);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/ActionMenuView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */