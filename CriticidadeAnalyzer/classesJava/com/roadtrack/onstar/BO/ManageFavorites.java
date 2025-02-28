package com.roadtrack.onstar.BO;

import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.LocationInfoRT;
import com.roadtrack.onstar.utils.GlobalMembersNavegation;
import com.roadtrack.onstar.utils.Utilities;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ManageFavorites {
  private String calculateChecksum(String paramString) {
    boolean bool;
    byte b = 0;
    String str = "utf-8";
    try {
      if (GlobalMembers.unitTypePlatinum == Enums.UnitType.Platinum_7)
        str = "Cp1252"; 
      byte[] arrayOfByte = paramString.getBytes(str);
      int i = 0;
      while (true) {
        bool = i;
        try {
          if (b < arrayOfByte.length) {
            bool = arrayOfByte[b];
            i += bool;
            b++;
            continue;
          } 
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
          // Byte code: goto -> 60
        } 
        break;
      } 
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      boolean bool1 = false;
      Utilities.escribeArchivo("ManagerFavorites", "Error: calculateChecksum", unsupportedEncodingException.getMessage());
      bool = bool1;
    } 
    paramString = Integer.toHexString(bool);
    return paramString.substring(paramString.length() - 2).toUpperCase();
  }
  
  private String getAsciiLatLng(Double paramDouble) {
    String str3 = Double.toString(paramDouble.doubleValue());
    int i = str3.indexOf(".");
    String str2 = str3.substring(0, i);
    String str1 = str2;
    if (!str2.contains("-")) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("+");
      stringBuilder.append(str3.substring(0, i));
      str1 = stringBuilder.toString();
    } 
    str2 = str3.substring(i + 1);
    if (str1.length() == 3) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1.substring(0, 1));
      stringBuilder.append("0");
      stringBuilder.append(str1.substring(1));
      stringBuilder.append(str2);
      str1 = stringBuilder.toString();
    } else if (str1.length() == 2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append("0");
      stringBuilder.append(str1);
      stringBuilder.append(str2);
      str1 = stringBuilder.toString();
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(str1);
      stringBuilder.append(str2);
      str1 = stringBuilder.toString();
    } 
    return str1;
  }
  
  public String getFavoritesStringToSend() {
    List<LocationInfoRT> list = GlobalMembersNavegation.FavoriteHistoricalList;
    if (list == null || list.size() == 0)
      return "no data"; 
    int i = 0;
    String str1 = "";
    while (i < list.size()) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str1);
      stringBuilder1.append(getAsciiLatLng(Double.valueOf(((LocationInfoRT)list.get(i)).getLatitude())));
      stringBuilder1.append("|");
      stringBuilder1.append(getAsciiLatLng(Double.valueOf(((LocationInfoRT)list.get(i)).getLongitude())));
      stringBuilder1.append("|");
      stringBuilder1.append(((LocationInfoRT)list.get(i)).getName());
      String str = stringBuilder1.toString();
      int j = i + 1;
      i = j;
      str1 = str;
      if (j < list.size()) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(";");
        str1 = stringBuilder2.toString();
        i = j;
      } 
    } 
    String str2 = calculateChecksum(str1);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[35|");
    stringBuilder.append(str1);
    stringBuilder.append('~');
    stringBuilder.append(str2);
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/ManageFavorites.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */