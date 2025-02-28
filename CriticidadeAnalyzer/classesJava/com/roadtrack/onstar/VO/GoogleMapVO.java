package com.roadtrack.onstar.VO;

import java.io.Serializable;

public class GoogleMapVO implements Serializable {
  String ALERT_CODE_ID;
  
  String KEY_EXTERNAL_LAT;
  
  String KEY_EXTERNAL_LNG;
  
  String KEY_GPSSTATUS;
  
  Serializable KEY_SET_ENGINE_SOURCE;
  
  String KEY_SET_NAV_ACTION;
  
  String LASTKNOWDATE;
  
  String PREVIOUS_ACTIVITY = "";
  
  String PUSH_NOTIFICATION_DEVICE_ID;
  
  public String getALERT_CODE_ID() {
    return this.ALERT_CODE_ID;
  }
  
  public String getKEY_EXTERNAL_LAT() {
    return this.KEY_EXTERNAL_LAT;
  }
  
  public String getKEY_EXTERNAL_LNG() {
    return this.KEY_EXTERNAL_LNG;
  }
  
  public String getKEY_GPSSTATUS() {
    return this.KEY_GPSSTATUS;
  }
  
  public Serializable getKEY_SET_ENGINE_SOURCE() {
    return this.KEY_SET_ENGINE_SOURCE;
  }
  
  public String getKEY_SET_NAV_ACTION() {
    return this.KEY_SET_NAV_ACTION;
  }
  
  public String getLASTKNOWDATE() {
    return this.LASTKNOWDATE;
  }
  
  public String getPREVIOUS_ACTIVITY() {
    return this.PREVIOUS_ACTIVITY;
  }
  
  public String getPUSH_NOTIFICATION_DEVICE_ID() {
    return this.PUSH_NOTIFICATION_DEVICE_ID;
  }
  
  public void setALERT_CODE_ID(String paramString) {
    this.ALERT_CODE_ID = paramString;
  }
  
  public void setFLAG_ACTIVITY_CLEAR_TOP(int paramInt) {}
  
  public void setFLAG_ACTIVITY_NEW_TASK(int paramInt) {}
  
  public void setKEY_EXTERNAL_LAT(String paramString) {
    this.KEY_EXTERNAL_LAT = paramString;
  }
  
  public void setKEY_EXTERNAL_LNG(String paramString) {
    this.KEY_EXTERNAL_LNG = paramString;
  }
  
  public void setKEY_GPSSTATUS(String paramString) {
    this.KEY_GPSSTATUS = paramString;
  }
  
  public void setKEY_SET_ENGINE_SOURCE(Serializable paramSerializable) {
    this.KEY_SET_ENGINE_SOURCE = paramSerializable;
  }
  
  public void setKEY_SET_NAV_ACTION(String paramString) {
    this.KEY_SET_NAV_ACTION = paramString;
  }
  
  public void setKEY_SET_NAV_DEVICE(String paramString) {}
  
  public void setKEY_SET_NAV_PARAMS(String paramString) {}
  
  public void setLASTKNOWDATE(String paramString) {
    this.LASTKNOWDATE = paramString;
  }
  
  public void setPREVIOUS_ACTIVITY(String paramString) {
    this.PREVIOUS_ACTIVITY = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/GoogleMapVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */