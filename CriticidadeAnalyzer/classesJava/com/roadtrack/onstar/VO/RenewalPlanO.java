package com.roadtrack.onstar.VO;

import java.io.Serializable;

public class RenewalPlanO implements Serializable {
  private String cpapres1;
  
  private String cpapres10;
  
  private String cpapres11;
  
  private String cpapres12;
  
  private String cpapres13;
  
  private String cpapres14;
  
  private String cpapres15;
  
  private String cpapres2;
  
  private String cpapres3;
  
  private String cpapres4;
  
  private String cpapres5;
  
  private String cpapres6;
  
  private String cpapres7;
  
  private String cpapres8;
  
  private String cpapres9;
  
  public String getCpapres1() {
    return this.cpapres1;
  }
  
  public String getCpapres10() {
    return this.cpapres10;
  }
  
  public String getCpapres11() {
    return this.cpapres11;
  }
  
  public String getCpapres12() {
    return this.cpapres12;
  }
  
  public String getCpapres13() {
    return this.cpapres13;
  }
  
  public String getCpapres14() {
    return this.cpapres14;
  }
  
  public String getCpapres15() {
    return this.cpapres15;
  }
  
  public String getCpapres2() {
    return this.cpapres2;
  }
  
  public String getCpapres3() {
    return this.cpapres3;
  }
  
  public String getCpapres4() {
    return this.cpapres4;
  }
  
  public String getCpapres5() {
    return this.cpapres5;
  }
  
  public String getCpapres6() {
    return this.cpapres6;
  }
  
  public String getCpapres7() {
    return this.cpapres7;
  }
  
  public String getCpapres8() {
    return this.cpapres8;
  }
  
  public String getCpapres9() {
    return this.cpapres9;
  }
  
  public boolean isValidObject() {
    boolean bool;
    if (!this.cpapres1.isEmpty() && !this.cpapres2.isEmpty() && !this.cpapres3.isEmpty() && !this.cpapres5.isEmpty() && !this.cpapres6.isEmpty() && !this.cpapres7.isEmpty() && !this.cpapres10.isEmpty() && !this.cpapres11.isEmpty() && !this.cpapres13.isEmpty() && !this.cpapres15.isEmpty()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setCpapres1(String paramString) {
    this.cpapres1 = paramString;
  }
  
  public void setCpapres10(String paramString) {
    this.cpapres10 = paramString;
  }
  
  public void setCpapres11(String paramString) {
    this.cpapres11 = paramString;
  }
  
  public void setCpapres12(String paramString) {
    this.cpapres12 = paramString;
  }
  
  public void setCpapres13(String paramString) {
    this.cpapres13 = paramString;
  }
  
  public void setCpapres14(String paramString) {
    this.cpapres14 = paramString;
  }
  
  public void setCpapres15(String paramString) {
    this.cpapres15 = paramString;
  }
  
  public void setCpapres2(String paramString) {
    this.cpapres2 = paramString;
  }
  
  public void setCpapres3(String paramString) {
    this.cpapres3 = paramString;
  }
  
  public void setCpapres4(String paramString) {
    this.cpapres4 = paramString;
  }
  
  public void setCpapres5(String paramString) {
    this.cpapres5 = paramString;
  }
  
  public void setCpapres6(String paramString) {
    this.cpapres6 = paramString;
  }
  
  public void setCpapres7(String paramString) {
    this.cpapres7 = paramString;
  }
  
  public void setCpapres8(String paramString) {
    this.cpapres8 = paramString;
  }
  
  public void setCpapres9(String paramString) {
    this.cpapres9 = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/RenewalPlanO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */