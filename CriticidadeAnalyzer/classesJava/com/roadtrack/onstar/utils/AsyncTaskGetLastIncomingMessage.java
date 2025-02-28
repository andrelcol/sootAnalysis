package com.roadtrack.onstar.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.FollowMeHandler;
import com.roadtrack.onstar.googleMaps.ActivityMapViewerG;
import com.roadtrack.onstar.tomtom.utilities.OnFollowMeListener;
import java.util.ArrayList;

public class AsyncTaskGetLastIncomingMessage extends AsyncTask<String, Void, String[]> {
  int certName;
  
  private Context context;
  
  private String deviceId;
  
  private Long insertedRow;
  
  String urlService;
  
  private WsAccess wsAccess;
  
  public AsyncTaskGetLastIncomingMessage(Context paramContext, boolean paramBoolean, TextView paramTextView) {
    this.context = paramContext;
    this.urlService = GlobalMembers.URL_WCF;
    this.certName = 2131623963;
    String str = GlobalMembers.NAMESPACE_WCF;
  }
  
  private String[] getFollowMePointInfo(String paramString) {
    return paramString.split("\\|");
  }
  
  protected String[] doInBackground(String... paramVarArgs) {
    Thread thread = Thread.currentThread();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(AsyncTaskGetLastIncomingMessage.class.getSimpleName());
    stringBuilder.append(": ");
    stringBuilder.append(Thread.currentThread().getName());
    thread.setName(stringBuilder.toString());
    this.deviceId = paramVarArgs[1];
    if (!NetUtilities.setUpHttpsConnection(this.urlService, this.context, this.certName, GlobalMembers.nameKeystoreServiceWS)) {
      paramVarArgs[0] = "0|";
      return paramVarArgs;
    } 
    return this.wsAccess.GetLastIncomingMessage(paramVarArgs[0]);
  }
  
  protected void onPostExecute(String[] paramArrayOfString) {
    super.onPostExecute(paramArrayOfString);
    DBFunctions dBFunctions = new DBFunctions(this.context);
    if (paramArrayOfString != null && !paramArrayOfString[0].equals("0|") && paramArrayOfString.length > 1) {
      try {
        int i = paramArrayOfString.length;
        for (byte b1 = 0; b1 < i; b1++)
          Utilities.escribeArchivo("Sígueme Test", "AsyncTaskGetLastIncomingMessage onPOstExecute", paramArrayOfString[b1]); 
      } catch (Exception exception) {}
      FollowMeHandler.deleteAllFollowmePointsSpecial();
      for (byte b = 1; b < paramArrayOfString.length; b++) {
        String[] arrayOfString1 = getFollowMePointInfo(paramArrayOfString[b]);
        this.insertedRow = Long.valueOf(dBFunctions.addFollowMePush((int)GlobalMembers.followMeIdOnDB, this.deviceId, arrayOfString1[2], arrayOfString1[3], arrayOfString1[6]));
        if (this.insertedRow.longValue() > 0L) {
          GlobalMembers.lastFollowMePoint = new Double[2];
          GlobalMembers.lastFollowMePoint[0] = Double.valueOf(Double.parseDouble(arrayOfString1[3]));
          GlobalMembers.lastFollowMePoint[1] = Double.valueOf(Double.parseDouble(arrayOfString1[2]));
        } 
      } 
      ArrayList arrayList = GlobalMembers.followMeArrayListPoints;
      if (arrayList == null || arrayList.size() == 0)
        FollowMeHandler.proccessForOnFollowMe(Long.toString(GlobalMembers.followMeIdOnDB)); 
      String[] arrayOfString = AsyncTaskGetLastIncomingMessage.class.getPackage().getName().toString().split("\\.");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(arrayOfString[0]);
      stringBuilder.append(".");
      stringBuilder.append(arrayOfString[1]);
      stringBuilder.append(".");
      stringBuilder.append(arrayOfString[2]);
      String str = stringBuilder.toString();
      if (OnFollowMeListener.onFollowMeObjectListener != null) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str);
        stringBuilder1.append(".googleMaps.ActivityMapViewerG");
        if (Utilities.ActivityRunning(stringBuilder1.toString(), (Context)ActivityMapViewerG.getActivity()))
          OnFollowMeListener.onFollowMeObjectListener.onFollowMe(GlobalMembers.followMeArrayListPoints); 
      } 
    } 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    this.insertedRow = Long.valueOf(0L);
    this.wsAccess = new WsAccess(this.context);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/AsyncTaskGetLastIncomingMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */