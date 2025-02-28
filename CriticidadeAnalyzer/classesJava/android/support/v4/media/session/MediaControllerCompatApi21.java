package android.support.v4.media.session;

import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.List;

class MediaControllerCompatApi21 {
  public static Object createCallback(Callback paramCallback) {
    return new CallbackProxy<Callback>(paramCallback);
  }
  
  public static interface Callback {
    void onAudioInfoChanged(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5);
    
    void onExtrasChanged(Bundle param1Bundle);
    
    void onMetadataChanged(Object param1Object);
    
    void onPlaybackStateChanged(Object param1Object);
    
    void onQueueChanged(List<?> param1List);
    
    void onQueueTitleChanged(CharSequence param1CharSequence);
    
    void onSessionDestroyed();
    
    void onSessionEvent(String param1String, Bundle param1Bundle);
  }
  
  static class CallbackProxy<T extends Callback> extends MediaController.Callback {
    protected final T mCallback;
    
    public CallbackProxy(T param1T) {
      this.mCallback = param1T;
    }
    
    public void onAudioInfoChanged(MediaController.PlaybackInfo param1PlaybackInfo) {
      this.mCallback.onAudioInfoChanged(param1PlaybackInfo.getPlaybackType(), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(param1PlaybackInfo), param1PlaybackInfo.getVolumeControl(), param1PlaybackInfo.getMaxVolume(), param1PlaybackInfo.getCurrentVolume());
    }
    
    public void onExtrasChanged(Bundle param1Bundle) {
      MediaSessionCompat.ensureClassLoader(param1Bundle);
      this.mCallback.onExtrasChanged(param1Bundle);
    }
    
    public void onMetadataChanged(MediaMetadata param1MediaMetadata) {
      this.mCallback.onMetadataChanged(param1MediaMetadata);
    }
    
    public void onPlaybackStateChanged(PlaybackState param1PlaybackState) {
      this.mCallback.onPlaybackStateChanged(param1PlaybackState);
    }
    
    public void onQueueChanged(List<MediaSession.QueueItem> param1List) {
      this.mCallback.onQueueChanged(param1List);
    }
    
    public void onQueueTitleChanged(CharSequence param1CharSequence) {
      this.mCallback.onQueueTitleChanged(param1CharSequence);
    }
    
    public void onSessionDestroyed() {
      this.mCallback.onSessionDestroyed();
    }
    
    public void onSessionEvent(String param1String, Bundle param1Bundle) {
      MediaSessionCompat.ensureClassLoader(param1Bundle);
      this.mCallback.onSessionEvent(param1String, param1Bundle);
    }
  }
  
  public static class PlaybackInfo {
    public static AudioAttributes getAudioAttributes(Object param1Object) {
      return ((MediaController.PlaybackInfo)param1Object).getAudioAttributes();
    }
    
    public static int getLegacyAudioStream(Object param1Object) {
      return toLegacyStreamType(getAudioAttributes(param1Object));
    }
    
    private static int toLegacyStreamType(AudioAttributes param1AudioAttributes) {
      if ((param1AudioAttributes.getFlags() & 0x1) == 1)
        return 7; 
      if ((param1AudioAttributes.getFlags() & 0x4) == 4)
        return 6; 
      switch (param1AudioAttributes.getUsage()) {
        default:
          return 3;
        case 13:
          return 1;
        case 6:
          return 2;
        case 5:
        case 7:
        case 8:
        case 9:
        case 10:
          return 5;
        case 4:
          return 4;
        case 3:
          return 8;
        case 2:
          return 0;
        case 1:
        case 11:
        case 12:
        case 14:
          break;
      } 
      return 3;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/session/MediaControllerCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */