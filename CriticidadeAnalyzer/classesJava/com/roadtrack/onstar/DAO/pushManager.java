package com.roadtrack.onstar.DAO;

import android.app.Activity;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.SimpleRequestObject;
import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedHashMap;

public class pushManager {
  private String LOCATOR_PUSH_appid = "appId";
  
  private String LOCATOR_PUSH_cellPhoneId = "cellPhoneId";
  
  private String LOCATOR_PUSH_forceRegister = "forceRegister";
  
  private String LOCATOR_PUSH_h = "h";
  
  private String LOCATOR_PUSH_id = "id";
  
  private String LOCATOR_PUSH_t = "t";
  
  private String URLGCMServer = GlobalMembers.URLGCMServer;
  
  private String deveciName = "deviceName";
  
  private Activity mActivity;
  
  public pushManager(Activity paramActivity) {
    this.mActivity = paramActivity;
  }
  
  public void deviceRegister(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DEVICEREGISTER: cellPhoneId: ");
    stringBuilder.append(paramString4);
    stringBuilder.append(" token: ");
    stringBuilder.append(paramString2);
    stringBuilder.append(" deviceName ");
    stringBuilder.append(paramString7);
    Utilities.escribeArchivo("pushManager", "GCM", stringBuilder.toString());
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    linkedHashMap.put(this.LOCATOR_PUSH_h, paramString1);
    linkedHashMap.put(this.LOCATOR_PUSH_t, paramString2);
    paramString1 = Utilities.Crypt(paramString3);
    linkedHashMap.put(this.LOCATOR_PUSH_id, paramString1);
    linkedHashMap.put(this.LOCATOR_PUSH_appid, paramString5);
    linkedHashMap.put(this.LOCATOR_PUSH_cellPhoneId, Utilities.Crypt(paramString4));
    linkedHashMap.put(this.deveciName, Utilities.Crypt(paramString7));
    linkedHashMap.put(this.LOCATOR_PUSH_forceRegister, paramString6);
    paramString1 = this.URLGCMServer;
    int i = GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF;
    SimpleRequestObject simpleRequestObject = new SimpleRequestObject(linkedHashMap, paramString1, i, i);
    simpleRequestObject.set_keyStoreId(2131623950, GlobalMembers.nameKeystoreService);
    RequestManager requestManager = new RequestManager(this.mActivity, simpleRequestObject);
    requestManager.setOnExecuteHttpPostAsyncListener(new RequestManager.OnExecuteHttpPostAsyncListenerURL(this) {
        
        });
    try {
      requestManager.sendRequest(2);
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public void setOnDeviceRegister(ondeviceRegister paramondeviceRegister) {}
  
  public static interface ondeviceRegister {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/DAO/pushManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */