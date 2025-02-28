package com.roadtrack.onstar.BO;

import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BackGroundRestartHandler {
  public List<UserDevicesVO> getListUserDevice(JSONArray paramJSONArray) {
    ArrayList<UserDevicesVO> arrayList = new ArrayList();
    byte b = 0;
    while (b < paramJSONArray.length()) {
      try {
        JSONObject jSONObject = paramJSONArray.getJSONObject(b);
        String str7 = jSONObject.getString("IdResponse");
        String str3 = jSONObject.getString("branchdealership");
        String str1 = jSONObject.getString("DeviceId");
        String str5 = jSONObject.getString("Phone");
        String str12 = jSONObject.getString("cve_model");
        String str6 = jSONObject.getString("DriverName");
        String str13 = jSONObject.getString("SpeedAlertActive");
        String str9 = jSONObject.getString("SpeedLimit");
        String str10 = jSONObject.getString("serialnumber");
        String str2 = jSONObject.getString("CurrentEnergyModeId");
        String str8 = jSONObject.getString("commserverid");
        String str11 = jSONObject.getString("Name");
        String str4 = jSONObject.getString("cve_brand");
        ArrayList<UserDevicesVO> arrayList1 = arrayList;
        try {
          String str = jSONObject.getString("TheftAlertActive");
          try {
            String str14 = jSONObject.getString("Purchasingdealership");
            String str15 = jSONObject.getString("DistanceUnit");
            JSONArray jSONArray = jSONObject.getJSONArray("Actions");
            ArrayList<String> arrayList2 = new ArrayList();
            this();
            if (jSONArray != null)
              for (byte b1 = 0; b1 < jSONArray.length(); b1++)
                arrayList2.add(jSONArray.get(b1).toString());  
            UserDevicesVO userDevicesVO = new UserDevicesVO();
            this();
            userDevicesVO.setIdResponse(str7);
            userDevicesVO.setBranchdealership(str3);
            userDevicesVO.setDeviceId(str1);
            userDevicesVO.setPhone(str5);
            userDevicesVO.setCve_model(str12);
            userDevicesVO.setDriverName(str6);
            userDevicesVO.setSpeedAlertActive(str13);
            userDevicesVO.setSpeedLimit(str9);
            userDevicesVO.setSerialnumber(str10);
            userDevicesVO.setCurrentEnergyModeId(str2);
            userDevicesVO.setCommserverid(str8);
            userDevicesVO.setName(str11);
            userDevicesVO.setCve_brand(str4);
            userDevicesVO.setTheftAlertActive(str);
            userDevicesVO.setPurchasingdealership(str14);
            userDevicesVO.setActions(arrayList2);
            userDevicesVO.setDistanceUnit(str15);
            arrayList1 = arrayList;
            arrayList.add(userDevicesVO);
          } catch (JSONException null) {}
        } catch (JSONException null) {
          arrayList = arrayList1;
        } 
      } catch (JSONException jSONException) {}
      Utilities.escribeArchivo("BackGroundRestartHandler", "Error: getListUserDevice", jSONException.getMessage());
    } 
    return arrayList;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/BackGroundRestartHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */