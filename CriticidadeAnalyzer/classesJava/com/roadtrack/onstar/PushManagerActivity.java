package com.roadtrack.onstar;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import com.roadtrack.onstar.nav.routing.MapsCommonFragment;
import com.roadtrack.onstar.nav.routing.NavigateCommonDialogActivity;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.ui.settings.SettingsNewActivity;
import com.roadtrack.onstar.ui.wizard.WizardActivity;
import com.roadtrack.onstar.utils.Utilities;
import java.util.List;

public class PushManagerActivity extends Activity {
  public static String TAG = PushManagerActivity.class.getSimpleName();
  
  private boolean appIsRunnig = false;
  
  private boolean isActivityRunning = false;
  
  private boolean logininProcess = false;
  
  private Intent oldIntent = null;
  
  private onstarApplication rtApp;
  
  public boolean containsOnstarActivity(ActivityManager.RunningTaskInfo paramRunningTaskInfo) {
    String str = paramRunningTaskInfo.baseActivity.getClassName();
    return (str.equals(MainActivity.class.getCanonicalName()) || str.equals(TbtListView.class.getCanonicalName()) || str.equals(TbtManeuverInfo.class.getCanonicalName()) || str.equals(WizardActivity.class.getCanonicalName()) || str.equals(SettingsNewActivity.class.getCanonicalName()) || str.equals(NotificationsActivity.class.getCanonicalName()) || str.equals(HistoricalTestActivity.class.getCanonicalName()) || str.equals(NavigateCommonDialogActivity.class.getCanonicalName()) || str.equals(MapsCommonFragment.class.getCanonicalName()) || str.equals(HmiMenu.class.getCanonicalName()) || str.equals(TransparentActivity.class.getCanonicalName()) || str.equals(TransparentActivityWithSpinner.class.getCanonicalName()));
  }
  
  public boolean isActivityRunning() {
    List list = ((ActivityManager)getSystemService("activity")).getRunningTasks(2147483647);
    this.isActivityRunning = false;
    for (ActivityManager.RunningTaskInfo runningTaskInfo : list) {
      Utilities.escribeArchivo(TAG, "isActivityRunning", runningTaskInfo.baseActivity.getClassName());
      if (containsOnstarActivity(runningTaskInfo)) {
        this.isActivityRunning = true;
        break;
      } 
    } 
    return this.isActivityRunning;
  }
  
  public boolean isLoginInProcess(ActivityManager.RunningTaskInfo paramRunningTaskInfo) {
    return paramRunningTaskInfo.baseActivity.getClassName().equals(LoginActivity.class.getCanonicalName());
  }
  
  public boolean isLoginRunning() {
    List list = ((ActivityManager)getSystemService("activity")).getRunningTasks(2147483647);
    this.isActivityRunning = false;
    for (ActivityManager.RunningTaskInfo runningTaskInfo : list) {
      Utilities.escribeArchivo(TAG, "isActivityRunning", runningTaskInfo.baseActivity.getClassName());
      if (isLoginInProcess(runningTaskInfo)) {
        this.isActivityRunning = true;
        break;
      } 
    } 
    return this.isActivityRunning;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    this.rtApp = (onstarApplication)getApplicationContext();
    Bundle bundle = null;
    String str = paramBundle.getString("account", null);
    this.oldIntent = (Intent)paramBundle.getParcelable("oldIntent");
    this.logininProcess = isLoginRunning();
    if (!this.logininProcess) {
      this.appIsRunnig = isActivityRunning();
      if (this.appIsRunnig) {
        String str1;
        paramBundle = bundle;
        if (this.rtApp.getUserAccessData() != null) {
          paramBundle = bundle;
          if ((this.rtApp.getUserAccessData()).length > 1)
            str1 = this.rtApp.getUserAccessData()[0]; 
        } 
        if (str1 != null && str1.equals(str)) {
          startActivity(this.oldIntent);
          finish();
        } 
      } else {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
      } 
    } 
    finish();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/PushManagerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */