package com.roadtrack.onstar.async_tasks.tasks;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.RemoteDiagnosticVO;
import com.roadtrack.onstar.async_tasks.intefaces.Dtc_Interface;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.List;

public class GetDtcs_Task extends AsyncTask<Void, Void, String> {
  private final String TAG = GetDtcs_Task.class.getSimpleName();
  
  private Dtc_Interface baseListener;
  
  private DBFunctions dbFunctions;
  
  private Context mContext;
  
  private String mDeviceId;
  
  public GetDtcs_Task(Dtc_Interface paramDtc_Interface, Context paramContext, String paramString) {
    this.mContext = paramContext;
    this.baseListener = paramDtc_Interface;
    this.mDeviceId = paramString;
  }
  
  private static RemoteDiagnosticVO getFeatureInformation(String paramString) {
    RemoteDiagnosticVO remoteDiagnosticVO = new RemoteDiagnosticVO();
    if (paramString != null && paramString.length() > 0) {
      String[] arrayOfString = paramString.split("\\|");
      if (arrayOfString.length >= 3)
        try {
          remoteDiagnosticVO.setDtcGroupId(Integer.parseInt(arrayOfString[0]));
          remoteDiagnosticVO.setDtcStatusId(Integer.parseInt(arrayOfString[1]));
          remoteDiagnosticVO.setDtcTitle(arrayOfString[2]);
          if (arrayOfString.length > 3)
            remoteDiagnosticVO.setDtcDescription(arrayOfString[3]); 
          return remoteDiagnosticVO;
        } catch (NumberFormatException numberFormatException) {
          Utilities.escribeArchivo("GetDtcs_Task", "Error: getFeatureInformation", numberFormatException.getMessage());
        }  
    } 
    return null;
  }
  
  private static List<RemoteDiagnosticVO> getFeaturesByDevice(String paramString) {
    ArrayList<RemoteDiagnosticVO> arrayList = new ArrayList();
    if (paramString != null && paramString.length() > 0) {
      String[] arrayOfString = paramString.split(";");
      if (arrayOfString.length > 2) {
        String str = arrayOfString[0];
        byte b = 1;
        paramString = arrayOfString[1];
        arrayOfString = arrayOfString[2].split("\\*");
        if (arrayOfString.length > 1 && !str.isEmpty() && isValidDate(paramString))
          while (b < arrayOfString.length) {
            RemoteDiagnosticVO remoteDiagnosticVO = getFeatureInformation(arrayOfString[b]);
            if (remoteDiagnosticVO != null) {
              remoteDiagnosticVO.setDeviceId(str);
              remoteDiagnosticVO.setDtcDate(paramString);
              arrayList.add(remoteDiagnosticVO);
            } 
            b++;
          }  
      } 
    } 
    return arrayList;
  }
  
  public static List<RemoteDiagnosticVO> getResponseFromString(String paramString) {
    ArrayList<RemoteDiagnosticVO> arrayList = new ArrayList();
    if (paramString != null && !paramString.equals("")) {
      String[] arrayOfString = paramString.split("#");
      int i = arrayOfString.length;
      byte b = 1;
      if (i > 1)
        while (b < arrayOfString.length) {
          List<RemoteDiagnosticVO> list = getFeaturesByDevice(arrayOfString[b]);
          if (list != null && list.size() > 0)
            arrayList.addAll(list); 
          b++;
        }  
      return arrayList;
    } 
    return null;
  }
  
  private static boolean isValidDate(String paramString) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try {
      if (!paramString.isEmpty()) {
        bool1 = bool2;
        if (paramString.length() == 17) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(paramString.substring(0, 4));
          stringBuilder.append("-");
          stringBuilder.append(paramString.substring(4, 6));
          stringBuilder.append("-");
          stringBuilder.append(paramString.substring(6));
          String[] arrayOfString = Utilities.getUTCToNormalDate(stringBuilder.toString(), "dd/MM/yyyy HH:mm").split(" ");
          bool1 = bool2;
          if (arrayOfString.length > 1) {
            stringBuilder = new StringBuilder();
            this();
            stringBuilder.append(arrayOfString[0]);
            stringBuilder.append("\n");
            stringBuilder.append(arrayOfString[1]);
            boolean bool = stringBuilder.toString().isEmpty();
            bool1 = bool2;
            if (!bool)
              bool1 = true; 
          } 
        } 
      } 
    } catch (android.content.res.Resources.NotFoundException notFoundException) {
      Utilities.escribeArchivo("", "Error fecha", "getDateLabel");
      bool1 = bool2;
    } 
    return bool1;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    try {
      WsAccess wsAccess = new WsAccess();
      this(this.mContext);
      return wsAccess.getDtcs(this.TAG, this.mDeviceId);
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, "Error: ", exception.getMessage());
      return null;
    } 
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    Utilities.escribeArchivo(this.TAG, "Result: ", paramString);
    if (paramString != null && !paramString.isEmpty()) {
      List<RemoteDiagnosticVO> list = getResponseFromString(paramString);
      if (list != null && list.size() > 0) {
        if (Long.valueOf(this.dbFunctions.insertDTC(list, new android.database.sqlite.SQLiteDatabase[0])).longValue() > 0L) {
          this.baseListener.onSuccess(list);
        } else {
          this.baseListener.onFail("No affected rows");
        } 
      } else {
        this.baseListener.onFail(null);
      } 
    } else {
      this.baseListener.onFail(null);
    } 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    this.dbFunctions = new DBFunctions(this.mContext);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetDtcs_Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */