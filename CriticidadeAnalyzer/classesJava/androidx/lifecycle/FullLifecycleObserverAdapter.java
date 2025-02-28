package androidx.lifecycle;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
  private final FullLifecycleObserver mObserver;
  
  FullLifecycleObserverAdapter(FullLifecycleObserver paramFullLifecycleObserver) {
    this.mObserver = paramFullLifecycleObserver;
  }
  
  public void onStateChanged(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent) {
    switch (paramEvent) {
      default:
        return;
      case ON_ANY:
        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
      case ON_DESTROY:
        this.mObserver.onDestroy(paramLifecycleOwner);
      case ON_STOP:
        this.mObserver.onStop(paramLifecycleOwner);
      case ON_PAUSE:
        this.mObserver.onPause(paramLifecycleOwner);
      case ON_RESUME:
        this.mObserver.onResume(paramLifecycleOwner);
      case ON_START:
        this.mObserver.onStart(paramLifecycleOwner);
      case ON_CREATE:
        break;
    } 
    this.mObserver.onCreate(paramLifecycleOwner);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/lifecycle/FullLifecycleObserverAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */