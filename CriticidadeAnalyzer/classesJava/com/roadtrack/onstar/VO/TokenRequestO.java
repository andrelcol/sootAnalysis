package com.roadtrack.onstar.VO;

import com.roadtrack.onstar.utils.Utilities;

public class TokenRequestO {
  private String lpar1;
  
  private String lpar2;
  
  public TokenRequestO(String paramString1, String paramString2) {
    this.lpar1 = Utilities.EncryptMoipData(paramString1);
    this.lpar2 = Utilities.EncryptMoipData(paramString2);
  }
  
  public String getLpar1() {
    return this.lpar1;
  }
  
  public String getLpar2() {
    return this.lpar2;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/TokenRequestO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */