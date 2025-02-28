package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;

public class ConstraintWidget {
  public static float DEFAULT_BIAS = 0.5F;
  
  protected ArrayList<ConstraintAnchor> mAnchors = new ArrayList<ConstraintAnchor>();
  
  ConstraintAnchor mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
  
  int mBaselineDistance;
  
  ConstraintWidgetGroup mBelongingGroup = null;
  
  ConstraintAnchor mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
  
  ConstraintAnchor mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
  
  ConstraintAnchor mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
  
  ConstraintAnchor mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
  
  private float mCircleConstraintAngle = 0.0F;
  
  private Object mCompanionWidget;
  
  private String mDebugName;
  
  protected float mDimensionRatio;
  
  protected int mDimensionRatioSide;
  
  private int mDrawX;
  
  private int mDrawY;
  
  boolean mGroupsToSolver;
  
  int mHeight;
  
  float mHorizontalBiasPercent;
  
  int mHorizontalChainStyle;
  
  public int mHorizontalResolution = -1;
  
  ConstraintAnchor mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
  
  protected ConstraintAnchor[] mListAnchors = new ConstraintAnchor[] { this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter };
  
  protected DimensionBehaviour[] mListDimensionBehaviors;
  
  protected ConstraintWidget[] mListNextMatchConstraintsWidget;
  
  int mMatchConstraintDefaultHeight = 0;
  
  int mMatchConstraintDefaultWidth = 0;
  
  int mMatchConstraintMaxHeight = 0;
  
  int mMatchConstraintMaxWidth = 0;
  
  int mMatchConstraintMinHeight = 0;
  
  int mMatchConstraintMinWidth = 0;
  
  float mMatchConstraintPercentHeight = 1.0F;
  
  float mMatchConstraintPercentWidth = 1.0F;
  
  private int[] mMaxDimension = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };
  
  protected int mMinHeight;
  
  protected int mMinWidth;
  
  protected ConstraintWidget[] mNextChainWidget;
  
  protected int mOffsetX;
  
  protected int mOffsetY;
  
  boolean mOptimizerMeasurable;
  
  boolean mOptimizerMeasured;
  
  ConstraintWidget mParent;
  
  int mRelX;
  
  int mRelY;
  
  ResolutionDimension mResolutionHeight;
  
  ResolutionDimension mResolutionWidth;
  
  float mResolvedDimensionRatio = 1.0F;
  
  int mResolvedDimensionRatioSide = -1;
  
  int[] mResolvedMatchConstraintDefault = new int[2];
  
  ConstraintAnchor mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
  
  ConstraintAnchor mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
  
  private String mType;
  
  float mVerticalBiasPercent;
  
  int mVerticalChainStyle;
  
  public int mVerticalResolution = -1;
  
  private int mVisibility;
  
  float[] mWeight;
  
  int mWidth;
  
  private int mWrapHeight;
  
  private int mWrapWidth;
  
  protected int mX;
  
  protected int mY;
  
  public ConstraintWidget() {
    DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
    this.mListDimensionBehaviors = new DimensionBehaviour[] { dimensionBehaviour, dimensionBehaviour };
    this.mParent = null;
    this.mWidth = 0;
    this.mHeight = 0;
    this.mDimensionRatio = 0.0F;
    this.mDimensionRatioSide = -1;
    this.mX = 0;
    this.mY = 0;
    this.mRelX = 0;
    this.mRelY = 0;
    this.mDrawX = 0;
    this.mDrawY = 0;
    this.mOffsetX = 0;
    this.mOffsetY = 0;
    this.mBaselineDistance = 0;
    float f = DEFAULT_BIAS;
    this.mHorizontalBiasPercent = f;
    this.mVerticalBiasPercent = f;
    this.mVisibility = 0;
    this.mDebugName = null;
    this.mType = null;
    this.mOptimizerMeasurable = false;
    this.mOptimizerMeasured = false;
    this.mGroupsToSolver = false;
    this.mHorizontalChainStyle = 0;
    this.mVerticalChainStyle = 0;
    this.mWeight = new float[] { -1.0F, -1.0F };
    this.mListNextMatchConstraintsWidget = new ConstraintWidget[] { null, null };
    this.mNextChainWidget = new ConstraintWidget[] { null, null };
    addAnchors();
  }
  
  private void addAnchors() {
    this.mAnchors.add(this.mLeft);
    this.mAnchors.add(this.mTop);
    this.mAnchors.add(this.mRight);
    this.mAnchors.add(this.mBottom);
    this.mAnchors.add(this.mCenterX);
    this.mAnchors.add(this.mCenterY);
    this.mAnchors.add(this.mCenter);
    this.mAnchors.add(this.mBaseline);
  }
  
  private void applyConstraints(LinearSystem paramLinearSystem, boolean paramBoolean1, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, DimensionBehaviour paramDimensionBehaviour, boolean paramBoolean2, ConstraintAnchor paramConstraintAnchor1, ConstraintAnchor paramConstraintAnchor2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, boolean paramBoolean3, boolean paramBoolean4, int paramInt5, int paramInt6, int paramInt7, float paramFloat2, boolean paramBoolean5) {
    // Byte code:
    //   0: aload_1
    //   1: aload #7
    //   3: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   6: astore #28
    //   8: aload_1
    //   9: aload #8
    //   11: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   14: astore #26
    //   16: aload_1
    //   17: aload #7
    //   19: invokevirtual getTarget : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   22: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   25: astore #29
    //   27: aload_1
    //   28: aload #8
    //   30: invokevirtual getTarget : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   33: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   36: astore #30
    //   38: aload_1
    //   39: getfield graphOptimizer : Z
    //   42: ifeq -> 128
    //   45: aload #7
    //   47: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   50: getfield state : I
    //   53: iconst_1
    //   54: if_icmpne -> 128
    //   57: aload #8
    //   59: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   62: getfield state : I
    //   65: iconst_1
    //   66: if_icmpne -> 128
    //   69: invokestatic getMetrics : ()Landroidx/constraintlayout/solver/Metrics;
    //   72: ifnull -> 89
    //   75: invokestatic getMetrics : ()Landroidx/constraintlayout/solver/Metrics;
    //   78: astore_3
    //   79: aload_3
    //   80: aload_3
    //   81: getfield resolvedWidgets : J
    //   84: lconst_1
    //   85: ladd
    //   86: putfield resolvedWidgets : J
    //   89: aload #7
    //   91: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   94: aload_1
    //   95: invokevirtual addResolvedValue : (Landroidx/constraintlayout/solver/LinearSystem;)V
    //   98: aload #8
    //   100: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   103: aload_1
    //   104: invokevirtual addResolvedValue : (Landroidx/constraintlayout/solver/LinearSystem;)V
    //   107: iload #15
    //   109: ifne -> 127
    //   112: iload_2
    //   113: ifeq -> 127
    //   116: aload_1
    //   117: aload #4
    //   119: aload #26
    //   121: iconst_0
    //   122: bipush #6
    //   124: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   127: return
    //   128: invokestatic getMetrics : ()Landroidx/constraintlayout/solver/Metrics;
    //   131: ifnull -> 151
    //   134: invokestatic getMetrics : ()Landroidx/constraintlayout/solver/Metrics;
    //   137: astore #27
    //   139: aload #27
    //   141: aload #27
    //   143: getfield nonresolvedWidgets : J
    //   146: lconst_1
    //   147: ladd
    //   148: putfield nonresolvedWidgets : J
    //   151: aload #7
    //   153: invokevirtual isConnected : ()Z
    //   156: istore #24
    //   158: aload #8
    //   160: invokevirtual isConnected : ()Z
    //   163: istore #25
    //   165: aload_0
    //   166: getfield mCenter : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   169: invokevirtual isConnected : ()Z
    //   172: istore #23
    //   174: iload #24
    //   176: ifeq -> 185
    //   179: iconst_1
    //   180: istore #22
    //   182: goto -> 188
    //   185: iconst_0
    //   186: istore #22
    //   188: iload #22
    //   190: istore #21
    //   192: iload #25
    //   194: ifeq -> 203
    //   197: iload #22
    //   199: iconst_1
    //   200: iadd
    //   201: istore #21
    //   203: iload #21
    //   205: istore #22
    //   207: iload #23
    //   209: ifeq -> 218
    //   212: iload #21
    //   214: iconst_1
    //   215: iadd
    //   216: istore #22
    //   218: iload #14
    //   220: ifeq -> 229
    //   223: iconst_3
    //   224: istore #21
    //   226: goto -> 233
    //   229: iload #16
    //   231: istore #21
    //   233: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour : [I
    //   236: aload #5
    //   238: invokevirtual ordinal : ()I
    //   241: iaload
    //   242: istore #16
    //   244: iload #16
    //   246: iconst_1
    //   247: if_icmpeq -> 268
    //   250: iload #16
    //   252: iconst_2
    //   253: if_icmpeq -> 268
    //   256: iload #16
    //   258: iconst_3
    //   259: if_icmpeq -> 268
    //   262: iload #16
    //   264: iconst_4
    //   265: if_icmpeq -> 274
    //   268: iconst_0
    //   269: istore #16
    //   271: goto -> 286
    //   274: iload #21
    //   276: iconst_4
    //   277: if_icmpne -> 283
    //   280: goto -> 268
    //   283: iconst_1
    //   284: istore #16
    //   286: aload_0
    //   287: getfield mVisibility : I
    //   290: bipush #8
    //   292: if_icmpne -> 304
    //   295: iconst_0
    //   296: istore #10
    //   298: iconst_0
    //   299: istore #16
    //   301: goto -> 304
    //   304: iload #20
    //   306: ifeq -> 364
    //   309: iload #24
    //   311: ifne -> 335
    //   314: iload #25
    //   316: ifne -> 335
    //   319: iload #23
    //   321: ifne -> 335
    //   324: aload_1
    //   325: aload #28
    //   327: iload #9
    //   329: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;I)V
    //   332: goto -> 364
    //   335: iload #24
    //   337: ifeq -> 364
    //   340: iload #25
    //   342: ifne -> 364
    //   345: aload_1
    //   346: aload #28
    //   348: aload #29
    //   350: aload #7
    //   352: invokevirtual getMargin : ()I
    //   355: bipush #6
    //   357: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   360: pop
    //   361: goto -> 364
    //   364: iload #16
    //   366: ifne -> 448
    //   369: iload #6
    //   371: ifeq -> 424
    //   374: aload_1
    //   375: aload #26
    //   377: aload #28
    //   379: iconst_0
    //   380: iconst_3
    //   381: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   384: pop
    //   385: iload #11
    //   387: ifle -> 402
    //   390: aload_1
    //   391: aload #26
    //   393: aload #28
    //   395: iload #11
    //   397: bipush #6
    //   399: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   402: iload #12
    //   404: ldc 2147483647
    //   406: if_icmpge -> 421
    //   409: aload_1
    //   410: aload #26
    //   412: aload #28
    //   414: iload #12
    //   416: bipush #6
    //   418: invokevirtual addLowerThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   421: goto -> 437
    //   424: aload_1
    //   425: aload #26
    //   427: aload #28
    //   429: iload #10
    //   431: bipush #6
    //   433: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   436: pop
    //   437: iload #18
    //   439: istore #12
    //   441: iload #17
    //   443: istore #9
    //   445: goto -> 812
    //   448: iload #17
    //   450: bipush #-2
    //   452: if_icmpne -> 462
    //   455: iload #10
    //   457: istore #9
    //   459: goto -> 466
    //   462: iload #17
    //   464: istore #9
    //   466: iload #18
    //   468: istore #12
    //   470: iload #18
    //   472: bipush #-2
    //   474: if_icmpne -> 481
    //   477: iload #10
    //   479: istore #12
    //   481: iload #10
    //   483: istore #17
    //   485: iload #9
    //   487: ifle -> 511
    //   490: aload_1
    //   491: aload #26
    //   493: aload #28
    //   495: iload #9
    //   497: bipush #6
    //   499: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   502: iload #10
    //   504: iload #9
    //   506: invokestatic max : (II)I
    //   509: istore #17
    //   511: iload #17
    //   513: istore #18
    //   515: iload #12
    //   517: ifle -> 541
    //   520: aload_1
    //   521: aload #26
    //   523: aload #28
    //   525: iload #12
    //   527: bipush #6
    //   529: invokevirtual addLowerThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   532: iload #17
    //   534: iload #12
    //   536: invokestatic min : (II)I
    //   539: istore #18
    //   541: iload #21
    //   543: iconst_1
    //   544: if_icmpne -> 602
    //   547: iload_2
    //   548: ifeq -> 567
    //   551: aload_1
    //   552: aload #26
    //   554: aload #28
    //   556: iload #18
    //   558: bipush #6
    //   560: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   563: pop
    //   564: goto -> 742
    //   567: iload #15
    //   569: ifeq -> 587
    //   572: aload_1
    //   573: aload #26
    //   575: aload #28
    //   577: iload #18
    //   579: iconst_4
    //   580: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   583: pop
    //   584: goto -> 742
    //   587: aload_1
    //   588: aload #26
    //   590: aload #28
    //   592: iload #18
    //   594: iconst_1
    //   595: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   598: pop
    //   599: goto -> 742
    //   602: iload #21
    //   604: iconst_2
    //   605: if_icmpne -> 742
    //   608: aload #7
    //   610: invokevirtual getType : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   613: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   616: if_acmpeq -> 672
    //   619: aload #7
    //   621: invokevirtual getType : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   624: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   627: if_acmpne -> 633
    //   630: goto -> 672
    //   633: aload_1
    //   634: aload_0
    //   635: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   638: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.LEFT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   641: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   644: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   647: astore #5
    //   649: aload_0
    //   650: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   653: astore #27
    //   655: aload_1
    //   656: aload #27
    //   658: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   661: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   664: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   667: astore #27
    //   669: goto -> 708
    //   672: aload_1
    //   673: aload_0
    //   674: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   677: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   680: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   683: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   686: astore #5
    //   688: aload_0
    //   689: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   692: astore #27
    //   694: aload_1
    //   695: aload #27
    //   697: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   700: invokevirtual getAnchor : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;)Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   703: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   706: astore #27
    //   708: aload_1
    //   709: invokevirtual createRow : ()Landroidx/constraintlayout/solver/ArrayRow;
    //   712: astore #31
    //   714: aload #31
    //   716: aload #26
    //   718: aload #28
    //   720: aload #27
    //   722: aload #5
    //   724: fload #19
    //   726: invokevirtual createRowDimensionRatio : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;F)Landroidx/constraintlayout/solver/ArrayRow;
    //   729: pop
    //   730: aload_1
    //   731: aload #31
    //   733: invokevirtual addConstraint : (Landroidx/constraintlayout/solver/ArrayRow;)V
    //   736: iconst_0
    //   737: istore #10
    //   739: goto -> 746
    //   742: iload #16
    //   744: istore #10
    //   746: iload #10
    //   748: ifeq -> 808
    //   751: iload #22
    //   753: iconst_2
    //   754: if_icmpeq -> 808
    //   757: iload #14
    //   759: ifne -> 808
    //   762: iload #9
    //   764: iload #18
    //   766: invokestatic max : (II)I
    //   769: istore #16
    //   771: iload #16
    //   773: istore #10
    //   775: iload #12
    //   777: ifle -> 789
    //   780: iload #12
    //   782: iload #16
    //   784: invokestatic min : (II)I
    //   787: istore #10
    //   789: aload_1
    //   790: aload #26
    //   792: aload #28
    //   794: iload #10
    //   796: bipush #6
    //   798: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   801: pop
    //   802: iconst_0
    //   803: istore #16
    //   805: goto -> 812
    //   808: iload #10
    //   810: istore #16
    //   812: iload #20
    //   814: ifeq -> 1420
    //   817: iload #15
    //   819: ifeq -> 825
    //   822: goto -> 1420
    //   825: iload #24
    //   827: ifne -> 857
    //   830: iload #25
    //   832: ifne -> 857
    //   835: iload #23
    //   837: ifne -> 857
    //   840: iload_2
    //   841: ifeq -> 1398
    //   844: aload_1
    //   845: aload #4
    //   847: aload #26
    //   849: iconst_0
    //   850: iconst_5
    //   851: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   854: goto -> 1398
    //   857: iload #24
    //   859: ifeq -> 884
    //   862: iload #25
    //   864: ifne -> 884
    //   867: iload_2
    //   868: ifeq -> 1398
    //   871: aload_1
    //   872: aload #4
    //   874: aload #26
    //   876: iconst_0
    //   877: iconst_5
    //   878: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   881: goto -> 1398
    //   884: iload #24
    //   886: ifne -> 927
    //   889: iload #25
    //   891: ifeq -> 927
    //   894: aload_1
    //   895: aload #26
    //   897: aload #30
    //   899: aload #8
    //   901: invokevirtual getMargin : ()I
    //   904: ineg
    //   905: bipush #6
    //   907: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   910: pop
    //   911: iload_2
    //   912: ifeq -> 1398
    //   915: aload_1
    //   916: aload #28
    //   918: aload_3
    //   919: iconst_0
    //   920: iconst_5
    //   921: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   924: goto -> 1398
    //   927: iload #24
    //   929: ifeq -> 1398
    //   932: iload #25
    //   934: ifeq -> 1398
    //   937: iload #16
    //   939: ifeq -> 1165
    //   942: iload_2
    //   943: ifeq -> 962
    //   946: iload #11
    //   948: ifne -> 962
    //   951: aload_1
    //   952: aload #26
    //   954: aload #28
    //   956: iconst_0
    //   957: bipush #6
    //   959: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   962: iload #21
    //   964: ifne -> 1061
    //   967: iload #12
    //   969: ifgt -> 990
    //   972: iload #9
    //   974: ifle -> 980
    //   977: goto -> 990
    //   980: bipush #6
    //   982: istore #11
    //   984: iconst_0
    //   985: istore #10
    //   987: goto -> 996
    //   990: iconst_4
    //   991: istore #11
    //   993: iconst_1
    //   994: istore #10
    //   996: aload_1
    //   997: aload #28
    //   999: aload #29
    //   1001: aload #7
    //   1003: invokevirtual getMargin : ()I
    //   1006: iload #11
    //   1008: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   1011: pop
    //   1012: aload_1
    //   1013: aload #26
    //   1015: aload #30
    //   1017: aload #8
    //   1019: invokevirtual getMargin : ()I
    //   1022: ineg
    //   1023: iload #11
    //   1025: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   1028: pop
    //   1029: iload #12
    //   1031: ifgt -> 1048
    //   1034: iload #9
    //   1036: ifle -> 1042
    //   1039: goto -> 1048
    //   1042: iconst_0
    //   1043: istore #11
    //   1045: goto -> 1051
    //   1048: iconst_1
    //   1049: istore #11
    //   1051: iload #10
    //   1053: istore #9
    //   1055: iconst_5
    //   1056: istore #10
    //   1058: goto -> 1077
    //   1061: iload #21
    //   1063: iconst_1
    //   1064: if_icmpne -> 1080
    //   1067: iconst_1
    //   1068: istore #11
    //   1070: bipush #6
    //   1072: istore #10
    //   1074: iconst_1
    //   1075: istore #9
    //   1077: goto -> 1174
    //   1080: iload #21
    //   1082: iconst_3
    //   1083: if_icmpne -> 1159
    //   1086: iload #14
    //   1088: ifne -> 1111
    //   1091: aload_0
    //   1092: getfield mResolvedDimensionRatioSide : I
    //   1095: iconst_m1
    //   1096: if_icmpeq -> 1111
    //   1099: iload #12
    //   1101: ifgt -> 1111
    //   1104: bipush #6
    //   1106: istore #9
    //   1108: goto -> 1114
    //   1111: iconst_4
    //   1112: istore #9
    //   1114: aload_1
    //   1115: aload #28
    //   1117: aload #29
    //   1119: aload #7
    //   1121: invokevirtual getMargin : ()I
    //   1124: iload #9
    //   1126: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   1129: pop
    //   1130: aload_1
    //   1131: aload #26
    //   1133: aload #30
    //   1135: aload #8
    //   1137: invokevirtual getMargin : ()I
    //   1140: ineg
    //   1141: iload #9
    //   1143: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   1146: pop
    //   1147: iconst_1
    //   1148: istore #11
    //   1150: iconst_5
    //   1151: istore #10
    //   1153: iconst_1
    //   1154: istore #9
    //   1156: goto -> 1174
    //   1159: iconst_0
    //   1160: istore #11
    //   1162: goto -> 1168
    //   1165: iconst_1
    //   1166: istore #11
    //   1168: iconst_5
    //   1169: istore #10
    //   1171: iconst_0
    //   1172: istore #9
    //   1174: iload #11
    //   1176: ifeq -> 1285
    //   1179: aload #7
    //   1181: invokevirtual getMargin : ()I
    //   1184: istore #11
    //   1186: aload #8
    //   1188: invokevirtual getMargin : ()I
    //   1191: istore #12
    //   1193: iconst_1
    //   1194: istore #6
    //   1196: aload_1
    //   1197: aload #28
    //   1199: aload #29
    //   1201: iload #11
    //   1203: fload #13
    //   1205: aload #30
    //   1207: aload #26
    //   1209: iload #12
    //   1211: iload #10
    //   1213: invokevirtual addCentering : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;IFLandroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   1216: aload #7
    //   1218: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1221: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1224: instanceof androidx/constraintlayout/solver/widgets/Barrier
    //   1227: istore #14
    //   1229: aload #8
    //   1231: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1234: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1237: instanceof androidx/constraintlayout/solver/widgets/Barrier
    //   1240: istore #15
    //   1242: iload #14
    //   1244: ifeq -> 1268
    //   1247: iload #15
    //   1249: ifne -> 1268
    //   1252: iload_2
    //   1253: istore #6
    //   1255: iconst_1
    //   1256: istore #14
    //   1258: iconst_5
    //   1259: istore #11
    //   1261: bipush #6
    //   1263: istore #10
    //   1265: goto -> 1297
    //   1268: iload #14
    //   1270: ifne -> 1285
    //   1273: iload #15
    //   1275: ifeq -> 1285
    //   1278: bipush #6
    //   1280: istore #11
    //   1282: goto -> 1291
    //   1285: iload_2
    //   1286: istore #6
    //   1288: iconst_5
    //   1289: istore #11
    //   1291: iload_2
    //   1292: istore #14
    //   1294: iconst_5
    //   1295: istore #10
    //   1297: iload #9
    //   1299: ifeq -> 1310
    //   1302: bipush #6
    //   1304: istore #11
    //   1306: bipush #6
    //   1308: istore #10
    //   1310: iload #16
    //   1312: ifne -> 1320
    //   1315: iload #6
    //   1317: ifne -> 1325
    //   1320: iload #9
    //   1322: ifeq -> 1340
    //   1325: aload_1
    //   1326: aload #28
    //   1328: aload #29
    //   1330: aload #7
    //   1332: invokevirtual getMargin : ()I
    //   1335: iload #11
    //   1337: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   1340: iload #16
    //   1342: ifne -> 1350
    //   1345: iload #14
    //   1347: ifne -> 1355
    //   1350: iload #9
    //   1352: ifeq -> 1371
    //   1355: aload_1
    //   1356: aload #26
    //   1358: aload #30
    //   1360: aload #8
    //   1362: invokevirtual getMargin : ()I
    //   1365: ineg
    //   1366: iload #10
    //   1368: invokevirtual addLowerThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   1371: iload_2
    //   1372: ifeq -> 1392
    //   1375: aload #26
    //   1377: astore #5
    //   1379: aload_1
    //   1380: aload #28
    //   1382: aload_3
    //   1383: iconst_0
    //   1384: bipush #6
    //   1386: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   1389: goto -> 1404
    //   1392: aload #26
    //   1394: astore_3
    //   1395: goto -> 1401
    //   1398: aload #26
    //   1400: astore_3
    //   1401: aload #26
    //   1403: astore_3
    //   1404: iload_2
    //   1405: ifeq -> 1419
    //   1408: aload_1
    //   1409: aload #4
    //   1411: aload #26
    //   1413: iconst_0
    //   1414: bipush #6
    //   1416: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   1419: return
    //   1420: iload #22
    //   1422: iconst_2
    //   1423: if_icmpge -> 1451
    //   1426: iload_2
    //   1427: ifeq -> 1451
    //   1430: aload_1
    //   1431: aload #28
    //   1433: aload_3
    //   1434: iconst_0
    //   1435: bipush #6
    //   1437: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   1440: aload_1
    //   1441: aload #4
    //   1443: aload #26
    //   1445: iconst_0
    //   1446: bipush #6
    //   1448: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   1451: return
  }
  
  private boolean isChainHead(int paramInt) {
    paramInt *= 2;
    ConstraintAnchor[] arrayOfConstraintAnchor = this.mListAnchors;
    ConstraintAnchor constraintAnchor = (arrayOfConstraintAnchor[paramInt]).mTarget;
    null = true;
    if (constraintAnchor != null && (arrayOfConstraintAnchor[paramInt]).mTarget.mTarget != arrayOfConstraintAnchor[paramInt])
      if ((arrayOfConstraintAnchor[++paramInt]).mTarget != null && (arrayOfConstraintAnchor[paramInt]).mTarget.mTarget == arrayOfConstraintAnchor[paramInt])
        return null;  
    return false;
  }
  
  public void addToSolver(LinearSystem paramLinearSystem) {
    // Byte code:
    //   0: aload_1
    //   1: aload_0
    //   2: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   5: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   8: astore #20
    //   10: aload_1
    //   11: aload_0
    //   12: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   15: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   18: astore #21
    //   20: aload_1
    //   21: aload_0
    //   22: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   25: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   28: astore #22
    //   30: aload_1
    //   31: aload_0
    //   32: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   35: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   38: astore #19
    //   40: aload_1
    //   41: aload_0
    //   42: getfield mBaseline : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   45: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   48: astore #23
    //   50: aload_0
    //   51: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   54: astore #17
    //   56: aload #17
    //   58: ifnull -> 325
    //   61: aload #17
    //   63: ifnull -> 85
    //   66: aload #17
    //   68: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   71: iconst_0
    //   72: aaload
    //   73: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   76: if_acmpne -> 85
    //   79: iconst_1
    //   80: istore #10
    //   82: goto -> 88
    //   85: iconst_0
    //   86: istore #10
    //   88: aload_0
    //   89: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   92: astore #17
    //   94: aload #17
    //   96: ifnull -> 118
    //   99: aload #17
    //   101: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   104: iconst_1
    //   105: aaload
    //   106: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   109: if_acmpne -> 118
    //   112: iconst_1
    //   113: istore #11
    //   115: goto -> 121
    //   118: iconst_0
    //   119: istore #11
    //   121: aload_0
    //   122: iconst_0
    //   123: invokespecial isChainHead : (I)Z
    //   126: ifeq -> 147
    //   129: aload_0
    //   130: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   133: checkcast androidx/constraintlayout/solver/widgets/ConstraintWidgetContainer
    //   136: aload_0
    //   137: iconst_0
    //   138: invokevirtual addChain : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;I)V
    //   141: iconst_1
    //   142: istore #12
    //   144: goto -> 153
    //   147: aload_0
    //   148: invokevirtual isInHorizontalChain : ()Z
    //   151: istore #12
    //   153: aload_0
    //   154: iconst_1
    //   155: invokespecial isChainHead : (I)Z
    //   158: ifeq -> 179
    //   161: aload_0
    //   162: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   165: checkcast androidx/constraintlayout/solver/widgets/ConstraintWidgetContainer
    //   168: aload_0
    //   169: iconst_1
    //   170: invokevirtual addChain : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;I)V
    //   173: iconst_1
    //   174: istore #13
    //   176: goto -> 185
    //   179: aload_0
    //   180: invokevirtual isInVerticalChain : ()Z
    //   183: istore #13
    //   185: iload #10
    //   187: ifeq -> 238
    //   190: aload_0
    //   191: getfield mVisibility : I
    //   194: bipush #8
    //   196: if_icmpeq -> 238
    //   199: aload_0
    //   200: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   203: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   206: ifnonnull -> 238
    //   209: aload_0
    //   210: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   213: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   216: ifnonnull -> 238
    //   219: aload_1
    //   220: aload_1
    //   221: aload_0
    //   222: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   225: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   228: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   231: aload #21
    //   233: iconst_0
    //   234: iconst_1
    //   235: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   238: iload #11
    //   240: ifeq -> 298
    //   243: aload_0
    //   244: getfield mVisibility : I
    //   247: bipush #8
    //   249: if_icmpeq -> 298
    //   252: aload_0
    //   253: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   256: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   259: ifnonnull -> 298
    //   262: aload_0
    //   263: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   266: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   269: ifnonnull -> 298
    //   272: aload_0
    //   273: getfield mBaseline : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   276: ifnonnull -> 298
    //   279: aload_1
    //   280: aload_1
    //   281: aload_0
    //   282: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   285: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   288: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   291: aload #19
    //   293: iconst_0
    //   294: iconst_1
    //   295: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   298: iload #12
    //   300: istore #15
    //   302: iload #13
    //   304: istore #14
    //   306: iload #10
    //   308: istore #12
    //   310: iload #11
    //   312: istore #10
    //   314: iload #15
    //   316: istore #13
    //   318: iload #14
    //   320: istore #11
    //   322: goto -> 337
    //   325: iconst_0
    //   326: istore #12
    //   328: iconst_0
    //   329: istore #10
    //   331: iconst_0
    //   332: istore #13
    //   334: iconst_0
    //   335: istore #11
    //   337: aload_0
    //   338: getfield mWidth : I
    //   341: istore #5
    //   343: aload_0
    //   344: getfield mMinWidth : I
    //   347: istore #4
    //   349: iload #5
    //   351: istore_3
    //   352: iload #5
    //   354: iload #4
    //   356: if_icmpge -> 362
    //   359: iload #4
    //   361: istore_3
    //   362: aload_0
    //   363: getfield mHeight : I
    //   366: istore #5
    //   368: aload_0
    //   369: getfield mMinHeight : I
    //   372: istore #6
    //   374: iload #5
    //   376: istore #4
    //   378: iload #5
    //   380: iload #6
    //   382: if_icmpge -> 389
    //   385: iload #6
    //   387: istore #4
    //   389: aload_0
    //   390: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   393: iconst_0
    //   394: aaload
    //   395: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   398: if_acmpeq -> 407
    //   401: iconst_1
    //   402: istore #14
    //   404: goto -> 410
    //   407: iconst_0
    //   408: istore #14
    //   410: aload_0
    //   411: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   414: iconst_1
    //   415: aaload
    //   416: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   419: if_acmpeq -> 428
    //   422: iconst_1
    //   423: istore #15
    //   425: goto -> 431
    //   428: iconst_0
    //   429: istore #15
    //   431: aload_0
    //   432: aload_0
    //   433: getfield mDimensionRatioSide : I
    //   436: putfield mResolvedDimensionRatioSide : I
    //   439: aload_0
    //   440: getfield mDimensionRatio : F
    //   443: fstore_2
    //   444: aload_0
    //   445: fload_2
    //   446: putfield mResolvedDimensionRatio : F
    //   449: aload_0
    //   450: getfield mMatchConstraintDefaultWidth : I
    //   453: istore #8
    //   455: aload_0
    //   456: getfield mMatchConstraintDefaultHeight : I
    //   459: istore #7
    //   461: fload_2
    //   462: fconst_0
    //   463: fcmpl
    //   464: ifle -> 809
    //   467: aload_0
    //   468: getfield mVisibility : I
    //   471: bipush #8
    //   473: if_icmpeq -> 809
    //   476: iload #8
    //   478: istore #6
    //   480: aload_0
    //   481: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   484: iconst_0
    //   485: aaload
    //   486: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   489: if_acmpne -> 504
    //   492: iload #8
    //   494: istore #6
    //   496: iload #8
    //   498: ifne -> 504
    //   501: iconst_3
    //   502: istore #6
    //   504: iload #7
    //   506: istore #5
    //   508: aload_0
    //   509: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   512: iconst_1
    //   513: aaload
    //   514: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   517: if_acmpne -> 532
    //   520: iload #7
    //   522: istore #5
    //   524: iload #7
    //   526: ifne -> 532
    //   529: iconst_3
    //   530: istore #5
    //   532: aload_0
    //   533: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   536: astore #17
    //   538: aload #17
    //   540: iconst_0
    //   541: aaload
    //   542: astore #18
    //   544: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   547: astore #24
    //   549: aload #18
    //   551: aload #24
    //   553: if_acmpne -> 592
    //   556: aload #17
    //   558: iconst_1
    //   559: aaload
    //   560: aload #24
    //   562: if_acmpne -> 592
    //   565: iload #6
    //   567: iconst_3
    //   568: if_icmpne -> 592
    //   571: iload #5
    //   573: iconst_3
    //   574: if_icmpne -> 592
    //   577: aload_0
    //   578: iload #12
    //   580: iload #10
    //   582: iload #14
    //   584: iload #15
    //   586: invokevirtual setupDimensionRatio : (ZZZZ)V
    //   589: goto -> 778
    //   592: aload_0
    //   593: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   596: astore #24
    //   598: aload #24
    //   600: iconst_0
    //   601: aaload
    //   602: astore #18
    //   604: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   607: astore #17
    //   609: aload #18
    //   611: aload #17
    //   613: if_acmpne -> 673
    //   616: iload #6
    //   618: iconst_3
    //   619: if_icmpne -> 673
    //   622: aload_0
    //   623: iconst_0
    //   624: putfield mResolvedDimensionRatioSide : I
    //   627: aload_0
    //   628: getfield mResolvedDimensionRatio : F
    //   631: aload_0
    //   632: getfield mHeight : I
    //   635: i2f
    //   636: fmul
    //   637: f2i
    //   638: istore #7
    //   640: aload #24
    //   642: iconst_1
    //   643: aaload
    //   644: astore #18
    //   646: aload #18
    //   648: aload #17
    //   650: if_acmpeq -> 670
    //   653: iload #4
    //   655: istore #6
    //   657: iload #5
    //   659: istore #4
    //   661: iconst_4
    //   662: istore_3
    //   663: iload #7
    //   665: istore #5
    //   667: goto -> 823
    //   670: goto -> 781
    //   673: aload_0
    //   674: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   677: iconst_1
    //   678: aaload
    //   679: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   682: if_acmpne -> 778
    //   685: iload #5
    //   687: iconst_3
    //   688: if_icmpne -> 778
    //   691: aload_0
    //   692: iconst_1
    //   693: putfield mResolvedDimensionRatioSide : I
    //   696: aload_0
    //   697: getfield mDimensionRatioSide : I
    //   700: iconst_m1
    //   701: if_icmpne -> 714
    //   704: aload_0
    //   705: fconst_1
    //   706: aload_0
    //   707: getfield mResolvedDimensionRatio : F
    //   710: fdiv
    //   711: putfield mResolvedDimensionRatio : F
    //   714: aload_0
    //   715: getfield mResolvedDimensionRatio : F
    //   718: aload_0
    //   719: getfield mWidth : I
    //   722: i2f
    //   723: fmul
    //   724: f2i
    //   725: istore #7
    //   727: aload_0
    //   728: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   731: iconst_0
    //   732: aaload
    //   733: astore #18
    //   735: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   738: astore #17
    //   740: iload #6
    //   742: istore #9
    //   744: iload_3
    //   745: istore #8
    //   747: iload #8
    //   749: istore #4
    //   751: iload #7
    //   753: istore_3
    //   754: aload #18
    //   756: aload #17
    //   758: if_acmpeq -> 788
    //   761: iconst_4
    //   762: istore #4
    //   764: iload #9
    //   766: istore_3
    //   767: iload #8
    //   769: istore #5
    //   771: iload #7
    //   773: istore #6
    //   775: goto -> 823
    //   778: iload_3
    //   779: istore #7
    //   781: iload #4
    //   783: istore_3
    //   784: iload #7
    //   786: istore #4
    //   788: iconst_1
    //   789: istore #7
    //   791: iload #6
    //   793: istore #8
    //   795: iload #7
    //   797: istore #6
    //   799: iload #4
    //   801: istore #9
    //   803: iload_3
    //   804: istore #7
    //   806: goto -> 845
    //   809: iload #4
    //   811: istore #6
    //   813: iload #7
    //   815: istore #4
    //   817: iload_3
    //   818: istore #5
    //   820: iload #8
    //   822: istore_3
    //   823: iconst_0
    //   824: istore #8
    //   826: iload #6
    //   828: istore #7
    //   830: iload #5
    //   832: istore #9
    //   834: iload #8
    //   836: istore #6
    //   838: iload #4
    //   840: istore #5
    //   842: iload_3
    //   843: istore #8
    //   845: aload_0
    //   846: getfield mResolvedMatchConstraintDefault : [I
    //   849: astore #17
    //   851: aload #17
    //   853: iconst_0
    //   854: iload #8
    //   856: iastore
    //   857: aload #17
    //   859: iconst_1
    //   860: iload #5
    //   862: iastore
    //   863: iload #6
    //   865: ifeq -> 888
    //   868: aload_0
    //   869: getfield mResolvedDimensionRatioSide : I
    //   872: istore_3
    //   873: iload_3
    //   874: ifeq -> 882
    //   877: iload_3
    //   878: iconst_m1
    //   879: if_icmpne -> 888
    //   882: iconst_1
    //   883: istore #14
    //   885: goto -> 891
    //   888: iconst_0
    //   889: istore #14
    //   891: aload_0
    //   892: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   895: iconst_0
    //   896: aaload
    //   897: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   900: if_acmpne -> 916
    //   903: aload_0
    //   904: instanceof androidx/constraintlayout/solver/widgets/ConstraintWidgetContainer
    //   907: ifeq -> 916
    //   910: iconst_1
    //   911: istore #15
    //   913: goto -> 919
    //   916: iconst_0
    //   917: istore #15
    //   919: aload_0
    //   920: getfield mCenter : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   923: invokevirtual isConnected : ()Z
    //   926: iconst_1
    //   927: ixor
    //   928: istore #16
    //   930: aload_0
    //   931: getfield mHorizontalResolution : I
    //   934: iconst_2
    //   935: if_icmpeq -> 1064
    //   938: aload_0
    //   939: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   942: astore #17
    //   944: aload #17
    //   946: ifnull -> 963
    //   949: aload_1
    //   950: aload #17
    //   952: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   955: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   958: astore #17
    //   960: goto -> 966
    //   963: aconst_null
    //   964: astore #17
    //   966: aload_0
    //   967: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   970: astore #18
    //   972: aload #18
    //   974: ifnull -> 991
    //   977: aload_1
    //   978: aload #18
    //   980: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   983: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   986: astore #18
    //   988: goto -> 994
    //   991: aconst_null
    //   992: astore #18
    //   994: aload_0
    //   995: aload_1
    //   996: iload #12
    //   998: aload #18
    //   1000: aload #17
    //   1002: aload_0
    //   1003: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1006: iconst_0
    //   1007: aaload
    //   1008: iload #15
    //   1010: aload_0
    //   1011: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1014: aload_0
    //   1015: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1018: aload_0
    //   1019: getfield mX : I
    //   1022: iload #9
    //   1024: aload_0
    //   1025: getfield mMinWidth : I
    //   1028: aload_0
    //   1029: getfield mMaxDimension : [I
    //   1032: iconst_0
    //   1033: iaload
    //   1034: aload_0
    //   1035: getfield mHorizontalBiasPercent : F
    //   1038: iload #14
    //   1040: iload #13
    //   1042: iload #8
    //   1044: aload_0
    //   1045: getfield mMatchConstraintMinWidth : I
    //   1048: aload_0
    //   1049: getfield mMatchConstraintMaxWidth : I
    //   1052: aload_0
    //   1053: getfield mMatchConstraintPercentWidth : F
    //   1056: iload #16
    //   1058: invokespecial applyConstraints : (Landroidx/constraintlayout/solver/LinearSystem;ZLandroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;ZLandroidx/constraintlayout/solver/widgets/ConstraintAnchor;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;IIIIFZZIIIFZ)V
    //   1061: goto -> 1064
    //   1064: aload_0
    //   1065: getfield mVerticalResolution : I
    //   1068: iconst_2
    //   1069: if_icmpne -> 1073
    //   1072: return
    //   1073: aload_0
    //   1074: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1077: iconst_1
    //   1078: aaload
    //   1079: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1082: if_acmpne -> 1098
    //   1085: aload_0
    //   1086: instanceof androidx/constraintlayout/solver/widgets/ConstraintWidgetContainer
    //   1089: ifeq -> 1098
    //   1092: iconst_1
    //   1093: istore #12
    //   1095: goto -> 1101
    //   1098: iconst_0
    //   1099: istore #12
    //   1101: iload #6
    //   1103: ifeq -> 1127
    //   1106: aload_0
    //   1107: getfield mResolvedDimensionRatioSide : I
    //   1110: istore_3
    //   1111: iload_3
    //   1112: iconst_1
    //   1113: if_icmpeq -> 1121
    //   1116: iload_3
    //   1117: iconst_m1
    //   1118: if_icmpne -> 1127
    //   1121: iconst_1
    //   1122: istore #13
    //   1124: goto -> 1130
    //   1127: iconst_0
    //   1128: istore #13
    //   1130: aload_0
    //   1131: getfield mBaselineDistance : I
    //   1134: ifle -> 1216
    //   1137: aload_0
    //   1138: getfield mBaseline : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1141: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1144: getfield state : I
    //   1147: iconst_1
    //   1148: if_icmpne -> 1165
    //   1151: aload_0
    //   1152: getfield mBaseline : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1155: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1158: aload_1
    //   1159: invokevirtual addResolvedValue : (Landroidx/constraintlayout/solver/LinearSystem;)V
    //   1162: goto -> 1216
    //   1165: aload_1
    //   1166: aload #23
    //   1168: aload #22
    //   1170: aload_0
    //   1171: invokevirtual getBaselineDistance : ()I
    //   1174: bipush #6
    //   1176: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   1179: pop
    //   1180: aload_0
    //   1181: getfield mBaseline : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1184: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1187: astore #17
    //   1189: aload #17
    //   1191: ifnull -> 1216
    //   1194: aload_1
    //   1195: aload #23
    //   1197: aload_1
    //   1198: aload #17
    //   1200: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   1203: iconst_0
    //   1204: bipush #6
    //   1206: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   1209: pop
    //   1210: iconst_0
    //   1211: istore #14
    //   1213: goto -> 1220
    //   1216: iload #16
    //   1218: istore #14
    //   1220: aload_0
    //   1221: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1224: astore #17
    //   1226: aload #17
    //   1228: ifnull -> 1245
    //   1231: aload_1
    //   1232: aload #17
    //   1234: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1237: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   1240: astore #17
    //   1242: goto -> 1248
    //   1245: aconst_null
    //   1246: astore #17
    //   1248: aload_0
    //   1249: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1252: astore #18
    //   1254: aload #18
    //   1256: ifnull -> 1273
    //   1259: aload_1
    //   1260: aload #18
    //   1262: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1265: invokevirtual createObjectVariable : (Ljava/lang/Object;)Landroidx/constraintlayout/solver/SolverVariable;
    //   1268: astore #18
    //   1270: goto -> 1276
    //   1273: aconst_null
    //   1274: astore #18
    //   1276: aload_0
    //   1277: aload_1
    //   1278: iload #10
    //   1280: aload #18
    //   1282: aload #17
    //   1284: aload_0
    //   1285: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1288: iconst_1
    //   1289: aaload
    //   1290: iload #12
    //   1292: aload_0
    //   1293: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1296: aload_0
    //   1297: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1300: aload_0
    //   1301: getfield mY : I
    //   1304: iload #7
    //   1306: aload_0
    //   1307: getfield mMinHeight : I
    //   1310: aload_0
    //   1311: getfield mMaxDimension : [I
    //   1314: iconst_1
    //   1315: iaload
    //   1316: aload_0
    //   1317: getfield mVerticalBiasPercent : F
    //   1320: iload #13
    //   1322: iload #11
    //   1324: iload #5
    //   1326: aload_0
    //   1327: getfield mMatchConstraintMinHeight : I
    //   1330: aload_0
    //   1331: getfield mMatchConstraintMaxHeight : I
    //   1334: aload_0
    //   1335: getfield mMatchConstraintPercentHeight : F
    //   1338: iload #14
    //   1340: invokespecial applyConstraints : (Landroidx/constraintlayout/solver/LinearSystem;ZLandroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;ZLandroidx/constraintlayout/solver/widgets/ConstraintAnchor;Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;IIIIFZZIIIFZ)V
    //   1343: iload #6
    //   1345: ifeq -> 1398
    //   1348: aload_0
    //   1349: getfield mResolvedDimensionRatioSide : I
    //   1352: iconst_1
    //   1353: if_icmpne -> 1377
    //   1356: aload_1
    //   1357: aload #19
    //   1359: aload #22
    //   1361: aload #21
    //   1363: aload #20
    //   1365: aload_0
    //   1366: getfield mResolvedDimensionRatio : F
    //   1369: bipush #6
    //   1371: invokevirtual addRatio : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;FI)V
    //   1374: goto -> 1398
    //   1377: aload_1
    //   1378: aload #21
    //   1380: aload #20
    //   1382: aload #19
    //   1384: aload #22
    //   1386: aload_0
    //   1387: getfield mResolvedDimensionRatio : F
    //   1390: bipush #6
    //   1392: invokevirtual addRatio : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;FI)V
    //   1395: goto -> 1398
    //   1398: aload_0
    //   1399: getfield mCenter : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1402: invokevirtual isConnected : ()Z
    //   1405: ifeq -> 1443
    //   1408: aload_1
    //   1409: aload_0
    //   1410: aload_0
    //   1411: getfield mCenter : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1414: invokevirtual getTarget : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1417: invokevirtual getOwner : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1420: aload_0
    //   1421: getfield mCircleConstraintAngle : F
    //   1424: ldc_w 90.0
    //   1427: fadd
    //   1428: f2d
    //   1429: invokestatic toRadians : (D)D
    //   1432: d2f
    //   1433: aload_0
    //   1434: getfield mCenter : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1437: invokevirtual getMargin : ()I
    //   1440: invokevirtual addCenterPoint : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;FI)V
    //   1443: return
  }
  
  public boolean allowedInBarrier() {
    boolean bool;
    if (this.mVisibility != 8) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void analyze(int paramInt) {
    Optimizer.analyze(paramInt, this);
  }
  
  public void connectCircularConstraint(ConstraintWidget paramConstraintWidget, float paramFloat, int paramInt) {
    ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
    immediateConnect(type, paramConstraintWidget, type, paramInt, 0);
    this.mCircleConstraintAngle = paramFloat;
  }
  
  public void createObjectVariables(LinearSystem paramLinearSystem) {
    paramLinearSystem.createObjectVariable(this.mLeft);
    paramLinearSystem.createObjectVariable(this.mTop);
    paramLinearSystem.createObjectVariable(this.mRight);
    paramLinearSystem.createObjectVariable(this.mBottom);
    if (this.mBaselineDistance > 0)
      paramLinearSystem.createObjectVariable(this.mBaseline); 
  }
  
  public ConstraintAnchor getAnchor(ConstraintAnchor.Type paramType) {
    switch (paramType) {
      default:
        throw new AssertionError(paramType.name());
      case null:
        return null;
      case null:
        return this.mCenterY;
      case null:
        return this.mCenterX;
      case null:
        return this.mCenter;
      case null:
        return this.mBaseline;
      case MATCH_CONSTRAINT:
        return this.mBottom;
      case MATCH_PARENT:
        return this.mRight;
      case WRAP_CONTENT:
        return this.mTop;
      case FIXED:
        break;
    } 
    return this.mLeft;
  }
  
  public ArrayList<ConstraintAnchor> getAnchors() {
    return this.mAnchors;
  }
  
  public int getBaselineDistance() {
    return this.mBaselineDistance;
  }
  
  public float getBiasPercent(int paramInt) {
    return (paramInt == 0) ? this.mHorizontalBiasPercent : ((paramInt == 1) ? this.mVerticalBiasPercent : -1.0F);
  }
  
  public int getBottom() {
    return getY() + this.mHeight;
  }
  
  public Object getCompanionWidget() {
    return this.mCompanionWidget;
  }
  
  public String getDebugName() {
    return this.mDebugName;
  }
  
  public DimensionBehaviour getDimensionBehaviour(int paramInt) {
    return (paramInt == 0) ? getHorizontalDimensionBehaviour() : ((paramInt == 1) ? getVerticalDimensionBehaviour() : null);
  }
  
  public int getDrawX() {
    return this.mDrawX + this.mOffsetX;
  }
  
  public int getDrawY() {
    return this.mDrawY + this.mOffsetY;
  }
  
  public int getHeight() {
    return (this.mVisibility == 8) ? 0 : this.mHeight;
  }
  
  public DimensionBehaviour getHorizontalDimensionBehaviour() {
    return this.mListDimensionBehaviors[0];
  }
  
  public int getLength(int paramInt) {
    return (paramInt == 0) ? getWidth() : ((paramInt == 1) ? getHeight() : 0);
  }
  
  public ConstraintWidget getParent() {
    return this.mParent;
  }
  
  int getRelativePositioning(int paramInt) {
    return (paramInt == 0) ? this.mRelX : ((paramInt == 1) ? this.mRelY : 0);
  }
  
  public ResolutionDimension getResolutionHeight() {
    if (this.mResolutionHeight == null)
      this.mResolutionHeight = new ResolutionDimension(); 
    return this.mResolutionHeight;
  }
  
  public ResolutionDimension getResolutionWidth() {
    if (this.mResolutionWidth == null)
      this.mResolutionWidth = new ResolutionDimension(); 
    return this.mResolutionWidth;
  }
  
  public int getRight() {
    return getX() + this.mWidth;
  }
  
  protected int getRootX() {
    return this.mX + this.mOffsetX;
  }
  
  protected int getRootY() {
    return this.mY + this.mOffsetY;
  }
  
  public DimensionBehaviour getVerticalDimensionBehaviour() {
    return this.mListDimensionBehaviors[1];
  }
  
  public int getVisibility() {
    return this.mVisibility;
  }
  
  public int getWidth() {
    return (this.mVisibility == 8) ? 0 : this.mWidth;
  }
  
  public int getWrapHeight() {
    return this.mWrapHeight;
  }
  
  public int getWrapWidth() {
    return this.mWrapWidth;
  }
  
  public int getX() {
    return this.mX;
  }
  
  public int getY() {
    return this.mY;
  }
  
  public boolean hasBaseline() {
    boolean bool;
    if (this.mBaselineDistance > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void immediateConnect(ConstraintAnchor.Type paramType1, ConstraintWidget paramConstraintWidget, ConstraintAnchor.Type paramType2, int paramInt1, int paramInt2) {
    getAnchor(paramType1).connect(paramConstraintWidget.getAnchor(paramType2), paramInt1, paramInt2, ConstraintAnchor.Strength.STRONG, 0, true);
  }
  
  public boolean isFullyResolved() {
    return ((this.mLeft.getResolutionNode()).state == 1 && (this.mRight.getResolutionNode()).state == 1 && (this.mTop.getResolutionNode()).state == 1 && (this.mBottom.getResolutionNode()).state == 1);
  }
  
  public boolean isInHorizontalChain() {
    ConstraintAnchor constraintAnchor1 = this.mLeft;
    ConstraintAnchor constraintAnchor2 = constraintAnchor1.mTarget;
    if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor1) {
      constraintAnchor1 = this.mRight;
      constraintAnchor2 = constraintAnchor1.mTarget;
      if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor1)
        return false; 
    } 
    return true;
  }
  
  public boolean isInVerticalChain() {
    ConstraintAnchor constraintAnchor1 = this.mTop;
    ConstraintAnchor constraintAnchor2 = constraintAnchor1.mTarget;
    if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor1) {
      constraintAnchor1 = this.mBottom;
      constraintAnchor2 = constraintAnchor1.mTarget;
      if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor1)
        return false; 
    } 
    return true;
  }
  
  public boolean isSpreadHeight() {
    int i = this.mMatchConstraintDefaultHeight;
    boolean bool = true;
    if (i != 0 || this.mDimensionRatio != 0.0F || this.mMatchConstraintMinHeight != 0 || this.mMatchConstraintMaxHeight != 0 || this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT)
      bool = false; 
    return bool;
  }
  
  public boolean isSpreadWidth() {
    int i = this.mMatchConstraintDefaultWidth;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (i == 0) {
      bool1 = bool2;
      if (this.mDimensionRatio == 0.0F) {
        bool1 = bool2;
        if (this.mMatchConstraintMinWidth == 0) {
          bool1 = bool2;
          if (this.mMatchConstraintMaxWidth == 0) {
            bool1 = bool2;
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT)
              bool1 = true; 
          } 
        } 
      } 
    } 
    return bool1;
  }
  
  public void reset() {
    this.mLeft.reset();
    this.mTop.reset();
    this.mRight.reset();
    this.mBottom.reset();
    this.mBaseline.reset();
    this.mCenterX.reset();
    this.mCenterY.reset();
    this.mCenter.reset();
    this.mParent = null;
    this.mCircleConstraintAngle = 0.0F;
    this.mWidth = 0;
    this.mHeight = 0;
    this.mDimensionRatio = 0.0F;
    this.mDimensionRatioSide = -1;
    this.mX = 0;
    this.mY = 0;
    this.mDrawX = 0;
    this.mDrawY = 0;
    this.mOffsetX = 0;
    this.mOffsetY = 0;
    this.mBaselineDistance = 0;
    this.mMinWidth = 0;
    this.mMinHeight = 0;
    this.mWrapWidth = 0;
    this.mWrapHeight = 0;
    float f = DEFAULT_BIAS;
    this.mHorizontalBiasPercent = f;
    this.mVerticalBiasPercent = f;
    DimensionBehaviour[] arrayOfDimensionBehaviour = this.mListDimensionBehaviors;
    DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
    arrayOfDimensionBehaviour[0] = dimensionBehaviour;
    arrayOfDimensionBehaviour[1] = dimensionBehaviour;
    this.mCompanionWidget = null;
    this.mVisibility = 0;
    this.mType = null;
    this.mHorizontalChainStyle = 0;
    this.mVerticalChainStyle = 0;
    float[] arrayOfFloat = this.mWeight;
    arrayOfFloat[0] = -1.0F;
    arrayOfFloat[1] = -1.0F;
    this.mHorizontalResolution = -1;
    this.mVerticalResolution = -1;
    int[] arrayOfInt = this.mMaxDimension;
    arrayOfInt[0] = Integer.MAX_VALUE;
    arrayOfInt[1] = Integer.MAX_VALUE;
    this.mMatchConstraintDefaultWidth = 0;
    this.mMatchConstraintDefaultHeight = 0;
    this.mMatchConstraintPercentWidth = 1.0F;
    this.mMatchConstraintPercentHeight = 1.0F;
    this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
    this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
    this.mMatchConstraintMinWidth = 0;
    this.mMatchConstraintMinHeight = 0;
    this.mResolvedDimensionRatioSide = -1;
    this.mResolvedDimensionRatio = 1.0F;
    ResolutionDimension resolutionDimension = this.mResolutionWidth;
    if (resolutionDimension != null)
      resolutionDimension.reset(); 
    resolutionDimension = this.mResolutionHeight;
    if (resolutionDimension != null)
      resolutionDimension.reset(); 
    this.mBelongingGroup = null;
    this.mOptimizerMeasurable = false;
    this.mOptimizerMeasured = false;
    this.mGroupsToSolver = false;
  }
  
  public void resetAnchors() {
    ConstraintWidget constraintWidget = getParent();
    if (constraintWidget != null && constraintWidget instanceof ConstraintWidgetContainer && ((ConstraintWidgetContainer)getParent()).handlesInternalConstraints())
      return; 
    byte b = 0;
    int i = this.mAnchors.size();
    while (b < i) {
      ((ConstraintAnchor)this.mAnchors.get(b)).reset();
      b++;
    } 
  }
  
  public void resetResolutionNodes() {
    for (byte b = 0; b < 6; b++)
      this.mListAnchors[b].getResolutionNode().reset(); 
  }
  
  public void resetSolverVariables(Cache paramCache) {
    this.mLeft.resetSolverVariable(paramCache);
    this.mTop.resetSolverVariable(paramCache);
    this.mRight.resetSolverVariable(paramCache);
    this.mBottom.resetSolverVariable(paramCache);
    this.mBaseline.resetSolverVariable(paramCache);
    this.mCenter.resetSolverVariable(paramCache);
    this.mCenterX.resetSolverVariable(paramCache);
    this.mCenterY.resetSolverVariable(paramCache);
  }
  
  public void resolve() {}
  
  public void setBaselineDistance(int paramInt) {
    this.mBaselineDistance = paramInt;
  }
  
  public void setCompanionWidget(Object paramObject) {
    this.mCompanionWidget = paramObject;
  }
  
  public void setDebugName(String paramString) {
    this.mDebugName = paramString;
  }
  
  public void setDimensionRatio(String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 261
    //   4: aload_1
    //   5: invokevirtual length : ()I
    //   8: ifne -> 14
    //   11: goto -> 261
    //   14: iconst_m1
    //   15: istore #6
    //   17: aload_1
    //   18: invokevirtual length : ()I
    //   21: istore #8
    //   23: aload_1
    //   24: bipush #44
    //   26: invokevirtual indexOf : (I)I
    //   29: istore #9
    //   31: iconst_0
    //   32: istore #7
    //   34: iload #6
    //   36: istore #4
    //   38: iload #7
    //   40: istore #5
    //   42: iload #9
    //   44: ifle -> 114
    //   47: iload #6
    //   49: istore #4
    //   51: iload #7
    //   53: istore #5
    //   55: iload #9
    //   57: iload #8
    //   59: iconst_1
    //   60: isub
    //   61: if_icmpge -> 114
    //   64: aload_1
    //   65: iconst_0
    //   66: iload #9
    //   68: invokevirtual substring : (II)Ljava/lang/String;
    //   71: astore #10
    //   73: aload #10
    //   75: ldc_w 'W'
    //   78: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   81: ifeq -> 90
    //   84: iconst_0
    //   85: istore #4
    //   87: goto -> 108
    //   90: iload #6
    //   92: istore #4
    //   94: aload #10
    //   96: ldc_w 'H'
    //   99: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   102: ifeq -> 108
    //   105: iconst_1
    //   106: istore #4
    //   108: iload #9
    //   110: iconst_1
    //   111: iadd
    //   112: istore #5
    //   114: aload_1
    //   115: bipush #58
    //   117: invokevirtual indexOf : (I)I
    //   120: istore #6
    //   122: iload #6
    //   124: iflt -> 219
    //   127: iload #6
    //   129: iload #8
    //   131: iconst_1
    //   132: isub
    //   133: if_icmpge -> 219
    //   136: aload_1
    //   137: iload #5
    //   139: iload #6
    //   141: invokevirtual substring : (II)Ljava/lang/String;
    //   144: astore #10
    //   146: aload_1
    //   147: iload #6
    //   149: iconst_1
    //   150: iadd
    //   151: invokevirtual substring : (I)Ljava/lang/String;
    //   154: astore_1
    //   155: aload #10
    //   157: invokevirtual length : ()I
    //   160: ifle -> 241
    //   163: aload_1
    //   164: invokevirtual length : ()I
    //   167: ifle -> 241
    //   170: aload #10
    //   172: invokestatic parseFloat : (Ljava/lang/String;)F
    //   175: fstore_3
    //   176: aload_1
    //   177: invokestatic parseFloat : (Ljava/lang/String;)F
    //   180: fstore_2
    //   181: fload_3
    //   182: fconst_0
    //   183: fcmpl
    //   184: ifle -> 241
    //   187: fload_2
    //   188: fconst_0
    //   189: fcmpl
    //   190: ifle -> 241
    //   193: iload #4
    //   195: iconst_1
    //   196: if_icmpne -> 209
    //   199: fload_2
    //   200: fload_3
    //   201: fdiv
    //   202: invokestatic abs : (F)F
    //   205: fstore_2
    //   206: goto -> 243
    //   209: fload_3
    //   210: fload_2
    //   211: fdiv
    //   212: invokestatic abs : (F)F
    //   215: fstore_2
    //   216: goto -> 243
    //   219: aload_1
    //   220: iload #5
    //   222: invokevirtual substring : (I)Ljava/lang/String;
    //   225: astore_1
    //   226: aload_1
    //   227: invokevirtual length : ()I
    //   230: ifle -> 241
    //   233: aload_1
    //   234: invokestatic parseFloat : (Ljava/lang/String;)F
    //   237: fstore_2
    //   238: goto -> 243
    //   241: fconst_0
    //   242: fstore_2
    //   243: fload_2
    //   244: fconst_0
    //   245: fcmpl
    //   246: ifle -> 260
    //   249: aload_0
    //   250: fload_2
    //   251: putfield mDimensionRatio : F
    //   254: aload_0
    //   255: iload #4
    //   257: putfield mDimensionRatioSide : I
    //   260: return
    //   261: aload_0
    //   262: fconst_0
    //   263: putfield mDimensionRatio : F
    //   266: return
    //   267: astore_1
    //   268: goto -> 241
    // Exception table:
    //   from	to	target	type
    //   170	181	267	java/lang/NumberFormatException
    //   199	206	267	java/lang/NumberFormatException
    //   209	216	267	java/lang/NumberFormatException
    //   233	238	267	java/lang/NumberFormatException
  }
  
  public void setFrame(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 == 0) {
      setHorizontalDimension(paramInt1, paramInt2);
    } else if (paramInt3 == 1) {
      setVerticalDimension(paramInt1, paramInt2);
    } 
    this.mOptimizerMeasured = true;
  }
  
  public void setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: iload_3
    //   1: iload_1
    //   2: isub
    //   3: istore #5
    //   5: iload #4
    //   7: iload_2
    //   8: isub
    //   9: istore_3
    //   10: aload_0
    //   11: iload_1
    //   12: putfield mX : I
    //   15: aload_0
    //   16: iload_2
    //   17: putfield mY : I
    //   20: aload_0
    //   21: getfield mVisibility : I
    //   24: bipush #8
    //   26: if_icmpne -> 40
    //   29: aload_0
    //   30: iconst_0
    //   31: putfield mWidth : I
    //   34: aload_0
    //   35: iconst_0
    //   36: putfield mHeight : I
    //   39: return
    //   40: aload_0
    //   41: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   44: iconst_0
    //   45: aaload
    //   46: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.FIXED : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   49: if_acmpne -> 66
    //   52: aload_0
    //   53: getfield mWidth : I
    //   56: istore_1
    //   57: iload #5
    //   59: iload_1
    //   60: if_icmpge -> 66
    //   63: goto -> 69
    //   66: iload #5
    //   68: istore_1
    //   69: aload_0
    //   70: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   73: iconst_1
    //   74: aaload
    //   75: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.FIXED : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   78: if_acmpne -> 94
    //   81: aload_0
    //   82: getfield mHeight : I
    //   85: istore_2
    //   86: iload_3
    //   87: iload_2
    //   88: if_icmpge -> 94
    //   91: goto -> 96
    //   94: iload_3
    //   95: istore_2
    //   96: aload_0
    //   97: iload_1
    //   98: putfield mWidth : I
    //   101: aload_0
    //   102: iload_2
    //   103: putfield mHeight : I
    //   106: aload_0
    //   107: getfield mHeight : I
    //   110: istore_2
    //   111: aload_0
    //   112: getfield mMinHeight : I
    //   115: istore_1
    //   116: iload_2
    //   117: iload_1
    //   118: if_icmpge -> 126
    //   121: aload_0
    //   122: iload_1
    //   123: putfield mHeight : I
    //   126: aload_0
    //   127: getfield mWidth : I
    //   130: istore_1
    //   131: aload_0
    //   132: getfield mMinWidth : I
    //   135: istore_2
    //   136: iload_1
    //   137: iload_2
    //   138: if_icmpge -> 146
    //   141: aload_0
    //   142: iload_2
    //   143: putfield mWidth : I
    //   146: aload_0
    //   147: iconst_1
    //   148: putfield mOptimizerMeasured : Z
    //   151: return
  }
  
  public void setHeight(int paramInt) {
    this.mHeight = paramInt;
    paramInt = this.mHeight;
    int i = this.mMinHeight;
    if (paramInt < i)
      this.mHeight = i; 
  }
  
  public void setHeightWrapContent(boolean paramBoolean) {}
  
  public void setHorizontalBiasPercent(float paramFloat) {
    this.mHorizontalBiasPercent = paramFloat;
  }
  
  public void setHorizontalChainStyle(int paramInt) {
    this.mHorizontalChainStyle = paramInt;
  }
  
  public void setHorizontalDimension(int paramInt1, int paramInt2) {
    this.mX = paramInt1;
    this.mWidth = paramInt2 - paramInt1;
    paramInt2 = this.mWidth;
    paramInt1 = this.mMinWidth;
    if (paramInt2 < paramInt1)
      this.mWidth = paramInt1; 
  }
  
  public void setHorizontalDimensionBehaviour(DimensionBehaviour paramDimensionBehaviour) {
    this.mListDimensionBehaviors[0] = paramDimensionBehaviour;
    if (paramDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT)
      setWidth(this.mWrapWidth); 
  }
  
  public void setHorizontalMatchStyle(int paramInt1, int paramInt2, int paramInt3, float paramFloat) {
    this.mMatchConstraintDefaultWidth = paramInt1;
    this.mMatchConstraintMinWidth = paramInt2;
    this.mMatchConstraintMaxWidth = paramInt3;
    this.mMatchConstraintPercentWidth = paramFloat;
    if (paramFloat < 1.0F && this.mMatchConstraintDefaultWidth == 0)
      this.mMatchConstraintDefaultWidth = 2; 
  }
  
  public void setHorizontalWeight(float paramFloat) {
    this.mWeight[0] = paramFloat;
  }
  
  public void setMaxHeight(int paramInt) {
    this.mMaxDimension[1] = paramInt;
  }
  
  public void setMaxWidth(int paramInt) {
    this.mMaxDimension[0] = paramInt;
  }
  
  public void setMinHeight(int paramInt) {
    if (paramInt < 0) {
      this.mMinHeight = 0;
    } else {
      this.mMinHeight = paramInt;
    } 
  }
  
  public void setMinWidth(int paramInt) {
    if (paramInt < 0) {
      this.mMinWidth = 0;
    } else {
      this.mMinWidth = paramInt;
    } 
  }
  
  public void setOffset(int paramInt1, int paramInt2) {
    this.mOffsetX = paramInt1;
    this.mOffsetY = paramInt2;
  }
  
  public void setOrigin(int paramInt1, int paramInt2) {
    this.mX = paramInt1;
    this.mY = paramInt2;
  }
  
  public void setParent(ConstraintWidget paramConstraintWidget) {
    this.mParent = paramConstraintWidget;
  }
  
  void setRelativePositioning(int paramInt1, int paramInt2) {
    if (paramInt2 == 0) {
      this.mRelX = paramInt1;
    } else if (paramInt2 == 1) {
      this.mRelY = paramInt1;
    } 
  }
  
  public void setVerticalBiasPercent(float paramFloat) {
    this.mVerticalBiasPercent = paramFloat;
  }
  
  public void setVerticalChainStyle(int paramInt) {
    this.mVerticalChainStyle = paramInt;
  }
  
  public void setVerticalDimension(int paramInt1, int paramInt2) {
    this.mY = paramInt1;
    this.mHeight = paramInt2 - paramInt1;
    paramInt1 = this.mHeight;
    paramInt2 = this.mMinHeight;
    if (paramInt1 < paramInt2)
      this.mHeight = paramInt2; 
  }
  
  public void setVerticalDimensionBehaviour(DimensionBehaviour paramDimensionBehaviour) {
    this.mListDimensionBehaviors[1] = paramDimensionBehaviour;
    if (paramDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT)
      setHeight(this.mWrapHeight); 
  }
  
  public void setVerticalMatchStyle(int paramInt1, int paramInt2, int paramInt3, float paramFloat) {
    this.mMatchConstraintDefaultHeight = paramInt1;
    this.mMatchConstraintMinHeight = paramInt2;
    this.mMatchConstraintMaxHeight = paramInt3;
    this.mMatchConstraintPercentHeight = paramFloat;
    if (paramFloat < 1.0F && this.mMatchConstraintDefaultHeight == 0)
      this.mMatchConstraintDefaultHeight = 2; 
  }
  
  public void setVerticalWeight(float paramFloat) {
    this.mWeight[1] = paramFloat;
  }
  
  public void setVisibility(int paramInt) {
    this.mVisibility = paramInt;
  }
  
  public void setWidth(int paramInt) {
    this.mWidth = paramInt;
    paramInt = this.mWidth;
    int i = this.mMinWidth;
    if (paramInt < i)
      this.mWidth = i; 
  }
  
  public void setWidthWrapContent(boolean paramBoolean) {}
  
  public void setWrapHeight(int paramInt) {
    this.mWrapHeight = paramInt;
  }
  
  public void setWrapWidth(int paramInt) {
    this.mWrapWidth = paramInt;
  }
  
  public void setX(int paramInt) {
    this.mX = paramInt;
  }
  
  public void setY(int paramInt) {
    this.mY = paramInt;
  }
  
  public void setupDimensionRatio(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    if (this.mResolvedDimensionRatioSide == -1)
      if (paramBoolean3 && !paramBoolean4) {
        this.mResolvedDimensionRatioSide = 0;
      } else if (!paramBoolean3 && paramBoolean4) {
        this.mResolvedDimensionRatioSide = 1;
        if (this.mDimensionRatioSide == -1)
          this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio; 
      }  
    if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
      this.mResolvedDimensionRatioSide = 1;
    } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
      this.mResolvedDimensionRatioSide = 0;
    } 
    if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected()))
      if (this.mTop.isConnected() && this.mBottom.isConnected()) {
        this.mResolvedDimensionRatioSide = 0;
      } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
        this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
        this.mResolvedDimensionRatioSide = 1;
      }  
    if (this.mResolvedDimensionRatioSide == -1)
      if (paramBoolean1 && !paramBoolean2) {
        this.mResolvedDimensionRatioSide = 0;
      } else if (!paramBoolean1 && paramBoolean2) {
        this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
        this.mResolvedDimensionRatioSide = 1;
      }  
    if (this.mResolvedDimensionRatioSide == -1)
      if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
        this.mResolvedDimensionRatioSide = 0;
      } else if (this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMinHeight > 0) {
        this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
        this.mResolvedDimensionRatioSide = 1;
      }  
    if (this.mResolvedDimensionRatioSide == -1 && paramBoolean1 && paramBoolean2) {
      this.mResolvedDimensionRatio = 1.0F / this.mResolvedDimensionRatio;
      this.mResolvedDimensionRatioSide = 1;
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    String str1 = this.mType;
    String str2 = "";
    if (str1 != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("type: ");
      stringBuilder1.append(this.mType);
      stringBuilder1.append(" ");
      String str = stringBuilder1.toString();
    } else {
      str1 = "";
    } 
    stringBuilder.append(str1);
    str1 = str2;
    if (this.mDebugName != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("id: ");
      stringBuilder1.append(this.mDebugName);
      stringBuilder1.append(" ");
      str1 = stringBuilder1.toString();
    } 
    stringBuilder.append(str1);
    stringBuilder.append("(");
    stringBuilder.append(this.mX);
    stringBuilder.append(", ");
    stringBuilder.append(this.mY);
    stringBuilder.append(") - (");
    stringBuilder.append(this.mWidth);
    stringBuilder.append(" x ");
    stringBuilder.append(this.mHeight);
    stringBuilder.append(") wrap: (");
    stringBuilder.append(this.mWrapWidth);
    stringBuilder.append(" x ");
    stringBuilder.append(this.mWrapHeight);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void updateDrawPosition() {
    int j = this.mX;
    int i = this.mY;
    this.mDrawX = j;
    this.mDrawY = i;
  }
  
  public void updateFromSolver(LinearSystem paramLinearSystem) {
    // Byte code:
    //   0: aload_1
    //   1: aload_0
    //   2: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   5: invokevirtual getObjectVariableValue : (Ljava/lang/Object;)I
    //   8: istore_3
    //   9: aload_1
    //   10: aload_0
    //   11: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   14: invokevirtual getObjectVariableValue : (Ljava/lang/Object;)I
    //   17: istore #5
    //   19: aload_1
    //   20: aload_0
    //   21: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   24: invokevirtual getObjectVariableValue : (Ljava/lang/Object;)I
    //   27: istore #4
    //   29: aload_1
    //   30: aload_0
    //   31: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   34: invokevirtual getObjectVariableValue : (Ljava/lang/Object;)I
    //   37: istore #6
    //   39: iload #4
    //   41: iload_3
    //   42: isub
    //   43: iflt -> 115
    //   46: iload #6
    //   48: iload #5
    //   50: isub
    //   51: iflt -> 115
    //   54: iload_3
    //   55: ldc_w -2147483648
    //   58: if_icmpeq -> 115
    //   61: iload_3
    //   62: ldc 2147483647
    //   64: if_icmpeq -> 115
    //   67: iload #5
    //   69: ldc_w -2147483648
    //   72: if_icmpeq -> 115
    //   75: iload #5
    //   77: ldc 2147483647
    //   79: if_icmpeq -> 115
    //   82: iload #4
    //   84: ldc_w -2147483648
    //   87: if_icmpeq -> 115
    //   90: iload #4
    //   92: ldc 2147483647
    //   94: if_icmpeq -> 115
    //   97: iload #6
    //   99: ldc_w -2147483648
    //   102: if_icmpeq -> 115
    //   105: iload #6
    //   107: istore_2
    //   108: iload #6
    //   110: ldc 2147483647
    //   112: if_icmpne -> 125
    //   115: iconst_0
    //   116: istore_2
    //   117: iconst_0
    //   118: istore_3
    //   119: iconst_0
    //   120: istore #5
    //   122: iconst_0
    //   123: istore #4
    //   125: aload_0
    //   126: iload_3
    //   127: iload #5
    //   129: iload #4
    //   131: iload_2
    //   132: invokevirtual setFrame : (IIII)V
    //   135: return
  }
  
  public void updateResolutionNodes() {
    for (byte b = 0; b < 6; b++)
      this.mListAnchors[b].getResolutionNode().update(); 
  }
  
  public enum DimensionBehaviour {
    FIXED, MATCH_CONSTRAINT, MATCH_PARENT, WRAP_CONTENT;
    
    private static final DimensionBehaviour[] $VALUES;
    
    static {
      $VALUES = new DimensionBehaviour[] { FIXED, WRAP_CONTENT, MATCH_CONSTRAINT, MATCH_PARENT };
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/ConstraintWidget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */