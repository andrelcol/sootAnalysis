package com.roadtrack.onstar.new_ws;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.pid.DTCWebViewActivity;
import com.roadtrack.onstar.utils.Utilities;
import java.io.Serializable;

public class ExecuteDTCWS extends AsyncTask<String, String, Object> {
  private String TAG = ExecuteDTCWS.class.getSimpleName();
  
  AsyncResponse asyncResponse;
  
  private Context context;
  
  private DBFunctions dbFunctions;
  
  private boolean isFromNotification = false;
  
  private ProgressDialog progressDialog;
  
  private int responseCode = 0;
  
  private int responseCodeAgendamiento = 0;
  
  private int responseCodeLogin = 0;
  
  private onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private String uriIntentWeb = null;
  
  String urlButton;
  
  private boolean userCancel;
  
  public ExecuteDTCWS(Context paramContext, boolean paramBoolean, AsyncResponse paramAsyncResponse, String paramString) {
    this.context = paramContext;
    this.asyncResponse = paramAsyncResponse;
    this.isFromNotification = paramBoolean;
    this.urlButton = paramString;
    this.dbFunctions = new DBFunctions(paramContext);
    this.rtApp = (onstarApplication)paramContext.getApplicationContext();
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  private String replaceCharacter(String paramString) {
    return paramString.replace("\n", "").trim().replace("+", "-").replace("/", "_");
  }
  
  protected Object doInBackground(String... paramVarArgs) {
    // Byte code:
    //   0: invokestatic currentThread : ()Ljava/lang/Thread;
    //   3: astore_3
    //   4: new java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial <init> : ()V
    //   11: astore #4
    //   13: aload #4
    //   15: ldc com/roadtrack/onstar/new_ws/ExecuteDTCWS
    //   17: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload #4
    //   26: ldc ': '
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload #4
    //   34: invokestatic currentThread : ()Ljava/lang/Thread;
    //   37: invokevirtual getName : ()Ljava/lang/String;
    //   40: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: aload_3
    //   45: aload #4
    //   47: invokevirtual toString : ()Ljava/lang/String;
    //   50: invokevirtual setName : (Ljava/lang/String;)V
    //   53: aload_0
    //   54: getfield context : Landroid/content/Context;
    //   57: iconst_0
    //   58: invokestatic validateNetwork : (Landroid/content/Context;Z)Z
    //   61: ifne -> 66
    //   64: aconst_null
    //   65: areturn
    //   66: aload_1
    //   67: iconst_0
    //   68: aaload
    //   69: astore_3
    //   70: aload_1
    //   71: iconst_1
    //   72: aaload
    //   73: astore #5
    //   75: new java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial <init> : ()V
    //   82: astore #4
    //   84: aload #4
    //   86: aload_3
    //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: aload #4
    //   93: ldc '[]'
    //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: aload #4
    //   101: aload #5
    //   103: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: aload #4
    //   109: ldc 'Ubiko'
    //   111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: pop
    //   115: aload_0
    //   116: aload #4
    //   118: invokevirtual toString : ()Ljava/lang/String;
    //   121: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   124: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   127: astore #5
    //   129: aload_1
    //   130: iconst_2
    //   131: aaload
    //   132: astore #9
    //   134: aload_1
    //   135: iconst_3
    //   136: aaload
    //   137: astore #8
    //   139: new java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial <init> : ()V
    //   146: astore_3
    //   147: aload_3
    //   148: aload #9
    //   150: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload_3
    //   155: ldc '-'
    //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: pop
    //   161: aload_3
    //   162: aload #8
    //   164: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: aload_3
    //   169: invokevirtual toString : ()Ljava/lang/String;
    //   172: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   175: astore_3
    //   176: ldc ''
    //   178: astore #4
    //   180: aload_3
    //   181: ldc '\\n'
    //   183: ldc ''
    //   185: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   188: invokevirtual trim : ()Ljava/lang/String;
    //   191: ldc '+'
    //   193: ldc '-'
    //   195: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   198: ldc '/'
    //   200: ldc '_'
    //   202: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   205: astore #7
    //   207: new java/util/LinkedHashMap
    //   210: astore #6
    //   212: aload #6
    //   214: invokespecial <init> : ()V
    //   217: aload #6
    //   219: ldc 'grant_type'
    //   221: ldc 'password'
    //   223: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   226: pop
    //   227: aload #6
    //   229: ldc 'clientId'
    //   231: aload #5
    //   233: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   236: pop
    //   237: new com/roadtrack/onstar/BO/SimpleRequestObject
    //   240: astore_3
    //   241: aload_3
    //   242: aload #6
    //   244: getstatic com/roadtrack/onstar/BO/GlobalMembers.URL_DTC_LocatorWebApiLogin : Ljava/lang/String;
    //   247: getstatic com/roadtrack/onstar/BO/GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF : I
    //   250: getstatic com/roadtrack/onstar/BO/GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF : I
    //   253: invokespecial <init> : (Ljava/util/LinkedHashMap;Ljava/lang/String;II)V
    //   256: aload_3
    //   257: ldc 2131623959
    //   259: getstatic com/roadtrack/onstar/BO/GlobalMembers.nameKeystoreServiceDTC : Ljava/lang/String;
    //   262: invokevirtual set_keyStoreId : (ILjava/lang/String;)V
    //   265: new java/util/LinkedHashMap
    //   268: astore #6
    //   270: aload #6
    //   272: invokespecial <init> : ()V
    //   275: aload #6
    //   277: ldc 'Content-Type'
    //   279: ldc 'application/x-www-form-urlencoded'
    //   281: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   284: pop
    //   285: aload_3
    //   286: aload #6
    //   288: invokevirtual setRequest_propertys : (Ljava/util/LinkedHashMap;)V
    //   291: new com/roadtrack/onstar/BO/RequestManager
    //   294: astore #6
    //   296: aload #6
    //   298: aload_0
    //   299: getfield context : Landroid/content/Context;
    //   302: aload_3
    //   303: invokespecial <init> : (Landroid/content/Context;Lcom/roadtrack/onstar/BO/SimpleRequestObject;)V
    //   306: aload #6
    //   308: iconst_2
    //   309: invokevirtual sendRequest : (I)V
    //   312: aload_0
    //   313: aload #6
    //   315: invokevirtual getResponseCode : ()I
    //   318: putfield responseCodeLogin : I
    //   321: aload #6
    //   323: invokevirtual getRespuesta : ()Ljava/lang/String;
    //   326: astore_3
    //   327: goto -> 363
    //   330: astore_3
    //   331: aload_0
    //   332: getfield TAG : Ljava/lang/String;
    //   335: ldc ' Exception'
    //   337: aload_3
    //   338: invokevirtual getMessage : ()Ljava/lang/String;
    //   341: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   344: goto -> 361
    //   347: astore_3
    //   348: aload_0
    //   349: getfield TAG : Ljava/lang/String;
    //   352: ldc ' IOException'
    //   354: aload_3
    //   355: invokevirtual getMessage : ()Ljava/lang/String;
    //   358: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   361: aconst_null
    //   362: astore_3
    //   363: aload_0
    //   364: getfield TAG : Ljava/lang/String;
    //   367: ldc 'EXITAPP'
    //   369: ldc 'After unregisterDevice'
    //   371: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   374: aload_0
    //   375: getfield responseCodeLogin : I
    //   378: sipush #200
    //   381: if_icmpne -> 1767
    //   384: new com/roadtrack/onstar/VO/DTCConnectionVO
    //   387: dup
    //   388: invokespecial <init> : ()V
    //   391: astore #6
    //   393: aload_3
    //   394: ifnull -> 526
    //   397: new org/json/JSONObject
    //   400: astore #10
    //   402: aload #10
    //   404: aload_3
    //   405: invokespecial <init> : (Ljava/lang/String;)V
    //   408: aload #6
    //   410: aload #10
    //   412: ldc 'access_token'
    //   414: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   417: invokevirtual setAccess_token : (Ljava/lang/String;)V
    //   420: aload #6
    //   422: aload #10
    //   424: ldc_w 'expires'
    //   427: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   430: invokevirtual setExpires : (Ljava/lang/String;)V
    //   433: aload #6
    //   435: aload #10
    //   437: ldc_w 'expires_in'
    //   440: invokevirtual getInt : (Ljava/lang/String;)I
    //   443: invokevirtual setExpires_in : (I)V
    //   446: aload #6
    //   448: aload #10
    //   450: ldc_w 'issued'
    //   453: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   456: invokevirtual setIssues : (Ljava/lang/String;)V
    //   459: aload #6
    //   461: aload #10
    //   463: ldc_w 'token_type'
    //   466: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   469: invokevirtual setToken_type : (Ljava/lang/String;)V
    //   472: aload #6
    //   474: aload #10
    //   476: ldc_w 'userId'
    //   479: invokevirtual getInt : (Ljava/lang/String;)I
    //   482: invokevirtual setUserId : (I)V
    //   485: aload #6
    //   487: aload #10
    //   489: ldc_w 'userName'
    //   492: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   495: invokevirtual setUserName : (Ljava/lang/String;)V
    //   498: goto -> 526
    //   501: astore #10
    //   503: aload_0
    //   504: getfield TAG : Ljava/lang/String;
    //   507: ldc_w ' JSONException parseo del token'
    //   510: aload #10
    //   512: invokevirtual getMessage : ()Ljava/lang/String;
    //   515: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   518: aload #10
    //   520: invokevirtual printStackTrace : ()V
    //   523: goto -> 526
    //   526: aload_0
    //   527: getfield urlButton : Ljava/lang/String;
    //   530: getstatic com/roadtrack/onstar/BO/GlobalMembers.URL_DTC_LocatorWebApp : Ljava/lang/String;
    //   533: invokevirtual equals : (Ljava/lang/Object;)Z
    //   536: ifeq -> 801
    //   539: aload_1
    //   540: iconst_4
    //   541: aaload
    //   542: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   545: ldc '\\n'
    //   547: ldc ''
    //   549: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   552: invokevirtual trim : ()Ljava/lang/String;
    //   555: ldc '+'
    //   557: ldc '-'
    //   559: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   562: ldc '/'
    //   564: ldc '_'
    //   566: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   569: astore #4
    //   571: aload_0
    //   572: getfield urlButton : Ljava/lang/String;
    //   575: astore_3
    //   576: aload #7
    //   578: ifnull -> 1767
    //   581: aload #6
    //   583: invokevirtual getAccess_token : ()Ljava/lang/String;
    //   586: ifnull -> 1767
    //   589: aload #6
    //   591: invokevirtual getAccess_token : ()Ljava/lang/String;
    //   594: invokevirtual isEmpty : ()Z
    //   597: ifne -> 1767
    //   600: new java/util/HashMap
    //   603: astore_1
    //   604: aload_1
    //   605: invokespecial <init> : ()V
    //   608: aload_1
    //   609: ldc_w 'URL'
    //   612: aload_3
    //   613: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   616: pop
    //   617: aload_1
    //   618: ldc_w 'EfCve_DeviceId'
    //   621: aload #7
    //   623: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   626: pop
    //   627: aload_1
    //   628: ldc_w 'tok'
    //   631: aload #6
    //   633: invokevirtual getAccess_token : ()Ljava/lang/String;
    //   636: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   639: pop
    //   640: aload_1
    //   641: ldc_w 'dtcGroup'
    //   644: aload #4
    //   646: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   649: pop
    //   650: aload_0
    //   651: aload_3
    //   652: iconst_3
    //   653: anewarray java/lang/Object
    //   656: dup
    //   657: iconst_0
    //   658: aload #7
    //   660: aastore
    //   661: dup
    //   662: iconst_1
    //   663: aload #6
    //   665: invokevirtual getAccess_token : ()Ljava/lang/String;
    //   668: aastore
    //   669: dup
    //   670: iconst_2
    //   671: aload #4
    //   673: aastore
    //   674: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   677: putfield uriIntentWeb : Ljava/lang/String;
    //   680: new com/roadtrack/onstar/BO/SimpleRequestObject
    //   683: astore_3
    //   684: aload_3
    //   685: aconst_null
    //   686: aload_0
    //   687: getfield uriIntentWeb : Ljava/lang/String;
    //   690: getstatic com/roadtrack/onstar/BO/GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF : I
    //   693: getstatic com/roadtrack/onstar/BO/GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF : I
    //   696: invokespecial <init> : (Ljava/util/LinkedHashMap;Ljava/lang/String;II)V
    //   699: aload_3
    //   700: ldc 2131623959
    //   702: getstatic com/roadtrack/onstar/BO/GlobalMembers.nameKeystoreServiceDTC : Ljava/lang/String;
    //   705: invokevirtual set_keyStoreId : (ILjava/lang/String;)V
    //   708: new com/roadtrack/onstar/BO/RequestManager
    //   711: astore #4
    //   713: aload #4
    //   715: aload_0
    //   716: getfield context : Landroid/content/Context;
    //   719: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   722: aload_3
    //   723: invokespecial <init> : (Landroid/content/Context;Lcom/roadtrack/onstar/BO/SimpleRequestObject;)V
    //   726: aload #4
    //   728: iconst_3
    //   729: invokevirtual sendRequest : (I)V
    //   732: aload_0
    //   733: aload #4
    //   735: invokevirtual getResponseCode : ()I
    //   738: putfield responseCode : I
    //   741: aload_0
    //   742: getfield responseCode : I
    //   745: sipush #200
    //   748: if_icmpne -> 777
    //   751: iconst_0
    //   752: putstatic com/roadtrack/onstar/BO/GlobalMembers.notificacionesDTC : I
    //   755: aload_0
    //   756: getfield dbFunctions : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   759: aload_0
    //   760: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   763: aload_0
    //   764: getfield TAG : Ljava/lang/String;
    //   767: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   770: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   773: invokevirtual readerNotificationDTC : (Ljava/lang/String;)J
    //   776: pop2
    //   777: aload_1
    //   778: areturn
    //   779: astore_1
    //   780: aload_0
    //   781: getfield TAG : Ljava/lang/String;
    //   784: ldc_w ' Exception RequestManager in sendRequest'
    //   787: aload_1
    //   788: invokevirtual getMessage : ()Ljava/lang/String;
    //   791: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   794: aload_1
    //   795: invokevirtual printStackTrace : ()V
    //   798: goto -> 1767
    //   801: aload_0
    //   802: getfield urlButton : Ljava/lang/String;
    //   805: getstatic com/roadtrack/onstar/BO/GlobalMembers.URL_DTC_LocatorWebApiAgendamiento : Ljava/lang/String;
    //   808: invokevirtual equals : (Ljava/lang/Object;)Z
    //   811: ifeq -> 1767
    //   814: new java/security/SecureRandom
    //   817: dup
    //   818: invokespecial <init> : ()V
    //   821: ldc_w 100000
    //   824: invokevirtual nextInt : (I)I
    //   827: istore_2
    //   828: new java/lang/StringBuilder
    //   831: dup
    //   832: invokespecial <init> : ()V
    //   835: astore_1
    //   836: aload_1
    //   837: aload #9
    //   839: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   842: pop
    //   843: aload_1
    //   844: ldc '-'
    //   846: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   849: pop
    //   850: aload_1
    //   851: aload #8
    //   853: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   856: pop
    //   857: aload_1
    //   858: ldc '-'
    //   860: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   863: pop
    //   864: aload_1
    //   865: iload_2
    //   866: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   869: pop
    //   870: aload_0
    //   871: aload_1
    //   872: invokevirtual toString : ()Ljava/lang/String;
    //   875: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   878: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   881: astore #8
    //   883: aload_0
    //   884: getfield urlButton : Ljava/lang/String;
    //   887: astore #7
    //   889: new java/lang/StringBuilder
    //   892: dup
    //   893: invokespecial <init> : ()V
    //   896: astore_1
    //   897: aload_1
    //   898: ldc_w 'bearer '
    //   901: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   904: pop
    //   905: aload_1
    //   906: aload #6
    //   908: invokevirtual getAccess_token : ()Ljava/lang/String;
    //   911: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   914: pop
    //   915: aload_1
    //   916: invokevirtual toString : ()Ljava/lang/String;
    //   919: astore_1
    //   920: aload #8
    //   922: ifnull -> 1077
    //   925: aload #6
    //   927: invokevirtual getAccess_token : ()Ljava/lang/String;
    //   930: ifnull -> 1077
    //   933: aload #6
    //   935: invokevirtual getAccess_token : ()Ljava/lang/String;
    //   938: invokevirtual isEmpty : ()Z
    //   941: ifne -> 1077
    //   944: aload_0
    //   945: aload #7
    //   947: iconst_1
    //   948: anewarray java/lang/Object
    //   951: dup
    //   952: iconst_0
    //   953: aload #8
    //   955: aastore
    //   956: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   959: putfield uriIntentWeb : Ljava/lang/String;
    //   962: new com/roadtrack/onstar/BO/SimpleRequestObject
    //   965: astore #7
    //   967: aload #7
    //   969: aconst_null
    //   970: aload_0
    //   971: getfield uriIntentWeb : Ljava/lang/String;
    //   974: getstatic com/roadtrack/onstar/BO/GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF : I
    //   977: getstatic com/roadtrack/onstar/BO/GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF : I
    //   980: invokespecial <init> : (Ljava/util/LinkedHashMap;Ljava/lang/String;II)V
    //   983: aload #7
    //   985: ldc 2131623959
    //   987: getstatic com/roadtrack/onstar/BO/GlobalMembers.nameKeystoreServiceDTC : Ljava/lang/String;
    //   990: invokevirtual set_keyStoreId : (ILjava/lang/String;)V
    //   993: new java/util/LinkedHashMap
    //   996: astore #8
    //   998: aload #8
    //   1000: invokespecial <init> : ()V
    //   1003: aload #8
    //   1005: ldc_w 'authorization'
    //   1008: aload_1
    //   1009: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1012: pop
    //   1013: aload #7
    //   1015: aload #8
    //   1017: invokevirtual setRequest_propertys : (Ljava/util/LinkedHashMap;)V
    //   1020: new com/roadtrack/onstar/BO/RequestManager
    //   1023: astore_1
    //   1024: aload_1
    //   1025: aload_0
    //   1026: getfield context : Landroid/content/Context;
    //   1029: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   1032: aload #7
    //   1034: invokespecial <init> : (Landroid/content/Context;Lcom/roadtrack/onstar/BO/SimpleRequestObject;)V
    //   1037: aload_1
    //   1038: iconst_3
    //   1039: invokevirtual sendRequest : (I)V
    //   1042: aload_0
    //   1043: aload_1
    //   1044: invokevirtual getResponseCode : ()I
    //   1047: putfield responseCodeAgendamiento : I
    //   1050: aload_1
    //   1051: invokevirtual getRespuesta : ()Ljava/lang/String;
    //   1054: astore_1
    //   1055: goto -> 1079
    //   1058: astore_1
    //   1059: aload_0
    //   1060: getfield TAG : Ljava/lang/String;
    //   1063: ldc_w ' Exception RequestManager in sendRequest'
    //   1066: aload_1
    //   1067: invokevirtual getMessage : ()Ljava/lang/String;
    //   1070: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1073: aload_1
    //   1074: invokevirtual printStackTrace : ()V
    //   1077: aload_3
    //   1078: astore_1
    //   1079: aload_0
    //   1080: getfield responseCodeAgendamiento : I
    //   1083: sipush #200
    //   1086: if_icmpne -> 1767
    //   1089: new org/json/JSONObject
    //   1092: astore_3
    //   1093: aload_3
    //   1094: aload_1
    //   1095: invokespecial <init> : (Ljava/lang/String;)V
    //   1098: aload_3
    //   1099: ldc_w 'Customer'
    //   1102: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
    //   1105: astore_1
    //   1106: aload_3
    //   1107: ldc_w 'Vehicle'
    //   1110: invokevirtual getJSONObject : (Ljava/lang/String;)Lorg/json/JSONObject;
    //   1113: astore_3
    //   1114: aload #6
    //   1116: aload_1
    //   1117: ldc_w 'Plate'
    //   1120: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1123: invokevirtual setPlate : (Ljava/lang/String;)V
    //   1126: aload #6
    //   1128: aload_1
    //   1129: ldc_w 'VIN'
    //   1132: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1135: invokevirtual setVin : (Ljava/lang/String;)V
    //   1138: aload #6
    //   1140: aload_1
    //   1141: ldc_w 'CSEntrega'
    //   1144: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1147: invokevirtual setCs_entrega : (Ljava/lang/String;)V
    //   1150: aload #6
    //   1152: aload_1
    //   1153: ldc_w 'Name'
    //   1156: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1159: invokevirtual setName : (Ljava/lang/String;)V
    //   1162: aload #6
    //   1164: aload_1
    //   1165: ldc_w 'Lastname'
    //   1168: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1171: invokevirtual setApellido : (Ljava/lang/String;)V
    //   1174: aload #6
    //   1176: aload_1
    //   1177: ldc_w 'ID'
    //   1180: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1183: invokevirtual setId : (Ljava/lang/String;)V
    //   1186: aload #6
    //   1188: aload_1
    //   1189: ldc_w 'Phone'
    //   1192: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1195: invokevirtual setPhone : (Ljava/lang/String;)V
    //   1198: aload #6
    //   1200: aload_1
    //   1201: ldc_w 'Email'
    //   1204: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1207: invokevirtual setEmail : (Ljava/lang/String;)V
    //   1210: aload #6
    //   1212: aload_3
    //   1213: ldc_w 'Km'
    //   1216: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1219: invokevirtual setOdometro : (Ljava/lang/String;)V
    //   1222: aload #6
    //   1224: aload_1
    //   1225: ldc_w 'DTCs'
    //   1228: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   1231: invokevirtual setDtcs : (Ljava/lang/String;)V
    //   1234: goto -> 1256
    //   1237: astore_1
    //   1238: aload_0
    //   1239: getfield TAG : Ljava/lang/String;
    //   1242: ldc_w ' JSONException parseo del token'
    //   1245: aload_1
    //   1246: invokevirtual getMessage : ()Ljava/lang/String;
    //   1249: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1252: aload_1
    //   1253: invokevirtual printStackTrace : ()V
    //   1256: aload #6
    //   1258: invokevirtual getAccess_token : ()Ljava/lang/String;
    //   1261: astore #7
    //   1263: aload_0
    //   1264: aload #6
    //   1266: invokevirtual getPlate : ()Ljava/lang/String;
    //   1269: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1272: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1275: astore #9
    //   1277: aload_0
    //   1278: aload #6
    //   1280: invokevirtual getVin : ()Ljava/lang/String;
    //   1283: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1286: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1289: astore #11
    //   1291: aload_0
    //   1292: aload #6
    //   1294: invokevirtual getCs_entrega : ()Ljava/lang/String;
    //   1297: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1300: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1303: astore #14
    //   1305: aload_0
    //   1306: aload #6
    //   1308: invokevirtual getName : ()Ljava/lang/String;
    //   1311: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1314: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1317: astore #13
    //   1319: aload_0
    //   1320: aload #6
    //   1322: invokevirtual getApellido : ()Ljava/lang/String;
    //   1325: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1328: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1331: astore_3
    //   1332: aload_0
    //   1333: aload #6
    //   1335: invokevirtual getId : ()Ljava/lang/String;
    //   1338: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1341: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1344: astore #15
    //   1346: aload_0
    //   1347: aload #6
    //   1349: invokevirtual getPhone : ()Ljava/lang/String;
    //   1352: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1355: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1358: astore #8
    //   1360: aload_0
    //   1361: aload #6
    //   1363: invokevirtual getEmail : ()Ljava/lang/String;
    //   1366: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1369: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1372: astore #10
    //   1374: aload_0
    //   1375: aload #6
    //   1377: invokevirtual getOdometro : ()Ljava/lang/String;
    //   1380: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1383: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1386: astore #12
    //   1388: aload_0
    //   1389: aload #6
    //   1391: invokevirtual getDtcs : ()Ljava/lang/String;
    //   1394: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1397: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1400: astore #16
    //   1402: aload #6
    //   1404: invokevirtual getOdometro : ()Ljava/lang/String;
    //   1407: ifnull -> 1484
    //   1410: aload #6
    //   1412: invokevirtual getOdometro : ()Ljava/lang/String;
    //   1415: invokevirtual isEmpty : ()Z
    //   1418: ifne -> 1484
    //   1421: aload #6
    //   1423: invokevirtual getOdometro : ()Ljava/lang/String;
    //   1426: invokestatic parseInt : (Ljava/lang/String;)I
    //   1429: istore_2
    //   1430: goto -> 1486
    //   1433: astore_1
    //   1434: aload_0
    //   1435: getfield TAG : Ljava/lang/String;
    //   1438: astore #17
    //   1440: new java/lang/StringBuilder
    //   1443: dup
    //   1444: invokespecial <init> : ()V
    //   1447: astore #6
    //   1449: aload #6
    //   1451: ldc_w 'Error al parsear String : strTireFL'
    //   1454: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1457: pop
    //   1458: aload #6
    //   1460: aload_1
    //   1461: invokevirtual toString : ()Ljava/lang/String;
    //   1464: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1467: pop
    //   1468: aload #17
    //   1470: ldc_w 'THEFT'
    //   1473: aload #6
    //   1475: invokevirtual toString : ()Ljava/lang/String;
    //   1478: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1481: goto -> 1484
    //   1484: iconst_0
    //   1485: istore_2
    //   1486: new java/lang/StringBuilder
    //   1489: dup
    //   1490: invokespecial <init> : ()V
    //   1493: astore_1
    //   1494: aload_1
    //   1495: iload_2
    //   1496: i2d
    //   1497: invokestatic factorOdometer : (D)I
    //   1500: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1503: pop
    //   1504: aload_1
    //   1505: ldc ''
    //   1507: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1510: pop
    //   1511: aload_1
    //   1512: invokevirtual toString : ()Ljava/lang/String;
    //   1515: astore #6
    //   1517: aload #4
    //   1519: astore_1
    //   1520: aload #6
    //   1522: ifnull -> 1570
    //   1525: aload #6
    //   1527: ldc_w '0'
    //   1530: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1533: ifeq -> 1542
    //   1536: aload #4
    //   1538: astore_1
    //   1539: goto -> 1570
    //   1542: new java/lang/StringBuilder
    //   1545: dup
    //   1546: invokespecial <init> : ()V
    //   1549: astore_1
    //   1550: aload_1
    //   1551: aload #6
    //   1553: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1556: pop
    //   1557: aload_1
    //   1558: ldc_w 'K'
    //   1561: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1564: pop
    //   1565: aload_1
    //   1566: invokevirtual toString : ()Ljava/lang/String;
    //   1569: astore_1
    //   1570: aload_0
    //   1571: aload_1
    //   1572: invokestatic CryptDTC : (Ljava/lang/String;)Ljava/lang/String;
    //   1575: invokespecial replaceCharacter : (Ljava/lang/String;)Ljava/lang/String;
    //   1578: astore_1
    //   1579: aload_0
    //   1580: getstatic com/roadtrack/onstar/BO/GlobalMembers.URL_DTC_ChevroletCOAgendar : Ljava/lang/String;
    //   1583: bipush #13
    //   1585: anewarray java/lang/Object
    //   1588: dup
    //   1589: iconst_0
    //   1590: aload #5
    //   1592: aastore
    //   1593: dup
    //   1594: iconst_1
    //   1595: aload #7
    //   1597: aastore
    //   1598: dup
    //   1599: iconst_2
    //   1600: aload #9
    //   1602: aastore
    //   1603: dup
    //   1604: iconst_3
    //   1605: aload #11
    //   1607: aastore
    //   1608: dup
    //   1609: iconst_4
    //   1610: aload #14
    //   1612: aastore
    //   1613: dup
    //   1614: iconst_5
    //   1615: aload #13
    //   1617: aastore
    //   1618: dup
    //   1619: bipush #6
    //   1621: aload_3
    //   1622: aastore
    //   1623: dup
    //   1624: bipush #7
    //   1626: aload #15
    //   1628: aastore
    //   1629: dup
    //   1630: bipush #8
    //   1632: aload #8
    //   1634: aastore
    //   1635: dup
    //   1636: bipush #9
    //   1638: aload #10
    //   1640: aastore
    //   1641: dup
    //   1642: bipush #10
    //   1644: aload #12
    //   1646: aastore
    //   1647: dup
    //   1648: bipush #11
    //   1650: aload #16
    //   1652: aastore
    //   1653: dup
    //   1654: bipush #12
    //   1656: aload_1
    //   1657: aastore
    //   1658: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1661: putfield uriIntentWeb : Ljava/lang/String;
    //   1664: aload_0
    //   1665: getfield uriIntentWeb : Ljava/lang/String;
    //   1668: astore_1
    //   1669: getstatic com/roadtrack/onstar/BO/GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF : I
    //   1672: istore_2
    //   1673: new com/roadtrack/onstar/BO/SimpleRequestObject
    //   1676: dup
    //   1677: aconst_null
    //   1678: aload_1
    //   1679: iload_2
    //   1680: iload_2
    //   1681: invokespecial <init> : (Ljava/util/LinkedHashMap;Ljava/lang/String;II)V
    //   1684: astore_1
    //   1685: aload_1
    //   1686: ldc 2131623959
    //   1688: getstatic com/roadtrack/onstar/BO/GlobalMembers.nameKeystoreServiceDTC : Ljava/lang/String;
    //   1691: invokevirtual set_keyStoreId : (ILjava/lang/String;)V
    //   1694: new com/roadtrack/onstar/BO/RequestManager
    //   1697: dup
    //   1698: aload_0
    //   1699: getfield context : Landroid/content/Context;
    //   1702: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   1705: aload_1
    //   1706: invokespecial <init> : (Landroid/content/Context;Lcom/roadtrack/onstar/BO/SimpleRequestObject;)V
    //   1709: astore_3
    //   1710: aload_3
    //   1711: iconst_3
    //   1712: invokevirtual sendRequest : (I)V
    //   1715: goto -> 1723
    //   1718: astore_1
    //   1719: aload_1
    //   1720: invokevirtual printStackTrace : ()V
    //   1723: aload_0
    //   1724: aload_3
    //   1725: invokevirtual getResponseCode : ()I
    //   1728: putfield responseCode : I
    //   1731: aload_0
    //   1732: getfield responseCode : I
    //   1735: sipush #200
    //   1738: if_icmpne -> 1767
    //   1741: iconst_0
    //   1742: putstatic com/roadtrack/onstar/BO/GlobalMembers.notificacionesAgendamiento : I
    //   1745: aload_0
    //   1746: getfield dbFunctions : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1749: aload_0
    //   1750: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1753: aload_0
    //   1754: getfield TAG : Ljava/lang/String;
    //   1757: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   1760: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1763: invokevirtual readerNotificationAgendamiento : (Ljava/lang/String;)J
    //   1766: pop2
    //   1767: aload_0
    //   1768: getfield uriIntentWeb : Ljava/lang/String;
    //   1771: areturn
    // Exception table:
    //   from	to	target	type
    //   207	327	347	java/io/IOException
    //   207	327	330	java/lang/Exception
    //   397	498	501	org/json/JSONException
    //   539	576	779	java/lang/Exception
    //   581	777	779	java/lang/Exception
    //   944	1055	1058	java/lang/Exception
    //   1089	1234	1237	org/json/JSONException
    //   1421	1430	1433	java/lang/NumberFormatException
    //   1710	1715	1718	java/lang/Exception
  }
  
  public void onPostExecute(Object paramObject) {
    String str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    if (this.isFromNotification)
      this.progressDialog.dismiss(); 
    AsyncResponse asyncResponse = this.asyncResponse;
    if (asyncResponse != null)
      if (paramObject != null) {
        asyncResponse.processFinish(paramObject.toString());
      } else {
        Toast.makeText(this.context, str, 1).show();
      }  
    if (this.responseCode == 200) {
      try {
        String[] arrayOfString = paramObject.toString().split("arg");
        if (arrayOfString.length > 1)
          paramObject.toString().contains(arrayOfString[0]); 
        Intent intent = new Intent();
        this(this.context.getApplicationContext(), DTCWebViewActivity.class);
        Bundle bundle = new Bundle();
        this();
        intent.addFlags(268435456);
        bundle.putSerializable("url", (Serializable)paramObject);
        intent.putExtras(bundle);
        this.context.getApplicationContext().startActivity(intent);
      } catch (Exception exception) {
        Toast.makeText(this.context, str, 1).show();
      } 
    } else {
      Toast.makeText(this.context, str, 1).show();
    } 
    this.isFromNotification = false;
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    if (this.isFromNotification) {
      String str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.please_wait, 2131690270);
      String str2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
      this.progressDialog = new ProgressDialog(this.context, 2131755173);
      this.progressDialog.setIndeterminate(true);
      this.progressDialog.setCancelable(false);
      this.progressDialog.setMessage(str1);
      this.progressDialog.setButton(-2, str2, new DialogInterface.OnClickListener() {
            final ExecuteDTCWS this$0;
            
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
              ExecuteDTCWS.access$002(ExecuteDTCWS.this, true);
              ExecuteDTCWS.this.progressDialog.dismiss();
            }
          });
      this.progressDialog.show();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/new_ws/ExecuteDTCWS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */