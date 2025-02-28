package com.roadtrack.onstar.googleMaps;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.legacy.app.ActionBarDrawerToggle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roadtrack.onstar.BO.ActionsProcess;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.ActionResultVO;
import com.roadtrack.onstar.VO.CustomActionButton;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import com.roadtrack.onstar.VO.FollowMePointsVO;
import com.roadtrack.onstar.VO.GoogleMapVO;
import com.roadtrack.onstar.VO.MarkerOrFavoritePointVO;
import com.roadtrack.onstar.VO.PushAlertsVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.adapter.ExpandableResultsAdapter;
import com.roadtrack.onstar.adapter.SearchCompleteTask;
import com.roadtrack.onstar.adapter.VehicleSpinnerAdapterWhite;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.ExpandableIcon_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.GoogleSearchResults_Interface;
import com.roadtrack.onstar.async_tasks.tasks_set.TaskSet;
import com.roadtrack.onstar.interfaces.GoogleMapInterface;
import com.roadtrack.onstar.interfaces.TomTomStartNavigation_Interface;
import com.roadtrack.onstar.mapData.MapController;
import com.roadtrack.onstar.nav.routing.NavigateCommonDialogActivity;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import com.roadtrack.onstar.tomtom.activities.ActivityFavoritesHistory;
import com.roadtrack.onstar.tomtom.nvdrwrs.TomTomMapLeftNavDrawerItem;
import com.roadtrack.onstar.tomtom.nvdrwrs.TomTomMapLeftNavDrawerListAdapter;
import com.roadtrack.onstar.tomtom.utilities.BubbleDrawable;
import com.roadtrack.onstar.tomtom.utilities.DelayedTimer;
import com.roadtrack.onstar.tomtom.utilities.OnFollowMeListener;
import com.roadtrack.onstar.tomtom.utilities.SaveImageCache;
import com.roadtrack.onstar.ui.dialogs.ActivityCall;
import com.roadtrack.onstar.utils.CallPhone;
import com.roadtrack.onstar.utils.DialogEmpty;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.RubenUltimaAlgorithm;
import com.roadtrack.onstar.utils.UserInterfaceUtils;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import com.roadtrack.onstar.viewTutorial.ClassElements;
import com.roadtrack.onstar.viewTutorial.ShowViewElement;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment implements LocationListener, GoogleMapInterface, OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener {
  public static final String TAG = MapsFragment.class.getSimpleName();
  
  private static Activity activity;
  
  private static String global_popup_btn_no_1;
  
  private static String global_popup_btn_ok_1;
  
  private static String global_popup_lbl_accionencurso_1;
  
  private static String global_popup_lbl_aviso_1;
  
  public static boolean isRouteInProgress;
  
  public static ListView lvSavedEditSearch;
  
  private static Location mCurrentLocation;
  
  public static boolean onSendRoute = false;
  
  private static ArrayList<String> saveEdittextsearch;
  
  private int TypeMarker;
  
  private TextView addr;
  
  private TextView addresslistshare1;
  
  private Button btnC;
  
  private Button btnNOk;
  
  private Button btnOk;
  
  private Button btnOk1;
  
  private BubbleDrawable bubbleDrawable;
  
  private Button buttonOk;
  
  private Button buttonOk1;
  
  private Button buttonToggleLeftNavigationDrawer;
  
  public View.OnClickListener clickTutorial;
  
  private SearchCompleteTask controlViews = null;
  
  private MapController controller;
  
  private String currentFunction = TAG;
  
  private ArrayList<PointInfo> currentMarkers = new ArrayList<PointInfo>();
  
  private TextView dateShare1;
  
  private DBFunctions dbFunctions;
  
  private String destinationAddress;
  
  private int[] destinationInMicroDegrees;
  
  private String destinationName;
  
  private DrawerLayout drawerLayout;
  
  private ExpandableListView expListView;
  
  private FrameLayout frame_container;
  
  private ExpandableListView fullList;
  
  private EditText googSearch;
  
  private GoogleSearchResults_Interface googleSearchResults_interface;
  
  private LinearLayout idTutorial;
  
  private ImageButton imageButtonCenterLocation;
  
  private ImageButton imageButtonFavs;
  
  private ImageButton imageButtonFindMe;
  
  private ImageButton imageButtonGoToRoute;
  
  private ImageButton imageButtonSendRoute;
  
  private ImageButton imageButtonShareLocation;
  
  private ImageView imageViewFindMe;
  
  private ImageView imageViewSendRoute;
  
  private ImageView imageViewSplashScreen;
  
  private boolean isFromFavouritesActivity = false;
  
  private boolean isFromLongPressEventOrGoogle;
  
  private boolean isLongPress = false;
  
  private Boolean isThereINET;
  
  private boolean isTutorialAvailable;
  
  private TextView lat;
  
  private LatLng latLngGlobal;
  
  private ActionBarDrawerToggle leftActionBarDrawerToggle;
  
  private TomTomMapLeftNavDrawerListAdapter leftDrawerListAdapter;
  
  private ListView leftDrawerListView;
  
  private TypedArray leftDrawerMenuIcons;
  
  private ArrayList<TomTomMapLeftNavDrawerItem> leftNavDrawerItems;
  
  private LinearLayout linearLayoutBottom;
  
  private TextView lon;
  
  Marker mCurrLocationMarker;
  
  private DelayedTimer mDelayedTimer;
  
  private ExpandableResultsAdapter mExpandableListAdapter;
  
  private GoogleApiClient mGoogleApiClient;
  
  private ArrayList<HashMap<String, String>> mGoogleSearchResults;
  
  LocationRequest mLocationRequest;
  
  private GoogleMap mMap;
  
  private RubenUltimaAlgorithm mRuAlgorithm;
  
  private SupportMapFragment mapFragment;
  
  private Enums.navigationProcess mapType;
  
  private MapsFragmentView mapsFragmentView;
  
  public boolean myLocationPressed = false;
  
  private TextView nm;
  
  GoogleMapVO params;
  
  private ArrayList<HashMap<String, String>> placesListItems;
  
  private int position;
  
  private boolean pressTuto;
  
  private ProgressBar progressBarFindMe;
  
  private ProgressBar progressBarSendRoute;
  
  ProgressDialog progressDialog;
  
  private RelativeLayout rlContainerMenu;
  
  private onstarApplication rtApp;
  
  private ShowViewElement show;
  
  private Spinner spinnerVehicle;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private TaskSet taskSet;
  
  private Typeface tf;
  
  private TextView tvDateInfo1;
  
  private String typeFloating;
  
  private ListView vehicleListShare;
  
  View view;
  
  private ViewGroup viewGroupTomTomSearchBar;
  
  static {
    isRouteInProgress = false;
    mCurrentLocation = null;
  }
  
  public MapsFragment() {
    new LinkedList();
    this.destinationName = null;
    this.destinationAddress = null;
    this.pressTuto = true;
    this.placesListItems = new ArrayList<HashMap<String, String>>();
    this.params = new GoogleMapVO();
    this.clickTutorial = new View.OnClickListener() {
        final MapsFragment this$0;
        
        public void onClick(View param1View) {
          param1View = MapsFragment.this.getActivity().getCurrentFocus();
          if (param1View != null) {
            ((InputMethodManager)MapsFragment.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(param1View.getWindowToken(), 0);
            (new Handler()).postDelayed(new Runnable() {
                  final MapsFragment.null this$1;
                  
                  public void run() {
                    if (MapsFragment.this.pressTuto)
                      MapsFragment.this.showTutorial(); 
                  }
                },  100L);
          } 
        }
      };
  }
  
  private void ActionActived() {
    try {
      Dialog dialog = Utilities.simpleDialog((Context)activity, null, global_popup_lbl_aviso_1, global_popup_lbl_accionencurso_1, true, global_popup_btn_ok_1, false, global_popup_btn_no_1, 20.0F, 20.0F);
      this.buttonOk1 = (Button)dialog.findViewById(2131296343);
      Button button = this.buttonOk1;
      View.OnClickListener onClickListener = new View.OnClickListener() {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        };
      super(this, dialog);
      button.setOnClickListener(onClickListener);
      dialog.show();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: dialogBackALert", exception.getMessage());
    } 
  }
  
  private boolean VerifyGoToRouteEnable() {
    String str = Enums.Services.NavigationWithTraffic.GetCodeString();
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    if (userDevicesVO != null)
      linkedHashMap = userDevicesVO.getNavigationActions(); 
    ArrayList arrayList = new ArrayList();
    arrayList.addAll(linkedHashMap.keySet());
    int i = UserInterfaceUtils.tieneWaterMark(Integer.parseInt(str), 2, this.rtApp);
    boolean bool = false;
    Iterator<String> iterator = arrayList.iterator();
    while (iterator.hasNext()) {
      if (((String)iterator.next()).equals(str) && i == 1)
        bool = true; 
    } 
    return bool;
  }
  
  private LinkedHashMap addFavoritesToActions(LinkedHashMap<String, LinkedHashMap<String, Integer>> paramLinkedHashMap) {
    LinkedHashMap<Object, Object> linkedHashMap1 = new LinkedHashMap<Object, Object>();
    LinkedHashMap<Object, Object> linkedHashMap2 = new LinkedHashMap<Object, Object>();
    boolean bool1 = true;
    boolean bool2 = true;
    byte b = 1;
    linkedHashMap2.put("water", Integer.valueOf(1));
    linkedHashMap2.put("hasmap", Integer.valueOf(0));
    if (paramLinkedHashMap != null) {
      int i = paramLinkedHashMap.size();
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i == 4)
              for (String str : paramLinkedHashMap.keySet()) {
                if (b == 3)
                  linkedHashMap1.put(Enums.Services.Favs.GetCodeString(), linkedHashMap2); 
                linkedHashMap1.put(str, paramLinkedHashMap.get(str));
                b++;
              }  
          } else {
            Iterator<String> iterator = paramLinkedHashMap.keySet().iterator();
            for (b = bool1; iterator.hasNext(); b++) {
              String str = iterator.next();
              if (b == 3)
                linkedHashMap1.put(Enums.Services.Favs.GetCodeString(), linkedHashMap2); 
              linkedHashMap1.put(str, paramLinkedHashMap.get(str));
            } 
          } 
        } else {
          Iterator<String> iterator = paramLinkedHashMap.keySet().iterator();
          for (b = bool2; iterator.hasNext(); b++) {
            String str = iterator.next();
            if (b == 2)
              linkedHashMap1.put(Enums.Services.Favs.GetCodeString(), linkedHashMap2); 
            linkedHashMap1.put(str, paramLinkedHashMap.get(str));
          } 
        } 
      } else {
        paramLinkedHashMap.put(Enums.Services.Favs.GetCodeString(), linkedHashMap2);
        return paramLinkedHashMap;
      } 
    } 
    return linkedHashMap1;
  }
  
  private void centerBubbles(Location[] paramArrayOfLocation) {
    long l;
    try {
      l = paramArrayOfLocation[0].getExtras().getLong("MAXDISTANCE");
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      l = 0L;
    } 
    LatLngBounds latLngBounds = new LatLngBounds(new LatLng(paramArrayOfLocation[1].getLatitude(), paramArrayOfLocation[1].getLongitude()), new LatLng(paramArrayOfLocation[2].getLatitude(), paramArrayOfLocation[2].getLongitude()));
    this.mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 250));
    if (l < 100L)
      this.mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0F), 1, null); 
  }
  
  private void centerMenu(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 4)
              this.mapsFragmentView.getGoTorouteGroup().setVisibility(8); 
          } else {
            this.mapsFragmentView.getShareGroup().setVisibility(8);
            this.mapsFragmentView.getGoTorouteGroup().setVisibility(8);
          } 
        } else {
          this.mapsFragmentView.getFavsGroup().setVisibility(8);
          this.mapsFragmentView.getShareGroup().setVisibility(8);
          this.mapsFragmentView.getGoTorouteGroup().setVisibility(8);
        } 
      } else {
        this.mapsFragmentView.getSendRouteGroup().setVisibility(8);
        this.mapsFragmentView.getFavsGroup().setVisibility(8);
        this.mapsFragmentView.getShareGroup().setVisibility(8);
        this.mapsFragmentView.getGoTorouteGroup().setVisibility(8);
      } 
    } else {
      this.mapsFragmentView.getAllMenu().setVisibility(8);
    } 
  }
  
  private void closeLeftNavigationDrawer() {
    DrawerLayout drawerLayout = this.drawerLayout;
    if (drawerLayout != null)
      drawerLayout.closeDrawer(8388611); 
  }
  
  private void createNavigationBottons() {
    resetMenu();
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    if (userDevicesVO != null)
      linkedHashMap = userDevicesVO.getNavigationActions(); 
    linkedHashMap = addFavoritesToActions((LinkedHashMap)linkedHashMap);
    int i = linkedHashMap.size();
    Iterator<String> iterator = linkedHashMap.keySet().iterator();
    for (byte b = 1; iterator.hasNext(); b++) {
      String str = iterator.next();
      int j = UserInterfaceUtils.tieneWaterMark(Integer.valueOf(str).intValue(), 2, this.rtApp);
      Enums.Services services = Enums.Services.GetValue(Integer.valueOf(str).intValue());
      if (b != 1) {
        if (b != 2) {
          if (b != 3) {
            if (b != 4) {
              if (b == 5)
                setActionAnIcon(services, this.imageButtonGoToRoute, b, j); 
            } else {
              setActionAnIcon(services, this.imageButtonShareLocation, b, j);
            } 
          } else {
            setActionAnIcon(services, this.imageButtonFavs, b, j);
          } 
        } else {
          setActionAnIcon(services, this.imageButtonSendRoute, b, j);
        } 
      } else {
        setActionAnIcon(services, this.imageButtonFindMe, b, j);
      } 
    } 
    centerMenu(i);
  }
  
  private void disableAllElements() {
    this.viewGroupTomTomSearchBar.setVisibility(8);
    this.imageButtonFindMe.setVisibility(8);
    this.imageButtonSendRoute.setVisibility(8);
    this.imageButtonFavs.setVisibility(8);
    this.imageButtonShareLocation.setVisibility(8);
    this.imageButtonGoToRoute.setVisibility(8);
    this.expListView.setVisibility(8);
    this.googSearch.setVisibility(8);
    this.imageButtonCenterLocation.setVisibility(8);
    if (!this.isTutorialAvailable)
      this.idTutorial.setVisibility(8); 
    hideBottomElements();
    lockRighDrawer(true);
    this.frame_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1, 0.0F));
    this.rlContainerMenu.setVisibility(8);
  }
  
  private void disableElementsOnFindMe() {
    this.viewGroupTomTomSearchBar.setVisibility(8);
    this.imageButtonSendRoute.setVisibility(8);
    this.imageButtonFavs.setVisibility(8);
    this.imageButtonShareLocation.setVisibility(8);
    this.imageButtonGoToRoute.setVisibility(8);
    this.expListView.setVisibility(8);
    this.googSearch.setVisibility(8);
    this.imageButtonCenterLocation.setVisibility(8);
    hideBottomElements();
    lockRighDrawer(true);
    this.frame_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1, 0.0F));
    this.rlContainerMenu.setVisibility(8);
  }
  
  private void drawMarker(ArrayList<PointInfo> paramArrayList) {
    if (this.mMap != null)
      for (byte b = 0; b < paramArrayList.size(); b++) {
        GoogleMap googleMap = this.mMap;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(((PointInfo)paramArrayList.get(b)).getLatLng());
        markerOptions.title("");
        this.mCurrLocationMarker = googleMap.addMarker(markerOptions);
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(((PointInfo)paramArrayList.get(b)).getLatLng()));
        enableSendRouteShareLocationGoToRoute();
      }  
  }
  
  private void enableFavorites(Boolean paramBoolean) {
    UserInterfaceUtils.getActionPosition(Integer.parseInt(Enums.Services.Favs.GetCodeString()), 2, this.rtApp);
    if (paramBoolean.booleanValue()) {
      this.imageButtonFavs.setAlpha(1.0F);
      this.imageButtonFavs.setEnabled(true);
    } else {
      this.imageButtonFavs.setAlpha(0.6F);
      this.imageButtonFavs.setEnabled(false);
    } 
  }
  
  private void enableSendRouteShareLocationGoToRoute() {
    String str = Enums.Services.SendPNDNavigationCommand.GetCodeString();
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    if (userDevicesVO != null)
      linkedHashMap = userDevicesVO.getNavigationActions(); 
    ArrayList arrayList = new ArrayList();
    arrayList.addAll(linkedHashMap.keySet());
    int j = UserInterfaceUtils.tieneWaterMark(Integer.parseInt(str), 2, this.rtApp);
    int i = UserInterfaceUtils.getActionPosition(Integer.parseInt(str), 2, this.rtApp);
    Iterator<String> iterator = arrayList.iterator();
    boolean bool = false;
    while (iterator.hasNext()) {
      if (((String)iterator.next()).equals(str) && j == 1) {
        Drawable drawable = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.nav_iconroutepnd, 2131165575);
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i == 4)
                this.imageButtonGoToRoute.setImageDrawable(drawable); 
            } else {
              this.imageButtonShareLocation.setImageDrawable(drawable);
            } 
          } else {
            this.imageButtonSendRoute.setBackground(drawable);
          } 
        } else {
          this.imageButtonFindMe.setImageDrawable(drawable);
        } 
        bool = true;
      } 
    } 
    i = UserInterfaceUtils.getActionPosition(Integer.parseInt(Enums.Services.NavigationWithTraffic.GetCodeString()), 2, this.rtApp);
    if (!bool) {
      Drawable drawable = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.nav_iconroutepnd_disable, 2131165576);
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i == 4) {
              this.imageButtonGoToRoute.setImageDrawable(drawable);
              this.imageButtonGoToRoute.setEnabled(false);
            } 
          } else {
            this.imageButtonShareLocation.setImageDrawable(drawable);
            this.imageButtonShareLocation.setEnabled(false);
          } 
        } else {
          this.imageButtonSendRoute.setBackground(drawable);
          this.imageButtonSendRoute.setEnabled(false);
        } 
      } else {
        this.imageButtonFindMe.setImageDrawable(drawable);
        this.imageButtonFindMe.setEnabled(false);
      } 
    } 
    if (VerifyGoToRouteEnable()) {
      Drawable drawable = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.nav_icondirections, 2131165567);
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i == 4)
              this.imageButtonGoToRoute.setImageDrawable(drawable); 
          } else {
            this.imageButtonShareLocation.setImageDrawable(drawable);
          } 
        } else {
          this.imageButtonSendRoute.setImageDrawable(drawable);
        } 
      } else {
        this.imageButtonFindMe.setImageDrawable(drawable);
      } 
    } 
  }
  
  private boolean existInsavedList(String paramString) {
    if (saveEdittextsearch == null)
      saveEdittextsearch = new ArrayList<String>(); 
    ArrayList<String> arrayList = saveEdittextsearch;
    if (arrayList != null && arrayList.size() > 0) {
      int i = saveEdittextsearch.size();
      for (byte b = 0; b < i; b++) {
        if (paramString.equalsIgnoreCase(saveEdittextsearch.get(b)))
          return true; 
      } 
    } 
    return false;
  }
  
  private void fakeGoogleSearchResult(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean1, boolean paramBoolean2) {
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_lbl_totalResultados_1, 2131690166);
    MapController mapController = this.controller;
    LatLng latLng1 = this.latLngGlobal;
    final double[] startLatLong = mapController.microDegreesToLatLng(latLng1.latitude, latLng1.longitude);
    RubenUltimaAlgorithm rubenUltimaAlgorithm = this.mRuAlgorithm;
    LatLng latLng2 = this.latLngGlobal;
    double d = rubenUltimaAlgorithm.linearDistance(latLng2.latitude, latLng2.longitude, Double.parseDouble(paramString2), Double.parseDouble(paramString3));
    d = this.mRuAlgorithm.linearDistanceToKilometers(d);
    String str2 = this.mRuAlgorithm.formatLinearDistance(d);
    String str3 = this.mRuAlgorithm.formatLinearDistanceWithString(d);
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    ArrayList<HashMap<Object, Object>> arrayList1 = new ArrayList();
    hashMap.put("reference", paramString1);
    hashMap.put(SearchCompleteTask.KEY_LATITUDE, paramString2);
    hashMap.put(SearchCompleteTask.KEY_LONGITUDE, paramString3);
    hashMap.put("name", paramString4);
    hashMap.put("address", paramString5);
    hashMap.put("distance", str2);
    hashMap.put("distanceString", str3);
    arrayList1.add(hashMap);
    ArrayList<String> arrayList = new ArrayList();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str1);
    stringBuilder.append(" (");
    stringBuilder.append(arrayList1.size());
    stringBuilder.append(")");
    arrayList.add(stringBuilder.toString());
    boolean bool = this.isLongPress;
    Boolean bool2 = Boolean.valueOf(false);
    Boolean bool1 = bool2;
    if (!bool) {
      ArrayList<HashMap<String, String>> arrayList2 = this.mGoogleSearchResults;
      if (arrayList2 != null && arrayList2.size() == 1) {
        Boolean bool3 = bool2;
      } else if (this.mGoogleSearchResults == null && arrayList1.size() == 1) {
        Boolean bool3 = bool2;
      } else {
        Boolean bool3 = Boolean.valueOf(paramBoolean1);
      } 
    } 
    if (paramBoolean2)
      bool1 = Boolean.valueOf(true); 
    this.mExpandableListAdapter = new ExpandableResultsAdapter(activity, arrayList, arrayList1, new TomTomStartNavigation_Interface() {
          final MapsFragment this$0;
          
          public void onStartNavigation(String param1String1, String param1String2, String param1String3, String param1String4) {
            if (MapsFragment.this.mDelayedTimer == null)
              return; 
            MapsFragment.this.mDelayedTimer.stopUpdates();
            throw null;
          }
        },  bool1.booleanValue(), new ExpandableIcon_Interface() {
          final MapsFragment this$0;
          
          final double[] val$startLatLong;
          
          public void onExpand() {
            MapsFragment mapsFragment = MapsFragment.this;
            double[] arrayOfDouble = startLatLong;
            mapsFragment.updateGoogleSearchResultsDistances(true, arrayOfDouble[0], arrayOfDouble[1]);
          }
        });
    this.expListView.setAdapter((ExpandableListAdapter)this.mExpandableListAdapter);
    if (!arrayList1.isEmpty()) {
      this.expListView.expandGroup(0);
      if (this.expListView.getVisibility() == 8)
        this.expListView.setVisibility(0); 
    } 
  }
  
  private void favsFunction() {
    if (ValidateTheftAuto())
      return; 
    int i = this.dbFunctions.countFavorites(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId(), "Favorites");
    if (this.mCurrLocationMarker != null) {
      if (i >= 10) {
        String str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.favoritos_lbl_excediolimite_2, 2131689817);
        DialogEmpty dialogEmpty = new DialogEmpty(activity, str);
        dialogEmpty.show();
        dialogEmpty.setOnDismissListener(new DialogInterface.OnDismissListener() {
              final MapsFragment this$0;
              
              public void onDismiss(DialogInterface param1DialogInterface) {
                MapsFragment.this.resetMapScreen();
                Intent intent = new Intent((Context)MapsFragment.activity, ActivityFavoritesHistory.class);
                intent.putExtra("TypeItem", (Serializable)Enums.TypeItem.NaviFavoritesHist);
                MapsFragment.activity.startActivityForResult(intent, 6);
              }
            });
      } else {
        saveFavourite();
      } 
    } else {
      resetMapScreen();
      Intent intent = new Intent((Context)activity, ActivityFavoritesHistory.class);
      intent.putExtra("TypeItem", (Serializable)Enums.TypeItem.NaviFavoritesHist);
      activity.startActivityForResult(intent, 6);
    } 
  }
  
  private void fillVehicleList(Spinner paramSpinner, Context paramContext) {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/googleMaps/MapsFragment}} */
    if (paramSpinner == null) {
      /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/googleMaps/MapsFragment}} */
      return;
    } 
    try {
      ArrayList<String> arrayList = new ArrayList();
      this();
      int i;
      for (i = 0; i < this.rtApp.getmDeviceUserList().size(); i++)
        arrayList.add(((UserDevicesVO)this.rtApp.getmDeviceUserList().get(i)).getName()); 
      GlobalMembers.vehicleList = arrayList;
      VehicleSpinnerAdapterWhite vehicleSpinnerAdapterWhite = new VehicleSpinnerAdapterWhite();
      this(paramContext, 2131427513, 2131297225, paramSpinner, arrayList);
      vehicleSpinnerAdapterWhite.setDropDownViewResource(17367049);
      i = Utilities.getLastKnownVehicleSelected(getContext(), this.dbFunctions.getUserPreference(GlobalMembers.userLogged).getUser(), this.rtApp);
      paramSpinner.setAdapter((SpinnerAdapter)vehicleSpinnerAdapterWhite);
      paramSpinner.setSelection(i);
      Utilities.setDeviceType(this.rtApp);
      AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
          final MapsFragment this$0;
          
          final Spinner val$spinner;
          
          public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            Utilities.escribeArchivo(MapsFragment.TAG, "SELECTVEHICLE", "onItemSelected");
            MapsFragment.this.setVehicleSelected(spinner);
            Utilities.setDeviceType(MapsFragment.this.rtApp);
          }
          
          public void onNothingSelected(AdapterView<?> param1AdapterView) {}
        };
      super(this, paramSpinner);
      paramSpinner.setOnItemSelectedListener(onItemSelectedListener);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: setOnItemSelectedItem", exception.getMessage());
    } finally {}
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/googleMaps/MapsFragment}} */
  }
  
  private void findMeFunction() {
    if (ValidateTheftAuto())
      return; 
    if (!NetUtilities.validateNetwork(getContext(), false, true))
      return; 
    if (!MainActivity.onFindMe.booleanValue()) {
      MainActivity.onFindMe = Boolean.valueOf(true);
      generateFindMe(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId(), 0);
    } else {
      Toast.makeText((Context)activity, global_popup_lbl_accionencurso_1, 1).show();
    } 
  }
  
  private void generateAssistanceDialog() {
    String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
    String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_llamar_1, 2131689948);
    Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.ic_btnasistenciamenu_onstar_dialog, 2131165443);
    final Dialog dialog = Utilities.simpleDialog(getContext(), null, str3, str1, true, str2, false, global_popup_btn_no_1);
    this.btnOk = (Button)dialog.findViewById(2131296343);
    this.btnNOk = (Button)dialog.findViewById(2131296344);
    this.btnOk.setOnClickListener(new View.OnClickListener() {
          final MapsFragment this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            GlobalMembers.btnpress = 2;
            if (GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
              if (!MainActivity.isBtAndPlatinumConnected()) {
                Utilities.sendIntentCallAction(MapsFragment.this.getContext());
              } else {
                if (!MainActivity.bEsperandoP8 && MainActivity.iPreviousCallIndex == 0 && MainActivity.bRelayReady) {
                  Intent intent = new Intent(MapsFragment.this.getContext(), ActivityCall.class);
                  intent.putExtra("Boton", "Asistencia");
                  MapsFragment.this.startActivityForResult(intent, 456);
                } 
                MainActivity.CallOrHangUp(Enums.Calls.AssistanceCallAndMessage);
              } 
            } else {
              Utilities.sendIntentCallAction(MapsFragment.this.getContext());
            } 
            dialog.dismiss();
          }
        });
    this.btnNOk.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
  }
  
  private void generateEmergencyDialog() {
    if (GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
      if (!MainActivity.isBtAndPlatinumConnected()) {
        (new CallPhone(getContext())).Emergency();
      } else {
        if (((MainActivity.bEsperandoP8 ^ true) & MainActivity.bRelayReady) != 0) {
          Intent intent = new Intent(getContext(), ActivityCall.class);
          intent.putExtra("Boton", "Emergencia");
          startActivityForResult(intent, 123);
        } 
        MainActivity.CallOrHangUp(Enums.Calls.EmergencyCallAndMessage);
      } 
    } else {
      (new CallPhone(getContext())).Emergency();
    } 
  }
  
  private void generateFindMe(String paramString, int paramInt) {
    String str5 = this.rtApp.getSessionKey();
    String str4 = this.rtApp.getUserAccessData()[1];
    String str2 = this.rtApp.getAccountID();
    String str1 = this.rtApp.getLocatorUserId();
    String str3 = Enums.Services.FindMe.GetCodeString();
    Activity activity = activity;
    ActionsProcess actionsProcess = new ActionsProcess((Context)activity, this.progressBarFindMe, this.imageViewFindMe, str3, paramString, activity);
    actionsProcess.setOnPostExecuteListener(new ActionsProcess.OnPostExecuteListener() {
          final MapsFragment this$0;
          
          public void onPostExecuteListener(ActionResultVO param1ActionResultVO) {
            MapsFragment.this.onPostFindMeFromNavigation(param1ActionResultVO);
          }
        });
    actionsProcess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str5, str4, str2, str1, str3, "" });
  }
  
  private void generateSendRoute(String paramString, double paramDouble1, double paramDouble2) {
    String str1 = paramString;
    String str2 = Enums.Services.SendPNDNavigationCommand.GetCodeString();
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    userDevicesVO.getActions();
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    if (userDevicesVO != null)
      linkedHashMap = userDevicesVO.getNavigationActions(); 
    ArrayList arrayList = new ArrayList();
    arrayList.addAll(linkedHashMap.keySet());
    int i = UserInterfaceUtils.tieneWaterMark(Integer.parseInt(str2), 2, this.rtApp);
    Iterator<String> iterator = arrayList.iterator();
    boolean bool = false;
    while (iterator.hasNext()) {
      if (((String)iterator.next()).equals(str2) && i == 1)
        bool = true; 
    } 
    if (bool) {
      String str3 = str1;
      if (str1 != null) {
        str3 = str1;
        if (paramString.length() > 87) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(str1.substring(0, 83));
          stringBuilder.append("...");
          str3 = stringBuilder.toString();
        } 
      } 
      double[] arrayOfDouble = new double[2];
      arrayOfDouble[0] = paramDouble1;
      arrayOfDouble[1] = paramDouble2;
      String str5 = this.rtApp.getSessionKey();
      String str4 = this.rtApp.getUserAccessData()[1];
      paramString = this.rtApp.getAccountID();
      String str6 = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId();
      str1 = this.rtApp.getLocatorUserId();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(arrayOfDouble[1]);
      stringBuilder1.append("");
      String str8 = stringBuilder1.toString();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(arrayOfDouble[0]);
      stringBuilder2.append("");
      String str7 = String.format("%s|%s|%s|3", new Object[] { str8, stringBuilder2.toString(), str3 });
      Activity activity = activity;
      ActionsProcess actionsProcess = new ActionsProcess((Context)activity, this.progressBarSendRoute, this.imageViewSendRoute, str2, str6, activity);
      actionsProcess.setAddress(str3);
      actionsProcess.setOnPostExecuteListener(new ActionsProcess.OnPostExecuteListener(this) {
            public void onPostExecuteListener(ActionResultVO param1ActionResultVO) {
              MapsFragment.onSendRoute = false;
            }
          });
      actionsProcess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str5, str4, paramString, str1, str7, "" });
    } 
  }
  
  private String getAddressFromLatLong(LatLng paramLatLng) {
    Geocoder geocoder2 = null;
    Geocoder geocoder1 = geocoder2;
    if (paramLatLng != null) {
      geocoder1 = new Geocoder(getContext());
      try {
        List<Address> list = geocoder1.getFromLocation(paramLatLng.latitude, paramLatLng.longitude, 1);
        geocoder1 = geocoder2;
        if (list.size() > 0) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          String str1 = ((Address)list.get(0)).getThoroughfare();
          String str2 = "";
          if (str1 == null) {
            str1 = "";
          } else {
            str1 = ((Address)list.get(0)).getThoroughfare();
          } 
          stringBuilder.append(str1);
          if (((Address)list.get(0)).getSubThoroughfare() == null) {
            str1 = "";
          } else {
            StringBuilder stringBuilder1 = new StringBuilder();
            this();
            stringBuilder1.append(" ");
            stringBuilder1.append(((Address)list.get(0)).getSubThoroughfare());
            str1 = stringBuilder1.toString();
          } 
          stringBuilder.append(str1);
          stringBuilder.append(", ");
          if (((Address)list.get(0)).getLocality() == null) {
            str1 = str2;
          } else {
            str1 = ((Address)list.get(0)).getLocality();
          } 
          stringBuilder.append(str1);
          stringBuilder.append(".");
          str2 = stringBuilder.toString();
        } 
      } catch (IOException iOException) {
        Utilities.escribeArchivo(TAG, "Error getAddressFomLatong", iOException.getMessage());
        geocoder1 = geocoder2;
      } 
    } 
    return (String)geocoder1;
  }
  
  private void gotoRouteFunction() {
    if (this.mCurrLocationMarker != null) {
      if (ValidateTheftAuto())
        return; 
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("google.navigation:q=");
        stringBuilder.append((this.mCurrLocationMarker.getPosition()).latitude);
        stringBuilder.append(",");
        stringBuilder.append((this.mCurrLocationMarker.getPosition()).longitude);
        Uri uri = Uri.parse(stringBuilder.toString());
        Intent intent = new Intent();
        this("android.intent.action.VIEW", uri);
        intent.setPackage("com.google.android.apps.maps");
        if (intent.resolveActivity(activity.getPackageManager()) != null)
          startActivity(intent); 
      } catch (Exception exception) {
        exception.printStackTrace();
      } 
    } else {
      String str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_lbl_busquedaalertamensaje_1, 2131690147);
      Toast.makeText((Context)activity, str, 1).show();
    } 
  }
  
  private void hideAddressSearch() {
    EditText editText = this.googSearch;
    if (editText != null)
      editText.setText(""); 
    if (this.expListView.getVisibility() == 0)
      this.expListView.setVisibility(8); 
    if (this.viewGroupTomTomSearchBar.getVisibility() == 8)
      this.viewGroupTomTomSearchBar.setVisibility(0); 
  }
  
  private void hideBottomElements() {
    if (this.linearLayoutBottom.getVisibility() == 0)
      this.linearLayoutBottom.setVisibility(8); 
  }
  
  private void initButtons() {
    this.imageButtonFindMe = this.mapsFragmentView.getImageButtonFindMe();
    this.imageButtonSendRoute = this.mapsFragmentView.getImageButtonSendRoute();
    this.imageButtonShareLocation = this.mapsFragmentView.getImageButtonShareLocation();
    this.imageButtonGoToRoute = this.mapsFragmentView.getImageButtonGoToRoute();
    this.imageButtonFavs = this.mapsFragmentView.getImageButtonFavs();
  }
  
  private int[] latLngToMicroDegrees(double paramDouble1, double paramDouble2) {
    return new int[] { (int)(paramDouble1 * 1000000.0D), (int)(paramDouble2 * 1000000.0D) };
  }
  
  private void lockRighDrawer(boolean paramBoolean) {
    this.drawerLayout.setDrawerLockMode(paramBoolean);
  }
  
  private void onPostFindMeFromNavigation(ActionResultVO paramActionResultVO) {
    String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_accionlocalizame_1, 2131689877);
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_lbl_localizame_gpssincobertura_1, 2131690158);
    float f = paramActionResultVO.getLatitude();
    Boolean bool = Boolean.valueOf(false);
    if (f != 0.0F && paramActionResultVO.getLongitude() != 0.0F && !isRouteInProgress) {
      hideAddressSearch();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(paramActionResultVO.getLatitude());
      stringBuilder2.append("");
      String str4 = stringBuilder2.toString();
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append(paramActionResultVO.getLongitude());
      stringBuilder3.append("");
      String str6 = stringBuilder3.toString();
      String str5 = Utilities.getUTCToNormalDate(paramActionResultVO.getEventDateTime(), "dd/MM/yyyy HH:mm:ss").replace("/", "-");
      if (paramActionResultVO.getGpsStatus() == Enums.GpsStatusCode.Ok.value())
        str1 = global_popup_btn_ok_1; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str4);
      stringBuilder1.append("_");
      stringBuilder1.append(str6);
      String str3 = stringBuilder1.toString();
      GlobalMembers.lastNavParamsFindMe = "";
      this.mMap.clear();
      this.currentMarkers.clear();
      showFindMeBubble(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getName(), str2, str5, str1, str3, Double.parseDouble(str4), Double.parseDouble(str6));
      if (str2.equals(str2)) {
        enableFavorites(bool);
        if (GlobalMembers.activeTutorial) {
          this.idTutorial.setVisibility(8);
          this.isTutorialAvailable = false;
          ShowViewElement showViewElement = this.show;
          if (showViewElement != null)
            showViewElement.removeView(this); 
        } 
      } 
    } else if (activity != null) {
      String str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.coordinatesNotValid, 2131689738);
      Toast.makeText((Context)activity, str, 1).show();
    } 
    ProgressDialog progressDialog = this.progressDialog;
    if (progressDialog != null)
      progressDialog.cancel(); 
    MainActivity.onFindMe = bool;
  }
  
  private void openLeftNavigationDrawer() {
    DrawerLayout drawerLayout = this.drawerLayout;
    if (drawerLayout != null)
      drawerLayout.openDrawer(8388611); 
  }
  
  private void resetMapScreen() {
    if (this.currentFunction.equals(TAG)) {
      hideAddressSearch();
      lockRighDrawer(false);
      createNavigationBottons();
    } 
    this.rlContainerMenu.setVisibility(0);
    GlobalMembers.objSendFavoritesMessage = null;
    this.isFromFavouritesActivity = false;
    this.isLongPress = false;
    this.destinationName = null;
    this.destinationAddress = null;
    GlobalMembers.isFromShareMarkerActivity = false;
    GlobalMembers.isFromShareFavActivity = false;
    if (this.currentFunction.equals(TAG) && GlobalMembers.activeTutorial && this.mapType.equals(Enums.navigationProcess.Navigation)) {
      this.idTutorial = (LinearLayout)this.view.findViewById(2131296607);
      this.idTutorial.setOnClickListener(this.clickTutorial);
      this.idTutorial.setVisibility(0);
    } 
    enableFavorites(Boolean.valueOf(true));
  }
  
  private void resetMenu() {
    this.mapsFragmentView.getAllMenu().setVisibility(0);
    this.mapsFragmentView.getFindMeGroup().setVisibility(0);
    this.mapsFragmentView.getSendRouteGroup().setVisibility(0);
    this.mapsFragmentView.getFavsGroup().setVisibility(0);
    this.mapsFragmentView.getShareGroup().setVisibility(0);
    this.mapsFragmentView.getGoTorouteGroup().setVisibility(0);
  }
  
  private void saveFavourite() {
    String str = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId();
    try {
      if (this.currentMarkers != null && this.currentMarkers.size() > 0) {
        Intent intent = new Intent();
        this((Context)activity, NavigateCommonDialogActivity.class);
        Bundle bundle = new Bundle();
        this();
        bundle.putString("deviceId", str);
        bundle.putString("adress", ((PointInfo)this.currentMarkers.get(0)).getAddress());
        bundle.putString("name", ((PointInfo)this.currentMarkers.get(0)).getName());
        bundle.putDouble("latitude", (((PointInfo)this.currentMarkers.get(0)).getLatLng()).latitude);
        bundle.putDouble("longitude", (((PointInfo)this.currentMarkers.get(0)).getLatLng()).longitude);
        bundle.putSerializable("TypeItem", (Serializable)Enums.TypeItem.Favorites);
        intent.putExtra("extra", bundle);
        GlobalMembers.googleSearchTitleToFavorite = ((PointInfo)this.currentMarkers.get(0)).getName();
        startActivity(intent);
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", "Couldn't start activity");
    } 
  }
  
  private void saveMarkerInfo(final LatLng latLng) {
    (new Thread(new Runnable() {
          final MapsFragment this$0;
          
          final LatLng val$latLng;
          
          public void run() {
            PointInfo pointInfo = new PointInfo(MapsFragment.this.getAddressFromLatLong(latLng), null, latLng, null);
            pointInfo.setName(pointInfo.getAddress());
            String str = MapsFragment.TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("address: ");
            stringBuilder.append(pointInfo.getName());
            Utilities.escribeArchivo(str, "sendRouteFunction", stringBuilder.toString());
            MapsFragment.this.currentMarkers.add(pointInfo);
          }
        })).start();
  }
  
  private void saveTempSearch(String paramString) {
    if (saveEdittextsearch == null)
      saveEdittextsearch = new ArrayList<String>(); 
    if (saveEdittextsearch.size() < 5) {
      saveEdittextsearch.add(paramString);
    } else {
      saveEdittextsearch.remove(0);
      saveEdittextsearch.add(paramString);
    } 
  }
  
  private void sendRouteFunction() {
    if (!NetUtilities.validateNetwork(getContext(), false, true))
      return; 
    if (this.mCurrLocationMarker != null) {
      if (ValidateTheftAuto())
        return; 
      if (!onSendRoute) {
        onSendRoute = true;
        ArrayList<PointInfo> arrayList = this.currentMarkers;
        if (arrayList != null && arrayList.size() > 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(((PointInfo)this.currentMarkers.get(0)).getName());
          stringBuilder.append(" - ");
          stringBuilder.append(((PointInfo)this.currentMarkers.get(0)).getName());
          generateSendRoute(stringBuilder.toString(), (((PointInfo)this.currentMarkers.get(0)).getLatLng()).latitude, (((PointInfo)this.currentMarkers.get(0)).getLatLng()).longitude);
        } 
      } else {
        Toast.makeText((Context)activity, global_popup_lbl_accionencurso_1, 1).show();
      } 
    } else {
      String str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_lbl_busquedaalertamensaje_1, 2131690147);
      Toast.makeText((Context)activity, str, 1).show();
    } 
    onSendRoute = false;
  }
  
  private void sendTomTomStatistics() {
    this.taskSet.executeSendTomTomStatistics_Task(new Base_Interface(this) {
          public void onFail(String param1String) {}
          
          public void onSuccess(String param1String) {}
        },  getContext());
  }
  
  private void setActionAnIcon(Enums.Services paramServices, ImageButton paramImageButton, int paramInt1, int paramInt2) {
    if (paramInt2 != 0) {
      if (paramInt2 != 1) {
        if (paramInt2 == 2) {
          paramImageButton.setVisibility(4);
          paramImageButton.setClickable(false);
        } 
      } else {
        paramImageButton.setVisibility(0);
        paramImageButton.setClickable(true);
      } 
    } else {
      paramImageButton.setAlpha(CustomActionButton.WATER_MARK_ALPHA);
      paramImageButton.setClickable(false);
    } 
    paramInt2 = null.$SwitchMap$com$roadtrack$onstar$Enums$Services[paramServices.ordinal()];
    if (paramInt2 != 1) {
      if (paramInt2 != 2) {
        if (paramInt2 != 3) {
          if (paramInt2 == 4) {
            paramImageButton.setBackground(Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.nav_iconfavoff, 2131165570));
            paramImageButton.setOnClickListener(new View.OnClickListener() {
                  final MapsFragment this$0;
                  
                  public void onClick(View param1View) {
                    MapsFragment.this.favsFunction();
                  }
                });
          } 
        } else {
          paramImageButton.setBackground(Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.nav_iconpinoff, 2131165573));
          paramImageButton.setOnClickListener(new View.OnClickListener() {
                final MapsFragment this$0;
                
                public void onClick(View param1View) {
                  if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue()) {
                    MapsFragment.this.findMeFunction();
                  } else {
                    MapsFragment.this.ActionActived();
                  } 
                }
              });
          if (paramInt1 != 1) {
            if (paramInt1 != 2) {
              if (paramInt1 != 3) {
                if (paramInt1 != 4) {
                  if (paramInt1 == 5) {
                    this.progressBarFindMe = this.mapsFragmentView.getProgressBarGoToRoute();
                    this.imageViewFindMe = this.mapsFragmentView.getImageViewGoToRoute();
                  } 
                } else {
                  this.progressBarFindMe = this.mapsFragmentView.getProgressBarShare();
                  this.imageViewFindMe = this.mapsFragmentView.getImageViewShare();
                } 
              } else {
                this.progressBarFindMe = this.mapsFragmentView.getProgressBarFavs();
                this.imageViewFindMe = this.mapsFragmentView.getImageViewfavs();
              } 
            } else {
              this.progressBarFindMe = this.mapsFragmentView.getProgressBarSendRoute();
              this.imageViewFindMe = this.mapsFragmentView.getImageViewSendRoute();
            } 
          } else {
            this.progressBarFindMe = this.mapsFragmentView.getProgressBarFindMe();
            this.imageViewFindMe = this.mapsFragmentView.getImageViewFindMe();
          } 
        } 
      } else {
        paramImageButton.setBackground(Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.nav_iconroutepnd_disable, 2131165576));
        paramImageButton.setOnClickListener(new View.OnClickListener() {
              final MapsFragment this$0;
              
              public void onClick(View param1View) {
                MapsFragment.this.sendRouteFunction();
              }
            });
        if (paramInt1 != 1) {
          if (paramInt1 != 2) {
            if (paramInt1 != 3) {
              if (paramInt1 != 4) {
                if (paramInt1 == 5) {
                  this.progressBarSendRoute = this.mapsFragmentView.getProgressBarGoToRoute();
                  this.imageViewSendRoute = this.mapsFragmentView.getImageViewGoToRoute();
                } 
              } else {
                this.progressBarSendRoute = this.mapsFragmentView.getProgressBarShare();
                this.imageViewSendRoute = this.mapsFragmentView.getImageViewShare();
              } 
            } else {
              this.progressBarSendRoute = this.mapsFragmentView.getProgressBarFavs();
              this.imageViewSendRoute = this.mapsFragmentView.getImageViewfavs();
            } 
          } else {
            this.progressBarSendRoute = this.mapsFragmentView.getProgressBarSendRoute();
            this.imageViewSendRoute = this.mapsFragmentView.getImageViewSendRoute();
          } 
        } else {
          this.progressBarSendRoute = this.mapsFragmentView.getProgressBarFindMe();
          this.imageViewSendRoute = this.mapsFragmentView.getImageViewFindMe();
        } 
      } 
    } else {
      paramImageButton.setBackground(Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.nav_icondirections_disable, 2131165568));
      paramImageButton.setOnClickListener(new View.OnClickListener() {
            final MapsFragment this$0;
            
            public void onClick(View param1View) {
              MapsFragment.this.gotoRouteFunction();
            }
          });
    } 
  }
  
  private void setActionBarAndSpinner() {
    this.spinnerVehicle.setOnTouchListener(new View.OnTouchListener() {
          final MapsFragment this$0;
          
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            boolean bool;
            if (MainActivity.onOpenDoors.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onFindMe.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onPID.booleanValue() || MapsFragment.onSendRoute || MainActivity.onDTC.booleanValue()) {
              bool = true;
            } else {
              bool = false;
            } 
            if (param1MotionEvent.getAction() == 1 && bool)
              try {
                Dialog dialog = Utilities.simpleDialog((Context)MapsFragment.activity, null, MapsFragment.global_popup_lbl_aviso_1, MapsFragment.global_popup_lbl_accionencurso_1, true, MapsFragment.global_popup_btn_ok_1, false, MapsFragment.global_popup_btn_no_1, 20.0F, 20.0F);
                MapsFragment.access$5702(MapsFragment.this, (Button)dialog.findViewById(2131296343));
                Button button = MapsFragment.this.buttonOk;
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    final Dialog val$dialog;
                    
                    public void onClick(View param2View) {
                      dialog.dismiss();
                    }
                  };
                super(this, dialog);
                button.setOnClickListener(onClickListener);
                dialog.show();
              } catch (Exception exception) {
                Utilities.escribeArchivo(MapsFragment.TAG, "Error: dialogBackALert", exception.getMessage());
              }  
            return bool;
          }
        });
  }
  
  private void setBottomContainer() {
    this.mapsFragmentView.getBottomContainer();
  }
  
  private void setButtonToggleLeftNavigationDrawer() {
    this.buttonToggleLeftNavigationDrawer = (Button)this.viewGroupTomTomSearchBar.findViewById(2131296633);
    this.buttonToggleLeftNavigationDrawer.setOnClickListener(new View.OnClickListener() {
          final MapsFragment this$0;
          
          public void onClick(View param1View) {
            MapsFragment.this.openLeftNavigationDrawer();
          }
        });
  }
  
  private void setDrawerLayout() {
    this.drawerLayout = this.mapsFragmentView.getDrawerLayout();
  }
  
  private void setEditTextSearch() {
    this.expListView = this.mapsFragmentView.getExpandableListViewSearch();
    this.expListView.setGroupIndicator(null);
    this.expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(this) {
          public boolean onGroupClick(ExpandableListView param1ExpandableListView, View param1View, int param1Int, long param1Long) {
            return false;
          }
        });
    this.expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
          final MapsFragment this$0;
          
          public void onGroupCollapse(int param1Int) {
            MapsFragment.this.viewGroupTomTomSearchBar.setVisibility(0);
            if (MapsFragment.this.mDelayedTimer == null)
              return; 
            MapsFragment.this.mDelayedTimer.stopUpdates();
            throw null;
          }
        });
    this.expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
          final MapsFragment this$0;
          
          public void onGroupExpand(int param1Int) {
            if (MapsFragment.this.mDelayedTimer == null)
              return; 
            MapsFragment.this.mDelayedTimer.startUpdates();
            throw null;
          }
        });
    this.expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
          final MapsFragment this$0;
          
          public boolean onChildClick(ExpandableListView param1ExpandableListView, View param1View, int param1Int1, int param1Int2, long param1Long) {
            MapsFragment.this.mapsFragmentView.getFloating().collapse();
            MapsFragment mapsFragment = MapsFragment.this;
            boolean bool = false;
            MapsFragment.access$2802(mapsFragment, false);
            MapsFragment.access$2902(MapsFragment.this, (TextView)param1View.findViewById(2131296683));
            MapsFragment.access$3002(MapsFragment.this, (TextView)param1View.findViewById(2131296862));
            MapsFragment.access$3102(MapsFragment.this, (TextView)param1View.findViewById(2131296363));
            MapsFragment.access$3202(MapsFragment.this, (TextView)param1View.findViewById(2131296921));
            String str2 = MapsFragment.this.nm.getText().toString();
            String str1 = str2;
            if (str2 != null) {
              str1 = str2;
              if (str2.contains(", ."))
                str1 = Utilities.getStringFromConfigList((Context)MapsFragment.this.getActivity(), MapsFragment.this.stringsResourcesVO.navigation_main_lbl_sindireccion, 2131690170); 
            } 
            double d2 = Double.parseDouble(MapsFragment.this.lat.getText().toString());
            double d1 = Double.parseDouble(MapsFragment.this.lon.getText().toString());
            if (d2 != 0.0D) {
              MapsFragment mapsFragment2 = MapsFragment.this;
              bool = true;
              MapsFragment.access$3402(mapsFragment2, true);
              MapsFragment.this.expListView.collapseGroup(param1Int1);
              mapsFragment2 = MapsFragment.this;
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("");
              stringBuilder.append(d2);
              String str = stringBuilder.toString();
              stringBuilder = new StringBuilder();
              stringBuilder.append("");
              stringBuilder.append(d1);
              mapsFragment2.fakeGoogleSearchResult("REFERENCE", str, stringBuilder.toString(), str1, MapsFragment.this.addr.getText().toString(), true, false);
              GlobalMembers.googleSearchTitleToFavorite = str1;
              MapsFragment mapsFragment1 = MapsFragment.this;
              MapsFragment.access$3702(mapsFragment1, mapsFragment1.latLngToMicroDegrees(d2, d1));
              MapsFragment.this.controller.setMarkers(d2, d1, MapsFragment.this.typeFloating, 0, MapsFragment.activity);
              MapsFragment.this.setMarkersMap(d2, d1);
            } 
            return bool;
          }
        });
    this.expListView.setOnScrollListener(new AbsListView.OnScrollListener(this) {
          public void onScroll(AbsListView param1AbsListView, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onScrollStateChanged(AbsListView param1AbsListView, int param1Int) {}
        });
    this.googleSearchResults_interface = new GoogleSearchResults_Interface() {
        final MapsFragment this$0;
        
        public void onSuccess(ArrayList<HashMap<String, String>> param1ArrayList, ExpandableResultsAdapter param1ExpandableResultsAdapter) {
          MapsFragment.access$4102(MapsFragment.this, param1ArrayList);
          MapsFragment.access$4202(MapsFragment.this, param1ExpandableResultsAdapter);
          if (MapsFragment.this.mGoogleSearchResults != null && MapsFragment.this.mGoogleSearchResults.size() == 1)
            MapsFragment.this.showMarquerOnResult(param1ArrayList); 
        }
      };
    this.rtApp = (onstarApplication)getActivity().getApplicationContext();
    this.tf = onstarApplication.getTypeface((Context)getActivity(), this.rtApp.fontPathLouisRegular);
    this.googSearch = this.mapsFragmentView.getEditTextGoogleSearch();
    this.googSearch.setTypeface(this.tf);
    this.googSearch.setOnClickListener(new View.OnClickListener() {
          final MapsFragment this$0;
          
          public void onClick(View param1View) {
            MapsFragment.this.resetListAndMap();
            MapsFragment.access$3702(MapsFragment.this, null);
            MapsFragment.access$4302(MapsFragment.this, false);
            MapsFragment.access$3402(MapsFragment.this, false);
            MapsFragment.access$2802(MapsFragment.this, false);
            if (MapsFragment.saveEdittextsearch != null && MapsFragment.saveEdittextsearch.size() > 0) {
              ArrayAdapter arrayAdapter = new ArrayAdapter((Context)MapsFragment.activity, 2131427485, 16908308, MapsFragment.saveEdittextsearch);
              MapsFragment.lvSavedEditSearch.setAdapter((ListAdapter)arrayAdapter);
              if (MapsFragment.lvSavedEditSearch.getVisibility() == 8)
                MapsFragment.lvSavedEditSearch.setVisibility(0); 
            } 
          }
        });
    this.googSearch.setOnKeyListener(new View.OnKeyListener() {
          final MapsFragment this$0;
          
          public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
            MapsFragment.this.resetListAndMap();
            MapsFragment.access$4302(MapsFragment.this, false);
            MapsFragment.access$3402(MapsFragment.this, false);
            MapsFragment.access$3702(MapsFragment.this, null);
            MapsFragment.access$2802(MapsFragment.this, false);
            MapsFragment mapsFragment = MapsFragment.this;
            MapsFragment.access$4502(mapsFragment, Boolean.valueOf(NetUtilities.validateNetwork(mapsFragment.getContext(), false)));
            String str = Utilities.getStringFromConfigList((Context)MapsFragment.this.getActivity(), MapsFragment.this.stringsResourcesVO.global_lbl_fallaredbusqueda, 2131689921);
            MapsFragment.access$3902(MapsFragment.this, "");
            if (param1Int == 66 && param1KeyEvent.getAction() == 0) {
              if (MapsFragment.this.googSearch.getText().toString().equals("") || MapsFragment.this.googSearch.getText().toString().equals(null)) {
                try {
                  String str1 = Utilities.getStringFromConfigList((Context)MapsFragment.this.getActivity(), MapsFragment.this.stringsResourcesVO.navigation_lbl_busquedaalertamensaje_1, 2131690147);
                  DialogEmpty dialogEmpty = new DialogEmpty();
                  this(MapsFragment.activity, str1);
                  dialogEmpty.show();
                  DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() {
                      final DialogEmpty val$dc;
                      
                      public void onDismiss(DialogInterface param2DialogInterface) {
                        dc.dismiss();
                      }
                    };
                  super(this, dialogEmpty);
                  dialogEmpty.setOnDismissListener(onDismissListener);
                } catch (Exception exception) {
                  Utilities.escribeArchivo(MapsFragment.TAG, "Error: onPostExecute", exception.getMessage());
                } 
                return false;
              } 
              if (MapsFragment.this.controlViews == null) {
                if (MapsFragment.this.isThereINET.booleanValue()) {
                  try {
                    Location location = new Location();
                    this(URLEncoder.encode(MapsFragment.this.googSearch.getText().toString(), "utf-8"));
                    MapsFragment.access$4802(location);
                    MapsFragment.mCurrentLocation.setLatitude(MapsFragment.this.latLngGlobal.latitude);
                    MapsFragment.mCurrentLocation.setLongitude(MapsFragment.this.latLngGlobal.longitude);
                    MapsFragment mapsFragment1 = MapsFragment.this;
                    SearchCompleteTask searchCompleteTask = new SearchCompleteTask();
                    this(MapsFragment.activity, MapsFragment.this.googleSearchResults_interface, MapsFragment.this.mMap, "", MapsFragment.this.expListView, MapsFragment.this.controller);
                    MapsFragment.access$4702(mapsFragment1, searchCompleteTask);
                    MapsFragment.this.controlViews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Location[] { MapsFragment.access$4800() });
                  } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                  } 
                } else {
                  Toast.makeText((Context)MapsFragment.activity, (CharSequence)unsupportedEncodingException, 1).show();
                } 
              } else if (MapsFragment.this.isThereINET.booleanValue()) {
                try {
                  Location location = new Location();
                  this(URLEncoder.encode(MapsFragment.this.googSearch.getText().toString(), "utf-8"));
                  MapsFragment.access$4802(location);
                  MapsFragment.mCurrentLocation.setLatitude(MapsFragment.this.latLngGlobal.latitude);
                  MapsFragment.mCurrentLocation.setLongitude(MapsFragment.this.latLngGlobal.longitude);
                  MapsFragment mapsFragment1 = MapsFragment.this;
                  SearchCompleteTask searchCompleteTask = new SearchCompleteTask();
                  this(MapsFragment.activity, MapsFragment.this.googleSearchResults_interface, MapsFragment.this.mMap, "", MapsFragment.this.expListView, MapsFragment.this.controller);
                  MapsFragment.access$4702(mapsFragment1, searchCompleteTask);
                  MapsFragment.this.controlViews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Location[] { MapsFragment.access$4800() });
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                  unsupportedEncodingException.printStackTrace();
                } 
              } else {
                Toast.makeText((Context)MapsFragment.activity, (CharSequence)unsupportedEncodingException, 1).show();
              } 
              str = MapsFragment.this.googSearch.getText().toString();
              if (str != null && !str.equals("") && !MapsFragment.this.existInsavedList(str))
                MapsFragment.this.saveTempSearch(str); 
              ((InputMethodManager)MapsFragment.activity.getSystemService("input_method")).hideSoftInputFromWindow(MapsFragment.this.googSearch.getApplicationWindowToken(), 2);
              if (MapsFragment.lvSavedEditSearch.getVisibility() == 0)
                MapsFragment.lvSavedEditSearch.setVisibility(8); 
              if (MapsFragment.this.expListView != null) {
                if (MapsFragment.this.expListView.getVisibility() == 8)
                  MapsFragment.this.expListView.setVisibility(0); 
                MapsFragment.access$4702(MapsFragment.this, null);
              } 
              return true;
            } 
            return false;
          }
        });
    this.googSearch.requestFocus();
    lvSavedEditSearch = this.mapsFragmentView.getListViewSavedItems();
    lvSavedEditSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          final MapsFragment this$0;
          
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            Object object = param1AdapterView.getItemAtPosition(param1Int);
            MapsFragment.this.googSearch.setText(object.toString());
            MapsFragment.lvSavedEditSearch.setVisibility(8);
            MapsFragment.access$2802(MapsFragment.this, false);
          }
        });
  }
  
  private void setExpandableListViewData() {
    this.fullList = this.mapsFragmentView.getExpandableListViewData();
    this.fullList.setOnChildClickListener(new ExpandableListView.OnChildClickListener(this) {
          public boolean onChildClick(ExpandableListView param1ExpandableListView, View param1View, int param1Int1, int param1Int2, long param1Long) {
            return true;
          }
        });
  }
  
  private void setFavouriteMarkerIfExists() {
    LatLng latLng;
    new MarkerOrFavoritePointVO(0, 0, "", "");
    MarkerOrFavoritePointVO markerOrFavoritePointVO = GlobalMembers.markerOrFavoritePointVO;
    GlobalMembers.isMarker = true;
    Object object = GlobalMembers.objSendFavoritesMessage;
    if (object != null && this.isFromFavouritesActivity) {
      this.myLocationPressed = false;
      FavoritesHistoryVO favoritesHistoryVO = (FavoritesHistoryVO)object;
      String[] arrayOfString = favoritesHistoryVO.getLatlng().split(",");
      object = favoritesHistoryVO.getAddress();
      String str2 = favoritesHistoryVO.getName();
      String str1 = this.destinationName;
      if (str1 == null) {
        this.destinationName = str2;
        GlobalMembers.googleSearchTitleToFavorite = str2;
      } else if (str1.equalsIgnoreCase(str2)) {
        this.destinationName = str2;
        GlobalMembers.googleSearchTitleToFavorite = str2;
      } 
      this.destinationAddress = (String)object;
      if (arrayOfString != null && arrayOfString.length == 2) {
        resetListAndMap();
        int j = this.TypeMarker;
        int i = 2131165645;
        if (j == 1) {
          if (Utilities.isAndinos().booleanValue()) {
            Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.pin_ubication_medium_andinos, 2131165645);
          } else {
            Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.pin_ubication_medium, 2131165644);
            i = 2131165644;
          } 
        } else if (Utilities.isAndinos().booleanValue()) {
          Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.pin_favorite_andinos, 2131165643);
          i = 2131165643;
        } else {
          Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.pin_favorite, 2131165642);
          i = 2131165642;
        } 
        latLng = new LatLng(Double.parseDouble(arrayOfString[0]), Double.parseDouble(arrayOfString[1]));
        object = BitmapDescriptorFactory.fromResource(i);
        GoogleMap googleMap = this.mMap;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("");
        markerOptions.icon((BitmapDescriptor)object);
        this.mCurrLocationMarker = googleMap.addMarker(markerOptions);
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0F));
        this.currentMarkers.clear();
        object = new PointInfo(this.destinationAddress, this.destinationName, latLng, null);
        this.currentMarkers.add(object);
      } 
    } else if (GlobalMembers.isFromShareFavActivity && ((MarkerOrFavoritePointVO)latLng).latitudeMicroDegrees != 0 && ((MarkerOrFavoritePointVO)latLng).longitudeMicroDegrees != 0) {
      this.mMap.clear();
    } 
  }
  
  private void setFrameContainer() {
    this.frame_container = this.mapsFragmentView.getFrameContainer();
  }
  
  private void setImageButtonCenterLocation() {
    this.imageButtonCenterLocation = this.mapsFragmentView.getImageButtonCenterLocation();
    this.imageButtonCenterLocation.setOnClickListener(new View.OnClickListener() {
          final MapsFragment this$0;
          
          public void onClick(View param1View) {
            if (!((LocationManager)MapsFragment.activity.getSystemService("location")).isProviderEnabled("gps")) {
              MapsFragment.this.dialogNoGPSActive();
            } else {
              MapsFragment mapsFragment = MapsFragment.this;
              mapsFragment.myLocationPressed = true;
              mapsFragment.resetMapScreen();
            } 
          }
        });
  }
  
  private void setImageViewFindMe() {
    this.mapsFragmentView.getShareImageViewFindMe();
  }
  
  private void setImageViewSplashScreen() {
    this.imageViewSplashScreen = this.mapsFragmentView.getSplashScreen();
  }
  
  private void setLandscapeAndPortraitConfiguration() {
    int i = Utilities.getCurrentScreenOrientation(activity);
    if (i == 2) {
      if (GlobalMembers.activeTutorial) {
        this.idTutorial.setVisibility(8);
        ShowViewElement showViewElement = this.show;
        if (showViewElement != null)
          showViewElement.removeView(this); 
      } 
    } else if (i == 1 && GlobalMembers.activeTutorial) {
      this.idTutorial.setVisibility(0);
    } 
  }
  
  private void setLayoutVehicleHeader() {
    this.mapsFragmentView.getRelativeLayoutVehiclesHeader();
  }
  
  private void setLeftNavigationDrawer(Bundle paramBundle) {
    Drawable drawable1;
    Drawable drawable2;
    Drawable drawable3;
    Drawable drawable4;
    if (Utilities.isAndinos().booleanValue()) {
      getResources().getStringArray(2130837510);
      this.leftDrawerMenuIcons = getResources().obtainTypedArray(2130837509);
      this.leftDrawerListView = (ListView)this.view.findViewById(2131296795);
      drawable2 = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.btn_menu_historial, this.leftDrawerMenuIcons.getResourceId(0, -1));
      drawable4 = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.iconfavorito_onstar_left, this.leftDrawerMenuIcons.getResourceId(1, -1));
      drawable3 = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.btn_menu_asistencia, this.leftDrawerMenuIcons.getResourceId(2, -1));
      drawable1 = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.actions_dialog_emergency, this.leftDrawerMenuIcons.getResourceId(3, -1));
    } else {
      getResources().getStringArray(2130837510);
      this.leftDrawerMenuIcons = getResources().obtainTypedArray(2130837508);
      this.leftDrawerListView = (ListView)this.view.findViewById(2131296795);
      drawable2 = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.btn_menu_historial, this.leftDrawerMenuIcons.getResourceId(0, -1));
      drawable4 = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.iconfavorito_onstar_left, this.leftDrawerMenuIcons.getResourceId(1, -1));
      drawable3 = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.btn_menu_asistencia, this.leftDrawerMenuIcons.getResourceId(2, -1));
      drawable1 = Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.btt_sos_withe_menu_left, this.leftDrawerMenuIcons.getResourceId(3, -1));
    } 
    String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_latmenu_lbl_historialdenav_1, 2131690146);
    String str4 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_fav_1, 2131689923);
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_emergencia_1, 2131689919);
    this.leftNavDrawerItems = new ArrayList<TomTomMapLeftNavDrawerItem>();
    this.leftNavDrawerItems.add(new TomTomMapLeftNavDrawerItem(str2, drawable2));
    this.leftNavDrawerItems.add(new TomTomMapLeftNavDrawerItem(str4, drawable4));
    this.leftNavDrawerItems.add(new TomTomMapLeftNavDrawerItem(str1, drawable3));
    this.leftNavDrawerItems.add(new TomTomMapLeftNavDrawerItem(str3, drawable1));
    this.leftNavDrawerItems.add(new TomTomMapLeftNavDrawerItem(true));
    this.leftDrawerMenuIcons.recycle();
    this.leftDrawerListView.setOnItemClickListener(new LeftSlideMenuClickListener(null));
    this.leftDrawerListAdapter = new TomTomMapLeftNavDrawerListAdapter((Context)this.rtApp, this.leftNavDrawerItems);
    this.leftDrawerListView.setAdapter((ListAdapter)this.leftDrawerListAdapter);
    this.leftActionBarDrawerToggle = new ActionBarDrawerToggle(activity, this.drawerLayout, 2131165484, 2131689666, 2131689666) {
        final MapsFragment this$0;
        
        public void onDrawerClosed(View param1View) {
          MapsFragment.activity.invalidateOptionsMenu();
        }
        
        public void onDrawerOpened(View param1View) {
          MapsFragment.activity.invalidateOptionsMenu();
          MapsFragment.this.setVehicleSpinnerListener();
          MapsFragment.this.setActionBarAndSpinner();
        }
        
        public void onDrawerSlide(View param1View, float param1Float) {
          super.onDrawerSlide(param1View, param1Float);
        }
      };
    this.drawerLayout.setDrawerListener((DrawerLayout.DrawerListener)this.leftActionBarDrawerToggle);
  }
  
  private void setLinearLayoutBottom() {
    this.linearLayoutBottom = this.mapsFragmentView.getLinearLayoutBottomMenu();
  }
  
  private void setLinearLayoutRoutenavheader() {
    this.mapsFragmentView.getLinearLayoutRoutenavheader();
  }
  
  private void setListViewVehicles() {
    this.vehicleListShare = this.mapsFragmentView.getListViewVehicles();
    this.vehicleListShare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          final MapsFragment this$0;
          
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            for (param1Int = 0; param1Int < MapsFragment.this.rtApp.getmDeviceUserList().size(); param1Int++) {
              MapsFragment mapsFragment = MapsFragment.this;
              View view = mapsFragment.getViewByPosition(param1Int, mapsFragment.vehicleListShare);
              MapsFragment.access$802(MapsFragment.this, (TextView)view.findViewById(2131296491));
              MapsFragment.access$902(MapsFragment.this, (TextView)view.findViewById(2131297145));
              MapsFragment.access$1002(MapsFragment.this, (TextView)view.findViewById(2131296367));
              MapsFragment.this.dateShare1.setText("");
              MapsFragment.this.tvDateInfo1.setText("");
              MapsFragment.this.addresslistshare1.setText("");
            } 
          }
        });
  }
  
  private void setMapType() {
    // Byte code:
    //   0: ldc_w 'PMM GoogleMap'
    //   3: ldc_w 'ActivityMapViewerG'
    //   6: ldc_w 'setMapType'
    //   9: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   12: aload_0
    //   13: getfield mapType : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   16: astore_1
    //   17: aload_1
    //   18: ifnull -> 463
    //   21: aload_1
    //   22: getstatic com/roadtrack/onstar/Enums$navigationProcess.External : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   25: invokevirtual equals : (Ljava/lang/Object;)Z
    //   28: ifeq -> 463
    //   31: getstatic com/roadtrack/onstar/BO/GlobalMembers.activeTutorial : Z
    //   34: ifeq -> 65
    //   37: aload_0
    //   38: getfield idTutorial : Landroid/widget/LinearLayout;
    //   41: bipush #8
    //   43: invokevirtual setVisibility : (I)V
    //   46: aload_0
    //   47: iconst_0
    //   48: putfield isTutorialAvailable : Z
    //   51: aload_0
    //   52: getfield show : Lcom/roadtrack/onstar/viewTutorial/ShowViewElement;
    //   55: astore_1
    //   56: aload_1
    //   57: ifnull -> 65
    //   60: aload_1
    //   61: aload_0
    //   62: invokevirtual removeView : (Landroidx/fragment/app/Fragment;)V
    //   65: new java/lang/String
    //   68: dup
    //   69: invokespecial <init> : ()V
    //   72: astore_2
    //   73: aload_0
    //   74: invokevirtual getActivity : ()Landroidx/fragment/app/FragmentActivity;
    //   77: aload_0
    //   78: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   81: getfield global_lbl_accionlocalizame_1 : Ljava/lang/String;
    //   84: ldc_w 2131689877
    //   87: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   90: astore_3
    //   91: aload_0
    //   92: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   95: astore #4
    //   97: aload_2
    //   98: astore_1
    //   99: aload #4
    //   101: ifnull -> 148
    //   104: aload_2
    //   105: astore_1
    //   106: aload #4
    //   108: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   111: ifnull -> 148
    //   114: aload_0
    //   115: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   118: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   121: ldc_w '1'
    //   124: invokevirtual equals : (Ljava/lang/Object;)Z
    //   127: ifne -> 146
    //   130: aload_2
    //   131: astore_1
    //   132: aload_0
    //   133: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   136: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   139: aload_3
    //   140: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   143: ifeq -> 148
    //   146: aload_3
    //   147: astore_1
    //   148: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   151: astore #4
    //   153: aload_1
    //   154: astore_2
    //   155: aload #4
    //   157: ifnull -> 196
    //   160: aload_1
    //   161: astore_2
    //   162: aload #4
    //   164: invokevirtual size : ()I
    //   167: ifle -> 196
    //   170: aload_1
    //   171: astore_2
    //   172: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   175: iconst_0
    //   176: invokevirtual get : (I)Ljava/lang/Object;
    //   179: ifnull -> 196
    //   182: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   185: iconst_0
    //   186: invokevirtual get : (I)Ljava/lang/Object;
    //   189: checkcast com/roadtrack/onstar/VO/PushAlertsVO
    //   192: invokevirtual getAlert : ()Ljava/lang/String;
    //   195: astore_2
    //   196: aload_0
    //   197: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   200: astore #4
    //   202: aload_2
    //   203: astore_1
    //   204: aload #4
    //   206: ifnull -> 242
    //   209: aload_2
    //   210: astore_1
    //   211: aload #4
    //   213: invokevirtual getALERT_CODE_ID : ()Ljava/lang/String;
    //   216: ifnull -> 242
    //   219: aload_2
    //   220: astore_1
    //   221: aload_0
    //   222: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   225: invokevirtual getALERT_CODE_ID : ()Ljava/lang/String;
    //   228: invokevirtual isEmpty : ()Z
    //   231: ifne -> 242
    //   234: aload_0
    //   235: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   238: invokevirtual getALERT_CODE_ID : ()Ljava/lang/String;
    //   241: astore_1
    //   242: aload_0
    //   243: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   246: astore_2
    //   247: aload_2
    //   248: ifnull -> 274
    //   251: aload_2
    //   252: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   255: ifnull -> 274
    //   258: aload_0
    //   259: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   262: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   265: ldc_w '1'
    //   268: invokevirtual equals : (Ljava/lang/Object;)Z
    //   271: ifne -> 288
    //   274: aload_0
    //   275: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   278: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   281: aload_3
    //   282: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   285: ifeq -> 290
    //   288: aload_3
    //   289: astore_1
    //   290: aload_0
    //   291: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   294: invokevirtual getKEY_EXTERNAL_LAT : ()Ljava/lang/String;
    //   297: astore #6
    //   299: aload_0
    //   300: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   303: invokevirtual getKEY_EXTERNAL_LNG : ()Ljava/lang/String;
    //   306: astore #5
    //   308: aload_0
    //   309: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   312: invokevirtual getLASTKNOWDATE : ()Ljava/lang/String;
    //   315: astore_2
    //   316: aload_0
    //   317: getfield params : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   320: invokevirtual getKEY_GPSSTATUS : ()Ljava/lang/String;
    //   323: astore #4
    //   325: new java/lang/StringBuilder
    //   328: dup
    //   329: invokespecial <init> : ()V
    //   332: astore #7
    //   334: aload #7
    //   336: aload #6
    //   338: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: pop
    //   342: aload #7
    //   344: ldc_w '_'
    //   347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: pop
    //   351: aload #7
    //   353: aload #5
    //   355: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: pop
    //   359: aload #7
    //   361: invokevirtual toString : ()Ljava/lang/String;
    //   364: astore #7
    //   366: aload_0
    //   367: aload #6
    //   369: invokestatic parseDouble : (Ljava/lang/String;)D
    //   372: aload #5
    //   374: invokestatic parseDouble : (Ljava/lang/String;)D
    //   377: invokespecial latLngToMicroDegrees : (DD)[I
    //   380: pop
    //   381: aload_0
    //   382: aload_0
    //   383: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   386: getstatic com/roadtrack/onstar/googleMaps/MapsFragment.TAG : Ljava/lang/String;
    //   389: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   392: invokevirtual getName : ()Ljava/lang/String;
    //   395: aload_1
    //   396: aload_2
    //   397: aload #4
    //   399: aload #7
    //   401: aload #6
    //   403: invokestatic parseDouble : (Ljava/lang/String;)D
    //   406: aload #5
    //   408: invokestatic parseDouble : (Ljava/lang/String;)D
    //   411: invokespecial showFindMeBubble : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DD)V
    //   414: aload_0
    //   415: invokespecial disableElementsOnFindMe : ()V
    //   418: aload_1
    //   419: aload_3
    //   420: invokevirtual equals : (Ljava/lang/Object;)Z
    //   423: ifeq -> 708
    //   426: getstatic com/roadtrack/onstar/BO/GlobalMembers.activeTutorial : Z
    //   429: ifeq -> 708
    //   432: aload_0
    //   433: getfield idTutorial : Landroid/widget/LinearLayout;
    //   436: bipush #8
    //   438: invokevirtual setVisibility : (I)V
    //   441: aload_0
    //   442: iconst_0
    //   443: putfield isTutorialAvailable : Z
    //   446: aload_0
    //   447: getfield show : Lcom/roadtrack/onstar/viewTutorial/ShowViewElement;
    //   450: astore_1
    //   451: aload_1
    //   452: ifnull -> 708
    //   455: aload_1
    //   456: aload_0
    //   457: invokevirtual removeView : (Landroidx/fragment/app/Fragment;)V
    //   460: goto -> 708
    //   463: aload_0
    //   464: getfield mapType : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   467: getstatic com/roadtrack/onstar/Enums$navigationProcess.Navigation : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   470: invokevirtual equals : (Ljava/lang/Object;)Z
    //   473: ifeq -> 527
    //   476: aload_0
    //   477: iconst_1
    //   478: putfield myLocationPressed : Z
    //   481: aload_0
    //   482: invokevirtual getContext : ()Landroid/content/Context;
    //   485: ldc_w 'android.permission.ACCESS_FINE_LOCATION'
    //   488: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
    //   491: ifeq -> 508
    //   494: aload_0
    //   495: invokevirtual getContext : ()Landroid/content/Context;
    //   498: ldc_w 'android.permission.ACCESS_COARSE_LOCATION'
    //   501: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
    //   504: ifeq -> 508
    //   507: return
    //   508: aload_0
    //   509: getfield mMap : Lcom/google/android/gms/maps/GoogleMap;
    //   512: iconst_1
    //   513: invokevirtual setMyLocationEnabled : (Z)V
    //   516: aload_0
    //   517: invokespecial setLandscapeAndPortraitConfiguration : ()V
    //   520: aload_0
    //   521: invokespecial setFavouriteMarkerIfExists : ()V
    //   524: goto -> 708
    //   527: aload_0
    //   528: getfield mapType : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   531: getstatic com/roadtrack/onstar/Enums$navigationProcess.FollowMe : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   534: invokevirtual equals : (Ljava/lang/Object;)Z
    //   537: ifeq -> 630
    //   540: aload_0
    //   541: iconst_0
    //   542: putfield myLocationPressed : Z
    //   545: aload_0
    //   546: iconst_1
    //   547: putfield isTutorialAvailable : Z
    //   550: aload_0
    //   551: iconst_1
    //   552: putfield pressTuto : Z
    //   555: aload_0
    //   556: getfield idTutorial : Landroid/widget/LinearLayout;
    //   559: aload_0
    //   560: getfield clickTutorial : Landroid/view/View$OnClickListener;
    //   563: invokevirtual setOnClickListener : (Landroid/view/View$OnClickListener;)V
    //   566: aload_0
    //   567: invokevirtual getContext : ()Landroid/content/Context;
    //   570: ldc_w 'android.permission.ACCESS_FINE_LOCATION'
    //   573: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
    //   576: ifeq -> 593
    //   579: aload_0
    //   580: invokevirtual getContext : ()Landroid/content/Context;
    //   583: ldc_w 'android.permission.ACCESS_COARSE_LOCATION'
    //   586: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
    //   589: ifeq -> 593
    //   592: return
    //   593: aload_0
    //   594: getfield mMap : Lcom/google/android/gms/maps/GoogleMap;
    //   597: iconst_0
    //   598: invokevirtual setMyLocationEnabled : (Z)V
    //   601: getstatic com/roadtrack/onstar/BO/GlobalMembers.followMeArrayListPoints : Ljava/util/ArrayList;
    //   604: astore_1
    //   605: aload_1
    //   606: ifnull -> 623
    //   609: aload_1
    //   610: invokevirtual size : ()I
    //   613: ifle -> 623
    //   616: aload_0
    //   617: getstatic com/roadtrack/onstar/BO/GlobalMembers.followMeArrayListPoints : Ljava/util/ArrayList;
    //   620: invokespecial showFollowMeBubble : (Ljava/util/ArrayList;)V
    //   623: aload_0
    //   624: invokespecial disableAllElements : ()V
    //   627: goto -> 708
    //   630: aload_0
    //   631: getfield mapType : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   634: getstatic com/roadtrack/onstar/Enums$navigationProcess.Mixed : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   637: invokevirtual equals : (Ljava/lang/Object;)Z
    //   640: ifeq -> 708
    //   643: aload_0
    //   644: iconst_0
    //   645: putfield myLocationPressed : Z
    //   648: getstatic com/roadtrack/onstar/BO/GlobalMembers.activeTutorial : Z
    //   651: ifeq -> 682
    //   654: aload_0
    //   655: getfield idTutorial : Landroid/widget/LinearLayout;
    //   658: bipush #8
    //   660: invokevirtual setVisibility : (I)V
    //   663: aload_0
    //   664: iconst_0
    //   665: putfield isTutorialAvailable : Z
    //   668: aload_0
    //   669: getfield show : Lcom/roadtrack/onstar/viewTutorial/ShowViewElement;
    //   672: astore_1
    //   673: aload_1
    //   674: ifnull -> 682
    //   677: aload_1
    //   678: aload_0
    //   679: invokevirtual removeView : (Landroidx/fragment/app/Fragment;)V
    //   682: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   685: astore_1
    //   686: aload_1
    //   687: ifnull -> 704
    //   690: aload_1
    //   691: invokevirtual size : ()I
    //   694: ifle -> 704
    //   697: aload_0
    //   698: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   701: invokespecial showMixedBubble : (Ljava/util/ArrayList;)V
    //   704: aload_0
    //   705: invokespecial disableAllElements : ()V
    //   708: aload_0
    //   709: getfield imageViewSplashScreen : Landroid/widget/ImageView;
    //   712: astore_1
    //   713: aload_1
    //   714: ifnull -> 723
    //   717: aload_1
    //   718: bipush #8
    //   720: invokevirtual setVisibility : (I)V
    //   723: return
  }
  
  private void setProgressBarShareHeader() {
    this.mapsFragmentView.getProgressBarShareHeader();
  }
  
  private void setProgressBarVehicles() {
    this.mapsFragmentView.getProgessBarVehicles();
  }
  
  private void setRelativeFloatingMenu() {
    this.rlContainerMenu = this.mapsFragmentView.getContainerFloatingMenu();
  }
  
  private void setShareLocationBottomMenu() {
    this.mapsFragmentView.getShareLocationBottomList();
  }
  
  private void setShareLocationTopMenu() {
    this.mapsFragmentView.getShareLocationTopMenu();
  }
  
  private void setVehicleSelected(Spinner paramSpinner) {
    try {
      DBFunctions dBFunctions = new DBFunctions();
      this(getContext());
      UserDevicesVO userDevicesVO1 = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
      UserDevicesVO userDevicesVO2 = this.rtApp.getmDeviceUserList().get(paramSpinner.getSelectedItemPosition());
      dBFunctions.addVehicleSelected(getContext(), dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser(), userDevicesVO2);
      if (!userDevicesVO2.equals(userDevicesVO1)) {
        Utilities.updateVehicleSelected(getContext(), dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser(), userDevicesVO2);
        paramSpinner.setSelection(Utilities.getLastKnownVehicleSelected(getContext(), dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser(), this.rtApp));
        UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
        boolean bool = this.dbFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), userDevicesVO.getDeviceId(), "", true);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Mostrando1: ");
        stringBuilder.append(bool);
        Utilities.escribeArchivo(str, "THEFT", stringBuilder.toString());
        if (bool) {
          MainActivity.Showbanner = true;
        } else {
          Utilities.isUUx(this.rtApp);
        } 
        RemoteDiagnosticActivity.isdialog_action_process_frame_visible = false;
        MainActivity.showRenewalDialog = true;
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: setVehicleSelected", exception.getMessage());
    } 
  }
  
  private void setVehicleSpinnerListener() {
    TomTomMapLeftNavDrawerListAdapter tomTomMapLeftNavDrawerListAdapter = this.leftDrawerListAdapter;
    if (tomTomMapLeftNavDrawerListAdapter != null) {
      this.spinnerVehicle = tomTomMapLeftNavDrawerListAdapter.getSpinner();
      Spinner spinner = this.spinnerVehicle;
      if (spinner != null)
        fillVehicleList(spinner, getContext()); 
    } 
  }
  
  private void setViewGroupTomTomSearchBar() {
    this.viewGroupTomTomSearchBar = this.mapsFragmentView.getViewGroupTomTomSearchBar();
  }
  
  private void setViewGroupTopInstructions() {
    this.mapsFragmentView.getViewGroupTopInstructions();
  }
  
  private void showFindMeBubble(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, double paramDouble1, double paramDouble2) {
    String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_lbl_localizame_direccion_1, 2131690157);
    String str4 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_localizameultimaposicion_1, 2131689926);
    String str2 = getAddressFromLatLong(new LatLng(paramDouble1, paramDouble2));
    String str5 = TAG;
    StringBuilder stringBuilder4 = new StringBuilder();
    stringBuilder4.append("Status: ");
    stringBuilder4.append(paramString4);
    Utilities.escribeArchivo(str5, "Bubble onCityAndStreet", stringBuilder4.toString());
    BubbleDrawable.onDestroy();
    this.bubbleDrawable = new BubbleDrawable(activity, 1);
    this.bubbleDrawable.setCornerRadius(20.0F);
    this.bubbleDrawable.setPointerAlignment(2);
    this.bubbleDrawable.setPadding(20, 20, 20, 20);
    stringBuilder4 = new StringBuilder();
    stringBuilder4.append("<center><b><small><font color='#3333aa'>");
    stringBuilder4.append(paramString1);
    stringBuilder4.append("</font></b><br><font color='#000000'>");
    stringBuilder4.append(paramString2);
    stringBuilder4.append("</font><br><font color='#1eb1ed'>");
    stringBuilder4.append(str4);
    stringBuilder4.append("<br>");
    if (paramString3.length() > 0) {
      paramString1 = paramString3.substring(0, 16);
    } else {
      paramString1 = paramString3;
    } 
    stringBuilder4.append(paramString1);
    stringBuilder4.append("</font><br><font color='#000000'><font color='#000000'>GPS: ");
    stringBuilder4.append(paramString4);
    stringBuilder4.append("</font><br><font color='#000000'>");
    stringBuilder4.append(str3);
    stringBuilder4.append("</font><br><font color='#000000'>");
    stringBuilder4.append(str2);
    stringBuilder4.append("</font></small></center>");
    paramString1 = stringBuilder4.toString();
    this.bubbleDrawable.setText(true, paramString1, false, "<br>Description2 here", false, 0);
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(paramString5);
    stringBuilder1.append(paramString3);
    paramString2 = stringBuilder1.toString().replace(" ", "").replace(":", "").replace(".", "").replace("-", "").replace("_", "");
    BubbleDrawable bubbleDrawable = this.bubbleDrawable;
    bubbleDrawable.createAndSaveBubble(bubbleDrawable, paramString2);
    UtilitiesFile.isAppDirAvailable();
    LatLng latLng = new LatLng(paramDouble1, paramDouble2);
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append("latitude: ");
    stringBuilder3.append(paramDouble1);
    stringBuilder3.append(" longitude: ");
    stringBuilder3.append(paramDouble2);
    stringBuilder3.append(" date: ");
    stringBuilder3.append(paramString3);
    stringBuilder3.append(" status: ");
    stringBuilder3.append(paramString4);
    stringBuilder3.append(" bubbleName: ");
    stringBuilder3.append(paramString5);
    stringBuilder3.append(" address: ");
    stringBuilder3.append(getAddressFromLatLong(latLng));
    Utilities.escribeArchivo("PMM", "GoogleMap", stringBuilder3.toString());
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(Utilities.getpath((Activity)getActivity()));
    stringBuilder2.append("/");
    stringBuilder2.append(paramString2);
    stringBuilder2.append(".png");
    String str1 = stringBuilder2.toString();
    MarkerOptions markerOptions = new MarkerOptions();
    markerOptions.position(latLng);
    Bitmap bitmap = BitmapFactory.decodeFile(str1);
    Matrix matrix = new Matrix();
    matrix.postScale(1.2F, 1.2F);
    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true)));
    this.mMap.addMarker(markerOptions);
    CameraPosition.Builder builder = new CameraPosition.Builder();
    builder.target(latLng);
    builder.zoom(16.0F);
    CameraPosition cameraPosition = builder.build();
    this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
  }
  
  private void showFollowMeBubble(ArrayList<FollowMePointsVO> paramArrayList) {
    Location[] arrayOfLocation = new Location[paramArrayList.size()];
    for (int i = paramArrayList.size() - 1; i >= 0; i--) {
      FollowMePointsVO followMePointsVO = paramArrayList.get(i);
      String str1 = followMePointsVO.getDateTime();
      String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_map_notificacionmap_sigueme_1, 2131690176);
      String str4 = TAG;
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append("Fecha: ");
      stringBuilder3.append(str1);
      Utilities.escribeArchivo(str4, "Bubble FOLLOWME showFollowMeBubble", stringBuilder3.toString());
      this.bubbleDrawable = new BubbleDrawable(activity, 1);
      this.bubbleDrawable.setCornerRadius(20.0F);
      this.bubbleDrawable.setPointerAlignment(2);
      this.bubbleDrawable.setPadding(20, 20, 20, 20);
      stringBuilder3 = new StringBuilder();
      stringBuilder3.append("<center><small><font color='#3333aa'><b>");
      stringBuilder3.append(str2);
      stringBuilder3.append("</b><br>");
      stringBuilder3.append(str1.substring(8, 10));
      stringBuilder3.append("-");
      stringBuilder3.append(str1.substring(5, 7));
      stringBuilder3.append("<br>");
      stringBuilder3.append(str1.substring(11, 16));
      stringBuilder3.append("</font></small></center>");
      str2 = stringBuilder3.toString();
      this.bubbleDrawable.setText(true, str2, false, "<br>Description2 here", false, 0);
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(followMePointsVO.getLat());
      stringBuilder2.append(followMePointsVO.getLng());
      stringBuilder2.append(String.valueOf(i));
      stringBuilder2.append(str1);
      str1 = stringBuilder2.toString().replace(" ", "").replace(":", "").replace(".", "").replace("-", "").replace("_", "");
      UtilitiesFile.isAppDirAvailable();
      BubbleDrawable bubbleDrawable = this.bubbleDrawable;
      bubbleDrawable.createAndSaveBubble(bubbleDrawable, str1);
      latLngToMicroDegrees(Double.parseDouble(followMePointsVO.getLat()), Double.parseDouble(followMePointsVO.getLng()));
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(Utilities.getpath((Activity)getActivity()));
      stringBuilder1.append("/");
      stringBuilder1.append(str1);
      stringBuilder1.append(".png");
      String str3 = stringBuilder1.toString();
      LatLng latLng = new LatLng(Double.parseDouble(followMePointsVO.getLat()), Double.parseDouble(followMePointsVO.getLng()));
      MarkerOptions markerOptions = new MarkerOptions();
      markerOptions.position(latLng);
      Bitmap bitmap = BitmapFactory.decodeFile(str3);
      Matrix matrix = new Matrix();
      matrix.postScale(1.5F, 1.5F);
      markerOptions.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true)));
      this.mMap.addMarker(markerOptions);
      CameraPosition.Builder builder = new CameraPosition.Builder();
      builder.target(latLng);
      builder.zoom(15.0F);
      CameraPosition cameraPosition = builder.build();
      this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
      arrayOfLocation[i] = new Location("Points");
      arrayOfLocation[i].setLatitude(Double.valueOf(followMePointsVO.getLat()).doubleValue());
      arrayOfLocation[i].setLongitude(Double.valueOf(followMePointsVO.getLng()).doubleValue());
    } 
    centerBubbles(Utilities.GetCentralGeoCoordinate(arrayOfLocation));
  }
  
  private void showMixedBubble(ArrayList<PushAlertsVO> paramArrayList) {
    Location[] arrayOfLocation = new Location[paramArrayList.size()];
    Collections.sort(paramArrayList, new Comparator<PushAlertsVO>(this) {
          public int compare(PushAlertsVO param1PushAlertsVO1, PushAlertsVO param1PushAlertsVO2) {
            return param1PushAlertsVO1.getDate().compareTo(param1PushAlertsVO2.getDate());
          }
        });
    Iterator<PushAlertsVO> iterator = paramArrayList.iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      PushAlertsVO pushAlertsVO = iterator.next();
      String str2 = pushAlertsVO.getDate().replace("/", "-");
      String str3 = pushAlertsVO.getAcc();
      String str1 = pushAlertsVO.getActionId();
      int i = pushAlertsVO.getAlertCodeId();
      if (str3.equals("10") || str1.equals("1")) {
        str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_map_notificacionmap_localizame_1, 2131690173);
        continue;
      } 
      if (str3.equals("12") || str1.equals("2")) {
        str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_map_notificacionmap_sigueme_1, 2131690176);
        continue;
      } 
      if (str3.equals("6")) {
        if (i == 15) {
          str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_map_notificacionmap_velocidad_1, 2131690178);
          continue;
        } 
        if (i == 57 || i == 225) {
          str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_map_notificacionmap_movimiento_1, 2131690174);
          continue;
        } 
        if (i == 65 || i == 235) {
          str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_map_notificacionmap_valet_1, 2131690177);
          continue;
        } 
      } 
      str1 = "";
      continue;
      str4 = TAG;
      stringBuilder3 = new StringBuilder();
      stringBuilder3.append("action: ");
      stringBuilder3.append((String)paramArrayList);
      Utilities.escribeArchivo(str4, "Bubble mix", stringBuilder3.toString());
      this.bubbleDrawable = new BubbleDrawable(activity, 1);
      this.bubbleDrawable.setCornerRadius(20.0F);
      this.bubbleDrawable.setPointerAlignment(2);
      this.bubbleDrawable.setPadding(20, 20, 20, 20);
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("<center><small><font color='#3333aa'><b>");
      stringBuilder2.append((String)paramArrayList);
      stringBuilder2.append("</b><br>");
      stringBuilder2.append(SYNTHETIC_LOCAL_VARIABLE_7.substring(0, 2));
      stringBuilder2.append("-");
      stringBuilder2.append(SYNTHETIC_LOCAL_VARIABLE_7.substring(3, 5));
      stringBuilder2.append("<br>");
      stringBuilder2.append(SYNTHETIC_LOCAL_VARIABLE_7.substring(11, 16));
      stringBuilder2.append("</font></small></center>");
      str1 = stringBuilder2.toString();
      this.bubbleDrawable.setText(true, str1, false, "<br>Description2 here", false, 0);
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append(SYNTHETIC_LOCAL_VARIABLE_5.getLatitude());
      stringBuilder1.append(SYNTHETIC_LOCAL_VARIABLE_5.getLongitude());
      stringBuilder1.append(String.valueOf(b));
      stringBuilder1.append((String)SYNTHETIC_LOCAL_VARIABLE_7);
      str2 = stringBuilder1.toString().replace(" ", "").replace(":", "").replace(".", "").replace("-", "").replace("_", "");
      UtilitiesFile.isAppDirAvailable();
      bubbleDrawable = this.bubbleDrawable;
      bubbleDrawable.createAndSaveBubble(bubbleDrawable, str2);
      latLngToMicroDegrees(Double.parseDouble(SYNTHETIC_LOCAL_VARIABLE_5.getLatitude()), Double.parseDouble(SYNTHETIC_LOCAL_VARIABLE_5.getLongitude()));
      latLng = new LatLng(Double.parseDouble(SYNTHETIC_LOCAL_VARIABLE_5.getLatitude()), Double.parseDouble(SYNTHETIC_LOCAL_VARIABLE_5.getLongitude()));
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(Utilities.getpath((Activity)getActivity()));
      stringBuilder2.append("/");
      stringBuilder2.append(str2);
      stringBuilder2.append(".png");
      str3 = stringBuilder2.toString();
      markerOptions = new MarkerOptions();
      markerOptions.position(latLng);
      bitmap = BitmapFactory.decodeFile(str3);
      matrix = new Matrix();
      matrix.postScale(1.5F, 1.5F);
      markerOptions.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true)));
      this.mMap.addMarker(markerOptions);
      builder = new CameraPosition.Builder();
      builder.target(latLng);
      builder.zoom(15.0F);
      cameraPosition = builder.build();
      this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
      arrayOfLocation[b] = new Location("Points");
      arrayOfLocation[b].setLatitude(Double.valueOf(SYNTHETIC_LOCAL_VARIABLE_5.getLatitude()).doubleValue());
      arrayOfLocation[b].setLongitude(Double.valueOf(SYNTHETIC_LOCAL_VARIABLE_5.getLongitude()).doubleValue());
    } 
    centerBubbles(Utilities.GetCentralGeoCoordinate(arrayOfLocation));
  }
  
  private void startActivityForFavourites() {
    Intent intent = new Intent((Context)activity, ActivityFavoritesHistory.class);
    intent.putExtra("TypeItem", (Serializable)Enums.TypeItem.FavoritesHistory);
    activity.startActivityForResult(intent, 6);
  }
  
  private void startActivityForHistorical() {
    Intent intent = new Intent((Context)activity, ActivityFavoritesHistory.class);
    intent.putExtra("TypeItem", (Serializable)Enums.TypeItem.NavigationHistory);
    activity.startActivityForResult(intent, 6);
  }
  
  private void updateGoogleSearchResultsDistances(boolean paramBoolean, double paramDouble1, double paramDouble2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   4: astore #7
    //   6: aload #7
    //   8: ifnull -> 605
    //   11: aload #7
    //   13: invokevirtual size : ()I
    //   16: ifle -> 605
    //   19: aload_0
    //   20: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   23: astore #7
    //   25: aload #7
    //   27: ifnull -> 605
    //   30: aload #7
    //   32: invokevirtual isEmpty : ()Z
    //   35: ifne -> 605
    //   38: aload_0
    //   39: getfield expListView : Landroid/widget/ExpandableListView;
    //   42: astore #7
    //   44: aload #7
    //   46: ifnull -> 605
    //   49: aload #7
    //   51: iconst_0
    //   52: invokevirtual isGroupExpanded : (I)Z
    //   55: ifeq -> 605
    //   58: aload_0
    //   59: getfield expListView : Landroid/widget/ExpandableListView;
    //   62: iconst_1
    //   63: invokevirtual setEnabled : (Z)V
    //   66: aload_0
    //   67: getfield controller : Lcom/roadtrack/onstar/mapData/MapController;
    //   70: dload_2
    //   71: dload #4
    //   73: invokevirtual microDegreesToLatLng : (DD)[D
    //   76: astore #10
    //   78: new java/util/ArrayList
    //   81: dup
    //   82: invokespecial <init> : ()V
    //   85: astore #7
    //   87: iconst_0
    //   88: istore #6
    //   90: iload #6
    //   92: aload_0
    //   93: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   96: invokevirtual size : ()I
    //   99: if_icmpge -> 375
    //   102: aload_0
    //   103: getfield mRuAlgorithm : Lcom/roadtrack/onstar/utils/RubenUltimaAlgorithm;
    //   106: aload #10
    //   108: iconst_0
    //   109: daload
    //   110: aload #10
    //   112: iconst_1
    //   113: daload
    //   114: aload_0
    //   115: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   118: iload #6
    //   120: invokevirtual get : (I)Ljava/lang/Object;
    //   123: checkcast java/util/HashMap
    //   126: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LATITUDE : Ljava/lang/String;
    //   129: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   132: checkcast java/lang/String
    //   135: invokestatic parseDouble : (Ljava/lang/String;)D
    //   138: aload_0
    //   139: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   142: iload #6
    //   144: invokevirtual get : (I)Ljava/lang/Object;
    //   147: checkcast java/util/HashMap
    //   150: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LONGITUDE : Ljava/lang/String;
    //   153: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   156: checkcast java/lang/String
    //   159: invokestatic parseDouble : (Ljava/lang/String;)D
    //   162: invokevirtual linearDistance : (DDDD)D
    //   165: dstore_2
    //   166: aload_0
    //   167: getfield mRuAlgorithm : Lcom/roadtrack/onstar/utils/RubenUltimaAlgorithm;
    //   170: dload_2
    //   171: invokevirtual linearDistanceToKilometers : (D)D
    //   174: dstore_2
    //   175: aload_0
    //   176: getfield mRuAlgorithm : Lcom/roadtrack/onstar/utils/RubenUltimaAlgorithm;
    //   179: dload_2
    //   180: invokevirtual formatLinearDistance : (D)Ljava/lang/String;
    //   183: astore #8
    //   185: aload_0
    //   186: getfield mRuAlgorithm : Lcom/roadtrack/onstar/utils/RubenUltimaAlgorithm;
    //   189: dload_2
    //   190: invokevirtual formatLinearDistanceWithString : (D)Ljava/lang/String;
    //   193: astore #9
    //   195: new java/util/HashMap
    //   198: dup
    //   199: invokespecial <init> : ()V
    //   202: astore #11
    //   204: aload #11
    //   206: ldc_w 'reference'
    //   209: aload_0
    //   210: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   213: iload #6
    //   215: invokevirtual get : (I)Ljava/lang/Object;
    //   218: checkcast java/util/HashMap
    //   221: ldc_w 'reference'
    //   224: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   227: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   230: pop
    //   231: aload #11
    //   233: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LATITUDE : Ljava/lang/String;
    //   236: aload_0
    //   237: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   240: iload #6
    //   242: invokevirtual get : (I)Ljava/lang/Object;
    //   245: checkcast java/util/HashMap
    //   248: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LATITUDE : Ljava/lang/String;
    //   251: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   254: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   257: pop
    //   258: aload #11
    //   260: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LONGITUDE : Ljava/lang/String;
    //   263: aload_0
    //   264: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   267: iload #6
    //   269: invokevirtual get : (I)Ljava/lang/Object;
    //   272: checkcast java/util/HashMap
    //   275: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LONGITUDE : Ljava/lang/String;
    //   278: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   281: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   284: pop
    //   285: aload #11
    //   287: ldc_w 'name'
    //   290: aload_0
    //   291: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   294: iload #6
    //   296: invokevirtual get : (I)Ljava/lang/Object;
    //   299: checkcast java/util/HashMap
    //   302: ldc_w 'name'
    //   305: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   308: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   311: pop
    //   312: aload #11
    //   314: ldc_w 'address'
    //   317: aload_0
    //   318: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   321: iload #6
    //   323: invokevirtual get : (I)Ljava/lang/Object;
    //   326: checkcast java/util/HashMap
    //   329: ldc_w 'address'
    //   332: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   335: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   338: pop
    //   339: aload #11
    //   341: ldc_w 'distance'
    //   344: aload #8
    //   346: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   349: pop
    //   350: aload #11
    //   352: ldc_w 'distanceString'
    //   355: aload #9
    //   357: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   360: pop
    //   361: aload #7
    //   363: aload #11
    //   365: invokevirtual add : (Ljava/lang/Object;)Z
    //   368: pop
    //   369: iinc #6, 1
    //   372: goto -> 90
    //   375: aload_0
    //   376: aload #7
    //   378: putfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   381: new java/util/ArrayList
    //   384: dup
    //   385: invokespecial <init> : ()V
    //   388: astore #9
    //   390: new java/lang/StringBuilder
    //   393: dup
    //   394: invokespecial <init> : ()V
    //   397: astore #8
    //   399: aload #8
    //   401: aload_0
    //   402: invokevirtual getActivity : ()Landroidx/fragment/app/FragmentActivity;
    //   405: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   408: ldc_w 2131690166
    //   411: invokevirtual getString : (I)Ljava/lang/String;
    //   414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: pop
    //   418: aload #8
    //   420: ldc_w ' ('
    //   423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: pop
    //   427: aload #8
    //   429: aload #7
    //   431: invokevirtual size : ()I
    //   434: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   437: pop
    //   438: aload #8
    //   440: ldc_w ')'
    //   443: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   446: pop
    //   447: aload #9
    //   449: aload #8
    //   451: invokevirtual toString : ()Ljava/lang/String;
    //   454: invokeinterface add : (Ljava/lang/Object;)Z
    //   459: pop
    //   460: aload_0
    //   461: getfield isLongPress : Z
    //   464: ifne -> 517
    //   467: aload_0
    //   468: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   471: astore #8
    //   473: aload #8
    //   475: ifnull -> 490
    //   478: aload #8
    //   480: invokevirtual size : ()I
    //   483: iconst_1
    //   484: if_icmpne -> 490
    //   487: goto -> 517
    //   490: aload_0
    //   491: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   494: ifnonnull -> 525
    //   497: aload #7
    //   499: invokevirtual size : ()I
    //   502: iconst_1
    //   503: if_icmpne -> 525
    //   506: aload_0
    //   507: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   510: iconst_0
    //   511: invokevirtual setShowExpandableIcon : (Z)V
    //   514: goto -> 525
    //   517: aload_0
    //   518: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   521: iconst_0
    //   522: invokevirtual setShowExpandableIcon : (Z)V
    //   525: iload_1
    //   526: ifeq -> 543
    //   529: aload_0
    //   530: getfield expListView : Landroid/widget/ExpandableListView;
    //   533: aload_0
    //   534: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   537: invokevirtual setAdapter : (Landroid/widget/ExpandableListAdapter;)V
    //   540: goto -> 561
    //   543: aload_0
    //   544: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   547: aload_0
    //   548: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   551: invokevirtual setData : (Ljava/util/ArrayList;)V
    //   554: aload_0
    //   555: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   558: invokevirtual notifyDataSetChanged : ()V
    //   561: aload #7
    //   563: invokevirtual isEmpty : ()Z
    //   566: ifne -> 1013
    //   569: iload_1
    //   570: ifeq -> 1013
    //   573: aload_0
    //   574: getfield expListView : Landroid/widget/ExpandableListView;
    //   577: iconst_0
    //   578: invokevirtual expandGroup : (I)Z
    //   581: pop
    //   582: aload_0
    //   583: getfield expListView : Landroid/widget/ExpandableListView;
    //   586: invokevirtual getVisibility : ()I
    //   589: bipush #8
    //   591: if_icmpne -> 1013
    //   594: aload_0
    //   595: getfield expListView : Landroid/widget/ExpandableListView;
    //   598: iconst_0
    //   599: invokevirtual setVisibility : (I)V
    //   602: goto -> 1013
    //   605: aload_0
    //   606: getfield expListView : Landroid/widget/ExpandableListView;
    //   609: iconst_1
    //   610: invokevirtual setEnabled : (Z)V
    //   613: new java/util/ArrayList
    //   616: dup
    //   617: invokespecial <init> : ()V
    //   620: astore #7
    //   622: new java/lang/StringBuilder
    //   625: dup
    //   626: invokespecial <init> : ()V
    //   629: astore #8
    //   631: aload #8
    //   633: aload_0
    //   634: invokevirtual getActivity : ()Landroidx/fragment/app/FragmentActivity;
    //   637: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   640: ldc_w 2131690166
    //   643: invokevirtual getString : (I)Ljava/lang/String;
    //   646: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: pop
    //   650: aload #8
    //   652: ldc_w ' ('
    //   655: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   658: pop
    //   659: aload #8
    //   661: aload_0
    //   662: getfield placesListItems : Ljava/util/ArrayList;
    //   665: invokevirtual size : ()I
    //   668: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   671: pop
    //   672: aload #8
    //   674: ldc_w ')'
    //   677: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: pop
    //   681: aload #7
    //   683: aload #8
    //   685: invokevirtual toString : ()Ljava/lang/String;
    //   688: invokeinterface add : (Ljava/lang/Object;)Z
    //   693: pop
    //   694: aload_0
    //   695: new com/roadtrack/onstar/adapter/ExpandableResultsAdapter
    //   698: dup
    //   699: getstatic com/roadtrack/onstar/googleMaps/MapsFragment.activity : Landroid/app/Activity;
    //   702: aload #7
    //   704: aload_0
    //   705: getfield placesListItems : Ljava/util/ArrayList;
    //   708: new com/roadtrack/onstar/googleMaps/MapsFragment$39
    //   711: dup
    //   712: aload_0
    //   713: invokespecial <init> : (Lcom/roadtrack/onstar/googleMaps/MapsFragment;)V
    //   716: iconst_0
    //   717: new com/roadtrack/onstar/googleMaps/MapsFragment$40
    //   720: dup
    //   721: aload_0
    //   722: invokespecial <init> : (Lcom/roadtrack/onstar/googleMaps/MapsFragment;)V
    //   725: invokespecial <init> : (Landroid/app/Activity;Ljava/util/List;Ljava/util/ArrayList;Lcom/roadtrack/onstar/interfaces/TomTomStartNavigation_Interface;ZLcom/roadtrack/onstar/async_tasks/intefaces/ExpandableIcon_Interface;)V
    //   728: putfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   731: aload_0
    //   732: getfield isLongPress : Z
    //   735: ifne -> 792
    //   738: aload_0
    //   739: getfield placesListItems : Ljava/util/ArrayList;
    //   742: astore #7
    //   744: aload #7
    //   746: ifnull -> 761
    //   749: aload #7
    //   751: invokevirtual size : ()I
    //   754: iconst_1
    //   755: if_icmpne -> 761
    //   758: goto -> 792
    //   761: aload_0
    //   762: getfield placesListItems : Ljava/util/ArrayList;
    //   765: astore #7
    //   767: aload #7
    //   769: ifnull -> 800
    //   772: aload #7
    //   774: invokevirtual size : ()I
    //   777: iconst_1
    //   778: if_icmpne -> 800
    //   781: aload_0
    //   782: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   785: iconst_0
    //   786: invokevirtual setShowExpandableIcon : (Z)V
    //   789: goto -> 800
    //   792: aload_0
    //   793: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   796: iconst_0
    //   797: invokevirtual setShowExpandableIcon : (Z)V
    //   800: aload_0
    //   801: getfield typeFloating : Ljava/lang/String;
    //   804: ldc_w ''
    //   807: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   810: ifne -> 925
    //   813: aload_0
    //   814: aload_0
    //   815: getfield placesListItems : Ljava/util/ArrayList;
    //   818: invokevirtual setPlacesListItems : (Ljava/util/ArrayList;)V
    //   821: iconst_0
    //   822: istore #6
    //   824: iload #6
    //   826: aload_0
    //   827: getfield placesListItems : Ljava/util/ArrayList;
    //   830: invokevirtual size : ()I
    //   833: if_icmpge -> 925
    //   836: aload_0
    //   837: getfield placesListItems : Ljava/util/ArrayList;
    //   840: iload #6
    //   842: invokevirtual get : (I)Ljava/lang/Object;
    //   845: checkcast java/util/HashMap
    //   848: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LATITUDE : Ljava/lang/String;
    //   851: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   854: checkcast java/lang/String
    //   857: astore #7
    //   859: aload_0
    //   860: getfield placesListItems : Ljava/util/ArrayList;
    //   863: iload #6
    //   865: invokevirtual get : (I)Ljava/lang/Object;
    //   868: checkcast java/util/HashMap
    //   871: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LONGITUDE : Ljava/lang/String;
    //   874: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   877: checkcast java/lang/String
    //   880: astore #8
    //   882: new com/roadtrack/onstar/mapData/MapController
    //   885: dup
    //   886: aload_0
    //   887: getfield mMap : Lcom/google/android/gms/maps/GoogleMap;
    //   890: getstatic com/roadtrack/onstar/googleMaps/MapsFragment.activity : Landroid/app/Activity;
    //   893: aload_0
    //   894: invokespecial <init> : (Lcom/google/android/gms/maps/GoogleMap;Landroid/app/Activity;Lcom/roadtrack/onstar/googleMaps/MapsFragment;)V
    //   897: aload #7
    //   899: invokestatic parseDouble : (Ljava/lang/String;)D
    //   902: aload #8
    //   904: invokestatic parseDouble : (Ljava/lang/String;)D
    //   907: aload_0
    //   908: getfield typeFloating : Ljava/lang/String;
    //   911: iload #6
    //   913: getstatic com/roadtrack/onstar/googleMaps/MapsFragment.activity : Landroid/app/Activity;
    //   916: invokevirtual setMarkers : (DDLjava/lang/String;ILandroid/app/Activity;)V
    //   919: iinc #6, 1
    //   922: goto -> 824
    //   925: iload_1
    //   926: ifeq -> 943
    //   929: aload_0
    //   930: getfield expListView : Landroid/widget/ExpandableListView;
    //   933: aload_0
    //   934: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   937: invokevirtual setAdapter : (Landroid/widget/ExpandableListAdapter;)V
    //   940: goto -> 961
    //   943: aload_0
    //   944: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   947: aload_0
    //   948: getfield placesListItems : Ljava/util/ArrayList;
    //   951: invokevirtual setData : (Ljava/util/ArrayList;)V
    //   954: aload_0
    //   955: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   958: invokevirtual notifyDataSetChanged : ()V
    //   961: aload_0
    //   962: getfield placesListItems : Ljava/util/ArrayList;
    //   965: astore #7
    //   967: aload #7
    //   969: ifnull -> 1013
    //   972: aload #7
    //   974: invokevirtual isEmpty : ()Z
    //   977: ifne -> 1013
    //   980: iload_1
    //   981: ifeq -> 1013
    //   984: aload_0
    //   985: getfield expListView : Landroid/widget/ExpandableListView;
    //   988: iconst_0
    //   989: invokevirtual expandGroup : (I)Z
    //   992: pop
    //   993: aload_0
    //   994: getfield expListView : Landroid/widget/ExpandableListView;
    //   997: invokevirtual getVisibility : ()I
    //   1000: bipush #8
    //   1002: if_icmpne -> 1013
    //   1005: aload_0
    //   1006: getfield expListView : Landroid/widget/ExpandableListView;
    //   1009: iconst_0
    //   1010: invokevirtual setVisibility : (I)V
    //   1013: return
  }
  
  private void userActive() {
    Intent intent = new Intent();
    intent.setAction("GlobalTouchService");
    intent.putExtra("ACTION_EXTRA", "usuario_activo_map");
    activity.sendBroadcast(intent);
  }
  
  public boolean ValidateTheftAuto() {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    boolean bool = this.dbFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), userDevicesVO.getDeviceId(), "", true);
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Mostrando1: ");
    stringBuilder.append(bool);
    Utilities.escribeArchivo(str, "THEFT", stringBuilder.toString());
    Utilities.showTheftAutoBanner((TextView)this.view.findViewById(2131297056), (Context)activity, bool);
    return bool;
  }
  
  protected void buildGoogleApiClient() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/google/android/gms/common/api/GoogleApiClient$Builder
    //   5: astore_1
    //   6: aload_1
    //   7: aload_0
    //   8: invokevirtual getContext : ()Landroid/content/Context;
    //   11: invokespecial <init> : (Landroid/content/Context;)V
    //   14: aload_1
    //   15: aload_0
    //   16: invokevirtual addConnectionCallbacks : (Lcom/google/android/gms/common/api/GoogleApiClient$ConnectionCallbacks;)Lcom/google/android/gms/common/api/GoogleApiClient$Builder;
    //   19: pop
    //   20: aload_1
    //   21: aload_0
    //   22: invokevirtual addOnConnectionFailedListener : (Lcom/google/android/gms/common/api/GoogleApiClient$OnConnectionFailedListener;)Lcom/google/android/gms/common/api/GoogleApiClient$Builder;
    //   25: pop
    //   26: aload_1
    //   27: getstatic com/google/android/gms/location/LocationServices.API : Lcom/google/android/gms/common/api/Api;
    //   30: invokevirtual addApi : (Lcom/google/android/gms/common/api/Api;)Lcom/google/android/gms/common/api/GoogleApiClient$Builder;
    //   33: pop
    //   34: aload_0
    //   35: aload_1
    //   36: invokevirtual build : ()Lcom/google/android/gms/common/api/GoogleApiClient;
    //   39: putfield mGoogleApiClient : Lcom/google/android/gms/common/api/GoogleApiClient;
    //   42: aload_0
    //   43: getfield mGoogleApiClient : Lcom/google/android/gms/common/api/GoogleApiClient;
    //   46: invokevirtual connect : ()V
    //   49: aload_0
    //   50: monitorexit
    //   51: return
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Exception table:
    //   from	to	target	type
    //   2	49	52	finally
  }
  
  public void dialogNoGPSActive() {
    String str1 = Utilities.getStringFromConfigList(getContext(), this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str3 = Utilities.getStringFromConfigList(getContext(), this.stringsResourcesVO.navigation_gps_lbl_alerta_1, 2131690144);
    String str4 = Utilities.getStringFromConfigList(getContext(), this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str2 = Utilities.getStringFromConfigList(getContext(), this.stringsResourcesVO.global_popup_btn_opciones_1, 2131689951);
    final Dialog dialog = Utilities.simpleDialog(getContext(), null, str1, str3, true, str4, true, str2, 20.0F, 16.0F);
    this.btnOk1 = (Button)dialog.findViewById(2131296343);
    this.btnC = (Button)dialog.findViewById(2131296344);
    this.btnOk1.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.btnC.setOnClickListener(new View.OnClickListener() {
          final MapsFragment this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
            MapsFragment.this.startActivity(intent);
          }
        });
    dialog.show();
  }
  
  public View getViewByPosition(int paramInt, ListView paramListView) {
    int j = paramListView.getFirstVisiblePosition();
    int i = paramListView.getChildCount();
    return (paramInt < j || paramInt > i + j - 1) ? paramListView.getAdapter().getView(paramInt, null, (ViewGroup)paramListView) : paramListView.getChildAt(paramInt - j);
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    super.onActivityCreated(paramBundle);
    FragmentManager fragmentManager = getChildFragmentManager();
    this.mapFragment = (SupportMapFragment)fragmentManager.findFragmentById(2131296880);
    if (this.mapFragment == null) {
      this.mapFragment = SupportMapFragment.newInstance();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.replace(2131296880, (Fragment)this.mapFragment);
      fragmentTransaction.commit();
    } 
    this.mapFragment.getMapAsync(this);
    if (GlobalMembers.activeTutorial) {
      this.idTutorial = (LinearLayout)this.view.findViewById(2131296607);
      this.idTutorial.setOnClickListener(this.clickTutorial);
      this.idTutorial.setVisibility(0);
    } 
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1 && paramInt1 == 6) {
      this.TypeMarker = paramIntent.getIntExtra("Type", 0);
      GlobalMembers.iconFav = true;
      this.isFromFavouritesActivity = true;
      GlobalMembers.isFromShareMarkerActivity = false;
      GlobalMembers.isFromShareFavActivity = false;
      setMapType();
    } 
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public void onConnected(Bundle paramBundle) {
    this.mLocationRequest = new LocationRequest();
    this.mLocationRequest.setInterval(1000L);
    this.mLocationRequest.setFastestInterval(1000L);
    this.mLocationRequest.setPriority(102);
    if (ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION") == 0)
      LocationServices.FusedLocationApi.requestLocationUpdates(this.mGoogleApiClient, this.mLocationRequest, this); 
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult) {}
  
  public void onConnectionSuspended(int paramInt) {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    this.view = paramLayoutInflater.inflate(2131427413, paramViewGroup, false);
    Bundle bundle = getArguments();
    if (bundle != null) {
      try {
        this.params = (GoogleMapVO)bundle.getSerializable("googleObject");
        this.mapType = (Enums.navigationProcess)this.params.getKEY_SET_ENGINE_SOURCE();
        this.params.getPREVIOUS_ACTIVITY();
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      } 
      try {
        int i = Utilities.getDevicePositionByDeviceId(this.params.getPUSH_NOTIFICATION_DEVICE_ID());
        if (i != -1) {
          UserPreferenceVO userPreferenceVO = this.dbFunctions.getUserPreference(GlobalMembers.userLogged);
          UserDevicesVO userDevicesVO = this.rtApp.getmDeviceUserList().get(i);
          this.dbFunctions.addVehicleSelected((Context)activity, userPreferenceVO.getUser(), userDevicesVO);
          Utilities.updateVehicleSelected(getContext(), userPreferenceVO.getUser(), userDevicesVO);
        } 
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      } 
    } 
    activity = (Activity)getActivity();
    this.mRuAlgorithm = new RubenUltimaAlgorithm();
    this.mapsFragmentView = new MapsFragmentView(this, this.view);
    this.stringsResourcesVO = new StringsResourcesVO();
    global_popup_lbl_aviso_1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    global_popup_lbl_accionencurso_1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
    global_popup_btn_ok_1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
    global_popup_btn_no_1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.bind_error, 2131689687);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.app_name, 2131689666);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.coordinatesNotValid, 2131689738);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_share_lbl_compartirubicacionvehic_1, 2131690191);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_lbl_share_titulocompartirlocalizacion_1, 2131690163);
    this.bubbleDrawable = new BubbleDrawable(activity, 1);
    new SaveImageCache(activity, 1);
    new SaveImageCache(activity, 1);
    this.dbFunctions = new DBFunctions((Context)activity);
    this.rtApp = (onstarApplication)activity.getApplicationContext();
    this.rtApp.setAppStatus(1);
    this.taskSet = new TaskSet();
    activity.getWindow().addFlags(128);
    setDrawerLayout();
    setLeftNavigationDrawer(paramBundle);
    setLinearLayoutRoutenavheader();
    setViewGroupTomTomSearchBar();
    setButtonToggleLeftNavigationDrawer();
    setImageButtonCenterLocation();
    setImageViewSplashScreen();
    initButtons();
    setViewGroupTopInstructions();
    setEditTextSearch();
    setLinearLayoutBottom();
    userActive();
    setFrameContainer();
    setBottomContainer();
    setShareLocationBottomMenu();
    setShareLocationTopMenu();
    setImageViewFindMe();
    setExpandableListViewData();
    setListViewVehicles();
    setLayoutVehicleHeader();
    setProgressBarShareHeader();
    setProgressBarVehicles();
    setRelativeFloatingMenu();
    sendTomTomStatistics();
    createNavigationBottons();
    if (paramBundle != null) {
      ArrayList<PointInfo> arrayList = this.currentMarkers;
      if (arrayList == null || arrayList.size() < 1)
        try {
          this.currentMarkers = (ArrayList<PointInfo>)paramBundle.getSerializable("markers");
        } catch (Exception exception) {
          Utilities.escribeArchivo(TAG, "Error recuperando bundle", exception.getMessage());
          this.currentMarkers = new ArrayList<PointInfo>();
        }  
    } 
    Utilities.escribeArchivo(TAG, "onCreateView", "Test...........");
    setRetainInstance(true);
    return this.view;
  }
  
  public void onLocationChanged(Location paramLocation) {
    Marker marker = this.mCurrLocationMarker;
    if (marker != null && this.myLocationPressed) {
      marker.remove();
      this.currentMarkers.clear();
    } 
    if (this.myLocationPressed) {
      this.latLngGlobal = new LatLng(paramLocation.getLatitude(), paramLocation.getLongitude());
      this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.latLngGlobal, 16.0F));
      this.myLocationPressed = false;
      if (this.controller == null) {
        setRelativeFloatingMenu();
        this.rlContainerMenu.setVisibility(0);
        this.controller = new MapController(this.mMap, activity, this);
        this.mapsFragmentView.setLatLng(this.latLngGlobal);
        this.mapsFragmentView.setFloating(this.mMap);
        this.mapsFragmentView.getFloating().expand();
      } 
    } 
  }
  
  public void onMapClick(LatLng paramLatLng) {
    if (this.mapType.equals(Enums.navigationProcess.Navigation) && this.currentFunction.equals(TAG)) {
      this.mMap.clear();
      this.currentMarkers.clear();
      this.mCurrLocationMarker = null;
      resetMapScreen();
      ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(this.googSearch.getApplicationWindowToken(), 2);
      if (lvSavedEditSearch.getVisibility() == 0) {
        ListView listView = lvSavedEditSearch;
        if (listView != null)
          listView.setVisibility(8); 
      } 
      if (this.controller == null)
        this.rlContainerMenu.setVisibility(8); 
    } 
  }
  
  public void onMapLongClick(LatLng paramLatLng) {
    if (this.mapType.equals(Enums.navigationProcess.Navigation) && this.currentFunction.equals(TAG)) {
      enableSendRouteShareLocationGoToRoute();
      this.mMap.clear();
      this.currentMarkers.clear();
      enableFavorites(Boolean.valueOf(true));
      if (this.currentFunction.equals(TAG) && GlobalMembers.activeTutorial && this.mapType.equals(Enums.navigationProcess.Navigation)) {
        this.idTutorial = (LinearLayout)this.view.findViewById(2131296607);
        this.idTutorial.setOnClickListener(this.clickTutorial);
        this.idTutorial.setVisibility(0);
      } 
      GoogleMap googleMap = this.mMap;
      MarkerOptions markerOptions = new MarkerOptions();
      markerOptions.position(paramLatLng);
      markerOptions.title("");
      markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
      this.mCurrLocationMarker = googleMap.addMarker(markerOptions);
      this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paramLatLng, 15.0F));
      saveMarkerInfo(paramLatLng);
      if (this.latLngGlobal != null)
        showPointDetail(paramLatLng.latitude, paramLatLng.longitude, false); 
    } 
  }
  
  public void onMapReady(GoogleMap paramGoogleMap) {
    this.mMap = paramGoogleMap;
    this.mMap.setOnMapLongClickListener(this);
    this.mMap.setOnMapClickListener(this);
    this.mMap.setOnMarkerClickListener(this);
    this.mMap.getUiSettings().setRotateGesturesEnabled(false);
    this.mMap.getUiSettings().setMyLocationButtonEnabled(false);
    buildGoogleApiClient();
    drawMarker(this.currentMarkers);
    if (OnFollowMeListener.onFollowMeObjectListener == null)
      new OnFollowMeListener(new OnFollowMeListener.OnFollowMeObjectListener() {
            final MapsFragment this$0;
            
            public void onFollowMe(ArrayList<FollowMePointsVO> param1ArrayList) {
              if (MapsFragment.this.mapType.equals(Enums.navigationProcess.FollowMe))
                MapsFragment.this.showFollowMeBubble(GlobalMembers.followMeArrayListPoints); 
            }
          }); 
    setMapType();
  }
  
  public boolean onMarkerClick(Marker paramMarker) {
    if (paramMarker.getTag() != null) {
      String str1 = (String)((HashMap)this.placesListItems.get(((Integer)paramMarker.getTag()).intValue())).get(SearchCompleteTask.KEY_LATITUDE);
      String str2 = (String)((HashMap)this.placesListItems.get(((Integer)paramMarker.getTag()).intValue())).get(SearchCompleteTask.KEY_LONGITUDE);
      LatLng latLng = new LatLng(Double.parseDouble(str1), Double.parseDouble(str2));
      this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0F));
      this.mExpandableListAdapter.notifyDataSetChanged();
      showPointDetail(Double.parseDouble(str1), Double.parseDouble(str2), true);
      return true;
    } 
    return false;
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    if (paramBundle != null) {
      ArrayList<PointInfo> arrayList = this.currentMarkers;
      if (arrayList != null)
        paramBundle.putSerializable("markers", arrayList); 
    } 
  }
  
  public void resetListAndMap() {
    this.mMap.clear();
    this.mGoogleSearchResults = new ArrayList<HashMap<String, String>>();
    ArrayList arrayList = new ArrayList();
    this.mExpandableListAdapter = new ExpandableResultsAdapter(activity, arrayList, this.mGoogleSearchResults, new TomTomStartNavigation_Interface(this) {
          public void onStartNavigation(String param1String1, String param1String2, String param1String3, String param1String4) {}
        },  false, new ExpandableIcon_Interface(this) {
          public void onExpand() {}
        });
    this.expListView.setAdapter((ExpandableListAdapter)this.mExpandableListAdapter);
    this.expListView.setEnabled(true);
  }
  
  public void setMarkersMap(double paramDouble1, double paramDouble2) {
    enableSendRouteShareLocationGoToRoute();
    LatLng latLng = new LatLng(paramDouble1, paramDouble2);
    this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0F));
    PointInfo pointInfo = new PointInfo(null, null, latLng, null);
    pointInfo.setName(pointInfo.getAddress());
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("address: ");
    stringBuilder.append(pointInfo.getName());
    Utilities.escribeArchivo(str, "sendRouteFunction", stringBuilder.toString());
    this.currentMarkers.add(pointInfo);
  }
  
  public void setPlacesListItems(ArrayList<HashMap<String, String>> paramArrayList) {
    this.placesListItems = paramArrayList;
  }
  
  public void setPosition(int paramInt) {
    this.position = paramInt;
  }
  
  public void setPressTuto(boolean paramBoolean) {
    this.pressTuto = paramBoolean;
  }
  
  public void setUserVisibleHint(boolean paramBoolean) {
    super.setUserVisibleHint(paramBoolean);
    if (paramBoolean) {
      FragmentActivity fragmentActivity = getActivity();
      if (fragmentActivity != null)
        fragmentActivity.setRequestedOrientation(1); 
    } 
  }
  
  public ClassElements setViewMaps() {
    ClassElements classElements = new ClassElements();
    String str = Utilities.getStringFromConfigList(getContext(), this.stringsResourcesVO.tutorial_lbl_sigueme, 2131690463);
    ShowViewElement.setValues(classElements, this.mapFragment.getView(), 0, str, "solo", 0, getResources().getInteger(2131361821), false, getResources().getInteger(2131361812), 0, false);
    return classElements;
  }
  
  public ClassElements setViews() {
    ClassElements classElements = new ClassElements();
    String str5 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_buscardireccion, 2131690441);
    String str4 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_localicevehiculo, 2131690452);
    String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_enviedestino, 2131690446);
    String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_favoritos, 2131690447);
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_compartir, 2131690443);
    if (this.position == 0) {
      ViewGroup viewGroup = this.viewGroupTomTomSearchBar;
      if (viewGroup != null)
        ShowViewElement.setValues(classElements, (View)viewGroup, 0, str5, "centro", -120, getResources().getInteger(2131361814), false, -getResources().getInteger(2131361821), getResources().getInteger(2131361818), false); 
      ImageButton imageButton4 = this.imageButtonFindMe;
      if (imageButton4 != null)
        ShowViewElement.setValues(classElements, (View)imageButton4, 1, str4, "rectaAbajo", 0, getResources().getInteger(2131361821), false, 0, -20, false); 
      ImageButton imageButton3 = this.imageButtonSendRoute;
      if (imageButton3 != null)
        ShowViewElement.setValues(classElements, (View)imageButton3, 2, str3, "rectaAbajo", 0, getResources().getInteger(2131361816), false, -getResources().getInteger(2131361819), 0, false); 
      ImageButton imageButton2 = this.imageButtonFavs;
      if (imageButton2 != null)
        ShowViewElement.setValues(classElements, (View)imageButton2, 3, str2, "rectaAbajo", 0, getResources().getInteger(2131361818), false, -getResources().getInteger(2131361806), 0, false); 
      ImageButton imageButton1 = this.imageButtonShareLocation;
      if (imageButton1 != null)
        ShowViewElement.setValues(classElements, (View)imageButton1, 4, str1, "rectaAbajo", 0, getResources().getInteger(2131361821), false, -getResources().getInteger(2131361816), 0, false); 
    } 
    return classElements;
  }
  
  public void showMarquerOnResult(ArrayList<HashMap<String, String>> paramArrayList) {
    HashMap hashMap = paramArrayList.get(0);
    String str1 = (String)hashMap.get(ExpandableResultsAdapter.KEY_NAME);
    String str2 = (String)hashMap.get(ExpandableResultsAdapter.KEY_ADDRESS);
    String str3 = (String)hashMap.get(ExpandableResultsAdapter.KEY_LATITUDE);
    String str4 = (String)hashMap.get(ExpandableResultsAdapter.KEY_LONGITUDE);
    String str5 = (String)hashMap.get(ExpandableResultsAdapter.KEY_DISTANCE);
    if (str1 != null && str1.contains(", ."))
      str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_main_lbl_sindireccion, 2131690170); 
    double d2 = Double.parseDouble(str3);
    double d1 = Double.parseDouble(str4);
    if (d2 != 0.0D) {
      str3 = Utilities.googleNormalizeAddress(str1, str2);
      this.expListView.collapseGroup(0);
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append(d2);
      String str = stringBuilder1.toString();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("");
      stringBuilder2.append(d1);
      fakeGoogleSearchResult("REFERENCE", str, stringBuilder2.toString(), str1, str2, false, false);
      this.destinationName = str1;
      this.destinationAddress = str3;
      GlobalMembers.googleSearchTitleToFavorite = str1;
      LatLng latLng = new LatLng(d2, d1);
      GoogleMap googleMap = this.mMap;
      MarkerOptions markerOptions = new MarkerOptions();
      markerOptions.position(latLng);
      markerOptions.title("");
      this.mCurrLocationMarker = googleMap.addMarker(markerOptions);
      this.mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
      PointInfo pointInfo = new PointInfo(this.destinationAddress, this.destinationName, latLng, null);
      this.currentMarkers.add(pointInfo);
    } 
  }
  
  public void showPointDetail(double paramDouble1, double paramDouble2, boolean paramBoolean) {
    if (!isRouteInProgress && this.mapType.equals(Enums.navigationProcess.Navigation)) {
      this.isLongPress = true;
      List<Address> list = new ArrayList();
      Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
      try {
        List<Address> list1 = geocoder.getFromLocation(paramDouble1, paramDouble2, 1);
        list = list1;
      } catch (IOException iOException) {
        iOException.printStackTrace();
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(((Address)list.get(0)).getCountryName());
      stringBuilder1.append(" ");
      stringBuilder1.append(((Address)list.get(0)).getFeatureName());
      stringBuilder1.append(" ");
      stringBuilder1.append(((Address)list.get(0)).getThoroughfare());
      stringBuilder1.append(", ");
      stringBuilder1.append(((Address)list.get(0)).getLocality());
      String str2 = stringBuilder1.toString();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(((Address)list.get(0)).getFeatureName());
      stringBuilder2.append(" ");
      stringBuilder2.append(((Address)list.get(0)).getThoroughfare());
      String str1 = stringBuilder2.toString();
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("");
      stringBuilder2.append(paramDouble1);
      String str3 = stringBuilder2.toString();
      StringBuilder stringBuilder3 = new StringBuilder();
      stringBuilder3.append("");
      stringBuilder3.append(paramDouble2);
      fakeGoogleSearchResult("REFERENCE", str3, stringBuilder3.toString(), str1, str2, false, paramBoolean);
    } 
    ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(this.googSearch.getApplicationWindowToken(), 2);
    if (lvSavedEditSearch.getVisibility() == 0) {
      ListView listView = lvSavedEditSearch;
      if (listView != null)
        listView.setVisibility(8); 
    } 
    GlobalMembers.iconFav = false;
  }
  
  public void showTutorial() {
    ClassElements classElements;
    this.pressTuto = false;
    Enums.navigationProcess navigationProcess1 = this.mapType;
    if (navigationProcess1 != null && navigationProcess1.equals(Enums.navigationProcess.FollowMe)) {
      classElements = setViewMaps();
    } else {
      classElements = setViews();
    } 
    this.show = new ShowViewElement(activity, this, classElements);
    this.show.setPages(1);
  }
  
  private class LeftSlideMenuClickListener implements AdapterView.OnItemClickListener {
    final MapsFragment this$0;
    
    private LeftSlideMenuClickListener() {}
    
    public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      if (param1Int != 0) {
        if (param1Int != 1) {
          if (param1Int != 2) {
            if (param1Int == 3) {
              MapsFragment.this.generateEmergencyDialog();
              MapsFragment.this.closeLeftNavigationDrawer();
            } 
          } else {
            MapsFragment.this.generateAssistanceDialog();
            MapsFragment.this.closeLeftNavigationDrawer();
          } 
        } else {
          if (MapsFragment.this.ValidateTheftAuto())
            return; 
          MapsFragment.this.startActivityForFavourites();
          MapsFragment.this.closeLeftNavigationDrawer();
        } 
      } else {
        if (MapsFragment.this.ValidateTheftAuto())
          return; 
        MapsFragment.this.startActivityForHistorical();
        MapsFragment.this.closeLeftNavigationDrawer();
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/googleMaps/MapsFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */