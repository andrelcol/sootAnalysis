package com.roadtrack.onstar.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.StringsResourcesVO;

public class RenewalDialogManager implements DialogInterface.OnDismissListener {
  public static final String TAG = RenewalDialogManager.class.getSimpleName();
  
  private static RenewalDialogManager instance = null;
  
  private static boolean isShowing = false;
  
  private Button btnOK;
  
  private Button btnOK2;
  
  private Context context;
  
  private Dialog dialog;
  
  private int dialog_type = 1;
  
  public static RenewalDialogManager getInstance() {
    if (instance == null)
      instance = new RenewalDialogManager(); 
    return instance;
  }
  
  public void dismissDialog() {
    isShowing = false;
    Dialog dialog = this.dialog;
    if (dialog != null)
      dialog.dismiss(); 
  }
  
  public int getDialog_type() {
    return this.dialog_type;
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
  
  public void setDialog_type(int paramInt) {
    this.dialog_type = paramInt;
  }
  
  public void showDialog() {
    Dialog dialog = this.dialog;
    if (dialog != null) {
      dialog.dismiss();
      isShowing = false;
    } 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str3 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str4 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
    String str5 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str1 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_lbl_conexiondered_1, 2131689912);
    String str2 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    String str6 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_popup_btn_si_1, 2131689952);
    int i = getDialog_type();
    if (i != 2) {
      if (i != 3) {
        if (i == 4) {
          this.dialog = Utilities.simpleDialog(this.context, (Drawable)null, str1, str2, true, str5, false, str6, false);
          ((Button)this.dialog.findViewById(2131296343)).setOnClickListener(new View.OnClickListener() {
                final RenewalDialogManager this$0;
                
                public void onClick(View param1View) {
                  RenewalDialogManager.this.dismissDialog();
                }
              });
        } 
      } else {
        this.dialog = Utilities.simpleDialog(this.context, (Drawable)null, str3, str4, true, str5, false, str6, false);
        this.btnOK2 = (Button)this.dialog.findViewById(2131296343);
        this.btnOK2.setOnClickListener(new View.OnClickListener() {
              final RenewalDialogManager this$0;
              
              public void onClick(View param1View) {
                RenewalDialogManager.this.dismissDialog();
              }
            });
      } 
    } else {
      this.dialog = Utilities.simpleDialog(this.context, (Drawable)null, str1, str2, true, str5, false, str6, false);
      this.btnOK = (Button)this.dialog.findViewById(2131296343);
      this.btnOK.setOnClickListener(new View.OnClickListener() {
            final RenewalDialogManager this$0;
            
            public void onClick(View param1View) {
              RenewalDialogManager.this.dismissDialog();
              Intent intent = new Intent(RenewalDialogManager.this.context, MainActivity.class);
              MainActivity.Showbanner = true;
              RenewalDialogManager.this.context.startActivity(intent);
            }
          });
    } 
    if (!isShowing() && !isShowing)
      try {
        isShowing = true;
        this.dialog.show();
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Exception", exception.getMessage());
        isShowing = false;
      }  
    this.dialog.setOnDismissListener(this);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/RenewalDialogManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */