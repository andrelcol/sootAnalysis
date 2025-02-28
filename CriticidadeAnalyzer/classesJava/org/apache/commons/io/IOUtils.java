package org.apache.commons.io;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import org.apache.commons.io.output.StringBuilderWriter;

public class IOUtils {
  static {
    char c = File.separatorChar;
    StringBuilderWriter stringBuilderWriter = new StringBuilderWriter(4);
    PrintWriter printWriter = new PrintWriter((Writer)stringBuilderWriter);
    printWriter.println();
    stringBuilderWriter.toString();
    printWriter.close();
  }
  
  public static void closeQuietly(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (IOException iOException) {} 
  }
  
  public static void closeQuietly(InputStream paramInputStream) {
    closeQuietly(paramInputStream);
  }
  
  public static int copy(Reader paramReader, Writer paramWriter) throws IOException {
    long l = copyLarge(paramReader, paramWriter);
    return (l > 2147483647L) ? -1 : (int)l;
  }
  
  public static void copy(InputStream paramInputStream, Writer paramWriter, Charset paramCharset) throws IOException {
    copy(new InputStreamReader(paramInputStream, Charsets.toCharset(paramCharset)), paramWriter);
  }
  
  public static long copyLarge(Reader paramReader, Writer paramWriter) throws IOException {
    return copyLarge(paramReader, paramWriter, new char[4096]);
  }
  
  public static long copyLarge(Reader paramReader, Writer paramWriter, char[] paramArrayOfchar) throws IOException {
    long l = 0L;
    while (true) {
      int i = paramReader.read(paramArrayOfchar);
      if (-1 != i) {
        paramWriter.write(paramArrayOfchar, 0, i);
        l += i;
        continue;
      } 
      return l;
    } 
  }
  
  public static String toString(InputStream paramInputStream) throws IOException {
    return toString(paramInputStream, Charset.defaultCharset());
  }
  
  public static String toString(InputStream paramInputStream, Charset paramCharset) throws IOException {
    StringBuilderWriter stringBuilderWriter = new StringBuilderWriter();
    copy(paramInputStream, (Writer)stringBuilderWriter, paramCharset);
    return stringBuilderWriter.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/org/apache/commons/io/IOUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */