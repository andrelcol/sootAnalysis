package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;

public class SetGmt_Task extends AsyncTask<Void, Void, String> {
  private final String TAG = SetGmt_Task.class.getSimpleName();
  
  private Base_Interface mBase_Interface;
  
  private Context mContext;
  
  private String mDeviceId = null;
  
  private String mGmtId;
  
  private ProgressDialog mProgressDialog;
  
  public SetGmt_Task(Context paramContext, Base_Interface paramBase_Interface, String paramString) {
    this.mContext = paramContext;
    this.mBase_Interface = paramBase_Interface;
    this.mGmtId = paramString;
    this.mDeviceId = null;
  }
  
  public SetGmt_Task(Context paramContext, Base_Interface paramBase_Interface, String paramString1, String paramString2) {
    this.mContext = paramContext;
    this.mBase_Interface = paramBase_Interface;
    this.mGmtId = paramString1;
    this.mDeviceId = paramString2;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    if (!NetUtilities.validateNetwork(this.mContext, false))
      return null; 
    try {
      WsAccess wsAccess = new WsAccess();
      this(this.mContext);
      String str = wsAccess.setGmt(null, this.mDeviceId, this.mGmtId);
      Utilities.escribeArchivo(this.TAG, "Result: ", str);
      return str;
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, "Error: ", exception.getMessage());
      return null;
    } 
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    this.mProgressDialog.dismiss();
    if (paramString != null && !paramString.isEmpty()) {
      this.mBase_Interface.onSuccess(paramString);
    } else {
      this.mBase_Interface.onFail(paramString);
    } 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(this.mContext, stringsResourcesVO.please_wait, 2131690270);
    new DBFunctions(this.mContext);
    this.mProgressDialog = ProgressDialog.show(this.mContext, null, str, true);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/SetGmt_Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */