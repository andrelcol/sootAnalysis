package com.roadtrack.onstar.new_ws;

import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.SoapRequestObject;
import com.roadtrack.onstar.interfaces.OnLoginListener;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedHashMap;

public class ExecuteWS extends AsyncTask<String, String, Object> {
  public static String SERVICE_DOWN_CODE = "8";
  
  String TAG = ExecuteWS.class.getSimpleName();
  
  private String URL;
  
  OnLoginListener _onLoginListener;
  
  private String _strUUID;
  
  private Context context;
  
  int resCode = -1;
  
  private String resSoap = "";
  
  public ExecuteWS(Context paramContext) {
    this.context = paramContext;
  }
  
  protected Object doInBackground(String... paramVarArgs) {
    Thread thread = Thread.currentThread();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(ExecuteWS.class.getSimpleName());
    stringBuilder.append(": ");
    stringBuilder.append(Thread.currentThread().getName());
    thread.setName(stringBuilder.toString());
    if (!NetUtilities.validateNetwork(this.context, false))
      return null; 
    String str1 = GlobalMembers.NAMESPACE_WCF;
    this.URL = paramVarArgs[4];
    stringBuilder = new StringBuilder();
    stringBuilder.append(str1);
    stringBuilder.append("IService1/GetUserDeviceShortG2");
    String str2 = stringBuilder.toString();
    String str3 = paramVarArgs[5];
    String str4 = paramVarArgs[7];
    this._strUUID = paramVarArgs[0];
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    linkedHashMap.put("UUID", paramVarArgs[0]);
    linkedHashMap.put("login", paramVarArgs[1]);
    linkedHashMap.put("pass", paramVarArgs[2]);
    linkedHashMap.put("applicationID", paramVarArgs[3]);
    linkedHashMap.put("cellphoneId", paramVarArgs[6]);
    try {
      SoapRequestObject soapRequestObject = new SoapRequestObject();
      this(linkedHashMap, str2, "GetUserDeviceShortG2", str1, this.URL, 30000, 30000);
      soapRequestObject.setKeyStoreId(Integer.parseInt(str3), str4);
      RequestManager requestManager = new RequestManager();
      this(this.context, soapRequestObject);
      RequestManager.OnPostExecuteListener onPostExecuteListener = new RequestManager.OnPostExecuteListener() {
          final ExecuteWS this$0;
          
          public void onPostExecuteListener(String param1String, int param1Int) {
            ExecuteWS.access$002(ExecuteWS.this, param1String);
            ExecuteWS.this.resCode = param1Int;
          }
        };
      super(this);
      requestManager.setOnPostExecuteListener(onPostExecuteListener);
      requestManager.sendRequest(1);
      int i = this.resCode;
      return (i == -1) ? null : this.resSoap;
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, "Error: ", exception.getMessage());
      try {
        Thread.sleep(30000L);
      } catch (InterruptedException interruptedException) {
        Utilities.escribeArchivo(this.TAG, "Error: Timer", interruptedException.getMessage());
      } 
      return null;
    } 
  }
  
  public void getcontryandversion(String paramString) {
    String[] arrayOfString = paramString.split("\\|");
    if (arrayOfString.length < 4)
      return; 
    if (arrayOfString.length > 4 && arrayOfString[6] != null && arrayOfString[5] != null) {
      GlobalMembers.version = arrayOfString[6].toString();
      GlobalMembers.codeCountry = arrayOfString[5].toString();
      if (arrayOfString[7] != null && !arrayOfString[7].equals("#CR1"))
        GlobalMembers.versionStatus = arrayOfString[7].toString(); 
    } 
  }
  
  public void onPostExecute(Object paramObject) {
    if (!GlobalMembers.responseLoginOK) {
      if (paramObject == null || paramObject.toString().equals("Network error")) {
        this._onLoginListener.onLoginFailConection(this.URL);
        return;
      } 
      paramObject = paramObject.toString();
      if (paramObject.contains("PI:1016")) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PI:1016,;1|");
        stringBuilder.append(this._strUUID);
        stringBuilder.append("|");
        paramObject = paramObject.replace("PI:1016,;1|", stringBuilder.toString());
        getcontryandversion((String)paramObject);
        if (paramObject.contains("#CR")) {
          String[] arrayOfString = paramObject.split("#");
          if (arrayOfString != null && arrayOfString.length > 1) {
            arrayOfString = arrayOfString[0].split("\\|");
            if (arrayOfString.length >= 9)
              if (arrayOfString[8].equals("1")) {
                GlobalMembers.flagShowWebViews = true;
              } else {
                GlobalMembers.flagShowWebViews = false;
              }  
          } 
          String str = paramObject.replace("|#CR", "#CR").replace("#CR", "\r");
          if (str.indexOf("\r") < 0) {
            GlobalMembers.bRespuestaNoRecibida = false;
            if (str != null && str.length() >= 10)
              if (str.substring(9, 10).equals("2")) {
                this._onLoginListener.onLoginFailAccountOrPass(this.URL, str);
              } else if (str.substring(9, 10).equals(SERVICE_DOWN_CODE)) {
                this._onLoginListener.onLoginServiceDown(this.URL, str);
              }  
          } else if (str.substring(9, 10).equals(SERVICE_DOWN_CODE)) {
            this._onLoginListener.onLoginServiceDown(this.URL, str);
          } else {
            paramObject = str.substring(0, str.indexOf("\r"));
            str = str.substring(str.indexOf("\r") + 1);
            if ((paramObject.contains("|") | str.contains("|")) != 0) {
              GlobalMembers.responseLoginOK = true;
              GlobalMembers.activeSession = true;
              this._onLoginListener.onLoginOK(this.URL, (String)paramObject, str);
            } else {
              this._onLoginListener.onLoginServiceUnavailable(this.URL);
            } 
          } 
        } else if (paramObject != null && paramObject.length() >= 10) {
          if (paramObject.substring(9, 10).equals("2")) {
            this._onLoginListener.onLoginFailAccountOrPass(this.URL, (String)paramObject);
          } else if (paramObject.substring(9, 10).equals(SERVICE_DOWN_CODE)) {
            this._onLoginListener.onLoginServiceDown(this.URL, (String)paramObject);
          } 
        } 
      } else {
        this._onLoginListener.onLoginServiceUnavailable(this.URL);
      } 
    } else {
      GlobalMembers.responseLoginOK = false;
    } 
  }
  
  public void setOnLoginListener(OnLoginListener paramOnLoginListener) {
    this._onLoginListener = paramOnLoginListener;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/new_ws/ExecuteWS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */