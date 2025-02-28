package com.roadtrack.onstar.VO;

import android.location.Location;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.nav.routing.LocationInfo;
import com.roadtrack.onstar.utils.IExist;
import com.roadtrack.onstar.utils.NavegationUtilities;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class LocationInfoRT extends LocationInfo implements IExist<LocationInfoRT> {
  private String name;
  
  private NavegationUtilities utl;
  
  public LocationInfoRT() {
    setDate();
  }
  
  private void setDate() {
    long l = System.currentTimeMillis();
    (new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())).format(Long.valueOf(l));
  }
  
  public String getName() {
    if (this.name.length() <= 0) {
      this.utl = new NavegationUtilities();
      Location location = new Location("gps");
      location.setLatitude(getLatitude());
      location.setLongitude(getLongitude());
      this.name = this.utl.getNiceAddress(location);
    } 
    return this.name;
  }
  
  public void setAddress(String paramString) {}
  
  public void setDeviceID(String paramString) {}
  
  public void setLatitude(double paramDouble) {}
  
  public void setLongitude(double paramDouble) {}
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setTypeItem(Enums.TypeItem paramTypeItem) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/LocationInfoRT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */