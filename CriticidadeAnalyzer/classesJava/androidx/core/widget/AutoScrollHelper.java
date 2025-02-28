package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;

public abstract class AutoScrollHelper implements View.OnTouchListener {
  private static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
  
  private int mActivationDelay;
  
  private boolean mAlreadyDelayed;
  
  boolean mAnimating;
  
  private final Interpolator mEdgeInterpolator = (Interpolator)new AccelerateInterpolator();
  
  private int mEdgeType;
  
  private boolean mEnabled;
  
  private boolean mExclusive;
  
  private float[] mMaximumEdges = new float[] { Float.MAX_VALUE, Float.MAX_VALUE };
  
  private float[] mMaximumVelocity = new float[] { Float.MAX_VALUE, Float.MAX_VALUE };
  
  private float[] mMinimumVelocity = new float[] { 0.0F, 0.0F };
  
  boolean mNeedsCancel;
  
  boolean mNeedsReset;
  
  private float[] mRelativeEdges = new float[] { 0.0F, 0.0F };
  
  private float[] mRelativeVelocity = new float[] { 0.0F, 0.0F };
  
  private Runnable mRunnable;
  
  final ClampedScroller mScroller = new ClampedScroller();
  
  final View mTarget;
  
  public AutoScrollHelper(View paramView) {
    this.mTarget = paramView;
    float f = (Resources.getSystem().getDisplayMetrics()).density;
    int i = (int)(1575.0F * f + 0.5F);
    int j = (int)(f * 315.0F + 0.5F);
    f = i;
    setMaximumVelocity(f, f);
    f = j;
    setMinimumVelocity(f, f);
    setEdgeType(1);
    setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
    setRelativeEdges(0.2F, 0.2F);
    setRelativeVelocity(1.0F, 1.0F);
    setActivationDelay(DEFAULT_ACTIVATION_DELAY);
    setRampUpDuration(500);
    setRampDownDuration(500);
  }
  
  private float computeTargetVelocity(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3) {
    float f1 = getEdgeValue(this.mRelativeEdges[paramInt], paramFloat2, this.mMaximumEdges[paramInt], paramFloat1);
    int i = f1 cmp 0.0F;
    if (i == 0)
      return 0.0F; 
    float f2 = this.mRelativeVelocity[paramInt];
    paramFloat1 = this.mMinimumVelocity[paramInt];
    paramFloat2 = this.mMaximumVelocity[paramInt];
    paramFloat3 = f2 * paramFloat3;
    return (i > 0) ? constrain(f1 * paramFloat3, paramFloat1, paramFloat2) : -constrain(-f1 * paramFloat3, paramFloat1, paramFloat2);
  }
  
  static float constrain(float paramFloat1, float paramFloat2, float paramFloat3) {
    return (paramFloat1 > paramFloat3) ? paramFloat3 : ((paramFloat1 < paramFloat2) ? paramFloat2 : paramFloat1);
  }
  
  static int constrain(int paramInt1, int paramInt2, int paramInt3) {
    return (paramInt1 > paramInt3) ? paramInt3 : ((paramInt1 < paramInt2) ? paramInt2 : paramInt1);
  }
  
  private float constrainEdgeValue(float paramFloat1, float paramFloat2) {
    if (paramFloat2 == 0.0F)
      return 0.0F; 
    int i = this.mEdgeType;
    if (i != 0 && i != 1) {
      if (i == 2 && paramFloat1 < 0.0F)
        return paramFloat1 / -paramFloat2; 
    } else if (paramFloat1 < paramFloat2) {
      if (paramFloat1 >= 0.0F)
        return 1.0F - paramFloat1 / paramFloat2; 
      if (this.mAnimating && this.mEdgeType == 1)
        return 1.0F; 
    } 
    return 0.0F;
  }
  
  private float getEdgeValue(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    paramFloat1 = constrain(paramFloat1 * paramFloat2, 0.0F, paramFloat3);
    paramFloat3 = constrainEdgeValue(paramFloat4, paramFloat1);
    paramFloat1 = constrainEdgeValue(paramFloat2 - paramFloat4, paramFloat1) - paramFloat3;
    if (paramFloat1 < 0.0F) {
      paramFloat1 = -this.mEdgeInterpolator.getInterpolation(-paramFloat1);
    } else {
      if (paramFloat1 > 0.0F) {
        paramFloat1 = this.mEdgeInterpolator.getInterpolation(paramFloat1);
        return constrain(paramFloat1, -1.0F, 1.0F);
      } 
      return 0.0F;
    } 
    return constrain(paramFloat1, -1.0F, 1.0F);
  }
  
  private void requestStop() {
    if (this.mNeedsReset) {
      this.mAnimating = false;
    } else {
      this.mScroller.requestStop();
    } 
  }
  
  private void startAnimating() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mRunnable : Ljava/lang/Runnable;
    //   4: ifnonnull -> 19
    //   7: aload_0
    //   8: new androidx/core/widget/AutoScrollHelper$ScrollAnimationRunnable
    //   11: dup
    //   12: aload_0
    //   13: invokespecial <init> : (Landroidx/core/widget/AutoScrollHelper;)V
    //   16: putfield mRunnable : Ljava/lang/Runnable;
    //   19: aload_0
    //   20: iconst_1
    //   21: putfield mAnimating : Z
    //   24: aload_0
    //   25: iconst_1
    //   26: putfield mNeedsReset : Z
    //   29: aload_0
    //   30: getfield mAlreadyDelayed : Z
    //   33: ifne -> 61
    //   36: aload_0
    //   37: getfield mActivationDelay : I
    //   40: istore_1
    //   41: iload_1
    //   42: ifle -> 61
    //   45: aload_0
    //   46: getfield mTarget : Landroid/view/View;
    //   49: aload_0
    //   50: getfield mRunnable : Ljava/lang/Runnable;
    //   53: iload_1
    //   54: i2l
    //   55: invokestatic postOnAnimationDelayed : (Landroid/view/View;Ljava/lang/Runnable;J)V
    //   58: goto -> 70
    //   61: aload_0
    //   62: getfield mRunnable : Ljava/lang/Runnable;
    //   65: invokeinterface run : ()V
    //   70: aload_0
    //   71: iconst_1
    //   72: putfield mAlreadyDelayed : Z
    //   75: return
  }
  
  public abstract boolean canTargetScrollHorizontally(int paramInt);
  
  public abstract boolean canTargetScrollVertically(int paramInt);
  
  void cancelTargetTouch() {
    long l = SystemClock.uptimeMillis();
    MotionEvent motionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
    this.mTarget.onTouchEvent(motionEvent);
    motionEvent.recycle();
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mEnabled : Z
    //   4: istore #6
    //   6: iconst_0
    //   7: istore #7
    //   9: iload #6
    //   11: ifne -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_2
    //   17: invokevirtual getActionMasked : ()I
    //   20: istore #5
    //   22: iload #5
    //   24: ifeq -> 55
    //   27: iload #5
    //   29: iconst_1
    //   30: if_icmpeq -> 48
    //   33: iload #5
    //   35: iconst_2
    //   36: if_icmpeq -> 65
    //   39: iload #5
    //   41: iconst_3
    //   42: if_icmpeq -> 48
    //   45: goto -> 140
    //   48: aload_0
    //   49: invokespecial requestStop : ()V
    //   52: goto -> 140
    //   55: aload_0
    //   56: iconst_1
    //   57: putfield mNeedsCancel : Z
    //   60: aload_0
    //   61: iconst_0
    //   62: putfield mAlreadyDelayed : Z
    //   65: aload_0
    //   66: iconst_0
    //   67: aload_2
    //   68: invokevirtual getX : ()F
    //   71: aload_1
    //   72: invokevirtual getWidth : ()I
    //   75: i2f
    //   76: aload_0
    //   77: getfield mTarget : Landroid/view/View;
    //   80: invokevirtual getWidth : ()I
    //   83: i2f
    //   84: invokespecial computeTargetVelocity : (IFFF)F
    //   87: fstore_3
    //   88: aload_0
    //   89: iconst_1
    //   90: aload_2
    //   91: invokevirtual getY : ()F
    //   94: aload_1
    //   95: invokevirtual getHeight : ()I
    //   98: i2f
    //   99: aload_0
    //   100: getfield mTarget : Landroid/view/View;
    //   103: invokevirtual getHeight : ()I
    //   106: i2f
    //   107: invokespecial computeTargetVelocity : (IFFF)F
    //   110: fstore #4
    //   112: aload_0
    //   113: getfield mScroller : Landroidx/core/widget/AutoScrollHelper$ClampedScroller;
    //   116: fload_3
    //   117: fload #4
    //   119: invokevirtual setTargetVelocity : (FF)V
    //   122: aload_0
    //   123: getfield mAnimating : Z
    //   126: ifne -> 140
    //   129: aload_0
    //   130: invokevirtual shouldAnimate : ()Z
    //   133: ifeq -> 140
    //   136: aload_0
    //   137: invokespecial startAnimating : ()V
    //   140: iload #7
    //   142: istore #6
    //   144: aload_0
    //   145: getfield mExclusive : Z
    //   148: ifeq -> 165
    //   151: iload #7
    //   153: istore #6
    //   155: aload_0
    //   156: getfield mAnimating : Z
    //   159: ifeq -> 165
    //   162: iconst_1
    //   163: istore #6
    //   165: iload #6
    //   167: ireturn
  }
  
  public abstract void scrollTargetBy(int paramInt1, int paramInt2);
  
  public AutoScrollHelper setActivationDelay(int paramInt) {
    this.mActivationDelay = paramInt;
    return this;
  }
  
  public AutoScrollHelper setEdgeType(int paramInt) {
    this.mEdgeType = paramInt;
    return this;
  }
  
  public AutoScrollHelper setEnabled(boolean paramBoolean) {
    if (this.mEnabled && !paramBoolean)
      requestStop(); 
    this.mEnabled = paramBoolean;
    return this;
  }
  
  public AutoScrollHelper setMaximumEdges(float paramFloat1, float paramFloat2) {
    float[] arrayOfFloat = this.mMaximumEdges;
    arrayOfFloat[0] = paramFloat1;
    arrayOfFloat[1] = paramFloat2;
    return this;
  }
  
  public AutoScrollHelper setMaximumVelocity(float paramFloat1, float paramFloat2) {
    float[] arrayOfFloat = this.mMaximumVelocity;
    arrayOfFloat[0] = paramFloat1 / 1000.0F;
    arrayOfFloat[1] = paramFloat2 / 1000.0F;
    return this;
  }
  
  public AutoScrollHelper setMinimumVelocity(float paramFloat1, float paramFloat2) {
    float[] arrayOfFloat = this.mMinimumVelocity;
    arrayOfFloat[0] = paramFloat1 / 1000.0F;
    arrayOfFloat[1] = paramFloat2 / 1000.0F;
    return this;
  }
  
  public AutoScrollHelper setRampDownDuration(int paramInt) {
    this.mScroller.setRampDownDuration(paramInt);
    return this;
  }
  
  public AutoScrollHelper setRampUpDuration(int paramInt) {
    this.mScroller.setRampUpDuration(paramInt);
    return this;
  }
  
  public AutoScrollHelper setRelativeEdges(float paramFloat1, float paramFloat2) {
    float[] arrayOfFloat = this.mRelativeEdges;
    arrayOfFloat[0] = paramFloat1;
    arrayOfFloat[1] = paramFloat2;
    return this;
  }
  
  public AutoScrollHelper setRelativeVelocity(float paramFloat1, float paramFloat2) {
    float[] arrayOfFloat = this.mRelativeVelocity;
    arrayOfFloat[0] = paramFloat1 / 1000.0F;
    arrayOfFloat[1] = paramFloat2 / 1000.0F;
    return this;
  }
  
  boolean shouldAnimate() {
    boolean bool;
    ClampedScroller clampedScroller = this.mScroller;
    int j = clampedScroller.getVerticalDirection();
    int i = clampedScroller.getHorizontalDirection();
    if ((j != 0 && canTargetScrollVertically(j)) || (i != 0 && canTargetScrollHorizontally(i))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static class ClampedScroller {
    private long mDeltaTime = 0L;
    
    private int mDeltaX = 0;
    
    private int mDeltaY = 0;
    
    private int mEffectiveRampDown;
    
    private int mRampDownDuration;
    
    private int mRampUpDuration;
    
    private long mStartTime = Long.MIN_VALUE;
    
    private long mStopTime = -1L;
    
    private float mStopValue;
    
    private float mTargetVelocityX;
    
    private float mTargetVelocityY;
    
    private float getValueAt(long param1Long) {
      if (param1Long < this.mStartTime)
        return 0.0F; 
      long l = this.mStopTime;
      if (l < 0L || param1Long < l)
        return AutoScrollHelper.constrain((float)(param1Long - this.mStartTime) / this.mRampUpDuration, 0.0F, 1.0F) * 0.5F; 
      float f = this.mStopValue;
      return 1.0F - f + f * AutoScrollHelper.constrain((float)(param1Long - l) / this.mEffectiveRampDown, 0.0F, 1.0F);
    }
    
    private float interpolateValue(float param1Float) {
      return -4.0F * param1Float * param1Float + param1Float * 4.0F;
    }
    
    public void computeScrollDelta() {
      if (this.mDeltaTime != 0L) {
        long l1 = AnimationUtils.currentAnimationTimeMillis();
        float f = interpolateValue(getValueAt(l1));
        long l2 = this.mDeltaTime;
        this.mDeltaTime = l1;
        f = (float)(l1 - l2) * f;
        this.mDeltaX = (int)(this.mTargetVelocityX * f);
        this.mDeltaY = (int)(f * this.mTargetVelocityY);
        return;
      } 
      throw new RuntimeException("Cannot compute scroll delta before calling start()");
    }
    
    public int getDeltaX() {
      return this.mDeltaX;
    }
    
    public int getDeltaY() {
      return this.mDeltaY;
    }
    
    public int getHorizontalDirection() {
      float f = this.mTargetVelocityX;
      return (int)(f / Math.abs(f));
    }
    
    public int getVerticalDirection() {
      float f = this.mTargetVelocityY;
      return (int)(f / Math.abs(f));
    }
    
    public boolean isFinished() {
      boolean bool;
      if (this.mStopTime > 0L && AnimationUtils.currentAnimationTimeMillis() > this.mStopTime + this.mEffectiveRampDown) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void requestStop() {
      long l = AnimationUtils.currentAnimationTimeMillis();
      this.mEffectiveRampDown = AutoScrollHelper.constrain((int)(l - this.mStartTime), 0, this.mRampDownDuration);
      this.mStopValue = getValueAt(l);
      this.mStopTime = l;
    }
    
    public void setRampDownDuration(int param1Int) {
      this.mRampDownDuration = param1Int;
    }
    
    public void setRampUpDuration(int param1Int) {
      this.mRampUpDuration = param1Int;
    }
    
    public void setTargetVelocity(float param1Float1, float param1Float2) {
      this.mTargetVelocityX = param1Float1;
      this.mTargetVelocityY = param1Float2;
    }
    
    public void start() {
      this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
      this.mStopTime = -1L;
      this.mDeltaTime = this.mStartTime;
      this.mStopValue = 0.5F;
      this.mDeltaX = 0;
      this.mDeltaY = 0;
    }
  }
  
  private class ScrollAnimationRunnable implements Runnable {
    final AutoScrollHelper this$0;
    
    public void run() {
      AutoScrollHelper autoScrollHelper = AutoScrollHelper.this;
      if (!autoScrollHelper.mAnimating)
        return; 
      if (autoScrollHelper.mNeedsReset) {
        autoScrollHelper.mNeedsReset = false;
        autoScrollHelper.mScroller.start();
      } 
      AutoScrollHelper.ClampedScroller clampedScroller = AutoScrollHelper.this.mScroller;
      if (clampedScroller.isFinished() || !AutoScrollHelper.this.shouldAnimate()) {
        AutoScrollHelper.this.mAnimating = false;
        return;
      } 
      autoScrollHelper = AutoScrollHelper.this;
      if (autoScrollHelper.mNeedsCancel) {
        autoScrollHelper.mNeedsCancel = false;
        autoScrollHelper.cancelTargetTouch();
      } 
      clampedScroller.computeScrollDelta();
      int i = clampedScroller.getDeltaX();
      int j = clampedScroller.getDeltaY();
      AutoScrollHelper.this.scrollTargetBy(i, j);
      ViewCompat.postOnAnimation(AutoScrollHelper.this.mTarget, this);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/widget/AutoScrollHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */