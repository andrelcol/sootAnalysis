package com.roadtrack.onstar.async_tasks.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.interfaces.DevicesInterface;
import com.roadtrack.onstar.utils.Utilities;

public class GetDevicesTask extends AsyncTask<Void, Void, String> {
  private final String TAG = GetDevicesTask.class.getSimpleName();
  
  private DevicesInterface callBack;
  
  private String mAccountId;
  
  private Activity mActivity;
  
  public GetDevicesTask(DevicesInterface paramDevicesInterface, Activity paramActivity, String paramString) {
    this.callBack = paramDevicesInterface;
    this.mActivity = paramActivity;
    this.mAccountId = paramString;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    try {
      WsAccess wsAccess = new WsAccess();
      this((Context)this.mActivity.getApplication());
      return wsAccess.getDeviceList(this.TAG, this.mAccountId);
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, "Error: ", exception.getMessage());
      return null;
    } 
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    Utilities.escribeArchivo(this.TAG, "Result: ", paramString);
    this.callBack.getResponseService(paramString);
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    new DBFunctions((Context)this.mActivity.getApplication());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetDevicesTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */