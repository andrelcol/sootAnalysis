package com.roadtrack.onstar.VO;

public class MapUpdateVO {
  private int fileMapOnParts;
  
  private int fileMapStatus;
  
  private long idMapUpdate;
  
  private String p8IdSerial;
  
  private String p8MapVersion;
  
  private String serverFileName;
  
  private String serverMapVersion;
  
  private String upgradeOldversionNewversion;
  
  private int userWantsUpdrade;
  
  public int getFileMapOnParts() {
    return this.fileMapOnParts;
  }
  
  public int getFileMapStatus() {
    return this.fileMapStatus;
  }
  
  public long getIdMapUpdate() {
    return this.idMapUpdate;
  }
  
  public String getP8IdSerial() {
    return this.p8IdSerial;
  }
  
  public String getP8MapVersion() {
    return this.p8MapVersion;
  }
  
  public String getServerFileName() {
    return this.serverFileName;
  }
  
  public String getServerMapVersion() {
    return this.serverMapVersion;
  }
  
  public String getUpgradeOldversionNewversion() {
    return this.upgradeOldversionNewversion;
  }
  
  public int isUserWantsUpdrade() {
    return this.userWantsUpdrade;
  }
  
  public void setFileMapOnParts(int paramInt) {
    this.fileMapOnParts = paramInt;
  }
  
  public void setFileMapStatus(int paramInt) {
    this.fileMapStatus = paramInt;
  }
  
  public void setIdMapUpdate(int paramInt) {
    this.idMapUpdate = paramInt;
  }
  
  public void setP8IdSerial(String paramString) {
    this.p8IdSerial = paramString;
  }
  
  public void setP8MapVersion(String paramString) {
    this.p8MapVersion = paramString;
  }
  
  public void setServerFileName(String paramString) {
    this.serverFileName = paramString;
  }
  
  public void setServerMapVersion(String paramString) {
    this.serverMapVersion = paramString;
  }
  
  public void setUpgradeOldversionNewversion(String paramString) {
    this.upgradeOldversionNewversion = paramString;
  }
  
  public void setUserWantsUpdrade(int paramInt) {
    this.userWantsUpdrade = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.upgradeOldversionNewversion);
    stringBuilder.append(" :\n");
    stringBuilder.append("P8 Serial:");
    stringBuilder.append(getP8IdSerial());
    stringBuilder.append("\n");
    stringBuilder.append("Map Status:");
    int i = this.fileMapStatus;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i == 5)
                stringBuilder.append("UPDATED"); 
            } else {
              stringBuilder.append("UPDATING");
            } 
          } else {
            stringBuilder.append("DOWNLOADED");
          } 
        } else {
          stringBuilder.append("DOWNLOADING");
        } 
      } else {
        stringBuilder.append("PENDING");
      } 
    } else {
      stringBuilder.append("SIN_INFO");
    } 
    stringBuilder.append("\n");
    stringBuilder.append("User want this update:");
    i = isUserWantsUpdrade();
    if (i != 0) {
      if (i == 1)
        stringBuilder.append("YES"); 
    } else {
      stringBuilder.append("NO");
    } 
    return stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/MapUpdateVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */