package com.google.android.gms.common.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
public final class IOUtils {
  public static void closeQuietly(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (IOException iOException) {} 
  }
  
  @Deprecated
  public static long copyStream(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean, int paramInt) throws IOException {
    null = new byte[paramInt];
    long l = 0L;
    try {
      while (true) {
        int i = paramInputStream.read(null, 0, paramInt);
        if (i != -1) {
          l += i;
          paramOutputStream.write(null, 0, i);
          continue;
        } 
        return l;
      } 
    } finally {
      if (paramBoolean) {
        closeQuietly(paramInputStream);
        closeQuietly(paramOutputStream);
      } 
    } 
  }
  
  @Deprecated
  public static byte[] readInputStreamFully(InputStream paramInputStream) throws IOException {
    return readInputStreamFully(paramInputStream, true);
  }
  
  @Deprecated
  public static byte[] readInputStreamFully(InputStream paramInputStream, boolean paramBoolean) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    zza(paramInputStream, byteArrayOutputStream, paramBoolean);
    return byteArrayOutputStream.toByteArray();
  }
  
  @Deprecated
  private static long zza(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean) throws IOException {
    return copyStream(paramInputStream, paramOutputStream, paramBoolean, 1024);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/IOUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */