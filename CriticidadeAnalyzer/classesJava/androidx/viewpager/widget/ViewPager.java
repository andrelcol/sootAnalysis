package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
  private static final Comparator<ItemInfo> COMPARATOR;
  
  static final int[] LAYOUT_ATTRS = new int[] { 16842931 };
  
  private static final Interpolator sInterpolator;
  
  private static final ViewPositionComparator sPositionComparator;
  
  private int mActivePointerId = -1;
  
  PagerAdapter mAdapter;
  
  private List<OnAdapterChangeListener> mAdapterChangeListeners;
  
  private int mBottomPageBounds;
  
  private boolean mCalledSuper;
  
  private int mChildHeightMeasureSpec;
  
  private int mCloseEnough;
  
  int mCurItem;
  
  private int mDecorChildCount;
  
  private int mDefaultGutterSize;
  
  private int mDrawingOrder;
  
  private ArrayList<View> mDrawingOrderedChildren;
  
  private final Runnable mEndScrollRunnable = new Runnable() {
      final ViewPager this$0;
      
      public void run() {
        ViewPager.this.setScrollState(0);
        ViewPager.this.populate();
      }
    };
  
  private int mExpectedAdapterCount;
  
  private boolean mFakeDragging;
  
  private boolean mFirstLayout = true;
  
  private float mFirstOffset = -3.4028235E38F;
  
  private int mFlingDistance;
  
  private int mGutterSize;
  
  private boolean mInLayout;
  
  private float mInitialMotionX;
  
  private float mInitialMotionY;
  
  private OnPageChangeListener mInternalPageChangeListener;
  
  private boolean mIsBeingDragged;
  
  private boolean mIsScrollStarted;
  
  private boolean mIsUnableToDrag;
  
  private final ArrayList<ItemInfo> mItems = new ArrayList<ItemInfo>();
  
  private float mLastMotionX;
  
  private float mLastMotionY;
  
  private float mLastOffset = Float.MAX_VALUE;
  
  private EdgeEffect mLeftEdge;
  
  private Drawable mMarginDrawable;
  
  private int mMaximumVelocity;
  
  private int mMinimumVelocity;
  
  private PagerObserver mObserver;
  
  private int mOffscreenPageLimit = 1;
  
  private OnPageChangeListener mOnPageChangeListener;
  
  private List<OnPageChangeListener> mOnPageChangeListeners;
  
  private int mPageMargin;
  
  private PageTransformer mPageTransformer;
  
  private int mPageTransformerLayerType;
  
  private boolean mPopulatePending;
  
  private Parcelable mRestoredAdapterState = null;
  
  private ClassLoader mRestoredClassLoader = null;
  
  private int mRestoredCurItem = -1;
  
  private EdgeEffect mRightEdge;
  
  private int mScrollState = 0;
  
  private Scroller mScroller;
  
  private boolean mScrollingCacheEnabled;
  
  private final ItemInfo mTempItem = new ItemInfo();
  
  private final Rect mTempRect = new Rect();
  
  private int mTopPageBounds;
  
  private int mTouchSlop;
  
  private VelocityTracker mVelocityTracker;
  
  static {
    COMPARATOR = new Comparator<ItemInfo>() {
        public int compare(ViewPager.ItemInfo param1ItemInfo1, ViewPager.ItemInfo param1ItemInfo2) {
          return param1ItemInfo1.position - param1ItemInfo2.position;
        }
      };
    sInterpolator = new Interpolator() {
        public float getInterpolation(float param1Float) {
          param1Float--;
          return param1Float * param1Float * param1Float * param1Float * param1Float + 1.0F;
        }
      };
    sPositionComparator = new ViewPositionComparator();
  }
  
  public ViewPager(Context paramContext) {
    super(paramContext);
    initViewPager();
  }
  
  public ViewPager(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initViewPager();
  }
  
  private void calculatePageOffsets(ItemInfo paramItemInfo1, int paramInt, ItemInfo paramItemInfo2) {
    float f2;
    float f3;
    int m = this.mAdapter.getCount();
    int i = getClientWidth();
    if (i > 0) {
      f2 = this.mPageMargin / i;
    } else {
      f2 = 0.0F;
    } 
    if (paramItemInfo2 != null) {
      i = paramItemInfo2.position;
      int n = paramItemInfo1.position;
      if (i < n) {
        n = 0;
        float f = paramItemInfo2.offset + paramItemInfo2.widthFactor + f2;
        while (true) {
          int i1 = i + 1;
          if (i1 <= paramItemInfo1.position && n < this.mItems.size()) {
            paramItemInfo2 = this.mItems.get(n);
            while (true) {
              i = i1;
              f3 = f;
              if (i1 > paramItemInfo2.position) {
                i = i1;
                f3 = f;
                if (n < this.mItems.size() - 1) {
                  paramItemInfo2 = this.mItems.get(++n);
                  continue;
                } 
              } 
              break;
            } 
            while (i < paramItemInfo2.position) {
              f3 += this.mAdapter.getPageWidth(i) + f2;
              i++;
            } 
            paramItemInfo2.offset = f3;
            f = f3 + paramItemInfo2.widthFactor + f2;
            continue;
          } 
          break;
        } 
      } else if (i > n) {
        n = this.mItems.size() - 1;
        float f = paramItemInfo2.offset;
        while (--i >= paramItemInfo1.position && n >= 0) {
          int i1;
          paramItemInfo2 = this.mItems.get(n);
          while (true) {
            i1 = i;
            f3 = f;
            if (i < paramItemInfo2.position) {
              i1 = i;
              f3 = f;
              if (n > 0) {
                paramItemInfo2 = this.mItems.get(--n);
                continue;
              } 
            } 
            break;
          } 
          while (i1 > paramItemInfo2.position) {
            f3 -= this.mAdapter.getPageWidth(i1) + f2;
            i1--;
          } 
          f = f3 - paramItemInfo2.widthFactor + f2;
          paramItemInfo2.offset = f;
          i = i1 - 1;
        } 
      } 
    } 
    int k = this.mItems.size();
    float f1 = paramItemInfo1.offset;
    int j = paramItemInfo1.position;
    i = j - 1;
    if (j == 0) {
      f3 = f1;
    } else {
      f3 = -3.4028235E38F;
    } 
    this.mFirstOffset = f3;
    j = paramItemInfo1.position;
    if (j == --m) {
      f3 = paramItemInfo1.offset + paramItemInfo1.widthFactor - 1.0F;
    } else {
      f3 = Float.MAX_VALUE;
    } 
    this.mLastOffset = f3;
    j = paramInt - 1;
    while (j >= 0) {
      paramItemInfo2 = this.mItems.get(j);
      while (true) {
        int n = paramItemInfo2.position;
        if (i > n) {
          f1 -= this.mAdapter.getPageWidth(i) + f2;
          i--;
          continue;
        } 
        f1 -= paramItemInfo2.widthFactor + f2;
        paramItemInfo2.offset = f1;
        if (n == 0)
          this.mFirstOffset = f1; 
        break;
      } 
      j--;
      i--;
    } 
    f1 = paramItemInfo1.offset + paramItemInfo1.widthFactor + f2;
    j = paramItemInfo1.position + 1;
    i = paramInt + 1;
    for (paramInt = j; i < k; paramInt++) {
      paramItemInfo1 = this.mItems.get(i);
      while (true) {
        j = paramItemInfo1.position;
        if (paramInt < j) {
          f1 += this.mAdapter.getPageWidth(paramInt) + f2;
          paramInt++;
          continue;
        } 
        if (j == m)
          this.mLastOffset = paramItemInfo1.widthFactor + f1 - 1.0F; 
        break;
      } 
      paramItemInfo1.offset = f1;
      f1 += paramItemInfo1.widthFactor + f2;
      i++;
    } 
  }
  
  private void completeScroll(boolean paramBoolean) {
    if (this.mScrollState == 2) {
      b1 = 1;
    } else {
      b1 = 0;
    } 
    if (b1) {
      setScrollingCacheEnabled(false);
      if ((this.mScroller.isFinished() ^ true) != 0) {
        this.mScroller.abortAnimation();
        int m = getScrollX();
        int k = getScrollY();
        int j = this.mScroller.getCurrX();
        int i = this.mScroller.getCurrY();
        if (m != j || k != i) {
          scrollTo(j, i);
          if (j != m)
            pageScrolled(j); 
        } 
      } 
    } 
    this.mPopulatePending = false;
    byte b3 = 0;
    byte b2 = b1;
    for (byte b1 = b3; b1 < this.mItems.size(); b1++) {
      ItemInfo itemInfo = this.mItems.get(b1);
      if (itemInfo.scrolling) {
        itemInfo.scrolling = false;
        b2 = 1;
      } 
    } 
    if (b2 != 0)
      if (paramBoolean) {
        ViewCompat.postOnAnimation((View)this, this.mEndScrollRunnable);
      } else {
        this.mEndScrollRunnable.run();
      }  
  }
  
  private int determineTargetPage(int paramInt1, float paramFloat, int paramInt2, int paramInt3) {
    if (Math.abs(paramInt3) > this.mFlingDistance && Math.abs(paramInt2) > this.mMinimumVelocity) {
      if (paramInt2 <= 0)
        paramInt1++; 
    } else {
      float f;
      if (paramInt1 >= this.mCurItem) {
        f = 0.4F;
      } else {
        f = 0.6F;
      } 
      paramInt1 += (int)(paramFloat + f);
    } 
    paramInt2 = paramInt1;
    if (this.mItems.size() > 0) {
      ItemInfo itemInfo1 = this.mItems.get(0);
      ArrayList<ItemInfo> arrayList = this.mItems;
      ItemInfo itemInfo2 = arrayList.get(arrayList.size() - 1);
      paramInt2 = Math.max(itemInfo1.position, Math.min(paramInt1, itemInfo2.position));
    } 
    return paramInt2;
  }
  
  private void dispatchOnPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListener;
    if (onPageChangeListener2 != null)
      onPageChangeListener2.onPageScrolled(paramInt1, paramFloat, paramInt2); 
    List<OnPageChangeListener> list = this.mOnPageChangeListeners;
    if (list != null) {
      byte b = 0;
      int i = list.size();
      while (b < i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(b);
        if (onPageChangeListener != null)
          onPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2); 
        b++;
      } 
    } 
    OnPageChangeListener onPageChangeListener1 = this.mInternalPageChangeListener;
    if (onPageChangeListener1 != null)
      onPageChangeListener1.onPageScrolled(paramInt1, paramFloat, paramInt2); 
  }
  
  private void dispatchOnPageSelected(int paramInt) {
    OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListener;
    if (onPageChangeListener2 != null)
      onPageChangeListener2.onPageSelected(paramInt); 
    List<OnPageChangeListener> list = this.mOnPageChangeListeners;
    if (list != null) {
      byte b = 0;
      int i = list.size();
      while (b < i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(b);
        if (onPageChangeListener != null)
          onPageChangeListener.onPageSelected(paramInt); 
        b++;
      } 
    } 
    OnPageChangeListener onPageChangeListener1 = this.mInternalPageChangeListener;
    if (onPageChangeListener1 != null)
      onPageChangeListener1.onPageSelected(paramInt); 
  }
  
  private void dispatchOnScrollStateChanged(int paramInt) {
    OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListener;
    if (onPageChangeListener2 != null)
      onPageChangeListener2.onPageScrollStateChanged(paramInt); 
    List<OnPageChangeListener> list = this.mOnPageChangeListeners;
    if (list != null) {
      byte b = 0;
      int i = list.size();
      while (b < i) {
        OnPageChangeListener onPageChangeListener = this.mOnPageChangeListeners.get(b);
        if (onPageChangeListener != null)
          onPageChangeListener.onPageScrollStateChanged(paramInt); 
        b++;
      } 
    } 
    OnPageChangeListener onPageChangeListener1 = this.mInternalPageChangeListener;
    if (onPageChangeListener1 != null)
      onPageChangeListener1.onPageScrollStateChanged(paramInt); 
  }
  
  private void enableLayers(boolean paramBoolean) {
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      boolean bool;
      if (paramBoolean) {
        bool = this.mPageTransformerLayerType;
      } else {
        bool = false;
      } 
      getChildAt(b).setLayerType(bool, null);
    } 
  }
  
  private void endDrag() {
    this.mIsBeingDragged = false;
    this.mIsUnableToDrag = false;
    VelocityTracker velocityTracker = this.mVelocityTracker;
    if (velocityTracker != null) {
      velocityTracker.recycle();
      this.mVelocityTracker = null;
    } 
  }
  
  private Rect getChildRectInPagerCoordinates(Rect paramRect, View paramView) {
    Rect rect = paramRect;
    if (paramRect == null)
      rect = new Rect(); 
    if (paramView == null) {
      rect.set(0, 0, 0, 0);
      return rect;
    } 
    rect.left = paramView.getLeft();
    rect.right = paramView.getRight();
    rect.top = paramView.getTop();
    rect.bottom = paramView.getBottom();
    ViewParent viewParent = paramView.getParent();
    while (viewParent instanceof ViewGroup && viewParent != this) {
      ViewGroup viewGroup = (ViewGroup)viewParent;
      rect.left += viewGroup.getLeft();
      rect.right += viewGroup.getRight();
      rect.top += viewGroup.getTop();
      rect.bottom += viewGroup.getBottom();
      ViewParent viewParent1 = viewGroup.getParent();
    } 
    return rect;
  }
  
  private int getClientWidth() {
    return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  private ItemInfo infoForCurrentScrollPosition() {
    float f1;
    float f2;
    int i = getClientWidth();
    if (i > 0) {
      f1 = getScrollX() / i;
    } else {
      f1 = 0.0F;
    } 
    if (i > 0) {
      f2 = this.mPageMargin / i;
    } else {
      f2 = 0.0F;
    } 
    ItemInfo itemInfo = null;
    i = 0;
    boolean bool = true;
    int j = -1;
    float f4 = 0.0F;
    float f3 = 0.0F;
    while (i < this.mItems.size()) {
      ItemInfo itemInfo2 = this.mItems.get(i);
      int k = i;
      ItemInfo itemInfo1 = itemInfo2;
      if (!bool) {
        int m = itemInfo2.position;
        j++;
        k = i;
        itemInfo1 = itemInfo2;
        if (m != j) {
          itemInfo1 = this.mTempItem;
          itemInfo1.offset = f4 + f3 + f2;
          itemInfo1.position = j;
          itemInfo1.widthFactor = this.mAdapter.getPageWidth(itemInfo1.position);
          k = i - 1;
        } 
      } 
      f4 = itemInfo1.offset;
      f3 = itemInfo1.widthFactor;
      if (bool || f1 >= f4) {
        if (f1 < f3 + f4 + f2 || k == this.mItems.size() - 1)
          return itemInfo1; 
        j = itemInfo1.position;
        f3 = itemInfo1.widthFactor;
        i = k + 1;
        bool = false;
        itemInfo = itemInfo1;
        continue;
      } 
      return itemInfo;
    } 
    return itemInfo;
  }
  
  private static boolean isDecorView(View paramView) {
    boolean bool;
    if (paramView.getClass().getAnnotation(DecorView.class) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isGutterDrag(float paramFloat1, float paramFloat2) {
    boolean bool;
    if ((paramFloat1 < this.mGutterSize && paramFloat2 > 0.0F) || (paramFloat1 > (getWidth() - this.mGutterSize) && paramFloat2 < 0.0F)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == this.mActivePointerId) {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      } 
      this.mLastMotionX = paramMotionEvent.getX(i);
      this.mActivePointerId = paramMotionEvent.getPointerId(i);
      VelocityTracker velocityTracker = this.mVelocityTracker;
      if (velocityTracker != null)
        velocityTracker.clear(); 
    } 
  }
  
  private boolean pageScrolled(int paramInt) {
    if (this.mItems.size() == 0) {
      if (this.mFirstLayout)
        return false; 
      this.mCalledSuper = false;
      onPageScrolled(0, 0.0F, 0);
      if (this.mCalledSuper)
        return false; 
      throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    } 
    ItemInfo itemInfo = infoForCurrentScrollPosition();
    int k = getClientWidth();
    int j = this.mPageMargin;
    float f2 = j;
    float f1 = k;
    f2 /= f1;
    int i = itemInfo.position;
    f1 = (paramInt / f1 - itemInfo.offset) / (itemInfo.widthFactor + f2);
    paramInt = (int)((k + j) * f1);
    this.mCalledSuper = false;
    onPageScrolled(i, f1, paramInt);
    if (this.mCalledSuper)
      return true; 
    throw new IllegalStateException("onPageScrolled did not call superclass implementation");
  }
  
  private boolean performDrag(float paramFloat) {
    boolean bool1;
    float f1 = this.mLastMotionX;
    this.mLastMotionX = paramFloat;
    float f2 = getScrollX() + f1 - paramFloat;
    float f3 = getClientWidth();
    paramFloat = this.mFirstOffset * f3;
    f1 = this.mLastOffset * f3;
    ArrayList<ItemInfo> arrayList1 = this.mItems;
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool4 = false;
    ItemInfo itemInfo1 = arrayList1.get(0);
    ArrayList<ItemInfo> arrayList2 = this.mItems;
    ItemInfo itemInfo2 = arrayList2.get(arrayList2.size() - 1);
    if (itemInfo1.position != 0) {
      paramFloat = itemInfo1.offset * f3;
      i = 0;
    } else {
      i = 1;
    } 
    if (itemInfo2.position != this.mAdapter.getCount() - 1) {
      f1 = itemInfo2.offset * f3;
      bool1 = false;
    } else {
      bool1 = true;
    } 
    if (f2 < paramFloat) {
      bool2 = bool4;
      if (i) {
        this.mLeftEdge.onPull(Math.abs(paramFloat - f2) / f3);
        bool2 = true;
      } 
    } else {
      paramFloat = f2;
      if (f2 > f1) {
        bool2 = bool3;
        if (bool1) {
          this.mRightEdge.onPull(Math.abs(f2 - f1) / f3);
          bool2 = true;
        } 
        paramFloat = f1;
      } 
    } 
    f1 = this.mLastMotionX;
    int i = (int)paramFloat;
    this.mLastMotionX = f1 + paramFloat - i;
    scrollTo(i, getScrollY());
    pageScrolled(i);
    return bool2;
  }
  
  private void recomputeScrollPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt2 > 0 && !this.mItems.isEmpty()) {
      if (!this.mScroller.isFinished()) {
        this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
      } else {
        int k = getPaddingLeft();
        int i = getPaddingRight();
        int j = getPaddingLeft();
        int m = getPaddingRight();
        scrollTo((int)(getScrollX() / (paramInt2 - j - m + paramInt4) * (paramInt1 - k - i + paramInt3)), getScrollY());
      } 
    } else {
      float f;
      ItemInfo itemInfo = infoForPosition(this.mCurItem);
      if (itemInfo != null) {
        f = Math.min(itemInfo.offset, this.mLastOffset);
      } else {
        f = 0.0F;
      } 
      paramInt1 = (int)(f * (paramInt1 - getPaddingLeft() - getPaddingRight()));
      if (paramInt1 != getScrollX()) {
        completeScroll(false);
        scrollTo(paramInt1, getScrollY());
      } 
    } 
  }
  
  private void removeNonDecorViews() {
    for (int i = 0; i < getChildCount(); i = j + 1) {
      int j = i;
      if (!((LayoutParams)getChildAt(i).getLayoutParams()).isDecor) {
        removeViewAt(i);
        j = i - 1;
      } 
    } 
  }
  
  private void requestParentDisallowInterceptTouchEvent(boolean paramBoolean) {
    ViewParent viewParent = getParent();
    if (viewParent != null)
      viewParent.requestDisallowInterceptTouchEvent(paramBoolean); 
  }
  
  private boolean resetTouch() {
    this.mActivePointerId = -1;
    endDrag();
    this.mLeftEdge.onRelease();
    this.mRightEdge.onRelease();
    return (this.mLeftEdge.isFinished() || this.mRightEdge.isFinished());
  }
  
  private void scrollToItem(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2) {
    boolean bool;
    ItemInfo itemInfo = infoForPosition(paramInt1);
    if (itemInfo != null) {
      bool = (int)(getClientWidth() * Math.max(this.mFirstOffset, Math.min(itemInfo.offset, this.mLastOffset)));
    } else {
      bool = false;
    } 
    if (paramBoolean1) {
      smoothScrollTo(bool, 0, paramInt2);
      if (paramBoolean2)
        dispatchOnPageSelected(paramInt1); 
    } else {
      if (paramBoolean2)
        dispatchOnPageSelected(paramInt1); 
      completeScroll(false);
      scrollTo(bool, 0);
      pageScrolled(bool);
    } 
  }
  
  private void setScrollingCacheEnabled(boolean paramBoolean) {
    if (this.mScrollingCacheEnabled != paramBoolean)
      this.mScrollingCacheEnabled = paramBoolean; 
  }
  
  private void sortChildDrawingOrder() {
    if (this.mDrawingOrder != 0) {
      ArrayList<View> arrayList = this.mDrawingOrderedChildren;
      if (arrayList == null) {
        this.mDrawingOrderedChildren = new ArrayList<View>();
      } else {
        arrayList.clear();
      } 
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        View view = getChildAt(b);
        this.mDrawingOrderedChildren.add(view);
      } 
      Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
    } 
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2) {
    int i = paramArrayList.size();
    int j = getDescendantFocusability();
    if (j != 393216)
      for (byte b = 0; b < getChildCount(); b++) {
        View view = getChildAt(b);
        if (view.getVisibility() == 0) {
          ItemInfo itemInfo = infoForChild(view);
          if (itemInfo != null && itemInfo.position == this.mCurItem)
            view.addFocusables(paramArrayList, paramInt1, paramInt2); 
        } 
      }  
    if (j != 262144 || i == paramArrayList.size()) {
      if (!isFocusable())
        return; 
      if ((paramInt2 & 0x1) == 1 && isInTouchMode() && !isFocusableInTouchMode())
        return; 
      if (paramArrayList != null)
        paramArrayList.add(this); 
    } 
  }
  
  ItemInfo addNewItem(int paramInt1, int paramInt2) {
    ItemInfo itemInfo = new ItemInfo();
    itemInfo.position = paramInt1;
    itemInfo.object = this.mAdapter.instantiateItem(this, paramInt1);
    itemInfo.widthFactor = this.mAdapter.getPageWidth(paramInt1);
    if (paramInt2 < 0 || paramInt2 >= this.mItems.size()) {
      this.mItems.add(itemInfo);
      return itemInfo;
    } 
    this.mItems.add(paramInt2, itemInfo);
    return itemInfo;
  }
  
  public void addOnAdapterChangeListener(OnAdapterChangeListener paramOnAdapterChangeListener) {
    if (this.mAdapterChangeListeners == null)
      this.mAdapterChangeListeners = new ArrayList<OnAdapterChangeListener>(); 
    this.mAdapterChangeListeners.add(paramOnAdapterChangeListener);
  }
  
  public void addOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
    if (this.mOnPageChangeListeners == null)
      this.mOnPageChangeListeners = new ArrayList<OnPageChangeListener>(); 
    this.mOnPageChangeListeners.add(paramOnPageChangeListener);
  }
  
  public void addTouchables(ArrayList<View> paramArrayList) {
    for (byte b = 0; b < getChildCount(); b++) {
      View view = getChildAt(b);
      if (view.getVisibility() == 0) {
        ItemInfo itemInfo = infoForChild(view);
        if (itemInfo != null && itemInfo.position == this.mCurItem)
          view.addTouchables(paramArrayList); 
      } 
    } 
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    ViewGroup.LayoutParams layoutParams = paramLayoutParams;
    if (!checkLayoutParams(paramLayoutParams))
      layoutParams = generateLayoutParams(paramLayoutParams); 
    paramLayoutParams = layoutParams;
    ((LayoutParams)paramLayoutParams).isDecor |= isDecorView(paramView);
    if (this.mInLayout) {
      if (paramLayoutParams == null || !((LayoutParams)paramLayoutParams).isDecor) {
        ((LayoutParams)paramLayoutParams).needsMeasure = true;
        addViewInLayout(paramView, paramInt, layoutParams);
        return;
      } 
      throw new IllegalStateException("Cannot add pager decor view during layout");
    } 
    super.addView(paramView, paramInt, layoutParams);
  }
  
  public boolean arrowScroll(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual findFocus : ()Landroid/view/View;
    //   4: astore #7
    //   6: iconst_0
    //   7: istore #4
    //   9: aconst_null
    //   10: astore #6
    //   12: aload #7
    //   14: aload_0
    //   15: if_acmpne -> 25
    //   18: aload #6
    //   20: astore #5
    //   22: goto -> 193
    //   25: aload #7
    //   27: ifnull -> 189
    //   30: aload #7
    //   32: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   35: astore #5
    //   37: aload #5
    //   39: instanceof android/view/ViewGroup
    //   42: ifeq -> 68
    //   45: aload #5
    //   47: aload_0
    //   48: if_acmpne -> 56
    //   51: iconst_1
    //   52: istore_2
    //   53: goto -> 70
    //   56: aload #5
    //   58: invokeinterface getParent : ()Landroid/view/ViewParent;
    //   63: astore #5
    //   65: goto -> 37
    //   68: iconst_0
    //   69: istore_2
    //   70: iload_2
    //   71: ifne -> 189
    //   74: new java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial <init> : ()V
    //   81: astore #8
    //   83: aload #8
    //   85: aload #7
    //   87: invokevirtual getClass : ()Ljava/lang/Class;
    //   90: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   93: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: aload #7
    //   99: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   102: astore #5
    //   104: aload #5
    //   106: instanceof android/view/ViewGroup
    //   109: ifeq -> 147
    //   112: aload #8
    //   114: ldc_w ' => '
    //   117: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: pop
    //   121: aload #8
    //   123: aload #5
    //   125: invokevirtual getClass : ()Ljava/lang/Class;
    //   128: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload #5
    //   137: invokeinterface getParent : ()Landroid/view/ViewParent;
    //   142: astore #5
    //   144: goto -> 104
    //   147: new java/lang/StringBuilder
    //   150: dup
    //   151: invokespecial <init> : ()V
    //   154: astore #5
    //   156: aload #5
    //   158: ldc_w 'arrowScroll tried to find focus based on non-child current focused view '
    //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: aload #5
    //   167: aload #8
    //   169: invokevirtual toString : ()Ljava/lang/String;
    //   172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: pop
    //   176: aload #5
    //   178: invokevirtual toString : ()Ljava/lang/String;
    //   181: pop
    //   182: aload #6
    //   184: astore #5
    //   186: goto -> 193
    //   189: aload #7
    //   191: astore #5
    //   193: invokestatic getInstance : ()Landroid/view/FocusFinder;
    //   196: aload_0
    //   197: aload #5
    //   199: iload_1
    //   200: invokevirtual findNextFocus : (Landroid/view/ViewGroup;Landroid/view/View;I)Landroid/view/View;
    //   203: astore #6
    //   205: aload #6
    //   207: ifnull -> 343
    //   210: aload #6
    //   212: aload #5
    //   214: if_acmpeq -> 343
    //   217: iload_1
    //   218: bipush #17
    //   220: if_icmpne -> 280
    //   223: aload_0
    //   224: aload_0
    //   225: getfield mTempRect : Landroid/graphics/Rect;
    //   228: aload #6
    //   230: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   233: getfield left : I
    //   236: istore_3
    //   237: aload_0
    //   238: aload_0
    //   239: getfield mTempRect : Landroid/graphics/Rect;
    //   242: aload #5
    //   244: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   247: getfield left : I
    //   250: istore_2
    //   251: aload #5
    //   253: ifnull -> 270
    //   256: iload_3
    //   257: iload_2
    //   258: if_icmplt -> 270
    //   261: aload_0
    //   262: invokevirtual pageLeft : ()Z
    //   265: istore #4
    //   267: goto -> 277
    //   270: aload #6
    //   272: invokevirtual requestFocus : ()Z
    //   275: istore #4
    //   277: goto -> 383
    //   280: iload_1
    //   281: bipush #66
    //   283: if_icmpne -> 383
    //   286: aload_0
    //   287: aload_0
    //   288: getfield mTempRect : Landroid/graphics/Rect;
    //   291: aload #6
    //   293: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   296: getfield left : I
    //   299: istore_2
    //   300: aload_0
    //   301: aload_0
    //   302: getfield mTempRect : Landroid/graphics/Rect;
    //   305: aload #5
    //   307: invokespecial getChildRectInPagerCoordinates : (Landroid/graphics/Rect;Landroid/view/View;)Landroid/graphics/Rect;
    //   310: getfield left : I
    //   313: istore_3
    //   314: aload #5
    //   316: ifnull -> 333
    //   319: iload_2
    //   320: iload_3
    //   321: if_icmpgt -> 333
    //   324: aload_0
    //   325: invokevirtual pageRight : ()Z
    //   328: istore #4
    //   330: goto -> 277
    //   333: aload #6
    //   335: invokevirtual requestFocus : ()Z
    //   338: istore #4
    //   340: goto -> 277
    //   343: iload_1
    //   344: bipush #17
    //   346: if_icmpeq -> 377
    //   349: iload_1
    //   350: iconst_1
    //   351: if_icmpne -> 357
    //   354: goto -> 377
    //   357: iload_1
    //   358: bipush #66
    //   360: if_icmpeq -> 368
    //   363: iload_1
    //   364: iconst_2
    //   365: if_icmpne -> 383
    //   368: aload_0
    //   369: invokevirtual pageRight : ()Z
    //   372: istore #4
    //   374: goto -> 383
    //   377: aload_0
    //   378: invokevirtual pageLeft : ()Z
    //   381: istore #4
    //   383: iload #4
    //   385: ifeq -> 396
    //   388: aload_0
    //   389: iload_1
    //   390: invokestatic getContantForFocusDirection : (I)I
    //   393: invokevirtual playSoundEffect : (I)V
    //   396: iload #4
    //   398: ireturn
  }
  
  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3) {
    boolean bool1 = paramView instanceof ViewGroup;
    boolean bool = true;
    if (bool1) {
      ViewGroup viewGroup = (ViewGroup)paramView;
      int k = paramView.getScrollX();
      int j = paramView.getScrollY();
      for (int i = viewGroup.getChildCount() - 1; i >= 0; i--) {
        View view = viewGroup.getChildAt(i);
        int m = paramInt2 + k;
        if (m >= view.getLeft() && m < view.getRight()) {
          int n = paramInt3 + j;
          if (n >= view.getTop() && n < view.getBottom() && canScroll(view, true, paramInt1, m - view.getLeft(), n - view.getTop()))
            return true; 
        } 
      } 
    } 
    if (paramBoolean && paramView.canScrollHorizontally(-paramInt1)) {
      paramBoolean = bool;
    } else {
      paramBoolean = false;
    } 
    return paramBoolean;
  }
  
  public boolean canScrollHorizontally(int paramInt) {
    PagerAdapter pagerAdapter = this.mAdapter;
    boolean bool2 = false;
    boolean bool1 = false;
    if (pagerAdapter == null)
      return false; 
    int i = getClientWidth();
    int j = getScrollX();
    if (paramInt < 0) {
      if (j > (int)(i * this.mFirstOffset))
        bool1 = true; 
      return bool1;
    } 
    bool1 = bool2;
    if (paramInt > 0) {
      bool1 = bool2;
      if (j < (int)(i * this.mLastOffset))
        bool1 = true; 
    } 
    return bool1;
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
  
  public void computeScroll() {
    this.mIsScrollStarted = true;
    if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
      int m = getScrollX();
      int j = getScrollY();
      int k = this.mScroller.getCurrX();
      int i = this.mScroller.getCurrY();
      if (m != k || j != i) {
        scrollTo(k, i);
        if (!pageScrolled(k)) {
          this.mScroller.abortAnimation();
          scrollTo(0, i);
        } 
      } 
      ViewCompat.postInvalidateOnAnimation((View)this);
      return;
    } 
    completeScroll(true);
  }
  
  void dataSetChanged() {
    Object object1;
    int i;
    Object object2;
    byte b;
    int i1 = this.mAdapter.getCount();
    this.mExpectedAdapterCount = i1;
    if (this.mItems.size() < this.mOffscreenPageLimit * 2 + 1 && this.mItems.size() < i1) {
      j = 1;
    } else {
      j = 0;
    } 
    int k = this.mCurItem;
    int n = j;
    int j = k;
    int m = 0;
    k = 0;
    while (m < this.mItems.size()) {
      ItemInfo itemInfo = this.mItems.get(m);
      int i4 = this.mAdapter.getItemPosition(itemInfo.object);
      if (i4 == -1) {
        int i5 = m;
        Object object3 = object2;
        Object object4 = object1;
        continue;
      } 
      if (i4 == -2) {
        byte b2;
        this.mItems.remove(m);
        int i5 = m - 1;
        Object object = object2;
        if (object2 == null) {
          this.mAdapter.startUpdate(this);
          b2 = 1;
        } 
        this.mAdapter.destroyItem(this, itemInfo.position, itemInfo.object);
        n = this.mCurItem;
        m = i5;
        b = b2;
        if (n == itemInfo.position) {
          i = Math.max(0, Math.min(n, i1 - 1));
          b = b2;
          m = i5;
        } 
      } else {
        int i7 = itemInfo.position;
        int i5 = m;
        byte b2 = b;
        int i6 = i;
        if (i7 != i4) {
          if (i7 == this.mCurItem)
            i = i4; 
          itemInfo.position = i4;
        } else {
          continue;
        } 
      } 
      n = 1;
      int i2 = m;
      byte b1 = b;
      int i3 = i;
      continue;
      m = SYNTHETIC_LOCAL_VARIABLE_5 + 1;
      object2 = SYNTHETIC_LOCAL_VARIABLE_4;
      object1 = SYNTHETIC_LOCAL_VARIABLE_7;
    } 
    if (b)
      this.mAdapter.finishUpdate(this); 
    Collections.sort(this.mItems, COMPARATOR);
    if (n != 0) {
      m = getChildCount();
      for (b = 0; b < m; b++) {
        LayoutParams layoutParams = (LayoutParams)getChildAt(b).getLayoutParams();
        if (!layoutParams.isDecor)
          layoutParams.widthFactor = 0.0F; 
      } 
      setCurrentItemInternal(i, false, true);
      requestLayout();
    } 
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    return (super.dispatchKeyEvent(paramKeyEvent) || executeKeyEvent(paramKeyEvent));
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    if (paramAccessibilityEvent.getEventType() == 4096)
      return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent); 
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = getChildAt(b);
      if (view.getVisibility() == 0) {
        ItemInfo itemInfo = infoForChild(view);
        if (itemInfo != null && itemInfo.position == this.mCurItem && view.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent))
          return true; 
      } 
    } 
    return false;
  }
  
  float distanceInfluenceForSnapDuration(float paramFloat) {
    return (float)Math.sin(((paramFloat - 0.5F) * 0.47123894F));
  }
  
  public void draw(Canvas paramCanvas) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial draw : (Landroid/graphics/Canvas;)V
    //   5: aload_0
    //   6: invokevirtual getOverScrollMode : ()I
    //   9: istore #4
    //   11: iconst_0
    //   12: istore_3
    //   13: iconst_0
    //   14: istore_2
    //   15: iload #4
    //   17: ifeq -> 66
    //   20: iload #4
    //   22: iconst_1
    //   23: if_icmpne -> 49
    //   26: aload_0
    //   27: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   30: astore #8
    //   32: aload #8
    //   34: ifnull -> 49
    //   37: aload #8
    //   39: invokevirtual getCount : ()I
    //   42: iconst_1
    //   43: if_icmple -> 49
    //   46: goto -> 66
    //   49: aload_0
    //   50: getfield mLeftEdge : Landroid/widget/EdgeEffect;
    //   53: invokevirtual finish : ()V
    //   56: aload_0
    //   57: getfield mRightEdge : Landroid/widget/EdgeEffect;
    //   60: invokevirtual finish : ()V
    //   63: goto -> 257
    //   66: aload_0
    //   67: getfield mLeftEdge : Landroid/widget/EdgeEffect;
    //   70: invokevirtual isFinished : ()Z
    //   73: ifne -> 155
    //   76: aload_1
    //   77: invokevirtual save : ()I
    //   80: istore_3
    //   81: aload_0
    //   82: invokevirtual getHeight : ()I
    //   85: aload_0
    //   86: invokevirtual getPaddingTop : ()I
    //   89: isub
    //   90: aload_0
    //   91: invokevirtual getPaddingBottom : ()I
    //   94: isub
    //   95: istore_2
    //   96: aload_0
    //   97: invokevirtual getWidth : ()I
    //   100: istore #4
    //   102: aload_1
    //   103: ldc_w 270.0
    //   106: invokevirtual rotate : (F)V
    //   109: aload_1
    //   110: iload_2
    //   111: ineg
    //   112: aload_0
    //   113: invokevirtual getPaddingTop : ()I
    //   116: iadd
    //   117: i2f
    //   118: aload_0
    //   119: getfield mFirstOffset : F
    //   122: iload #4
    //   124: i2f
    //   125: fmul
    //   126: invokevirtual translate : (FF)V
    //   129: aload_0
    //   130: getfield mLeftEdge : Landroid/widget/EdgeEffect;
    //   133: iload_2
    //   134: iload #4
    //   136: invokevirtual setSize : (II)V
    //   139: iconst_0
    //   140: aload_0
    //   141: getfield mLeftEdge : Landroid/widget/EdgeEffect;
    //   144: aload_1
    //   145: invokevirtual draw : (Landroid/graphics/Canvas;)Z
    //   148: ior
    //   149: istore_2
    //   150: aload_1
    //   151: iload_3
    //   152: invokevirtual restoreToCount : (I)V
    //   155: iload_2
    //   156: istore_3
    //   157: aload_0
    //   158: getfield mRightEdge : Landroid/widget/EdgeEffect;
    //   161: invokevirtual isFinished : ()Z
    //   164: ifne -> 257
    //   167: aload_1
    //   168: invokevirtual save : ()I
    //   171: istore #4
    //   173: aload_0
    //   174: invokevirtual getWidth : ()I
    //   177: istore #5
    //   179: aload_0
    //   180: invokevirtual getHeight : ()I
    //   183: istore_3
    //   184: aload_0
    //   185: invokevirtual getPaddingTop : ()I
    //   188: istore #7
    //   190: aload_0
    //   191: invokevirtual getPaddingBottom : ()I
    //   194: istore #6
    //   196: aload_1
    //   197: ldc_w 90.0
    //   200: invokevirtual rotate : (F)V
    //   203: aload_1
    //   204: aload_0
    //   205: invokevirtual getPaddingTop : ()I
    //   208: ineg
    //   209: i2f
    //   210: aload_0
    //   211: getfield mLastOffset : F
    //   214: fconst_1
    //   215: fadd
    //   216: fneg
    //   217: iload #5
    //   219: i2f
    //   220: fmul
    //   221: invokevirtual translate : (FF)V
    //   224: aload_0
    //   225: getfield mRightEdge : Landroid/widget/EdgeEffect;
    //   228: iload_3
    //   229: iload #7
    //   231: isub
    //   232: iload #6
    //   234: isub
    //   235: iload #5
    //   237: invokevirtual setSize : (II)V
    //   240: iload_2
    //   241: aload_0
    //   242: getfield mRightEdge : Landroid/widget/EdgeEffect;
    //   245: aload_1
    //   246: invokevirtual draw : (Landroid/graphics/Canvas;)Z
    //   249: ior
    //   250: istore_3
    //   251: aload_1
    //   252: iload #4
    //   254: invokevirtual restoreToCount : (I)V
    //   257: iload_3
    //   258: ifeq -> 265
    //   261: aload_0
    //   262: invokestatic postInvalidateOnAnimation : (Landroid/view/View;)V
    //   265: return
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    Drawable drawable = this.mMarginDrawable;
    if (drawable != null && drawable.isStateful())
      drawable.setState(getDrawableState()); 
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getAction : ()I
    //   4: ifne -> 118
    //   7: aload_1
    //   8: invokevirtual getKeyCode : ()I
    //   11: istore_2
    //   12: iload_2
    //   13: bipush #21
    //   15: if_icmpeq -> 92
    //   18: iload_2
    //   19: bipush #22
    //   21: if_icmpeq -> 66
    //   24: iload_2
    //   25: bipush #61
    //   27: if_icmpeq -> 33
    //   30: goto -> 118
    //   33: aload_1
    //   34: invokevirtual hasNoModifiers : ()Z
    //   37: ifeq -> 49
    //   40: aload_0
    //   41: iconst_2
    //   42: invokevirtual arrowScroll : (I)Z
    //   45: istore_3
    //   46: goto -> 120
    //   49: aload_1
    //   50: iconst_1
    //   51: invokevirtual hasModifiers : (I)Z
    //   54: ifeq -> 118
    //   57: aload_0
    //   58: iconst_1
    //   59: invokevirtual arrowScroll : (I)Z
    //   62: istore_3
    //   63: goto -> 120
    //   66: aload_1
    //   67: iconst_2
    //   68: invokevirtual hasModifiers : (I)Z
    //   71: ifeq -> 82
    //   74: aload_0
    //   75: invokevirtual pageRight : ()Z
    //   78: istore_3
    //   79: goto -> 120
    //   82: aload_0
    //   83: bipush #66
    //   85: invokevirtual arrowScroll : (I)Z
    //   88: istore_3
    //   89: goto -> 120
    //   92: aload_1
    //   93: iconst_2
    //   94: invokevirtual hasModifiers : (I)Z
    //   97: ifeq -> 108
    //   100: aload_0
    //   101: invokevirtual pageLeft : ()Z
    //   104: istore_3
    //   105: goto -> 120
    //   108: aload_0
    //   109: bipush #17
    //   111: invokevirtual arrowScroll : (I)Z
    //   114: istore_3
    //   115: goto -> 120
    //   118: iconst_0
    //   119: istore_3
    //   120: iload_3
    //   121: ireturn
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return generateDefaultLayoutParams();
  }
  
  public PagerAdapter getAdapter() {
    return this.mAdapter;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2) {
    int i = paramInt2;
    if (this.mDrawingOrder == 2)
      i = paramInt1 - 1 - paramInt2; 
    return ((LayoutParams)((View)this.mDrawingOrderedChildren.get(i)).getLayoutParams()).childIndex;
  }
  
  public int getCurrentItem() {
    return this.mCurItem;
  }
  
  public int getOffscreenPageLimit() {
    return this.mOffscreenPageLimit;
  }
  
  public int getPageMargin() {
    return this.mPageMargin;
  }
  
  ItemInfo infoForAnyChild(View paramView) {
    while (true) {
      ViewParent viewParent = paramView.getParent();
      if (viewParent != this) {
        if (viewParent != null) {
          if (!(viewParent instanceof View))
            return null; 
          paramView = (View)viewParent;
          continue;
        } 
        continue;
      } 
      return infoForChild(paramView);
    } 
  }
  
  ItemInfo infoForChild(View paramView) {
    for (byte b = 0; b < this.mItems.size(); b++) {
      ItemInfo itemInfo = this.mItems.get(b);
      if (this.mAdapter.isViewFromObject(paramView, itemInfo.object))
        return itemInfo; 
    } 
    return null;
  }
  
  ItemInfo infoForPosition(int paramInt) {
    for (byte b = 0; b < this.mItems.size(); b++) {
      ItemInfo itemInfo = this.mItems.get(b);
      if (itemInfo.position == paramInt)
        return itemInfo; 
    } 
    return null;
  }
  
  void initViewPager() {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context context = getContext();
    this.mScroller = new Scroller(context, sInterpolator);
    ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
    float f = (context.getResources().getDisplayMetrics()).density;
    this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
    this.mMinimumVelocity = (int)(400.0F * f);
    this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
    this.mLeftEdge = new EdgeEffect(context);
    this.mRightEdge = new EdgeEffect(context);
    this.mFlingDistance = (int)(25.0F * f);
    this.mCloseEnough = (int)(2.0F * f);
    this.mDefaultGutterSize = (int)(f * 16.0F);
    ViewCompat.setAccessibilityDelegate((View)this, new MyAccessibilityDelegate());
    if (ViewCompat.getImportantForAccessibility((View)this) == 0)
      ViewCompat.setImportantForAccessibility((View)this, 1); 
    ViewCompat.setOnApplyWindowInsetsListener((View)this, new OnApplyWindowInsetsListener() {
          private final Rect mTempRect = new Rect();
          
          final ViewPager this$0;
          
          public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
            WindowInsetsCompat windowInsetsCompat = ViewCompat.onApplyWindowInsets(param1View, param1WindowInsetsCompat);
            if (windowInsetsCompat.isConsumed())
              return windowInsetsCompat; 
            Rect rect = this.mTempRect;
            rect.left = windowInsetsCompat.getSystemWindowInsetLeft();
            rect.top = windowInsetsCompat.getSystemWindowInsetTop();
            rect.right = windowInsetsCompat.getSystemWindowInsetRight();
            rect.bottom = windowInsetsCompat.getSystemWindowInsetBottom();
            byte b = 0;
            int i = ViewPager.this.getChildCount();
            while (b < i) {
              param1WindowInsetsCompat = ViewCompat.dispatchApplyWindowInsets(ViewPager.this.getChildAt(b), windowInsetsCompat);
              rect.left = Math.min(param1WindowInsetsCompat.getSystemWindowInsetLeft(), rect.left);
              rect.top = Math.min(param1WindowInsetsCompat.getSystemWindowInsetTop(), rect.top);
              rect.right = Math.min(param1WindowInsetsCompat.getSystemWindowInsetRight(), rect.right);
              rect.bottom = Math.min(param1WindowInsetsCompat.getSystemWindowInsetBottom(), rect.bottom);
              b++;
            } 
            return windowInsetsCompat.replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
          }
        });
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow() {
    removeCallbacks(this.mEndScrollRunnable);
    Scroller scroller = this.mScroller;
    if (scroller != null && !scroller.isFinished())
      this.mScroller.abortAnimation(); 
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
      int m = getScrollX();
      int n = getWidth();
      float f1 = this.mPageMargin;
      float f3 = n;
      float f2 = f1 / f3;
      ArrayList<ItemInfo> arrayList = this.mItems;
      byte b = 0;
      ItemInfo itemInfo = arrayList.get(0);
      f1 = itemInfo.offset;
      int k = this.mItems.size();
      int i = itemInfo.position;
      int j = ((ItemInfo)this.mItems.get(k - 1)).position;
      while (i < j) {
        float f;
        ItemInfo itemInfo1;
        while (i > itemInfo.position && b < k) {
          ArrayList<ItemInfo> arrayList1 = this.mItems;
          itemInfo1 = arrayList1.get(++b);
        } 
        if (i == itemInfo1.position) {
          float f4 = itemInfo1.offset;
          f1 = itemInfo1.widthFactor;
          f = (f4 + f1) * f3;
          f1 = f4 + f1 + f2;
        } else {
          float f4 = this.mAdapter.getPageWidth(i);
          f = (f1 + f4) * f3;
          f1 += f4 + f2;
        } 
        if (this.mPageMargin + f > m) {
          this.mMarginDrawable.setBounds(Math.round(f), this.mTopPageBounds, Math.round(this.mPageMargin + f), this.mBottomPageBounds);
          this.mMarginDrawable.draw(paramCanvas);
        } 
        if (f > (m + n))
          break; 
        i++;
      } 
    } 
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getAction() & 0xFF;
    if (i == 3 || i == 1) {
      resetTouch();
      return false;
    } 
    if (i != 0) {
      if (this.mIsBeingDragged)
        return true; 
      if (this.mIsUnableToDrag)
        return false; 
    } 
    if (i != 0) {
      if (i != 2) {
        if (i == 6)
          onSecondaryPointerUp(paramMotionEvent); 
      } else {
        i = this.mActivePointerId;
        if (i != -1) {
          i = paramMotionEvent.findPointerIndex(i);
          float f3 = paramMotionEvent.getX(i);
          float f5 = f3 - this.mLastMotionX;
          float f4 = Math.abs(f5);
          float f2 = paramMotionEvent.getY(i);
          float f1 = Math.abs(f2 - this.mInitialMotionY);
          i = f5 cmp 0.0F;
          if (i != 0 && !isGutterDrag(this.mLastMotionX, f5) && canScroll((View)this, false, (int)f5, (int)f3, (int)f2)) {
            this.mLastMotionX = f3;
            this.mLastMotionY = f2;
            this.mIsUnableToDrag = true;
            return false;
          } 
          if (f4 > this.mTouchSlop && f4 * 0.5F > f1) {
            this.mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            setScrollState(1);
            f1 = this.mInitialMotionX;
            f4 = this.mTouchSlop;
            if (i > 0) {
              f1 += f4;
            } else {
              f1 -= f4;
            } 
            this.mLastMotionX = f1;
            this.mLastMotionY = f2;
            setScrollingCacheEnabled(true);
          } else if (f1 > this.mTouchSlop) {
            this.mIsUnableToDrag = true;
          } 
          if (this.mIsBeingDragged && performDrag(f3))
            ViewCompat.postInvalidateOnAnimation((View)this); 
        } 
      } 
    } else {
      float f = paramMotionEvent.getX();
      this.mInitialMotionX = f;
      this.mLastMotionX = f;
      f = paramMotionEvent.getY();
      this.mInitialMotionY = f;
      this.mLastMotionY = f;
      this.mActivePointerId = paramMotionEvent.getPointerId(0);
      this.mIsUnableToDrag = false;
      this.mIsScrollStarted = true;
      this.mScroller.computeScrollOffset();
      if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
        this.mScroller.abortAnimation();
        this.mPopulatePending = false;
        populate();
        this.mIsBeingDragged = true;
        requestParentDisallowInterceptTouchEvent(true);
        setScrollState(1);
      } else {
        completeScroll(false);
        this.mIsBeingDragged = false;
      } 
    } 
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain(); 
    this.mVelocityTracker.addMovement(paramMotionEvent);
    return this.mIsBeingDragged;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int k = getChildCount();
    int m = paramInt3 - paramInt1;
    int n = paramInt4 - paramInt2;
    paramInt2 = getPaddingLeft();
    paramInt1 = getPaddingTop();
    int i = getPaddingRight();
    paramInt4 = getPaddingBottom();
    int i1 = getScrollX();
    int j = 0;
    byte b = 0;
    while (b < k) {
      View view = getChildAt(b);
      int i2 = paramInt2;
      int i5 = i;
      int i4 = paramInt1;
      int i3 = paramInt4;
      paramInt3 = j;
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        i2 = paramInt2;
        i5 = i;
        i4 = paramInt1;
        i3 = paramInt4;
        paramInt3 = j;
        if (layoutParams.isDecor) {
          i2 = layoutParams.gravity;
          paramInt3 = i2 & 0x7;
          i3 = i2 & 0x70;
          if (paramInt3 != 1) {
            if (paramInt3 != 3) {
              if (paramInt3 != 5) {
                paramInt3 = paramInt2;
                i2 = paramInt2;
              } else {
                paramInt3 = m - i - view.getMeasuredWidth();
                i += view.getMeasuredWidth();
                i2 = paramInt2;
              } 
            } else {
              i2 = view.getMeasuredWidth();
              paramInt3 = paramInt2;
              i2 += paramInt2;
            } 
          } else {
            paramInt3 = Math.max((m - view.getMeasuredWidth()) / 2, paramInt2);
            i2 = paramInt2;
          } 
          if (i3 != 16) {
            if (i3 != 48) {
              if (i3 != 80) {
                paramInt2 = paramInt1;
              } else {
                paramInt2 = n - paramInt4 - view.getMeasuredHeight();
                paramInt4 += view.getMeasuredHeight();
              } 
            } else {
              i3 = view.getMeasuredHeight();
              paramInt2 = paramInt1;
              paramInt1 = i3 + paramInt1;
            } 
          } else {
            paramInt2 = Math.max((n - view.getMeasuredHeight()) / 2, paramInt1);
          } 
          paramInt3 += i1;
          view.layout(paramInt3, paramInt2, view.getMeasuredWidth() + paramInt3, paramInt2 + view.getMeasuredHeight());
          paramInt3 = j + 1;
          i3 = paramInt4;
          i4 = paramInt1;
          i5 = i;
        } 
      } 
      b++;
      paramInt2 = i2;
      i = i5;
      paramInt1 = i4;
      paramInt4 = i3;
      j = paramInt3;
    } 
    for (paramInt3 = 0; paramInt3 < k; paramInt3++) {
      View view = getChildAt(paramInt3);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (!layoutParams.isDecor) {
          ItemInfo itemInfo = infoForChild(view);
          if (itemInfo != null) {
            float f = (m - paramInt2 - i);
            int i2 = (int)(itemInfo.offset * f) + paramInt2;
            if (layoutParams.needsMeasure) {
              layoutParams.needsMeasure = false;
              view.measure(View.MeasureSpec.makeMeasureSpec((int)(f * layoutParams.widthFactor), 1073741824), View.MeasureSpec.makeMeasureSpec(n - paramInt1 - paramInt4, 1073741824));
            } 
            view.layout(i2, paramInt1, view.getMeasuredWidth() + i2, view.getMeasuredHeight() + paramInt1);
          } 
        } 
      } 
    } 
    this.mTopPageBounds = paramInt1;
    this.mBottomPageBounds = n - paramInt4;
    this.mDecorChildCount = j;
    if (this.mFirstLayout)
      scrollToItem(this.mCurItem, false, 0, false); 
    this.mFirstLayout = false;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #11
    //   3: aload_0
    //   4: iconst_0
    //   5: iload_1
    //   6: invokestatic getDefaultSize : (II)I
    //   9: iconst_0
    //   10: iload_2
    //   11: invokestatic getDefaultSize : (II)I
    //   14: invokevirtual setMeasuredDimension : (II)V
    //   17: aload_0
    //   18: invokevirtual getMeasuredWidth : ()I
    //   21: istore_3
    //   22: aload_0
    //   23: iload_3
    //   24: bipush #10
    //   26: idiv
    //   27: aload_0
    //   28: getfield mDefaultGutterSize : I
    //   31: invokestatic min : (II)I
    //   34: putfield mGutterSize : I
    //   37: aload_0
    //   38: invokevirtual getPaddingLeft : ()I
    //   41: istore #4
    //   43: aload_0
    //   44: invokevirtual getPaddingRight : ()I
    //   47: istore_1
    //   48: aload_0
    //   49: invokevirtual getMeasuredHeight : ()I
    //   52: istore #6
    //   54: aload_0
    //   55: invokevirtual getPaddingTop : ()I
    //   58: istore #5
    //   60: aload_0
    //   61: invokevirtual getPaddingBottom : ()I
    //   64: istore_2
    //   65: aload_0
    //   66: invokevirtual getChildCount : ()I
    //   69: istore #12
    //   71: iload #6
    //   73: iload #5
    //   75: isub
    //   76: iload_2
    //   77: isub
    //   78: istore_2
    //   79: iload_3
    //   80: iload #4
    //   82: isub
    //   83: iload_1
    //   84: isub
    //   85: istore_1
    //   86: iconst_0
    //   87: istore #5
    //   89: iconst_1
    //   90: istore #8
    //   92: ldc_w 1073741824
    //   95: istore #10
    //   97: iload #5
    //   99: iload #12
    //   101: if_icmpge -> 431
    //   104: aload_0
    //   105: iload #5
    //   107: invokevirtual getChildAt : (I)Landroid/view/View;
    //   110: astore #14
    //   112: iload_2
    //   113: istore_3
    //   114: iload_1
    //   115: istore #4
    //   117: aload #14
    //   119: invokevirtual getVisibility : ()I
    //   122: bipush #8
    //   124: if_icmpeq -> 420
    //   127: aload #14
    //   129: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   132: checkcast androidx/viewpager/widget/ViewPager$LayoutParams
    //   135: astore #13
    //   137: iload_2
    //   138: istore_3
    //   139: iload_1
    //   140: istore #4
    //   142: aload #13
    //   144: ifnull -> 420
    //   147: iload_2
    //   148: istore_3
    //   149: iload_1
    //   150: istore #4
    //   152: aload #13
    //   154: getfield isDecor : Z
    //   157: ifeq -> 420
    //   160: aload #13
    //   162: getfield gravity : I
    //   165: istore #4
    //   167: iload #4
    //   169: bipush #7
    //   171: iand
    //   172: istore_3
    //   173: iload #4
    //   175: bipush #112
    //   177: iand
    //   178: istore #4
    //   180: iload #4
    //   182: bipush #48
    //   184: if_icmpeq -> 203
    //   187: iload #4
    //   189: bipush #80
    //   191: if_icmpne -> 197
    //   194: goto -> 203
    //   197: iconst_0
    //   198: istore #7
    //   200: goto -> 206
    //   203: iconst_1
    //   204: istore #7
    //   206: iload #8
    //   208: istore #6
    //   210: iload_3
    //   211: iconst_3
    //   212: if_icmpeq -> 230
    //   215: iload_3
    //   216: iconst_5
    //   217: if_icmpne -> 227
    //   220: iload #8
    //   222: istore #6
    //   224: goto -> 230
    //   227: iconst_0
    //   228: istore #6
    //   230: ldc_w -2147483648
    //   233: istore_3
    //   234: iload #7
    //   236: ifeq -> 247
    //   239: ldc_w 1073741824
    //   242: istore #4
    //   244: goto -> 269
    //   247: iload_3
    //   248: istore #4
    //   250: iload #6
    //   252: ifeq -> 269
    //   255: ldc_w 1073741824
    //   258: istore #8
    //   260: iload_3
    //   261: istore #4
    //   263: iload #8
    //   265: istore_3
    //   266: goto -> 273
    //   269: ldc_w -2147483648
    //   272: istore_3
    //   273: aload #13
    //   275: getfield width : I
    //   278: istore #8
    //   280: iload #8
    //   282: bipush #-2
    //   284: if_icmpeq -> 315
    //   287: iload #8
    //   289: iconst_m1
    //   290: if_icmpeq -> 300
    //   293: iload #8
    //   295: istore #4
    //   297: goto -> 303
    //   300: iload_1
    //   301: istore #4
    //   303: ldc_w 1073741824
    //   306: istore #9
    //   308: iload #4
    //   310: istore #8
    //   312: goto -> 322
    //   315: iload_1
    //   316: istore #8
    //   318: iload #4
    //   320: istore #9
    //   322: aload #13
    //   324: getfield height : I
    //   327: istore #4
    //   329: iload #4
    //   331: bipush #-2
    //   333: if_icmpeq -> 353
    //   336: iload #4
    //   338: iconst_m1
    //   339: if_icmpeq -> 348
    //   342: iload #4
    //   344: istore_3
    //   345: goto -> 362
    //   348: iload_2
    //   349: istore_3
    //   350: goto -> 362
    //   353: iload_2
    //   354: istore #4
    //   356: iload_3
    //   357: istore #10
    //   359: iload #4
    //   361: istore_3
    //   362: aload #14
    //   364: iload #8
    //   366: iload #9
    //   368: invokestatic makeMeasureSpec : (II)I
    //   371: iload_3
    //   372: iload #10
    //   374: invokestatic makeMeasureSpec : (II)I
    //   377: invokevirtual measure : (II)V
    //   380: iload #7
    //   382: ifeq -> 399
    //   385: iload_2
    //   386: aload #14
    //   388: invokevirtual getMeasuredHeight : ()I
    //   391: isub
    //   392: istore_3
    //   393: iload_1
    //   394: istore #4
    //   396: goto -> 420
    //   399: iload_2
    //   400: istore_3
    //   401: iload_1
    //   402: istore #4
    //   404: iload #6
    //   406: ifeq -> 420
    //   409: iload_1
    //   410: aload #14
    //   412: invokevirtual getMeasuredWidth : ()I
    //   415: isub
    //   416: istore #4
    //   418: iload_2
    //   419: istore_3
    //   420: iinc #5, 1
    //   423: iload_3
    //   424: istore_2
    //   425: iload #4
    //   427: istore_1
    //   428: goto -> 89
    //   431: iload_1
    //   432: ldc_w 1073741824
    //   435: invokestatic makeMeasureSpec : (II)I
    //   438: pop
    //   439: aload_0
    //   440: iload_2
    //   441: ldc_w 1073741824
    //   444: invokestatic makeMeasureSpec : (II)I
    //   447: putfield mChildHeightMeasureSpec : I
    //   450: aload_0
    //   451: iconst_1
    //   452: putfield mInLayout : Z
    //   455: aload_0
    //   456: invokevirtual populate : ()V
    //   459: aload_0
    //   460: iconst_0
    //   461: putfield mInLayout : Z
    //   464: aload_0
    //   465: invokevirtual getChildCount : ()I
    //   468: istore_3
    //   469: iload #11
    //   471: istore_2
    //   472: iload_2
    //   473: iload_3
    //   474: if_icmpge -> 547
    //   477: aload_0
    //   478: iload_2
    //   479: invokevirtual getChildAt : (I)Landroid/view/View;
    //   482: astore #13
    //   484: aload #13
    //   486: invokevirtual getVisibility : ()I
    //   489: bipush #8
    //   491: if_icmpeq -> 541
    //   494: aload #13
    //   496: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   499: checkcast androidx/viewpager/widget/ViewPager$LayoutParams
    //   502: astore #14
    //   504: aload #14
    //   506: ifnull -> 517
    //   509: aload #14
    //   511: getfield isDecor : Z
    //   514: ifne -> 541
    //   517: aload #13
    //   519: iload_1
    //   520: i2f
    //   521: aload #14
    //   523: getfield widthFactor : F
    //   526: fmul
    //   527: f2i
    //   528: ldc_w 1073741824
    //   531: invokestatic makeMeasureSpec : (II)I
    //   534: aload_0
    //   535: getfield mChildHeightMeasureSpec : I
    //   538: invokevirtual measure : (II)V
    //   541: iinc #2, 1
    //   544: goto -> 472
    //   547: return
  }
  
  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    int i = this.mDecorChildCount;
    boolean bool = false;
    if (i > 0) {
      int n = getScrollX();
      i = getPaddingLeft();
      int j = getPaddingRight();
      int m = getWidth();
      int k = getChildCount();
      for (byte b = 0; b < k; b++) {
        View view = getChildAt(b);
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.isDecor) {
          int i1 = layoutParams.gravity & 0x7;
          if (i1 != 1) {
            if (i1 != 3) {
              if (i1 != 5) {
                int i2 = i;
                i1 = i;
                i = i2;
              } else {
                i1 = m - j - view.getMeasuredWidth();
                j += view.getMeasuredWidth();
              } 
            } else {
              int i2 = view.getWidth() + i;
              i1 = i;
              i = i2;
            } 
          } else {
            i1 = Math.max((m - view.getMeasuredWidth()) / 2, i);
          } 
          i1 = i1 + n - view.getLeft();
          if (i1 != 0)
            view.offsetLeftAndRight(i1); 
        } 
      } 
    } 
    dispatchOnPageScrolled(paramInt1, paramFloat, paramInt2);
    if (this.mPageTransformer != null) {
      i = getScrollX();
      paramInt2 = getChildCount();
      for (paramInt1 = bool; paramInt1 < paramInt2; paramInt1++) {
        View view = getChildAt(paramInt1);
        if (!((LayoutParams)view.getLayoutParams()).isDecor) {
          paramFloat = (view.getLeft() - i) / getClientWidth();
          this.mPageTransformer.transformPage(view, paramFloat);
        } 
      } 
    } 
    this.mCalledSuper = true;
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect) {
    byte b;
    int i = getChildCount();
    int j = -1;
    if ((paramInt & 0x2) != 0) {
      j = i;
      i = 0;
      b = 1;
    } else {
      i--;
      b = -1;
    } 
    while (i != j) {
      View view = getChildAt(i);
      if (view.getVisibility() == 0) {
        ItemInfo itemInfo = infoForChild(view);
        if (itemInfo != null && itemInfo.position == this.mCurItem && view.requestFocus(paramInt, paramRect))
          return true; 
      } 
      i += b;
    } 
    return false;
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    PagerAdapter pagerAdapter = this.mAdapter;
    if (pagerAdapter != null) {
      pagerAdapter.restoreState(savedState.adapterState, savedState.loader);
      setCurrentItemInternal(savedState.position, false, true);
    } else {
      this.mRestoredCurItem = savedState.position;
      this.mRestoredAdapterState = savedState.adapterState;
      this.mRestoredClassLoader = savedState.loader;
    } 
  }
  
  public Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    savedState.position = this.mCurItem;
    PagerAdapter pagerAdapter = this.mAdapter;
    if (pagerAdapter != null)
      savedState.adapterState = pagerAdapter.saveState(); 
    return (Parcelable)savedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3) {
      paramInt2 = this.mPageMargin;
      recomputeScrollPosition(paramInt1, paramInt3, paramInt2, paramInt2);
    } 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mFakeDragging : Z
    //   4: ifeq -> 9
    //   7: iconst_1
    //   8: ireturn
    //   9: aload_1
    //   10: invokevirtual getAction : ()I
    //   13: istore #6
    //   15: iconst_0
    //   16: istore #9
    //   18: iload #6
    //   20: ifne -> 32
    //   23: aload_1
    //   24: invokevirtual getEdgeFlags : ()I
    //   27: ifeq -> 32
    //   30: iconst_0
    //   31: ireturn
    //   32: aload_0
    //   33: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   36: astore #10
    //   38: aload #10
    //   40: ifnull -> 613
    //   43: aload #10
    //   45: invokevirtual getCount : ()I
    //   48: ifne -> 54
    //   51: goto -> 613
    //   54: aload_0
    //   55: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   58: ifnonnull -> 68
    //   61: aload_0
    //   62: invokestatic obtain : ()Landroid/view/VelocityTracker;
    //   65: putfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   68: aload_0
    //   69: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   72: aload_1
    //   73: invokevirtual addMovement : (Landroid/view/MotionEvent;)V
    //   76: aload_1
    //   77: invokevirtual getAction : ()I
    //   80: sipush #255
    //   83: iand
    //   84: istore #6
    //   86: iload #6
    //   88: ifeq -> 547
    //   91: iload #6
    //   93: iconst_1
    //   94: if_icmpeq -> 407
    //   97: iload #6
    //   99: iconst_2
    //   100: if_icmpeq -> 205
    //   103: iload #6
    //   105: iconst_3
    //   106: if_icmpeq -> 178
    //   109: iload #6
    //   111: iconst_5
    //   112: if_icmpeq -> 149
    //   115: iload #6
    //   117: bipush #6
    //   119: if_icmpeq -> 125
    //   122: goto -> 602
    //   125: aload_0
    //   126: aload_1
    //   127: invokespecial onSecondaryPointerUp : (Landroid/view/MotionEvent;)V
    //   130: aload_0
    //   131: aload_1
    //   132: aload_1
    //   133: aload_0
    //   134: getfield mActivePointerId : I
    //   137: invokevirtual findPointerIndex : (I)I
    //   140: invokevirtual getX : (I)F
    //   143: putfield mLastMotionX : F
    //   146: goto -> 602
    //   149: aload_1
    //   150: invokevirtual getActionIndex : ()I
    //   153: istore #6
    //   155: aload_0
    //   156: aload_1
    //   157: iload #6
    //   159: invokevirtual getX : (I)F
    //   162: putfield mLastMotionX : F
    //   165: aload_0
    //   166: aload_1
    //   167: iload #6
    //   169: invokevirtual getPointerId : (I)I
    //   172: putfield mActivePointerId : I
    //   175: goto -> 602
    //   178: aload_0
    //   179: getfield mIsBeingDragged : Z
    //   182: ifeq -> 602
    //   185: aload_0
    //   186: aload_0
    //   187: getfield mCurItem : I
    //   190: iconst_1
    //   191: iconst_0
    //   192: iconst_0
    //   193: invokespecial scrollToItem : (IZIZ)V
    //   196: aload_0
    //   197: invokespecial resetTouch : ()Z
    //   200: istore #9
    //   202: goto -> 602
    //   205: aload_0
    //   206: getfield mIsBeingDragged : Z
    //   209: ifne -> 377
    //   212: aload_1
    //   213: aload_0
    //   214: getfield mActivePointerId : I
    //   217: invokevirtual findPointerIndex : (I)I
    //   220: istore #6
    //   222: iload #6
    //   224: iconst_m1
    //   225: if_icmpne -> 237
    //   228: aload_0
    //   229: invokespecial resetTouch : ()Z
    //   232: istore #9
    //   234: goto -> 602
    //   237: aload_1
    //   238: iload #6
    //   240: invokevirtual getX : (I)F
    //   243: fstore_2
    //   244: fload_2
    //   245: aload_0
    //   246: getfield mLastMotionX : F
    //   249: fsub
    //   250: invokestatic abs : (F)F
    //   253: fstore #5
    //   255: aload_1
    //   256: iload #6
    //   258: invokevirtual getY : (I)F
    //   261: fstore_3
    //   262: fload_3
    //   263: aload_0
    //   264: getfield mLastMotionY : F
    //   267: fsub
    //   268: invokestatic abs : (F)F
    //   271: fstore #4
    //   273: fload #5
    //   275: aload_0
    //   276: getfield mTouchSlop : I
    //   279: i2f
    //   280: fcmpl
    //   281: ifle -> 377
    //   284: fload #5
    //   286: fload #4
    //   288: fcmpl
    //   289: ifle -> 377
    //   292: aload_0
    //   293: iconst_1
    //   294: putfield mIsBeingDragged : Z
    //   297: aload_0
    //   298: iconst_1
    //   299: invokespecial requestParentDisallowInterceptTouchEvent : (Z)V
    //   302: aload_0
    //   303: getfield mInitialMotionX : F
    //   306: fstore #4
    //   308: fload_2
    //   309: fload #4
    //   311: fsub
    //   312: fconst_0
    //   313: fcmpl
    //   314: ifle -> 329
    //   317: fload #4
    //   319: aload_0
    //   320: getfield mTouchSlop : I
    //   323: i2f
    //   324: fadd
    //   325: fstore_2
    //   326: goto -> 338
    //   329: fload #4
    //   331: aload_0
    //   332: getfield mTouchSlop : I
    //   335: i2f
    //   336: fsub
    //   337: fstore_2
    //   338: aload_0
    //   339: fload_2
    //   340: putfield mLastMotionX : F
    //   343: aload_0
    //   344: fload_3
    //   345: putfield mLastMotionY : F
    //   348: aload_0
    //   349: iconst_1
    //   350: invokevirtual setScrollState : (I)V
    //   353: aload_0
    //   354: iconst_1
    //   355: invokespecial setScrollingCacheEnabled : (Z)V
    //   358: aload_0
    //   359: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   362: astore #10
    //   364: aload #10
    //   366: ifnull -> 377
    //   369: aload #10
    //   371: iconst_1
    //   372: invokeinterface requestDisallowInterceptTouchEvent : (Z)V
    //   377: aload_0
    //   378: getfield mIsBeingDragged : Z
    //   381: ifeq -> 602
    //   384: iconst_0
    //   385: aload_0
    //   386: aload_1
    //   387: aload_1
    //   388: aload_0
    //   389: getfield mActivePointerId : I
    //   392: invokevirtual findPointerIndex : (I)I
    //   395: invokevirtual getX : (I)F
    //   398: invokespecial performDrag : (F)Z
    //   401: ior
    //   402: istore #9
    //   404: goto -> 602
    //   407: aload_0
    //   408: getfield mIsBeingDragged : Z
    //   411: ifeq -> 602
    //   414: aload_0
    //   415: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   418: astore #10
    //   420: aload #10
    //   422: sipush #1000
    //   425: aload_0
    //   426: getfield mMaximumVelocity : I
    //   429: i2f
    //   430: invokevirtual computeCurrentVelocity : (IF)V
    //   433: aload #10
    //   435: aload_0
    //   436: getfield mActivePointerId : I
    //   439: invokevirtual getXVelocity : (I)F
    //   442: f2i
    //   443: istore #6
    //   445: aload_0
    //   446: iconst_1
    //   447: putfield mPopulatePending : Z
    //   450: aload_0
    //   451: invokespecial getClientWidth : ()I
    //   454: istore #8
    //   456: aload_0
    //   457: invokevirtual getScrollX : ()I
    //   460: istore #7
    //   462: aload_0
    //   463: invokespecial infoForCurrentScrollPosition : ()Landroidx/viewpager/widget/ViewPager$ItemInfo;
    //   466: astore #10
    //   468: aload_0
    //   469: getfield mPageMargin : I
    //   472: i2f
    //   473: fstore_3
    //   474: iload #8
    //   476: i2f
    //   477: fstore_2
    //   478: fload_3
    //   479: fload_2
    //   480: fdiv
    //   481: fstore_3
    //   482: aload_0
    //   483: aload_0
    //   484: aload #10
    //   486: getfield position : I
    //   489: iload #7
    //   491: i2f
    //   492: fload_2
    //   493: fdiv
    //   494: aload #10
    //   496: getfield offset : F
    //   499: fsub
    //   500: aload #10
    //   502: getfield widthFactor : F
    //   505: fload_3
    //   506: fadd
    //   507: fdiv
    //   508: iload #6
    //   510: aload_1
    //   511: aload_1
    //   512: aload_0
    //   513: getfield mActivePointerId : I
    //   516: invokevirtual findPointerIndex : (I)I
    //   519: invokevirtual getX : (I)F
    //   522: aload_0
    //   523: getfield mInitialMotionX : F
    //   526: fsub
    //   527: f2i
    //   528: invokespecial determineTargetPage : (IFII)I
    //   531: iconst_1
    //   532: iconst_1
    //   533: iload #6
    //   535: invokevirtual setCurrentItemInternal : (IZZI)V
    //   538: aload_0
    //   539: invokespecial resetTouch : ()Z
    //   542: istore #9
    //   544: goto -> 602
    //   547: aload_0
    //   548: getfield mScroller : Landroid/widget/Scroller;
    //   551: invokevirtual abortAnimation : ()V
    //   554: aload_0
    //   555: iconst_0
    //   556: putfield mPopulatePending : Z
    //   559: aload_0
    //   560: invokevirtual populate : ()V
    //   563: aload_1
    //   564: invokevirtual getX : ()F
    //   567: fstore_2
    //   568: aload_0
    //   569: fload_2
    //   570: putfield mInitialMotionX : F
    //   573: aload_0
    //   574: fload_2
    //   575: putfield mLastMotionX : F
    //   578: aload_1
    //   579: invokevirtual getY : ()F
    //   582: fstore_2
    //   583: aload_0
    //   584: fload_2
    //   585: putfield mInitialMotionY : F
    //   588: aload_0
    //   589: fload_2
    //   590: putfield mLastMotionY : F
    //   593: aload_0
    //   594: aload_1
    //   595: iconst_0
    //   596: invokevirtual getPointerId : (I)I
    //   599: putfield mActivePointerId : I
    //   602: iload #9
    //   604: ifeq -> 611
    //   607: aload_0
    //   608: invokestatic postInvalidateOnAnimation : (Landroid/view/View;)V
    //   611: iconst_1
    //   612: ireturn
    //   613: iconst_0
    //   614: ireturn
  }
  
  boolean pageLeft() {
    int i = this.mCurItem;
    if (i > 0) {
      setCurrentItem(i - 1, true);
      return true;
    } 
    return false;
  }
  
  boolean pageRight() {
    PagerAdapter pagerAdapter = this.mAdapter;
    if (pagerAdapter != null && this.mCurItem < pagerAdapter.getCount() - 1) {
      setCurrentItem(this.mCurItem + 1, true);
      return true;
    } 
    return false;
  }
  
  void populate() {
    populate(this.mCurItem);
  }
  
  void populate(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mCurItem : I
    //   4: istore #5
    //   6: iload #5
    //   8: iload_1
    //   9: if_icmpeq -> 28
    //   12: aload_0
    //   13: iload #5
    //   15: invokevirtual infoForPosition : (I)Landroidx/viewpager/widget/ViewPager$ItemInfo;
    //   18: astore #14
    //   20: aload_0
    //   21: iload_1
    //   22: putfield mCurItem : I
    //   25: goto -> 31
    //   28: aconst_null
    //   29: astore #14
    //   31: aload_0
    //   32: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   35: ifnonnull -> 43
    //   38: aload_0
    //   39: invokespecial sortChildDrawingOrder : ()V
    //   42: return
    //   43: aload_0
    //   44: getfield mPopulatePending : Z
    //   47: ifeq -> 55
    //   50: aload_0
    //   51: invokespecial sortChildDrawingOrder : ()V
    //   54: return
    //   55: aload_0
    //   56: invokevirtual getWindowToken : ()Landroid/os/IBinder;
    //   59: ifnonnull -> 63
    //   62: return
    //   63: aload_0
    //   64: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   67: aload_0
    //   68: invokevirtual startUpdate : (Landroid/view/ViewGroup;)V
    //   71: aload_0
    //   72: getfield mOffscreenPageLimit : I
    //   75: istore_1
    //   76: iconst_0
    //   77: aload_0
    //   78: getfield mCurItem : I
    //   81: iload_1
    //   82: isub
    //   83: invokestatic max : (II)I
    //   86: istore #11
    //   88: aload_0
    //   89: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   92: invokevirtual getCount : ()I
    //   95: istore #9
    //   97: iload #9
    //   99: iconst_1
    //   100: isub
    //   101: aload_0
    //   102: getfield mCurItem : I
    //   105: iload_1
    //   106: iadd
    //   107: invokestatic min : (II)I
    //   110: istore #10
    //   112: iload #9
    //   114: aload_0
    //   115: getfield mExpectedAdapterCount : I
    //   118: if_icmpne -> 1195
    //   121: iconst_0
    //   122: istore_1
    //   123: iload_1
    //   124: aload_0
    //   125: getfield mItems : Ljava/util/ArrayList;
    //   128: invokevirtual size : ()I
    //   131: if_icmpge -> 183
    //   134: aload_0
    //   135: getfield mItems : Ljava/util/ArrayList;
    //   138: iload_1
    //   139: invokevirtual get : (I)Ljava/lang/Object;
    //   142: checkcast androidx/viewpager/widget/ViewPager$ItemInfo
    //   145: astore #13
    //   147: aload #13
    //   149: getfield position : I
    //   152: istore #6
    //   154: aload_0
    //   155: getfield mCurItem : I
    //   158: istore #5
    //   160: iload #6
    //   162: iload #5
    //   164: if_icmplt -> 177
    //   167: iload #6
    //   169: iload #5
    //   171: if_icmpne -> 183
    //   174: goto -> 186
    //   177: iinc #1, 1
    //   180: goto -> 123
    //   183: aconst_null
    //   184: astore #13
    //   186: aload #13
    //   188: astore #15
    //   190: aload #13
    //   192: ifnonnull -> 215
    //   195: aload #13
    //   197: astore #15
    //   199: iload #9
    //   201: ifle -> 215
    //   204: aload_0
    //   205: aload_0
    //   206: getfield mCurItem : I
    //   209: iload_1
    //   210: invokevirtual addNewItem : (II)Landroidx/viewpager/widget/ViewPager$ItemInfo;
    //   213: astore #15
    //   215: aload #15
    //   217: ifnull -> 979
    //   220: iload_1
    //   221: iconst_1
    //   222: isub
    //   223: istore #5
    //   225: iload #5
    //   227: iflt -> 247
    //   230: aload_0
    //   231: getfield mItems : Ljava/util/ArrayList;
    //   234: iload #5
    //   236: invokevirtual get : (I)Ljava/lang/Object;
    //   239: checkcast androidx/viewpager/widget/ViewPager$ItemInfo
    //   242: astore #13
    //   244: goto -> 250
    //   247: aconst_null
    //   248: astore #13
    //   250: aload_0
    //   251: invokespecial getClientWidth : ()I
    //   254: istore #12
    //   256: iload #12
    //   258: ifgt -> 267
    //   261: fconst_0
    //   262: fstore #4
    //   264: goto -> 286
    //   267: fconst_2
    //   268: aload #15
    //   270: getfield widthFactor : F
    //   273: fsub
    //   274: aload_0
    //   275: invokevirtual getPaddingLeft : ()I
    //   278: i2f
    //   279: iload #12
    //   281: i2f
    //   282: fdiv
    //   283: fadd
    //   284: fstore #4
    //   286: aload_0
    //   287: getfield mCurItem : I
    //   290: iconst_1
    //   291: isub
    //   292: istore #8
    //   294: fconst_0
    //   295: fstore_3
    //   296: iload #8
    //   298: iflt -> 582
    //   301: fload_3
    //   302: fload #4
    //   304: fcmpl
    //   305: iflt -> 431
    //   308: iload #8
    //   310: iload #11
    //   312: if_icmpge -> 431
    //   315: aload #13
    //   317: ifnonnull -> 323
    //   320: goto -> 582
    //   323: fload_3
    //   324: fstore_2
    //   325: iload_1
    //   326: istore #7
    //   328: aload #13
    //   330: astore #16
    //   332: iload #5
    //   334: istore #6
    //   336: iload #8
    //   338: aload #13
    //   340: getfield position : I
    //   343: if_icmpne -> 563
    //   346: fload_3
    //   347: fstore_2
    //   348: iload_1
    //   349: istore #7
    //   351: aload #13
    //   353: astore #16
    //   355: iload #5
    //   357: istore #6
    //   359: aload #13
    //   361: getfield scrolling : Z
    //   364: ifne -> 563
    //   367: aload_0
    //   368: getfield mItems : Ljava/util/ArrayList;
    //   371: iload #5
    //   373: invokevirtual remove : (I)Ljava/lang/Object;
    //   376: pop
    //   377: aload_0
    //   378: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   381: aload_0
    //   382: iload #8
    //   384: aload #13
    //   386: getfield object : Ljava/lang/Object;
    //   389: invokevirtual destroyItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   392: iinc #5, -1
    //   395: iinc #1, -1
    //   398: fload_3
    //   399: fstore_2
    //   400: iload_1
    //   401: istore #6
    //   403: iload #5
    //   405: istore #7
    //   407: iload #5
    //   409: iflt -> 542
    //   412: aload_0
    //   413: getfield mItems : Ljava/util/ArrayList;
    //   416: iload #5
    //   418: invokevirtual get : (I)Ljava/lang/Object;
    //   421: checkcast androidx/viewpager/widget/ViewPager$ItemInfo
    //   424: astore #13
    //   426: fload_3
    //   427: fstore_2
    //   428: goto -> 552
    //   431: aload #13
    //   433: ifnull -> 490
    //   436: iload #8
    //   438: aload #13
    //   440: getfield position : I
    //   443: if_icmpne -> 490
    //   446: fload_3
    //   447: aload #13
    //   449: getfield widthFactor : F
    //   452: fadd
    //   453: fstore_3
    //   454: iinc #5, -1
    //   457: fload_3
    //   458: fstore_2
    //   459: iload_1
    //   460: istore #6
    //   462: iload #5
    //   464: istore #7
    //   466: iload #5
    //   468: iflt -> 542
    //   471: aload_0
    //   472: getfield mItems : Ljava/util/ArrayList;
    //   475: iload #5
    //   477: invokevirtual get : (I)Ljava/lang/Object;
    //   480: checkcast androidx/viewpager/widget/ViewPager$ItemInfo
    //   483: astore #13
    //   485: fload_3
    //   486: fstore_2
    //   487: goto -> 552
    //   490: fload_3
    //   491: aload_0
    //   492: iload #8
    //   494: iload #5
    //   496: iconst_1
    //   497: iadd
    //   498: invokevirtual addNewItem : (II)Landroidx/viewpager/widget/ViewPager$ItemInfo;
    //   501: getfield widthFactor : F
    //   504: fadd
    //   505: fstore_3
    //   506: iinc #1, 1
    //   509: fload_3
    //   510: fstore_2
    //   511: iload_1
    //   512: istore #6
    //   514: iload #5
    //   516: istore #7
    //   518: iload #5
    //   520: iflt -> 542
    //   523: aload_0
    //   524: getfield mItems : Ljava/util/ArrayList;
    //   527: iload #5
    //   529: invokevirtual get : (I)Ljava/lang/Object;
    //   532: checkcast androidx/viewpager/widget/ViewPager$ItemInfo
    //   535: astore #13
    //   537: fload_3
    //   538: fstore_2
    //   539: goto -> 552
    //   542: aconst_null
    //   543: astore #13
    //   545: iload #7
    //   547: istore #5
    //   549: iload #6
    //   551: istore_1
    //   552: iload #5
    //   554: istore #6
    //   556: aload #13
    //   558: astore #16
    //   560: iload_1
    //   561: istore #7
    //   563: iinc #8, -1
    //   566: fload_2
    //   567: fstore_3
    //   568: iload #7
    //   570: istore_1
    //   571: aload #16
    //   573: astore #13
    //   575: iload #6
    //   577: istore #5
    //   579: goto -> 296
    //   582: aload #15
    //   584: getfield widthFactor : F
    //   587: fstore_3
    //   588: iload_1
    //   589: iconst_1
    //   590: iadd
    //   591: istore #6
    //   593: fload_3
    //   594: fconst_2
    //   595: fcmpg
    //   596: ifge -> 953
    //   599: iload #6
    //   601: aload_0
    //   602: getfield mItems : Ljava/util/ArrayList;
    //   605: invokevirtual size : ()I
    //   608: if_icmpge -> 628
    //   611: aload_0
    //   612: getfield mItems : Ljava/util/ArrayList;
    //   615: iload #6
    //   617: invokevirtual get : (I)Ljava/lang/Object;
    //   620: checkcast androidx/viewpager/widget/ViewPager$ItemInfo
    //   623: astore #13
    //   625: goto -> 631
    //   628: aconst_null
    //   629: astore #13
    //   631: iload #12
    //   633: ifgt -> 642
    //   636: fconst_0
    //   637: fstore #4
    //   639: goto -> 655
    //   642: aload_0
    //   643: invokevirtual getPaddingRight : ()I
    //   646: i2f
    //   647: iload #12
    //   649: i2f
    //   650: fdiv
    //   651: fconst_2
    //   652: fadd
    //   653: fstore #4
    //   655: aload_0
    //   656: getfield mCurItem : I
    //   659: istore #5
    //   661: aload #13
    //   663: astore #16
    //   665: iload #5
    //   667: iconst_1
    //   668: iadd
    //   669: istore #7
    //   671: iload #7
    //   673: iload #9
    //   675: if_icmpge -> 953
    //   678: fload_3
    //   679: fload #4
    //   681: fcmpl
    //   682: iflt -> 810
    //   685: iload #7
    //   687: iload #10
    //   689: if_icmple -> 810
    //   692: aload #16
    //   694: ifnonnull -> 700
    //   697: goto -> 953
    //   700: fload_3
    //   701: fstore_2
    //   702: iload #6
    //   704: istore #5
    //   706: aload #16
    //   708: astore #13
    //   710: iload #7
    //   712: aload #16
    //   714: getfield position : I
    //   717: if_icmpne -> 936
    //   720: fload_3
    //   721: fstore_2
    //   722: iload #6
    //   724: istore #5
    //   726: aload #16
    //   728: astore #13
    //   730: aload #16
    //   732: getfield scrolling : Z
    //   735: ifne -> 936
    //   738: aload_0
    //   739: getfield mItems : Ljava/util/ArrayList;
    //   742: iload #6
    //   744: invokevirtual remove : (I)Ljava/lang/Object;
    //   747: pop
    //   748: aload_0
    //   749: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   752: aload_0
    //   753: iload #7
    //   755: aload #16
    //   757: getfield object : Ljava/lang/Object;
    //   760: invokevirtual destroyItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   763: fload_3
    //   764: fstore_2
    //   765: iload #6
    //   767: istore #5
    //   769: iload #6
    //   771: aload_0
    //   772: getfield mItems : Ljava/util/ArrayList;
    //   775: invokevirtual size : ()I
    //   778: if_icmpge -> 804
    //   781: aload_0
    //   782: getfield mItems : Ljava/util/ArrayList;
    //   785: iload #6
    //   787: invokevirtual get : (I)Ljava/lang/Object;
    //   790: checkcast androidx/viewpager/widget/ViewPager$ItemInfo
    //   793: astore #13
    //   795: fload_3
    //   796: fstore_2
    //   797: iload #6
    //   799: istore #5
    //   801: goto -> 936
    //   804: aconst_null
    //   805: astore #13
    //   807: goto -> 936
    //   810: aload #16
    //   812: ifnull -> 877
    //   815: iload #7
    //   817: aload #16
    //   819: getfield position : I
    //   822: if_icmpne -> 877
    //   825: fload_3
    //   826: aload #16
    //   828: getfield widthFactor : F
    //   831: fadd
    //   832: fstore_3
    //   833: iinc #6, 1
    //   836: fload_3
    //   837: fstore_2
    //   838: iload #6
    //   840: istore #5
    //   842: iload #6
    //   844: aload_0
    //   845: getfield mItems : Ljava/util/ArrayList;
    //   848: invokevirtual size : ()I
    //   851: if_icmpge -> 804
    //   854: aload_0
    //   855: getfield mItems : Ljava/util/ArrayList;
    //   858: iload #6
    //   860: invokevirtual get : (I)Ljava/lang/Object;
    //   863: checkcast androidx/viewpager/widget/ViewPager$ItemInfo
    //   866: astore #13
    //   868: fload_3
    //   869: fstore_2
    //   870: iload #6
    //   872: istore #5
    //   874: goto -> 936
    //   877: aload_0
    //   878: iload #7
    //   880: iload #6
    //   882: invokevirtual addNewItem : (II)Landroidx/viewpager/widget/ViewPager$ItemInfo;
    //   885: astore #13
    //   887: iinc #6, 1
    //   890: fload_3
    //   891: aload #13
    //   893: getfield widthFactor : F
    //   896: fadd
    //   897: fstore_3
    //   898: fload_3
    //   899: fstore_2
    //   900: iload #6
    //   902: istore #5
    //   904: iload #6
    //   906: aload_0
    //   907: getfield mItems : Ljava/util/ArrayList;
    //   910: invokevirtual size : ()I
    //   913: if_icmpge -> 804
    //   916: aload_0
    //   917: getfield mItems : Ljava/util/ArrayList;
    //   920: iload #6
    //   922: invokevirtual get : (I)Ljava/lang/Object;
    //   925: checkcast androidx/viewpager/widget/ViewPager$ItemInfo
    //   928: astore #13
    //   930: iload #6
    //   932: istore #5
    //   934: fload_3
    //   935: fstore_2
    //   936: fload_2
    //   937: fstore_3
    //   938: iload #5
    //   940: istore #6
    //   942: aload #13
    //   944: astore #16
    //   946: iload #7
    //   948: istore #5
    //   950: goto -> 665
    //   953: aload_0
    //   954: aload #15
    //   956: iload_1
    //   957: aload #14
    //   959: invokespecial calculatePageOffsets : (Landroidx/viewpager/widget/ViewPager$ItemInfo;ILandroidx/viewpager/widget/ViewPager$ItemInfo;)V
    //   962: aload_0
    //   963: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   966: aload_0
    //   967: aload_0
    //   968: getfield mCurItem : I
    //   971: aload #15
    //   973: getfield object : Ljava/lang/Object;
    //   976: invokevirtual setPrimaryItem : (Landroid/view/ViewGroup;ILjava/lang/Object;)V
    //   979: aload_0
    //   980: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   983: aload_0
    //   984: invokevirtual finishUpdate : (Landroid/view/ViewGroup;)V
    //   987: aload_0
    //   988: invokevirtual getChildCount : ()I
    //   991: istore #5
    //   993: iconst_0
    //   994: istore_1
    //   995: iload_1
    //   996: iload #5
    //   998: if_icmpge -> 1081
    //   1001: aload_0
    //   1002: iload_1
    //   1003: invokevirtual getChildAt : (I)Landroid/view/View;
    //   1006: astore #14
    //   1008: aload #14
    //   1010: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1013: checkcast androidx/viewpager/widget/ViewPager$LayoutParams
    //   1016: astore #13
    //   1018: aload #13
    //   1020: iload_1
    //   1021: putfield childIndex : I
    //   1024: aload #13
    //   1026: getfield isDecor : Z
    //   1029: ifne -> 1075
    //   1032: aload #13
    //   1034: getfield widthFactor : F
    //   1037: fconst_0
    //   1038: fcmpl
    //   1039: ifne -> 1075
    //   1042: aload_0
    //   1043: aload #14
    //   1045: invokevirtual infoForChild : (Landroid/view/View;)Landroidx/viewpager/widget/ViewPager$ItemInfo;
    //   1048: astore #14
    //   1050: aload #14
    //   1052: ifnull -> 1075
    //   1055: aload #13
    //   1057: aload #14
    //   1059: getfield widthFactor : F
    //   1062: putfield widthFactor : F
    //   1065: aload #13
    //   1067: aload #14
    //   1069: getfield position : I
    //   1072: putfield position : I
    //   1075: iinc #1, 1
    //   1078: goto -> 995
    //   1081: aload_0
    //   1082: invokespecial sortChildDrawingOrder : ()V
    //   1085: aload_0
    //   1086: invokevirtual hasFocus : ()Z
    //   1089: ifeq -> 1194
    //   1092: aload_0
    //   1093: invokevirtual findFocus : ()Landroid/view/View;
    //   1096: astore #13
    //   1098: aload #13
    //   1100: ifnull -> 1114
    //   1103: aload_0
    //   1104: aload #13
    //   1106: invokevirtual infoForAnyChild : (Landroid/view/View;)Landroidx/viewpager/widget/ViewPager$ItemInfo;
    //   1109: astore #13
    //   1111: goto -> 1117
    //   1114: aconst_null
    //   1115: astore #13
    //   1117: aload #13
    //   1119: ifnull -> 1134
    //   1122: aload #13
    //   1124: getfield position : I
    //   1127: aload_0
    //   1128: getfield mCurItem : I
    //   1131: if_icmpeq -> 1194
    //   1134: iconst_0
    //   1135: istore_1
    //   1136: iload_1
    //   1137: aload_0
    //   1138: invokevirtual getChildCount : ()I
    //   1141: if_icmpge -> 1194
    //   1144: aload_0
    //   1145: iload_1
    //   1146: invokevirtual getChildAt : (I)Landroid/view/View;
    //   1149: astore #13
    //   1151: aload_0
    //   1152: aload #13
    //   1154: invokevirtual infoForChild : (Landroid/view/View;)Landroidx/viewpager/widget/ViewPager$ItemInfo;
    //   1157: astore #14
    //   1159: aload #14
    //   1161: ifnull -> 1188
    //   1164: aload #14
    //   1166: getfield position : I
    //   1169: aload_0
    //   1170: getfield mCurItem : I
    //   1173: if_icmpne -> 1188
    //   1176: aload #13
    //   1178: iconst_2
    //   1179: invokevirtual requestFocus : (I)Z
    //   1182: ifeq -> 1188
    //   1185: goto -> 1194
    //   1188: iinc #1, 1
    //   1191: goto -> 1136
    //   1194: return
    //   1195: aload_0
    //   1196: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   1199: aload_0
    //   1200: invokevirtual getId : ()I
    //   1203: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   1206: astore #13
    //   1208: goto -> 1222
    //   1211: astore #13
    //   1213: aload_0
    //   1214: invokevirtual getId : ()I
    //   1217: invokestatic toHexString : (I)Ljava/lang/String;
    //   1220: astore #13
    //   1222: new java/lang/StringBuilder
    //   1225: dup
    //   1226: invokespecial <init> : ()V
    //   1229: astore #14
    //   1231: aload #14
    //   1233: ldc_w 'The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: '
    //   1236: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1239: pop
    //   1240: aload #14
    //   1242: aload_0
    //   1243: getfield mExpectedAdapterCount : I
    //   1246: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1249: pop
    //   1250: aload #14
    //   1252: ldc_w ', found: '
    //   1255: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1258: pop
    //   1259: aload #14
    //   1261: iload #9
    //   1263: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1266: pop
    //   1267: aload #14
    //   1269: ldc_w ' Pager id: '
    //   1272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1275: pop
    //   1276: aload #14
    //   1278: aload #13
    //   1280: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1283: pop
    //   1284: aload #14
    //   1286: ldc_w ' Pager class: '
    //   1289: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1292: pop
    //   1293: aload #14
    //   1295: ldc androidx/viewpager/widget/ViewPager
    //   1297: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1300: pop
    //   1301: aload #14
    //   1303: ldc_w ' Problematic adapter: '
    //   1306: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1309: pop
    //   1310: aload #14
    //   1312: aload_0
    //   1313: getfield mAdapter : Landroidx/viewpager/widget/PagerAdapter;
    //   1316: invokevirtual getClass : ()Ljava/lang/Class;
    //   1319: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1322: pop
    //   1323: new java/lang/IllegalStateException
    //   1326: dup
    //   1327: aload #14
    //   1329: invokevirtual toString : ()Ljava/lang/String;
    //   1332: invokespecial <init> : (Ljava/lang/String;)V
    //   1335: athrow
    // Exception table:
    //   from	to	target	type
    //   1195	1208	1211	android/content/res/Resources$NotFoundException
  }
  
  public void removeOnAdapterChangeListener(OnAdapterChangeListener paramOnAdapterChangeListener) {
    List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
    if (list != null)
      list.remove(paramOnAdapterChangeListener); 
  }
  
  public void removeView(View paramView) {
    if (this.mInLayout) {
      removeViewInLayout(paramView);
    } else {
      super.removeView(paramView);
    } 
  }
  
  public void setAdapter(PagerAdapter paramPagerAdapter) {
    PagerAdapter pagerAdapter = this.mAdapter;
    byte b = 0;
    if (pagerAdapter != null) {
      pagerAdapter.setViewPagerObserver(null);
      this.mAdapter.startUpdate(this);
      for (byte b1 = 0; b1 < this.mItems.size(); b1++) {
        ItemInfo itemInfo = this.mItems.get(b1);
        this.mAdapter.destroyItem(this, itemInfo.position, itemInfo.object);
      } 
      this.mAdapter.finishUpdate(this);
      this.mItems.clear();
      removeNonDecorViews();
      this.mCurItem = 0;
      scrollTo(0, 0);
    } 
    pagerAdapter = this.mAdapter;
    this.mAdapter = paramPagerAdapter;
    this.mExpectedAdapterCount = 0;
    if (this.mAdapter != null) {
      if (this.mObserver == null)
        this.mObserver = new PagerObserver(); 
      this.mAdapter.setViewPagerObserver(this.mObserver);
      this.mPopulatePending = false;
      boolean bool = this.mFirstLayout;
      this.mFirstLayout = true;
      this.mExpectedAdapterCount = this.mAdapter.getCount();
      if (this.mRestoredCurItem >= 0) {
        this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
        setCurrentItemInternal(this.mRestoredCurItem, false, true);
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
      } else if (!bool) {
        populate();
      } else {
        requestLayout();
      } 
    } 
    List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
    if (list != null && !list.isEmpty()) {
      int i = this.mAdapterChangeListeners.size();
      for (byte b1 = b; b1 < i; b1++)
        ((OnAdapterChangeListener)this.mAdapterChangeListeners.get(b1)).onAdapterChanged(this, pagerAdapter, paramPagerAdapter); 
    } 
  }
  
  public void setCurrentItem(int paramInt) {
    this.mPopulatePending = false;
    setCurrentItemInternal(paramInt, this.mFirstLayout ^ true, false);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean) {
    this.mPopulatePending = false;
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }
  
  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }
  
  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) {
    int i;
    PagerAdapter pagerAdapter = this.mAdapter;
    if (pagerAdapter == null || pagerAdapter.getCount() <= 0) {
      setScrollingCacheEnabled(false);
      return;
    } 
    if (!paramBoolean2 && this.mCurItem == paramInt1 && this.mItems.size() != 0) {
      setScrollingCacheEnabled(false);
      return;
    } 
    paramBoolean2 = true;
    if (paramInt1 < 0) {
      i = 0;
    } else {
      i = paramInt1;
      if (paramInt1 >= this.mAdapter.getCount())
        i = this.mAdapter.getCount() - 1; 
    } 
    int j = this.mOffscreenPageLimit;
    paramInt1 = this.mCurItem;
    if (i > paramInt1 + j || i < paramInt1 - j)
      for (paramInt1 = 0; paramInt1 < this.mItems.size(); paramInt1++)
        ((ItemInfo)this.mItems.get(paramInt1)).scrolling = true;  
    if (this.mCurItem == i)
      paramBoolean2 = false; 
    if (this.mFirstLayout) {
      this.mCurItem = i;
      if (paramBoolean2)
        dispatchOnPageSelected(i); 
      requestLayout();
    } else {
      populate(i);
      scrollToItem(i, paramBoolean1, paramInt2, paramBoolean2);
    } 
  }
  
  OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
    OnPageChangeListener onPageChangeListener = this.mInternalPageChangeListener;
    this.mInternalPageChangeListener = paramOnPageChangeListener;
    return onPageChangeListener;
  }
  
  public void setOffscreenPageLimit(int paramInt) {
    int i = paramInt;
    if (paramInt < 1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Requested offscreen page limit ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" too small; defaulting to ");
      stringBuilder.append(1);
      stringBuilder.toString();
      i = 1;
    } 
    if (i != this.mOffscreenPageLimit) {
      this.mOffscreenPageLimit = i;
      populate();
    } 
  }
  
  @Deprecated
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener) {
    this.mOnPageChangeListener = paramOnPageChangeListener;
  }
  
  public void setPageMargin(int paramInt) {
    int i = this.mPageMargin;
    this.mPageMargin = paramInt;
    int j = getWidth();
    recomputeScrollPosition(j, j, paramInt, i);
    requestLayout();
  }
  
  public void setPageMarginDrawable(int paramInt) {
    setPageMarginDrawable(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setPageMarginDrawable(Drawable paramDrawable) {
    boolean bool;
    this.mMarginDrawable = paramDrawable;
    if (paramDrawable != null)
      refreshDrawableState(); 
    if (paramDrawable == null) {
      bool = true;
    } else {
      bool = false;
    } 
    setWillNotDraw(bool);
    invalidate();
  }
  
  void setScrollState(int paramInt) {
    if (this.mScrollState == paramInt)
      return; 
    this.mScrollState = paramInt;
    if (this.mPageTransformer != null) {
      boolean bool;
      if (paramInt != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      enableLayers(bool);
    } 
    dispatchOnScrollStateChanged(paramInt);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3) {
    int i;
    if (getChildCount() == 0) {
      setScrollingCacheEnabled(false);
      return;
    } 
    Scroller scroller = this.mScroller;
    if (scroller != null && !scroller.isFinished()) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i) {
      if (this.mIsScrollStarted) {
        i = this.mScroller.getCurrX();
      } else {
        i = this.mScroller.getStartX();
      } 
      this.mScroller.abortAnimation();
      setScrollingCacheEnabled(false);
    } else {
      i = getScrollX();
    } 
    int j = getScrollY();
    int k = paramInt1 - i;
    paramInt2 -= j;
    if (k == 0 && paramInt2 == 0) {
      completeScroll(false);
      populate();
      setScrollState(0);
      return;
    } 
    setScrollingCacheEnabled(true);
    setScrollState(2);
    int m = getClientWidth();
    paramInt1 = m / 2;
    float f2 = Math.abs(k);
    float f1 = m;
    float f3 = Math.min(1.0F, f2 * 1.0F / f1);
    f2 = paramInt1;
    f3 = distanceInfluenceForSnapDuration(f3);
    paramInt1 = Math.abs(paramInt3);
    if (paramInt1 > 0) {
      paramInt1 = Math.round(Math.abs((f2 + f3 * f2) / paramInt1) * 1000.0F) * 4;
    } else {
      f2 = this.mAdapter.getPageWidth(this.mCurItem);
      paramInt1 = (int)((Math.abs(k) / (f1 * f2 + this.mPageMargin) + 1.0F) * 100.0F);
    } 
    paramInt1 = Math.min(paramInt1, 600);
    this.mIsScrollStarted = false;
    this.mScroller.startScroll(i, j, k, paramInt2, paramInt1);
    ViewCompat.postInvalidateOnAnimation((View)this);
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable) {
    return (super.verifyDrawable(paramDrawable) || paramDrawable == this.mMarginDrawable);
  }
  
  @Inherited
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE})
  public static @interface DecorView {}
  
  static class ItemInfo {
    Object object;
    
    float offset;
    
    int position;
    
    boolean scrolling;
    
    float widthFactor;
  }
  
  public static class LayoutParams extends ViewGroup.LayoutParams {
    int childIndex;
    
    public int gravity;
    
    public boolean isDecor;
    
    boolean needsMeasure;
    
    int position;
    
    float widthFactor = 0.0F;
    
    public LayoutParams() {
      super(-1, -1);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, ViewPager.LAYOUT_ATTRS);
      this.gravity = typedArray.getInteger(0, 48);
      typedArray.recycle();
    }
  }
  
  class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
    final ViewPager this$0;
    
    private boolean canScroll() {
      PagerAdapter pagerAdapter = ViewPager.this.mAdapter;
      boolean bool = true;
      if (pagerAdapter == null || pagerAdapter.getCount() <= 1)
        bool = false; 
      return bool;
    }
    
    public void onInitializeAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      super.onInitializeAccessibilityEvent(param1View, param1AccessibilityEvent);
      param1AccessibilityEvent.setClassName(ViewPager.class.getName());
      param1AccessibilityEvent.setScrollable(canScroll());
      if (param1AccessibilityEvent.getEventType() == 4096) {
        PagerAdapter pagerAdapter = ViewPager.this.mAdapter;
        if (pagerAdapter != null) {
          param1AccessibilityEvent.setItemCount(pagerAdapter.getCount());
          param1AccessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
          param1AccessibilityEvent.setToIndex(ViewPager.this.mCurItem);
        } 
      } 
    }
    
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
      param1AccessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
      param1AccessibilityNodeInfoCompat.setScrollable(canScroll());
      if (ViewPager.this.canScrollHorizontally(1))
        param1AccessibilityNodeInfoCompat.addAction(4096); 
      if (ViewPager.this.canScrollHorizontally(-1))
        param1AccessibilityNodeInfoCompat.addAction(8192); 
    }
    
    public boolean performAccessibilityAction(View param1View, int param1Int, Bundle param1Bundle) {
      if (super.performAccessibilityAction(param1View, param1Int, param1Bundle))
        return true; 
      if (param1Int != 4096) {
        if (param1Int != 8192)
          return false; 
        if (ViewPager.this.canScrollHorizontally(-1)) {
          ViewPager viewPager = ViewPager.this;
          viewPager.setCurrentItem(viewPager.mCurItem - 1);
          return true;
        } 
        return false;
      } 
      if (ViewPager.this.canScrollHorizontally(1)) {
        ViewPager viewPager = ViewPager.this;
        viewPager.setCurrentItem(viewPager.mCurItem + 1);
        return true;
      } 
      return false;
    }
  }
  
  public static interface OnAdapterChangeListener {
    void onAdapterChanged(ViewPager param1ViewPager, PagerAdapter param1PagerAdapter1, PagerAdapter param1PagerAdapter2);
  }
  
  public static interface OnPageChangeListener {
    void onPageScrollStateChanged(int param1Int);
    
    void onPageScrolled(int param1Int1, float param1Float, int param1Int2);
    
    void onPageSelected(int param1Int);
  }
  
  public static interface PageTransformer {
    void transformPage(View param1View, float param1Float);
  }
  
  private class PagerObserver extends DataSetObserver {
    final ViewPager this$0;
    
    public void onChanged() {
      ViewPager.this.dataSetChanged();
    }
    
    public void onInvalidated() {
      ViewPager.this.dataSetChanged();
    }
  }
  
  public static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public ViewPager.SavedState createFromParcel(Parcel param2Parcel) {
          return new ViewPager.SavedState(param2Parcel, null);
        }
        
        public ViewPager.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new ViewPager.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public ViewPager.SavedState[] newArray(int param2Int) {
          return new ViewPager.SavedState[param2Int];
        }
      };
    
    Parcelable adapterState;
    
    ClassLoader loader;
    
    int position;
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      ClassLoader classLoader = param1ClassLoader;
      if (param1ClassLoader == null)
        classLoader = SavedState.class.getClassLoader(); 
      this.position = param1Parcel.readInt();
      this.adapterState = param1Parcel.readParcelable(classLoader);
      this.loader = classLoader;
    }
    
    public SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("FragmentPager.SavedState{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" position=");
      stringBuilder.append(this.position);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.position);
      param1Parcel.writeParcelable(this.adapterState, param1Int);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public ViewPager.SavedState createFromParcel(Parcel param1Parcel) {
      return new ViewPager.SavedState(param1Parcel, null);
    }
    
    public ViewPager.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new ViewPager.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public ViewPager.SavedState[] newArray(int param1Int) {
      return new ViewPager.SavedState[param1Int];
    }
  }
  
  static class ViewPositionComparator implements Comparator<View> {
    public int compare(View param1View1, View param1View2) {
      ViewPager.LayoutParams layoutParams1 = (ViewPager.LayoutParams)param1View1.getLayoutParams();
      ViewPager.LayoutParams layoutParams2 = (ViewPager.LayoutParams)param1View2.getLayoutParams();
      boolean bool = layoutParams1.isDecor;
      if (bool != layoutParams2.isDecor) {
        byte b;
        if (bool) {
          b = 1;
        } else {
          b = -1;
        } 
        return b;
      } 
      return layoutParams1.position - layoutParams2.position;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/viewpager/widget/ViewPager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */