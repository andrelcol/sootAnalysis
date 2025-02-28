package com.roadtrack.onstar.ui.settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.MapUpdateVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.adapter.DialogMapSupportDownload;
import com.roadtrack.onstar.adapter.DialogMenu;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.async_tasks.tasks.AsyncTaskUnregisterDevice;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.ui.devices.RegistrationDevices;
import com.roadtrack.onstar.ui.gmt.GmtActivity;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.UserInterfaceUtils;
import com.roadtrack.onstar.utils.Utilities;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SettingsNewActivity extends FragmentActivity implements DialogMapSupportDownload.DialogMapSupportDownloadListener, DialogMenu.DialogMenuListener {
  private static final String DATA_PATH;
  
  private static final String PACKAGE_NAME;
  
  private static final String[] PACKAGE_NAMES;
  
  private static final String TAG = SettingsNewActivity.class.getSimpleName();
  
  public static Context context;
  
  public static Activity mActivity;
  
  private TextView ActionValueMessage;
  
  private TextView OfSetTimeTextView;
  
  private View OfSetTimeView;
  
  private TextView Tip_DTC_Selector;
  
  private SwitchCompat autoNotifications;
  
  private Button btnC;
  
  private Button btnOk;
  
  private Button btnbyp;
  
  private View cityView;
  
  public View.OnClickListener clickDevices = new View.OnClickListener() {
      final SettingsNewActivity this$0;
      
      public void onClick(View param1View) {
        if (SettingsNewActivity.this.network) {
          Intent intent = new Intent(SettingsNewActivity.context, RegistrationDevices.class);
          SettingsNewActivity.this.startActivity(intent);
        } else {
          SettingsNewActivity settingsNewActivity1 = SettingsNewActivity.this;
          String str1 = Utilities.getStringFromConfigList((Context)settingsNewActivity1, settingsNewActivity1.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
          SettingsNewActivity settingsNewActivity2 = SettingsNewActivity.this;
          String str2 = Utilities.getStringFromConfigList((Context)settingsNewActivity2, settingsNewActivity2.stringsResourcesVO.login_global_popup_lbl_sindatos_2, 2131690012);
          SettingsNewActivity settingsNewActivity3 = SettingsNewActivity.this;
          String str3 = Utilities.getStringFromConfigList((Context)settingsNewActivity3, settingsNewActivity3.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
          final Dialog dialogConfirm = Utilities.simpleDialog((Context)SettingsNewActivity.this, null, str3, str2, true, str1, false, "", 20.0F, 16.0F);
          SettingsNewActivity.access$4902(SettingsNewActivity.this, (Button)dialog.findViewById(2131296343));
          SettingsNewActivity.this.btnOk.setOnClickListener(new View.OnClickListener(this) {
                final Dialog val$dialogConfirm;
                
                public void onClick(View param2View) {
                  dialogConfirm.dismiss();
                }
              });
          dialog.show();
        } 
      }
    };
  
  public View.OnClickListener clickEditAlias = new View.OnClickListener() {
      final SettingsNewActivity this$0;
      
      public void onClick(View param1View) {
        SettingsNewActivity.this.editAlias();
      }
    };
  
  private String configuracion_map_lbl_nohayact_8;
  
  private View countryView;
  
  private DBFunctions dbFun;
  
  private DBFunctions dbFunctions;
  
  private File debugCarpet;
  
  private String debugMapUpdateCarpet;
  
  private String deviceIdTimeOffSet;
  
  private Dialog dialogDICAS;
  
  private DialogMenu dialogMenu;
  
  private EditText edit;
  
  private String emailStatus = "";
  
  private String emailUser;
  
  private boolean errorChecker;
  
  private String global_popup_btn_aceptar_1;
  
  private String global_popup_btn_no_1;
  
  private String global_popup_btn_si_1;
  
  private String global_popup_lbl_aviso_1;
  
  private ImageView header;
  
  private LinearLayout headernotif;
  
  private ImageView imageView1;
  
  private TextView internalCity;
  
  private TextView internalCountry;
  
  private TextView internalMail;
  
  private TextView internalTerms;
  
  private TextView internalTermstext;
  
  private TextView internalTitleCity;
  
  private TextView internalTitleCountry;
  
  private TextView internalTitleMail;
  
  private TextView internalTitleMap;
  
  private TextView internalTitleTerms;
  
  private ImageView iv2PalomaAzulALertTip;
  
  private ImageView iv2PalomaAzulTip;
  
  private ImageView ivCarMovementTip;
  
  private ImageView ivEdit;
  
  private ImageView ivPalomaGrisTip;
  
  private ImageView ivProgressBarCircularTip;
  
  private ImageView ivTacheTip;
  
  private ImageView ivWaitNotificationTip;
  
  private ImageView iv_icon_wakeup_car;
  
  private ImageView ivheaderlogo;
  
  private ImageView ivsettings;
  
  private TextView labelAuto;
  
  private TextView lbl_map;
  
  private TextView lbl_terminos_1;
  
  private TextView lbl_version;
  
  private TextView lbl_wakeup_car;
  
  private LinearLayout linearLayoutByp;
  
  private LinearLayout llDevices;
  
  private String login_global_popup_lbl_sindatos_2;
  
  public TextView mTextViewGmtValue;
  
  private SwitchCompat mailNotifications;
  
  private View mailView;
  
  private String main_activity_download_in_progress;
  
  private LinearLayout mapCategory;
  
  private TextView mapDownloadText;
  
  private Button mapUpdate;
  
  private LinearLayout mapUpdateCategory;
  
  private MapUpdateVO mapUpdateVO;
  
  private View mapView;
  
  private TextView map_update_title;
  
  private boolean network;
  
  private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
      final SettingsNewActivity this$0;
      
      public void onReceive(Context param1Context, Intent param1Intent) {
        SettingsNewActivity settingsNewActivity = SettingsNewActivity.this;
        SettingsNewActivity.access$4702(settingsNewActivity, Utilities.showNetworkServiceData((TextView)settingsNewActivity.findViewById(2131297030), (Context)SettingsNewActivity.this, new TextView[0]));
      }
    };
  
  private String notificationValue;
  
  private String phoneNumber;
  
  UserPreferenceVO preferences;
  
  private String prefix = "";
  
  private ProgressBar progressAuto;
  
  private ProgressBar progressMail;
  
  public onstarApplication rtApp;
  
  private boolean setStateByWS = false;
  
  private Spinner spinner_menu;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private View termsView;
  
  private TextView terms_settingText;
  
  private TextView terms_settingText1;
  
  boolean termsconditions = false;
  
  private TextView text;
  
  private TextView textViewVersionNumber;
  
  private LinearLayout tipsCategoryAccess;
  
  private TextView tips_desc;
  
  private TextView tips_title;
  
  private TextView titleMap;
  
  private TextView tv2PalomaAzulALertTip;
  
  private TextView tv2PalomaAzulTip;
  
  private TextView tvCarMovementTip;
  
  private TextView tvCelulares;
  
  private TextView tvDescTip;
  
  private TextView tvDeviceName;
  
  private TextView tvPalomaGrisTip;
  
  private TextView tvProgressBarCircularTip;
  
  private TextView tvTacheTip;
  
  private TextView tvTitleTip;
  
  private TextView tvWaitNotificationTip;
  
  private TextView tvpersonaldata;
  
  private TextView txtlabelMail;
  
  private TextView txtshowEmail;
  
  private UserPreferenceVO userPreference;
  
  private int versionNumberTapCounter = 0;
  
  static {
    PACKAGE_NAMES = SettingsNewActivity.class.getPackage().getName().toString().split("\\.");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(PACKAGE_NAMES[0]);
    stringBuilder.append(".");
    stringBuilder.append(PACKAGE_NAMES[1]);
    stringBuilder.append(".");
    stringBuilder.append(PACKAGE_NAMES[2]);
    PACKAGE_NAME = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(Environment.getExternalStorageDirectory());
    stringBuilder.append("/");
    stringBuilder.append("Android");
    stringBuilder.append("/");
    stringBuilder.append("data");
    stringBuilder.append("/");
    stringBuilder.append(PACKAGE_NAME);
    DATA_PATH = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(DATA_PATH);
    stringBuilder.append("/");
    stringBuilder.append("RoadTrack/map/osm");
    stringBuilder.toString();
  }
  
  private void checkNotificationState(boolean paramBoolean1, boolean paramBoolean2) {
    byte b1;
    byte b2 = 1;
    if (paramBoolean1 && paramBoolean2) {
      b1 = 3;
    } else if (!paramBoolean1 && !paramBoolean2) {
      b1 = 0;
    } else {
      b1 = b2;
      if (!paramBoolean1) {
        b1 = b2;
        if (paramBoolean2)
          b1 = 2; 
      } 
    } 
    this.preferences.setNotifications(Integer.toString(b1));
    this.notificationValue = Integer.toString(b1);
    DBFunctions dBFunctions = this.dbFun;
    UserPreferenceVO userPreferenceVO = this.preferences;
    dBFunctions.updateCfg(userPreferenceVO, userPreferenceVO.getId());
  }
  
  private void createLaterAlertDialog() {
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.mapdownloading_popup_lbl_recuerdequeusted_2, 2131690124);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_continuar, 2131689956);
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(str1).setTitle("DESCARGA DE MAPAS");
    builder.setPositiveButton(str2, new DialogInterface.OnClickListener(this) {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            param1DialogInterface.dismiss();
          }
        });
    builder.create().show();
  }
  
  @SuppressLint({"InflateParams"})
  private void createMobileAlertDialog() {
    Context context = context;
    View view = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2131427435, null);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_acticity_no_wifi, 2131690027);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_mapdownloading_popup_lbl_descargademapa_1, 2131689940);
    this.text = (TextView)view.findViewById(2131297249);
    this.text.setText(str2);
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(str2);
    builder.setTitle(str1);
    builder.setPositiveButton(this.global_popup_btn_no_1, new DialogInterface.OnClickListener() {
          final SettingsNewActivity this$0;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            param1DialogInterface.dismiss();
            SettingsNewActivity.this.createLaterAlertDialog();
          }
        });
    builder.setNegativeButton(this.global_popup_btn_si_1, new DialogInterface.OnClickListener() {
          final SettingsNewActivity this$0;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            param1DialogInterface.dismiss();
            SettingsNewActivity.this.mapDownloadText.setText(SettingsNewActivity.this.main_activity_download_in_progress);
            SettingsNewActivity.this.validProcessDownload();
          }
        });
    builder.create().show();
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
    //   12: ifnonnull -> 28
    //   15: aload_0
    //   16: getfield spinner_menu : Landroid/widget/Spinner;
    //   19: astore #4
    //   21: goto -> 28
    //   24: astore_1
    //   25: goto -> 168
    //   28: aload #4
    //   30: ifnonnull -> 36
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: new java/util/ArrayList
    //   39: astore #6
    //   41: aload #6
    //   43: invokespecial <init> : ()V
    //   46: iconst_0
    //   47: istore_3
    //   48: iload_3
    //   49: aload #5
    //   51: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   54: invokeinterface size : ()I
    //   59: if_icmpge -> 93
    //   62: aload #6
    //   64: aload #5
    //   66: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   69: iload_3
    //   70: invokeinterface get : (I)Ljava/lang/Object;
    //   75: checkcast com/roadtrack/onstar/VO/UserDevicesVO
    //   78: invokevirtual getName : ()Ljava/lang/String;
    //   81: invokeinterface add : (Ljava/lang/Object;)Z
    //   86: pop
    //   87: iinc #3, 1
    //   90: goto -> 48
    //   93: new com/roadtrack/onstar/adapter/VehiculeSpinnerAdapter
    //   96: astore_1
    //   97: aload_1
    //   98: aload_2
    //   99: ldc_w 2131427512
    //   102: ldc_w 2131297225
    //   105: ldc_w 2131297226
    //   108: aload_0
    //   109: getfield spinner_menu : Landroid/widget/Spinner;
    //   112: aload #6
    //   114: invokespecial <init> : (Landroid/content/Context;IIILandroid/widget/Spinner;Ljava/util/List;)V
    //   117: aload #4
    //   119: aload_1
    //   120: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   123: aload #4
    //   125: aload_0
    //   126: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   129: aload_0
    //   130: getfield userPreference : Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   133: invokevirtual getUser : ()Ljava/lang/String;
    //   136: aload #5
    //   138: invokestatic getLastKnownVehicleSelected : (Landroid/content/Context;Ljava/lang/String;Lcom/roadtrack/onstar/onstarApplication;)I
    //   141: invokevirtual setSelection : (I)V
    //   144: aload_2
    //   145: invokestatic setDeviceType : (Landroid/content/Context;)V
    //   148: new com/roadtrack/onstar/ui/settings/SettingsNewActivity$10
    //   151: astore_1
    //   152: aload_1
    //   153: aload_0
    //   154: aload #5
    //   156: invokespecial <init> : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;Lcom/roadtrack/onstar/onstarApplication;)V
    //   159: aload #4
    //   161: aload_1
    //   162: invokevirtual setOnItemSelectedListener : (Landroid/widget/AdapterView$OnItemSelectedListener;)V
    //   165: goto -> 181
    //   168: getstatic com/roadtrack/onstar/ui/settings/SettingsNewActivity.TAG : Ljava/lang/String;
    //   171: ldc_w 'Error: fillVehicleList'
    //   174: aload_1
    //   175: invokevirtual getMessage : ()Ljava/lang/String;
    //   178: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   181: aload_0
    //   182: monitorexit
    //   183: return
    //   184: astore_1
    //   185: aload_0
    //   186: monitorexit
    //   187: aload_1
    //   188: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	184	finally
    //   15	21	24	java/lang/Exception
    //   15	21	184	finally
    //   36	46	24	java/lang/Exception
    //   36	46	184	finally
    //   48	87	24	java/lang/Exception
    //   48	87	184	finally
    //   93	165	24	java/lang/Exception
    //   93	165	184	finally
    //   168	181	184	finally
  }
  
  private void noRepeatSameActionDialog() {
    try {
      String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
      this.dialogMenu = DialogMenu.newInstance();
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, 2131165484);
      this.dialogMenu.setPreferenceDialogFragment(this, (Context)this, true, drawable, str, this.global_popup_lbl_aviso_1, true, this.global_popup_btn_aceptar_1, false, "0");
      this.dialogMenu.show(getSupportFragmentManager(), null);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: noRepeatSameActionDIalog", exception.getMessage());
    } 
  }
  
  private String nombreDelPais(String paramString) {
    return GlobalMembers.countryName;
  }
  
  private static void setDeviceType(Context paramContext) {
    String str = Utilities.getLastKnownDeviceSelected((onstarApplication)paramContext, TAG).getDeviceTypeId();
    if (!str.equals("")) {
      GlobalMembers.deviceType = str;
    } else {
      GlobalMembers.deviceType = GlobalMembers.deviceTypeP7;
    } 
  }
  
  private void setGMT() {
    this.deviceIdTimeOffSet = this.dbFun.getSelectedVehicle(GlobalMembers.userLogged).getVehicle_GTM();
    final ArrayList allGmtCatalog = this.dbFun.getAllGmtCatalog();
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.configuracion_lbl_actualizarcatalogogmt, 2131689724);
    final String please_wait = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.please_wait, 2131690270);
    TextView textView = this.mTextViewGmtValue;
    if (arrayList.size() > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("GMT ");
      stringBuilder.append(Utilities.FormatHour(Float.parseFloat(this.deviceIdTimeOffSet)));
      str1 = stringBuilder.toString();
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("GMT ");
      stringBuilder.append(Utilities.FormatHour(Float.parseFloat(this.deviceIdTimeOffSet)));
      stringBuilder.append(" ");
      stringBuilder.append(str1);
      str1 = stringBuilder.toString();
    } 
    textView.setText(str1);
    this.mTextViewGmtValue.setTextColor(getResources().getColor(2131034206));
    this.mTextViewGmtValue.setTypeface(null, 1);
    this.mTextViewGmtValue.setOnClickListener(new View.OnClickListener() {
          final SettingsNewActivity this$0;
          
          final ArrayList val$allGmtCatalog;
          
          final String val$please_wait;
          
          public void onClick(View param1View) {
            if (allGmtCatalog.size() > 0) {
              SettingsNewActivity settingsNewActivity = SettingsNewActivity.this;
              settingsNewActivity.startActivity(new Intent((Context)settingsNewActivity, GmtActivity.class));
              SettingsNewActivity.this.finish();
            } else {
              final ProgressDialog progressDialog = new ProgressDialog(SettingsNewActivity.context, 2131755173);
              progressDialog.setIndeterminate(true);
              progressDialog.setCancelable(false);
              progressDialog.setMessage(please_wait);
              progressDialog.show();
              MainActivity.taskSet.getGtm_Task((Context)SettingsNewActivity.this, new Base_Interface() {
                    final SettingsNewActivity.null this$1;
                    
                    final ProgressDialog val$progressDialog;
                    
                    public void onFail(String param2String) {
                      progressDialog.dismiss();
                      SettingsNewActivity settingsNewActivity = SettingsNewActivity.this;
                      String str = Utilities.getStringFromConfigList((Context)settingsNewActivity, settingsNewActivity.stringsResourcesVO.mapdownloading_lbl_instentarnuevamente_1, 2131690108);
                      Toast.makeText((Context)SettingsNewActivity.this, str, 1).show();
                    }
                    
                    public void onSuccess(String param2String) {
                      progressDialog.dismiss();
                      TextView textView = SettingsNewActivity.this.mTextViewGmtValue;
                      StringBuilder stringBuilder = new StringBuilder();
                      stringBuilder.append("GMT ");
                      stringBuilder.append(Utilities.FormatHour(Float.parseFloat(SettingsNewActivity.this.deviceIdTimeOffSet)));
                      textView.setText(stringBuilder.toString());
                    }
                  });
            } 
          }
        });
  }
  
  @SuppressLint({"NewApi"})
  private void setNotifications(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt == 3)
            this.mailNotifications.setChecked(true); 
        } else {
          this.mailNotifications.setChecked(true);
        } 
      } else {
        this.mailNotifications.setChecked(false);
      } 
    } else {
      this.mailNotifications.setChecked(false);
    } 
  }
  
  private void setSpinner() {
    this.spinner_menu = (Spinner)findViewById(2131297043);
    fillVehicleList(this.spinner_menu, getApplicationContext());
    this.spinner_menu.setOnTouchListener(new View.OnTouchListener() {
          final SettingsNewActivity this$0;
          
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            boolean bool;
            if (MainActivity.onOpenDoors.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onFindMe.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onPID.booleanValue() || MainActivity.onDTC.booleanValue() || GlobalMembers.onfollowmeActivated) {
              bool = true;
            } else {
              bool = false;
            } 
            if (param1MotionEvent.getAction() == 0 && bool)
              SettingsNewActivity.this.noRepeatSameActionDialog(); 
            return bool;
          }
        });
  }
  
  private void validProcessDownload() {
    String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_download_in_progress, 2131690033);
    this.mapDownloadText.setText(str);
  }
  
  public void acceptButtonListener(String paramString) {}
  
  public void cancelButtonListener(String paramString) {}
  
  public void dialogChangeAlias() {
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.BTCancel, 2131689476);
    final Dialog dialog = Utilities.simpleDialogEdit((Context)this, null, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.aviso_editar_alias, 2131689684), Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.alias, 2131689664), true, str1, true, str2, 20.0F, 16.0F);
    this.btnOk = (Button)dialog.findViewById(2131296343);
    this.btnC = (Button)dialog.findViewById(2131296344);
    this.edit = (EditText)dialog.findViewById(2131296522);
    new ArrayList();
    InputFilter inputFilter = new InputFilter(this) {
        public CharSequence filter(CharSequence param1CharSequence, int param1Int1, int param1Int2, Spanned param1Spanned, int param1Int3, int param1Int4) {
          while (param1Int1 < param1Int2) {
            if (!Utilities.validateCharSequence(param1CharSequence.toString()))
              return ""; 
            param1Int1++;
          } 
          return null;
        }
      };
    this.edit.setFilters(new InputFilter[] { inputFilter, (InputFilter)new InputFilter.LengthFilter(80) });
    this.btnC.setOnClickListener(new View.OnClickListener() {
          final SettingsNewActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            if (SettingsNewActivity.this.network) {
              if (!SettingsNewActivity.this.edit.getText().toString().equalsIgnoreCase("") && !Utilities.isStringNullOrWhiteSpace(SettingsNewActivity.this.edit.getText().toString())) {
                SettingsNewActivity.this.tvDeviceName.setText(SettingsNewActivity.this.edit.getText().toString().trim());
                PreferenceRT.SetStringPreference(GlobalMembers.deviceName, SettingsNewActivity.this.edit.getText().toString().trim(), onstarApplication.getContext());
                GlobalMembers.unRegisterDevice = false;
                (new AsyncTaskUnregisterDevice(SettingsNewActivity.mActivity)).GCM_Inner(SettingsNewActivity.mActivity);
              } else {
                SettingsNewActivity.this.dialogInvalidAlias();
              } 
            } else {
              dialog.dismiss();
              if (!NetUtilities.validateNetwork((Context)SettingsNewActivity.this, false, true))
                return; 
            } 
            dialog.dismiss();
          }
        });
    this.btnOk.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
  }
  
  public void dialogInvalidAlias() {
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    final Dialog dialog = Utilities.simpleDialog((Context)this, null, str2, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.alias_valido, 2131689665), true, str1, false, null, 20.0F, 16.0F);
    this.btnOk = (Button)dialog.findViewById(2131296343);
    this.btnOk.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
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
  
  public void editAlias() {
    if (this.network) {
      String str3 = PreferenceRT.GetValuePreference(GlobalMembers.deviceName, "", onstarApplication.getContext());
      String str2 = Settings.System.getString(getContentResolver(), "device_name");
      String str1 = str2;
      if (str2 == null)
        str1 = Utilities.getDeviceName(); 
      if (str3.equalsIgnoreCase(str1)) {
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
        str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.desvincular, 2131689750);
        final Dialog dialogConfirm = Utilities.simpleDialog((Context)this, null, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955), str2, true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.BTCancel, 2131689476), true, str1, 20.0F, 16.0F);
        this.btnOk = (Button)dialog.findViewById(2131296343);
        this.btnC = (Button)dialog.findViewById(2131296344);
        this.btnOk.setOnClickListener(new View.OnClickListener(this) {
              final Dialog val$dialogConfirm;
              
              public void onClick(View param1View) {
                dialogConfirm.dismiss();
              }
            });
        this.btnC.setOnClickListener(new View.OnClickListener() {
              final SettingsNewActivity this$0;
              
              final Dialog val$dialogConfirm;
              
              public void onClick(View param1View) {
                SettingsNewActivity.this.dialogChangeAlias();
                dialogConfirm.dismiss();
              }
            });
        dialog.show();
      } else {
        dialogChangeAlias();
      } 
    } else if (!NetUtilities.validateNetwork((Context)this, false, true)) {
    
    } 
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackMenuListener(String paramString) {}
  
  public void onBackPressed() {
    super.onBackPressed();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 1) {
      this.dialogDICAS.setContentView(2131427357);
      if (this.dialogDICAS.isShowing())
        setViewImages(); 
      if (Utilities.isAndinos().booleanValue()) {
        this.imageView1 = (ImageView)this.dialogDICAS.findViewById(2131296588);
        Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
        this.imageView1.setImageDrawable(drawable);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.imageView1.getLayoutParams();
        layoutParams.gravity = 5;
        this.imageView1.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      } 
    } else {
      this.dialogDICAS.setContentView(2131427358);
      if (this.dialogDICAS.isShowing())
        setViewImages(); 
      if (Utilities.isAndinos().booleanValue()) {
        this.imageView1 = (ImageView)this.dialogDICAS.findViewById(2131296588);
        Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
        this.imageView1.setImageDrawable(drawable);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.imageView1.getLayoutParams();
        layoutParams.gravity = 5;
        this.imageView1.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      } 
    } 
  }
  
  @SuppressLint({"NewApi"})
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    mActivity = (Activity)this;
    context = (Context)this;
    getActionBar().hide();
    this.preferences = new UserPreferenceVO();
    this.stringsResourcesVO = new StringsResourcesVO();
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_conexiondered_1, 2131689912);
    this.global_popup_btn_aceptar_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    this.global_popup_btn_si_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_si_1, 2131689952);
    this.global_popup_lbl_aviso_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_popup_lbl_finalizandoproceso, 2131690333);
    this.global_popup_btn_no_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    this.global_popup_lbl_aviso_1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    this.configuracion_map_lbl_nohayact_8 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.configuracion_map_lbl_nohayact_8, 2131689731);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.mapdownloading_popup_lbl_actualizacionencontradaAPP_2, 2131690119);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.configuracion_map_lbl_sihayact_8, 2131689732);
    this.main_activity_download_in_progress = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_download_in_progress, 2131690033);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.mapdownloading_popup_lbl_esnecesariohacer_2, 2131690122);
    this.login_global_popup_lbl_sindatos_2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.login_global_popup_lbl_sindatos_2, 2131690012);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_alterar, 2131689947);
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_configuracion_1, 2131689913);
    String str8 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.configuracion_lbl_datosper_4, 2131689726);
    String str7 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.configuracion_lbl_mapa_8, 2131689728);
    String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.start_map_update_label, 2131690378);
    String str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_notificaciones_1, 2131689930);
    String str10 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_notificaciones_lbl_notificatelefono_1, 2131689943);
    String str11 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_consejosmayus_1, 2131689915);
    String str5 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_dicas_lbl_entenderconsejos_1, 2131689841);
    String str6 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_tyc_lbl_terminos_1, 2131689972);
    Intent intent = new Intent();
    intent.setAction("GlobalTouchService");
    intent.putExtra("ACTION_EXTRA", "usuario_activo");
    sendBroadcast(intent);
    setContentView(2131427477);
    this.dbFun = new DBFunctions(getApplicationContext());
    this.rtApp = (onstarApplication)getApplicationContext();
    this.preferences = this.dbFun.getUserPreference(this.rtApp.getUserPreference().getUser());
    this.dbFunctions = new DBFunctions(getApplicationContext());
    this.userPreference = new UserPreferenceVO();
    this.userPreference = this.dbFunctions.getUserPreference(GlobalMembers.userLogged);
    Typeface typeface = onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisBold);
    setSpinner();
    this.dialogDICAS = new Dialog(context);
    this.ivheaderlogo = (ImageView)findViewById(2131296678);
    Drawable drawable2 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    this.ivheaderlogo.setImageDrawable(drawable2);
    this.ivsettings = (ImageView)findViewById(2131296619);
    drawable2 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.settings_title_iturango_azul, 2131165663);
    this.ivsettings.setImageDrawable(drawable2);
    this.ActionValueMessage = (TextView)findViewById(2131296257);
    this.ActionValueMessage.setText(str3);
    this.ActionValueMessage.setTypeface(typeface);
    this.tvpersonaldata = (TextView)findViewById(2131297170);
    this.tvpersonaldata.setText(str8);
    this.lbl_map = (TextView)findViewById(2131296761);
    this.lbl_map.setText(str7);
    this.map_update_title = (TextView)findViewById(2131296892);
    this.map_update_title.setText(str4);
    this.titleMap = (TextView)findViewById(2131297124);
    this.titleMap.setText(str9);
    this.labelAuto = (TextView)findViewById(2131296679);
    this.labelAuto.setText(str10);
    this.tips_title = (TextView)findViewById(2131297118);
    this.tips_title.setText(str11);
    this.tips_desc = (TextView)findViewById(2131297117);
    this.tips_desc.setText(str5);
    this.lbl_terminos_1 = (TextView)findViewById(2131296786);
    this.lbl_terminos_1.setText(str6);
    this.llDevices = (LinearLayout)findViewById(2131296851);
    this.llDevices.setVisibility(0);
    this.tvDeviceName = (TextView)findViewById(2131297160);
    str3 = PreferenceRT.GetValuePreference(GlobalMembers.deviceName, "", onstarApplication.getContext());
    this.tvDeviceName.setText(str3);
    this.tvCelulares = (TextView)findViewById(2131297157);
    this.tvCelulares.setOnClickListener(this.clickDevices);
    Drawable drawable1 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_edit, 2131165471);
    this.ivEdit = (ImageView)findViewById(2131296668);
    this.ivEdit.setImageDrawable(drawable1);
    this.ivEdit.setOnClickListener(this.clickEditAlias);
    if (Utilities.isAndinos().booleanValue()) {
      this.headernotif = (LinearLayout)findViewById(2131296373);
      this.headernotif.setBackgroundResource(2131165662);
    } else {
      ((LinearLayout.LayoutParams)this.ivheaderlogo.getLayoutParams()).gravity = 17;
    } 
    this.countryView = findViewById(2131296484);
    this.cityView = findViewById(2131296470);
    this.mailView = findViewById(2131296872);
    this.OfSetTimeView = findViewById(2131296296);
    this.mapView = findViewById(2131296888);
    this.termsView = findViewById(2131297089);
    this.mapCategory = (LinearLayout)findViewById(2131296887);
    this.mapUpdateCategory = (LinearLayout)findViewById(2131296891);
    ProgressBar progressBar2 = (ProgressBar)findViewById(2131296886);
    if (!UserInterfaceUtils.someActionHasMap(this.rtApp))
      this.mapCategory.setVisibility(8); 
    if (!GlobalMembers.isMAPUpdateEnabled)
      this.mapUpdateCategory.setVisibility(8); 
    if (paramBundle != null) {
      this.phoneNumber = paramBundle.getString("phoneNumber");
      this.emailUser = paramBundle.getString("email");
      this.notificationValue = paramBundle.getString("notificationValue");
      this.prefix = paramBundle.getString("prefix");
    } else {
      this.phoneNumber = this.preferences.getPhone_number();
      this.emailUser = this.preferences.getUser();
      this.notificationValue = this.preferences.getNotifications();
      if (this.preferences.getCountry() != null) {
        this.prefix = Utilities.getPrefixFromCountry(this.preferences.getCountry(), (Context)this);
      } else {
        this.prefix = Utilities.getPrefixFromCountry("BR", (Context)this);
      } 
    } 
    this.internalTitleMap = (TextView)this.mapView.findViewById(2131296652);
    this.mapDownloadText = (TextView)this.mapView.findViewById(2131296651);
    ProgressBar progressBar1 = (ProgressBar)findViewById(2131296885);
    this.mapDownloadText.setText(this.configuracion_map_lbl_nohayact_8);
    this.mailNotifications = (SwitchCompat)findViewById(2131296871);
    this.mailNotifications.setTextOff(this.global_popup_btn_no_1);
    this.mailNotifications.setTextOn(this.global_popup_btn_si_1);
    this.autoNotifications = (SwitchCompat)findViewById(2131296383);
    this.autoNotifications.setTextOff(this.global_popup_btn_no_1);
    this.autoNotifications.setTextOn(this.global_popup_btn_si_1);
    int i = PreferenceRT.GetValuePreference("com.roadtrack.push.notification.status", 0, (Context)this);
    boolean bool = true;
    if (i != 1)
      bool = false; 
    this.autoNotifications.setChecked(bool);
    this.progressAuto = (ProgressBar)findViewById(2131296969);
    this.progressMail = (ProgressBar)findViewById(2131296970);
    this.btnbyp = (Button)findViewById(2131296393);
    this.mapUpdate = (Button)findViewById(2131296890);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_configuracion_map_lbl_descargar_1, 2131689839);
    str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_tyc_txt_contrato_1, 2131689973);
    this.mapUpdate.setText(str1);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_tyc_txt_contrato_2, 2131689974);
    this.mapUpdate.setEnabled(GlobalMembers.isMAPUpdateEnabled);
    this.linearLayoutByp = (LinearLayout)findViewById(2131296437);
    this.btnbyp.setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {}
        });
    this.mapUpdate.setOnClickListener(new View.OnClickListener() {
          final SettingsNewActivity this$0;
          
          public void onClick(View param1View) {
            // Byte code:
            //   0: aload_0
            //   1: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   4: astore_1
            //   5: aload_1
            //   6: aload_1
            //   7: invokestatic access$100 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
            //   10: getfield main_activity_map_update_error : Ljava/lang/String;
            //   13: ldc 2131690039
            //   15: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
            //   18: astore_1
            //   19: aload_0
            //   20: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   23: astore_3
            //   24: aload_3
            //   25: aload_3
            //   26: invokestatic access$100 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
            //   29: getfield map_update_no_version_available : Ljava/lang/String;
            //   32: ldc 2131690086
            //   34: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
            //   37: astore_3
            //   38: invokestatic access$200 : ()Ljava/lang/String;
            //   41: ldc 'MAPUPDATE SERIAL P8:'
            //   43: aload_0
            //   44: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   47: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
            //   50: invokestatic access$200 : ()Ljava/lang/String;
            //   53: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
            //   56: invokevirtual getDeviceId : ()Ljava/lang/String;
            //   59: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
            //   62: aload_0
            //   63: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   66: invokestatic access$300 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Lcom/roadtrack/onstar/DAO/DBFunctions;
            //   69: aload_0
            //   70: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   73: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
            //   76: invokestatic access$200 : ()Ljava/lang/String;
            //   79: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
            //   82: invokevirtual getDeviceId : ()Ljava/lang/String;
            //   85: invokevirtual getMapUpdateMapaData : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/MapUpdateVO;
            //   88: astore #4
            //   90: aload_0
            //   91: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   94: astore #5
            //   96: aload #5
            //   98: aload #5
            //   100: invokestatic access$300 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Lcom/roadtrack/onstar/DAO/DBFunctions;
            //   103: aload_0
            //   104: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   107: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
            //   110: invokestatic access$200 : ()Ljava/lang/String;
            //   113: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
            //   116: invokevirtual getDeviceId : ()Ljava/lang/String;
            //   119: invokevirtual getMapUpdateMapaData : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/MapUpdateVO;
            //   122: invokestatic access$402 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;Lcom/roadtrack/onstar/VO/MapUpdateVO;)Lcom/roadtrack/onstar/VO/MapUpdateVO;
            //   125: pop
            //   126: aload_0
            //   127: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   130: new com/roadtrack/onstar/utils/GetHexDumpMap
            //   133: dup
            //   134: invokespecial <init> : ()V
            //   137: ldc 'FASTMODE'
            //   139: invokevirtual getMapUpdateFile : (Ljava/lang/String;)Ljava/lang/String;
            //   142: invokestatic access$502 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;Ljava/lang/String;)Ljava/lang/String;
            //   145: pop
            //   146: aload_0
            //   147: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   150: astore #5
            //   152: aload #5
            //   154: new java/io/File
            //   157: dup
            //   158: aload #5
            //   160: invokestatic access$500 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Ljava/lang/String;
            //   163: invokespecial <init> : (Ljava/lang/String;)V
            //   166: invokestatic access$602 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;Ljava/io/File;)Ljava/io/File;
            //   169: pop
            //   170: aload_0
            //   171: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   174: invokestatic access$600 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Ljava/io/File;
            //   177: ifnull -> 204
            //   180: aload_0
            //   181: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   184: invokestatic access$600 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Ljava/io/File;
            //   187: invokevirtual list : ()[Ljava/lang/String;
            //   190: astore #5
            //   192: aload #5
            //   194: ifnull -> 204
            //   197: aload #5
            //   199: arraylength
            //   200: istore_2
            //   201: goto -> 207
            //   204: bipush #99
            //   206: istore_2
            //   207: iload_2
            //   208: iconst_1
            //   209: if_icmpne -> 240
            //   212: iconst_1
            //   213: invokestatic valueOf : (Z)Ljava/lang/Boolean;
            //   216: putstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_DEBUG : Ljava/lang/Boolean;
            //   219: aload_0
            //   220: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   223: invokestatic access$600 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Ljava/io/File;
            //   226: invokevirtual listFiles : ()[Ljava/io/File;
            //   229: iconst_0
            //   230: aaload
            //   231: invokevirtual getName : ()Ljava/lang/String;
            //   234: putstatic com/roadtrack/onstar/BO/GlobalMembers.mapFileName : Ljava/lang/String;
            //   237: goto -> 247
            //   240: iconst_0
            //   241: invokestatic valueOf : (Z)Ljava/lang/Boolean;
            //   244: putstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_DEBUG : Ljava/lang/Boolean;
            //   247: getstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_DEBUG : Ljava/lang/Boolean;
            //   250: invokevirtual booleanValue : ()Z
            //   253: pop
            //   254: aload #4
            //   256: ifnonnull -> 260
            //   259: return
            //   260: aload #4
            //   262: invokevirtual getFileMapStatus : ()I
            //   265: iconst_1
            //   266: if_icmpeq -> 277
            //   269: aload #4
            //   271: invokevirtual getFileMapStatus : ()I
            //   274: ifne -> 299
            //   277: aload #4
            //   279: invokevirtual getServerFileName : ()Ljava/lang/String;
            //   282: invokestatic fileExists : (Ljava/lang/String;)Z
            //   285: ifeq -> 299
            //   288: aload #4
            //   290: invokevirtual getServerFileName : ()Ljava/lang/String;
            //   293: invokestatic deleteAllMapUpdateFiles : (Ljava/lang/String;)V
            //   296: goto -> 441
            //   299: aload #4
            //   301: invokevirtual getFileMapStatus : ()I
            //   304: iconst_3
            //   305: if_icmpne -> 365
            //   308: aload #4
            //   310: invokevirtual getServerFileName : ()Ljava/lang/String;
            //   313: invokestatic fileExists : (Ljava/lang/String;)Z
            //   316: ifne -> 441
            //   319: aload #4
            //   321: invokevirtual getServerFileName : ()Ljava/lang/String;
            //   324: invokestatic deleteAllMapUpdateFiles : (Ljava/lang/String;)V
            //   327: aload #4
            //   329: iconst_1
            //   330: invokevirtual setFileMapStatus : (I)V
            //   333: aload_0
            //   334: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   337: invokestatic access$300 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Lcom/roadtrack/onstar/DAO/DBFunctions;
            //   340: aload #4
            //   342: invokevirtual updateMapUpdateData : (Lcom/roadtrack/onstar/VO/MapUpdateVO;)Z
            //   345: pop
            //   346: aload_0
            //   347: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   350: aload_1
            //   351: invokestatic genericAlertDialogOk : (Landroid/content/Context;Ljava/lang/String;)V
            //   354: invokestatic access$200 : ()Ljava/lang/String;
            //   357: ldc 'Map Update:'
            //   359: ldc 'deleted files after download'
            //   361: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
            //   364: return
            //   365: aload #4
            //   367: invokevirtual getFileMapStatus : ()I
            //   370: iconst_4
            //   371: if_icmpne -> 431
            //   374: aload #4
            //   376: invokevirtual getServerFileName : ()Ljava/lang/String;
            //   379: invokestatic fileExists : (Ljava/lang/String;)Z
            //   382: ifne -> 441
            //   385: aload #4
            //   387: invokevirtual getServerFileName : ()Ljava/lang/String;
            //   390: invokestatic deleteAllMapUpdateFiles : (Ljava/lang/String;)V
            //   393: aload #4
            //   395: iconst_1
            //   396: invokevirtual setFileMapStatus : (I)V
            //   399: aload_0
            //   400: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   403: invokestatic access$300 : (Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;)Lcom/roadtrack/onstar/DAO/DBFunctions;
            //   406: aload #4
            //   408: invokevirtual updateMapUpdateData : (Lcom/roadtrack/onstar/VO/MapUpdateVO;)Z
            //   411: pop
            //   412: aload_0
            //   413: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   416: aload_1
            //   417: invokestatic genericAlertDialogOk : (Landroid/content/Context;Ljava/lang/String;)V
            //   420: invokestatic access$200 : ()Ljava/lang/String;
            //   423: ldc 'Map Update:'
            //   425: ldc 'deleted files after start transfering'
            //   427: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
            //   430: return
            //   431: aload #4
            //   433: invokevirtual getFileMapStatus : ()I
            //   436: iconst_5
            //   437: if_icmpne -> 441
            //   440: return
            //   441: aload #4
            //   443: ifnull -> 494
            //   446: aload #4
            //   448: invokevirtual getFileMapStatus : ()I
            //   451: iconst_3
            //   452: if_icmpeq -> 464
            //   455: aload #4
            //   457: invokevirtual getFileMapStatus : ()I
            //   460: iconst_4
            //   461: if_icmpne -> 494
            //   464: invokestatic access$200 : ()Ljava/lang/String;
            //   467: ldc 'MAPUPDATE VERSION:'
            //   469: aload #4
            //   471: invokevirtual toString : ()Ljava/lang/String;
            //   474: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
            //   477: getstatic com/roadtrack/onstar/BO/GlobalMembers.myListener : Lcom/roadtrack/onstar/MainActivity$OnCustomEventListener;
            //   480: astore_1
            //   481: aload_1
            //   482: ifnull -> 529
            //   485: aload_1
            //   486: invokeinterface onEvent : ()V
            //   491: goto -> 529
            //   494: aload #4
            //   496: ifnull -> 511
            //   499: aload #4
            //   501: invokevirtual isUserWantsUpdrade : ()I
            //   504: iconst_1
            //   505: if_icmpne -> 511
            //   508: goto -> 529
            //   511: invokestatic access$200 : ()Ljava/lang/String;
            //   514: ldc 'CONSULTA MAPUPDATE'
            //   516: ldc 'NULL MAP VERSION'
            //   518: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
            //   521: aload_0
            //   522: getfield this$0 : Lcom/roadtrack/onstar/ui/settings/SettingsNewActivity;
            //   525: aload_3
            //   526: invokestatic genericAlertDialogOk : (Landroid/content/Context;Ljava/lang/String;)V
            //   529: return
          }
        });
    this.mapUpdate.setOnLongClickListener(new View.OnLongClickListener(this) {
          public boolean onLongClick(View param1View) {
            return true;
          }
        });
    this.btnbyp.setOnTouchListener(new View.OnTouchListener() {
          final SettingsNewActivity this$0;
          
          @SuppressLint({"ClickableViewAccessibility"})
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            if (param1MotionEvent.getAction() == 0)
              SettingsNewActivity.this.btnbyp.setBackgroundResource(2131427391); 
            return false;
          }
        });
    setNotifications(Integer.valueOf(this.notificationValue).intValue());
    this.internalTitleMap.setVisibility(8);
    str1 = new String();
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append((getPackageManager().getPackageInfo(getPackageName(), 0)).versionName);
      stringBuilder.append("_");
      stringBuilder.append(GlobalMembers.countryName);
      String str = stringBuilder.toString();
      str1 = str;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Utilities.escribeArchivo(TAG, "Error: onCreate", nameNotFoundException.getMessage());
    } 
    this.lbl_version = (TextView)findViewById(2131296788);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.configuracion_lbl_version_4, 2131689729);
    this.lbl_version.setText(str2);
    this.textViewVersionNumber = (TextView)findViewById(2131297233);
    this.textViewVersionNumber.setText(str1);
    this.textViewVersionNumber.setOnClickListener(new View.OnClickListener() {
          final SettingsNewActivity this$0;
          
          public void onClick(View param1View) {
            SettingsNewActivity.access$908(SettingsNewActivity.this);
            if (SettingsNewActivity.this.versionNumberTapCounter >= 5 && GlobalMembers.showLog) {
              SettingsNewActivity.access$902(SettingsNewActivity.this, 0);
              Toast.makeText(SettingsNewActivity.context, GlobalMembers.SVN_REVISION_NUMBER, 0).show();
            } 
          }
        });
    this.internalTitleCountry = (TextView)this.countryView.findViewById(2131296652);
    this.internalCountry = (TextView)this.countryView.findViewById(2131296651);
    this.internalTitleCountry.setVisibility(8);
    this.internalCountry.setText(nombreDelPais("BR"));
    this.internalTitleCity = (TextView)this.cityView.findViewById(2131296652);
    this.internalCity = (TextView)this.cityView.findViewById(2131296651);
    this.internalTitleCity.setVisibility(8);
    this.internalCity.setText(GlobalMembers.auxCity);
    this.internalTitleMail = (TextView)this.mailView.findViewById(2131296652);
    this.internalMail = (TextView)this.mailView.findViewById(2131296651);
    this.internalTitleMail.setVisibility(8);
    str1 = this.emailUser;
    if (str1 != null)
      this.internalMail.setText(str1); 
    this.OfSetTimeTextView = (TextView)this.OfSetTimeView.findViewById(2131296652);
    this.mTextViewGmtValue = (TextView)this.OfSetTimeView.findViewById(2131296651);
    this.OfSetTimeTextView.setVisibility(8);
    setGMT();
    this.internalTitleTerms = (TextView)this.termsView.findViewById(2131296652);
    this.internalTerms = (TextView)this.termsView.findViewById(2131296651);
    this.internalTermstext = (TextView)findViewById(2131297090);
    this.internalTermstext.setText(str4);
    this.internalTermstext.setOnClickListener(new View.OnClickListener() {
          final SettingsNewActivity this$0;
          
          public void onClick(View param1View) {
            Dialog dialog = new Dialog(SettingsNewActivity.context);
            dialog.requestWindowFeature(1);
            dialog.setContentView(2131427492);
            SettingsNewActivity.access$1002(SettingsNewActivity.this, (TextView)dialog.findViewById(2131297090));
            SettingsNewActivity.access$1102(SettingsNewActivity.this, (TextView)dialog.findViewById(2131297091));
            SettingsNewActivity settingsNewActivity2 = SettingsNewActivity.this;
            String str1 = Utilities.getStringFromConfigList((Context)settingsNewActivity2, settingsNewActivity2.stringsResourcesVO.global_tyc_txt_contrato_1, 2131689973);
            SettingsNewActivity settingsNewActivity3 = SettingsNewActivity.this;
            String str2 = Utilities.getStringFromConfigList((Context)settingsNewActivity3, settingsNewActivity3.stringsResourcesVO.global_tyc_txt_contrato_2, 2131689974);
            SettingsNewActivity.this.terms_settingText.setText(str1);
            SettingsNewActivity.this.terms_settingText1.setText(str2);
            SettingsNewActivity settingsNewActivity1 = SettingsNewActivity.this;
            if (!settingsNewActivity1.termsconditions) {
              settingsNewActivity1.termsconditions = true;
              dialog.show();
            } 
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                  final SettingsNewActivity.null this$1;
                  
                  public void onCancel(DialogInterface param2DialogInterface) {
                    SettingsNewActivity.this.termsconditions = false;
                  }
                });
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                  final SettingsNewActivity.null this$1;
                  
                  public void onDismiss(DialogInterface param2DialogInterface) {
                    SettingsNewActivity.this.termsconditions = false;
                  }
                });
          }
        });
    str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_notificaciones_lbl_porcorreo_1, 2131689945);
    str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_tyc_txt_contratovistaprevia_1, 2131689975);
    this.internalTitleTerms.setVisibility(8);
    this.internalTerms.setText(str4);
    this.internalTermstext.setText(str1);
    this.txtshowEmail = (TextView)findViewById(2131297037);
    this.txtshowEmail.setText(str2);
    this.txtshowEmail.setText(GlobalMembers.userLogged);
    this.txtlabelMail = (TextView)findViewById(2131296680);
    this.txtlabelMail.setText(str2);
    if (!GlobalMembers.userLogged.contains("@")) {
      this.txtshowEmail.setVisibility(8);
      this.txtlabelMail.setVisibility(8);
      this.mailNotifications.setVisibility(8);
    } 
    this.linearLayoutByp.setVisibility(8);
    this.tipsCategoryAccess = (LinearLayout)findViewById(2131297116);
    this.tipsCategoryAccess.setOnClickListener(new View.OnClickListener() {
          final SettingsNewActivity this$0;
          
          public void onClick(View param1View) {
            SettingsNewActivity.this.tipsCategoryAccess.setEnabled(false);
            SettingsNewActivity.access$1302(SettingsNewActivity.this, new Dialog(SettingsNewActivity.context));
            SettingsNewActivity.this.dialogDICAS.requestWindowFeature(1);
            SettingsNewActivity.this.dialogDICAS.setContentView(2131427357);
            SettingsNewActivity settingsNewActivity2 = SettingsNewActivity.this;
            String str9 = Utilities.getStringFromConfigList((Context)settingsNewActivity2, settingsNewActivity2.stringsResourcesVO.global_lbl_consejosmayus_1, 2131689915);
            settingsNewActivity2 = SettingsNewActivity.this;
            String str8 = Utilities.getStringFromConfigList((Context)settingsNewActivity2, settingsNewActivity2.stringsResourcesVO.global_dicas_lbl_entenderconsejos_1, 2131689841);
            settingsNewActivity2 = SettingsNewActivity.this;
            String str6 = Utilities.getStringFromConfigList((Context)settingsNewActivity2, settingsNewActivity2.stringsResourcesVO.wizard_dicas_lbl_enviarcomando_3, 2131690515);
            settingsNewActivity2 = SettingsNewActivity.this;
            String str3 = Utilities.getStringFromConfigList((Context)settingsNewActivity2, settingsNewActivity2.stringsResourcesVO.wizard_dicas_lbl_comandorecibido_4, 2131690514);
            settingsNewActivity2 = SettingsNewActivity.this;
            String str1 = Utilities.getStringFromConfigList((Context)settingsNewActivity2, settingsNewActivity2.stringsResourcesVO.wizard_dicas_lbl_comandoejecutado_5, 2131690513);
            SettingsNewActivity settingsNewActivity3 = SettingsNewActivity.this;
            String str2 = Utilities.getStringFromConfigList((Context)settingsNewActivity3, settingsNewActivity3.stringsResourcesVO.wizard_dicas_lbl_alertaactivada_6, 2131690512);
            SettingsNewActivity settingsNewActivity5 = SettingsNewActivity.this;
            String str4 = Utilities.getStringFromConfigList((Context)settingsNewActivity5, settingsNewActivity5.stringsResourcesVO.wakeupcar_estatus_error_header, 2131690496);
            SettingsNewActivity settingsNewActivity6 = SettingsNewActivity.this;
            String str7 = Utilities.getStringFromConfigList((Context)settingsNewActivity6, settingsNewActivity6.stringsResourcesVO.wizard_dicas_lbl_vehiculomovimiento_8, 2131690517);
            settingsNewActivity6 = SettingsNewActivity.this;
            String str5 = Utilities.getStringFromConfigList((Context)settingsNewActivity6, settingsNewActivity6.stringsResourcesVO.wizard_dicas_lbl_aguardenotificacion_9, 2131690511);
            SettingsNewActivity settingsNewActivity10 = SettingsNewActivity.this;
            String str10 = Utilities.getStringFromConfigList((Context)settingsNewActivity10, settingsNewActivity10.stringsResourcesVO.wakeupcar_estatusdicas, 2131690498);
            SettingsNewActivity.this.dialogDICAS.setContentView(2131427357);
            SettingsNewActivity settingsNewActivity11 = SettingsNewActivity.this;
            SettingsNewActivity.access$1402(settingsNewActivity11, (TextView)settingsNewActivity11.dialogDICAS.findViewById(2131297155));
            SettingsNewActivity.this.tvTitleTip.setText(str9);
            SettingsNewActivity settingsNewActivity9 = SettingsNewActivity.this;
            SettingsNewActivity.access$1502(settingsNewActivity9, (TextView)settingsNewActivity9.dialogDICAS.findViewById(2131297146));
            SettingsNewActivity.this.tvDescTip.setText(str8);
            SettingsNewActivity settingsNewActivity8 = SettingsNewActivity.this;
            SettingsNewActivity.access$1602(settingsNewActivity8, (TextView)settingsNewActivity8.dialogDICAS.findViewById(2131296789));
            SettingsNewActivity.this.lbl_wakeup_car.setText(str10);
            settingsNewActivity8 = SettingsNewActivity.this;
            SettingsNewActivity.access$1702(settingsNewActivity8, (TextView)settingsNewActivity8.dialogDICAS.findViewById(2131297152));
            SettingsNewActivity.this.tvProgressBarCircularTip.setText(str6);
            SettingsNewActivity settingsNewActivity7 = SettingsNewActivity.this;
            SettingsNewActivity.access$1802(settingsNewActivity7, (TextView)settingsNewActivity7.dialogDICAS.findViewById(2131297151));
            SettingsNewActivity.this.tvPalomaGrisTip.setText(str3);
            SettingsNewActivity settingsNewActivity4 = SettingsNewActivity.this;
            SettingsNewActivity.access$1902(settingsNewActivity4, (TextView)settingsNewActivity4.dialogDICAS.findViewById(2131297139));
            SettingsNewActivity.this.tv2PalomaAzulTip.setText(str1);
            SettingsNewActivity settingsNewActivity1 = SettingsNewActivity.this;
            SettingsNewActivity.access$2002(settingsNewActivity1, (TextView)settingsNewActivity1.dialogDICAS.findViewById(2131297138));
            SettingsNewActivity.this.tv2PalomaAzulALertTip.setText(str2);
            settingsNewActivity1 = SettingsNewActivity.this;
            SettingsNewActivity.access$2102(settingsNewActivity1, (TextView)settingsNewActivity1.dialogDICAS.findViewById(2131297154));
            SettingsNewActivity.this.tvTacheTip.setText(str4);
            settingsNewActivity1 = SettingsNewActivity.this;
            SettingsNewActivity.access$2202(settingsNewActivity1, (TextView)settingsNewActivity1.dialogDICAS.findViewById(2131297141));
            SettingsNewActivity.this.tvCarMovementTip.setText(str7);
            settingsNewActivity1 = SettingsNewActivity.this;
            SettingsNewActivity.access$2302(settingsNewActivity1, (TextView)settingsNewActivity1.dialogDICAS.findViewById(2131297156));
            SettingsNewActivity.this.tvWaitNotificationTip.setText(str5);
            SettingsNewActivity.this.setViewImages();
            SettingsNewActivity.this.dialogDICAS.show();
            SettingsNewActivity.this.tipsCategoryAccess.setEnabled(true);
          }
        });
    registerReceiver(this.networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.dtc_popup_lbl_activardesactivarDTC, 2131689766);
    this.Tip_DTC_Selector = (TextView)findViewById(2131296312);
    this.Tip_DTC_Selector.setText(str1);
  }
  
  protected void onDestroy() {
    super.onDestroy();
    BroadcastReceiver broadcastReceiver = this.networkChangeReceiver;
    if (broadcastReceiver != null)
      unregisterReceiver(broadcastReceiver); 
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
            Toast.makeText(getApplicationContext(), str, 1).show();
            createLaterAlertDialog();
            return;
          } 
        } 
      } else {
        return;
      } 
    } else {
      Toast.makeText(getApplicationContext(), this.login_global_popup_lbl_sindatos_2, 1).show();
    } 
  }
  
  @SuppressLint({"NewApi"})
  protected void onResume() {
    this.mapDownloadText.setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {}
        });
    this.autoNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final SettingsNewActivity this$0;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (!SettingsNewActivity.this.errorChecker) {
              if (param1Boolean) {
                (new SettingsNewActivity.cnnAuto()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "1" });
              } else {
                (new SettingsNewActivity.cnnAuto()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "0" });
              } 
            } else {
              SettingsNewActivity.access$3702(SettingsNewActivity.this, false);
            } 
          }
        });
    this.mailNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final SettingsNewActivity this$0;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (!SettingsNewActivity.this.errorChecker) {
              if (!SettingsNewActivity.this.setStateByWS)
                if (param1Boolean) {
                  (new SettingsNewActivity.cnnMailActiveNotificationEmail()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "1" });
                } else {
                  (new SettingsNewActivity.cnnMailActiveNotificationEmail()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "0" });
                }  
            } else {
              SettingsNewActivity.access$3702(SettingsNewActivity.this, false);
            } 
          }
        });
    super.onResume();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    paramBundle.putString("email", this.emailUser);
    paramBundle.putString("phoneNumber", this.phoneNumber);
    paramBundle.putString("prefix", this.prefix);
    paramBundle.putString("notificationValue", this.notificationValue);
    paramBundle.putString("deviceIdTimeOffSet", this.deviceIdTimeOffSet);
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart() {
    super.onStart();
    (new validateNotificationEmailStatus()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[0]);
  }
  
  public void setStringGmt(String paramString) {
    TextView textView = this.mTextViewGmtValue;
    if (textView != null)
      textView.setText(paramString); 
  }
  
  public void setViewImages() {
    this.ivProgressBarCircularTip = (ImageView)this.dialogDICAS.findViewById(2131296665);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.progressbarcircular, 2131165647);
    this.ivProgressBarCircularTip.setImageDrawable(drawable);
    this.ivPalomaGrisTip = (ImageView)this.dialogDICAS.findViewById(2131296661);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_send_wait_azul_wizard, 2131165281);
    this.ivPalomaGrisTip.setImageDrawable(drawable);
    this.iv_icon_wakeup_car = (ImageView)this.dialogDICAS.findViewById(2131296673);
    this.lbl_wakeup_car = (TextView)this.dialogDICAS.findViewById(2131296789);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_wakeup, 2131165282);
    this.iv_icon_wakeup_car.setImageDrawable(drawable);
    this.iv2PalomaAzulTip = (ImageView)this.dialogDICAS.findViewById(2131296659);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.palomita_2azul_executado_wizard, 2131165621);
    this.iv2PalomaAzulTip.setImageDrawable(drawable);
    this.iv2PalomaAzulALertTip = (ImageView)this.dialogDICAS.findViewById(2131296658);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_alert_activated_azul_wizard, 2131165272);
    this.iv2PalomaAzulALertTip.setImageDrawable(drawable);
    this.ivTacheTip = (ImageView)this.dialogDICAS.findViewById(2131296666);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_not_executed_azul_wizard, 2131165278);
    this.ivTacheTip.setImageDrawable(drawable);
    this.ivCarMovementTip = (ImageView)this.dialogDICAS.findViewById(2131296660);
    this.tvCarMovementTip = (TextView)this.dialogDICAS.findViewById(2131297141);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.dicas_status_not_available, 2131165402);
    this.ivCarMovementTip.setImageDrawable(drawable);
    this.ivWaitNotificationTip = (ImageView)this.dialogDICAS.findViewById(2131296667);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.timeout_azul_wizard, 2131165688);
    this.ivWaitNotificationTip.setImageDrawable(drawable);
    this.header = (ImageView)this.dialogDICAS.findViewById(2131296588);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    this.header.setImageDrawable(drawable);
  }
  
  private class cnnAuto extends AsyncTask<String, Void, List<Object>> {
    List<Object> activePush = null;
    
    int switchState = 0;
    
    final SettingsNewActivity this$0;
    
    private cnnAuto() {}
    
    protected List<Object> doInBackground(String... param1VarArgs) {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(cnnAuto.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      if (!NetUtilities.validateNetwork(SettingsNewActivity.this.getApplicationContext(), false))
        return null; 
      if (param1VarArgs != null)
        this.switchState = Integer.valueOf(param1VarArgs[0]).intValue(); 
      this.activePush = (new WsAccess((Context)SettingsNewActivity.this)).activePushNotificationAppG2(param1VarArgs[0]);
      return this.activePush;
    }
    
    @SuppressLint({"NewApi"})
    protected void onPostExecute(List<Object> param1List) {
      super.onPostExecute(param1List);
      SettingsNewActivity settingsNewActivity1 = SettingsNewActivity.this;
      String str1 = Utilities.getStringFromConfigList((Context)settingsNewActivity1, settingsNewActivity1.stringsResourcesVO.wizard_notificaciones_global_popup_lbl_recibircel_2, 2131690522);
      SettingsNewActivity settingsNewActivity2 = SettingsNewActivity.this;
      String str2 = Utilities.getStringFromConfigList((Context)settingsNewActivity2, settingsNewActivity2.stringsResourcesVO.wizard_notificaciones_global_popup_lbl_norecibircel_2, 2131690520);
      SettingsNewActivity settingsNewActivity3 = SettingsNewActivity.this;
      String str3 = Utilities.getStringFromConfigList((Context)settingsNewActivity3, settingsNewActivity3.stringsResourcesVO.fail_Un_RegistrerPush2, 2131689789);
      boolean bool = false;
      if (param1List != null && param1List.size() > 1) {
        if (((Integer)param1List.get(1)).intValue() == 0) {
          SettingsNewActivity.access$3702(SettingsNewActivity.this, false);
          SettingsNewActivity settingsNewActivity = SettingsNewActivity.this;
          if (this.switchState == 1)
            bool = true; 
          settingsNewActivity.checkNotificationState(bool, SettingsNewActivity.this.mailNotifications.isChecked());
          if (this.switchState == 1) {
            Toast.makeText(SettingsNewActivity.this.getApplicationContext(), str1, 1).show();
          } else {
            Toast.makeText(SettingsNewActivity.this.getApplicationContext(), str2, 1).show();
          } 
          try {
            PreferenceRT.SetValuePreference("com.roadtrack.push.notification.status", this.switchState, (Context)SettingsNewActivity.this);
          } catch (Exception exception) {
            Utilities.escribeArchivo(SettingsNewActivity.TAG, "Error: onLoginOK: ", exception.toString());
          } 
        } else {
          Toast.makeText((Context)SettingsNewActivity.this, str3, 1).show();
          SettingsNewActivity.access$3702(SettingsNewActivity.this, true);
        } 
        SettingsNewActivity.this.progressAuto.setVisibility(8);
        SettingsNewActivity.this.autoNotifications.setEnabled(true);
      } else {
        SettingsNewActivity.this.progressAuto.setVisibility(8);
        SettingsNewActivity.this.autoNotifications.setEnabled(true);
        SettingsNewActivity settingsNewActivity = SettingsNewActivity.this;
        Toast.makeText((Context)settingsNewActivity, settingsNewActivity.login_global_popup_lbl_sindatos_2, 1).show();
        SettingsNewActivity.access$3702(SettingsNewActivity.this, true);
        if (SettingsNewActivity.this.autoNotifications.isChecked()) {
          SettingsNewActivity.this.autoNotifications.setChecked(false);
        } else {
          SettingsNewActivity.this.autoNotifications.setChecked(true);
        } 
      } 
    }
    
    @SuppressLint({"NewApi"})
    protected void onPreExecute() {
      super.onPreExecute();
      SettingsNewActivity.this.progressAuto.setVisibility(0);
      SettingsNewActivity.this.autoNotifications.setEnabled(false);
    }
  }
  
  private class cnnMailActiveNotificationEmail extends AsyncTask<String, Void, List<Object>> {
    List<Object> activeMail = null;
    
    int switchState = 0;
    
    final SettingsNewActivity this$0;
    
    private cnnMailActiveNotificationEmail() {}
    
    @SuppressLint({"NewApi"})
    protected List<Object> doInBackground(String... param1VarArgs) {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(cnnMailActiveNotificationEmail.class.getSimpleName());
      stringBuilder1.append(": ");
      stringBuilder1.append(Thread.currentThread().getName());
      thread.setName(stringBuilder1.toString());
      Context context = SettingsNewActivity.this.getApplicationContext();
      boolean bool = false;
      if (!NetUtilities.validateNetwork(context, false))
        return null; 
      if (param1VarArgs != null)
        this.switchState = Integer.valueOf(param1VarArgs[0]).intValue(); 
      WsAccess wsAccess = new WsAccess((Context)SettingsNewActivity.this);
      List<UserDevicesVO> list1 = SettingsNewActivity.this.rtApp.getmDeviceUserList();
      String str = new String();
      for (byte b = 0; b < list1.size(); b++) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(((UserDevicesVO)list1.get(b)).getDeviceId());
        stringBuilder.append(",");
        str = stringBuilder.toString();
      } 
      str = str.substring(0, str.length() - 1);
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(this.switchState);
      stringBuilder2.append("");
      this.activeMail = wsAccess.activeNotificationEmail(stringBuilder2.toString(), str);
      List<Object> list = this.activeMail;
      if (list != null) {
        if (((Integer)list.get(1)).intValue() == 0) {
          SettingsNewActivity settingsNewActivity = SettingsNewActivity.this;
          boolean bool1 = settingsNewActivity.autoNotifications.isChecked();
          if (this.switchState == 1)
            bool = true; 
          settingsNewActivity.checkNotificationState(bool1, bool);
        } else {
          this.activeMail = null;
        } 
      } else {
        this.activeMail = null;
      } 
      return this.activeMail;
    }
    
    @SuppressLint({"NewApi"})
    protected void onPostExecute(List<Object> param1List) {
      super.onPostExecute(param1List);
      SettingsNewActivity settingsNewActivity1 = SettingsNewActivity.this;
      String str1 = Utilities.getStringFromConfigList((Context)settingsNewActivity1, settingsNewActivity1.stringsResourcesVO.wizard_notificaciones_global_popup_lbl_recibirmail_2, 2131690523);
      SettingsNewActivity settingsNewActivity2 = SettingsNewActivity.this;
      String str2 = Utilities.getStringFromConfigList((Context)settingsNewActivity2, settingsNewActivity2.stringsResourcesVO.wizard_notificaciones_global_popup_lbl_norecibirmail_2, 2131690521);
      if (param1List != null && this.activeMail.size() > 1) {
        if (((Integer)this.activeMail.get(1)).intValue() == 0) {
          SettingsNewActivity.access$3702(SettingsNewActivity.this, false);
          if (this.switchState == 1) {
            Toast.makeText(SettingsNewActivity.this.getApplicationContext(), str1, 1).show();
          } else {
            Toast.makeText(SettingsNewActivity.this.getApplicationContext(), str2, 1).show();
          } 
        } 
        SettingsNewActivity.this.progressMail.setVisibility(8);
        SettingsNewActivity.this.mailNotifications.setEnabled(true);
      } else {
        SettingsNewActivity.this.progressMail.setVisibility(8);
        SettingsNewActivity.this.mailNotifications.setEnabled(true);
        Toast.makeText(SettingsNewActivity.this.getApplicationContext(), SettingsNewActivity.this.login_global_popup_lbl_sindatos_2, 1).show();
        SettingsNewActivity.access$3702(SettingsNewActivity.this, true);
        if (SettingsNewActivity.this.mailNotifications.isChecked()) {
          SettingsNewActivity.this.mailNotifications.setChecked(false);
        } else {
          SettingsNewActivity.this.mailNotifications.setChecked(true);
        } 
      } 
    }
    
    @SuppressLint({"NewApi"})
    protected void onPreExecute() {
      super.onPreExecute();
      SettingsNewActivity.this.progressMail.setVisibility(0);
      SettingsNewActivity.this.mailNotifications.setEnabled(false);
    }
  }
  
  private class validateNotificationEmailStatus extends AsyncTask<String, Void, List<Object>> {
    List<Object> result = null;
    
    final SettingsNewActivity this$0;
    
    private validateNotificationEmailStatus() {}
    
    @SuppressLint({"NewApi"})
    protected List<Object> doInBackground(String... param1VarArgs) {
      if (!NetUtilities.validateNetwork(SettingsNewActivity.this.getApplicationContext(), false))
        return null; 
      this.result = (new WsAccess((Context)SettingsNewActivity.this)).getEmailNotificationStatus();
      String str = SettingsNewActivity.TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(":");
      stringBuilder.append(this.result);
      Utilities.escribeArchivo(str, "WS result", stringBuilder.toString());
      return this.result;
    }
    
    @SuppressLint({"NewApi"})
    protected void onPostExecute(List<Object> param1List) {
      if (param1List != null && param1List.size() > 1) {
        SettingsNewActivity.access$5202(SettingsNewActivity.this, param1List.get(param1List.size() - 1).toString());
        String str = SettingsNewActivity.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-");
        stringBuilder.append(param1List);
        Utilities.escribeArchivo(str, "EmailNotifyTask", stringBuilder.toString());
      } else {
        SettingsNewActivity.access$5202(SettingsNewActivity.this, "");
        Utilities.escribeArchivo(SettingsNewActivity.TAG, "EmailNotifyTask", "result error");
      } 
      SettingsNewActivity.access$3902(SettingsNewActivity.this, true);
      if (SettingsNewActivity.this.emailStatus.equals("0")) {
        SettingsNewActivity.this.mailNotifications.setChecked(true);
      } else {
        SettingsNewActivity.this.mailNotifications.setChecked(false);
      } 
      SettingsNewActivity.access$3902(SettingsNewActivity.this, false);
      SettingsNewActivity.this.progressMail.setVisibility(8);
      SettingsNewActivity.this.mailNotifications.setEnabled(true);
    }
    
    @SuppressLint({"NewApi"})
    protected void onPreExecute() {
      super.onPreExecute();
      SettingsNewActivity.this.progressMail.setVisibility(0);
      SettingsNewActivity.this.mailNotifications.setEnabled(false);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/settings/SettingsNewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */