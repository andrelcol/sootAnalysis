package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.core.util.Preconditions;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

public class CircularProgressDrawable extends Drawable implements Animatable {
  private static final int[] COLORS;
  
  private static final Interpolator LINEAR_INTERPOLATOR = (Interpolator)new LinearInterpolator();
  
  private static final Interpolator MATERIAL_INTERPOLATOR = (Interpolator)new FastOutSlowInInterpolator();
  
  private Animator mAnimator;
  
  boolean mFinishing;
  
  private Resources mResources;
  
  private final Ring mRing;
  
  private float mRotation;
  
  float mRotationCount;
  
  static {
    COLORS = new int[] { -16777216 };
  }
  
  public CircularProgressDrawable(Context paramContext) {
    Preconditions.checkNotNull(paramContext);
    this.mResources = paramContext.getResources();
    this.mRing = new Ring();
    this.mRing.setColors(COLORS);
    setStrokeWidth(2.5F);
    setupAnimators();
  }
  
  private void applyFinishTranslation(float paramFloat, Ring paramRing) {
    updateRingColor(paramFloat, paramRing);
    float f = (float)(Math.floor((paramRing.getStartingRotation() / 0.8F)) + 1.0D);
    paramRing.setStartTrim(paramRing.getStartingStartTrim() + (paramRing.getStartingEndTrim() - 0.01F - paramRing.getStartingStartTrim()) * paramFloat);
    paramRing.setEndTrim(paramRing.getStartingEndTrim());
    paramRing.setRotation(paramRing.getStartingRotation() + (f - paramRing.getStartingRotation()) * paramFloat);
  }
  
  private int evaluateColorChange(float paramFloat, int paramInt1, int paramInt2) {
    int i = paramInt1 >> 24 & 0xFF;
    int k = paramInt1 >> 16 & 0xFF;
    int j = paramInt1 >> 8 & 0xFF;
    paramInt1 &= 0xFF;
    return i + (int)(((paramInt2 >> 24 & 0xFF) - i) * paramFloat) << 24 | k + (int)(((paramInt2 >> 16 & 0xFF) - k) * paramFloat) << 16 | j + (int)(((paramInt2 >> 8 & 0xFF) - j) * paramFloat) << 8 | paramInt1 + (int)(paramFloat * ((paramInt2 & 0xFF) - paramInt1));
  }
  
  private void setRotation(float paramFloat) {
    this.mRotation = paramFloat;
  }
  
  private void setSizeParameters(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    Ring ring = this.mRing;
    float f = (this.mResources.getDisplayMetrics()).density;
    ring.setStrokeWidth(paramFloat2 * f);
    ring.setCenterRadius(paramFloat1 * f);
    ring.setColorIndex(0);
    ring.setArrowDimensions(paramFloat3 * f, paramFloat4 * f);
  }
  
  private void setupAnimators() {
    final Ring ring = this.mRing;
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          final CircularProgressDrawable this$0;
          
          final CircularProgressDrawable.Ring val$ring;
          
          public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
            float f = ((Float)param1ValueAnimator.getAnimatedValue()).floatValue();
            CircularProgressDrawable.this.updateRingColor(f, ring);
            CircularProgressDrawable.this.applyTransformation(f, ring, false);
            CircularProgressDrawable.this.invalidateSelf();
          }
        });
    valueAnimator.setRepeatCount(-1);
    valueAnimator.setRepeatMode(1);
    valueAnimator.setInterpolator((TimeInterpolator)LINEAR_INTERPOLATOR);
    valueAnimator.addListener(new Animator.AnimatorListener() {
          final CircularProgressDrawable this$0;
          
          final CircularProgressDrawable.Ring val$ring;
          
          public void onAnimationCancel(Animator param1Animator) {}
          
          public void onAnimationEnd(Animator param1Animator) {}
          
          public void onAnimationRepeat(Animator param1Animator) {
            CircularProgressDrawable.this.applyTransformation(1.0F, ring, true);
            ring.storeOriginals();
            ring.goToNextColor();
            CircularProgressDrawable circularProgressDrawable = CircularProgressDrawable.this;
            if (circularProgressDrawable.mFinishing) {
              circularProgressDrawable.mFinishing = false;
              param1Animator.cancel();
              param1Animator.setDuration(1332L);
              param1Animator.start();
              ring.setShowArrow(false);
            } else {
              circularProgressDrawable.mRotationCount++;
            } 
          }
          
          public void onAnimationStart(Animator param1Animator) {
            CircularProgressDrawable.this.mRotationCount = 0.0F;
          }
        });
    this.mAnimator = (Animator)valueAnimator;
  }
  
  void applyTransformation(float paramFloat, Ring paramRing, boolean paramBoolean) {
    if (this.mFinishing) {
      applyFinishTranslation(paramFloat, paramRing);
    } else if (paramFloat != 1.0F || paramBoolean) {
      float f1;
      float f2;
      float f3 = paramRing.getStartingRotation();
      if (paramFloat < 0.5F) {
        f2 = paramFloat / 0.5F;
        f1 = paramRing.getStartingStartTrim();
        f2 = MATERIAL_INTERPOLATOR.getInterpolation(f2) * 0.79F + 0.01F + f1;
      } else {
        f1 = (paramFloat - 0.5F) / 0.5F;
        f2 = paramRing.getStartingStartTrim() + 0.79F;
        f1 = f2 - (1.0F - MATERIAL_INTERPOLATOR.getInterpolation(f1)) * 0.79F + 0.01F;
      } 
      float f4 = this.mRotationCount;
      paramRing.setStartTrim(f1);
      paramRing.setEndTrim(f2);
      paramRing.setRotation(f3 + 0.20999998F * paramFloat);
      setRotation((paramFloat + f4) * 216.0F);
    } 
  }
  
  public void draw(Canvas paramCanvas) {
    Rect rect = getBounds();
    paramCanvas.save();
    paramCanvas.rotate(this.mRotation, rect.exactCenterX(), rect.exactCenterY());
    this.mRing.draw(paramCanvas, rect);
    paramCanvas.restore();
  }
  
  public int getAlpha() {
    return this.mRing.getAlpha();
  }
  
  public int getOpacity() {
    return -3;
  }
  
  public boolean isRunning() {
    return this.mAnimator.isRunning();
  }
  
  public void setAlpha(int paramInt) {
    this.mRing.setAlpha(paramInt);
    invalidateSelf();
  }
  
  public void setArrowEnabled(boolean paramBoolean) {
    this.mRing.setShowArrow(paramBoolean);
    invalidateSelf();
  }
  
  public void setArrowScale(float paramFloat) {
    this.mRing.setArrowScale(paramFloat);
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mRing.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setColorSchemeColors(int... paramVarArgs) {
    this.mRing.setColors(paramVarArgs);
    this.mRing.setColorIndex(0);
    invalidateSelf();
  }
  
  public void setProgressRotation(float paramFloat) {
    this.mRing.setRotation(paramFloat);
    invalidateSelf();
  }
  
  public void setStartEndTrim(float paramFloat1, float paramFloat2) {
    this.mRing.setStartTrim(paramFloat1);
    this.mRing.setEndTrim(paramFloat2);
    invalidateSelf();
  }
  
  public void setStrokeWidth(float paramFloat) {
    this.mRing.setStrokeWidth(paramFloat);
    invalidateSelf();
  }
  
  public void setStyle(int paramInt) {
    if (paramInt == 0) {
      setSizeParameters(11.0F, 3.0F, 12.0F, 6.0F);
    } else {
      setSizeParameters(7.5F, 2.5F, 10.0F, 5.0F);
    } 
    invalidateSelf();
  }
  
  public void start() {
    this.mAnimator.cancel();
    this.mRing.storeOriginals();
    if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
      this.mFinishing = true;
      this.mAnimator.setDuration(666L);
      this.mAnimator.start();
    } else {
      this.mRing.setColorIndex(0);
      this.mRing.resetOriginals();
      this.mAnimator.setDuration(1332L);
      this.mAnimator.start();
    } 
  }
  
  public void stop() {
    this.mAnimator.cancel();
    setRotation(0.0F);
    this.mRing.setShowArrow(false);
    this.mRing.setColorIndex(0);
    this.mRing.resetOriginals();
    invalidateSelf();
  }
  
  void updateRingColor(float paramFloat, Ring paramRing) {
    if (paramFloat > 0.75F) {
      paramRing.setColor(evaluateColorChange((paramFloat - 0.75F) / 0.25F, paramRing.getStartingColor(), paramRing.getNextColor()));
    } else {
      paramRing.setColor(paramRing.getStartingColor());
    } 
  }
  
  private static class Ring {
    int mAlpha = 255;
    
    Path mArrow;
    
    int mArrowHeight;
    
    final Paint mArrowPaint = new Paint();
    
    float mArrowScale = 1.0F;
    
    int mArrowWidth;
    
    final Paint mCirclePaint = new Paint();
    
    int mColorIndex;
    
    int[] mColors;
    
    int mCurrentColor;
    
    float mEndTrim = 0.0F;
    
    final Paint mPaint = new Paint();
    
    float mRingCenterRadius;
    
    float mRotation = 0.0F;
    
    boolean mShowArrow;
    
    float mStartTrim = 0.0F;
    
    float mStartingEndTrim;
    
    float mStartingRotation;
    
    float mStartingStartTrim;
    
    float mStrokeWidth = 5.0F;
    
    final RectF mTempBounds = new RectF();
    
    Ring() {
      this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
      this.mPaint.setAntiAlias(true);
      this.mPaint.setStyle(Paint.Style.STROKE);
      this.mArrowPaint.setStyle(Paint.Style.FILL);
      this.mArrowPaint.setAntiAlias(true);
      this.mCirclePaint.setColor(0);
    }
    
    void draw(Canvas param1Canvas, Rect param1Rect) {
      RectF rectF = this.mTempBounds;
      float f2 = this.mRingCenterRadius;
      float f1 = this.mStrokeWidth / 2.0F + f2;
      if (f2 <= 0.0F)
        f1 = Math.min(param1Rect.width(), param1Rect.height()) / 2.0F - Math.max(this.mArrowWidth * this.mArrowScale / 2.0F, this.mStrokeWidth / 2.0F); 
      rectF.set(param1Rect.centerX() - f1, param1Rect.centerY() - f1, param1Rect.centerX() + f1, param1Rect.centerY() + f1);
      f1 = this.mStartTrim;
      f2 = this.mRotation;
      f1 = (f1 + f2) * 360.0F;
      f2 = (this.mEndTrim + f2) * 360.0F - f1;
      this.mPaint.setColor(this.mCurrentColor);
      this.mPaint.setAlpha(this.mAlpha);
      float f3 = this.mStrokeWidth / 2.0F;
      rectF.inset(f3, f3);
      param1Canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0F, this.mCirclePaint);
      f3 = -f3;
      rectF.inset(f3, f3);
      param1Canvas.drawArc(rectF, f1, f2, false, this.mPaint);
      drawTriangle(param1Canvas, f1, f2, rectF);
    }
    
    void drawTriangle(Canvas param1Canvas, float param1Float1, float param1Float2, RectF param1RectF) {
      if (this.mShowArrow) {
        Path path = this.mArrow;
        if (path == null) {
          this.mArrow = new Path();
          this.mArrow.setFillType(Path.FillType.EVEN_ODD);
        } else {
          path.reset();
        } 
        float f1 = Math.min(param1RectF.width(), param1RectF.height()) / 2.0F;
        float f2 = this.mArrowWidth * this.mArrowScale / 2.0F;
        this.mArrow.moveTo(0.0F, 0.0F);
        this.mArrow.lineTo(this.mArrowWidth * this.mArrowScale, 0.0F);
        path = this.mArrow;
        float f3 = this.mArrowWidth;
        float f4 = this.mArrowScale;
        path.lineTo(f3 * f4 / 2.0F, this.mArrowHeight * f4);
        this.mArrow.offset(f1 + param1RectF.centerX() - f2, param1RectF.centerY() + this.mStrokeWidth / 2.0F);
        this.mArrow.close();
        this.mArrowPaint.setColor(this.mCurrentColor);
        this.mArrowPaint.setAlpha(this.mAlpha);
        param1Canvas.save();
        param1Canvas.rotate(param1Float1 + param1Float2, param1RectF.centerX(), param1RectF.centerY());
        param1Canvas.drawPath(this.mArrow, this.mArrowPaint);
        param1Canvas.restore();
      } 
    }
    
    int getAlpha() {
      return this.mAlpha;
    }
    
    float getEndTrim() {
      return this.mEndTrim;
    }
    
    int getNextColor() {
      return this.mColors[getNextColorIndex()];
    }
    
    int getNextColorIndex() {
      return (this.mColorIndex + 1) % this.mColors.length;
    }
    
    float getStartTrim() {
      return this.mStartTrim;
    }
    
    int getStartingColor() {
      return this.mColors[this.mColorIndex];
    }
    
    float getStartingEndTrim() {
      return this.mStartingEndTrim;
    }
    
    float getStartingRotation() {
      return this.mStartingRotation;
    }
    
    float getStartingStartTrim() {
      return this.mStartingStartTrim;
    }
    
    void goToNextColor() {
      setColorIndex(getNextColorIndex());
    }
    
    void resetOriginals() {
      this.mStartingStartTrim = 0.0F;
      this.mStartingEndTrim = 0.0F;
      this.mStartingRotation = 0.0F;
      setStartTrim(0.0F);
      setEndTrim(0.0F);
      setRotation(0.0F);
    }
    
    void setAlpha(int param1Int) {
      this.mAlpha = param1Int;
    }
    
    void setArrowDimensions(float param1Float1, float param1Float2) {
      this.mArrowWidth = (int)param1Float1;
      this.mArrowHeight = (int)param1Float2;
    }
    
    void setArrowScale(float param1Float) {
      if (param1Float != this.mArrowScale)
        this.mArrowScale = param1Float; 
    }
    
    void setCenterRadius(float param1Float) {
      this.mRingCenterRadius = param1Float;
    }
    
    void setColor(int param1Int) {
      this.mCurrentColor = param1Int;
    }
    
    void setColorFilter(ColorFilter param1ColorFilter) {
      this.mPaint.setColorFilter(param1ColorFilter);
    }
    
    void setColorIndex(int param1Int) {
      this.mColorIndex = param1Int;
      this.mCurrentColor = this.mColors[this.mColorIndex];
    }
    
    void setColors(int[] param1ArrayOfint) {
      this.mColors = param1ArrayOfint;
      setColorIndex(0);
    }
    
    void setEndTrim(float param1Float) {
      this.mEndTrim = param1Float;
    }
    
    void setRotation(float param1Float) {
      this.mRotation = param1Float;
    }
    
    void setShowArrow(boolean param1Boolean) {
      if (this.mShowArrow != param1Boolean)
        this.mShowArrow = param1Boolean; 
    }
    
    void setStartTrim(float param1Float) {
      this.mStartTrim = param1Float;
    }
    
    void setStrokeWidth(float param1Float) {
      this.mStrokeWidth = param1Float;
      this.mPaint.setStrokeWidth(param1Float);
    }
    
    void storeOriginals() {
      this.mStartingStartTrim = this.mStartTrim;
      this.mStartingEndTrim = this.mEndTrim;
      this.mStartingRotation = this.mRotation;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/swiperefreshlayout/widget/CircularProgressDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */