package com.roadtrack.onstar;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import com.roadtrack.onstar.utils.Utilities;

public class NotificationService extends NotificationListenerService {
  public static String TAG = NotificationService.class.getSimpleName();
  
  public void onCreate() {
    super.onCreate();
    Utilities.escribeArchivo(TAG, "OnCreate:", "NotificationService");
  }
  
  public void onListenerConnected() {
    super.onListenerConnected();
    for (StatusBarNotification statusBarNotification : getActiveNotifications())
      Utilities.escribeArchivo(TAG, "onListenerConnected:", statusBarNotification.getPackageName()); 
  }
  
  public void onNotificationPosted(StatusBarNotification paramStatusBarNotification) {}
  
  public void onNotificationRemoved(StatusBarNotification paramStatusBarNotification) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/NotificationService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */