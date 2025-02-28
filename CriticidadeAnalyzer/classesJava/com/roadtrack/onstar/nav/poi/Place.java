package com.roadtrack.onstar.nav.poi;

import java.io.Serializable;

public class Place implements Serializable {
  public String formatted_address;
  
  public Geometry geometry;
  
  public String id;
  
  public String name;
  
  public String reference;
  
  public String vicinity;
  
  public void setFormatted_address(String paramString) {
    this.formatted_address = paramString;
  }
  
  public void setFormatted_phone_number(String paramString) {}
  
  public void setGeometry(Geometry paramGeometry) {
    this.geometry = paramGeometry;
  }
  
  public void setIcon(String paramString) {}
  
  public void setId(String paramString) {
    this.id = paramString;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setReference(String paramString) {
    this.reference = paramString;
  }
  
  public void setVicinity(String paramString) {
    this.vicinity = paramString;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.name);
    stringBuilder.append(" - ");
    stringBuilder.append(this.id);
    stringBuilder.append(" - ");
    stringBuilder.append(this.reference);
    return stringBuilder.toString();
  }
  
  public static class Geometry implements Serializable {
    public Place.Location location;
    
    public void setLocation(Place.Location param1Location) {
      this.location = param1Location;
    }
  }
  
  public static class Location implements Serializable {
    public double lat;
    
    public double lng;
    
    public void setLat(double param1Double) {
      this.lat = param1Double;
    }
    
    public void setLng(double param1Double) {
      this.lng = param1Double;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/nav/poi/Place.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */