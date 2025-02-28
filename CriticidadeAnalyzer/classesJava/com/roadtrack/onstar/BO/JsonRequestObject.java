package com.roadtrack.onstar.BO;

import com.roadtrack.onstar.gson.GsonC;
import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedHashMap;
import java.util.List;

public class JsonRequestObject {
  private int _connection_time_out = 10000;
  
  private String _fullUrl = "";
  
  private String _namespace = "";
  
  private int _read_time_out = 10000;
  
  private int keyStoreId = -1;
  
  private String nameKeyStore = null;
  
  private List params;
  
  private boolean propertysAsSet = false;
  
  private LinkedHashMap<String, String> request_propertys;
  
  public JsonRequestObject(String paramString1, String paramString2, List paramList, int paramInt1, int paramInt2) {
    Utilities.Decrypt("mZzuQbXNdt80z8Pp6LuKMg==");
    this.params = paramList;
    this._read_time_out = paramInt1;
    this._connection_time_out = paramInt2;
    this._namespace = paramString2;
    this.request_propertys = new LinkedHashMap<String, String>();
    set_fullUrl(paramString1);
  }
  
  public String buildJsonRequest() {
    String str = (new GsonC()).toJsonString(this.params);
    str = str.substring(1, str.length() - 1);
    Utilities.escribeArchivo("JsonRequestObject", "JsonRequest", str);
    if (this.params.get(0).equals("TC"))
      str = this.params.get(1); 
    return str;
  }
  
  public int getKeyStoreId() {
    return this.keyStoreId;
  }
  
  public String getNameKeyStore() {
    return this.nameKeyStore;
  }
  
  public LinkedHashMap<String, String> getRequest_propertys() {
    LinkedHashMap<String, String> linkedHashMap = this.request_propertys;
    if (linkedHashMap == null || linkedHashMap.size() <= 0) {
      this.request_propertys.put("Content-Type", "application/json");
      this.request_propertys.put("Accept", "application/json");
    } 
    return this.request_propertys;
  }
  
  public int get_connection_time_out() {
    return this._connection_time_out;
  }
  
  public String get_fullUrl() {
    return this._fullUrl;
  }
  
  public int get_read_time_out() {
    return this._read_time_out;
  }
  
  public boolean isPropertysAsSet() {
    return this.propertysAsSet;
  }
  
  public void setKeyStoreId(int paramInt, String paramString) {
    this.keyStoreId = paramInt;
    this.nameKeyStore = paramString;
  }
  
  public void setPropertysAsSet(boolean paramBoolean) {
    this.propertysAsSet = paramBoolean;
  }
  
  public void setRequest_propertys(LinkedHashMap<String, String> paramLinkedHashMap) {
    this.request_propertys = paramLinkedHashMap;
  }
  
  public void set_fullUrl(String paramString) {
    if (this._namespace.isEmpty()) {
      this._fullUrl = paramString;
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append(this._namespace);
      this._fullUrl = stringBuilder.toString();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/JsonRequestObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */