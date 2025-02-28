package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzk extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzk> CREATOR = new zzl();
  
  public final String packageName;
  
  public final long zzade;
  
  public final String zzafi;
  
  public final String zzafk;
  
  public final long zzafo;
  
  public final String zzafp;
  
  public final long zzafq;
  
  public final boolean zzafr;
  
  public final long zzafs;
  
  public final boolean zzaft;
  
  public final boolean zzafu;
  
  public final String zzafv;
  
  public final String zzagm;
  
  public final boolean zzagn;
  
  public final long zzago;
  
  public final int zzagp;
  
  public final boolean zzagq;
  
  public final String zzts;
  
  zzk(String paramString1, String paramString2, String paramString3, long paramLong1, String paramString4, long paramLong2, long paramLong3, String paramString5, boolean paramBoolean1, boolean paramBoolean2, String paramString6, long paramLong4, long paramLong5, int paramInt, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString7) {
    Preconditions.checkNotEmpty(paramString1);
    this.packageName = paramString1;
    if (TextUtils.isEmpty(paramString2))
      paramString2 = null; 
    this.zzafi = paramString2;
    this.zzts = paramString3;
    this.zzafo = paramLong1;
    this.zzafp = paramString4;
    this.zzade = paramLong2;
    this.zzafq = paramLong3;
    this.zzagm = paramString5;
    this.zzafr = paramBoolean1;
    this.zzagn = paramBoolean2;
    this.zzafk = paramString6;
    this.zzafs = paramLong4;
    this.zzago = paramLong5;
    this.zzagp = paramInt;
    this.zzaft = paramBoolean3;
    this.zzafu = paramBoolean4;
    this.zzagq = paramBoolean5;
    this.zzafv = paramString7;
  }
  
  zzk(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong1, long paramLong2, String paramString5, boolean paramBoolean1, boolean paramBoolean2, long paramLong3, String paramString6, long paramLong4, long paramLong5, int paramInt, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String paramString7) {
    this.packageName = paramString1;
    this.zzafi = paramString2;
    this.zzts = paramString3;
    this.zzafo = paramLong3;
    this.zzafp = paramString4;
    this.zzade = paramLong1;
    this.zzafq = paramLong2;
    this.zzagm = paramString5;
    this.zzafr = paramBoolean1;
    this.zzagn = paramBoolean2;
    this.zzafk = paramString6;
    this.zzafs = paramLong4;
    this.zzago = paramLong5;
    this.zzagp = paramInt;
    this.zzaft = paramBoolean3;
    this.zzafu = paramBoolean4;
    this.zzagq = paramBoolean5;
    this.zzafv = paramString7;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 2, this.packageName, false);
    SafeParcelWriter.writeString(paramParcel, 3, this.zzafi, false);
    SafeParcelWriter.writeString(paramParcel, 4, this.zzts, false);
    SafeParcelWriter.writeString(paramParcel, 5, this.zzafp, false);
    SafeParcelWriter.writeLong(paramParcel, 6, this.zzade);
    SafeParcelWriter.writeLong(paramParcel, 7, this.zzafq);
    SafeParcelWriter.writeString(paramParcel, 8, this.zzagm, false);
    SafeParcelWriter.writeBoolean(paramParcel, 9, this.zzafr);
    SafeParcelWriter.writeBoolean(paramParcel, 10, this.zzagn);
    SafeParcelWriter.writeLong(paramParcel, 11, this.zzafo);
    SafeParcelWriter.writeString(paramParcel, 12, this.zzafk, false);
    SafeParcelWriter.writeLong(paramParcel, 13, this.zzafs);
    SafeParcelWriter.writeLong(paramParcel, 14, this.zzago);
    SafeParcelWriter.writeInt(paramParcel, 15, this.zzagp);
    SafeParcelWriter.writeBoolean(paramParcel, 16, this.zzaft);
    SafeParcelWriter.writeBoolean(paramParcel, 17, this.zzafu);
    SafeParcelWriter.writeBoolean(paramParcel, 18, this.zzagq);
    SafeParcelWriter.writeString(paramParcel, 19, this.zzafv, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */