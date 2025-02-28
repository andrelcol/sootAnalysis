package com.google.firebase.components;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Component<T> {
  private final Set<Class<? super T>> zza;
  
  private final Set<Dependency> zzb;
  
  private final int zzc;
  
  private final ComponentFactory<T> zzd;
  
  private final Set<Class<?>> zze;
  
  private Component(Set<Class<? super T>> paramSet, Set<Dependency> paramSet1, int paramInt, ComponentFactory<T> paramComponentFactory, Set<Class<?>> paramSet2) {
    this.zza = Collections.unmodifiableSet(paramSet);
    this.zzb = Collections.unmodifiableSet(paramSet1);
    this.zzc = paramInt;
    this.zzd = paramComponentFactory;
    this.zze = Collections.unmodifiableSet(paramSet2);
  }
  
  public static <T> Builder<T> builder(Class<T> paramClass) {
    return new Builder<T>(paramClass, new Class[0], (byte)0);
  }
  
  public static <T> Builder<T> builder(Class<T> paramClass, Class<? super T>... paramVarArgs) {
    return new Builder<T>(paramClass, (Class[])paramVarArgs, (byte)0);
  }
  
  @SafeVarargs
  public static <T> Component<T> of(T paramT, Class<T> paramClass, Class<? super T>... paramVarArgs) {
    Builder<T> builder = builder(paramClass, paramVarArgs);
    builder.factory(zzc.zza(paramT));
    return builder.build();
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder("Component<");
    stringBuilder.append(Arrays.toString(this.zza.toArray()));
    stringBuilder.append(">{");
    stringBuilder.append(this.zzc);
    stringBuilder.append(", deps=");
    stringBuilder.append(Arrays.toString(this.zzb.toArray()));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final Set<Class<? super T>> zza() {
    return this.zza;
  }
  
  public final Set<Dependency> zzb() {
    return this.zzb;
  }
  
  public final ComponentFactory<T> zzc() {
    return this.zzd;
  }
  
  public final Set<Class<?>> zzd() {
    return this.zze;
  }
  
  public final boolean zze() {
    return (this.zzc == 1);
  }
  
  public final boolean zzf() {
    return (this.zzc == 2);
  }
  
  public static class Builder<T> {
    private final Set<Class<? super T>> zza = new HashSet<Class<? super T>>();
    
    private final Set<Dependency> zzb = new HashSet<Dependency>();
    
    private int zzc;
    
    private ComponentFactory<T> zzd;
    
    private Set<Class<?>> zze;
    
    private Builder(Class<T> param1Class, Class<? super T>... param1VarArgs) {
      byte b = 0;
      this.zzc = 0;
      this.zze = new HashSet<Class<?>>();
      Preconditions.checkNotNull(param1Class, "Null interface");
      this.zza.add(param1Class);
      int i = param1VarArgs.length;
      while (b < i) {
        Preconditions.checkNotNull(param1VarArgs[b], "Null interface");
        b++;
      } 
      Collections.addAll(this.zza, param1VarArgs);
    }
    
    private Builder<T> zza(int param1Int) {
      boolean bool;
      if (this.zzc == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "Instantiation type has already been set.");
      this.zzc = param1Int;
      return this;
    }
    
    public Builder<T> add(Dependency param1Dependency) {
      Preconditions.checkNotNull(param1Dependency, "Null dependency");
      Class<?> clazz = param1Dependency.zza();
      Preconditions.checkArgument(this.zza.contains(clazz) ^ true, "Components are not allowed to depend on interfaces they themselves provide.");
      this.zzb.add(param1Dependency);
      return this;
    }
    
    public Builder<T> alwaysEager() {
      zza(1);
      return this;
    }
    
    public Component<T> build() {
      boolean bool;
      if (this.zzd != null) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "Missing required property: factory.");
      return new Component<T>(new HashSet<Class<? super T>>(this.zza), new HashSet<Dependency>(this.zzb), this.zzc, this.zzd, this.zze, (byte)0);
    }
    
    public Builder<T> eagerInDefaultApp() {
      zza(2);
      return this;
    }
    
    public Builder<T> factory(ComponentFactory<T> param1ComponentFactory) {
      Preconditions.checkNotNull(param1ComponentFactory, "Null factory");
      this.zzd = param1ComponentFactory;
      return this;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/Component.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */