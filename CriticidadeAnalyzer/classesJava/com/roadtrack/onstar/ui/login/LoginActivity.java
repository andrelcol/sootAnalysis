package com.roadtrack.onstar.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.text.Editable;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.FollowMeHandler;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.adapter.ClearableEditText;
import com.roadtrack.onstar.adapter.ClearableEditTextPassword;
import com.roadtrack.onstar.async_tasks.tasks.ValidateUrlOnStar;
import com.roadtrack.onstar.interfaces.OnLoginListener;
import com.roadtrack.onstar.new_ws.ExecuteCreateAccount;
import com.roadtrack.onstar.new_ws.ExecuteWS;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.root_utils.RootUtil;
import com.roadtrack.onstar.servicios.ServiceAlertsSocket;
import com.roadtrack.onstar.sockets.ServicioSockets;
import com.roadtrack.onstar.ui.dialogs.DialogTwoOptions;
import com.roadtrack.onstar.ui.wizard.WizardActivity;
import com.roadtrack.onstar.utils.GenericDialog;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.PermissionsManagerMarshmallow;
import com.roadtrack.onstar.utils.SocketListener;
import com.roadtrack.onstar.utils.Utilities;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Vector;

@SuppressLint({"NewApi", "HandlerLeak"})
public class LoginActivity extends Activity implements OnLoginListener {
  private static final String TAG = LoginActivity.class.getSimpleName();
  
  private static Context context;
  
  private static onstarApplication rtApp;
  
  private static final String strAppID;
  
  public static String strVehicleList = "";
  
  String OfsetTime;
  
  private String P_REMEMBERED = "p_remembered";
  
  private String SWITCH_REMEMBERED = "switch_remembered";
  
  private String U_REMEMBERED = "u_remembered";
  
  private Button access;
  
  private Button btn1;
  
  private Button btn2;
  
  private Button btnNOk;
  
  private Button btnNOk1;
  
  private Button btnOk;
  
  private Button btnOk1;
  
  private Button btnOk2;
  
  private Button btnOk3;
  
  private Button btnOk4;
  
  private SwitchCompat chkRememberPassword;
  
  private DBFunctions dbFun;
  
  private DrawableResourcesVO drawableResourcesVO;
  
  private EditText edit_text;
  
  private TextView errorLogin;
  
  private ImageView headerlogin;
  
  private ImageView img_email;
  
  private ImageView img_pass;
  
  private LinearLayout layPassword;
  
  private TextView link;
  
  private DialogTwoOptions loginExit;
  
  private CountDownTimer loginTimer;
  
  private String msgErrorId = null;
  
  private ProgressBar progressBar;
  
  private boolean readyToLogin = false;
  
  private TextView remmember;
  
  private String respUrlService1 = "";
  
  private String respUrlService2 = "";
  
  private Boolean saveAccess;
  
  private String strUUID;
  
  private StringsResourcesVO stringsResourcesVO = null;
  
  private TextView textdata_login;
  
  private Typeface tf_LouisRegular;
  
  boolean timeexpiderloginaction;
  
  private TextView tvAlterarEmail;
  
  private TextView tvCell;
  
  private TextView tvCreateAccount;
  
  private TextView tvForgetPass;
  
  private ClearableEditTextPassword txtPassword;
  
  private ClearableEditText user;
  
  private UserPreferenceVO userPreference;
  
  private String user_outState = "";
  
  private ExecuteWS wcfLogin1;
  
  private ExecuteWS wcfLogin2;
  
  static {
    strAppID = Integer.toString(13);
  }
  
  private void DialogFailLoginServiceDown() {
    String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.login_popup_sitioenmantenimiento, 2131690025);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.nothing, 2131690207);
    final Dialog dialog = Utilities.simpleDialog(context, null, str2, str4, true, str1, false, str3, 22.0F, 16.0F, 2131034260);
    this.btnNOk1 = (Button)dialog.findViewById(2131296343);
    this.btnNOk1.setOnClickListener(new View.OnClickListener() {
          final LoginActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            LoginActivity.this.manageViewElements(8);
            LoginActivity.this.unlockChangeOrientation();
          }
        });
    dialog.show();
  }
  
  private void DialogVersion() {
    String str1 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str4 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.actualizarapp_lbl_necesitaactualizar, 2131689649);
    String str3 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_lbl_actualizar_1, 2131689954);
    String str2 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.renovacion_popup_lbl_ahorano, 2131690328);
    final Dialog oldversion = Utilities.simpleDialog(context, null, str1, str4, true, str3, true, str2);
    dialog.setOnKeyListener(new DialogInterface.OnKeyListener(this) {
          final Dialog val$oldversion;
          
          public boolean onKey(DialogInterface param1DialogInterface, int param1Int, KeyEvent param1KeyEvent) {
            if (param1Int == 4) {
              oldversion.dismiss();
              System.exit(0);
            } 
            return true;
          }
        });
    this.btn1 = (Button)dialog.findViewById(2131296343);
    this.btn2 = (Button)dialog.findViewById(2131296344);
    this.btn1.setOnClickListener(new View.OnClickListener() {
          final LoginActivity this$0;
          
          final Dialog val$oldversion;
          
          public void onClick(View param1View) {
            oldversion.dismiss();
            LoginActivity.this.getPackageName();
            try {
              LoginActivity.this.intentToURL(1);
            } catch (ActivityNotFoundException activityNotFoundException) {
              LoginActivity.this.intentToURL(2);
            } 
            System.exit(0);
          }
        });
    this.btn2.setOnClickListener(new View.OnClickListener() {
          final LoginActivity this$0;
          
          final Dialog val$oldversion;
          
          public void onClick(View param1View) {
            oldversion.dismiss();
            LoginActivity.this.manageViewElements(8);
          }
        });
    dialog.show();
  }
  
  private String[] GetNumberOfAttemps(String paramString) {
    String[] arrayOfString = paramString.split("\\|");
    paramString = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("returnData:");
    stringBuilder.append(arrayOfString.length);
    Utilities.escribeArchivo(paramString, "POLICY PASSWORD", stringBuilder.toString());
    return (arrayOfString.length >= 3) ? arrayOfString : null;
  }
  
  private String GetUuiData() {
    String str = (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date());
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Utilities.DeviceUuidFactory(GlobalMembers.contexGlobal));
    stringBuilder.append("|");
    stringBuilder.append(str);
    stringBuilder.append("|");
    stringBuilder.append(GlobalMembers.CodeVersion);
    return stringBuilder.toString();
  }
  
  private void NewDialogFailConnection(String[] paramArrayOfString) {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   5: getfield login_main_lbl_intentologin1 : Ljava/lang/String;
    //   8: ldc_w 2131690014
    //   11: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   14: astore_3
    //   15: aload_0
    //   16: aload_0
    //   17: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   20: getfield login_main_lbl_intentologin2 : Ljava/lang/String;
    //   23: ldc_w 2131690015
    //   26: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   29: astore #4
    //   31: aload_1
    //   32: iconst_2
    //   33: aaload
    //   34: ifnull -> 60
    //   37: aload_1
    //   38: iconst_1
    //   39: aaload
    //   40: ifnull -> 60
    //   43: aload_1
    //   44: iconst_1
    //   45: aaload
    //   46: invokestatic parseInt : (Ljava/lang/String;)I
    //   49: aload_1
    //   50: iconst_2
    //   51: aaload
    //   52: invokestatic parseInt : (Ljava/lang/String;)I
    //   55: isub
    //   56: istore_2
    //   57: goto -> 62
    //   60: iconst_0
    //   61: istore_2
    //   62: aload_3
    //   63: ldc_w '%s'
    //   66: aload_1
    //   67: iconst_1
    //   68: aaload
    //   69: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   72: astore_3
    //   73: new java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial <init> : ()V
    //   80: astore #5
    //   82: aload #5
    //   84: iload_2
    //   85: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload #5
    //   91: ldc ''
    //   93: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: aload #4
    //   99: ldc_w '%s'
    //   102: aload #5
    //   104: invokevirtual toString : ()Ljava/lang/String;
    //   107: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   110: astore #5
    //   112: new java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial <init> : ()V
    //   119: astore #4
    //   121: aload #4
    //   123: aload_3
    //   124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: pop
    //   128: aload #4
    //   130: ldc_w ' '
    //   133: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: pop
    //   137: aload #4
    //   139: aload #5
    //   141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: pop
    //   145: aload #4
    //   147: invokevirtual toString : ()Ljava/lang/String;
    //   150: astore #4
    //   152: aload_1
    //   153: iconst_2
    //   154: aaload
    //   155: invokestatic parseInt : (Ljava/lang/String;)I
    //   158: aload_1
    //   159: iconst_1
    //   160: aaload
    //   161: invokestatic parseInt : (Ljava/lang/String;)I
    //   164: if_icmpge -> 212
    //   167: aload #4
    //   169: astore_3
    //   170: aload_1
    //   171: iconst_3
    //   172: aaload
    //   173: invokestatic parseInt : (Ljava/lang/String;)I
    //   176: ifne -> 209
    //   179: aload #4
    //   181: astore_3
    //   182: aload_1
    //   183: iconst_2
    //   184: aaload
    //   185: ifnull -> 209
    //   188: aload #4
    //   190: astore_3
    //   191: aload_1
    //   192: iconst_1
    //   193: aaload
    //   194: ifnull -> 209
    //   197: aload #4
    //   199: astore_3
    //   200: aload_1
    //   201: iconst_3
    //   202: aaload
    //   203: ifnull -> 209
    //   206: goto -> 212
    //   209: goto -> 230
    //   212: aload_0
    //   213: aload_0
    //   214: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   217: getfield global_popup_lbl_pasbloqueado : Ljava/lang/String;
    //   220: ldc_w 2131689960
    //   223: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   226: astore_3
    //   227: goto -> 209
    //   230: aload_0
    //   231: aload_0
    //   232: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   235: getfield global_popup_lbl_aviso_1 : Ljava/lang/String;
    //   238: ldc 2131689955
    //   240: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   243: astore #5
    //   245: aload_0
    //   246: aload_0
    //   247: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   250: getfield login_popup_lbl_intentarnuevamente : Ljava/lang/String;
    //   253: ldc_w 2131690022
    //   256: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   259: astore #4
    //   261: aload_0
    //   262: aload_0
    //   263: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   266: getfield login_popup_lbl_lrecuperarpas : Ljava/lang/String;
    //   269: ldc_w 2131690023
    //   272: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   275: astore_1
    //   276: getstatic com/roadtrack/onstar/ui/login/LoginActivity.context : Landroid/content/Context;
    //   279: aconst_null
    //   280: aload #5
    //   282: aload_3
    //   283: iconst_1
    //   284: aload #4
    //   286: iconst_1
    //   287: aload_1
    //   288: ldc 22.0
    //   290: ldc 16.0
    //   292: invokestatic simpleDialog : (Landroid/content/Context;Landroid/graphics/drawable/Drawable;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/String;FF)Landroid/app/Dialog;
    //   295: astore_1
    //   296: aload_0
    //   297: aload_1
    //   298: ldc_w 2131296344
    //   301: invokevirtual findViewById : (I)Landroid/view/View;
    //   304: checkcast android/widget/Button
    //   307: putfield btnOk4 : Landroid/widget/Button;
    //   310: aload_0
    //   311: aload_1
    //   312: ldc 2131296343
    //   314: invokevirtual findViewById : (I)Landroid/view/View;
    //   317: checkcast android/widget/Button
    //   320: putfield btnNOk : Landroid/widget/Button;
    //   323: aload_0
    //   324: getfield btnNOk : Landroid/widget/Button;
    //   327: new com/roadtrack/onstar/ui/login/LoginActivity$22
    //   330: dup
    //   331: aload_0
    //   332: aload_1
    //   333: invokespecial <init> : (Lcom/roadtrack/onstar/ui/login/LoginActivity;Landroid/app/Dialog;)V
    //   336: invokevirtual setOnClickListener : (Landroid/view/View$OnClickListener;)V
    //   339: aload_0
    //   340: getfield btnOk4 : Landroid/widget/Button;
    //   343: new com/roadtrack/onstar/ui/login/LoginActivity$23
    //   346: dup
    //   347: aload_0
    //   348: aload_1
    //   349: invokespecial <init> : (Lcom/roadtrack/onstar/ui/login/LoginActivity;Landroid/app/Dialog;)V
    //   352: invokevirtual setOnClickListener : (Landroid/view/View$OnClickListener;)V
    //   355: aload_1
    //   356: invokevirtual show : ()V
    //   359: return
    //   360: astore_1
    //   361: aload_0
    //   362: invokespecial OldDialogFailConnection : ()V
    //   365: return
    // Exception table:
    //   from	to	target	type
    //   152	167	360	java/lang/Exception
    //   170	179	360	java/lang/Exception
    //   212	227	360	java/lang/Exception
  }
  
  private void OldDialogFailConnection() {
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.login_global_popup_lbl_errorcredenciales_2, 2131690011);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    final Dialog dialog = Utilities.simpleDialog(context, null, str3, str2, true, str1, false, "", 22.0F, 16.0F);
    this.btnOk3 = (Button)dialog.findViewById(2131296343);
    this.btnOk3.setOnClickListener(new View.OnClickListener() {
          final LoginActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            LoginActivity.this.manageViewElements(8);
            LoginActivity.this.unlockChangeOrientation();
          }
        });
    dialog.show();
  }
  
  private void afterStartSession(String paramString) {
    Intent intent;
    GlobalMembers.usingPush = true;
    new Intent(getApplicationContext(), MainActivity.class);
    int i = Integer.parseInt(GlobalMembers.version);
    if (paramString.trim().length() > 8) {
      boolean bool = this.dbFun.existUserWithTerms(this.user.getText().toString().trim().toLowerCase()).booleanValue();
      paramString = "Y";
      if (bool) {
        this.dbFun.updateHistoricalOrphans();
        UserPreferenceVO userPreferenceVO = this.dbFun.getUserPreference(this.user.getText().toString());
        if (userPreferenceVO != null)
          rtApp.setUserPreference(userPreferenceVO); 
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        if (!this.saveAccess.booleanValue())
          paramString = "N"; 
        this.dbFun.updateSaveAccess(this.user.getText().toString(), paramString);
        intent1.putExtra("saveAccess", paramString);
        GlobalMembers.ToExitApp = false;
        intent = intent1;
      } else {
        Intent intent1 = new Intent(getApplicationContext(), WizardActivity.class);
        intent1.putExtra("user", this.user.getText().toString());
        intent1.putExtra("magicSpell", this.txtPassword.getText().toString());
        if (!this.saveAccess.booleanValue())
          paramString = "N"; 
        intent1.putExtra("saveAccess", paramString);
        intent = intent1;
      } 
      String str = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Version backend:");
      stringBuilder.append(i);
      stringBuilder.append(" version app:");
      stringBuilder.append(GlobalMembers.CodeVersion);
      Utilities.escribeArchivo(str, "CodeVersion", stringBuilder.toString());
      startActivity(intent);
      finish();
      GlobalMembers.userLogged = this.user.getText().toString();
    } else if (intent.equals("2")) {
      manageViewElements(6);
      String str = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.login_global_popup_lbl_errorcredenciales_2, 2131690011);
      this.errorLogin.setText(str);
    } else {
      manageViewElements(6);
      String str = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.loginFailureChevy, 2131690008);
      this.errorLogin.setText(str);
    } 
  }
  
  private void enableListeners() {
    if (RootUtil.checkIsRootMyDeviceOrEmulator(context))
      return; 
    this.access.setOnClickListener(new View.OnClickListener() {
          final LoginActivity this$0;
          
          public void onClick(View param1View) {
            LoginActivity.this.lockChangeOrientation();
            ((InputMethodManager)LoginActivity.this.getApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(param1View.getApplicationWindowToken(), 2);
            LoginActivity.this.validateLogin();
          }
        });
    this.user.setClearableEditTextEventListener(new ClearableEditText.OnClearableEditTextListener() {
          final LoginActivity this$0;
          
          public boolean onKeyEditText(View param1View, int param1Int, KeyEvent param1KeyEvent) {
            int i = param1KeyEvent.getAction();
            boolean bool2 = false;
            boolean bool1 = bool2;
            if (i == 0) {
              bool1 = bool2;
              if (param1Int == 66) {
                bool1 = bool2;
                if (LoginActivity.this.txtPassword.lenght() != 0) {
                  LoginActivity.this.validateLogin();
                  if (LoginActivity.this.readyToLogin) {
                    LoginActivity.this.validateLogin();
                  } else {
                    LoginActivity.this.txtPassword.requestFocus();
                  } 
                  ((InputMethodManager)LoginActivity.this.getApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(param1View.getApplicationWindowToken(), 2);
                  bool1 = bool2;
                  if (param1Int != 66)
                    bool1 = true; 
                } 
              } 
            } 
            return bool1;
          }
        });
    this.user.setClickClearableEditTextEventListener(new ClearableEditText.OnClickClearableEditTextListener() {
          final LoginActivity this$0;
          
          public void onClickEditText(View param1View) {
            LoginActivity.this.txtPassword.setText("");
            LoginActivity.this.errorLogin.setText("");
            LoginActivity.this.chkRememberPassword.setChecked(false);
          }
        });
    this.user.setAddTextChangedClearableEditTextEventListener(new ClearableEditText.OnAddTextChangedCETListener() {
          final LoginActivity this$0;
          
          public void onAddTextChangedCET(Editable param1Editable) {
            LoginActivity.this.manageViewElements(2);
          }
        });
    this.txtPassword.setClickClearableEditTextEventListener(new ClearableEditTextPassword.OnClickClearableEditTextListener(this) {
        
        });
    this.txtPassword.setAddTextChangedClearableEditTextEventListener(new ClearableEditTextPassword.OnAddTextChangedCETListener() {
          final LoginActivity this$0;
          
          public void onAddTextChangedCET(Editable param1Editable) {
            LoginActivity.this.manageViewElements(2);
          }
        });
    this.txtPassword.setOnKeyListener(new View.OnKeyListener() {
          final LoginActivity this$0;
          
          public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
            if (param1KeyEvent.getAction() == 0 && param1Int == 66) {
              LoginActivity.this.validateLogin();
              return true;
            } 
            return false;
          }
        });
    this.txtPassword.setOnTouchListener(new View.OnTouchListener() {
          final LoginActivity this$0;
          
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            String str = LoginActivity.this.user.getText().toString();
            if (str != null && !str.equals("") && str.contains("@")) {
              boolean bool;
              UserPreferenceVO userPreferenceVO = LoginActivity.this.dbFun.getUserPreferenceSaveAcccess(str, LoginActivity.this.OfsetTime);
              if (userPreferenceVO != null) {
                String str1 = userPreferenceVO.getMagic_spell();
                bool = userPreferenceVO.getSave_access().equals("Y");
              } else {
                param1View = null;
                bool = false;
              } 
              if (bool) {
                LoginActivity.this.txtPassword.setText((String)param1View);
                LoginActivity.this.chkRememberPassword.setChecked(bool);
              } else {
                LoginActivity.access$1202(LoginActivity.this, str);
              } 
            } 
            return false;
          }
        });
    this.loginExit.setOnClickDialogListener(new DialogTwoOptions.OnClickDialogListener() {
          final LoginActivity this$0;
          
          public void onClickDialog(int param1Int) {
            LoginActivity.this.finish();
          }
        });
    this.chkRememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final LoginActivity this$0;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            LoginActivity.this.setLastUser();
            LoginActivity.access$1402(LoginActivity.this, Boolean.valueOf(param1Boolean));
          }
        });
    this.tvAlterarEmail.setOnClickListener(new View.OnClickListener() {
          final LoginActivity this$0;
          
          public void onClick(View param1View) {
            String str1;
            LoginActivity loginActivity1 = LoginActivity.this;
            String str2 = Utilities.getStringFromConfigList((Context)loginActivity1, loginActivity1.stringsResourcesVO.global_cambiarmail, 2131689835);
            LoginActivity loginActivity2 = LoginActivity.this;
            Utilities.getStringFromConfigList((Context)loginActivity2, loginActivity2.stringsResourcesVO.global_cambiarmail2, 2131689836);
            String[] arrayOfString = str2.split("\n");
            String str3 = arrayOfString[0];
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("<a href=");
            stringBuilder2.append(GlobalMembers.URL_ChangeEmail);
            stringBuilder2.append(">");
            stringBuilder2.append(arrayOfString[1]);
            stringBuilder2.append("</a>");
            String str6 = stringBuilder2.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("<center>");
            stringBuilder3.append(str3);
            stringBuilder3.append(" ");
            stringBuilder3.append(str6);
            stringBuilder3.append(" ");
            stringBuilder3.append(arrayOfString[2]);
            stringBuilder3.append("</center>");
            str3 = stringBuilder3.toString();
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("<a href=");
            stringBuilder1.append(GlobalMembers.renewalPhone);
            stringBuilder1.append(">");
            stringBuilder1.append(GlobalMembers.renewalPhone);
            stringBuilder1.append("</a>");
            String str5 = stringBuilder1.toString();
            if (arrayOfString.length > 3) {
              stringBuilder3 = new StringBuilder();
              stringBuilder3.append("<center>");
              stringBuilder3.append(str5);
              stringBuilder3.append(arrayOfString[3].replace("%s", " "));
              stringBuilder3.append("</center>");
              str1 = stringBuilder3.toString();
            } else {
              str1 = "";
            } 
            LoginActivity loginActivity3 = LoginActivity.this;
            String str4 = Utilities.getStringFromConfigList((Context)loginActivity3, loginActivity3.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
            LoginActivity loginActivity4 = LoginActivity.this;
            String str7 = Utilities.getStringFromConfigList((Context)loginActivity4, loginActivity4.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
            loginActivity4 = LoginActivity.this;
            Utilities.getStringFromConfigList((Context)loginActivity4, loginActivity4.stringsResourcesVO.login_global_popup_lbl_errorcredenciales_2, 2131690011);
            final Dialog dialog = Utilities.simpleDialogHiper((Context)LoginActivity.this, null, str7, str3, true, str4, false, "", 22.0F, 16.0F, str1);
            LoginActivity.access$1602(LoginActivity.this, (TextView)dialog.findViewById(2131296257));
            LoginActivity.this.link.setOnClickListener(new View.OnClickListener(this) {
                  final Dialog val$dialog;
                  
                  public void onClick(View param2View) {
                    (new ValidateUrlOnStar(LoginActivity.context, dialog)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
                  }
                });
            LoginActivity.access$1802(LoginActivity.this, (TextView)dialog.findViewById(2131296258));
            LoginActivity.this.tvCell.setOnClickListener(new View.OnClickListener(this) {
                  public void onClick(View param2View) {
                    Utilities.sendIntentCallAction(LoginActivity.context);
                  }
                });
            LoginActivity.access$1902(LoginActivity.this, (Button)dialog.findViewById(2131296343));
            LoginActivity.this.btnOk1.setOnClickListener(new View.OnClickListener(this) {
                  final Dialog val$dialog;
                  
                  public void onClick(View param2View) {
                    dialog.dismiss();
                  }
                });
            dialog.show();
          }
        });
    this.tvForgetPass.setOnClickListener(new View.OnClickListener() {
          final LoginActivity this$0;
          
          public void onClick(View param1View) {
            if (!Utilities.isAndinos().booleanValue())
              try {
                LoginActivity.this.intentToURL(0);
              } catch (Exception exception) {
                LoginActivity loginActivity = LoginActivity.this;
                String str = Utilities.getStringFromConfigList((Context)loginActivity, loginActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText((Context)LoginActivity.this, str, 1).show();
              }  
          }
        });
    this.tvCreateAccount.setOnClickListener(new View.OnClickListener() {
          final LoginActivity this$0;
          
          public void onClick(View param1View) {
            String str2 = Utilities.DeviceUuidFactory(GlobalMembers.contexGlobal);
            String str1 = Integer.toString(13);
            LoginActivity.this.getPackageName();
            (new ExecuteCreateAccount((Context)LoginActivity.this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str1, str2 });
          }
        });
  }
  
  private void getLastUser() {
    String str = this.dbFun.getLastUser();
    if (str.equals("##NULLVALOR##")) {
      this.msgErrorId = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.ErrLoginDB, 2131689494);
      String str1 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
      str = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
      final Dialog dialog2 = Utilities.simpleDialog(context, null, str1, this.msgErrorId, true, str, false, "");
      this.btnOk2 = (Button)dialog.findViewById(2131296343);
      this.btnOk2.setOnClickListener(new View.OnClickListener(this) {
            final Dialog val$dialog2;
            
            public void onClick(View param1View) {
              dialog2.dismiss();
              System.exit(0);
            }
          });
      dialog.show();
      return;
    } 
    if (!str.isEmpty()) {
      UserPreferenceVO userPreferenceVO = this.dbFun.getUserPreferenceSaveAcccess(str, this.OfsetTime);
      if (userPreferenceVO != null) {
        String str1 = userPreferenceVO.getMagic_spell();
        boolean bool = userPreferenceVO.getSave_access().equals("Y");
        this.user.setText(str);
        if (bool)
          this.txtPassword.setText(str1); 
        this.chkRememberPassword.setChecked(bool);
      } 
    } 
  }
  
  private void getSavedUser() {
    String str1;
    String str2 = this.dbFun.getLastUser();
    ClearableEditText clearableEditText = this.user;
    if (!str2.equals("")) {
      str1 = str2;
    } else {
      str1 = this.user_outState;
    } 
    clearableEditText.setText(str1);
    this.user_outState = "";
    getLastUser();
    UserPreferenceVO userPreferenceVO = this.dbFun.getUserPreferenceSaveAcccess(str2, this.OfsetTime);
    if (userPreferenceVO != null) {
      str1 = userPreferenceVO.getMagic_spell();
      String str = userPreferenceVO.getSave_access();
      if (str != null) {
        this.saveAccess = Boolean.valueOf(str.equals("Y"));
        if (str.equals("Y")) {
          this.txtPassword.setText(str1);
          this.chkRememberPassword.setChecked(true);
          getWindow().setSoftInputMode(2);
        } 
      } else {
        this.txtPassword.setText("");
        this.chkRememberPassword.setChecked(false);
      } 
    } 
    this.user.clearFocusClearableText();
    this.txtPassword.clearFocus();
  }
  
  @SuppressLint({"WrongViewCast"})
  private void initControls() {
    this.drawableResourcesVO = new DrawableResourcesVO();
    LinearLayout linearLayout1 = (LinearLayout)findViewById(2131297223);
    String str7 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.login_main_lbl_introduzca_1, 2131690016);
    String str8 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.login_main_lbl_recordar_4, 2131690018);
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.login_main_btn_iniciar_6, 2131690013);
    String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.login_main_lbl_olvido_5, 2131690017);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_main_lbl_saliraplicacion_1, 2131689937);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str5 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.CreateAccount, 2131689487);
    String str6 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_cambiousuario, 2131689910);
    setContentView(2131427365);
    this.textdata_login = (TextView)findViewById(2131297109);
    this.textdata_login.setText(str7);
    this.remmember = (TextView)findViewById(2131296978);
    this.remmember.setText(str8);
    this.headerlogin = (ImageView)findViewById(2131296590);
    getResources().getConfiguration();
    Drawable drawable2 = Utilities.getDrawableFromConfigList((Context)this, this.drawableResourcesVO.ic_login_icon, 2131165486);
    this.headerlogin.setBackground(drawable2);
    ImageView imageView = (ImageView)findViewById(2131296620);
    this.layPassword = (LinearLayout)findViewById(2131296948);
    LinearLayout linearLayout2 = (LinearLayout)findViewById(2131296684);
    linearLayout2 = (LinearLayout)findViewById(2131296860);
    this.edit_text = (EditText)findViewById(2131296473);
    this.edit_text.setImeOptions(5);
    this.user = (ClearableEditText)findViewById(2131296859);
    this.user.setTypeInputEditText(32);
    this.user.setMaxLength(100);
    this.user.setMaxLines(1);
    this.txtPassword = (ClearableEditTextPassword)findViewById(2131296857);
    this.txtPassword.setFilters(new InputFilter[] { (InputFilter)new InputFilter.LengthFilter(50) });
    this.txtPassword.setMaxLines(1);
    this.access = (Button)findViewById(2131296856);
    this.access.setText(str3);
    this.progressBar = (ProgressBar)findViewById(2131296858);
    this.errorLogin = (TextView)findViewById(2131296517);
    this.chkRememberPassword = (SwitchCompat)findViewById(2131296465);
    this.tvAlterarEmail = (TextView)findViewById(2131297140);
    this.tvAlterarEmail.setText(str6);
    this.tvForgetPass = (TextView)findViewById(2131297148);
    this.tvForgetPass.setText(str4);
    this.tvCreateAccount = (TextView)findViewById(2131297144);
    this.tvCreateAccount.setText(str5);
    this.img_email = (ImageView)findViewById(2131296626);
    Drawable drawable1 = Utilities.getDrawableFromConfigList((Context)this, this.drawableResourcesVO.ic_action_email, 2131165436);
    this.img_email.setImageDrawable(drawable1);
    this.img_pass = (ImageView)findViewById(2131296662);
    drawable1 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.icon_secret, 2131165553);
    this.img_pass.setImageDrawable(drawable1);
    if (Utilities.isAndinos().booleanValue()) {
      this.tvForgetPass.setVisibility(8);
    } else {
      String str = GlobalMembers.FORGET_ACCESS;
      if (str != null && !str.isEmpty())
        this.tvForgetPass.setVisibility(0); 
    } 
    this.saveAccess = Boolean.valueOf(false);
    this.access.setEnabled(false);
    this.access.setClickable(false);
    this.chkRememberPassword.setEnabled(false);
    this.loginExit = DialogTwoOptions.newInstance(str2, str1);
    myDeviceIsRootEmulator();
  }
  
  private void intentToURL(int paramInt) {
    Uri uri = null;
    if (paramInt == 0) {
      try {
        Uri uri1 = Uri.parse(GlobalMembers.FORGET_ACCESS);
        uri = uri1;
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      } 
    } else if (paramInt == 1) {
      String str = getPackageName();
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(GlobalMembers.MARKET_ADDRESS);
        stringBuilder.append(str);
        Uri uri1 = Uri.parse(stringBuilder.toString());
        uri = uri1;
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      } 
    } else {
      String str = getPackageName();
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(GlobalMembers.STORE_ADDRESS);
        stringBuilder.append(str);
        Uri uri1 = Uri.parse(stringBuilder.toString());
        uri = uri1;
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      } 
    } 
    if (uri != null)
      try {
        Intent intent = new Intent();
        this("android.intent.action.VIEW", uri);
        intent.addFlags(268435456);
        getApplication().startActivity(intent);
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error", "ActionNotFound");
      }  
  }
  
  private void lockChangeOrientation() {
    try {
      setRequestedOrientation(14);
    } catch (Exception exception) {
      Utilities.escribeArchivo(LoginActivity.class.getSimpleName(), "Error: ", exception.getMessage());
    } 
  }
  
  private void loginFailureMessage() {
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    final Dialog dialog = Utilities.simpleDialog(context, null, str3, str2, true, str1, false, "");
    ((Button)dialog.findViewById(2131296343)).setOnClickListener(new View.OnClickListener() {
          final LoginActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            LoginActivity.this.manageViewElements(8);
            LoginActivity.this.unlockChangeOrientation();
          }
        });
    try {
      dialog.show();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: loginFailureMessage", exception.getMessage());
    } 
  }
  
  private void loginFailureMessage2(String paramString) {
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("strRespuesta:");
    stringBuilder.append(paramString);
    Utilities.escribeArchivo(str, "POLICY PASSWORD", stringBuilder.toString());
    String[] arrayOfString = GetNumberOfAttemps(paramString);
    if (arrayOfString != null) {
      NewDialogFailConnection(arrayOfString);
    } else {
      OldDialogFailConnection();
    } 
  }
  
  private void loginFailureServiceDown(String paramString) {
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("strRespuesta:");
    stringBuilder.append(paramString);
    Utilities.escribeArchivo(str, "POLICY PASSWORD", stringBuilder.toString());
    if (paramString.substring(9, 10).equals(ExecuteWS.SERVICE_DOWN_CODE)) {
      DialogFailLoginServiceDown();
    } else {
      OldDialogFailConnection();
    } 
  }
  
  @SuppressLint({"NewApi", "CommitTransaction"})
  private void manageViewElements(int paramInt) {
    String str1;
    final Dialog dialog;
    FragmentTransaction fragmentTransaction;
    String str2;
    FragmentManager fragmentManager;
    Fragment fragment;
    if (RootUtil.checkIsRootMyDeviceOrEmulator(context))
      return; 
    int i = 0;
    boolean bool1 = false;
    boolean bool2 = true;
    switch (paramInt) {
      case 9:
        manageViewElements(6);
        str2 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
        str1 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
        dialog = Utilities.simpleDialog(context, null, str2, this.msgErrorId, true, str1, false, "");
        this.btnOk = (Button)dialog.findViewById(2131296343);
        this.btnOk.setOnClickListener(new View.OnClickListener() {
              final LoginActivity this$0;
              
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                dialog.dismiss();
                LoginActivity.this.unlockChangeOrientation();
              }
            });
        dialog.show();
        break;
      case 8:
        this.user.setEnabled(true);
        this.user.setEnabledText(true);
        this.txtPassword.setEnabled(true);
        this.access.setClickable(true);
        this.progressBar.setVisibility(4);
        this.chkRememberPassword.setEnabled(true);
        break;
      case 7:
        if (this.edit_text.length() > 0)
          bool1 = true; 
        if (bool1) {
          paramInt = 2131427390;
        } else {
          paramInt = 2131165659;
        } 
        this.access.setEnabled(bool1);
        this.access.setBackgroundResource(paramInt);
        this.chkRememberPassword.setEnabled(bool1);
        break;
      case 6:
        this.user.setEnabled(true);
        this.user.setEnabledText(true);
        this.txtPassword.setEnabled(true);
        this.access.setClickable(true);
        this.progressBar.setVisibility(4);
        this.chkRememberPassword.setEnabled(true);
        break;
      case 5:
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragmentManager.findFragmentByTag("exitLogin");
        if (fragment != null) {
          ((DialogFragment)fragment).dismiss();
          fragmentTransaction.remove(fragment);
        } 
        fragmentTransaction.addToBackStack(null);
        break;
      case 2:
        if (this.txtPassword.lenght() > 0 && this.user.lenght() > 0) {
          bool1 = bool2;
        } else {
          bool1 = false;
        } 
        if (bool1) {
          paramInt = i;
        } else {
          paramInt = 8;
        } 
        if (bool1) {
          i = 2131165660;
        } else {
          i = 2131034212;
        } 
        this.access.setEnabled(bool1);
        this.access.setBackgroundResource(i);
        this.txtPassword.setVisibilityEyed(paramInt);
        this.txtPassword.setVisibilityEyed(paramInt);
        this.chkRememberPassword.setEnabled(bool1);
        break;
      case 1:
        this.user.setEnabled(true);
        this.user.setEnabledText(true);
        this.txtPassword.setEnabled(true);
        this.txtPassword.setText("");
        this.access.setClickable(true);
        this.progressBar.setVisibility(4);
        this.chkRememberPassword.setEnabled(true);
        break;
      case 0:
        this.user.setEnabled(false);
        this.user.setEnabledText(false);
        this.txtPassword.setEnabled(false);
        this.access.setClickable(false);
        this.progressBar.setVisibility(0);
        this.chkRememberPassword.setEnabled(false);
        break;
    } 
  }
  
  private void myDeviceIsRootEmulator() {
    if (RootUtil.checkIsRootMyDeviceOrEmulator(context)) {
      Toast.makeText((Context)this, "El app no es compatible con su telefono", 1).show();
      this.txtPassword.setEnabled(false);
      this.tvAlterarEmail.setEnabled(false);
      this.tvCreateAccount.setEnabled(false);
      this.tvForgetPass.setEnabled(false);
      this.tvForgetPass.setClickable(false);
      this.access.setClickable(false);
      this.access.setEnabled(false);
      this.chkRememberPassword.setEnabled(false);
      this.layPassword.setEnabled(false);
      this.user.setEnabled(false);
      this.user.setEnabledText(false);
      this.user.setText("");
      this.txtPassword.setText("");
    } 
  }
  
  private void setLastUser() {
    String str2 = this.user.getText().toString().trim().toLowerCase();
    String str1 = this.txtPassword.getText().toString();
    boolean bool = this.chkRememberPassword.isChecked();
    if (!str2.isEmpty() && !str1.isEmpty()) {
      this.dbFun.updateUser(str2, str1, bool);
      this.dbFun.setLastUser(str2, "BR", "pt");
    } 
  }
  
  private void setLastUserForOrientationChanged() {
    String str2 = this.user.getText().toString().trim().toLowerCase();
    String str1 = this.txtPassword.getText().toString();
    boolean bool = this.chkRememberPassword.isChecked();
    this.dbFun.updateUser(str2, str1, bool);
    this.dbFun.setLastUser(str2, "BR", "pt");
  }
  
  private void unlockChangeOrientation() {
    try {
      setRequestedOrientation(4);
    } catch (Exception exception) {
      Utilities.escribeArchivo(LoginActivity.class.getSimpleName(), "Error: ", exception.getMessage());
    } 
  }
  
  private void validateLogin() {
    if (!getpermissions()) {
      Toast.makeText((Context)this, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_faltanpermisos_1, 2131689922), 1).show();
      this.txtPassword.setText("");
      return;
    } 
    ClearableEditText clearableEditText = this.user;
    clearableEditText.setText(clearableEditText.getText().toString().trim().toLowerCase());
    String str1 = this.user.getText().toString().toLowerCase();
    String str2 = this.txtPassword.getText().toString();
    this.readyToLogin = false;
    this.errorLogin.setText("");
    GlobalMembers.bWCFFInishLogin = false;
    manageViewElements(0);
    boolean bool = NetUtilities.validateNetwork(context, false);
    if (str1.equals("") || str2.equals("")) {
      this.msgErrorId = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.login_popup_lbl_esnecesario, 2131690021);
      manageViewElements(9);
      return;
    } 
    if (!bool) {
      this.msgErrorId = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.login_global_popup_lbl_sindatos_2, 2131690012);
      manageViewElements(9);
      return;
    } 
    setLastUser();
    Utilities utilities = new Utilities();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str2);
    stringBuilder.append(GlobalMembers.stringToAccess);
    String str3 = utilities.getCrypt(stringBuilder.toString());
    manageViewElements(0);
    this.dbFun.setLastUser(str1, "BR", "pt");
    GlobalMembers.strUser = str1;
    GlobalMembers.strPassword = str2;
    GlobalMembers.bRespuestaNoRecibida = false;
    this.loginTimer = (new CountDownTimer(40000L, 1000L) {
        final LoginActivity this$0;
        
        public void onFinish() {
          LoginActivity loginActivity = LoginActivity.this;
          loginActivity.timeexpiderloginaction = true;
          loginActivity.onLoginFailConection(GlobalMembers.strUrl2);
          LoginActivity.this.onLoginFailConection(GlobalMembers.strUrl1);
          if (LoginActivity.this.wcfLogin1 != null && !LoginActivity.this.wcfLogin1.isCancelled())
            LoginActivity.this.wcfLogin1.cancel(true); 
          if (LoginActivity.this.wcfLogin2 != null && !LoginActivity.this.wcfLogin2.isCancelled())
            LoginActivity.this.wcfLogin2.cancel(true); 
        }
        
        public void onTick(long param1Long) {}
      }).start();
    String str4 = GlobalMembers.strUrl1;
    str2 = GlobalMembers.strUrl2;
    Utilities.escribeArchivo("PMMLogin", "GlobalMembers.strUrl1", GlobalMembers.strUrl1);
    this.wcfLogin1 = new ExecuteWS((Context)this);
    this.wcfLogin1.setOnLoginListener(this);
    this.wcfLogin1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.strUUID, str1, str3, strAppID, str4, String.valueOf(2131623963), GetUuiData(), GlobalMembers.nameKeystoreServiceWS });
    this.wcfLogin2 = new ExecuteWS((Context)this);
    this.wcfLogin2.setOnLoginListener(this);
    this.wcfLogin2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.strUUID, str1, str3, strAppID, str2, String.valueOf(2131623950), GetUuiData(), GlobalMembers.nameKeystoreService });
  }
  
  public boolean getpermissions() {
    boolean bool;
    if ((new PermissionsManagerMarshmallow()).checkPermissions(this, 100)) {
      context = (Context)this;
      this.strUUID = Utilities.DeviceUuidFactory(context);
      String str = this.strUUID;
      if (str == null || str.length() == 0 || this.strUUID.equals("0"))
        this.strUUID = ((WifiManager)getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress().replace(":", ""); 
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SuppressLint({"NewApi"})
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    context = (Context)this;
    this.stringsResourcesVO = new StringsResourcesVO();
    Intent intent = getIntent();
    boolean bool2 = intent.getBooleanExtra("EXIT_PERMISSIONS", false);
    boolean bool1 = intent.getBooleanExtra("EXIT", false);
    String str = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_lbl_faltanpermisos_1, 2131689922);
    if (bool2) {
      Toast.makeText((Context)this, str, 1).show();
      finish();
    } 
    if (bool1) {
      SocketListener.SocketListenerCloses();
      stopService(new Intent((Context)this, ServiceAlertsSocket.class));
    } 
    GlobalMembers.contexGlobal = context;
    setContentView(2131427365);
    initControls();
    if ((getIntent().getFlags() & 0x400000) != 0) {
      finish();
      return;
    } 
    this.userPreference = new UserPreferenceVO();
    rtApp = (onstarApplication)getApplicationContext();
    this.dbFun = new DBFunctions(getApplicationContext());
    getWindow().setSoftInputMode(2);
    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder()).permitAll().build());
    (new IntentFilter()).addAction("com.roadtrack.onstar.sockets.NEW_SOCKET_RESPONSE_OBTAINED_EVENT");
    PreferenceRT.SetValuePreference(Enums.SettingsPreference.statusNotifications, Boolean.valueOf(false), GlobalMembers.contexGlobal);
    GlobalMembers.lastGetLastIncomingMessageFindMe = null;
    if (getIntent().getExtras() != null) {
      bool1 = getIntent().getExtras().getBoolean("App_Close", false);
    } else {
      bool1 = false;
    } 
    if (bool1) {
      Bundle bundle = getIntent().getExtras();
      Intent intent1 = new Intent(context, GenericDialog.class);
      intent1.putExtras(bundle);
      startActivity(intent1);
    } 
    this.dbFun.IsShowDialogBefore((Context)this);
    if (GlobalMembers.URL_CreateAccount.isEmpty())
      this.tvCreateAccount.setVisibility(4); 
    str = GlobalMembers.URL_ChangeEmail;
    if (str != null && !str.isEmpty()) {
      this.tvAlterarEmail.setVisibility(0);
    } else {
      this.tvAlterarEmail.setVisibility(8);
    } 
  }
  
  @SuppressLint({"NewApi"})
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    FragmentTransaction fragmentTransaction;
    if (paramInt == 4) {
      FragmentManager fragmentManager = getFragmentManager();
      fragmentTransaction = fragmentManager.beginTransaction();
      Fragment fragment = fragmentManager.findFragmentByTag("exitLogin");
      if (fragment != null)
        fragmentTransaction.remove(fragment); 
      fragmentTransaction.addToBackStack(null);
      this.loginExit.show(fragmentTransaction, "exitLogin");
      return true;
    } 
    return super.onKeyDown(paramInt, (KeyEvent)fragmentTransaction);
  }
  
  public void onLoginFailAccountOrPass(String paramString1, String paramString2) {
    this.loginTimer.cancel();
    if (paramString1.equals(GlobalMembers.strUrl1)) {
      this.respUrlService1 = "FailAccountOrPass";
    } else if (paramString1.equals(GlobalMembers.strUrl2)) {
      this.respUrlService2 = "FailAccountOrPass";
    } 
    if ((this.respUrlService1.equals("FailAccountOrPass") && this.respUrlService2.equals("FailAccountOrPass")) || (this.respUrlService1.equals("FailAccountOrPass") && this.respUrlService2.equals("ServiceUnavailable")) || (this.respUrlService1.equals("ServiceUnavailable") && this.respUrlService2.equals("FailAccountOrPass")) || (this.respUrlService1.equals("FailAccountOrPass") && this.respUrlService2.equals("LoginFailConection")) || (this.respUrlService1.equals("LoginFailConection") && this.respUrlService2.equals("FailAccountOrPass"))) {
      loginFailureMessage2(paramString2);
      manageViewElements(6);
      stopService(new Intent((Context)this, ServicioSockets.class));
      this.respUrlService1 = "";
      this.respUrlService2 = "";
    } 
  }
  
  public void onLoginFailConection(String paramString) {
    if (paramString.equals(GlobalMembers.strUrl1)) {
      this.respUrlService1 = "LoginFailConection";
    } else if (paramString.equals(GlobalMembers.strUrl2)) {
      this.respUrlService2 = "LoginFailConection";
    } 
    if (this.respUrlService1.equals("LoginFailConection") && this.respUrlService2.equals("LoginFailConection")) {
      this.progressBar.setVisibility(4);
      this.access.setClickable(true);
      loginFailureMessage();
      this.respUrlService1 = "";
      this.respUrlService2 = "";
    } 
  }
  
  public void onLoginOK(String paramString1, String paramString2, String paramString3) {
    this.loginTimer.cancel();
    paramString1 = GlobalMembers.versionStatus;
    if (paramString1 != null && paramString1.equals("2")) {
      DialogVersion();
      return;
    } 
    if ((paramString2.contains("|") | paramString3.contains("|")) != 0) {
      StringBuilder stringBuilder1;
      String[] arrayOfString = paramString2.split("\\|");
      TimeZone timeZone = TimeZone.getDefault();
      int i = timeZone.getOffset(GregorianCalendar.getInstance(timeZone).getTimeInMillis());
      int j = Math.abs(i / 3600000);
      boolean bool = false;
      paramString2 = String.format("%02d", new Object[] { Integer.valueOf(j) });
      StringBuilder stringBuilder2 = new StringBuilder();
      if (i >= 0) {
        str1 = "+";
      } else {
        str1 = "-";
      } 
      stringBuilder2.append(str1);
      stringBuilder2.append(paramString2);
      this.OfsetTime = stringBuilder2.toString();
      this.userPreference.setUser(this.user.getText().toString());
      this.userPreference.setTimeOfSet(this.OfsetTime);
      if (this.dbFun.existUser(this.user.getText().toString()).booleanValue())
        this.dbFun.updateGTM(this.userPreference); 
      if (arrayOfString.length == 1) {
        str1 = arrayOfString[0].substring(arrayOfString[0].indexOf(";") + 1);
      } else {
        str1 = arrayOfString[1];
      } 
      String str2 = str1.trim();
      String str1 = arrayOfString[0].trim();
      String str3 = str1.substring(str1.indexOf(";") + 1);
      paramString2 = paramString3;
      if (!str3.equals(paramString3.split("\\|")[0])) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str3);
        stringBuilder.append("|");
        stringBuilder.append(paramString3);
        String[] arrayOfString1 = stringBuilder.toString().split("\\|");
        Vector<String> vector = new Vector();
        for (i = 0; i < arrayOfString1.length; i++)
          vector.add(arrayOfString1[i]); 
        vector.remove(16);
        String str = "";
        i = bool;
        while (true) {
          paramString2 = str;
          if (i < vector.size()) {
            if (i == vector.size() - 1) {
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(str);
              stringBuilder1.append(vector.get(i));
              str = stringBuilder1.toString();
            } else {
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(str);
              stringBuilder1.append(vector.get(i));
              stringBuilder1.append("|");
              str = stringBuilder1.toString();
            } 
            i++;
            continue;
          } 
          break;
        } 
      } 
      strVehicleList = (String)stringBuilder1;
      try {
        i = Integer.parseInt(str3);
        if (i == 1) {
          str1 = arrayOfString[2];
          String str5 = arrayOfString[3];
          if (!str2.equals(""))
            rtApp.setSessionKey(str2); 
          if (!str1.equals(""))
            rtApp.setLocatorUserId(str1); 
          if (!str5.equals(""))
            rtApp.setAccountID(str5); 
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(GlobalMembers.strUser);
          stringBuilder.append(",");
          stringBuilder.append(GlobalMembers.strPassword);
          String[] arrayOfString1 = stringBuilder.toString().split(",");
          rtApp.setUserAccessData(arrayOfString1);
          WsAccess wsAccess = new WsAccess();
          this((Context)this);
          FollowMeHandler.sharedPreferenceInBackGround(GlobalMembers.contexGlobal, str2, GlobalMembers.strUser, GlobalMembers.strPassword);
          wsAccess.GetUserDeviceShortG2_WS();
          String str4 = str2;
        } else {
          str1 = String.valueOf(i);
        } 
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error: onLoginOK: ", exception.toString());
        str1 = "-1";
      } 
      afterStartSession(str1);
      try {
        PreferenceRT.SetValuePreference("com.roadtrack.push.notification.status", Integer.valueOf(arrayOfString[4]).intValue(), (Context)this);
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error: onLoginOK: ", exception.toString());
      } 
    } 
    unlockChangeOrientation();
    this.respUrlService1 = "";
    this.respUrlService2 = "";
  }
  
  public void onLoginServiceDown(String paramString1, String paramString2) {
    this.loginTimer.cancel();
    if (paramString1.equals(GlobalMembers.strUrl1)) {
      this.respUrlService1 = "ServiceDown";
    } else if (paramString1.equals(GlobalMembers.strUrl2)) {
      this.respUrlService2 = "ServiceDown";
    } 
    if ((this.respUrlService1.equals("ServiceDown") && this.respUrlService2.equals("ServiceDown")) || (this.respUrlService1.equals("ServiceDown") && this.respUrlService2.equals("ServiceUnavailable")) || (this.respUrlService1.equals("ServiceUnavailable") && this.respUrlService2.equals("ServiceDown")) || (this.respUrlService1.equals("ServiceDown") && this.respUrlService2.equals("LoginFailConection")) || (this.respUrlService1.equals("LoginFailConection") && this.respUrlService2.equals("ServiceDown"))) {
      loginFailureServiceDown(paramString2);
      manageViewElements(6);
      stopService(new Intent((Context)this, ServicioSockets.class));
      this.respUrlService1 = "";
      this.respUrlService2 = "";
    } 
  }
  
  public void onLoginServiceUnavailable(String paramString) {
    if (paramString.equals(GlobalMembers.strUrl1)) {
      this.respUrlService1 = "ServiceUnavailable";
    } else if (paramString.equals(GlobalMembers.strUrl2)) {
      this.respUrlService2 = "ServiceUnavailable";
    } 
    if ((this.respUrlService1.equals("ServiceUnavailable") && this.respUrlService2.equals("ServiceUnavailable")) || (this.respUrlService1.equals("LoginFailConection") && this.respUrlService2.equals("ServiceUnavailable")) || (this.respUrlService1.equals("ServiceUnavailable") && this.respUrlService2.equals("LoginFailConection"))) {
      paramString = getResources().getString(2131690008);
      this.errorLogin.setText(paramString);
      this.progressBar.setVisibility(4);
      this.access.setClickable(true);
      stopService(new Intent((Context)this, ServicioSockets.class));
      this.respUrlService1 = "";
      this.respUrlService2 = "";
    } 
    unlockChangeOrientation();
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle) {
    super.onRestoreInstanceState(paramBundle);
    this.user.setText(paramBundle.getString(this.U_REMEMBERED));
    this.txtPassword.setText(paramBundle.getString(this.P_REMEMBERED));
    this.chkRememberPassword.setChecked(paramBundle.getBoolean(this.SWITCH_REMEMBERED));
  }
  
  @SuppressLint({"NewApi", "CommitTransaction"})
  protected void onResume() {
    super.onResume();
    manageViewElements(5);
    enableListeners();
    manageViewElements(2);
    manageViewElements(4);
    getSavedUser();
    onstarApplication onstarApplication1 = rtApp;
    this.tf_LouisRegular = onstarApplication.getTypeface(context, onstarApplication1.fontPathLouisRegular);
    this.chkRememberPassword.setTypeface(this.tf_LouisRegular);
    this.chkRememberPassword.setTextColor(getResources().getColor(2131034213));
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_si_1, 2131689952);
    this.chkRememberPassword.setTextOff(str1);
    this.chkRememberPassword.setTextOn(str2);
    this.access.setTypeface(this.tf_LouisRegular);
    this.errorLogin.setTypeface(this.tf_LouisRegular);
    myDeviceIsRootEmulator();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    setLastUserForOrientationChanged();
    paramBundle.putString(this.U_REMEMBERED, this.user.getText().toString());
    paramBundle.putString(this.P_REMEMBERED, this.txtPassword.getText().toString());
    paramBundle.putBoolean(this.SWITCH_REMEMBERED, this.chkRememberPassword.isChecked());
  }
  
  protected void onStart() {
    super.onStart();
    getpermissions();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/login/LoginActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */