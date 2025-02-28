package com.google.android.gms.internal.measurement;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class zzum<T extends zzub> {
  private static final Logger logger = Logger.getLogger(zztv.class.getName());
  
  private static String zzbyd = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
  
  static <T extends zzub> T zzd(Class<T> paramClass) {
    String str;
    ClassLoader classLoader = zzum.class.getClassLoader();
    if (paramClass.equals(zzub.class)) {
      str = zzbyd;
    } else if (paramClass.getPackage().equals(zzum.class.getPackage())) {
      str = String.format("%s.BlazeGenerated%sLoader", new Object[] { paramClass.getPackage().getName(), paramClass.getSimpleName() });
    } else {
      throw new IllegalArgumentException(paramClass.getName());
    } 
    try {
      Class<?> clazz = Class.forName(str, true, classLoader);
      try {
        zzum zzum1 = clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
        return paramClass.cast(zzum1.zzwd());
      } catch (NoSuchMethodException noSuchMethodException) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this(noSuchMethodException);
        throw illegalStateException;
      } catch (InstantiationException instantiationException) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this(instantiationException);
        throw illegalStateException;
      } catch (IllegalAccessException illegalAccessException) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this(illegalAccessException);
        throw illegalStateException;
      } catch (InvocationTargetException invocationTargetException) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this(invocationTargetException);
        throw illegalStateException;
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      Iterator<zzum> iterator = ServiceLoader.<zzum>load(zzum.class, classLoader).iterator();
      ArrayList<zzub> arrayList = new ArrayList();
      while (iterator.hasNext()) {
        try {
          arrayList.add((zzub)paramClass.cast(((zzum)iterator.next()).zzwd()));
        } catch (ServiceConfigurationError serviceConfigurationError) {
          Logger logger = logger;
          Level level = Level.SEVERE;
          String str1 = String.valueOf(paramClass.getSimpleName());
          if (str1.length() != 0) {
            str1 = "Unable to load ".concat(str1);
          } else {
            str1 = new String("Unable to load ");
          } 
          logger.logp(level, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", str1, serviceConfigurationError);
        } 
      } 
      if (arrayList.size() == 1)
        return (T)arrayList.get(0); 
      if (arrayList.size() == 0)
        return null; 
      try {
        return (T)paramClass.getMethod("combine", new Class[] { Collection.class }).invoke(null, new Object[] { arrayList });
      } catch (NoSuchMethodException noSuchMethodException) {
        throw new IllegalStateException(noSuchMethodException);
      } catch (IllegalAccessException illegalAccessException) {
        throw new IllegalStateException(illegalAccessException);
      } catch (InvocationTargetException invocationTargetException) {
        throw new IllegalStateException(invocationTargetException);
      } 
    } 
  }
  
  protected abstract T zzwd();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */