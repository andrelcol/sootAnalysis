package com.google.android.gms.measurement.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzq;
import com.google.android.gms.internal.measurement.zzs;
import java.util.ArrayList;
import java.util.List;

public final class zzal extends zzq implements zzaj {
  zzal(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
  }
  
  public final List<zzo> zza(String paramString1, String paramString2, zzk paramzzk) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    parcel2.writeString(paramString1);
    parcel2.writeString(paramString2);
    zzs.zza(parcel2, (Parcelable)paramzzk);
    Parcel parcel1 = transactAndReadException(16, parcel2);
    ArrayList<zzo> arrayList = parcel1.createTypedArrayList(zzo.CREATOR);
    parcel1.recycle();
    return arrayList;
  }
  
  public final List<zzfu> zza(String paramString1, String paramString2, String paramString3, boolean paramBoolean) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    parcel2.writeString(paramString1);
    parcel2.writeString(paramString2);
    parcel2.writeString(paramString3);
    zzs.writeBoolean(parcel2, paramBoolean);
    Parcel parcel1 = transactAndReadException(15, parcel2);
    ArrayList<zzfu> arrayList = parcel1.createTypedArrayList(zzfu.CREATOR);
    parcel1.recycle();
    return arrayList;
  }
  
  public final List<zzfu> zza(String paramString1, String paramString2, boolean paramBoolean, zzk paramzzk) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    parcel2.writeString(paramString1);
    parcel2.writeString(paramString2);
    zzs.writeBoolean(parcel2, paramBoolean);
    zzs.zza(parcel2, (Parcelable)paramzzk);
    Parcel parcel1 = transactAndReadException(14, parcel2);
    ArrayList<zzfu> arrayList = parcel1.createTypedArrayList(zzfu.CREATOR);
    parcel1.recycle();
    return arrayList;
  }
  
  public final void zza(long paramLong, String paramString1, String paramString2, String paramString3) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    parcel.writeLong(paramLong);
    parcel.writeString(paramString1);
    parcel.writeString(paramString2);
    parcel.writeString(paramString3);
    zza(10, parcel);
  }
  
  public final void zza(zzag paramzzag, zzk paramzzk) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzs.zza(parcel, (Parcelable)paramzzag);
    zzs.zza(parcel, (Parcelable)paramzzk);
    zza(1, parcel);
  }
  
  public final void zza(zzag paramzzag, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzs.zza(parcel, (Parcelable)paramzzag);
    parcel.writeString(paramString1);
    parcel.writeString(paramString2);
    zza(5, parcel);
  }
  
  public final void zza(zzfu paramzzfu, zzk paramzzk) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzs.zza(parcel, (Parcelable)paramzzfu);
    zzs.zza(parcel, (Parcelable)paramzzk);
    zza(2, parcel);
  }
  
  public final void zza(zzk paramzzk) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzs.zza(parcel, (Parcelable)paramzzk);
    zza(4, parcel);
  }
  
  public final void zza(zzo paramzzo, zzk paramzzk) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzs.zza(parcel, (Parcelable)paramzzo);
    zzs.zza(parcel, (Parcelable)paramzzk);
    zza(12, parcel);
  }
  
  public final void zzb(zzk paramzzk) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzs.zza(parcel, (Parcelable)paramzzk);
    zza(6, parcel);
  }
  
  public final void zzb(zzo paramzzo) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzs.zza(parcel, (Parcelable)paramzzo);
    zza(13, parcel);
  }
  
  public final String zzc(zzk paramzzk) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzs.zza(parcel, (Parcelable)paramzzk);
    parcel = transactAndReadException(11, parcel);
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final List<zzo> zze(String paramString1, String paramString2, String paramString3) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    parcel2.writeString(paramString1);
    parcel2.writeString(paramString2);
    parcel2.writeString(paramString3);
    Parcel parcel1 = transactAndReadException(17, parcel2);
    ArrayList<zzo> arrayList = parcel1.createTypedArrayList(zzo.CREATOR);
    parcel1.recycle();
    return arrayList;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */