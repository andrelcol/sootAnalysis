package com.roadtrack.onstar.pid;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.roadtrack.onstar.BO.ActionsProcess;
import com.roadtrack.onstar.BO.BluetoothServiceRT;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.ManagerNotificationPlatinum;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.ActionResultVO;
import com.roadtrack.onstar.VO.CustomActionButton;
import com.roadtrack.onstar.VO.CustomActionButtonFactory;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.RemoteDiagnosticVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.adapter.RemoteDiagnosticAdapter;
import com.roadtrack.onstar.async_tasks.intefaces.DtcStatus_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.Dtc_Interface;
import com.roadtrack.onstar.async_tasks.tasks.GetDTCSelector_task;
import com.roadtrack.onstar.async_tasks.tasks.UpdateStatusDTCSelector_task;
import com.roadtrack.onstar.circularProgress.AnimDownloadProgressButton;
import com.roadtrack.onstar.entities.ActiveCallEntity;
import com.roadtrack.onstar.new_ws.ExecuteDTCWS;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.progressAnimation.DotProgressBar;
import com.roadtrack.onstar.ui.dialogs.ActivityCall;
import com.roadtrack.onstar.utils.DialogManager;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.viewTutorial.ClassElements;
import com.roadtrack.onstar.viewTutorial.ShowViewElement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class RemoteDiagnosticActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
  private static boolean IsCheckStatusDTC_Selector = false;
  
  static boolean Process_dialog_DTC_finished = false;
  
  public static String TAG;
  
  public static Activity _activity;
  
  public static Context _context;
  
  private static String _status;
  
  private static ActiveCallEntity actCall;
  
  static ActionsProcess apPID;
  
  public static boolean bBotonIVRPulsado = false;
  
  public static boolean bRelayReady = true;
  
  private static boolean blockBackPressed = false;
  
  private static Configuration config;
  
  private static int contCircular = 0;
  
  static DBFunctions dbFunctions;
  
  static UserDevicesVO deviceInfo;
  
  private static String dtc_popup_lbl_avisodesactivarDTC;
  
  private static String dtc_popup_lbl_cliquepararesultado;
  
  private static String dtc_popup_lbl_ejecutado;
  
  private static String dtc_popup_lbl_noejecutado;
  
  private static String dtc_popup_lbl_proceso;
  
  private static boolean flagProgress = false;
  
  private static String global_lbl_acciondescposiblenotif_1;
  
  private static String global_lbl_asistencia_1;
  
  private static String global_lbl_warnUserDTC;
  
  private static String global_popup_btn_aceptar_1;
  
  private static String global_popup_btn_no_1;
  
  private static String global_popup_btn_ok_1;
  
  private static String global_popup_lbl_accionencurso_1;
  
  private static String global_popup_lbl_aviso_1;
  
  private static Handler handler;
  
  public static int iPreviousCallIndex = 0;
  
  private static boolean isExecuteAction = false;
  
  private static boolean isInitiatingCall = false;
  
  public static boolean isOnPause = false;
  
  private static Boolean isSwipeGesture = Boolean.valueOf(true);
  
  public static boolean isdialog_action_process_frame_visible = false;
  
  static boolean isrunning;
  
  public static BluetoothServiceRT mChatService;
  
  private static ManagerNotificationPlatinum mNPlatinum;
  
  private static String notificaciones_main_lbl_cancelar_1;
  
  private static String pid_popup_lbl_actualizadofecha_1;
  
  static RemoteDiagnosticAdapter remoteAdapter;
  
  static List<RemoteDiagnosticVO> remoteItemsList;
  
  static onstarApplication rtApp;
  
  private static Runnable runnable;
  
  private static StringsResourcesVO stringsResourcesVO;
  
  private CustomActionButton CustomActionButton_status;
  
  private SwitchCompat DTC_Selector;
  
  private String STATE_TUTORIAL = "state_tutorial";
  
  private TextView StatusTextMessage;
  
  private TextView TextCliked;
  
  private LinearLayout alertcontaineractionprocess;
  
  private String asistencia_global_popup_lbl_llamadaasistencia_2;
  
  Button btnAccept;
  
  Button btnCancel;
  
  Button btnNOK;
  
  Button buttonNOK;
  
  Button buttonOk;
  
  private CustomActionButton cab_dtc_assistance;
  
  private CustomActionButton cab_dtc_schedule;
  
  public View.OnClickListener clickTutorial = new View.OnClickListener() {
      final RemoteDiagnosticActivity this$0;
      
      public void onClick(View param1View) {
        if (!RemoteDiagnosticActivity.isExecuteAction) {
          if (RemoteDiagnosticActivity.this.pressTuto)
            RemoteDiagnosticActivity.this.showTutorial(); 
        } else {
          RemoteDiagnosticActivity.this.setActionButtons();
        } 
      }
    };
  
  private RelativeLayout dialog_action_process_frame;
  
  private DotProgressBar dotProgressBar;
  
  private FrameLayout frl_dtc_no_info;
  
  FrameLayout frl_dtc_swipe;
  
  private String global_lbl_acciondescfallared_1;
  
  private String global_popup_btn_llamar_1;
  
  private LinearLayout idTutorial;
  
  ImageView imageView;
  
  ImageView ivHeader;
  
  private ImageView ivIconWake;
  
  TextView lbl_dtc_device_name;
  
  private TextView lbl_dtc_no_info;
  
  private TextView lbl_dtc_no_info2;
  
  TextView lbl_dtc_title;
  
  private TextView lbl_dtc_update_time;
  
  TextView lbl_swipe_update;
  
  LinearLayout lin_dtc_device_info;
  
  LinearLayout lin_dtc_footer_buttons;
  
  private ListView lv_dtc;
  
  BroadcastReceiver mReceiver = new BroadcastReceiver() {
      final RemoteDiagnosticActivity this$0;
      
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getAction().equals("com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT")) {
          param1Intent.getExtras();
          String str1 = param1Intent.getStringExtra("Response");
          String str2 = param1Intent.getStringExtra("Response2");
          int j = Integer.parseInt(str1);
          int k = str2.equals("0");
          int i = RemoteDiagnosticActivity.iPreviousCallIndex;
          boolean bool = false;
          if (i != 0) {
            i = 1;
          } else {
            i = 0;
          } 
          if ((k & i) != 0)
            (new Thread(new Runnable() {
                  final RemoteDiagnosticActivity.null this$1;
                  
                  public void run() {
                    try {
                      Thread.sleep(5000L);
                    } catch (InterruptedException interruptedException) {
                      Utilities.escribeArchivo(RemoteDiagnosticActivity.TAG, "Error: Timer", interruptedException.toString());
                    } 
                    RemoteDiagnosticActivity.this.runOnUiThread(new Runnable(this) {
                          public void run() {
                            Thread thread = Thread.currentThread();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(null.class.getSimpleName());
                            stringBuilder.append(": ");
                            stringBuilder.append(Thread.currentThread().getName());
                            thread.setName(stringBuilder.toString());
                            GlobalMembers.bStartingCall = false;
                          }
                        });
                  }
                })).start(); 
          RemoteDiagnosticActivity.iPreviousCallIndex = Integer.parseInt(str2);
          if (GlobalMembers.btnpress != 1)
            if (!GlobalMembers.bActivityCallRunning) {
              boolean bool1 = bool;
              if (RemoteDiagnosticActivity.iPreviousCallIndex != 0)
                bool1 = true; 
              if (((GlobalMembers.bStartingCall ^ true) & bool1) != 0) {
                GlobalMembers.bStartingCall = true;
                Intent intent = new Intent((Context)RemoteDiagnosticActivity.this, ActivityCall.class);
                intent.putExtra("Boton", "Llamada");
                RemoteDiagnosticActivity.access$2302(true);
                RemoteDiagnosticActivity.iPreviousCallIndex = 1;
                RemoteDiagnosticActivity.this.startActivityForResult(intent, 123);
              } 
            } else {
              ActivityCall.sendCallStatusRecived(j, Integer.parseInt(str2));
            }  
        } 
      }
    };
  
  private boolean pressTuto = true;
  
  String previousClass = "";
  
  private AnimDownloadProgressButton progress;
  
  ProgressBar progressDTCSelector;
  
  private ShowViewElement show;
  
  private boolean stateTutorial;
  
  public SwipeRefreshLayout.OnRefreshListener swipeRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
      final RemoteDiagnosticActivity this$0;
      
      public void onRefresh() {
        if (!RemoteDiagnosticActivity.this.verifyActionRunning()) {
          GlobalMembers.notificacionesDTC = 0;
          RemoteDiagnosticActivity.dbFunctions.readerNotificationDTC(Utilities.getLastKnownDeviceSelected(RemoteDiagnosticActivity.rtApp, RemoteDiagnosticActivity.TAG).getDeviceId());
          if (!NetUtilities.validateNetwork(RemoteDiagnosticActivity._context, false, true)) {
            RemoteDiagnosticActivity.this.afterRefresh();
          } else if (RemoteDiagnosticActivity.isSwipeGesture.booleanValue()) {
            RemoteDiagnosticActivity.this.confirmActionDialog();
          } else {
            RemoteDiagnosticActivity.this.whileRefreshing();
            RemoteDiagnosticActivity.this.consumingWS();
            RemoteDiagnosticActivity.access$302(Boolean.valueOf(true));
          } 
        } else {
          RemoteDiagnosticActivity.this.swipe_dtc.setRefreshing(false);
        } 
      }
    };
  
  private SwipeRefreshLayout swipe_dtc;
  
  private Typeface tfLouis;
  
  private Typeface tfLouisItalic;
  
  private TextView tip_dtc;
  
  LinearLayout tip_dtc_layout;
  
  private TextView tittleTextStatus;
  
  private TextView tvProgress;
  
  private TextView tvTextReintent;
  
  private TextView tvTextWake;
  
  TextView txt_dtc;
  
  public RelativeLayout wakeupcar_container;
  
  static {
    blockBackPressed = false;
    IsCheckStatusDTC_Selector = false;
    isrunning = false;
    isExecuteAction = false;
    isOnPause = false;
    flagProgress = false;
    handler = null;
    runnable = null;
    contCircular = 0;
  }
  
  public static boolean CallOrHangUp(Enums.Calls paramCalls) {
    // Byte code:
    //   0: ldc com/roadtrack/onstar/pid/RemoteDiagnosticActivity
    //   2: monitorenter
    //   3: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.isInitiatingCall : Z
    //   6: istore_3
    //   7: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.iPreviousCallIndex : I
    //   10: ifeq -> 18
    //   13: iconst_1
    //   14: istore_1
    //   15: goto -> 20
    //   18: iconst_0
    //   19: istore_1
    //   20: iload_3
    //   21: iload_1
    //   22: iand
    //   23: istore_2
    //   24: iload_2
    //   25: istore_1
    //   26: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   29: ifnull -> 227
    //   32: iload_2
    //   33: istore_1
    //   34: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity$33.$SwitchMap$com$roadtrack$onstar$Enums$Calls : [I
    //   37: aload_0
    //   38: invokevirtual ordinal : ()I
    //   41: iaload
    //   42: tableswitch default -> 84, 1 -> 197, 2 -> 166, 3 -> 135, 4 -> 227, 5 -> 89, 6 -> 227, 7 -> 227
    //   84: iload_2
    //   85: istore_1
    //   86: goto -> 227
    //   89: iload_2
    //   90: istore_1
    //   91: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   94: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   97: getstatic com/roadtrack/onstar/Enums$CallNumberType.VR : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   100: if_acmpne -> 227
    //   103: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   106: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   109: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   112: if_acmpeq -> 127
    //   115: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity._context : Landroid/content/Context;
    //   118: ldc_w 'RTMobile_HMI'
    //   121: invokestatic wakeDevice : (Landroid/content/Context;Ljava/lang/String;)V
    //   124: goto -> 225
    //   127: invokestatic stopWakeDevice : ()V
    //   130: iload_2
    //   131: istore_1
    //   132: goto -> 227
    //   135: iload_2
    //   136: istore_1
    //   137: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   140: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   143: getstatic com/roadtrack/onstar/Enums$CallNumberType.Agendamiento : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   146: if_acmpne -> 227
    //   149: iload_2
    //   150: istore_1
    //   151: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   154: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   157: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   160: if_acmpeq -> 227
    //   163: goto -> 225
    //   166: iload_2
    //   167: istore_1
    //   168: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   171: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   174: getstatic com/roadtrack/onstar/Enums$CallNumberType.Emergency : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   177: if_acmpne -> 227
    //   180: iload_2
    //   181: istore_1
    //   182: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   185: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   188: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   191: if_acmpeq -> 227
    //   194: goto -> 225
    //   197: iload_2
    //   198: istore_1
    //   199: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   202: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   205: getstatic com/roadtrack/onstar/Enums$CallNumberType.Assistance : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   208: if_acmpne -> 227
    //   211: iload_2
    //   212: istore_1
    //   213: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   216: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   219: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   222: if_acmpeq -> 227
    //   225: iconst_1
    //   226: istore_1
    //   227: iload_1
    //   228: ifeq -> 280
    //   231: new com/roadtrack/onstar/entities/ActiveCallEntity
    //   234: astore #4
    //   236: aload #4
    //   238: invokespecial <init> : ()V
    //   241: aload #4
    //   243: putstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   246: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   249: getstatic com/roadtrack/onstar/Enums$DeviceCall.BT : Lcom/roadtrack/onstar/Enums$DeviceCall;
    //   252: invokevirtual setDevice : (Lcom/roadtrack/onstar/Enums$DeviceCall;)V
    //   255: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   258: iconst_0
    //   259: invokevirtual setCallIndex : (I)V
    //   262: iconst_0
    //   263: putstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.isInitiatingCall : Z
    //   266: aload_0
    //   267: getstatic com/roadtrack/onstar/Enums$Calls.IVR : Lcom/roadtrack/onstar/Enums$Calls;
    //   270: if_acmpne -> 280
    //   273: invokestatic getInstance : ()Lcom/roadtrack/onstar/platinum/MultiModalHMI;
    //   276: iconst_2
    //   277: invokevirtual closeMenuHMI : (I)V
    //   280: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   283: ifnonnull -> 292
    //   286: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   289: putstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   292: iload_1
    //   293: ifeq -> 317
    //   296: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   299: getstatic com/roadtrack/onstar/Enums$ActionCall.Hang_Up : Lcom/roadtrack/onstar/Enums$ActionCall;
    //   302: invokevirtual setAction : (Lcom/roadtrack/onstar/Enums$ActionCall;)V
    //   305: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   308: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   311: invokevirtual callControl : (Lcom/roadtrack/onstar/entities/ActiveCallEntity;)V
    //   314: goto -> 386
    //   317: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.bBotonIVRPulsado : Z
    //   320: ifne -> 328
    //   323: iconst_1
    //   324: istore_1
    //   325: goto -> 330
    //   328: iconst_0
    //   329: istore_1
    //   330: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.iPreviousCallIndex : I
    //   333: ifne -> 341
    //   336: iconst_1
    //   337: istore_2
    //   338: goto -> 343
    //   341: iconst_0
    //   342: istore_2
    //   343: iload_1
    //   344: iload_2
    //   345: iand
    //   346: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.bRelayReady : Z
    //   349: iand
    //   350: ifeq -> 366
    //   353: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   356: aload_0
    //   357: ldc_w ''
    //   360: invokevirtual initiatingCall : (Lcom/roadtrack/onstar/Enums$Calls;Ljava/lang/String;)V
    //   363: goto -> 386
    //   366: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.bBotonIVRPulsado : Z
    //   369: ifeq -> 391
    //   372: iconst_0
    //   373: putstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.bBotonIVRPulsado : Z
    //   376: getstatic com/roadtrack/onstar/pid/RemoteDiagnosticActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   379: aload_0
    //   380: ldc_w ''
    //   383: invokevirtual initiatingCall : (Lcom/roadtrack/onstar/Enums$Calls;Ljava/lang/String;)V
    //   386: ldc com/roadtrack/onstar/pid/RemoteDiagnosticActivity
    //   388: monitorexit
    //   389: iconst_1
    //   390: ireturn
    //   391: ldc com/roadtrack/onstar/pid/RemoteDiagnosticActivity
    //   393: monitorexit
    //   394: iconst_0
    //   395: ireturn
    //   396: astore_0
    //   397: ldc com/roadtrack/onstar/pid/RemoteDiagnosticActivity
    //   399: monitorexit
    //   400: aload_0
    //   401: athrow
    // Exception table:
    //   from	to	target	type
    //   3	13	396	finally
    //   26	32	396	finally
    //   34	84	396	finally
    //   91	124	396	finally
    //   127	130	396	finally
    //   137	149	396	finally
    //   151	163	396	finally
    //   168	180	396	finally
    //   182	194	396	finally
    //   199	211	396	finally
    //   213	225	396	finally
    //   231	280	396	finally
    //   280	292	396	finally
    //   296	314	396	finally
    //   317	323	396	finally
    //   330	336	396	finally
    //   343	363	396	finally
    //   366	386	396	finally
  }
  
  private static Animation SetAnimation(int paramInt) {
    return AnimationUtils.loadAnimation(_context, paramInt);
  }
  
  private void actualiceView() {
    (new Handler()).postDelayed(new Runnable() {
          final RemoteDiagnosticActivity this$0;
          
          public void run() {
            if (RemoteDiagnosticActivity.flagProgress) {
              RemoteDiagnosticActivity.this.progress.setVisibility(8);
              RemoteDiagnosticActivity.this.tvProgress.setVisibility(8);
            } 
          }
        },  2000L);
  }
  
  private void afterRefresh() {
    _activity.runOnUiThread(new Runnable() {
          final RemoteDiagnosticActivity this$0;
          
          public void run() {
            RemoteDiagnosticActivity.access$002(false);
            RemoteDiagnosticActivity.this.swipe_dtc.setRefreshing(false);
            RemoteDiagnosticActivity.this.lv_dtc.setClickable(true);
            RemoteDiagnosticActivity.this.lv_dtc.setEnabled(true);
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setEnabled(true);
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setClickable(true);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(true);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(true);
            RemoteDiagnosticActivity.this.swipe_dtc.setClickable(true);
            RemoteDiagnosticActivity.this.swipe_dtc.setEnabled(true);
            RemoteDiagnosticActivity.this.DTC_Selector.setClickable(true);
            RemoteDiagnosticActivity remoteDiagnosticActivity = RemoteDiagnosticActivity.this;
            remoteDiagnosticActivity.blockList(remoteDiagnosticActivity.DTC_Selector.isChecked());
          }
        });
    AlertOn();
  }
  
  private void assistanceCall() {
    GlobalMembers.btnpress = 2;
    if (GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
      if (!isBtAndPlatinumConnected()) {
        Utilities.sendIntentCallAction((Context)this);
      } else {
        if (iPreviousCallIndex == 0) {
          Intent intent = new Intent((Context)this, ActivityCall.class);
          intent.putExtra("Boton", "Asistencia");
          startActivityForResult(intent, 456);
          (new Thread(new TimerThread())).start();
        } 
        CallOrHangUp(Enums.Calls.AssistanceCallAndMessage);
      } 
    } else {
      Utilities.sendIntentCallAction((Context)this);
    } 
  }
  
  private void assistanceDTC() {
    if (this.wakeupcar_container.getVisibility() == 8 && this.wakeupcar_container != null)
      assistanceFunction(); 
  }
  
  private void assistanceFunction() {
    if (!GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
      if (!isBtAndPlatinumConnected()) {
        Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_btnasistenciamenu_onstar_dialog, 2131165443);
        final Dialog dialog = Utilities.simpleDialog((Context)this, null, global_lbl_asistencia_1, this.asistencia_global_popup_lbl_llamadaasistencia_2, true, this.global_popup_btn_llamar_1, false, global_popup_btn_no_1);
        this.btnAccept = (Button)dialog.findViewById(2131296343);
        this.btnCancel = (Button)dialog.findViewById(2131296344);
        this.btnAccept.setOnClickListener(new View.OnClickListener() {
              final RemoteDiagnosticActivity this$0;
              
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                RemoteDiagnosticActivity.this.assistanceCall();
                dialog.dismiss();
              }
            });
        this.btnCancel.setOnClickListener(new View.OnClickListener(this) {
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                dialog.dismiss();
              }
            });
        dialog.show();
        GlobalMembers.isShowingDialog = true;
      } else {
        assistanceCall();
      } 
    } else {
      final Dialog dialog = Utilities.simpleDialog((Context)this, 2131165443, global_lbl_asistencia_1, this.asistencia_global_popup_lbl_llamadaasistencia_2, true, this.global_popup_btn_llamar_1, false, global_popup_btn_no_1);
      ((Button)dialog.findViewById(2131296343)).setOnClickListener(new View.OnClickListener() {
            final RemoteDiagnosticActivity this$0;
            
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              RemoteDiagnosticActivity.this.assistanceCall();
              dialog.dismiss();
            }
          });
      ((Button)dialog.findViewById(2131296344)).setOnClickListener(new View.OnClickListener(this) {
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              dialog.dismiss();
            }
          });
      dialog.show();
      GlobalMembers.isShowingDialog = true;
    } 
  }
  
  private void changeOrientation(Configuration paramConfiguration) {
    int i = paramConfiguration.orientation;
    if (i == 1) {
      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
      layoutParams.weight = 0.14F;
      this.lin_dtc_device_info.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      layoutParams = new LinearLayout.LayoutParams(-1, 0);
      layoutParams.weight = 0.701F;
      this.frl_dtc_swipe.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      layoutParams = new LinearLayout.LayoutParams(-1, 0);
      layoutParams.weight = 0.04F;
      this.tip_dtc_layout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      layoutParams = new LinearLayout.LayoutParams(-1, 0);
      layoutParams.weight = 0.12F;
      this.lin_dtc_footer_buttons.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      if (GlobalMembers.activeTutorial)
        this.idTutorial.setVisibility(0); 
      if (this.wakeupcar_container.getVisibility() == 0) {
        Drawable drawable;
        String str;
        if (_status.equals("3")) {
          drawable = Utilities.getDrawableFromConfigList(_context, DrawableResourcesVO.falla_wakeup, 2131165411);
          str = Utilities.getStringFromConfigList(_context, stringsResourcesVO.wakeupcar_estatus_error_header, 2131690496);
        } else {
          drawable = Utilities.getDrawableFromConfigList(_context, DrawableResourcesVO.ic_timeout_wakeup, 2131165544);
          str = Utilities.getStringFromConfigList(_context, stringsResourcesVO.wizard_dicas_lbl_aguardenotificacion_9, 2131690511);
        } 
        this.tvTextWake.setText(str);
        this.tvTextWake.setGravity(17);
        this.ivIconWake.setVisibility(0);
        this.ivIconWake.setImageDrawable(drawable);
        this.tvTextReintent.setVisibility(0);
      } 
    } else if (i == 2) {
      LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
      layoutParams.weight = 0.26F;
      this.lin_dtc_device_info.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      layoutParams = new LinearLayout.LayoutParams(-1, 0);
      layoutParams.weight = 0.43F;
      this.frl_dtc_swipe.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      layoutParams = new LinearLayout.LayoutParams(-1, 0);
      layoutParams.weight = 0.06F;
      this.tip_dtc_layout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      layoutParams = new LinearLayout.LayoutParams(-1, 0);
      layoutParams.weight = 0.25F;
      this.lin_dtc_footer_buttons.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      if (GlobalMembers.activeTutorial) {
        this.idTutorial.setVisibility(8);
        ShowViewElement showViewElement = this.show;
        if (showViewElement != null)
          showViewElement.removeView(this, true); 
      } 
      if (this.wakeupcar_container.getVisibility() == 0) {
        if (flagProgress) {
          this.progress.setVisibility(8);
          this.tvProgress.setVisibility(8);
        } else {
          this.dotProgressBar.setVisibility(8);
        } 
        this.ivIconWake.setVisibility(8);
        this.tvTextReintent.setVisibility(8);
      } 
    } 
  }
  
  private void confirmActionDialog() {
    try {
      Dialog dialog = Utilities.simpleDialog(_context, null, global_popup_lbl_aviso_1, global_lbl_warnUserDTC, true, global_popup_btn_aceptar_1, true, notificaciones_main_lbl_cancelar_1, 20.0F, 20.0F);
      this.buttonOk = (Button)dialog.findViewById(2131296343);
      Button button = this.buttonOk;
      View.OnClickListener onClickListener = new View.OnClickListener() {
          final RemoteDiagnosticActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            RemoteDiagnosticActivity.this.whileRefreshingInActionProcess();
            RemoteDiagnosticActivity.this.goingToActionProcess();
            RemoteDiagnosticActivity.this.SetVisibleDialogProcess();
            RemoteDiagnosticActivity.access$802(true);
            RemoteDiagnosticActivity remoteDiagnosticActivity = RemoteDiagnosticActivity.this;
            RemoteDiagnosticActivity.access$902(remoteDiagnosticActivity, CustomActionButtonFactory.getActionButton(Enums.Services.ActionAssistance, RemoteDiagnosticActivity._context, remoteDiagnosticActivity.cab_dtc_assistance, 0, 1));
            remoteDiagnosticActivity = RemoteDiagnosticActivity.this;
            RemoteDiagnosticActivity.access$1002(remoteDiagnosticActivity, CustomActionButtonFactory.getActionButton(Enums.Services.ActionSchedule, RemoteDiagnosticActivity._context, remoteDiagnosticActivity.cab_dtc_schedule, 0, 1));
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setEnabled(false);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(false);
          }
        };
      super(this, dialog);
      button.setOnClickListener(onClickListener);
      this.buttonNOK = (Button)dialog.findViewById(2131296344);
      button = this.buttonNOK;
      onClickListener = new View.OnClickListener() {
          final RemoteDiagnosticActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            RemoteDiagnosticActivity.this.afterRefresh();
            dialog.dismiss();
          }
        };
      super(this, dialog);
      button.setOnClickListener(onClickListener);
      dialog.show();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: dialogBackALert", exception.getMessage());
    } 
  }
  
  private void displayDTCInfo(List<RemoteDiagnosticVO> paramList) {
    this.lbl_dtc_no_info.setVisibility(8);
    this.frl_dtc_no_info.setVisibility(8);
    remoteAdapter = new RemoteDiagnosticAdapter(paramList, _context);
    this.lv_dtc.setAdapter((ListAdapter)remoteAdapter);
    this.lbl_dtc_update_time.setText(getDateLabel(((RemoteDiagnosticVO)paramList.get(0)).getDtcDate()));
  }
  
  private void eventNotification() {
    if (getIntent().getExtras() != null && getIntent().getExtras().getInt("Acc") == Enums.Services.DTCNotification.GetCode()) {
      int i = Utilities.getDevicePositionByDeviceId(getIntent().getExtras().getString("DeviceId"));
      if (i != -1) {
        UserPreferenceVO userPreferenceVO = dbFunctions.getUserPreference(GlobalMembers.userLogged);
        UserDevicesVO userDevicesVO = rtApp.getmDeviceUserList().get(i);
        dbFunctions.addVehicleSelected(getApplicationContext(), userPreferenceVO.getUser(), userDevicesVO);
        Utilities.updateVehicleSelected(_context, userPreferenceVO.getUser(), userDevicesVO);
      } 
      if (Utilities.isValidActionVehicle(rtApp, TAG)) {
        if (dbFunctions.getStatusVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(rtApp, TAG).getDeviceId()).equals("1")) {
          Context context = _context;
          Toast.makeText(context, context.getString(2131690277), 1).show();
          finish();
          return;
        } 
        getIntent().removeExtra("Acc");
        getIntent().removeExtra("DeviceId");
      } else {
        Context context = _context;
        Toast.makeText(context, context.getString(2131690277), 1).show();
        finish();
      } 
    } 
  }
  
  public static void executeGetDtcs_Task(Context paramContext, String paramString) {
    MainActivity.taskSet.executeDtcs_Task(new Dtc_Interface() {
          public void onFail(String param1String) {}
          
          public void onRunning() {}
          
          public void onSuccess(List<RemoteDiagnosticVO> param1List) {}
        },  paramContext, paramString);
  }
  
  private void formattedFont() {
    this.tfLouis = onstarApplication.getTypeface((Context)this, rtApp.fontPathLouisRegular);
    this.tfLouisItalic = onstarApplication.getTypeface((Context)this, rtApp.fontPathLouisItalic);
    this.lbl_dtc_device_name.setTypeface(this.tfLouis);
    this.lbl_dtc_update_time.setTypeface(this.tfLouisItalic);
    this.lbl_dtc_title.setTypeface(this.tfLouis);
    String str = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_lbl_accionpidinfovehiculo_1, 2131689883);
    this.lbl_dtc_title.setText(str);
    str = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.pid_main_lbl_arrastre_3, 2131690245);
    this.lbl_swipe_update.setText(str);
    this.lbl_swipe_update.setTypeface(this.tfLouisItalic);
    str = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_lbl_vehiculo_mantenimiento, 2131689757);
    this.txt_dtc.setText(str);
    this.DTC_Selector.setTextOff(global_popup_btn_no_1);
    str = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_popup_btn_si_1, 2131689952);
    this.DTC_Selector.setTextOn(str);
    str = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_main_lbl_descripciondtc, 2131689758);
    this.lbl_dtc_no_info.setText(str);
    str = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_main_lbl_descripciondtc2, 2131689759);
    this.lbl_dtc_no_info2.setText(str);
    Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_main_lbl_descripciondtc3, 2131689760);
    str = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_main_lbl_requiereconexion, 2131689764);
    this.tip_dtc.setText(str);
    str = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_popup_lbl_tituloinfodtc, 2131689774);
    this.tittleTextStatus.setText(str);
    this.StatusTextMessage.setText(dtc_popup_lbl_proceso);
  }
  
  private static String getDateLabel(String paramString) {
    String str2 = "";
    String str1 = str2;
    try {
      if (!paramString.isEmpty()) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(paramString.substring(0, 4));
        stringBuilder.append("-");
        stringBuilder.append(paramString.substring(4, 6));
        stringBuilder.append("-");
        stringBuilder.append(paramString.substring(6));
        String[] arrayOfString = Utilities.getUTCToNormalDate(stringBuilder.toString(), "dd/MM/yyyy HH:mm").split(" ");
        String str = str2;
        if (arrayOfString.length > 1) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append(pid_popup_lbl_actualizadofecha_1);
          stringBuilder1.append("\n");
          stringBuilder1.append(arrayOfString[0]);
          stringBuilder1.append("\n");
          stringBuilder1.append(arrayOfString[1]);
          String str3 = stringBuilder1.toString();
        } 
      } 
    } catch (android.content.res.Resources.NotFoundException notFoundException) {
      Utilities.escribeArchivo("", "Error fecha", "getDateLabel");
      str1 = str2;
    } 
    return str1;
  }
  
  public static ManagerNotificationPlatinum getmNPlatinum() {
    return mNPlatinum;
  }
  
  public static boolean isBtAndPlatinumConnected() {
    boolean bool;
    BluetoothServiceRT bluetoothServiceRT = mChatService;
    if (bluetoothServiceRT != null && bluetoothServiceRT.getState() == 3) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void onCreateDisplayInfo() {
    this.lbl_dtc_no_info.setVisibility(8);
    this.frl_dtc_no_info.setVisibility(8);
    getInfoFromDB();
  }
  
  private void openDTCWeb(int paramInt) {
    String str2 = rtApp.getUserAccessData()[0];
    String str4 = rtApp.getUserAccessData()[1];
    String str3 = GlobalMembers.codeCountry;
    String str1 = Utilities.getLastKnownDeviceSelected(rtApp, TAG).getSerialnumber();
    (new ExecuteDTCWS((Context)this, true, null, GlobalMembers.URL_DTC_LocatorWebApp)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str2, str4, str3, str1, String.valueOf(paramInt) });
  }
  
  private void resetWakeUpCar() {
    GlobalMembers.wakeUpCar = true;
    Drawable drawable = Utilities.getDrawableFromConfigList(_context, DrawableResourcesVO.ic_login_icon_static, 2131165486);
    this.ivIconWake.setImageDrawable(drawable);
    String str = Utilities.getStringFromConfigList(_context, stringsResourcesVO.wakeupcar_estatus, 2131690494);
    this.tvTextWake.setText(str);
    this.cab_dtc_assistance = CustomActionButtonFactory.getActionButton(Enums.Services.ActionAssistance, _context, this.cab_dtc_assistance, 1, 1);
    this.cab_dtc_schedule = CustomActionButtonFactory.getActionButton(Enums.Services.ActionSchedule, _context, this.cab_dtc_schedule, 1, 1);
    this.cab_dtc_assistance.setEnabled(true);
    this.cab_dtc_schedule.setEnabled(true);
    if (flagProgress) {
      Handler handler = handler;
      if (handler != null) {
        handler.removeCallbacks(runnable);
        if (Build.VERSION.SDK_INT >= 23) {
          this.tvProgress.setTextColor(_activity.getResources().getColor(2131034285, _activity.getTheme()));
        } else {
          this.tvProgress.setTextColor(_activity.getResources().getColor(2131034285));
        } 
        this.progress.setProgress(100.0F);
        this.progress.setText("");
        this.tvProgress.setText("100%");
        contCircular = 1;
        actualiceView();
      } 
    } 
  }
  
  private void scheduleFunction() {
    if (NetUtilities.validateNetwork((Context)this, false)) {
      try {
        Intent intent = new Intent();
        this("android.intent.action.VIEW", Uri.parse(GlobalMembers.URL_DTC_ChevroletCOAgendar));
        intent.addFlags(268435456);
        getApplication().startActivity(intent);
      } catch (Exception exception) {
        Toast.makeText((Context)this, this.global_lbl_acciondescfallared_1, 1).show();
      } 
    } else {
      Toast.makeText((Context)this, this.global_lbl_acciondescfallared_1, 1).show();
    } 
  }
  
  private void setAlert(int paramInt, Enums.Services paramServices) {
    boolean bool;
    if (paramServices.equals(Enums.Services.ActionSchedule)) {
      bool = GlobalMembers.notificacionesAgendamiento;
    } else {
      bool = false;
    } 
    this.cab_dtc_schedule.setBadgeNumber(bool);
    this.cab_dtc_schedule.showActionStatus(paramInt);
  }
  
  public static void setSwipeGesture(Boolean paramBoolean) {
    isSwipeGesture = paramBoolean;
  }
  
  private boolean verifyActionRunning() {
    boolean bool;
    if (MainActivity.onOpenDoors.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onFindMe.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onPID.booleanValue() || MainActivity.onDTC.booleanValue()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool)
      try {
        Dialog dialog = Utilities.simpleDialog(_context, null, global_popup_lbl_aviso_1, global_popup_lbl_accionencurso_1, true, global_popup_btn_ok_1, false, global_popup_btn_no_1, 20.0F, 20.0F);
        this.buttonOk = (Button)dialog.findViewById(2131296343);
        Button button = this.buttonOk;
        View.OnClickListener onClickListener = new View.OnClickListener() {
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              dialog.dismiss();
            }
          };
        super(this, dialog);
        button.setOnClickListener(onClickListener);
        dialog.show();
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error: dialogBackALert", exception.getMessage());
      }  
    return bool;
  }
  
  private void whileRefreshing() {
    _activity.runOnUiThread(new Runnable() {
          final RemoteDiagnosticActivity this$0;
          
          public void run() {
            RemoteDiagnosticActivity.access$002(true);
            RemoteDiagnosticActivity.this.swipe_dtc.post(new Runnable() {
                  final RemoteDiagnosticActivity.null this$1;
                  
                  public void run() {
                    RemoteDiagnosticActivity.this.swipe_dtc.setRefreshing(true);
                  }
                });
            RemoteDiagnosticActivity.this.lv_dtc.setClickable(false);
            RemoteDiagnosticActivity.this.lv_dtc.setEnabled(false);
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setEnabled(false);
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setClickable(false);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(false);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(false);
            RemoteDiagnosticActivity.this.swipe_dtc.setClickable(false);
            RemoteDiagnosticActivity.this.swipe_dtc.setEnabled(false);
            RemoteDiagnosticActivity.this.DTC_Selector.setClickable(false);
          }
        });
  }
  
  private void whileRefreshingInActionProcess() {
    _activity.runOnUiThread(new Runnable() {
          final RemoteDiagnosticActivity this$0;
          
          public void run() {
            RemoteDiagnosticActivity.access$002(true);
            RemoteDiagnosticActivity.this.swipe_dtc.setRefreshing(false);
            RemoteDiagnosticActivity.this.lv_dtc.setClickable(false);
            RemoteDiagnosticActivity.this.lv_dtc.setEnabled(false);
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setEnabled(true);
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setClickable(true);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(true);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(true);
            RemoteDiagnosticActivity.this.swipe_dtc.setClickable(false);
            RemoteDiagnosticActivity.this.swipe_dtc.setEnabled(false);
            RemoteDiagnosticActivity.this.DTC_Selector.setClickable(false);
          }
        });
  }
  
  private void whileRefreshingWithThread() {
    _activity.runOnUiThread(new Runnable() {
          final RemoteDiagnosticActivity this$0;
          
          public void run() {
            RemoteDiagnosticActivity.access$002(true);
            RemoteDiagnosticActivity.this.swipe_dtc.post(new Runnable() {
                  final RemoteDiagnosticActivity.null this$1;
                  
                  public void run() {
                    RemoteDiagnosticActivity.this.swipe_dtc.setRefreshing(true);
                  }
                });
            RemoteDiagnosticActivity.this.lv_dtc.setClickable(false);
            RemoteDiagnosticActivity.this.lv_dtc.setEnabled(false);
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setEnabled(false);
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setClickable(false);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(false);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(false);
            RemoteDiagnosticActivity.this.swipe_dtc.setClickable(false);
            RemoteDiagnosticActivity.this.swipe_dtc.setEnabled(false);
            RemoteDiagnosticActivity.this.DTC_Selector.setClickable(false);
          }
        });
  }
  
  public void AlertOn() {
    if (GlobalMembers.redPoint)
      if (GlobalMembers.notificacionesAgendamiento != 0) {
        setAlert(8, Enums.Services.ActionSchedule);
      } else {
        setAlert(0, Enums.Services.ActionSchedule);
      }  
  }
  
  public void ChangedStatusSocketSwitch(final String statusSocket) {
    _activity.runOnUiThread(new Runnable() {
          final RemoteDiagnosticActivity this$0;
          
          final String val$statusSocket;
          
          public void run() {
            if (statusSocket.equals("1")) {
              RemoteDiagnosticActivity.access$2402(true);
              RemoteDiagnosticActivity.this.DTC_Selector.setChecked(true);
              RemoteDiagnosticActivity.access$2402(false);
              RemoteDiagnosticActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(RemoteDiagnosticActivity.rtApp, RemoteDiagnosticActivity.TAG).getDeviceId(), "1");
            } else {
              RemoteDiagnosticActivity.access$2402(true);
              RemoteDiagnosticActivity.this.DTC_Selector.setChecked(false);
              RemoteDiagnosticActivity.access$2402(false);
              RemoteDiagnosticActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(RemoteDiagnosticActivity.rtApp, RemoteDiagnosticActivity.TAG).getDeviceId(), "0");
            } 
          }
        });
  }
  
  public void GETStatusDTCSelector() {
    if (!this.DTC_Selector.isChecked()) {
      blockList(false);
    } else {
      blockList(true);
    } 
    if (NetUtilities.validateNetwork((Context)this, false)) {
      GetDTCSelector_task getDTCSelector_task = (GetDTCSelector_task)(new GetDTCSelector_task(_context, deviceInfo.getDeviceId(), this.progressDTCSelector, this.DTC_Selector)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
      blockList(true);
      getDTCSelector_task.setOnPostExecuteListenerDTCSelector(new GetDTCSelector_task.OnPostExecuteListenerDTCSelector() {
            final RemoteDiagnosticActivity this$0;
            
            public void onPostExecuteListener(String param1String) {
              String str = RemoteDiagnosticActivity.TAG;
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("GETStatusDTCSelector:");
              stringBuilder.append(param1String);
              Utilities.escribeArchivo(str, "GETStatusDTCSelector", stringBuilder.toString());
              if (param1String != null) {
                try {
                  if (param1String.split("\\|")[1].toString().equals("1")) {
                    RemoteDiagnosticActivity.access$2402(true);
                    RemoteDiagnosticActivity.this.DTC_Selector.setChecked(true);
                    RemoteDiagnosticActivity.access$2402(false);
                    RemoteDiagnosticActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(RemoteDiagnosticActivity.rtApp, RemoteDiagnosticActivity.TAG).getDeviceId(), "1");
                  } else {
                    RemoteDiagnosticActivity.access$2402(true);
                    RemoteDiagnosticActivity.this.DTC_Selector.setChecked(false);
                    RemoteDiagnosticActivity.access$2402(false);
                    RemoteDiagnosticActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(RemoteDiagnosticActivity.rtApp, RemoteDiagnosticActivity.TAG).getDeviceId(), "0");
                  } 
                  if (!RemoteDiagnosticActivity.this.DTC_Selector.isChecked()) {
                    RemoteDiagnosticActivity.this.blockList(false);
                  } else {
                    RemoteDiagnosticActivity.this.blockList(true);
                  } 
                } catch (Exception exception) {
                  RemoteDiagnosticActivity.this.setLastKnowStatusDTCSelector();
                } 
              } else {
                RemoteDiagnosticActivity.this.setLastKnowStatusDTCSelector();
              } 
            }
          });
    } else {
      setLastKnowStatusDTCSelector();
    } 
  }
  
  public void SetResetDialogProcess() {
    (new CountDownTimer(1000L, 900L) {
        final RemoteDiagnosticActivity this$0;
        
        public void onFinish() {
          GlobalMembers.DTCCustomActionButton = RemoteDiagnosticActivity.this.CustomActionButton_status;
          RemoteDiagnosticActivity.this.StatusTextMessage.setText(RemoteDiagnosticActivity.dtc_popup_lbl_proceso);
          GlobalMembers.DTCCustomActionButton.showActionStatus(1);
          RemoteDiagnosticActivity.isdialog_action_process_frame_visible = false;
          RemoteDiagnosticActivity.Process_dialog_DTC_finished = false;
          RemoteDiagnosticActivity.this.TextCliked.setText("");
        }
        
        public void onTick(long param1Long) {
          RemoteDiagnosticActivity.this.afterRefresh();
          RemoteDiagnosticActivity.this.dialog_action_process_frame.startAnimation(RemoteDiagnosticActivity.SetAnimation(2130771982));
          RemoteDiagnosticActivity.this.dialog_action_process_frame.setVisibility(8);
        }
      }).start();
  }
  
  public void SetVisibleDialogProcess() {
    GlobalMembers.DTCCustomActionButton = this.CustomActionButton_status;
    this.dialog_action_process_frame.setVisibility(0);
    this.TextCliked.setText("");
    this.dialog_action_process_frame.startAnimation(SetAnimation(2130771981));
    this.alertcontaineractionprocess.startAnimation(SetAnimation(2130771989));
    GlobalMembers.DTCCustomActionButton.showActionStatus(1);
    isdialog_action_process_frame_visible = true;
    Process_dialog_DTC_finished = false;
  }
  
  public void ShowDialog48DTC() {
    Drawable drawable = Utilities.getDrawableFromConfigList(_context, DrawableResourcesVO.ic_launcher2, 2131165484);
    final Dialog dialog = Utilities.simpleDialog(_context, drawable, global_popup_lbl_aviso_1, dtc_popup_lbl_avisodesactivarDTC, false, global_popup_btn_no_1, true, global_popup_btn_aceptar_1, false);
    this.btnNOK = (Button)dialog.findViewById(2131296344);
    this.btnNOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
  }
  
  public void ShowWakeupCar() {
    Animation animation = AnimationUtils.loadAnimation(_context, 2130771983);
    _activity.getWindow().addFlags(128);
    ((NotificationManager)_context.getApplicationContext().getSystemService("notification")).cancelAll();
    this.wakeupcar_container.setAnimation(animation);
    this.wakeupcar_container.setVisibility(0);
    this.wakeupcar_container.setBackgroundResource(2131165671);
    Drawable drawable = Utilities.getDrawableFromConfigList(_context, DrawableResourcesVO.ic_login_icon_static, 2131165486);
    this.ivIconWake.setImageDrawable(drawable);
    String str = Utilities.getStringFromConfigList(_context, stringsResourcesVO.wakeupcar_estatus, 2131690494);
    this.tvTextWake.setText(str);
    if (flagProgress) {
      this.progress.setVisibility(0);
      this.progress.setState(1);
      this.progress.setText("0%");
      this.tvProgress.setVisibility(0);
      handler = new Handler();
      runnable = new Runnable() {
          final RemoteDiagnosticActivity this$0;
          
          public void run() {
            double d = RemoteDiagnosticActivity.contCircular * 0.8D;
            if (d <= 98.0D) {
              if (d >= 48.0D) {
                if (Build.VERSION.SDK_INT >= 23) {
                  RemoteDiagnosticActivity.this.tvProgress.setTextColor(RemoteDiagnosticActivity._activity.getResources().getColor(2131034285, RemoteDiagnosticActivity._activity.getTheme()));
                } else {
                  RemoteDiagnosticActivity.this.tvProgress.setTextColor(RemoteDiagnosticActivity._activity.getResources().getColor(2131034285));
                } 
              } else if (Build.VERSION.SDK_INT >= 23) {
                RemoteDiagnosticActivity.this.tvProgress.setTextColor(RemoteDiagnosticActivity._activity.getResources().getColor(2131034170, RemoteDiagnosticActivity._activity.getTheme()));
              } else {
                RemoteDiagnosticActivity.this.tvProgress.setTextColor(RemoteDiagnosticActivity._activity.getResources().getColor(2131034170));
              } 
              TextView textView = RemoteDiagnosticActivity.this.tvProgress;
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append((int)d);
              stringBuilder.append("%");
              textView.setText(stringBuilder.toString());
              RemoteDiagnosticActivity.this.progress.setText("");
              RemoteDiagnosticActivity.this.progress.setProgress((float)d);
              RemoteDiagnosticActivity.access$2608();
            } 
            RemoteDiagnosticActivity.handler.postDelayed(this, 1200L);
          }
        };
      handler.post(runnable);
    } else {
      this.dotProgressBar.setVisibility(0);
      this.progress.setVisibility(8);
      this.tvProgress.setVisibility(8);
    } 
    this.tvTextReintent.setVisibility(8);
    this.cab_dtc_assistance = CustomActionButtonFactory.getActionButton(Enums.Services.ActionAssistance, _context, this.cab_dtc_assistance, 0, 1);
    this.cab_dtc_schedule = CustomActionButtonFactory.getActionButton(Enums.Services.ActionSchedule, _context, this.cab_dtc_schedule, 0, 1);
    this.cab_dtc_assistance.setEnabled(false);
    this.cab_dtc_schedule.setEnabled(false);
    if (config.orientation == 2) {
      if (flagProgress) {
        this.progress.setVisibility(8);
        this.tvProgress.setVisibility(8);
        handler.removeCallbacks(runnable);
      } else {
        this.dotProgressBar.setVisibility(8);
      } 
      this.ivIconWake.setVisibility(8);
    } else {
      this.ivIconWake.setVisibility(0);
    } 
  }
  
  public void StartRefresh() {
    whileRefreshing();
    consumingWS();
    isSwipeGesture = Boolean.valueOf(true);
  }
  
  public void UPDATEStatusDTCSelector(boolean paramBoolean) {
    if (!IsCheckStatusDTC_Selector) {
      if (NetUtilities.validateNetwork((Context)this, false, true)) {
        blockList(true);
        ((UpdateStatusDTCSelector_task)(new UpdateStatusDTCSelector_task(_context, deviceInfo.getDeviceId(), this.progressDTCSelector, this.DTC_Selector)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0])).setOnPostExecuteListenerUpdateDTCSelector(new UpdateStatusDTCSelector_task.OnPostExecuteListenerUpdateDTCSelector() {
              final RemoteDiagnosticActivity this$0;
              
              public void onPostExecuteListener(String param1String) {
                String str = RemoteDiagnosticActivity.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("UPDATEStatusDTCSelector:");
                stringBuilder.append(param1String);
                Utilities.escribeArchivo(str, "UPDATEStatusDTCSelector", stringBuilder.toString());
                if (param1String != null) {
                  try {
                    if (param1String.split("\\|")[1].toString().equals("1")) {
                      RemoteDiagnosticActivity.access$2402(true);
                      RemoteDiagnosticActivity.this.DTC_Selector.setChecked(true);
                      RemoteDiagnosticActivity.access$2402(false);
                      RemoteDiagnosticActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(RemoteDiagnosticActivity.rtApp, RemoteDiagnosticActivity.TAG).getDeviceId(), "1");
                      RemoteDiagnosticActivity.this.ShowDialog48DTC();
                    } else {
                      RemoteDiagnosticActivity.access$2402(true);
                      RemoteDiagnosticActivity.this.DTC_Selector.setChecked(false);
                      RemoteDiagnosticActivity.access$2402(false);
                      RemoteDiagnosticActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(RemoteDiagnosticActivity.rtApp, RemoteDiagnosticActivity.TAG).getDeviceId(), "0");
                    } 
                    if (!RemoteDiagnosticActivity.this.DTC_Selector.isChecked()) {
                      RemoteDiagnosticActivity.this.blockList(false);
                    } else {
                      RemoteDiagnosticActivity.this.blockList(true);
                    } 
                  } catch (Exception exception) {
                    DialogManager.getInstance().setContext(RemoteDiagnosticActivity._context);
                    DialogManager.getInstance().showDialog();
                    RemoteDiagnosticActivity.this.setLastKnowStatusDTCSelector();
                  } 
                } else {
                  DialogManager.getInstance().setContext(RemoteDiagnosticActivity._context);
                  DialogManager.getInstance().showDialog();
                  RemoteDiagnosticActivity.this.setLastKnowStatusDTCSelector();
                } 
              }
            });
      } else {
        this.DTC_Selector.setChecked(paramBoolean ^ true);
      } 
    } else if (paramBoolean) {
      blockList(true);
      dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(rtApp, TAG).getDeviceId(), "1");
    } else {
      blockList(false);
      dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(rtApp, TAG).getDeviceId(), "0");
    } 
  }
  
  public void blockList(boolean paramBoolean) {
    _activity.runOnUiThread(new Runnable() {
          final RemoteDiagnosticActivity this$0;
          
          final boolean val$blockAux;
          
          public void run() {
            RemoteDiagnosticActivity.this.swipe_dtc.setEnabled(blockAux);
            RemoteDiagnosticActivity.this.swipe_dtc.setClickable(blockAux);
          }
        });
  }
  
  public void changeView(Drawable paramDrawable, String paramString) {
    if (paramDrawable != null)
      this.ivIconWake.setImageDrawable(paramDrawable); 
    if (!paramString.equalsIgnoreCase(""))
      this.tvTextWake.setText(paramString); 
    String str = Utilities.getStringFromConfigList((Context)_activity, stringsResourcesVO.wakeupcar_estatus_error_txt, 2131690497);
    this.tvTextReintent.setVisibility(0);
    this.tvTextReintent.setText(str);
    this.ivIconWake.setVisibility(0);
    this.wakeupcar_container.setVisibility(8);
  }
  
  public void consumingWS() {
    MainActivity.taskSet.executeDtcs_Task(new Dtc_Interface() {
          final RemoteDiagnosticActivity this$0;
          
          public void onFail(String param1String) {
            RemoteDiagnosticActivity.this.getInfoFromDB();
            RemoteDiagnosticActivity.this.onCreateDisplayInfo();
          }
          
          public void onRunning() {}
          
          public void onSuccess(List<RemoteDiagnosticVO> param1List) {
            if (param1List != null && param1List.size() > 0) {
              RemoteDiagnosticActivity.this.displayDTCInfo(param1List);
              RemoteDiagnosticActivity.this.afterRefresh();
            } else {
              RemoteDiagnosticActivity.this.getInfoFromDB();
            } 
            RemoteDiagnosticActivity.this.onCreateDisplayInfo();
          }
        },  _context, null);
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
  
  public void getInfoFromDB() {
    remoteItemsList = dbFunctions.getDTCByDeviceId(deviceInfo.getDeviceId());
    List<RemoteDiagnosticVO> list = remoteItemsList;
    if (list != null && list.size() > 0) {
      displayDTCInfo(remoteItemsList);
      afterRefresh();
    } else {
      this.lbl_dtc_no_info.setVisibility(0);
      this.frl_dtc_no_info.setVisibility(0);
      this.lbl_dtc_update_time.setText("");
      afterRefresh();
    } 
  }
  
  public void goingToActionProcess() {
    String str5 = rtApp.getSessionKey();
    String str1 = rtApp.getUserAccessData()[1];
    String str4 = rtApp.getAccountID();
    String str2 = rtApp.getLocatorUserId();
    String str3 = Enums.Services.DTCAction.GetCodeString();
    MainActivity.onDTC = Boolean.valueOf(true);
    apPID = new ActionsProcess(_context, this.CustomActionButton_status, str3, deviceInfo.getDeviceId(), _activity, true);
    apPID.setOnPostExecuteListener(new ActionsProcess.OnPostExecuteListener() {
          final RemoteDiagnosticActivity this$0;
          
          public void onPostExecuteListener(ActionResultVO param1ActionResultVO) {
            RemoteDiagnosticActivity.access$802(false);
            RemoteDiagnosticActivity remoteDiagnosticActivity = RemoteDiagnosticActivity.this;
            RemoteDiagnosticActivity.access$902(remoteDiagnosticActivity, CustomActionButtonFactory.getActionButton(Enums.Services.ActionAssistance, RemoteDiagnosticActivity._context, remoteDiagnosticActivity.cab_dtc_assistance, 1, 1));
            remoteDiagnosticActivity = RemoteDiagnosticActivity.this;
            RemoteDiagnosticActivity.access$1002(remoteDiagnosticActivity, CustomActionButtonFactory.getActionButton(Enums.Services.ActionSchedule, RemoteDiagnosticActivity._context, remoteDiagnosticActivity.cab_dtc_schedule, 1, 1));
            RemoteDiagnosticActivity.this.cab_dtc_assistance.setEnabled(true);
            RemoteDiagnosticActivity.this.cab_dtc_schedule.setEnabled(true);
          }
        });
    apPID.setOnProgressUpdateListenerCustomActionButton(new ActionsProcess.OnProgressUpdateListenerCustomActionButton() {
          final RemoteDiagnosticActivity this$0;
          
          public void onProgressUpdateListenerCustomButton(CustomActionButton param1CustomActionButton) {
            GlobalMembers.DTCCustomActionButton = param1CustomActionButton;
            RemoteDiagnosticActivity.this.CustomActionButton_status.showActionStatus(GlobalMembers.DTCCustomActionButton.get_current_status());
            RemoteDiagnosticActivity.this.StatusTextMessage.setText(RemoteDiagnosticActivity.this.setStatusText(GlobalMembers.DTCCustomActionButton.get_current_status(), RemoteDiagnosticActivity._context));
            RemoteDiagnosticActivity.this.StatusTextMessage.startAnimation(RemoteDiagnosticActivity.SetAnimation(2130771980));
          }
        });
    apPID.executeOnExecutor(Executors.newSingleThreadExecutor(), (Object[])new String[] { str5, str1, str4, str2, str3, "" });
  }
  
  public void hideWakeupCar() {
    resetWakeUpCar();
  }
  
  public void onBackPressed() {
    if (!blockBackPressed) {
      super.onBackPressed();
      if (deviceInfo.getButtomActions().containsKey(Enums.Services.pid.GetCodeString())) {
        String str = this.previousClass;
        if (str != null) {
          if (!str.equals(PIDActivity.class.getSimpleName()))
            startActivity(new Intent((Context)this, PIDActivity.class)); 
        } else {
          startActivity(new Intent((Context)this, MainActivity.class));
        } 
      } else {
        startActivity(new Intent((Context)this, MainActivity.class));
      } 
      GlobalMembers.notificacionesDTC = 0;
    } 
  }
  
  public void onClick(View paramView) {
    switch (paramView.getId()) {
      default:
        return;
      case 2131296440:
        if (this.wakeupcar_container.getVisibility() == 8 && this.wakeupcar_container != null)
          scheduleFunction(); 
      case 2131296439:
        break;
    } 
    assistanceDTC();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    changeOrientation(paramConfiguration);
    config = paramConfiguration;
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427376);
    TAG = RemoteDiagnosticActivity.class.getSimpleName();
    rtApp = (onstarApplication)getApplicationContext();
    _activity = this;
    _context = (Context)this;
    dbFunctions = new DBFunctions(_context);
    eventNotification();
    config = getResources().getConfiguration();
    deviceInfo = Utilities.getLastKnownDeviceSelected(rtApp, TAG);
    this.progress = (AnimDownloadProgressButton)findViewById(2131296965);
    this.tvProgress = (TextView)findViewById(2131297164);
    this.dotProgressBar = (DotProgressBar)findViewById(2131296502);
    isOnPause = false;
    isSwipeGesture = Boolean.valueOf(false);
    stringsResourcesVO = new StringsResourcesVO();
    this.global_lbl_acciondescfallared_1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    pid_popup_lbl_actualizadofecha_1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.pid_popup_lbl_actualizadofecha_1, 2131690260);
    dtc_popup_lbl_proceso = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_popup_lbl_proceso, 2131689773);
    dtc_popup_lbl_cliquepararesultado = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_popup_lbl_cliquepararesultado, 2131689770);
    global_popup_btn_aceptar_1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    this.asistencia_global_popup_lbl_llamadaasistencia_2 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
    this.global_popup_btn_llamar_1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_popup_btn_llamar_1, 2131689948);
    global_lbl_asistencia_1 = Utilities.getStringFromConfigList(_context, stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    global_popup_btn_no_1 = Utilities.getStringFromConfigList(_context, stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    global_popup_lbl_aviso_1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    dtc_popup_lbl_avisodesactivarDTC = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_popup_lbl_avisodesactivarDTC, 2131689768);
    dtc_popup_lbl_ejecutado = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_popup_lbl_ejecutado, 2131689771);
    dtc_popup_lbl_noejecutado = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.dtc_popup_lbl_noejecutado, 2131689772);
    global_lbl_acciondescposiblenotif_1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_lbl_acciondescposiblenotif_1, 2131689867);
    global_popup_btn_ok_1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
    global_popup_lbl_accionencurso_1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
    notificaciones_main_lbl_cancelar_1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    global_lbl_warnUserDTC = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_lbl_warnUserDTC, 2131689936);
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
      try {
        this.previousClass = paramBundle.getString("previousClass");
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error Bundle", exception.getMessage());
        this.previousClass = "";
      }  
    this.lbl_dtc_device_name = (TextView)findViewById(2131296737);
    this.lbl_dtc_update_time = (TextView)findViewById(2131296743);
    this.lbl_dtc_title = (TextView)findViewById(2131296742);
    this.lbl_swipe_update = (TextView)findViewById(2131296785);
    this.lbl_dtc_no_info = (TextView)findViewById(2131296740);
    this.lbl_dtc_no_info2 = (TextView)findViewById(2131296741);
    TextView textView = (TextView)findViewById(2131296765);
    this.StatusTextMessage = (TextView)findViewById(2131296308);
    this.TextCliked = (TextView)findViewById(2131296309);
    this.swipe_dtc = (SwipeRefreshLayout)findViewById(2131297064);
    this.lin_dtc_device_info = (LinearLayout)findViewById(2131296801);
    this.lin_dtc_footer_buttons = (LinearLayout)findViewById(2131296802);
    this.tip_dtc_layout = (LinearLayout)findViewById(2131297114);
    this.lv_dtc = (ListView)findViewById(2131296868);
    this.frl_dtc_no_info = (FrameLayout)findViewById(2131296554);
    this.frl_dtc_swipe = (FrameLayout)findViewById(2131296555);
    this.cab_dtc_assistance = (CustomActionButton)findViewById(2131296439);
    this.cab_dtc_schedule = (CustomActionButton)findViewById(2131296440);
    this.CustomActionButton_status = (CustomActionButton)findViewById(2131296271);
    this.dialog_action_process_frame = (RelativeLayout)findViewById(2131296496);
    this.alertcontaineractionprocess = (LinearLayout)findViewById(2131296374);
    this.txt_dtc = (TextView)findViewById(2131297196);
    this.DTC_Selector = (SwitchCompat)findViewById(2131296272);
    this.progressDTCSelector = (ProgressBar)findViewById(2131296962);
    this.tip_dtc = (TextView)findViewById(2131297113);
    this.tittleTextStatus = (TextView)findViewById(2131297131);
    this.wakeupcar_container = (RelativeLayout)findViewById(2131297248);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_login_icon_static, 2131165486);
    this.ivIconWake = (ImageView)findViewById(2131296672);
    this.ivIconWake.setImageDrawable(drawable);
    this.tvTextWake = (TextView)findViewById(2131297167);
    this.tvTextReintent = (TextView)findViewById(2131297168);
    this.cab_dtc_assistance = CustomActionButtonFactory.getActionButton(Enums.Services.ActionAssistance, _context, this.cab_dtc_assistance, 1, 1);
    this.cab_dtc_schedule.setVisibility(8);
    this.cab_dtc_schedule = CustomActionButtonFactory.getActionButton(Enums.Services.ActionSchedule, _context, this.cab_dtc_schedule, 1, 1);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_red_dot_base, 2131165513);
    this.CustomActionButton_status.set_drw_action_image(drawable);
    this.CustomActionButton_status.setHideLabel(true);
    this.cab_dtc_assistance.setOnClickListener(this);
    this.cab_dtc_schedule.setOnClickListener(this);
    this.swipe_dtc.setOnRefreshListener(this.swipeRefreshListener);
    this.swipe_dtc.setColorSchemeResources(new int[] { 2131034155 });
    this.imageView = (ImageView)findViewById(2131296664);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.pidcar_info, 2131165641);
    this.imageView.setImageDrawable(drawable);
    if (GlobalMembers.activeTutorial) {
      this.idTutorial = (LinearLayout)findViewById(2131296607);
      this.idTutorial.setOnClickListener(this.clickTutorial);
      this.idTutorial.setVisibility(0);
    } 
    String str = deviceInfo.getName();
    this.lbl_dtc_device_name.setText(str);
    recivedNotification();
    formattedFont();
    remoteItemsList = new ArrayList<RemoteDiagnosticVO>();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT");
    registerReceiver(this.mReceiver, intentFilter);
    GlobalMembers.notificacionesDTC = 0;
    dbFunctions.readerNotificationDTC(Utilities.getLastKnownDeviceSelected(rtApp, TAG).getDeviceId());
    changeOrientation(getResources().getConfiguration());
    this.lv_dtc.setOnItemClickListener(this);
    this.dialog_action_process_frame.setOnClickListener(new View.OnClickListener() {
          final RemoteDiagnosticActivity this$0;
          
          public void onClick(View param1View) {
            if (RemoteDiagnosticActivity.Process_dialog_DTC_finished) {
              RemoteDiagnosticActivity.access$002(false);
              RemoteDiagnosticActivity.this.SetResetDialogProcess();
            } 
          }
        });
    onCreateDisplayInfo();
    if (isdialog_action_process_frame_visible) {
      whileRefreshingInActionProcess();
      CustomActionButton customActionButton = GlobalMembers.DTCCustomActionButton;
      if (customActionButton != null) {
        this.CustomActionButton_status.showActionStatus(customActionButton.get_current_status());
        this.StatusTextMessage.setText(setStatusText(GlobalMembers.DTCCustomActionButton.get_current_status(), _context));
        this.StatusTextMessage.startAnimation(SetAnimation(2130771980));
      } 
      this.dialog_action_process_frame.setVisibility(0);
    } else if (NetUtilities.validateNetwork((Context)this, false)) {
      whileRefreshingWithThread();
      MainActivity.taskSet.getDtcs_Task_Status(new DtcStatus_Interface(this) {
            public void onRunning() {
              RemoteDiagnosticActivity.isrunning = true;
            }
          });
      if (!isrunning) {
        StartRefresh();
        isrunning = false;
      } 
    } 
    if (deviceInfo.getButtomActions().containsKey(Enums.Services.DTCAction.GetCodeString())) {
      GETStatusDTCSelector();
      this.DTC_Selector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            final RemoteDiagnosticActivity this$0;
            
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
              RemoteDiagnosticActivity.this.UPDATEStatusDTCSelector(param1Boolean);
            }
          });
    } else {
      this.DTC_Selector.setVisibility(8);
      this.progressDTCSelector.setVisibility(8);
      this.txt_dtc.setVisibility(8);
      blockList(false);
    } 
    AlertOn();
    GlobalMembers.wakeUpCar = true;
    this.wakeupcar_container.setOnClickListener(new View.OnClickListener() {
          final RemoteDiagnosticActivity this$0;
          
          public void onClick(View param1View) {
            if (!GlobalMembers.wakeUpCar)
              RemoteDiagnosticActivity.this.hideWakeupCar(); 
          }
        });
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    getMenuInflater().inflate(2131492874, paramMenu);
    ActionBar actionBar = getActionBar();
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    View view = getLayoutInflater().inflate(2131427419, null);
    if (actionBar != null) {
      actionBar.setBackgroundDrawable((Drawable)new ColorDrawable(-1));
      actionBar.setCustomView(view, new ActionBar.LayoutParams(-2, -1, 17));
      this.ivHeader = (ImageView)view.findViewById(2131296591);
      this.ivHeader.setImageDrawable(drawable);
      actionBar.setDisplayOptions(18);
      actionBar.setDisplayShowHomeEnabled(false);
      actionBar.setDisplayShowTitleEnabled(false);
    } 
    return true;
  }
  
  protected void onDestroy() {
    super.onDestroy();
    dbFunctions.readerNotificationDTC(Utilities.getLastKnownDeviceSelected(rtApp, TAG).getDeviceId());
    unregisterReceiver(this.mReceiver);
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    if (!this.DTC_Selector.isChecked())
      openDTCWeb(((RemoteDiagnosticVO)remoteItemsList.get(paramInt)).getDtcGroupId()); 
  }
  
  protected void onNewIntent(Intent paramIntent) {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    recivedNotification();
  }
  
  protected void onPause() {
    super.onPause();
    isOnPause = true;
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle) {
    super.onRestoreInstanceState(paramBundle);
    this.stateTutorial = paramBundle.getBoolean(this.STATE_TUTORIAL);
    if (this.wakeupcar_container.getVisibility() == 8 && this.wakeupcar_container != null && this.stateTutorial)
      showTutorial(); 
  }
  
  protected void onResume() {
    AlertOn();
    super.onResume();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBoolean(this.STATE_TUTORIAL, this.stateTutorial);
  }
  
  public void recivedNotification() {
    Bundle bundle = getIntent().getExtras();
    if (bundle != null || GlobalMembers.onfollowmeActivated) {
      int i = Utilities.getDevicePositionByDeviceId(bundle.getString("PUSH_NOTIFICATION_DEVICE_ID"));
      if (i != -1) {
        UserPreferenceVO userPreferenceVO = dbFunctions.getUserPreference(GlobalMembers.userLogged);
        UserDevicesVO userDevicesVO = rtApp.getmDeviceUserList().get(i);
        dbFunctions.addVehicleSelected(getApplicationContext(), userPreferenceVO.getUser(), userDevicesVO);
        Utilities.updateVehicleSelected(_context, userPreferenceVO.getUser(), userDevicesVO);
      } 
      this.swipeRefreshListener.onRefresh();
    } 
  }
  
  public void setActionButtons() {
    try {
      Dialog dialog = Utilities.simpleDialog(_context, null, global_popup_lbl_aviso_1, global_popup_lbl_accionencurso_1, true, global_popup_btn_ok_1, false, global_popup_btn_no_1, 20.0F, 20.0F);
      this.buttonOk = (Button)dialog.findViewById(2131296343);
      Button button = this.buttonOk;
      View.OnClickListener onClickListener = new View.OnClickListener() {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        };
      super(this, dialog);
      button.setOnClickListener(onClickListener);
      dialog.show();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: dialogBackALert", exception.getMessage());
    } 
  }
  
  public void setDisableOrientation() {
    OrientationManager.unlockOrientation(this);
  }
  
  public void setLastKnowStatusDTCSelector() {
    if (dbFunctions.getVehicleDtcStatus(Utilities.getLastKnownDeviceSelected(rtApp, TAG).getDeviceId()).equals("0")) {
      IsCheckStatusDTC_Selector = true;
      this.DTC_Selector.setChecked(false);
      IsCheckStatusDTC_Selector = false;
      blockList(false);
      dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(rtApp, TAG).getDeviceId(), "0");
    } else {
      IsCheckStatusDTC_Selector = true;
      this.DTC_Selector.setChecked(true);
      IsCheckStatusDTC_Selector = false;
      blockList(true);
      dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(rtApp, TAG).getDeviceId(), "1");
    } 
  }
  
  public void setPosition(int paramInt) {}
  
  public void setPressTuto(boolean paramBoolean) {
    this.pressTuto = paramBoolean;
  }
  
  public void setStateTutorial(boolean paramBoolean) {
    this.stateTutorial = paramBoolean;
  }
  
  public void setStatus(String paramString) {
    Drawable drawable;
    String str;
    _status = paramString;
    GlobalMembers.wakeUpCar = false;
    this.cab_dtc_assistance = CustomActionButtonFactory.getActionButton(Enums.Services.ActionAssistance, _context, this.cab_dtc_assistance, 1, 1);
    this.cab_dtc_schedule = CustomActionButtonFactory.getActionButton(Enums.Services.ActionSchedule, _context, this.cab_dtc_schedule, 1, 1);
    this.cab_dtc_assistance.setEnabled(true);
    this.cab_dtc_schedule.setEnabled(true);
    if (paramString.equals("3")) {
      drawable = Utilities.getDrawableFromConfigList(_context, DrawableResourcesVO.falla_wakeup, 2131165411);
      str = Utilities.getStringFromConfigList(_context, stringsResourcesVO.wakeupcar_estatus_error_header, 2131690496);
    } else {
      drawable = Utilities.getDrawableFromConfigList(_context, DrawableResourcesVO.ic_timeout_wakeup, 2131165544);
      str = Utilities.getStringFromConfigList(_context, stringsResourcesVO.wizard_dicas_lbl_aguardenotificacion_9, 2131690511);
    } 
    this.tvTextWake.setText(str);
    this.tvTextWake.setGravity(17);
    if (config.orientation == 1) {
      this.ivIconWake.setImageDrawable(drawable);
      String str1 = Utilities.getStringFromConfigList(_context, stringsResourcesVO.wakeupcar_estatus_error_txt, 2131690497);
      actualiceView();
      changeView(drawable, str1);
    } 
    if (flagProgress) {
      handler.removeCallbacks(runnable);
      this.progress.setProgress(100.0F);
      this.progress.setText("100%");
      this.tvProgress.setText("100%");
      contCircular = 1;
      actualiceView();
    } else {
      this.dotProgressBar.setVisibility(8);
    } 
    changeView(drawable, str);
  }
  
  public String setStatusText(int paramInt, Context paramContext) {
    String str2 = dtc_popup_lbl_proceso;
    String str3 = dtc_popup_lbl_cliquepararesultado;
    Boolean bool = Boolean.valueOf(false);
    String str1 = str2;
    switch (paramInt) {
      default:
        str1 = str2;
        break;
      case 18:
        str1 = global_lbl_acciondescposiblenotif_1;
        getInfoFromDB();
        this.TextCliked.setText(str3);
        this.TextCliked.startAnimation(SetAnimation(2130771980));
        Process_dialog_DTC_finished = true;
        MainActivity.onDTC = bool;
        break;
      case 17:
        str1 = dtc_popup_lbl_noejecutado;
        getInfoFromDB();
        this.TextCliked.setText(str3);
        this.TextCliked.startAnimation(SetAnimation(2130771980));
        Process_dialog_DTC_finished = true;
        MainActivity.onDTC = bool;
        break;
      case 16:
        str1 = dtc_popup_lbl_noejecutado;
        getInfoFromDB();
        this.TextCliked.setText(str3);
        this.TextCliked.startAnimation(SetAnimation(2130771980));
        Process_dialog_DTC_finished = true;
        MainActivity.onDTC = bool;
        break;
      case 15:
        str1 = dtc_popup_lbl_ejecutado;
        getInfoFromDB();
        this.TextCliked.setText(str3);
        this.TextCliked.startAnimation(SetAnimation(2130771980));
        Process_dialog_DTC_finished = true;
        MainActivity.onDTC = bool;
        break;
      case 14:
        str1 = dtc_popup_lbl_ejecutado;
        getInfoFromDB();
        this.TextCliked.setText(str3);
        this.TextCliked.startAnimation(SetAnimation(2130771980));
        Process_dialog_DTC_finished = true;
        MainActivity.onDTC = bool;
        break;
      case 7:
        str1 = dtc_popup_lbl_noejecutado;
        break;
      case 6:
        str1 = dtc_popup_lbl_noejecutado;
        break;
      case 5:
        str1 = dtc_popup_lbl_noejecutado;
        break;
      case 4:
        str1 = dtc_popup_lbl_ejecutado;
        break;
      case 3:
        str1 = dtc_popup_lbl_ejecutado;
        break;
      case 0:
      case 1:
      case 2:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 19:
      case 20:
      case 21:
        break;
    } 
    return str1;
  }
  
  public ClassElements setViews() {
    ClassElements classElements = new ClassElements();
    String str2 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.tutorial_lbl_llamadaasistencia, 2131690449);
    Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.tutorial_lbl_agendarrevision, 2131690438);
    String str1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.tutorial_lbl_versistemasvehiculo, 2131690469);
    CustomActionButton customActionButton2 = this.cab_dtc_assistance;
    if (customActionButton2 != null)
      ShowViewElement.setValues(classElements, (View)customActionButton2, 0, str2, "rectaAbajo", 0, getResources().getInteger(2131361815), false, -getResources().getInteger(2131361814), -getResources().getInteger(2131361805), false); 
    CustomActionButton customActionButton1 = this.cab_dtc_schedule;
    if (customActionButton1 != null)
      ShowViewElement.setValues(classElements, (View)customActionButton1, 1, "", "solo", 0, 0, false, 0, 0, false); 
    SwipeRefreshLayout swipeRefreshLayout = this.swipe_dtc;
    if (swipeRefreshLayout != null)
      ShowViewElement.setValues(classElements, (View)swipeRefreshLayout, 2, str1, "solo", getResources().getInteger(2131361822), getResources().getInteger(2131361823), false, getResources().getInteger(2131361814), 0, false); 
    return classElements;
  }
  
  public void showTutorial() {
    ClassElements classElements = setViews();
    this.stateTutorial = true;
    this.pressTuto = false;
    this.show = new ShowViewElement(getApplicationContext(), this, classElements);
    this.show.setPages(1);
  }
  
  private class TimerThread implements Runnable {
    final RemoteDiagnosticActivity this$0;
    
    private TimerThread() {}
    
    public void run() {
      byte b = 0;
      while (true) {
        boolean bool1;
        boolean bool2 = true;
        if (b < 30) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        if (RemoteDiagnosticActivity.iPreviousCallIndex != 0)
          bool2 = false; 
        if ((bool1 & bool2) != 0) {
          try {
            Thread.sleep(1000L);
            b++;
          } catch (InterruptedException interruptedException) {
            Utilities.escribeArchivo(RemoteDiagnosticActivity.TAG, "Error: timer", interruptedException.toString());
          } 
          continue;
        } 
        RemoteDiagnosticActivity.this.runOnUiThread(new Runnable(this) {
              public void run() {
                Thread thread = Thread.currentThread();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(null.class.getSimpleName());
                stringBuilder.append(": ");
                stringBuilder.append(Thread.currentThread().getName());
                thread.setName(stringBuilder.toString());
                GlobalMembers.bStartingCall = false;
              }
            });
        return;
      } 
    }
  }
  
  class null implements Runnable {
    null(RemoteDiagnosticActivity this$0) {}
    
    public void run() {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(null.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      GlobalMembers.bStartingCall = false;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/pid/RemoteDiagnosticActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */