package com.roadtrack.onstar.ui.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.interfaces.DialogToActivity;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedHashMap;

public class DialogPaymentOptions extends DialogFragment implements View.OnClickListener {
  private Button btn_dpo_negative;
  
  private Button btn_dpo_positive;
  
  DialogToActivity dialogToActivity;
  
  private TextView lbl_dpo_price2;
  
  private TextView lbl_dpo_title;
  
  private LinkedHashMap<String, Object> params;
  
  private RadioButton rdb_dpo_option2;
  
  private onstarApplication rtApp;
  
  private Typeface tfLouis;
  
  private Typeface tfLouisBold;
  
  private void formattedFont() {
    this.tfLouis = onstarApplication.getTypeface((Context)getActivity(), this.rtApp.fontPathLouisRegular);
    this.tfLouisBold = onstarApplication.getTypeface((Context)getActivity(), this.rtApp.fontPathLouisBold);
    this.rdb_dpo_option2.setTypeface(this.tfLouis);
    this.lbl_dpo_price2.setTypeface(this.tfLouisBold);
  }
  
  public void onClick(View paramView) {
    switch (paramView.getId()) {
      default:
        return;
      case 2131296416:
        this.dialogToActivity.onOptionSelected(Enums.paymentMethod.CreditCard.toString());
        dismiss();
      case 2131296415:
        break;
    } 
    dismiss();
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setStyle(1, 2131755178);
    setCancelable(true);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view = paramLayoutInflater.inflate(2131427361, paramViewGroup, false);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    this.rtApp = (onstarApplication)getActivity().getApplicationContext();
    this.rdb_dpo_option2 = (RadioButton)view.findViewById(2131296973);
    this.btn_dpo_positive = (Button)view.findViewById(2131296416);
    this.btn_dpo_negative = (Button)view.findViewById(2131296415);
    this.lbl_dpo_price2 = (TextView)view.findViewById(2131296735);
    this.lbl_dpo_title = (TextView)view.findViewById(2131296736);
    String str4 = Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.renovacion_lbl_formadepago, 2131690289);
    Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.renovacion_lbl_boletobancario, 2131690284);
    String str2 = Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.renovacion_lbl_tarjetadecredito, 2131690319);
    Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.renovacion_lbl_aunpago2, 2131690282);
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.renovacion_lbl_ohasta12xde, 2131690304);
    String str3 = Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    String str5 = Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    this.lbl_dpo_title.setText(str4);
    this.rdb_dpo_option2.setText(str2);
    this.lbl_dpo_price2.setText(str1);
    this.btn_dpo_negative.setText(str3);
    this.btn_dpo_positive.setText(str5);
    this.rdb_dpo_option2.setChecked(true);
    this.btn_dpo_negative.setOnClickListener(this);
    this.btn_dpo_positive.setOnClickListener(this);
    try {
      this.params = (LinkedHashMap<String, Object>)getArguments().getSerializable("params");
      if (this.params.containsKey("TypeToRenewal")) {
        str1 = this.params.get("TypeToRenewal").toString();
        this.rdb_dpo_option2.setChecked(true);
        if (str1.equals("2")) {
          this.lbl_dpo_price2.setText("");
        } else {
          String str = Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.renovacion_lbl_un_pago_mayus, 2131690323);
          this.lbl_dpo_price2.setText(str);
        } 
        formattedFont();
      } 
    } catch (Exception exception) {
      dismiss();
    } 
    return view;
  }
  
  public void setDialogToActivity(DialogToActivity paramDialogToActivity) {
    this.dialogToActivity = paramDialogToActivity;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/dialogs/DialogPaymentOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */