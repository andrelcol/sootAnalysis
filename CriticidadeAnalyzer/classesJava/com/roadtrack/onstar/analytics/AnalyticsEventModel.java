package com.roadtrack.onstar.analytics;

public class AnalyticsEventModel {
  private String content = "";
  
  private String id = "";
  
  private String name = "";
  
  public AnalyticsEventModel(String paramString1, String paramString2, String paramString3) {
    this.id = paramString1;
    this.name = paramString2;
    this.content = paramString3;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/analytics/AnalyticsEventModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */