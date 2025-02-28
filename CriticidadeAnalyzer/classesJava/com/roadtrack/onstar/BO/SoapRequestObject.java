package com.roadtrack.onstar.BO;

import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedHashMap;

public class SoapRequestObject {
  private final String USER_AGENT = Utilities.Decrypt("mZzuQbXNdt80z8Pp6LuKMg==");
  
  private int _connection_time_out = 10000;
  
  private String _methodName = "";
  
  private String _namespace = "";
  
  private int _read_time_out = 10000;
  
  private String _request_tag = Utilities.Decrypt("CAVkQFWjcOSa2lWhP9mIgA==");
  
  private String _response_tag = Utilities.Decrypt("WZDgsihTdTS3pziOkMUIdA==");
  
  private String _soapAction = "";
  
  private String _url = "";
  
  private String envelope_schema = Utilities.Decrypt("H6JjTBN1uI/wOXBNkvEFTcKRijRVWHdZTIJDwrNZ8zrXToOmUYAM9LLJoLcCIM8v");
  
  private int keyStoreId = -1;
  
  private String nameKeyStore = null;
  
  private LinkedHashMap<String, Object> params;
  
  private StringBuilder paramsStr = new StringBuilder();
  
  private LinkedHashMap<String, String> request_propertys;
  
  public SoapRequestObject(LinkedHashMap<String, Object> paramLinkedHashMap, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2) {
    this.params = paramLinkedHashMap;
    this._soapAction = paramString1;
    this._methodName = paramString2;
    this._namespace = paramString3;
    this._url = paramString4;
    this._connection_time_out = paramInt1;
    this._read_time_out = paramInt2;
    this.request_propertys = new LinkedHashMap<String, String>();
  }
  
  private boolean verifyParameters() {
    return !(get_soapAction().trim().length() == 0 || get_soapAction().equals("") || get_soapAction() == null || get_namespace().trim().length() == 0 || get_namespace().equals("") || get_namespace() == null || get_methodName().trim().length() == 0 || get_methodName().equals("") || get_methodName() == null || get_request_tag().trim().length() == 0 || get_request_tag().equals("") || get_request_tag() == null);
  }
  
  public String buildSoapRequest() {
    if (!verifyParameters())
      return null; 
    StringBuilder stringBuilder1 = new StringBuilder();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(Utilities.Decrypt("IiN05L+Ih+nkE40RyLgrqFLo+iD0x3DkA77rVzlrOLfibeeCDHOdiATl0jekLyNS"));
    stringBuilder2.append("\n");
    stringBuilder1.append(stringBuilder2.toString());
    stringBuilder1.append(Utilities.Decrypt("S0vmU7k98ZMfX656pEfAFMMWsgv+jY/3CGHow5qpY5JxSbQeH9OPxqbeW9mrhHNi"));
    stringBuilder1.append(this.envelope_schema);
    stringBuilder1.append("\" ");
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append("xmlns:tem=\"");
    stringBuilder2.append(get_namespace());
    stringBuilder2.append("\">\n");
    stringBuilder1.append(stringBuilder2.toString());
    stringBuilder1.append("<soapenv:Body>");
    stringBuilder1.append("\n<tem:");
    stringBuilder1.append(get_methodName());
    stringBuilder1.append(">\n");
    stringBuilder1.append("<tem:");
    stringBuilder1.append(get_request_tag());
    stringBuilder1.append(">");
    for (String str : this.params.keySet()) {
      StringBuilder stringBuilder = this.paramsStr;
      stringBuilder.append("<");
      stringBuilder.append(str);
      stringBuilder.append(">");
      stringBuilder.append(this.params.get(str));
      stringBuilder.append("<");
      stringBuilder.append("/");
      stringBuilder.append(str);
      stringBuilder.append(">");
      stringBuilder.append("\n");
    } 
    stringBuilder1.append(Utilities.Crypt(this.paramsStr.toString()));
    stringBuilder1.append("</tem:");
    stringBuilder1.append(get_request_tag());
    stringBuilder1.append(">\n");
    stringBuilder1.append("</tem:");
    stringBuilder1.append(get_methodName());
    stringBuilder1.append(">\n");
    stringBuilder1.append("</soapenv:Body>\n");
    stringBuilder1.append("</soapenv:Envelope>");
    return stringBuilder1.toString();
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
      this.request_propertys.put("User-Agent", this.USER_AGENT);
      this.request_propertys.put("Content-type", "text/xml; charset=utf-8");
    } 
    return this.request_propertys;
  }
  
  public int get_connection_time_out() {
    return this._connection_time_out;
  }
  
  public String get_methodName() {
    return this._methodName;
  }
  
  public String get_namespace() {
    return this._namespace;
  }
  
  public int get_read_time_out() {
    return this._read_time_out;
  }
  
  public String get_request_tag() {
    return this._request_tag;
  }
  
  public String get_response_tag() {
    return this._response_tag;
  }
  
  public String get_soapAction() {
    return this._soapAction;
  }
  
  public String get_url() {
    return this._url;
  }
  
  public void setKeyStoreId(int paramInt, String paramString) {
    this.keyStoreId = paramInt;
    this.nameKeyStore = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/SoapRequestObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */