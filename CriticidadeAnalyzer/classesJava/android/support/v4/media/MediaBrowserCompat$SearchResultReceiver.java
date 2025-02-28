package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import java.util.ArrayList;

class MediaBrowserCompat$SearchResultReceiver extends ResultReceiver {
  private final MediaBrowserCompat$SearchCallback mCallback;
  
  private final Bundle mExtras;
  
  private final String mQuery;
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle) {
    ArrayList<MediaBrowserCompat$MediaItem> arrayList;
    MediaSessionCompat.ensureClassLoader(paramBundle);
    if (paramInt != 0 || paramBundle == null || !paramBundle.containsKey("search_results")) {
      this.mCallback.onError(this.mQuery, this.mExtras);
      return;
    } 
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("search_results");
    paramBundle = null;
    if (arrayOfParcelable != null) {
      ArrayList<MediaBrowserCompat$MediaItem> arrayList1 = new ArrayList();
      int i = arrayOfParcelable.length;
      paramInt = 0;
      while (true) {
        arrayList = arrayList1;
        if (paramInt < i) {
          arrayList1.add((MediaBrowserCompat$MediaItem)arrayOfParcelable[paramInt]);
          paramInt++;
          continue;
        } 
        break;
      } 
    } 
    this.mCallback.onSearchResult(this.mQuery, this.mExtras, arrayList);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/MediaBrowserCompat$SearchResultReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */