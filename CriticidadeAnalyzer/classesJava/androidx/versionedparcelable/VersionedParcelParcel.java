package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

class VersionedParcelParcel extends VersionedParcel {
  private int mCurrentField = -1;
  
  private final int mEnd;
  
  private int mNextRead = 0;
  
  private final int mOffset;
  
  private final Parcel mParcel;
  
  private final SparseIntArray mPositionLookup = new SparseIntArray();
  
  private final String mPrefix;
  
  VersionedParcelParcel(Parcel paramParcel) {
    this(paramParcel, paramParcel.dataPosition(), paramParcel.dataSize(), "");
  }
  
  VersionedParcelParcel(Parcel paramParcel, int paramInt1, int paramInt2, String paramString) {
    this.mParcel = paramParcel;
    this.mOffset = paramInt1;
    this.mEnd = paramInt2;
    this.mNextRead = this.mOffset;
    this.mPrefix = paramString;
  }
  
  private int readUntilField(int paramInt) {
    while (true) {
      int i = this.mNextRead;
      if (i < this.mEnd) {
        this.mParcel.setDataPosition(i);
        int j = this.mParcel.readInt();
        i = this.mParcel.readInt();
        this.mNextRead += j;
        if (i == paramInt)
          return this.mParcel.dataPosition(); 
        continue;
      } 
      return -1;
    } 
  }
  
  public void closeField() {
    int i = this.mCurrentField;
    if (i >= 0) {
      i = this.mPositionLookup.get(i);
      int j = this.mParcel.dataPosition();
      this.mParcel.setDataPosition(i);
      this.mParcel.writeInt(j - i);
      this.mParcel.setDataPosition(j);
    } 
  }
  
  protected VersionedParcel createSubParcel() {
    Parcel parcel = this.mParcel;
    int k = parcel.dataPosition();
    int j = this.mNextRead;
    int i = j;
    if (j == this.mOffset)
      i = this.mEnd; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mPrefix);
    stringBuilder.append("  ");
    return new VersionedParcelParcel(parcel, k, i, stringBuilder.toString());
  }
  
  public byte[] readByteArray() {
    int i = this.mParcel.readInt();
    if (i < 0)
      return null; 
    byte[] arrayOfByte = new byte[i];
    this.mParcel.readByteArray(arrayOfByte);
    return arrayOfByte;
  }
  
  public boolean readField(int paramInt) {
    paramInt = readUntilField(paramInt);
    if (paramInt == -1)
      return false; 
    this.mParcel.setDataPosition(paramInt);
    return true;
  }
  
  public int readInt() {
    return this.mParcel.readInt();
  }
  
  public <T extends Parcelable> T readParcelable() {
    return (T)this.mParcel.readParcelable(VersionedParcelParcel.class.getClassLoader());
  }
  
  public String readString() {
    return this.mParcel.readString();
  }
  
  public void setOutputField(int paramInt) {
    closeField();
    this.mCurrentField = paramInt;
    this.mPositionLookup.put(paramInt, this.mParcel.dataPosition());
    writeInt(0);
    writeInt(paramInt);
  }
  
  public void writeByteArray(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null) {
      this.mParcel.writeInt(paramArrayOfbyte.length);
      this.mParcel.writeByteArray(paramArrayOfbyte);
    } else {
      this.mParcel.writeInt(-1);
    } 
  }
  
  public void writeInt(int paramInt) {
    this.mParcel.writeInt(paramInt);
  }
  
  public void writeParcelable(Parcelable paramParcelable) {
    this.mParcel.writeParcelable(paramParcelable, 0);
  }
  
  public void writeString(String paramString) {
    this.mParcel.writeString(paramString);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/versionedparcelable/VersionedParcelParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */