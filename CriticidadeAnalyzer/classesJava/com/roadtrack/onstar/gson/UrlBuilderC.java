package com.roadtrack.onstar.gson;

import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.utils.Utilities;
import java.net.URL;
import java.util.HashMap;

public class UrlBuilderC {
  private static final String API_KEY = GlobalMembers.GOOGLE_API_KEY;
  
  public static String TAG = "com.roadtrack.onstar.gson.UrlBuilderC";
  
  private HashMap<String, Object> params;
  
  private StringBuilder urlBuilder;
  
  private URL urlParams;
  
  public UrlBuilderC(String paramString, HashMap<String, Object> paramHashMap) {
    this.params = paramHashMap;
    this.urlBuilder = new StringBuilder(paramString);
  }
  
  public URL build() {
    try {
      if (this.params.size() > 0)
        this.params.put("key", API_KEY); 
      for (String str : this.params.keySet()) {
        StringBuilder stringBuilder = this.urlBuilder;
        stringBuilder.append(str);
        stringBuilder.append("=");
        stringBuilder.append(this.params.get(str));
        stringBuilder.append("&");
      } 
      this.urlBuilder.deleteCharAt(this.urlBuilder.length() - 1);
      URL uRL = new URL();
      this(this.urlBuilder.toString().trim());
      this.urlParams = uRL;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "build:", exception.getMessage());
    } 
    return this.urlParams;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/gson/UrlBuilderC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */