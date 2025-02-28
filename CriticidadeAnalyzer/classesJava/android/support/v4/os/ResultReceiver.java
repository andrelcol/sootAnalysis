package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;

public class ResultReceiver implements Parcelable {
  public static final Parcelable.Creator<ResultReceiver> CREATOR = new Parcelable.Creator<ResultReceiver>() {
      public ResultReceiver createFromParcel(Parcel param1Parcel) {
        return new ResultReceiver(param1Parcel);
      }
      
      public ResultReceiver[] newArray(int param1Int) {
        return new ResultReceiver[param1Int];
      }
    };
  
  final Handler mHandler = null;
  
  IResultReceiver mReceiver;
  
  ResultReceiver(Parcel paramParcel) {
    this.mReceiver = IResultReceiver.Stub.asInterface(paramParcel.readStrongBinder());
  }
  
  public int describeContents() {
    return 0;
  }
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle) {}
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mReceiver : Landroid/support/v4/os/IResultReceiver;
    //   6: ifnonnull -> 23
    //   9: new android/support/v4/os/ResultReceiver$MyResultReceiver
    //   12: astore_3
    //   13: aload_3
    //   14: aload_0
    //   15: invokespecial <init> : (Landroid/support/v4/os/ResultReceiver;)V
    //   18: aload_0
    //   19: aload_3
    //   20: putfield mReceiver : Landroid/support/v4/os/IResultReceiver;
    //   23: aload_1
    //   24: aload_0
    //   25: getfield mReceiver : Landroid/support/v4/os/IResultReceiver;
    //   28: invokeinterface asBinder : ()Landroid/os/IBinder;
    //   33: invokevirtual writeStrongBinder : (Landroid/os/IBinder;)V
    //   36: aload_0
    //   37: monitorexit
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	39	finally
    //   23	38	39	finally
    //   40	42	39	finally
  }
  
  class MyResultReceiver extends IResultReceiver.Stub {
    final ResultReceiver this$0;
    
    public void send(int param1Int, Bundle param1Bundle) {
      ResultReceiver resultReceiver = ResultReceiver.this;
      Handler handler = resultReceiver.mHandler;
      if (handler != null) {
        handler.post(new ResultReceiver.MyRunnable(param1Int, param1Bundle));
      } else {
        resultReceiver.onReceiveResult(param1Int, param1Bundle);
      } 
    }
  }
  
  class MyRunnable implements Runnable {
    final int mResultCode;
    
    final Bundle mResultData;
    
    final ResultReceiver this$0;
    
    MyRunnable(int param1Int, Bundle param1Bundle) {
      this.mResultCode = param1Int;
      this.mResultData = param1Bundle;
    }
    
    public void run() {
      ResultReceiver.this.onReceiveResult(this.mResultCode, this.mResultData);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/os/ResultReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */