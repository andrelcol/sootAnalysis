package com.roadtrack.onstar;

import android.annotation.SuppressLint;
import android.content.Context;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.FollowMePointsVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import java.util.ArrayList;
import java.util.List;

public class FollowMeHandler {
  static FollowMePointsVO activePoint;
  
  @SuppressLint({"NewApi"})
  public static void deleteAllFollowmePointsSpecial() {
    DBFunctions dBFunctions = new DBFunctions(GlobalMembers.contexGlobal);
    VehicleCatalogVO vehicleCatalogVO = dBFunctions.getSelectedVehicle(dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
    if (vehicleCatalogVO != null && vehicleCatalogVO.getDeviceId() != null && !vehicleCatalogVO.getDeviceId().isEmpty()) {
      dBFunctions.deleteAllFollowmePoints();
    } else {
      dBFunctions.deleteAllFollowmePoints();
    } 
    if (!GlobalMembers.followMeArrayListPoints.isEmpty())
      activePoint = GlobalMembers.followMeArrayListPoints.get(0); 
    GlobalMembers.followMeIdOnDB = -1L;
    GlobalMembers.followMeArrayListPoints.clear();
  }
  
  public static void nullFollowMePoints() {
    (new DBFunctions(GlobalMembers.contexGlobal)).updateFollowMeStatus();
    GlobalMembers.followMeIdOnDB = -1L;
    GlobalMembers.followMeArrayListPoints.clear();
  }
  
  public static void proccessForOnFollowMe(String paramString) {
    if (GlobalMembers.onFollowMeRun.booleanValue() || GlobalMembers.ifollowMeType == 2) {
      String str = GlobalMembers.followMeProcessDeviceId;
      if (str != null && !str.equalsIgnoreCase("")) {
        GlobalMembers.followMeArrayListPoints.clear();
        GlobalMembers.followMeArrayListPoints = null;
        GlobalMembers.followMeArrayListPoints = new ArrayList();
        List list = (new DBFunctions(GlobalMembers.contexGlobal)).getAllFollowMe(paramString, GlobalMembers.followMeProcessDeviceId);
        for (byte b = 0; b < list.size(); b++) {
          if (b == 0 && activePoint != null) {
            GlobalMembers.followMeArrayListPoints.add(activePoint);
          } else {
            GlobalMembers.followMeArrayListPoints.add(list.get(b));
          } 
        } 
      } 
    } 
  }
  
  public static void proccessForOnFollowMeGlim(String paramString1, String paramString2) {
    if (paramString2 != null && !paramString2.equalsIgnoreCase("")) {
      GlobalMembers.followMeArrayListPoints.clear();
      GlobalMembers.followMeArrayListPoints = null;
      GlobalMembers.followMeArrayListPoints = new ArrayList();
      List list = (new DBFunctions(GlobalMembers.contexGlobal)).getAllFollowMe(paramString1, paramString2);
      for (byte b = 0; b < list.size(); b++) {
        if (b == 0 && activePoint != null) {
          GlobalMembers.followMeArrayListPoints.add(activePoint);
        } else {
          GlobalMembers.followMeArrayListPoints.add(list.get(b));
        } 
      } 
    } 
  }
  
  public static void sharedPreferenceInBackGround(Context paramContext, String paramString1, String paramString2, String paramString3) {
    PreferenceRT.SetValuePreference("sKey", paramString1, paramContext);
    PreferenceRT.SetValuePreference("user", paramString2, paramContext);
    PreferenceRT.SetValuePreference("pass", paramString3, paramContext);
  }
  
  public static void shredFollowMePreference(Boolean paramBoolean, Context paramContext) {
    long l = System.currentTimeMillis();
    PreferenceRT.SetValuePreference("onFollowMe", paramBoolean.booleanValue(), paramContext);
    PreferenceRT.SetValuePreference("onFollowMeId", GlobalMembers.followMeIdOnDB, paramContext);
    PreferenceRT.SetValuePreference("deviceId", GlobalMembers.followMeProcessDeviceId, paramContext);
    PreferenceRT.SetValuePreference("initTime", GlobalMembers.initFollowMeTime, paramContext);
    PreferenceRT.SetValuePreference("outTime", l, paramContext);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/FollowMeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */