package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zap;

public final class ListenerHolder<L> {
  private final zaa zajj;
  
  private volatile L zajk;
  
  private final ListenerKey<L> zajl;
  
  ListenerHolder(Looper paramLooper, L paramL, String paramString) {
    this.zajj = new zaa(this, paramLooper);
    Preconditions.checkNotNull(paramL, "Listener must not be null");
    this.zajk = paramL;
    Preconditions.checkNotEmpty(paramString);
    this.zajl = new ListenerKey<L>(paramL, paramString);
  }
  
  public final ListenerKey<L> getListenerKey() {
    return this.zajl;
  }
  
  public final void notifyListener(Notifier<? super L> paramNotifier) {
    Preconditions.checkNotNull(paramNotifier, "Notifier must not be null");
    Message message = this.zajj.obtainMessage(1, paramNotifier);
    this.zajj.sendMessage(message);
  }
  
  final void notifyListenerInternal(Notifier<? super L> paramNotifier) {
    L l = this.zajk;
    if (l == null) {
      paramNotifier.onNotifyListenerFailed();
      return;
    } 
    try {
      paramNotifier.notifyListener(l);
      return;
    } catch (RuntimeException runtimeException) {
      paramNotifier.onNotifyListenerFailed();
      throw runtimeException;
    } 
  }
  
  public static final class ListenerKey<L> {
    private final L zajk;
    
    private final String zajn;
    
    ListenerKey(L param1L, String param1String) {
      this.zajk = param1L;
      this.zajn = param1String;
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof ListenerKey))
        return false; 
      param1Object = param1Object;
      return (this.zajk == ((ListenerKey)param1Object).zajk && this.zajn.equals(((ListenerKey)param1Object).zajn));
    }
    
    public final int hashCode() {
      return System.identityHashCode(this.zajk) * 31 + this.zajn.hashCode();
    }
  }
  
  public static interface Notifier<L> {
    void notifyListener(L param1L);
    
    void onNotifyListenerFailed();
  }
  
  private final class zaa extends zap {
    private final ListenerHolder zajm;
    
    public zaa(ListenerHolder this$0, Looper param1Looper) {
      super(param1Looper);
    }
    
    public final void handleMessage(Message param1Message) {
      int i = param1Message.what;
      boolean bool = true;
      if (i != 1)
        bool = false; 
      Preconditions.checkArgument(bool);
      this.zajm.notifyListenerInternal((ListenerHolder.Notifier)param1Message.obj);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/ListenerHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */