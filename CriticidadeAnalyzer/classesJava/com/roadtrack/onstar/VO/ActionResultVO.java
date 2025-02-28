package com.roadtrack.onstar.VO;

import android.content.Context;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.utils.Utilities;

public class ActionResultVO {
  private String TPMSText;
  
  private String autonomy_km;
  
  private String autonomy_status;
  
  private String autonomy_text;
  
  private String batteryLevel;
  
  private int deviceId;
  
  private String eventDateTime;
  
  private String gas;
  
  private int gpsStatus;
  
  private Enums.ActionResultCode idResponse;
  
  private Enums.ActionResultCode idResponseOriginal;
  
  private String isM300;
  
  private float latitude;
  
  private float longitude;
  
  private int messageId;
  
  private String modelo;
  
  private String odometer;
  
  private String oil;
  
  private String oilStatus;
  
  private String oilValue;
  
  private String placa;
  
  private Enums.WSResultId sendCommandResultId;
  
  private String socketCode;
  
  private String tireFL;
  
  private String tireFR;
  
  private String tireRL;
  
  private String tireRR;
  
  private String tireStatusFL;
  
  private String tireStatusFR;
  
  private String tireStatusRL;
  
  private String tireStatusRR;
  
  private String version;
  
  private String year;
  
  public ActionResultVO(Context paramContext, int paramInt) {
    Enums.ActionResultCode actionResultCode = Enums.ActionResultCode.NoResult;
    this.idResponse = actionResultCode;
    this.socketCode = "0";
    this.idResponseOriginal = actionResultCode;
    this.sendCommandResultId = Enums.WSResultId.NoResult;
    actionResultCode = Enums.ActionResultCode.NoResult;
    this.idResponse = actionResultCode;
    this.idResponseOriginal = actionResultCode;
    this.deviceId = paramInt;
    Enums.Services services = Enums.Services.None;
    this.messageId = 0;
    this.latitude = 0.0F;
    this.longitude = 0.0F;
    this.odometer = "";
    this.gpsStatus = Enums.GpsStatusCode.NoStatus.value();
    this.modelo = "";
    this.version = "";
    this.year = "";
    this.placa = "";
    this.oil = "";
    this.gas = "";
    this.batteryLevel = "";
    this.tireStatusFR = "";
    this.tireFR = "";
    this.tireStatusFL = "";
    this.tireFL = "";
    this.tireStatusRR = "";
    this.tireRR = "";
    this.tireStatusRL = "";
    this.tireRL = "";
    this.eventDateTime = Utilities.getDateTime(String.valueOf(paramInt), paramContext);
    this.isM300 = "";
    this.autonomy_km = "";
    this.autonomy_status = "";
    this.autonomy_text = "";
  }
  
  public String getAutonomy_km() {
    return this.autonomy_km;
  }
  
  public String getAutonomy_status() {
    return this.autonomy_status;
  }
  
  public String getAutonomy_text() {
    return this.autonomy_text;
  }
  
  public String getBatteryLevel() {
    return this.batteryLevel;
  }
  
  public int getDeviceId() {
    return this.deviceId;
  }
  
  public String getEventDateTime() {
    return this.eventDateTime;
  }
  
  public String getGas() {
    return this.gas;
  }
  
  public int getGpsStatus() {
    return this.gpsStatus;
  }
  
  public Enums.ActionResultCode getIdResponse() {
    return this.idResponse;
  }
  
  public Enums.ActionResultCode getIdResponseOriginal() {
    return this.idResponseOriginal;
  }
  
  public String getIsM300() {
    return this.isM300;
  }
  
  public float getLatitude() {
    return this.latitude;
  }
  
  public float getLongitude() {
    return this.longitude;
  }
  
  public int getMessageId() {
    return this.messageId;
  }
  
  public String getModelo() {
    return this.modelo;
  }
  
  public String getOdometer() {
    return this.odometer;
  }
  
  public String getOilStatus() {
    return this.oilStatus;
  }
  
  public String getOilValue() {
    return this.oilValue;
  }
  
  public String getPlaca() {
    return this.placa;
  }
  
  public Enums.WSResultId getSendCommandResultId() {
    return this.sendCommandResultId;
  }
  
  public String getSocketCode() {
    return this.socketCode;
  }
  
  public String getTPMSText() {
    return this.TPMSText;
  }
  
  public String getTireFL() {
    return this.tireFL;
  }
  
  public String getTireFR() {
    return this.tireFR;
  }
  
  public String getTireRL() {
    return this.tireRL;
  }
  
  public String getTireRR() {
    return this.tireRR;
  }
  
  public String getTireStatusFL() {
    return this.tireStatusFL;
  }
  
  public String getTireStatusFR() {
    return this.tireStatusFR;
  }
  
  public String getTireStatusRL() {
    return this.tireStatusRL;
  }
  
  public String getTireStatusRR() {
    return this.tireStatusRR;
  }
  
  public String getVersion() {
    return this.version;
  }
  
  public String getYear() {
    return this.year;
  }
  
  public boolean isValidPID() {
    int i = this.gas.length();
    boolean bool = false;
    if (i > 0 || this.oil.length() > 0 || this.odometer.length() > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i != 0 || this.tireFL.length() > 0 || this.tireFR.length() > 0 || this.tireRL.length() > 0 || this.tireRR.length() > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i != 0 || this.tireStatusFL.length() > 0 || this.tireStatusFR.length() > 0 || this.tireStatusRL.length() > 0 || this.tireStatusRR.length() > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i != 0 || this.modelo.length() > 0 || this.version.length() > 0 || this.year.length() > 0 || this.placa.length() > 0 || this.autonomy_km.length() > 0)
      bool = true; 
    return bool;
  }
  
  public void setAddress(String paramString) {}
  
  public void setAutonomy_km(String paramString) {
    this.autonomy_km = paramString;
  }
  
  public void setAutonomy_status(String paramString) {
    this.autonomy_status = paramString;
  }
  
  public void setAutonomy_text(String paramString) {
    this.autonomy_text = paramString;
  }
  
  public void setBatteryLevel(String paramString) {
    this.batteryLevel = paramString;
  }
  
  public void setEventDateTime(String paramString) {
    this.eventDateTime = paramString;
  }
  
  public void setGas(String paramString) {
    this.gas = paramString;
  }
  
  public void setGpsStatus(int paramInt) {
    this.gpsStatus = paramInt;
  }
  
  public void setIdResponse(Enums.ActionResultCode paramActionResultCode) {
    this.idResponse = paramActionResultCode;
  }
  
  public void setIdResponseOriginal(Enums.ActionResultCode paramActionResultCode) {
    this.idResponseOriginal = this.idResponse;
  }
  
  public void setIsM300(String paramString) {
    this.isM300 = paramString;
  }
  
  public void setLatitude(float paramFloat) {
    this.latitude = paramFloat;
  }
  
  public void setLongitude(float paramFloat) {
    this.longitude = paramFloat;
  }
  
  public void setMessageId(int paramInt) {
    this.messageId = paramInt;
  }
  
  public void setModelo(String paramString) {
    this.modelo = paramString;
  }
  
  public void setNickname(String paramString) {}
  
  public void setOdometer(String paramString) {
    this.odometer = paramString;
  }
  
  public void setOilStatus(String paramString) {
    this.oilStatus = paramString;
  }
  
  public void setOilValue(String paramString) {
    this.oilValue = paramString;
  }
  
  public void setPlaca(String paramString) {
    this.placa = paramString;
  }
  
  public void setSendCommandResultId(Enums.WSResultId paramWSResultId) {
    this.sendCommandResultId = paramWSResultId;
  }
  
  public void setSessionKey(String paramString) {}
  
  public void setSocketCode(String paramString) {
    this.socketCode = paramString;
  }
  
  public void setTPMSText(String paramString) {
    this.TPMSText = paramString;
  }
  
  public void setTireFL(String paramString) {
    this.tireFL = paramString;
  }
  
  public void setTireFR(String paramString) {
    this.tireFR = paramString;
  }
  
  public void setTireRL(String paramString) {
    this.tireRL = paramString;
  }
  
  public void setTireRR(String paramString) {
    this.tireRR = paramString;
  }
  
  public void setTireStatusFL(String paramString) {
    this.tireStatusFL = paramString;
  }
  
  public void setTireStatusFR(String paramString) {
    this.tireStatusFR = paramString;
  }
  
  public void setTireStatusRL(String paramString) {
    this.tireStatusRL = paramString;
  }
  
  public void setTireStatusRR(String paramString) {
    this.tireStatusRR = paramString;
  }
  
  public void setVersion(String paramString) {
    this.version = paramString;
  }
  
  public void setYear(String paramString) {
    this.year = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/ActionResultVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */