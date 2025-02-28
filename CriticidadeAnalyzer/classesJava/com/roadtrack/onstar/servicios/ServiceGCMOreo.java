package com.roadtrack.onstar.servicios;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import androidx.core.app.JobIntentService;
import com.roadtrack.onstar.BO.NotificationRoute;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.HistoricalTestActivity;
import com.roadtrack.onstar.HmiMenu;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.NotificationsActivity;
import com.roadtrack.onstar.TbtListView;
import com.roadtrack.onstar.TbtManeuverInfo;
import com.roadtrack.onstar.TransparentActivity;
import com.roadtrack.onstar.TransparentActivityWithSpinner;
import com.roadtrack.onstar.VO.PushNotificationsVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.nav.routing.MapsCommonFragment;
import com.roadtrack.onstar.nav.routing.NavigateCommonDialogActivity;
import com.roadtrack.onstar.objects.PushGoRute;
import com.roadtrack.onstar.ui.settings.SettingsNewActivity;
import com.roadtrack.onstar.ui.wizard.WizardActivity;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ServiceGCMOreo extends JobIntentService {
  public static String ALERT_PUSHNOTIFICATION = "PUSHNOTIFICATION";
  
  public static String PN_ACC_SENDVIEW_ACTIONS = "PN_ACTIONS";
  
  public static String PN_ACC_SENDVIEW_ALERTS = "PN_ALERTS";
  
  public static String PN_ACC_SENDVIEW_FOLLOWME = "PN_FOLLOWME";
  
  public static String PN_ACC_SENDVIEW_REGIONS = "PN_REGIONS";
  
  private static AudioManager audioManager;
  
  private static Context contex;
  
  private static String deviceID;
  
  public static boolean isActivityRunning = false;
  
  private static StringsResourcesVO stringsResourcesVO;
  
  public static TextToSpeech t1;
  
  private static Bundle buildMixedBubbleExtra(Context paramContext, PushNotificationsVO paramPushNotificationsVO) {
    // Byte code:
    //   0: new java/lang/String
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: pop
    //   8: new java/lang/String
    //   11: dup
    //   12: invokespecial <init> : ()V
    //   15: astore #5
    //   17: new java/lang/String
    //   20: dup
    //   21: invokespecial <init> : ()V
    //   24: pop
    //   25: new java/lang/String
    //   28: dup
    //   29: invokespecial <init> : ()V
    //   32: pop
    //   33: new android/os/Bundle
    //   36: dup
    //   37: invokespecial <init> : ()V
    //   40: astore #6
    //   42: new java/util/ArrayList
    //   45: dup
    //   46: invokespecial <init> : ()V
    //   49: putstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   52: aload_1
    //   53: invokevirtual getLatitude : ()Ljava/lang/String;
    //   56: astore_3
    //   57: aload_1
    //   58: invokevirtual getLongitude : ()Ljava/lang/String;
    //   61: astore #4
    //   63: aload_1
    //   64: invokevirtual getAccNumber : ()Ljava/lang/String;
    //   67: astore #7
    //   69: new java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial <init> : ()V
    //   76: astore_2
    //   77: aload_2
    //   78: ldc ''
    //   80: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: aload_2
    //   85: aload_1
    //   86: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   89: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   92: pop
    //   93: aload_2
    //   94: invokevirtual toString : ()Ljava/lang/String;
    //   97: astore #10
    //   99: aload_1
    //   100: invokevirtual getTitle : ()Ljava/lang/String;
    //   103: astore #8
    //   105: aload_1
    //   106: invokevirtual getGPSStatus : ()Ljava/lang/String;
    //   109: astore #9
    //   111: aload_1
    //   112: invokevirtual getTime : ()Ljava/lang/String;
    //   115: astore_1
    //   116: new java/text/SimpleDateFormat
    //   119: dup
    //   120: ldc 'yyyy-dd-MM HH:mm:ss'
    //   122: invokestatic getDefault : ()Ljava/util/Locale;
    //   125: invokespecial <init> : (Ljava/lang/String;Ljava/util/Locale;)V
    //   128: astore_2
    //   129: new java/text/SimpleDateFormat
    //   132: dup
    //   133: ldc 'dd-MM-yyyy HH:mm:ss'
    //   135: invokestatic getDefault : ()Ljava/util/Locale;
    //   138: invokespecial <init> : (Ljava/lang/String;Ljava/util/Locale;)V
    //   141: astore #11
    //   143: aload #11
    //   145: aload_2
    //   146: aload_1
    //   147: invokevirtual parse : (Ljava/lang/String;)Ljava/util/Date;
    //   150: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
    //   153: astore_1
    //   154: goto -> 161
    //   157: astore_1
    //   158: ldc '00-00-0000 00:00:00'
    //   160: astore_1
    //   161: aload_3
    //   162: ifnull -> 176
    //   165: aload_3
    //   166: astore_2
    //   167: aload_3
    //   168: ldc ''
    //   170: invokevirtual equals : (Ljava/lang/Object;)Z
    //   173: ifeq -> 179
    //   176: ldc '0.0'
    //   178: astore_2
    //   179: aload #4
    //   181: ifnull -> 197
    //   184: aload #4
    //   186: astore_3
    //   187: aload #4
    //   189: ldc ''
    //   191: invokevirtual equals : (Ljava/lang/Object;)Z
    //   194: ifeq -> 200
    //   197: ldc '0.0'
    //   199: astore_3
    //   200: new com/roadtrack/onstar/VO/PushAlertsVO
    //   203: dup
    //   204: ldc '0'
    //   206: aload #8
    //   208: ldc 'vehicle'
    //   210: aload_1
    //   211: aload_2
    //   212: aload_3
    //   213: aload #10
    //   215: ldc 'nActionId'
    //   217: aload #7
    //   219: ldc 'id'
    //   221: ldc 'insertedRow'
    //   223: iconst_1
    //   224: ldc 'completitionCode'
    //   226: ldc 'requestErrorId'
    //   228: ldc 'responseErrorId'
    //   230: ldc '0'
    //   232: iconst_0
    //   233: aload_1
    //   234: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    //   237: astore #4
    //   239: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   242: aload #4
    //   244: invokevirtual add : (Ljava/lang/Object;)Z
    //   247: pop
    //   248: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   251: invokevirtual size : ()I
    //   254: ifle -> 573
    //   257: aload #9
    //   259: ldc '3'
    //   261: invokevirtual equals : (Ljava/lang/Object;)Z
    //   264: ifeq -> 297
    //   267: aload_0
    //   268: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   271: getfield previousposition : Ljava/lang/String;
    //   274: ldc 2131690271
    //   276: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   279: pop
    //   280: aload_0
    //   281: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   284: getfield navigation_lbl_localizame_gpssincobertura_1 : Ljava/lang/String;
    //   287: ldc 2131690158
    //   289: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   292: astore #4
    //   294: goto -> 324
    //   297: aload_0
    //   298: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   301: getfield currentposition : Ljava/lang/String;
    //   304: ldc 2131689742
    //   306: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   309: pop
    //   310: aload_0
    //   311: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   314: getfield global_popup_btn_ok_1 : Ljava/lang/String;
    //   317: ldc 2131689950
    //   319: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   322: astore #4
    //   324: aload #7
    //   326: ldc '6'
    //   328: invokevirtual equals : (Ljava/lang/Object;)Z
    //   331: ifeq -> 344
    //   334: aload #8
    //   336: ldc '27'
    //   338: invokevirtual equals : (Ljava/lang/Object;)Z
    //   341: ifne -> 354
    //   344: aload #8
    //   346: ldc '10'
    //   348: invokevirtual equals : (Ljava/lang/Object;)Z
    //   351: ifeq -> 370
    //   354: aload_0
    //   355: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   358: getfield alert_code_id_follow_me : Ljava/lang/String;
    //   361: ldc 2131689662
    //   363: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   366: astore_0
    //   367: goto -> 491
    //   370: aload #7
    //   372: ldc '6'
    //   374: invokevirtual equals : (Ljava/lang/Object;)Z
    //   377: ifeq -> 406
    //   380: aload #8
    //   382: ldc '15'
    //   384: invokevirtual equals : (Ljava/lang/Object;)Z
    //   387: ifeq -> 406
    //   390: aload_0
    //   391: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   394: getfield global_lbl_accionalertavel_1 : Ljava/lang/String;
    //   397: ldc 2131689851
    //   399: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   402: astore_0
    //   403: goto -> 491
    //   406: aload #7
    //   408: ldc '6'
    //   410: invokevirtual equals : (Ljava/lang/Object;)Z
    //   413: ifeq -> 426
    //   416: aload #8
    //   418: ldc '57'
    //   420: invokevirtual equals : (Ljava/lang/Object;)Z
    //   423: ifne -> 436
    //   426: aload #8
    //   428: ldc '255'
    //   430: invokevirtual equals : (Ljava/lang/Object;)Z
    //   433: ifeq -> 452
    //   436: aload_0
    //   437: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   440: getfield global_lbl_accionalertamov_1 : Ljava/lang/String;
    //   443: ldc 2131689845
    //   445: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   448: astore_0
    //   449: goto -> 491
    //   452: aload #7
    //   454: ldc '6'
    //   456: invokevirtual equals : (Ljava/lang/Object;)Z
    //   459: ifeq -> 488
    //   462: aload #8
    //   464: ldc '235'
    //   466: invokevirtual equals : (Ljava/lang/Object;)Z
    //   469: ifeq -> 488
    //   472: aload_0
    //   473: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   476: getfield global_lbl_accionalertavalet_1 : Ljava/lang/String;
    //   479: ldc 2131689849
    //   481: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   484: astore_0
    //   485: goto -> 491
    //   488: aload #5
    //   490: astore_0
    //   491: getstatic com/roadtrack/onstar/Enums$navigationProcess.External : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   494: astore #5
    //   496: aload #6
    //   498: ldc 'source'
    //   500: aload #5
    //   502: invokevirtual putSerializable : (Ljava/lang/String;Ljava/io/Serializable;)V
    //   505: aload #6
    //   507: ldc 'navextra_params'
    //   509: ldc ''
    //   511: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   514: aload #6
    //   516: ldc 'LASTKNOWDATE'
    //   518: aload_1
    //   519: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   522: aload #6
    //   524: ldc 'GPSSTATUS'
    //   526: aload #4
    //   528: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   531: aload #6
    //   533: ldc 'ALERT_CODE_ID'
    //   535: aload_0
    //   536: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   539: aload #6
    //   541: ldc 'navaction'
    //   543: aload #7
    //   545: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   548: aload #6
    //   550: ldc 'externallatitude'
    //   552: aload_2
    //   553: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   556: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   559: aload #6
    //   561: ldc 'externallongitude'
    //   563: aload_3
    //   564: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   567: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   570: goto -> 573
    //   573: aload #6
    //   575: areturn
    // Exception table:
    //   from	to	target	type
    //   143	154	157	java/lang/Exception
  }
  
  public static boolean containsOnstarActivity(ActivityManager.RunningTaskInfo paramRunningTaskInfo) {
    String str = paramRunningTaskInfo.baseActivity.getClassName();
    return (str.equals(MainActivity.class.getCanonicalName()) || str.equals(TbtListView.class.getCanonicalName()) || str.equals(TbtManeuverInfo.class.getCanonicalName()) || str.equals(WizardActivity.class.getCanonicalName()) || str.equals(SettingsNewActivity.class.getCanonicalName()) || str.equals(NotificationsActivity.class.getCanonicalName()) || str.equals(HistoricalTestActivity.class.getCanonicalName()) || str.equals(NavigateCommonDialogActivity.class.getCanonicalName()) || str.equals(MapsCommonFragment.class.getCanonicalName()) || str.equals(HmiMenu.class.getCanonicalName()) || str.equals(TransparentActivity.class.getCanonicalName()) || str.equals(TransparentActivityWithSpinner.class.getCanonicalName()));
  }
  
  public static void enqueueWork(Context paramContext, Intent paramIntent) {
    JobIntentService.enqueueWork(paramContext, ServiceGCMOreo.class, 1, paramIntent);
  }
  
  public static boolean isActivityRunning(Context paramContext) {
    List list = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(2147483647);
    isActivityRunning = false;
    Iterator<ActivityManager.RunningTaskInfo> iterator = list.iterator();
    while (iterator.hasNext()) {
      if (containsOnstarActivity(iterator.next())) {
        isActivityRunning = true;
        break;
      } 
    } 
    return isActivityRunning;
  }
  
  public static void onHandleIntent(Context paramContext, Intent paramIntent) {
    // Byte code:
    //   0: new com/roadtrack/onstar/VO/StringsResourcesVO
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: putstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   10: aload_0
    //   11: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   14: checkcast com/roadtrack/onstar/onstarApplication
    //   17: astore #4
    //   19: new java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial <init> : ()V
    //   26: astore #4
    //   28: aload #4
    //   30: ldc_w 'Datos: '
    //   33: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: aload #4
    //   39: aload_1
    //   40: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   43: invokevirtual toString : ()Ljava/lang/String;
    //   46: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: ldc_w 'GCM'
    //   53: ldc_w 'PN'
    //   56: aload #4
    //   58: invokevirtual toString : ()Ljava/lang/String;
    //   61: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   64: aload_1
    //   65: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   68: invokevirtual keySet : ()Ljava/util/Set;
    //   71: invokeinterface iterator : ()Ljava/util/Iterator;
    //   76: astore #5
    //   78: aload #5
    //   80: invokeinterface hasNext : ()Z
    //   85: ifeq -> 167
    //   88: aload #5
    //   90: invokeinterface next : ()Ljava/lang/Object;
    //   95: checkcast java/lang/String
    //   98: astore #4
    //   100: new java/lang/StringBuilder
    //   103: dup
    //   104: invokespecial <init> : ()V
    //   107: astore #6
    //   109: aload #6
    //   111: aload #4
    //   113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: pop
    //   117: aload #6
    //   119: ldc_w ' = "'
    //   122: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: pop
    //   126: aload #6
    //   128: aload_1
    //   129: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   132: aload #4
    //   134: invokevirtual get : (Ljava/lang/String;)Ljava/lang/Object;
    //   137: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: aload #6
    //   143: ldc_w '"'
    //   146: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: pop
    //   150: ldc_w 'Bundle Debug'
    //   153: ldc_w 'FCM'
    //   156: aload #6
    //   158: invokevirtual toString : ()Ljava/lang/String;
    //   161: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   164: goto -> 78
    //   167: getstatic com/roadtrack/onstar/BO/GlobalMembers.usingPush : Z
    //   170: ifeq -> 2682
    //   173: aload_1
    //   174: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   177: invokevirtual toString : ()Ljava/lang/String;
    //   180: ldc_w 'Bundle[{CMD=RST_FULL, from=google.com/iid}]'
    //   183: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   186: ifeq -> 190
    //   189: return
    //   190: getstatic com/roadtrack/onstar/Enums$responcesGCM.none_acc : Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   193: astore #4
    //   195: getstatic com/roadtrack/onstar/Enums$Services.None : Lcom/roadtrack/onstar/Enums$Services;
    //   198: astore #5
    //   200: new java/lang/String
    //   203: dup
    //   204: invokespecial <init> : ()V
    //   207: astore #6
    //   209: new com/roadtrack/onstar/VO/PushNotificationsVO
    //   212: dup
    //   213: invokespecial <init> : ()V
    //   216: astore #7
    //   218: aload #7
    //   220: aload_1
    //   221: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   224: ldc_w 'idResponse'
    //   227: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   230: invokevirtual setIdResponse : (Ljava/lang/String;)V
    //   233: aload_1
    //   234: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   237: ldc_w 'Destination'
    //   240: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   243: astore #4
    //   245: ldc ''
    //   247: astore #12
    //   249: aload #4
    //   251: ifnull -> 757
    //   254: aload_1
    //   255: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   258: ldc_w 'Lat'
    //   261: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   264: ifnull -> 282
    //   267: aload_1
    //   268: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   271: ldc_w 'Lat'
    //   274: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   277: astore #4
    //   279: goto -> 286
    //   282: ldc ''
    //   284: astore #4
    //   286: aload_1
    //   287: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   290: ldc_w 'Lon'
    //   293: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   296: ifnull -> 314
    //   299: aload_1
    //   300: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   303: ldc_w 'Lon'
    //   306: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   309: astore #5
    //   311: goto -> 318
    //   314: ldc ''
    //   316: astore #5
    //   318: aload_1
    //   319: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   322: ldc_w 'NavigationApp'
    //   325: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   328: ifnull -> 346
    //   331: aload_1
    //   332: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   335: ldc_w 'NavigationApp'
    //   338: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   341: astore #6
    //   343: goto -> 350
    //   346: ldc ''
    //   348: astore #6
    //   350: aload_1
    //   351: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   354: ldc_w 'Destination'
    //   357: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   360: ifnull -> 378
    //   363: aload_1
    //   364: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   367: ldc_w 'Destination'
    //   370: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   373: astore #7
    //   375: goto -> 382
    //   378: ldc ''
    //   380: astore #7
    //   382: aload_1
    //   383: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   386: ldc_w 'acc'
    //   389: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   392: ifnull -> 410
    //   395: aload_1
    //   396: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   399: ldc_w 'acc'
    //   402: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   405: astore #8
    //   407: goto -> 414
    //   410: ldc ''
    //   412: astore #8
    //   414: aload_1
    //   415: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   418: ldc_w 'message'
    //   421: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   424: ifnull -> 442
    //   427: aload_1
    //   428: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   431: ldc_w 'message'
    //   434: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   437: astore #9
    //   439: goto -> 446
    //   442: ldc ''
    //   444: astore #9
    //   446: aload_1
    //   447: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   450: ldc_w 'title'
    //   453: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   456: ifnull -> 474
    //   459: aload_1
    //   460: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   463: ldc_w 'title'
    //   466: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   469: astore #10
    //   471: goto -> 478
    //   474: ldc ''
    //   476: astore #10
    //   478: aload_1
    //   479: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   482: ldc_w 'badge'
    //   485: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   488: ifnull -> 506
    //   491: aload_1
    //   492: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   495: ldc_w 'badge'
    //   498: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   501: astore #11
    //   503: goto -> 510
    //   506: ldc ''
    //   508: astore #11
    //   510: aload_1
    //   511: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   514: ldc_w 'sound'
    //   517: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   520: ifnull -> 535
    //   523: aload_1
    //   524: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   527: ldc_w 'sound'
    //   530: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   533: astore #12
    //   535: new com/roadtrack/onstar/objects/PushGoRute
    //   538: dup
    //   539: invokespecial <init> : ()V
    //   542: astore_1
    //   543: aload_1
    //   544: aload #7
    //   546: invokevirtual setDestination : (Ljava/lang/String;)V
    //   549: aload_1
    //   550: aload #5
    //   552: invokevirtual setLongitud : (Ljava/lang/String;)V
    //   555: aload_1
    //   556: aload #4
    //   558: invokevirtual setLatitud : (Ljava/lang/String;)V
    //   561: aload_1
    //   562: aload #6
    //   564: invokevirtual setNavigationApp : (Ljava/lang/String;)V
    //   567: aload_1
    //   568: aload #9
    //   570: invokevirtual setMessage : (Ljava/lang/String;)V
    //   573: aload_1
    //   574: aload #10
    //   576: invokevirtual setTitle : (Ljava/lang/String;)V
    //   579: aload_1
    //   580: aload #11
    //   582: invokevirtual setBadge : (Ljava/lang/String;)V
    //   585: aload_1
    //   586: aload #12
    //   588: invokevirtual setSound : (Ljava/lang/String;)V
    //   591: aload_1
    //   592: aload #8
    //   594: invokevirtual setAcc : (Ljava/lang/String;)V
    //   597: aload_0
    //   598: ldc_w 'audio'
    //   601: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   604: checkcast android/media/AudioManager
    //   607: putstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.audioManager : Landroid/media/AudioManager;
    //   610: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.audioManager : Landroid/media/AudioManager;
    //   613: iconst_3
    //   614: invokevirtual setMode : (I)V
    //   617: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.audioManager : Landroid/media/AudioManager;
    //   620: invokevirtual startBluetoothSco : ()V
    //   623: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.audioManager : Landroid/media/AudioManager;
    //   626: iconst_1
    //   627: invokevirtual setBluetoothScoOn : (Z)V
    //   630: getstatic com/roadtrack/onstar/BO/GlobalMembers.enableTTS : Z
    //   633: ifeq -> 654
    //   636: new android/speech/tts/TextToSpeech
    //   639: dup
    //   640: aload_0
    //   641: new com/roadtrack/onstar/servicios/ServiceGCMOreo$1
    //   644: dup
    //   645: invokespecial <init> : ()V
    //   648: invokespecial <init> : (Landroid/content/Context;Landroid/speech/tts/TextToSpeech$OnInitListener;)V
    //   651: putstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.t1 : Landroid/speech/tts/TextToSpeech;
    //   654: aload_1
    //   655: invokevirtual getDestination : ()Ljava/lang/String;
    //   658: astore #5
    //   660: aload_0
    //   661: ldc_w 'audio'
    //   664: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   667: checkcast android/media/AudioManager
    //   670: astore #4
    //   672: aload #4
    //   674: iconst_3
    //   675: aload #4
    //   677: iconst_3
    //   678: invokevirtual getStreamMaxVolume : (I)I
    //   681: iconst_0
    //   682: invokevirtual setStreamVolume : (III)V
    //   685: getstatic com/roadtrack/onstar/BO/GlobalMembers.enableTTS : Z
    //   688: ifeq -> 717
    //   691: new android/os/Handler
    //   694: dup
    //   695: invokestatic getMainLooper : ()Landroid/os/Looper;
    //   698: invokespecial <init> : (Landroid/os/Looper;)V
    //   701: new com/roadtrack/onstar/servicios/ServiceGCMOreo$2
    //   704: dup
    //   705: aload #5
    //   707: invokespecial <init> : (Ljava/lang/String;)V
    //   710: ldc2_w 1000
    //   713: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
    //   716: pop
    //   717: sipush #1000
    //   720: istore_2
    //   721: getstatic com/roadtrack/onstar/BO/GlobalMembers.enableTTS : Z
    //   724: ifeq -> 731
    //   727: sipush #3000
    //   730: istore_2
    //   731: new android/os/Handler
    //   734: dup
    //   735: invokestatic getMainLooper : ()Landroid/os/Looper;
    //   738: invokespecial <init> : (Landroid/os/Looper;)V
    //   741: new com/roadtrack/onstar/servicios/ServiceGCMOreo$3
    //   744: dup
    //   745: aload_1
    //   746: aload_0
    //   747: invokespecial <init> : (Lcom/roadtrack/onstar/objects/PushGoRute;Landroid/content/Context;)V
    //   750: iload_2
    //   751: i2l
    //   752: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
    //   755: pop
    //   756: return
    //   757: aload_1
    //   758: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   761: ldc_w 'acc'
    //   764: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   767: ifnull -> 866
    //   770: aload #7
    //   772: aload_1
    //   773: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   776: ldc_w 'acc'
    //   779: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   782: invokestatic parseInt : (Ljava/lang/String;)I
    //   785: invokestatic parseResponcesGCM : (I)Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   788: invokevirtual setAcc : (Lcom/roadtrack/onstar/Enums$responcesGCM;)V
    //   791: aload #7
    //   793: aload_1
    //   794: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   797: ldc_w 'acc'
    //   800: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   803: invokevirtual setAccNumber : (Ljava/lang/String;)V
    //   806: aload #7
    //   808: aload_1
    //   809: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   812: ldc_w 'message'
    //   815: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   818: invokevirtual setMessage : (Ljava/lang/String;)V
    //   821: aload #7
    //   823: aload_1
    //   824: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   827: ldc_w 'title'
    //   830: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   833: invokevirtual setTitle : (Ljava/lang/String;)V
    //   836: aload #7
    //   838: aload_1
    //   839: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   842: ldc_w 'GPS'
    //   845: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   848: invokevirtual setGPSStatus : (Ljava/lang/String;)V
    //   851: aload #7
    //   853: aload_1
    //   854: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   857: ldc_w 'Created'
    //   860: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   863: invokevirtual setTime : (Ljava/lang/String;)V
    //   866: aload_1
    //   867: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   870: ldc_w 'Acc'
    //   873: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   876: ifnull -> 1070
    //   879: aload_1
    //   880: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   883: ldc_w 'Acc'
    //   886: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   889: invokestatic parseInt : (Ljava/lang/String;)I
    //   892: bipush #22
    //   894: if_icmpeq -> 915
    //   897: aload_1
    //   898: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   901: ldc_w 'Acc'
    //   904: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   907: invokestatic parseInt : (Ljava/lang/String;)I
    //   910: bipush #23
    //   912: if_icmpne -> 1070
    //   915: aload #7
    //   917: aload_1
    //   918: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   921: ldc_w 'Acc'
    //   924: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   927: invokestatic parseInt : (Ljava/lang/String;)I
    //   930: invokevirtual setAcc_Dialog : (I)V
    //   933: aload #7
    //   935: aload_1
    //   936: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   939: ldc_w 'IdCampaign'
    //   942: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   945: invokevirtual setIdCampaign_Dialog : (Ljava/lang/String;)V
    //   948: aload #7
    //   950: aload_1
    //   951: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   954: ldc_w 'Title'
    //   957: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   960: invokevirtual setTitle_Dialog : (Ljava/lang/String;)V
    //   963: aload #7
    //   965: aload_1
    //   966: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   969: ldc_w 'ShowCheckBox'
    //   972: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   975: invokestatic parseInt : (Ljava/lang/String;)I
    //   978: invokevirtual setShowCheckBox_Dialog : (I)V
    //   981: aload #7
    //   983: aload_1
    //   984: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   987: ldc_w 'Message'
    //   990: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   993: invokevirtual setMessage_Dialog : (Ljava/lang/String;)V
    //   996: aload #7
    //   998: aload_1
    //   999: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1002: ldc_w 'TitleButton'
    //   1005: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1008: invokevirtual setTitleButton_Dialog : (Ljava/lang/String;)V
    //   1011: aload #7
    //   1013: aload_1
    //   1014: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1017: ldc_w 'Url'
    //   1020: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1023: invokevirtual setUrl_Dialog : (Ljava/lang/String;)V
    //   1026: aload #7
    //   1028: aload_1
    //   1029: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1032: ldc_w 'ExpirationDate'
    //   1035: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1038: invokevirtual setExpirationDate_Dialog : (Ljava/lang/String;)V
    //   1041: new com/roadtrack/onstar/entities/MessageRTMobileGenericDialog
    //   1044: dup
    //   1045: aload_0
    //   1046: invokespecial <init> : (Landroid/content/Context;)V
    //   1049: astore #4
    //   1051: ldc_w 'EnebledPush'
    //   1054: iconst_1
    //   1055: aload_0
    //   1056: invokestatic GetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   1059: ifeq -> 1070
    //   1062: aload #4
    //   1064: aload_0
    //   1065: aload #7
    //   1067: invokevirtual NotificationRecived : (Landroid/content/Context;Lcom/roadtrack/onstar/VO/PushNotificationsVO;)V
    //   1070: aload_1
    //   1071: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1074: ldc_w 'Acc'
    //   1077: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1080: ifnull -> 1311
    //   1083: aload_1
    //   1084: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1087: ldc_w 'Acc'
    //   1090: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1093: invokestatic parseInt : (Ljava/lang/String;)I
    //   1096: bipush #24
    //   1098: if_icmpne -> 1311
    //   1101: aload #7
    //   1103: aload_1
    //   1104: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1107: ldc_w 'Acc'
    //   1110: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1113: invokestatic parseInt : (Ljava/lang/String;)I
    //   1116: invokevirtual setAcc_Renewal : (I)V
    //   1119: aload #7
    //   1121: aload_1
    //   1122: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1125: ldc_w 'IdCampaign'
    //   1128: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1131: invokevirtual setIdCampaign_Renewal : (Ljava/lang/String;)V
    //   1134: aload #7
    //   1136: aload_1
    //   1137: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1140: ldc_w 'Title'
    //   1143: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1146: invokevirtual setTitle_Renewal : (Ljava/lang/String;)V
    //   1149: aload #7
    //   1151: aload_1
    //   1152: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1155: ldc_w 'Message'
    //   1158: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1161: invokevirtual setMessage_Renewal : (Ljava/lang/String;)V
    //   1164: aload #7
    //   1166: aload_1
    //   1167: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1170: ldc_w 'Badge'
    //   1173: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1176: invokestatic parseInt : (Ljava/lang/String;)I
    //   1179: invokevirtual setShowCheckBox_Renewal : (I)V
    //   1182: aload #7
    //   1184: aload_1
    //   1185: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1188: ldc_w 'Sound'
    //   1191: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1194: invokevirtual setTitleButton_Renewal : (Ljava/lang/String;)V
    //   1197: aload #7
    //   1199: aload_1
    //   1200: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1203: ldc_w 'ShowCheckBox'
    //   1206: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1209: invokestatic parseInt : (Ljava/lang/String;)I
    //   1212: invokevirtual setShowCheckBox_Renewal : (I)V
    //   1215: aload #7
    //   1217: aload_1
    //   1218: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1221: ldc_w 'TitleButton'
    //   1224: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1227: invokevirtual setTitleButton_Renewal : (Ljava/lang/String;)V
    //   1230: aload #7
    //   1232: aload_1
    //   1233: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1236: ldc_w 'Url'
    //   1239: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1242: invokevirtual setUrl_Renewal : (Ljava/lang/String;)V
    //   1245: aload #7
    //   1247: aload_1
    //   1248: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1251: ldc_w 'ExpirationDate'
    //   1254: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1257: invokevirtual setExpirationDate_Renewal : (Ljava/lang/String;)V
    //   1260: aload #7
    //   1262: aload_1
    //   1263: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1266: ldc_w 'DeviceId'
    //   1269: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1272: invokestatic parseInt : (Ljava/lang/String;)I
    //   1275: invokevirtual setDeviceID_Renewal : (I)V
    //   1278: new com/roadtrack/onstar/entities/PushRenewal
    //   1281: dup
    //   1282: aload_0
    //   1283: aload_0
    //   1284: invokestatic isActivityRunning : (Landroid/content/Context;)Z
    //   1287: invokespecial <init> : (Landroid/content/Context;Z)V
    //   1290: astore #4
    //   1292: ldc_w 'EnebledPush'
    //   1295: iconst_1
    //   1296: aload_0
    //   1297: invokestatic GetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   1300: ifeq -> 1311
    //   1303: aload #4
    //   1305: aload_0
    //   1306: aload #7
    //   1308: invokevirtual NotificationRecived : (Landroid/content/Context;Lcom/roadtrack/onstar/VO/PushNotificationsVO;)V
    //   1311: aload_1
    //   1312: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1315: ldc_w 'actionId'
    //   1318: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1321: ifnull -> 1342
    //   1324: aload_1
    //   1325: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1328: ldc_w 'actionId'
    //   1331: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1334: invokestatic parseInt : (Ljava/lang/String;)I
    //   1337: invokestatic parseActionType : (I)Lcom/roadtrack/onstar/Enums$Services;
    //   1340: astore #5
    //   1342: aload #5
    //   1344: astore #4
    //   1346: aload_1
    //   1347: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1350: ldc_w 'Acc'
    //   1353: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1356: ifnull -> 1503
    //   1359: aload #5
    //   1361: astore #4
    //   1363: aload_1
    //   1364: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1367: ldc_w 'Acc'
    //   1370: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1373: invokestatic parseInt : (Ljava/lang/String;)I
    //   1376: getstatic com/roadtrack/onstar/Enums$Services.FindMeNotification : Lcom/roadtrack/onstar/Enums$Services;
    //   1379: invokevirtual GetCode : ()I
    //   1382: if_icmpne -> 1503
    //   1385: aload #7
    //   1387: aload_1
    //   1388: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1391: ldc_w 'Acc'
    //   1394: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1397: invokestatic parseInt : (Ljava/lang/String;)I
    //   1400: invokevirtual setAcc_Dialog : (I)V
    //   1403: aload #7
    //   1405: aload_1
    //   1406: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1409: ldc_w 'Title'
    //   1412: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1415: invokevirtual setTitle_Dialog : (Ljava/lang/String;)V
    //   1418: aload #7
    //   1420: aload_1
    //   1421: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1424: ldc_w 'Message'
    //   1427: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1430: invokevirtual setMessage_Dialog : (Ljava/lang/String;)V
    //   1433: aload #7
    //   1435: aload_1
    //   1436: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1439: ldc_w 'Url'
    //   1442: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1445: invokevirtual setUrl_Dialog : (Ljava/lang/String;)V
    //   1448: aload #7
    //   1450: aload_1
    //   1451: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1454: ldc_w 'ExpirationDate'
    //   1457: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1460: invokevirtual setExpirationDate_Dialog : (Ljava/lang/String;)V
    //   1463: aload_0
    //   1464: invokestatic isActivityRunning : (Landroid/content/Context;)Z
    //   1467: istore_3
    //   1468: getstatic com/roadtrack/onstar/Enums$Services.None : Lcom/roadtrack/onstar/Enums$Services;
    //   1471: astore #4
    //   1473: new com/roadtrack/onstar/entities/PushRenewal
    //   1476: dup
    //   1477: aload_0
    //   1478: iload_3
    //   1479: invokespecial <init> : (Landroid/content/Context;Z)V
    //   1482: astore #5
    //   1484: ldc_w 'EnebledPush'
    //   1487: iconst_1
    //   1488: aload_0
    //   1489: invokestatic GetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   1492: ifeq -> 1503
    //   1495: aload #5
    //   1497: aload_0
    //   1498: aload #7
    //   1500: invokevirtual NotificationRecived : (Landroid/content/Context;Lcom/roadtrack/onstar/VO/PushNotificationsVO;)V
    //   1503: aload #4
    //   1505: astore #5
    //   1507: aload_1
    //   1508: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1511: ldc_w 'acc'
    //   1514: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1517: ifnull -> 1661
    //   1520: aload #4
    //   1522: astore #5
    //   1524: aload_1
    //   1525: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1528: ldc_w 'acc'
    //   1531: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1534: invokestatic parseInt : (Ljava/lang/String;)I
    //   1537: sipush #200
    //   1540: if_icmpne -> 1661
    //   1543: aload #7
    //   1545: aload_1
    //   1546: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1549: ldc_w 'acc'
    //   1552: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1555: invokestatic parseInt : (Ljava/lang/String;)I
    //   1558: invokevirtual setAcc_Dialog : (I)V
    //   1561: aload #7
    //   1563: aload_1
    //   1564: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1567: ldc_w 'Title'
    //   1570: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1573: invokevirtual setTitle_Dialog : (Ljava/lang/String;)V
    //   1576: aload #7
    //   1578: aload_1
    //   1579: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1582: ldc_w 'Message'
    //   1585: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1588: invokevirtual setMessage_Dialog : (Ljava/lang/String;)V
    //   1591: aload #7
    //   1593: aload_1
    //   1594: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1597: ldc_w 'Url'
    //   1600: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1603: invokevirtual setUrl_Dialog : (Ljava/lang/String;)V
    //   1606: aload #7
    //   1608: aload_1
    //   1609: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1612: ldc_w 'ExpirationDate'
    //   1615: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1618: invokevirtual setExpirationDate_Dialog : (Ljava/lang/String;)V
    //   1621: aload_0
    //   1622: invokestatic isActivityRunning : (Landroid/content/Context;)Z
    //   1625: istore_3
    //   1626: getstatic com/roadtrack/onstar/Enums$Services.None : Lcom/roadtrack/onstar/Enums$Services;
    //   1629: astore #5
    //   1631: new com/roadtrack/onstar/entities/PushRenewal
    //   1634: dup
    //   1635: aload_0
    //   1636: iload_3
    //   1637: invokespecial <init> : (Landroid/content/Context;Z)V
    //   1640: astore #4
    //   1642: ldc_w 'EnebledPush'
    //   1645: iconst_1
    //   1646: aload_0
    //   1647: invokestatic GetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   1650: ifeq -> 1661
    //   1653: aload #4
    //   1655: aload_0
    //   1656: aload #7
    //   1658: invokevirtual NotificationRecived : (Landroid/content/Context;Lcom/roadtrack/onstar/VO/PushNotificationsVO;)V
    //   1661: aload_1
    //   1662: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1665: ldc_w 'acc'
    //   1668: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1671: ifnull -> 1691
    //   1674: aload_1
    //   1675: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1678: ldc_w 'acc'
    //   1681: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1684: invokestatic parseInt : (Ljava/lang/String;)I
    //   1687: istore_2
    //   1688: goto -> 1723
    //   1691: aload_1
    //   1692: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1695: ldc_w 'Acc'
    //   1698: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1701: ifnull -> 1721
    //   1704: aload_1
    //   1705: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1708: ldc_w 'Acc'
    //   1711: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1714: invokestatic parseInt : (Ljava/lang/String;)I
    //   1717: istore_2
    //   1718: goto -> 1723
    //   1721: iconst_0
    //   1722: istore_2
    //   1723: aload #5
    //   1725: astore #4
    //   1727: aload_1
    //   1728: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1731: ldc_w 'Acc'
    //   1734: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1737: ifnull -> 1958
    //   1740: iload_2
    //   1741: getstatic com/roadtrack/onstar/Enums$Services.FindMeNotification : Lcom/roadtrack/onstar/Enums$Services;
    //   1744: invokevirtual GetCode : ()I
    //   1747: if_icmpeq -> 1774
    //   1750: iload_2
    //   1751: getstatic com/roadtrack/onstar/Enums$Services.DTCNotification : Lcom/roadtrack/onstar/Enums$Services;
    //   1754: invokevirtual GetCode : ()I
    //   1757: if_icmpeq -> 1774
    //   1760: aload #5
    //   1762: astore #4
    //   1764: iload_2
    //   1765: getstatic com/roadtrack/onstar/Enums$Services.EmergencyNotification : Lcom/roadtrack/onstar/Enums$Services;
    //   1768: invokevirtual GetCode : ()I
    //   1771: if_icmpne -> 1958
    //   1774: aload #7
    //   1776: aload_1
    //   1777: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1780: ldc_w 'Acc'
    //   1783: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1786: invokestatic parseInt : (Ljava/lang/String;)I
    //   1789: invokestatic parseResponcesGCM : (I)Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   1792: invokevirtual setAcc : (Lcom/roadtrack/onstar/Enums$responcesGCM;)V
    //   1795: aload #7
    //   1797: aload_1
    //   1798: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1801: ldc_w 'Acc'
    //   1804: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1807: invokestatic parseInt : (Ljava/lang/String;)I
    //   1810: invokevirtual setAcc_Dialog : (I)V
    //   1813: aload #7
    //   1815: aload_1
    //   1816: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1819: ldc_w 'Title'
    //   1822: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1825: invokevirtual setTitle_Dialog : (Ljava/lang/String;)V
    //   1828: aload #7
    //   1830: aload_1
    //   1831: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1834: ldc_w 'Message'
    //   1837: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1840: invokevirtual setMessage_Dialog : (Ljava/lang/String;)V
    //   1843: aload #7
    //   1845: aload_1
    //   1846: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1849: ldc_w 'Url'
    //   1852: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1855: invokevirtual setUrl_Dialog : (Ljava/lang/String;)V
    //   1858: aload #7
    //   1860: aload_1
    //   1861: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1864: ldc_w 'ExpirationDate'
    //   1867: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1870: invokevirtual setExpirationDate_Dialog : (Ljava/lang/String;)V
    //   1873: aload #7
    //   1875: aload_1
    //   1876: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1879: ldc_w 'DeviceId'
    //   1882: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1885: invokevirtual setDeviceId : (Ljava/lang/String;)V
    //   1888: aload #7
    //   1890: aload_1
    //   1891: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1894: ldc_w 'Title'
    //   1897: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1900: invokevirtual setTitle : (Ljava/lang/String;)V
    //   1903: aload #7
    //   1905: aload_1
    //   1906: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1909: ldc_w 'Message'
    //   1912: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1915: invokevirtual setMessage : (Ljava/lang/String;)V
    //   1918: aload_0
    //   1919: invokestatic isActivityRunning : (Landroid/content/Context;)Z
    //   1922: istore_3
    //   1923: getstatic com/roadtrack/onstar/Enums$Services.None : Lcom/roadtrack/onstar/Enums$Services;
    //   1926: astore #4
    //   1928: new com/roadtrack/onstar/entities/PushRenewal
    //   1931: dup
    //   1932: aload_0
    //   1933: iload_3
    //   1934: invokespecial <init> : (Landroid/content/Context;Z)V
    //   1937: astore #5
    //   1939: ldc_w 'EnebledPush'
    //   1942: iconst_1
    //   1943: aload_0
    //   1944: invokestatic GetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   1947: ifeq -> 1958
    //   1950: aload #5
    //   1952: aload_0
    //   1953: aload #7
    //   1955: invokevirtual NotificationRecived : (Landroid/content/Context;Lcom/roadtrack/onstar/VO/PushNotificationsVO;)V
    //   1958: aload #7
    //   1960: aload #4
    //   1962: invokevirtual setActionId : (Lcom/roadtrack/onstar/Enums$Services;)V
    //   1965: aload_1
    //   1966: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1969: ldc_w 'deviceId'
    //   1972: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1975: putstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.deviceID : Ljava/lang/String;
    //   1978: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.deviceID : Ljava/lang/String;
    //   1981: ifnonnull -> 2008
    //   1984: aload_1
    //   1985: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   1988: ldc_w 'DeviceId'
    //   1991: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1994: putstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.deviceID : Ljava/lang/String;
    //   1997: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.deviceID : Ljava/lang/String;
    //   2000: ifnonnull -> 2008
    //   2003: ldc ''
    //   2005: putstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.deviceID : Ljava/lang/String;
    //   2008: aload #7
    //   2010: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.deviceID : Ljava/lang/String;
    //   2013: invokevirtual setDeviceId : (Ljava/lang/String;)V
    //   2016: invokestatic getActualTime : ()Ljava/lang/String;
    //   2019: astore #8
    //   2021: aload #7
    //   2023: aload_1
    //   2024: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2027: ldc_w 'alertId'
    //   2030: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2033: invokevirtual setAlertId : (Ljava/lang/String;)V
    //   2036: aload_1
    //   2037: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2040: ldc_w 'Latitude'
    //   2043: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2046: ifnull -> 2088
    //   2049: aload_1
    //   2050: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2053: ldc_w 'Latitude'
    //   2056: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2059: ldc ''
    //   2061: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2064: ifeq -> 2070
    //   2067: goto -> 2088
    //   2070: aload #7
    //   2072: aload_1
    //   2073: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2076: ldc_w 'Latitude'
    //   2079: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2082: invokevirtual setLatitude : (Ljava/lang/String;)V
    //   2085: goto -> 2095
    //   2088: aload #7
    //   2090: ldc '0.0'
    //   2092: invokevirtual setLatitude : (Ljava/lang/String;)V
    //   2095: aload_1
    //   2096: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2099: ldc_w 'Longitude'
    //   2102: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2105: ifnull -> 2147
    //   2108: aload_1
    //   2109: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2112: ldc_w 'Longitude'
    //   2115: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2118: ldc ''
    //   2120: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2123: ifeq -> 2129
    //   2126: goto -> 2147
    //   2129: aload #7
    //   2131: aload_1
    //   2132: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2135: ldc_w 'Longitude'
    //   2138: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2141: invokevirtual setLongitude : (Ljava/lang/String;)V
    //   2144: goto -> 2154
    //   2147: aload #7
    //   2149: ldc '0.0'
    //   2151: invokevirtual setLongitude : (Ljava/lang/String;)V
    //   2154: aload_1
    //   2155: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2158: ldc_w 'acc'
    //   2161: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2164: ifnull -> 2187
    //   2167: aload_1
    //   2168: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2171: ldc_w 'acc'
    //   2174: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2177: invokevirtual trim : ()Ljava/lang/String;
    //   2180: invokestatic parseInt : (Ljava/lang/String;)I
    //   2183: istore_2
    //   2184: goto -> 2222
    //   2187: aload_1
    //   2188: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2191: ldc_w 'Acc'
    //   2194: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2197: ifnull -> 2220
    //   2200: aload_1
    //   2201: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2204: ldc_w 'Acc'
    //   2207: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2210: invokevirtual trim : ()Ljava/lang/String;
    //   2213: invokestatic parseInt : (Ljava/lang/String;)I
    //   2216: istore_2
    //   2217: goto -> 2222
    //   2220: iconst_0
    //   2221: istore_2
    //   2222: aload_1
    //   2223: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2226: ldc_w 'OMId'
    //   2229: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2232: astore #9
    //   2234: iload_2
    //   2235: bipush #12
    //   2237: if_icmpne -> 2247
    //   2240: aload #4
    //   2242: astore #5
    //   2244: goto -> 2592
    //   2247: iload_2
    //   2248: bipush #10
    //   2250: if_icmpne -> 2265
    //   2253: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   2256: astore #5
    //   2258: aload #4
    //   2260: astore #5
    //   2262: goto -> 2592
    //   2265: iload_2
    //   2266: bipush #6
    //   2268: if_icmpne -> 2556
    //   2271: new com/roadtrack/onstar/DAO/DBFunctions
    //   2274: dup
    //   2275: aload_0
    //   2276: invokespecial <init> : (Landroid/content/Context;)V
    //   2279: astore #10
    //   2281: aload #4
    //   2283: astore #5
    //   2285: aload_1
    //   2286: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2289: ldc_w 'alertId'
    //   2292: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2295: ifnull -> 2592
    //   2298: aload #4
    //   2300: astore #5
    //   2302: aload_1
    //   2303: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2306: ldc_w 'alertId'
    //   2309: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2312: ldc_w '-1'
    //   2315: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   2318: ifeq -> 2592
    //   2321: getstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_ON_PARTS : Ljava/lang/Boolean;
    //   2324: invokevirtual booleanValue : ()Z
    //   2327: ifne -> 2555
    //   2330: getstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_VERSION : I
    //   2333: getstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_WITH_PUSH : I
    //   2336: if_icmpeq -> 2342
    //   2339: goto -> 2555
    //   2342: aload #7
    //   2344: iload_2
    //   2345: iconst_1
    //   2346: iadd
    //   2347: invokestatic parseResponcesGCM : (I)Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   2350: invokevirtual setAcc : (Lcom/roadtrack/onstar/Enums$responcesGCM;)V
    //   2353: aload #7
    //   2355: aload_1
    //   2356: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2359: ldc_w 'NewVersion'
    //   2362: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2365: invokevirtual setNewVersion : (Ljava/lang/String;)V
    //   2368: aload #7
    //   2370: aload_1
    //   2371: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2374: ldc_w 'OldVersion'
    //   2377: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2380: invokevirtual setOldVersion : (Ljava/lang/String;)V
    //   2383: new java/lang/StringBuilder
    //   2386: dup
    //   2387: invokespecial <init> : ()V
    //   2390: astore #5
    //   2392: aload #5
    //   2394: aload_1
    //   2395: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2398: ldc_w 'OldVersion'
    //   2401: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2404: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2407: pop
    //   2408: aload #5
    //   2410: ldc_w '__'
    //   2413: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2416: pop
    //   2417: aload #5
    //   2419: aload_1
    //   2420: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2423: ldc_w 'NewVersion'
    //   2426: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2429: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2432: pop
    //   2433: aload #5
    //   2435: ldc_w '.zip'
    //   2438: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2441: pop
    //   2442: aload #5
    //   2444: invokevirtual toString : ()Ljava/lang/String;
    //   2447: putstatic com/roadtrack/onstar/BO/GlobalMembers.mapFileName : Ljava/lang/String;
    //   2450: new com/roadtrack/onstar/utils/GetHexDumpMap
    //   2453: dup
    //   2454: invokespecial <init> : ()V
    //   2457: getstatic com/roadtrack/onstar/BO/GlobalMembers.mapFileName : Ljava/lang/String;
    //   2460: invokevirtual getMapUpdateFile : (Ljava/lang/String;)Ljava/lang/String;
    //   2463: invokestatic getFileFromStringFile : (Ljava/lang/String;)Ljava/io/File;
    //   2466: invokevirtual exists : ()Z
    //   2469: ifeq -> 2473
    //   2472: return
    //   2473: aload #7
    //   2475: getstatic com/roadtrack/onstar/BO/GlobalMembers.mapFileName : Ljava/lang/String;
    //   2478: invokevirtual setFileName : (Ljava/lang/String;)V
    //   2481: aload #7
    //   2483: aload_0
    //   2484: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   2487: getfield main_activity_map_update_push_message : Ljava/lang/String;
    //   2490: ldc_w 2131690041
    //   2493: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   2496: invokevirtual setMessage : (Ljava/lang/String;)V
    //   2499: aload #7
    //   2501: aload_0
    //   2502: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   2505: getfield main_activity_map_update_push_title : Ljava/lang/String;
    //   2508: ldc_w 2131690042
    //   2511: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   2514: invokevirtual setTitle : (Ljava/lang/String;)V
    //   2517: aload #10
    //   2519: invokevirtual isMapUpdateEmpty : ()Z
    //   2522: ifeq -> 2540
    //   2525: aload #10
    //   2527: aload #7
    //   2529: invokevirtual addMapUpdateData : (Lcom/roadtrack/onstar/VO/PushNotificationsVO;)J
    //   2532: pop2
    //   2533: aload #4
    //   2535: astore #5
    //   2537: goto -> 2592
    //   2540: aload #10
    //   2542: aload #7
    //   2544: invokevirtual updateMapUpdateData : (Lcom/roadtrack/onstar/VO/PushNotificationsVO;)Z
    //   2547: pop
    //   2548: aload #4
    //   2550: astore #5
    //   2552: goto -> 2592
    //   2555: return
    //   2556: iload_2
    //   2557: bipush #8
    //   2559: if_icmpne -> 2569
    //   2562: aload #4
    //   2564: astore #5
    //   2566: goto -> 2592
    //   2569: aload #4
    //   2571: astore #5
    //   2573: iload_2
    //   2574: bipush #28
    //   2576: if_icmpne -> 2592
    //   2579: aload #7
    //   2581: getstatic com/roadtrack/onstar/Enums$Services.NewActionForCreate : Lcom/roadtrack/onstar/Enums$Services;
    //   2584: invokevirtual setActionId : (Lcom/roadtrack/onstar/Enums$Services;)V
    //   2587: getstatic com/roadtrack/onstar/Enums$Services.NewActionForCreate : Lcom/roadtrack/onstar/Enums$Services;
    //   2590: astore #5
    //   2592: aload_0
    //   2593: putstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   2596: new com/roadtrack/onstar/BO/FindMePointsHandler
    //   2599: dup
    //   2600: invokespecial <init> : ()V
    //   2603: astore #4
    //   2605: aload #7
    //   2607: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   2610: ldc ''
    //   2612: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2615: ifne -> 2638
    //   2618: aload #4
    //   2620: aload #7
    //   2622: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   2625: aload #7
    //   2627: invokevirtual getLatitude : ()Ljava/lang/String;
    //   2630: aload #7
    //   2632: invokevirtual getLongitude : ()Ljava/lang/String;
    //   2635: invokevirtual insertFindmePoint : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2638: aload_1
    //   2639: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2642: ldc_w 'url'
    //   2645: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2648: ifnull -> 2665
    //   2651: aload_1
    //   2652: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   2655: ldc_w 'url'
    //   2658: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   2661: astore_1
    //   2662: goto -> 2668
    //   2665: aload #6
    //   2667: astore_1
    //   2668: aload_0
    //   2669: aload #7
    //   2671: aload #5
    //   2673: iload_2
    //   2674: aload #9
    //   2676: aload #8
    //   2678: aload_1
    //   2679: invokestatic sendGCMIntent : (Landroid/content/Context;Lcom/roadtrack/onstar/VO/PushNotificationsVO;Lcom/roadtrack/onstar/Enums$Services;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2682: return
  }
  
  private static Enums.responcesGCM parseResponcesGCM(int paramInt) {
    Enums.responcesGCM responcesGCM1;
    Enums.responcesGCM responcesGCM2 = Enums.responcesGCM.none_acc;
    Enums.responcesGCM[] arrayOfResponcesGCM = Enums.responcesGCM.values();
    int i = arrayOfResponcesGCM.length;
    byte b = 0;
    while (true) {
      responcesGCM1 = responcesGCM2;
      if (b < i) {
        responcesGCM1 = arrayOfResponcesGCM[b];
        if (responcesGCM1.GetResponcesGCM() == paramInt)
          break; 
        b++;
        continue;
      } 
      break;
    } 
    return responcesGCM1;
  }
  
  private static void sendGCMIntent(Context paramContext, PushNotificationsVO paramPushNotificationsVO, Enums.Services paramServices, int paramInt, String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   4: checkcast com/roadtrack/onstar/onstarApplication
    //   7: astore #4
    //   9: aload_1
    //   10: invokevirtual getMessage : ()Ljava/lang/String;
    //   13: astore #17
    //   15: aload_1
    //   16: invokevirtual getTitle : ()Ljava/lang/String;
    //   19: astore #15
    //   21: aload_1
    //   22: invokevirtual getAcc : ()Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   25: astore #16
    //   27: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   30: invokevirtual booleanValue : ()Z
    //   33: pop
    //   34: new com/roadtrack/onstar/entities/MessageRTMobile
    //   37: astore #14
    //   39: aload #14
    //   41: aload_0
    //   42: invokespecial <init> : (Landroid/content/Context;)V
    //   45: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   48: astore #4
    //   50: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo$4.$SwitchMap$com$roadtrack$onstar$Enums$responcesGCM : [I
    //   53: aload #16
    //   55: invokevirtual ordinal : ()I
    //   58: iaload
    //   59: istore #7
    //   61: ldc ''
    //   63: astore #12
    //   65: aload #12
    //   67: astore #11
    //   69: iload #7
    //   71: tableswitch default -> 112, 1 -> 448, 2 -> 295, 3 -> 277, 4 -> 176, 5 -> 142, 6 -> 129, 7 -> 121
    //   112: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   115: astore #4
    //   117: aload #12
    //   119: astore #11
    //   121: ldc_w 2131165536
    //   124: istore #7
    //   126: goto -> 461
    //   129: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.PN_ACC_SENDVIEW_ACTIONS : Ljava/lang/String;
    //   132: astore #11
    //   134: ldc_w com/roadtrack/onstar/MainActivity
    //   137: astore #4
    //   139: goto -> 121
    //   142: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   145: invokevirtual booleanValue : ()Z
    //   148: ifeq -> 159
    //   151: ldc_w 2131165535
    //   154: istore #7
    //   156: goto -> 164
    //   159: ldc_w 2131165534
    //   162: istore #7
    //   164: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   167: astore #4
    //   169: aload #12
    //   171: astore #11
    //   173: goto -> 461
    //   176: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.PN_ACC_SENDVIEW_ALERTS : Ljava/lang/String;
    //   179: astore #11
    //   181: aload_0
    //   182: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   185: getfield parkingAlertTitle : Ljava/lang/String;
    //   188: ldc_w 2131690233
    //   191: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   194: astore #4
    //   196: aload #15
    //   198: aload #4
    //   200: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   203: ifne -> 247
    //   206: aload #15
    //   208: aload #4
    //   210: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   213: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   216: ifeq -> 222
    //   219: goto -> 247
    //   222: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   225: invokevirtual booleanValue : ()Z
    //   228: ifeq -> 239
    //   231: ldc_w 2131165531
    //   234: istore #7
    //   236: goto -> 269
    //   239: ldc_w 2131165530
    //   242: istore #7
    //   244: goto -> 269
    //   247: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   250: invokevirtual booleanValue : ()Z
    //   253: ifeq -> 264
    //   256: ldc_w 2131165529
    //   259: istore #7
    //   261: goto -> 269
    //   264: ldc_w 2131165528
    //   267: istore #7
    //   269: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   272: astore #4
    //   274: goto -> 173
    //   277: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.PN_ACC_SENDVIEW_FOLLOWME : Ljava/lang/String;
    //   280: astore #11
    //   282: ldc_w com/roadtrack/onstar/googleMaps/ActivityMapViewerG
    //   285: astore #4
    //   287: ldc_w 2131165676
    //   290: istore #7
    //   292: goto -> 461
    //   295: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.PN_ACC_SENDVIEW_ACTIONS : Ljava/lang/String;
    //   298: astore #12
    //   300: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   303: astore #13
    //   305: aload_1
    //   306: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   309: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   312: ldc_w '58'
    //   315: invokevirtual equals : (Ljava/lang/Object;)Z
    //   318: ifeq -> 337
    //   321: ldc_w 2131165678
    //   324: istore #7
    //   326: aload #13
    //   328: astore #4
    //   330: aload #12
    //   332: astore #11
    //   334: goto -> 461
    //   337: aload_1
    //   338: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   341: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   344: ldc_w '56'
    //   347: invokevirtual equals : (Ljava/lang/Object;)Z
    //   350: istore #9
    //   352: ldc_w 2131165524
    //   355: istore #7
    //   357: iload #9
    //   359: ifeq -> 390
    //   362: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   365: invokevirtual booleanValue : ()Z
    //   368: ifeq -> 374
    //   371: goto -> 379
    //   374: ldc_w 2131165523
    //   377: istore #7
    //   379: aload #13
    //   381: astore #4
    //   383: aload #12
    //   385: astore #11
    //   387: goto -> 461
    //   390: aload_1
    //   391: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   394: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   397: getstatic com/roadtrack/onstar/Enums$Services.Horn : Lcom/roadtrack/onstar/Enums$Services;
    //   400: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   403: invokevirtual equals : (Ljava/lang/Object;)Z
    //   406: ifne -> 436
    //   409: aload #13
    //   411: astore #4
    //   413: aload #12
    //   415: astore #11
    //   417: aload_1
    //   418: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   421: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   424: getstatic com/roadtrack/onstar/Enums$Services.HornF1 : Lcom/roadtrack/onstar/Enums$Services;
    //   427: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   430: invokevirtual equals : (Ljava/lang/Object;)Z
    //   433: ifeq -> 121
    //   436: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   439: invokevirtual booleanValue : ()Z
    //   442: ifeq -> 374
    //   445: goto -> 379
    //   448: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.PN_ACC_SENDVIEW_REGIONS : Ljava/lang/String;
    //   451: astore #11
    //   453: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   456: astore #4
    //   458: goto -> 121
    //   461: getstatic com/roadtrack/onstar/BO/GlobalMembers.ToExitApp : Z
    //   464: ifeq -> 472
    //   467: ldc_w com/roadtrack/onstar/ui/login/LoginActivity
    //   470: astore #4
    //   472: new android/content/Intent
    //   475: astore #12
    //   477: aload #12
    //   479: aload_0
    //   480: aload #4
    //   482: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   485: aload #12
    //   487: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.ALERT_PUSHNOTIFICATION : Ljava/lang/String;
    //   490: aload #11
    //   492: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   495: pop
    //   496: aload #12
    //   498: ldc_w 'TIME'
    //   501: aload #5
    //   503: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   506: pop
    //   507: aload #16
    //   509: getstatic com/roadtrack/onstar/Enums$responcesGCM.speed_parking_acc : Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   512: invokevirtual equals : (Ljava/lang/Object;)Z
    //   515: ifeq -> 525
    //   518: aload #12
    //   520: astore #4
    //   522: goto -> 784
    //   525: aload #16
    //   527: getstatic com/roadtrack/onstar/Enums$responcesGCM.follow_acc : Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   530: invokevirtual equals : (Ljava/lang/Object;)Z
    //   533: istore #9
    //   535: iload #9
    //   537: ifeq -> 583
    //   540: new android/content/Intent
    //   543: astore #4
    //   545: aload #4
    //   547: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   550: ldc_w com/roadtrack/onstar/googleMaps/ActivityMapViewerG
    //   553: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   556: aload #4
    //   558: ldc_w 'extra'
    //   561: aload_0
    //   562: aload_1
    //   563: invokestatic buildMixedBubbleExtra : (Landroid/content/Context;Lcom/roadtrack/onstar/VO/PushNotificationsVO;)Landroid/os/Bundle;
    //   566: invokevirtual putExtra : (Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/Intent;
    //   569: pop
    //   570: aload #4
    //   572: ldc_w 'openFragmentMap'
    //   575: iconst_1
    //   576: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   579: pop
    //   580: goto -> 784
    //   583: aload #12
    //   585: astore #4
    //   587: aload_1
    //   588: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   591: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   594: if_acmpne -> 784
    //   597: aload_1
    //   598: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   601: astore #4
    //   603: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   606: invokevirtual GetCode : ()I
    //   609: istore #8
    //   611: getstatic com/roadtrack/onstar/BO/GlobalMembers.lastNavParamsFindMe : Ljava/lang/String;
    //   614: astore #11
    //   616: new android/os/Bundle
    //   619: astore #12
    //   621: aload #12
    //   623: invokespecial <init> : ()V
    //   626: new android/content/Intent
    //   629: astore #5
    //   631: aload #5
    //   633: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   636: ldc_w com/roadtrack/onstar/googleMaps/ActivityMapViewerG
    //   639: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   642: aload #12
    //   644: ldc 'source'
    //   646: getstatic com/roadtrack/onstar/Enums$navigationProcess.External : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   649: invokevirtual putSerializable : (Ljava/lang/String;Ljava/io/Serializable;)V
    //   652: aload #12
    //   654: ldc_w 'navdevice_id'
    //   657: aload #4
    //   659: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   662: aload #12
    //   664: ldc 'navaction'
    //   666: iload #8
    //   668: invokestatic valueOf : (I)Ljava/lang/String;
    //   671: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   674: aload #12
    //   676: ldc 'navextra_params'
    //   678: aload #11
    //   680: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   683: aload #11
    //   685: ifnull -> 700
    //   688: aload #11
    //   690: astore #4
    //   692: aload #11
    //   694: invokevirtual isEmpty : ()Z
    //   697: ifeq -> 705
    //   700: ldc_w 'OK'
    //   703: astore #4
    //   705: aload #12
    //   707: ldc 'GPSSTATUS'
    //   709: aload #4
    //   711: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   714: aload #12
    //   716: ldc 'externallatitude'
    //   718: aload_1
    //   719: invokevirtual getLatitude : ()Ljava/lang/String;
    //   722: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   725: aload #12
    //   727: ldc 'externallongitude'
    //   729: aload_1
    //   730: invokevirtual getLongitude : ()Ljava/lang/String;
    //   733: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   736: aload #12
    //   738: ldc 'LASTKNOWDATE'
    //   740: aload_1
    //   741: invokevirtual getTime : ()Ljava/lang/String;
    //   744: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   747: aload #5
    //   749: ldc_w 'extra'
    //   752: aload #12
    //   754: invokevirtual putExtra : (Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/Intent;
    //   757: pop
    //   758: aload #5
    //   760: ldc_w 'PUSH_NOTIFICATION_DEVICE_ID'
    //   763: getstatic com/roadtrack/onstar/servicios/ServiceGCMOreo.deviceID : Ljava/lang/String;
    //   766: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   769: pop
    //   770: aload #5
    //   772: ldc_w 'openFragmentMap'
    //   775: iconst_1
    //   776: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   779: pop
    //   780: aload #5
    //   782: astore #4
    //   784: ldc_w 'EnebledPush'
    //   787: iconst_1
    //   788: aload_0
    //   789: invokestatic GetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   792: istore #10
    //   794: aload_1
    //   795: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   798: astore #5
    //   800: aload_0
    //   801: invokestatic isActivityRunning : (Landroid/content/Context;)Z
    //   804: istore #9
    //   806: aload_0
    //   807: invokestatic isActivityRunning : (Landroid/content/Context;)Z
    //   810: ifne -> 827
    //   813: new android/content/Intent
    //   816: astore #4
    //   818: aload #4
    //   820: aload_0
    //   821: ldc_w com/roadtrack/onstar/ui/login/LoginActivity
    //   824: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   827: iload #10
    //   829: ifeq -> 970
    //   832: aload #16
    //   834: invokevirtual GetResponcesGCM : ()I
    //   837: getstatic com/roadtrack/onstar/Enums$Services.FindMeNotification : Lcom/roadtrack/onstar/Enums$Services;
    //   840: invokevirtual GetCode : ()I
    //   843: if_icmpeq -> 921
    //   846: aload #16
    //   848: invokevirtual GetResponcesGCM : ()I
    //   851: getstatic com/roadtrack/onstar/Enums$Services.DTCNotification : Lcom/roadtrack/onstar/Enums$Services;
    //   854: invokevirtual GetCode : ()I
    //   857: if_icmpeq -> 921
    //   860: aload #16
    //   862: invokevirtual GetResponcesGCM : ()I
    //   865: getstatic com/roadtrack/onstar/Enums$Services.EmergencyNotification : Lcom/roadtrack/onstar/Enums$Services;
    //   868: invokevirtual GetCode : ()I
    //   871: if_icmpne -> 877
    //   874: goto -> 921
    //   877: aload #14
    //   879: aload_0
    //   880: aload #4
    //   882: aload #15
    //   884: aload #17
    //   886: getstatic com/roadtrack/onstar/Enums$NotificationRTMobile.comandos : Lcom/roadtrack/onstar/Enums$NotificationRTMobile;
    //   889: iload #7
    //   891: aload_2
    //   892: aload #6
    //   894: iload_3
    //   895: aload #5
    //   897: aload_1
    //   898: invokevirtual getTime : ()Ljava/lang/String;
    //   901: aload_1
    //   902: invokevirtual getAlertId : ()Ljava/lang/String;
    //   905: aload_1
    //   906: invokevirtual getLatitude : ()Ljava/lang/String;
    //   909: aload_1
    //   910: invokevirtual getLongitude : ()Ljava/lang/String;
    //   913: iload #9
    //   915: invokevirtual NotificationAlert : (Landroid/content/Context;Landroid/content/Intent;Ljava/lang/String;Ljava/lang/String;Lcom/roadtrack/onstar/Enums$NotificationRTMobile;ILcom/roadtrack/onstar/Enums$Services;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   918: goto -> 970
    //   921: aload #14
    //   923: aload_0
    //   924: aload #4
    //   926: aload #15
    //   928: aload #17
    //   930: getstatic com/roadtrack/onstar/Enums$NotificationRTMobile.notification_event : Lcom/roadtrack/onstar/Enums$NotificationRTMobile;
    //   933: iload #7
    //   935: aload_2
    //   936: aload #6
    //   938: iload_3
    //   939: aload #5
    //   941: aload_1
    //   942: invokevirtual getTime : ()Ljava/lang/String;
    //   945: aload_1
    //   946: invokevirtual getAlertId : ()Ljava/lang/String;
    //   949: aload_1
    //   950: invokevirtual getLatitude : ()Ljava/lang/String;
    //   953: aload_1
    //   954: invokevirtual getLongitude : ()Ljava/lang/String;
    //   957: iload #9
    //   959: invokevirtual NotificationAlert : (Landroid/content/Context;Landroid/content/Intent;Ljava/lang/String;Ljava/lang/String;Lcom/roadtrack/onstar/Enums$NotificationRTMobile;ILcom/roadtrack/onstar/Enums$Services;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   962: goto -> 970
    //   965: astore_0
    //   966: aload_0
    //   967: invokevirtual printStackTrace : ()V
    //   970: return
    // Exception table:
    //   from	to	target	type
    //   34	45	965	java/lang/Exception
    //   50	61	965	java/lang/Exception
    //   129	134	965	java/lang/Exception
    //   142	151	965	java/lang/Exception
    //   176	219	965	java/lang/Exception
    //   222	231	965	java/lang/Exception
    //   247	256	965	java/lang/Exception
    //   277	282	965	java/lang/Exception
    //   295	300	965	java/lang/Exception
    //   305	321	965	java/lang/Exception
    //   337	352	965	java/lang/Exception
    //   362	371	965	java/lang/Exception
    //   390	409	965	java/lang/Exception
    //   417	436	965	java/lang/Exception
    //   436	445	965	java/lang/Exception
    //   448	453	965	java/lang/Exception
    //   461	467	965	java/lang/Exception
    //   472	518	965	java/lang/Exception
    //   525	535	965	java/lang/Exception
    //   540	580	965	java/lang/Exception
    //   587	683	965	java/lang/Exception
    //   692	700	965	java/lang/Exception
    //   705	780	965	java/lang/Exception
    //   784	806	965	java/lang/Exception
    //   806	827	965	java/lang/Exception
    //   832	874	965	java/lang/Exception
    //   877	918	965	java/lang/Exception
    //   921	962	965	java/lang/Exception
  }
  
  public void onCreate() {
    super.onCreate();
  }
  
  protected void onHandleWork(Intent paramIntent) {
    contex = getApplicationContext();
    onHandleIntent(contex, paramIntent);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/servicios/ServiceGCMOreo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */