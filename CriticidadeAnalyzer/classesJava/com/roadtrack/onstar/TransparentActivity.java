package com.roadtrack.onstar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.errors.DefaultExceptionHandler;
import com.roadtrack.onstar.utils.Utilities;

public class TransparentActivity extends Activity {
  private Button btnOk;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private void showFollowMeEndDialog() {
    String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionsigueme_1, 2131689885);
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_siguemeterminado_1, 2131689934);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    final Dialog dialog = Utilities.simpleDialog((Context)this, Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_followmeonstar, 2131165290), str4, str3, true, str1, false, str2, false);
    this.btnOk = (Button)dialog.findViewById(2131296343);
    this.btnOk.setOnClickListener(new View.OnClickListener() {
          final TransparentActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            TransparentActivity.this.finish();
          }
        });
    dialog.show();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.stringsResourcesVO = new StringsResourcesVO();
    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new DefaultExceptionHandler(getApplicationContext(), this));
    Utilities.escribeArchivo("TransparentActivity", "onCreate", "Starting");
    showFollowMeEndDialog();
  }
  
  public void onResume() {
    super.onResume();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/TransparentActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */