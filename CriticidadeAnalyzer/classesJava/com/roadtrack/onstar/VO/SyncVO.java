package com.roadtrack.onstar.VO;

public class SyncVO {
  private String address;
  
  private String category;
  
  private String date;
  
  private String deviceId;
  
  private String id_favs_history;
  
  private String id_sync;
  
  private String latlng;
  
  private String name;
  
  private String type_item;
  
  private String type_poi;
  
  private String user;
  
  public SyncVO() {}
  
  public SyncVO(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15) {
    this.user = paramString4;
    this.id_favs_history = paramString6;
    this.name = paramString7;
    this.address = paramString8;
    this.date = paramString9;
    this.deviceId = paramString10;
    this.type_item = paramString11;
    this.category = paramString12;
    this.latlng = paramString13;
    this.type_poi = paramString14;
    this.id_sync = paramString15;
  }
  
  public String getAddress() {
    return this.address;
  }
  
  public String getCategory() {
    return this.category;
  }
  
  public String getDate() {
    return this.date;
  }
  
  public String getDeviceId() {
    return this.deviceId;
  }
  
  public String getId_favs_history() {
    return this.id_favs_history;
  }
  
  public String getId_sync() {
    return this.id_sync;
  }
  
  public String getLatlng() {
    return this.latlng;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getType_item() {
    return this.type_item;
  }
  
  public String getType_poi() {
    return this.type_poi;
  }
  
  public String getUser() {
    return this.user;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/SyncVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */