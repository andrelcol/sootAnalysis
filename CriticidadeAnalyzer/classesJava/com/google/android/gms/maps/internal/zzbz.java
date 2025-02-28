package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzbz {
  @SuppressLint({"StaticFieldLeak"})
  private static Context zzck;
  
  private static zze zzcl;
  
  public static zze zza(Context paramContext) throws GooglePlayServicesNotAvailableException {
    Preconditions.checkNotNull(paramContext);
    zze zze1 = zzcl;
    if (zze1 != null)
      return zze1; 
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext, 13400000);
    if (i == 0) {
      zze zze2;
      IBinder iBinder = zza(zzb(paramContext).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
      if (iBinder == null) {
        iBinder = null;
      } else {
        IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
        if (iInterface instanceof zze) {
          zze2 = (zze)iInterface;
        } else {
          zze2 = new zzf((IBinder)zze2);
        } 
      } 
      zzcl = zze2;
      try {
        zzcl.zza(ObjectWrapper.wrap(zzb(paramContext).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        return zzcl;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      } 
    } 
    throw new GooglePlayServicesNotAvailableException(i);
  }
  
  private static <T> T zza(Class<?> paramClass) {
    String str;
    try {
      return (T)paramClass.newInstance();
    } catch (InstantiationException instantiationException) {
      str = String.valueOf(paramClass.getName());
      if (str.length() != 0) {
        str = "Unable to instantiate the dynamic class ".concat(str);
      } else {
        str = new String("Unable to instantiate the dynamic class ");
      } 
      throw new IllegalStateException(str);
    } catch (IllegalAccessException illegalAccessException) {
      str = String.valueOf(str.getName());
      if (str.length() != 0) {
        str = "Unable to call the default constructor of ".concat(str);
      } else {
        str = new String("Unable to call the default constructor of ");
      } 
      throw new IllegalStateException(str);
    } 
  }
  
  private static <T> T zza(ClassLoader paramClassLoader, String paramString) {
    try {
      Preconditions.checkNotNull(paramClassLoader);
      return (T)zza(paramClassLoader.loadClass(paramString));
    } catch (ClassNotFoundException classNotFoundException) {
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        str = "Unable to find dynamic class ".concat(str);
      } else {
        str = new String("Unable to find dynamic class ");
      } 
      throw new IllegalStateException(str);
    } 
  }
  
  private static Context zzb(Context paramContext) {
    Context context = zzck;
    if (context != null)
      return context; 
    paramContext = zzc(paramContext);
    zzck = paramContext;
    return paramContext;
  }
  
  private static Context zzc(Context paramContext) {
    try {
      Context context = DynamiteModule.load(paramContext, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.maps_dynamite").getModuleContext();
      paramContext = context;
    } catch (Exception exception) {
      paramContext = GooglePlayServicesUtil.getRemoteContext(paramContext);
    } 
    return paramContext;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/internal/zzbz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */