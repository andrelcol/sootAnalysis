package com.roadtrack.onstar.entities;

public class RegisterDevice {
  private String applicationType;
  
  private String deviceName;
  
  private Boolean select;
  
  private String udid;
  
  public String getApplicationType() {
    return this.applicationType;
  }
  
  public String getDeviceName() {
    return this.deviceName;
  }
  
  public Boolean getSelect() {
    return this.select;
  }
  
  public String getUdid() {
    return this.udid;
  }
  
  public void setApplicationType(String paramString) {
    this.applicationType = paramString;
  }
  
  public void setDeviceName(String paramString) {
    this.deviceName = paramString;
  }
  
  public void setSelect(Boolean paramBoolean) {
    this.select = paramBoolean;
  }
  
  public void setUdid(String paramString) {
    this.udid = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/entities/RegisterDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */