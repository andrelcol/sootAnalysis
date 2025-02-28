package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Iterator;

public final class zzad extends AbstractSafeParcelable implements Iterable<String> {
  public static final Parcelable.Creator<zzad> CREATOR = new zzaf();
  
  private final Bundle zzaid;
  
  zzad(Bundle paramBundle) {
    this.zzaid = paramBundle;
  }
  
  final Object get(String paramString) {
    return this.zzaid.get(paramString);
  }
  
  final Long getLong(String paramString) {
    return Long.valueOf(this.zzaid.getLong(paramString));
  }
  
  final String getString(String paramString) {
    return this.zzaid.getString(paramString);
  }
  
  public final Iterator<String> iterator() {
    return new zzae(this);
  }
  
  public final int size() {
    return this.zzaid.size();
  }
  
  public final String toString() {
    return this.zzaid.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeBundle(paramParcel, 2, zziy(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
  
  final Double zzbr(String paramString) {
    return Double.valueOf(this.zzaid.getDouble(paramString));
  }
  
  public final Bundle zziy() {
    return new Bundle(this.zzaid);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */