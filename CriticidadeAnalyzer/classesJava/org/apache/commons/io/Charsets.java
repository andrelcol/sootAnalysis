package org.apache.commons.io;

import java.nio.charset.Charset;

public class Charsets {
  static {
    Charset.forName("ISO-8859-1");
    Charset.forName("US-ASCII");
    Charset.forName("UTF-16");
    Charset.forName("UTF-16BE");
    Charset.forName("UTF-16LE");
    Charset.forName("UTF-8");
  }
  
  public static Charset toCharset(Charset paramCharset) {
    Charset charset = paramCharset;
    if (paramCharset == null)
      charset = Charset.defaultCharset(); 
    return charset;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/org/apache/commons/io/Charsets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */