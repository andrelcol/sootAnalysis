package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

abstract class AbsActionBarView extends ViewGroup {
  protected ActionMenuPresenter mActionMenuPresenter;
  
  protected int mContentHeight;
  
  private boolean mEatingHover;
  
  private boolean mEatingTouch;
  
  protected ActionMenuView mMenuView;
  
  protected final Context mPopupContext;
  
  protected final VisibilityAnimListener mVisAnimListener = new VisibilityAnimListener();
  
  protected ViewPropertyAnimatorCompat mVisibilityAnim;
  
  AbsActionBarView(Context paramContext) {
    this(paramContext, null);
  }
  
  AbsActionBarView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  AbsActionBarView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TypedValue typedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true)) {
      paramInt = typedValue.resourceId;
      if (paramInt != 0) {
        this.mPopupContext = (Context)new ContextThemeWrapper(paramContext, paramInt);
        return;
      } 
    } 
    this.mPopupContext = paramContext;
  }
  
  protected static int next(int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramBoolean) {
      paramInt1 -= paramInt2;
    } else {
      paramInt1 += paramInt2;
    } 
    return paramInt1;
  }
  
  public int getAnimatedVisibility() {
    return (this.mVisibilityAnim != null) ? this.mVisAnimListener.mFinalVisibility : getVisibility();
  }
  
  public int getContentHeight() {
    return this.mContentHeight;
  }
  
  protected int measureChildView(View paramView, int paramInt1, int paramInt2, int paramInt3) {
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, -2147483648), paramInt2);
    return Math.max(0, paramInt1 - paramView.getMeasuredWidth() - paramInt3);
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    TypedArray typedArray = getContext().obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
    setContentHeight(typedArray.getLayoutDimension(R.styleable.ActionBar_height, 0));
    typedArray.recycle();
    ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
    if (actionMenuPresenter != null)
      actionMenuPresenter.onConfigurationChanged(paramConfiguration); 
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getActionMasked();
    if (i == 9)
      this.mEatingHover = false; 
    if (!this.mEatingHover) {
      boolean bool = super.onHoverEvent(paramMotionEvent);
      if (i == 9 && !bool)
        this.mEatingHover = true; 
    } 
    if (i == 10 || i == 3)
      this.mEatingHover = false; 
    return true;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getActionMasked();
    if (i == 0)
      this.mEatingTouch = false; 
    if (!this.mEatingTouch) {
      boolean bool = super.onTouchEvent(paramMotionEvent);
      if (i == 0 && !bool)
        this.mEatingTouch = true; 
    } 
    if (i == 1 || i == 3)
      this.mEatingTouch = false; 
    return true;
  }
  
  protected int positionChild(View paramView, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    int i = paramView.getMeasuredWidth();
    int j = paramView.getMeasuredHeight();
    paramInt2 += (paramInt3 - j) / 2;
    if (paramBoolean) {
      paramView.layout(paramInt1 - i, paramInt2, paramInt1, j + paramInt2);
    } else {
      paramView.layout(paramInt1, paramInt2, paramInt1 + i, j + paramInt2);
    } 
    paramInt1 = i;
    if (paramBoolean)
      paramInt1 = -i; 
    return paramInt1;
  }
  
  public abstract void setContentHeight(int paramInt);
  
  public void setVisibility(int paramInt) {
    if (paramInt != getVisibility()) {
      ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVisibilityAnim;
      if (viewPropertyAnimatorCompat != null)
        viewPropertyAnimatorCompat.cancel(); 
      super.setVisibility(paramInt);
    } 
  }
  
  public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int paramInt, long paramLong) {
    ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVisibilityAnim;
    if (viewPropertyAnimatorCompat != null)
      viewPropertyAnimatorCompat.cancel(); 
    if (paramInt == 0) {
      if (getVisibility() != 0)
        setAlpha(0.0F); 
      viewPropertyAnimatorCompat = ViewCompat.animate((View)this);
      viewPropertyAnimatorCompat.alpha(1.0F);
      viewPropertyAnimatorCompat.setDuration(paramLong);
      VisibilityAnimListener visibilityAnimListener1 = this.mVisAnimListener;
      visibilityAnimListener1.withFinalVisibility(viewPropertyAnimatorCompat, paramInt);
      viewPropertyAnimatorCompat.setListener(visibilityAnimListener1);
      return viewPropertyAnimatorCompat;
    } 
    viewPropertyAnimatorCompat = ViewCompat.animate((View)this);
    viewPropertyAnimatorCompat.alpha(0.0F);
    viewPropertyAnimatorCompat.setDuration(paramLong);
    VisibilityAnimListener visibilityAnimListener = this.mVisAnimListener;
    visibilityAnimListener.withFinalVisibility(viewPropertyAnimatorCompat, paramInt);
    viewPropertyAnimatorCompat.setListener(visibilityAnimListener);
    return viewPropertyAnimatorCompat;
  }
  
  protected class VisibilityAnimListener implements ViewPropertyAnimatorListener {
    private boolean mCanceled = false;
    
    int mFinalVisibility;
    
    final AbsActionBarView this$0;
    
    public void onAnimationCancel(View param1View) {
      this.mCanceled = true;
    }
    
    public void onAnimationEnd(View param1View) {
      if (this.mCanceled)
        return; 
      AbsActionBarView absActionBarView = AbsActionBarView.this;
      absActionBarView.mVisibilityAnim = null;
      absActionBarView.setVisibility(this.mFinalVisibility);
    }
    
    public void onAnimationStart(View param1View) {
      AbsActionBarView.this.setVisibility(0);
      this.mCanceled = false;
    }
    
    public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat param1ViewPropertyAnimatorCompat, int param1Int) {
      AbsActionBarView.this.mVisibilityAnim = param1ViewPropertyAnimatorCompat;
      this.mFinalVisibility = param1Int;
      return this;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/AbsActionBarView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */