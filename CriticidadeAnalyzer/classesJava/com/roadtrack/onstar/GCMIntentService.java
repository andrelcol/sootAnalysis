package com.roadtrack.onstar;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import com.roadtrack.onstar.BO.FindMePointsHandler;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.NotificationRoute;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.DAO.pushManager;
import com.roadtrack.onstar.VO.PushNotificationsVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.entities.MessageRTMobileGenericDialog;
import com.roadtrack.onstar.entities.PushRenewal;
import com.roadtrack.onstar.nav.routing.MapsCommonFragment;
import com.roadtrack.onstar.nav.routing.NavigateCommonDialogActivity;
import com.roadtrack.onstar.objects.PushGoRute;
import com.roadtrack.onstar.servicios.GCMBaseRegisterOreo;
import com.roadtrack.onstar.ui.settings.SettingsNewActivity;
import com.roadtrack.onstar.ui.wizard.WizardActivity;
import com.roadtrack.onstar.utils.GetHexDumpMap;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@SuppressLint({"DefaultLocale"})
public class GCMIntentService extends GCMBaseRegisterOreo {
  public static String ALERT_PUSHNOTIFICATION = "PUSHNOTIFICATION";
  
  public static String PN_ACC_SENDVIEW_ACTIONS = "PN_ACTIONS";
  
  public static String PN_ACC_SENDVIEW_ALERTS = "PN_ALERTS";
  
  public static String PN_ACC_SENDVIEW_FOLLOWME = "PN_FOLLOWME";
  
  public static String PN_ACC_SENDVIEW_REGIONS = "PN_REGIONS";
  
  public static boolean isActivityRunning = false;
  
  public static boolean isMapUpdatePush = false;
  
  AudioManager audioManager;
  
  private String deviceID = null;
  
  private onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO = null;
  
  TextToSpeech t1;
  
  public GCMIntentService() {
    super(new String[] { GlobalMembers.Sender_IDGCM });
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  private Bundle buildMixedBubbleExtra(Context paramContext, PushNotificationsVO paramPushNotificationsVO) {
    // Byte code:
    //   0: new java/lang/String
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: pop
    //   8: new java/lang/String
    //   11: dup
    //   12: invokespecial <init> : ()V
    //   15: astore #6
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
    //   40: astore #7
    //   42: new java/util/ArrayList
    //   45: dup
    //   46: invokespecial <init> : ()V
    //   49: putstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   52: aload_2
    //   53: invokevirtual getLatitude : ()Ljava/lang/String;
    //   56: astore #4
    //   58: aload_2
    //   59: invokevirtual getLongitude : ()Ljava/lang/String;
    //   62: astore #5
    //   64: aload_2
    //   65: invokevirtual getAccNumber : ()Ljava/lang/String;
    //   68: astore #8
    //   70: new java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial <init> : ()V
    //   77: astore_3
    //   78: aload_3
    //   79: ldc ''
    //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: aload_3
    //   86: aload_2
    //   87: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   90: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload_3
    //   95: invokevirtual toString : ()Ljava/lang/String;
    //   98: astore #11
    //   100: aload_2
    //   101: invokevirtual getTitle : ()Ljava/lang/String;
    //   104: astore #9
    //   106: aload_2
    //   107: invokevirtual getGPSStatus : ()Ljava/lang/String;
    //   110: astore #10
    //   112: aload_2
    //   113: invokevirtual getTime : ()Ljava/lang/String;
    //   116: astore #12
    //   118: new java/text/SimpleDateFormat
    //   121: dup
    //   122: ldc 'yyyy-dd-MM HH:mm:ss'
    //   124: invokestatic getDefault : ()Ljava/util/Locale;
    //   127: invokespecial <init> : (Ljava/lang/String;Ljava/util/Locale;)V
    //   130: astore_2
    //   131: new java/text/SimpleDateFormat
    //   134: dup
    //   135: ldc 'dd-MM-yyyy HH:mm:ss'
    //   137: invokestatic getDefault : ()Ljava/util/Locale;
    //   140: invokespecial <init> : (Ljava/lang/String;Ljava/util/Locale;)V
    //   143: astore_3
    //   144: aload_3
    //   145: aload_2
    //   146: aload #12
    //   148: invokevirtual parse : (Ljava/lang/String;)Ljava/util/Date;
    //   151: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
    //   154: astore_2
    //   155: goto -> 162
    //   158: astore_2
    //   159: ldc '00-00-0000 00:00:00'
    //   161: astore_2
    //   162: aload #4
    //   164: ifnull -> 180
    //   167: aload #4
    //   169: astore_3
    //   170: aload #4
    //   172: ldc ''
    //   174: invokevirtual equals : (Ljava/lang/Object;)Z
    //   177: ifeq -> 183
    //   180: ldc '0.0'
    //   182: astore_3
    //   183: aload #5
    //   185: ifnull -> 202
    //   188: aload #5
    //   190: astore #4
    //   192: aload #5
    //   194: ldc ''
    //   196: invokevirtual equals : (Ljava/lang/Object;)Z
    //   199: ifeq -> 206
    //   202: ldc '0.0'
    //   204: astore #4
    //   206: new com/roadtrack/onstar/VO/PushAlertsVO
    //   209: dup
    //   210: ldc '0'
    //   212: aload #9
    //   214: ldc 'vehicle'
    //   216: aload_2
    //   217: aload_3
    //   218: aload #4
    //   220: aload #11
    //   222: ldc 'nActionId'
    //   224: aload #8
    //   226: ldc 'id'
    //   228: ldc 'insertedRow'
    //   230: iconst_1
    //   231: ldc 'completitionCode'
    //   233: ldc 'requestErrorId'
    //   235: ldc 'responseErrorId'
    //   237: ldc '0'
    //   239: iconst_0
    //   240: aload_2
    //   241: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    //   244: astore #5
    //   246: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   249: aload #5
    //   251: invokevirtual add : (Ljava/lang/Object;)Z
    //   254: pop
    //   255: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   258: invokevirtual size : ()I
    //   261: ifle -> 595
    //   264: aload #10
    //   266: ldc '3'
    //   268: invokevirtual equals : (Ljava/lang/Object;)Z
    //   271: ifeq -> 306
    //   274: aload_1
    //   275: aload_0
    //   276: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   279: getfield previousposition : Ljava/lang/String;
    //   282: ldc 2131690271
    //   284: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   287: pop
    //   288: aload_1
    //   289: aload_0
    //   290: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   293: getfield navigation_lbl_localizame_gpssincobertura_1 : Ljava/lang/String;
    //   296: ldc 2131690158
    //   298: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   301: astore #5
    //   303: goto -> 335
    //   306: aload_1
    //   307: aload_0
    //   308: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   311: getfield currentposition : Ljava/lang/String;
    //   314: ldc 2131689742
    //   316: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   319: pop
    //   320: aload_1
    //   321: aload_0
    //   322: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   325: getfield global_popup_btn_ok_1 : Ljava/lang/String;
    //   328: ldc 2131689950
    //   330: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   333: astore #5
    //   335: aload #8
    //   337: ldc '6'
    //   339: invokevirtual equals : (Ljava/lang/Object;)Z
    //   342: ifeq -> 355
    //   345: aload #9
    //   347: ldc '27'
    //   349: invokevirtual equals : (Ljava/lang/Object;)Z
    //   352: ifne -> 365
    //   355: aload #9
    //   357: ldc '10'
    //   359: invokevirtual equals : (Ljava/lang/Object;)Z
    //   362: ifeq -> 382
    //   365: aload_1
    //   366: aload_0
    //   367: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   370: getfield alert_code_id_follow_me : Ljava/lang/String;
    //   373: ldc 2131689662
    //   375: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   378: astore_1
    //   379: goto -> 506
    //   382: aload #8
    //   384: ldc '6'
    //   386: invokevirtual equals : (Ljava/lang/Object;)Z
    //   389: ifeq -> 419
    //   392: aload #9
    //   394: ldc '15'
    //   396: invokevirtual equals : (Ljava/lang/Object;)Z
    //   399: ifeq -> 419
    //   402: aload_1
    //   403: aload_0
    //   404: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   407: getfield global_lbl_accionalertavel_1 : Ljava/lang/String;
    //   410: ldc 2131689851
    //   412: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   415: astore_1
    //   416: goto -> 506
    //   419: aload #8
    //   421: ldc '6'
    //   423: invokevirtual equals : (Ljava/lang/Object;)Z
    //   426: ifeq -> 439
    //   429: aload #9
    //   431: ldc '57'
    //   433: invokevirtual equals : (Ljava/lang/Object;)Z
    //   436: ifne -> 449
    //   439: aload #9
    //   441: ldc '255'
    //   443: invokevirtual equals : (Ljava/lang/Object;)Z
    //   446: ifeq -> 466
    //   449: aload_1
    //   450: aload_0
    //   451: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   454: getfield global_lbl_accionalertamov_1 : Ljava/lang/String;
    //   457: ldc 2131689845
    //   459: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   462: astore_1
    //   463: goto -> 506
    //   466: aload #8
    //   468: ldc '6'
    //   470: invokevirtual equals : (Ljava/lang/Object;)Z
    //   473: ifeq -> 503
    //   476: aload #9
    //   478: ldc '235'
    //   480: invokevirtual equals : (Ljava/lang/Object;)Z
    //   483: ifeq -> 503
    //   486: aload_1
    //   487: aload_0
    //   488: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   491: getfield global_lbl_accionalertavalet_1 : Ljava/lang/String;
    //   494: ldc 2131689849
    //   496: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   499: astore_1
    //   500: goto -> 506
    //   503: aload #6
    //   505: astore_1
    //   506: getstatic com/roadtrack/onstar/Enums$navigationProcess.External : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   509: astore #6
    //   511: aload #7
    //   513: ldc 'source'
    //   515: aload #6
    //   517: invokevirtual putSerializable : (Ljava/lang/String;Ljava/io/Serializable;)V
    //   520: aload #7
    //   522: ldc 'navextra_params'
    //   524: ldc ''
    //   526: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   529: aload #7
    //   531: ldc_w 'LASTKNOWDATE'
    //   534: aload_2
    //   535: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   538: aload #7
    //   540: ldc_w 'GPSSTATUS'
    //   543: aload #5
    //   545: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   548: aload #7
    //   550: ldc_w 'ALERT_CODE_ID'
    //   553: aload_1
    //   554: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   557: aload #7
    //   559: ldc_w 'navaction'
    //   562: aload #8
    //   564: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   567: aload #7
    //   569: ldc_w 'externallatitude'
    //   572: aload_3
    //   573: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   576: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   579: aload #7
    //   581: ldc_w 'externallongitude'
    //   584: aload #4
    //   586: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   589: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   592: goto -> 595
    //   595: aload #7
    //   597: areturn
    // Exception table:
    //   from	to	target	type
    //   144	155	158	java/lang/Exception
  }
  
  private Enums$responcesGCM parseResponcesGCM(int paramInt) {
    Enums$responcesGCM enums$responcesGCM1;
    Enums$responcesGCM enums$responcesGCM2 = Enums$responcesGCM.none_acc;
    Enums$responcesGCM[] arrayOfEnums$responcesGCM = Enums$responcesGCM.values();
    int i = arrayOfEnums$responcesGCM.length;
    byte b = 0;
    while (true) {
      enums$responcesGCM1 = enums$responcesGCM2;
      if (b < i) {
        enums$responcesGCM1 = arrayOfEnums$responcesGCM[b];
        if (enums$responcesGCM1.GetResponcesGCM() == paramInt)
          break; 
        b++;
        continue;
      } 
      break;
    } 
    return enums$responcesGCM1;
  }
  
  private void sendGCMIntent(Context paramContext, PushNotificationsVO paramPushNotificationsVO, Enums$Services paramEnums$Services, int paramInt, String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   5: checkcast com/roadtrack/onstar/onstarApplication
    //   8: putfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   11: aload_2
    //   12: invokevirtual getMessage : ()Ljava/lang/String;
    //   15: astore #17
    //   17: aload_2
    //   18: invokevirtual getTitle : ()Ljava/lang/String;
    //   21: astore #15
    //   23: aload_2
    //   24: invokevirtual getAcc : ()Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   27: astore #18
    //   29: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   32: invokevirtual booleanValue : ()Z
    //   35: pop
    //   36: new com/roadtrack/onstar/entities/MessageRTMobile
    //   39: astore #16
    //   41: aload #16
    //   43: aload_1
    //   44: invokespecial <init> : (Landroid/content/Context;)V
    //   47: getstatic com/roadtrack/onstar/GCMIntentService$4.$SwitchMap$com$roadtrack$onstar$Enums$responcesGCM : [I
    //   50: aload #18
    //   52: invokevirtual ordinal : ()I
    //   55: iaload
    //   56: istore #8
    //   58: ldc ''
    //   60: astore #12
    //   62: iload #8
    //   64: tableswitch default -> 104, 1 -> 444, 2 -> 275, 3 -> 257, 4 -> 155, 5 -> 125, 6 -> 112
    //   104: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   107: astore #5
    //   109: goto -> 454
    //   112: getstatic com/roadtrack/onstar/GCMIntentService.PN_ACC_SENDVIEW_ACTIONS : Ljava/lang/String;
    //   115: astore #12
    //   117: ldc_w com/roadtrack/onstar/MainActivity
    //   120: astore #5
    //   122: goto -> 454
    //   125: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   128: invokevirtual booleanValue : ()Z
    //   131: ifeq -> 142
    //   134: ldc_w 2131165535
    //   137: istore #8
    //   139: goto -> 147
    //   142: ldc_w 2131165534
    //   145: istore #8
    //   147: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   150: astore #5
    //   152: goto -> 459
    //   155: getstatic com/roadtrack/onstar/GCMIntentService.PN_ACC_SENDVIEW_ALERTS : Ljava/lang/String;
    //   158: astore #12
    //   160: aload_1
    //   161: aload_0
    //   162: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   165: getfield parkingAlertTitle : Ljava/lang/String;
    //   168: ldc_w 2131690233
    //   171: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   174: astore #5
    //   176: aload #15
    //   178: aload #5
    //   180: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   183: ifne -> 227
    //   186: aload #15
    //   188: aload #5
    //   190: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   193: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   196: ifeq -> 202
    //   199: goto -> 227
    //   202: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   205: invokevirtual booleanValue : ()Z
    //   208: ifeq -> 219
    //   211: ldc_w 2131165531
    //   214: istore #8
    //   216: goto -> 249
    //   219: ldc_w 2131165530
    //   222: istore #8
    //   224: goto -> 249
    //   227: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   230: invokevirtual booleanValue : ()Z
    //   233: ifeq -> 244
    //   236: ldc_w 2131165529
    //   239: istore #8
    //   241: goto -> 249
    //   244: ldc_w 2131165528
    //   247: istore #8
    //   249: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   252: astore #5
    //   254: goto -> 152
    //   257: getstatic com/roadtrack/onstar/GCMIntentService.PN_ACC_SENDVIEW_FOLLOWME : Ljava/lang/String;
    //   260: astore #12
    //   262: ldc_w com/roadtrack/onstar/googleMaps/ActivityMapViewerG
    //   265: astore #5
    //   267: ldc_w 2131165676
    //   270: istore #8
    //   272: goto -> 459
    //   275: getstatic com/roadtrack/onstar/GCMIntentService.PN_ACC_SENDVIEW_ACTIONS : Ljava/lang/String;
    //   278: astore #13
    //   280: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   283: astore #14
    //   285: aload_2
    //   286: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   289: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   292: ldc_w '58'
    //   295: invokevirtual equals : (Ljava/lang/Object;)Z
    //   298: ifeq -> 317
    //   301: ldc_w 2131165678
    //   304: istore #8
    //   306: aload #14
    //   308: astore #5
    //   310: aload #13
    //   312: astore #12
    //   314: goto -> 459
    //   317: aload_2
    //   318: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   321: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   324: ldc_w '56'
    //   327: invokevirtual equals : (Ljava/lang/Object;)Z
    //   330: istore #10
    //   332: ldc_w 2131165524
    //   335: istore #8
    //   337: iload #10
    //   339: ifeq -> 378
    //   342: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   345: invokevirtual booleanValue : ()Z
    //   348: ifeq -> 362
    //   351: aload #14
    //   353: astore #5
    //   355: aload #13
    //   357: astore #12
    //   359: goto -> 459
    //   362: ldc_w 2131165523
    //   365: istore #8
    //   367: aload #14
    //   369: astore #5
    //   371: aload #13
    //   373: astore #12
    //   375: goto -> 459
    //   378: aload_2
    //   379: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   382: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   385: getstatic com/roadtrack/onstar/Enums$Services.Horn : Lcom/roadtrack/onstar/Enums$Services;
    //   388: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   391: invokevirtual equals : (Ljava/lang/Object;)Z
    //   394: ifne -> 424
    //   397: aload #14
    //   399: astore #5
    //   401: aload #13
    //   403: astore #12
    //   405: aload_2
    //   406: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   409: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   412: getstatic com/roadtrack/onstar/Enums$Services.HornF1 : Lcom/roadtrack/onstar/Enums$Services;
    //   415: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   418: invokevirtual equals : (Ljava/lang/Object;)Z
    //   421: ifeq -> 454
    //   424: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   427: invokevirtual booleanValue : ()Z
    //   430: ifeq -> 362
    //   433: aload #14
    //   435: astore #5
    //   437: aload #13
    //   439: astore #12
    //   441: goto -> 459
    //   444: getstatic com/roadtrack/onstar/GCMIntentService.PN_ACC_SENDVIEW_REGIONS : Ljava/lang/String;
    //   447: astore #12
    //   449: ldc_w com/roadtrack/onstar/NotificationsActivity
    //   452: astore #5
    //   454: ldc_w 2131165536
    //   457: istore #8
    //   459: getstatic com/roadtrack/onstar/BO/GlobalMembers.ToExitApp : Z
    //   462: ifeq -> 470
    //   465: ldc_w com/roadtrack/onstar/ui/login/LoginActivity
    //   468: astore #5
    //   470: new android/content/Intent
    //   473: astore #13
    //   475: aload #13
    //   477: aload_1
    //   478: aload #5
    //   480: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   483: aload #13
    //   485: getstatic com/roadtrack/onstar/GCMIntentService.ALERT_PUSHNOTIFICATION : Ljava/lang/String;
    //   488: aload #12
    //   490: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   493: pop
    //   494: aload #13
    //   496: ldc_w 'TIME'
    //   499: aload #6
    //   501: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   504: pop
    //   505: aload #18
    //   507: getstatic com/roadtrack/onstar/Enums$responcesGCM.speed_parking_acc : Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   510: invokevirtual equals : (Ljava/lang/Object;)Z
    //   513: ifeq -> 523
    //   516: aload #13
    //   518: astore #5
    //   520: goto -> 798
    //   523: aload #18
    //   525: getstatic com/roadtrack/onstar/Enums$responcesGCM.follow_acc : Lcom/roadtrack/onstar/Enums$responcesGCM;
    //   528: invokevirtual equals : (Ljava/lang/Object;)Z
    //   531: istore #10
    //   533: iload #10
    //   535: ifeq -> 582
    //   538: new android/content/Intent
    //   541: astore #5
    //   543: aload #5
    //   545: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   548: ldc_w com/roadtrack/onstar/googleMaps/ActivityMapViewerG
    //   551: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   554: aload #5
    //   556: ldc_w 'extra'
    //   559: aload_0
    //   560: aload_1
    //   561: aload_2
    //   562: invokespecial buildMixedBubbleExtra : (Landroid/content/Context;Lcom/roadtrack/onstar/VO/PushNotificationsVO;)Landroid/os/Bundle;
    //   565: invokevirtual putExtra : (Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/Intent;
    //   568: pop
    //   569: aload #5
    //   571: ldc_w 'openFragmentMap'
    //   574: iconst_1
    //   575: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   578: pop
    //   579: goto -> 798
    //   582: aload #13
    //   584: astore #5
    //   586: aload_2
    //   587: invokevirtual getActionId : ()Lcom/roadtrack/onstar/Enums$Services;
    //   590: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   593: if_acmpne -> 798
    //   596: aload_0
    //   597: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   600: ldc_w 'GCMintentService'
    //   603: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   606: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   609: astore #5
    //   611: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   614: invokevirtual GetCode : ()I
    //   617: istore #9
    //   619: getstatic com/roadtrack/onstar/BO/GlobalMembers.lastNavParamsFindMe : Ljava/lang/String;
    //   622: astore #12
    //   624: new android/os/Bundle
    //   627: astore #13
    //   629: aload #13
    //   631: invokespecial <init> : ()V
    //   634: new android/content/Intent
    //   637: astore #6
    //   639: aload #6
    //   641: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   644: ldc_w com/roadtrack/onstar/googleMaps/ActivityMapViewerG
    //   647: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   650: aload #13
    //   652: ldc 'source'
    //   654: getstatic com/roadtrack/onstar/Enums$navigationProcess.External : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   657: invokevirtual putSerializable : (Ljava/lang/String;Ljava/io/Serializable;)V
    //   660: aload #13
    //   662: ldc_w 'navdevice_id'
    //   665: aload #5
    //   667: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   670: aload #13
    //   672: ldc_w 'navaction'
    //   675: iload #9
    //   677: invokestatic valueOf : (I)Ljava/lang/String;
    //   680: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   683: aload #13
    //   685: ldc 'navextra_params'
    //   687: aload #12
    //   689: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   692: aload #12
    //   694: ifnull -> 709
    //   697: aload #12
    //   699: astore #5
    //   701: aload #12
    //   703: invokevirtual isEmpty : ()Z
    //   706: ifeq -> 714
    //   709: ldc_w 'OK'
    //   712: astore #5
    //   714: aload #13
    //   716: ldc_w 'GPSSTATUS'
    //   719: aload #5
    //   721: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   724: aload #13
    //   726: ldc_w 'externallatitude'
    //   729: aload_2
    //   730: invokevirtual getLatitude : ()Ljava/lang/String;
    //   733: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   736: aload #13
    //   738: ldc_w 'externallongitude'
    //   741: aload_2
    //   742: invokevirtual getLongitude : ()Ljava/lang/String;
    //   745: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   748: aload #13
    //   750: ldc_w 'LASTKNOWDATE'
    //   753: aload_2
    //   754: invokevirtual getTime : ()Ljava/lang/String;
    //   757: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   760: aload #6
    //   762: ldc_w 'extra'
    //   765: aload #13
    //   767: invokevirtual putExtra : (Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/Intent;
    //   770: pop
    //   771: aload #6
    //   773: ldc_w 'PUSH_NOTIFICATION_DEVICE_ID'
    //   776: aload_0
    //   777: getfield deviceID : Ljava/lang/String;
    //   780: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   783: pop
    //   784: aload #6
    //   786: ldc_w 'openFragmentMap'
    //   789: iconst_1
    //   790: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   793: pop
    //   794: aload #6
    //   796: astore #5
    //   798: ldc_w 'EnebledPush'
    //   801: iconst_1
    //   802: aload_0
    //   803: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   806: invokestatic GetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   809: istore #11
    //   811: aload_2
    //   812: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   815: astore #6
    //   817: aload_0
    //   818: invokevirtual isActivityRunning : ()Z
    //   821: istore #10
    //   823: aload_0
    //   824: invokevirtual isActivityRunning : ()Z
    //   827: ifne -> 844
    //   830: new android/content/Intent
    //   833: astore #5
    //   835: aload #5
    //   837: aload_0
    //   838: ldc_w com/roadtrack/onstar/ui/login/LoginActivity
    //   841: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   844: iload #11
    //   846: ifeq -> 930
    //   849: getstatic com/roadtrack/onstar/Enums$NotificationRTMobile.comandos : Lcom/roadtrack/onstar/Enums$NotificationRTMobile;
    //   852: astore #18
    //   854: aload_2
    //   855: invokevirtual getTime : ()Ljava/lang/String;
    //   858: astore #14
    //   860: aload_2
    //   861: invokevirtual getAlertId : ()Ljava/lang/String;
    //   864: astore #13
    //   866: aload_2
    //   867: invokevirtual getLatitude : ()Ljava/lang/String;
    //   870: astore #12
    //   872: aload_2
    //   873: invokevirtual getLongitude : ()Ljava/lang/String;
    //   876: astore_2
    //   877: aload #16
    //   879: aload_1
    //   880: aload #5
    //   882: aload #15
    //   884: aload #17
    //   886: aload #18
    //   888: iload #8
    //   890: aload_3
    //   891: aload #7
    //   893: iload #4
    //   895: aload #6
    //   897: aload #14
    //   899: aload #13
    //   901: aload #12
    //   903: aload_2
    //   904: iload #10
    //   906: invokevirtual NotificationAlert : (Landroid/content/Context;Landroid/content/Intent;Ljava/lang/String;Ljava/lang/String;Lcom/roadtrack/onstar/Enums$NotificationRTMobile;ILcom/roadtrack/onstar/Enums$Services;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   909: goto -> 930
    //   912: astore_1
    //   913: goto -> 917
    //   916: astore_1
    //   917: ldc_w 'GCMintentService'
    //   920: ldc_w 'Error: sendGCMIntent'
    //   923: aload_1
    //   924: invokevirtual getMessage : ()Ljava/lang/String;
    //   927: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   930: return
    // Exception table:
    //   from	to	target	type
    //   36	58	916	java/lang/Exception
    //   112	117	916	java/lang/Exception
    //   125	134	916	java/lang/Exception
    //   155	199	916	java/lang/Exception
    //   202	211	916	java/lang/Exception
    //   227	236	916	java/lang/Exception
    //   257	262	916	java/lang/Exception
    //   275	280	916	java/lang/Exception
    //   285	301	916	java/lang/Exception
    //   317	332	916	java/lang/Exception
    //   342	351	916	java/lang/Exception
    //   378	397	916	java/lang/Exception
    //   405	424	916	java/lang/Exception
    //   424	433	916	java/lang/Exception
    //   444	449	916	java/lang/Exception
    //   459	465	916	java/lang/Exception
    //   470	516	916	java/lang/Exception
    //   523	533	916	java/lang/Exception
    //   538	579	916	java/lang/Exception
    //   586	692	916	java/lang/Exception
    //   701	709	916	java/lang/Exception
    //   714	794	916	java/lang/Exception
    //   798	823	916	java/lang/Exception
    //   823	844	916	java/lang/Exception
    //   849	877	916	java/lang/Exception
    //   877	909	912	java/lang/Exception
  }
  
  public boolean containsOnstarActivity(ActivityManager.RunningTaskInfo paramRunningTaskInfo) {
    String str = paramRunningTaskInfo.baseActivity.getClassName();
    return (str.equals(MainActivity.class.getCanonicalName()) || str.equals(TbtListView.class.getCanonicalName()) || str.equals(TbtManeuverInfo.class.getCanonicalName()) || str.equals(WizardActivity.class.getCanonicalName()) || str.equals(SettingsNewActivity.class.getCanonicalName()) || str.equals(NotificationsActivity.class.getCanonicalName()) || str.equals(HistoricalTestActivity.class.getCanonicalName()) || str.equals(NavigateCommonDialogActivity.class.getCanonicalName()) || str.equals(MapsCommonFragment.class.getCanonicalName()) || str.equals(HmiMenu.class.getCanonicalName()) || str.equals(TransparentActivity.class.getCanonicalName()) || str.equals(TransparentActivityWithSpinner.class.getCanonicalName()));
  }
  
  public boolean isActivityRunning() {
    List list = ((ActivityManager)getSystemService("activity")).getRunningTasks(2147483647);
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
  
  protected void onError(Context paramContext, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("registrationId: ");
    stringBuilder.append(paramString);
    Utilities.escribeArchivo("GCMIntentService", "GCM Error", stringBuilder.toString());
  }
  
  @SuppressLint({"DefaultLocale"})
  protected void onMessage(Context paramContext, Intent paramIntent) {
    this.rtApp = (onstarApplication)getApplicationContext();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Datos: ");
    stringBuilder.append(paramIntent.getExtras().toString());
    Utilities.escribeArchivo("GCM", "PN", stringBuilder.toString());
    if (GlobalMembers.usingPush) {
      byte b;
      String str1;
      if (paramIntent.getExtras().toString().equalsIgnoreCase("Bundle[{CMD=RST_FULL, from=google.com/iid}]"))
        return; 
      Enums$responcesGCM enums$responcesGCM = Enums$responcesGCM.none_acc;
      final Enums$Services action = Enums$Services.None;
      final String url = new String();
      final PushNotificationsVO push = new PushNotificationsVO();
      pushNotificationsVO.setIdResponse(paramIntent.getExtras().getString("idResponse"));
      if (paramIntent.getExtras().getString("Destination") != null) {
        String str6;
        String str7;
        final String a;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        if (paramIntent.getExtras().getString("Latitude") != null) {
          str5 = paramIntent.getExtras().getString("Latitude");
        } else {
          str5 = "";
        } 
        if (paramIntent.getExtras().getString("Longitude") != null) {
          str6 = paramIntent.getExtras().getString("Longitude");
        } else {
          str6 = "";
        } 
        if (paramIntent.getExtras().getString("NavigationApp") != null) {
          str7 = paramIntent.getExtras().getString("NavigationApp");
        } else {
          str7 = "";
        } 
        if (paramIntent.getExtras().getString("Destination") != null) {
          str9 = paramIntent.getExtras().getString("Destination");
        } else {
          str9 = "";
        } 
        if (paramIntent.getExtras().getString("acc") != null) {
          str8 = paramIntent.getExtras().getString("acc");
        } else {
          str8 = "";
        } 
        if (paramIntent.getExtras().getString("message") != null) {
          str10 = paramIntent.getExtras().getString("message");
        } else {
          str10 = "";
        } 
        if (paramIntent.getExtras().getString("title") != null) {
          str11 = paramIntent.getExtras().getString("title");
        } else {
          str11 = "";
        } 
        if (paramIntent.getExtras().getString("badge") != null) {
          str12 = paramIntent.getExtras().getString("badge");
        } else {
          str12 = "";
        } 
        if (paramIntent.getExtras().getString("sound") != null) {
          str13 = paramIntent.getExtras().getString("sound");
        } else {
          str13 = "";
        } 
        final PushGoRute go = new PushGoRute();
        pushGoRute.setDestination(str9);
        pushGoRute.setLongitud(str6);
        pushGoRute.setLatitud(str5);
        pushGoRute.setNavigationApp(str7);
        pushGoRute.setMessage(str10);
        pushGoRute.setTitle(str11);
        pushGoRute.setBadge(str12);
        pushGoRute.setSound(str13);
        pushGoRute.setAcc(str8);
        this.audioManager = (AudioManager)getSystemService("audio");
        this.audioManager.setMode(3);
        this.audioManager.startBluetoothSco();
        this.audioManager.setBluetoothScoOn(true);
        this.t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
              final GCMIntentService this$0;
              
              public void onInit(int param1Int) {
                if (param1Int != -1)
                  GCMIntentService.this.t1.setLanguage(Locale.ROOT); 
              }
            });
        final String destini = pushGoRute.getDestination();
        AudioManager audioManager = (AudioManager)paramContext.getSystemService("audio");
        audioManager.setMode(3);
        audioManager.setSpeakerphoneOn(true);
        (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
              final GCMIntentService this$0;
              
              final String val$destini;
              
              public void run() {
                TextToSpeech textToSpeech = GCMIntentService.this.t1;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Onstar le ha enviado el destino ");
                stringBuilder.append(destini);
                textToSpeech.speak(stringBuilder.toString(), 1, null);
              }
            }1000L);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
              final GCMIntentService this$0;
              
              final String val$a;
              
              final Enums$Services val$action;
              
              final PushGoRute val$go;
              
              final PushNotificationsVO val$push;
              
              final String val$url;
              
              public void run() {
                GCMIntentService gCMIntentService = GCMIntentService.this;
                gCMIntentService.sendGCMIntent(gCMIntentService.getApplicationContext(), push, action, Integer.valueOf(a).intValue(), "", "", url);
                if (!go.getNavigationApp().equalsIgnoreCase("") && !go.getLongitud().equalsIgnoreCase("") && !go.getLatitud().equalsIgnoreCase(""))
                  (new NotificationRoute(GCMIntentService.this.getApplicationContext())).NotificationRecived(GCMIntentService.this.getApplicationContext(), go); 
              }
            }2000L);
      } else {
        Enums$Services enums$Services1;
        if (paramIntent.getExtras().getString("acc") != null) {
          pushNotificationsVO.setAcc(parseResponcesGCM(Integer.parseInt(paramIntent.getExtras().getString("acc"))));
          pushNotificationsVO.setAccNumber(paramIntent.getExtras().getString("acc"));
          pushNotificationsVO.setMessage(paramIntent.getExtras().getString("message"));
          pushNotificationsVO.setTitle(paramIntent.getExtras().getString("title"));
          pushNotificationsVO.setGPSStatus(paramIntent.getExtras().getString("GPS"));
          pushNotificationsVO.setTime(paramIntent.getExtras().getString("Created"));
        } 
        if (paramIntent.getExtras().getString("Acc") != null && (Integer.parseInt(paramIntent.getExtras().getString("Acc")) == 22 || Integer.parseInt(paramIntent.getExtras().getString("Acc")) == 23)) {
          pushNotificationsVO.setAcc_Dialog(Integer.parseInt(paramIntent.getExtras().getString("Acc")));
          pushNotificationsVO.setIdCampaign_Dialog(paramIntent.getExtras().getString("IdCampaign"));
          pushNotificationsVO.setTitle_Dialog(paramIntent.getExtras().getString("Title"));
          pushNotificationsVO.setShowCheckBox_Dialog(Integer.parseInt(paramIntent.getExtras().getString("ShowCheckBox")));
          pushNotificationsVO.setMessage_Dialog(paramIntent.getExtras().getString("Message"));
          pushNotificationsVO.setTitleButton_Dialog(paramIntent.getExtras().getString("TitleButton"));
          pushNotificationsVO.setUrl_Dialog(paramIntent.getExtras().getString("Url"));
          pushNotificationsVO.setExpirationDate_Dialog(paramIntent.getExtras().getString("ExpirationDate"));
          MessageRTMobileGenericDialog messageRTMobileGenericDialog = new MessageRTMobileGenericDialog(paramContext);
          if (PreferenceRT.GetValuePreference("EnebledPush", true, getApplicationContext()))
            messageRTMobileGenericDialog.NotificationRecived(paramContext, pushNotificationsVO); 
        } 
        if (paramIntent.getExtras().getString("Acc") != null && Integer.parseInt(paramIntent.getExtras().getString("Acc")) == 24) {
          pushNotificationsVO.setAcc_Renewal(Integer.parseInt(paramIntent.getExtras().getString("Acc")));
          pushNotificationsVO.setIdCampaign_Renewal(paramIntent.getExtras().getString("IdCampaign"));
          pushNotificationsVO.setTitle_Renewal(paramIntent.getExtras().getString("Title"));
          pushNotificationsVO.setMessage_Renewal(paramIntent.getExtras().getString("Message"));
          pushNotificationsVO.setShowCheckBox_Renewal(Integer.parseInt(paramIntent.getExtras().getString("Badge")));
          pushNotificationsVO.setTitleButton_Renewal(paramIntent.getExtras().getString("Sound"));
          pushNotificationsVO.setShowCheckBox_Renewal(Integer.parseInt(paramIntent.getExtras().getString("ShowCheckBox")));
          pushNotificationsVO.setTitleButton_Renewal(paramIntent.getExtras().getString("TitleButton"));
          pushNotificationsVO.setUrl_Renewal(paramIntent.getExtras().getString("Url"));
          pushNotificationsVO.setExpirationDate_Renewal(paramIntent.getExtras().getString("ExpirationDate"));
          pushNotificationsVO.setDeviceID_Renewal(Integer.parseInt(paramIntent.getExtras().getString("DeviceId")));
          PushRenewal pushRenewal = new PushRenewal(paramContext, isActivityRunning());
          if (PreferenceRT.GetValuePreference("EnebledPush", true, getApplicationContext()))
            pushRenewal.NotificationRecived(paramContext, pushNotificationsVO); 
        } 
        if (paramIntent.getExtras().getString("actionId") != null) {
          enums$Services1 = Utilities.parseActionType(Integer.parseInt(paramIntent.getExtras().getString("actionId")));
        } else {
          enums$Services1 = enums$Services;
        } 
        enums$Services = enums$Services1;
        if (paramIntent.getExtras().getString("acc") != null) {
          enums$Services = enums$Services1;
          if (Integer.parseInt(paramIntent.getExtras().getString("acc")) == 200) {
            pushNotificationsVO.setAcc_Dialog(Integer.parseInt(paramIntent.getExtras().getString("acc")));
            pushNotificationsVO.setTitle_Dialog(paramIntent.getExtras().getString("Title"));
            pushNotificationsVO.setMessage_Dialog(paramIntent.getExtras().getString("Message"));
            pushNotificationsVO.setUrl_Dialog(paramIntent.getExtras().getString("Url"));
            pushNotificationsVO.setExpirationDate_Dialog(paramIntent.getExtras().getString("ExpirationDate"));
            enums$Services1 = Enums$Services.None;
            PushRenewal pushRenewal = new PushRenewal(paramContext, isActivityRunning());
            enums$Services = enums$Services1;
            if (PreferenceRT.GetValuePreference("EnebledPush", true, getApplicationContext())) {
              pushRenewal.NotificationRecived(paramContext, pushNotificationsVO);
              enums$Services = enums$Services1;
            } 
          } 
        } 
      } 
      pushNotificationsVO.setActionId(enums$Services);
      this.deviceID = paramIntent.getExtras().getString("deviceId");
      if (this.deviceID == null) {
        this.deviceID = paramIntent.getExtras().getString("DeviceId");
        if (this.deviceID == null)
          this.deviceID = ""; 
      } 
      pushNotificationsVO.setDeviceId(this.deviceID);
      String str3 = Utilities.getActualTime();
      pushNotificationsVO.setAlertId(paramIntent.getExtras().getString("alertId"));
      if (paramIntent.getExtras().getString("Latitude") == null || paramIntent.getExtras().getString("Latitude").equals("")) {
        pushNotificationsVO.setLatitude("0.0");
      } else {
        pushNotificationsVO.setLatitude(paramIntent.getExtras().getString("Latitude"));
      } 
      if (paramIntent.getExtras().getString("Longitude") == null || paramIntent.getExtras().getString("Longitude").equals("")) {
        pushNotificationsVO.setLongitude("0.0");
      } else {
        pushNotificationsVO.setLongitude(paramIntent.getExtras().getString("Longitude"));
      } 
      if (paramIntent.getExtras().getString("acc") != null) {
        b = Integer.parseInt(paramIntent.getExtras().getString("acc").trim());
      } else {
        b = 0;
      } 
      String str2 = paramIntent.getExtras().getString("OMId");
      if (b == 12) {
        str1 = str4;
      } else {
        Enums$Services enums$Services1;
        if (b == 10) {
          enums$Services1 = Enums$Services.FindMe;
          str1 = str4;
        } else if (b == 6) {
          DBFunctions dBFunctions = new DBFunctions(paramContext);
          str1 = str4;
          if (enums$Services1.getExtras().getString("alertId") != null) {
            str1 = str4;
            if (enums$Services1.getExtras().getString("alertId").equalsIgnoreCase("-1")) {
              if (GlobalMembers.MAP_UPDATE_ON_PARTS.booleanValue() || GlobalMembers.MAP_UPDATE_VERSION != GlobalMembers.MAP_UPDATE_WITH_PUSH)
                return; 
              isMapUpdatePush = true;
              pushNotificationsVO.setAcc(parseResponcesGCM(b + 1));
              pushNotificationsVO.setNewVersion(enums$Services1.getExtras().getString("NewVersion"));
              pushNotificationsVO.setOldVersion(enums$Services1.getExtras().getString("OldVersion"));
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append(enums$Services1.getExtras().getString("OldVersion"));
              stringBuilder1.append("__");
              stringBuilder1.append(enums$Services1.getExtras().getString("NewVersion"));
              stringBuilder1.append(".zip");
              GlobalMembers.mapFileName = stringBuilder1.toString();
              if (UtilitiesFile.getFileFromStringFile((new GetHexDumpMap()).getMapUpdateFile(GlobalMembers.mapFileName)).exists())
                return; 
              pushNotificationsVO.setFileName(GlobalMembers.mapFileName);
              pushNotificationsVO.setMessage(Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.main_activity_map_update_push_message, 2131690041));
              pushNotificationsVO.setTitle(Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.main_activity_map_update_push_title, 2131690042));
              if (dBFunctions.isMapUpdateEmpty()) {
                dBFunctions.addMapUpdateData(pushNotificationsVO);
                String str = str4;
              } else {
                dBFunctions.updateMapUpdateData(pushNotificationsVO);
                String str = str4;
              } 
            } 
          } 
        } else {
          str1 = str4;
          if (b == 8)
            str1 = enums$Services1.getExtras().getString("URLDTCCodes"); 
        } 
      } 
      GlobalMembers.contexGlobal = paramContext;
      FindMePointsHandler findMePointsHandler = new FindMePointsHandler();
      if (!pushNotificationsVO.getDeviceId().equals(""))
        findMePointsHandler.insertFindmePoint(pushNotificationsVO.getDeviceId(), pushNotificationsVO.getLatitude(), pushNotificationsVO.getLongitude()); 
      sendGCMIntent(paramContext, pushNotificationsVO, enums$Services, b, str2, str3, str1);
    } 
  }
  
  protected void onRegistered(Context paramContext, String paramString) {
    pushManager pushManager = new pushManager(GlobalMembers.globalActivity);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("onMessage() > Token: ");
    stringBuilder.append(paramString);
    Utilities.escribeArchivo("GCMintentService", "GCM", stringBuilder.toString());
    this.rtApp = (onstarApplication)getApplicationContext();
    pushManager.deviceRegister("3434", paramString, this.rtApp.getLocatorUserId(), Utilities.DeviceUuidFactory(GlobalMembers.contexGlobal), String.valueOf(13), "1", PreferenceRT.GetValuePreference(GlobalMembers.deviceName, "", onstarApplication.getContext()), false);
  }
  
  protected void onUnregistered(Context paramContext, String paramString) {
    Utilities.escribeArchivo("GCMIntentService", "GCM", "onUnregistered");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/GCMIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */