package androidx.customview.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

public class ViewDragHelper {
  private static final Interpolator sInterpolator = new Interpolator() {
      public float getInterpolation(float param1Float) {
        param1Float--;
        return param1Float * param1Float * param1Float * param1Float * param1Float + 1.0F;
      }
    };
  
  private int mActivePointerId = -1;
  
  private final Callback mCallback;
  
  private View mCapturedView;
  
  private int mDragState;
  
  private int[] mEdgeDragsInProgress;
  
  private int[] mEdgeDragsLocked;
  
  private int mEdgeSize;
  
  private int[] mInitialEdgesTouched;
  
  private float[] mInitialMotionX;
  
  private float[] mInitialMotionY;
  
  private float[] mLastMotionX;
  
  private float[] mLastMotionY;
  
  private float mMaxVelocity;
  
  private float mMinVelocity;
  
  private final ViewGroup mParentView;
  
  private int mPointersDown;
  
  private boolean mReleaseInProgress;
  
  private OverScroller mScroller;
  
  private final Runnable mSetIdleRunnable = new Runnable() {
      final ViewDragHelper this$0;
      
      public void run() {
        ViewDragHelper.this.setDragState(0);
      }
    };
  
  private int mTouchSlop;
  
  private int mTrackingEdges;
  
  private VelocityTracker mVelocityTracker;
  
  private ViewDragHelper(Context paramContext, ViewGroup paramViewGroup, Callback paramCallback) {
    if (paramViewGroup != null) {
      if (paramCallback != null) {
        this.mParentView = paramViewGroup;
        this.mCallback = paramCallback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(paramContext);
        this.mEdgeSize = (int)((paramContext.getResources().getDisplayMetrics()).density * 20.0F + 0.5F);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMaxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mScroller = new OverScroller(paramContext, sInterpolator);
        return;
      } 
      throw new IllegalArgumentException("Callback may not be null");
    } 
    throw new IllegalArgumentException("Parent view may not be null");
  }
  
  private boolean checkNewEdgeDrag(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2) {
    paramFloat1 = Math.abs(paramFloat1);
    paramFloat2 = Math.abs(paramFloat2);
    int i = this.mInitialEdgesTouched[paramInt1];
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((i & paramInt2) == paramInt2) {
      bool1 = bool2;
      if ((this.mTrackingEdges & paramInt2) != 0) {
        bool1 = bool2;
        if ((this.mEdgeDragsLocked[paramInt1] & paramInt2) != paramInt2) {
          bool1 = bool2;
          if ((this.mEdgeDragsInProgress[paramInt1] & paramInt2) != paramInt2) {
            i = this.mTouchSlop;
            if (paramFloat1 <= i && paramFloat2 <= i) {
              bool1 = bool2;
            } else {
              if (paramFloat1 < paramFloat2 * 0.5F && this.mCallback.onEdgeLock(paramInt2)) {
                int[] arrayOfInt = this.mEdgeDragsLocked;
                arrayOfInt[paramInt1] = arrayOfInt[paramInt1] | paramInt2;
                return false;
              } 
              bool1 = bool2;
              if ((this.mEdgeDragsInProgress[paramInt1] & paramInt2) == 0) {
                bool1 = bool2;
                if (paramFloat1 > this.mTouchSlop)
                  bool1 = true; 
              } 
            } 
          } 
        } 
      } 
    } 
    return bool1;
  }
  
  private boolean checkTouchSlop(View paramView, float paramFloat1, float paramFloat2) {
    int i;
    boolean bool1;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    if (paramView == null)
      return false; 
    if (this.mCallback.getViewHorizontalDragRange(paramView) > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (this.mCallback.getViewVerticalDragRange(paramView) > 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (i && bool1) {
      i = this.mTouchSlop;
      bool2 = bool4;
      if (paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2 > (i * i))
        bool2 = true; 
      return bool2;
    } 
    if (i != 0) {
      if (Math.abs(paramFloat1) > this.mTouchSlop)
        bool2 = true; 
      return bool2;
    } 
    bool2 = bool3;
    if (bool1) {
      bool2 = bool3;
      if (Math.abs(paramFloat2) > this.mTouchSlop)
        bool2 = true; 
    } 
    return bool2;
  }
  
  private float clampMag(float paramFloat1, float paramFloat2, float paramFloat3) {
    float f = Math.abs(paramFloat1);
    if (f < paramFloat2)
      return 0.0F; 
    if (f > paramFloat3) {
      if (paramFloat1 <= 0.0F)
        paramFloat3 = -paramFloat3; 
      return paramFloat3;
    } 
    return paramFloat1;
  }
  
  private int clampMag(int paramInt1, int paramInt2, int paramInt3) {
    int i = Math.abs(paramInt1);
    if (i < paramInt2)
      return 0; 
    if (i > paramInt3) {
      if (paramInt1 <= 0)
        paramInt3 = -paramInt3; 
      return paramInt3;
    } 
    return paramInt1;
  }
  
  private void clearMotionHistory() {
    float[] arrayOfFloat = this.mInitialMotionX;
    if (arrayOfFloat == null)
      return; 
    Arrays.fill(arrayOfFloat, 0.0F);
    Arrays.fill(this.mInitialMotionY, 0.0F);
    Arrays.fill(this.mLastMotionX, 0.0F);
    Arrays.fill(this.mLastMotionY, 0.0F);
    Arrays.fill(this.mInitialEdgesTouched, 0);
    Arrays.fill(this.mEdgeDragsInProgress, 0);
    Arrays.fill(this.mEdgeDragsLocked, 0);
    this.mPointersDown = 0;
  }
  
  private void clearMotionHistory(int paramInt) {
    if (this.mInitialMotionX != null && isPointerDown(paramInt)) {
      this.mInitialMotionX[paramInt] = 0.0F;
      this.mInitialMotionY[paramInt] = 0.0F;
      this.mLastMotionX[paramInt] = 0.0F;
      this.mLastMotionY[paramInt] = 0.0F;
      this.mInitialEdgesTouched[paramInt] = 0;
      this.mEdgeDragsInProgress[paramInt] = 0;
      this.mEdgeDragsLocked[paramInt] = 0;
      this.mPointersDown = (1 << paramInt ^ 0xFFFFFFFF) & this.mPointersDown;
    } 
  }
  
  private int computeAxisDuration(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 == 0)
      return 0; 
    int j = this.mParentView.getWidth();
    int i = j / 2;
    float f2 = Math.min(1.0F, Math.abs(paramInt1) / j);
    float f1 = i;
    f2 = distanceInfluenceForSnapDuration(f2);
    paramInt2 = Math.abs(paramInt2);
    if (paramInt2 > 0) {
      paramInt1 = Math.round(Math.abs((f1 + f2 * f1) / paramInt2) * 1000.0F) * 4;
    } else {
      paramInt1 = (int)((Math.abs(paramInt1) / paramInt3 + 1.0F) * 256.0F);
    } 
    return Math.min(paramInt1, 600);
  }
  
  private int computeSettleDuration(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    float f1;
    float f2;
    paramInt3 = clampMag(paramInt3, (int)this.mMinVelocity, (int)this.mMaxVelocity);
    paramInt4 = clampMag(paramInt4, (int)this.mMinVelocity, (int)this.mMaxVelocity);
    int k = Math.abs(paramInt1);
    int m = Math.abs(paramInt2);
    int j = Math.abs(paramInt3);
    int i = Math.abs(paramInt4);
    int n = j + i;
    int i1 = k + m;
    if (paramInt3 != 0) {
      f2 = j;
      f1 = n;
    } else {
      f2 = k;
      f1 = i1;
    } 
    float f3 = f2 / f1;
    if (paramInt4 != 0) {
      f1 = i;
      f2 = n;
    } else {
      f1 = m;
      f2 = i1;
    } 
    f1 /= f2;
    paramInt1 = computeAxisDuration(paramInt1, paramInt3, this.mCallback.getViewHorizontalDragRange(paramView));
    paramInt2 = computeAxisDuration(paramInt2, paramInt4, this.mCallback.getViewVerticalDragRange(paramView));
    return (int)(paramInt1 * f3 + paramInt2 * f1);
  }
  
  public static ViewDragHelper create(ViewGroup paramViewGroup, float paramFloat, Callback paramCallback) {
    ViewDragHelper viewDragHelper = create(paramViewGroup, paramCallback);
    viewDragHelper.mTouchSlop = (int)(viewDragHelper.mTouchSlop * 1.0F / paramFloat);
    return viewDragHelper;
  }
  
  public static ViewDragHelper create(ViewGroup paramViewGroup, Callback paramCallback) {
    return new ViewDragHelper(paramViewGroup.getContext(), paramViewGroup, paramCallback);
  }
  
  private void dispatchViewReleased(float paramFloat1, float paramFloat2) {
    this.mReleaseInProgress = true;
    this.mCallback.onViewReleased(this.mCapturedView, paramFloat1, paramFloat2);
    this.mReleaseInProgress = false;
    if (this.mDragState == 1)
      setDragState(0); 
  }
  
  private float distanceInfluenceForSnapDuration(float paramFloat) {
    return (float)Math.sin(((paramFloat - 0.5F) * 0.47123894F));
  }
  
  private void dragTo(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int k = this.mCapturedView.getLeft();
    int j = this.mCapturedView.getTop();
    int i = paramInt1;
    if (paramInt3 != 0) {
      i = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, paramInt1, paramInt3);
      ViewCompat.offsetLeftAndRight(this.mCapturedView, i - k);
    } 
    paramInt1 = paramInt2;
    if (paramInt4 != 0) {
      paramInt1 = this.mCallback.clampViewPositionVertical(this.mCapturedView, paramInt2, paramInt4);
      ViewCompat.offsetTopAndBottom(this.mCapturedView, paramInt1 - j);
    } 
    if (paramInt3 != 0 || paramInt4 != 0)
      this.mCallback.onViewPositionChanged(this.mCapturedView, i, paramInt1, i - k, paramInt1 - j); 
  }
  
  private void ensureMotionHistorySizeForId(int paramInt) {
    float[] arrayOfFloat = this.mInitialMotionX;
    if (arrayOfFloat == null || arrayOfFloat.length <= paramInt) {
      float[] arrayOfFloat1 = new float[++paramInt];
      arrayOfFloat = new float[paramInt];
      float[] arrayOfFloat3 = new float[paramInt];
      float[] arrayOfFloat2 = new float[paramInt];
      int[] arrayOfInt1 = new int[paramInt];
      int[] arrayOfInt3 = new int[paramInt];
      int[] arrayOfInt2 = new int[paramInt];
      float[] arrayOfFloat4 = this.mInitialMotionX;
      if (arrayOfFloat4 != null) {
        System.arraycopy(arrayOfFloat4, 0, arrayOfFloat1, 0, arrayOfFloat4.length);
        arrayOfFloat4 = this.mInitialMotionY;
        System.arraycopy(arrayOfFloat4, 0, arrayOfFloat, 0, arrayOfFloat4.length);
        arrayOfFloat4 = this.mLastMotionX;
        System.arraycopy(arrayOfFloat4, 0, arrayOfFloat3, 0, arrayOfFloat4.length);
        arrayOfFloat4 = this.mLastMotionY;
        System.arraycopy(arrayOfFloat4, 0, arrayOfFloat2, 0, arrayOfFloat4.length);
        int[] arrayOfInt = this.mInitialEdgesTouched;
        System.arraycopy(arrayOfInt, 0, arrayOfInt1, 0, arrayOfInt.length);
        arrayOfInt = this.mEdgeDragsInProgress;
        System.arraycopy(arrayOfInt, 0, arrayOfInt3, 0, arrayOfInt.length);
        arrayOfInt = this.mEdgeDragsLocked;
        System.arraycopy(arrayOfInt, 0, arrayOfInt2, 0, arrayOfInt.length);
      } 
      this.mInitialMotionX = arrayOfFloat1;
      this.mInitialMotionY = arrayOfFloat;
      this.mLastMotionX = arrayOfFloat3;
      this.mLastMotionY = arrayOfFloat2;
      this.mInitialEdgesTouched = arrayOfInt1;
      this.mEdgeDragsInProgress = arrayOfInt3;
      this.mEdgeDragsLocked = arrayOfInt2;
    } 
  }
  
  private boolean forceSettleCapturedViewAt(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int j = this.mCapturedView.getLeft();
    int i = this.mCapturedView.getTop();
    paramInt1 -= j;
    paramInt2 -= i;
    if (paramInt1 == 0 && paramInt2 == 0) {
      this.mScroller.abortAnimation();
      setDragState(0);
      return false;
    } 
    paramInt3 = computeSettleDuration(this.mCapturedView, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mScroller.startScroll(j, i, paramInt1, paramInt2, paramInt3);
    setDragState(2);
    return true;
  }
  
  private int getEdgesTouched(int paramInt1, int paramInt2) {
    if (paramInt1 < this.mParentView.getLeft() + this.mEdgeSize) {
      i = 1;
    } else {
      i = 0;
    } 
    int j = i;
    if (paramInt2 < this.mParentView.getTop() + this.mEdgeSize)
      j = i | 0x4; 
    int i = j;
    if (paramInt1 > this.mParentView.getRight() - this.mEdgeSize)
      i = j | 0x2; 
    paramInt1 = i;
    if (paramInt2 > this.mParentView.getBottom() - this.mEdgeSize)
      paramInt1 = i | 0x8; 
    return paramInt1;
  }
  
  private boolean isValidPointerForActionMove(int paramInt) {
    if (!isPointerDown(paramInt)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Ignoring pointerId=");
      stringBuilder.append(paramInt);
      stringBuilder.append(" because ACTION_DOWN was not received ");
      stringBuilder.append("for this pointer before ACTION_MOVE. It likely happened because ");
      stringBuilder.append(" ViewDragHelper did not receive all the events in the event stream.");
      stringBuilder.toString();
      return false;
    } 
    return true;
  }
  
  private void releaseViewForPointerUp() {
    this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
    dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
  }
  
  private void reportNewEdgeDrags(float paramFloat1, float paramFloat2, int paramInt) {
    int j = 1;
    if (!checkNewEdgeDrag(paramFloat1, paramFloat2, paramInt, 1))
      j = 0; 
    int i = j;
    if (checkNewEdgeDrag(paramFloat2, paramFloat1, paramInt, 4))
      i = j | 0x4; 
    j = i;
    if (checkNewEdgeDrag(paramFloat1, paramFloat2, paramInt, 2))
      j = i | 0x2; 
    i = j;
    if (checkNewEdgeDrag(paramFloat2, paramFloat1, paramInt, 8))
      i = j | 0x8; 
    if (i != 0) {
      int[] arrayOfInt = this.mEdgeDragsInProgress;
      arrayOfInt[paramInt] = arrayOfInt[paramInt] | i;
      this.mCallback.onEdgeDragStarted(i, paramInt);
    } 
  }
  
  private void saveInitialMotion(float paramFloat1, float paramFloat2, int paramInt) {
    ensureMotionHistorySizeForId(paramInt);
    float[] arrayOfFloat = this.mInitialMotionX;
    this.mLastMotionX[paramInt] = paramFloat1;
    arrayOfFloat[paramInt] = paramFloat1;
    arrayOfFloat = this.mInitialMotionY;
    this.mLastMotionY[paramInt] = paramFloat2;
    arrayOfFloat[paramInt] = paramFloat2;
    this.mInitialEdgesTouched[paramInt] = getEdgesTouched((int)paramFloat1, (int)paramFloat2);
    this.mPointersDown |= 1 << paramInt;
  }
  
  private void saveLastMotion(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getPointerCount();
    for (byte b = 0; b < i; b++) {
      int j = paramMotionEvent.getPointerId(b);
      if (isValidPointerForActionMove(j)) {
        float f1 = paramMotionEvent.getX(b);
        float f2 = paramMotionEvent.getY(b);
        this.mLastMotionX[j] = f1;
        this.mLastMotionY[j] = f2;
      } 
    } 
  }
  
  public void cancel() {
    this.mActivePointerId = -1;
    clearMotionHistory();
    VelocityTracker velocityTracker = this.mVelocityTracker;
    if (velocityTracker != null) {
      velocityTracker.recycle();
      this.mVelocityTracker = null;
    } 
  }
  
  public void captureChildView(View paramView, int paramInt) {
    if (paramView.getParent() == this.mParentView) {
      this.mCapturedView = paramView;
      this.mActivePointerId = paramInt;
      this.mCallback.onViewCaptured(paramView, paramInt);
      setDragState(1);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (");
    stringBuilder.append(this.mParentView);
    stringBuilder.append(")");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean checkTouchSlop(int paramInt) {
    int i = this.mInitialMotionX.length;
    for (byte b = 0; b < i; b++) {
      if (checkTouchSlop(paramInt, b))
        return true; 
    } 
    return false;
  }
  
  public boolean checkTouchSlop(int paramInt1, int paramInt2) {
    boolean bool1;
    boolean bool = isPointerDown(paramInt2);
    boolean bool4 = false;
    boolean bool3 = false;
    boolean bool2 = false;
    if (!bool)
      return false; 
    if ((paramInt1 & 0x1) == 1) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if ((paramInt1 & 0x2) == 2) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    float f2 = this.mLastMotionX[paramInt2] - this.mInitialMotionX[paramInt2];
    float f1 = this.mLastMotionY[paramInt2] - this.mInitialMotionY[paramInt2];
    if (bool1 && paramInt1 != 0) {
      paramInt1 = this.mTouchSlop;
      if (f2 * f2 + f1 * f1 > (paramInt1 * paramInt1))
        bool2 = true; 
      return bool2;
    } 
    if (bool1) {
      bool2 = bool4;
      if (Math.abs(f2) > this.mTouchSlop)
        bool2 = true; 
      return bool2;
    } 
    bool2 = bool3;
    if (paramInt1 != 0) {
      bool2 = bool3;
      if (Math.abs(f1) > this.mTouchSlop)
        bool2 = true; 
    } 
    return bool2;
  }
  
  public boolean continueSettling(boolean paramBoolean) {
    int i = this.mDragState;
    boolean bool = false;
    if (i == 2) {
      boolean bool2 = this.mScroller.computeScrollOffset();
      int j = this.mScroller.getCurrX();
      i = this.mScroller.getCurrY();
      int m = j - this.mCapturedView.getLeft();
      int k = i - this.mCapturedView.getTop();
      if (m != 0)
        ViewCompat.offsetLeftAndRight(this.mCapturedView, m); 
      if (k != 0)
        ViewCompat.offsetTopAndBottom(this.mCapturedView, k); 
      if (m != 0 || k != 0)
        this.mCallback.onViewPositionChanged(this.mCapturedView, j, i, m, k); 
      boolean bool1 = bool2;
      if (bool2) {
        bool1 = bool2;
        if (j == this.mScroller.getFinalX()) {
          bool1 = bool2;
          if (i == this.mScroller.getFinalY()) {
            this.mScroller.abortAnimation();
            bool1 = false;
          } 
        } 
      } 
      if (!bool1)
        if (paramBoolean) {
          this.mParentView.post(this.mSetIdleRunnable);
        } else {
          setDragState(0);
        }  
    } 
    paramBoolean = bool;
    if (this.mDragState == 2)
      paramBoolean = true; 
    return paramBoolean;
  }
  
  public View findTopChildUnder(int paramInt1, int paramInt2) {
    for (int i = this.mParentView.getChildCount() - 1; i >= 0; i--) {
      ViewGroup viewGroup = this.mParentView;
      this.mCallback.getOrderedChildIndex(i);
      View view = viewGroup.getChildAt(i);
      if (paramInt1 >= view.getLeft() && paramInt1 < view.getRight() && paramInt2 >= view.getTop() && paramInt2 < view.getBottom())
        return view; 
    } 
    return null;
  }
  
  public View getCapturedView() {
    return this.mCapturedView;
  }
  
  public int getEdgeSize() {
    return this.mEdgeSize;
  }
  
  public int getTouchSlop() {
    return this.mTouchSlop;
  }
  
  public int getViewDragState() {
    return this.mDragState;
  }
  
  public boolean isCapturedViewUnder(int paramInt1, int paramInt2) {
    return isViewUnder(this.mCapturedView, paramInt1, paramInt2);
  }
  
  public boolean isPointerDown(int paramInt) {
    int i = this.mPointersDown;
    boolean bool = true;
    if ((1 << paramInt & i) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean isViewUnder(View paramView, int paramInt1, int paramInt2) {
    boolean bool2 = false;
    if (paramView == null)
      return false; 
    boolean bool1 = bool2;
    if (paramInt1 >= paramView.getLeft()) {
      bool1 = bool2;
      if (paramInt1 < paramView.getRight()) {
        bool1 = bool2;
        if (paramInt2 >= paramView.getTop()) {
          bool1 = bool2;
          if (paramInt2 < paramView.getBottom())
            bool1 = true; 
        } 
      } 
    } 
    return bool1;
  }
  
  public void processTouchEvent(MotionEvent paramMotionEvent) {
    int m = paramMotionEvent.getActionMasked();
    int k = paramMotionEvent.getActionIndex();
    if (m == 0)
      cancel(); 
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain(); 
    this.mVelocityTracker.addMovement(paramMotionEvent);
    int j = 0;
    int i = 0;
    if (m != 0) {
      if (m != 1) {
        if (m != 2) {
          if (m != 3) {
            if (m != 5) {
              if (m == 6) {
                j = paramMotionEvent.getPointerId(k);
                if (this.mDragState == 1 && j == this.mActivePointerId) {
                  k = paramMotionEvent.getPointerCount();
                  while (true) {
                    if (i < k) {
                      m = paramMotionEvent.getPointerId(i);
                      if (m != this.mActivePointerId) {
                        float f2 = paramMotionEvent.getX(i);
                        float f1 = paramMotionEvent.getY(i);
                        View view2 = findTopChildUnder((int)f2, (int)f1);
                        View view1 = this.mCapturedView;
                        if (view2 == view1 && tryCaptureViewForDrag(view1, m)) {
                          i = this.mActivePointerId;
                          break;
                        } 
                      } 
                      i++;
                      continue;
                    } 
                    i = -1;
                    break;
                  } 
                  if (i == -1)
                    releaseViewForPointerUp(); 
                } 
                clearMotionHistory(j);
              } 
            } else {
              i = paramMotionEvent.getPointerId(k);
              float f2 = paramMotionEvent.getX(k);
              float f1 = paramMotionEvent.getY(k);
              saveInitialMotion(f2, f1, i);
              if (this.mDragState == 0) {
                tryCaptureViewForDrag(findTopChildUnder((int)f2, (int)f1), i);
                j = this.mInitialEdgesTouched[i];
                k = this.mTrackingEdges;
                if ((j & k) != 0)
                  this.mCallback.onEdgeTouched(j & k, i); 
              } else if (isCapturedViewUnder((int)f2, (int)f1)) {
                tryCaptureViewForDrag(this.mCapturedView, i);
              } 
            } 
          } else {
            if (this.mDragState == 1)
              dispatchViewReleased(0.0F, 0.0F); 
            cancel();
          } 
        } else if (this.mDragState == 1) {
          if (isValidPointerForActionMove(this.mActivePointerId)) {
            i = paramMotionEvent.findPointerIndex(this.mActivePointerId);
            float f1 = paramMotionEvent.getX(i);
            float f2 = paramMotionEvent.getY(i);
            float[] arrayOfFloat = this.mLastMotionX;
            j = this.mActivePointerId;
            i = (int)(f1 - arrayOfFloat[j]);
            j = (int)(f2 - this.mLastMotionY[j]);
            dragTo(this.mCapturedView.getLeft() + i, this.mCapturedView.getTop() + j, i, j);
            saveLastMotion(paramMotionEvent);
          } 
        } else {
          k = paramMotionEvent.getPointerCount();
          for (i = j; i < k; i++) {
            j = paramMotionEvent.getPointerId(i);
            if (isValidPointerForActionMove(j)) {
              float f3 = paramMotionEvent.getX(i);
              float f4 = paramMotionEvent.getY(i);
              float f2 = f3 - this.mInitialMotionX[j];
              float f1 = f4 - this.mInitialMotionY[j];
              reportNewEdgeDrags(f2, f1, j);
              if (this.mDragState == 1)
                break; 
              View view = findTopChildUnder((int)f3, (int)f4);
              if (checkTouchSlop(view, f2, f1) && tryCaptureViewForDrag(view, j))
                break; 
            } 
          } 
          saveLastMotion(paramMotionEvent);
        } 
      } else {
        if (this.mDragState == 1)
          releaseViewForPointerUp(); 
        cancel();
      } 
    } else {
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      i = paramMotionEvent.getPointerId(0);
      View view = findTopChildUnder((int)f1, (int)f2);
      saveInitialMotion(f1, f2, i);
      tryCaptureViewForDrag(view, i);
      j = this.mInitialEdgesTouched[i];
      k = this.mTrackingEdges;
      if ((j & k) != 0)
        this.mCallback.onEdgeTouched(j & k, i); 
    } 
  }
  
  void setDragState(int paramInt) {
    this.mParentView.removeCallbacks(this.mSetIdleRunnable);
    if (this.mDragState != paramInt) {
      this.mDragState = paramInt;
      this.mCallback.onViewDragStateChanged(paramInt);
      if (this.mDragState == 0)
        this.mCapturedView = null; 
    } 
  }
  
  public void setEdgeTrackingEnabled(int paramInt) {
    this.mTrackingEdges = paramInt;
  }
  
  public void setMinVelocity(float paramFloat) {
    this.mMinVelocity = paramFloat;
  }
  
  public boolean settleCapturedViewAt(int paramInt1, int paramInt2) {
    if (this.mReleaseInProgress)
      return forceSettleCapturedViewAt(paramInt1, paramInt2, (int)this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int)this.mVelocityTracker.getYVelocity(this.mActivePointerId)); 
    throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
  }
  
  public boolean shouldInterceptTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getActionMasked : ()I
    //   4: istore #6
    //   6: aload_1
    //   7: invokevirtual getActionIndex : ()I
    //   10: istore #7
    //   12: iload #6
    //   14: ifne -> 21
    //   17: aload_0
    //   18: invokevirtual cancel : ()V
    //   21: aload_0
    //   22: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   25: ifnonnull -> 35
    //   28: aload_0
    //   29: invokestatic obtain : ()Landroid/view/VelocityTracker;
    //   32: putfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   35: aload_0
    //   36: getfield mVelocityTracker : Landroid/view/VelocityTracker;
    //   39: aload_1
    //   40: invokevirtual addMovement : (Landroid/view/MotionEvent;)V
    //   43: iload #6
    //   45: ifeq -> 520
    //   48: iload #6
    //   50: iconst_1
    //   51: if_icmpeq -> 513
    //   54: iload #6
    //   56: iconst_2
    //   57: if_icmpeq -> 210
    //   60: iload #6
    //   62: iconst_3
    //   63: if_icmpeq -> 513
    //   66: iload #6
    //   68: iconst_5
    //   69: if_icmpeq -> 95
    //   72: iload #6
    //   74: bipush #6
    //   76: if_icmpeq -> 82
    //   79: goto -> 615
    //   82: aload_0
    //   83: aload_1
    //   84: iload #7
    //   86: invokevirtual getPointerId : (I)I
    //   89: invokespecial clearMotionHistory : (I)V
    //   92: goto -> 79
    //   95: aload_1
    //   96: iload #7
    //   98: invokevirtual getPointerId : (I)I
    //   101: istore #6
    //   103: aload_1
    //   104: iload #7
    //   106: invokevirtual getX : (I)F
    //   109: fstore_2
    //   110: aload_1
    //   111: iload #7
    //   113: invokevirtual getY : (I)F
    //   116: fstore_3
    //   117: aload_0
    //   118: fload_2
    //   119: fload_3
    //   120: iload #6
    //   122: invokespecial saveInitialMotion : (FFI)V
    //   125: aload_0
    //   126: getfield mDragState : I
    //   129: istore #7
    //   131: iload #7
    //   133: ifne -> 176
    //   136: aload_0
    //   137: getfield mInitialEdgesTouched : [I
    //   140: iload #6
    //   142: iaload
    //   143: istore #7
    //   145: aload_0
    //   146: getfield mTrackingEdges : I
    //   149: istore #8
    //   151: iload #7
    //   153: iload #8
    //   155: iand
    //   156: ifeq -> 79
    //   159: aload_0
    //   160: getfield mCallback : Landroidx/customview/widget/ViewDragHelper$Callback;
    //   163: iload #7
    //   165: iload #8
    //   167: iand
    //   168: iload #6
    //   170: invokevirtual onEdgeTouched : (II)V
    //   173: goto -> 79
    //   176: iload #7
    //   178: iconst_2
    //   179: if_icmpne -> 79
    //   182: aload_0
    //   183: fload_2
    //   184: f2i
    //   185: fload_3
    //   186: f2i
    //   187: invokevirtual findTopChildUnder : (II)Landroid/view/View;
    //   190: astore_1
    //   191: aload_1
    //   192: aload_0
    //   193: getfield mCapturedView : Landroid/view/View;
    //   196: if_acmpne -> 79
    //   199: aload_0
    //   200: aload_1
    //   201: iload #6
    //   203: invokevirtual tryCaptureViewForDrag : (Landroid/view/View;I)Z
    //   206: pop
    //   207: goto -> 79
    //   210: aload_0
    //   211: getfield mInitialMotionX : [F
    //   214: ifnull -> 79
    //   217: aload_0
    //   218: getfield mInitialMotionY : [F
    //   221: ifnonnull -> 227
    //   224: goto -> 79
    //   227: aload_1
    //   228: invokevirtual getPointerCount : ()I
    //   231: istore #8
    //   233: iconst_0
    //   234: istore #6
    //   236: iload #6
    //   238: iload #8
    //   240: if_icmpge -> 505
    //   243: aload_1
    //   244: iload #6
    //   246: invokevirtual getPointerId : (I)I
    //   249: istore #9
    //   251: aload_0
    //   252: iload #9
    //   254: invokespecial isValidPointerForActionMove : (I)Z
    //   257: ifne -> 263
    //   260: goto -> 499
    //   263: aload_1
    //   264: iload #6
    //   266: invokevirtual getX : (I)F
    //   269: fstore #5
    //   271: aload_1
    //   272: iload #6
    //   274: invokevirtual getY : (I)F
    //   277: fstore_2
    //   278: fload #5
    //   280: aload_0
    //   281: getfield mInitialMotionX : [F
    //   284: iload #9
    //   286: faload
    //   287: fsub
    //   288: fstore_3
    //   289: fload_2
    //   290: aload_0
    //   291: getfield mInitialMotionY : [F
    //   294: iload #9
    //   296: faload
    //   297: fsub
    //   298: fstore #4
    //   300: aload_0
    //   301: fload #5
    //   303: f2i
    //   304: fload_2
    //   305: f2i
    //   306: invokevirtual findTopChildUnder : (II)Landroid/view/View;
    //   309: astore #17
    //   311: aload #17
    //   313: ifnull -> 334
    //   316: aload_0
    //   317: aload #17
    //   319: fload_3
    //   320: fload #4
    //   322: invokespecial checkTouchSlop : (Landroid/view/View;FF)Z
    //   325: ifeq -> 334
    //   328: iconst_1
    //   329: istore #7
    //   331: goto -> 337
    //   334: iconst_0
    //   335: istore #7
    //   337: iload #7
    //   339: ifeq -> 460
    //   342: aload #17
    //   344: invokevirtual getLeft : ()I
    //   347: istore #10
    //   349: fload_3
    //   350: f2i
    //   351: istore #11
    //   353: aload_0
    //   354: getfield mCallback : Landroidx/customview/widget/ViewDragHelper$Callback;
    //   357: aload #17
    //   359: iload #10
    //   361: iload #11
    //   363: iadd
    //   364: iload #11
    //   366: invokevirtual clampViewPositionHorizontal : (Landroid/view/View;II)I
    //   369: istore #12
    //   371: aload #17
    //   373: invokevirtual getTop : ()I
    //   376: istore #11
    //   378: fload #4
    //   380: f2i
    //   381: istore #13
    //   383: aload_0
    //   384: getfield mCallback : Landroidx/customview/widget/ViewDragHelper$Callback;
    //   387: aload #17
    //   389: iload #11
    //   391: iload #13
    //   393: iadd
    //   394: iload #13
    //   396: invokevirtual clampViewPositionVertical : (Landroid/view/View;II)I
    //   399: istore #13
    //   401: aload_0
    //   402: getfield mCallback : Landroidx/customview/widget/ViewDragHelper$Callback;
    //   405: aload #17
    //   407: invokevirtual getViewHorizontalDragRange : (Landroid/view/View;)I
    //   410: istore #15
    //   412: aload_0
    //   413: getfield mCallback : Landroidx/customview/widget/ViewDragHelper$Callback;
    //   416: aload #17
    //   418: invokevirtual getViewVerticalDragRange : (Landroid/view/View;)I
    //   421: istore #14
    //   423: iload #15
    //   425: ifeq -> 440
    //   428: iload #15
    //   430: ifle -> 460
    //   433: iload #12
    //   435: iload #10
    //   437: if_icmpne -> 460
    //   440: iload #14
    //   442: ifeq -> 505
    //   445: iload #14
    //   447: ifle -> 460
    //   450: iload #13
    //   452: iload #11
    //   454: if_icmpne -> 460
    //   457: goto -> 505
    //   460: aload_0
    //   461: fload_3
    //   462: fload #4
    //   464: iload #9
    //   466: invokespecial reportNewEdgeDrags : (FFI)V
    //   469: aload_0
    //   470: getfield mDragState : I
    //   473: iconst_1
    //   474: if_icmpne -> 480
    //   477: goto -> 505
    //   480: iload #7
    //   482: ifeq -> 499
    //   485: aload_0
    //   486: aload #17
    //   488: iload #9
    //   490: invokevirtual tryCaptureViewForDrag : (Landroid/view/View;I)Z
    //   493: ifeq -> 499
    //   496: goto -> 505
    //   499: iinc #6, 1
    //   502: goto -> 236
    //   505: aload_0
    //   506: aload_1
    //   507: invokespecial saveLastMotion : (Landroid/view/MotionEvent;)V
    //   510: goto -> 79
    //   513: aload_0
    //   514: invokevirtual cancel : ()V
    //   517: goto -> 79
    //   520: aload_1
    //   521: invokevirtual getX : ()F
    //   524: fstore_2
    //   525: aload_1
    //   526: invokevirtual getY : ()F
    //   529: fstore_3
    //   530: aload_1
    //   531: iconst_0
    //   532: invokevirtual getPointerId : (I)I
    //   535: istore #7
    //   537: aload_0
    //   538: fload_2
    //   539: fload_3
    //   540: iload #7
    //   542: invokespecial saveInitialMotion : (FFI)V
    //   545: aload_0
    //   546: fload_2
    //   547: f2i
    //   548: fload_3
    //   549: f2i
    //   550: invokevirtual findTopChildUnder : (II)Landroid/view/View;
    //   553: astore_1
    //   554: aload_1
    //   555: aload_0
    //   556: getfield mCapturedView : Landroid/view/View;
    //   559: if_acmpne -> 578
    //   562: aload_0
    //   563: getfield mDragState : I
    //   566: iconst_2
    //   567: if_icmpne -> 578
    //   570: aload_0
    //   571: aload_1
    //   572: iload #7
    //   574: invokevirtual tryCaptureViewForDrag : (Landroid/view/View;I)Z
    //   577: pop
    //   578: aload_0
    //   579: getfield mInitialEdgesTouched : [I
    //   582: iload #7
    //   584: iaload
    //   585: istore #8
    //   587: aload_0
    //   588: getfield mTrackingEdges : I
    //   591: istore #6
    //   593: iload #8
    //   595: iload #6
    //   597: iand
    //   598: ifeq -> 615
    //   601: aload_0
    //   602: getfield mCallback : Landroidx/customview/widget/ViewDragHelper$Callback;
    //   605: iload #8
    //   607: iload #6
    //   609: iand
    //   610: iload #7
    //   612: invokevirtual onEdgeTouched : (II)V
    //   615: iconst_0
    //   616: istore #16
    //   618: aload_0
    //   619: getfield mDragState : I
    //   622: iconst_1
    //   623: if_icmpne -> 629
    //   626: iconst_1
    //   627: istore #16
    //   629: iload #16
    //   631: ireturn
  }
  
  public boolean smoothSlideViewTo(View paramView, int paramInt1, int paramInt2) {
    this.mCapturedView = paramView;
    this.mActivePointerId = -1;
    boolean bool = forceSettleCapturedViewAt(paramInt1, paramInt2, 0, 0);
    if (!bool && this.mDragState == 0 && this.mCapturedView != null)
      this.mCapturedView = null; 
    return bool;
  }
  
  boolean tryCaptureViewForDrag(View paramView, int paramInt) {
    if (paramView == this.mCapturedView && this.mActivePointerId == paramInt)
      return true; 
    if (paramView != null && this.mCallback.tryCaptureView(paramView, paramInt)) {
      this.mActivePointerId = paramInt;
      captureChildView(paramView, paramInt);
      return true;
    } 
    return false;
  }
  
  public static abstract class Callback {
    public abstract int clampViewPositionHorizontal(View param1View, int param1Int1, int param1Int2);
    
    public abstract int clampViewPositionVertical(View param1View, int param1Int1, int param1Int2);
    
    public int getOrderedChildIndex(int param1Int) {
      return param1Int;
    }
    
    public abstract int getViewHorizontalDragRange(View param1View);
    
    public int getViewVerticalDragRange(View param1View) {
      return 0;
    }
    
    public abstract void onEdgeDragStarted(int param1Int1, int param1Int2);
    
    public abstract boolean onEdgeLock(int param1Int);
    
    public abstract void onEdgeTouched(int param1Int1, int param1Int2);
    
    public abstract void onViewCaptured(View param1View, int param1Int);
    
    public abstract void onViewDragStateChanged(int param1Int);
    
    public abstract void onViewPositionChanged(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4);
    
    public abstract void onViewReleased(View param1View, float param1Float1, float param1Float2);
    
    public abstract boolean tryCaptureView(View param1View, int param1Int);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/customview/widget/ViewDragHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */