package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

public class StringBuilderWriter extends Writer implements Serializable {
  private final StringBuilder builder = new StringBuilder();
  
  public StringBuilderWriter() {}
  
  public StringBuilderWriter(int paramInt) {}
  
  public Writer append(char paramChar) {
    this.builder.append(paramChar);
    return this;
  }
  
  public Writer append(CharSequence paramCharSequence) {
    this.builder.append(paramCharSequence);
    return this;
  }
  
  public Writer append(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    this.builder.append(paramCharSequence, paramInt1, paramInt2);
    return this;
  }
  
  public void close() {}
  
  public void flush() {}
  
  public String toString() {
    return this.builder.toString();
  }
  
  public void write(String paramString) {
    if (paramString != null)
      this.builder.append(paramString); 
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramArrayOfchar != null)
      this.builder.append(paramArrayOfchar, paramInt1, paramInt2); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/org/apache/commons/io/output/StringBuilderWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */