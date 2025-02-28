package com.roadtrack.onstar.floatingActionButton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import com.roadtrack.onstar.R;

public class FloatingActionsMenu extends ViewGroup {
  private static Interpolator sAlphaExpandInterpolator;
  
  private static Interpolator sCollapseInterpolator;
  
  private static Interpolator sExpandInterpolator = (Interpolator)new OvershootInterpolator();
  
  private AddFloatingActionButton mAddButton;
  
  private int mAddButtonColorNormal;
  
  private int mAddButtonColorPressed;
  
  private int mAddButtonPlusColor;
  
  private int mAddButtonSize;
  
  private boolean mAddButtonStrokeVisible;
  
  private int mButtonSpacing;
  
  private int mButtonsCount;
  
  private AnimatorSet mCollapseAnimation = (new AnimatorSet()).setDuration(300L);
  
  private AnimatorSet mExpandAnimation = (new AnimatorSet()).setDuration(300L);
  
  private int mExpandDirection;
  
  private boolean mExpanded;
  
  private int mLabelsMargin;
  
  private int mLabelsPosition;
  
  private int mLabelsStyle;
  
  private int mLabelsVerticalOffset;
  
  private OnFloatingActionsMenuUpdateListener mListener;
  
  private int mMaxButtonHeight;
  
  private int mMaxButtonWidth;
  
  private RotatingDrawable mRotatingDrawable;
  
  private TouchDelegateGroup mTouchDelegateGroup;
  
  static {
    sCollapseInterpolator = (Interpolator)new DecelerateInterpolator(3.0F);
    sAlphaExpandInterpolator = (Interpolator)new DecelerateInterpolator();
  }
  
  public FloatingActionsMenu(Context paramContext) {
    this(paramContext, null);
  }
  
  public FloatingActionsMenu(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  public FloatingActionsMenu(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private int adjustForOvershoot(int paramInt) {
    return paramInt * 12 / 10;
  }
  
  private void collapse(boolean paramBoolean) {
    if (this.mExpanded) {
      long l;
      this.mExpanded = false;
      this.mTouchDelegateGroup.setEnabled(false);
      AnimatorSet animatorSet = this.mCollapseAnimation;
      if (paramBoolean) {
        l = 0L;
      } else {
        l = 300L;
      } 
      animatorSet.setDuration(l);
      this.mCollapseAnimation.start();
      this.mExpandAnimation.cancel();
      OnFloatingActionsMenuUpdateListener onFloatingActionsMenuUpdateListener = this.mListener;
      if (onFloatingActionsMenuUpdateListener != null)
        onFloatingActionsMenuUpdateListener.onMenuCollapsed(); 
    } 
  }
  
  private void createAddButton(Context paramContext) {
    this.mAddButton = new AddFloatingActionButton(paramContext) {
        final FloatingActionsMenu this$0;
        
        Drawable getIconDrawable() {
          FloatingActionsMenu.RotatingDrawable rotatingDrawable = new FloatingActionsMenu.RotatingDrawable(super.getIconDrawable());
          FloatingActionsMenu.access$402(FloatingActionsMenu.this, rotatingDrawable);
          OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
          ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(rotatingDrawable, "rotation", new float[] { 135.0F, 0.0F });
          ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(rotatingDrawable, "rotation", new float[] { 0.0F, 135.0F });
          objectAnimator1.setInterpolator((TimeInterpolator)overshootInterpolator);
          objectAnimator2.setInterpolator((TimeInterpolator)overshootInterpolator);
          FloatingActionsMenu.this.mExpandAnimation.play((Animator)objectAnimator2);
          FloatingActionsMenu.this.mCollapseAnimation.play((Animator)objectAnimator1);
          return (Drawable)rotatingDrawable;
        }
        
        void updateBackground() {
          this.mPlusColor = FloatingActionsMenu.this.mAddButtonPlusColor;
          this.mColorNormal = FloatingActionsMenu.this.mAddButtonColorNormal;
          this.mColorPressed = FloatingActionsMenu.this.mAddButtonColorPressed;
          this.mStrokeVisible = FloatingActionsMenu.this.mAddButtonStrokeVisible;
          super.updateBackground();
        }
      };
    this.mAddButton.setId(2131296527);
    this.mAddButton.setSize(this.mAddButtonSize);
    this.mAddButton.setOnClickListener(new View.OnClickListener() {
          final FloatingActionsMenu this$0;
          
          public void onClick(View param1View) {
            FloatingActionsMenu.this.toggle();
          }
        });
    addView((View)this.mAddButton, super.generateDefaultLayoutParams());
    this.mButtonsCount++;
  }
  
  private void createLabels() {
    ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.mLabelsStyle);
    for (byte b = 0; b < this.mButtonsCount; b++) {
      FloatingActionButton floatingActionButton = (FloatingActionButton)getChildAt(b);
      String str = floatingActionButton.getTitle();
      if (floatingActionButton != this.mAddButton && str != null && floatingActionButton.getTag(2131296528) == null) {
        TextView textView = new TextView((Context)contextThemeWrapper);
        textView.setTextAppearance(getContext(), this.mLabelsStyle);
        textView.setText(floatingActionButton.getTitle());
        addView((View)textView);
        floatingActionButton.setTag(2131296528, textView);
      } 
    } 
  }
  
  private boolean expandsHorizontally() {
    int i = this.mExpandDirection;
    return (i == 2 || i == 3);
  }
  
  private int getColor(int paramInt) {
    return getResources().getColor(paramInt);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet) {
    this.mButtonSpacing = (int)(getResources().getDimension(2131099751) - getResources().getDimension(2131099757) - getResources().getDimension(2131099756));
    this.mLabelsMargin = getResources().getDimensionPixelSize(2131099753);
    this.mLabelsVerticalOffset = getResources().getDimensionPixelSize(2131099756);
    this.mTouchDelegateGroup = new TouchDelegateGroup((View)this);
    setTouchDelegate(this.mTouchDelegateGroup);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FloatingActionsMenu, 0, 0);
    this.mAddButtonPlusColor = typedArray.getColor(2, getColor(17170443));
    this.mAddButtonColorNormal = typedArray.getColor(0, getColor(17170443));
    this.mAddButtonColorPressed = typedArray.getColor(1, getColor(17170443));
    this.mAddButtonSize = typedArray.getInt(3, 0);
    this.mAddButtonStrokeVisible = typedArray.getBoolean(4, true);
    this.mExpandDirection = typedArray.getInt(5, 0);
    this.mLabelsStyle = typedArray.getResourceId(6, 0);
    this.mLabelsPosition = typedArray.getInt(7, 0);
    typedArray.recycle();
    if (this.mLabelsStyle == 0 || !expandsHorizontally()) {
      createAddButton(paramContext);
      return;
    } 
    throw new IllegalStateException("Action labels in horizontal expand orientation is not supported.");
  }
  
  public void addButton(FloatingActionButton paramFloatingActionButton) {
    addView((View)paramFloatingActionButton, this.mButtonsCount - 1);
    this.mButtonsCount++;
    if (this.mLabelsStyle != 0)
      createLabels(); 
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return super.checkLayoutParams(paramLayoutParams);
  }
  
  public void collapse() {
    collapse(false);
  }
  
  public void expand() {
    if (!this.mExpanded) {
      this.mExpanded = true;
      this.mTouchDelegateGroup.setEnabled(true);
      this.mCollapseAnimation.cancel();
      this.mExpandAnimation.start();
      OnFloatingActionsMenuUpdateListener onFloatingActionsMenuUpdateListener = this.mListener;
      if (onFloatingActionsMenuUpdateListener != null)
        onFloatingActionsMenuUpdateListener.onMenuExpanded(); 
    } 
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(super.generateDefaultLayoutParams());
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(super.generateLayoutParams(paramAttributeSet));
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return new LayoutParams(super.generateLayoutParams(paramLayoutParams));
  }
  
  public boolean isExpanded() {
    return this.mExpanded;
  }
  
  protected void onFinishInflate() {
    super.onFinishInflate();
    bringChildToFront((View)this.mAddButton);
    this.mButtonsCount = getChildCount();
    if (this.mLabelsStyle != 0)
      createLabels(); 
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = this.mExpandDirection;
    if (i != 0 && i != 1) {
      if (i == 2 || i == 3) {
        if (this.mExpandDirection == 2) {
          i = 1;
        } else {
          i = 0;
        } 
        if (i != 0) {
          paramInt3 = paramInt3 - paramInt1 - this.mAddButton.getMeasuredWidth();
        } else {
          paramInt3 = 0;
        } 
        paramInt1 = this.mMaxButtonHeight;
        int j = paramInt4 - paramInt2 - paramInt1 + (paramInt1 - this.mAddButton.getMeasuredHeight()) / 2;
        AddFloatingActionButton addFloatingActionButton = this.mAddButton;
        addFloatingActionButton.layout(paramInt3, j, addFloatingActionButton.getMeasuredWidth() + paramInt3, this.mAddButton.getMeasuredHeight() + j);
        if (i != 0) {
          paramInt1 = paramInt3 - this.mButtonSpacing;
        } else {
          paramInt1 = this.mAddButton.getMeasuredWidth() + paramInt3 + this.mButtonSpacing;
        } 
        paramInt4 = this.mButtonsCount - 1;
        while (paramInt4 >= 0) {
          View view = getChildAt(paramInt4);
          paramInt2 = paramInt1;
          if (view != this.mAddButton)
            if (view.getVisibility() == 8) {
              paramInt2 = paramInt1;
            } else {
              float f1;
              paramInt2 = paramInt1;
              if (i != 0)
                paramInt2 = paramInt1 - view.getMeasuredWidth(); 
              paramInt1 = (this.mAddButton.getMeasuredHeight() - view.getMeasuredHeight()) / 2 + j;
              view.layout(paramInt2, paramInt1, view.getMeasuredWidth() + paramInt2, view.getMeasuredHeight() + paramInt1);
              float f2 = (paramInt3 - paramInt2);
              if (this.mExpanded) {
                f1 = 0.0F;
              } else {
                f1 = f2;
              } 
              view.setTranslationX(f1);
              if (this.mExpanded) {
                f1 = 1.0F;
              } else {
                f1 = 0.0F;
              } 
              view.setAlpha(f1);
              LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
              layoutParams.mCollapseDir.setFloatValues(new float[] { 0.0F, f2 });
              layoutParams.mExpandDir.setFloatValues(new float[] { f2, 0.0F });
              layoutParams.setAnimationsTarget(view);
              if (i != 0) {
                paramInt2 -= this.mButtonSpacing;
              } else {
                paramInt2 = paramInt2 + view.getMeasuredWidth() + this.mButtonSpacing;
              } 
            }  
          paramInt4--;
          paramInt1 = paramInt2;
        } 
      } 
    } else {
      if (this.mExpandDirection == 0) {
        i = 1;
      } else {
        i = 0;
      } 
      if (paramBoolean)
        this.mTouchDelegateGroup.clearTouchDelegates(); 
      if (i != 0) {
        paramInt2 = paramInt4 - paramInt2 - this.mAddButton.getMeasuredHeight();
      } else {
        paramInt2 = 0;
      } 
      if (this.mLabelsPosition == 0) {
        paramInt4 = paramInt3 - paramInt1 - this.mMaxButtonWidth / 2;
      } else {
        paramInt4 = this.mMaxButtonWidth / 2;
      } 
      paramInt1 = paramInt4 - this.mAddButton.getMeasuredWidth() / 2;
      AddFloatingActionButton addFloatingActionButton = this.mAddButton;
      addFloatingActionButton.layout(paramInt1, paramInt2, addFloatingActionButton.getMeasuredWidth() + paramInt1, this.mAddButton.getMeasuredHeight() + paramInt2);
      paramInt1 = this.mMaxButtonWidth / 2 + this.mLabelsMargin;
      if (this.mLabelsPosition == 0) {
        paramInt1 = paramInt4 - paramInt1;
      } else {
        paramInt1 += paramInt4;
      } 
      if (i != 0) {
        paramInt3 = paramInt2 - this.mButtonSpacing;
      } else {
        paramInt3 = this.mAddButton.getMeasuredHeight() + paramInt2 + this.mButtonSpacing;
      } 
      int j = this.mButtonsCount - 1;
      int k = paramInt2;
      while (j >= 0) {
        View view = getChildAt(j);
        if (view != this.mAddButton && view.getVisibility() != 8) {
          float f2;
          int n = paramInt4 - view.getMeasuredWidth() / 2;
          int m = paramInt3;
          if (i != 0)
            m = paramInt3 - view.getMeasuredHeight(); 
          view.layout(n, m, view.getMeasuredWidth() + n, view.getMeasuredHeight() + m);
          float f1 = (k - m);
          if (this.mExpanded) {
            f2 = 0.0F;
          } else {
            f2 = f1;
          } 
          view.setTranslationY(f2);
          if (this.mExpanded) {
            f2 = 1.0F;
          } else {
            f2 = 0.0F;
          } 
          view.setAlpha(f2);
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          layoutParams.mCollapseDir.setFloatValues(new float[] { 0.0F, f1 });
          layoutParams.mExpandDir.setFloatValues(new float[] { f1, 0.0F });
          layoutParams.setAnimationsTarget(view);
          View view1 = (View)view.getTag(2131296528);
          if (view1 != null) {
            if (this.mLabelsPosition == 0) {
              paramInt2 = paramInt1 - view1.getMeasuredWidth();
            } else {
              paramInt2 = view1.getMeasuredWidth() + paramInt1;
            } 
            if (this.mLabelsPosition == 0) {
              paramInt3 = paramInt2;
            } else {
              paramInt3 = paramInt1;
            } 
            if (this.mLabelsPosition == 0)
              paramInt2 = paramInt1; 
            int i1 = m - this.mLabelsVerticalOffset + (view.getMeasuredHeight() - view1.getMeasuredHeight()) / 2;
            view1.layout(paramInt3, i1, paramInt2, i1 + view1.getMeasuredHeight());
            Rect rect = new Rect(Math.min(n, paramInt3), m - this.mButtonSpacing / 2, Math.max(n + view.getMeasuredWidth(), paramInt2), view.getMeasuredHeight() + m + this.mButtonSpacing / 2);
            this.mTouchDelegateGroup.addTouchDelegate(new TouchDelegate(rect, view));
            if (this.mExpanded) {
              f2 = 0.0F;
            } else {
              f2 = f1;
            } 
            view1.setTranslationY(f2);
            if (this.mExpanded) {
              f2 = 1.0F;
            } else {
              f2 = 0.0F;
            } 
            view1.setAlpha(f2);
            LayoutParams layoutParams1 = (LayoutParams)view1.getLayoutParams();
            layoutParams1.mCollapseDir.setFloatValues(new float[] { 0.0F, f1 });
            layoutParams1.mExpandDir.setFloatValues(new float[] { f1, 0.0F });
            layoutParams1.setAnimationsTarget(view1);
          } 
          if (i != 0) {
            paramInt3 = m - this.mButtonSpacing;
          } else {
            paramInt3 = m + view.getMeasuredHeight() + this.mButtonSpacing;
          } 
        } 
        j--;
      } 
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    measureChildren(paramInt1, paramInt2);
    boolean bool = false;
    this.mMaxButtonWidth = 0;
    this.mMaxButtonHeight = 0;
    int i = 0;
    int j = 0;
    paramInt1 = 0;
    for (paramInt2 = 0; i < this.mButtonsCount; paramInt2 = k) {
      int k;
      int m;
      int n;
      View view = getChildAt(i);
      if (view.getVisibility() == 8) {
        m = j;
        n = paramInt1;
        k = paramInt2;
      } else {
        k = this.mExpandDirection;
        if (k != 0 && k != 1) {
          if (k == 2 || k == 3) {
            paramInt2 += view.getMeasuredWidth();
            this.mMaxButtonHeight = Math.max(this.mMaxButtonHeight, view.getMeasuredHeight());
          } 
        } else {
          this.mMaxButtonWidth = Math.max(this.mMaxButtonWidth, view.getMeasuredWidth());
          paramInt1 += view.getMeasuredHeight();
        } 
        m = j;
        n = paramInt1;
        k = paramInt2;
        if (!expandsHorizontally()) {
          TextView textView = (TextView)view.getTag(2131296528);
          m = j;
          n = paramInt1;
          k = paramInt2;
          if (textView != null) {
            m = Math.max(j, textView.getMeasuredWidth());
            k = paramInt2;
            n = paramInt1;
          } 
        } 
      } 
      i++;
      j = m;
      paramInt1 = n;
    } 
    if (!expandsHorizontally()) {
      i = this.mMaxButtonWidth;
      paramInt2 = bool;
      if (j > 0)
        paramInt2 = this.mLabelsMargin + j; 
      paramInt2 = i + paramInt2;
    } else {
      paramInt1 = this.mMaxButtonHeight;
    } 
    i = this.mExpandDirection;
    if (i != 0 && i != 1) {
      if (i == 2 || i == 3)
        paramInt2 = adjustForOvershoot(paramInt2 + this.mButtonSpacing * (this.mButtonsCount - 1)); 
    } else {
      paramInt1 = adjustForOvershoot(paramInt1 + this.mButtonSpacing * (this.mButtonsCount - 1));
    } 
    setMeasuredDimension(paramInt2, paramInt1);
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    SavedState savedState;
    if (paramParcelable instanceof SavedState) {
      savedState = (SavedState)paramParcelable;
      this.mExpanded = savedState.mExpanded;
      this.mTouchDelegateGroup.setEnabled(this.mExpanded);
      RotatingDrawable rotatingDrawable = this.mRotatingDrawable;
      if (rotatingDrawable != null) {
        float f;
        if (this.mExpanded) {
          f = 135.0F;
        } else {
          f = 0.0F;
        } 
        rotatingDrawable.setRotation(f);
      } 
      super.onRestoreInstanceState(savedState.getSuperState());
    } else {
      super.onRestoreInstanceState((Parcelable)savedState);
    } 
  }
  
  public Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    savedState.mExpanded = this.mExpanded;
    return (Parcelable)savedState;
  }
  
  public void setEnabled(boolean paramBoolean) {
    super.setEnabled(paramBoolean);
    this.mAddButton.setEnabled(paramBoolean);
  }
  
  public void setOnFloatingActionsMenuUpdateListener(OnFloatingActionsMenuUpdateListener paramOnFloatingActionsMenuUpdateListener) {
    this.mListener = paramOnFloatingActionsMenuUpdateListener;
  }
  
  public void toggle() {
    if (this.mExpanded) {
      collapse();
    } else {
      expand();
    } 
  }
  
  private class LayoutParams extends ViewGroup.LayoutParams {
    private boolean animationsSetToPlay;
    
    private ObjectAnimator mCollapseAlpha = new ObjectAnimator();
    
    private ObjectAnimator mCollapseDir = new ObjectAnimator();
    
    private ObjectAnimator mExpandAlpha = new ObjectAnimator();
    
    private ObjectAnimator mExpandDir = new ObjectAnimator();
    
    final FloatingActionsMenu this$0;
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.mExpandDir.setInterpolator((TimeInterpolator)FloatingActionsMenu.sExpandInterpolator);
      this.mExpandAlpha.setInterpolator((TimeInterpolator)FloatingActionsMenu.sAlphaExpandInterpolator);
      this.mCollapseDir.setInterpolator((TimeInterpolator)FloatingActionsMenu.sCollapseInterpolator);
      this.mCollapseAlpha.setInterpolator((TimeInterpolator)FloatingActionsMenu.sCollapseInterpolator);
      this.mCollapseAlpha.setProperty(View.ALPHA);
      this.mCollapseAlpha.setFloatValues(new float[] { 1.0F, 0.0F });
      this.mExpandAlpha.setProperty(View.ALPHA);
      this.mExpandAlpha.setFloatValues(new float[] { 0.0F, 1.0F });
      int i = FloatingActionsMenu.this.mExpandDirection;
      if (i != 0 && i != 1) {
        if (i == 2 || i == 3) {
          this.mCollapseDir.setProperty(View.TRANSLATION_X);
          this.mExpandDir.setProperty(View.TRANSLATION_X);
        } 
      } else {
        this.mCollapseDir.setProperty(View.TRANSLATION_Y);
        this.mExpandDir.setProperty(View.TRANSLATION_Y);
      } 
    }
    
    private void addLayerTypeListener(Animator param1Animator, final View view) {
      param1Animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter(this) {
            final View val$view;
            
            public void onAnimationEnd(Animator param2Animator) {
              view.setLayerType(0, null);
            }
            
            public void onAnimationStart(Animator param2Animator) {
              view.setLayerType(2, null);
            }
          });
    }
    
    public void setAnimationsTarget(View param1View) {
      this.mCollapseAlpha.setTarget(param1View);
      this.mCollapseDir.setTarget(param1View);
      this.mExpandAlpha.setTarget(param1View);
      this.mExpandDir.setTarget(param1View);
      if (!this.animationsSetToPlay) {
        addLayerTypeListener((Animator)this.mExpandDir, param1View);
        addLayerTypeListener((Animator)this.mCollapseDir, param1View);
        FloatingActionsMenu.this.mCollapseAnimation.play((Animator)this.mCollapseAlpha);
        FloatingActionsMenu.this.mCollapseAnimation.play((Animator)this.mCollapseDir);
        FloatingActionsMenu.this.mExpandAnimation.play((Animator)this.mExpandAlpha);
        FloatingActionsMenu.this.mExpandAnimation.play((Animator)this.mExpandDir);
        this.animationsSetToPlay = true;
      } 
    }
  }
  
  class null extends AnimatorListenerAdapter {
    final View val$view;
    
    null(FloatingActionsMenu this$0) {}
    
    public void onAnimationEnd(Animator param1Animator) {
      view.setLayerType(0, null);
    }
    
    public void onAnimationStart(Animator param1Animator) {
      view.setLayerType(2, null);
    }
  }
  
  public static interface OnFloatingActionsMenuUpdateListener {
    void onMenuCollapsed();
    
    void onMenuExpanded();
  }
  
  private static class RotatingDrawable extends LayerDrawable {
    private float mRotation;
    
    public RotatingDrawable(Drawable param1Drawable) {
      super(new Drawable[] { param1Drawable });
    }
    
    public void draw(Canvas param1Canvas) {
      param1Canvas.save();
      param1Canvas.rotate(this.mRotation, getBounds().centerX(), getBounds().centerY());
      super.draw(param1Canvas);
      param1Canvas.restore();
    }
    
    public void setRotation(float param1Float) {
      this.mRotation = param1Float;
      invalidateSelf();
    }
  }
  
  public static class SavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
        public FloatingActionsMenu.SavedState createFromParcel(Parcel param2Parcel) {
          return new FloatingActionsMenu.SavedState(param2Parcel);
        }
        
        public FloatingActionsMenu.SavedState[] newArray(int param2Int) {
          return new FloatingActionsMenu.SavedState[param2Int];
        }
      };
    
    public boolean mExpanded;
    
    private SavedState(Parcel param1Parcel) {
      super(param1Parcel);
      int i = param1Parcel.readInt();
      boolean bool = true;
      if (i != 1)
        bool = false; 
      this.mExpanded = bool;
    }
    
    public SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.mExpanded);
    }
  }
  
  static final class null implements Parcelable.Creator<SavedState> {
    public FloatingActionsMenu.SavedState createFromParcel(Parcel param1Parcel) {
      return new FloatingActionsMenu.SavedState(param1Parcel);
    }
    
    public FloatingActionsMenu.SavedState[] newArray(int param1Int) {
      return new FloatingActionsMenu.SavedState[param1Int];
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/floatingActionButton/FloatingActionsMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */