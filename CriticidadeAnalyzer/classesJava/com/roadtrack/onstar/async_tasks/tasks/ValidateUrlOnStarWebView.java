package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.SimpleRequestObject;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ValidateUrlOnStarWebView extends AsyncTask<Void, Void, String> {
  private String TAG = ValidateUrlOnStarWebView.class.getSimpleName();
  
  private String URL;
  
  private WebView Wv;
  
  private Context context;
  
  private ProgressDialog progressDialog;
  
  private int responseCodeLogin = 0;
  
  private StringsResourcesVO stringsResourcesVO;
  
  public ValidateUrlOnStarWebView(Context paramContext, String paramString, WebView paramWebView) {
    this.context = paramContext;
    this.URL = paramString;
    this.Wv = paramWebView;
    onstarApplication onstarApplication = (onstarApplication)paramContext.getApplicationContext();
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    String str3 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    boolean bool = NetUtilities.validateNetwork(this.context, false);
    Void[] arrayOfVoid = null;
    String str2 = null;
    if (!bool)
      return null; 
    String str1 = str2;
    paramVarArgs = arrayOfVoid;
    try {
      SimpleRequestObject simpleRequestObject = new SimpleRequestObject();
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      this(null, this.URL, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF);
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      simpleRequestObject.set_keyStoreId(2131623959, GlobalMembers.nameKeystoreServiceDTC);
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      this();
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      linkedHashMap.put("Content-Type", "application/x-www-form-urlencoded");
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      simpleRequestObject.setRequest_propertys(linkedHashMap);
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      RequestManager requestManager = new RequestManager();
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      this(this.context, simpleRequestObject);
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      requestManager.sendRequest(3);
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      this.responseCodeLogin = requestManager.getResponseCode();
      str1 = str2;
      paramVarArgs = arrayOfVoid;
      String str5 = requestManager.getRespuesta();
      str1 = str5;
      String str4 = str5;
      str2 = this.TAG;
      str1 = str5;
      str4 = str5;
      StringBuilder stringBuilder = new StringBuilder();
      str1 = str5;
      str4 = str5;
      this();
      str1 = str5;
      str4 = str5;
      stringBuilder.append(this.responseCodeLogin);
      str1 = str5;
      str4 = str5;
      stringBuilder.append("");
      str1 = str5;
      str4 = str5;
      Utilities.escribeArchivo(str2, " Resultado", stringBuilder.toString());
      str1 = str5;
      str4 = str5;
      if (this.responseCodeLogin == 200) {
        str1 = str5;
        str4 = str5;
        this.progressDialog.dismiss();
        str1 = str5;
        str4 = str5;
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        str1 = str5;
        str4 = str5;
        this();
        str1 = str5;
        str4 = str5;
        hashMap.put("URL", this.URL);
        str1 = str5;
        str4 = str5;
        return this.URL;
      } 
      str1 = str5;
      str4 = str5;
      Toast.makeText(this.context, str3, 1).show();
      str4 = str5;
    } catch (IOException iOException) {
      Utilities.escribeArchivo(this.TAG, " IOException", iOException.getMessage());
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, " Exception", exception.getMessage());
      exception = iOException;
    } 
    Utilities.escribeArchivo(this.TAG, "EXITAPP", "After unregisterDevice");
    return (String)exception;
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    String str = this.TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Url: web.loadUrl: ");
    stringBuilder.append(paramString);
    Utilities.escribeArchivo(str, "Urls", stringBuilder.toString());
    this.Wv.loadUrl(paramString);
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    String str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.please_wait, 2131690270);
    Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    this.progressDialog = new ProgressDialog(this.context, 2131755173);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setCancelable(false);
    this.progressDialog.setMessage(str);
    this.progressDialog.show();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/ValidateUrlOnStarWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */