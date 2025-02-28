package com.roadtrack.onstar.async_tasks.tasks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;

public class GetOnStarClubURL extends AsyncTask<Void, Void, String> {
  private final String TAG = SetGmt_Task.class.getSimpleName();
  
  private Context mContext;
  
  public GetOnStarClubURL(Context paramContext) {
    this.mContext = paramContext;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    if (!NetUtilities.validateNetwork(this.mContext, false))
      return null; 
    try {
      WsAccess wsAccess = new WsAccess();
      this(this.mContext);
      String str = wsAccess.getOnStartClubURL();
      Utilities.escribeArchivo(this.TAG, "Result: ", str);
      return str;
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, "Error: ", exception.getMessage());
      return null;
    } 
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    try {
      String[] arrayOfString = paramString.split("\\|");
      if (arrayOfString[2] != null) {
        String str = arrayOfString[2].toString();
        Intent intent = new Intent();
        this("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
      } else {
        StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
        this();
        String str = Utilities.getStringFromConfigList(this.mContext, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
        Toast.makeText(this.mContext, str, 1).show();
      } 
    } catch (Exception exception) {
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      String str = Utilities.getStringFromConfigList(this.mContext, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
      Toast.makeText(this.mContext, str, 1).show();
    } 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetOnStarClubURL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */