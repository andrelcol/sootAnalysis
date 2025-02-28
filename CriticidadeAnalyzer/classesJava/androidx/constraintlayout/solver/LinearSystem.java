package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem {
  private static int POOL_SIZE = 1000;
  
  public static Metrics sMetrics;
  
  private int TABLE_SIZE = 32;
  
  public boolean graphOptimizer;
  
  private boolean[] mAlreadyTestedCandidates;
  
  final Cache mCache;
  
  private Row mGoal;
  
  private int mMaxColumns;
  
  private int mMaxRows;
  
  int mNumColumns;
  
  int mNumRows;
  
  private SolverVariable[] mPoolVariables;
  
  private int mPoolVariablesCount;
  
  ArrayRow[] mRows;
  
  private final Row mTempGoal;
  
  private HashMap<String, SolverVariable> mVariables = null;
  
  int mVariablesID = 0;
  
  public LinearSystem() {
    int i = this.TABLE_SIZE;
    this.mMaxColumns = i;
    this.mRows = null;
    this.graphOptimizer = false;
    this.mAlreadyTestedCandidates = new boolean[i];
    this.mNumColumns = 1;
    this.mNumRows = 0;
    this.mMaxRows = i;
    this.mPoolVariables = new SolverVariable[POOL_SIZE];
    this.mPoolVariablesCount = 0;
    ArrayRow[] arrayOfArrayRow = new ArrayRow[i];
    this.mRows = new ArrayRow[i];
    releaseRows();
    this.mCache = new Cache();
    this.mGoal = new GoalRow(this.mCache);
    this.mTempGoal = new ArrayRow(this.mCache);
  }
  
  private SolverVariable acquireSolverVariable(SolverVariable.Type paramType, String paramString) {
    SolverVariable solverVariable1;
    SolverVariable solverVariable2 = this.mCache.solverVariablePool.acquire();
    if (solverVariable2 == null) {
      solverVariable2 = new SolverVariable(paramType, paramString);
      solverVariable2.setType(paramType, paramString);
      solverVariable1 = solverVariable2;
    } else {
      solverVariable2.reset();
      solverVariable2.setType((SolverVariable.Type)solverVariable1, paramString);
      solverVariable1 = solverVariable2;
    } 
    int i = this.mPoolVariablesCount;
    int j = POOL_SIZE;
    if (i >= j) {
      POOL_SIZE = j * 2;
      this.mPoolVariables = Arrays.<SolverVariable>copyOf(this.mPoolVariables, POOL_SIZE);
    } 
    SolverVariable[] arrayOfSolverVariable = this.mPoolVariables;
    i = this.mPoolVariablesCount;
    this.mPoolVariablesCount = i + 1;
    arrayOfSolverVariable[i] = solverVariable1;
    return solverVariable1;
  }
  
  private void addError(ArrayRow paramArrayRow) {
    paramArrayRow.addError(this, 0);
  }
  
  private final void addRow(ArrayRow paramArrayRow) {
    ArrayRow[] arrayOfArrayRow = this.mRows;
    int i = this.mNumRows;
    if (arrayOfArrayRow[i] != null)
      this.mCache.arrayRowPool.release(arrayOfArrayRow[i]); 
    arrayOfArrayRow = this.mRows;
    i = this.mNumRows;
    arrayOfArrayRow[i] = paramArrayRow;
    SolverVariable solverVariable = paramArrayRow.variable;
    solverVariable.definitionId = i;
    this.mNumRows = i + 1;
    solverVariable.updateReferencesWithNewDefinition(paramArrayRow);
  }
  
  private void computeValues() {
    for (byte b = 0; b < this.mNumRows; b++) {
      ArrayRow arrayRow = this.mRows[b];
      arrayRow.variable.computedValue = arrayRow.constantValue;
    } 
  }
  
  public static ArrayRow createRowDimensionPercent(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, float paramFloat, boolean paramBoolean) {
    ArrayRow arrayRow = paramLinearSystem.createRow();
    if (paramBoolean)
      paramLinearSystem.addError(arrayRow); 
    arrayRow.createRowDimensionPercent(paramSolverVariable1, paramSolverVariable2, paramSolverVariable3, paramFloat);
    return arrayRow;
  }
  
  private int enforceBFS(Row paramRow) throws Exception {
    boolean bool;
    int i = 0;
    while (true) {
      if (i < this.mNumRows) {
        ArrayRow[] arrayOfArrayRow = this.mRows;
        if ((arrayOfArrayRow[i]).variable.mType != SolverVariable.Type.UNRESTRICTED && (arrayOfArrayRow[i]).constantValue < 0.0F) {
          i = 1;
          break;
        } 
        i++;
        continue;
      } 
      i = 0;
      break;
    } 
    if (i != 0) {
      boolean bool1 = false;
      i = 0;
      while (true) {
        bool = i;
        if (!bool1) {
          Metrics metrics = sMetrics;
          if (metrics != null)
            metrics.bfs++; 
          int k = i + 1;
          byte b = 0;
          int j = -1;
          i = -1;
          float f = Float.MAX_VALUE;
          for (bool = false; b < this.mNumRows; bool = i1) {
            float f1;
            int m;
            int n;
            int i1;
            ArrayRow arrayRow = this.mRows[b];
            if (arrayRow.variable.mType == SolverVariable.Type.UNRESTRICTED) {
              n = j;
              m = i;
              f1 = f;
              i1 = bool;
            } else if (arrayRow.isSimpleDefinition) {
              n = j;
              m = i;
              f1 = f;
              i1 = bool;
            } else {
              n = j;
              m = i;
              f1 = f;
              i1 = bool;
              if (arrayRow.constantValue < 0.0F)
                for (byte b1 = 1;; b1++) {
                  n = j;
                  m = i;
                  f1 = f;
                  i1 = bool;
                  if (b1 < this.mNumColumns) {
                    SolverVariable solverVariable = this.mCache.mIndexedVariables[b1];
                    float f2 = arrayRow.variables.get(solverVariable);
                    if (f2 <= 0.0F)
                      continue; 
                    n = bool;
                    i1 = 0;
                    bool = i;
                    m = j;
                    i = i1;
                    for (j = n;; j = n)
                      i++; 
                    i = bool;
                    bool = j;
                    j = m;
                    continue;
                  } 
                  break;
                }  
            } 
            b++;
            j = n;
            i = m;
            f = f1;
          } 
          if (j != -1) {
            ArrayRow arrayRow = this.mRows[j];
            arrayRow.variable.definitionId = -1;
            Metrics metrics1 = sMetrics;
            if (metrics1 != null)
              metrics1.pivots++; 
            arrayRow.pivot(this.mCache.mIndexedVariables[i]);
            SolverVariable solverVariable = arrayRow.variable;
            solverVariable.definitionId = j;
            solverVariable.updateReferencesWithNewDefinition(arrayRow);
          } else {
            bool1 = true;
          } 
          if (k > this.mNumColumns / 2)
            bool1 = true; 
          i = k;
          continue;
        } 
        break;
      } 
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static Metrics getMetrics() {
    return sMetrics;
  }
  
  private void increaseTableSize() {
    this.TABLE_SIZE *= 2;
    this.mRows = Arrays.<ArrayRow>copyOf(this.mRows, this.TABLE_SIZE);
    Cache cache = this.mCache;
    cache.mIndexedVariables = Arrays.<SolverVariable>copyOf(cache.mIndexedVariables, this.TABLE_SIZE);
    int i = this.TABLE_SIZE;
    this.mAlreadyTestedCandidates = new boolean[i];
    this.mMaxColumns = i;
    this.mMaxRows = i;
    Metrics metrics = sMetrics;
    if (metrics != null) {
      metrics.tableSizeIncrease++;
      metrics.maxTableSize = Math.max(metrics.maxTableSize, i);
      metrics = sMetrics;
      metrics.lastTableSize = metrics.maxTableSize;
    } 
  }
  
  private final int optimize(Row paramRow, boolean paramBoolean) {
    Metrics metrics = sMetrics;
    if (metrics != null)
      metrics.optimize++; 
    int i;
    for (i = 0; i < this.mNumColumns; i++)
      this.mAlreadyTestedCandidates[i] = false; 
    boolean bool = false;
    for (i = 0; !bool; i = j) {
      metrics = sMetrics;
      if (metrics != null)
        metrics.iterations++; 
      int j = i + 1;
      if (j >= this.mNumColumns * 2)
        return j; 
      if (paramRow.getKey() != null)
        this.mAlreadyTestedCandidates[(paramRow.getKey()).id] = true; 
      SolverVariable solverVariable = paramRow.getPivotCandidate(this, this.mAlreadyTestedCandidates);
      if (solverVariable != null) {
        boolean[] arrayOfBoolean = this.mAlreadyTestedCandidates;
        i = solverVariable.id;
        if (arrayOfBoolean[i])
          return j; 
        arrayOfBoolean[i] = true;
      } 
      if (solverVariable != null) {
        i = 0;
        int k = -1;
        for (float f = Float.MAX_VALUE; i < this.mNumRows; f = f1) {
          float f1;
          int m;
          ArrayRow arrayRow = this.mRows[i];
          if (arrayRow.variable.mType == SolverVariable.Type.UNRESTRICTED) {
            m = k;
            f1 = f;
          } else if (arrayRow.isSimpleDefinition) {
            m = k;
            f1 = f;
          } else {
            m = k;
            f1 = f;
            if (arrayRow.hasVariable(solverVariable)) {
              float f2 = arrayRow.variables.get(solverVariable);
              m = k;
              f1 = f;
              if (f2 < 0.0F) {
                f2 = -arrayRow.constantValue / f2;
                m = k;
                f1 = f;
                if (f2 < f) {
                  m = i;
                  f1 = f2;
                } 
              } 
            } 
          } 
          i++;
          k = m;
        } 
        if (k > -1) {
          ArrayRow arrayRow = this.mRows[k];
          arrayRow.variable.definitionId = -1;
          Metrics metrics1 = sMetrics;
          if (metrics1 != null)
            metrics1.pivots++; 
          arrayRow.pivot(solverVariable);
          solverVariable = arrayRow.variable;
          solverVariable.definitionId = k;
          solverVariable.updateReferencesWithNewDefinition(arrayRow);
          i = j;
          continue;
        } 
      } 
      bool = true;
    } 
    return i;
  }
  
  private void releaseRows() {
    byte b = 0;
    while (true) {
      ArrayRow[] arrayOfArrayRow = this.mRows;
      if (b < arrayOfArrayRow.length) {
        ArrayRow arrayRow = arrayOfArrayRow[b];
        if (arrayRow != null)
          this.mCache.arrayRowPool.release(arrayRow); 
        this.mRows[b] = null;
        b++;
        continue;
      } 
      break;
    } 
  }
  
  private final void updateRowFromVariables(ArrayRow paramArrayRow) {
    if (this.mNumRows > 0) {
      paramArrayRow.variables.updateFromSystem(paramArrayRow, this.mRows);
      if (paramArrayRow.variables.currentSize == 0)
        paramArrayRow.isSimpleDefinition = true; 
    } 
  }
  
  public void addCenterPoint(ConstraintWidget paramConstraintWidget1, ConstraintWidget paramConstraintWidget2, float paramFloat, int paramInt) {
    SolverVariable solverVariable3 = createObjectVariable(paramConstraintWidget1.getAnchor(ConstraintAnchor.Type.LEFT));
    SolverVariable solverVariable5 = createObjectVariable(paramConstraintWidget1.getAnchor(ConstraintAnchor.Type.TOP));
    SolverVariable solverVariable4 = createObjectVariable(paramConstraintWidget1.getAnchor(ConstraintAnchor.Type.RIGHT));
    SolverVariable solverVariable8 = createObjectVariable(paramConstraintWidget1.getAnchor(ConstraintAnchor.Type.BOTTOM));
    SolverVariable solverVariable6 = createObjectVariable(paramConstraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT));
    SolverVariable solverVariable7 = createObjectVariable(paramConstraintWidget2.getAnchor(ConstraintAnchor.Type.TOP));
    SolverVariable solverVariable1 = createObjectVariable(paramConstraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT));
    SolverVariable solverVariable2 = createObjectVariable(paramConstraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM));
    ArrayRow arrayRow2 = createRow();
    double d3 = paramFloat;
    double d2 = Math.sin(d3);
    double d1 = paramInt;
    arrayRow2.createRowWithAngle(solverVariable5, solverVariable8, solverVariable7, solverVariable2, (float)(d2 * d1));
    addConstraint(arrayRow2);
    ArrayRow arrayRow1 = createRow();
    arrayRow1.createRowWithAngle(solverVariable3, solverVariable4, solverVariable6, solverVariable1, (float)(Math.cos(d3) * d1));
    addConstraint(arrayRow1);
  }
  
  public void addCentering(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, float paramFloat, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, int paramInt2, int paramInt3) {
    ArrayRow arrayRow = createRow();
    arrayRow.createRowCentering(paramSolverVariable1, paramSolverVariable2, paramInt1, paramFloat, paramSolverVariable3, paramSolverVariable4, paramInt2);
    if (paramInt3 != 6)
      arrayRow.addError(this, paramInt3); 
    addConstraint(arrayRow);
  }
  
  public void addConstraint(ArrayRow paramArrayRow) {
    if (paramArrayRow == null)
      return; 
    Metrics metrics = sMetrics;
    if (metrics != null) {
      metrics.constraints++;
      if (paramArrayRow.isSimpleDefinition)
        metrics.simpleconstraints++; 
    } 
    if (this.mNumRows + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    boolean bool1 = false;
    boolean bool2 = false;
    if (!paramArrayRow.isSimpleDefinition) {
      updateRowFromVariables(paramArrayRow);
      if (paramArrayRow.isEmpty())
        return; 
      paramArrayRow.ensurePositiveConstant();
      bool1 = bool2;
      if (paramArrayRow.chooseSubject(this)) {
        SolverVariable solverVariable = createExtraVariable();
        paramArrayRow.variable = solverVariable;
        addRow(paramArrayRow);
        this.mTempGoal.initFromRow(paramArrayRow);
        optimize(this.mTempGoal, true);
        if (solverVariable.definitionId == -1) {
          if (paramArrayRow.variable == solverVariable) {
            SolverVariable solverVariable1 = paramArrayRow.pickPivot(solverVariable);
            if (solverVariable1 != null) {
              Metrics metrics1 = sMetrics;
              if (metrics1 != null)
                metrics1.pivots++; 
              paramArrayRow.pivot(solverVariable1);
            } 
          } 
          if (!paramArrayRow.isSimpleDefinition)
            paramArrayRow.variable.updateReferencesWithNewDefinition(paramArrayRow); 
          this.mNumRows--;
        } 
        bool1 = true;
      } 
      if (!paramArrayRow.hasKeyVariable())
        return; 
    } 
    if (!bool1)
      addRow(paramArrayRow); 
  }
  
  public ArrayRow addEquality(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, int paramInt2) {
    ArrayRow arrayRow = createRow();
    arrayRow.createRowEquals(paramSolverVariable1, paramSolverVariable2, paramInt1);
    if (paramInt2 != 6)
      arrayRow.addError(this, paramInt2); 
    addConstraint(arrayRow);
    return arrayRow;
  }
  
  public void addEquality(SolverVariable paramSolverVariable, int paramInt) {
    int i = paramSolverVariable.definitionId;
    if (i != -1) {
      ArrayRow arrayRow = this.mRows[i];
      if (arrayRow.isSimpleDefinition) {
        arrayRow.constantValue = paramInt;
      } else if (arrayRow.variables.currentSize == 0) {
        arrayRow.isSimpleDefinition = true;
        arrayRow.constantValue = paramInt;
      } else {
        arrayRow = createRow();
        arrayRow.createRowEquals(paramSolverVariable, paramInt);
        addConstraint(arrayRow);
      } 
    } else {
      ArrayRow arrayRow = createRow();
      arrayRow.createRowDefinition(paramSolverVariable, paramInt);
      addConstraint(arrayRow);
    } 
  }
  
  public void addGreaterBarrier(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, boolean paramBoolean) {
    ArrayRow arrayRow = createRow();
    SolverVariable solverVariable = createSlackVariable();
    solverVariable.strength = 0;
    arrayRow.createRowGreaterThan(paramSolverVariable1, paramSolverVariable2, solverVariable, 0);
    if (paramBoolean)
      addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F), 1); 
    addConstraint(arrayRow);
  }
  
  public void addGreaterThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, int paramInt2) {
    ArrayRow arrayRow = createRow();
    SolverVariable solverVariable = createSlackVariable();
    solverVariable.strength = 0;
    arrayRow.createRowGreaterThan(paramSolverVariable1, paramSolverVariable2, solverVariable, paramInt1);
    if (paramInt2 != 6)
      addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F), paramInt2); 
    addConstraint(arrayRow);
  }
  
  public void addLowerBarrier(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, boolean paramBoolean) {
    ArrayRow arrayRow = createRow();
    SolverVariable solverVariable = createSlackVariable();
    solverVariable.strength = 0;
    arrayRow.createRowLowerThan(paramSolverVariable1, paramSolverVariable2, solverVariable, 0);
    if (paramBoolean)
      addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F), 1); 
    addConstraint(arrayRow);
  }
  
  public void addLowerThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, int paramInt2) {
    ArrayRow arrayRow = createRow();
    SolverVariable solverVariable = createSlackVariable();
    solverVariable.strength = 0;
    arrayRow.createRowLowerThan(paramSolverVariable1, paramSolverVariable2, solverVariable, paramInt1);
    if (paramInt2 != 6)
      addSingleError(arrayRow, (int)(arrayRow.variables.get(solverVariable) * -1.0F), paramInt2); 
    addConstraint(arrayRow);
  }
  
  public void addRatio(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, float paramFloat, int paramInt) {
    ArrayRow arrayRow = createRow();
    arrayRow.createRowDimensionRatio(paramSolverVariable1, paramSolverVariable2, paramSolverVariable3, paramSolverVariable4, paramFloat);
    if (paramInt != 6)
      arrayRow.addError(this, paramInt); 
    addConstraint(arrayRow);
  }
  
  void addSingleError(ArrayRow paramArrayRow, int paramInt1, int paramInt2) {
    paramArrayRow.addSingleError(createErrorVariable(paramInt2, null), paramInt1);
  }
  
  public SolverVariable createErrorVariable(int paramInt, String paramString) {
    Metrics metrics = sMetrics;
    if (metrics != null)
      metrics.errors++; 
    if (this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    SolverVariable solverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, paramString);
    this.mVariablesID++;
    this.mNumColumns++;
    int i = this.mVariablesID;
    solverVariable.id = i;
    solverVariable.strength = paramInt;
    this.mCache.mIndexedVariables[i] = solverVariable;
    this.mGoal.addError(solverVariable);
    return solverVariable;
  }
  
  public SolverVariable createExtraVariable() {
    Metrics metrics = sMetrics;
    if (metrics != null)
      metrics.extravariables++; 
    if (this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    SolverVariable solverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
    this.mVariablesID++;
    this.mNumColumns++;
    int i = this.mVariablesID;
    solverVariable.id = i;
    this.mCache.mIndexedVariables[i] = solverVariable;
    return solverVariable;
  }
  
  public SolverVariable createObjectVariable(Object paramObject) {
    SolverVariable solverVariable = null;
    if (paramObject == null)
      return null; 
    if (this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    if (paramObject instanceof ConstraintAnchor) {
      ConstraintAnchor constraintAnchor = (ConstraintAnchor)paramObject;
      solverVariable = constraintAnchor.getSolverVariable();
      paramObject = solverVariable;
      if (solverVariable == null) {
        constraintAnchor.resetSolverVariable(this.mCache);
        paramObject = constraintAnchor.getSolverVariable();
      } 
      int j = ((SolverVariable)paramObject).id;
      if (j != -1 && j <= this.mVariablesID) {
        Object object = paramObject;
        if (this.mCache.mIndexedVariables[j] == null) {
          if (((SolverVariable)paramObject).id != -1)
            paramObject.reset(); 
          this.mVariablesID++;
          this.mNumColumns++;
          j = this.mVariablesID;
          ((SolverVariable)paramObject).id = j;
          ((SolverVariable)paramObject).mType = SolverVariable.Type.UNRESTRICTED;
          this.mCache.mIndexedVariables[j] = (SolverVariable)paramObject;
          return (SolverVariable)paramObject;
        } 
        return (SolverVariable)object;
      } 
    } else {
      return solverVariable;
    } 
    if (((SolverVariable)paramObject).id != -1)
      paramObject.reset(); 
    this.mVariablesID++;
    this.mNumColumns++;
    int i = this.mVariablesID;
    ((SolverVariable)paramObject).id = i;
    ((SolverVariable)paramObject).mType = SolverVariable.Type.UNRESTRICTED;
    this.mCache.mIndexedVariables[i] = (SolverVariable)paramObject;
    return (SolverVariable)paramObject;
  }
  
  public ArrayRow createRow() {
    ArrayRow arrayRow = this.mCache.arrayRowPool.acquire();
    if (arrayRow == null) {
      arrayRow = new ArrayRow(this.mCache);
    } else {
      arrayRow.reset();
    } 
    SolverVariable.increaseErrorId();
    return arrayRow;
  }
  
  public SolverVariable createSlackVariable() {
    Metrics metrics = sMetrics;
    if (metrics != null)
      metrics.slackvariables++; 
    if (this.mNumColumns + 1 >= this.mMaxColumns)
      increaseTableSize(); 
    SolverVariable solverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
    this.mVariablesID++;
    this.mNumColumns++;
    int i = this.mVariablesID;
    solverVariable.id = i;
    this.mCache.mIndexedVariables[i] = solverVariable;
    return solverVariable;
  }
  
  public Cache getCache() {
    return this.mCache;
  }
  
  public int getObjectVariableValue(Object paramObject) {
    paramObject = ((ConstraintAnchor)paramObject).getSolverVariable();
    return (paramObject != null) ? (int)(((SolverVariable)paramObject).computedValue + 0.5F) : 0;
  }
  
  public void minimize() throws Exception {
    Metrics metrics = sMetrics;
    if (metrics != null)
      metrics.minimize++; 
    if (this.graphOptimizer) {
      metrics = sMetrics;
      if (metrics != null)
        metrics.graphOptimizer++; 
      boolean bool = false;
      byte b = 0;
      while (true) {
        if (b < this.mNumRows) {
          if (!(this.mRows[b]).isSimpleDefinition) {
            b = bool;
            break;
          } 
          b++;
          continue;
        } 
        b = 1;
        break;
      } 
      if (b == 0) {
        minimizeGoal(this.mGoal);
      } else {
        metrics = sMetrics;
        if (metrics != null)
          metrics.fullySolved++; 
        computeValues();
      } 
    } else {
      minimizeGoal(this.mGoal);
    } 
  }
  
  void minimizeGoal(Row paramRow) throws Exception {
    Metrics metrics = sMetrics;
    if (metrics != null) {
      metrics.minimizeGoal++;
      metrics.maxVariables = Math.max(metrics.maxVariables, this.mNumColumns);
      metrics = sMetrics;
      metrics.maxRows = Math.max(metrics.maxRows, this.mNumRows);
    } 
    updateRowFromVariables((ArrayRow)paramRow);
    enforceBFS(paramRow);
    optimize(paramRow, false);
    computeValues();
  }
  
  public void reset() {
    byte b = 0;
    while (true) {
      Cache cache = this.mCache;
      SolverVariable[] arrayOfSolverVariable = cache.mIndexedVariables;
      if (b < arrayOfSolverVariable.length) {
        SolverVariable solverVariable = arrayOfSolverVariable[b];
        if (solverVariable != null)
          solverVariable.reset(); 
        b++;
        continue;
      } 
      cache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
      this.mPoolVariablesCount = 0;
      Arrays.fill((Object[])this.mCache.mIndexedVariables, (Object)null);
      HashMap<String, SolverVariable> hashMap = this.mVariables;
      if (hashMap != null)
        hashMap.clear(); 
      this.mVariablesID = 0;
      this.mGoal.clear();
      this.mNumColumns = 1;
      for (b = 0; b < this.mNumRows; b++)
        (this.mRows[b]).used = false; 
      releaseRows();
      this.mNumRows = 0;
      return;
    } 
  }
  
  static interface Row {
    void addError(SolverVariable param1SolverVariable);
    
    void clear();
    
    SolverVariable getKey();
    
    SolverVariable getPivotCandidate(LinearSystem param1LinearSystem, boolean[] param1ArrayOfboolean);
    
    void initFromRow(Row param1Row);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/LinearSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */