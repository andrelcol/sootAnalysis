package com.roadtrack.onstar.async_tasks.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.TomTomStatistics_VO;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;

public class SendTomTomStatistics_Task extends AsyncTask<Void, Void, String> {
  private final String TAG = SendTomTomStatistics_Task.class.getSimpleName();
  
  private Base_Interface baseListener;
  
  private Context context;
  
  private DBFunctions dbFunctions;
  
  public SendTomTomStatistics_Task(Base_Interface paramBase_Interface, Context paramContext) {
    this.context = paramContext;
    this.baseListener = paramBase_Interface;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    new String();
    StringBuilder stringBuilder = new StringBuilder();
    if (!NetUtilities.validateNetwork(this.context, false))
      return null; 
    ArrayList arrayList = this.dbFunctions.getAllTomTomStatistics();
    if (arrayList != null && arrayList.size() != 0) {
      for (TomTomStatistics_VO tomTomStatistics_VO : arrayList) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(tomTomStatistics_VO.getAccountId());
        stringBuilder1.append(",");
        stringBuilder1.append(tomTomStatistics_VO.getDeviceId());
        stringBuilder1.append("*");
        stringBuilder1.append(tomTomStatistics_VO.getFechaHora());
        stringBuilder1.append(",");
        stringBuilder1.append(tomTomStatistics_VO.getLatitudeStart());
        stringBuilder1.append(",");
        stringBuilder1.append(tomTomStatistics_VO.getLongitudeStart());
        stringBuilder1.append(",");
        stringBuilder1.append(tomTomStatistics_VO.getLatitudeEnd());
        stringBuilder1.append(",");
        stringBuilder1.append(tomTomStatistics_VO.getLongitudeEnd());
        stringBuilder1.append(",");
        stringBuilder1.append(tomTomStatistics_VO.getActionId());
        stringBuilder1.append("|");
        stringBuilder.append(stringBuilder1.toString());
      } 
      String str = stringBuilder.toString();
      str = str.substring(0, str.length() - 1);
      try {
        WsAccess wsAccess = new WsAccess();
        this(this.context);
        String str1 = wsAccess.saveStatisticsNavigationIn(str);
        String str2 = this.TAG;
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append(":");
        stringBuilder1.append(str1);
        Utilities.escribeArchivo(str2, "WS result", stringBuilder1.toString());
        return str1;
      } catch (Exception exception) {
        Utilities.escribeArchivo(this.TAG, "Error", exception.getMessage());
      } 
    } 
    return null;
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    if (paramString != null && !paramString.isEmpty()) {
      this.dbFunctions.removeAllTomTomStatistics();
      this.baseListener.onSuccess(paramString);
    } else {
      this.baseListener.onFail(null);
    } 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    this.dbFunctions = new DBFunctions(this.context);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/SendTomTomStatistics_Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */