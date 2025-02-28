package com.roadtrack.onstar.gson;

import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.SimpleRequestObject;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.nav.poi.Place;
import com.roadtrack.onstar.nav.poi.PlacesList;
import com.roadtrack.onstar.utils.Utilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class HttpGoogleRequestSearchC {
  public static String HTTP_SEARCH_ERROR = "ERROR";
  
  public static String HTTP_SEARCH_OK = "OK";
  
  public static String TAG = "com.roadtrack.onstar.gson.HttpGoogleRequestSearchC";
  
  public static String USER_AGENT = "Mozilla/5.0";
  
  private BufferedReader buffer;
  
  private HttpsURLConnection con;
  
  private String jsonString;
  
  private GsonC parser;
  
  private PlacesList placeList;
  
  private LinkedHashMap<String, Object> properties;
  
  private List<Place> rawList;
  
  private String response;
  
  private int responseCode;
  
  private InputStreamReader streamReader;
  
  private String stringUrl;
  
  private URL url;
  
  public HttpGoogleRequestSearchC(String paramString) {
    new ArrayList();
    this.properties = new LinkedHashMap<String, Object>();
    this.stringUrl = paramString;
    this.parser = new GsonC();
    this.placeList = new PlacesList();
  }
  
  public void execute() throws IOException {
    try {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      this();
      linkedHashMap.put("User-Agent", USER_AGENT);
      SimpleRequestObject simpleRequestObject = new SimpleRequestObject();
      this(null, linkedHashMap, this.stringUrl, 10000, 10000, -1, null);
      simpleRequestObject.setProperties(this.properties);
      RequestManager requestManager = new RequestManager();
      this(MainActivity.mainContext, simpleRequestObject);
      requestManager.sendRequest(6);
      this.responseCode = requestManager.getResponseCode();
      if (this.responseCode == 200) {
        this.jsonString = requestManager.getRespuesta();
        this.rawList = this.parser.toListObject(this.jsonString);
        this.response = HTTP_SEARCH_OK;
        this.placeList.setResults(this.rawList);
        this.placeList.setStatus(HTTP_SEARCH_OK);
        this.placeList.setNext_page_token(this.parser.getNext_page_token());
        Utilities.escribeArchivo("jsonString", "jsonString", this.jsonString.toString());
      } else {
        this.response = HTTP_SEARCH_ERROR;
        this.placeList = null;
        this.placeList.setStatus(HTTP_SEARCH_ERROR);
        this.placeList.setNext_page_token("");
      } 
      HttpsURLConnection httpsURLConnection = this.con;
      if (httpsURLConnection != null)
        httpsURLConnection.disconnect(); 
      BufferedReader bufferedReader = this.buffer;
      if (bufferedReader != null)
        bufferedReader.close(); 
      InputStreamReader inputStreamReader = this.streamReader;
      if (inputStreamReader != null) {
        inputStreamReader.close();
        return;
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "execute:", exception.getMessage());
      HttpsURLConnection httpsURLConnection = this.con;
      if (httpsURLConnection != null)
        httpsURLConnection.disconnect(); 
      BufferedReader bufferedReader = this.buffer;
      if (bufferedReader != null)
        bufferedReader.close(); 
      InputStreamReader inputStreamReader = this.streamReader;
      if (inputStreamReader != null) {
        inputStreamReader.close();
        return;
      } 
    } finally {
      Exception exception;
    } 
  }
  
  public PlacesList getPlaceList() {
    return this.placeList;
  }
  
  public String getResponse() {
    return this.response;
  }
  
  public URL getUrl() {
    return this.url;
  }
  
  public void setProperty(String paramString1, String paramString2) {
    this.properties.put(paramString1, paramString2);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/gson/HttpGoogleRequestSearchC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */