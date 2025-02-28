package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class GetRegistrationWeb extends AsyncTask<Void, Void, String> {
  public static final String TAG = GetRegistrationWeb.class.getSimpleName();
  
  private AsyncResponse asyncResponse = null;
  
  private Context context;
  
  private LinkedHashMap<String, Object> params;
  
  private ProgressDialog progressDialog;
  
  public GetRegistrationWeb(Context paramContext, AsyncResponse paramAsyncResponse, LinkedHashMap<String, Object> paramLinkedHashMap) {
    this.context = paramContext;
    this.asyncResponse = paramAsyncResponse;
    this.params = paramLinkedHashMap;
  }
  
  private String buildFullURL(LinkedHashMap<String, String> paramLinkedHashMap) {
    String str2 = "";
    String str1 = str2;
    if (paramLinkedHashMap != null) {
      String str = str2;
      str1 = str2;
      try {
        if (paramLinkedHashMap.size() > 0) {
          String str4;
          String str5;
          String str6;
          String str7;
          String str8;
          String str9;
          String str10;
          String str11;
          str = str2;
          if (this.params.get("usr") != null) {
            str = str2;
            str1 = formatParameter(this.params.get("usr").toString());
          } else {
            str1 = "";
          } 
          str = str2;
          if (this.params.get("pass") != null) {
            str = str2;
            str4 = formatParameter(this.params.get("pass").toString());
          } else {
            str4 = "";
          } 
          str = str2;
          if (this.params.get("devId") != null) {
            str = str2;
            str5 = formatParameter(this.params.get("devId").toString());
          } else {
            str5 = "";
          } 
          str = str2;
          if (this.params.get("newPlan") != null) {
            str = str2;
            str6 = formatParameter(this.params.get("newPlan").toString());
          } else {
            str6 = "";
          } 
          str = str2;
          if (this.params.get("service") != null) {
            str = str2;
            str7 = formatParameter(this.params.get("service").toString());
          } else {
            str7 = "";
          } 
          str = str2;
          if (this.params.get("salesPolitic") != null) {
            str = str2;
            str8 = formatParameter(this.params.get("salesPolitic").toString());
          } else {
            str8 = "";
          } 
          str = str2;
          if (this.params.get("paymentMethod") != null) {
            str = str2;
            str9 = formatParameter(this.params.get("paymentMethod").toString());
          } else {
            str9 = "";
          } 
          str = str2;
          if (this.params.get("currentPlan") != null) {
            str = str2;
            str10 = formatParameter(this.params.get("currentPlan").toString());
          } else {
            str10 = "";
          } 
          str = str2;
          if (this.params.get("renewalKind") != null) {
            str = str2;
            str11 = formatParameter(this.params.get("renewalKind").toString());
          } else {
            str11 = "";
          } 
          str = str2;
          String str3 = URLEncoder.encode(paramLinkedHashMap.get("access_token"), "UTF-8");
          str = str2;
          str1 = String.format(GlobalMembers.RegistrationWeb, new Object[] { str1, str4, str5, str6, str7, str8, str9, str10, str11, str3 });
          str = str1;
          Utilities.escribeArchivo(TAG, "loadRegistrationInfo", str1);
        } 
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "loadRegistrationInfo", exception.getMessage());
        str1 = str;
      } 
    } 
    return str1;
  }
  
  private boolean isValidResponseStructure(String paramString) {
    if (paramString != null && !paramString.equals("")) {
      boolean bool1;
      try {
        bool1 = true;
      } catch (MalformedURLException malformedURLException) {
        Utilities.escribeArchivo(TAG, "Error en URL", malformedURLException.getMessage());
        bool1 = false;
      } 
      return bool1;
    } 
    boolean bool = false;
  }
  
  private LinkedHashMap<String, String> parseResponse(String paramString) {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    if (paramString != null)
      try {
        JSONObject jSONObject = new JSONObject();
        this(paramString);
        linkedHashMap.put("access_token", jSONObject.getString("access_token"));
        linkedHashMap.put("expires", jSONObject.getString("expires"));
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(jSONObject.getInt("expires_in"));
        stringBuilder.append("");
        linkedHashMap.put("expires_in", stringBuilder.toString());
        linkedHashMap.put("issued", jSONObject.getString("issued"));
        linkedHashMap.put("token_type", jSONObject.getString("token_type"));
        linkedHashMap.put("userId", jSONObject.getString("userId"));
        linkedHashMap.put("userName", jSONObject.getString("userName"));
      } catch (JSONException jSONException) {
        Utilities.escribeArchivo(TAG, "parseResponse error", jSONException.getMessage());
      }  
    return (LinkedHashMap)linkedHashMap;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    if (!NetUtilities.validateNetwork(this.context, false))
      return null; 
    try {
      WsAccess wsAccess = new WsAccess();
      this(this.context);
      String str = wsAccess.getRegistrationInfoToken();
      null = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(":");
      stringBuilder.append(str);
      Utilities.escribeArchivo(null, "WS result", stringBuilder.toString());
      return buildFullURL(parseResponse(str));
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      return null;
    } 
  }
  
  public String formatParameter(String paramString) {
    String str;
    try {
      paramString = Utilities.Crypt(paramString);
      boolean bool = paramString.endsWith("\n");
      if (bool) {
        paramString = URLEncoder.encode(paramString.substring(0, paramString.length() - 1), "UTF-8");
      } else {
        paramString = URLEncoder.encode(paramString, "UTF-8");
      } 
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      unsupportedEncodingException.printStackTrace();
      str = "";
    } 
    return str;
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    try {
      this.progressDialog.dismiss();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Close Progress Dialog", exception.getMessage());
    } 
    String str = paramString;
    if (!isValidResponseStructure(paramString))
      str = null; 
    AsyncResponse asyncResponse = this.asyncResponse;
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


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetRegistrationWeb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */