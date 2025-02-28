package com.roadtrack.onstar.ui.wizard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.appcompat.widget.SwitchCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.EmailCatalogVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.ui.dialogs.DialogTwoOptions;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class WizardActivity extends Activity {
  private SwitchCompat autoNotifications;
  
  private Button button_next;
  
  private Button buttons_nextactions;
  
  private Button buttons_nextnotif;
  
  private CheckBox chkTherms;
  
  private Context ctx;
  
  private DBFunctions dbFun;
  
  private String emailStatus = "";
  
  private boolean errorChecker;
  
  private TextView errorLogs;
  
  private ImageView header;
  
  private ImageView headerNotifications;
  
  private ImageView headerchevrolet;
  
  private ImageView imageView;
  
  private ImageView iv2PalomaAzulALertTip;
  
  private ImageView iv2PalomaAzulTip;
  
  private ImageView ivCarMovementTip;
  
  private ImageView ivPalomaGrisTip;
  
  private ImageView ivProgressBarCircularTip;
  
  private ImageView ivTacheTip;
  
  private ImageView ivWaitNotificationTip;
  
  private ImageView iv_icon_wakeup_car;
  
  private TextView labelAuto;
  
  private TextView lbl_wakeup_car;
  
  private LinearLayout ll;
  
  private LinearLayout lltitlewizardnotifications;
  
  private LinearLayout lltitlewizardtermsandconditions;
  
  private DialogTwoOptions loginExit;
  
  private SwitchCompat mailNotifications;
  
  private String mainCountry = "";
  
  private String nothing;
  
  private TextView notifText;
  
  private ProgressBar progressAuto;
  
  private ProgressBar progressMail;
  
  private onstarApplication rtApp;
  
  private boolean setStateByWS = false;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Typeface tf;
  
  private TextView tv2PalomaAzulALertTip;
  
  private TextView tv2PalomaAzulTip;
  
  private TextView tvCarMovementTip;
  
  private TextView tvDescTip;
  
  private TextView tvPalomaGrisTip;
  
  private TextView tvProgressBarCircularTip;
  
  private TextView tvTacheTip;
  
  private TextView tvTitleTip;
  
  private TextView tvWaitNotificationTip;
  
  private TextView tvtitlewizardnotifications;
  
  private TextView tvtitlewizardtermsandconditions;
  
  private TextView txtTerms;
  
  private TextView txtTerms1;
  
  private TextView txtlabelMail;
  
  private TextView txtshowEmail;
  
  private UserPreferenceVO userPreference;
  
  private ViewFlipper viewFlipp;
  
  private String wizard_tyc_btn_aceptar_4;
  
  private void autoNotificationsListeners(boolean paramBoolean) {
    if (!this.errorChecker) {
      if (paramBoolean) {
        (new cnnAuto()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "1" });
      } else {
        (new cnnAuto()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "0" });
      } 
    } else {
      this.errorChecker = false;
    } 
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
    this.userPreference.setNotifications(Integer.toString(b1));
    DBFunctions dBFunctions = this.dbFun;
    UserPreferenceVO userPreferenceVO = this.userPreference;
    dBFunctions.updateCfg(userPreferenceVO, userPreferenceVO.getId());
  }
  
  @SuppressLint({"NewApi"})
  private void enableListeners() {
    this.chkTherms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final WizardActivity this$0;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            WizardActivity.this.errorLogs.setText(WizardActivity.this.nothing);
            if (param1Boolean) {
              WizardActivity.this.button_next.setText(WizardActivity.this.wizard_tyc_btn_aceptar_4);
              WizardActivity.this.userPreference.setTerms_conditions("1");
              WizardActivity.this.button_next.setTextColor(-1);
              GlobalMembers.TermConditionsData = (new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss")).format(new Date());
            } else {
              WizardActivity.this.button_next.setText(WizardActivity.this.wizard_tyc_btn_aceptar_4);
              WizardActivity.this.userPreference.setTerms_conditions("0");
              WizardActivity.this.button_next.setTextColor(-3355444);
              GlobalMembers.TermConditionsData = "";
            } 
          }
        });
    this.button_next.setOnClickListener(new View.OnClickListener() {
          final WizardActivity this$0;
          
          public void onClick(View param1View) {
            WizardActivity.this.errorLogs.setText(WizardActivity.this.nothing);
            if (WizardActivity.this.chkTherms.isChecked()) {
              if (WizardActivity.this.dbFun.updateTermsAndConditionsSent("0", WizardActivity.this.userPreference.getUser()) > 0L)
                NetUtilities.sendTermsAndConditions(WizardActivity.this.ctx, Utilities.getDateTime()); 
              WizardActivity.this.nextWizard();
            } else {
              WizardActivity wizardActivity = WizardActivity.this;
              String str = Utilities.getStringFromConfigList((Context)wizardActivity, wizardActivity.stringsResourcesVO.wizard_global_popup_lbl_erroraceptar_2, 2131690518);
              Toast.makeText(WizardActivity.this.getApplicationContext(), str, 0).show();
            } 
          }
        });
    this.autoNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final WizardActivity this$0;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            WizardActivity.this.autoNotificationsListeners(param1Boolean);
          }
        });
    this.mailNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final WizardActivity this$0;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            WizardActivity.this.mailNotificationsListener(param1Boolean);
          }
        });
    this.buttons_nextnotif.setOnClickListener(new View.OnClickListener() {
          final WizardActivity this$0;
          
          public void onClick(View param1View) {
            boolean bool1;
            byte b;
            boolean bool3 = WizardActivity.this.autoNotifications.isChecked();
            boolean bool4 = WizardActivity.this.mailNotifications.isChecked();
            if (bool4) {
              byte b1 = 2;
            } else if (!bool3) {
              bool1 = false;
            } else {
              bool1 = bool3;
            } 
            boolean bool2 = bool1;
            if (bool4) {
              bool2 = bool1;
              if (bool3)
                b = 3; 
            } 
            WizardActivity.this.userPreference.setNotifications(Integer.toString(b));
            WizardActivity.this.nextWizard();
          }
        });
    this.buttons_nextactions.setOnClickListener(new View.OnClickListener() {
          final WizardActivity this$0;
          
          public void onClick(View param1View) {
            WizardActivity.this.gotoMainActivity();
          }
        });
    this.loginExit.setOnClickDialogListener(new DialogTwoOptions.OnClickDialogListener() {
          final WizardActivity this$0;
          
          public void onClickDialog(int param1Int) {
            WizardActivity.this.finish();
          }
        });
  }
  
  private void gotoMainActivity() {
    hideKeyboard();
    this.userPreference.setRemindDownload("F");
    this.userPreference.setCountry(this.mainCountry);
    this.userPreference.setLanguage("pt");
    this.userPreference.setTerms_conditions_date(GlobalMembers.TermConditionsData);
    TimeZone timeZone = TimeZone.getDefault();
    int i = timeZone.getOffset(GregorianCalendar.getInstance(timeZone).getTimeInMillis());
    int j = Math.abs(i / 3600000);
    byte b = 0;
    String str2 = String.format("%02d", new Object[] { Integer.valueOf(j) });
    StringBuilder stringBuilder = new StringBuilder();
    if (i >= 0) {
      str1 = "+";
    } else {
      str1 = "-";
    } 
    stringBuilder.append(str1);
    stringBuilder.append(str2);
    String str1 = stringBuilder.toString();
    this.userPreference.setTimeOfSet(str1);
    str1 = GlobalMembers.auxCity;
    if (str1 != null) {
      this.userPreference.setCity(str1);
    } else {
      this.userPreference.setCity("");
    } 
    if (!this.dbFun.existUser(this.userPreference.getUser()).booleanValue()) {
      this.dbFun.addCfg(this.userPreference);
    } else {
      this.dbFun.updateCfg(this.userPreference);
    } 
    onstarApplication onstarApplication1 = (onstarApplication)getApplicationContext();
    onstarApplication1.setUserPreference(this.userPreference);
    ArrayList arrayList = this.dbFun.getEmailCatalog(this.userPreference.getUser());
    if (arrayList != null && arrayList.size() <= 0) {
      EmailCatalogVO emailCatalogVO = new EmailCatalogVO(null, this.userPreference.getUser(), this.userPreference.getUser());
      this.dbFun.addEmailCatalog(emailCatalogVO);
    } 
    arrayList = this.dbFun.getVehiclesCatalog(this.userPreference.getUser());
    List list = onstarApplication1.getmDeviceUserList();
    if (arrayList != null && arrayList.size() <= 0 && list != null && list.size() > 0) {
      new VehicleCatalogVO();
      for (UserDevicesVO userDevicesVO : list) {
        VehicleCatalogVO vehicleCatalogVO;
        if (!b) {
          vehicleCatalogVO = new VehicleCatalogVO(null, userDevicesVO.getDeviceId(), this.userPreference.getUser(), "T", "-3", "1", "1", "1", "1", userDevicesVO.getName());
        } else {
          vehicleCatalogVO = new VehicleCatalogVO(null, vehicleCatalogVO.getDeviceId(), this.userPreference.getUser(), "F", "-3", "1", "1", "1", "1", vehicleCatalogVO.getName());
        } 
        this.dbFun.addVehiclesCatalog(vehicleCatalogVO);
        b++;
      } 
    } 
    Intent intent = new Intent((Context)this, MainActivity.class);
    GlobalMembers.usingPush = true;
    startActivity(intent);
    finish();
  }
  
  private void hideKeyboard() {
    View view = getCurrentFocus();
    if (view != null)
      ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2); 
  }
  
  private void initControls() {
    this.loginExit = DialogTwoOptions.newInstance(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.salir_popup_lbl_cerrarsesion_2, 2131690353), Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946));
    this.button_next.setTextColor(-3355444);
  }
  
  private void mailNotificationsListener(boolean paramBoolean) {
    if (!this.errorChecker) {
      if (!this.setStateByWS)
        if (paramBoolean) {
          (new cnnMailActiveNotificationEmail()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "1" });
        } else {
          (new cnnMailActiveNotificationEmail()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "0" });
        }  
    } else {
      this.errorChecker = false;
    } 
  }
  
  @SuppressLint({"NewApi", "CommitTransaction"})
  private void manageViewElements(int paramInt) {
    if (paramInt == 0) {
      FragmentManager fragmentManager = getFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      Fragment fragment = fragmentManager.findFragmentByTag("exitLogin");
      if (fragment != null) {
        ((DialogFragment)fragment).dismiss();
        fragmentTransaction.remove(fragment);
      } 
      fragmentTransaction.addToBackStack(null);
    } 
  }
  
  private void nextWizard() {
    hideKeyboard();
    this.viewFlipp.setInAnimation((Context)this, 17432578);
    this.viewFlipp.setOutAnimation((Context)this, 17432579);
    this.viewFlipp.showNext();
    this.viewFlipp.getDisplayedChild();
  }
  
  private void previousWizard() {
    hideKeyboard();
    this.viewFlipp.setInAnimation((Context)this, 17432578);
    this.viewFlipp.setOutAnimation((Context)this, 17432579);
    this.viewFlipp.showPrevious();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    this.ll = (LinearLayout)findViewById(2131296853);
    this.ll.removeAllViews();
    View view = LayoutInflater.from((Context)this).inflate(2131427357, null);
    view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    this.ll.addView(view);
    this.tvTitleTip = (TextView)findViewById(2131297155);
    this.tvtitlewizardtermsandconditions = (TextView)findViewById(2131297172);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_tyc_lbl_terminos_1, 2131689972);
    this.tvtitlewizardtermsandconditions.setText(str1);
    this.tvtitlewizardnotifications = (TextView)findViewById(2131297171);
    str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_notificaciones_1, 2131689930);
    this.tvtitlewizardnotifications.setText(str1);
    this.txtTerms = (TextView)findViewById(2131297188);
    str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_tyc_txt_contrato_1, 2131689973);
    this.txtTerms.setText(str1);
    this.tvTitleTip = (TextView)findViewById(2131297155);
    str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_consejosmayus_1, 2131689915);
    this.tvTitleTip.setText(str1);
    this.tvDescTip = (TextView)findViewById(2131297146);
    String str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_dicas_lbl_entenderconsejos_1, 2131689841);
    this.tvDescTip.setText(str9);
    this.tvProgressBarCircularTip = (TextView)findViewById(2131297152);
    str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_enviarcomando_3, 2131690515);
    this.tvProgressBarCircularTip.setText(str9);
    this.ivProgressBarCircularTip = (ImageView)findViewById(2131296665);
    Drawable drawable9 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.progressbarcircular, 2131165647);
    this.ivProgressBarCircularTip.setImageDrawable(drawable9);
    this.tvPalomaGrisTip = (TextView)findViewById(2131297151);
    String str8 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_comandorecibido_4, 2131690514);
    this.tvPalomaGrisTip.setText(str8);
    this.ivPalomaGrisTip = (ImageView)findViewById(2131296661);
    Drawable drawable8 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_send_wait_azul_fondo, 2131165280);
    this.ivPalomaGrisTip.setImageDrawable(drawable8);
    this.lbl_wakeup_car = (TextView)findViewById(2131296789);
    this.iv_icon_wakeup_car = (ImageView)findViewById(2131296673);
    String str7 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wakeupcar_estatusdicas, 2131690498);
    this.lbl_wakeup_car.setText(str7);
    Drawable drawable7 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_wakeup, 2131165283);
    this.iv_icon_wakeup_car.setImageDrawable(drawable7);
    this.tv2PalomaAzulTip = (TextView)findViewById(2131297139);
    String str6 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_comandoejecutado_5, 2131690513);
    this.tv2PalomaAzulTip.setText(str6);
    this.iv2PalomaAzulTip = (ImageView)findViewById(2131296659);
    Drawable drawable6 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.palomita_2azul_executado_fondo, 2131165620);
    this.iv2PalomaAzulTip.setImageDrawable(drawable6);
    this.tv2PalomaAzulALertTip = (TextView)findViewById(2131297138);
    String str5 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_alertaactivada_6, 2131690512);
    this.tv2PalomaAzulALertTip.setText(str5);
    this.iv2PalomaAzulALertTip = (ImageView)findViewById(2131296658);
    Drawable drawable5 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_alert_activated_azul, 2131165271);
    this.iv2PalomaAzulALertTip.setImageDrawable(drawable5);
    this.tvTacheTip = (TextView)findViewById(2131297154);
    String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wakeupcar_estatus_error_header, 2131690496);
    this.tvTacheTip.setText(str4);
    this.ivTacheTip = (ImageView)findViewById(2131296666);
    Drawable drawable4 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_not_executed_azul_fondo, 2131165277);
    this.ivTacheTip.setImageDrawable(drawable4);
    this.tvCarMovementTip = (TextView)findViewById(2131297141);
    this.ivCarMovementTip = (ImageView)findViewById(2131296660);
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_vehiculomovimiento_8, 2131690517);
    this.tvCarMovementTip.setText(str3);
    Drawable drawable3 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.dicas_status_not_available, 2131165402);
    this.ivCarMovementTip.setImageDrawable(drawable3);
    this.tvWaitNotificationTip = (TextView)findViewById(2131297156);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_aguardenotificacion_9, 2131690511);
    this.tvWaitNotificationTip.setText(str2);
    this.ivWaitNotificationTip = (ImageView)findViewById(2131296667);
    Drawable drawable2 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.timeout_azul_fondo, 2131165684);
    this.ivWaitNotificationTip.setImageDrawable(drawable2);
    this.tvTitleTip.setText(str1);
    this.tvTitleTip.setGravity(19);
    this.headerchevrolet = (ImageView)findViewById(2131296588);
    Drawable drawable1 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    this.headerchevrolet.setImageDrawable(drawable1);
    if (Utilities.isAndinos().booleanValue()) {
      this.tvTitleTip.setGravity(17);
      this.imageView = (ImageView)findViewById(2131296588);
      LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.imageView.getLayoutParams();
      layoutParams.gravity = 5;
      this.imageView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    } else {
      LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.headerchevrolet.getLayoutParams();
      layoutParams.gravity = 17;
      Resources resources = getResources();
      float f2 = TypedValue.applyDimension(1, 0.0F, resources.getDisplayMetrics());
      float f4 = TypedValue.applyDimension(1, 15.0F, resources.getDisplayMetrics());
      float f3 = TypedValue.applyDimension(1, 0.0F, resources.getDisplayMetrics());
      float f1 = TypedValue.applyDimension(1, 0.0F, resources.getDisplayMetrics());
      layoutParams.setMargins(Math.round(f2), Math.round(f4), Math.round(f3), Math.round(f1));
      this.headerchevrolet.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    } 
    this.buttons_nextactions = (Button)findViewById(2131296397);
    this.buttons_nextactions.setVisibility(0);
    this.buttons_nextactions.setOnClickListener(new View.OnClickListener() {
          final WizardActivity this$0;
          
          public void onClick(View param1View) {
            WizardActivity.this.gotoMainActivity();
          }
        });
    super.onConfigurationChanged(paramConfiguration);
    Utilities.escribeArchivo("WizardActivity", "onConfigurationChanged", "TEST");
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.ctx = getApplicationContext();
    getActionBar().hide();
    setContentView(2131427517);
    this.viewFlipp = (ViewFlipper)findViewById(2131297241);
    if (PreferenceRT.GetValuePreference(GlobalMembers.deviceName, "", onstarApplication.getContext()).equalsIgnoreCase("")) {
      String str = Settings.System.getString(getContentResolver(), "device_name");
      if (str == null) {
        str = Settings.Secure.getString(getContentResolver(), "bluetooth_name");
        if (str != null)
          if (Utilities.validateCharSequence(str)) {
            PreferenceRT.SetStringPreference(GlobalMembers.deviceName, str, onstarApplication.getContext());
          } else {
            PreferenceRT.SetStringPreference(GlobalMembers.deviceName, Utilities.getDeviceName(), onstarApplication.getContext());
          }  
      } else if (Utilities.validateCharSequence(str)) {
        PreferenceRT.SetStringPreference(GlobalMembers.deviceName, str, onstarApplication.getContext());
      } else {
        PreferenceRT.SetStringPreference(GlobalMembers.deviceName, Utilities.getDeviceName(), onstarApplication.getContext());
      } 
    } 
    this.stringsResourcesVO = new StringsResourcesVO();
    this.wizard_tyc_btn_aceptar_4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_tyc_btn_aceptar_4, 2131690531);
    this.nothing = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.nothing, 2131690207);
    this.rtApp = (onstarApplication)getApplicationContext();
    this.tf = onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisRegular);
    this.dbFun = new DBFunctions((Context)this);
    Intent intent = getIntent();
    String str12 = intent.getStringExtra("user");
    String str13 = intent.getStringExtra("magicSpell");
    String str14 = intent.getStringExtra("saveAccess");
    this.userPreference = new UserPreferenceVO();
    this.userPreference.setNotifications("1");
    this.userPreference.setUser(str12);
    this.userPreference.setEMAIL(str12);
    this.userPreference.setMagic_spell(str13);
    this.userPreference.setSave_access(str14);
    this.button_next = (Button)findViewById(2131296396);
    str12 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_btn_siguiente_1, 2131689833);
    this.button_next.setText(str12);
    this.buttons_nextnotif = (Button)findViewById(2131296398);
    this.buttons_nextnotif.setText(str12);
    this.buttons_nextactions = (Button)findViewById(2131296397);
    this.buttons_nextactions.setText(str12);
    Button button = this.buttons_nextactions;
    boolean bool = false;
    button.setVisibility(0);
    str13 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_si_1, 2131689952);
    String str11 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    str14 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_tyc_txt_leido_3, 2131690532);
    this.chkTherms = (CheckBox)findViewById(2131296466);
    this.chkTherms.setText(str14);
    this.errorLogs = (TextView)findViewById(2131296518);
    TextView textView = (TextView)findViewById(2131297191);
    this.autoNotifications = (SwitchCompat)findViewById(2131296383);
    this.autoNotifications.setTextOn(str13);
    this.autoNotifications.setTextOff(str11);
    if (PreferenceRT.GetValuePreference("com.roadtrack.push.notification.status", 0, (Context)this) == 1)
      bool = true; 
    this.autoNotifications.setChecked(bool);
    this.mailNotifications = (SwitchCompat)findViewById(2131296871);
    this.mailNotifications.setTextOn(str13);
    this.mailNotifications.setTextOff(str11);
    this.progressAuto = (ProgressBar)findViewById(2131296969);
    this.progressMail = (ProgressBar)findViewById(2131296970);
    str11 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_notificaciones_lbl_notificatelefono_1, 2131689943);
    this.labelAuto = (TextView)findViewById(2131296679);
    this.labelAuto.setText(str11);
    str11 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_notificaciones_lbl_lasnotificaciones_2, 2131690526);
    this.notifText = (TextView)findViewById(2131296931);
    this.notifText.setText(str11);
    this.autoNotifications.setTypeface(this.tf);
    this.mailNotifications.setTypeface(this.tf);
    this.notifText.setTypeface(this.tf);
    this.lltitlewizardtermsandconditions = (LinearLayout)findViewById(2131296855);
    Drawable drawable11 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.wizar_terminos, 2131165708);
    this.lltitlewizardtermsandconditions.setBackground(drawable11);
    this.tvtitlewizardtermsandconditions = (TextView)findViewById(2131297172);
    String str10 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_tyc_lbl_terminos_1, 2131689972);
    this.tvtitlewizardtermsandconditions.setText(str10);
    this.lltitlewizardnotifications = (LinearLayout)findViewById(2131296854);
    Drawable drawable10 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.wizar_notificaciones, 2131165707);
    this.lltitlewizardnotifications.setBackground(drawable10);
    this.tvtitlewizardnotifications = (TextView)findViewById(2131297171);
    String str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_notificaciones_1, 2131689930);
    this.tvtitlewizardnotifications.setText(str9);
    this.txtTerms = (TextView)findViewById(2131297188);
    str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_tyc_txt_contrato_1, 2131689973);
    this.txtTerms.setText(str9);
    this.txtTerms1 = (TextView)findViewById(2131297189);
    str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_tyc_txt_contrato_2, 2131689974);
    this.txtTerms1.setText(str9);
    this.tvTitleTip = (TextView)findViewById(2131297155);
    str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_consejosmayus_1, 2131689915);
    this.tvTitleTip.setText(str9);
    this.tvDescTip = (TextView)findViewById(2131297146);
    str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_dicas_lbl_entenderconsejos_1, 2131689841);
    this.tvDescTip.setText(str9);
    this.tvProgressBarCircularTip = (TextView)findViewById(2131297152);
    str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_enviarcomando_3, 2131690515);
    this.tvProgressBarCircularTip.setText(str9);
    this.ivProgressBarCircularTip = (ImageView)findViewById(2131296665);
    Drawable drawable9 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.progressbarcircular, 2131165647);
    this.ivProgressBarCircularTip.setImageDrawable(drawable9);
    this.lbl_wakeup_car = (TextView)findViewById(2131296789);
    this.iv_icon_wakeup_car = (ImageView)findViewById(2131296673);
    String str8 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wakeupcar_estatusdicas, 2131690498);
    this.lbl_wakeup_car.setText(str8);
    Drawable drawable8 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_wake_up_car, 2131165282);
    this.iv_icon_wakeup_car.setImageDrawable(drawable8);
    this.tvPalomaGrisTip = (TextView)findViewById(2131297151);
    String str7 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_comandorecibido_4, 2131690514);
    this.tvPalomaGrisTip.setText(str7);
    this.ivPalomaGrisTip = (ImageView)findViewById(2131296661);
    Drawable drawable7 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_send_wait_azul_wizard, 2131165281);
    this.ivPalomaGrisTip.setImageDrawable(drawable7);
    this.tv2PalomaAzulTip = (TextView)findViewById(2131297139);
    String str6 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_comandoejecutado_5, 2131690513);
    this.tv2PalomaAzulTip.setText(str6);
    this.iv2PalomaAzulTip = (ImageView)findViewById(2131296659);
    Drawable drawable6 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.palomita_2azul_executado_wizard, 2131165621);
    this.iv2PalomaAzulTip.setImageDrawable(drawable6);
    this.tv2PalomaAzulALertTip = (TextView)findViewById(2131297138);
    String str5 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_alertaactivada_6, 2131690512);
    this.tv2PalomaAzulALertTip.setText(str5);
    this.iv2PalomaAzulALertTip = (ImageView)findViewById(2131296658);
    Drawable drawable5 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_alert_activated_azul_wizard, 2131165272);
    this.iv2PalomaAzulALertTip.setImageDrawable(drawable5);
    this.tvTacheTip = (TextView)findViewById(2131297154);
    String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wakeupcar_estatus_error_header, 2131690496);
    this.tvTacheTip.setText(str4);
    this.ivTacheTip = (ImageView)findViewById(2131296666);
    Drawable drawable4 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_not_executed_azul_wizard, 2131165278);
    this.ivTacheTip.setImageDrawable(drawable4);
    this.tvCarMovementTip = (TextView)findViewById(2131297141);
    this.ivCarMovementTip = (ImageView)findViewById(2131296660);
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_vehiculomovimiento_8, 2131690517);
    this.tvCarMovementTip.setText(str3);
    Drawable drawable3 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.dicas_status_not_available, 2131165402);
    this.ivCarMovementTip.setImageDrawable(drawable3);
    this.tvWaitNotificationTip = (TextView)findViewById(2131297156);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_aguardenotificacion_9, 2131690511);
    this.tvWaitNotificationTip.setText(str2);
    this.ivWaitNotificationTip = (ImageView)findViewById(2131296667);
    Drawable drawable2 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.timeout_azul_fondo, 2131165688);
    this.ivWaitNotificationTip.setImageDrawable(drawable2);
    this.tvtitlewizardtermsandconditions.setGravity(17);
    this.tvtitlewizardnotifications.setGravity(17);
    this.tvTitleTip.setGravity(17);
    this.button_next.setText(this.wizard_tyc_btn_aceptar_4);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_notificaciones_lbl_porcorreo_1, 2131689945);
    this.txtshowEmail = (TextView)findViewById(2131297037);
    this.txtshowEmail.setText(str1);
    this.txtshowEmail.setText(GlobalMembers.userLogged);
    this.txtlabelMail = (TextView)findViewById(2131296680);
    this.txtlabelMail.setText(str1);
    str1 = GlobalMembers.userLogged;
    if (str1 != null && !str1.contains("@")) {
      this.txtshowEmail.setVisibility(8);
      this.txtlabelMail.setVisibility(8);
      this.mailNotifications.setVisibility(8);
    } 
    this.header = (ImageView)findViewById(2131296585);
    Drawable drawable1 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    this.header.setImageDrawable(drawable1);
    this.headerNotifications = (ImageView)findViewById(2131296583);
    this.headerNotifications.setImageDrawable(drawable1);
    this.headerchevrolet = (ImageView)findViewById(2131296588);
    this.headerchevrolet.setBackground(drawable1);
    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams)this.header.getLayoutParams();
    layoutParams1.gravity = 17;
    Resources resources2 = getResources();
    float f2 = TypedValue.applyDimension(1, 0.0F, resources2.getDisplayMetrics());
    float f3 = TypedValue.applyDimension(1, 15.0F, resources2.getDisplayMetrics());
    float f1 = TypedValue.applyDimension(1, 0.0F, resources2.getDisplayMetrics());
    float f4 = TypedValue.applyDimension(1, 0.0F, resources2.getDisplayMetrics());
    layoutParams1.setMargins(Math.round(f2), Math.round(f3), Math.round(f1), Math.round(f4));
    this.header.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams)this.headerNotifications.getLayoutParams();
    layoutParams2.gravity = 17;
    Resources resources3 = getResources();
    f2 = TypedValue.applyDimension(1, 0.0F, resources3.getDisplayMetrics());
    f1 = TypedValue.applyDimension(1, 15.0F, resources3.getDisplayMetrics());
    f4 = TypedValue.applyDimension(1, 0.0F, resources3.getDisplayMetrics());
    f3 = TypedValue.applyDimension(1, 0.0F, resources3.getDisplayMetrics());
    layoutParams2.setMargins(Math.round(f2), Math.round(f1), Math.round(f4), Math.round(f3));
    this.headerNotifications.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
    layoutParams2 = (LinearLayout.LayoutParams)this.headerchevrolet.getLayoutParams();
    layoutParams2.gravity = 17;
    Resources resources1 = getResources();
    f2 = TypedValue.applyDimension(1, 0.0F, resources1.getDisplayMetrics());
    f3 = TypedValue.applyDimension(1, 15.0F, resources1.getDisplayMetrics());
    f1 = TypedValue.applyDimension(1, 0.0F, resources1.getDisplayMetrics());
    f4 = TypedValue.applyDimension(1, 0.0F, resources1.getDisplayMetrics());
    layoutParams2.setMargins(Math.round(f2), Math.round(f3), Math.round(f1), Math.round(f4));
    this.headerchevrolet.setLayoutParams((ViewGroup.LayoutParams)layoutParams2);
  }
  
  protected void onDestroy() {
    super.onDestroy();
  }
  
  @SuppressLint({"NewApi"})
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    FragmentTransaction fragmentTransaction;
    if (paramInt == 4) {
      if (this.viewFlipp.getDisplayedChild() != 0) {
        previousWizard();
      } else {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag("exitLogin");
        if (fragment != null)
          fragmentTransaction.remove(fragment); 
        fragmentTransaction.addToBackStack(null);
        this.loginExit.show(fragmentTransaction, "exitLogin");
      } 
      return true;
    } 
    return super.onKeyDown(paramInt, (KeyEvent)fragmentTransaction);
  }
  
  protected void onResume() {
    super.onResume();
    manageViewElements(1);
    initControls();
    manageViewElements(0);
    enableListeners();
  }
  
  @SuppressLint({"NewApi"})
  protected void onStart() {
    super.onStart();
    (new validateNotificationEmailStatus()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[0]);
  }
  
  private class cnnAuto extends AsyncTask<String, Void, List<Object>> {
    List<Object> activePush = null;
    
    int switchState = 0;
    
    final WizardActivity this$0;
    
    private cnnAuto() {}
    
    protected List<Object> doInBackground(String... param1VarArgs) {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(cnnAuto.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      if (param1VarArgs != null)
        this.switchState = Integer.valueOf(param1VarArgs[0]).intValue(); 
      this.activePush = (new WsAccess((Context)WizardActivity.this)).activePushNotificationAppG2(param1VarArgs[0]);
      return this.activePush;
    }
    
    @SuppressLint({"NewApi"})
    protected void onPostExecute(List<Object> param1List) {
      super.onPostExecute(param1List);
      WizardActivity wizardActivity1 = WizardActivity.this;
      String str1 = Utilities.getStringFromConfigList((Context)wizardActivity1, wizardActivity1.stringsResourcesVO.wizard_notificaciones_global_popup_lbl_recibircel_2, 2131690522);
      WizardActivity wizardActivity2 = WizardActivity.this;
      String str2 = Utilities.getStringFromConfigList((Context)wizardActivity2, wizardActivity2.stringsResourcesVO.wizard_notificaciones_global_popup_lbl_norecibircel_2, 2131690520);
      WizardActivity wizardActivity3 = WizardActivity.this;
      String str3 = Utilities.getStringFromConfigList((Context)wizardActivity3, wizardActivity3.stringsResourcesVO.fail_Un_RegistrerPush, 2131689788);
      if (param1List != null) {
        if (((Integer)param1List.get(1)).intValue() == 0) {
          WizardActivity wizardActivity = WizardActivity.this;
          boolean bool = false;
          WizardActivity.access$2002(wizardActivity, false);
          wizardActivity = WizardActivity.this;
          if (this.switchState == 1)
            bool = true; 
          wizardActivity.checkNotificationState(bool, WizardActivity.this.mailNotifications.isChecked());
          if (this.switchState == 1) {
            Toast.makeText(WizardActivity.this.getApplicationContext(), str1, 1).show();
          } else {
            Toast.makeText(WizardActivity.this.getApplicationContext(), str2, 1).show();
          } 
          try {
            PreferenceRT.SetValuePreference("com.roadtrack.push.notification.status", this.switchState, (Context)WizardActivity.this);
          } catch (Exception exception) {
            Utilities.escribeArchivo("WizardActivity", "Error: onLoginOK: ", exception.toString());
          } 
        } else {
          Toast.makeText(WizardActivity.this.getApplicationContext(), str3, 1).show();
          WizardActivity.access$2002(WizardActivity.this, true);
        } 
        WizardActivity.this.progressAuto.setVisibility(8);
        WizardActivity.this.autoNotifications.setEnabled(true);
        WizardActivity.this.buttons_nextnotif.setEnabled(true);
      } else {
        WizardActivity.this.progressAuto.setVisibility(8);
        WizardActivity.this.autoNotifications.setEnabled(true);
        WizardActivity.this.buttons_nextnotif.setEnabled(true);
        Toast.makeText(WizardActivity.this.getApplicationContext(), str3, 1).show();
        WizardActivity.access$2002(WizardActivity.this, true);
      } 
    }
    
    @SuppressLint({"NewApi"})
    protected void onPreExecute() {
      super.onPreExecute();
      WizardActivity.this.progressAuto.setVisibility(0);
      WizardActivity.this.autoNotifications.setEnabled(false);
      WizardActivity.this.buttons_nextnotif.setEnabled(true);
    }
  }
  
  private class cnnMailActiveNotificationEmail extends AsyncTask<String, Void, List<Object>> {
    List<Object> activeMail = null;
    
    int switchState = 0;
    
    final WizardActivity this$0;
    
    private cnnMailActiveNotificationEmail() {}
    
    @SuppressLint({"NewApi"})
    protected List<Object> doInBackground(String... param1VarArgs) {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(cnnMailActiveNotificationEmail.class.getSimpleName());
      stringBuilder2.append(": ");
      stringBuilder2.append(Thread.currentThread().getName());
      thread.setName(stringBuilder2.toString());
      if (param1VarArgs != null)
        this.switchState = Integer.valueOf(param1VarArgs[0]).intValue(); 
      WsAccess wsAccess = new WsAccess((Context)WizardActivity.this);
      List<UserDevicesVO> list1 = ((onstarApplication)WizardActivity.this.getApplicationContext()).getmDeviceUserList();
      String str1 = new String();
      for (byte b = 0; b < list1.size(); b++) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str1);
        stringBuilder.append(((UserDevicesVO)list1.get(b)).getDeviceId());
        stringBuilder.append(",");
        str1 = stringBuilder.toString();
      } 
      String str2 = str1.substring(0, str1.length() - 1);
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(this.switchState);
      stringBuilder1.append("");
      this.activeMail = wsAccess.activeNotificationEmail(stringBuilder1.toString(), str2);
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append(":");
      stringBuilder1.append(this.activeMail);
      Utilities.escribeArchivo("WizardActivity", "WS result", stringBuilder1.toString());
      List<Object> list = this.activeMail;
      if (list != null) {
        if (((Integer)list.get(1)).intValue() != 0)
          this.activeMail = null; 
      } else {
        this.activeMail = null;
      } 
      return this.activeMail;
    }
    
    @SuppressLint({"NewApi"})
    protected void onPostExecute(List<Object> param1List) {
      super.onPostExecute(param1List);
      WizardActivity wizardActivity1 = WizardActivity.this;
      String str1 = Utilities.getStringFromConfigList((Context)wizardActivity1, wizardActivity1.stringsResourcesVO.wizard_notificaciones_global_popup_lbl_recibirmail_2, 2131690523);
      WizardActivity wizardActivity2 = WizardActivity.this;
      String str2 = Utilities.getStringFromConfigList((Context)wizardActivity2, wizardActivity2.stringsResourcesVO.wizard_notificaciones_global_popup_lbl_norecibirmail_2, 2131690521);
      WizardActivity wizardActivity3 = WizardActivity.this;
      String str3 = Utilities.getStringFromConfigList((Context)wizardActivity3, wizardActivity3.stringsResourcesVO.nothing, 2131690207);
      if (param1List != null) {
        if (((Integer)this.activeMail.get(1)).intValue() == 0) {
          WizardActivity.access$2002(WizardActivity.this, false);
          if (this.switchState == 1) {
            Toast.makeText(WizardActivity.this.getApplicationContext(), str1, 1).show();
          } else {
            Toast.makeText(WizardActivity.this.getApplicationContext(), str2, 1).show();
          } 
        } else {
          Toast.makeText(WizardActivity.this.getApplicationContext(), str2, 1).show();
        } 
        WizardActivity.this.progressMail.setVisibility(8);
        WizardActivity.this.mailNotifications.setEnabled(true);
        WizardActivity.this.buttons_nextnotif.setEnabled(true);
      } else {
        WizardActivity.this.progressMail.setVisibility(8);
        WizardActivity.this.mailNotifications.setEnabled(true);
        WizardActivity.this.buttons_nextnotif.setEnabled(true);
        Toast.makeText(WizardActivity.this.getApplicationContext(), str3, 1).show();
        WizardActivity.access$2002(WizardActivity.this, true);
        if (WizardActivity.this.mailNotifications.isChecked()) {
          WizardActivity.this.mailNotifications.setChecked(false);
        } else {
          WizardActivity.this.mailNotifications.setChecked(true);
        } 
      } 
    }
    
    @SuppressLint({"NewApi"})
    protected void onPreExecute() {
      super.onPreExecute();
      WizardActivity.this.progressMail.setVisibility(0);
      WizardActivity.this.mailNotifications.setEnabled(false);
      WizardActivity.this.buttons_nextnotif.setEnabled(true);
    }
  }
  
  private class validateNotificationEmailStatus extends AsyncTask<String, Void, List<Object>> {
    List<Object> result = null;
    
    final WizardActivity this$0;
    
    private validateNotificationEmailStatus() {}
    
    @SuppressLint({"NewApi"})
    protected List<Object> doInBackground(String... param1VarArgs) {
      if (!NetUtilities.validateNetwork(WizardActivity.this.getApplicationContext(), false))
        return null; 
      this.result = (new WsAccess((Context)WizardActivity.this)).getEmailNotificationStatus();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(":");
      stringBuilder.append(this.result);
      Utilities.escribeArchivo("WizardActivity", "WS result", stringBuilder.toString());
      return this.result;
    }
    
    @SuppressLint({"NewApi"})
    protected void onPostExecute(List<Object> param1List) {
      if (param1List != null && param1List.size() > 1) {
        WizardActivity.access$2402(WizardActivity.this, param1List.get(param1List.size() - 1).toString());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-");
        stringBuilder.append(param1List);
        Utilities.escribeArchivo("WizardActivity", "EmailNotifyTask", stringBuilder.toString());
      } else {
        WizardActivity.access$2402(WizardActivity.this, "");
        Utilities.escribeArchivo("WizardActivity", "EmailNotifyTask", "result error");
      } 
      WizardActivity.access$2502(WizardActivity.this, true);
      if (WizardActivity.this.emailStatus.equals("0")) {
        WizardActivity.this.mailNotifications.setChecked(true);
      } else {
        WizardActivity.this.mailNotifications.setChecked(false);
      } 
      WizardActivity.access$2502(WizardActivity.this, false);
      WizardActivity.this.progressMail.setVisibility(8);
      WizardActivity.this.mailNotifications.setEnabled(true);
    }
    
    @SuppressLint({"NewApi"})
    protected void onPreExecute() {
      super.onPreExecute();
      WizardActivity.this.progressMail.setVisibility(0);
      WizardActivity.this.mailNotifications.setEnabled(false);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/wizard/WizardActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */