package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;

class Chain {
  static void applyChainConstraints(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt) {
    byte b1;
    int i;
    ChainHead[] arrayOfChainHead;
    byte b2 = 0;
    if (paramInt == 0) {
      i = paramConstraintWidgetContainer.mHorizontalChainsSize;
      arrayOfChainHead = paramConstraintWidgetContainer.mHorizontalChainsArray;
      b1 = 0;
    } else {
      b1 = 2;
      i = paramConstraintWidgetContainer.mVerticalChainsSize;
      arrayOfChainHead = paramConstraintWidgetContainer.mVerticalChainsArray;
    } 
    while (b2 < i) {
      ChainHead chainHead = arrayOfChainHead[b2];
      chainHead.define();
      if (paramConstraintWidgetContainer.optimizeFor(4)) {
        if (!Optimizer.applyChainOptimized(paramConstraintWidgetContainer, paramLinearSystem, paramInt, b1, chainHead))
          applyChainConstraints(paramConstraintWidgetContainer, paramLinearSystem, paramInt, b1, chainHead); 
      } else {
        applyChainConstraints(paramConstraintWidgetContainer, paramLinearSystem, paramInt, b1, chainHead);
      } 
      b2++;
    } 
  }
  
  static void applyChainConstraints(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt1, int paramInt2, ChainHead paramChainHead) {
    // Byte code:
    //   0: aload #4
    //   2: getfield mFirst : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   5: astore #24
    //   7: aload #4
    //   9: getfield mLast : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   12: astore #23
    //   14: aload #4
    //   16: getfield mFirstVisibleWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   19: astore #19
    //   21: aload #4
    //   23: getfield mLastVisibleWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   26: astore #22
    //   28: aload #4
    //   30: getfield mHead : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   33: astore #17
    //   35: aload #4
    //   37: getfield mTotalWeight : F
    //   40: fstore #5
    //   42: aload #4
    //   44: getfield mFirstMatchConstraintWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   47: astore #16
    //   49: aload #4
    //   51: getfield mLastMatchConstraintWidget : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   54: astore #16
    //   56: aload_0
    //   57: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   60: iload_2
    //   61: aaload
    //   62: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   65: if_acmpne -> 74
    //   68: iconst_1
    //   69: istore #12
    //   71: goto -> 77
    //   74: iconst_0
    //   75: istore #12
    //   77: iload_2
    //   78: ifne -> 136
    //   81: aload #17
    //   83: getfield mHorizontalChainStyle : I
    //   86: ifne -> 95
    //   89: iconst_1
    //   90: istore #8
    //   92: goto -> 98
    //   95: iconst_0
    //   96: istore #8
    //   98: aload #17
    //   100: getfield mHorizontalChainStyle : I
    //   103: iconst_1
    //   104: if_icmpne -> 113
    //   107: iconst_1
    //   108: istore #9
    //   110: goto -> 116
    //   113: iconst_0
    //   114: istore #9
    //   116: iload #8
    //   118: istore #10
    //   120: iload #9
    //   122: istore #11
    //   124: aload #17
    //   126: getfield mHorizontalChainStyle : I
    //   129: iconst_2
    //   130: if_icmpne -> 202
    //   133: goto -> 188
    //   136: aload #17
    //   138: getfield mVerticalChainStyle : I
    //   141: ifne -> 150
    //   144: iconst_1
    //   145: istore #8
    //   147: goto -> 153
    //   150: iconst_0
    //   151: istore #8
    //   153: aload #17
    //   155: getfield mVerticalChainStyle : I
    //   158: iconst_1
    //   159: if_icmpne -> 168
    //   162: iconst_1
    //   163: istore #9
    //   165: goto -> 171
    //   168: iconst_0
    //   169: istore #9
    //   171: iload #8
    //   173: istore #10
    //   175: iload #9
    //   177: istore #11
    //   179: aload #17
    //   181: getfield mVerticalChainStyle : I
    //   184: iconst_2
    //   185: if_icmpne -> 202
    //   188: iconst_1
    //   189: istore #13
    //   191: iload #8
    //   193: istore #10
    //   195: iload #9
    //   197: istore #11
    //   199: goto -> 205
    //   202: iconst_0
    //   203: istore #13
    //   205: aload #24
    //   207: astore #18
    //   209: iconst_0
    //   210: istore #8
    //   212: aconst_null
    //   213: astore #20
    //   215: iload #8
    //   217: ifne -> 600
    //   220: aload #18
    //   222: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   225: iload_3
    //   226: aaload
    //   227: astore #16
    //   229: iload #12
    //   231: ifne -> 248
    //   234: iload #13
    //   236: ifeq -> 242
    //   239: goto -> 248
    //   242: iconst_4
    //   243: istore #9
    //   245: goto -> 251
    //   248: iconst_1
    //   249: istore #9
    //   251: aload #16
    //   253: invokevirtual getMargin : ()I
    //   256: istore #15
    //   258: aload #16
    //   260: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   263: astore #21
    //   265: iload #15
    //   267: istore #14
    //   269: aload #21
    //   271: ifnull -> 295
    //   274: iload #15
    //   276: istore #14
    //   278: aload #18
    //   280: aload #24
    //   282: if_acmpeq -> 295
    //   285: iload #15
    //   287: aload #21
    //   289: invokevirtual getMargin : ()I
    //   292: iadd
    //   293: istore #14
    //   295: iload #13
    //   297: ifeq -> 321
    //   300: aload #18
    //   302: aload #24
    //   304: if_acmpeq -> 321
    //   307: aload #18
    //   309: aload #19
    //   311: if_acmpeq -> 321
    //   314: bipush #6
    //   316: istore #9
    //   318: goto -> 337
    //   321: iload #10
    //   323: ifeq -> 337
    //   326: iload #12
    //   328: ifeq -> 337
    //   331: iconst_4
    //   332: istore #9
    //   334: goto -> 337
    //   337: aload #16
    //   339: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   342: astore #21
    //   344: aload #21
    //   346: ifnull -> 419
    //   349: aload #18
    //   351: aload #19
    //   353: if_acmpne -> 376
    //   356: aload_1
    //   357: aload #16
    //   359: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   362: aload #21
    //   364: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   367: iload #14
    //   369: iconst_5
    //   370: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   373: goto -> 394
    //   376: aload_1
    //   377: aload #16
    //   379: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   382: aload #21
    //   384: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   387: iload #14
    //   389: bipush #6
    //   391: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   394: aload_1
    //   395: aload #16
    //   397: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   400: aload #16
    //   402: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   405: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   408: iload #14
    //   410: iload #9
    //   412: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   415: pop
    //   416: goto -> 419
    //   419: iload #12
    //   421: ifeq -> 505
    //   424: aload #18
    //   426: invokevirtual getVisibility : ()I
    //   429: bipush #8
    //   431: if_icmpeq -> 479
    //   434: aload #18
    //   436: getfield mListDimensionBehaviors : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   439: iload_2
    //   440: aaload
    //   441: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   444: if_acmpne -> 479
    //   447: aload #18
    //   449: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   452: astore #16
    //   454: aload_1
    //   455: aload #16
    //   457: iload_3
    //   458: iconst_1
    //   459: iadd
    //   460: aaload
    //   461: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   464: aload #16
    //   466: iload_3
    //   467: aaload
    //   468: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   471: iconst_0
    //   472: iconst_5
    //   473: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   476: goto -> 479
    //   479: aload_1
    //   480: aload #18
    //   482: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   485: iload_3
    //   486: aaload
    //   487: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   490: aload_0
    //   491: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   494: iload_3
    //   495: aaload
    //   496: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   499: iconst_0
    //   500: bipush #6
    //   502: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   505: aload #18
    //   507: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   510: iload_3
    //   511: iconst_1
    //   512: iadd
    //   513: aaload
    //   514: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   517: astore #21
    //   519: aload #20
    //   521: astore #16
    //   523: aload #21
    //   525: ifnull -> 582
    //   528: aload #21
    //   530: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   533: astore #21
    //   535: aload #21
    //   537: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   540: astore #25
    //   542: aload #20
    //   544: astore #16
    //   546: aload #25
    //   548: iload_3
    //   549: aaload
    //   550: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   553: ifnull -> 582
    //   556: aload #25
    //   558: iload_3
    //   559: aaload
    //   560: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   563: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   566: aload #18
    //   568: if_acmpeq -> 578
    //   571: aload #20
    //   573: astore #16
    //   575: goto -> 582
    //   578: aload #21
    //   580: astore #16
    //   582: aload #16
    //   584: ifnull -> 594
    //   587: aload #16
    //   589: astore #18
    //   591: goto -> 597
    //   594: iconst_1
    //   595: istore #8
    //   597: goto -> 212
    //   600: aload #22
    //   602: ifnull -> 668
    //   605: aload #23
    //   607: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   610: astore #18
    //   612: iload_3
    //   613: iconst_1
    //   614: iadd
    //   615: istore #8
    //   617: aload #18
    //   619: iload #8
    //   621: aaload
    //   622: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   625: ifnull -> 668
    //   628: aload #22
    //   630: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   633: iload #8
    //   635: aaload
    //   636: astore #16
    //   638: aload_1
    //   639: aload #16
    //   641: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   644: aload #18
    //   646: iload #8
    //   648: aaload
    //   649: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   652: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   655: aload #16
    //   657: invokevirtual getMargin : ()I
    //   660: ineg
    //   661: iconst_5
    //   662: invokevirtual addLowerThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   665: goto -> 668
    //   668: iload #12
    //   670: ifeq -> 720
    //   673: aload_0
    //   674: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   677: astore_0
    //   678: iload_3
    //   679: iconst_1
    //   680: iadd
    //   681: istore #8
    //   683: aload_0
    //   684: iload #8
    //   686: aaload
    //   687: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   690: astore #16
    //   692: aload #23
    //   694: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   697: astore_0
    //   698: aload_1
    //   699: aload #16
    //   701: aload_0
    //   702: iload #8
    //   704: aaload
    //   705: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   708: aload_0
    //   709: iload #8
    //   711: aaload
    //   712: invokevirtual getMargin : ()I
    //   715: bipush #6
    //   717: invokevirtual addGreaterThan : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   720: aload #4
    //   722: getfield mWeightedMatchConstraintsWidgets : Ljava/util/ArrayList;
    //   725: astore_0
    //   726: aload_0
    //   727: ifnull -> 1019
    //   730: aload_0
    //   731: invokevirtual size : ()I
    //   734: istore #8
    //   736: iload #8
    //   738: iconst_1
    //   739: if_icmple -> 1019
    //   742: aload #4
    //   744: getfield mHasUndefinedWeights : Z
    //   747: ifeq -> 769
    //   750: aload #4
    //   752: getfield mHasComplexMatchWeights : Z
    //   755: ifne -> 769
    //   758: aload #4
    //   760: getfield mWidgetsMatchCount : I
    //   763: i2f
    //   764: fstore #6
    //   766: goto -> 773
    //   769: fload #5
    //   771: fstore #6
    //   773: aconst_null
    //   774: astore #16
    //   776: iconst_0
    //   777: istore #9
    //   779: fconst_0
    //   780: fstore #7
    //   782: iload #9
    //   784: iload #8
    //   786: if_icmpge -> 1019
    //   789: aload_0
    //   790: iload #9
    //   792: invokevirtual get : (I)Ljava/lang/Object;
    //   795: checkcast androidx/constraintlayout/solver/widgets/ConstraintWidget
    //   798: astore #18
    //   800: aload #18
    //   802: getfield mWeight : [F
    //   805: iload_2
    //   806: faload
    //   807: fstore #5
    //   809: fload #5
    //   811: fconst_0
    //   812: fcmpg
    //   813: ifge -> 863
    //   816: aload #4
    //   818: getfield mHasComplexMatchWeights : Z
    //   821: ifeq -> 857
    //   824: aload #18
    //   826: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   829: astore #18
    //   831: aload_1
    //   832: aload #18
    //   834: iload_3
    //   835: iconst_1
    //   836: iadd
    //   837: aaload
    //   838: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   841: aload #18
    //   843: iload_3
    //   844: aaload
    //   845: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   848: iconst_0
    //   849: iconst_4
    //   850: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   853: pop
    //   854: goto -> 901
    //   857: fconst_1
    //   858: fstore #5
    //   860: goto -> 863
    //   863: fload #5
    //   865: fconst_0
    //   866: fcmpl
    //   867: ifne -> 908
    //   870: aload #18
    //   872: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   875: astore #18
    //   877: aload_1
    //   878: aload #18
    //   880: iload_3
    //   881: iconst_1
    //   882: iadd
    //   883: aaload
    //   884: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   887: aload #18
    //   889: iload_3
    //   890: aaload
    //   891: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   894: iconst_0
    //   895: bipush #6
    //   897: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   900: pop
    //   901: fload #7
    //   903: fstore #5
    //   905: goto -> 1009
    //   908: aload #16
    //   910: ifnull -> 1005
    //   913: aload #16
    //   915: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   918: astore #20
    //   920: aload #20
    //   922: iload_3
    //   923: aaload
    //   924: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   927: astore #16
    //   929: iload_3
    //   930: iconst_1
    //   931: iadd
    //   932: istore #12
    //   934: aload #20
    //   936: iload #12
    //   938: aaload
    //   939: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   942: astore #21
    //   944: aload #18
    //   946: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   949: astore #25
    //   951: aload #25
    //   953: iload_3
    //   954: aaload
    //   955: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   958: astore #20
    //   960: aload #25
    //   962: iload #12
    //   964: aaload
    //   965: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   968: astore #26
    //   970: aload_1
    //   971: invokevirtual createRow : ()Landroidx/constraintlayout/solver/ArrayRow;
    //   974: astore #25
    //   976: aload #25
    //   978: fload #7
    //   980: fload #6
    //   982: fload #5
    //   984: aload #16
    //   986: aload #21
    //   988: aload #20
    //   990: aload #26
    //   992: invokevirtual createRowEqualMatchDimensions : (FFFLandroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;)Landroidx/constraintlayout/solver/ArrayRow;
    //   995: pop
    //   996: aload_1
    //   997: aload #25
    //   999: invokevirtual addConstraint : (Landroidx/constraintlayout/solver/ArrayRow;)V
    //   1002: goto -> 1005
    //   1005: aload #18
    //   1007: astore #16
    //   1009: iinc #9, 1
    //   1012: fload #5
    //   1014: fstore #7
    //   1016: goto -> 782
    //   1019: aload #19
    //   1021: ifnull -> 1223
    //   1024: aload #19
    //   1026: aload #22
    //   1028: if_acmpeq -> 1036
    //   1031: iload #13
    //   1033: ifeq -> 1223
    //   1036: aload #24
    //   1038: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1041: astore_0
    //   1042: aload_0
    //   1043: iload_3
    //   1044: aaload
    //   1045: astore #18
    //   1047: aload #23
    //   1049: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1052: astore #4
    //   1054: iload_3
    //   1055: iconst_1
    //   1056: iadd
    //   1057: istore #8
    //   1059: aload #4
    //   1061: iload #8
    //   1063: aaload
    //   1064: astore #16
    //   1066: aload_0
    //   1067: iload_3
    //   1068: aaload
    //   1069: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1072: ifnull -> 1088
    //   1075: aload_0
    //   1076: iload_3
    //   1077: aaload
    //   1078: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1081: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1084: astore_0
    //   1085: goto -> 1090
    //   1088: aconst_null
    //   1089: astore_0
    //   1090: aload #23
    //   1092: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1095: astore #4
    //   1097: aload #4
    //   1099: iload #8
    //   1101: aaload
    //   1102: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1105: ifnull -> 1124
    //   1108: aload #4
    //   1110: iload #8
    //   1112: aaload
    //   1113: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1116: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1119: astore #4
    //   1121: goto -> 1127
    //   1124: aconst_null
    //   1125: astore #4
    //   1127: aload #19
    //   1129: aload #22
    //   1131: if_acmpne -> 1154
    //   1134: aload #19
    //   1136: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1139: astore #16
    //   1141: aload #16
    //   1143: iload_3
    //   1144: aaload
    //   1145: astore #18
    //   1147: aload #16
    //   1149: iload #8
    //   1151: aaload
    //   1152: astore #16
    //   1154: aload_0
    //   1155: ifnull -> 2294
    //   1158: aload #4
    //   1160: ifnull -> 2294
    //   1163: iload_2
    //   1164: ifne -> 1177
    //   1167: aload #17
    //   1169: getfield mHorizontalBiasPercent : F
    //   1172: fstore #5
    //   1174: goto -> 1184
    //   1177: aload #17
    //   1179: getfield mVerticalBiasPercent : F
    //   1182: fstore #5
    //   1184: aload #18
    //   1186: invokevirtual getMargin : ()I
    //   1189: istore #8
    //   1191: aload #16
    //   1193: invokevirtual getMargin : ()I
    //   1196: istore_2
    //   1197: aload_1
    //   1198: aload #18
    //   1200: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1203: aload_0
    //   1204: iload #8
    //   1206: fload #5
    //   1208: aload #4
    //   1210: aload #16
    //   1212: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1215: iload_2
    //   1216: iconst_5
    //   1217: invokevirtual addCentering : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;IFLandroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   1220: goto -> 2294
    //   1223: iload #10
    //   1225: ifeq -> 1720
    //   1228: aload #19
    //   1230: ifnull -> 1720
    //   1233: aload #4
    //   1235: getfield mWidgetsMatchCount : I
    //   1238: istore #8
    //   1240: iload #8
    //   1242: ifle -> 1261
    //   1245: aload #4
    //   1247: getfield mWidgetsCount : I
    //   1250: iload #8
    //   1252: if_icmpne -> 1261
    //   1255: iconst_1
    //   1256: istore #12
    //   1258: goto -> 1264
    //   1261: iconst_0
    //   1262: istore #12
    //   1264: aload #19
    //   1266: astore #4
    //   1268: aload #4
    //   1270: astore #18
    //   1272: aload #4
    //   1274: ifnull -> 2294
    //   1277: aload #4
    //   1279: getfield mNextChainWidget : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1282: iload_2
    //   1283: aaload
    //   1284: astore #16
    //   1286: aload #16
    //   1288: ifnull -> 1313
    //   1291: aload #16
    //   1293: invokevirtual getVisibility : ()I
    //   1296: bipush #8
    //   1298: if_icmpne -> 1313
    //   1301: aload #16
    //   1303: getfield mNextChainWidget : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1306: iload_2
    //   1307: aaload
    //   1308: astore #16
    //   1310: goto -> 1286
    //   1313: aload #16
    //   1315: ifnonnull -> 1331
    //   1318: aload #4
    //   1320: aload #22
    //   1322: if_acmpne -> 1328
    //   1325: goto -> 1331
    //   1328: goto -> 1699
    //   1331: aload #4
    //   1333: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1336: iload_3
    //   1337: aaload
    //   1338: astore #20
    //   1340: aload #20
    //   1342: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1345: astore #26
    //   1347: aload #20
    //   1349: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1352: astore_0
    //   1353: aload_0
    //   1354: ifnull -> 1366
    //   1357: aload_0
    //   1358: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1361: astore #17
    //   1363: goto -> 1369
    //   1366: aconst_null
    //   1367: astore #17
    //   1369: aload #18
    //   1371: aload #4
    //   1373: if_acmpeq -> 1392
    //   1376: aload #18
    //   1378: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1381: iload_3
    //   1382: iconst_1
    //   1383: iadd
    //   1384: aaload
    //   1385: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1388: astore_0
    //   1389: goto -> 1442
    //   1392: aload #17
    //   1394: astore_0
    //   1395: aload #4
    //   1397: aload #19
    //   1399: if_acmpne -> 1442
    //   1402: aload #17
    //   1404: astore_0
    //   1405: aload #18
    //   1407: aload #4
    //   1409: if_acmpne -> 1442
    //   1412: aload #24
    //   1414: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1417: astore_0
    //   1418: aload_0
    //   1419: iload_3
    //   1420: aaload
    //   1421: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1424: ifnull -> 1440
    //   1427: aload_0
    //   1428: iload_3
    //   1429: aaload
    //   1430: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1433: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1436: astore_0
    //   1437: goto -> 1442
    //   1440: aconst_null
    //   1441: astore_0
    //   1442: aload #20
    //   1444: invokevirtual getMargin : ()I
    //   1447: istore #13
    //   1449: aload #4
    //   1451: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1454: astore #17
    //   1456: iload_3
    //   1457: iconst_1
    //   1458: iadd
    //   1459: istore #14
    //   1461: aload #17
    //   1463: iload #14
    //   1465: aaload
    //   1466: invokevirtual getMargin : ()I
    //   1469: istore #9
    //   1471: aload #16
    //   1473: ifnull -> 1508
    //   1476: aload #16
    //   1478: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1481: iload_3
    //   1482: aaload
    //   1483: astore #17
    //   1485: aload #17
    //   1487: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1490: astore #20
    //   1492: aload #4
    //   1494: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1497: iload #14
    //   1499: aaload
    //   1500: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1503: astore #21
    //   1505: goto -> 1560
    //   1508: aload #23
    //   1510: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1513: iload #14
    //   1515: aaload
    //   1516: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1519: astore #25
    //   1521: aload #25
    //   1523: ifnull -> 1536
    //   1526: aload #25
    //   1528: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1531: astore #17
    //   1533: goto -> 1539
    //   1536: aconst_null
    //   1537: astore #17
    //   1539: aload #4
    //   1541: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1544: iload #14
    //   1546: aaload
    //   1547: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1550: astore #21
    //   1552: aload #17
    //   1554: astore #20
    //   1556: aload #25
    //   1558: astore #17
    //   1560: iload #9
    //   1562: istore #8
    //   1564: aload #17
    //   1566: ifnull -> 1579
    //   1569: iload #9
    //   1571: aload #17
    //   1573: invokevirtual getMargin : ()I
    //   1576: iadd
    //   1577: istore #8
    //   1579: iload #13
    //   1581: istore #9
    //   1583: aload #18
    //   1585: ifnull -> 1604
    //   1588: iload #13
    //   1590: aload #18
    //   1592: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1595: iload #14
    //   1597: aaload
    //   1598: invokevirtual getMargin : ()I
    //   1601: iadd
    //   1602: istore #9
    //   1604: aload #26
    //   1606: ifnull -> 1328
    //   1609: aload_0
    //   1610: ifnull -> 1328
    //   1613: aload #20
    //   1615: ifnull -> 1328
    //   1618: aload #21
    //   1620: ifnull -> 1328
    //   1623: aload #4
    //   1625: aload #19
    //   1627: if_acmpne -> 1642
    //   1630: aload #19
    //   1632: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1635: iload_3
    //   1636: aaload
    //   1637: invokevirtual getMargin : ()I
    //   1640: istore #9
    //   1642: aload #4
    //   1644: aload #22
    //   1646: if_acmpne -> 1665
    //   1649: aload #22
    //   1651: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1654: iload #14
    //   1656: aaload
    //   1657: invokevirtual getMargin : ()I
    //   1660: istore #8
    //   1662: goto -> 1665
    //   1665: iload #12
    //   1667: ifeq -> 1677
    //   1670: bipush #6
    //   1672: istore #13
    //   1674: goto -> 1680
    //   1677: iconst_4
    //   1678: istore #13
    //   1680: aload_1
    //   1681: aload #26
    //   1683: aload_0
    //   1684: iload #9
    //   1686: ldc 0.5
    //   1688: aload #20
    //   1690: aload #21
    //   1692: iload #8
    //   1694: iload #13
    //   1696: invokevirtual addCentering : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;IFLandroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   1699: aload #4
    //   1701: invokevirtual getVisibility : ()I
    //   1704: bipush #8
    //   1706: if_icmpeq -> 1713
    //   1709: aload #4
    //   1711: astore #18
    //   1713: aload #16
    //   1715: astore #4
    //   1717: goto -> 1272
    //   1720: iload #11
    //   1722: ifeq -> 2294
    //   1725: aload #19
    //   1727: ifnull -> 2294
    //   1730: aload #4
    //   1732: getfield mWidgetsMatchCount : I
    //   1735: istore #8
    //   1737: iload #8
    //   1739: ifle -> 1758
    //   1742: aload #4
    //   1744: getfield mWidgetsCount : I
    //   1747: iload #8
    //   1749: if_icmpne -> 1758
    //   1752: iconst_1
    //   1753: istore #8
    //   1755: goto -> 1761
    //   1758: iconst_0
    //   1759: istore #8
    //   1761: aload #19
    //   1763: astore #4
    //   1765: aload #4
    //   1767: astore #16
    //   1769: aload #4
    //   1771: ifnull -> 2134
    //   1774: aload #4
    //   1776: getfield mNextChainWidget : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1779: iload_2
    //   1780: aaload
    //   1781: astore_0
    //   1782: aload_0
    //   1783: ifnull -> 1805
    //   1786: aload_0
    //   1787: invokevirtual getVisibility : ()I
    //   1790: bipush #8
    //   1792: if_icmpne -> 1805
    //   1795: aload_0
    //   1796: getfield mNextChainWidget : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   1799: iload_2
    //   1800: aaload
    //   1801: astore_0
    //   1802: goto -> 1782
    //   1805: aload #4
    //   1807: aload #19
    //   1809: if_acmpeq -> 2107
    //   1812: aload #4
    //   1814: aload #22
    //   1816: if_acmpeq -> 2107
    //   1819: aload_0
    //   1820: ifnull -> 2107
    //   1823: aload_0
    //   1824: aload #22
    //   1826: if_acmpne -> 1834
    //   1829: aconst_null
    //   1830: astore_0
    //   1831: goto -> 1834
    //   1834: aload #4
    //   1836: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1839: iload_3
    //   1840: aaload
    //   1841: astore #17
    //   1843: aload #17
    //   1845: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1848: astore #21
    //   1850: aload #17
    //   1852: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1855: astore #18
    //   1857: aload #18
    //   1859: ifnull -> 1869
    //   1862: aload #18
    //   1864: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1867: astore #18
    //   1869: aload #16
    //   1871: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1874: astore #18
    //   1876: iload_3
    //   1877: iconst_1
    //   1878: iadd
    //   1879: istore #14
    //   1881: aload #18
    //   1883: iload #14
    //   1885: aaload
    //   1886: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1889: astore #25
    //   1891: aload #17
    //   1893: invokevirtual getMargin : ()I
    //   1896: istore #13
    //   1898: aload #4
    //   1900: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1903: iload #14
    //   1905: aaload
    //   1906: invokevirtual getMargin : ()I
    //   1909: istore #12
    //   1911: aload_0
    //   1912: ifnull -> 1958
    //   1915: aload_0
    //   1916: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1919: iload_3
    //   1920: aaload
    //   1921: astore #20
    //   1923: aload #20
    //   1925: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1928: astore #18
    //   1930: aload #20
    //   1932: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1935: astore #17
    //   1937: aload #17
    //   1939: ifnull -> 1952
    //   1942: aload #17
    //   1944: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1947: astore #17
    //   1949: goto -> 2002
    //   1952: aconst_null
    //   1953: astore #17
    //   1955: goto -> 2002
    //   1958: aload #4
    //   1960: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1963: iload #14
    //   1965: aaload
    //   1966: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1969: astore #20
    //   1971: aload #20
    //   1973: ifnull -> 1986
    //   1976: aload #20
    //   1978: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   1981: astore #18
    //   1983: goto -> 1989
    //   1986: aconst_null
    //   1987: astore #18
    //   1989: aload #4
    //   1991: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   1994: iload #14
    //   1996: aaload
    //   1997: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2000: astore #17
    //   2002: iload #12
    //   2004: istore #9
    //   2006: aload #20
    //   2008: ifnull -> 2021
    //   2011: iload #12
    //   2013: aload #20
    //   2015: invokevirtual getMargin : ()I
    //   2018: iadd
    //   2019: istore #9
    //   2021: iload #13
    //   2023: istore #12
    //   2025: aload #16
    //   2027: ifnull -> 2046
    //   2030: iload #13
    //   2032: aload #16
    //   2034: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2037: iload #14
    //   2039: aaload
    //   2040: invokevirtual getMargin : ()I
    //   2043: iadd
    //   2044: istore #12
    //   2046: iload #8
    //   2048: ifeq -> 2058
    //   2051: bipush #6
    //   2053: istore #13
    //   2055: goto -> 2061
    //   2058: iconst_4
    //   2059: istore #13
    //   2061: aload #21
    //   2063: ifnull -> 2104
    //   2066: aload #25
    //   2068: ifnull -> 2104
    //   2071: aload #18
    //   2073: ifnull -> 2104
    //   2076: aload #17
    //   2078: ifnull -> 2104
    //   2081: aload_1
    //   2082: aload #21
    //   2084: aload #25
    //   2086: iload #12
    //   2088: ldc 0.5
    //   2090: aload #18
    //   2092: aload #17
    //   2094: iload #9
    //   2096: iload #13
    //   2098: invokevirtual addCentering : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;IFLandroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   2101: goto -> 2104
    //   2104: goto -> 2107
    //   2107: aload #4
    //   2109: invokevirtual getVisibility : ()I
    //   2112: bipush #8
    //   2114: if_icmpeq -> 2120
    //   2117: goto -> 2124
    //   2120: aload #16
    //   2122: astore #4
    //   2124: aload #4
    //   2126: astore #16
    //   2128: aload_0
    //   2129: astore #4
    //   2131: goto -> 1769
    //   2134: aload #19
    //   2136: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2139: iload_3
    //   2140: aaload
    //   2141: astore_0
    //   2142: aload #24
    //   2144: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2147: iload_3
    //   2148: aaload
    //   2149: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2152: astore #4
    //   2154: aload #22
    //   2156: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2159: astore #16
    //   2161: iload_3
    //   2162: iconst_1
    //   2163: iadd
    //   2164: istore_2
    //   2165: aload #16
    //   2167: iload_2
    //   2168: aaload
    //   2169: astore #16
    //   2171: aload #23
    //   2173: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2176: iload_2
    //   2177: aaload
    //   2178: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2181: astore #17
    //   2183: aload #4
    //   2185: ifnull -> 2260
    //   2188: aload #19
    //   2190: aload #22
    //   2192: if_acmpeq -> 2217
    //   2195: aload_1
    //   2196: aload_0
    //   2197: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2200: aload #4
    //   2202: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2205: aload_0
    //   2206: invokevirtual getMargin : ()I
    //   2209: iconst_5
    //   2210: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   2213: pop
    //   2214: goto -> 2260
    //   2217: aload #17
    //   2219: ifnull -> 2260
    //   2222: aload_1
    //   2223: aload_0
    //   2224: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2227: aload #4
    //   2229: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2232: aload_0
    //   2233: invokevirtual getMargin : ()I
    //   2236: ldc 0.5
    //   2238: aload #16
    //   2240: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2243: aload #17
    //   2245: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2248: aload #16
    //   2250: invokevirtual getMargin : ()I
    //   2253: iconst_5
    //   2254: invokevirtual addCentering : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;IFLandroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   2257: goto -> 2260
    //   2260: aload #17
    //   2262: ifnull -> 2294
    //   2265: aload #19
    //   2267: aload #22
    //   2269: if_acmpeq -> 2294
    //   2272: aload_1
    //   2273: aload #16
    //   2275: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2278: aload #17
    //   2280: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2283: aload #16
    //   2285: invokevirtual getMargin : ()I
    //   2288: ineg
    //   2289: iconst_5
    //   2290: invokevirtual addEquality : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)Landroidx/constraintlayout/solver/ArrayRow;
    //   2293: pop
    //   2294: iload #10
    //   2296: ifne -> 2304
    //   2299: iload #11
    //   2301: ifeq -> 2497
    //   2304: aload #19
    //   2306: ifnull -> 2497
    //   2309: aload #19
    //   2311: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2314: iload_3
    //   2315: aaload
    //   2316: astore #16
    //   2318: aload #22
    //   2320: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2323: astore_0
    //   2324: iload_3
    //   2325: iconst_1
    //   2326: iadd
    //   2327: istore_2
    //   2328: aload_0
    //   2329: iload_2
    //   2330: aaload
    //   2331: astore #17
    //   2333: aload #16
    //   2335: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2338: astore_0
    //   2339: aload_0
    //   2340: ifnull -> 2352
    //   2343: aload_0
    //   2344: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2347: astore #4
    //   2349: goto -> 2355
    //   2352: aconst_null
    //   2353: astore #4
    //   2355: aload #17
    //   2357: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2360: astore_0
    //   2361: aload_0
    //   2362: ifnull -> 2373
    //   2365: aload_0
    //   2366: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2369: astore_0
    //   2370: goto -> 2375
    //   2373: aconst_null
    //   2374: astore_0
    //   2375: aload #23
    //   2377: aload #22
    //   2379: if_acmpeq -> 2407
    //   2382: aload #23
    //   2384: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2387: iload_2
    //   2388: aaload
    //   2389: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2392: astore_0
    //   2393: aload_0
    //   2394: ifnull -> 2405
    //   2397: aload_0
    //   2398: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2401: astore_0
    //   2402: goto -> 2407
    //   2405: aconst_null
    //   2406: astore_0
    //   2407: aload #19
    //   2409: aload #22
    //   2411: if_acmpne -> 2433
    //   2414: aload #19
    //   2416: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2419: astore #17
    //   2421: aload #17
    //   2423: iload_3
    //   2424: aaload
    //   2425: astore #16
    //   2427: aload #17
    //   2429: iload_2
    //   2430: aaload
    //   2431: astore #17
    //   2433: aload #4
    //   2435: ifnull -> 2497
    //   2438: aload_0
    //   2439: ifnull -> 2497
    //   2442: aload #16
    //   2444: invokevirtual getMargin : ()I
    //   2447: istore_3
    //   2448: aload #22
    //   2450: ifnonnull -> 2460
    //   2453: aload #23
    //   2455: astore #18
    //   2457: goto -> 2464
    //   2460: aload #22
    //   2462: astore #18
    //   2464: aload #18
    //   2466: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   2469: iload_2
    //   2470: aaload
    //   2471: invokevirtual getMargin : ()I
    //   2474: istore_2
    //   2475: aload_1
    //   2476: aload #16
    //   2478: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2481: aload #4
    //   2483: iload_3
    //   2484: ldc 0.5
    //   2486: aload_0
    //   2487: aload #17
    //   2489: getfield mSolverVariable : Landroidx/constraintlayout/solver/SolverVariable;
    //   2492: iload_2
    //   2493: iconst_5
    //   2494: invokevirtual addCentering : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;IFLandroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/SolverVariable;II)V
    //   2497: return
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/Chain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */