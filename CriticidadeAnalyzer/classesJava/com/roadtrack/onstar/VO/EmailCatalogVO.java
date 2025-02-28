package com.roadtrack.onstar.VO;

public class EmailCatalogVO {
  private String email;
  
  private String user;
  
  public EmailCatalogVO(String paramString1, String paramString2, String paramString3) {
    setId(paramString1);
    setEmail(paramString2);
    setUser(paramString3);
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public String getUser() {
    return this.user;
  }
  
  public void setEmail(String paramString) {
    this.email = paramString;
  }
  
  public void setId(String paramString) {}
  
  public void setUser(String paramString) {
    this.user = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/EmailCatalogVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */