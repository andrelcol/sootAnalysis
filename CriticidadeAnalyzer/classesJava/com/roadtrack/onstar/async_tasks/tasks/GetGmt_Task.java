package com.roadtrack.onstar.async_tasks.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.GMTCatalog;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.gson.GsonC;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.util.List;

public class GetGmt_Task extends AsyncTask<Void, Void, String> {
  private final String TAG = GetGmt_Task.class.getSimpleName();
  
  private Base_Interface mBase_Interface;
  
  private Context mContext;
  
  private DBFunctions mDbFunctions;
  
  private GsonC mGsonC;
  
  public GetGmt_Task(Context paramContext, Base_Interface paramBase_Interface) {
    this.mContext = paramContext;
    this.mBase_Interface = paramBase_Interface;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    if (!NetUtilities.validateNetwork(this.mContext, false))
      return null; 
    try {
      WsAccess wsAccess = new WsAccess();
      this(this.mContext);
      String str = wsAccess.getGmt(null, null);
      Utilities.escribeArchivo(this.TAG, "Result: ", str);
      return str;
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, "Error: ", exception.getMessage());
      return null;
    } 
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    if (paramString != null && !paramString.isEmpty()) {
      paramString = paramString.replace("|", "");
      List list = this.mGsonC.parseGTMCatalogJsonString(paramString);
      this.mDbFunctions.removeAllGmtCatalog();
      for (GMTCatalog gMTCatalog : list)
        this.mDbFunctions.addGmtRegister(gMTCatalog, new android.database.sqlite.SQLiteDatabase[0]); 
      this.mBase_Interface.onSuccess(paramString);
    } else {
      this.mBase_Interface.onFail(paramString);
    } 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    this.mDbFunctions = new DBFunctions(this.mContext);
    this.mGsonC = new GsonC();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetGmt_Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */