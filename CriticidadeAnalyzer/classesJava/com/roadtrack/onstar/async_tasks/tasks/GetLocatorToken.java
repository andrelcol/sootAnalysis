package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class GetLocatorToken extends AsyncTask<Void, Void, String> {
  public static final String TAG = GetRegistrationWeb.class.getSimpleName();
  
  private AsyncResponse asyncResponse = null;
  
  private Context context;
  
  private ProgressDialog progressDialog;
  
  public GetLocatorToken(Context paramContext, AsyncResponse paramAsyncResponse) {
    this.context = paramContext;
    this.asyncResponse = paramAsyncResponse;
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
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(jSONObject.getInt("userId"));
        stringBuilder.append("");
        linkedHashMap.put("userId", stringBuilder.toString());
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
      String str1 = wsAccess.getRegistrationInfoToken();
      String str2 = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(":");
      stringBuilder.append(str1);
      Utilities.escribeArchivo(str2, "WS result", stringBuilder.toString());
      LinkedHashMap<String, String> linkedHashMap = parseResponse(str1);
      return linkedHashMap.containsKey("access_token") ? linkedHashMap.get("access_token") : "";
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      return null;
    } 
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    try {
      this.progressDialog.dismiss();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Close Progress Dialog", exception.getMessage());
    } 
    AsyncResponse asyncResponse = this.asyncResponse;
    if (asyncResponse != null)
      asyncResponse.processFinish(paramString); 
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


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetLocatorToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */