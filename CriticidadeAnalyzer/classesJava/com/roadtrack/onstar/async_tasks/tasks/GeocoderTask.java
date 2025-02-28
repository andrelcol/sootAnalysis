package com.roadtrack.onstar.async_tasks.tasks;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import com.google.android.gms.maps.model.LatLng;
import com.roadtrack.onstar.async_tasks.intefaces.GeocoderInterface;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GeocoderTask extends AsyncTask<Void, Void, HashMap<String, String>> {
  public static final String TAG = GeocoderTask.class.getName();
  
  private GeocoderInterface geocoderInterface;
  
  private LatLng latLng = null;
  
  private Context mContext;
  
  public GeocoderTask(Context paramContext, GeocoderInterface paramGeocoderInterface, LatLng paramLatLng) {
    this.mContext = paramContext;
    this.geocoderInterface = paramGeocoderInterface;
    this.latLng = paramLatLng;
  }
  
  protected HashMap<String, String> doInBackground(Void... paramVarArgs) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    boolean bool = NetUtilities.validateNetwork(this.mContext, false);
    if (this.latLng != null && Boolean.valueOf(bool).booleanValue()) {
      Geocoder geocoder = new Geocoder(this.mContext);
      try {
        List<Address> list = geocoder.getFromLocation(this.latLng.latitude, this.latLng.longitude, 1);
        if (list.size() > 0) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          String str1 = ((Address)list.get(0)).getThoroughfare();
          String str2 = "";
          if (str1 == null) {
            str1 = "";
          } else {
            str1 = ((Address)list.get(0)).getThoroughfare();
          } 
          stringBuilder1.append(str1);
          str1 = ((Address)list.get(0)).getSubThoroughfare();
          if (str1 == null) {
            str1 = "";
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append(" ");
            stringBuilder.append(((Address)list.get(0)).getSubThoroughfare());
            str1 = stringBuilder.toString();
          } 
          stringBuilder1.append(str1);
          stringBuilder1.append(", ");
          if (((Address)list.get(0)).getLocality() == null) {
            str1 = "";
          } else {
            str1 = ((Address)list.get(0)).getLocality();
          } 
          stringBuilder1.append(str1);
          stringBuilder1.append(".");
          String str3 = stringBuilder1.toString();
          StringBuilder stringBuilder2 = new StringBuilder();
          this();
          if (((Address)list.get(0)).getFeatureName() == null) {
            str1 = "";
          } else {
            str1 = ((Address)list.get(0)).getFeatureName();
          } 
          stringBuilder2.append(str1);
          stringBuilder2.append(" ");
          if (((Address)list.get(0)).getThoroughfare() == null) {
            str1 = str2;
          } else {
            str1 = ((Address)list.get(0)).getThoroughfare();
          } 
          stringBuilder2.append(str1);
          str1 = stringBuilder2.toString();
          hashMap.put("address", str3);
          hashMap.put("name", str1);
        } 
      } catch (IOException iOException) {
        Utilities.escribeArchivo(TAG, "Error getAddressFomLatong", iOException.getMessage());
      } 
    } 
    return (HashMap)hashMap;
  }
  
  protected void onPostExecute(HashMap<String, String> paramHashMap) {
    super.onPostExecute(paramHashMap);
    GeocoderInterface geocoderInterface = this.geocoderInterface;
    if (geocoderInterface != null)
      geocoderInterface.onResult(paramHashMap); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GeocoderTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */