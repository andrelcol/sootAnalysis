package com.roadtrack.onstar.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ScrollViewExt extends ScrollView {
  private ScrollViewListener scrollViewListener = null;
  
  public ScrollViewExt(Context paramContext) {
    super(paramContext);
  }
  
  public ScrollViewExt(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public ScrollViewExt(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    ScrollViewListener scrollViewListener = this.scrollViewListener;
    if (scrollViewListener != null)
      scrollViewListener.onScrollChanged(this, paramInt1, paramInt2, paramInt3, paramInt4); 
  }
  
  public void setScrollViewListener(ScrollViewListener paramScrollViewListener) {
    this.scrollViewListener = paramScrollViewListener;
  }
  
  public static interface ScrollViewListener {
    void onScrollChanged(ScrollViewExt param1ScrollViewExt, int param1Int1, int param1Int2, int param1Int3, int param1Int4);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/ScrollViewExt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */