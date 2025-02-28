package android.support.v4.media.session;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import androidx.core.app.BundleCompat;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class MediaControllerCompat$MediaControllerImplApi21 implements MediaControllerCompat$MediaControllerImpl {
  private HashMap<MediaControllerCompat$Callback, ExtraCallback> mCallbackMap;
  
  final Object mLock;
  
  private final List<MediaControllerCompat$Callback> mPendingCallbacks;
  
  final MediaSessionCompat.Token mSessionToken;
  
  void processPendingCallbacksLocked() {
    if (this.mSessionToken.getExtraBinder() == null)
      return; 
    Iterator<MediaControllerCompat$Callback> iterator = this.mPendingCallbacks.iterator();
    while (true) {
      if (iterator.hasNext()) {
        MediaControllerCompat$Callback mediaControllerCompat$Callback = iterator.next();
        ExtraCallback extraCallback = new ExtraCallback(mediaControllerCompat$Callback);
        this.mCallbackMap.put(mediaControllerCompat$Callback, extraCallback);
        mediaControllerCompat$Callback.mIControllerCallback = extraCallback;
        try {
          this.mSessionToken.getExtraBinder().registerCallbackListener(extraCallback);
          mediaControllerCompat$Callback.postToHandler(13, null, null);
          continue;
        } catch (RemoteException remoteException) {}
      } 
      this.mPendingCallbacks.clear();
      return;
    } 
  }
  
  private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
    private WeakReference<MediaControllerCompat$MediaControllerImplApi21> mMediaControllerImpl;
    
    protected void onReceiveResult(int param1Int, Bundle param1Bundle) {
      MediaControllerCompat$MediaControllerImplApi21 mediaControllerCompat$MediaControllerImplApi21 = this.mMediaControllerImpl.get();
      if (mediaControllerCompat$MediaControllerImplApi21 == null || param1Bundle == null)
        return; 
      synchronized (mediaControllerCompat$MediaControllerImplApi21.mLock) {
        mediaControllerCompat$MediaControllerImplApi21.mSessionToken.setExtraBinder(IMediaSession.Stub.asInterface(BundleCompat.getBinder(param1Bundle, "android.support.v4.media.session.EXTRA_BINDER")));
        mediaControllerCompat$MediaControllerImplApi21.mSessionToken.setSessionToken2Bundle(param1Bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"));
        mediaControllerCompat$MediaControllerImplApi21.processPendingCallbacksLocked();
        return;
      } 
    }
  }
  
  private static class ExtraCallback extends MediaControllerCompat$Callback.StubCompat {
    ExtraCallback(MediaControllerCompat$Callback param1MediaControllerCompat$Callback) {
      super(param1MediaControllerCompat$Callback);
    }
    
    public void onExtrasChanged(Bundle param1Bundle) throws RemoteException {
      throw new AssertionError();
    }
    
    public void onMetadataChanged(MediaMetadataCompat param1MediaMetadataCompat) throws RemoteException {
      throw new AssertionError();
    }
    
    public void onQueueChanged(List<MediaSessionCompat.QueueItem> param1List) throws RemoteException {
      throw new AssertionError();
    }
    
    public void onQueueTitleChanged(CharSequence param1CharSequence) throws RemoteException {
      throw new AssertionError();
    }
    
    public void onSessionDestroyed() throws RemoteException {
      throw new AssertionError();
    }
    
    public void onVolumeInfoChanged(ParcelableVolumeInfo param1ParcelableVolumeInfo) throws RemoteException {
      throw new AssertionError();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/session/MediaControllerCompat$MediaControllerImplApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */