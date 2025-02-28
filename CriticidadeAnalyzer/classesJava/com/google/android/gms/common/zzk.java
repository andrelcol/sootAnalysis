package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzk extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzk> CREATOR = new zzl();
  
  private final boolean zzaa;
  
  private final boolean zzab;
  
  private final String zzy;
  
  private final zze zzz;
  
  zzk(String paramString, IBinder paramIBinder, boolean paramBoolean1, boolean paramBoolean2) {
    this.zzy = paramString;
    this.zzz = zza(paramIBinder);
    this.zzaa = paramBoolean1;
    this.zzab = paramBoolean2;
  }
  
  zzk(String paramString, zze paramzze, boolean paramBoolean1, boolean paramBoolean2) {
    this.zzy = paramString;
    this.zzz = paramzze;
    this.zzaa = paramBoolean1;
    this.zzab = paramBoolean2;
  }
  
  private static zze zza(IBinder paramIBinder) {
    zzf zzf = null;
    if (paramIBinder == null)
      return null; 
    try {
      byte[] arrayOfByte;
      IObjectWrapper iObjectWrapper = zzj.zzb(paramIBinder).zzb();
      if (iObjectWrapper == null) {
        iObjectWrapper = null;
      } else {
        arrayOfByte = (byte[])ObjectWrapper.unwrap(iObjectWrapper);
      } 
      if (arrayOfByte != null)
        zzf = new zzf(arrayOfByte); 
    } catch (RemoteException remoteException) {}
    return zzf;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 1, this.zzy, false);
    zze zze1 = this.zzz;
    if (zze1 == null) {
      zze1 = null;
    } else {
      zze1.asBinder();
    } 
    SafeParcelWriter.writeIBinder(paramParcel, 2, (IBinder)zze1, false);
    SafeParcelWriter.writeBoolean(paramParcel, 3, this.zzaa);
    SafeParcelWriter.writeBoolean(paramParcel, 4, this.zzab);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */