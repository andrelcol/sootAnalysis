package com.roadtrack.onstar.ui.my_plan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.roadtrack.onstar.BO.BluetoothServiceRT;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.ManagerNotificationPlatinum;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.RenewalPlanO;
import com.roadtrack.onstar.VO.RenewalPlansListResponseO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.SuscriptionResponse;
import com.roadtrack.onstar.VO.TransactionResponse;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.adapter.RenewalPlansAdapter;
import com.roadtrack.onstar.adapter.VehiculeSpinnerAdapter;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.async_tasks.intefaces.RenewalPlans_Interface;
import com.roadtrack.onstar.async_tasks.tasks.GetLocatorToken;
import com.roadtrack.onstar.async_tasks.tasks.GetPaymentTransactionTask;
import com.roadtrack.onstar.async_tasks.tasks.GetPdfCipherKeyTask;
import com.roadtrack.onstar.async_tasks.tasks.GetRegistrationWeb;
import com.roadtrack.onstar.async_tasks.tasks.GetRenewalPlansTask;
import com.roadtrack.onstar.entities.ActiveCallEntity;
import com.roadtrack.onstar.gson.GsonC;
import com.roadtrack.onstar.interfaces.DialogToActivity;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import com.roadtrack.onstar.ui.dialogs.ActivityCall;
import com.roadtrack.onstar.ui.dialogs.DialogPaymentOptions;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.viewTutorial.ClassElements;
import com.roadtrack.onstar.viewTutorial.ShowViewElement;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class OriginalRenewalActivity extends Activity implements RenewalPlansAdapter.dialogToActivity {
  public static Context _context;
  
  private static ActiveCallEntity actCall;
  
  public static boolean bBotonIVRPulsado = false;
  
  public static boolean bRelayReady = true;
  
  public static int iPreviousCallIndex = 0;
  
  private static boolean isInitiatingCall = false;
  
  public static BluetoothServiceRT mChatService;
  
  private static ManagerNotificationPlatinum mNPlatinum;
  
  private String HistoricoDeBoletos;
  
  public final String TAG = OriginalRenewalActivity.class.getSimpleName();
  
  private Button btnAccept;
  
  private Button btnAccept1;
  
  private Button btnCancel;
  
  private Button btnCancel1;
  
  private Button btnOK;
  
  private Button btnOK1;
  
  private Button btnOK2;
  
  private Button btnOK3;
  
  private Button btn_left;
  
  private Button btn_right;
  
  private Button buttonOk;
  
  private LinearLayout buttonTutorial;
  
  public View.OnClickListener clickTutorial;
  
  DBFunctions dbFun;
  
  private String global_lbl_acciondescfallared_1;
  
  private String global_lbl_asistencia_1;
  
  private String global_lbl_conexiondered_1;
  
  private String global_popup_btn_aceptar_1;
  
  private String global_popup_btn_llamar_1;
  
  private String global_popup_btn_no_1;
  
  private String global_popup_btn_si_1;
  
  private String global_popup_lbl_aviso_1;
  
  private String global_popup_lbl_continuar;
  
  private TextView lbl_aor_cap_line;
  
  private TextView lbl_aor_current_plan;
  
  private TextView lbl_aor_expiration_date;
  
  private TextView lbl_aor_title;
  
  private LinearLayout lin_aor_cap_line;
  
  RenewalPlansAdapter.dialogToActivity listener;
  
  private ListView lv_aor_plan;
  
  private ProgressBar pb_aor_cap_line;
  
  private List<RenewalPlanO> plansList;
  
  private RenewalPlansAdapter renewalPlansAdapter;
  
  private String renovacion_lbl_planactual;
  
  private String renovacion_lbl_planes;
  
  private String renovacion_lbl_validez;
  
  private RenewalPlansListResponseO responseO;
  
  onstarApplication rtApp;
  
  private ShowViewElement show;
  
  private Spinner spinner_menu;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Typeface tfLouis;
  
  private UserPreferenceVO userPreference;
  
  private View view_aor_cap_line;
  
  public OriginalRenewalActivity() {
    new BroadcastReceiver() {
        final OriginalRenewalActivity this$0;
        
        public void onReceive(Context param1Context, Intent param1Intent) {
          if (param1Intent.getAction().equals("com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT")) {
            param1Intent.getExtras();
            String str1 = param1Intent.getStringExtra("Response");
            String str2 = param1Intent.getStringExtra("Response2");
            int j = Integer.parseInt(str1);
            int k = str2.equals("0");
            int i = OriginalRenewalActivity.iPreviousCallIndex;
            boolean bool = false;
            if (i != 0) {
              i = 1;
            } else {
              i = 0;
            } 
            if ((k & i) != 0)
              (new Thread(new Runnable() {
                    final OriginalRenewalActivity.null this$1;
                    
                    public void run() {
                      try {
                        Thread.sleep(5000L);
                      } catch (InterruptedException interruptedException) {
                        Utilities.escribeArchivo(OriginalRenewalActivity.this.TAG, "Error: Timer", interruptedException.toString());
                      } 
                      OriginalRenewalActivity.this.runOnUiThread(new Runnable(this) {
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
            OriginalRenewalActivity.iPreviousCallIndex = Integer.parseInt(str2);
            if (GlobalMembers.btnpress != 1)
              if (!GlobalMembers.bActivityCallRunning) {
                boolean bool1 = bool;
                if (OriginalRenewalActivity.iPreviousCallIndex != 0)
                  bool1 = true; 
                if (((GlobalMembers.bStartingCall ^ true) & bool1) != 0) {
                  GlobalMembers.bStartingCall = true;
                  Intent intent = new Intent((Context)OriginalRenewalActivity.this, ActivityCall.class);
                  intent.putExtra("Boton", "Llamada");
                  OriginalRenewalActivity.access$1602(true);
                  OriginalRenewalActivity.iPreviousCallIndex = 1;
                  OriginalRenewalActivity.this.startActivityForResult(intent, 123);
                } 
              } else {
                ActivityCall.sendCallStatusRecived(j, Integer.parseInt(str2));
              }  
          } 
        }
      };
    this.clickTutorial = new View.OnClickListener() {
        final OriginalRenewalActivity this$0;
        
        public void onClick(View param1View) {
          if (OriginalRenewalActivity.this.lbl_aor_cap_line.getVisibility() == 0) {
            ClassElements classElements = new ClassElements();
            OriginalRenewalActivity originalRenewalActivity2 = OriginalRenewalActivity.this;
            String str = Utilities.getStringFromConfigList((Context)originalRenewalActivity2, originalRenewalActivity2.stringsResourcesVO.TutorialAyudaBradesco, 2131689559);
            ShowViewElement.setValues(classElements, (View)OriginalRenewalActivity.this.lbl_aor_cap_line, 0, str, "derechaArriba", 0, OriginalRenewalActivity.this.getResources().getInteger(2131361812), false, -OriginalRenewalActivity.this.getResources().getInteger(2131361818), OriginalRenewalActivity.this.getResources().getInteger(2131361805), false);
            OriginalRenewalActivity originalRenewalActivity1 = OriginalRenewalActivity.this;
            OriginalRenewalActivity.access$1802(originalRenewalActivity1, new ShowViewElement(OriginalRenewalActivity._context, originalRenewalActivity1, classElements));
            OriginalRenewalActivity.this.show.setPages(1);
          } 
        }
      };
  }
  
  public static boolean CallOrHangUp(Enums.Calls paramCalls) {
    // Byte code:
    //   0: ldc com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity
    //   2: monitorenter
    //   3: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.isInitiatingCall : Z
    //   6: istore_3
    //   7: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.iPreviousCallIndex : I
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
    //   26: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   29: ifnull -> 226
    //   32: iload_2
    //   33: istore_1
    //   34: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity$32.$SwitchMap$com$roadtrack$onstar$Enums$Calls : [I
    //   37: aload_0
    //   38: invokevirtual ordinal : ()I
    //   41: iaload
    //   42: tableswitch default -> 84, 1 -> 196, 2 -> 165, 3 -> 134, 4 -> 226, 5 -> 89, 6 -> 226, 7 -> 226
    //   84: iload_2
    //   85: istore_1
    //   86: goto -> 226
    //   89: iload_2
    //   90: istore_1
    //   91: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   94: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   97: getstatic com/roadtrack/onstar/Enums$CallNumberType.VR : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   100: if_acmpne -> 226
    //   103: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   106: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   109: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   112: if_acmpeq -> 126
    //   115: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity._context : Landroid/content/Context;
    //   118: ldc 'RTMobile_HMI'
    //   120: invokestatic wakeDevice : (Landroid/content/Context;Ljava/lang/String;)V
    //   123: goto -> 224
    //   126: invokestatic stopWakeDevice : ()V
    //   129: iload_2
    //   130: istore_1
    //   131: goto -> 226
    //   134: iload_2
    //   135: istore_1
    //   136: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   139: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   142: getstatic com/roadtrack/onstar/Enums$CallNumberType.Agendamiento : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   145: if_acmpne -> 226
    //   148: iload_2
    //   149: istore_1
    //   150: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   153: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   156: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   159: if_acmpeq -> 226
    //   162: goto -> 224
    //   165: iload_2
    //   166: istore_1
    //   167: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   170: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   173: getstatic com/roadtrack/onstar/Enums$CallNumberType.Emergency : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   176: if_acmpne -> 226
    //   179: iload_2
    //   180: istore_1
    //   181: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   184: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   187: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   190: if_acmpeq -> 226
    //   193: goto -> 224
    //   196: iload_2
    //   197: istore_1
    //   198: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   201: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   204: getstatic com/roadtrack/onstar/Enums$CallNumberType.Assistance : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   207: if_acmpne -> 226
    //   210: iload_2
    //   211: istore_1
    //   212: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   215: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   218: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   221: if_acmpeq -> 226
    //   224: iconst_1
    //   225: istore_1
    //   226: iload_1
    //   227: ifeq -> 279
    //   230: new com/roadtrack/onstar/entities/ActiveCallEntity
    //   233: astore #4
    //   235: aload #4
    //   237: invokespecial <init> : ()V
    //   240: aload #4
    //   242: putstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   245: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   248: getstatic com/roadtrack/onstar/Enums$DeviceCall.BT : Lcom/roadtrack/onstar/Enums$DeviceCall;
    //   251: invokevirtual setDevice : (Lcom/roadtrack/onstar/Enums$DeviceCall;)V
    //   254: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   257: iconst_0
    //   258: invokevirtual setCallIndex : (I)V
    //   261: iconst_0
    //   262: putstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.isInitiatingCall : Z
    //   265: aload_0
    //   266: getstatic com/roadtrack/onstar/Enums$Calls.IVR : Lcom/roadtrack/onstar/Enums$Calls;
    //   269: if_acmpne -> 279
    //   272: invokestatic getInstance : ()Lcom/roadtrack/onstar/platinum/MultiModalHMI;
    //   275: iconst_2
    //   276: invokevirtual closeMenuHMI : (I)V
    //   279: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   282: ifnonnull -> 291
    //   285: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   288: putstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   291: iload_1
    //   292: ifeq -> 316
    //   295: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   298: getstatic com/roadtrack/onstar/Enums$ActionCall.Hang_Up : Lcom/roadtrack/onstar/Enums$ActionCall;
    //   301: invokevirtual setAction : (Lcom/roadtrack/onstar/Enums$ActionCall;)V
    //   304: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   307: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   310: invokevirtual callControl : (Lcom/roadtrack/onstar/entities/ActiveCallEntity;)V
    //   313: goto -> 385
    //   316: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.bBotonIVRPulsado : Z
    //   319: ifne -> 327
    //   322: iconst_1
    //   323: istore_1
    //   324: goto -> 329
    //   327: iconst_0
    //   328: istore_1
    //   329: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.iPreviousCallIndex : I
    //   332: ifne -> 340
    //   335: iconst_1
    //   336: istore_2
    //   337: goto -> 342
    //   340: iconst_0
    //   341: istore_2
    //   342: iload_1
    //   343: iload_2
    //   344: iand
    //   345: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.bRelayReady : Z
    //   348: iand
    //   349: ifeq -> 365
    //   352: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   355: aload_0
    //   356: ldc_w ''
    //   359: invokevirtual initiatingCall : (Lcom/roadtrack/onstar/Enums$Calls;Ljava/lang/String;)V
    //   362: goto -> 385
    //   365: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.bBotonIVRPulsado : Z
    //   368: ifeq -> 390
    //   371: iconst_0
    //   372: putstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.bBotonIVRPulsado : Z
    //   375: getstatic com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   378: aload_0
    //   379: ldc_w ''
    //   382: invokevirtual initiatingCall : (Lcom/roadtrack/onstar/Enums$Calls;Ljava/lang/String;)V
    //   385: ldc com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity
    //   387: monitorexit
    //   388: iconst_1
    //   389: ireturn
    //   390: ldc com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity
    //   392: monitorexit
    //   393: iconst_0
    //   394: ireturn
    //   395: astore_0
    //   396: ldc com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity
    //   398: monitorexit
    //   399: aload_0
    //   400: athrow
    // Exception table:
    //   from	to	target	type
    //   3	13	395	finally
    //   26	32	395	finally
    //   34	84	395	finally
    //   91	123	395	finally
    //   126	129	395	finally
    //   136	148	395	finally
    //   150	162	395	finally
    //   167	179	395	finally
    //   181	193	395	finally
    //   198	210	395	finally
    //   212	224	395	finally
    //   230	279	395	finally
    //   279	291	395	finally
    //   295	313	395	finally
    //   316	322	395	finally
    //   329	335	395	finally
    //   342	362	395	finally
    //   365	385	395	finally
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
    if (!GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
      if (!isBtAndPlatinumConnected()) {
        String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
        Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_btnasistenciamenu_onstar_dialog, 2131165443);
        final Dialog dialog = Utilities.simpleDialog((Context)this, null, this.global_lbl_asistencia_1, str, true, this.global_popup_btn_llamar_1, false, this.global_popup_btn_no_1);
        this.btnAccept = (Button)dialog.findViewById(2131296343);
        this.btnCancel = (Button)dialog.findViewById(2131296344);
        this.btnAccept.setOnClickListener(new View.OnClickListener() {
              final OriginalRenewalActivity this$0;
              
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                OriginalRenewalActivity.this.assistanceCall();
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
      String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
      Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_btnasistenciamenu_onstar_dialog, 2131165443);
      final Dialog dialog = Utilities.simpleDialog((Context)this, null, this.global_lbl_asistencia_1, str, true, this.global_popup_btn_llamar_1, false, this.global_popup_btn_no_1);
      this.btnAccept1 = (Button)dialog.findViewById(2131296343);
      this.btnCancel1 = (Button)dialog.findViewById(2131296344);
      this.btnAccept1.setOnClickListener(new View.OnClickListener() {
            final OriginalRenewalActivity this$0;
            
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              OriginalRenewalActivity.this.assistanceCall();
              dialog.dismiss();
            }
          });
      this.btnCancel1.setOnClickListener(new View.OnClickListener(this) {
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              dialog.dismiss();
            }
          });
      dialog.show();
      GlobalMembers.isShowingDialog = true;
    } 
  }
  
  private void cleanView() {
    this.plansList = new ArrayList<RenewalPlanO>();
    this.renewalPlansAdapter = new RenewalPlansAdapter(getApplicationContext(), this.plansList, this, this.responseO);
    this.listener = this;
    this.renewalPlansAdapter.setListener(this.listener);
    this.lv_aor_plan.setAdapter((ListAdapter)this.renewalPlansAdapter);
    this.lbl_aor_current_plan.setText(this.renovacion_lbl_planactual);
    this.lbl_aor_expiration_date.setText(this.renovacion_lbl_validez);
  }
  
  private SuscriptionResponse decryptResponse(SuscriptionResponse paramSuscriptionResponse) {
    paramSuscriptionResponse.setSubscres1(Utilities.DecryptMoip(paramSuscriptionResponse.getSubscres1()));
    paramSuscriptionResponse.setSubscres2(paramSuscriptionResponse.getSubscres2());
    paramSuscriptionResponse.setSubscres3(Utilities.DecryptMoip(paramSuscriptionResponse.getSubscres3()));
    String str = this.TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("subscres1 = ");
    stringBuilder.append(paramSuscriptionResponse.getSubscres1());
    stringBuilder.append(" subscres2 = ");
    stringBuilder.append(paramSuscriptionResponse.getSubscres2());
    stringBuilder.append(" subscres3 = ");
    stringBuilder.append(paramSuscriptionResponse.getSubscres3());
    Utilities.escribeArchivo(str, "SuscriptionResponse", stringBuilder.toString());
    return paramSuscriptionResponse;
  }
  
  private TransactionResponse decryptResponse(TransactionResponse paramTransactionResponse) {
    paramTransactionResponse.setCposres1(Utilities.DecryptMoip(paramTransactionResponse.getCposres1()));
    paramTransactionResponse.setCposres2(paramTransactionResponse.getCposres2());
    paramTransactionResponse.setCposres3(Utilities.DecryptMoip(paramTransactionResponse.getCposres3()));
    String str = this.TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("cposres1 = ");
    stringBuilder.append(paramTransactionResponse.getCposres1());
    stringBuilder.append(" cposres2 = ");
    stringBuilder.append(paramTransactionResponse.getCposres2());
    stringBuilder.append(" cposres3 = ");
    stringBuilder.append(paramTransactionResponse.getCposres3());
    Utilities.escribeArchivo(str, "TransactionResponse", stringBuilder.toString());
    return paramTransactionResponse;
  }
  
  private void fillInformation(RenewalPlansListResponseO paramRenewalPlansListResponseO) {
    if (paramRenewalPlansListResponseO != null) {
      this.responseO = paramRenewalPlansListResponseO;
      this.plansList = this.responseO.getCpres5();
      this.listener = this;
      this.renewalPlansAdapter = new RenewalPlansAdapter(getApplicationContext(), this.plansList, this, paramRenewalPlansListResponseO);
      this.renewalPlansAdapter.setListener(this.listener);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.renovacion_lbl_planactual);
      stringBuilder.append(" <b>");
      stringBuilder.append(this.responseO.getCpres2());
      stringBuilder.append("</b>");
      String str1 = stringBuilder.toString();
      this.lbl_aor_current_plan.setText((CharSequence)Html.fromHtml(str1));
      String str2 = this.responseO.getCpres4();
      str1 = "";
      if (str2.equals("")) {
        this.lbl_aor_expiration_date.setVisibility(4);
      } else {
        String[] arrayOfString = this.responseO.getCpres4().split(" ");
        if (arrayOfString.length > 1)
          str1 = arrayOfString[0]; 
        this.lbl_aor_expiration_date.setVisibility(0);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(this.renovacion_lbl_validez);
        stringBuilder1.append(" <b>");
        stringBuilder1.append(str1);
        stringBuilder1.append("</b>");
        str1 = stringBuilder1.toString();
        this.lbl_aor_expiration_date.setText((CharSequence)Html.fromHtml(str1));
      } 
      this.lv_aor_plan.setAdapter((ListAdapter)this.renewalPlansAdapter);
    } else {
      onResponseError();
    } 
  }
  
  private void fillVehicleList(Spinner paramSpinner, Context paramContext) {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity}} */
    Spinner spinner = paramSpinner;
    if (paramSpinner == null)
      try {
        spinner = this.spinner_menu;
      } catch (Exception exception) {
      
      } finally {} 
    if (spinner == null) {
      /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity}} */
      return;
    } 
    ArrayList<String> arrayList = new ArrayList();
    this();
    byte b = 0;
    int i;
    for (i = 0; i < this.rtApp.getmDeviceUserList().size(); i++)
      arrayList.add(((UserDevicesVO)this.rtApp.getmDeviceUserList().get(i)).getName()); 
    i = b;
    if (arrayList.size() > 0) {
      VehiculeSpinnerAdapter vehiculeSpinnerAdapter = new VehiculeSpinnerAdapter();
      this(paramContext, 2131427512, 2131297225, 2131297226, this.spinner_menu, arrayList);
      spinner.setAdapter((SpinnerAdapter)vehiculeSpinnerAdapter);
      i = Utilities.getLastKnownVehicleSelected(getApplicationContext(), this.userPreference.getUser(), this.rtApp);
      spinner.setSelection(i);
    } 
    ((UserDevicesVO)this.rtApp.getmDeviceUserList().get(i)).getSeguroApp();
    Utilities.setDeviceType(this.rtApp);
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        final OriginalRenewalActivity this$0;
        
        @SuppressLint({"NewApi"})
        public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
          OriginalRenewalActivity originalRenewalActivity1 = OriginalRenewalActivity.this;
          UserDevicesVO userDevicesVO2 = Utilities.getLastKnownDeviceSelected(originalRenewalActivity1.rtApp, originalRenewalActivity1.TAG);
          UserDevicesVO userDevicesVO1 = OriginalRenewalActivity.this.rtApp.getmDeviceUserList().get(param1Int);
          OriginalRenewalActivity originalRenewalActivity2 = OriginalRenewalActivity.this;
          originalRenewalActivity2.dbFun.addVehicleSelected(originalRenewalActivity2.getApplicationContext(), OriginalRenewalActivity.this.userPreference.getUser(), userDevicesVO1);
          if (!userDevicesVO1.equals(userDevicesVO2)) {
            Utilities.updateVehicleSelected(OriginalRenewalActivity.this.getApplicationContext(), OriginalRenewalActivity.this.userPreference.getUser(), userDevicesVO1);
            originalRenewalActivity2 = OriginalRenewalActivity.this;
            if (originalRenewalActivity2.dbFun.userDataTableHandler(originalRenewalActivity2.rtApp.getAccountID().toString(), userDevicesVO1.getDeviceId(), "", true) || Utilities.isUUx(OriginalRenewalActivity.this.rtApp)) {
              Intent intent = new Intent(OriginalRenewalActivity.this.getApplicationContext(), MainActivity.class);
              MainActivity.Showbanner = true;
              OriginalRenewalActivity.this.startActivity(intent);
            } else {
              OriginalRenewalActivity.this.cleanView();
              OriginalRenewalActivity.this.AsyncTaskRenewal();
              if (GlobalMembers.moipCapLine)
                OriginalRenewalActivity.this.AsyncPdfCipherKey(false); 
            } 
            RemoteDiagnosticActivity.isdialog_action_process_frame_visible = false;
            MainActivity.showRenewalDialog = true;
          } 
        }
        
        public void onNothingSelected(AdapterView<?> param1AdapterView) {}
      };
    super(this);
    spinner.setOnItemSelectedListener(onItemSelectedListener);
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        final OriginalRenewalActivity this$0;
        
        public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
          boolean bool;
          if (MainActivity.onOpenDoors.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onFindMe.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onPID.booleanValue() || MainActivity.onDTC.booleanValue()) {
            bool = true;
          } else {
            bool = false;
          } 
          if (param1MotionEvent.getAction() == 1 && bool)
            try {
              String str1 = Utilities.getStringFromConfigList((Context)OriginalRenewalActivity.this, OriginalRenewalActivity.this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
              String str2 = Utilities.getStringFromConfigList((Context)OriginalRenewalActivity.this, OriginalRenewalActivity.this.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
              Dialog dialog = Utilities.simpleDialog((Context)OriginalRenewalActivity.this, null, OriginalRenewalActivity.this.global_popup_lbl_aviso_1, str1, true, str2, false, OriginalRenewalActivity.this.global_popup_btn_no_1, 20.0F, 20.0F);
              OriginalRenewalActivity.access$502(OriginalRenewalActivity.this, (Button)dialog.findViewById(2131296343));
              Button button = OriginalRenewalActivity.this.buttonOk;
              View.OnClickListener onClickListener = new View.OnClickListener() {
                  final Dialog val$dialog;
                  
                  public void onClick(View param2View) {
                    dialog.dismiss();
                  }
                };
              super(this, dialog);
              button.setOnClickListener(onClickListener);
              dialog.show();
            } catch (Exception exception) {
              Utilities.escribeArchivo(OriginalRenewalActivity.this.TAG, "Error: dialogBackALert", exception.getMessage());
            }  
          return bool;
        }
      };
    super(this);
    spinner.setOnTouchListener(onTouchListener);
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity}} */
  }
  
  private void formattedFont() {
    onstarApplication onstarApplication1 = this.rtApp;
    this.tfLouis = onstarApplication.getTypeface(_context, onstarApplication1.fontPathLouisRegular);
    this.lbl_aor_title.setTypeface(this.tfLouis);
    this.lbl_aor_title.setText(this.renovacion_lbl_planes);
    this.lbl_aor_current_plan.setTypeface(this.tfLouis);
    this.lbl_aor_current_plan.setText(this.renovacion_lbl_planactual);
    this.lbl_aor_expiration_date.setTypeface(this.tfLouis);
  }
  
  private void getPaymentMethodsDialog(final int renewalType, final LinkedHashMap<String, Object> params) {
    FragmentManager fragmentManager = getFragmentManager();
    DialogPaymentOptions dialogPaymentOptions = new DialogPaymentOptions();
    Bundle bundle = new Bundle();
    bundle.putSerializable("params", params);
    dialogPaymentOptions.setArguments(bundle);
    dialogPaymentOptions.setDialogToActivity(new DialogToActivity() {
          final OriginalRenewalActivity this$0;
          
          final LinkedHashMap val$params;
          
          final int val$renewalType;
          
          public void onOptionSelected(String param1String) {
            OriginalRenewalActivity.this.goToRenewalOption(renewalType, params);
          }
        });
    dialogPaymentOptions.show(fragmentManager, this.TAG);
  }
  
  private int getResponseStatus(String paramString1, String paramString2) {
    byte b = -1;
    int i = b;
    if (paramString1 != null) {
      i = b;
      if (!paramString1.isEmpty()) {
        paramString1 = paramString1.replace("\\\"", "\"");
        paramString1 = paramString1.substring(1, paramString1.length() - 1);
        GsonC gsonC = new GsonC();
        if (paramString2.equals(String.valueOf(0))) {
          SuscriptionResponse suscriptionResponse = new SuscriptionResponse();
          gsonC.toListObject(paramString1, suscriptionResponse);
          decryptResponse(suscriptionResponse);
          String[] arrayOfString = suscriptionResponse.getSubscres3().split("-");
          i = b;
          if (arrayOfString.length > 0) {
            String str = arrayOfString[0];
            try {
              i = Integer.parseInt(str);
            } catch (NumberFormatException numberFormatException) {
              Utilities.escribeArchivo(this.TAG, "Error status", numberFormatException.getMessage());
              i = b;
            } 
          } 
        } else {
          TransactionResponse transactionResponse = new TransactionResponse();
          gsonC.toListObject((String)numberFormatException, transactionResponse);
          decryptResponse(transactionResponse);
          String[] arrayOfString = transactionResponse.getCposres3().split("-");
          i = b;
          if (arrayOfString.length > 0) {
            String str = arrayOfString[0];
            try {
              i = Integer.parseInt(str);
            } catch (NumberFormatException numberFormatException1) {
              Utilities.escribeArchivo(this.TAG, "Error status", numberFormatException1.getMessage());
              i = b;
            } 
          } 
        } 
      } 
    } 
    return i;
  }
  
  public static ManagerNotificationPlatinum getmNPlatinum() {
    return mNPlatinum;
  }
  
  private void goToContinueDialog(int paramInt, final LinkedHashMap<String, Object> params) {
    String str1;
    Resources resources = getResources();
    String str2 = resources.getString(2131690289);
    String str4 = resources.getString(2131690317);
    String str5 = resources.getString(2131689476);
    String str3 = resources.getString(2131690338);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, 2131165484);
    if (paramInt == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaAnual.getOption()) {
      str1 = resources.getString(2131690299);
    } else if (paramInt == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaMensual.getOption()) {
      str1 = str1.getString(2131690298);
    } else {
      str1 = "";
    } 
    final Dialog dialog = Utilities.genericDialog((Context)this, drawable, str2, str4, str1, true, str5, true, str3, 18.0F, 15.0F, false, 2131690207, false);
    this.btn_left = (Button)dialog.findViewById(2131296421);
    this.btn_left.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.btn_right = (Button)dialog.findViewById(2131296420);
    this.btn_right.setOnClickListener(new View.OnClickListener() {
          final OriginalRenewalActivity this$0;
          
          final Dialog val$dialog;
          
          final LinkedHashMap val$params;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            OriginalRenewalActivity.this.processPaymentTransaction(params);
          }
        });
    dialog.show();
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
  
  private void onResponseError() {
    final Dialog dialog = Utilities.simpleDialog((Context)this, null, this.global_lbl_conexiondered_1, this.global_lbl_acciondescfallared_1, true, this.global_popup_btn_aceptar_1, false, this.global_popup_btn_si_1, false);
    this.btnOK = (Button)dialog.findViewById(2131296343);
    this.btnOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
  }
  
  private void processPaymentTransaction(final LinkedHashMap<String, Object> fullParams) {
    (new GetPaymentTransactionTask((Context)this, new AsyncResponse() {
          final OriginalRenewalActivity this$0;
          
          final LinkedHashMap val$fullParams;
          
          public void processFinish(String param1String) {
            int i = OriginalRenewalActivity.this.getResponseStatus(param1String, fullParams.get("renewalKind").toString());
            if (param1String != null && !param1String.isEmpty()) {
              if (i == 0) {
                if (OriginalRenewalActivity.this.verifyURL(param1String, fullParams.get("renewalKind").toString())) {
                  Intent intent = new Intent((Context)OriginalRenewalActivity.this, PaymentActivity.class);
                  Bundle bundle = new Bundle();
                  bundle.putString("service_response", param1String);
                  bundle.putString("renewalKind", fullParams.get("renewalKind").toString());
                  intent.putExtras(bundle);
                  OriginalRenewalActivity.this.startActivity(intent);
                } else {
                  OriginalRenewalActivity.this.onResponseError();
                } 
              } else {
                OriginalRenewalActivity.this.showKnownErrorDialog(i, fullParams);
              } 
            } else {
              OriginalRenewalActivity.this.onResponseError();
            } 
          }
        }fullParams)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void showKnownErrorDialog(int paramInt, LinkedHashMap<String, Object> paramLinkedHashMap) {
    String str2;
    final Dialog dialog2;
    String str1;
    final Dialog fullParams;
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_popup_lbl_completarregistro, 2131690329);
    switch (Enums.oneShotErrorStatus.getValue(paramInt)) {
      default:
        onResponseError();
        return;
      case EmergencyMessage:
      case IVR:
      case Redial:
      case UserDefined:
      case null:
      case null:
      case null:
      case null:
      case null:
        str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_popup_lbl_transaccionerror, 2131690339);
        dialog2 = Utilities.simpleDialog((Context)this, null, this.global_popup_lbl_aviso_1, str2, true, this.global_popup_btn_aceptar_1, false, this.global_popup_btn_si_1, false);
        this.btnOK3 = (Button)dialog2.findViewById(2131296343);
        this.btnOK3.setOnClickListener(new View.OnClickListener(this) {
              final Dialog val$dialog2;
              
              public void onClick(View param1View) {
                dialog2.dismiss();
              }
            });
        dialog2.show();
        return;
      case EmergencyCallAndMessage:
      case AgendamientoCallAndMessage:
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_popup_lbl_transaccionrealizada, 2131690341);
        dialog1 = Utilities.simpleDialog((Context)this, null, this.global_popup_lbl_aviso_1, str1, true, this.global_popup_btn_aceptar_1, false, this.global_popup_btn_si_1, false);
        this.btnOK2 = (Button)dialog1.findViewById(2131296343);
        this.btnOK2.setOnClickListener(new View.OnClickListener(this) {
              final Dialog val$dialog1;
              
              public void onClick(View param1View) {
                dialog1.dismiss();
              }
            });
        dialog1.show();
        return;
      case AssistanceCallAndMessage:
        break;
    } 
    final Dialog dialog = Utilities.simpleDialog((Context)this, null, this.global_popup_lbl_aviso_1, str3, true, this.global_popup_lbl_continuar, false, this.global_popup_btn_si_1, false);
    this.btnOK1 = (Button)dialog3.findViewById(2131296343);
    this.btnOK1.setOnClickListener(new View.OnClickListener() {
          final OriginalRenewalActivity this$0;
          
          final Dialog val$dialog;
          
          final LinkedHashMap val$fullParams;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            OriginalRenewalActivity originalRenewalActivity = OriginalRenewalActivity.this;
            UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(originalRenewalActivity.rtApp, originalRenewalActivity.TAG);
            if (userDevicesVO != null) {
              LinkedHashMap linkedHashMap = fullParams;
              if (linkedHashMap != null && linkedHashMap.size() > 0) {
                String str = userDevicesVO.getSerialnumber();
                fullParams.put("devId", str);
                fullParams.put("usr", OriginalRenewalActivity.this.rtApp.getUserAccessData()[0]);
                fullParams.put("pass", OriginalRenewalActivity.this.rtApp.getUserAccessData()[1]);
                (new GetRegistrationWeb((Context)OriginalRenewalActivity.this, new AsyncResponse() {
                      final OriginalRenewalActivity.null this$1;
                      
                      public void processFinish(String param2String) {
                        if (param2String != null && !param2String.equals("")) {
                          try {
                            Intent intent = new Intent();
                            this("android.intent.action.VIEW", Uri.parse(param2String));
                            intent.addFlags(268435456);
                            OriginalRenewalActivity.this.getApplication().startActivity(intent);
                          } catch (Exception exception) {
                            OriginalRenewalActivity.this.onResponseError();
                            Utilities.escribeArchivo(OriginalRenewalActivity.this.TAG, "loadRegistrationInfo", exception.getMessage());
                          } 
                        } else {
                          OriginalRenewalActivity.this.onResponseError();
                        } 
                      }
                    }fullParams)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
                return;
              } 
            } 
            OriginalRenewalActivity.this.onResponseError();
          }
        });
    dialog3.show();
  }
  
  private boolean verifyURL(String paramString1, String paramString2) {
    boolean bool = true;
    if (paramString1 != null && !paramString1.isEmpty()) {
      paramString1 = paramString1.replace("\\\"", "\"");
      String str = paramString1.substring(1, paramString1.length() - 1);
      GsonC gsonC = new GsonC();
      if (paramString2.equals(String.valueOf(0))) {
        SuscriptionResponse suscriptionResponse = new SuscriptionResponse();
        gsonC.toListObject(str, suscriptionResponse);
        decryptResponse(suscriptionResponse);
        if (suscriptionResponse.isValidObject()) {
          try {
          
          } catch (MalformedURLException malformedURLException) {
            Utilities.escribeArchivo(this.TAG, "Error en URL", malformedURLException.getMessage());
            bool = false;
          } 
          return bool;
        } 
      } else {
        TransactionResponse transactionResponse = new TransactionResponse();
        malformedURLException.toListObject(str, transactionResponse);
        decryptResponse(transactionResponse);
        if (transactionResponse.isValidObject()) {
          try {
            new URL(transactionResponse.getCposres2());
          } catch (MalformedURLException malformedURLException1) {
            Utilities.escribeArchivo(this.TAG, "Error en URL", malformedURLException1.getMessage());
            bool = false;
          } 
          return bool;
        } 
      } 
    } 
    bool = false;
  }
  
  public void AsyncPdfCipherKey(final boolean isButtonPressed) {
    if (isButtonPressed)
      this.pb_aor_cap_line.setVisibility(0); 
    this.lin_aor_cap_line.setClickable(false);
    (new GetPdfCipherKeyTask((Context)this, new AsyncResponse() {
          final OriginalRenewalActivity this$0;
          
          final boolean val$isButtonPressed;
          
          public void processFinish(String param1String) {
            OriginalRenewalActivity.this.processPdfCipherKey(isButtonPressed, param1String);
          }
        }isButtonPressed ^ true)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public void AsyncTaskRenewal() {
    (new GetRenewalPlansTask((Context)this, new RenewalPlans_Interface() {
          final OriginalRenewalActivity this$0;
          
          public void processFinish(RenewalPlansListResponseO param1RenewalPlansListResponseO) {
            if (param1RenewalPlansListResponseO != null) {
              if (param1RenewalPlansListResponseO.getCpres5() != null && param1RenewalPlansListResponseO.getCpres5().size() > 0) {
                OriginalRenewalActivity.this.fillInformation(param1RenewalPlansListResponseO);
              } else {
                OriginalRenewalActivity.this.onResponseError();
              } 
            } else {
              OriginalRenewalActivity.this.onResponseError();
            } 
          }
        })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
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
  
  public void goToRenewalOption(final int renewalType, final LinkedHashMap<String, Object> params) {
    String str3;
    String str4;
    String str5;
    Resources resources = getResources();
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, 2131165484);
    String str2 = this.responseO.getCpres9();
    if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotConFechaAnual.getOption()) {
      str1 = resources.getString(2131690296);
      str3 = resources.getString(2131689555);
      str4 = resources.getString(2131689834);
      str5 = resources.getString(2131689908);
      str3 = String.format(str3, new Object[] { str2, resources.getString(2131689475) });
    } else {
      if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotSinFechaAnual.getOption()) {
        str3 = resources.getString(2131690289);
        str2 = resources.getString(2131690317);
        str1 = resources.getString(2131690299);
        str4 = resources.getString(2131689476);
        str5 = resources.getString(2131690338);
      } else {
        if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteConFechaAnual.getOption()) {
          str3 = resources.getString(2131690296);
          String str7 = resources.getString(2131689560);
          str4 = resources.getString(2131689834);
          str1 = resources.getString(2131689908);
          str2 = String.format(str7, new Object[] { str2 });
        } else {
          if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteConFechaAnual.getOption()) {
            str1 = resources.getString(2131689955);
            str2 = resources.getString(2131690516);
            str3 = resources.getString(2131689476);
          } else {
            if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaAnual.getOption()) {
              str1 = resources.getString(2131690296);
              str3 = resources.getString(2131690302);
              String str8 = resources.getString(2131689834);
              str2 = resources.getString(2131689956);
            } else {
              final Dialog dialog;
              if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaAnual.getOption()) {
                str3 = resources.getString(2131690296);
                String str10 = resources.getString(2131689562);
                String str9 = resources.getString(2131689834);
                String str8 = resources.getString(2131689908);
                str2 = String.format(str10, new Object[] { str2 });
              } else {
                boolean bool2;
                String str8;
                String str9;
                String str10;
                if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotConFechaMensual.getOption()) {
                  str8 = resources.getString(2131690296);
                  str3 = resources.getString(2131689555);
                  str9 = resources.getString(2131689834);
                  str10 = resources.getString(2131689908);
                  str3 = String.format(str3, new Object[] { str2, resources.getString(2131689513) });
                  str2 = "";
                  bool2 = true;
                } else {
                  if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotSinFechaMensual.getOption()) {
                    str3 = resources.getString(2131690289);
                    str2 = resources.getString(2131690317);
                    str8 = resources.getString(2131689557);
                    str9 = resources.getString(2131689476);
                    str10 = resources.getString(2131690338);
                  } else {
                    if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteConFechaMensual.getOption()) {
                      str3 = resources.getString(2131690296);
                      String str12 = resources.getString(2131689561);
                      str9 = resources.getString(2131689834);
                      str8 = resources.getString(2131689908);
                      str2 = String.format(str12, new Object[] { str2 });
                    } else {
                      if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteConFechaMensual.getOption()) {
                        str8 = resources.getString(2131689619);
                        str3 = resources.getString(2131690307);
                        str9 = resources.getString(2131689834);
                        str2 = resources.getString(2131689908);
                      } else {
                        final Dialog dialog;
                        if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaMensual.getOption()) {
                          str3 = resources.getString(2131689619);
                          String str14 = resources.getString(2131689493);
                          str9 = resources.getString(2131689834);
                          String str13 = resources.getString(2131689908);
                          str2 = String.format(str14, new Object[] { str2 });
                        } else {
                          if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaMensual.getOption()) {
                            String str14 = resources.getString(2131690296);
                            str3 = resources.getString(2131689563);
                            str9 = resources.getString(2131689834);
                            str2 = resources.getString(2131689956);
                          } else {
                            String str14;
                            if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.NombreSinFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.NombreSinFechaMensual.getOption()) {
                              str14 = resources.getString(2131690296);
                              str3 = resources.getString(2131689488);
                              str9 = resources.getString(2131689834);
                              str3 = String.format(str3, new Object[] { str2 });
                            } else {
                              str14 = resources.getString(2131689955);
                              str2 = resources.getString(2131689862);
                              str3 = resources.getString(2131689476);
                              str9 = str3;
                              str3 = str2;
                            } 
                            str2 = "";
                            String str15 = "";
                            boolean bool4 = false;
                            dialog3 = Utilities.genericDialog((Context)this, drawable, str14, str2, str3, true, str9, bool4, str15, 18.0F, 15.0F, false, 2131690207, false);
                            this.btn_left = (Button)dialog3.findViewById(2131296421);
                            this.btn_left.setOnClickListener(new View.OnClickListener(this) {
                                  final Dialog val$dialog;
                                  
                                  public void onClick(View param1View) {
                                    dialog.dismiss();
                                  }
                                });
                            this.btn_right = (Button)dialog3.findViewById(2131296420);
                            this.btn_right.setOnClickListener(new View.OnClickListener() {
                                  final OriginalRenewalActivity this$0;
                                  
                                  final Dialog val$dialog;
                                  
                                  final LinkedHashMap val$params;
                                  
                                  final int val$renewalType;
                                  
                                  public void onClick(View param1View) {
                                    dialog.dismiss();
                                    if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotConFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotConFechaMensual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaMensual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaAnual.getOption()) {
                                      OriginalRenewalActivity.this.assistanceFunction();
                                      return;
                                    } 
                                    if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotSinFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotSinFechaMensual.getOption()) {
                                      OriginalRenewalActivity.this.processPaymentTransaction(params);
                                      return;
                                    } 
                                    if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteConFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteConFechaMensual.getOption()) {
                                      OriginalRenewalActivity.this.assistanceFunction();
                                      return;
                                    } 
                                    if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteConFechaMensual.getOption()) {
                                      OriginalRenewalActivity.this.assistanceFunction();
                                    } else if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaAnual.getOption()) {
                                      dialog.dismiss();
                                      OriginalRenewalActivity.this.goToContinueDialog(renewalType, params);
                                    } else if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaMensual.getOption()) {
                                      dialog.dismiss();
                                      OriginalRenewalActivity.this.goToContinueDialog(renewalType, params);
                                    } 
                                  }
                                });
                            dialog3.show();
                          } 
                          String str13 = str2;
                          boolean bool3 = true;
                          str2 = "";
                        } 
                        Dialog dialog4 = dialog3;
                        str8 = str3;
                        str3 = str2;
                      } 
                      String str12 = str2;
                      bool2 = true;
                      str2 = "";
                    } 
                    str10 = str8;
                    str8 = str3;
                    str3 = str2;
                  } 
                  String str11 = str3;
                  str3 = str8;
                } 
                dialog1 = Utilities.genericDialog((Context)this, drawable, str8, str2, str3, true, str9, bool2, str10, 18.0F, 15.0F, false, 2131690207, false);
                this.btn_left = (Button)dialog1.findViewById(2131296421);
                this.btn_left.setOnClickListener(new View.OnClickListener(this) {
                      final Dialog val$dialog;
                      
                      public void onClick(View param1View) {
                        dialog.dismiss();
                      }
                    });
                this.btn_right = (Button)dialog1.findViewById(2131296420);
                this.btn_right.setOnClickListener(new View.OnClickListener() {
                      final OriginalRenewalActivity this$0;
                      
                      final Dialog val$dialog;
                      
                      final LinkedHashMap val$params;
                      
                      final int val$renewalType;
                      
                      public void onClick(View param1View) {
                        dialog.dismiss();
                        if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotConFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotConFechaMensual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaMensual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaAnual.getOption()) {
                          OriginalRenewalActivity.this.assistanceFunction();
                          return;
                        } 
                        if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotSinFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotSinFechaMensual.getOption()) {
                          OriginalRenewalActivity.this.processPaymentTransaction(params);
                          return;
                        } 
                        if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteConFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteConFechaMensual.getOption()) {
                          OriginalRenewalActivity.this.assistanceFunction();
                          return;
                        } 
                        if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteConFechaMensual.getOption()) {
                          OriginalRenewalActivity.this.assistanceFunction();
                        } else if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaAnual.getOption()) {
                          dialog.dismiss();
                          OriginalRenewalActivity.this.goToContinueDialog(renewalType, params);
                        } else if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaMensual.getOption()) {
                          dialog.dismiss();
                          OriginalRenewalActivity.this.goToContinueDialog(renewalType, params);
                        } 
                      }
                    });
                dialog1.show();
              } 
              Dialog dialog2 = dialog1;
              str1 = str3;
              str3 = str2;
            } 
            String str7 = str2;
            boolean bool1 = true;
            str2 = "";
          } 
          str4 = str3;
          str3 = str2;
        } 
        str5 = str1;
        str1 = str3;
        str3 = str2;
      } 
      String str = str3;
      str3 = str1;
    } 
    str2 = "";
    String str6 = str1;
    boolean bool = true;
    String str1 = str6;
    final Dialog dialog = Utilities.genericDialog((Context)this, drawable, str1, str2, str3, true, str4, bool, str5, 18.0F, 15.0F, false, 2131690207, false);
    this.btn_left = (Button)dialog.findViewById(2131296421);
    this.btn_left.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.btn_right = (Button)dialog.findViewById(2131296420);
    this.btn_right.setOnClickListener(new View.OnClickListener() {
          final OriginalRenewalActivity this$0;
          
          final Dialog val$dialog;
          
          final LinkedHashMap val$params;
          
          final int val$renewalType;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotConFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotConFechaMensual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaMensual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaAnual.getOption()) {
              OriginalRenewalActivity.this.assistanceFunction();
              return;
            } 
            if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotSinFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.OneShotSinFechaMensual.getOption()) {
              OriginalRenewalActivity.this.processPaymentTransaction(params);
              return;
            } 
            if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteConFechaAnual.getOption() || renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteConFechaMensual.getOption()) {
              OriginalRenewalActivity.this.assistanceFunction();
              return;
            } 
            if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteConFechaMensual.getOption()) {
              OriginalRenewalActivity.this.assistanceFunction();
            } else if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaAnual.getOption()) {
              dialog.dismiss();
              OriginalRenewalActivity.this.goToContinueDialog(renewalType, params);
            } else if (renewalType == RenewalPlansAdapter.RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaMensual.getOption()) {
              dialog.dismiss();
              OriginalRenewalActivity.this.goToContinueDialog(renewalType, params);
            } 
          }
        });
    dialog.show();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2) {
      this.buttonTutorial.setVisibility(8);
      ShowViewElement showViewElement = this.show;
      if (showViewElement != null)
        showViewElement.removeView(this, true); 
    } else if (this.lin_aor_cap_line.getVisibility() == 0) {
      this.buttonTutorial.setVisibility(0);
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    setContentView(2131427372);
    this.rtApp = (onstarApplication)getApplicationContext();
    this.dbFun = new DBFunctions(getApplicationContext());
    _context = (Context)this;
    this.stringsResourcesVO = new StringsResourcesVO();
    this.global_popup_btn_si_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_si_1, 2131689952);
    this.global_lbl_conexiondered_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_conexiondered_1, 2131689912);
    this.global_popup_lbl_aviso_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    this.renovacion_lbl_planactual = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_planactual, 2131690308);
    this.global_lbl_acciondescfallared_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    this.global_popup_btn_aceptar_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    this.global_popup_btn_no_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    this.renovacion_lbl_validez = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_validez, 2131690324);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.nothing, 2131690207);
    this.global_popup_lbl_continuar = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_continuar, 2131689956);
    this.global_lbl_asistencia_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    this.global_popup_btn_llamar_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_llamar_1, 2131689948);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_importante, 2131690296);
    this.renovacion_lbl_planes = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_planes, 2131690309);
    this.HistoricoDeBoletos = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.HistoricoDeBoletos, 2131689501);
    try {
      this.userPreference = this.dbFun.getUserPreference(GlobalMembers.userLogged);
    } catch (Exception exception) {
      startActivity(new Intent((Context)this, LoginActivity.class));
    } 
    this.spinner_menu = (Spinner)findViewById(2131297043);
    this.lbl_aor_title = (TextView)findViewById(2131296721);
    this.lbl_aor_current_plan = (TextView)findViewById(2131296719);
    this.lbl_aor_expiration_date = (TextView)findViewById(2131296720);
    this.lv_aor_plan = (ListView)findViewById(2131296864);
    this.lin_aor_cap_line = (LinearLayout)findViewById(2131296799);
    this.lbl_aor_cap_line = (TextView)findViewById(2131296718);
    this.pb_aor_cap_line = (ProgressBar)findViewById(2131296950);
    this.view_aor_cap_line = findViewById(2131297240);
    this.buttonTutorial = (LinearLayout)findViewById(2131296607);
    this.buttonTutorial.setOnClickListener(this.clickTutorial);
    if ((getResources().getConfiguration()).orientation == 2)
      this.buttonTutorial.setVisibility(8); 
    this.lbl_aor_cap_line.setText(this.HistoricoDeBoletos);
    if (GlobalMembers.moipCapLine)
      AsyncPdfCipherKey(false); 
    fillVehicleList(this.spinner_menu, getApplicationContext());
    formattedFont();
    try {
      fillInformation((RenewalPlansListResponseO)getIntent().getExtras().getSerializable("service_response"));
    } catch (Exception exception) {
      onResponseError();
    } 
    this.lin_aor_cap_line.setOnClickListener(new View.OnClickListener() {
          final OriginalRenewalActivity this$0;
          
          public void onClick(View param1View) {
            if (GlobalMembers.moipCapLine)
              OriginalRenewalActivity.this.AsyncPdfCipherKey(true); 
          }
        });
  }
  
  public void onResponseSet(int paramInt1, int paramInt2, RenewalPlanO paramRenewalPlanO) {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    linkedHashMap.put("currentPlan", this.responseO.getCpres1());
    linkedHashMap.put("newPlan", paramRenewalPlanO.getCpapres1());
    linkedHashMap.put("renewalKind", Integer.valueOf(paramInt1));
    linkedHashMap.put("service", paramRenewalPlanO.getCpapres7());
    Integer integer = Integer.valueOf(0);
    if (paramInt1 == 1) {
      linkedHashMap.put("renewalKind", integer);
      linkedHashMap.put("TypeToRenewal", "1");
    } else if (paramInt1 == 0) {
      linkedHashMap.put("renewalKind", integer);
      linkedHashMap.put("TypeToRenewal", "2");
    } 
    linkedHashMap.put("salesPolitic", paramRenewalPlanO.getCpapres2());
    linkedHashMap.put("price", paramRenewalPlanO.getCpapres5());
    linkedHashMap.put("monthlyPrice", paramRenewalPlanO.getCpapres6());
    linkedHashMap.put("currency", paramRenewalPlanO.getCpapres11());
    linkedHashMap.put("numPayments", paramRenewalPlanO.getCpapres13());
    String str = this.TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("planName: ");
    stringBuilder.append(paramRenewalPlanO.getCpapres3());
    stringBuilder.append(" price: ");
    stringBuilder.append(paramRenewalPlanO.getCpapres5());
    Utilities.escribeArchivo(str, "onResponseSet item", stringBuilder.toString());
    try {
      Integer.parseInt(this.responseO.getCpres6());
    } catch (NumberFormatException numberFormatException) {
      Utilities.escribeArchivo(this.TAG, "onResponseSet - Error", numberFormatException.getMessage());
    } 
    this.responseO.getCpres7();
    if (paramInt1 != 5) {
      getPaymentMethodsDialog(paramInt2, (LinkedHashMap)linkedHashMap);
    } else {
      Resources resources = getResources();
      String str2 = resources.getString(2131689955);
      String str1 = resources.getString(2131690516);
      String str3 = resources.getString(2131689476);
      final Dialog dialog = Utilities.genericDialog((Context)this, Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, 2131165484), str2, "", str1, true, str3, false, "", 18.0F, 15.0F, false, 2131690207, false);
      ((Button)dialog.findViewById(2131296421)).setOnClickListener(new View.OnClickListener(this) {
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              dialog.dismiss();
            }
          });
      dialog.show();
    } 
  }
  
  public void openPdfWebView() {
    (new GetLocatorToken((Context)this, new AsyncResponse() {
          final OriginalRenewalActivity this$0;
          
          final UserDevicesVO val$last_known_device;
          
          public void processFinish(String param1String) {
            HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
            if (param1String != null && !param1String.equals("")) {
              hashMap.put("URL", GlobalMembers.MoipTicketHistory);
              hashMap.put("lpar1", "2");
              hashMap.put("lpar2", OriginalRenewalActivity.this.rtApp.getUserAccessData()[0]);
              hashMap.put("lpar3", OriginalRenewalActivity.this.rtApp.getUserAccessData()[0]);
              hashMap.put("lpar4", last_known_device.getSerialnumber());
              hashMap.put("t", param1String);
              Intent intent = new Intent(OriginalRenewalActivity._context, RenewalMoipTicketWV.class);
              Bundle bundle = new Bundle();
              intent.addFlags(268435456);
              bundle.putSerializable("url", hashMap);
              intent.putExtras(bundle);
              OriginalRenewalActivity.this.startActivity(intent);
            } else {
              OriginalRenewalActivity.this.onResponseError();
            } 
          }
        })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    this.pb_aor_cap_line.setVisibility(8);
    this.lin_aor_cap_line.setClickable(true);
  }
  
  public void processPdfCipherKey(boolean paramBoolean, String paramString) {
    if (paramBoolean) {
      if (paramString != null && !paramString.equals("") && paramString.equals("true")) {
        openPdfWebView();
      } else {
        onResponseError();
        this.pb_aor_cap_line.setVisibility(8);
        this.lin_aor_cap_line.setClickable(true);
      } 
    } else {
      if (paramString == null || paramString.equals("") || !paramString.equals("true")) {
        this.view_aor_cap_line.setVisibility(8);
        this.lin_aor_cap_line.setVisibility(8);
        this.buttonTutorial.setVisibility(8);
        this.lin_aor_cap_line.setClickable(true);
        return;
      } 
      this.view_aor_cap_line.setVisibility(0);
      this.lin_aor_cap_line.setVisibility(0);
      if ((getResources().getConfiguration()).orientation == 1) {
        this.buttonTutorial.setVisibility(0);
      } else {
        this.buttonTutorial.setVisibility(8);
      } 
      this.lin_aor_cap_line.setClickable(true);
    } 
  }
  
  private class TimerThread implements Runnable {
    final OriginalRenewalActivity this$0;
    
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
        if (OriginalRenewalActivity.iPreviousCallIndex != 0)
          bool2 = false; 
        if ((bool1 & bool2) != 0) {
          try {
            Thread.sleep(1000L);
            b++;
          } catch (InterruptedException interruptedException) {
            Utilities.escribeArchivo(OriginalRenewalActivity.this.TAG, "Error: timer", interruptedException.toString());
          } 
          continue;
        } 
        OriginalRenewalActivity.this.runOnUiThread(new Runnable(this) {
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
    null(OriginalRenewalActivity this$0) {}
    
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


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/my_plan/OriginalRenewalActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */