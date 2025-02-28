package com.google.firebase.components;

import java.util.List;

public class DependencyCycleException extends DependencyException {
  public DependencyCycleException(List<Component<?>> paramList) {
    super(stringBuilder.toString());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/DependencyCycleException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */