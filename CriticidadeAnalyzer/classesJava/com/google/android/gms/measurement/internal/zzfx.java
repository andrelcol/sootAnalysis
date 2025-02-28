package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

public final class zzfx extends zzcs {
  private static final String[] zzauq = new String[] { "firebase_", "google_", "ga_" };
  
  private int zzado;
  
  private SecureRandom zzaur;
  
  private final AtomicLong zzaus = new AtomicLong(0L);
  
  private Integer zzaut = null;
  
  zzfx(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  static MessageDigest getMessageDigest() {
    byte b = 0;
    while (true) {
      if (b < 2) {
        try {
          MessageDigest messageDigest = MessageDigest.getInstance("MD5");
          if (messageDigest != null)
            return messageDigest; 
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {}
        b++;
        continue;
      } 
      return null;
    } 
  }
  
  private static Object zza(int paramInt, Object paramObject, boolean paramBoolean) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof Long || paramObject instanceof Double)
      return paramObject; 
    if (paramObject instanceof Integer)
      return Long.valueOf(((Integer)paramObject).intValue()); 
    if (paramObject instanceof Byte)
      return Long.valueOf(((Byte)paramObject).byteValue()); 
    if (paramObject instanceof Short)
      return Long.valueOf(((Short)paramObject).shortValue()); 
    if (paramObject instanceof Boolean) {
      long l;
      if (((Boolean)paramObject).booleanValue()) {
        l = 1L;
      } else {
        l = 0L;
      } 
      return Long.valueOf(l);
    } 
    return (paramObject instanceof Float) ? Double.valueOf(((Float)paramObject).doubleValue()) : ((paramObject instanceof String || paramObject instanceof Character || paramObject instanceof CharSequence) ? zza(String.valueOf(paramObject), paramInt, paramBoolean) : null);
  }
  
  public static String zza(String paramString, int paramInt, boolean paramBoolean) {
    return (paramString == null) ? null : ((paramString.codePointCount(0, paramString.length()) > paramInt) ? (paramBoolean ? String.valueOf(paramString.substring(0, paramString.offsetByCodePoints(0, paramInt))).concat("...") : null) : paramString);
  }
  
  private static void zza(Bundle paramBundle, Object paramObject) {
    Preconditions.checkNotNull(paramBundle);
    if (paramObject != null && (paramObject instanceof String || paramObject instanceof CharSequence))
      paramBundle.putLong("_el", String.valueOf(paramObject).length()); 
  }
  
  static boolean zza(Context paramContext, boolean paramBoolean) {
    Preconditions.checkNotNull(paramContext);
    return (Build.VERSION.SDK_INT >= 24) ? zzc(paramContext, "com.google.android.gms.measurement.AppMeasurementJobService") : zzc(paramContext, "com.google.android.gms.measurement.AppMeasurementService");
  }
  
  private static boolean zza(Bundle paramBundle, int paramInt) {
    if (paramBundle.getLong("_err") == 0L) {
      paramBundle.putLong("_err", paramInt);
      return true;
    } 
    return false;
  }
  
  private final boolean zza(String paramString1, String paramString2, int paramInt, Object paramObject, boolean paramBoolean) {
    if (paramObject == null)
      return true; 
    if (!(paramObject instanceof Long) && !(paramObject instanceof Float) && !(paramObject instanceof Integer) && !(paramObject instanceof Byte) && !(paramObject instanceof Short) && !(paramObject instanceof Boolean) && !(paramObject instanceof Double)) {
      if (paramObject instanceof String || paramObject instanceof Character || paramObject instanceof CharSequence) {
        paramObject = String.valueOf(paramObject);
        if (paramObject.codePointCount(0, paramObject.length()) > paramInt) {
          zzgt().zzjj().zzd("Value is too long; discarded. Value kind, name, value length", paramString1, paramString2, Integer.valueOf(paramObject.length()));
          return false;
        } 
        return true;
      } 
      if (paramObject instanceof Bundle && paramBoolean)
        return true; 
      if (paramObject instanceof Parcelable[] && paramBoolean) {
        Parcelable[] arrayOfParcelable = (Parcelable[])paramObject;
        int i = arrayOfParcelable.length;
        for (paramInt = 0; paramInt < i; paramInt++) {
          paramObject = arrayOfParcelable[paramInt];
          if (!(paramObject instanceof Bundle)) {
            zzgt().zzjj().zze("All Parcelable[] elements must be of type Bundle. Value type, name", paramObject.getClass(), paramString2);
            return false;
          } 
        } 
        return true;
      } 
      if (paramObject instanceof ArrayList && paramBoolean) {
        ArrayList arrayList = (ArrayList)paramObject;
        int i = arrayList.size();
        paramInt = 0;
        while (paramInt < i) {
          paramObject = arrayList.get(paramInt);
          paramInt++;
          if (!(paramObject instanceof Bundle)) {
            zzgt().zzjj().zze("All ArrayList elements must be of type Bundle. Value type, name", paramObject.getClass(), paramString2);
            return false;
          } 
        } 
        return true;
      } 
      return false;
    } 
    return true;
  }
  
  static boolean zza(String paramString1, String paramString2, String paramString3, String paramString4) {
    boolean bool2 = TextUtils.isEmpty(paramString1);
    boolean bool1 = TextUtils.isEmpty(paramString2);
    return (!bool2 && !bool1) ? (!paramString1.equals(paramString2)) : ((bool2 && bool1) ? ((!TextUtils.isEmpty(paramString3) && !TextUtils.isEmpty(paramString4)) ? (!paramString3.equals(paramString4)) : (!TextUtils.isEmpty(paramString4))) : ((!bool2 && bool1) ? (TextUtils.isEmpty(paramString4) ? false : ((TextUtils.isEmpty(paramString3) || !paramString3.equals(paramString4)))) : ((TextUtils.isEmpty(paramString3) || !paramString3.equals(paramString4)))));
  }
  
  static byte[] zza(Parcelable paramParcelable) {
    if (paramParcelable == null)
      return null; 
    Parcel parcel = Parcel.obtain();
    try {
      paramParcelable.writeToParcel(parcel, 0);
      return parcel.marshall();
    } finally {
      parcel.recycle();
    } 
  }
  
  public static long zzc(long paramLong1, long paramLong2) {
    return (paramLong1 + paramLong2 * 60000L) / 86400000L;
  }
  
  static long zzc(byte[] paramArrayOfbyte) {
    boolean bool2;
    Preconditions.checkNotNull(paramArrayOfbyte);
    int i = paramArrayOfbyte.length;
    boolean bool1 = false;
    if (i > 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkState(bool2);
    long l = 0L;
    for (i = paramArrayOfbyte.length - 1; i >= 0 && i >= paramArrayOfbyte.length - 8; i--) {
      l += (paramArrayOfbyte[i] & 0xFFL) << bool1;
      bool1 += true;
    } 
    return l;
  }
  
  private static boolean zzc(Context paramContext, String paramString) {
    try {
      PackageManager packageManager = paramContext.getPackageManager();
      if (packageManager == null)
        return false; 
      ComponentName componentName = new ComponentName();
      this(paramContext, paramString);
      ServiceInfo serviceInfo = packageManager.getServiceInfo(componentName, 0);
      if (serviceInfo != null) {
        boolean bool = serviceInfo.enabled;
        if (bool)
          return true; 
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return false;
  }
  
  static boolean zzc(Intent paramIntent) {
    String str = paramIntent.getStringExtra("android.intent.extra.REFERRER_NAME");
    return ("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(str) || "https://www.google.com".equals(str) || "android-app://com.google.appcrawler".equals(str));
  }
  
  static boolean zzct(String paramString) {
    Preconditions.checkNotEmpty(paramString);
    return (paramString.charAt(0) != '_' || paramString.equals("_ep"));
  }
  
  private static boolean zzcw(String paramString) {
    Preconditions.checkNotNull(paramString);
    return paramString.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
  }
  
  private static int zzcx(String paramString) {
    return "_ldl".equals(paramString) ? 2048 : ("_id".equals(paramString) ? 256 : 36);
  }
  
  static boolean zzcy(String paramString) {
    return (!TextUtils.isEmpty(paramString) && paramString.startsWith("_"));
  }
  
  private final boolean zze(Context paramContext, String paramString) {
    X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
    try {
      PackageInfo packageInfo = Wrappers.packageManager(paramContext).getPackageInfo(paramString, 64);
      if (packageInfo != null && packageInfo.signatures != null && packageInfo.signatures.length > 0) {
        Signature signature = packageInfo.signatures[0];
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
        this(signature.toByteArray());
        return ((X509Certificate)certificateFactory.generateCertificate(byteArrayInputStream)).getSubjectX500Principal().equals(x500Principal);
      } 
    } catch (CertificateException certificateException) {
      zzgt().zzjg().zzg("Error obtaining certificate", certificateException);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      zzgt().zzjg().zzg("Package name not found", nameNotFoundException);
    } 
    return true;
  }
  
  public static Bundle zzf(Bundle paramBundle) {
    if (paramBundle == null)
      return new Bundle(); 
    Bundle bundle = new Bundle(paramBundle);
    for (String str : bundle.keySet()) {
      Object object = bundle.get(str);
      if (object instanceof Bundle) {
        bundle.putBundle(str, new Bundle((Bundle)object));
        continue;
      } 
      boolean bool1 = object instanceof Parcelable[];
      boolean bool = false;
      byte b = 0;
      if (bool1) {
        object = object;
        while (b < object.length) {
          if (object[b] instanceof Bundle)
            object[b] = new Bundle((Bundle)object[b]); 
          b++;
        } 
        continue;
      } 
      if (object instanceof List) {
        List<Bundle> list = (List)object;
        for (b = bool; b < list.size(); b++) {
          object = list.get(b);
          if (object instanceof Bundle)
            list.set(b, new Bundle((Bundle)object)); 
        } 
      } 
    } 
    return bundle;
  }
  
  static Bundle[] zzf(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof android/os/Bundle
    //   4: ifeq -> 19
    //   7: iconst_1
    //   8: anewarray android/os/Bundle
    //   11: dup
    //   12: iconst_0
    //   13: aload_0
    //   14: checkcast android/os/Bundle
    //   17: aastore
    //   18: areturn
    //   19: aload_0
    //   20: instanceof [Landroid/os/Parcelable;
    //   23: ifeq -> 44
    //   26: aload_0
    //   27: checkcast [Landroid/os/Parcelable;
    //   30: astore_0
    //   31: aload_0
    //   32: aload_0
    //   33: arraylength
    //   34: ldc_w [Landroid/os/Bundle;
    //   37: invokestatic copyOf : ([Ljava/lang/Object;ILjava/lang/Class;)[Ljava/lang/Object;
    //   40: checkcast [Landroid/os/Bundle;
    //   43: areturn
    //   44: aload_0
    //   45: instanceof java/util/ArrayList
    //   48: ifeq -> 71
    //   51: aload_0
    //   52: checkcast java/util/ArrayList
    //   55: astore_0
    //   56: aload_0
    //   57: aload_0
    //   58: invokevirtual size : ()I
    //   61: anewarray android/os/Bundle
    //   64: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   67: checkcast [Landroid/os/Bundle;
    //   70: areturn
    //   71: aconst_null
    //   72: areturn
  }
  
  private final boolean zzt(String paramString1, String paramString2) {
    if (paramString2 == null) {
      zzgt().zzjg().zzg("Name is required and can't be null. Type", paramString1);
      return false;
    } 
    if (paramString2.length() == 0) {
      zzgt().zzjg().zzg("Name is required and can't be empty. Type", paramString1);
      return false;
    } 
    int i = paramString2.codePointAt(0);
    if (!Character.isLetter(i) && i != 95) {
      zzgt().zzjg().zze("Name must start with a letter or _ (underscore). Type, name", paramString1, paramString2);
      return false;
    } 
    int j = paramString2.length();
    for (i = Character.charCount(i); i < j; i += Character.charCount(k)) {
      int k = paramString2.codePointAt(i);
      if (k != 95 && !Character.isLetterOrDigit(k)) {
        zzgt().zzjg().zze("Name must consist of letters, digits or _ (underscores). Type, name", paramString1, paramString2);
        return false;
      } 
    } 
    return true;
  }
  
  static boolean zzv(String paramString1, String paramString2) {
    return (paramString1 == null && paramString2 == null) ? true : ((paramString1 == null) ? false : paramString1.equals(paramString2));
  }
  
  final Bundle zza(Uri paramUri) {
    if (paramUri == null)
      return null; 
    try {
      CharSequence charSequence1;
      CharSequence charSequence2;
      CharSequence charSequence3;
      CharSequence charSequence4;
      boolean bool = paramUri.isHierarchical();
      if (bool) {
        charSequence3 = paramUri.getQueryParameter("utm_campaign");
        charSequence1 = paramUri.getQueryParameter("utm_source");
        charSequence4 = paramUri.getQueryParameter("utm_medium");
        charSequence2 = paramUri.getQueryParameter("gclid");
      } else {
        CharSequence charSequence = null;
        charSequence3 = null;
        charSequence1 = charSequence3;
        charSequence2 = charSequence1;
        charSequence4 = charSequence1;
        charSequence1 = charSequence3;
        charSequence3 = charSequence;
      } 
      if (!TextUtils.isEmpty(charSequence3) || !TextUtils.isEmpty(charSequence1) || !TextUtils.isEmpty(charSequence4) || !TextUtils.isEmpty(charSequence2)) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(charSequence3))
          bundle.putString("campaign", (String)charSequence3); 
        if (!TextUtils.isEmpty(charSequence1))
          bundle.putString("source", (String)charSequence1); 
        if (!TextUtils.isEmpty(charSequence4))
          bundle.putString("medium", (String)charSequence4); 
        if (!TextUtils.isEmpty(charSequence2))
          bundle.putString("gclid", (String)charSequence2); 
        charSequence1 = paramUri.getQueryParameter("utm_term");
        if (!TextUtils.isEmpty(charSequence1))
          bundle.putString("term", (String)charSequence1); 
        charSequence1 = paramUri.getQueryParameter("utm_content");
        if (!TextUtils.isEmpty(charSequence1))
          bundle.putString("content", (String)charSequence1); 
        charSequence1 = paramUri.getQueryParameter("aclid");
        if (!TextUtils.isEmpty(charSequence1))
          bundle.putString("aclid", (String)charSequence1); 
        charSequence1 = paramUri.getQueryParameter("cp1");
        if (!TextUtils.isEmpty(charSequence1))
          bundle.putString("cp1", (String)charSequence1); 
        String str = paramUri.getQueryParameter("anid");
        if (!TextUtils.isEmpty(str))
          bundle.putString("anid", str); 
        return bundle;
      } 
      return null;
    } catch (UnsupportedOperationException unsupportedOperationException) {
      zzgt().zzjj().zzg("Install referrer url isn't a hierarchical URI", unsupportedOperationException);
      return null;
    } 
  }
  
  final Bundle zza(String paramString1, String paramString2, Bundle paramBundle, List<String> paramList, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_3
    //   1: ifnull -> 625
    //   4: new android/os/Bundle
    //   7: dup
    //   8: aload_3
    //   9: invokespecial <init> : (Landroid/os/Bundle;)V
    //   12: astore #12
    //   14: aload_3
    //   15: invokevirtual keySet : ()Ljava/util/Set;
    //   18: invokeinterface iterator : ()Ljava/util/Iterator;
    //   23: astore #13
    //   25: iconst_0
    //   26: istore #8
    //   28: aload #12
    //   30: astore #11
    //   32: aload #13
    //   34: invokeinterface hasNext : ()Z
    //   39: ifeq -> 628
    //   42: aload #13
    //   44: invokeinterface next : ()Ljava/lang/Object;
    //   49: checkcast java/lang/String
    //   52: astore #11
    //   54: aload #4
    //   56: ifnull -> 80
    //   59: aload #4
    //   61: aload #11
    //   63: invokeinterface contains : (Ljava/lang/Object;)Z
    //   68: ifne -> 74
    //   71: goto -> 80
    //   74: iconst_0
    //   75: istore #7
    //   77: goto -> 207
    //   80: bipush #14
    //   82: istore #9
    //   84: iload #5
    //   86: ifeq -> 144
    //   89: aload_0
    //   90: ldc_w 'event param'
    //   93: aload #11
    //   95: invokevirtual zzs : (Ljava/lang/String;Ljava/lang/String;)Z
    //   98: ifne -> 107
    //   101: iconst_3
    //   102: istore #7
    //   104: goto -> 147
    //   107: aload_0
    //   108: ldc_w 'event param'
    //   111: aconst_null
    //   112: aload #11
    //   114: invokevirtual zza : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Z
    //   117: ifne -> 127
    //   120: bipush #14
    //   122: istore #7
    //   124: goto -> 147
    //   127: aload_0
    //   128: ldc_w 'event param'
    //   131: bipush #40
    //   133: aload #11
    //   135: invokevirtual zza : (Ljava/lang/String;ILjava/lang/String;)Z
    //   138: ifne -> 144
    //   141: goto -> 101
    //   144: iconst_0
    //   145: istore #7
    //   147: iload #7
    //   149: ifne -> 207
    //   152: aload_0
    //   153: ldc_w 'event param'
    //   156: aload #11
    //   158: invokespecial zzt : (Ljava/lang/String;Ljava/lang/String;)Z
    //   161: ifne -> 170
    //   164: iconst_3
    //   165: istore #7
    //   167: goto -> 207
    //   170: aload_0
    //   171: ldc_w 'event param'
    //   174: aconst_null
    //   175: aload #11
    //   177: invokevirtual zza : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Z
    //   180: ifne -> 190
    //   183: iload #9
    //   185: istore #7
    //   187: goto -> 207
    //   190: aload_0
    //   191: ldc_w 'event param'
    //   194: bipush #40
    //   196: aload #11
    //   198: invokevirtual zza : (Ljava/lang/String;ILjava/lang/String;)Z
    //   201: ifne -> 74
    //   204: goto -> 164
    //   207: iload #7
    //   209: ifeq -> 265
    //   212: aload #12
    //   214: iload #7
    //   216: invokestatic zza : (Landroid/os/Bundle;I)Z
    //   219: ifeq -> 251
    //   222: aload #12
    //   224: ldc_w '_ev'
    //   227: aload #11
    //   229: bipush #40
    //   231: iconst_1
    //   232: invokestatic zza : (Ljava/lang/String;IZ)Ljava/lang/String;
    //   235: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   238: iload #7
    //   240: iconst_3
    //   241: if_icmpne -> 251
    //   244: aload #12
    //   246: aload #11
    //   248: invokestatic zza : (Landroid/os/Bundle;Ljava/lang/Object;)V
    //   251: aload #12
    //   253: aload #11
    //   255: invokevirtual remove : (Ljava/lang/String;)V
    //   258: iload #8
    //   260: istore #7
    //   262: goto -> 513
    //   265: aload_3
    //   266: aload #11
    //   268: invokevirtual get : (Ljava/lang/String;)Ljava/lang/Object;
    //   271: astore #14
    //   273: aload_0
    //   274: invokevirtual zzaf : ()V
    //   277: iload #6
    //   279: ifeq -> 371
    //   282: aload #14
    //   284: instanceof [Landroid/os/Parcelable;
    //   287: ifeq -> 301
    //   290: aload #14
    //   292: checkcast [Landroid/os/Parcelable;
    //   295: arraylength
    //   296: istore #7
    //   298: goto -> 319
    //   301: aload #14
    //   303: instanceof java/util/ArrayList
    //   306: ifeq -> 356
    //   309: aload #14
    //   311: checkcast java/util/ArrayList
    //   314: invokevirtual size : ()I
    //   317: istore #7
    //   319: iload #7
    //   321: sipush #1000
    //   324: if_icmple -> 356
    //   327: aload_0
    //   328: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   331: invokevirtual zzjj : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   334: ldc_w 'Parameter array is too long; discarded. Value kind, name, array length'
    //   337: ldc_w 'param'
    //   340: aload #11
    //   342: iload #7
    //   344: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   347: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   350: iconst_0
    //   351: istore #7
    //   353: goto -> 359
    //   356: iconst_1
    //   357: istore #7
    //   359: iload #7
    //   361: ifne -> 371
    //   364: bipush #17
    //   366: istore #7
    //   368: goto -> 449
    //   371: aload_0
    //   372: invokevirtual zzgv : ()Lcom/google/android/gms/measurement/internal/zzq;
    //   375: aload_1
    //   376: invokevirtual zzav : (Ljava/lang/String;)Z
    //   379: ifeq -> 389
    //   382: aload_2
    //   383: invokestatic zzcy : (Ljava/lang/String;)Z
    //   386: ifne -> 397
    //   389: aload #11
    //   391: invokestatic zzcy : (Ljava/lang/String;)Z
    //   394: ifeq -> 418
    //   397: aload_0
    //   398: ldc_w 'param'
    //   401: aload #11
    //   403: sipush #256
    //   406: aload #14
    //   408: iload #6
    //   410: invokespecial zza : (Ljava/lang/String;Ljava/lang/String;ILjava/lang/Object;Z)Z
    //   413: istore #10
    //   415: goto -> 435
    //   418: aload_0
    //   419: ldc_w 'param'
    //   422: aload #11
    //   424: bipush #100
    //   426: aload #14
    //   428: iload #6
    //   430: invokespecial zza : (Ljava/lang/String;Ljava/lang/String;ILjava/lang/Object;Z)Z
    //   433: istore #10
    //   435: iload #10
    //   437: ifeq -> 446
    //   440: iconst_0
    //   441: istore #7
    //   443: goto -> 449
    //   446: iconst_4
    //   447: istore #7
    //   449: iload #7
    //   451: ifeq -> 520
    //   454: ldc_w '_ev'
    //   457: aload #11
    //   459: invokevirtual equals : (Ljava/lang/Object;)Z
    //   462: ifne -> 520
    //   465: aload #12
    //   467: iload #7
    //   469: invokestatic zza : (Landroid/os/Bundle;I)Z
    //   472: ifeq -> 502
    //   475: aload #12
    //   477: ldc_w '_ev'
    //   480: aload #11
    //   482: bipush #40
    //   484: iconst_1
    //   485: invokestatic zza : (Ljava/lang/String;IZ)Ljava/lang/String;
    //   488: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   491: aload #12
    //   493: aload_3
    //   494: aload #11
    //   496: invokevirtual get : (Ljava/lang/String;)Ljava/lang/Object;
    //   499: invokestatic zza : (Landroid/os/Bundle;Ljava/lang/Object;)V
    //   502: aload #12
    //   504: aload #11
    //   506: invokevirtual remove : (Ljava/lang/String;)V
    //   509: iload #8
    //   511: istore #7
    //   513: iload #7
    //   515: istore #8
    //   517: goto -> 28
    //   520: iload #8
    //   522: istore #7
    //   524: aload #11
    //   526: invokestatic zzct : (Ljava/lang/String;)Z
    //   529: ifeq -> 622
    //   532: iinc #8, 1
    //   535: iload #8
    //   537: istore #7
    //   539: iload #8
    //   541: bipush #25
    //   543: if_icmple -> 622
    //   546: new java/lang/StringBuilder
    //   549: dup
    //   550: bipush #48
    //   552: invokespecial <init> : (I)V
    //   555: astore #14
    //   557: aload #14
    //   559: ldc_w 'Event can't contain more than 25 params'
    //   562: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   565: pop
    //   566: aload #14
    //   568: invokevirtual toString : ()Ljava/lang/String;
    //   571: astore #14
    //   573: aload_0
    //   574: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   577: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   580: aload #14
    //   582: aload_0
    //   583: invokevirtual zzgq : ()Lcom/google/android/gms/measurement/internal/zzaq;
    //   586: aload_2
    //   587: invokevirtual zzbt : (Ljava/lang/String;)Ljava/lang/String;
    //   590: aload_0
    //   591: invokevirtual zzgq : ()Lcom/google/android/gms/measurement/internal/zzaq;
    //   594: aload_3
    //   595: invokevirtual zzd : (Landroid/os/Bundle;)Ljava/lang/String;
    //   598: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   601: aload #12
    //   603: iconst_5
    //   604: invokestatic zza : (Landroid/os/Bundle;I)Z
    //   607: pop
    //   608: aload #12
    //   610: aload #11
    //   612: invokevirtual remove : (Ljava/lang/String;)V
    //   615: iload #8
    //   617: istore #7
    //   619: goto -> 513
    //   622: goto -> 513
    //   625: aconst_null
    //   626: astore #11
    //   628: aload #11
    //   630: areturn
  }
  
  final zzag zza(String paramString1, String paramString2, Bundle paramBundle, String paramString3, long paramLong, boolean paramBoolean1, boolean paramBoolean2) {
    if (TextUtils.isEmpty(paramString2))
      return null; 
    if (zzcu(paramString2) == 0) {
      Bundle bundle = new Bundle();
      if (paramBundle != null) {
        this(paramBundle);
      } else {
        this();
      } 
      bundle.putString("_o", paramString3);
      return new zzag(paramString2, new zzad(zze(zza(paramString1, paramString2, bundle, CollectionUtils.listOf("_o"), false, false))), paramString3, paramLong);
    } 
    zzgt().zzjg().zzg("Invalid conditional property event name", zzgq().zzbv(paramString2));
    throw new IllegalArgumentException();
  }
  
  public final void zza(int paramInt1, String paramString1, String paramString2, int paramInt2) {
    zza((String)null, paramInt1, paramString1, paramString2, paramInt2);
  }
  
  final void zza(Bundle paramBundle, long paramLong) {
    long l = paramBundle.getLong("_et");
    if (l != 0L)
      zzgt().zzjj().zzg("Params already contained engagement", Long.valueOf(l)); 
    paramBundle.putLong("_et", paramLong + l);
  }
  
  final void zza(Bundle paramBundle, String paramString, Object paramObject) {
    if (paramBundle == null)
      return; 
    if (paramObject instanceof Long) {
      paramBundle.putLong(paramString, ((Long)paramObject).longValue());
      return;
    } 
    if (paramObject instanceof String) {
      paramBundle.putString(paramString, String.valueOf(paramObject));
      return;
    } 
    if (paramObject instanceof Double) {
      paramBundle.putDouble(paramString, ((Double)paramObject).doubleValue());
      return;
    } 
    if (paramString != null) {
      if (paramObject != null) {
        String str = paramObject.getClass().getSimpleName();
      } else {
        paramBundle = null;
      } 
      zzgt().zzjl().zze("Not putting event parameter. Invalid value type. name, type", zzgq().zzbu(paramString), paramBundle);
    } 
  }
  
  final void zza(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2) {
    Bundle bundle = new Bundle();
    zza(bundle, paramInt1);
    if (zzgv().zze(paramString1, zzai.zzali)) {
      if (!TextUtils.isEmpty(paramString2) && !TextUtils.isEmpty(paramString3))
        bundle.putString(paramString2, paramString3); 
    } else if (!TextUtils.isEmpty(paramString2)) {
      bundle.putString(paramString2, paramString3);
    } 
    if (paramInt1 == 6 || paramInt1 == 7 || paramInt1 == 2)
      bundle.putLong("_el", paramInt2); 
    this.zzada.zzgw();
    this.zzada.zzgj().logEvent("auto", "_err", bundle);
  }
  
  final boolean zza(String paramString1, int paramInt, String paramString2) {
    if (paramString2 == null) {
      zzgt().zzjg().zzg("Name is required and can't be null. Type", paramString1);
      return false;
    } 
    if (paramString2.codePointCount(0, paramString2.length()) > paramInt) {
      zzgt().zzjg().zzd("Name is too long. Type, maximum supported length, name", paramString1, Integer.valueOf(paramInt), paramString2);
      return false;
    } 
    return true;
  }
  
  final boolean zza(String paramString1, String[] paramArrayOfString, String paramString2) {
    if (paramString2 == null) {
      zzgt().zzjg().zzg("Name is required and can't be null. Type", paramString1);
      return false;
    } 
    Preconditions.checkNotNull(paramString2);
    String[] arrayOfString = zzauq;
    int i = arrayOfString.length;
    byte b = 0;
    while (true) {
      if (b < i) {
        if (paramString2.startsWith(arrayOfString[b])) {
          b = 1;
          break;
        } 
        b++;
        continue;
      } 
      b = 0;
      break;
    } 
    if (b != 0) {
      zzgt().zzjg().zze("Name starts with reserved prefix. Type, name", paramString1, paramString2);
      return false;
    } 
    if (paramArrayOfString != null) {
      Preconditions.checkNotNull(paramArrayOfString);
      i = paramArrayOfString.length;
      b = 0;
      while (true) {
        if (b < i) {
          if (zzv(paramString2, paramArrayOfString[b])) {
            b = 1;
            break;
          } 
          b++;
          continue;
        } 
        b = 0;
        break;
      } 
      if (b != 0) {
        zzgt().zzjg().zze("Name is reserved. Type, name", paramString1, paramString2);
        return false;
      } 
    } 
    return true;
  }
  
  final int zzcu(String paramString) {
    return !zzt("event", paramString) ? 2 : (!zza("event", zzcu.zzaqt, paramString) ? 13 : (!zza("event", 40, paramString) ? 2 : 0));
  }
  
  final int zzcv(String paramString) {
    return !zzt("user property", paramString) ? 6 : (!zza("user property", zzcw.zzaqx, paramString) ? 15 : (!zza("user property", 24, paramString) ? 6 : 0));
  }
  
  final boolean zzcz(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return false; 
    String str = zzgv().zzid();
    zzgw();
    return str.equals(paramString);
  }
  
  final long zzd(Context paramContext, String paramString) {
    zzaf();
    Preconditions.checkNotNull(paramContext);
    Preconditions.checkNotEmpty(paramString);
    PackageManager packageManager = paramContext.getPackageManager();
    MessageDigest messageDigest = getMessageDigest();
    long l = -1L;
    if (messageDigest == null) {
      zzgt().zzjg().zzby("Could not get MD5 instance");
    } else {
      if (packageManager != null)
        try {
          if (!zze(paramContext, paramString)) {
            PackageInfo packageInfo = Wrappers.packageManager(paramContext).getPackageInfo(getContext().getPackageName(), 64);
            if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
              l = zzc(messageDigest.digest(packageInfo.signatures[0].toByteArray()));
            } else {
              zzgt().zzjj().zzby("Could not get signatures");
            } 
            return l;
          } 
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          zzgt().zzjg().zzg("Package name not found", nameNotFoundException);
        }  
      l = 0L;
    } 
    return l;
  }
  
  final Bundle zze(Bundle paramBundle) {
    Bundle bundle = new Bundle();
    if (paramBundle != null)
      for (String str : paramBundle.keySet()) {
        Object object = zzh(str, paramBundle.get(str));
        if (object == null) {
          zzgt().zzjj().zzg("Param value can't be null", zzgq().zzbu(str));
          continue;
        } 
        zza(bundle, str, object);
      }  
    return bundle;
  }
  
  protected final boolean zzgy() {
    return true;
  }
  
  protected final void zzgz() {
    zzaf();
    SecureRandom secureRandom = new SecureRandom();
    long l2 = secureRandom.nextLong();
    long l1 = l2;
    if (l2 == 0L) {
      l2 = secureRandom.nextLong();
      l1 = l2;
      if (l2 == 0L) {
        zzgt().zzjj().zzby("Utils falling back to Random for random id");
        l1 = l2;
      } 
    } 
    this.zzaus.set(l1);
  }
  
  final Object zzh(String paramString, Object paramObject) {
    boolean bool = "_ev".equals(paramString);
    char c = 'Ā';
    if (bool)
      return zza(256, paramObject, true); 
    if (!zzcy(paramString))
      c = 'd'; 
    return zza(c, paramObject, false);
  }
  
  final int zzi(String paramString, Object paramObject) {
    boolean bool;
    if ("_ldl".equals(paramString)) {
      bool = zza("user property referrer", paramString, zzcx(paramString), paramObject, false);
    } else {
      bool = zza("user property", paramString, zzcx(paramString), paramObject, false);
    } 
    return bool ? 0 : 7;
  }
  
  final Object zzj(String paramString, Object paramObject) {
    return "_ldl".equals(paramString) ? zza(zzcx(paramString), paramObject, true) : zza(zzcx(paramString), paramObject, false);
  }
  
  public final long zzmj() {
    if (this.zzaus.get() == 0L)
      synchronized (this.zzaus) {
        Random random = new Random();
        this(System.nanoTime() ^ zzbx().currentTimeMillis());
        long l1 = random.nextLong();
        int i = this.zzado + 1;
        this.zzado = i;
        long l2 = i;
        return l1 + l2;
      }  
    synchronized (this.zzaus) {
      this.zzaus.compareAndSet(-1L, 1L);
      return this.zzaus.getAndIncrement();
    } 
  }
  
  final SecureRandom zzmk() {
    zzaf();
    if (this.zzaur == null)
      this.zzaur = new SecureRandom(); 
    return this.zzaur;
  }
  
  public final int zzml() {
    if (this.zzaut == null)
      this.zzaut = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(getContext()) / 1000); 
    return this.zzaut.intValue();
  }
  
  final String zzmm() {
    byte[] arrayOfByte = new byte[16];
    zzmk().nextBytes(arrayOfByte);
    return String.format(Locale.US, "%032x", new Object[] { new BigInteger(1, arrayOfByte) });
  }
  
  public final int zzs(int paramInt) {
    return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(getContext(), 12451000);
  }
  
  final boolean zzs(String paramString1, String paramString2) {
    if (paramString2 == null) {
      zzgt().zzjg().zzg("Name is required and can't be null. Type", paramString1);
      return false;
    } 
    if (paramString2.length() == 0) {
      zzgt().zzjg().zzg("Name is required and can't be empty. Type", paramString1);
      return false;
    } 
    int i = paramString2.codePointAt(0);
    if (!Character.isLetter(i)) {
      zzgt().zzjg().zze("Name must start with a letter. Type, name", paramString1, paramString2);
      return false;
    } 
    int j = paramString2.length();
    for (i = Character.charCount(i); i < j; i += Character.charCount(k)) {
      int k = paramString2.codePointAt(i);
      if (k != 95 && !Character.isLetterOrDigit(k)) {
        zzgt().zzjg().zze("Name must consist of letters, digits or _ (underscores). Type, name", paramString1, paramString2);
        return false;
      } 
    } 
    return true;
  }
  
  final boolean zzu(String paramString1, String paramString2) {
    if (!TextUtils.isEmpty(paramString1)) {
      if (!zzcw(paramString1)) {
        if (this.zzada.zzkn())
          zzgt().zzjg().zzg("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzas.zzbw(paramString1)); 
        return false;
      } 
    } else {
      if (!TextUtils.isEmpty(paramString2)) {
        if (!zzcw(paramString2)) {
          zzgt().zzjg().zzg("Invalid admob_app_id. Analytics disabled.", zzas.zzbw(paramString2));
          return false;
        } 
        return true;
      } 
      if (this.zzada.zzkn())
        zzgt().zzjg().zzby("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI"); 
      return false;
    } 
    return true;
  }
  
  final boolean zzx(String paramString) {
    zzaf();
    if (Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(paramString) == 0)
      return true; 
    zzgt().zzjn().zzg("Permission not granted", paramString);
    return false;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzfx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */