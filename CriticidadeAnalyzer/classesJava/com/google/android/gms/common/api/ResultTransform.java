package com.google.android.gms.common.api;

public abstract class ResultTransform<R extends Result, S extends Result> {
  public abstract Status onFailure(Status paramStatus);
  
  public abstract PendingResult<S> onSuccess(R paramR);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/ResultTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */