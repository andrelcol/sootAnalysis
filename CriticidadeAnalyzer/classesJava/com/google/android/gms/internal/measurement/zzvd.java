package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzvd extends zzta<String> implements zzve, RandomAccess {
  private static final zzvd zzbzz;
  
  private final List<Object> zzcab;
  
  static {
    zzvd zzvd1 = new zzvd();
    zzbzz = zzvd1;
    zzvd1.zzsw();
  }
  
  public zzvd() {
    this(10);
  }
  
  public zzvd(int paramInt) {
    this(new ArrayList(paramInt));
  }
  
  private zzvd(ArrayList<Object> paramArrayList) {
    this.zzcab = paramArrayList;
  }
  
  private static String zzaa(Object paramObject) {
    return (paramObject instanceof String) ? (String)paramObject : ((paramObject instanceof zzte) ? ((zzte)paramObject).zzud() : zzuq.zzm((byte[])paramObject));
  }
  
  public final boolean addAll(int paramInt, Collection<? extends String> paramCollection) {
    zzua();
    Collection<? extends String> collection = paramCollection;
    if (paramCollection instanceof zzve)
      collection = (Collection)((zzve)paramCollection).zzxb(); 
    boolean bool = this.zzcab.addAll(paramInt, collection);
    this.modCount++;
    return bool;
  }
  
  public final boolean addAll(Collection<? extends String> paramCollection) {
    return addAll(size(), paramCollection);
  }
  
  public final void clear() {
    zzua();
    this.zzcab.clear();
    this.modCount++;
  }
  
  public final int size() {
    return this.zzcab.size();
  }
  
  public final Object zzbp(int paramInt) {
    return this.zzcab.get(paramInt);
  }
  
  public final void zzc(zzte paramzzte) {
    zzua();
    this.zzcab.add(paramzzte);
    this.modCount++;
  }
  
  public final List<?> zzxb() {
    return Collections.unmodifiableList(this.zzcab);
  }
  
  public final zzve zzxc() {
    return (zzve)(zztz() ? new zzxg(this) : this);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */