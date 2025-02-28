package com.google.firebase.events;

import java.util.concurrent.Executor;

public interface Subscriber {
  <T> void subscribe(Class<T> paramClass, EventHandler<? super T> paramEventHandler);
  
  <T> void subscribe(Class<T> paramClass, Executor paramExecutor, EventHandler<? super T> paramEventHandler);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/events/Subscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */