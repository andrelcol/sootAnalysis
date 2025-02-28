package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

public final class zag extends zaa implements zaf {
  zag(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.signin.internal.ISignInService");
  }
  
  public final void zaa(IAccountAccessor paramIAccountAccessor, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = zaa();
    zac.zaa(parcel, (IInterface)paramIAccountAccessor);
    parcel.writeInt(paramInt);
    zac.writeBoolean(parcel, paramBoolean);
    zab(9, parcel);
  }
  
  public final void zaa(zah paramzah, zad paramzad) throws RemoteException {
    Parcel parcel = zaa();
    zac.zaa(parcel, (Parcelable)paramzah);
    zac.zaa(parcel, paramzad);
    zab(12, parcel);
  }
  
  public final void zam(int paramInt) throws RemoteException {
    Parcel parcel = zaa();
    parcel.writeInt(paramInt);
    zab(7, parcel);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/signin/internal/zag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */