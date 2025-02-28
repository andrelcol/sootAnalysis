package com.roadtrack.onstar.mapData;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.adapter.AdapterDialogMultiCheckBox;
import com.roadtrack.onstar.adapter.ExpandableResultsAdapter;
import com.roadtrack.onstar.adapter.SearchCompleteTask;
import com.roadtrack.onstar.async_tasks.intefaces.GoogleSearchResults_Interface;
import com.roadtrack.onstar.floatingActionButton.FloatingActionButton;
import com.roadtrack.onstar.googleMaps.ActivityMapViewerG;
import com.roadtrack.onstar.googleMaps.ActivityMapViewerViewG;
import com.roadtrack.onstar.googleMaps.MapsFragment;
import com.roadtrack.onstar.googleMaps.MapsFragmentView;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.DialogEmpty;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.HashMap;

public class MapController {
  private final String TAG = ActivityMapViewerG.class.getSimpleName();
  
  private Activity activity;
  
  private Button btnC;
  
  private Button btnOk;
  
  private boolean changeMap = false;
  
  private boolean changeTraffic = false;
  
  public View.OnClickListener clickListener = new View.OnClickListener() {
      final MapController this$0;
      
      public void onClick(View param1View) {
        int i = param1View.getId();
        if (i != 1) {
          String str;
          if (i != 2) {
            if (i != 3) {
              if (i != 4) {
                if (i == 5)
                  MapController.this.showDialogPicker(); 
              } else {
                ActivityMapViewerG activityMapViewerG = (ActivityMapViewerG)MapController.this.activity;
                if (!ActivityMapViewerG.onSendRoute) {
                  if (!((LocationManager)MapController.this.activity.getSystemService("location")).isProviderEnabled("gps")) {
                    ((ActivityMapViewerG)MapController.this.activity).dialogNoGPSActive();
                  } else {
                    MapController mapController = MapController.this;
                    MapController.access$402(mapController, (EditText)mapController.activity.findViewById(2131297027));
                    MapController.this.ed.setText("");
                    ((ActivityMapViewerG)MapController.this.activity).setListCollapse(false);
                    String str1 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi2, "", onstarApplication.getContext());
                    str = str1;
                    if (str1.equalsIgnoreCase(""))
                      str = MapController.this.valueFind2; 
                    MapController.this.findData(str);
                  } 
                } else {
                  ((ActivityMapViewerG)MapController.this.activity).setActionButtons();
                } 
              } 
            } else {
              ActivityMapViewerG activityMapViewerG = (ActivityMapViewerG)MapController.this.activity;
              if (!ActivityMapViewerG.onSendRoute) {
                if (!((LocationManager)MapController.this.activity.getSystemService("location")).isProviderEnabled("gps")) {
                  ((ActivityMapViewerG)MapController.this.activity).dialogNoGPSActive();
                } else {
                  MapController mapController = MapController.this;
                  MapController.access$402(mapController, (EditText)mapController.activity.findViewById(2131297027));
                  MapController.this.ed.setText("");
                  ((ActivityMapViewerG)MapController.this.activity).setListCollapse(false);
                  String str1 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi1, "", onstarApplication.getContext());
                  str = str1;
                  if (str1.equalsIgnoreCase(""))
                    str = MapController.this.valueFind1; 
                  MapController.this.findData(str);
                } 
              } else {
                ((ActivityMapViewerG)MapController.this.activity).setActionButtons();
              } 
            } 
          } else if (!MapController.this.changeMap) {
            MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle((Context)MapController.this.activity, MapController.this.activity.getResources().getIdentifier("map_style_night", "raw", MapController.this.activity.getPackageName()));
            Drawable drawable = Utilities.getDrawableFromConfigList((Context)MapController.this.activity, DrawableResourcesVO.ic_day, 2131165452);
            ((FloatingActionButton)str).setIconDrawable(drawable);
            MapController.this.mMap.setMapStyle(mapStyleOptions);
            MapController.access$202(MapController.this, true);
          } else {
            MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle((Context)MapController.this.activity, MapController.this.activity.getResources().getIdentifier("map_style_day", "raw", MapController.this.activity.getPackageName()));
            Drawable drawable = Utilities.getDrawableFromConfigList((Context)MapController.this.activity, DrawableResourcesVO.ic_night, 2131165496);
            ((FloatingActionButton)str).setIconDrawable(drawable);
            MapController.this.mMap.setMapStyle(mapStyleOptions);
            MapController.access$202(MapController.this, false);
          } 
        } else if (!MapController.this.changeTraffic) {
          MapController.this.mMap.setTrafficEnabled(true);
          MapController.access$002(MapController.this, true);
        } else {
          MapController.this.mMap.setTrafficEnabled(false);
          MapController.access$002(MapController.this, false);
        } 
      }
    };
  
  private SearchCompleteTask controlViews = null;
  
  private EditText ed;
  
  private ExpandableListView expListView;
  
  private GoogleSearchResults_Interface googleSearchResults_interface;
  
  private Boolean isThereINET;
  
  private LatLng latLng;
  
  private Location mCurrentLocation = null;
  
  private ExpandableResultsAdapter mExpandableListAdapter;
  
  private ArrayList<HashMap<String, String>> mGoogleSearchResults;
  
  private GoogleMap mMap;
  
  private ActivityMapViewerViewG mapViewerViewG;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private String valueFind1;
  
  private String valueFind2;
  
  public MapController(GoogleMap paramGoogleMap, Activity paramActivity) {
    this.mMap = paramGoogleMap;
    this.activity = paramActivity;
  }
  
  public MapController(GoogleMap paramGoogleMap, Activity paramActivity, ActivityMapViewerViewG paramActivityMapViewerViewG, ExpandableListView paramExpandableListView) {
    this.mMap = paramGoogleMap;
    this.activity = paramActivity;
    this.mapViewerViewG = paramActivityMapViewerViewG;
    this.expListView = paramExpandableListView;
  }
  
  public MapController(GoogleMap paramGoogleMap, Activity paramActivity, MapsFragment paramMapsFragment) {
    this.mMap = paramGoogleMap;
    this.activity = paramActivity;
  }
  
  public MapController(GoogleMap paramGoogleMap, Activity paramActivity, MapsFragmentView paramMapsFragmentView, MapsFragment paramMapsFragment, ExpandableListView paramExpandableListView, View paramView) {
    this.mMap = paramGoogleMap;
    this.activity = paramActivity;
    this.expListView = paramExpandableListView;
  }
  
  public boolean findData(String paramString) {
    DialogInterface.OnDismissListener onDismissListener;
    ((ActivityMapViewerG)this.activity).resetListAndMap();
    this.isThereINET = Boolean.valueOf(NetUtilities.validateNetwork((Context)this.activity, false));
    this.stringsResourcesVO = new StringsResourcesVO();
    this.googleSearchResults_interface = new GoogleSearchResults_Interface() {
        final MapController this$0;
        
        public void onSuccess(ArrayList<HashMap<String, String>> param1ArrayList, ExpandableResultsAdapter param1ExpandableResultsAdapter) {
          MapController.access$702(MapController.this, param1ArrayList);
          MapController.access$802(MapController.this, param1ExpandableResultsAdapter);
        }
      };
    this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(this.latLng, 10.0F));
    String str2 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.global_lbl_fallaredbusqueda, 2131689921);
    if (paramString.equals("")) {
      try {
        str2 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.navigation_lbl_busquedaalertamensaje_1, 2131690147);
        DialogEmpty dialogEmpty = new DialogEmpty();
        this(this.activity, str2);
        dialogEmpty.show();
        onDismissListener = new DialogInterface.OnDismissListener() {
            final DialogEmpty val$dc;
            
            public void onDismiss(DialogInterface param1DialogInterface) {
              dc.dismiss();
            }
          };
        super(this, dialogEmpty);
        dialogEmpty.setOnDismissListener(onDismissListener);
      } catch (Exception exception) {
        Utilities.escribeArchivo(this.TAG, "Error: onPostExecute", exception.getMessage());
      } 
      return false;
    } 
    String str1 = getTextSearch((String)exception);
    ((ActivityMapViewerG)this.activity).setTypeFloating(str1);
    if (this.controlViews == null) {
      if (this.isThereINET.booleanValue()) {
        this.mCurrentLocation = new Location(str1);
        this.mCurrentLocation.setLatitude(this.latLng.latitude);
        this.mCurrentLocation.setLongitude(this.latLng.longitude);
        this.controlViews = new SearchCompleteTask(this.activity, this.googleSearchResults_interface, this.mMap, str1, this.expListView, this);
        this.controlViews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Location[] { this.mCurrentLocation });
      } else {
        Toast.makeText((Context)this.activity, (CharSequence)onDismissListener, 1).show();
      } 
    } else if (this.isThereINET.booleanValue()) {
      this.mCurrentLocation = new Location(str1);
      this.mCurrentLocation.setLatitude(this.latLng.latitude);
      this.mCurrentLocation.setLongitude(this.latLng.longitude);
      this.controlViews = new SearchCompleteTask(this.activity, this.googleSearchResults_interface, this.mMap, str1, this.expListView, this);
      this.controlViews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Location[] { this.mCurrentLocation });
    } else {
      Toast.makeText((Context)this.activity, (CharSequence)onDismissListener, 1).show();
    } 
    ExpandableListView expandableListView = this.expListView;
    if (expandableListView != null) {
      if (expandableListView.getVisibility() == 8)
        this.expListView.setVisibility(0); 
      this.controlViews = null;
    } 
    return true;
  }
  
  public String getTextSearch(String paramString) {
    this.stringsResourcesVO = new StringsResourcesVO();
    String str1 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_1_cafeterias, 2131689612);
    String str4 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_2_restaurantes, 2131689613);
    String str2 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_3_gasolineria, 2131689614);
    String str5 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_4_atm, 2131689615);
    String str3 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_5_supermercados, 2131689616);
    String str6 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_6_farmacias, 2131689617);
    if (paramString.equalsIgnoreCase(str1)) {
      paramString = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_1_cafe, 2131689605);
    } else if (paramString.equalsIgnoreCase(str4)) {
      paramString = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_2_restaurante, 2131689606);
    } else if (paramString.equalsIgnoreCase(str2)) {
      paramString = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_3_gas, 2131689607);
    } else if (paramString.equalsIgnoreCase(str5)) {
      paramString = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_4_atm, 2131689608);
    } else if (paramString.equalsIgnoreCase(str3)) {
      paramString = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_5_supermercado, 2131689609);
    } else if (paramString.equalsIgnoreCase(str6)) {
      paramString = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_6_farmacia, 2131689610);
    } else {
      paramString = "";
    } 
    return paramString;
  }
  
  public double[] microDegreesToLatLng(double paramDouble1, double paramDouble2) {
    return new double[] { paramDouble1 / 1000000.0D, paramDouble2 / 1000000.0D };
  }
  
  public Bitmap scaleImageMarker(int paramInt) {
    Bitmap bitmap = BitmapFactory.decodeResource(this.activity.getResources(), paramInt);
    Matrix matrix = new Matrix();
    matrix.postScale(0.5F, 0.5F);
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
  }
  
  public FloatingActionButton setButtons(Context paramContext, int paramInt, Drawable paramDrawable) {
    FloatingActionButton floatingActionButton = new FloatingActionButton(paramContext);
    floatingActionButton.setId(paramInt);
    floatingActionButton.setSize(1);
    floatingActionButton.setIconDrawable(paramDrawable);
    return floatingActionButton;
  }
  
  public int setIconMarker(String paramString, Activity paramActivity) {
    boolean bool;
    this.stringsResourcesVO = new StringsResourcesVO();
    String str5 = Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_1_cafe, 2131689605);
    String str6 = Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_2_restaurante, 2131689606);
    String str3 = Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_3_gas, 2131689607);
    String str4 = Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_4_atm, 2131689608);
    String str2 = Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_5_supermercado, 2131689609);
    String str1 = Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.accesos_rapidos_mapa_busca_6_farmacia, 2131689610);
    if (paramString.equalsIgnoreCase(str5)) {
      bool = true;
    } else if (paramString.equalsIgnoreCase(str6)) {
      bool = true;
    } else if (paramString.equalsIgnoreCase(str3)) {
      bool = true;
    } else if (paramString.equalsIgnoreCase(str4)) {
      bool = true;
    } else if (paramString.equalsIgnoreCase(str2)) {
      bool = true;
    } else if (paramString.equalsIgnoreCase(str1)) {
      bool = true;
    } else if (paramString.equalsIgnoreCase("")) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setLatLng(LatLng paramLatLng) {
    this.latLng = paramLatLng;
  }
  
  public void setMarkers(double paramDouble1, double paramDouble2, String paramString, int paramInt, Activity paramActivity) {
    int i = setIconMarker(paramString, paramActivity);
    LatLng latLng = new LatLng(paramDouble1, paramDouble2);
    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(scaleImageMarker(i));
    GoogleMap googleMap = this.mMap;
    MarkerOptions markerOptions = new MarkerOptions();
    markerOptions.position(latLng);
    markerOptions.title("");
    markerOptions.icon(bitmapDescriptor);
    googleMap.addMarker(markerOptions).setTag(Integer.valueOf(paramInt));
  }
  
  public void setValueFind1(String paramString) {
    this.valueFind1 = paramString;
  }
  
  public void setValueFind2(String paramString) {
    this.valueFind2 = paramString;
  }
  
  public void showDialogPicker() {
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str1 = Utilities.getStringFromConfigList((Context)this.activity, stringsResourcesVO.navegacion_mapa_lbl_lugaresproximos, 2131690138);
    ArrayList<String> arrayList = new ArrayList();
    String str3 = Utilities.getStringFromConfigList((Context)this.activity, stringsResourcesVO.accesos_rapidos_mapa_txt_3_gasolineria, 2131689614);
    String str5 = Utilities.getStringFromConfigList((Context)this.activity, stringsResourcesVO.accesos_rapidos_mapa_txt_4_atm, 2131689615);
    String str4 = Utilities.getStringFromConfigList((Context)this.activity, stringsResourcesVO.accesos_rapidos_mapa_txt_5_supermercados, 2131689616);
    String str6 = Utilities.getStringFromConfigList((Context)this.activity, stringsResourcesVO.accesos_rapidos_mapa_txt_6_farmacias, 2131689617);
    arrayList.add(str3);
    arrayList.add(str5);
    arrayList.add(str4);
    arrayList.add(str6);
    ArrayList<ClassMapData> arrayList1 = new ArrayList();
    for (byte b = 0; b < arrayList.size(); b++) {
      ClassMapData classMapData = new ClassMapData();
      classMapData.setData(arrayList.get(b));
      classMapData.setValue(false);
      arrayList1.add(classMapData);
    } 
    final AdapterDialogMultiCheckBox adapter = new AdapterDialogMultiCheckBox((Context)this.activity, 2131427423, arrayList1);
    str4 = Utilities.getStringFromConfigList((Context)this.activity, stringsResourcesVO.BTCancel, 2131689476);
    String str2 = Utilities.getStringFromConfigList((Context)this.activity, stringsResourcesVO.global_popup_btn_alterar, 2131689947);
    final Dialog dialog = Utilities.simpleDialogMultiCheckBox((Context)this.activity, null, str1, arrayList, true, str2, true, str4, 18.0F, adapterDialogMultiCheckBox);
    this.btnOk = (Button)dialog.findViewById(2131296343);
    this.btnC = (Button)dialog.findViewById(2131296344);
    this.btnOk.setOnClickListener(new View.OnClickListener() {
          final MapController this$0;
          
          final AdapterDialogMultiCheckBox val$adapter;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            ArrayList<Boolean> arrayList = new ArrayList();
            boolean bool = false;
            byte b;
            for (b = 0; b < adapter.getListSeleccionado().size(); b++) {
              if (((ClassMapData)adapter.getListSeleccionado().get(b)).isValue())
                arrayList.add(Boolean.valueOf(((ClassMapData)adapter.getListSeleccionado().get(b)).isValue())); 
            } 
            if (arrayList.size() > 1) {
              String str3 = "";
              String str1 = "";
              String str2 = str1;
              b = bool;
              while (b < adapter.getListSeleccionado().size()) {
                String str6 = str3;
                String str4 = str1;
                String str5 = str2;
                if (((ClassMapData)adapter.getListSeleccionado().get(b)).isValue())
                  if (str2.equalsIgnoreCase("")) {
                    str6 = ((ClassMapData)adapter.getListSeleccionado().get(b)).getData();
                    PreferenceRT.SetValuePreference(GlobalMembers.valuePoi1, str6, (Context)MapController.this.activity);
                    str5 = "primero";
                    str4 = str1;
                  } else {
                    str4 = ((ClassMapData)adapter.getListSeleccionado().get(b)).getData();
                    PreferenceRT.SetValuePreference(GlobalMembers.valuePoi2, str4, (Context)MapController.this.activity);
                    str5 = str2;
                    str6 = str3;
                  }  
                b++;
                str3 = str6;
                str1 = str4;
                str2 = str5;
              } 
              MapController.this.mapViewerViewG.resetFloating(str3, str1);
              MapController.this.mapViewerViewG.resetFloatingLans(str3, str1);
              dialog.dismiss();
            } 
          }
        });
    this.btnC.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/mapData/MapController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */