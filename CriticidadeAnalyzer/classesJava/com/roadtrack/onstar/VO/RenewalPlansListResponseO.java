package com.roadtrack.onstar.VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RenewalPlansListResponseO implements Serializable {
  private String cpres1;
  
  private String cpres2;
  
  private String cpres3;
  
  private String cpres4;
  
  private List<RenewalPlanO> cpres5;
  
  private String cpres6;
  
  private String cpres7;
  
  private String cpres8;
  
  private String cpres9;
  
  public String getCpres1() {
    return this.cpres1;
  }
  
  public String getCpres2() {
    return this.cpres2;
  }
  
  public String getCpres3() {
    return this.cpres3;
  }
  
  public String getCpres4() {
    return this.cpres4;
  }
  
  public List<RenewalPlanO> getCpres5() {
    return this.cpres5;
  }
  
  public String getCpres6() {
    return this.cpres6;
  }
  
  public String getCpres7() {
    return this.cpres7;
  }
  
  public String getCpres8() {
    return this.cpres8;
  }
  
  public String getCpres9() {
    return this.cpres9;
  }
  
  public void isOMSPlan() {
    ArrayList<RenewalPlanO> arrayList = new ArrayList();
    if (this.cpres5 != null) {
      for (byte b = 0; b < this.cpres5.size(); b++)
        arrayList.add(this.cpres5.get(b)); 
      this.cpres5 = new ArrayList<RenewalPlanO>();
      this.cpres5 = arrayList;
    } 
  }
  
  public boolean isValidObject() {
    ArrayList<RenewalPlanO> arrayList = new ArrayList();
    boolean bool = this.cpres1.isEmpty();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!bool) {
      bool1 = bool2;
      if (!this.cpres2.isEmpty()) {
        bool1 = bool2;
        if (!this.cpres3.isEmpty()) {
          bool1 = bool2;
          if (!this.cpres4.isEmpty()) {
            List<RenewalPlanO> list = this.cpres5;
            bool1 = bool2;
            if (list != null) {
              bool1 = bool2;
              if (list.size() > 0) {
                for (byte b = 0; b < this.cpres5.size(); b++) {
                  if (((RenewalPlanO)this.cpres5.get(b)).isValidObject())
                    arrayList.add(this.cpres5.get(b)); 
                } 
                this.cpres5 = new ArrayList<RenewalPlanO>();
                this.cpres5 = arrayList;
                bool1 = bool2;
                if (this.cpres5.size() > 0)
                  bool1 = true; 
              } 
            } 
          } 
        } 
      } 
    } 
    return bool1;
  }
  
  public void setCpres1(String paramString) {
    this.cpres1 = paramString;
  }
  
  public void setCpres2(String paramString) {
    this.cpres2 = paramString;
  }
  
  public void setCpres3(String paramString) {
    this.cpres3 = paramString;
  }
  
  public void setCpres4(String paramString) {
    this.cpres4 = paramString;
  }
  
  public void setCpres5(List<RenewalPlanO> paramList) {
    this.cpres5 = paramList;
  }
  
  public void setCpres6(String paramString) {
    this.cpres6 = paramString;
  }
  
  public void setCpres7(String paramString) {
    this.cpres7 = paramString;
  }
  
  public void setCpres8(String paramString) {
    this.cpres8 = paramString;
  }
  
  public void setCpres9(String paramString) {
    this.cpres9 = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/RenewalPlansListResponseO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */