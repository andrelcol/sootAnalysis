package androidx.arch.core.executor;

public abstract class TaskExecutor {
  public abstract void executeOnDiskIO(Runnable paramRunnable);
  
  public abstract boolean isMainThread();
  
  public abstract void postToMainThread(Runnable paramRunnable);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/arch/core/executor/TaskExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */