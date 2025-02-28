package com.roadtrack.onstar.VO;

public class TransactionResponse {
  private String cposres1;
  
  private String cposres2;
  
  private String cposres3;
  
  public String getCposres1() {
    return this.cposres1;
  }
  
  public String getCposres2() {
    return this.cposres2;
  }
  
  public String getCposres3() {
    return this.cposres3;
  }
  
  public boolean isValidObject() {
    boolean bool;
    if (getCposres2() != null && !getCposres2().isEmpty()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setCposres1(String paramString) {
    this.cposres1 = paramString;
  }
  
  public void setCposres2(String paramString) {
    this.cposres2 = paramString;
  }
  
  public void setCposres3(String paramString) {
    this.cposres3 = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/TransactionResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */