package com.google.firebase.analytics.connector;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Event;
import com.google.firebase.events.Subscriber;
import java.util.concurrent.ConcurrentHashMap;

public class AnalyticsConnectorImpl implements AnalyticsConnector {
  private static volatile AnalyticsConnector zzbsr;
  
  private final AppMeasurement zzbss;
  
  private AnalyticsConnectorImpl(AppMeasurement paramAppMeasurement) {
    Preconditions.checkNotNull(paramAppMeasurement);
    this.zzbss = paramAppMeasurement;
    new ConcurrentHashMap<Object, Object>();
  }
  
  public static AnalyticsConnector getInstance(FirebaseApp paramFirebaseApp, Context paramContext, Subscriber paramSubscriber) {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_1
    //   6: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   9: pop
    //   10: aload_2
    //   11: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   14: pop
    //   15: aload_1
    //   16: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   19: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: pop
    //   23: getstatic com/google/firebase/analytics/connector/AnalyticsConnectorImpl.zzbsr : Lcom/google/firebase/analytics/connector/AnalyticsConnector;
    //   26: ifnonnull -> 113
    //   29: ldc com/google/firebase/analytics/connector/AnalyticsConnectorImpl
    //   31: monitorenter
    //   32: getstatic com/google/firebase/analytics/connector/AnalyticsConnectorImpl.zzbsr : Lcom/google/firebase/analytics/connector/AnalyticsConnector;
    //   35: ifnonnull -> 101
    //   38: new android/os/Bundle
    //   41: astore_3
    //   42: aload_3
    //   43: iconst_1
    //   44: invokespecial <init> : (I)V
    //   47: aload_0
    //   48: invokevirtual isDefaultApp : ()Z
    //   51: ifeq -> 78
    //   54: aload_2
    //   55: ldc com/google/firebase/DataCollectionDefaultChange
    //   57: getstatic com/google/firebase/analytics/connector/zza.zzbsu : Ljava/util/concurrent/Executor;
    //   60: getstatic com/google/firebase/analytics/connector/zzb.zzbsv : Lcom/google/firebase/events/EventHandler;
    //   63: invokeinterface subscribe : (Ljava/lang/Class;Ljava/util/concurrent/Executor;Lcom/google/firebase/events/EventHandler;)V
    //   68: aload_3
    //   69: ldc 'dataCollectionDefaultEnabled'
    //   71: aload_0
    //   72: invokevirtual isDataCollectionDefaultEnabled : ()Z
    //   75: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   78: new com/google/firebase/analytics/connector/AnalyticsConnectorImpl
    //   81: astore_0
    //   82: aload_0
    //   83: aload_1
    //   84: aload_3
    //   85: invokestatic zzc : (Landroid/os/Bundle;)Lcom/google/android/gms/measurement/internal/zzan;
    //   88: invokestatic zza : (Landroid/content/Context;Lcom/google/android/gms/measurement/internal/zzan;)Lcom/google/android/gms/measurement/internal/zzbw;
    //   91: invokevirtual zzkm : ()Lcom/google/android/gms/measurement/AppMeasurement;
    //   94: invokespecial <init> : (Lcom/google/android/gms/measurement/AppMeasurement;)V
    //   97: aload_0
    //   98: putstatic com/google/firebase/analytics/connector/AnalyticsConnectorImpl.zzbsr : Lcom/google/firebase/analytics/connector/AnalyticsConnector;
    //   101: ldc com/google/firebase/analytics/connector/AnalyticsConnectorImpl
    //   103: monitorexit
    //   104: goto -> 113
    //   107: astore_0
    //   108: ldc com/google/firebase/analytics/connector/AnalyticsConnectorImpl
    //   110: monitorexit
    //   111: aload_0
    //   112: athrow
    //   113: getstatic com/google/firebase/analytics/connector/AnalyticsConnectorImpl.zzbsr : Lcom/google/firebase/analytics/connector/AnalyticsConnector;
    //   116: areturn
    // Exception table:
    //   from	to	target	type
    //   32	78	107	finally
    //   78	101	107	finally
    //   101	104	107	finally
    //   108	111	107	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/analytics/connector/AnalyticsConnectorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */