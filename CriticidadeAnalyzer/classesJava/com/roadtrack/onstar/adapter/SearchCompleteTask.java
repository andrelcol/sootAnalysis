package com.roadtrack.onstar.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.ExpandableIcon_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.GoogleSearchResults_Interface;
import com.roadtrack.onstar.googleMaps.ActivityMapViewerG;
import com.roadtrack.onstar.interfaces.TomTomStartNavigation_Interface;
import com.roadtrack.onstar.mapData.MapController;
import com.roadtrack.onstar.nav.poi.GooglePlaces;
import com.roadtrack.onstar.nav.poi.Place;
import com.roadtrack.onstar.nav.poi.PlacesList;
import com.roadtrack.onstar.utils.DialogEmpty;
import com.roadtrack.onstar.utils.RubenUltimaAlgorithm;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SearchCompleteTask extends AsyncTask<Location, String, String> {
  public static String KEY_LATITUDE = "location";
  
  public static String KEY_LONGITUDE = "vicinity";
  
  private PlacesList TextNerbyPlaces;
  
  private PlacesList TextSearchPlaces;
  
  private Activity activity;
  
  private Context context;
  
  private MapController controller;
  
  private EditText ed;
  
  private ExpandableListView expListView;
  
  private GooglePlaces googlePlaces;
  
  private GoogleSearchResults_Interface googleSearchResults_interface;
  
  private String input;
  
  private double latitude;
  
  private double longitude;
  
  private GoogleMap mMap;
  
  private RubenUltimaAlgorithm mRuAlgorithm;
  
  private ProgressDialog pDialog;
  
  private ArrayList<HashMap<String, String>> placesListItems;
  
  private int radius;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private String texto;
  
  public SearchCompleteTask(Activity paramActivity, GoogleSearchResults_Interface paramGoogleSearchResults_Interface, GoogleMap paramGoogleMap, String paramString, ExpandableListView paramExpandableListView, MapController paramMapController) {
    new HashMap<Object, Object>();
    this.placesListItems = new ArrayList<HashMap<String, String>>();
    this.radius = 50000;
    this.activity = paramActivity;
    this.context = paramActivity.getBaseContext();
    this.googleSearchResults_interface = paramGoogleSearchResults_Interface;
    this.mMap = paramGoogleMap;
    this.texto = paramString;
    this.expListView = paramExpandableListView;
    this.stringsResourcesVO = new StringsResourcesVO();
    this.controller = paramMapController;
  }
  
  protected String doInBackground(Location... paramVarArgs) {
    Utilities.escribeArchivo("SearchCompleteTask", "SearchCompleteTask", "DOING BACKGROUND");
    Thread.currentThread().setName("SearchCompleteTask");
    this.googlePlaces = new GooglePlaces();
    this.latitude = paramVarArgs[0].getLatitude();
    this.longitude = paramVarArgs[0].getLongitude();
    if (this.input.equalsIgnoreCase(""))
      this.input = paramVarArgs[0].getProvider(); 
    try {
      if (this.input != null && !this.input.isEmpty()) {
        this.TextSearchPlaces = this.googlePlaces.ListTextSearch(this.input, this.latitude, this.longitude, this.radius, this.activity.getApplicationContext());
      } else {
        this.TextNerbyPlaces = null;
        this.TextSearchPlaces = null;
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("SearchCompleteTask", "Error: doInBackground", exception.getMessage());
    } 
    return this.input;
  }
  
  protected void onPostExecute(String paramString) {
    saveResult();
    setResults(this.placesListItems);
    if (paramString.isEmpty()) {
      try {
        String str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.navigation_lbl_busquedaalertamensaje_1, 2131690147);
        DialogEmpty dialogEmpty = new DialogEmpty();
        this(this.activity, str);
        dialogEmpty.show();
        DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() {
            final DialogEmpty val$dc;
            
            public void onDismiss(DialogInterface param1DialogInterface) {
              dc.dismiss();
            }
          };
        super(this, dialogEmpty);
        dialogEmpty.setOnDismissListener(onDismissListener);
      } catch (Exception exception) {
        Utilities.escribeArchivo("SearchCompleteTask", "Error: onPostExecute", exception.getMessage());
      } 
    } else {
      ArrayList<HashMap<String, String>> arrayList = this.placesListItems;
      if (arrayList != null && arrayList.size() > 0) {
        arrayList = new ArrayList<HashMap<String, String>>();
        String str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.navigation_lbl_totalResultados_1, 2131690166);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" (");
        stringBuilder.append(this.placesListItems.size());
        stringBuilder.append(")");
        arrayList.add(stringBuilder.toString());
        this.mMap.clear();
        this.expListView = (ExpandableListView)this.activity.findViewById(2131297020);
        Collections.sort(this.placesListItems, new Comparator<HashMap<String, String>>(this) {
              public int compare(HashMap<String, String> param1HashMap1, HashMap<String, String> param1HashMap2) {
                Double double_1;
                Double double_2;
                if (((String)param1HashMap1.get("distanceString")).equalsIgnoreCase("m")) {
                  double_1 = Double.valueOf(Double.parseDouble(((String)param1HashMap1.get("distance")).replace(",", ".")) * 0.001D);
                } else {
                  double_1 = Double.valueOf(Double.parseDouble(((String)double_1.get("distance")).replace(",", ".")));
                } 
                if (((String)param1HashMap2.get("distanceString")).equalsIgnoreCase("m")) {
                  double_2 = Double.valueOf(Double.parseDouble(((String)param1HashMap2.get("distance")).replace(",", ".")) * 0.001D);
                } else {
                  double_2 = Double.valueOf(Double.parseDouble(((String)double_2.get("distance")).replace(",", ".")));
                } 
                return double_1.compareTo(double_2);
              }
            });
        ExpandableResultsAdapter expandableResultsAdapter = new ExpandableResultsAdapter(this.activity, (List)arrayList, this.placesListItems, new TomTomStartNavigation_Interface(this) {
              public void onStartNavigation(String param1String1, String param1String2, String param1String3, String param1String4) {}
            },  false, new ExpandableIcon_Interface(this) {
              public void onExpand() {}
            });
        ((ActivityMapViewerG)this.activity).setmExpandableListAdapter(expandableResultsAdapter);
        ((ActivityMapViewerG)this.activity).setPlacesListItems(this.placesListItems);
        if (!this.texto.equalsIgnoreCase(""))
          for (byte b = 0; b < this.placesListItems.size(); b++) {
            String str1 = (String)((HashMap)this.placesListItems.get(b)).get(KEY_LATITUDE);
            String str2 = (String)((HashMap)this.placesListItems.get(b)).get(KEY_LONGITUDE);
            this.controller.setMarkers(Double.parseDouble(str1), Double.parseDouble(str2), this.texto, b, this.activity);
          }  
        this.expListView.setAdapter((ExpandableListAdapter)((ActivityMapViewerG)this.activity).getmExpandableListAdapter());
        if (!this.placesListItems.isEmpty()) {
          this.expListView.expandGroup(0);
          this.googleSearchResults_interface.onSuccess(this.placesListItems, ((ActivityMapViewerG)this.activity).getmExpandableListAdapter());
        } 
      } else {
        String str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_fallaredbusqueda, 2131689921);
        Toast.makeText((Context)this.activity, str, 1).show();
      } 
    } 
    ProgressDialog progressDialog = this.pDialog;
    if (progressDialog != null)
      progressDialog.dismiss(); 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    Utilities.escribeArchivo("SearchCompleteTask", "SearchCompleteTask", "ONPREXECUTE");
    this.mRuAlgorithm = new RubenUltimaAlgorithm();
    this.ed = (EditText)this.activity.findViewById(2131297027);
    this.input = this.ed.getText().toString();
    this.pDialog = new ProgressDialog((Context)this.activity, 2131755173);
    String str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_spinner_mapa_buscando_1, 2131689963);
    this.pDialog.setMessage((CharSequence)Html.fromHtml(str));
    this.pDialog.setIndeterminate(false);
    this.pDialog.setCancelable(false);
    this.pDialog.show();
  }
  
  public int[] reviewLimits(PlacesList paramPlacesList1, PlacesList paramPlacesList2) {
    int i;
    int m;
    if (paramPlacesList1 == null) {
      k = 0;
    } else {
      k = paramPlacesList1.results.size();
    } 
    if (paramPlacesList2 == null) {
      i = 0;
    } else {
      i = paramPlacesList2.results.size();
    } 
    int j = k;
    if (k > 20)
      j = 20; 
    int k = i;
    if (i > 20)
      k = 20; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BEFORE nearByItems: ");
    stringBuilder.append(j);
    stringBuilder.append(", textSearchItems: ");
    stringBuilder.append(k);
    Utilities.escribeArchivo("SearchCompleteTask", "GOOGLE_SEARCH", stringBuilder.toString());
    if (j == 0) {
      m = j;
      i = k;
      if (k > 20) {
        i = 20;
        m = j;
      } 
    } else if (j > 0 && j <= 15) {
      m = j;
      i = k;
      if (k > 5) {
        int n = 20 - j;
        m = j;
        i = k;
        if (n >= k) {
          i = n;
          m = j;
        } 
      } 
    } else if (k > 0 && k <= 5) {
      int n = 20 - k;
      m = j;
      i = k;
      if (n >= j) {
        m = n;
        i = k;
      } 
    } else {
      m = j;
      i = k;
      if (k > 5) {
        m = 15;
        i = 5;
      } 
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append("AFTER nearByItems: ");
    stringBuilder.append(m);
    stringBuilder.append(", textSearchItems: ");
    stringBuilder.append(i);
    Utilities.escribeArchivo("SearchCompleteTask", "GOOGLE_SEARCH", stringBuilder.toString());
    return new int[] { m, i };
  }
  
  public void saveResult() {
    int[] arrayOfInt1 = reviewLimits(this.TextNerbyPlaces, this.TextSearchPlaces);
    int[] arrayOfInt2 = arrayOfInt1;
    PlacesList placesList = this.TextNerbyPlaces;
    String str9 = "NEARBY    : ";
    String str7 = "GOOGLE_SEARCH";
    String str6 = "SearchCompleteTask";
    String str5 = "address";
    String str3 = "name";
    String str4 = "reference";
    String str8 = "OK";
    String str2 = " - ";
    if (placesList != null) {
      String str = placesList.status;
      int i = arrayOfInt2[0];
      this.placesListItems.clear();
      if (str.equals("OK")) {
        byte b;
        String str14;
        StringBuilder stringBuilder;
        HashMap<Object, Object> hashMap1;
        String str15;
        HashMap<Object, Object> hashMap2;
        String str16;
        String str17;
        String str18;
        String str19;
        List list = this.TextNerbyPlaces.results;
        if (list != null) {
          Iterator<Place> iterator = list.iterator();
          byte b1 = 0;
          b = 0;
          str14 = str8;
          while (iterator.hasNext()) {
            Place place = iterator.next();
            if (b1 < i && this.placesListItems.size() < 15) {
              HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
              hashMap.put(str4, place.reference);
              hashMap.put(KEY_LATITUDE, String.valueOf(place.geometry.location.lat));
              hashMap.put(KEY_LONGITUDE, String.valueOf(place.geometry.location.lng));
              hashMap.put(str3, place.name);
              hashMap.put(str5, place.vicinity);
              RubenUltimaAlgorithm rubenUltimaAlgorithm = this.mRuAlgorithm;
              double d1 = this.latitude;
              double d2 = this.longitude;
              Place.Location location = place.geometry.location;
              d1 = rubenUltimaAlgorithm.linearDistance(d1, d2, location.lat, location.lng);
              d1 = this.mRuAlgorithm.linearDistanceToKilometers(d1);
              String str20 = this.mRuAlgorithm.formatLinearDistance(d1);
              String str21 = this.mRuAlgorithm.formatLinearDistanceWithString(d1);
              hashMap.put("distance", str20);
              hashMap.put("distanceString", str21);
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("NEARBY    : ");
              stringBuilder1.append(place.name);
              stringBuilder1.append(str2);
              stringBuilder1.append(place.vicinity);
              stringBuilder1.append(str2);
              stringBuilder1.append(place.geometry.location.lat);
              stringBuilder1.append(str2);
              stringBuilder1.append(place.geometry.location.lng);
              stringBuilder1.append(str2);
              stringBuilder1.append(str20);
              Utilities.escribeArchivo(str6, str7, stringBuilder1.toString());
              this.placesListItems.add(hashMap);
              b = b1;
            } 
            b1++;
          } 
          str17 = str3;
          str3 = str4;
          str4 = str14;
          str18 = str2;
          str14 = str7;
          str2 = str6;
          str16 = str5;
          int[] arrayOfInt = arrayOfInt2;
        } else {
          str2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.navigation_main_lbl_nohayningunresultado_1, 2131690169);
          Toast.makeText(this.context, str2, 1).show();
          str17 = "name";
          str16 = "address";
          str4 = "OK";
          str3 = "reference";
          str18 = " - ";
          str2 = "SearchCompleteTask";
          str5 = "GOOGLE_SEARCH";
          b = 0;
          str19 = str14;
          str14 = str5;
        } 
        PlacesList placesList1 = this.TextSearchPlaces;
        if (placesList1 != null) {
          HashMap<Object, Object> hashMap;
          Place place2;
          StringBuilder stringBuilder1;
          String str24 = placesList1.status;
          String str20 = str19[1];
          String str21 = str4;
          str8 = str9;
          String str23 = str14;
          String str22 = str2;
          str7 = str21;
          str6 = str3;
          if (str24.equals(str21)) {
            List list1 = this.TextSearchPlaces.results;
            str8 = str9;
            str23 = str14;
            str22 = str2;
            str7 = str21;
            str6 = str3;
            if (list1 != null) {
              Iterator<Place> iterator1 = list1.iterator();
              i = 0;
              str15 = str3;
              str3 = str21;
              str21 = str14;
              str14 = str9;
              Iterator<Place> iterator2 = iterator1;
              while (true) {
                str8 = str14;
                str23 = str21;
                str22 = str2;
                str7 = str3;
                String str25 = str15;
                if (iterator2.hasNext()) {
                  place2 = iterator2.next();
                  if (i < str20 && this.placesListItems.size() < 20) {
                    hashMap = new HashMap<Object, Object>();
                    hashMap.put(str15, place2.reference);
                    hashMap.put(KEY_LATITUDE, String.valueOf(place2.geometry.location.lat));
                    hashMap.put(KEY_LONGITUDE, String.valueOf(place2.geometry.location.lng));
                    hashMap.put(str17, place2.name);
                    hashMap.put(str16, place2.formatted_address);
                    RubenUltimaAlgorithm rubenUltimaAlgorithm = this.mRuAlgorithm;
                    double d1 = this.latitude;
                    double d2 = this.longitude;
                    Place.Location location = place2.geometry.location;
                    d1 = rubenUltimaAlgorithm.linearDistance(d1, d2, location.lat, location.lng);
                    d1 = this.mRuAlgorithm.linearDistanceToKilometers(d1);
                    str22 = this.mRuAlgorithm.formatLinearDistance(d1);
                    String str26 = this.mRuAlgorithm.formatLinearDistanceWithString(d1);
                    hashMap.put("distance", str22);
                    hashMap.put("distanceString", str26);
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("TEXTSEARCH: ");
                    stringBuilder1.append(place2.name);
                    stringBuilder1.append(str18);
                    stringBuilder1.append(place2.formatted_address);
                    stringBuilder1.append(str18);
                    stringBuilder1.append(place2.geometry.location.lat);
                    stringBuilder1.append(str18);
                    stringBuilder1.append(place2.geometry.location.lng);
                    stringBuilder1.append(str18);
                    stringBuilder1.append(str22);
                    Utilities.escribeArchivo(str2, str21, stringBuilder1.toString());
                    this.placesListItems.add(hashMap);
                  } 
                  i++;
                  continue;
                } 
                break;
              } 
            } 
          } 
          str3 = str8;
          stringBuilder = stringBuilder1;
          str2 = str22;
          Place place1 = place2;
          hashMap1 = hashMap;
        } else {
          str7 = "NEARBY    : ";
          str6 = str3;
          hashMap2 = hashMap1;
          str3 = str7;
          str15 = str6;
        } 
        if (this.placesListItems.size() < 20) {
          PlacesList placesList2 = this.TextNerbyPlaces;
          if (placesList2 != null) {
            String str21 = placesList2.status;
            String str20 = str19[0];
            if (str21.equals(hashMap2))
              if (this.TextNerbyPlaces.results != null) {
                i = b + 1;
                int j = i;
                String str22 = str20;
                while (i < this.TextNerbyPlaces.results.size()) {
                  if (j < str22 && this.placesListItems.size() < 20) {
                    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
                    hashMap.put(str15, ((Place)this.TextNerbyPlaces.results.get(i)).reference);
                    hashMap.put(KEY_LATITUDE, String.valueOf(((Place)this.TextNerbyPlaces.results.get(i)).geometry.location.lat));
                    hashMap.put(KEY_LONGITUDE, String.valueOf(((Place)this.TextNerbyPlaces.results.get(i)).geometry.location.lng));
                    hashMap.put(str17, ((Place)this.TextNerbyPlaces.results.get(i)).name);
                    hashMap.put(str16, ((Place)this.TextNerbyPlaces.results.get(i)).vicinity);
                    double d = this.mRuAlgorithm.linearDistance(this.latitude, this.longitude, ((Place)this.TextNerbyPlaces.results.get(i)).geometry.location.lat, ((Place)this.TextNerbyPlaces.results.get(i)).geometry.location.lng);
                    d = this.mRuAlgorithm.linearDistanceToKilometers(d);
                    String str23 = this.mRuAlgorithm.formatLinearDistance(d);
                    str7 = this.mRuAlgorithm.formatLinearDistanceWithString(d);
                    hashMap.put("distance", str23);
                    hashMap.put("distanceString", str7);
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(str3);
                    stringBuilder1.append(((Place)this.TextNerbyPlaces.results.get(i)).name);
                    stringBuilder1.append(str18);
                    stringBuilder1.append(((Place)this.TextNerbyPlaces.results.get(i)).vicinity);
                    stringBuilder1.append(str18);
                    stringBuilder1.append(((Place)this.TextNerbyPlaces.results.get(i)).geometry.location.lat);
                    stringBuilder1.append(str18);
                    stringBuilder1.append(((Place)this.TextNerbyPlaces.results.get(i)).geometry.location.lng);
                    stringBuilder1.append(str18);
                    stringBuilder1.append(str23);
                    Utilities.escribeArchivo(str2, (String)stringBuilder, stringBuilder1.toString());
                    this.placesListItems.add(hashMap);
                  } 
                  i++;
                  j++;
                } 
              } else {
                str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.navigation_main_lbl_nohayningunresultado_1, 2131690169);
                Toast.makeText(this.context, str1, 1).show();
              }  
          } 
        } 
        return;
      } 
    } 
    String str11 = "name";
    String str10 = "address";
    str4 = "OK";
    str3 = "reference";
    String str12 = " - ";
    str2 = "SearchCompleteTask";
    str5 = "GOOGLE_SEARCH";
    boolean bool = false;
    String str13 = str1;
    String str1 = str5;
  }
  
  public void setResults(ArrayList<HashMap<String, String>> paramArrayList) {
    this.placesListItems = paramArrayList;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/SearchCompleteTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */