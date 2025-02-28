package androidx.core.util;

import java.io.Writer;

public class LogWriter extends Writer {
  private StringBuilder mBuilder = new StringBuilder(128);
  
  private final String mTag;
  
  public LogWriter(String paramString) {
    this.mTag = paramString;
  }
  
  private void flushBuilder() {
    if (this.mBuilder.length() > 0) {
      this.mBuilder.toString();
      StringBuilder stringBuilder = this.mBuilder;
      stringBuilder.delete(0, stringBuilder.length());
    } 
  }
  
  public void close() {
    flushBuilder();
  }
  
  public void flush() {
    flushBuilder();
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    for (byte b = 0; b < paramInt2; b++) {
      char c = paramArrayOfchar[paramInt1 + b];
      if (c == '\n') {
        flushBuilder();
      } else {
        this.mBuilder.append(c);
      } 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/util/LogWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */