package com.roadtrack.onstar.utils;

import android.os.Environment;

public class GetHexDumpMap {
  private static final String DATA_PATH;
  
  private static final String PACKAGE_NAME;
  
  private static final String[] PACKAGE_NAMES = GetHexDumpMap.class.getPackage().getName().toString().split("\\.");
  
  static {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(PACKAGE_NAMES[0]);
    stringBuilder.append(".");
    stringBuilder.append(PACKAGE_NAMES[1]);
    stringBuilder.append(".");
    stringBuilder.append(PACKAGE_NAMES[2]);
    PACKAGE_NAME = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(Environment.getExternalStorageDirectory());
    stringBuilder.append("/");
    stringBuilder.append("Android");
    stringBuilder.append("/");
    stringBuilder.append("data");
    stringBuilder.append("/");
    stringBuilder.append(PACKAGE_NAME);
    DATA_PATH = stringBuilder.toString();
  }
  
  public String getMapUpdateFile(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(DATA_PATH);
    stringBuilder.append("/");
    stringBuilder.append("RoadTrack/");
    stringBuilder.append(paramString);
    return stringBuilder.toString();
  }
  
  public long getMapUpdateFileSize(String paramString) {
    long l;
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(DATA_PATH);
      stringBuilder.append("/");
      stringBuilder.append("RoadTrack/");
      stringBuilder.append(paramString);
      l = UtilitiesFile.getFileFromStringFile(stringBuilder.toString()).length();
    } catch (Exception exception) {
      l = 0L;
    } 
    return l;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/GetHexDumpMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */