package com.roadtrack.onstar.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.core.app.NotificationCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.ui.splash.SplashActivity;
import java.util.HashMap;

public class BroadcastReceiverLogOut extends BroadcastReceiver {
  private static CountDownTimer countDownTimer;
  
  static Context mContext;
  
  boolean Map = false;
  
  private void startCountDownTimer() {
    CountDownTimer countDownTimer = countDownTimer;
    if (countDownTimer != null) {
      countDownTimer.cancel();
      countDownTimer = null;
    } 
    countDownTimer = new CountDownTimer((GlobalMembers.SessionTimeout * 60000), 60000L) {
        final BroadcastReceiverLogOut this$0;
        
        public void onFinish() {
          StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
          if (!BroadcastReceiverLogOut.this.Map) {
            String str1 = Utilities.getStringFromConfigList(BroadcastReceiverLogOut.mContext, stringsResourcesVO.global_lbl_accionstatusexpirada_1, 2131689895);
            String str2 = Utilities.getStringFromConfigList(BroadcastReceiverLogOut.mContext, stringsResourcesVO.global_lbl_acciondescsesioncerrada_1, 2131689868);
            BroadcastReceiverLogOut.this.NotifyExpired(str1, str2);
            MainActivity.activityAlive = false;
            GlobalMembers.ToExitApp = true;
            GlobalMembers.isSockeActived = false;
            MainActivity.mainContext = null;
            MainActivity.showRenewalDialog = true;
            MainActivity.pendingDialogs = new HashMap<Object, Object>();
            Intent intent = new Intent(BroadcastReceiverLogOut.mContext.getApplicationContext(), SplashActivity.class);
            intent.addFlags(268468224);
            intent.putExtra("EXIT", true);
            BroadcastReceiverLogOut.mContext.startActivity(intent);
          } 
        }
        
        public void onTick(long param1Long) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("LOGOUT > Faltan ");
          stringBuilder.append(param1Long / 1000L / 60L);
          stringBuilder.append(" minutes to close session.");
          Utilities.escribeArchivo("GlobalTouchService", "GlobalTouchService", stringBuilder.toString());
        }
      };
    countDownTimer.start();
  }
  
  public void NotifyExpired(String paramString1, String paramString2) {
    NotificationManager notificationManager = (NotificationManager)mContext.getSystemService("notification");
    notificationManager.cancelAll();
    PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, new Intent(), 201326592);
    Utilities.isAndinos().booleanValue();
    NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
    builder.setContentText(paramString2);
    NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
    bigTextStyle.bigText(paramString2);
    builder.setStyle((NotificationCompat.Style)bigTextStyle);
    builder.setContentTitle(paramString1);
    builder.setSmallIcon(2131165536);
    builder.setAutoCancel(true);
    builder.setContentIntent(pendingIntent);
    builder.setWhen(System.currentTimeMillis());
    builder.setDefaults(-1);
    builder.setNumber(1);
    if (26 <= Build.VERSION.SDK_INT) {
      NotificationChannel notificationChannel = new NotificationChannel(GlobalMembers.CHANNEL_ID, GlobalMembers.CHANNEL_NAME, GlobalMembers.CHANNEL_IMPORTANCE);
      builder.setChannelId(GlobalMembers.CHANNEL_ID);
      notificationManager.createNotificationChannel(notificationChannel);
    } 
    Notification notification = builder.build();
    notification.flags = 16;
    notificationManager.notify(20, notification);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    mContext = paramContext.getApplicationContext();
    Bundle bundle = paramIntent.getExtras();
    if (bundle != null && bundle.containsKey("ACTION_EXTRA")) {
      String str = bundle.getString("ACTION_EXTRA");
      if (str != null && str.equals("usuario_activo")) {
        Utilities.escribeArchivo("GlobalTouchService", "GlobalTouchService", "LOGOUT > El usuario sigue activo, reiniciando contador...");
        startCountDownTimer();
      } else if (str != null && str.equals("usuario_activo_map")) {
        CountDownTimer countDownTimer = countDownTimer;
        if (countDownTimer != null) {
          countDownTimer.cancel();
          countDownTimer = null;
        } 
        Utilities.escribeArchivo("GlobalTouchService", "GlobalTouchService", "LOGOUT > CANCELADO EL SESSION TIMEOUT POR ESTAR EN MAPA ");
      } 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/BroadcastReceiverLogOut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */