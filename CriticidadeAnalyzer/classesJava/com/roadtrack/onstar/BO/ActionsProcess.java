package com.roadtrack.onstar.BO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.ActionResultVO;
import com.roadtrack.onstar.VO.CustomActionButton;
import com.roadtrack.onstar.VO.NavigationVO;
import com.roadtrack.onstar.VO.PIDVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import com.roadtrack.onstar.ui.wakeUpCar.WakeUpCar;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.Utilities;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TimeZone;

public class ActionsProcess extends AsyncTask<String, String, String> {
  private String TAG = "ActionsProcess";
  
  private String accountId;
  
  private String ack;
  
  private Activity act;
  
  private CustomActionButton actionButton;
  
  private String actionId;
  
  private String address;
  
  private String applicationSourceId;
  
  private String aux_result;
  
  private Button btControl;
  
  private int buttonStatus;
  
  private Context context;
  
  private String csvParams;
  
  private DBFunctions dbFun;
  
  private String deviceCSVParams;
  
  private String deviceId;
  
  private double endTime;
  
  private String epochTmmeNewPositionMapBejoreGlm;
  
  private boolean extendGetcomman;
  
  private int gpsSttusNewPositionBejoreGlm;
  
  private long historicoId;
  
  private ImageView ivControl;
  
  private Float latSendComandBejoreGlm;
  
  private Float longSendComandBejoreGlm;
  
  OnPostExecuteListener mOnPosteExecuteListener;
  
  OnProgressUpdateListener mOnProgressUpdateListener;
  
  OnProgressUpdateListenerCustomActionButton mOnProgressUpdateListenerCustomButton;
  
  private String messageId = "0";
  
  private String outMsg;
  
  private double palomaTime;
  
  private String password;
  
  private ProgressBar pbControl;
  
  private boolean pidHasInfo;
  
  private boolean processFinished;
  
  private ActionResultVO result;
  
  private int sendCommandErrorCode = 0;
  
  private int sendCommandErrorCodeTimeOut = 408;
  
  private String sessionKey;
  
  private double speedLimit = 0.0D;
  
  private String sr;
  
  private double startTime;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private SwipeRefreshLayout swipeProgress;
  
  private TextView tvControl;
  
  private String userId;
  
  private String userName;
  
  public ActionsProcess(Context paramContext, ProgressBar paramProgressBar, ImageView paramImageView, String paramString1, String paramString2, Activity paramActivity) {
    Float float_ = Float.valueOf(0.0F);
    this.latSendComandBejoreGlm = float_;
    this.longSendComandBejoreGlm = float_;
    this.gpsSttusNewPositionBejoreGlm = -100;
    this.epochTmmeNewPositionMapBejoreGlm = "";
    this.address = "";
    this.aux_result = "";
    new Date();
    new Date();
    this.extendGetcomman = false;
    this.pidHasInfo = false;
    this.context = paramContext;
    this.pbControl = paramProgressBar;
    this.tvControl = null;
    this.ivControl = paramImageView;
    this.btControl = null;
    this.actionId = paramString1;
    this.deviceId = paramString2;
    this.extendGetcomman = false;
    this.act = paramActivity;
    this.dbFun = new DBFunctions(paramContext);
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  public ActionsProcess(Context paramContext, ProgressBar paramProgressBar, TextView paramTextView, String paramString1, String paramString2) {
    Float float_ = Float.valueOf(0.0F);
    this.latSendComandBejoreGlm = float_;
    this.longSendComandBejoreGlm = float_;
    this.gpsSttusNewPositionBejoreGlm = -100;
    this.epochTmmeNewPositionMapBejoreGlm = "";
    this.address = "";
    this.aux_result = "";
    new Date();
    new Date();
    this.extendGetcomman = false;
    this.pidHasInfo = false;
    this.context = paramContext;
    this.pbControl = paramProgressBar;
    this.tvControl = paramTextView;
    this.ivControl = null;
    this.btControl = null;
    this.actionId = paramString1;
    this.deviceId = paramString2;
    this.extendGetcomman = false;
    this.dbFun = new DBFunctions(paramContext);
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  public ActionsProcess(Context paramContext, SwipeRefreshLayout paramSwipeRefreshLayout, TextView paramTextView, String paramString1, String paramString2) {
    Float float_ = Float.valueOf(0.0F);
    this.latSendComandBejoreGlm = float_;
    this.longSendComandBejoreGlm = float_;
    this.gpsSttusNewPositionBejoreGlm = -100;
    this.epochTmmeNewPositionMapBejoreGlm = "";
    this.address = "";
    this.aux_result = "";
    new Date();
    new Date();
    this.extendGetcomman = false;
    this.pidHasInfo = false;
    this.context = paramContext;
    this.swipeProgress = paramSwipeRefreshLayout;
    this.tvControl = paramTextView;
    this.ivControl = null;
    this.btControl = null;
    this.actionId = paramString1;
    this.deviceId = paramString2;
    this.extendGetcomman = false;
    this.dbFun = new DBFunctions(paramContext);
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  public ActionsProcess(Context paramContext, CustomActionButton paramCustomActionButton, String paramString1, String paramString2, Activity paramActivity) {
    Float float_ = Float.valueOf(0.0F);
    this.latSendComandBejoreGlm = float_;
    this.longSendComandBejoreGlm = float_;
    this.gpsSttusNewPositionBejoreGlm = -100;
    this.epochTmmeNewPositionMapBejoreGlm = "";
    this.address = "";
    this.aux_result = "";
    new Date();
    new Date();
    this.extendGetcomman = false;
    this.pidHasInfo = false;
    this.context = paramContext;
    this.tvControl = null;
    this.btControl = null;
    this.actionId = paramString1;
    this.deviceId = paramString2;
    this.actionButton = paramCustomActionButton;
    this.act = paramActivity;
    this.dbFun = new DBFunctions(paramContext);
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  public ActionsProcess(Context paramContext, CustomActionButton paramCustomActionButton, String paramString1, String paramString2, Activity paramActivity, boolean paramBoolean) {
    Float float_ = Float.valueOf(0.0F);
    this.latSendComandBejoreGlm = float_;
    this.longSendComandBejoreGlm = float_;
    this.gpsSttusNewPositionBejoreGlm = -100;
    this.epochTmmeNewPositionMapBejoreGlm = "";
    this.address = "";
    this.aux_result = "";
    new Date();
    new Date();
    this.extendGetcomman = false;
    this.pidHasInfo = false;
    this.context = paramContext;
    this.tvControl = null;
    this.btControl = null;
    this.actionId = paramString1;
    this.deviceId = paramString2;
    this.actionButton = paramCustomActionButton;
    this.act = paramActivity;
    this.extendGetcomman = paramBoolean;
    this.dbFun = new DBFunctions(paramContext);
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  private boolean AdditionalTasks() {
    if (this.actionId.equals(Enums.Services.SendPNDNavigationCommand.GetCodeString())) {
      NavigationVO navigationVO = new NavigationVO();
      navigationVO.setLatEnd(this.result.getLatitude());
      navigationVO.setLonEnd(this.result.getLongitude());
      navigationVO.setAddress(this.address);
      navigationVO.setActionId(Enums.Services.SendPNDNavigationCommand.GetCode());
      navigationVO.setMessageId(Integer.valueOf(this.messageId).intValue());
      this.dbFun.addNavigation(navigationVO, new android.database.sqlite.SQLiteDatabase[0]);
    } 
    return false;
  }
  
  private void GetCommandStatus(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2, String paramString3, String paramString4, String paramString5, LinkedHashMap<String, Object> paramLinkedHashMap, String paramString6) {
    byte b;
    boolean bool;
    ActionResultVO actionResultVO;
    Date date = new Date();
    if (paramString1.equals(Enums.Services.FindMe.GetCodeString())) {
      b = 2;
    } else {
      b = 0;
    } 
    if (paramString1.equals(Enums.Services.pid.GetCodeString()))
      b = 6; 
    String str = "";
    do {
      String str1 = WS(b, paramString2, paramString3, paramString4, paramString5, paramInt1, paramLinkedHashMap);
      if (str1 != null && str1.length() > 0 && str1.contains("\\|"))
        str = str1; 
      actionResultVO = adjustWCFResult(this.context, Integer.valueOf(this.actionId).intValue(), str1, str, this.deviceId, paramString6);
      String str2 = this.TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.deviceId);
      stringBuilder.append(": action: ");
      stringBuilder.append(this.actionId);
      stringBuilder.append(" result: ");
      stringBuilder.append(actionResultVO.getIdResponse());
      Utilities.escribeArchivo(str2, "GetCommandStatus", stringBuilder.toString());
      if (!this.processFinished && (actionResultVO.getIdResponse() == Enums.ActionResultCode.Fail || actionResultVO.getIdResponse() == Enums.ActionResultCode.Activated || actionResultVO.getIdResponse() == Enums.ActionResultCode.OnMovement || paramString1.equals(Enums.Services.pid.GetCodeString()) || paramString1.equals(Enums.Services.DTCAction.GetCodeString())))
        this.processFinished = true; 
      if (!this.processFinished) {
        long l = paramInt2;
        try {
          Thread.sleep(l);
        } catch (InterruptedException interruptedException) {
          String str3 = this.TAG;
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(this.deviceId);
          stringBuilder1.append(": ");
          stringBuilder1.append(interruptedException.getMessage());
          Utilities.escribeArchivo(str3, "Error: Timer", stringBuilder1.toString());
        } 
      } 
      if (getTimeDifference(null, date, 13) < paramInt3 && actionResultVO.getIdResponse() != Enums.ActionResultCode.Activated) {
        bool = true;
      } else {
        bool = false;
      } 
    } while (bool && !this.processFinished);
    if (this.actionId.equals(Enums.Services.FindMe.GetCodeString()) && actionResultVO.getLatitude() != 0.0D && actionResultVO.getLongitude() != 0.0D) {
      this.processFinished = true;
      actionResultVO.setIdResponse(Enums.ActionResultCode.Activated);
    } 
  }
  
  private String[] GetEndMessage(int paramInt, String paramString) {
    // Byte code:
    //   0: iconst_3
    //   1: anewarray java/lang/String
    //   4: dup
    //   5: iconst_0
    //   6: ldc '0'
    //   8: aastore
    //   9: dup
    //   10: iconst_1
    //   11: ldc ''
    //   13: aastore
    //   14: dup
    //   15: iconst_2
    //   16: ldc '0'
    //   18: aastore
    //   19: astore #10
    //   21: aload_0
    //   22: getfield actionId : Ljava/lang/String;
    //   25: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   28: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   31: invokevirtual equals : (Ljava/lang/Object;)Z
    //   34: ifeq -> 44
    //   37: ldc_w 2131165624
    //   40: istore_3
    //   41: goto -> 48
    //   44: ldc_w 2131165620
    //   47: istore_3
    //   48: aload_0
    //   49: getfield context : Landroid/content/Context;
    //   52: aload_0
    //   53: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   56: getfield global_lbl_acciondescfallared_1 : Ljava/lang/String;
    //   59: ldc_w 2131689862
    //   62: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   65: astore #15
    //   67: aload_0
    //   68: getfield context : Landroid/content/Context;
    //   71: aload_0
    //   72: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   75: getfield global_lbl_acciondescposiblenotif_1 : Ljava/lang/String;
    //   78: ldc_w 2131689867
    //   81: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   84: astore #8
    //   86: aload_0
    //   87: getfield context : Landroid/content/Context;
    //   90: aload_0
    //   91: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   94: getfield global_status_lbl_noejecutado_1 : Ljava/lang/String;
    //   97: ldc_w 2131689971
    //   100: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   103: astore #7
    //   105: aload_0
    //   106: getfield context : Landroid/content/Context;
    //   109: aload_0
    //   110: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   113: getfield global_lbl_accionstatusnoejecutado_1 : Ljava/lang/String;
    //   116: ldc_w 2131689900
    //   119: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   122: astore #13
    //   124: aload_0
    //   125: getfield context : Landroid/content/Context;
    //   128: aload_0
    //   129: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   132: getfield actionResultWaitParking : Ljava/lang/String;
    //   135: ldc_w 2131689622
    //   138: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   141: astore #11
    //   143: aload_0
    //   144: getfield context : Landroid/content/Context;
    //   147: aload_0
    //   148: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   151: getfield actionResultWait : Ljava/lang/String;
    //   154: ldc_w 2131689621
    //   157: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   160: astore #12
    //   162: aload_0
    //   163: getfield context : Landroid/content/Context;
    //   166: aload_0
    //   167: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   170: getfield actionResultWaitSpeed : Ljava/lang/String;
    //   173: ldc_w 2131689623
    //   176: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   179: astore #14
    //   181: aload_0
    //   182: getfield context : Landroid/content/Context;
    //   185: aload_0
    //   186: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   189: getfield action_parkingrequested : Ljava/lang/String;
    //   192: ldc_w 2131689632
    //   195: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   198: astore #19
    //   200: aload_0
    //   201: getfield context : Landroid/content/Context;
    //   204: aload_0
    //   205: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   208: getfield action_speedrequested : Ljava/lang/String;
    //   211: ldc_w 2131689634
    //   214: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   217: astore #16
    //   219: aload_0
    //   220: getfield context : Landroid/content/Context;
    //   223: aload_0
    //   224: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   227: getfield global_lbl_acciondescvehiculomovimiento_1 : Ljava/lang/String;
    //   230: ldc_w 2131689871
    //   233: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   236: astore #17
    //   238: aload_0
    //   239: getfield context : Landroid/content/Context;
    //   242: aload_0
    //   243: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   246: getfield action_lockdoorsmoving : Ljava/lang/String;
    //   249: ldc_w 2131689627
    //   252: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   255: astore #18
    //   257: aload_0
    //   258: getfield context : Landroid/content/Context;
    //   261: aload_0
    //   262: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   265: getfield action_unlockdoorsmoving : Ljava/lang/String;
    //   268: ldc_w 2131689635
    //   271: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   274: astore #20
    //   276: aload_0
    //   277: getfield processFinished : Z
    //   280: istore #6
    //   282: ldc_w 2131165687
    //   285: istore #5
    //   287: ldc_w 'PALOMA 2 AZUL'
    //   290: astore #9
    //   292: iload #6
    //   294: ifne -> 575
    //   297: aload_0
    //   298: getfield actionId : Ljava/lang/String;
    //   301: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   304: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   307: invokevirtual equals : (Ljava/lang/Object;)Z
    //   310: ifeq -> 320
    //   313: ldc_w 2131165583
    //   316: istore_3
    //   317: goto -> 324
    //   320: ldc_w 2131165581
    //   323: istore_3
    //   324: aload_0
    //   325: getfield messageId : Ljava/lang/String;
    //   328: ldc '0'
    //   330: invokevirtual equals : (Ljava/lang/Object;)Z
    //   333: ifeq -> 454
    //   336: iload_3
    //   337: istore #4
    //   339: aload_0
    //   340: getfield sendCommandErrorCode : I
    //   343: aload_0
    //   344: getfield sendCommandErrorCodeTimeOut : I
    //   347: if_icmpne -> 444
    //   350: aload_0
    //   351: getfield actionId : Ljava/lang/String;
    //   354: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   357: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   360: invokevirtual equals : (Ljava/lang/Object;)Z
    //   363: ifeq -> 372
    //   366: iload #5
    //   368: istore_3
    //   369: goto -> 376
    //   372: ldc_w 2131165684
    //   375: istore_3
    //   376: aload_0
    //   377: getfield actionId : Ljava/lang/String;
    //   380: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   383: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   386: invokevirtual equals : (Ljava/lang/Object;)Z
    //   389: ifeq -> 399
    //   392: ldc_w 2131165581
    //   395: istore_3
    //   396: goto -> 399
    //   399: aload_0
    //   400: getfield actionId : Ljava/lang/String;
    //   403: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   406: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   409: invokevirtual equals : (Ljava/lang/Object;)Z
    //   412: ifne -> 441
    //   415: aload_0
    //   416: getfield actionId : Ljava/lang/String;
    //   419: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   422: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   425: invokevirtual equals : (Ljava/lang/Object;)Z
    //   428: ifeq -> 434
    //   431: goto -> 441
    //   434: aload #8
    //   436: astore #7
    //   438: goto -> 555
    //   441: goto -> 555
    //   444: aload #15
    //   446: astore #7
    //   448: iload #4
    //   450: istore_3
    //   451: goto -> 567
    //   454: aload_2
    //   455: ldc_w '3'
    //   458: invokevirtual equals : (Ljava/lang/Object;)Z
    //   461: ifne -> 563
    //   464: aload_0
    //   465: getfield actionId : Ljava/lang/String;
    //   468: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   471: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   474: invokevirtual equals : (Ljava/lang/Object;)Z
    //   477: ifne -> 502
    //   480: aload_0
    //   481: getfield actionId : Ljava/lang/String;
    //   484: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   487: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   490: invokevirtual equals : (Ljava/lang/Object;)Z
    //   493: ifeq -> 499
    //   496: goto -> 502
    //   499: goto -> 506
    //   502: aload #7
    //   504: astore #8
    //   506: aload #8
    //   508: astore #7
    //   510: iload_1
    //   511: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   514: invokevirtual GetCode : ()I
    //   517: if_icmpeq -> 555
    //   520: aload_0
    //   521: getfield actionId : Ljava/lang/String;
    //   524: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   527: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   530: invokevirtual equals : (Ljava/lang/Object;)Z
    //   533: ifeq -> 547
    //   536: ldc_w 2131165687
    //   539: istore_3
    //   540: aload #8
    //   542: astore #7
    //   544: goto -> 555
    //   547: ldc_w 2131165684
    //   550: istore_3
    //   551: aload #8
    //   553: astore #7
    //   555: ldc_w 'PALOMA GRIS'
    //   558: astore #8
    //   560: goto -> 998
    //   563: aload #13
    //   565: astore #7
    //   567: ldc_w 'TACHE'
    //   570: astore #8
    //   572: goto -> 998
    //   575: aload_2
    //   576: ldc_w '2'
    //   579: invokevirtual equals : (Ljava/lang/Object;)Z
    //   582: ifeq -> 630
    //   585: iload_1
    //   586: getstatic com/roadtrack/onstar/Enums$Services.Speed : Lcom/roadtrack/onstar/Enums$Services;
    //   589: invokevirtual GetCode : ()I
    //   592: if_icmpne -> 606
    //   595: aload #14
    //   597: astore #7
    //   599: aload #9
    //   601: astore #8
    //   603: goto -> 998
    //   606: iload_1
    //   607: getstatic com/roadtrack/onstar/Enums$Services.Parking : Lcom/roadtrack/onstar/Enums$Services;
    //   610: invokevirtual GetCode : ()I
    //   613: if_icmpne -> 623
    //   616: aload #11
    //   618: astore #7
    //   620: goto -> 599
    //   623: aload #12
    //   625: astore #7
    //   627: goto -> 599
    //   630: aload_2
    //   631: ldc_w '3'
    //   634: invokevirtual equals : (Ljava/lang/Object;)Z
    //   637: ifeq -> 689
    //   640: aload_0
    //   641: getfield actionId : Ljava/lang/String;
    //   644: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   647: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   650: invokevirtual equals : (Ljava/lang/Object;)Z
    //   653: ifeq -> 663
    //   656: ldc_w 2131165583
    //   659: istore_3
    //   660: goto -> 667
    //   663: ldc_w 2131165581
    //   666: istore_3
    //   667: iload_3
    //   668: istore #4
    //   670: aload_0
    //   671: getfield actionId : Ljava/lang/String;
    //   674: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   677: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   680: invokevirtual equals : (Ljava/lang/Object;)Z
    //   683: ifeq -> 444
    //   686: goto -> 567
    //   689: aload_2
    //   690: ldc_w '15'
    //   693: invokevirtual equals : (Ljava/lang/Object;)Z
    //   696: ifeq -> 772
    //   699: iload_1
    //   700: getstatic com/roadtrack/onstar/Enums$Services.Parking : Lcom/roadtrack/onstar/Enums$Services;
    //   703: invokevirtual GetCode : ()I
    //   706: if_icmpeq -> 760
    //   709: iload_1
    //   710: getstatic com/roadtrack/onstar/Enums$Services.Speed : Lcom/roadtrack/onstar/Enums$Services;
    //   713: invokevirtual GetCode : ()I
    //   716: if_icmpne -> 722
    //   719: goto -> 760
    //   722: iload_1
    //   723: getstatic com/roadtrack/onstar/Enums$Services.Parking : Lcom/roadtrack/onstar/Enums$Services;
    //   726: invokevirtual GetCode : ()I
    //   729: if_icmpne -> 739
    //   732: aload #19
    //   734: astore #7
    //   736: goto -> 599
    //   739: iload_1
    //   740: getstatic com/roadtrack/onstar/Enums$Services.Speed : Lcom/roadtrack/onstar/Enums$Services;
    //   743: invokevirtual GetCode : ()I
    //   746: if_icmpne -> 991
    //   749: aload #16
    //   751: astore #7
    //   753: aload #9
    //   755: astore #8
    //   757: goto -> 998
    //   760: ldc_w 2131165632
    //   763: istore_3
    //   764: ldc_w 'PALOMA 2 AZUL ALERTA'
    //   767: astore #8
    //   769: goto -> 995
    //   772: aload_2
    //   773: ldc_w '16'
    //   776: invokevirtual equals : (Ljava/lang/Object;)Z
    //   779: ifeq -> 888
    //   782: ldc_w 2131165301
    //   785: istore_3
    //   786: aload_0
    //   787: getfield actionId : Ljava/lang/String;
    //   790: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   793: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   796: invokevirtual equals : (Ljava/lang/Object;)Z
    //   799: ifeq -> 806
    //   802: ldc_w 2131165302
    //   805: istore_3
    //   806: iload_1
    //   807: getstatic com/roadtrack/onstar/Enums$Services.HornLigths : Lcom/roadtrack/onstar/Enums$Services;
    //   810: invokevirtual GetCode : ()I
    //   813: if_icmpne -> 819
    //   816: goto -> 829
    //   819: iload_1
    //   820: getstatic com/roadtrack/onstar/Enums$Services.Ligths : Lcom/roadtrack/onstar/Enums$Services;
    //   823: invokevirtual GetCode : ()I
    //   826: if_icmpne -> 841
    //   829: aload #17
    //   831: astore #7
    //   833: ldc_w 'EN MOVIMIENTO'
    //   836: astore #8
    //   838: goto -> 998
    //   841: iload_1
    //   842: getstatic com/roadtrack/onstar/Enums$Services.DoorsLock : Lcom/roadtrack/onstar/Enums$Services;
    //   845: invokevirtual GetCode : ()I
    //   848: if_icmpne -> 858
    //   851: aload #18
    //   853: astore #7
    //   855: goto -> 833
    //   858: iload_1
    //   859: getstatic com/roadtrack/onstar/Enums$Services.DoorsUnlock : Lcom/roadtrack/onstar/Enums$Services;
    //   862: invokevirtual GetCode : ()I
    //   865: if_icmpne -> 880
    //   868: aload #20
    //   870: astore #7
    //   872: ldc_w 'EN MOVIMIENTO'
    //   875: astore #8
    //   877: goto -> 998
    //   880: ldc_w 'EN MOVIMIENTO'
    //   883: astore #8
    //   885: goto -> 995
    //   888: aload_2
    //   889: ldc '0'
    //   891: invokevirtual equals : (Ljava/lang/Object;)Z
    //   894: ifeq -> 991
    //   897: aload_0
    //   898: getfield actionId : Ljava/lang/String;
    //   901: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   904: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   907: invokevirtual equals : (Ljava/lang/Object;)Z
    //   910: ifne -> 939
    //   913: aload_0
    //   914: getfield actionId : Ljava/lang/String;
    //   917: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   920: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   923: invokevirtual equals : (Ljava/lang/Object;)Z
    //   926: ifeq -> 932
    //   929: goto -> 939
    //   932: aload #8
    //   934: astore #7
    //   936: goto -> 939
    //   939: aload_0
    //   940: getfield actionId : Ljava/lang/String;
    //   943: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   946: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   949: invokevirtual equals : (Ljava/lang/Object;)Z
    //   952: ifeq -> 961
    //   955: iload #5
    //   957: istore_3
    //   958: goto -> 965
    //   961: ldc_w 2131165684
    //   964: istore_3
    //   965: aload_0
    //   966: getfield actionId : Ljava/lang/String;
    //   969: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   972: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   975: invokevirtual equals : (Ljava/lang/Object;)Z
    //   978: ifeq -> 988
    //   981: ldc_w 2131165581
    //   984: istore_3
    //   985: goto -> 555
    //   988: goto -> 555
    //   991: aload #9
    //   993: astore #8
    //   995: aconst_null
    //   996: astore #7
    //   998: aload #10
    //   1000: iconst_0
    //   1001: iload_3
    //   1002: invokestatic valueOf : (I)Ljava/lang/String;
    //   1005: aastore
    //   1006: aload #10
    //   1008: iconst_1
    //   1009: aload #8
    //   1011: aastore
    //   1012: aload #10
    //   1014: iconst_2
    //   1015: aload #7
    //   1017: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   1020: aastore
    //   1021: aload #10
    //   1023: astore #7
    //   1025: aload_0
    //   1026: getfield actionId : Ljava/lang/String;
    //   1029: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   1032: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1035: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1038: ifeq -> 1049
    //   1041: aload_0
    //   1042: iload_1
    //   1043: aload_2
    //   1044: invokespecial GetIconBanerDTC : (ILjava/lang/String;)[Ljava/lang/String;
    //   1047: astore #7
    //   1049: aload #7
    //   1051: areturn
  }
  
  private String[] GetIconBanerDTC(int paramInt, String paramString) {
    String str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    String str2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_acciondescposiblenotif_1, 2131689867);
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "0";
    arrayOfString[1] = "";
    arrayOfString[2] = "0";
    boolean bool = this.processFinished;
    String str3 = "TACHE";
    paramInt = 2131165583;
    if (!bool) {
      if (this.messageId.equals("0") && this.sendCommandErrorCode == this.sendCommandErrorCodeTimeOut) {
        str1 = str2;
        paramString = "TIME OUT";
        paramInt = 2131165687;
      } 
    } else {
      if (paramString.equals("3") && this.result.getSendCommandResultId() == Enums.WSResultId.OK) {
        paramString = str3;
      } else {
        if (paramString.equals("15") && this.result.getSendCommandResultId() == Enums.WSResultId.NoResult) {
          Utilities.SendNotify(this.context, this.actionId, "title", str2);
        } else if (paramString.equals("0") && (this.result.getSendCommandResultId() == Enums.WSResultId.NoResult || this.result.getSendCommandResultId() == Enums.WSResultId.OK)) {
          Utilities.SendNotify(this.context, this.actionId, "title", str2);
        } else {
          if (paramString.equals("3") && this.result.getSendCommandResultId() == Enums.WSResultId.NoResult) {
            paramString = str3;
            arrayOfString[0] = String.valueOf(paramInt);
            arrayOfString[1] = paramString;
            arrayOfString[2] = String.valueOf(str1);
            return arrayOfString;
          } 
          paramInt = 2131165624;
          paramString = "PALOMA 2 AZUL";
          str1 = null;
        } 
        str1 = str2;
        paramString = "TIME OUT";
        paramInt = 2131165687;
      } 
      arrayOfString[0] = String.valueOf(paramInt);
      arrayOfString[1] = paramString;
      arrayOfString[2] = String.valueOf(str1);
      return arrayOfString;
    } 
    paramInt = 2131165624;
    paramString = "PALOMA 2 AZUL";
    str1 = null;
  }
  
  private String[] SendCommand(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2, String paramString3, String paramString4, String paramString5, LinkedHashMap<String, Object> paramLinkedHashMap) {
    // Byte code:
    //   0: new java/util/Date
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #18
    //   9: ldc_w '0|0|0'
    //   12: astore #17
    //   14: iconst_3
    //   15: anewarray java/lang/String
    //   18: dup
    //   19: iconst_0
    //   20: ldc '0'
    //   22: aastore
    //   23: dup
    //   24: iconst_1
    //   25: ldc '0'
    //   27: aastore
    //   28: dup
    //   29: iconst_2
    //   30: ldc '0'
    //   32: aastore
    //   33: astore #16
    //   35: iconst_1
    //   36: istore #10
    //   38: aload #16
    //   40: astore #15
    //   42: aload_0
    //   43: iconst_0
    //   44: putfield sendCommandErrorCode : I
    //   47: aload #16
    //   49: astore #15
    //   51: getstatic com/roadtrack/onstar/BO/GlobalMembers.URL_WCF : Ljava/lang/String;
    //   54: aload_0
    //   55: getfield context : Landroid/content/Context;
    //   58: ldc_w 2131623963
    //   61: getstatic com/roadtrack/onstar/BO/GlobalMembers.nameKeystoreServiceWS : Ljava/lang/String;
    //   64: invokestatic setUpHttpsConnection : (Ljava/lang/String;Landroid/content/Context;ILjava/lang/String;)Z
    //   67: ifeq -> 95
    //   70: aload #16
    //   72: astore #15
    //   74: aload_0
    //   75: iconst_0
    //   76: aload #5
    //   78: aload #6
    //   80: aload #7
    //   82: aload #8
    //   84: iload_2
    //   85: aload #9
    //   87: invokespecial WS : (ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/LinkedHashMap;)Ljava/lang/String;
    //   90: astore #17
    //   92: goto -> 106
    //   95: aload #16
    //   97: astore #15
    //   99: aload_0
    //   100: sipush #500
    //   103: putfield sendCommandErrorCode : I
    //   106: aload #16
    //   108: astore #15
    //   110: aload_0
    //   111: getfield TAG : Ljava/lang/String;
    //   114: astore_1
    //   115: aload #16
    //   117: astore #15
    //   119: new java/lang/StringBuilder
    //   122: astore #19
    //   124: aload #16
    //   126: astore #15
    //   128: aload #19
    //   130: invokespecial <init> : ()V
    //   133: aload #16
    //   135: astore #15
    //   137: aload #19
    //   139: aload_0
    //   140: getfield deviceId : Ljava/lang/String;
    //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: pop
    //   147: aload #16
    //   149: astore #15
    //   151: aload #19
    //   153: ldc_w ': action: '
    //   156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload #16
    //   162: astore #15
    //   164: aload #19
    //   166: aload_0
    //   167: getfield actionId : Ljava/lang/String;
    //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload #16
    //   176: astore #15
    //   178: aload #19
    //   180: ldc_w ' result: '
    //   183: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: aload #16
    //   189: astore #15
    //   191: aload #19
    //   193: aload #17
    //   195: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: pop
    //   199: aload #16
    //   201: astore #15
    //   203: aload_1
    //   204: ldc_w 'SENDCOMMAND'
    //   207: aload #19
    //   209: invokevirtual toString : ()Ljava/lang/String;
    //   212: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   215: aload #16
    //   217: astore_1
    //   218: aload #17
    //   220: ifnull -> 434
    //   223: aload #16
    //   225: astore_1
    //   226: aload #16
    //   228: astore #15
    //   230: aload #17
    //   232: invokevirtual length : ()I
    //   235: ifle -> 434
    //   238: aload #16
    //   240: astore_1
    //   241: aload #16
    //   243: astore #15
    //   245: aload #17
    //   247: ldc_w '|'
    //   250: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   253: ifeq -> 434
    //   256: aload #16
    //   258: astore #15
    //   260: aload #17
    //   262: ldc_w '\|'
    //   265: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   268: astore #16
    //   270: aload #16
    //   272: astore_1
    //   273: aload #16
    //   275: astore #15
    //   277: aload #16
    //   279: arraylength
    //   280: iconst_3
    //   281: if_icmplt -> 434
    //   284: iload #10
    //   286: istore #11
    //   288: aload #16
    //   290: iconst_2
    //   291: aaload
    //   292: ifnull -> 405
    //   295: iload #10
    //   297: istore #11
    //   299: aload #16
    //   301: astore #15
    //   303: aload_0
    //   304: getfield deviceId : Ljava/lang/String;
    //   307: ifnull -> 405
    //   310: iload #10
    //   312: istore #11
    //   314: aload #16
    //   316: astore #15
    //   318: aload_0
    //   319: getfield accountId : Ljava/lang/String;
    //   322: ifnull -> 405
    //   325: iload #10
    //   327: istore #11
    //   329: aload #16
    //   331: astore #15
    //   333: aload_0
    //   334: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   337: aload_0
    //   338: getfield accountId : Ljava/lang/String;
    //   341: aload_0
    //   342: getfield deviceId : Ljava/lang/String;
    //   345: aload #16
    //   347: iconst_2
    //   348: aaload
    //   349: invokevirtual toString : ()Ljava/lang/String;
    //   352: iconst_0
    //   353: invokevirtual userDataTableHandler : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Z
    //   356: ifeq -> 405
    //   359: aload #16
    //   361: iconst_0
    //   362: ldc '0'
    //   364: aastore
    //   365: aload #16
    //   367: iconst_1
    //   368: ldc '0'
    //   370: aastore
    //   371: aload #16
    //   373: iconst_2
    //   374: ldc '0'
    //   376: aastore
    //   377: aload #16
    //   379: iconst_3
    //   380: ldc '0'
    //   382: aastore
    //   383: aload #16
    //   385: iconst_4
    //   386: ldc '0'
    //   388: aastore
    //   389: aload #16
    //   391: iconst_5
    //   392: ldc '0'
    //   394: aastore
    //   395: aload #16
    //   397: bipush #6
    //   399: ldc '0'
    //   401: aastore
    //   402: iconst_0
    //   403: istore #11
    //   405: aload #16
    //   407: astore #15
    //   409: aload #16
    //   411: iconst_2
    //   412: aaload
    //   413: ldc_w '1'
    //   416: invokevirtual equals : (Ljava/lang/Object;)Z
    //   419: ifeq -> 425
    //   422: iconst_0
    //   423: istore #11
    //   425: iconst_1
    //   426: istore #10
    //   428: aload #16
    //   430: astore_1
    //   431: goto -> 445
    //   434: iconst_0
    //   435: istore #12
    //   437: iload #10
    //   439: istore #11
    //   441: iload #12
    //   443: istore #10
    //   445: iload #11
    //   447: ifeq -> 591
    //   450: iload #10
    //   452: ifne -> 491
    //   455: aload_1
    //   456: iconst_0
    //   457: ldc '0'
    //   459: aastore
    //   460: aload_1
    //   461: iconst_1
    //   462: ldc '0'
    //   464: aastore
    //   465: aload_1
    //   466: iconst_2
    //   467: ldc '0'
    //   469: aastore
    //   470: aload_1
    //   471: iconst_3
    //   472: ldc '0'
    //   474: aastore
    //   475: aload_1
    //   476: iconst_4
    //   477: ldc '0'
    //   479: aastore
    //   480: aload_1
    //   481: iconst_5
    //   482: ldc '0'
    //   484: aastore
    //   485: aload_1
    //   486: bipush #6
    //   488: ldc '0'
    //   490: aastore
    //   491: iload_3
    //   492: i2l
    //   493: lstore #13
    //   495: aload_1
    //   496: astore #15
    //   498: lload #13
    //   500: invokestatic sleep : (J)V
    //   503: goto -> 591
    //   506: astore #19
    //   508: aload_1
    //   509: astore #15
    //   511: aload_0
    //   512: getfield TAG : Ljava/lang/String;
    //   515: astore #20
    //   517: aload_1
    //   518: astore #15
    //   520: new java/lang/StringBuilder
    //   523: astore #16
    //   525: aload_1
    //   526: astore #15
    //   528: aload #16
    //   530: invokespecial <init> : ()V
    //   533: aload_1
    //   534: astore #15
    //   536: aload #16
    //   538: aload_0
    //   539: getfield deviceId : Ljava/lang/String;
    //   542: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: pop
    //   546: aload_1
    //   547: astore #15
    //   549: aload #16
    //   551: ldc_w ': '
    //   554: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   557: pop
    //   558: aload_1
    //   559: astore #15
    //   561: aload #16
    //   563: aload #19
    //   565: invokevirtual getMessage : ()Ljava/lang/String;
    //   568: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: pop
    //   572: aload_1
    //   573: astore #15
    //   575: aload #20
    //   577: ldc_w 'Error: Timer'
    //   580: aload #16
    //   582: invokevirtual toString : ()Ljava/lang/String;
    //   585: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   588: goto -> 591
    //   591: aload_1
    //   592: astore #15
    //   594: aload_0
    //   595: aconst_null
    //   596: aload #18
    //   598: bipush #13
    //   600: invokespecial getTimeDifference : (Ljava/util/Date;Ljava/util/Date;I)J
    //   603: lstore #13
    //   605: lload #13
    //   607: iload #4
    //   609: i2l
    //   610: lcmp
    //   611: ifge -> 625
    //   614: iload #11
    //   616: ifeq -> 625
    //   619: iconst_1
    //   620: istore #11
    //   622: goto -> 628
    //   625: iconst_0
    //   626: istore #11
    //   628: iload #11
    //   630: istore #10
    //   632: aload_1
    //   633: astore #16
    //   635: iload #11
    //   637: ifne -> 38
    //   640: goto -> 704
    //   643: astore #5
    //   645: aload_0
    //   646: getfield TAG : Ljava/lang/String;
    //   649: astore_1
    //   650: new java/lang/StringBuilder
    //   653: dup
    //   654: invokespecial <init> : ()V
    //   657: astore #6
    //   659: aload #6
    //   661: aload_0
    //   662: getfield deviceId : Ljava/lang/String;
    //   665: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   668: pop
    //   669: aload #6
    //   671: ldc_w ': '
    //   674: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   677: pop
    //   678: aload #6
    //   680: aload #5
    //   682: invokevirtual getMessage : ()Ljava/lang/String;
    //   685: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: pop
    //   689: aload_1
    //   690: ldc_w 'Error: SENDCOMAND'
    //   693: aload #6
    //   695: invokevirtual toString : ()Ljava/lang/String;
    //   698: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   701: aload #15
    //   703: astore_1
    //   704: aload_1
    //   705: areturn
    // Exception table:
    //   from	to	target	type
    //   42	47	643	java/lang/Exception
    //   51	70	643	java/lang/Exception
    //   74	92	643	java/lang/Exception
    //   99	106	643	java/lang/Exception
    //   110	115	643	java/lang/Exception
    //   119	124	643	java/lang/Exception
    //   128	133	643	java/lang/Exception
    //   137	147	643	java/lang/Exception
    //   151	160	643	java/lang/Exception
    //   164	174	643	java/lang/Exception
    //   178	187	643	java/lang/Exception
    //   191	199	643	java/lang/Exception
    //   203	215	643	java/lang/Exception
    //   230	238	643	java/lang/Exception
    //   245	256	643	java/lang/Exception
    //   260	270	643	java/lang/Exception
    //   277	284	643	java/lang/Exception
    //   303	310	643	java/lang/Exception
    //   318	325	643	java/lang/Exception
    //   333	359	643	java/lang/Exception
    //   409	422	643	java/lang/Exception
    //   498	503	506	java/lang/InterruptedException
    //   498	503	643	java/lang/Exception
    //   511	517	643	java/lang/Exception
    //   520	525	643	java/lang/Exception
    //   528	533	643	java/lang/Exception
    //   536	546	643	java/lang/Exception
    //   549	558	643	java/lang/Exception
    //   561	572	643	java/lang/Exception
    //   575	588	643	java/lang/Exception
    //   594	605	643	java/lang/Exception
  }
  
  private void Socket(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, int paramInt3, String paramString4, String paramString5) {
    // Byte code:
    //   0: iconst_1
    //   1: anewarray java/lang/String
    //   4: astore #27
    //   6: aload #27
    //   8: iconst_0
    //   9: aload #4
    //   11: aastore
    //   12: new java/util/Date
    //   15: dup
    //   16: invokespecial <init> : ()V
    //   19: astore #28
    //   21: aconst_null
    //   22: astore #22
    //   24: aconst_null
    //   25: astore #21
    //   27: aload #21
    //   29: astore #19
    //   31: aload #19
    //   33: astore #18
    //   35: iconst_1
    //   36: istore #9
    //   38: iconst_1
    //   39: istore #10
    //   41: iload #9
    //   43: ifeq -> 1550
    //   46: aload #22
    //   48: astore #26
    //   50: aload #21
    //   52: astore #23
    //   54: aload #19
    //   56: astore #24
    //   58: aload #18
    //   60: astore #25
    //   62: new java/net/Socket
    //   65: astore #20
    //   67: aload #22
    //   69: astore #26
    //   71: aload #21
    //   73: astore #23
    //   75: aload #19
    //   77: astore #24
    //   79: aload #18
    //   81: astore #25
    //   83: aload #20
    //   85: invokespecial <init> : ()V
    //   88: new java/net/InetSocketAddress
    //   91: astore #22
    //   93: aload #22
    //   95: aload #5
    //   97: iload #6
    //   99: invokespecial <init> : (Ljava/lang/String;I)V
    //   102: aload #20
    //   104: aconst_null
    //   105: invokevirtual bind : (Ljava/net/SocketAddress;)V
    //   108: aload #20
    //   110: getstatic com/roadtrack/onstar/BO/GlobalMembers.SOCKET_READ_TIMEOUT : I
    //   113: invokevirtual setSoTimeout : (I)V
    //   116: aload #20
    //   118: aload #22
    //   120: iload_2
    //   121: invokevirtual connect : (Ljava/net/SocketAddress;I)V
    //   124: aload #20
    //   126: invokevirtual isConnected : ()Z
    //   129: ifeq -> 1184
    //   132: new java/io/BufferedWriter
    //   135: astore #22
    //   137: new java/io/OutputStreamWriter
    //   140: astore #23
    //   142: aload #23
    //   144: aload #20
    //   146: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   149: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   152: aload #22
    //   154: aload #23
    //   156: invokespecial <init> : (Ljava/io/Writer;)V
    //   159: new java/lang/StringBuilder
    //   162: astore #21
    //   164: aload #21
    //   166: invokespecial <init> : ()V
    //   169: aload #21
    //   171: ldc_w 'AC:'
    //   174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: aload #21
    //   180: aload #27
    //   182: iconst_0
    //   183: aaload
    //   184: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: pop
    //   188: aload_0
    //   189: aload #21
    //   191: invokevirtual toString : ()Ljava/lang/String;
    //   194: putfield outMsg : Ljava/lang/String;
    //   197: aload_0
    //   198: aload_0
    //   199: getfield outMsg : Ljava/lang/String;
    //   202: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   205: putfield outMsg : Ljava/lang/String;
    //   208: aload #22
    //   210: aload_0
    //   211: getfield outMsg : Ljava/lang/String;
    //   214: invokevirtual write : (Ljava/lang/String;)V
    //   217: aload #22
    //   219: invokevirtual flush : ()V
    //   222: iload #10
    //   224: ifeq -> 351
    //   227: new java/lang/StringBuilder
    //   230: astore #21
    //   232: aload #21
    //   234: invokespecial <init> : ()V
    //   237: aload #21
    //   239: ldc_w 'ServiceMobileApp:'
    //   242: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: pop
    //   246: aload #21
    //   248: getstatic com/roadtrack/onstar/BO/GlobalMembers.actionSocketCode : Ljava/lang/String;
    //   251: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: pop
    //   255: aload #21
    //   257: ldc_w ','
    //   260: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: pop
    //   264: aload #21
    //   266: aload #8
    //   268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: pop
    //   272: aload #21
    //   274: ldc_w ','
    //   277: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: pop
    //   281: aload #21
    //   283: aload #4
    //   285: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: pop
    //   289: aload #21
    //   291: ldc_w ','
    //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: aload #21
    //   300: aload #7
    //   302: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: pop
    //   306: aload_0
    //   307: aload #21
    //   309: invokevirtual toString : ()Ljava/lang/String;
    //   312: putfield outMsg : Ljava/lang/String;
    //   315: aload_0
    //   316: aload_0
    //   317: getfield outMsg : Ljava/lang/String;
    //   320: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   323: putfield outMsg : Ljava/lang/String;
    //   326: aload #22
    //   328: aload_0
    //   329: getfield outMsg : Ljava/lang/String;
    //   332: invokevirtual write : (Ljava/lang/String;)V
    //   335: aload #22
    //   337: invokevirtual flush : ()V
    //   340: iconst_0
    //   341: istore #10
    //   343: goto -> 351
    //   346: astore #21
    //   348: goto -> 1177
    //   351: new java/io/ByteArrayOutputStream
    //   354: astore #21
    //   356: aload #21
    //   358: iconst_1
    //   359: invokespecial <init> : (I)V
    //   362: aload #18
    //   364: astore #19
    //   366: iload #9
    //   368: istore #12
    //   370: iload #10
    //   372: istore #11
    //   374: aload #18
    //   376: astore #23
    //   378: aload #20
    //   380: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   383: astore #18
    //   385: iload #9
    //   387: ifeq -> 1052
    //   390: aload #18
    //   392: astore #19
    //   394: iload #9
    //   396: istore #12
    //   398: iload #10
    //   400: istore #11
    //   402: aload #18
    //   404: astore #23
    //   406: aload_0
    //   407: getfield processFinished : Z
    //   410: ifne -> 1052
    //   413: aload #18
    //   415: astore #19
    //   417: iload #9
    //   419: istore #12
    //   421: iload #10
    //   423: istore #11
    //   425: aload #18
    //   427: astore #23
    //   429: sipush #8192
    //   432: newarray byte
    //   434: astore #24
    //   436: aload #18
    //   438: astore #19
    //   440: iload #9
    //   442: istore #12
    //   444: iload #10
    //   446: istore #11
    //   448: aload #18
    //   450: astore #23
    //   452: aload #21
    //   454: aload #24
    //   456: iconst_0
    //   457: aload #18
    //   459: aload #24
    //   461: invokevirtual read : ([B)I
    //   464: invokevirtual write : ([BII)V
    //   467: aload #18
    //   469: astore #19
    //   471: iload #9
    //   473: istore #12
    //   475: iload #10
    //   477: istore #11
    //   479: aload #18
    //   481: astore #23
    //   483: aload #21
    //   485: ldc_w 'UTF-8'
    //   488: invokevirtual toString : (Ljava/lang/String;)Ljava/lang/String;
    //   491: ldc_w '\\n'
    //   494: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   497: astore #24
    //   499: aload #18
    //   501: astore #19
    //   503: iload #9
    //   505: istore #12
    //   507: iload #10
    //   509: istore #11
    //   511: aload #18
    //   513: astore #23
    //   515: aload_0
    //   516: aconst_null
    //   517: aload #28
    //   519: bipush #13
    //   521: invokespecial getTimeDifference : (Ljava/util/Date;Ljava/util/Date;I)J
    //   524: lstore #15
    //   526: iconst_0
    //   527: istore #14
    //   529: aload #18
    //   531: astore #19
    //   533: iload #9
    //   535: istore #12
    //   537: iload #10
    //   539: istore #11
    //   541: aload #18
    //   543: astore #23
    //   545: iload #14
    //   547: aload #24
    //   549: arraylength
    //   550: if_icmpge -> 989
    //   553: aload #18
    //   555: astore #19
    //   557: iload #9
    //   559: istore #12
    //   561: iload #10
    //   563: istore #11
    //   565: aload #18
    //   567: astore #23
    //   569: aload_0
    //   570: getfield processFinished : Z
    //   573: istore #17
    //   575: iload #17
    //   577: ifne -> 989
    //   580: iload #10
    //   582: istore #11
    //   584: lload #15
    //   586: iload_3
    //   587: i2l
    //   588: lcmp
    //   589: ifge -> 989
    //   592: aload #18
    //   594: astore #19
    //   596: iload #9
    //   598: istore #13
    //   600: iload #11
    //   602: istore #12
    //   604: aload_0
    //   605: aload #24
    //   607: iload #14
    //   609: aaload
    //   610: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   613: putfield sr : Ljava/lang/String;
    //   616: aload #18
    //   618: astore #19
    //   620: iload #9
    //   622: istore #13
    //   624: iload #11
    //   626: istore #12
    //   628: aload_0
    //   629: aload_0
    //   630: getfield context : Landroid/content/Context;
    //   633: aload_1
    //   634: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   637: invokevirtual intValue : ()I
    //   640: aload_0
    //   641: getfield sr : Ljava/lang/String;
    //   644: invokespecial adjustSocketsResults : (Landroid/content/Context;ILjava/lang/String;)V
    //   647: aload #18
    //   649: astore #19
    //   651: iload #9
    //   653: istore #13
    //   655: iload #11
    //   657: istore #12
    //   659: aload_0
    //   660: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   663: invokevirtual getSocketCode : ()Ljava/lang/String;
    //   666: astore #23
    //   668: aload #18
    //   670: astore #19
    //   672: iload #9
    //   674: istore #13
    //   676: iload #11
    //   678: istore #12
    //   680: new java/lang/StringBuilder
    //   683: astore #25
    //   685: aload #18
    //   687: astore #19
    //   689: iload #9
    //   691: istore #13
    //   693: iload #11
    //   695: istore #12
    //   697: aload #25
    //   699: invokespecial <init> : ()V
    //   702: aload #18
    //   704: astore #19
    //   706: iload #9
    //   708: istore #13
    //   710: iload #11
    //   712: istore #12
    //   714: aload #25
    //   716: ldc_w '2002,'
    //   719: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   722: pop
    //   723: aload #18
    //   725: astore #19
    //   727: iload #9
    //   729: istore #13
    //   731: iload #11
    //   733: istore #12
    //   735: aload #25
    //   737: aload #7
    //   739: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   742: pop
    //   743: aload #18
    //   745: astore #19
    //   747: iload #9
    //   749: istore #13
    //   751: iload #11
    //   753: istore #12
    //   755: aload #23
    //   757: aload #25
    //   759: invokevirtual toString : ()Ljava/lang/String;
    //   762: invokevirtual equals : (Ljava/lang/Object;)Z
    //   765: istore #17
    //   767: iload #17
    //   769: ifeq -> 979
    //   772: aload #18
    //   774: astore #19
    //   776: aload_0
    //   777: iconst_1
    //   778: putfield processFinished : Z
    //   781: aload #18
    //   783: astore #19
    //   785: aload #24
    //   787: arraylength
    //   788: istore #9
    //   790: aload #18
    //   792: astore #19
    //   794: aload #8
    //   796: invokestatic getDeviceSerialNumberByDeviceId : (Ljava/lang/String;)Ljava/lang/String;
    //   799: astore #24
    //   801: aload #18
    //   803: astore #19
    //   805: new java/lang/StringBuilder
    //   808: astore #23
    //   810: aload #18
    //   812: astore #19
    //   814: aload #23
    //   816: invokespecial <init> : ()V
    //   819: aload #18
    //   821: astore #19
    //   823: aload #23
    //   825: ldc_w 'ServiceMobileApp:3002,'
    //   828: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   831: pop
    //   832: aload #18
    //   834: astore #19
    //   836: aload #23
    //   838: aload #24
    //   840: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   843: pop
    //   844: aload #18
    //   846: astore #19
    //   848: aload #23
    //   850: ldc_w ','
    //   853: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   856: pop
    //   857: aload #18
    //   859: astore #19
    //   861: aload #23
    //   863: aload #4
    //   865: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   868: pop
    //   869: aload #18
    //   871: astore #19
    //   873: aload #23
    //   875: ldc_w ','
    //   878: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   881: pop
    //   882: aload #18
    //   884: astore #19
    //   886: aload #23
    //   888: aload #7
    //   890: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   893: pop
    //   894: aload #18
    //   896: astore #19
    //   898: aload_0
    //   899: aload #23
    //   901: invokevirtual toString : ()Ljava/lang/String;
    //   904: putfield ack : Ljava/lang/String;
    //   907: aload #18
    //   909: astore #19
    //   911: aload_0
    //   912: aload_0
    //   913: getfield ack : Ljava/lang/String;
    //   916: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   919: putfield ack : Ljava/lang/String;
    //   922: aload #18
    //   924: astore #19
    //   926: aload #22
    //   928: aload_0
    //   929: getfield ack : Ljava/lang/String;
    //   932: invokevirtual write : (Ljava/lang/String;)V
    //   935: aload #18
    //   937: astore #19
    //   939: aload #22
    //   941: invokevirtual flush : ()V
    //   944: aload #18
    //   946: astore #19
    //   948: aload #22
    //   950: invokevirtual close : ()V
    //   953: iconst_0
    //   954: istore #9
    //   956: goto -> 989
    //   959: astore #19
    //   961: aload #21
    //   963: astore #19
    //   965: aload #22
    //   967: astore #21
    //   969: iconst_0
    //   970: istore #9
    //   972: iload #11
    //   974: istore #10
    //   976: goto -> 1308
    //   979: iinc #14, 1
    //   982: iload #11
    //   984: istore #10
    //   986: goto -> 529
    //   989: aload #18
    //   991: astore #19
    //   993: iload #9
    //   995: istore #13
    //   997: iload #10
    //   999: istore #12
    //   1001: aload_0
    //   1002: aconst_null
    //   1003: aload #28
    //   1005: bipush #13
    //   1007: invokespecial getTimeDifference : (Ljava/util/Date;Ljava/util/Date;I)J
    //   1010: iload_3
    //   1011: i2l
    //   1012: lcmp
    //   1013: ifge -> 1046
    //   1016: iload #9
    //   1018: ifeq -> 1046
    //   1021: aload #18
    //   1023: astore #19
    //   1025: iload #9
    //   1027: istore #13
    //   1029: iload #10
    //   1031: istore #12
    //   1033: aload_0
    //   1034: getfield processFinished : Z
    //   1037: ifne -> 1046
    //   1040: iconst_1
    //   1041: istore #9
    //   1043: goto -> 1049
    //   1046: iconst_0
    //   1047: istore #9
    //   1049: goto -> 385
    //   1052: aload #18
    //   1054: astore #19
    //   1056: iload #9
    //   1058: istore #13
    //   1060: iload #10
    //   1062: istore #12
    //   1064: aload #21
    //   1066: invokevirtual close : ()V
    //   1069: aload #18
    //   1071: astore #19
    //   1073: iload #9
    //   1075: istore #13
    //   1077: iload #10
    //   1079: istore #12
    //   1081: aload #18
    //   1083: invokevirtual close : ()V
    //   1086: aload #18
    //   1088: astore #19
    //   1090: iload #9
    //   1092: istore #13
    //   1094: iload #10
    //   1096: istore #12
    //   1098: aload #22
    //   1100: invokevirtual close : ()V
    //   1103: aload #18
    //   1105: astore #19
    //   1107: iload #9
    //   1109: istore #13
    //   1111: iload #10
    //   1113: istore #12
    //   1115: aload #20
    //   1117: invokevirtual close : ()V
    //   1120: aload #21
    //   1122: astore #19
    //   1124: goto -> 1188
    //   1127: astore_1
    //   1128: aload #19
    //   1130: astore #18
    //   1132: goto -> 1333
    //   1135: astore #18
    //   1137: iload #11
    //   1139: istore #9
    //   1141: aload #23
    //   1143: astore #18
    //   1145: iload #12
    //   1147: istore #11
    //   1149: aload #21
    //   1151: astore #19
    //   1153: iload #9
    //   1155: istore #10
    //   1157: goto -> 1166
    //   1160: astore #21
    //   1162: iload #9
    //   1164: istore #11
    //   1166: iload #11
    //   1168: istore #9
    //   1170: goto -> 1177
    //   1173: astore_1
    //   1174: goto -> 1295
    //   1177: aload #22
    //   1179: astore #21
    //   1181: goto -> 1308
    //   1184: aload #21
    //   1186: astore #22
    //   1188: aload #19
    //   1190: ifnull -> 1203
    //   1193: aload #19
    //   1195: invokevirtual close : ()V
    //   1198: goto -> 1203
    //   1201: astore #21
    //   1203: aload #18
    //   1205: ifnull -> 1218
    //   1208: aload #18
    //   1210: invokevirtual close : ()V
    //   1213: goto -> 1218
    //   1216: astore #21
    //   1218: aload #22
    //   1220: ifnull -> 1228
    //   1223: aload #22
    //   1225: invokevirtual close : ()V
    //   1228: aload #20
    //   1230: invokevirtual close : ()V
    //   1233: aload #22
    //   1235: astore #25
    //   1237: iload #9
    //   1239: istore #12
    //   1241: aload #20
    //   1243: astore #22
    //   1245: aload #19
    //   1247: astore #23
    //   1249: aload #18
    //   1251: astore #24
    //   1253: iload #10
    //   1255: istore #11
    //   1257: goto -> 1495
    //   1260: astore #22
    //   1262: goto -> 1271
    //   1265: astore_1
    //   1266: goto -> 1291
    //   1269: astore #22
    //   1271: goto -> 1308
    //   1274: astore_1
    //   1275: aload #26
    //   1277: astore #20
    //   1279: aload #25
    //   1281: astore #18
    //   1283: aload #24
    //   1285: astore #19
    //   1287: aload #23
    //   1289: astore #21
    //   1291: aload #21
    //   1293: astore #22
    //   1295: aload #19
    //   1297: astore #21
    //   1299: goto -> 1333
    //   1302: astore #20
    //   1304: aload #22
    //   1306: astore #20
    //   1308: aload #20
    //   1310: astore #26
    //   1312: aload #21
    //   1314: astore #23
    //   1316: aload #19
    //   1318: astore #24
    //   1320: aload #18
    //   1322: astore #25
    //   1324: ldc2_w 1000
    //   1327: invokestatic sleep : (J)V
    //   1330: goto -> 1392
    //   1333: aload #21
    //   1335: ifnull -> 1348
    //   1338: aload #21
    //   1340: invokevirtual close : ()V
    //   1343: goto -> 1348
    //   1346: astore #4
    //   1348: aload #18
    //   1350: ifnull -> 1363
    //   1353: aload #18
    //   1355: invokevirtual close : ()V
    //   1358: goto -> 1363
    //   1361: astore #4
    //   1363: aload #22
    //   1365: ifnull -> 1378
    //   1368: aload #22
    //   1370: invokevirtual close : ()V
    //   1373: goto -> 1378
    //   1376: astore #4
    //   1378: aload #20
    //   1380: ifnull -> 1388
    //   1383: aload #20
    //   1385: invokevirtual close : ()V
    //   1388: aload_1
    //   1389: athrow
    //   1390: astore #22
    //   1392: aload #19
    //   1394: ifnull -> 1407
    //   1397: aload #19
    //   1399: invokevirtual close : ()V
    //   1402: goto -> 1407
    //   1405: astore #22
    //   1407: aload #18
    //   1409: ifnull -> 1422
    //   1412: aload #18
    //   1414: invokevirtual close : ()V
    //   1417: goto -> 1422
    //   1420: astore #22
    //   1422: aload #21
    //   1424: ifnull -> 1437
    //   1427: aload #21
    //   1429: invokevirtual close : ()V
    //   1432: goto -> 1437
    //   1435: astore #22
    //   1437: iload #9
    //   1439: istore #12
    //   1441: aload #20
    //   1443: astore #22
    //   1445: aload #21
    //   1447: astore #25
    //   1449: aload #19
    //   1451: astore #23
    //   1453: aload #18
    //   1455: astore #24
    //   1457: iload #10
    //   1459: istore #11
    //   1461: aload #20
    //   1463: ifnull -> 1495
    //   1466: aload #20
    //   1468: invokevirtual close : ()V
    //   1471: iload #10
    //   1473: istore #11
    //   1475: aload #18
    //   1477: astore #24
    //   1479: aload #19
    //   1481: astore #23
    //   1483: aload #21
    //   1485: astore #25
    //   1487: aload #20
    //   1489: astore #22
    //   1491: iload #9
    //   1493: istore #12
    //   1495: iload #11
    //   1497: istore #10
    //   1499: aload_0
    //   1500: aconst_null
    //   1501: aload #28
    //   1503: bipush #13
    //   1505: invokespecial getTimeDifference : (Ljava/util/Date;Ljava/util/Date;I)J
    //   1508: iload_3
    //   1509: i2l
    //   1510: lcmp
    //   1511: ifge -> 1532
    //   1514: iload #12
    //   1516: ifeq -> 1532
    //   1519: aload_0
    //   1520: getfield processFinished : Z
    //   1523: ifne -> 1532
    //   1526: iconst_1
    //   1527: istore #9
    //   1529: goto -> 1535
    //   1532: iconst_0
    //   1533: istore #9
    //   1535: aload #25
    //   1537: astore #21
    //   1539: aload #23
    //   1541: astore #19
    //   1543: aload #24
    //   1545: astore #18
    //   1547: goto -> 41
    //   1550: iload #9
    //   1552: ifne -> 1583
    //   1555: aload #22
    //   1557: ifnull -> 1583
    //   1560: aload #22
    //   1562: invokevirtual isConnected : ()Z
    //   1565: ifeq -> 1583
    //   1568: aload #22
    //   1570: invokevirtual close : ()V
    //   1573: goto -> 1583
    //   1576: astore_1
    //   1577: ldc2_w 1000
    //   1580: invokestatic sleep : (J)V
    //   1583: return
    //   1584: astore #21
    //   1586: goto -> 1177
    //   1589: astore #21
    //   1591: goto -> 1162
    //   1594: astore #19
    //   1596: iload #13
    //   1598: istore #11
    //   1600: iload #12
    //   1602: istore #9
    //   1604: goto -> 1149
    //   1607: astore #21
    //   1609: goto -> 1228
    //   1612: astore #21
    //   1614: goto -> 1233
    //   1617: astore #4
    //   1619: goto -> 1388
    //   1622: astore #22
    //   1624: iload #9
    //   1626: istore #12
    //   1628: aload #20
    //   1630: astore #22
    //   1632: aload #21
    //   1634: astore #25
    //   1636: aload #19
    //   1638: astore #23
    //   1640: aload #18
    //   1642: astore #24
    //   1644: iload #10
    //   1646: istore #11
    //   1648: goto -> 1495
    //   1651: astore_1
    //   1652: goto -> 1583
    // Exception table:
    //   from	to	target	type
    //   62	67	1302	java/lang/Exception
    //   62	67	1274	finally
    //   83	88	1302	java/lang/Exception
    //   83	88	1274	finally
    //   88	93	1269	java/lang/Exception
    //   88	93	1265	finally
    //   93	159	1260	java/lang/Exception
    //   93	159	1265	finally
    //   159	222	1584	java/lang/Exception
    //   159	222	1173	finally
    //   227	264	346	java/lang/Exception
    //   227	264	1173	finally
    //   264	340	1584	java/lang/Exception
    //   264	340	1173	finally
    //   351	356	1160	java/lang/Exception
    //   351	356	1173	finally
    //   356	362	1589	java/lang/Exception
    //   356	362	1173	finally
    //   378	385	1135	java/lang/Exception
    //   378	385	1127	finally
    //   406	413	1135	java/lang/Exception
    //   406	413	1127	finally
    //   429	436	1135	java/lang/Exception
    //   429	436	1127	finally
    //   452	467	1135	java/lang/Exception
    //   452	467	1127	finally
    //   483	499	1135	java/lang/Exception
    //   483	499	1127	finally
    //   515	526	1135	java/lang/Exception
    //   515	526	1127	finally
    //   545	553	1135	java/lang/Exception
    //   545	553	1127	finally
    //   569	575	1135	java/lang/Exception
    //   569	575	1127	finally
    //   604	616	1594	java/lang/Exception
    //   604	616	1127	finally
    //   628	647	1594	java/lang/Exception
    //   628	647	1127	finally
    //   659	668	1594	java/lang/Exception
    //   659	668	1127	finally
    //   680	685	1594	java/lang/Exception
    //   680	685	1127	finally
    //   697	702	1594	java/lang/Exception
    //   697	702	1127	finally
    //   714	723	1594	java/lang/Exception
    //   714	723	1127	finally
    //   735	743	1594	java/lang/Exception
    //   735	743	1127	finally
    //   755	767	1594	java/lang/Exception
    //   755	767	1127	finally
    //   776	781	959	java/lang/Exception
    //   776	781	1127	finally
    //   785	790	959	java/lang/Exception
    //   785	790	1127	finally
    //   794	801	959	java/lang/Exception
    //   794	801	1127	finally
    //   805	810	959	java/lang/Exception
    //   805	810	1127	finally
    //   814	819	959	java/lang/Exception
    //   814	819	1127	finally
    //   823	832	959	java/lang/Exception
    //   823	832	1127	finally
    //   836	844	959	java/lang/Exception
    //   836	844	1127	finally
    //   848	857	959	java/lang/Exception
    //   848	857	1127	finally
    //   861	869	959	java/lang/Exception
    //   861	869	1127	finally
    //   873	882	959	java/lang/Exception
    //   873	882	1127	finally
    //   886	894	959	java/lang/Exception
    //   886	894	1127	finally
    //   898	907	959	java/lang/Exception
    //   898	907	1127	finally
    //   911	922	959	java/lang/Exception
    //   911	922	1127	finally
    //   926	935	959	java/lang/Exception
    //   926	935	1127	finally
    //   939	944	959	java/lang/Exception
    //   939	944	1127	finally
    //   948	953	959	java/lang/Exception
    //   948	953	1127	finally
    //   1001	1016	1594	java/lang/Exception
    //   1001	1016	1127	finally
    //   1033	1040	1594	java/lang/Exception
    //   1033	1040	1127	finally
    //   1064	1069	1594	java/lang/Exception
    //   1064	1069	1127	finally
    //   1081	1086	1594	java/lang/Exception
    //   1081	1086	1127	finally
    //   1098	1103	1594	java/lang/Exception
    //   1098	1103	1127	finally
    //   1115	1120	1594	java/lang/Exception
    //   1115	1120	1127	finally
    //   1193	1198	1201	java/io/IOException
    //   1208	1213	1216	java/io/IOException
    //   1223	1228	1607	java/io/IOException
    //   1228	1233	1612	java/io/IOException
    //   1324	1330	1390	java/lang/InterruptedException
    //   1324	1330	1274	finally
    //   1338	1343	1346	java/io/IOException
    //   1353	1358	1361	java/io/IOException
    //   1368	1373	1376	java/io/IOException
    //   1383	1388	1617	java/io/IOException
    //   1397	1402	1405	java/io/IOException
    //   1412	1417	1420	java/io/IOException
    //   1427	1432	1435	java/io/IOException
    //   1466	1471	1622	java/io/IOException
    //   1568	1573	1576	java/io/IOException
    //   1577	1583	1651	java/lang/InterruptedException
  }
  
  private void SocketACK(final int socketTimeOut, final String cuentaId, final String serverIP, final int serverPort, final String messageId, final String deviceId) {
    (new Thread() {
        final ActionsProcess this$0;
        
        final String val$cuentaId;
        
        final String val$deviceId;
        
        final String val$messageId;
        
        final String val$serverIP;
        
        final int val$serverPort;
        
        final int val$socketTimeOut;
        
        public void run() {
          try {
            Socket socket = new Socket();
            this();
            InetSocketAddress inetSocketAddress = new InetSocketAddress();
            this(serverIP, serverPort);
            BufferedWriter bufferedWriter = null;
            socket.bind(null);
            socket.setSoTimeout(GlobalMembers.SOCKET_READ_TIMEOUT);
            socket.connect(inetSocketAddress, socketTimeOut);
            if (socket.isConnected()) {
              bufferedWriter = new BufferedWriter();
              OutputStreamWriter outputStreamWriter = new OutputStreamWriter();
              this(socket.getOutputStream());
              this(outputStreamWriter);
              ActionsProcess actionsProcess1 = ActionsProcess.this;
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("AC:");
              stringBuilder.append(cuentaId);
              ActionsProcess.access$202(actionsProcess1, stringBuilder.toString());
              ActionsProcess.access$202(ActionsProcess.this, Utilities.Crypt(ActionsProcess.this.outMsg));
              bufferedWriter.write(ActionsProcess.this.outMsg);
              bufferedWriter.flush();
              String str = Utilities.getDeviceSerialNumberByDeviceId(deviceId);
              ActionsProcess actionsProcess2 = ActionsProcess.this;
              stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("ServiceMobileApp:");
              stringBuilder.append(GlobalMembers.actionSocketCode);
              stringBuilder.append(",");
              stringBuilder.append(str);
              stringBuilder.append(",");
              stringBuilder.append(cuentaId);
              stringBuilder.append(",");
              stringBuilder.append(messageId);
              ActionsProcess.access$202(actionsProcess2, stringBuilder.toString());
              ActionsProcess.access$202(ActionsProcess.this, Utilities.Crypt(ActionsProcess.this.outMsg));
              bufferedWriter.write(ActionsProcess.this.outMsg);
              bufferedWriter.flush();
            } 
          } catch (Exception exception) {
          
          } finally {
            ActionsProcess.this.stopThread(this);
          } 
          ActionsProcess.this.stopThread(this);
        }
      }).start();
  }
  
  private void SocketKeepAlive(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, int paramInt3, String paramString4, String paramString5) {
    // Byte code:
    //   0: new java/util/Date
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #16
    //   9: aconst_null
    //   10: astore #4
    //   12: aconst_null
    //   13: astore_1
    //   14: iconst_1
    //   15: istore #9
    //   17: iload #9
    //   19: ifeq -> 840
    //   22: aload #4
    //   24: astore #15
    //   26: aload_1
    //   27: astore #14
    //   29: new java/net/Socket
    //   32: astore #7
    //   34: aload #4
    //   36: astore #15
    //   38: aload_1
    //   39: astore #14
    //   41: aload #7
    //   43: invokespecial <init> : ()V
    //   46: aload_1
    //   47: astore #4
    //   49: new java/net/InetSocketAddress
    //   52: astore #14
    //   54: aload_1
    //   55: astore #4
    //   57: aload #14
    //   59: aload #5
    //   61: iload #6
    //   63: invokespecial <init> : (Ljava/lang/String;I)V
    //   66: aload_1
    //   67: astore #4
    //   69: aload #7
    //   71: aconst_null
    //   72: invokevirtual bind : (Ljava/net/SocketAddress;)V
    //   75: aload_1
    //   76: astore #4
    //   78: aload #7
    //   80: getstatic com/roadtrack/onstar/BO/GlobalMembers.SOCKET_READ_TIMEOUT : I
    //   83: invokevirtual setSoTimeout : (I)V
    //   86: aload_1
    //   87: astore #4
    //   89: aload #7
    //   91: aload #14
    //   93: iload_2
    //   94: invokevirtual connect : (Ljava/net/SocketAddress;I)V
    //   97: iload #9
    //   99: istore #10
    //   101: aload_1
    //   102: astore #14
    //   104: aload_1
    //   105: astore #4
    //   107: aload #7
    //   109: invokevirtual isConnected : ()Z
    //   112: ifeq -> 426
    //   115: aload_1
    //   116: astore #4
    //   118: aload #7
    //   120: iconst_1
    //   121: invokevirtual setKeepAlive : (Z)V
    //   124: aload_1
    //   125: astore #4
    //   127: ldc_w 'connected'
    //   130: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   133: astore #15
    //   135: iload #9
    //   137: ifeq -> 396
    //   140: aload_1
    //   141: astore #4
    //   143: new java/io/BufferedWriter
    //   146: astore #14
    //   148: aload_1
    //   149: astore #4
    //   151: new java/io/OutputStreamWriter
    //   154: astore #17
    //   156: aload_1
    //   157: astore #4
    //   159: aload #17
    //   161: aload #7
    //   163: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   166: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   169: aload_1
    //   170: astore #4
    //   172: aload #14
    //   174: aload #17
    //   176: invokespecial <init> : (Ljava/io/Writer;)V
    //   179: aload #14
    //   181: aload #15
    //   183: invokevirtual write : (Ljava/lang/String;)V
    //   186: aload #14
    //   188: invokevirtual flush : ()V
    //   191: new java/io/ByteArrayOutputStream
    //   194: astore #17
    //   196: aload #17
    //   198: iconst_1
    //   199: invokespecial <init> : (I)V
    //   202: sipush #8192
    //   205: newarray byte
    //   207: astore #4
    //   209: aload #7
    //   211: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   214: astore_1
    //   215: aload #7
    //   217: invokevirtual getKeepAlive : ()Z
    //   220: pop
    //   221: aload #14
    //   223: invokevirtual close : ()V
    //   226: aload #17
    //   228: aload #4
    //   230: iconst_0
    //   231: aload_1
    //   232: aload #4
    //   234: invokevirtual read : ([B)I
    //   237: invokevirtual write : ([BII)V
    //   240: aload #17
    //   242: ldc_w 'UTF-8'
    //   245: invokevirtual toString : (Ljava/lang/String;)Ljava/lang/String;
    //   248: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   251: pop
    //   252: aload_0
    //   253: aconst_null
    //   254: aload #16
    //   256: bipush #13
    //   258: invokespecial getTimeDifference : (Ljava/util/Date;Ljava/util/Date;I)J
    //   261: lstore #12
    //   263: lload #12
    //   265: iload_3
    //   266: i2l
    //   267: lcmp
    //   268: iflt -> 293
    //   271: aload_0
    //   272: getfield processFinished : Z
    //   275: istore #11
    //   277: iload #11
    //   279: ifne -> 293
    //   282: iconst_0
    //   283: istore #9
    //   285: goto -> 293
    //   288: astore #4
    //   290: goto -> 385
    //   293: sipush #3000
    //   296: i2l
    //   297: lstore #12
    //   299: lload #12
    //   301: invokestatic sleep : (J)V
    //   304: goto -> 369
    //   307: astore #4
    //   309: goto -> 385
    //   312: astore_1
    //   313: aload_0
    //   314: getfield TAG : Ljava/lang/String;
    //   317: astore #4
    //   319: new java/lang/StringBuilder
    //   322: astore #17
    //   324: aload #17
    //   326: invokespecial <init> : ()V
    //   329: aload #17
    //   331: aload #8
    //   333: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: pop
    //   337: aload #17
    //   339: ldc_w ': '
    //   342: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: pop
    //   346: aload #17
    //   348: aload_1
    //   349: invokevirtual toString : ()Ljava/lang/String;
    //   352: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: pop
    //   356: aload #4
    //   358: ldc_w 'Error: timer'
    //   361: aload #17
    //   363: invokevirtual toString : ()Ljava/lang/String;
    //   366: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   369: aload #14
    //   371: astore_1
    //   372: goto -> 135
    //   375: astore_1
    //   376: aload #14
    //   378: astore #4
    //   380: goto -> 778
    //   383: astore #4
    //   385: aload #14
    //   387: astore_1
    //   388: goto -> 491
    //   391: astore #4
    //   393: goto -> 491
    //   396: aload_1
    //   397: astore #4
    //   399: aload #7
    //   401: invokevirtual close : ()V
    //   404: aload_1
    //   405: astore #4
    //   407: aload_1
    //   408: invokevirtual close : ()V
    //   411: iload #9
    //   413: istore #10
    //   415: aload_1
    //   416: astore #14
    //   418: goto -> 426
    //   421: astore #4
    //   423: goto -> 491
    //   426: aload #14
    //   428: ifnull -> 454
    //   431: aload #14
    //   433: invokevirtual close : ()V
    //   436: goto -> 454
    //   439: astore_1
    //   440: aload_0
    //   441: getfield TAG : Ljava/lang/String;
    //   444: ldc_w 'Error'
    //   447: aload_1
    //   448: invokevirtual toString : ()Ljava/lang/String;
    //   451: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   454: aload #7
    //   456: invokevirtual close : ()V
    //   459: goto -> 477
    //   462: astore_1
    //   463: aload_0
    //   464: getfield TAG : Ljava/lang/String;
    //   467: ldc_w 'Error'
    //   470: aload_1
    //   471: invokevirtual toString : ()Ljava/lang/String;
    //   474: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   477: goto -> 732
    //   480: astore #4
    //   482: goto -> 491
    //   485: astore_1
    //   486: goto -> 778
    //   489: astore #4
    //   491: aload #7
    //   493: astore #14
    //   495: aload #4
    //   497: astore #7
    //   499: aload #14
    //   501: astore #4
    //   503: goto -> 520
    //   506: astore_1
    //   507: aload #14
    //   509: astore #4
    //   511: aload #15
    //   513: astore #7
    //   515: goto -> 778
    //   518: astore #7
    //   520: aload #4
    //   522: astore #15
    //   524: aload_1
    //   525: astore #14
    //   527: aload_0
    //   528: getfield TAG : Ljava/lang/String;
    //   531: astore #17
    //   533: aload #4
    //   535: astore #15
    //   537: aload_1
    //   538: astore #14
    //   540: new java/lang/StringBuilder
    //   543: astore #18
    //   545: aload #4
    //   547: astore #15
    //   549: aload_1
    //   550: astore #14
    //   552: aload #18
    //   554: invokespecial <init> : ()V
    //   557: aload #4
    //   559: astore #15
    //   561: aload_1
    //   562: astore #14
    //   564: aload #18
    //   566: aload #8
    //   568: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: pop
    //   572: aload #4
    //   574: astore #15
    //   576: aload_1
    //   577: astore #14
    //   579: aload #18
    //   581: ldc_w ': '
    //   584: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   587: pop
    //   588: aload #4
    //   590: astore #15
    //   592: aload_1
    //   593: astore #14
    //   595: aload #18
    //   597: aload #7
    //   599: invokevirtual toString : ()Ljava/lang/String;
    //   602: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   605: pop
    //   606: aload #4
    //   608: astore #15
    //   610: aload_1
    //   611: astore #14
    //   613: aload #17
    //   615: ldc_w 'Error: SocketKeepAlive'
    //   618: aload #18
    //   620: invokevirtual toString : ()Ljava/lang/String;
    //   623: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   626: aload #4
    //   628: astore #15
    //   630: aload_1
    //   631: astore #14
    //   633: bipush #10
    //   635: invokestatic waiting : (I)V
    //   638: aload_1
    //   639: ifnull -> 666
    //   642: aload_1
    //   643: invokevirtual close : ()V
    //   646: goto -> 666
    //   649: astore #7
    //   651: aload_0
    //   652: getfield TAG : Ljava/lang/String;
    //   655: ldc_w 'Error'
    //   658: aload #7
    //   660: invokevirtual toString : ()Ljava/lang/String;
    //   663: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   666: iload #9
    //   668: istore #10
    //   670: aload #4
    //   672: astore #7
    //   674: aload_1
    //   675: astore #14
    //   677: aload #4
    //   679: ifnull -> 477
    //   682: aload #4
    //   684: invokevirtual close : ()V
    //   687: iload #9
    //   689: istore #10
    //   691: aload #4
    //   693: astore #7
    //   695: aload_1
    //   696: astore #14
    //   698: goto -> 477
    //   701: astore #7
    //   703: aload_0
    //   704: getfield TAG : Ljava/lang/String;
    //   707: ldc_w 'Error'
    //   710: aload #7
    //   712: invokevirtual toString : ()Ljava/lang/String;
    //   715: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   718: iload #9
    //   720: istore #10
    //   722: aload #4
    //   724: astore #7
    //   726: aload_1
    //   727: astore #14
    //   729: goto -> 477
    //   732: aload_0
    //   733: aconst_null
    //   734: aload #16
    //   736: bipush #13
    //   738: invokespecial getTimeDifference : (Ljava/util/Date;Ljava/util/Date;I)J
    //   741: iload_3
    //   742: i2l
    //   743: lcmp
    //   744: ifge -> 765
    //   747: iload #10
    //   749: ifeq -> 765
    //   752: aload_0
    //   753: getfield processFinished : Z
    //   756: ifne -> 765
    //   759: iconst_1
    //   760: istore #9
    //   762: goto -> 768
    //   765: iconst_0
    //   766: istore #9
    //   768: aload #7
    //   770: astore #4
    //   772: aload #14
    //   774: astore_1
    //   775: goto -> 17
    //   778: aload #4
    //   780: ifnull -> 808
    //   783: aload #4
    //   785: invokevirtual close : ()V
    //   788: goto -> 808
    //   791: astore #4
    //   793: aload_0
    //   794: getfield TAG : Ljava/lang/String;
    //   797: ldc_w 'Error'
    //   800: aload #4
    //   802: invokevirtual toString : ()Ljava/lang/String;
    //   805: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   808: aload #7
    //   810: ifnull -> 838
    //   813: aload #7
    //   815: invokevirtual close : ()V
    //   818: goto -> 838
    //   821: astore #4
    //   823: aload_0
    //   824: getfield TAG : Ljava/lang/String;
    //   827: ldc_w 'Error'
    //   830: aload #4
    //   832: invokevirtual toString : ()Ljava/lang/String;
    //   835: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   838: aload_1
    //   839: athrow
    //   840: iload #9
    //   842: ifne -> 984
    //   845: aload #4
    //   847: ifnull -> 984
    //   850: aload #4
    //   852: invokevirtual isConnected : ()Z
    //   855: ifeq -> 984
    //   858: aload #4
    //   860: invokevirtual close : ()V
    //   863: goto -> 984
    //   866: astore #4
    //   868: aload_0
    //   869: getfield TAG : Ljava/lang/String;
    //   872: astore #5
    //   874: new java/lang/StringBuilder
    //   877: dup
    //   878: invokespecial <init> : ()V
    //   881: astore_1
    //   882: aload_1
    //   883: aload #8
    //   885: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   888: pop
    //   889: aload_1
    //   890: ldc_w ': '
    //   893: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   896: pop
    //   897: aload_1
    //   898: aload #4
    //   900: invokevirtual toString : ()Ljava/lang/String;
    //   903: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   906: pop
    //   907: aload #5
    //   909: ldc_w 'Error: SOCKET.IOException'
    //   912: aload_1
    //   913: invokevirtual toString : ()Ljava/lang/String;
    //   916: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   919: ldc2_w 10000
    //   922: invokestatic sleep : (J)V
    //   925: goto -> 984
    //   928: astore #5
    //   930: aload_0
    //   931: getfield TAG : Ljava/lang/String;
    //   934: astore_1
    //   935: new java/lang/StringBuilder
    //   938: dup
    //   939: invokespecial <init> : ()V
    //   942: astore #4
    //   944: aload #4
    //   946: aload #8
    //   948: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   951: pop
    //   952: aload #4
    //   954: ldc_w ': '
    //   957: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   960: pop
    //   961: aload #4
    //   963: aload #5
    //   965: invokevirtual getMessage : ()Ljava/lang/String;
    //   968: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   971: pop
    //   972: aload_1
    //   973: ldc_w 'Error: Timer'
    //   976: aload #4
    //   978: invokevirtual toString : ()Ljava/lang/String;
    //   981: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   984: return
    // Exception table:
    //   from	to	target	type
    //   29	34	518	java/lang/Exception
    //   29	34	506	finally
    //   41	46	518	java/lang/Exception
    //   41	46	506	finally
    //   49	54	489	java/lang/Exception
    //   49	54	485	finally
    //   57	66	480	java/lang/Exception
    //   57	66	485	finally
    //   69	75	480	java/lang/Exception
    //   69	75	485	finally
    //   78	86	480	java/lang/Exception
    //   78	86	485	finally
    //   89	97	480	java/lang/Exception
    //   89	97	485	finally
    //   107	115	480	java/lang/Exception
    //   107	115	485	finally
    //   118	124	480	java/lang/Exception
    //   118	124	485	finally
    //   127	135	480	java/lang/Exception
    //   127	135	485	finally
    //   143	148	391	java/lang/Exception
    //   143	148	485	finally
    //   151	156	391	java/lang/Exception
    //   151	156	485	finally
    //   159	169	391	java/lang/Exception
    //   159	169	485	finally
    //   172	179	391	java/lang/Exception
    //   172	179	485	finally
    //   179	263	383	java/lang/Exception
    //   179	263	375	finally
    //   271	277	288	java/lang/Exception
    //   271	277	375	finally
    //   299	304	312	java/lang/InterruptedException
    //   299	304	307	java/lang/Exception
    //   299	304	375	finally
    //   313	369	307	java/lang/Exception
    //   313	369	375	finally
    //   399	404	421	java/lang/Exception
    //   399	404	485	finally
    //   407	411	421	java/lang/Exception
    //   407	411	485	finally
    //   431	436	439	java/io/IOException
    //   454	459	462	java/io/IOException
    //   527	533	506	finally
    //   540	545	506	finally
    //   552	557	506	finally
    //   564	572	506	finally
    //   579	588	506	finally
    //   595	606	506	finally
    //   613	626	506	finally
    //   633	638	506	finally
    //   642	646	649	java/io/IOException
    //   682	687	701	java/io/IOException
    //   783	788	791	java/io/IOException
    //   813	818	821	java/io/IOException
    //   858	863	866	java/io/IOException
    //   919	925	928	java/lang/InterruptedException
  }
  
  private void StartSocket(final String actionCode, final int socketTimeOut, final int socketMaxTime, final String accountId, final String serverIP, final int serverPort, final String messageId, final String deviceId, final boolean isKeepAlive) {
    (new Thread() {
        final ActionsProcess this$0;
        
        final String val$accountId;
        
        final String val$actionCode;
        
        final String val$deviceId;
        
        final boolean val$isKeepAlive;
        
        final String val$messageId;
        
        final String val$serverIP;
        
        final int val$serverPort;
        
        final int val$socketMaxTime;
        
        final int val$socketTimeOut;
        
        public void run() {
          if (!ActionsProcess.this.processFinished) {
            if (isKeepAlive) {
              ActionsProcess.this.SocketKeepAlive(actionCode, socketTimeOut, socketMaxTime, accountId, serverIP, serverPort, messageId, deviceId);
            } else {
              ActionsProcess.this.Socket(actionCode, socketTimeOut, socketMaxTime, accountId, serverIP, serverPort, messageId, deviceId);
            } 
          } else {
            ActionsProcess.this.stopThread(this);
          } 
        }
      }).start();
  }
  
  private String WS(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, LinkedHashMap<String, Object> paramLinkedHashMap) {
    String str1;
    String str3 = "";
    String str2 = str3;
    try {
      this.sendCommandErrorCode = 0;
      str2 = str3;
      SoapRequestObject soapRequestObject = new SoapRequestObject();
      str2 = str3;
      this(paramLinkedHashMap, paramString4, paramString3, paramString2, paramString1, paramInt2, paramInt2);
      str2 = str3;
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      str2 = str3;
      RequestManager requestManager = new RequestManager();
      str2 = str3;
      this(this.context, soapRequestObject);
      str2 = str3;
      RequestManager.OnPostExecuteListener onPostExecuteListener = new RequestManager.OnPostExecuteListener() {
          final ActionsProcess this$0;
          
          public void onPostExecuteListener(String param1String, int param1Int) {
            ActionsProcess.access$002(ActionsProcess.this, param1String);
            ActionsProcess.access$102(ActionsProcess.this, param1Int);
          }
        };
      str2 = str3;
      super(this);
      str2 = str3;
      requestManager.setOnPostExecuteListener(onPostExecuteListener);
      str2 = str3;
      requestManager.sendRequest(1);
      str2 = str3;
      String str = this.aux_result;
      str2 = str;
      str1 = str;
      if (paramString3.contains("SendCommand")) {
        str2 = str;
        if (this.aux_result.contains("timeout")) {
          str2 = str;
          this.sendCommandErrorCode = this.sendCommandErrorCodeTimeOut;
          str1 = str;
        } else {
          str2 = str;
          str1 = str;
          if (this.sendCommandErrorCode != -1) {
            str2 = str;
            this.sendCommandErrorCode = 0;
            str1 = str;
          } 
        } 
      } 
    } catch (Exception exception) {
      if (paramString3.contains("SendCommand"))
        if (this.aux_result.contains("timeout")) {
          this.sendCommandErrorCode = this.sendCommandErrorCodeTimeOut;
        } else {
          this.sendCommandErrorCode = -1;
        }  
      paramString2 = this.TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.deviceId);
      stringBuilder.append(": ");
      stringBuilder.append(exception.toString());
      Utilities.escribeArchivo(paramString2, "Error: WS", stringBuilder.toString());
      str1 = str2;
    } 
    return str1;
  }
  
  private void adjustSocketsResults(Context paramContext, int paramInt, String paramString) {
    Utilities.getDateTime(this.deviceId, paramContext);
    if (paramString != null && paramString.length() != 0 && paramString.contains(";")) {
      String[] arrayOfString = paramString.split(";");
      paramString = arrayOfString[0].replace("PI:", "");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("2002,");
      stringBuilder.append(this.messageId);
      if (paramString.equals(stringBuilder.toString())) {
        PIDVO pIDVO;
        this.result.setSocketCode(paramString);
        this.result.setIdResponse(Enums.ActionResultCode.forValue(Integer.valueOf(arrayOfString[1]).intValue()));
        this.result.setIdResponseOriginal(Enums.ActionResultCode.forValue(Integer.valueOf(arrayOfString[1]).intValue()));
        this.result.setLatitude(Float.valueOf(arrayOfString[4]).floatValue());
        this.result.setLongitude(Float.valueOf(arrayOfString[5]).floatValue());
        this.result.setOdometer(arrayOfString[6]);
        this.result.setGpsStatus(Integer.valueOf(arrayOfString[7]).intValue());
        this.result.setEventDateTime(arrayOfString[9]);
        if (paramInt == Enums.Services.pid.GetCode() && arrayOfString != null && arrayOfString.length > 15) {
          this.result.setIdResponse(Enums.ActionResultCode.Activated);
          paramString = Utilities.getUTCDateTime("MM/dd/yyyy hh:mm:ss a", arrayOfString[15]);
          this.result.setOdometer(arrayOfString[10]);
          this.result.setGas(arrayOfString[11]);
          if (arrayOfString[12] != null)
            if (arrayOfString[12].length() > 0) {
              String[] arrayOfString1 = arrayOfString[12].split("-");
              if (arrayOfString1.length > 0)
                this.result.setOilValue(arrayOfString1[0]); 
              if (arrayOfString1.length > 1)
                this.result.setOilStatus(arrayOfString1[1]); 
            } else {
              this.result.setOilValue(arrayOfString[12]);
            }  
          this.result.setBatteryLevel(arrayOfString[13]);
          if (arrayOfString[14].length() > 0) {
            String[] arrayOfString1 = arrayOfString[14].split("/");
            if (arrayOfString1.length > 3) {
              String[] arrayOfString2 = arrayOfString1[0].split("-");
              if (arrayOfString2.length > 0)
                this.result.setTireStatusFL(arrayOfString2[0]); 
              if (arrayOfString2.length > 1)
                this.result.setTireFL(arrayOfString2[1]); 
              arrayOfString2 = arrayOfString1[1].split("-");
              if (arrayOfString2.length > 0)
                this.result.setTireStatusRL(arrayOfString2[0]); 
              if (arrayOfString2.length > 1)
                this.result.setTireRL(arrayOfString2[1]); 
              arrayOfString2 = arrayOfString1[2].split("-");
              if (arrayOfString2.length > 0)
                this.result.setTireStatusFR(arrayOfString2[0]); 
              if (arrayOfString2.length > 1)
                this.result.setTireFR(arrayOfString2[1]); 
              arrayOfString1 = arrayOfString1[3].split("-");
              if (arrayOfString1.length > 0)
                this.result.setTireStatusRR(arrayOfString1[0]); 
              if (arrayOfString1.length > 1)
                this.result.setTireRR(arrayOfString1[1]); 
            } 
          } 
          this.result.setEventDateTime(paramString);
          this.result.setModelo(arrayOfString[16].trim());
          this.result.setVersion(arrayOfString[17].trim());
          this.result.setYear(arrayOfString[18].trim());
          this.result.setPlaca(arrayOfString[19].trim());
          if (arrayOfString.length > 20)
            this.result.setTPMSText(arrayOfString[20].trim()); 
          if (arrayOfString.length > 21)
            this.result.setIsM300(arrayOfString[21].trim()); 
          if (arrayOfString.length > 23) {
            this.result.setAutonomy_km(arrayOfString[22]);
            if (!arrayOfString[23].isEmpty()) {
              arrayOfString = arrayOfString[23].split("#");
              this.result.setAutonomy_status(arrayOfString[0]);
              if (arrayOfString.length > 1)
                this.result.setAutonomy_text(arrayOfString[1]); 
            } else {
              this.result.setAutonomy_status("");
              this.result.setAutonomy_text("");
            } 
          } 
          pIDVO = new PIDVO(this.context);
          pIDVO.setDeviceId(this.result.getDeviceId());
          pIDVO.setMessageId(this.result.getMessageId());
          pIDVO.setOdometer(this.result.getOdometer());
          pIDVO.setGas(this.result.getGas());
          pIDVO.setOilValue(this.result.getOilValue());
          pIDVO.setOilStatus(this.result.getOilStatus());
          pIDVO.setBatterylevel(this.result.getBatteryLevel());
          pIDVO.setTire_status_fl(this.result.getTireStatusFL());
          pIDVO.setTire_fl(this.result.getTireFL());
          pIDVO.setTire_status_fr(this.result.getTireStatusFR());
          pIDVO.setTire_fr(this.result.getTireFR());
          pIDVO.setTire_status_rl(this.result.getTireStatusRL());
          pIDVO.setTire_rl(this.result.getTireRL());
          pIDVO.setTire_status_rr(this.result.getTireStatusRR());
          pIDVO.setTire_rr(this.result.getTireRR());
          pIDVO.setEventDate(this.result.getEventDateTime());
          pIDVO.setModelo(this.result.getModelo());
          pIDVO.setVersion(this.result.getVersion());
          pIDVO.setYear(this.result.getYear());
          pIDVO.setPlaca(this.result.getPlaca());
          pIDVO.setTPMSText(this.result.getTPMSText());
          pIDVO.setIsM300(this.result.getIsM300());
          pIDVO.setAutonomy_km(this.result.getAutonomy_km());
          pIDVO.setAutonomy_status(this.result.getAutonomy_status());
          pIDVO.setAutonomy_text(this.result.getAutonomy_text());
          this.dbFun.addPid(this.result.getDeviceId(), pIDVO);
        } else if (paramInt == Enums.Services.DTCAction.GetCode()) {
          PIDVO pIDVO1 = pIDVO[1];
          this.result.setSendCommandResultId(Enums.WSResultId.OK);
          this.result.setIdResponse(Enums.ActionResultCode.forValue(Integer.valueOf((String)pIDVO[1]).intValue()));
        } 
      } 
    } 
  }
  
  private ActionResultVO adjustWCFResult(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4) {
    // Byte code:
    //   0: aload #5
    //   2: aload_1
    //   3: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   6: pop
    //   7: iload_2
    //   8: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   11: invokevirtual GetCode : ()I
    //   14: if_icmpne -> 24
    //   17: bipush #9
    //   19: istore #7
    //   21: goto -> 45
    //   24: iload_2
    //   25: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   28: invokevirtual GetCode : ()I
    //   31: if_icmpne -> 41
    //   34: bipush #14
    //   36: istore #7
    //   38: goto -> 45
    //   41: bipush #11
    //   43: istore #7
    //   45: iload #7
    //   47: anewarray java/lang/String
    //   50: astore #6
    //   52: aload_3
    //   53: ifnull -> 99
    //   56: aload_3
    //   57: invokevirtual length : ()I
    //   60: ifeq -> 99
    //   63: aload_3
    //   64: ldc_w '|'
    //   67: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   70: ifeq -> 99
    //   73: aload_3
    //   74: ldc_w '2|'
    //   77: invokevirtual equals : (Ljava/lang/Object;)Z
    //   80: ifne -> 99
    //   83: aload_3
    //   84: ldc_w '0|'
    //   87: invokevirtual equals : (Ljava/lang/Object;)Z
    //   90: ifne -> 99
    //   93: iconst_1
    //   94: istore #7
    //   96: goto -> 102
    //   99: iconst_0
    //   100: istore #7
    //   102: getstatic com/roadtrack/onstar/Enums$ActionResultCode.NoResult : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   105: astore #8
    //   107: iload #7
    //   109: ifeq -> 121
    //   112: aload_3
    //   113: ldc_w '\|'
    //   116: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   119: astore #6
    //   121: iload_2
    //   122: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   125: invokevirtual GetCode : ()I
    //   128: if_icmpne -> 620
    //   131: iload #7
    //   133: ifeq -> 383
    //   136: aload #6
    //   138: ifnull -> 383
    //   141: aload #6
    //   143: arraylength
    //   144: bipush #6
    //   146: if_icmple -> 383
    //   149: aload #6
    //   151: iconst_3
    //   152: aaload
    //   153: invokevirtual isEmpty : ()Z
    //   156: ifne -> 266
    //   159: aload #6
    //   161: iconst_4
    //   162: aaload
    //   163: invokevirtual isEmpty : ()Z
    //   166: ifne -> 266
    //   169: aload #6
    //   171: iconst_3
    //   172: aaload
    //   173: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Double;
    //   176: invokevirtual doubleValue : ()D
    //   179: dconst_0
    //   180: dcmpl
    //   181: ifne -> 253
    //   184: aload #6
    //   186: iconst_4
    //   187: aaload
    //   188: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Double;
    //   191: invokevirtual doubleValue : ()D
    //   194: dconst_0
    //   195: dcmpl
    //   196: ifne -> 253
    //   199: new com/roadtrack/onstar/DAO/DBFunctions
    //   202: dup
    //   203: aload_1
    //   204: invokespecial <init> : (Landroid/content/Context;)V
    //   207: aload #5
    //   209: invokevirtual getLastFindMe : (Ljava/lang/String;)[Ljava/lang/String;
    //   212: astore #4
    //   214: aload #6
    //   216: iconst_3
    //   217: aload #4
    //   219: iconst_0
    //   220: aaload
    //   221: aastore
    //   222: aload #6
    //   224: iconst_4
    //   225: aload #4
    //   227: iconst_1
    //   228: aaload
    //   229: aastore
    //   230: aload #4
    //   232: iconst_2
    //   233: aaload
    //   234: astore_3
    //   235: aload #6
    //   237: iconst_5
    //   238: ldc ''
    //   240: aastore
    //   241: aload #6
    //   243: bipush #6
    //   245: aload #4
    //   247: iconst_3
    //   248: aaload
    //   249: aastore
    //   250: goto -> 317
    //   253: aconst_null
    //   254: aload #6
    //   256: bipush #7
    //   258: aaload
    //   259: invokestatic getUTCDateTime : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   262: astore_3
    //   263: goto -> 317
    //   266: new com/roadtrack/onstar/DAO/DBFunctions
    //   269: dup
    //   270: aload_1
    //   271: invokespecial <init> : (Landroid/content/Context;)V
    //   274: aload #5
    //   276: invokevirtual getLastFindMe : (Ljava/lang/String;)[Ljava/lang/String;
    //   279: astore #4
    //   281: aload #6
    //   283: iconst_3
    //   284: aload #4
    //   286: iconst_0
    //   287: aaload
    //   288: aastore
    //   289: aload #6
    //   291: iconst_4
    //   292: aload #4
    //   294: iconst_1
    //   295: aaload
    //   296: aastore
    //   297: aload #4
    //   299: iconst_2
    //   300: aaload
    //   301: astore_3
    //   302: aload #6
    //   304: iconst_5
    //   305: ldc ''
    //   307: aastore
    //   308: aload #6
    //   310: bipush #6
    //   312: aload #4
    //   314: iconst_3
    //   315: aaload
    //   316: aastore
    //   317: aload #6
    //   319: iconst_1
    //   320: aaload
    //   321: ldc '0'
    //   323: invokevirtual equals : (Ljava/lang/Object;)Z
    //   326: ifne -> 359
    //   329: aload #6
    //   331: iconst_1
    //   332: aaload
    //   333: ldc_w '3'
    //   336: invokevirtual equals : (Ljava/lang/Object;)Z
    //   339: ifeq -> 345
    //   342: goto -> 359
    //   345: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   348: astore #8
    //   350: aload_3
    //   351: astore #4
    //   353: aload #8
    //   355: astore_3
    //   356: goto -> 472
    //   359: aload #6
    //   361: iconst_1
    //   362: aaload
    //   363: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   366: invokevirtual intValue : ()I
    //   369: invokestatic forValue : (I)Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   372: astore #8
    //   374: aload_3
    //   375: astore #4
    //   377: aload #8
    //   379: astore_3
    //   380: goto -> 472
    //   383: aload_0
    //   384: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   387: aload #5
    //   389: invokevirtual getLastFindMe : (Ljava/lang/String;)[Ljava/lang/String;
    //   392: astore_3
    //   393: aload #6
    //   395: iconst_3
    //   396: aload_3
    //   397: iconst_0
    //   398: aaload
    //   399: aastore
    //   400: aload #6
    //   402: iconst_4
    //   403: aload_3
    //   404: iconst_1
    //   405: aaload
    //   406: aastore
    //   407: aload_3
    //   408: iconst_2
    //   409: aaload
    //   410: astore #9
    //   412: aload #6
    //   414: iconst_5
    //   415: ldc ''
    //   417: aastore
    //   418: aload #6
    //   420: bipush #6
    //   422: aload_3
    //   423: iconst_3
    //   424: aaload
    //   425: aastore
    //   426: aload #9
    //   428: astore #4
    //   430: aload #8
    //   432: astore_3
    //   433: aload #6
    //   435: iconst_3
    //   436: aaload
    //   437: ldc '0'
    //   439: invokevirtual equals : (Ljava/lang/Object;)Z
    //   442: ifeq -> 472
    //   445: aload #9
    //   447: astore #4
    //   449: aload #8
    //   451: astore_3
    //   452: aload #6
    //   454: iconst_4
    //   455: aaload
    //   456: ldc '0'
    //   458: invokevirtual equals : (Ljava/lang/Object;)Z
    //   461: ifeq -> 472
    //   464: getstatic com/roadtrack/onstar/Enums$ActionResultCode.NoResult : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   467: astore_3
    //   468: aload #9
    //   470: astore #4
    //   472: aload #4
    //   474: ifnull -> 489
    //   477: aload #4
    //   479: astore #8
    //   481: aload #4
    //   483: invokevirtual length : ()I
    //   486: ifne -> 497
    //   489: aload #5
    //   491: aload_1
    //   492: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   495: astore #8
    //   497: aload_3
    //   498: astore_1
    //   499: aload_3
    //   500: getstatic com/roadtrack/onstar/Enums$ActionResultCode.NoResult : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   503: if_acmpeq -> 519
    //   506: aload_3
    //   507: astore_1
    //   508: aload_3
    //   509: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Fail : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   512: if_acmpeq -> 519
    //   515: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   518: astore_1
    //   519: aload_0
    //   520: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   523: aload_1
    //   524: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   527: aload_0
    //   528: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   531: aload #6
    //   533: iconst_3
    //   534: aaload
    //   535: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Float;
    //   538: invokevirtual floatValue : ()F
    //   541: invokevirtual setLatitude : (F)V
    //   544: aload_0
    //   545: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   548: aload #6
    //   550: iconst_4
    //   551: aaload
    //   552: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Float;
    //   555: invokevirtual floatValue : ()F
    //   558: invokevirtual setLongitude : (F)V
    //   561: aload_0
    //   562: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   565: aload #6
    //   567: iconst_5
    //   568: aaload
    //   569: invokevirtual setOdometer : (Ljava/lang/String;)V
    //   572: aload_0
    //   573: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   576: aload #6
    //   578: bipush #6
    //   580: aaload
    //   581: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   584: invokevirtual intValue : ()I
    //   587: invokevirtual setGpsStatus : (I)V
    //   590: aload_0
    //   591: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   594: aload #8
    //   596: invokevirtual setEventDateTime : (Ljava/lang/String;)V
    //   599: goto -> 2489
    //   602: astore_1
    //   603: aload_0
    //   604: getfield TAG : Ljava/lang/String;
    //   607: ldc_w 'Error:adjustWCFResult'
    //   610: aload_1
    //   611: invokevirtual getMessage : ()Ljava/lang/String;
    //   614: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   617: goto -> 2489
    //   620: iload_2
    //   621: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   624: invokevirtual GetCode : ()I
    //   627: if_icmpne -> 1846
    //   630: new com/roadtrack/onstar/VO/PIDVO
    //   633: dup
    //   634: aload_0
    //   635: getfield context : Landroid/content/Context;
    //   638: invokespecial <init> : (Landroid/content/Context;)V
    //   641: astore_3
    //   642: aload_0
    //   643: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   646: aload #8
    //   648: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   651: iload #7
    //   653: ifeq -> 1524
    //   656: aload #6
    //   658: ifnull -> 1524
    //   661: aload #6
    //   663: arraylength
    //   664: bipush #12
    //   666: if_icmple -> 1524
    //   669: aload_0
    //   670: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   673: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   676: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   679: ldc_w 'MM/dd/yyyy hh:mm:ss a'
    //   682: aload #6
    //   684: bipush #8
    //   686: aaload
    //   687: invokestatic getUTCDateTime : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   690: astore_1
    //   691: aload_0
    //   692: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   695: aload #6
    //   697: bipush #9
    //   699: aaload
    //   700: invokevirtual setModelo : (Ljava/lang/String;)V
    //   703: aload_0
    //   704: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   707: aload #6
    //   709: bipush #10
    //   711: aaload
    //   712: invokevirtual setVersion : (Ljava/lang/String;)V
    //   715: aload_0
    //   716: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   719: aload #6
    //   721: bipush #11
    //   723: aaload
    //   724: invokevirtual setYear : (Ljava/lang/String;)V
    //   727: aload_0
    //   728: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   731: aload #6
    //   733: bipush #12
    //   735: aaload
    //   736: invokevirtual trim : ()Ljava/lang/String;
    //   739: invokevirtual setPlaca : (Ljava/lang/String;)V
    //   742: aload #6
    //   744: arraylength
    //   745: bipush #13
    //   747: if_icmple -> 762
    //   750: aload_0
    //   751: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   754: aload #6
    //   756: bipush #13
    //   758: aaload
    //   759: invokevirtual setTPMSText : (Ljava/lang/String;)V
    //   762: aload #6
    //   764: arraylength
    //   765: bipush #14
    //   767: if_icmple -> 782
    //   770: aload_0
    //   771: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   774: aload #6
    //   776: bipush #14
    //   778: aaload
    //   779: invokevirtual setIsM300 : (Ljava/lang/String;)V
    //   782: aload #6
    //   784: arraylength
    //   785: bipush #16
    //   787: if_icmple -> 876
    //   790: aload_0
    //   791: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   794: aload #6
    //   796: bipush #15
    //   798: aaload
    //   799: invokevirtual setAutonomy_km : (Ljava/lang/String;)V
    //   802: aload #6
    //   804: bipush #15
    //   806: aaload
    //   807: invokevirtual isEmpty : ()Z
    //   810: ifne -> 858
    //   813: aload #6
    //   815: bipush #16
    //   817: aaload
    //   818: ldc_w '#'
    //   821: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   824: astore #4
    //   826: aload_0
    //   827: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   830: aload #4
    //   832: iconst_0
    //   833: aaload
    //   834: invokevirtual setAutonomy_status : (Ljava/lang/String;)V
    //   837: aload #4
    //   839: arraylength
    //   840: iconst_1
    //   841: if_icmple -> 876
    //   844: aload_0
    //   845: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   848: aload #4
    //   850: iconst_1
    //   851: aaload
    //   852: invokevirtual setAutonomy_text : (Ljava/lang/String;)V
    //   855: goto -> 876
    //   858: aload_0
    //   859: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   862: ldc ''
    //   864: invokevirtual setAutonomy_status : (Ljava/lang/String;)V
    //   867: aload_0
    //   868: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   871: ldc ''
    //   873: invokevirtual setAutonomy_text : (Ljava/lang/String;)V
    //   876: aload_0
    //   877: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   880: aload #6
    //   882: iconst_3
    //   883: aaload
    //   884: invokevirtual setOdometer : (Ljava/lang/String;)V
    //   887: aload_0
    //   888: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   891: aload #6
    //   893: iconst_4
    //   894: aaload
    //   895: invokevirtual setGas : (Ljava/lang/String;)V
    //   898: aload #6
    //   900: iconst_5
    //   901: aaload
    //   902: ifnull -> 976
    //   905: aload #6
    //   907: iconst_5
    //   908: aaload
    //   909: invokevirtual length : ()I
    //   912: ifle -> 965
    //   915: aload #6
    //   917: iconst_5
    //   918: aaload
    //   919: ldc_w '-'
    //   922: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   925: astore #4
    //   927: aload #4
    //   929: arraylength
    //   930: ifle -> 944
    //   933: aload_0
    //   934: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   937: aload #4
    //   939: iconst_0
    //   940: aaload
    //   941: invokevirtual setOilValue : (Ljava/lang/String;)V
    //   944: aload #4
    //   946: arraylength
    //   947: iconst_1
    //   948: if_icmple -> 976
    //   951: aload_0
    //   952: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   955: aload #4
    //   957: iconst_1
    //   958: aaload
    //   959: invokevirtual setOilStatus : (Ljava/lang/String;)V
    //   962: goto -> 976
    //   965: aload_0
    //   966: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   969: aload #6
    //   971: iconst_5
    //   972: aaload
    //   973: invokevirtual setOilValue : (Ljava/lang/String;)V
    //   976: aload_0
    //   977: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   980: aload #6
    //   982: bipush #6
    //   984: aaload
    //   985: invokevirtual setBatteryLevel : (Ljava/lang/String;)V
    //   988: aload #6
    //   990: bipush #7
    //   992: aaload
    //   993: invokevirtual length : ()I
    //   996: ifle -> 1207
    //   999: aload #6
    //   1001: bipush #7
    //   1003: aaload
    //   1004: ldc_w '/'
    //   1007: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   1010: astore #4
    //   1012: aload #4
    //   1014: arraylength
    //   1015: iconst_3
    //   1016: if_icmple -> 1207
    //   1019: aload #4
    //   1021: iconst_0
    //   1022: aaload
    //   1023: ldc_w '-'
    //   1026: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   1029: astore #5
    //   1031: aload #5
    //   1033: arraylength
    //   1034: ifle -> 1048
    //   1037: aload_0
    //   1038: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1041: aload #5
    //   1043: iconst_0
    //   1044: aaload
    //   1045: invokevirtual setTireStatusFL : (Ljava/lang/String;)V
    //   1048: aload #5
    //   1050: arraylength
    //   1051: iconst_1
    //   1052: if_icmple -> 1066
    //   1055: aload_0
    //   1056: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1059: aload #5
    //   1061: iconst_1
    //   1062: aaload
    //   1063: invokevirtual setTireFL : (Ljava/lang/String;)V
    //   1066: aload #4
    //   1068: iconst_1
    //   1069: aaload
    //   1070: ldc_w '-'
    //   1073: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   1076: astore #5
    //   1078: aload #5
    //   1080: arraylength
    //   1081: ifle -> 1095
    //   1084: aload_0
    //   1085: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1088: aload #5
    //   1090: iconst_0
    //   1091: aaload
    //   1092: invokevirtual setTireStatusRL : (Ljava/lang/String;)V
    //   1095: aload #5
    //   1097: arraylength
    //   1098: iconst_1
    //   1099: if_icmple -> 1113
    //   1102: aload_0
    //   1103: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1106: aload #5
    //   1108: iconst_1
    //   1109: aaload
    //   1110: invokevirtual setTireRL : (Ljava/lang/String;)V
    //   1113: aload #4
    //   1115: iconst_2
    //   1116: aaload
    //   1117: ldc_w '-'
    //   1120: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   1123: astore #5
    //   1125: aload #5
    //   1127: arraylength
    //   1128: ifle -> 1142
    //   1131: aload_0
    //   1132: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1135: aload #5
    //   1137: iconst_0
    //   1138: aaload
    //   1139: invokevirtual setTireStatusFR : (Ljava/lang/String;)V
    //   1142: aload #5
    //   1144: arraylength
    //   1145: iconst_1
    //   1146: if_icmple -> 1160
    //   1149: aload_0
    //   1150: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1153: aload #5
    //   1155: iconst_1
    //   1156: aaload
    //   1157: invokevirtual setTireFR : (Ljava/lang/String;)V
    //   1160: aload #4
    //   1162: iconst_3
    //   1163: aaload
    //   1164: ldc_w '-'
    //   1167: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   1170: astore #4
    //   1172: aload #4
    //   1174: arraylength
    //   1175: ifle -> 1189
    //   1178: aload_0
    //   1179: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1182: aload #4
    //   1184: iconst_0
    //   1185: aaload
    //   1186: invokevirtual setTireStatusRR : (Ljava/lang/String;)V
    //   1189: aload #4
    //   1191: arraylength
    //   1192: iconst_1
    //   1193: if_icmple -> 1207
    //   1196: aload_0
    //   1197: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1200: aload #4
    //   1202: iconst_1
    //   1203: aaload
    //   1204: invokevirtual setTireRR : (Ljava/lang/String;)V
    //   1207: aload_0
    //   1208: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1211: aload_1
    //   1212: invokevirtual setEventDateTime : (Ljava/lang/String;)V
    //   1215: aload_0
    //   1216: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1219: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1222: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   1225: aload_3
    //   1226: aload_0
    //   1227: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1230: invokevirtual getDeviceId : ()I
    //   1233: invokevirtual setDeviceId : (I)V
    //   1236: aload_3
    //   1237: aload_0
    //   1238: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1241: invokevirtual getMessageId : ()I
    //   1244: invokevirtual setMessageId : (I)V
    //   1247: aload_3
    //   1248: aload_0
    //   1249: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1252: invokevirtual getOdometer : ()Ljava/lang/String;
    //   1255: invokevirtual setOdometer : (Ljava/lang/String;)V
    //   1258: aload_3
    //   1259: aload_0
    //   1260: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1263: invokevirtual getGas : ()Ljava/lang/String;
    //   1266: invokevirtual setGas : (Ljava/lang/String;)V
    //   1269: aload_3
    //   1270: aload_0
    //   1271: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1274: invokevirtual getOilValue : ()Ljava/lang/String;
    //   1277: invokevirtual setOilValue : (Ljava/lang/String;)V
    //   1280: aload_3
    //   1281: aload_0
    //   1282: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1285: invokevirtual getOilStatus : ()Ljava/lang/String;
    //   1288: invokevirtual setOilStatus : (Ljava/lang/String;)V
    //   1291: aload_3
    //   1292: aload_0
    //   1293: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1296: invokevirtual getBatteryLevel : ()Ljava/lang/String;
    //   1299: invokevirtual setBatterylevel : (Ljava/lang/String;)V
    //   1302: aload_3
    //   1303: aload_0
    //   1304: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1307: invokevirtual getTireStatusFL : ()Ljava/lang/String;
    //   1310: invokevirtual setTire_status_fl : (Ljava/lang/String;)V
    //   1313: aload_3
    //   1314: aload_0
    //   1315: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1318: invokevirtual getTireFL : ()Ljava/lang/String;
    //   1321: invokevirtual setTire_fl : (Ljava/lang/String;)V
    //   1324: aload_3
    //   1325: aload_0
    //   1326: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1329: invokevirtual getTireStatusFR : ()Ljava/lang/String;
    //   1332: invokevirtual setTire_status_fr : (Ljava/lang/String;)V
    //   1335: aload_3
    //   1336: aload_0
    //   1337: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1340: invokevirtual getTireFR : ()Ljava/lang/String;
    //   1343: invokevirtual setTire_fr : (Ljava/lang/String;)V
    //   1346: aload_3
    //   1347: aload_0
    //   1348: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1351: invokevirtual getTireStatusRL : ()Ljava/lang/String;
    //   1354: invokevirtual setTire_status_rl : (Ljava/lang/String;)V
    //   1357: aload_3
    //   1358: aload_0
    //   1359: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1362: invokevirtual getTireRL : ()Ljava/lang/String;
    //   1365: invokevirtual setTire_rl : (Ljava/lang/String;)V
    //   1368: aload_3
    //   1369: aload_0
    //   1370: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1373: invokevirtual getTireStatusRR : ()Ljava/lang/String;
    //   1376: invokevirtual setTire_status_rr : (Ljava/lang/String;)V
    //   1379: aload_3
    //   1380: aload_0
    //   1381: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1384: invokevirtual getTireRR : ()Ljava/lang/String;
    //   1387: invokevirtual setTire_rr : (Ljava/lang/String;)V
    //   1390: aload_3
    //   1391: aload_0
    //   1392: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1395: invokevirtual getEventDateTime : ()Ljava/lang/String;
    //   1398: invokevirtual setEventDate : (Ljava/lang/String;)V
    //   1401: aload_3
    //   1402: aload_0
    //   1403: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1406: invokevirtual getModelo : ()Ljava/lang/String;
    //   1409: invokevirtual setModelo : (Ljava/lang/String;)V
    //   1412: aload_3
    //   1413: aload_0
    //   1414: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1417: invokevirtual getVersion : ()Ljava/lang/String;
    //   1420: invokevirtual setVersion : (Ljava/lang/String;)V
    //   1423: aload_3
    //   1424: aload_0
    //   1425: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1428: invokevirtual getYear : ()Ljava/lang/String;
    //   1431: invokevirtual setYear : (Ljava/lang/String;)V
    //   1434: aload_3
    //   1435: aload_0
    //   1436: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1439: invokevirtual getPlaca : ()Ljava/lang/String;
    //   1442: invokevirtual setPlaca : (Ljava/lang/String;)V
    //   1445: aload_3
    //   1446: aload_0
    //   1447: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1450: invokevirtual getTPMSText : ()Ljava/lang/String;
    //   1453: invokevirtual setTPMSText : (Ljava/lang/String;)V
    //   1456: aload_3
    //   1457: aload_0
    //   1458: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1461: invokevirtual getIsM300 : ()Ljava/lang/String;
    //   1464: invokevirtual setIsM300 : (Ljava/lang/String;)V
    //   1467: aload_3
    //   1468: aload_0
    //   1469: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1472: invokevirtual getAutonomy_km : ()Ljava/lang/String;
    //   1475: invokevirtual setAutonomy_km : (Ljava/lang/String;)V
    //   1478: aload_3
    //   1479: aload_0
    //   1480: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1483: invokevirtual getAutonomy_status : ()Ljava/lang/String;
    //   1486: invokevirtual setAutonomy_status : (Ljava/lang/String;)V
    //   1489: aload_3
    //   1490: aload_0
    //   1491: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1494: invokevirtual getAutonomy_text : ()Ljava/lang/String;
    //   1497: invokevirtual setAutonomy_text : (Ljava/lang/String;)V
    //   1500: aload_0
    //   1501: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1504: aload_0
    //   1505: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1508: invokevirtual getDeviceId : ()I
    //   1511: aload_3
    //   1512: invokevirtual addPid : (ILcom/roadtrack/onstar/VO/PIDVO;)J
    //   1515: pop2
    //   1516: aload_0
    //   1517: iconst_1
    //   1518: putfield pidHasInfo : Z
    //   1521: goto -> 2489
    //   1524: aload #5
    //   1526: aload_1
    //   1527: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   1530: pop
    //   1531: aload_0
    //   1532: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1535: aload #5
    //   1537: invokevirtual getLastPid : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/PIDVO;
    //   1540: astore_1
    //   1541: aload_1
    //   1542: ifnull -> 1828
    //   1545: aload_1
    //   1546: aload #5
    //   1548: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   1551: invokevirtual intValue : ()I
    //   1554: invokevirtual setDeviceId : (I)V
    //   1557: aload_0
    //   1558: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1561: aload_1
    //   1562: invokevirtual getModelo : ()Ljava/lang/String;
    //   1565: invokevirtual setModelo : (Ljava/lang/String;)V
    //   1568: aload_0
    //   1569: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1572: aload_1
    //   1573: invokevirtual getVersion : ()Ljava/lang/String;
    //   1576: invokevirtual setVersion : (Ljava/lang/String;)V
    //   1579: aload_0
    //   1580: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1583: aload_1
    //   1584: invokevirtual getYear : ()Ljava/lang/String;
    //   1587: invokevirtual setYear : (Ljava/lang/String;)V
    //   1590: aload_0
    //   1591: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1594: aload_1
    //   1595: invokevirtual getPlaca : ()Ljava/lang/String;
    //   1598: invokevirtual setPlaca : (Ljava/lang/String;)V
    //   1601: aload_0
    //   1602: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1605: aload_1
    //   1606: invokevirtual getTPMSText : ()Ljava/lang/String;
    //   1609: invokevirtual setTPMSText : (Ljava/lang/String;)V
    //   1612: aload_0
    //   1613: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1616: aload_1
    //   1617: invokevirtual getIsM300 : ()Ljava/lang/String;
    //   1620: invokevirtual setIsM300 : (Ljava/lang/String;)V
    //   1623: aload_0
    //   1624: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1627: aload_1
    //   1628: invokevirtual getAutonomy_km : ()Ljava/lang/String;
    //   1631: invokevirtual setAutonomy_km : (Ljava/lang/String;)V
    //   1634: aload_0
    //   1635: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1638: aload_1
    //   1639: invokevirtual getAutonomy_status : ()Ljava/lang/String;
    //   1642: invokevirtual setAutonomy_status : (Ljava/lang/String;)V
    //   1645: aload_0
    //   1646: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1649: aload_1
    //   1650: invokevirtual getAutonomy_text : ()Ljava/lang/String;
    //   1653: invokevirtual setAutonomy_text : (Ljava/lang/String;)V
    //   1656: aload_0
    //   1657: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1660: aload_1
    //   1661: invokevirtual getOdometer : ()Ljava/lang/String;
    //   1664: invokevirtual setOdometer : (Ljava/lang/String;)V
    //   1667: aload_0
    //   1668: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1671: aload_1
    //   1672: invokevirtual getGas : ()Ljava/lang/String;
    //   1675: invokevirtual setGas : (Ljava/lang/String;)V
    //   1678: aload_0
    //   1679: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1682: aload_1
    //   1683: invokevirtual getOilValue : ()Ljava/lang/String;
    //   1686: invokevirtual setOilValue : (Ljava/lang/String;)V
    //   1689: aload_0
    //   1690: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1693: aload_1
    //   1694: invokevirtual getOilStatus : ()Ljava/lang/String;
    //   1697: invokevirtual setOilStatus : (Ljava/lang/String;)V
    //   1700: aload_0
    //   1701: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1704: aload_1
    //   1705: invokevirtual getBatterylevel : ()Ljava/lang/String;
    //   1708: invokevirtual setBatteryLevel : (Ljava/lang/String;)V
    //   1711: aload_0
    //   1712: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1715: aload_1
    //   1716: invokevirtual getTire_status_fl : ()Ljava/lang/String;
    //   1719: invokevirtual setTireStatusFL : (Ljava/lang/String;)V
    //   1722: aload_0
    //   1723: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1726: aload_1
    //   1727: invokevirtual getTire_fl : ()Ljava/lang/String;
    //   1730: invokevirtual setTireFL : (Ljava/lang/String;)V
    //   1733: aload_0
    //   1734: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1737: aload_1
    //   1738: invokevirtual getTire_status_fr : ()Ljava/lang/String;
    //   1741: invokevirtual setTireStatusFR : (Ljava/lang/String;)V
    //   1744: aload_0
    //   1745: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1748: aload_1
    //   1749: invokevirtual getTire_fr : ()Ljava/lang/String;
    //   1752: invokevirtual setTireFR : (Ljava/lang/String;)V
    //   1755: aload_0
    //   1756: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1759: aload_1
    //   1760: invokevirtual getTire_status_rl : ()Ljava/lang/String;
    //   1763: invokevirtual setTireStatusRL : (Ljava/lang/String;)V
    //   1766: aload_0
    //   1767: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1770: aload_1
    //   1771: invokevirtual getTire_rl : ()Ljava/lang/String;
    //   1774: invokevirtual setTireRL : (Ljava/lang/String;)V
    //   1777: aload_0
    //   1778: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1781: aload_1
    //   1782: invokevirtual getTire_status_rr : ()Ljava/lang/String;
    //   1785: invokevirtual setTireStatusRR : (Ljava/lang/String;)V
    //   1788: aload_0
    //   1789: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1792: aload_1
    //   1793: invokevirtual getTire_rr : ()Ljava/lang/String;
    //   1796: invokevirtual setTireRR : (Ljava/lang/String;)V
    //   1799: aload_0
    //   1800: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1803: aload_1
    //   1804: invokevirtual getEventDate : ()Ljava/lang/String;
    //   1807: invokevirtual setEventDateTime : (Ljava/lang/String;)V
    //   1810: aload_0
    //   1811: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1814: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1817: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   1820: aload_0
    //   1821: iconst_1
    //   1822: putfield pidHasInfo : Z
    //   1825: goto -> 2489
    //   1828: aload_0
    //   1829: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1832: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Fail : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1835: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   1838: aload_0
    //   1839: iconst_0
    //   1840: putfield pidHasInfo : Z
    //   1843: goto -> 2489
    //   1846: iload_2
    //   1847: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   1850: invokevirtual GetCode : ()I
    //   1853: if_icmpne -> 1936
    //   1856: aload_3
    //   1857: invokestatic getResponseFromString : (Ljava/lang/String;)Ljava/util/List;
    //   1860: astore_1
    //   1861: aload_1
    //   1862: ifnull -> 1923
    //   1865: aload_1
    //   1866: invokeinterface size : ()I
    //   1871: ifle -> 1923
    //   1874: aload_0
    //   1875: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1878: aload_1
    //   1879: iconst_0
    //   1880: anewarray android/database/sqlite/SQLiteDatabase
    //   1883: invokevirtual insertDTC : (Ljava/util/List;[Landroid/database/sqlite/SQLiteDatabase;)J
    //   1886: invokestatic valueOf : (J)Ljava/lang/Long;
    //   1889: invokevirtual longValue : ()J
    //   1892: lconst_0
    //   1893: lcmp
    //   1894: ifle -> 1910
    //   1897: aload_0
    //   1898: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1901: getstatic com/roadtrack/onstar/Enums$WSResultId.OK : Lcom/roadtrack/onstar/Enums$WSResultId;
    //   1904: invokevirtual setSendCommandResultId : (Lcom/roadtrack/onstar/Enums$WSResultId;)V
    //   1907: goto -> 2489
    //   1910: aload_0
    //   1911: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1914: getstatic com/roadtrack/onstar/Enums$WSResultId.NoResult : Lcom/roadtrack/onstar/Enums$WSResultId;
    //   1917: invokevirtual setSendCommandResultId : (Lcom/roadtrack/onstar/Enums$WSResultId;)V
    //   1920: goto -> 2489
    //   1923: aload_0
    //   1924: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1927: getstatic com/roadtrack/onstar/Enums$WSResultId.NoResult : Lcom/roadtrack/onstar/Enums$WSResultId;
    //   1930: invokevirtual setSendCommandResultId : (Lcom/roadtrack/onstar/Enums$WSResultId;)V
    //   1933: goto -> 2489
    //   1936: aload_0
    //   1937: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1940: invokevirtual getIdResponseOriginal : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1943: getstatic com/roadtrack/onstar/Enums$ActionResultCode.NoResult : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1946: if_acmpeq -> 2143
    //   1949: aload_0
    //   1950: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1953: invokevirtual getIdResponseOriginal : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1956: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Fail : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1959: if_acmpne -> 1965
    //   1962: goto -> 2143
    //   1965: aload_0
    //   1966: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1969: aload_0
    //   1970: getfield accountId : Ljava/lang/String;
    //   1973: aload #5
    //   1975: aload #6
    //   1977: iconst_0
    //   1978: aaload
    //   1979: iconst_0
    //   1980: invokevirtual userDataTableHandler : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Z
    //   1983: ifne -> 2489
    //   1986: aload #6
    //   1988: ifnull -> 2489
    //   1991: aload #6
    //   1993: arraylength
    //   1994: bipush #10
    //   1996: if_icmple -> 2489
    //   1999: ldc_w 'MM/dd/yyyy hh:mm:ss a'
    //   2002: aload #6
    //   2004: iconst_4
    //   2005: aaload
    //   2006: invokestatic getUTCDateTime : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   2009: astore_3
    //   2010: aload #6
    //   2012: iconst_0
    //   2013: aaload
    //   2014: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   2017: invokevirtual intValue : ()I
    //   2020: invokestatic forValue : (I)Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2023: astore_1
    //   2024: aload_0
    //   2025: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2028: aload_1
    //   2029: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   2032: aload_0
    //   2033: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2036: aload #6
    //   2038: iconst_3
    //   2039: aaload
    //   2040: invokevirtual setNickname : (Ljava/lang/String;)V
    //   2043: aload_0
    //   2044: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2047: aload #6
    //   2049: bipush #6
    //   2051: aaload
    //   2052: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Float;
    //   2055: invokevirtual floatValue : ()F
    //   2058: invokevirtual setLatitude : (F)V
    //   2061: aload_0
    //   2062: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2065: aload #6
    //   2067: iconst_5
    //   2068: aaload
    //   2069: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Float;
    //   2072: invokevirtual floatValue : ()F
    //   2075: invokevirtual setLongitude : (F)V
    //   2078: aload_0
    //   2079: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2082: aload #6
    //   2084: bipush #7
    //   2086: aaload
    //   2087: invokevirtual setOdometer : (Ljava/lang/String;)V
    //   2090: aload_0
    //   2091: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2094: aload #6
    //   2096: bipush #8
    //   2098: aaload
    //   2099: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   2102: invokevirtual intValue : ()I
    //   2105: invokevirtual setGpsStatus : (I)V
    //   2108: aload_0
    //   2109: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2112: aload #6
    //   2114: bipush #9
    //   2116: aaload
    //   2117: invokevirtual setAddress : (Ljava/lang/String;)V
    //   2120: aload_0
    //   2121: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2124: aload #6
    //   2126: bipush #10
    //   2128: aaload
    //   2129: invokevirtual setSessionKey : (Ljava/lang/String;)V
    //   2132: aload_0
    //   2133: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2136: aload_3
    //   2137: invokevirtual setEventDateTime : (Ljava/lang/String;)V
    //   2140: goto -> 2489
    //   2143: aload_0
    //   2144: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2147: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2150: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2153: if_acmpeq -> 2218
    //   2156: aload_0
    //   2157: getfield gpsSttusNewPositionBejoreGlm : I
    //   2160: iflt -> 2218
    //   2163: aload_0
    //   2164: getfield epochTmmeNewPositionMapBejoreGlm : Ljava/lang/String;
    //   2167: ldc ''
    //   2169: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2172: ifne -> 2218
    //   2175: aload_0
    //   2176: invokespecial changeFormatEpoch : ()V
    //   2179: new java/lang/StringBuilder
    //   2182: astore_1
    //   2183: aload_1
    //   2184: invokespecial <init> : ()V
    //   2187: aload_1
    //   2188: aload #5
    //   2190: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2193: pop
    //   2194: aload_1
    //   2195: ldc_w ': Waiting'
    //   2198: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2201: pop
    //   2202: ldc_w 'SendComand Epoch OK FIN1'
    //   2205: ldc_w 'EPOCH'
    //   2208: aload_1
    //   2209: invokevirtual toString : ()Ljava/lang/String;
    //   2212: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2215: goto -> 2489
    //   2218: aload_0
    //   2219: getfield aux_result : Ljava/lang/String;
    //   2222: invokevirtual toString : ()Ljava/lang/String;
    //   2225: ldc_w 'Network error'
    //   2228: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2231: ifeq -> 2296
    //   2234: aload_0
    //   2235: getfield gpsSttusNewPositionBejoreGlm : I
    //   2238: iflt -> 2296
    //   2241: aload_0
    //   2242: getfield epochTmmeNewPositionMapBejoreGlm : Ljava/lang/String;
    //   2245: ldc ''
    //   2247: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2250: ifne -> 2296
    //   2253: aload_0
    //   2254: invokespecial changeFormatEpoch : ()V
    //   2257: new java/lang/StringBuilder
    //   2260: astore_1
    //   2261: aload_1
    //   2262: invokespecial <init> : ()V
    //   2265: aload_1
    //   2266: aload #5
    //   2268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2271: pop
    //   2272: aload_1
    //   2273: ldc_w ': Waiting'
    //   2276: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2279: pop
    //   2280: ldc_w 'SendComand Epoch OK FIN2'
    //   2283: ldc_w 'EPOCH'
    //   2286: aload_1
    //   2287: invokevirtual toString : ()Ljava/lang/String;
    //   2290: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2293: goto -> 2489
    //   2296: aload_0
    //   2297: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   2300: aload_0
    //   2301: getfield accountId : Ljava/lang/String;
    //   2304: aload #5
    //   2306: aload #6
    //   2308: iconst_0
    //   2309: aaload
    //   2310: iconst_0
    //   2311: invokevirtual userDataTableHandler : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Z
    //   2314: ifne -> 2489
    //   2317: aload #6
    //   2319: ifnull -> 2489
    //   2322: aload #6
    //   2324: arraylength
    //   2325: bipush #10
    //   2327: if_icmple -> 2489
    //   2330: ldc_w 'MM/dd/yyyy hh:mm:ss a'
    //   2333: aload #6
    //   2335: iconst_4
    //   2336: aaload
    //   2337: invokestatic getUTCDateTime : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   2340: astore_3
    //   2341: aload #6
    //   2343: iconst_0
    //   2344: aaload
    //   2345: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   2348: invokevirtual intValue : ()I
    //   2351: invokestatic forValue : (I)Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2354: astore_1
    //   2355: aload_0
    //   2356: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2359: aload_1
    //   2360: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   2363: aload_0
    //   2364: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2367: aload #6
    //   2369: iconst_3
    //   2370: aaload
    //   2371: invokevirtual setNickname : (Ljava/lang/String;)V
    //   2374: aload_0
    //   2375: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2378: aload #6
    //   2380: bipush #6
    //   2382: aaload
    //   2383: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Float;
    //   2386: invokevirtual floatValue : ()F
    //   2389: invokevirtual setLatitude : (F)V
    //   2392: aload_0
    //   2393: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2396: aload #6
    //   2398: iconst_5
    //   2399: aaload
    //   2400: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Float;
    //   2403: invokevirtual floatValue : ()F
    //   2406: invokevirtual setLongitude : (F)V
    //   2409: aload_0
    //   2410: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2413: aload #6
    //   2415: bipush #7
    //   2417: aaload
    //   2418: invokevirtual setOdometer : (Ljava/lang/String;)V
    //   2421: aload_0
    //   2422: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2425: aload #6
    //   2427: bipush #8
    //   2429: aaload
    //   2430: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   2433: invokevirtual intValue : ()I
    //   2436: invokevirtual setGpsStatus : (I)V
    //   2439: aload_0
    //   2440: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2443: aload #6
    //   2445: bipush #9
    //   2447: aaload
    //   2448: invokevirtual setAddress : (Ljava/lang/String;)V
    //   2451: aload_0
    //   2452: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2455: aload #6
    //   2457: bipush #10
    //   2459: aaload
    //   2460: invokevirtual setSessionKey : (Ljava/lang/String;)V
    //   2463: aload_0
    //   2464: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2467: aload_3
    //   2468: invokevirtual setEventDateTime : (Ljava/lang/String;)V
    //   2471: goto -> 2489
    //   2474: astore_1
    //   2475: aload_0
    //   2476: getfield TAG : Ljava/lang/String;
    //   2479: ldc_w 'Error:adjustWCFResult'
    //   2482: aload_1
    //   2483: invokevirtual getMessage : ()Ljava/lang/String;
    //   2486: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2489: aload_0
    //   2490: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2493: areturn
    // Exception table:
    //   from	to	target	type
    //   519	599	602	java/lang/NumberFormatException
    //   1936	1962	2474	java/lang/Exception
    //   1965	1986	2474	java/lang/Exception
    //   1991	2140	2474	java/lang/Exception
    //   2143	2215	2474	java/lang/Exception
    //   2218	2293	2474	java/lang/Exception
    //   2296	2317	2474	java/lang/Exception
    //   2322	2471	2474	java/lang/Exception
  }
  
  private void changeFormatEpoch() {
    Date date = new Date(Long.parseLong(this.epochTmmeNewPositionMapBejoreGlm) * 1000L);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Gmt/UTC"));
    String str = simpleDateFormat.format(date);
    this.result.setIdResponse(Enums.ActionResultCode.Activated);
    this.result.setLatitude(this.latSendComandBejoreGlm.floatValue());
    this.result.setLongitude(this.longSendComandBejoreGlm.floatValue());
    this.result.setOdometer("");
    this.result.setGpsStatus(this.gpsSttusNewPositionBejoreGlm);
    this.result.setEventDateTime(str);
  }
  
  private void changeIconDrawable(Drawable paramDrawable, boolean paramBoolean) {
    if (paramBoolean) {
      TextView textView = this.tvControl;
      if (textView != null) {
        textView.setVisibility(0);
        this.tvControl.setCompoundDrawablesWithIntrinsicBounds(null, paramDrawable, null, null);
        this.tvControl.invalidate();
      } 
      ImageView imageView = this.ivControl;
      if (imageView != null) {
        imageView.setVisibility(0);
        this.ivControl.setImageDrawable(paramDrawable);
        this.ivControl.invalidate();
      } 
      Button button = this.btControl;
      if (button != null) {
        button.setVisibility(0);
        this.btControl.setCompoundDrawables(null, paramDrawable, null, null);
        this.btControl.invalidate();
      } 
    } else {
      TextView textView = this.tvControl;
      if (textView != null)
        textView.setVisibility(8); 
      ImageView imageView = this.ivControl;
      if (imageView != null)
        imageView.setVisibility(8); 
      Button button = this.btControl;
      if (button != null)
        button.setVisibility(8); 
    } 
  }
  
  private long getTimeDifference(Date paramDate1, Date paramDate2, int paramInt) {
    Date date = paramDate1;
    if (paramDate1 == null)
      date = new Date(); 
    paramDate1 = new Date(date.getTime() - paramDate2.getTime());
    long l3 = paramDate1.getTime() / 1000L / 60L / 60L;
    long l2 = paramDate1.getTime() / 1000L / 60L;
    long l1 = paramDate1.getTime() / 1000L;
    return (paramInt == 11) ? l3 : ((paramInt == 12) ? l2 : l1);
  }
  
  private int parseStatus(int paramInt) {
    switch (paramInt) {
      default:
        return 0;
      case 2131165687:
        return 18;
      case 2131165684:
        return 7;
      case 2131165632:
        return 4;
      case 2131165628:
        return 13;
      case 2131165626:
        return 2;
      case 2131165624:
        return 14;
      case 2131165620:
        return 3;
      case 2131165618:
        return 15;
      case 2131165583:
        return 16;
      case 2131165581:
        return 5;
      case 2131165302:
        return 17;
      case 2131165301:
        return 6;
      case 1:
        break;
    } 
    return 1;
  }
  
  private void savelatLongNewPositionBeforSearchGlim(String[] paramArrayOfString) {
    if (paramArrayOfString != null)
      try {
        if (!paramArrayOfString.equals("") && paramArrayOfString.length > 5) {
          this.latSendComandBejoreGlm = Float.valueOf(Float.parseFloat(paramArrayOfString[3]));
          this.longSendComandBejoreGlm = Float.valueOf(Float.parseFloat(paramArrayOfString[4]));
          this.gpsSttusNewPositionBejoreGlm = Integer.parseInt(paramArrayOfString[5]);
          this.epochTmmeNewPositionMapBejoreGlm = paramArrayOfString[6];
        } 
      } catch (Exception exception) {
        Utilities.escribeArchivo(this.TAG, "Error: getLatLOngSendomand", exception.toString());
      }  
  }
  
  private void stopThread(Thread paramThread) {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/BO/ActionsProcess}} */
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/BO/ActionsProcess}} */
  }
  
  private void wakeUpCarSetStatus(String paramString) {
    Activity activity1;
    WakeUpCar wakeUpCar = new WakeUpCar();
    Activity activity2 = WakeUpCar.wakeActivity;
    if (activity2 != null)
      wakeUpCar = (WakeUpCar)activity2; 
    if (paramString.equals(Enums.Services.DTCAction.GetCodeString()) || paramString.equals(Enums.Services.pid.GetCodeString()) || paramString.equals(Enums.Services.SendPNDNavigationCommand.GetCodeString())) {
      if (this.result.getIdResponseOriginal() == Enums.ActionResultCode.Fail) {
        if (Utilities.isActivityRunning(this.context, WakeUpCar.class) || WakeUpCar.isOnPause) {
          wakeUpCar.setStatus("3");
        } else if (Utilities.isActivityRunning(this.context, RemoteDiagnosticActivity.class) || RemoteDiagnosticActivity.isOnPause) {
          activity1 = RemoteDiagnosticActivity._activity;
          if (activity1 != null)
            ((RemoteDiagnosticActivity)activity1).setStatus("3"); 
        } 
      } else if (this.result.getIdResponseOriginal() == Enums.ActionResultCode.NoResult) {
        if (!activity1.equals(Enums.Services.SendPNDNavigationCommand.GetCodeString())) {
          if (Utilities.isActivityRunning(this.context, WakeUpCar.class) || WakeUpCar.isOnPause) {
            wakeUpCar.setStatus("finish");
          } else if (Utilities.isActivityRunning(this.context, RemoteDiagnosticActivity.class) || RemoteDiagnosticActivity.isOnPause) {
            activity1 = RemoteDiagnosticActivity._activity;
            if (activity1 != null)
              ((RemoteDiagnosticActivity)activity1).hideWakeupCar(); 
          } 
        } else if (Utilities.isActivityRunning(this.context, WakeUpCar.class) || WakeUpCar.isOnPause) {
          wakeUpCar.setStatus("0");
        } 
      } else if (this.result.getIdResponseOriginal() == Enums.ActionResultCode.Activated) {
        if (Utilities.isActivityRunning(this.context, WakeUpCar.class) || WakeUpCar.isOnPause) {
          wakeUpCar.setStatus("finish");
        } else if (Utilities.isActivityRunning(this.context, RemoteDiagnosticActivity.class) || RemoteDiagnosticActivity.isOnPause) {
          activity1 = RemoteDiagnosticActivity._activity;
          if (activity1 != null)
            ((RemoteDiagnosticActivity)activity1).hideWakeupCar(); 
        } 
      } 
    } else if (activity1.equals(Enums.Services.FindMe.GetCodeString())) {
      if (this.result.getIdResponseOriginal() == Enums.ActionResultCode.NoResult || this.result.getIdResponseOriginal() == Enums.ActionResultCode.Fail) {
        if (this.result.getIdResponse() != Enums.ActionResultCode.Activated && this.gpsSttusNewPositionBejoreGlm >= 0 && !this.epochTmmeNewPositionMapBejoreGlm.equals("")) {
          changeFormatEpoch();
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(this.deviceId);
          stringBuilder.append(": Waiting");
          Utilities.escribeArchivo("SendComand Epoch OK FIN2", "EPOCH", stringBuilder.toString());
        } else if (this.aux_result.equals("Network error") && this.gpsSttusNewPositionBejoreGlm >= 0 && !this.epochTmmeNewPositionMapBejoreGlm.equals("")) {
          changeFormatEpoch();
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(this.deviceId);
          stringBuilder.append(": Waiting");
          Utilities.escribeArchivo("SendComand Epoch OK FIN2", "EPOCH", stringBuilder.toString());
        } else if (Utilities.isActivityRunning(this.context, WakeUpCar.class) || WakeUpCar.isOnPause) {
          if (this.result.getIdResponseOriginal() == Enums.ActionResultCode.Fail) {
            String[] arrayOfString = this.dbFun.getLastFindMe(this.deviceId);
            if (arrayOfString[0].equals("0") && arrayOfString[1].equals("0"))
              wakeUpCar.setStatus("3"); 
          } else if (Utilities.isActivityRunning(this.context, WakeUpCar.class) || WakeUpCar.isOnPause) {
            wakeUpCar.setStatus("finish");
          } 
        } 
      } else if (Utilities.isActivityRunning(this.context, WakeUpCar.class) || WakeUpCar.isOnPause) {
        if (this.result.getIdResponseOriginal() == Enums.ActionResultCode.Fail) {
          String[] arrayOfString = this.dbFun.getLastFindMe(this.deviceId);
          if (arrayOfString[0].equals("0") && arrayOfString[1].equals("0"))
            wakeUpCar.setStatus("3"); 
        } else if (Utilities.isActivityRunning(this.context, WakeUpCar.class) || WakeUpCar.isOnPause) {
          wakeUpCar.setStatus("finish");
        } 
      } 
    } 
    WakeUpCar.isOnPause = false;
    RemoteDiagnosticActivity.isOnPause = false;
  }
  
  protected String doInBackground(String... paramVarArgs) {
    // Byte code:
    //   0: aload_0
    //   1: new com/roadtrack/onstar/VO/ActionResultVO
    //   4: dup
    //   5: aload_0
    //   6: getfield context : Landroid/content/Context;
    //   9: aload_0
    //   10: getfield deviceId : Ljava/lang/String;
    //   13: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   16: invokevirtual intValue : ()I
    //   19: invokespecial <init> : (Landroid/content/Context;I)V
    //   22: putfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   25: aload_0
    //   26: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   29: iconst_0
    //   30: invokevirtual setMessageId : (I)V
    //   33: aload_0
    //   34: getfield actionId : Ljava/lang/String;
    //   37: ldc_w '1'
    //   40: invokevirtual equals : (Ljava/lang/Object;)Z
    //   43: istore #8
    //   45: iconst_1
    //   46: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   49: astore #14
    //   51: iload #8
    //   53: ifeq -> 60
    //   56: iconst_1
    //   57: putstatic com/roadtrack/onstar/MainActivity.actFindMe : I
    //   60: aload_1
    //   61: ifnull -> 5283
    //   64: aload_1
    //   65: arraylength
    //   66: iconst_5
    //   67: if_icmpge -> 73
    //   70: goto -> 5283
    //   73: aload_0
    //   74: getstatic com/roadtrack/onstar/BO/GlobalMembers.userLogged : Ljava/lang/String;
    //   77: putfield userName : Ljava/lang/String;
    //   80: aload_0
    //   81: bipush #13
    //   83: invokestatic toString : (I)Ljava/lang/String;
    //   86: putfield applicationSourceId : Ljava/lang/String;
    //   89: aload_0
    //   90: aload_1
    //   91: iconst_0
    //   92: aaload
    //   93: invokevirtual toString : ()Ljava/lang/String;
    //   96: putfield sessionKey : Ljava/lang/String;
    //   99: aload_0
    //   100: aload_1
    //   101: iconst_1
    //   102: aaload
    //   103: invokevirtual toString : ()Ljava/lang/String;
    //   106: putfield password : Ljava/lang/String;
    //   109: aload_0
    //   110: aload_1
    //   111: iconst_2
    //   112: aaload
    //   113: invokevirtual toString : ()Ljava/lang/String;
    //   116: putfield accountId : Ljava/lang/String;
    //   119: aload_0
    //   120: aload_1
    //   121: iconst_3
    //   122: aaload
    //   123: invokevirtual toString : ()Ljava/lang/String;
    //   126: putfield userId : Ljava/lang/String;
    //   129: aload_0
    //   130: aload_1
    //   131: iconst_4
    //   132: aaload
    //   133: invokevirtual toString : ()Ljava/lang/String;
    //   136: putfield csvParams : Ljava/lang/String;
    //   139: aload_0
    //   140: aload_1
    //   141: iconst_5
    //   142: aaload
    //   143: invokevirtual toString : ()Ljava/lang/String;
    //   146: putfield deviceCSVParams : Ljava/lang/String;
    //   149: getstatic com/roadtrack/onstar/BO/GlobalMembers.strPuertoA : Ljava/lang/String;
    //   152: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   155: invokevirtual intValue : ()I
    //   158: istore #7
    //   160: getstatic com/roadtrack/onstar/BO/GlobalMembers.strIPSocketGetCommand : Ljava/lang/String;
    //   163: astore #13
    //   165: aload_0
    //   166: getfield actionId : Ljava/lang/String;
    //   169: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   172: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   175: invokevirtual equals : (Ljava/lang/Object;)Z
    //   178: ifne -> 221
    //   181: aload_0
    //   182: getfield actionId : Ljava/lang/String;
    //   185: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   188: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   191: invokevirtual equals : (Ljava/lang/Object;)Z
    //   194: ifne -> 221
    //   197: aload_0
    //   198: getfield actionId : Ljava/lang/String;
    //   201: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   204: invokevirtual equals : (Ljava/lang/Object;)Z
    //   207: ifeq -> 213
    //   210: goto -> 221
    //   213: sipush #15000
    //   216: istore #4
    //   218: goto -> 226
    //   221: sipush #3000
    //   224: istore #4
    //   226: aload_0
    //   227: getfield extendGetcomman : Z
    //   230: ifeq -> 251
    //   233: sipush #300
    //   236: istore #5
    //   238: sipush #10000
    //   241: istore #4
    //   243: sipush #300
    //   246: istore #6
    //   248: goto -> 261
    //   251: sipush #150
    //   254: istore #5
    //   256: sipush #150
    //   259: istore #6
    //   261: getstatic com/roadtrack/onstar/BO/GlobalMembers.URL_WCF : Ljava/lang/String;
    //   264: astore_1
    //   265: getstatic com/roadtrack/onstar/BO/GlobalMembers.NAMESPACE_WCF : Ljava/lang/String;
    //   268: astore #11
    //   270: new java/lang/StringBuilder
    //   273: dup
    //   274: invokespecial <init> : ()V
    //   277: astore #12
    //   279: aload #12
    //   281: aload #11
    //   283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: pop
    //   287: aload #12
    //   289: ldc_w 'IService1/'
    //   292: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: pop
    //   296: aload #12
    //   298: ldc_w 'SendCommand'
    //   301: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: pop
    //   305: aload #12
    //   307: invokevirtual toString : ()Ljava/lang/String;
    //   310: astore #15
    //   312: new java/lang/StringBuilder
    //   315: dup
    //   316: invokespecial <init> : ()V
    //   319: astore #12
    //   321: aload #12
    //   323: aload #11
    //   325: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: pop
    //   329: aload #12
    //   331: ldc_w 'IService1/'
    //   334: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: pop
    //   338: aload #12
    //   340: ldc_w 'GetCommandStatus'
    //   343: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: pop
    //   347: aload #12
    //   349: invokevirtual toString : ()Ljava/lang/String;
    //   352: pop
    //   353: new java/util/LinkedHashMap
    //   356: dup
    //   357: invokespecial <init> : ()V
    //   360: astore #12
    //   362: getstatic com/roadtrack/onstar/Enums$Category.Other : Lcom/roadtrack/onstar/Enums$Category;
    //   365: invokevirtual toString : ()Ljava/lang/String;
    //   368: astore #21
    //   370: aload_0
    //   371: getfield actionId : Ljava/lang/String;
    //   374: invokestatic parseInt : (Ljava/lang/String;)I
    //   377: invokestatic GetName : (I)Ljava/lang/String;
    //   380: astore #18
    //   382: aload_0
    //   383: getfield deviceId : Ljava/lang/String;
    //   386: aload_0
    //   387: getfield context : Landroid/content/Context;
    //   390: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   393: astore #16
    //   395: getstatic com/roadtrack/onstar/Enums$TypeItem.Historical : Lcom/roadtrack/onstar/Enums$TypeItem;
    //   398: invokevirtual toString : ()Ljava/lang/String;
    //   401: astore #20
    //   403: aload_0
    //   404: getfield deviceId : Ljava/lang/String;
    //   407: aload_0
    //   408: getfield context : Landroid/content/Context;
    //   411: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   414: astore #17
    //   416: aload_0
    //   417: getfield context : Landroid/content/Context;
    //   420: invokestatic GCMHeartBeat : (Landroid/content/Context;)V
    //   423: aload_0
    //   424: getfield actionId : Ljava/lang/String;
    //   427: getstatic com/roadtrack/onstar/Enums$Services.Speed : Lcom/roadtrack/onstar/Enums$Services;
    //   430: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   433: invokevirtual equals : (Ljava/lang/Object;)Z
    //   436: ifeq -> 483
    //   439: aload_0
    //   440: getfield csvParams : Ljava/lang/String;
    //   443: astore #19
    //   445: aload #19
    //   447: ifnull -> 483
    //   450: aload #19
    //   452: invokevirtual length : ()I
    //   455: ifle -> 483
    //   458: aload_0
    //   459: aload_0
    //   460: getfield csvParams : Ljava/lang/String;
    //   463: ldc_w ','
    //   466: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   469: iconst_0
    //   470: aaload
    //   471: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Double;
    //   474: invokevirtual doubleValue : ()D
    //   477: putfield speedLimit : D
    //   480: goto -> 483
    //   483: aload_0
    //   484: getfield actionId : Ljava/lang/String;
    //   487: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   490: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   493: invokevirtual equals : (Ljava/lang/Object;)Z
    //   496: ifne -> 579
    //   499: new com/roadtrack/onstar/VO/Historical
    //   502: dup
    //   503: aload #21
    //   505: aload #18
    //   507: aload #16
    //   509: aload #20
    //   511: ldc ''
    //   513: ldc ''
    //   515: ldc ''
    //   517: iconst_0
    //   518: iconst_0
    //   519: aload_0
    //   520: getfield actionId : Ljava/lang/String;
    //   523: aload_0
    //   524: getfield deviceId : Ljava/lang/String;
    //   527: aload_0
    //   528: getfield userName : Ljava/lang/String;
    //   531: aload #17
    //   533: ldc '0'
    //   535: ldc '0'
    //   537: ldc '0'
    //   539: ldc '0'
    //   541: iconst_0
    //   542: aload_0
    //   543: getfield speedLimit : D
    //   546: dconst_0
    //   547: ldc ''
    //   549: aload_0
    //   550: getfield messageId : Ljava/lang/String;
    //   553: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   556: invokevirtual intValue : ()I
    //   559: aload #17
    //   561: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IDDLjava/lang/String;ILjava/lang/String;)V
    //   564: astore #16
    //   566: aload_0
    //   567: aload_0
    //   568: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   571: aload #16
    //   573: invokevirtual addHistorical : (Lcom/roadtrack/onstar/VO/Historical;)J
    //   576: putfield historicoId : J
    //   579: aload #12
    //   581: invokevirtual clear : ()V
    //   584: aload #12
    //   586: ldc_w 'sessionKey'
    //   589: aload_0
    //   590: getfield sessionKey : Ljava/lang/String;
    //   593: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   596: pop
    //   597: aload #12
    //   599: ldc_w 'login'
    //   602: aload_0
    //   603: getfield userName : Ljava/lang/String;
    //   606: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   609: pop
    //   610: aload #12
    //   612: ldc_w 'password'
    //   615: aload_0
    //   616: getfield password : Ljava/lang/String;
    //   619: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   622: pop
    //   623: aload #12
    //   625: ldc_w 'deviceId'
    //   628: aload_0
    //   629: getfield deviceId : Ljava/lang/String;
    //   632: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   635: pop
    //   636: aload #12
    //   638: ldc_w 'actionCode'
    //   641: aload_0
    //   642: getfield actionId : Ljava/lang/String;
    //   645: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   648: pop
    //   649: aload #12
    //   651: ldc_w 'csvParams'
    //   654: aload_0
    //   655: getfield csvParams : Ljava/lang/String;
    //   658: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   661: pop
    //   662: aload #12
    //   664: ldc_w 'userId'
    //   667: aload_0
    //   668: getfield userId : Ljava/lang/String;
    //   671: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   674: pop
    //   675: aload #12
    //   677: ldc_w 'applicationSourceId'
    //   680: aload_0
    //   681: getfield applicationSourceId : Ljava/lang/String;
    //   684: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   687: pop
    //   688: aload #12
    //   690: ldc_w 'deviceCSVParams'
    //   693: aload_0
    //   694: getfield deviceCSVParams : Ljava/lang/String;
    //   697: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   700: pop
    //   701: aload_0
    //   702: getfield TAG : Ljava/lang/String;
    //   705: astore #17
    //   707: new java/lang/StringBuilder
    //   710: dup
    //   711: invokespecial <init> : ()V
    //   714: astore #16
    //   716: aload #16
    //   718: aload_0
    //   719: getfield deviceId : Ljava/lang/String;
    //   722: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   725: pop
    //   726: aload #16
    //   728: ldc_w ': Starting action: '
    //   731: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   734: pop
    //   735: aload #16
    //   737: aload_0
    //   738: getfield actionId : Ljava/lang/String;
    //   741: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   744: pop
    //   745: aload #17
    //   747: ldc_w 'SENDCOMMAND'
    //   750: aload #16
    //   752: invokevirtual toString : ()Ljava/lang/String;
    //   755: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   758: aload_0
    //   759: aload_0
    //   760: getfield actionId : Ljava/lang/String;
    //   763: sipush #30000
    //   766: sipush #3000
    //   769: bipush #30
    //   771: aload_1
    //   772: aload #11
    //   774: ldc_w 'SendCommand'
    //   777: aload #15
    //   779: aload #12
    //   781: invokespecial SendCommand : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;)[Ljava/lang/String;
    //   784: astore #17
    //   786: aload_0
    //   787: aload #17
    //   789: invokespecial savelatLongNewPositionBeforSearchGlim : ([Ljava/lang/String;)V
    //   792: invokestatic getTime : ()Ljava/util/Date;
    //   795: pop
    //   796: aload #17
    //   798: ifnonnull -> 1420
    //   801: aload_0
    //   802: getfield sendCommandErrorCode : I
    //   805: aload_0
    //   806: getfield sendCommandErrorCodeTimeOut : I
    //   809: if_icmpeq -> 1420
    //   812: aload_0
    //   813: iconst_3
    //   814: anewarray java/lang/String
    //   817: dup
    //   818: iconst_0
    //   819: ldc_w '1'
    //   822: aastore
    //   823: dup
    //   824: iconst_1
    //   825: aload_0
    //   826: getfield actionId : Ljava/lang/String;
    //   829: aastore
    //   830: dup
    //   831: iconst_2
    //   832: ldc '0'
    //   834: aastore
    //   835: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   838: aload_0
    //   839: getfield actionId : Ljava/lang/String;
    //   842: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   845: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   848: invokevirtual equals : (Ljava/lang/Object;)Z
    //   851: ifeq -> 1081
    //   854: aload #12
    //   856: invokevirtual clear : ()V
    //   859: aload #12
    //   861: ldc_w 'sessionKey'
    //   864: aload_0
    //   865: getfield sessionKey : Ljava/lang/String;
    //   868: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   871: pop
    //   872: aload #12
    //   874: ldc_w 'login'
    //   877: aload_0
    //   878: getfield userName : Ljava/lang/String;
    //   881: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   884: pop
    //   885: aload #12
    //   887: ldc_w 'password'
    //   890: aload_0
    //   891: getfield password : Ljava/lang/String;
    //   894: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   897: pop
    //   898: aload #12
    //   900: ldc_w 'deviceId'
    //   903: aload_0
    //   904: getfield deviceId : Ljava/lang/String;
    //   907: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   910: pop
    //   911: aload #12
    //   913: ldc_w 'actionCode'
    //   916: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   919: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   922: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   925: pop
    //   926: aload #12
    //   928: ldc_w 'csvParams'
    //   931: ldc ''
    //   933: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   936: pop
    //   937: aload #12
    //   939: ldc_w 'userId'
    //   942: aload_0
    //   943: getfield userId : Ljava/lang/String;
    //   946: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   949: pop
    //   950: aload #12
    //   952: ldc_w 'applicationSourceId'
    //   955: aload_0
    //   956: getfield applicationSourceId : Ljava/lang/String;
    //   959: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   962: pop
    //   963: aload #12
    //   965: ldc_w 'deviceCSVParams'
    //   968: ldc ''
    //   970: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   973: pop
    //   974: new java/lang/StringBuilder
    //   977: dup
    //   978: invokespecial <init> : ()V
    //   981: astore #13
    //   983: aload #13
    //   985: aload #11
    //   987: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   990: pop
    //   991: aload #13
    //   993: ldc_w 'IService1/'
    //   996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   999: pop
    //   1000: aload #13
    //   1002: ldc_w 'GetPIDByDevice'
    //   1005: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1008: pop
    //   1009: aload #13
    //   1011: invokevirtual toString : ()Ljava/lang/String;
    //   1014: astore #13
    //   1016: aload_0
    //   1017: aload_0
    //   1018: getfield actionId : Ljava/lang/String;
    //   1021: sipush #30000
    //   1024: sipush #3000
    //   1027: bipush #10
    //   1029: aload_1
    //   1030: aload #11
    //   1032: ldc_w 'GetPIDByDevice'
    //   1035: aload #13
    //   1037: aload #12
    //   1039: ldc '0'
    //   1041: invokespecial GetCommandStatus : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;Ljava/lang/String;)V
    //   1044: aload_0
    //   1045: iconst_3
    //   1046: anewarray java/lang/String
    //   1049: dup
    //   1050: iconst_0
    //   1051: ldc_w '3'
    //   1054: aastore
    //   1055: dup
    //   1056: iconst_1
    //   1057: aload_0
    //   1058: getfield actionId : Ljava/lang/String;
    //   1061: aastore
    //   1062: dup
    //   1063: iconst_2
    //   1064: aload_0
    //   1065: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1068: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1071: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   1074: aastore
    //   1075: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   1078: goto -> 1324
    //   1081: aload_0
    //   1082: getfield actionId : Ljava/lang/String;
    //   1085: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   1088: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1091: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1094: ifeq -> 1324
    //   1097: aload #12
    //   1099: invokevirtual clear : ()V
    //   1102: aload #12
    //   1104: ldc_w 'sessionKey'
    //   1107: aload_0
    //   1108: getfield sessionKey : Ljava/lang/String;
    //   1111: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1114: pop
    //   1115: aload #12
    //   1117: ldc_w 'login'
    //   1120: aload_0
    //   1121: getfield userName : Ljava/lang/String;
    //   1124: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1127: pop
    //   1128: aload #12
    //   1130: ldc_w 'password'
    //   1133: aload_0
    //   1134: getfield password : Ljava/lang/String;
    //   1137: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1140: pop
    //   1141: aload #12
    //   1143: ldc_w 'deviceId'
    //   1146: aload_0
    //   1147: getfield deviceId : Ljava/lang/String;
    //   1150: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1153: pop
    //   1154: aload #12
    //   1156: ldc_w 'actionCode'
    //   1159: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   1162: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1165: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1168: pop
    //   1169: aload #12
    //   1171: ldc_w 'csvParams'
    //   1174: ldc ''
    //   1176: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1179: pop
    //   1180: aload #12
    //   1182: ldc_w 'userId'
    //   1185: aload_0
    //   1186: getfield userId : Ljava/lang/String;
    //   1189: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1192: pop
    //   1193: aload #12
    //   1195: ldc_w 'applicationSourceId'
    //   1198: aload_0
    //   1199: getfield applicationSourceId : Ljava/lang/String;
    //   1202: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1205: pop
    //   1206: aload #12
    //   1208: ldc_w 'deviceCSVParams'
    //   1211: ldc ''
    //   1213: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1216: pop
    //   1217: new java/lang/StringBuilder
    //   1220: dup
    //   1221: invokespecial <init> : ()V
    //   1224: astore #13
    //   1226: aload #13
    //   1228: aload #11
    //   1230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1233: pop
    //   1234: aload #13
    //   1236: ldc_w 'IService1/'
    //   1239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1242: pop
    //   1243: aload #13
    //   1245: ldc_w 'GetDTCInformationByDeviceId'
    //   1248: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1251: pop
    //   1252: aload #13
    //   1254: invokevirtual toString : ()Ljava/lang/String;
    //   1257: astore #13
    //   1259: aload_0
    //   1260: aload_0
    //   1261: getfield actionId : Ljava/lang/String;
    //   1264: sipush #30000
    //   1267: sipush #3000
    //   1270: bipush #10
    //   1272: aload_1
    //   1273: aload #11
    //   1275: ldc_w 'GetDTCInformationByDeviceId'
    //   1278: aload #13
    //   1280: aload #12
    //   1282: ldc '0'
    //   1284: invokespecial GetCommandStatus : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;Ljava/lang/String;)V
    //   1287: aload_0
    //   1288: iconst_3
    //   1289: anewarray java/lang/String
    //   1292: dup
    //   1293: iconst_0
    //   1294: ldc_w '4'
    //   1297: aastore
    //   1298: dup
    //   1299: iconst_1
    //   1300: aload_0
    //   1301: getfield actionId : Ljava/lang/String;
    //   1304: aastore
    //   1305: dup
    //   1306: iconst_2
    //   1307: aload_0
    //   1308: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1311: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1314: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   1317: aastore
    //   1318: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   1321: goto -> 1324
    //   1324: iload #4
    //   1326: i2l
    //   1327: lstore #9
    //   1329: lload #9
    //   1331: invokestatic sleep : (J)V
    //   1334: goto -> 1383
    //   1337: astore_1
    //   1338: aload_0
    //   1339: getfield TAG : Ljava/lang/String;
    //   1342: astore_1
    //   1343: new java/lang/StringBuilder
    //   1346: dup
    //   1347: invokespecial <init> : ()V
    //   1350: astore #11
    //   1352: aload #11
    //   1354: aload_0
    //   1355: getfield deviceId : Ljava/lang/String;
    //   1358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1361: pop
    //   1362: aload #11
    //   1364: ldc_w ': Waiting'
    //   1367: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1370: pop
    //   1371: aload_1
    //   1372: ldc_w 'Error'
    //   1375: aload #11
    //   1377: invokevirtual toString : ()Ljava/lang/String;
    //   1380: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1383: aload_0
    //   1384: iconst_3
    //   1385: anewarray java/lang/String
    //   1388: dup
    //   1389: iconst_0
    //   1390: ldc_w '99'
    //   1393: aastore
    //   1394: dup
    //   1395: iconst_1
    //   1396: aload_0
    //   1397: getfield actionId : Ljava/lang/String;
    //   1400: aastore
    //   1401: dup
    //   1402: iconst_2
    //   1403: aload_0
    //   1404: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1407: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1410: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   1413: aastore
    //   1414: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   1417: ldc '0'
    //   1419: areturn
    //   1420: aload_0
    //   1421: getfield sendCommandErrorCode : I
    //   1424: aload_0
    //   1425: getfield sendCommandErrorCodeTimeOut : I
    //   1428: if_icmpeq -> 4342
    //   1431: aload_0
    //   1432: getfield actionId : Ljava/lang/String;
    //   1435: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   1438: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1441: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1444: ifeq -> 1457
    //   1447: aload_0
    //   1448: getfield sendCommandErrorCode : I
    //   1451: ifeq -> 1457
    //   1454: goto -> 4342
    //   1457: aload_0
    //   1458: aload #17
    //   1460: iconst_1
    //   1461: aaload
    //   1462: invokevirtual toString : ()Ljava/lang/String;
    //   1465: putfield messageId : Ljava/lang/String;
    //   1468: aload_0
    //   1469: getfield messageId : Ljava/lang/String;
    //   1472: astore #16
    //   1474: aload #16
    //   1476: putstatic com/roadtrack/onstar/BO/GlobalMembers.messsageIdGlobal : Ljava/lang/String;
    //   1479: aload #17
    //   1481: iconst_2
    //   1482: aaload
    //   1483: astore #15
    //   1485: aload #16
    //   1487: ldc ''
    //   1489: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1492: ifeq -> 1502
    //   1495: aload_0
    //   1496: getfield messageId : Ljava/lang/String;
    //   1499: ifnull -> 1532
    //   1502: aload_0
    //   1503: getfield messageId : Ljava/lang/String;
    //   1506: invokestatic isNumeric : (Ljava/lang/String;)Z
    //   1509: ifeq -> 1532
    //   1512: aload_0
    //   1513: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1516: aload_0
    //   1517: getfield messageId : Ljava/lang/String;
    //   1520: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   1523: invokevirtual intValue : ()I
    //   1526: invokevirtual setMessageId : (I)V
    //   1529: goto -> 1532
    //   1532: aload #17
    //   1534: arraylength
    //   1535: iconst_3
    //   1536: if_icmplt -> 2656
    //   1539: aload #15
    //   1541: ldc_w '1'
    //   1544: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1547: ifne -> 2653
    //   1550: aload_0
    //   1551: getfield actionId : Ljava/lang/String;
    //   1554: getstatic com/roadtrack/onstar/Enums$Services.SendPNDNavigationCommand : Lcom/roadtrack/onstar/Enums$Services;
    //   1557: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1560: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1563: ifne -> 2653
    //   1566: aload_0
    //   1567: iconst_3
    //   1568: anewarray java/lang/String
    //   1571: dup
    //   1572: iconst_0
    //   1573: ldc_w '1'
    //   1576: aastore
    //   1577: dup
    //   1578: iconst_1
    //   1579: aload_0
    //   1580: getfield actionId : Ljava/lang/String;
    //   1583: aastore
    //   1584: dup
    //   1585: iconst_2
    //   1586: aload_0
    //   1587: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1590: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1593: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   1596: aastore
    //   1597: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   1600: aload_0
    //   1601: getfield actionId : Ljava/lang/String;
    //   1604: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   1607: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1610: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1613: ifeq -> 2025
    //   1616: aload #12
    //   1618: invokevirtual clear : ()V
    //   1621: aload #12
    //   1623: ldc_w 'sessionKey'
    //   1626: aload_0
    //   1627: getfield sessionKey : Ljava/lang/String;
    //   1630: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1633: pop
    //   1634: aload #12
    //   1636: ldc_w 'login'
    //   1639: aload_0
    //   1640: getfield userName : Ljava/lang/String;
    //   1643: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1646: pop
    //   1647: aload #12
    //   1649: ldc_w 'password'
    //   1652: aload_0
    //   1653: getfield password : Ljava/lang/String;
    //   1656: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1659: pop
    //   1660: aload #12
    //   1662: ldc_w 'deviceId'
    //   1665: aload_0
    //   1666: getfield deviceId : Ljava/lang/String;
    //   1669: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1672: pop
    //   1673: aload #12
    //   1675: ldc_w 'actionCode'
    //   1678: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   1681: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1684: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1687: pop
    //   1688: aload #12
    //   1690: ldc_w 'csvParams'
    //   1693: ldc ''
    //   1695: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1698: pop
    //   1699: aload #12
    //   1701: ldc_w 'userId'
    //   1704: aload_0
    //   1705: getfield userId : Ljava/lang/String;
    //   1708: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1711: pop
    //   1712: aload #12
    //   1714: ldc_w 'applicationSourceId'
    //   1717: aload_0
    //   1718: getfield applicationSourceId : Ljava/lang/String;
    //   1721: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1724: pop
    //   1725: aload #12
    //   1727: ldc_w 'deviceCSVParams'
    //   1730: ldc ''
    //   1732: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1735: pop
    //   1736: new java/lang/StringBuilder
    //   1739: dup
    //   1740: invokespecial <init> : ()V
    //   1743: astore #13
    //   1745: aload #13
    //   1747: aload #11
    //   1749: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1752: pop
    //   1753: aload #13
    //   1755: ldc_w 'IService1/'
    //   1758: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1761: pop
    //   1762: aload #13
    //   1764: ldc_w 'GetPIDByDevice'
    //   1767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1770: pop
    //   1771: aload #13
    //   1773: invokevirtual toString : ()Ljava/lang/String;
    //   1776: astore #13
    //   1778: aload_0
    //   1779: aload_0
    //   1780: getfield actionId : Ljava/lang/String;
    //   1783: sipush #30000
    //   1786: sipush #3000
    //   1789: bipush #10
    //   1791: aload_1
    //   1792: aload #11
    //   1794: ldc_w 'GetPIDByDevice'
    //   1797: aload #13
    //   1799: aload #12
    //   1801: ldc '0'
    //   1803: invokespecial GetCommandStatus : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;Ljava/lang/String;)V
    //   1806: aload_0
    //   1807: getfield deviceId : Ljava/lang/String;
    //   1810: aload_0
    //   1811: getfield context : Landroid/content/Context;
    //   1814: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   1817: astore #11
    //   1819: aload_0
    //   1820: getfield pidHasInfo : Z
    //   1823: ifeq -> 1833
    //   1826: getstatic com/roadtrack/onstar/Enums$actionStatus.Success : Lcom/roadtrack/onstar/Enums$actionStatus;
    //   1829: astore_1
    //   1830: goto -> 1837
    //   1833: getstatic com/roadtrack/onstar/Enums$actionStatus.Failure : Lcom/roadtrack/onstar/Enums$actionStatus;
    //   1836: astore_1
    //   1837: aload_1
    //   1838: invokevirtual GetCode : ()I
    //   1841: istore #5
    //   1843: aload_0
    //   1844: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1847: aload #14
    //   1849: aload_0
    //   1850: getfield historicoId : J
    //   1853: invokestatic toString : (J)Ljava/lang/String;
    //   1856: iload #5
    //   1858: invokestatic valueOf : (I)Ljava/lang/String;
    //   1861: aload_0
    //   1862: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1865: invokevirtual getLatitude : ()F
    //   1868: invokestatic valueOf : (F)Ljava/lang/String;
    //   1871: aload_0
    //   1872: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1875: invokevirtual getLongitude : ()F
    //   1878: invokestatic valueOf : (F)Ljava/lang/String;
    //   1881: aload #11
    //   1883: aload_0
    //   1884: getfield messageId : Ljava/lang/String;
    //   1887: aload_0
    //   1888: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1891: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1894: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   1897: aload_0
    //   1898: getfield sendCommandErrorCode : I
    //   1901: invokestatic toString : (I)Ljava/lang/String;
    //   1904: ldc '0'
    //   1906: ldc '0'
    //   1908: aload_0
    //   1909: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1912: invokevirtual getGpsStatus : ()I
    //   1915: aload_0
    //   1916: getfield actionId : Ljava/lang/String;
    //   1919: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   1922: invokevirtual intValue : ()I
    //   1925: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   1928: aload_0
    //   1929: iconst_3
    //   1930: anewarray java/lang/String
    //   1933: dup
    //   1934: iconst_0
    //   1935: ldc_w '3'
    //   1938: aastore
    //   1939: dup
    //   1940: iconst_1
    //   1941: aload_0
    //   1942: getfield actionId : Ljava/lang/String;
    //   1945: aastore
    //   1946: dup
    //   1947: iconst_2
    //   1948: aload_0
    //   1949: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1952: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1955: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   1958: aastore
    //   1959: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   1962: iload #4
    //   1964: i2l
    //   1965: lstore #9
    //   1967: lload #9
    //   1969: invokestatic sleep : (J)V
    //   1972: goto -> 2022
    //   1975: astore #12
    //   1977: aload_0
    //   1978: getfield TAG : Ljava/lang/String;
    //   1981: astore #11
    //   1983: new java/lang/StringBuilder
    //   1986: dup
    //   1987: invokespecial <init> : ()V
    //   1990: astore_1
    //   1991: aload_1
    //   1992: aload_0
    //   1993: getfield deviceId : Ljava/lang/String;
    //   1996: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1999: pop
    //   2000: aload_1
    //   2001: ldc_w ': Error: Actions.Timer'
    //   2004: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2007: pop
    //   2008: aload #11
    //   2010: aload_1
    //   2011: invokevirtual toString : ()Ljava/lang/String;
    //   2014: aload #12
    //   2016: invokevirtual toString : ()Ljava/lang/String;
    //   2019: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2022: goto -> 2614
    //   2025: aload_0
    //   2026: getfield actionId : Ljava/lang/String;
    //   2029: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   2032: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   2035: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2038: ifeq -> 2328
    //   2041: aload #12
    //   2043: invokevirtual clear : ()V
    //   2046: aload #12
    //   2048: ldc_w 'sessionKey'
    //   2051: aload_0
    //   2052: getfield sessionKey : Ljava/lang/String;
    //   2055: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2058: pop
    //   2059: aload #12
    //   2061: ldc_w 'login'
    //   2064: aload_0
    //   2065: getfield userName : Ljava/lang/String;
    //   2068: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2071: pop
    //   2072: aload #12
    //   2074: ldc_w 'password'
    //   2077: aload_0
    //   2078: getfield password : Ljava/lang/String;
    //   2081: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2084: pop
    //   2085: aload #12
    //   2087: ldc_w 'deviceId'
    //   2090: aload_0
    //   2091: getfield deviceId : Ljava/lang/String;
    //   2094: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2097: pop
    //   2098: aload #12
    //   2100: ldc_w 'actionCode'
    //   2103: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   2106: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   2109: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2112: pop
    //   2113: aload #12
    //   2115: ldc_w 'csvParams'
    //   2118: ldc ''
    //   2120: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2123: pop
    //   2124: aload #12
    //   2126: ldc_w 'userId'
    //   2129: aload_0
    //   2130: getfield userId : Ljava/lang/String;
    //   2133: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2136: pop
    //   2137: aload #12
    //   2139: ldc_w 'applicationSourceId'
    //   2142: aload_0
    //   2143: getfield applicationSourceId : Ljava/lang/String;
    //   2146: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2149: pop
    //   2150: aload #12
    //   2152: ldc_w 'deviceCSVParams'
    //   2155: ldc ''
    //   2157: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2160: pop
    //   2161: new java/lang/StringBuilder
    //   2164: dup
    //   2165: invokespecial <init> : ()V
    //   2168: astore #13
    //   2170: aload #13
    //   2172: aload #11
    //   2174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2177: pop
    //   2178: aload #13
    //   2180: ldc_w 'IService1/'
    //   2183: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2186: pop
    //   2187: aload #13
    //   2189: ldc_w 'GetDTCInformationByDeviceId'
    //   2192: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2195: pop
    //   2196: aload #13
    //   2198: invokevirtual toString : ()Ljava/lang/String;
    //   2201: astore #13
    //   2203: aload_0
    //   2204: aload_0
    //   2205: getfield actionId : Ljava/lang/String;
    //   2208: sipush #30000
    //   2211: sipush #3000
    //   2214: bipush #10
    //   2216: aload_1
    //   2217: aload #11
    //   2219: ldc_w 'GetDTCInformationByDeviceId'
    //   2222: aload #13
    //   2224: aload #12
    //   2226: ldc '0'
    //   2228: invokespecial GetCommandStatus : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;Ljava/lang/String;)V
    //   2231: aload_0
    //   2232: iconst_3
    //   2233: anewarray java/lang/String
    //   2236: dup
    //   2237: iconst_0
    //   2238: ldc_w '4'
    //   2241: aastore
    //   2242: dup
    //   2243: iconst_1
    //   2244: aload_0
    //   2245: getfield actionId : Ljava/lang/String;
    //   2248: aastore
    //   2249: dup
    //   2250: iconst_2
    //   2251: aload_0
    //   2252: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2255: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2258: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   2261: aastore
    //   2262: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   2265: iload #4
    //   2267: i2l
    //   2268: lstore #9
    //   2270: lload #9
    //   2272: invokestatic sleep : (J)V
    //   2275: goto -> 2614
    //   2278: astore #12
    //   2280: aload_0
    //   2281: getfield TAG : Ljava/lang/String;
    //   2284: astore #11
    //   2286: new java/lang/StringBuilder
    //   2289: dup
    //   2290: invokespecial <init> : ()V
    //   2293: astore_1
    //   2294: aload_1
    //   2295: aload_0
    //   2296: getfield deviceId : Ljava/lang/String;
    //   2299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2302: pop
    //   2303: aload_1
    //   2304: ldc_w ': Error: Actions.Timer'
    //   2307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2310: pop
    //   2311: aload #11
    //   2313: aload_1
    //   2314: invokevirtual toString : ()Ljava/lang/String;
    //   2317: aload #12
    //   2319: invokevirtual toString : ()Ljava/lang/String;
    //   2322: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2325: goto -> 2614
    //   2328: aload_0
    //   2329: getfield actionId : Ljava/lang/String;
    //   2332: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   2335: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   2338: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2341: ifeq -> 2435
    //   2344: new java/lang/StringBuilder
    //   2347: dup
    //   2348: invokespecial <init> : ()V
    //   2351: astore_1
    //   2352: aload_1
    //   2353: aload_0
    //   2354: getfield deviceId : Ljava/lang/String;
    //   2357: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2360: pop
    //   2361: aload_1
    //   2362: ldc_w '|0|0|0|0|0|3|'
    //   2365: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2368: pop
    //   2369: aload_1
    //   2370: invokevirtual toString : ()Ljava/lang/String;
    //   2373: astore_1
    //   2374: aload_0
    //   2375: aload_0
    //   2376: getfield context : Landroid/content/Context;
    //   2379: aload_0
    //   2380: getfield actionId : Ljava/lang/String;
    //   2383: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   2386: invokevirtual intValue : ()I
    //   2389: aload_1
    //   2390: ldc ''
    //   2392: aload_0
    //   2393: getfield deviceId : Ljava/lang/String;
    //   2396: ldc '0'
    //   2398: invokespecial adjustWCFResult : (Landroid/content/Context;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2401: astore_1
    //   2402: aload_1
    //   2403: invokevirtual getLongitude : ()F
    //   2406: f2d
    //   2407: dconst_0
    //   2408: dcmpl
    //   2409: ifeq -> 2548
    //   2412: aload_1
    //   2413: invokevirtual getLatitude : ()F
    //   2416: f2d
    //   2417: dconst_0
    //   2418: dcmpl
    //   2419: ifeq -> 2548
    //   2422: aload_0
    //   2423: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2426: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2429: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   2432: goto -> 2548
    //   2435: aload_0
    //   2436: getfield deviceId : Ljava/lang/String;
    //   2439: aload_0
    //   2440: getfield context : Landroid/content/Context;
    //   2443: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   2446: astore_1
    //   2447: aload_0
    //   2448: getfield actionId : Ljava/lang/String;
    //   2451: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   2454: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   2457: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2460: ifne -> 2548
    //   2463: aload_0
    //   2464: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   2467: aload #14
    //   2469: aload_0
    //   2470: getfield historicoId : J
    //   2473: invokestatic toString : (J)Ljava/lang/String;
    //   2476: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Fail : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2479: invokevirtual toString : ()Ljava/lang/String;
    //   2482: aload_0
    //   2483: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2486: invokevirtual getLatitude : ()F
    //   2489: invokestatic valueOf : (F)Ljava/lang/String;
    //   2492: aload_0
    //   2493: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2496: invokevirtual getLongitude : ()F
    //   2499: invokestatic valueOf : (F)Ljava/lang/String;
    //   2502: aload_1
    //   2503: aload_0
    //   2504: getfield messageId : Ljava/lang/String;
    //   2507: aload_0
    //   2508: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2511: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2514: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   2517: aload_0
    //   2518: getfield sendCommandErrorCode : I
    //   2521: invokestatic toString : (I)Ljava/lang/String;
    //   2524: ldc '0'
    //   2526: ldc '0'
    //   2528: aload_0
    //   2529: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2532: invokevirtual getGpsStatus : ()I
    //   2535: aload_0
    //   2536: getfield actionId : Ljava/lang/String;
    //   2539: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   2542: invokevirtual intValue : ()I
    //   2545: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   2548: iload #5
    //   2550: sipush #1000
    //   2553: imul
    //   2554: i2l
    //   2555: lstore #9
    //   2557: lload #9
    //   2559: invokestatic sleep : (J)V
    //   2562: goto -> 2614
    //   2565: astore_1
    //   2566: aload_0
    //   2567: getfield TAG : Ljava/lang/String;
    //   2570: astore #11
    //   2572: new java/lang/StringBuilder
    //   2575: dup
    //   2576: invokespecial <init> : ()V
    //   2579: astore #12
    //   2581: aload #12
    //   2583: aload_0
    //   2584: getfield deviceId : Ljava/lang/String;
    //   2587: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2590: pop
    //   2591: aload #12
    //   2593: ldc_w ': Error: Actions.Timer'
    //   2596: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2599: pop
    //   2600: aload #11
    //   2602: aload #12
    //   2604: invokevirtual toString : ()Ljava/lang/String;
    //   2607: aload_1
    //   2608: invokevirtual toString : ()Ljava/lang/String;
    //   2611: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2614: aload_0
    //   2615: iconst_3
    //   2616: anewarray java/lang/String
    //   2619: dup
    //   2620: iconst_0
    //   2621: ldc_w '99'
    //   2624: aastore
    //   2625: dup
    //   2626: iconst_1
    //   2627: aload_0
    //   2628: getfield actionId : Ljava/lang/String;
    //   2631: aastore
    //   2632: dup
    //   2633: iconst_2
    //   2634: aload_0
    //   2635: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2638: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2641: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   2644: aastore
    //   2645: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   2648: aload_0
    //   2649: getfield messageId : Ljava/lang/String;
    //   2652: areturn
    //   2653: goto -> 2656
    //   2656: aload #17
    //   2658: arraylength
    //   2659: iconst_3
    //   2660: if_icmplt -> 2831
    //   2663: aload #15
    //   2665: ldc_w '1'
    //   2668: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2671: ifne -> 2828
    //   2674: aload_0
    //   2675: getfield actionId : Ljava/lang/String;
    //   2678: getstatic com/roadtrack/onstar/Enums$Services.SendPNDNavigationCommand : Lcom/roadtrack/onstar/Enums$Services;
    //   2681: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   2684: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2687: ifeq -> 2831
    //   2690: aload_0
    //   2691: iconst_3
    //   2692: anewarray java/lang/String
    //   2695: dup
    //   2696: iconst_0
    //   2697: ldc_w '3'
    //   2700: aastore
    //   2701: dup
    //   2702: iconst_1
    //   2703: aload_0
    //   2704: getfield actionId : Ljava/lang/String;
    //   2707: aastore
    //   2708: dup
    //   2709: iconst_2
    //   2710: aload_0
    //   2711: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2714: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2717: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   2720: aastore
    //   2721: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   2724: iload #4
    //   2726: i2l
    //   2727: lstore #9
    //   2729: lload #9
    //   2731: invokestatic sleep : (J)V
    //   2734: goto -> 2789
    //   2737: astore_1
    //   2738: aload_0
    //   2739: getfield TAG : Ljava/lang/String;
    //   2742: astore #11
    //   2744: new java/lang/StringBuilder
    //   2747: dup
    //   2748: invokespecial <init> : ()V
    //   2751: astore #12
    //   2753: aload #12
    //   2755: aload_0
    //   2756: getfield deviceId : Ljava/lang/String;
    //   2759: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2762: pop
    //   2763: aload #12
    //   2765: ldc_w ': Error: Actions.Timer'
    //   2768: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2771: pop
    //   2772: aload #11
    //   2774: aload #12
    //   2776: invokevirtual toString : ()Ljava/lang/String;
    //   2779: aload_1
    //   2780: invokevirtual toString : ()Ljava/lang/String;
    //   2783: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2786: goto -> 2734
    //   2789: aload_0
    //   2790: iconst_3
    //   2791: anewarray java/lang/String
    //   2794: dup
    //   2795: iconst_0
    //   2796: ldc_w '99'
    //   2799: aastore
    //   2800: dup
    //   2801: iconst_1
    //   2802: aload_0
    //   2803: getfield actionId : Ljava/lang/String;
    //   2806: aastore
    //   2807: dup
    //   2808: iconst_2
    //   2809: aload_0
    //   2810: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   2813: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   2816: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   2819: aastore
    //   2820: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   2823: aload_0
    //   2824: getfield messageId : Ljava/lang/String;
    //   2827: areturn
    //   2828: goto -> 2831
    //   2831: aload_0
    //   2832: getfield deviceId : Ljava/lang/String;
    //   2835: aload_0
    //   2836: getfield context : Landroid/content/Context;
    //   2839: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   2842: astore #16
    //   2844: aload_0
    //   2845: getfield actionId : Ljava/lang/String;
    //   2848: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   2851: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   2854: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2857: ifne -> 2915
    //   2860: aload_0
    //   2861: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   2864: aload #14
    //   2866: aload_0
    //   2867: getfield historicoId : J
    //   2870: invokestatic toString : (J)Ljava/lang/String;
    //   2873: ldc '0'
    //   2875: ldc '0'
    //   2877: ldc '0'
    //   2879: aload #16
    //   2881: aload_0
    //   2882: getfield messageId : Ljava/lang/String;
    //   2885: ldc '0'
    //   2887: aload_0
    //   2888: getfield sendCommandErrorCode : I
    //   2891: invokestatic toString : (I)Ljava/lang/String;
    //   2894: ldc '0'
    //   2896: ldc '0'
    //   2898: iconst_0
    //   2899: aload_0
    //   2900: getfield actionId : Ljava/lang/String;
    //   2903: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   2906: invokevirtual intValue : ()I
    //   2909: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   2912: goto -> 2915
    //   2915: aload_0
    //   2916: iconst_3
    //   2917: anewarray java/lang/String
    //   2920: dup
    //   2921: iconst_0
    //   2922: ldc_w '1'
    //   2925: aastore
    //   2926: dup
    //   2927: iconst_1
    //   2928: aload_0
    //   2929: getfield actionId : Ljava/lang/String;
    //   2932: aastore
    //   2933: dup
    //   2934: iconst_2
    //   2935: aload #15
    //   2937: aastore
    //   2938: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   2941: aload_0
    //   2942: getfield actionId : Ljava/lang/String;
    //   2945: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   2948: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   2951: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2954: ifeq -> 3176
    //   2957: aload #12
    //   2959: invokevirtual clear : ()V
    //   2962: aload #12
    //   2964: ldc_w 'accountId'
    //   2967: aload_0
    //   2968: getfield accountId : Ljava/lang/String;
    //   2971: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2974: pop
    //   2975: aload #12
    //   2977: ldc_w 'deviceId'
    //   2980: aload_0
    //   2981: getfield deviceId : Ljava/lang/String;
    //   2984: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   2987: pop
    //   2988: aload #12
    //   2990: ldc_w 'actionId'
    //   2993: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   2996: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   2999: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3002: pop
    //   3003: aload #12
    //   3005: ldc_w 'messageId'
    //   3008: aload_0
    //   3009: getfield messageId : Ljava/lang/String;
    //   3012: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3015: pop
    //   3016: new java/lang/StringBuilder
    //   3019: dup
    //   3020: invokespecial <init> : ()V
    //   3023: astore #15
    //   3025: aload #15
    //   3027: aload #11
    //   3029: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3032: pop
    //   3033: aload #15
    //   3035: ldc_w 'IService1/'
    //   3038: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3041: pop
    //   3042: aload #15
    //   3044: ldc_w 'GetLastIncomingMessage'
    //   3047: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3050: pop
    //   3051: aload #15
    //   3053: invokevirtual toString : ()Ljava/lang/String;
    //   3056: astore #15
    //   3058: aload_0
    //   3059: sipush #30000
    //   3062: aload_0
    //   3063: getfield accountId : Ljava/lang/String;
    //   3066: aload #13
    //   3068: iload #7
    //   3070: aload_0
    //   3071: getfield messageId : Ljava/lang/String;
    //   3074: aload_0
    //   3075: getfield deviceId : Ljava/lang/String;
    //   3078: invokespecial SocketACK : (ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
    //   3081: aload_0
    //   3082: getfield TAG : Ljava/lang/String;
    //   3085: astore #16
    //   3087: new java/lang/StringBuilder
    //   3090: dup
    //   3091: invokespecial <init> : ()V
    //   3094: astore #13
    //   3096: aload #13
    //   3098: aload_0
    //   3099: getfield deviceId : Ljava/lang/String;
    //   3102: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3105: pop
    //   3106: aload #13
    //   3108: ldc_w ': Starting'
    //   3111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3114: pop
    //   3115: aload #16
    //   3117: ldc_w 'GETCOMMAND'
    //   3120: aload #13
    //   3122: invokevirtual toString : ()Ljava/lang/String;
    //   3125: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   3128: aload_0
    //   3129: aload_0
    //   3130: getfield actionId : Ljava/lang/String;
    //   3133: sipush #30000
    //   3136: sipush #3000
    //   3139: iload #5
    //   3141: aload_1
    //   3142: aload #11
    //   3144: ldc_w 'GetLastIncomingMessage'
    //   3147: aload #15
    //   3149: aload #12
    //   3151: ldc ''
    //   3153: invokespecial GetCommandStatus : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;Ljava/lang/String;)V
    //   3156: aload_0
    //   3157: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3160: aload_0
    //   3161: getfield messageId : Ljava/lang/String;
    //   3164: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   3167: invokevirtual intValue : ()I
    //   3170: invokevirtual setMessageId : (I)V
    //   3173: goto -> 3865
    //   3176: aload_0
    //   3177: getfield TAG : Ljava/lang/String;
    //   3180: astore #15
    //   3182: new java/lang/StringBuilder
    //   3185: dup
    //   3186: invokespecial <init> : ()V
    //   3189: astore #16
    //   3191: aload #16
    //   3193: aload_0
    //   3194: getfield deviceId : Ljava/lang/String;
    //   3197: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3200: pop
    //   3201: aload #16
    //   3203: ldc_w ': Starting action: '
    //   3206: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3209: pop
    //   3210: aload #16
    //   3212: aload_0
    //   3213: getfield actionId : Ljava/lang/String;
    //   3216: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3219: pop
    //   3220: aload #15
    //   3222: ldc_w 'SOCKET'
    //   3225: aload #16
    //   3227: invokevirtual toString : ()Ljava/lang/String;
    //   3230: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   3233: aload_0
    //   3234: aload_0
    //   3235: getfield actionId : Ljava/lang/String;
    //   3238: sipush #30000
    //   3241: iload #6
    //   3243: aload_0
    //   3244: getfield accountId : Ljava/lang/String;
    //   3247: aload #13
    //   3249: iload #7
    //   3251: aload_0
    //   3252: getfield messageId : Ljava/lang/String;
    //   3255: aload_0
    //   3256: getfield deviceId : Ljava/lang/String;
    //   3259: iconst_0
    //   3260: invokespecial StartSocket : (Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Z)V
    //   3263: new java/util/Date
    //   3266: dup
    //   3267: invokespecial <init> : ()V
    //   3270: astore #13
    //   3272: lconst_0
    //   3273: lstore #9
    //   3275: lload #9
    //   3277: iload #6
    //   3279: i2l
    //   3280: lcmp
    //   3281: ifge -> 3366
    //   3284: aload_0
    //   3285: getfield processFinished : Z
    //   3288: ifeq -> 3294
    //   3291: goto -> 3366
    //   3294: ldc2_w 1000
    //   3297: invokestatic sleep : (J)V
    //   3300: goto -> 3352
    //   3303: astore #15
    //   3305: aload_0
    //   3306: getfield TAG : Ljava/lang/String;
    //   3309: astore #15
    //   3311: new java/lang/StringBuilder
    //   3314: dup
    //   3315: invokespecial <init> : ()V
    //   3318: astore #16
    //   3320: aload #16
    //   3322: aload_0
    //   3323: getfield deviceId : Ljava/lang/String;
    //   3326: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3329: pop
    //   3330: aload #16
    //   3332: ldc_w ': Error'
    //   3335: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3338: pop
    //   3339: aload #15
    //   3341: aload #16
    //   3343: invokevirtual toString : ()Ljava/lang/String;
    //   3346: ldc_w 'Waiting'
    //   3349: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   3352: aload_0
    //   3353: aconst_null
    //   3354: aload #13
    //   3356: bipush #13
    //   3358: invokespecial getTimeDifference : (Ljava/util/Date;Ljava/util/Date;I)J
    //   3361: lstore #9
    //   3363: goto -> 3275
    //   3366: aload_0
    //   3367: getfield actionId : Ljava/lang/String;
    //   3370: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   3373: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   3376: invokevirtual equals : (Ljava/lang/Object;)Z
    //   3379: ifeq -> 3625
    //   3382: aload_0
    //   3383: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3386: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   3389: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   3392: if_acmpeq -> 3625
    //   3395: aload_0
    //   3396: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3399: invokevirtual getModelo : ()Ljava/lang/String;
    //   3402: ifnull -> 3431
    //   3405: aload_0
    //   3406: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3409: invokevirtual getModelo : ()Ljava/lang/String;
    //   3412: invokevirtual length : ()I
    //   3415: ifeq -> 3431
    //   3418: aload_0
    //   3419: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3422: invokevirtual getEventDateTime : ()Ljava/lang/String;
    //   3425: invokevirtual length : ()I
    //   3428: ifne -> 3625
    //   3431: aload #12
    //   3433: invokevirtual clear : ()V
    //   3436: aload #12
    //   3438: ldc_w 'sessionKey'
    //   3441: aload_0
    //   3442: getfield sessionKey : Ljava/lang/String;
    //   3445: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3448: pop
    //   3449: aload #12
    //   3451: ldc_w 'login'
    //   3454: aload_0
    //   3455: getfield userName : Ljava/lang/String;
    //   3458: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3461: pop
    //   3462: aload #12
    //   3464: ldc_w 'password'
    //   3467: aload_0
    //   3468: getfield password : Ljava/lang/String;
    //   3471: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3474: pop
    //   3475: aload #12
    //   3477: ldc_w 'deviceId'
    //   3480: aload_0
    //   3481: getfield deviceId : Ljava/lang/String;
    //   3484: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3487: pop
    //   3488: aload #12
    //   3490: ldc_w 'actionCode'
    //   3493: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   3496: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   3499: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3502: pop
    //   3503: aload #12
    //   3505: ldc_w 'csvParams'
    //   3508: ldc ''
    //   3510: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3513: pop
    //   3514: aload #12
    //   3516: ldc_w 'userId'
    //   3519: aload_0
    //   3520: getfield userId : Ljava/lang/String;
    //   3523: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3526: pop
    //   3527: aload #12
    //   3529: ldc_w 'applicationSourceId'
    //   3532: aload_0
    //   3533: getfield applicationSourceId : Ljava/lang/String;
    //   3536: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3539: pop
    //   3540: aload #12
    //   3542: ldc_w 'deviceCSVParams'
    //   3545: ldc ''
    //   3547: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3550: pop
    //   3551: new java/lang/StringBuilder
    //   3554: dup
    //   3555: invokespecial <init> : ()V
    //   3558: astore #13
    //   3560: aload #13
    //   3562: aload #11
    //   3564: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3567: pop
    //   3568: aload #13
    //   3570: ldc_w 'IService1/'
    //   3573: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3576: pop
    //   3577: aload #13
    //   3579: ldc_w 'GetPIDByDevice'
    //   3582: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3585: pop
    //   3586: aload #13
    //   3588: invokevirtual toString : ()Ljava/lang/String;
    //   3591: astore #13
    //   3593: aload_0
    //   3594: aload_0
    //   3595: getfield actionId : Ljava/lang/String;
    //   3598: sipush #30000
    //   3601: sipush #3000
    //   3604: bipush #10
    //   3606: aload_1
    //   3607: aload #11
    //   3609: ldc_w 'GetPIDByDevice'
    //   3612: aload #13
    //   3614: aload #12
    //   3616: ldc_w '3'
    //   3619: invokespecial GetCommandStatus : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;Ljava/lang/String;)V
    //   3622: goto -> 3625
    //   3625: aload_0
    //   3626: getfield actionId : Ljava/lang/String;
    //   3629: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   3632: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   3635: invokevirtual equals : (Ljava/lang/Object;)Z
    //   3638: ifeq -> 3865
    //   3641: aload #12
    //   3643: invokevirtual clear : ()V
    //   3646: aload #12
    //   3648: ldc_w 'sessionKey'
    //   3651: aload_0
    //   3652: getfield sessionKey : Ljava/lang/String;
    //   3655: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3658: pop
    //   3659: aload #12
    //   3661: ldc_w 'login'
    //   3664: aload_0
    //   3665: getfield userName : Ljava/lang/String;
    //   3668: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3671: pop
    //   3672: aload #12
    //   3674: ldc_w 'password'
    //   3677: aload_0
    //   3678: getfield password : Ljava/lang/String;
    //   3681: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3684: pop
    //   3685: aload #12
    //   3687: ldc_w 'deviceId'
    //   3690: aload_0
    //   3691: getfield deviceId : Ljava/lang/String;
    //   3694: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3697: pop
    //   3698: aload #12
    //   3700: ldc_w 'actionCode'
    //   3703: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   3706: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   3709: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3712: pop
    //   3713: aload #12
    //   3715: ldc_w 'csvParams'
    //   3718: ldc ''
    //   3720: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3723: pop
    //   3724: aload #12
    //   3726: ldc_w 'userId'
    //   3729: aload_0
    //   3730: getfield userId : Ljava/lang/String;
    //   3733: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3736: pop
    //   3737: aload #12
    //   3739: ldc_w 'applicationSourceId'
    //   3742: aload_0
    //   3743: getfield applicationSourceId : Ljava/lang/String;
    //   3746: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3749: pop
    //   3750: aload #12
    //   3752: ldc_w 'deviceCSVParams'
    //   3755: ldc ''
    //   3757: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   3760: pop
    //   3761: new java/lang/StringBuilder
    //   3764: dup
    //   3765: invokespecial <init> : ()V
    //   3768: astore #13
    //   3770: aload #13
    //   3772: aload #11
    //   3774: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3777: pop
    //   3778: aload #13
    //   3780: ldc_w 'IService1/'
    //   3783: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3786: pop
    //   3787: aload #13
    //   3789: ldc_w 'GetDTCInformationByDeviceId'
    //   3792: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3795: pop
    //   3796: aload #13
    //   3798: invokevirtual toString : ()Ljava/lang/String;
    //   3801: astore #13
    //   3803: aload_0
    //   3804: aload_0
    //   3805: getfield actionId : Ljava/lang/String;
    //   3808: sipush #30000
    //   3811: sipush #3000
    //   3814: bipush #10
    //   3816: aload_1
    //   3817: aload #11
    //   3819: ldc_w 'GetDTCInformationByDeviceId'
    //   3822: aload #13
    //   3824: aload #12
    //   3826: ldc '0'
    //   3828: invokespecial GetCommandStatus : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;Ljava/lang/String;)V
    //   3831: aload_0
    //   3832: iconst_3
    //   3833: anewarray java/lang/String
    //   3836: dup
    //   3837: iconst_0
    //   3838: ldc_w '4'
    //   3841: aastore
    //   3842: dup
    //   3843: iconst_1
    //   3844: aload_0
    //   3845: getfield actionId : Ljava/lang/String;
    //   3848: aastore
    //   3849: dup
    //   3850: iconst_2
    //   3851: aload_0
    //   3852: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3855: invokevirtual getIdResponseOriginal : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   3858: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   3861: aastore
    //   3862: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   3865: aload_0
    //   3866: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3869: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   3872: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   3875: astore_1
    //   3876: aload_1
    //   3877: astore #11
    //   3879: aload_0
    //   3880: getfield actionId : Ljava/lang/String;
    //   3883: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   3886: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   3889: invokevirtual equals : (Ljava/lang/Object;)Z
    //   3892: ifeq -> 3971
    //   3895: aload_1
    //   3896: astore #11
    //   3898: aload_0
    //   3899: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3902: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   3905: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Fail : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   3908: if_acmpne -> 3971
    //   3911: aload_1
    //   3912: astore #11
    //   3914: aload_0
    //   3915: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3918: invokevirtual getModelo : ()Ljava/lang/String;
    //   3921: ifnull -> 3971
    //   3924: aload_1
    //   3925: astore #11
    //   3927: aload_0
    //   3928: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3931: invokevirtual getModelo : ()Ljava/lang/String;
    //   3934: invokevirtual length : ()I
    //   3937: ifeq -> 3971
    //   3940: aload_1
    //   3941: astore #11
    //   3943: aload_0
    //   3944: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3947: invokevirtual getEventDateTime : ()Ljava/lang/String;
    //   3950: invokevirtual length : ()I
    //   3953: ifeq -> 3971
    //   3956: aload_0
    //   3957: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3960: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   3963: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   3966: ldc_w '15'
    //   3969: astore #11
    //   3971: aload_0
    //   3972: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   3975: aload_0
    //   3976: getfield messageId : Ljava/lang/String;
    //   3979: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   3982: invokevirtual intValue : ()I
    //   3985: invokevirtual setMessageId : (I)V
    //   3988: aload_0
    //   3989: aload_0
    //   3990: getfield actionId : Ljava/lang/String;
    //   3993: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   3996: invokevirtual intValue : ()I
    //   3999: aload_0
    //   4000: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   4003: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   4006: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   4009: invokespecial GetEndMessage : (ILjava/lang/String;)[Ljava/lang/String;
    //   4012: astore_1
    //   4013: aload_1
    //   4014: iconst_0
    //   4015: aaload
    //   4016: astore #15
    //   4018: aload_1
    //   4019: iconst_1
    //   4020: aaload
    //   4021: astore #16
    //   4023: aload_1
    //   4024: iconst_2
    //   4025: aaload
    //   4026: astore #17
    //   4028: aload_0
    //   4029: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   4032: invokevirtual getLatitude : ()F
    //   4035: fstore_2
    //   4036: aload_0
    //   4037: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   4040: invokevirtual getLongitude : ()F
    //   4043: fstore_3
    //   4044: aload_0
    //   4045: getfield processFinished : Z
    //   4048: ifne -> 4076
    //   4051: aload_0
    //   4052: getfield sendCommandErrorCode : I
    //   4055: ifne -> 4065
    //   4058: ldc_w '408'
    //   4061: astore_1
    //   4062: goto -> 4095
    //   4065: ldc '0'
    //   4067: astore #12
    //   4069: ldc_w '2'
    //   4072: astore_1
    //   4073: goto -> 4106
    //   4076: aload_0
    //   4077: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   4080: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   4083: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Fail : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   4086: if_acmpne -> 4092
    //   4089: goto -> 4065
    //   4092: ldc '0'
    //   4094: astore_1
    //   4095: ldc_w '1'
    //   4098: astore #13
    //   4100: aload_1
    //   4101: astore #12
    //   4103: aload #13
    //   4105: astore_1
    //   4106: aload_0
    //   4107: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   4110: invokevirtual getGpsStatus : ()I
    //   4113: istore #5
    //   4115: aload_0
    //   4116: getfield deviceId : Ljava/lang/String;
    //   4119: aload_0
    //   4120: getfield context : Landroid/content/Context;
    //   4123: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   4126: astore #13
    //   4128: aload_0
    //   4129: getfield actionId : Ljava/lang/String;
    //   4132: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   4135: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   4138: invokevirtual equals : (Ljava/lang/Object;)Z
    //   4141: ifne -> 4200
    //   4144: aload_0
    //   4145: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   4148: aload #14
    //   4150: aload_0
    //   4151: getfield historicoId : J
    //   4154: invokestatic toString : (J)Ljava/lang/String;
    //   4157: aload_1
    //   4158: fload_2
    //   4159: invokestatic valueOf : (F)Ljava/lang/String;
    //   4162: fload_3
    //   4163: invokestatic valueOf : (F)Ljava/lang/String;
    //   4166: aload #13
    //   4168: aload_0
    //   4169: getfield messageId : Ljava/lang/String;
    //   4172: aload #11
    //   4174: aload_0
    //   4175: getfield sendCommandErrorCode : I
    //   4178: invokestatic toString : (I)Ljava/lang/String;
    //   4181: aload #12
    //   4183: aload #17
    //   4185: iload #5
    //   4187: aload_0
    //   4188: getfield actionId : Ljava/lang/String;
    //   4191: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   4194: invokevirtual intValue : ()I
    //   4197: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   4200: aload_0
    //   4201: bipush #6
    //   4203: anewarray java/lang/String
    //   4206: dup
    //   4207: iconst_0
    //   4208: ldc_w '2'
    //   4211: aastore
    //   4212: dup
    //   4213: iconst_1
    //   4214: aload_0
    //   4215: getfield actionId : Ljava/lang/String;
    //   4218: aastore
    //   4219: dup
    //   4220: iconst_2
    //   4221: aload #11
    //   4223: aastore
    //   4224: dup
    //   4225: iconst_3
    //   4226: aload #15
    //   4228: aastore
    //   4229: dup
    //   4230: iconst_4
    //   4231: aload #16
    //   4233: aastore
    //   4234: dup
    //   4235: iconst_5
    //   4236: aload #17
    //   4238: aastore
    //   4239: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   4242: iload #4
    //   4244: i2l
    //   4245: lstore #9
    //   4247: lload #9
    //   4249: invokestatic sleep : (J)V
    //   4252: goto -> 4299
    //   4255: astore_1
    //   4256: aload_0
    //   4257: getfield TAG : Ljava/lang/String;
    //   4260: astore #12
    //   4262: new java/lang/StringBuilder
    //   4265: dup
    //   4266: invokespecial <init> : ()V
    //   4269: astore_1
    //   4270: aload_1
    //   4271: aload_0
    //   4272: getfield deviceId : Ljava/lang/String;
    //   4275: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4278: pop
    //   4279: aload_1
    //   4280: ldc_w ': Waiting'
    //   4283: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4286: pop
    //   4287: aload #12
    //   4289: ldc_w 'Error'
    //   4292: aload_1
    //   4293: invokevirtual toString : ()Ljava/lang/String;
    //   4296: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   4299: aload_0
    //   4300: getfield context : Landroid/content/Context;
    //   4303: invokestatic GCMHeartBeat : (Landroid/content/Context;)V
    //   4306: aload_0
    //   4307: invokespecial AdditionalTasks : ()Z
    //   4310: pop
    //   4311: aload_0
    //   4312: iconst_3
    //   4313: anewarray java/lang/String
    //   4316: dup
    //   4317: iconst_0
    //   4318: ldc_w '99'
    //   4321: aastore
    //   4322: dup
    //   4323: iconst_1
    //   4324: aload_0
    //   4325: getfield actionId : Ljava/lang/String;
    //   4328: aastore
    //   4329: dup
    //   4330: iconst_2
    //   4331: aload #11
    //   4333: aastore
    //   4334: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   4337: aload_0
    //   4338: getfield messageId : Ljava/lang/String;
    //   4341: areturn
    //   4342: aload_0
    //   4343: iconst_3
    //   4344: anewarray java/lang/String
    //   4347: dup
    //   4348: iconst_0
    //   4349: ldc_w '1'
    //   4352: aastore
    //   4353: dup
    //   4354: iconst_1
    //   4355: aload_0
    //   4356: getfield actionId : Ljava/lang/String;
    //   4359: aastore
    //   4360: dup
    //   4361: iconst_2
    //   4362: ldc '0'
    //   4364: aastore
    //   4365: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   4368: aload_0
    //   4369: getfield actionId : Ljava/lang/String;
    //   4372: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   4375: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   4378: invokevirtual equals : (Ljava/lang/Object;)Z
    //   4381: ifeq -> 4577
    //   4384: aload #12
    //   4386: invokevirtual clear : ()V
    //   4389: aload #12
    //   4391: ldc_w 'sessionKey'
    //   4394: aload_0
    //   4395: getfield sessionKey : Ljava/lang/String;
    //   4398: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4401: pop
    //   4402: aload #12
    //   4404: ldc_w 'login'
    //   4407: aload_0
    //   4408: getfield userName : Ljava/lang/String;
    //   4411: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4414: pop
    //   4415: aload #12
    //   4417: ldc_w 'password'
    //   4420: aload_0
    //   4421: getfield password : Ljava/lang/String;
    //   4424: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4427: pop
    //   4428: aload #12
    //   4430: ldc_w 'deviceId'
    //   4433: aload_0
    //   4434: getfield deviceId : Ljava/lang/String;
    //   4437: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4440: pop
    //   4441: aload #12
    //   4443: ldc_w 'actionCode'
    //   4446: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   4449: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   4452: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4455: pop
    //   4456: aload #12
    //   4458: ldc_w 'csvParams'
    //   4461: ldc ''
    //   4463: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4466: pop
    //   4467: aload #12
    //   4469: ldc_w 'userId'
    //   4472: aload_0
    //   4473: getfield userId : Ljava/lang/String;
    //   4476: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4479: pop
    //   4480: aload #12
    //   4482: ldc_w 'applicationSourceId'
    //   4485: aload_0
    //   4486: getfield applicationSourceId : Ljava/lang/String;
    //   4489: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4492: pop
    //   4493: aload #12
    //   4495: ldc_w 'deviceCSVParams'
    //   4498: ldc ''
    //   4500: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4503: pop
    //   4504: new java/lang/StringBuilder
    //   4507: dup
    //   4508: invokespecial <init> : ()V
    //   4511: astore #13
    //   4513: aload #13
    //   4515: aload #11
    //   4517: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4520: pop
    //   4521: aload #13
    //   4523: ldc_w 'IService1/'
    //   4526: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4529: pop
    //   4530: aload #13
    //   4532: ldc_w 'GetPIDByDevice'
    //   4535: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4538: pop
    //   4539: aload #13
    //   4541: invokevirtual toString : ()Ljava/lang/String;
    //   4544: astore #13
    //   4546: aload_0
    //   4547: aload_0
    //   4548: getfield actionId : Ljava/lang/String;
    //   4551: sipush #30000
    //   4554: sipush #3000
    //   4557: bipush #10
    //   4559: aload_1
    //   4560: aload #11
    //   4562: ldc_w 'GetPIDByDevice'
    //   4565: aload #13
    //   4567: aload #12
    //   4569: ldc '0'
    //   4571: invokespecial GetCommandStatus : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;Ljava/lang/String;)V
    //   4574: goto -> 5023
    //   4577: aload_0
    //   4578: getfield actionId : Ljava/lang/String;
    //   4581: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   4584: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   4587: invokevirtual equals : (Ljava/lang/Object;)Z
    //   4590: ifeq -> 4820
    //   4593: aload #12
    //   4595: invokevirtual clear : ()V
    //   4598: aload #12
    //   4600: ldc_w 'sessionKey'
    //   4603: aload_0
    //   4604: getfield sessionKey : Ljava/lang/String;
    //   4607: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4610: pop
    //   4611: aload #12
    //   4613: ldc_w 'login'
    //   4616: aload_0
    //   4617: getfield userName : Ljava/lang/String;
    //   4620: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4623: pop
    //   4624: aload #12
    //   4626: ldc_w 'password'
    //   4629: aload_0
    //   4630: getfield password : Ljava/lang/String;
    //   4633: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4636: pop
    //   4637: aload #12
    //   4639: ldc_w 'deviceId'
    //   4642: aload_0
    //   4643: getfield deviceId : Ljava/lang/String;
    //   4646: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4649: pop
    //   4650: aload #12
    //   4652: ldc_w 'actionCode'
    //   4655: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   4658: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   4661: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4664: pop
    //   4665: aload #12
    //   4667: ldc_w 'csvParams'
    //   4670: ldc ''
    //   4672: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4675: pop
    //   4676: aload #12
    //   4678: ldc_w 'userId'
    //   4681: aload_0
    //   4682: getfield userId : Ljava/lang/String;
    //   4685: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4688: pop
    //   4689: aload #12
    //   4691: ldc_w 'applicationSourceId'
    //   4694: aload_0
    //   4695: getfield applicationSourceId : Ljava/lang/String;
    //   4698: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4701: pop
    //   4702: aload #12
    //   4704: ldc_w 'deviceCSVParams'
    //   4707: ldc ''
    //   4709: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4712: pop
    //   4713: new java/lang/StringBuilder
    //   4716: dup
    //   4717: invokespecial <init> : ()V
    //   4720: astore #13
    //   4722: aload #13
    //   4724: aload #11
    //   4726: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4729: pop
    //   4730: aload #13
    //   4732: ldc_w 'IService1/'
    //   4735: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4738: pop
    //   4739: aload #13
    //   4741: ldc_w 'GetDTCInformationByDeviceId'
    //   4744: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4747: pop
    //   4748: aload #13
    //   4750: invokevirtual toString : ()Ljava/lang/String;
    //   4753: astore #13
    //   4755: aload_0
    //   4756: aload_0
    //   4757: getfield actionId : Ljava/lang/String;
    //   4760: sipush #30000
    //   4763: sipush #3000
    //   4766: bipush #10
    //   4768: aload_1
    //   4769: aload #11
    //   4771: ldc_w 'GetDTCInformationByDeviceId'
    //   4774: aload #13
    //   4776: aload #12
    //   4778: ldc '0'
    //   4780: invokespecial GetCommandStatus : (Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/LinkedHashMap;Ljava/lang/String;)V
    //   4783: aload_0
    //   4784: iconst_3
    //   4785: anewarray java/lang/String
    //   4788: dup
    //   4789: iconst_0
    //   4790: ldc_w '4'
    //   4793: aastore
    //   4794: dup
    //   4795: iconst_1
    //   4796: aload_0
    //   4797: getfield actionId : Ljava/lang/String;
    //   4800: aastore
    //   4801: dup
    //   4802: iconst_2
    //   4803: aload_0
    //   4804: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   4807: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   4810: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   4813: aastore
    //   4814: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   4817: goto -> 5023
    //   4820: aload_0
    //   4821: getfield actionId : Ljava/lang/String;
    //   4824: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   4827: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   4830: invokevirtual equals : (Ljava/lang/Object;)Z
    //   4833: ifne -> 4849
    //   4836: iload #6
    //   4838: sipush #1000
    //   4841: imul
    //   4842: i2l
    //   4843: invokestatic sleep : (J)V
    //   4846: goto -> 4989
    //   4849: new java/lang/StringBuilder
    //   4852: astore_1
    //   4853: aload_1
    //   4854: invokespecial <init> : ()V
    //   4857: aload_1
    //   4858: aload_0
    //   4859: getfield deviceId : Ljava/lang/String;
    //   4862: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4865: pop
    //   4866: aload_1
    //   4867: ldc_w '|0|0|0|0|0|3|'
    //   4870: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4873: pop
    //   4874: aload_1
    //   4875: invokevirtual toString : ()Ljava/lang/String;
    //   4878: astore_1
    //   4879: aload_0
    //   4880: aload_0
    //   4881: getfield context : Landroid/content/Context;
    //   4884: aload_0
    //   4885: getfield actionId : Ljava/lang/String;
    //   4888: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   4891: invokevirtual intValue : ()I
    //   4894: aload_1
    //   4895: ldc ''
    //   4897: aload_0
    //   4898: getfield deviceId : Ljava/lang/String;
    //   4901: ldc '0'
    //   4903: invokespecial adjustWCFResult : (Landroid/content/Context;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   4906: astore_1
    //   4907: aload_1
    //   4908: invokevirtual getLongitude : ()F
    //   4911: f2d
    //   4912: dconst_0
    //   4913: dcmpl
    //   4914: ifeq -> 4989
    //   4917: aload_1
    //   4918: invokevirtual getLatitude : ()F
    //   4921: f2d
    //   4922: dconst_0
    //   4923: dcmpl
    //   4924: ifeq -> 4989
    //   4927: aload_0
    //   4928: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   4931: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   4934: invokevirtual setIdResponse : (Lcom/roadtrack/onstar/Enums$ActionResultCode;)V
    //   4937: goto -> 4989
    //   4940: astore #12
    //   4942: aload_0
    //   4943: getfield TAG : Ljava/lang/String;
    //   4946: astore_1
    //   4947: new java/lang/StringBuilder
    //   4950: dup
    //   4951: invokespecial <init> : ()V
    //   4954: astore #11
    //   4956: aload #11
    //   4958: aload_0
    //   4959: getfield deviceId : Ljava/lang/String;
    //   4962: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4965: pop
    //   4966: aload #11
    //   4968: ldc_w ': Error: Actions.Timer'
    //   4971: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4974: pop
    //   4975: aload_1
    //   4976: aload #11
    //   4978: invokevirtual toString : ()Ljava/lang/String;
    //   4981: aload #12
    //   4983: invokevirtual toString : ()Ljava/lang/String;
    //   4986: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   4989: aload_0
    //   4990: iconst_3
    //   4991: anewarray java/lang/String
    //   4994: dup
    //   4995: iconst_0
    //   4996: ldc_w '3'
    //   4999: aastore
    //   5000: dup
    //   5001: iconst_1
    //   5002: aload_0
    //   5003: getfield actionId : Ljava/lang/String;
    //   5006: aastore
    //   5007: dup
    //   5008: iconst_2
    //   5009: aload_0
    //   5010: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   5013: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   5016: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   5019: aastore
    //   5020: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   5023: iload #4
    //   5025: i2l
    //   5026: lstore #9
    //   5028: lload #9
    //   5030: invokestatic sleep : (J)V
    //   5033: goto -> 5080
    //   5036: astore_1
    //   5037: aload_0
    //   5038: getfield TAG : Ljava/lang/String;
    //   5041: astore #11
    //   5043: new java/lang/StringBuilder
    //   5046: dup
    //   5047: invokespecial <init> : ()V
    //   5050: astore_1
    //   5051: aload_1
    //   5052: aload_0
    //   5053: getfield deviceId : Ljava/lang/String;
    //   5056: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5059: pop
    //   5060: aload_1
    //   5061: ldc_w ': Error'
    //   5064: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5067: pop
    //   5068: aload #11
    //   5070: aload_1
    //   5071: invokevirtual toString : ()Ljava/lang/String;
    //   5074: ldc_w 'Waiting'
    //   5077: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   5080: aload_0
    //   5081: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   5084: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   5087: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   5090: astore_1
    //   5091: aload_0
    //   5092: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   5095: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   5098: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   5101: if_acmpeq -> 5127
    //   5104: aload_0
    //   5105: ldc_w '-1'
    //   5108: putfield messageId : Ljava/lang/String;
    //   5111: ldc_w '-1'
    //   5114: astore #11
    //   5116: ldc '0'
    //   5118: astore_1
    //   5119: ldc_w '2'
    //   5122: astore #12
    //   5124: goto -> 5136
    //   5127: ldc '0'
    //   5129: astore #11
    //   5131: ldc_w '1'
    //   5134: astore #12
    //   5136: aload_0
    //   5137: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   5140: invokevirtual getLatitude : ()F
    //   5143: fstore_3
    //   5144: aload_0
    //   5145: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   5148: invokevirtual getLongitude : ()F
    //   5151: fstore_2
    //   5152: aload_0
    //   5153: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   5156: invokevirtual getGpsStatus : ()I
    //   5159: istore #4
    //   5161: aload_0
    //   5162: getfield deviceId : Ljava/lang/String;
    //   5165: aload_0
    //   5166: getfield context : Landroid/content/Context;
    //   5169: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   5172: astore #13
    //   5174: aload_0
    //   5175: getfield actionId : Ljava/lang/String;
    //   5178: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   5181: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   5184: invokevirtual equals : (Ljava/lang/Object;)Z
    //   5187: ifne -> 5246
    //   5190: aload_0
    //   5191: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   5194: aload #14
    //   5196: aload_0
    //   5197: getfield historicoId : J
    //   5200: invokestatic toString : (J)Ljava/lang/String;
    //   5203: aload #12
    //   5205: fload_3
    //   5206: invokestatic valueOf : (F)Ljava/lang/String;
    //   5209: fload_2
    //   5210: invokestatic valueOf : (F)Ljava/lang/String;
    //   5213: aload #13
    //   5215: aload_0
    //   5216: getfield messageId : Ljava/lang/String;
    //   5219: aload_1
    //   5220: aload_0
    //   5221: getfield sendCommandErrorCode : I
    //   5224: invokestatic toString : (I)Ljava/lang/String;
    //   5227: aload #11
    //   5229: ldc '0'
    //   5231: iload #4
    //   5233: aload_0
    //   5234: getfield actionId : Ljava/lang/String;
    //   5237: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   5240: invokevirtual intValue : ()I
    //   5243: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   5246: aload_0
    //   5247: iconst_3
    //   5248: anewarray java/lang/String
    //   5251: dup
    //   5252: iconst_0
    //   5253: ldc_w '99'
    //   5256: aastore
    //   5257: dup
    //   5258: iconst_1
    //   5259: aload_0
    //   5260: getfield actionId : Ljava/lang/String;
    //   5263: aastore
    //   5264: dup
    //   5265: iconst_2
    //   5266: aload_0
    //   5267: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   5270: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   5273: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   5276: aastore
    //   5277: invokevirtual publishProgress : ([Ljava/lang/Object;)V
    //   5280: ldc '0'
    //   5282: areturn
    //   5283: ldc ''
    //   5285: areturn
    // Exception table:
    //   from	to	target	type
    //   1329	1334	1337	java/lang/InterruptedException
    //   1967	1972	1975	java/lang/InterruptedException
    //   2270	2275	2278	java/lang/InterruptedException
    //   2557	2562	2565	java/lang/InterruptedException
    //   2729	2734	2737	java/lang/InterruptedException
    //   3294	3300	3303	java/lang/InterruptedException
    //   4247	4252	4255	java/lang/InterruptedException
    //   4820	4846	4940	java/lang/InterruptedException
    //   4849	4937	4940	java/lang/InterruptedException
    //   5028	5033	5036	java/lang/InterruptedException
  }
  
  protected void onCancelled() {}
  
  protected void onPostExecute(String paramString) {
    GlobalMembers.messsageIdGlobal = "";
    PreferenceRT.SetValuePreference(Enums.SettingsPreference.statusNotifications, Boolean.valueOf(true), this.context);
    Intent intent = new Intent("BROADCAST_ALERTS_NOTIFACTIONS");
    this.context.sendBroadcast(intent);
    cancel(true);
    OnPostExecuteListener onPostExecuteListener = this.mOnPosteExecuteListener;
    if (onPostExecuteListener != null)
      onPostExecuteListener.onPostExecuteListener(this.result); 
    if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue() && !MainActivity.onHornLights.booleanValue() && !MainActivity.onCloseDoors.booleanValue() && !MainActivity.onOpenDoors.booleanValue() && !MainActivity.onDisarmPINCODE.booleanValue() && !MainActivity.onAlertParking.booleanValue() && !MainActivity.onAlertSpeed.booleanValue() && !MainActivity.onAlertValet.booleanValue() && !MainActivity.onNotification.booleanValue() && !MainActivity.onDTC.booleanValue()) {
      Activity activity = this.act;
      if (activity != null)
        OrientationManager.unlockOrientation(activity); 
    } 
    this.endTime = System.nanoTime();
  }
  
  protected void onPreExecute() {
    GlobalMembers.messsageIdGlobal = null;
    GlobalMembers.processMessageId = false;
    this.startTime = System.nanoTime();
    String str1 = this.TAG;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("ActionID: ");
    stringBuilder1.append(this.actionId);
    String str2 = stringBuilder1.toString();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(this.deviceId);
    stringBuilder2.append(": PREXECUTE");
    Utilities.escribeArchivo(str1, str2, stringBuilder2.toString());
    if (this.swipeProgress == null) {
      ProgressBar progressBar = this.pbControl;
      if (progressBar != null)
        progressBar.setVisibility(0); 
      Activity activity = this.act;
      if (activity != null)
        OrientationManager.lockOrientation(activity); 
      TextView textView = this.tvControl;
      if (textView != null)
        textView.setEnabled(false); 
      ImageView imageView = this.ivControl;
      if (imageView != null)
        imageView.setEnabled(false); 
      Button button = this.btControl;
      if (button != null)
        button.setEnabled(false); 
      CustomActionButton customActionButton = this.actionButton;
      if (customActionButton != null)
        customActionButton.showActionStatus(1); 
    } 
  }
  
  protected void onProgressUpdate(String... paramVarArgs) {
    // Byte code:
    //   0: aload_0
    //   1: new com/roadtrack/onstar/VO/StringsResourcesVO
    //   4: dup
    //   5: invokespecial <init> : ()V
    //   8: putfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   11: aload_0
    //   12: getfield context : Landroid/content/Context;
    //   15: aload_0
    //   16: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   19: getfield global_lbl_acciondescfallared_1 : Ljava/lang/String;
    //   22: ldc_w 2131689862
    //   25: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   28: astore #9
    //   30: aload_0
    //   31: getfield context : Landroid/content/Context;
    //   34: aload_0
    //   35: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   38: getfield global_lbl_acciondescposiblenotif_1 : Ljava/lang/String;
    //   41: ldc_w 2131689867
    //   44: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   47: astore #8
    //   49: aload_0
    //   50: getfield context : Landroid/content/Context;
    //   53: aload_0
    //   54: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   57: getfield global_status_lbl_noejecutado_1 : Ljava/lang/String;
    //   60: ldc_w 2131689971
    //   63: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   66: astore #7
    //   68: aload_0
    //   69: getfield context : Landroid/content/Context;
    //   72: aload_0
    //   73: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   76: getfield global_lbl_accionstatusnoejecutado_1 : Ljava/lang/String;
    //   79: ldc_w 2131689900
    //   82: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   85: astore #10
    //   87: aload_0
    //   88: getfield context : Landroid/content/Context;
    //   91: aload_0
    //   92: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   95: getfield pid_main_lbl_diagnotico_12 : Ljava/lang/String;
    //   98: ldc_w 2131690247
    //   101: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   104: astore #13
    //   106: aload_1
    //   107: iconst_0
    //   108: aaload
    //   109: astore #14
    //   111: aload_1
    //   112: iconst_1
    //   113: aaload
    //   114: astore #12
    //   116: aload_1
    //   117: iconst_2
    //   118: aaload
    //   119: astore #11
    //   121: aload_1
    //   122: arraylength
    //   123: iconst_3
    //   124: if_icmple -> 135
    //   127: aload_1
    //   128: iconst_3
    //   129: aaload
    //   130: astore #5
    //   132: goto -> 139
    //   135: ldc '0'
    //   137: astore #5
    //   139: aload #5
    //   141: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   144: invokevirtual intValue : ()I
    //   147: istore_2
    //   148: aload_0
    //   149: aload_0
    //   150: iload_2
    //   151: invokespecial parseStatus : (I)I
    //   154: putfield buttonStatus : I
    //   157: aload_1
    //   158: arraylength
    //   159: iconst_4
    //   160: if_icmple -> 168
    //   163: aload_1
    //   164: iconst_4
    //   165: aaload
    //   166: astore #5
    //   168: aload_1
    //   169: arraylength
    //   170: iconst_5
    //   171: if_icmple -> 182
    //   174: aload_1
    //   175: iconst_5
    //   176: aaload
    //   177: astore #6
    //   179: goto -> 186
    //   182: ldc ''
    //   184: astore #6
    //   186: aload #14
    //   188: ldc_w '1'
    //   191: invokevirtual equals : (Ljava/lang/Object;)Z
    //   194: ifeq -> 335
    //   197: aload_0
    //   198: getfield swipeProgress : Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;
    //   201: ifnonnull -> 1680
    //   204: aload_0
    //   205: getfield pbControl : Landroid/widget/ProgressBar;
    //   208: astore #5
    //   210: aload #5
    //   212: ifnull -> 222
    //   215: aload #5
    //   217: bipush #8
    //   219: invokevirtual setVisibility : (I)V
    //   222: iconst_0
    //   223: putstatic com/roadtrack/onstar/MainActivity.isShowingOnFindMe : Z
    //   226: ldc_w 2131165626
    //   229: istore_2
    //   230: aload_0
    //   231: getfield context : Landroid/content/Context;
    //   234: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.palomita_azul_fondo : Ljava/lang/String;
    //   237: ldc_w 2131165626
    //   240: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   243: astore #5
    //   245: aload #12
    //   247: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   250: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   253: invokevirtual equals : (Ljava/lang/Object;)Z
    //   256: ifeq -> 278
    //   259: ldc_w 2131165628
    //   262: istore_2
    //   263: aload_0
    //   264: getfield context : Landroid/content/Context;
    //   267: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.palomita_azul_sintexto_fondo : Ljava/lang/String;
    //   270: ldc_w 2131165628
    //   273: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   276: astore #5
    //   278: aload_0
    //   279: aload #5
    //   281: iconst_1
    //   282: invokespecial changeIconDrawable : (Landroid/graphics/drawable/Drawable;Z)V
    //   285: aload_0
    //   286: aload_0
    //   287: iload_2
    //   288: invokespecial parseStatus : (I)I
    //   291: putfield buttonStatus : I
    //   294: aload_0
    //   295: getfield actionButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   298: astore #5
    //   300: aload #5
    //   302: ifnull -> 314
    //   305: aload #5
    //   307: aload_0
    //   308: getfield buttonStatus : I
    //   311: invokevirtual showActionStatus : (I)V
    //   314: aload #12
    //   316: ldc_w '1'
    //   319: invokevirtual equals : (Ljava/lang/Object;)Z
    //   322: ifeq -> 1680
    //   325: aload_0
    //   326: getfield buttonStatus : I
    //   329: putstatic com/roadtrack/onstar/MainActivity.actFindMe : I
    //   332: goto -> 1680
    //   335: aload #14
    //   337: ldc_w '2'
    //   340: invokevirtual equals : (Ljava/lang/Object;)Z
    //   343: istore #4
    //   345: ldc_w 2131165620
    //   348: istore_3
    //   349: iload #4
    //   351: ifeq -> 593
    //   354: aload_0
    //   355: aload #12
    //   357: invokespecial wakeUpCarSetStatus : (Ljava/lang/String;)V
    //   360: aload_0
    //   361: getfield swipeProgress : Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;
    //   364: ifnonnull -> 556
    //   367: aload #12
    //   369: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   372: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   375: invokevirtual equals : (Ljava/lang/Object;)Z
    //   378: ifeq -> 414
    //   381: aload_0
    //   382: getfield context : Landroid/content/Context;
    //   385: ldc_w com/roadtrack/onstar/googleMaps/ActivityMapViewerG
    //   388: invokestatic isActivityRunning : (Landroid/content/Context;Ljava/lang/Class;)Z
    //   391: ifeq -> 414
    //   394: aload_0
    //   395: getfield context : Landroid/content/Context;
    //   398: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.palomita_2azul_executado_fondo : Ljava/lang/String;
    //   401: ldc_w 2131165620
    //   404: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   407: astore #5
    //   409: iload_3
    //   410: istore_2
    //   411: goto -> 417
    //   414: aconst_null
    //   415: astore #5
    //   417: aload_0
    //   418: aload #5
    //   420: iconst_1
    //   421: invokespecial changeIconDrawable : (Landroid/graphics/drawable/Drawable;Z)V
    //   424: aload_0
    //   425: aload_0
    //   426: iload_2
    //   427: invokespecial parseStatus : (I)I
    //   430: putfield buttonStatus : I
    //   433: aload_0
    //   434: getfield actionButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   437: astore #5
    //   439: aload #5
    //   441: ifnull -> 453
    //   444: aload #5
    //   446: aload_0
    //   447: getfield buttonStatus : I
    //   450: invokevirtual showActionStatus : (I)V
    //   453: aload #11
    //   455: getstatic com/roadtrack/onstar/Enums$ActionResultCode.NoResult : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   458: invokevirtual toString : ()Ljava/lang/String;
    //   461: invokevirtual equals : (Ljava/lang/Object;)Z
    //   464: ifeq -> 484
    //   467: aload_0
    //   468: getfield context : Landroid/content/Context;
    //   471: aload #12
    //   473: ldc_w 'title'
    //   476: aload #6
    //   478: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   481: goto -> 556
    //   484: aload #11
    //   486: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   489: invokevirtual toString : ()Ljava/lang/String;
    //   492: invokevirtual equals : (Ljava/lang/Object;)Z
    //   495: ifeq -> 556
    //   498: aload #12
    //   500: getstatic com/roadtrack/onstar/Enums$Services.SendPNDNavigationCommand : Lcom/roadtrack/onstar/Enums$Services;
    //   503: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   506: invokevirtual equals : (Ljava/lang/Object;)Z
    //   509: ifeq -> 556
    //   512: aload_0
    //   513: getfield context : Landroid/content/Context;
    //   516: aload_0
    //   517: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   520: getfield global_lbl_accionstatusenviadogps_1 : Ljava/lang/String;
    //   523: ldc_w 2131689891
    //   526: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   529: iconst_1
    //   530: anewarray java/lang/Object
    //   533: dup
    //   534: iconst_0
    //   535: ldc ''
    //   537: aastore
    //   538: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   541: astore #5
    //   543: aload_0
    //   544: getfield context : Landroid/content/Context;
    //   547: aload #5
    //   549: iconst_1
    //   550: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   553: invokevirtual show : ()V
    //   556: aload_0
    //   557: getfield mOnProgressUpdateListenerCustomButton : Lcom/roadtrack/onstar/BO/ActionsProcess$OnProgressUpdateListenerCustomActionButton;
    //   560: ifnull -> 1680
    //   563: aload #12
    //   565: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   568: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   571: invokevirtual equals : (Ljava/lang/Object;)Z
    //   574: ifeq -> 1680
    //   577: aload_0
    //   578: getfield mOnProgressUpdateListenerCustomButton : Lcom/roadtrack/onstar/BO/ActionsProcess$OnProgressUpdateListenerCustomActionButton;
    //   581: aload_0
    //   582: getfield actionButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   585: invokeinterface onProgressUpdateListenerCustomButton : (Lcom/roadtrack/onstar/VO/CustomActionButton;)V
    //   590: goto -> 1680
    //   593: aload #14
    //   595: ldc_w '3'
    //   598: invokevirtual equals : (Ljava/lang/Object;)Z
    //   601: istore #4
    //   603: ldc_w 2131165583
    //   606: istore_3
    //   607: iload #4
    //   609: ifeq -> 1334
    //   612: aload_0
    //   613: getfield swipeProgress : Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;
    //   616: ifnonnull -> 1325
    //   619: aload_0
    //   620: getfield pbControl : Landroid/widget/ProgressBar;
    //   623: astore #5
    //   625: aload #5
    //   627: ifnull -> 637
    //   630: aload #5
    //   632: bipush #8
    //   634: invokevirtual setVisibility : (I)V
    //   637: aload_0
    //   638: getfield pbControl : Landroid/widget/ProgressBar;
    //   641: astore #5
    //   643: aload #5
    //   645: ifnull -> 655
    //   648: aload #5
    //   650: bipush #8
    //   652: invokevirtual setVisibility : (I)V
    //   655: aload_0
    //   656: getfield actionButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   659: astore #5
    //   661: aload #5
    //   663: ifnull -> 671
    //   666: aload #5
    //   668: invokevirtual dismissProgressBar : ()V
    //   671: iconst_0
    //   672: putstatic com/roadtrack/onstar/MainActivity.isShowingOnFindMe : Z
    //   675: aload_0
    //   676: getfield context : Landroid/content/Context;
    //   679: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.no_ejecutado_azul_fondo : Ljava/lang/String;
    //   682: ldc_w 2131165581
    //   685: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   688: astore #5
    //   690: aload #12
    //   692: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   695: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   698: invokevirtual equals : (Ljava/lang/Object;)Z
    //   701: ifeq -> 724
    //   704: aload_0
    //   705: getfield context : Landroid/content/Context;
    //   708: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.no_ejecutado_azul_sintexto_fondo : Ljava/lang/String;
    //   711: ldc_w 2131165583
    //   714: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   717: astore #5
    //   719: iload_3
    //   720: istore_2
    //   721: goto -> 728
    //   724: ldc_w 2131165581
    //   727: istore_2
    //   728: aload_0
    //   729: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   732: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   735: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Activated : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   738: if_acmpne -> 835
    //   741: aload_0
    //   742: getfield context : Landroid/content/Context;
    //   745: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.palomita_2azul_executado_fondo : Ljava/lang/String;
    //   748: ldc_w 2131165620
    //   751: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   754: astore #5
    //   756: aload #12
    //   758: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   761: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   764: invokevirtual equals : (Ljava/lang/Object;)Z
    //   767: ifeq -> 792
    //   770: ldc_w 2131165624
    //   773: istore_2
    //   774: aload_0
    //   775: getfield context : Landroid/content/Context;
    //   778: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.palomita_2azul_sintexto_fondo : Ljava/lang/String;
    //   781: ldc_w 2131165624
    //   784: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   787: astore #5
    //   789: goto -> 796
    //   792: ldc_w 2131165620
    //   795: istore_2
    //   796: aload #12
    //   798: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   801: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   804: invokevirtual equals : (Ljava/lang/Object;)Z
    //   807: ifeq -> 832
    //   810: aload_0
    //   811: getfield context : Landroid/content/Context;
    //   814: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.palomita_2azul_executado_fondo : Ljava/lang/String;
    //   817: ldc_w 2131165620
    //   820: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   823: astore #5
    //   825: ldc_w 2131165620
    //   828: istore_2
    //   829: goto -> 832
    //   832: goto -> 1251
    //   835: aload_0
    //   836: getfield messageId : Ljava/lang/String;
    //   839: ldc '0'
    //   841: invokevirtual equals : (Ljava/lang/Object;)Z
    //   844: istore #4
    //   846: ldc_w 2131165687
    //   849: istore_3
    //   850: iload #4
    //   852: ifeq -> 1074
    //   855: aload #12
    //   857: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   860: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   863: invokevirtual equals : (Ljava/lang/Object;)Z
    //   866: ifne -> 887
    //   869: aload #9
    //   871: astore #6
    //   873: aload #12
    //   875: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   878: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   881: invokevirtual equals : (Ljava/lang/Object;)Z
    //   884: ifeq -> 891
    //   887: aload #7
    //   889: astore #6
    //   891: aload_0
    //   892: getfield sendCommandErrorCode : I
    //   895: aload_0
    //   896: getfield sendCommandErrorCodeTimeOut : I
    //   899: if_icmpne -> 1027
    //   902: aload #12
    //   904: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   907: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   910: invokevirtual equals : (Ljava/lang/Object;)Z
    //   913: ifne -> 930
    //   916: aload #12
    //   918: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   921: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   924: invokevirtual equals : (Ljava/lang/Object;)Z
    //   927: ifeq -> 934
    //   930: aload #7
    //   932: astore #8
    //   934: aload_0
    //   935: getfield context : Landroid/content/Context;
    //   938: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.timeout_azul_fondo : Ljava/lang/String;
    //   941: ldc_w 2131165684
    //   944: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   947: astore #5
    //   949: aload #12
    //   951: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   954: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   957: invokevirtual equals : (Ljava/lang/Object;)Z
    //   960: ifeq -> 983
    //   963: aload_0
    //   964: getfield context : Landroid/content/Context;
    //   967: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.timeout_azul_sintexto_fondo : Ljava/lang/String;
    //   970: ldc_w 2131165687
    //   973: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   976: astore #5
    //   978: iload_3
    //   979: istore_2
    //   980: goto -> 987
    //   983: ldc_w 2131165684
    //   986: istore_2
    //   987: aload #12
    //   989: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   992: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   995: invokevirtual equals : (Ljava/lang/Object;)Z
    //   998: ifeq -> 1020
    //   1001: aload_0
    //   1002: getfield context : Landroid/content/Context;
    //   1005: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.no_ejecutado_azul_fondo : Ljava/lang/String;
    //   1008: ldc_w 2131165581
    //   1011: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   1014: astore #5
    //   1016: ldc_w 2131165581
    //   1019: istore_2
    //   1020: aload #8
    //   1022: astore #6
    //   1024: goto -> 1027
    //   1027: aload #12
    //   1029: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   1032: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1035: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1038: ifne -> 1058
    //   1041: aload_0
    //   1042: getfield context : Landroid/content/Context;
    //   1045: aload #12
    //   1047: ldc_w 'title'
    //   1050: aload #6
    //   1052: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1055: goto -> 1251
    //   1058: aload_0
    //   1059: getfield context : Landroid/content/Context;
    //   1062: aload #12
    //   1064: aload #13
    //   1066: aload #6
    //   1068: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1071: goto -> 1251
    //   1074: aload_0
    //   1075: getfield context : Landroid/content/Context;
    //   1078: aload #12
    //   1080: ldc_w 'title'
    //   1083: aload #10
    //   1085: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1088: aload #10
    //   1090: astore #6
    //   1092: aload #11
    //   1094: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Fail : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1097: invokevirtual toString : ()Ljava/lang/String;
    //   1100: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1103: ifne -> 1251
    //   1106: aload #12
    //   1108: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   1111: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1114: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1117: ifne -> 1144
    //   1120: aload #12
    //   1122: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   1125: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1128: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1131: ifeq -> 1137
    //   1134: goto -> 1144
    //   1137: aload #8
    //   1139: astore #6
    //   1141: goto -> 1148
    //   1144: aload #7
    //   1146: astore #6
    //   1148: aload_0
    //   1149: getfield context : Landroid/content/Context;
    //   1152: aload #12
    //   1154: ldc_w 'title'
    //   1157: aload #6
    //   1159: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1162: aload_0
    //   1163: getfield context : Landroid/content/Context;
    //   1166: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.timeout_azul_fondo : Ljava/lang/String;
    //   1169: ldc_w 2131165684
    //   1172: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   1175: astore #5
    //   1177: aload #12
    //   1179: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   1182: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1185: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1188: ifeq -> 1211
    //   1191: aload_0
    //   1192: getfield context : Landroid/content/Context;
    //   1195: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.timeout_azul_sintexto_fondo : Ljava/lang/String;
    //   1198: ldc_w 2131165687
    //   1201: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   1204: astore #5
    //   1206: iload_3
    //   1207: istore_2
    //   1208: goto -> 1215
    //   1211: ldc_w 2131165684
    //   1214: istore_2
    //   1215: aload #12
    //   1217: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   1220: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1223: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1226: ifeq -> 1251
    //   1229: aload_0
    //   1230: getfield context : Landroid/content/Context;
    //   1233: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.no_ejecutado_azul_fondo : Ljava/lang/String;
    //   1236: ldc_w 2131165581
    //   1239: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   1242: astore #5
    //   1244: ldc_w 2131165581
    //   1247: istore_2
    //   1248: goto -> 1251
    //   1251: aload #6
    //   1253: invokevirtual isEmpty : ()Z
    //   1256: ifne -> 1289
    //   1259: aload #12
    //   1261: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   1264: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1267: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1270: ifne -> 1289
    //   1273: aload_0
    //   1274: getfield context : Landroid/content/Context;
    //   1277: aload #6
    //   1279: iconst_1
    //   1280: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1283: invokevirtual show : ()V
    //   1286: goto -> 1289
    //   1289: aload_0
    //   1290: aload #5
    //   1292: iconst_1
    //   1293: invokespecial changeIconDrawable : (Landroid/graphics/drawable/Drawable;Z)V
    //   1296: aload_0
    //   1297: aload_0
    //   1298: iload_2
    //   1299: invokespecial parseStatus : (I)I
    //   1302: putfield buttonStatus : I
    //   1305: aload_0
    //   1306: getfield actionButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   1309: astore #5
    //   1311: aload #5
    //   1313: ifnull -> 1325
    //   1316: aload #5
    //   1318: aload_0
    //   1319: getfield buttonStatus : I
    //   1322: invokevirtual showActionStatus : (I)V
    //   1325: aload_0
    //   1326: aload #12
    //   1328: invokespecial wakeUpCarSetStatus : (Ljava/lang/String;)V
    //   1331: goto -> 1680
    //   1334: aload #14
    //   1336: ldc_w '99'
    //   1339: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1342: ifeq -> 1508
    //   1345: aload #12
    //   1347: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   1350: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1353: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1356: ifne -> 1508
    //   1359: aload_0
    //   1360: getfield swipeProgress : Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;
    //   1363: ifnull -> 1394
    //   1366: aload_0
    //   1367: getfield tvControl : Landroid/widget/TextView;
    //   1370: astore #5
    //   1372: aload #5
    //   1374: ifnull -> 1383
    //   1377: aload #5
    //   1379: iconst_0
    //   1380: invokevirtual setVisibility : (I)V
    //   1383: aload_0
    //   1384: getfield swipeProgress : Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;
    //   1387: iconst_0
    //   1388: invokevirtual setRefreshing : (Z)V
    //   1391: goto -> 1499
    //   1394: aload #12
    //   1396: ldc_w '1'
    //   1399: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1402: ifne -> 1481
    //   1405: aload_0
    //   1406: getfield pbControl : Landroid/widget/ProgressBar;
    //   1409: astore #5
    //   1411: aload #5
    //   1413: ifnull -> 1423
    //   1416: aload #5
    //   1418: bipush #8
    //   1420: invokevirtual setVisibility : (I)V
    //   1423: aload_0
    //   1424: getfield actionButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   1427: astore #5
    //   1429: aload #5
    //   1431: ifnull -> 1439
    //   1434: aload #5
    //   1436: invokevirtual dismissProgressBar : ()V
    //   1439: iconst_0
    //   1440: putstatic com/roadtrack/onstar/MainActivity.isShowingOnFindMe : Z
    //   1443: aload_0
    //   1444: aconst_null
    //   1445: iconst_0
    //   1446: invokespecial changeIconDrawable : (Landroid/graphics/drawable/Drawable;Z)V
    //   1449: aload_0
    //   1450: aload_0
    //   1451: iload_2
    //   1452: invokespecial parseStatus : (I)I
    //   1455: putfield buttonStatus : I
    //   1458: aload_0
    //   1459: getfield actionButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   1462: astore #5
    //   1464: aload #5
    //   1466: ifnull -> 1499
    //   1469: aload #5
    //   1471: aload_0
    //   1472: getfield buttonStatus : I
    //   1475: invokevirtual showActionStatus : (I)V
    //   1478: goto -> 1499
    //   1481: aload_0
    //   1482: aload_0
    //   1483: ldc_w 2131165620
    //   1486: invokespecial parseStatus : (I)I
    //   1489: putfield buttonStatus : I
    //   1492: aload_0
    //   1493: getfield buttonStatus : I
    //   1496: putstatic com/roadtrack/onstar/MainActivity.actFindMe : I
    //   1499: aload_0
    //   1500: aload #12
    //   1502: invokespecial wakeUpCarSetStatus : (Ljava/lang/String;)V
    //   1505: goto -> 1680
    //   1508: aload #14
    //   1510: ldc_w '4'
    //   1513: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1516: ifeq -> 1680
    //   1519: aload #12
    //   1521: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   1524: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   1527: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1530: ifeq -> 1680
    //   1533: aload_0
    //   1534: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1537: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1540: getstatic com/roadtrack/onstar/Enums$ActionResultCode.NoResult : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1543: if_acmpeq -> 1559
    //   1546: aload_0
    //   1547: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1550: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1553: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Fail : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1556: if_acmpne -> 1652
    //   1559: aload_0
    //   1560: aload_0
    //   1561: getfield context : Landroid/content/Context;
    //   1564: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.no_ejecutado_azul_sintexto_fondo : Ljava/lang/String;
    //   1567: ldc_w 2131165583
    //   1570: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   1573: iconst_1
    //   1574: invokespecial changeIconDrawable : (Landroid/graphics/drawable/Drawable;Z)V
    //   1577: aload_0
    //   1578: aload_0
    //   1579: ldc_w 2131165583
    //   1582: invokespecial parseStatus : (I)I
    //   1585: putfield buttonStatus : I
    //   1588: aload_0
    //   1589: getfield actionButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   1592: astore #5
    //   1594: aload #5
    //   1596: ifnull -> 1608
    //   1599: aload #5
    //   1601: aload_0
    //   1602: getfield buttonStatus : I
    //   1605: invokevirtual showActionStatus : (I)V
    //   1608: aload #12
    //   1610: ldc_w '1'
    //   1613: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1616: ifeq -> 1626
    //   1619: aload_0
    //   1620: getfield buttonStatus : I
    //   1623: putstatic com/roadtrack/onstar/MainActivity.actFindMe : I
    //   1626: aload_0
    //   1627: getfield result : Lcom/roadtrack/onstar/VO/ActionResultVO;
    //   1630: invokevirtual getIdResponse : ()Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1633: getstatic com/roadtrack/onstar/Enums$ActionResultCode.Fail : Lcom/roadtrack/onstar/Enums$ActionResultCode;
    //   1636: if_acmpeq -> 1652
    //   1639: aload_0
    //   1640: getfield context : Landroid/content/Context;
    //   1643: aload #12
    //   1645: aload #10
    //   1647: aload #9
    //   1649: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1652: aload_0
    //   1653: getfield mOnProgressUpdateListenerCustomButton : Lcom/roadtrack/onstar/BO/ActionsProcess$OnProgressUpdateListenerCustomActionButton;
    //   1656: astore #5
    //   1658: aload #5
    //   1660: ifnull -> 1674
    //   1663: aload #5
    //   1665: aload_0
    //   1666: getfield actionButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   1669: invokeinterface onProgressUpdateListenerCustomButton : (Lcom/roadtrack/onstar/VO/CustomActionButton;)V
    //   1674: aload_0
    //   1675: aload #12
    //   1677: invokespecial wakeUpCarSetStatus : (Ljava/lang/String;)V
    //   1680: aload_0
    //   1681: getfield deviceId : Ljava/lang/String;
    //   1684: aload #12
    //   1686: aload #11
    //   1688: invokestatic logActions : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1691: aload_0
    //   1692: getfield mOnProgressUpdateListener : Lcom/roadtrack/onstar/BO/ActionsProcess$OnProgressUpdateListener;
    //   1695: astore #5
    //   1697: aload #5
    //   1699: ifnull -> 1710
    //   1702: aload #5
    //   1704: aload_1
    //   1705: invokeinterface onProgressUpdateListener : ([Ljava/lang/String;)V
    //   1710: aload_0
    //   1711: invokestatic nanoTime : ()J
    //   1714: l2d
    //   1715: putfield palomaTime : D
    //   1718: return
  }
  
  public void setAddress(String paramString) {
    this.address = paramString;
  }
  
  public void setOnPostExecuteListener(OnPostExecuteListener paramOnPostExecuteListener) {
    this.mOnPosteExecuteListener = paramOnPostExecuteListener;
  }
  
  public void setOnProgressUpdateListenerCustomActionButton(OnProgressUpdateListenerCustomActionButton paramOnProgressUpdateListenerCustomActionButton) {
    this.mOnProgressUpdateListenerCustomButton = paramOnProgressUpdateListenerCustomActionButton;
  }
  
  public static interface OnPostExecuteListener {
    void onPostExecuteListener(ActionResultVO param1ActionResultVO);
  }
  
  public static interface OnProgressUpdateListener {
    void onProgressUpdateListener(String[] param1ArrayOfString);
  }
  
  public static interface OnProgressUpdateListenerCustomActionButton {
    void onProgressUpdateListenerCustomButton(CustomActionButton param1CustomActionButton);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/ActionsProcess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */