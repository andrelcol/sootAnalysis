package com.roadtrack.onstar.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.VO.RenewalPlanO;
import com.roadtrack.onstar.VO.RenewalPlansListResponseO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.CallSOS;
import com.roadtrack.onstar.utils.Utilities;
import java.util.List;

public class RenewalPlansAdapter extends BaseAdapter {
  public static final String TAG = RenewalPlansAdapter.class.getSimpleName();
  
  private Activity act;
  
  private TextView btn_ir_renewal_annual;
  
  private TextView btn_ir_renewal_monthly;
  
  private Context context;
  
  private TextView lbl_ir_plan_description;
  
  private TextView lbl_ir_plan_name;
  
  private dialogToActivity listener;
  
  private List<RenewalPlanO> plansList;
  
  private RenewalPlansListResponseO responseO;
  
  private onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  public RenewalPlansAdapter(Context paramContext, List<RenewalPlanO> paramList, Activity paramActivity, RenewalPlansListResponseO paramRenewalPlansListResponseO) {
    this.context = paramContext;
    this.plansList = paramList;
    this.act = paramActivity;
    this.responseO = paramRenewalPlansListResponseO;
  }
  
  private void formattedFont() {
    onstarApplication onstarApplication1 = this.rtApp;
    Typeface typeface = onstarApplication.getTypeface(this.context, onstarApplication1.fontPathLouisRegular);
    this.lbl_ir_plan_name.setTypeface(typeface);
    this.lbl_ir_plan_description.setTypeface(typeface);
    this.btn_ir_renewal_annual.setTypeface(typeface);
    this.btn_ir_renewal_monthly.setTypeface(typeface);
  }
  
  private void getMatrixRenewalCases(int paramInt1, int paramInt2) {
    String str1 = this.responseO.getCpres8();
    String str2 = this.responseO.getCpres9();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("status: ");
    stringBuilder.append(str1);
    String str3 = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append("fecha: ");
    stringBuilder.append(str2);
    Utilities.escribeArchivo("PMM CAMBIO TC", str3, stringBuilder.toString());
    if (str1.equals("3") && !str2.equals("") && paramInt1 == 1) {
      this.listener.onResponseSet(1, RENOVATION_TYPE_CASE.OneShotConFechaAnual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("3") && str2.equals("") && paramInt1 == 1) {
      this.listener.onResponseSet(1, RENOVATION_TYPE_CASE.OneShotSinFechaAnual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("1") && !str2.equals("") && paramInt1 == 1) {
      this.listener.onResponseSet(1, RENOVATION_TYPE_CASE.AnualRecurrenteConFechaAnual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("2") && !str2.equals("") && paramInt1 == 1) {
      this.listener.onResponseSet(1, RENOVATION_TYPE_CASE.MensualRecurrenteConFechaAnual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("2") && str2.equals("") && paramInt1 == 1) {
      this.listener.onResponseSet(1, RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaAnual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("1") && str2.equals("") && paramInt1 == 1) {
      this.listener.onResponseSet(1, RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaAnual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("3") && !str2.equals("") && paramInt1 == 0) {
      this.listener.onResponseSet(0, RENOVATION_TYPE_CASE.OneShotConFechaMensual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("3") && str2.equals("") && paramInt1 == 0) {
      this.listener.onResponseSet(0, RENOVATION_TYPE_CASE.OneShotSinFechaMensual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("1") && !str2.equals("") && paramInt1 == 0) {
      this.listener.onResponseSet(0, RENOVATION_TYPE_CASE.AnualRecurrenteConFechaMensual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("2") && !str2.equals("") && paramInt1 == 0) {
      this.listener.onResponseSet(0, RENOVATION_TYPE_CASE.MensualRecurrenteConFechaMensual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("2") && str2.equals("") && paramInt1 == 0) {
      this.listener.onResponseSet(0, RENOVATION_TYPE_CASE.MensualRecurrenteSinFechaMensual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("1") && str2.equals("") && paramInt1 == 0) {
      this.listener.onResponseSet(0, RENOVATION_TYPE_CASE.AnualRecurrenteSinFechaMensual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("4") && !str2.equals("") && paramInt1 == 1) {
      this.listener.onResponseSet(0, RENOVATION_TYPE_CASE.NombreSinFechaAnual.getOption(), this.plansList.get(paramInt2));
    } else if (str1.equals("4") && !str2.equals("") && paramInt1 == 0) {
      this.listener.onResponseSet(0, RENOVATION_TYPE_CASE.NombreSinFechaMensual.getOption(), this.plansList.get(paramInt2));
    } else {
      this.listener.onResponseSet(5, 5, this.plansList.get(paramInt2));
    } 
  }
  
  public int getCount() {
    return this.plansList.size();
  }
  
  public Object getItem(int paramInt) {
    return this.plansList.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(final int position, View paramView, ViewGroup paramViewGroup) {
    String str1;
    View view = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2131427427, paramViewGroup, false);
    this.rtApp = (onstarApplication)this.context.getApplicationContext();
    this.stringsResourcesVO = new StringsResourcesVO();
    this.lbl_ir_plan_name = (TextView)view.findViewById(2131296758);
    this.lbl_ir_plan_description = (TextView)view.findViewById(2131296757);
    String str3 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_lbl_signomoneda, 2131690316);
    this.btn_ir_renewal_annual = (TextView)view.findViewById(2131296759);
    this.btn_ir_renewal_annual.setText(str3);
    this.btn_ir_renewal_monthly = (TextView)view.findViewById(2131296760);
    this.btn_ir_renewal_monthly.setText(str3);
    formattedFont();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(" <b>");
    stringBuilder2.append(((RenewalPlanO)this.plansList.get(position)).getCpapres3());
    stringBuilder2.append("</b>");
    String str2 = stringBuilder2.toString();
    this.lbl_ir_plan_name.setText((CharSequence)Html.fromHtml(str2));
    if (((RenewalPlanO)this.plansList.get(position)).getCpapres4().contains("{string}")) {
      str2 = ((RenewalPlanO)this.plansList.get(position)).getCpapres4().replace("{string}", "0800 011 1095");
    } else {
      str2 = ((RenewalPlanO)this.plansList.get(position)).getCpapres4();
    } 
    this.lbl_ir_plan_description.setText(str2);
    String str5 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_lbl_pago_anual, 2131690305);
    String str6 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_lbl_precio_anual_descuento_2, 2131690311);
    String str7 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_lbl_pago_mensual, 2131690306);
    String str4 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_lbl_precio_mensual, 2131690312);
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(str7);
    stringBuilder1.append("\n");
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(((RenewalPlanO)this.plansList.get(position)).getCpapres11());
    stringBuilder3.append(" ");
    stringBuilder3.append(((RenewalPlanO)this.plansList.get(position)).getCpapres15());
    stringBuilder1.append(String.format(str4, new Object[] { stringBuilder3.toString() }));
    str4 = stringBuilder1.toString();
    if (((RenewalPlanO)this.plansList.get(position)).getCpapres2().equalsIgnoreCase("OMS") && ((RenewalPlanO)this.plansList.get(position)).getCpapres10().equalsIgnoreCase("P8BA11")) {
      str1 = ((RenewalPlanO)this.plansList.get(position)).getCpapres5();
      str4 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    } else {
      if (((RenewalPlanO)this.plansList.get(position)).getCpapres14().equals("null") || ((RenewalPlanO)this.plansList.get(position)).getCpapres14().equals("")) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str5);
        stringBuilder1.append("\n");
        stringBuilder1.append(((RenewalPlanO)this.plansList.get(position)).getCpapres11());
        stringBuilder1.append(" ");
        stringBuilder1.append(((RenewalPlanO)this.plansList.get(position)).getCpapres5());
        str1 = stringBuilder1.toString();
        this.btn_ir_renewal_annual.setText(str1);
        this.btn_ir_renewal_monthly.setText(str4);
        this.btn_ir_renewal_annual.setOnClickListener(new View.OnClickListener() {
              final RenewalPlansAdapter this$0;
              
              final int val$position;
              
              public void onClick(View param1View) {
                if (((TextView)param1View).getText().toString().equalsIgnoreCase(Utilities.getStringFromConfigList(RenewalPlansAdapter.this.context, RenewalPlansAdapter.this.stringsResourcesVO.global_btn_volver_1, 2131689834))) {
                  PreferenceRT.SetStringPreference(GlobalMembers.valorPO, "Original", onstarApplication.getContext());
                  RenewalPlansAdapter.this.act.finish();
                } else {
                  RenewalPlansAdapter.this.getMatrixRenewalCases(1, position);
                } 
              }
            });
        this.btn_ir_renewal_monthly.setOnClickListener(new View.OnClickListener() {
              final RenewalPlansAdapter this$0;
              
              final int val$position;
              
              public void onClick(View param1View) {
                if (((TextView)param1View).getText().toString().equalsIgnoreCase(Utilities.getStringFromConfigList(RenewalPlansAdapter.this.context, RenewalPlansAdapter.this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908))) {
                  CallSOS.SendCall(RenewalPlansAdapter.this.context, "0800 011 1095");
                } else {
                  RenewalPlansAdapter.this.getMatrixRenewalCases(0, position);
                } 
              }
            });
        return view;
      } 
      int i = -1;
      try {
        int j = Integer.parseInt(((RenewalPlanO)this.plansList.get(position)).getCpapres14());
        i = j;
      } catch (NumberFormatException numberFormatException) {
        Utilities.escribeArchivo(TAG, "Error ", numberFormatException.getMessage());
      } 
      if (i <= 0) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str5);
        stringBuilder1.append("\n");
        stringBuilder1.append(((RenewalPlanO)this.plansList.get(position)).getCpapres11());
        stringBuilder1.append(" ");
        stringBuilder1.append(((RenewalPlanO)this.plansList.get(position)).getCpapres5());
        str1 = stringBuilder1.toString();
      } else {
        try {
          stringBuilder3 = new StringBuilder();
          this();
          stringBuilder3.append(str5);
          stringBuilder3.append("\n");
          str1 = ((RenewalPlanO)this.plansList.get(position)).getCpapres14();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(((RenewalPlanO)this.plansList.get(position)).getCpapres11());
          stringBuilder.append(" ");
          stringBuilder.append(((RenewalPlanO)this.plansList.get(position)).getCpapres5());
          stringBuilder3.append(String.format(str6, new Object[] { str1, stringBuilder.toString() }));
          str1 = stringBuilder3.toString();
        } catch (Exception exception) {
          Utilities.escribeArchivo(TAG, "Error ", exception.getMessage());
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(str5);
          stringBuilder.append("\n");
          stringBuilder.append(str6);
          str1 = stringBuilder.toString();
        } 
      } 
    } 
    this.btn_ir_renewal_annual.setText(str1);
    this.btn_ir_renewal_monthly.setText(str4);
    this.btn_ir_renewal_annual.setOnClickListener(new View.OnClickListener() {
          final RenewalPlansAdapter this$0;
          
          final int val$position;
          
          public void onClick(View param1View) {
            if (((TextView)param1View).getText().toString().equalsIgnoreCase(Utilities.getStringFromConfigList(RenewalPlansAdapter.this.context, RenewalPlansAdapter.this.stringsResourcesVO.global_btn_volver_1, 2131689834))) {
              PreferenceRT.SetStringPreference(GlobalMembers.valorPO, "Original", onstarApplication.getContext());
              RenewalPlansAdapter.this.act.finish();
            } else {
              RenewalPlansAdapter.this.getMatrixRenewalCases(1, position);
            } 
          }
        });
    this.btn_ir_renewal_monthly.setOnClickListener(new View.OnClickListener() {
          final RenewalPlansAdapter this$0;
          
          final int val$position;
          
          public void onClick(View param1View) {
            if (((TextView)param1View).getText().toString().equalsIgnoreCase(Utilities.getStringFromConfigList(RenewalPlansAdapter.this.context, RenewalPlansAdapter.this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908))) {
              CallSOS.SendCall(RenewalPlansAdapter.this.context, "0800 011 1095");
            } else {
              RenewalPlansAdapter.this.getMatrixRenewalCases(0, position);
            } 
          }
        });
    return view;
  }
  
  public void setListener(dialogToActivity paramdialogToActivity) {
    this.listener = paramdialogToActivity;
  }
  
  public enum RENOVATION_TYPE_CASE {
    AnualRecurrenteConFechaAnual,
    AnualRecurrenteConFechaMensual,
    AnualRecurrenteSinFechaAnual,
    AnualRecurrenteSinFechaMensual,
    MensualRecurrenteConFechaAnual,
    MensualRecurrenteConFechaMensual,
    MensualRecurrenteSinFechaAnual,
    MensualRecurrenteSinFechaMensual,
    NombreSinFechaAnual,
    NombreSinFechaMensual,
    OneShotConFechaAnual(311),
    OneShotConFechaMensual(311),
    OneShotSinFechaAnual(301),
    OneShotSinFechaMensual(301);
    
    private static final RENOVATION_TYPE_CASE[] $VALUES;
    
    int option;
    
    static {
      AnualRecurrenteSinFechaAnual = new RENOVATION_TYPE_CASE("AnualRecurrenteSinFechaAnual", 5, 101);
      OneShotConFechaMensual = new RENOVATION_TYPE_CASE("OneShotConFechaMensual", 6, 312);
      OneShotSinFechaMensual = new RENOVATION_TYPE_CASE("OneShotSinFechaMensual", 7, 302);
      MensualRecurrenteConFechaMensual = new RENOVATION_TYPE_CASE("MensualRecurrenteConFechaMensual", 8, 212);
      MensualRecurrenteSinFechaMensual = new RENOVATION_TYPE_CASE("MensualRecurrenteSinFechaMensual", 9, 202);
      AnualRecurrenteConFechaMensual = new RENOVATION_TYPE_CASE("AnualRecurrenteConFechaMensual", 10, 112);
      AnualRecurrenteSinFechaMensual = new RENOVATION_TYPE_CASE("AnualRecurrenteSinFechaMensual", 11, 102);
      NombreSinFechaAnual = new RENOVATION_TYPE_CASE("NombreSinFechaAnual", 12, 401);
      NombreSinFechaMensual = new RENOVATION_TYPE_CASE("NombreSinFechaMensual", 13, 402);
      $VALUES = new RENOVATION_TYPE_CASE[] { 
          OneShotConFechaAnual, OneShotSinFechaAnual, AnualRecurrenteConFechaAnual, MensualRecurrenteConFechaAnual, MensualRecurrenteSinFechaAnual, AnualRecurrenteSinFechaAnual, OneShotConFechaMensual, OneShotSinFechaMensual, MensualRecurrenteConFechaMensual, MensualRecurrenteSinFechaMensual, 
          AnualRecurrenteConFechaMensual, AnualRecurrenteSinFechaMensual, NombreSinFechaAnual, NombreSinFechaMensual };
    }
    
    RENOVATION_TYPE_CASE(int param1Int1) {
      this.option = param1Int1;
    }
    
    public int getOption() {
      return this.option;
    }
  }
  
  public static interface dialogToActivity {
    void onResponseSet(int param1Int1, int param1Int2, RenewalPlanO param1RenewalPlanO);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/RenewalPlansAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */