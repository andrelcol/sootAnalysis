package com.google.android.gms.internal.base;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public class zac {
  static {
    zac.class.getClassLoader();
  }
  
  public static void writeBoolean(Parcel paramParcel, boolean paramBoolean) {
    paramParcel.writeInt(paramBoolean);
  }
  
  public static <T extends Parcelable> T zaa(Parcel paramParcel, Parcelable.Creator<T> paramCreator) {
    return (T)((paramParcel.readInt() == 0) ? null : (Parcelable)paramCreator.createFromParcel(paramParcel));
  }
  
  public static void zaa(Parcel paramParcel, IInterface paramIInterface) {
    if (paramIInterface == null) {
      paramParcel.writeStrongBinder(null);
      return;
    } 
    paramParcel.writeStrongBinder(paramIInterface.asBinder());
  }
  
  public static void zaa(Parcel paramParcel, Parcelable paramParcelable) {
    if (paramParcelable == null) {
      paramParcel.writeInt(0);
      return;
    } 
    paramParcel.writeInt(1);
    paramParcelable.writeToParcel(paramParcel, 0);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/base/zac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */