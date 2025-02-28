package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class ClientIdentity extends AbstractSafeParcelable {
  public static final Parcelable.Creator<ClientIdentity> CREATOR = new zab();
  
  private final String packageName;
  
  private final int uid;
  
  public ClientIdentity(int paramInt, String paramString) {
    this.uid = paramInt;
    this.packageName = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (paramObject != null && paramObject instanceof ClientIdentity) {
      paramObject = paramObject;
      if (((ClientIdentity)paramObject).uid == this.uid && Objects.equal(((ClientIdentity)paramObject).packageName, this.packageName))
        return true; 
    } 
    return false;
  }
  
  public int hashCode() {
    return this.uid;
  }
  
  public String toString() {
    int i = this.uid;
    String str = this.packageName;
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 12);
    stringBuilder.append(i);
    stringBuilder.append(":");
    stringBuilder.append(str);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.uid);
    SafeParcelWriter.writeString(paramParcel, 2, this.packageName, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/ClientIdentity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */