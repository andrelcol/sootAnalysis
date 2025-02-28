package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponseList;
import com.roadtrack.onstar.utils.Utilities;
import java.util.List;

public class GetURLChangeCreditCard extends AsyncTask<Void, Void, List<String>> {
  public static final String TAG = GetURLPaymentsHistoryTask.class.getSimpleName();
  
  private String GMTID;
  
  private AsyncResponseList asyncResponse = null;
  
  private Context context;
  
  private ProgressDialog progressDialog;
  
  public GetURLChangeCreditCard(Context paramContext, AsyncResponseList paramAsyncResponseList, String paramString) {
    this.context = paramContext;
    this.asyncResponse = paramAsyncResponseList;
    this.GMTID = paramString;
  }
  
  private boolean isValidResponseStructure(List<String> paramList) {
    boolean bool;
    if (paramList != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected List<String> doInBackground(Void... paramVarArgs) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #6
    //   9: aload_0
    //   10: getfield context : Landroid/content/Context;
    //   13: iconst_0
    //   14: invokestatic validateNetwork : (Landroid/content/Context;Z)Z
    //   17: ifne -> 22
    //   20: aconst_null
    //   21: areturn
    //   22: new com/roadtrack/onstar/BO/WsAccess
    //   25: astore #7
    //   27: aload #7
    //   29: aload_0
    //   30: getfield context : Landroid/content/Context;
    //   33: invokespecial <init> : (Landroid/content/Context;)V
    //   36: aload_0
    //   37: getfield context : Landroid/content/Context;
    //   40: invokestatic getTokenAndDate : (Landroid/content/Context;)Ljava/util/HashMap;
    //   43: astore #4
    //   45: aload #4
    //   47: ldc 'MoTokn'
    //   49: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   52: checkcast java/lang/String
    //   55: astore_1
    //   56: aload #4
    //   58: ldc 'DaTokn'
    //   60: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   63: checkcast java/lang/String
    //   66: astore #5
    //   68: ldc 'PMM AttemptToPay'
    //   70: ldc 'token antes'
    //   72: aload_1
    //   73: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   76: aload_1
    //   77: ifnull -> 99
    //   80: aload_1
    //   81: invokevirtual isEmpty : ()Z
    //   84: ifne -> 99
    //   87: aload #5
    //   89: astore #4
    //   91: aload #5
    //   93: invokestatic isValidToken : (Ljava/lang/String;)Z
    //   96: ifne -> 119
    //   99: aload #7
    //   101: invokevirtual getRenewalToken : ()Lcom/roadtrack/onstar/VO/TokenResponseO;
    //   104: astore #4
    //   106: aload #4
    //   108: invokevirtual getLresp1 : ()Ljava/lang/String;
    //   111: astore_1
    //   112: aload #4
    //   114: invokevirtual getLresp6 : ()Ljava/lang/String;
    //   117: astore #4
    //   119: ldc 'PMM AttemptToPay'
    //   121: ldc 'token después'
    //   123: aload_1
    //   124: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   127: aload_1
    //   128: invokevirtual isEmpty : ()Z
    //   131: ifne -> 976
    //   134: aload_1
    //   135: ifnonnull -> 141
    //   138: goto -> 976
    //   141: aload #6
    //   143: aload_1
    //   144: invokeinterface add : (Ljava/lang/Object;)Z
    //   149: pop
    //   150: aload_1
    //   151: aload #4
    //   153: aload_0
    //   154: getfield context : Landroid/content/Context;
    //   157: invokestatic saveTokenAndDate : (Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
    //   160: aload #7
    //   162: aload_1
    //   163: aload_0
    //   164: getfield GMTID : Ljava/lang/String;
    //   167: invokevirtual getURLChangeCreditCard : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   170: astore #4
    //   172: aload_0
    //   173: getfield context : Landroid/content/Context;
    //   176: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   179: checkcast com/roadtrack/onstar/onstarApplication
    //   182: astore #7
    //   184: aload #7
    //   186: getstatic com/roadtrack/onstar/async_tasks/tasks/GetURLChangeCreditCard.TAG : Ljava/lang/String;
    //   189: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   192: astore #5
    //   194: new com/roadtrack/onstar/DAO/DBFunctions
    //   197: astore #8
    //   199: aload #8
    //   201: aload_0
    //   202: getfield context : Landroid/content/Context;
    //   205: invokespecial <init> : (Landroid/content/Context;)V
    //   208: aload #7
    //   210: invokevirtual getBaseContext : ()Landroid/content/Context;
    //   213: putstatic com/roadtrack/onstar/BO/GlobalMembers.ctxBase : Landroid/content/Context;
    //   216: aload #8
    //   218: getstatic com/roadtrack/onstar/BO/GlobalMembers.userLogged : Ljava/lang/String;
    //   221: invokevirtual getUserPreference : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   224: astore #8
    //   226: aload #5
    //   228: ifnull -> 974
    //   231: aload #5
    //   233: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   236: pop
    //   237: ldc 'PMM AttemptToPay'
    //   239: ldc 'Serialnumber'
    //   241: aload #5
    //   243: invokevirtual getSerialnumber : ()Ljava/lang/String;
    //   246: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   249: ldc 'PMM AttemptToPay'
    //   251: ldc 'DeviceId'
    //   253: aload #5
    //   255: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   258: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   261: ldc 'PMM AttemptToPay'
    //   263: ldc 'token'
    //   265: aload_1
    //   266: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   269: new java/lang/StringBuilder
    //   272: astore #7
    //   274: aload #7
    //   276: invokespecial <init> : ()V
    //   279: aload #7
    //   281: ldc '{"suspar1": "", "suspar2":"2", "suspar3":"'
    //   283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: pop
    //   287: aload #7
    //   289: aload #8
    //   291: invokevirtual getEMAIL : ()Ljava/lang/String;
    //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: aload #7
    //   300: ldc '", "suspar4":"'
    //   302: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: pop
    //   306: aload #7
    //   308: aload #5
    //   310: invokevirtual getSerialnumber : ()Ljava/lang/String;
    //   313: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: pop
    //   317: aload #7
    //   319: ldc '"}'
    //   321: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: pop
    //   325: aload #7
    //   327: invokevirtual toString : ()Ljava/lang/String;
    //   330: invokestatic EncryptMoipData : (Ljava/lang/String;)Ljava/lang/String;
    //   333: astore #8
    //   335: ldc '{"suspar1": "", "suspar2":"2", "suspar3":"testeoms1@gmail.com", "suspar4":"2445005"}'
    //   337: invokestatic EncryptMoipData : (Ljava/lang/String;)Ljava/lang/String;
    //   340: astore #7
    //   342: ldc '{"suspar1":"","suspar2":"1", "suspar3":"CGY", "suspar4":"2552833"}'
    //   344: invokestatic EncryptMoipData : (Ljava/lang/String;)Ljava/lang/String;
    //   347: astore #11
    //   349: ldc '{"suspar1":"3014","suspar2":"1", "suspar3":"CGY", "suspar4":"2552833"}'
    //   351: invokestatic EncryptData : (Ljava/lang/String;)Ljava/lang/String;
    //   354: astore #9
    //   356: ldc 'E4W0PwBYWTfj3SdnPmvRZ5mEMt4KIE9WFwlww2g8FcLwBXjYOZkkvuSJ5mSlV992pHeXonQEb4czVrailyePShmCo/AFnlNDS2HWU//p7mk='
    //   358: invokestatic DecryptMoip : (Ljava/lang/String;)Ljava/lang/String;
    //   361: astore #10
    //   363: ldc 'E4W0PwBYWTfj3SdnPmvRZ5mEMt4KIE9WFwlww2g8FcLwBXjYOZkkvuSJ5mSlV992pHeXonQEb4czVrailyePShmCo/AFnlNDS2HWU//p7mk='
    //   365: aload #11
    //   367: invokevirtual equals : (Ljava/lang/Object;)Z
    //   370: istore_3
    //   371: ldc ''
    //   373: astore #5
    //   375: iload_3
    //   376: ifeq -> 391
    //   379: ldc 'PMM AttemptToPay'
    //   381: ldc 'El encriptado es correcto'
    //   383: ldc ''
    //   385: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   388: goto -> 422
    //   391: ldc 'E4W0PwBYWTfj3SdnPmvRZ5mEMt4KIE9WFwlww2g8FcLwBXjYOZkkvuSJ5mSlV992pHeXonQEb4czVrailyePShmCo/AFnlNDS2HWU//p7mk='
    //   393: aload #9
    //   395: invokevirtual equals : (Ljava/lang/Object;)Z
    //   398: ifeq -> 413
    //   401: ldc 'PMM AttemptToPay'
    //   403: ldc 'El encriptado NORMAL'
    //   405: ldc ''
    //   407: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   410: goto -> 422
    //   413: ldc 'PMM AttemptToPay'
    //   415: ldc 'El encriptado NO es correcto'
    //   417: ldc ''
    //   419: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   422: ldc 'PMM AttemptToPay'
    //   424: ldc 'exampleDevice'
    //   426: aload #8
    //   428: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   431: ldc 'PMM AttemptToPay'
    //   433: ldc 'exampleSerialNumber'
    //   435: aload #7
    //   437: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   440: ldc 'PMM AttemptToPay'
    //   442: ldc 'exampleCarlos'
    //   444: aload #10
    //   446: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   449: ldc 'PMM AttemptToPay'
    //   451: ldc 'exampleEncriptandoCarlos'
    //   453: aload #11
    //   455: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   458: ldc 'PMM AttemptToPay'
    //   460: ldc 'responsePostMan'
    //   462: ldc 'qyHV4nzY+qCqDHmL+vKSVF6QQh4hbXguQcFf+KShFyyLa9MCgrTaQxvN19/v2r+Vhecrlo5VX+7CLoBe5bJbE1QvMSWsKtP16RwJuSYWOQw='
    //   464: invokestatic DecryptMoip : (Ljava/lang/String;)Ljava/lang/String;
    //   467: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   470: new java/util/ArrayList
    //   473: astore #9
    //   475: aload #9
    //   477: invokespecial <init> : ()V
    //   480: aload #9
    //   482: ldc 'TC'
    //   484: invokeinterface add : (Ljava/lang/Object;)Z
    //   489: pop
    //   490: new java/lang/StringBuilder
    //   493: astore #10
    //   495: aload #10
    //   497: invokespecial <init> : ()V
    //   500: aload #10
    //   502: ldc '"'
    //   504: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: pop
    //   508: aload #10
    //   510: aload #8
    //   512: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   515: pop
    //   516: aload #10
    //   518: ldc '"'
    //   520: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: pop
    //   524: aload #9
    //   526: aload #10
    //   528: invokevirtual toString : ()Ljava/lang/String;
    //   531: invokeinterface add : (Ljava/lang/Object;)Z
    //   536: pop
    //   537: new java/lang/StringBuilder
    //   540: astore #10
    //   542: aload #10
    //   544: invokespecial <init> : ()V
    //   547: aload #10
    //   549: ldc '"'
    //   551: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: pop
    //   555: aload #10
    //   557: aload #8
    //   559: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   562: pop
    //   563: aload #10
    //   565: ldc '"'
    //   567: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   570: pop
    //   571: ldc 'PMM AttemptToPay'
    //   573: ldc 'COSA ENVIADA'
    //   575: aload #10
    //   577: invokevirtual toString : ()Ljava/lang/String;
    //   580: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   583: new com/roadtrack/onstar/BO/JsonRequestObject
    //   586: astore #8
    //   588: new java/lang/StringBuilder
    //   591: astore #10
    //   593: aload #10
    //   595: invokespecial <init> : ()V
    //   598: aload #10
    //   600: getstatic com/roadtrack/onstar/BO/GlobalMembers.URLMoipPrefix : Ljava/lang/String;
    //   603: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   606: pop
    //   607: aload #10
    //   609: getstatic com/roadtrack/onstar/BO/GlobalMembers.MoipCreditCard : Ljava/lang/String;
    //   612: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: pop
    //   616: aload #8
    //   618: aload #10
    //   620: invokevirtual toString : ()Ljava/lang/String;
    //   623: ldc ''
    //   625: aload #9
    //   627: getstatic com/roadtrack/onstar/BO/GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF : I
    //   630: getstatic com/roadtrack/onstar/BO/GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF : I
    //   633: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/util/List;II)V
    //   636: aload #8
    //   638: iconst_1
    //   639: invokevirtual setPropertysAsSet : (Z)V
    //   642: aload #8
    //   644: ldc_w 2131623938
    //   647: getstatic com/roadtrack/onstar/BO/GlobalMembers.nameKeystoreMoip : Ljava/lang/String;
    //   650: invokevirtual setKeyStoreId : (ILjava/lang/String;)V
    //   653: new java/util/LinkedHashMap
    //   656: astore #9
    //   658: aload #9
    //   660: invokespecial <init> : ()V
    //   663: aload #9
    //   665: ldc_w 'Content-Type'
    //   668: ldc_w 'application/json'
    //   671: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   674: pop
    //   675: new java/lang/StringBuilder
    //   678: astore #10
    //   680: aload #10
    //   682: invokespecial <init> : ()V
    //   685: aload #10
    //   687: ldc_w 'Bearer '
    //   690: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   693: pop
    //   694: aload #10
    //   696: aload_1
    //   697: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: pop
    //   701: aload #9
    //   703: ldc_w 'Authorization'
    //   706: aload #10
    //   708: invokevirtual toString : ()Ljava/lang/String;
    //   711: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   714: pop
    //   715: aload #8
    //   717: aload #9
    //   719: invokevirtual setRequest_propertys : (Ljava/util/LinkedHashMap;)V
    //   722: new java/util/LinkedHashMap
    //   725: invokespecial <init> : ()V
    //   728: aload #9
    //   730: ldc_w 'Content-Type'
    //   733: ldc_w 'application/json'
    //   736: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   739: pop
    //   740: new java/lang/StringBuilder
    //   743: astore #10
    //   745: aload #10
    //   747: invokespecial <init> : ()V
    //   750: aload #10
    //   752: ldc_w 'Bearer '
    //   755: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   758: pop
    //   759: aload #10
    //   761: aload_1
    //   762: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   765: pop
    //   766: aload #9
    //   768: ldc_w 'Authorization'
    //   771: aload #10
    //   773: invokevirtual toString : ()Ljava/lang/String;
    //   776: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   779: pop
    //   780: aload #9
    //   782: ldc_w 'accept-charset'
    //   785: ldc_w 'utf-8'
    //   788: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   791: pop
    //   792: new java/util/LinkedHashMap
    //   795: astore_1
    //   796: aload_1
    //   797: invokespecial <init> : ()V
    //   800: aload_1
    //   801: ldc ''
    //   803: aload #7
    //   805: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   808: pop
    //   809: new com/roadtrack/onstar/BO/RequestManager
    //   812: astore_1
    //   813: aload_1
    //   814: aload_0
    //   815: getfield context : Landroid/content/Context;
    //   818: aload #8
    //   820: invokespecial <init> : (Landroid/content/Context;Lcom/roadtrack/onstar/BO/JsonRequestObject;)V
    //   823: aload_1
    //   824: iconst_5
    //   825: invokevirtual sendRequest : (I)V
    //   828: aload_1
    //   829: invokevirtual getResponseCode : ()I
    //   832: istore_2
    //   833: aload_1
    //   834: invokevirtual getRespuesta : ()Ljava/lang/String;
    //   837: astore_1
    //   838: ldc 'PMM AttemptToPay'
    //   840: ldc_w 'result'
    //   843: aload_1
    //   844: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   847: iload_2
    //   848: sipush #200
    //   851: if_icmpne -> 901
    //   854: aload_1
    //   855: invokestatic DecryptMoip : (Ljava/lang/String;)Ljava/lang/String;
    //   858: astore_1
    //   859: ldc 'PMM AttemptToPay'
    //   861: ldc_w 'result'
    //   864: aload_1
    //   865: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   868: new org/json/JSONObject
    //   871: astore #4
    //   873: aload #4
    //   875: aload_1
    //   876: invokespecial <init> : (Ljava/lang/String;)V
    //   879: aload #4
    //   881: ldc_w 'CheckoutUrl'
    //   884: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   887: astore_1
    //   888: aload #4
    //   890: ldc_w 'Status'
    //   893: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   896: astore #4
    //   898: goto -> 908
    //   901: aload #4
    //   903: astore_1
    //   904: aload #5
    //   906: astore #4
    //   908: aload #6
    //   910: aload_1
    //   911: invokeinterface add : (Ljava/lang/Object;)Z
    //   916: pop
    //   917: aload #6
    //   919: aload #4
    //   921: invokeinterface add : (Ljava/lang/Object;)Z
    //   926: pop
    //   927: getstatic com/roadtrack/onstar/async_tasks/tasks/GetURLChangeCreditCard.TAG : Ljava/lang/String;
    //   930: astore #4
    //   932: new java/lang/StringBuilder
    //   935: astore #5
    //   937: aload #5
    //   939: invokespecial <init> : ()V
    //   942: aload #5
    //   944: ldc_w ':'
    //   947: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   950: pop
    //   951: aload #5
    //   953: aload_1
    //   954: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   957: pop
    //   958: aload #4
    //   960: ldc_w 'WS result'
    //   963: aload #5
    //   965: invokevirtual toString : ()Ljava/lang/String;
    //   968: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   971: aload #6
    //   973: areturn
    //   974: aconst_null
    //   975: areturn
    //   976: aconst_null
    //   977: areturn
    //   978: astore_1
    //   979: getstatic com/roadtrack/onstar/async_tasks/tasks/GetURLChangeCreditCard.TAG : Ljava/lang/String;
    //   982: ldc_w 'Error'
    //   985: aload_1
    //   986: invokevirtual getMessage : ()Ljava/lang/String;
    //   989: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   992: aconst_null
    //   993: areturn
    // Exception table:
    //   from	to	target	type
    //   22	76	978	java/lang/Exception
    //   80	87	978	java/lang/Exception
    //   91	99	978	java/lang/Exception
    //   99	119	978	java/lang/Exception
    //   119	134	978	java/lang/Exception
    //   141	226	978	java/lang/Exception
    //   231	371	978	java/lang/Exception
    //   379	388	978	java/lang/Exception
    //   391	410	978	java/lang/Exception
    //   413	422	978	java/lang/Exception
    //   422	847	978	java/lang/Exception
    //   854	898	978	java/lang/Exception
    //   908	971	978	java/lang/Exception
  }
  
  protected void onPostExecute(List<String> paramList) {
    try {
      this.progressDialog.dismiss();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Close Progress Dialog", exception.getMessage());
    } 
    List<String> list = paramList;
    if (!isValidResponseStructure(paramList))
      list = null; 
    AsyncResponseList asyncResponseList = this.asyncResponse;
    if (asyncResponseList != null)
      asyncResponseList.processFinish(list); 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.please_wait, 2131690270);
    this.progressDialog = new ProgressDialog(this.context, 2131755173);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setCancelable(false);
    this.progressDialog.setMessage(str);
    this.progressDialog.show();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetURLChangeCreditCard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */