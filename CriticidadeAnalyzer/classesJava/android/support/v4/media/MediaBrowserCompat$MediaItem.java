package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaBrowserCompat$MediaItem implements Parcelable {
  public static final Parcelable.Creator<MediaBrowserCompat$MediaItem> CREATOR = new Parcelable.Creator<MediaBrowserCompat$MediaItem>() {
      public MediaBrowserCompat$MediaItem createFromParcel(Parcel param1Parcel) {
        return new MediaBrowserCompat$MediaItem(param1Parcel);
      }
      
      public MediaBrowserCompat$MediaItem[] newArray(int param1Int) {
        return new MediaBrowserCompat$MediaItem[param1Int];
      }
    };
  
  private final MediaDescriptionCompat mDescription;
  
  private final int mFlags;
  
  MediaBrowserCompat$MediaItem(Parcel paramParcel) {
    this.mFlags = paramParcel.readInt();
    this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("MediaItem{");
    stringBuilder.append("mFlags=");
    stringBuilder.append(this.mFlags);
    stringBuilder.append(", mDescription=");
    stringBuilder.append(this.mDescription);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mFlags);
    this.mDescription.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/MediaBrowserCompat$MediaItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */