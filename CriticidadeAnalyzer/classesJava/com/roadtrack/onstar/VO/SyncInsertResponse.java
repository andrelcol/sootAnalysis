package com.roadtrack.onstar.VO;

public class SyncInsertResponse {
  private String date;
  
  private int id_favs;
  
  private int id_sync;
  
  private int status;
  
  public SyncInsertResponse() {}
  
  public SyncInsertResponse(int paramInt1, int paramInt2, String paramString, int paramInt3) {
    this.id_sync = paramInt1;
    this.id_favs = paramInt2;
    this.date = paramString;
    this.status = paramInt3;
  }
  
  public String getDate() {
    return this.date;
  }
  
  public int getId_favs() {
    return this.id_favs;
  }
  
  public int getId_sync() {
    return this.id_sync;
  }
  
  public int getStatus() {
    return this.status;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/SyncInsertResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */