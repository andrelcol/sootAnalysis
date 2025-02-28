package com.roadtrack.onstar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.roadtrack.onstar.utils.CloseAppDialogManager;

public class TransparentActivityExpirationDialog extends Activity {
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    setContentView(2131427382);
    CloseAppDialogManager.getInstance().setContext((Context)this);
    CloseAppDialogManager.getInstance().showDialog();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/TransparentActivityExpirationDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */