package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class zzvp<K, V> extends LinkedHashMap<K, V> {
  private static final zzvp zzcan;
  
  private boolean zzbtn = true;
  
  static {
    zzvp zzvp1 = new zzvp();
    zzcan = zzvp1;
    zzvp1.zzbtn = false;
  }
  
  private zzvp() {}
  
  private zzvp(Map<K, V> paramMap) {
    super(paramMap);
  }
  
  private static int zzab(Object paramObject) {
    if (paramObject instanceof byte[])
      return zzuq.hashCode((byte[])paramObject); 
    if (!(paramObject instanceof zzur))
      return paramObject.hashCode(); 
    throw new UnsupportedOperationException();
  }
  
  public static <K, V> zzvp<K, V> zzxg() {
    return zzcan;
  }
  
  private final void zzxi() {
    if (this.zzbtn)
      return; 
    throw new UnsupportedOperationException();
  }
  
  public final void clear() {
    zzxi();
    super.clear();
  }
  
  public final Set<Map.Entry<K, V>> entrySet() {
    return isEmpty() ? Collections.emptySet() : super.entrySet();
  }
  
  public final boolean equals(Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof java/util/Map
    //   4: ifeq -> 171
    //   7: aload_1
    //   8: checkcast java/util/Map
    //   11: astore_1
    //   12: aload_0
    //   13: aload_1
    //   14: if_acmpeq -> 163
    //   17: aload_0
    //   18: invokeinterface size : ()I
    //   23: aload_1
    //   24: invokeinterface size : ()I
    //   29: if_icmpeq -> 37
    //   32: iconst_0
    //   33: istore_2
    //   34: goto -> 165
    //   37: aload_0
    //   38: invokeinterface entrySet : ()Ljava/util/Set;
    //   43: invokeinterface iterator : ()Ljava/util/Iterator;
    //   48: astore #4
    //   50: aload #4
    //   52: invokeinterface hasNext : ()Z
    //   57: ifeq -> 163
    //   60: aload #4
    //   62: invokeinterface next : ()Ljava/lang/Object;
    //   67: checkcast java/util/Map$Entry
    //   70: astore #6
    //   72: aload_1
    //   73: aload #6
    //   75: invokeinterface getKey : ()Ljava/lang/Object;
    //   80: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   85: ifne -> 91
    //   88: goto -> 32
    //   91: aload #6
    //   93: invokeinterface getValue : ()Ljava/lang/Object;
    //   98: astore #5
    //   100: aload_1
    //   101: aload #6
    //   103: invokeinterface getKey : ()Ljava/lang/Object;
    //   108: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   113: astore #6
    //   115: aload #5
    //   117: instanceof [B
    //   120: ifeq -> 148
    //   123: aload #6
    //   125: instanceof [B
    //   128: ifeq -> 148
    //   131: aload #5
    //   133: checkcast [B
    //   136: aload #6
    //   138: checkcast [B
    //   141: invokestatic equals : ([B[B)Z
    //   144: istore_3
    //   145: goto -> 156
    //   148: aload #5
    //   150: aload #6
    //   152: invokevirtual equals : (Ljava/lang/Object;)Z
    //   155: istore_3
    //   156: iload_3
    //   157: ifne -> 50
    //   160: goto -> 32
    //   163: iconst_1
    //   164: istore_2
    //   165: iload_2
    //   166: ifeq -> 171
    //   169: iconst_1
    //   170: ireturn
    //   171: iconst_0
    //   172: ireturn
  }
  
  public final int hashCode() {
    Iterator<Map.Entry> iterator = super.entrySet().iterator();
    int i;
    for (i = 0; iterator.hasNext(); i += zzab(entry.getValue()) ^ j) {
      Map.Entry entry = iterator.next();
      int j = zzab(entry.getKey());
    } 
    return i;
  }
  
  public final boolean isMutable() {
    return this.zzbtn;
  }
  
  public final V put(K paramK, V paramV) {
    zzxi();
    zzuq.checkNotNull(paramK);
    zzuq.checkNotNull(paramV);
    return super.put(paramK, paramV);
  }
  
  public final void putAll(Map<? extends K, ? extends V> paramMap) {
    zzxi();
    for (K k : paramMap.keySet()) {
      zzuq.checkNotNull(k);
      zzuq.checkNotNull(paramMap.get(k));
    } 
    super.putAll(paramMap);
  }
  
  public final V remove(Object paramObject) {
    zzxi();
    return super.remove(paramObject);
  }
  
  public final void zza(zzvp<K, V> paramzzvp) {
    zzxi();
    if (!paramzzvp.isEmpty())
      putAll(paramzzvp); 
  }
  
  public final void zzsw() {
    this.zzbtn = false;
  }
  
  public final zzvp<K, V> zzxh() {
    return isEmpty() ? new zzvp() : new zzvp(this);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */