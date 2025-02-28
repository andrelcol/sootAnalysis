package com.roadtrack.onstar.CryptLibPackage;

import android.util.Base64;
import com.roadtrack.onstar.utils.Utilities;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptLib {
  Cipher _cx = Cipher.getInstance("AES/CBC/PKCS5Padding");
  
  byte[] _iv = new byte[16];
  
  byte[] _key = new byte[32];
  
  public static String SHA256(String paramString, int paramInt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    String str;
    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    messageDigest.update(paramString.getBytes("ISO-8859-1"));
    byte[] arrayOfByte = messageDigest.digest();
    StringBuffer stringBuffer = new StringBuffer();
    int i = arrayOfByte.length;
    for (byte b = 0; b < i; b++) {
      stringBuffer.append(String.format("%02x", new Object[] { Byte.valueOf(arrayOfByte[b]) }));
    } 
    if (paramInt > stringBuffer.toString().length()) {
      str = stringBuffer.toString();
    } else {
      str = stringBuffer.toString().substring(0, paramInt);
    } 
    return str;
  }
  
  private String encryptDecrypt(String paramString1, String paramString2, EncryptMode paramEncryptMode, String paramString3) throws UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    int i = (paramString2.getBytes("ISO-8859-1")).length;
    int j = (paramString2.getBytes("ISO-8859-1")).length;
    byte[] arrayOfByte = this._key;
    if (j > arrayOfByte.length)
      i = arrayOfByte.length; 
    j = (paramString3.getBytes("ISO-8859-1")).length;
    int k = (paramString3.getBytes("ISO-8859-1")).length;
    arrayOfByte = this._iv;
    if (k > arrayOfByte.length)
      j = arrayOfByte.length; 
    System.arraycopy(paramString2.getBytes("ISO-8859-1"), 0, this._key, 0, i);
    System.arraycopy(paramString3.getBytes("ISO-8859-1"), 0, this._iv, 0, j);
    SecretKeySpec secretKeySpec = new SecretKeySpec(this._key, "AES");
    IvParameterSpec ivParameterSpec = new IvParameterSpec(this._iv);
    if (paramEncryptMode.equals(EncryptMode.ENCRYPT)) {
      this._cx.init(1, secretKeySpec, ivParameterSpec);
      return Base64.encodeToString(this._cx.doFinal(paramString1.getBytes("ISO-8859-1")), 2);
    } 
    if (paramEncryptMode.equals(EncryptMode.DECRYPT)) {
      this._cx.init(2, secretKeySpec, ivParameterSpec);
      byte[] arrayOfByte1 = getOutputText(this._cx, paramString1);
      return new String(arrayOfByte1, 0, arrayOfByte1.length, "ISO-8859-1");
    } 
    return "";
  }
  
  private byte[] getOutputText(Cipher paramCipher, String paramString) {
    try {
      return paramCipher.doFinal(Base64.decode(paramString.getBytes(), 2));
    } catch (IllegalBlockSizeException illegalBlockSizeException) {
      Utilities.escribeArchivo("CryptLib", "Error", illegalBlockSizeException.getMessage());
    } catch (BadPaddingException badPaddingException) {
      Utilities.escribeArchivo("CryptLib", "Error", badPaddingException.getMessage());
    } 
    return null;
  }
  
  public String decrypt(String paramString1, String paramString2, String paramString3) throws InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    return encryptDecrypt(paramString1, paramString2, EncryptMode.DECRYPT, paramString3);
  }
  
  public String encrypt(String paramString1, String paramString2, String paramString3) throws InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    return encryptDecrypt(paramString1, paramString2, EncryptMode.ENCRYPT, paramString3);
  }
  
  private enum EncryptMode {
    DECRYPT, ENCRYPT;
    
    private static final EncryptMode[] $VALUES;
    
    static {
      $VALUES = new EncryptMode[] { ENCRYPT, DECRYPT };
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/CryptLibPackage/CryptLib.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */