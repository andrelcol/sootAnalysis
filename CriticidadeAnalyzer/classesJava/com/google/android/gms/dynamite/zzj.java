package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzj extends zza implements zzi {
  zzj(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
  }
  
  public final int zza(IObjectWrapper paramIObjectWrapper, String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel2 = zza();
    zzc.zza(parcel2, (IInterface)paramIObjectWrapper);
    parcel2.writeString(paramString);
    zzc.writeBoolean(parcel2, paramBoolean);
    Parcel parcel1 = zza(3, parcel2);
    int i = parcel1.readInt();
    parcel1.recycle();
    return i;
  }
  
  public final IObjectWrapper zza(IObjectWrapper paramIObjectWrapper, String paramString, int paramInt) throws RemoteException {
    Parcel parcel2 = zza();
    zzc.zza(parcel2, (IInterface)paramIObjectWrapper);
    parcel2.writeString(paramString);
    parcel2.writeInt(paramInt);
    Parcel parcel1 = zza(2, parcel2);
    paramIObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return paramIObjectWrapper;
  }
  
  public final int zzak() throws RemoteException {
    Parcel parcel = zza(6, zza());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final int zzb(IObjectWrapper paramIObjectWrapper, String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel2 = zza();
    zzc.zza(parcel2, (IInterface)paramIObjectWrapper);
    parcel2.writeString(paramString);
    zzc.writeBoolean(parcel2, paramBoolean);
    Parcel parcel1 = zza(5, parcel2);
    int i = parcel1.readInt();
    parcel1.recycle();
    return i;
  }
  
  public final IObjectWrapper zzb(IObjectWrapper paramIObjectWrapper, String paramString, int paramInt) throws RemoteException {
    Parcel parcel2 = zza();
    zzc.zza(parcel2, (IInterface)paramIObjectWrapper);
    parcel2.writeString(paramString);
    parcel2.writeInt(paramInt);
    Parcel parcel1 = zza(4, parcel2);
    paramIObjectWrapper = IObjectWrapper.Stub.asInterface(parcel1.readStrongBinder());
    parcel1.recycle();
    return paramIObjectWrapper;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamite/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */