package com.roadtrack.onstar.VO;

public class PushAlertsVO {
  private String acc;
  
  private String actionId;
  
  private String alert;
  
  public int alertCodeId = 0;
  
  public String completion_code;
  
  public String date;
  
  private String date_execution;
  
  private String id;
  
  private String insertedRow;
  
  private boolean isAction;
  
  private String latitude;
  
  private String longitude;
  
  public String messageResponseId;
  
  private String nActionID;
  
  public String requestErroId;
  
  public String responseErrorId;
  
  private String status;
  
  private String vehicle;
  
  public PushAlertsVO(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, boolean paramBoolean, String paramString12, String paramString13, String paramString14, String paramString15, int paramInt, String paramString16) {
    setStatus(paramString1);
    setAlert(paramString2);
    setVehicle(paramString3);
    setDate(paramString4);
    this.latitude = paramString5;
    this.longitude = paramString6;
    this.actionId = paramString7;
    this.nActionID = paramString8;
    this.acc = paramString9;
    this.id = paramString10;
    this.insertedRow = paramString11;
    this.isAction = paramBoolean;
    this.completion_code = paramString12;
    this.requestErroId = paramString13;
    this.responseErrorId = paramString14;
    this.messageResponseId = paramString15;
    this.alertCodeId = paramInt;
    this.date_execution = paramString16;
  }
  
  public String getAcc() {
    return this.acc;
  }
  
  public String getActionId() {
    return this.actionId;
  }
  
  public String getAlert() {
    return this.alert;
  }
  
  public int getAlertCodeId() {
    return this.alertCodeId;
  }
  
  public String getCompletion_code() {
    return this.completion_code;
  }
  
  public String getDate() {
    return this.date;
  }
  
  public String getDate_execution() {
    return this.date_execution;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getInsertedRow() {
    return this.insertedRow;
  }
  
  public String getLatitude() {
    return this.latitude;
  }
  
  public String getLongitude() {
    return this.longitude;
  }
  
  public String getMessageResponseId() {
    return this.messageResponseId;
  }
  
  public String getRequestErroId() {
    return this.requestErroId;
  }
  
  public String getResponseErrorId() {
    return this.responseErrorId;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public String getVehicle() {
    return this.vehicle;
  }
  
  public String getnActionID() {
    return this.nActionID;
  }
  
  public boolean isAction() {
    return this.isAction;
  }
  
  public void setAction(boolean paramBoolean) {
    this.isAction = paramBoolean;
  }
  
  public void setActionId(String paramString) {
    this.actionId = paramString;
  }
  
  public void setAlert(String paramString) {
    this.alert = paramString;
  }
  
  public void setAlertCodeId(int paramInt) {
    this.alertCodeId = paramInt;
  }
  
  public void setCompletion_code(String paramString) {
    this.completion_code = paramString;
  }
  
  public void setDate(String paramString) {
    this.date = paramString;
  }
  
  public void setInsertedRow(String paramString) {
    this.insertedRow = paramString;
  }
  
  public void setMessageResponseId(String paramString) {
    this.messageResponseId = paramString;
  }
  
  public void setRequestErroId(String paramString) {
    this.requestErroId = paramString;
  }
  
  public void setResponseErrorId(String paramString) {
    this.responseErrorId = paramString;
  }
  
  public void setStatus(String paramString) {
    this.status = paramString;
  }
  
  public void setVehicle(String paramString) {
    this.vehicle = paramString;
  }
  
  public void setnActionID(String paramString) {
    this.nActionID = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/PushAlertsVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */