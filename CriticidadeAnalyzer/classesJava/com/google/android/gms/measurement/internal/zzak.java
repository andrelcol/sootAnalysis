package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzr;
import com.google.android.gms.internal.measurement.zzs;
import java.util.List;

public abstract class zzak extends zzr implements zzaj {
  public zzak() {
    super("com.google.android.gms.measurement.internal.IMeasurementService");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    List<zzo> list1;
    String str;
    byte[] arrayOfByte;
    List<zzfu> list;
    switch (paramInt1) {
      default:
        return false;
      case 18:
        zzd((zzk)zzs.zza(paramParcel1, zzk.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 17:
        list1 = zze(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(list1);
        return true;
      case 16:
        list1 = zza(list1.readString(), list1.readString(), (zzk)zzs.zza((Parcel)list1, zzk.CREATOR));
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(list1);
        return true;
      case 15:
        list1 = (List)zza(list1.readString(), list1.readString(), list1.readString(), zzs.zza((Parcel)list1));
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(list1);
        return true;
      case 14:
        list1 = (List)zza(list1.readString(), list1.readString(), zzs.zza((Parcel)list1), (zzk)zzs.zza((Parcel)list1, zzk.CREATOR));
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(list1);
        return true;
      case 13:
        zzb((zzo)zzs.zza((Parcel)list1, zzo.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 12:
        zza((zzo)zzs.zza((Parcel)list1, zzo.CREATOR), (zzk)zzs.zza((Parcel)list1, zzk.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 11:
        str = zzc((zzk)zzs.zza((Parcel)list1, zzk.CREATOR));
        paramParcel2.writeNoException();
        paramParcel2.writeString(str);
        return true;
      case 10:
        zza(str.readLong(), str.readString(), str.readString(), str.readString());
        paramParcel2.writeNoException();
        return true;
      case 9:
        arrayOfByte = zza((zzag)zzs.zza((Parcel)str, zzag.CREATOR), str.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeByteArray(arrayOfByte);
        return true;
      case 7:
        list = zza((zzk)zzs.zza((Parcel)arrayOfByte, zzk.CREATOR), zzs.zza((Parcel)arrayOfByte));
        paramParcel2.writeNoException();
        paramParcel2.writeTypedList(list);
        return true;
      case 6:
        zzb((zzk)zzs.zza((Parcel)list, zzk.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 5:
        zza((zzag)zzs.zza((Parcel)list, zzag.CREATOR), list.readString(), list.readString());
        paramParcel2.writeNoException();
        return true;
      case 4:
        zza((zzk)zzs.zza((Parcel)list, zzk.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 2:
        zza((zzfu)zzs.zza((Parcel)list, zzfu.CREATOR), (zzk)zzs.zza((Parcel)list, zzk.CREATOR));
        paramParcel2.writeNoException();
        return true;
      case 1:
        break;
    } 
    zza((zzag)zzs.zza((Parcel)list, zzag.CREATOR), (zzk)zzs.zza((Parcel)list, zzk.CREATOR));
    paramParcel2.writeNoException();
    return true;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */