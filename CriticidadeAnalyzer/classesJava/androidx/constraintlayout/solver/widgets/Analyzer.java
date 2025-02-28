package androidx.constraintlayout.solver.widgets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Analyzer {
  public static void determineGroups(ConstraintWidgetContainer paramConstraintWidgetContainer) {
    boolean bool1;
    boolean bool2;
    boolean bool3;
    if ((paramConstraintWidgetContainer.getOptimizationLevel() & 0x20) != 32) {
      singleGroup(paramConstraintWidgetContainer);
      return;
    } 
    paramConstraintWidgetContainer.mSkipSolver = true;
    paramConstraintWidgetContainer.mGroupsWrapOptimized = false;
    paramConstraintWidgetContainer.mHorizontalWrapOptimized = false;
    paramConstraintWidgetContainer.mVerticalWrapOptimized = false;
    ArrayList<ConstraintWidget> arrayList = paramConstraintWidgetContainer.mChildren;
    List<ConstraintWidgetGroup> list = paramConstraintWidgetContainer.mWidgetGroups;
    if (paramConstraintWidgetContainer.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramConstraintWidgetContainer.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (bool1 || bool2) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    list.clear();
    for (ConstraintWidget constraintWidget : arrayList) {
      constraintWidget.mBelongingGroup = null;
      constraintWidget.mGroupsToSolver = false;
      constraintWidget.resetResolutionNodes();
    } 
    for (ConstraintWidget constraintWidget : arrayList) {
      if (constraintWidget.mBelongingGroup == null && !determineGroups(constraintWidget, list, bool3)) {
        singleGroup(paramConstraintWidgetContainer);
        paramConstraintWidgetContainer.mSkipSolver = false;
        return;
      } 
    } 
    Iterator<ConstraintWidgetGroup> iterator = list.iterator();
    int j = 0;
    int i;
    for (i = 0; iterator.hasNext(); i = Math.max(i, getMaxDimension(constraintWidgetGroup, 1))) {
      ConstraintWidgetGroup constraintWidgetGroup = iterator.next();
      j = Math.max(j, getMaxDimension(constraintWidgetGroup, 0));
    } 
    if (bool1) {
      paramConstraintWidgetContainer.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
      paramConstraintWidgetContainer.setWidth(j);
      paramConstraintWidgetContainer.mGroupsWrapOptimized = true;
      paramConstraintWidgetContainer.mHorizontalWrapOptimized = true;
      paramConstraintWidgetContainer.mWrapFixedWidth = j;
    } 
    if (bool2) {
      paramConstraintWidgetContainer.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
      paramConstraintWidgetContainer.setHeight(i);
      paramConstraintWidgetContainer.mGroupsWrapOptimized = true;
      paramConstraintWidgetContainer.mVerticalWrapOptimized = true;
      paramConstraintWidgetContainer.mWrapFixedHeight = i;
    } 
    setPosition(list, 0, paramConstraintWidgetContainer.getWidth());
    setPosition(list, 1, paramConstraintWidgetContainer.getHeight());
  }
  
  private static boolean determineGroups(ConstraintWidget paramConstraintWidget, List<ConstraintWidgetGroup> paramList, boolean paramBoolean) {
    ConstraintWidgetGroup constraintWidgetGroup = new ConstraintWidgetGroup(new ArrayList<ConstraintWidget>(), true);
    paramList.add(constraintWidgetGroup);
    return traverse(paramConstraintWidget, constraintWidgetGroup, paramList, paramBoolean);
  }
  
  private static int getMaxDimension(ConstraintWidgetGroup paramConstraintWidgetGroup, int paramInt) {
    int k = paramInt * 2;
    List<ConstraintWidget> list = paramConstraintWidgetGroup.getStartWidgets(paramInt);
    int j = list.size();
    byte b = 0;
    int i = 0;
    while (b < j) {
      boolean bool;
      ConstraintWidget constraintWidget = list.get(b);
      ConstraintAnchor[] arrayOfConstraintAnchor = constraintWidget.mListAnchors;
      int m = k + 1;
      if ((arrayOfConstraintAnchor[m]).mTarget == null || ((arrayOfConstraintAnchor[k]).mTarget != null && (arrayOfConstraintAnchor[m]).mTarget != null)) {
        bool = true;
      } else {
        bool = false;
      } 
      i = Math.max(i, getMaxDimensionTraversal(constraintWidget, paramInt, bool, 0));
      b++;
    } 
    paramConstraintWidgetGroup.mGroupDimensions[paramInt] = i;
    return i;
  }
  
  private static int getMaxDimensionTraversal(ConstraintWidget paramConstraintWidget, int paramInt1, boolean paramBoolean, int paramInt2) {
    int i;
    int j;
    int k;
    byte b;
    int n;
    int i3;
    boolean bool = paramConstraintWidget.mOptimizerMeasurable;
    int i2 = 0;
    if (!bool)
      return 0; 
    if (paramConstraintWidget.mBaseline.mTarget != null && paramInt1 == 1) {
      k = 1;
    } else {
      k = 0;
    } 
    if (paramBoolean) {
      n = paramConstraintWidget.getBaselineDistance();
      m = paramConstraintWidget.getHeight() - paramConstraintWidget.getBaselineDistance();
      j = paramInt1 * 2;
      i = j + 1;
    } else {
      n = paramConstraintWidget.getHeight() - paramConstraintWidget.getBaselineDistance();
      m = paramConstraintWidget.getBaselineDistance();
      i = paramInt1 * 2;
      j = i + 1;
    } 
    ConstraintAnchor[] arrayOfConstraintAnchor2 = paramConstraintWidget.mListAnchors;
    if ((arrayOfConstraintAnchor2[i]).mTarget != null && (arrayOfConstraintAnchor2[j]).mTarget == null) {
      int i6 = j;
      b = -1;
      j = i;
      i = i6;
    } else {
      b = 1;
    } 
    if (k)
      paramInt2 -= n; 
    int i4 = paramConstraintWidget.mListAnchors[j].getMargin() * b + getParentBiasOffset(paramConstraintWidget, paramInt1);
    int i1 = paramInt2 + i4;
    if (paramInt1 == 0) {
      paramInt2 = paramConstraintWidget.getWidth();
    } else {
      paramInt2 = paramConstraintWidget.getHeight();
    } 
    int i5 = paramInt2 * b;
    Iterator<ResolutionNode> iterator = (paramConstraintWidget.mListAnchors[j].getResolutionNode()).dependents.iterator();
    for (paramInt2 = i2; iterator.hasNext(); paramInt2 = Math.max(paramInt2, getMaxDimensionTraversal(((ResolutionAnchor)iterator.next()).myAnchor.mOwner, paramInt1, paramBoolean, i1)));
    iterator = (paramConstraintWidget.mListAnchors[i].getResolutionNode()).dependents.iterator();
    for (i2 = 0; iterator.hasNext(); i2 = Math.max(i2, getMaxDimensionTraversal(((ResolutionAnchor)iterator.next()).myAnchor.mOwner, paramInt1, paramBoolean, i5 + i1)));
    if (k) {
      paramInt2 -= n;
      i3 = i2 + m;
      i2 = paramInt2;
    } else {
      if (paramInt1 == 0) {
        i3 = paramConstraintWidget.getWidth();
      } else {
        i3 = paramConstraintWidget.getHeight();
      } 
      i3 = i2 + i3 * b;
      i2 = paramInt2;
    } 
    if (paramInt1 == 1) {
      iterator = (paramConstraintWidget.mBaseline.getResolutionNode()).dependents.iterator();
      for (paramInt2 = 0; iterator.hasNext(); paramInt2 = Math.max(paramInt2, getMaxDimensionTraversal(resolutionAnchor.myAnchor.mOwner, paramInt1, paramBoolean, m * b + i1))) {
        ResolutionAnchor resolutionAnchor = (ResolutionAnchor)iterator.next();
        if (b == 1) {
          paramInt2 = Math.max(paramInt2, getMaxDimensionTraversal(resolutionAnchor.myAnchor.mOwner, paramInt1, paramBoolean, n + i1));
          continue;
        } 
      } 
      if ((paramConstraintWidget.mBaseline.getResolutionNode()).dependents.size() > 0 && !k)
        if (b == 1) {
          paramInt2 += n;
        } else {
          paramInt2 -= m;
        }  
    } else {
      paramInt2 = 0;
    } 
    int m = Math.max(i2, Math.max(i3, paramInt2));
    paramInt2 = i1 + i5;
    if (b == -1) {
      k = i1;
    } else {
      k = paramInt2;
      paramInt2 = i1;
    } 
    if (paramBoolean) {
      Optimizer.setOptimizedWidget(paramConstraintWidget, paramInt1, paramInt2);
      paramConstraintWidget.setFrame(paramInt2, k, paramInt1);
    } else {
      paramConstraintWidget.mBelongingGroup.addWidgetsToSet(paramConstraintWidget, paramInt1);
      paramConstraintWidget.setRelativePositioning(paramInt2, paramInt1);
    } 
    if (paramConstraintWidget.getDimensionBehaviour(paramInt1) == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && paramConstraintWidget.mDimensionRatio != 0.0F)
      paramConstraintWidget.mBelongingGroup.addWidgetsToSet(paramConstraintWidget, paramInt1); 
    ConstraintAnchor[] arrayOfConstraintAnchor1 = paramConstraintWidget.mListAnchors;
    if ((arrayOfConstraintAnchor1[j]).mTarget != null && (arrayOfConstraintAnchor1[i]).mTarget != null) {
      ConstraintWidget constraintWidget = paramConstraintWidget.getParent();
      ConstraintAnchor[] arrayOfConstraintAnchor = paramConstraintWidget.mListAnchors;
      if ((arrayOfConstraintAnchor[j]).mTarget.mOwner == constraintWidget && (arrayOfConstraintAnchor[i]).mTarget.mOwner == constraintWidget)
        paramConstraintWidget.mBelongingGroup.addWidgetsToSet(paramConstraintWidget, paramInt1); 
    } 
    return i4 + m;
  }
  
  private static int getParentBiasOffset(ConstraintWidget paramConstraintWidget, int paramInt) {
    int i = paramInt * 2;
    ConstraintAnchor[] arrayOfConstraintAnchor = paramConstraintWidget.mListAnchors;
    ConstraintAnchor constraintAnchor1 = arrayOfConstraintAnchor[i];
    ConstraintAnchor constraintAnchor2 = arrayOfConstraintAnchor[i + 1];
    ConstraintAnchor constraintAnchor3 = constraintAnchor1.mTarget;
    if (constraintAnchor3 != null) {
      ConstraintWidget constraintWidget2 = constraintAnchor3.mOwner;
      ConstraintWidget constraintWidget1 = paramConstraintWidget.mParent;
      if (constraintWidget2 == constraintWidget1) {
        ConstraintAnchor constraintAnchor = constraintAnchor2.mTarget;
        if (constraintAnchor != null && constraintAnchor.mOwner == constraintWidget1) {
          float f;
          i = constraintWidget1.getLength(paramInt);
          if (paramInt == 0) {
            f = paramConstraintWidget.mHorizontalBiasPercent;
          } else {
            f = paramConstraintWidget.mVerticalBiasPercent;
          } 
          paramInt = paramConstraintWidget.getLength(paramInt);
          return (int)((i - constraintAnchor1.getMargin() - constraintAnchor2.getMargin() - paramInt) * f);
        } 
      } 
    } 
    return 0;
  }
  
  private static void invalidate(ConstraintWidgetContainer paramConstraintWidgetContainer, ConstraintWidget paramConstraintWidget, ConstraintWidgetGroup paramConstraintWidgetGroup) {
    paramConstraintWidgetGroup.mSkipSolver = false;
    paramConstraintWidgetContainer.mSkipSolver = false;
    paramConstraintWidget.mOptimizerMeasurable = false;
  }
  
  private static int resolveDimensionRatio(ConstraintWidget paramConstraintWidget) {
    byte b;
    if (paramConstraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
      float f;
      if (paramConstraintWidget.mDimensionRatioSide == 0) {
        f = paramConstraintWidget.getHeight() * paramConstraintWidget.mDimensionRatio;
      } else {
        f = paramConstraintWidget.getHeight() / paramConstraintWidget.mDimensionRatio;
      } 
      b = (int)f;
      paramConstraintWidget.setWidth(b);
    } else if (paramConstraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
      float f;
      if (paramConstraintWidget.mDimensionRatioSide == 1) {
        f = paramConstraintWidget.getWidth() * paramConstraintWidget.mDimensionRatio;
      } else {
        f = paramConstraintWidget.getWidth() / paramConstraintWidget.mDimensionRatio;
      } 
      b = (int)f;
      paramConstraintWidget.setHeight(b);
    } else {
      b = -1;
    } 
    return b;
  }
  
  private static void setConnection(ConstraintAnchor paramConstraintAnchor) {
    ResolutionAnchor resolutionAnchor = paramConstraintAnchor.getResolutionNode();
    ConstraintAnchor constraintAnchor = paramConstraintAnchor.mTarget;
    if (constraintAnchor != null && constraintAnchor.mTarget != paramConstraintAnchor)
      constraintAnchor.getResolutionNode().addDependent(resolutionAnchor); 
  }
  
  public static void setPosition(List<ConstraintWidgetGroup> paramList, int paramInt1, int paramInt2) {
    int i = paramList.size();
    for (byte b = 0; b < i; b++) {
      for (ConstraintWidget constraintWidget : ((ConstraintWidgetGroup)paramList.get(b)).getWidgetsToSet(paramInt1)) {
        if (constraintWidget.mOptimizerMeasurable)
          updateSizeDependentWidgets(constraintWidget, paramInt1, paramInt2); 
      } 
    } 
  }
  
  private static void singleGroup(ConstraintWidgetContainer paramConstraintWidgetContainer) {
    paramConstraintWidgetContainer.mWidgetGroups.clear();
    paramConstraintWidgetContainer.mWidgetGroups.add(0, new ConstraintWidgetGroup(paramConstraintWidgetContainer.mChildren));
  }
  
  private static boolean traverse(ConstraintWidget paramConstraintWidget, ConstraintWidgetGroup paramConstraintWidgetGroup, List<ConstraintWidgetGroup> paramList, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull -> 6
    //   4: iconst_1
    //   5: ireturn
    //   6: aload_0
    //   7: iconst_0
    //   8: putfield mOptimizerMeasured : Z
    //   11: aload_0
    //   12: invokevirtual getParent : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   15: checkcast androidx/constraintlayout/solver/widgets/ConstraintWidgetContainer
    //   18: astore #6
    //   20: aload_0
    //   21: getfield mBelongingGroup : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;
    //   24: astore #7
    //   26: aload #7
    //   28: ifnonnull -> 941
    //   31: aload_0
    //   32: iconst_1
    //   33: putfield mOptimizerMeasurable : Z
    //   36: aload_1
    //   37: getfield mConstrainedGroup : Ljava/util/List;
    //   40: aload_0
    //   41: invokeinterface add : (Ljava/lang/Object;)Z
    //   46: pop
    //   47: aload_0
    //   48: aload_1
    //   49: putfield mBelongingGroup : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;
    //   52: aload_0
    //   53: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   56: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   59: ifnonnull -> 125
    //   62: aload_0
    //   63: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   66: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   69: ifnonnull -> 125
    //   72: aload_0
    //   73: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   76: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   79: ifnonnull -> 125
    //   82: aload_0
    //   83: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   86: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   89: ifnonnull -> 125
    //   92: aload_0
    //   93: getfield mBaseline : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   96: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   99: ifnonnull -> 125
    //   102: aload_0
    //   103: getfield mCenter : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   106: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   109: ifnonnull -> 125
    //   112: aload #6
    //   114: aload_0
    //   115: aload_1
    //   116: invokestatic invalidate : (Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;)V
    //   119: iload_3
    //   120: ifeq -> 125
    //   123: iconst_0
    //   124: ireturn
    //   125: aload_0
    //   126: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   129: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   132: ifnull -> 210
    //   135: aload_0
    //   136: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   139: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   142: ifnull -> 210
    //   145: aload #6
    //   147: invokevirtual getVerticalDimensionBehaviour : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   150: pop
    //   151: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   154: astore #7
    //   156: iload_3
    //   157: ifeq -> 169
    //   160: aload #6
    //   162: aload_0
    //   163: aload_1
    //   164: invokestatic invalidate : (Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;)V
    //   167: iconst_0
    //   168: ireturn
    //   169: aload_0
    //   170: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   173: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   176: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   179: aload_0
    //   180: invokevirtual getParent : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   183: if_acmpne -> 203
    //   186: aload_0
    //   187: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   190: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   193: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   196: aload_0
    //   197: invokevirtual getParent : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   200: if_acmpeq -> 210
    //   203: aload #6
    //   205: aload_0
    //   206: aload_1
    //   207: invokestatic invalidate : (Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;)V
    //   210: aload_0
    //   211: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   214: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   217: ifnull -> 295
    //   220: aload_0
    //   221: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   224: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   227: ifnull -> 295
    //   230: aload #6
    //   232: invokevirtual getHorizontalDimensionBehaviour : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   235: pop
    //   236: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.WRAP_CONTENT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   239: astore #7
    //   241: iload_3
    //   242: ifeq -> 254
    //   245: aload #6
    //   247: aload_0
    //   248: aload_1
    //   249: invokestatic invalidate : (Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;)V
    //   252: iconst_0
    //   253: ireturn
    //   254: aload_0
    //   255: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   258: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   261: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   264: aload_0
    //   265: invokevirtual getParent : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   268: if_acmpne -> 288
    //   271: aload_0
    //   272: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   275: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   278: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   281: aload_0
    //   282: invokevirtual getParent : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   285: if_acmpeq -> 295
    //   288: aload #6
    //   290: aload_0
    //   291: aload_1
    //   292: invokestatic invalidate : (Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;)V
    //   295: aload_0
    //   296: invokevirtual getHorizontalDimensionBehaviour : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   299: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   302: if_acmpne -> 311
    //   305: iconst_1
    //   306: istore #4
    //   308: goto -> 314
    //   311: iconst_0
    //   312: istore #4
    //   314: aload_0
    //   315: invokevirtual getVerticalDimensionBehaviour : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   318: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   321: if_acmpne -> 330
    //   324: iconst_1
    //   325: istore #5
    //   327: goto -> 333
    //   330: iconst_0
    //   331: istore #5
    //   333: iload #4
    //   335: iload #5
    //   337: ixor
    //   338: ifeq -> 358
    //   341: aload_0
    //   342: getfield mDimensionRatio : F
    //   345: fconst_0
    //   346: fcmpl
    //   347: ifeq -> 358
    //   350: aload_0
    //   351: invokestatic resolveDimensionRatio : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;)I
    //   354: pop
    //   355: goto -> 391
    //   358: aload_0
    //   359: invokevirtual getHorizontalDimensionBehaviour : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   362: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   365: if_acmpeq -> 378
    //   368: aload_0
    //   369: invokevirtual getVerticalDimensionBehaviour : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   372: getstatic androidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT : Landroidx/constraintlayout/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   375: if_acmpne -> 391
    //   378: aload #6
    //   380: aload_0
    //   381: aload_1
    //   382: invokestatic invalidate : (Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;)V
    //   385: iload_3
    //   386: ifeq -> 391
    //   389: iconst_0
    //   390: ireturn
    //   391: aload_0
    //   392: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   395: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   398: ifnonnull -> 411
    //   401: aload_0
    //   402: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   405: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   408: ifnull -> 541
    //   411: aload_0
    //   412: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   415: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   418: astore #7
    //   420: aload #7
    //   422: ifnull -> 447
    //   425: aload #7
    //   427: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   430: aload_0
    //   431: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   434: if_acmpne -> 447
    //   437: aload_0
    //   438: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   441: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   444: ifnull -> 541
    //   447: aload_0
    //   448: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   451: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   454: astore #7
    //   456: aload #7
    //   458: ifnull -> 483
    //   461: aload #7
    //   463: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   466: aload_0
    //   467: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   470: if_acmpne -> 483
    //   473: aload_0
    //   474: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   477: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   480: ifnull -> 541
    //   483: aload_0
    //   484: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   487: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   490: astore #7
    //   492: aload #7
    //   494: ifnull -> 576
    //   497: aload #7
    //   499: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   502: astore #8
    //   504: aload_0
    //   505: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   508: astore #7
    //   510: aload #8
    //   512: aload #7
    //   514: if_acmpne -> 576
    //   517: aload_0
    //   518: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   521: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   524: astore #8
    //   526: aload #8
    //   528: ifnull -> 576
    //   531: aload #8
    //   533: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   536: aload #7
    //   538: if_acmpne -> 576
    //   541: aload_0
    //   542: getfield mCenter : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   545: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   548: ifnonnull -> 576
    //   551: aload_0
    //   552: instanceof androidx/constraintlayout/solver/widgets/Guideline
    //   555: ifne -> 576
    //   558: aload_0
    //   559: instanceof androidx/constraintlayout/solver/widgets/Helper
    //   562: ifne -> 576
    //   565: aload_1
    //   566: getfield mStartHorizontalWidgets : Ljava/util/List;
    //   569: aload_0
    //   570: invokeinterface add : (Ljava/lang/Object;)Z
    //   575: pop
    //   576: aload_0
    //   577: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   580: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   583: ifnonnull -> 596
    //   586: aload_0
    //   587: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   590: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   593: ifnull -> 726
    //   596: aload_0
    //   597: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   600: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   603: astore #7
    //   605: aload #7
    //   607: ifnull -> 632
    //   610: aload #7
    //   612: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   615: aload_0
    //   616: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   619: if_acmpne -> 632
    //   622: aload_0
    //   623: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   626: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   629: ifnull -> 726
    //   632: aload_0
    //   633: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   636: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   639: astore #7
    //   641: aload #7
    //   643: ifnull -> 668
    //   646: aload #7
    //   648: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   651: aload_0
    //   652: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   655: if_acmpne -> 668
    //   658: aload_0
    //   659: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   662: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   665: ifnull -> 726
    //   668: aload_0
    //   669: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   672: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   675: astore #7
    //   677: aload #7
    //   679: ifnull -> 771
    //   682: aload #7
    //   684: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   687: astore #8
    //   689: aload_0
    //   690: getfield mParent : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   693: astore #7
    //   695: aload #8
    //   697: aload #7
    //   699: if_acmpne -> 771
    //   702: aload_0
    //   703: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   706: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   709: astore #8
    //   711: aload #8
    //   713: ifnull -> 771
    //   716: aload #8
    //   718: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   721: aload #7
    //   723: if_acmpne -> 771
    //   726: aload_0
    //   727: getfield mCenter : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   730: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   733: ifnonnull -> 771
    //   736: aload_0
    //   737: getfield mBaseline : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   740: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   743: ifnonnull -> 771
    //   746: aload_0
    //   747: instanceof androidx/constraintlayout/solver/widgets/Guideline
    //   750: ifne -> 771
    //   753: aload_0
    //   754: instanceof androidx/constraintlayout/solver/widgets/Helper
    //   757: ifne -> 771
    //   760: aload_1
    //   761: getfield mStartVerticalWidgets : Ljava/util/List;
    //   764: aload_0
    //   765: invokeinterface add : (Ljava/lang/Object;)Z
    //   770: pop
    //   771: aload_0
    //   772: instanceof androidx/constraintlayout/solver/widgets/Helper
    //   775: ifeq -> 835
    //   778: aload #6
    //   780: aload_0
    //   781: aload_1
    //   782: invokestatic invalidate : (Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;)V
    //   785: iload_3
    //   786: ifeq -> 791
    //   789: iconst_0
    //   790: ireturn
    //   791: aload_0
    //   792: checkcast androidx/constraintlayout/solver/widgets/Helper
    //   795: astore #7
    //   797: iconst_0
    //   798: istore #4
    //   800: iload #4
    //   802: aload #7
    //   804: getfield mWidgetsCount : I
    //   807: if_icmpge -> 835
    //   810: aload #7
    //   812: getfield mWidgets : [Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   815: iload #4
    //   817: aaload
    //   818: aload_1
    //   819: aload_2
    //   820: iload_3
    //   821: invokestatic traverse : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;Ljava/util/List;Z)Z
    //   824: ifne -> 829
    //   827: iconst_0
    //   828: ireturn
    //   829: iinc #4, 1
    //   832: goto -> 800
    //   835: aload_0
    //   836: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   839: arraylength
    //   840: istore #5
    //   842: iconst_0
    //   843: istore #4
    //   845: iload #4
    //   847: iload #5
    //   849: if_icmpge -> 939
    //   852: aload_0
    //   853: getfield mListAnchors : [Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   856: iload #4
    //   858: aaload
    //   859: astore #8
    //   861: aload #8
    //   863: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   866: astore #7
    //   868: aload #7
    //   870: ifnull -> 933
    //   873: aload #7
    //   875: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   878: aload_0
    //   879: invokevirtual getParent : ()Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   882: if_acmpeq -> 933
    //   885: aload #8
    //   887: getfield mType : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   890: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.CENTER : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   893: if_acmpne -> 909
    //   896: aload #6
    //   898: aload_0
    //   899: aload_1
    //   900: invokestatic invalidate : (Landroidx/constraintlayout/solver/widgets/ConstraintWidgetContainer;Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;)V
    //   903: iload_3
    //   904: ifeq -> 914
    //   907: iconst_0
    //   908: ireturn
    //   909: aload #8
    //   911: invokestatic setConnection : (Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;)V
    //   914: aload #8
    //   916: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   919: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   922: aload_1
    //   923: aload_2
    //   924: iload_3
    //   925: invokestatic traverse : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;Ljava/util/List;Z)Z
    //   928: ifne -> 933
    //   931: iconst_0
    //   932: ireturn
    //   933: iinc #4, 1
    //   936: goto -> 845
    //   939: iconst_1
    //   940: ireturn
    //   941: aload #7
    //   943: aload_1
    //   944: if_acmpeq -> 1060
    //   947: aload_1
    //   948: getfield mConstrainedGroup : Ljava/util/List;
    //   951: aload #7
    //   953: getfield mConstrainedGroup : Ljava/util/List;
    //   956: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   961: pop
    //   962: aload_1
    //   963: getfield mStartHorizontalWidgets : Ljava/util/List;
    //   966: aload_0
    //   967: getfield mBelongingGroup : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;
    //   970: getfield mStartHorizontalWidgets : Ljava/util/List;
    //   973: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   978: pop
    //   979: aload_1
    //   980: getfield mStartVerticalWidgets : Ljava/util/List;
    //   983: aload_0
    //   984: getfield mBelongingGroup : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;
    //   987: getfield mStartVerticalWidgets : Ljava/util/List;
    //   990: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   995: pop
    //   996: aload_0
    //   997: getfield mBelongingGroup : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;
    //   1000: getfield mSkipSolver : Z
    //   1003: ifne -> 1011
    //   1006: aload_1
    //   1007: iconst_0
    //   1008: putfield mSkipSolver : Z
    //   1011: aload_2
    //   1012: aload_0
    //   1013: getfield mBelongingGroup : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;
    //   1016: invokeinterface remove : (Ljava/lang/Object;)Z
    //   1021: pop
    //   1022: aload_0
    //   1023: getfield mBelongingGroup : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;
    //   1026: getfield mConstrainedGroup : Ljava/util/List;
    //   1029: invokeinterface iterator : ()Ljava/util/Iterator;
    //   1034: astore_0
    //   1035: aload_0
    //   1036: invokeinterface hasNext : ()Z
    //   1041: ifeq -> 1060
    //   1044: aload_0
    //   1045: invokeinterface next : ()Ljava/lang/Object;
    //   1050: checkcast androidx/constraintlayout/solver/widgets/ConstraintWidget
    //   1053: aload_1
    //   1054: putfield mBelongingGroup : Landroidx/constraintlayout/solver/widgets/ConstraintWidgetGroup;
    //   1057: goto -> 1035
    //   1060: iconst_1
    //   1061: ireturn
  }
  
  private static void updateSizeDependentWidgets(ConstraintWidget paramConstraintWidget, int paramInt1, int paramInt2) {
    int j = paramInt1 * 2;
    ConstraintAnchor[] arrayOfConstraintAnchor = paramConstraintWidget.mListAnchors;
    ConstraintAnchor constraintAnchor1 = arrayOfConstraintAnchor[j];
    ConstraintAnchor constraintAnchor2 = arrayOfConstraintAnchor[j + 1];
    if (constraintAnchor1.mTarget != null && constraintAnchor2.mTarget != null) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i) {
      Optimizer.setOptimizedWidget(paramConstraintWidget, paramInt1, getParentBiasOffset(paramConstraintWidget, paramInt1) + constraintAnchor1.getMargin());
      return;
    } 
    if (paramConstraintWidget.mDimensionRatio != 0.0F && paramConstraintWidget.getDimensionBehaviour(paramInt1) == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
      paramInt2 = resolveDimensionRatio(paramConstraintWidget);
      i = (int)(paramConstraintWidget.mListAnchors[j].getResolutionNode()).resolvedOffset;
      (constraintAnchor2.getResolutionNode()).resolvedTarget = constraintAnchor1.getResolutionNode();
      (constraintAnchor2.getResolutionNode()).resolvedOffset = paramInt2;
      (constraintAnchor2.getResolutionNode()).state = 1;
      paramConstraintWidget.setFrame(i, i + paramInt2, paramInt1);
      return;
    } 
    int i = paramInt2 - paramConstraintWidget.getRelativePositioning(paramInt1);
    paramInt2 = i - paramConstraintWidget.getLength(paramInt1);
    paramConstraintWidget.setFrame(paramInt2, i, paramInt1);
    Optimizer.setOptimizedWidget(paramConstraintWidget, paramInt1, paramInt2);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/Analyzer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */