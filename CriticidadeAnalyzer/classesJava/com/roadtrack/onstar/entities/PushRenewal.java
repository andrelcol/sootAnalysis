package com.roadtrack.onstar.entities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.PushManagerActivity;
import com.roadtrack.onstar.VO.PushNotificationsVO;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.ui.my_plan.OriginalRenewalActivity;
import com.roadtrack.onstar.utils.Utilities;

public class PushRenewal {
  private boolean appAlive;
  
  private NotificationManager notifManager;
  
  public PushRenewal(Context paramContext, boolean paramBoolean) {
    this.appAlive = paramBoolean;
    try {
      this.notifManager = (NotificationManager)paramContext.getSystemService("notification");
    } catch (Exception exception) {
      Utilities.escribeArchivo("MessageRTMobileGenericDialog", "Error: MessageRTMobile", exception.getMessage());
    } 
  }
  
  private void sendNotifications(Context paramContext, int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, boolean paramBoolean) {
    Intent intent1 = new Intent(paramContext, MainActivity.class);
    Bundle bundle = new Bundle();
    bundle.putBoolean("isRenewalpush", true);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramInt3);
    bundle.putString("pushNotificationDeviceId", stringBuilder.toString());
    intent1.setFlags(603979776);
    intent1.putExtras(bundle);
    if (!Utilities.GetTaskInBackGround(paramContext)) {
      intent1 = new Intent(paramContext, LoginActivity.class);
    } else if (Utilities.isActivityRunning(paramContext, LoginActivity.class)) {
      intent1 = new Intent(paramContext, LoginActivity.class);
    } else if (Utilities.isActivityRunning(paramContext, OriginalRenewalActivity.class)) {
      intent1 = new Intent(paramContext, OriginalRenewalActivity.class);
    } else if (Utilities.GetTaskInBackGround(paramContext, LoginActivity.class)) {
      intent1 = new Intent(paramContext, LoginActivity.class);
    } 
    try {
      boolean bool = this.appAlive;
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    Intent intent2 = intent1;
    if (!this.appAlive) {
      Bundle bundle1 = new Bundle();
      bundle1.putBoolean("cleanNotifications", true);
      bundle1.putString("account", Utilities.Decrypt(paramContext.getSharedPreferences("lloged", 0).getString("account", "sLUM6vZor6R7bS7n4Vw4rw==")));
      bundle1.putParcelable("oldIntent", (Parcelable)intent1);
      intent2 = new Intent(paramContext, PushManagerActivity.class);
      intent2.putExtras(bundle1);
    } 
    PendingIntent pendingIntent = PendingIntent.getActivity(paramContext, 0, intent2, 469762048);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(paramContext);
    builder.setContentText(paramString2);
    NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
    bigTextStyle.bigText(paramString2);
    builder.setStyle((NotificationCompat.Style)bigTextStyle);
    builder.setContentTitle(paramString1);
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
    this.notifManager.notify(paramInt2, notification);
  }
  
  public void NotificationRecived(Context paramContext, PushNotificationsVO paramPushNotificationsVO) {
    int j = paramPushNotificationsVO.getAcc_Renewal();
    paramPushNotificationsVO.getIdCampaign_Renewal();
    String str2 = paramPushNotificationsVO.getTitle_Renewal();
    String str1 = paramPushNotificationsVO.getMessage_Renewal();
    int i = paramPushNotificationsVO.getDeviceID_Renewal();
    Utilities.isAndinos().booleanValue();
    if (j == 24)
      sendNotifications(paramContext, 2131165536, str2, str1, 805, i, true); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/entities/PushRenewal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */