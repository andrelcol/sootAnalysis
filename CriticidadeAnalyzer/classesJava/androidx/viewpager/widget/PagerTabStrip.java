package androidx.viewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.content.ContextCompat;

public class PagerTabStrip extends PagerTitleStrip {
  private boolean mDrawFullUnderline = false;
  
  private boolean mDrawFullUnderlineSet = false;
  
  private int mFullUnderlineHeight;
  
  private boolean mIgnoreTap;
  
  private int mIndicatorColor = this.mTextColor;
  
  private int mIndicatorHeight;
  
  private float mInitialMotionX;
  
  private float mInitialMotionY;
  
  private int mMinPaddingBottom;
  
  private int mMinStripHeight;
  
  private int mMinTextSpacing;
  
  private int mTabAlpha = 255;
  
  private int mTabPadding;
  
  private final Paint mTabPaint = new Paint();
  
  private final Rect mTempRect = new Rect();
  
  private int mTouchSlop;
  
  public PagerTabStrip(Context paramContext) {
    this(paramContext, null);
  }
  
  public PagerTabStrip(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    this.mTabPaint.setColor(this.mIndicatorColor);
    float f = (paramContext.getResources().getDisplayMetrics()).density;
    this.mIndicatorHeight = (int)(3.0F * f + 0.5F);
    this.mMinPaddingBottom = (int)(6.0F * f + 0.5F);
    this.mMinTextSpacing = (int)(64.0F * f);
    this.mTabPadding = (int)(16.0F * f + 0.5F);
    this.mFullUnderlineHeight = (int)(1.0F * f + 0.5F);
    this.mMinStripHeight = (int)(f * 32.0F + 0.5F);
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    setTextSpacing(getTextSpacing());
    setWillNotDraw(false);
    this.mPrevText.setFocusable(true);
    this.mPrevText.setOnClickListener(new View.OnClickListener() {
          final PagerTabStrip this$0;
          
          public void onClick(View param1View) {
            ViewPager viewPager = PagerTabStrip.this.mPager;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
          }
        });
    this.mNextText.setFocusable(true);
    this.mNextText.setOnClickListener(new View.OnClickListener() {
          final PagerTabStrip this$0;
          
          public void onClick(View param1View) {
            ViewPager viewPager = PagerTabStrip.this.mPager;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
          }
        });
    if (getBackground() == null)
      this.mDrawFullUnderline = true; 
  }
  
  public boolean getDrawFullUnderline() {
    return this.mDrawFullUnderline;
  }
  
  int getMinHeight() {
    return Math.max(super.getMinHeight(), this.mMinStripHeight);
  }
  
  public int getTabIndicatorColor() {
    return this.mIndicatorColor;
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    int k = getHeight();
    int n = this.mCurrText.getLeft();
    int i1 = this.mTabPadding;
    int j = this.mCurrText.getRight();
    int i = this.mTabPadding;
    int m = this.mIndicatorHeight;
    this.mTabPaint.setColor(this.mTabAlpha << 24 | this.mIndicatorColor & 0xFFFFFF);
    float f4 = (n - i1);
    float f2 = (k - m);
    float f3 = (j + i);
    float f1 = k;
    paramCanvas.drawRect(f4, f2, f3, f1, this.mTabPaint);
    if (this.mDrawFullUnderline) {
      this.mTabPaint.setColor(0xFF000000 | this.mIndicatorColor & 0xFFFFFF);
      paramCanvas.drawRect(getPaddingLeft(), (k - this.mFullUnderlineHeight), (getWidth() - getPaddingRight()), f1, this.mTabPaint);
    } 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    int i = paramMotionEvent.getAction();
    if (i != 0 && this.mIgnoreTap)
      return false; 
    float f2 = paramMotionEvent.getX();
    float f1 = paramMotionEvent.getY();
    if (i != 0) {
      if (i != 1) {
        if (i == 2 && (Math.abs(f2 - this.mInitialMotionX) > this.mTouchSlop || Math.abs(f1 - this.mInitialMotionY) > this.mTouchSlop))
          this.mIgnoreTap = true; 
      } else if (f2 < (this.mCurrText.getLeft() - this.mTabPadding)) {
        ViewPager viewPager = this.mPager;
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
      } else if (f2 > (this.mCurrText.getRight() + this.mTabPadding)) {
        ViewPager viewPager = this.mPager;
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
      } 
    } else {
      this.mInitialMotionX = f2;
      this.mInitialMotionY = f1;
      this.mIgnoreTap = false;
    } 
    return true;
  }
  
  public void setBackgroundColor(int paramInt) {
    super.setBackgroundColor(paramInt);
    if (!this.mDrawFullUnderlineSet) {
      boolean bool;
      if ((paramInt & 0xFF000000) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mDrawFullUnderline = bool;
    } 
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    super.setBackgroundDrawable(paramDrawable);
    if (!this.mDrawFullUnderlineSet) {
      boolean bool;
      if (paramDrawable == null) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mDrawFullUnderline = bool;
    } 
  }
  
  public void setBackgroundResource(int paramInt) {
    super.setBackgroundResource(paramInt);
    if (!this.mDrawFullUnderlineSet) {
      boolean bool;
      if (paramInt == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mDrawFullUnderline = bool;
    } 
  }
  
  public void setDrawFullUnderline(boolean paramBoolean) {
    this.mDrawFullUnderline = paramBoolean;
    this.mDrawFullUnderlineSet = true;
    invalidate();
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int j = this.mMinPaddingBottom;
    int i = paramInt4;
    if (paramInt4 < j)
      i = j; 
    super.setPadding(paramInt1, paramInt2, paramInt3, i);
  }
  
  public void setTabIndicatorColor(int paramInt) {
    this.mIndicatorColor = paramInt;
    this.mTabPaint.setColor(this.mIndicatorColor);
    invalidate();
  }
  
  public void setTabIndicatorColorResource(int paramInt) {
    setTabIndicatorColor(ContextCompat.getColor(getContext(), paramInt));
  }
  
  public void setTextSpacing(int paramInt) {
    int j = this.mMinTextSpacing;
    int i = paramInt;
    if (paramInt < j)
      i = j; 
    super.setTextSpacing(i);
  }
  
  void updateTextPositions(int paramInt, float paramFloat, boolean paramBoolean) {
    Rect rect = this.mTempRect;
    int j = getHeight();
    int k = this.mCurrText.getLeft();
    int m = this.mTabPadding;
    int n = this.mCurrText.getRight();
    int i = this.mTabPadding;
    int i1 = j - this.mIndicatorHeight;
    rect.set(k - m, i1, n + i, j);
    super.updateTextPositions(paramInt, paramFloat, paramBoolean);
    this.mTabAlpha = (int)(Math.abs(paramFloat - 0.5F) * 2.0F * 255.0F);
    rect.union(this.mCurrText.getLeft() - this.mTabPadding, i1, this.mCurrText.getRight() + this.mTabPadding, j);
    invalidate(rect);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/viewpager/widget/PagerTabStrip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */