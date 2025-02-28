package android.support.v4.media;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.collection.ArrayMap;

public final class MediaMetadataCompat implements Parcelable {
  public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;
  
  static final ArrayMap<String, Integer> METADATA_KEYS_TYPE = new ArrayMap();
  
  final Bundle mBundle;
  
  private Object mMetadataObj;
  
  static {
    ArrayMap<String, Integer> arrayMap1 = METADATA_KEYS_TYPE;
    Integer integer1 = Integer.valueOf(1);
    arrayMap1.put("android.media.metadata.TITLE", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.ARTIST", integer1);
    ArrayMap<String, Integer> arrayMap2 = METADATA_KEYS_TYPE;
    Integer integer2 = Integer.valueOf(0);
    arrayMap2.put("android.media.metadata.DURATION", integer2);
    METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.AUTHOR", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.WRITER", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.COMPOSER", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.COMPILATION", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.DATE", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.YEAR", integer2);
    METADATA_KEYS_TYPE.put("android.media.metadata.GENRE", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.TRACK_NUMBER", integer2);
    METADATA_KEYS_TYPE.put("android.media.metadata.NUM_TRACKS", integer2);
    METADATA_KEYS_TYPE.put("android.media.metadata.DISC_NUMBER", integer2);
    METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ARTIST", integer1);
    ArrayMap<String, Integer> arrayMap3 = METADATA_KEYS_TYPE;
    Integer integer3 = Integer.valueOf(2);
    arrayMap3.put("android.media.metadata.ART", integer3);
    METADATA_KEYS_TYPE.put("android.media.metadata.ART_URI", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART", integer3);
    METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART_URI", integer1);
    ArrayMap<String, Integer> arrayMap4 = METADATA_KEYS_TYPE;
    Integer integer4 = Integer.valueOf(3);
    arrayMap4.put("android.media.metadata.USER_RATING", integer4);
    METADATA_KEYS_TYPE.put("android.media.metadata.RATING", integer4);
    METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_TITLE", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_SUBTITLE", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_DESCRIPTION", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON", integer3);
    METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON_URI", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_ID", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.BT_FOLDER_TYPE", integer2);
    METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_URI", integer1);
    METADATA_KEYS_TYPE.put("android.media.metadata.ADVERTISEMENT", integer2);
    METADATA_KEYS_TYPE.put("android.media.metadata.DOWNLOAD_STATUS", integer2);
    CREATOR = new Parcelable.Creator<MediaMetadataCompat>() {
        public MediaMetadataCompat createFromParcel(Parcel param1Parcel) {
          return new MediaMetadataCompat(param1Parcel);
        }
        
        public MediaMetadataCompat[] newArray(int param1Int) {
          return new MediaMetadataCompat[param1Int];
        }
      };
  }
  
  MediaMetadataCompat(Parcel paramParcel) {
    this.mBundle = paramParcel.readBundle(MediaSessionCompat.class.getClassLoader());
  }
  
  public static MediaMetadataCompat fromMediaMetadata(Object paramObject) {
    if (paramObject != null && Build.VERSION.SDK_INT >= 21) {
      Parcel parcel = Parcel.obtain();
      MediaMetadataCompatApi21.writeToParcel(paramObject, parcel, 0);
      parcel.setDataPosition(0);
      MediaMetadataCompat mediaMetadataCompat = (MediaMetadataCompat)CREATOR.createFromParcel(parcel);
      parcel.recycle();
      mediaMetadataCompat.mMetadataObj = paramObject;
      return mediaMetadataCompat;
    } 
    return null;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBundle(this.mBundle);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/MediaMetadataCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */