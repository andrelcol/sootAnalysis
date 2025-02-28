package com.roadtrack.onstar.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;

public class CallPhone {
  public static String TAG = "CallPhone";
  
  private Context _ctx;
  
  private Button btnNOk;
  
  private Button btnOk;
  
  private String global_popup_btn_llamar_1;
  
  private String global_popup_btn_no_1;
  
  private StringsResourcesVO stringsResourcesVO;
  
  public CallPhone(Context paramContext) {
    this._ctx = paramContext;
    this.stringsResourcesVO = new StringsResourcesVO();
    this.global_popup_btn_llamar_1 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_popup_btn_llamar_1, 2131689948);
    this.global_popup_btn_no_1 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
  }
  
  public void Emergency() {
    final Dialog dialog;
    String str1 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_emergencia_1, 2131689919);
    String str2 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.emergencia_global_popup_lbl_llamadaemergencia_2, 2131689778);
    Utilities.getDrawableFromConfigList(this._ctx, DrawableResourcesVO.actions_dialog_emergency, 2131165286);
    if (Utilities.isAndinos().booleanValue()) {
      dialog = Utilities.simpleDialog(this._ctx, (Drawable)null, str1, str2, true, this.global_popup_btn_llamar_1, false, this.global_popup_btn_no_1);
    } else {
      dialog = Utilities.simpleDialog(this._ctx, (Drawable)null, (String)dialog, str2, true, this.global_popup_btn_llamar_1, false, this.global_popup_btn_no_1);
    } 
    this.btnOk = (Button)dialog.findViewById(2131296343);
    this.btnNOk = (Button)dialog.findViewById(2131296344);
    this.btnOk.setOnClickListener(new View.OnClickListener() {
          final CallPhone this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            CallSOS.SendCall(CallPhone.this._ctx, GlobalMembers.PhoneSOS.toString());
            dialog.dismiss();
          }
        });
    this.btnNOk.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            GlobalMembers.isShowingDialog = false;
          }
        });
    dialog.setOnCancelListener(new DialogInterface.OnCancelListener(this) {
          public void onCancel(DialogInterface param1DialogInterface) {
            GlobalMembers.isShowingDialog = false;
            Utilities.escribeArchivo(CallPhone.TAG, "btn_emergency:", "canceled");
            Intent intent = new Intent();
            intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
            GlobalMembers.contexGlobal.sendBroadcast(intent);
          }
        });
    dialog.show();
    GlobalMembers.isShowingDialog = true;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/CallPhone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */