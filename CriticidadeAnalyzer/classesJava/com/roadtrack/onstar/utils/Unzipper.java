package com.roadtrack.onstar.utils;

import com.roadtrack.onstar.BO.GlobalMembers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzipper {
  private static Unzipper instance;
  
  public static Unzipper getInstance() {
    if (instance == null)
      instance = new Unzipper(); 
    return instance;
  }
  
  public String unzip(String paramString1, String paramString2) {
    byte[] arrayOfByte = new byte[1024];
    try {
      String str = GlobalMembers.mapFileName.substring(0, GlobalMembers.mapFileName.length() - 4);
      File file = UtilitiesFile.getFileFromStringFile(paramString2);
      if (!file.exists())
        file.mkdir(); 
      FileInputStream fileInputStream = UtilitiesFile.fileInputStreamFromStringFile(paramString1);
      ZipInputStream zipInputStream = new ZipInputStream();
      this(fileInputStream);
      ZipEntry zipEntry = zipInputStream.getNextEntry();
      byte b = 0;
      while (zipEntry != null) {
        String str1 = zipEntry.getName();
        GetHexDumpMap getHexDumpMap = new GetHexDumpMap();
        this();
        File file1 = UtilitiesFile.getFileFromStringFile(getHexDumpMap.getMapUpdateFile(str1));
        if (zipEntry.isDirectory()) {
          UtilitiesFile.getFileFromStringFile(file1.getCanonicalPath()).mkdirs();
        } else {
          FileOutputStream fileOutputStream = UtilitiesFile.fileOutputStreamFromStringFile(file1);
          while (true) {
            int i = zipInputStream.read(arrayOfByte);
            if (i > 0) {
              fileOutputStream.write(arrayOfByte, 0, i);
              continue;
            } 
            fileOutputStream.close();
            b++;
            break;
          } 
        } 
        zipEntry = zipInputStream.getNextEntry();
      } 
      zipInputStream.closeEntry();
      zipInputStream.close();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append("|");
      stringBuilder.append(b);
      return stringBuilder.toString();
    } catch (IOException iOException) {
      Utilities.escribeArchivo("UNZIPPEr", "UNZIP", iOException.getMessage());
      return null;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/Unzipper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */