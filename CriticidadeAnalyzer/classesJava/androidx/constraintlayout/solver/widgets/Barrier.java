package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;

public class Barrier extends Helper {
  private boolean mAllowsGoneWidget = true;
  
  private int mBarrierType = 0;
  
  private ArrayList<ResolutionAnchor> mNodes = new ArrayList<ResolutionAnchor>(4);
  
  public void addToSolver(LinearSystem paramLinearSystem) {
    ConstraintAnchor[] arrayOfConstraintAnchor = this.mListAnchors;
    arrayOfConstraintAnchor[0] = this.mLeft;
    arrayOfConstraintAnchor[2] = this.mTop;
    arrayOfConstraintAnchor[1] = this.mRight;
    arrayOfConstraintAnchor[3] = this.mBottom;
    for (int i = 0;; i++) {
      arrayOfConstraintAnchor = this.mListAnchors;
      if (i < arrayOfConstraintAnchor.length)
        continue; 
      i = this.mBarrierType;
      if (i >= 0 && i < 4) {
        boolean bool;
        ConstraintAnchor constraintAnchor = arrayOfConstraintAnchor[i];
        i = 0;
        while (true)
          i++; 
        i = this.mBarrierType;
        if ((i == 1) ? (getParent().getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) : (getParent().getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT))
          bool = false; 
        for (i = 0; i < this.mWidgetsCount; i++) {
          ConstraintWidget constraintWidget = this.mWidgets[i];
          if (this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) {
            SolverVariable solverVariable = paramLinearSystem.createObjectVariable(constraintWidget.mListAnchors[this.mBarrierType]);
            ConstraintAnchor[] arrayOfConstraintAnchor1 = constraintWidget.mListAnchors;
            int j = this.mBarrierType;
            (arrayOfConstraintAnchor1[j]).mSolverVariable = solverVariable;
            if (j == 0 || j == 2) {
              paramLinearSystem.addLowerBarrier(constraintAnchor.mSolverVariable, solverVariable, bool);
            } else {
              paramLinearSystem.addGreaterBarrier(constraintAnchor.mSolverVariable, solverVariable, bool);
            } 
          } 
        } 
        i = this.mBarrierType;
        if (i == 0) {
          paramLinearSystem.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 6);
          if (!bool)
            paramLinearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 5); 
        } else if (i == 1) {
          paramLinearSystem.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 6);
          if (!bool)
            paramLinearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 5); 
        } else if (i == 2) {
          paramLinearSystem.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 6);
          if (!bool)
            paramLinearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 5); 
        } else if (i == 3) {
          paramLinearSystem.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 6);
          if (!bool)
            paramLinearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 5); 
        } 
      } 
      return;
      (arrayOfConstraintAnchor[i]).mSolverVariable = paramLinearSystem.createObjectVariable(arrayOfConstraintAnchor[i]);
    } 
  }
  
  public boolean allowedInBarrier() {
    return true;
  }
  
  public void analyze(int paramInt) {
    ResolutionAnchor resolutionAnchor;
    ConstraintWidget constraintWidget = this.mParent;
    if (constraintWidget == null)
      return; 
    if (!((ConstraintWidgetContainer)constraintWidget).optimizeFor(2))
      return; 
    paramInt = this.mBarrierType;
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3)
            return; 
          resolutionAnchor = this.mBottom.getResolutionNode();
        } else {
          resolutionAnchor = this.mTop.getResolutionNode();
        } 
      } else {
        resolutionAnchor = this.mRight.getResolutionNode();
      } 
    } else {
      resolutionAnchor = this.mLeft.getResolutionNode();
    } 
    resolutionAnchor.setType(5);
    paramInt = this.mBarrierType;
    if (paramInt == 0 || paramInt == 1) {
      this.mTop.getResolutionNode().resolve(null, 0.0F);
      this.mBottom.getResolutionNode().resolve(null, 0.0F);
    } else {
      this.mLeft.getResolutionNode().resolve(null, 0.0F);
      this.mRight.getResolutionNode().resolve(null, 0.0F);
    } 
    this.mNodes.clear();
    for (paramInt = 0; paramInt < this.mWidgetsCount; paramInt++) {
      constraintWidget = this.mWidgets[paramInt];
      if (this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) {
        ResolutionAnchor resolutionAnchor1;
        int i = this.mBarrierType;
        if (i != 0) {
          if (i != 1) {
            if (i != 2) {
              if (i != 3) {
                constraintWidget = null;
              } else {
                resolutionAnchor1 = constraintWidget.mBottom.getResolutionNode();
              } 
            } else {
              resolutionAnchor1 = ((ConstraintWidget)resolutionAnchor1).mTop.getResolutionNode();
            } 
          } else {
            resolutionAnchor1 = ((ConstraintWidget)resolutionAnchor1).mRight.getResolutionNode();
          } 
        } else {
          resolutionAnchor1 = ((ConstraintWidget)resolutionAnchor1).mLeft.getResolutionNode();
        } 
        if (resolutionAnchor1 != null) {
          this.mNodes.add(resolutionAnchor1);
          resolutionAnchor1.addDependent(resolutionAnchor);
        } 
      } 
    } 
  }
  
  public void resetResolutionNodes() {
    super.resetResolutionNodes();
    this.mNodes.clear();
  }
  
  public void resolve() {
    int i = this.mBarrierType;
    float f = Float.MAX_VALUE;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3)
            return; 
          ResolutionAnchor resolutionAnchor = this.mBottom.getResolutionNode();
        } else {
          ResolutionAnchor resolutionAnchor = this.mTop.getResolutionNode();
          int k = this.mNodes.size();
          Object object1 = null;
          i = 0;
        } 
      } else {
        ResolutionAnchor resolutionAnchor = this.mRight.getResolutionNode();
      } 
      f = 0.0F;
    } else {
      ResolutionAnchor resolutionAnchor = this.mLeft.getResolutionNode();
    } 
    int j = this.mNodes.size();
    Object object = null;
    i = 0;
  }
  
  public void setAllowsGoneWidget(boolean paramBoolean) {
    this.mAllowsGoneWidget = paramBoolean;
  }
  
  public void setBarrierType(int paramInt) {
    this.mBarrierType = paramInt;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/Barrier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */