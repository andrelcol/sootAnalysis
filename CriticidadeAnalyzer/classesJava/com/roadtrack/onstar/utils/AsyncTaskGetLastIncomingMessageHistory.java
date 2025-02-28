package com.roadtrack.onstar.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.FollowMeHandler;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import java.util.ArrayList;

public class AsyncTaskGetLastIncomingMessageHistory extends AsyncTask<String, Void, String[]> {
  private Context context;
  
  private String deviceId;
  
  private ProgressDialog progressDialog;
  
  private WsAccess wsAccess;
  
  public AsyncTaskGetLastIncomingMessageHistory(Context paramContext) {
    this.context = paramContext;
  }
  
  private String[] getFollowMePointInfo(String paramString) {
    return paramString.split("\\|");
  }
  
  protected String[] doInBackground(String... paramVarArgs) {
    Thread thread = Thread.currentThread();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(AsyncTaskGetLastIncomingMessageHistory.class.getSimpleName());
    stringBuilder.append(": ");
    stringBuilder.append(Thread.currentThread().getName());
    thread.setName(stringBuilder.toString());
    this.deviceId = paramVarArgs[1];
    return this.wsAccess.GetLastIncomingMessageHistory(paramVarArgs[0]);
  }
  
  protected void onPostExecute(String[] paramArrayOfString) {
    super.onPostExecute(paramArrayOfString);
    this.progressDialog.hide();
    DBFunctions dBFunctions = new DBFunctions(this.context);
    if (paramArrayOfString != null && !paramArrayOfString[0].equals("0|") && paramArrayOfString.length > 1) {
      FollowMeHandler.deleteAllFollowmePointsSpecial();
      for (byte b = 0; b < paramArrayOfString.length; b++) {
        if (b != 0) {
          String[] arrayOfString = getFollowMePointInfo(paramArrayOfString[b]);
          if (Long.valueOf(dBFunctions.addFollowMePush((int)GlobalMembers.followMeIdOnDB, this.deviceId, arrayOfString[2], arrayOfString[3], arrayOfString[6])).longValue() > 0L) {
            GlobalMembers.lastFollowMePoint = new Double[2];
            GlobalMembers.lastFollowMePoint[0] = Double.valueOf(Double.parseDouble(arrayOfString[3]));
            GlobalMembers.lastFollowMePoint[1] = Double.valueOf(Double.parseDouble(arrayOfString[2]));
          } 
        } 
      } 
      ArrayList arrayList = GlobalMembers.followMeArrayListPoints;
      if (arrayList == null || arrayList.size() == 0)
        FollowMeHandler.proccessForOnFollowMeGlim(Long.toString(GlobalMembers.followMeIdOnDB), this.deviceId); 
    } 
    (new DBFunctions(this.context)).executeNotificationFollowMeSynch(this.context, this.deviceId);
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
    this.progressDialog = ProgressDialog.show(this.context, null, str, true);
    this.wsAccess = new WsAccess(this.context);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/AsyncTaskGetLastIncomingMessageHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */