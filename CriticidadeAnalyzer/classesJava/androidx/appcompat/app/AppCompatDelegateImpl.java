package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.FitWindowsViewGroup;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.appcompat.widget.ViewUtils;
import androidx.core.app.NavUtils;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImpl extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
  private static final boolean IS_PRE_LOLLIPOP;
  
  private static boolean sInstalledExceptionHandler;
  
  private static final int[] sWindowBackgroundStyleable = new int[] { 16842836 };
  
  ActionBar mActionBar;
  
  private ActionMenuPresenterCallback mActionMenuPresenterCallback;
  
  ActionMode mActionMode;
  
  PopupWindow mActionModePopup;
  
  ActionBarContextView mActionModeView;
  
  final AppCompatCallback mAppCompatCallback;
  
  private AppCompatViewInflater mAppCompatViewInflater;
  
  final Window.Callback mAppCompatWindowCallback;
  
  private boolean mApplyDayNightCalled;
  
  private AutoNightModeManager mAutoNightModeManager;
  
  private boolean mClosingActionMenu;
  
  final Context mContext;
  
  private DecorContentParent mDecorContentParent;
  
  private boolean mEnableDefaultActionBarUp;
  
  ViewPropertyAnimatorCompat mFadeAnim = null;
  
  private boolean mFeatureIndeterminateProgress;
  
  private boolean mFeatureProgress;
  
  private boolean mHandleNativeActionModes = true;
  
  boolean mHasActionBar;
  
  int mInvalidatePanelMenuFeatures;
  
  boolean mInvalidatePanelMenuPosted;
  
  private final Runnable mInvalidatePanelMenuRunnable = new Runnable() {
      final AppCompatDelegateImpl this$0;
      
      public void run() {
        AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
        if ((appCompatDelegateImpl.mInvalidatePanelMenuFeatures & 0x1) != 0)
          appCompatDelegateImpl.doInvalidatePanelMenu(0); 
        appCompatDelegateImpl = AppCompatDelegateImpl.this;
        if ((appCompatDelegateImpl.mInvalidatePanelMenuFeatures & 0x1000) != 0)
          appCompatDelegateImpl.doInvalidatePanelMenu(108); 
        appCompatDelegateImpl = AppCompatDelegateImpl.this;
        appCompatDelegateImpl.mInvalidatePanelMenuPosted = false;
        appCompatDelegateImpl.mInvalidatePanelMenuFeatures = 0;
      }
    };
  
  boolean mIsDestroyed;
  
  boolean mIsFloating;
  
  private int mLocalNightMode = -100;
  
  private boolean mLongPressBackDown;
  
  MenuInflater mMenuInflater;
  
  final Window.Callback mOriginalWindowCallback;
  
  boolean mOverlayActionBar;
  
  boolean mOverlayActionMode;
  
  private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
  
  private PanelFeatureState[] mPanels;
  
  private PanelFeatureState mPreparedPanel;
  
  Runnable mShowActionModePopup;
  
  private View mStatusGuard;
  
  private ViewGroup mSubDecor;
  
  private boolean mSubDecorInstalled;
  
  private Rect mTempRect1;
  
  private Rect mTempRect2;
  
  private CharSequence mTitle;
  
  private TextView mTitleView;
  
  final Window mWindow;
  
  boolean mWindowNoTitle;
  
  static {
    if (IS_PRE_LOLLIPOP && !sInstalledExceptionHandler) {
      Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()) {
            final Thread.UncaughtExceptionHandler val$defHandler;
            
            private boolean shouldWrapException(Throwable param1Throwable) {
              boolean bool1 = param1Throwable instanceof Resources.NotFoundException;
              boolean bool = false;
              null = bool;
              if (bool1) {
                String str = param1Throwable.getMessage();
                null = bool;
                if (str != null) {
                  if (!str.contains("drawable")) {
                    null = bool;
                    return str.contains("Drawable") ? true : null;
                  } 
                } else {
                  return null;
                } 
              } else {
                return null;
              } 
              return true;
            }
            
            public void uncaughtException(Thread param1Thread, Throwable param1Throwable) {
              if (shouldWrapException(param1Throwable)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(param1Throwable.getMessage());
                stringBuilder.append(". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                Resources.NotFoundException notFoundException = new Resources.NotFoundException(stringBuilder.toString());
                notFoundException.initCause(param1Throwable.getCause());
                notFoundException.setStackTrace(param1Throwable.getStackTrace());
                defHandler.uncaughtException(param1Thread, (Throwable)notFoundException);
              } else {
                defHandler.uncaughtException(param1Thread, param1Throwable);
              } 
            }
          });
      sInstalledExceptionHandler = true;
    } 
  }
  
  AppCompatDelegateImpl(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback) {
    this.mContext = paramContext;
    this.mWindow = paramWindow;
    this.mAppCompatCallback = paramAppCompatCallback;
    this.mOriginalWindowCallback = this.mWindow.getCallback();
    Window.Callback callback = this.mOriginalWindowCallback;
    if (!(callback instanceof AppCompatWindowCallback)) {
      this.mAppCompatWindowCallback = (Window.Callback)new AppCompatWindowCallback(callback);
      this.mWindow.setCallback(this.mAppCompatWindowCallback);
      TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, null, sWindowBackgroundStyleable);
      Drawable drawable = tintTypedArray.getDrawableIfKnown(0);
      if (drawable != null)
        this.mWindow.setBackgroundDrawable(drawable); 
      tintTypedArray.recycle();
      return;
    } 
    throw new IllegalStateException("AppCompat has already installed itself into the Window");
  }
  
  private void applyFixedSizeWindow() {
    ContentFrameLayout contentFrameLayout = (ContentFrameLayout)this.mSubDecor.findViewById(16908290);
    View view = this.mWindow.getDecorView();
    contentFrameLayout.setDecorPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    TypedArray typedArray = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
    typedArray.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
    typedArray.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
    if (typedArray.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor))
      typedArray.getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor()); 
    if (typedArray.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor))
      typedArray.getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor()); 
    if (typedArray.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor))
      typedArray.getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor()); 
    if (typedArray.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor))
      typedArray.getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor()); 
    typedArray.recycle();
    contentFrameLayout.requestLayout();
  }
  
  private ViewGroup createSubDecor() {
    StringBuilder stringBuilder;
    TypedArray typedArray = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
    if (typedArray.hasValue(R.styleable.AppCompatTheme_windowActionBar)) {
      ViewGroup viewGroup;
      if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
        requestWindowFeature(1);
      } else if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowActionBar, false)) {
        requestWindowFeature(108);
      } 
      if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false))
        requestWindowFeature(109); 
      if (typedArray.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false))
        requestWindowFeature(10); 
      this.mIsFloating = typedArray.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
      typedArray.recycle();
      this.mWindow.getDecorView();
      LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
      if (!this.mWindowNoTitle) {
        if (this.mIsFloating) {
          viewGroup = (ViewGroup)layoutInflater.inflate(R.layout.abc_dialog_title_material, null);
          this.mOverlayActionBar = false;
          this.mHasActionBar = false;
        } else if (this.mHasActionBar) {
          Context context;
          TypedValue typedValue = new TypedValue();
          this.mContext.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
          int i = typedValue.resourceId;
          if (i != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, i);
          } else {
            context = this.mContext;
          } 
          ViewGroup viewGroup1 = (ViewGroup)LayoutInflater.from(context).inflate(R.layout.abc_screen_toolbar, null);
          this.mDecorContentParent = (DecorContentParent)viewGroup1.findViewById(R.id.decor_content_parent);
          this.mDecorContentParent.setWindowCallback(getWindowCallback());
          if (this.mOverlayActionBar)
            this.mDecorContentParent.initFeature(109); 
          if (this.mFeatureProgress)
            this.mDecorContentParent.initFeature(2); 
          viewGroup = viewGroup1;
          if (this.mFeatureIndeterminateProgress) {
            this.mDecorContentParent.initFeature(5);
            viewGroup = viewGroup1;
          } 
        } else {
          layoutInflater = null;
        } 
      } else {
        if (this.mOverlayActionMode) {
          viewGroup = (ViewGroup)layoutInflater.inflate(R.layout.abc_screen_simple_overlay_action_mode, null);
        } else {
          viewGroup = (ViewGroup)viewGroup.inflate(R.layout.abc_screen_simple, null);
        } 
        if (Build.VERSION.SDK_INT >= 21) {
          ViewCompat.setOnApplyWindowInsetsListener((View)viewGroup, new OnApplyWindowInsetsListener() {
                final AppCompatDelegateImpl this$0;
                
                public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
                  int i = param1WindowInsetsCompat.getSystemWindowInsetTop();
                  int j = AppCompatDelegateImpl.this.updateStatusGuard(i);
                  WindowInsetsCompat windowInsetsCompat = param1WindowInsetsCompat;
                  if (i != j)
                    windowInsetsCompat = param1WindowInsetsCompat.replaceSystemWindowInsets(param1WindowInsetsCompat.getSystemWindowInsetLeft(), j, param1WindowInsetsCompat.getSystemWindowInsetRight(), param1WindowInsetsCompat.getSystemWindowInsetBottom()); 
                  return ViewCompat.onApplyWindowInsets(param1View, windowInsetsCompat);
                }
              });
        } else {
          ((FitWindowsViewGroup)viewGroup).setOnFitSystemWindowsListener(new FitWindowsViewGroup.OnFitSystemWindowsListener() {
                final AppCompatDelegateImpl this$0;
                
                public void onFitSystemWindows(Rect param1Rect) {
                  param1Rect.top = AppCompatDelegateImpl.this.updateStatusGuard(param1Rect.top);
                }
              });
        } 
      } 
      if (viewGroup != null) {
        if (this.mDecorContentParent == null)
          this.mTitleView = (TextView)viewGroup.findViewById(R.id.title); 
        ViewUtils.makeOptionalFitsSystemWindows((View)viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout)viewGroup.findViewById(R.id.action_bar_activity_content);
        ViewGroup viewGroup1 = (ViewGroup)this.mWindow.findViewById(16908290);
        if (viewGroup1 != null) {
          while (viewGroup1.getChildCount() > 0) {
            View view = viewGroup1.getChildAt(0);
            viewGroup1.removeViewAt(0);
            contentFrameLayout.addView(view);
          } 
          viewGroup1.setId(-1);
          contentFrameLayout.setId(16908290);
          if (viewGroup1 instanceof FrameLayout)
            ((FrameLayout)viewGroup1).setForeground(null); 
        } 
        this.mWindow.setContentView((View)viewGroup);
        contentFrameLayout.setAttachListener(new ContentFrameLayout.OnAttachListener() {
              final AppCompatDelegateImpl this$0;
              
              public void onAttachedFromWindow() {}
              
              public void onDetachedFromWindow() {
                AppCompatDelegateImpl.this.dismissPopups();
              }
            });
        return viewGroup;
      } 
      stringBuilder = new StringBuilder();
      stringBuilder.append("AppCompat does not support the current theme features: { windowActionBar: ");
      stringBuilder.append(this.mHasActionBar);
      stringBuilder.append(", windowActionBarOverlay: ");
      stringBuilder.append(this.mOverlayActionBar);
      stringBuilder.append(", android:windowIsFloating: ");
      stringBuilder.append(this.mIsFloating);
      stringBuilder.append(", windowActionModeOverlay: ");
      stringBuilder.append(this.mOverlayActionMode);
      stringBuilder.append(", windowNoTitle: ");
      stringBuilder.append(this.mWindowNoTitle);
      stringBuilder.append(" }");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    stringBuilder.recycle();
    throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
  }
  
  private void ensureAutoNightModeManager() {
    if (this.mAutoNightModeManager == null)
      this.mAutoNightModeManager = new AutoNightModeManager(TwilightManager.getInstance(this.mContext)); 
  }
  
  private void ensureSubDecor() {
    if (!this.mSubDecorInstalled) {
      this.mSubDecor = createSubDecor();
      CharSequence charSequence = getTitle();
      if (!TextUtils.isEmpty(charSequence)) {
        DecorContentParent decorContentParent = this.mDecorContentParent;
        if (decorContentParent != null) {
          decorContentParent.setWindowTitle(charSequence);
        } else if (peekSupportActionBar() != null) {
          peekSupportActionBar().setWindowTitle(charSequence);
        } else {
          TextView textView = this.mTitleView;
          if (textView != null)
            textView.setText(charSequence); 
        } 
      } 
      applyFixedSizeWindow();
      onSubDecorInstalled(this.mSubDecor);
      this.mSubDecorInstalled = true;
      PanelFeatureState panelFeatureState = getPanelState(0, false);
      if (!this.mIsDestroyed && (panelFeatureState == null || panelFeatureState.menu == null))
        invalidatePanelMenu(108); 
    } 
  }
  
  private int getNightMode() {
    int i = this.mLocalNightMode;
    if (i == -100)
      i = AppCompatDelegate.getDefaultNightMode(); 
    return i;
  }
  
  private void initWindowDecorActionBar() {
    ensureSubDecor();
    if (this.mHasActionBar && this.mActionBar == null) {
      Window.Callback callback = this.mOriginalWindowCallback;
      if (callback instanceof Activity) {
        this.mActionBar = new WindowDecorActionBar((Activity)callback, this.mOverlayActionBar);
      } else if (callback instanceof Dialog) {
        this.mActionBar = new WindowDecorActionBar((Dialog)callback);
      } 
      ActionBar actionBar = this.mActionBar;
      if (actionBar != null)
        actionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp); 
    } 
  }
  
  private boolean initializePanelContent(PanelFeatureState paramPanelFeatureState) {
    View view = paramPanelFeatureState.createdPanelView;
    boolean bool = true;
    if (view != null) {
      paramPanelFeatureState.shownPanelView = view;
      return true;
    } 
    if (paramPanelFeatureState.menu == null)
      return false; 
    if (this.mPanelMenuPresenterCallback == null)
      this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback(); 
    paramPanelFeatureState.shownPanelView = (View)paramPanelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
    if (paramPanelFeatureState.shownPanelView == null)
      bool = false; 
    return bool;
  }
  
  private boolean initializePanelDecor(PanelFeatureState paramPanelFeatureState) {
    paramPanelFeatureState.setStyle(getActionBarThemedContext());
    paramPanelFeatureState.decorView = (ViewGroup)new ListMenuDecorView(paramPanelFeatureState.listPresenterContext);
    paramPanelFeatureState.gravity = 81;
    return true;
  }
  
  private boolean initializePanelMenu(PanelFeatureState paramPanelFeatureState) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mContext : Landroid/content/Context;
    //   4: astore #5
    //   6: aload_1
    //   7: getfield featureId : I
    //   10: istore_2
    //   11: iload_2
    //   12: ifeq -> 24
    //   15: aload #5
    //   17: astore_3
    //   18: iload_2
    //   19: bipush #108
    //   21: if_icmpne -> 197
    //   24: aload #5
    //   26: astore_3
    //   27: aload_0
    //   28: getfield mDecorContentParent : Landroidx/appcompat/widget/DecorContentParent;
    //   31: ifnull -> 197
    //   34: new android/util/TypedValue
    //   37: dup
    //   38: invokespecial <init> : ()V
    //   41: astore #7
    //   43: aload #5
    //   45: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   48: astore #6
    //   50: aload #6
    //   52: getstatic androidx/appcompat/R$attr.actionBarTheme : I
    //   55: aload #7
    //   57: iconst_1
    //   58: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   61: pop
    //   62: aconst_null
    //   63: astore_3
    //   64: aload #7
    //   66: getfield resourceId : I
    //   69: ifeq -> 111
    //   72: aload #5
    //   74: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   77: invokevirtual newTheme : ()Landroid/content/res/Resources$Theme;
    //   80: astore_3
    //   81: aload_3
    //   82: aload #6
    //   84: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   87: aload_3
    //   88: aload #7
    //   90: getfield resourceId : I
    //   93: iconst_1
    //   94: invokevirtual applyStyle : (IZ)V
    //   97: aload_3
    //   98: getstatic androidx/appcompat/R$attr.actionBarWidgetTheme : I
    //   101: aload #7
    //   103: iconst_1
    //   104: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   107: pop
    //   108: goto -> 123
    //   111: aload #6
    //   113: getstatic androidx/appcompat/R$attr.actionBarWidgetTheme : I
    //   116: aload #7
    //   118: iconst_1
    //   119: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   122: pop
    //   123: aload_3
    //   124: astore #4
    //   126: aload #7
    //   128: getfield resourceId : I
    //   131: ifeq -> 169
    //   134: aload_3
    //   135: astore #4
    //   137: aload_3
    //   138: ifnonnull -> 158
    //   141: aload #5
    //   143: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   146: invokevirtual newTheme : ()Landroid/content/res/Resources$Theme;
    //   149: astore #4
    //   151: aload #4
    //   153: aload #6
    //   155: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   158: aload #4
    //   160: aload #7
    //   162: getfield resourceId : I
    //   165: iconst_1
    //   166: invokevirtual applyStyle : (IZ)V
    //   169: aload #5
    //   171: astore_3
    //   172: aload #4
    //   174: ifnull -> 197
    //   177: new androidx/appcompat/view/ContextThemeWrapper
    //   180: dup
    //   181: aload #5
    //   183: iconst_0
    //   184: invokespecial <init> : (Landroid/content/Context;I)V
    //   187: astore_3
    //   188: aload_3
    //   189: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   192: aload #4
    //   194: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   197: new androidx/appcompat/view/menu/MenuBuilder
    //   200: dup
    //   201: aload_3
    //   202: invokespecial <init> : (Landroid/content/Context;)V
    //   205: astore_3
    //   206: aload_3
    //   207: aload_0
    //   208: invokevirtual setCallback : (Landroidx/appcompat/view/menu/MenuBuilder$Callback;)V
    //   211: aload_1
    //   212: aload_3
    //   213: invokevirtual setMenu : (Landroidx/appcompat/view/menu/MenuBuilder;)V
    //   216: iconst_1
    //   217: ireturn
  }
  
  private void invalidatePanelMenu(int paramInt) {
    this.mInvalidatePanelMenuFeatures = 1 << paramInt | this.mInvalidatePanelMenuFeatures;
    if (!this.mInvalidatePanelMenuPosted) {
      ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
      this.mInvalidatePanelMenuPosted = true;
    } 
  }
  
  private boolean onKeyDownPanel(int paramInt, KeyEvent paramKeyEvent) {
    if (paramKeyEvent.getRepeatCount() == 0) {
      PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
      if (!panelFeatureState.isOpen)
        return preparePanel(panelFeatureState, paramKeyEvent); 
    } 
    return false;
  }
  
  private boolean onKeyUpPanel(int paramInt, KeyEvent paramKeyEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mActionMode : Landroidx/appcompat/view/ActionMode;
    //   4: ifnull -> 9
    //   7: iconst_0
    //   8: ireturn
    //   9: aload_0
    //   10: iload_1
    //   11: iconst_1
    //   12: invokevirtual getPanelState : (IZ)Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;
    //   15: astore #5
    //   17: iload_1
    //   18: ifne -> 110
    //   21: aload_0
    //   22: getfield mDecorContentParent : Landroidx/appcompat/widget/DecorContentParent;
    //   25: astore #4
    //   27: aload #4
    //   29: ifnull -> 110
    //   32: aload #4
    //   34: invokeinterface canShowOverflowMenu : ()Z
    //   39: ifeq -> 110
    //   42: aload_0
    //   43: getfield mContext : Landroid/content/Context;
    //   46: invokestatic get : (Landroid/content/Context;)Landroid/view/ViewConfiguration;
    //   49: invokevirtual hasPermanentMenuKey : ()Z
    //   52: ifne -> 110
    //   55: aload_0
    //   56: getfield mDecorContentParent : Landroidx/appcompat/widget/DecorContentParent;
    //   59: invokeinterface isOverflowMenuShowing : ()Z
    //   64: ifne -> 97
    //   67: aload_0
    //   68: getfield mIsDestroyed : Z
    //   71: ifne -> 180
    //   74: aload_0
    //   75: aload #5
    //   77: aload_2
    //   78: invokespecial preparePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   81: ifeq -> 180
    //   84: aload_0
    //   85: getfield mDecorContentParent : Landroidx/appcompat/widget/DecorContentParent;
    //   88: invokeinterface showOverflowMenu : ()Z
    //   93: istore_3
    //   94: goto -> 198
    //   97: aload_0
    //   98: getfield mDecorContentParent : Landroidx/appcompat/widget/DecorContentParent;
    //   101: invokeinterface hideOverflowMenu : ()Z
    //   106: istore_3
    //   107: goto -> 198
    //   110: aload #5
    //   112: getfield isOpen : Z
    //   115: ifne -> 185
    //   118: aload #5
    //   120: getfield isHandled : Z
    //   123: ifeq -> 129
    //   126: goto -> 185
    //   129: aload #5
    //   131: getfield isPrepared : Z
    //   134: ifeq -> 180
    //   137: aload #5
    //   139: getfield refreshMenuContent : Z
    //   142: ifeq -> 162
    //   145: aload #5
    //   147: iconst_0
    //   148: putfield isPrepared : Z
    //   151: aload_0
    //   152: aload #5
    //   154: aload_2
    //   155: invokespecial preparePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   158: istore_3
    //   159: goto -> 164
    //   162: iconst_1
    //   163: istore_3
    //   164: iload_3
    //   165: ifeq -> 180
    //   168: aload_0
    //   169: aload #5
    //   171: aload_2
    //   172: invokespecial openPanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Landroid/view/KeyEvent;)V
    //   175: iconst_1
    //   176: istore_3
    //   177: goto -> 198
    //   180: iconst_0
    //   181: istore_3
    //   182: goto -> 198
    //   185: aload #5
    //   187: getfield isOpen : Z
    //   190: istore_3
    //   191: aload_0
    //   192: aload #5
    //   194: iconst_1
    //   195: invokevirtual closePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Z)V
    //   198: iload_3
    //   199: ifeq -> 225
    //   202: aload_0
    //   203: getfield mContext : Landroid/content/Context;
    //   206: ldc_w 'audio'
    //   209: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   212: checkcast android/media/AudioManager
    //   215: astore_2
    //   216: aload_2
    //   217: ifnull -> 225
    //   220: aload_2
    //   221: iconst_0
    //   222: invokevirtual playSoundEffect : (I)V
    //   225: iload_3
    //   226: ireturn
  }
  
  private void openPanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent) {
    // Byte code:
    //   0: aload_1
    //   1: getfield isOpen : Z
    //   4: ifne -> 409
    //   7: aload_0
    //   8: getfield mIsDestroyed : Z
    //   11: ifeq -> 17
    //   14: goto -> 409
    //   17: aload_1
    //   18: getfield featureId : I
    //   21: ifne -> 56
    //   24: aload_0
    //   25: getfield mContext : Landroid/content/Context;
    //   28: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   31: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   34: getfield screenLayout : I
    //   37: bipush #15
    //   39: iand
    //   40: iconst_4
    //   41: if_icmpne -> 49
    //   44: iconst_1
    //   45: istore_3
    //   46: goto -> 51
    //   49: iconst_0
    //   50: istore_3
    //   51: iload_3
    //   52: ifeq -> 56
    //   55: return
    //   56: aload_0
    //   57: invokevirtual getWindowCallback : ()Landroid/view/Window$Callback;
    //   60: astore #4
    //   62: aload #4
    //   64: ifnull -> 92
    //   67: aload #4
    //   69: aload_1
    //   70: getfield featureId : I
    //   73: aload_1
    //   74: getfield menu : Landroidx/appcompat/view/menu/MenuBuilder;
    //   77: invokeinterface onMenuOpened : (ILandroid/view/Menu;)Z
    //   82: ifne -> 92
    //   85: aload_0
    //   86: aload_1
    //   87: iconst_1
    //   88: invokevirtual closePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Z)V
    //   91: return
    //   92: aload_0
    //   93: getfield mContext : Landroid/content/Context;
    //   96: ldc_w 'window'
    //   99: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   102: checkcast android/view/WindowManager
    //   105: astore #5
    //   107: aload #5
    //   109: ifnonnull -> 113
    //   112: return
    //   113: aload_0
    //   114: aload_1
    //   115: aload_2
    //   116: invokespecial preparePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   119: ifne -> 123
    //   122: return
    //   123: aload_1
    //   124: getfield decorView : Landroid/view/ViewGroup;
    //   127: ifnull -> 171
    //   130: aload_1
    //   131: getfield refreshDecorView : Z
    //   134: ifeq -> 140
    //   137: goto -> 171
    //   140: aload_1
    //   141: getfield createdPanelView : Landroid/view/View;
    //   144: astore_2
    //   145: aload_2
    //   146: ifnull -> 341
    //   149: aload_2
    //   150: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   153: astore_2
    //   154: aload_2
    //   155: ifnull -> 341
    //   158: aload_2
    //   159: getfield width : I
    //   162: iconst_m1
    //   163: if_icmpne -> 341
    //   166: iconst_m1
    //   167: istore_3
    //   168: goto -> 344
    //   171: aload_1
    //   172: getfield decorView : Landroid/view/ViewGroup;
    //   175: astore_2
    //   176: aload_2
    //   177: ifnonnull -> 196
    //   180: aload_0
    //   181: aload_1
    //   182: invokespecial initializePanelDecor : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;)Z
    //   185: ifeq -> 195
    //   188: aload_1
    //   189: getfield decorView : Landroid/view/ViewGroup;
    //   192: ifnonnull -> 217
    //   195: return
    //   196: aload_1
    //   197: getfield refreshDecorView : Z
    //   200: ifeq -> 217
    //   203: aload_2
    //   204: invokevirtual getChildCount : ()I
    //   207: ifle -> 217
    //   210: aload_1
    //   211: getfield decorView : Landroid/view/ViewGroup;
    //   214: invokevirtual removeAllViews : ()V
    //   217: aload_0
    //   218: aload_1
    //   219: invokespecial initializePanelContent : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;)Z
    //   222: ifeq -> 409
    //   225: aload_1
    //   226: invokevirtual hasPanelItems : ()Z
    //   229: ifne -> 235
    //   232: goto -> 409
    //   235: aload_1
    //   236: getfield shownPanelView : Landroid/view/View;
    //   239: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   242: astore #4
    //   244: aload #4
    //   246: astore_2
    //   247: aload #4
    //   249: ifnonnull -> 264
    //   252: new android/view/ViewGroup$LayoutParams
    //   255: dup
    //   256: bipush #-2
    //   258: bipush #-2
    //   260: invokespecial <init> : (II)V
    //   263: astore_2
    //   264: aload_1
    //   265: getfield background : I
    //   268: istore_3
    //   269: aload_1
    //   270: getfield decorView : Landroid/view/ViewGroup;
    //   273: iload_3
    //   274: invokevirtual setBackgroundResource : (I)V
    //   277: aload_1
    //   278: getfield shownPanelView : Landroid/view/View;
    //   281: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   284: astore #4
    //   286: aload #4
    //   288: ifnull -> 311
    //   291: aload #4
    //   293: instanceof android/view/ViewGroup
    //   296: ifeq -> 311
    //   299: aload #4
    //   301: checkcast android/view/ViewGroup
    //   304: aload_1
    //   305: getfield shownPanelView : Landroid/view/View;
    //   308: invokevirtual removeView : (Landroid/view/View;)V
    //   311: aload_1
    //   312: getfield decorView : Landroid/view/ViewGroup;
    //   315: aload_1
    //   316: getfield shownPanelView : Landroid/view/View;
    //   319: aload_2
    //   320: invokevirtual addView : (Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   323: aload_1
    //   324: getfield shownPanelView : Landroid/view/View;
    //   327: invokevirtual hasFocus : ()Z
    //   330: ifne -> 341
    //   333: aload_1
    //   334: getfield shownPanelView : Landroid/view/View;
    //   337: invokevirtual requestFocus : ()Z
    //   340: pop
    //   341: bipush #-2
    //   343: istore_3
    //   344: aload_1
    //   345: iconst_0
    //   346: putfield isHandled : Z
    //   349: new android/view/WindowManager$LayoutParams
    //   352: dup
    //   353: iload_3
    //   354: bipush #-2
    //   356: aload_1
    //   357: getfield x : I
    //   360: aload_1
    //   361: getfield y : I
    //   364: sipush #1002
    //   367: ldc_w 8519680
    //   370: bipush #-3
    //   372: invokespecial <init> : (IIIIIII)V
    //   375: astore_2
    //   376: aload_2
    //   377: aload_1
    //   378: getfield gravity : I
    //   381: putfield gravity : I
    //   384: aload_2
    //   385: aload_1
    //   386: getfield windowAnimations : I
    //   389: putfield windowAnimations : I
    //   392: aload #5
    //   394: aload_1
    //   395: getfield decorView : Landroid/view/ViewGroup;
    //   398: aload_2
    //   399: invokeinterface addView : (Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   404: aload_1
    //   405: iconst_1
    //   406: putfield isOpen : Z
    //   409: return
  }
  
  private boolean performPanelShortcut(PanelFeatureState paramPanelFeatureState, int paramInt1, KeyEvent paramKeyEvent, int paramInt2) {
    // Byte code:
    //   0: aload_3
    //   1: invokevirtual isSystem : ()Z
    //   4: istore #5
    //   6: iconst_0
    //   7: istore #6
    //   9: iload #5
    //   11: ifeq -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: getfield isPrepared : Z
    //   20: ifne -> 36
    //   23: iload #6
    //   25: istore #5
    //   27: aload_0
    //   28: aload_1
    //   29: aload_3
    //   30: invokespecial preparePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Landroid/view/KeyEvent;)Z
    //   33: ifeq -> 62
    //   36: aload_1
    //   37: getfield menu : Landroidx/appcompat/view/menu/MenuBuilder;
    //   40: astore #7
    //   42: iload #6
    //   44: istore #5
    //   46: aload #7
    //   48: ifnull -> 62
    //   51: aload #7
    //   53: iload_2
    //   54: aload_3
    //   55: iload #4
    //   57: invokevirtual performShortcut : (ILandroid/view/KeyEvent;I)Z
    //   60: istore #5
    //   62: iload #5
    //   64: ifeq -> 87
    //   67: iload #4
    //   69: iconst_1
    //   70: iand
    //   71: ifne -> 87
    //   74: aload_0
    //   75: getfield mDecorContentParent : Landroidx/appcompat/widget/DecorContentParent;
    //   78: ifnonnull -> 87
    //   81: aload_0
    //   82: aload_1
    //   83: iconst_1
    //   84: invokevirtual closePanel : (Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;Z)V
    //   87: iload #5
    //   89: ireturn
  }
  
  private boolean preparePanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent) {
    DecorContentParent decorContentParent;
    if (this.mIsDestroyed)
      return false; 
    if (paramPanelFeatureState.isPrepared)
      return true; 
    PanelFeatureState panelFeatureState = this.mPreparedPanel;
    if (panelFeatureState != null && panelFeatureState != paramPanelFeatureState)
      closePanel(panelFeatureState, false); 
    Window.Callback callback = getWindowCallback();
    if (callback != null)
      paramPanelFeatureState.createdPanelView = callback.onCreatePanelView(paramPanelFeatureState.featureId); 
    int i = paramPanelFeatureState.featureId;
    if (i == 0 || i == 108) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i != 0) {
      DecorContentParent decorContentParent1 = this.mDecorContentParent;
      if (decorContentParent1 != null)
        decorContentParent1.setMenuPrepared(); 
    } 
    if (paramPanelFeatureState.createdPanelView == null) {
      DecorContentParent decorContentParent1;
      boolean bool;
      if (i != 0)
        peekSupportActionBar(); 
      if (paramPanelFeatureState.menu == null || paramPanelFeatureState.refreshMenuContent) {
        if (paramPanelFeatureState.menu == null && (!initializePanelMenu(paramPanelFeatureState) || paramPanelFeatureState.menu == null))
          return false; 
        if (i != 0 && this.mDecorContentParent != null) {
          if (this.mActionMenuPresenterCallback == null)
            this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback(); 
          this.mDecorContentParent.setMenu((Menu)paramPanelFeatureState.menu, this.mActionMenuPresenterCallback);
        } 
        paramPanelFeatureState.menu.stopDispatchingItemsChanged();
        if (!callback.onCreatePanelMenu(paramPanelFeatureState.featureId, (Menu)paramPanelFeatureState.menu)) {
          paramPanelFeatureState.setMenu(null);
          if (i != 0) {
            decorContentParent = this.mDecorContentParent;
            if (decorContentParent != null)
              decorContentParent.setMenu(null, this.mActionMenuPresenterCallback); 
          } 
          return false;
        } 
        ((PanelFeatureState)decorContentParent).refreshMenuContent = false;
      } 
      ((PanelFeatureState)decorContentParent).menu.stopDispatchingItemsChanged();
      Bundle bundle = ((PanelFeatureState)decorContentParent).frozenActionViewState;
      if (bundle != null) {
        ((PanelFeatureState)decorContentParent).menu.restoreActionViewStates(bundle);
        ((PanelFeatureState)decorContentParent).frozenActionViewState = null;
      } 
      if (!callback.onPreparePanel(0, ((PanelFeatureState)decorContentParent).createdPanelView, (Menu)((PanelFeatureState)decorContentParent).menu)) {
        if (i != 0) {
          decorContentParent1 = this.mDecorContentParent;
          if (decorContentParent1 != null)
            decorContentParent1.setMenu(null, this.mActionMenuPresenterCallback); 
        } 
        ((PanelFeatureState)decorContentParent).menu.startDispatchingItemsChanged();
        return false;
      } 
      if (decorContentParent1 != null) {
        i = decorContentParent1.getDeviceId();
      } else {
        i = -1;
      } 
      if (KeyCharacterMap.load(i).getKeyboardType() != 1) {
        bool = true;
      } else {
        bool = false;
      } 
      ((PanelFeatureState)decorContentParent).qwertyMode = bool;
      ((PanelFeatureState)decorContentParent).menu.setQwertyMode(((PanelFeatureState)decorContentParent).qwertyMode);
      ((PanelFeatureState)decorContentParent).menu.startDispatchingItemsChanged();
    } 
    ((PanelFeatureState)decorContentParent).isPrepared = true;
    ((PanelFeatureState)decorContentParent).isHandled = false;
    this.mPreparedPanel = (PanelFeatureState)decorContentParent;
    return true;
  }
  
  private void reopenMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {
    DecorContentParent decorContentParent = this.mDecorContentParent;
    if (decorContentParent != null && decorContentParent.canShowOverflowMenu() && (!ViewConfiguration.get(this.mContext).hasPermanentMenuKey() || this.mDecorContentParent.isOverflowMenuShowPending())) {
      Window.Callback callback = getWindowCallback();
      if (!this.mDecorContentParent.isOverflowMenuShowing() || !paramBoolean) {
        if (callback != null && !this.mIsDestroyed) {
          if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 0x1) != 0) {
            this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuRunnable.run();
          } 
          PanelFeatureState panelFeatureState1 = getPanelState(0, true);
          MenuBuilder menuBuilder = panelFeatureState1.menu;
          if (menuBuilder != null && !panelFeatureState1.refreshMenuContent && callback.onPreparePanel(0, panelFeatureState1.createdPanelView, (Menu)menuBuilder)) {
            callback.onMenuOpened(108, (Menu)panelFeatureState1.menu);
            this.mDecorContentParent.showOverflowMenu();
          } 
        } 
        return;
      } 
      this.mDecorContentParent.hideOverflowMenu();
      if (!this.mIsDestroyed)
        callback.onPanelClosed(108, (Menu)(getPanelState(0, true)).menu); 
      return;
    } 
    PanelFeatureState panelFeatureState = getPanelState(0, true);
    panelFeatureState.refreshDecorView = true;
    closePanel(panelFeatureState, false);
    openPanel(panelFeatureState, null);
  }
  
  private int sanitizeWindowFeatureId(int paramInt) {
    if (paramInt == 8)
      return 108; 
    int i = paramInt;
    if (paramInt == 9)
      i = 109; 
    return i;
  }
  
  private boolean shouldInheritContext(ViewParent paramViewParent) {
    if (paramViewParent == null)
      return false; 
    View view = this.mWindow.getDecorView();
    while (true) {
      if (paramViewParent == null)
        return true; 
      if (paramViewParent == view || !(paramViewParent instanceof View) || ViewCompat.isAttachedToWindow((View)paramViewParent))
        break; 
      paramViewParent = paramViewParent.getParent();
    } 
    return false;
  }
  
  private boolean shouldRecreateOnNightModeChange() {
    boolean bool1 = this.mApplyDayNightCalled;
    boolean bool = false;
    if (bool1) {
      Context context = this.mContext;
      if (context instanceof Activity) {
        PackageManager packageManager = context.getPackageManager();
        try {
          ComponentName componentName = new ComponentName();
          this(this.mContext, this.mContext.getClass());
          int i = (packageManager.getActivityInfo(componentName, 0)).configChanges;
          if ((i & 0x200) == 0)
            bool = true; 
          return bool;
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          return true;
        } 
      } 
    } 
    return false;
  }
  
  private void throwFeatureRequestIfSubDecorInstalled() {
    if (!this.mSubDecorInstalled)
      return; 
    throw new AndroidRuntimeException("Window feature must be requested before adding content");
  }
  
  private boolean updateForNightMode(int paramInt) {
    Resources resources = this.mContext.getResources();
    Configuration configuration = resources.getConfiguration();
    int i = configuration.uiMode;
    if (paramInt == 2) {
      paramInt = 32;
    } else {
      paramInt = 16;
    } 
    if ((i & 0x30) != paramInt) {
      if (shouldRecreateOnNightModeChange()) {
        ((Activity)this.mContext).recreate();
      } else {
        configuration = new Configuration(configuration);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration.uiMode = paramInt | configuration.uiMode & 0xFFFFFFCF;
        resources.updateConfiguration(configuration, displayMetrics);
        if (Build.VERSION.SDK_INT < 26)
          ResourcesFlusher.flush(resources); 
      } 
      return true;
    } 
    return false;
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    ensureSubDecor();
    ((ViewGroup)this.mSubDecor.findViewById(16908290)).addView(paramView, paramLayoutParams);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public boolean applyDayNight() {
    boolean bool;
    int i = getNightMode();
    int j = mapNightMode(i);
    if (j != -1) {
      bool = updateForNightMode(j);
    } else {
      bool = false;
    } 
    if (i == 0) {
      ensureAutoNightModeManager();
      this.mAutoNightModeManager.setup();
    } 
    this.mApplyDayNightCalled = true;
    return bool;
  }
  
  void callOnPanelClosed(int paramInt, PanelFeatureState paramPanelFeatureState, Menu paramMenu) {
    MenuBuilder menuBuilder;
    PanelFeatureState panelFeatureState = paramPanelFeatureState;
    Menu menu = paramMenu;
    if (paramMenu == null) {
      PanelFeatureState panelFeatureState1 = paramPanelFeatureState;
      if (paramPanelFeatureState == null) {
        panelFeatureState1 = paramPanelFeatureState;
        if (paramInt >= 0) {
          PanelFeatureState[] arrayOfPanelFeatureState = this.mPanels;
          panelFeatureState1 = paramPanelFeatureState;
          if (paramInt < arrayOfPanelFeatureState.length)
            panelFeatureState1 = arrayOfPanelFeatureState[paramInt]; 
        } 
      } 
      panelFeatureState = panelFeatureState1;
      menu = paramMenu;
      if (panelFeatureState1 != null) {
        menuBuilder = panelFeatureState1.menu;
        panelFeatureState = panelFeatureState1;
      } 
    } 
    if (panelFeatureState != null && !panelFeatureState.isOpen)
      return; 
    if (!this.mIsDestroyed)
      this.mOriginalWindowCallback.onPanelClosed(paramInt, (Menu)menuBuilder); 
  }
  
  void checkCloseActionMenu(MenuBuilder paramMenuBuilder) {
    if (this.mClosingActionMenu)
      return; 
    this.mClosingActionMenu = true;
    this.mDecorContentParent.dismissPopups();
    Window.Callback callback = getWindowCallback();
    if (callback != null && !this.mIsDestroyed)
      callback.onPanelClosed(108, (Menu)paramMenuBuilder); 
    this.mClosingActionMenu = false;
  }
  
  void closePanel(int paramInt) {
    closePanel(getPanelState(paramInt, true), true);
  }
  
  void closePanel(PanelFeatureState paramPanelFeatureState, boolean paramBoolean) {
    if (paramBoolean && paramPanelFeatureState.featureId == 0) {
      DecorContentParent decorContentParent = this.mDecorContentParent;
      if (decorContentParent != null && decorContentParent.isOverflowMenuShowing()) {
        checkCloseActionMenu(paramPanelFeatureState.menu);
        return;
      } 
    } 
    WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
    if (windowManager != null && paramPanelFeatureState.isOpen) {
      ViewGroup viewGroup = paramPanelFeatureState.decorView;
      if (viewGroup != null) {
        windowManager.removeView((View)viewGroup);
        if (paramBoolean)
          callOnPanelClosed(paramPanelFeatureState.featureId, paramPanelFeatureState, null); 
      } 
    } 
    paramPanelFeatureState.isPrepared = false;
    paramPanelFeatureState.isHandled = false;
    paramPanelFeatureState.isOpen = false;
    paramPanelFeatureState.shownPanelView = null;
    paramPanelFeatureState.refreshDecorView = true;
    if (this.mPreparedPanel == paramPanelFeatureState)
      this.mPreparedPanel = null; 
  }
  
  public View createView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    AppCompatViewInflater appCompatViewInflater = this.mAppCompatViewInflater;
    boolean bool = false;
    if (appCompatViewInflater == null) {
      String str = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme).getString(R.styleable.AppCompatTheme_viewInflaterClass);
      if (str == null || AppCompatViewInflater.class.getName().equals(str)) {
        this.mAppCompatViewInflater = new AppCompatViewInflater();
      } else {
        try {
          this.mAppCompatViewInflater = Class.forName(str).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } finally {
          Exception exception = null;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Failed to instantiate custom view inflater ");
          stringBuilder.append(str);
          stringBuilder.append(". Falling back to default.");
          stringBuilder.toString();
        } 
      } 
    } 
    if (IS_PRE_LOLLIPOP) {
      if (paramAttributeSet instanceof XmlPullParser) {
        if (((XmlPullParser)paramAttributeSet).getDepth() > 1)
          bool = true; 
      } else {
        bool = shouldInheritContext((ViewParent)paramView);
      } 
    } else {
      bool = false;
    } 
    return this.mAppCompatViewInflater.createView(paramView, paramString, paramContext, paramAttributeSet, bool, IS_PRE_LOLLIPOP, true, VectorEnabledTintResources.shouldBeUsed());
  }
  
  void dismissPopups() {
    DecorContentParent decorContentParent = this.mDecorContentParent;
    if (decorContentParent != null)
      decorContentParent.dismissPopups(); 
    if (this.mActionModePopup != null) {
      this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
      if (this.mActionModePopup.isShowing())
        try {
          this.mActionModePopup.dismiss();
        } catch (IllegalArgumentException illegalArgumentException) {} 
      this.mActionModePopup = null;
    } 
    endOnGoingFadeAnimation();
    PanelFeatureState panelFeatureState = getPanelState(0, false);
    if (panelFeatureState != null) {
      MenuBuilder menuBuilder = panelFeatureState.menu;
      if (menuBuilder != null)
        menuBuilder.close(); 
    } 
  }
  
  boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    Window.Callback callback = this.mOriginalWindowCallback;
    boolean bool1 = callback instanceof KeyEventDispatcher.Component;
    boolean bool = true;
    if (bool1 || callback instanceof AppCompatDialog) {
      View view = this.mWindow.getDecorView();
      if (view != null && KeyEventDispatcher.dispatchBeforeHierarchy(view, paramKeyEvent))
        return true; 
    } 
    if (paramKeyEvent.getKeyCode() == 82 && this.mOriginalWindowCallback.dispatchKeyEvent(paramKeyEvent))
      return true; 
    int i = paramKeyEvent.getKeyCode();
    if (paramKeyEvent.getAction() != 0)
      bool = false; 
    if (bool) {
      bool1 = onKeyDown(i, paramKeyEvent);
    } else {
      bool1 = onKeyUp(i, paramKeyEvent);
    } 
    return bool1;
  }
  
  void doInvalidatePanelMenu(int paramInt) {
    PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
    if (panelFeatureState.menu != null) {
      Bundle bundle = new Bundle();
      panelFeatureState.menu.saveActionViewStates(bundle);
      if (bundle.size() > 0)
        panelFeatureState.frozenActionViewState = bundle; 
      panelFeatureState.menu.stopDispatchingItemsChanged();
      panelFeatureState.menu.clear();
    } 
    panelFeatureState.refreshMenuContent = true;
    panelFeatureState.refreshDecorView = true;
    if ((paramInt == 108 || paramInt == 0) && this.mDecorContentParent != null) {
      panelFeatureState = getPanelState(0, false);
      if (panelFeatureState != null) {
        panelFeatureState.isPrepared = false;
        preparePanel(panelFeatureState, null);
      } 
    } 
  }
  
  void endOnGoingFadeAnimation() {
    ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mFadeAnim;
    if (viewPropertyAnimatorCompat != null)
      viewPropertyAnimatorCompat.cancel(); 
  }
  
  PanelFeatureState findMenuPanel(Menu paramMenu) {
    byte b1;
    PanelFeatureState[] arrayOfPanelFeatureState = this.mPanels;
    byte b2 = 0;
    if (arrayOfPanelFeatureState != null) {
      b1 = arrayOfPanelFeatureState.length;
    } else {
      b1 = 0;
    } 
    while (b2 < b1) {
      PanelFeatureState panelFeatureState = arrayOfPanelFeatureState[b2];
      if (panelFeatureState != null && panelFeatureState.menu == paramMenu)
        return panelFeatureState; 
      b2++;
    } 
    return null;
  }
  
  public <T extends View> T findViewById(int paramInt) {
    ensureSubDecor();
    return (T)this.mWindow.findViewById(paramInt);
  }
  
  final Context getActionBarThemedContext() {
    Context context;
    ActionBar actionBar1 = getSupportActionBar();
    if (actionBar1 != null) {
      Context context1 = actionBar1.getThemedContext();
    } else {
      actionBar1 = null;
    } 
    ActionBar actionBar2 = actionBar1;
    if (actionBar1 == null)
      context = this.mContext; 
    return context;
  }
  
  public MenuInflater getMenuInflater() {
    if (this.mMenuInflater == null) {
      Context context;
      initWindowDecorActionBar();
      ActionBar actionBar = this.mActionBar;
      if (actionBar != null) {
        context = actionBar.getThemedContext();
      } else {
        context = this.mContext;
      } 
      this.mMenuInflater = (MenuInflater)new SupportMenuInflater(context);
    } 
    return this.mMenuInflater;
  }
  
  protected PanelFeatureState getPanelState(int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mPanels : [Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;
    //   4: astore #4
    //   6: aload #4
    //   8: ifnull -> 21
    //   11: aload #4
    //   13: astore_3
    //   14: aload #4
    //   16: arraylength
    //   17: iload_1
    //   18: if_icmpgt -> 49
    //   21: iload_1
    //   22: iconst_1
    //   23: iadd
    //   24: anewarray androidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState
    //   27: astore_3
    //   28: aload #4
    //   30: ifnull -> 44
    //   33: aload #4
    //   35: iconst_0
    //   36: aload_3
    //   37: iconst_0
    //   38: aload #4
    //   40: arraylength
    //   41: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   44: aload_0
    //   45: aload_3
    //   46: putfield mPanels : [Landroidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState;
    //   49: aload_3
    //   50: iload_1
    //   51: aaload
    //   52: astore #5
    //   54: aload #5
    //   56: astore #4
    //   58: aload #5
    //   60: ifnonnull -> 78
    //   63: new androidx/appcompat/app/AppCompatDelegateImpl$PanelFeatureState
    //   66: dup
    //   67: iload_1
    //   68: invokespecial <init> : (I)V
    //   71: astore #4
    //   73: aload_3
    //   74: iload_1
    //   75: aload #4
    //   77: aastore
    //   78: aload #4
    //   80: areturn
  }
  
  public ActionBar getSupportActionBar() {
    initWindowDecorActionBar();
    return this.mActionBar;
  }
  
  final CharSequence getTitle() {
    Window.Callback callback = this.mOriginalWindowCallback;
    return (callback instanceof Activity) ? ((Activity)callback).getTitle() : this.mTitle;
  }
  
  final Window.Callback getWindowCallback() {
    return this.mWindow.getCallback();
  }
  
  public void installViewFactory() {
    LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
    if (layoutInflater.getFactory() == null) {
      LayoutInflaterCompat.setFactory2(layoutInflater, this);
    } else {
      boolean bool = layoutInflater.getFactory2() instanceof AppCompatDelegateImpl;
    } 
  }
  
  public void invalidateOptionsMenu() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null && actionBar.invalidateOptionsMenu())
      return; 
    invalidatePanelMenu(0);
  }
  
  public boolean isHandleNativeActionModesEnabled() {
    return this.mHandleNativeActionModes;
  }
  
  int mapNightMode(int paramInt) {
    if (paramInt != -100) {
      if (paramInt != 0)
        return paramInt; 
      if (Build.VERSION.SDK_INT >= 23 && ((UiModeManager)this.mContext.getSystemService(UiModeManager.class)).getNightMode() == 0)
        return -1; 
      ensureAutoNightModeManager();
      return this.mAutoNightModeManager.getApplyableNightMode();
    } 
    return -1;
  }
  
  boolean onBackPressed() {
    ActionMode actionMode = this.mActionMode;
    if (actionMode != null) {
      actionMode.finish();
      return true;
    } 
    ActionBar actionBar = getSupportActionBar();
    return (actionBar != null && actionBar.collapseActionView());
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    if (this.mHasActionBar && this.mSubDecorInstalled) {
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null)
        actionBar.onConfigurationChanged(paramConfiguration); 
    } 
    AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
    applyDayNight();
  }
  
  public void onCreate(Bundle paramBundle) {
    Window.Callback callback = this.mOriginalWindowCallback;
    if (callback instanceof Activity) {
      String str = null;
      try {
        String str1 = NavUtils.getParentActivityName((Activity)callback);
        str = str1;
      } catch (IllegalArgumentException illegalArgumentException) {}
      if (str != null) {
        ActionBar actionBar = peekSupportActionBar();
        if (actionBar == null) {
          this.mEnableDefaultActionBarUp = true;
        } else {
          actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        } 
      } 
    } 
    if (paramBundle != null && this.mLocalNightMode == -100)
      this.mLocalNightMode = paramBundle.getInt("appcompat:local_night_mode", -100); 
  }
  
  public final View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return createView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  public void onDestroy() {
    if (this.mInvalidatePanelMenuPosted)
      this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable); 
    this.mIsDestroyed = true;
    ActionBar actionBar = this.mActionBar;
    if (actionBar != null)
      actionBar.onDestroy(); 
    AutoNightModeManager autoNightModeManager = this.mAutoNightModeManager;
    if (autoNightModeManager != null)
      autoNightModeManager.cleanup(); 
  }
  
  boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool = true;
    if (paramInt != 4) {
      if (paramInt == 82) {
        onKeyDownPanel(0, paramKeyEvent);
        return true;
      } 
    } else {
      if ((paramKeyEvent.getFlags() & 0x80) == 0)
        bool = false; 
      this.mLongPressBackDown = bool;
    } 
    return false;
  }
  
  boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent) {
    PanelFeatureState panelFeatureState1;
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null && actionBar.onKeyShortcut(paramInt, paramKeyEvent))
      return true; 
    PanelFeatureState panelFeatureState2 = this.mPreparedPanel;
    if (panelFeatureState2 != null && performPanelShortcut(panelFeatureState2, paramKeyEvent.getKeyCode(), paramKeyEvent, 1)) {
      panelFeatureState1 = this.mPreparedPanel;
      if (panelFeatureState1 != null)
        panelFeatureState1.isHandled = true; 
      return true;
    } 
    if (this.mPreparedPanel == null) {
      panelFeatureState2 = getPanelState(0, true);
      preparePanel(panelFeatureState2, (KeyEvent)panelFeatureState1);
      boolean bool = performPanelShortcut(panelFeatureState2, panelFeatureState1.getKeyCode(), (KeyEvent)panelFeatureState1, 1);
      panelFeatureState2.isPrepared = false;
      if (bool)
        return true; 
    } 
    return false;
  }
  
  boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt != 4) {
      if (paramInt == 82) {
        onKeyUpPanel(0, paramKeyEvent);
        return true;
      } 
    } else {
      boolean bool = this.mLongPressBackDown;
      this.mLongPressBackDown = false;
      PanelFeatureState panelFeatureState = getPanelState(0, false);
      if (panelFeatureState != null && panelFeatureState.isOpen) {
        if (!bool)
          closePanel(panelFeatureState, true); 
        return true;
      } 
      if (onBackPressed())
        return true; 
    } 
    return false;
  }
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem) {
    Window.Callback callback = getWindowCallback();
    if (callback != null && !this.mIsDestroyed) {
      PanelFeatureState panelFeatureState = findMenuPanel((Menu)paramMenuBuilder.getRootMenu());
      if (panelFeatureState != null)
        return callback.onMenuItemSelected(panelFeatureState.featureId, paramMenuItem); 
    } 
    return false;
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder) {
    reopenMenu(paramMenuBuilder, true);
  }
  
  void onMenuOpened(int paramInt) {
    if (paramInt == 108) {
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null)
        actionBar.dispatchMenuVisibilityChanged(true); 
    } 
  }
  
  void onPanelClosed(int paramInt) {
    if (paramInt == 108) {
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null)
        actionBar.dispatchMenuVisibilityChanged(false); 
    } else if (paramInt == 0) {
      PanelFeatureState panelFeatureState = getPanelState(paramInt, true);
      if (panelFeatureState.isOpen)
        closePanel(panelFeatureState, false); 
    } 
  }
  
  public void onPostCreate(Bundle paramBundle) {
    ensureSubDecor();
  }
  
  public void onPostResume() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(true); 
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    int i = this.mLocalNightMode;
    if (i != -100)
      paramBundle.putInt("appcompat:local_night_mode", i); 
  }
  
  public void onStart() {
    applyDayNight();
  }
  
  public void onStop() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(false); 
    AutoNightModeManager autoNightModeManager = this.mAutoNightModeManager;
    if (autoNightModeManager != null)
      autoNightModeManager.cleanup(); 
  }
  
  void onSubDecorInstalled(ViewGroup paramViewGroup) {}
  
  final ActionBar peekSupportActionBar() {
    return this.mActionBar;
  }
  
  public boolean requestWindowFeature(int paramInt) {
    paramInt = sanitizeWindowFeatureId(paramInt);
    if (this.mWindowNoTitle && paramInt == 108)
      return false; 
    if (this.mHasActionBar && paramInt == 1)
      this.mHasActionBar = false; 
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 5) {
          if (paramInt != 10) {
            if (paramInt != 108) {
              if (paramInt != 109)
                return this.mWindow.requestFeature(paramInt); 
              throwFeatureRequestIfSubDecorInstalled();
              this.mOverlayActionBar = true;
              return true;
            } 
            throwFeatureRequestIfSubDecorInstalled();
            this.mHasActionBar = true;
            return true;
          } 
          throwFeatureRequestIfSubDecorInstalled();
          this.mOverlayActionMode = true;
          return true;
        } 
        throwFeatureRequestIfSubDecorInstalled();
        this.mFeatureIndeterminateProgress = true;
        return true;
      } 
      throwFeatureRequestIfSubDecorInstalled();
      this.mFeatureProgress = true;
      return true;
    } 
    throwFeatureRequestIfSubDecorInstalled();
    this.mWindowNoTitle = true;
    return true;
  }
  
  public void setContentView(int paramInt) {
    ensureSubDecor();
    ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    viewGroup.removeAllViews();
    LayoutInflater.from(this.mContext).inflate(paramInt, viewGroup);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView) {
    ensureSubDecor();
    ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    viewGroup.removeAllViews();
    viewGroup.addView(paramView);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    ensureSubDecor();
    ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    viewGroup.removeAllViews();
    viewGroup.addView(paramView, paramLayoutParams);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public final void setTitle(CharSequence paramCharSequence) {
    this.mTitle = paramCharSequence;
    DecorContentParent decorContentParent = this.mDecorContentParent;
    if (decorContentParent != null) {
      decorContentParent.setWindowTitle(paramCharSequence);
    } else if (peekSupportActionBar() != null) {
      peekSupportActionBar().setWindowTitle(paramCharSequence);
    } else {
      TextView textView = this.mTitleView;
      if (textView != null)
        textView.setText(paramCharSequence); 
    } 
  }
  
  final boolean shouldAnimateActionModeView() {
    if (this.mSubDecorInstalled) {
      ViewGroup viewGroup = this.mSubDecor;
      if (viewGroup != null && ViewCompat.isLaidOut((View)viewGroup))
        return true; 
    } 
    return false;
  }
  
  public ActionMode startSupportActionMode(ActionMode.Callback paramCallback) {
    if (paramCallback != null) {
      ActionMode actionMode = this.mActionMode;
      if (actionMode != null)
        actionMode.finish(); 
      paramCallback = new ActionModeCallbackWrapperV9(paramCallback);
      ActionBar actionBar = getSupportActionBar();
      if (actionBar != null) {
        this.mActionMode = actionBar.startActionMode(paramCallback);
        ActionMode actionMode1 = this.mActionMode;
        if (actionMode1 != null) {
          AppCompatCallback appCompatCallback = this.mAppCompatCallback;
          if (appCompatCallback != null)
            appCompatCallback.onSupportActionModeStarted(actionMode1); 
        } 
      } 
      if (this.mActionMode == null)
        this.mActionMode = startSupportActionModeFromWindow(paramCallback); 
      return this.mActionMode;
    } 
    throw new IllegalArgumentException("ActionMode callback can not be null.");
  }
  
  ActionMode startSupportActionModeFromWindow(ActionMode.Callback paramCallback) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual endOnGoingFadeAnimation : ()V
    //   4: aload_0
    //   5: getfield mActionMode : Landroidx/appcompat/view/ActionMode;
    //   8: astore #4
    //   10: aload #4
    //   12: ifnull -> 20
    //   15: aload #4
    //   17: invokevirtual finish : ()V
    //   20: aload_1
    //   21: astore #4
    //   23: aload_1
    //   24: instanceof androidx/appcompat/app/AppCompatDelegateImpl$ActionModeCallbackWrapperV9
    //   27: ifne -> 41
    //   30: new androidx/appcompat/app/AppCompatDelegateImpl$ActionModeCallbackWrapperV9
    //   33: dup
    //   34: aload_0
    //   35: aload_1
    //   36: invokespecial <init> : (Landroidx/appcompat/app/AppCompatDelegateImpl;Landroidx/appcompat/view/ActionMode$Callback;)V
    //   39: astore #4
    //   41: aload_0
    //   42: getfield mAppCompatCallback : Landroidx/appcompat/app/AppCompatCallback;
    //   45: astore_1
    //   46: aload_1
    //   47: ifnull -> 69
    //   50: aload_0
    //   51: getfield mIsDestroyed : Z
    //   54: ifne -> 69
    //   57: aload_1
    //   58: aload #4
    //   60: invokeinterface onWindowStartingSupportActionMode : (Landroidx/appcompat/view/ActionMode$Callback;)Landroidx/appcompat/view/ActionMode;
    //   65: astore_1
    //   66: goto -> 71
    //   69: aconst_null
    //   70: astore_1
    //   71: aload_1
    //   72: ifnull -> 83
    //   75: aload_0
    //   76: aload_1
    //   77: putfield mActionMode : Landroidx/appcompat/view/ActionMode;
    //   80: goto -> 577
    //   83: aload_0
    //   84: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   87: astore_1
    //   88: iconst_1
    //   89: istore_3
    //   90: aload_1
    //   91: ifnonnull -> 353
    //   94: aload_0
    //   95: getfield mIsFloating : Z
    //   98: ifeq -> 313
    //   101: new android/util/TypedValue
    //   104: dup
    //   105: invokespecial <init> : ()V
    //   108: astore #5
    //   110: aload_0
    //   111: getfield mContext : Landroid/content/Context;
    //   114: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   117: astore_1
    //   118: aload_1
    //   119: getstatic androidx/appcompat/R$attr.actionBarTheme : I
    //   122: aload #5
    //   124: iconst_1
    //   125: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   128: pop
    //   129: aload #5
    //   131: getfield resourceId : I
    //   134: ifeq -> 191
    //   137: aload_0
    //   138: getfield mContext : Landroid/content/Context;
    //   141: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   144: invokevirtual newTheme : ()Landroid/content/res/Resources$Theme;
    //   147: astore #6
    //   149: aload #6
    //   151: aload_1
    //   152: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   155: aload #6
    //   157: aload #5
    //   159: getfield resourceId : I
    //   162: iconst_1
    //   163: invokevirtual applyStyle : (IZ)V
    //   166: new androidx/appcompat/view/ContextThemeWrapper
    //   169: dup
    //   170: aload_0
    //   171: getfield mContext : Landroid/content/Context;
    //   174: iconst_0
    //   175: invokespecial <init> : (Landroid/content/Context;I)V
    //   178: astore_1
    //   179: aload_1
    //   180: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   183: aload #6
    //   185: invokevirtual setTo : (Landroid/content/res/Resources$Theme;)V
    //   188: goto -> 196
    //   191: aload_0
    //   192: getfield mContext : Landroid/content/Context;
    //   195: astore_1
    //   196: aload_0
    //   197: new androidx/appcompat/widget/ActionBarContextView
    //   200: dup
    //   201: aload_1
    //   202: invokespecial <init> : (Landroid/content/Context;)V
    //   205: putfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   208: aload_0
    //   209: new android/widget/PopupWindow
    //   212: dup
    //   213: aload_1
    //   214: aconst_null
    //   215: getstatic androidx/appcompat/R$attr.actionModePopupWindowStyle : I
    //   218: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   221: putfield mActionModePopup : Landroid/widget/PopupWindow;
    //   224: aload_0
    //   225: getfield mActionModePopup : Landroid/widget/PopupWindow;
    //   228: iconst_2
    //   229: invokestatic setWindowLayoutType : (Landroid/widget/PopupWindow;I)V
    //   232: aload_0
    //   233: getfield mActionModePopup : Landroid/widget/PopupWindow;
    //   236: aload_0
    //   237: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   240: invokevirtual setContentView : (Landroid/view/View;)V
    //   243: aload_0
    //   244: getfield mActionModePopup : Landroid/widget/PopupWindow;
    //   247: iconst_m1
    //   248: invokevirtual setWidth : (I)V
    //   251: aload_1
    //   252: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   255: getstatic androidx/appcompat/R$attr.actionBarSize : I
    //   258: aload #5
    //   260: iconst_1
    //   261: invokevirtual resolveAttribute : (ILandroid/util/TypedValue;Z)Z
    //   264: pop
    //   265: aload #5
    //   267: getfield data : I
    //   270: aload_1
    //   271: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   274: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   277: invokestatic complexToDimensionPixelSize : (ILandroid/util/DisplayMetrics;)I
    //   280: istore_2
    //   281: aload_0
    //   282: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   285: iload_2
    //   286: invokevirtual setContentHeight : (I)V
    //   289: aload_0
    //   290: getfield mActionModePopup : Landroid/widget/PopupWindow;
    //   293: bipush #-2
    //   295: invokevirtual setHeight : (I)V
    //   298: aload_0
    //   299: new androidx/appcompat/app/AppCompatDelegateImpl$6
    //   302: dup
    //   303: aload_0
    //   304: invokespecial <init> : (Landroidx/appcompat/app/AppCompatDelegateImpl;)V
    //   307: putfield mShowActionModePopup : Ljava/lang/Runnable;
    //   310: goto -> 353
    //   313: aload_0
    //   314: getfield mSubDecor : Landroid/view/ViewGroup;
    //   317: getstatic androidx/appcompat/R$id.action_mode_bar_stub : I
    //   320: invokevirtual findViewById : (I)Landroid/view/View;
    //   323: checkcast androidx/appcompat/widget/ViewStubCompat
    //   326: astore_1
    //   327: aload_1
    //   328: ifnull -> 353
    //   331: aload_1
    //   332: aload_0
    //   333: invokevirtual getActionBarThemedContext : ()Landroid/content/Context;
    //   336: invokestatic from : (Landroid/content/Context;)Landroid/view/LayoutInflater;
    //   339: invokevirtual setLayoutInflater : (Landroid/view/LayoutInflater;)V
    //   342: aload_0
    //   343: aload_1
    //   344: invokevirtual inflate : ()Landroid/view/View;
    //   347: checkcast androidx/appcompat/widget/ActionBarContextView
    //   350: putfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   353: aload_0
    //   354: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   357: ifnull -> 577
    //   360: aload_0
    //   361: invokevirtual endOnGoingFadeAnimation : ()V
    //   364: aload_0
    //   365: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   368: invokevirtual killMode : ()V
    //   371: aload_0
    //   372: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   375: invokevirtual getContext : ()Landroid/content/Context;
    //   378: astore #5
    //   380: aload_0
    //   381: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   384: astore_1
    //   385: aload_0
    //   386: getfield mActionModePopup : Landroid/widget/PopupWindow;
    //   389: ifnonnull -> 395
    //   392: goto -> 397
    //   395: iconst_0
    //   396: istore_3
    //   397: new androidx/appcompat/view/StandaloneActionMode
    //   400: dup
    //   401: aload #5
    //   403: aload_1
    //   404: aload #4
    //   406: iload_3
    //   407: invokespecial <init> : (Landroid/content/Context;Landroidx/appcompat/widget/ActionBarContextView;Landroidx/appcompat/view/ActionMode$Callback;Z)V
    //   410: astore_1
    //   411: aload #4
    //   413: aload_1
    //   414: aload_1
    //   415: invokevirtual getMenu : ()Landroid/view/Menu;
    //   418: invokeinterface onCreateActionMode : (Landroidx/appcompat/view/ActionMode;Landroid/view/Menu;)Z
    //   423: ifeq -> 572
    //   426: aload_1
    //   427: invokevirtual invalidate : ()V
    //   430: aload_0
    //   431: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   434: aload_1
    //   435: invokevirtual initForMode : (Landroidx/appcompat/view/ActionMode;)V
    //   438: aload_0
    //   439: aload_1
    //   440: putfield mActionMode : Landroidx/appcompat/view/ActionMode;
    //   443: aload_0
    //   444: invokevirtual shouldAnimateActionModeView : ()Z
    //   447: ifeq -> 496
    //   450: aload_0
    //   451: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   454: fconst_0
    //   455: invokevirtual setAlpha : (F)V
    //   458: aload_0
    //   459: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   462: invokestatic animate : (Landroid/view/View;)Landroidx/core/view/ViewPropertyAnimatorCompat;
    //   465: astore_1
    //   466: aload_1
    //   467: fconst_1
    //   468: invokevirtual alpha : (F)Landroidx/core/view/ViewPropertyAnimatorCompat;
    //   471: pop
    //   472: aload_0
    //   473: aload_1
    //   474: putfield mFadeAnim : Landroidx/core/view/ViewPropertyAnimatorCompat;
    //   477: aload_0
    //   478: getfield mFadeAnim : Landroidx/core/view/ViewPropertyAnimatorCompat;
    //   481: new androidx/appcompat/app/AppCompatDelegateImpl$7
    //   484: dup
    //   485: aload_0
    //   486: invokespecial <init> : (Landroidx/appcompat/app/AppCompatDelegateImpl;)V
    //   489: invokevirtual setListener : (Landroidx/core/view/ViewPropertyAnimatorListener;)Landroidx/core/view/ViewPropertyAnimatorCompat;
    //   492: pop
    //   493: goto -> 547
    //   496: aload_0
    //   497: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   500: fconst_1
    //   501: invokevirtual setAlpha : (F)V
    //   504: aload_0
    //   505: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   508: iconst_0
    //   509: invokevirtual setVisibility : (I)V
    //   512: aload_0
    //   513: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   516: bipush #32
    //   518: invokevirtual sendAccessibilityEvent : (I)V
    //   521: aload_0
    //   522: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   525: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   528: instanceof android/view/View
    //   531: ifeq -> 547
    //   534: aload_0
    //   535: getfield mActionModeView : Landroidx/appcompat/widget/ActionBarContextView;
    //   538: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   541: checkcast android/view/View
    //   544: invokestatic requestApplyInsets : (Landroid/view/View;)V
    //   547: aload_0
    //   548: getfield mActionModePopup : Landroid/widget/PopupWindow;
    //   551: ifnull -> 577
    //   554: aload_0
    //   555: getfield mWindow : Landroid/view/Window;
    //   558: invokevirtual getDecorView : ()Landroid/view/View;
    //   561: aload_0
    //   562: getfield mShowActionModePopup : Ljava/lang/Runnable;
    //   565: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   568: pop
    //   569: goto -> 577
    //   572: aload_0
    //   573: aconst_null
    //   574: putfield mActionMode : Landroidx/appcompat/view/ActionMode;
    //   577: aload_0
    //   578: getfield mActionMode : Landroidx/appcompat/view/ActionMode;
    //   581: astore #4
    //   583: aload #4
    //   585: ifnull -> 605
    //   588: aload_0
    //   589: getfield mAppCompatCallback : Landroidx/appcompat/app/AppCompatCallback;
    //   592: astore_1
    //   593: aload_1
    //   594: ifnull -> 605
    //   597: aload_1
    //   598: aload #4
    //   600: invokeinterface onSupportActionModeStarted : (Landroidx/appcompat/view/ActionMode;)V
    //   605: aload_0
    //   606: getfield mActionMode : Landroidx/appcompat/view/ActionMode;
    //   609: areturn
    //   610: astore_1
    //   611: goto -> 69
    // Exception table:
    //   from	to	target	type
    //   57	66	610	java/lang/AbstractMethodError
  }
  
  int updateStatusGuard(int paramInt) {
    boolean bool1;
    ActionBarContextView actionBarContextView = this.mActionModeView;
    boolean bool2 = false;
    if (actionBarContextView != null && actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
      int i;
      boolean bool3;
      boolean bool5;
      ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.mActionModeView.getLayoutParams();
      boolean bool = this.mActionModeView.isShown();
      boolean bool4 = true;
      if (bool) {
        boolean bool6;
        if (this.mTempRect1 == null) {
          this.mTempRect1 = new Rect();
          this.mTempRect2 = new Rect();
        } 
        Rect rect1 = this.mTempRect1;
        Rect rect2 = this.mTempRect2;
        rect1.set(0, paramInt, 0, 0);
        ViewUtils.computeFitSystemWindows((View)this.mSubDecor, rect1, rect2);
        if (rect2.top == 0) {
          i = paramInt;
        } else {
          i = 0;
        } 
        if (marginLayoutParams.topMargin != i) {
          marginLayoutParams.topMargin = paramInt;
          View view1 = this.mStatusGuard;
          if (view1 == null) {
            this.mStatusGuard = new View(this.mContext);
            this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(R.color.abc_input_method_navigation_guard));
            this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup.LayoutParams(-1, paramInt));
          } else {
            ViewGroup.LayoutParams layoutParams = view1.getLayoutParams();
            if (layoutParams.height != paramInt) {
              layoutParams.height = paramInt;
              this.mStatusGuard.setLayoutParams(layoutParams);
            } 
          } 
          bool6 = true;
        } else {
          bool6 = false;
        } 
        if (this.mStatusGuard == null)
          bool4 = false; 
        bool5 = bool6;
        bool3 = bool4;
        i = paramInt;
        if (!this.mOverlayActionMode) {
          bool5 = bool6;
          bool3 = bool4;
          i = paramInt;
          if (bool4) {
            i = 0;
            bool5 = bool6;
            bool3 = bool4;
          } 
        } 
      } else {
        boolean bool6;
        if (marginLayoutParams.topMargin != 0) {
          marginLayoutParams.topMargin = 0;
          bool6 = true;
        } else {
          bool6 = false;
        } 
        bool3 = false;
        i = paramInt;
        bool5 = bool6;
      } 
      bool1 = bool3;
      paramInt = i;
      if (bool5) {
        this.mActionModeView.setLayoutParams((ViewGroup.LayoutParams)marginLayoutParams);
        bool1 = bool3;
        paramInt = i;
      } 
    } else {
      bool1 = false;
    } 
    View view = this.mStatusGuard;
    if (view != null) {
      byte b;
      if (bool1) {
        b = bool2;
      } else {
        b = 8;
      } 
      view.setVisibility(b);
    } 
    return paramInt;
  }
  
  static {
    boolean bool;
    if (Build.VERSION.SDK_INT < 21) {
      bool = true;
    } else {
      bool = false;
    } 
    IS_PRE_LOLLIPOP = bool;
  }
  
  private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
    final AppCompatDelegateImpl this$0;
    
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {
      AppCompatDelegateImpl.this.checkCloseActionMenu(param1MenuBuilder);
    }
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      Window.Callback callback = AppCompatDelegateImpl.this.getWindowCallback();
      if (callback != null)
        callback.onMenuOpened(108, (Menu)param1MenuBuilder); 
      return true;
    }
  }
  
  class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
    private ActionMode.Callback mWrapped;
    
    final AppCompatDelegateImpl this$0;
    
    public ActionModeCallbackWrapperV9(ActionMode.Callback param1Callback) {
      this.mWrapped = param1Callback;
    }
    
    public boolean onActionItemClicked(ActionMode param1ActionMode, MenuItem param1MenuItem) {
      return this.mWrapped.onActionItemClicked(param1ActionMode, param1MenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return this.mWrapped.onCreateActionMode(param1ActionMode, param1Menu);
    }
    
    public void onDestroyActionMode(ActionMode param1ActionMode) {
      this.mWrapped.onDestroyActionMode(param1ActionMode);
      AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
      if (appCompatDelegateImpl.mActionModePopup != null)
        appCompatDelegateImpl.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.mShowActionModePopup); 
      appCompatDelegateImpl = AppCompatDelegateImpl.this;
      if (appCompatDelegateImpl.mActionModeView != null) {
        appCompatDelegateImpl.endOnGoingFadeAnimation();
        appCompatDelegateImpl = AppCompatDelegateImpl.this;
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate((View)appCompatDelegateImpl.mActionModeView);
        viewPropertyAnimatorCompat.alpha(0.0F);
        appCompatDelegateImpl.mFadeAnim = viewPropertyAnimatorCompat;
        AppCompatDelegateImpl.this.mFadeAnim.setListener((ViewPropertyAnimatorListener)new ViewPropertyAnimatorListenerAdapter() {
              final AppCompatDelegateImpl.ActionModeCallbackWrapperV9 this$1;
              
              public void onAnimationEnd(View param2View) {
                AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.mActionModePopup;
                if (popupWindow != null) {
                  popupWindow.dismiss();
                } else if (appCompatDelegateImpl.mActionModeView.getParent() instanceof View) {
                  ViewCompat.requestApplyInsets((View)AppCompatDelegateImpl.this.mActionModeView.getParent());
                } 
                AppCompatDelegateImpl.this.mActionModeView.removeAllViews();
                AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                AppCompatDelegateImpl.this.mFadeAnim = null;
              }
            });
      } 
      appCompatDelegateImpl = AppCompatDelegateImpl.this;
      AppCompatCallback appCompatCallback = appCompatDelegateImpl.mAppCompatCallback;
      if (appCompatCallback != null)
        appCompatCallback.onSupportActionModeFinished(appCompatDelegateImpl.mActionMode); 
      AppCompatDelegateImpl.this.mActionMode = null;
    }
    
    public boolean onPrepareActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return this.mWrapped.onPrepareActionMode(param1ActionMode, param1Menu);
    }
  }
  
  class null extends ViewPropertyAnimatorListenerAdapter {
    final AppCompatDelegateImpl.ActionModeCallbackWrapperV9 this$1;
    
    public void onAnimationEnd(View param1View) {
      AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
      AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
      PopupWindow popupWindow = appCompatDelegateImpl.mActionModePopup;
      if (popupWindow != null) {
        popupWindow.dismiss();
      } else if (appCompatDelegateImpl.mActionModeView.getParent() instanceof View) {
        ViewCompat.requestApplyInsets((View)AppCompatDelegateImpl.this.mActionModeView.getParent());
      } 
      AppCompatDelegateImpl.this.mActionModeView.removeAllViews();
      AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
      AppCompatDelegateImpl.this.mFadeAnim = null;
    }
  }
  
  class AppCompatWindowCallback extends WindowCallbackWrapper {
    final AppCompatDelegateImpl this$0;
    
    AppCompatWindowCallback(Window.Callback param1Callback) {
      super(param1Callback);
    }
    
    public boolean dispatchKeyEvent(KeyEvent param1KeyEvent) {
      return (AppCompatDelegateImpl.this.dispatchKeyEvent(param1KeyEvent) || super.dispatchKeyEvent(param1KeyEvent));
    }
    
    public boolean dispatchKeyShortcutEvent(KeyEvent param1KeyEvent) {
      return (super.dispatchKeyShortcutEvent(param1KeyEvent) || AppCompatDelegateImpl.this.onKeyShortcut(param1KeyEvent.getKeyCode(), param1KeyEvent));
    }
    
    public void onContentChanged() {}
    
    public boolean onCreatePanelMenu(int param1Int, Menu param1Menu) {
      return (param1Int == 0 && !(param1Menu instanceof MenuBuilder)) ? false : super.onCreatePanelMenu(param1Int, param1Menu);
    }
    
    public boolean onMenuOpened(int param1Int, Menu param1Menu) {
      super.onMenuOpened(param1Int, param1Menu);
      AppCompatDelegateImpl.this.onMenuOpened(param1Int);
      return true;
    }
    
    public void onPanelClosed(int param1Int, Menu param1Menu) {
      super.onPanelClosed(param1Int, param1Menu);
      AppCompatDelegateImpl.this.onPanelClosed(param1Int);
    }
    
    public boolean onPreparePanel(int param1Int, View param1View, Menu param1Menu) {
      MenuBuilder menuBuilder;
      if (param1Menu instanceof MenuBuilder) {
        menuBuilder = (MenuBuilder)param1Menu;
      } else {
        menuBuilder = null;
      } 
      if (param1Int == 0 && menuBuilder == null)
        return false; 
      if (menuBuilder != null)
        menuBuilder.setOverrideVisibleItems(true); 
      boolean bool = super.onPreparePanel(param1Int, param1View, param1Menu);
      if (menuBuilder != null)
        menuBuilder.setOverrideVisibleItems(false); 
      return bool;
    }
    
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> param1List, Menu param1Menu, int param1Int) {
      AppCompatDelegateImpl.PanelFeatureState panelFeatureState = AppCompatDelegateImpl.this.getPanelState(0, true);
      if (panelFeatureState != null) {
        MenuBuilder menuBuilder = panelFeatureState.menu;
        if (menuBuilder != null) {
          super.onProvideKeyboardShortcuts(param1List, (Menu)menuBuilder, param1Int);
          return;
        } 
      } 
      super.onProvideKeyboardShortcuts(param1List, param1Menu, param1Int);
    }
    
    public ActionMode onWindowStartingActionMode(ActionMode.Callback param1Callback) {
      return (Build.VERSION.SDK_INT >= 23) ? null : (AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() ? startAsSupportActionMode(param1Callback) : super.onWindowStartingActionMode(param1Callback));
    }
    
    public ActionMode onWindowStartingActionMode(ActionMode.Callback param1Callback, int param1Int) {
      return (!AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() || param1Int != 0) ? super.onWindowStartingActionMode(param1Callback, param1Int) : startAsSupportActionMode(param1Callback);
    }
    
    final ActionMode startAsSupportActionMode(ActionMode.Callback param1Callback) {
      SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImpl.this.mContext, param1Callback);
      ActionMode actionMode = AppCompatDelegateImpl.this.startSupportActionMode((ActionMode.Callback)callbackWrapper);
      return (actionMode != null) ? callbackWrapper.getActionModeWrapper(actionMode) : null;
    }
  }
  
  final class AutoNightModeManager {
    private BroadcastReceiver mAutoTimeChangeReceiver;
    
    private IntentFilter mAutoTimeChangeReceiverFilter;
    
    private boolean mIsNight;
    
    private TwilightManager mTwilightManager;
    
    final AppCompatDelegateImpl this$0;
    
    AutoNightModeManager(TwilightManager param1TwilightManager) {
      this.mTwilightManager = param1TwilightManager;
      this.mIsNight = param1TwilightManager.isNight();
    }
    
    void cleanup() {
      BroadcastReceiver broadcastReceiver = this.mAutoTimeChangeReceiver;
      if (broadcastReceiver != null) {
        AppCompatDelegateImpl.this.mContext.unregisterReceiver(broadcastReceiver);
        this.mAutoTimeChangeReceiver = null;
      } 
    }
    
    void dispatchTimeChanged() {
      boolean bool = this.mTwilightManager.isNight();
      if (bool != this.mIsNight) {
        this.mIsNight = bool;
        AppCompatDelegateImpl.this.applyDayNight();
      } 
    }
    
    int getApplyableNightMode() {
      boolean bool;
      this.mIsNight = this.mTwilightManager.isNight();
      if (this.mIsNight) {
        bool = true;
      } else {
        bool = true;
      } 
      return bool;
    }
    
    void setup() {
      cleanup();
      if (this.mAutoTimeChangeReceiver == null)
        this.mAutoTimeChangeReceiver = new BroadcastReceiver() {
            final AppCompatDelegateImpl.AutoNightModeManager this$1;
            
            public void onReceive(Context param2Context, Intent param2Intent) {
              AppCompatDelegateImpl.AutoNightModeManager.this.dispatchTimeChanged();
            }
          }; 
      if (this.mAutoTimeChangeReceiverFilter == null) {
        this.mAutoTimeChangeReceiverFilter = new IntentFilter();
        this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
        this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
      } 
      AppCompatDelegateImpl.this.mContext.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
    }
  }
  
  class null extends BroadcastReceiver {
    final AppCompatDelegateImpl.AutoNightModeManager this$1;
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      this.this$1.dispatchTimeChanged();
    }
  }
  
  private class ListMenuDecorView extends ContentFrameLayout {
    final AppCompatDelegateImpl this$0;
    
    public ListMenuDecorView(Context param1Context) {
      super(param1Context);
    }
    
    private boolean isOutOfBounds(int param1Int1, int param1Int2) {
      return (param1Int1 < -5 || param1Int2 < -5 || param1Int1 > getWidth() + 5 || param1Int2 > getHeight() + 5);
    }
    
    public boolean dispatchKeyEvent(KeyEvent param1KeyEvent) {
      return (AppCompatDelegateImpl.this.dispatchKeyEvent(param1KeyEvent) || super.dispatchKeyEvent(param1KeyEvent));
    }
    
    public boolean onInterceptTouchEvent(MotionEvent param1MotionEvent) {
      if (param1MotionEvent.getAction() == 0 && isOutOfBounds((int)param1MotionEvent.getX(), (int)param1MotionEvent.getY())) {
        AppCompatDelegateImpl.this.closePanel(0);
        return true;
      } 
      return super.onInterceptTouchEvent(param1MotionEvent);
    }
    
    public void setBackgroundResource(int param1Int) {
      setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), param1Int));
    }
  }
  
  protected static final class PanelFeatureState {
    int background;
    
    View createdPanelView;
    
    ViewGroup decorView;
    
    int featureId;
    
    Bundle frozenActionViewState;
    
    int gravity;
    
    boolean isHandled;
    
    boolean isOpen;
    
    boolean isPrepared;
    
    ListMenuPresenter listMenuPresenter;
    
    Context listPresenterContext;
    
    MenuBuilder menu;
    
    public boolean qwertyMode;
    
    boolean refreshDecorView;
    
    boolean refreshMenuContent;
    
    View shownPanelView;
    
    int windowAnimations;
    
    int x;
    
    int y;
    
    PanelFeatureState(int param1Int) {
      this.featureId = param1Int;
      this.refreshDecorView = false;
    }
    
    MenuView getListMenuView(MenuPresenter.Callback param1Callback) {
      if (this.menu == null)
        return null; 
      if (this.listMenuPresenter == null) {
        this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R.layout.abc_list_menu_item_layout);
        this.listMenuPresenter.setCallback(param1Callback);
        this.menu.addMenuPresenter((MenuPresenter)this.listMenuPresenter);
      } 
      return this.listMenuPresenter.getMenuView(this.decorView);
    }
    
    public boolean hasPanelItems() {
      View view = this.shownPanelView;
      boolean bool = false;
      if (view == null)
        return false; 
      if (this.createdPanelView != null)
        return true; 
      if (this.listMenuPresenter.getAdapter().getCount() > 0)
        bool = true; 
      return bool;
    }
    
    void setMenu(MenuBuilder param1MenuBuilder) {
      MenuBuilder menuBuilder = this.menu;
      if (param1MenuBuilder == menuBuilder)
        return; 
      if (menuBuilder != null)
        menuBuilder.removeMenuPresenter((MenuPresenter)this.listMenuPresenter); 
      this.menu = param1MenuBuilder;
      if (param1MenuBuilder != null) {
        ListMenuPresenter listMenuPresenter = this.listMenuPresenter;
        if (listMenuPresenter != null)
          param1MenuBuilder.addMenuPresenter((MenuPresenter)listMenuPresenter); 
      } 
    }
    
    void setStyle(Context param1Context) {
      TypedValue typedValue = new TypedValue();
      Resources.Theme theme = param1Context.getResources().newTheme();
      theme.setTo(param1Context.getTheme());
      theme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
      int i = typedValue.resourceId;
      if (i != 0)
        theme.applyStyle(i, true); 
      theme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
      i = typedValue.resourceId;
      if (i != 0) {
        theme.applyStyle(i, true);
      } else {
        theme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
      } 
      ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(param1Context, 0);
      contextThemeWrapper.getTheme().setTo(theme);
      this.listPresenterContext = (Context)contextThemeWrapper;
      TypedArray typedArray = contextThemeWrapper.obtainStyledAttributes(R.styleable.AppCompatTheme);
      this.background = typedArray.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
      this.windowAnimations = typedArray.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
      typedArray.recycle();
    }
  }
  
  private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
    final AppCompatDelegateImpl this$0;
    
    public void onCloseMenu(MenuBuilder param1MenuBuilder, boolean param1Boolean) {
      boolean bool;
      MenuBuilder menuBuilder = param1MenuBuilder.getRootMenu();
      if (menuBuilder != param1MenuBuilder) {
        bool = true;
      } else {
        bool = false;
      } 
      AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
      if (bool)
        param1MenuBuilder = menuBuilder; 
      AppCompatDelegateImpl.PanelFeatureState panelFeatureState = appCompatDelegateImpl.findMenuPanel((Menu)param1MenuBuilder);
      if (panelFeatureState != null)
        if (bool) {
          AppCompatDelegateImpl.this.callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, (Menu)menuBuilder);
          AppCompatDelegateImpl.this.closePanel(panelFeatureState, true);
        } else {
          AppCompatDelegateImpl.this.closePanel(panelFeatureState, param1Boolean);
        }  
    }
    
    public boolean onOpenSubMenu(MenuBuilder param1MenuBuilder) {
      if (param1MenuBuilder == null) {
        AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
        if (appCompatDelegateImpl.mHasActionBar) {
          Window.Callback callback = appCompatDelegateImpl.getWindowCallback();
          if (callback != null && !AppCompatDelegateImpl.this.mIsDestroyed)
            callback.onMenuOpened(108, (Menu)param1MenuBuilder); 
        } 
      } 
      return true;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/app/AppCompatDelegateImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */