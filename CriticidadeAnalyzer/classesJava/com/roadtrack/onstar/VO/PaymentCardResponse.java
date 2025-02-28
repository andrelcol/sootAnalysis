package com.roadtrack.onstar.VO;

import java.io.Serializable;

public class PaymentCardResponse implements Serializable {
  private String gcmures1;
  
  private String gcmures2;
  
  private String gcmures3;
  
  public PaymentCardResponse() {}
  
  public PaymentCardResponse(String paramString1, String paramString2, String paramString3) {
    this.gcmures1 = paramString1;
    this.gcmures2 = paramString2;
    this.gcmures3 = paramString3;
  }
  
  public String getGcmures1() {
    return this.gcmures1;
  }
  
  public String getGcmures2() {
    return this.gcmures2;
  }
  
  public String getGcmures3() {
    return this.gcmures3;
  }
  
  public boolean isValidObject() {
    boolean bool;
    if (getGcmures2() != null && !getGcmures2().isEmpty()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setGcmures1(String paramString) {
    this.gcmures1 = paramString;
  }
  
  public void setGcmures2(String paramString) {
    this.gcmures2 = paramString;
  }
  
  public void setGcmures3(String paramString) {
    this.gcmures3 = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/PaymentCardResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */