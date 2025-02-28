package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.SolverVariable;

public class ResolutionAnchor extends ResolutionNode {
  private ResolutionDimension dimension = null;
  
  private int dimensionMultiplier = 1;
  
  ConstraintAnchor myAnchor;
  
  float offset;
  
  private ResolutionAnchor opposite;
  
  private ResolutionDimension oppositeDimension = null;
  
  private int oppositeDimensionMultiplier = 1;
  
  float resolvedOffset;
  
  ResolutionAnchor resolvedTarget;
  
  ResolutionAnchor target;
  
  int type = 0;
  
  public ResolutionAnchor(ConstraintAnchor paramConstraintAnchor) {
    this.myAnchor = paramConstraintAnchor;
  }
  
  void addResolvedValue(LinearSystem paramLinearSystem) {
    SolverVariable solverVariable = this.myAnchor.getSolverVariable();
    ResolutionAnchor resolutionAnchor = this.resolvedTarget;
    if (resolutionAnchor == null) {
      paramLinearSystem.addEquality(solverVariable, (int)(this.resolvedOffset + 0.5F));
    } else {
      paramLinearSystem.addEquality(solverVariable, paramLinearSystem.createObjectVariable(resolutionAnchor.myAnchor), (int)(this.resolvedOffset + 0.5F), 6);
    } 
  }
  
  public void dependsOn(int paramInt1, ResolutionAnchor paramResolutionAnchor, int paramInt2) {
    this.type = paramInt1;
    this.target = paramResolutionAnchor;
    this.offset = paramInt2;
    this.target.addDependent(this);
  }
  
  public void dependsOn(ResolutionAnchor paramResolutionAnchor, int paramInt) {
    this.target = paramResolutionAnchor;
    this.offset = paramInt;
    this.target.addDependent(this);
  }
  
  public void dependsOn(ResolutionAnchor paramResolutionAnchor, int paramInt, ResolutionDimension paramResolutionDimension) {
    this.target = paramResolutionAnchor;
    this.target.addDependent(this);
    this.dimension = paramResolutionDimension;
    this.dimensionMultiplier = paramInt;
    this.dimension.addDependent(this);
  }
  
  public float getResolvedValue() {
    return this.resolvedOffset;
  }
  
  public void reset() {
    super.reset();
    this.target = null;
    this.offset = 0.0F;
    this.dimension = null;
    this.dimensionMultiplier = 1;
    this.oppositeDimension = null;
    this.oppositeDimensionMultiplier = 1;
    this.resolvedTarget = null;
    this.resolvedOffset = 0.0F;
    this.opposite = null;
    this.type = 0;
  }
  
  public void resolve() {
    int i = this.state;
    int j = 1;
    if (i == 1)
      return; 
    if (this.type == 4)
      return; 
    ResolutionDimension resolutionDimension = this.dimension;
    if (resolutionDimension != null) {
      if (resolutionDimension.state != 1)
        return; 
      this.offset = this.dimensionMultiplier * resolutionDimension.value;
    } 
    resolutionDimension = this.oppositeDimension;
    if (resolutionDimension != null) {
      if (resolutionDimension.state != 1)
        return; 
      float f = resolutionDimension.value;
    } 
    if (this.type == 1) {
      ResolutionAnchor resolutionAnchor = this.target;
      if (resolutionAnchor == null || resolutionAnchor.state == 1) {
        resolutionAnchor = this.target;
        if (resolutionAnchor == null) {
          this.resolvedTarget = this;
          this.resolvedOffset = this.offset;
        } else {
          this.resolvedTarget = resolutionAnchor.resolvedTarget;
          resolutionAnchor.resolvedOffset += this.offset;
        } 
        didResolve();
        return;
      } 
    } 
    if (this.type == 2) {
      ResolutionAnchor resolutionAnchor = this.target;
      if (resolutionAnchor != null && resolutionAnchor.state == 1) {
        resolutionAnchor = this.opposite;
        if (resolutionAnchor != null) {
          resolutionAnchor = resolutionAnchor.target;
          if (resolutionAnchor != null && resolutionAnchor.state == 1) {
            float f1;
            if (LinearSystem.getMetrics() != null) {
              Metrics metrics = LinearSystem.getMetrics();
              metrics.centerConnectionResolved++;
            } 
            this.resolvedTarget = this.target.resolvedTarget;
            resolutionAnchor = this.opposite;
            resolutionAnchor.resolvedTarget = resolutionAnchor.target.resolvedTarget;
            ConstraintAnchor.Type type2 = this.myAnchor.mType;
            ConstraintAnchor.Type type1 = ConstraintAnchor.Type.RIGHT;
            int k = 0;
            i = j;
            if (type2 != type1)
              if (type2 == ConstraintAnchor.Type.BOTTOM) {
                i = j;
              } else {
                i = 0;
              }  
            if (i != 0) {
              f1 = this.target.resolvedOffset;
              f2 = this.opposite.target.resolvedOffset;
            } else {
              f1 = this.opposite.target.resolvedOffset;
              f2 = this.target.resolvedOffset;
            } 
            f1 -= f2;
            ConstraintAnchor constraintAnchor = this.myAnchor;
            type2 = constraintAnchor.mType;
            if (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT) {
              f2 = f1 - this.myAnchor.mOwner.getWidth();
              f1 = this.myAnchor.mOwner.mHorizontalBiasPercent;
            } else {
              f2 = f1 - constraintAnchor.mOwner.getHeight();
              f1 = this.myAnchor.mOwner.mVerticalBiasPercent;
            } 
            int m = this.myAnchor.getMargin();
            j = this.opposite.myAnchor.getMargin();
            if (this.myAnchor.getTarget() == this.opposite.myAnchor.getTarget()) {
              f1 = 0.5F;
              j = 0;
            } else {
              k = m;
            } 
            float f3 = k;
            float f4 = j;
            float f2 = f2 - f3 - f4;
            if (i != 0) {
              ResolutionAnchor resolutionAnchor1 = this.opposite;
              resolutionAnchor1.resolvedOffset = resolutionAnchor1.target.resolvedOffset + f4 + f2 * f1;
              this.resolvedOffset = this.target.resolvedOffset - f3 - f2 * (1.0F - f1);
            } else {
              this.resolvedOffset = this.target.resolvedOffset + f3 + f2 * f1;
              ResolutionAnchor resolutionAnchor1 = this.opposite;
              resolutionAnchor1.resolvedOffset = resolutionAnchor1.target.resolvedOffset - f4 - f2 * (1.0F - f1);
            } 
            didResolve();
            this.opposite.didResolve();
            return;
          } 
        } 
      } 
    } 
    if (this.type == 3) {
      ResolutionAnchor resolutionAnchor = this.target;
      if (resolutionAnchor != null && resolutionAnchor.state == 1) {
        resolutionAnchor = this.opposite;
        if (resolutionAnchor != null) {
          resolutionAnchor = resolutionAnchor.target;
          if (resolutionAnchor != null && resolutionAnchor.state == 1) {
            if (LinearSystem.getMetrics() != null) {
              Metrics metrics = LinearSystem.getMetrics();
              metrics.matchConnectionResolved++;
            } 
            ResolutionAnchor resolutionAnchor1 = this.target;
            this.resolvedTarget = resolutionAnchor1.resolvedTarget;
            ResolutionAnchor resolutionAnchor2 = this.opposite;
            resolutionAnchor = resolutionAnchor2.target;
            resolutionAnchor2.resolvedTarget = resolutionAnchor.resolvedTarget;
            resolutionAnchor1.resolvedOffset += this.offset;
            resolutionAnchor.resolvedOffset += resolutionAnchor2.offset;
            didResolve();
            this.opposite.didResolve();
            return;
          } 
        } 
      } 
    } 
    if (this.type == 5)
      this.myAnchor.mOwner.resolve(); 
  }
  
  public void resolve(ResolutionAnchor paramResolutionAnchor, float paramFloat) {
    if (this.state == 0 || (this.resolvedTarget != paramResolutionAnchor && this.resolvedOffset != paramFloat)) {
      this.resolvedTarget = paramResolutionAnchor;
      this.resolvedOffset = paramFloat;
      if (this.state == 1)
        invalidate(); 
      didResolve();
    } 
  }
  
  String sType(int paramInt) {
    return (paramInt == 1) ? "DIRECT" : ((paramInt == 2) ? "CENTER" : ((paramInt == 3) ? "MATCH" : ((paramInt == 4) ? "CHAIN" : ((paramInt == 5) ? "BARRIER" : "UNCONNECTED"))));
  }
  
  public void setOpposite(ResolutionAnchor paramResolutionAnchor, float paramFloat) {
    this.opposite = paramResolutionAnchor;
  }
  
  public void setOpposite(ResolutionAnchor paramResolutionAnchor, int paramInt, ResolutionDimension paramResolutionDimension) {
    this.opposite = paramResolutionAnchor;
    this.oppositeDimension = paramResolutionDimension;
    this.oppositeDimensionMultiplier = paramInt;
  }
  
  public void setType(int paramInt) {
    this.type = paramInt;
  }
  
  public String toString() {
    if (this.state == 1) {
      if (this.resolvedTarget == this) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("[");
        stringBuilder2.append(this.myAnchor);
        stringBuilder2.append(", RESOLVED: ");
        stringBuilder2.append(this.resolvedOffset);
        stringBuilder2.append("]  type: ");
        stringBuilder2.append(sType(this.type));
        return stringBuilder2.toString();
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("[");
      stringBuilder1.append(this.myAnchor);
      stringBuilder1.append(", RESOLVED: ");
      stringBuilder1.append(this.resolvedTarget);
      stringBuilder1.append(":");
      stringBuilder1.append(this.resolvedOffset);
      stringBuilder1.append("] type: ");
      stringBuilder1.append(sType(this.type));
      return stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{ ");
    stringBuilder.append(this.myAnchor);
    stringBuilder.append(" UNRESOLVED} type: ");
    stringBuilder.append(sType(this.type));
    return stringBuilder.toString();
  }
  
  public void update() {
    // Byte code:
    //   0: aload_0
    //   1: getfield myAnchor : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   4: invokevirtual getTarget : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   7: astore_3
    //   8: aload_3
    //   9: ifnonnull -> 13
    //   12: return
    //   13: aload_3
    //   14: invokevirtual getTarget : ()Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   17: aload_0
    //   18: getfield myAnchor : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   21: if_acmpne -> 37
    //   24: aload_0
    //   25: iconst_4
    //   26: putfield type : I
    //   29: aload_3
    //   30: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   33: iconst_4
    //   34: putfield type : I
    //   37: aload_0
    //   38: getfield myAnchor : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   41: invokevirtual getMargin : ()I
    //   44: istore_2
    //   45: aload_0
    //   46: getfield myAnchor : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor;
    //   49: getfield mType : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   52: astore #4
    //   54: aload #4
    //   56: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.RIGHT : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   59: if_acmpeq -> 72
    //   62: iload_2
    //   63: istore_1
    //   64: aload #4
    //   66: getstatic androidx/constraintlayout/solver/widgets/ConstraintAnchor$Type.BOTTOM : Landroidx/constraintlayout/solver/widgets/ConstraintAnchor$Type;
    //   69: if_acmpne -> 75
    //   72: iload_2
    //   73: ineg
    //   74: istore_1
    //   75: aload_0
    //   76: aload_3
    //   77: invokevirtual getResolutionNode : ()Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;
    //   80: iload_1
    //   81: invokevirtual dependsOn : (Landroidx/constraintlayout/solver/widgets/ResolutionAnchor;I)V
    //   84: return
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/ResolutionAnchor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */