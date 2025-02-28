package com.google.firebase.components;

import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class zzl extends zza {
  private final Set<Class<?>> zza;
  
  private final Set<Class<?>> zzb;
  
  private final Set<Class<?>> zzc;
  
  private final ComponentContainer zzd;
  
  zzl(Component<?> paramComponent, ComponentContainer paramComponentContainer) {
    HashSet<Class<?>> hashSet1 = new HashSet();
    HashSet<Class<?>> hashSet2 = new HashSet();
    for (Dependency dependency : paramComponent.zzb()) {
      if (dependency.zzc()) {
        hashSet1.add(dependency.zza());
        continue;
      } 
      hashSet2.add(dependency.zza());
    } 
    if (!paramComponent.zzd().isEmpty())
      hashSet1.add(Publisher.class); 
    this.zza = Collections.unmodifiableSet(hashSet1);
    this.zzb = Collections.unmodifiableSet(hashSet2);
    this.zzc = paramComponent.zzd();
    this.zzd = paramComponentContainer;
  }
  
  public final <T> T get(Class<T> paramClass) {
    if (this.zza.contains(paramClass)) {
      T t = (T)this.zzd.get((Class)paramClass);
      return (T)(!paramClass.equals(Publisher.class) ? (Object)t : new zza(this.zzc, (Publisher)t));
    } 
    throw new IllegalArgumentException(String.format("Requesting %s is not allowed.", new Object[] { paramClass }));
  }
  
  public final <T> Provider<T> getProvider(Class<T> paramClass) {
    if (this.zzb.contains(paramClass))
      return this.zzd.getProvider(paramClass); 
    throw new IllegalArgumentException(String.format("Requesting Provider<%s> is not allowed.", new Object[] { paramClass }));
  }
  
  static final class zza implements Publisher {
    public zza(Set<Class<?>> param1Set, Publisher param1Publisher) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */