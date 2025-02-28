package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;

public class GetMyPlanInformation_Task extends AsyncTask<Void, Void, String> {
  private Context context;
  
  private AsyncResponse myPlanResponse = null;
  
  private ProgressDialog progressDialog;
  
  public GetMyPlanInformation_Task(Context paramContext, AsyncResponse paramAsyncResponse) {
    this.context = paramContext;
    this.myPlanResponse = paramAsyncResponse;
  }
  
  private boolean isValidResponseStructure(String paramString) {
    null = true;
    if (paramString != null && !paramString.equals("")) {
      String[] arrayOfString = paramString.split("\\|");
      if (arrayOfString.length >= 5) {
        String str;
        if (arrayOfString.length == 5) {
          str = arrayOfString[4];
        } else {
          str = str[5];
        } 
        if (str != null && !str.equals("") && (str.split("\\*")).length > 1)
          return null; 
      } 
    } 
    return false;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    if (!NetUtilities.validateNetwork(this.context, false))
      return null; 
    try {
      if (!NetUtilities.validateNetwork(this.context, false))
        return null; 
      WsAccess wsAccess = new WsAccess();
      this(this.context);
      String str = wsAccess.getMyPlanInfo();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(":");
      stringBuilder.append(str);
      Utilities.escribeArchivo("GetMyPlanInformation_Task", "WS result", stringBuilder.toString());
      return str;
    } catch (Exception exception) {
      Utilities.escribeArchivo("GetMyPlanInformation_Task", "Error", exception.getMessage());
      return null;
    } 
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    try {
      this.progressDialog.dismiss();
    } catch (Exception exception) {
      Utilities.escribeArchivo("GetMyPlanInformation_Task", "Close Progress Dialog", exception.getMessage());
    } 
    String str = paramString;
    if (!isValidResponseStructure(paramString))
      str = null; 
    AsyncResponse asyncResponse = this.myPlanResponse;
    if (asyncResponse != null)
      asyncResponse.processFinish(str); 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.please_wait, 2131690270);
    this.progressDialog = new ProgressDialog(this.context, 2131755173);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setCancelable(false);
    this.progressDialog.setMessage(str);
    this.progressDialog.show();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetMyPlanInformation_Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */