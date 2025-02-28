package com.roadtrack.onstar.tomtom.utilities;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.roadtrack.onstar.utils.Utilities;

public class SaveImageCache extends Drawable {
  private int mBoxHeight;
  
  private Rect mBoxPadding = new Rect();
  
  private int mBoxWidth;
  
  private int mColor;
  
  private Paint mPaint;
  
  private Path mPointer;
  
  private int mPointerHeight;
  
  public SaveImageCache(Activity paramActivity, int paramInt) {
    Utilities.getpath(paramActivity);
    setPointerAlignment(paramInt);
    setBubble();
  }
  
  private void setBubble() {
    this.mPaint = new Paint();
    this.mPaint.setAntiAlias(true);
    this.mColor = -1;
    this.mPaint.setColor(this.mColor);
    setPointerWidth(40);
    setPointerHeight(40);
  }
  
  public void draw(Canvas paramCanvas) {
    new RectF(0.0F, 0.0F, this.mBoxWidth, this.mBoxHeight);
    paramCanvas.drawPath(this.mPointer, this.mPaint);
  }
  
  public int getOpacity() {
    return 255;
  }
  
  public boolean getPadding(Rect paramRect) {
    paramRect.set(this.mBoxPadding);
    paramRect.bottom += this.mPointerHeight;
    return true;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    this.mBoxWidth = paramRect.width();
    this.mBoxHeight = getBounds().height() - this.mPointerHeight;
    super.onBoundsChange(paramRect);
  }
  
  public void setAlpha(int paramInt) {}
  
  public void setColorFilter(ColorFilter paramColorFilter) {}
  
  public void setPointerAlignment(int paramInt) {
    if (paramInt < 0 || paramInt > 3)
      Utilities.escribeArchivo("BubbleDrawable", "Image", "Invalid pointerAlignment argument"); 
  }
  
  public void setPointerHeight(int paramInt) {
    this.mPointerHeight = paramInt;
  }
  
  public void setPointerWidth(int paramInt) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/tomtom/utilities/SaveImageCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */