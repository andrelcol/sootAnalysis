package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableVolumeInfo implements Parcelable {
  public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new Parcelable.Creator<ParcelableVolumeInfo>() {
      public ParcelableVolumeInfo createFromParcel(Parcel param1Parcel) {
        return new ParcelableVolumeInfo(param1Parcel);
      }
      
      public ParcelableVolumeInfo[] newArray(int param1Int) {
        return new ParcelableVolumeInfo[param1Int];
      }
    };
  
  public int audioStream;
  
  public int controlType;
  
  public int currentVolume;
  
  public int maxVolume;
  
  public int volumeType;
  
  public ParcelableVolumeInfo(Parcel paramParcel) {
    this.volumeType = paramParcel.readInt();
    this.controlType = paramParcel.readInt();
    this.maxVolume = paramParcel.readInt();
    this.currentVolume = paramParcel.readInt();
    this.audioStream = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.volumeType);
    paramParcel.writeInt(this.controlType);
    paramParcel.writeInt(this.maxVolume);
    paramParcel.writeInt(this.currentVolume);
    paramParcel.writeInt(this.audioStream);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/session/ParcelableVolumeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */