package com.roadtrack.onstar.ui.gmt;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.GMTCatalog;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.adapter.Gmt_Adapter;
import com.roadtrack.onstar.adapter.Gmt_Getters_Setters;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.Gmt_Interface;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.ui.settings.SettingsNewActivity;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.List;

public class GmtActivity extends Activity {
  private static String ALL_DEVICE;
  
  private static final String TAG = GmtActivity.class.getSimpleName();
  
  private Button Acept;
  
  private TextView ActionValueMessage;
  
  private Button Cancel;
  
  private Activity act;
  
  private Button btnOk2;
  
  private CheckBox checkbox;
  
  private String global_popup_btn_aceptar_1;
  
  private String global_popup_lbl_aviso_1;
  
  private TextView lbl_gmt_cancel;
  
  private Context mContext;
  
  private DBFunctions mDbFunctions;
  
  private EditText mEditTextSearch;
  
  private Gmt_Adapter mGmt_Adapter;
  
  private List<Gmt_Getters_Setters> mListGmt_Getters_Setters;
  
  private ListView mListViewGmt;
  
  private onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  static {
    ALL_DEVICE = "0";
  }
  
  private List<Gmt_Getters_Setters> getAllGmtCatalog() {
    ArrayList<Gmt_Getters_Setters> arrayList = new ArrayList();
    new ArrayList();
    for (GMTCatalog gMTCatalog : this.mDbFunctions.getAllGmtCatalog()) {
      Gmt_Getters_Setters gmt_Getters_Setters = new Gmt_Getters_Setters();
      gmt_Getters_Setters.setGmtId(gMTCatalog.getGmtId());
      gmt_Getters_Setters.setGmtLabel(gMTCatalog.getGmtPrefix());
      gmt_Getters_Setters.setGmtValue(Float.parseFloat(gMTCatalog.getGmtValue()));
      gmt_Getters_Setters.setGmtZone(gMTCatalog.getGmtName());
      arrayList.add(gmt_Getters_Setters);
    } 
    return arrayList;
  }
  
  private void showCancelButton(Boolean paramBoolean) {
    if (paramBoolean.booleanValue()) {
      if (this.lbl_gmt_cancel.getVisibility() != 0)
        this.lbl_gmt_cancel.setVisibility(0); 
      this.lbl_gmt_cancel.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -1, 1.0F));
      this.mEditTextSearch.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -1, 3.0F));
    } else {
      this.lbl_gmt_cancel.setVisibility(8);
      this.mEditTextSearch.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -1, 4.0F));
    } 
  }
  
  public void Dialog_network(Context paramContext) {
    String str = Utilities.getStringFromConfigList(this.mContext, this.stringsResourcesVO.login_global_popup_lbl_sindatos_2, 2131690012);
    final Dialog dialog2 = Utilities.simpleDialog(paramContext, null, this.global_popup_lbl_aviso_1, str, true, this.global_popup_btn_aceptar_1, false, "0");
    this.btnOk2 = (Button)dialog.findViewById(2131296343);
    this.btnOk2.setOnClickListener(new View.OnClickListener() {
          final GmtActivity this$0;
          
          final Dialog val$dialog2;
          
          public void onClick(View param1View) {
            dialog2.dismiss();
            GmtActivity.this.finish();
          }
        });
    dialog.show();
  }
  
  public void onBackPressed() {
    super.onBackPressed();
    startActivity(new Intent((Context)this, SettingsNewActivity.class));
    finish();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427364);
    getActionBar().hide();
    this.mContext = (Context)this;
    this.mDbFunctions = new DBFunctions(this.mContext);
    this.rtApp = (onstarApplication)getApplicationContext();
    this.act = this;
    this.stringsResourcesVO = new StringsResourcesVO();
    this.global_popup_btn_aceptar_1 = Utilities.getStringFromConfigList(this.mContext, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    this.global_popup_lbl_aviso_1 = Utilities.getStringFromConfigList(this.mContext, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    Utilities.getStringFromConfigList(this.mContext, this.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
    Utilities.getStringFromConfigList(this.mContext, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    String str1 = Utilities.getStringFromConfigList(this.mContext, this.stringsResourcesVO.global_lbl_configuracion_1, 2131689913);
    String str2 = Utilities.getStringFromConfigList(this.mContext, this.stringsResourcesVO.configuracion_gmt_lbl_ciudad, 2131689723);
    onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisRegular);
    this.lbl_gmt_cancel = (TextView)findViewById(2131296750);
    this.mEditTextSearch = (EditText)findViewById(2131296510);
    this.mEditTextSearch.setHint(str2);
    this.ActionValueMessage = (TextView)findViewById(2131296257);
    this.ActionValueMessage.setText(str1);
    this.mEditTextSearch.addTextChangedListener(new TextWatcher() {
          final GmtActivity this$0;
          
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            GmtActivity.this.mGmt_Adapter.getFilter().filter(param1CharSequence.toString());
            if (param1CharSequence.toString().equals("")) {
              GmtActivity.this.showCancelButton(Boolean.valueOf(false));
            } else {
              GmtActivity.this.showCancelButton(Boolean.valueOf(true));
            } 
          }
        });
    this.mListViewGmt = (ListView)findViewById(2131296831);
    this.mListGmt_Getters_Setters = getAllGmtCatalog();
    this.mGmt_Adapter = new Gmt_Adapter(this.mContext, this.mListGmt_Getters_Setters, new Gmt_Interface() {
          final GmtActivity this$0;
          
          public void onFilteredChange(List<Gmt_Getters_Setters> param1List) {
            GmtActivity.access$202(GmtActivity.this, param1List);
          }
        });
    this.lbl_gmt_cancel.setOnClickListener(new View.OnClickListener() {
          final GmtActivity this$0;
          
          public void onClick(View param1View) {
            GmtActivity.this.mEditTextSearch.setText("");
          }
        });
    this.mListViewGmt.setAdapter((ListAdapter)this.mGmt_Adapter);
    this.mListViewGmt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          final GmtActivity this$0;
          
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            String str2 = ((Gmt_Getters_Setters)GmtActivity.this.mListGmt_Getters_Setters.get(param1Int)).getGmtId();
            float f = ((Gmt_Getters_Setters)GmtActivity.this.mListGmt_Getters_Setters.get(param1Int)).getGmtValue();
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("");
            stringBuilder1.append(str2);
            String str1 = stringBuilder1.toString();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(Utilities.FormatHour(f));
            String str3 = stringBuilder2.toString();
            if (GmtActivity.this.mListGmt_Getters_Setters != null && GmtActivity.this.mListGmt_Getters_Setters.size() > 0)
              try {
                String str6 = Utilities.getStringFromConfigList(GmtActivity.this.mContext, GmtActivity.this.stringsResourcesVO.cambiogmt_lbl_confirmar, 2131689692);
                String str4 = Utilities.getStringFromConfigList(GmtActivity.this.mContext, GmtActivity.this.stringsResourcesVO.cambiogmt_lbl_confirmar2, 2131689693);
                str6 = str6.replace("%s", str3);
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append(str6);
                stringBuilder.append(" ");
                stringBuilder.append(str4);
                str6 = stringBuilder.toString().replace("%s", Utilities.getLastKnownDeviceSelected(GmtActivity.this.rtApp, GmtActivity.TAG).getName());
                str4 = Utilities.getStringFromConfigList(GmtActivity.this.mContext, GmtActivity.this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
                String str5 = Utilities.getStringFromConfigList(GmtActivity.this.mContext, GmtActivity.this.stringsResourcesVO.gmt_lbl_aplicaratodos, 2131689976);
                String str7 = Utilities.getStringFromConfigList(GmtActivity.this.mContext, GmtActivity.this.stringsResourcesVO.gmt_lbl_avisocambiohorario, 2131689977);
                Dialog dialog = Utilities.DialogGTM((Context)GmtActivity.this, null, GmtActivity.this.global_popup_lbl_aviso_1, str6, true, str5, str7, true, GmtActivity.this.global_popup_btn_aceptar_1, true, str4);
                GmtActivity.access$1002(GmtActivity.this, (Button)dialog.findViewById(2131296343));
                GmtActivity.access$1102(GmtActivity.this, (Button)dialog.findViewById(2131296344));
                GmtActivity.access$1202(GmtActivity.this, (CheckBox)dialog.findViewById(2131296461));
                Button button = GmtActivity.this.Acept;
                View.OnClickListener onClickListener2 = new View.OnClickListener() {
                    final GmtActivity.null this$1;
                    
                    final Dialog val$dialog;
                    
                    final String val$selectedGmtId;
                    
                    public void onClick(View param2View) {
                      dialog.dismiss();
                      if (!GmtActivity.this.checkbox.isChecked()) {
                        MainActivity.taskSet.setGtm_Task(GmtActivity.this.mContext, selectedGmtId, new Base_Interface() {
                              final GmtActivity.null.null this$2;
                              
                              public void onFail(String param3String) {
                                GmtActivity gmtActivity = GmtActivity.this;
                                gmtActivity.Dialog_network(gmtActivity.mContext);
                              }
                              
                              public void onSuccess(String param3String) {
                                Utilities.getLastKnownDeviceSelected(GmtActivity.this.rtApp, GmtActivity.TAG).getDeviceId();
                                GmtActivity gmtActivity = GmtActivity.this;
                                gmtActivity.startActivity(new Intent(gmtActivity.mContext, SettingsNewActivity.class));
                                GmtActivity.this.finish();
                              }
                            }null);
                      } else {
                        MainActivity.taskSet.setGtm_Task(GmtActivity.this.mContext, selectedGmtId, new Base_Interface() {
                              final GmtActivity.null.null this$2;
                              
                              public void onFail(String param3String) {
                                GmtActivity gmtActivity = GmtActivity.this;
                                gmtActivity.Dialog_network(gmtActivity.mContext);
                              }
                              
                              public void onSuccess(String param3String) {
                                for (byte b = 0; b < GmtActivity.this.rtApp.getmDeviceUserList().size(); b++)
                                  ((UserDevicesVO)GmtActivity.this.rtApp.getmDeviceUserList().get(b)).getDeviceId().toString(); 
                                GmtActivity gmtActivity = GmtActivity.this;
                                gmtActivity.startActivity(new Intent(gmtActivity.mContext, SettingsNewActivity.class));
                                GmtActivity.this.finish();
                              }
                            }GmtActivity.ALL_DEVICE);
                      } 
                    }
                  };
                super(this, dialog, str1);
                button.setOnClickListener(onClickListener2);
                button = GmtActivity.this.Cancel;
                View.OnClickListener onClickListener1 = new View.OnClickListener() {
                    final Dialog val$dialog;
                    
                    public void onClick(View param2View) {
                      dialog.dismiss();
                    }
                  };
                super(this, dialog);
                button.setOnClickListener(onClickListener1);
                DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() {
                    final GmtActivity.null this$1;
                    
                    public void onDismiss(DialogInterface param2DialogInterface) {
                      OrientationManager.unlockOrientation(GmtActivity.this.act);
                    }
                  };
                super(this);
                dialog.setOnDismissListener(onDismissListener);
                DialogInterface.OnShowListener onShowListener = new DialogInterface.OnShowListener() {
                    final GmtActivity.null this$1;
                    
                    public void onShow(DialogInterface param2DialogInterface) {
                      OrientationManager.lockOrientation(GmtActivity.this.act);
                    }
                  };
                super(this);
                dialog.setOnShowListener(onShowListener);
                dialog.show();
              } catch (Exception exception) {
                Utilities.escribeArchivo(GmtActivity.TAG, "Error: createExitAppDialog", exception.getMessage());
              }  
          }
        });
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/gmt/GmtActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */