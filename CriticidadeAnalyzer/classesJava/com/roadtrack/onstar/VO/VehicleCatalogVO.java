package com.roadtrack.onstar.VO;

import java.io.Serializable;

public class VehicleCatalogVO implements Serializable {
  private String DTC_Selector;
  
  private String Vehicle_GTM;
  
  private String Vehicle_IDGTM;
  
  private String amountToPay;
  
  private String dateCardExpiration;
  
  private String dateExpire;
  
  private String deviceId;
  
  private String id;
  
  private String isValidCard;
  
  private String lastCardNumber;
  
  private String monetaryCurrency;
  
  private String payAttemptStatus;
  
  private String planStatusInform;
  
  private String plan_app;
  
  private String selected;
  
  private String status_renewal_account;
  
  private String status_renewal_dialog;
  
  private String user;
  
  private String vehicleName;
  
  public VehicleCatalogVO() {}
  
  public VehicleCatalogVO(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10) {
    this.id = paramString1;
    this.deviceId = paramString2;
    this.user = paramString3;
    this.selected = paramString4;
    this.Vehicle_IDGTM = paramString9;
    this.Vehicle_GTM = paramString5;
    this.DTC_Selector = paramString6;
    this.status_renewal_account = paramString7;
    this.status_renewal_dialog = paramString8;
    this.vehicleName = paramString10;
  }
  
  public VehicleCatalogVO(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13) {
    this.id = paramString1;
    this.deviceId = paramString2;
    this.user = paramString3;
    this.selected = paramString4;
    this.Vehicle_IDGTM = paramString9;
    this.Vehicle_GTM = paramString5;
    this.DTC_Selector = paramString6;
    this.status_renewal_account = paramString7;
    this.status_renewal_dialog = paramString8;
    this.vehicleName = paramString10;
    this.isValidCard = paramString11;
    this.dateCardExpiration = paramString12;
    this.lastCardNumber = paramString13;
  }
  
  public VehicleCatalogVO(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16) {
    this.id = paramString1;
    this.deviceId = paramString2;
    this.user = paramString3;
    this.selected = paramString4;
    this.Vehicle_IDGTM = paramString9;
    this.Vehicle_GTM = paramString5;
    this.DTC_Selector = paramString6;
    this.status_renewal_account = paramString7;
    this.status_renewal_dialog = paramString8;
    this.vehicleName = paramString10;
    this.dateExpire = paramString11;
    this.plan_app = paramString13;
    this.isValidCard = paramString14;
    this.dateCardExpiration = paramString15;
    this.lastCardNumber = paramString16;
  }
  
  public String getAmountToPay() {
    return this.amountToPay;
  }
  
  public String getDTC_Selector() {
    return this.DTC_Selector;
  }
  
  public String getDateCardExpiration() {
    return this.dateCardExpiration;
  }
  
  public String getDateExpire() {
    return this.dateExpire;
  }
  
  public String getDeviceId() {
    return this.deviceId;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getLastCardNumber() {
    return this.lastCardNumber;
  }
  
  public String getMonetaryCurrency() {
    return this.monetaryCurrency;
  }
  
  public String getPayAttemptStatus() {
    return this.payAttemptStatus;
  }
  
  public String getPlanStatusInform() {
    return this.planStatusInform;
  }
  
  public String getPlan_app() {
    return this.plan_app;
  }
  
  public String getSelected() {
    return this.selected;
  }
  
  public String getStatus_renewal_account() {
    return this.status_renewal_account;
  }
  
  public String getStatus_renewal_dialog() {
    return this.status_renewal_dialog;
  }
  
  public String getUser() {
    return this.user;
  }
  
  public String getVehicleName() {
    return this.vehicleName;
  }
  
  public String getVehicle_GTM() {
    return this.Vehicle_GTM;
  }
  
  public String getVehicle_IDGTM() {
    return this.Vehicle_IDGTM;
  }
  
  public String isValidCard() {
    return this.isValidCard;
  }
  
  public void setAmountToPay(String paramString) {
    this.amountToPay = paramString;
  }
  
  public void setDTC_Selector(String paramString) {
    this.DTC_Selector = paramString;
  }
  
  public void setDateCardExpiration(String paramString) {
    this.dateCardExpiration = paramString;
  }
  
  public void setDeviceId(String paramString) {
    this.deviceId = paramString;
  }
  
  public void setLastCardNumber(String paramString) {
    this.lastCardNumber = paramString;
  }
  
  public void setMonetaryCurrency(String paramString) {
    this.monetaryCurrency = paramString;
  }
  
  public void setPayAttemptStatus(String paramString) {
    this.payAttemptStatus = paramString;
  }
  
  public void setPlanStatusInform(String paramString) {
    this.planStatusInform = paramString;
  }
  
  public void setPlan_app(String paramString) {
    this.plan_app = paramString;
  }
  
  public void setSelected(String paramString) {
    this.selected = paramString;
  }
  
  public void setStatus_renewal_account(String paramString) {
    this.status_renewal_account = paramString;
  }
  
  public void setUser(String paramString) {
    this.user = paramString;
  }
  
  public void setValidCard(String paramString) {
    this.isValidCard = paramString;
  }
  
  public void setVehicleName(String paramString) {
    this.vehicleName = paramString;
  }
  
  public void setVehicle_GTM(String paramString) {
    this.Vehicle_GTM = paramString;
  }
  
  public void setVehicle_IDGTM(String paramString) {
    this.Vehicle_IDGTM = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/VehicleCatalogVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */