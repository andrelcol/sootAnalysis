package com.roadtrack.onstar.nav.poi;

import android.content.Context;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.gson.HttpGoogleRequestSearchC;
import com.roadtrack.onstar.utils.Utilities;
import java.net.URLEncoder;
import org.apache.http.client.HttpResponseException;

public class GooglePlaces {
  private static final String API_KEY = GlobalMembers.GOOGLE_API_KEY;
  
  private static final String PLACES_TEXT_SEARCH_URL = GlobalMembers.PLACES_TEXT_SEARCH_URL;
  
  private double _latitude;
  
  private double _longitude;
  
  static {
    str = GlobalMembers.PLACES_NERBY_SEARCH_URL;
    str = GlobalMembers.AUTOCOMPLETE_URL;
    str = GlobalMembers.NEARBY_URL;
    str = GlobalMembers.PLACES_DETAILS_URL;
  }
  
  public PlacesList ListTextSearch(String paramString, double paramDouble1, double paramDouble2, int paramInt, Context paramContext) throws Exception {
    PlacesList placesList1;
    PlacesList placesList2;
    this._latitude = paramDouble1;
    this._longitude = paramDouble2;
    Context context = null;
    StringBuilder stringBuilder = null;
    paramContext = context;
    try {
      paramString = URLEncoder.encode(paramString.trim(), "UTF-8");
      paramContext = context;
      HttpGoogleRequestSearchC httpGoogleRequestSearchC = new HttpGoogleRequestSearchC();
      paramContext = context;
      this(PLACES_TEXT_SEARCH_URL);
      paramContext = context;
      httpGoogleRequestSearchC.setProperty("query", paramString);
      paramContext = context;
      StringBuilder stringBuilder1 = new StringBuilder();
      paramContext = context;
      this();
      paramContext = context;
      stringBuilder1.append(this._latitude);
      paramContext = context;
      stringBuilder1.append(",");
      paramContext = context;
      stringBuilder1.append(this._longitude);
      paramContext = context;
      httpGoogleRequestSearchC.setProperty("location", stringBuilder1.toString());
      paramContext = context;
      httpGoogleRequestSearchC.setProperty("language", GlobalMembers.langGooglePlaces);
      paramContext = context;
      httpGoogleRequestSearchC.setProperty("key", API_KEY);
      paramContext = context;
      httpGoogleRequestSearchC.execute();
      stringBuilder1 = stringBuilder;
      paramContext = context;
      if (httpGoogleRequestSearchC.getResponse().equals(HttpGoogleRequestSearchC.HTTP_SEARCH_OK)) {
        paramContext = context;
        placesList1 = httpGoogleRequestSearchC.getPlaceList();
      } 
      placesList2 = placesList1;
      StringBuilder stringBuilder2 = new StringBuilder();
      placesList2 = placesList1;
      this();
      placesList2 = placesList1;
      stringBuilder2.append("ListTextSearch: ");
      placesList2 = placesList1;
      stringBuilder2.append(httpGoogleRequestSearchC.getUrl());
      placesList2 = placesList1;
      Utilities.escribeArchivo("GooglePlaces", "GOOGLE_SEARCH", stringBuilder2.toString());
    } catch (HttpResponseException httpResponseException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("getPlaceTextSearch: ");
      stringBuilder1.append(httpResponseException.getMessage());
      Utilities.escribeArchivo("GooglePlaces", "TAG", stringBuilder1.toString());
      placesList1 = placesList2;
    } 
    return placesList1;
  }
  
  static {
    String str = GlobalMembers.PLACES_SEARCH_URL;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/nav/poi/GooglePlaces.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */