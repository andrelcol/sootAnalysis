package com.roadtrack.onstar.BO;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.entities.MessageRTMobile;
import com.roadtrack.onstar.objects.PushGoRute;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;

public class NotificationRoute {
  int icon;
  
  private NotificationManager notifManager;
  
  public NotificationRoute(Context paramContext) {
    onstarApplication onstarApplication = (onstarApplication)paramContext.getApplicationContext();
    new DBFunctions(paramContext);
    try {
      this.notifManager = (NotificationManager)paramContext.getSystemService("notification");
    } catch (Exception exception) {
      Utilities.escribeArchivo("MessageRTMobileGenericDialog", "Error: MessageRTMobile", exception.getMessage());
    } 
  }
  
  private boolean appInstalledOrNot(String paramString, Context paramContext) {
    PackageManager packageManager = paramContext.getPackageManager();
    try {
      packageManager.getPackageInfo(paramString, 1);
      return true;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return false;
    } 
  }
  
  public void NotificationRecived(Context paramContext, PushGoRute paramPushGoRute) {
    this.icon = 2131165536;
    boolean bool = false;
    if (PreferenceRT.GetValuePreference("com.roadtrack.push.notification.status", 0, paramContext) == 1)
      bool = true; 
    if (bool)
      sendNotifications(paramContext, this.icon, paramPushGoRute.getTitle(), paramPushGoRute.getMessage(), 100, true, paramPushGoRute); 
  }
  
  public void sendNotifications(Context paramContext, int paramInt1, String paramString1, String paramString2, int paramInt2, boolean paramBoolean, PushGoRute paramPushGoRute) {
    Intent intent1;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("geo:0,0?q=");
    stringBuilder.append(paramPushGoRute.getLatitud());
    stringBuilder.append(",");
    stringBuilder.append(paramPushGoRute.getLongitud());
    Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
    if (paramPushGoRute.getNavigationApp().toLowerCase().equals("waze".toLowerCase())) {
      if (appInstalledOrNot("com.waze", paramContext)) {
        try {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("waze://?ll=");
          stringBuilder1.append(paramPushGoRute.getLatitud());
          stringBuilder1.append(",");
          stringBuilder1.append(paramPushGoRute.getLongitud());
          stringBuilder1.append("&navigate=yes");
          String str = stringBuilder1.toString();
          Intent intent = new Intent();
          this("android.intent.action.VIEW", Uri.parse(str));
        } catch (ActivityNotFoundException activityNotFoundException) {
          intent2 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.waze"));
          paramContext.startActivity(intent2);
        } 
      } else {
        intent1 = new Intent("android.intent.action.VIEW");
        intent1.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.waze"));
        intent1.setFlags(404783104);
        paramContext.startActivity(intent1);
        return;
      } 
    } else if (activityNotFoundException.getNavigationApp().toLowerCase().equals("googlemaps".toLowerCase())) {
      if (appInstalledOrNot("com.google.android.apps.maps", paramContext)) {
        ApplicationInfo applicationInfo;
        intent2 = null;
        try {
          ApplicationInfo applicationInfo1 = paramContext.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
          applicationInfo = applicationInfo1;
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          nameNotFoundException.printStackTrace();
        } 
        if (applicationInfo.enabled) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("http://maps.google.com/maps?q=loc:");
          stringBuilder1.append(activityNotFoundException.getLatitud());
          stringBuilder1.append(",");
          stringBuilder1.append(activityNotFoundException.getLongitud());
          stringBuilder1.append(" (");
          stringBuilder1.append(activityNotFoundException.getDestination());
          stringBuilder1.append(")");
          intent2 = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder1.toString()));
          intent2.setPackage("com.google.android.apps.maps");
        } else {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("package:");
          stringBuilder1.append("com.google.android.apps.maps");
          paramContext.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse(stringBuilder1.toString())));
          return;
        } 
      } else {
        intent1 = new Intent("android.intent.action.VIEW");
        intent1.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"));
        intent1.setFlags(404783104);
        paramContext.startActivity(intent1);
        return;
      } 
    } 
    intent2.setFlags(404783104);
    MessageRTMobile.AppName.setPendingNotificationsCount(MessageRTMobile.AppName.getPendingNotificationsCount() + 1);
    PendingIntent pendingIntent = PendingIntent.getActivity(paramContext, 0, intent2, 203423744);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(paramContext);
    builder.setContentText(paramString2);
    NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
    bigTextStyle.bigText(paramString2);
    builder.setStyle((NotificationCompat.Style)bigTextStyle);
    builder.setContentTitle((CharSequence)intent1);
    builder.setSmallIcon(paramInt1);
    builder.setAutoCancel(true);
    builder.setContentIntent(pendingIntent);
    builder.setWhen(System.currentTimeMillis());
    builder.setDefaults(-1);
    if (26 <= Build.VERSION.SDK_INT) {
      NotificationChannel notificationChannel = new NotificationChannel(GlobalMembers.CHANNEL_ID, GlobalMembers.CHANNEL_NAME, GlobalMembers.CHANNEL_IMPORTANCE);
      builder.setChannelId(GlobalMembers.CHANNEL_ID);
      this.notifManager.createNotificationChannel(notificationChannel);
    } 
    Notification notification = builder.build();
    if (paramBoolean && GlobalMembers.NoneBoolean.booleanValue())
      notification.vibrate = new long[] { 500L, 200L, 200L, 500L }; 
    paramContext.startActivity(intent2);
    this.notifManager.notify(paramInt2, notification);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/NotificationRoute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */