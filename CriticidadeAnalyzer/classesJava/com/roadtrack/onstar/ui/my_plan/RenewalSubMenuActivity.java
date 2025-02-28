package com.roadtrack.onstar.ui.my_plan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.adapter.RenewalPlansAdapter;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.List;

public class RenewalSubMenuActivity extends Activity implements AdapterView.OnItemClickListener {
  static UserDevicesVO deviceInfo;
  
  public final String TAG = RenewalSubMenuActivity.class.getSimpleName();
  
  private DBFunctions dbFun;
  
  private TextView lbl_rasm_device_name;
  
  private TextView lbl_rasm_title;
  
  private ListView lv_rasm_plan;
  
  RenewalPlansAdapter renewalPlansAdapter;
  
  onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Typeface tfLouis;
  
  private void formattedFont() {
    this.tfLouis = onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisRegular);
    this.lbl_rasm_device_name.setTypeface(this.tfLouis);
    this.lbl_rasm_title.setTypeface(this.tfLouis);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    setContentView(2131427379);
    this.stringsResourcesVO = new StringsResourcesVO();
    this.rtApp = (onstarApplication)getApplicationContext();
    this.dbFun = new DBFunctions(getApplicationContext());
    GlobalMembers.ctxBase = this.rtApp.getBaseContext();
    this.dbFun.getUserPreference(GlobalMembers.userLogged);
    deviceInfo = Utilities.getLastKnownDeviceSelected(this.rtApp, this.TAG);
    this.lbl_rasm_device_name = (TextView)findViewById(2131296779);
    this.lbl_rasm_title = (TextView)findViewById(2131296780);
    String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_planes, 2131690309);
    this.lbl_rasm_title.setText(str);
    Button button = (Button)findViewById(2131296426);
    this.lv_rasm_plan = (ListView)findViewById(2131296870);
    this.lbl_rasm_device_name.setText(deviceInfo.getName());
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      List list = (List)bundle.getSerializable("items");
    } else {
      new ArrayList();
    } 
    this.lv_rasm_plan.setAdapter((ListAdapter)this.renewalPlansAdapter);
    this.lv_rasm_plan.setOnItemClickListener(this);
    formattedFont();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/my_plan/RenewalSubMenuActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */