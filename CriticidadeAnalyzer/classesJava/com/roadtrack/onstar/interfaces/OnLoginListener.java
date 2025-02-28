package com.roadtrack.onstar.interfaces;

public interface OnLoginListener {
  void onLoginFailAccountOrPass(String paramString1, String paramString2);
  
  void onLoginFailConection(String paramString);
  
  void onLoginOK(String paramString1, String paramString2, String paramString3);
  
  void onLoginServiceDown(String paramString1, String paramString2);
  
  void onLoginServiceUnavailable(String paramString);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/interfaces/OnLoginListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */