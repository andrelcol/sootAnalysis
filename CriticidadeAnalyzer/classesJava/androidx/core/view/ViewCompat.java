package androidx.core.view;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import androidx.core.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewCompat {
  private static Field sMinHeightField;
  
  private static boolean sMinHeightFieldFetched;
  
  private static ThreadLocal<Rect> sThreadLocalRect;
  
  private static WeakHashMap<View, String> sTransitionNameMap;
  
  private static WeakHashMap<View, ViewPropertyAnimatorCompat> sViewPropertyAnimatorMap = null;
  
  public static ViewPropertyAnimatorCompat animate(View paramView) {
    if (sViewPropertyAnimatorMap == null)
      sViewPropertyAnimatorMap = new WeakHashMap<View, ViewPropertyAnimatorCompat>(); 
    ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = sViewPropertyAnimatorMap.get(paramView);
    ViewPropertyAnimatorCompat viewPropertyAnimatorCompat1 = viewPropertyAnimatorCompat2;
    if (viewPropertyAnimatorCompat2 == null) {
      viewPropertyAnimatorCompat1 = new ViewPropertyAnimatorCompat(paramView);
      sViewPropertyAnimatorMap.put(paramView, viewPropertyAnimatorCompat1);
    } 
    return viewPropertyAnimatorCompat1;
  }
  
  private static void compatOffsetLeftAndRight(View paramView, int paramInt) {
    paramView.offsetLeftAndRight(paramInt);
    if (paramView.getVisibility() == 0) {
      tickleInvalidationFlag(paramView);
      ViewParent viewParent = paramView.getParent();
      if (viewParent instanceof View)
        tickleInvalidationFlag((View)viewParent); 
    } 
  }
  
  private static void compatOffsetTopAndBottom(View paramView, int paramInt) {
    paramView.offsetTopAndBottom(paramInt);
    if (paramView.getVisibility() == 0) {
      tickleInvalidationFlag(paramView);
      ViewParent viewParent = paramView.getParent();
      if (viewParent instanceof View)
        tickleInvalidationFlag((View)viewParent); 
    } 
  }
  
  public static WindowInsetsCompat dispatchApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat) {
    WindowInsets windowInsets;
    if (Build.VERSION.SDK_INT >= 21) {
      windowInsets = (WindowInsets)WindowInsetsCompat.unwrap(paramWindowInsetsCompat);
      WindowInsets windowInsets2 = paramView.dispatchApplyWindowInsets(windowInsets);
      WindowInsets windowInsets1 = windowInsets;
      if (windowInsets2 != windowInsets)
        windowInsets1 = new WindowInsets(windowInsets2); 
      return WindowInsetsCompat.wrap(windowInsets1);
    } 
    return (WindowInsetsCompat)windowInsets;
  }
  
  static boolean dispatchUnhandledKeyEventBeforeCallback(View paramView, KeyEvent paramKeyEvent) {
    return (Build.VERSION.SDK_INT >= 28) ? false : UnhandledKeyEventManager.at(paramView).dispatch(paramView, paramKeyEvent);
  }
  
  static boolean dispatchUnhandledKeyEventBeforeHierarchy(View paramView, KeyEvent paramKeyEvent) {
    return (Build.VERSION.SDK_INT >= 28) ? false : UnhandledKeyEventManager.at(paramView).preDispatch(paramKeyEvent);
  }
  
  public static ColorStateList getBackgroundTintList(View paramView) {
    if (Build.VERSION.SDK_INT >= 21)
      return paramView.getBackgroundTintList(); 
    if (paramView instanceof TintableBackgroundView) {
      ColorStateList colorStateList = ((TintableBackgroundView)paramView).getSupportBackgroundTintList();
    } else {
      paramView = null;
    } 
    return (ColorStateList)paramView;
  }
  
  public static PorterDuff.Mode getBackgroundTintMode(View paramView) {
    if (Build.VERSION.SDK_INT >= 21)
      return paramView.getBackgroundTintMode(); 
    if (paramView instanceof TintableBackgroundView) {
      PorterDuff.Mode mode = ((TintableBackgroundView)paramView).getSupportBackgroundTintMode();
    } else {
      paramView = null;
    } 
    return (PorterDuff.Mode)paramView;
  }
  
  public static float getElevation(View paramView) {
    return (Build.VERSION.SDK_INT >= 21) ? paramView.getElevation() : 0.0F;
  }
  
  private static Rect getEmptyTempRect() {
    if (sThreadLocalRect == null)
      sThreadLocalRect = new ThreadLocal<Rect>(); 
    Rect rect2 = sThreadLocalRect.get();
    Rect rect1 = rect2;
    if (rect2 == null) {
      rect1 = new Rect();
      sThreadLocalRect.set(rect1);
    } 
    rect1.setEmpty();
    return rect1;
  }
  
  public static boolean getFitsSystemWindows(View paramView) {
    return (Build.VERSION.SDK_INT >= 16) ? paramView.getFitsSystemWindows() : false;
  }
  
  public static int getImportantForAccessibility(View paramView) {
    return (Build.VERSION.SDK_INT >= 16) ? paramView.getImportantForAccessibility() : 0;
  }
  
  public static int getLayoutDirection(View paramView) {
    return (Build.VERSION.SDK_INT >= 17) ? paramView.getLayoutDirection() : 0;
  }
  
  public static int getMinimumHeight(View paramView) {
    if (Build.VERSION.SDK_INT >= 16)
      return paramView.getMinimumHeight(); 
    if (!sMinHeightFieldFetched) {
      try {
        sMinHeightField = View.class.getDeclaredField("mMinHeight");
        sMinHeightField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {}
      sMinHeightFieldFetched = true;
    } 
    Field field = sMinHeightField;
    if (field != null)
      try {
        return ((Integer)field.get(paramView)).intValue();
      } catch (Exception exception) {} 
    return 0;
  }
  
  public static ViewParent getParentForAccessibility(View paramView) {
    return (Build.VERSION.SDK_INT >= 16) ? paramView.getParentForAccessibility() : paramView.getParent();
  }
  
  public static String getTransitionName(View paramView) {
    if (Build.VERSION.SDK_INT >= 21)
      return paramView.getTransitionName(); 
    WeakHashMap<View, String> weakHashMap = sTransitionNameMap;
    return (weakHashMap == null) ? null : weakHashMap.get(paramView);
  }
  
  public static int getWindowSystemUiVisibility(View paramView) {
    return (Build.VERSION.SDK_INT >= 16) ? paramView.getWindowSystemUiVisibility() : 0;
  }
  
  public static boolean hasOnClickListeners(View paramView) {
    return (Build.VERSION.SDK_INT >= 15) ? paramView.hasOnClickListeners() : false;
  }
  
  public static boolean hasOverlappingRendering(View paramView) {
    return (Build.VERSION.SDK_INT >= 16) ? paramView.hasOverlappingRendering() : true;
  }
  
  public static boolean isAttachedToWindow(View paramView) {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 19)
      return paramView.isAttachedToWindow(); 
    if (paramView.getWindowToken() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isLaidOut(View paramView) {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 19)
      return paramView.isLaidOut(); 
    if (paramView.getWidth() > 0 && paramView.getHeight() > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isNestedScrollingEnabled(View paramView) {
    return (Build.VERSION.SDK_INT >= 21) ? paramView.isNestedScrollingEnabled() : ((paramView instanceof NestedScrollingChild) ? ((NestedScrollingChild)paramView).isNestedScrollingEnabled() : false);
  }
  
  public static void offsetLeftAndRight(View paramView, int paramInt) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      paramView.offsetLeftAndRight(paramInt);
    } else if (i >= 21) {
      Rect rect = getEmptyTempRect();
      i = 0;
      ViewParent viewParent = paramView.getParent();
      if (viewParent instanceof View) {
        View view = (View)viewParent;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        i = rect.intersects(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom()) ^ true;
      } 
      compatOffsetLeftAndRight(paramView, paramInt);
      if (i != 0 && rect.intersect(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom()))
        ((View)viewParent).invalidate(rect); 
    } else {
      compatOffsetLeftAndRight(paramView, paramInt);
    } 
  }
  
  public static void offsetTopAndBottom(View paramView, int paramInt) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      paramView.offsetTopAndBottom(paramInt);
    } else if (i >= 21) {
      Rect rect = getEmptyTempRect();
      i = 0;
      ViewParent viewParent = paramView.getParent();
      if (viewParent instanceof View) {
        View view = (View)viewParent;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        i = rect.intersects(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom()) ^ true;
      } 
      compatOffsetTopAndBottom(paramView, paramInt);
      if (i != 0 && rect.intersect(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom()))
        ((View)viewParent).invalidate(rect); 
    } else {
      compatOffsetTopAndBottom(paramView, paramInt);
    } 
  }
  
  public static WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat) {
    WindowInsets windowInsets;
    if (Build.VERSION.SDK_INT >= 21) {
      windowInsets = (WindowInsets)WindowInsetsCompat.unwrap(paramWindowInsetsCompat);
      WindowInsets windowInsets2 = paramView.onApplyWindowInsets(windowInsets);
      WindowInsets windowInsets1 = windowInsets;
      if (windowInsets2 != windowInsets)
        windowInsets1 = new WindowInsets(windowInsets2); 
      return WindowInsetsCompat.wrap(windowInsets1);
    } 
    return (WindowInsetsCompat)windowInsets;
  }
  
  public static void postInvalidateOnAnimation(View paramView) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramView.postInvalidateOnAnimation();
    } else {
      paramView.postInvalidate();
    } 
  }
  
  public static void postOnAnimation(View paramView, Runnable paramRunnable) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramView.postOnAnimation(paramRunnable);
    } else {
      paramView.postDelayed(paramRunnable, ValueAnimator.getFrameDelay());
    } 
  }
  
  public static void postOnAnimationDelayed(View paramView, Runnable paramRunnable, long paramLong) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramView.postOnAnimationDelayed(paramRunnable, paramLong);
    } else {
      paramView.postDelayed(paramRunnable, ValueAnimator.getFrameDelay() + paramLong);
    } 
  }
  
  public static void requestApplyInsets(View paramView) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 20) {
      paramView.requestApplyInsets();
    } else if (i >= 16) {
      paramView.requestFitSystemWindows();
    } 
  }
  
  public static void setAccessibilityDelegate(View paramView, AccessibilityDelegateCompat paramAccessibilityDelegateCompat) {
    View.AccessibilityDelegate accessibilityDelegate;
    if (paramAccessibilityDelegateCompat == null) {
      paramAccessibilityDelegateCompat = null;
    } else {
      accessibilityDelegate = paramAccessibilityDelegateCompat.getBridge();
    } 
    paramView.setAccessibilityDelegate(accessibilityDelegate);
  }
  
  public static void setBackground(View paramView, Drawable paramDrawable) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramView.setBackground(paramDrawable);
    } else {
      paramView.setBackgroundDrawable(paramDrawable);
    } 
  }
  
  public static void setBackgroundTintList(View paramView, ColorStateList paramColorStateList) {
    Drawable drawable;
    if (Build.VERSION.SDK_INT >= 21) {
      paramView.setBackgroundTintList(paramColorStateList);
      if (Build.VERSION.SDK_INT == 21) {
        boolean bool;
        drawable = paramView.getBackground();
        if (paramView.getBackgroundTintList() != null || paramView.getBackgroundTintMode() != null) {
          bool = true;
        } else {
          bool = false;
        } 
        if (drawable != null && bool) {
          if (drawable.isStateful())
            drawable.setState(paramView.getDrawableState()); 
          paramView.setBackground(drawable);
        } 
      } 
    } else if (paramView instanceof TintableBackgroundView) {
      ((TintableBackgroundView)paramView).setSupportBackgroundTintList((ColorStateList)drawable);
    } 
  }
  
  public static void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode) {
    Drawable drawable;
    if (Build.VERSION.SDK_INT >= 21) {
      paramView.setBackgroundTintMode(paramMode);
      if (Build.VERSION.SDK_INT == 21) {
        boolean bool;
        drawable = paramView.getBackground();
        if (paramView.getBackgroundTintList() != null || paramView.getBackgroundTintMode() != null) {
          bool = true;
        } else {
          bool = false;
        } 
        if (drawable != null && bool) {
          if (drawable.isStateful())
            drawable.setState(paramView.getDrawableState()); 
          paramView.setBackground(drawable);
        } 
      } 
    } else if (paramView instanceof TintableBackgroundView) {
      ((TintableBackgroundView)paramView).setSupportBackgroundTintMode((PorterDuff.Mode)drawable);
    } 
  }
  
  public static void setElevation(View paramView, float paramFloat) {
    if (Build.VERSION.SDK_INT >= 21)
      paramView.setElevation(paramFloat); 
  }
  
  public static void setImportantForAccessibility(View paramView, int paramInt) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 19) {
      paramView.setImportantForAccessibility(paramInt);
    } else if (i >= 16) {
      i = paramInt;
      if (paramInt == 4)
        i = 2; 
      paramView.setImportantForAccessibility(i);
    } 
  }
  
  public static void setOnApplyWindowInsetsListener(View paramView, final OnApplyWindowInsetsListener listener) {
    if (Build.VERSION.SDK_INT >= 21) {
      if (listener == null) {
        paramView.setOnApplyWindowInsetsListener(null);
        return;
      } 
      paramView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            final OnApplyWindowInsetsListener val$listener;
            
            public WindowInsets onApplyWindowInsets(View param1View, WindowInsets param1WindowInsets) {
              WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.wrap(param1WindowInsets);
              return (WindowInsets)WindowInsetsCompat.unwrap(listener.onApplyWindowInsets(param1View, windowInsetsCompat));
            }
          });
    } 
  }
  
  public static void setScrollIndicators(View paramView, int paramInt1, int paramInt2) {
    if (Build.VERSION.SDK_INT >= 23)
      paramView.setScrollIndicators(paramInt1, paramInt2); 
  }
  
  public static void setTransitionName(View paramView, String paramString) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramView.setTransitionName(paramString);
    } else {
      if (sTransitionNameMap == null)
        sTransitionNameMap = new WeakHashMap<View, String>(); 
      sTransitionNameMap.put(paramView, paramString);
    } 
  }
  
  public static void stopNestedScroll(View paramView) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramView.stopNestedScroll();
    } else if (paramView instanceof NestedScrollingChild) {
      ((NestedScrollingChild)paramView).stopNestedScroll();
    } 
  }
  
  private static void tickleInvalidationFlag(View paramView) {
    float f = paramView.getTranslationY();
    paramView.setTranslationY(1.0F + f);
    paramView.setTranslationY(f);
  }
  
  static {
    new AtomicInteger(1);
  }
  
  public static interface OnUnhandledKeyEventListenerCompat {
    boolean onUnhandledKeyEvent(View param1View, KeyEvent param1KeyEvent);
  }
  
  static class UnhandledKeyEventManager {
    private static final ArrayList<WeakReference<View>> sViewsWithListeners = new ArrayList<WeakReference<View>>();
    
    private SparseArray<WeakReference<View>> mCapturedKeys = null;
    
    private WeakReference<KeyEvent> mLastDispatchedPreViewKeyEvent = null;
    
    private WeakHashMap<View, Boolean> mViewsContainingListeners = null;
    
    static UnhandledKeyEventManager at(View param1View) {
      UnhandledKeyEventManager unhandledKeyEventManager2 = (UnhandledKeyEventManager)param1View.getTag(R.id.tag_unhandled_key_event_manager);
      UnhandledKeyEventManager unhandledKeyEventManager1 = unhandledKeyEventManager2;
      if (unhandledKeyEventManager2 == null) {
        unhandledKeyEventManager1 = new UnhandledKeyEventManager();
        param1View.setTag(R.id.tag_unhandled_key_event_manager, unhandledKeyEventManager1);
      } 
      return unhandledKeyEventManager1;
    }
    
    private View dispatchInOrder(View param1View, KeyEvent param1KeyEvent) {
      WeakHashMap<View, Boolean> weakHashMap = this.mViewsContainingListeners;
      if (weakHashMap != null && weakHashMap.containsKey(param1View)) {
        if (param1View instanceof ViewGroup) {
          ViewGroup viewGroup = (ViewGroup)param1View;
          for (int i = viewGroup.getChildCount() - 1; i >= 0; i--) {
            View view = dispatchInOrder(viewGroup.getChildAt(i), param1KeyEvent);
            if (view != null)
              return view; 
          } 
        } 
        if (onUnhandledKeyEvent(param1View, param1KeyEvent))
          return param1View; 
      } 
      return null;
    }
    
    private SparseArray<WeakReference<View>> getCapturedKeys() {
      if (this.mCapturedKeys == null)
        this.mCapturedKeys = new SparseArray(); 
      return this.mCapturedKeys;
    }
    
    private boolean onUnhandledKeyEvent(View param1View, KeyEvent param1KeyEvent) {
      ArrayList<ViewCompat.OnUnhandledKeyEventListenerCompat> arrayList = (ArrayList)param1View.getTag(R.id.tag_unhandled_key_listeners);
      if (arrayList != null)
        for (int i = arrayList.size() - 1; i >= 0; i--) {
          if (((ViewCompat.OnUnhandledKeyEventListenerCompat)arrayList.get(i)).onUnhandledKeyEvent(param1View, param1KeyEvent))
            return true; 
        }  
      return false;
    }
    
    private void recalcViewsWithUnhandled() {
      null = this.mViewsContainingListeners;
      if (null != null)
        null.clear(); 
      if (sViewsWithListeners.isEmpty())
        return; 
      synchronized (sViewsWithListeners) {
        if (this.mViewsContainingListeners == null) {
          null = new WeakHashMap<View, Boolean>();
          this();
          this.mViewsContainingListeners = null;
        } 
        for (int i = sViewsWithListeners.size() - 1; i >= 0; i--) {
          View view = ((WeakReference<View>)sViewsWithListeners.get(i)).get();
          if (view == null) {
            sViewsWithListeners.remove(i);
          } else {
            this.mViewsContainingListeners.put(view, Boolean.TRUE);
            for (ViewParent viewParent = view.getParent(); viewParent instanceof View; viewParent = viewParent.getParent())
              this.mViewsContainingListeners.put((View)viewParent, Boolean.TRUE); 
          } 
        } 
        return;
      } 
    }
    
    boolean dispatch(View param1View, KeyEvent param1KeyEvent) {
      boolean bool;
      if (param1KeyEvent.getAction() == 0)
        recalcViewsWithUnhandled(); 
      param1View = dispatchInOrder(param1View, param1KeyEvent);
      if (param1KeyEvent.getAction() == 0) {
        int i = param1KeyEvent.getKeyCode();
        if (param1View != null && !KeyEvent.isModifierKey(i))
          getCapturedKeys().put(i, new WeakReference<View>(param1View)); 
      } 
      if (param1View != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean preDispatch(KeyEvent param1KeyEvent) {
      WeakReference<KeyEvent> weakReference1 = this.mLastDispatchedPreViewKeyEvent;
      if (weakReference1 != null && weakReference1.get() == param1KeyEvent)
        return false; 
      this.mLastDispatchedPreViewKeyEvent = new WeakReference<KeyEvent>(param1KeyEvent);
      WeakReference<KeyEvent> weakReference2 = null;
      SparseArray<WeakReference<View>> sparseArray = getCapturedKeys();
      weakReference1 = weakReference2;
      if (param1KeyEvent.getAction() == 1) {
        int i = sparseArray.indexOfKey(param1KeyEvent.getKeyCode());
        weakReference1 = weakReference2;
        if (i >= 0) {
          weakReference1 = (WeakReference<KeyEvent>)sparseArray.valueAt(i);
          sparseArray.removeAt(i);
        } 
      } 
      weakReference2 = weakReference1;
      if (weakReference1 == null)
        weakReference2 = (WeakReference<KeyEvent>)sparseArray.get(param1KeyEvent.getKeyCode()); 
      if (weakReference2 != null) {
        View view = (View)weakReference2.get();
        if (view != null && ViewCompat.isAttachedToWindow(view))
          onUnhandledKeyEvent(view, param1KeyEvent); 
        return true;
      } 
      return false;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/view/ViewCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */