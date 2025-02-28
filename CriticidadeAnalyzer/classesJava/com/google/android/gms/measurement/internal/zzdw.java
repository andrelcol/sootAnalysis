package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class zzdw {
  public static String zza(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    Preconditions.checkNotNull(paramArrayOfString1);
    Preconditions.checkNotNull(paramArrayOfString2);
    int i = Math.min(paramArrayOfString1.length, paramArrayOfString2.length);
    for (byte b = 0; b < i; b++) {
      boolean bool;
      String str = paramArrayOfString1[b];
      if (paramString == null && str == null) {
        bool = true;
      } else if (paramString == null) {
        bool = false;
      } else {
        bool = paramString.equals(str);
      } 
      if (bool)
        return paramArrayOfString2[b]; 
    } 
    return null;
  }
  
  public static Object zze(Object paramObject) {
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    if (paramObject == null)
      return null; 
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      objectOutputStream = new ObjectOutputStream();
    } finally {
      paramObject = null;
      objectInputStream = null;
    } 
    if (objectOutputStream != null)
      objectOutputStream.close(); 
    if (objectInputStream != null)
      objectInputStream.close(); 
    throw paramObject;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzdw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */