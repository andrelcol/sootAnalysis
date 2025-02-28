package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.android.gms.internal.firebase_messaging.zzc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

final class zzy {
  private final zzz zza(Context paramContext, String paramString, zzz paramzzz, boolean paramBoolean) {
    Log.isLoggable("FirebaseInstanceId", 3);
    Properties properties = new Properties();
    properties.setProperty("pub", zzz.zza(paramzzz));
    properties.setProperty("pri", zzz.zzb(paramzzz));
    properties.setProperty("cre", String.valueOf(zzz.zzc(paramzzz)));
    File file = zzf(paramContext, paramString);
    try {
      file.createNewFile();
      RandomAccessFile randomAccessFile = new RandomAccessFile();
      this(file, "rw");
      try {
        FileChannel fileChannel = randomAccessFile.getChannel();
      } finally {
        paramzzz = null;
      } 
    } catch (IOException iOException) {
      paramString = String.valueOf(iOException);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 21);
      stringBuilder.append("Failed to write key: ");
      stringBuilder.append(paramString);
      stringBuilder.toString();
      return null;
    } 
  }
  
  private static zzz zza(SharedPreferences paramSharedPreferences, String paramString) throws zzaa {
    zzz zzz;
    String str1 = zzaw.zzd(paramString, "|P|");
    String str2 = null;
    String str3 = paramSharedPreferences.getString(str1, null);
    String str4 = paramSharedPreferences.getString(zzaw.zzd(paramString, "|K|"), null);
    str1 = str2;
    if (str3 != null)
      if (str4 == null) {
        str1 = str2;
      } else {
        zzz = new zzz(zzc(str3, str4), zzb(paramSharedPreferences, paramString));
      }  
    return zzz;
  }
  
  private final zzz zza(File paramFile) throws zzaa, IOException {
    FileInputStream fileInputStream = new FileInputStream(paramFile);
    try {
      FileChannel fileChannel = fileInputStream.getChannel();
    } finally {
      Exception exception = null;
    } 
  }
  
  private static zzz zza(FileChannel paramFileChannel) throws zzaa, IOException {
    Properties properties = new Properties();
    properties.load(Channels.newInputStream(paramFileChannel));
    String str2 = properties.getProperty("pub");
    String str1 = properties.getProperty("pri");
    if (str2 != null && str1 != null) {
      KeyPair keyPair = zzc(str2, str1);
      try {
        long l = Long.parseLong(properties.getProperty("cre"));
        return new zzz(keyPair, l);
      } catch (NumberFormatException numberFormatException) {
        throw new zzaa(numberFormatException);
      } 
    } 
    throw new zzaa("Invalid properties file");
  }
  
  static void zza(Context paramContext) {
    for (File file : zzb(paramContext).listFiles()) {
      if (file.getName().startsWith("com.google.InstanceId"))
        file.delete(); 
    } 
  }
  
  private final void zza(Context paramContext, String paramString, zzz paramzzz) {
    SharedPreferences sharedPreferences = paramContext.getSharedPreferences("com.google.android.gms.appid", 0);
    try {
      boolean bool = paramzzz.equals(zza(sharedPreferences, paramString));
      if (bool)
        return; 
    } catch (zzaa zzaa) {}
    Log.isLoggable("FirebaseInstanceId", 3);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(zzaw.zzd(paramString, "|P|"), zzz.zza(paramzzz));
    editor.putString(zzaw.zzd(paramString, "|K|"), zzz.zzb(paramzzz));
    editor.putString(zzaw.zzd(paramString, "cre"), String.valueOf(zzz.zzc(paramzzz)));
    editor.commit();
  }
  
  private static long zzb(SharedPreferences paramSharedPreferences, String paramString) {
    String str = paramSharedPreferences.getString(zzaw.zzd(paramString, "cre"), null);
    if (str != null)
      try {
        return Long.parseLong(str);
      } catch (NumberFormatException numberFormatException) {} 
    return 0L;
  }
  
  private static File zzb(Context paramContext) {
    File file = ContextCompat.getNoBackupFilesDir(paramContext);
    return (file != null && file.isDirectory()) ? file : paramContext.getFilesDir();
  }
  
  private static KeyPair zzc(String paramString1, String paramString2) throws zzaa {
    try {
      byte[] arrayOfByte2 = Base64.decode(paramString1, 8);
      byte[] arrayOfByte1 = Base64.decode(paramString2, 8);
      try {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec();
        this(arrayOfByte2);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec();
        this(arrayOfByte1);
        return new KeyPair(publicKey, keyFactory.generatePrivate(pKCS8EncodedKeySpec));
      } catch (InvalidKeySpecException invalidKeySpecException) {
      
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {}
      String str = String.valueOf(noSuchAlgorithmException);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 19);
      stringBuilder.append("Invalid key stored ");
      stringBuilder.append(str);
      stringBuilder.toString();
      throw new zzaa(noSuchAlgorithmException);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new zzaa(illegalArgumentException);
    } 
  }
  
  private final zzz zzd(Context paramContext, String paramString) throws zzaa {
    try {
      zzz zzz = zze(paramContext, paramString);
      if (zzz != null) {
        zza(paramContext, paramString, zzz);
        return zzz;
      } 
      zzz = null;
    } catch (zzaa null) {}
    try {
      zzz zzz = zza(paramContext.getSharedPreferences("com.google.android.gms.appid", 0), paramString);
      if (zzz != null) {
        zza(paramContext, paramString, zzz, false);
        return zzz;
      } 
    } catch (zzaa zzaa) {}
    if (zzaa == null)
      return null; 
    throw zzaa;
  }
  
  private final zzz zze(Context paramContext, String paramString) throws zzaa {
    File file = zzf(paramContext, paramString);
    if (!file.exists())
      return null; 
    try {
      return zza(file);
    } catch (zzaa zzaa) {
    
    } catch (IOException iOException) {}
    if (Log.isLoggable("FirebaseInstanceId", 3)) {
      String str = String.valueOf(iOException);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 40);
      stringBuilder.append("Failed to read key from file, retrying: ");
      stringBuilder.append(str);
      stringBuilder.toString();
    } 
    try {
      return zza(file);
    } catch (IOException iOException1) {
      String str = String.valueOf(iOException1);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 45);
      stringBuilder.append("IID file exists, but failed to read from it: ");
      stringBuilder.append(str);
      stringBuilder.toString();
      throw new zzaa(iOException1);
    } 
  }
  
  private static File zzf(Context paramContext, String paramString) {
    if (TextUtils.isEmpty(paramString)) {
      paramString = "com.google.InstanceId.properties";
    } else {
      try {
        paramString = Base64.encodeToString(paramString.getBytes("UTF-8"), 11);
        int i = String.valueOf(paramString).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 33);
        stringBuilder.append("com.google.InstanceId_");
        stringBuilder.append(paramString);
        stringBuilder.append(".properties");
        paramString = stringBuilder.toString();
        return new File(zzb(paramContext), paramString);
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        throw new AssertionError(unsupportedEncodingException);
      } 
    } 
    return new File(zzb((Context)unsupportedEncodingException), paramString);
  }
  
  final zzz zzb(Context paramContext, String paramString) throws zzaa {
    zzz zzz = zzd(paramContext, paramString);
    return (zzz != null) ? zzz : zzc(paramContext, paramString);
  }
  
  final zzz zzc(Context paramContext, String paramString) {
    zzz zzz1 = new zzz(zza.zzb(), System.currentTimeMillis());
    zzz zzz2 = zza(paramContext, paramString, zzz1, true);
    if (zzz2 != null && !zzz2.equals(zzz1)) {
      Log.isLoggable("FirebaseInstanceId", 3);
      return zzz2;
    } 
    Log.isLoggable("FirebaseInstanceId", 3);
    zza(paramContext, paramString, zzz1);
    return zzz1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */