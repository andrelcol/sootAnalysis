package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.zzj;
import java.util.Collections;
import java.util.List;

public final class zzm extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzm> CREATOR;
  
  static final List<ClientIdentity> zzcd = Collections.emptyList();
  
  static final zzj zzce = new zzj();
  
  private String tag;
  
  private zzj zzcf;
  
  private List<ClientIdentity> zzm;
  
  static {
    CREATOR = new zzn();
  }
  
  zzm(zzj paramzzj, List<ClientIdentity> paramList, String paramString) {
    this.zzcf = paramzzj;
    this.zzm = paramList;
    this.tag = paramString;
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof zzm))
      return false; 
    paramObject = paramObject;
    return (Objects.equal(this.zzcf, ((zzm)paramObject).zzcf) && Objects.equal(this.zzm, ((zzm)paramObject).zzm) && Objects.equal(this.tag, ((zzm)paramObject).tag));
  }
  
  public final int hashCode() {
    return this.zzcf.hashCode();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeParcelable(paramParcel, 1, (Parcelable)this.zzcf, paramInt, false);
    SafeParcelWriter.writeTypedList(paramParcel, 2, this.zzm, false);
    SafeParcelWriter.writeString(paramParcel, 3, this.tag, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */