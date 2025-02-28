package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

class zzh implements Publisher, Subscriber {
  private final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> zza = new HashMap<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>>();
  
  private Queue<Event<?>> zzb = new ArrayDeque<Event<?>>();
  
  private final Executor zzc;
  
  zzh(Executor paramExecutor) {
    this.zzc = paramExecutor;
  }
  
  private Set<Map.Entry<EventHandler<Object>, Executor>> zza(Event<?> paramEvent) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zza : Ljava/util/Map;
    //   6: aload_1
    //   7: invokevirtual getType : ()Ljava/lang/Class;
    //   10: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   15: checkcast java/util/Map
    //   18: astore_1
    //   19: aload_1
    //   20: ifnonnull -> 31
    //   23: invokestatic emptySet : ()Ljava/util/Set;
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: areturn
    //   31: aload_1
    //   32: invokeinterface entrySet : ()Ljava/util/Set;
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: areturn
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	42	finally
    //   23	27	42	finally
    //   31	38	42	finally
  }
  
  public void publish(Event<?> paramEvent) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield zzb : Ljava/util/Queue;
    //   11: ifnull -> 28
    //   14: aload_0
    //   15: getfield zzb : Ljava/util/Queue;
    //   18: aload_1
    //   19: invokeinterface add : (Ljava/lang/Object;)Z
    //   24: pop
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_0
    //   31: aload_1
    //   32: invokespecial zza : (Lcom/google/firebase/events/Event;)Ljava/util/Set;
    //   35: invokeinterface iterator : ()Ljava/util/Iterator;
    //   40: astore_3
    //   41: aload_3
    //   42: invokeinterface hasNext : ()Z
    //   47: ifeq -> 82
    //   50: aload_3
    //   51: invokeinterface next : ()Ljava/lang/Object;
    //   56: checkcast java/util/Map$Entry
    //   59: astore_2
    //   60: aload_2
    //   61: invokeinterface getValue : ()Ljava/lang/Object;
    //   66: checkcast java/util/concurrent/Executor
    //   69: aload_2
    //   70: aload_1
    //   71: invokestatic zza : (Ljava/util/Map$Entry;Lcom/google/firebase/events/Event;)Ljava/lang/Runnable;
    //   74: invokeinterface execute : (Ljava/lang/Runnable;)V
    //   79: goto -> 41
    //   82: return
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   7	27	83	finally
    //   28	30	83	finally
    //   84	86	83	finally
  }
  
  public <T> void subscribe(Class<T> paramClass, EventHandler<? super T> paramEventHandler) {
    subscribe(paramClass, this.zzc, paramEventHandler);
  }
  
  public <T> void subscribe(Class<T> paramClass, Executor paramExecutor, EventHandler<? super T> paramEventHandler) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   6: pop
    //   7: aload_3
    //   8: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   11: pop
    //   12: aload_2
    //   13: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   16: pop
    //   17: aload_0
    //   18: getfield zza : Ljava/util/Map;
    //   21: aload_1
    //   22: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   27: ifne -> 57
    //   30: aload_0
    //   31: getfield zza : Ljava/util/Map;
    //   34: astore #5
    //   36: new java/util/concurrent/ConcurrentHashMap
    //   39: astore #4
    //   41: aload #4
    //   43: invokespecial <init> : ()V
    //   46: aload #5
    //   48: aload_1
    //   49: aload #4
    //   51: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   56: pop
    //   57: aload_0
    //   58: getfield zza : Ljava/util/Map;
    //   61: aload_1
    //   62: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   67: checkcast java/util/concurrent/ConcurrentHashMap
    //   70: aload_3
    //   71: aload_2
    //   72: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   75: pop
    //   76: aload_0
    //   77: monitorexit
    //   78: return
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    // Exception table:
    //   from	to	target	type
    //   2	57	79	finally
    //   57	76	79	finally
  }
  
  final void zza() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzb : Ljava/util/Queue;
    //   6: ifnull -> 22
    //   9: aload_0
    //   10: getfield zzb : Ljava/util/Queue;
    //   13: astore_1
    //   14: aload_0
    //   15: aconst_null
    //   16: putfield zzb : Ljava/util/Queue;
    //   19: goto -> 24
    //   22: aconst_null
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: ifnull -> 62
    //   30: aload_1
    //   31: invokeinterface iterator : ()Ljava/util/Iterator;
    //   36: astore_1
    //   37: aload_1
    //   38: invokeinterface hasNext : ()Z
    //   43: ifeq -> 62
    //   46: aload_0
    //   47: aload_1
    //   48: invokeinterface next : ()Ljava/lang/Object;
    //   53: checkcast com/google/firebase/events/Event
    //   56: invokevirtual publish : (Lcom/google/firebase/events/Event;)V
    //   59: goto -> 37
    //   62: return
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	63	finally
    //   24	26	63	finally
    //   64	66	63	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */