package com.roadtrack.onstar.utils;

public class CryptedSoapGenerator {
  private static CryptedSoapGenerator instance;
  
  public static CryptedSoapGenerator getInstance() {
    if (instance == null)
      instance = new CryptedSoapGenerator(); 
    return instance;
  }
  
  public String getDecryptedResult(String paramString) {
    return Utilities.Decrypt(paramString);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/CryptedSoapGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */