package com.roadtrack.onstar.floatingActionButton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import com.roadtrack.onstar.R;

public class AddFloatingActionButton extends FloatingActionButton {
  int mPlusColor;
  
  public AddFloatingActionButton(Context paramContext) {
    this(paramContext, null);
  }
  
  public AddFloatingActionButton(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  Drawable getIconDrawable() {
    final float iconSize = getDimension(2131099752);
    final float iconHalfSize = f3 / 2.0F;
    float f1 = getDimension(2131099754);
    final float plusHalfStroke = getDimension(2131099755) / 2.0F;
    ShapeDrawable shapeDrawable = new ShapeDrawable(new Shape(this) {
          final float val$iconHalfSize;
          
          final float val$iconSize;
          
          final float val$plusHalfStroke;
          
          final float val$plusOffset;
          
          public void draw(Canvas param1Canvas, Paint param1Paint) {
            float f2 = plusOffset;
            float f1 = iconHalfSize;
            float f3 = plusHalfStroke;
            param1Canvas.drawRect(f2, f1 - f3, iconSize - f2, f1 + f3, param1Paint);
            f2 = iconHalfSize;
            f3 = plusHalfStroke;
            f1 = plusOffset;
            param1Canvas.drawRect(f2 - f3, f1, f2 + f3, iconSize - f1, param1Paint);
          }
        });
    Paint paint = shapeDrawable.getPaint();
    paint.setColor(this.mPlusColor);
    paint.setStyle(Paint.Style.FILL);
    paint.setAntiAlias(true);
    return (Drawable)shapeDrawable;
  }
  
  public int getPlusColor() {
    return this.mPlusColor;
  }
  
  void init(Context paramContext, AttributeSet paramAttributeSet) {
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AddFloatingActionButton, 0, 0);
    this.mPlusColor = typedArray.getColor(0, getColor(17170443));
    typedArray.recycle();
    super.init(paramContext, paramAttributeSet);
  }
  
  public void setIcon(int paramInt) {
    throw new UnsupportedOperationException("Use FloatingActionButton if you want to use custom icon");
  }
  
  public void setPlusColor(int paramInt) {
    if (this.mPlusColor != paramInt) {
      this.mPlusColor = paramInt;
      updateBackground();
    } 
  }
  
  public void setPlusColorResId(int paramInt) {
    setPlusColor(getColor(paramInt));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/floatingActionButton/AddFloatingActionButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */