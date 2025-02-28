package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;

public class ToolbarWidgetWrapper implements DecorToolbar {
  private ActionMenuPresenter mActionMenuPresenter;
  
  private View mCustomView;
  
  private int mDefaultNavigationContentDescription;
  
  private Drawable mDefaultNavigationIcon;
  
  private int mDisplayOpts;
  
  private CharSequence mHomeDescription;
  
  private Drawable mIcon;
  
  private Drawable mLogo;
  
  boolean mMenuPrepared;
  
  private Drawable mNavIcon;
  
  private int mNavigationMode;
  
  private CharSequence mSubtitle;
  
  private View mTabView;
  
  CharSequence mTitle;
  
  private boolean mTitleSet;
  
  Toolbar mToolbar;
  
  Window.Callback mWindowCallback;
  
  public ToolbarWidgetWrapper(Toolbar paramToolbar, boolean paramBoolean) {
    this(paramToolbar, paramBoolean, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_material);
  }
  
  public ToolbarWidgetWrapper(Toolbar paramToolbar, boolean paramBoolean, int paramInt1, int paramInt2) {
    boolean bool;
    this.mNavigationMode = 0;
    this.mDefaultNavigationContentDescription = 0;
    this.mToolbar = paramToolbar;
    this.mTitle = paramToolbar.getTitle();
    this.mSubtitle = paramToolbar.getSubtitle();
    if (this.mTitle != null) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mTitleSet = bool;
    this.mNavIcon = paramToolbar.getNavigationIcon();
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramToolbar.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
    this.mDefaultNavigationIcon = tintTypedArray.getDrawable(R.styleable.ActionBar_homeAsUpIndicator);
    if (paramBoolean) {
      CharSequence charSequence = tintTypedArray.getText(R.styleable.ActionBar_title);
      if (!TextUtils.isEmpty(charSequence))
        setTitle(charSequence); 
      charSequence = tintTypedArray.getText(R.styleable.ActionBar_subtitle);
      if (!TextUtils.isEmpty(charSequence))
        setSubtitle(charSequence); 
      Drawable drawable = tintTypedArray.getDrawable(R.styleable.ActionBar_logo);
      if (drawable != null)
        setLogo(drawable); 
      drawable = tintTypedArray.getDrawable(R.styleable.ActionBar_icon);
      if (drawable != null)
        setIcon(drawable); 
      if (this.mNavIcon == null) {
        drawable = this.mDefaultNavigationIcon;
        if (drawable != null)
          setNavigationIcon(drawable); 
      } 
      setDisplayOptions(tintTypedArray.getInt(R.styleable.ActionBar_displayOptions, 0));
      paramInt2 = tintTypedArray.getResourceId(R.styleable.ActionBar_customNavigationLayout, 0);
      if (paramInt2 != 0) {
        setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(paramInt2, this.mToolbar, false));
        setDisplayOptions(this.mDisplayOpts | 0x10);
      } 
      paramInt2 = tintTypedArray.getLayoutDimension(R.styleable.ActionBar_height, 0);
      if (paramInt2 > 0) {
        ViewGroup.LayoutParams layoutParams = this.mToolbar.getLayoutParams();
        layoutParams.height = paramInt2;
        this.mToolbar.setLayoutParams(layoutParams);
      } 
      paramInt2 = tintTypedArray.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetStart, -1);
      int i = tintTypedArray.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetEnd, -1);
      if (paramInt2 >= 0 || i >= 0)
        this.mToolbar.setContentInsetsRelative(Math.max(paramInt2, 0), Math.max(i, 0)); 
      paramInt2 = tintTypedArray.getResourceId(R.styleable.ActionBar_titleTextStyle, 0);
      if (paramInt2 != 0) {
        Toolbar toolbar = this.mToolbar;
        toolbar.setTitleTextAppearance(toolbar.getContext(), paramInt2);
      } 
      paramInt2 = tintTypedArray.getResourceId(R.styleable.ActionBar_subtitleTextStyle, 0);
      if (paramInt2 != 0) {
        Toolbar toolbar = this.mToolbar;
        toolbar.setSubtitleTextAppearance(toolbar.getContext(), paramInt2);
      } 
      paramInt2 = tintTypedArray.getResourceId(R.styleable.ActionBar_popupTheme, 0);
      if (paramInt2 != 0)
        this.mToolbar.setPopupTheme(paramInt2); 
    } else {
      this.mDisplayOpts = detectDisplayOptions();
    } 
    tintTypedArray.recycle();
    setDefaultNavigationContentDescription(paramInt1);
    this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
    this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
          final ActionMenuItem mNavItem = new ActionMenuItem(ToolbarWidgetWrapper.this.mToolbar.getContext(), 0, 16908332, 0, 0, ToolbarWidgetWrapper.this.mTitle);
          
          final ToolbarWidgetWrapper this$0;
          
          public void onClick(View param1View) {
            ToolbarWidgetWrapper toolbarWidgetWrapper = ToolbarWidgetWrapper.this;
            Window.Callback callback = toolbarWidgetWrapper.mWindowCallback;
            if (callback != null && toolbarWidgetWrapper.mMenuPrepared)
              callback.onMenuItemSelected(0, (MenuItem)this.mNavItem); 
          }
        });
  }
  
  private int detectDisplayOptions() {
    byte b;
    if (this.mToolbar.getNavigationIcon() != null) {
      b = 15;
      this.mDefaultNavigationIcon = this.mToolbar.getNavigationIcon();
    } else {
      b = 11;
    } 
    return b;
  }
  
  private void setTitleInt(CharSequence paramCharSequence) {
    this.mTitle = paramCharSequence;
    if ((this.mDisplayOpts & 0x8) != 0)
      this.mToolbar.setTitle(paramCharSequence); 
  }
  
  private void updateHomeAccessibility() {
    if ((this.mDisplayOpts & 0x4) != 0)
      if (TextUtils.isEmpty(this.mHomeDescription)) {
        this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
      } else {
        this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
      }  
  }
  
  private void updateNavigationIcon() {
    if ((this.mDisplayOpts & 0x4) != 0) {
      Toolbar toolbar = this.mToolbar;
      Drawable drawable = this.mNavIcon;
      if (drawable == null)
        drawable = this.mDefaultNavigationIcon; 
      toolbar.setNavigationIcon(drawable);
    } else {
      this.mToolbar.setNavigationIcon((Drawable)null);
    } 
  }
  
  private void updateToolbarLogo() {
    Drawable drawable;
    int i = this.mDisplayOpts;
    if ((i & 0x2) != 0) {
      if ((i & 0x1) != 0) {
        drawable = this.mLogo;
        if (drawable == null)
          drawable = this.mIcon; 
      } else {
        drawable = this.mIcon;
      } 
    } else {
      drawable = null;
    } 
    this.mToolbar.setLogo(drawable);
  }
  
  public boolean canShowOverflowMenu() {
    return this.mToolbar.canShowOverflowMenu();
  }
  
  public void collapseActionView() {
    this.mToolbar.collapseActionView();
  }
  
  public void dismissPopupMenus() {
    this.mToolbar.dismissPopupMenus();
  }
  
  public Context getContext() {
    return this.mToolbar.getContext();
  }
  
  public int getDisplayOptions() {
    return this.mDisplayOpts;
  }
  
  public int getNavigationMode() {
    return this.mNavigationMode;
  }
  
  public CharSequence getTitle() {
    return this.mToolbar.getTitle();
  }
  
  public ViewGroup getViewGroup() {
    return this.mToolbar;
  }
  
  public boolean hasExpandedActionView() {
    return this.mToolbar.hasExpandedActionView();
  }
  
  public boolean hideOverflowMenu() {
    return this.mToolbar.hideOverflowMenu();
  }
  
  public void initIndeterminateProgress() {}
  
  public void initProgress() {}
  
  public boolean isOverflowMenuShowPending() {
    return this.mToolbar.isOverflowMenuShowPending();
  }
  
  public boolean isOverflowMenuShowing() {
    return this.mToolbar.isOverflowMenuShowing();
  }
  
  public void setCollapsible(boolean paramBoolean) {
    this.mToolbar.setCollapsible(paramBoolean);
  }
  
  public void setCustomView(View paramView) {
    View view = this.mCustomView;
    if (view != null && (this.mDisplayOpts & 0x10) != 0)
      this.mToolbar.removeView(view); 
    this.mCustomView = paramView;
    if (paramView != null && (this.mDisplayOpts & 0x10) != 0)
      this.mToolbar.addView(this.mCustomView); 
  }
  
  public void setDefaultNavigationContentDescription(int paramInt) {
    if (paramInt == this.mDefaultNavigationContentDescription)
      return; 
    this.mDefaultNavigationContentDescription = paramInt;
    if (TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription()))
      setNavigationContentDescription(this.mDefaultNavigationContentDescription); 
  }
  
  public void setDisplayOptions(int paramInt) {
    int i = this.mDisplayOpts ^ paramInt;
    this.mDisplayOpts = paramInt;
    if (i != 0) {
      if ((i & 0x4) != 0) {
        if ((paramInt & 0x4) != 0)
          updateHomeAccessibility(); 
        updateNavigationIcon();
      } 
      if ((i & 0x3) != 0)
        updateToolbarLogo(); 
      if ((i & 0x8) != 0)
        if ((paramInt & 0x8) != 0) {
          this.mToolbar.setTitle(this.mTitle);
          this.mToolbar.setSubtitle(this.mSubtitle);
        } else {
          this.mToolbar.setTitle((CharSequence)null);
          this.mToolbar.setSubtitle((CharSequence)null);
        }  
      if ((i & 0x10) != 0) {
        View view = this.mCustomView;
        if (view != null)
          if ((paramInt & 0x10) != 0) {
            this.mToolbar.addView(view);
          } else {
            this.mToolbar.removeView(view);
          }  
      } 
    } 
  }
  
  public void setEmbeddedTabView(ScrollingTabContainerView paramScrollingTabContainerView) {
    View view = this.mTabView;
    if (view != null) {
      ViewParent viewParent = view.getParent();
      Toolbar toolbar = this.mToolbar;
      if (viewParent == toolbar)
        toolbar.removeView(this.mTabView); 
    } 
    this.mTabView = (View)paramScrollingTabContainerView;
    if (paramScrollingTabContainerView != null && this.mNavigationMode == 2) {
      this.mToolbar.addView(this.mTabView, 0);
      Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams)this.mTabView.getLayoutParams();
      ((ViewGroup.MarginLayoutParams)layoutParams).width = -2;
      ((ViewGroup.MarginLayoutParams)layoutParams).height = -2;
      layoutParams.gravity = 8388691;
      paramScrollingTabContainerView.setAllowCollapse(true);
    } 
  }
  
  public void setHomeButtonEnabled(boolean paramBoolean) {}
  
  public void setIcon(int paramInt) {
    Drawable drawable;
    if (paramInt != 0) {
      drawable = AppCompatResources.getDrawable(getContext(), paramInt);
    } else {
      drawable = null;
    } 
    setIcon(drawable);
  }
  
  public void setIcon(Drawable paramDrawable) {
    this.mIcon = paramDrawable;
    updateToolbarLogo();
  }
  
  public void setLogo(int paramInt) {
    Drawable drawable;
    if (paramInt != 0) {
      drawable = AppCompatResources.getDrawable(getContext(), paramInt);
    } else {
      drawable = null;
    } 
    setLogo(drawable);
  }
  
  public void setLogo(Drawable paramDrawable) {
    this.mLogo = paramDrawable;
    updateToolbarLogo();
  }
  
  public void setMenu(Menu paramMenu, MenuPresenter.Callback paramCallback) {
    if (this.mActionMenuPresenter == null) {
      this.mActionMenuPresenter = new ActionMenuPresenter(this.mToolbar.getContext());
      this.mActionMenuPresenter.setId(R.id.action_menu_presenter);
    } 
    this.mActionMenuPresenter.setCallback(paramCallback);
    this.mToolbar.setMenu((MenuBuilder)paramMenu, this.mActionMenuPresenter);
  }
  
  public void setMenuPrepared() {
    this.mMenuPrepared = true;
  }
  
  public void setNavigationContentDescription(int paramInt) {
    String str;
    if (paramInt == 0) {
      str = null;
    } else {
      str = getContext().getString(paramInt);
    } 
    setNavigationContentDescription(str);
  }
  
  public void setNavigationContentDescription(CharSequence paramCharSequence) {
    this.mHomeDescription = paramCharSequence;
    updateHomeAccessibility();
  }
  
  public void setNavigationIcon(Drawable paramDrawable) {
    this.mNavIcon = paramDrawable;
    updateNavigationIcon();
  }
  
  public void setSubtitle(CharSequence paramCharSequence) {
    this.mSubtitle = paramCharSequence;
    if ((this.mDisplayOpts & 0x8) != 0)
      this.mToolbar.setSubtitle(paramCharSequence); 
  }
  
  public void setTitle(CharSequence paramCharSequence) {
    this.mTitleSet = true;
    setTitleInt(paramCharSequence);
  }
  
  public void setVisibility(int paramInt) {
    this.mToolbar.setVisibility(paramInt);
  }
  
  public void setWindowCallback(Window.Callback paramCallback) {
    this.mWindowCallback = paramCallback;
  }
  
  public void setWindowTitle(CharSequence paramCharSequence) {
    if (!this.mTitleSet)
      setTitleInt(paramCharSequence); 
  }
  
  public ViewPropertyAnimatorCompat setupAnimatorToVisibility(final int visibility, long paramLong) {
    float f;
    ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this.mToolbar);
    if (visibility == 0) {
      f = 1.0F;
    } else {
      f = 0.0F;
    } 
    viewPropertyAnimatorCompat.alpha(f);
    viewPropertyAnimatorCompat.setDuration(paramLong);
    viewPropertyAnimatorCompat.setListener((ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
          private boolean mCanceled = false;
          
          final ToolbarWidgetWrapper this$0;
          
          final int val$visibility;
          
          public void onAnimationCancel(View param1View) {
            this.mCanceled = true;
          }
          
          public void onAnimationEnd(View param1View) {
            if (!this.mCanceled)
              ToolbarWidgetWrapper.this.mToolbar.setVisibility(visibility); 
          }
          
          public void onAnimationStart(View param1View) {
            ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
          }
        });
    return viewPropertyAnimatorCompat;
  }
  
  public boolean showOverflowMenu() {
    return this.mToolbar.showOverflowMenu();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/ToolbarWidgetWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */