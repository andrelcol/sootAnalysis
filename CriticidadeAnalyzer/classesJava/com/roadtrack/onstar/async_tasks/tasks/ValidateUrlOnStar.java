package com.roadtrack.onstar.async_tasks.tasks;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ValidateUrlOnStar extends AsyncTask<Void, Void, Object> {
  private String TAG = ValidateUrlOnStar.class.getSimpleName();
  
  private Context context;
  
  private Dialog dialog;
  
  private ProgressDialog progressDialog;
  
  private int responseCodeLogin = 0;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private boolean userCancel;
  
  public ValidateUrlOnStar(Context paramContext, Dialog paramDialog) {
    this.context = paramContext;
    this.dialog = paramDialog;
    onstarApplication onstarApplication = (onstarApplication)paramContext.getApplicationContext();
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  protected Object doInBackground(Void... paramVarArgs) {
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
      this(null, GlobalMembers.URL_ChangeEmail, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF);
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
      str2 = requestManager.getRespuesta();
      str1 = str2;
      String str4 = str2;
      String str5 = this.TAG;
      str1 = str2;
      str4 = str2;
      StringBuilder stringBuilder = new StringBuilder();
      str1 = str2;
      str4 = str2;
      this();
      str1 = str2;
      str4 = str2;
      stringBuilder.append(this.responseCodeLogin);
      str1 = str2;
      str4 = str2;
      stringBuilder.append("");
      str1 = str2;
      str4 = str2;
      Utilities.escribeArchivo(str5, " Resultado", stringBuilder.toString());
      str1 = str2;
      str4 = str2;
      if (this.responseCodeLogin == 200) {
        str1 = str2;
        str4 = str2;
        this.progressDialog.dismiss();
        str1 = str2;
        str4 = str2;
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        str1 = str2;
        str4 = str2;
        this();
        str1 = str2;
        str4 = str2;
        hashMap.put("URL", GlobalMembers.URL_ChangeEmail);
        try {
          Intent intent = new Intent();
          this("android.intent.action.VIEW");
          intent.setData(Uri.parse(GlobalMembers.URL_ChangeEmail));
          this.context.startActivity(intent);
        } catch (Exception exception1) {
          str1 = str2;
          str4 = str2;
          Utilities.escribeArchivo(this.TAG, "Error", exception1.getMessage());
        } 
        str1 = str2;
        str4 = str2;
        this.dialog.dismiss();
        str4 = str2;
      } else {
        str1 = str2;
        str4 = str2;
        Toast.makeText(this.context, str3, 1).show();
        str4 = str2;
      } 
    } catch (IOException iOException) {
      Utilities.escribeArchivo(this.TAG, " IOException", iOException.getMessage());
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, " Exception", exception.getMessage());
      exception = iOException;
    } 
    Utilities.escribeArchivo(this.TAG, "EXITAPP", "After unregisterDevice");
    return exception;
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    String str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.please_wait, 2131690270);
    String str2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    this.progressDialog = new ProgressDialog(this.context, 2131755173);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setCancelable(false);
    this.progressDialog.setMessage(str1);
    this.progressDialog.setButton(-2, str2, new DialogInterface.OnClickListener() {
          final ValidateUrlOnStar this$0;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            ValidateUrlOnStar.access$002(ValidateUrlOnStar.this, true);
            ValidateUrlOnStar.this.progressDialog.dismiss();
          }
        });
    this.progressDialog.show();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/ValidateUrlOnStar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */