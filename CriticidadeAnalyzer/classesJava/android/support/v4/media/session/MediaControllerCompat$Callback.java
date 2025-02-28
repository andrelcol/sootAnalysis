package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import java.lang.ref.WeakReference;
import java.util.List;

public abstract class MediaControllerCompat$Callback implements IBinder.DeathRecipient {
  MessageHandler mHandler;
  
  IMediaControllerCallback mIControllerCallback;
  
  public MediaControllerCompat$Callback() {
    if (Build.VERSION.SDK_INT >= 21) {
      MediaControllerCompatApi21.createCallback(new StubApi21(this));
    } else {
      this.mIControllerCallback = new StubCompat(this);
    } 
  }
  
  public void binderDied() {
    postToHandler(8, null, null);
  }
  
  public void onAudioInfoChanged(MediaControllerCompat$PlaybackInfo paramMediaControllerCompat$PlaybackInfo) {}
  
  public void onExtrasChanged(Bundle paramBundle) {}
  
  public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat) {}
  
  public void onPlaybackStateChanged(PlaybackStateCompat paramPlaybackStateCompat) {}
  
  public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList) {}
  
  public void onQueueTitleChanged(CharSequence paramCharSequence) {}
  
  public void onSessionDestroyed() {}
  
  public void onSessionEvent(String paramString, Bundle paramBundle) {}
  
  void postToHandler(int paramInt, Object paramObject, Bundle paramBundle) {
    MessageHandler messageHandler = this.mHandler;
    if (messageHandler != null) {
      paramObject = messageHandler.obtainMessage(paramInt, paramObject);
      paramObject.setData(paramBundle);
      paramObject.sendToTarget();
    } 
  }
  
  private class MessageHandler extends Handler {}
  
  private static class StubApi21 implements MediaControllerCompatApi21.Callback {
    private final WeakReference<MediaControllerCompat$Callback> mCallback;
    
    StubApi21(MediaControllerCompat$Callback param1MediaControllerCompat$Callback) {
      this.mCallback = new WeakReference<MediaControllerCompat$Callback>(param1MediaControllerCompat$Callback);
    }
    
    public void onAudioInfoChanged(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5) {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.onAudioInfoChanged(new MediaControllerCompat$PlaybackInfo(param1Int1, param1Int2, param1Int3, param1Int4, param1Int5)); 
    }
    
    public void onExtrasChanged(Bundle param1Bundle) {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.onExtrasChanged(param1Bundle); 
    }
    
    public void onMetadataChanged(Object param1Object) {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(param1Object)); 
    }
    
    public void onPlaybackStateChanged(Object param1Object) {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null && mediaControllerCompat$Callback.mIControllerCallback == null)
        mediaControllerCompat$Callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(param1Object)); 
    }
    
    public void onQueueChanged(List<?> param1List) {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(param1List)); 
    }
    
    public void onQueueTitleChanged(CharSequence param1CharSequence) {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.onQueueTitleChanged(param1CharSequence); 
    }
    
    public void onSessionDestroyed() {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.onSessionDestroyed(); 
    }
    
    public void onSessionEvent(String param1String, Bundle param1Bundle) {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null && (mediaControllerCompat$Callback.mIControllerCallback == null || Build.VERSION.SDK_INT >= 23))
        mediaControllerCompat$Callback.onSessionEvent(param1String, param1Bundle); 
    }
  }
  
  private static class StubCompat extends IMediaControllerCallback.Stub {
    private final WeakReference<MediaControllerCompat$Callback> mCallback;
    
    StubCompat(MediaControllerCompat$Callback param1MediaControllerCompat$Callback) {
      this.mCallback = new WeakReference<MediaControllerCompat$Callback>(param1MediaControllerCompat$Callback);
    }
    
    public void onCaptioningEnabledChanged(boolean param1Boolean) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(11, Boolean.valueOf(param1Boolean), null); 
    }
    
    public void onEvent(String param1String, Bundle param1Bundle) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(1, param1String, param1Bundle); 
    }
    
    public void onExtrasChanged(Bundle param1Bundle) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(7, param1Bundle, null); 
    }
    
    public void onMetadataChanged(MediaMetadataCompat param1MediaMetadataCompat) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(3, param1MediaMetadataCompat, null); 
    }
    
    public void onPlaybackStateChanged(PlaybackStateCompat param1PlaybackStateCompat) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(2, param1PlaybackStateCompat, null); 
    }
    
    public void onQueueChanged(List<MediaSessionCompat.QueueItem> param1List) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(5, param1List, null); 
    }
    
    public void onQueueTitleChanged(CharSequence param1CharSequence) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(6, param1CharSequence, null); 
    }
    
    public void onRepeatModeChanged(int param1Int) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(9, Integer.valueOf(param1Int), null); 
    }
    
    public void onSessionDestroyed() throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(8, null, null); 
    }
    
    public void onSessionReady() throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(13, null, null); 
    }
    
    public void onShuffleModeChanged(int param1Int) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null)
        mediaControllerCompat$Callback.postToHandler(12, Integer.valueOf(param1Int), null); 
    }
    
    public void onShuffleModeChangedRemoved(boolean param1Boolean) throws RemoteException {}
    
    public void onVolumeInfoChanged(ParcelableVolumeInfo param1ParcelableVolumeInfo) throws RemoteException {
      MediaControllerCompat$Callback mediaControllerCompat$Callback = this.mCallback.get();
      if (mediaControllerCompat$Callback != null) {
        if (param1ParcelableVolumeInfo != null) {
          MediaControllerCompat$PlaybackInfo mediaControllerCompat$PlaybackInfo = new MediaControllerCompat$PlaybackInfo(param1ParcelableVolumeInfo.volumeType, param1ParcelableVolumeInfo.audioStream, param1ParcelableVolumeInfo.controlType, param1ParcelableVolumeInfo.maxVolume, param1ParcelableVolumeInfo.currentVolume);
        } else {
          param1ParcelableVolumeInfo = null;
        } 
        mediaControllerCompat$Callback.postToHandler(4, param1ParcelableVolumeInfo, null);
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/session/MediaControllerCompat$Callback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */