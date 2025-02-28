package com.google.firebase.components;

import com.google.firebase.inject.Provider;

abstract class zza implements ComponentContainer {
  public <T> T get(Class<T> paramClass) {
    Provider<T> provider = getProvider(paramClass);
    return (T)((provider == null) ? null : provider.get());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */