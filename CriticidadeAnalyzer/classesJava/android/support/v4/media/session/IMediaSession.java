package android.support.v4.media.session;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMediaSession extends IInterface {
  void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback) throws RemoteException;
  
  public static abstract class Stub extends Binder implements IMediaSession {
    public static IMediaSession asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
      return (iInterface != null && iInterface instanceof IMediaSession) ? (IMediaSession)iInterface : new Proxy(param1IBinder);
    }
    
    private static class Proxy implements IMediaSession {
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void registerCallbackListener(IMediaControllerCallback param2IMediaControllerCallback) throws RemoteException {
        Parcel parcel2 = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        try {
          parcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (param2IMediaControllerCallback != null) {
            IBinder iBinder = param2IMediaControllerCallback.asBinder();
          } else {
            param2IMediaControllerCallback = null;
          } 
          parcel2.writeStrongBinder((IBinder)param2IMediaControllerCallback);
          this.mRemote.transact(3, parcel2, parcel1, 0);
          parcel1.readException();
          return;
        } finally {
          parcel1.recycle();
          parcel2.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IMediaSession {
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void registerCallbackListener(IMediaControllerCallback param1IMediaControllerCallback) throws RemoteException {
      Parcel parcel2 = Parcel.obtain();
      Parcel parcel1 = Parcel.obtain();
      try {
        parcel2.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        if (param1IMediaControllerCallback != null) {
          IBinder iBinder = param1IMediaControllerCallback.asBinder();
        } else {
          param1IMediaControllerCallback = null;
        } 
        parcel2.writeStrongBinder((IBinder)param1IMediaControllerCallback);
        this.mRemote.transact(3, parcel2, parcel1, 0);
        parcel1.readException();
        return;
      } finally {
        parcel1.recycle();
        parcel2.recycle();
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/session/IMediaSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */