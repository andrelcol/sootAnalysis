package com.roadtrack.onstar.entities;

import android.content.Context;
import android.content.pm.PackageManager;
import com.roadtrack.onstar.utils.Utilities;

public class DeviceInfo {
  private String DID;
  
  private String Shell;
  
  public DeviceInfo(Context paramContext) {
    this.DID = Utilities.GetUUID(paramContext);
    try {
      this.Shell = (paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0)).versionName;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Utilities.escribeArchivo("DeviceInfo", "Error: get shell version", nameNotFoundException.getMessage());
      this.Shell = "1.0";
    } 
  }
  
  private String getMapsInstalled() {
    return "NULL";
  }
  
  public DeviceInfoEntity getDeviceInfo() {
    return new DeviceInfoEntity(this.DID, this.Shell, getMapsInstalled(), 0);
  }
  
  public DeviceInfoEntity getDeviceInfoP7() {
    return new DeviceInfoEntity(this.DID, this.Shell, getMapsInstalled(), 0);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/entities/DeviceInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */