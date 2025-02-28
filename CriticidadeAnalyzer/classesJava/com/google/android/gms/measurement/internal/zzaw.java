package com.google.android.gms.measurement.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public final class zzaw extends zzfm {
  private final SSLSocketFactory zzamo;
  
  public zzaw(zzfn paramzzfn) {
    super(paramzzfn);
    if (Build.VERSION.SDK_INT < 19) {
      zzfy zzfy = new zzfy();
    } else {
      paramzzfn = null;
    } 
    this.zzamo = (SSLSocketFactory)paramzzfn;
  }
  
  private static byte[] zzb(HttpURLConnection paramHttpURLConnection) throws IOException {
    InputStream inputStream2 = null;
    InputStream inputStream1 = inputStream2;
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      inputStream1 = inputStream2;
      this();
      inputStream1 = inputStream2;
      InputStream inputStream = paramHttpURLConnection.getInputStream();
      inputStream1 = inputStream;
      byte[] arrayOfByte = new byte[1024];
      while (true) {
        inputStream1 = inputStream;
        int i = inputStream.read(arrayOfByte);
        if (i > 0) {
          inputStream1 = inputStream;
          byteArrayOutputStream.write(arrayOfByte, 0, i);
          continue;
        } 
        inputStream1 = inputStream;
        arrayOfByte = byteArrayOutputStream.toByteArray();
        return arrayOfByte;
      } 
    } finally {
      if (inputStream1 != null)
        inputStream1.close(); 
    } 
  }
  
  protected final HttpURLConnection zzb(URL paramURL) throws IOException {
    URLConnection uRLConnection = paramURL.openConnection();
    if (uRLConnection instanceof HttpURLConnection) {
      SSLSocketFactory sSLSocketFactory = this.zzamo;
      if (sSLSocketFactory != null && uRLConnection instanceof HttpsURLConnection)
        ((HttpsURLConnection)uRLConnection).setSSLSocketFactory(sSLSocketFactory); 
      HttpURLConnection httpURLConnection = (HttpURLConnection)uRLConnection;
      httpURLConnection.setDefaultUseCaches(false);
      httpURLConnection.setConnectTimeout(60000);
      httpURLConnection.setReadTimeout(61000);
      httpURLConnection.setInstanceFollowRedirects(false);
      httpURLConnection.setDoInput(true);
      return httpURLConnection;
    } 
    throw new IOException("Failed to obtain HTTP connection");
  }
  
  public final boolean zzfb() {
    zzcl();
    ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService("connectivity");
    try {
      NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    } catch (SecurityException securityException) {
      securityException = null;
    } 
    return (securityException != null && securityException.isConnected());
  }
  
  protected final boolean zzgy() {
    return false;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzaw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */