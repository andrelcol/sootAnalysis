package com.roadtrack.onstar.async_tasks;

import android.os.AsyncTask;
import android.webkit.WebView;
import com.roadtrack.onstar.utils.Utilities;

public class TaskWb extends AsyncTask<Void, Void, String> {
  private String liga;
  
  private WebView wvGeneric;
  
  public TaskWb(WebView paramWebView, String paramString) {
    this.wvGeneric = paramWebView;
    this.liga = paramString;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    return this.liga;
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Url: web.loadUrl: ");
    stringBuilder.append(paramString);
    Utilities.escribeArchivo("TAG", "Urls", stringBuilder.toString());
    this.wvGeneric.loadUrl(paramString);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/TaskWb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */