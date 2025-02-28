package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.zzr;
import com.google.android.gms.location.zzs;

public final class zzo extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzo> CREATOR = new zzp();
  
  private int zzcg;
  
  private zzm zzch;
  
  private zzr zzci;
  
  private zzaj zzcj;
  
  zzo(int paramInt, zzm paramzzm, IBinder paramIBinder1, IBinder paramIBinder2) {
    zzr zzr1;
    IInterface iInterface;
    this.zzcg = paramInt;
    this.zzch = paramzzm;
    zzr zzr2 = null;
    if (paramIBinder1 == null) {
      paramzzm = null;
    } else {
      zzr1 = zzs.zza(paramIBinder1);
    } 
    this.zzci = zzr1;
    if (paramIBinder2 == null) {
      zzr1 = zzr2;
    } else if (paramIBinder2 == null) {
      zzr1 = zzr2;
    } else {
      iInterface = paramIBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
      if (iInterface instanceof zzaj) {
        iInterface = iInterface;
      } else {
        iInterface = new zzal(paramIBinder2);
      } 
    } 
    this.zzcj = (zzaj)iInterface;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder2;
    IBinder iBinder1;
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzcg);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)this.zzch, paramInt, false);
    zzr zzr1 = this.zzci;
    zzaj zzaj2 = null;
    if (zzr1 == null) {
      zzr1 = null;
    } else {
      iBinder2 = zzr1.asBinder();
    } 
    SafeParcelWriter.writeIBinder(paramParcel, 3, iBinder2, false);
    zzaj zzaj1 = this.zzcj;
    if (zzaj1 == null) {
      zzaj1 = zzaj2;
    } else {
      iBinder1 = zzaj1.asBinder();
    } 
    SafeParcelWriter.writeIBinder(paramParcel, 4, iBinder1, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */