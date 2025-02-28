package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

public final class RatingCompat implements Parcelable {
  public static final Parcelable.Creator<RatingCompat> CREATOR = new Parcelable.Creator<RatingCompat>() {
      public RatingCompat createFromParcel(Parcel param1Parcel) {
        return new RatingCompat(param1Parcel.readInt(), param1Parcel.readFloat());
      }
      
      public RatingCompat[] newArray(int param1Int) {
        return new RatingCompat[param1Int];
      }
    };
  
  private final int mRatingStyle;
  
  private final float mRatingValue;
  
  RatingCompat(int paramInt, float paramFloat) {
    this.mRatingStyle = paramInt;
    this.mRatingValue = paramFloat;
  }
  
  public int describeContents() {
    return this.mRatingStyle;
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Rating:style=");
    stringBuilder.append(this.mRatingStyle);
    stringBuilder.append(" rating=");
    float f = this.mRatingValue;
    if (f < 0.0F) {
      str = "unrated";
    } else {
      str = String.valueOf(f);
    } 
    stringBuilder.append(str);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mRatingStyle);
    paramParcel.writeFloat(this.mRatingValue);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/RatingCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */