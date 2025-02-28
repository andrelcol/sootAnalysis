package com.roadtrack.onstar.utils;

import android.content.Context;
import android.os.Environment;
import com.roadtrack.onstar.onstarApplication;

public class MapUtils {
  public MapUtils() {
    new Utilities();
  }
  
  public void changePath(Context paramContext, String paramString) {
    onstarApplication onstarApplication = (onstarApplication)paramContext;
  }
  
  public String getExternalDirectoryFile() {
    return Environment.getExternalStorageDirectory().toString();
  }
  
  public void setMapPath(Context paramContext) {
    String str = getExternalDirectoryFile();
    changePath(paramContext.getApplicationContext(), str);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/MapUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */