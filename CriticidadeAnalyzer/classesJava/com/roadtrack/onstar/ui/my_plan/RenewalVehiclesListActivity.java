package com.roadtrack.onstar.ui.my_plan;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.PaymentCardResponse;
import com.roadtrack.onstar.VO.RenewalPlansListResponseO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import com.roadtrack.onstar.adapter.DialogMenu;
import com.roadtrack.onstar.adapter.RenewalVehiclesAdapter;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.RenewalPlans_Interface;
import com.roadtrack.onstar.async_tasks.tasks.GetPaymentProcessTask;
import com.roadtrack.onstar.async_tasks.tasks.GetRenewalPlansTask;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.utils.Utilities;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RenewalVehiclesListActivity extends FragmentActivity implements DialogMenu.DialogMenuListener, AdapterView.OnItemClickListener {
  public static final String TAG = RenewalVehiclesListActivity.class.getName();
  
  DBFunctions dbFun;
  
  DialogMenu dialogMenu;
  
  private ImageView imgv_header_image;
  
  private TextView lbl_arv_activity_title;
  
  ListView lv_arv_vehicles_list;
  
  RenewalVehiclesAdapter renewalVehiclesAdapter;
  
  onstarApplication rtApp;
  
  StringsResourcesVO stringsResourcesVO;
  
  private UserPreferenceVO userPreference;
  
  LinkedList<VehicleCatalogVO> vehicleCatalogVOS = new LinkedList<VehicleCatalogVO>();
  
  private void changeSelector(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    boolean bool = false;
    byte b1;
    for (b1 = 0; b1 < this.rtApp.getmDeviceUserList().size(); b1++)
      arrayList.add(((UserDevicesVO)this.rtApp.getmDeviceUserList().get(b1)).getName()); 
    byte b2 = 0;
    for (b1 = bool; b1 < arrayList.size(); b1++) {
      if (((String)arrayList.get(b1)).equals(((VehicleCatalogVO)this.vehicleCatalogVOS.get(paramInt)).getVehicleName()))
        b2 = b1; 
    } 
    UserDevicesVO userDevicesVO2 = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    UserDevicesVO userDevicesVO1 = this.rtApp.getmDeviceUserList().get(b2);
    this.dbFun.addVehicleSelected(getApplicationContext(), this.userPreference.getUser(), userDevicesVO1);
    if (!userDevicesVO1.equals(userDevicesVO2))
      Utilities.updateVehicleSelected(getApplicationContext(), this.userPreference.getUser(), userDevicesVO1); 
  }
  
  private void fillList(List<VehicleCatalogVO> paramList1, List<VehicleCatalogVO> paramList2, List<VehicleCatalogVO> paramList3) {
    if (paramList1 != null && paramList1.size() > 1)
      Collections.sort(paramList1, new Comparator<VehicleCatalogVO>(this) {
            public int compare(VehicleCatalogVO param1VehicleCatalogVO1, VehicleCatalogVO param1VehicleCatalogVO2) {
              Date date1 = Utilities.DateStringToDate(param1VehicleCatalogVO1.getDateExpire());
              Date date2 = Utilities.DateStringToDate(param1VehicleCatalogVO2.getDateExpire());
              return (date1 == null && date2 == null) ? 0 : ((date1 == null) ? -1 : ((date2 == null) ? 1 : date1.compareTo(date2)));
            }
          }); 
    if (paramList2 != null && paramList2.size() > 1)
      Collections.sort(paramList2, new Comparator<VehicleCatalogVO>(this) {
            public int compare(VehicleCatalogVO param1VehicleCatalogVO1, VehicleCatalogVO param1VehicleCatalogVO2) {
              Date date1 = Utilities.DateStringToDate(param1VehicleCatalogVO1.getDateExpire());
              Date date2 = Utilities.DateStringToDate(param1VehicleCatalogVO2.getDateExpire());
              return (date1 == null && date2 == null) ? 0 : ((date1 == null) ? -1 : ((date2 == null) ? 1 : date1.compareTo(date2)));
            }
          }); 
    if (paramList2 != null && paramList2.size() > 0)
      this.vehicleCatalogVOS.addAll(paramList2); 
    if (paramList1 != null && paramList1.size() > 0)
      this.vehicleCatalogVOS.addAll(paramList1); 
    if (paramList3 != null && paramList3.size() > 0)
      this.vehicleCatalogVOS.addAll(paramList3); 
  }
  
  private void noRepeatSameActionDialog() {
    try {
      String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
      String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
      String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
      this.dialogMenu = DialogMenu.newInstance();
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, 2131165484);
      this.dialogMenu.setPreferenceDialogFragment(this, (Context)this, true, drawable, str3, str2, true, str1, false, "0");
      this.dialogMenu.show(getSupportFragmentManager(), null);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: noRepeatSameActionDIalog", exception.getMessage());
    } 
  }
  
  private void paymentProcessFunction() {
    (new GetPaymentProcessTask((Context)this, new Base_Interface() {
          final RenewalVehiclesListActivity this$0;
          
          public void onFail(String param1String) {}
          
          public void onSuccess(String param1String) {
            try {
              if (param1String.contains("https")) {
                PaymentCardResponse paymentCardResponse = new PaymentCardResponse();
                this("", param1String, "");
                Intent intent = new Intent();
                this(PaymentCardInfo.class);
                Bundle bundle = new Bundle();
                this();
                bundle.putSerializable("resultObject", (Serializable)paymentCardResponse);
                intent.putExtras(bundle);
                RenewalVehiclesListActivity.this.startActivity(intent);
              } else {
                param1String = Utilities.getStringFromConfigList((Context)RenewalVehiclesListActivity.this, RenewalVehiclesListActivity.this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText((Context)RenewalVehiclesListActivity.this, param1String, 1).show();
              } 
            } catch (MalformedURLException malformedURLException) {
              Utilities.escribeArchivo(RenewalVehiclesListActivity.TAG, "paymentCardInfoFunction", malformedURLException.getMessage());
              RenewalVehiclesListActivity renewalVehiclesListActivity = RenewalVehiclesListActivity.this;
              String str = Utilities.getStringFromConfigList((Context)renewalVehiclesListActivity, renewalVehiclesListActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)RenewalVehiclesListActivity.this, str, 1).show();
            } 
          }
        }null)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void renewalPlanFunction(int paramInt) {
    GlobalMembers.isActionRenewalFromMain = false;
    (new GetRenewalPlansTask((Context)this, new RenewalPlans_Interface() {
          final RenewalVehiclesListActivity this$0;
          
          public void processFinish(RenewalPlansListResponseO param1RenewalPlansListResponseO) {
            if (param1RenewalPlansListResponseO != null) {
              if (param1RenewalPlansListResponseO.getCpres5() != null && param1RenewalPlansListResponseO.getCpres5().size() > 0) {
                Intent intent = new Intent((Context)RenewalVehiclesListActivity.this, OriginalRenewalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("service_response", (Serializable)param1RenewalPlansListResponseO);
                bundle.putString("previous_class", MainActivity.class.getSimpleName());
                intent.putExtras(bundle);
                RenewalVehiclesListActivity.this.startActivity(intent);
              } else {
                RenewalVehiclesListActivity renewalVehiclesListActivity = RenewalVehiclesListActivity.this;
                String str = Utilities.getStringFromConfigList((Context)renewalVehiclesListActivity, renewalVehiclesListActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText((Context)RenewalVehiclesListActivity.this, str, 1).show();
              } 
            } else {
              RenewalVehiclesListActivity renewalVehiclesListActivity = RenewalVehiclesListActivity.this;
              String str = Utilities.getStringFromConfigList((Context)renewalVehiclesListActivity, renewalVehiclesListActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)RenewalVehiclesListActivity.this, str, 1).show();
            } 
          }
        })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public void acceptButtonListener(String paramString) {
    this.dialogMenu.dismiss();
  }
  
  public void cancelButtonListener(String paramString) {
    this.dialogMenu.dismiss();
  }
  
  public void onBackMenuListener(String paramString) {
    this.dialogMenu.dismiss();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    setContentView(2131427380);
    MainActivity.onRenewalDialog = Boolean.valueOf(false);
    this.rtApp = (onstarApplication)getApplicationContext();
    this.dbFun = new DBFunctions(getApplicationContext());
    try {
      this.userPreference = this.dbFun.getUserPreference(GlobalMembers.userLogged);
    } catch (Exception exception) {
      startActivity(new Intent((Context)this, LoginActivity.class));
    } 
    this.imgv_header_image = (ImageView)findViewById(2131296639);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    this.imgv_header_image.setImageDrawable(drawable);
    this.stringsResourcesVO = new StringsResourcesVO();
    this.lbl_arv_activity_title = (TextView)findViewById(2131296729);
    String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_popup_lbl_renovaciones, 2131690337);
    this.lbl_arv_activity_title.setText(str);
    Bundle bundle = getIntent().getExtras();
    if (bundle != null)
      try {
        fillList((List<VehicleCatalogVO>)bundle.getSerializable("almostExpired"), (List<VehicleCatalogVO>)bundle.getSerializable("expired"), (List<VehicleCatalogVO>)bundle.getSerializable("normal"));
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      }  
    this.lv_arv_vehicles_list = (ListView)findViewById(2131296866);
    this.renewalVehiclesAdapter = new RenewalVehiclesAdapter(this.vehicleCatalogVOS, (Context)this);
    this.lv_arv_vehicles_list.setAdapter((ListAdapter)this.renewalVehiclesAdapter);
    this.lv_arv_vehicles_list.setOnItemClickListener(this);
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    boolean bool;
    boolean bool1 = this.dbFun.userDataTableHandler(this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId(), "", true);
    if (MainActivity.onOpenDoors.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onFindMe.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onPID.booleanValue() || GlobalMembers.onfollowmeActivated || MainActivity.onDTC.booleanValue()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool1) {
      changeSelector(paramInt);
      finish();
    } else {
      if (bool) {
        noRepeatSameActionDialog();
        return;
      } 
      changeSelector(paramInt);
      if (!GlobalMembers.flagShowWebViews) {
        GlobalMembers.isActionRenewalFromMain = true;
        renewalPlanFunction(paramInt);
      } else {
        paymentProcessFunction();
      } 
    } 
  }
  
  protected void onStart() {
    super.onStart();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/my_plan/RenewalVehiclesListActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */