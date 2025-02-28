package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;

public final class MapStyleOptions extends AbstractSafeParcelable {
  public static final Parcelable.Creator<MapStyleOptions> CREATOR = new zzg();
  
  private String zzdl;
  
  public MapStyleOptions(String paramString) {
    this.zzdl = paramString;
  }
  
  public static MapStyleOptions loadRawResourceStyle(Context paramContext, int paramInt) throws Resources.NotFoundException {
    InputStream inputStream = paramContext.getResources().openRawResource(paramInt);
    try {
      byte[] arrayOfByte = IOUtils.readInputStreamFully(inputStream);
      String str = new String();
      this(arrayOfByte, "UTF-8");
      return new MapStyleOptions(str);
    } catch (IOException iOException) {
      String str = String.valueOf(iOException);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 37);
      stringBuilder.append("Failed to read resource ");
      stringBuilder.append(paramInt);
      stringBuilder.append(": ");
      stringBuilder.append(str);
      throw new Resources.NotFoundException(stringBuilder.toString());
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 2, this.zzdl, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/model/MapStyleOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */