package com.roadtrack.onstar.async_tasks.tasks;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Process;
import com.google.firebase.iid.FirebaseInstanceId;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.pushManager;
import com.roadtrack.onstar.GCMIntentService;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.servicios.ServiceAlertsSocket;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.SocketListener;
import com.roadtrack.onstar.utils.Utilities;

public class AsyncTaskUnregisterDevice extends AsyncTask<String, Void, String> {
  String appId;
  
  private String device;
  
  String deviceName;
  
  String hashKey;
  
  Activity mActivity;
  
  Context mContext;
  
  private pushManager manager;
  
  private onstarApplication rtApp;
  
  String toExitAPP;
  
  String token;
  
  String user;
  
  public AsyncTaskUnregisterDevice(Activity paramActivity) {
    this.mContext = paramActivity.getApplicationContext();
    this.mActivity = paramActivity;
    this.manager = new pushManager(paramActivity);
  }
  
  public void GCM_Inner(Activity paramActivity) {
    this.token = FirebaseInstanceId.getInstance().getToken();
    this.rtApp = (onstarApplication)paramActivity.getApplicationContext();
    this.manager.setOnDeviceRegister(new pushManager.ondeviceRegister(this) {
        
        });
    String str = this.rtApp.getLocatorUserId();
    this.device = PreferenceRT.GetValuePreference(GlobalMembers.deviceName, "", onstarApplication.getContext());
    if (this.token.length() < 1)
      this.token = GlobalMembers.token; 
    AsyncTaskUnregisterDevice asyncTaskUnregisterDevice = new AsyncTaskUnregisterDevice(paramActivity);
    if (!GlobalMembers.unRegisterDevice) {
      GlobalMembers.unRegisterDevice = true;
      asyncTaskUnregisterDevice.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "3434", this.token, str, String.valueOf(13), this.device, "" });
    } 
  }
  
  protected String doInBackground(String... paramVarArgs) {
    this.hashKey = paramVarArgs[0];
    this.token = paramVarArgs[1];
    this.user = paramVarArgs[2];
    this.appId = paramVarArgs[3];
    this.deviceName = paramVarArgs[4];
    this.toExitAPP = paramVarArgs[5];
    Utilities.escribeArchivo("MainActivity", "EXITAPP", "Before unregisterDevice");
    if (NetUtilities.setUpHttpsConnection(GlobalMembers.URL_WCF, (Context)this.mActivity, 2131623963, GlobalMembers.nameKeystoreServiceWS)) {
      WsAccess.unRegisterDevice(paramVarArgs[0], paramVarArgs[1], paramVarArgs[2], paramVarArgs[3], this.mActivity);
      if (!this.toExitAPP.equals("exit")) {
        Utilities.escribeArchivo("MainActivity", "!EXITAPP", "Before deviceRegister");
        String str = Utilities.DeviceUuidFactory((Context)this.mActivity);
        this.manager.deviceRegister(this.hashKey, this.token, this.user, str, this.appId, "1", this.deviceName, false);
      } 
    } 
    return "";
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    if (this.toExitAPP.equals("exit")) {
      ((NotificationManager)this.mContext.getSystemService("notification")).cancelAll();
      SocketListener.SocketListenerCloses();
      Activity activity = this.mActivity;
      activity.stopService(new Intent((Context)activity, ServiceAlertsSocket.class));
      PreferenceRT.SetValuePreference("EnebledPush", false, (Context)this.mActivity);
      activity = this.mActivity;
      activity.stopService(new Intent((Context)activity, GCMIntentService.class));
      this.mActivity.finish();
      Process.killProcess(Process.myPid());
      GlobalMembers.activeSession = false;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/AsyncTaskUnregisterDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */