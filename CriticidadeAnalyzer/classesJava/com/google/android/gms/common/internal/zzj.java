package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public abstract class zzj extends zzb implements zzi {
  public zzj() {
    super("com.google.android.gms.common.internal.ICertData");
  }
  
  public static zzi zzb(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
    return (iInterface instanceof zzi) ? (zzi)iInterface : new zzk(paramIBinder);
  }
  
  protected final boolean zza(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 2)
        return false; 
      paramInt1 = zzc();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
    } else {
      IObjectWrapper iObjectWrapper = zzb();
      paramParcel2.writeNoException();
      zzc.zza(paramParcel2, (IInterface)iObjectWrapper);
    } 
    return true;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */