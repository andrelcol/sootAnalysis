package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.SolverVariable;

public class ConstraintAnchor {
  private int mConnectionCreator;
  
  int mGoneMargin = -1;
  
  public int mMargin = 0;
  
  final ConstraintWidget mOwner;
  
  private ResolutionAnchor mResolutionAnchor = new ResolutionAnchor(this);
  
  SolverVariable mSolverVariable;
  
  private Strength mStrength = Strength.NONE;
  
  ConstraintAnchor mTarget;
  
  final Type mType;
  
  public ConstraintAnchor(ConstraintWidget paramConstraintWidget, Type paramType) {
    ConnectionType connectionType = ConnectionType.RELAXED;
    this.mConnectionCreator = 0;
    this.mOwner = paramConstraintWidget;
    this.mType = paramType;
  }
  
  public boolean connect(ConstraintAnchor paramConstraintAnchor, int paramInt1, int paramInt2, Strength paramStrength, int paramInt3, boolean paramBoolean) {
    if (paramConstraintAnchor == null) {
      this.mTarget = null;
      this.mMargin = 0;
      this.mGoneMargin = -1;
      this.mStrength = Strength.NONE;
      this.mConnectionCreator = 2;
      return true;
    } 
    if (!paramBoolean && !isValidConnection(paramConstraintAnchor))
      return false; 
    this.mTarget = paramConstraintAnchor;
    if (paramInt1 > 0) {
      this.mMargin = paramInt1;
    } else {
      this.mMargin = 0;
    } 
    this.mGoneMargin = paramInt2;
    this.mStrength = paramStrength;
    this.mConnectionCreator = paramInt3;
    return true;
  }
  
  public boolean connect(ConstraintAnchor paramConstraintAnchor, int paramInt1, Strength paramStrength, int paramInt2) {
    return connect(paramConstraintAnchor, paramInt1, -1, paramStrength, paramInt2, false);
  }
  
  public int getConnectionCreator() {
    return this.mConnectionCreator;
  }
  
  public int getMargin() {
    if (this.mOwner.getVisibility() == 8)
      return 0; 
    if (this.mGoneMargin > -1) {
      ConstraintAnchor constraintAnchor = this.mTarget;
      if (constraintAnchor != null && constraintAnchor.mOwner.getVisibility() == 8)
        return this.mGoneMargin; 
    } 
    return this.mMargin;
  }
  
  public ConstraintWidget getOwner() {
    return this.mOwner;
  }
  
  public ResolutionAnchor getResolutionNode() {
    return this.mResolutionAnchor;
  }
  
  public SolverVariable getSolverVariable() {
    return this.mSolverVariable;
  }
  
  public Strength getStrength() {
    return this.mStrength;
  }
  
  public ConstraintAnchor getTarget() {
    return this.mTarget;
  }
  
  public Type getType() {
    return this.mType;
  }
  
  public boolean isConnected() {
    boolean bool;
    if (this.mTarget != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isValidConnection(ConstraintAnchor paramConstraintAnchor) {
    boolean bool2 = false;
    if (paramConstraintAnchor == null)
      return false; 
    Type type2 = paramConstraintAnchor.getType();
    Type type1 = this.mType;
    if (type2 == type1)
      return !(type1 == Type.BASELINE && (!paramConstraintAnchor.getOwner().hasBaseline() || !getOwner().hasBaseline())); 
    switch (type1) {
      default:
        throw new AssertionError(this.mType.name());
      case BASELINE:
      case CENTER_X:
      case CENTER_Y:
      case NONE:
        return false;
      case TOP:
      case BOTTOM:
        if (type2 == Type.TOP || type2 == Type.BOTTOM) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        bool2 = bool1;
        if (paramConstraintAnchor.getOwner() instanceof Guideline) {
          if (bool1 || type2 == Type.CENTER_Y)
            return true; 
          bool2 = false;
        } 
        return bool2;
      case LEFT:
      case RIGHT:
        if (type2 == Type.LEFT || type2 == Type.RIGHT) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        bool1 = bool2;
        if (paramConstraintAnchor.getOwner() instanceof Guideline) {
          if (bool2 || type2 == Type.CENTER_X)
            return true; 
          bool1 = false;
        } 
        return bool1;
      case CENTER:
        break;
    } 
    boolean bool1 = bool2;
    if (type2 != Type.BASELINE) {
      bool1 = bool2;
      if (type2 != Type.CENTER_X) {
        bool1 = bool2;
        if (type2 != Type.CENTER_Y)
          bool1 = true; 
      } 
    } 
    return bool1;
  }
  
  public void reset() {
    this.mTarget = null;
    this.mMargin = 0;
    this.mGoneMargin = -1;
    this.mStrength = Strength.STRONG;
    this.mConnectionCreator = 0;
    ConnectionType connectionType = ConnectionType.RELAXED;
    this.mResolutionAnchor.reset();
  }
  
  public void resetSolverVariable(Cache paramCache) {
    SolverVariable solverVariable = this.mSolverVariable;
    if (solverVariable == null) {
      this.mSolverVariable = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
    } else {
      solverVariable.reset();
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mOwner.getDebugName());
    stringBuilder.append(":");
    stringBuilder.append(this.mType.toString());
    return stringBuilder.toString();
  }
  
  public enum ConnectionType {
    RELAXED, STRICT;
    
    private static final ConnectionType[] $VALUES = new ConnectionType[] { RELAXED, STRICT };
    
    static {
    
    }
  }
  
  public enum Strength {
    NONE, STRONG, WEAK;
    
    private static final Strength[] $VALUES = new Strength[] { NONE, STRONG, WEAK };
    
    static {
    
    }
  }
  
  public enum Type {
    NONE, RIGHT, TOP, BASELINE, BOTTOM, CENTER, CENTER_X, CENTER_Y, LEFT;
    
    private static final Type[] $VALUES;
    
    static {
      RIGHT = new Type("RIGHT", 3);
      BOTTOM = new Type("BOTTOM", 4);
      BASELINE = new Type("BASELINE", 5);
      CENTER = new Type("CENTER", 6);
      CENTER_X = new Type("CENTER_X", 7);
      CENTER_Y = new Type("CENTER_Y", 8);
      $VALUES = new Type[] { NONE, LEFT, TOP, RIGHT, BOTTOM, BASELINE, CENTER, CENTER_X, CENTER_Y };
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/ConstraintAnchor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */