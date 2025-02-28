package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.R;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;

public class LinearLayoutCompat extends ViewGroup {
  private boolean mBaselineAligned = true;
  
  private int mBaselineAlignedChildIndex = -1;
  
  private int mBaselineChildTop = 0;
  
  private Drawable mDivider;
  
  private int mDividerHeight;
  
  private int mDividerPadding;
  
  private int mDividerWidth;
  
  private int mGravity = 8388659;
  
  private int[] mMaxAscent;
  
  private int[] mMaxDescent;
  
  private int mOrientation;
  
  private int mShowDividers;
  
  private int mTotalLength;
  
  private boolean mUseLargestChild;
  
  private float mWeightSum;
  
  public LinearLayoutCompat(Context paramContext) {
    this(paramContext, null);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.LinearLayoutCompat, paramInt, 0);
    paramInt = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
    if (paramInt >= 0)
      setOrientation(paramInt); 
    paramInt = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
    if (paramInt >= 0)
      setGravity(paramInt); 
    boolean bool = tintTypedArray.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
    if (!bool)
      setBaselineAligned(bool); 
    this.mWeightSum = tintTypedArray.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0F);
    this.mBaselineAlignedChildIndex = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
    this.mUseLargestChild = tintTypedArray.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
    setDividerDrawable(tintTypedArray.getDrawable(R.styleable.LinearLayoutCompat_divider));
    this.mShowDividers = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
    this.mDividerPadding = tintTypedArray.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
    tintTypedArray.recycle();
  }
  
  private void forceUniformHeight(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
    for (byte b = 0; b < paramInt1; b++) {
      View view = getVirtualChildAt(b);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.height == -1) {
          int j = layoutParams.width;
          layoutParams.width = view.getMeasuredWidth();
          measureChildWithMargins(view, paramInt2, 0, i, 0);
          layoutParams.width = j;
        } 
      } 
    } 
  }
  
  private void forceUniformWidth(int paramInt1, int paramInt2) {
    int i = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
    for (byte b = 0; b < paramInt1; b++) {
      View view = getVirtualChildAt(b);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.width == -1) {
          int j = layoutParams.height;
          layoutParams.height = view.getMeasuredHeight();
          measureChildWithMargins(view, i, 0, paramInt2, 0);
          layoutParams.height = j;
        } 
      } 
    } 
  }
  
  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    paramView.layout(paramInt1, paramInt2, paramInt3 + paramInt1, paramInt4 + paramInt2);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void drawDividersHorizontal(Canvas paramCanvas) {
    int j = getVirtualChildCount();
    boolean bool = ViewUtils.isLayoutRtl((View)this);
    int i;
    for (i = 0; i < j; i++) {
      View view = getVirtualChildAt(i);
      if (view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
        int k;
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool) {
          k = view.getRight() + layoutParams.rightMargin;
        } else {
          k = view.getLeft() - layoutParams.leftMargin - this.mDividerWidth;
        } 
        drawVerticalDivider(paramCanvas, k);
      } 
    } 
    if (hasDividerBeforeChildAt(j)) {
      View view = getVirtualChildAt(j - 1);
      if (view == null) {
        if (bool) {
          i = getPaddingLeft();
        } else {
          i = getWidth() - getPaddingRight();
          int k = this.mDividerWidth;
          i -= k;
        } 
      } else {
        int k;
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool) {
          i = view.getLeft() - layoutParams.leftMargin;
          k = this.mDividerWidth;
        } else {
          i = view.getRight() + layoutParams.rightMargin;
          drawVerticalDivider(paramCanvas, i);
        } 
        i -= k;
      } 
    } else {
      return;
    } 
    drawVerticalDivider(paramCanvas, i);
  }
  
  void drawDividersVertical(Canvas paramCanvas) {
    int j = getVirtualChildCount();
    int i;
    for (i = 0; i < j; i++) {
      View view = getVirtualChildAt(i);
      if (view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        drawHorizontalDivider(paramCanvas, view.getTop() - layoutParams.topMargin - this.mDividerHeight);
      } 
    } 
    if (hasDividerBeforeChildAt(j)) {
      View view = getVirtualChildAt(j - 1);
      if (view == null) {
        i = getHeight() - getPaddingBottom() - this.mDividerHeight;
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        i = view.getBottom() + layoutParams.bottomMargin;
      } 
      drawHorizontalDivider(paramCanvas, i);
    } 
  }
  
  void drawHorizontalDivider(Canvas paramCanvas, int paramInt) {
    this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, paramInt, getWidth() - getPaddingRight() - this.mDividerPadding, this.mDividerHeight + paramInt);
    this.mDivider.draw(paramCanvas);
  }
  
  void drawVerticalDivider(Canvas paramCanvas, int paramInt) {
    this.mDivider.setBounds(paramInt, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + paramInt, getHeight() - getPaddingBottom() - this.mDividerPadding);
    this.mDivider.draw(paramCanvas);
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    int i = this.mOrientation;
    return (i == 0) ? new LayoutParams(-2, -2) : ((i == 1) ? new LayoutParams(-1, -2) : null);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getBaseline() {
    if (this.mBaselineAlignedChildIndex < 0)
      return super.getBaseline(); 
    int i = getChildCount();
    int j = this.mBaselineAlignedChildIndex;
    if (i > j) {
      View view = getChildAt(j);
      int k = view.getBaseline();
      if (k == -1) {
        if (this.mBaselineAlignedChildIndex == 0)
          return -1; 
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
      } 
      j = this.mBaselineChildTop;
      i = j;
      if (this.mOrientation == 1) {
        int m = this.mGravity & 0x70;
        i = j;
        if (m != 48)
          if (m != 16) {
            if (m != 80) {
              i = j;
            } else {
              i = getBottom() - getTop() - getPaddingBottom() - this.mTotalLength;
            } 
          } else {
            i = j + (getBottom() - getTop() - getPaddingTop() - getPaddingBottom() - this.mTotalLength) / 2;
          }  
      } 
      return i + ((LayoutParams)view.getLayoutParams()).topMargin + k;
    } 
    throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
  }
  
  public int getBaselineAlignedChildIndex() {
    return this.mBaselineAlignedChildIndex;
  }
  
  int getChildrenSkipCount(View paramView, int paramInt) {
    return 0;
  }
  
  public Drawable getDividerDrawable() {
    return this.mDivider;
  }
  
  public int getDividerPadding() {
    return this.mDividerPadding;
  }
  
  public int getDividerWidth() {
    return this.mDividerWidth;
  }
  
  public int getGravity() {
    return this.mGravity;
  }
  
  int getLocationOffset(View paramView) {
    return 0;
  }
  
  int getNextLocationOffset(View paramView) {
    return 0;
  }
  
  public int getOrientation() {
    return this.mOrientation;
  }
  
  public int getShowDividers() {
    return this.mShowDividers;
  }
  
  View getVirtualChildAt(int paramInt) {
    return getChildAt(paramInt);
  }
  
  int getVirtualChildCount() {
    return getChildCount();
  }
  
  public float getWeightSum() {
    return this.mWeightSum;
  }
  
  protected boolean hasDividerBeforeChildAt(int paramInt) {
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1 = false;
    if (paramInt == 0) {
      if ((this.mShowDividers & 0x1) != 0)
        bool1 = true; 
      return bool1;
    } 
    if (paramInt == getChildCount()) {
      bool1 = bool3;
      if ((this.mShowDividers & 0x4) != 0)
        bool1 = true; 
      return bool1;
    } 
    bool1 = bool2;
    if ((this.mShowDividers & 0x2) != 0) {
      paramInt--;
      while (true) {
        bool1 = bool2;
        if (paramInt >= 0) {
          if (getChildAt(paramInt).getVisibility() != 8) {
            bool1 = true;
            break;
          } 
          paramInt--;
          continue;
        } 
        break;
      } 
    } 
    return bool1;
  }
  
  void layoutHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    byte b1;
    byte b2;
    boolean bool2 = ViewUtils.isLayoutRtl((View)this);
    int j = getPaddingTop();
    int n = paramInt4 - paramInt2;
    int m = getPaddingBottom();
    int k = getPaddingBottom();
    int i = getVirtualChildCount();
    paramInt4 = this.mGravity;
    paramInt2 = paramInt4 & 0x70;
    boolean bool1 = this.mBaselineAligned;
    int[] arrayOfInt1 = this.mMaxAscent;
    int[] arrayOfInt2 = this.mMaxDescent;
    paramInt4 = GravityCompat.getAbsoluteGravity(0x800007 & paramInt4, ViewCompat.getLayoutDirection((View)this));
    if (paramInt4 != 1) {
      if (paramInt4 != 5) {
        paramInt1 = getPaddingLeft();
      } else {
        paramInt1 = getPaddingLeft() + paramInt3 - paramInt1 - this.mTotalLength;
      } 
    } else {
      paramInt1 = getPaddingLeft() + (paramInt3 - paramInt1 - this.mTotalLength) / 2;
    } 
    if (bool2) {
      b2 = i - 1;
      b1 = -1;
    } else {
      b2 = 0;
      b1 = 1;
    } 
    paramInt4 = 0;
    paramInt3 = j;
    while (paramInt4 < i) {
      int i1 = b2 + b1 * paramInt4;
      View view = getVirtualChildAt(i1);
      if (view == null) {
        paramInt1 += measureNullChild(i1);
      } else if (view.getVisibility() != 8) {
        int i5 = view.getMeasuredWidth();
        int i6 = view.getMeasuredHeight();
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool1 && layoutParams.height != -1) {
          i3 = view.getBaseline();
        } else {
          i3 = -1;
        } 
        int i4 = layoutParams.gravity;
        int i2 = i4;
        if (i4 < 0)
          i2 = paramInt2; 
        i2 &= 0x70;
        if (i2 != 16) {
          if (i2 != 48) {
            if (i2 != 80) {
              i2 = paramInt3;
            } else {
              i4 = n - m - i6 - layoutParams.bottomMargin;
              i2 = i4;
              if (i3 != -1) {
                i2 = view.getMeasuredHeight();
                i2 = i4 - arrayOfInt2[2] - i2 - i3;
              } 
            } 
          } else {
            i4 = layoutParams.topMargin + paramInt3;
            i2 = i4;
            if (i3 != -1)
              i2 = i4 + arrayOfInt1[1] - i3; 
          } 
        } else {
          i2 = (n - j - k - i6) / 2 + paramInt3 + layoutParams.topMargin - layoutParams.bottomMargin;
        } 
        int i3 = paramInt1;
        if (hasDividerBeforeChildAt(i1))
          i3 = paramInt1 + this.mDividerWidth; 
        paramInt1 = layoutParams.leftMargin + i3;
        setChildFrame(view, paramInt1 + getLocationOffset(view), i2, i5, i6);
        i3 = layoutParams.rightMargin;
        i2 = getNextLocationOffset(view);
        paramInt4 += getChildrenSkipCount(view, i1);
        paramInt1 += i5 + i3 + i2;
      } 
      paramInt4++;
    } 
  }
  
  void layoutVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = getPaddingLeft();
    int n = paramInt3 - paramInt1;
    int j = getPaddingRight();
    int k = getPaddingRight();
    int i1 = getVirtualChildCount();
    int m = this.mGravity;
    paramInt1 = m & 0x70;
    if (paramInt1 != 16) {
      if (paramInt1 != 80) {
        paramInt1 = getPaddingTop();
      } else {
        paramInt1 = getPaddingTop() + paramInt4 - paramInt2 - this.mTotalLength;
      } 
    } else {
      paramInt1 = getPaddingTop() + (paramInt4 - paramInt2 - this.mTotalLength) / 2;
    } 
    paramInt2 = 0;
    while (paramInt2 < i1) {
      View view = getVirtualChildAt(paramInt2);
      if (view == null) {
        paramInt3 = paramInt1 + measureNullChild(paramInt2);
        paramInt4 = paramInt2;
      } else {
        paramInt3 = paramInt1;
        paramInt4 = paramInt2;
        if (view.getVisibility() != 8) {
          int i3 = view.getMeasuredWidth();
          int i2 = view.getMeasuredHeight();
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          paramInt4 = layoutParams.gravity;
          paramInt3 = paramInt4;
          if (paramInt4 < 0)
            paramInt3 = m & 0x800007; 
          paramInt3 = GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection((View)this)) & 0x7;
          if (paramInt3 != 1) {
            if (paramInt3 != 5) {
              paramInt3 = layoutParams.leftMargin + i;
            } else {
              paramInt3 = n - j - i3;
              paramInt4 = layoutParams.rightMargin;
              paramInt3 -= paramInt4;
            } 
          } else {
            paramInt3 = (n - i - k - i3) / 2 + i + layoutParams.leftMargin;
            paramInt4 = layoutParams.rightMargin;
            paramInt3 -= paramInt4;
          } 
          paramInt4 = paramInt1;
          if (hasDividerBeforeChildAt(paramInt2))
            paramInt4 = paramInt1 + this.mDividerHeight; 
          paramInt1 = paramInt4 + layoutParams.topMargin;
          setChildFrame(view, paramInt3, paramInt1 + getLocationOffset(view), i3, i2);
          paramInt3 = layoutParams.bottomMargin;
          i3 = getNextLocationOffset(view);
          paramInt4 = paramInt2 + getChildrenSkipCount(view, paramInt2);
          paramInt3 = paramInt1 + i2 + paramInt3 + i3;
        } 
      } 
      paramInt2 = paramInt4 + 1;
      paramInt1 = paramInt3;
    } 
  }
  
  void measureChildBeforeLayout(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    measureChildWithMargins(paramView, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  void measureHorizontal(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield mTotalLength : I
    //   5: aload_0
    //   6: invokevirtual getVirtualChildCount : ()I
    //   9: istore #16
    //   11: iload_1
    //   12: invokestatic getMode : (I)I
    //   15: istore #22
    //   17: iload_2
    //   18: invokestatic getMode : (I)I
    //   21: istore #21
    //   23: aload_0
    //   24: getfield mMaxAscent : [I
    //   27: ifnull -> 37
    //   30: aload_0
    //   31: getfield mMaxDescent : [I
    //   34: ifnonnull -> 51
    //   37: aload_0
    //   38: iconst_4
    //   39: newarray int
    //   41: putfield mMaxAscent : [I
    //   44: aload_0
    //   45: iconst_4
    //   46: newarray int
    //   48: putfield mMaxDescent : [I
    //   51: aload_0
    //   52: getfield mMaxAscent : [I
    //   55: astore #27
    //   57: aload_0
    //   58: getfield mMaxDescent : [I
    //   61: astore #26
    //   63: aload #27
    //   65: iconst_3
    //   66: iconst_m1
    //   67: iastore
    //   68: aload #27
    //   70: iconst_2
    //   71: iconst_m1
    //   72: iastore
    //   73: aload #27
    //   75: iconst_1
    //   76: iconst_m1
    //   77: iastore
    //   78: aload #27
    //   80: iconst_0
    //   81: iconst_m1
    //   82: iastore
    //   83: aload #26
    //   85: iconst_3
    //   86: iconst_m1
    //   87: iastore
    //   88: aload #26
    //   90: iconst_2
    //   91: iconst_m1
    //   92: iastore
    //   93: aload #26
    //   95: iconst_1
    //   96: iconst_m1
    //   97: iastore
    //   98: aload #26
    //   100: iconst_0
    //   101: iconst_m1
    //   102: iastore
    //   103: aload_0
    //   104: getfield mBaselineAligned : Z
    //   107: istore #24
    //   109: aload_0
    //   110: getfield mUseLargestChild : Z
    //   113: istore #25
    //   115: iload #22
    //   117: ldc 1073741824
    //   119: if_icmpne -> 128
    //   122: iconst_1
    //   123: istore #15
    //   125: goto -> 131
    //   128: iconst_0
    //   129: istore #15
    //   131: fconst_0
    //   132: fstore_3
    //   133: iconst_0
    //   134: istore #8
    //   136: iconst_0
    //   137: istore #7
    //   139: iconst_0
    //   140: istore #9
    //   142: iconst_0
    //   143: istore #6
    //   145: iconst_0
    //   146: istore #11
    //   148: iconst_0
    //   149: istore #12
    //   151: iconst_0
    //   152: istore #13
    //   154: iconst_1
    //   155: istore #5
    //   157: iconst_0
    //   158: istore #10
    //   160: iload #8
    //   162: iload #16
    //   164: if_icmpge -> 845
    //   167: aload_0
    //   168: iload #8
    //   170: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   173: astore #29
    //   175: aload #29
    //   177: ifnonnull -> 198
    //   180: aload_0
    //   181: aload_0
    //   182: getfield mTotalLength : I
    //   185: aload_0
    //   186: iload #8
    //   188: invokevirtual measureNullChild : (I)I
    //   191: iadd
    //   192: putfield mTotalLength : I
    //   195: goto -> 839
    //   198: aload #29
    //   200: invokevirtual getVisibility : ()I
    //   203: bipush #8
    //   205: if_icmpne -> 224
    //   208: iload #8
    //   210: aload_0
    //   211: aload #29
    //   213: iload #8
    //   215: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   218: iadd
    //   219: istore #8
    //   221: goto -> 195
    //   224: aload_0
    //   225: iload #8
    //   227: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   230: ifeq -> 246
    //   233: aload_0
    //   234: aload_0
    //   235: getfield mTotalLength : I
    //   238: aload_0
    //   239: getfield mDividerWidth : I
    //   242: iadd
    //   243: putfield mTotalLength : I
    //   246: aload #29
    //   248: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   251: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   254: astore #28
    //   256: fload_3
    //   257: aload #28
    //   259: getfield weight : F
    //   262: fadd
    //   263: fstore_3
    //   264: iload #22
    //   266: ldc 1073741824
    //   268: if_icmpne -> 380
    //   271: aload #28
    //   273: getfield width : I
    //   276: ifne -> 380
    //   279: aload #28
    //   281: getfield weight : F
    //   284: fconst_0
    //   285: fcmpl
    //   286: ifle -> 380
    //   289: iload #15
    //   291: ifeq -> 317
    //   294: aload_0
    //   295: aload_0
    //   296: getfield mTotalLength : I
    //   299: aload #28
    //   301: getfield leftMargin : I
    //   304: aload #28
    //   306: getfield rightMargin : I
    //   309: iadd
    //   310: iadd
    //   311: putfield mTotalLength : I
    //   314: goto -> 346
    //   317: aload_0
    //   318: getfield mTotalLength : I
    //   321: istore #14
    //   323: aload_0
    //   324: iload #14
    //   326: aload #28
    //   328: getfield leftMargin : I
    //   331: iload #14
    //   333: iadd
    //   334: aload #28
    //   336: getfield rightMargin : I
    //   339: iadd
    //   340: invokestatic max : (II)I
    //   343: putfield mTotalLength : I
    //   346: iload #24
    //   348: ifeq -> 374
    //   351: iconst_0
    //   352: iconst_0
    //   353: invokestatic makeMeasureSpec : (II)I
    //   356: istore #14
    //   358: aload #29
    //   360: iload #14
    //   362: iload #14
    //   364: invokevirtual measure : (II)V
    //   367: iload #7
    //   369: istore #14
    //   371: goto -> 564
    //   374: iconst_1
    //   375: istore #12
    //   377: goto -> 568
    //   380: aload #28
    //   382: getfield width : I
    //   385: ifne -> 411
    //   388: aload #28
    //   390: getfield weight : F
    //   393: fconst_0
    //   394: fcmpl
    //   395: ifle -> 411
    //   398: aload #28
    //   400: bipush #-2
    //   402: putfield width : I
    //   405: iconst_0
    //   406: istore #14
    //   408: goto -> 416
    //   411: ldc_w -2147483648
    //   414: istore #14
    //   416: fload_3
    //   417: fconst_0
    //   418: fcmpl
    //   419: ifne -> 431
    //   422: aload_0
    //   423: getfield mTotalLength : I
    //   426: istore #17
    //   428: goto -> 434
    //   431: iconst_0
    //   432: istore #17
    //   434: aload_0
    //   435: aload #29
    //   437: iload #8
    //   439: iload_1
    //   440: iload #17
    //   442: iload_2
    //   443: iconst_0
    //   444: invokevirtual measureChildBeforeLayout : (Landroid/view/View;IIIII)V
    //   447: iload #14
    //   449: ldc_w -2147483648
    //   452: if_icmpeq -> 462
    //   455: aload #28
    //   457: iload #14
    //   459: putfield width : I
    //   462: aload #29
    //   464: invokevirtual getMeasuredWidth : ()I
    //   467: istore #17
    //   469: iload #15
    //   471: ifeq -> 507
    //   474: aload_0
    //   475: aload_0
    //   476: getfield mTotalLength : I
    //   479: aload #28
    //   481: getfield leftMargin : I
    //   484: iload #17
    //   486: iadd
    //   487: aload #28
    //   489: getfield rightMargin : I
    //   492: iadd
    //   493: aload_0
    //   494: aload #29
    //   496: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   499: iadd
    //   500: iadd
    //   501: putfield mTotalLength : I
    //   504: goto -> 546
    //   507: aload_0
    //   508: getfield mTotalLength : I
    //   511: istore #14
    //   513: aload_0
    //   514: iload #14
    //   516: iload #14
    //   518: iload #17
    //   520: iadd
    //   521: aload #28
    //   523: getfield leftMargin : I
    //   526: iadd
    //   527: aload #28
    //   529: getfield rightMargin : I
    //   532: iadd
    //   533: aload_0
    //   534: aload #29
    //   536: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   539: iadd
    //   540: invokestatic max : (II)I
    //   543: putfield mTotalLength : I
    //   546: iload #7
    //   548: istore #14
    //   550: iload #25
    //   552: ifeq -> 564
    //   555: iload #17
    //   557: iload #7
    //   559: invokestatic max : (II)I
    //   562: istore #14
    //   564: iload #14
    //   566: istore #7
    //   568: iload #21
    //   570: ldc 1073741824
    //   572: if_icmpeq -> 593
    //   575: aload #28
    //   577: getfield height : I
    //   580: iconst_m1
    //   581: if_icmpne -> 593
    //   584: iconst_1
    //   585: istore #14
    //   587: iconst_1
    //   588: istore #10
    //   590: goto -> 596
    //   593: iconst_0
    //   594: istore #14
    //   596: aload #28
    //   598: getfield topMargin : I
    //   601: aload #28
    //   603: getfield bottomMargin : I
    //   606: iadd
    //   607: istore #17
    //   609: aload #29
    //   611: invokevirtual getMeasuredHeight : ()I
    //   614: iload #17
    //   616: iadd
    //   617: istore #18
    //   619: iload #13
    //   621: aload #29
    //   623: invokevirtual getMeasuredState : ()I
    //   626: invokestatic combineMeasuredStates : (II)I
    //   629: istore #19
    //   631: iload #24
    //   633: ifeq -> 718
    //   636: aload #29
    //   638: invokevirtual getBaseline : ()I
    //   641: istore #23
    //   643: iload #23
    //   645: iconst_m1
    //   646: if_icmpeq -> 718
    //   649: aload #28
    //   651: getfield gravity : I
    //   654: istore #20
    //   656: iload #20
    //   658: istore #13
    //   660: iload #20
    //   662: ifge -> 671
    //   665: aload_0
    //   666: getfield mGravity : I
    //   669: istore #13
    //   671: iload #13
    //   673: bipush #112
    //   675: iand
    //   676: iconst_4
    //   677: ishr
    //   678: bipush #-2
    //   680: iand
    //   681: iconst_1
    //   682: ishr
    //   683: istore #13
    //   685: aload #27
    //   687: iload #13
    //   689: aload #27
    //   691: iload #13
    //   693: iaload
    //   694: iload #23
    //   696: invokestatic max : (II)I
    //   699: iastore
    //   700: aload #26
    //   702: iload #13
    //   704: aload #26
    //   706: iload #13
    //   708: iaload
    //   709: iload #18
    //   711: iload #23
    //   713: isub
    //   714: invokestatic max : (II)I
    //   717: iastore
    //   718: iload #9
    //   720: iload #18
    //   722: invokestatic max : (II)I
    //   725: istore #13
    //   727: iload #5
    //   729: ifeq -> 747
    //   732: aload #28
    //   734: getfield height : I
    //   737: iconst_m1
    //   738: if_icmpne -> 747
    //   741: iconst_1
    //   742: istore #5
    //   744: goto -> 750
    //   747: iconst_0
    //   748: istore #5
    //   750: aload #28
    //   752: getfield weight : F
    //   755: fconst_0
    //   756: fcmpl
    //   757: ifle -> 784
    //   760: iload #14
    //   762: ifeq -> 768
    //   765: goto -> 772
    //   768: iload #18
    //   770: istore #17
    //   772: iload #11
    //   774: iload #17
    //   776: invokestatic max : (II)I
    //   779: istore #9
    //   781: goto -> 806
    //   784: iload #14
    //   786: ifeq -> 793
    //   789: iload #17
    //   791: istore #18
    //   793: iload #6
    //   795: iload #18
    //   797: invokestatic max : (II)I
    //   800: istore #6
    //   802: iload #11
    //   804: istore #9
    //   806: aload_0
    //   807: aload #29
    //   809: iload #8
    //   811: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   814: istore #11
    //   816: iload #13
    //   818: istore #14
    //   820: iload #19
    //   822: istore #13
    //   824: iload #11
    //   826: iload #8
    //   828: iadd
    //   829: istore #8
    //   831: iload #9
    //   833: istore #11
    //   835: iload #14
    //   837: istore #9
    //   839: iinc #8, 1
    //   842: goto -> 160
    //   845: aload_0
    //   846: getfield mTotalLength : I
    //   849: ifle -> 874
    //   852: aload_0
    //   853: iload #16
    //   855: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   858: ifeq -> 874
    //   861: aload_0
    //   862: aload_0
    //   863: getfield mTotalLength : I
    //   866: aload_0
    //   867: getfield mDividerWidth : I
    //   870: iadd
    //   871: putfield mTotalLength : I
    //   874: aload #27
    //   876: iconst_1
    //   877: iaload
    //   878: iconst_m1
    //   879: if_icmpne -> 912
    //   882: aload #27
    //   884: iconst_0
    //   885: iaload
    //   886: iconst_m1
    //   887: if_icmpne -> 912
    //   890: aload #27
    //   892: iconst_2
    //   893: iaload
    //   894: iconst_m1
    //   895: if_icmpne -> 912
    //   898: aload #27
    //   900: iconst_3
    //   901: iaload
    //   902: iconst_m1
    //   903: if_icmpeq -> 909
    //   906: goto -> 912
    //   909: goto -> 970
    //   912: iload #9
    //   914: aload #27
    //   916: iconst_3
    //   917: iaload
    //   918: aload #27
    //   920: iconst_0
    //   921: iaload
    //   922: aload #27
    //   924: iconst_1
    //   925: iaload
    //   926: aload #27
    //   928: iconst_2
    //   929: iaload
    //   930: invokestatic max : (II)I
    //   933: invokestatic max : (II)I
    //   936: invokestatic max : (II)I
    //   939: aload #26
    //   941: iconst_3
    //   942: iaload
    //   943: aload #26
    //   945: iconst_0
    //   946: iaload
    //   947: aload #26
    //   949: iconst_1
    //   950: iaload
    //   951: aload #26
    //   953: iconst_2
    //   954: iaload
    //   955: invokestatic max : (II)I
    //   958: invokestatic max : (II)I
    //   961: invokestatic max : (II)I
    //   964: iadd
    //   965: invokestatic max : (II)I
    //   968: istore #9
    //   970: iload #9
    //   972: istore #14
    //   974: iload #25
    //   976: ifeq -> 1165
    //   979: iload #22
    //   981: ldc_w -2147483648
    //   984: if_icmpeq -> 996
    //   987: iload #9
    //   989: istore #14
    //   991: iload #22
    //   993: ifne -> 1165
    //   996: aload_0
    //   997: iconst_0
    //   998: putfield mTotalLength : I
    //   1001: iconst_0
    //   1002: istore #8
    //   1004: iload #9
    //   1006: istore #14
    //   1008: iload #8
    //   1010: iload #16
    //   1012: if_icmpge -> 1165
    //   1015: aload_0
    //   1016: iload #8
    //   1018: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1021: astore #28
    //   1023: aload #28
    //   1025: ifnonnull -> 1046
    //   1028: aload_0
    //   1029: aload_0
    //   1030: getfield mTotalLength : I
    //   1033: aload_0
    //   1034: iload #8
    //   1036: invokevirtual measureNullChild : (I)I
    //   1039: iadd
    //   1040: putfield mTotalLength : I
    //   1043: goto -> 1069
    //   1046: aload #28
    //   1048: invokevirtual getVisibility : ()I
    //   1051: bipush #8
    //   1053: if_icmpne -> 1072
    //   1056: iload #8
    //   1058: aload_0
    //   1059: aload #28
    //   1061: iload #8
    //   1063: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   1066: iadd
    //   1067: istore #8
    //   1069: goto -> 1159
    //   1072: aload #28
    //   1074: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1077: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   1080: astore #29
    //   1082: iload #15
    //   1084: ifeq -> 1120
    //   1087: aload_0
    //   1088: aload_0
    //   1089: getfield mTotalLength : I
    //   1092: aload #29
    //   1094: getfield leftMargin : I
    //   1097: iload #7
    //   1099: iadd
    //   1100: aload #29
    //   1102: getfield rightMargin : I
    //   1105: iadd
    //   1106: aload_0
    //   1107: aload #28
    //   1109: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1112: iadd
    //   1113: iadd
    //   1114: putfield mTotalLength : I
    //   1117: goto -> 1069
    //   1120: aload_0
    //   1121: getfield mTotalLength : I
    //   1124: istore #14
    //   1126: aload_0
    //   1127: iload #14
    //   1129: iload #14
    //   1131: iload #7
    //   1133: iadd
    //   1134: aload #29
    //   1136: getfield leftMargin : I
    //   1139: iadd
    //   1140: aload #29
    //   1142: getfield rightMargin : I
    //   1145: iadd
    //   1146: aload_0
    //   1147: aload #28
    //   1149: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1152: iadd
    //   1153: invokestatic max : (II)I
    //   1156: putfield mTotalLength : I
    //   1159: iinc #8, 1
    //   1162: goto -> 1004
    //   1165: aload_0
    //   1166: aload_0
    //   1167: getfield mTotalLength : I
    //   1170: aload_0
    //   1171: invokevirtual getPaddingLeft : ()I
    //   1174: aload_0
    //   1175: invokevirtual getPaddingRight : ()I
    //   1178: iadd
    //   1179: iadd
    //   1180: putfield mTotalLength : I
    //   1183: aload_0
    //   1184: getfield mTotalLength : I
    //   1187: aload_0
    //   1188: invokevirtual getSuggestedMinimumWidth : ()I
    //   1191: invokestatic max : (II)I
    //   1194: iload_1
    //   1195: iconst_0
    //   1196: invokestatic resolveSizeAndState : (III)I
    //   1199: istore #18
    //   1201: ldc_w 16777215
    //   1204: iload #18
    //   1206: iand
    //   1207: aload_0
    //   1208: getfield mTotalLength : I
    //   1211: isub
    //   1212: istore #17
    //   1214: iload #12
    //   1216: ifne -> 1349
    //   1219: iload #17
    //   1221: ifeq -> 1233
    //   1224: fload_3
    //   1225: fconst_0
    //   1226: fcmpl
    //   1227: ifle -> 1233
    //   1230: goto -> 1349
    //   1233: iload #6
    //   1235: iload #11
    //   1237: invokestatic max : (II)I
    //   1240: istore #9
    //   1242: iload #25
    //   1244: ifeq -> 1334
    //   1247: iload #22
    //   1249: ldc 1073741824
    //   1251: if_icmpeq -> 1334
    //   1254: iconst_0
    //   1255: istore #6
    //   1257: iload #6
    //   1259: iload #16
    //   1261: if_icmpge -> 1334
    //   1264: aload_0
    //   1265: iload #6
    //   1267: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1270: astore #26
    //   1272: aload #26
    //   1274: ifnull -> 1328
    //   1277: aload #26
    //   1279: invokevirtual getVisibility : ()I
    //   1282: bipush #8
    //   1284: if_icmpne -> 1290
    //   1287: goto -> 1328
    //   1290: aload #26
    //   1292: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1295: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   1298: getfield weight : F
    //   1301: fconst_0
    //   1302: fcmpl
    //   1303: ifle -> 1328
    //   1306: aload #26
    //   1308: iload #7
    //   1310: ldc 1073741824
    //   1312: invokestatic makeMeasureSpec : (II)I
    //   1315: aload #26
    //   1317: invokevirtual getMeasuredHeight : ()I
    //   1320: ldc 1073741824
    //   1322: invokestatic makeMeasureSpec : (II)I
    //   1325: invokevirtual measure : (II)V
    //   1328: iinc #6, 1
    //   1331: goto -> 1257
    //   1334: iload #16
    //   1336: istore #8
    //   1338: iload #14
    //   1340: istore #6
    //   1342: iload #9
    //   1344: istore #7
    //   1346: goto -> 2086
    //   1349: aload_0
    //   1350: getfield mWeightSum : F
    //   1353: fstore #4
    //   1355: fload #4
    //   1357: fconst_0
    //   1358: fcmpl
    //   1359: ifle -> 1365
    //   1362: fload #4
    //   1364: fstore_3
    //   1365: aload #27
    //   1367: iconst_3
    //   1368: iconst_m1
    //   1369: iastore
    //   1370: aload #27
    //   1372: iconst_2
    //   1373: iconst_m1
    //   1374: iastore
    //   1375: aload #27
    //   1377: iconst_1
    //   1378: iconst_m1
    //   1379: iastore
    //   1380: aload #27
    //   1382: iconst_0
    //   1383: iconst_m1
    //   1384: iastore
    //   1385: aload #26
    //   1387: iconst_3
    //   1388: iconst_m1
    //   1389: iastore
    //   1390: aload #26
    //   1392: iconst_2
    //   1393: iconst_m1
    //   1394: iastore
    //   1395: aload #26
    //   1397: iconst_1
    //   1398: iconst_m1
    //   1399: iastore
    //   1400: aload #26
    //   1402: iconst_0
    //   1403: iconst_m1
    //   1404: iastore
    //   1405: aload_0
    //   1406: iconst_0
    //   1407: putfield mTotalLength : I
    //   1410: iconst_m1
    //   1411: istore #11
    //   1413: iconst_0
    //   1414: istore #12
    //   1416: iload #5
    //   1418: istore #8
    //   1420: iload #16
    //   1422: istore #7
    //   1424: iload #6
    //   1426: istore #9
    //   1428: iload #13
    //   1430: istore #5
    //   1432: iload #17
    //   1434: istore #6
    //   1436: iload #12
    //   1438: istore #13
    //   1440: iload #13
    //   1442: iload #7
    //   1444: if_icmpge -> 1952
    //   1447: aload_0
    //   1448: iload #13
    //   1450: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1453: astore #28
    //   1455: aload #28
    //   1457: ifnull -> 1946
    //   1460: aload #28
    //   1462: invokevirtual getVisibility : ()I
    //   1465: bipush #8
    //   1467: if_icmpne -> 1473
    //   1470: goto -> 1946
    //   1473: aload #28
    //   1475: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1478: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   1481: astore #29
    //   1483: aload #29
    //   1485: getfield weight : F
    //   1488: fstore #4
    //   1490: fload #4
    //   1492: fconst_0
    //   1493: fcmpl
    //   1494: ifle -> 1657
    //   1497: iload #6
    //   1499: i2f
    //   1500: fload #4
    //   1502: fmul
    //   1503: fload_3
    //   1504: fdiv
    //   1505: f2i
    //   1506: istore #14
    //   1508: iload_2
    //   1509: aload_0
    //   1510: invokevirtual getPaddingTop : ()I
    //   1513: aload_0
    //   1514: invokevirtual getPaddingBottom : ()I
    //   1517: iadd
    //   1518: aload #29
    //   1520: getfield topMargin : I
    //   1523: iadd
    //   1524: aload #29
    //   1526: getfield bottomMargin : I
    //   1529: iadd
    //   1530: aload #29
    //   1532: getfield height : I
    //   1535: invokestatic getChildMeasureSpec : (III)I
    //   1538: istore #17
    //   1540: aload #29
    //   1542: getfield width : I
    //   1545: ifne -> 1590
    //   1548: iload #22
    //   1550: ldc 1073741824
    //   1552: if_icmpeq -> 1558
    //   1555: goto -> 1590
    //   1558: iload #14
    //   1560: ifle -> 1570
    //   1563: iload #14
    //   1565: istore #12
    //   1567: goto -> 1573
    //   1570: iconst_0
    //   1571: istore #12
    //   1573: aload #28
    //   1575: iload #12
    //   1577: ldc 1073741824
    //   1579: invokestatic makeMeasureSpec : (II)I
    //   1582: iload #17
    //   1584: invokevirtual measure : (II)V
    //   1587: goto -> 1626
    //   1590: aload #28
    //   1592: invokevirtual getMeasuredWidth : ()I
    //   1595: iload #14
    //   1597: iadd
    //   1598: istore #16
    //   1600: iload #16
    //   1602: istore #12
    //   1604: iload #16
    //   1606: ifge -> 1612
    //   1609: iconst_0
    //   1610: istore #12
    //   1612: aload #28
    //   1614: iload #12
    //   1616: ldc 1073741824
    //   1618: invokestatic makeMeasureSpec : (II)I
    //   1621: iload #17
    //   1623: invokevirtual measure : (II)V
    //   1626: iload #5
    //   1628: aload #28
    //   1630: invokevirtual getMeasuredState : ()I
    //   1633: ldc_w -16777216
    //   1636: iand
    //   1637: invokestatic combineMeasuredStates : (II)I
    //   1640: istore #5
    //   1642: fload_3
    //   1643: fload #4
    //   1645: fsub
    //   1646: fstore_3
    //   1647: iload #6
    //   1649: iload #14
    //   1651: isub
    //   1652: istore #6
    //   1654: goto -> 1657
    //   1657: iload #15
    //   1659: ifeq -> 1698
    //   1662: aload_0
    //   1663: aload_0
    //   1664: getfield mTotalLength : I
    //   1667: aload #28
    //   1669: invokevirtual getMeasuredWidth : ()I
    //   1672: aload #29
    //   1674: getfield leftMargin : I
    //   1677: iadd
    //   1678: aload #29
    //   1680: getfield rightMargin : I
    //   1683: iadd
    //   1684: aload_0
    //   1685: aload #28
    //   1687: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1690: iadd
    //   1691: iadd
    //   1692: putfield mTotalLength : I
    //   1695: goto -> 1740
    //   1698: aload_0
    //   1699: getfield mTotalLength : I
    //   1702: istore #12
    //   1704: aload_0
    //   1705: iload #12
    //   1707: aload #28
    //   1709: invokevirtual getMeasuredWidth : ()I
    //   1712: iload #12
    //   1714: iadd
    //   1715: aload #29
    //   1717: getfield leftMargin : I
    //   1720: iadd
    //   1721: aload #29
    //   1723: getfield rightMargin : I
    //   1726: iadd
    //   1727: aload_0
    //   1728: aload #28
    //   1730: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1733: iadd
    //   1734: invokestatic max : (II)I
    //   1737: putfield mTotalLength : I
    //   1740: iload #21
    //   1742: ldc 1073741824
    //   1744: if_icmpeq -> 1762
    //   1747: aload #29
    //   1749: getfield height : I
    //   1752: iconst_m1
    //   1753: if_icmpne -> 1762
    //   1756: iconst_1
    //   1757: istore #12
    //   1759: goto -> 1765
    //   1762: iconst_0
    //   1763: istore #12
    //   1765: aload #29
    //   1767: getfield topMargin : I
    //   1770: aload #29
    //   1772: getfield bottomMargin : I
    //   1775: iadd
    //   1776: istore #17
    //   1778: aload #28
    //   1780: invokevirtual getMeasuredHeight : ()I
    //   1783: iload #17
    //   1785: iadd
    //   1786: istore #16
    //   1788: iload #11
    //   1790: iload #16
    //   1792: invokestatic max : (II)I
    //   1795: istore #14
    //   1797: iload #12
    //   1799: ifeq -> 1809
    //   1802: iload #17
    //   1804: istore #11
    //   1806: goto -> 1813
    //   1809: iload #16
    //   1811: istore #11
    //   1813: iload #9
    //   1815: iload #11
    //   1817: invokestatic max : (II)I
    //   1820: istore #11
    //   1822: iload #8
    //   1824: ifeq -> 1842
    //   1827: aload #29
    //   1829: getfield height : I
    //   1832: iconst_m1
    //   1833: if_icmpne -> 1842
    //   1836: iconst_1
    //   1837: istore #8
    //   1839: goto -> 1845
    //   1842: iconst_0
    //   1843: istore #8
    //   1845: iload #24
    //   1847: ifeq -> 1935
    //   1850: aload #28
    //   1852: invokevirtual getBaseline : ()I
    //   1855: istore #17
    //   1857: iload #17
    //   1859: iconst_m1
    //   1860: if_icmpeq -> 1935
    //   1863: aload #29
    //   1865: getfield gravity : I
    //   1868: istore #12
    //   1870: iload #12
    //   1872: istore #9
    //   1874: iload #12
    //   1876: ifge -> 1885
    //   1879: aload_0
    //   1880: getfield mGravity : I
    //   1883: istore #9
    //   1885: iload #9
    //   1887: bipush #112
    //   1889: iand
    //   1890: iconst_4
    //   1891: ishr
    //   1892: bipush #-2
    //   1894: iand
    //   1895: iconst_1
    //   1896: ishr
    //   1897: istore #9
    //   1899: aload #27
    //   1901: iload #9
    //   1903: aload #27
    //   1905: iload #9
    //   1907: iaload
    //   1908: iload #17
    //   1910: invokestatic max : (II)I
    //   1913: iastore
    //   1914: aload #26
    //   1916: iload #9
    //   1918: aload #26
    //   1920: iload #9
    //   1922: iaload
    //   1923: iload #16
    //   1925: iload #17
    //   1927: isub
    //   1928: invokestatic max : (II)I
    //   1931: iastore
    //   1932: goto -> 1935
    //   1935: iload #11
    //   1937: istore #9
    //   1939: iload #14
    //   1941: istore #11
    //   1943: goto -> 1946
    //   1946: iinc #13, 1
    //   1949: goto -> 1440
    //   1952: aload_0
    //   1953: aload_0
    //   1954: getfield mTotalLength : I
    //   1957: aload_0
    //   1958: invokevirtual getPaddingLeft : ()I
    //   1961: aload_0
    //   1962: invokevirtual getPaddingRight : ()I
    //   1965: iadd
    //   1966: iadd
    //   1967: putfield mTotalLength : I
    //   1970: aload #27
    //   1972: iconst_1
    //   1973: iaload
    //   1974: iconst_m1
    //   1975: if_icmpne -> 2012
    //   1978: aload #27
    //   1980: iconst_0
    //   1981: iaload
    //   1982: iconst_m1
    //   1983: if_icmpne -> 2012
    //   1986: aload #27
    //   1988: iconst_2
    //   1989: iaload
    //   1990: iconst_m1
    //   1991: if_icmpne -> 2012
    //   1994: aload #27
    //   1996: iconst_3
    //   1997: iaload
    //   1998: iconst_m1
    //   1999: if_icmpeq -> 2005
    //   2002: goto -> 2012
    //   2005: iload #11
    //   2007: istore #6
    //   2009: goto -> 2070
    //   2012: iload #11
    //   2014: aload #27
    //   2016: iconst_3
    //   2017: iaload
    //   2018: aload #27
    //   2020: iconst_0
    //   2021: iaload
    //   2022: aload #27
    //   2024: iconst_1
    //   2025: iaload
    //   2026: aload #27
    //   2028: iconst_2
    //   2029: iaload
    //   2030: invokestatic max : (II)I
    //   2033: invokestatic max : (II)I
    //   2036: invokestatic max : (II)I
    //   2039: aload #26
    //   2041: iconst_3
    //   2042: iaload
    //   2043: aload #26
    //   2045: iconst_0
    //   2046: iaload
    //   2047: aload #26
    //   2049: iconst_1
    //   2050: iaload
    //   2051: aload #26
    //   2053: iconst_2
    //   2054: iaload
    //   2055: invokestatic max : (II)I
    //   2058: invokestatic max : (II)I
    //   2061: invokestatic max : (II)I
    //   2064: iadd
    //   2065: invokestatic max : (II)I
    //   2068: istore #6
    //   2070: iload #5
    //   2072: istore #13
    //   2074: iload #8
    //   2076: istore #5
    //   2078: iload #7
    //   2080: istore #8
    //   2082: iload #9
    //   2084: istore #7
    //   2086: iload #5
    //   2088: ifne -> 2101
    //   2091: iload #21
    //   2093: ldc 1073741824
    //   2095: if_icmpeq -> 2101
    //   2098: goto -> 2105
    //   2101: iload #6
    //   2103: istore #7
    //   2105: aload_0
    //   2106: iload #18
    //   2108: iload #13
    //   2110: ldc_w -16777216
    //   2113: iand
    //   2114: ior
    //   2115: iload #7
    //   2117: aload_0
    //   2118: invokevirtual getPaddingTop : ()I
    //   2121: aload_0
    //   2122: invokevirtual getPaddingBottom : ()I
    //   2125: iadd
    //   2126: iadd
    //   2127: aload_0
    //   2128: invokevirtual getSuggestedMinimumHeight : ()I
    //   2131: invokestatic max : (II)I
    //   2134: iload_2
    //   2135: iload #13
    //   2137: bipush #16
    //   2139: ishl
    //   2140: invokestatic resolveSizeAndState : (III)I
    //   2143: invokevirtual setMeasuredDimension : (II)V
    //   2146: iload #10
    //   2148: ifeq -> 2158
    //   2151: aload_0
    //   2152: iload #8
    //   2154: iload_1
    //   2155: invokespecial forceUniformHeight : (II)V
    //   2158: return
  }
  
  int measureNullChild(int paramInt) {
    return 0;
  }
  
  void measureVertical(int paramInt1, int paramInt2) {
    this.mTotalLength = 0;
    int i4 = getVirtualChildCount();
    int i8 = View.MeasureSpec.getMode(paramInt1);
    int i5 = View.MeasureSpec.getMode(paramInt2);
    int i9 = this.mBaselineAlignedChildIndex;
    boolean bool1 = this.mUseLargestChild;
    float f = 0.0F;
    int i = 0;
    int i3 = 0;
    int m = 0;
    int n = 0;
    int k = 0;
    int i1 = 0;
    int i2 = 0;
    int j = 1;
    boolean bool = false;
    while (i1 < i4) {
      View view = getVirtualChildAt(i1);
      if (view == null) {
        this.mTotalLength += measureNullChild(i1);
      } else if (view.getVisibility() == 8) {
        i1 += getChildrenSkipCount(view, i1);
      } else {
        if (hasDividerBeforeChildAt(i1))
          this.mTotalLength += this.mDividerHeight; 
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        f += layoutParams.weight;
        if (i5 == 1073741824 && layoutParams.height == 0 && layoutParams.weight > 0.0F) {
          i2 = this.mTotalLength;
          this.mTotalLength = Math.max(i2, layoutParams.topMargin + i2 + layoutParams.bottomMargin);
          i2 = 1;
        } else {
          if (layoutParams.height == 0 && layoutParams.weight > 0.0F) {
            layoutParams.height = -2;
            i10 = 0;
          } else {
            i10 = Integer.MIN_VALUE;
          } 
          if (f == 0.0F) {
            i11 = this.mTotalLength;
          } else {
            i11 = 0;
          } 
          measureChildBeforeLayout(view, i1, paramInt1, 0, paramInt2, i11);
          if (i10 != Integer.MIN_VALUE)
            layoutParams.height = i10; 
          int i10 = view.getMeasuredHeight();
          int i11 = this.mTotalLength;
          this.mTotalLength = Math.max(i11, i11 + i10 + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(view));
          if (bool1)
            m = Math.max(i10, m); 
        } 
        if (i9 >= 0 && i9 == i1 + 1)
          this.mBaselineChildTop = this.mTotalLength; 
        if (i1 >= i9 || layoutParams.weight <= 0.0F) {
          if (i8 != 1073741824 && layoutParams.width == -1) {
            i10 = 1;
            bool = true;
          } else {
            i10 = 0;
          } 
          int i12 = layoutParams.leftMargin + layoutParams.rightMargin;
          int i11 = view.getMeasuredWidth() + i12;
          i3 = Math.max(i3, i11);
          int i13 = View.combineMeasuredStates(i, view.getMeasuredState());
          if (j && layoutParams.width == -1) {
            i = 1;
          } else {
            i = 0;
          } 
          if (layoutParams.weight > 0.0F) {
            if (!i10)
              i12 = i11; 
            n = Math.max(n, i12);
            j = k;
            k = n;
          } else {
            if (i10)
              i11 = i12; 
            j = Math.max(k, i11);
            k = n;
          } 
          i11 = getChildrenSkipCount(view, i1);
          int i10 = i;
          n = k;
          k = j;
          i = i13;
          i1 = i11 + i1;
          j = i10;
        } else {
          throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
        } 
      } 
      i1++;
    } 
    if (this.mTotalLength > 0 && hasDividerBeforeChildAt(i4))
      this.mTotalLength += this.mDividerHeight; 
    if (bool1 && (i5 == Integer.MIN_VALUE || i5 == 0)) {
      this.mTotalLength = 0;
      for (i1 = 0; i1 < i4; i1++) {
        View view = getVirtualChildAt(i1);
        if (view == null) {
          this.mTotalLength += measureNullChild(i1);
        } else if (view.getVisibility() == 8) {
          i1 += getChildrenSkipCount(view, i1);
        } else {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          int i10 = this.mTotalLength;
          this.mTotalLength = Math.max(i10, i10 + m + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(view));
        } 
      } 
    } 
    this.mTotalLength += getPaddingTop() + getPaddingBottom();
    int i7 = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), paramInt2, 0);
    int i6 = (0xFFFFFF & i7) - this.mTotalLength;
    if (i2 != 0 || (i6 != 0 && f > 0.0F)) {
      float f1 = this.mWeightSum;
      if (f1 > 0.0F)
        f = f1; 
      this.mTotalLength = 0;
      i1 = 0;
      n = i6;
      m = i3;
      while (i1 < i4) {
        View view = getVirtualChildAt(i1);
        if (view.getVisibility() != 8) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          f1 = layoutParams.weight;
          if (f1 > 0.0F) {
            i3 = (int)(n * f1 / f);
            i6 = getPaddingLeft();
            int i10 = getPaddingRight();
            i2 = n - i3;
            i9 = layoutParams.leftMargin;
            int i11 = layoutParams.rightMargin;
            n = layoutParams.width;
            f -= f1;
            i6 = ViewGroup.getChildMeasureSpec(paramInt1, i6 + i10 + i9 + i11, n);
            if (layoutParams.height != 0 || i5 != 1073741824) {
              i3 = view.getMeasuredHeight() + i3;
              n = i3;
              if (i3 < 0)
                n = 0; 
              view.measure(i6, View.MeasureSpec.makeMeasureSpec(n, 1073741824));
            } else {
              if (i3 > 0) {
                n = i3;
              } else {
                n = 0;
              } 
              view.measure(i6, View.MeasureSpec.makeMeasureSpec(n, 1073741824));
            } 
            i = View.combineMeasuredStates(i, view.getMeasuredState() & 0xFFFFFF00);
            n = i2;
          } 
          i6 = layoutParams.leftMargin + layoutParams.rightMargin;
          i3 = view.getMeasuredWidth() + i6;
          i2 = Math.max(m, i3);
          if (i8 != 1073741824 && layoutParams.width == -1) {
            m = 1;
          } else {
            m = 0;
          } 
          if (m != 0) {
            m = i6;
          } else {
            m = i3;
          } 
          k = Math.max(k, m);
          if (j != 0 && layoutParams.width == -1) {
            j = 1;
          } else {
            j = 0;
          } 
          m = this.mTotalLength;
          this.mTotalLength = Math.max(m, view.getMeasuredHeight() + m + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(view));
          m = i2;
        } 
        i1++;
      } 
      this.mTotalLength += getPaddingTop() + getPaddingBottom();
      n = k;
      k = i;
      i = n;
    } else {
      n = Math.max(k, n);
      if (bool1 && i5 != 1073741824)
        for (k = 0; k < i4; k++) {
          View view = getVirtualChildAt(k);
          if (view != null && view.getVisibility() != 8 && ((LayoutParams)view.getLayoutParams()).weight > 0.0F)
            view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(m, 1073741824)); 
        }  
      k = i;
      i = n;
      m = i3;
    } 
    if (j != 0 || i8 == 1073741824)
      i = m; 
    setMeasuredDimension(View.resolveSizeAndState(Math.max(i + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), paramInt1, k), i7);
    if (bool)
      forceUniformWidth(i4, paramInt2); 
  }
  
  protected void onDraw(Canvas paramCanvas) {
    if (this.mDivider == null)
      return; 
    if (this.mOrientation == 1) {
      drawDividersVertical(paramCanvas);
    } else {
      drawDividersHorizontal(paramCanvas);
    } 
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.mOrientation == 1) {
      layoutVertical(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      layoutHorizontal(paramInt1, paramInt2, paramInt3, paramInt4);
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (this.mOrientation == 1) {
      measureVertical(paramInt1, paramInt2);
    } else {
      measureHorizontal(paramInt1, paramInt2);
    } 
  }
  
  public void setBaselineAligned(boolean paramBoolean) {
    this.mBaselineAligned = paramBoolean;
  }
  
  public void setBaselineAlignedChildIndex(int paramInt) {
    if (paramInt >= 0 && paramInt < getChildCount()) {
      this.mBaselineAlignedChildIndex = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("base aligned child index out of range (0, ");
    stringBuilder.append(getChildCount());
    stringBuilder.append(")");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setDividerDrawable(Drawable paramDrawable) {
    if (paramDrawable == this.mDivider)
      return; 
    this.mDivider = paramDrawable;
    boolean bool = false;
    if (paramDrawable != null) {
      this.mDividerWidth = paramDrawable.getIntrinsicWidth();
      this.mDividerHeight = paramDrawable.getIntrinsicHeight();
    } else {
      this.mDividerWidth = 0;
      this.mDividerHeight = 0;
    } 
    if (paramDrawable == null)
      bool = true; 
    setWillNotDraw(bool);
    requestLayout();
  }
  
  public void setDividerPadding(int paramInt) {
    this.mDividerPadding = paramInt;
  }
  
  public void setGravity(int paramInt) {
    if (this.mGravity != paramInt) {
      int i = paramInt;
      if ((0x800007 & paramInt) == 0)
        i = paramInt | 0x800003; 
      paramInt = i;
      if ((i & 0x70) == 0)
        paramInt = i | 0x30; 
      this.mGravity = paramInt;
      requestLayout();
    } 
  }
  
  public void setHorizontalGravity(int paramInt) {
    paramInt &= 0x800007;
    int i = this.mGravity;
    if ((0x800007 & i) != paramInt) {
      this.mGravity = paramInt | 0xFF7FFFF8 & i;
      requestLayout();
    } 
  }
  
  public void setMeasureWithLargestChildEnabled(boolean paramBoolean) {
    this.mUseLargestChild = paramBoolean;
  }
  
  public void setOrientation(int paramInt) {
    if (this.mOrientation != paramInt) {
      this.mOrientation = paramInt;
      requestLayout();
    } 
  }
  
  public void setShowDividers(int paramInt) {
    if (paramInt != this.mShowDividers)
      requestLayout(); 
    this.mShowDividers = paramInt;
  }
  
  public void setVerticalGravity(int paramInt) {
    paramInt &= 0x70;
    int i = this.mGravity;
    if ((i & 0x70) != paramInt) {
      this.mGravity = paramInt | i & 0xFFFFFF8F;
      requestLayout();
    } 
  }
  
  public void setWeightSum(float paramFloat) {
    this.mWeightSum = Math.max(0.0F, paramFloat);
  }
  
  public boolean shouldDelayChildPressedState() {
    return false;
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public int gravity = -1;
    
    public float weight;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.weight = 0.0F;
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.LinearLayoutCompat_Layout);
      this.weight = typedArray.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0F);
      this.gravity = typedArray.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
      typedArray.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/LinearLayoutCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */