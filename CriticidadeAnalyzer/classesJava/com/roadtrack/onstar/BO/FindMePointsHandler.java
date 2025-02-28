package com.roadtrack.onstar.BO;

import com.roadtrack.onstar.DAO.DBFunctions;

public class FindMePointsHandler {
  private static DBFunctions dbFun;
  
  public static final String[] getDevicePoints(String paramString) {
    String[] arrayOfString;
    dbFun = new DBFunctions(GlobalMembers.contexGlobal);
    if (dbFun.existDevice(paramString).booleanValue()) {
      arrayOfString = dbFun.getLastDevicePoint(paramString);
    } else {
      arrayOfString = "0.0,0.0".split(",");
    } 
    return arrayOfString;
  }
  
  public void insertFindmePoint(String paramString1, String paramString2, String paramString3) {
    dbFun = new DBFunctions(GlobalMembers.contexGlobal);
    if (dbFun.existDevice(paramString1).booleanValue()) {
      dbFun.updateFindmePoint(paramString1, paramString2, paramString3);
    } else {
      dbFun.addFindmePoint(paramString1, paramString2, paramString3);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/FindMePointsHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */