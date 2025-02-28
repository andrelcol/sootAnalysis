package com.roadtrack.onstar.VO;

public class RemoteDiagnosticVO {
  private String deviceId;
  
  private String dtcDate;
  
  private String dtcDescription;
  
  private int dtcGroupId;
  
  private int dtcStatusId;
  
  private String dtcTitle;
  
  public String getDeviceId() {
    return this.deviceId;
  }
  
  public String getDtcDate() {
    return this.dtcDate;
  }
  
  public String getDtcDescription() {
    return this.dtcDescription;
  }
  
  public int getDtcGroupId() {
    return this.dtcGroupId;
  }
  
  public int getDtcStatusId() {
    return this.dtcStatusId;
  }
  
  public String getDtcTitle() {
    return this.dtcTitle;
  }
  
  public void setDeviceId(String paramString) {
    this.deviceId = paramString;
  }
  
  public void setDtcDate(String paramString) {
    this.dtcDate = paramString;
  }
  
  public void setDtcDescription(String paramString) {
    this.dtcDescription = paramString;
  }
  
  public void setDtcGroupId(int paramInt) {
    this.dtcGroupId = paramInt;
  }
  
  public void setDtcStatusId(int paramInt) {
    this.dtcStatusId = paramInt;
  }
  
  public void setDtcTitle(String paramString) {
    this.dtcTitle = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/RemoteDiagnosticVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */