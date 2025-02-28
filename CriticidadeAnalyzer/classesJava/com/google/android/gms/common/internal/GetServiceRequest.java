package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class GetServiceRequest extends AbstractSafeParcelable {
  public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzd();
  
  private final int version = 4;
  
  private final int zzdg;
  
  private int zzdh;
  
  IBinder zzdi;
  
  Scope[] zzdj;
  
  Bundle zzdk;
  
  Account zzdl;
  
  Feature[] zzdm;
  
  Feature[] zzdn;
  
  private boolean zzdo;
  
  String zzy;
  
  public GetServiceRequest(int paramInt) {
    this.zzdh = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    this.zzdg = paramInt;
    this.zzdo = true;
  }
  
  GetServiceRequest(int paramInt1, int paramInt2, int paramInt3, String paramString, IBinder paramIBinder, Scope[] paramArrayOfScope, Bundle paramBundle, Account paramAccount, Feature[] paramArrayOfFeature1, Feature[] paramArrayOfFeature2, boolean paramBoolean) {
    this.zzdg = paramInt2;
    this.zzdh = paramInt3;
    if ("com.google.android.gms".equals(paramString)) {
      this.zzy = "com.google.android.gms";
    } else {
      this.zzy = paramString;
    } 
    if (paramInt1 < 2) {
      Account account;
      paramString = null;
      if (paramIBinder != null)
        account = AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(paramIBinder)); 
      this.zzdl = account;
    } else {
      this.zzdi = paramIBinder;
      this.zzdl = paramAccount;
    } 
    this.zzdj = paramArrayOfScope;
    this.zzdk = paramBundle;
    this.zzdm = paramArrayOfFeature1;
    this.zzdn = paramArrayOfFeature2;
    this.zzdo = paramBoolean;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.version);
    SafeParcelWriter.writeInt(paramParcel, 2, this.zzdg);
    SafeParcelWriter.writeInt(paramParcel, 3, this.zzdh);
    SafeParcelWriter.writeString(paramParcel, 4, this.zzy, false);
    SafeParcelWriter.writeIBinder(paramParcel, 5, this.zzdi, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 6, (Parcelable[])this.zzdj, paramInt, false);
    SafeParcelWriter.writeBundle(paramParcel, 7, this.zzdk, false);
    SafeParcelWriter.writeParcelable(paramParcel, 8, (Parcelable)this.zzdl, paramInt, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 10, (Parcelable[])this.zzdm, paramInt, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 11, (Parcelable[])this.zzdn, paramInt, false);
    SafeParcelWriter.writeBoolean(paramParcel, 12, this.zzdo);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/GetServiceRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */