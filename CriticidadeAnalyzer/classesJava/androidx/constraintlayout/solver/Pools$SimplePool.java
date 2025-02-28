package androidx.constraintlayout.solver;

class Pools$SimplePool<T> implements Pools$Pool<T> {
  private final Object[] mPool;
  
  private int mPoolSize;
  
  Pools$SimplePool(int paramInt) {
    if (paramInt > 0) {
      this.mPool = new Object[paramInt];
      return;
    } 
    throw new IllegalArgumentException("The max pool size must be > 0");
  }
  
  public T acquire() {
    int i = this.mPoolSize;
    if (i > 0) {
      int j = i - 1;
      Object[] arrayOfObject = this.mPool;
      Object object = arrayOfObject[j];
      arrayOfObject[j] = null;
      this.mPoolSize = i - 1;
      return (T)object;
    } 
    return null;
  }
  
  public boolean release(T paramT) {
    int i = this.mPoolSize;
    Object[] arrayOfObject = this.mPool;
    if (i < arrayOfObject.length) {
      arrayOfObject[i] = paramT;
      this.mPoolSize = i + 1;
      return true;
    } 
    return false;
  }
  
  public void releaseAll(T[] paramArrayOfT, int paramInt) {
    int i = paramInt;
    if (paramInt > paramArrayOfT.length)
      i = paramArrayOfT.length; 
    for (paramInt = 0; paramInt < i; paramInt++) {
      T t = paramArrayOfT[paramInt];
      int j = this.mPoolSize;
      Object[] arrayOfObject = this.mPool;
      if (j < arrayOfObject.length) {
        arrayOfObject[j] = t;
        this.mPoolSize = j + 1;
      } 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/Pools$SimplePool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */