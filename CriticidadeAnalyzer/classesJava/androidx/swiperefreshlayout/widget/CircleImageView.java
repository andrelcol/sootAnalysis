package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;

class CircleImageView extends ImageView {
  private Animation.AnimationListener mListener;
  
  int mShadowRadius;
  
  CircleImageView(Context paramContext, int paramInt) {
    super(paramContext);
    ShapeDrawable shapeDrawable;
    float f = (getContext().getResources().getDisplayMetrics()).density;
    int j = (int)(1.75F * f);
    int i = (int)(0.0F * f);
    this.mShadowRadius = (int)(3.5F * f);
    if (elevationSupported()) {
      shapeDrawable = new ShapeDrawable((Shape)new OvalShape());
      ViewCompat.setElevation((View)this, f * 4.0F);
    } else {
      shapeDrawable = new ShapeDrawable((Shape)new OvalShadow(this.mShadowRadius));
      setLayerType(1, shapeDrawable.getPaint());
      shapeDrawable.getPaint().setShadowLayer(this.mShadowRadius, i, j, 503316480);
      i = this.mShadowRadius;
      setPadding(i, i, i, i);
    } 
    shapeDrawable.getPaint().setColor(paramInt);
    ViewCompat.setBackground((View)this, (Drawable)shapeDrawable);
  }
  
  private boolean elevationSupported() {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 21) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void onAnimationEnd() {
    super.onAnimationEnd();
    Animation.AnimationListener animationListener = this.mListener;
    if (animationListener != null)
      animationListener.onAnimationEnd(getAnimation()); 
  }
  
  public void onAnimationStart() {
    super.onAnimationStart();
    Animation.AnimationListener animationListener = this.mListener;
    if (animationListener != null)
      animationListener.onAnimationStart(getAnimation()); 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    if (!elevationSupported())
      setMeasuredDimension(getMeasuredWidth() + this.mShadowRadius * 2, getMeasuredHeight() + this.mShadowRadius * 2); 
  }
  
  public void setAnimationListener(Animation.AnimationListener paramAnimationListener) {
    this.mListener = paramAnimationListener;
  }
  
  public void setBackgroundColor(int paramInt) {
    if (getBackground() instanceof ShapeDrawable)
      ((ShapeDrawable)getBackground()).getPaint().setColor(paramInt); 
  }
  
  private class OvalShadow extends OvalShape {
    private RadialGradient mRadialGradient;
    
    private Paint mShadowPaint = new Paint();
    
    final CircleImageView this$0;
    
    OvalShadow(int param1Int) {
      CircleImageView.this.mShadowRadius = param1Int;
      updateRadialGradient((int)rect().width());
    }
    
    private void updateRadialGradient(int param1Int) {
      float f1 = (param1Int / 2);
      float f2 = CircleImageView.this.mShadowRadius;
      Shader.TileMode tileMode = Shader.TileMode.CLAMP;
      this.mRadialGradient = new RadialGradient(f1, f1, f2, new int[] { 1023410176, 0 }, null, tileMode);
      this.mShadowPaint.setShader((Shader)this.mRadialGradient);
    }
    
    public void draw(Canvas param1Canvas, Paint param1Paint) {
      int j = CircleImageView.this.getWidth();
      int i = CircleImageView.this.getHeight();
      j /= 2;
      float f1 = j;
      float f2 = (i / 2);
      param1Canvas.drawCircle(f1, f2, f1, this.mShadowPaint);
      param1Canvas.drawCircle(f1, f2, (j - CircleImageView.this.mShadowRadius), param1Paint);
    }
    
    protected void onResize(float param1Float1, float param1Float2) {
      super.onResize(param1Float1, param1Float2);
      updateRadialGradient((int)param1Float1);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/swiperefreshlayout/widget/CircleImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */