package com.google.firebase.components;

import com.google.firebase.inject.Provider;

public interface ComponentContainer {
  <T> T get(Class<T> paramClass);
  
  <T> Provider<T> getProvider(Class<T> paramClass);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/ComponentContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */