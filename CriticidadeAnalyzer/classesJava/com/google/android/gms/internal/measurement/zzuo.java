package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzuo<MessageType extends zzuo<MessageType, BuilderType>, BuilderType extends zzuo.zza<MessageType, BuilderType>> extends zzsx<MessageType, BuilderType> {
  private static Map<Object, zzuo<?, ?>> zzbyh = new ConcurrentHashMap<Object, zzuo<?, ?>>();
  
  protected zzxe zzbyf = zzxe.zzyl();
  
  private int zzbyg = -1;
  
  static <T extends zzuo<T, ?>> T zza(T paramT, zztq paramzztq, zzub paramzzub) throws zzuv {
    zzuo zzuo1 = (zzuo)paramT.zza(zze.zzbyp, (Object)null, (Object)null);
    try {
      zzwh.zzxt().<zzuo>zzak(zzuo1).zza(zzuo1, zztt.zza(paramzztq), paramzzub);
      zzwh.zzxt().<zzuo>zzak(zzuo1).zzy(zzuo1);
      return (T)zzuo1;
    } catch (IOException iOException) {
      if (iOException.getCause() instanceof zzuv)
        throw (zzuv)iOException.getCause(); 
      iOException = new zzuv(iOException.getMessage());
      iOException.zzg(zzuo1);
      throw iOException;
    } catch (RuntimeException runtimeException) {
      if (runtimeException.getCause() instanceof zzuv)
        throw (zzuv)runtimeException.getCause(); 
      throw runtimeException;
    } 
  }
  
  protected static Object zza(zzvv paramzzvv, String paramString, Object[] paramArrayOfObject) {
    return new zzwj(paramzzvv, paramString, paramArrayOfObject);
  }
  
  static Object zza(Method paramMethod, Object paramObject, Object... paramVarArgs) {
    try {
      return paramMethod.invoke(paramObject, paramVarArgs);
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      Throwable throwable = invocationTargetException.getCause();
      if (!(throwable instanceof RuntimeException)) {
        if (throwable instanceof Error)
          throw (Error)throwable; 
        throw new RuntimeException("Unexpected exception thrown by generated accessor method.", throwable);
      } 
      throw (RuntimeException)throwable;
    } 
  }
  
  protected static <T extends zzuo<?, ?>> void zza(Class<T> paramClass, T paramT) {
    zzbyh.put(paramClass, (zzuo<?, ?>)paramT);
  }
  
  static <T extends zzuo<?, ?>> T zzg(Class<T> paramClass) {
    zzuo<?, ?> zzuo2 = zzbyh.get(paramClass);
    zzuo<?, ?> zzuo1 = zzuo2;
    if (zzuo2 == null)
      try {
        Class.forName(paramClass.getName(), true, paramClass.getClassLoader());
        zzuo1 = zzbyh.get(paramClass);
      } catch (ClassNotFoundException classNotFoundException) {
        throw new IllegalStateException("Class initialization cannot fail.", classNotFoundException);
      }  
    zzuo2 = zzuo1;
    if (zzuo1 == null) {
      zzuo2 = (zzuo)((zzuo)zzxj.<zzuo>zzk((Class<zzuo>)classNotFoundException)).zza(zze.zzbyr, (Object)null, (Object)null);
      if (zzuo2 != null) {
        zzbyh.put(classNotFoundException, zzuo2);
      } else {
        throw new IllegalStateException();
      } 
    } 
    return (T)zzuo2;
  }
  
  protected static <E> zzuu<E> zzwg() {
    return zzwi.zzxu();
  }
  
  public boolean equals(Object paramObject) {
    return (this == paramObject) ? true : (!((zzuo)zza(zze.zzbyr, (Object)null, (Object)null)).getClass().isInstance(paramObject) ? false : zzwh.zzxt().<zzuo>zzak(this).equals(this, (zzuo)paramObject));
  }
  
  public int hashCode() {
    int i = this.zzbtk;
    if (i != 0)
      return i; 
    this.zzbtk = zzwh.zzxt().<zzuo>zzak(this).hashCode(this);
    return this.zzbtk;
  }
  
  public final boolean isInitialized() {
    boolean bool2 = Boolean.TRUE.booleanValue();
    byte b = ((Byte)zza(zze.zzbym, (Object)null, (Object)null)).byteValue();
    if (b == 1)
      return true; 
    if (b == 0)
      return false; 
    boolean bool1 = zzwh.zzxt().<zzuo>zzak(this).zzaj(this);
    if (bool2) {
      Object object;
      int i = zze.zzbyn;
      if (bool1) {
        object = this;
      } else {
        object = null;
      } 
      zza(i, object, (Object)null);
    } 
    return bool1;
  }
  
  public String toString() {
    return zzvy.zza(this, super.toString());
  }
  
  protected abstract Object zza(int paramInt, Object paramObject1, Object paramObject2);
  
  final void zzai(int paramInt) {
    this.zzbyg = paramInt;
  }
  
  public final void zzb(zztv paramzztv) throws IOException {
    zzwh.zzxt().zzi(getClass()).zza(this, zztx.zza(paramzztv));
  }
  
  final int zztx() {
    return this.zzbyg;
  }
  
  public final int zzvx() {
    if (this.zzbyg == -1)
      this.zzbyg = zzwh.zzxt().<zzuo>zzak(this).zzai(this); 
    return this.zzbyg;
  }
  
  public final BuilderType zzwf() {
    zza zza = (zza)zza(zze.zzbyq, (Object)null, (Object)null);
    zza.zza(this);
    return (BuilderType)zza;
  }
  
  public static class zza<MessageType extends zzuo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzsy<MessageType, BuilderType> {
    private final MessageType zzbyi;
    
    protected MessageType zzbyj;
    
    private boolean zzbyk;
    
    protected zza(MessageType param1MessageType) {
      this.zzbyi = param1MessageType;
      this.zzbyj = (MessageType)param1MessageType.zza(zzuo.zze.zzbyp, (Object)null, (Object)null);
      this.zzbyk = false;
    }
    
    private static void zza(MessageType param1MessageType1, MessageType param1MessageType2) {
      zzwh.zzxt().<MessageType>zzak(param1MessageType1).zzd(param1MessageType1, param1MessageType2);
    }
    
    public final BuilderType zza(MessageType param1MessageType) {
      zzwk();
      zza(this.zzbyj, param1MessageType);
      return (BuilderType)this;
    }
    
    protected final void zzwk() {
      if (this.zzbyk) {
        zzuo zzuo1 = (zzuo)this.zzbyj.zza(zzuo.zze.zzbyp, (Object)null, (Object)null);
        zza((MessageType)zzuo1, this.zzbyj);
        this.zzbyj = (MessageType)zzuo1;
        this.zzbyk = false;
      } 
    }
    
    public MessageType zzwl() {
      if (this.zzbyk)
        return this.zzbyj; 
      MessageType messageType = this.zzbyj;
      zzwh.zzxt().<MessageType>zzak(messageType).zzy(messageType);
      this.zzbyk = true;
      return this.zzbyj;
    }
    
    public final MessageType zzwm() {
      zzuo zzuo1 = (zzuo)zzwn();
      boolean bool2 = Boolean.TRUE.booleanValue();
      byte b = ((Byte)zzuo1.zza(zzuo.zze.zzbym, (Object)null, (Object)null)).byteValue();
      boolean bool1 = true;
      if (b != 1)
        if (b == 0) {
          bool1 = false;
        } else {
          boolean bool = zzwh.zzxt().<zzuo>zzak(zzuo1).zzaj(zzuo1);
          bool1 = bool;
          if (bool2) {
            Object object;
            int i = zzuo.zze.zzbyn;
            if (bool) {
              object = zzuo1;
            } else {
              object = null;
            } 
            zzuo1.zza(i, object, (Object)null);
            bool1 = bool;
          } 
        }  
      if (bool1)
        return (MessageType)zzuo1; 
      throw new zzxc(zzuo1);
    }
  }
  
  public static final class zzb<T extends zzuo<T, ?>> extends zzsz<T> {
    private final T zzbyi;
    
    public zzb(T param1T) {
      this.zzbyi = param1T;
    }
  }
  
  public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzuo<MessageType, BuilderType> implements zzvx {
    protected zzuf<Object> zzbyl = zzuf.zzvw();
  }
  
  public static final class zzd<ContainingType extends zzvv, Type> extends zztz<ContainingType, Type> {}
  
  public enum zze {
    zzbym, zzbyn, zzbyo, zzbyp, zzbyq, zzbyr, zzbys, zzbyu, zzbyv, zzbyx, zzbyy;
    
    private static final int[] zzbyt = new int[] { zzbym, zzbyn, zzbyo, zzbyp, zzbyq, zzbyr, zzbys };
    
    static {
      int i = zzbyu;
      i = zzbyv;
      i = zzbyx;
      i = zzbyy;
    }
    
    public static int[] zzwp() {
      return (int[])zzbyt.clone();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzuo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */