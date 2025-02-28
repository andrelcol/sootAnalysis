package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.google.android.gms.common.wrappers.Wrappers;

public class GoogleSignatureVerifier {
  private static GoogleSignatureVerifier zzam;
  
  private final Context mContext;
  
  private GoogleSignatureVerifier(Context paramContext) {
    this.mContext = paramContext.getApplicationContext();
  }
  
  public static GoogleSignatureVerifier getInstance(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: ldc com/google/android/gms/common/GoogleSignatureVerifier
    //   7: monitorenter
    //   8: getstatic com/google/android/gms/common/GoogleSignatureVerifier.zzam : Lcom/google/android/gms/common/GoogleSignatureVerifier;
    //   11: ifnonnull -> 31
    //   14: aload_0
    //   15: invokestatic zza : (Landroid/content/Context;)V
    //   18: new com/google/android/gms/common/GoogleSignatureVerifier
    //   21: astore_1
    //   22: aload_1
    //   23: aload_0
    //   24: invokespecial <init> : (Landroid/content/Context;)V
    //   27: aload_1
    //   28: putstatic com/google/android/gms/common/GoogleSignatureVerifier.zzam : Lcom/google/android/gms/common/GoogleSignatureVerifier;
    //   31: ldc com/google/android/gms/common/GoogleSignatureVerifier
    //   33: monitorexit
    //   34: getstatic com/google/android/gms/common/GoogleSignatureVerifier.zzam : Lcom/google/android/gms/common/GoogleSignatureVerifier;
    //   37: areturn
    //   38: astore_0
    //   39: ldc com/google/android/gms/common/GoogleSignatureVerifier
    //   41: monitorexit
    //   42: aload_0
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   8	31	38	finally
    //   31	34	38	finally
    //   39	42	38	finally
  }
  
  private static zze zza(PackageInfo paramPackageInfo, zze... paramVarArgs) {
    Signature[] arrayOfSignature = paramPackageInfo.signatures;
    if (arrayOfSignature == null)
      return null; 
    if (arrayOfSignature.length != 1)
      return null; 
    byte b = 0;
    zzf zzf = new zzf(arrayOfSignature[0].toByteArray());
    while (b < paramVarArgs.length) {
      if (paramVarArgs[b].equals(zzf))
        return paramVarArgs[b]; 
      b++;
    } 
    return null;
  }
  
  private final zzm zza(String paramString, int paramInt) {
    try {
      PackageInfo packageInfo = Wrappers.packageManager(this.mContext).zza(paramString, 64, paramInt);
      boolean bool = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
      if (packageInfo == null)
        return zzm.zzb("null pkg"); 
      if (packageInfo.signatures.length != 1)
        return zzm.zzb("single cert required"); 
      zzf zzf = new zzf();
      this(packageInfo.signatures[0].toByteArray());
      String str = packageInfo.packageName;
      zzm zzm = zzc.zza(str, zzf, bool, false);
      return (zzm.zzad && packageInfo.applicationInfo != null && (packageInfo.applicationInfo.flags & 0x2) != 0 && (zzc.zza(str, zzf, false, true)).zzad) ? zzm.zzb("debuggable release cert app rejected") : zzm;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "no pkg ".concat(paramString);
      } else {
        paramString = new String("no pkg ");
      } 
      return zzm.zzb(paramString);
    } 
  }
  
  public static boolean zza(PackageInfo paramPackageInfo, boolean paramBoolean) {
    if (paramPackageInfo != null && paramPackageInfo.signatures != null) {
      zze zze;
      if (paramBoolean) {
        zze = zza(paramPackageInfo, zzh.zzx);
      } else {
        zze = zza((PackageInfo)zze, new zze[] { zzh.zzx[0] });
      } 
      if (zze != null)
        return true; 
    } 
    return false;
  }
  
  public boolean isGooglePublicSignedPackage(PackageInfo paramPackageInfo) {
    return (paramPackageInfo == null) ? false : (zza(paramPackageInfo, false) ? true : ((zza(paramPackageInfo, true) && GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext))));
  }
  
  public boolean isUidGoogleSigned(int paramInt) {
    String[] arrayOfString = Wrappers.packageManager(this.mContext).getPackagesForUid(paramInt);
    if (arrayOfString == null || arrayOfString.length == 0) {
      zzm zzm1 = zzm.zzb("no pkgs");
      zzm1.zzf();
      return zzm1.zzad;
    } 
    zzm zzm = null;
    int i = arrayOfString.length;
    byte b = 0;
    while (b < i) {
      zzm zzm1 = zza(arrayOfString[b], paramInt);
      zzm = zzm1;
      if (!zzm1.zzad) {
        b++;
        zzm = zzm1;
      } 
    } 
    zzm.zzf();
    return zzm.zzad;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/GoogleSignatureVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */