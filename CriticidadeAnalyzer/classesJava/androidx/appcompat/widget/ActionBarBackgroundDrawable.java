package androidx.appcompat.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

class ActionBarBackgroundDrawable extends Drawable {
  final ActionBarContainer mContainer;
  
  public ActionBarBackgroundDrawable(ActionBarContainer paramActionBarContainer) {
    this.mContainer = paramActionBarContainer;
  }
  
  public void draw(Canvas paramCanvas) {
    Drawable drawable;
    ActionBarContainer actionBarContainer = this.mContainer;
    if (actionBarContainer.mIsSplit) {
      drawable = actionBarContainer.mSplitBackground;
      if (drawable != null)
        drawable.draw(paramCanvas); 
    } else {
      drawable = ((ActionBarContainer)drawable).mBackground;
      if (drawable != null)
        drawable.draw(paramCanvas); 
      ActionBarContainer actionBarContainer1 = this.mContainer;
      Drawable drawable1 = actionBarContainer1.mStackedBackground;
      if (drawable1 != null && actionBarContainer1.mIsStacked)
        drawable1.draw(paramCanvas); 
    } 
  }
  
  public int getOpacity() {
    return 0;
  }
  
  public void getOutline(Outline paramOutline) {
    Drawable drawable;
    ActionBarContainer actionBarContainer = this.mContainer;
    if (actionBarContainer.mIsSplit) {
      drawable = actionBarContainer.mSplitBackground;
      if (drawable != null)
        drawable.getOutline(paramOutline); 
    } else {
      drawable = ((ActionBarContainer)drawable).mBackground;
      if (drawable != null)
        drawable.getOutline(paramOutline); 
    } 
  }
  
  public void setAlpha(int paramInt) {}
  
  public void setColorFilter(ColorFilter paramColorFilter) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/ActionBarBackgroundDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */