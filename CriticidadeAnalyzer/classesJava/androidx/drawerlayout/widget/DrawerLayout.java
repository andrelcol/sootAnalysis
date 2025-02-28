package androidx.drawerlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup {
  static final boolean CAN_HIDE_DESCENDANTS;
  
  static final int[] LAYOUT_ATTRS;
  
  private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION;
  
  private static final int[] THEME_ATTRS = new int[] { 16843828 };
  
  private final ChildAccessibilityDelegate mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
  
  private Rect mChildHitRect;
  
  private Matrix mChildInvertedMatrix;
  
  private boolean mChildrenCanceledTouch;
  
  private boolean mDrawStatusBarBackground;
  
  private float mDrawerElevation;
  
  private int mDrawerState;
  
  private boolean mFirstLayout = true;
  
  private boolean mInLayout;
  
  private float mInitialMotionX;
  
  private float mInitialMotionY;
  
  private Object mLastInsets;
  
  private final ViewDragCallback mLeftCallback;
  
  private final ViewDragHelper mLeftDragger;
  
  private DrawerListener mListener;
  
  private List<DrawerListener> mListeners;
  
  private int mLockModeEnd = 3;
  
  private int mLockModeLeft = 3;
  
  private int mLockModeRight = 3;
  
  private int mLockModeStart = 3;
  
  private int mMinDrawerMargin;
  
  private final ArrayList<View> mNonDrawerViews;
  
  private final ViewDragCallback mRightCallback;
  
  private final ViewDragHelper mRightDragger;
  
  private int mScrimColor = -1728053248;
  
  private float mScrimOpacity;
  
  private Paint mScrimPaint = new Paint();
  
  private Drawable mShadowEnd = null;
  
  private Drawable mShadowLeft = null;
  
  private Drawable mShadowLeftResolved;
  
  private Drawable mShadowRight = null;
  
  private Drawable mShadowRightResolved;
  
  private Drawable mShadowStart = null;
  
  private Drawable mStatusBarBackground;
  
  private CharSequence mTitleLeft;
  
  private CharSequence mTitleRight;
  
  static {
    LAYOUT_ATTRS = new int[] { 16842931 };
    if (Build.VERSION.SDK_INT >= 19) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    CAN_HIDE_DESCENDANTS = bool1;
    if (Build.VERSION.SDK_INT >= 21) {
      bool1 = bool2;
    } else {
      bool1 = false;
    } 
    SET_DRAWER_SHADOW_FROM_ELEVATION = bool1;
  }
  
  public DrawerLayout(Context paramContext) {
    this(paramContext, null);
  }
  
  public DrawerLayout(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public DrawerLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    setDescendantFocusability(262144);
    float f2 = (getResources().getDisplayMetrics()).density;
    this.mMinDrawerMargin = (int)(64.0F * f2 + 0.5F);
    float f1 = 400.0F * f2;
    this.mLeftCallback = new ViewDragCallback(3);
    this.mRightCallback = new ViewDragCallback(5);
    this.mLeftDragger = ViewDragHelper.create(this, 1.0F, this.mLeftCallback);
    this.mLeftDragger.setEdgeTrackingEnabled(1);
    this.mLeftDragger.setMinVelocity(f1);
    this.mLeftCallback.setDragger(this.mLeftDragger);
    this.mRightDragger = ViewDragHelper.create(this, 1.0F, this.mRightCallback);
    this.mRightDragger.setEdgeTrackingEnabled(2);
    this.mRightDragger.setMinVelocity(f1);
    this.mRightCallback.setDragger(this.mRightDragger);
    setFocusableInTouchMode(true);
    ViewCompat.setImportantForAccessibility((View)this, 1);
    ViewCompat.setAccessibilityDelegate((View)this, new AccessibilityDelegate());
    setMotionEventSplittingEnabled(false);
    if (ViewCompat.getFitsSystemWindows((View)this))
      if (Build.VERSION.SDK_INT >= 21) {
        setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener(this) {
              public WindowInsets onApplyWindowInsets(View param1View, WindowInsets param1WindowInsets) {
                boolean bool;
                DrawerLayout drawerLayout = (DrawerLayout)param1View;
                if (param1WindowInsets.getSystemWindowInsetTop() > 0) {
                  bool = true;
                } else {
                  bool = false;
                } 
                drawerLayout.setChildInsets(param1WindowInsets, bool);
                return param1WindowInsets.consumeSystemWindowInsets();
              }
            });
        setSystemUiVisibility(1280);
        TypedArray typedArray = paramContext.obtainStyledAttributes(THEME_ATTRS);
        try {
          this.mStatusBarBackground = typedArray.getDrawable(0);
        } finally {
          typedArray.recycle();
        } 
      } else {
        this.mStatusBarBackground = null;
      }  
    this.mDrawerElevation = f2 * 10.0F;
    this.mNonDrawerViews = new ArrayList<View>();
  }
  
  private boolean dispatchTransformedGenericPointerEvent(MotionEvent paramMotionEvent, View paramView) {
    boolean bool;
    if (!paramView.getMatrix().isIdentity()) {
      paramMotionEvent = getTransformedMotionEvent(paramMotionEvent, paramView);
      bool = paramView.dispatchGenericMotionEvent(paramMotionEvent);
      paramMotionEvent.recycle();
    } else {
      float f2 = (getScrollX() - paramView.getLeft());
      float f1 = (getScrollY() - paramView.getTop());
      paramMotionEvent.offsetLocation(f2, f1);
      bool = paramView.dispatchGenericMotionEvent(paramMotionEvent);
      paramMotionEvent.offsetLocation(-f2, -f1);
    } 
    return bool;
  }
  
  private MotionEvent getTransformedMotionEvent(MotionEvent paramMotionEvent, View paramView) {
    float f1 = (getScrollX() - paramView.getLeft());
    float f2 = (getScrollY() - paramView.getTop());
    paramMotionEvent = MotionEvent.obtain(paramMotionEvent);
    paramMotionEvent.offsetLocation(f1, f2);
    Matrix matrix = paramView.getMatrix();
    if (!matrix.isIdentity()) {
      if (this.mChildInvertedMatrix == null)
        this.mChildInvertedMatrix = new Matrix(); 
      matrix.invert(this.mChildInvertedMatrix);
      paramMotionEvent.transform(this.mChildInvertedMatrix);
    } 
    return paramMotionEvent;
  }
  
  static String gravityToString(int paramInt) {
    return ((paramInt & 0x3) == 3) ? "LEFT" : (((paramInt & 0x5) == 5) ? "RIGHT" : Integer.toHexString(paramInt));
  }
  
  private static boolean hasOpaqueBackground(View paramView) {
    Drawable drawable = paramView.getBackground();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (drawable != null) {
      bool1 = bool2;
      if (drawable.getOpacity() == -1)
        bool1 = true; 
    } 
    return bool1;
  }
  
  private boolean hasPeekingDrawer() {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      if (((LayoutParams)getChildAt(b).getLayoutParams()).isPeeking)
        return true; 
    } 
    return false;
  }
  
  private boolean hasVisibleDrawer() {
    boolean bool;
    if (findVisibleDrawer() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  static boolean includeChildForAccessibility(View paramView) {
    boolean bool;
    if (ViewCompat.getImportantForAccessibility(paramView) != 4 && ViewCompat.getImportantForAccessibility(paramView) != 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isInBoundsOfChild(float paramFloat1, float paramFloat2, View paramView) {
    if (this.mChildHitRect == null)
      this.mChildHitRect = new Rect(); 
    paramView.getHitRect(this.mChildHitRect);
    return this.mChildHitRect.contains((int)paramFloat1, (int)paramFloat2);
  }
  
  private boolean mirror(Drawable paramDrawable, int paramInt) {
    if (paramDrawable == null || !DrawableCompat.isAutoMirrored(paramDrawable))
      return false; 
    DrawableCompat.setLayoutDirection(paramDrawable, paramInt);
    return true;
  }
  
  private Drawable resolveLeftShadow() {
    int i = ViewCompat.getLayoutDirection((View)this);
    if (i == 0) {
      Drawable drawable = this.mShadowStart;
      if (drawable != null) {
        mirror(drawable, i);
        return this.mShadowStart;
      } 
    } else {
      Drawable drawable = this.mShadowEnd;
      if (drawable != null) {
        mirror(drawable, i);
        return this.mShadowEnd;
      } 
    } 
    return this.mShadowLeft;
  }
  
  private Drawable resolveRightShadow() {
    int i = ViewCompat.getLayoutDirection((View)this);
    if (i == 0) {
      Drawable drawable = this.mShadowEnd;
      if (drawable != null) {
        mirror(drawable, i);
        return this.mShadowEnd;
      } 
    } else {
      Drawable drawable = this.mShadowStart;
      if (drawable != null) {
        mirror(drawable, i);
        return this.mShadowStart;
      } 
    } 
    return this.mShadowRight;
  }
  
  private void resolveShadowDrawables() {
    if (SET_DRAWER_SHADOW_FROM_ELEVATION)
      return; 
    this.mShadowLeftResolved = resolveLeftShadow();
    this.mShadowRightResolved = resolveRightShadow();
  }
  
  private void updateChildrenImportantForAccessibility(View paramView, boolean paramBoolean) {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = getChildAt(b);
      if ((!paramBoolean && !isDrawerView(view)) || (paramBoolean && view == paramView)) {
        ViewCompat.setImportantForAccessibility(view, 1);
      } else {
        ViewCompat.setImportantForAccessibility(view, 4);
      } 
    } 
  }
  
  public void addDrawerListener(DrawerListener paramDrawerListener) {
    if (paramDrawerListener == null)
      return; 
    if (this.mListeners == null)
      this.mListeners = new ArrayList<DrawerListener>(); 
    this.mListeners.add(paramDrawerListener);
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2) {
    if (getDescendantFocusability() == 393216)
      return; 
    int j = getChildCount();
    boolean bool = false;
    byte b = 0;
    int i = 0;
    while (b < j) {
      View view = getChildAt(b);
      if (isDrawerView(view)) {
        if (isDrawerOpen(view)) {
          view.addFocusables(paramArrayList, paramInt1, paramInt2);
          i = 1;
        } 
      } else {
        this.mNonDrawerViews.add(view);
      } 
      b++;
    } 
    if (!i) {
      i = this.mNonDrawerViews.size();
      for (b = bool; b < i; b++) {
        View view = this.mNonDrawerViews.get(b);
        if (view.getVisibility() == 0)
          view.addFocusables(paramArrayList, paramInt1, paramInt2); 
      } 
    } 
    this.mNonDrawerViews.clear();
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    super.addView(paramView, paramInt, paramLayoutParams);
    if (findOpenDrawer() != null || isDrawerView(paramView)) {
      ViewCompat.setImportantForAccessibility(paramView, 4);
    } else {
      ViewCompat.setImportantForAccessibility(paramView, 1);
    } 
    if (!CAN_HIDE_DESCENDANTS)
      ViewCompat.setAccessibilityDelegate(paramView, this.mChildAccessibilityDelegate); 
  }
  
  void cancelChildViewTouch() {
    if (!this.mChildrenCanceledTouch) {
      long l = SystemClock.uptimeMillis();
      MotionEvent motionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
      int i = getChildCount();
      for (byte b = 0; b < i; b++)
        getChildAt(b).dispatchTouchEvent(motionEvent); 
      motionEvent.recycle();
      this.mChildrenCanceledTouch = true;
    } 
  }
  
  boolean checkDrawerViewAbsoluteGravity(View paramView, int paramInt) {
    boolean bool;
    if ((getDrawerViewAbsoluteGravity(paramView) & paramInt) == paramInt) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    boolean bool;
    if (paramLayoutParams instanceof LayoutParams && super.checkLayoutParams(paramLayoutParams)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void closeDrawer(int paramInt) {
    closeDrawer(paramInt, true);
  }
  
  public void closeDrawer(int paramInt, boolean paramBoolean) {
    View view = findDrawerWithGravity(paramInt);
    if (view != null) {
      closeDrawer(view, paramBoolean);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("No drawer view found with gravity ");
    stringBuilder.append(gravityToString(paramInt));
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void closeDrawer(View paramView) {
    closeDrawer(paramView, true);
  }
  
  public void closeDrawer(View paramView, boolean paramBoolean) {
    if (isDrawerView(paramView)) {
      LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
      if (this.mFirstLayout) {
        layoutParams.onScreen = 0.0F;
        layoutParams.openState = 0;
      } else if (paramBoolean) {
        layoutParams.openState |= 0x4;
        if (checkDrawerViewAbsoluteGravity(paramView, 3)) {
          this.mLeftDragger.smoothSlideViewTo(paramView, -paramView.getWidth(), paramView.getTop());
        } else {
          this.mRightDragger.smoothSlideViewTo(paramView, getWidth(), paramView.getTop());
        } 
      } else {
        moveDrawerToOffset(paramView, 0.0F);
        updateDrawerState(layoutParams.gravity, 0, paramView);
        paramView.setVisibility(4);
      } 
      invalidate();
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("View ");
    stringBuilder.append(paramView);
    stringBuilder.append(" is not a sliding drawer");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void closeDrawers() {
    closeDrawers(false);
  }
  
  void closeDrawers(boolean paramBoolean) {
    int i;
    int k = getChildCount();
    byte b = 0;
    int j = 0;
    while (b < k) {
      int m;
      View view = getChildAt(b);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      int n = j;
      if (isDrawerView(view))
        if (paramBoolean && !layoutParams.isPeeking) {
          n = j;
        } else {
          int i1;
          n = view.getWidth();
          if (checkDrawerViewAbsoluteGravity(view, 3)) {
            i1 = this.mLeftDragger.smoothSlideViewTo(view, -n, view.getTop());
          } else {
            i1 = this.mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
          } 
          m = j | i1;
          layoutParams.isPeeking = false;
        }  
      b++;
      i = m;
    } 
    this.mLeftCallback.removeCallbacks();
    this.mRightCallback.removeCallbacks();
    if (i != 0)
      invalidate(); 
  }
  
  public void computeScroll() {
    int i = getChildCount();
    float f = 0.0F;
    for (byte b = 0; b < i; b++)
      f = Math.max(f, ((LayoutParams)getChildAt(b).getLayoutParams()).onScreen); 
    this.mScrimOpacity = f;
    boolean bool2 = this.mLeftDragger.continueSettling(true);
    boolean bool1 = this.mRightDragger.continueSettling(true);
    if (bool2 || bool1)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent) {
    if ((paramMotionEvent.getSource() & 0x2) == 0 || paramMotionEvent.getAction() == 10 || this.mScrimOpacity <= 0.0F)
      return super.dispatchGenericMotionEvent(paramMotionEvent); 
    int i = getChildCount();
    if (i != 0) {
      float f2 = paramMotionEvent.getX();
      float f1 = paramMotionEvent.getY();
      while (--i >= 0) {
        View view = getChildAt(i);
        if (isInBoundsOfChild(f2, f1, view) && !isContentView(view) && dispatchTransformedGenericPointerEvent(paramMotionEvent, view))
          return true; 
        i--;
      } 
    } 
    return false;
  }
  
  void dispatchOnDrawerClosed(View paramView) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if ((layoutParams.openState & 0x1) == 1) {
      layoutParams.openState = 0;
      List<DrawerListener> list = this.mListeners;
      if (list != null)
        for (int i = list.size() - 1; i >= 0; i--)
          ((DrawerListener)this.mListeners.get(i)).onDrawerClosed(paramView);  
      updateChildrenImportantForAccessibility(paramView, false);
      if (hasWindowFocus()) {
        paramView = getRootView();
        if (paramView != null)
          paramView.sendAccessibilityEvent(32); 
      } 
    } 
  }
  
  void dispatchOnDrawerOpened(View paramView) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if ((layoutParams.openState & 0x1) == 0) {
      layoutParams.openState = 1;
      List<DrawerListener> list = this.mListeners;
      if (list != null)
        for (int i = list.size() - 1; i >= 0; i--)
          ((DrawerListener)this.mListeners.get(i)).onDrawerOpened(paramView);  
      updateChildrenImportantForAccessibility(paramView, true);
      if (hasWindowFocus())
        sendAccessibilityEvent(32); 
    } 
  }
  
  void dispatchOnDrawerSlide(View paramView, float paramFloat) {
    List<DrawerListener> list = this.mListeners;
    if (list != null)
      for (int i = list.size() - 1; i >= 0; i--)
        ((DrawerListener)this.mListeners.get(i)).onDrawerSlide(paramView, paramFloat);  
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong) {
    int m = getHeight();
    boolean bool2 = isContentView(paramView);
    int i = getWidth();
    int k = paramCanvas.save();
    int j = 0;
    if (bool2) {
      int n = getChildCount();
      byte b = 0;
      j = 0;
      while (b < n) {
        View view = getChildAt(b);
        int i1 = j;
        int i2 = i;
        if (view != paramView) {
          i1 = j;
          i2 = i;
          if (view.getVisibility() == 0) {
            i1 = j;
            i2 = i;
            if (hasOpaqueBackground(view)) {
              i1 = j;
              i2 = i;
              if (isDrawerView(view))
                if (view.getHeight() < m) {
                  i1 = j;
                  i2 = i;
                } else if (checkDrawerViewAbsoluteGravity(view, 3)) {
                  int i3 = view.getRight();
                  i1 = j;
                  i2 = i;
                  if (i3 > j) {
                    i1 = i3;
                    i2 = i;
                  } 
                } else {
                  int i3 = view.getLeft();
                  i1 = j;
                  i2 = i;
                  if (i3 < i) {
                    i2 = i3;
                    i1 = j;
                  } 
                }  
            } 
          } 
        } 
        b++;
        j = i1;
        i = i2;
      } 
      paramCanvas.clipRect(j, 0, i, getHeight());
    } 
    boolean bool1 = super.drawChild(paramCanvas, paramView, paramLong);
    paramCanvas.restoreToCount(k);
    float f = this.mScrimOpacity;
    if (f > 0.0F && bool2) {
      int n = this.mScrimColor;
      int i1 = (int)(((0xFF000000 & n) >>> 24) * f);
      this.mScrimPaint.setColor(n & 0xFFFFFF | i1 << 24);
      paramCanvas.drawRect(j, 0.0F, i, getHeight(), this.mScrimPaint);
    } else if (this.mShadowLeftResolved != null && checkDrawerViewAbsoluteGravity(paramView, 3)) {
      i = this.mShadowLeftResolved.getIntrinsicWidth();
      j = paramView.getRight();
      int n = this.mLeftDragger.getEdgeSize();
      f = Math.max(0.0F, Math.min(j / n, 1.0F));
      this.mShadowLeftResolved.setBounds(j, paramView.getTop(), i + j, paramView.getBottom());
      this.mShadowLeftResolved.setAlpha((int)(f * 255.0F));
      this.mShadowLeftResolved.draw(paramCanvas);
    } else if (this.mShadowRightResolved != null && checkDrawerViewAbsoluteGravity(paramView, 5)) {
      int n = this.mShadowRightResolved.getIntrinsicWidth();
      int i1 = paramView.getLeft();
      j = getWidth();
      i = this.mRightDragger.getEdgeSize();
      f = Math.max(0.0F, Math.min((j - i1) / i, 1.0F));
      this.mShadowRightResolved.setBounds(i1 - n, paramView.getTop(), i1, paramView.getBottom());
      this.mShadowRightResolved.setAlpha((int)(f * 255.0F));
      this.mShadowRightResolved.draw(paramCanvas);
    } 
    return bool1;
  }
  
  View findDrawerWithGravity(int paramInt) {
    int j = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection((View)this));
    int i = getChildCount();
    for (paramInt = 0; paramInt < i; paramInt++) {
      View view = getChildAt(paramInt);
      if ((getDrawerViewAbsoluteGravity(view) & 0x7) == (j & 0x7))
        return view; 
    } 
    return null;
  }
  
  View findOpenDrawer() {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = getChildAt(b);
      if ((((LayoutParams)view.getLayoutParams()).openState & 0x1) == 1)
        return view; 
    } 
    return null;
  }
  
  View findVisibleDrawer() {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = getChildAt(b);
      if (isDrawerView(view) && isDrawerVisible(view))
        return view; 
    } 
    return null;
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    return (ViewGroup.LayoutParams)new LayoutParams(-1, -1);
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return (ViewGroup.LayoutParams)new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    LayoutParams layoutParams;
    if (paramLayoutParams instanceof LayoutParams) {
      layoutParams = new LayoutParams((LayoutParams)paramLayoutParams);
    } else if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
      layoutParams = new LayoutParams(layoutParams);
    } else {
      layoutParams = new LayoutParams((ViewGroup.LayoutParams)layoutParams);
    } 
    return (ViewGroup.LayoutParams)layoutParams;
  }
  
  public float getDrawerElevation() {
    return SET_DRAWER_SHADOW_FROM_ELEVATION ? this.mDrawerElevation : 0.0F;
  }
  
  public int getDrawerLockMode(int paramInt) {
    int i = ViewCompat.getLayoutDirection((View)this);
    if (paramInt != 3) {
      if (paramInt != 5) {
        if (paramInt != 8388611) {
          if (paramInt == 8388613) {
            paramInt = this.mLockModeEnd;
            if (paramInt != 3)
              return paramInt; 
            if (i == 0) {
              paramInt = this.mLockModeRight;
            } else {
              paramInt = this.mLockModeLeft;
            } 
            if (paramInt != 3)
              return paramInt; 
          } 
        } else {
          paramInt = this.mLockModeStart;
          if (paramInt != 3)
            return paramInt; 
          if (i == 0) {
            paramInt = this.mLockModeLeft;
          } else {
            paramInt = this.mLockModeRight;
          } 
          if (paramInt != 3)
            return paramInt; 
        } 
      } else {
        paramInt = this.mLockModeRight;
        if (paramInt != 3)
          return paramInt; 
        if (i == 0) {
          paramInt = this.mLockModeEnd;
        } else {
          paramInt = this.mLockModeStart;
        } 
        if (paramInt != 3)
          return paramInt; 
      } 
    } else {
      paramInt = this.mLockModeLeft;
      if (paramInt != 3)
        return paramInt; 
      if (i == 0) {
        paramInt = this.mLockModeStart;
      } else {
        paramInt = this.mLockModeEnd;
      } 
      if (paramInt != 3)
        return paramInt; 
    } 
    return 0;
  }
  
  public int getDrawerLockMode(View paramView) {
    if (isDrawerView(paramView))
      return getDrawerLockMode(((LayoutParams)paramView.getLayoutParams()).gravity); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("View ");
    stringBuilder.append(paramView);
    stringBuilder.append(" is not a drawer");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public CharSequence getDrawerTitle(int paramInt) {
    paramInt = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection((View)this));
    return (paramInt == 3) ? this.mTitleLeft : ((paramInt == 5) ? this.mTitleRight : null);
  }
  
  int getDrawerViewAbsoluteGravity(View paramView) {
    return GravityCompat.getAbsoluteGravity(((LayoutParams)paramView.getLayoutParams()).gravity, ViewCompat.getLayoutDirection((View)this));
  }
  
  float getDrawerViewOffset(View paramView) {
    return ((LayoutParams)paramView.getLayoutParams()).onScreen;
  }
  
  public Drawable getStatusBarBackgroundDrawable() {
    return this.mStatusBarBackground;
  }
  
  boolean isContentView(View paramView) {
    boolean bool;
    if (((LayoutParams)paramView.getLayoutParams()).gravity == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDrawerOpen(int paramInt) {
    View view = findDrawerWithGravity(paramInt);
    return (view != null) ? isDrawerOpen(view) : false;
  }
  
  public boolean isDrawerOpen(View paramView) {
    if (isDrawerView(paramView)) {
      int i = ((LayoutParams)paramView.getLayoutParams()).openState;
      boolean bool = true;
      if ((i & 0x1) != 1)
        bool = false; 
      return bool;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("View ");
    stringBuilder.append(paramView);
    stringBuilder.append(" is not a drawer");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  boolean isDrawerView(View paramView) {
    int i = GravityCompat.getAbsoluteGravity(((LayoutParams)paramView.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(paramView));
    return ((i & 0x3) != 0) ? true : (((i & 0x5) != 0));
  }
  
  public boolean isDrawerVisible(View paramView) {
    if (isDrawerView(paramView)) {
      boolean bool;
      if (((LayoutParams)paramView.getLayoutParams()).onScreen > 0.0F) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("View ");
    stringBuilder.append(paramView);
    stringBuilder.append(" is not a drawer");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  void moveDrawerToOffset(View paramView, float paramFloat) {
    float f1 = getDrawerViewOffset(paramView);
    float f2 = paramView.getWidth();
    int i = (int)(f1 * f2);
    i = (int)(f2 * paramFloat) - i;
    if (!checkDrawerViewAbsoluteGravity(paramView, 3))
      i = -i; 
    paramView.offsetLeftAndRight(i);
    setDrawerViewOffset(paramView, paramFloat);
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    this.mFirstLayout = true;
  }
  
  public void onDraw(Canvas paramCanvas) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial onDraw : (Landroid/graphics/Canvas;)V
    //   5: aload_0
    //   6: getfield mDrawStatusBarBackground : Z
    //   9: ifeq -> 75
    //   12: aload_0
    //   13: getfield mStatusBarBackground : Landroid/graphics/drawable/Drawable;
    //   16: ifnull -> 75
    //   19: getstatic android/os/Build$VERSION.SDK_INT : I
    //   22: bipush #21
    //   24: if_icmplt -> 47
    //   27: aload_0
    //   28: getfield mLastInsets : Ljava/lang/Object;
    //   31: astore_3
    //   32: aload_3
    //   33: ifnull -> 47
    //   36: aload_3
    //   37: checkcast android/view/WindowInsets
    //   40: invokevirtual getSystemWindowInsetTop : ()I
    //   43: istore_2
    //   44: goto -> 49
    //   47: iconst_0
    //   48: istore_2
    //   49: iload_2
    //   50: ifle -> 75
    //   53: aload_0
    //   54: getfield mStatusBarBackground : Landroid/graphics/drawable/Drawable;
    //   57: iconst_0
    //   58: iconst_0
    //   59: aload_0
    //   60: invokevirtual getWidth : ()I
    //   63: iload_2
    //   64: invokevirtual setBounds : (IIII)V
    //   67: aload_0
    //   68: getfield mStatusBarBackground : Landroid/graphics/drawable/Drawable;
    //   71: aload_1
    //   72: invokevirtual draw : (Landroid/graphics/Canvas;)V
    //   75: return
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getActionMasked : ()I
    //   4: istore #4
    //   6: aload_0
    //   7: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   10: aload_1
    //   11: invokevirtual shouldInterceptTouchEvent : (Landroid/view/MotionEvent;)Z
    //   14: istore #8
    //   16: aload_0
    //   17: getfield mRightDragger : Landroidx/customview/widget/ViewDragHelper;
    //   20: aload_1
    //   21: invokevirtual shouldInterceptTouchEvent : (Landroid/view/MotionEvent;)Z
    //   24: istore #7
    //   26: iconst_1
    //   27: istore #6
    //   29: iload #4
    //   31: ifeq -> 99
    //   34: iload #4
    //   36: iconst_1
    //   37: if_icmpeq -> 83
    //   40: iload #4
    //   42: iconst_2
    //   43: if_icmpeq -> 55
    //   46: iload #4
    //   48: iconst_3
    //   49: if_icmpeq -> 83
    //   52: goto -> 93
    //   55: aload_0
    //   56: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   59: iconst_3
    //   60: invokevirtual checkTouchSlop : (I)Z
    //   63: ifeq -> 93
    //   66: aload_0
    //   67: getfield mLeftCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   70: invokevirtual removeCallbacks : ()V
    //   73: aload_0
    //   74: getfield mRightCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   77: invokevirtual removeCallbacks : ()V
    //   80: goto -> 93
    //   83: aload_0
    //   84: iconst_1
    //   85: invokevirtual closeDrawers : (Z)V
    //   88: aload_0
    //   89: iconst_0
    //   90: putfield mChildrenCanceledTouch : Z
    //   93: iconst_0
    //   94: istore #4
    //   96: goto -> 166
    //   99: aload_1
    //   100: invokevirtual getX : ()F
    //   103: fstore_2
    //   104: aload_1
    //   105: invokevirtual getY : ()F
    //   108: fstore_3
    //   109: aload_0
    //   110: fload_2
    //   111: putfield mInitialMotionX : F
    //   114: aload_0
    //   115: fload_3
    //   116: putfield mInitialMotionY : F
    //   119: aload_0
    //   120: getfield mScrimOpacity : F
    //   123: fconst_0
    //   124: fcmpl
    //   125: ifle -> 158
    //   128: aload_0
    //   129: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   132: fload_2
    //   133: f2i
    //   134: fload_3
    //   135: f2i
    //   136: invokevirtual findTopChildUnder : (II)Landroid/view/View;
    //   139: astore_1
    //   140: aload_1
    //   141: ifnull -> 158
    //   144: aload_0
    //   145: aload_1
    //   146: invokevirtual isContentView : (Landroid/view/View;)Z
    //   149: ifeq -> 158
    //   152: iconst_1
    //   153: istore #4
    //   155: goto -> 161
    //   158: iconst_0
    //   159: istore #4
    //   161: aload_0
    //   162: iconst_0
    //   163: putfield mChildrenCanceledTouch : Z
    //   166: iload #6
    //   168: istore #5
    //   170: iload #8
    //   172: iload #7
    //   174: ior
    //   175: ifne -> 215
    //   178: iload #6
    //   180: istore #5
    //   182: iload #4
    //   184: ifne -> 215
    //   187: iload #6
    //   189: istore #5
    //   191: aload_0
    //   192: invokespecial hasPeekingDrawer : ()Z
    //   195: ifne -> 215
    //   198: aload_0
    //   199: getfield mChildrenCanceledTouch : Z
    //   202: ifeq -> 212
    //   205: iload #6
    //   207: istore #5
    //   209: goto -> 215
    //   212: iconst_0
    //   213: istore #5
    //   215: iload #5
    //   217: ireturn
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4 && hasVisibleDrawer()) {
      paramKeyEvent.startTracking();
      return true;
    } 
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    View view;
    if (paramInt == 4) {
      boolean bool;
      view = findVisibleDrawer();
      if (view != null && getDrawerLockMode(view) == 0)
        closeDrawers(); 
      if (view != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
    return super.onKeyUp(paramInt, (KeyEvent)view);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mInLayout = true;
    int j = paramInt3 - paramInt1;
    int i = getChildCount();
    for (paramInt3 = 0; paramInt3 < i; paramInt3++) {
      View view = getChildAt(paramInt3);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (isContentView(view)) {
          paramInt1 = layoutParams.leftMargin;
          view.layout(paramInt1, layoutParams.topMargin, view.getMeasuredWidth() + paramInt1, layoutParams.topMargin + view.getMeasuredHeight());
        } else {
          float f;
          int k;
          boolean bool;
          int m = view.getMeasuredWidth();
          int n = view.getMeasuredHeight();
          if (checkDrawerViewAbsoluteGravity(view, 3)) {
            paramInt1 = -m;
            f = m;
            k = paramInt1 + (int)(layoutParams.onScreen * f);
            f = (m + k) / f;
          } else {
            f = m;
            k = j - (int)(layoutParams.onScreen * f);
            f = (j - k) / f;
          } 
          if (f != layoutParams.onScreen) {
            bool = true;
          } else {
            bool = false;
          } 
          paramInt1 = layoutParams.gravity & 0x70;
          if (paramInt1 != 16) {
            if (paramInt1 != 80) {
              paramInt1 = layoutParams.topMargin;
              view.layout(k, paramInt1, m + k, n + paramInt1);
            } else {
              paramInt1 = paramInt4 - paramInt2;
              view.layout(k, paramInt1 - layoutParams.bottomMargin - view.getMeasuredHeight(), m + k, paramInt1 - layoutParams.bottomMargin);
            } 
          } else {
            int i2 = paramInt4 - paramInt2;
            int i1 = (i2 - n) / 2;
            paramInt1 = layoutParams.topMargin;
            if (i1 >= paramInt1) {
              int i3 = layoutParams.bottomMargin;
              paramInt1 = i1;
              if (i1 + n > i2 - i3)
                paramInt1 = i2 - i3 - n; 
            } 
            view.layout(k, paramInt1, m + k, n + paramInt1);
          } 
          if (bool)
            setDrawerViewOffset(view, f); 
          if (layoutParams.onScreen > 0.0F) {
            paramInt1 = 0;
          } else {
            paramInt1 = 4;
          } 
          if (view.getVisibility() != paramInt1)
            view.setVisibility(paramInt1); 
        } 
      } 
    } 
    this.mInLayout = false;
    this.mFirstLayout = false;
  }
  
  @SuppressLint({"WrongConstant"})
  protected void onMeasure(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iload_1
    //   1: invokestatic getMode : (I)I
    //   4: istore #10
    //   6: iload_2
    //   7: invokestatic getMode : (I)I
    //   10: istore #9
    //   12: iload_1
    //   13: invokestatic getSize : (I)I
    //   16: istore #5
    //   18: iload_2
    //   19: invokestatic getSize : (I)I
    //   22: istore #6
    //   24: iload #10
    //   26: ldc_w 1073741824
    //   29: if_icmpne -> 48
    //   32: iload #5
    //   34: istore #7
    //   36: iload #6
    //   38: istore #8
    //   40: iload #9
    //   42: ldc_w 1073741824
    //   45: if_icmpeq -> 117
    //   48: aload_0
    //   49: invokevirtual isInEditMode : ()Z
    //   52: ifeq -> 816
    //   55: iload #10
    //   57: ldc_w -2147483648
    //   60: if_icmpne -> 66
    //   63: goto -> 76
    //   66: iload #10
    //   68: ifne -> 76
    //   71: sipush #300
    //   74: istore #5
    //   76: iload #9
    //   78: ldc_w -2147483648
    //   81: if_icmpne -> 95
    //   84: iload #5
    //   86: istore #7
    //   88: iload #6
    //   90: istore #8
    //   92: goto -> 117
    //   95: iload #5
    //   97: istore #7
    //   99: iload #6
    //   101: istore #8
    //   103: iload #9
    //   105: ifne -> 117
    //   108: sipush #300
    //   111: istore #8
    //   113: iload #5
    //   115: istore #7
    //   117: aload_0
    //   118: iload #7
    //   120: iload #8
    //   122: invokevirtual setMeasuredDimension : (II)V
    //   125: aload_0
    //   126: getfield mLastInsets : Ljava/lang/Object;
    //   129: ifnull -> 145
    //   132: aload_0
    //   133: invokestatic getFitsSystemWindows : (Landroid/view/View;)Z
    //   136: ifeq -> 145
    //   139: iconst_1
    //   140: istore #9
    //   142: goto -> 148
    //   145: iconst_0
    //   146: istore #9
    //   148: aload_0
    //   149: invokestatic getLayoutDirection : (Landroid/view/View;)I
    //   152: istore #12
    //   154: aload_0
    //   155: invokevirtual getChildCount : ()I
    //   158: istore #13
    //   160: iconst_0
    //   161: istore #10
    //   163: iconst_0
    //   164: istore #6
    //   166: iconst_0
    //   167: istore #5
    //   169: iload #10
    //   171: iload #13
    //   173: if_icmpge -> 815
    //   176: aload_0
    //   177: iload #10
    //   179: invokevirtual getChildAt : (I)Landroid/view/View;
    //   182: astore #17
    //   184: aload #17
    //   186: invokevirtual getVisibility : ()I
    //   189: bipush #8
    //   191: if_icmpne -> 197
    //   194: goto -> 501
    //   197: aload #17
    //   199: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   202: checkcast androidx/drawerlayout/widget/DrawerLayout$LayoutParams
    //   205: astore #18
    //   207: iload #9
    //   209: ifeq -> 447
    //   212: aload #18
    //   214: getfield gravity : I
    //   217: iload #12
    //   219: invokestatic getAbsoluteGravity : (II)I
    //   222: istore #11
    //   224: aload #17
    //   226: invokestatic getFitsSystemWindows : (Landroid/view/View;)Z
    //   229: ifeq -> 325
    //   232: getstatic android/os/Build$VERSION.SDK_INT : I
    //   235: bipush #21
    //   237: if_icmplt -> 447
    //   240: aload_0
    //   241: getfield mLastInsets : Ljava/lang/Object;
    //   244: checkcast android/view/WindowInsets
    //   247: astore #16
    //   249: iload #11
    //   251: iconst_3
    //   252: if_icmpne -> 281
    //   255: aload #16
    //   257: aload #16
    //   259: invokevirtual getSystemWindowInsetLeft : ()I
    //   262: aload #16
    //   264: invokevirtual getSystemWindowInsetTop : ()I
    //   267: iconst_0
    //   268: aload #16
    //   270: invokevirtual getSystemWindowInsetBottom : ()I
    //   273: invokevirtual replaceSystemWindowInsets : (IIII)Landroid/view/WindowInsets;
    //   276: astore #15
    //   278: goto -> 314
    //   281: aload #16
    //   283: astore #15
    //   285: iload #11
    //   287: iconst_5
    //   288: if_icmpne -> 314
    //   291: aload #16
    //   293: iconst_0
    //   294: aload #16
    //   296: invokevirtual getSystemWindowInsetTop : ()I
    //   299: aload #16
    //   301: invokevirtual getSystemWindowInsetRight : ()I
    //   304: aload #16
    //   306: invokevirtual getSystemWindowInsetBottom : ()I
    //   309: invokevirtual replaceSystemWindowInsets : (IIII)Landroid/view/WindowInsets;
    //   312: astore #15
    //   314: aload #17
    //   316: aload #15
    //   318: invokevirtual dispatchApplyWindowInsets : (Landroid/view/WindowInsets;)Landroid/view/WindowInsets;
    //   321: pop
    //   322: goto -> 447
    //   325: getstatic android/os/Build$VERSION.SDK_INT : I
    //   328: bipush #21
    //   330: if_icmplt -> 447
    //   333: aload_0
    //   334: getfield mLastInsets : Ljava/lang/Object;
    //   337: checkcast android/view/WindowInsets
    //   340: astore #16
    //   342: iload #11
    //   344: iconst_3
    //   345: if_icmpne -> 374
    //   348: aload #16
    //   350: aload #16
    //   352: invokevirtual getSystemWindowInsetLeft : ()I
    //   355: aload #16
    //   357: invokevirtual getSystemWindowInsetTop : ()I
    //   360: iconst_0
    //   361: aload #16
    //   363: invokevirtual getSystemWindowInsetBottom : ()I
    //   366: invokevirtual replaceSystemWindowInsets : (IIII)Landroid/view/WindowInsets;
    //   369: astore #15
    //   371: goto -> 407
    //   374: aload #16
    //   376: astore #15
    //   378: iload #11
    //   380: iconst_5
    //   381: if_icmpne -> 407
    //   384: aload #16
    //   386: iconst_0
    //   387: aload #16
    //   389: invokevirtual getSystemWindowInsetTop : ()I
    //   392: aload #16
    //   394: invokevirtual getSystemWindowInsetRight : ()I
    //   397: aload #16
    //   399: invokevirtual getSystemWindowInsetBottom : ()I
    //   402: invokevirtual replaceSystemWindowInsets : (IIII)Landroid/view/WindowInsets;
    //   405: astore #15
    //   407: aload #18
    //   409: aload #15
    //   411: invokevirtual getSystemWindowInsetLeft : ()I
    //   414: putfield leftMargin : I
    //   417: aload #18
    //   419: aload #15
    //   421: invokevirtual getSystemWindowInsetTop : ()I
    //   424: putfield topMargin : I
    //   427: aload #18
    //   429: aload #15
    //   431: invokevirtual getSystemWindowInsetRight : ()I
    //   434: putfield rightMargin : I
    //   437: aload #18
    //   439: aload #15
    //   441: invokevirtual getSystemWindowInsetBottom : ()I
    //   444: putfield bottomMargin : I
    //   447: aload_0
    //   448: aload #17
    //   450: invokevirtual isContentView : (Landroid/view/View;)Z
    //   453: ifeq -> 504
    //   456: aload #17
    //   458: iload #7
    //   460: aload #18
    //   462: getfield leftMargin : I
    //   465: isub
    //   466: aload #18
    //   468: getfield rightMargin : I
    //   471: isub
    //   472: ldc_w 1073741824
    //   475: invokestatic makeMeasureSpec : (II)I
    //   478: iload #8
    //   480: aload #18
    //   482: getfield topMargin : I
    //   485: isub
    //   486: aload #18
    //   488: getfield bottomMargin : I
    //   491: isub
    //   492: ldc_w 1073741824
    //   495: invokestatic makeMeasureSpec : (II)I
    //   498: invokevirtual measure : (II)V
    //   501: goto -> 735
    //   504: aload_0
    //   505: aload #17
    //   507: invokevirtual isDrawerView : (Landroid/view/View;)Z
    //   510: ifeq -> 741
    //   513: getstatic androidx/drawerlayout/widget/DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION : Z
    //   516: ifeq -> 544
    //   519: aload #17
    //   521: invokestatic getElevation : (Landroid/view/View;)F
    //   524: fstore #4
    //   526: aload_0
    //   527: getfield mDrawerElevation : F
    //   530: fstore_3
    //   531: fload #4
    //   533: fload_3
    //   534: fcmpl
    //   535: ifeq -> 544
    //   538: aload #17
    //   540: fload_3
    //   541: invokestatic setElevation : (Landroid/view/View;F)V
    //   544: aload_0
    //   545: aload #17
    //   547: invokevirtual getDrawerViewAbsoluteGravity : (Landroid/view/View;)I
    //   550: bipush #7
    //   552: iand
    //   553: istore #14
    //   555: iload #14
    //   557: iconst_3
    //   558: if_icmpne -> 567
    //   561: iconst_1
    //   562: istore #11
    //   564: goto -> 570
    //   567: iconst_0
    //   568: istore #11
    //   570: iload #11
    //   572: ifeq -> 580
    //   575: iload #6
    //   577: ifne -> 593
    //   580: iload #11
    //   582: ifne -> 671
    //   585: iload #5
    //   587: ifne -> 593
    //   590: goto -> 671
    //   593: new java/lang/StringBuilder
    //   596: dup
    //   597: invokespecial <init> : ()V
    //   600: astore #15
    //   602: aload #15
    //   604: ldc_w 'Child drawer has absolute gravity '
    //   607: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   610: pop
    //   611: aload #15
    //   613: iload #14
    //   615: invokestatic gravityToString : (I)Ljava/lang/String;
    //   618: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: pop
    //   622: aload #15
    //   624: ldc_w ' but this '
    //   627: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: pop
    //   631: aload #15
    //   633: ldc_w 'DrawerLayout'
    //   636: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   639: pop
    //   640: aload #15
    //   642: ldc_w ' already has a '
    //   645: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   648: pop
    //   649: aload #15
    //   651: ldc_w 'drawer view along that edge'
    //   654: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   657: pop
    //   658: new java/lang/IllegalStateException
    //   661: dup
    //   662: aload #15
    //   664: invokevirtual toString : ()Ljava/lang/String;
    //   667: invokespecial <init> : (Ljava/lang/String;)V
    //   670: athrow
    //   671: iload #11
    //   673: ifeq -> 682
    //   676: iconst_1
    //   677: istore #6
    //   679: goto -> 685
    //   682: iconst_1
    //   683: istore #5
    //   685: aload #17
    //   687: iload_1
    //   688: aload_0
    //   689: getfield mMinDrawerMargin : I
    //   692: aload #18
    //   694: getfield leftMargin : I
    //   697: iadd
    //   698: aload #18
    //   700: getfield rightMargin : I
    //   703: iadd
    //   704: aload #18
    //   706: getfield width : I
    //   709: invokestatic getChildMeasureSpec : (III)I
    //   712: iload_2
    //   713: aload #18
    //   715: getfield topMargin : I
    //   718: aload #18
    //   720: getfield bottomMargin : I
    //   723: iadd
    //   724: aload #18
    //   726: getfield height : I
    //   729: invokestatic getChildMeasureSpec : (III)I
    //   732: invokevirtual measure : (II)V
    //   735: iinc #10, 1
    //   738: goto -> 169
    //   741: new java/lang/StringBuilder
    //   744: dup
    //   745: invokespecial <init> : ()V
    //   748: astore #15
    //   750: aload #15
    //   752: ldc_w 'Child '
    //   755: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   758: pop
    //   759: aload #15
    //   761: aload #17
    //   763: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   766: pop
    //   767: aload #15
    //   769: ldc_w ' at index '
    //   772: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   775: pop
    //   776: aload #15
    //   778: iload #10
    //   780: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   783: pop
    //   784: aload #15
    //   786: ldc_w ' does not have a valid layout_gravity - must be Gravity.LEFT, '
    //   789: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   792: pop
    //   793: aload #15
    //   795: ldc_w 'Gravity.RIGHT or Gravity.NO_GRAVITY'
    //   798: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   801: pop
    //   802: new java/lang/IllegalStateException
    //   805: dup
    //   806: aload #15
    //   808: invokevirtual toString : ()Ljava/lang/String;
    //   811: invokespecial <init> : (Ljava/lang/String;)V
    //   814: athrow
    //   815: return
    //   816: new java/lang/IllegalArgumentException
    //   819: dup
    //   820: ldc_w 'DrawerLayout must be measured with MeasureSpec.EXACTLY.'
    //   823: invokespecial <init> : (Ljava/lang/String;)V
    //   826: athrow
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    int i = savedState.openDrawerGravity;
    if (i != 0) {
      View view = findDrawerWithGravity(i);
      if (view != null)
        openDrawer(view); 
    } 
    i = savedState.lockModeLeft;
    if (i != 3)
      setDrawerLockMode(i, 3); 
    i = savedState.lockModeRight;
    if (i != 3)
      setDrawerLockMode(i, 5); 
    i = savedState.lockModeStart;
    if (i != 3)
      setDrawerLockMode(i, 8388611); 
    i = savedState.lockModeEnd;
    if (i != 3)
      setDrawerLockMode(i, 8388613); 
  }
  
  public void onRtlPropertiesChanged(int paramInt) {
    resolveShadowDrawables();
  }
  
  protected Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      LayoutParams layoutParams = (LayoutParams)getChildAt(b).getLayoutParams();
      int j = layoutParams.openState;
      boolean bool = true;
      if (j == 1) {
        j = 1;
      } else {
        j = 0;
      } 
      if (layoutParams.openState != 2)
        bool = false; 
      if (j != 0 || bool) {
        savedState.openDrawerGravity = layoutParams.gravity;
        break;
      } 
    } 
    savedState.lockModeLeft = this.mLockModeLeft;
    savedState.lockModeRight = this.mLockModeRight;
    savedState.lockModeStart = this.mLockModeStart;
    savedState.lockModeEnd = this.mLockModeEnd;
    return (Parcelable)savedState;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   4: aload_1
    //   5: invokevirtual processTouchEvent : (Landroid/view/MotionEvent;)V
    //   8: aload_0
    //   9: getfield mRightDragger : Landroidx/customview/widget/ViewDragHelper;
    //   12: aload_1
    //   13: invokevirtual processTouchEvent : (Landroid/view/MotionEvent;)V
    //   16: aload_1
    //   17: invokevirtual getAction : ()I
    //   20: sipush #255
    //   23: iand
    //   24: istore #4
    //   26: iconst_0
    //   27: istore #5
    //   29: iload #4
    //   31: ifeq -> 166
    //   34: iload #4
    //   36: iconst_1
    //   37: if_icmpeq -> 62
    //   40: iload #4
    //   42: iconst_3
    //   43: if_icmpeq -> 49
    //   46: goto -> 191
    //   49: aload_0
    //   50: iconst_1
    //   51: invokevirtual closeDrawers : (Z)V
    //   54: aload_0
    //   55: iconst_0
    //   56: putfield mChildrenCanceledTouch : Z
    //   59: goto -> 191
    //   62: aload_1
    //   63: invokevirtual getX : ()F
    //   66: fstore_3
    //   67: aload_1
    //   68: invokevirtual getY : ()F
    //   71: fstore_2
    //   72: aload_0
    //   73: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   76: fload_3
    //   77: f2i
    //   78: fload_2
    //   79: f2i
    //   80: invokevirtual findTopChildUnder : (II)Landroid/view/View;
    //   83: astore_1
    //   84: aload_1
    //   85: ifnull -> 154
    //   88: aload_0
    //   89: aload_1
    //   90: invokevirtual isContentView : (Landroid/view/View;)Z
    //   93: ifeq -> 154
    //   96: fload_3
    //   97: aload_0
    //   98: getfield mInitialMotionX : F
    //   101: fsub
    //   102: fstore_3
    //   103: fload_2
    //   104: aload_0
    //   105: getfield mInitialMotionY : F
    //   108: fsub
    //   109: fstore_2
    //   110: aload_0
    //   111: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   114: invokevirtual getTouchSlop : ()I
    //   117: istore #4
    //   119: fload_3
    //   120: fload_3
    //   121: fmul
    //   122: fload_2
    //   123: fload_2
    //   124: fmul
    //   125: fadd
    //   126: iload #4
    //   128: iload #4
    //   130: imul
    //   131: i2f
    //   132: fcmpg
    //   133: ifge -> 154
    //   136: aload_0
    //   137: invokevirtual findOpenDrawer : ()Landroid/view/View;
    //   140: astore_1
    //   141: aload_1
    //   142: ifnull -> 154
    //   145: aload_0
    //   146: aload_1
    //   147: invokevirtual getDrawerLockMode : (Landroid/view/View;)I
    //   150: iconst_2
    //   151: if_icmpne -> 157
    //   154: iconst_1
    //   155: istore #5
    //   157: aload_0
    //   158: iload #5
    //   160: invokevirtual closeDrawers : (Z)V
    //   163: goto -> 191
    //   166: aload_1
    //   167: invokevirtual getX : ()F
    //   170: fstore_3
    //   171: aload_1
    //   172: invokevirtual getY : ()F
    //   175: fstore_2
    //   176: aload_0
    //   177: fload_3
    //   178: putfield mInitialMotionX : F
    //   181: aload_0
    //   182: fload_2
    //   183: putfield mInitialMotionY : F
    //   186: aload_0
    //   187: iconst_0
    //   188: putfield mChildrenCanceledTouch : Z
    //   191: iconst_1
    //   192: ireturn
  }
  
  public void openDrawer(int paramInt) {
    openDrawer(paramInt, true);
  }
  
  public void openDrawer(int paramInt, boolean paramBoolean) {
    View view = findDrawerWithGravity(paramInt);
    if (view != null) {
      openDrawer(view, paramBoolean);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("No drawer view found with gravity ");
    stringBuilder.append(gravityToString(paramInt));
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void openDrawer(View paramView) {
    openDrawer(paramView, true);
  }
  
  public void openDrawer(View paramView, boolean paramBoolean) {
    if (isDrawerView(paramView)) {
      LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
      if (this.mFirstLayout) {
        layoutParams.onScreen = 1.0F;
        layoutParams.openState = 1;
        updateChildrenImportantForAccessibility(paramView, true);
      } else if (paramBoolean) {
        layoutParams.openState |= 0x2;
        if (checkDrawerViewAbsoluteGravity(paramView, 3)) {
          this.mLeftDragger.smoothSlideViewTo(paramView, 0, paramView.getTop());
        } else {
          this.mRightDragger.smoothSlideViewTo(paramView, getWidth() - paramView.getWidth(), paramView.getTop());
        } 
      } else {
        moveDrawerToOffset(paramView, 1.0F);
        updateDrawerState(layoutParams.gravity, 0, paramView);
        paramView.setVisibility(0);
      } 
      invalidate();
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("View ");
    stringBuilder.append(paramView);
    stringBuilder.append(" is not a sliding drawer");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void removeDrawerListener(DrawerListener paramDrawerListener) {
    if (paramDrawerListener == null)
      return; 
    List<DrawerListener> list = this.mListeners;
    if (list == null)
      return; 
    list.remove(paramDrawerListener);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean) {
    super.requestDisallowInterceptTouchEvent(paramBoolean);
    if (paramBoolean)
      closeDrawers(true); 
  }
  
  public void requestLayout() {
    if (!this.mInLayout)
      super.requestLayout(); 
  }
  
  public void setChildInsets(Object paramObject, boolean paramBoolean) {
    this.mLastInsets = paramObject;
    this.mDrawStatusBarBackground = paramBoolean;
    if (!paramBoolean && getBackground() == null) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    setWillNotDraw(paramBoolean);
    requestLayout();
  }
  
  public void setDrawerElevation(float paramFloat) {
    this.mDrawerElevation = paramFloat;
    for (byte b = 0; b < getChildCount(); b++) {
      View view = getChildAt(b);
      if (isDrawerView(view))
        ViewCompat.setElevation(view, this.mDrawerElevation); 
    } 
  }
  
  @Deprecated
  public void setDrawerListener(DrawerListener paramDrawerListener) {
    DrawerListener drawerListener = this.mListener;
    if (drawerListener != null)
      removeDrawerListener(drawerListener); 
    if (paramDrawerListener != null)
      addDrawerListener(paramDrawerListener); 
    this.mListener = paramDrawerListener;
  }
  
  public void setDrawerLockMode(int paramInt) {
    setDrawerLockMode(paramInt, 3);
    setDrawerLockMode(paramInt, 5);
  }
  
  public void setDrawerLockMode(int paramInt1, int paramInt2) {
    int i = GravityCompat.getAbsoluteGravity(paramInt2, ViewCompat.getLayoutDirection((View)this));
    if (paramInt2 != 3) {
      if (paramInt2 != 5) {
        if (paramInt2 != 8388611) {
          if (paramInt2 == 8388613)
            this.mLockModeEnd = paramInt1; 
        } else {
          this.mLockModeStart = paramInt1;
        } 
      } else {
        this.mLockModeRight = paramInt1;
      } 
    } else {
      this.mLockModeLeft = paramInt1;
    } 
    if (paramInt1 != 0) {
      ViewDragHelper viewDragHelper;
      if (i == 3) {
        viewDragHelper = this.mLeftDragger;
      } else {
        viewDragHelper = this.mRightDragger;
      } 
      viewDragHelper.cancel();
    } 
    if (paramInt1 != 1) {
      if (paramInt1 == 2) {
        View view = findDrawerWithGravity(i);
        if (view != null)
          openDrawer(view); 
      } 
    } else {
      View view = findDrawerWithGravity(i);
      if (view != null)
        closeDrawer(view); 
    } 
  }
  
  void setDrawerViewOffset(View paramView, float paramFloat) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (paramFloat == layoutParams.onScreen)
      return; 
    layoutParams.onScreen = paramFloat;
    dispatchOnDrawerSlide(paramView, paramFloat);
  }
  
  public void setScrimColor(int paramInt) {
    this.mScrimColor = paramInt;
    invalidate();
  }
  
  public void setStatusBarBackground(int paramInt) {
    Drawable drawable;
    if (paramInt != 0) {
      drawable = ContextCompat.getDrawable(getContext(), paramInt);
    } else {
      drawable = null;
    } 
    this.mStatusBarBackground = drawable;
    invalidate();
  }
  
  public void setStatusBarBackground(Drawable paramDrawable) {
    this.mStatusBarBackground = paramDrawable;
    invalidate();
  }
  
  public void setStatusBarBackgroundColor(int paramInt) {
    this.mStatusBarBackground = (Drawable)new ColorDrawable(paramInt);
    invalidate();
  }
  
  void updateDrawerState(int paramInt1, int paramInt2, View paramView) {
    int j = this.mLeftDragger.getViewDragState();
    int i = this.mRightDragger.getViewDragState();
    byte b = 2;
    if (j == 1 || i == 1) {
      paramInt1 = 1;
    } else {
      paramInt1 = b;
      if (j != 2)
        if (i == 2) {
          paramInt1 = b;
        } else {
          paramInt1 = 0;
        }  
    } 
    if (paramView != null && paramInt2 == 0) {
      float f = ((LayoutParams)paramView.getLayoutParams()).onScreen;
      if (f == 0.0F) {
        dispatchOnDrawerClosed(paramView);
      } else if (f == 1.0F) {
        dispatchOnDrawerOpened(paramView);
      } 
    } 
    if (paramInt1 != this.mDrawerState) {
      this.mDrawerState = paramInt1;
      List<DrawerListener> list = this.mListeners;
      if (list != null)
        for (paramInt2 = list.size() - 1; paramInt2 >= 0; paramInt2--)
          ((DrawerListener)this.mListeners.get(paramInt2)).onDrawerStateChanged(paramInt1);  
    } 
  }
  
  static {
    boolean bool1;
    boolean bool2 = true;
  }
  
  class AccessibilityDelegate extends AccessibilityDelegateCompat {
    private final Rect mTmpRect = new Rect();
    
    final DrawerLayout this$0;
    
    private void addChildrenForAccessibility(AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat, ViewGroup param1ViewGroup) {
      int i = param1ViewGroup.getChildCount();
      for (byte b = 0; b < i; b++) {
        View view = param1ViewGroup.getChildAt(b);
        if (DrawerLayout.includeChildForAccessibility(view))
          param1AccessibilityNodeInfoCompat.addChild(view); 
      } 
    }
    
    private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat1, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat2) {
      Rect rect = this.mTmpRect;
      param1AccessibilityNodeInfoCompat2.getBoundsInParent(rect);
      param1AccessibilityNodeInfoCompat1.setBoundsInParent(rect);
      param1AccessibilityNodeInfoCompat2.getBoundsInScreen(rect);
      param1AccessibilityNodeInfoCompat1.setBoundsInScreen(rect);
      param1AccessibilityNodeInfoCompat1.setVisibleToUser(param1AccessibilityNodeInfoCompat2.isVisibleToUser());
      param1AccessibilityNodeInfoCompat1.setPackageName(param1AccessibilityNodeInfoCompat2.getPackageName());
      param1AccessibilityNodeInfoCompat1.setClassName(param1AccessibilityNodeInfoCompat2.getClassName());
      param1AccessibilityNodeInfoCompat1.setContentDescription(param1AccessibilityNodeInfoCompat2.getContentDescription());
      param1AccessibilityNodeInfoCompat1.setEnabled(param1AccessibilityNodeInfoCompat2.isEnabled());
      param1AccessibilityNodeInfoCompat1.setClickable(param1AccessibilityNodeInfoCompat2.isClickable());
      param1AccessibilityNodeInfoCompat1.setFocusable(param1AccessibilityNodeInfoCompat2.isFocusable());
      param1AccessibilityNodeInfoCompat1.setFocused(param1AccessibilityNodeInfoCompat2.isFocused());
      param1AccessibilityNodeInfoCompat1.setAccessibilityFocused(param1AccessibilityNodeInfoCompat2.isAccessibilityFocused());
      param1AccessibilityNodeInfoCompat1.setSelected(param1AccessibilityNodeInfoCompat2.isSelected());
      param1AccessibilityNodeInfoCompat1.setLongClickable(param1AccessibilityNodeInfoCompat2.isLongClickable());
      param1AccessibilityNodeInfoCompat1.addAction(param1AccessibilityNodeInfoCompat2.getActions());
    }
    
    public boolean dispatchPopulateAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      List<CharSequence> list;
      CharSequence charSequence;
      if (param1AccessibilityEvent.getEventType() == 32) {
        list = param1AccessibilityEvent.getText();
        View view = DrawerLayout.this.findVisibleDrawer();
        if (view != null) {
          int i = DrawerLayout.this.getDrawerViewAbsoluteGravity(view);
          charSequence = DrawerLayout.this.getDrawerTitle(i);
          if (charSequence != null)
            list.add(charSequence); 
        } 
        return true;
      } 
      return super.dispatchPopulateAccessibilityEvent((View)list, (AccessibilityEvent)charSequence);
    }
    
    public void onInitializeAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      super.onInitializeAccessibilityEvent(param1View, param1AccessibilityEvent);
      param1AccessibilityEvent.setClassName(DrawerLayout.class.getName());
    }
    
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      if (DrawerLayout.CAN_HIDE_DESCENDANTS) {
        super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
      } else {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(param1AccessibilityNodeInfoCompat);
        super.onInitializeAccessibilityNodeInfo(param1View, accessibilityNodeInfoCompat);
        param1AccessibilityNodeInfoCompat.setSource(param1View);
        ViewParent viewParent = ViewCompat.getParentForAccessibility(param1View);
        if (viewParent instanceof View)
          param1AccessibilityNodeInfoCompat.setParent((View)viewParent); 
        copyNodeInfoNoChildren(param1AccessibilityNodeInfoCompat, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.recycle();
        addChildrenForAccessibility(param1AccessibilityNodeInfoCompat, (ViewGroup)param1View);
      } 
      param1AccessibilityNodeInfoCompat.setClassName(DrawerLayout.class.getName());
      param1AccessibilityNodeInfoCompat.setFocusable(false);
      param1AccessibilityNodeInfoCompat.setFocused(false);
      param1AccessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
      param1AccessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
    }
    
    public boolean onRequestSendAccessibilityEvent(ViewGroup param1ViewGroup, View param1View, AccessibilityEvent param1AccessibilityEvent) {
      return (DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(param1View)) ? super.onRequestSendAccessibilityEvent(param1ViewGroup, param1View, param1AccessibilityEvent) : false;
    }
  }
  
  static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
      if (!DrawerLayout.includeChildForAccessibility(param1View))
        param1AccessibilityNodeInfoCompat.setParent(null); 
    }
  }
  
  public static interface DrawerListener {
    void onDrawerClosed(View param1View);
    
    void onDrawerOpened(View param1View);
    
    void onDrawerSlide(View param1View, float param1Float);
    
    void onDrawerStateChanged(int param1Int);
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public int gravity = 0;
    
    boolean isPeeking;
    
    float onScreen;
    
    int openState;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, DrawerLayout.LAYOUT_ATTRS);
      this.gravity = typedArray.getInt(0, 0);
      typedArray.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.gravity = param1LayoutParams.gravity;
    }
  }
  
  protected static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public DrawerLayout.SavedState createFromParcel(Parcel param2Parcel) {
          return new DrawerLayout.SavedState(param2Parcel, null);
        }
        
        public DrawerLayout.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new DrawerLayout.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public DrawerLayout.SavedState[] newArray(int param2Int) {
          return new DrawerLayout.SavedState[param2Int];
        }
      };
    
    int lockModeEnd;
    
    int lockModeLeft;
    
    int lockModeRight;
    
    int lockModeStart;
    
    int openDrawerGravity = 0;
    
    public SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      this.openDrawerGravity = param1Parcel.readInt();
      this.lockModeLeft = param1Parcel.readInt();
      this.lockModeRight = param1Parcel.readInt();
      this.lockModeStart = param1Parcel.readInt();
      this.lockModeEnd = param1Parcel.readInt();
    }
    
    public SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.openDrawerGravity);
      param1Parcel.writeInt(this.lockModeLeft);
      param1Parcel.writeInt(this.lockModeRight);
      param1Parcel.writeInt(this.lockModeStart);
      param1Parcel.writeInt(this.lockModeEnd);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public DrawerLayout.SavedState createFromParcel(Parcel param1Parcel) {
      return new DrawerLayout.SavedState(param1Parcel, null);
    }
    
    public DrawerLayout.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new DrawerLayout.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public DrawerLayout.SavedState[] newArray(int param1Int) {
      return new DrawerLayout.SavedState[param1Int];
    }
  }
  
  private class ViewDragCallback extends ViewDragHelper.Callback {
    private final int mAbsGravity;
    
    private ViewDragHelper mDragger;
    
    private final Runnable mPeekRunnable = new Runnable() {
        final DrawerLayout.ViewDragCallback this$1;
        
        public void run() {
          DrawerLayout.ViewDragCallback.this.peekDrawer();
        }
      };
    
    final DrawerLayout this$0;
    
    ViewDragCallback(int param1Int) {
      this.mAbsGravity = param1Int;
    }
    
    private void closeOtherDrawer() {
      int i = this.mAbsGravity;
      byte b = 3;
      if (i == 3)
        b = 5; 
      View view = DrawerLayout.this.findDrawerWithGravity(b);
      if (view != null)
        DrawerLayout.this.closeDrawer(view); 
    }
    
    public int clampViewPositionHorizontal(View param1View, int param1Int1, int param1Int2) {
      if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(param1View, 3))
        return Math.max(-param1View.getWidth(), Math.min(param1Int1, 0)); 
      param1Int2 = DrawerLayout.this.getWidth();
      return Math.max(param1Int2 - param1View.getWidth(), Math.min(param1Int1, param1Int2));
    }
    
    public int clampViewPositionVertical(View param1View, int param1Int1, int param1Int2) {
      return param1View.getTop();
    }
    
    public int getViewHorizontalDragRange(View param1View) {
      boolean bool;
      if (DrawerLayout.this.isDrawerView(param1View)) {
        bool = param1View.getWidth();
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onEdgeDragStarted(int param1Int1, int param1Int2) {
      View view;
      if ((param1Int1 & 0x1) == 1) {
        view = DrawerLayout.this.findDrawerWithGravity(3);
      } else {
        view = DrawerLayout.this.findDrawerWithGravity(5);
      } 
      if (view != null && DrawerLayout.this.getDrawerLockMode(view) == 0)
        this.mDragger.captureChildView(view, param1Int2); 
    }
    
    public boolean onEdgeLock(int param1Int) {
      return false;
    }
    
    public void onEdgeTouched(int param1Int1, int param1Int2) {
      DrawerLayout.this.postDelayed(this.mPeekRunnable, 160L);
    }
    
    public void onViewCaptured(View param1View, int param1Int) {
      ((DrawerLayout.LayoutParams)param1View.getLayoutParams()).isPeeking = false;
      closeOtherDrawer();
    }
    
    public void onViewDragStateChanged(int param1Int) {
      DrawerLayout.this.updateDrawerState(this.mAbsGravity, param1Int, this.mDragger.getCapturedView());
    }
    
    public void onViewPositionChanged(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      float f;
      param1Int2 = param1View.getWidth();
      if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(param1View, 3)) {
        f = (param1Int1 + param1Int2);
      } else {
        f = (DrawerLayout.this.getWidth() - param1Int1);
      } 
      f /= param1Int2;
      DrawerLayout.this.setDrawerViewOffset(param1View, f);
      if (f == 0.0F) {
        param1Int1 = 4;
      } else {
        param1Int1 = 0;
      } 
      param1View.setVisibility(param1Int1);
      DrawerLayout.this.invalidate();
    }
    
    public void onViewReleased(View param1View, float param1Float1, float param1Float2) {
      // Byte code:
      //   0: aload_0
      //   1: getfield this$0 : Landroidx/drawerlayout/widget/DrawerLayout;
      //   4: aload_1
      //   5: invokevirtual getDrawerViewOffset : (Landroid/view/View;)F
      //   8: fstore_3
      //   9: aload_1
      //   10: invokevirtual getWidth : ()I
      //   13: istore #6
      //   15: aload_0
      //   16: getfield this$0 : Landroidx/drawerlayout/widget/DrawerLayout;
      //   19: aload_1
      //   20: iconst_3
      //   21: invokevirtual checkDrawerViewAbsoluteGravity : (Landroid/view/View;I)Z
      //   24: ifeq -> 66
      //   27: fload_2
      //   28: fconst_0
      //   29: fcmpl
      //   30: istore #4
      //   32: iload #4
      //   34: ifgt -> 60
      //   37: iload #4
      //   39: ifne -> 52
      //   42: fload_3
      //   43: ldc 0.5
      //   45: fcmpl
      //   46: ifle -> 52
      //   49: goto -> 60
      //   52: iload #6
      //   54: ineg
      //   55: istore #4
      //   57: goto -> 109
      //   60: iconst_0
      //   61: istore #4
      //   63: goto -> 109
      //   66: aload_0
      //   67: getfield this$0 : Landroidx/drawerlayout/widget/DrawerLayout;
      //   70: invokevirtual getWidth : ()I
      //   73: istore #5
      //   75: fload_2
      //   76: fconst_0
      //   77: fcmpg
      //   78: iflt -> 102
      //   81: iload #5
      //   83: istore #4
      //   85: fload_2
      //   86: fconst_0
      //   87: fcmpl
      //   88: ifne -> 109
      //   91: iload #5
      //   93: istore #4
      //   95: fload_3
      //   96: ldc 0.5
      //   98: fcmpl
      //   99: ifle -> 109
      //   102: iload #5
      //   104: iload #6
      //   106: isub
      //   107: istore #4
      //   109: aload_0
      //   110: getfield mDragger : Landroidx/customview/widget/ViewDragHelper;
      //   113: iload #4
      //   115: aload_1
      //   116: invokevirtual getTop : ()I
      //   119: invokevirtual settleCapturedViewAt : (II)Z
      //   122: pop
      //   123: aload_0
      //   124: getfield this$0 : Landroidx/drawerlayout/widget/DrawerLayout;
      //   127: invokevirtual invalidate : ()V
      //   130: return
    }
    
    void peekDrawer() {
      View view;
      int k = this.mDragger.getEdgeSize();
      int i = this.mAbsGravity;
      int j = 0;
      if (i == 3) {
        i = 1;
      } else {
        i = 0;
      } 
      if (i != 0) {
        view = DrawerLayout.this.findDrawerWithGravity(3);
        if (view != null)
          j = -view.getWidth(); 
        j += k;
      } else {
        view = DrawerLayout.this.findDrawerWithGravity(5);
        j = DrawerLayout.this.getWidth() - k;
      } 
      if (view != null && ((i != 0 && view.getLeft() < j) || (i == 0 && view.getLeft() > j)) && DrawerLayout.this.getDrawerLockMode(view) == 0) {
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams)view.getLayoutParams();
        this.mDragger.smoothSlideViewTo(view, j, view.getTop());
        layoutParams.isPeeking = true;
        DrawerLayout.this.invalidate();
        closeOtherDrawer();
        DrawerLayout.this.cancelChildViewTouch();
      } 
    }
    
    public void removeCallbacks() {
      DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
    }
    
    public void setDragger(ViewDragHelper param1ViewDragHelper) {
      this.mDragger = param1ViewDragHelper;
    }
    
    public boolean tryCaptureView(View param1View, int param1Int) {
      boolean bool;
      if (DrawerLayout.this.isDrawerView(param1View) && DrawerLayout.this.checkDrawerViewAbsoluteGravity(param1View, this.mAbsGravity) && DrawerLayout.this.getDrawerLockMode(param1View) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
  
  class null implements Runnable {
    final DrawerLayout.ViewDragCallback this$1;
    
    public void run() {
      this.this$1.peekDrawer();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/drawerlayout/widget/DrawerLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */