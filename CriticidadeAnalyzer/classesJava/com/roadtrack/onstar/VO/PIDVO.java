package com.roadtrack.onstar.VO;

import android.content.Context;

public class PIDVO {
  private String TPMSText = "";
  
  private String autonomy_km = "";
  
  private String autonomy_status = "";
  
  private String autonomy_text = "";
  
  private String batterylevel = "";
  
  private String eventDate = "";
  
  private String gas = "";
  
  private String isM300 = "";
  
  private int messageId;
  
  private String modelo = "";
  
  private String odometer = "";
  
  private String oilStatus = "";
  
  private String oilValue = "";
  
  private String placa = "";
  
  private String tire_fl = "";
  
  private String tire_fr = "";
  
  private String tire_rl = "";
  
  private String tire_rr = "";
  
  private String tire_status_fl = "";
  
  private String tire_status_fr = "";
  
  private String tire_status_rl = "";
  
  private String tire_status_rr = "";
  
  private String version = "";
  
  private String year = "";
  
  public PIDVO(Context paramContext) {}
  
  public String getAutonomy_km() {
    return this.autonomy_km;
  }
  
  public String getAutonomy_status() {
    return this.autonomy_status;
  }
  
  public String getAutonomy_text() {
    return this.autonomy_text;
  }
  
  public String getBatterylevel() {
    return this.batterylevel;
  }
  
  public String getEventDate() {
    return this.eventDate;
  }
  
  public String getGas() {
    return this.gas;
  }
  
  public String getIsM300() {
    return this.isM300;
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
  
  public String getTPMSText() {
    return this.TPMSText;
  }
  
  public String getTire_fl() {
    return this.tire_fl;
  }
  
  public String getTire_fr() {
    return this.tire_fr;
  }
  
  public String getTire_rl() {
    return this.tire_rl;
  }
  
  public String getTire_rr() {
    return this.tire_rr;
  }
  
  public String getTire_status_fl() {
    return this.tire_status_fl;
  }
  
  public String getTire_status_fr() {
    return this.tire_status_fr;
  }
  
  public String getTire_status_rl() {
    return this.tire_status_rl;
  }
  
  public String getTire_status_rr() {
    return this.tire_status_rr;
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
    if (i > 0 || this.oilValue.length() > 0 || this.odometer.length() > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i != 0 || this.tire_fl.length() > 0 || this.tire_fr.length() > 0 || this.tire_rl.length() > 0 || this.tire_rr.length() > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i != 0 || this.tire_status_fl.length() > 0 || this.tire_status_fr.length() > 0 || this.tire_status_rl.length() > 0 || this.tire_status_rr.length() > 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i != 0 || this.modelo.length() > 0 || this.version.length() > 0 || this.year.length() > 0 || this.placa.length() > 0 || this.autonomy_km.length() > 0)
      bool = true; 
    return bool;
  }
  
  public void setAutonomy_km(String paramString) {
    this.autonomy_km = paramString;
  }
  
  public void setAutonomy_status(String paramString) {
    this.autonomy_status = paramString;
  }
  
  public void setAutonomy_text(String paramString) {
    this.autonomy_text = paramString;
  }
  
  public void setBatterylevel(String paramString) {
    this.batterylevel = paramString;
  }
  
  public void setDeviceId(int paramInt) {}
  
  public void setEventDate(String paramString) {
    this.eventDate = paramString;
  }
  
  public void setGas(String paramString) {
    this.gas = paramString;
  }
  
  public void setIsM300(String paramString) {
    this.isM300 = paramString;
  }
  
  public void setMessageId(int paramInt) {
    this.messageId = paramInt;
  }
  
  public void setModelo(String paramString) {
    this.modelo = paramString;
  }
  
  public void setOdometer(String paramString) {
    this.odometer = paramString;
  }
  
  public void setOilStatus(String paramString) {
    this.oilStatus = paramString;
  }
  
  public void setOilValue(String paramString) {
    this.oilValue = paramString;
  }
  
  public void setPidId(long paramLong) {}
  
  public void setPlaca(String paramString) {
    this.placa = paramString;
  }
  
  public void setTPMSText(String paramString) {
    this.TPMSText = paramString;
  }
  
  public void setTire_fl(String paramString) {
    this.tire_fl = paramString;
  }
  
  public void setTire_fr(String paramString) {
    this.tire_fr = paramString;
  }
  
  public void setTire_rl(String paramString) {
    this.tire_rl = paramString;
  }
  
  public void setTire_rr(String paramString) {
    this.tire_rr = paramString;
  }
  
  public void setTire_status_fl(String paramString) {
    this.tire_status_fl = paramString;
  }
  
  public void setTire_status_fr(String paramString) {
    this.tire_status_fr = paramString;
  }
  
  public void setTire_status_rl(String paramString) {
    this.tire_status_rl = paramString;
  }
  
  public void setTire_status_rr(String paramString) {
    this.tire_status_rr = paramString;
  }
  
  public void setVersion(String paramString) {
    this.version = paramString;
  }
  
  public void setYear(String paramString) {
    this.year = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/PIDVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */