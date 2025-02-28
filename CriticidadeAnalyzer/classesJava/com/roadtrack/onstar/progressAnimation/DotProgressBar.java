package com.roadtrack.onstar.progressAnimation;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.R;

public class DotProgressBar extends View {
  private float animatedRadius;
  
  private int animationDirection;
  
  private long animationTime;
  
  private float bounceDotRadius;
  
  private int dotAmount;
  
  private int dotPosition;
  
  private float dotRadius;
  
  private int endColor;
  
  private Paint endPaint;
  
  private ValueAnimator endValueAnimator;
  
  private boolean isFirstLaunch = true;
  
  private Paint primaryPaint;
  
  private int startColor;
  
  private Paint startPaint;
  
  private ValueAnimator startValueAnimator;
  
  private float xCoordinate;
  
  public DotProgressBar(Context paramContext) {
    super(paramContext);
    initializeAttributes(null);
    init();
  }
  
  public DotProgressBar(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initializeAttributes(paramAttributeSet);
    init();
  }
  
  public DotProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    initializeAttributes(paramAttributeSet);
    init();
  }
  
  @TargetApi(21)
  public DotProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    initializeAttributes(paramAttributeSet);
    init();
  }
  
  private void drawCircle(Canvas paramCanvas, float paramFloat) {
    paramCanvas.drawCircle(this.xCoordinate + paramFloat, (getMeasuredHeight() / 2), this.dotRadius, this.primaryPaint);
  }
  
  private void drawCircleDown(Canvas paramCanvas, float paramFloat1, float paramFloat2) {
    paramCanvas.drawCircle(this.xCoordinate + paramFloat1, (getMeasuredHeight() / 2), this.bounceDotRadius - paramFloat2, this.endPaint);
  }
  
  private void drawCircleUp(Canvas paramCanvas, float paramFloat1, float paramFloat2) {
    paramCanvas.drawCircle(this.xCoordinate + paramFloat1, (getMeasuredHeight() / 2), this.dotRadius + paramFloat2, this.startPaint);
  }
  
  private void drawCircles(Canvas paramCanvas, int paramInt, float paramFloat1, float paramFloat2) {
    int i = this.dotPosition;
    if (i == paramInt) {
      drawCircleUp(paramCanvas, paramFloat1, paramFloat2);
    } else if ((paramInt == this.dotAmount - 1 && i == 0 && !this.isFirstLaunch) || this.dotPosition - 1 == paramInt) {
      drawCircleDown(paramCanvas, paramFloat1, paramFloat2);
    } else {
      drawCircle(paramCanvas, paramFloat1);
    } 
  }
  
  private void drawCirclesLeftToRight(Canvas paramCanvas, float paramFloat) {
    float f = 0.0F;
    for (byte b = 0; b < this.dotAmount; b++) {
      drawCircles(paramCanvas, b, f, paramFloat);
      f += this.dotRadius * 3.0F;
    } 
  }
  
  private void drawCirclesRightToLeft(Canvas paramCanvas, float paramFloat) {
    int i = this.dotAmount - 1;
    float f = 0.0F;
    while (i >= 0) {
      drawCircles(paramCanvas, i, f, paramFloat);
      f += this.dotRadius * 3.0F;
      i--;
    } 
  }
  
  private void init() {
    this.primaryPaint = new Paint(5);
    this.primaryPaint.setColor(this.startColor);
    this.primaryPaint.setStrokeJoin(Paint.Join.ROUND);
    this.primaryPaint.setStrokeCap(Paint.Cap.ROUND);
    this.primaryPaint.setStrokeWidth(5.0F);
    this.startPaint = new Paint(this.primaryPaint);
    this.endPaint = new Paint(this.primaryPaint);
    this.startValueAnimator = ValueAnimator.ofInt(new int[] { this.startColor, this.endColor });
    this.startValueAnimator.setDuration(this.animationTime);
    this.startValueAnimator.setEvaluator((TypeEvaluator)new ArgbEvaluator());
    this.startValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          final DotProgressBar this$0;
          
          public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
            DotProgressBar.this.startPaint.setColor(((Integer)param1ValueAnimator.getAnimatedValue()).intValue());
          }
        });
    this.endValueAnimator = ValueAnimator.ofInt(new int[] { this.endColor, this.startColor });
    this.endValueAnimator.setDuration(this.animationTime);
    this.endValueAnimator.setEvaluator((TypeEvaluator)new ArgbEvaluator());
    this.endValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          final DotProgressBar this$0;
          
          public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
            DotProgressBar.this.endPaint.setColor(((Integer)param1ValueAnimator.getAnimatedValue()).intValue());
          }
        });
  }
  
  private void initializeAttributes(AttributeSet paramAttributeSet) {
    if (paramAttributeSet != null) {
      TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.DotProgressBar, 0, 0);
      try {
        setDotAmount(typedArray.getInteger(0, 10));
        long l = typedArray.getInteger(2, getResources().getInteger(17694722));
        this.animationTime = l;
        setAnimationTime(l);
        setStartColor(typedArray.getInteger(4, ContextCompat.getColor(getContext(), 2131034170)));
        setEndColor(typedArray.getInteger(3, ContextCompat.getColor(getContext(), 2131034170)));
        setAnimationDirection(typedArray.getInt(1, 10));
      } finally {
        typedArray.recycle();
      } 
    } else {
      setDotAmount(10);
      setAnimationTime(getResources().getInteger(17694721));
      setStartColor(ContextCompat.getColor(getContext(), 2131034170));
      setEndColor(ContextCompat.getColor(getContext(), 2131034170));
      setAnimationDirection(3);
    } 
  }
  
  private void setDotPosition(int paramInt) {
    this.dotPosition = paramInt;
  }
  
  private void startAnimation() {
    BounceAnimation bounceAnimation = new BounceAnimation();
    bounceAnimation.setDuration(this.animationTime / 2L);
    bounceAnimation.setRepeatCount(-1);
    bounceAnimation.setInterpolator((Interpolator)new LinearInterpolator());
    bounceAnimation.setAnimationListener(new AnimationListener() {
          final DotProgressBar this$0;
          
          public void onAnimationRepeat(Animation param1Animation) {
            DotProgressBar.access$308(DotProgressBar.this);
            if (DotProgressBar.this.dotPosition == DotProgressBar.this.dotAmount)
              DotProgressBar.access$302(DotProgressBar.this, 0); 
            DotProgressBar.this.startValueAnimator.start();
            if (!DotProgressBar.this.isFirstLaunch)
              DotProgressBar.this.endValueAnimator.start(); 
            DotProgressBar.access$602(DotProgressBar.this, false);
          }
        });
    startAnimation(bounceAnimation);
  }
  
  private void stopAnimation() {
    clearAnimation();
    postInvalidate();
  }
  
  public int getAnimationDirection() {
    return this.animationDirection;
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    startAnimation();
  }
  
  protected void onDetachedFromWindow() {
    stopAnimation();
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.animationDirection < 0) {
      drawCirclesRightToLeft(paramCanvas, this.animatedRadius);
    } else {
      drawCirclesLeftToRight(paramCanvas, this.animatedRadius);
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    if (getMeasuredHeight() > getMeasuredWidth()) {
      this.dotRadius = (getMeasuredWidth() / this.dotAmount / 5);
    } else {
      this.dotRadius = (getMeasuredHeight() / 5);
    } 
    float f1 = this.dotRadius;
    this.bounceDotRadius = f1 / 3.0F + f1;
    paramInt1 = this.dotAmount;
    float f2 = paramInt1;
    float f3 = (paramInt1 - 1);
    this.xCoordinate = (getMeasuredWidth() - f2 * f1 * 2.0F + f1 * f3) / 2.0F + this.dotRadius;
  }
  
  void setAnimationDirection(int paramInt) {
    this.animationDirection = paramInt;
  }
  
  void setAnimationTime(long paramLong) {
    this.animationTime = paramLong;
  }
  
  void setDotAmount(int paramInt) {
    this.dotAmount = paramInt;
  }
  
  void setEndColor(int paramInt) {
    this.endColor = paramInt;
  }
  
  void setStartColor(int paramInt) {
    this.startColor = paramInt;
  }
  
  public void setVisibility(int paramInt) {
    super.setVisibility(paramInt);
    if (paramInt == 8 || paramInt == 4) {
      stopAnimation();
      return;
    } 
    startAnimation();
  }
  
  private class BounceAnimation extends Animation {
    final DotProgressBar this$0;
    
    private BounceAnimation() {}
    
    protected void applyTransformation(float param1Float, Transformation param1Transformation) {
      super.applyTransformation(param1Float, param1Transformation);
      DotProgressBar dotProgressBar = DotProgressBar.this;
      DotProgressBar.access$802(dotProgressBar, (dotProgressBar.bounceDotRadius - DotProgressBar.this.dotRadius) * param1Float);
      DotProgressBar.this.invalidate();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/progressAnimation/DotProgressBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */