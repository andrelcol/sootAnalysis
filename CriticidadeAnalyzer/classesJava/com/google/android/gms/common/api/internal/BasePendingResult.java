package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@KeepName
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
  static final ThreadLocal<Boolean> zadn = new zap();
  
  private Status mStatus;
  
  private R zacj;
  
  private final Object zado;
  
  private final CallbackHandler<R> zadp;
  
  private final WeakReference<GoogleApiClient> zadq;
  
  private final CountDownLatch zadr;
  
  private final ArrayList<PendingResult.StatusListener> zads;
  
  private ResultCallback<? super R> zadt;
  
  private final AtomicReference<zacs> zadu;
  
  private volatile boolean zadv;
  
  private boolean zadw;
  
  private boolean zadx;
  
  private ICancelToken zady;
  
  private volatile zacm<R> zadz;
  
  private boolean zaea;
  
  @Deprecated
  BasePendingResult() {
    this.zado = new Object();
    this.zadr = new CountDownLatch(1);
    this.zads = new ArrayList<PendingResult.StatusListener>();
    this.zadu = new AtomicReference<zacs>();
    this.zaea = false;
    this.zadp = new CallbackHandler<R>(Looper.getMainLooper());
    this.zadq = new WeakReference<GoogleApiClient>(null);
  }
  
  protected BasePendingResult(GoogleApiClient paramGoogleApiClient) {
    Looper looper;
    this.zado = new Object();
    this.zadr = new CountDownLatch(1);
    this.zads = new ArrayList<PendingResult.StatusListener>();
    this.zadu = new AtomicReference<zacs>();
    this.zaea = false;
    if (paramGoogleApiClient != null) {
      looper = paramGoogleApiClient.getLooper();
    } else {
      looper = Looper.getMainLooper();
    } 
    this.zadp = new CallbackHandler<R>(looper);
    this.zadq = new WeakReference<GoogleApiClient>(paramGoogleApiClient);
  }
  
  private final R get() {
    synchronized (this.zado) {
      boolean bool;
      if (!this.zadv) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "Result has already been consumed.");
      Preconditions.checkState(isReady(), "Result is not ready.");
      R r = this.zacj;
      this.zacj = null;
      this.zadt = null;
      this.zadv = true;
      null = this.zadu.getAndSet(null);
      if (null != null)
        null.zac(this); 
      return r;
    } 
  }
  
  private final void zaa(R paramR) {
    this.zacj = paramR;
    this.zady = null;
    this.zadr.countDown();
    this.mStatus = this.zacj.getStatus();
    if (this.zadw) {
      this.zadt = null;
    } else if (this.zadt == null) {
      if (this.zacj instanceof Releasable)
        new zaa(null); 
    } else {
      this.zadp.removeMessages(2);
      this.zadp.zaa(this.zadt, get());
    } 
    ArrayList<PendingResult.StatusListener> arrayList = this.zads;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      paramR = (R)arrayList.get(b);
      b++;
      ((PendingResult.StatusListener)paramR).onComplete(this.mStatus);
    } 
    this.zads.clear();
  }
  
  public static void zab(Result paramResult) {
    if (paramResult instanceof Releasable)
      try {
        ((Releasable)paramResult).release();
        return;
      } catch (RuntimeException runtimeException) {
        String str = String.valueOf(paramResult);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 18);
        stringBuilder.append("Unable to release ");
        stringBuilder.append(str);
        stringBuilder.toString();
      }  
  }
  
  public final void addStatusListener(PendingResult.StatusListener paramStatusListener) {
    boolean bool;
    if (paramStatusListener != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "Callback cannot be null.");
    synchronized (this.zado) {
      if (isReady()) {
        paramStatusListener.onComplete(this.mStatus);
      } else {
        this.zads.add(paramStatusListener);
      } 
      return;
    } 
  }
  
  public void cancel() {
    synchronized (this.zado) {
      if (this.zadw || this.zadv)
        return; 
      ICancelToken iCancelToken = this.zady;
      if (iCancelToken != null)
        try {
          this.zady.cancel();
        } catch (RemoteException remoteException) {} 
      zab((Result)this.zacj);
      this.zadw = true;
      Status status = Status.RESULT_CANCELED;
      createFailedResult(status);
      zaa((R)status);
      return;
    } 
  }
  
  protected abstract R createFailedResult(Status paramStatus);
  
  public boolean isCanceled() {
    synchronized (this.zado) {
      return this.zadw;
    } 
  }
  
  public final boolean isReady() {
    return (this.zadr.getCount() == 0L);
  }
  
  public final void setResult(R paramR) {
    synchronized (this.zado) {
      if (!this.zadx && !this.zadw) {
        isReady();
        boolean bool = isReady();
        boolean bool1 = true;
        if (!bool) {
          bool = true;
        } else {
          bool = false;
        } 
        Preconditions.checkState(bool, "Results have already been set");
        if (!this.zadv) {
          bool = bool1;
        } else {
          bool = false;
        } 
        Preconditions.checkState(bool, "Result has already been consumed");
        zaa(paramR);
        return;
      } 
      zab((Result)paramR);
      return;
    } 
  }
  
  public final void setResultCallback(ResultCallback<? super R> paramResultCallback) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zado : Ljava/lang/Object;
    //   4: astore #4
    //   6: aload #4
    //   8: monitorenter
    //   9: aload_1
    //   10: ifnonnull -> 22
    //   13: aload_0
    //   14: aconst_null
    //   15: putfield zadt : Lcom/google/android/gms/common/api/ResultCallback;
    //   18: aload #4
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield zadv : Z
    //   26: istore_2
    //   27: iconst_1
    //   28: istore_3
    //   29: iload_2
    //   30: ifne -> 38
    //   33: iconst_1
    //   34: istore_2
    //   35: goto -> 40
    //   38: iconst_0
    //   39: istore_2
    //   40: iload_2
    //   41: ldc 'Result has already been consumed.'
    //   43: invokestatic checkState : (ZLjava/lang/Object;)V
    //   46: aload_0
    //   47: getfield zadz : Lcom/google/android/gms/common/api/internal/zacm;
    //   50: ifnonnull -> 58
    //   53: iload_3
    //   54: istore_2
    //   55: goto -> 60
    //   58: iconst_0
    //   59: istore_2
    //   60: iload_2
    //   61: ldc_w 'Cannot set callbacks if then() has been called.'
    //   64: invokestatic checkState : (ZLjava/lang/Object;)V
    //   67: aload_0
    //   68: invokevirtual isCanceled : ()Z
    //   71: ifeq -> 78
    //   74: aload #4
    //   76: monitorexit
    //   77: return
    //   78: aload_0
    //   79: invokevirtual isReady : ()Z
    //   82: ifeq -> 100
    //   85: aload_0
    //   86: getfield zadp : Lcom/google/android/gms/common/api/internal/BasePendingResult$CallbackHandler;
    //   89: aload_1
    //   90: aload_0
    //   91: invokespecial get : ()Lcom/google/android/gms/common/api/Result;
    //   94: invokevirtual zaa : (Lcom/google/android/gms/common/api/ResultCallback;Lcom/google/android/gms/common/api/Result;)V
    //   97: goto -> 105
    //   100: aload_0
    //   101: aload_1
    //   102: putfield zadt : Lcom/google/android/gms/common/api/ResultCallback;
    //   105: aload #4
    //   107: monitorexit
    //   108: return
    //   109: astore_1
    //   110: aload #4
    //   112: monitorexit
    //   113: aload_1
    //   114: athrow
    // Exception table:
    //   from	to	target	type
    //   13	21	109	finally
    //   22	27	109	finally
    //   40	53	109	finally
    //   60	77	109	finally
    //   78	97	109	finally
    //   100	105	109	finally
    //   105	108	109	finally
    //   110	113	109	finally
  }
  
  public final void zaa(zacs paramzacs) {
    this.zadu.set(paramzacs);
  }
  
  public final void zab(Status paramStatus) {
    synchronized (this.zado) {
      if (!isReady()) {
        createFailedResult(paramStatus);
        setResult((R)paramStatus);
        this.zadx = true;
      } 
      return;
    } 
  }
  
  public final Integer zam() {
    return null;
  }
  
  public final boolean zat() {
    synchronized (this.zado) {
      if ((GoogleApiClient)this.zadq.get() == null || !this.zaea)
        cancel(); 
      return isCanceled();
    } 
  }
  
  public final void zau() {
    boolean bool;
    if (this.zaea || ((Boolean)zadn.get()).booleanValue()) {
      bool = true;
    } else {
      bool = false;
    } 
    this.zaea = bool;
  }
  
  public static class CallbackHandler<R extends Result> extends zap {
    public CallbackHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      StringBuilder stringBuilder;
      int i = param1Message.what;
      if (i != 1) {
        if (i != 2) {
          stringBuilder = new StringBuilder(45);
          stringBuilder.append("Don't know how to handle message: ");
          stringBuilder.append(i);
          stringBuilder.toString();
          new Exception();
          return;
        } 
        ((BasePendingResult)((Message)stringBuilder).obj).zab(Status.RESULT_TIMEOUT);
        return;
      } 
      Pair pair = (Pair)((Message)stringBuilder).obj;
      ResultCallback resultCallback = (ResultCallback)pair.first;
      Result result = (Result)pair.second;
      try {
        resultCallback.onResult(result);
        return;
      } catch (RuntimeException runtimeException) {
        BasePendingResult.zab(result);
        throw runtimeException;
      } 
    }
    
    public final void zaa(ResultCallback<? super R> param1ResultCallback, R param1R) {
      sendMessage(obtainMessage(1, new Pair(param1ResultCallback, param1R)));
    }
  }
  
  private final class zaa {
    private final BasePendingResult zaeb;
    
    private zaa(BasePendingResult this$0) {}
    
    protected final void finalize() throws Throwable {
      BasePendingResult.zab(BasePendingResult.zaa(this.zaeb));
      super.finalize();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/BasePendingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */