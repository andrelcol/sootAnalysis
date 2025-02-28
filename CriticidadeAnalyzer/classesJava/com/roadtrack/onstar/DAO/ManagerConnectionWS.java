package com.roadtrack.onstar.DAO;

import android.content.Context;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.JsonRequestObject;
import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.SimpleRequestObject;
import com.roadtrack.onstar.BO.SoapRequestObject;
import com.roadtrack.onstar.utils.Utilities;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.apache.http.conn.ConnectTimeoutException;

public class ManagerConnectionWS {
  private String _methodName = "";
  
  private String _namespace = "";
  
  private String _soapAction = "";
  
  private String _url = "";
  
  private String aux_result = "";
  
  private String DeleteDataWSGetDevices(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    if (isCorrectPreferences()) {
      String str1 = get_soapAction();
      String str2 = get_methodName();
      String str3 = get_namespace();
      String str4 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(paramLinkedHashMap, str1, str2, str3, str4, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.sendRequest(1);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private Object GetDataWS(Hashtable<String, Object> paramHashtable, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.put("sessionKey", paramHashtable.get("sessionKey"));
      linkedHashMap.put("login", paramHashtable.get("login"));
      linkedHashMap.put("password", paramHashtable.get("password"));
      linkedHashMap.put("messageId", paramHashtable.get("messageId"));
      linkedHashMap.put("applicationSourceId", paramHashtable.get("applicationSourceId"));
      String str2 = get_soapAction();
      String str4 = get_methodName();
      String str1 = get_namespace();
      String str3 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str2, str4, str1, str3, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("response: ");
              stringBuilder1.append(param1String);
              String str = stringBuilder1.toString();
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("responseCode: ");
              stringBuilder2.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder2.toString());
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSAvailableTicket(String paramString, List paramList, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferencesJson()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bearer ");
      stringBuilder.append(paramString);
      linkedHashMap.put("AUTHORIZATION", stringBuilder.toString());
      linkedHashMap.put("Content-Type", "application/json");
      paramString = get_url();
      String str = get_namespace();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT;
      JsonRequestObject jsonRequestObject = new JsonRequestObject(paramString, str, paramList, i, i);
      jsonRequestObject.setRequest_propertys(linkedHashMap);
      jsonRequestObject.setPropertysAsSet(true);
      jsonRequestObject.setKeyStoreId(2131623938, GlobalMembers.nameKeystoreMoip);
      RequestManager requestManager = new RequestManager(paramContext, jsonRequestObject);
      requestManager.sendRequest(5);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str1 = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSGetDevices(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    if (isCorrectPreferences()) {
      String str2 = get_soapAction();
      String str4 = get_methodName();
      String str3 = get_namespace();
      String str1 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(paramLinkedHashMap, str2, str4, str3, str1, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.sendRequest(1);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSGetDtcStatus(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    if (isCorrectPreferences()) {
      String str1 = get_soapAction();
      String str4 = get_methodName();
      String str3 = get_namespace();
      String str2 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(paramLinkedHashMap, str1, str4, str3, str2, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.sendRequest(1);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSGetDtcs(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    if (isCorrectPreferences()) {
      String str3 = get_soapAction();
      String str4 = get_methodName();
      String str2 = get_namespace();
      String str1 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(paramLinkedHashMap, str3, str4, str2, str1, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.sendRequest(1);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSGetGmt(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    if (isCorrectPreferences()) {
      String str3 = get_soapAction();
      String str4 = get_methodName();
      String str2 = get_namespace();
      String str1 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(paramLinkedHashMap, str3, str4, str2, str1, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.sendRequest(1);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSMyPlanInfo(LinkedHashMap<String, Object> paramLinkedHashMap, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      String str1 = get_soapAction();
      String str4 = get_methodName();
      String str3 = get_namespace();
      String str2 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      RequestManager requestManager = new RequestManager(paramContext, new SoapRequestObject(paramLinkedHashMap, str1, str4, str3, str2, i, i));
      requestManager.sendRequest(1);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSPaymentCardInfo(LinkedHashMap<String, Object> paramLinkedHashMap, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferencesJson()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Bearer ");
      stringBuilder2.append(paramLinkedHashMap.get("Authorization").toString());
      linkedHashMap.put("AUTHORIZATION", stringBuilder2.toString());
      linkedHashMap.put("accept-charset", "utf-8");
      String str2 = URLEncoder.encode(paramLinkedHashMap.get("gcmupar1").toString(), "UTF-8");
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(get_url());
      stringBuilder1.append(get_namespace());
      String str1 = String.format(stringBuilder1.toString(), new Object[] { str2 });
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      RequestManager requestManager = new RequestManager(paramContext, new SimpleRequestObject(null, linkedHashMap, str1, i, i, 2131623938, GlobalMembers.nameKeystoreMoip));
      requestManager.sendRequest(4);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSPaymentHistorical(LinkedHashMap<String, Object> paramLinkedHashMap, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferencesJson()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bearer ");
      stringBuilder.append(paramLinkedHashMap.get("Authorization").toString());
      linkedHashMap.put("AUTHORIZATION", stringBuilder.toString());
      linkedHashMap.put("accept-charset", "utf-8");
      String str4 = URLEncoder.encode(paramLinkedHashMap.get("par1").toString(), "UTF-8");
      String str3 = URLEncoder.encode(paramLinkedHashMap.get("par2").toString(), "UTF-8");
      String str2 = URLEncoder.encode(paramLinkedHashMap.get("par3").toString(), "UTF-8");
      String str5 = URLEncoder.encode(paramLinkedHashMap.get("par4").toString(), "UTF-8");
      String str1 = URLEncoder.encode(paramLinkedHashMap.get("par5").toString(), "UTF-8");
      return String.format(get_url(), new Object[] { str4, str3, str2, str5, str1 });
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSPaymentHistory(LinkedHashMap<String, Object> paramLinkedHashMap, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferencesJson()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Bearer ");
      stringBuilder1.append(paramLinkedHashMap.get("Authorization").toString());
      linkedHashMap.put("AUTHORIZATION", stringBuilder1.toString());
      linkedHashMap.put("accept-charset", "utf-8");
      String str2 = URLEncoder.encode(paramLinkedHashMap.get("chppar1").toString(), "UTF-8");
      String str1 = URLEncoder.encode(paramLinkedHashMap.get("chppar2").toString(), "UTF-8");
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(get_url());
      stringBuilder2.append(get_namespace());
      str1 = String.format(stringBuilder2.toString(), new Object[] { str2, str1 });
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      RequestManager requestManager = new RequestManager(paramContext, new SimpleRequestObject(null, linkedHashMap, str1, i, i, 2131623938, GlobalMembers.nameKeystoreMoip));
      requestManager.sendRequest(4);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSPaymentProcess(LinkedHashMap<String, Object> paramLinkedHashMap, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferencesJson()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bearer ");
      stringBuilder.append(paramLinkedHashMap.get("Authorization").toString());
      linkedHashMap.put("AUTHORIZATION", stringBuilder.toString());
      linkedHashMap.put("accept-charset", "utf-8");
      String str3 = URLEncoder.encode(paramLinkedHashMap.get("par1").toString(), "UTF-8");
      String str5 = URLEncoder.encode(paramLinkedHashMap.get("par2").toString(), "UTF-8");
      String str2 = URLEncoder.encode(paramLinkedHashMap.get("par3").toString(), "UTF-8");
      String str4 = URLEncoder.encode(paramLinkedHashMap.get("par4").toString(), "UTF-8");
      String str1 = URLEncoder.encode(paramLinkedHashMap.get("par5").toString(), "UTF-8");
      return String.format(get_url(), new Object[] { str3, str5, str2, str4, str1 });
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSRenewalPlans(LinkedHashMap<String, Object> paramLinkedHashMap, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferencesJson()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Bearer ");
      stringBuilder2.append(paramLinkedHashMap.get("Authorization").toString());
      linkedHashMap.put("AUTHORIZATION", stringBuilder2.toString());
      linkedHashMap.put("accept-charset", "utf-8");
      String str2 = URLEncoder.encode(paramLinkedHashMap.get("cppar1").toString(), "UTF-8");
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(get_url());
      stringBuilder1.append(get_namespace());
      String str1 = String.format(stringBuilder1.toString(), new Object[] { str2 });
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      RequestManager requestManager = new RequestManager(paramContext, new SimpleRequestObject(null, linkedHashMap, str1, i, i, 2131623938, GlobalMembers.nameKeystoreMoip));
      requestManager.sendRequest(4);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSRenewalToken(List paramList, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferencesJson()) {
      String str1 = get_url();
      String str2 = get_namespace();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      JsonRequestObject jsonRequestObject = new JsonRequestObject(str1, str2, paramList, i, i);
      jsonRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, jsonRequestObject);
      requestManager.sendRequest(5);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSRenewalTransaction(String paramString, List paramList, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferencesJson()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bearer ");
      stringBuilder.append(paramString);
      linkedHashMap.put("AUTHORIZATION", stringBuilder.toString());
      linkedHashMap.put("Content-Type", "application/json");
      String str = get_url();
      paramString = get_namespace();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      JsonRequestObject jsonRequestObject = new JsonRequestObject(str, paramString, paramList, i, i);
      jsonRequestObject.setRequest_propertys(linkedHashMap);
      jsonRequestObject.setPropertysAsSet(true);
      jsonRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, jsonRequestObject);
      requestManager.sendRequest(5);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str1 = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSSaveStatisticsNavigationIn(LinkedHashMap<String, Object> paramLinkedHashMap, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      String str3 = get_soapAction();
      String str4 = get_methodName();
      String str1 = get_namespace();
      String str2 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(paramLinkedHashMap, str3, str4, str1, str2, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.sendRequest(1);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWSSetGmt(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    if (isCorrectPreferences()) {
      String str4 = get_soapAction();
      String str3 = get_methodName();
      String str2 = get_namespace();
      String str1 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(paramLinkedHashMap, str4, str3, str2, str1, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.sendRequest(1);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private Object GetDataWSactivePushNotificationAppG2(Hashtable<String, Object> paramHashtable, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.clear();
      linkedHashMap.put("sessionKey", paramHashtable.get("sessionKey"));
      linkedHashMap.put("login", paramHashtable.get("login"));
      linkedHashMap.put("password", paramHashtable.get("password"));
      linkedHashMap.put("accountId", paramHashtable.get("accountId"));
      linkedHashMap.put("userId", paramHashtable.get("userId"));
      linkedHashMap.put("status", paramHashtable.get("status"));
      linkedHashMap.put("applicationSourceId", paramHashtable.get("applicationSourceId"));
      String str1 = get_soapAction();
      String str2 = get_methodName();
      String str3 = get_namespace();
      String str4 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str1, str2, str3, str4, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("response: ");
              stringBuilder1.append(param1String);
              String str = stringBuilder1.toString();
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("responseCode: ");
              stringBuilder2.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder2.toString());
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWcf(Hashtable<String, Object> paramHashtable, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.put("sessionKey", paramHashtable.get("sessionKey").toString());
      linkedHashMap.put("login", paramHashtable.get("login").toString());
      linkedHashMap.put("password", paramHashtable.get("password").toString());
      linkedHashMap.put("deviceId", paramHashtable.get("deviceId").toString());
      linkedHashMap.put("actionCode", paramHashtable.get("actionCode").toString());
      linkedHashMap.put("csvParams", paramHashtable.get("csvParams"));
      linkedHashMap.put("userId", paramHashtable.get("userId"));
      linkedHashMap.put("applicationSourceId", paramHashtable.get("applicationSourceId"));
      linkedHashMap.put("deviceCSVParams", paramHashtable.get("deviceCSVParams"));
      String str2 = get_soapAction();
      String str4 = get_methodName();
      String str1 = get_namespace();
      String str3 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_SENDCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str2, str4, str1, str3, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("response: ");
              stringBuilder1.append(param1String);
              String str = stringBuilder1.toString();
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("responseCode: ");
              stringBuilder2.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder2.toString());
              if (param1Int == -1 || param1Int < 200 || param1Int > 300) {
                ManagerConnectionWS managerConnectionWS = ManagerConnectionWS.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("0|0|");
                stringBuilder.append(param1String);
                ManagerConnectionWS.access$002(managerConnectionWS, stringBuilder.toString());
                return;
              } 
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private Object GetDataWcfSyncDelete(Hashtable<String, Object> paramHashtable, boolean paramBoolean1, boolean paramBoolean2, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.put("ids", paramHashtable.get("ids"));
      linkedHashMap.put("user", paramHashtable.get("user"));
      linkedHashMap.put("device", paramHashtable.get("device"));
      linkedHashMap.put("commId", paramHashtable.get("commId"));
      linkedHashMap.put("serialNumber", paramHashtable.get("serialNumber"));
      String str1 = get_soapAction();
      String str4 = get_methodName();
      String str2 = get_namespace();
      String str3 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str1, str4, str2, str3, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("response: ");
              stringBuilder1.append(param1String);
              String str = stringBuilder1.toString();
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("responseCode: ");
              stringBuilder2.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder2.toString());
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private Object GetDataWcfSyncInsert(Hashtable<String, Object> paramHashtable, boolean paramBoolean1, boolean paramBoolean2, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.put("user", paramHashtable.get("user"));
      linkedHashMap.put("phone", paramHashtable.get("phone"));
      linkedHashMap.put("device", paramHashtable.get("device"));
      linkedHashMap.put("name", paramHashtable.get("name"));
      linkedHashMap.put("latlng", paramHashtable.get("latlng"));
      linkedHashMap.put("commId", paramHashtable.get("commId"));
      linkedHashMap.put("serialNumber", paramHashtable.get("serialNumber"));
      linkedHashMap.put("id_favs", paramHashtable.get("id_favs"));
      linkedHashMap.put("address_syc", paramHashtable.get("address_syc"));
      String str2 = get_soapAction();
      String str4 = get_methodName();
      String str3 = get_namespace();
      String str1 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str2, str4, str3, str1, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("response: ");
              stringBuilder.append(param1String);
              String str = stringBuilder.toString();
              stringBuilder = new StringBuilder();
              stringBuilder.append("responseCode: ");
              stringBuilder.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder.toString());
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetDataWcfSyncSelect(Hashtable<String, Object> paramHashtable, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.put("user_Sync", paramHashtable.get("user_Sync"));
      linkedHashMap.put("deviceId", paramHashtable.get("deviceId").toString());
      String str4 = get_soapAction();
      String str3 = get_methodName();
      String str2 = get_namespace();
      String str1 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str4, str3, str2, str1, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("response: ");
              stringBuilder1.append(param1String);
              String str = stringBuilder1.toString();
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("responseCode: ");
              stringBuilder2.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder2.toString());
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private Object GetDataWcfSyncUpdate(Hashtable<String, Object> paramHashtable, boolean paramBoolean1, boolean paramBoolean2, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.put("id", paramHashtable.get("id"));
      linkedHashMap.put("user", paramHashtable.get("user"));
      linkedHashMap.put("phone", paramHashtable.get("phone"));
      linkedHashMap.put("device", paramHashtable.get("device"));
      linkedHashMap.put("name", paramHashtable.get("name"));
      linkedHashMap.put("latlng", paramHashtable.get("latlng"));
      linkedHashMap.put("id_favs", paramHashtable.get("id_favs"));
      linkedHashMap.put("commId", paramHashtable.get("commId"));
      linkedHashMap.put("serialNumber", paramHashtable.get("serialNumber"));
      linkedHashMap.put("address_syc", paramHashtable.get("address_syc"));
      String str2 = get_soapAction();
      String str4 = get_methodName();
      String str3 = get_namespace();
      String str1 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str2, str4, str3, str1, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("response: ");
              stringBuilder1.append(param1String);
              String str = stringBuilder1.toString();
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("responseCode: ");
              stringBuilder2.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder2.toString());
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private Object GetDataWsActiveNotificationEmail(Hashtable<String, Object> paramHashtable, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.clear();
      linkedHashMap.put("sessionKey", paramHashtable.get("sessionKey"));
      linkedHashMap.put("login", paramHashtable.get("login"));
      linkedHashMap.put("password", paramHashtable.get("password"));
      linkedHashMap.put("userId", paramHashtable.get("userId"));
      linkedHashMap.put("accountId", paramHashtable.get("accountId"));
      linkedHashMap.put("devices", paramHashtable.get("devices"));
      linkedHashMap.put("enabled", paramHashtable.get("enabled"));
      linkedHashMap.put("applicationSourceId", paramHashtable.get("applicationSourceId"));
      String str4 = get_soapAction();
      String str1 = get_methodName();
      String str3 = get_namespace();
      String str2 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str4, str1, str3, str2, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("response: ");
              stringBuilder1.append(param1String);
              String str = stringBuilder1.toString();
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("responseCode: ");
              stringBuilder2.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder2.toString());
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private Object GetDataWsNotificationEmailStatus(Hashtable<String, Object> paramHashtable, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.clear();
      linkedHashMap.put("sessionKey", paramHashtable.get("sessionKey"));
      linkedHashMap.put("login", paramHashtable.get("login"));
      linkedHashMap.put("password", paramHashtable.get("password"));
      linkedHashMap.put("userId", paramHashtable.get("userId"));
      linkedHashMap.put("accountId", paramHashtable.get("accountId"));
      linkedHashMap.put("applicationSourceId", paramHashtable.get("applicationSourceId"));
      String str1 = get_soapAction();
      String str4 = get_methodName();
      String str2 = get_namespace();
      String str3 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str1, str4, str2, str3, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("response: ");
              stringBuilder1.append(param1String);
              String str = stringBuilder1.toString();
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("responseCode: ");
              stringBuilder2.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder2.toString());
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetOnStarClubURL(Hashtable<String, Object> paramHashtable, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    if (isCorrectPreferences()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      linkedHashMap.clear();
      linkedHashMap.put("sessionKey", paramHashtable.get("sessionKey"));
      linkedHashMap.put("login", paramHashtable.get("login"));
      linkedHashMap.put("password", "@Irt54321");
      linkedHashMap.put("applicationSourceId", paramHashtable.get("applicationSourceId"));
      String str3 = get_soapAction();
      String str4 = get_methodName();
      String str1 = get_namespace();
      String str2 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(linkedHashMap, str3, str4, str1, str2, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.setOnPostExecuteListener(new RequestManager.OnPostExecuteListener() {
            final ManagerConnectionWS this$0;
            
            public void onPostExecuteListener(String param1String, int param1Int) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("response: ");
              stringBuilder.append(param1String);
              String str = stringBuilder.toString();
              stringBuilder = new StringBuilder();
              stringBuilder.append("responseCode: ");
              stringBuilder.append(param1Int);
              Utilities.escribeArchivo("ManagerConnectionsWS", str, stringBuilder.toString());
              ManagerConnectionWS.access$002(ManagerConnectionWS.this, param1String);
            }
          });
      requestManager.sendRequest(1);
      return this.aux_result;
    } 
    throw new Exception("Verify preferences");
  }
  
  private String GetRegistrationInfoToken(LinkedHashMap<String, Object> paramLinkedHashMap, boolean paramBoolean, Context paramContext) throws TimeoutException, ConnectTimeoutException, Exception {
    String str = GlobalMembers.URL_DTC_LocatorWebApiLogin;
    int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF;
    SimpleRequestObject simpleRequestObject = new SimpleRequestObject(paramLinkedHashMap, str, i, i);
    simpleRequestObject.set_keyStoreId(2131623959, GlobalMembers.nameKeystoreServiceDTC);
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    linkedHashMap.put("Content-Type", "application/x-www-form-urlencoded");
    simpleRequestObject.setRequest_propertys(linkedHashMap);
    RequestManager requestManager = new RequestManager(paramContext, simpleRequestObject);
    requestManager.sendRequest(2);
    this.aux_result = requestManager.getRespuesta();
    if (requestManager.getResponseCode() != -1) {
      String str1 = this.aux_result;
    } else {
      requestManager = null;
    } 
    return (String)requestManager;
  }
  
  private String UpdateDataWSGetDtcStatus(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    if (isCorrectPreferences()) {
      String str2 = get_soapAction();
      String str1 = get_methodName();
      String str3 = get_namespace();
      String str4 = get_url();
      int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
      SoapRequestObject soapRequestObject = new SoapRequestObject(paramLinkedHashMap, str2, str1, str3, str4, i, i);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager(paramContext, soapRequestObject);
      requestManager.sendRequest(1);
      this.aux_result = requestManager.getRespuesta();
      if (requestManager.getResponseCode() != -1) {
        String str = this.aux_result;
      } else {
        requestManager = null;
      } 
      return (String)requestManager;
    } 
    throw new Exception("Verify preferences");
  }
  
  private boolean isCorrectPreferences() {
    boolean bool;
    if (get_soapAction().trim().length() == 0) {
      bool = false;
    } else {
      bool = true;
    } 
    if (get_methodName().trim().length() == 0)
      bool = false; 
    if (get_namespace().trim().length() == 0)
      bool = false; 
    if (get_url().trim().length() == 0)
      bool = false; 
    return bool;
  }
  
  private boolean isCorrectPreferencesJson() {
    boolean bool;
    if (get_namespace().trim().length() == 0) {
      bool = false;
    } else {
      bool = true;
    } 
    if (get_url().trim().length() == 0)
      bool = false; 
    return bool;
  }
  
  public String DeleteDataWSNetDevice(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    return DeleteDataWSGetDevices(paramLinkedHashMap, paramContext);
  }
  
  public String GetDataWSAvailableTicket(String paramString, List paramList, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSAvailableTicket(paramString, paramList, true, paramContext);
  }
  
  public Object GetDataWSNet(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWS(paramHashtable, true, paramContext);
  }
  
  public Object GetDataWSNetActiveNotificationEmail(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWsActiveNotificationEmail(paramHashtable, true, paramContext);
  }
  
  public String GetDataWSNetDevice(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    return GetDataWSGetDevices(paramLinkedHashMap, paramContext);
  }
  
  public String GetDataWSNetGetDtcStatus(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    return GetDataWSGetDtcStatus(paramLinkedHashMap, paramContext);
  }
  
  public String GetDataWSNetGetDtcs(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    return GetDataWSGetDtcs(paramLinkedHashMap, paramContext);
  }
  
  public String GetDataWSNetGetGmt(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    return GetDataWSGetGmt(paramLinkedHashMap, paramContext);
  }
  
  public String GetDataWSNetMyPlanInfo(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSMyPlanInfo(paramLinkedHashMap, true, paramContext);
  }
  
  public Object GetDataWSNetNotificationEmailStatus(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWsNotificationEmailStatus(paramHashtable, true, paramContext);
  }
  
  public String GetDataWSNetSaveStatisticsNavigationIn(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSSaveStatisticsNavigationIn(paramLinkedHashMap, true, paramContext);
  }
  
  public String GetDataWSNetSetGmt(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    return GetDataWSSetGmt(paramLinkedHashMap, paramContext);
  }
  
  public Object GetDataWSNetWcf(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWcf(paramHashtable, true, paramContext);
  }
  
  public Object GetDataWSNetWcfSyncDelete(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWcfSyncDelete(paramHashtable, true, true, paramContext);
  }
  
  public Object GetDataWSNetWcfSyncInsert(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWcfSyncInsert(paramHashtable, true, true, paramContext);
  }
  
  public Object GetDataWSNetWcfSyncSelect(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWcfSyncSelect(paramHashtable, true, paramContext);
  }
  
  public Object GetDataWSNetWcfSyncUpdate(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWcfSyncUpdate(paramHashtable, true, true, paramContext);
  }
  
  public String GetDataWSPaymentCardInfo(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSPaymentCardInfo(paramLinkedHashMap, true, paramContext);
  }
  
  public String GetDataWSPaymentHistorical(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSPaymentHistorical(paramLinkedHashMap, true, paramContext);
  }
  
  public String GetDataWSPaymentHistory(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSPaymentHistory(paramLinkedHashMap, true, paramContext);
  }
  
  public String GetDataWSPaymentProcess(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSPaymentProcess(paramLinkedHashMap, true, paramContext);
  }
  
  public String GetDataWSRenewalPlans(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSRenewalPlans(paramLinkedHashMap, true, paramContext);
  }
  
  public String GetDataWSRenewalToken(List paramList, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSRenewalToken(paramList, true, paramContext);
  }
  
  public String GetDataWSRenewalTransaction(String paramString, List paramList, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSRenewalTransaction(paramString, paramList, true, paramContext);
  }
  
  public Object GetDataWsNetActivePushNotificationAppG2(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetDataWSactivePushNotificationAppG2(paramHashtable, true, paramContext);
  }
  
  public String GetOnStarClUBURL(Hashtable<String, Object> paramHashtable, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetOnStarClubURL(paramHashtable, paramContext);
  }
  
  public String GetRegistrationInfoToken(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception, TimeoutException, ConnectTimeoutException, SocketTimeoutException {
    return GetRegistrationInfoToken(paramLinkedHashMap, true, paramContext);
  }
  
  public String UpdateDataWSNetGetDtcStatus(LinkedHashMap<String, Object> paramLinkedHashMap, Context paramContext) throws Exception {
    return UpdateDataWSGetDtcStatus(paramLinkedHashMap, paramContext);
  }
  
  public String get_methodName() {
    return this._methodName;
  }
  
  public String get_namespace() {
    return this._namespace;
  }
  
  public String get_soapAction() {
    return this._soapAction;
  }
  
  public String get_url() {
    return this._url;
  }
  
  public void set_methodName(String paramString) {
    this._methodName = paramString;
  }
  
  public void set_namespace(String paramString) {
    this._namespace = paramString;
  }
  
  public void set_soapAction(String paramString) {
    this._soapAction = paramString;
  }
  
  public void set_url(String paramString) {
    this._url = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/DAO/ManagerConnectionWS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */