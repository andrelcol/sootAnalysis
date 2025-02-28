package com.roadtrack.onstar.utils;

import android.content.Context;
import android.os.Environment;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.onstarApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.io.FilenameUtils;

public class UtilitiesFile {
  private static final String PACKAGE_NAME;
  
  private static final String[] PACKAGE_NAMES = UtilitiesFile.class.getPackage().getName().toString().split("\\.");
  
  public static String segundo;
  
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
    stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(Character.toString('S'));
    stringBuilder.append(Character.toString('t'));
    stringBuilder.append(Character.toString('a'));
    stringBuilder.append(Character.toString('r'));
    segundo = stringBuilder.toString();
  }
  
  public static String ReadCfg(String paramString) {
    String str1;
    String str2;
    String str5 = "";
    Context context = onstarApplication.getContext();
    String str3 = str5;
    String str4 = paramString;
    try {
      StringBuilder stringBuilder1 = new StringBuilder();
      str3 = str5;
      str4 = paramString;
      this();
      str3 = str5;
      str4 = paramString;
      stringBuilder1.append("<string name=\"");
      str3 = str5;
      str4 = paramString;
      stringBuilder1.append(paramString);
      str3 = str5;
      str4 = paramString;
      stringBuilder1.append("\">");
      str3 = str5;
      str4 = paramString;
      str2 = stringBuilder1.toString();
      str3 = str5;
      str4 = str2;
      InputStream inputStream = context.getResources().openRawResource(2131623940);
      str3 = str5;
      str4 = str2;
      BufferedReader bufferedReader = new BufferedReader();
      str3 = str5;
      str4 = str2;
      InputStreamReader inputStreamReader = new InputStreamReader();
      str3 = str5;
      str4 = str2;
      this(inputStream);
      str3 = str5;
      str4 = str2;
      this(inputStreamReader);
      while (true) {
        str3 = str5;
        str4 = str2;
        String str7 = bufferedReader.readLine();
        String str6 = str5;
        if (str7 != null) {
          str3 = str5;
          str4 = str2;
          if (str7.trim().contains(str2)) {
            str3 = str5;
            str4 = str2;
            str5 = str7.trim().replace(str2, "");
            try {
              str6 = str5.replace("</string>", "");
              break;
            } catch (Exception exception) {
              str3 = str5;
            } 
          } else {
            continue;
          } 
        } else {
          break;
        } 
        Utilities.escribeArchivo("UtilitiesFile", "Error: ReadCfg", exception.getMessage());
        str1 = str3;
      } 
      str3 = str1;
      str4 = str2;
      inputStream.close();
      str3 = str1;
      str4 = str2;
      bufferedReader.close();
    } catch (Exception exception) {
      str2 = str4;
      Utilities.escribeArchivo("UtilitiesFile", "Error: ReadCfg", exception.getMessage());
      str1 = str3;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Params:");
    stringBuilder.append(str2);
    stringBuilder.append(" = ");
    stringBuilder.append(Utilities.Decrypt(str1));
    Utilities.escribeArchivo("UtilitiesFile", "Params", stringBuilder.toString());
    return Utilities.Decrypt(str1);
  }
  
  public static boolean deleteDir(File paramFile) {
    boolean bool1;
    boolean bool2 = false;
    try {
      if (paramFile.isDirectory()) {
        String[] arrayOfString = paramFile.list();
        for (byte b = 0; b < arrayOfString.length; b++) {
          File file = new File();
          this(paramFile, arrayOfString[b]);
          if (!deleteDir(file))
            return false; 
        } 
      } 
      bool1 = paramFile.delete();
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Deleting: ");
      stringBuilder.append(paramFile.toString());
      Utilities.escribeArchivo("UtilitiesFile", "ERROR", stringBuilder.toString());
      bool1 = bool2;
    } 
    return bool1;
  }
  
  public static final void deleteMapUpdateFile(Context paramContext, String paramString) {
    try {
      DBFunctions dBFunctions1 = new DBFunctions();
      this(paramContext);
      GetHexDumpMap getHexDumpMap = new GetHexDumpMap();
      this();
      File file1 = getFileFromStringFile(getHexDumpMap.getMapUpdateFile(paramString));
      if (file1.exists() && file1.delete())
        dBFunctions1.deleteMapUpdateData(); 
    } catch (Exception exception) {
      Utilities.escribeArchivo("UtilitiesFile", "MAP UPDATE DELETE:", exception.getMessage());
    } 
    DBFunctions dBFunctions = new DBFunctions(paramContext);
    File file = getFileFromStringFile((new GetHexDumpMap()).getMapUpdateFile(paramString));
    if (file.exists() && file.delete()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("DELETING FILE ");
      stringBuilder.append(paramString);
      Utilities.escribeArchivo("UtilitiesFile", "MAPUPDATE", stringBuilder.toString());
      dBFunctions.deleteMapUpdateData();
    } 
  }
  
  public static void deleteUnnecessaryFiles() {
    deleteDir(getFileFromStringFile(GlobalMembers.pathCloudmadeMap));
  }
  
  public static FileInputStream fileInputStreamFromStringFile(File paramFile) throws FileNotFoundException {
    return new FileInputStream(paramFile);
  }
  
  public static FileInputStream fileInputStreamFromStringFile(String paramString) throws FileNotFoundException {
    return fileInputStreamFromStringFile(getFileFromStringFile(paramString));
  }
  
  public static FileOutputStream fileOutputStreamFromStringFile(File paramFile) throws FileNotFoundException {
    return new FileOutputStream(paramFile);
  }
  
  public static File getFileFromStringFile(File paramFile, String paramString) {
    return new File(paramFile, paramString);
  }
  
  public static File getFileFromStringFile(String paramString) {
    FilenameUtils.getBaseName(paramString);
    String str = FilenameUtils.getExtension(paramString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(removeExtension(paramString));
    stringBuilder.append(".");
    stringBuilder.append(str.trim());
    return new File(stringBuilder.toString());
  }
  
  public static File getFileFromStringFile(String paramString1, String paramString2) {
    return new File(paramString1, paramString2);
  }
  
  public static boolean isAppDirAvailable() {
    File file = getFileFromStringFile(GlobalMembers.appSDPath);
    boolean bool2 = file.exists();
    boolean bool1 = bool2;
    if (!bool2)
      bool1 = file.mkdirs(); 
    return bool1;
  }
  
  public static String removeExtension(String paramString) {
    if (paramString == null)
      return null; 
    int i = FilenameUtils.indexOfExtension(paramString);
    return (i == -1) ? paramString : paramString.substring(0, i);
  }
  
  public static String reviewPathFile(String paramString) {
    return paramString;
  }
  
  public static String validatePath(String paramString) {
    return paramString.replace("..", "");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/UtilitiesFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */