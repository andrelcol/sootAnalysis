package com.roadtrack.onstar;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.errors.DefaultExceptionHandler;
import com.roadtrack.onstar.utils.Utilities;
import java.util.List;

public class TransparentActivityWithSpinner extends Activity {
  private boolean isActivityRunning = false;
  
  public boolean containsOnstarActivity(ActivityManager.RunningTaskInfo paramRunningTaskInfo) {
    return paramRunningTaskInfo.baseActivity.getClassName().equals(MainActivity.class.getCanonicalName());
  }
  
  public boolean isActivityRunning() {
    List list = ((ActivityManager)getSystemService("activity")).getRunningTasks(2147483647);
    this.isActivityRunning = false;
    for (ActivityManager.RunningTaskInfo runningTaskInfo : list) {
      Utilities.escribeArchivo("TransparentActivityWithSpinner", "isActivityRunning", runningTaskInfo.baseActivity.getClassName());
      if (containsOnstarActivity(runningTaskInfo)) {
        this.isActivityRunning = true;
        break;
      } 
    } 
    return this.isActivityRunning;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new DefaultExceptionHandler(getApplicationContext(), this));
    Utilities.escribeArchivo("TransparentActivityWithSpinner", "onCreate", "Starting");
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    ProgressDialog progressDialog = new ProgressDialog((Context)this, 2131755173);
    progressDialog.setMessage(Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953));
    progressDialog.setIndeterminate(false);
    progressDialog.setCancelable(false);
    progressDialog.show();
  }
  
  public void onResume() {
    super.onResume();
    if (!isActivityRunning())
      finish(); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/TransparentActivityWithSpinner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */