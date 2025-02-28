package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstraintWidgetContainer extends WidgetContainer {
  public boolean mGroupsWrapOptimized = false;
  
  private boolean mHeightMeasuredTooSmall = false;
  
  ChainHead[] mHorizontalChainsArray = new ChainHead[4];
  
  int mHorizontalChainsSize = 0;
  
  public boolean mHorizontalWrapOptimized = false;
  
  private boolean mIsRtl = false;
  
  private int mOptimizationLevel = 7;
  
  int mPaddingBottom;
  
  int mPaddingLeft;
  
  int mPaddingRight;
  
  int mPaddingTop;
  
  public boolean mSkipSolver = false;
  
  private Snapshot mSnapshot;
  
  protected LinearSystem mSystem = new LinearSystem();
  
  ChainHead[] mVerticalChainsArray = new ChainHead[4];
  
  int mVerticalChainsSize = 0;
  
  public boolean mVerticalWrapOptimized = false;
  
  public List<ConstraintWidgetGroup> mWidgetGroups = new ArrayList<ConstraintWidgetGroup>();
  
  private boolean mWidthMeasuredTooSmall = false;
  
  public int mWrapFixedHeight = 0;
  
  public int mWrapFixedWidth = 0;
  
  private void addHorizontalChain(ConstraintWidget paramConstraintWidget) {
    int i = this.mHorizontalChainsSize;
    ChainHead[] arrayOfChainHead = this.mHorizontalChainsArray;
    if (i + 1 >= arrayOfChainHead.length)
      this.mHorizontalChainsArray = Arrays.<ChainHead>copyOf(arrayOfChainHead, arrayOfChainHead.length * 2); 
    this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(paramConstraintWidget, 0, isRtl());
    this.mHorizontalChainsSize++;
  }
  
  private void addVerticalChain(ConstraintWidget paramConstraintWidget) {
    int i = this.mVerticalChainsSize;
    ChainHead[] arrayOfChainHead = this.mVerticalChainsArray;
    if (i + 1 >= arrayOfChainHead.length)
      this.mVerticalChainsArray = Arrays.<ChainHead>copyOf(arrayOfChainHead, arrayOfChainHead.length * 2); 
    this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(paramConstraintWidget, 1, isRtl());
    this.mVerticalChainsSize++;
  }
  
  private void resetChains() {
    this.mHorizontalChainsSize = 0;
    this.mVerticalChainsSize = 0;
  }
  
  void addChain(ConstraintWidget paramConstraintWidget, int paramInt) {
    if (paramInt == 0) {
      addHorizontalChain(paramConstraintWidget);
    } else if (paramInt == 1) {
      addVerticalChain(paramConstraintWidget);
    } 
  }
  
  public boolean addChildrenToSolver(LinearSystem paramLinearSystem) {
    addToSolver(paramLinearSystem);
    int i = this.mChildren.size();
    for (byte b = 0; b < i; b++) {
      ConstraintWidget constraintWidget = this.mChildren.get(b);
      if (constraintWidget instanceof ConstraintWidgetContainer) {
        ConstraintWidget.DimensionBehaviour[] arrayOfDimensionBehaviour = constraintWidget.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour1 = arrayOfDimensionBehaviour[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = arrayOfDimensionBehaviour[1];
        if (dimensionBehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
          constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED); 
        if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
          constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED); 
        constraintWidget.addToSolver(paramLinearSystem);
        if (dimensionBehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
          constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour1); 
        if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
          constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2); 
      } else {
        Optimizer.checkMatchParent(this, paramLinearSystem, constraintWidget);
        constraintWidget.addToSolver(paramLinearSystem);
      } 
    } 
    if (this.mHorizontalChainsSize > 0)
      Chain.applyChainConstraints(this, paramLinearSystem, 0); 
    if (this.mVerticalChainsSize > 0)
      Chain.applyChainConstraints(this, paramLinearSystem, 1); 
    return true;
  }
  
  public void analyze(int paramInt) {
    super.analyze(paramInt);
    int i = this.mChildren.size();
    for (byte b = 0; b < i; b++)
      ((ConstraintWidget)this.mChildren.get(b)).analyze(paramInt); 
  }
  
  public int getOptimizationLevel() {
    return this.mOptimizationLevel;
  }
  
  public boolean handlesInternalConstraints() {
    return false;
  }
  
  public boolean isHeightMeasuredTooSmall() {
    return this.mHeightMeasuredTooSmall;
  }
  
  public boolean isRtl() {
    return this.mIsRtl;
  }
  
  public boolean isWidthMeasuredTooSmall() {
    return this.mWidthMeasuredTooSmall;
  }
  
  public void layout() {
    boolean bool;
    int n = this.mX;
    int m = this.mY;
    int k = Math.max(0, getWidth());
    int i1 = Math.max(0, getHeight());
    this.mWidthMeasuredTooSmall = false;
    this.mHeightMeasuredTooSmall = false;
    if (this.mParent != null) {
      if (this.mSnapshot == null)
        this.mSnapshot = new Snapshot(this); 
      this.mSnapshot.updateFrom(this);
      setX(this.mPaddingLeft);
      setY(this.mPaddingTop);
      resetAnchors();
      resetSolverVariables(this.mSystem.getCache());
    } else {
      this.mX = 0;
      this.mY = 0;
    } 
    if (this.mOptimizationLevel != 0) {
      if (!optimizeFor(8))
        optimizeReset(); 
      if (!optimizeFor(32))
        optimize(); 
      this.mSystem.graphOptimizer = true;
    } else {
      this.mSystem.graphOptimizer = false;
    } 
    ConstraintWidget.DimensionBehaviour[] arrayOfDimensionBehaviour = this.mListDimensionBehaviors;
    ConstraintWidget.DimensionBehaviour dimensionBehaviour1 = arrayOfDimensionBehaviour[1];
    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = arrayOfDimensionBehaviour[0];
    resetChains();
    if (this.mWidgetGroups.size() == 0) {
      this.mWidgetGroups.clear();
      this.mWidgetGroups.add(0, new ConstraintWidgetGroup(this.mChildren));
    } 
    int i = this.mWidgetGroups.size();
    ArrayList<ConstraintWidget> arrayList = this.mChildren;
    if (getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
      bool = true;
    } else {
      bool = false;
    } 
    int j = 0;
    for (byte b = 0; b < i && !this.mSkipSolver; b++) {
      if (!((ConstraintWidgetGroup)this.mWidgetGroups.get(b)).mSkipSolver) {
        if (optimizeFor(32))
          if (getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED && getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
            this.mChildren = (ArrayList<ConstraintWidget>)((ConstraintWidgetGroup)this.mWidgetGroups.get(b)).getWidgetsToSolve();
          } else {
            this.mChildren = (ArrayList<ConstraintWidget>)((ConstraintWidgetGroup)this.mWidgetGroups.get(b)).mConstrainedGroup;
          }  
        resetChains();
        int i4 = this.mChildren.size();
        int i2;
        for (i2 = 0; i2 < i4; i2++) {
          ConstraintWidget constraintWidget = this.mChildren.get(i2);
          if (constraintWidget instanceof WidgetContainer)
            ((WidgetContainer)constraintWidget).layout(); 
        } 
        i2 = j;
        int i3 = 0;
        boolean bool1 = true;
        j = i;
        i = i2;
        i2 = i3;
        label152: while (bool1) {
          i3 = i2 + 1;
          i2 = i;
          try {
            this.mSystem.reset();
            i2 = i;
            resetChains();
            i2 = i;
            createObjectVariables(this.mSystem);
            byte b1 = 0;
            while (true) {
              if (b1 < i4) {
                i2 = i;
                ConstraintWidget constraintWidget = this.mChildren.get(b1);
                try {
                  constraintWidget.createObjectVariables(this.mSystem);
                  b1++;
                } catch (Exception exception) {
                  continue label152;
                } 
                continue;
              } 
              i2 = i;
              boolean bool3 = addChildrenToSolver(this.mSystem);
              if (bool3)
                try {
                  this.mSystem.minimize();
                } catch (Exception exception) {
                  bool1 = bool3;
                  i = i2;
                  continue label152;
                }  
              bool1 = bool3;
              i = i2;
              break;
            } 
          } catch (Exception exception) {
            i = i2;
            exception.printStackTrace();
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("EXCEPTION : ");
            stringBuilder.append(exception);
            printStream.println(stringBuilder.toString());
          } 
          if (bool1) {
            updateChildrenFromSolver(this.mSystem, Optimizer.flags);
          } else {
            updateFromSolver(this.mSystem);
            for (i2 = 0; i2 < i4; i2++) {
              ConstraintWidget constraintWidget = this.mChildren.get(i2);
              if (constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                Optimizer.flags[2] = true;
                break;
              } 
              if (constraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                Optimizer.flags[2] = true;
                break;
              } 
            } 
          } 
          if (bool && i3 < 8 && Optimizer.flags[2]) {
            boolean bool3 = false;
            int i7 = 0;
            int i6 = 0;
            i2 = i3;
            for (i3 = bool3; i3 < i4; i3++) {
              ConstraintWidget constraintWidget = this.mChildren.get(i3);
              i7 = Math.max(i7, constraintWidget.mX + constraintWidget.getWidth());
              i6 = Math.max(i6, constraintWidget.mY + constraintWidget.getHeight());
            } 
            i7 = Math.max(this.mMinWidth, i7);
            i3 = Math.max(this.mMinHeight, i6);
            if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && getWidth() < i7) {
              setWidth(i7);
              this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
              bool1 = true;
              i = 1;
            } else {
              bool1 = false;
            } 
            if (dimensionBehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && getHeight() < i3) {
              setHeight(i3);
              this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
              bool1 = true;
              i = 1;
            } 
          } else {
            i2 = i3;
            bool1 = false;
          } 
          i3 = Math.max(this.mMinWidth, getWidth());
          if (i3 > getWidth()) {
            setWidth(i3);
            this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.FIXED;
            bool1 = true;
            i = 1;
          } 
          i3 = Math.max(this.mMinHeight, getHeight());
          if (i3 > getHeight()) {
            setHeight(i3);
            this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.FIXED;
            bool1 = true;
            i = 1;
          } 
          boolean bool2 = bool1;
          int i5 = i;
          if (i == 0) {
            boolean bool3 = bool1;
            i3 = i;
            if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
              bool3 = bool1;
              i3 = i;
              if (k > 0) {
                bool3 = bool1;
                i3 = i;
                if (getWidth() > k) {
                  this.mWidthMeasuredTooSmall = true;
                  this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                  setWidth(k);
                  bool3 = true;
                  i3 = 1;
                } 
              } 
            } 
            bool2 = bool3;
            i5 = i3;
            if (this.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
              bool2 = bool3;
              i5 = i3;
              if (i1 > 0) {
                bool2 = bool3;
                i5 = i3;
                if (getHeight() > i1) {
                  this.mHeightMeasuredTooSmall = true;
                  this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.FIXED;
                  setHeight(i1);
                  bool1 = true;
                  i = 1;
                  continue;
                } 
              } 
            } 
          } 
          bool1 = bool2;
          i = i5;
        } 
        ((ConstraintWidgetGroup)this.mWidgetGroups.get(b)).updateUnresolvedWidgets();
        i2 = i;
        i = j;
        j = i2;
      } 
    } 
    this.mChildren = arrayList;
    if (this.mParent != null) {
      int i2 = Math.max(this.mMinWidth, getWidth());
      i = Math.max(this.mMinHeight, getHeight());
      this.mSnapshot.applyTo(this);
      setWidth(i2 + this.mPaddingLeft + this.mPaddingRight);
      setHeight(i + this.mPaddingTop + this.mPaddingBottom);
    } else {
      this.mX = n;
      this.mY = m;
    } 
    if (j != 0) {
      arrayOfDimensionBehaviour = this.mListDimensionBehaviors;
      arrayOfDimensionBehaviour[0] = dimensionBehaviour2;
      arrayOfDimensionBehaviour[1] = dimensionBehaviour1;
    } 
    resetSolverVariables(this.mSystem.getCache());
    if (this == getRootConstraintContainer())
      updateDrawPosition(); 
  }
  
  public void optimize() {
    if (!optimizeFor(8))
      analyze(this.mOptimizationLevel); 
    solveGraph();
  }
  
  public boolean optimizeFor(int paramInt) {
    boolean bool;
    if ((this.mOptimizationLevel & paramInt) == paramInt) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void optimizeForDimensions(int paramInt1, int paramInt2) {
    if (this.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
      ResolutionDimension resolutionDimension = this.mResolutionWidth;
      if (resolutionDimension != null)
        resolutionDimension.resolve(paramInt1); 
    } 
    if (this.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
      ResolutionDimension resolutionDimension = this.mResolutionHeight;
      if (resolutionDimension != null)
        resolutionDimension.resolve(paramInt2); 
    } 
  }
  
  public void optimizeReset() {
    int i = this.mChildren.size();
    resetResolutionNodes();
    for (byte b = 0; b < i; b++)
      ((ConstraintWidget)this.mChildren.get(b)).resetResolutionNodes(); 
  }
  
  public void preOptimize() {
    optimizeReset();
    analyze(this.mOptimizationLevel);
  }
  
  public void reset() {
    this.mSystem.reset();
    this.mPaddingLeft = 0;
    this.mPaddingRight = 0;
    this.mPaddingTop = 0;
    this.mPaddingBottom = 0;
    this.mWidgetGroups.clear();
    this.mSkipSolver = false;
    super.reset();
  }
  
  public void setOptimizationLevel(int paramInt) {
    this.mOptimizationLevel = paramInt;
  }
  
  public void setRtl(boolean paramBoolean) {
    this.mIsRtl = paramBoolean;
  }
  
  public void solveGraph() {
    ResolutionAnchor resolutionAnchor1 = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
    ResolutionAnchor resolutionAnchor2 = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
    resolutionAnchor1.resolve(null, 0.0F);
    resolutionAnchor2.resolve(null, 0.0F);
  }
  
  public void updateChildrenFromSolver(LinearSystem paramLinearSystem, boolean[] paramArrayOfboolean) {
    paramArrayOfboolean[2] = false;
    updateFromSolver(paramLinearSystem);
    int i = this.mChildren.size();
    for (byte b = 0; b < i; b++) {
      ConstraintWidget constraintWidget = this.mChildren.get(b);
      constraintWidget.updateFromSolver(paramLinearSystem);
      if (constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth())
        paramArrayOfboolean[2] = true; 
      if (constraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight())
        paramArrayOfboolean[2] = true; 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/ConstraintWidgetContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */