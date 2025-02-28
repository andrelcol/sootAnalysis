package com.roadtrack.onstar.async_tasks.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.utils.Utilities;

public class GetOnstarClubAutenticate extends AsyncTask<Void, Void, String> {
  private static Context mContext;
  
  AsyncResponse asyncResponse;
  
  public GetOnstarClubAutenticate(Context paramContext, AsyncResponse paramAsyncResponse) {
    mContext = paramContext;
    this.asyncResponse = paramAsyncResponse;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    try {
      WsAccess wsAccess = new WsAccess();
      this(mContext);
      return wsAccess.getOnstarClubAutenticate();
    } catch (Exception exception) {
      Utilities.escribeArchivo("GetOnstarClubAutenticate", "Error: ", exception.getMessage());
      return null;
    } 
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    AsyncResponse asyncResponse = this.asyncResponse;
    if (asyncResponse != null)
      asyncResponse.processFinish(paramString); 
    Utilities.escribeArchivo("GetOnstarClubAutenticate", "Result: ", paramString);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetOnstarClubAutenticate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */