package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat {
  Runnable mEndAction = null;
  
  int mOldLayerType = -1;
  
  Runnable mStartAction = null;
  
  private WeakReference<View> mView;
  
  ViewPropertyAnimatorCompat(View paramView) {
    this.mView = new WeakReference<View>(paramView);
  }
  
  private void setListenerInternal(final View view, final ViewPropertyAnimatorListener listener) {
    if (listener != null) {
      view.animate().setListener((Animator.AnimatorListener)new AnimatorListenerAdapter(this) {
            final ViewPropertyAnimatorListener val$listener;
            
            final View val$view;
            
            public void onAnimationCancel(Animator param1Animator) {
              listener.onAnimationCancel(view);
            }
            
            public void onAnimationEnd(Animator param1Animator) {
              listener.onAnimationEnd(view);
            }
            
            public void onAnimationStart(Animator param1Animator) {
              listener.onAnimationStart(view);
            }
          });
    } else {
      view.animate().setListener(null);
    } 
  }
  
  public ViewPropertyAnimatorCompat alpha(float paramFloat) {
    View view = this.mView.get();
    if (view != null)
      view.animate().alpha(paramFloat); 
    return this;
  }
  
  public void cancel() {
    View view = this.mView.get();
    if (view != null)
      view.animate().cancel(); 
  }
  
  public long getDuration() {
    View view = this.mView.get();
    return (view != null) ? view.animate().getDuration() : 0L;
  }
  
  public ViewPropertyAnimatorCompat setDuration(long paramLong) {
    View view = this.mView.get();
    if (view != null)
      view.animate().setDuration(paramLong); 
    return this;
  }
  
  public ViewPropertyAnimatorCompat setInterpolator(Interpolator paramInterpolator) {
    View view = this.mView.get();
    if (view != null)
      view.animate().setInterpolator((TimeInterpolator)paramInterpolator); 
    return this;
  }
  
  public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener paramViewPropertyAnimatorListener) {
    View view = this.mView.get();
    if (view != null)
      if (Build.VERSION.SDK_INT >= 16) {
        setListenerInternal(view, paramViewPropertyAnimatorListener);
      } else {
        view.setTag(2113929216, paramViewPropertyAnimatorListener);
        setListenerInternal(view, new ViewPropertyAnimatorListenerApi14(this));
      }  
    return this;
  }
  
  public ViewPropertyAnimatorCompat setStartDelay(long paramLong) {
    View view = this.mView.get();
    if (view != null)
      view.animate().setStartDelay(paramLong); 
    return this;
  }
  
  public ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener listener) {
    final View view = this.mView.get();
    if (view != null && Build.VERSION.SDK_INT >= 19) {
      ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
      if (listener != null)
        animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener(this) {
            final ViewPropertyAnimatorUpdateListener val$listener;
            
            final View val$view;
            
            public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
              listener.onAnimationUpdate(view);
            }
          }; 
      view.animate().setUpdateListener(animatorUpdateListener);
    } 
    return this;
  }
  
  public void start() {
    View view = this.mView.get();
    if (view != null)
      view.animate().start(); 
  }
  
  public ViewPropertyAnimatorCompat translationY(float paramFloat) {
    View view = this.mView.get();
    if (view != null)
      view.animate().translationY(paramFloat); 
    return this;
  }
  
  static class ViewPropertyAnimatorListenerApi14 implements ViewPropertyAnimatorListener {
    boolean mAnimEndCalled;
    
    ViewPropertyAnimatorCompat mVpa;
    
    ViewPropertyAnimatorListenerApi14(ViewPropertyAnimatorCompat param1ViewPropertyAnimatorCompat) {
      this.mVpa = param1ViewPropertyAnimatorCompat;
    }
    
    public void onAnimationCancel(View param1View) {
      Object object = param1View.getTag(2113929216);
      if (object instanceof ViewPropertyAnimatorListener) {
        object = object;
      } else {
        object = null;
      } 
      if (object != null)
        object.onAnimationCancel(param1View); 
    }
    
    public void onAnimationEnd(View param1View) {
      int i = this.mVpa.mOldLayerType;
      ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
      if (i > -1) {
        param1View.setLayerType(i, null);
        this.mVpa.mOldLayerType = -1;
      } 
      if (Build.VERSION.SDK_INT >= 16 || !this.mAnimEndCalled) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVpa;
        Runnable runnable = viewPropertyAnimatorCompat.mEndAction;
        if (runnable != null) {
          viewPropertyAnimatorCompat.mEndAction = null;
          runnable.run();
        } 
        Object object = param1View.getTag(2113929216);
        if (object instanceof ViewPropertyAnimatorListener)
          viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)object; 
        if (viewPropertyAnimatorListener != null)
          viewPropertyAnimatorListener.onAnimationEnd(param1View); 
        this.mAnimEndCalled = true;
      } 
    }
    
    public void onAnimationStart(View param1View) {
      this.mAnimEndCalled = false;
      int i = this.mVpa.mOldLayerType;
      ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
      if (i > -1)
        param1View.setLayerType(2, null); 
      ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mVpa;
      Runnable runnable = viewPropertyAnimatorCompat.mStartAction;
      if (runnable != null) {
        viewPropertyAnimatorCompat.mStartAction = null;
        runnable.run();
      } 
      Object object = param1View.getTag(2113929216);
      if (object instanceof ViewPropertyAnimatorListener)
        viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)object; 
      if (viewPropertyAnimatorListener != null)
        viewPropertyAnimatorListener.onAnimationStart(param1View); 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/view/ViewPropertyAnimatorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */