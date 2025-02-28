package com.roadtrack.onstar.googleMaps;

import android.graphics.drawable.Drawable;
import com.google.android.gms.maps.model.LatLng;
import java.io.Serializable;

public class PointInfo implements Serializable {
  private String address;
  
  private transient LatLng latLng;
  
  private String name;
  
  public PointInfo(String paramString1, String paramString2, LatLng paramLatLng, Drawable paramDrawable) {
    this.address = paramString1;
    this.name = paramString2;
    this.latLng = paramLatLng;
  }
  
  public String getAddress() {
    return this.address;
  }
  
  public LatLng getLatLng() {
    return this.latLng;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/googleMaps/PointInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */