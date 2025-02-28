package com.google.android.gms.internal.maps;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzg extends zza implements zze {
  zzg(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
  }
  
  public final IObjectWrapper zza(int paramInt) throws RemoteException {
    Parcel parcel = zza();
    parcel.writeInt(paramInt);
    parcel = zza(1, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zza(Bitmap paramBitmap) throws RemoteException {
    Parcel parcel2 = zza();
    zzc.zza(parcel2, (Parcelable)paramBitmap);
    Parcel parcel1 = zza(6, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zzi() throws RemoteException {
    Parcel parcel = zza(4, zza());
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/maps/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */