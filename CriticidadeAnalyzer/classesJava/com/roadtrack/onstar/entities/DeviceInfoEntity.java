package com.roadtrack.onstar.entities;

public class DeviceInfoEntity {
  private String deviceID;
  
  private String installMaps;
  
  private String shellVersion;
  
  private int versionSDK;
  
  public DeviceInfoEntity(String paramString1, String paramString2, String paramString3, int paramInt) {
    this.deviceID = paramString1;
    this.shellVersion = paramString2;
    this.installMaps = paramString3;
    this.versionSDK = paramInt;
  }
  
  public String getDeviceID() {
    return this.deviceID;
  }
  
  public String getInstallMaps() {
    return this.installMaps;
  }
  
  public String getShellVersion() {
    return this.shellVersion;
  }
  
  public int getVersionSDK() {
    return this.versionSDK;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/entities/DeviceInfoEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */