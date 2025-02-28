package com.roadtrack.onstar.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import com.roadtrack.onstar.VO.StringsResourcesVO;

public class DialogManager implements DialogInterface.OnDismissListener {
  private static DialogManager instance;
  
  private static boolean isShowing = false;
  
  private Button btnOk;
  
  private Context context;
  
  private Dialog dialog;
  
  public static DialogManager getInstance() {
    if (instance == null)
      instance = new DialogManager(); 
    return instance;
  }
  
  public void dismissDialog() {
    isShowing = false;
    Dialog dialog = this.dialog;
    if (dialog != null)
      dialog.dismiss(); 
  }
  
  public boolean isShowing() {
    Dialog dialog = this.dialog;
    return (dialog != null) ? dialog.isShowing() : false;
  }
  
  public void onDismiss(DialogInterface paramDialogInterface) {
    isShowing = false;
  }
  
  public void setContext(Context paramContext) {
    this.context = paramContext;
  }
  
  public void showDialog() {
    Dialog dialog = this.dialog;
    if (dialog != null) {
      dialog.dismiss();
      isShowing = false;
    } 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str3 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_lbl_conexiondered_1, 2131689912);
    String str2 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    String str1 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str4 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    this.dialog = Utilities.simpleDialog(this.context, (Drawable)null, str3, str2, true, str1, false, str4);
    this.btnOk = (Button)this.dialog.findViewById(2131296343);
    this.btnOk.setOnClickListener(new View.OnClickListener() {
          final DialogManager this$0;
          
          public void onClick(View param1View) {
            DialogManager.this.dialog.dismiss();
          }
        });
    if (!isShowing() && !isShowing) {
      isShowing = true;
      this.dialog.show();
    } 
    this.dialog.setOnDismissListener(this);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/DialogManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */