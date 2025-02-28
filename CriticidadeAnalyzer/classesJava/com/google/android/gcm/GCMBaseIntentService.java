package com.google.android.gcm;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public abstract class GCMBaseIntentService extends IntentService {
  private static final Object LOCK = GCMBaseIntentService.class;
  
  private static final int MAX_BACKOFF_MS;
  
  private static final String TOKEN;
  
  private static int sCounter = 0;
  
  private static final SecureRandom sRandom = new SecureRandom();
  
  private static PowerManager.WakeLock sWakeLock;
  
  private final String[] mSenderIds;
  
  static {
    MAX_BACKOFF_MS = (int)TimeUnit.SECONDS.toMillis(3600L);
    TOKEN = Long.toBinaryString(sRandom.nextLong());
  }
  
  protected GCMBaseIntentService() {
    this(getName("DynamicSenderIds"), null);
  }
  
  private GCMBaseIntentService(String paramString, String[] paramArrayOfString) {
    super(paramString);
    this.mSenderIds = paramArrayOfString;
  }
  
  private static String getName(String paramString) {
    StringBuilder stringBuilder = new StringBuilder("GCMIntentService-");
    stringBuilder.append(paramString);
    stringBuilder.append("-");
    int i = sCounter + 1;
    sCounter = i;
    stringBuilder.append(i);
    return stringBuilder.toString();
  }
  
  private void handleRegistration(Context paramContext, Intent paramIntent) {
    String str3 = paramIntent.getStringExtra("registration_id");
    String str2 = paramIntent.getStringExtra("error");
    String str1 = paramIntent.getStringExtra("unregistered");
    if (str3 != null) {
      GCMRegistrar.resetBackoff(paramContext);
      GCMRegistrar.setRegistrationId(paramContext, str3);
      onRegistered(paramContext, str3);
      return;
    } 
    if (str1 != null) {
      GCMRegistrar.resetBackoff(paramContext);
      onUnregistered(paramContext, GCMRegistrar.clearRegistrationId(paramContext));
      return;
    } 
    if ("SERVICE_NOT_AVAILABLE".equals(str2)) {
      if (onRecoverableError(paramContext, str2)) {
        int j = GCMRegistrar.getBackoff(paramContext);
        int k = j / 2;
        int i = sRandom.nextInt(j);
        Intent intent = new Intent("com.google.android.gcm.intent.RETRY");
        intent.putExtra("token", TOKEN);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(paramContext, 0, intent, 0);
        ((AlarmManager)paramContext.getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + (k + i), pendingIntent);
        if (j < MAX_BACKOFF_MS)
          GCMRegistrar.setBackoff(paramContext, j * 2); 
      } 
    } else {
      onError(paramContext, str2);
    } 
  }
  
  static void runIntentInService(Context paramContext, Intent paramIntent, String paramString) {
    synchronized (LOCK) {
      if (sWakeLock == null)
        sWakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "GCM_LIB"); 
      sWakeLock.acquire();
      paramIntent.setClassName(paramContext, paramString);
      paramContext.startService(paramIntent);
      return;
    } 
  }
  
  protected String[] getSenderIds(Context paramContext) {
    String[] arrayOfString = this.mSenderIds;
    if (arrayOfString != null)
      return arrayOfString; 
    throw new IllegalStateException("sender id not set on constructor");
  }
  
  protected void onDeletedMessages(Context paramContext, int paramInt) {}
  
  protected abstract void onError(Context paramContext, String paramString);
  
  public final void onHandleIntent(Intent paramIntent) {
    try {
      null = getApplicationContext();
      String str = paramIntent.getAction();
      if (str.equals("com.google.android.c2dm.intent.REGISTRATION")) {
        GCMRegistrar.setRetryBroadcastReceiver(null);
        handleRegistration(null, paramIntent);
      } else if (str.equals("com.google.android.c2dm.intent.RECEIVE")) {
        str = paramIntent.getStringExtra("message_type");
        if (str != null) {
          if (str.equals("deleted_messages")) {
            String str1 = paramIntent.getStringExtra("total_deleted");
            if (str1 != null)
              try {
                onDeletedMessages(null, Integer.parseInt(str1));
              } catch (NumberFormatException numberFormatException) {} 
          } 
        } else {
          onMessage(null, (Intent)numberFormatException);
        } 
      } else if (str.equals("com.google.android.gcm.intent.RETRY")) {
        String str1 = numberFormatException.getStringExtra("token");
        boolean bool = TOKEN.equals(str1);
        if (!bool)
          synchronized (LOCK) {
            return;
          }  
        if (GCMRegistrar.isRegistered(null)) {
          GCMRegistrar.internalUnregister(null);
        } else {
          GCMRegistrar.internalRegister(null, getSenderIds(null));
        } 
      } 
    } finally {
      null = null;
    } 
  }
  
  protected abstract void onMessage(Context paramContext, Intent paramIntent);
  
  protected boolean onRecoverableError(Context paramContext, String paramString) {
    return true;
  }
  
  protected abstract void onRegistered(Context paramContext, String paramString);
  
  protected abstract void onUnregistered(Context paramContext, String paramString);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gcm/GCMBaseIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */