package com.roadtrack.onstar.analytics;

import android.content.Context;
import android.os.Bundle;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.utils.Utilities;

public class FirebaseAnalyticsHelper implements AnalyticsHelper {
  private static volatile FirebaseAnalytics firebaseAnalytics;
  
  private final String TAG = FirebaseAnalyticsHelper.class.getSimpleName();
  
  public void analyticsEventGeneric(Context paramContext, AnalyticsEventModel paramAnalyticsEventModel) {
    try {
      logEvent(paramContext, paramAnalyticsEventModel.getName(), paramAnalyticsEventModel);
      String str = this.TAG;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(" eventName: ");
      stringBuilder.append(paramAnalyticsEventModel.getName());
      stringBuilder.append(" id: ");
      stringBuilder.append(paramAnalyticsEventModel.getId());
      stringBuilder.append(" name: ");
      stringBuilder.append(paramAnalyticsEventModel.getName());
      stringBuilder.append(" content: ");
      stringBuilder.append(paramAnalyticsEventModel.getContent());
      Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder.toString());
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, exception);
    } 
  }
  
  public void genericAnalyticsEventBottom(Context paramContext, Enums.Services paramServices) {
    try {
      AnalyticsUsesCase analyticsUsesCase = new AnalyticsUsesCase();
      this();
      analyticsEventGeneric(paramContext, analyticsUsesCase.getModelForEventBottom(paramServices));
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, exception);
    } 
  }
  
  public void genericAnalyticsEventButtonAction(Context paramContext, Enums.Services paramServices) {
    try {
      AnalyticsUsesCase analyticsUsesCase = new AnalyticsUsesCase();
      this();
      analyticsEventGeneric(paramContext, analyticsUsesCase.getModelForButtonActions(paramServices));
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, exception);
    } 
  }
  
  public void genericAnalyticsEventDrawer(Context paramContext, Enums.Services paramServices) {
    try {
      AnalyticsUsesCase analyticsUsesCase = new AnalyticsUsesCase();
      this();
      analyticsEventGeneric(paramContext, analyticsUsesCase.getModelForEventDrawer(paramServices));
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, exception);
    } 
  }
  
  public FirebaseAnalytics getInstance(Context paramContext) throws RuntimeException {
    // Byte code:
    //   0: getstatic com/roadtrack/onstar/analytics/FirebaseAnalyticsHelper.firebaseAnalytics : Lcom/google/firebase/analytics/FirebaseAnalytics;
    //   3: ifnonnull -> 34
    //   6: ldc com/roadtrack/onstar/analytics/FirebaseAnalyticsHelper
    //   8: monitorenter
    //   9: getstatic com/roadtrack/onstar/analytics/FirebaseAnalyticsHelper.firebaseAnalytics : Lcom/google/firebase/analytics/FirebaseAnalytics;
    //   12: ifnonnull -> 22
    //   15: aload_1
    //   16: invokestatic getInstance : (Landroid/content/Context;)Lcom/google/firebase/analytics/FirebaseAnalytics;
    //   19: putstatic com/roadtrack/onstar/analytics/FirebaseAnalyticsHelper.firebaseAnalytics : Lcom/google/firebase/analytics/FirebaseAnalytics;
    //   22: ldc com/roadtrack/onstar/analytics/FirebaseAnalyticsHelper
    //   24: monitorexit
    //   25: goto -> 34
    //   28: astore_1
    //   29: ldc com/roadtrack/onstar/analytics/FirebaseAnalyticsHelper
    //   31: monitorexit
    //   32: aload_1
    //   33: athrow
    //   34: getstatic com/roadtrack/onstar/analytics/FirebaseAnalyticsHelper.firebaseAnalytics : Lcom/google/firebase/analytics/FirebaseAnalytics;
    //   37: astore_1
    //   38: aload_1
    //   39: areturn
    //   40: astore_1
    //   41: new java/lang/RuntimeException
    //   44: dup
    //   45: ldc 'Error initializing Firebase Analytics'
    //   47: aload_1
    //   48: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   51: athrow
    // Exception table:
    //   from	to	target	type
    //   0	9	40	java/lang/Exception
    //   9	22	28	finally
    //   22	25	28	finally
    //   29	32	28	finally
    //   32	34	40	java/lang/Exception
    //   34	38	40	java/lang/Exception
  }
  
  public void initializeService(Context paramContext) {
    try {
      FirebaseApp.initializeApp(paramContext);
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, exception);
    } 
  }
  
  public void logEvent(Context paramContext, String paramString, AnalyticsEventModel paramAnalyticsEventModel) {
    try {
      FirebaseAnalytics firebaseAnalytics = getInstance(paramContext);
      Bundle bundle = new Bundle();
      this();
      bundle.putString("item_id", paramAnalyticsEventModel.getId());
      bundle.putString("item_name", paramAnalyticsEventModel.getName());
      bundle.putString("content", paramAnalyticsEventModel.getContent());
      firebaseAnalytics.logEvent(paramString, bundle);
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, exception);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/analytics/FirebaseAnalyticsHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */