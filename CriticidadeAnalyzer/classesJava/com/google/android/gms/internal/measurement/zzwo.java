package com.google.android.gms.internal.measurement;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzwo<K extends Comparable<K>, V> extends AbstractMap<K, V> {
  private boolean zzbqa;
  
  private final int zzcbt;
  
  private List<zzwv> zzcbu;
  
  private Map<K, V> zzcbv;
  
  private volatile zzwx zzcbw;
  
  private Map<K, V> zzcbx;
  
  private volatile zzwr zzcby;
  
  private zzwo(int paramInt) {
    this.zzcbt = paramInt;
    this.zzcbu = Collections.emptyList();
    this.zzcbv = Collections.emptyMap();
    this.zzcbx = Collections.emptyMap();
  }
  
  private final int zza(K paramK) {
    int j = this.zzcbu.size() - 1;
    if (j >= 0) {
      int k = paramK.compareTo((Comparable)((zzwv)this.zzcbu.get(j)).getKey());
      if (k > 0)
        return -(j + 2); 
      if (k == 0)
        return j; 
    } 
    int i = 0;
    while (i <= j) {
      int k = (i + j) / 2;
      int m = paramK.compareTo((Comparable)((zzwv)this.zzcbu.get(k)).getKey());
      if (m < 0) {
        j = k - 1;
        continue;
      } 
      if (m > 0) {
        i = k + 1;
        continue;
      } 
      return k;
    } 
    return -(i + 1);
  }
  
  static <FieldDescriptorType extends zzuh<FieldDescriptorType>> zzwo<FieldDescriptorType, Object> zzbw(int paramInt) {
    return new zzwp(paramInt);
  }
  
  private final V zzby(int paramInt) {
    zzyf();
    V v = ((zzwv)this.zzcbu.remove(paramInt)).getValue();
    if (!this.zzcbv.isEmpty()) {
      Iterator<Map.Entry<K, V>> iterator = zzyg().entrySet().iterator();
      this.zzcbu.add(new zzwv(this, iterator.next()));
      iterator.remove();
    } 
    return v;
  }
  
  private final void zzyf() {
    if (!this.zzbqa)
      return; 
    throw new UnsupportedOperationException();
  }
  
  private final SortedMap<K, V> zzyg() {
    zzyf();
    if (this.zzcbv.isEmpty() && !(this.zzcbv instanceof TreeMap)) {
      this.zzcbv = new TreeMap<K, V>();
      this.zzcbx = ((TreeMap<K, V>)this.zzcbv).descendingMap();
    } 
    return (SortedMap<K, V>)this.zzcbv;
  }
  
  public void clear() {
    zzyf();
    if (!this.zzcbu.isEmpty())
      this.zzcbu.clear(); 
    if (!this.zzcbv.isEmpty())
      this.zzcbv.clear(); 
  }
  
  public boolean containsKey(Object paramObject) {
    paramObject = paramObject;
    return (zza((K)paramObject) >= 0 || this.zzcbv.containsKey(paramObject));
  }
  
  public Set<Map.Entry<K, V>> entrySet() {
    if (this.zzcbw == null)
      this.zzcbw = new zzwx(this, null); 
    return this.zzcbw;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzwo))
      return super.equals(paramObject); 
    paramObject = paramObject;
    int j = size();
    if (j != paramObject.size())
      return false; 
    int i = zzyc();
    if (i != paramObject.zzyc())
      return entrySet().equals(paramObject.entrySet()); 
    for (byte b = 0; b < i; b++) {
      if (!zzbx(b).equals(paramObject.zzbx(b)))
        return false; 
    } 
    return (i != j) ? this.zzcbv.equals(((zzwo)paramObject).zzcbv) : true;
  }
  
  public V get(Object paramObject) {
    paramObject = paramObject;
    int i = zza((K)paramObject);
    return (i >= 0) ? ((zzwv)this.zzcbu.get(i)).getValue() : this.zzcbv.get(paramObject);
  }
  
  public int hashCode() {
    int k = zzyc();
    int j = 0;
    int i = 0;
    while (j < k) {
      i += ((zzwv)this.zzcbu.get(j)).hashCode();
      j++;
    } 
    j = i;
    if (this.zzcbv.size() > 0)
      j = i + this.zzcbv.hashCode(); 
    return j;
  }
  
  public final boolean isImmutable() {
    return this.zzbqa;
  }
  
  public V remove(Object paramObject) {
    zzyf();
    paramObject = paramObject;
    int i = zza((K)paramObject);
    return (i >= 0) ? zzby(i) : (this.zzcbv.isEmpty() ? null : this.zzcbv.remove(paramObject));
  }
  
  public int size() {
    return this.zzcbu.size() + this.zzcbv.size();
  }
  
  public final V zza(K paramK, V paramV) {
    zzyf();
    int i = zza(paramK);
    if (i >= 0)
      return ((zzwv)this.zzcbu.get(i)).setValue(paramV); 
    zzyf();
    if (this.zzcbu.isEmpty() && !(this.zzcbu instanceof ArrayList))
      this.zzcbu = new ArrayList<zzwv>(this.zzcbt); 
    i = -(i + 1);
    if (i >= this.zzcbt)
      return zzyg().put(paramK, paramV); 
    int j = this.zzcbu.size();
    int k = this.zzcbt;
    if (j == k) {
      zzwv zzwv = this.zzcbu.remove(k - 1);
      zzyg().put((K)zzwv.getKey(), zzwv.getValue());
    } 
    this.zzcbu.add(i, new zzwv(this, paramK, paramV));
    return null;
  }
  
  public final Map.Entry<K, V> zzbx(int paramInt) {
    return this.zzcbu.get(paramInt);
  }
  
  public void zzsw() {
    if (!this.zzbqa) {
      Map<?, ?> map;
      if (this.zzcbv.isEmpty()) {
        map = Collections.emptyMap();
      } else {
        map = Collections.unmodifiableMap(this.zzcbv);
      } 
      this.zzcbv = (Map)map;
      if (this.zzcbx.isEmpty()) {
        map = Collections.emptyMap();
      } else {
        map = Collections.unmodifiableMap(this.zzcbx);
      } 
      this.zzcbx = (Map)map;
      this.zzbqa = true;
    } 
  }
  
  public final int zzyc() {
    return this.zzcbu.size();
  }
  
  public final Iterable<Map.Entry<K, V>> zzyd() {
    return this.zzcbv.isEmpty() ? zzws.zzyi() : this.zzcbv.entrySet();
  }
  
  final Set<Map.Entry<K, V>> zzye() {
    if (this.zzcby == null)
      this.zzcby = new zzwr(this, null); 
    return this.zzcby;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */