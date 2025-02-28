package com.roadtrack.onstar.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.Utilities;

@SuppressLint({"NewApi"})
public class DialogTwoOptions extends DialogFragment {
  private Button btnDialogOk;
  
  private String mMsg = "";
  
  private String mMsgBtn = "";
  
  private OnClickDialogListener mOnClickDialogListener;
  
  private TextView tvMensaje;
  
  public static DialogTwoOptions newInstance(String paramString1, String paramString2) {
    DialogTwoOptions dialogTwoOptions = new DialogTwoOptions();
    Bundle bundle = new Bundle();
    bundle.putString("msg", paramString1);
    dialogTwoOptions.setArguments(bundle);
    bundle.putString("msgBtn", paramString2);
    dialogTwoOptions.setArguments(bundle);
    return dialogTwoOptions;
  }
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    Dialog dialog = new Dialog((Context)getActivity());
    this.mMsg = getArguments().getString("msg");
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    String str2 = Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    if (this.mMsg == null)
      this.mMsg = str1; 
    this.mMsgBtn = getArguments().getString("msgBtn");
    if (this.mMsgBtn == null)
      this.mMsgBtn = str2; 
    dialog.getWindow().requestFeature(1);
    dialog.getWindow().setFlags(1024, 1024);
    dialog.setContentView(2131427470);
    ColorDrawable colorDrawable = new ColorDrawable(0);
    dialog.getWindow().setBackgroundDrawable((Drawable)colorDrawable);
    dialog.show();
    this.tvMensaje = (TextView)dialog.findViewById(2131296478);
    this.tvMensaje.setText(this.mMsg);
    this.btnDialogOk = (Button)dialog.findViewById(2131296321);
    this.btnDialogOk.setText(this.mMsgBtn);
    this.btnDialogOk.setOnClickListener(new View.OnClickListener() {
          final DialogTwoOptions this$0;
          
          public void onClick(View param1View) {
            if (DialogTwoOptions.this.mOnClickDialogListener != null)
              DialogTwoOptions.this.mOnClickDialogListener.onClickDialog(1); 
            DialogTwoOptions.this.dismiss();
          }
        });
    return dialog;
  }
  
  public void setOnClickDialogListener(OnClickDialogListener paramOnClickDialogListener) {
    this.mOnClickDialogListener = paramOnClickDialogListener;
  }
  
  public static interface OnClickDialogListener {
    void onClickDialog(int param1Int);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/dialogs/DialogTwoOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */