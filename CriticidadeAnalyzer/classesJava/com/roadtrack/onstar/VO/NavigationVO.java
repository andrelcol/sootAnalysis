package com.roadtrack.onstar.VO;

import com.roadtrack.onstar.utils.Utilities;

public class NavigationVO {
  private int actionId;
  
  private String address;
  
  private int alertId;
  
  private String event_date;
  
  private double latEnd;
  
  private double latStart;
  
  private double lonEnd;
  
  private double lonStart;
  
  private int messageId;
  
  private String rec_date;
  
  public int getActionId() {
    return this.actionId;
  }
  
  public String getAddress() {
    if (this.address == null)
      this.address = ""; 
    return this.address;
  }
  
  public int getAlertId() {
    return this.alertId;
  }
  
  public String getEvent_Date() {
    String str = this.event_date;
    if (str == null || str.length() == 0)
      this.event_date = Utilities.getDateTime(); 
    return this.event_date;
  }
  
  public double getLatEnd() {
    return this.latEnd;
  }
  
  public double getLatStart() {
    return this.latStart;
  }
  
  public double getLonEnd() {
    return this.lonEnd;
  }
  
  public double getLonStart() {
    return this.lonStart;
  }
  
  public int getMessageId() {
    return this.messageId;
  }
  
  public String getRec_Date() {
    String str = this.rec_date;
    if (str == null || str.length() == 0)
      this.rec_date = Utilities.getDateTime(); 
    return this.rec_date;
  }
  
  public void setActionId(int paramInt) {
    this.actionId = paramInt;
  }
  
  public void setAddress(String paramString) {
    this.address = paramString;
  }
  
  public void setAlertId(int paramInt) {
    this.alertId = paramInt;
  }
  
  public void setEvent_date(String paramString) {
    this.event_date = paramString;
  }
  
  public void setLatEnd(double paramDouble) {
    this.latEnd = paramDouble;
  }
  
  public void setLatStart(double paramDouble) {
    this.latStart = paramDouble;
  }
  
  public void setLonEnd(double paramDouble) {
    this.lonEnd = paramDouble;
  }
  
  public void setLonStart(double paramDouble) {
    this.lonStart = paramDouble;
  }
  
  public void setMessageId(int paramInt) {
    this.messageId = paramInt;
  }
  
  public void setNavigationId(int paramInt) {}
  
  public void setRec_date(String paramString) {
    this.rec_date = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/NavigationVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */