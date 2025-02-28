package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;

public class Optimizer {
  static boolean[] flags = new boolean[3];
  
  static void analyze(int paramInt, ConstraintWidget paramConstraintWidget) {
    int i;
    paramConstraintWidget.updateResolutionNodes();
    ResolutionAnchor resolutionAnchor4 = paramConstraintWidget.mLeft.getResolutionNode();
    ResolutionAnchor resolutionAnchor1 = paramConstraintWidget.mTop.getResolutionNode();
    ResolutionAnchor resolutionAnchor3 = paramConstraintWidget.mRight.getResolutionNode();
    ResolutionAnchor resolutionAnchor2 = paramConstraintWidget.mBottom.getResolutionNode();
    if ((paramInt & 0x8) == 8) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    if (paramConstraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(paramConstraintWidget, 0)) {
      i = 1;
    } else {
      i = 0;
    } 
    if (resolutionAnchor4.type != 4 && resolutionAnchor3.type != 4)
      if (paramConstraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || (i && paramConstraintWidget.getVisibility() == 8)) {
        if (paramConstraintWidget.mLeft.mTarget == null && paramConstraintWidget.mRight.mTarget == null) {
          resolutionAnchor4.setType(1);
          resolutionAnchor3.setType(1);
          if (paramInt != 0) {
            resolutionAnchor3.dependsOn(resolutionAnchor4, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor3.dependsOn(resolutionAnchor4, paramConstraintWidget.getWidth());
          } 
        } else if (paramConstraintWidget.mLeft.mTarget != null && paramConstraintWidget.mRight.mTarget == null) {
          resolutionAnchor4.setType(1);
          resolutionAnchor3.setType(1);
          if (paramInt != 0) {
            resolutionAnchor3.dependsOn(resolutionAnchor4, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor3.dependsOn(resolutionAnchor4, paramConstraintWidget.getWidth());
          } 
        } else if (paramConstraintWidget.mLeft.mTarget == null && paramConstraintWidget.mRight.mTarget != null) {
          resolutionAnchor4.setType(1);
          resolutionAnchor3.setType(1);
          resolutionAnchor4.dependsOn(resolutionAnchor3, -paramConstraintWidget.getWidth());
          if (paramInt != 0) {
            resolutionAnchor4.dependsOn(resolutionAnchor3, -1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor4.dependsOn(resolutionAnchor3, -paramConstraintWidget.getWidth());
          } 
        } else if (paramConstraintWidget.mLeft.mTarget != null && paramConstraintWidget.mRight.mTarget != null) {
          resolutionAnchor4.setType(2);
          resolutionAnchor3.setType(2);
          if (paramInt != 0) {
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor4);
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor3);
            resolutionAnchor4.setOpposite(resolutionAnchor3, -1, paramConstraintWidget.getResolutionWidth());
            resolutionAnchor3.setOpposite(resolutionAnchor4, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor4.setOpposite(resolutionAnchor3, -paramConstraintWidget.getWidth());
            resolutionAnchor3.setOpposite(resolutionAnchor4, paramConstraintWidget.getWidth());
          } 
        } 
      } else if (i) {
        i = paramConstraintWidget.getWidth();
        resolutionAnchor4.setType(1);
        resolutionAnchor3.setType(1);
        if (paramConstraintWidget.mLeft.mTarget == null && paramConstraintWidget.mRight.mTarget == null) {
          if (paramInt != 0) {
            resolutionAnchor3.dependsOn(resolutionAnchor4, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor3.dependsOn(resolutionAnchor4, i);
          } 
        } else if (paramConstraintWidget.mLeft.mTarget != null && paramConstraintWidget.mRight.mTarget == null) {
          if (paramInt != 0) {
            resolutionAnchor3.dependsOn(resolutionAnchor4, 1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor3.dependsOn(resolutionAnchor4, i);
          } 
        } else if (paramConstraintWidget.mLeft.mTarget == null && paramConstraintWidget.mRight.mTarget != null) {
          if (paramInt != 0) {
            resolutionAnchor4.dependsOn(resolutionAnchor3, -1, paramConstraintWidget.getResolutionWidth());
          } else {
            resolutionAnchor4.dependsOn(resolutionAnchor3, -i);
          } 
        } else if (paramConstraintWidget.mLeft.mTarget != null && paramConstraintWidget.mRight.mTarget != null) {
          if (paramInt != 0) {
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor4);
            paramConstraintWidget.getResolutionWidth().addDependent(resolutionAnchor3);
          } 
          if (paramConstraintWidget.mDimensionRatio == 0.0F) {
            resolutionAnchor4.setType(3);
            resolutionAnchor3.setType(3);
            resolutionAnchor4.setOpposite(resolutionAnchor3, 0.0F);
            resolutionAnchor3.setOpposite(resolutionAnchor4, 0.0F);
          } else {
            resolutionAnchor4.setType(2);
            resolutionAnchor3.setType(2);
            resolutionAnchor4.setOpposite(resolutionAnchor3, -i);
            resolutionAnchor3.setOpposite(resolutionAnchor4, i);
            paramConstraintWidget.setWidth(i);
          } 
        } 
      }  
    if (paramConstraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(paramConstraintWidget, 1)) {
      i = 1;
    } else {
      i = 0;
    } 
    if (resolutionAnchor1.type != 4 && resolutionAnchor2.type != 4) {
      ConstraintAnchor constraintAnchor;
      if (paramConstraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED || (i != 0 && paramConstraintWidget.getVisibility() == 8)) {
        if (paramConstraintWidget.mTop.mTarget == null && paramConstraintWidget.mBottom.mTarget == null) {
          resolutionAnchor1.setType(1);
          resolutionAnchor2.setType(1);
          if (paramInt != 0) {
            resolutionAnchor2.dependsOn(resolutionAnchor1, 1, paramConstraintWidget.getResolutionHeight());
          } else {
            resolutionAnchor2.dependsOn(resolutionAnchor1, paramConstraintWidget.getHeight());
          } 
          constraintAnchor = paramConstraintWidget.mBaseline;
          if (constraintAnchor.mTarget != null) {
            constraintAnchor.getResolutionNode().setType(1);
            resolutionAnchor1.dependsOn(1, paramConstraintWidget.mBaseline.getResolutionNode(), -paramConstraintWidget.mBaselineDistance);
          } 
        } else if (paramConstraintWidget.mTop.mTarget != null && paramConstraintWidget.mBottom.mTarget == null) {
          resolutionAnchor1.setType(1);
          constraintAnchor.setType(1);
          if (paramInt != 0) {
            constraintAnchor.dependsOn(resolutionAnchor1, 1, paramConstraintWidget.getResolutionHeight());
          } else {
            constraintAnchor.dependsOn(resolutionAnchor1, paramConstraintWidget.getHeight());
          } 
          if (paramConstraintWidget.mBaselineDistance > 0)
            paramConstraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionAnchor1, paramConstraintWidget.mBaselineDistance); 
        } else if (paramConstraintWidget.mTop.mTarget == null && paramConstraintWidget.mBottom.mTarget != null) {
          resolutionAnchor1.setType(1);
          constraintAnchor.setType(1);
          if (paramInt != 0) {
            resolutionAnchor1.dependsOn((ResolutionAnchor)constraintAnchor, -1, paramConstraintWidget.getResolutionHeight());
          } else {
            resolutionAnchor1.dependsOn((ResolutionAnchor)constraintAnchor, -paramConstraintWidget.getHeight());
          } 
          if (paramConstraintWidget.mBaselineDistance > 0)
            paramConstraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionAnchor1, paramConstraintWidget.mBaselineDistance); 
        } else if (paramConstraintWidget.mTop.mTarget != null && paramConstraintWidget.mBottom.mTarget != null) {
          resolutionAnchor1.setType(2);
          constraintAnchor.setType(2);
          if (paramInt != 0) {
            resolutionAnchor1.setOpposite((ResolutionAnchor)constraintAnchor, -1, paramConstraintWidget.getResolutionHeight());
            constraintAnchor.setOpposite(resolutionAnchor1, 1, paramConstraintWidget.getResolutionHeight());
            paramConstraintWidget.getResolutionHeight().addDependent(resolutionAnchor1);
            paramConstraintWidget.getResolutionWidth().addDependent((ResolutionNode)constraintAnchor);
          } else {
            resolutionAnchor1.setOpposite((ResolutionAnchor)constraintAnchor, -paramConstraintWidget.getHeight());
            constraintAnchor.setOpposite(resolutionAnchor1, paramConstraintWidget.getHeight());
          } 
          if (paramConstraintWidget.mBaselineDistance > 0)
            paramConstraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionAnchor1, paramConstraintWidget.mBaselineDistance); 
        } 
        return;
      } 
      if (i != 0) {
        i = paramConstraintWidget.getHeight();
        resolutionAnchor1.setType(1);
        constraintAnchor.setType(1);
        if (paramConstraintWidget.mTop.mTarget == null && paramConstraintWidget.mBottom.mTarget == null) {
          if (paramInt != 0) {
            constraintAnchor.dependsOn(resolutionAnchor1, 1, paramConstraintWidget.getResolutionHeight());
          } else {
            constraintAnchor.dependsOn(resolutionAnchor1, i);
          } 
        } else if (paramConstraintWidget.mTop.mTarget != null && paramConstraintWidget.mBottom.mTarget == null) {
          if (paramInt != 0) {
            constraintAnchor.dependsOn(resolutionAnchor1, 1, paramConstraintWidget.getResolutionHeight());
          } else {
            constraintAnchor.dependsOn(resolutionAnchor1, i);
          } 
        } else if (paramConstraintWidget.mTop.mTarget == null && paramConstraintWidget.mBottom.mTarget != null) {
          if (paramInt != 0) {
            resolutionAnchor1.dependsOn((ResolutionAnchor)constraintAnchor, -1, paramConstraintWidget.getResolutionHeight());
          } else {
            resolutionAnchor1.dependsOn((ResolutionAnchor)constraintAnchor, -i);
          } 
        } else if (paramConstraintWidget.mTop.mTarget != null && paramConstraintWidget.mBottom.mTarget != null) {
          if (paramInt != 0) {
            paramConstraintWidget.getResolutionHeight().addDependent(resolutionAnchor1);
            paramConstraintWidget.getResolutionWidth().addDependent((ResolutionNode)constraintAnchor);
          } 
          if (paramConstraintWidget.mDimensionRatio == 0.0F) {
            resolutionAnchor1.setType(3);
            constraintAnchor.setType(3);
            resolutionAnchor1.setOpposite((ResolutionAnchor)constraintAnchor, 0.0F);
            constraintAnchor.setOpposite(resolutionAnchor1, 0.0F);
          } else {
            resolutionAnchor1.setType(2);
            constraintAnchor.setType(2);
            resolutionAnchor1.setOpposite((ResolutionAnchor)constraintAnchor, -i);
            constraintAnchor.setOpposite(resolutionAnchor1, i);
            paramConstraintWidget.setHeight(i);
            if (paramConstraintWidget.mBaselineDistance > 0)
              paramConstraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionAnchor1, paramConstraintWidget.mBaselineDistance); 
          } 
        } 
      } 
    } 
  }
  
  static boolean applyChainOptimized(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt1, int paramInt2, ChainHead paramChainHead) {
    // Byte code:
    //   0: aload #4
    //   2: getfield mFirst : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   5: astore #19
    //   7: aload #4
    //   9: getfield mLast : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   12: astore #20
    //   14: aload #4
    //   16: getfield mFirstVisibleWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   19: astore #21
    //   21: aload #4
    //   23: getfield mLastVisibleWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   26: astore #23
    //   28: aload #4
    //   30: getfield mHead : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   33: astore #22
    //   35: aload #4
    //   37: getfield mTotalWeight : F
    //   40: fstore #11
    //   42: aload #4
    //   44: getfield mFirstMatchConstraintWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   47: astore #24
    //   49: aload #4
    //   51: getfield mLastMatchConstraintWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   54: astore #4
    //   56: aload_0
    //   57: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   60: iload_2
    //   61: aaload
    //   62: astore_0
    //   63: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   66: astore_0
    //   67: iload_2
    //   68: ifne -> 151
    //   71: aload #22
    //   73: getfield mHorizontalChainStyle : I
    //   76: ifne -> 85
    //   79: iconst_1
    //   80: istore #12
    //   82: goto -> 88
    //   85: iconst_0
    //   86: istore #12
    //   88: aload #22
    //   90: getfield mHorizontalChainStyle : I
    //   93: iconst_1
    //   94: if_icmpne -> 103
    //   97: iconst_1
    //   98: istore #13
    //   100: goto -> 106
    //   103: iconst_0
    //   104: istore #13
    //   106: iload #12
    //   108: istore #15
    //   110: iload #13
    //   112: istore #14
    //   114: aload #22
    //   116: getfield mHorizontalChainStyle : I
    //   119: iconst_2
    //   120: if_icmpne -> 141
    //   123: iload #13
    //   125: istore #14
    //   127: iload #12
    //   129: istore #15
    //   131: iconst_1
    //   132: istore #12
    //   134: iload #15
    //   136: istore #13
    //   138: goto -> 214
    //   141: iconst_0
    //   142: istore #12
    //   144: iload #15
    //   146: istore #13
    //   148: goto -> 214
    //   151: aload #22
    //   153: getfield mVerticalChainStyle : I
    //   156: ifne -> 165
    //   159: iconst_1
    //   160: istore #12
    //   162: goto -> 168
    //   165: iconst_0
    //   166: istore #12
    //   168: aload #22
    //   170: getfield mVerticalChainStyle : I
    //   173: iconst_1
    //   174: if_icmpne -> 183
    //   177: iconst_1
    //   178: istore #13
    //   180: goto -> 186
    //   183: iconst_0
    //   184: istore #13
    //   186: iload #12
    //   188: istore #15
    //   190: iload #13
    //   192: istore #14
    //   194: aload #22
    //   196: getfield mVerticalChainStyle : I
    //   199: iconst_2
    //   200: if_icmpne -> 141
    //   203: iload #12
    //   205: istore #15
    //   207: iload #13
    //   209: istore #14
    //   211: goto -> 131
    //   214: aload #19
    //   216: astore #4
    //   218: iconst_0
    //   219: istore #16
    //   221: iconst_0
    //   222: istore #17
    //   224: iconst_0
    //   225: istore #15
    //   227: fconst_0
    //   228: fstore #9
    //   230: fconst_0
    //   231: fstore #7
    //   233: iload #17
    //   235: ifne -> 608
    //   238: iload #15
    //   240: istore #18
    //   242: fload #9
    //   244: fstore #5
    //   246: fload #7
    //   248: fstore #6
    //   250: aload #4
    //   252: invokevirtual getVisibility : ()I
    //   255: bipush #8
    //   257: if_icmpeq -> 381
    //   260: iload #15
    //   262: iconst_1
    //   263: iadd
    //   264: istore #18
    //   266: iload_2
    //   267: ifne -> 280
    //   270: aload #4
    //   272: invokevirtual getWidth : ()I
    //   275: istore #15
    //   277: goto -> 287
    //   280: aload #4
    //   282: invokevirtual getHeight : ()I
    //   285: istore #15
    //   287: fload #9
    //   289: iload #15
    //   291: i2f
    //   292: fadd
    //   293: fstore #5
    //   295: fload #5
    //   297: fstore #6
    //   299: aload #4
    //   301: aload #21
    //   303: if_acmpeq -> 322
    //   306: fload #5
    //   308: aload #4
    //   310: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   313: iload_3
    //   314: aaload
    //   315: invokevirtual getMargin : ()I
    //   318: i2f
    //   319: fadd
    //   320: fstore #6
    //   322: fload #6
    //   324: fstore #5
    //   326: aload #4
    //   328: aload #23
    //   330: if_acmpeq -> 351
    //   333: fload #6
    //   335: aload #4
    //   337: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   340: iload_3
    //   341: iconst_1
    //   342: iadd
    //   343: aaload
    //   344: invokevirtual getMargin : ()I
    //   347: i2f
    //   348: fadd
    //   349: fstore #5
    //   351: fload #7
    //   353: aload #4
    //   355: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   358: iload_3
    //   359: aaload
    //   360: invokevirtual getMargin : ()I
    //   363: i2f
    //   364: fadd
    //   365: aload #4
    //   367: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   370: iload_3
    //   371: iconst_1
    //   372: iadd
    //   373: aaload
    //   374: invokevirtual getMargin : ()I
    //   377: i2f
    //   378: fadd
    //   379: fstore #6
    //   381: aload #4
    //   383: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   386: iload_3
    //   387: aaload
    //   388: astore_0
    //   389: iload #16
    //   391: istore #15
    //   393: aload #4
    //   395: invokevirtual getVisibility : ()I
    //   398: bipush #8
    //   400: if_icmpeq -> 499
    //   403: iload #16
    //   405: istore #15
    //   407: aload #4
    //   409: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   412: iload_2
    //   413: aaload
    //   414: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   417: if_acmpne -> 499
    //   420: iload #16
    //   422: iconst_1
    //   423: iadd
    //   424: istore #15
    //   426: iload_2
    //   427: ifne -> 458
    //   430: aload #4
    //   432: getfield mMatchConstraintDefaultWidth : I
    //   435: ifeq -> 440
    //   438: iconst_0
    //   439: ireturn
    //   440: aload #4
    //   442: getfield mMatchConstraintMinWidth : I
    //   445: ifne -> 456
    //   448: aload #4
    //   450: getfield mMatchConstraintMaxWidth : I
    //   453: ifeq -> 487
    //   456: iconst_0
    //   457: ireturn
    //   458: aload #4
    //   460: getfield mMatchConstraintDefaultHeight : I
    //   463: ifeq -> 468
    //   466: iconst_0
    //   467: ireturn
    //   468: aload #4
    //   470: getfield mMatchConstraintMinHeight : I
    //   473: ifne -> 497
    //   476: aload #4
    //   478: getfield mMatchConstraintMaxHeight : I
    //   481: ifeq -> 487
    //   484: goto -> 497
    //   487: aload #4
    //   489: getfield mDimensionRatio : F
    //   492: fconst_0
    //   493: fcmpl
    //   494: ifeq -> 499
    //   497: iconst_0
    //   498: ireturn
    //   499: aload #4
    //   501: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   504: iload_3
    //   505: iconst_1
    //   506: iadd
    //   507: aaload
    //   508: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   511: astore_0
    //   512: aload_0
    //   513: ifnull -> 558
    //   516: aload_0
    //   517: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   520: astore_0
    //   521: aload_0
    //   522: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   525: astore #22
    //   527: aload #22
    //   529: iload_3
    //   530: aaload
    //   531: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   534: ifnull -> 558
    //   537: aload #22
    //   539: iload_3
    //   540: aaload
    //   541: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   544: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   547: aload #4
    //   549: if_acmpeq -> 555
    //   552: goto -> 558
    //   555: goto -> 560
    //   558: aconst_null
    //   559: astore_0
    //   560: aload_0
    //   561: ifnull -> 586
    //   564: iload #15
    //   566: istore #16
    //   568: aload_0
    //   569: astore #4
    //   571: iload #18
    //   573: istore #15
    //   575: fload #5
    //   577: fstore #9
    //   579: fload #6
    //   581: fstore #7
    //   583: goto -> 233
    //   586: iconst_1
    //   587: istore #17
    //   589: iload #15
    //   591: istore #16
    //   593: iload #18
    //   595: istore #15
    //   597: fload #5
    //   599: fstore #9
    //   601: fload #6
    //   603: fstore #7
    //   605: goto -> 233
    //   608: aload #19
    //   610: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   613: iload_3
    //   614: aaload
    //   615: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   618: astore #22
    //   620: aload #20
    //   622: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   625: astore_0
    //   626: iload_3
    //   627: iconst_1
    //   628: iadd
    //   629: istore #17
    //   631: aload_0
    //   632: iload #17
    //   634: aaload
    //   635: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   638: astore #24
    //   640: aload #22
    //   642: getfield target : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   645: astore_0
    //   646: aload_0
    //   647: ifnull -> 1841
    //   650: aload #24
    //   652: getfield target : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   655: astore #25
    //   657: aload #25
    //   659: ifnonnull -> 665
    //   662: goto -> 1841
    //   665: aload_0
    //   666: getfield state : I
    //   669: iconst_1
    //   670: if_icmpne -> 1839
    //   673: aload #25
    //   675: getfield state : I
    //   678: iconst_1
    //   679: if_icmpeq -> 685
    //   682: goto -> 1839
    //   685: iload #16
    //   687: ifle -> 699
    //   690: iload #16
    //   692: iload #15
    //   694: if_icmpeq -> 699
    //   697: iconst_0
    //   698: ireturn
    //   699: iload #12
    //   701: ifne -> 723
    //   704: iload #13
    //   706: ifne -> 723
    //   709: iload #14
    //   711: ifeq -> 717
    //   714: goto -> 723
    //   717: fconst_0
    //   718: fstore #5
    //   720: goto -> 773
    //   723: aload #21
    //   725: ifnull -> 744
    //   728: aload #21
    //   730: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   733: iload_3
    //   734: aaload
    //   735: invokevirtual getMargin : ()I
    //   738: i2f
    //   739: fstore #6
    //   741: goto -> 747
    //   744: fconst_0
    //   745: fstore #6
    //   747: fload #6
    //   749: fstore #5
    //   751: aload #23
    //   753: ifnull -> 773
    //   756: fload #6
    //   758: aload #23
    //   760: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   763: iload #17
    //   765: aaload
    //   766: invokevirtual getMargin : ()I
    //   769: i2f
    //   770: fadd
    //   771: fstore #5
    //   773: aload #22
    //   775: getfield target : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   778: getfield resolvedOffset : F
    //   781: fstore #8
    //   783: aload #24
    //   785: getfield target : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   788: getfield resolvedOffset : F
    //   791: fstore #6
    //   793: fload #8
    //   795: fload #6
    //   797: fcmpg
    //   798: ifge -> 811
    //   801: fload #6
    //   803: fload #8
    //   805: fsub
    //   806: fstore #6
    //   808: goto -> 818
    //   811: fload #8
    //   813: fload #6
    //   815: fsub
    //   816: fstore #6
    //   818: fload #6
    //   820: fload #9
    //   822: fsub
    //   823: fstore #10
    //   825: iload #16
    //   827: ifle -> 1136
    //   830: iload #16
    //   832: iload #15
    //   834: if_icmpne -> 1136
    //   837: aload #4
    //   839: invokevirtual getParent : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   842: ifnull -> 863
    //   845: aload #4
    //   847: invokevirtual getParent : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   850: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   853: iload_2
    //   854: aaload
    //   855: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   858: if_acmpne -> 863
    //   861: iconst_0
    //   862: ireturn
    //   863: fload #10
    //   865: fload #9
    //   867: fadd
    //   868: fload #7
    //   870: fsub
    //   871: fstore #7
    //   873: fload #8
    //   875: fstore #6
    //   877: aload #19
    //   879: astore_0
    //   880: aload_0
    //   881: ifnull -> 1134
    //   884: getstatic androidx/constraintlayout/solver/LinearSystem.sMetrics : Landroidx/constraintlayout/solver/Metrics;
    //   887: astore #4
    //   889: aload #4
    //   891: ifnull -> 930
    //   894: aload #4
    //   896: aload #4
    //   898: getfield nonresolvedWidgets : J
    //   901: lconst_1
    //   902: lsub
    //   903: putfield nonresolvedWidgets : J
    //   906: aload #4
    //   908: aload #4
    //   910: getfield resolvedWidgets : J
    //   913: lconst_1
    //   914: ladd
    //   915: putfield resolvedWidgets : J
    //   918: aload #4
    //   920: aload #4
    //   922: getfield chainConnectionResolved : J
    //   925: lconst_1
    //   926: ladd
    //   927: putfield chainConnectionResolved : J
    //   930: aload_0
    //   931: getfield mNextChainWidget : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   934: iload_2
    //   935: aaload
    //   936: astore #4
    //   938: aload #4
    //   940: ifnonnull -> 955
    //   943: aload_0
    //   944: aload #20
    //   946: if_acmpne -> 952
    //   949: goto -> 955
    //   952: goto -> 1128
    //   955: fload #7
    //   957: iload #16
    //   959: i2f
    //   960: fdiv
    //   961: fstore #5
    //   963: fload #11
    //   965: fconst_0
    //   966: fcmpl
    //   967: ifle -> 1004
    //   970: aload_0
    //   971: getfield mWeight : [F
    //   974: astore #19
    //   976: aload #19
    //   978: iload_2
    //   979: faload
    //   980: ldc -1.0
    //   982: fcmpl
    //   983: ifne -> 992
    //   986: fconst_0
    //   987: fstore #5
    //   989: goto -> 1004
    //   992: aload #19
    //   994: iload_2
    //   995: faload
    //   996: fload #7
    //   998: fmul
    //   999: fload #11
    //   1001: fdiv
    //   1002: fstore #5
    //   1004: aload_0
    //   1005: invokevirtual getVisibility : ()I
    //   1008: bipush #8
    //   1010: if_icmpne -> 1016
    //   1013: fconst_0
    //   1014: fstore #5
    //   1016: fload #6
    //   1018: aload_0
    //   1019: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1022: iload_3
    //   1023: aaload
    //   1024: invokevirtual getMargin : ()I
    //   1027: i2f
    //   1028: fadd
    //   1029: fstore #6
    //   1031: aload_0
    //   1032: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1035: iload_3
    //   1036: aaload
    //   1037: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1040: aload #22
    //   1042: getfield resolvedTarget : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1045: fload #6
    //   1047: invokevirtual resolve : (Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;F)V
    //   1050: aload_0
    //   1051: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1054: iload #17
    //   1056: aaload
    //   1057: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1060: astore #19
    //   1062: aload #22
    //   1064: getfield resolvedTarget : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1067: astore #21
    //   1069: fload #6
    //   1071: fload #5
    //   1073: fadd
    //   1074: fstore #5
    //   1076: aload #19
    //   1078: aload #21
    //   1080: fload #5
    //   1082: invokevirtual resolve : (Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;F)V
    //   1085: aload_0
    //   1086: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1089: iload_3
    //   1090: aaload
    //   1091: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1094: aload_1
    //   1095: invokevirtual addResolvedValue : (Landroidx/constraintlayout/solver/LinearSystem;)V
    //   1098: aload_0
    //   1099: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1102: iload #17
    //   1104: aaload
    //   1105: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1108: aload_1
    //   1109: invokevirtual addResolvedValue : (Landroidx/constraintlayout/solver/LinearSystem;)V
    //   1112: fload #5
    //   1114: aload_0
    //   1115: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1118: iload #17
    //   1120: aaload
    //   1121: invokevirtual getMargin : ()I
    //   1124: i2f
    //   1125: fadd
    //   1126: fstore #6
    //   1128: aload #4
    //   1130: astore_0
    //   1131: goto -> 880
    //   1134: iconst_1
    //   1135: ireturn
    //   1136: fload #10
    //   1138: fconst_0
    //   1139: fcmpg
    //   1140: ifge -> 1152
    //   1143: iconst_1
    //   1144: istore #12
    //   1146: iconst_0
    //   1147: istore #13
    //   1149: iconst_0
    //   1150: istore #14
    //   1152: iload #12
    //   1154: ifeq -> 1395
    //   1157: aload #19
    //   1159: astore_0
    //   1160: fload #8
    //   1162: fload #10
    //   1164: fload #5
    //   1166: fsub
    //   1167: aload_0
    //   1168: iload_2
    //   1169: invokevirtual getBiasPercent : (I)F
    //   1172: fmul
    //   1173: fadd
    //   1174: fstore #5
    //   1176: aload_0
    //   1177: ifnull -> 1408
    //   1180: getstatic androidx/constraintlayout/solver/LinearSystem.sMetrics : Landroidx/constraintlayout/solver/Metrics;
    //   1183: astore #4
    //   1185: aload #4
    //   1187: ifnull -> 1226
    //   1190: aload #4
    //   1192: aload #4
    //   1194: getfield nonresolvedWidgets : J
    //   1197: lconst_1
    //   1198: lsub
    //   1199: putfield nonresolvedWidgets : J
    //   1202: aload #4
    //   1204: aload #4
    //   1206: getfield resolvedWidgets : J
    //   1209: lconst_1
    //   1210: ladd
    //   1211: putfield resolvedWidgets : J
    //   1214: aload #4
    //   1216: aload #4
    //   1218: getfield chainConnectionResolved : J
    //   1221: lconst_1
    //   1222: ladd
    //   1223: putfield chainConnectionResolved : J
    //   1226: aload_0
    //   1227: getfield mNextChainWidget : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1230: iload_2
    //   1231: aaload
    //   1232: astore #4
    //   1234: aload #4
    //   1236: ifnonnull -> 1249
    //   1239: fload #5
    //   1241: fstore #6
    //   1243: aload_0
    //   1244: aload #20
    //   1246: if_acmpne -> 1385
    //   1249: iload_2
    //   1250: ifne -> 1262
    //   1253: aload_0
    //   1254: invokevirtual getWidth : ()I
    //   1257: istore #12
    //   1259: goto -> 1268
    //   1262: aload_0
    //   1263: invokevirtual getHeight : ()I
    //   1266: istore #12
    //   1268: iload #12
    //   1270: i2f
    //   1271: fstore #6
    //   1273: fload #5
    //   1275: aload_0
    //   1276: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1279: iload_3
    //   1280: aaload
    //   1281: invokevirtual getMargin : ()I
    //   1284: i2f
    //   1285: fadd
    //   1286: fstore #5
    //   1288: aload_0
    //   1289: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1292: iload_3
    //   1293: aaload
    //   1294: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1297: aload #22
    //   1299: getfield resolvedTarget : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1302: fload #5
    //   1304: invokevirtual resolve : (Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;F)V
    //   1307: aload_0
    //   1308: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1311: iload #17
    //   1313: aaload
    //   1314: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1317: astore #21
    //   1319: aload #22
    //   1321: getfield resolvedTarget : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1324: astore #19
    //   1326: fload #5
    //   1328: fload #6
    //   1330: fadd
    //   1331: fstore #5
    //   1333: aload #21
    //   1335: aload #19
    //   1337: fload #5
    //   1339: invokevirtual resolve : (Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;F)V
    //   1342: aload_0
    //   1343: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1346: iload_3
    //   1347: aaload
    //   1348: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1351: aload_1
    //   1352: invokevirtual addResolvedValue : (Landroidx/constraintlayout/solver/LinearSystem;)V
    //   1355: aload_0
    //   1356: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1359: iload #17
    //   1361: aaload
    //   1362: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1365: aload_1
    //   1366: invokevirtual addResolvedValue : (Landroidx/constraintlayout/solver/LinearSystem;)V
    //   1369: fload #5
    //   1371: aload_0
    //   1372: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1375: iload #17
    //   1377: aaload
    //   1378: invokevirtual getMargin : ()I
    //   1381: i2f
    //   1382: fadd
    //   1383: fstore #6
    //   1385: aload #4
    //   1387: astore_0
    //   1388: fload #6
    //   1390: fstore #5
    //   1392: goto -> 1176
    //   1395: iload #13
    //   1397: ifne -> 1411
    //   1400: iload #14
    //   1402: ifeq -> 1408
    //   1405: goto -> 1411
    //   1408: goto -> 1837
    //   1411: iload #13
    //   1413: ifeq -> 1426
    //   1416: fload #10
    //   1418: fload #5
    //   1420: fsub
    //   1421: fstore #6
    //   1423: goto -> 1438
    //   1426: fload #10
    //   1428: fstore #6
    //   1430: iload #14
    //   1432: ifeq -> 1438
    //   1435: goto -> 1416
    //   1438: fload #6
    //   1440: iload #15
    //   1442: iconst_1
    //   1443: iadd
    //   1444: i2f
    //   1445: fdiv
    //   1446: fstore #7
    //   1448: iload #14
    //   1450: ifeq -> 1479
    //   1453: iload #15
    //   1455: iconst_1
    //   1456: if_icmple -> 1469
    //   1459: iload #15
    //   1461: iconst_1
    //   1462: isub
    //   1463: i2f
    //   1464: fstore #5
    //   1466: goto -> 1472
    //   1469: fconst_2
    //   1470: fstore #5
    //   1472: fload #6
    //   1474: fload #5
    //   1476: fdiv
    //   1477: fstore #7
    //   1479: aload #19
    //   1481: invokevirtual getVisibility : ()I
    //   1484: bipush #8
    //   1486: if_icmpeq -> 1499
    //   1489: fload #8
    //   1491: fload #7
    //   1493: fadd
    //   1494: fstore #5
    //   1496: goto -> 1503
    //   1499: fload #8
    //   1501: fstore #5
    //   1503: fload #5
    //   1505: fstore #6
    //   1507: iload #14
    //   1509: ifeq -> 1538
    //   1512: fload #5
    //   1514: fstore #6
    //   1516: iload #15
    //   1518: iconst_1
    //   1519: if_icmple -> 1538
    //   1522: aload #21
    //   1524: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1527: iload_3
    //   1528: aaload
    //   1529: invokevirtual getMargin : ()I
    //   1532: i2f
    //   1533: fload #8
    //   1535: fadd
    //   1536: fstore #6
    //   1538: aload #19
    //   1540: astore_0
    //   1541: fload #6
    //   1543: fstore #5
    //   1545: iload #13
    //   1547: ifeq -> 1581
    //   1550: aload #19
    //   1552: astore_0
    //   1553: fload #6
    //   1555: fstore #5
    //   1557: aload #21
    //   1559: ifnull -> 1581
    //   1562: fload #6
    //   1564: aload #21
    //   1566: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1569: iload_3
    //   1570: aaload
    //   1571: invokevirtual getMargin : ()I
    //   1574: i2f
    //   1575: fadd
    //   1576: fstore #5
    //   1578: aload #19
    //   1580: astore_0
    //   1581: aload_0
    //   1582: ifnull -> 1408
    //   1585: getstatic androidx/constraintlayout/solver/LinearSystem.sMetrics : Landroidx/constraintlayout/solver/Metrics;
    //   1588: astore #4
    //   1590: aload #4
    //   1592: ifnull -> 1631
    //   1595: aload #4
    //   1597: aload #4
    //   1599: getfield nonresolvedWidgets : J
    //   1602: lconst_1
    //   1603: lsub
    //   1604: putfield nonresolvedWidgets : J
    //   1607: aload #4
    //   1609: aload #4
    //   1611: getfield resolvedWidgets : J
    //   1614: lconst_1
    //   1615: ladd
    //   1616: putfield resolvedWidgets : J
    //   1619: aload #4
    //   1621: aload #4
    //   1623: getfield chainConnectionResolved : J
    //   1626: lconst_1
    //   1627: ladd
    //   1628: putfield chainConnectionResolved : J
    //   1631: aload_0
    //   1632: getfield mNextChainWidget : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1635: iload_2
    //   1636: aaload
    //   1637: astore #4
    //   1639: aload #4
    //   1641: ifnonnull -> 1664
    //   1644: fload #5
    //   1646: fstore #6
    //   1648: aload_0
    //   1649: aload #20
    //   1651: if_acmpne -> 1657
    //   1654: goto -> 1664
    //   1657: fload #6
    //   1659: fstore #5
    //   1661: goto -> 1831
    //   1664: iload_2
    //   1665: ifne -> 1677
    //   1668: aload_0
    //   1669: invokevirtual getWidth : ()I
    //   1672: istore #12
    //   1674: goto -> 1683
    //   1677: aload_0
    //   1678: invokevirtual getHeight : ()I
    //   1681: istore #12
    //   1683: iload #12
    //   1685: i2f
    //   1686: fstore #8
    //   1688: fload #5
    //   1690: fstore #6
    //   1692: aload_0
    //   1693: aload #21
    //   1695: if_acmpeq -> 1713
    //   1698: fload #5
    //   1700: aload_0
    //   1701: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1704: iload_3
    //   1705: aaload
    //   1706: invokevirtual getMargin : ()I
    //   1709: i2f
    //   1710: fadd
    //   1711: fstore #6
    //   1713: aload_0
    //   1714: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1717: iload_3
    //   1718: aaload
    //   1719: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1722: aload #22
    //   1724: getfield resolvedTarget : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1727: fload #6
    //   1729: invokevirtual resolve : (Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;F)V
    //   1732: aload_0
    //   1733: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1736: iload #17
    //   1738: aaload
    //   1739: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1742: aload #22
    //   1744: getfield resolvedTarget : Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1747: fload #6
    //   1749: fload #8
    //   1751: fadd
    //   1752: invokevirtual resolve : (Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;F)V
    //   1755: aload_0
    //   1756: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1759: iload_3
    //   1760: aaload
    //   1761: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1764: aload_1
    //   1765: invokevirtual addResolvedValue : (Landroidx/constraintlayout/solver/LinearSystem;)V
    //   1768: aload_0
    //   1769: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1772: iload #17
    //   1774: aaload
    //   1775: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   1778: aload_1
    //   1779: invokevirtual addResolvedValue : (Landroidx/constraintlayout/solver/LinearSystem;)V
    //   1782: fload #6
    //   1784: fload #8
    //   1786: aload_0
    //   1787: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1790: iload #17
    //   1792: aaload
    //   1793: invokevirtual getMargin : ()I
    //   1796: i2f
    //   1797: fadd
    //   1798: fadd
    //   1799: fstore #8
    //   1801: fload #8
    //   1803: fstore #6
    //   1805: aload #4
    //   1807: ifnull -> 1657
    //   1810: fload #8
    //   1812: fstore #5
    //   1814: aload #4
    //   1816: invokevirtual getVisibility : ()I
    //   1819: bipush #8
    //   1821: if_icmpeq -> 1831
    //   1824: fload #8
    //   1826: fload #7
    //   1828: fadd
    //   1829: fstore #5
    //   1831: aload #4
    //   1833: astore_0
    //   1834: goto -> 1581
    //   1837: iconst_1
    //   1838: ireturn
    //   1839: iconst_0
    //   1840: ireturn
    //   1841: iconst_0
    //   1842: ireturn
  }
  
  static void checkMatchParent(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, ConstraintWidget paramConstraintWidget) {
    if (paramConstraintWidgetContainer.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && paramConstraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
      int j = paramConstraintWidget.mLeft.mMargin;
      int i = paramConstraintWidgetContainer.getWidth() - paramConstraintWidget.mRight.mMargin;
      ConstraintAnchor constraintAnchor = paramConstraintWidget.mLeft;
      constraintAnchor.mSolverVariable = paramLinearSystem.createObjectVariable(constraintAnchor);
      constraintAnchor = paramConstraintWidget.mRight;
      constraintAnchor.mSolverVariable = paramLinearSystem.createObjectVariable(constraintAnchor);
      paramLinearSystem.addEquality(paramConstraintWidget.mLeft.mSolverVariable, j);
      paramLinearSystem.addEquality(paramConstraintWidget.mRight.mSolverVariable, i);
      paramConstraintWidget.mHorizontalResolution = 2;
      paramConstraintWidget.setHorizontalDimension(j, i);
    } 
    if (paramConstraintWidgetContainer.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && paramConstraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
      int i = paramConstraintWidget.mTop.mMargin;
      int j = paramConstraintWidgetContainer.getHeight() - paramConstraintWidget.mBottom.mMargin;
      ConstraintAnchor constraintAnchor = paramConstraintWidget.mTop;
      constraintAnchor.mSolverVariable = paramLinearSystem.createObjectVariable(constraintAnchor);
      constraintAnchor = paramConstraintWidget.mBottom;
      constraintAnchor.mSolverVariable = paramLinearSystem.createObjectVariable(constraintAnchor);
      paramLinearSystem.addEquality(paramConstraintWidget.mTop.mSolverVariable, i);
      paramLinearSystem.addEquality(paramConstraintWidget.mBottom.mSolverVariable, j);
      if (paramConstraintWidget.mBaselineDistance > 0 || paramConstraintWidget.getVisibility() == 8) {
        constraintAnchor = paramConstraintWidget.mBaseline;
        constraintAnchor.mSolverVariable = paramLinearSystem.createObjectVariable(constraintAnchor);
        paramLinearSystem.addEquality(paramConstraintWidget.mBaseline.mSolverVariable, paramConstraintWidget.mBaselineDistance + i);
      } 
      paramConstraintWidget.mVerticalResolution = 2;
      paramConstraintWidget.setVerticalDimension(i, j);
    } 
  }
  
  private static boolean optimizableMatchConstraint(ConstraintWidget paramConstraintWidget, int paramInt) {
    ConstraintWidget.DimensionBehaviour[] arrayOfDimensionBehaviour = paramConstraintWidget.mListDimensionBehaviors;
    if (arrayOfDimensionBehaviour[paramInt] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
      return false; 
    float f = paramConstraintWidget.mDimensionRatio;
    boolean bool = true;
    if (f != 0.0F) {
      if (paramInt == 0) {
        paramInt = bool;
      } else {
        paramInt = 0;
      } 
      if (arrayOfDimensionBehaviour[paramInt] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
      return false;
    } 
    if (paramInt == 0) {
      if (paramConstraintWidget.mMatchConstraintDefaultWidth != 0)
        return false; 
      if (paramConstraintWidget.mMatchConstraintMinWidth != 0 || paramConstraintWidget.mMatchConstraintMaxWidth != 0)
        return false; 
    } else {
      if (paramConstraintWidget.mMatchConstraintDefaultHeight != 0)
        return false; 
      if (paramConstraintWidget.mMatchConstraintMinHeight != 0 || paramConstraintWidget.mMatchConstraintMaxHeight != 0)
        return false; 
    } 
    return true;
  }
  
  static void setOptimizedWidget(ConstraintWidget paramConstraintWidget, int paramInt1, int paramInt2) {
    int i = paramInt1 * 2;
    int j = i + 1;
    (paramConstraintWidget.mListAnchors[i].getResolutionNode()).resolvedTarget = (paramConstraintWidget.getParent()).mLeft.getResolutionNode();
    (paramConstraintWidget.mListAnchors[i].getResolutionNode()).resolvedOffset = paramInt2;
    (paramConstraintWidget.mListAnchors[i].getResolutionNode()).state = 1;
    (paramConstraintWidget.mListAnchors[j].getResolutionNode()).resolvedTarget = paramConstraintWidget.mListAnchors[i].getResolutionNode();
    (paramConstraintWidget.mListAnchors[j].getResolutionNode()).resolvedOffset = paramConstraintWidget.getLength(paramInt1);
    (paramConstraintWidget.mListAnchors[j].getResolutionNode()).state = 1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/Optimizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */