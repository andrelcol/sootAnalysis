package com.google.firebase.events;

public class Event<T> {
  private final Class<T> zza;
  
  private final T zzb;
  
  public T getPayload() {
    return this.zzb;
  }
  
  public Class<T> getType() {
    return this.zza;
  }
  
  public String toString() {
    return String.format("Event{type: %s, payload: %s}", new Object[] { this.zza, this.zzb });
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/events/Event.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */