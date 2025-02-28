package androidx.constraintlayout.solver;

interface Pools$Pool<T> {
  T acquire();
  
  boolean release(T paramT);
  
  void releaseAll(T[] paramArrayOfT, int paramInt);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/Pools$Pool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */