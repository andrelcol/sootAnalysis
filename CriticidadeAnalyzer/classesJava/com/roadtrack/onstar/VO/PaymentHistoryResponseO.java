package com.roadtrack.onstar.VO;

public class PaymentHistoryResponseO {
  private String chpres1;
  
  private String chpres2;
  
  private String chpres3;
  
  private String chpres4;
  
  private String chpres5;
  
  private String chpres6;
  
  public String getChpres1() {
    return this.chpres1;
  }
  
  public String getChpres2() {
    return this.chpres2;
  }
  
  public String getChpres3() {
    return this.chpres3;
  }
  
  public String getChpres4() {
    return this.chpres4;
  }
  
  public String getChpres5() {
    return this.chpres5;
  }
  
  public String getChpres6() {
    return this.chpres6;
  }
  
  public boolean isValidResponse() {
    boolean bool = getChpres1().isEmpty();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!bool) {
      bool1 = bool2;
      if (!getChpres2().isEmpty()) {
        bool1 = bool2;
        if (!getChpres3().isEmpty()) {
          bool1 = bool2;
          if (!getChpres4().isEmpty()) {
            bool1 = bool2;
            if (!getChpres5().isEmpty()) {
              boolean bool3;
              try {
                bool3 = Integer.parseInt(getChpres2());
              } catch (NumberFormatException numberFormatException) {
                bool3 = false;
              } 
              bool1 = bool2;
              if (bool3)
                bool1 = true; 
            } 
          } 
        } 
      } 
    } 
    return bool1;
  }
  
  public void setChpres1(String paramString) {
    this.chpres1 = paramString;
  }
  
  public void setChpres2(String paramString) {
    this.chpres2 = paramString;
  }
  
  public void setChpres3(String paramString) {
    this.chpres3 = paramString;
  }
  
  public void setChpres4(String paramString) {
    this.chpres4 = paramString;
  }
  
  public void setChpres5(String paramString) {
    this.chpres5 = paramString;
  }
  
  public void setChpres6(String paramString) {
    this.chpres6 = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/PaymentHistoryResponseO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */