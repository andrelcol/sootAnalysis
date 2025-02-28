package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.SimpleRequestObject;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.VO.DTCConnectionVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponseList;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class GetURLPaymentsHistoryTask extends AsyncTask<String, String, List<String>> {
  public static final String TAG = GetURLPaymentsHistoryTask.class.getSimpleName();
  
  private String GMTID;
  
  private AsyncResponseList asyncResponse = null;
  
  private Context context;
  
  private ProgressDialog progressDialog;
  
  public GetURLPaymentsHistoryTask(Context paramContext, AsyncResponseList paramAsyncResponseList, String paramString) {
    this.context = paramContext;
    this.asyncResponse = paramAsyncResponseList;
    this.GMTID = paramString;
  }
  
  private boolean isValidResponseStructure(List<String> paramList) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramList != null) {
      bool1 = bool2;
      if (paramList.size() > 0) {
        bool1 = bool2;
        if (paramList.get(0) != null)
          bool1 = true; 
      } 
    } 
    return bool1;
  }
  
  private String replaceCharacter(String paramString) {
    return paramString.replace("\n", "").trim().replace("+", "-").replace("/", "_");
  }
  
  protected List<String> doInBackground(String... paramVarArgs) {
    ArrayList<Exception> arrayList = new ArrayList();
    Context context = this.context;
    int i = 0;
    byte b = 0;
    if (!NetUtilities.validateNetwork(context, false))
      return null; 
    try {
      WsAccess wsAccess = new WsAccess();
      this(this.context);
      String str3 = paramVarArgs[0];
      String str2 = paramVarArgs[1];
      StringBuilder stringBuilder3 = new StringBuilder();
      this();
      stringBuilder3.append(str3);
      stringBuilder3.append("[]");
      stringBuilder3.append(str2);
      stringBuilder3.append("Ubiko");
      str2 = replaceCharacter(Utilities.Crypt(stringBuilder3.toString()));
      str3 = paramVarArgs[2];
      String str4 = paramVarArgs[3];
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append(str3);
      stringBuilder1.append("-");
      stringBuilder1.append(str4);
      Utilities.Crypt(stringBuilder1.toString()).replace("\n", "").trim().replace("+", "-").replace("/", "_");
      int j = b;
      int k = i;
      try {
        LinkedHashMap<Object, Object> linkedHashMap2 = new LinkedHashMap<Object, Object>();
        j = b;
        k = i;
        this();
        j = b;
        k = i;
        linkedHashMap2.put("grant_type", "password");
        j = b;
        k = i;
        linkedHashMap2.put("clientId", str2);
        j = b;
        k = i;
        SimpleRequestObject simpleRequestObject = new SimpleRequestObject();
        j = b;
        k = i;
        this(linkedHashMap2, GlobalMembers.URL_DTC_LocatorWebApiLogin, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF);
        j = b;
        k = i;
        simpleRequestObject.set_keyStoreId(2131623959, GlobalMembers.nameKeystoreServiceDTC);
        j = b;
        k = i;
        LinkedHashMap<Object, Object> linkedHashMap1 = new LinkedHashMap<Object, Object>();
        j = b;
        k = i;
        this();
        j = b;
        k = i;
        linkedHashMap1.put("Content-Type", "application/x-www-form-urlencoded");
        j = b;
        k = i;
        simpleRequestObject.setRequest_propertys(linkedHashMap1);
        j = b;
        k = i;
        RequestManager requestManager = new RequestManager();
        j = b;
        k = i;
        this(this.context, simpleRequestObject);
        j = b;
        k = i;
        requestManager.sendRequest(2);
        j = b;
        k = i;
        i = requestManager.getResponseCode();
        j = i;
        k = i;
        String str = requestManager.getRespuesta();
        j = i;
      } catch (IOException null) {
        Utilities.escribeArchivo(TAG, " IOException", exception.getMessage());
        j = k;
        exception = null;
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, " Exception", exception.getMessage());
        exception = null;
      } 
      Utilities.escribeArchivo(TAG, "EXITAPP", "After unregisterDevice");
      if (j == 200) {
        DTCConnectionVO dTCConnectionVO = new DTCConnectionVO();
        this();
        if (exception != null) {
          try {
            JSONObject jSONObject = new JSONObject();
            this((String)exception);
            dTCConnectionVO.setAccess_token(jSONObject.getString("access_token"));
            String str = jSONObject.getString("access_token");
            try {
              dTCConnectionVO.setExpires(jSONObject.getString("expires"));
              dTCConnectionVO.setExpires_in(jSONObject.getInt("expires_in"));
              dTCConnectionVO.setIssues(jSONObject.getString("issued"));
              dTCConnectionVO.setToken_type(jSONObject.getString("token_type"));
              dTCConnectionVO.setUserId(jSONObject.getInt("userId"));
              dTCConnectionVO.setUserName(jSONObject.getString("userName"));
            } catch (JSONException null) {}
          } catch (JSONException jSONException) {
            exception = null;
          } 
          Utilities.escribeArchivo(TAG, " JSONException parseo del token", jSONException.getMessage());
          jSONException.printStackTrace();
        } 
      } 
      exception = null;
      arrayList.add(exception);
      String str1 = wsAccess.getURLPaymentHistory((String)exception, this.GMTID);
      arrayList.add(str1);
      str2 = TAG;
      StringBuilder stringBuilder2 = new StringBuilder();
      this();
      stringBuilder2.append(":");
      stringBuilder2.append(str1);
      Utilities.escribeArchivo(str2, "WS result", stringBuilder2.toString());
      return (List)arrayList;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      return null;
    } 
  }
  
  protected void onPostExecute(List<String> paramList) {
    try {
      this.progressDialog.dismiss();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Close Progress Dialog", exception.getMessage());
    } 
    List<String> list = paramList;
    if (!isValidResponseStructure(paramList))
      list = null; 
    AsyncResponseList asyncResponseList = this.asyncResponse;
    if (asyncResponseList != null)
      asyncResponseList.processFinish(list); 
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


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetURLPaymentsHistoryTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */