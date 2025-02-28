package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzyf implements Cloneable {
  private Object value;
  
  private zzyd<?, ?> zzcfc;
  
  private List<zzyk> zzcfd = new ArrayList<zzyk>();
  
  private final byte[] toByteArray() throws IOException {
    byte[] arrayOfByte = new byte[zzf()];
    zza(zzya.zzo(arrayOfByte));
    return arrayOfByte;
  }
  
  private final zzyf zzzc() {
    zzyf zzyf1 = new zzyf();
    try {
      zzyf1.zzcfc = this.zzcfc;
      if (this.zzcfd == null) {
        zzyf1.zzcfd = null;
      } else {
        zzyf1.zzcfd.addAll(this.zzcfd);
      } 
      if (this.value != null)
        if (this.value instanceof zzyi) {
          zzyf1.value = ((zzyi)this.value).clone();
        } else if (this.value instanceof byte[]) {
          zzyf1.value = ((byte[])this.value).clone();
        } else {
          boolean bool1 = this.value instanceof byte[][];
          boolean bool = false;
          byte b = 0;
          if (bool1) {
            byte[][] arrayOfByte2 = (byte[][])this.value;
            byte[][] arrayOfByte1 = new byte[arrayOfByte2.length][];
            zzyf1.value = arrayOfByte1;
            while (b < arrayOfByte2.length) {
              arrayOfByte1[b] = (byte[])arrayOfByte2[b].clone();
              b++;
            } 
          } else if (this.value instanceof boolean[]) {
            zzyf1.value = ((boolean[])this.value).clone();
          } else if (this.value instanceof int[]) {
            zzyf1.value = ((int[])this.value).clone();
          } else if (this.value instanceof long[]) {
            zzyf1.value = ((long[])this.value).clone();
          } else if (this.value instanceof float[]) {
            zzyf1.value = ((float[])this.value).clone();
          } else if (this.value instanceof double[]) {
            zzyf1.value = ((double[])this.value).clone();
          } else if (this.value instanceof zzyi[]) {
            zzyi[] arrayOfZzyi2 = (zzyi[])this.value;
            zzyi[] arrayOfZzyi1 = new zzyi[arrayOfZzyi2.length];
            zzyf1.value = arrayOfZzyi1;
            for (b = bool; b < arrayOfZzyi2.length; b++)
              arrayOfZzyi1[b] = (zzyi)arrayOfZzyi2[b].clone(); 
          } 
        }  
      return zzyf1;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError(cloneNotSupportedException);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzyf))
      return false; 
    paramObject = paramObject;
    if (this.value != null && ((zzyf)paramObject).value != null) {
      zzyd<?, ?> zzyd1 = this.zzcfc;
      if (zzyd1 != ((zzyf)paramObject).zzcfc)
        return false; 
      if (!zzyd1.zzcew.isArray())
        return this.value.equals(((zzyf)paramObject).value); 
      Object object = this.value;
      return (object instanceof byte[]) ? Arrays.equals((byte[])object, (byte[])((zzyf)paramObject).value) : ((object instanceof int[]) ? Arrays.equals((int[])object, (int[])((zzyf)paramObject).value) : ((object instanceof long[]) ? Arrays.equals((long[])object, (long[])((zzyf)paramObject).value) : ((object instanceof float[]) ? Arrays.equals((float[])object, (float[])((zzyf)paramObject).value) : ((object instanceof double[]) ? Arrays.equals((double[])object, (double[])((zzyf)paramObject).value) : ((object instanceof boolean[]) ? Arrays.equals((boolean[])object, (boolean[])((zzyf)paramObject).value) : Arrays.deepEquals((Object[])object, (Object[])((zzyf)paramObject).value))))));
    } 
    List<zzyk> list = this.zzcfd;
    if (list != null) {
      List<zzyk> list1 = ((zzyf)paramObject).zzcfd;
      if (list1 != null)
        return list.equals(list1); 
    } 
    try {
      return Arrays.equals(toByteArray(), paramObject.toByteArray());
    } catch (IOException iOException) {
      throw new IllegalStateException(iOException);
    } 
  }
  
  public final int hashCode() {
    try {
      int i = Arrays.hashCode(toByteArray());
      return i + 527;
    } catch (IOException iOException) {
      throw new IllegalStateException(iOException);
    } 
  }
  
  final void zza(zzya paramzzya) throws IOException {
    Object object = this.value;
    if (object != null) {
      zzyd<?, ?> zzyd1 = this.zzcfc;
      if (zzyd1.zzcex) {
        int i = Array.getLength(object);
        byte b = 0;
        while (b < i) {
          Object object1 = Array.get(object, b);
          if (object1 == null) {
            b++;
            continue;
          } 
          zzyd1.zza(object1, paramzzya);
          throw null;
        } 
        return;
      } 
      zzyd1.zza(object, paramzzya);
      throw null;
    } 
    for (zzyk zzyk : this.zzcfd) {
      paramzzya.zzcd(zzyk.tag);
      paramzzya.zzp(zzyk.zzbtz);
    } 
  }
  
  final void zza(zzyk paramzzyk) throws IOException {
    zzyi zzyi;
    List<zzyk> list = this.zzcfd;
    if (list != null) {
      list.add(paramzzyk);
      return;
    } 
    Object object = this.value;
    if (object instanceof zzyi) {
      byte[] arrayOfByte = paramzzyk.zzbtz;
      object = zzxz.zzj(arrayOfByte, 0, arrayOfByte.length);
      int i = object.zzvb();
      if (i == arrayOfByte.length - zzya.zzbe(i)) {
        zzyi = ((zzyi)this.value).zza((zzxz)object);
        this.zzcfc = this.zzcfc;
        this.value = zzyi;
        this.zzcfd = null;
        return;
      } 
      throw zzyh.zzzd();
    } 
    if (!(object instanceof zzyi[])) {
      if (!(object instanceof zzvv)) {
        if (object instanceof zzvv[]) {
          this.zzcfc.zzai((List)Collections.singletonList(zzyi));
          throw null;
        } 
        this.zzcfc.zzai((List)Collections.singletonList(zzyi));
        throw null;
      } 
      this.zzcfc.zzai((List)Collections.singletonList(zzyi));
      throw null;
    } 
    this.zzcfc.zzai((List)Collections.singletonList(zzyi));
    throw null;
  }
  
  final int zzf() {
    int i;
    Object<zzyk> object = (Object<zzyk>)this.value;
    boolean bool = false;
    if (object != null) {
      zzyd<?, ?> zzyd1 = this.zzcfc;
      if (zzyd1.zzcex) {
        int j = Array.getLength(object);
        byte b = 0;
        while (true) {
          i = bool;
          if (b < j) {
            Object object1 = Array.get(object, b);
            if (object1 == null) {
              b++;
              continue;
            } 
            zzyd1.zzao(object1);
            throw null;
          } 
          break;
        } 
      } else {
        zzyd1.zzao(object);
        throw null;
      } 
    } else {
      object = (Object<zzyk>)this.zzcfd.iterator();
      for (i = 0; object.hasNext(); i += zzya.zzbl(zzyk.tag) + 0 + zzyk.zzbtz.length)
        zzyk zzyk = object.next(); 
    } 
    return i;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzyf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */