package com.roadtrack.onstar.ui.my_plan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.RenewalPlanVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.adapter.VehiculeSpinnerAdapter;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RenewalActivity extends Activity implements View.OnClickListener {
  public final String TAG = RenewalActivity.class.getSimpleName();
  
  private Button btn_ra_other_plans;
  
  private Button btn_ra_plan1_renewal;
  
  private Button btn_ra_plan2_renewal;
  
  private Button btn_ra_renewal;
  
  private DBFunctions dbFun;
  
  private TextView lbl_ra_current_plan;
  
  private TextView lbl_ra_expiration_date;
  
  private TextView lbl_ra_plan1_description;
  
  private TextView lbl_ra_plan1_name;
  
  private TextView lbl_ra_plan1_price;
  
  private TextView lbl_ra_plan2_description;
  
  private TextView lbl_ra_plan2_name;
  
  private TextView lbl_ra_plan2_price;
  
  private TextView lbl_ra_price;
  
  private TextView lbl_title_renewal;
  
  private LinearLayout lin_ra_current_plan;
  
  private LinearLayout lin_ra_plan1;
  
  private LinearLayout lin_ra_plan2;
  
  onstarApplication rtApp;
  
  private Spinner spinner_menu;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Typeface tfLouis;
  
  private UserPreferenceVO userPreference;
  
  private List<RenewalPlanVO> fillList() {
    ArrayList<RenewalPlanVO> arrayList = new ArrayList();
    for (byte b = 0; b < 5; b++) {
      RenewalPlanVO renewalPlanVO = new RenewalPlanVO();
      renewalPlanVO.setPlanId("IdPlan1");
      renewalPlanVO.setPlanName("PlanName1");
      renewalPlanVO.setPlanDescription("PlanDescription1");
      renewalPlanVO.setSalePolitic("123P");
      renewalPlanVO.setHighPriority("1");
      renewalPlanVO.setPlanPrice("R$ 123");
      renewalPlanVO.setPlanMonthlyPrice("R$ 34");
      renewalPlanVO.setOrderView("1");
      renewalPlanVO.setSaleService("0");
      arrayList.add(renewalPlanVO);
    } 
    return arrayList;
  }
  
  private void fillVehicleList(Spinner paramSpinner, Context paramContext) {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/ui/my_plan/RenewalActivity}} */
    Spinner spinner = paramSpinner;
    if (paramSpinner == null)
      try {
        spinner = this.spinner_menu;
      } catch (Exception exception) {
      
      } finally {} 
    if (spinner == null) {
      /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/ui/my_plan/RenewalActivity}} */
      return;
    } 
    ArrayList<String> arrayList = new ArrayList();
    this();
    for (byte b = 0; b < this.rtApp.getmDeviceUserList().size(); b++)
      arrayList.add(((UserDevicesVO)this.rtApp.getmDeviceUserList().get(b)).getName()); 
    VehiculeSpinnerAdapter vehiculeSpinnerAdapter = new VehiculeSpinnerAdapter();
    this(paramContext, 2131427512, 2131297225, 2131297226, this.spinner_menu, arrayList);
    spinner.setAdapter((SpinnerAdapter)vehiculeSpinnerAdapter);
    spinner.setSelection(Utilities.getLastKnownVehicleSelected(getApplicationContext(), this.userPreference.getUser(), this.rtApp));
    Utilities.setDeviceType(this.rtApp);
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        final RenewalActivity this$0;
        
        @SuppressLint({"NewApi"})
        public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
          RenewalActivity renewalActivity = RenewalActivity.this;
          UserDevicesVO userDevicesVO2 = Utilities.getLastKnownDeviceSelected(renewalActivity.rtApp, renewalActivity.TAG);
          UserDevicesVO userDevicesVO1 = RenewalActivity.this.rtApp.getmDeviceUserList().get(param1Int);
          RenewalActivity.this.dbFun.addVehicleSelected(RenewalActivity.this.getApplicationContext(), RenewalActivity.this.userPreference.getUser(), userDevicesVO1);
          if (!userDevicesVO1.equals(userDevicesVO2)) {
            Utilities.updateVehicleSelected(RenewalActivity.this.getApplicationContext(), RenewalActivity.this.userPreference.getUser(), userDevicesVO1);
            if (Utilities.isUUx(RenewalActivity.this.rtApp)) {
              Intent intent = new Intent(RenewalActivity.this.getApplicationContext(), MainActivity.class);
              MainActivity.Showbanner = true;
              RenewalActivity.this.startActivity(intent);
            } 
          } 
        }
        
        public void onNothingSelected(AdapterView<?> param1AdapterView) {}
      };
    super(this);
    spinner.setOnItemSelectedListener(onItemSelectedListener);
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/ui/my_plan/RenewalActivity}} */
  }
  
  private void formattedFont() {
    this.tfLouis = onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisRegular);
    this.lbl_title_renewal.setTypeface(this.tfLouis);
    String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_planes, 2131690309);
    this.lbl_title_renewal.setText(str);
    this.lbl_ra_expiration_date.setTypeface(this.tfLouis);
    this.lbl_ra_current_plan.setTypeface(this.tfLouis);
    str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_planactual, 2131690308);
    this.lbl_ra_current_plan.setText(str);
    this.lbl_ra_price.setTypeface(this.tfLouis);
    this.lbl_ra_plan1_name.setTypeface(this.tfLouis);
    this.lbl_ra_plan1_description.setTypeface(this.tfLouis);
    this.lbl_ra_plan1_price.setTypeface(this.tfLouis);
    this.lbl_ra_plan2_name.setTypeface(this.tfLouis);
    this.lbl_ra_plan2_description.setTypeface(this.tfLouis);
    this.lbl_ra_plan2_price.setTypeface(this.tfLouis);
    this.btn_ra_renewal.setTypeface(this.tfLouis);
    str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_renovarplan, 2131690313);
    this.btn_ra_renewal.setText(str);
    this.btn_ra_plan1_renewal.setTypeface(this.tfLouis);
    this.btn_ra_plan1_renewal.setText(str);
    this.btn_ra_plan2_renewal.setTypeface(this.tfLouis);
    this.btn_ra_plan2_renewal.setText(str);
    this.btn_ra_other_plans.setTypeface(this.tfLouis);
  }
  
  private void testInfo() {
    String str2 = Utilities.getStringFromConfigList((Context)this, (new StringsResourcesVO()).renovacion_lbl_planactual, 2131690308);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str2);
    stringBuilder.append(": <b> Protection </b>");
    String str1 = stringBuilder.toString();
    this.lbl_ra_current_plan.setText((CharSequence)Html.fromHtml(str1));
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
  
  public void onClick(View paramView) {
    Bundle bundle2;
    Intent intent1;
    Intent intent3;
    Bundle bundle3;
    int i = paramView.getId();
    switch (i) {
      default:
        switch (i) {
          default:
            return;
          case 2131296811:
            startActivity(new Intent((Context)this, PaymentActivity.class));
          case 2131296813:
            intent3 = new Intent((Context)this, RenewalSubMenuActivity.class);
            bundle2 = new Bundle();
            bundle2.putSerializable("items", (Serializable)fillList());
            intent3.putExtras(bundle2);
            startActivity(intent3);
          case 2131296812:
            break;
        } 
      case 2131296425:
      case 2131296424:
      
      case 2131296423:
        intent1 = new Intent((Context)this, RenewalSubMenuActivity.class);
        bundle3 = new Bundle();
        bundle3.putSerializable("items", (Serializable)fillList());
        intent1.putExtras(bundle3);
        startActivity(intent1);
      case 2131296422:
        break;
    } 
    Intent intent2 = new Intent((Context)this, RenewalSubMenuActivity.class);
    Bundle bundle1 = new Bundle();
    bundle1.putSerializable("items", (Serializable)fillList());
    intent2.putExtras(bundle1);
    startActivity(intent2);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    setContentView(2131427377);
    this.stringsResourcesVO = new StringsResourcesVO();
    this.rtApp = (onstarApplication)getApplicationContext();
    this.dbFun = new DBFunctions(getApplicationContext());
    GlobalMembers.ctxBase = this.rtApp.getBaseContext();
    this.userPreference = this.dbFun.getUserPreference(GlobalMembers.userLogged);
    this.spinner_menu = (Spinner)findViewById(2131297043);
    fillVehicleList(this.spinner_menu, getApplicationContext());
    this.lbl_title_renewal = (TextView)findViewById(2131296787);
    this.lbl_ra_expiration_date = (TextView)findViewById(2131296771);
    this.lbl_ra_current_plan = (TextView)findViewById(2131296770);
    this.lbl_ra_price = (TextView)findViewById(2131296778);
    this.lbl_ra_plan1_name = (TextView)findViewById(2131296773);
    this.lbl_ra_plan1_description = (TextView)findViewById(2131296772);
    this.lbl_ra_plan1_price = (TextView)findViewById(2131296774);
    this.lbl_ra_plan2_name = (TextView)findViewById(2131296776);
    this.lbl_ra_plan2_description = (TextView)findViewById(2131296775);
    this.lbl_ra_plan2_price = (TextView)findViewById(2131296777);
    this.btn_ra_renewal = (Button)findViewById(2131296425);
    this.btn_ra_plan1_renewal = (Button)findViewById(2131296423);
    this.btn_ra_plan2_renewal = (Button)findViewById(2131296424);
    this.btn_ra_other_plans = (Button)findViewById(2131296422);
    this.lin_ra_plan1 = (LinearLayout)findViewById(2131296812);
    this.lin_ra_plan2 = (LinearLayout)findViewById(2131296813);
    LinearLayout linearLayout = (LinearLayout)findViewById(2131296810);
    this.lin_ra_current_plan = (LinearLayout)findViewById(2131296811);
    this.lin_ra_current_plan.setOnClickListener(this);
    this.btn_ra_renewal.setOnClickListener(this);
    this.lin_ra_plan1.setOnClickListener(this);
    this.btn_ra_plan1_renewal.setOnClickListener(this);
    this.lin_ra_plan2.setOnClickListener(this);
    this.btn_ra_plan2_renewal.setOnClickListener(this);
    this.btn_ra_other_plans.setOnClickListener(this);
    formattedFont();
    testInfo();
  }
  
  protected void onResume() {
    super.onResume();
    fillVehicleList(this.spinner_menu, getApplicationContext());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/my_plan/RenewalActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */