package com.roadtrack.onstar.VO;

import com.roadtrack.onstar.utils.IExist;
import com.roadtrack.onstar.utils.Utilities;

public class FavoritesHistoryVO implements IExist<FavoritesHistoryVO> {
  private String address;
  
  private String category;
  
  private String date;
  
  private String device_id;
  
  private String id_cfg;
  
  private int id_favs_history;
  
  private String id_sync;
  
  private String latlng;
  
  private String name;
  
  private String type_item;
  
  private String type_poi;
  
  public FavoritesHistoryVO() {}
  
  public FavoritesHistoryVO(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10) {
    this.id_favs_history = paramInt;
    this.id_cfg = paramString1;
    this.name = paramString2;
    this.address = paramString3;
    this.date = paramString4;
    this.device_id = paramString5;
    this.type_item = paramString6;
    this.category = paramString7;
    this.latlng = paramString8;
    this.type_poi = paramString9;
    this.id_sync = paramString10;
    getSyncInfo();
  }
  
  private void getSyncInfo() {
    String str = this.device_id;
    if (str != null && str.length() > 0) {
      String[] arrayOfString = Utilities.getCommIdAndSerialNumber(this.device_id);
      String str2 = arrayOfString[0];
      str2 = arrayOfString[1];
      String str1 = arrayOfString[2];
    } 
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
  
  public String getDevice_id() {
    return this.device_id;
  }
  
  public String getId_cfg() {
    return this.id_cfg;
  }
  
  public int getId_favs_history() {
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
  
  public void setCheked(boolean paramBoolean) {}
  
  public void setName(String paramString) {
    this.name = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/FavoritesHistoryVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */