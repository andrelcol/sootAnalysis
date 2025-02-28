package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public final class zzf extends zza {
  private final List<Component<?>> zza;
  
  private final Map<Class<?>, zzj<?>> zzb = new HashMap<Class<?>, zzj<?>>();
  
  private final zzh zzc;
  
  public zzf(Executor paramExecutor, Iterable<ComponentRegistrar> paramIterable, Component<?>... paramVarArgs) {
    this.zzc = new zzh(paramExecutor);
    ArrayList<Component<?>> arrayList = new ArrayList();
    arrayList.add(Component.of(this.zzc, zzh.class, (Class<? super zzh>[])new Class[] { Subscriber.class, Publisher.class }));
    Iterator<ComponentRegistrar> iterator1 = paramIterable.iterator();
    while (iterator1.hasNext())
      arrayList.addAll(((ComponentRegistrar)iterator1.next()).getComponents()); 
    Collections.addAll(arrayList, paramVarArgs);
    this.zza = Collections.unmodifiableList(Component.null.zza(arrayList));
    Iterator<Component<?>> iterator = this.zza.iterator();
    while (iterator.hasNext())
      zza(iterator.next()); 
    zza();
  }
  
  private void zza() {
    for (Component<?> component : this.zza) {
      for (Dependency dependency : component.zzb()) {
        if (!dependency.zzb() || this.zzb.containsKey(dependency.zza()))
          continue; 
        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", new Object[] { component, dependency.zza() }));
      } 
    } 
  }
  
  private <T> void zza(Component<T> paramComponent) {
    zzj<?> zzj = new zzj(paramComponent.zzc(), new zzl(paramComponent, this));
    for (Class<?> clazz : paramComponent.zza())
      this.zzb.put(clazz, zzj); 
  }
  
  public final <T> Provider<T> getProvider(Class<T> paramClass) {
    Preconditions.checkNotNull(paramClass, "Null interface requested.");
    return (Provider<T>)this.zzb.get(paramClass);
  }
  
  public final void zza(boolean paramBoolean) {
    for (Component<?> component : this.zza) {
      if (component.zze() || (component.zzf() && paramBoolean))
        get(component.zza().iterator().next()); 
    } 
    this.zzc.zza();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */