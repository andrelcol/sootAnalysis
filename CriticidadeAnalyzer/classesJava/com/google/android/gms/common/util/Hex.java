package com.google.android.gms.common.util;

public class Hex {
  private static final char[] zzgz = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'a', 'b', 'c', 'd', 'e', 'f' };
  
  public static String bytesToStringLowercase(byte[] paramArrayOfbyte) {
    char[] arrayOfChar = new char[paramArrayOfbyte.length << 1];
    byte b = 0;
    int i = 0;
    while (b < paramArrayOfbyte.length) {
      int k = paramArrayOfbyte[b] & 0xFF;
      int j = i + 1;
      char[] arrayOfChar1 = zzgz;
      arrayOfChar[i] = arrayOfChar1[k >>> 4];
      i = j + 1;
      arrayOfChar[j] = arrayOfChar1[k & 0xF];
      b++;
    } 
    return new String(arrayOfChar);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/Hex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */