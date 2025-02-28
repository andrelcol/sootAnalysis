package com.roadtrack.onstar.entities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.NotificationsActivity;
import com.roadtrack.onstar.VO.PushNotificationsVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.utils.GenericDialog;
import com.roadtrack.onstar.utils.Utilities;

public class MessageRTMobileGenericDialog {
  private DBFunctions dbFun;
  
  private NotificationManager notifManager;
  
  public MessageRTMobileGenericDialog(Context paramContext) {
    onstarApplication onstarApplication = (onstarApplication)paramContext.getApplicationContext();
    this.dbFun = new DBFunctions(paramContext);
    try {
      this.notifManager = (NotificationManager)paramContext.getSystemService("notification");
    } catch (Exception exception) {
      Utilities.escribeArchivo("MessageRTMobileGenericDialog", "Error: MessageRTMobile", exception.getMessage());
    } 
  }
  
  public void NotificationRecived(Context paramContext, PushNotificationsVO paramPushNotificationsVO) {
    boolean bool;
    int i = paramPushNotificationsVO.getAcc_Dialog();
    String str6 = paramPushNotificationsVO.getIdCampaign_Dialog();
    String str3 = paramPushNotificationsVO.getTitle_Dialog();
    int j = paramPushNotificationsVO.getShowCheckBox_Dialog();
    String str2 = paramPushNotificationsVO.getMessage_Dialog();
    String str5 = paramPushNotificationsVO.getTitleButton_Dialog();
    String str4 = paramPushNotificationsVO.getUrl_Dialog();
    paramPushNotificationsVO.getExpirationDate_Dialog();
    String str1 = paramPushNotificationsVO.getDeviceId();
    if (!this.dbFun.ExistCampaing(paramPushNotificationsVO)) {
      this.dbFun.InsertGenericDialog(paramPushNotificationsVO);
      bool = true;
    } else {
      bool = this.dbFun.IsDialogShowing(paramPushNotificationsVO);
      this.dbFun.UpdateCampingGenericDialog(paramPushNotificationsVO, bool);
    } 
    Utilities.isAndinos().booleanValue();
    if (bool)
      if (i == 23) {
        boolean bool1;
        if (j == 1) {
          bool = true;
        } else {
          bool = false;
        } 
        if (str4 != null && !str4.equals("")) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        sendNotificationDialog(paramContext, 800, 2131165536, str3, str2, str6, bool, bool1, str5, str4, str1, true);
      } else if (i == 22) {
        sendNotifications(paramContext, str6, 2131165536, str3, str2, 801, str1, true);
      }  
  }
  
  public void sendNotificationDialog(Context paramContext, int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, String paramString4, String paramString5, String paramString6, boolean paramBoolean3) {
    Intent intent1;
    Intent intent2 = new Intent(paramContext, GenericDialog.class);
    Bundle bundle = new Bundle();
    bundle.putString("IdCampaign_Dialog", paramString3);
    bundle.putString("Title", paramString1);
    bundle.putString("Content", paramString2);
    bundle.putBoolean("NotShowAgain", paramBoolean1);
    bundle.putBoolean("MoreInfo", paramBoolean2);
    bundle.putBoolean("CancelButton", false);
    bundle.putString("TitleButton", paramString4);
    bundle.putString("URL_Dialog", paramString5);
    bundle.putString("deviceID", paramString6);
    intent2.putExtras(bundle);
    if (!Utilities.GetTaskInBackGround(paramContext)) {
      intent2 = new Intent(paramContext, LoginActivity.class);
      bundle = new Bundle();
      bundle.putString("IdCampaign_Dialog", paramString3);
      bundle.putString("Title", paramString1);
      bundle.putString("Content", paramString2);
      bundle.putBoolean("NotShowAgain", paramBoolean1);
      bundle.putBoolean("MoreInfo", paramBoolean2);
      bundle.putBoolean("CancelButton", false);
      bundle.putString("TitleButton", paramString4);
      bundle.putString("URL_Dialog", paramString5);
      bundle.putString("deviceID", paramString6);
      bundle.putBoolean("App_Close", true);
      intent2.putExtras(bundle);
      intent1 = intent2;
    } else {
      intent1 = intent2;
    } 
    PendingIntent pendingIntent = PendingIntent.getActivity(paramContext, 0, intent1, 203423744);
    MessageRTMobile.AppName.setPendingNotificationsCount(MessageRTMobile.AppName.getPendingNotificationsCount() + 1);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(paramContext);
    builder.setContentText(paramString2);
    NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
    bigTextStyle.bigText(paramString2);
    builder.setStyle((NotificationCompat.Style)bigTextStyle);
    builder.setContentTitle(paramString1);
    builder.setSmallIcon(paramInt2);
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
    if (paramBoolean3 && GlobalMembers.NoneBoolean.booleanValue())
      notification.vibrate = new long[] { 500L, 200L, 200L, 500L }; 
    this.notifManager.notify(paramInt1, notification);
  }
  
  public void sendNotifications(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, String paramString4, boolean paramBoolean) {
    Intent intent = new Intent(paramContext, NotificationsActivity.class);
    if (!Utilities.GetTaskInBackGround(paramContext)) {
      intent = new Intent(paramContext, LoginActivity.class);
    } else if (Utilities.isActivityRunning(paramContext, LoginActivity.class)) {
      intent = new Intent(paramContext, LoginActivity.class);
    } 
    PendingIntent pendingIntent = PendingIntent.getActivity(paramContext, 0, intent, 203423744);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(paramContext);
    builder.setContentText(paramString3);
    NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
    bigTextStyle.bigText(paramString3);
    builder.setStyle((NotificationCompat.Style)bigTextStyle);
    builder.setContentTitle(paramString2);
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
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/entities/MessageRTMobileGenericDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */