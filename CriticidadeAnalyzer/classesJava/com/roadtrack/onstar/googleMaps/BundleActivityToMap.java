package com.roadtrack.onstar.googleMaps;

import com.roadtrack.onstar.VO.GoogleMapVO;

public class BundleActivityToMap {
  private static BundleActivityToMap bundleActivityToMap;
  
  private static GoogleMapVO googleMapVO = new GoogleMapVO();
  
  public static GoogleMapVO getGoogleMapVO() {
    return googleMapVO;
  }
  
  public static BundleActivityToMap getInstanceOfBundleActivityToMap() {
    if (bundleActivityToMap == null)
      bundleActivityToMap = new BundleActivityToMap(); 
    return bundleActivityToMap;
  }
  
  public static void setGoogleMapVO(GoogleMapVO paramGoogleMapVO) {
    googleMapVO = paramGoogleMapVO;
  }
  
  public static void setIsFindmePending(Boolean paramBoolean) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/googleMaps/BundleActivityToMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */