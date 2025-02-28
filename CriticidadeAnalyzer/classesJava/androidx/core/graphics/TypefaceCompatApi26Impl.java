package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
  protected final Method mAbortCreation;
  
  protected final Method mAddFontFromAssetManager;
  
  protected final Method mAddFontFromBuffer;
  
  protected final Method mCreateFromFamiliesWithDefault;
  
  protected final Class mFontFamily;
  
  protected final Constructor mFontFamilyCtor;
  
  protected final Method mFreeze;
  
  public TypefaceCompatApi26Impl() {
    ClassNotFoundException classNotFoundException1;
    ClassNotFoundException classNotFoundException2;
    ClassNotFoundException classNotFoundException3;
    ClassNotFoundException classNotFoundException4;
    ClassNotFoundException classNotFoundException5;
    Class clazz = null;
    try {
      Class clazz1 = obtainFontFamily();
      Constructor constructor = obtainFontFamilyCtor(clazz1);
      Method method1 = obtainAddFontFromAssetManagerMethod(clazz1);
      Method method4 = obtainAddFontFromBufferMethod(clazz1);
      Method method3 = obtainFreezeMethod(clazz1);
      Method method5 = obtainAbortCreationMethod(clazz1);
      Method method2 = obtainCreateFromFamiliesWithDefaultMethod(clazz1);
      clazz = clazz1;
    } catch (ClassNotFoundException classNotFoundException6) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to collect necessary methods for class ");
      stringBuilder.append(classNotFoundException6.getClass().getName());
      stringBuilder.toString();
      ClassNotFoundException classNotFoundException7 = null;
      classNotFoundException4 = null;
      classNotFoundException6 = classNotFoundException4;
      classNotFoundException1 = classNotFoundException6;
      classNotFoundException2 = classNotFoundException1;
      classNotFoundException5 = classNotFoundException2;
      classNotFoundException3 = classNotFoundException1;
      classNotFoundException1 = classNotFoundException7;
    } catch (NoSuchMethodException noSuchMethodException) {}
    this.mFontFamily = clazz;
    this.mFontFamilyCtor = (Constructor)classNotFoundException4;
    this.mAddFontFromAssetManager = (Method)noSuchMethodException;
    this.mAddFontFromBuffer = (Method)classNotFoundException3;
    this.mFreeze = (Method)classNotFoundException2;
    this.mAbortCreation = (Method)classNotFoundException5;
    this.mCreateFromFamiliesWithDefault = (Method)classNotFoundException1;
  }
  
  private void abortCreation(Object paramObject) {
    try {
      this.mAbortCreation.invoke(paramObject, new Object[0]);
      return;
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  private boolean addFontFromAssetManager(Context paramContext, Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, FontVariationAxis[] paramArrayOfFontVariationAxis) {
    try {
      return ((Boolean)this.mAddFontFromAssetManager.invoke(paramObject, new Object[] { paramContext.getAssets(), paramString, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), paramArrayOfFontVariationAxis })).booleanValue();
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  private boolean addFontFromBuffer(Object paramObject, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3) {
    try {
      return ((Boolean)this.mAddFontFromBuffer.invoke(paramObject, new Object[] { paramByteBuffer, Integer.valueOf(paramInt1), null, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) })).booleanValue();
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  private boolean freeze(Object paramObject) {
    try {
      return ((Boolean)this.mFreeze.invoke(paramObject, new Object[0])).booleanValue();
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  private boolean isFontFamilyPrivateAPIAvailable() {
    boolean bool;
    Method method = this.mAddFontFromAssetManager;
    if (this.mAddFontFromAssetManager != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private Object newFamily() {
    try {
      return this.mFontFamilyCtor.newInstance(new Object[0]);
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InstantiationException instantiationException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  protected Typeface createFromFamiliesWithDefault(Object paramObject) {
    try {
      Object object = Array.newInstance(this.mFontFamily, 1);
      Array.set(object, 0, paramObject);
      return (Typeface)this.mCreateFromFamiliesWithDefault.invoke(null, new Object[] { object, Integer.valueOf(-1), Integer.valueOf(-1) });
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  public Typeface createFromFontFamilyFilesResourceEntry(Context paramContext, FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, Resources paramResources, int paramInt) {
    if (!isFontFamilyPrivateAPIAvailable())
      return super.createFromFontFamilyFilesResourceEntry(paramContext, paramFontFamilyFilesResourceEntry, paramResources, paramInt); 
    Object object = newFamily();
    FontResourcesParserCompat.FontFileResourceEntry[] arrayOfFontFileResourceEntry = paramFontFamilyFilesResourceEntry.getEntries();
    int i = arrayOfFontFileResourceEntry.length;
    for (paramInt = 0; paramInt < i; paramInt++) {
      FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = arrayOfFontFileResourceEntry[paramInt];
      if (!addFontFromAssetManager(paramContext, object, fontFileResourceEntry.getFileName(), fontFileResourceEntry.getTtcIndex(), fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic(), FontVariationAxis.fromFontVariationSettings(fontFileResourceEntry.getVariationSettings()))) {
        abortCreation(object);
        return null;
      } 
    } 
    return !freeze(object) ? null : createFromFamiliesWithDefault(object);
  }
  
  public Typeface createFromFontInfo(Context paramContext, CancellationSignal paramCancellationSignal, FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt) {
    if (paramArrayOfFontInfo.length < 1)
      return null; 
    if (!isFontFamilyPrivateAPIAvailable()) {
      FontsContractCompat.FontInfo fontInfo = findBestInfo(paramArrayOfFontInfo, paramInt);
      ContentResolver contentResolver = paramContext.getContentResolver();
      try {
        ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(fontInfo.getUri(), "r", paramCancellationSignal);
        if (parcelFileDescriptor == null) {
          if (parcelFileDescriptor != null)
            parcelFileDescriptor.close(); 
          return null;
        } 
        try {
          Typeface.Builder builder = new Typeface.Builder();
          this(parcelFileDescriptor.getFileDescriptor());
          return builder.setWeight(fontInfo.getWeight()).setItalic(fontInfo.isItalic()).build();
        } finally {
          paramCancellationSignal = null;
        } 
      } catch (IOException iOException) {
        return null;
      } 
    } 
    Map map = FontsContractCompat.prepareFontData((Context)iOException, paramArrayOfFontInfo, paramCancellationSignal);
    Object object = newFamily();
    int i = paramArrayOfFontInfo.length;
    boolean bool = false;
    for (byte b = 0; b < i; b++) {
      FontsContractCompat.FontInfo fontInfo = paramArrayOfFontInfo[b];
      ByteBuffer byteBuffer = (ByteBuffer)map.get(fontInfo.getUri());
      if (byteBuffer != null) {
        if (!addFontFromBuffer(object, byteBuffer, fontInfo.getTtcIndex(), fontInfo.getWeight(), fontInfo.isItalic())) {
          abortCreation(object);
          return null;
        } 
        bool = true;
      } 
    } 
    if (!bool) {
      abortCreation(object);
      return null;
    } 
    return !freeze(object) ? null : Typeface.create(createFromFamiliesWithDefault(object), paramInt);
  }
  
  public Typeface createFromResourcesFontFile(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2) {
    if (!isFontFamilyPrivateAPIAvailable())
      return super.createFromResourcesFontFile(paramContext, paramResources, paramInt1, paramString, paramInt2); 
    Object object = newFamily();
    if (!addFontFromAssetManager(paramContext, object, paramString, 0, -1, -1, null)) {
      abortCreation(object);
      return null;
    } 
    return !freeze(object) ? null : createFromFamiliesWithDefault(object);
  }
  
  protected Method obtainAbortCreationMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("abortCreation", new Class[0]);
  }
  
  protected Method obtainAddFontFromAssetManagerMethod(Class paramClass) throws NoSuchMethodException {
    Class<int> clazz2 = int.class;
    Class<boolean> clazz = boolean.class;
    Class<int> clazz1 = int.class;
    return paramClass.getMethod("addFontFromAssetManager", new Class[] { AssetManager.class, String.class, clazz2, clazz, clazz1, clazz1, clazz1, FontVariationAxis[].class });
  }
  
  protected Method obtainAddFontFromBufferMethod(Class paramClass) throws NoSuchMethodException {
    Class<int> clazz = int.class;
    return paramClass.getMethod("addFontFromBuffer", new Class[] { ByteBuffer.class, clazz, FontVariationAxis[].class, clazz, clazz });
  }
  
  protected Method obtainCreateFromFamiliesWithDefaultMethod(Class<?> paramClass) throws NoSuchMethodException {
    Class<?> clazz = Array.newInstance(paramClass, 1).getClass();
    paramClass = int.class;
    Method method = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] { clazz, paramClass, paramClass });
    method.setAccessible(true);
    return method;
  }
  
  protected Class obtainFontFamily() throws ClassNotFoundException {
    return Class.forName("android.graphics.FontFamily");
  }
  
  protected Constructor obtainFontFamilyCtor(Class paramClass) throws NoSuchMethodException {
    return paramClass.getConstructor(new Class[0]);
  }
  
  protected Method obtainFreezeMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("freeze", new Class[0]);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/graphics/TypefaceCompatApi26Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */