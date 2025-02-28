package com.roadtrack.onstar.BO;

import android.content.Context;
import android.os.Environment;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.MapUtils;
import java.util.ArrayList;

public class MapDownload {
  private static final String DATA_PATH;
  
  private static final String PACKAGE_NAME;
  
  private static final String[] PACKAGE_NAMES = MapDownload.class.getPackage().getName().toString().split("\\.");
  
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
    stringBuilder = new StringBuilder();
    stringBuilder.append(DATA_PATH);
    stringBuilder.append("/");
    stringBuilder.append("RoadTrack/map/osm");
    stringBuilder.toString();
  }
  
  public MapDownload(Context paramContext, String paramString, boolean paramBoolean) {
    new MapNameandSize();
    new ArrayList();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("/Android/data/");
    stringBuilder.append(PACKAGE_NAME);
    stringBuilder.append("/");
    stringBuilder.append("ttndata");
    stringBuilder.append("/");
    stringBuilder.append("files");
    stringBuilder.append("/");
    stringBuilder.append("Maps");
    stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(GlobalMembers.URLMapsDownload);
    stringBuilder.append(GlobalMembers.fileMapsDownload);
    stringBuilder.toString();
    new ArrayList();
    new ArrayList();
    new StringsResourcesVO();
    new MapUtils();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/MapDownload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */