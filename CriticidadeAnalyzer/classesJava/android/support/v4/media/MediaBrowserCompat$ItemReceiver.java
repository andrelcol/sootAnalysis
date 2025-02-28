package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;

class MediaBrowserCompat$ItemReceiver extends ResultReceiver {
  private final MediaBrowserCompat$ItemCallback mCallback;
  
  private final String mMediaId;
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle) {
    MediaSessionCompat.ensureClassLoader(paramBundle);
    if (paramInt != 0 || paramBundle == null || !paramBundle.containsKey("media_item")) {
      this.mCallback.onError(this.mMediaId);
      return;
    } 
    Parcelable parcelable = paramBundle.getParcelable("media_item");
    if (parcelable == null || parcelable instanceof MediaBrowserCompat$MediaItem) {
      this.mCallback.onItemLoaded((MediaBrowserCompat$MediaItem)parcelable);
      return;
    } 
    this.mCallback.onError(this.mMediaId);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/MediaBrowserCompat$ItemReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */