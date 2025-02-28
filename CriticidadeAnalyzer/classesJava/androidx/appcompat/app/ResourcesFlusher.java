package androidx.appcompat.app;

import android.content.res.Resources;
import android.os.Build;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher {
  private static Field sDrawableCacheField;
  
  private static boolean sDrawableCacheFieldFetched;
  
  private static Field sResourcesImplField;
  
  private static boolean sResourcesImplFieldFetched;
  
  private static Class sThemedResourceCacheClazz;
  
  private static boolean sThemedResourceCacheClazzFetched;
  
  private static Field sThemedResourceCache_mUnthemedEntriesField;
  
  private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;
  
  static void flush(Resources paramResources) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 28)
      return; 
    if (i >= 24) {
      flushNougats(paramResources);
    } else if (i >= 23) {
      flushMarshmallows(paramResources);
    } else if (i >= 21) {
      flushLollipops(paramResources);
    } 
  }
  
  private static void flushLollipops(Resources paramResources) {
    if (!sDrawableCacheFieldFetched) {
      try {
        sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
        sDrawableCacheField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {}
      sDrawableCacheFieldFetched = true;
    } 
    Field field = sDrawableCacheField;
    if (field != null) {
      try {
        Map map = (Map)field.get(paramResources);
      } catch (IllegalAccessException illegalAccessException) {
        illegalAccessException = null;
      } 
      if (illegalAccessException != null)
        illegalAccessException.clear(); 
    } 
  }
  
  private static void flushMarshmallows(Resources paramResources) {
    if (!sDrawableCacheFieldFetched) {
      try {
        sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
        sDrawableCacheField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {}
      sDrawableCacheFieldFetched = true;
    } 
    Object object1 = null;
    Field field = sDrawableCacheField;
    Object object = object1;
    if (field != null)
      try {
        object = field.get(paramResources);
      } catch (IllegalAccessException illegalAccessException) {
        object = object1;
      }  
    if (object == null)
      return; 
    flushThemedResourcesCache(object);
  }
  
  private static void flushNougats(Resources paramResources) {
    if (!sResourcesImplFieldFetched) {
      try {
        sResourcesImplField = Resources.class.getDeclaredField("mResourcesImpl");
        sResourcesImplField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {}
      sResourcesImplFieldFetched = true;
    } 
    Field field1 = sResourcesImplField;
    if (field1 == null)
      return; 
    Field field2 = null;
    try {
      object = field1.get(paramResources);
    } catch (IllegalAccessException object) {
      object = null;
    } 
    if (object == null)
      return; 
    if (!sDrawableCacheFieldFetched) {
      try {
        sDrawableCacheField = object.getClass().getDeclaredField("mDrawableCache");
        sDrawableCacheField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {}
      sDrawableCacheFieldFetched = true;
    } 
    Field field3 = sDrawableCacheField;
    field1 = field2;
    if (field3 != null)
      try {
        Object object1 = field3.get(object);
      } catch (IllegalAccessException illegalAccessException) {
        field1 = field2;
      }  
    if (field1 != null)
      flushThemedResourcesCache(field1); 
  }
  
  private static void flushThemedResourcesCache(Object paramObject) {
    if (!sThemedResourceCacheClazzFetched) {
      try {
        sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
      } catch (ClassNotFoundException classNotFoundException) {}
      sThemedResourceCacheClazzFetched = true;
    } 
    Class clazz = sThemedResourceCacheClazz;
    if (clazz == null)
      return; 
    if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {
      try {
        sThemedResourceCache_mUnthemedEntriesField = clazz.getDeclaredField("mUnthemedEntries");
        sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {}
      sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
    } 
    Field field = sThemedResourceCache_mUnthemedEntriesField;
    if (field == null)
      return; 
    try {
      paramObject = field.get(paramObject);
    } catch (IllegalAccessException illegalAccessException) {
      illegalAccessException = null;
    } 
    if (illegalAccessException != null)
      illegalAccessException.clear(); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/app/ResourcesFlusher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */