package com.roadtrack.onstar.ui.my_plan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.PaymentHistoryResponseO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.adapter.PaymentHistoryAdapter;
import com.roadtrack.onstar.adapter.VehiculeSpinnerAdapter;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.async_tasks.tasks.GetPaymentsHistoryTask;
import com.roadtrack.onstar.gson.GsonC;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryActivity extends Activity {
  public final String TAG = PaymentHistoryActivity.class.getSimpleName();
  
  private Button btnOK;
  
  private Button buttonOk;
  
  DBFunctions dbFun;
  
  private FrameLayout frl_payment_history_list;
  
  private FrameLayout frl_payment_history_no_info;
  
  private TextView lbl_aph_date_title;
  
  private TextView lbl_aph_description_title;
  
  private TextView lbl_aph_monthly_title;
  
  private TextView lbl_aph_status_title;
  
  private TextView lbl_sinInfo;
  
  private ListView lv_aph_payments;
  
  private boolean onChangeSpinnerVehicle = false;
  
  private PaymentHistoryAdapter paymentHistoryAdapter;
  
  private List<PaymentHistoryResponseO> paymentsHistory;
  
  onstarApplication rtApp;
  
  private Spinner spinner_menu;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private UserPreferenceVO userPreference;
  
  private void cleanView() {
    this.paymentsHistory = new ArrayList<PaymentHistoryResponseO>();
    this.paymentHistoryAdapter = new PaymentHistoryAdapter(getApplicationContext(), this.paymentsHistory);
    this.lv_aph_payments.setAdapter((ListAdapter)this.paymentHistoryAdapter);
  }
  
  private List<PaymentHistoryResponseO> decryptResponse(List<PaymentHistoryResponseO> paramList) {
    if (paramList != null && paramList.size() > 0)
      for (byte b = 0; b < paramList.size(); b++) {
        ((PaymentHistoryResponseO)paramList.get(b)).setChpres1(Utilities.DecryptMoip(((PaymentHistoryResponseO)paramList.get(b)).getChpres1()));
        ((PaymentHistoryResponseO)paramList.get(b)).setChpres2(Utilities.DecryptMoip(((PaymentHistoryResponseO)paramList.get(b)).getChpres2()));
        ((PaymentHistoryResponseO)paramList.get(b)).setChpres3(Utilities.decodeStringUTF(Utilities.DecryptMoip(((PaymentHistoryResponseO)paramList.get(b)).getChpres3())));
        ((PaymentHistoryResponseO)paramList.get(b)).setChpres4(Utilities.DecryptMoip(((PaymentHistoryResponseO)paramList.get(b)).getChpres4()));
        ((PaymentHistoryResponseO)paramList.get(b)).setChpres5(Utilities.decodeStringUTF(Utilities.DecryptMoip(((PaymentHistoryResponseO)paramList.get(b)).getChpres5())));
        ((PaymentHistoryResponseO)paramList.get(b)).setChpres6(Utilities.DecryptMoip(((PaymentHistoryResponseO)paramList.get(b)).getChpres6()));
        String str = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" chpres1 = ");
        stringBuilder.append(((PaymentHistoryResponseO)paramList.get(b)).getChpres1());
        stringBuilder.append(" chpres2 = ");
        stringBuilder.append(((PaymentHistoryResponseO)paramList.get(b)).getChpres2());
        stringBuilder.append(" chpres3 = ");
        stringBuilder.append(((PaymentHistoryResponseO)paramList.get(b)).getChpres3());
        stringBuilder.append(" chpres4 = ");
        stringBuilder.append(((PaymentHistoryResponseO)paramList.get(b)).getChpres4());
        stringBuilder.append(" chpres5 = ");
        stringBuilder.append(((PaymentHistoryResponseO)paramList.get(b)).getChpres5());
        stringBuilder.append(" chpres6 = ");
        stringBuilder.append(((PaymentHistoryResponseO)paramList.get(b)).getChpres6());
        Utilities.escribeArchivo(str, "PaymentHistoryList", stringBuilder.toString());
      }  
    return paramList;
  }
  
  private void fillInformation(String paramString) {
    if (paramString != null && !paramString.isEmpty()) {
      String str = paramString.replace("\\\"", "\"");
      List<PaymentHistoryResponseO> list = (new GsonC()).toListObject(str, PaymentHistoryResponseO.class);
      decryptResponse(list);
      if (isValidList(list)) {
        this.frl_payment_history_no_info.setVisibility(8);
        this.frl_payment_history_list.setVisibility(0);
        this.paymentHistoryAdapter = new PaymentHistoryAdapter(getApplicationContext(), this.paymentsHistory);
        this.lv_aph_payments.setAdapter((ListAdapter)this.paymentHistoryAdapter);
      } else {
        if (paramString.equals("[]") || this.paymentsHistory.size() < 1) {
          this.frl_payment_history_list.setVisibility(8);
          this.frl_payment_history_no_info.setVisibility(0);
          return;
        } 
        if (list == null)
          onResponseError(); 
      } 
    } 
  }
  
  private void fillVehicleList(Spinner paramSpinner, Context paramContext) {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/ui/my_plan/PaymentHistoryActivity}} */
    Spinner spinner = paramSpinner;
    if (paramSpinner == null)
      try {
        spinner = this.spinner_menu;
      } catch (Exception exception) {
      
      } finally {} 
    if (spinner == null) {
      /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/ui/my_plan/PaymentHistoryActivity}} */
      return;
    } 
    ArrayList<String> arrayList = new ArrayList();
    this();
    for (byte b = 0; b < this.rtApp.getmDeviceUserList().size(); b++)
      arrayList.add(((UserDevicesVO)this.rtApp.getmDeviceUserList().get(b)).getName()); 
    if (arrayList.size() > 0) {
      VehiculeSpinnerAdapter vehiculeSpinnerAdapter = new VehiculeSpinnerAdapter();
      this(paramContext, 2131427512, 2131297225, 2131297226, this.spinner_menu, arrayList);
      spinner.setAdapter((SpinnerAdapter)vehiculeSpinnerAdapter);
      spinner.setSelection(Utilities.getLastKnownVehicleSelected(getApplicationContext(), this.userPreference.getUser(), this.rtApp));
    } 
    Utilities.setDeviceType(this.rtApp);
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        final PaymentHistoryActivity this$0;
        
        @SuppressLint({"NewApi"})
        public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
          PaymentHistoryActivity paymentHistoryActivity1 = PaymentHistoryActivity.this;
          UserDevicesVO userDevicesVO2 = Utilities.getLastKnownDeviceSelected(paymentHistoryActivity1.rtApp, paymentHistoryActivity1.TAG);
          UserDevicesVO userDevicesVO1 = PaymentHistoryActivity.this.rtApp.getmDeviceUserList().get(param1Int);
          PaymentHistoryActivity paymentHistoryActivity2 = PaymentHistoryActivity.this;
          paymentHistoryActivity2.dbFun.addVehicleSelected(paymentHistoryActivity2.getApplicationContext(), PaymentHistoryActivity.this.userPreference.getUser(), userDevicesVO1);
          if (!userDevicesVO1.equals(userDevicesVO2)) {
            PaymentHistoryActivity.access$102(PaymentHistoryActivity.this, true);
            Utilities.updateVehicleSelected(PaymentHistoryActivity.this.getApplicationContext(), PaymentHistoryActivity.this.userPreference.getUser(), userDevicesVO1);
            PaymentHistoryActivity paymentHistoryActivity = PaymentHistoryActivity.this;
            if (paymentHistoryActivity.dbFun.userDataTableHandler(paymentHistoryActivity.rtApp.getAccountID().toString(), userDevicesVO1.getDeviceId(), "", true) || Utilities.isUUx(PaymentHistoryActivity.this.rtApp)) {
              Intent intent = new Intent(PaymentHistoryActivity.this.getApplicationContext(), MainActivity.class);
              MainActivity.Showbanner = true;
              PaymentHistoryActivity.this.startActivity(intent);
            } else {
              PaymentHistoryActivity.this.cleanView();
              PaymentHistoryActivity paymentHistoryActivity3 = PaymentHistoryActivity.this;
              String str = paymentHistoryActivity3.dbFun.getCurentIDGMT(Utilities.getLastKnownDeviceSelected(paymentHistoryActivity3.rtApp, paymentHistoryActivity3.TAG).getDeviceId());
              (new GetPaymentsHistoryTask((Context)PaymentHistoryActivity.this, new AsyncResponse() {
                    final PaymentHistoryActivity.null this$1;
                    
                    public void processFinish(String param2String) {
                      if (param2String != null && !param2String.isEmpty()) {
                        PaymentHistoryActivity.this.fillInformation(param2String);
                      } else {
                        PaymentHistoryActivity.this.onResponseError();
                      } 
                    }
                  },  str)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
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
        final PaymentHistoryActivity this$0;
        
        public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
          boolean bool;
          if (MainActivity.onOpenDoors.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onFindMe.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onPID.booleanValue() || MainActivity.onDTC.booleanValue()) {
            bool = true;
          } else {
            bool = false;
          } 
          if (param1MotionEvent.getAction() == 1 && bool) {
            PaymentHistoryActivity paymentHistoryActivity1 = PaymentHistoryActivity.this;
            String str1 = Utilities.getStringFromConfigList((Context)paymentHistoryActivity1, paymentHistoryActivity1.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
            PaymentHistoryActivity paymentHistoryActivity2 = PaymentHistoryActivity.this;
            String str2 = Utilities.getStringFromConfigList((Context)paymentHistoryActivity2, paymentHistoryActivity2.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
            PaymentHistoryActivity paymentHistoryActivity3 = PaymentHistoryActivity.this;
            String str3 = Utilities.getStringFromConfigList((Context)paymentHistoryActivity3, paymentHistoryActivity3.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
            PaymentHistoryActivity paymentHistoryActivity4 = PaymentHistoryActivity.this;
            String str4 = Utilities.getStringFromConfigList((Context)paymentHistoryActivity4, paymentHistoryActivity4.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
            try {
              Dialog dialog = Utilities.simpleDialog((Context)PaymentHistoryActivity.this, null, str1, str2, true, str3, false, str4, 20.0F, 20.0F);
              PaymentHistoryActivity.access$602(PaymentHistoryActivity.this, (Button)dialog.findViewById(2131296343));
              Button button = PaymentHistoryActivity.this.buttonOk;
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
              Utilities.escribeArchivo(PaymentHistoryActivity.this.TAG, "Error: dialogBackALert", exception.getMessage());
            } 
          } 
          return bool;
        }
      };
    super(this);
    spinner.setOnTouchListener(onTouchListener);
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/ui/my_plan/PaymentHistoryActivity}} */
  }
  
  private void formattedFont() {
    Typeface typeface = onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisBold);
    this.lbl_aph_description_title.setTypeface(typeface);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_histodescripcion, 2131690291);
    this.lbl_aph_description_title.setText(str2);
    this.lbl_aph_monthly_title.setTypeface(typeface);
    str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_histovalormensual, 2131690295);
    this.lbl_aph_monthly_title.setText(str2);
    this.lbl_aph_date_title.setTypeface(typeface);
    str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_histotransaccion, 2131690294);
    this.lbl_aph_date_title.setText(str2);
    this.lbl_aph_status_title.setTypeface(typeface);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_histoestatus, 2131690292);
    this.lbl_aph_status_title.setText(str1);
    str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_sininformación, 2131689935);
    this.lbl_sinInfo.setText(str1);
  }
  
  private boolean isValidList(List<PaymentHistoryResponseO> paramList) {
    if (paramList != null && paramList.size() > 0) {
      for (PaymentHistoryResponseO paymentHistoryResponseO : paramList) {
        if (paymentHistoryResponseO.isValidResponse())
          this.paymentsHistory.add(paymentHistoryResponseO); 
      } 
      if (this.paymentsHistory.size() > 0)
        return true; 
    } 
    return false;
  }
  
  private void onResponseError() {
    if (this.onChangeSpinnerVehicle) {
      String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_conexiondered_1, 2131689912);
      String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
      final Dialog dialog = Utilities.simpleDialog((Context)this, null, str1, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862), true, str2, false, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_si_1, 2131689952), false);
      this.btnOK = (Button)dialog.findViewById(2131296343);
      this.btnOK.setOnClickListener(new View.OnClickListener() {
            final PaymentHistoryActivity this$0;
            
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              dialog.dismiss();
              Intent intent = new Intent(PaymentHistoryActivity.this.getApplicationContext(), MainActivity.class);
              MainActivity.Showbanner = true;
              PaymentHistoryActivity.this.startActivity(intent);
            }
          });
      this.onChangeSpinnerVehicle = false;
      dialog.show();
    } else {
      String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
      Toast.makeText(getApplicationContext(), str, 1).show();
      onBackPressed();
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
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    setContentView(2131427375);
    this.rtApp = (onstarApplication)getApplicationContext();
    this.dbFun = new DBFunctions(getApplicationContext());
    this.userPreference = this.dbFun.getUserPreference(GlobalMembers.userLogged);
    this.stringsResourcesVO = new StringsResourcesVO();
    this.spinner_menu = (Spinner)findViewById(2131297043);
    this.lbl_aph_description_title = (TextView)findViewById(2131296725);
    this.lbl_aph_monthly_title = (TextView)findViewById(2131296726);
    this.lbl_aph_date_title = (TextView)findViewById(2131296724);
    this.lbl_aph_status_title = (TextView)findViewById(2131296727);
    this.lv_aph_payments = (ListView)findViewById(2131296865);
    this.frl_payment_history_list = (FrameLayout)findViewById(2131296556);
    this.frl_payment_history_no_info = (FrameLayout)findViewById(2131296557);
    this.lbl_sinInfo = (TextView)findViewById(2131296784);
    fillVehicleList(this.spinner_menu, getApplicationContext());
    formattedFont();
    this.paymentsHistory = new ArrayList<PaymentHistoryResponseO>();
    try {
      fillInformation(getIntent().getExtras().getString("service_response"));
    } catch (Exception exception) {
      onResponseError();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/my_plan/PaymentHistoryActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */