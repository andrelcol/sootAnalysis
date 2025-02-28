package androidx.constraintlayout.solver.widgets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConstraintWidgetGroup {
  public List<ConstraintWidget> mConstrainedGroup;
  
  public final int[] mGroupDimensions = new int[] { this.mGroupWidth, this.mGroupHeight };
  
  int mGroupHeight = -1;
  
  int mGroupWidth = -1;
  
  public boolean mSkipSolver = false;
  
  List<ConstraintWidget> mStartHorizontalWidgets = new ArrayList<ConstraintWidget>();
  
  List<ConstraintWidget> mStartVerticalWidgets = new ArrayList<ConstraintWidget>();
  
  List<ConstraintWidget> mUnresolvedWidgets = new ArrayList<ConstraintWidget>();
  
  HashSet<ConstraintWidget> mWidgetsToSetHorizontal = new HashSet<ConstraintWidget>();
  
  HashSet<ConstraintWidget> mWidgetsToSetVertical = new HashSet<ConstraintWidget>();
  
  List<ConstraintWidget> mWidgetsToSolve = new ArrayList<ConstraintWidget>();
  
  ConstraintWidgetGroup(List<ConstraintWidget> paramList) {
    this.mConstrainedGroup = paramList;
  }
  
  ConstraintWidgetGroup(List<ConstraintWidget> paramList, boolean paramBoolean) {
    this.mConstrainedGroup = paramList;
    this.mSkipSolver = paramBoolean;
  }
  
  private void getWidgetsToSolveTraversal(ArrayList<ConstraintWidget> paramArrayList, ConstraintWidget paramConstraintWidget) {
    if (paramConstraintWidget.mGroupsToSolver)
      return; 
    paramArrayList.add(paramConstraintWidget);
    paramConstraintWidget.mGroupsToSolver = true;
    if (paramConstraintWidget.isFullyResolved())
      return; 
    boolean bool = paramConstraintWidget instanceof Helper;
    byte b2 = 0;
    if (bool) {
      Helper helper = (Helper)paramConstraintWidget;
      int j = helper.mWidgetsCount;
      for (byte b = 0; b < j; b++)
        getWidgetsToSolveTraversal(paramArrayList, helper.mWidgets[b]); 
    } 
    int i = paramConstraintWidget.mListAnchors.length;
    for (byte b1 = b2; b1 < i; b1++) {
      ConstraintAnchor constraintAnchor = (paramConstraintWidget.mListAnchors[b1]).mTarget;
      if (constraintAnchor != null) {
        ConstraintWidget constraintWidget = constraintAnchor.mOwner;
        if (constraintAnchor != null && constraintWidget != paramConstraintWidget.getParent())
          getWidgetsToSolveTraversal(paramArrayList, constraintWidget); 
      } 
    } 
  }
  
  private void updateResolvedDimension(ConstraintWidget paramConstraintWidget) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mOptimizerMeasurable : Z
    //   4: ifeq -> 442
    //   7: aload_1
    //   8: invokevirtual isFullyResolved : ()Z
    //   11: ifeq -> 15
    //   14: return
    //   15: aload_1
    //   16: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   19: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   22: astore #5
    //   24: iconst_0
    //   25: istore #4
    //   27: aload #5
    //   29: ifnull -> 37
    //   32: iconst_1
    //   33: istore_3
    //   34: goto -> 39
    //   37: iconst_0
    //   38: istore_3
    //   39: iload_3
    //   40: ifeq -> 55
    //   43: aload_1
    //   44: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   47: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   50: astore #5
    //   52: goto -> 64
    //   55: aload_1
    //   56: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   59: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   62: astore #5
    //   64: aload #5
    //   66: ifnull -> 149
    //   69: aload #5
    //   71: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   74: astore #6
    //   76: aload #6
    //   78: getfield mOptimizerMeasured : Z
    //   81: ifne -> 90
    //   84: aload_0
    //   85: aload #6
    //   87: invokespecial updateResolvedDimension : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;)V
    //   90: aload #5
    //   92: getfield mType : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   95: astore #6
    //   97: aload #6
    //   99: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   102: if_acmpne -> 129
    //   105: aload #5
    //   107: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   110: astore #5
    //   112: aload #5
    //   114: getfield mX : I
    //   117: istore_2
    //   118: aload #5
    //   120: invokevirtual getWidth : ()I
    //   123: iload_2
    //   124: iadd
    //   125: istore_2
    //   126: goto -> 151
    //   129: aload #6
    //   131: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.LEFT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   134: if_acmpne -> 149
    //   137: aload #5
    //   139: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   142: getfield mX : I
    //   145: istore_2
    //   146: goto -> 151
    //   149: iconst_0
    //   150: istore_2
    //   151: iload_3
    //   152: ifeq -> 168
    //   155: iload_2
    //   156: aload_1
    //   157: getfield mRight : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   160: invokevirtual getMargin : ()I
    //   163: isub
    //   164: istore_2
    //   165: goto -> 183
    //   168: iload_2
    //   169: aload_1
    //   170: getfield mLeft : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   173: invokevirtual getMargin : ()I
    //   176: aload_1
    //   177: invokevirtual getWidth : ()I
    //   180: iadd
    //   181: iadd
    //   182: istore_2
    //   183: aload_1
    //   184: iload_2
    //   185: aload_1
    //   186: invokevirtual getWidth : ()I
    //   189: isub
    //   190: iload_2
    //   191: invokevirtual setHorizontalDimension : (II)V
    //   194: aload_1
    //   195: getfield mBaseline : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   198: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   201: astore #5
    //   203: aload #5
    //   205: ifnull -> 270
    //   208: aload #5
    //   210: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   213: astore #6
    //   215: aload #6
    //   217: getfield mOptimizerMeasured : Z
    //   220: ifne -> 229
    //   223: aload_0
    //   224: aload #6
    //   226: invokespecial updateResolvedDimension : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;)V
    //   229: aload #5
    //   231: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   234: astore #5
    //   236: aload #5
    //   238: getfield mY : I
    //   241: aload #5
    //   243: getfield mBaselineDistance : I
    //   246: iadd
    //   247: aload_1
    //   248: getfield mBaselineDistance : I
    //   251: isub
    //   252: istore_2
    //   253: aload_1
    //   254: iload_2
    //   255: aload_1
    //   256: getfield mHeight : I
    //   259: iload_2
    //   260: iadd
    //   261: invokevirtual setVerticalDimension : (II)V
    //   264: aload_1
    //   265: iconst_1
    //   266: putfield mOptimizerMeasured : Z
    //   269: return
    //   270: aload_1
    //   271: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   274: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   277: ifnull -> 283
    //   280: iconst_1
    //   281: istore #4
    //   283: iload #4
    //   285: ifeq -> 300
    //   288: aload_1
    //   289: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   292: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   295: astore #5
    //   297: goto -> 309
    //   300: aload_1
    //   301: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   304: getfield mTarget : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   307: astore #5
    //   309: iload_2
    //   310: istore_3
    //   311: aload #5
    //   313: ifnull -> 393
    //   316: aload #5
    //   318: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   321: astore #6
    //   323: aload #6
    //   325: getfield mOptimizerMeasured : Z
    //   328: ifne -> 337
    //   331: aload_0
    //   332: aload #6
    //   334: invokespecial updateResolvedDimension : (Landroidx/constraintlayout/solver/widgets/ConstraintWidget;)V
    //   337: aload #5
    //   339: getfield mType : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   342: astore #6
    //   344: aload #6
    //   346: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   349: if_acmpne -> 374
    //   352: aload #5
    //   354: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   357: astore #5
    //   359: aload #5
    //   361: getfield mY : I
    //   364: aload #5
    //   366: invokevirtual getHeight : ()I
    //   369: iadd
    //   370: istore_3
    //   371: goto -> 393
    //   374: iload_2
    //   375: istore_3
    //   376: aload #6
    //   378: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.TOP : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   381: if_acmpne -> 393
    //   384: aload #5
    //   386: getfield mOwner : Landroidx/constraintlayout/solver/widgets/ConstraintWidget;
    //   389: getfield mY : I
    //   392: istore_3
    //   393: iload #4
    //   395: ifeq -> 411
    //   398: iload_3
    //   399: aload_1
    //   400: getfield mBottom : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   403: invokevirtual getMargin : ()I
    //   406: isub
    //   407: istore_2
    //   408: goto -> 426
    //   411: iload_3
    //   412: aload_1
    //   413: getfield mTop : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   416: invokevirtual getMargin : ()I
    //   419: aload_1
    //   420: invokevirtual getHeight : ()I
    //   423: iadd
    //   424: iadd
    //   425: istore_2
    //   426: aload_1
    //   427: iload_2
    //   428: aload_1
    //   429: invokevirtual getHeight : ()I
    //   432: isub
    //   433: iload_2
    //   434: invokevirtual setVerticalDimension : (II)V
    //   437: aload_1
    //   438: iconst_1
    //   439: putfield mOptimizerMeasured : Z
    //   442: return
  }
  
  void addWidgetsToSet(ConstraintWidget paramConstraintWidget, int paramInt) {
    if (paramInt == 0) {
      this.mWidgetsToSetHorizontal.add(paramConstraintWidget);
    } else if (paramInt == 1) {
      this.mWidgetsToSetVertical.add(paramConstraintWidget);
    } 
  }
  
  public List<ConstraintWidget> getStartWidgets(int paramInt) {
    return (paramInt == 0) ? this.mStartHorizontalWidgets : ((paramInt == 1) ? this.mStartVerticalWidgets : null);
  }
  
  Set<ConstraintWidget> getWidgetsToSet(int paramInt) {
    return (paramInt == 0) ? this.mWidgetsToSetHorizontal : ((paramInt == 1) ? this.mWidgetsToSetVertical : null);
  }
  
  List<ConstraintWidget> getWidgetsToSolve() {
    if (!this.mWidgetsToSolve.isEmpty())
      return this.mWidgetsToSolve; 
    int i = this.mConstrainedGroup.size();
    for (byte b = 0; b < i; b++) {
      ConstraintWidget constraintWidget = this.mConstrainedGroup.get(b);
      if (!constraintWidget.mOptimizerMeasurable)
        getWidgetsToSolveTraversal((ArrayList<ConstraintWidget>)this.mWidgetsToSolve, constraintWidget); 
    } 
    this.mUnresolvedWidgets.clear();
    this.mUnresolvedWidgets.addAll(this.mConstrainedGroup);
    this.mUnresolvedWidgets.removeAll(this.mWidgetsToSolve);
    return this.mWidgetsToSolve;
  }
  
  void updateUnresolvedWidgets() {
    int i = this.mUnresolvedWidgets.size();
    for (byte b = 0; b < i; b++)
      updateResolvedDimension(this.mUnresolvedWidgets.get(b)); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/ConstraintWidgetGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */