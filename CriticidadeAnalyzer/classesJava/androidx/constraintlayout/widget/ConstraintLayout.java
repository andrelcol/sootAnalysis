package androidx.constraintlayout.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.Analyzer;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
  SparseArray<View> mChildrenByIds = new SparseArray();
  
  private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList<ConstraintHelper>(4);
  
  private ConstraintSet mConstraintSet = null;
  
  private int mConstraintSetId = -1;
  
  private HashMap<String, Integer> mDesignIds = new HashMap<String, Integer>();
  
  private boolean mDirtyHierarchy = true;
  
  private int mLastMeasureHeight = -1;
  
  private int mLastMeasureWidth = -1;
  
  ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
  
  private int mMaxHeight = Integer.MAX_VALUE;
  
  private int mMaxWidth = Integer.MAX_VALUE;
  
  private Metrics mMetrics;
  
  private int mMinHeight = 0;
  
  private int mMinWidth = 0;
  
  private int mOptimizationLevel = 7;
  
  private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<ConstraintWidget>(100);
  
  public ConstraintLayout(Context paramContext) {
    super(paramContext);
    init(null);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
  }
  
  private final ConstraintWidget getTargetWidget(int paramInt) {
    ConstraintWidget constraintWidget;
    if (paramInt == 0)
      return (ConstraintWidget)this.mLayoutWidget; 
    View view2 = (View)this.mChildrenByIds.get(paramInt);
    View view1 = view2;
    if (view2 == null) {
      view2 = findViewById(paramInt);
      view1 = view2;
      if (view2 != null) {
        view1 = view2;
        if (view2 != this) {
          view1 = view2;
          if (view2.getParent() == this) {
            onViewAdded(view2);
            view1 = view2;
          } 
        } 
      } 
    } 
    if (view1 == this)
      return (ConstraintWidget)this.mLayoutWidget; 
    if (view1 == null) {
      view1 = null;
    } else {
      constraintWidget = ((LayoutParams)view1.getLayoutParams()).widget;
    } 
    return constraintWidget;
  }
  
  private void init(AttributeSet paramAttributeSet) {
    this.mLayoutWidget.setCompanionWidget(this);
    this.mChildrenByIds.put(getId(), this);
    this.mConstraintSet = null;
    if (paramAttributeSet != null) {
      TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R$styleable.ConstraintLayout_Layout);
      int i = typedArray.getIndexCount();
      for (byte b = 0; b < i; b++) {
        int j = typedArray.getIndex(b);
        if (j == R$styleable.ConstraintLayout_Layout_android_minWidth) {
          this.mMinWidth = typedArray.getDimensionPixelOffset(j, this.mMinWidth);
        } else if (j == R$styleable.ConstraintLayout_Layout_android_minHeight) {
          this.mMinHeight = typedArray.getDimensionPixelOffset(j, this.mMinHeight);
        } else if (j == R$styleable.ConstraintLayout_Layout_android_maxWidth) {
          this.mMaxWidth = typedArray.getDimensionPixelOffset(j, this.mMaxWidth);
        } else if (j == R$styleable.ConstraintLayout_Layout_android_maxHeight) {
          this.mMaxHeight = typedArray.getDimensionPixelOffset(j, this.mMaxHeight);
        } else if (j == R$styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
          this.mOptimizationLevel = typedArray.getInt(j, this.mOptimizationLevel);
        } else if (j == R$styleable.ConstraintLayout_Layout_constraintSet) {
          j = typedArray.getResourceId(j, 0);
          try {
            ConstraintSet constraintSet = new ConstraintSet();
            this();
            this.mConstraintSet = constraintSet;
            this.mConstraintSet.load(getContext(), j);
          } catch (android.content.res.Resources.NotFoundException notFoundException) {
            this.mConstraintSet = null;
          } 
          this.mConstraintSetId = j;
        } 
      } 
      typedArray.recycle();
    } 
    this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
  }
  
  private void internalMeasureChildren(int paramInt1, int paramInt2) {
    int k = getPaddingTop() + getPaddingBottom();
    int j = getPaddingLeft() + getPaddingRight();
    int i = getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = getChildAt(b);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        ConstraintWidget constraintWidget = layoutParams.widget;
        if (!layoutParams.isGuideline && !layoutParams.isHelper) {
          int m;
          boolean bool;
          int n;
          int i1;
          constraintWidget.setVisibility(view.getVisibility());
          int i2 = layoutParams.width;
          int i3 = layoutParams.height;
          boolean bool1 = layoutParams.horizontalDimensionFixed;
          if (bool1 || layoutParams.verticalDimensionFixed || (!bool1 && layoutParams.matchConstraintDefaultWidth == 1) || layoutParams.width == -1 || (!layoutParams.verticalDimensionFixed && (layoutParams.matchConstraintDefaultHeight == 1 || layoutParams.height == -1))) {
            m = 1;
          } else {
            m = 0;
          } 
          if (m) {
            if (i2 == 0) {
              n = ViewGroup.getChildMeasureSpec(paramInt1, j, -2);
              m = 1;
            } else if (i2 == -1) {
              n = ViewGroup.getChildMeasureSpec(paramInt1, j, -1);
              m = 0;
            } else {
              if (i2 == -2) {
                m = 1;
              } else {
                m = 0;
              } 
              n = ViewGroup.getChildMeasureSpec(paramInt1, j, i2);
            } 
            if (i3 == 0) {
              i1 = ViewGroup.getChildMeasureSpec(paramInt2, k, -2);
              bool = true;
            } else if (i3 == -1) {
              i1 = ViewGroup.getChildMeasureSpec(paramInt2, k, -1);
              bool = false;
            } else {
              if (i3 == -2) {
                bool = true;
              } else {
                bool = false;
              } 
              i1 = ViewGroup.getChildMeasureSpec(paramInt2, k, i3);
            } 
            view.measure(n, i1);
            Metrics metrics = this.mMetrics;
            if (metrics != null)
              metrics.measures++; 
            if (i2 == -2) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            constraintWidget.setWidthWrapContent(bool1);
            if (i3 == -2) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            constraintWidget.setHeightWrapContent(bool1);
            i1 = view.getMeasuredWidth();
            n = view.getMeasuredHeight();
          } else {
            m = 0;
            bool = false;
            n = i3;
            i1 = i2;
          } 
          constraintWidget.setWidth(i1);
          constraintWidget.setHeight(n);
          if (m)
            constraintWidget.setWrapWidth(i1); 
          if (bool)
            constraintWidget.setWrapHeight(n); 
          if (layoutParams.needsBaseline) {
            m = view.getBaseline();
            if (m != -1)
              constraintWidget.setBaselineDistance(m); 
          } 
        } 
      } 
    } 
  }
  
  private void internalMeasureDimensions(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: astore #21
    //   3: aload_0
    //   4: invokevirtual getPaddingTop : ()I
    //   7: aload_0
    //   8: invokevirtual getPaddingBottom : ()I
    //   11: iadd
    //   12: istore #9
    //   14: aload_0
    //   15: invokevirtual getPaddingLeft : ()I
    //   18: aload_0
    //   19: invokevirtual getPaddingRight : ()I
    //   22: iadd
    //   23: istore #15
    //   25: aload_0
    //   26: invokevirtual getChildCount : ()I
    //   29: istore #10
    //   31: iconst_0
    //   32: istore_3
    //   33: lconst_1
    //   34: lstore #16
    //   36: iload_3
    //   37: iload #10
    //   39: if_icmpge -> 407
    //   42: aload #21
    //   44: iload_3
    //   45: invokevirtual getChildAt : (I)Landroid/view/View;
    //   48: astore #24
    //   50: aload #24
    //   52: invokevirtual getVisibility : ()I
    //   55: bipush #8
    //   57: if_icmpne -> 63
    //   60: goto -> 401
    //   63: aload #24
    //   65: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   68: checkcast androidx/constraintlayout/widget/ConstraintLayout$LayoutParams
    //   71: astore #23
    //   73: aload #23
    //   75: getfield widget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   78: astore #22
    //   80: aload #23
    //   82: getfield isGuideline : Z
    //   85: ifne -> 401
    //   88: aload #23
    //   90: getfield isHelper : Z
    //   93: ifeq -> 99
    //   96: goto -> 401
    //   99: aload #22
    //   101: aload #24
    //   103: invokevirtual getVisibility : ()I
    //   106: invokevirtual setVisibility : (I)V
    //   109: aload #23
    //   111: getfield width : I
    //   114: istore #6
    //   116: aload #23
    //   118: getfield height : I
    //   121: istore #7
    //   123: iload #6
    //   125: ifeq -> 382
    //   128: iload #7
    //   130: ifne -> 136
    //   133: goto -> 382
    //   136: iload #6
    //   138: bipush #-2
    //   140: if_icmpne -> 149
    //   143: iconst_1
    //   144: istore #4
    //   146: goto -> 152
    //   149: iconst_0
    //   150: istore #4
    //   152: iload_1
    //   153: iload #15
    //   155: iload #6
    //   157: invokestatic getChildMeasureSpec : (III)I
    //   160: istore #8
    //   162: iload #7
    //   164: bipush #-2
    //   166: if_icmpne -> 175
    //   169: iconst_1
    //   170: istore #5
    //   172: goto -> 178
    //   175: iconst_0
    //   176: istore #5
    //   178: aload #24
    //   180: iload #8
    //   182: iload_2
    //   183: iload #9
    //   185: iload #7
    //   187: invokestatic getChildMeasureSpec : (III)I
    //   190: invokevirtual measure : (II)V
    //   193: aload #21
    //   195: getfield mMetrics : Landroidx/constraintlayout/solver/Metrics;
    //   198: astore #25
    //   200: aload #25
    //   202: ifnull -> 217
    //   205: aload #25
    //   207: aload #25
    //   209: getfield measures : J
    //   212: lconst_1
    //   213: ladd
    //   214: putfield measures : J
    //   217: iload #6
    //   219: bipush #-2
    //   221: if_icmpne -> 230
    //   224: iconst_1
    //   225: istore #20
    //   227: goto -> 233
    //   230: iconst_0
    //   231: istore #20
    //   233: aload #22
    //   235: iload #20
    //   237: invokevirtual setWidthWrapContent : (Z)V
    //   240: iload #7
    //   242: bipush #-2
    //   244: if_icmpne -> 253
    //   247: iconst_1
    //   248: istore #20
    //   250: goto -> 256
    //   253: iconst_0
    //   254: istore #20
    //   256: aload #22
    //   258: iload #20
    //   260: invokevirtual setHeightWrapContent : (Z)V
    //   263: aload #24
    //   265: invokevirtual getMeasuredWidth : ()I
    //   268: istore #7
    //   270: aload #24
    //   272: invokevirtual getMeasuredHeight : ()I
    //   275: istore #6
    //   277: aload #22
    //   279: iload #7
    //   281: invokevirtual setWidth : (I)V
    //   284: aload #22
    //   286: iload #6
    //   288: invokevirtual setHeight : (I)V
    //   291: iload #4
    //   293: ifeq -> 303
    //   296: aload #22
    //   298: iload #7
    //   300: invokevirtual setWrapWidth : (I)V
    //   303: iload #5
    //   305: ifeq -> 315
    //   308: aload #22
    //   310: iload #6
    //   312: invokevirtual setWrapHeight : (I)V
    //   315: aload #23
    //   317: getfield needsBaseline : Z
    //   320: ifeq -> 343
    //   323: aload #24
    //   325: invokevirtual getBaseline : ()I
    //   328: istore #4
    //   330: iload #4
    //   332: iconst_m1
    //   333: if_icmpeq -> 343
    //   336: aload #22
    //   338: iload #4
    //   340: invokevirtual setBaselineDistance : (I)V
    //   343: aload #23
    //   345: getfield horizontalDimensionFixed : Z
    //   348: ifeq -> 401
    //   351: aload #23
    //   353: getfield verticalDimensionFixed : Z
    //   356: ifeq -> 401
    //   359: aload #22
    //   361: invokevirtual getResolutionWidth : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   364: iload #7
    //   366: invokevirtual resolve : (I)V
    //   369: aload #22
    //   371: invokevirtual getResolutionHeight : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   374: iload #6
    //   376: invokevirtual resolve : (I)V
    //   379: goto -> 401
    //   382: aload #22
    //   384: invokevirtual getResolutionWidth : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   387: invokevirtual invalidate : ()V
    //   390: aload #22
    //   392: invokevirtual getResolutionHeight : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   395: invokevirtual invalidate : ()V
    //   398: goto -> 401
    //   401: iinc #3, 1
    //   404: goto -> 33
    //   407: aload #21
    //   409: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   412: invokevirtual solveGraph : ()V
    //   415: iconst_0
    //   416: istore #11
    //   418: iload #11
    //   420: iload #10
    //   422: if_icmpge -> 1319
    //   425: aload #21
    //   427: iload #11
    //   429: invokevirtual getChildAt : (I)Landroid/view/View;
    //   432: astore #22
    //   434: aload #22
    //   436: invokevirtual getVisibility : ()I
    //   439: bipush #8
    //   441: if_icmpne -> 447
    //   444: goto -> 1305
    //   447: aload #22
    //   449: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   452: checkcast androidx/constraintlayout/widget/ConstraintLayout$LayoutParams
    //   455: astore #24
    //   457: aload #24
    //   459: getfield widget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   462: astore #23
    //   464: aload #24
    //   466: getfield isGuideline : Z
    //   469: ifne -> 1305
    //   472: aload #24
    //   474: getfield isHelper : Z
    //   477: ifeq -> 483
    //   480: goto -> 1305
    //   483: aload #23
    //   485: aload #22
    //   487: invokevirtual getVisibility : ()I
    //   490: invokevirtual setVisibility : (I)V
    //   493: aload #24
    //   495: getfield width : I
    //   498: istore #7
    //   500: aload #24
    //   502: getfield height : I
    //   505: istore #8
    //   507: iload #7
    //   509: ifeq -> 520
    //   512: iload #8
    //   514: ifeq -> 520
    //   517: goto -> 1305
    //   520: aload #23
    //   522: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.LEFT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   525: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   528: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   531: astore #28
    //   533: aload #23
    //   535: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   538: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   541: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   544: astore #26
    //   546: aload #23
    //   548: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.LEFT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   551: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   554: invokevirtual getTarget : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   557: ifnull -> 580
    //   560: aload #23
    //   562: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   565: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   568: invokevirtual getTarget : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   571: ifnull -> 580
    //   574: iconst_1
    //   575: istore #4
    //   577: goto -> 583
    //   580: iconst_0
    //   581: istore #4
    //   583: aload #23
    //   585: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   588: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   591: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   594: astore #27
    //   596: aload #23
    //   598: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   601: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   604: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   607: astore #25
    //   609: aload #23
    //   611: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   614: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   617: invokevirtual getTarget : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   620: ifnull -> 643
    //   623: aload #23
    //   625: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   628: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   631: invokevirtual getTarget : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   634: ifnull -> 643
    //   637: iconst_1
    //   638: istore #14
    //   640: goto -> 646
    //   643: iconst_0
    //   644: istore #14
    //   646: iload #7
    //   648: ifne -> 676
    //   651: iload #8
    //   653: ifne -> 676
    //   656: iload #4
    //   658: ifeq -> 676
    //   661: iload #14
    //   663: ifeq -> 676
    //   666: lconst_1
    //   667: lstore #16
    //   669: aload #21
    //   671: astore #22
    //   673: goto -> 1309
    //   676: aload #21
    //   678: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   681: invokevirtual getHorizontalDimensionBehaviour : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   684: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   687: if_acmpeq -> 696
    //   690: iconst_1
    //   691: istore #6
    //   693: goto -> 699
    //   696: iconst_0
    //   697: istore #6
    //   699: aload #21
    //   701: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   704: invokevirtual getVerticalDimensionBehaviour : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   707: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   710: if_acmpeq -> 718
    //   713: iconst_1
    //   714: istore_3
    //   715: goto -> 720
    //   718: iconst_0
    //   719: istore_3
    //   720: iload #6
    //   722: ifne -> 733
    //   725: aload #23
    //   727: invokevirtual getResolutionWidth : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   730: invokevirtual invalidate : ()V
    //   733: iload_3
    //   734: ifne -> 745
    //   737: aload #23
    //   739: invokevirtual getResolutionHeight : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   742: invokevirtual invalidate : ()V
    //   745: iload #7
    //   747: ifne -> 844
    //   750: iload #6
    //   752: ifeq -> 821
    //   755: aload #23
    //   757: invokevirtual isSpreadWidth : ()Z
    //   760: ifeq -> 821
    //   763: iload #4
    //   765: ifeq -> 821
    //   768: aload #28
    //   770: invokevirtual isResolved : ()Z
    //   773: ifeq -> 821
    //   776: aload #26
    //   778: invokevirtual isResolved : ()Z
    //   781: ifeq -> 821
    //   784: aload #26
    //   786: invokevirtual getResolvedValue : ()F
    //   789: aload #28
    //   791: invokevirtual getResolvedValue : ()F
    //   794: fsub
    //   795: f2i
    //   796: istore #7
    //   798: aload #23
    //   800: invokevirtual getResolutionWidth : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   803: iload #7
    //   805: invokevirtual resolve : (I)V
    //   808: iload_1
    //   809: iload #15
    //   811: iload #7
    //   813: invokestatic getChildMeasureSpec : (III)I
    //   816: istore #5
    //   818: goto -> 859
    //   821: iload_1
    //   822: iload #15
    //   824: bipush #-2
    //   826: invokestatic getChildMeasureSpec : (III)I
    //   829: istore #5
    //   831: iconst_1
    //   832: istore #4
    //   834: iconst_0
    //   835: istore #12
    //   837: iload #7
    //   839: istore #13
    //   841: goto -> 907
    //   844: iload #7
    //   846: iconst_m1
    //   847: if_icmpne -> 873
    //   850: iload_1
    //   851: iload #15
    //   853: iconst_m1
    //   854: invokestatic getChildMeasureSpec : (III)I
    //   857: istore #5
    //   859: iconst_0
    //   860: istore #4
    //   862: iload #6
    //   864: istore #12
    //   866: iload #7
    //   868: istore #13
    //   870: goto -> 907
    //   873: iload #7
    //   875: bipush #-2
    //   877: if_icmpne -> 886
    //   880: iconst_1
    //   881: istore #4
    //   883: goto -> 889
    //   886: iconst_0
    //   887: istore #4
    //   889: iload_1
    //   890: iload #15
    //   892: iload #7
    //   894: invokestatic getChildMeasureSpec : (III)I
    //   897: istore #5
    //   899: iload #7
    //   901: istore #13
    //   903: iload #6
    //   905: istore #12
    //   907: iload #8
    //   909: ifne -> 1000
    //   912: iload_3
    //   913: ifeq -> 982
    //   916: aload #23
    //   918: invokevirtual isSpreadHeight : ()Z
    //   921: ifeq -> 982
    //   924: iload #14
    //   926: ifeq -> 982
    //   929: aload #27
    //   931: invokevirtual isResolved : ()Z
    //   934: ifeq -> 982
    //   937: aload #25
    //   939: invokevirtual isResolved : ()Z
    //   942: ifeq -> 982
    //   945: aload #25
    //   947: invokevirtual getResolvedValue : ()F
    //   950: aload #27
    //   952: invokevirtual getResolvedValue : ()F
    //   955: fsub
    //   956: f2i
    //   957: istore #8
    //   959: aload #23
    //   961: invokevirtual getResolutionHeight : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   964: iload #8
    //   966: invokevirtual resolve : (I)V
    //   969: iload_2
    //   970: iload #9
    //   972: iload #8
    //   974: invokestatic getChildMeasureSpec : (III)I
    //   977: istore #6
    //   979: goto -> 1015
    //   982: iload_2
    //   983: iload #9
    //   985: bipush #-2
    //   987: invokestatic getChildMeasureSpec : (III)I
    //   990: istore #6
    //   992: iconst_1
    //   993: istore_3
    //   994: iconst_0
    //   995: istore #7
    //   997: goto -> 1059
    //   1000: iload #8
    //   1002: iconst_m1
    //   1003: if_icmpne -> 1023
    //   1006: iload_2
    //   1007: iload #9
    //   1009: iconst_m1
    //   1010: invokestatic getChildMeasureSpec : (III)I
    //   1013: istore #6
    //   1015: iload_3
    //   1016: istore #7
    //   1018: iconst_0
    //   1019: istore_3
    //   1020: goto -> 1059
    //   1023: iload #8
    //   1025: bipush #-2
    //   1027: if_icmpne -> 1036
    //   1030: iconst_1
    //   1031: istore #6
    //   1033: goto -> 1039
    //   1036: iconst_0
    //   1037: istore #6
    //   1039: iload_2
    //   1040: iload #9
    //   1042: iload #8
    //   1044: invokestatic getChildMeasureSpec : (III)I
    //   1047: istore #14
    //   1049: iload_3
    //   1050: istore #7
    //   1052: iload #6
    //   1054: istore_3
    //   1055: iload #14
    //   1057: istore #6
    //   1059: aload #22
    //   1061: iload #5
    //   1063: iload #6
    //   1065: invokevirtual measure : (II)V
    //   1068: aload_0
    //   1069: astore #21
    //   1071: aload #21
    //   1073: getfield mMetrics : Landroidx/constraintlayout/solver/Metrics;
    //   1076: astore #25
    //   1078: aload #25
    //   1080: ifnull -> 1098
    //   1083: aload #25
    //   1085: aload #25
    //   1087: getfield measures : J
    //   1090: lconst_1
    //   1091: ladd
    //   1092: putfield measures : J
    //   1095: goto -> 1098
    //   1098: lconst_1
    //   1099: lstore #18
    //   1101: iload #13
    //   1103: bipush #-2
    //   1105: if_icmpne -> 1114
    //   1108: iconst_1
    //   1109: istore #20
    //   1111: goto -> 1117
    //   1114: iconst_0
    //   1115: istore #20
    //   1117: aload #23
    //   1119: iload #20
    //   1121: invokevirtual setWidthWrapContent : (Z)V
    //   1124: iload #8
    //   1126: bipush #-2
    //   1128: if_icmpne -> 1137
    //   1131: iconst_1
    //   1132: istore #20
    //   1134: goto -> 1140
    //   1137: iconst_0
    //   1138: istore #20
    //   1140: aload #23
    //   1142: iload #20
    //   1144: invokevirtual setHeightWrapContent : (Z)V
    //   1147: aload #22
    //   1149: invokevirtual getMeasuredWidth : ()I
    //   1152: istore #6
    //   1154: aload #22
    //   1156: invokevirtual getMeasuredHeight : ()I
    //   1159: istore #5
    //   1161: aload #23
    //   1163: iload #6
    //   1165: invokevirtual setWidth : (I)V
    //   1168: aload #23
    //   1170: iload #5
    //   1172: invokevirtual setHeight : (I)V
    //   1175: iload #4
    //   1177: ifeq -> 1187
    //   1180: aload #23
    //   1182: iload #6
    //   1184: invokevirtual setWrapWidth : (I)V
    //   1187: iload_3
    //   1188: ifeq -> 1198
    //   1191: aload #23
    //   1193: iload #5
    //   1195: invokevirtual setWrapHeight : (I)V
    //   1198: iload #12
    //   1200: ifeq -> 1216
    //   1203: aload #23
    //   1205: invokevirtual getResolutionWidth : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   1208: iload #6
    //   1210: invokevirtual resolve : (I)V
    //   1213: goto -> 1224
    //   1216: aload #23
    //   1218: invokevirtual getResolutionWidth : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   1221: invokevirtual remove : ()V
    //   1224: iload #7
    //   1226: ifeq -> 1242
    //   1229: aload #23
    //   1231: invokevirtual getResolutionHeight : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   1234: iload #5
    //   1236: invokevirtual resolve : (I)V
    //   1239: goto -> 1250
    //   1242: aload #23
    //   1244: invokevirtual getResolutionHeight : ()Landroidx/constraintlayout/solver/widgets/ResolutionDimension;
    //   1247: invokevirtual remove : ()V
    //   1250: aload #24
    //   1252: getfield needsBaseline : Z
    //   1255: ifeq -> 1294
    //   1258: aload #22
    //   1260: invokevirtual getBaseline : ()I
    //   1263: istore_3
    //   1264: aload #21
    //   1266: astore #22
    //   1268: lload #18
    //   1270: lstore #16
    //   1272: iload_3
    //   1273: iconst_m1
    //   1274: if_icmpeq -> 1309
    //   1277: aload #23
    //   1279: iload_3
    //   1280: invokevirtual setBaselineDistance : (I)V
    //   1283: aload #21
    //   1285: astore #22
    //   1287: lload #18
    //   1289: lstore #16
    //   1291: goto -> 1309
    //   1294: aload #21
    //   1296: astore #22
    //   1298: lload #18
    //   1300: lstore #16
    //   1302: goto -> 1309
    //   1305: aload #21
    //   1307: astore #22
    //   1309: iinc #11, 1
    //   1312: aload #22
    //   1314: astore #21
    //   1316: goto -> 418
    //   1319: return
  }
  
  private void setChildrenConstraints() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual isInEditMode : ()Z
    //   4: istore #13
    //   6: aload_0
    //   7: invokevirtual getChildCount : ()I
    //   10: istore #12
    //   12: iconst_0
    //   13: istore_3
    //   14: iload #13
    //   16: ifeq -> 113
    //   19: iconst_0
    //   20: istore_2
    //   21: iload_2
    //   22: iload #12
    //   24: if_icmpge -> 113
    //   27: aload_0
    //   28: iload_2
    //   29: invokevirtual getChildAt : (I)Landroid/view/View;
    //   32: astore #16
    //   34: aload_0
    //   35: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   38: aload #16
    //   40: invokevirtual getId : ()I
    //   43: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   46: astore #15
    //   48: aload_0
    //   49: iconst_0
    //   50: aload #15
    //   52: aload #16
    //   54: invokevirtual getId : ()I
    //   57: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   60: invokevirtual setDesignInformation : (ILjava/lang/Object;Ljava/lang/Object;)V
    //   63: aload #15
    //   65: bipush #47
    //   67: invokevirtual indexOf : (I)I
    //   70: istore #4
    //   72: aload #15
    //   74: astore #14
    //   76: iload #4
    //   78: iconst_m1
    //   79: if_icmpeq -> 93
    //   82: aload #15
    //   84: iload #4
    //   86: iconst_1
    //   87: iadd
    //   88: invokevirtual substring : (I)Ljava/lang/String;
    //   91: astore #14
    //   93: aload_0
    //   94: aload #16
    //   96: invokevirtual getId : ()I
    //   99: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   102: aload #14
    //   104: invokevirtual setDebugName : (Ljava/lang/String;)V
    //   107: iinc #2, 1
    //   110: goto -> 21
    //   113: iconst_0
    //   114: istore_2
    //   115: iload_2
    //   116: iload #12
    //   118: if_icmpge -> 151
    //   121: aload_0
    //   122: aload_0
    //   123: iload_2
    //   124: invokevirtual getChildAt : (I)Landroid/view/View;
    //   127: invokevirtual getViewWidget : (Landroid/view/View;)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   130: astore #14
    //   132: aload #14
    //   134: ifnonnull -> 140
    //   137: goto -> 145
    //   140: aload #14
    //   142: invokevirtual reset : ()V
    //   145: iinc #2, 1
    //   148: goto -> 115
    //   151: aload_0
    //   152: getfield mConstraintSetId : I
    //   155: iconst_m1
    //   156: if_icmpeq -> 212
    //   159: iconst_0
    //   160: istore_2
    //   161: iload_2
    //   162: iload #12
    //   164: if_icmpge -> 212
    //   167: aload_0
    //   168: iload_2
    //   169: invokevirtual getChildAt : (I)Landroid/view/View;
    //   172: astore #14
    //   174: aload #14
    //   176: invokevirtual getId : ()I
    //   179: aload_0
    //   180: getfield mConstraintSetId : I
    //   183: if_icmpne -> 206
    //   186: aload #14
    //   188: instanceof androidx/constraintlayout/widget/Constraints
    //   191: ifeq -> 206
    //   194: aload_0
    //   195: aload #14
    //   197: checkcast androidx/constraintlayout/widget/Constraints
    //   200: invokevirtual getConstraintSet : ()Landroidx/constraintlayout/widget/ConstraintSet;
    //   203: putfield mConstraintSet : Landroidx/constraintlayout/widget/ConstraintSet;
    //   206: iinc #2, 1
    //   209: goto -> 161
    //   212: aload_0
    //   213: getfield mConstraintSet : Landroidx/constraintlayout/widget/ConstraintSet;
    //   216: astore #14
    //   218: aload #14
    //   220: ifnull -> 229
    //   223: aload #14
    //   225: aload_0
    //   226: invokevirtual applyToInternal : (Landroidx/constraintlayout/widget/ConstraintLayout;)V
    //   229: aload_0
    //   230: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   233: invokevirtual removeAllChildren : ()V
    //   236: aload_0
    //   237: getfield mConstraintHelpers : Ljava/util/ArrayList;
    //   240: invokevirtual size : ()I
    //   243: istore #4
    //   245: iload #4
    //   247: ifle -> 279
    //   250: iconst_0
    //   251: istore_2
    //   252: iload_2
    //   253: iload #4
    //   255: if_icmpge -> 279
    //   258: aload_0
    //   259: getfield mConstraintHelpers : Ljava/util/ArrayList;
    //   262: iload_2
    //   263: invokevirtual get : (I)Ljava/lang/Object;
    //   266: checkcast androidx/constraintlayout/widget/ConstraintHelper
    //   269: aload_0
    //   270: invokevirtual updatePreLayout : (Landroidx/constraintlayout/widget/ConstraintLayout;)V
    //   273: iinc #2, 1
    //   276: goto -> 252
    //   279: iconst_0
    //   280: istore_2
    //   281: iload_2
    //   282: iload #12
    //   284: if_icmpge -> 317
    //   287: aload_0
    //   288: iload_2
    //   289: invokevirtual getChildAt : (I)Landroid/view/View;
    //   292: astore #14
    //   294: aload #14
    //   296: instanceof androidx/constraintlayout/widget/Placeholder
    //   299: ifeq -> 311
    //   302: aload #14
    //   304: checkcast androidx/constraintlayout/widget/Placeholder
    //   307: aload_0
    //   308: invokevirtual updatePreLayout : (Landroidx/constraintlayout/widget/ConstraintLayout;)V
    //   311: iinc #2, 1
    //   314: goto -> 281
    //   317: iconst_0
    //   318: istore #8
    //   320: iload_3
    //   321: istore_2
    //   322: iload #8
    //   324: iload #12
    //   326: if_icmpge -> 2016
    //   329: aload_0
    //   330: iload #8
    //   332: invokevirtual getChildAt : (I)Landroid/view/View;
    //   335: astore #16
    //   337: aload_0
    //   338: aload #16
    //   340: invokevirtual getViewWidget : (Landroid/view/View;)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   343: astore #15
    //   345: aload #15
    //   347: ifnonnull -> 356
    //   350: iload_2
    //   351: istore #4
    //   353: goto -> 2007
    //   356: aload #16
    //   358: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   361: checkcast androidx/constraintlayout/widget/ConstraintLayout$LayoutParams
    //   364: astore #14
    //   366: aload #14
    //   368: invokevirtual validate : ()V
    //   371: aload #14
    //   373: getfield helped : Z
    //   376: ifeq -> 388
    //   379: aload #14
    //   381: iload_2
    //   382: putfield helped : Z
    //   385: goto -> 458
    //   388: iload #13
    //   390: ifeq -> 458
    //   393: aload_0
    //   394: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   397: aload #16
    //   399: invokevirtual getId : ()I
    //   402: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   405: astore #17
    //   407: aload_0
    //   408: iload_2
    //   409: aload #17
    //   411: aload #16
    //   413: invokevirtual getId : ()I
    //   416: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   419: invokevirtual setDesignInformation : (ILjava/lang/Object;Ljava/lang/Object;)V
    //   422: aload #17
    //   424: aload #17
    //   426: ldc_w 'id/'
    //   429: invokevirtual indexOf : (Ljava/lang/String;)I
    //   432: iconst_3
    //   433: iadd
    //   434: invokevirtual substring : (I)Ljava/lang/String;
    //   437: astore #17
    //   439: aload_0
    //   440: aload #16
    //   442: invokevirtual getId : ()I
    //   445: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   448: aload #17
    //   450: invokevirtual setDebugName : (Ljava/lang/String;)V
    //   453: goto -> 458
    //   456: astore #17
    //   458: aload #15
    //   460: aload #16
    //   462: invokevirtual getVisibility : ()I
    //   465: invokevirtual setVisibility : (I)V
    //   468: aload #14
    //   470: getfield isInPlaceholder : Z
    //   473: ifeq -> 483
    //   476: aload #15
    //   478: bipush #8
    //   480: invokevirtual setVisibility : (I)V
    //   483: aload #15
    //   485: aload #16
    //   487: invokevirtual setCompanionWidget : (Ljava/lang/Object;)V
    //   490: aload_0
    //   491: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   494: aload #15
    //   496: invokevirtual add : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;)V
    //   499: aload #14
    //   501: getfield verticalDimensionFixed : Z
    //   504: ifeq -> 515
    //   507: aload #14
    //   509: getfield horizontalDimensionFixed : Z
    //   512: ifne -> 525
    //   515: aload_0
    //   516: getfield mVariableDimensionsWidgets : Ljava/util/ArrayList;
    //   519: aload #15
    //   521: invokevirtual add : (Ljava/lang/Object;)Z
    //   524: pop
    //   525: aload #14
    //   527: getfield isGuideline : Z
    //   530: ifeq -> 645
    //   533: aload #15
    //   535: checkcast androidx/constraintlayout/solver/widgets/Guideline
    //   538: astore #15
    //   540: aload #14
    //   542: getfield resolvedGuideBegin : I
    //   545: istore #4
    //   547: aload #14
    //   549: getfield resolvedGuideEnd : I
    //   552: istore_3
    //   553: aload #14
    //   555: getfield resolvedGuidePercent : F
    //   558: fstore_1
    //   559: getstatic android/os/Build$VERSION.SDK_INT : I
    //   562: bipush #17
    //   564: if_icmpge -> 586
    //   567: aload #14
    //   569: getfield guideBegin : I
    //   572: istore #4
    //   574: aload #14
    //   576: getfield guideEnd : I
    //   579: istore_3
    //   580: aload #14
    //   582: getfield guidePercent : F
    //   585: fstore_1
    //   586: fload_1
    //   587: ldc_w -1.0
    //   590: fcmpl
    //   591: ifeq -> 606
    //   594: aload #15
    //   596: fload_1
    //   597: invokevirtual setGuidePercent : (F)V
    //   600: iload_2
    //   601: istore #4
    //   603: goto -> 2007
    //   606: iload #4
    //   608: iconst_m1
    //   609: if_icmpeq -> 625
    //   612: aload #15
    //   614: iload #4
    //   616: invokevirtual setGuideBegin : (I)V
    //   619: iload_2
    //   620: istore #4
    //   622: goto -> 2007
    //   625: iload_2
    //   626: istore #4
    //   628: iload_3
    //   629: iconst_m1
    //   630: if_icmpeq -> 2007
    //   633: aload #15
    //   635: iload_3
    //   636: invokevirtual setGuideEnd : (I)V
    //   639: iload_2
    //   640: istore #4
    //   642: goto -> 2007
    //   645: aload #14
    //   647: getfield leftToLeft : I
    //   650: iconst_m1
    //   651: if_icmpne -> 810
    //   654: aload #14
    //   656: getfield leftToRight : I
    //   659: iconst_m1
    //   660: if_icmpne -> 810
    //   663: aload #14
    //   665: getfield rightToLeft : I
    //   668: iconst_m1
    //   669: if_icmpne -> 810
    //   672: aload #14
    //   674: getfield rightToRight : I
    //   677: iconst_m1
    //   678: if_icmpne -> 810
    //   681: aload #14
    //   683: getfield startToStart : I
    //   686: iconst_m1
    //   687: if_icmpne -> 810
    //   690: aload #14
    //   692: getfield startToEnd : I
    //   695: iconst_m1
    //   696: if_icmpne -> 810
    //   699: aload #14
    //   701: getfield endToStart : I
    //   704: iconst_m1
    //   705: if_icmpne -> 810
    //   708: aload #14
    //   710: getfield endToEnd : I
    //   713: iconst_m1
    //   714: if_icmpne -> 810
    //   717: aload #14
    //   719: getfield topToTop : I
    //   722: iconst_m1
    //   723: if_icmpne -> 810
    //   726: aload #14
    //   728: getfield topToBottom : I
    //   731: iconst_m1
    //   732: if_icmpne -> 810
    //   735: aload #14
    //   737: getfield bottomToTop : I
    //   740: iconst_m1
    //   741: if_icmpne -> 810
    //   744: aload #14
    //   746: getfield bottomToBottom : I
    //   749: iconst_m1
    //   750: if_icmpne -> 810
    //   753: aload #14
    //   755: getfield baselineToBaseline : I
    //   758: iconst_m1
    //   759: if_icmpne -> 810
    //   762: aload #14
    //   764: getfield editorAbsoluteX : I
    //   767: iconst_m1
    //   768: if_icmpne -> 810
    //   771: aload #14
    //   773: getfield editorAbsoluteY : I
    //   776: iconst_m1
    //   777: if_icmpne -> 810
    //   780: aload #14
    //   782: getfield circleConstraint : I
    //   785: iconst_m1
    //   786: if_icmpne -> 810
    //   789: aload #14
    //   791: getfield width : I
    //   794: iconst_m1
    //   795: if_icmpeq -> 810
    //   798: iload_2
    //   799: istore #4
    //   801: aload #14
    //   803: getfield height : I
    //   806: iconst_m1
    //   807: if_icmpne -> 2007
    //   810: aload #14
    //   812: getfield resolvedLeftToLeft : I
    //   815: istore #5
    //   817: aload #14
    //   819: getfield resolvedLeftToRight : I
    //   822: istore #6
    //   824: aload #14
    //   826: getfield resolvedRightToLeft : I
    //   829: istore #4
    //   831: aload #14
    //   833: getfield resolvedRightToRight : I
    //   836: istore #7
    //   838: aload #14
    //   840: getfield resolveGoneLeftMargin : I
    //   843: istore_2
    //   844: aload #14
    //   846: getfield resolveGoneRightMargin : I
    //   849: istore_3
    //   850: aload #14
    //   852: getfield resolvedHorizontalBias : F
    //   855: fstore_1
    //   856: getstatic android/os/Build$VERSION.SDK_INT : I
    //   859: bipush #17
    //   861: if_icmpge -> 1073
    //   864: aload #14
    //   866: getfield leftToLeft : I
    //   869: istore_3
    //   870: aload #14
    //   872: getfield leftToRight : I
    //   875: istore_2
    //   876: aload #14
    //   878: getfield rightToLeft : I
    //   881: istore #6
    //   883: aload #14
    //   885: getfield rightToRight : I
    //   888: istore #7
    //   890: aload #14
    //   892: getfield goneLeftMargin : I
    //   895: istore #5
    //   897: aload #14
    //   899: getfield goneRightMargin : I
    //   902: istore #4
    //   904: aload #14
    //   906: getfield horizontalBias : F
    //   909: fstore_1
    //   910: iload_3
    //   911: iconst_m1
    //   912: if_icmpne -> 958
    //   915: iload_2
    //   916: iconst_m1
    //   917: if_icmpne -> 958
    //   920: aload #14
    //   922: getfield startToStart : I
    //   925: istore #9
    //   927: iload #9
    //   929: iconst_m1
    //   930: if_icmpeq -> 939
    //   933: iload #9
    //   935: istore_3
    //   936: goto -> 958
    //   939: aload #14
    //   941: getfield startToEnd : I
    //   944: istore #9
    //   946: iload #9
    //   948: iconst_m1
    //   949: if_icmpeq -> 958
    //   952: iload #9
    //   954: istore_2
    //   955: goto -> 958
    //   958: iload #6
    //   960: iconst_m1
    //   961: if_icmpne -> 1046
    //   964: iload #7
    //   966: iconst_m1
    //   967: if_icmpne -> 1046
    //   970: aload #14
    //   972: getfield endToStart : I
    //   975: istore #11
    //   977: iload #11
    //   979: iconst_m1
    //   980: if_icmpeq -> 1010
    //   983: iload #5
    //   985: istore #9
    //   987: iload #4
    //   989: istore #10
    //   991: iload #11
    //   993: istore #4
    //   995: iload_3
    //   996: istore #5
    //   998: iload_2
    //   999: istore #6
    //   1001: iload #9
    //   1003: istore_2
    //   1004: iload #10
    //   1006: istore_3
    //   1007: goto -> 1073
    //   1010: aload #14
    //   1012: getfield endToEnd : I
    //   1015: istore #9
    //   1017: iload #9
    //   1019: iconst_m1
    //   1020: if_icmpeq -> 1046
    //   1023: iload #9
    //   1025: istore #7
    //   1027: iload #6
    //   1029: istore #10
    //   1031: iload_3
    //   1032: istore #6
    //   1034: iload_2
    //   1035: istore #9
    //   1037: iload #5
    //   1039: istore_2
    //   1040: iload #4
    //   1042: istore_3
    //   1043: goto -> 1085
    //   1046: iload #5
    //   1048: istore #10
    //   1050: iload #4
    //   1052: istore #9
    //   1054: iload #6
    //   1056: istore #4
    //   1058: iload_3
    //   1059: istore #5
    //   1061: iload_2
    //   1062: istore #6
    //   1064: iload #10
    //   1066: istore_2
    //   1067: iload #9
    //   1069: istore_3
    //   1070: goto -> 1073
    //   1073: iload #6
    //   1075: istore #9
    //   1077: iload #5
    //   1079: istore #6
    //   1081: iload #4
    //   1083: istore #10
    //   1085: aload #14
    //   1087: getfield circleConstraint : I
    //   1090: istore #4
    //   1092: iload #4
    //   1094: iconst_m1
    //   1095: if_icmpeq -> 1131
    //   1098: aload_0
    //   1099: iload #4
    //   1101: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1104: astore #16
    //   1106: aload #16
    //   1108: ifnull -> 1667
    //   1111: aload #15
    //   1113: aload #16
    //   1115: aload #14
    //   1117: getfield circleAngle : F
    //   1120: aload #14
    //   1122: getfield circleRadius : I
    //   1125: invokevirtual connectCircularConstraint : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;FI)V
    //   1128: goto -> 1667
    //   1131: iload #6
    //   1133: iconst_m1
    //   1134: if_icmpeq -> 1178
    //   1137: aload_0
    //   1138: iload #6
    //   1140: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1143: astore #17
    //   1145: aload #17
    //   1147: ifnull -> 1175
    //   1150: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.LEFT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1153: astore #16
    //   1155: aload #15
    //   1157: aload #16
    //   1159: aload #17
    //   1161: aload #16
    //   1163: aload #14
    //   1165: getfield leftMargin : I
    //   1168: iload_2
    //   1169: invokevirtual immediateConnect : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;II)V
    //   1172: goto -> 1216
    //   1175: goto -> 1216
    //   1178: iload #9
    //   1180: iconst_m1
    //   1181: if_icmpeq -> 1216
    //   1184: aload_0
    //   1185: iload #9
    //   1187: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1190: astore #16
    //   1192: aload #16
    //   1194: ifnull -> 1216
    //   1197: aload #15
    //   1199: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.LEFT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1202: aload #16
    //   1204: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1207: aload #14
    //   1209: getfield leftMargin : I
    //   1212: iload_2
    //   1213: invokevirtual immediateConnect : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;II)V
    //   1216: iload #10
    //   1218: iconst_m1
    //   1219: if_icmpeq -> 1257
    //   1222: aload_0
    //   1223: iload #10
    //   1225: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1228: astore #16
    //   1230: aload #16
    //   1232: ifnull -> 1298
    //   1235: aload #15
    //   1237: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1240: aload #16
    //   1242: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.LEFT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1245: aload #14
    //   1247: getfield rightMargin : I
    //   1250: iload_3
    //   1251: invokevirtual immediateConnect : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;II)V
    //   1254: goto -> 1298
    //   1257: iload #7
    //   1259: iconst_m1
    //   1260: if_icmpeq -> 1298
    //   1263: aload_0
    //   1264: iload #7
    //   1266: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1269: astore #16
    //   1271: aload #16
    //   1273: ifnull -> 1298
    //   1276: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1279: astore #17
    //   1281: aload #15
    //   1283: aload #17
    //   1285: aload #16
    //   1287: aload #17
    //   1289: aload #14
    //   1291: getfield rightMargin : I
    //   1294: iload_3
    //   1295: invokevirtual immediateConnect : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;II)V
    //   1298: aload #14
    //   1300: getfield topToTop : I
    //   1303: istore_2
    //   1304: iload_2
    //   1305: iconst_m1
    //   1306: if_icmpeq -> 1350
    //   1309: aload_0
    //   1310: iload_2
    //   1311: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1314: astore #17
    //   1316: aload #17
    //   1318: ifnull -> 1396
    //   1321: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1324: astore #16
    //   1326: aload #15
    //   1328: aload #16
    //   1330: aload #17
    //   1332: aload #16
    //   1334: aload #14
    //   1336: getfield topMargin : I
    //   1339: aload #14
    //   1341: getfield goneTopMargin : I
    //   1344: invokevirtual immediateConnect : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;II)V
    //   1347: goto -> 1396
    //   1350: aload #14
    //   1352: getfield topToBottom : I
    //   1355: istore_2
    //   1356: iload_2
    //   1357: iconst_m1
    //   1358: if_icmpeq -> 1396
    //   1361: aload_0
    //   1362: iload_2
    //   1363: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1366: astore #16
    //   1368: aload #16
    //   1370: ifnull -> 1396
    //   1373: aload #15
    //   1375: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1378: aload #16
    //   1380: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1383: aload #14
    //   1385: getfield topMargin : I
    //   1388: aload #14
    //   1390: getfield goneTopMargin : I
    //   1393: invokevirtual immediateConnect : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;II)V
    //   1396: aload #14
    //   1398: getfield bottomToTop : I
    //   1401: istore_2
    //   1402: iload_2
    //   1403: iconst_m1
    //   1404: if_icmpeq -> 1445
    //   1407: aload_0
    //   1408: iload_2
    //   1409: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1412: astore #16
    //   1414: aload #16
    //   1416: ifnull -> 1494
    //   1419: aload #15
    //   1421: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1424: aload #16
    //   1426: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1429: aload #14
    //   1431: getfield bottomMargin : I
    //   1434: aload #14
    //   1436: getfield goneBottomMargin : I
    //   1439: invokevirtual immediateConnect : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;II)V
    //   1442: goto -> 1494
    //   1445: aload #14
    //   1447: getfield bottomToBottom : I
    //   1450: istore_2
    //   1451: iload_2
    //   1452: iconst_m1
    //   1453: if_icmpeq -> 1494
    //   1456: aload_0
    //   1457: iload_2
    //   1458: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1461: astore #16
    //   1463: aload #16
    //   1465: ifnull -> 1494
    //   1468: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1471: astore #17
    //   1473: aload #15
    //   1475: aload #17
    //   1477: aload #16
    //   1479: aload #17
    //   1481: aload #14
    //   1483: getfield bottomMargin : I
    //   1486: aload #14
    //   1488: getfield goneBottomMargin : I
    //   1491: invokevirtual immediateConnect : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;II)V
    //   1494: aload #14
    //   1496: getfield baselineToBaseline : I
    //   1499: istore_2
    //   1500: iload_2
    //   1501: iconst_m1
    //   1502: if_icmpeq -> 1621
    //   1505: aload_0
    //   1506: getfield mChildrenByIds : Landroid/util/SparseArray;
    //   1509: iload_2
    //   1510: invokevirtual get : (I)Ljava/lang/Object;
    //   1513: checkcast android/view/View
    //   1516: astore #17
    //   1518: aload_0
    //   1519: aload #14
    //   1521: getfield baselineToBaseline : I
    //   1524: invokespecial getTargetWidget : (I)Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1527: astore #16
    //   1529: aload #16
    //   1531: ifnull -> 1621
    //   1534: aload #17
    //   1536: ifnull -> 1621
    //   1539: aload #17
    //   1541: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1544: instanceof androidx/constraintlayout/widget/ConstraintLayout$LayoutParams
    //   1547: ifeq -> 1621
    //   1550: aload #17
    //   1552: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1555: checkcast androidx/constraintlayout/widget/ConstraintLayout$LayoutParams
    //   1558: astore #17
    //   1560: aload #14
    //   1562: iconst_1
    //   1563: putfield needsBaseline : Z
    //   1566: aload #17
    //   1568: iconst_1
    //   1569: putfield needsBaseline : Z
    //   1572: aload #15
    //   1574: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BASELINE : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1577: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1580: aload #16
    //   1582: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BASELINE : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1585: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1588: iconst_0
    //   1589: iconst_m1
    //   1590: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Strength.STRONG : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Strength;
    //   1593: iconst_0
    //   1594: iconst_1
    //   1595: invokevirtual connect : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;IILandroidx/constraintlayout/solver/widgets/ConstraintAnchor$Strength;IZ)Z
    //   1598: pop
    //   1599: aload #15
    //   1601: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1604: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1607: invokevirtual reset : ()V
    //   1610: aload #15
    //   1612: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1615: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1618: invokevirtual reset : ()V
    //   1621: fload_1
    //   1622: fconst_0
    //   1623: fcmpl
    //   1624: iflt -> 1641
    //   1627: fload_1
    //   1628: ldc_w 0.5
    //   1631: fcmpl
    //   1632: ifeq -> 1641
    //   1635: aload #15
    //   1637: fload_1
    //   1638: invokevirtual setHorizontalBiasPercent : (F)V
    //   1641: aload #14
    //   1643: getfield verticalBias : F
    //   1646: fstore_1
    //   1647: fload_1
    //   1648: fconst_0
    //   1649: fcmpl
    //   1650: iflt -> 1667
    //   1653: fload_1
    //   1654: ldc_w 0.5
    //   1657: fcmpl
    //   1658: ifeq -> 1667
    //   1661: aload #15
    //   1663: fload_1
    //   1664: invokevirtual setVerticalBiasPercent : (F)V
    //   1667: iload #13
    //   1669: ifeq -> 1705
    //   1672: aload #14
    //   1674: getfield editorAbsoluteX : I
    //   1677: iconst_m1
    //   1678: if_icmpne -> 1690
    //   1681: aload #14
    //   1683: getfield editorAbsoluteY : I
    //   1686: iconst_m1
    //   1687: if_icmpeq -> 1705
    //   1690: aload #15
    //   1692: aload #14
    //   1694: getfield editorAbsoluteX : I
    //   1697: aload #14
    //   1699: getfield editorAbsoluteY : I
    //   1702: invokevirtual setOrigin : (II)V
    //   1705: aload #14
    //   1707: getfield horizontalDimensionFixed : Z
    //   1710: ifne -> 1782
    //   1713: aload #14
    //   1715: getfield width : I
    //   1718: iconst_m1
    //   1719: if_icmpne -> 1765
    //   1722: aload #15
    //   1724: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_PARENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1727: invokevirtual setHorizontalDimensionBehaviour : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   1730: aload #15
    //   1732: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.LEFT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1735: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1738: aload #14
    //   1740: getfield leftMargin : I
    //   1743: putfield mMargin : I
    //   1746: aload #15
    //   1748: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1751: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1754: aload #14
    //   1756: getfield rightMargin : I
    //   1759: putfield mMargin : I
    //   1762: goto -> 1800
    //   1765: aload #15
    //   1767: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1770: invokevirtual setHorizontalDimensionBehaviour : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   1773: aload #15
    //   1775: iconst_0
    //   1776: invokevirtual setWidth : (I)V
    //   1779: goto -> 1800
    //   1782: aload #15
    //   1784: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.FIXED : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1787: invokevirtual setHorizontalDimensionBehaviour : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   1790: aload #15
    //   1792: aload #14
    //   1794: getfield width : I
    //   1797: invokevirtual setWidth : (I)V
    //   1800: aload #14
    //   1802: getfield verticalDimensionFixed : Z
    //   1805: ifne -> 1877
    //   1808: aload #14
    //   1810: getfield height : I
    //   1813: iconst_m1
    //   1814: if_icmpne -> 1860
    //   1817: aload #15
    //   1819: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_PARENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1822: invokevirtual setVerticalDimensionBehaviour : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   1825: aload #15
    //   1827: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1830: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1833: aload #14
    //   1835: getfield topMargin : I
    //   1838: putfield mMargin : I
    //   1841: aload #15
    //   1843: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   1846: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1849: aload #14
    //   1851: getfield bottomMargin : I
    //   1854: putfield mMargin : I
    //   1857: goto -> 1895
    //   1860: aload #15
    //   1862: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1865: invokevirtual setVerticalDimensionBehaviour : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   1868: aload #15
    //   1870: iconst_0
    //   1871: invokevirtual setHeight : (I)V
    //   1874: goto -> 1895
    //   1877: aload #15
    //   1879: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.FIXED : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1882: invokevirtual setVerticalDimensionBehaviour : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   1885: aload #15
    //   1887: aload #14
    //   1889: getfield height : I
    //   1892: invokevirtual setHeight : (I)V
    //   1895: iconst_0
    //   1896: istore #4
    //   1898: aload #14
    //   1900: getfield dimensionRatio : Ljava/lang/String;
    //   1903: astore #16
    //   1905: aload #16
    //   1907: ifnull -> 1917
    //   1910: aload #15
    //   1912: aload #16
    //   1914: invokevirtual setDimensionRatio : (Ljava/lang/String;)V
    //   1917: aload #15
    //   1919: aload #14
    //   1921: getfield horizontalWeight : F
    //   1924: invokevirtual setHorizontalWeight : (F)V
    //   1927: aload #15
    //   1929: aload #14
    //   1931: getfield verticalWeight : F
    //   1934: invokevirtual setVerticalWeight : (F)V
    //   1937: aload #15
    //   1939: aload #14
    //   1941: getfield horizontalChainStyle : I
    //   1944: invokevirtual setHorizontalChainStyle : (I)V
    //   1947: aload #15
    //   1949: aload #14
    //   1951: getfield verticalChainStyle : I
    //   1954: invokevirtual setVerticalChainStyle : (I)V
    //   1957: aload #15
    //   1959: aload #14
    //   1961: getfield matchConstraintDefaultWidth : I
    //   1964: aload #14
    //   1966: getfield matchConstraintMinWidth : I
    //   1969: aload #14
    //   1971: getfield matchConstraintMaxWidth : I
    //   1974: aload #14
    //   1976: getfield matchConstraintPercentWidth : F
    //   1979: invokevirtual setHorizontalMatchStyle : (IIIF)V
    //   1982: aload #15
    //   1984: aload #14
    //   1986: getfield matchConstraintDefaultHeight : I
    //   1989: aload #14
    //   1991: getfield matchConstraintMinHeight : I
    //   1994: aload #14
    //   1996: getfield matchConstraintMaxHeight : I
    //   1999: aload #14
    //   2001: getfield matchConstraintPercentHeight : F
    //   2004: invokevirtual setVerticalMatchStyle : (IIIF)V
    //   2007: iinc #8, 1
    //   2010: iload #4
    //   2012: istore_2
    //   2013: goto -> 322
    //   2016: return
    //   2017: astore #14
    //   2019: goto -> 107
    // Exception table:
    //   from	to	target	type
    //   34	72	2017	android/content/res/Resources$NotFoundException
    //   82	93	2017	android/content/res/Resources$NotFoundException
    //   93	107	2017	android/content/res/Resources$NotFoundException
    //   393	453	456	android/content/res/Resources$NotFoundException
  }
  
  private void setSelfDimensionBehaviour(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iload_1
    //   1: invokestatic getMode : (I)I
    //   4: istore #6
    //   6: iload_1
    //   7: invokestatic getSize : (I)I
    //   10: istore_1
    //   11: iload_2
    //   12: invokestatic getMode : (I)I
    //   15: istore_3
    //   16: iload_2
    //   17: invokestatic getSize : (I)I
    //   20: istore_2
    //   21: aload_0
    //   22: invokevirtual getPaddingTop : ()I
    //   25: istore #5
    //   27: aload_0
    //   28: invokevirtual getPaddingBottom : ()I
    //   31: istore #4
    //   33: aload_0
    //   34: invokevirtual getPaddingLeft : ()I
    //   37: istore #8
    //   39: aload_0
    //   40: invokevirtual getPaddingRight : ()I
    //   43: istore #7
    //   45: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.FIXED : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   48: astore #9
    //   50: aload_0
    //   51: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   54: pop
    //   55: iload #6
    //   57: ldc_w -2147483648
    //   60: if_icmpeq -> 115
    //   63: iload #6
    //   65: ifeq -> 107
    //   68: iload #6
    //   70: ldc_w 1073741824
    //   73: if_icmpeq -> 85
    //   76: aload #9
    //   78: astore #10
    //   80: iconst_0
    //   81: istore_1
    //   82: goto -> 120
    //   85: aload_0
    //   86: getfield mMaxWidth : I
    //   89: iload_1
    //   90: invokestatic min : (II)I
    //   93: iload #8
    //   95: iload #7
    //   97: iadd
    //   98: isub
    //   99: istore_1
    //   100: aload #9
    //   102: astore #10
    //   104: goto -> 120
    //   107: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   110: astore #10
    //   112: goto -> 80
    //   115: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   118: astore #10
    //   120: iload_3
    //   121: ldc_w -2147483648
    //   124: if_icmpeq -> 169
    //   127: iload_3
    //   128: ifeq -> 161
    //   131: iload_3
    //   132: ldc_w 1073741824
    //   135: if_icmpeq -> 143
    //   138: iconst_0
    //   139: istore_2
    //   140: goto -> 174
    //   143: aload_0
    //   144: getfield mMaxHeight : I
    //   147: iload_2
    //   148: invokestatic min : (II)I
    //   151: iload #5
    //   153: iload #4
    //   155: iadd
    //   156: isub
    //   157: istore_2
    //   158: goto -> 174
    //   161: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   164: astore #9
    //   166: goto -> 138
    //   169: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   172: astore #9
    //   174: aload_0
    //   175: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   178: iconst_0
    //   179: invokevirtual setMinWidth : (I)V
    //   182: aload_0
    //   183: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   186: iconst_0
    //   187: invokevirtual setMinHeight : (I)V
    //   190: aload_0
    //   191: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   194: aload #10
    //   196: invokevirtual setHorizontalDimensionBehaviour : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   199: aload_0
    //   200: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   203: iload_1
    //   204: invokevirtual setWidth : (I)V
    //   207: aload_0
    //   208: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   211: aload #9
    //   213: invokevirtual setVerticalDimensionBehaviour : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;)V
    //   216: aload_0
    //   217: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   220: iload_2
    //   221: invokevirtual setHeight : (I)V
    //   224: aload_0
    //   225: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   228: aload_0
    //   229: getfield mMinWidth : I
    //   232: aload_0
    //   233: invokevirtual getPaddingLeft : ()I
    //   236: isub
    //   237: aload_0
    //   238: invokevirtual getPaddingRight : ()I
    //   241: isub
    //   242: invokevirtual setMinWidth : (I)V
    //   245: aload_0
    //   246: getfield mLayoutWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;
    //   249: aload_0
    //   250: getfield mMinHeight : I
    //   253: aload_0
    //   254: invokevirtual getPaddingTop : ()I
    //   257: isub
    //   258: aload_0
    //   259: invokevirtual getPaddingBottom : ()I
    //   262: isub
    //   263: invokevirtual setMinHeight : (I)V
    //   266: return
  }
  
  private void updateHierarchy() {
    boolean bool1;
    int i = getChildCount();
    boolean bool2 = false;
    byte b = 0;
    while (true) {
      bool1 = bool2;
      if (b < i) {
        if (getChildAt(b).isLayoutRequested()) {
          bool1 = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    if (bool1) {
      this.mVariableDimensionsWidgets.clear();
      setChildrenConstraints();
    } 
  }
  
  private void updatePostMeasures() {
    int i = getChildCount();
    boolean bool = false;
    byte b;
    for (b = 0; b < i; b++) {
      View view = getChildAt(b);
      if (view instanceof Placeholder)
        ((Placeholder)view).updatePostMeasure(this); 
    } 
    i = this.mConstraintHelpers.size();
    if (i > 0)
      for (b = bool; b < i; b++)
        ((ConstraintHelper)this.mConstraintHelpers.get(b)).updatePostMeasure(this);  
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    super.addView(paramView, paramInt, paramLayoutParams);
    if (Build.VERSION.SDK_INT < 14)
      onViewAdded(paramView); 
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  public void dispatchDraw(Canvas paramCanvas) {
    super.dispatchDraw(paramCanvas);
    if (isInEditMode()) {
      int i = getChildCount();
      float f1 = getWidth();
      float f2 = getHeight();
      for (byte b = 0; b < i; b++) {
        View view = getChildAt(b);
        if (view.getVisibility() != 8) {
          Object object = view.getTag();
          if (object != null && object instanceof String) {
            object = ((String)object).split(",");
            if (object.length == 4) {
              int k = Integer.parseInt((String)object[0]);
              int m = Integer.parseInt((String)object[1]);
              int n = Integer.parseInt((String)object[2]);
              int j = Integer.parseInt((String)object[3]);
              k = (int)(k / 1080.0F * f1);
              m = (int)(m / 1920.0F * f2);
              n = (int)(n / 1080.0F * f1);
              j = (int)(j / 1920.0F * f2);
              object = new Paint();
              object.setColor(-65536);
              float f4 = k;
              float f3 = m;
              float f5 = (k + n);
              paramCanvas.drawLine(f4, f3, f5, f3, (Paint)object);
              float f6 = (m + j);
              paramCanvas.drawLine(f5, f3, f5, f6, (Paint)object);
              paramCanvas.drawLine(f5, f6, f4, f6, (Paint)object);
              paramCanvas.drawLine(f4, f6, f4, f3, (Paint)object);
              object.setColor(-16711936);
              paramCanvas.drawLine(f4, f3, f5, f6, (Paint)object);
              paramCanvas.drawLine(f4, f6, f5, f3, (Paint)object);
            } 
          } 
        } 
      } 
    } 
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(-2, -2);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (ViewGroup.LayoutParams)new LayoutParams(paramLayoutParams);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  public Object getDesignInformation(int paramInt, Object<String, Integer> paramObject) {
    if (paramInt == 0 && paramObject instanceof String) {
      String str = (String)paramObject;
      paramObject = (Object<String, Integer>)this.mDesignIds;
      if (paramObject != null && paramObject.containsKey(str))
        return this.mDesignIds.get(str); 
    } 
    return null;
  }
  
  public int getMaxHeight() {
    return this.mMaxHeight;
  }
  
  public int getMaxWidth() {
    return this.mMaxWidth;
  }
  
  public int getMinHeight() {
    return this.mMinHeight;
  }
  
  public int getMinWidth() {
    return this.mMinWidth;
  }
  
  public int getOptimizationLevel() {
    return this.mLayoutWidget.getOptimizationLevel();
  }
  
  public View getViewById(int paramInt) {
    return (View)this.mChildrenByIds.get(paramInt);
  }
  
  public final ConstraintWidget getViewWidget(View paramView) {
    ConstraintWidget constraintWidget;
    if (paramView == this)
      return (ConstraintWidget)this.mLayoutWidget; 
    if (paramView == null) {
      paramView = null;
    } else {
      constraintWidget = ((LayoutParams)paramView.getLayoutParams()).widget;
    } 
    return constraintWidget;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    paramInt3 = getChildCount();
    paramBoolean = isInEditMode();
    paramInt2 = 0;
    for (paramInt1 = 0; paramInt1 < paramInt3; paramInt1++) {
      View view = getChildAt(paramInt1);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      ConstraintWidget constraintWidget = layoutParams.widget;
      if ((view.getVisibility() != 8 || layoutParams.isGuideline || layoutParams.isHelper || paramBoolean) && !layoutParams.isInPlaceholder) {
        paramInt4 = constraintWidget.getDrawX();
        int k = constraintWidget.getDrawY();
        int i = constraintWidget.getWidth() + paramInt4;
        int j = constraintWidget.getHeight() + k;
        view.layout(paramInt4, k, i, j);
        if (view instanceof Placeholder) {
          view = ((Placeholder)view).getContent();
          if (view != null) {
            view.setVisibility(0);
            view.layout(paramInt4, k, i, j);
          } 
        } 
      } 
    } 
    paramInt3 = this.mConstraintHelpers.size();
    if (paramInt3 > 0)
      for (paramInt1 = paramInt2; paramInt1 < paramInt3; paramInt1++)
        ((ConstraintHelper)this.mConstraintHelpers.get(paramInt1)).updatePostLayout(this);  
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i;
    boolean bool;
    System.currentTimeMillis();
    int i2 = View.MeasureSpec.getMode(paramInt1);
    int i1 = View.MeasureSpec.getSize(paramInt1);
    int n = View.MeasureSpec.getMode(paramInt2);
    int m = View.MeasureSpec.getSize(paramInt2);
    int j = getPaddingLeft();
    int k = getPaddingTop();
    this.mLayoutWidget.setX(j);
    this.mLayoutWidget.setY(k);
    this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
    this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
    if (Build.VERSION.SDK_INT >= 17) {
      boolean bool1;
      ConstraintWidgetContainer constraintWidgetContainer1 = this.mLayoutWidget;
      if (getLayoutDirection() == 1) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      constraintWidgetContainer1.setRtl(bool1);
    } 
    setSelfDimensionBehaviour(paramInt1, paramInt2);
    int i5 = this.mLayoutWidget.getWidth();
    int i4 = this.mLayoutWidget.getHeight();
    if (this.mDirtyHierarchy) {
      this.mDirtyHierarchy = false;
      updateHierarchy();
      i = 1;
    } else {
      i = 0;
    } 
    if ((this.mOptimizationLevel & 0x8) == 8) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      this.mLayoutWidget.preOptimize();
      this.mLayoutWidget.optimizeForDimensions(i5, i4);
      internalMeasureDimensions(paramInt1, paramInt2);
    } else {
      internalMeasureChildren(paramInt1, paramInt2);
    } 
    updatePostMeasures();
    if (getChildCount() > 0 && i)
      Analyzer.determineGroups(this.mLayoutWidget); 
    ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
    if (constraintWidgetContainer.mGroupsWrapOptimized) {
      if (constraintWidgetContainer.mHorizontalWrapOptimized && i2 == Integer.MIN_VALUE) {
        i = constraintWidgetContainer.mWrapFixedWidth;
        if (i < i1)
          constraintWidgetContainer.setWidth(i); 
        this.mLayoutWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
      } 
      constraintWidgetContainer = this.mLayoutWidget;
      if (constraintWidgetContainer.mVerticalWrapOptimized && n == Integer.MIN_VALUE) {
        i = constraintWidgetContainer.mWrapFixedHeight;
        if (i < m)
          constraintWidgetContainer.setHeight(i); 
        this.mLayoutWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
      } 
    } 
    if ((this.mOptimizationLevel & 0x20) == 32) {
      int i8 = this.mLayoutWidget.getWidth();
      i = this.mLayoutWidget.getHeight();
      if (this.mLastMeasureWidth != i8 && i2 == 1073741824)
        Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, i8); 
      if (this.mLastMeasureHeight != i && n == 1073741824)
        Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, i); 
      constraintWidgetContainer = this.mLayoutWidget;
      if (constraintWidgetContainer.mHorizontalWrapOptimized && constraintWidgetContainer.mWrapFixedWidth > i1)
        Analyzer.setPosition(constraintWidgetContainer.mWidgetGroups, 0, i1); 
      constraintWidgetContainer = this.mLayoutWidget;
      if (constraintWidgetContainer.mVerticalWrapOptimized && constraintWidgetContainer.mWrapFixedHeight > m)
        Analyzer.setPosition(constraintWidgetContainer.mWidgetGroups, 1, m); 
    } 
    if (getChildCount() > 0)
      solveLinearSystem("First pass"); 
    int i3 = this.mVariableDimensionsWidgets.size();
    int i6 = k + getPaddingBottom();
    int i7 = j + getPaddingRight();
    if (i3 > 0) {
      if (this.mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        i1 = 1;
      } else {
        i1 = 0;
      } 
      if (this.mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        i2 = 1;
      } else {
        i2 = 0;
      } 
      k = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
      j = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
      byte b = 0;
      m = 0;
      i = 0;
      while (b < i3) {
        ConstraintWidget constraintWidget = this.mVariableDimensionsWidgets.get(b);
        View view = (View)constraintWidget.getCompanionWidget();
        if (view != null) {
          LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
          if (!layoutParams.isHelper && !layoutParams.isGuideline) {
            int i8 = view.getVisibility();
            n = m;
            if (i8 != 8 && (!bool || !constraintWidget.getResolutionWidth().isResolved() || !constraintWidget.getResolutionHeight().isResolved())) {
              if (layoutParams.width == -2 && layoutParams.horizontalDimensionFixed) {
                m = ViewGroup.getChildMeasureSpec(paramInt1, i7, layoutParams.width);
              } else {
                m = View.MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824);
              } 
              if (layoutParams.height == -2 && layoutParams.verticalDimensionFixed) {
                i8 = ViewGroup.getChildMeasureSpec(paramInt2, i6, layoutParams.height);
              } else {
                i8 = View.MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824);
              } 
              view.measure(m, i8);
              Metrics metrics = this.mMetrics;
              if (metrics != null)
                metrics.additionalMeasures++; 
              int i9 = view.getMeasuredWidth();
              i8 = view.getMeasuredHeight();
              m = k;
              if (i9 != constraintWidget.getWidth()) {
                constraintWidget.setWidth(i9);
                if (bool)
                  constraintWidget.getResolutionWidth().resolve(i9); 
                m = k;
                if (i1 != 0) {
                  m = k;
                  if (constraintWidget.getRight() > k)
                    m = Math.max(k, constraintWidget.getRight() + constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin()); 
                } 
                n = 1;
              } 
              k = j;
              if (i8 != constraintWidget.getHeight()) {
                constraintWidget.setHeight(i8);
                if (bool)
                  constraintWidget.getResolutionHeight().resolve(i8); 
                k = j;
                if (i2 != 0) {
                  k = j;
                  if (constraintWidget.getBottom() > j)
                    k = Math.max(j, constraintWidget.getBottom() + constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin()); 
                } 
                n = 1;
              } 
              j = n;
              if (layoutParams.needsBaseline) {
                i8 = view.getBaseline();
                j = n;
                if (i8 != -1) {
                  j = n;
                  if (i8 != constraintWidget.getBaselineDistance()) {
                    constraintWidget.setBaselineDistance(i8);
                    j = 1;
                  } 
                } 
              } 
              if (Build.VERSION.SDK_INT >= 11) {
                i = ViewGroup.combineMeasuredStates(i, view.getMeasuredState());
                n = k;
                k = m;
                m = j;
              } else {
                n = k;
                k = m;
                m = j;
              } 
              continue;
            } 
          } 
        } 
        n = j;
        continue;
        b++;
        j = n;
      } 
      n = i;
      if (m != 0) {
        this.mLayoutWidget.setWidth(i5);
        this.mLayoutWidget.setHeight(i4);
        if (bool)
          this.mLayoutWidget.solveGraph(); 
        solveLinearSystem("2nd pass");
        if (this.mLayoutWidget.getWidth() < k) {
          this.mLayoutWidget.setWidth(k);
          i = 1;
        } else {
          i = 0;
        } 
        if (this.mLayoutWidget.getHeight() < j) {
          this.mLayoutWidget.setHeight(j);
          i = 1;
        } 
        if (i != 0)
          solveLinearSystem("3rd pass"); 
      } 
      j = 0;
      while (true) {
        i = n;
        if (j < i3) {
          ConstraintWidget constraintWidget = this.mVariableDimensionsWidgets.get(j);
          View view = (View)constraintWidget.getCompanionWidget();
          if (view != null && (view.getMeasuredWidth() != constraintWidget.getWidth() || view.getMeasuredHeight() != constraintWidget.getHeight()) && constraintWidget.getVisibility() != 8) {
            view.measure(View.MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824));
            Metrics metrics = this.mMetrics;
            if (metrics != null)
              metrics.additionalMeasures++; 
          } 
          j++;
          continue;
        } 
        break;
      } 
    } else {
      i = 0;
    } 
    k = this.mLayoutWidget.getWidth() + i7;
    j = this.mLayoutWidget.getHeight() + i6;
    if (Build.VERSION.SDK_INT >= 11) {
      paramInt1 = ViewGroup.resolveSizeAndState(k, paramInt1, i);
      i = ViewGroup.resolveSizeAndState(j, paramInt2, i << 16);
      paramInt2 = Math.min(this.mMaxWidth, paramInt1 & 0xFFFFFF);
      i = Math.min(this.mMaxHeight, i & 0xFFFFFF);
      paramInt1 = paramInt2;
      if (this.mLayoutWidget.isWidthMeasuredTooSmall())
        paramInt1 = paramInt2 | 0x1000000; 
      paramInt2 = i;
      if (this.mLayoutWidget.isHeightMeasuredTooSmall())
        paramInt2 = i | 0x1000000; 
      setMeasuredDimension(paramInt1, paramInt2);
      this.mLastMeasureWidth = paramInt1;
      this.mLastMeasureHeight = paramInt2;
    } else {
      setMeasuredDimension(k, j);
      this.mLastMeasureWidth = k;
      this.mLastMeasureHeight = j;
    } 
  }
  
  public void onViewAdded(View paramView) {
    if (Build.VERSION.SDK_INT >= 14)
      super.onViewAdded(paramView); 
    ConstraintWidget constraintWidget = getViewWidget(paramView);
    if (paramView instanceof Guideline && !(constraintWidget instanceof Guideline)) {
      LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
      layoutParams.widget = (ConstraintWidget)new Guideline();
      layoutParams.isGuideline = true;
      ((Guideline)layoutParams.widget).setOrientation(layoutParams.orientation);
    } 
    if (paramView instanceof ConstraintHelper) {
      ConstraintHelper constraintHelper = (ConstraintHelper)paramView;
      constraintHelper.validateParams();
      ((LayoutParams)paramView.getLayoutParams()).isHelper = true;
      if (!this.mConstraintHelpers.contains(constraintHelper))
        this.mConstraintHelpers.add(constraintHelper); 
    } 
    this.mChildrenByIds.put(paramView.getId(), paramView);
    this.mDirtyHierarchy = true;
  }
  
  public void onViewRemoved(View paramView) {
    if (Build.VERSION.SDK_INT >= 14)
      super.onViewRemoved(paramView); 
    this.mChildrenByIds.remove(paramView.getId());
    ConstraintWidget constraintWidget = getViewWidget(paramView);
    this.mLayoutWidget.remove(constraintWidget);
    this.mConstraintHelpers.remove(paramView);
    this.mVariableDimensionsWidgets.remove(constraintWidget);
    this.mDirtyHierarchy = true;
  }
  
  public void removeView(View paramView) {
    super.removeView(paramView);
    if (Build.VERSION.SDK_INT < 14)
      onViewRemoved(paramView); 
  }
  
  public void requestLayout() {
    super.requestLayout();
    this.mDirtyHierarchy = true;
    this.mLastMeasureWidth = -1;
    this.mLastMeasureHeight = -1;
  }
  
  public void setConstraintSet(ConstraintSet paramConstraintSet) {
    this.mConstraintSet = paramConstraintSet;
  }
  
  public void setDesignInformation(int paramInt, Object paramObject1, Object paramObject2) {
    if (paramInt == 0 && paramObject1 instanceof String && paramObject2 instanceof Integer) {
      if (this.mDesignIds == null)
        this.mDesignIds = new HashMap<String, Integer>(); 
      String str = (String)paramObject1;
      paramInt = str.indexOf("/");
      paramObject1 = str;
      if (paramInt != -1)
        paramObject1 = str.substring(paramInt + 1); 
      paramInt = ((Integer)paramObject2).intValue();
      this.mDesignIds.put(paramObject1, Integer.valueOf(paramInt));
    } 
  }
  
  public void setId(int paramInt) {
    this.mChildrenByIds.remove(getId());
    super.setId(paramInt);
    this.mChildrenByIds.put(getId(), this);
  }
  
  public void setMaxHeight(int paramInt) {
    if (paramInt == this.mMaxHeight)
      return; 
    this.mMaxHeight = paramInt;
    requestLayout();
  }
  
  public void setMaxWidth(int paramInt) {
    if (paramInt == this.mMaxWidth)
      return; 
    this.mMaxWidth = paramInt;
    requestLayout();
  }
  
  public void setMinHeight(int paramInt) {
    if (paramInt == this.mMinHeight)
      return; 
    this.mMinHeight = paramInt;
    requestLayout();
  }
  
  public void setMinWidth(int paramInt) {
    if (paramInt == this.mMinWidth)
      return; 
    this.mMinWidth = paramInt;
    requestLayout();
  }
  
  public void setOptimizationLevel(int paramInt) {
    this.mLayoutWidget.setOptimizationLevel(paramInt);
  }
  
  public boolean shouldDelayChildPressedState() {
    return false;
  }
  
  protected void solveLinearSystem(String paramString) {
    this.mLayoutWidget.layout();
    Metrics metrics = this.mMetrics;
    if (metrics != null)
      metrics.resolutions++; 
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public int baselineToBaseline = -1;
    
    public int bottomToBottom = -1;
    
    public int bottomToTop = -1;
    
    public float circleAngle = 0.0F;
    
    public int circleConstraint = -1;
    
    public int circleRadius = 0;
    
    public boolean constrainedHeight = false;
    
    public boolean constrainedWidth = false;
    
    public String dimensionRatio = null;
    
    int dimensionRatioSide = 1;
    
    public int editorAbsoluteX = -1;
    
    public int editorAbsoluteY = -1;
    
    public int endToEnd = -1;
    
    public int endToStart = -1;
    
    public int goneBottomMargin = -1;
    
    public int goneEndMargin = -1;
    
    public int goneLeftMargin = -1;
    
    public int goneRightMargin = -1;
    
    public int goneStartMargin = -1;
    
    public int goneTopMargin = -1;
    
    public int guideBegin = -1;
    
    public int guideEnd = -1;
    
    public float guidePercent = -1.0F;
    
    public boolean helped = false;
    
    public float horizontalBias = 0.5F;
    
    public int horizontalChainStyle = 0;
    
    boolean horizontalDimensionFixed = true;
    
    public float horizontalWeight = -1.0F;
    
    boolean isGuideline = false;
    
    boolean isHelper = false;
    
    boolean isInPlaceholder = false;
    
    public int leftToLeft = -1;
    
    public int leftToRight = -1;
    
    public int matchConstraintDefaultHeight = 0;
    
    public int matchConstraintDefaultWidth = 0;
    
    public int matchConstraintMaxHeight = 0;
    
    public int matchConstraintMaxWidth = 0;
    
    public int matchConstraintMinHeight = 0;
    
    public int matchConstraintMinWidth = 0;
    
    public float matchConstraintPercentHeight = 1.0F;
    
    public float matchConstraintPercentWidth = 1.0F;
    
    boolean needsBaseline = false;
    
    public int orientation = -1;
    
    int resolveGoneLeftMargin = -1;
    
    int resolveGoneRightMargin = -1;
    
    int resolvedGuideBegin;
    
    int resolvedGuideEnd;
    
    float resolvedGuidePercent;
    
    float resolvedHorizontalBias = 0.5F;
    
    int resolvedLeftToLeft = -1;
    
    int resolvedLeftToRight = -1;
    
    int resolvedRightToLeft = -1;
    
    int resolvedRightToRight = -1;
    
    public int rightToLeft = -1;
    
    public int rightToRight = -1;
    
    public int startToEnd = -1;
    
    public int startToStart = -1;
    
    public int topToBottom = -1;
    
    public int topToTop = -1;
    
    public float verticalBias = 0.5F;
    
    public int verticalChainStyle = 0;
    
    boolean verticalDimensionFixed = true;
    
    public float verticalWeight = -1.0F;
    
    ConstraintWidget widget = new ConstraintWidget();
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: aload_2
      //   3: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;)V
      //   6: aload_0
      //   7: iconst_m1
      //   8: putfield guideBegin : I
      //   11: aload_0
      //   12: iconst_m1
      //   13: putfield guideEnd : I
      //   16: aload_0
      //   17: ldc -1.0
      //   19: putfield guidePercent : F
      //   22: aload_0
      //   23: iconst_m1
      //   24: putfield leftToLeft : I
      //   27: aload_0
      //   28: iconst_m1
      //   29: putfield leftToRight : I
      //   32: aload_0
      //   33: iconst_m1
      //   34: putfield rightToLeft : I
      //   37: aload_0
      //   38: iconst_m1
      //   39: putfield rightToRight : I
      //   42: aload_0
      //   43: iconst_m1
      //   44: putfield topToTop : I
      //   47: aload_0
      //   48: iconst_m1
      //   49: putfield topToBottom : I
      //   52: aload_0
      //   53: iconst_m1
      //   54: putfield bottomToTop : I
      //   57: aload_0
      //   58: iconst_m1
      //   59: putfield bottomToBottom : I
      //   62: aload_0
      //   63: iconst_m1
      //   64: putfield baselineToBaseline : I
      //   67: aload_0
      //   68: iconst_m1
      //   69: putfield circleConstraint : I
      //   72: aload_0
      //   73: iconst_0
      //   74: putfield circleRadius : I
      //   77: aload_0
      //   78: fconst_0
      //   79: putfield circleAngle : F
      //   82: aload_0
      //   83: iconst_m1
      //   84: putfield startToEnd : I
      //   87: aload_0
      //   88: iconst_m1
      //   89: putfield startToStart : I
      //   92: aload_0
      //   93: iconst_m1
      //   94: putfield endToStart : I
      //   97: aload_0
      //   98: iconst_m1
      //   99: putfield endToEnd : I
      //   102: aload_0
      //   103: iconst_m1
      //   104: putfield goneLeftMargin : I
      //   107: aload_0
      //   108: iconst_m1
      //   109: putfield goneTopMargin : I
      //   112: aload_0
      //   113: iconst_m1
      //   114: putfield goneRightMargin : I
      //   117: aload_0
      //   118: iconst_m1
      //   119: putfield goneBottomMargin : I
      //   122: aload_0
      //   123: iconst_m1
      //   124: putfield goneStartMargin : I
      //   127: aload_0
      //   128: iconst_m1
      //   129: putfield goneEndMargin : I
      //   132: aload_0
      //   133: ldc 0.5
      //   135: putfield horizontalBias : F
      //   138: aload_0
      //   139: ldc 0.5
      //   141: putfield verticalBias : F
      //   144: aload_0
      //   145: aconst_null
      //   146: putfield dimensionRatio : Ljava/lang/String;
      //   149: aload_0
      //   150: iconst_1
      //   151: putfield dimensionRatioSide : I
      //   154: aload_0
      //   155: ldc -1.0
      //   157: putfield horizontalWeight : F
      //   160: aload_0
      //   161: ldc -1.0
      //   163: putfield verticalWeight : F
      //   166: aload_0
      //   167: iconst_0
      //   168: putfield horizontalChainStyle : I
      //   171: aload_0
      //   172: iconst_0
      //   173: putfield verticalChainStyle : I
      //   176: aload_0
      //   177: iconst_0
      //   178: putfield matchConstraintDefaultWidth : I
      //   181: aload_0
      //   182: iconst_0
      //   183: putfield matchConstraintDefaultHeight : I
      //   186: aload_0
      //   187: iconst_0
      //   188: putfield matchConstraintMinWidth : I
      //   191: aload_0
      //   192: iconst_0
      //   193: putfield matchConstraintMinHeight : I
      //   196: aload_0
      //   197: iconst_0
      //   198: putfield matchConstraintMaxWidth : I
      //   201: aload_0
      //   202: iconst_0
      //   203: putfield matchConstraintMaxHeight : I
      //   206: aload_0
      //   207: fconst_1
      //   208: putfield matchConstraintPercentWidth : F
      //   211: aload_0
      //   212: fconst_1
      //   213: putfield matchConstraintPercentHeight : F
      //   216: aload_0
      //   217: iconst_m1
      //   218: putfield editorAbsoluteX : I
      //   221: aload_0
      //   222: iconst_m1
      //   223: putfield editorAbsoluteY : I
      //   226: aload_0
      //   227: iconst_m1
      //   228: putfield orientation : I
      //   231: aload_0
      //   232: iconst_0
      //   233: putfield constrainedWidth : Z
      //   236: aload_0
      //   237: iconst_0
      //   238: putfield constrainedHeight : Z
      //   241: aload_0
      //   242: iconst_1
      //   243: putfield horizontalDimensionFixed : Z
      //   246: aload_0
      //   247: iconst_1
      //   248: putfield verticalDimensionFixed : Z
      //   251: aload_0
      //   252: iconst_0
      //   253: putfield needsBaseline : Z
      //   256: aload_0
      //   257: iconst_0
      //   258: putfield isGuideline : Z
      //   261: aload_0
      //   262: iconst_0
      //   263: putfield isHelper : Z
      //   266: aload_0
      //   267: iconst_0
      //   268: putfield isInPlaceholder : Z
      //   271: aload_0
      //   272: iconst_m1
      //   273: putfield resolvedLeftToLeft : I
      //   276: aload_0
      //   277: iconst_m1
      //   278: putfield resolvedLeftToRight : I
      //   281: aload_0
      //   282: iconst_m1
      //   283: putfield resolvedRightToLeft : I
      //   286: aload_0
      //   287: iconst_m1
      //   288: putfield resolvedRightToRight : I
      //   291: aload_0
      //   292: iconst_m1
      //   293: putfield resolveGoneLeftMargin : I
      //   296: aload_0
      //   297: iconst_m1
      //   298: putfield resolveGoneRightMargin : I
      //   301: aload_0
      //   302: ldc 0.5
      //   304: putfield resolvedHorizontalBias : F
      //   307: aload_0
      //   308: new androidx/constraintlayout/solver/widgets/ConstraintWidget
      //   311: dup
      //   312: invokespecial <init> : ()V
      //   315: putfield widget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
      //   318: aload_0
      //   319: iconst_0
      //   320: putfield helped : Z
      //   323: aload_1
      //   324: aload_2
      //   325: getstatic androidx/constraintlayout/widget/R$styleable.ConstraintLayout_Layout : [I
      //   328: invokevirtual obtainStyledAttributes : (Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;
      //   331: astore_1
      //   332: aload_1
      //   333: invokevirtual getIndexCount : ()I
      //   336: istore #7
      //   338: iconst_0
      //   339: istore #5
      //   341: iload #5
      //   343: iload #7
      //   345: if_icmpge -> 1993
      //   348: aload_1
      //   349: iload #5
      //   351: invokevirtual getIndex : (I)I
      //   354: istore #6
      //   356: getstatic androidx/constraintlayout/widget/ConstraintLayout$LayoutParams$Table.map : Landroid/util/SparseIntArray;
      //   359: iload #6
      //   361: invokevirtual get : (I)I
      //   364: tableswitch default -> 584, 0 -> 1987, 1 -> 1973, 2 -> 1937, 3 -> 1920, 4 -> 1875, 5 -> 1858, 6 -> 1841, 7 -> 1824, 8 -> 1788, 9 -> 1752, 10 -> 1716, 11 -> 1680, 12 -> 1644, 13 -> 1608, 14 -> 1572, 15 -> 1536, 16 -> 1500, 17 -> 1464, 18 -> 1428, 19 -> 1392, 20 -> 1356, 21 -> 1339, 22 -> 1322, 23 -> 1305, 24 -> 1288, 25 -> 1271, 26 -> 1254, 27 -> 1237, 28 -> 1220, 29 -> 1203, 30 -> 1186, 31 -> 1166, 32 -> 1146, 33 -> 1104, 34 -> 1062, 35 -> 1041, 36 -> 999, 37 -> 957, 38 -> 936, 39 -> 1987, 40 -> 1987, 41 -> 1987, 42 -> 1987, 43 -> 584, 44 -> 683, 45 -> 666, 46 -> 649, 47 -> 635, 48 -> 621, 49 -> 604, 50 -> 587
      //   584: goto -> 1987
      //   587: aload_0
      //   588: aload_1
      //   589: iload #6
      //   591: aload_0
      //   592: getfield editorAbsoluteY : I
      //   595: invokevirtual getDimensionPixelOffset : (II)I
      //   598: putfield editorAbsoluteY : I
      //   601: goto -> 1987
      //   604: aload_0
      //   605: aload_1
      //   606: iload #6
      //   608: aload_0
      //   609: getfield editorAbsoluteX : I
      //   612: invokevirtual getDimensionPixelOffset : (II)I
      //   615: putfield editorAbsoluteX : I
      //   618: goto -> 1987
      //   621: aload_0
      //   622: aload_1
      //   623: iload #6
      //   625: iconst_0
      //   626: invokevirtual getInt : (II)I
      //   629: putfield verticalChainStyle : I
      //   632: goto -> 1987
      //   635: aload_0
      //   636: aload_1
      //   637: iload #6
      //   639: iconst_0
      //   640: invokevirtual getInt : (II)I
      //   643: putfield horizontalChainStyle : I
      //   646: goto -> 1987
      //   649: aload_0
      //   650: aload_1
      //   651: iload #6
      //   653: aload_0
      //   654: getfield verticalWeight : F
      //   657: invokevirtual getFloat : (IF)F
      //   660: putfield verticalWeight : F
      //   663: goto -> 1987
      //   666: aload_0
      //   667: aload_1
      //   668: iload #6
      //   670: aload_0
      //   671: getfield horizontalWeight : F
      //   674: invokevirtual getFloat : (IF)F
      //   677: putfield horizontalWeight : F
      //   680: goto -> 1987
      //   683: aload_0
      //   684: aload_1
      //   685: iload #6
      //   687: invokevirtual getString : (I)Ljava/lang/String;
      //   690: putfield dimensionRatio : Ljava/lang/String;
      //   693: aload_0
      //   694: iconst_m1
      //   695: putfield dimensionRatioSide : I
      //   698: aload_0
      //   699: getfield dimensionRatio : Ljava/lang/String;
      //   702: astore_2
      //   703: aload_2
      //   704: ifnull -> 1987
      //   707: aload_2
      //   708: invokevirtual length : ()I
      //   711: istore #8
      //   713: aload_0
      //   714: getfield dimensionRatio : Ljava/lang/String;
      //   717: bipush #44
      //   719: invokevirtual indexOf : (I)I
      //   722: istore #6
      //   724: iload #6
      //   726: ifle -> 788
      //   729: iload #6
      //   731: iload #8
      //   733: iconst_1
      //   734: isub
      //   735: if_icmpge -> 788
      //   738: aload_0
      //   739: getfield dimensionRatio : Ljava/lang/String;
      //   742: iconst_0
      //   743: iload #6
      //   745: invokevirtual substring : (II)Ljava/lang/String;
      //   748: astore_2
      //   749: aload_2
      //   750: ldc_w 'W'
      //   753: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
      //   756: ifeq -> 767
      //   759: aload_0
      //   760: iconst_0
      //   761: putfield dimensionRatioSide : I
      //   764: goto -> 782
      //   767: aload_2
      //   768: ldc_w 'H'
      //   771: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
      //   774: ifeq -> 782
      //   777: aload_0
      //   778: iconst_1
      //   779: putfield dimensionRatioSide : I
      //   782: iinc #6, 1
      //   785: goto -> 791
      //   788: iconst_0
      //   789: istore #6
      //   791: aload_0
      //   792: getfield dimensionRatio : Ljava/lang/String;
      //   795: bipush #58
      //   797: invokevirtual indexOf : (I)I
      //   800: istore #9
      //   802: iload #9
      //   804: iflt -> 911
      //   807: iload #9
      //   809: iload #8
      //   811: iconst_1
      //   812: isub
      //   813: if_icmpge -> 911
      //   816: aload_0
      //   817: getfield dimensionRatio : Ljava/lang/String;
      //   820: iload #6
      //   822: iload #9
      //   824: invokevirtual substring : (II)Ljava/lang/String;
      //   827: astore #10
      //   829: aload_0
      //   830: getfield dimensionRatio : Ljava/lang/String;
      //   833: iload #9
      //   835: iconst_1
      //   836: iadd
      //   837: invokevirtual substring : (I)Ljava/lang/String;
      //   840: astore_2
      //   841: aload #10
      //   843: invokevirtual length : ()I
      //   846: ifle -> 1987
      //   849: aload_2
      //   850: invokevirtual length : ()I
      //   853: ifle -> 1987
      //   856: aload #10
      //   858: invokestatic parseFloat : (Ljava/lang/String;)F
      //   861: fstore #4
      //   863: aload_2
      //   864: invokestatic parseFloat : (Ljava/lang/String;)F
      //   867: fstore_3
      //   868: fload #4
      //   870: fconst_0
      //   871: fcmpl
      //   872: ifle -> 1987
      //   875: fload_3
      //   876: fconst_0
      //   877: fcmpl
      //   878: ifle -> 1987
      //   881: aload_0
      //   882: getfield dimensionRatioSide : I
      //   885: iconst_1
      //   886: if_icmpne -> 900
      //   889: fload_3
      //   890: fload #4
      //   892: fdiv
      //   893: invokestatic abs : (F)F
      //   896: pop
      //   897: goto -> 1987
      //   900: fload #4
      //   902: fload_3
      //   903: fdiv
      //   904: invokestatic abs : (F)F
      //   907: pop
      //   908: goto -> 1987
      //   911: aload_0
      //   912: getfield dimensionRatio : Ljava/lang/String;
      //   915: iload #6
      //   917: invokevirtual substring : (I)Ljava/lang/String;
      //   920: astore_2
      //   921: aload_2
      //   922: invokevirtual length : ()I
      //   925: ifle -> 1987
      //   928: aload_2
      //   929: invokestatic parseFloat : (Ljava/lang/String;)F
      //   932: pop
      //   933: goto -> 1987
      //   936: aload_0
      //   937: fconst_0
      //   938: aload_1
      //   939: iload #6
      //   941: aload_0
      //   942: getfield matchConstraintPercentHeight : F
      //   945: invokevirtual getFloat : (IF)F
      //   948: invokestatic max : (FF)F
      //   951: putfield matchConstraintPercentHeight : F
      //   954: goto -> 1987
      //   957: aload_0
      //   958: aload_1
      //   959: iload #6
      //   961: aload_0
      //   962: getfield matchConstraintMaxHeight : I
      //   965: invokevirtual getDimensionPixelSize : (II)I
      //   968: putfield matchConstraintMaxHeight : I
      //   971: goto -> 1987
      //   974: astore_2
      //   975: aload_1
      //   976: iload #6
      //   978: aload_0
      //   979: getfield matchConstraintMaxHeight : I
      //   982: invokevirtual getInt : (II)I
      //   985: bipush #-2
      //   987: if_icmpne -> 1987
      //   990: aload_0
      //   991: bipush #-2
      //   993: putfield matchConstraintMaxHeight : I
      //   996: goto -> 1987
      //   999: aload_0
      //   1000: aload_1
      //   1001: iload #6
      //   1003: aload_0
      //   1004: getfield matchConstraintMinHeight : I
      //   1007: invokevirtual getDimensionPixelSize : (II)I
      //   1010: putfield matchConstraintMinHeight : I
      //   1013: goto -> 1987
      //   1016: astore_2
      //   1017: aload_1
      //   1018: iload #6
      //   1020: aload_0
      //   1021: getfield matchConstraintMinHeight : I
      //   1024: invokevirtual getInt : (II)I
      //   1027: bipush #-2
      //   1029: if_icmpne -> 1987
      //   1032: aload_0
      //   1033: bipush #-2
      //   1035: putfield matchConstraintMinHeight : I
      //   1038: goto -> 1987
      //   1041: aload_0
      //   1042: fconst_0
      //   1043: aload_1
      //   1044: iload #6
      //   1046: aload_0
      //   1047: getfield matchConstraintPercentWidth : F
      //   1050: invokevirtual getFloat : (IF)F
      //   1053: invokestatic max : (FF)F
      //   1056: putfield matchConstraintPercentWidth : F
      //   1059: goto -> 1987
      //   1062: aload_0
      //   1063: aload_1
      //   1064: iload #6
      //   1066: aload_0
      //   1067: getfield matchConstraintMaxWidth : I
      //   1070: invokevirtual getDimensionPixelSize : (II)I
      //   1073: putfield matchConstraintMaxWidth : I
      //   1076: goto -> 1987
      //   1079: astore_2
      //   1080: aload_1
      //   1081: iload #6
      //   1083: aload_0
      //   1084: getfield matchConstraintMaxWidth : I
      //   1087: invokevirtual getInt : (II)I
      //   1090: bipush #-2
      //   1092: if_icmpne -> 1987
      //   1095: aload_0
      //   1096: bipush #-2
      //   1098: putfield matchConstraintMaxWidth : I
      //   1101: goto -> 1987
      //   1104: aload_0
      //   1105: aload_1
      //   1106: iload #6
      //   1108: aload_0
      //   1109: getfield matchConstraintMinWidth : I
      //   1112: invokevirtual getDimensionPixelSize : (II)I
      //   1115: putfield matchConstraintMinWidth : I
      //   1118: goto -> 1987
      //   1121: astore_2
      //   1122: aload_1
      //   1123: iload #6
      //   1125: aload_0
      //   1126: getfield matchConstraintMinWidth : I
      //   1129: invokevirtual getInt : (II)I
      //   1132: bipush #-2
      //   1134: if_icmpne -> 1987
      //   1137: aload_0
      //   1138: bipush #-2
      //   1140: putfield matchConstraintMinWidth : I
      //   1143: goto -> 1987
      //   1146: aload_0
      //   1147: aload_1
      //   1148: iload #6
      //   1150: iconst_0
      //   1151: invokevirtual getInt : (II)I
      //   1154: putfield matchConstraintDefaultHeight : I
      //   1157: aload_0
      //   1158: getfield matchConstraintDefaultHeight : I
      //   1161: istore #6
      //   1163: goto -> 1987
      //   1166: aload_0
      //   1167: aload_1
      //   1168: iload #6
      //   1170: iconst_0
      //   1171: invokevirtual getInt : (II)I
      //   1174: putfield matchConstraintDefaultWidth : I
      //   1177: aload_0
      //   1178: getfield matchConstraintDefaultWidth : I
      //   1181: istore #6
      //   1183: goto -> 1987
      //   1186: aload_0
      //   1187: aload_1
      //   1188: iload #6
      //   1190: aload_0
      //   1191: getfield verticalBias : F
      //   1194: invokevirtual getFloat : (IF)F
      //   1197: putfield verticalBias : F
      //   1200: goto -> 1987
      //   1203: aload_0
      //   1204: aload_1
      //   1205: iload #6
      //   1207: aload_0
      //   1208: getfield horizontalBias : F
      //   1211: invokevirtual getFloat : (IF)F
      //   1214: putfield horizontalBias : F
      //   1217: goto -> 1987
      //   1220: aload_0
      //   1221: aload_1
      //   1222: iload #6
      //   1224: aload_0
      //   1225: getfield constrainedHeight : Z
      //   1228: invokevirtual getBoolean : (IZ)Z
      //   1231: putfield constrainedHeight : Z
      //   1234: goto -> 1987
      //   1237: aload_0
      //   1238: aload_1
      //   1239: iload #6
      //   1241: aload_0
      //   1242: getfield constrainedWidth : Z
      //   1245: invokevirtual getBoolean : (IZ)Z
      //   1248: putfield constrainedWidth : Z
      //   1251: goto -> 1987
      //   1254: aload_0
      //   1255: aload_1
      //   1256: iload #6
      //   1258: aload_0
      //   1259: getfield goneEndMargin : I
      //   1262: invokevirtual getDimensionPixelSize : (II)I
      //   1265: putfield goneEndMargin : I
      //   1268: goto -> 1987
      //   1271: aload_0
      //   1272: aload_1
      //   1273: iload #6
      //   1275: aload_0
      //   1276: getfield goneStartMargin : I
      //   1279: invokevirtual getDimensionPixelSize : (II)I
      //   1282: putfield goneStartMargin : I
      //   1285: goto -> 1987
      //   1288: aload_0
      //   1289: aload_1
      //   1290: iload #6
      //   1292: aload_0
      //   1293: getfield goneBottomMargin : I
      //   1296: invokevirtual getDimensionPixelSize : (II)I
      //   1299: putfield goneBottomMargin : I
      //   1302: goto -> 1987
      //   1305: aload_0
      //   1306: aload_1
      //   1307: iload #6
      //   1309: aload_0
      //   1310: getfield goneRightMargin : I
      //   1313: invokevirtual getDimensionPixelSize : (II)I
      //   1316: putfield goneRightMargin : I
      //   1319: goto -> 1987
      //   1322: aload_0
      //   1323: aload_1
      //   1324: iload #6
      //   1326: aload_0
      //   1327: getfield goneTopMargin : I
      //   1330: invokevirtual getDimensionPixelSize : (II)I
      //   1333: putfield goneTopMargin : I
      //   1336: goto -> 1987
      //   1339: aload_0
      //   1340: aload_1
      //   1341: iload #6
      //   1343: aload_0
      //   1344: getfield goneLeftMargin : I
      //   1347: invokevirtual getDimensionPixelSize : (II)I
      //   1350: putfield goneLeftMargin : I
      //   1353: goto -> 1987
      //   1356: aload_0
      //   1357: aload_1
      //   1358: iload #6
      //   1360: aload_0
      //   1361: getfield endToEnd : I
      //   1364: invokevirtual getResourceId : (II)I
      //   1367: putfield endToEnd : I
      //   1370: aload_0
      //   1371: getfield endToEnd : I
      //   1374: iconst_m1
      //   1375: if_icmpne -> 1987
      //   1378: aload_0
      //   1379: aload_1
      //   1380: iload #6
      //   1382: iconst_m1
      //   1383: invokevirtual getInt : (II)I
      //   1386: putfield endToEnd : I
      //   1389: goto -> 1987
      //   1392: aload_0
      //   1393: aload_1
      //   1394: iload #6
      //   1396: aload_0
      //   1397: getfield endToStart : I
      //   1400: invokevirtual getResourceId : (II)I
      //   1403: putfield endToStart : I
      //   1406: aload_0
      //   1407: getfield endToStart : I
      //   1410: iconst_m1
      //   1411: if_icmpne -> 1987
      //   1414: aload_0
      //   1415: aload_1
      //   1416: iload #6
      //   1418: iconst_m1
      //   1419: invokevirtual getInt : (II)I
      //   1422: putfield endToStart : I
      //   1425: goto -> 1987
      //   1428: aload_0
      //   1429: aload_1
      //   1430: iload #6
      //   1432: aload_0
      //   1433: getfield startToStart : I
      //   1436: invokevirtual getResourceId : (II)I
      //   1439: putfield startToStart : I
      //   1442: aload_0
      //   1443: getfield startToStart : I
      //   1446: iconst_m1
      //   1447: if_icmpne -> 1987
      //   1450: aload_0
      //   1451: aload_1
      //   1452: iload #6
      //   1454: iconst_m1
      //   1455: invokevirtual getInt : (II)I
      //   1458: putfield startToStart : I
      //   1461: goto -> 1987
      //   1464: aload_0
      //   1465: aload_1
      //   1466: iload #6
      //   1468: aload_0
      //   1469: getfield startToEnd : I
      //   1472: invokevirtual getResourceId : (II)I
      //   1475: putfield startToEnd : I
      //   1478: aload_0
      //   1479: getfield startToEnd : I
      //   1482: iconst_m1
      //   1483: if_icmpne -> 1987
      //   1486: aload_0
      //   1487: aload_1
      //   1488: iload #6
      //   1490: iconst_m1
      //   1491: invokevirtual getInt : (II)I
      //   1494: putfield startToEnd : I
      //   1497: goto -> 1987
      //   1500: aload_0
      //   1501: aload_1
      //   1502: iload #6
      //   1504: aload_0
      //   1505: getfield baselineToBaseline : I
      //   1508: invokevirtual getResourceId : (II)I
      //   1511: putfield baselineToBaseline : I
      //   1514: aload_0
      //   1515: getfield baselineToBaseline : I
      //   1518: iconst_m1
      //   1519: if_icmpne -> 1987
      //   1522: aload_0
      //   1523: aload_1
      //   1524: iload #6
      //   1526: iconst_m1
      //   1527: invokevirtual getInt : (II)I
      //   1530: putfield baselineToBaseline : I
      //   1533: goto -> 1987
      //   1536: aload_0
      //   1537: aload_1
      //   1538: iload #6
      //   1540: aload_0
      //   1541: getfield bottomToBottom : I
      //   1544: invokevirtual getResourceId : (II)I
      //   1547: putfield bottomToBottom : I
      //   1550: aload_0
      //   1551: getfield bottomToBottom : I
      //   1554: iconst_m1
      //   1555: if_icmpne -> 1987
      //   1558: aload_0
      //   1559: aload_1
      //   1560: iload #6
      //   1562: iconst_m1
      //   1563: invokevirtual getInt : (II)I
      //   1566: putfield bottomToBottom : I
      //   1569: goto -> 1987
      //   1572: aload_0
      //   1573: aload_1
      //   1574: iload #6
      //   1576: aload_0
      //   1577: getfield bottomToTop : I
      //   1580: invokevirtual getResourceId : (II)I
      //   1583: putfield bottomToTop : I
      //   1586: aload_0
      //   1587: getfield bottomToTop : I
      //   1590: iconst_m1
      //   1591: if_icmpne -> 1987
      //   1594: aload_0
      //   1595: aload_1
      //   1596: iload #6
      //   1598: iconst_m1
      //   1599: invokevirtual getInt : (II)I
      //   1602: putfield bottomToTop : I
      //   1605: goto -> 1987
      //   1608: aload_0
      //   1609: aload_1
      //   1610: iload #6
      //   1612: aload_0
      //   1613: getfield topToBottom : I
      //   1616: invokevirtual getResourceId : (II)I
      //   1619: putfield topToBottom : I
      //   1622: aload_0
      //   1623: getfield topToBottom : I
      //   1626: iconst_m1
      //   1627: if_icmpne -> 1987
      //   1630: aload_0
      //   1631: aload_1
      //   1632: iload #6
      //   1634: iconst_m1
      //   1635: invokevirtual getInt : (II)I
      //   1638: putfield topToBottom : I
      //   1641: goto -> 1987
      //   1644: aload_0
      //   1645: aload_1
      //   1646: iload #6
      //   1648: aload_0
      //   1649: getfield topToTop : I
      //   1652: invokevirtual getResourceId : (II)I
      //   1655: putfield topToTop : I
      //   1658: aload_0
      //   1659: getfield topToTop : I
      //   1662: iconst_m1
      //   1663: if_icmpne -> 1987
      //   1666: aload_0
      //   1667: aload_1
      //   1668: iload #6
      //   1670: iconst_m1
      //   1671: invokevirtual getInt : (II)I
      //   1674: putfield topToTop : I
      //   1677: goto -> 1987
      //   1680: aload_0
      //   1681: aload_1
      //   1682: iload #6
      //   1684: aload_0
      //   1685: getfield rightToRight : I
      //   1688: invokevirtual getResourceId : (II)I
      //   1691: putfield rightToRight : I
      //   1694: aload_0
      //   1695: getfield rightToRight : I
      //   1698: iconst_m1
      //   1699: if_icmpne -> 1987
      //   1702: aload_0
      //   1703: aload_1
      //   1704: iload #6
      //   1706: iconst_m1
      //   1707: invokevirtual getInt : (II)I
      //   1710: putfield rightToRight : I
      //   1713: goto -> 1987
      //   1716: aload_0
      //   1717: aload_1
      //   1718: iload #6
      //   1720: aload_0
      //   1721: getfield rightToLeft : I
      //   1724: invokevirtual getResourceId : (II)I
      //   1727: putfield rightToLeft : I
      //   1730: aload_0
      //   1731: getfield rightToLeft : I
      //   1734: iconst_m1
      //   1735: if_icmpne -> 1987
      //   1738: aload_0
      //   1739: aload_1
      //   1740: iload #6
      //   1742: iconst_m1
      //   1743: invokevirtual getInt : (II)I
      //   1746: putfield rightToLeft : I
      //   1749: goto -> 1987
      //   1752: aload_0
      //   1753: aload_1
      //   1754: iload #6
      //   1756: aload_0
      //   1757: getfield leftToRight : I
      //   1760: invokevirtual getResourceId : (II)I
      //   1763: putfield leftToRight : I
      //   1766: aload_0
      //   1767: getfield leftToRight : I
      //   1770: iconst_m1
      //   1771: if_icmpne -> 1987
      //   1774: aload_0
      //   1775: aload_1
      //   1776: iload #6
      //   1778: iconst_m1
      //   1779: invokevirtual getInt : (II)I
      //   1782: putfield leftToRight : I
      //   1785: goto -> 1987
      //   1788: aload_0
      //   1789: aload_1
      //   1790: iload #6
      //   1792: aload_0
      //   1793: getfield leftToLeft : I
      //   1796: invokevirtual getResourceId : (II)I
      //   1799: putfield leftToLeft : I
      //   1802: aload_0
      //   1803: getfield leftToLeft : I
      //   1806: iconst_m1
      //   1807: if_icmpne -> 1987
      //   1810: aload_0
      //   1811: aload_1
      //   1812: iload #6
      //   1814: iconst_m1
      //   1815: invokevirtual getInt : (II)I
      //   1818: putfield leftToLeft : I
      //   1821: goto -> 1987
      //   1824: aload_0
      //   1825: aload_1
      //   1826: iload #6
      //   1828: aload_0
      //   1829: getfield guidePercent : F
      //   1832: invokevirtual getFloat : (IF)F
      //   1835: putfield guidePercent : F
      //   1838: goto -> 1987
      //   1841: aload_0
      //   1842: aload_1
      //   1843: iload #6
      //   1845: aload_0
      //   1846: getfield guideEnd : I
      //   1849: invokevirtual getDimensionPixelOffset : (II)I
      //   1852: putfield guideEnd : I
      //   1855: goto -> 1987
      //   1858: aload_0
      //   1859: aload_1
      //   1860: iload #6
      //   1862: aload_0
      //   1863: getfield guideBegin : I
      //   1866: invokevirtual getDimensionPixelOffset : (II)I
      //   1869: putfield guideBegin : I
      //   1872: goto -> 1987
      //   1875: aload_0
      //   1876: aload_1
      //   1877: iload #6
      //   1879: aload_0
      //   1880: getfield circleAngle : F
      //   1883: invokevirtual getFloat : (IF)F
      //   1886: ldc_w 360.0
      //   1889: frem
      //   1890: putfield circleAngle : F
      //   1893: aload_0
      //   1894: getfield circleAngle : F
      //   1897: fstore_3
      //   1898: fload_3
      //   1899: fconst_0
      //   1900: fcmpg
      //   1901: ifge -> 1987
      //   1904: aload_0
      //   1905: ldc_w 360.0
      //   1908: fload_3
      //   1909: fsub
      //   1910: ldc_w 360.0
      //   1913: frem
      //   1914: putfield circleAngle : F
      //   1917: goto -> 1987
      //   1920: aload_0
      //   1921: aload_1
      //   1922: iload #6
      //   1924: aload_0
      //   1925: getfield circleRadius : I
      //   1928: invokevirtual getDimensionPixelSize : (II)I
      //   1931: putfield circleRadius : I
      //   1934: goto -> 1987
      //   1937: aload_0
      //   1938: aload_1
      //   1939: iload #6
      //   1941: aload_0
      //   1942: getfield circleConstraint : I
      //   1945: invokevirtual getResourceId : (II)I
      //   1948: putfield circleConstraint : I
      //   1951: aload_0
      //   1952: getfield circleConstraint : I
      //   1955: iconst_m1
      //   1956: if_icmpne -> 1987
      //   1959: aload_0
      //   1960: aload_1
      //   1961: iload #6
      //   1963: iconst_m1
      //   1964: invokevirtual getInt : (II)I
      //   1967: putfield circleConstraint : I
      //   1970: goto -> 1987
      //   1973: aload_0
      //   1974: aload_1
      //   1975: iload #6
      //   1977: aload_0
      //   1978: getfield orientation : I
      //   1981: invokevirtual getInt : (II)I
      //   1984: putfield orientation : I
      //   1987: iinc #5, 1
      //   1990: goto -> 341
      //   1993: aload_1
      //   1994: invokevirtual recycle : ()V
      //   1997: aload_0
      //   1998: invokevirtual validate : ()V
      //   2001: return
      //   2002: astore_2
      //   2003: goto -> 1987
      // Exception table:
      //   from	to	target	type
      //   856	868	2002	java/lang/NumberFormatException
      //   881	897	2002	java/lang/NumberFormatException
      //   900	908	2002	java/lang/NumberFormatException
      //   928	933	2002	java/lang/NumberFormatException
      //   957	971	974	java/lang/Exception
      //   999	1013	1016	java/lang/Exception
      //   1062	1076	1079	java/lang/Exception
      //   1104	1118	1121	java/lang/Exception
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    @TargetApi(17)
    public void resolveLayoutDirection(int param1Int) {
      // Byte code:
      //   0: aload_0
      //   1: getfield leftMargin : I
      //   4: istore #4
      //   6: aload_0
      //   7: getfield rightMargin : I
      //   10: istore #5
      //   12: aload_0
      //   13: iload_1
      //   14: invokespecial resolveLayoutDirection : (I)V
      //   17: aload_0
      //   18: iconst_m1
      //   19: putfield resolvedRightToLeft : I
      //   22: aload_0
      //   23: iconst_m1
      //   24: putfield resolvedRightToRight : I
      //   27: aload_0
      //   28: iconst_m1
      //   29: putfield resolvedLeftToLeft : I
      //   32: aload_0
      //   33: iconst_m1
      //   34: putfield resolvedLeftToRight : I
      //   37: aload_0
      //   38: iconst_m1
      //   39: putfield resolveGoneLeftMargin : I
      //   42: aload_0
      //   43: iconst_m1
      //   44: putfield resolveGoneRightMargin : I
      //   47: aload_0
      //   48: aload_0
      //   49: getfield goneLeftMargin : I
      //   52: putfield resolveGoneLeftMargin : I
      //   55: aload_0
      //   56: aload_0
      //   57: getfield goneRightMargin : I
      //   60: putfield resolveGoneRightMargin : I
      //   63: aload_0
      //   64: aload_0
      //   65: getfield horizontalBias : F
      //   68: putfield resolvedHorizontalBias : F
      //   71: aload_0
      //   72: aload_0
      //   73: getfield guideBegin : I
      //   76: putfield resolvedGuideBegin : I
      //   79: aload_0
      //   80: aload_0
      //   81: getfield guideEnd : I
      //   84: putfield resolvedGuideEnd : I
      //   87: aload_0
      //   88: aload_0
      //   89: getfield guidePercent : F
      //   92: putfield resolvedGuidePercent : F
      //   95: aload_0
      //   96: invokevirtual getLayoutDirection : ()I
      //   99: istore_1
      //   100: iconst_0
      //   101: istore_3
      //   102: iconst_1
      //   103: iload_1
      //   104: if_icmpne -> 112
      //   107: iconst_1
      //   108: istore_1
      //   109: goto -> 114
      //   112: iconst_0
      //   113: istore_1
      //   114: iload_1
      //   115: ifeq -> 344
      //   118: aload_0
      //   119: getfield startToEnd : I
      //   122: istore_1
      //   123: iload_1
      //   124: iconst_m1
      //   125: if_icmpeq -> 138
      //   128: aload_0
      //   129: iload_1
      //   130: putfield resolvedRightToLeft : I
      //   133: iconst_1
      //   134: istore_1
      //   135: goto -> 161
      //   138: aload_0
      //   139: getfield startToStart : I
      //   142: istore #6
      //   144: iload_3
      //   145: istore_1
      //   146: iload #6
      //   148: iconst_m1
      //   149: if_icmpeq -> 161
      //   152: aload_0
      //   153: iload #6
      //   155: putfield resolvedRightToRight : I
      //   158: goto -> 133
      //   161: aload_0
      //   162: getfield endToStart : I
      //   165: istore_3
      //   166: iload_3
      //   167: iconst_m1
      //   168: if_icmpeq -> 178
      //   171: aload_0
      //   172: iload_3
      //   173: putfield resolvedLeftToRight : I
      //   176: iconst_1
      //   177: istore_1
      //   178: aload_0
      //   179: getfield endToEnd : I
      //   182: istore_3
      //   183: iload_3
      //   184: iconst_m1
      //   185: if_icmpeq -> 195
      //   188: aload_0
      //   189: iload_3
      //   190: putfield resolvedLeftToLeft : I
      //   193: iconst_1
      //   194: istore_1
      //   195: aload_0
      //   196: getfield goneStartMargin : I
      //   199: istore_3
      //   200: iload_3
      //   201: iconst_m1
      //   202: if_icmpeq -> 210
      //   205: aload_0
      //   206: iload_3
      //   207: putfield resolveGoneRightMargin : I
      //   210: aload_0
      //   211: getfield goneEndMargin : I
      //   214: istore_3
      //   215: iload_3
      //   216: iconst_m1
      //   217: if_icmpeq -> 225
      //   220: aload_0
      //   221: iload_3
      //   222: putfield resolveGoneLeftMargin : I
      //   225: iload_1
      //   226: ifeq -> 239
      //   229: aload_0
      //   230: fconst_1
      //   231: aload_0
      //   232: getfield horizontalBias : F
      //   235: fsub
      //   236: putfield resolvedHorizontalBias : F
      //   239: aload_0
      //   240: getfield isGuideline : Z
      //   243: ifeq -> 434
      //   246: aload_0
      //   247: getfield orientation : I
      //   250: iconst_1
      //   251: if_icmpne -> 434
      //   254: aload_0
      //   255: getfield guidePercent : F
      //   258: fstore_2
      //   259: fload_2
      //   260: ldc -1.0
      //   262: fcmpl
      //   263: ifeq -> 286
      //   266: aload_0
      //   267: fconst_1
      //   268: fload_2
      //   269: fsub
      //   270: putfield resolvedGuidePercent : F
      //   273: aload_0
      //   274: iconst_m1
      //   275: putfield resolvedGuideBegin : I
      //   278: aload_0
      //   279: iconst_m1
      //   280: putfield resolvedGuideEnd : I
      //   283: goto -> 434
      //   286: aload_0
      //   287: getfield guideBegin : I
      //   290: istore_1
      //   291: iload_1
      //   292: iconst_m1
      //   293: if_icmpeq -> 315
      //   296: aload_0
      //   297: iload_1
      //   298: putfield resolvedGuideEnd : I
      //   301: aload_0
      //   302: iconst_m1
      //   303: putfield resolvedGuideBegin : I
      //   306: aload_0
      //   307: ldc -1.0
      //   309: putfield resolvedGuidePercent : F
      //   312: goto -> 434
      //   315: aload_0
      //   316: getfield guideEnd : I
      //   319: istore_1
      //   320: iload_1
      //   321: iconst_m1
      //   322: if_icmpeq -> 434
      //   325: aload_0
      //   326: iload_1
      //   327: putfield resolvedGuideBegin : I
      //   330: aload_0
      //   331: iconst_m1
      //   332: putfield resolvedGuideEnd : I
      //   335: aload_0
      //   336: ldc -1.0
      //   338: putfield resolvedGuidePercent : F
      //   341: goto -> 434
      //   344: aload_0
      //   345: getfield startToEnd : I
      //   348: istore_1
      //   349: iload_1
      //   350: iconst_m1
      //   351: if_icmpeq -> 359
      //   354: aload_0
      //   355: iload_1
      //   356: putfield resolvedLeftToRight : I
      //   359: aload_0
      //   360: getfield startToStart : I
      //   363: istore_1
      //   364: iload_1
      //   365: iconst_m1
      //   366: if_icmpeq -> 374
      //   369: aload_0
      //   370: iload_1
      //   371: putfield resolvedLeftToLeft : I
      //   374: aload_0
      //   375: getfield endToStart : I
      //   378: istore_1
      //   379: iload_1
      //   380: iconst_m1
      //   381: if_icmpeq -> 389
      //   384: aload_0
      //   385: iload_1
      //   386: putfield resolvedRightToLeft : I
      //   389: aload_0
      //   390: getfield endToEnd : I
      //   393: istore_1
      //   394: iload_1
      //   395: iconst_m1
      //   396: if_icmpeq -> 404
      //   399: aload_0
      //   400: iload_1
      //   401: putfield resolvedRightToRight : I
      //   404: aload_0
      //   405: getfield goneStartMargin : I
      //   408: istore_1
      //   409: iload_1
      //   410: iconst_m1
      //   411: if_icmpeq -> 419
      //   414: aload_0
      //   415: iload_1
      //   416: putfield resolveGoneLeftMargin : I
      //   419: aload_0
      //   420: getfield goneEndMargin : I
      //   423: istore_1
      //   424: iload_1
      //   425: iconst_m1
      //   426: if_icmpeq -> 434
      //   429: aload_0
      //   430: iload_1
      //   431: putfield resolveGoneRightMargin : I
      //   434: aload_0
      //   435: getfield endToStart : I
      //   438: iconst_m1
      //   439: if_icmpne -> 604
      //   442: aload_0
      //   443: getfield endToEnd : I
      //   446: iconst_m1
      //   447: if_icmpne -> 604
      //   450: aload_0
      //   451: getfield startToStart : I
      //   454: iconst_m1
      //   455: if_icmpne -> 604
      //   458: aload_0
      //   459: getfield startToEnd : I
      //   462: iconst_m1
      //   463: if_icmpne -> 604
      //   466: aload_0
      //   467: getfield rightToLeft : I
      //   470: istore_1
      //   471: iload_1
      //   472: iconst_m1
      //   473: if_icmpeq -> 502
      //   476: aload_0
      //   477: iload_1
      //   478: putfield resolvedRightToLeft : I
      //   481: aload_0
      //   482: getfield rightMargin : I
      //   485: ifgt -> 535
      //   488: iload #5
      //   490: ifle -> 535
      //   493: aload_0
      //   494: iload #5
      //   496: putfield rightMargin : I
      //   499: goto -> 535
      //   502: aload_0
      //   503: getfield rightToRight : I
      //   506: istore_1
      //   507: iload_1
      //   508: iconst_m1
      //   509: if_icmpeq -> 535
      //   512: aload_0
      //   513: iload_1
      //   514: putfield resolvedRightToRight : I
      //   517: aload_0
      //   518: getfield rightMargin : I
      //   521: ifgt -> 535
      //   524: iload #5
      //   526: ifle -> 535
      //   529: aload_0
      //   530: iload #5
      //   532: putfield rightMargin : I
      //   535: aload_0
      //   536: getfield leftToLeft : I
      //   539: istore_1
      //   540: iload_1
      //   541: iconst_m1
      //   542: if_icmpeq -> 571
      //   545: aload_0
      //   546: iload_1
      //   547: putfield resolvedLeftToLeft : I
      //   550: aload_0
      //   551: getfield leftMargin : I
      //   554: ifgt -> 604
      //   557: iload #4
      //   559: ifle -> 604
      //   562: aload_0
      //   563: iload #4
      //   565: putfield leftMargin : I
      //   568: goto -> 604
      //   571: aload_0
      //   572: getfield leftToRight : I
      //   575: istore_1
      //   576: iload_1
      //   577: iconst_m1
      //   578: if_icmpeq -> 604
      //   581: aload_0
      //   582: iload_1
      //   583: putfield resolvedLeftToRight : I
      //   586: aload_0
      //   587: getfield leftMargin : I
      //   590: ifgt -> 604
      //   593: iload #4
      //   595: ifle -> 604
      //   598: aload_0
      //   599: iload #4
      //   601: putfield leftMargin : I
      //   604: return
    }
    
    public void validate() {
      this.isGuideline = false;
      this.horizontalDimensionFixed = true;
      this.verticalDimensionFixed = true;
      if (this.width == -2 && this.constrainedWidth) {
        this.horizontalDimensionFixed = false;
        this.matchConstraintDefaultWidth = 1;
      } 
      if (this.height == -2 && this.constrainedHeight) {
        this.verticalDimensionFixed = false;
        this.matchConstraintDefaultHeight = 1;
      } 
      if (this.width == 0 || this.width == -1) {
        this.horizontalDimensionFixed = false;
        if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
          this.width = -2;
          this.constrainedWidth = true;
        } 
      } 
      if (this.height == 0 || this.height == -1) {
        this.verticalDimensionFixed = false;
        if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
          this.height = -2;
          this.constrainedHeight = true;
        } 
      } 
      if (this.guidePercent != -1.0F || this.guideBegin != -1 || this.guideEnd != -1) {
        this.isGuideline = true;
        this.horizontalDimensionFixed = true;
        this.verticalDimensionFixed = true;
        if (!(this.widget instanceof Guideline))
          this.widget = (ConstraintWidget)new Guideline(); 
        ((Guideline)this.widget).setOrientation(this.orientation);
      } 
    }
    
    private static class Table {
      public static final SparseIntArray map = new SparseIntArray();
      
      static {
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
        map.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
        map.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
        map.append(R$styleable.ConstraintLayout_Layout_android_orientation, 1);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
        map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
        map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
        map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
        map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
        map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
        map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
        map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
      }
    }
  }
  
  private static class Table {
    public static final SparseIntArray map = new SparseIntArray();
    
    static {
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
      map.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
      map.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
      map.append(R$styleable.ConstraintLayout_Layout_android_orientation, 1);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
      map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
      map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
      map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
      map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
      map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
      map.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
      map.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/widget/ConstraintLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */