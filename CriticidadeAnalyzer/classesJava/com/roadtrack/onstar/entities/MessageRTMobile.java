package com.roadtrack.onstar.entities;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.utils.Utilities;

@SuppressLint({"DefaultLocale"})
public class MessageRTMobile {
  private DBFunctions dbFun;
  
  private NotificationManager notifManager;
  
  private onstarApplication rtApp;
  
  public MessageRTMobile(Context paramContext) {
    this.rtApp = (onstarApplication)paramContext.getApplicationContext();
    this.dbFun = new DBFunctions(paramContext);
    try {
      this.notifManager = (NotificationManager)paramContext.getSystemService("notification");
    } catch (Exception exception) {
      Utilities.escribeArchivo("MessageRTMobile", "Error: MessageRTMobile", exception.getMessage());
    } 
  }
  
  private void NotificationAlert_Inner(Context paramContext, Intent paramIntent, String paramString1, String paramString2, Enums.NotificationRTMobile paramNotificationRTMobile, boolean paramBoolean1, int paramInt1, Enums.Services paramServices, String paramString3, int paramInt2, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, boolean paramBoolean2) {
    // Byte code:
    //   0: iload #10
    //   2: istore #18
    //   4: invokestatic setLanguageAndRegion : ()V
    //   7: iload #7
    //   9: ifne -> 56
    //   12: ldc '%s_%s_%s'
    //   14: iconst_3
    //   15: anewarray java/lang/Object
    //   18: dup
    //   19: iconst_0
    //   20: aload #5
    //   22: invokevirtual toString : ()Ljava/lang/String;
    //   25: iconst_0
    //   26: iconst_4
    //   27: invokevirtual substring : (II)Ljava/lang/String;
    //   30: aastore
    //   31: dup
    //   32: iconst_1
    //   33: ldc 'Ubiko'
    //   35: aastore
    //   36: dup
    //   37: iconst_2
    //   38: ldc 'alert'
    //   40: aastore
    //   41: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   44: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   47: aload_1
    //   48: invokestatic GetIdentResourceImage : (Ljava/lang/String;Landroid/content/Context;)I
    //   51: istore #10
    //   53: goto -> 60
    //   56: iload #7
    //   58: istore #10
    //   60: getstatic com/roadtrack/onstar/entities/MessageRTMobile$1.$SwitchMap$com$roadtrack$onstar$Enums$NotificationRTMobile : [I
    //   63: aload #5
    //   65: invokevirtual ordinal : ()I
    //   68: iaload
    //   69: istore #17
    //   71: ldc 2131165530
    //   73: istore #7
    //   75: iload #17
    //   77: tableswitch default -> 120, 1 -> 2559, 2 -> 2523, 3 -> 2482, 4 -> 398, 5 -> 392, 6 -> 356, 7 -> 127
    //   120: new android/content/Intent
    //   123: astore_2
    //   124: goto -> 2594
    //   127: getstatic com/roadtrack/onstar/entities/MessageRTMobile$1.$SwitchMap$com$roadtrack$onstar$Enums$Services : [I
    //   130: aload #8
    //   132: invokevirtual ordinal : ()I
    //   135: iaload
    //   136: istore #17
    //   138: iload #17
    //   140: iconst_1
    //   141: if_icmpeq -> 324
    //   144: iload #17
    //   146: iconst_2
    //   147: if_icmpeq -> 312
    //   150: iload #17
    //   152: iconst_3
    //   153: if_icmpeq -> 259
    //   156: iload #17
    //   158: bipush #13
    //   160: if_icmpeq -> 199
    //   163: new android/content/Intent
    //   166: astore_2
    //   167: aload_2
    //   168: aload_1
    //   169: ldc com/roadtrack/onstar/NotificationsActivity
    //   171: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   174: aload_2
    //   175: ldc 'EXE_NOTIF_PROCESS'
    //   177: iconst_1
    //   178: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   181: pop
    //   182: aload_2
    //   183: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   185: aload #11
    //   187: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   190: pop
    //   191: sipush #600
    //   194: istore #7
    //   196: goto -> 2621
    //   199: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   202: invokevirtual booleanValue : ()Z
    //   205: ifeq -> 215
    //   208: ldc 2131165529
    //   210: istore #7
    //   212: goto -> 219
    //   215: ldc 2131165528
    //   217: istore #7
    //   219: new android/content/Intent
    //   222: astore_2
    //   223: aload_2
    //   224: aload_1
    //   225: ldc com/roadtrack/onstar/NotificationsActivity
    //   227: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   230: aload_2
    //   231: ldc 'EXE_NOTIF_PROCESS'
    //   233: iconst_1
    //   234: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   237: pop
    //   238: aload_2
    //   239: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   241: aload #11
    //   243: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   246: pop
    //   247: iload #7
    //   249: istore #10
    //   251: sipush #604
    //   254: istore #7
    //   256: goto -> 2621
    //   259: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   262: invokevirtual booleanValue : ()Z
    //   265: ifeq -> 272
    //   268: ldc 2131165531
    //   270: istore #7
    //   272: new android/content/Intent
    //   275: astore_2
    //   276: aload_2
    //   277: aload_1
    //   278: ldc com/roadtrack/onstar/NotificationsActivity
    //   280: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   283: aload_2
    //   284: ldc 'EXE_NOTIF_PROCESS'
    //   286: iconst_1
    //   287: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   290: pop
    //   291: aload_2
    //   292: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   294: aload #11
    //   296: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   299: pop
    //   300: iload #7
    //   302: istore #10
    //   304: sipush #603
    //   307: istore #7
    //   309: goto -> 2621
    //   312: sipush #602
    //   315: istore #7
    //   317: ldc 2131165676
    //   319: istore #10
    //   321: goto -> 2621
    //   324: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   327: invokevirtual booleanValue : ()Z
    //   330: ifeq -> 340
    //   333: ldc 2131165522
    //   335: istore #7
    //   337: goto -> 344
    //   340: ldc 2131165521
    //   342: istore #7
    //   344: iload #7
    //   346: istore #10
    //   348: sipush #601
    //   351: istore #7
    //   353: goto -> 2621
    //   356: new android/content/Intent
    //   359: astore_2
    //   360: aload_2
    //   361: aload_1
    //   362: ldc com/roadtrack/onstar/NotificationsActivity
    //   364: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   367: aload_2
    //   368: ldc 'EXE_NOTIF_PROCESS'
    //   370: iconst_1
    //   371: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   374: pop
    //   375: aload_2
    //   376: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   378: aload #11
    //   380: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   383: pop
    //   384: sipush #500
    //   387: istore #7
    //   389: goto -> 2621
    //   392: iconst_0
    //   393: istore #7
    //   395: goto -> 2195
    //   398: getstatic com/roadtrack/onstar/entities/MessageRTMobile$1.$SwitchMap$com$roadtrack$onstar$Enums$Services : [I
    //   401: aload #8
    //   403: invokevirtual ordinal : ()I
    //   406: iaload
    //   407: tableswitch default -> 496, 1 -> 1196, 2 -> 1184, 3 -> 1128, 4 -> 1128, 5 -> 1128, 6 -> 1088, 7 -> 1032, 8 -> 976, 9 -> 976, 10 -> 920, 11 -> 864, 12 -> 824, 13 -> 768, 14 -> 732, 15 -> 692, 16 -> 652, 17 -> 596, 18 -> 556, 19 -> 504
    //   496: new com/roadtrack/onstar/VO/StringsResourcesVO
    //   499: astore #12
    //   501: goto -> 1224
    //   504: sipush #430
    //   507: istore #7
    //   509: ldc 2131165678
    //   511: istore #10
    //   513: new android/content/Intent
    //   516: astore_2
    //   517: aload_2
    //   518: aload_1
    //   519: ldc com/roadtrack/onstar/activities/GenericWVActivity
    //   521: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   524: aload_2
    //   525: getstatic com/roadtrack/onstar/activities/GenericWVActivity.EXTRA_TYPE_TAG : Ljava/lang/String;
    //   528: iconst_1
    //   529: invokevirtual putExtra : (Ljava/lang/String;I)Landroid/content/Intent;
    //   532: pop
    //   533: aload_2
    //   534: getstatic com/roadtrack/onstar/activities/GenericWVActivity.EXTRA_URL : Ljava/lang/String;
    //   537: aload #9
    //   539: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   542: pop
    //   543: aload_2
    //   544: getstatic com/roadtrack/onstar/activities/GenericWVActivity.EXTRA_DEVICE_ID : Ljava/lang/String;
    //   547: aload #11
    //   549: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   552: pop
    //   553: goto -> 2195
    //   556: sipush #414
    //   559: istore #7
    //   561: ldc 2131165678
    //   563: istore #10
    //   565: new android/content/Intent
    //   568: astore_2
    //   569: aload_2
    //   570: aload_1
    //   571: ldc com/roadtrack/onstar/NotificationsActivity
    //   573: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   576: aload_2
    //   577: ldc 'EXE_NOTIF_PROCESS'
    //   579: iconst_1
    //   580: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   583: pop
    //   584: aload_2
    //   585: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   587: aload #11
    //   589: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   592: pop
    //   593: goto -> 2195
    //   596: sipush #413
    //   599: istore #7
    //   601: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   604: invokevirtual booleanValue : ()Z
    //   607: ifeq -> 617
    //   610: ldc 2131165535
    //   612: istore #10
    //   614: goto -> 621
    //   617: ldc 2131165534
    //   619: istore #10
    //   621: new android/content/Intent
    //   624: astore_2
    //   625: aload_2
    //   626: aload_1
    //   627: ldc com/roadtrack/onstar/NotificationsActivity
    //   629: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   632: aload_2
    //   633: ldc 'EXE_NOTIF_PROCESS'
    //   635: iconst_1
    //   636: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   639: pop
    //   640: aload_2
    //   641: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   643: aload #11
    //   645: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   648: pop
    //   649: goto -> 2195
    //   652: sipush #412
    //   655: istore #7
    //   657: ldc 2131165677
    //   659: istore #10
    //   661: new android/content/Intent
    //   664: astore_2
    //   665: aload_2
    //   666: aload_1
    //   667: ldc com/roadtrack/onstar/NotificationsActivity
    //   669: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   672: aload_2
    //   673: ldc 'EXE_NOTIF_PROCESS'
    //   675: iconst_1
    //   676: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   679: pop
    //   680: aload_2
    //   681: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   683: aload #11
    //   685: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   688: pop
    //   689: goto -> 2195
    //   692: sipush #411
    //   695: istore #7
    //   697: ldc 2131165578
    //   699: istore #10
    //   701: new android/content/Intent
    //   704: astore_2
    //   705: aload_2
    //   706: aload_1
    //   707: ldc com/roadtrack/onstar/NotificationsActivity
    //   709: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   712: aload_2
    //   713: ldc 'EXE_NOTIF_PROCESS'
    //   715: iconst_1
    //   716: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   719: pop
    //   720: aload_2
    //   721: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   723: aload #11
    //   725: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   728: pop
    //   729: goto -> 2195
    //   732: sipush #410
    //   735: istore #7
    //   737: new android/content/Intent
    //   740: astore_2
    //   741: aload_2
    //   742: aload_1
    //   743: ldc com/roadtrack/onstar/NotificationsActivity
    //   745: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   748: aload_2
    //   749: ldc 'EXE_NOTIF_PROCESS'
    //   751: iconst_1
    //   752: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   755: pop
    //   756: aload_2
    //   757: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   759: aload #11
    //   761: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   764: pop
    //   765: goto -> 2195
    //   768: sipush #409
    //   771: istore #7
    //   773: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   776: invokevirtual booleanValue : ()Z
    //   779: ifeq -> 789
    //   782: ldc 2131165529
    //   784: istore #10
    //   786: goto -> 793
    //   789: ldc 2131165528
    //   791: istore #10
    //   793: new android/content/Intent
    //   796: astore_2
    //   797: aload_2
    //   798: aload_1
    //   799: ldc com/roadtrack/onstar/NotificationsActivity
    //   801: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   804: aload_2
    //   805: ldc 'EXE_NOTIF_PROCESS'
    //   807: iconst_1
    //   808: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   811: pop
    //   812: aload_2
    //   813: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   815: aload #11
    //   817: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   820: pop
    //   821: goto -> 2195
    //   824: sipush #408
    //   827: istore #7
    //   829: ldc 2131165675
    //   831: istore #10
    //   833: new android/content/Intent
    //   836: astore_2
    //   837: aload_2
    //   838: aload_1
    //   839: ldc com/roadtrack/onstar/NotificationsActivity
    //   841: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   844: aload_2
    //   845: ldc 'EXE_NOTIF_PROCESS'
    //   847: iconst_1
    //   848: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   851: pop
    //   852: aload_2
    //   853: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   855: aload #11
    //   857: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   860: pop
    //   861: goto -> 2195
    //   864: sipush #407
    //   867: istore #7
    //   869: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   872: invokevirtual booleanValue : ()Z
    //   875: ifeq -> 885
    //   878: ldc 2131165533
    //   880: istore #10
    //   882: goto -> 889
    //   885: ldc 2131165532
    //   887: istore #10
    //   889: new android/content/Intent
    //   892: astore_2
    //   893: aload_2
    //   894: aload_1
    //   895: ldc com/roadtrack/onstar/NotificationsActivity
    //   897: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   900: aload_2
    //   901: ldc 'EXE_NOTIF_PROCESS'
    //   903: iconst_1
    //   904: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   907: pop
    //   908: aload_2
    //   909: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   911: aload #11
    //   913: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   916: pop
    //   917: goto -> 2195
    //   920: sipush #406
    //   923: istore #7
    //   925: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   928: invokevirtual booleanValue : ()Z
    //   931: ifeq -> 941
    //   934: ldc 2131165527
    //   936: istore #10
    //   938: goto -> 945
    //   941: ldc 2131165526
    //   943: istore #10
    //   945: new android/content/Intent
    //   948: astore_2
    //   949: aload_2
    //   950: aload_1
    //   951: ldc com/roadtrack/onstar/NotificationsActivity
    //   953: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   956: aload_2
    //   957: ldc 'EXE_NOTIF_PROCESS'
    //   959: iconst_1
    //   960: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   963: pop
    //   964: aload_2
    //   965: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   967: aload #11
    //   969: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   972: pop
    //   973: goto -> 2195
    //   976: sipush #405
    //   979: istore #7
    //   981: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   984: invokevirtual booleanValue : ()Z
    //   987: ifeq -> 997
    //   990: ldc 2131165524
    //   992: istore #10
    //   994: goto -> 1001
    //   997: ldc 2131165523
    //   999: istore #10
    //   1001: new android/content/Intent
    //   1004: astore_2
    //   1005: aload_2
    //   1006: aload_1
    //   1007: ldc com/roadtrack/onstar/NotificationsActivity
    //   1009: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1012: aload_2
    //   1013: ldc 'EXE_NOTIF_PROCESS'
    //   1015: iconst_1
    //   1016: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1019: pop
    //   1020: aload_2
    //   1021: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1023: aload #11
    //   1025: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1028: pop
    //   1029: goto -> 2195
    //   1032: sipush #404
    //   1035: istore #7
    //   1037: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   1040: invokevirtual booleanValue : ()Z
    //   1043: ifeq -> 1053
    //   1046: ldc 2131165524
    //   1048: istore #10
    //   1050: goto -> 1057
    //   1053: ldc 2131165523
    //   1055: istore #10
    //   1057: new android/content/Intent
    //   1060: astore_2
    //   1061: aload_2
    //   1062: aload_1
    //   1063: ldc com/roadtrack/onstar/NotificationsActivity
    //   1065: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1068: aload_2
    //   1069: ldc 'EXE_NOTIF_PROCESS'
    //   1071: iconst_1
    //   1072: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1075: pop
    //   1076: aload_2
    //   1077: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1079: aload #11
    //   1081: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1084: pop
    //   1085: goto -> 2195
    //   1088: sipush #423
    //   1091: istore #7
    //   1093: ldc 2131165525
    //   1095: istore #10
    //   1097: new android/content/Intent
    //   1100: astore_2
    //   1101: aload_2
    //   1102: aload_1
    //   1103: ldc com/roadtrack/onstar/NotificationsActivity
    //   1105: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1108: aload_2
    //   1109: ldc 'EXE_NOTIF_PROCESS'
    //   1111: iconst_1
    //   1112: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1115: pop
    //   1116: aload_2
    //   1117: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1119: aload #11
    //   1121: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1124: pop
    //   1125: goto -> 2195
    //   1128: sipush #403
    //   1131: istore #7
    //   1133: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   1136: invokevirtual booleanValue : ()Z
    //   1139: ifeq -> 1149
    //   1142: ldc 2131165531
    //   1144: istore #10
    //   1146: goto -> 1153
    //   1149: ldc 2131165530
    //   1151: istore #10
    //   1153: new android/content/Intent
    //   1156: astore_2
    //   1157: aload_2
    //   1158: aload_1
    //   1159: ldc com/roadtrack/onstar/NotificationsActivity
    //   1161: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1164: aload_2
    //   1165: ldc 'EXE_NOTIF_PROCESS'
    //   1167: iconst_1
    //   1168: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1171: pop
    //   1172: aload_2
    //   1173: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1175: aload #11
    //   1177: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1180: pop
    //   1181: goto -> 2195
    //   1184: sipush #402
    //   1187: istore #7
    //   1189: ldc 2131165676
    //   1191: istore #10
    //   1193: goto -> 2195
    //   1196: sipush #401
    //   1199: istore #7
    //   1201: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   1204: invokevirtual booleanValue : ()Z
    //   1207: ifeq -> 1217
    //   1210: ldc 2131165522
    //   1212: istore #10
    //   1214: goto -> 1221
    //   1217: ldc 2131165521
    //   1219: istore #10
    //   1221: goto -> 2195
    //   1224: aload #12
    //   1226: invokespecial <init> : ()V
    //   1229: aload_1
    //   1230: aload #12
    //   1232: getfield parkingAlertTitle : Ljava/lang/String;
    //   1235: ldc 2131690233
    //   1237: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1240: astore #5
    //   1242: aload_1
    //   1243: aload #12
    //   1245: getfield SpeedTitle : Ljava/lang/String;
    //   1248: ldc 2131689543
    //   1250: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1253: astore #9
    //   1255: aload_1
    //   1256: aload #12
    //   1258: getfield shot_valet : Ljava/lang/String;
    //   1261: ldc 2131690370
    //   1263: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1266: astore #8
    //   1268: aload_1
    //   1269: aload #12
    //   1271: getfield global_lbl_accionalertabocina_1 : Ljava/lang/String;
    //   1274: ldc 2131689844
    //   1276: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1279: astore #12
    //   1281: aload_3
    //   1282: aload #5
    //   1284: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   1287: ifeq -> 1346
    //   1290: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   1293: invokevirtual booleanValue : ()Z
    //   1296: ifeq -> 1306
    //   1299: ldc 2131165529
    //   1301: istore #10
    //   1303: goto -> 1310
    //   1306: ldc 2131165528
    //   1308: istore #10
    //   1310: new android/content/Intent
    //   1313: astore_2
    //   1314: aload_2
    //   1315: aload_1
    //   1316: ldc com/roadtrack/onstar/NotificationsActivity
    //   1318: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1321: aload_2
    //   1322: ldc 'EXE_NOTIF_PROCESS'
    //   1324: iconst_1
    //   1325: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1328: pop
    //   1329: aload_2
    //   1330: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1332: aload #11
    //   1334: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1337: pop
    //   1338: sipush #415
    //   1341: istore #7
    //   1343: goto -> 1546
    //   1346: aload_3
    //   1347: aload #9
    //   1349: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   1352: ifeq -> 1411
    //   1355: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   1358: invokevirtual booleanValue : ()Z
    //   1361: ifeq -> 1371
    //   1364: ldc 2131165531
    //   1366: istore #10
    //   1368: goto -> 1375
    //   1371: ldc 2131165530
    //   1373: istore #10
    //   1375: new android/content/Intent
    //   1378: astore_2
    //   1379: aload_2
    //   1380: aload_1
    //   1381: ldc com/roadtrack/onstar/NotificationsActivity
    //   1383: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1386: aload_2
    //   1387: ldc 'EXE_NOTIF_PROCESS'
    //   1389: iconst_1
    //   1390: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1393: pop
    //   1394: aload_2
    //   1395: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1397: aload #11
    //   1399: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1402: pop
    //   1403: sipush #416
    //   1406: istore #7
    //   1408: goto -> 1546
    //   1411: aload_3
    //   1412: aload #8
    //   1414: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   1417: ifeq -> 1476
    //   1420: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   1423: invokevirtual booleanValue : ()Z
    //   1426: ifeq -> 1436
    //   1429: ldc 2131165535
    //   1431: istore #10
    //   1433: goto -> 1440
    //   1436: ldc 2131165534
    //   1438: istore #10
    //   1440: new android/content/Intent
    //   1443: astore_2
    //   1444: aload_2
    //   1445: aload_1
    //   1446: ldc com/roadtrack/onstar/NotificationsActivity
    //   1448: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1451: aload_2
    //   1452: ldc 'EXE_NOTIF_PROCESS'
    //   1454: iconst_1
    //   1455: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1458: pop
    //   1459: aload_2
    //   1460: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1462: aload #11
    //   1464: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1467: pop
    //   1468: sipush #417
    //   1471: istore #7
    //   1473: goto -> 1546
    //   1476: aload_3
    //   1477: aload #12
    //   1479: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   1482: ifeq -> 1541
    //   1485: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   1488: invokevirtual booleanValue : ()Z
    //   1491: ifeq -> 1501
    //   1494: ldc 2131165524
    //   1496: istore #10
    //   1498: goto -> 1505
    //   1501: ldc 2131165523
    //   1503: istore #10
    //   1505: new android/content/Intent
    //   1508: astore_2
    //   1509: aload_2
    //   1510: aload_1
    //   1511: ldc com/roadtrack/onstar/NotificationsActivity
    //   1513: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1516: aload_2
    //   1517: ldc 'EXE_NOTIF_PROCESS'
    //   1519: iconst_1
    //   1520: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1523: pop
    //   1524: aload_2
    //   1525: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1527: aload #11
    //   1529: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1532: pop
    //   1533: sipush #418
    //   1536: istore #7
    //   1538: goto -> 1546
    //   1541: sipush #400
    //   1544: istore #7
    //   1546: iload #18
    //   1548: bipush #8
    //   1550: if_icmpne -> 1619
    //   1553: ldc 2131165635
    //   1555: istore #10
    //   1557: new android/content/Intent
    //   1560: astore_2
    //   1561: aload_2
    //   1562: aload_1
    //   1563: ldc com/roadtrack/onstar/pid/RemoteDiagnosticActivity
    //   1565: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1568: aload_2
    //   1569: ldc 'EXE_NOTIF_PROCESS'
    //   1571: iconst_1
    //   1572: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1575: pop
    //   1576: new android/os/Bundle
    //   1579: astore #5
    //   1581: aload #5
    //   1583: invokespecial <init> : ()V
    //   1586: aload #5
    //   1588: ldc 'previousClass'
    //   1590: ldc 'MessageRTMobile'
    //   1592: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1595: aload #5
    //   1597: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1599: aload #11
    //   1601: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1604: aload_2
    //   1605: aload #5
    //   1607: invokevirtual putExtras : (Landroid/os/Bundle;)Landroid/content/Intent;
    //   1610: pop
    //   1611: sipush #419
    //   1614: istore #7
    //   1616: goto -> 2195
    //   1619: iload #18
    //   1621: bipush #20
    //   1623: if_icmpne -> 1692
    //   1626: ldc 2131165635
    //   1628: istore #10
    //   1630: new android/content/Intent
    //   1633: astore_2
    //   1634: aload_2
    //   1635: aload_1
    //   1636: ldc com/roadtrack/onstar/pid/RemoteDiagnosticActivity
    //   1638: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1641: aload_2
    //   1642: ldc 'EXE_NOTIF_PROCESS'
    //   1644: iconst_1
    //   1645: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   1648: pop
    //   1649: new android/os/Bundle
    //   1652: astore #5
    //   1654: aload #5
    //   1656: invokespecial <init> : ()V
    //   1659: aload #5
    //   1661: ldc 'previousClass'
    //   1663: ldc 'MessageRTMobile'
    //   1665: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1668: aload #5
    //   1670: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1672: aload #11
    //   1674: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1677: aload_2
    //   1678: aload #5
    //   1680: invokevirtual putExtras : (Landroid/os/Bundle;)Landroid/content/Intent;
    //   1683: pop
    //   1684: sipush #422
    //   1687: istore #7
    //   1689: goto -> 2195
    //   1692: iload #18
    //   1694: bipush #11
    //   1696: if_icmpeq -> 1720
    //   1699: iload #7
    //   1701: istore #17
    //   1703: iload #18
    //   1705: bipush #14
    //   1707: if_icmpne -> 1713
    //   1710: goto -> 1720
    //   1713: iload #17
    //   1715: istore #7
    //   1717: goto -> 2195
    //   1720: iload #18
    //   1722: bipush #11
    //   1724: if_icmpne -> 1735
    //   1727: sipush #420
    //   1730: istore #7
    //   1732: goto -> 1747
    //   1735: iload #18
    //   1737: bipush #14
    //   1739: if_icmpne -> 1747
    //   1742: sipush #421
    //   1745: istore #7
    //   1747: new android/content/Intent
    //   1750: astore_2
    //   1751: aload_2
    //   1752: aload_1
    //   1753: ldc com/roadtrack/onstar/pid/PIDActivity
    //   1755: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   1758: new android/os/Bundle
    //   1761: astore #12
    //   1763: aload #12
    //   1765: invokespecial <init> : ()V
    //   1768: aload #12
    //   1770: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   1772: aload #11
    //   1774: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1777: aload_0
    //   1778: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1781: ldc 'MessageRTMobile'
    //   1783: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   1786: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1789: astore #5
    //   1791: aload_0
    //   1792: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1795: ldc 'MessageRTMobile'
    //   1797: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   1800: invokevirtual getName : ()Ljava/lang/String;
    //   1803: astore #8
    //   1805: aload_0
    //   1806: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1809: aload #5
    //   1811: invokevirtual getLastPid : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/PIDVO;
    //   1814: astore #9
    //   1816: aload #9
    //   1818: ifnull -> 2172
    //   1821: aload #9
    //   1823: invokevirtual isValidPID : ()Z
    //   1826: ifeq -> 2172
    //   1829: aload #9
    //   1831: aload #5
    //   1833: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   1836: invokevirtual intValue : ()I
    //   1839: invokevirtual setDeviceId : (I)V
    //   1842: aload_2
    //   1843: ldc 'odometer'
    //   1845: aload #9
    //   1847: invokevirtual getOdometer : ()Ljava/lang/String;
    //   1850: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   1853: pop
    //   1854: aload #12
    //   1856: ldc_w 'gasoline'
    //   1859: aload #9
    //   1861: invokevirtual getGas : ()Ljava/lang/String;
    //   1864: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1867: aload #12
    //   1869: ldc_w 'oilValue'
    //   1872: aload #9
    //   1874: invokevirtual getOilValue : ()Ljava/lang/String;
    //   1877: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1880: aload #12
    //   1882: ldc_w 'oilStatus'
    //   1885: aload #9
    //   1887: invokevirtual getOilStatus : ()Ljava/lang/String;
    //   1890: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1893: aload #12
    //   1895: ldc_w 'battery'
    //   1898: aload #9
    //   1900: invokevirtual getBatterylevel : ()Ljava/lang/String;
    //   1903: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1906: aload #12
    //   1908: ldc_w 'tireFL'
    //   1911: aload #9
    //   1913: invokevirtual getTire_fl : ()Ljava/lang/String;
    //   1916: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1919: aload #12
    //   1921: ldc_w 'tireStatusFL'
    //   1924: aload #9
    //   1926: invokevirtual getTire_status_fl : ()Ljava/lang/String;
    //   1929: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1932: aload #12
    //   1934: ldc_w 'tireFR'
    //   1937: aload #9
    //   1939: invokevirtual getTire_fr : ()Ljava/lang/String;
    //   1942: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1945: aload #12
    //   1947: ldc_w 'tireStatusFR'
    //   1950: aload #9
    //   1952: invokevirtual getTire_status_fr : ()Ljava/lang/String;
    //   1955: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1958: aload #12
    //   1960: ldc_w 'tireRL'
    //   1963: aload #9
    //   1965: invokevirtual getTire_rl : ()Ljava/lang/String;
    //   1968: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1971: aload #12
    //   1973: ldc_w 'tireStatusRL'
    //   1976: aload #9
    //   1978: invokevirtual getTire_status_rl : ()Ljava/lang/String;
    //   1981: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1984: aload #12
    //   1986: ldc_w 'tireRR'
    //   1989: aload #9
    //   1991: invokevirtual getTire_rr : ()Ljava/lang/String;
    //   1994: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   1997: aload #12
    //   1999: ldc_w 'tireStatusRR'
    //   2002: aload #9
    //   2004: invokevirtual getTire_status_rr : ()Ljava/lang/String;
    //   2007: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2010: aload #12
    //   2012: ldc_w 'eventDate'
    //   2015: aload #9
    //   2017: invokevirtual getEventDate : ()Ljava/lang/String;
    //   2020: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2023: aload #12
    //   2025: ldc_w 'modelo'
    //   2028: aload #9
    //   2030: invokevirtual getModelo : ()Ljava/lang/String;
    //   2033: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2036: aload #12
    //   2038: ldc_w 'version'
    //   2041: aload #9
    //   2043: invokevirtual getVersion : ()Ljava/lang/String;
    //   2046: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2049: aload #12
    //   2051: ldc_w 'year'
    //   2054: aload #9
    //   2056: invokevirtual getYear : ()Ljava/lang/String;
    //   2059: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2062: aload #12
    //   2064: ldc_w 'placa'
    //   2067: aload #9
    //   2069: invokevirtual getPlaca : ()Ljava/lang/String;
    //   2072: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2075: aload #12
    //   2077: ldc_w 'tpms'
    //   2080: aload #9
    //   2082: invokevirtual getTPMSText : ()Ljava/lang/String;
    //   2085: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2088: aload #12
    //   2090: ldc_w 'DeviceId'
    //   2093: aload #5
    //   2095: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2098: aload #12
    //   2100: ldc_w 'DeviceName'
    //   2103: aload #8
    //   2105: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2108: aload #12
    //   2110: ldc_w 'isM300'
    //   2113: aload #9
    //   2115: invokevirtual getIsM300 : ()Ljava/lang/String;
    //   2118: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2121: aload #12
    //   2123: ldc_w 'autonomy_km'
    //   2126: aload #9
    //   2128: invokevirtual getAutonomy_km : ()Ljava/lang/String;
    //   2131: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2134: aload #12
    //   2136: ldc_w 'autonomy_status'
    //   2139: aload #9
    //   2141: invokevirtual getAutonomy_status : ()Ljava/lang/String;
    //   2144: invokevirtual getString : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   2147: pop
    //   2148: aload #12
    //   2150: ldc_w 'autonomy_text'
    //   2153: aload #9
    //   2155: invokevirtual getAutonomy_text : ()Ljava/lang/String;
    //   2158: invokevirtual getString : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   2161: pop
    //   2162: aload_2
    //   2163: aload #12
    //   2165: invokevirtual putExtras : (Landroid/os/Bundle;)Landroid/content/Intent;
    //   2168: pop
    //   2169: goto -> 2183
    //   2172: new android/content/Intent
    //   2175: dup
    //   2176: aload_1
    //   2177: ldc com/roadtrack/onstar/pid/PIDActivity
    //   2179: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2182: astore_2
    //   2183: ldc_w 2131165634
    //   2186: istore #10
    //   2188: iload #7
    //   2190: istore #17
    //   2192: goto -> 1713
    //   2195: iload #18
    //   2197: tableswitch default -> 2224, 201 -> 2405, 202 -> 2308, 203 -> 2227
    //   2224: goto -> 2479
    //   2227: getstatic com/roadtrack/onstar/BO/GlobalMembers.ToExitApp : Z
    //   2230: ifeq -> 2271
    //   2233: new android/content/Intent
    //   2236: astore_2
    //   2237: aload_2
    //   2238: aload_1
    //   2239: ldc_w com/roadtrack/onstar/ui/login/LoginActivity
    //   2242: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2245: aload_2
    //   2246: ldc 'EXE_NOTIF_PROCESS'
    //   2248: iconst_1
    //   2249: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2252: pop
    //   2253: aload_2
    //   2254: ldc_w 'Acc'
    //   2257: iload #18
    //   2259: invokevirtual putExtra : (Ljava/lang/String;I)Landroid/content/Intent;
    //   2262: pop
    //   2263: sipush #203
    //   2266: istore #7
    //   2268: goto -> 2479
    //   2271: new android/content/Intent
    //   2274: astore_2
    //   2275: aload_2
    //   2276: aload_1
    //   2277: ldc_w com/roadtrack/onstar/MainActivity
    //   2280: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2283: aload_2
    //   2284: ldc 'EXE_NOTIF_PROCESS'
    //   2286: iconst_1
    //   2287: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2290: pop
    //   2291: aload_2
    //   2292: ldc_w 'Acc'
    //   2295: iload #18
    //   2297: invokevirtual putExtra : (Ljava/lang/String;I)Landroid/content/Intent;
    //   2300: pop
    //   2301: iload #18
    //   2303: istore #7
    //   2305: goto -> 2479
    //   2308: getstatic com/roadtrack/onstar/BO/GlobalMembers.ToExitApp : Z
    //   2311: ifeq -> 2358
    //   2314: getstatic com/roadtrack/onstar/BO/GlobalMembers.userLogged : Ljava/lang/String;
    //   2317: ifnonnull -> 2358
    //   2320: new android/content/Intent
    //   2323: astore_2
    //   2324: aload_2
    //   2325: aload_1
    //   2326: ldc_w com/roadtrack/onstar/ui/login/LoginActivity
    //   2329: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2332: aload_2
    //   2333: ldc 'EXE_NOTIF_PROCESS'
    //   2335: iconst_1
    //   2336: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2339: pop
    //   2340: aload_2
    //   2341: ldc_w 'Acc'
    //   2344: iload #18
    //   2346: invokevirtual putExtra : (Ljava/lang/String;I)Landroid/content/Intent;
    //   2349: pop
    //   2350: sipush #202
    //   2353: istore #7
    //   2355: goto -> 2479
    //   2358: new android/content/Intent
    //   2361: astore_2
    //   2362: aload_2
    //   2363: aload_1
    //   2364: ldc_w com/roadtrack/onstar/MainActivity
    //   2367: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2370: aload_2
    //   2371: ldc 'EXE_NOTIF_PROCESS'
    //   2373: iconst_1
    //   2374: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2377: pop
    //   2378: aload_2
    //   2379: ldc_w 'Acc'
    //   2382: iload #18
    //   2384: invokevirtual putExtra : (Ljava/lang/String;I)Landroid/content/Intent;
    //   2387: pop
    //   2388: aload_2
    //   2389: ldc_w 'DeviceId'
    //   2392: aload #11
    //   2394: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   2397: pop
    //   2398: iload #18
    //   2400: istore #7
    //   2402: goto -> 2479
    //   2405: getstatic com/roadtrack/onstar/BO/GlobalMembers.ToExitApp : Z
    //   2408: ifeq -> 2444
    //   2411: new android/content/Intent
    //   2414: astore_2
    //   2415: aload_2
    //   2416: aload_1
    //   2417: ldc_w com/roadtrack/onstar/ui/login/LoginActivity
    //   2420: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2423: aload_2
    //   2424: ldc 'EXE_NOTIF_PROCESS'
    //   2426: iconst_1
    //   2427: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2430: pop
    //   2431: aload_2
    //   2432: ldc_w 'Acc'
    //   2435: iload #18
    //   2437: invokevirtual putExtra : (Ljava/lang/String;I)Landroid/content/Intent;
    //   2440: pop
    //   2441: goto -> 2474
    //   2444: new android/content/Intent
    //   2447: astore_2
    //   2448: aload_2
    //   2449: aload_1
    //   2450: ldc_w com/roadtrack/onstar/MainActivity
    //   2453: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2456: aload_2
    //   2457: ldc 'EXE_NOTIF_PROCESS'
    //   2459: iconst_1
    //   2460: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2463: pop
    //   2464: aload_2
    //   2465: ldc_w 'Acc'
    //   2468: iload #18
    //   2470: invokevirtual putExtra : (Ljava/lang/String;I)Landroid/content/Intent;
    //   2473: pop
    //   2474: sipush #201
    //   2477: istore #7
    //   2479: goto -> 2621
    //   2482: new android/content/Intent
    //   2485: astore_2
    //   2486: aload_2
    //   2487: aload_1
    //   2488: ldc com/roadtrack/onstar/NotificationsActivity
    //   2490: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2493: aload_2
    //   2494: ldc 'EXE_NOTIF_PROCESS'
    //   2496: iconst_1
    //   2497: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2500: pop
    //   2501: aload_2
    //   2502: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   2504: aload #11
    //   2506: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   2509: pop
    //   2510: sipush #300
    //   2513: istore #7
    //   2515: ldc_w 2131165295
    //   2518: istore #10
    //   2520: goto -> 2621
    //   2523: new android/content/Intent
    //   2526: astore_2
    //   2527: aload_2
    //   2528: aload_1
    //   2529: ldc com/roadtrack/onstar/NotificationsActivity
    //   2531: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2534: aload_2
    //   2535: ldc 'EXE_NOTIF_PROCESS'
    //   2537: iconst_1
    //   2538: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2541: pop
    //   2542: aload_2
    //   2543: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   2545: aload #11
    //   2547: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   2550: pop
    //   2551: sipush #200
    //   2554: istore #7
    //   2556: goto -> 2621
    //   2559: new android/content/Intent
    //   2562: astore_2
    //   2563: aload_2
    //   2564: aload_1
    //   2565: ldc com/roadtrack/onstar/NotificationsActivity
    //   2567: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2570: aload_2
    //   2571: ldc 'EXE_NOTIF_PROCESS'
    //   2573: iconst_1
    //   2574: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2577: pop
    //   2578: aload_2
    //   2579: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   2581: aload #11
    //   2583: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   2586: pop
    //   2587: bipush #100
    //   2589: istore #7
    //   2591: goto -> 2621
    //   2594: aload_2
    //   2595: aload_1
    //   2596: ldc com/roadtrack/onstar/NotificationsActivity
    //   2598: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2601: aload_2
    //   2602: ldc 'EXE_NOTIF_PROCESS'
    //   2604: iconst_1
    //   2605: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
    //   2608: pop
    //   2609: aload_2
    //   2610: ldc 'PUSH_NOTIFICATION_DEVICE_ID'
    //   2612: aload #11
    //   2614: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   2617: pop
    //   2618: iconst_0
    //   2619: istore #7
    //   2621: iload #16
    //   2623: ifne -> 2709
    //   2626: new android/os/Bundle
    //   2629: astore #8
    //   2631: aload #8
    //   2633: invokespecial <init> : ()V
    //   2636: aload #8
    //   2638: ldc_w 'cleanNotifications'
    //   2641: iconst_1
    //   2642: invokevirtual putBoolean : (Ljava/lang/String;Z)V
    //   2645: aload #8
    //   2647: ldc_w 'account'
    //   2650: aload_1
    //   2651: ldc_w 'lloged'
    //   2654: iconst_0
    //   2655: invokevirtual getSharedPreferences : (Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   2658: ldc_w 'account'
    //   2661: ldc_w 'sLUM6vZor6R7bS7n4Vw4rw=='
    //   2664: invokeinterface getString : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   2669: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   2672: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   2675: aload #8
    //   2677: ldc_w 'oldIntent'
    //   2680: aload_2
    //   2681: invokevirtual putParcelable : (Ljava/lang/String;Landroid/os/Parcelable;)V
    //   2684: new android/content/Intent
    //   2687: astore #5
    //   2689: aload #5
    //   2691: aload_1
    //   2692: ldc_w com/roadtrack/onstar/PushManagerActivity
    //   2695: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   2698: aload #5
    //   2700: aload #8
    //   2702: invokevirtual putExtras : (Landroid/os/Bundle;)Landroid/content/Intent;
    //   2705: pop
    //   2706: goto -> 2799
    //   2709: iconst_0
    //   2710: istore #18
    //   2712: iload #18
    //   2714: istore #17
    //   2716: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity._activity : Landroid/app/Activity;
    //   2719: ifnull -> 2744
    //   2722: iload #18
    //   2724: istore #17
    //   2726: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity._activity : Landroid/app/Activity;
    //   2729: checkcast com/roadtrack/onstar/pid/RemoteDiagnosticActivity
    //   2732: getfield wakeupcar_container : Landroid/widget/RelativeLayout;
    //   2735: invokevirtual getVisibility : ()I
    //   2738: ifne -> 2744
    //   2741: iconst_1
    //   2742: istore #17
    //   2744: aload_1
    //   2745: ldc_w com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar
    //   2748: invokestatic isActivityRunning : (Landroid/content/Context;Ljava/lang/Class;)Z
    //   2751: ifeq -> 2768
    //   2754: new android/content/Intent
    //   2757: dup
    //   2758: invokespecial <init> : ()V
    //   2761: astore_2
    //   2762: aload_2
    //   2763: astore #5
    //   2765: goto -> 2799
    //   2768: aload_2
    //   2769: astore #5
    //   2771: aload_1
    //   2772: ldc com/roadtrack/onstar/pid/RemoteDiagnosticActivity
    //   2774: invokestatic isActivityRunning : (Landroid/content/Context;Ljava/lang/Class;)Z
    //   2777: ifeq -> 2799
    //   2780: aload_2
    //   2781: astore #5
    //   2783: iload #17
    //   2785: ifeq -> 2799
    //   2788: new android/content/Intent
    //   2791: dup
    //   2792: invokespecial <init> : ()V
    //   2795: astore_2
    //   2796: goto -> 2762
    //   2799: new java/lang/String
    //   2802: invokespecial <init> : ()V
    //   2805: aload #5
    //   2807: invokevirtual getComponent : ()Landroid/content/ComponentName;
    //   2810: invokevirtual getClassName : ()Ljava/lang/String;
    //   2813: pop
    //   2814: goto -> 2830
    //   2817: astore_2
    //   2818: ldc 'MessageRTMobile'
    //   2820: ldc_w 'Exception'
    //   2823: aload_2
    //   2824: invokevirtual getMessage : ()Ljava/lang/String;
    //   2827: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2830: aload_0
    //   2831: aload_1
    //   2832: aload #5
    //   2834: iload #10
    //   2836: aload_3
    //   2837: aload #4
    //   2839: iload #7
    //   2841: iload #6
    //   2843: invokevirtual sendNotification : (Landroid/content/Context;Landroid/content/Intent;ILjava/lang/String;Ljava/lang/String;IZ)V
    //   2846: goto -> 2862
    //   2849: astore_1
    //   2850: ldc 'MessageRTMobile'
    //   2852: ldc_w 'Error: MessageRTMobile.NotificationAlert_Inner: '
    //   2855: aload_1
    //   2856: invokevirtual getMessage : ()Ljava/lang/String;
    //   2859: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2862: return
    // Exception table:
    //   from	to	target	type
    //   12	53	2849	java/lang/Exception
    //   60	71	2849	java/lang/Exception
    //   120	124	2849	java/lang/Exception
    //   127	138	2849	java/lang/Exception
    //   163	191	2849	java/lang/Exception
    //   199	208	2849	java/lang/Exception
    //   219	247	2849	java/lang/Exception
    //   259	268	2849	java/lang/Exception
    //   272	300	2849	java/lang/Exception
    //   324	333	2849	java/lang/Exception
    //   356	384	2849	java/lang/Exception
    //   398	496	2849	java/lang/Exception
    //   496	501	2849	java/lang/Exception
    //   513	553	2849	java/lang/Exception
    //   565	593	2849	java/lang/Exception
    //   601	610	2849	java/lang/Exception
    //   621	649	2849	java/lang/Exception
    //   661	689	2849	java/lang/Exception
    //   701	729	2849	java/lang/Exception
    //   737	765	2849	java/lang/Exception
    //   773	782	2849	java/lang/Exception
    //   793	821	2849	java/lang/Exception
    //   833	861	2849	java/lang/Exception
    //   869	878	2849	java/lang/Exception
    //   889	917	2849	java/lang/Exception
    //   925	934	2849	java/lang/Exception
    //   945	973	2849	java/lang/Exception
    //   981	990	2849	java/lang/Exception
    //   1001	1029	2849	java/lang/Exception
    //   1037	1046	2849	java/lang/Exception
    //   1057	1085	2849	java/lang/Exception
    //   1097	1125	2849	java/lang/Exception
    //   1133	1142	2849	java/lang/Exception
    //   1153	1181	2849	java/lang/Exception
    //   1201	1210	2849	java/lang/Exception
    //   1224	1299	2849	java/lang/Exception
    //   1310	1338	2849	java/lang/Exception
    //   1346	1364	2849	java/lang/Exception
    //   1375	1403	2849	java/lang/Exception
    //   1411	1429	2849	java/lang/Exception
    //   1440	1468	2849	java/lang/Exception
    //   1476	1494	2849	java/lang/Exception
    //   1505	1533	2849	java/lang/Exception
    //   1557	1611	2849	java/lang/Exception
    //   1630	1684	2849	java/lang/Exception
    //   1747	1816	2849	java/lang/Exception
    //   1821	2169	2849	java/lang/Exception
    //   2172	2183	2849	java/lang/Exception
    //   2227	2263	2849	java/lang/Exception
    //   2271	2301	2849	java/lang/Exception
    //   2308	2350	2849	java/lang/Exception
    //   2358	2398	2849	java/lang/Exception
    //   2405	2441	2849	java/lang/Exception
    //   2444	2474	2849	java/lang/Exception
    //   2482	2510	2849	java/lang/Exception
    //   2523	2551	2849	java/lang/Exception
    //   2559	2587	2849	java/lang/Exception
    //   2594	2618	2849	java/lang/Exception
    //   2626	2706	2849	java/lang/Exception
    //   2716	2722	2849	java/lang/Exception
    //   2726	2741	2849	java/lang/Exception
    //   2744	2762	2849	java/lang/Exception
    //   2771	2780	2849	java/lang/Exception
    //   2788	2796	2849	java/lang/Exception
    //   2799	2805	2849	java/lang/Exception
    //   2805	2814	2817	java/lang/Exception
    //   2818	2830	2849	java/lang/Exception
    //   2830	2846	2849	java/lang/Exception
  }
  
  public void MessageToast(Context paramContext, String paramString1, String paramString2) {
    Toast.makeText(paramContext.getApplicationContext(), String.format("%s\n%s", new Object[] { paramString1, paramString2 }), 1).show();
  }
  
  public void NotificationAlert(Context paramContext, Intent paramIntent, String paramString1, String paramString2, Enums.NotificationRTMobile paramNotificationRTMobile, int paramInt1, Enums.Services paramServices, String paramString3, int paramInt2, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, boolean paramBoolean) {
    NotificationAlert_Inner(paramContext, paramIntent, paramString1, paramString2, paramNotificationRTMobile, true, paramInt1, paramServices, paramString3, paramInt2, paramString4, paramString5, paramString6, paramString7, paramString8, paramBoolean);
  }
  
  public void sendNotification(Context paramContext, Intent paramIntent, int paramInt1, String paramString1, String paramString2, int paramInt2, boolean paramBoolean) {
    if (GlobalMembers.ToExitApp)
      paramIntent = new Intent(paramContext, LoginActivity.class); 
    PendingIntent pendingIntent = PendingIntent.getActivity(paramContext, 0, paramIntent, 203423744);
    AppName.setPendingNotificationsCount(AppName.getPendingNotificationsCount() + 1);
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
  
  public static class AppName extends Application {
    private static int pendingNotificationsCount;
    
    public static int getPendingNotificationsCount() {
      return pendingNotificationsCount;
    }
    
    public static void setPendingNotificationsCount(int param1Int) {
      pendingNotificationsCount = param1Int;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/entities/MessageRTMobile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */