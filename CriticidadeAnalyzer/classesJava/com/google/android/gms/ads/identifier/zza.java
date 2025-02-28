package com.google.android.gms.ads.identifier;

import android.net.Uri;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

final class zza extends Thread {
  private final Map zzl;
  
  zza(AdvertisingIdClient paramAdvertisingIdClient, Map paramMap) {}
  
  public final void run() {
    String str1;
    StringBuilder stringBuilder;
    String str2;
    new zzc();
    Map map = this.zzl;
    Uri.Builder builder = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
    for (String null : map.keySet())
      builder.appendQueryParameter(str1, (String)map.get(str1)); 
    String str3 = builder.build().toString();
    try {
      URL uRL = new URL();
      this(str3);
      HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
      try {
        int i = httpURLConnection.getResponseCode();
        if (i < 200 || i >= 300) {
          int j = String.valueOf(str3).length();
          stringBuilder = new StringBuilder();
          this(j + 65);
          stringBuilder.append("Received non-success response code ");
          stringBuilder.append(i);
          stringBuilder.append(" from pinging URL: ");
          stringBuilder.append(str3);
          stringBuilder.toString();
        } 
        return;
      } finally {
        httpURLConnection.disconnect();
      } 
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      str1 = indexOutOfBoundsException.getMessage();
      stringBuilder = new StringBuilder(String.valueOf(str3).length() + 32 + String.valueOf(str1).length());
      str2 = "Error while parsing ping URL: ";
    } catch (IOException iOException) {
      str1 = iOException.getMessage();
      stringBuilder = new StringBuilder(String.valueOf(str3).length() + 27 + String.valueOf(str1).length());
      str2 = "Error while pinging URL: ";
    } catch (RuntimeException runtimeException) {
      str1 = runtimeException.getMessage();
      stringBuilder = new StringBuilder(String.valueOf(str3).length() + 27 + String.valueOf(str1).length());
      str2 = "Error while pinging URL: ";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(str3);
    stringBuilder.append(". ");
    stringBuilder.append(str1);
    stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/ads/identifier/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */