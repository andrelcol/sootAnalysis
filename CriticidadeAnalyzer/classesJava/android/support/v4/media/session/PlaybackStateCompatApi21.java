package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.List;

class PlaybackStateCompatApi21 {
  public static long getActions(Object paramObject) {
    return ((PlaybackState)paramObject).getActions();
  }
  
  public static long getActiveQueueItemId(Object paramObject) {
    return ((PlaybackState)paramObject).getActiveQueueItemId();
  }
  
  public static long getBufferedPosition(Object paramObject) {
    return ((PlaybackState)paramObject).getBufferedPosition();
  }
  
  public static List<Object> getCustomActions(Object paramObject) {
    return ((PlaybackState)paramObject).getCustomActions();
  }
  
  public static CharSequence getErrorMessage(Object paramObject) {
    return ((PlaybackState)paramObject).getErrorMessage();
  }
  
  public static long getLastPositionUpdateTime(Object paramObject) {
    return ((PlaybackState)paramObject).getLastPositionUpdateTime();
  }
  
  public static float getPlaybackSpeed(Object paramObject) {
    return ((PlaybackState)paramObject).getPlaybackSpeed();
  }
  
  public static long getPosition(Object paramObject) {
    return ((PlaybackState)paramObject).getPosition();
  }
  
  public static int getState(Object paramObject) {
    return ((PlaybackState)paramObject).getState();
  }
  
  static final class CustomAction {
    public static String getAction(Object param1Object) {
      return ((PlaybackState.CustomAction)param1Object).getAction();
    }
    
    public static Bundle getExtras(Object param1Object) {
      return ((PlaybackState.CustomAction)param1Object).getExtras();
    }
    
    public static int getIcon(Object param1Object) {
      return ((PlaybackState.CustomAction)param1Object).getIcon();
    }
    
    public static CharSequence getName(Object param1Object) {
      return ((PlaybackState.CustomAction)param1Object).getName();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/session/PlaybackStateCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */