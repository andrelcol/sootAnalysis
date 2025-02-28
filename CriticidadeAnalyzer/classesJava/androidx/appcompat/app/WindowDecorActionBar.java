package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.ViewPropertyAnimatorCompatSet;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
  private static final Interpolator sHideInterpolator = (Interpolator)new AccelerateInterpolator();
  
  private static final Interpolator sShowInterpolator = (Interpolator)new DecelerateInterpolator();
  
  ActionModeImpl mActionMode;
  
  ActionBarContainer mContainerView;
  
  boolean mContentAnimations;
  
  View mContentView;
  
  Context mContext;
  
  ActionBarContextView mContextView;
  
  private int mCurWindowVisibility;
  
  ViewPropertyAnimatorCompatSet mCurrentShowAnim;
  
  DecorToolbar mDecorToolbar;
  
  ActionMode mDeferredDestroyActionMode;
  
  ActionMode.Callback mDeferredModeDestroyCallback;
  
  private boolean mDisplayHomeAsUpSet;
  
  private boolean mHasEmbeddedTabs;
  
  boolean mHiddenByApp;
  
  boolean mHiddenBySystem;
  
  final ViewPropertyAnimatorListener mHideListener;
  
  boolean mHideOnContentScroll;
  
  private boolean mLastMenuVisibility;
  
  private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners;
  
  private boolean mNowShowing;
  
  ActionBarOverlayLayout mOverlayLayout;
  
  private boolean mShowHideAnimationEnabled;
  
  final ViewPropertyAnimatorListener mShowListener;
  
  private boolean mShowingForMode;
  
  ScrollingTabContainerView mTabScrollView;
  
  private Context mThemedContext;
  
  final ViewPropertyAnimatorUpdateListener mUpdateListener;
  
  public WindowDecorActionBar(Activity paramActivity, boolean paramBoolean) {
    new ArrayList();
    this.mMenuVisibilityListeners = new ArrayList<ActionBar.OnMenuVisibilityListener>();
    this.mCurWindowVisibility = 0;
    this.mContentAnimations = true;
    this.mNowShowing = true;
    this.mHideListener = (ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
        final WindowDecorActionBar this$0;
        
        public void onAnimationEnd(View param1View) {
          WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
          if (windowDecorActionBar.mContentAnimations) {
            View view = windowDecorActionBar.mContentView;
            if (view != null) {
              view.setTranslationY(0.0F);
              WindowDecorActionBar.this.mContainerView.setTranslationY(0.0F);
            } 
          } 
          WindowDecorActionBar.this.mContainerView.setVisibility(8);
          WindowDecorActionBar.this.mContainerView.setTransitioning(false);
          windowDecorActionBar = WindowDecorActionBar.this;
          windowDecorActionBar.mCurrentShowAnim = null;
          windowDecorActionBar.completeDeferredDestroyActionMode();
          ActionBarOverlayLayout actionBarOverlayLayout = WindowDecorActionBar.this.mOverlayLayout;
          if (actionBarOverlayLayout != null)
            ViewCompat.requestApplyInsets((View)actionBarOverlayLayout); 
        }
      };
    this.mShowListener = (ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
        final WindowDecorActionBar this$0;
        
        public void onAnimationEnd(View param1View) {
          WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
          windowDecorActionBar.mCurrentShowAnim = null;
          windowDecorActionBar.mContainerView.requestLayout();
        }
      };
    this.mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
        final WindowDecorActionBar this$0;
        
        public void onAnimationUpdate(View param1View) {
          ((View)WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
        }
      };
    View view = paramActivity.getWindow().getDecorView();
    init(view);
    if (!paramBoolean)
      this.mContentView = view.findViewById(16908290); 
  }
  
  public WindowDecorActionBar(Dialog paramDialog) {
    new ArrayList();
    this.mMenuVisibilityListeners = new ArrayList<ActionBar.OnMenuVisibilityListener>();
    this.mCurWindowVisibility = 0;
    this.mContentAnimations = true;
    this.mNowShowing = true;
    this.mHideListener = (ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
        final WindowDecorActionBar this$0;
        
        public void onAnimationEnd(View param1View) {
          WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
          if (windowDecorActionBar.mContentAnimations) {
            View view = windowDecorActionBar.mContentView;
            if (view != null) {
              view.setTranslationY(0.0F);
              WindowDecorActionBar.this.mContainerView.setTranslationY(0.0F);
            } 
          } 
          WindowDecorActionBar.this.mContainerView.setVisibility(8);
          WindowDecorActionBar.this.mContainerView.setTransitioning(false);
          windowDecorActionBar = WindowDecorActionBar.this;
          windowDecorActionBar.mCurrentShowAnim = null;
          windowDecorActionBar.completeDeferredDestroyActionMode();
          ActionBarOverlayLayout actionBarOverlayLayout = WindowDecorActionBar.this.mOverlayLayout;
          if (actionBarOverlayLayout != null)
            ViewCompat.requestApplyInsets((View)actionBarOverlayLayout); 
        }
      };
    this.mShowListener = (ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
        final WindowDecorActionBar this$0;
        
        public void onAnimationEnd(View param1View) {
          WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
          windowDecorActionBar.mCurrentShowAnim = null;
          windowDecorActionBar.mContainerView.requestLayout();
        }
      };
    this.mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
        final WindowDecorActionBar this$0;
        
        public void onAnimationUpdate(View param1View) {
          ((View)WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
        }
      };
    init(paramDialog.getWindow().getDecorView());
  }
  
  static boolean checkShowingFlags(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    return paramBoolean3 ? true : (!(paramBoolean1 || paramBoolean2));
  }
  
  private DecorToolbar getDecorToolbar(View paramView) {
    String str;
    if (paramView instanceof DecorToolbar)
      return (DecorToolbar)paramView; 
    if (paramView instanceof Toolbar)
      return ((Toolbar)paramView).getWrapper(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Can't make a decor toolbar out of ");
    if (paramView != null) {
      str = paramView.getClass().getSimpleName();
    } else {
      str = "null";
    } 
    stringBuilder.append(str);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private void hideForActionMode() {
    if (this.mShowingForMode) {
      this.mShowingForMode = false;
      ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
      if (actionBarOverlayLayout != null)
        actionBarOverlayLayout.setShowingForActionMode(false); 
      updateVisibility(false);
    } 
  }
  
  private void init(View paramView) {
    this.mOverlayLayout = (ActionBarOverlayLayout)paramView.findViewById(R.id.decor_content_parent);
    ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
    if (actionBarOverlayLayout != null)
      actionBarOverlayLayout.setActionBarVisibilityCallback(this); 
    this.mDecorToolbar = getDecorToolbar(paramView.findViewById(R.id.action_bar));
    this.mContextView = (ActionBarContextView)paramView.findViewById(R.id.action_context_bar);
    this.mContainerView = (ActionBarContainer)paramView.findViewById(R.id.action_bar_container);
    DecorToolbar decorToolbar = this.mDecorToolbar;
    if (decorToolbar != null && this.mContextView != null && this.mContainerView != null) {
      boolean bool;
      this.mContext = decorToolbar.getContext();
      if ((this.mDecorToolbar.getDisplayOptions() & 0x4) != 0) {
        i = 1;
      } else {
        i = 0;
      } 
      if (i)
        this.mDisplayHomeAsUpSet = true; 
      ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(this.mContext);
      if (actionBarPolicy.enableHomeButtonByDefault() || i) {
        bool = true;
      } else {
        bool = false;
      } 
      setHomeButtonEnabled(bool);
      setHasEmbeddedTabs(actionBarPolicy.hasEmbeddedTabs());
      TypedArray typedArray = this.mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
      if (typedArray.getBoolean(R.styleable.ActionBar_hideOnContentScroll, false))
        setHideOnContentScrollEnabled(true); 
      int i = typedArray.getDimensionPixelSize(R.styleable.ActionBar_elevation, 0);
      if (i != 0)
        setElevation(i); 
      typedArray.recycle();
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(WindowDecorActionBar.class.getSimpleName());
    stringBuilder.append(" can only be used ");
    stringBuilder.append("with a compatible window decor layout");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private void setHasEmbeddedTabs(boolean paramBoolean) {
    this.mHasEmbeddedTabs = paramBoolean;
    if (!this.mHasEmbeddedTabs) {
      this.mDecorToolbar.setEmbeddedTabView(null);
      this.mContainerView.setTabContainer(this.mTabScrollView);
    } else {
      this.mContainerView.setTabContainer(null);
      this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
    } 
    int i = getNavigationMode();
    boolean bool = true;
    if (i == 2) {
      i = 1;
    } else {
      i = 0;
    } 
    ScrollingTabContainerView scrollingTabContainerView = this.mTabScrollView;
    if (scrollingTabContainerView != null) {
      ActionBarOverlayLayout actionBarOverlayLayout1;
      if (i != 0) {
        scrollingTabContainerView.setVisibility(0);
        actionBarOverlayLayout1 = this.mOverlayLayout;
        if (actionBarOverlayLayout1 != null)
          ViewCompat.requestApplyInsets((View)actionBarOverlayLayout1); 
      } else {
        actionBarOverlayLayout1.setVisibility(8);
      } 
    } 
    DecorToolbar decorToolbar = this.mDecorToolbar;
    if (!this.mHasEmbeddedTabs && i != 0) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    decorToolbar.setCollapsible(paramBoolean);
    ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
    if (!this.mHasEmbeddedTabs && i != 0) {
      paramBoolean = bool;
    } else {
      paramBoolean = false;
    } 
    actionBarOverlayLayout.setHasNonEmbeddedTabs(paramBoolean);
  }
  
  private boolean shouldAnimateContextView() {
    return ViewCompat.isLaidOut((View)this.mContainerView);
  }
  
  private void showForActionMode() {
    if (!this.mShowingForMode) {
      this.mShowingForMode = true;
      ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
      if (actionBarOverlayLayout != null)
        actionBarOverlayLayout.setShowingForActionMode(true); 
      updateVisibility(false);
    } 
  }
  
  private void updateVisibility(boolean paramBoolean) {
    if (checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
      if (!this.mNowShowing) {
        this.mNowShowing = true;
        doShow(paramBoolean);
      } 
    } else if (this.mNowShowing) {
      this.mNowShowing = false;
      doHide(paramBoolean);
    } 
  }
  
  public void animateToMode(boolean paramBoolean) {
    if (paramBoolean) {
      showForActionMode();
    } else {
      hideForActionMode();
    } 
    if (shouldAnimateContextView()) {
      ViewPropertyAnimatorCompat viewPropertyAnimatorCompat1;
      ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2;
      if (paramBoolean) {
        viewPropertyAnimatorCompat1 = this.mDecorToolbar.setupAnimatorToVisibility(4, 100L);
        viewPropertyAnimatorCompat2 = this.mContextView.setupAnimatorToVisibility(0, 200L);
      } else {
        viewPropertyAnimatorCompat2 = this.mDecorToolbar.setupAnimatorToVisibility(0, 200L);
        viewPropertyAnimatorCompat1 = this.mContextView.setupAnimatorToVisibility(8, 100L);
      } 
      ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
      viewPropertyAnimatorCompatSet.playSequentially(viewPropertyAnimatorCompat1, viewPropertyAnimatorCompat2);
      viewPropertyAnimatorCompatSet.start();
    } else if (paramBoolean) {
      this.mDecorToolbar.setVisibility(4);
      this.mContextView.setVisibility(0);
    } else {
      this.mDecorToolbar.setVisibility(0);
      this.mContextView.setVisibility(8);
    } 
  }
  
  public boolean collapseActionView() {
    DecorToolbar decorToolbar = this.mDecorToolbar;
    if (decorToolbar != null && decorToolbar.hasExpandedActionView()) {
      this.mDecorToolbar.collapseActionView();
      return true;
    } 
    return false;
  }
  
  void completeDeferredDestroyActionMode() {
    ActionMode.Callback callback = this.mDeferredModeDestroyCallback;
    if (callback != null) {
      callback.onDestroyActionMode(this.mDeferredDestroyActionMode);
      this.mDeferredDestroyActionMode = null;
      this.mDeferredModeDestroyCallback = null;
    } 
  }
  
  public void dispatchMenuVisibilityChanged(boolean paramBoolean) {
    if (paramBoolean == this.mLastMenuVisibility)
      return; 
    this.mLastMenuVisibility = paramBoolean;
    int i = this.mMenuVisibilityListeners.size();
    for (byte b = 0; b < i; b++)
      ((ActionBar.OnMenuVisibilityListener)this.mMenuVisibilityListeners.get(b)).onMenuVisibilityChanged(paramBoolean); 
  }
  
  public void doHide(boolean paramBoolean) {
    ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentShowAnim;
    if (viewPropertyAnimatorCompatSet != null)
      viewPropertyAnimatorCompatSet.cancel(); 
    if (this.mCurWindowVisibility == 0 && (this.mShowHideAnimationEnabled || paramBoolean)) {
      this.mContainerView.setAlpha(1.0F);
      this.mContainerView.setTransitioning(true);
      viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
      float f2 = -this.mContainerView.getHeight();
      float f1 = f2;
      if (paramBoolean) {
        int[] arrayOfInt = new int[2];
        arrayOfInt[0] = 0;
        arrayOfInt[1] = 0;
        this.mContainerView.getLocationInWindow(arrayOfInt);
        f1 = f2 - arrayOfInt[1];
      } 
      ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this.mContainerView);
      viewPropertyAnimatorCompat.translationY(f1);
      viewPropertyAnimatorCompat.setUpdateListener(this.mUpdateListener);
      viewPropertyAnimatorCompatSet.play(viewPropertyAnimatorCompat);
      if (this.mContentAnimations) {
        View view = this.mContentView;
        if (view != null) {
          ViewPropertyAnimatorCompat viewPropertyAnimatorCompat1 = ViewCompat.animate(view);
          viewPropertyAnimatorCompat1.translationY(f1);
          viewPropertyAnimatorCompatSet.play(viewPropertyAnimatorCompat1);
        } 
      } 
      viewPropertyAnimatorCompatSet.setInterpolator(sHideInterpolator);
      viewPropertyAnimatorCompatSet.setDuration(250L);
      viewPropertyAnimatorCompatSet.setListener(this.mHideListener);
      this.mCurrentShowAnim = viewPropertyAnimatorCompatSet;
      viewPropertyAnimatorCompatSet.start();
    } else {
      this.mHideListener.onAnimationEnd(null);
    } 
  }
  
  public void doShow(boolean paramBoolean) {
    ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentShowAnim;
    if (viewPropertyAnimatorCompatSet != null)
      viewPropertyAnimatorCompatSet.cancel(); 
    this.mContainerView.setVisibility(0);
    if (this.mCurWindowVisibility == 0 && (this.mShowHideAnimationEnabled || paramBoolean)) {
      this.mContainerView.setTranslationY(0.0F);
      float f2 = -this.mContainerView.getHeight();
      float f1 = f2;
      if (paramBoolean) {
        int[] arrayOfInt = new int[2];
        arrayOfInt[0] = 0;
        arrayOfInt[1] = 0;
        this.mContainerView.getLocationInWindow(arrayOfInt);
        f1 = f2 - arrayOfInt[1];
      } 
      this.mContainerView.setTranslationY(f1);
      viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
      ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)this.mContainerView);
      viewPropertyAnimatorCompat.translationY(0.0F);
      viewPropertyAnimatorCompat.setUpdateListener(this.mUpdateListener);
      viewPropertyAnimatorCompatSet.play(viewPropertyAnimatorCompat);
      if (this.mContentAnimations) {
        View view = this.mContentView;
        if (view != null) {
          view.setTranslationY(f1);
          ViewPropertyAnimatorCompat viewPropertyAnimatorCompat1 = ViewCompat.animate(this.mContentView);
          viewPropertyAnimatorCompat1.translationY(0.0F);
          viewPropertyAnimatorCompatSet.play(viewPropertyAnimatorCompat1);
        } 
      } 
      viewPropertyAnimatorCompatSet.setInterpolator(sShowInterpolator);
      viewPropertyAnimatorCompatSet.setDuration(250L);
      viewPropertyAnimatorCompatSet.setListener(this.mShowListener);
      this.mCurrentShowAnim = viewPropertyAnimatorCompatSet;
      viewPropertyAnimatorCompatSet.start();
    } else {
      this.mContainerView.setAlpha(1.0F);
      this.mContainerView.setTranslationY(0.0F);
      if (this.mContentAnimations) {
        View view = this.mContentView;
        if (view != null)
          view.setTranslationY(0.0F); 
      } 
      this.mShowListener.onAnimationEnd(null);
    } 
    ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
    if (actionBarOverlayLayout != null)
      ViewCompat.requestApplyInsets((View)actionBarOverlayLayout); 
  }
  
  public void enableContentAnimations(boolean paramBoolean) {
    this.mContentAnimations = paramBoolean;
  }
  
  public int getDisplayOptions() {
    return this.mDecorToolbar.getDisplayOptions();
  }
  
  public int getNavigationMode() {
    return this.mDecorToolbar.getNavigationMode();
  }
  
  public Context getThemedContext() {
    if (this.mThemedContext == null) {
      TypedValue typedValue = new TypedValue();
      this.mContext.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
      int i = typedValue.resourceId;
      if (i != 0) {
        this.mThemedContext = (Context)new ContextThemeWrapper(this.mContext, i);
      } else {
        this.mThemedContext = this.mContext;
      } 
    } 
    return this.mThemedContext;
  }
  
  public void hideForSystem() {
    if (!this.mHiddenBySystem) {
      this.mHiddenBySystem = true;
      updateVisibility(true);
    } 
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
  }
  
  public void onContentScrollStarted() {
    ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentShowAnim;
    if (viewPropertyAnimatorCompatSet != null) {
      viewPropertyAnimatorCompatSet.cancel();
      this.mCurrentShowAnim = null;
    } 
  }
  
  public void onContentScrollStopped() {}
  
  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent) {
    ActionModeImpl actionModeImpl = this.mActionMode;
    if (actionModeImpl == null)
      return false; 
    Menu menu = actionModeImpl.getMenu();
    if (menu != null) {
      if (paramKeyEvent != null) {
        i = paramKeyEvent.getDeviceId();
      } else {
        i = -1;
      } 
      int i = KeyCharacterMap.load(i).getKeyboardType();
      boolean bool = true;
      if (i == 1)
        bool = false; 
      menu.setQwertyMode(bool);
      return menu.performShortcut(paramInt, paramKeyEvent, 0);
    } 
    return false;
  }
  
  public void onWindowVisibilityChanged(int paramInt) {
    this.mCurWindowVisibility = paramInt;
  }
  
  public void setDefaultDisplayHomeAsUpEnabled(boolean paramBoolean) {
    if (!this.mDisplayHomeAsUpSet)
      setDisplayHomeAsUpEnabled(paramBoolean); 
  }
  
  public void setDisplayHomeAsUpEnabled(boolean paramBoolean) {
    boolean bool;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    setDisplayOptions(bool, 4);
  }
  
  public void setDisplayOptions(int paramInt1, int paramInt2) {
    int i = this.mDecorToolbar.getDisplayOptions();
    if ((paramInt2 & 0x4) != 0)
      this.mDisplayHomeAsUpSet = true; 
    this.mDecorToolbar.setDisplayOptions(paramInt1 & paramInt2 | (paramInt2 ^ 0xFFFFFFFF) & i);
  }
  
  public void setElevation(float paramFloat) {
    ViewCompat.setElevation((View)this.mContainerView, paramFloat);
  }
  
  public void setHideOnContentScrollEnabled(boolean paramBoolean) {
    if (!paramBoolean || this.mOverlayLayout.isInOverlayMode()) {
      this.mHideOnContentScroll = paramBoolean;
      this.mOverlayLayout.setHideOnContentScrollEnabled(paramBoolean);
      return;
    } 
    throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
  }
  
  public void setHomeButtonEnabled(boolean paramBoolean) {
    this.mDecorToolbar.setHomeButtonEnabled(paramBoolean);
  }
  
  public void setShowHideAnimationEnabled(boolean paramBoolean) {
    this.mShowHideAnimationEnabled = paramBoolean;
    if (!paramBoolean) {
      ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentShowAnim;
      if (viewPropertyAnimatorCompatSet != null)
        viewPropertyAnimatorCompatSet.cancel(); 
    } 
  }
  
  public void setWindowTitle(CharSequence paramCharSequence) {
    this.mDecorToolbar.setWindowTitle(paramCharSequence);
  }
  
  public void showForSystem() {
    if (this.mHiddenBySystem) {
      this.mHiddenBySystem = false;
      updateVisibility(true);
    } 
  }
  
  public ActionMode startActionMode(ActionMode.Callback paramCallback) {
    ActionModeImpl actionModeImpl2 = this.mActionMode;
    if (actionModeImpl2 != null)
      actionModeImpl2.finish(); 
    this.mOverlayLayout.setHideOnContentScrollEnabled(false);
    this.mContextView.killMode();
    ActionModeImpl actionModeImpl1 = new ActionModeImpl(this.mContextView.getContext(), paramCallback);
    if (actionModeImpl1.dispatchOnCreate()) {
      this.mActionMode = actionModeImpl1;
      actionModeImpl1.invalidate();
      this.mContextView.initForMode(actionModeImpl1);
      animateToMode(true);
      this.mContextView.sendAccessibilityEvent(32);
      return actionModeImpl1;
    } 
    return null;
  }
  
  public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
    private final Context mActionModeContext;
    
    private ActionMode.Callback mCallback;
    
    private WeakReference<View> mCustomView;
    
    private final MenuBuilder mMenu;
    
    final WindowDecorActionBar this$0;
    
    public ActionModeImpl(Context param1Context, ActionMode.Callback param1Callback) {
      this.mActionModeContext = param1Context;
      this.mCallback = param1Callback;
      MenuBuilder menuBuilder = new MenuBuilder(param1Context);
      menuBuilder.setDefaultShowAsAction(1);
      this.mMenu = menuBuilder;
      this.mMenu.setCallback(this);
    }
    
    public boolean dispatchOnCreate() {
      this.mMenu.stopDispatchingItemsChanged();
      try {
        return this.mCallback.onCreateActionMode(this, (Menu)this.mMenu);
      } finally {
        this.mMenu.startDispatchingItemsChanged();
      } 
    }
    
    public void finish() {
      WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
      if (windowDecorActionBar.mActionMode != this)
        return; 
      if (!WindowDecorActionBar.checkShowingFlags(windowDecorActionBar.mHiddenByApp, windowDecorActionBar.mHiddenBySystem, false)) {
        windowDecorActionBar = WindowDecorActionBar.this;
        windowDecorActionBar.mDeferredDestroyActionMode = this;
        windowDecorActionBar.mDeferredModeDestroyCallback = this.mCallback;
      } else {
        this.mCallback.onDestroyActionMode(this);
      } 
      this.mCallback = null;
      WindowDecorActionBar.this.animateToMode(false);
      WindowDecorActionBar.this.mContextView.closeMode();
      WindowDecorActionBar.this.mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
      windowDecorActionBar = WindowDecorActionBar.this;
      windowDecorActionBar.mOverlayLayout.setHideOnContentScrollEnabled(windowDecorActionBar.mHideOnContentScroll);
      WindowDecorActionBar.this.mActionMode = null;
    }
    
    public View getCustomView() {
      WeakReference<View> weakReference = this.mCustomView;
      if (weakReference != null) {
        View view = weakReference.get();
      } else {
        weakReference = null;
      } 
      return (View)weakReference;
    }
    
    public Menu getMenu() {
      return (Menu)this.mMenu;
    }
    
    public MenuInflater getMenuInflater() {
      return (MenuInflater)new SupportMenuInflater(this.mActionModeContext);
    }
    
    public CharSequence getSubtitle() {
      return WindowDecorActionBar.this.mContextView.getSubtitle();
    }
    
    public CharSequence getTitle() {
      return WindowDecorActionBar.this.mContextView.getTitle();
    }
    
    public void invalidate() {
      if (WindowDecorActionBar.this.mActionMode != this)
        return; 
      this.mMenu.stopDispatchingItemsChanged();
      try {
        this.mCallback.onPrepareActionMode(this, (Menu)this.mMenu);
        return;
      } finally {
        this.mMenu.startDispatchingItemsChanged();
      } 
    }
    
    public boolean isTitleOptional() {
      return WindowDecorActionBar.this.mContextView.isTitleOptional();
    }
    
    public boolean onMenuItemSelected(MenuBuilder param1MenuBuilder, MenuItem param1MenuItem) {
      ActionMode.Callback callback = this.mCallback;
      return (callback != null) ? callback.onActionItemClicked(this, param1MenuItem) : false;
    }
    
    public void onMenuModeChange(MenuBuilder param1MenuBuilder) {
      if (this.mCallback == null)
        return; 
      invalidate();
      WindowDecorActionBar.this.mContextView.showOverflowMenu();
    }
    
    public void setCustomView(View param1View) {
      WindowDecorActionBar.this.mContextView.setCustomView(param1View);
      this.mCustomView = new WeakReference<View>(param1View);
    }
    
    public void setSubtitle(int param1Int) {
      setSubtitle(WindowDecorActionBar.this.mContext.getResources().getString(param1Int));
    }
    
    public void setSubtitle(CharSequence param1CharSequence) {
      WindowDecorActionBar.this.mContextView.setSubtitle(param1CharSequence);
    }
    
    public void setTitle(int param1Int) {
      setTitle(WindowDecorActionBar.this.mContext.getResources().getString(param1Int));
    }
    
    public void setTitle(CharSequence param1CharSequence) {
      WindowDecorActionBar.this.mContextView.setTitle(param1CharSequence);
    }
    
    public void setTitleOptionalHint(boolean param1Boolean) {
      super.setTitleOptionalHint(param1Boolean);
      WindowDecorActionBar.this.mContextView.setTitleOptional(param1Boolean);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/app/WindowDecorActionBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */