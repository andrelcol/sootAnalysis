package com.roadtrack.onstar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.SoapRequestObject;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.GoogleMapVO;
import com.roadtrack.onstar.VO.Historical;
import com.roadtrack.onstar.VO.PushAlertsVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.adapter.DialogAlert;
import com.roadtrack.onstar.adapter.DialogMapSupportDownload;
import com.roadtrack.onstar.adapter.DialogMenu;
import com.roadtrack.onstar.errors.DefaultExceptionHandler;
import com.roadtrack.onstar.googleMaps.ActivityMapViewerG;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import com.roadtrack.onstar.ui.settings.SettingsNewActivity;
import com.roadtrack.onstar.utils.BRInfo;
import com.roadtrack.onstar.utils.DialogEmpty;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.Utilities;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class NotificationsActivity extends FragmentActivity implements DialogMapSupportDownload.DialogMapSupportDownloadListener, DialogAlert.DialogAlertListener, DialogMenu.DialogMenuListener, SwipeRefreshLayout.OnRefreshListener {
  private static String TAG = "NotificationsActivity";
  
  private static Object mActionMode;
  
  private static Spinner spinner_menu;
  
  private TextView ActionValueMessage;
  
  private TextView Alertas;
  
  private TextView Fechas;
  
  private String KEY_EXIT = "exitApp";
  
  private String KEY_SPINNER_POSITION = "spinnerposition";
  
  MenuItem SeeMapButton;
  
  String accountId = "";
  
  private Activity act = (Activity)this;
  
  private CustomAdapter adapter;
  
  String applicationSourceId = Integer.toString(13);
  
  private boolean cancelable = true;
  
  private CheckBox chk;
  
  private CheckBox chk1;
  
  private Context context;
  
  String csvParams = "";
  
  private DBFunctions dbFunctions;
  
  String deviceCSVParams = "";
  
  String deviceId = "";
  
  private DialogMenu dialogMenu;
  
  private int drawableId;
  
  private boolean endProcess = false;
  
  private boolean exitApp = false;
  
  private IntentFilter followmefinish;
  
  private BroadcastReceiver followmefinishmyReceiver = new BroadcastReceiver() {
      final NotificationsActivity this$0;
      
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getExtras().getString("parameter").equals("show"))
          NotificationsActivity.this.followmefinisheddialog(); 
      }
    };
  
  private ImageView imgTitle;
  
  String login = "";
  
  private ListView lvData;
  
  private SparseBooleanArray mSelectedItemsIds;
  
  Menu menuMap;
  
  private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
      final NotificationsActivity this$0;
      
      public void onReceive(Context param1Context, Intent param1Intent) {
        Utilities.showNetworkServiceData((TextView)NotificationsActivity.this.findViewById(2131297056), (Context)NotificationsActivity.this, new TextView[0]);
      }
    };
  
  private ArrayList<PushAlertsVO> objectAlert;
  
  String password = "";
  
  private onstarApplication rtApp;
  
  String sessionKey;
  
  private String stringId;
  
  private StringsResourcesVO stringsResourcesVO;
  
  SwipeRefreshLayout swipeLayout;
  
  private Typeface tf;
  
  private String titleId;
  
  private TextView tvDummy;
  
  String userId = "";
  
  private UserPreferenceVO userPreference;
  
  private Drawable alertInfo() {
    return Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_notifonstar, 2131165296);
  }
  
  private void createLaterAlertDialog() {
    this.cancelable = true;
    this.drawableId = 2131165484;
    this.stringId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.mapdownloading_popup_lbl_recuerdequeusted_2, 2131690124);
    this.titleId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_mapdownloading_popup_lbl_descargademapa_1, 2131689940);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, this.drawableId);
    createMenuDialog(this.cancelable, drawable, this.stringId, this.titleId, false);
  }
  
  private DialogMenu createMenuDialog(boolean paramBoolean1, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean2) {
    try {
      this.dialogMenu = DialogMenu.newInstance();
      this.dialogMenu.setPreferenceDialogFragment(this, (Context)this, paramBoolean1, paramDrawable, paramString1, paramString2, paramBoolean2);
      this.dialogMenu.show(getFragmentManager(), null);
      return this.dialogMenu;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: createMenuDialog", exception.getMessage());
      return null;
    } 
  }
  
  private void createMobileAlertDialog() {
    this.cancelable = true;
    this.drawableId = 2131165484;
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, this.drawableId);
    this.stringId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_acticity_no_wifi, 2131690027);
    this.titleId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_mapdownloading_popup_lbl_descargademapa_1, 2131689940);
    createMenuDialog(this.cancelable, drawable, this.stringId, this.titleId, true);
  }
  
  private void executeNotificationProcess() {
    this.rtApp.getSessionKey();
    this.login = this.rtApp.getUserAccessData()[0];
    this.password = this.rtApp.getUserAccessData()[1];
    this.accountId = this.rtApp.getAccountID();
    this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId();
    this.userId = this.rtApp.getLocatorUserId();
    this.applicationSourceId = Integer.toString(13);
    String str = Enums$Services.ActionNotifications.GetCodeString();
    (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "", this.login, this.password, this.accountId, this.deviceId, str, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
  }
  
  private ArrayList<PushAlertsVO> fillEmptyFollowMePoints(ArrayList<PushAlertsVO> paramArrayList) {
    if (paramArrayList != null && !paramArrayList.isEmpty())
      for (PushAlertsVO pushAlertsVO : paramArrayList) {
        if (pushAlertsVO.getAcc().equals("12")) {
          if (pushAlertsVO.getActionId().isEmpty())
            pushAlertsVO.setActionId("FollowMe"); 
          if (pushAlertsVO.getAlert().isEmpty())
            pushAlertsVO.setAlert("FollowMe"); 
          if (pushAlertsVO.getAlertCodeId() != 27)
            pushAlertsVO.setAlertCodeId(27); 
          if (pushAlertsVO.getCompletion_code().isEmpty())
            pushAlertsVO.setCompletion_code("0"); 
          pushAlertsVO.getDate().isEmpty();
          pushAlertsVO.getDate_execution().isEmpty();
          pushAlertsVO.getId().isEmpty();
          if (!pushAlertsVO.getInsertedRow().isEmpty())
            pushAlertsVO.setInsertedRow(""); 
          if (pushAlertsVO.isAction())
            pushAlertsVO.setAction(false); 
          pushAlertsVO.getLatitude().isEmpty();
          pushAlertsVO.getLongitude().isEmpty();
          if (pushAlertsVO.getMessageResponseId() != null && !pushAlertsVO.getMessageResponseId().equals("0"))
            pushAlertsVO.setMessageResponseId("0"); 
          if (!pushAlertsVO.getnActionID().isEmpty())
            pushAlertsVO.setnActionID("FollowMe"); 
          if (pushAlertsVO.getRequestErroId().isEmpty())
            pushAlertsVO.setRequestErroId("0"); 
          if (pushAlertsVO.getResponseErrorId().isEmpty())
            pushAlertsVO.setResponseErrorId("0"); 
          if (pushAlertsVO.getStatus() != null && !pushAlertsVO.getStatus().equals("1"))
            pushAlertsVO.setStatus("1"); 
          pushAlertsVO.getVehicle().isEmpty();
        } 
      }  
    return paramArrayList;
  }
  
  private void fillVehicleList(Spinner paramSpinner, Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: checkcast com/roadtrack/onstar/onstarApplication
    //   6: astore #5
    //   8: aload_1
    //   9: astore #4
    //   11: aload_1
    //   12: ifnonnull -> 27
    //   15: getstatic com/roadtrack/onstar/NotificationsActivity.spinner_menu : Landroid/widget/Spinner;
    //   18: astore #4
    //   20: goto -> 27
    //   23: astore_1
    //   24: goto -> 171
    //   27: aload #4
    //   29: ifnonnull -> 35
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: new java/util/ArrayList
    //   38: astore_1
    //   39: aload_1
    //   40: invokespecial <init> : ()V
    //   43: iconst_0
    //   44: istore_3
    //   45: iload_3
    //   46: aload #5
    //   48: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   51: invokeinterface size : ()I
    //   56: if_icmpge -> 89
    //   59: aload_1
    //   60: aload #5
    //   62: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   65: iload_3
    //   66: invokeinterface get : (I)Ljava/lang/Object;
    //   71: checkcast com/roadtrack/onstar/VO/UserDevicesVO
    //   74: invokevirtual getName : ()Ljava/lang/String;
    //   77: invokeinterface add : (Ljava/lang/Object;)Z
    //   82: pop
    //   83: iinc #3, 1
    //   86: goto -> 45
    //   89: new com/roadtrack/onstar/adapter/VehiculeSpinnerAdapter
    //   92: astore #6
    //   94: aload #6
    //   96: aload_2
    //   97: ldc_w 2131427512
    //   100: ldc_w 2131297225
    //   103: ldc_w 2131297226
    //   106: getstatic com/roadtrack/onstar/NotificationsActivity.spinner_menu : Landroid/widget/Spinner;
    //   109: aload_1
    //   110: invokespecial <init> : (Landroid/content/Context;IIILandroid/widget/Spinner;Ljava/util/List;)V
    //   113: aload #6
    //   115: iconst_0
    //   116: invokevirtual setSpinnerType : (I)V
    //   119: aload #4
    //   121: aload #6
    //   123: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   126: aload #4
    //   128: aload_0
    //   129: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   132: aload_0
    //   133: getfield userPreference : Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   136: invokevirtual getUser : ()Ljava/lang/String;
    //   139: aload #5
    //   141: invokestatic getLastKnownVehicleSelected : (Landroid/content/Context;Ljava/lang/String;Lcom/roadtrack/onstar/onstarApplication;)I
    //   144: invokevirtual setSelection : (I)V
    //   147: aload_2
    //   148: invokestatic setDeviceType : (Landroid/content/Context;)V
    //   151: new com/roadtrack/onstar/NotificationsActivity$3
    //   154: astore_1
    //   155: aload_1
    //   156: aload_0
    //   157: aload #5
    //   159: invokespecial <init> : (Lcom/roadtrack/onstar/NotificationsActivity;Lcom/roadtrack/onstar/onstarApplication;)V
    //   162: aload #4
    //   164: aload_1
    //   165: invokevirtual setOnItemSelectedListener : (Landroid/widget/AdapterView$OnItemSelectedListener;)V
    //   168: goto -> 184
    //   171: getstatic com/roadtrack/onstar/NotificationsActivity.TAG : Ljava/lang/String;
    //   174: ldc_w 'Error: fillVehicleList'
    //   177: aload_1
    //   178: invokevirtual getMessage : ()Ljava/lang/String;
    //   181: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   184: aload_0
    //   185: monitorexit
    //   186: return
    //   187: astore_1
    //   188: aload_0
    //   189: monitorexit
    //   190: aload_1
    //   191: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	187	finally
    //   15	20	23	java/lang/Exception
    //   15	20	187	finally
    //   35	43	23	java/lang/Exception
    //   35	43	187	finally
    //   45	83	23	java/lang/Exception
    //   45	83	187	finally
    //   89	168	23	java/lang/Exception
    //   89	168	187	finally
    //   171	184	187	finally
  }
  
  private void followmefinisheddialog() {
    Utilities.escribeArchivo(TAG, "FOLLOWME", "FOLLOWME FINISHED: Mostrando dialogo");
    (new DialogEmpty((Activity)this, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_siguemeterminado_1, 2131689934), true)).show();
  }
  
  private boolean isAlertRecordShowCheck(PushAlertsVO paramPushAlertsVO) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #6
    //   3: iload #6
    //   5: istore_3
    //   6: aload_1
    //   7: ifnull -> 522
    //   10: aload_1
    //   11: invokevirtual getActionId : ()Ljava/lang/String;
    //   14: invokevirtual length : ()I
    //   17: ifne -> 26
    //   20: iload #6
    //   22: istore_3
    //   23: goto -> 522
    //   26: aload_1
    //   27: invokevirtual isAction : ()Z
    //   30: ifeq -> 47
    //   33: aload_1
    //   34: invokevirtual getActionId : ()Ljava/lang/String;
    //   37: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   40: invokevirtual intValue : ()I
    //   43: istore_2
    //   44: goto -> 101
    //   47: aload_1
    //   48: invokevirtual getActionId : ()Ljava/lang/String;
    //   51: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   54: invokevirtual name : ()Ljava/lang/String;
    //   57: invokevirtual equals : (Ljava/lang/Object;)Z
    //   60: ifeq -> 73
    //   63: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   66: invokevirtual GetCode : ()I
    //   69: istore_2
    //   70: goto -> 101
    //   73: aload_1
    //   74: invokevirtual getActionId : ()Ljava/lang/String;
    //   77: getstatic com/roadtrack/onstar/Enums$Services.FollowMe : Lcom/roadtrack/onstar/Enums$Services;
    //   80: invokevirtual name : ()Ljava/lang/String;
    //   83: invokevirtual equals : (Ljava/lang/Object;)Z
    //   86: ifeq -> 99
    //   89: getstatic com/roadtrack/onstar/Enums$Services.FollowMe : Lcom/roadtrack/onstar/Enums$Services;
    //   92: invokevirtual GetCode : ()I
    //   95: istore_2
    //   96: goto -> 101
    //   99: iconst_0
    //   100: istore_2
    //   101: iload_2
    //   102: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   105: invokevirtual GetCode : ()I
    //   108: if_icmpne -> 119
    //   111: aload_1
    //   112: invokevirtual isAction : ()Z
    //   115: istore_3
    //   116: goto -> 141
    //   119: iload_2
    //   120: getstatic com/roadtrack/onstar/Enums$Services.FollowMe : Lcom/roadtrack/onstar/Enums$Services;
    //   123: invokevirtual GetCode : ()I
    //   126: if_icmpne -> 139
    //   129: aload_1
    //   130: invokevirtual isAction : ()Z
    //   133: iconst_1
    //   134: ixor
    //   135: istore_3
    //   136: goto -> 141
    //   139: iconst_0
    //   140: istore_3
    //   141: iload_3
    //   142: istore #4
    //   144: aload_1
    //   145: invokevirtual getActionId : ()Ljava/lang/String;
    //   148: ldc_w '1'
    //   151: invokevirtual equals : (Ljava/lang/Object;)Z
    //   154: ifeq -> 192
    //   157: iload_3
    //   158: istore #4
    //   160: aload_1
    //   161: invokevirtual getLatitude : ()Ljava/lang/String;
    //   164: ldc_w '0'
    //   167: invokevirtual equals : (Ljava/lang/Object;)Z
    //   170: ifeq -> 192
    //   173: iload_3
    //   174: istore #4
    //   176: aload_1
    //   177: invokevirtual getLongitude : ()Ljava/lang/String;
    //   180: ldc_w '0'
    //   183: invokevirtual equals : (Ljava/lang/Object;)Z
    //   186: ifeq -> 192
    //   189: iconst_0
    //   190: istore #4
    //   192: aload_1
    //   193: invokevirtual getAcc : ()Ljava/lang/String;
    //   196: ldc_w '12'
    //   199: invokevirtual equals : (Ljava/lang/Object;)Z
    //   202: ifne -> 233
    //   205: iload #4
    //   207: istore #5
    //   209: aload_1
    //   210: invokevirtual getAcc : ()Ljava/lang/String;
    //   213: ldc_w '6'
    //   216: invokevirtual equals : (Ljava/lang/Object;)Z
    //   219: ifeq -> 236
    //   222: iload #4
    //   224: istore #5
    //   226: aload_1
    //   227: invokevirtual isAction : ()Z
    //   230: ifne -> 236
    //   233: iconst_1
    //   234: istore #5
    //   236: iload #5
    //   238: istore_3
    //   239: aload_1
    //   240: invokevirtual isAction : ()Z
    //   243: ifeq -> 267
    //   246: iload #5
    //   248: istore_3
    //   249: aload_1
    //   250: invokevirtual getActionId : ()Ljava/lang/String;
    //   253: getstatic com/roadtrack/onstar/Enums$Services.FollowMe : Lcom/roadtrack/onstar/Enums$Services;
    //   256: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   259: invokevirtual equals : (Ljava/lang/Object;)Z
    //   262: ifeq -> 267
    //   265: iconst_1
    //   266: istore_3
    //   267: aload_1
    //   268: invokevirtual getAlert : ()Ljava/lang/String;
    //   271: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   274: astore #11
    //   276: aload_0
    //   277: aload_0
    //   278: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   281: getfield global_lbl_accionstatusproceso_1 : Ljava/lang/String;
    //   284: ldc_w 2131689902
    //   287: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   290: astore #12
    //   292: aload_0
    //   293: aload_0
    //   294: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   297: getfield global_lbl_accionstatusnoactivada_1 : Ljava/lang/String;
    //   300: ldc_w 2131689898
    //   303: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   306: astore #13
    //   308: aload_0
    //   309: aload_0
    //   310: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   313: getfield global_lbl_accionstatusnoactivado_1 : Ljava/lang/String;
    //   316: ldc_w 2131689899
    //   319: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   322: astore #10
    //   324: aload_0
    //   325: aload_0
    //   326: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   329: getfield serviceGetRequestTimeOut : Ljava/lang/String;
    //   332: ldc_w 2131690364
    //   335: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   338: astore #8
    //   340: aload_0
    //   341: aload_0
    //   342: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   345: getfield global_lbl_accionstatusnoejecutado_1 : Ljava/lang/String;
    //   348: ldc_w 2131689900
    //   351: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   354: astore #7
    //   356: aload_0
    //   357: aload_0
    //   358: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   361: getfield global_lbl_accionstatusposiblenotificacion_1 : Ljava/lang/String;
    //   364: ldc_w 2131689901
    //   367: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   370: astore #9
    //   372: iload_3
    //   373: istore #4
    //   375: aload_1
    //   376: invokevirtual isAction : ()Z
    //   379: ifeq -> 466
    //   382: aload #11
    //   384: aload #12
    //   386: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   389: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   392: ifne -> 463
    //   395: aload #11
    //   397: aload #13
    //   399: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   402: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   405: ifne -> 463
    //   408: aload #11
    //   410: aload #10
    //   412: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   415: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   418: ifne -> 463
    //   421: aload #11
    //   423: aload #8
    //   425: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   428: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   431: ifne -> 463
    //   434: aload #11
    //   436: aload #7
    //   438: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   441: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   444: ifne -> 463
    //   447: iload_3
    //   448: istore #4
    //   450: aload #11
    //   452: aload #9
    //   454: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   457: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   460: ifeq -> 466
    //   463: iconst_0
    //   464: istore #4
    //   466: iload #4
    //   468: istore_3
    //   469: aload #11
    //   471: aload_0
    //   472: aload_0
    //   473: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   476: getfield global_lbl_accionstatussiguemefinalizado_1 : Ljava/lang/String;
    //   479: ldc_w 2131689905
    //   482: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   485: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   488: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   491: ifeq -> 496
    //   494: iconst_0
    //   495: istore_3
    //   496: aload_1
    //   497: invokevirtual getAlertCodeId : ()I
    //   500: sipush #208
    //   503: if_icmpne -> 508
    //   506: iconst_0
    //   507: istore_3
    //   508: aload_1
    //   509: invokevirtual getAlertCodeId : ()I
    //   512: iconst_m1
    //   513: if_icmpne -> 522
    //   516: iload #6
    //   518: istore_3
    //   519: goto -> 522
    //   522: iload_3
    //   523: ireturn
  }
  
  private boolean isMapACtive(PushAlertsVO paramPushAlertsVO, CheckBox paramCheckBox) {
    paramCheckBox.isChecked();
    if (paramPushAlertsVO != null)
      try {
        String str1 = paramPushAlertsVO.getActionId();
        String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionalertavel_1, 2131689851);
        String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.alert_code_id_follow_me, 2131689662);
        String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionalertamov_1, 2131689845);
        if ("valet".equalsIgnoreCase(str1) || str3.equalsIgnoreCase(str1)) {
          Enums$Services.valet.GetCode();
          return true;
        } 
        if ("followme".equalsIgnoreCase(str1) || str2.equalsIgnoreCase(str1)) {
          Enums$Services.FollowMe.GetCode();
          return true;
        } 
        if ("movimento".equalsIgnoreCase(str1) || str4.equalsIgnoreCase(str1)) {
          Enums$Services.Parking.GetCode();
          return true;
        } 
        if ("none".equalsIgnoreCase(str1) || str3.equalsIgnoreCase(str1)) {
          Enums$Services.None.GetCode();
          return true;
        } 
        if ("Velocidade".equalsIgnoreCase(str1) || str3.equalsIgnoreCase(str1)) {
          Enums$Services.Speed.GetCode();
          return true;
        } 
        Integer.parseInt(str1);
      } catch (Exception exception) {} 
    return true;
  }
  
  private void loadNotifications() {
    this.objectAlert = (ArrayList<PushAlertsVO>)this.dbFunctions.getAlertList(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId(), true);
    ArrayList<PushAlertsVO> arrayList = new ArrayList();
    DBFunctions dBFunctions = new DBFunctions((Context)this);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    if (dBFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), userDevicesVO.getDeviceId(), "", true)) {
      Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_lbl_banner_vehiculorobado_1, 2131690066);
      MainActivity.Showbanner = true;
      startActivity(new Intent(getApplicationContext(), MainActivity.class));
    } else {
      dBFunctions = null;
      new ArrayList();
      List list = this.dbFunctions.getHistoricalList(GlobalMembers.userLogged, Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId());
      if (list != null && list.size() != 0)
        for (Historical historical : list) {
          String str1;
          try {
            String str = GlobalMembers.pushVOsdf.format(GlobalMembers.historicalsdf.parse(historical.getDateTime()));
            str1 = str;
          } catch (ParseException parseException) {
            Utilities.escribeArchivo(TAG, "Error: loadNotificioatns", parseException.getMessage());
          } 
          String str2 = (new BRInfo(getApplicationContext())).getActionName(historical);
          arrayList.add(new PushAlertsVO(historical.getGPSStatus(), str2, "", str1, historical.getLatitud(), historical.getLongitud(), historical.getIdAction(), historical.getId(), "", "", "", true, historical.getCompletion_code(), historical.getRequestErroId(), historical.getResponseErrorId(), historical.getMessageResponseId(), 0, historical.getDateTimeExecution()));
        }  
      ArrayList<PushAlertsVO> arrayList1 = this.objectAlert;
      if (arrayList1 != null) {
        fillEmptyFollowMePoints(arrayList1);
        for (byte b = 0; b < this.objectAlert.size(); b++) {
          if ((((PushAlertsVO)this.objectAlert.get(b)).getAcc().equals("6") || ((PushAlertsVO)this.objectAlert.get(b)).getAcc().equals("10") || ((PushAlertsVO)this.objectAlert.get(b)).getAcc().equals("12")) && (!((PushAlertsVO)this.objectAlert.get(b)).getActionId().equals("FollowMe") || !((PushAlertsVO)this.objectAlert.get(b)).getInsertedRow().equals("1") || ((PushAlertsVO)this.objectAlert.get(b)).getAlert().equals("FollowMeEnd"))) {
            BRInfo bRInfo = new BRInfo(this.context);
            ((PushAlertsVO)this.objectAlert.get(b)).setAlert(bRInfo.getActionName(this.objectAlert.get(b)));
            arrayList.add(this.objectAlert.get(b));
          } 
        } 
      } 
      sort(arrayList);
      if (arrayList.size() > 50)
        for (int i = arrayList.size() - 1; i >= 50; i--)
          arrayList.remove(i);  
      this.adapter = new CustomAdapter((Activity)this, arrayList);
      this.lvData.setAdapter((ListAdapter)this.adapter);
      this.dbFunctions.readerNotificationAlerts(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId());
      this.dbFunctions.readerNotificationAccions(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId());
      this.dbFunctions.readerNotificationDTC(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId());
      this.dbFunctions.readerNotificationAgendamiento(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId());
    } 
  }
  
  private void noRepeatSameActionDialog() {
    try {
      String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
      String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
      String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
      this.dialogMenu = DialogMenu.newInstance();
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, 2131165484);
      this.dialogMenu.setPreferenceDialogFragment(this, (Context)this, true, drawable, str2, str3, true, str1, false, "0");
      this.dialogMenu.show(getSupportFragmentManager(), null);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: noRepeatSameActionDIalog", exception.getMessage());
    } 
  }
  
  @SuppressLint({"NewApi"})
  private void reloadPageView() {
    // Byte code:
    //   0: aload_0
    //   1: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   4: getstatic com/roadtrack/onstar/NotificationsActivity.TAG : Ljava/lang/String;
    //   7: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   10: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   13: putstatic com/roadtrack/onstar/BO/GlobalMembers.followMeProcessDeviceId : Ljava/lang/String;
    //   16: aload_0
    //   17: getfield dbFunctions : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   20: ldc ''
    //   22: aload_0
    //   23: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   26: getstatic com/roadtrack/onstar/NotificationsActivity.TAG : Ljava/lang/String;
    //   29: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   32: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   35: invokevirtual getAllFollowMe : (Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
    //   38: astore #4
    //   40: new java/lang/String
    //   43: dup
    //   44: invokespecial <init> : ()V
    //   47: pop
    //   48: getstatic com/roadtrack/onstar/MainActivity.lastFollowMeIdMessage : Ljava/lang/String;
    //   51: astore_3
    //   52: aload_3
    //   53: ifnull -> 65
    //   56: aload_3
    //   57: astore_2
    //   58: aload_3
    //   59: invokevirtual length : ()I
    //   62: ifne -> 73
    //   65: aload_0
    //   66: getstatic com/roadtrack/onstar/BO/GlobalMembers.followMeProcessDeviceId : Ljava/lang/String;
    //   69: invokestatic getLastFollowMeMessageId : (Landroid/app/Activity;Ljava/lang/String;)Ljava/lang/String;
    //   72: astore_2
    //   73: aload_0
    //   74: invokestatic getLastFollowMeDeviceId : (Landroid/content/Context;)Ljava/lang/String;
    //   77: putstatic com/roadtrack/onstar/BO/GlobalMembers.followMeProcessDeviceId : Ljava/lang/String;
    //   80: iconst_m1
    //   81: putstatic com/roadtrack/onstar/BO/GlobalMembers.ifollowMeType : I
    //   84: getstatic com/roadtrack/onstar/BO/GlobalMembers.followMeProcessDeviceId : Ljava/lang/String;
    //   87: astore_3
    //   88: aload_3
    //   89: ifnull -> 116
    //   92: aload_3
    //   93: invokevirtual isEmpty : ()Z
    //   96: ifne -> 116
    //   99: aload_0
    //   100: getfield dbFunctions : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   103: getstatic com/roadtrack/onstar/BO/GlobalMembers.followMeProcessDeviceId : Ljava/lang/String;
    //   106: aload_0
    //   107: getfield context : Landroid/content/Context;
    //   110: invokevirtual mustStartFollowMe : (Ljava/lang/String;Landroid/content/Context;)I
    //   113: putstatic com/roadtrack/onstar/BO/GlobalMembers.ifollowMeType : I
    //   116: aload_0
    //   117: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   120: iconst_0
    //   121: invokestatic validateNetwork : (Landroid/content/Context;Z)Z
    //   124: istore_1
    //   125: aload #4
    //   127: ifnull -> 205
    //   130: aload #4
    //   132: invokeinterface size : ()I
    //   137: bipush #8
    //   139: if_icmpge -> 205
    //   142: aload_2
    //   143: ldc_w '0'
    //   146: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   149: ifne -> 205
    //   152: getstatic com/roadtrack/onstar/BO/GlobalMembers.ifollowMeType : I
    //   155: iconst_1
    //   156: if_icmpeq -> 205
    //   159: iload_1
    //   160: ifeq -> 205
    //   163: new com/roadtrack/onstar/utils/AsyncTaskGetLastIncomingMessageHistory
    //   166: dup
    //   167: aload_0
    //   168: invokespecial <init> : (Landroid/content/Context;)V
    //   171: getstatic android/os/AsyncTask.THREAD_POOL_EXECUTOR : Ljava/util/concurrent/Executor;
    //   174: iconst_2
    //   175: anewarray java/lang/String
    //   178: dup
    //   179: iconst_0
    //   180: aload_2
    //   181: aastore
    //   182: dup
    //   183: iconst_1
    //   184: aload_0
    //   185: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   188: getstatic com/roadtrack/onstar/NotificationsActivity.TAG : Ljava/lang/String;
    //   191: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   194: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   197: aastore
    //   198: invokevirtual executeOnExecutor : (Ljava/util/concurrent/Executor;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   201: pop
    //   202: goto -> 256
    //   205: new com/roadtrack/onstar/DAO/DBFunctions
    //   208: dup
    //   209: aload_0
    //   210: getfield context : Landroid/content/Context;
    //   213: invokespecial <init> : (Landroid/content/Context;)V
    //   216: aload_0
    //   217: getfield context : Landroid/content/Context;
    //   220: aload_0
    //   221: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   224: getstatic com/roadtrack/onstar/NotificationsActivity.TAG : Ljava/lang/String;
    //   227: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   230: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   233: invokevirtual executeNotificationFollowMeSynch : (Landroid/content/Context;Ljava/lang/String;)Z
    //   236: pop
    //   237: aload_0
    //   238: getfield lvData : Landroid/widget/ListView;
    //   241: aconst_null
    //   242: invokevirtual setAdapter : (Landroid/widget/ListAdapter;)V
    //   245: aload_0
    //   246: invokespecial loadNotifications : ()V
    //   249: aload_0
    //   250: getfield adapter : Lcom/roadtrack/onstar/NotificationsActivity$CustomAdapter;
    //   253: invokevirtual notifyDataSetChanged : ()V
    //   256: return
  }
  
  private static void setDeviceType(Context paramContext) {
    String str = Utilities.getLastKnownDeviceSelected((onstarApplication)paramContext, TAG).getDeviceTypeId();
    if (!str.equals("")) {
      GlobalMembers.deviceType = str;
    } else {
      GlobalMembers.deviceType = GlobalMembers.deviceTypeP7;
    } 
  }
  
  private void setVehicleSelected() {
    UserDevicesVO userDevicesVO1 = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    UserDevicesVO userDevicesVO2 = this.rtApp.getmDeviceUserList().get(spinner_menu.getSelectedItemPosition());
    this.dbFunctions.addVehicleSelected(getApplicationContext(), this.userPreference.getUser(), userDevicesVO2);
    if (!userDevicesVO2.equals(userDevicesVO1)) {
      Utilities.updateVehicleSelected(getApplicationContext(), this.userPreference.getUser(), userDevicesVO2);
      int i = Utilities.getLastKnownVehicleSelected(getApplicationContext(), this.dbFunctions.getUserPreference(GlobalMembers.userLogged).getUser(), this.rtApp);
      spinner_menu.setSelection(i);
      userDevicesVO1 = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
      this.dbFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), userDevicesVO1.getDeviceId(), "", true);
    } 
  }
  
  public static void sort(List<PushAlertsVO> paramList) {
    if (paramList != null)
      Collections.sort(paramList, new Comparator<PushAlertsVO>() {
            public int compare(PushAlertsVO param1PushAlertsVO1, PushAlertsVO param1PushAlertsVO2) {
              boolean bool;
              try {
                Date date = GlobalMembers.pushVOsdf.parse(param1PushAlertsVO1.date);
                bool = GlobalMembers.pushVOsdf.parse(param1PushAlertsVO2.date).compareTo(date);
              } catch (Exception exception) {
                Utilities.escribeArchivo(NotificationsActivity.TAG, "Error: sort list notifications", exception.getMessage());
                bool = false;
              } 
              return bool;
            }
          }); 
  }
  
  private void startingMap(double paramDouble1, double paramDouble2, String paramString, boolean paramBoolean, PushAlertsVO paramPushAlertsVO) {
    String str;
    if (paramDouble1 == 0.0D || paramDouble2 == 0.0D) {
      paramString = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.coordinatesNotValid, 2131689738);
      Toast.makeText(this.context, paramString, 1).show();
      return;
    } 
    Bundle bundle2 = new Bundle();
    GoogleMapVO googleMapVO = new GoogleMapVO();
    if (paramBoolean) {
      bundle2.putSerializable("source", Enums$navigationProcess.Mixed);
      googleMapVO.setKEY_SET_ENGINE_SOURCE(Enums$navigationProcess.Mixed);
    } else {
      String str1;
      paramPushAlertsVO.getDate();
      String str3 = new String();
      String str2 = paramString.replace("\n", " ");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(paramPushAlertsVO.getStatus());
      if (stringBuilder.toString().equals("3")) {
        Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.previousposition, 2131690271);
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.navigation_lbl_localizame_gpssincobertura_1, 2131690158);
      } else {
        Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.currentposition, 2131689742);
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
      } 
      String str4 = paramPushAlertsVO.getDate_execution();
      if ((paramPushAlertsVO.getAcc().equalsIgnoreCase("6") && paramPushAlertsVO.getAlertCodeId() == 27) || paramPushAlertsVO.getAlertCodeId() == 10) {
        str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.alert_code_id_follow_me, 2131689662);
      } else if (paramPushAlertsVO.getAcc().equalsIgnoreCase("6") && paramPushAlertsVO.getAlertCodeId() == 15) {
        str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionalertavel_1, 2131689851);
      } else if ((paramPushAlertsVO.getAcc().equalsIgnoreCase("6") && paramPushAlertsVO.getAlertCodeId() == 57) || paramPushAlertsVO.getAlertCodeId() == 225) {
        str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionalertamov_1, 2131689845);
      } else {
        str = str3;
        if (paramPushAlertsVO.getAcc().equalsIgnoreCase("6")) {
          str = str3;
          if (paramPushAlertsVO.getAlertCodeId() == 235)
            str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionalertavalet_1, 2131689849); 
        } 
      } 
      googleMapVO.setKEY_SET_ENGINE_SOURCE(Enums$navigationProcess.External);
      googleMapVO.setKEY_SET_NAV_PARAMS("");
      googleMapVO.setLASTKNOWDATE(str4);
      googleMapVO.setKEY_GPSSTATUS(str1);
      googleMapVO.setALERT_CODE_ID(str);
      str = str2;
    } 
    googleMapVO.setKEY_SET_NAV_ACTION(str);
    googleMapVO.setKEY_EXTERNAL_LAT(String.valueOf(paramDouble1));
    googleMapVO.setKEY_EXTERNAL_LNG(String.valueOf(paramDouble2));
    googleMapVO.setPREVIOUS_ACTIVITY(NotificationsActivity.class.getName());
    Intent intent = new Intent((Context)this, ActivityMapViewerG.class);
    Bundle bundle1 = new Bundle();
    bundle1.putSerializable("googleObject", (Serializable)googleMapVO);
    intent.putExtras(bundle1);
    startActivity(intent);
  }
  
  private void validProcessDownload() {}
  
  public void acceptButtonListener(String paramString) {
    this.dialogMenu.dismiss();
  }
  
  public void cancelButtonListener(String paramString) {
    this.dialogMenu.dismiss();
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    if (paramMotionEvent.getAction() == 0) {
      Intent intent = new Intent();
      intent.setAction("GlobalTouchService");
      intent.putExtra("ACTION_EXTRA", "usuario_activo");
      sendBroadcast(intent);
    } 
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  public void finish() {
    Intent intent = new Intent();
    intent.putExtra(this.KEY_SPINNER_POSITION, Utilities.getLastKnownVehicleSelected(getApplicationContext(), this.dbFunctions.getUserPreference(GlobalMembers.userLogged).getUser(), this.rtApp));
    if (this.exitApp)
      intent.putExtra(this.KEY_EXIT, "EXIT"); 
    setResult(-1, intent);
    super.finish();
  }
  
  public void onAlertClick() {}
  
  public void onBackMenuListener(String paramString) {
    this.dialogMenu.dismiss();
  }
  
  public void onCancelListener() {}
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.stringsResourcesVO = new StringsResourcesVO();
    Utilities.escribeArchivo(TAG, "OnCreate------------------------------>", "OnCreate");
    GlobalMembers.notificaciones = 0;
    GlobalMembers.totalNotificaciones = 0;
    GlobalMembers.notificacionesDTC = 0;
    GlobalMembers.notificacionesAgendamiento = 0;
    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new DefaultExceptionHandler(getApplicationContext(), (Activity)this));
    this.followmefinish = new IntentFilter();
    this.followmefinish.addAction("SHOWDIALOGFOLLOWMEFINISH");
    this.dbFunctions = new DBFunctions(getApplicationContext());
    this.rtApp = (onstarApplication)getApplicationContext();
    this.userPreference = new UserPreferenceVO();
    this.userPreference = this.dbFunctions.getUserPreference(GlobalMembers.userLogged);
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue() && !MainActivity.onHornLights.booleanValue() && !MainActivity.onCloseDoors.booleanValue() && !MainActivity.onOpenDoors.booleanValue() && !MainActivity.onDisarmPINCODE.booleanValue() && !MainActivity.onAlertParking.booleanValue() && !MainActivity.onAlertSpeed.booleanValue() && !MainActivity.onAlertValet.booleanValue() && !MainActivity.onNotification.booleanValue() && !MainActivity.onHorn.booleanValue() && !GlobalMembers.onfollowmeActivated) {
        int i = Utilities.getDevicePositionByDeviceId(bundle.getString("PUSH_NOTIFICATION_DEVICE_ID"));
        if (i != -1) {
          UserPreferenceVO userPreferenceVO = this.dbFunctions.getUserPreference(GlobalMembers.userLogged);
          UserDevicesVO userDevicesVO = this.rtApp.getmDeviceUserList().get(i);
          this.dbFunctions.addVehicleSelected(getApplicationContext(), this.userPreference.getUser(), userDevicesVO);
          Utilities.updateVehicleSelected((Context)this, userPreferenceVO.getUser(), userDevicesVO);
        } 
      } else {
        int i = Utilities.getDevicePositionByDeviceId(bundle.getString("PUSH_NOTIFICATION_DEVICE_ID"));
        if (i != -1 && !Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).equals(this.rtApp.getmDeviceUserList().get(i)))
          noRepeatSameActionDialog(); 
      } 
      bundle.getString("previousActivity");
    } 
    this.tf = onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisRegular);
    setContentView(2131427469);
    if (this.userPreference.getCountry() != null) {
      Utilities.getPrefixFromCountry(this.userPreference.getCountry(), (Context)this);
    } else {
      Utilities.getPrefixFromCountry("BR", (Context)this);
    } 
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_notificaciones_1, 2131689930);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.notificaciones_main_lbl_titulofecha_7, 2131690214);
    this.Alertas = (TextView)findViewById(2131296261);
    this.Alertas.setTypeface(this.tf);
    this.Fechas = (TextView)findViewById(2131296275);
    this.Fechas.setTypeface(this.tf);
    this.Fechas.setText(str2);
    this.ActionValueMessage = (TextView)findViewById(2131296257);
    this.tvDummy = (TextView)findViewById(2131297147);
    this.tvDummy.setText(str1);
    this.ActionValueMessage.setText(str1);
    this.ActionValueMessage.setTypeface(this.tf);
    this.imgTitle = (ImageView)findViewById(2131296619);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.title_notific, 2131165694);
    this.imgTitle.setImageDrawable(drawable);
    this.lvData = (ListView)findViewById(2131296829);
    registerReceiver(this.networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    PreferenceRT.SetValuePreference(Enums$SettingsPreference.statusNotifications, Boolean.valueOf(false), GlobalMembers.contexGlobal);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.rtApp.getDeviceTypeId());
    stringBuilder.append("_ID");
    PreferenceRT.SetValuePreference(stringBuilder.toString(), false, (Context)this);
    this.swipeLayout = (SwipeRefreshLayout)findViewById(2131297063);
    this.swipeLayout.setOnRefreshListener(this);
    this.swipeLayout.setColorSchemeResources(new int[] { 17170459, 17170452, 17170456, 17170454 });
    this.dbFunctions.IsShowDialogBefore((Context)this);
  }
  
  @SuppressLint({"NewApi"})
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    getMenuInflater().inflate(2131492873, paramMenu);
    ActionBar actionBar = getActionBar();
    actionBar.setCustomView(2131427356);
    RelativeLayout relativeLayout = (RelativeLayout)actionBar.getCustomView();
    actionBar.setDisplayOptions(18);
    actionBar.setDisplayShowHomeEnabled(false);
    actionBar.setDisplayShowTitleEnabled(false);
    spinner_menu = (Spinner)relativeLayout.getChildAt(0);
    fillVehicleList(spinner_menu, getApplicationContext());
    setVehicleSelected();
    Intent intent = getIntent();
    if (intent != null && intent.hasExtra("EXE_NOTIF_PROCESS") && intent.getBooleanExtra("EXE_NOTIF_PROCESS", false))
      onRefresh(); 
    spinner_menu.setOnTouchListener(new View.OnTouchListener() {
          final NotificationsActivity this$0;
          
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            boolean bool;
            if (MainActivity.onOpenDoors.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onFindMe.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onPID.booleanValue() || MainActivity.onDTC.booleanValue() || GlobalMembers.onfollowmeActivated) {
              bool = true;
            } else {
              bool = false;
            } 
            if (param1MotionEvent.getAction() == 0 && bool)
              NotificationsActivity.this.noRepeatSameActionDialog(); 
            return bool;
          }
        });
    return true;
  }
  
  protected void onDestroy() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.rtApp.getDeviceTypeId());
    stringBuilder.append("_ID");
    PreferenceRT.SetValuePreference(stringBuilder.toString(), false, (Context)this);
    GlobalMembers.notificaciones = 0;
    GlobalMembers.totalNotificaciones = 0;
    GlobalMembers.notificacionesDTC = 0;
    GlobalMembers.notificacionesAgendamiento = 0;
    MainActivity.onNotification = Boolean.valueOf(false);
    super.onDestroy();
    BroadcastReceiver broadcastReceiver = this.networkChangeReceiver;
    if (broadcastReceiver != null)
      unregisterReceiver(broadcastReceiver); 
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    Intent intent;
    switch (paramMenuItem.getItemId()) {
      default:
        return super.onOptionsItemSelected(paramMenuItem);
      case 2131296913:
        startActivity(new Intent((Context)this, SettingsNewActivity.class));
        return true;
      case 2131296910:
        this.exitApp = true;
        finish();
        intent = new Intent((Context)this, MainActivity.class);
        intent.addFlags(67108864);
        intent.putExtra("exitApp", this.exitApp);
        startActivity(intent);
        return true;
      case 2131296909:
        startActivity(new Intent((Context)this, HistoricalTestActivity.class));
        return true;
      case 2131296907:
        startActivity(new Intent((Context)this, NotificationsActivity.class));
        return true;
      case 16908332:
        break;
    } 
    Toast.makeText((Context)this.rtApp, "TEST", 0).show();
    return true;
  }
  
  protected void onPause() {
    super.onPause();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.rtApp.getDeviceTypeId());
    stringBuilder.append("_ID");
    PreferenceRT.SetValuePreference(stringBuilder.toString(), false, (Context)this);
    GlobalMembers.notificaciones = 0;
    GlobalMembers.totalNotificaciones = 0;
    GlobalMembers.notificacionesDTC = 0;
    GlobalMembers.notificacionesAgendamiento = 0;
    unregisterReceiver(this.followmefinishmyReceiver);
  }
  
  public void onPositiveClick(View paramView) {
    if (NetUtilities.validateNetwork(getApplicationContext(), false)) {
      NetUtilities.NetServiceResponseEnum netServiceResponseEnum = NetUtilities.isThereNETService(NetUtilities.NetServiceRequestEnum.CHECKNETSERVICE, getApplicationContext());
      if (netServiceResponseEnum != null) {
        if (netServiceResponseEnum.equals(NetUtilities.NetServiceResponseEnum.SERVICEWIFIOK)) {
          validProcessDownload();
        } else {
          if (netServiceResponseEnum.equals(NetUtilities.NetServiceResponseEnum.SERVICEMOBILEOK)) {
            createMobileAlertDialog();
            return;
          } 
          if (netServiceResponseEnum.equals(NetUtilities.NetServiceResponseEnum.THERESNOSERVICE)) {
            String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_no_network_available, 2131690058);
            Toast.makeText((Context)this.rtApp, str, 1).show();
            createLaterAlertDialog();
            return;
          } 
        } 
      } else {
        return;
      } 
    } else {
      String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.login_global_popup_lbl_sindatos_2, 2131690012);
      Toast.makeText((Context)this.rtApp, str, 1).show();
    } 
  }
  
  public void onRefresh() {
    if (!NetUtilities.validateNetwork((Context)this, true)) {
      this.swipeLayout.setRefreshing(false);
      OrientationManager.unlockOrientation(this.act);
      spinner_menu.setClickable(true);
    } else {
      executeNotificationProcess();
    } 
  }
  
  public void onResume() {
    super.onResume();
    Utilities.escribeArchivo(TAG, "onResume------------------------------>", "onResume");
    registerReceiver(this.followmefinishmyReceiver, this.followmefinish);
    new String();
    String str = MainActivity.lastFollowMeIdMessage;
    if (str == null || str.length() == 0)
      Utilities.getLastFollowMeMessageId((Activity)this, this.deviceId); 
    this.context = getApplicationContext();
    GlobalMembers.followMeProcessDeviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId().toString();
    GlobalMembers.ifollowMeType = this.dbFunctions.mustStartFollowMe(GlobalMembers.followMeProcessDeviceId, this.context);
    loadNotifications();
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
  }
  
  private class Actions extends AsyncTask<String, String, String> {
    private String actionId;
    
    String aux_result = "";
    
    private String messageId = "0";
    
    private boolean processFinished;
    
    private String responseNotifications = "";
    
    final NotificationsActivity this$0;
    
    private Actions() {}
    
    private void GetNotifications(String param1String1, int param1Int1, int param1Int2, int param1Int3, String param1String2, String param1String3, String param1String4, String param1String5, LinkedHashMap<String, Object> param1LinkedHashMap) {
      Date date = new Date();
      boolean bool = true;
      do {
        String str = WS(param1String2, param1String3, param1String4, param1String5, param1Int1, param1LinkedHashMap);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("action: ");
        stringBuilder.append(this.actionId);
        stringBuilder.append(" result: ");
        stringBuilder.append(str);
        Utilities.escribeArchivo("ACTIONS", "WCF", stringBuilder.toString());
        boolean bool1 = bool;
        if (str != null) {
          bool1 = bool;
          if (str.length() > 0) {
            bool1 = bool;
            if (str.contains("*")) {
              bool1 = bool;
              if (!this.processFinished) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("15|");
                stringBuilder.append(NotificationsActivity.this.deviceId);
                stringBuilder.append("|");
                stringBuilder.append(str.substring(NotificationsActivity.this.deviceId.length() + 1, str.length()).replace("*", ";").replace("|", ","));
                this.responseNotifications = stringBuilder.toString();
                this.processFinished = true;
                bool1 = false;
              } 
            } 
          } 
        } 
        if (str != null && str.length() > 0 && str.equals("0|")) {
          this.responseNotifications = "";
          this.processFinished = true;
          bool1 = false;
        } 
        if (!this.processFinished) {
          long l = param1Int2;
          try {
            Thread.sleep(l);
          } catch (InterruptedException interruptedException) {
            Utilities.escribeArchivo(NotificationsActivity.TAG, "Error: getNotifications", interruptedException.getMessage());
          } 
        } 
        if (getTimeDifference(null, date, 13) < param1Int3 && bool1) {
          bool = true;
        } else {
          bool = false;
        } 
      } while (bool && !this.processFinished);
    }
    
    private String WS(String param1String1, String param1String2, String param1String3, String param1String4, int param1Int, LinkedHashMap<String, Object> param1LinkedHashMap) {
      String str;
      try {
        SoapRequestObject soapRequestObject = new SoapRequestObject();
        this(param1LinkedHashMap, param1String4, param1String3, param1String2, param1String1, param1Int, param1Int);
        soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
        RequestManager requestManager = new RequestManager();
        this(NotificationsActivity.this.getApplicationContext(), soapRequestObject);
        RequestManager.OnPostExecuteListener onPostExecuteListener = new RequestManager.OnPostExecuteListener() {
            final NotificationsActivity.Actions this$1;
            
            public void onPostExecuteListener(String param2String, int param2Int) {
              String str1 = NotificationsActivity.TAG;
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("response: ");
              stringBuilder.append(param2String);
              String str2 = stringBuilder.toString();
              stringBuilder = new StringBuilder();
              stringBuilder.append("responseCode: ");
              stringBuilder.append(param2Int);
              Utilities.escribeArchivo(str1, str2, stringBuilder.toString());
              NotificationsActivity.Actions.this.aux_result = param2String;
            }
          };
        super(this);
        requestManager.setOnPostExecuteListener(onPostExecuteListener);
        requestManager.sendRequest(1);
        str = this.aux_result;
      } catch (Exception exception) {
        Utilities.escribeArchivo(NotificationsActivity.TAG, "Error: WS", exception.toString());
        str = "";
      } 
      return str;
    }
    
    private long getTimeDifference(Date param1Date1, Date param1Date2, int param1Int) {
      Date date = param1Date1;
      if (param1Date1 == null)
        date = new Date(); 
      param1Date1 = new Date(date.getTime() - param1Date2.getTime());
      long l1 = param1Date1.getTime() / 1000L / 60L / 60L;
      long l3 = param1Date1.getTime() / 1000L / 60L;
      long l2 = param1Date1.getTime() / 1000L;
      return (param1Int == 11) ? l1 : ((param1Int == 12) ? l3 : l2);
    }
    
    protected String doInBackground(String... param1VarArgs) {
      NotificationsActivity notificationsActivity = NotificationsActivity.this;
      Integer integer = Integer.valueOf(0);
      notificationsActivity.sessionKey = param1VarArgs[0].toString();
      NotificationsActivity.this.login = param1VarArgs[1].toString();
      NotificationsActivity.this.password = param1VarArgs[2].toString();
      String str4 = param1VarArgs[3].toString();
      String str2 = param1VarArgs[4].toString();
      String str3 = param1VarArgs[5].toString();
      NotificationsActivity.this.csvParams = param1VarArgs[6].toString();
      NotificationsActivity.this.userId = param1VarArgs[7].toString();
      NotificationsActivity.this.applicationSourceId = param1VarArgs[8].toString();
      NotificationsActivity.this.deviceCSVParams = param1VarArgs[9].toString();
      String str5 = GlobalMembers.URL_WCF;
      String str1 = GlobalMembers.NAMESPACE_WCF;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str1);
      stringBuilder1.append("IService1/");
      stringBuilder1.append("GetCommandStatus");
      stringBuilder1.toString();
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      this.actionId = str3;
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Starting action: ");
      stringBuilder2.append(this.actionId);
      Utilities.escribeArchivo("ACTIONS", "WCF", stringBuilder2.toString());
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(str1);
      stringBuilder2.append("IService1/");
      stringBuilder2.append("GetLastIncomingMessageHistory2");
      String str6 = stringBuilder2.toString();
      linkedHashMap.clear();
      linkedHashMap.put("accountId", str4);
      linkedHashMap.put("deviceId", str2);
      linkedHashMap.put("actionId", integer);
      linkedHashMap.put("messageId", integer);
      GetNotifications(str3, 30000, 3000, 30, str5, str1, "GetLastIncomingMessageHistory2", str6, (LinkedHashMap)linkedHashMap);
      return this.messageId;
    }
    
    protected void onPostExecute(String param1String) {
      (new DBFunctions(NotificationsActivity.this.getApplicationContext())).processNotifications(this.responseNotifications);
      NotificationsActivity.this.loadNotifications();
      NotificationsActivity.this.swipeLayout.setRefreshing(false);
      OrientationManager.unlockOrientation(NotificationsActivity.this.act);
      NotificationsActivity.spinner_menu.setClickable(true);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(NotificationsActivity.this.rtApp.getDeviceTypeId());
      stringBuilder.append("_ID");
      PreferenceRT.SetValuePreference(stringBuilder.toString(), false, (Context)NotificationsActivity.this);
      GlobalMembers.notificaciones = 0;
      GlobalMembers.totalNotificaciones = 0;
      GlobalMembers.notificacionesDTC = 0;
      GlobalMembers.notificacionesAgendamiento = 0;
    }
    
    protected void onPreExecute() {
      OrientationManager.lockOrientation(NotificationsActivity.this.act);
      NotificationsActivity.spinner_menu.setClickable(false);
    }
    
    protected void onProgressUpdate(String... param1VarArgs) {}
  }
  
  class null implements RequestManager.OnPostExecuteListener {
    final NotificationsActivity.Actions this$1;
    
    public void onPostExecuteListener(String param1String, int param1Int) {
      String str1 = NotificationsActivity.TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("response: ");
      stringBuilder.append(param1String);
      String str2 = stringBuilder.toString();
      stringBuilder = new StringBuilder();
      stringBuilder.append("responseCode: ");
      stringBuilder.append(param1Int);
      Utilities.escribeArchivo(str1, str2, stringBuilder.toString());
      this.this$1.aux_result = param1String;
    }
  }
  
  @SuppressLint({"NewApi"})
  private class CustomAdapter extends ArrayAdapter<PushAlertsVO> {
    Activity activity;
    
    ArrayList<PushAlertsVO> alerts;
    
    RelativeLayout lvInside;
    
    @SuppressLint({"NewApi"})
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        final NotificationsActivity.CustomAdapter this$1;
        
        private void openMap(ActionMode param2ActionMode) {
          SparseBooleanArray sparseBooleanArray = NotificationsActivity.CustomAdapter.this.getSelectedIds();
          String str = new String();
          GlobalMembers.mixedNotificationsPoints = new ArrayList();
          byte b = 0;
          while (true) {
            int i = sparseBooleanArray.size();
            boolean bool = true;
            if (b < i) {
              String str1 = str;
              if (sparseBooleanArray.valueAt(b)) {
                PushAlertsVO pushAlertsVO = NotificationsActivity.CustomAdapter.this.getItem(sparseBooleanArray.keyAt(b));
                NotificationsActivity notificationsActivity = NotificationsActivity.this;
                str1 = Utilities.getStringFromConfigList((Context)notificationsActivity, notificationsActivity.stringsResourcesVO.notificationsNoRecorder, 2131690223);
                if (pushAlertsVO.getAlert().equalsIgnoreCase(str1) || !NotificationsActivity.this.isAlertRecordShowCheck(pushAlertsVO))
                  bool = false; 
                str1 = str;
                if (bool) {
                  String str2 = pushAlertsVO.getLatitude();
                  str = pushAlertsVO.getLongitude();
                  str1 = pushAlertsVO.getAlert();
                  if (str2 != null)
                    str2.equals(""); 
                  if (str != null)
                    str.equals(""); 
                  GlobalMembers.mixedNotificationsPoints.add(pushAlertsVO);
                } 
              } 
              b++;
              str = str1;
              continue;
            } 
            param2ActionMode.finish();
            NotificationsActivity.access$1702(NotificationsActivity.this, true);
            if (GlobalMembers.mixedNotificationsPoints.size() > 0) {
              Double double_2 = Double.valueOf(Double.parseDouble(((PushAlertsVO)GlobalMembers.mixedNotificationsPoints.get(0)).getLatitude()));
              Double double_1 = Double.valueOf(Double.parseDouble(((PushAlertsVO)GlobalMembers.mixedNotificationsPoints.get(0)).getLongitude()));
              if (sparseBooleanArray.size() == 1) {
                NotificationsActivity.this.startingMap(double_2.doubleValue(), double_1.doubleValue(), str, false, GlobalMembers.mixedNotificationsPoints.get(0));
              } else {
                NotificationsActivity.this.startingMap(double_2.doubleValue(), double_1.doubleValue(), str, true, GlobalMembers.mixedNotificationsPoints.get(0));
              } 
            } 
            return;
          } 
        }
        
        @SuppressLint({"NewApi"})
        public boolean onActionItemClicked(ActionMode param2ActionMode, MenuItem param2MenuItem) {
          String str1;
          SparseBooleanArray sparseBooleanArray;
          String str2;
          NotificationsActivity.access$1702(NotificationsActivity.this, false);
          int i = Integer.parseInt(param2ActionMode.getTag().toString());
          switch (param2MenuItem.getItemId()) {
            default:
              NotificationsActivity.access$1702(NotificationsActivity.this, false);
              break;
            case 2131296895:
              str2 = NotificationsActivity.CustomAdapter.this.getItem(i).getVehicle();
              str1 = str2;
              if (str2.equals(""))
                str1 = Utilities.getLastKnownDeviceSelected(NotificationsActivity.this.rtApp, NotificationsActivity.TAG).getDeviceId(); 
              NotificationsActivity.this.dbFunctions.deleteAllPush(str1);
              NotificationsActivity.this.dbFunctions.deleteAllHistorical(str1);
              NotificationsActivity.CustomAdapter.this.alerts.clear();
              NotificationsActivity.CustomAdapter.this.notifyDataSetChanged();
              param2ActionMode.finish();
              NotificationsActivity.access$1702(NotificationsActivity.this, true);
              break;
            case 2131296894:
              sparseBooleanArray = NotificationsActivity.CustomAdapter.this.getSelectedIds();
              for (i = sparseBooleanArray.size() - 1; i >= 0; i--) {
                if (sparseBooleanArray.valueAt(i)) {
                  PushAlertsVO pushAlertsVO = NotificationsActivity.CustomAdapter.this.getItem(sparseBooleanArray.keyAt(i));
                  if (pushAlertsVO.isAction()) {
                    NotificationsActivity.this.dbFunctions.deleteHistorical(Integer.valueOf(pushAlertsVO.getnActionID()).intValue());
                  } else {
                    NotificationsActivity.this.dbFunctions.deletePush(Integer.valueOf(pushAlertsVO.getId()).intValue());
                  } 
                  NotificationsActivity.CustomAdapter.this.alerts.remove(sparseBooleanArray.keyAt(i));
                  NotificationsActivity.CustomAdapter.this.notifyDataSetChanged();
                } 
              } 
              param2ActionMode.finish();
              NotificationsActivity.access$1702(NotificationsActivity.this, true);
              break;
            case 2131296898:
              break;
          } 
          return NotificationsActivity.this.endProcess;
        }
        
        public boolean onCreateActionMode(final ActionMode mode, Menu param2Menu) {
          NotificationsActivity.this.menuMap = param2Menu;
          mode.getMenuInflater().inflate(2131492870, NotificationsActivity.this.menuMap);
          NotificationsActivity notificationsActivity = NotificationsActivity.this;
          notificationsActivity.SeeMapButton = notificationsActivity.menuMap.findItem(2131296898);
          NotificationsActivity.this.SeeMapButton.setVisible(true);
          NotificationsActivity.this.SeeMapButton.getActionView().setOnClickListener(new View.OnClickListener() {
                final NotificationsActivity.CustomAdapter.null this$2;
                
                final ActionMode val$mode;
                
                public void onClick(View param3View) {
                  NotificationsActivity.CustomAdapter.null.this.openMap(mode);
                }
              });
          return true;
        }
        
        public void onDestroyActionMode(ActionMode param2ActionMode) {
          NotificationsActivity.access$402(NotificationsActivity.this, new SparseBooleanArray());
          NotificationsActivity.CustomAdapter.this.notifyDataSetChanged();
          NotificationsActivity.access$802(null);
        }
        
        public boolean onPrepareActionMode(ActionMode param2ActionMode, Menu param2Menu) {
          return false;
        }
      };
    
    final NotificationsActivity this$0;
    
    public CustomAdapter(Activity param1Activity, ArrayList<PushAlertsVO> param1ArrayList) {
      super((Context)param1Activity, 0);
      this.activity = param1Activity;
      this.alerts = param1ArrayList;
      NotificationsActivity.access$402(NotificationsActivity.this, new SparseBooleanArray());
    }
    
    private void onListItemSelected(int param1Int, CheckBox param1CheckBox) {
      boolean bool;
      toggleSelection(param1Int);
      if (NotificationsActivity.mActionMode != null) {
        NotificationsActivity notificationsActivity = NotificationsActivity.this;
        String str = Utilities.getStringFromConfigList((Context)notificationsActivity, notificationsActivity.stringsResourcesVO.global_main_lbl_seleccionado_1, 2131689938);
        if (getSelectedCount() < 2) {
          ActionMode actionMode = (ActionMode)NotificationsActivity.mActionMode;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(String.valueOf(getSelectedCount()));
          stringBuilder.append(" ");
          stringBuilder.append(str);
          actionMode.setTitle(stringBuilder.toString());
        } else {
          notificationsActivity = NotificationsActivity.this;
          String str1 = Utilities.getStringFromConfigList((Context)notificationsActivity, notificationsActivity.stringsResourcesVO.global_main_lbl_seleccionados_1, 2131689939);
          ActionMode actionMode = (ActionMode)NotificationsActivity.mActionMode;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(String.valueOf(getSelectedCount()));
          stringBuilder.append(" ");
          stringBuilder.append(str1);
          actionMode.setTitle(stringBuilder.toString());
        } 
      } 
      SparseBooleanArray sparseBooleanArray = getSelectedIds();
      if (sparseBooleanArray.size() > 0) {
        bool = true;
      } else {
        bool = false;
      } 
      param1Int = 0;
      while (param1Int < sparseBooleanArray.size()) {
        boolean bool1 = bool;
        if (sparseBooleanArray.valueAt(param1Int)) {
          PushAlertsVO pushAlertsVO = getItem(sparseBooleanArray.keyAt(param1Int));
          if (pushAlertsVO != null && (!pushAlertsVO.getActionId().equals("") || pushAlertsVO.getActionId() != null)) {
            String str1 = pushAlertsVO.getActionId().trim();
            NotificationsActivity notificationsActivity1 = NotificationsActivity.this;
            String str2 = Utilities.getStringFromConfigList((Context)notificationsActivity1, notificationsActivity1.stringsResourcesVO.global_lbl_accionalertavel_1, 2131689851);
            NotificationsActivity notificationsActivity2 = NotificationsActivity.this;
            String str3 = Utilities.getStringFromConfigList((Context)notificationsActivity2, notificationsActivity2.stringsResourcesVO.alert_code_id_follow_me, 2131689662);
            NotificationsActivity notificationsActivity3 = NotificationsActivity.this;
            String str4 = Utilities.getStringFromConfigList((Context)notificationsActivity3, notificationsActivity3.stringsResourcesVO.global_lbl_accionalertamov_1, 2131689845);
            try {
              if ("valet".equalsIgnoreCase(str1) || str2.equalsIgnoreCase(str1)) {
                Enums$Services.valet.GetCode();
              } else if ("followme".equalsIgnoreCase(str1) || str3.equalsIgnoreCase(str1)) {
                Enums$Services.FollowMe.GetCode();
              } else if ("movimento".equalsIgnoreCase(str1) || str4.equalsIgnoreCase(str1)) {
                Enums$Services.Parking.GetCode();
              } else if ("none".equalsIgnoreCase(str1) || str2.equalsIgnoreCase(str1)) {
                Enums$Services.None.GetCode();
              } else if ("Velocidade".equalsIgnoreCase(str1) || str2.equalsIgnoreCase(str1)) {
                Enums$Services.Speed.GetCode();
              } else {
                Integer.parseInt(str1);
              } 
            } catch (Exception exception) {}
          } 
          if (!NotificationsActivity.this.isAlertRecordShowCheck(pushAlertsVO))
            bool = false; 
          MenuItem menuItem = NotificationsActivity.this.SeeMapButton;
          bool1 = bool;
          if (menuItem != null) {
            menuItem.setVisible(bool);
            bool1 = bool;
          } 
        } 
        param1Int++;
        bool = bool1;
      } 
    }
    
    private void toggleSelection(int param1Int) {
      selectView(param1Int, NotificationsActivity.this.mSelectedItemsIds.get(param1Int) ^ true);
    }
    
    public int getCount() {
      return this.alerts.size();
    }
    
    public PushAlertsVO getItem(int param1Int) {
      return this.alerts.get(param1Int);
    }
    
    public long getItemId(int param1Int) {
      return 0L;
    }
    
    public int getSelectedCount() {
      return NotificationsActivity.this.mSelectedItemsIds.size();
    }
    
    public SparseBooleanArray getSelectedIds() {
      return NotificationsActivity.this.mSelectedItemsIds;
    }
    
    public View getView(final int position, View param1View, ViewGroup param1ViewGroup) {
      View view = this.activity.getLayoutInflater().inflate(2131427386, null);
      NotificationsActivity notificationsActivity1 = NotificationsActivity.this;
      notificationsActivity1.rtApp;
      NotificationsActivity notificationsActivity2 = NotificationsActivity.this;
      NotificationsActivity.access$502(notificationsActivity1, onstarApplication.getTypeface((Context)notificationsActivity2, notificationsActivity2.rtApp.fontPathLouisRegular));
      String str1 = Utilities.getStringFromConfigList((Context)this.activity, NotificationsActivity.this.stringsResourcesVO.global_lbl_accionalertamovsl_1, 2131689846);
      final CheckBox checkSel = (CheckBox)view.findViewById(2131296463);
      ImageView imageView = (ImageView)view.findViewById(2131296634);
      TextView textView1 = (TextView)view.findViewById(2131296371);
      textView1.setText(str1);
      textView1.setTypeface(NotificationsActivity.this.tf);
      TextView textView2 = (TextView)view.findViewById(2131297229);
      textView2.setTypeface(NotificationsActivity.this.tf);
      TextView textView3 = (TextView)view.findViewById(2131296492);
      textView3.setTypeface(NotificationsActivity.this.tf);
      this.lvInside = (RelativeLayout)view.findViewById(2131296283);
      PushAlertsVO pushAlertsVO = this.alerts.get(position);
      String str4 = pushAlertsVO.getAlert();
      String str3 = pushAlertsVO.getVehicle();
      String str2 = pushAlertsVO.getDate();
      int j = str2.length();
      int i = 0;
      str1 = str2;
      if (j > 0)
        str1 = str2.substring(0, j - 3); 
      str4.split(",");
      imageView.setImageDrawable(NotificationsActivity.this.alertInfo());
      if (str4 != null)
        textView1.setText(str4); 
      if (str3 != null)
        textView2.setText(((PushAlertsVO)this.alerts.get(position)).getVehicle()); 
      if (str1 != null)
        textView3.setText(str1); 
      checkBox.setChecked(NotificationsActivity.this.mSelectedItemsIds.get(position));
      checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            final NotificationsActivity.CustomAdapter this$1;
            
            final CheckBox val$checkSel;
            
            final int val$position;
            
            public void onCheckedChanged(CompoundButton param2CompoundButton, boolean param2Boolean) {
              if (NotificationsActivity.mActionMode == null) {
                NotificationsActivity.CustomAdapter customAdapter = NotificationsActivity.CustomAdapter.this;
                NotificationsActivity.access$802(NotificationsActivity.this.startActionMode(customAdapter.mActionModeCallback));
                ((ActionMode)NotificationsActivity.mActionMode).setTag(Integer.valueOf(position));
                NotificationsActivity.this.lvData.setSelected(true);
              } 
              NotificationsActivity.CustomAdapter.this.onListItemSelected(position, checkSel);
            }
          });
      if (!NotificationsActivity.this.isAlertRecordShowCheck(pushAlertsVO))
        checkBox.setVisibility(4); 
      if (!NotificationsActivity.this.isMapACtive(pushAlertsVO, checkBox))
        checkBox.setVisibility(4); 
      this.lvInside.setOnClickListener(new View.OnClickListener() {
            final NotificationsActivity.CustomAdapter this$1;
            
            final int val$position;
            
            public void onClick(View param2View) {
              NotificationsActivity.access$1402(NotificationsActivity.this, (CheckBox)param2View.findViewById(2131296463));
              if (NotificationsActivity.mActionMode == null && ((PushAlertsVO)NotificationsActivity.CustomAdapter.this.alerts.get(position)).getAlertCodeId() != 208 && ((PushAlertsVO)NotificationsActivity.CustomAdapter.this.alerts.get(position)).getAlertCodeId() != -1) {
                NotificationsActivity.this.chk.setChecked(true);
              } else if (NotificationsActivity.mActionMode == null) {
                if (((PushAlertsVO)NotificationsActivity.CustomAdapter.this.alerts.get(position)).getAlertCodeId() == 208) {
                  Intent intent = new Intent((Context)NotificationsActivity.this, RemoteDiagnosticActivity.class);
                  Bundle bundle = new Bundle();
                  bundle.putString("previousClass", NotificationsActivity.class.getSimpleName());
                  intent.putExtras(bundle);
                  NotificationsActivity.this.startActivity(intent);
                } else if (((PushAlertsVO)NotificationsActivity.CustomAdapter.this.alerts.get(position)).getAlertCodeId() == -1) {
                  if (NetUtilities.validateNetwork((Context)NotificationsActivity.this, false)) {
                    try {
                      Intent intent = new Intent();
                      this("android.intent.action.VIEW", Uri.parse(GlobalMembers.URL_DTC_ChevroletCOAgendar));
                      intent.addFlags(268435456);
                      NotificationsActivity.this.getApplication().startActivity(intent);
                    } catch (Exception exception) {
                      NotificationsActivity notificationsActivity = NotificationsActivity.this;
                      String str = Utilities.getStringFromConfigList((Context)notificationsActivity, notificationsActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                      Toast.makeText(NotificationsActivity.this.context, str, 1).show();
                    } 
                  } else {
                    NotificationsActivity notificationsActivity = NotificationsActivity.this;
                    String str = Utilities.getStringFromConfigList((Context)notificationsActivity, notificationsActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                    Toast.makeText((Context)NotificationsActivity.this, str, 1).show();
                  } 
                } 
              } else {
                NotificationsActivity.CustomAdapter customAdapter = NotificationsActivity.CustomAdapter.this;
                customAdapter.onListItemSelected(position, NotificationsActivity.this.chk);
              } 
            }
          });
      this.lvInside.setOnLongClickListener(new View.OnLongClickListener() {
            final NotificationsActivity.CustomAdapter this$1;
            
            final int val$position;
            
            public boolean onLongClick(View param2View) {
              NotificationsActivity.access$1502(NotificationsActivity.this, (CheckBox)param2View.findViewById(2131296463));
              if (NotificationsActivity.mActionMode == null) {
                NotificationsActivity.this.chk1.setChecked(true);
                return true;
              } 
              NotificationsActivity.CustomAdapter customAdapter = NotificationsActivity.CustomAdapter.this;
              customAdapter.onListItemSelected(position, NotificationsActivity.this.chk);
              return false;
            }
          });
      if (NotificationsActivity.this.mSelectedItemsIds.get(position))
        i = Color.parseColor("#D4E1DE"); 
      view.setBackgroundColor(i);
      return view;
    }
    
    public void selectView(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        NotificationsActivity.this.mSelectedItemsIds.put(param1Int, param1Boolean);
      } else {
        NotificationsActivity.this.mSelectedItemsIds.delete(param1Int);
      } 
      notifyDataSetChanged();
    }
  }
  
  class null implements CompoundButton.OnCheckedChangeListener {
    final NotificationsActivity.CustomAdapter this$1;
    
    final CheckBox val$checkSel;
    
    final int val$position;
    
    public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
      if (NotificationsActivity.mActionMode == null) {
        NotificationsActivity.CustomAdapter customAdapter = this.this$1;
        NotificationsActivity.access$802(NotificationsActivity.this.startActionMode(customAdapter.mActionModeCallback));
        ((ActionMode)NotificationsActivity.mActionMode).setTag(Integer.valueOf(position));
        NotificationsActivity.this.lvData.setSelected(true);
      } 
      this.this$1.onListItemSelected(position, checkSel);
    }
  }
  
  class null implements View.OnClickListener {
    final NotificationsActivity.CustomAdapter this$1;
    
    final int val$position;
    
    public void onClick(View param1View) {
      NotificationsActivity.access$1402(NotificationsActivity.this, (CheckBox)param1View.findViewById(2131296463));
      if (NotificationsActivity.mActionMode == null && ((PushAlertsVO)this.this$1.alerts.get(position)).getAlertCodeId() != 208 && ((PushAlertsVO)this.this$1.alerts.get(position)).getAlertCodeId() != -1) {
        NotificationsActivity.this.chk.setChecked(true);
      } else if (NotificationsActivity.mActionMode == null) {
        if (((PushAlertsVO)this.this$1.alerts.get(position)).getAlertCodeId() == 208) {
          Intent intent = new Intent((Context)NotificationsActivity.this, RemoteDiagnosticActivity.class);
          Bundle bundle = new Bundle();
          bundle.putString("previousClass", NotificationsActivity.class.getSimpleName());
          intent.putExtras(bundle);
          NotificationsActivity.this.startActivity(intent);
        } else if (((PushAlertsVO)this.this$1.alerts.get(position)).getAlertCodeId() == -1) {
          if (NetUtilities.validateNetwork((Context)NotificationsActivity.this, false)) {
            try {
              Intent intent = new Intent();
              this("android.intent.action.VIEW", Uri.parse(GlobalMembers.URL_DTC_ChevroletCOAgendar));
              intent.addFlags(268435456);
              NotificationsActivity.this.getApplication().startActivity(intent);
            } catch (Exception exception) {
              NotificationsActivity notificationsActivity = NotificationsActivity.this;
              String str = Utilities.getStringFromConfigList((Context)notificationsActivity, notificationsActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText(NotificationsActivity.this.context, str, 1).show();
            } 
          } else {
            NotificationsActivity notificationsActivity = NotificationsActivity.this;
            String str = Utilities.getStringFromConfigList((Context)notificationsActivity, notificationsActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
            Toast.makeText((Context)NotificationsActivity.this, str, 1).show();
          } 
        } 
      } else {
        NotificationsActivity.CustomAdapter customAdapter = this.this$1;
        customAdapter.onListItemSelected(position, NotificationsActivity.this.chk);
      } 
    }
  }
  
  class null implements View.OnLongClickListener {
    final NotificationsActivity.CustomAdapter this$1;
    
    final int val$position;
    
    public boolean onLongClick(View param1View) {
      NotificationsActivity.access$1502(NotificationsActivity.this, (CheckBox)param1View.findViewById(2131296463));
      if (NotificationsActivity.mActionMode == null) {
        NotificationsActivity.this.chk1.setChecked(true);
        return true;
      } 
      NotificationsActivity.CustomAdapter customAdapter = this.this$1;
      customAdapter.onListItemSelected(position, NotificationsActivity.this.chk);
      return false;
    }
  }
  
  class null implements ActionMode.Callback {
    final NotificationsActivity.CustomAdapter this$1;
    
    private void openMap(ActionMode param1ActionMode) {
      SparseBooleanArray sparseBooleanArray = this.this$1.getSelectedIds();
      String str = new String();
      GlobalMembers.mixedNotificationsPoints = new ArrayList();
      byte b = 0;
      while (true) {
        int i = sparseBooleanArray.size();
        boolean bool = true;
        if (b < i) {
          String str1 = str;
          if (sparseBooleanArray.valueAt(b)) {
            PushAlertsVO pushAlertsVO = this.this$1.getItem(sparseBooleanArray.keyAt(b));
            NotificationsActivity notificationsActivity = NotificationsActivity.this;
            str1 = Utilities.getStringFromConfigList((Context)notificationsActivity, notificationsActivity.stringsResourcesVO.notificationsNoRecorder, 2131690223);
            if (pushAlertsVO.getAlert().equalsIgnoreCase(str1) || !NotificationsActivity.this.isAlertRecordShowCheck(pushAlertsVO))
              bool = false; 
            str1 = str;
            if (bool) {
              String str2 = pushAlertsVO.getLatitude();
              str = pushAlertsVO.getLongitude();
              str1 = pushAlertsVO.getAlert();
              if (str2 != null)
                str2.equals(""); 
              if (str != null)
                str.equals(""); 
              GlobalMembers.mixedNotificationsPoints.add(pushAlertsVO);
            } 
          } 
          b++;
          str = str1;
          continue;
        } 
        param1ActionMode.finish();
        NotificationsActivity.access$1702(NotificationsActivity.this, true);
        if (GlobalMembers.mixedNotificationsPoints.size() > 0) {
          Double double_2 = Double.valueOf(Double.parseDouble(((PushAlertsVO)GlobalMembers.mixedNotificationsPoints.get(0)).getLatitude()));
          Double double_1 = Double.valueOf(Double.parseDouble(((PushAlertsVO)GlobalMembers.mixedNotificationsPoints.get(0)).getLongitude()));
          if (sparseBooleanArray.size() == 1) {
            NotificationsActivity.this.startingMap(double_2.doubleValue(), double_1.doubleValue(), str, false, GlobalMembers.mixedNotificationsPoints.get(0));
          } else {
            NotificationsActivity.this.startingMap(double_2.doubleValue(), double_1.doubleValue(), str, true, GlobalMembers.mixedNotificationsPoints.get(0));
          } 
        } 
        return;
      } 
    }
    
    @SuppressLint({"NewApi"})
    public boolean onActionItemClicked(ActionMode param1ActionMode, MenuItem param1MenuItem) {
      String str1;
      SparseBooleanArray sparseBooleanArray;
      String str2;
      NotificationsActivity.access$1702(NotificationsActivity.this, false);
      int i = Integer.parseInt(param1ActionMode.getTag().toString());
      switch (param1MenuItem.getItemId()) {
        default:
          NotificationsActivity.access$1702(NotificationsActivity.this, false);
          break;
        case 2131296895:
          str2 = this.this$1.getItem(i).getVehicle();
          str1 = str2;
          if (str2.equals(""))
            str1 = Utilities.getLastKnownDeviceSelected(NotificationsActivity.this.rtApp, NotificationsActivity.TAG).getDeviceId(); 
          NotificationsActivity.this.dbFunctions.deleteAllPush(str1);
          NotificationsActivity.this.dbFunctions.deleteAllHistorical(str1);
          this.this$1.alerts.clear();
          this.this$1.notifyDataSetChanged();
          param1ActionMode.finish();
          NotificationsActivity.access$1702(NotificationsActivity.this, true);
          break;
        case 2131296894:
          sparseBooleanArray = this.this$1.getSelectedIds();
          for (i = sparseBooleanArray.size() - 1; i >= 0; i--) {
            if (sparseBooleanArray.valueAt(i)) {
              PushAlertsVO pushAlertsVO = this.this$1.getItem(sparseBooleanArray.keyAt(i));
              if (pushAlertsVO.isAction()) {
                NotificationsActivity.this.dbFunctions.deleteHistorical(Integer.valueOf(pushAlertsVO.getnActionID()).intValue());
              } else {
                NotificationsActivity.this.dbFunctions.deletePush(Integer.valueOf(pushAlertsVO.getId()).intValue());
              } 
              this.this$1.alerts.remove(sparseBooleanArray.keyAt(i));
              this.this$1.notifyDataSetChanged();
            } 
          } 
          param1ActionMode.finish();
          NotificationsActivity.access$1702(NotificationsActivity.this, true);
          break;
        case 2131296898:
          break;
      } 
      return NotificationsActivity.this.endProcess;
    }
    
    public boolean onCreateActionMode(final ActionMode mode, Menu param1Menu) {
      NotificationsActivity.this.menuMap = param1Menu;
      mode.getMenuInflater().inflate(2131492870, NotificationsActivity.this.menuMap);
      NotificationsActivity notificationsActivity = NotificationsActivity.this;
      notificationsActivity.SeeMapButton = notificationsActivity.menuMap.findItem(2131296898);
      NotificationsActivity.this.SeeMapButton.setVisible(true);
      NotificationsActivity.this.SeeMapButton.getActionView().setOnClickListener(new View.OnClickListener() {
            final NotificationsActivity.CustomAdapter.null this$2;
            
            final ActionMode val$mode;
            
            public void onClick(View param3View) {
              NotificationsActivity.CustomAdapter.null.this.openMap(mode);
            }
          });
      return true;
    }
    
    public void onDestroyActionMode(ActionMode param1ActionMode) {
      NotificationsActivity.access$402(NotificationsActivity.this, new SparseBooleanArray());
      this.this$1.notifyDataSetChanged();
      NotificationsActivity.access$802(null);
    }
    
    public boolean onPrepareActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return false;
    }
  }
  
  class null implements View.OnClickListener {
    final NotificationsActivity.CustomAdapter.null this$2;
    
    final ActionMode val$mode;
    
    public void onClick(View param1View) {
      this.this$2.openMap(mode);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/NotificationsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */