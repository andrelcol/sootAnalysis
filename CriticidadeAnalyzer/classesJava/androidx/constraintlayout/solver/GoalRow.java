package androidx.constraintlayout.solver;

public class GoalRow extends ArrayRow {
  public GoalRow(Cache paramCache) {
    super(paramCache);
  }
  
  public void addError(SolverVariable paramSolverVariable) {
    super.addError(paramSolverVariable);
    paramSolverVariable.usageInRowCount--;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/GoalRow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */