package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import androidx.core.content.PermissionChecker;
import java.util.Calendar;

class TwilightManager {
  private static TwilightManager sInstance;
  
  private final Context mContext;
  
  private final LocationManager mLocationManager;
  
  private final TwilightState mTwilightState = new TwilightState();
  
  TwilightManager(Context paramContext, LocationManager paramLocationManager) {
    this.mContext = paramContext;
    this.mLocationManager = paramLocationManager;
  }
  
  static TwilightManager getInstance(Context paramContext) {
    if (sInstance == null) {
      paramContext = paramContext.getApplicationContext();
      sInstance = new TwilightManager(paramContext, (LocationManager)paramContext.getSystemService("location"));
    } 
    return sInstance;
  }
  
  @SuppressLint({"MissingPermission"})
  private Location getLastKnownLocation() {
    Location location1;
    int i = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION");
    Location location2 = null;
    if (i == 0) {
      location1 = getLastKnownLocationForProvider("network");
    } else {
      location1 = null;
    } 
    if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0)
      location2 = getLastKnownLocationForProvider("gps"); 
    if (location2 != null && location1 != null) {
      Location location = location1;
      if (location2.getTime() > location1.getTime())
        location = location2; 
      return location;
    } 
    if (location2 != null)
      location1 = location2; 
    return location1;
  }
  
  private Location getLastKnownLocationForProvider(String paramString) {
    try {
      if (this.mLocationManager.isProviderEnabled(paramString))
        return this.mLocationManager.getLastKnownLocation(paramString); 
    } catch (Exception exception) {}
    return null;
  }
  
  private boolean isStateValid() {
    boolean bool;
    if (this.mTwilightState.nextUpdate > System.currentTimeMillis()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void updateState(Location paramLocation) {
    boolean bool;
    TwilightState twilightState = this.mTwilightState;
    long l1 = System.currentTimeMillis();
    TwilightCalculator twilightCalculator = TwilightCalculator.getInstance();
    twilightCalculator.calculateTwilight(l1 - 86400000L, paramLocation.getLatitude(), paramLocation.getLongitude());
    long l2 = twilightCalculator.sunset;
    twilightCalculator.calculateTwilight(l1, paramLocation.getLatitude(), paramLocation.getLongitude());
    if (twilightCalculator.state == 1) {
      bool = true;
    } else {
      bool = false;
    } 
    long l4 = twilightCalculator.sunrise;
    long l3 = twilightCalculator.sunset;
    twilightCalculator.calculateTwilight(86400000L + l1, paramLocation.getLatitude(), paramLocation.getLongitude());
    long l5 = twilightCalculator.sunrise;
    if (l4 == -1L || l3 == -1L) {
      l1 = 43200000L + l1;
    } else {
      if (l1 > l3) {
        l1 = 0L + l5;
      } else if (l1 > l4) {
        l1 = 0L + l3;
      } else {
        l1 = 0L + l4;
      } 
      l1 += 60000L;
    } 
    twilightState.isNight = bool;
    twilightState.yesterdaySunset = l2;
    twilightState.todaySunrise = l4;
    twilightState.todaySunset = l3;
    twilightState.tomorrowSunrise = l5;
    twilightState.nextUpdate = l1;
  }
  
  boolean isNight() {
    TwilightState twilightState = this.mTwilightState;
    if (isStateValid())
      return twilightState.isNight; 
    Location location = getLastKnownLocation();
    if (location != null) {
      updateState(location);
      return twilightState.isNight;
    } 
    int i = Calendar.getInstance().get(11);
    return (i < 6 || i >= 22);
  }
  
  private static class TwilightState {
    boolean isNight;
    
    long nextUpdate;
    
    long todaySunrise;
    
    long todaySunset;
    
    long tomorrowSunrise;
    
    long yesterdaySunset;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/app/TwilightManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */