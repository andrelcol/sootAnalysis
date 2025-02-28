package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;

public abstract class VersionedParcel {
  private static <T extends VersionedParcelable> Class findParcelClass(T paramT) throws ClassNotFoundException {
    return findParcelClass((Class)paramT.getClass());
  }
  
  private static Class findParcelClass(Class<? extends VersionedParcelable> paramClass) throws ClassNotFoundException {
    return Class.forName(String.format("%s.%sParcelizer", new Object[] { paramClass.getPackage().getName(), paramClass.getSimpleName() }), false, paramClass.getClassLoader());
  }
  
  protected static <T extends VersionedParcelable> T readFromParcel(String paramString, VersionedParcel paramVersionedParcel) {
    try {
      return (T)Class.forName(paramString, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", new Class[] { VersionedParcel.class }).invoke(null, new Object[] { paramVersionedParcel });
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {
    
    } catch (ClassNotFoundException classNotFoundException) {}
    if (classNotFoundException.getCause() instanceof RuntimeException)
      throw (RuntimeException)classNotFoundException.getCause(); 
    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", classNotFoundException);
  }
  
  protected static <T extends VersionedParcelable> void writeToParcel(T paramT, VersionedParcel paramVersionedParcel) {
    try {
      findParcelClass(paramT).getDeclaredMethod("write", new Class[] { paramT.getClass(), VersionedParcel.class }).invoke(null, new Object[] { paramT, paramVersionedParcel });
      return;
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("VersionedParcel encountered IllegalAccessException", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      if (invocationTargetException.getCause() instanceof RuntimeException)
        throw (RuntimeException)invocationTargetException.getCause(); 
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", invocationTargetException);
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", noSuchMethodException);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", classNotFoundException);
    } 
  }
  
  private void writeVersionedParcelableCreator(VersionedParcelable paramVersionedParcelable) {
    try {
      Class clazz = findParcelClass((Class)paramVersionedParcelable.getClass());
      writeString(clazz.getName());
      return;
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramVersionedParcelable.getClass().getSimpleName());
      stringBuilder.append(" does not have a Parcelizer");
      throw new RuntimeException(stringBuilder.toString(), classNotFoundException);
    } 
  }
  
  protected abstract void closeField();
  
  protected abstract VersionedParcel createSubParcel();
  
  public boolean isStream() {
    return false;
  }
  
  protected abstract byte[] readByteArray();
  
  public byte[] readByteArray(byte[] paramArrayOfbyte, int paramInt) {
    return !readField(paramInt) ? paramArrayOfbyte : readByteArray();
  }
  
  protected abstract boolean readField(int paramInt);
  
  protected abstract int readInt();
  
  public int readInt(int paramInt1, int paramInt2) {
    return !readField(paramInt2) ? paramInt1 : readInt();
  }
  
  protected abstract <T extends Parcelable> T readParcelable();
  
  public <T extends Parcelable> T readParcelable(T paramT, int paramInt) {
    return !readField(paramInt) ? paramT : readParcelable();
  }
  
  protected abstract String readString();
  
  public String readString(String paramString, int paramInt) {
    return !readField(paramInt) ? paramString : readString();
  }
  
  protected <T extends VersionedParcelable> T readVersionedParcelable() {
    String str = readString();
    return (str == null) ? null : readFromParcel(str, createSubParcel());
  }
  
  public <T extends VersionedParcelable> T readVersionedParcelable(T paramT, int paramInt) {
    return !readField(paramInt) ? paramT : readVersionedParcelable();
  }
  
  protected abstract void setOutputField(int paramInt);
  
  public void setSerializationFlags(boolean paramBoolean1, boolean paramBoolean2) {}
  
  protected abstract void writeByteArray(byte[] paramArrayOfbyte);
  
  public void writeByteArray(byte[] paramArrayOfbyte, int paramInt) {
    setOutputField(paramInt);
    writeByteArray(paramArrayOfbyte);
  }
  
  protected abstract void writeInt(int paramInt);
  
  public void writeInt(int paramInt1, int paramInt2) {
    setOutputField(paramInt2);
    writeInt(paramInt1);
  }
  
  protected abstract void writeParcelable(Parcelable paramParcelable);
  
  public void writeParcelable(Parcelable paramParcelable, int paramInt) {
    setOutputField(paramInt);
    writeParcelable(paramParcelable);
  }
  
  protected abstract void writeString(String paramString);
  
  public void writeString(String paramString, int paramInt) {
    setOutputField(paramInt);
    writeString(paramString);
  }
  
  protected void writeVersionedParcelable(VersionedParcelable paramVersionedParcelable) {
    if (paramVersionedParcelable == null) {
      writeString(null);
      return;
    } 
    writeVersionedParcelableCreator(paramVersionedParcelable);
    VersionedParcel versionedParcel = createSubParcel();
    writeToParcel(paramVersionedParcelable, versionedParcel);
    versionedParcel.closeField();
  }
  
  public void writeVersionedParcelable(VersionedParcelable paramVersionedParcelable, int paramInt) {
    setOutputField(paramInt);
    writeVersionedParcelable(paramVersionedParcelable);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/versionedparcelable/VersionedParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */