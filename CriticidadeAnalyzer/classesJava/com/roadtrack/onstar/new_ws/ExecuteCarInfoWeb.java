package com.roadtrack.onstar.new_ws;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.SimpleRequestObject;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.pid.CarInfoWebViewActivity;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.io.IOException;

public class ExecuteCarInfoWeb extends AsyncTask<String, String, Object> {
  private String TAG = ExecuteCarInfoWeb.class.getSimpleName();
  
  AsyncResponse asyncResponse;
  
  private Context context;
  
  private ProgressDialog progressDialog;
  
  private int responseCodeLogin = 0;
  
  private StringsResourcesVO stringsResourcesVO;
  
  String urlCarInfo;
  
  private boolean userCancel;
  
  public ExecuteCarInfoWeb(Context paramContext, AsyncResponse paramAsyncResponse, String paramString) {
    this.context = paramContext;
    this.asyncResponse = paramAsyncResponse;
    this.urlCarInfo = paramString;
    onstarApplication onstarApplication = (onstarApplication)paramContext.getApplicationContext();
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  protected Object doInBackground(String... paramVarArgs) {
    Thread thread = Thread.currentThread();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(ExecuteCarInfoWeb.class.getSimpleName());
    stringBuilder.append(": ");
    stringBuilder.append(Thread.currentThread().getName());
    thread.setName(stringBuilder.toString());
    if (!NetUtilities.validateNetwork(this.context, false))
      return null; 
    try {
      SimpleRequestObject simpleRequestObject = new SimpleRequestObject();
      this(null, this.urlCarInfo, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF);
      RequestManager requestManager = new RequestManager();
      this(this.context, simpleRequestObject);
      requestManager.sendRequest(3);
      this.responseCodeLogin = requestManager.getResponseCode();
      requestManager.getRespuesta();
    } catch (IOException iOException) {
      Utilities.escribeArchivo(this.TAG, " IOException", iOException.getMessage());
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, " Exception", exception.getMessage());
    } 
    Utilities.escribeArchivo(this.TAG, "EXITAPP", "After unregisterDevice");
    return this.urlCarInfo;
  }
  
  public void onPostExecute(Object paramObject) {
    String str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    this.progressDialog.dismiss();
    AsyncResponse asyncResponse = this.asyncResponse;
    if (asyncResponse != null)
      if (paramObject != null) {
        asyncResponse.processFinish(paramObject.toString());
      } else {
        Toast.makeText(this.context, str, 1).show();
      }  
    if (this.responseCodeLogin == 200) {
      String str1 = paramObject.toString();
      try {
        String[] arrayOfString = str1.split("arg");
        if (arrayOfString.length > 1)
          paramObject.toString().contains(arrayOfString[0]); 
        Intent intent = new Intent();
        this(this.context.getApplicationContext(), CarInfoWebViewActivity.class);
        paramObject = new Bundle();
        super();
        intent.addFlags(268435456);
        paramObject.putString("url", str1);
        intent.putExtras((Bundle)paramObject);
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
    String str2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.please_wait, 2131690270);
    String str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    this.progressDialog = new ProgressDialog(this.context, 2131755173);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setCancelable(false);
    this.progressDialog.setMessage(str2);
    this.progressDialog.setButton(-2, str1, new DialogInterface.OnClickListener() {
          final ExecuteCarInfoWeb this$0;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            ExecuteCarInfoWeb.access$002(ExecuteCarInfoWeb.this, true);
            ExecuteCarInfoWeb.this.progressDialog.dismiss();
          }
        });
    this.progressDialog.show();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/new_ws/ExecuteCarInfoWeb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */