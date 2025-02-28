package androidx.constraintlayout.solver;

public class Cache {
  Pools$Pool<ArrayRow> arrayRowPool = new Pools$SimplePool<ArrayRow>(256);
  
  SolverVariable[] mIndexedVariables = new SolverVariable[32];
  
  Pools$Pool<SolverVariable> solverVariablePool = new Pools$SimplePool<SolverVariable>(256);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/Cache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */