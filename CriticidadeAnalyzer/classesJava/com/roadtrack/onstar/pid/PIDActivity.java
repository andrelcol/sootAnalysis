package com.roadtrack.onstar.pid;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
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
import com.roadtrack.onstar.VO.PIDVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.async_tasks.tasks.GetDTCSelector_task;
import com.roadtrack.onstar.async_tasks.tasks.UpdateStatusDTCSelector_task;
import com.roadtrack.onstar.entities.ActiveCallEntity;
import com.roadtrack.onstar.new_ws.ExecuteCarInfoWeb;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.ui.dialogs.ActivityCall;
import com.roadtrack.onstar.utils.DialogManager;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.UserInterfaceUtils;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.viewTutorial.ClassElements;
import com.roadtrack.onstar.viewTutorial.ShowViewElement;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;

public class PIDActivity extends Activity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
  public static Activity Act;
  
  private static boolean IsCheckStatusDTC_Selector = false;
  
  public static Context _context;
  
  private static ActiveCallEntity actCall;
  
  public static boolean bBotonIVRPulsado = false;
  
  public static boolean bRelayReady = true;
  
  private static boolean blockBackPressed = false;
  
  private static DBFunctions dbFunctions;
  
  public static int iPreviousCallIndex = 0;
  
  private static boolean isExecuteAction = false;
  
  private static boolean isInitiatingCall = false;
  
  public static BluetoothServiceRT mChatService;
  
  private static ManagerNotificationPlatinum mNPlatinum;
  
  private static onstarApplication rtApp;
  
  private SwitchCompat DTC_Selector;
  
  private String STATE_TUTORIAL = "state_tutorial";
  
  private ImageView S_tirebottomleft;
  
  private ImageView S_tirebottomright;
  
  private ImageView S_tiretopleft;
  
  private ImageView S_tiretopright;
  
  private TextView actionbar_vehicle;
  
  private Button actions_btnActive;
  
  private Button actions_btnCancel;
  
  private String[] arrayOdometer;
  
  private String asistencia_global_popup_lbl_llamadaasistencia_2;
  
  private String autonomy_km;
  
  private String autonomy_status;
  
  private String autonomy_text;
  
  private Button btnAccept;
  
  private Button btnCancel;
  
  private Button btnNOK;
  
  private Button buttonOk;
  
  private CustomActionButton cab_pid_1;
  
  private CustomActionButton cab_pid_2;
  
  private CustomActionButton cab_pid_3;
  
  public View.OnClickListener clickTutorial;
  
  private String[] dateTime = null;
  
  private String deviceId;
  
  private String eventDate = "";
  
  private LinearLayout footerButtonPids;
  
  private FrameLayout frl_pid_no_info;
  
  private FrameLayout frl_pid_swipe;
  
  private int gas = 0;
  
  private String global_popup_btn_aceptar_1;
  
  private String global_popup_btn_llamar_1;
  
  private String global_popup_lbl_accionencurso_1;
  
  private LinearLayout header_vehiculo_name;
  
  private LinearLayout idTutorial;
  
  private ImageView imgGas;
  
  private ImageView imgOil;
  
  private ImageView imgOilStatus;
  
  private ImageView imgPid;
  
  private String isM300;
  
  private ImageView ivHeader;
  
  private LinearLayout lay_driverprofile;
  
  private LinearLayout layoutGasoline;
  
  private LinearLayout layoutInfoVehiculo;
  
  private LinearLayout layoutModeloPlaca;
  
  private LinearLayout layoutOdometer;
  
  private LinearLayout layoutOil;
  
  private LinearLayout layoutTirePressure;
  
  private TextView lbl_pid_autonomy_text;
  
  private TextView lbl_pid_no_info;
  
  private TextView lbl_pid_no_info2;
  
  private LinearLayout lin_pid_info;
  
  private LinearLayout.LayoutParams lp;
  
  private LinearLayout.LayoutParams lpBanner;
  
  private LinearLayout.LayoutParams lpHeader;
  
  private LinearLayout.LayoutParams lpfooter;
  
  private String modelo;
  
  private TextView pid_dtc;
  
  private String pid_main_lbl_aceite_9;
  
  private String pid_main_lbl_km_5;
  
  private String pid_main_lbl_kmaproximado;
  
  private String pid_main_lbl_na_1;
  
  private String pid_main_lbl_presionllantas_10;
  
  private String pid_main_lbl_psi_1;
  
  private String pid_main_lbl_totalkm_4;
  
  private String pid_popup_lbl_actualizadofecha_1;
  
  private String placa;
  
  private TextView pocentajeOil;
  
  private int position;
  
  private boolean pressTuto = true;
  
  private ProgressBar progressDTCSelector;
  
  private ShowViewElement show;
  
  private boolean stateTutorial;
  
  private String strOdometer = "";
  
  private String strOilStatus = "";
  
  private String strOilValue = "";
  
  private String strTireFL = "";
  
  private String strTireFR = "";
  
  private String strTireRL = "";
  
  private String strTireRR = "";
  
  private String strTireStatusFL;
  
  private String strTireStatusFR;
  
  private String strTireStatusRL;
  
  private String strTireStatusRR;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private SwipeRefreshLayout swipeLayout;
  
  private TextView textTireInmutable;
  
  private TextView textTirePressure;
  
  private Typeface tfLouis;
  
  private Typeface tfLouisItalic;
  
  private Typeface tfOdometer;
  
  private LinearLayout tip_pid_layout;
  
  private String tpms;
  
  private TextView txtDriverProfile;
  
  private TextView txt_bannerVehiculo;
  
  private TextView txt_dtc;
  
  private TextView txt_info_gasoline;
  
  private TextView txt_info_pidOdometer;
  
  private TextView txt_modelo;
  
  private TextView txt_pidOdometer1;
  
  private TextView txt_pidOdometer2;
  
  private TextView txt_pidOdometer3;
  
  private TextView txt_pidOdometer4;
  
  private TextView txt_pidOdometer5;
  
  private TextView txt_pidOdometer6;
  
  private TextView txt_pid_main_lbl_km_5;
  
  private TextView txt_pid_name_tire;
  
  private TextView txt_placa;
  
  private TextView txt_porcentajeGas;
  
  private TextView txt_porcentajeOil;
  
  private TextView txt_swipe;
  
  private TextView txt_tirebottomleft;
  
  private TextView txt_tirebottomright;
  
  private TextView txt_tiretopleft;
  
  private TextView txt_tiretopright;
  
  private TextView txtdate;
  
  private String version;
  
  private View view_spacer_1;
  
  private View view_spacer_2;
  
  private String year;
  
  public PIDActivity() {
    new BroadcastReceiver() {
        final PIDActivity this$0;
        
        public void onReceive(Context param1Context, Intent param1Intent) {
          if (param1Intent.getAction().equals("com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT")) {
            param1Intent.getExtras();
            String str1 = param1Intent.getStringExtra("Response");
            String str2 = param1Intent.getStringExtra("Response2");
            int j = Integer.parseInt(str1);
            int k = str2.equals("0");
            int i = PIDActivity.iPreviousCallIndex;
            boolean bool = false;
            if (i != 0) {
              i = 1;
            } else {
              i = 0;
            } 
            if ((k & i) != 0)
              (new Thread(new Runnable() {
                    final PIDActivity.null this$1;
                    
                    public void run() {
                      try {
                        Thread.sleep(5000L);
                      } catch (InterruptedException interruptedException) {
                        Utilities.escribeArchivo("PIDActivity", "Error: Timer", interruptedException.toString());
                      } 
                      PIDActivity.this.runOnUiThread(new Runnable(this) {
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
            PIDActivity.iPreviousCallIndex = Integer.parseInt(str2);
            if (GlobalMembers.btnpress != 1)
              if (!GlobalMembers.bActivityCallRunning) {
                boolean bool1 = bool;
                if (PIDActivity.iPreviousCallIndex != 0)
                  bool1 = true; 
                if (((GlobalMembers.bStartingCall ^ true) & bool1) != 0) {
                  GlobalMembers.bStartingCall = true;
                  Intent intent = new Intent((Context)PIDActivity.this, ActivityCall.class);
                  intent.putExtra("Boton", "Llamada");
                  PIDActivity.access$1702(true);
                  PIDActivity.iPreviousCallIndex = 1;
                  PIDActivity.this.startActivityForResult(intent, 123);
                } 
              } else {
                ActivityCall.sendCallStatusRecived(j, Integer.parseInt(str2));
              }  
          } 
        }
      };
    this.clickTutorial = new View.OnClickListener() {
        final PIDActivity this$0;
        
        public void onClick(View param1View) {
          if (!PIDActivity.isExecuteAction) {
            if (PIDActivity.this.pressTuto)
              PIDActivity.this.showTutorial(); 
          } else {
            PIDActivity.this.setActionButtons();
          } 
        }
      };
  }
  
  public static boolean CallOrHangUp(Enums.Calls paramCalls) {
    // Byte code:
    //   0: ldc com/roadtrack/onstar/pid/PIDActivity
    //   2: monitorenter
    //   3: getstatic com/roadtrack/onstar/pid/PIDActivity.isInitiatingCall : Z
    //   6: istore_3
    //   7: getstatic com/roadtrack/onstar/pid/PIDActivity.iPreviousCallIndex : I
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
    //   26: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   29: ifnull -> 192
    //   32: iload_2
    //   33: istore_1
    //   34: getstatic com/roadtrack/onstar/pid/PIDActivity$22.$SwitchMap$com$roadtrack$onstar$Enums$Calls : [I
    //   37: aload_0
    //   38: invokevirtual ordinal : ()I
    //   41: iaload
    //   42: tableswitch default -> 80, 1 -> 162, 2 -> 131, 3 -> 192, 4 -> 85, 5 -> 192, 6 -> 192
    //   80: iload_2
    //   81: istore_1
    //   82: goto -> 192
    //   85: iload_2
    //   86: istore_1
    //   87: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   90: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   93: getstatic com/roadtrack/onstar/Enums$CallNumberType.VR : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   96: if_acmpne -> 192
    //   99: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   102: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   105: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   108: if_acmpeq -> 123
    //   111: getstatic com/roadtrack/onstar/pid/PIDActivity._context : Landroid/content/Context;
    //   114: ldc_w 'RTMobile_HMI'
    //   117: invokestatic wakeDevice : (Landroid/content/Context;Ljava/lang/String;)V
    //   120: goto -> 190
    //   123: invokestatic stopWakeDevice : ()V
    //   126: iload_2
    //   127: istore_1
    //   128: goto -> 192
    //   131: iload_2
    //   132: istore_1
    //   133: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   136: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   139: getstatic com/roadtrack/onstar/Enums$CallNumberType.Emergency : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   142: if_acmpne -> 192
    //   145: iload_2
    //   146: istore_1
    //   147: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   150: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   153: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   156: if_acmpeq -> 192
    //   159: goto -> 190
    //   162: iload_2
    //   163: istore_1
    //   164: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   167: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   170: getstatic com/roadtrack/onstar/Enums$CallNumberType.Assistance : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   173: if_acmpne -> 192
    //   176: iload_2
    //   177: istore_1
    //   178: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   181: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   184: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   187: if_acmpeq -> 192
    //   190: iconst_1
    //   191: istore_1
    //   192: iload_1
    //   193: ifeq -> 245
    //   196: new com/roadtrack/onstar/entities/ActiveCallEntity
    //   199: astore #4
    //   201: aload #4
    //   203: invokespecial <init> : ()V
    //   206: aload #4
    //   208: putstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   211: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   214: getstatic com/roadtrack/onstar/Enums$DeviceCall.BT : Lcom/roadtrack/onstar/Enums$DeviceCall;
    //   217: invokevirtual setDevice : (Lcom/roadtrack/onstar/Enums$DeviceCall;)V
    //   220: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   223: iconst_0
    //   224: invokevirtual setCallIndex : (I)V
    //   227: iconst_0
    //   228: putstatic com/roadtrack/onstar/pid/PIDActivity.isInitiatingCall : Z
    //   231: aload_0
    //   232: getstatic com/roadtrack/onstar/Enums$Calls.IVR : Lcom/roadtrack/onstar/Enums$Calls;
    //   235: if_acmpne -> 245
    //   238: invokestatic getInstance : ()Lcom/roadtrack/onstar/platinum/MultiModalHMI;
    //   241: iconst_2
    //   242: invokevirtual closeMenuHMI : (I)V
    //   245: getstatic com/roadtrack/onstar/pid/PIDActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   248: ifnonnull -> 257
    //   251: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   254: putstatic com/roadtrack/onstar/pid/PIDActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   257: iload_1
    //   258: ifeq -> 282
    //   261: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   264: getstatic com/roadtrack/onstar/Enums$ActionCall.Hang_Up : Lcom/roadtrack/onstar/Enums$ActionCall;
    //   267: invokevirtual setAction : (Lcom/roadtrack/onstar/Enums$ActionCall;)V
    //   270: getstatic com/roadtrack/onstar/pid/PIDActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   273: getstatic com/roadtrack/onstar/pid/PIDActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   276: invokevirtual callControl : (Lcom/roadtrack/onstar/entities/ActiveCallEntity;)V
    //   279: goto -> 349
    //   282: getstatic com/roadtrack/onstar/pid/PIDActivity.bBotonIVRPulsado : Z
    //   285: ifne -> 293
    //   288: iconst_1
    //   289: istore_1
    //   290: goto -> 295
    //   293: iconst_0
    //   294: istore_1
    //   295: getstatic com/roadtrack/onstar/pid/PIDActivity.iPreviousCallIndex : I
    //   298: ifne -> 306
    //   301: iconst_1
    //   302: istore_2
    //   303: goto -> 308
    //   306: iconst_0
    //   307: istore_2
    //   308: iload_1
    //   309: iload_2
    //   310: iand
    //   311: getstatic com/roadtrack/onstar/pid/PIDActivity.bRelayReady : Z
    //   314: iand
    //   315: ifeq -> 330
    //   318: getstatic com/roadtrack/onstar/pid/PIDActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   321: aload_0
    //   322: ldc ''
    //   324: invokevirtual initiatingCall : (Lcom/roadtrack/onstar/Enums$Calls;Ljava/lang/String;)V
    //   327: goto -> 349
    //   330: getstatic com/roadtrack/onstar/pid/PIDActivity.bBotonIVRPulsado : Z
    //   333: ifeq -> 354
    //   336: iconst_0
    //   337: putstatic com/roadtrack/onstar/pid/PIDActivity.bBotonIVRPulsado : Z
    //   340: getstatic com/roadtrack/onstar/pid/PIDActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   343: aload_0
    //   344: ldc ''
    //   346: invokevirtual initiatingCall : (Lcom/roadtrack/onstar/Enums$Calls;Ljava/lang/String;)V
    //   349: ldc com/roadtrack/onstar/pid/PIDActivity
    //   351: monitorexit
    //   352: iconst_1
    //   353: ireturn
    //   354: ldc com/roadtrack/onstar/pid/PIDActivity
    //   356: monitorexit
    //   357: iconst_0
    //   358: ireturn
    //   359: astore_0
    //   360: ldc com/roadtrack/onstar/pid/PIDActivity
    //   362: monitorexit
    //   363: aload_0
    //   364: athrow
    // Exception table:
    //   from	to	target	type
    //   3	13	359	finally
    //   26	32	359	finally
    //   34	80	359	finally
    //   87	120	359	finally
    //   123	126	359	finally
    //   133	145	359	finally
    //   147	159	359	finally
    //   164	176	359	finally
    //   178	190	359	finally
    //   196	245	359	finally
    //   245	257	359	finally
    //   261	279	359	finally
    //   282	288	359	finally
    //   295	301	359	finally
    //   308	327	359	finally
    //   330	349	359	finally
  }
  
  private void SetStatusDTCSelectorOnRefresh() {
    if (!this.DTC_Selector.isChecked()) {
      SetStatusDTC(1);
    } else {
      SetStatusDTC(0);
    } 
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
  
  private void assistanceFunction() {
    final Dialog dialog;
    String str1 = Utilities.getStringFromConfigList(_context, this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    String str2 = Utilities.getStringFromConfigList(_context, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    if (!GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
      if (!isBtAndPlatinumConnected()) {
        Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_btnasistenciamenu_onstar_dialog, 2131165443);
        dialog = Utilities.simpleDialog((Context)this, null, str1, this.asistencia_global_popup_lbl_llamadaasistencia_2, true, this.global_popup_btn_llamar_1, false, str2);
        this.btnAccept = (Button)dialog.findViewById(2131296343);
        this.btnCancel = (Button)dialog.findViewById(2131296344);
        this.btnAccept.setOnClickListener(new View.OnClickListener() {
              final PIDActivity this$0;
              
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                PIDActivity.this.assistanceCall();
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
      Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_btnasistenciamenu_onstar_dialog, 2131165443);
      dialog = Utilities.simpleDialog((Context)this, null, (String)dialog, this.asistencia_global_popup_lbl_llamadaasistencia_2, true, this.global_popup_btn_llamar_1, false, str2);
      this.btnAccept = (Button)dialog.findViewById(2131296343);
      this.btnCancel = (Button)dialog.findViewById(2131296344);
      this.btnAccept.setOnClickListener(new View.OnClickListener() {
            final PIDActivity this$0;
            
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              PIDActivity.this.assistanceCall();
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
    } 
  }
  
  private void callShowTuto() {
    (new Handler()).postDelayed(new Runnable() {
          final PIDActivity this$0;
          
          public void run() {
            if (PIDActivity.this.stateTutorial)
              PIDActivity.this.showTutorial(); 
          }
        },  100L);
  }
  
  private void changeIconOilStatus(int paramInt, ImageView paramImageView) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt == 3)
            paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.palomitatire, 2131165630)); 
        } else {
          paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.tirewarning, 2131165693));
        } 
      } else {
        paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.tire_red, 2131165691));
      } 
    } else {
      paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.tireunknown, 2131165692));
    } 
  }
  
  private void changeOrientation(Configuration paramConfiguration) {
    LinkedHashMap linkedHashMap = Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getPidActions();
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
    int i = paramConfiguration.orientation;
    if (i == 1) {
      i = linkedHashMap.size();
      if (i != 0) {
        if (i == 1 || i == 2 || i == 3) {
          LinearLayout.LayoutParams layoutParams1 = this.lpHeader;
          layoutParams1.weight = 0.1F;
          this.header_vehiculo_name.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
          layoutParams1 = this.lpBanner;
          layoutParams1.weight = 0.08F;
          this.layoutInfoVehiculo.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
          layoutParams1 = this.lp;
          layoutParams1.weight = 0.66F;
          this.frl_pid_swipe.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
          layoutParams.weight = 0.04F;
          this.tip_pid_layout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
          layoutParams1 = this.lpfooter;
          layoutParams1.weight = 0.12F;
          this.footerButtonPids.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
        } 
      } else {
        LinearLayout.LayoutParams layoutParams1 = this.lpHeader;
        layoutParams1.weight = 0.1F;
        this.header_vehiculo_name.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
        layoutParams1 = this.lpBanner;
        layoutParams1.weight = 0.08F;
        this.layoutInfoVehiculo.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
        layoutParams.weight = 0.04F;
        this.tip_pid_layout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        layoutParams1 = this.lp;
        layoutParams1.weight = 0.66F;
        this.frl_pid_swipe.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
        layoutParams1 = this.lpfooter;
        layoutParams1.weight = 0.12F;
        this.footerButtonPids.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
      } 
      if (GlobalMembers.activeTutorial)
        this.idTutorial.setVisibility(0); 
    } else if (i == 2) {
      i = linkedHashMap.size();
      if (i != 0) {
        if (i == 1 || i == 2 || i == 3) {
          LinearLayout.LayoutParams layoutParams1 = this.lpHeader;
          layoutParams1.weight = 0.18F;
          this.header_vehiculo_name.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
          layoutParams1 = this.lpBanner;
          layoutParams1.weight = 0.13F;
          this.layoutInfoVehiculo.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
          layoutParams1 = this.lp;
          layoutParams1.weight = 0.41F;
          this.frl_pid_swipe.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
          layoutParams.weight = 0.01F;
          this.tip_pid_layout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
          layoutParams.weight = 0.06F;
          this.tip_pid_layout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
          layoutParams1 = this.lpfooter;
          layoutParams1.weight = 0.21F;
          this.footerButtonPids.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
        } 
      } else {
        LinearLayout.LayoutParams layoutParams1 = this.lpHeader;
        layoutParams1.weight = 0.18F;
        this.header_vehiculo_name.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
        layoutParams1 = this.lpBanner;
        layoutParams1.weight = 0.13F;
        this.layoutInfoVehiculo.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
        layoutParams1 = this.lp;
        layoutParams1.weight = 0.41F;
        this.frl_pid_swipe.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
        layoutParams.weight = 0.01F;
        this.tip_pid_layout.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        layoutParams1 = this.lp;
        layoutParams1.weight = 0.66F;
        this.frl_pid_swipe.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
        layoutParams1 = this.lpfooter;
        layoutParams1.weight = 0.21F;
        this.footerButtonPids.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
      } 
      if (GlobalMembers.activeTutorial) {
        this.idTutorial.setVisibility(8);
        ShowViewElement showViewElement = this.show;
        if (showViewElement != null)
          showViewElement.removeView(this, true); 
      } 
    } 
  }
  
  private void changeWarningTire(int paramInt, ImageView paramImageView, TextView paramTextView) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt != 4) {
              if (paramInt == 5)
                paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.tirewarning, 2131165693)); 
            } else {
              paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.tire_red, 2131165691));
            } 
          } else {
            paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.tirewarning, 2131165693));
          } 
        } else {
          paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.tire_red, 2131165691));
        } 
      } else {
        paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.palomitatire, 2131165630));
      } 
    } else {
      paramImageView.setImageDrawable(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.tirewarning, 2131165693));
      paramTextView.setText(this.pid_main_lbl_na_1);
    } 
  }
  
  private void dtcFunction() {
    Intent intent = new Intent((Context)this, RemoteDiagnosticActivity.class);
    Bundle bundle = new Bundle();
    bundle.putString("previousClass", PIDActivity.class.getSimpleName());
    intent.putExtras(bundle);
    startActivity(intent);
    GlobalMembers.notificacionesDTC = 0;
    dbFunctions.readerNotificationDTC(Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getDeviceId());
  }
  
  private void enableButtons() {
    this.cab_pid_1.setEnabled(true);
    this.cab_pid_1.setClickable(true);
    this.cab_pid_2.setEnabled(true);
    this.cab_pid_2.setClickable(true);
    this.cab_pid_3.setEnabled(true);
    this.cab_pid_3.setClickable(true);
  }
  
  private void formattedFont() {
    this.tfLouis = onstarApplication.getTypeface((Context)this, rtApp.fontPathLouisRegular);
    this.tfLouisItalic = onstarApplication.getTypeface((Context)this, rtApp.fontPathLouisItalic);
    this.tfOdometer = onstarApplication.getTypeface((Context)this, rtApp.fontPathCourierbold);
    this.actionbar_vehicle.setText(Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getName());
    this.actionbar_vehicle.setTypeface(this.tfLouis);
    this.txtdate.setTypeface(this.tfLouisItalic);
    this.txt_bannerVehiculo.setTypeface(this.tfLouis);
    this.txt_swipe.setTypeface(this.tfLouisItalic);
    this.txt_modelo.setTypeface(this.tfLouis);
    this.txt_placa.setTypeface(this.tfLouis);
    this.txt_info_pidOdometer.setTypeface(this.tfLouis);
    this.txt_info_gasoline.setTypeface(this.tfLouis);
    this.txt_pidOdometer1.setTypeface(this.tfOdometer);
    this.txt_pidOdometer2.setTypeface(this.tfOdometer);
    this.txt_pidOdometer3.setTypeface(this.tfOdometer);
    this.txt_pidOdometer4.setTypeface(this.tfOdometer);
    this.txt_pidOdometer5.setTypeface(this.tfOdometer);
    this.txt_pidOdometer6.setTypeface(this.tfOdometer);
    this.txt_pid_name_tire.setTypeface(this.tfLouis);
    this.textTirePressure.setTypeface(this.tfLouis);
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionpidinfovehiculo_1, 2131689883);
    String str11 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.dtc_lbl_vehiculo_mantenimiento, 2131689757);
    String str5 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_si_1, 2131689952);
    String str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_main_lbl_arrastre_3, 2131690245);
    String str8 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.dtc_main_lbl_descripcionpid, 2131689761);
    String str10 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.dtc_main_lbl_descripcionpid2, 2131689762);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.dtc_main_lbl_descripcionpid3, 2131689763);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_popup_lbl_autonomia_1, 2131690261);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_main_lbl_informacionpresionllantas_1, 2131690248);
    String str7 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.dtc_main_lbl_requiereconexion, 2131689764);
    String str6 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.dtc_main_lbl_requiereconexion, 2131690268);
    this.txtDriverProfile.setText(str6);
    this.txt_bannerVehiculo.setText(str3);
    this.txt_dtc.setText(str11);
    this.DTC_Selector.setTextOff(str5);
    this.DTC_Selector.setTextOn(str4);
    this.txt_swipe.setText(str9);
    this.lbl_pid_no_info.setText(str8);
    this.lbl_pid_no_info2.setText(str10);
    this.pocentajeOil.setText(this.pid_main_lbl_aceite_9);
    this.txt_pid_main_lbl_km_5.setText(this.pid_main_lbl_km_5);
    this.textTireInmutable.setText(str2);
    this.pid_dtc.setText(str7);
    this.txt_info_pidOdometer.setText(this.pid_main_lbl_totalkm_4);
    this.txt_info_gasoline.setText(str1);
    this.txt_pid_name_tire.setText(this.pid_main_lbl_presionllantas_10);
  }
  
  private void gasolineLevel() {
    if (this.autonomy_status.isEmpty() || this.autonomy_status == null) {
      this.layoutGasoline.setVisibility(8);
      return;
    } 
    this.layoutGasoline.setVisibility(0);
    try {
      if (this.autonomy_status.isEmpty()) {
        this.layoutGasoline.setVisibility(8);
      } else {
        int i = Integer.parseInt(this.autonomy_status);
        i = null.$SwitchMap$com$roadtrack$onstar$Enums$dtcStatus[Enums.dtcStatus.getValue(i).ordinal()];
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              this.layoutGasoline.setVisibility(8);
            } else if (this.autonomy_km != null && this.autonomy_km.isEmpty()) {
              this.layoutGasoline.setVisibility(8);
            } else {
              Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.pid_gasok, 2131165637);
              this.imgGas.setImageDrawable(drawable);
              TextView textView = this.txt_porcentajeGas;
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append(this.autonomy_km);
              stringBuilder.append(" ");
              stringBuilder.append(this.pid_main_lbl_km_5);
              textView.setText(stringBuilder.toString());
              this.txt_porcentajeGas.setTextColor(ContextCompat.getColor(getApplicationContext(), 2131034152));
              this.lbl_pid_autonomy_text.setVisibility(8);
            } 
          } else {
            Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.pid_gaswarning, 2131165638);
            this.imgGas.setImageDrawable(drawable);
            this.txt_porcentajeGas.setTextColor(ContextCompat.getColor(getApplicationContext(), 2131034287));
            this.txt_porcentajeGas.setText(this.autonomy_text);
          } 
        } else {
          Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.pid_gasdangerous, 2131165636);
          this.imgGas.setImageDrawable(drawable);
          this.txt_porcentajeGas.setTextColor(ContextCompat.getColor(getApplicationContext(), 2131034260));
          this.txt_porcentajeGas.setText(this.autonomy_text);
        } 
      } 
    } catch (NumberFormatException numberFormatException) {
      Utilities.escribeArchivo("PIDActivity", "Error gasolineLevel", numberFormatException.getMessage());
      this.layoutGasoline.setVisibility(8);
    } 
  }
  
  private String[] getArrayOdometerString(int paramInt) {
    try {
      String str = String.valueOf(paramInt);
      String[] arrayOfString = new String[str.length()];
      for (paramInt = 0; paramInt < str.length(); paramInt = i) {
        int i = paramInt + 1;
        arrayOfString[paramInt] = str.substring(paramInt, i);
      } 
      return arrayOfString;
    } catch (Exception exception) {
      return null;
    } 
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
  
  private void launcherCarInfoFunction() {
    String str4 = rtApp.getUserAccessData()[0];
    String str2 = rtApp.getUserAccessData()[1];
    String str3 = GlobalMembers.codeCountry;
    String str1 = Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getSerialnumber();
    (new ExecuteCarInfoWeb((Context)this, new AsyncResponse(this) {
          public void processFinish(String param1String) {}
        },  GlobalMembers.URL_PID_CarInfo)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str4, str2, str3, str1 });
  }
  
  @SuppressLint({"StringFormatInvalid"})
  private void loadPIDs() {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial formattedFont : ()V
    //   4: aload_0
    //   5: invokevirtual AlertOn : ()V
    //   8: aload_0
    //   9: getfield modelo : Ljava/lang/String;
    //   12: astore_3
    //   13: aload_3
    //   14: ifnull -> 24
    //   17: aload_3
    //   18: invokevirtual isEmpty : ()Z
    //   21: ifeq -> 75
    //   24: aload_0
    //   25: getfield placa : Ljava/lang/String;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull -> 40
    //   33: aload_3
    //   34: invokevirtual isEmpty : ()Z
    //   37: ifeq -> 75
    //   40: aload_0
    //   41: getfield version : Ljava/lang/String;
    //   44: astore_3
    //   45: aload_3
    //   46: ifnull -> 56
    //   49: aload_3
    //   50: invokevirtual isEmpty : ()Z
    //   53: ifeq -> 75
    //   56: aload_0
    //   57: getfield year : Ljava/lang/String;
    //   60: astore_3
    //   61: aload_3
    //   62: ifnull -> 86
    //   65: aload_3
    //   66: invokevirtual isEmpty : ()Z
    //   69: ifeq -> 75
    //   72: goto -> 86
    //   75: aload_0
    //   76: getfield layoutModeloPlaca : Landroid/widget/LinearLayout;
    //   79: iconst_0
    //   80: invokevirtual setVisibility : (I)V
    //   83: goto -> 95
    //   86: aload_0
    //   87: getfield layoutModeloPlaca : Landroid/widget/LinearLayout;
    //   90: bipush #8
    //   92: invokevirtual setVisibility : (I)V
    //   95: aload_0
    //   96: getfield modelo : Ljava/lang/String;
    //   99: astore_3
    //   100: aload_3
    //   101: ifnull -> 111
    //   104: aload_3
    //   105: invokevirtual isEmpty : ()Z
    //   108: ifeq -> 146
    //   111: aload_0
    //   112: getfield version : Ljava/lang/String;
    //   115: astore_3
    //   116: aload_3
    //   117: ifnull -> 127
    //   120: aload_3
    //   121: invokevirtual isEmpty : ()Z
    //   124: ifeq -> 146
    //   127: aload_0
    //   128: getfield year : Ljava/lang/String;
    //   131: astore_3
    //   132: aload_3
    //   133: ifnull -> 228
    //   136: aload_3
    //   137: invokevirtual isEmpty : ()Z
    //   140: ifeq -> 146
    //   143: goto -> 228
    //   146: aload_0
    //   147: getfield txt_modelo : Landroid/widget/TextView;
    //   150: iconst_0
    //   151: invokevirtual setVisibility : (I)V
    //   154: aload_0
    //   155: getfield txt_modelo : Landroid/widget/TextView;
    //   158: astore_3
    //   159: new java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial <init> : ()V
    //   166: astore #4
    //   168: aload #4
    //   170: aload_0
    //   171: getfield modelo : Ljava/lang/String;
    //   174: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: aload #4
    //   180: ldc_w ' '
    //   183: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: aload #4
    //   189: aload_0
    //   190: getfield version : Ljava/lang/String;
    //   193: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: pop
    //   197: aload #4
    //   199: ldc_w ' '
    //   202: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: pop
    //   206: aload #4
    //   208: aload_0
    //   209: getfield year : Ljava/lang/String;
    //   212: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload_3
    //   217: aload #4
    //   219: invokevirtual toString : ()Ljava/lang/String;
    //   222: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   225: goto -> 237
    //   228: aload_0
    //   229: getfield txt_modelo : Landroid/widget/TextView;
    //   232: bipush #8
    //   234: invokevirtual setVisibility : (I)V
    //   237: aload_0
    //   238: getfield placa : Ljava/lang/String;
    //   241: astore_3
    //   242: aload_3
    //   243: ifnull -> 278
    //   246: aload_3
    //   247: invokevirtual isEmpty : ()Z
    //   250: ifeq -> 256
    //   253: goto -> 278
    //   256: aload_0
    //   257: getfield txt_placa : Landroid/widget/TextView;
    //   260: iconst_0
    //   261: invokevirtual setVisibility : (I)V
    //   264: aload_0
    //   265: getfield txt_placa : Landroid/widget/TextView;
    //   268: aload_0
    //   269: getfield placa : Ljava/lang/String;
    //   272: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   275: goto -> 287
    //   278: aload_0
    //   279: getfield txt_placa : Landroid/widget/TextView;
    //   282: bipush #8
    //   284: invokevirtual setVisibility : (I)V
    //   287: aload_0
    //   288: getfield eventDate : Ljava/lang/String;
    //   291: astore_3
    //   292: aload_3
    //   293: ifnull -> 413
    //   296: aload_3
    //   297: invokevirtual isEmpty : ()Z
    //   300: ifne -> 413
    //   303: aload_0
    //   304: aload_0
    //   305: getfield eventDate : Ljava/lang/String;
    //   308: ldc_w 'dd/MM/yyyy HH:mm'
    //   311: invokestatic getUTCToNormalDate : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   314: putfield eventDate : Ljava/lang/String;
    //   317: aload_0
    //   318: aload_0
    //   319: getfield eventDate : Ljava/lang/String;
    //   322: ldc_w ' '
    //   325: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   328: putfield dateTime : [Ljava/lang/String;
    //   331: aload_0
    //   332: getfield dateTime : [Ljava/lang/String;
    //   335: arraylength
    //   336: iconst_1
    //   337: if_icmple -> 422
    //   340: aload_0
    //   341: getfield txtdate : Landroid/widget/TextView;
    //   344: astore #4
    //   346: new java/lang/StringBuilder
    //   349: dup
    //   350: invokespecial <init> : ()V
    //   353: astore_3
    //   354: aload_3
    //   355: aload_0
    //   356: getfield pid_popup_lbl_actualizadofecha_1 : Ljava/lang/String;
    //   359: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: pop
    //   363: aload_3
    //   364: ldc_w '\\n'
    //   367: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: pop
    //   371: aload_3
    //   372: aload_0
    //   373: getfield dateTime : [Ljava/lang/String;
    //   376: iconst_0
    //   377: aaload
    //   378: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: pop
    //   382: aload_3
    //   383: ldc_w '\\n'
    //   386: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: pop
    //   390: aload_3
    //   391: aload_0
    //   392: getfield dateTime : [Ljava/lang/String;
    //   395: iconst_1
    //   396: aaload
    //   397: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: pop
    //   401: aload #4
    //   403: aload_3
    //   404: invokevirtual toString : ()Ljava/lang/String;
    //   407: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   410: goto -> 422
    //   413: aload_0
    //   414: getfield txtdate : Landroid/widget/TextView;
    //   417: ldc ''
    //   419: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   422: aload_0
    //   423: getfield strOdometer : Ljava/lang/String;
    //   426: astore_3
    //   427: aload_3
    //   428: ifnull -> 1074
    //   431: aload_3
    //   432: invokevirtual isEmpty : ()Z
    //   435: ifeq -> 441
    //   438: goto -> 1074
    //   441: aload_0
    //   442: getfield strOdometer : Ljava/lang/String;
    //   445: ldc_w '.'
    //   448: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   451: ifne -> 479
    //   454: aload_0
    //   455: getfield strOdometer : Ljava/lang/String;
    //   458: invokestatic isNumeric : (Ljava/lang/String;)Z
    //   461: ifeq -> 467
    //   464: goto -> 479
    //   467: aload_0
    //   468: getfield layoutOdometer : Landroid/widget/LinearLayout;
    //   471: bipush #8
    //   473: invokevirtual setVisibility : (I)V
    //   476: goto -> 1083
    //   479: aload_0
    //   480: getfield layoutOdometer : Landroid/widget/LinearLayout;
    //   483: iconst_0
    //   484: invokevirtual setVisibility : (I)V
    //   487: aload_0
    //   488: getfield strOdometer : Ljava/lang/String;
    //   491: ldc_w '.'
    //   494: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   497: ifeq -> 514
    //   500: aload_0
    //   501: getfield strOdometer : Ljava/lang/String;
    //   504: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Double;
    //   507: invokevirtual intValue : ()I
    //   510: istore_1
    //   511: goto -> 522
    //   514: aload_0
    //   515: getfield strOdometer : Ljava/lang/String;
    //   518: invokestatic parseInt : (Ljava/lang/String;)I
    //   521: istore_1
    //   522: iload_1
    //   523: ifne -> 535
    //   526: aload_0
    //   527: getfield layoutOdometer : Landroid/widget/LinearLayout;
    //   530: bipush #8
    //   532: invokevirtual setVisibility : (I)V
    //   535: iload_1
    //   536: istore_2
    //   537: iload_1
    //   538: ldc_w 999999
    //   541: if_icmple -> 548
    //   544: ldc_w 999999
    //   547: istore_2
    //   548: aload_0
    //   549: aload_0
    //   550: iload_2
    //   551: invokespecial getArrayOdometerString : (I)[Ljava/lang/String;
    //   554: putfield arrayOdometer : [Ljava/lang/String;
    //   557: aload_0
    //   558: getfield arrayOdometer : [Ljava/lang/String;
    //   561: astore_3
    //   562: aload_3
    //   563: ifnull -> 1062
    //   566: aload_3
    //   567: arraylength
    //   568: tableswitch default -> 608, 2 -> 993, 3 -> 921, 4 -> 846, 5 -> 768, 6 -> 690, 7 -> 611
    //   608: goto -> 1083
    //   611: aload_0
    //   612: getfield txt_pidOdometer1 : Landroid/widget/TextView;
    //   615: aload_3
    //   616: iconst_1
    //   617: aaload
    //   618: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   621: aload_0
    //   622: getfield txt_pidOdometer2 : Landroid/widget/TextView;
    //   625: aload_0
    //   626: getfield arrayOdometer : [Ljava/lang/String;
    //   629: iconst_2
    //   630: aaload
    //   631: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   634: aload_0
    //   635: getfield txt_pidOdometer3 : Landroid/widget/TextView;
    //   638: aload_0
    //   639: getfield arrayOdometer : [Ljava/lang/String;
    //   642: iconst_3
    //   643: aaload
    //   644: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   647: aload_0
    //   648: getfield txt_pidOdometer4 : Landroid/widget/TextView;
    //   651: aload_0
    //   652: getfield arrayOdometer : [Ljava/lang/String;
    //   655: iconst_4
    //   656: aaload
    //   657: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   660: aload_0
    //   661: getfield txt_pidOdometer5 : Landroid/widget/TextView;
    //   664: aload_0
    //   665: getfield arrayOdometer : [Ljava/lang/String;
    //   668: iconst_5
    //   669: aaload
    //   670: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   673: aload_0
    //   674: getfield txt_pidOdometer6 : Landroid/widget/TextView;
    //   677: aload_0
    //   678: getfield arrayOdometer : [Ljava/lang/String;
    //   681: bipush #6
    //   683: aaload
    //   684: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   687: goto -> 1083
    //   690: aload_0
    //   691: getfield txt_pidOdometer1 : Landroid/widget/TextView;
    //   694: aload_3
    //   695: iconst_0
    //   696: aaload
    //   697: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   700: aload_0
    //   701: getfield txt_pidOdometer2 : Landroid/widget/TextView;
    //   704: aload_0
    //   705: getfield arrayOdometer : [Ljava/lang/String;
    //   708: iconst_1
    //   709: aaload
    //   710: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   713: aload_0
    //   714: getfield txt_pidOdometer3 : Landroid/widget/TextView;
    //   717: aload_0
    //   718: getfield arrayOdometer : [Ljava/lang/String;
    //   721: iconst_2
    //   722: aaload
    //   723: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   726: aload_0
    //   727: getfield txt_pidOdometer4 : Landroid/widget/TextView;
    //   730: aload_0
    //   731: getfield arrayOdometer : [Ljava/lang/String;
    //   734: iconst_3
    //   735: aaload
    //   736: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   739: aload_0
    //   740: getfield txt_pidOdometer5 : Landroid/widget/TextView;
    //   743: aload_0
    //   744: getfield arrayOdometer : [Ljava/lang/String;
    //   747: iconst_4
    //   748: aaload
    //   749: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   752: aload_0
    //   753: getfield txt_pidOdometer6 : Landroid/widget/TextView;
    //   756: aload_0
    //   757: getfield arrayOdometer : [Ljava/lang/String;
    //   760: iconst_5
    //   761: aaload
    //   762: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   765: goto -> 1083
    //   768: aload_0
    //   769: getfield txt_pidOdometer1 : Landroid/widget/TextView;
    //   772: ldc_w '0'
    //   775: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   778: aload_0
    //   779: getfield txt_pidOdometer2 : Landroid/widget/TextView;
    //   782: aload_0
    //   783: getfield arrayOdometer : [Ljava/lang/String;
    //   786: iconst_0
    //   787: aaload
    //   788: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   791: aload_0
    //   792: getfield txt_pidOdometer3 : Landroid/widget/TextView;
    //   795: aload_0
    //   796: getfield arrayOdometer : [Ljava/lang/String;
    //   799: iconst_1
    //   800: aaload
    //   801: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   804: aload_0
    //   805: getfield txt_pidOdometer4 : Landroid/widget/TextView;
    //   808: aload_0
    //   809: getfield arrayOdometer : [Ljava/lang/String;
    //   812: iconst_2
    //   813: aaload
    //   814: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   817: aload_0
    //   818: getfield txt_pidOdometer5 : Landroid/widget/TextView;
    //   821: aload_0
    //   822: getfield arrayOdometer : [Ljava/lang/String;
    //   825: iconst_3
    //   826: aaload
    //   827: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   830: aload_0
    //   831: getfield txt_pidOdometer6 : Landroid/widget/TextView;
    //   834: aload_0
    //   835: getfield arrayOdometer : [Ljava/lang/String;
    //   838: iconst_4
    //   839: aaload
    //   840: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   843: goto -> 1083
    //   846: aload_0
    //   847: getfield txt_pidOdometer1 : Landroid/widget/TextView;
    //   850: ldc_w '0'
    //   853: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   856: aload_0
    //   857: getfield txt_pidOdometer2 : Landroid/widget/TextView;
    //   860: ldc_w '0'
    //   863: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   866: aload_0
    //   867: getfield txt_pidOdometer3 : Landroid/widget/TextView;
    //   870: aload_0
    //   871: getfield arrayOdometer : [Ljava/lang/String;
    //   874: iconst_0
    //   875: aaload
    //   876: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   879: aload_0
    //   880: getfield txt_pidOdometer4 : Landroid/widget/TextView;
    //   883: aload_0
    //   884: getfield arrayOdometer : [Ljava/lang/String;
    //   887: iconst_1
    //   888: aaload
    //   889: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   892: aload_0
    //   893: getfield txt_pidOdometer5 : Landroid/widget/TextView;
    //   896: aload_0
    //   897: getfield arrayOdometer : [Ljava/lang/String;
    //   900: iconst_2
    //   901: aaload
    //   902: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   905: aload_0
    //   906: getfield txt_pidOdometer6 : Landroid/widget/TextView;
    //   909: aload_0
    //   910: getfield arrayOdometer : [Ljava/lang/String;
    //   913: iconst_3
    //   914: aaload
    //   915: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   918: goto -> 1083
    //   921: aload_0
    //   922: getfield txt_pidOdometer1 : Landroid/widget/TextView;
    //   925: ldc_w '0'
    //   928: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   931: aload_0
    //   932: getfield txt_pidOdometer2 : Landroid/widget/TextView;
    //   935: ldc_w '0'
    //   938: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   941: aload_0
    //   942: getfield txt_pidOdometer3 : Landroid/widget/TextView;
    //   945: ldc_w '0'
    //   948: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   951: aload_0
    //   952: getfield txt_pidOdometer4 : Landroid/widget/TextView;
    //   955: aload_0
    //   956: getfield arrayOdometer : [Ljava/lang/String;
    //   959: iconst_0
    //   960: aaload
    //   961: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   964: aload_0
    //   965: getfield txt_pidOdometer5 : Landroid/widget/TextView;
    //   968: aload_0
    //   969: getfield arrayOdometer : [Ljava/lang/String;
    //   972: iconst_1
    //   973: aaload
    //   974: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   977: aload_0
    //   978: getfield txt_pidOdometer6 : Landroid/widget/TextView;
    //   981: aload_0
    //   982: getfield arrayOdometer : [Ljava/lang/String;
    //   985: iconst_2
    //   986: aaload
    //   987: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   990: goto -> 1083
    //   993: aload_0
    //   994: getfield txt_pidOdometer1 : Landroid/widget/TextView;
    //   997: ldc_w '0'
    //   1000: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1003: aload_0
    //   1004: getfield txt_pidOdometer2 : Landroid/widget/TextView;
    //   1007: ldc_w '0'
    //   1010: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1013: aload_0
    //   1014: getfield txt_pidOdometer3 : Landroid/widget/TextView;
    //   1017: ldc_w '0'
    //   1020: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1023: aload_0
    //   1024: getfield txt_pidOdometer4 : Landroid/widget/TextView;
    //   1027: ldc_w '0'
    //   1030: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1033: aload_0
    //   1034: getfield txt_pidOdometer5 : Landroid/widget/TextView;
    //   1037: aload_0
    //   1038: getfield arrayOdometer : [Ljava/lang/String;
    //   1041: iconst_0
    //   1042: aaload
    //   1043: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1046: aload_0
    //   1047: getfield txt_pidOdometer6 : Landroid/widget/TextView;
    //   1050: aload_0
    //   1051: getfield arrayOdometer : [Ljava/lang/String;
    //   1054: iconst_1
    //   1055: aaload
    //   1056: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1059: goto -> 1083
    //   1062: aload_0
    //   1063: getfield layoutOdometer : Landroid/widget/LinearLayout;
    //   1066: bipush #8
    //   1068: invokevirtual setVisibility : (I)V
    //   1071: goto -> 1083
    //   1074: aload_0
    //   1075: getfield layoutOdometer : Landroid/widget/LinearLayout;
    //   1078: bipush #8
    //   1080: invokevirtual setVisibility : (I)V
    //   1083: aload_0
    //   1084: getfield strOilValue : Ljava/lang/String;
    //   1087: astore_3
    //   1088: aload_3
    //   1089: ifnull -> 1217
    //   1092: aload_3
    //   1093: invokevirtual isEmpty : ()Z
    //   1096: ifeq -> 1102
    //   1099: goto -> 1217
    //   1102: aload_0
    //   1103: getfield strOilValue : Ljava/lang/String;
    //   1106: invokestatic parseInt : (Ljava/lang/String;)I
    //   1109: istore_1
    //   1110: aload_0
    //   1111: getfield txt_porcentajeOil : Landroid/widget/TextView;
    //   1114: aload_0
    //   1115: getfield pid_main_lbl_aceite_9 : Ljava/lang/String;
    //   1118: ldc_w '%s'
    //   1121: aload_0
    //   1122: getfield strOilValue : Ljava/lang/String;
    //   1125: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   1128: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1131: iload_1
    //   1132: bipush #100
    //   1134: if_icmple -> 1199
    //   1137: aload_0
    //   1138: getfield layoutOil : Landroid/widget/LinearLayout;
    //   1141: bipush #8
    //   1143: invokevirtual setVisibility : (I)V
    //   1146: goto -> 1199
    //   1149: astore #4
    //   1151: aload_0
    //   1152: getfield layoutOil : Landroid/widget/LinearLayout;
    //   1155: bipush #8
    //   1157: invokevirtual setVisibility : (I)V
    //   1160: new java/lang/StringBuilder
    //   1163: dup
    //   1164: invokespecial <init> : ()V
    //   1167: astore_3
    //   1168: aload_3
    //   1169: ldc_w 'Error al parsear String : strValue'
    //   1172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1175: pop
    //   1176: aload_3
    //   1177: aload #4
    //   1179: invokevirtual toString : ()Ljava/lang/String;
    //   1182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1185: pop
    //   1186: ldc_w 'PIDActivity'
    //   1189: ldc_w 'THEFT'
    //   1192: aload_3
    //   1193: invokevirtual toString : ()Ljava/lang/String;
    //   1196: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1199: aload_0
    //   1200: aload_0
    //   1201: getfield strOilStatus : Ljava/lang/String;
    //   1204: invokestatic parseIntPIDValue : (Ljava/lang/String;)I
    //   1207: aload_0
    //   1208: getfield imgOilStatus : Landroid/widget/ImageView;
    //   1211: invokespecial changeIconOilStatus : (ILandroid/widget/ImageView;)V
    //   1214: goto -> 1226
    //   1217: aload_0
    //   1218: getfield layoutOil : Landroid/widget/LinearLayout;
    //   1221: bipush #8
    //   1223: invokevirtual setVisibility : (I)V
    //   1226: aload_0
    //   1227: getfield strTireStatusFL : Ljava/lang/String;
    //   1230: astore_3
    //   1231: aload_3
    //   1232: ifnull -> 1255
    //   1235: aload_3
    //   1236: ldc_w 'null'
    //   1239: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1242: ifne -> 1255
    //   1245: aload_0
    //   1246: getfield strTireStatusFL : Ljava/lang/String;
    //   1249: invokevirtual isEmpty : ()Z
    //   1252: ifeq -> 1467
    //   1255: aload_0
    //   1256: getfield strTireFL : Ljava/lang/String;
    //   1259: astore_3
    //   1260: aload_3
    //   1261: ifnull -> 1284
    //   1264: aload_3
    //   1265: ldc_w 'null'
    //   1268: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1271: ifne -> 1284
    //   1274: aload_0
    //   1275: getfield strTireFL : Ljava/lang/String;
    //   1278: invokevirtual isEmpty : ()Z
    //   1281: ifeq -> 1467
    //   1284: aload_0
    //   1285: getfield strTireStatusFR : Ljava/lang/String;
    //   1288: astore_3
    //   1289: aload_3
    //   1290: ifnull -> 1313
    //   1293: aload_3
    //   1294: ldc_w 'null'
    //   1297: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1300: ifne -> 1313
    //   1303: aload_0
    //   1304: getfield strTireStatusFR : Ljava/lang/String;
    //   1307: invokevirtual isEmpty : ()Z
    //   1310: ifeq -> 1467
    //   1313: aload_0
    //   1314: getfield strTireFR : Ljava/lang/String;
    //   1317: astore_3
    //   1318: aload_3
    //   1319: ifnull -> 1342
    //   1322: aload_3
    //   1323: ldc_w 'null'
    //   1326: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1329: ifne -> 1342
    //   1332: aload_0
    //   1333: getfield strTireFR : Ljava/lang/String;
    //   1336: invokevirtual isEmpty : ()Z
    //   1339: ifeq -> 1467
    //   1342: aload_0
    //   1343: getfield strTireStatusRL : Ljava/lang/String;
    //   1346: astore_3
    //   1347: aload_3
    //   1348: ifnull -> 1371
    //   1351: aload_3
    //   1352: ldc_w 'null'
    //   1355: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1358: ifne -> 1371
    //   1361: aload_0
    //   1362: getfield strTireStatusRL : Ljava/lang/String;
    //   1365: invokevirtual isEmpty : ()Z
    //   1368: ifeq -> 1467
    //   1371: aload_0
    //   1372: getfield strTireRL : Ljava/lang/String;
    //   1375: astore_3
    //   1376: aload_3
    //   1377: ifnull -> 1400
    //   1380: aload_3
    //   1381: ldc_w 'null'
    //   1384: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1387: ifne -> 1400
    //   1390: aload_0
    //   1391: getfield strTireRL : Ljava/lang/String;
    //   1394: invokevirtual isEmpty : ()Z
    //   1397: ifeq -> 1467
    //   1400: aload_0
    //   1401: getfield strTireStatusRR : Ljava/lang/String;
    //   1404: astore_3
    //   1405: aload_3
    //   1406: ifnull -> 1429
    //   1409: aload_3
    //   1410: ldc_w 'null'
    //   1413: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1416: ifne -> 1429
    //   1419: aload_0
    //   1420: getfield strTireStatusRR : Ljava/lang/String;
    //   1423: invokevirtual isEmpty : ()Z
    //   1426: ifeq -> 1467
    //   1429: aload_0
    //   1430: getfield strTireRR : Ljava/lang/String;
    //   1433: astore_3
    //   1434: aload_3
    //   1435: ifnull -> 1458
    //   1438: aload_3
    //   1439: ldc_w 'null'
    //   1442: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1445: ifne -> 1458
    //   1448: aload_0
    //   1449: getfield strTireRR : Ljava/lang/String;
    //   1452: invokevirtual isEmpty : ()Z
    //   1455: ifeq -> 1467
    //   1458: aload_0
    //   1459: getfield layoutTirePressure : Landroid/widget/LinearLayout;
    //   1462: bipush #8
    //   1464: invokevirtual setVisibility : (I)V
    //   1467: aload_0
    //   1468: getfield tpms : Ljava/lang/String;
    //   1471: astore_3
    //   1472: aload_3
    //   1473: ifnull -> 1496
    //   1476: aload_3
    //   1477: ldc_w 'null'
    //   1480: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1483: ifne -> 1496
    //   1486: aload_0
    //   1487: getfield tpms : Ljava/lang/String;
    //   1490: invokevirtual isEmpty : ()Z
    //   1493: ifeq -> 1505
    //   1496: aload_0
    //   1497: getfield textTirePressure : Landroid/widget/TextView;
    //   1500: bipush #8
    //   1502: invokevirtual setVisibility : (I)V
    //   1505: aload_0
    //   1506: getfield txt_modelo : Landroid/widget/TextView;
    //   1509: astore_3
    //   1510: new java/lang/StringBuilder
    //   1513: dup
    //   1514: invokespecial <init> : ()V
    //   1517: astore #4
    //   1519: aload #4
    //   1521: aload_0
    //   1522: getfield modelo : Ljava/lang/String;
    //   1525: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1528: pop
    //   1529: aload #4
    //   1531: ldc_w ' '
    //   1534: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1537: pop
    //   1538: aload #4
    //   1540: aload_0
    //   1541: getfield version : Ljava/lang/String;
    //   1544: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1547: pop
    //   1548: aload #4
    //   1550: ldc_w ' '
    //   1553: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1556: pop
    //   1557: aload #4
    //   1559: aload_0
    //   1560: getfield year : Ljava/lang/String;
    //   1563: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1566: pop
    //   1567: aload_3
    //   1568: aload #4
    //   1570: invokevirtual toString : ()Ljava/lang/String;
    //   1573: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1576: aload_0
    //   1577: getfield txt_placa : Landroid/widget/TextView;
    //   1580: aload_0
    //   1581: getfield placa : Ljava/lang/String;
    //   1584: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1587: aload_0
    //   1588: getfield eventDate : Ljava/lang/String;
    //   1591: astore_3
    //   1592: aload_3
    //   1593: ifnull -> 1713
    //   1596: aload_3
    //   1597: invokevirtual isEmpty : ()Z
    //   1600: ifne -> 1713
    //   1603: aload_0
    //   1604: aload_0
    //   1605: getfield eventDate : Ljava/lang/String;
    //   1608: ldc_w 'dd/MM/yyyy HH:mm'
    //   1611: invokestatic getUTCToNormalDate : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   1614: putfield eventDate : Ljava/lang/String;
    //   1617: aload_0
    //   1618: aload_0
    //   1619: getfield eventDate : Ljava/lang/String;
    //   1622: ldc_w ' '
    //   1625: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   1628: putfield dateTime : [Ljava/lang/String;
    //   1631: aload_0
    //   1632: getfield dateTime : [Ljava/lang/String;
    //   1635: arraylength
    //   1636: iconst_1
    //   1637: if_icmple -> 1722
    //   1640: aload_0
    //   1641: getfield txtdate : Landroid/widget/TextView;
    //   1644: astore #4
    //   1646: new java/lang/StringBuilder
    //   1649: dup
    //   1650: invokespecial <init> : ()V
    //   1653: astore_3
    //   1654: aload_3
    //   1655: aload_0
    //   1656: getfield pid_popup_lbl_actualizadofecha_1 : Ljava/lang/String;
    //   1659: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1662: pop
    //   1663: aload_3
    //   1664: ldc_w '\\n'
    //   1667: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1670: pop
    //   1671: aload_3
    //   1672: aload_0
    //   1673: getfield dateTime : [Ljava/lang/String;
    //   1676: iconst_0
    //   1677: aaload
    //   1678: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1681: pop
    //   1682: aload_3
    //   1683: ldc_w '\\n'
    //   1686: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1689: pop
    //   1690: aload_3
    //   1691: aload_0
    //   1692: getfield dateTime : [Ljava/lang/String;
    //   1695: iconst_1
    //   1696: aaload
    //   1697: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1700: pop
    //   1701: aload #4
    //   1703: aload_3
    //   1704: invokevirtual toString : ()Ljava/lang/String;
    //   1707: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1710: goto -> 1722
    //   1713: aload_0
    //   1714: getfield txtdate : Landroid/widget/TextView;
    //   1717: ldc ''
    //   1719: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1722: aload_0
    //   1723: invokespecial gasolineLevel : ()V
    //   1726: aload_0
    //   1727: getfield txt_pid_name_tire : Landroid/widget/TextView;
    //   1730: aload_0
    //   1731: getfield pid_main_lbl_presionllantas_10 : Ljava/lang/String;
    //   1734: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1737: aload_0
    //   1738: getfield txt_tiretopleft : Landroid/widget/TextView;
    //   1741: astore #4
    //   1743: new java/lang/StringBuilder
    //   1746: dup
    //   1747: invokespecial <init> : ()V
    //   1750: astore_3
    //   1751: aload_3
    //   1752: aload_0
    //   1753: getfield strTireFL : Ljava/lang/String;
    //   1756: invokestatic parseIntPIDValue : (Ljava/lang/String;)I
    //   1759: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1762: pop
    //   1763: aload_3
    //   1764: ldc_w ' '
    //   1767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1770: pop
    //   1771: aload_3
    //   1772: aload_0
    //   1773: getfield pid_main_lbl_psi_1 : Ljava/lang/String;
    //   1776: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1779: pop
    //   1780: aload #4
    //   1782: aload_3
    //   1783: invokevirtual toString : ()Ljava/lang/String;
    //   1786: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1789: aload_0
    //   1790: getfield txt_tiretopright : Landroid/widget/TextView;
    //   1793: astore_3
    //   1794: new java/lang/StringBuilder
    //   1797: dup
    //   1798: invokespecial <init> : ()V
    //   1801: astore #4
    //   1803: aload #4
    //   1805: aload_0
    //   1806: getfield strTireFR : Ljava/lang/String;
    //   1809: invokestatic parseIntPIDValue : (Ljava/lang/String;)I
    //   1812: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1815: pop
    //   1816: aload #4
    //   1818: ldc_w ' '
    //   1821: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1824: pop
    //   1825: aload #4
    //   1827: aload_0
    //   1828: getfield pid_main_lbl_psi_1 : Ljava/lang/String;
    //   1831: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1834: pop
    //   1835: aload_3
    //   1836: aload #4
    //   1838: invokevirtual toString : ()Ljava/lang/String;
    //   1841: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1844: aload_0
    //   1845: getfield txt_tirebottomleft : Landroid/widget/TextView;
    //   1848: astore_3
    //   1849: new java/lang/StringBuilder
    //   1852: dup
    //   1853: invokespecial <init> : ()V
    //   1856: astore #4
    //   1858: aload #4
    //   1860: aload_0
    //   1861: getfield strTireRL : Ljava/lang/String;
    //   1864: invokestatic parseIntPIDValue : (Ljava/lang/String;)I
    //   1867: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1870: pop
    //   1871: aload #4
    //   1873: ldc_w ' '
    //   1876: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1879: pop
    //   1880: aload #4
    //   1882: aload_0
    //   1883: getfield pid_main_lbl_psi_1 : Ljava/lang/String;
    //   1886: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1889: pop
    //   1890: aload_3
    //   1891: aload #4
    //   1893: invokevirtual toString : ()Ljava/lang/String;
    //   1896: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1899: aload_0
    //   1900: getfield txt_tirebottomright : Landroid/widget/TextView;
    //   1903: astore_3
    //   1904: new java/lang/StringBuilder
    //   1907: dup
    //   1908: invokespecial <init> : ()V
    //   1911: astore #4
    //   1913: aload #4
    //   1915: aload_0
    //   1916: getfield strTireRR : Ljava/lang/String;
    //   1919: invokestatic parseIntPIDValue : (Ljava/lang/String;)I
    //   1922: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1925: pop
    //   1926: aload #4
    //   1928: ldc_w ' '
    //   1931: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1934: pop
    //   1935: aload #4
    //   1937: aload_0
    //   1938: getfield pid_main_lbl_psi_1 : Ljava/lang/String;
    //   1941: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1944: pop
    //   1945: aload_3
    //   1946: aload #4
    //   1948: invokevirtual toString : ()Ljava/lang/String;
    //   1951: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1954: aload_0
    //   1955: getfield textTirePressure : Landroid/widget/TextView;
    //   1958: aload_0
    //   1959: getfield tpms : Ljava/lang/String;
    //   1962: invokestatic unicodeToString : (Ljava/lang/String;)Ljava/lang/String;
    //   1965: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   1968: aload_0
    //   1969: aload_0
    //   1970: ldc_w 2131297199
    //   1973: invokevirtual findViewById : (I)Landroid/view/View;
    //   1976: checkcast android/widget/TextView
    //   1979: putfield txt_info_pidOdometer : Landroid/widget/TextView;
    //   1982: aload_0
    //   1983: getfield isM300 : Ljava/lang/String;
    //   1986: ldc_w '1'
    //   1989: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1992: ifeq -> 2009
    //   1995: aload_0
    //   1996: getfield txt_info_pidOdometer : Landroid/widget/TextView;
    //   1999: aload_0
    //   2000: getfield pid_main_lbl_kmaproximado : Ljava/lang/String;
    //   2003: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   2006: goto -> 2020
    //   2009: aload_0
    //   2010: getfield txt_info_pidOdometer : Landroid/widget/TextView;
    //   2013: aload_0
    //   2014: getfield pid_main_lbl_totalkm_4 : Ljava/lang/String;
    //   2017: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   2020: aload_0
    //   2021: aload_0
    //   2022: getfield strTireStatusRR : Ljava/lang/String;
    //   2025: invokestatic parseIntPIDValue : (Ljava/lang/String;)I
    //   2028: aload_0
    //   2029: getfield S_tirebottomright : Landroid/widget/ImageView;
    //   2032: aload_0
    //   2033: getfield txt_tirebottomright : Landroid/widget/TextView;
    //   2036: invokespecial changeWarningTire : (ILandroid/widget/ImageView;Landroid/widget/TextView;)V
    //   2039: aload_0
    //   2040: aload_0
    //   2041: getfield strTireStatusRL : Ljava/lang/String;
    //   2044: invokestatic parseIntPIDValue : (Ljava/lang/String;)I
    //   2047: aload_0
    //   2048: getfield S_tirebottomleft : Landroid/widget/ImageView;
    //   2051: aload_0
    //   2052: getfield txt_tirebottomleft : Landroid/widget/TextView;
    //   2055: invokespecial changeWarningTire : (ILandroid/widget/ImageView;Landroid/widget/TextView;)V
    //   2058: aload_0
    //   2059: aload_0
    //   2060: getfield strTireStatusFR : Ljava/lang/String;
    //   2063: invokestatic parseIntPIDValue : (Ljava/lang/String;)I
    //   2066: aload_0
    //   2067: getfield S_tiretopright : Landroid/widget/ImageView;
    //   2070: aload_0
    //   2071: getfield txt_tiretopright : Landroid/widget/TextView;
    //   2074: invokespecial changeWarningTire : (ILandroid/widget/ImageView;Landroid/widget/TextView;)V
    //   2077: aload_0
    //   2078: aload_0
    //   2079: getfield strTireStatusFL : Ljava/lang/String;
    //   2082: invokestatic parseIntPIDValue : (Ljava/lang/String;)I
    //   2085: aload_0
    //   2086: getfield S_tiretopleft : Landroid/widget/ImageView;
    //   2089: aload_0
    //   2090: getfield txt_tiretopleft : Landroid/widget/TextView;
    //   2093: invokespecial changeWarningTire : (ILandroid/widget/ImageView;Landroid/widget/TextView;)V
    //   2096: aload_0
    //   2097: invokespecial newFooterButtons : ()V
    //   2100: aload_0
    //   2101: invokespecial SetStatusDTCSelectorOnRefresh : ()V
    //   2104: aload_0
    //   2105: invokevirtual AlertOn : ()V
    //   2108: return
    // Exception table:
    //   from	to	target	type
    //   1102	1131	1149	java/lang/NumberFormatException
    //   1137	1146	1149	java/lang/NumberFormatException
  }
  
  private void newFooterButtons() {
    LinkedHashMap linkedHashMap = Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getPidActions();
    if (linkedHashMap.size() < 1 || linkedHashMap == null) {
      this.cab_pid_1.setVisibility(8);
      this.view_spacer_1.setVisibility(8);
      this.cab_pid_2.setVisibility(8);
      this.view_spacer_2.setVisibility(8);
      this.cab_pid_3.setVisibility(8);
      this.footerButtonPids.setVisibility(8);
      return;
    } 
    if (linkedHashMap.size() == 1) {
      this.cab_pid_1.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
      this.view_spacer_1.setVisibility(8);
      this.cab_pid_2.setVisibility(8);
      this.view_spacer_2.setVisibility(8);
      this.cab_pid_3.setVisibility(8);
    } else if (linkedHashMap.size() == 2) {
      this.cab_pid_1.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -1, 1.5F));
      this.cab_pid_2.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -1, 1.5F));
      this.view_spacer_2.setVisibility(8);
      this.cab_pid_3.setVisibility(8);
    } 
    Iterator<String> iterator = linkedHashMap.keySet().iterator();
    byte b = 0;
    while (iterator.hasNext()) {
      String str = iterator.next();
      try {
        int i = Integer.parseInt(str);
        i = null.$SwitchMap$com$roadtrack$onstar$Enums$Services[Enums.Services.GetValue(Integer.valueOf(i).intValue()).ordinal()];
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i == 4)
                if (!b) {
                  this.cab_pid_1.setVisibility(4);
                } else if (b == 1) {
                  this.cab_pid_2.setVisibility(4);
                  this.view_spacer_1.setVisibility(0);
                } else if (b == 2) {
                  this.cab_pid_3.setVisibility(4);
                  this.view_spacer_2.setVisibility(0);
                }  
            } else if (!b) {
              this.cab_pid_1.setVisibility(8);
            } else if (b == 1) {
              this.cab_pid_2.setVisibility(8);
              this.view_spacer_1.setVisibility(8);
            } else if (b == 2) {
              this.cab_pid_3.setVisibility(8);
              this.view_spacer_2.setVisibility(8);
            } 
          } else if (!b) {
            this.cab_pid_1 = CustomActionButtonFactory.getActionButton(Enums.Services.ActionAssistance, getApplicationContext(), this.cab_pid_1, UserInterfaceUtils.tieneWaterMark(Enums.Services.ActionAssistance.GetCode(), 3, rtApp), 1);
            this.cab_pid_1.setVisibility(0);
          } else if (b == 1) {
            this.cab_pid_2 = CustomActionButtonFactory.getActionButton(Enums.Services.ActionAssistance, getApplicationContext(), this.cab_pid_2, UserInterfaceUtils.tieneWaterMark(Enums.Services.ActionAssistance.GetCode(), 3, rtApp), 1);
            this.cab_pid_2.setVisibility(0);
            this.view_spacer_1.setVisibility(0);
          } else if (b == 2) {
            this.cab_pid_3 = CustomActionButtonFactory.getActionButton(Enums.Services.ActionAssistance, getApplicationContext(), this.cab_pid_3, UserInterfaceUtils.tieneWaterMark(Enums.Services.ActionAssistance.GetCode(), 3, rtApp), 1);
            this.cab_pid_3.setVisibility(0);
            this.view_spacer_2.setVisibility(0);
          } 
        } else if (!b) {
          this.cab_pid_1 = CustomActionButtonFactory.getActionButton(Enums.Services.DTCAction, getApplicationContext(), this.cab_pid_1, UserInterfaceUtils.tieneWaterMark(Enums.Services.DTCAction.GetCode(), 3, rtApp), 1);
          this.cab_pid_1.setVisibility(0);
        } else if (b == 1) {
          this.cab_pid_2 = CustomActionButtonFactory.getActionButton(Enums.Services.DTCAction, getApplicationContext(), this.cab_pid_2, UserInterfaceUtils.tieneWaterMark(Enums.Services.DTCAction.GetCode(), 3, rtApp), 1);
          this.cab_pid_2.setVisibility(0);
          this.view_spacer_1.setVisibility(0);
        } else if (b == 2) {
          this.cab_pid_3 = CustomActionButtonFactory.getActionButton(Enums.Services.DTCAction, getApplicationContext(), this.cab_pid_3, UserInterfaceUtils.tieneWaterMark(Enums.Services.DTCAction.GetCode(), 3, rtApp), 1);
          this.cab_pid_3.setVisibility(0);
          this.view_spacer_2.setVisibility(0);
        } 
        b++;
      } catch (NumberFormatException numberFormatException) {
        numberFormatException.printStackTrace();
      } 
    } 
  }
  
  private void searchOnDB() {
    this.swipeLayout.setRefreshing(true);
    this.frl_pid_no_info.setVisibility(8);
    this.cab_pid_1.setEnabled(false);
    this.cab_pid_1.setClickable(false);
    this.cab_pid_2.setEnabled(false);
    this.cab_pid_2.setClickable(false);
    this.cab_pid_3.setEnabled(false);
    this.cab_pid_3.setClickable(false);
    try {
      int i = Integer.parseInt(this.deviceId);
      ActionResultVO actionResultVO = new ActionResultVO(getApplicationContext(), i);
      PIDVO pIDVO = dbFunctions.getLastPid(this.deviceId);
      if (pIDVO != null && pIDVO.isValidPID()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.deviceId);
        stringBuilder.append(": Reading Historic PID: ");
        stringBuilder.append(Enums.Services.pid.GetCode());
        Utilities.escribeArchivo("PIDActivity", "searchOnDB", stringBuilder.toString());
        pIDVO.setDeviceId(i);
        actionResultVO.setModelo(pIDVO.getModelo());
        actionResultVO.setVersion(pIDVO.getVersion());
        actionResultVO.setYear(pIDVO.getYear());
        actionResultVO.setPlaca(pIDVO.getPlaca());
        actionResultVO.setOdometer(pIDVO.getOdometer());
        actionResultVO.setGas(pIDVO.getGas());
        actionResultVO.setOilValue(pIDVO.getOilValue());
        actionResultVO.setOilStatus(pIDVO.getOilStatus());
        actionResultVO.setBatteryLevel(pIDVO.getBatterylevel());
        actionResultVO.setTireStatusFL(pIDVO.getTire_status_fl());
        actionResultVO.setTireFL(pIDVO.getTire_fl());
        actionResultVO.setTireStatusFR(pIDVO.getTire_status_fr());
        actionResultVO.setTireFR(pIDVO.getTire_fr());
        actionResultVO.setTireStatusRL(pIDVO.getTire_status_rl());
        actionResultVO.setTireRL(pIDVO.getTire_rl());
        actionResultVO.setTireStatusRR(pIDVO.getTire_status_rr());
        actionResultVO.setTireRR(pIDVO.getTire_rr());
        actionResultVO.setTPMSText(pIDVO.getTPMSText());
        actionResultVO.setIsM300(pIDVO.getIsM300());
        actionResultVO.setAutonomy_km(pIDVO.getAutonomy_km());
        actionResultVO.setAutonomy_status(pIDVO.getAutonomy_status());
        actionResultVO.setAutonomy_text(pIDVO.getAutonomy_text());
        actionResultVO.setEventDateTime(pIDVO.getEventDate());
        actionResultVO.setIdResponse(Enums.ActionResultCode.Activated);
        this.eventDate = actionResultVO.getEventDateTime();
        this.modelo = actionResultVO.getModelo();
        this.version = actionResultVO.getVersion();
        this.year = actionResultVO.getYear();
        this.placa = actionResultVO.getPlaca();
        this.tpms = actionResultVO.getTPMSText();
        this.isM300 = actionResultVO.getIsM300();
        this.autonomy_km = actionResultVO.getAutonomy_km();
        this.autonomy_status = actionResultVO.getAutonomy_status();
        this.autonomy_text = actionResultVO.getAutonomy_text();
        this.strOdometer = actionResultVO.getOdometer();
        this.strOilValue = actionResultVO.getOilValue();
        this.strOilStatus = actionResultVO.getOilStatus();
        this.strTireFL = actionResultVO.getTireFL();
        this.strTireFR = actionResultVO.getTireFR();
        this.strTireRL = actionResultVO.getTireRL();
        this.strTireRR = actionResultVO.getTireRR();
        this.strTireStatusFL = actionResultVO.getTireStatusFL();
        this.strTireStatusFR = actionResultVO.getTireStatusFR();
        this.strTireStatusRL = actionResultVO.getTireStatusRL();
        this.strTireStatusRR = actionResultVO.getTireStatusRR();
        this.frl_pid_no_info.setVisibility(8);
        this.lin_pid_info.setVisibility(0);
        loadPIDs();
      } else {
        actionResultVO.setIdResponse(Enums.ActionResultCode.Fail);
        this.frl_pid_no_info.setVisibility(0);
        this.lin_pid_info.setVisibility(4);
      } 
      this.swipeLayout.setRefreshing(false);
      this.cab_pid_1.setEnabled(true);
      this.cab_pid_1.setClickable(true);
      this.cab_pid_2.setEnabled(true);
      this.cab_pid_2.setClickable(true);
      this.cab_pid_3.setEnabled(true);
      this.cab_pid_3.setClickable(true);
      return;
    } catch (NumberFormatException numberFormatException) {
      Utilities.escribeArchivo("PIDActivity", "Error searchOnDB", numberFormatException.getMessage());
      return;
    } 
  }
  
  private void setAlert(int paramInt, Enums.Services paramServices) {
    boolean bool;
    if (paramServices.equals(Enums.Services.DTCAction)) {
      bool = GlobalMembers.notificacionesDTC;
    } else {
      bool = false;
    } 
    CustomActionButton customActionButton = this.cab_pid_1;
    if (customActionButton != null && customActionButton.getVisibility() != 8 && this.cab_pid_1.getMyService().equals(paramServices)) {
      this.cab_pid_1.setBadgeNumber(bool);
      this.cab_pid_1.showActionStatus(paramInt);
    } else {
      customActionButton = this.cab_pid_2;
      if (customActionButton != null && customActionButton.getVisibility() != 8 && this.cab_pid_2.getMyService().equals(paramServices)) {
        this.cab_pid_2.setBadgeNumber(bool);
        this.cab_pid_2.showActionStatus(paramInt);
      } else {
        customActionButton = this.cab_pid_3;
        if (customActionButton != null && customActionButton.getVisibility() != 8 && this.cab_pid_3.getMyService().equals(paramServices)) {
          this.cab_pid_3.setBadgeNumber(bool);
          this.cab_pid_3.showActionStatus(paramInt);
        } 
      } 
    } 
  }
  
  private void setResultOnGlobals(ActionResultVO paramActionResultVO) {
    this.eventDate = paramActionResultVO.getEventDateTime();
    this.modelo = paramActionResultVO.getModelo();
    this.version = paramActionResultVO.getVersion();
    this.year = paramActionResultVO.getYear();
    this.placa = paramActionResultVO.getPlaca();
    this.tpms = paramActionResultVO.getTPMSText();
    this.isM300 = paramActionResultVO.getIsM300();
    this.autonomy_km = paramActionResultVO.getAutonomy_km();
    this.autonomy_status = paramActionResultVO.getAutonomy_status();
    this.autonomy_text = paramActionResultVO.getAutonomy_text();
    this.strOdometer = paramActionResultVO.getOdometer();
    this.strOilValue = paramActionResultVO.getOilValue();
    this.strOilStatus = paramActionResultVO.getOilStatus();
    this.strTireFL = paramActionResultVO.getTireFL();
    this.strTireFR = paramActionResultVO.getTireFR();
    this.strTireRL = paramActionResultVO.getTireRL();
    this.strTireRR = paramActionResultVO.getTireRR();
    this.strTireStatusFL = paramActionResultVO.getTireStatusFL();
    this.strTireStatusFR = paramActionResultVO.getTireStatusFR();
    this.strTireStatusRL = paramActionResultVO.getTireStatusRL();
    this.strTireStatusRR = paramActionResultVO.getTireStatusRR();
  }
  
  private void stopProcessButtonDTC(Context paramContext) {
    String str3 = this.global_popup_lbl_accionencurso_1;
    String str1 = Utilities.getStringFromConfigList(_context, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str2 = Utilities.getStringFromConfigList(_context, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    final Dialog dialog = Utilities.simpleDialog(paramContext, null, str1, str3, false, this.global_popup_btn_aceptar_1, true, str2, 20.0F, 20.0F);
    this.actions_btnActive = (Button)dialog.findViewById(2131296343);
    this.actions_btnActive.setText(this.global_popup_lbl_accionencurso_1);
    this.actions_btnActive.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.actions_btnCancel = (Button)dialog.findViewById(2131296344);
    this.actions_btnCancel.setText(this.global_popup_btn_aceptar_1);
    this.actions_btnCancel.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
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
        String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
        String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
        String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
        Dialog dialog = Utilities.simpleDialog((Context)this, null, str3, this.global_popup_lbl_accionencurso_1, true, str1, false, str2, 20.0F, 20.0F);
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
        Utilities.escribeArchivo("PIDActivity", "Error: dialogBackALert", exception.getMessage());
      }  
    return bool;
  }
  
  public void AlertOn() {
    if (GlobalMembers.redPoint)
      if (GlobalMembers.notificacionesDTC != 0) {
        setAlert(8, Enums.Services.DTCAction);
      } else {
        setAlert(0, Enums.Services.DTCAction);
      }  
  }
  
  public void ChangedStatusSocketSwitch(final String statusSocket) {
    Act.runOnUiThread(new Runnable() {
          final PIDActivity this$0;
          
          final String val$statusSocket;
          
          public void run() {
            if (statusSocket.equals("1")) {
              PIDActivity.access$202(true);
              PIDActivity.this.DTC_Selector.setChecked(true);
              PIDActivity.access$202(false);
              PIDActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(PIDActivity.rtApp, "PIDActivity").getDeviceId(), "1");
            } else {
              PIDActivity.access$202(true);
              PIDActivity.this.DTC_Selector.setChecked(false);
              PIDActivity.access$202(false);
              PIDActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(PIDActivity.rtApp, "PIDActivity").getDeviceId(), "0");
            } 
          }
        });
  }
  
  public void GETStatusDTCSelector() {
    if (!this.DTC_Selector.isChecked()) {
      SetStatusDTC(1);
    } else {
      SetStatusDTC(0);
    } 
    if (NetUtilities.validateNetwork((Context)this, false)) {
      GetDTCSelector_task getDTCSelector_task = (GetDTCSelector_task)(new GetDTCSelector_task(_context, this.deviceId, this.progressDTCSelector, this.DTC_Selector)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
      SetStatusDTC(0);
      getDTCSelector_task.setOnPostExecuteListenerDTCSelector(new GetDTCSelector_task.OnPostExecuteListenerDTCSelector() {
            final PIDActivity this$0;
            
            public void onPostExecuteListener(String param1String) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("GETStatusDTCSelector:");
              stringBuilder.append(param1String);
              Utilities.escribeArchivo("PIDActivity", "GETStatusDTCSelector", stringBuilder.toString());
              if (param1String != null) {
                try {
                  if (param1String.split("\\|")[1].toString().equals("1")) {
                    PIDActivity.access$202(true);
                    PIDActivity.this.DTC_Selector.setChecked(true);
                    PIDActivity.access$202(false);
                    PIDActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(PIDActivity.rtApp, "PIDActivity").getDeviceId(), "1");
                  } else {
                    PIDActivity.access$202(true);
                    PIDActivity.this.DTC_Selector.setChecked(false);
                    PIDActivity.access$202(false);
                    PIDActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(PIDActivity.rtApp, "PIDActivity").getDeviceId(), "0");
                  } 
                  if (!PIDActivity.this.DTC_Selector.isChecked()) {
                    PIDActivity.this.SetStatusDTC(1);
                  } else {
                    PIDActivity.this.SetStatusDTC(0);
                  } 
                } catch (Exception exception) {
                  PIDActivity.this.setLastKnowStatusDTCSelector();
                } 
              } else {
                PIDActivity.this.setLastKnowStatusDTCSelector();
              } 
            }
          });
    } else {
      setLastKnowStatusDTCSelector();
    } 
  }
  
  public void SetStatusDTC(int paramInt) {
    if (this.cab_pid_1.getMyService() == Enums.Services.DTCAction) {
      this.cab_pid_1.set_action_service_status(paramInt);
    } else if (this.cab_pid_2.getMyService() == Enums.Services.DTCAction) {
      this.cab_pid_2.set_action_service_status(paramInt);
    } else if (this.cab_pid_3.getMyService() == Enums.Services.DTCAction) {
      this.cab_pid_3.set_action_service_status(paramInt);
    } 
    AlertOn();
  }
  
  public void ShowDialog48DTC() {
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str3 = Utilities.getStringFromConfigList(_context, stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str2 = Utilities.getStringFromConfigList(_context, stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    String str1 = Utilities.getStringFromConfigList(_context, stringsResourcesVO.dtc_popup_lbl_avisodesactivarDTC, 2131689768);
    String str4 = Utilities.getStringFromConfigList(_context, stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    final Dialog dialog = Utilities.simpleDialog(_context, null, str3, str1, false, str2, true, str4, false);
    this.btnNOK = (Button)dialog.findViewById(2131296344);
    this.btnNOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
  }
  
  public void UPDATEStatusDTCSelector(boolean paramBoolean) {
    if (!IsCheckStatusDTC_Selector) {
      if (NetUtilities.validateNetwork((Context)this, false, true)) {
        SetStatusDTC(0);
        ((UpdateStatusDTCSelector_task)(new UpdateStatusDTCSelector_task(_context, this.deviceId, this.progressDTCSelector, this.DTC_Selector)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0])).setOnPostExecuteListenerUpdateDTCSelector(new UpdateStatusDTCSelector_task.OnPostExecuteListenerUpdateDTCSelector() {
              final PIDActivity this$0;
              
              public void onPostExecuteListener(String param1String) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("UPDATEStatusDTCSelector:");
                stringBuilder.append(param1String);
                Utilities.escribeArchivo("PIDActivity", "UPDATEStatusDTCSelector", stringBuilder.toString());
                if (param1String != null) {
                  try {
                    if (param1String.split("\\|")[1].toString().equals("1")) {
                      PIDActivity.access$202(true);
                      PIDActivity.this.DTC_Selector.setChecked(true);
                      PIDActivity.access$202(false);
                      PIDActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(PIDActivity.rtApp, "PIDActivity").getDeviceId(), "1");
                      PIDActivity.this.ShowDialog48DTC();
                    } else {
                      PIDActivity.access$202(true);
                      PIDActivity.this.DTC_Selector.setChecked(false);
                      PIDActivity.access$202(false);
                      PIDActivity.dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(PIDActivity.rtApp, "PIDActivity").getDeviceId(), "0");
                    } 
                    if (!PIDActivity.this.DTC_Selector.isChecked()) {
                      PIDActivity.this.SetStatusDTC(1);
                    } else {
                      PIDActivity.this.SetStatusDTC(0);
                    } 
                  } catch (Exception exception) {
                    DialogManager.getInstance().setContext(PIDActivity._context);
                    DialogManager.getInstance().showDialog();
                    PIDActivity.this.setLastKnowStatusDTCSelector();
                  } 
                } else {
                  DialogManager.getInstance().setContext(PIDActivity._context);
                  DialogManager.getInstance().showDialog();
                  PIDActivity.this.setLastKnowStatusDTCSelector();
                } 
              }
            });
      } else {
        this.DTC_Selector.setChecked(paramBoolean ^ true);
      } 
    } else if (paramBoolean) {
      SetStatusDTC(0);
      dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getDeviceId(), "1");
    } else {
      SetStatusDTC(1);
      dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getDeviceId(), "0");
    } 
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
  
  public int getPosition() {
    return this.position;
  }
  
  public void onBackPressed() {
    if (!blockBackPressed)
      super.onBackPressed(); 
  }
  
  public void onClick(View paramView) {
    CustomActionButton customActionButton = this.cab_pid_1;
    if (customActionButton == null || !customActionButton.get_is_progress_showing()) {
      customActionButton = this.cab_pid_2;
      if (customActionButton == null || !customActionButton.get_is_progress_showing()) {
        customActionButton = this.cab_pid_3;
        if (customActionButton == null || !customActionButton.get_is_progress_showing()) {
          switch (paramView.getId()) {
            default:
              return;
            case 2131296446:
              if (this.cab_pid_1.get_action_service_status() == 1)
                if (this.cab_pid_3.getMyService() == Enums.Services.ActionAssistance) {
                  assistanceFunction();
                } else if (this.cab_pid_3.getMyService() == Enums.Services.DTCAction) {
                  dtcFunction();
                }  
            case 2131296445:
              if (this.cab_pid_2.get_action_service_status() == 1)
                if (this.cab_pid_2.getMyService() == Enums.Services.ActionAssistance) {
                  assistanceFunction();
                } else if (this.cab_pid_2.getMyService() == Enums.Services.DTCAction) {
                  dtcFunction();
                }  
            case 2131296444:
              break;
          } 
          if (this.cab_pid_1.get_action_service_status() == 1) {
            if (this.cab_pid_1.getMyService() == Enums.Services.ActionAssistance)
              assistanceFunction(); 
            if (this.cab_pid_1.getMyService() == Enums.Services.DTCAction)
              dtcFunction(); 
          } 
        } 
      } 
    } 
    stopProcessButtonDTC((Context)this);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    changeOrientation(paramConfiguration);
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427437);
    rtApp = (onstarApplication)getApplicationContext();
    _context = (Context)this;
    dbFunctions = new DBFunctions(_context);
    Act = this;
    this.stringsResourcesVO = new StringsResourcesVO();
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    this.pid_popup_lbl_actualizadofecha_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_popup_lbl_actualizadofecha_1, 2131690260);
    this.pid_main_lbl_aceite_9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_main_lbl_aceite_9, 2131690243);
    this.pid_main_lbl_presionllantas_10 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_main_lbl_presionllantas_10, 2131690252);
    this.pid_main_lbl_psi_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_main_lbl_psi_1, 2131690253);
    this.pid_main_lbl_kmaproximado = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_main_lbl_kmaproximado, 2131690250);
    this.pid_main_lbl_totalkm_4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_main_lbl_totalkm_4, 2131690255);
    this.pid_main_lbl_km_5 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_main_lbl_km_5, 2131690249);
    this.pid_main_lbl_na_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.pid_main_lbl_na_1, 2131690251);
    this.global_popup_lbl_accionencurso_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
    this.global_popup_btn_aceptar_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    this.asistencia_global_popup_lbl_llamadaasistencia_2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
    this.global_popup_btn_llamar_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_llamar_1, 2131689948);
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      int i = Utilities.getDevicePositionByDeviceId(bundle.getString("PUSH_NOTIFICATION_DEVICE_ID"));
      if (i != -1) {
        UserPreferenceVO userPreferenceVO = dbFunctions.getUserPreference(GlobalMembers.userLogged);
        UserDevicesVO userDevicesVO = rtApp.getmDeviceUserList().get(i);
        dbFunctions.addVehicleSelected(getApplicationContext(), userPreferenceVO.getUser(), userDevicesVO);
        Utilities.updateVehicleSelected(_context, userPreferenceVO.getUser(), userDevicesVO);
      } 
    } 
    if (paramBundle != null)
      this.stateTutorial = paramBundle.getBoolean(this.STATE_TUTORIAL); 
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    getMenuInflater().inflate(2131492874, paramMenu);
    ActionBar actionBar = getActionBar();
    actionBar.setBackgroundDrawable((Drawable)new ColorDrawable(-1));
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    View view = getLayoutInflater().inflate(2131427419, null);
    actionBar.setCustomView(view, new ActionBar.LayoutParams(-2, -1, 17));
    this.ivHeader = (ImageView)view.findViewById(2131296591);
    this.ivHeader.setImageDrawable(drawable);
    actionBar.setDisplayOptions(18);
    actionBar.setDisplayShowHomeEnabled(false);
    actionBar.setDisplayShowTitleEnabled(false);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    paramMenuItem.getItemId();
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onRefresh() {
    if (!verifyActionRunning()) {
      boolean bool = NetUtilities.validateNetwork(_context, false, true);
      this.cab_pid_1.setEnabled(false);
      this.cab_pid_1.setClickable(false);
      this.cab_pid_2.setEnabled(false);
      this.cab_pid_2.setClickable(false);
      this.cab_pid_3.setEnabled(false);
      this.cab_pid_3.setClickable(false);
      isExecuteAction = true;
      if (!bool) {
        this.swipeLayout.setRefreshing(false);
        OrientationManager.unlockOrientation(this);
      } else {
        String str3 = rtApp.getSessionKey();
        String str1 = rtApp.getUserAccessData()[1];
        String str2 = rtApp.getAccountID();
        String str5 = rtApp.getLocatorUserId();
        String str4 = Enums.Services.pid.GetCodeString();
        blockBackPressed = true;
        ActionsProcess actionsProcess = new ActionsProcess((Context)this, this.swipeLayout, this.txt_swipe, str4, this.deviceId);
        actionsProcess.setOnPostExecuteListener(new ActionsProcess.OnPostExecuteListener() {
              final PIDActivity this$0;
              
              public void onPostExecuteListener(ActionResultVO param1ActionResultVO) {
                PIDActivity.access$602(false);
                PIDActivity.this.swipeLayout.setRefreshing(false);
                PIDActivity.access$802(false);
                if (!param1ActionResultVO.isValidPID()) {
                  PIDVO pIDVO = PIDActivity.dbFunctions.getLastPid(PIDActivity.this.deviceId);
                  if (pIDVO != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(PIDActivity.this.deviceId);
                    stringBuilder.append(": Reading Historic PID: ");
                    stringBuilder.append(Enums.Services.pid.GetCode());
                    Utilities.escribeArchivo("PIDActivity", "WCF", stringBuilder.toString());
                    param1ActionResultVO.setModelo(pIDVO.getModelo());
                    param1ActionResultVO.setVersion(pIDVO.getVersion());
                    param1ActionResultVO.setYear(pIDVO.getYear());
                    param1ActionResultVO.setPlaca(pIDVO.getPlaca());
                    param1ActionResultVO.setOdometer(pIDVO.getOdometer());
                    param1ActionResultVO.setGas(pIDVO.getGas());
                    param1ActionResultVO.setOilValue(pIDVO.getOilValue());
                    param1ActionResultVO.setOilStatus(pIDVO.getOilStatus());
                    param1ActionResultVO.setBatteryLevel(pIDVO.getBatterylevel());
                    param1ActionResultVO.setTireStatusFL(pIDVO.getTire_status_fl());
                    param1ActionResultVO.setTireFL(pIDVO.getTire_fl());
                    param1ActionResultVO.setTireStatusFR(pIDVO.getTire_status_fr());
                    param1ActionResultVO.setTireFR(pIDVO.getTire_fr());
                    param1ActionResultVO.setTireStatusRL(pIDVO.getTire_status_rl());
                    param1ActionResultVO.setTireRL(pIDVO.getTire_rl());
                    param1ActionResultVO.setTireStatusRR(pIDVO.getTire_status_rr());
                    param1ActionResultVO.setTireRR(pIDVO.getTire_rr());
                    param1ActionResultVO.setTPMSText(pIDVO.getTPMSText());
                    param1ActionResultVO.setIsM300(pIDVO.getIsM300());
                    param1ActionResultVO.setAutonomy_km(pIDVO.getAutonomy_km());
                    param1ActionResultVO.setAutonomy_status(pIDVO.getAutonomy_status());
                    param1ActionResultVO.setAutonomy_text(pIDVO.getAutonomy_text());
                    param1ActionResultVO.setEventDateTime(pIDVO.getEventDate());
                    param1ActionResultVO.setIdResponse(Enums.ActionResultCode.Activated);
                    PIDActivity.this.frl_pid_no_info.setVisibility(8);
                    PIDActivity.this.lin_pid_info.setVisibility(0);
                    PIDActivity.this.setResultOnGlobals(param1ActionResultVO);
                    PIDActivity.this.enableButtons();
                    PIDActivity.this.loadPIDs();
                  } else {
                    param1ActionResultVO.setIdResponse(Enums.ActionResultCode.Fail);
                    PIDActivity.this.frl_pid_no_info.setVisibility(0);
                    PIDActivity.this.lin_pid_info.setVisibility(4);
                    PIDActivity.this.enableButtons();
                  } 
                } else {
                  param1ActionResultVO.setIdResponse(Enums.ActionResultCode.Activated);
                  PIDActivity.this.frl_pid_no_info.setVisibility(8);
                  PIDActivity.this.lin_pid_info.setVisibility(0);
                  PIDActivity.this.setResultOnGlobals(param1ActionResultVO);
                  PIDActivity.this.enableButtons();
                  PIDActivity.this.loadPIDs();
                } 
              }
            });
        actionsProcess.executeOnExecutor(Executors.newSingleThreadExecutor(), (Object[])new String[] { str3, str1, str2, str5, str4, "" });
      } 
    } else {
      this.swipeLayout.setRefreshing(false);
    } 
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle) {
    super.onRestoreInstanceState(paramBundle);
    paramBundle.putInt("gas", this.gas);
    this.stateTutorial = paramBundle.getBoolean(this.STATE_TUTORIAL);
  }
  
  protected void onResume() {
    this.deviceId = Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getDeviceId();
    rtApp.getAccountID();
    Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getName();
    this.layoutOdometer = (LinearLayout)findViewById(2131296937);
    this.layoutGasoline = (LinearLayout)findViewById(2131296561);
    this.layoutOil = (LinearLayout)findViewById(2131296938);
    this.layoutTirePressure = (LinearLayout)findViewById(2131297121);
    this.footerButtonPids = (LinearLayout)findViewById(2131296546);
    this.layoutInfoVehiculo = (LinearLayout)findViewById(2131296650);
    this.header_vehiculo_name = (LinearLayout)findViewById(2131296587);
    this.layoutModeloPlaca = (LinearLayout)findViewById(2131296915);
    this.frl_pid_swipe = (FrameLayout)findViewById(2131296559);
    this.frl_pid_no_info = (FrameLayout)findViewById(2131296558);
    this.lin_pid_info = (LinearLayout)findViewById(2131296809);
    this.layoutModeloPlaca.setOnClickListener(new View.OnClickListener() {
          final PIDActivity this$0;
          
          public void onClick(View param1View) {}
        });
    this.lay_driverprofile = (LinearLayout)findViewById(2131296690);
    this.lay_driverprofile.setOnClickListener(new View.OnClickListener() {
          final PIDActivity this$0;
          
          public void onClick(View param1View) {
            PIDActivity.this.launcherCarInfoFunction();
          }
        });
    this.lbl_pid_no_info = (TextView)findViewById(2131296763);
    this.lbl_pid_no_info2 = (TextView)findViewById(2131296764);
    TextView textView = (TextView)findViewById(2131296765);
    this.pocentajeOil = (TextView)findViewById(2131296958);
    this.txt_pid_main_lbl_km_5 = (TextView)findViewById(2131296954);
    this.textTireInmutable = (TextView)findViewById(2131297100);
    this.pid_dtc = (TextView)findViewById(2131296953);
    this.swipeLayout = (SwipeRefreshLayout)findViewById(2131297063);
    this.actionbar_vehicle = (TextView)findViewById(2131296341);
    this.txtdate = (TextView)findViewById(2131297210);
    this.txt_bannerVehiculo = (TextView)findViewById(2131297193);
    this.imgPid = (ImageView)findViewById(2131296663);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.pidcar_info, 2131165641);
    this.imgPid.setImageDrawable(drawable);
    this.txt_swipe = (TextView)findViewById(2131297187);
    this.txt_modelo = (TextView)findViewById(2131297095);
    this.txt_placa = (TextView)findViewById(2131297097);
    this.txt_info_pidOdometer = (TextView)findViewById(2131297199);
    this.txt_pidOdometer1 = (TextView)findViewById(2131297201);
    this.txt_pidOdometer2 = (TextView)findViewById(2131297202);
    this.txt_pidOdometer3 = (TextView)findViewById(2131297203);
    this.txt_pidOdometer4 = (TextView)findViewById(2131297204);
    this.txt_pidOdometer5 = (TextView)findViewById(2131297205);
    this.txt_pidOdometer6 = (TextView)findViewById(2131297206);
    this.imgGas = (ImageView)findViewById(2131296623);
    this.txt_porcentajeGas = (TextView)findViewById(2131296957);
    this.txt_info_gasoline = (TextView)findViewById(2131297198);
    this.lbl_pid_autonomy_text = (TextView)findViewById(2131296762);
    this.imgOilStatus = (ImageView)findViewById(2131296939);
    this.imgOil = (ImageView)findViewById(2131296624);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.pid_oil, 2131165639);
    this.imgOil.setImageDrawable(drawable);
    this.txt_porcentajeOil = (TextView)findViewById(2131296958);
    this.txt_pid_name_tire = (TextView)findViewById(2131297207);
    this.txt_tiretopleft = (TextView)findViewById(2131297122);
    this.txt_tiretopright = (TextView)findViewById(2131297123);
    this.txt_tirebottomleft = (TextView)findViewById(2131297119);
    this.txt_tirebottomright = (TextView)findViewById(2131297120);
    this.textTirePressure = (TextView)findViewById(2131297101);
    this.S_tiretopleft = (ImageView)findViewById(2131296637);
    this.S_tiretopright = (ImageView)findViewById(2131296638);
    this.S_tirebottomleft = (ImageView)findViewById(2131296635);
    this.S_tirebottomright = (ImageView)findViewById(2131296636);
    this.cab_pid_1 = (CustomActionButton)findViewById(2131296444);
    this.cab_pid_2 = (CustomActionButton)findViewById(2131296445);
    this.cab_pid_3 = (CustomActionButton)findViewById(2131296446);
    ImageView imageView = (ImageView)findViewById(2131296621);
    this.txtDriverProfile = (TextView)findViewById(2131297180);
    this.txt_dtc = (TextView)findViewById(2131297196);
    this.DTC_Selector = (SwitchCompat)findViewById(2131296272);
    this.progressDTCSelector = (ProgressBar)findViewById(2131296962);
    this.cab_pid_1.setOnClickListener(this);
    this.cab_pid_2.setOnClickListener(this);
    this.cab_pid_3.setOnClickListener(this);
    this.view_spacer_1 = findViewById(2131297242);
    this.view_spacer_2 = findViewById(2131297243);
    this.lpBanner = new LinearLayout.LayoutParams(-1, 0);
    this.lpHeader = new LinearLayout.LayoutParams(-1, 0);
    this.lp = new LinearLayout.LayoutParams(-1, 0);
    this.lpfooter = new LinearLayout.LayoutParams(-1, 0);
    this.tip_pid_layout = (LinearLayout)findViewById(2131297115);
    this.swipeLayout.setOnRefreshListener(this);
    this.swipeLayout.setColorSchemeResources(new int[] { 2131034155 });
    if (GlobalMembers.activeTutorial) {
      this.idTutorial = (LinearLayout)findViewById(2131296607);
      this.idTutorial.setOnClickListener(this.clickTutorial);
      this.idTutorial.setVisibility(0);
    } 
    formattedFont();
    newFooterButtons();
    AlertOn();
    changeOrientation(getResources().getConfiguration());
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      try {
        bundle.getString("gasoline");
        bundle.getString("battery");
        this.strOdometer = bundle.getString("odometer");
        this.strOilValue = bundle.getString("oilValue");
        this.strOilStatus = bundle.getString("oilStatus");
        this.strTireFL = bundle.getString("tireFL");
        this.strTireFR = bundle.getString("tireFR");
        this.strTireRL = bundle.getString("tireRL");
        this.strTireRR = bundle.getString("tireRR");
        this.modelo = bundle.getString("modelo");
        this.version = bundle.getString("version");
        this.year = bundle.getString("year");
        this.placa = bundle.getString("placa");
        this.tpms = bundle.getString("tpms");
        this.isM300 = bundle.getString("isM300");
        this.autonomy_km = bundle.getString("autonomy_km");
        this.autonomy_status = bundle.getString("autonomy_status");
        this.autonomy_text = bundle.getString("autonomy_text");
        this.strTireStatusFL = bundle.getString("tireStatusFL");
        this.strTireStatusFR = bundle.getString("tireStatusFR");
        this.strTireStatusRL = bundle.getString("tireStatusRL");
        this.strTireStatusRR = bundle.getString("tireStatusRR");
        this.eventDate = bundle.getString("eventDate");
        bundle.getString("DeviceName");
        loadPIDs();
      } catch (Exception exception) {
        Utilities.escribeArchivo("PIDActivity", "Error bundle", exception.getMessage());
        searchOnDB();
      } 
    } else {
      searchOnDB();
    } 
    GETStatusDTCSelector();
    this.DTC_Selector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final PIDActivity this$0;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            PIDActivity.this.UPDATEStatusDTCSelector(param1Boolean);
          }
        });
    AlertOn();
    if (blockBackPressed)
      this.swipeLayout.setRefreshing(true); 
    callShowTuto();
    super.onResume();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    this.gas = paramBundle.getInt("gas");
    paramBundle.putBoolean(this.STATE_TUTORIAL, this.stateTutorial);
  }
  
  public void setActionButtons() {
    try {
      String str3 = Utilities.getStringFromConfigList(_context, this.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
      String str2 = Utilities.getStringFromConfigList(_context, this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
      Utilities.getStringFromConfigList(_context, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
      String str1 = Utilities.getStringFromConfigList(_context, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
      Dialog dialog = Utilities.simpleDialog(_context, null, str1, str2, true, str3, false, "", 20.0F, 20.0F);
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
      Utilities.escribeArchivo("PIDActivity", "Error: dialogBackALert", exception.getMessage());
    } 
  }
  
  public void setDisableOrientation() {
    OrientationManager.unlockOrientation(this);
  }
  
  public void setLastKnowStatusDTCSelector() {
    if (dbFunctions.getVehicleDtcStatus(Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getDeviceId()).equals("0")) {
      IsCheckStatusDTC_Selector = true;
      this.DTC_Selector.setChecked(false);
      IsCheckStatusDTC_Selector = false;
      SetStatusDTC(1);
      dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getDeviceId(), "0");
    } else {
      IsCheckStatusDTC_Selector = true;
      this.DTC_Selector.setChecked(true);
      IsCheckStatusDTC_Selector = false;
      SetStatusDTC(0);
      dbFunctions.updateVehicleDTCSelector(Utilities.getLastKnownDeviceSelected(rtApp, "PIDActivity").getDeviceId(), "1");
    } 
  }
  
  public void setPosition(int paramInt) {
    this.position = paramInt;
  }
  
  public void setPressTuto(boolean paramBoolean) {
    this.pressTuto = paramBoolean;
  }
  
  public void setStateTutorial(boolean paramBoolean) {
    this.stateTutorial = paramBoolean;
  }
  
  public ClassElements setViews() {
    ClassElements classElements = new ClassElements();
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_deshabilitarnotificaciones, 2131690444);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_agendarrevision, 2131690438);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_verinformacion, 2131690466);
    SwitchCompat switchCompat = this.DTC_Selector;
    if (switchCompat != null)
      ShowViewElement.setValues(classElements, (View)switchCompat, 0, str2, "izquierdaArriba", 0, getResources().getInteger(2131361812), false, -getResources().getInteger(2131361818), getResources().getInteger(2131361805), false); 
    CustomActionButton customActionButton = this.cab_pid_2;
    if (customActionButton != null)
      ShowViewElement.setValues(classElements, (View)customActionButton, 1, "", "solo", 0, 0, false, 0, 0, false); 
    SwipeRefreshLayout swipeRefreshLayout = this.swipeLayout;
    if (swipeRefreshLayout != null)
      ShowViewElement.setValues(classElements, (View)swipeRefreshLayout, 2, str1, "solo", 0, getResources().getInteger(2131361818), false, 0, 0, false); 
    return classElements;
  }
  
  public void showTutorial() {
    ClassElements classElements = setViews();
    this.position = 0;
    this.stateTutorial = true;
    this.pressTuto = false;
    this.show = new ShowViewElement(getApplicationContext(), this, classElements);
    this.show.setPages(1);
  }
  
  private class TimerThread implements Runnable {
    final PIDActivity this$0;
    
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
        if (PIDActivity.iPreviousCallIndex != 0)
          bool2 = false; 
        if ((bool1 & bool2) != 0) {
          try {
            Thread.sleep(1000L);
            b++;
          } catch (InterruptedException interruptedException) {
            Utilities.escribeArchivo("PIDActivity", "Error: timer", interruptedException.toString());
          } 
          continue;
        } 
        PIDActivity.this.runOnUiThread(new Runnable(this) {
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
    null(PIDActivity this$0) {}
    
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


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/pid/PIDActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */