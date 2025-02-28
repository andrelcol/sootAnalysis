package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class Constraints extends ViewGroup {
  ConstraintSet myConstraintSet;
  
  protected LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(-2, -2);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (ViewGroup.LayoutParams)new ConstraintLayout.LayoutParams(paramLayoutParams);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  public ConstraintSet getConstraintSet() {
    if (this.myConstraintSet == null)
      this.myConstraintSet = new ConstraintSet(); 
    this.myConstraintSet.clone(this);
    return this.myConstraintSet;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public static class LayoutParams extends ConstraintLayout.LayoutParams {
    public float alpha = 1.0F;
    
    public boolean applyElevation;
    
    public float elevation;
    
    public float rotation;
    
    public float rotationX;
    
    public float rotationY;
    
    public float scaleX;
    
    public float scaleY;
    
    public float transformPivotX;
    
    public float transformPivotY;
    
    public float translationX;
    
    public float translationY;
    
    public float translationZ;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.applyElevation = false;
      this.elevation = 0.0F;
      this.rotation = 0.0F;
      this.rotationX = 0.0F;
      this.rotationY = 0.0F;
      this.scaleX = 1.0F;
      this.scaleY = 1.0F;
      this.transformPivotX = 0.0F;
      this.transformPivotY = 0.0F;
      this.translationX = 0.0F;
      this.translationY = 0.0F;
      this.translationZ = 0.0F;
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      byte b = 0;
      this.applyElevation = false;
      this.elevation = 0.0F;
      this.rotation = 0.0F;
      this.rotationX = 0.0F;
      this.rotationY = 0.0F;
      this.scaleX = 1.0F;
      this.scaleY = 1.0F;
      this.transformPivotX = 0.0F;
      this.transformPivotY = 0.0F;
      this.translationX = 0.0F;
      this.translationY = 0.0F;
      this.translationZ = 0.0F;
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R$styleable.ConstraintSet);
      int i = typedArray.getIndexCount();
      while (b < i) {
        int j = typedArray.getIndex(b);
        if (j == R$styleable.ConstraintSet_android_alpha) {
          this.alpha = typedArray.getFloat(j, this.alpha);
        } else if (j == R$styleable.ConstraintSet_android_elevation) {
          this.elevation = typedArray.getFloat(j, this.elevation);
          this.applyElevation = true;
        } else if (j == R$styleable.ConstraintSet_android_rotationX) {
          this.rotationX = typedArray.getFloat(j, this.rotationX);
        } else if (j == R$styleable.ConstraintSet_android_rotationY) {
          this.rotationY = typedArray.getFloat(j, this.rotationY);
        } else if (j == R$styleable.ConstraintSet_android_rotation) {
          this.rotation = typedArray.getFloat(j, this.rotation);
        } else if (j == R$styleable.ConstraintSet_android_scaleX) {
          this.scaleX = typedArray.getFloat(j, this.scaleX);
        } else if (j == R$styleable.ConstraintSet_android_scaleY) {
          this.scaleY = typedArray.getFloat(j, this.scaleY);
        } else if (j == R$styleable.ConstraintSet_android_transformPivotX) {
          this.transformPivotX = typedArray.getFloat(j, this.transformPivotX);
        } else if (j == R$styleable.ConstraintSet_android_transformPivotY) {
          this.transformPivotY = typedArray.getFloat(j, this.transformPivotY);
        } else if (j == R$styleable.ConstraintSet_android_translationX) {
          this.translationX = typedArray.getFloat(j, this.translationX);
        } else if (j == R$styleable.ConstraintSet_android_translationY) {
          this.translationY = typedArray.getFloat(j, this.translationY);
        } else if (j == R$styleable.ConstraintSet_android_translationZ) {
          this.translationX = typedArray.getFloat(j, this.translationZ);
        } 
        b++;
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/widget/Constraints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */