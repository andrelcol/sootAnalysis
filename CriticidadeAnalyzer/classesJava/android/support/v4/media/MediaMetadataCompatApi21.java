package android.support.v4.media;

import android.media.MediaMetadata;
import android.os.Parcel;

class MediaMetadataCompatApi21 {
  public static void writeToParcel(Object paramObject, Parcel paramParcel, int paramInt) {
    ((MediaMetadata)paramObject).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/MediaMetadataCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */