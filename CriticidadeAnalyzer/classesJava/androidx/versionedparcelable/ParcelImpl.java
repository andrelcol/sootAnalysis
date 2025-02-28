package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelImpl implements Parcelable {
  public static final Parcelable.Creator<ParcelImpl> CREATOR = new Parcelable.Creator<ParcelImpl>() {
      public ParcelImpl createFromParcel(Parcel param1Parcel) {
        return new ParcelImpl(param1Parcel);
      }
      
      public ParcelImpl[] newArray(int param1Int) {
        return new ParcelImpl[param1Int];
      }
    };
  
  private final VersionedParcelable mParcel;
  
  protected ParcelImpl(Parcel paramParcel) {
    this.mParcel = (new VersionedParcelParcel(paramParcel)).readVersionedParcelable();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    (new VersionedParcelParcel(paramParcel)).writeVersionedParcelable(this.mParcel);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/versionedparcelable/ParcelImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */