package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;

class MediaBrowserCompat$CustomActionResultReceiver extends ResultReceiver {
  private final String mAction;
  
  private final MediaBrowserCompat$CustomActionCallback mCallback;
  
  private final Bundle mExtras;
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle) {
    if (this.mCallback == null)
      return; 
    MediaSessionCompat.ensureClassLoader(paramBundle);
    if (paramInt != -1) {
      if (paramInt != 0) {
        if (paramInt != 1) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown result code: ");
          stringBuilder.append(paramInt);
          stringBuilder.append(" (extras=");
          stringBuilder.append(this.mExtras);
          stringBuilder.append(", resultData=");
          stringBuilder.append(paramBundle);
          stringBuilder.append(")");
          stringBuilder.toString();
        } else {
          this.mCallback.onProgressUpdate(this.mAction, this.mExtras, paramBundle);
        } 
      } else {
        this.mCallback.onResult(this.mAction, this.mExtras, paramBundle);
      } 
    } else {
      this.mCallback.onError(this.mAction, this.mExtras, paramBundle);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/MediaBrowserCompat$CustomActionResultReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */