package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzv;
import com.google.android.gms.location.zzx;
import com.google.android.gms.location.zzy;

public final class zzbf extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzbf> CREATOR = new zzbg();
  
  private PendingIntent zzbv;
  
  private int zzcg;
  
  private zzaj zzcj;
  
  private zzbd zzdl;
  
  private zzx zzdm;
  
  private zzu zzdn;
  
  zzbf(int paramInt, zzbd paramzzbd, IBinder paramIBinder1, PendingIntent paramPendingIntent, IBinder paramIBinder2, IBinder paramIBinder3) {
    zzx zzx1;
    zzu zzu1;
    IInterface iInterface;
    this.zzcg = paramInt;
    this.zzdl = paramzzbd;
    zzu zzu2 = null;
    if (paramIBinder1 == null) {
      paramzzbd = null;
    } else {
      zzx1 = zzy.zzc(paramIBinder1);
    } 
    this.zzdm = zzx1;
    this.zzbv = paramPendingIntent;
    if (paramIBinder2 == null) {
      zzx1 = null;
    } else {
      zzu1 = zzv.zzb(paramIBinder2);
    } 
    this.zzdn = zzu1;
    if (paramIBinder3 == null) {
      zzu1 = zzu2;
    } else if (paramIBinder3 == null) {
      zzu1 = zzu2;
    } else {
      iInterface = paramIBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
      if (iInterface instanceof zzaj) {
        iInterface = iInterface;
      } else {
        iInterface = new zzal(paramIBinder3);
      } 
    } 
    this.zzcj = (zzaj)iInterface;
  }
  
  public static zzbf zza(zzu paramzzu, zzaj paramzzaj) {
    IBinder iBinder = paramzzu.asBinder();
    if (paramzzaj != null) {
      IBinder iBinder1 = paramzzaj.asBinder();
    } else {
      paramzzu = null;
    } 
    return new zzbf(2, null, null, null, iBinder, (IBinder)paramzzu);
  }
  
  public static zzbf zza(zzx paramzzx, zzaj paramzzaj) {
    IBinder iBinder = paramzzx.asBinder();
    if (paramzzaj != null) {
      IBinder iBinder1 = paramzzaj.asBinder();
    } else {
      paramzzx = null;
    } 
    return new zzbf(2, null, iBinder, null, null, (IBinder)paramzzx);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder3;
    IBinder iBinder2;
    IBinder iBinder1;
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzcg);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)this.zzdl, paramInt, false);
    zzx zzx1 = this.zzdm;
    zzaj zzaj2 = null;
    if (zzx1 == null) {
      zzx1 = null;
    } else {
      iBinder3 = zzx1.asBinder();
    } 
    SafeParcelWriter.writeIBinder(paramParcel, 3, iBinder3, false);
    SafeParcelWriter.writeParcelable(paramParcel, 4, (Parcelable)this.zzbv, paramInt, false);
    zzu zzu1 = this.zzdn;
    if (zzu1 == null) {
      zzu1 = null;
    } else {
      iBinder2 = zzu1.asBinder();
    } 
    SafeParcelWriter.writeIBinder(paramParcel, 5, iBinder2, false);
    zzaj zzaj1 = this.zzcj;
    if (zzaj1 == null) {
      zzaj1 = zzaj2;
    } else {
      iBinder1 = zzaj1.asBinder();
    } 
    SafeParcelWriter.writeIBinder(paramParcel, 6, iBinder1, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzbf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */