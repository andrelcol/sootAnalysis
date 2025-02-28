package com.google.android.gms.common;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

abstract class zze extends zzj {
  private int zzt;
  
  protected zze(byte[] paramArrayOfbyte) {
    boolean bool;
    if (paramArrayOfbyte.length == 25) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    this.zzt = Arrays.hashCode(paramArrayOfbyte);
  }
  
  protected static byte[] zza(String paramString) {
    try {
      return paramString.getBytes("ISO-8859-1");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new AssertionError(unsupportedEncodingException);
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject != null && paramObject instanceof com.google.android.gms.common.internal.zzi)
      try {
        paramObject = paramObject;
        if (paramObject.zzc() != hashCode())
          return false; 
        paramObject = paramObject.zzb();
        if (paramObject == null)
          return false; 
        paramObject = ObjectWrapper.unwrap((IObjectWrapper)paramObject);
        return Arrays.equals(getBytes(), (byte[])paramObject);
      } catch (RemoteException remoteException) {} 
    return false;
  }
  
  abstract byte[] getBytes();
  
  public int hashCode() {
    return this.zzt;
  }
  
  public final IObjectWrapper zzb() {
    return ObjectWrapper.wrap(getBytes());
  }
  
  public final int zzc() {
    return hashCode();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */