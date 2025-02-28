package com.roadtrack.onstar.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.roadtrack.onstar.VO.StringsResourcesVO;

public class DialogEmpty extends Dialog implements View.OnClickListener {
  private boolean _useButtonOk;
  
  private String content;
  
  private TextView context;
  
  private Activity mActivity;
  
  private Button yes;
  
  public DialogEmpty(Activity paramActivity, String paramString) {
    super((Context)paramActivity);
    this.mActivity = paramActivity;
    this.content = paramString;
    this._useButtonOk = false;
  }
  
  public DialogEmpty(Activity paramActivity, String paramString, boolean paramBoolean) {
    super((Context)paramActivity);
    this.mActivity = paramActivity;
    this.content = paramString;
    this._useButtonOk = paramBoolean;
  }
  
  public void onClick(View paramView) {
    if (paramView.getId() == 2131296321)
      dismiss(); 
    dismiss();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2131427470);
    getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0));
    this.yes = (Button)findViewById(2131296321);
    if (this._useButtonOk) {
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      String str = Utilities.getStringFromConfigList((Context)this.mActivity, stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
      this.yes.setText(str);
    } 
    this.context = (TextView)findViewById(2131296478);
    this.context.setText(this.content);
    this.yes.setOnClickListener(this);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/DialogEmpty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */