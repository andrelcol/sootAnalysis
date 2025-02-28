package com.roadtrack.onstar.circularProgress;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.roadtrack.onstar.R;

public class AnimDownloadProgressButton extends TextView {
  private float mAboveTextSize = 50.0F;
  
  private RectF mBackgroundBounds;
  
  private int[] mBackgroundColor;
  
  private Paint mBackgroundPaint;
  
  private int mBackgroundSecondColor;
  
  private float mButtonRadius;
  
  private CharSequence mCurrentText;
  
  private ButtonController mCustomerController;
  
  private ButtonController mDefaultController;
  
  private Paint mDot1Paint;
  
  private float mDot1transX;
  
  private Paint mDot2Paint;
  
  private float mDot2transX;
  
  private AnimatorSet mDotAnimationSet;
  
  private LinearGradient mFillBgGradient;
  
  private int mMaxProgress;
  
  private int mMinProgress;
  
  private int[] mOriginBackgroundColor;
  
  private float mProgress = -1.0F;
  
  private ValueAnimator mProgressAnimation;
  
  private LinearGradient mProgressBgGradient;
  
  private float mProgressPercent;
  
  private LinearGradient mProgressTextGradient;
  
  private int mState;
  
  private int mTextColor;
  
  private int mTextCoverColor;
  
  private volatile Paint mTextPaint;
  
  private float mToProgress;
  
  public AnimDownloadProgressButton(Context paramContext) {
    this(paramContext, null);
  }
  
  public AnimDownloadProgressButton(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (!isInEditMode()) {
      initController();
      initAttrs(paramContext, paramAttributeSet);
      init();
      setupAnimations();
    } else {
      initController();
    } 
  }
  
  private int calculateDot1AlphaByTime(int paramInt) {
    char c = 'ÿ';
    int i = 160;
    if (paramInt >= 0 && paramInt <= 160) {
      i = 0;
    } else {
      double d;
      if (160 < paramInt && paramInt <= 243) {
        d = 3.072289156626506D;
      } else {
        if (243 < paramInt && paramInt <= 1160) {
          i = c;
        } else {
          i = c;
          if (1160 < paramInt) {
            char c1 = 'ӛ';
            i = c;
            if (paramInt <= 1243) {
              d = -3.072289156626506D;
              i = c1;
            } else {
              return i;
            } 
          } else {
            return i;
          } 
          i = (int)((paramInt - i) * d);
        } 
        return i;
      } 
      i = (int)((paramInt - i) * d);
    } 
    return i;
  }
  
  private int calculateDot2AlphaByTime(int paramInt) {
    char c1;
    char c2 = 'ÿ';
    if (paramInt >= 0 && paramInt <= 83) {
      c1 = (int)(paramInt * 3.072289156626506D);
    } else if (83 < paramInt && paramInt <= 1000) {
      c1 = c2;
    } else if (1000 < paramInt && paramInt <= 1083) {
      c1 = (int)((paramInt - 1083) * -3.072289156626506D);
    } else {
      c1 = c2;
      if (1083 < paramInt) {
        c1 = c2;
        if (paramInt <= 1243)
          c1 = Character.MIN_VALUE; 
      } 
    } 
    return c1;
  }
  
  private void drawBackground(Canvas paramCanvas) {
    RectF rectF1;
    this.mBackgroundBounds = new RectF();
    if (this.mButtonRadius == 0.0F)
      this.mButtonRadius = (getMeasuredHeight() / 2); 
    RectF rectF2 = this.mBackgroundBounds;
    rectF2.left = 2.0F;
    rectF2.top = 2.0F;
    rectF2.right = (getMeasuredWidth() - 2);
    this.mBackgroundBounds.bottom = (getMeasuredHeight() - 2);
    ButtonController buttonController = switchController();
    int i = this.mState;
    if (i != 0) {
      if (i != 1) {
        if (i == 2) {
          if (buttonController.enableGradient()) {
            this.mFillBgGradient = new LinearGradient(0.0F, (getMeasuredHeight() / 2), getMeasuredWidth(), (getMeasuredHeight() / 2), this.mBackgroundColor, null, Shader.TileMode.CLAMP);
            this.mBackgroundPaint.setShader((Shader)this.mFillBgGradient);
          } else {
            this.mBackgroundPaint.setShader(null);
            this.mBackgroundPaint.setColor(this.mBackgroundColor[0]);
          } 
          rectF1 = this.mBackgroundBounds;
          float f = this.mButtonRadius;
          paramCanvas.drawRoundRect(rectF1, f, f, this.mBackgroundPaint);
        } 
      } else {
        if (rectF1.enableGradient()) {
          this.mProgressPercent = this.mProgress / (this.mMaxProgress + 0.0F);
          int[] arrayOfInt = this.mBackgroundColor;
          int k = arrayOfInt[0];
          i = arrayOfInt[1];
          int j = this.mBackgroundSecondColor;
          float f2 = getMeasuredWidth();
          float f1 = this.mProgressPercent;
          Shader.TileMode tileMode = Shader.TileMode.CLAMP;
          this.mProgressBgGradient = new LinearGradient(0.0F, 0.0F, f2, 0.0F, new int[] { k, i, j }, new float[] { 0.0F, f1, f1 + 0.001F }, tileMode);
          this.mBackgroundPaint.setShader((Shader)this.mProgressBgGradient);
        } else {
          this.mProgressPercent = this.mProgress / (this.mMaxProgress + 0.0F);
          float f1 = getMeasuredWidth();
          i = this.mBackgroundColor[0];
          int j = this.mBackgroundSecondColor;
          float f2 = this.mProgressPercent;
          Shader.TileMode tileMode = Shader.TileMode.CLAMP;
          this.mProgressBgGradient = new LinearGradient(0.0F, 0.0F, f1, 0.0F, new int[] { i, j }, new float[] { f2, f2 + 0.001F }, tileMode);
          this.mBackgroundPaint.setColor(this.mBackgroundColor[0]);
          this.mBackgroundPaint.setShader((Shader)this.mProgressBgGradient);
        } 
        rectF1 = this.mBackgroundBounds;
        float f = this.mButtonRadius;
        paramCanvas.drawRoundRect(rectF1, f, f, this.mBackgroundPaint);
      } 
    } else {
      if (rectF1.enableGradient()) {
        this.mFillBgGradient = new LinearGradient(0.0F, (getMeasuredHeight() / 2), getMeasuredWidth(), (getMeasuredHeight() / 2), this.mBackgroundColor, null, Shader.TileMode.CLAMP);
        this.mBackgroundPaint.setShader((Shader)this.mFillBgGradient);
      } else {
        if (this.mBackgroundPaint.getShader() != null)
          this.mBackgroundPaint.setShader(null); 
        this.mBackgroundPaint.setColor(this.mBackgroundColor[0]);
      } 
      rectF1 = this.mBackgroundBounds;
      float f = this.mButtonRadius;
      paramCanvas.drawRoundRect(rectF1, f, f, this.mBackgroundPaint);
    } 
  }
  
  private void drawTextAbove(Canvas paramCanvas) {
    float f1 = (paramCanvas.getHeight() / 2) - this.mTextPaint.descent() / 2.0F + this.mTextPaint.ascent() / 2.0F;
    if (this.mCurrentText == null)
      this.mCurrentText = ""; 
    float f2 = this.mTextPaint.measureText(this.mCurrentText.toString());
    int i = this.mState;
    if (i != 0) {
      if (i != 1) {
        if (i == 2) {
          this.mTextPaint.setColor(this.mTextCoverColor);
          paramCanvas.drawText(this.mCurrentText.toString(), (getMeasuredWidth() - f2) / 2.0F, f1, this.mTextPaint);
          paramCanvas.drawCircle((getMeasuredWidth() + f2) / 2.0F + 4.0F + this.mDot1transX, f1, 4.0F, this.mDot1Paint);
          paramCanvas.drawCircle((getMeasuredWidth() + f2) / 2.0F + 24.0F + this.mDot2transX, f1, 4.0F, this.mDot2Paint);
        } 
      } else {
        float f5 = getMeasuredWidth() * this.mProgressPercent;
        float f3 = (getMeasuredWidth() / 2);
        float f4 = f2 / 2.0F;
        float f7 = f3 - f4;
        float f6 = (getMeasuredWidth() / 2);
        f3 = (f4 - (getMeasuredWidth() / 2) + f5) / f2;
        if (f5 <= f7) {
          this.mTextPaint.setShader(null);
          this.mTextPaint.setColor(this.mTextColor);
        } else if (f7 < f5 && f5 <= f6 + f4) {
          f4 = (getMeasuredWidth() - f2) / 2.0F;
          f5 = (getMeasuredWidth() + f2) / 2.0F;
          int j = this.mTextCoverColor;
          i = this.mTextColor;
          Shader.TileMode tileMode = Shader.TileMode.CLAMP;
          this.mProgressTextGradient = new LinearGradient(f4, 0.0F, f5, 0.0F, new int[] { j, i }, new float[] { f3, f3 + 0.001F }, tileMode);
          this.mTextPaint.setColor(this.mTextColor);
          this.mTextPaint.setShader((Shader)this.mProgressTextGradient);
        } else {
          this.mTextPaint.setShader(null);
          this.mTextPaint.setColor(this.mTextCoverColor);
        } 
        paramCanvas.drawText(this.mCurrentText.toString(), (getMeasuredWidth() - f2) / 2.0F, f1, this.mTextPaint);
      } 
    } else {
      this.mTextPaint.setShader(null);
      this.mTextPaint.setColor(this.mTextCoverColor);
      paramCanvas.drawText(this.mCurrentText.toString(), (getMeasuredWidth() - f2) / 2.0F, f1, this.mTextPaint);
    } 
  }
  
  private void drawing(Canvas paramCanvas) {
    drawBackground(paramCanvas);
    drawTextAbove(paramCanvas);
  }
  
  private void init() {
    this.mMaxProgress = 100;
    this.mMinProgress = 0;
    this.mProgress = 0.0F;
    this.mBackgroundPaint = new Paint();
    this.mBackgroundPaint.setAntiAlias(true);
    this.mBackgroundPaint.setStyle(Paint.Style.FILL);
    this.mTextPaint = new Paint();
    this.mTextPaint.setAntiAlias(true);
    this.mTextPaint.setTextSize(this.mAboveTextSize);
    if (Build.VERSION.SDK_INT >= 11)
      setLayerType(1, this.mTextPaint); 
    this.mDot1Paint = new Paint();
    this.mDot1Paint.setAntiAlias(true);
    this.mDot1Paint.setTextSize(this.mAboveTextSize);
    this.mDot2Paint = new Paint();
    this.mDot2Paint.setAntiAlias(true);
    this.mDot2Paint.setTextSize(this.mAboveTextSize);
    this.mState = 0;
    invalidate();
  }
  
  private void initAttrs(Context paramContext, AttributeSet paramAttributeSet) {
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AnimDownloadProgressButton);
    int i = typedArray.getColor(0, getResources().getColor(2131034282));
    initGradientColor(i, i);
    this.mBackgroundSecondColor = typedArray.getColor(1, -3355444);
    this.mButtonRadius = typedArray.getFloat(4, (getMeasuredHeight() / 2));
    this.mAboveTextSize = typedArray.getFloat(7, 0.0F);
    this.mTextColor = typedArray.getColor(5, i);
    this.mTextCoverColor = typedArray.getColor(6, i);
    boolean bool1 = typedArray.getBoolean(2, false);
    boolean bool2 = typedArray.getBoolean(3, false);
    DefaultButtonController defaultButtonController = (DefaultButtonController)this.mDefaultController;
    defaultButtonController.setEnableGradient(bool1);
    defaultButtonController.setEnablePress(bool2);
    if (bool1)
      initGradientColor(this.mDefaultController.getLighterColor(this.mBackgroundColor[0]), this.mBackgroundColor[0]); 
    typedArray.recycle();
  }
  
  private void initController() {
    this.mDefaultController = new DefaultButtonController();
  }
  
  private int[] initGradientColor(int paramInt1, int paramInt2) {
    this.mBackgroundColor = new int[2];
    int[] arrayOfInt = this.mBackgroundColor;
    arrayOfInt[0] = paramInt1;
    arrayOfInt[1] = paramInt2;
    return arrayOfInt;
  }
  
  private void setupAnimations() {
    ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(new float[] { 0.0F, 20.0F });
    valueAnimator1.setInterpolator((TimeInterpolator)PathInterpolatorCompat.create(0.11F, 0.0F, 0.12F, 1.0F));
    valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          final AnimDownloadProgressButton this$0;
          
          public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
            float f = ((Float)param1ValueAnimator.getAnimatedValue()).floatValue();
            AnimDownloadProgressButton.access$002(AnimDownloadProgressButton.this, f);
            AnimDownloadProgressButton.access$102(AnimDownloadProgressButton.this, f);
            AnimDownloadProgressButton.this.invalidate();
          }
        });
    valueAnimator1.setDuration(1243L);
    valueAnimator1.setRepeatMode(1);
    valueAnimator1.setRepeatCount(-1);
    final ValueAnimator dotAlphaAnim = ValueAnimator.ofInt(new int[] { 0, 1243 }).setDuration(1243L);
    valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          final AnimDownloadProgressButton this$0;
          
          final ValueAnimator val$dotAlphaAnim;
          
          public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
            int j = ((Integer)dotAlphaAnim.getAnimatedValue()).intValue();
            int i = AnimDownloadProgressButton.this.calculateDot1AlphaByTime(j);
            j = AnimDownloadProgressButton.this.calculateDot2AlphaByTime(j);
            AnimDownloadProgressButton.this.mDot1Paint.setColor(AnimDownloadProgressButton.this.mTextCoverColor);
            AnimDownloadProgressButton.this.mDot2Paint.setColor(AnimDownloadProgressButton.this.mTextCoverColor);
            AnimDownloadProgressButton.this.mDot1Paint.setAlpha(i);
            AnimDownloadProgressButton.this.mDot2Paint.setAlpha(j);
          }
        });
    valueAnimator2.addListener(new Animator.AnimatorListener() {
          final AnimDownloadProgressButton this$0;
          
          public void onAnimationCancel(Animator param1Animator) {}
          
          public void onAnimationEnd(Animator param1Animator) {}
          
          public void onAnimationRepeat(Animator param1Animator) {}
          
          public void onAnimationStart(Animator param1Animator) {
            AnimDownloadProgressButton.this.mDot1Paint.setAlpha(0);
            AnimDownloadProgressButton.this.mDot2Paint.setAlpha(0);
          }
        });
    valueAnimator2.setRepeatMode(1);
    valueAnimator2.setRepeatCount(-1);
    this.mDotAnimationSet = new AnimatorSet();
    this.mDotAnimationSet.playTogether(new Animator[] { (Animator)valueAnimator2, (Animator)valueAnimator1 });
    this.mProgressAnimation = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F }).setDuration(500L);
    this.mProgressAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          final AnimDownloadProgressButton this$0;
          
          public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
            float f = ((Float)param1ValueAnimator.getAnimatedValue()).floatValue();
            AnimDownloadProgressButton animDownloadProgressButton = AnimDownloadProgressButton.this;
            AnimDownloadProgressButton.access$702(animDownloadProgressButton, (animDownloadProgressButton.mToProgress - AnimDownloadProgressButton.this.mProgress) * f + AnimDownloadProgressButton.this.mProgress);
            AnimDownloadProgressButton.this.invalidate();
          }
        });
  }
  
  private ButtonController switchController() {
    ButtonController buttonController = this.mCustomerController;
    return (buttonController != null) ? buttonController : this.mDefaultController;
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    ButtonController buttonController = switchController();
    if (buttonController.enablePress()) {
      if (this.mOriginBackgroundColor == null) {
        this.mOriginBackgroundColor = new int[2];
        int[] arrayOfInt1 = this.mOriginBackgroundColor;
        int[] arrayOfInt2 = this.mBackgroundColor;
        arrayOfInt1[0] = arrayOfInt2[0];
        arrayOfInt1[1] = arrayOfInt2[1];
      } 
      if (isPressed()) {
        int j = buttonController.getPressedColor(this.mBackgroundColor[0]);
        int i = buttonController.getPressedColor(this.mBackgroundColor[1]);
        if (buttonController.enableGradient()) {
          initGradientColor(j, i);
        } else {
          initGradientColor(j, j);
        } 
      } else if (buttonController.enableGradient()) {
        int[] arrayOfInt = this.mOriginBackgroundColor;
        initGradientColor(arrayOfInt[0], arrayOfInt[1]);
      } else {
        int[] arrayOfInt = this.mOriginBackgroundColor;
        initGradientColor(arrayOfInt[0], arrayOfInt[0]);
      } 
      invalidate();
    } 
  }
  
  public float getButtonRadius() {
    return this.mButtonRadius;
  }
  
  public int getMaxProgress() {
    return this.mMaxProgress;
  }
  
  public int getMinProgress() {
    return this.mMinProgress;
  }
  
  public float getProgress() {
    return this.mProgress;
  }
  
  public int getState() {
    return this.mState;
  }
  
  public int getTextColor() {
    return this.mTextColor;
  }
  
  public int getTextCoverColor() {
    return this.mTextCoverColor;
  }
  
  public float getTextSize() {
    return this.mAboveTextSize;
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (!isInEditMode())
      drawing(paramCanvas); 
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    this.mState = savedState.state;
    this.mProgress = savedState.progress;
    this.mCurrentText = savedState.currentText;
  }
  
  public Parcelable onSaveInstanceState() {
    Parcelable parcelable = super.onSaveInstanceState();
    CharSequence charSequence = this.mCurrentText;
    return (Parcelable)((charSequence != null) ? new SavedState(parcelable, (int)this.mProgress, this.mState, charSequence.toString()) : new SavedState(parcelable, (int)this.mProgress, this.mState, ""));
  }
  
  public void setButtonRadius(float paramFloat) {
    this.mButtonRadius = paramFloat;
  }
  
  public void setCurrentText(CharSequence paramCharSequence) {
    this.mCurrentText = paramCharSequence;
    invalidate();
  }
  
  public void setMaxProgress(int paramInt) {
    this.mMaxProgress = paramInt;
  }
  
  public void setMinProgress(int paramInt) {
    this.mMinProgress = paramInt;
  }
  
  public void setProgress(float paramFloat) {
    this.mProgress = paramFloat;
  }
  
  public void setProgressBtnBackgroundColor(int paramInt) {
    initGradientColor(paramInt, paramInt);
  }
  
  public void setProgressBtnBackgroundSecondColor(int paramInt) {
    this.mBackgroundSecondColor = paramInt;
  }
  
  public void setState(int paramInt) {
    if (this.mState != paramInt) {
      this.mState = paramInt;
      invalidate();
      if (paramInt == 2) {
        this.mDotAnimationSet.start();
      } else if (paramInt == 0) {
        this.mDotAnimationSet.cancel();
      } else if (paramInt == 1) {
        this.mDotAnimationSet.cancel();
      } 
    } 
  }
  
  public void setTextColor(int paramInt) {
    this.mTextColor = paramInt;
  }
  
  public void setTextCoverColor(int paramInt) {
    this.mTextCoverColor = paramInt;
  }
  
  public void setTextSize(float paramFloat) {
    this.mAboveTextSize = paramFloat;
    this.mTextPaint.setTextSize(paramFloat);
  }
  
  public static class SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
        public AnimDownloadProgressButton.SavedState createFromParcel(Parcel param2Parcel) {
          return new AnimDownloadProgressButton.SavedState(param2Parcel);
        }
        
        public AnimDownloadProgressButton.SavedState[] newArray(int param2Int) {
          return new AnimDownloadProgressButton.SavedState[param2Int];
        }
      };
    
    private String currentText;
    
    private int progress;
    
    private int state;
    
    private SavedState(Parcel param1Parcel) {
      super(param1Parcel);
      this.progress = param1Parcel.readInt();
      this.state = param1Parcel.readInt();
      this.currentText = param1Parcel.readString();
    }
    
    public SavedState(Parcelable param1Parcelable, int param1Int1, int param1Int2, String param1String) {
      super(param1Parcelable);
      this.progress = param1Int1;
      this.state = param1Int2;
      this.currentText = param1String;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.progress);
      param1Parcel.writeInt(this.state);
      param1Parcel.writeString(this.currentText);
    }
  }
  
  static final class null implements Parcelable.Creator<SavedState> {
    public AnimDownloadProgressButton.SavedState createFromParcel(Parcel param1Parcel) {
      return new AnimDownloadProgressButton.SavedState(param1Parcel);
    }
    
    public AnimDownloadProgressButton.SavedState[] newArray(int param1Int) {
      return new AnimDownloadProgressButton.SavedState[param1Int];
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/circularProgress/AnimDownloadProgressButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */