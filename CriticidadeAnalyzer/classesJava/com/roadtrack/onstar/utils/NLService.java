package com.roadtrack.onstar.utils;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

@TargetApi(18)
public class NLService extends NotificationListenerService {
  private NLServiceReceiver nlservicereciver;
  
  public void onCreate() {
    super.onCreate();
    this.nlservicereciver = new NLServiceReceiver();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("com.roadtrack.onstar.NOTIFICATION_LISTENER_SERVICE");
    registerReceiver(this.nlservicereciver, intentFilter);
  }
  
  public void onDestroy() {
    super.onDestroy();
    unregisterReceiver(this.nlservicereciver);
  }
  
  public void onNotificationPosted(StatusBarNotification paramStatusBarNotification) {
    // Byte code:
    //   0: new android/content/Intent
    //   3: dup
    //   4: ldc 'com.roadtrack.onstar.NOTIFICATION_LISTENER_ALL'
    //   6: invokespecial <init> : (Ljava/lang/String;)V
    //   9: astore_3
    //   10: aload_1
    //   11: invokevirtual getNotification : ()Landroid/app/Notification;
    //   14: astore #6
    //   16: aload_1
    //   17: invokevirtual getPackageName : ()Ljava/lang/String;
    //   20: astore #4
    //   22: aload_1
    //   23: invokevirtual getId : ()I
    //   26: invokestatic toString : (I)Ljava/lang/String;
    //   29: astore #5
    //   31: aload #6
    //   33: invokevirtual toString : ()Ljava/lang/String;
    //   36: astore_1
    //   37: aconst_null
    //   38: astore_2
    //   39: aload_1
    //   40: ifnull -> 110
    //   43: aload #6
    //   45: invokevirtual toString : ()Ljava/lang/String;
    //   48: ldc 'contentText='
    //   50: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   53: astore_1
    //   54: aload_1
    //   55: arraylength
    //   56: ifle -> 90
    //   59: aload_1
    //   60: iconst_0
    //   61: aaload
    //   62: ldc 'contentTitle='
    //   64: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   67: astore_1
    //   68: aload_1
    //   69: arraylength
    //   70: iconst_1
    //   71: if_icmple -> 81
    //   74: aload_1
    //   75: iconst_1
    //   76: aaload
    //   77: astore_1
    //   78: goto -> 83
    //   81: aconst_null
    //   82: astore_1
    //   83: aload_1
    //   84: astore_2
    //   85: aconst_null
    //   86: astore_1
    //   87: goto -> 112
    //   90: aload_1
    //   91: arraylength
    //   92: iconst_1
    //   93: if_icmple -> 110
    //   96: aload_1
    //   97: iconst_1
    //   98: aaload
    //   99: ldc 'tickerText='
    //   101: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   104: iconst_0
    //   105: aaload
    //   106: astore_1
    //   107: goto -> 112
    //   110: aconst_null
    //   111: astore_1
    //   112: aload_3
    //   113: ldc 'notification_source'
    //   115: aload #4
    //   117: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   120: pop
    //   121: aload_3
    //   122: ldc 'notification_title'
    //   124: aload_2
    //   125: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   128: pop
    //   129: aload_3
    //   130: ldc 'notification_message'
    //   132: aload_1
    //   133: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   136: pop
    //   137: aload_3
    //   138: ldc 'notification_id'
    //   140: aload #5
    //   142: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   145: pop
    //   146: aload_0
    //   147: aload_3
    //   148: invokevirtual sendBroadcast : (Landroid/content/Intent;)V
    //   151: return
  }
  
  public void onNotificationRemoved(StatusBarNotification paramStatusBarNotification) {}
  
  class NLServiceReceiver extends BroadcastReceiver {
    final NLService this$0;
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      if (param1Intent.getStringExtra("command").equals("clearall")) {
        NLService.this.cancelAllNotifications();
      } else if (param1Intent.getStringExtra("command").equals("list")) {
        Intent intent2 = new Intent("com.roadtrack.onstar.NOTIFICATION_LISTENER_ALL");
        intent2.putExtra("notification_event", "============================");
        NLService.this.sendBroadcast(intent2);
        StatusBarNotification[] arrayOfStatusBarNotification = NLService.this.getActiveNotifications();
        int i = arrayOfStatusBarNotification.length;
        byte b1 = 0;
        byte b2 = 1;
        while (b1 < i) {
          StatusBarNotification statusBarNotification = arrayOfStatusBarNotification[b1];
          param1Intent = new Intent("com.roadtrack.onstar.NOTIFICATION_LISTENER_ALL");
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append(statusBarNotification.getNotification());
          stringBuilder2.append("\n");
          String str = stringBuilder2.toString();
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(b2);
          stringBuilder1.append(" ");
          stringBuilder1.append(str);
          param1Intent.putExtra("notification_event", stringBuilder1.toString());
          NLService.this.sendBroadcast(param1Intent);
          b2++;
          b1++;
        } 
        Intent intent1 = new Intent("com.roadtrack.onstar.NOTIFICATION_LISTENER_ALL");
        intent1.putExtra("notification_event", "===== Notification List ====");
        NLService.this.sendBroadcast(intent1);
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/NLService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */