package com.roadtrack.onstar.VO;

import com.roadtrack.onstar.Enums;

public class PushNotificationsVO extends ActionFindmeVO {
  private int Acc_Dialog;
  
  private int Acc_Renewal;
  
  private int DeviceID_Renewal;
  
  private String ExpirationDate_Dialog;
  
  private String IdCampaign_Dialog;
  
  private String IdCampaign_Renewal;
  
  private String Message_Dialog;
  
  private String Message_Renewal;
  
  private int ShowCheckBox_Dialog;
  
  private String TitleButton_Dialog;
  
  private String Title_Dialog;
  
  private String Title_Renewal;
  
  private String Url_Dialog;
  
  private Enums.responcesGCM acc;
  
  private String accNumber;
  
  private Enums.Services actionId;
  
  private String alertId;
  
  private String deviceId;
  
  private String fileName;
  
  private String gpsStatus;
  
  private String idResponse;
  
  private String latitude;
  
  private String longitude;
  
  private String message;
  
  private String newVersion;
  
  private String numberActionID;
  
  private String oldVersion;
  
  private String time;
  
  private String title;
  
  private String type;
  
  public Enums.responcesGCM getAcc() {
    return this.acc;
  }
  
  public String getAccNumber() {
    return this.accNumber;
  }
  
  public int getAcc_Dialog() {
    return this.Acc_Dialog;
  }
  
  public int getAcc_Renewal() {
    return this.Acc_Renewal;
  }
  
  public Enums.Services getActionId() {
    return this.actionId;
  }
  
  public String getAlertId() {
    return this.alertId;
  }
  
  public int getDeviceID_Renewal() {
    return this.DeviceID_Renewal;
  }
  
  public String getDeviceId() {
    return this.deviceId;
  }
  
  public String getExpirationDate_Dialog() {
    return this.ExpirationDate_Dialog;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String getGPSStatus() {
    return this.gpsStatus;
  }
  
  public String getIdCampaign_Dialog() {
    return this.IdCampaign_Dialog;
  }
  
  public String getIdCampaign_Renewal() {
    return this.IdCampaign_Renewal;
  }
  
  public String getIdResponse() {
    return this.idResponse;
  }
  
  public String getLatitude() {
    return this.latitude;
  }
  
  public String getLongitude() {
    return this.longitude;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public String getMessage_Dialog() {
    return this.Message_Dialog;
  }
  
  public String getMessage_Renewal() {
    return this.Message_Renewal;
  }
  
  public String getNewVersion() {
    return this.newVersion;
  }
  
  public String getNumberActionID() {
    return this.numberActionID;
  }
  
  public String getOldVersion() {
    return this.oldVersion;
  }
  
  public int getShowCheckBox_Dialog() {
    return this.ShowCheckBox_Dialog;
  }
  
  public String getTime() {
    return this.time;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public String getTitleButton_Dialog() {
    return this.TitleButton_Dialog;
  }
  
  public String getTitle_Dialog() {
    return this.Title_Dialog;
  }
  
  public String getTitle_Renewal() {
    return this.Title_Renewal;
  }
  
  public String getType() {
    return this.type;
  }
  
  public String getUrl_Dialog() {
    return this.Url_Dialog;
  }
  
  public void setAcc(Enums.responcesGCM paramresponcesGCM) {
    this.acc = paramresponcesGCM;
  }
  
  public void setAccNumber(String paramString) {
    this.accNumber = paramString;
  }
  
  public void setAcc_Dialog(int paramInt) {
    this.Acc_Dialog = paramInt;
  }
  
  public void setAcc_Renewal(int paramInt) {
    this.Acc_Renewal = paramInt;
  }
  
  public void setActionId(Enums.Services paramServices) {
    this.actionId = paramServices;
  }
  
  public void setAlertId(String paramString) {
    this.alertId = paramString;
  }
  
  public void setDeviceID_Renewal(int paramInt) {
    this.DeviceID_Renewal = paramInt;
  }
  
  public void setDeviceId(String paramString) {
    this.deviceId = paramString;
  }
  
  public void setExpirationDate_Dialog(String paramString) {
    this.ExpirationDate_Dialog = paramString;
  }
  
  public void setExpirationDate_Renewal(String paramString) {}
  
  public void setFileName(String paramString) {
    this.fileName = paramString;
  }
  
  public void setGPSStatus(String paramString) {
    this.gpsStatus = paramString;
  }
  
  public void setIdCampaign_Dialog(String paramString) {
    this.IdCampaign_Dialog = paramString;
  }
  
  public void setIdCampaign_Renewal(String paramString) {
    this.IdCampaign_Renewal = paramString;
  }
  
  public void setIdResponse(String paramString) {
    this.idResponse = paramString;
  }
  
  public void setLatitude(String paramString) {
    this.latitude = paramString;
  }
  
  public void setLongitude(String paramString) {
    this.longitude = paramString;
  }
  
  public void setMessage(String paramString) {
    this.message = paramString;
  }
  
  public void setMessage_Dialog(String paramString) {
    this.Message_Dialog = paramString;
  }
  
  public void setMessage_Renewal(String paramString) {
    this.Message_Renewal = paramString;
  }
  
  public void setNewVersion(String paramString) {
    this.newVersion = paramString;
  }
  
  public void setNumberActionID(String paramString) {
    this.numberActionID = paramString;
  }
  
  public void setOldVersion(String paramString) {
    this.oldVersion = paramString;
  }
  
  public void setShowCheckBox_Dialog(int paramInt) {
    this.ShowCheckBox_Dialog = paramInt;
  }
  
  public void setShowCheckBox_Renewal(int paramInt) {}
  
  public void setTime(String paramString) {
    this.time = paramString;
  }
  
  public void setTitle(String paramString) {
    this.title = paramString;
  }
  
  public void setTitleButton_Dialog(String paramString) {
    this.TitleButton_Dialog = paramString;
  }
  
  public void setTitleButton_Renewal(String paramString) {}
  
  public void setTitle_Dialog(String paramString) {
    this.Title_Dialog = paramString;
  }
  
  public void setTitle_Renewal(String paramString) {
    this.Title_Renewal = paramString;
  }
  
  public void setType(String paramString) {
    this.type = paramString;
  }
  
  public void setUrl_Dialog(String paramString) {
    this.Url_Dialog = paramString;
  }
  
  public void setUrl_Renewal(String paramString) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/PushNotificationsVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */