package com.roadtrack.onstar.VO;

public class SuscriptionResponse {
  private String subscres1;
  
  private String subscres2;
  
  private String subscres3;
  
  public String getSubscres1() {
    return this.subscres1;
  }
  
  public String getSubscres2() {
    return this.subscres2;
  }
  
  public String getSubscres3() {
    return this.subscres3;
  }
  
  public boolean isValidObject() {
    boolean bool;
    if (getSubscres2() != null && !getSubscres2().isEmpty()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setSubscres1(String paramString) {
    this.subscres1 = paramString;
  }
  
  public void setSubscres2(String paramString) {
    this.subscres2 = paramString;
  }
  
  public void setSubscres3(String paramString) {
    this.subscres3 = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/SuscriptionResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */