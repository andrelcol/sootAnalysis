package com.roadtrack.onstar.VO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class UserDevicesVO {
  private ArrayList<String> Actions;
  
  private String DeviceId;
  
  private String Name;
  
  private String Phone;
  
  private LinkedHashMap<String, LinkedHashMap<String, Integer>> buttomActions;
  
  private String commserverid;
  
  private String dateLastFindeMe;
  
  private String deviceTypeId;
  
  private LinkedHashMap<String, LinkedHashMap<String, Integer>> lateralActions;
  
  private LinkedHashMap<String, LinkedHashMap<String, Integer>> mainActions;
  
  private LinkedHashMap<String, LinkedHashMap<String, Integer>> navigationActions;
  
  private LinkedHashMap<String, LinkedHashMap<String, Integer>> pidActions;
  
  private LinkedList<String> repeatedActions;
  
  private String seguroApp;
  
  private String serialnumber;
  
  public ArrayList<String> getActions() {
    return this.Actions;
  }
  
  public LinkedHashMap<String, LinkedHashMap<String, Integer>> getButtomActions() {
    return this.buttomActions;
  }
  
  public String getCommserverid() {
    return this.commserverid;
  }
  
  public String getDateLastFindeMe() {
    return this.dateLastFindeMe;
  }
  
  public String getDeviceId() {
    return this.DeviceId;
  }
  
  public String getDeviceTypeId() {
    return this.deviceTypeId;
  }
  
  public LinkedHashMap<String, LinkedHashMap<String, Integer>> getLateralActions() {
    return this.lateralActions;
  }
  
  public LinkedHashMap<String, LinkedHashMap<String, Integer>> getMainActions() {
    return this.mainActions;
  }
  
  public String getName() {
    return this.Name;
  }
  
  public LinkedHashMap<String, LinkedHashMap<String, Integer>> getNavigationActions() {
    return this.navigationActions;
  }
  
  public String getPhone() {
    return this.Phone;
  }
  
  public LinkedHashMap<String, LinkedHashMap<String, Integer>> getPidActions() {
    return this.pidActions;
  }
  
  public LinkedList<String> getRepeatedActions() {
    return this.repeatedActions;
  }
  
  public String getSeguroApp() {
    return this.seguroApp;
  }
  
  public String getSerialnumber() {
    return this.serialnumber;
  }
  
  public void setActions(ArrayList<String> paramArrayList) {
    this.Actions = paramArrayList;
  }
  
  public void setBranchdealership(String paramString) {}
  
  public void setButtomActions(LinkedHashMap<String, LinkedHashMap<String, Integer>> paramLinkedHashMap) {
    this.buttomActions = paramLinkedHashMap;
  }
  
  public void setCommserverid(String paramString) {
    this.commserverid = paramString;
  }
  
  public void setCurrentEnergyModeId(String paramString) {}
  
  public void setCve_brand(String paramString) {}
  
  public void setCve_model(String paramString) {}
  
  public void setDateLastFindeMe(String paramString) {
    this.dateLastFindeMe = paramString;
  }
  
  public void setDeviceId(String paramString) {
    this.DeviceId = paramString;
  }
  
  public void setDeviceTypeId(String paramString) {
    this.deviceTypeId = paramString;
  }
  
  public void setDistanceUnit(String paramString) {}
  
  public void setDriverName(String paramString) {}
  
  public void setIdResponse(String paramString) {}
  
  public void setLateralActions(LinkedHashMap<String, LinkedHashMap<String, Integer>> paramLinkedHashMap) {
    this.lateralActions = paramLinkedHashMap;
  }
  
  public void setMainActions(LinkedHashMap<String, LinkedHashMap<String, Integer>> paramLinkedHashMap) {
    this.mainActions = paramLinkedHashMap;
  }
  
  public void setName(String paramString) {
    this.Name = paramString;
  }
  
  public void setNavigationActions(LinkedHashMap<String, LinkedHashMap<String, Integer>> paramLinkedHashMap) {
    this.navigationActions = paramLinkedHashMap;
  }
  
  public void setPhone(String paramString) {
    this.Phone = paramString;
  }
  
  public void setPidActions(LinkedHashMap<String, LinkedHashMap<String, Integer>> paramLinkedHashMap) {
    this.pidActions = paramLinkedHashMap;
  }
  
  public void setPurchasingdealership(String paramString) {}
  
  public void setRepeatedActions(LinkedList<String> paramLinkedList) {
    this.repeatedActions = paramLinkedList;
  }
  
  public void setSeguroApp(String paramString) {
    this.seguroApp = paramString;
  }
  
  public void setSerialnumber(String paramString) {
    this.serialnumber = paramString;
  }
  
  public void setSpeedAlertActive(String paramString) {}
  
  public void setSpeedLimit(String paramString) {}
  
  public void setStolenCode(String paramString) {}
  
  public void setTheftAlertActive(String paramString) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/UserDevicesVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */