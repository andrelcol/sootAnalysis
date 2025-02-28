package com.roadtrack.onstar.new_ws;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.SimpleRequestObject;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ExecuteCreateAccount extends AsyncTask<String, String, Object> {
  private String TAG = ExecuteCreateAccount.class.getSimpleName();
  
  private Context context;
  
  private int responseCode = 0;
  
  public ExecuteCreateAccount(Context paramContext) {
    this.context = paramContext;
    onstarApplication onstarApplication = (onstarApplication)paramContext.getApplicationContext();
  }
  
  protected Object doInBackground(String... paramVarArgs) {
    StringBuilder stringBuilder1;
    Thread thread = Thread.currentThread();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(ExecuteCreateAccount.class.getSimpleName());
    stringBuilder2.append(": ");
    stringBuilder2.append(Thread.currentThread().getName());
    thread.setName(stringBuilder2.toString());
    boolean bool = NetUtilities.validateNetwork(this.context, false);
    stringBuilder2 = null;
    if (!bool)
      return null; 
    try {
      String str3 = Utilities.CryptAccount(paramVarArgs[0]);
      String str2 = Utilities.CryptAccount(paramVarArgs[1]);
      LinkedHashMap<Object, Object> linkedHashMap2 = new LinkedHashMap<Object, Object>();
      this();
      linkedHashMap2.put("var1", str3);
      linkedHashMap2.put("var2", str2);
      SimpleRequestObject simpleRequestObject = new SimpleRequestObject();
      this(linkedHashMap2, GlobalMembers.URL_CreateAccount, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF);
      simpleRequestObject.set_keyStoreId(2131623950, GlobalMembers.nameKeystoreService);
      LinkedHashMap<Object, Object> linkedHashMap1 = new LinkedHashMap<Object, Object>();
      this();
      linkedHashMap1.put("Content-Type", "application/x-www-form-urlencoded");
      simpleRequestObject.setRequest_propertys(linkedHashMap1);
      RequestManager requestManager = new RequestManager();
      this(this.context, simpleRequestObject);
      requestManager.sendRequest(2);
      this.responseCode = requestManager.getResponseCode();
      String str1 = requestManager.getRespuesta();
    } catch (IOException iOException) {
      Utilities.escribeArchivo(this.TAG, " IOException", iOException.getMessage());
      stringBuilder1 = stringBuilder2;
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, " Exception", exception.getMessage());
      stringBuilder1 = stringBuilder2;
    } 
    return stringBuilder1;
  }
  
  protected void onPostExecute(Object paramObject) {
    super.onPostExecute(paramObject);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    if (this.responseCode == 200) {
      try {
        Intent intent = new Intent();
        this("android.intent.action.VIEW", Uri.parse(paramObject.toString()));
        intent.addFlags(268435456);
        this.context.getApplicationContext().startActivity(intent);
      } catch (Exception exception) {
        Toast.makeText(this.context, str, 1).show();
      } 
    } else {
      Toast.makeText(this.context, str, 1).show();
    } 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
  }
  
  protected void onProgressUpdate(String... paramVarArgs) {
    super.onProgressUpdate((Object[])paramVarArgs);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/new_ws/ExecuteCreateAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */