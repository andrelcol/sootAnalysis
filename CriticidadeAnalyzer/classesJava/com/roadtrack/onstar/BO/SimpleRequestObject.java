package com.roadtrack.onstar.BO;

import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedHashMap;

public class SimpleRequestObject {
  private final String USER_AGENT = Utilities.Decrypt("mZzuQbXNdt80z8Pp6LuKMg==");
  
  private int _connection_time_out = 10000;
  
  private int _keyStoreId = -1;
  
  private String _nameKeyStore = null;
  
  private int _read_time_out = 10000;
  
  private String _url = "";
  
  private LinkedHashMap<String, Object> params;
  
  private StringBuilder paramsStr = new StringBuilder();
  
  private LinkedHashMap<String, Object> properties;
  
  private LinkedHashMap<String, String> request_properties;
  
  public SimpleRequestObject(LinkedHashMap<String, Object> paramLinkedHashMap, String paramString, int paramInt1, int paramInt2) {
    this.params = paramLinkedHashMap;
    this._url = paramString;
    this._connection_time_out = paramInt1;
    this._read_time_out = paramInt2;
    this.request_properties = new LinkedHashMap<String, String>();
  }
  
  public SimpleRequestObject(LinkedHashMap<String, Object> paramLinkedHashMap, LinkedHashMap<String, String> paramLinkedHashMap1, String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2) {
    this.params = paramLinkedHashMap;
    this.request_properties = paramLinkedHashMap1;
    this._url = paramString1;
    this._connection_time_out = paramInt1;
    this._read_time_out = paramInt2;
    this._keyStoreId = paramInt3;
    this._nameKeyStore = paramString2;
    new LinkedHashMap<Object, Object>();
  }
  
  private boolean verifyParameters() {
    return (get_url() == null || get_url().equals("")) ? false : (!(getParams() == null));
  }
  
  public String buildSimpleRequest() {
    if (!verifyParameters())
      return null; 
    for (String str : this.params.keySet()) {
      this.paramsStr.append(str);
      this.paramsStr.append("=");
      this.paramsStr.append(this.params.get(str));
      this.paramsStr.append("&");
    } 
    StringBuilder stringBuilder = this.paramsStr;
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    return this.paramsStr.toString();
  }
  
  public String buildSimpleRequest2() {
    if (!verifyParameters())
      return null; 
    this.paramsStr.append("{");
    for (String str : this.params.keySet()) {
      this.paramsStr.append("\"");
      this.paramsStr.append(str);
      this.paramsStr.append("\":\"");
      this.paramsStr.append(this.params.get(str));
      this.paramsStr.append("\",");
    } 
    StringBuilder stringBuilder = this.paramsStr;
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    this.paramsStr.append("}");
    return this.paramsStr.toString();
  }
  
  public LinkedHashMap<String, Object> getParams() {
    return this.params;
  }
  
  public LinkedHashMap<String, Object> getProperties() {
    return this.properties;
  }
  
  public LinkedHashMap<String, String> getRequest_propertys() {
    LinkedHashMap<String, String> linkedHashMap = this.request_properties;
    if (linkedHashMap == null || linkedHashMap.size() <= 0) {
      this.request_properties.put("User-Agent", this.USER_AGENT);
      this.request_properties.put("Accept-Language", "en-US,en;q=0.5");
    } 
    return this.request_properties;
  }
  
  public int get_connection_time_out() {
    return this._connection_time_out;
  }
  
  public int get_keyStoreId() {
    return this._keyStoreId;
  }
  
  public String get_nameKeyStore() {
    return this._nameKeyStore;
  }
  
  public int get_read_time_out() {
    return this._read_time_out;
  }
  
  public String get_url() {
    return this._url;
  }
  
  public void setProperties(LinkedHashMap<String, Object> paramLinkedHashMap) {
    this.properties = paramLinkedHashMap;
  }
  
  public void setRequest_propertys(LinkedHashMap<String, String> paramLinkedHashMap) {
    this.request_properties = paramLinkedHashMap;
  }
  
  public void set_keyStoreId(int paramInt, String paramString) {
    this._keyStoreId = paramInt;
    this._nameKeyStore = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/SimpleRequestObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */