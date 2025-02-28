package androidx.constraintlayout.solver;

public class ArrayRow implements LinearSystem.Row {
  float constantValue = 0.0F;
  
  boolean isSimpleDefinition = false;
  
  boolean used;
  
  SolverVariable variable = null;
  
  public final ArrayLinkedVariables variables;
  
  public ArrayRow(Cache paramCache) {
    this.variables = new ArrayLinkedVariables(this, paramCache);
  }
  
  public ArrayRow addError(LinearSystem paramLinearSystem, int paramInt) {
    this.variables.put(paramLinearSystem.createErrorVariable(paramInt, "ep"), 1.0F);
    this.variables.put(paramLinearSystem.createErrorVariable(paramInt, "em"), -1.0F);
    return this;
  }
  
  public void addError(SolverVariable paramSolverVariable) {
    int i = paramSolverVariable.strength;
    float f = 1.0F;
    if (i != 1)
      if (i == 2) {
        f = 1000.0F;
      } else if (i == 3) {
        f = 1000000.0F;
      } else if (i == 4) {
        f = 1.0E9F;
      } else if (i == 5) {
        f = 1.0E12F;
      }  
    this.variables.put(paramSolverVariable, f);
  }
  
  ArrayRow addSingleError(SolverVariable paramSolverVariable, int paramInt) {
    this.variables.put(paramSolverVariable, paramInt);
    return this;
  }
  
  boolean chooseSubject(LinearSystem paramLinearSystem) {
    boolean bool;
    SolverVariable solverVariable = this.variables.chooseSubject(paramLinearSystem);
    if (solverVariable == null) {
      bool = true;
    } else {
      pivot(solverVariable);
      bool = false;
    } 
    if (this.variables.currentSize == 0)
      this.isSimpleDefinition = true; 
    return bool;
  }
  
  public void clear() {
    this.variables.clear();
    this.variable = null;
    this.constantValue = 0.0F;
  }
  
  ArrayRow createRowCentering(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, float paramFloat, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, int paramInt2) {
    if (paramSolverVariable2 == paramSolverVariable3) {
      this.variables.put(paramSolverVariable1, 1.0F);
      this.variables.put(paramSolverVariable4, 1.0F);
      this.variables.put(paramSolverVariable2, -2.0F);
      return this;
    } 
    if (paramFloat == 0.5F) {
      this.variables.put(paramSolverVariable1, 1.0F);
      this.variables.put(paramSolverVariable2, -1.0F);
      this.variables.put(paramSolverVariable3, -1.0F);
      this.variables.put(paramSolverVariable4, 1.0F);
      if (paramInt1 > 0 || paramInt2 > 0)
        this.constantValue = (-paramInt1 + paramInt2); 
    } else if (paramFloat <= 0.0F) {
      this.variables.put(paramSolverVariable1, -1.0F);
      this.variables.put(paramSolverVariable2, 1.0F);
      this.constantValue = paramInt1;
    } else if (paramFloat >= 1.0F) {
      this.variables.put(paramSolverVariable3, -1.0F);
      this.variables.put(paramSolverVariable4, 1.0F);
      this.constantValue = paramInt2;
    } else {
      ArrayLinkedVariables arrayLinkedVariables = this.variables;
      float f = 1.0F - paramFloat;
      arrayLinkedVariables.put(paramSolverVariable1, f * 1.0F);
      this.variables.put(paramSolverVariable2, f * -1.0F);
      this.variables.put(paramSolverVariable3, -1.0F * paramFloat);
      this.variables.put(paramSolverVariable4, 1.0F * paramFloat);
      if (paramInt1 > 0 || paramInt2 > 0)
        this.constantValue = -paramInt1 * f + paramInt2 * paramFloat; 
    } 
    return this;
  }
  
  ArrayRow createRowDefinition(SolverVariable paramSolverVariable, int paramInt) {
    this.variable = paramSolverVariable;
    float f = paramInt;
    paramSolverVariable.computedValue = f;
    this.constantValue = f;
    this.isSimpleDefinition = true;
    return this;
  }
  
  ArrayRow createRowDimensionPercent(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, float paramFloat) {
    this.variables.put(paramSolverVariable1, -1.0F);
    this.variables.put(paramSolverVariable2, 1.0F - paramFloat);
    this.variables.put(paramSolverVariable3, paramFloat);
    return this;
  }
  
  public ArrayRow createRowDimensionRatio(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, float paramFloat) {
    this.variables.put(paramSolverVariable1, -1.0F);
    this.variables.put(paramSolverVariable2, 1.0F);
    this.variables.put(paramSolverVariable3, paramFloat);
    this.variables.put(paramSolverVariable4, -paramFloat);
    return this;
  }
  
  public ArrayRow createRowEqualMatchDimensions(float paramFloat1, float paramFloat2, float paramFloat3, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4) {
    this.constantValue = 0.0F;
    if (paramFloat2 == 0.0F || paramFloat1 == paramFloat3) {
      this.variables.put(paramSolverVariable1, 1.0F);
      this.variables.put(paramSolverVariable2, -1.0F);
      this.variables.put(paramSolverVariable4, 1.0F);
      this.variables.put(paramSolverVariable3, -1.0F);
      return this;
    } 
    if (paramFloat1 == 0.0F) {
      this.variables.put(paramSolverVariable1, 1.0F);
      this.variables.put(paramSolverVariable2, -1.0F);
    } else if (paramFloat3 == 0.0F) {
      this.variables.put(paramSolverVariable3, 1.0F);
      this.variables.put(paramSolverVariable4, -1.0F);
    } else {
      paramFloat1 = paramFloat1 / paramFloat2 / paramFloat3 / paramFloat2;
      this.variables.put(paramSolverVariable1, 1.0F);
      this.variables.put(paramSolverVariable2, -1.0F);
      this.variables.put(paramSolverVariable4, paramFloat1);
      this.variables.put(paramSolverVariable3, -paramFloat1);
    } 
    return this;
  }
  
  public ArrayRow createRowEquals(SolverVariable paramSolverVariable, int paramInt) {
    if (paramInt < 0) {
      this.constantValue = (paramInt * -1);
      this.variables.put(paramSolverVariable, 1.0F);
    } else {
      this.constantValue = paramInt;
      this.variables.put(paramSolverVariable, -1.0F);
    } 
    return this;
  }
  
  public ArrayRow createRowEquals(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt) {
    int i = 0;
    int j = 0;
    if (paramInt != 0) {
      i = j;
      j = paramInt;
      if (paramInt < 0) {
        j = paramInt * -1;
        i = 1;
      } 
      this.constantValue = j;
    } 
    if (i == 0) {
      this.variables.put(paramSolverVariable1, -1.0F);
      this.variables.put(paramSolverVariable2, 1.0F);
    } else {
      this.variables.put(paramSolverVariable1, 1.0F);
      this.variables.put(paramSolverVariable2, -1.0F);
    } 
    return this;
  }
  
  public ArrayRow createRowGreaterThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, int paramInt) {
    int i = 0;
    int j = 0;
    if (paramInt != 0) {
      i = j;
      j = paramInt;
      if (paramInt < 0) {
        j = paramInt * -1;
        i = 1;
      } 
      this.constantValue = j;
    } 
    if (i == 0) {
      this.variables.put(paramSolverVariable1, -1.0F);
      this.variables.put(paramSolverVariable2, 1.0F);
      this.variables.put(paramSolverVariable3, 1.0F);
    } else {
      this.variables.put(paramSolverVariable1, 1.0F);
      this.variables.put(paramSolverVariable2, -1.0F);
      this.variables.put(paramSolverVariable3, -1.0F);
    } 
    return this;
  }
  
  public ArrayRow createRowLowerThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, int paramInt) {
    int i = 0;
    int j = 0;
    if (paramInt != 0) {
      i = j;
      j = paramInt;
      if (paramInt < 0) {
        j = paramInt * -1;
        i = 1;
      } 
      this.constantValue = j;
    } 
    if (i == 0) {
      this.variables.put(paramSolverVariable1, -1.0F);
      this.variables.put(paramSolverVariable2, 1.0F);
      this.variables.put(paramSolverVariable3, -1.0F);
    } else {
      this.variables.put(paramSolverVariable1, 1.0F);
      this.variables.put(paramSolverVariable2, -1.0F);
      this.variables.put(paramSolverVariable3, 1.0F);
    } 
    return this;
  }
  
  public ArrayRow createRowWithAngle(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, float paramFloat) {
    this.variables.put(paramSolverVariable3, 0.5F);
    this.variables.put(paramSolverVariable4, 0.5F);
    this.variables.put(paramSolverVariable1, -0.5F);
    this.variables.put(paramSolverVariable2, -0.5F);
    this.constantValue = -paramFloat;
    return this;
  }
  
  void ensurePositiveConstant() {
    float f = this.constantValue;
    if (f < 0.0F) {
      this.constantValue = f * -1.0F;
      this.variables.invert();
    } 
  }
  
  public SolverVariable getKey() {
    return this.variable;
  }
  
  public SolverVariable getPivotCandidate(LinearSystem paramLinearSystem, boolean[] paramArrayOfboolean) {
    return this.variables.getPivotCandidate(paramArrayOfboolean, null);
  }
  
  boolean hasKeyVariable() {
    boolean bool;
    SolverVariable solverVariable = this.variable;
    if (solverVariable != null && (solverVariable.mType == SolverVariable.Type.UNRESTRICTED || this.constantValue >= 0.0F)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  boolean hasVariable(SolverVariable paramSolverVariable) {
    return this.variables.containsKey(paramSolverVariable);
  }
  
  public void initFromRow(LinearSystem.Row paramRow) {
    if (paramRow instanceof ArrayRow) {
      paramRow = paramRow;
      this.variable = null;
      this.variables.clear();
      byte b = 0;
      while (true) {
        ArrayLinkedVariables arrayLinkedVariables = ((ArrayRow)paramRow).variables;
        if (b < arrayLinkedVariables.currentSize) {
          SolverVariable solverVariable = arrayLinkedVariables.getVariable(b);
          float f = ((ArrayRow)paramRow).variables.getVariableValue(b);
          this.variables.add(solverVariable, f, true);
          b++;
          continue;
        } 
        break;
      } 
    } 
  }
  
  public boolean isEmpty() {
    boolean bool;
    if (this.variable == null && this.constantValue == 0.0F && this.variables.currentSize == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  SolverVariable pickPivot(SolverVariable paramSolverVariable) {
    return this.variables.getPivotCandidate(null, paramSolverVariable);
  }
  
  void pivot(SolverVariable paramSolverVariable) {
    SolverVariable solverVariable = this.variable;
    if (solverVariable != null) {
      this.variables.put(solverVariable, -1.0F);
      this.variable = null;
    } 
    float f = this.variables.remove(paramSolverVariable, true) * -1.0F;
    this.variable = paramSolverVariable;
    if (f == 1.0F)
      return; 
    this.constantValue /= f;
    this.variables.divideByAmount(f);
  }
  
  public void reset() {
    this.variable = null;
    this.variables.clear();
    this.constantValue = 0.0F;
    this.isSimpleDefinition = false;
  }
  
  String toReadableString() {
    // Byte code:
    //   0: aload_0
    //   1: getfield variable : Landroidx/constraintlayout/solver/SolverVariable;
    //   4: ifnonnull -> 42
    //   7: new java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial <init> : ()V
    //   14: astore #7
    //   16: aload #7
    //   18: ldc ''
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload #7
    //   26: ldc '0'
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload #7
    //   34: invokevirtual toString : ()Ljava/lang/String;
    //   37: astore #7
    //   39: goto -> 76
    //   42: new java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial <init> : ()V
    //   49: astore #7
    //   51: aload #7
    //   53: ldc ''
    //   55: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: pop
    //   59: aload #7
    //   61: aload_0
    //   62: getfield variable : Landroidx/constraintlayout/solver/SolverVariable;
    //   65: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload #7
    //   71: invokevirtual toString : ()Ljava/lang/String;
    //   74: astore #7
    //   76: new java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial <init> : ()V
    //   83: astore #8
    //   85: aload #8
    //   87: aload #7
    //   89: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: pop
    //   93: aload #8
    //   95: ldc ' = '
    //   97: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: pop
    //   101: aload #8
    //   103: invokevirtual toString : ()Ljava/lang/String;
    //   106: astore #7
    //   108: aload_0
    //   109: getfield constantValue : F
    //   112: fstore_1
    //   113: iconst_0
    //   114: istore #4
    //   116: fload_1
    //   117: fconst_0
    //   118: fcmpl
    //   119: ifeq -> 161
    //   122: new java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial <init> : ()V
    //   129: astore #8
    //   131: aload #8
    //   133: aload #7
    //   135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: aload #8
    //   141: aload_0
    //   142: getfield constantValue : F
    //   145: invokevirtual append : (F)Ljava/lang/StringBuilder;
    //   148: pop
    //   149: aload #8
    //   151: invokevirtual toString : ()Ljava/lang/String;
    //   154: astore #7
    //   156: iconst_1
    //   157: istore_3
    //   158: goto -> 163
    //   161: iconst_0
    //   162: istore_3
    //   163: aload_0
    //   164: getfield variables : Landroidx/constraintlayout/solver/ArrayLinkedVariables;
    //   167: getfield currentSize : I
    //   170: istore #5
    //   172: iload #4
    //   174: iload #5
    //   176: if_icmpge -> 454
    //   179: aload_0
    //   180: getfield variables : Landroidx/constraintlayout/solver/ArrayLinkedVariables;
    //   183: iload #4
    //   185: invokevirtual getVariable : (I)Landroidx/constraintlayout/solver/SolverVariable;
    //   188: astore #8
    //   190: aload #8
    //   192: ifnonnull -> 198
    //   195: goto -> 448
    //   198: aload_0
    //   199: getfield variables : Landroidx/constraintlayout/solver/ArrayLinkedVariables;
    //   202: iload #4
    //   204: invokevirtual getVariableValue : (I)F
    //   207: fstore_2
    //   208: fload_2
    //   209: fconst_0
    //   210: fcmpl
    //   211: istore #6
    //   213: iload #6
    //   215: ifne -> 221
    //   218: goto -> 448
    //   221: aload #8
    //   223: invokevirtual toString : ()Ljava/lang/String;
    //   226: astore #9
    //   228: iload_3
    //   229: ifne -> 279
    //   232: aload #7
    //   234: astore #8
    //   236: fload_2
    //   237: fstore_1
    //   238: fload_2
    //   239: fconst_0
    //   240: fcmpg
    //   241: ifge -> 358
    //   244: new java/lang/StringBuilder
    //   247: dup
    //   248: invokespecial <init> : ()V
    //   251: astore #8
    //   253: aload #8
    //   255: aload #7
    //   257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: pop
    //   261: aload #8
    //   263: ldc '- '
    //   265: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: pop
    //   269: aload #8
    //   271: invokevirtual toString : ()Ljava/lang/String;
    //   274: astore #8
    //   276: goto -> 353
    //   279: iload #6
    //   281: ifle -> 321
    //   284: new java/lang/StringBuilder
    //   287: dup
    //   288: invokespecial <init> : ()V
    //   291: astore #8
    //   293: aload #8
    //   295: aload #7
    //   297: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: pop
    //   301: aload #8
    //   303: ldc ' + '
    //   305: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: pop
    //   309: aload #8
    //   311: invokevirtual toString : ()Ljava/lang/String;
    //   314: astore #8
    //   316: fload_2
    //   317: fstore_1
    //   318: goto -> 358
    //   321: new java/lang/StringBuilder
    //   324: dup
    //   325: invokespecial <init> : ()V
    //   328: astore #8
    //   330: aload #8
    //   332: aload #7
    //   334: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: pop
    //   338: aload #8
    //   340: ldc ' - '
    //   342: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: pop
    //   346: aload #8
    //   348: invokevirtual toString : ()Ljava/lang/String;
    //   351: astore #8
    //   353: fload_2
    //   354: ldc -1.0
    //   356: fmul
    //   357: fstore_1
    //   358: fload_1
    //   359: fconst_1
    //   360: fcmpl
    //   361: ifne -> 399
    //   364: new java/lang/StringBuilder
    //   367: dup
    //   368: invokespecial <init> : ()V
    //   371: astore #7
    //   373: aload #7
    //   375: aload #8
    //   377: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: pop
    //   381: aload #7
    //   383: aload #9
    //   385: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   388: pop
    //   389: aload #7
    //   391: invokevirtual toString : ()Ljava/lang/String;
    //   394: astore #7
    //   396: goto -> 446
    //   399: new java/lang/StringBuilder
    //   402: dup
    //   403: invokespecial <init> : ()V
    //   406: astore #7
    //   408: aload #7
    //   410: aload #8
    //   412: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: pop
    //   416: aload #7
    //   418: fload_1
    //   419: invokevirtual append : (F)Ljava/lang/StringBuilder;
    //   422: pop
    //   423: aload #7
    //   425: ldc ' '
    //   427: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: pop
    //   431: aload #7
    //   433: aload #9
    //   435: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: pop
    //   439: aload #7
    //   441: invokevirtual toString : ()Ljava/lang/String;
    //   444: astore #7
    //   446: iconst_1
    //   447: istore_3
    //   448: iinc #4, 1
    //   451: goto -> 172
    //   454: aload #7
    //   456: astore #8
    //   458: iload_3
    //   459: ifne -> 494
    //   462: new java/lang/StringBuilder
    //   465: dup
    //   466: invokespecial <init> : ()V
    //   469: astore #8
    //   471: aload #8
    //   473: aload #7
    //   475: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   478: pop
    //   479: aload #8
    //   481: ldc '0.0'
    //   483: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: pop
    //   487: aload #8
    //   489: invokevirtual toString : ()Ljava/lang/String;
    //   492: astore #8
    //   494: aload #8
    //   496: areturn
  }
  
  public String toString() {
    return toReadableString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/ArrayRow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */