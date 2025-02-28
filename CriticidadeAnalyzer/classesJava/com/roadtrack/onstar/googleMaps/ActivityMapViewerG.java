package com.roadtrack.onstar.googleMaps;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
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
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
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
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roadtrack.onstar.BO.ActionsProcess;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.FollowMeHandler;
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
import com.roadtrack.onstar.adapter.ShareVehicleListAdapter;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.ExpandableIcon_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.GeocoderInterface;
import com.roadtrack.onstar.async_tasks.intefaces.GoogleSearchResults_Interface;
import com.roadtrack.onstar.async_tasks.tasks.GeocoderTask;
import com.roadtrack.onstar.async_tasks.tasks_set.TaskSet;
import com.roadtrack.onstar.errors.DefaultExceptionHandler;
import com.roadtrack.onstar.floatingActionButton.FloatingActionsMenu;
import com.roadtrack.onstar.interfaces.TomTomStartNavigation_Interface;
import com.roadtrack.onstar.mapData.MapController;
import com.roadtrack.onstar.nav.routing.NavigateCommonDialogActivity;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.tomtom.activities.ActivityFavoritesHistory;
import com.roadtrack.onstar.tomtom.nvdrwrs.TomTomMapLeftNavDrawerItem;
import com.roadtrack.onstar.tomtom.nvdrwrs.TomTomMapLeftNavDrawerListAdapter;
import com.roadtrack.onstar.tomtom.utilities.BubbleDrawable;
import com.roadtrack.onstar.tomtom.utilities.OnFollowMeListener;
import com.roadtrack.onstar.ui.dialogs.ActivityCall;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.utils.CallPhone;
import com.roadtrack.onstar.utils.DialogEmpty;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.RubenUltimaAlgorithm;
import com.roadtrack.onstar.utils.UserInterfaceUtils;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import com.roadtrack.onstar.viewTutorial.ClassElements;
import com.roadtrack.onstar.viewTutorial.ShowViewElement;
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

public class ActivityMapViewerG extends FragmentActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapLongClickListener, OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {
  private static final String TAG = ActivityMapViewerG.class.getSimpleName();
  
  private static ActivityMapViewerG activity;
  
  private static String global_popup_btn_no_1;
  
  private static String global_popup_btn_ok_1;
  
  private static String global_popup_lbl_accionencurso_1;
  
  private static String global_popup_lbl_aviso_1;
  
  public static ImageButton imageButtonFavs;
  
  static boolean isBubbleActived;
  
  private static boolean isFavClicked = false;
  
  public static boolean isRouteInProgress;
  
  public static ListView lvSavedEditSearch;
  
  private static Location mCurrentLocation;
  
  public static boolean onSendRoute = false;
  
  private static ArrayList<String> saveEdittextsearch;
  
  private int TypeMarker;
  
  private ActivityMapViewerViewG activityMapViewerView;
  
  private TextView addr;
  
  private TextView addresslistshare;
  
  private LinearLayout bottom_container;
  
  private BubbleDrawable bubbleDrawable;
  
  private Button buttonNOk;
  
  private Button buttonOk;
  
  private Button buttonToggleLeftNavigationDrawer;
  
  private boolean cleanMarkers;
  
  public View.OnClickListener clickTutorial = new View.OnClickListener() {
      final ActivityMapViewerG this$0;
      
      public void onClick(View param1View) {
        param1View = ActivityMapViewerG.getActivity().getCurrentFocus();
        if (param1View != null) {
          ((InputMethodManager)ActivityMapViewerG.this.getApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(param1View.getWindowToken(), 0);
          (new Handler()).postDelayed(new Runnable() {
                final ActivityMapViewerG.null this$1;
                
                public void run() {
                  if (!ActivityMapViewerG.onSendRoute) {
                    if (ActivityMapViewerG.this.pressTuto)
                      ActivityMapViewerG.this.showTutorial(); 
                  } else {
                    ActivityMapViewerG.this.setActionButtons();
                  } 
                }
              },  100L);
        } 
      }
    };
  
  private SearchCompleteTask controlViews = null;
  
  private MapController controller;
  
  private String currentFunction = TAG;
  
  private ArrayList<PointInfo> currentMarkers = new ArrayList<PointInfo>();
  
  private ShareVehicleListAdapter dataAdapter;
  
  private TextView dateShare;
  
  private DBFunctions dbFunctions;
  
  private String destinationAddress = null;
  
  private String destinationName = null;
  
  private DrawerLayout drawerLayout;
  
  private ExpandableListView expListView;
  
  private FloatingActionsMenu fab;
  
  private FloatingActionsMenu fabLans;
  
  private FrameLayout frame_container;
  
  private EditText googSearch;
  
  private GoogleMapVO googleMapVO = new GoogleMapVO();
  
  private GoogleSearchResults_Interface googleSearchResults_interface;
  
  private LinearLayout idTutorial;
  
  private ImageView imageButtonCenterLocation;
  
  private ImageButton imageButtonFindMe;
  
  private ImageButton imageButtonGoToRoute;
  
  private ImageButton imageButtonSendRoute;
  
  private ImageButton imageButtonShareLocation;
  
  private ImageView imageViewFindMe;
  
  private ImageView imageViewSendRoute;
  
  private ImageView imageViewSplashScreen;
  
  private boolean isActionRunning = false;
  
  private boolean isFromFavouritesActivity = false;
  
  private boolean isLongPress = false;
  
  private Boolean isThereINET;
  
  private boolean isTutorialAvailable;
  
  private TextView lat;
  
  private LatLng latLngGlobal;
  
  private RelativeLayout lay_header;
  
  private ActionBarDrawerToggle leftActionBarDrawerToggle;
  
  private TomTomMapLeftNavDrawerListAdapter leftDrawerListAdapter;
  
  private ListView leftDrawerListView;
  
  private TypedArray leftDrawerMenuIcons;
  
  private ArrayList<TomTomMapLeftNavDrawerItem> leftNavDrawerItems;
  
  private LinearLayout lin_parent_container;
  
  private LinearLayout lin_share_top_menu_land;
  
  private LinearLayout lin_share_top_menu_portrait;
  
  private LinearLayout linearLayoutBottom;
  
  private boolean listCollapse = false;
  
  private TextView lon;
  
  Marker mCurrLocationMarker;
  
  private ExpandableResultsAdapter mExpandableListAdapter;
  
  private GoogleApiClient mGoogleApiClient;
  
  private ArrayList<HashMap<String, String>> mGoogleSearchResults;
  
  Location mLastLocation;
  
  LocationRequest mLocationRequest;
  
  private GoogleMap mMap;
  
  private RubenUltimaAlgorithm mRuAlgorithm;
  
  private SupportMapFragment mapFragment;
  
  private Enums.navigationProcess mapType;
  
  public Configuration myConfiguration;
  
  public boolean myLocationPressed = false;
  
  private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
      final ActivityMapViewerG this$0;
      
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (!ActivityMapViewerG.isRouteInProgress)
          Utilities.showNetworkServiceData((TextView)ActivityMapViewerG.this.findViewById(2131297057), (Context)ActivityMapViewerG.this, new TextView[0]); 
      }
    };
  
  private TextView nm;
  
  private ArrayList<HashMap<String, String>> placesListItems = new ArrayList<HashMap<String, String>>();
  
  private int position;
  
  private boolean pressTuto = true;
  
  private ProgressBar progressBarFindMe;
  
  private ProgressBar progressBarSendRoute;
  
  ProgressDialog progressDialog;
  
  private RelativeLayout rlContainerMenu;
  
  private RelativeLayout rlContainerMenuLans;
  
  private onstarApplication rtApp;
  
  private ImageView shareDest;
  
  private ImageView shareDest2;
  
  private ImageView shareLoc;
  
  private ImageView shareLoc2;
  
  private LinearLayout shareLocationBottomMenu;
  
  private LinearLayout shareLocationTopMenu;
  
  private LinearLayout shareLocationTopMenu2;
  
  private ShowViewElement show;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private TaskSet taskSet;
  
  private Typeface tf;
  
  private TextView tvDateInfo;
  
  private String typeFloating;
  
  private List<String> vehicle;
  
  private LinkedList<UserDevicesVO> vehicleListC = new LinkedList<UserDevicesVO>();
  
  private ListView vehicleListShare;
  
  private ViewGroup viewGroupTomTomSearchBar;
  
  static {
    isRouteInProgress = false;
    mCurrentLocation = null;
  }
  
  private void ActionActived() {
    try {
      Dialog dialog = Utilities.simpleDialog((Context)activity, null, global_popup_lbl_aviso_1, global_popup_lbl_accionencurso_1, true, global_popup_btn_ok_1, false, global_popup_btn_no_1, 20.0F, 20.0F);
      this.buttonOk = (Button)dialog.findViewById(2131296343);
      Button button = this.buttonOk;
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
    boolean bool = true;
    byte b1 = 1;
    byte b2 = 1;
    linkedHashMap2.put("water", Integer.valueOf(1));
    linkedHashMap2.put("hasmap", Integer.valueOf(0));
    if (paramLinkedHashMap != null) {
      int i = paramLinkedHashMap.size();
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i == 4) {
              Iterator<String> iterator = paramLinkedHashMap.keySet().iterator();
              for (b1 = b2; iterator.hasNext(); b1++) {
                String str = iterator.next();
                if (b1 == 3)
                  linkedHashMap1.put(Enums.Services.Favs.GetCodeString(), linkedHashMap2); 
                linkedHashMap1.put(str, paramLinkedHashMap.get(str));
              } 
            } 
          } else {
            Iterator<String> iterator = paramLinkedHashMap.keySet().iterator();
            for (b1 = bool; iterator.hasNext(); b1++) {
              String str = iterator.next();
              if (b1 == 3)
                linkedHashMap1.put(Enums.Services.Favs.GetCodeString(), linkedHashMap2); 
              linkedHashMap1.put(str, paramLinkedHashMap.get(str));
            } 
          } 
        } else {
          for (String str : paramLinkedHashMap.keySet()) {
            if (b1 == 2)
              linkedHashMap1.put(Enums.Services.Favs.GetCodeString(), linkedHashMap2); 
            linkedHashMap1.put(str, paramLinkedHashMap.get(str));
            b1++;
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
    if (this.mMap != null) {
      int j = (getResources().getDisplayMetrics()).widthPixels;
      int i = (getResources().getDisplayMetrics()).heightPixels;
      if ((getResources().getDisplayMetrics()).density >= 2.0D) {
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, j, i, 250));
      } else {
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, j, i, 50));
      } 
      if (l < 100L)
        this.mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0F), 1, null); 
    } 
  }
  
  private void centerMenu(int paramInt) {
    if (GlobalMembers.newMaps) {
      this.activityMapViewerView.getFindMeGroup().setVisibility(8);
      this.activityMapViewerView.getSendRouteGroup().setVisibility(8);
      this.activityMapViewerView.getFavsGroup().setVisibility(0);
      this.activityMapViewerView.getShareGroup().setVisibility(8);
      this.activityMapViewerView.getGoTorouteGroup().setVisibility(8);
    } else if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 4)
              this.activityMapViewerView.getGoTorouteGroup().setVisibility(8); 
          } else {
            this.activityMapViewerView.getShareGroup().setVisibility(8);
            this.activityMapViewerView.getGoTorouteGroup().setVisibility(8);
          } 
        } else {
          this.activityMapViewerView.getFavsGroup().setVisibility(8);
          this.activityMapViewerView.getShareGroup().setVisibility(8);
          this.activityMapViewerView.getGoTorouteGroup().setVisibility(8);
        } 
      } else {
        this.activityMapViewerView.getSendRouteGroup().setVisibility(8);
        this.activityMapViewerView.getFavsGroup().setVisibility(8);
        this.activityMapViewerView.getShareGroup().setVisibility(8);
        this.activityMapViewerView.getGoTorouteGroup().setVisibility(8);
      } 
    } else {
      this.activityMapViewerView.getAllMenu().setVisibility(8);
    } 
  }
  
  private void changeOrientation(Configuration paramConfiguration, boolean paramBoolean) {
    if (paramConfiguration.orientation == 2) {
      if (this.mapType.equals(Enums.navigationProcess.Navigation)) {
        this.frame_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, 0, 85.0F));
        this.bottom_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, 0, 15.0F));
        if (GlobalMembers.activeTutorial) {
          LinearLayout linearLayout = this.idTutorial;
          if (linearLayout != null) {
            linearLayout.setVisibility(8);
            ShowViewElement showViewElement = this.show;
            if (showViewElement != null)
              showViewElement.removeView((Activity)this, true); 
          } 
        } 
        RelativeLayout relativeLayout = this.rlContainerMenu;
        if (relativeLayout != null) {
          relativeLayout.setVisibility(8);
        } else {
          setRelativeFloatingMenu();
        } 
        relativeLayout = this.rlContainerMenuLans;
        if (relativeLayout != null)
          relativeLayout.setVisibility(0); 
        if (this.fab.isExpanded()) {
          this.fabLans.expand();
        } else {
          this.fabLans.collapse();
        } 
      } else {
        Enums.navigationProcess navigationProcess1 = this.mapType;
        if (navigationProcess1 != null && navigationProcess1.equals(Enums.navigationProcess.External)) {
          LinearLayout linearLayout = this.idTutorial;
          if (linearLayout != null && GlobalMembers.activeTutorial && linearLayout != null)
            linearLayout.setVisibility(8); 
          RelativeLayout relativeLayout = this.rlContainerMenu;
          if (relativeLayout != null)
            relativeLayout.setVisibility(8); 
          relativeLayout = this.rlContainerMenuLans;
          if (relativeLayout != null)
            relativeLayout.setVisibility(8); 
        } else if (this.mapType.equals(Enums.navigationProcess.FollowMe)) {
          LinearLayout linearLayout = this.idTutorial;
          if (linearLayout != null && GlobalMembers.activeTutorial && linearLayout != null) {
            linearLayout.setVisibility(8);
            ShowViewElement showViewElement = this.show;
            if (showViewElement != null)
              showViewElement.removeView((Activity)this, true); 
          } 
          RelativeLayout relativeLayout = this.rlContainerMenu;
          if (relativeLayout != null)
            relativeLayout.setVisibility(8); 
          relativeLayout = this.rlContainerMenuLans;
          if (relativeLayout != null)
            relativeLayout.setVisibility(8); 
        } else if (this.mapType.equals(Enums.navigationProcess.Mixed)) {
          LinearLayout linearLayout = this.idTutorial;
          if (linearLayout != null && GlobalMembers.activeTutorial && linearLayout != null)
            linearLayout.setVisibility(8); 
          RelativeLayout relativeLayout = this.rlContainerMenu;
          if (relativeLayout != null)
            relativeLayout.setVisibility(8); 
          relativeLayout = this.rlContainerMenuLans;
          if (relativeLayout != null)
            relativeLayout.setVisibility(8); 
        } 
      } 
    } else if (this.mapType.equals(Enums.navigationProcess.Navigation)) {
      this.frame_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, 0, 90.0F));
      this.bottom_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, 0, 10.0F));
      if (GlobalMembers.activeTutorial) {
        LinearLayout linearLayout = this.idTutorial;
        if (linearLayout != null)
          linearLayout.setVisibility(0); 
      } 
      RelativeLayout relativeLayout = this.rlContainerMenu;
      if (relativeLayout != null)
        relativeLayout.setVisibility(0); 
      relativeLayout = this.rlContainerMenuLans;
      if (relativeLayout != null) {
        relativeLayout.setVisibility(8);
      } else {
        setRelativeFloatingMenuLans();
      } 
      if (this.fabLans.isExpanded()) {
        this.fab.expand();
      } else {
        this.fab.collapse();
      } 
    } else {
      Enums.navigationProcess navigationProcess1 = this.mapType;
      if (navigationProcess1 != null && navigationProcess1.equals(Enums.navigationProcess.External)) {
        LinearLayout linearLayout = this.idTutorial;
        if (linearLayout != null && GlobalMembers.activeTutorial && linearLayout != null)
          linearLayout.setVisibility(8); 
        RelativeLayout relativeLayout = this.rlContainerMenu;
        if (relativeLayout != null)
          relativeLayout.setVisibility(8); 
        relativeLayout = this.rlContainerMenuLans;
        if (relativeLayout != null)
          relativeLayout.setVisibility(8); 
      } else if (this.mapType.equals(Enums.navigationProcess.FollowMe)) {
        LinearLayout linearLayout = this.idTutorial;
        if (linearLayout != null && GlobalMembers.activeTutorial && linearLayout != null)
          linearLayout.setVisibility(0); 
        RelativeLayout relativeLayout = this.rlContainerMenu;
        if (relativeLayout != null)
          relativeLayout.setVisibility(8); 
        relativeLayout = this.rlContainerMenuLans;
        if (relativeLayout != null)
          relativeLayout.setVisibility(8); 
      } else if (this.mapType.equals(Enums.navigationProcess.Mixed)) {
        LinearLayout linearLayout = this.idTutorial;
        if (linearLayout != null && GlobalMembers.activeTutorial && linearLayout != null)
          linearLayout.setVisibility(8); 
        RelativeLayout relativeLayout = this.rlContainerMenu;
        if (relativeLayout != null)
          relativeLayout.setVisibility(8); 
        relativeLayout = this.rlContainerMenuLans;
        if (relativeLayout != null)
          relativeLayout.setVisibility(8); 
      } 
    } 
  }
  
  private void closeLeftNavigationDrawer() {
    DrawerLayout drawerLayout = this.drawerLayout;
    if (drawerLayout != null)
      drawerLayout.closeDrawer(8388611); 
  }
  
  private void createNavigationBottons() {
    if (!this.isActionRunning)
      if (GlobalMembers.newMaps) {
        resetMenu();
        enableFavorites(Boolean.valueOf(true));
        setActionAnIcon(Enums.Services.Favs, imageButtonFavs, 3, 1);
        centerMenu(1);
      } else {
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
                setActionAnIcon(services, imageButtonFavs, b, j);
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
  }
  
  private void dialogBackToMainActivity() {
    try {
      String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_navegacion_1, 2131689929);
      String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_exit_lbl_deseasalirdelmodo_2, 2131690142);
      String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_navonstar, 2131165295);
      Dialog dialog = Utilities.simpleDialog((Context)activity, drawable, str3, str2, true, str1, false, global_popup_btn_no_1);
      this.buttonOk = (Button)dialog.findViewById(2131296343);
      Button button = this.buttonOk;
      View.OnClickListener onClickListener = new View.OnClickListener() {
          final ActivityMapViewerG this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            ActivityMapViewerG.this.disableOnFollowMeObjectListener();
            if (GlobalMembers.needFollowMeReview.booleanValue()) {
              FollowMeHandler.nullFollowMePoints();
              GlobalMembers.needFollowMeReview = Boolean.valueOf(false);
            } 
            dialog.dismiss();
            GlobalMembers.isFromShareFavActivity = false;
            GlobalMembers.isFromShareMarkerActivity = false;
            ActivityMapViewerG.this.finish();
          }
        };
      super(this, dialog);
      button.setOnClickListener(onClickListener);
      dialog.show();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: dialogBackALert", exception.getMessage());
    } 
  }
  
  private void dialogBackToMainSendToRoute() {
    try {
      String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_navegacion_1, 2131689929);
      String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_exit_lbl_deseasalirdelmodo_2, 2131690142);
      String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_si_1, 2131689952);
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_navonstar, 2131165295);
      Dialog dialog = Utilities.simpleDialog((Context)activity, drawable, str3, str1, true, str2, true, global_popup_btn_no_1);
      this.buttonOk = (Button)dialog.findViewById(2131296343);
      Button button1 = this.buttonOk;
      View.OnClickListener onClickListener2 = new View.OnClickListener() {
          final ActivityMapViewerG this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            if (GlobalMembers.needFollowMeReview.booleanValue()) {
              FollowMeHandler.nullFollowMePoints();
              GlobalMembers.needFollowMeReview = Boolean.valueOf(false);
            } 
            dialog.dismiss();
            GlobalMembers.isFromShareFavActivity = false;
            GlobalMembers.isFromShareMarkerActivity = false;
            ActivityMapViewerG.this.finish();
          }
        };
      super(this, dialog);
      button1.setOnClickListener(onClickListener2);
      this.buttonNOk = (Button)dialog.findViewById(2131296344);
      Button button2 = this.buttonNOk;
      View.OnClickListener onClickListener1 = new View.OnClickListener() {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        };
      super(this, dialog);
      button2.setOnClickListener(onClickListener1);
      dialog.show();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: dialogBackALert", exception.getMessage());
    } 
  }
  
  private void disableAllElements() {
    this.viewGroupTomTomSearchBar.setVisibility(8);
    this.imageButtonFindMe.setVisibility(8);
    this.imageButtonSendRoute.setVisibility(8);
    imageButtonFavs.setVisibility(8);
    this.imageButtonShareLocation.setVisibility(8);
    this.imageButtonGoToRoute.setVisibility(8);
    this.expListView.setVisibility(8);
    this.googSearch.setVisibility(8);
    this.imageButtonCenterLocation.setVisibility(8);
    if (GlobalMembers.activeTutorial && !this.isTutorialAvailable)
      this.idTutorial.setVisibility(8); 
    hideBottomElements();
    lockRighDrawer(true);
    this.frame_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1, 0.0F));
    RelativeLayout relativeLayout = this.rlContainerMenu;
    if (relativeLayout != null)
      relativeLayout.setVisibility(8); 
    relativeLayout = this.rlContainerMenuLans;
    if (relativeLayout != null)
      relativeLayout.setVisibility(8); 
  }
  
  private void disableBottomButtons(Enums.Services paramServices) {
    if (GlobalMembers.newMaps) {
      enableFavorites(Boolean.valueOf(false));
    } else {
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
                  if (services != paramServices && services != Enums.Services.Favs) {
                    setActionAnIcon(services, this.imageButtonGoToRoute, b, 0);
                    this.imageButtonGoToRoute.setClickable(false);
                  } else if (services != paramServices) {
                    setActionAnIcon(services, this.imageButtonGoToRoute, b, j);
                  }  
              } else if (services != paramServices && services != Enums.Services.Favs) {
                setActionAnIcon(services, this.imageButtonShareLocation, b, 0);
                this.imageButtonShareLocation.setClickable(false);
              } else if (services != paramServices) {
                setActionAnIcon(services, this.imageButtonShareLocation, b, j);
              } 
            } else if (services != paramServices && services != Enums.Services.Favs) {
              setActionAnIcon(services, imageButtonFavs, b, 0);
              imageButtonFavs.setClickable(false);
            } else if (services != paramServices) {
              setActionAnIcon(services, imageButtonFavs, b, j);
            } 
          } else if (services != paramServices && services != Enums.Services.Favs) {
            setActionAnIcon(services, this.imageButtonSendRoute, b, 0);
            this.imageButtonSendRoute.setClickable(false);
          } else if (services != paramServices) {
            setActionAnIcon(services, this.imageButtonSendRoute, b, j);
          } 
        } else if (services != paramServices && services != Enums.Services.Favs) {
          setActionAnIcon(services, this.imageButtonFindMe, b, 0);
          this.imageButtonFindMe.setClickable(false);
        } else if (services != paramServices) {
          setActionAnIcon(services, this.imageButtonFindMe, b, j);
        } 
      } 
      centerMenu(i);
    } 
  }
  
  private void disableElementsOnFindMe() {
    this.viewGroupTomTomSearchBar.setVisibility(8);
    this.imageButtonSendRoute.setVisibility(8);
    imageButtonFavs.setVisibility(8);
    this.imageButtonShareLocation.setVisibility(8);
    this.imageButtonGoToRoute.setVisibility(8);
    this.expListView.setVisibility(8);
    this.googSearch.setVisibility(8);
    this.imageButtonCenterLocation.setVisibility(8);
    hideBottomElements();
    lockRighDrawer(true);
    this.frame_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1, 0.0F));
    RelativeLayout relativeLayout = this.rlContainerMenu;
    if (relativeLayout != null)
      relativeLayout.setVisibility(8); 
    relativeLayout = this.rlContainerMenuLans;
    if (relativeLayout != null)
      relativeLayout.setVisibility(8); 
  }
  
  private void disableOnFollowMeObjectListener() {
    if (OnFollowMeListener.onFollowMeObjectListener != null) {
      Utilities.escribeArchivo("PMM FOLLOWME", "Destruye listener", "");
      OnFollowMeListener.onFollowMeObjectListener = null;
    } 
  }
  
  private void drawMarker(ArrayList<PointInfo> paramArrayList) {
    if (this.mMap != null && paramArrayList != null)
      for (byte b = 0; b < paramArrayList.size(); b++) {
        try {
          GoogleMap googleMap = this.mMap;
          MarkerOptions markerOptions = new MarkerOptions();
          this();
          markerOptions.position(((PointInfo)paramArrayList.get(b)).getLatLng());
          markerOptions.title("");
          this.mCurrLocationMarker = googleMap.addMarker(markerOptions);
          this.mMap.moveCamera(CameraUpdateFactory.newLatLng(((PointInfo)paramArrayList.get(b)).getLatLng()));
          enableSendRouteShareLocationGoToRoute();
        } catch (Exception exception) {
          exception.printStackTrace();
        } 
      }  
  }
  
  private void enableBottomButtons() {
    this.isActionRunning = false;
    createNavigationBottons();
    ArrayList<PointInfo> arrayList = this.currentMarkers;
    if (arrayList != null && arrayList.size() > 0)
      enableSendRouteShareLocationGoToRoute(); 
  }
  
  private void enableFavorites(Boolean paramBoolean) {
    Enums.Services.Favs.GetCodeString();
    if (paramBoolean.booleanValue()) {
      imageButtonFavs.setAlpha(1.0F);
      imageButtonFavs.setEnabled(true);
    } else {
      imageButtonFavs.setAlpha(CustomActionButton.WATER_MARK_ALPHA);
      imageButtonFavs.setEnabled(false);
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
        Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.nav_iconroutepnd, 2131165575);
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
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.nav_iconroutepnd_disable, 2131165576);
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
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.nav_icondirections, 2131165567);
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
  
  private void enableShareLocationElements() {
    if (Utilities.getCurrentScreenOrientation((Activity)activity) == 2) {
      this.lin_parent_container.setOrientation(0);
      this.lin_share_top_menu_land.setVisibility(0);
      this.lin_share_top_menu_portrait.setVisibility(8);
      this.frame_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -1, 50.0F));
      this.bottom_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -1, 50.0F));
    } else {
      this.lin_parent_container.setOrientation(1);
      this.lin_share_top_menu_land.setVisibility(8);
      this.lin_share_top_menu_portrait.setVisibility(0);
      this.frame_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, 0, 40.0F));
      this.bottom_container.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, 0, 60.0F));
    } 
    this.shareLocationBottomMenu.setVisibility(0);
    this.shareLocationTopMenu.setVisibility(0);
    this.shareLocationTopMenu2.setVisibility(0);
    if (ContextCompat.checkSelfPermission((Context)this, "android.permission.ACCESS_FINE_LOCATION") != 0 && ContextCompat.checkSelfPermission((Context)this, "android.permission.ACCESS_COARSE_LOCATION") != 0)
      return; 
    this.mMap.setMyLocationEnabled(true);
    this.mMap.getUiSettings().setMyLocationButtonEnabled(false);
    this.shareLoc.setEnabled(true);
    this.shareDest.setEnabled(true);
    this.shareLoc2.setEnabled(true);
    this.shareDest2.setEnabled(true);
    this.lay_header.setEnabled(true);
    this.lay_header.setClickable(true);
    this.vehicleListShare.setEnabled(true);
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
    String str3 = this.mRuAlgorithm.formatLinearDistance(d);
    String str2 = this.mRuAlgorithm.formatLinearDistanceWithString(d);
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    ArrayList<HashMap<Object, Object>> arrayList1 = new ArrayList();
    hashMap.put("reference", paramString1);
    hashMap.put(SearchCompleteTask.KEY_LATITUDE, paramString2);
    hashMap.put(SearchCompleteTask.KEY_LONGITUDE, paramString3);
    hashMap.put("name", paramString4);
    hashMap.put("address", paramString5);
    hashMap.put("distance", str3);
    hashMap.put("distanceString", str2);
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
    ExpandableResultsAdapter expandableResultsAdapter = new ExpandableResultsAdapter((Activity)activity, arrayList, arrayList1, new TomTomStartNavigation_Interface() {
          final ActivityMapViewerG this$0;
          
          public void onStartNavigation(String param1String1, String param1String2, String param1String3, String param1String4) {
            ActivityMapViewerG.this.saveRoute(param1String1, param1String2, param1String3, param1String4);
          }
        }bool1.booleanValue(), new ExpandableIcon_Interface() {
          final ActivityMapViewerG this$0;
          
          final double[] val$startLatLong;
          
          public void onExpand() {
            ActivityMapViewerG activityMapViewerG = ActivityMapViewerG.this;
            double[] arrayOfDouble = startLatLong;
            activityMapViewerG.updateGoogleSearchResultsDistances(true, arrayOfDouble[0], arrayOfDouble[1]);
          }
        });
    this.expListView.setAdapter((ExpandableListAdapter)expandableResultsAdapter);
    expandableResultsAdapter.notifyDataSetChanged();
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
    ArrayList<PointInfo> arrayList = this.currentMarkers;
    if (arrayList != null && arrayList.size() > 0) {
      if (i >= 10) {
        String str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.favoritos_lbl_excediolimite_2, 2131689817);
        DialogEmpty dialogEmpty = new DialogEmpty((Activity)activity, str);
        dialogEmpty.show();
        dialogEmpty.setOnDismissListener(new DialogInterface.OnDismissListener() {
              final ActivityMapViewerG this$0;
              
              public void onDismiss(DialogInterface param1DialogInterface) {
                ActivityMapViewerG.this.resetMapScreen();
                Intent intent = new Intent((Context)ActivityMapViewerG.activity, ActivityFavoritesHistory.class);
                intent.putExtra("TypeItem", (Serializable)Enums.TypeItem.NaviFavoritesHist);
                ActivityMapViewerG.activity.startActivityForResult(intent, 6);
                ActivityMapViewerG.access$6102(false);
              }
            });
      } else {
        saveFavourite();
      } 
    } else {
      resetMapScreen();
      isFavClicked = false;
      Intent intent = new Intent((Context)activity, ActivityFavoritesHistory.class);
      intent.putExtra("TypeItem", (Serializable)Enums.TypeItem.NaviFavoritesHist);
      activity.startActivityForResult(intent, 6);
    } 
  }
  
  private void findMeFunction() {
    if (ValidateTheftAuto())
      return; 
    if (!NetUtilities.validateNetwork((Context)this, false, true))
      return; 
    if (!MainActivity.onFindMe.booleanValue()) {
      MainActivity.onFindMe = Boolean.valueOf(true);
      generateFindMe(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId(), 0, this.currentFunction);
      this.isActionRunning = true;
      disableBottomButtons(Enums.Services.FindMe);
    } else {
      Toast.makeText((Context)activity, global_popup_lbl_accionencurso_1, 1).show();
    } 
  }
  
  private void generateAssistanceDialog() {
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
    String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_llamar_1, 2131689948);
    Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_btnasistenciamenu_onstar_dialog, 2131165443);
    final Dialog dialog = Utilities.simpleDialog((Context)this, null, str1, str3, true, str2, false, global_popup_btn_no_1);
    this.buttonOk = (Button)dialog.findViewById(2131296343);
    this.buttonNOk = (Button)dialog.findViewById(2131296344);
    this.buttonOk.setOnClickListener(new View.OnClickListener() {
          final ActivityMapViewerG this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            GlobalMembers.btnpress = 2;
            if (GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
              if (!MainActivity.isBtAndPlatinumConnected()) {
                Utilities.sendIntentCallAction((Context)ActivityMapViewerG.this);
              } else {
                if (!MainActivity.bEsperandoP8 && MainActivity.iPreviousCallIndex == 0 && MainActivity.bRelayReady) {
                  Intent intent = new Intent((Context)ActivityMapViewerG.this, ActivityCall.class);
                  intent.putExtra("Boton", "Asistencia");
                  ActivityMapViewerG.this.startActivityForResult(intent, 456);
                } 
                MainActivity.CallOrHangUp(Enums.Calls.AssistanceCallAndMessage);
              } 
            } else {
              Utilities.sendIntentCallAction((Context)ActivityMapViewerG.this);
            } 
            dialog.dismiss();
          }
        });
    this.buttonNOk.setOnClickListener(new View.OnClickListener(this) {
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
        (new CallPhone((Context)this)).Emergency();
      } else {
        if (((MainActivity.bEsperandoP8 ^ true) & MainActivity.bRelayReady) != 0) {
          Intent intent = new Intent((Context)this, ActivityCall.class);
          intent.putExtra("Boton", "Emergencia");
          startActivityForResult(intent, 123);
        } 
        MainActivity.CallOrHangUp(Enums.Calls.EmergencyCallAndMessage);
      } 
    } else {
      (new CallPhone((Context)this)).Emergency();
    } 
  }
  
  private void generateFindMe(String paramString1, int paramInt, final String execFromFunction) {
    String str2 = this.rtApp.getSessionKey();
    String str4 = this.rtApp.getUserAccessData()[1];
    String str3 = this.rtApp.getAccountID();
    String str5 = this.rtApp.getLocatorUserId();
    String str1 = Enums.Services.FindMe.GetCodeString();
    ActionsProcess actionsProcess = new ActionsProcess((Context)activity, this.progressBarFindMe, this.imageViewFindMe, str1, paramString1, (Activity)this);
    actionsProcess.setOnPostExecuteListener(new ActionsProcess.OnPostExecuteListener() {
          final ActivityMapViewerG this$0;
          
          final String val$execFromFunction;
          
          public void onPostExecuteListener(ActionResultVO param1ActionResultVO) {
            ActivityMapViewerG.access$6802(ActivityMapViewerG.this, false);
            ActivityMapViewerG.this.enableBottomButtons();
            ActivityMapViewerG.this.onPostFindMeFromNavigation(param1ActionResultVO, execFromFunction);
            MainActivity.onFindMe = Boolean.valueOf(false);
          }
        });
    actionsProcess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str2, str4, str3, str5, str1, "" });
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
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(str1.substring(0, 83));
          stringBuilder1.append("...");
          str3 = stringBuilder1.toString();
        } 
      } 
      this.isActionRunning = true;
      disableBottomButtons(Enums.Services.SendPNDNavigationCommand);
      double[] arrayOfDouble = new double[2];
      arrayOfDouble[0] = paramDouble1;
      arrayOfDouble[1] = paramDouble2;
      paramString = this.rtApp.getSessionKey();
      String str5 = this.rtApp.getUserAccessData()[1];
      str1 = this.rtApp.getAccountID();
      String str6 = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId();
      String str4 = this.rtApp.getLocatorUserId();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(arrayOfDouble[1]);
      stringBuilder.append("");
      String str8 = stringBuilder.toString();
      stringBuilder = new StringBuilder();
      stringBuilder.append(arrayOfDouble[0]);
      stringBuilder.append("");
      String str7 = String.format("%s|%s|%s|3", new Object[] { str8, stringBuilder.toString(), str3 });
      ActionsProcess actionsProcess = new ActionsProcess((Context)activity, this.progressBarSendRoute, this.imageViewSendRoute, str2, str6, (Activity)this);
      actionsProcess.setAddress(str3);
      actionsProcess.setOnPostExecuteListener(new ActionsProcess.OnPostExecuteListener() {
            final ActivityMapViewerG this$0;
            
            public void onPostExecuteListener(ActionResultVO param1ActionResultVO) {
              ActivityMapViewerG.onSendRoute = false;
              ActivityMapViewerG.this.enableBottomButtons();
            }
          });
      actionsProcess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { paramString, str5, str1, str4, str7, "" });
    } 
  }
  
  public static ActivityMapViewerG getActivity() {
    return activity;
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
        if (intent.resolveActivity(getPackageManager()) != null)
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
    this.imageButtonFindMe = this.activityMapViewerView.getImageButtonFindMe();
    this.imageButtonSendRoute = this.activityMapViewerView.getImageButtonSendRoute();
    this.imageButtonShareLocation = this.activityMapViewerView.getImageButtonShareLocation();
    this.imageButtonGoToRoute = this.activityMapViewerView.getImageButtonGoToRoute();
    imageButtonFavs = this.activityMapViewerView.getImageButtonFavs();
  }
  
  private int[] latLngToMicroDegrees(double paramDouble1, double paramDouble2) {
    return new int[] { (int)(paramDouble1 * 1000000.0D), (int)(paramDouble2 * 1000000.0D) };
  }
  
  private void onPostFindMeFromNavigation(ActionResultVO paramActionResultVO, String paramString) {
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
      if (paramString.equals(this.currentFunction)) {
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
              showViewElement.removeView((Activity)this, false); 
          } 
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
    RelativeLayout relativeLayout = this.rlContainerMenu;
    if (relativeLayout != null)
      relativeLayout.setVisibility(0); 
    GlobalMembers.objSendFavoritesMessage = null;
    this.isFromFavouritesActivity = false;
    this.isLongPress = false;
    this.destinationName = null;
    this.destinationAddress = null;
    GlobalMembers.isFromShareMarkerActivity = false;
    GlobalMembers.isFromShareFavActivity = false;
    if (this.currentFunction.equals(TAG) && GlobalMembers.activeTutorial && this.mapType.equals(Enums.navigationProcess.Navigation))
      setLandscapeAndPortraitConfiguration(); 
    enableFavorites(Boolean.valueOf(true));
  }
  
  private void resetMenu() {
    if (GlobalMembers.newMaps) {
      this.activityMapViewerView.getAllMenu().setVisibility(0);
      this.activityMapViewerView.getFindMeGroup().setVisibility(8);
      this.activityMapViewerView.getSendRouteGroup().setVisibility(8);
      this.activityMapViewerView.getFavsGroup().setVisibility(0);
      this.activityMapViewerView.getShareGroup().setVisibility(8);
      this.activityMapViewerView.getGoTorouteGroup().setVisibility(8);
    } else {
      this.activityMapViewerView.getAllMenu().setVisibility(0);
      this.activityMapViewerView.getFindMeGroup().setVisibility(0);
      this.activityMapViewerView.getSendRouteGroup().setVisibility(0);
      this.activityMapViewerView.getFavsGroup().setVisibility(0);
      this.activityMapViewerView.getShareGroup().setVisibility(0);
      this.activityMapViewerView.getGoTorouteGroup().setVisibility(0);
    } 
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
        startActivityForResult(intent, 6);
      } 
    } catch (Exception exception) {
      isFavClicked = false;
      Utilities.escribeArchivo(TAG, "Error", "Couldn't start activity");
    } 
  }
  
  private void saveRoute(String paramString1, String paramString2, String paramString3, String paramString4) {
    try {
      Utilities.escribeArchivo(TAG, "save route", "SaveRoute function");
      double[] arrayOfDouble = new double[2];
      arrayOfDouble[0] = Double.parseDouble(paramString3);
      arrayOfDouble[1] = Double.parseDouble(paramString4);
      DBFunctions dBFunctions = this.dbFunctions;
      paramString3 = this.rtApp.getDeviceTypeId().toString();
      paramString4 = Enums.TypeItem.Historical.toString();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(arrayOfDouble[0]);
      stringBuilder.append(",");
      stringBuilder.append(arrayOfDouble[1]);
      dBFunctions.addFavoriteHistory("", paramString1, paramString2, paramString3, paramString4, "", stringBuilder.toString(), "0", "");
    } catch (NumberFormatException numberFormatException) {
      Utilities.escribeArchivo(TAG, "save route", numberFormatException.getMessage());
    } 
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
    if (!NetUtilities.validateNetwork((Context)this, false, true))
      return; 
    ArrayList<PointInfo> arrayList = this.currentMarkers;
    if (arrayList != null && arrayList.size() > 0) {
      if (ValidateTheftAuto())
        return; 
      if (!onSendRoute) {
        onSendRoute = true;
        arrayList = this.currentMarkers;
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
        },  (Context)this);
  }
  
  private void setActionAnIcon(Enums.Services paramServices, ImageButton paramImageButton, int paramInt1, int paramInt2) {
    if (paramInt2 != 0) {
      if (paramInt2 != 1) {
        if (paramInt2 == 2) {
          paramImageButton.setVisibility(4);
          paramImageButton.setClickable(false);
        } 
      } else {
        paramImageButton.setAlpha(1.0F);
        paramImageButton.setVisibility(0);
        paramImageButton.setClickable(true);
      } 
    } else {
      paramImageButton.setAlpha(CustomActionButton.WATER_MARK_ALPHA);
      paramImageButton.setClickable(false);
    } 
    switch (paramServices) {
      case Favs:
        paramImageButton.setBackground(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.nav_iconfavoff, 2131165570));
        paramImageButton.setOnClickListener(new View.OnClickListener() {
              final ActivityMapViewerG this$0;
              
              public void onClick(View param1View) {
                if (!ActivityMapViewerG.isFavClicked) {
                  ActivityMapViewerG.access$6102(true);
                  ActivityMapViewerG.this.favsFunction();
                } 
              }
            });
        break;
      case FindMe:
        paramImageButton.setBackground(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.nav_iconpinoff, 2131165573));
        paramImageButton.setOnClickListener(new View.OnClickListener() {
              final ActivityMapViewerG this$0;
              
              public void onClick(View param1View) {
                if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue()) {
                  ActivityMapViewerG.this.findMeFunction();
                } else {
                  ActivityMapViewerG.this.ActionActived();
                } 
              }
            });
        if (paramInt1 != 1) {
          if (paramInt1 != 2) {
            if (paramInt1 != 3) {
              if (paramInt1 != 4) {
                if (paramInt1 != 5)
                  break; 
                this.progressBarFindMe = this.activityMapViewerView.getProgressBarGoToRoute();
                this.imageViewFindMe = this.activityMapViewerView.getImageViewGoToRoute();
                break;
              } 
              this.progressBarFindMe = this.activityMapViewerView.getProgressBarShare();
              this.imageViewFindMe = this.activityMapViewerView.getImageViewShare();
              break;
            } 
            this.progressBarFindMe = this.activityMapViewerView.getProgressBarFavs();
            this.imageViewFindMe = this.activityMapViewerView.getImageViewfavs();
            break;
          } 
          this.progressBarFindMe = this.activityMapViewerView.getProgressBarSendRoute();
          this.imageViewFindMe = this.activityMapViewerView.getImageViewSendRoute();
          break;
        } 
        this.progressBarFindMe = this.activityMapViewerView.getProgressBarFindMe();
        this.imageViewFindMe = this.activityMapViewerView.getImageViewFindMe();
        break;
      case SendPNDNavigationCommand:
        paramImageButton.setBackground(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.nav_iconroutepnd_disable, 2131165576));
        paramImageButton.setOnClickListener(new View.OnClickListener() {
              final ActivityMapViewerG this$0;
              
              public void onClick(View param1View) {
                ActivityMapViewerG.this.sendRouteFunction();
              }
            });
        if (paramInt1 != 1) {
          if (paramInt1 != 2) {
            if (paramInt1 != 3) {
              if (paramInt1 != 4) {
                if (paramInt1 != 5)
                  break; 
                this.progressBarSendRoute = this.activityMapViewerView.getProgressBarGoToRoute();
                this.imageViewSendRoute = this.activityMapViewerView.getImageViewGoToRoute();
                break;
              } 
              this.progressBarSendRoute = this.activityMapViewerView.getProgressBarShare();
              this.imageViewSendRoute = this.activityMapViewerView.getImageViewShare();
              break;
            } 
            this.progressBarSendRoute = this.activityMapViewerView.getProgressBarFavs();
            this.imageViewSendRoute = this.activityMapViewerView.getImageViewfavs();
            break;
          } 
          this.progressBarSendRoute = this.activityMapViewerView.getProgressBarSendRoute();
          this.imageViewSendRoute = this.activityMapViewerView.getImageViewSendRoute();
          break;
        } 
        this.progressBarSendRoute = this.activityMapViewerView.getProgressBarFindMe();
        this.imageViewSendRoute = this.activityMapViewerView.getImageViewFindMe();
        break;
      case NavigationWithTraffic:
        paramImageButton.setBackground(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.nav_icondirections_disable, 2131165568));
        paramImageButton.setOnClickListener(new View.OnClickListener() {
              final ActivityMapViewerG this$0;
              
              public void onClick(View param1View) {
                ActivityMapViewerG.this.gotoRouteFunction();
              }
            });
        break;
      case ActionShare:
        paramImageButton.setBackground(Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.nav_iconshare_new, 2131165577));
        paramImageButton.setOnClickListener(new View.OnClickListener() {
              final ActivityMapViewerG this$0;
              
              public void onClick(View param1View) {
                ActivityMapViewerG.this.shareLocationFunction();
              }
            });
        break;
    } 
  }
  
  private void setBottomContainer() {
    this.bottom_container = this.activityMapViewerView.getBottomContainer();
  }
  
  private void setButtonToggleLeftNavigationDrawer() {
    this.buttonToggleLeftNavigationDrawer = (Button)this.viewGroupTomTomSearchBar.findViewById(2131296633);
    this.buttonToggleLeftNavigationDrawer.setOnClickListener(new View.OnClickListener() {
          final ActivityMapViewerG this$0;
          
          public void onClick(View param1View) {
            if (!ActivityMapViewerG.onSendRoute) {
              ActivityMapViewerG.this.openLeftNavigationDrawer();
            } else {
              ActivityMapViewerG.this.setActionButtons();
            } 
          }
        });
  }
  
  private void setDrawerLayout() {
    this.drawerLayout = this.activityMapViewerView.getDrawerLayout();
  }
  
  private void setEditTextSearch() {
    this.expListView = this.activityMapViewerView.getExpandableListViewSearch();
    this.expListView.setGroupIndicator(null);
    this.expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(this) {
          public boolean onGroupClick(ExpandableListView param1ExpandableListView, View param1View, int param1Int, long param1Long) {
            return false;
          }
        });
    this.expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
          final ActivityMapViewerG this$0;
          
          public void onGroupCollapse(int param1Int) {
            ActivityMapViewerG.this.viewGroupTomTomSearchBar.setVisibility(0);
          }
        });
    this.expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener(this) {
          public void onGroupExpand(int param1Int) {}
        });
    this.expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
          final ActivityMapViewerG this$0;
          
          public boolean onChildClick(ExpandableListView param1ExpandableListView, View param1View, int param1Int1, int param1Int2, long param1Long) {
            boolean bool = ActivityMapViewerG.this.listCollapse;
            param1Int2 = 0;
            if (!bool) {
              ActivityMapViewerG.access$2302(ActivityMapViewerG.this, true);
              ActivityMapViewerG.this.mMap.clear();
              ActivityMapViewerG.access$2502(ActivityMapViewerG.this, false);
              ActivityMapViewerG.access$2602(ActivityMapViewerG.this, (TextView)param1View.findViewById(2131296683));
              ActivityMapViewerG.access$2702(ActivityMapViewerG.this, (TextView)param1View.findViewById(2131296862));
              ActivityMapViewerG.access$2802(ActivityMapViewerG.this, (TextView)param1View.findViewById(2131296363));
              ActivityMapViewerG.access$2902(ActivityMapViewerG.this, (TextView)param1View.findViewById(2131296921));
              String str2 = ActivityMapViewerG.this.nm.getText().toString();
              String str1 = str2;
              if (str2 != null) {
                str1 = str2;
                if (str2.contains(", ."))
                  str1 = Utilities.getStringFromConfigList((Context)ActivityMapViewerG.getActivity(), ActivityMapViewerG.this.stringsResourcesVO.navigation_main_lbl_sindireccion, 2131690170); 
              } 
              double d2 = Double.parseDouble(ActivityMapViewerG.this.lat.getText().toString());
              double d1 = Double.parseDouble(ActivityMapViewerG.this.lon.getText().toString());
              if (d2 != 0.0D) {
                ActivityMapViewerG.this.expListView.collapseGroup(param1Int1);
                ActivityMapViewerG activityMapViewerG2 = ActivityMapViewerG.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(d2);
                String str = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(d1);
                activityMapViewerG2.fakeGoogleSearchResult("REFERENCE", str, stringBuilder.toString(), str1, ActivityMapViewerG.this.addr.getText().toString(), true, false);
                GlobalMembers.googleSearchTitleToFavorite = str1;
                if (!ActivityMapViewerG.this.typeFloating.equalsIgnoreCase("")) {
                  int i = 0;
                  param1Int1 = param1Int2;
                  for (param1Int2 = i; param1Int1 < ActivityMapViewerG.this.placesListItems.size(); param1Int2 = i) {
                    str1 = (String)((HashMap)ActivityMapViewerG.this.placesListItems.get(param1Int1)).get(SearchCompleteTask.KEY_LATITUDE);
                    String str3 = (String)((HashMap)ActivityMapViewerG.this.placesListItems.get(param1Int1)).get(SearchCompleteTask.KEY_LONGITUDE);
                    ActivityMapViewerG.this.controller.setMarkers(Double.parseDouble(str1), Double.parseDouble(str3), ActivityMapViewerG.this.typeFloating, param1Int1, (Activity)ActivityMapViewerG.activity);
                    i = param1Int2;
                    if (str1.equalsIgnoreCase(ActivityMapViewerG.this.lat.getText().toString())) {
                      i = param1Int2;
                      if (str3.equalsIgnoreCase(ActivityMapViewerG.this.lon.getText().toString()))
                        i = param1Int1; 
                    } 
                    param1Int1++;
                  } 
                  ActivityMapViewerG.this.controller.setMarkers(d2, d1, "", param1Int2, (Activity)ActivityMapViewerG.activity);
                } else {
                  ActivityMapViewerG.this.controller.setMarkers(d2, d1, "", -1, (Activity)ActivityMapViewerG.activity);
                } 
                ActivityMapViewerG activityMapViewerG1 = ActivityMapViewerG.this;
                activityMapViewerG1.setMarkersMap(d2, d1, activityMapViewerG1.addr.getText().toString());
                return true;
              } 
            } 
            return false;
          }
        });
    this.expListView.setOnScrollListener(new AbsListView.OnScrollListener(this) {
          public void onScroll(AbsListView param1AbsListView, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onScrollStateChanged(AbsListView param1AbsListView, int param1Int) {}
        });
    this.googleSearchResults_interface = new GoogleSearchResults_Interface() {
        final ActivityMapViewerG this$0;
        
        public void onSuccess(ArrayList<HashMap<String, String>> param1ArrayList, ExpandableResultsAdapter param1ExpandableResultsAdapter) {
          ActivityMapViewerG.access$3602(ActivityMapViewerG.this, param1ArrayList);
          ActivityMapViewerG.access$3702(ActivityMapViewerG.this, param1ExpandableResultsAdapter);
          if (ActivityMapViewerG.this.mGoogleSearchResults != null && ActivityMapViewerG.this.mGoogleSearchResults.size() == 1)
            ActivityMapViewerG.this.showMarquerOnResult(param1ArrayList); 
        }
      };
    this.tf = onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisRegular);
    this.googSearch = this.activityMapViewerView.getEditTextGoogleSearch();
    this.googSearch.setTypeface(this.tf);
    this.googSearch.setOnClickListener(new View.OnClickListener() {
          final ActivityMapViewerG this$0;
          
          public void onClick(View param1View) {
            ActivityMapViewerG.this.resetListAndMap();
            ActivityMapViewerG.access$3802(ActivityMapViewerG.this, false);
            ActivityMapViewerG.access$2502(ActivityMapViewerG.this, false);
            ActivityMapViewerG.access$2302(ActivityMapViewerG.this, false);
            if (ActivityMapViewerG.saveEdittextsearch != null && ActivityMapViewerG.saveEdittextsearch.size() > 0) {
              ArrayAdapter arrayAdapter = new ArrayAdapter((Context)ActivityMapViewerG.activity, 2131427485, 16908308, ActivityMapViewerG.saveEdittextsearch);
              ActivityMapViewerG.lvSavedEditSearch.setAdapter((ListAdapter)arrayAdapter);
              if (ActivityMapViewerG.lvSavedEditSearch.getVisibility() == 8)
                ActivityMapViewerG.lvSavedEditSearch.setVisibility(0); 
            } 
          }
        });
    this.googSearch.setOnKeyListener(new View.OnKeyListener() {
          final ActivityMapViewerG this$0;
          
          public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
            ActivityMapViewerG.this.resetListAndMap();
            ActivityMapViewerG.access$3802(ActivityMapViewerG.this, false);
            ActivityMapViewerG.access$2502(ActivityMapViewerG.this, false);
            ActivityMapViewerG.access$2302(ActivityMapViewerG.this, false);
            ActivityMapViewerG activityMapViewerG = ActivityMapViewerG.this;
            ActivityMapViewerG.access$4002(activityMapViewerG, Boolean.valueOf(NetUtilities.validateNetwork(activityMapViewerG.getApplicationContext(), false)));
            String str = Utilities.getStringFromConfigList((Context)ActivityMapViewerG.getActivity(), ActivityMapViewerG.this.stringsResourcesVO.global_lbl_fallaredbusqueda, 2131689921);
            ActivityMapViewerG.access$3302(ActivityMapViewerG.this, "");
            if (param1Int == 66 && param1KeyEvent.getAction() == 0) {
              if (ActivityMapViewerG.this.googSearch.getText().toString().equals("") || ActivityMapViewerG.this.googSearch.getText().toString().equals(null)) {
                try {
                  String str1 = Utilities.getStringFromConfigList((Context)ActivityMapViewerG.getActivity(), ActivityMapViewerG.this.stringsResourcesVO.navigation_lbl_busquedaalertamensaje_1, 2131690147);
                  DialogEmpty dialogEmpty = new DialogEmpty();
                  this(str1);
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
                  Utilities.escribeArchivo(ActivityMapViewerG.TAG, "Error: onPostExecute", exception.getMessage());
                } 
                return false;
              } 
              if (ActivityMapViewerG.this.controlViews == null) {
                if (ActivityMapViewerG.this.isThereINET.booleanValue()) {
                  try {
                    if (ActivityMapViewerG.this.latLngGlobal != null) {
                      Location location = new Location();
                      this(URLEncoder.encode(ActivityMapViewerG.this.googSearch.getText().toString(), "utf-8"));
                      ActivityMapViewerG.access$4402(location);
                      ActivityMapViewerG.mCurrentLocation.setLatitude(ActivityMapViewerG.this.latLngGlobal.latitude);
                      ActivityMapViewerG.mCurrentLocation.setLongitude(ActivityMapViewerG.this.latLngGlobal.longitude);
                      ActivityMapViewerG activityMapViewerG1 = ActivityMapViewerG.this;
                      SearchCompleteTask searchCompleteTask = new SearchCompleteTask();
                      this(ActivityMapViewerG.this.googleSearchResults_interface, ActivityMapViewerG.this.mMap, "", ActivityMapViewerG.this.expListView, ActivityMapViewerG.this.controller);
                      ActivityMapViewerG.access$4202(activityMapViewerG1, searchCompleteTask);
                      ActivityMapViewerG.this.controlViews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Location[] { ActivityMapViewerG.access$4400() });
                    } 
                  } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                  } 
                } else {
                  Toast.makeText((Context)ActivityMapViewerG.activity, (CharSequence)unsupportedEncodingException, 1).show();
                } 
              } else if (ActivityMapViewerG.this.isThereINET.booleanValue()) {
                try {
                  if (ActivityMapViewerG.this.latLngGlobal != null) {
                    Location location = new Location();
                    this(URLEncoder.encode(ActivityMapViewerG.this.googSearch.getText().toString(), "utf-8"));
                    ActivityMapViewerG.access$4402(location);
                    location = new Location();
                    this(ActivityMapViewerG.this.googSearch.getText().toString());
                    ActivityMapViewerG.access$4402(location);
                    ActivityMapViewerG.mCurrentLocation.setLatitude(ActivityMapViewerG.this.latLngGlobal.latitude);
                    ActivityMapViewerG.mCurrentLocation.setLongitude(ActivityMapViewerG.this.latLngGlobal.longitude);
                    ActivityMapViewerG activityMapViewerG1 = ActivityMapViewerG.this;
                    SearchCompleteTask searchCompleteTask = new SearchCompleteTask();
                    this(ActivityMapViewerG.this.googleSearchResults_interface, ActivityMapViewerG.this.mMap, "", ActivityMapViewerG.this.expListView, ActivityMapViewerG.this.controller);
                    ActivityMapViewerG.access$4202(activityMapViewerG1, searchCompleteTask);
                    ActivityMapViewerG.this.controlViews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Location[] { ActivityMapViewerG.access$4400() });
                  } 
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                  unsupportedEncodingException.printStackTrace();
                } 
              } else {
                Toast.makeText((Context)ActivityMapViewerG.activity, (CharSequence)unsupportedEncodingException, 1).show();
              } 
              str = ActivityMapViewerG.this.googSearch.getText().toString();
              if (str != null && !str.equals("") && !ActivityMapViewerG.this.existInsavedList(str))
                ActivityMapViewerG.this.saveTempSearch(str); 
              ((InputMethodManager)ActivityMapViewerG.this.getSystemService("input_method")).hideSoftInputFromWindow(ActivityMapViewerG.this.googSearch.getApplicationWindowToken(), 2);
              if (ActivityMapViewerG.lvSavedEditSearch.getVisibility() == 0)
                ActivityMapViewerG.lvSavedEditSearch.setVisibility(8); 
              if (ActivityMapViewerG.this.expListView != null) {
                if (ActivityMapViewerG.this.expListView.getVisibility() == 8)
                  ActivityMapViewerG.this.expListView.setVisibility(0); 
                ActivityMapViewerG.access$4202(ActivityMapViewerG.this, null);
              } 
              return true;
            } 
            return false;
          }
        });
    this.googSearch.requestFocus();
    lvSavedEditSearch = this.activityMapViewerView.getListViewSavedItems();
    lvSavedEditSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          final ActivityMapViewerG this$0;
          
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            Object object = param1AdapterView.getItemAtPosition(param1Int);
            ActivityMapViewerG.this.googSearch.setText(object.toString());
            ActivityMapViewerG.lvSavedEditSearch.setVisibility(8);
            ActivityMapViewerG.access$2502(ActivityMapViewerG.this, false);
          }
        });
  }
  
  private void setFavouriteMarkerIfExists() {
    PointInfo pointInfo;
    new MarkerOrFavoritePointVO(0, 0, "", "");
    MarkerOrFavoritePointVO markerOrFavoritePointVO = GlobalMembers.markerOrFavoritePointVO;
    GlobalMembers.isMarker = true;
    Object object = GlobalMembers.objSendFavoritesMessage;
    if (object != null && this.isFromFavouritesActivity) {
      this.myLocationPressed = false;
      FavoritesHistoryVO favoritesHistoryVO = (FavoritesHistoryVO)object;
      object = favoritesHistoryVO.getLatlng().split(",");
      String str1 = favoritesHistoryVO.getAddress();
      String str2 = favoritesHistoryVO.getName();
      String str3 = this.destinationName;
      if (str3 == null) {
        this.destinationName = str2;
        GlobalMembers.googleSearchTitleToFavorite = str2;
      } else if (str3.equalsIgnoreCase(str2)) {
        this.destinationName = str2;
        GlobalMembers.googleSearchTitleToFavorite = str2;
      } 
      this.destinationAddress = str1;
      if (object != null && object.length == 2) {
        int i;
        resetListAndMap();
        if (this.TypeMarker == 1) {
          i = 2131165644;
        } else {
          i = 2131165642;
        } 
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), i);
        Matrix matrix = new Matrix();
        matrix.postScale(0.5F, 0.5F);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));
        LatLng latLng = new LatLng(Double.parseDouble((String)object[0]), Double.parseDouble((String)object[1]));
        GoogleMap googleMap = this.mMap;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("");
        markerOptions.icon(bitmapDescriptor);
        this.mCurrLocationMarker = googleMap.addMarker(markerOptions);
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0F));
        this.currentMarkers.clear();
        pointInfo = new PointInfo(this.destinationAddress, this.destinationName, latLng, null);
        this.currentMarkers.add(pointInfo);
        fakeGoogleSearchResult("REFERENCE", (String)object[0], (String)object[1], this.destinationName, this.destinationAddress, false, false);
        this.expListView.setEnabled(false);
      } 
    } else if (GlobalMembers.isFromShareFavActivity && ((MarkerOrFavoritePointVO)pointInfo).latitudeMicroDegrees != 0 && ((MarkerOrFavoritePointVO)pointInfo).longitudeMicroDegrees != 0) {
      this.mMap.clear();
    } 
  }
  
  private void setFloatingButton() {
    this.fab = this.activityMapViewerView.getFloating();
  }
  
  private void setFloatingButtonLans() {
    this.fabLans = this.activityMapViewerView.getFloatingLans();
  }
  
  private void setFrameContainer() {
    this.frame_container = this.activityMapViewerView.getFrameContainer();
  }
  
  private void setImageButtonCenterLocation() {
    this.imageButtonCenterLocation = this.activityMapViewerView.getImageButtonCenterLocation();
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)activity, DrawableResourcesVO.button_center_location, 2131165357);
    this.imageButtonCenterLocation.setImageDrawable(drawable);
    this.imageButtonCenterLocation.setOnClickListener(new View.OnClickListener() {
          final ActivityMapViewerG this$0;
          
          public void onClick(View param1View) {
            if (!((LocationManager)ActivityMapViewerG.activity.getSystemService("location")).isProviderEnabled("gps")) {
              ActivityMapViewerG.this.dialogNoGPSActive();
            } else {
              ActivityMapViewerG activityMapViewerG = ActivityMapViewerG.this;
              activityMapViewerG.myLocationPressed = true;
              activityMapViewerG.resetMapScreen();
            } 
          }
        });
  }
  
  private void setImageViewFindMe() {
    this.activityMapViewerView.getShareImageViewFindMe();
  }
  
  private void setImageViewSplashScreen() {
    this.imageViewSplashScreen = this.activityMapViewerView.getSplashScreen();
  }
  
  private void setLandscapeAndPortraitConfiguration() {
    changeOrientation(getResources().getConfiguration(), false);
  }
  
  private void setLayoutVehicleHeader() {
    this.activityMapViewerView.getRelativeLayoutVehiclesHeader();
  }
  
  private void setLeftNavigationDrawer(Bundle paramBundle) {
    getResources().getStringArray(2130837510);
    this.leftDrawerMenuIcons = getResources().obtainTypedArray(2130837508);
    this.leftDrawerListView = (ListView)findViewById(2131296795);
    Drawable drawable3 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.iconfavorito_onstar_left, this.leftDrawerMenuIcons.getResourceId(1, -1));
    Drawable drawable1 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.btn_menu_asistencia, this.leftDrawerMenuIcons.getResourceId(2, -1));
    Drawable drawable2 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.btt_sos_withe_menu_left, this.leftDrawerMenuIcons.getResourceId(3, -1));
    String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_fav_1, 2131689923);
    String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_emergencia_1, 2131689919);
    this.leftNavDrawerItems = new ArrayList<TomTomMapLeftNavDrawerItem>();
    this.leftNavDrawerItems.add(new TomTomMapLeftNavDrawerItem(str3, drawable3));
    this.leftNavDrawerItems.add(new TomTomMapLeftNavDrawerItem(str2, drawable1));
    this.leftNavDrawerItems.add(new TomTomMapLeftNavDrawerItem(str1, drawable2));
    this.leftDrawerMenuIcons.recycle();
    this.leftDrawerListView.setOnItemClickListener(new LeftSlideMenuClickListener());
    this.leftDrawerListAdapter = new TomTomMapLeftNavDrawerListAdapter(getApplicationContext(), this.leftNavDrawerItems);
    this.leftDrawerListView.setAdapter((ListAdapter)this.leftDrawerListAdapter);
    this.leftActionBarDrawerToggle = new ActionBarDrawerToggle((Activity)this, this.drawerLayout, 2131165484, 2131689666, 2131689666) {
        final ActivityMapViewerG this$0;
        
        public void onDrawerClosed(View param1View) {
          ActivityMapViewerG.this.invalidateOptionsMenu();
        }
        
        public void onDrawerOpened(View param1View) {
          ActivityMapViewerG.this.invalidateOptionsMenu();
        }
        
        public void onDrawerSlide(View param1View, float param1Float) {
          super.onDrawerSlide(param1View, param1Float);
        }
      };
    this.drawerLayout.setDrawerListener((DrawerLayout.DrawerListener)this.leftActionBarDrawerToggle);
  }
  
  private void setLinearLayoutBottom() {
    this.linearLayoutBottom = this.activityMapViewerView.getLinearLayoutBottomMenu();
  }
  
  private void setLinearShareTopMenuLand() {
    this.lin_share_top_menu_land = this.activityMapViewerView.getLinearShareTopMenuLand();
  }
  
  private void setLinearShareTopMenuPortrait() {
    this.lin_share_top_menu_portrait = this.activityMapViewerView.getLinearShareTopMenuPortrait();
  }
  
  private void setListViewVehicles() {
    this.vehicleListShare = this.activityMapViewerView.getListViewVehicles();
    this.vehicleListShare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          final ActivityMapViewerG this$0;
          
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            for (param1Int = 0; param1Int < ActivityMapViewerG.this.rtApp.getmDeviceUserList().size(); param1Int++) {
              ActivityMapViewerG activityMapViewerG = ActivityMapViewerG.this;
              View view = activityMapViewerG.getViewByPosition(param1Int, activityMapViewerG.vehicleListShare);
              ActivityMapViewerG.access$802(ActivityMapViewerG.this, (TextView)view.findViewById(2131296491));
              ActivityMapViewerG.access$902(ActivityMapViewerG.this, (TextView)view.findViewById(2131297145));
              ActivityMapViewerG.access$1002(ActivityMapViewerG.this, (TextView)view.findViewById(2131296367));
              ActivityMapViewerG.this.dateShare.setText("");
              ActivityMapViewerG.this.tvDateInfo.setText("");
              ActivityMapViewerG.this.addresslistshare.setText("");
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
    //   18: ifnull -> 462
    //   21: aload_1
    //   22: getstatic com/roadtrack/onstar/Enums$navigationProcess.External : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   25: invokevirtual equals : (Ljava/lang/Object;)Z
    //   28: ifeq -> 462
    //   31: getstatic com/roadtrack/onstar/BO/GlobalMembers.activeTutorial : Z
    //   34: ifeq -> 66
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
    //   57: ifnull -> 66
    //   60: aload_1
    //   61: aload_0
    //   62: iconst_0
    //   63: invokevirtual removeView : (Landroid/app/Activity;Z)V
    //   66: new java/lang/String
    //   69: dup
    //   70: invokespecial <init> : ()V
    //   73: astore_1
    //   74: invokestatic getActivity : ()Lcom/roadtrack/onstar/googleMaps/ActivityMapViewerG;
    //   77: aload_0
    //   78: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   81: getfield global_lbl_accionlocalizame_1 : Ljava/lang/String;
    //   84: ldc_w 2131689877
    //   87: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   90: astore_3
    //   91: aload_0
    //   92: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   95: astore #4
    //   97: aload_1
    //   98: astore_2
    //   99: aload #4
    //   101: ifnull -> 148
    //   104: aload_1
    //   105: astore_2
    //   106: aload #4
    //   108: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   111: ifnull -> 148
    //   114: aload_0
    //   115: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   118: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   121: ldc_w '1'
    //   124: invokevirtual equals : (Ljava/lang/Object;)Z
    //   127: ifne -> 146
    //   130: aload_1
    //   131: astore_2
    //   132: aload_0
    //   133: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   136: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   139: aload_3
    //   140: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   143: ifeq -> 148
    //   146: aload_3
    //   147: astore_2
    //   148: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   151: astore #4
    //   153: aload_2
    //   154: astore_1
    //   155: aload #4
    //   157: ifnull -> 196
    //   160: aload_2
    //   161: astore_1
    //   162: aload #4
    //   164: invokevirtual size : ()I
    //   167: ifle -> 196
    //   170: aload_2
    //   171: astore_1
    //   172: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   175: iconst_0
    //   176: invokevirtual get : (I)Ljava/lang/Object;
    //   179: ifnull -> 196
    //   182: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   185: iconst_0
    //   186: invokevirtual get : (I)Ljava/lang/Object;
    //   189: checkcast com/roadtrack/onstar/VO/PushAlertsVO
    //   192: invokevirtual getAlert : ()Ljava/lang/String;
    //   195: astore_1
    //   196: aload_0
    //   197: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   200: astore #4
    //   202: aload_1
    //   203: astore_2
    //   204: aload #4
    //   206: ifnull -> 242
    //   209: aload_1
    //   210: astore_2
    //   211: aload #4
    //   213: invokevirtual getALERT_CODE_ID : ()Ljava/lang/String;
    //   216: ifnull -> 242
    //   219: aload_1
    //   220: astore_2
    //   221: aload_0
    //   222: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   225: invokevirtual getALERT_CODE_ID : ()Ljava/lang/String;
    //   228: invokevirtual isEmpty : ()Z
    //   231: ifne -> 242
    //   234: aload_0
    //   235: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   238: invokevirtual getALERT_CODE_ID : ()Ljava/lang/String;
    //   241: astore_2
    //   242: aload_0
    //   243: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   246: astore_1
    //   247: aload_1
    //   248: ifnull -> 274
    //   251: aload_1
    //   252: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   255: ifnull -> 274
    //   258: aload_0
    //   259: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   262: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   265: ldc_w '1'
    //   268: invokevirtual equals : (Ljava/lang/Object;)Z
    //   271: ifne -> 288
    //   274: aload_0
    //   275: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   278: invokevirtual getKEY_SET_NAV_ACTION : ()Ljava/lang/String;
    //   281: aload_3
    //   282: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   285: ifeq -> 290
    //   288: aload_3
    //   289: astore_2
    //   290: aload_0
    //   291: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   294: invokevirtual getKEY_EXTERNAL_LAT : ()Ljava/lang/String;
    //   297: astore_1
    //   298: aload_0
    //   299: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   302: invokevirtual getKEY_EXTERNAL_LNG : ()Ljava/lang/String;
    //   305: astore #6
    //   307: aload_0
    //   308: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   311: invokevirtual getLASTKNOWDATE : ()Ljava/lang/String;
    //   314: astore #4
    //   316: aload_0
    //   317: getfield googleMapVO : Lcom/roadtrack/onstar/VO/GoogleMapVO;
    //   320: invokevirtual getKEY_GPSSTATUS : ()Ljava/lang/String;
    //   323: astore #5
    //   325: new java/lang/StringBuilder
    //   328: dup
    //   329: invokespecial <init> : ()V
    //   332: astore #7
    //   334: aload #7
    //   336: aload_1
    //   337: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: pop
    //   341: aload #7
    //   343: ldc_w '_'
    //   346: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: pop
    //   350: aload #7
    //   352: aload #6
    //   354: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: pop
    //   358: aload #7
    //   360: invokevirtual toString : ()Ljava/lang/String;
    //   363: astore #7
    //   365: aload_0
    //   366: aload_1
    //   367: invokestatic parseDouble : (Ljava/lang/String;)D
    //   370: aload #6
    //   372: invokestatic parseDouble : (Ljava/lang/String;)D
    //   375: invokespecial latLngToMicroDegrees : (DD)[I
    //   378: pop
    //   379: aload_0
    //   380: aload_0
    //   381: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   384: getstatic com/roadtrack/onstar/googleMaps/ActivityMapViewerG.TAG : Ljava/lang/String;
    //   387: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   390: invokevirtual getName : ()Ljava/lang/String;
    //   393: aload_2
    //   394: aload #4
    //   396: aload #5
    //   398: aload #7
    //   400: aload_1
    //   401: invokestatic parseDouble : (Ljava/lang/String;)D
    //   404: aload #6
    //   406: invokestatic parseDouble : (Ljava/lang/String;)D
    //   409: invokespecial showFindMeBubble : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DD)V
    //   412: aload_0
    //   413: invokespecial disableElementsOnFindMe : ()V
    //   416: aload_2
    //   417: aload_3
    //   418: invokevirtual equals : (Ljava/lang/Object;)Z
    //   421: ifeq -> 714
    //   424: getstatic com/roadtrack/onstar/BO/GlobalMembers.activeTutorial : Z
    //   427: ifeq -> 714
    //   430: aload_0
    //   431: getfield idTutorial : Landroid/widget/LinearLayout;
    //   434: bipush #8
    //   436: invokevirtual setVisibility : (I)V
    //   439: aload_0
    //   440: iconst_0
    //   441: putfield isTutorialAvailable : Z
    //   444: aload_0
    //   445: getfield show : Lcom/roadtrack/onstar/viewTutorial/ShowViewElement;
    //   448: astore_1
    //   449: aload_1
    //   450: ifnull -> 714
    //   453: aload_1
    //   454: aload_0
    //   455: iconst_0
    //   456: invokevirtual removeView : (Landroid/app/Activity;Z)V
    //   459: goto -> 714
    //   462: aload_0
    //   463: getfield mapType : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   466: getstatic com/roadtrack/onstar/Enums$navigationProcess.Navigation : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   469: invokevirtual equals : (Ljava/lang/Object;)Z
    //   472: ifeq -> 520
    //   475: aload_0
    //   476: iconst_1
    //   477: putfield myLocationPressed : Z
    //   480: aload_0
    //   481: ldc_w 'android.permission.ACCESS_FINE_LOCATION'
    //   484: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
    //   487: ifeq -> 501
    //   490: aload_0
    //   491: ldc_w 'android.permission.ACCESS_COARSE_LOCATION'
    //   494: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
    //   497: ifeq -> 501
    //   500: return
    //   501: aload_0
    //   502: getfield mMap : Lcom/google/android/gms/maps/GoogleMap;
    //   505: iconst_1
    //   506: invokevirtual setMyLocationEnabled : (Z)V
    //   509: aload_0
    //   510: invokespecial setLandscapeAndPortraitConfiguration : ()V
    //   513: aload_0
    //   514: invokespecial setFavouriteMarkerIfExists : ()V
    //   517: goto -> 714
    //   520: aload_0
    //   521: getfield mapType : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   524: getstatic com/roadtrack/onstar/Enums$navigationProcess.FollowMe : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   527: invokevirtual equals : (Ljava/lang/Object;)Z
    //   530: ifeq -> 635
    //   533: aload_0
    //   534: iconst_0
    //   535: putfield myLocationPressed : Z
    //   538: getstatic com/roadtrack/onstar/BO/GlobalMembers.activeTutorial : Z
    //   541: ifeq -> 565
    //   544: aload_0
    //   545: iconst_1
    //   546: putfield isTutorialAvailable : Z
    //   549: aload_0
    //   550: iconst_1
    //   551: putfield pressTuto : Z
    //   554: aload_0
    //   555: getfield idTutorial : Landroid/widget/LinearLayout;
    //   558: aload_0
    //   559: getfield clickTutorial : Landroid/view/View$OnClickListener;
    //   562: invokevirtual setOnClickListener : (Landroid/view/View$OnClickListener;)V
    //   565: aload_0
    //   566: ldc_w 'android.permission.ACCESS_FINE_LOCATION'
    //   569: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
    //   572: ifeq -> 586
    //   575: aload_0
    //   576: ldc_w 'android.permission.ACCESS_COARSE_LOCATION'
    //   579: invokestatic checkSelfPermission : (Landroid/content/Context;Ljava/lang/String;)I
    //   582: ifeq -> 586
    //   585: return
    //   586: aload_0
    //   587: getfield mMap : Lcom/google/android/gms/maps/GoogleMap;
    //   590: iconst_0
    //   591: invokevirtual setMyLocationEnabled : (Z)V
    //   594: getstatic com/roadtrack/onstar/BO/GlobalMembers.followMeArrayListPoints : Ljava/util/ArrayList;
    //   597: astore_1
    //   598: aload_1
    //   599: ifnull -> 628
    //   602: aload_1
    //   603: invokevirtual size : ()I
    //   606: ifle -> 628
    //   609: ldc_w 'ActivityMapViewerG PMM FOLLOWME'
    //   612: ldc_w 'SetMaptype'
    //   615: ldc_w ''
    //   618: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   621: aload_0
    //   622: getstatic com/roadtrack/onstar/BO/GlobalMembers.followMeArrayListPoints : Ljava/util/ArrayList;
    //   625: invokespecial showFollowMeBubble : (Ljava/util/ArrayList;)V
    //   628: aload_0
    //   629: invokespecial disableAllElements : ()V
    //   632: goto -> 714
    //   635: aload_0
    //   636: getfield mapType : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   639: getstatic com/roadtrack/onstar/Enums$navigationProcess.Mixed : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   642: invokevirtual equals : (Ljava/lang/Object;)Z
    //   645: ifeq -> 714
    //   648: aload_0
    //   649: iconst_0
    //   650: putfield myLocationPressed : Z
    //   653: getstatic com/roadtrack/onstar/BO/GlobalMembers.activeTutorial : Z
    //   656: ifeq -> 688
    //   659: aload_0
    //   660: getfield idTutorial : Landroid/widget/LinearLayout;
    //   663: bipush #8
    //   665: invokevirtual setVisibility : (I)V
    //   668: aload_0
    //   669: iconst_0
    //   670: putfield isTutorialAvailable : Z
    //   673: aload_0
    //   674: getfield show : Lcom/roadtrack/onstar/viewTutorial/ShowViewElement;
    //   677: astore_1
    //   678: aload_1
    //   679: ifnull -> 688
    //   682: aload_1
    //   683: aload_0
    //   684: iconst_0
    //   685: invokevirtual removeView : (Landroid/app/Activity;Z)V
    //   688: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   691: astore_1
    //   692: aload_1
    //   693: ifnull -> 710
    //   696: aload_1
    //   697: invokevirtual size : ()I
    //   700: ifle -> 710
    //   703: aload_0
    //   704: getstatic com/roadtrack/onstar/BO/GlobalMembers.mixedNotificationsPoints : Ljava/util/ArrayList;
    //   707: invokespecial showMixedBubble : (Ljava/util/ArrayList;)V
    //   710: aload_0
    //   711: invokespecial disableAllElements : ()V
    //   714: aload_0
    //   715: getfield imageViewSplashScreen : Landroid/widget/ImageView;
    //   718: astore_1
    //   719: aload_1
    //   720: ifnull -> 729
    //   723: aload_1
    //   724: bipush #8
    //   726: invokevirtual setVisibility : (I)V
    //   729: return
  }
  
  private void setParentLinearLayout() {
    this.lin_parent_container = this.activityMapViewerView.getParentLinearLayout();
  }
  
  private void setProgressBarShareHeader() {
    this.activityMapViewerView.getProgressBarShareHeader();
  }
  
  private void setRelativeFloatingMenu() {
    this.rlContainerMenu = this.activityMapViewerView.getContainerFloatingMenu();
  }
  
  private void setRelativeFloatingMenuLans() {
    this.rlContainerMenuLans = this.activityMapViewerView.getContainerFloatingMenuLans();
  }
  
  private void setShareLocationBottomMenu() {
    this.shareLocationBottomMenu = this.activityMapViewerView.getShareLocationBottomList();
  }
  
  private void setShareLocationTopMenu() {
    this.shareLocationTopMenu = this.activityMapViewerView.getShareLocationTopMenu();
  }
  
  private void setShareLocationTopMenu2() {
    this.shareLocationTopMenu2 = this.activityMapViewerView.getShareLocationTopMenu2();
  }
  
  private void setTextViewShareHeader() {
    this.activityMapViewerView.getTextViewShareHeader();
  }
  
  private void setViewGroupTomTomSearchBar() {
    this.viewGroupTomTomSearchBar = this.activityMapViewerView.getViewGroupTomTomSearchBar();
  }
  
  private void shareLocationFunction() {
    if (ValidateTheftAuto())
      return; 
    disableAllElements();
    enableShareLocationElements();
    Location location = this.mLastLocation;
    if (location != null)
      new LatLng(location.getLatitude(), this.mLastLocation.getLongitude()); 
    setVehicleList();
    if (!this.lay_header.isClickable())
      this.lay_header.setClickable(true); 
  }
  
  private void showFindMeBubble(final String deviceId, final String action, final String date, final String status, final String bubbleName, final double latitudeAux, final double longitudAux) {
    final String addressTitle = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_lbl_localizame_direccion_1, 2131690157);
    final String positiontest = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_lbl_localizameultimaposicion_1, 2131689926);
    (new GeocoderTask((Context)activity, new GeocoderInterface() {
          final ActivityMapViewerG this$0;
          
          final String val$action;
          
          final String val$addressTitle;
          
          final String val$bubbleName;
          
          final String val$date;
          
          final String val$deviceId;
          
          final double val$latitudeAux;
          
          final double val$longitudAux;
          
          final String val$positiontest;
          
          final String val$status;
          
          public void onResult(HashMap<String, String> param1HashMap) {
            String str2;
            if (param1HashMap != null && param1HashMap.size() > 0) {
              if (param1HashMap.containsKey("address")) {
                str2 = param1HashMap.get("address");
              } else {
                str2 = "";
              } 
              if (str2 == null)
                str2 = ""; 
              if (param1HashMap.containsKey("name"))
                str1 = param1HashMap.get("name"); 
            } else {
              str2 = "";
            } 
            String str4 = ActivityMapViewerG.TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Status: ");
            stringBuilder2.append(status);
            Utilities.escribeArchivo(str4, "Bubble onCityAndStreet", stringBuilder2.toString());
            ActivityMapViewerG.this.bubbleDrawable;
            BubbleDrawable.onDestroy();
            ActivityMapViewerG.access$7202(ActivityMapViewerG.this, new BubbleDrawable((Activity)ActivityMapViewerG.activity, 1));
            ActivityMapViewerG.this.bubbleDrawable.setCornerRadius(20.0F);
            ActivityMapViewerG.this.bubbleDrawable.setPointerAlignment(2);
            ActivityMapViewerG.this.bubbleDrawable.setPadding(20, 20, 20, 20);
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("<center><b><small><font color='#3333aa'>");
            stringBuilder4.append(deviceId);
            stringBuilder4.append("</font></b><br><font color='#000000'>");
            stringBuilder4.append(action);
            stringBuilder4.append("</font><br><font color='#1eb1ed'>");
            stringBuilder4.append(positiontest);
            stringBuilder4.append("<br>");
            if (date.length() > 0) {
              str1 = date.substring(0, 16);
            } else {
              str1 = date;
            } 
            stringBuilder4.append(str1);
            stringBuilder4.append("</font><br><font color='#000000'><font color='#000000'>GPS: ");
            stringBuilder4.append(status);
            stringBuilder4.append("</font><br><font color='#000000'>");
            stringBuilder4.append(addressTitle);
            stringBuilder4.append("</font><br><font color='#000000'>");
            stringBuilder4.append(str2);
            stringBuilder4.append("</font></small></center>");
            String str1 = stringBuilder4.toString();
            ActivityMapViewerG.this.bubbleDrawable.setText(true, str1, false, "<br>Description2 here", false, 0);
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append(bubbleName);
            stringBuilder1.append(date);
            String str3 = stringBuilder1.toString().replace(" ", "").replace(":", "").replace(".", "").replace("-", "").replace("_", "");
            ActivityMapViewerG.this.bubbleDrawable.createAndSaveBubble(ActivityMapViewerG.this.bubbleDrawable, str3);
            UtilitiesFile.isAppDirAvailable();
            ActivityMapViewerG.isBubbleActived = true;
            LatLng latLng = new LatLng(latitudeAux, longitudAux);
            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5.append("latitude: ");
            stringBuilder5.append(latitudeAux);
            stringBuilder5.append(" longitude: ");
            stringBuilder5.append(longitudAux);
            stringBuilder5.append(" date: ");
            stringBuilder5.append(date);
            stringBuilder5.append(" status: ");
            stringBuilder5.append(status);
            stringBuilder5.append(" bubbleName: ");
            stringBuilder5.append(bubbleName);
            stringBuilder5.append(" address: ");
            stringBuilder5.append(str2);
            Utilities.escribeArchivo("PMM", "GoogleMap", stringBuilder5.toString());
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(Utilities.getpath((Activity)ActivityMapViewerG.getActivity()));
            stringBuilder3.append("/");
            stringBuilder3.append(str3);
            stringBuilder3.append(".png");
            str3 = stringBuilder3.toString();
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            Bitmap bitmap = BitmapFactory.decodeFile(str3);
            Matrix matrix = new Matrix();
            matrix.postScale(1.2F, 1.2F);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true)));
            ActivityMapViewerG.this.mMap.addMarker(markerOptions);
            CameraPosition.Builder builder = new CameraPosition.Builder();
            builder.target(latLng);
            builder.zoom(16.0F);
            CameraPosition cameraPosition = builder.build();
            ActivityMapViewerG.this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
          }
        }new LatLng(latitudeAux, longitudAux))).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void showFollowMeBubble(ArrayList<FollowMePointsVO> paramArrayList) {
    this.mMap.clear();
    Location[] arrayOfLocation = new Location[paramArrayList.size()];
    try {
      for (FollowMePointsVO followMePointsVO : paramArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(followMePointsVO.getDateTime());
        stringBuilder.append("  ");
        stringBuilder.append(followMePointsVO.getLat());
        stringBuilder.append("  ");
        stringBuilder.append(followMePointsVO.getLng());
        Utilities.escribeArchivo("Sígueme Test", "ActivityMapViewerG showFollowMeBubble", stringBuilder.toString());
      } 
    } catch (Exception exception) {}
    BubbleDrawable.onDestroy();
    for (byte b = 0; b < paramArrayList.size(); b++) {
      FollowMePointsVO followMePointsVO = paramArrayList.get(b);
      String str1 = followMePointsVO.getDateTime();
      String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_map_notificacionmap_sigueme_1, 2131690176);
      String str4 = TAG;
      StringBuilder stringBuilder4 = new StringBuilder();
      stringBuilder4.append("Fecha: ");
      stringBuilder4.append(str1);
      Utilities.escribeArchivo(str4, "Bubble FOLLOWME showFollowMeBubble", stringBuilder4.toString());
      this.bubbleDrawable = new BubbleDrawable((Activity)activity, 1);
      this.bubbleDrawable.setCornerRadius(20.0F);
      this.bubbleDrawable.setPointerAlignment(2);
      this.bubbleDrawable.setPadding(20, 20, 20, 20);
      StringBuilder stringBuilder3 = new StringBuilder();
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
      stringBuilder2.append(String.valueOf(b));
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
      this.mMap.addMarker(markerOptions).setZIndex(b);
      CameraPosition.Builder builder = new CameraPosition.Builder();
      builder.target(latLng);
      builder.zoom(15.0F);
      CameraPosition cameraPosition = builder.build();
      this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
      arrayOfLocation[b] = new Location("Points");
      arrayOfLocation[b].setLatitude(Double.valueOf(followMePointsVO.getLat()).doubleValue());
      arrayOfLocation[b].setLongitude(Double.valueOf(followMePointsVO.getLng()).doubleValue());
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
    BubbleDrawable.onDestroy();
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
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append("action: ");
      stringBuilder2.append((String)paramArrayList);
      Utilities.escribeArchivo(str4, "Bubble mix", stringBuilder2.toString());
      this.bubbleDrawable = new BubbleDrawable((Activity)activity, 1);
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
      stringBuilder1.append(SYNTHETIC_LOCAL_VARIABLE_6.getLatitude());
      stringBuilder1.append(SYNTHETIC_LOCAL_VARIABLE_6.getLongitude());
      stringBuilder1.append(String.valueOf(b));
      stringBuilder1.append((String)SYNTHETIC_LOCAL_VARIABLE_7);
      str2 = stringBuilder1.toString().replace(" ", "").replace(":", "").replace(".", "").replace("-", "").replace("_", "");
      UtilitiesFile.isAppDirAvailable();
      bubbleDrawable = this.bubbleDrawable;
      bubbleDrawable.createAndSaveBubble(bubbleDrawable, str2);
      latLngToMicroDegrees(Double.parseDouble(SYNTHETIC_LOCAL_VARIABLE_6.getLatitude()), Double.parseDouble(SYNTHETIC_LOCAL_VARIABLE_6.getLongitude()));
      latLng = new LatLng(Double.parseDouble(SYNTHETIC_LOCAL_VARIABLE_6.getLatitude()), Double.parseDouble(SYNTHETIC_LOCAL_VARIABLE_6.getLongitude()));
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
      arrayOfLocation[b].setLatitude(Double.valueOf(SYNTHETIC_LOCAL_VARIABLE_6.getLatitude()).doubleValue());
      arrayOfLocation[b].setLongitude(Double.valueOf(SYNTHETIC_LOCAL_VARIABLE_6.getLongitude()).doubleValue());
    } 
    centerBubbles(Utilities.GetCentralGeoCoordinate(arrayOfLocation));
  }
  
  private void startActivityForFavourites() {
    Intent intent = new Intent((Context)activity, ActivityFavoritesHistory.class);
    intent.putExtra("TypeItem", (Serializable)Enums.TypeItem.FavoritesHistory);
    activity.startActivityForResult(intent, 6);
  }
  
  private void updateGoogleSearchResultsDistances(boolean paramBoolean, double paramDouble1, double paramDouble2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   4: astore #7
    //   6: aload #7
    //   8: ifnull -> 611
    //   11: aload #7
    //   13: invokevirtual size : ()I
    //   16: ifle -> 611
    //   19: aload_0
    //   20: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   23: astore #7
    //   25: aload #7
    //   27: ifnull -> 611
    //   30: aload #7
    //   32: invokevirtual isEmpty : ()Z
    //   35: ifne -> 611
    //   38: aload_0
    //   39: getfield expListView : Landroid/widget/ExpandableListView;
    //   42: astore #7
    //   44: aload #7
    //   46: ifnull -> 611
    //   49: aload #7
    //   51: iconst_0
    //   52: invokevirtual isGroupExpanded : (I)Z
    //   55: ifeq -> 611
    //   58: aload_0
    //   59: getfield mMap : Lcom/google/android/gms/maps/GoogleMap;
    //   62: invokevirtual clear : ()V
    //   65: aload_0
    //   66: getfield expListView : Landroid/widget/ExpandableListView;
    //   69: iconst_1
    //   70: invokevirtual setEnabled : (Z)V
    //   73: aload_0
    //   74: getfield controller : Lcom/roadtrack/onstar/mapData/MapController;
    //   77: dload_2
    //   78: dload #4
    //   80: invokevirtual microDegreesToLatLng : (DD)[D
    //   83: astore #9
    //   85: new java/util/ArrayList
    //   88: dup
    //   89: invokespecial <init> : ()V
    //   92: astore #7
    //   94: iconst_0
    //   95: istore #6
    //   97: iload #6
    //   99: aload_0
    //   100: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   103: invokevirtual size : ()I
    //   106: if_icmpge -> 382
    //   109: aload_0
    //   110: getfield mRuAlgorithm : Lcom/roadtrack/onstar/utils/RubenUltimaAlgorithm;
    //   113: aload #9
    //   115: iconst_0
    //   116: daload
    //   117: aload #9
    //   119: iconst_1
    //   120: daload
    //   121: aload_0
    //   122: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   125: iload #6
    //   127: invokevirtual get : (I)Ljava/lang/Object;
    //   130: checkcast java/util/HashMap
    //   133: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LATITUDE : Ljava/lang/String;
    //   136: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   139: checkcast java/lang/String
    //   142: invokestatic parseDouble : (Ljava/lang/String;)D
    //   145: aload_0
    //   146: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   149: iload #6
    //   151: invokevirtual get : (I)Ljava/lang/Object;
    //   154: checkcast java/util/HashMap
    //   157: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LONGITUDE : Ljava/lang/String;
    //   160: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   163: checkcast java/lang/String
    //   166: invokestatic parseDouble : (Ljava/lang/String;)D
    //   169: invokevirtual linearDistance : (DDDD)D
    //   172: dstore_2
    //   173: aload_0
    //   174: getfield mRuAlgorithm : Lcom/roadtrack/onstar/utils/RubenUltimaAlgorithm;
    //   177: dload_2
    //   178: invokevirtual linearDistanceToKilometers : (D)D
    //   181: dstore_2
    //   182: aload_0
    //   183: getfield mRuAlgorithm : Lcom/roadtrack/onstar/utils/RubenUltimaAlgorithm;
    //   186: dload_2
    //   187: invokevirtual formatLinearDistance : (D)Ljava/lang/String;
    //   190: astore #11
    //   192: aload_0
    //   193: getfield mRuAlgorithm : Lcom/roadtrack/onstar/utils/RubenUltimaAlgorithm;
    //   196: dload_2
    //   197: invokevirtual formatLinearDistanceWithString : (D)Ljava/lang/String;
    //   200: astore #8
    //   202: new java/util/HashMap
    //   205: dup
    //   206: invokespecial <init> : ()V
    //   209: astore #10
    //   211: aload #10
    //   213: ldc_w 'reference'
    //   216: aload_0
    //   217: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   220: iload #6
    //   222: invokevirtual get : (I)Ljava/lang/Object;
    //   225: checkcast java/util/HashMap
    //   228: ldc_w 'reference'
    //   231: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   234: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   237: pop
    //   238: aload #10
    //   240: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LATITUDE : Ljava/lang/String;
    //   243: aload_0
    //   244: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   247: iload #6
    //   249: invokevirtual get : (I)Ljava/lang/Object;
    //   252: checkcast java/util/HashMap
    //   255: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LATITUDE : Ljava/lang/String;
    //   258: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   261: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   264: pop
    //   265: aload #10
    //   267: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LONGITUDE : Ljava/lang/String;
    //   270: aload_0
    //   271: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   274: iload #6
    //   276: invokevirtual get : (I)Ljava/lang/Object;
    //   279: checkcast java/util/HashMap
    //   282: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LONGITUDE : Ljava/lang/String;
    //   285: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   288: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   291: pop
    //   292: aload #10
    //   294: ldc_w 'name'
    //   297: aload_0
    //   298: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   301: iload #6
    //   303: invokevirtual get : (I)Ljava/lang/Object;
    //   306: checkcast java/util/HashMap
    //   309: ldc_w 'name'
    //   312: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   315: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   318: pop
    //   319: aload #10
    //   321: ldc_w 'address'
    //   324: aload_0
    //   325: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   328: iload #6
    //   330: invokevirtual get : (I)Ljava/lang/Object;
    //   333: checkcast java/util/HashMap
    //   336: ldc_w 'address'
    //   339: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   342: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   345: pop
    //   346: aload #10
    //   348: ldc_w 'distance'
    //   351: aload #11
    //   353: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   356: pop
    //   357: aload #10
    //   359: ldc_w 'distanceString'
    //   362: aload #8
    //   364: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   367: pop
    //   368: aload #7
    //   370: aload #10
    //   372: invokevirtual add : (Ljava/lang/Object;)Z
    //   375: pop
    //   376: iinc #6, 1
    //   379: goto -> 97
    //   382: aload_0
    //   383: aload #7
    //   385: putfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   388: new java/util/ArrayList
    //   391: dup
    //   392: invokespecial <init> : ()V
    //   395: astore #8
    //   397: new java/lang/StringBuilder
    //   400: dup
    //   401: invokespecial <init> : ()V
    //   404: astore #9
    //   406: aload #9
    //   408: invokestatic getActivity : ()Lcom/roadtrack/onstar/googleMaps/ActivityMapViewerG;
    //   411: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   414: ldc_w 2131690166
    //   417: invokevirtual getString : (I)Ljava/lang/String;
    //   420: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: pop
    //   424: aload #9
    //   426: ldc_w ' ('
    //   429: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: pop
    //   433: aload #9
    //   435: aload #7
    //   437: invokevirtual size : ()I
    //   440: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   443: pop
    //   444: aload #9
    //   446: ldc_w ')'
    //   449: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   452: pop
    //   453: aload #8
    //   455: aload #9
    //   457: invokevirtual toString : ()Ljava/lang/String;
    //   460: invokeinterface add : (Ljava/lang/Object;)Z
    //   465: pop
    //   466: aload_0
    //   467: getfield isLongPress : Z
    //   470: ifne -> 523
    //   473: aload_0
    //   474: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   477: astore #8
    //   479: aload #8
    //   481: ifnull -> 496
    //   484: aload #8
    //   486: invokevirtual size : ()I
    //   489: iconst_1
    //   490: if_icmpne -> 496
    //   493: goto -> 523
    //   496: aload_0
    //   497: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   500: ifnonnull -> 531
    //   503: aload #7
    //   505: invokevirtual size : ()I
    //   508: iconst_1
    //   509: if_icmpne -> 531
    //   512: aload_0
    //   513: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   516: iconst_0
    //   517: invokevirtual setShowExpandableIcon : (Z)V
    //   520: goto -> 531
    //   523: aload_0
    //   524: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   527: iconst_0
    //   528: invokevirtual setShowExpandableIcon : (Z)V
    //   531: iload_1
    //   532: ifeq -> 549
    //   535: aload_0
    //   536: getfield expListView : Landroid/widget/ExpandableListView;
    //   539: aload_0
    //   540: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   543: invokevirtual setAdapter : (Landroid/widget/ExpandableListAdapter;)V
    //   546: goto -> 567
    //   549: aload_0
    //   550: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   553: aload_0
    //   554: getfield mGoogleSearchResults : Ljava/util/ArrayList;
    //   557: invokevirtual setData : (Ljava/util/ArrayList;)V
    //   560: aload_0
    //   561: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   564: invokevirtual notifyDataSetChanged : ()V
    //   567: aload #7
    //   569: invokevirtual isEmpty : ()Z
    //   572: ifne -> 1007
    //   575: iload_1
    //   576: ifeq -> 1007
    //   579: aload_0
    //   580: getfield expListView : Landroid/widget/ExpandableListView;
    //   583: iconst_0
    //   584: invokevirtual expandGroup : (I)Z
    //   587: pop
    //   588: aload_0
    //   589: getfield expListView : Landroid/widget/ExpandableListView;
    //   592: invokevirtual getVisibility : ()I
    //   595: bipush #8
    //   597: if_icmpne -> 1007
    //   600: aload_0
    //   601: getfield expListView : Landroid/widget/ExpandableListView;
    //   604: iconst_0
    //   605: invokevirtual setVisibility : (I)V
    //   608: goto -> 1007
    //   611: aload_0
    //   612: getfield expListView : Landroid/widget/ExpandableListView;
    //   615: iconst_1
    //   616: invokevirtual setEnabled : (Z)V
    //   619: new java/util/ArrayList
    //   622: dup
    //   623: invokespecial <init> : ()V
    //   626: astore #7
    //   628: new java/lang/StringBuilder
    //   631: dup
    //   632: invokespecial <init> : ()V
    //   635: astore #8
    //   637: aload #8
    //   639: invokestatic getActivity : ()Lcom/roadtrack/onstar/googleMaps/ActivityMapViewerG;
    //   642: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   645: ldc_w 2131690166
    //   648: invokevirtual getString : (I)Ljava/lang/String;
    //   651: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   654: pop
    //   655: aload #8
    //   657: ldc_w ' ('
    //   660: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   663: pop
    //   664: aload #8
    //   666: aload_0
    //   667: getfield placesListItems : Ljava/util/ArrayList;
    //   670: invokevirtual size : ()I
    //   673: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   676: pop
    //   677: aload #8
    //   679: ldc_w ')'
    //   682: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   685: pop
    //   686: aload #7
    //   688: aload #8
    //   690: invokevirtual toString : ()Ljava/lang/String;
    //   693: invokeinterface add : (Ljava/lang/Object;)Z
    //   698: pop
    //   699: aload_0
    //   700: new com/roadtrack/onstar/adapter/ExpandableResultsAdapter
    //   703: dup
    //   704: getstatic com/roadtrack/onstar/googleMaps/ActivityMapViewerG.activity : Lcom/roadtrack/onstar/googleMaps/ActivityMapViewerG;
    //   707: aload #7
    //   709: aload_0
    //   710: getfield placesListItems : Ljava/util/ArrayList;
    //   713: new com/roadtrack/onstar/googleMaps/ActivityMapViewerG$43
    //   716: dup
    //   717: aload_0
    //   718: invokespecial <init> : (Lcom/roadtrack/onstar/googleMaps/ActivityMapViewerG;)V
    //   721: iconst_0
    //   722: new com/roadtrack/onstar/googleMaps/ActivityMapViewerG$44
    //   725: dup
    //   726: aload_0
    //   727: invokespecial <init> : (Lcom/roadtrack/onstar/googleMaps/ActivityMapViewerG;)V
    //   730: invokespecial <init> : (Landroid/app/Activity;Ljava/util/List;Ljava/util/ArrayList;Lcom/roadtrack/onstar/interfaces/TomTomStartNavigation_Interface;ZLcom/roadtrack/onstar/async_tasks/intefaces/ExpandableIcon_Interface;)V
    //   733: putfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   736: aload_0
    //   737: getfield isLongPress : Z
    //   740: ifne -> 797
    //   743: aload_0
    //   744: getfield placesListItems : Ljava/util/ArrayList;
    //   747: astore #7
    //   749: aload #7
    //   751: ifnull -> 766
    //   754: aload #7
    //   756: invokevirtual size : ()I
    //   759: iconst_1
    //   760: if_icmpne -> 766
    //   763: goto -> 797
    //   766: aload_0
    //   767: getfield placesListItems : Ljava/util/ArrayList;
    //   770: astore #7
    //   772: aload #7
    //   774: ifnull -> 805
    //   777: aload #7
    //   779: invokevirtual size : ()I
    //   782: iconst_1
    //   783: if_icmpne -> 805
    //   786: aload_0
    //   787: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   790: iconst_0
    //   791: invokevirtual setShowExpandableIcon : (Z)V
    //   794: goto -> 805
    //   797: aload_0
    //   798: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   801: iconst_0
    //   802: invokevirtual setShowExpandableIcon : (Z)V
    //   805: aload_0
    //   806: getfield typeFloating : Ljava/lang/String;
    //   809: ldc_w ''
    //   812: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   815: ifne -> 919
    //   818: aload_0
    //   819: aload_0
    //   820: getfield placesListItems : Ljava/util/ArrayList;
    //   823: invokevirtual setPlacesListItems : (Ljava/util/ArrayList;)V
    //   826: iconst_0
    //   827: istore #6
    //   829: iload #6
    //   831: aload_0
    //   832: getfield placesListItems : Ljava/util/ArrayList;
    //   835: invokevirtual size : ()I
    //   838: if_icmpge -> 919
    //   841: aload_0
    //   842: getfield placesListItems : Ljava/util/ArrayList;
    //   845: iload #6
    //   847: invokevirtual get : (I)Ljava/lang/Object;
    //   850: checkcast java/util/HashMap
    //   853: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LATITUDE : Ljava/lang/String;
    //   856: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   859: checkcast java/lang/String
    //   862: astore #7
    //   864: aload_0
    //   865: getfield placesListItems : Ljava/util/ArrayList;
    //   868: iload #6
    //   870: invokevirtual get : (I)Ljava/lang/Object;
    //   873: checkcast java/util/HashMap
    //   876: getstatic com/roadtrack/onstar/adapter/SearchCompleteTask.KEY_LONGITUDE : Ljava/lang/String;
    //   879: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   882: checkcast java/lang/String
    //   885: astore #8
    //   887: aload_0
    //   888: getfield controller : Lcom/roadtrack/onstar/mapData/MapController;
    //   891: aload #7
    //   893: invokestatic parseDouble : (Ljava/lang/String;)D
    //   896: aload #8
    //   898: invokestatic parseDouble : (Ljava/lang/String;)D
    //   901: aload_0
    //   902: getfield typeFloating : Ljava/lang/String;
    //   905: iload #6
    //   907: getstatic com/roadtrack/onstar/googleMaps/ActivityMapViewerG.activity : Lcom/roadtrack/onstar/googleMaps/ActivityMapViewerG;
    //   910: invokevirtual setMarkers : (DDLjava/lang/String;ILandroid/app/Activity;)V
    //   913: iinc #6, 1
    //   916: goto -> 829
    //   919: iload_1
    //   920: ifeq -> 937
    //   923: aload_0
    //   924: getfield expListView : Landroid/widget/ExpandableListView;
    //   927: aload_0
    //   928: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   931: invokevirtual setAdapter : (Landroid/widget/ExpandableListAdapter;)V
    //   934: goto -> 955
    //   937: aload_0
    //   938: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   941: aload_0
    //   942: getfield placesListItems : Ljava/util/ArrayList;
    //   945: invokevirtual setData : (Ljava/util/ArrayList;)V
    //   948: aload_0
    //   949: getfield mExpandableListAdapter : Lcom/roadtrack/onstar/adapter/ExpandableResultsAdapter;
    //   952: invokevirtual notifyDataSetChanged : ()V
    //   955: aload_0
    //   956: getfield placesListItems : Ljava/util/ArrayList;
    //   959: astore #7
    //   961: aload #7
    //   963: ifnull -> 1007
    //   966: aload #7
    //   968: invokevirtual isEmpty : ()Z
    //   971: ifne -> 1007
    //   974: iload_1
    //   975: ifeq -> 1007
    //   978: aload_0
    //   979: getfield expListView : Landroid/widget/ExpandableListView;
    //   982: iconst_0
    //   983: invokevirtual expandGroup : (I)Z
    //   986: pop
    //   987: aload_0
    //   988: getfield expListView : Landroid/widget/ExpandableListView;
    //   991: invokevirtual getVisibility : ()I
    //   994: bipush #8
    //   996: if_icmpne -> 1007
    //   999: aload_0
    //   1000: getfield expListView : Landroid/widget/ExpandableListView;
    //   1003: iconst_0
    //   1004: invokevirtual setVisibility : (I)V
    //   1007: return
  }
  
  private void userActive() {
    Intent intent = new Intent();
    intent.setAction("GlobalTouchService");
    intent.putExtra("ACTION_EXTRA", "usuario_activo_map");
    sendBroadcast(intent);
  }
  
  public boolean ValidateTheftAuto() {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    boolean bool = this.dbFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), userDevicesVO.getDeviceId(), "", true);
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Mostrando1: ");
    stringBuilder.append(bool);
    Utilities.escribeArchivo(str, "THEFT", stringBuilder.toString());
    Utilities.showTheftAutoBanner((TextView)findViewById(2131297056), (Context)activity, bool);
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
    //   8: invokespecial <init> : (Landroid/content/Context;)V
    //   11: aload_1
    //   12: aload_0
    //   13: invokevirtual addConnectionCallbacks : (Lcom/google/android/gms/common/api/GoogleApiClient$ConnectionCallbacks;)Lcom/google/android/gms/common/api/GoogleApiClient$Builder;
    //   16: pop
    //   17: aload_1
    //   18: aload_0
    //   19: invokevirtual addOnConnectionFailedListener : (Lcom/google/android/gms/common/api/GoogleApiClient$OnConnectionFailedListener;)Lcom/google/android/gms/common/api/GoogleApiClient$Builder;
    //   22: pop
    //   23: aload_1
    //   24: getstatic com/google/android/gms/location/LocationServices.API : Lcom/google/android/gms/common/api/Api;
    //   27: invokevirtual addApi : (Lcom/google/android/gms/common/api/Api;)Lcom/google/android/gms/common/api/GoogleApiClient$Builder;
    //   30: pop
    //   31: aload_0
    //   32: aload_1
    //   33: invokevirtual build : ()Lcom/google/android/gms/common/api/GoogleApiClient;
    //   36: putfield mGoogleApiClient : Lcom/google/android/gms/common/api/GoogleApiClient;
    //   39: aload_0
    //   40: getfield mGoogleApiClient : Lcom/google/android/gms/common/api/GoogleApiClient;
    //   43: invokevirtual connect : ()V
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	46	49	finally
  }
  
  public void dialogNoGPSActive() {
    final Dialog dialog = Utilities.simpleDialog((Context)this, null, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955), Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.navigation_gps_lbl_alerta_1, 2131690144), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_opciones_1, 2131689951), 20.0F, 16.0F);
    this.buttonOk = (Button)dialog.findViewById(2131296343);
    this.buttonNOk = (Button)dialog.findViewById(2131296344);
    this.buttonOk.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.buttonNOk.setOnClickListener(new View.OnClickListener() {
          final ActivityMapViewerG this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
            ActivityMapViewerG.this.startActivity(intent);
          }
        });
    dialog.show();
  }
  
  public void functionMarker(final double latitudAux, final double longitudeAux, final boolean iconExpAux, final int tag, final boolean showMarker) {
    final LatLng latLng = new LatLng(latitudAux, longitudeAux);
    (new GeocoderTask((Context)activity, new GeocoderInterface() {
          final ActivityMapViewerG this$0;
          
          final boolean val$iconExpAux;
          
          final LatLng val$latLng;
          
          final double val$latitudAux;
          
          final double val$longitudeAux;
          
          final boolean val$showMarker;
          
          final int val$tag;
          
          public void onResult(HashMap<String, String> param1HashMap) {
            String str1;
            String str2;
            if (param1HashMap != null && param1HashMap.size() > 0) {
              if (param1HashMap.containsKey("address")) {
                str2 = param1HashMap.get("address");
              } else {
                str2 = "";
              } 
              if (str2 == null)
                str2 = ""; 
              if (param1HashMap.containsKey("name")) {
                str1 = param1HashMap.get("name");
              } else {
                str1 = "";
              } 
              if (str1 == null)
                str1 = ""; 
            } else {
              str1 = "";
              str2 = "";
            } 
            ActivityMapViewerG.this.currentMarkers.clear();
            if (showMarker)
              ActivityMapViewerG.this.mMap.clear(); 
            MapController mapController = ActivityMapViewerG.this.controller;
            LatLng latLng = latLng;
            mapController.setMarkers(latLng.latitude, latLng.longitude, "", tag, (Activity)ActivityMapViewerG.activity);
            ActivityMapViewerG.this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0F));
            ActivityMapViewerG.access$4902(ActivityMapViewerG.this, false);
            PointInfo pointInfo = new PointInfo(str2, str1, latLng, null);
            pointInfo.setName(pointInfo.getAddress());
            String str3 = ActivityMapViewerG.TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("address: ");
            stringBuilder2.append(pointInfo.getName());
            Utilities.escribeArchivo(str3, "sendRouteFunction", stringBuilder2.toString());
            ActivityMapViewerG.this.currentMarkers.add(pointInfo);
            ActivityMapViewerG activityMapViewerG = ActivityMapViewerG.this;
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("");
            stringBuilder1.append(latitudAux);
            String str4 = stringBuilder1.toString();
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("");
            stringBuilder1.append(longitudeAux);
            activityMapViewerG.fakeGoogleSearchResult("REFERENCE", str4, stringBuilder1.toString(), str1, str2, false, iconExpAux);
          }
        }latLng)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public MapController getController() {
    return this.controller;
  }
  
  public GoogleMap getMap() {
    return this.mMap;
  }
  
  public ArrayList<HashMap<String, String>> getPlacesList() {
    return this.placesListItems;
  }
  
  public int getPosition() {
    return this.position;
  }
  
  public String getTypeFloating() {
    return this.typeFloating;
  }
  
  public View getViewByPosition(int paramInt, ListView paramListView) {
    int j = paramListView.getFirstVisiblePosition();
    int i = paramListView.getChildCount();
    return (paramInt < j || paramInt > i + j - 1) ? paramListView.getAdapter().getView(paramInt, null, (ViewGroup)paramListView) : paramListView.getChildAt(paramInt - j);
  }
  
  public ExpandableResultsAdapter getmExpandableListAdapter() {
    return this.mExpandableListAdapter;
  }
  
  public void lockRighDrawer(boolean paramBoolean) {
    this.drawerLayout.setDrawerLockMode(paramBoolean);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    isFavClicked = false;
    enableFavorites(Boolean.valueOf(true));
    if (paramInt2 == -1 && paramInt1 == 6) {
      this.TypeMarker = paramIntent.getIntExtra("Type", 0);
      GlobalMembers.iconFav = true;
      this.isFromFavouritesActivity = true;
      GlobalMembers.isFromShareMarkerActivity = false;
      GlobalMembers.isFromShareFavActivity = false;
      this.destinationName = null;
      this.destinationAddress = null;
      setMapType();
    } 
  }
  
  public void onBackPressed() {
    if (!this.isActionRunning) {
      if (lvSavedEditSearch.getVisibility() == 0)
        lvSavedEditSearch.setVisibility(8); 
      dialogBackToMainActivity();
    } 
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    changeOrientation(paramConfiguration, true);
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public void onConnected(Bundle paramBundle) {
    this.mLocationRequest = new LocationRequest();
    this.mLocationRequest.setInterval(1000L);
    this.mLocationRequest.setFastestInterval(1000L);
    this.mLocationRequest.setPriority(102);
    if (ContextCompat.checkSelfPermission((Context)this, "android.permission.ACCESS_FINE_LOCATION") == 0)
      LocationServices.FusedLocationApi.requestLocationUpdates(this.mGoogleApiClient, this.mLocationRequest, this); 
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult) {}
  
  public void onConnectionSuspended(int paramInt) {}
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    Utilities.escribeArchivo("PMM GoogleMap", "ActivityMapViewerG", "onCreate");
    if (Build.VERSION.SDK_INT < 21)
      Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new DefaultExceptionHandler(getApplicationContext(), (Activity)this)); 
    setVolumeControlStream(3);
    if (getActionBar() != null)
      getActionBar().hide(); 
    activity = this;
    this.mRuAlgorithm = new RubenUltimaAlgorithm();
    this.activityMapViewerView = new ActivityMapViewerViewG(activity);
    this.dbFunctions = new DBFunctions((Context)activity);
    try {
      this.dbFunctions.getUserPreference(GlobalMembers.userLogged);
    } catch (Exception exception) {
      startActivity(new Intent((Context)this, LoginActivity.class));
    } 
    this.stringsResourcesVO = new StringsResourcesVO();
    global_popup_lbl_aviso_1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    global_popup_lbl_accionencurso_1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
    global_popup_btn_ok_1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
    global_popup_btn_no_1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.coordinatesNotValid, 2131689738);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_share_lbl_compartirubicacionvehic_1, 2131690191);
    Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_lbl_share_titulocompartirlocalizacion_1, 2131690163);
    this.bubbleDrawable = new BubbleDrawable((Activity)activity, 1);
    this.googleMapVO = (GoogleMapVO)getIntent().getSerializableExtra("googleObject");
    try {
      this.mapType = (Enums.navigationProcess)this.googleMapVO.getKEY_SET_ENGINE_SOURCE();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
    } 
    this.rtApp = (onstarApplication)getApplicationContext();
    this.rtApp.setAppStatus(1);
    this.taskSet = new TaskSet();
    this.idTutorial = (LinearLayout)activity.findViewById(2131296607);
    if (GlobalMembers.activeTutorial) {
      this.idTutorial.setOnClickListener(activity.clickTutorial);
      this.idTutorial.setVisibility(0);
    } 
    try {
      Bundle bundle = getIntent().getBundleExtra("extra");
      if (bundle != null) {
        GoogleMapVO googleMapVO = new GoogleMapVO();
        this();
        this.googleMapVO = googleMapVO;
        this.googleMapVO.setKEY_SET_ENGINE_SOURCE(bundle.getSerializable("source"));
        this.mapType = (Enums.navigationProcess)this.googleMapVO.getKEY_SET_ENGINE_SOURCE();
        this.googleMapVO.setKEY_SET_NAV_ACTION(bundle.getString("navaction"));
        this.googleMapVO.setKEY_SET_NAV_PARAMS(bundle.getString("navextra_params"));
        this.googleMapVO.setKEY_GPSSTATUS(bundle.getString("GPSSTATUS"));
        this.googleMapVO.setKEY_EXTERNAL_LAT(bundle.getString("externallatitude"));
        this.googleMapVO.setKEY_EXTERNAL_LNG(bundle.getString("externallongitude"));
        this.googleMapVO.setLASTKNOWDATE(bundle.getString("LASTKNOWDATE"));
        try {
          this.googleMapVO.setKEY_SET_NAV_DEVICE(bundle.getString("navdevice_id"));
        } catch (Exception exception) {
          Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
        } 
        try {
          this.googleMapVO.setALERT_CODE_ID(bundle.getString("ALERT_CODE_ID"));
        } catch (Exception exception) {
          Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
        } 
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    try {
      int j = Utilities.getDevicePositionByDeviceId(this.googleMapVO.getPUSH_NOTIFICATION_DEVICE_ID());
      if (j != -1) {
        UserPreferenceVO userPreferenceVO = this.dbFunctions.getUserPreference(GlobalMembers.userLogged);
        UserDevicesVO userDevicesVO = this.rtApp.getmDeviceUserList().get(j);
        this.dbFunctions.addVehicleSelected((Context)activity, userPreferenceVO.getUser(), userDevicesVO);
        Utilities.updateVehicleSelected(getApplicationContext(), userPreferenceVO.getUser(), userDevicesVO);
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
    } 
    getWindow().addFlags(128);
    setDrawerLayout();
    setLeftNavigationDrawer(paramBundle);
    setViewGroupTomTomSearchBar();
    setButtonToggleLeftNavigationDrawer();
    setImageButtonCenterLocation();
    setImageViewSplashScreen();
    initButtons();
    setEditTextSearch();
    setLinearLayoutBottom();
    userActive();
    setFrameContainer();
    setBottomContainer();
    setShareLocationBottomMenu();
    setShareLocationTopMenu();
    setImageViewFindMe();
    setTextViewShareHeader();
    setListViewVehicles();
    setLayoutVehicleHeader();
    setProgressBarShareHeader();
    setParentLinearLayout();
    setLinearShareTopMenuPortrait();
    setLinearShareTopMenuLand();
    setShareLocationTopMenu2();
    setFloatingButton();
    setFloatingButtonLans();
    if (this.mapFragment == null) {
      this.mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(2131296880);
      this.mapFragment.getMapAsync(this);
    } 
    Configuration configuration = getResources().getConfiguration();
    int i = configuration.orientation;
    if (i == 2) {
      if (GlobalMembers.activeTutorial) {
        this.idTutorial.setVisibility(8);
        ShowViewElement showViewElement = this.show;
        if (showViewElement != null)
          showViewElement.removeView((Activity)this, false); 
      } 
      if (this.mapType.equals(Enums.navigationProcess.Navigation)) {
        setRelativeFloatingMenuLans();
        this.rlContainerMenuLans.setVisibility(0);
      } 
    } else if (i == 1) {
      if (GlobalMembers.activeTutorial)
        this.idTutorial.setVisibility(0); 
      if (this.mapType.equals(Enums.navigationProcess.Navigation)) {
        setRelativeFloatingMenu();
        this.rlContainerMenu.setVisibility(0);
      } 
    } 
    changeOrientation(configuration, true);
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
    registerReceiver(this.networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
  
  protected void onDestroy() {
    super.onDestroy();
    Utilities.escribeArchivo("ActivityMapViewerG PMM FOLLOWME", "ON DESTROY", "");
    BubbleDrawable.onDestroy();
    unregisterReceiver(this.networkChangeReceiver);
    disableOnFollowMeObjectListener();
  }
  
  public void onLocationChanged(Location paramLocation) {
    this.mLastLocation = paramLocation;
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
        this.controller = new MapController(this.mMap, (Activity)activity);
        this.activityMapViewerView.setLatLng(this.latLngGlobal);
        this.activityMapViewerView.setFloating(this.mMap, this);
        this.activityMapViewerView.getFloating().expand();
        this.activityMapViewerView.setFloatingLans(this.mMap, this);
        this.activityMapViewerView.getFloatingLans().expand();
      } 
    } 
  }
  
  public void onMapClick(LatLng paramLatLng) {
    if (!onSendRoute) {
      if (this.mapType.equals(Enums.navigationProcess.Navigation) && this.currentFunction.equals(TAG)) {
        this.mMap.clear();
        this.currentMarkers.clear();
        this.mCurrLocationMarker = null;
        resetMapScreen();
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.googSearch.getApplicationWindowToken(), 2);
        if (lvSavedEditSearch.getVisibility() == 0) {
          ListView listView = lvSavedEditSearch;
          if (listView != null)
            listView.setVisibility(8); 
        } 
        if (this.controller == null)
          this.rlContainerMenu.setVisibility(8); 
        this.myConfiguration = getResources().getConfiguration();
        if (this.myConfiguration.orientation == 2)
          this.rlContainerMenu.setVisibility(8); 
      } 
    } else {
      setActionButtons();
    } 
  }
  
  public void onMapLongClick(LatLng paramLatLng) {
    if (!onSendRoute) {
      if (this.mapType.equals(Enums.navigationProcess.Navigation) && this.currentFunction.equals(TAG)) {
        enableSendRouteShareLocationGoToRoute();
        enableFavorites(Boolean.valueOf(true));
        setLandscapeAndPortraitConfiguration();
        this.listCollapse = true;
        this.typeFloating = "";
        this.placesListItems = new ArrayList<HashMap<String, String>>();
        if (paramLatLng != null)
          showPointDetail(paramLatLng.latitude, paramLatLng.longitude, false, true, -1); 
      } 
    } else {
      setActionButtons();
    } 
  }
  
  public void onMapReady(GoogleMap paramGoogleMap) {
    this.mMap = paramGoogleMap;
    this.mMap.clear();
    ActivityMapViewerG activityMapViewerG = activity;
    MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle((Context)activityMapViewerG, activityMapViewerG.getResources().getIdentifier("map_style_day", "raw", activity.getPackageName()));
    this.mMap.setMapStyle(mapStyleOptions);
    this.mMap.setOnMapLongClickListener(this);
    this.mMap.setOnMapClickListener(this);
    this.mMap.setOnMarkerClickListener(this);
    this.mMap.getUiSettings().setRotateGesturesEnabled(false);
    this.mMap.getUiSettings().setMyLocationButtonEnabled(false);
    this.mMap.getUiSettings().setMapToolbarEnabled(false);
    buildGoogleApiClient();
    drawMarker(this.currentMarkers);
    if (OnFollowMeListener.onFollowMeObjectListener == null)
      new OnFollowMeListener(new OnFollowMeListener.OnFollowMeObjectListener() {
            final ActivityMapViewerG this$0;
            
            public void onFollowMe(ArrayList<FollowMePointsVO> param1ArrayList) {
              Utilities.escribeArchivo("ActivityMapViewerG PMM FOLLOWME", "LISTENER ON MAP READY", "");
              if (ActivityMapViewerG.this.mapType.equals(Enums.navigationProcess.FollowMe))
                ActivityMapViewerG.this.showFollowMeBubble(GlobalMembers.followMeArrayListPoints); 
            }
          }); 
    setMapType();
  }
  
  public boolean onMarkerClick(Marker paramMarker) {
    boolean bool = onSendRoute;
    byte b = 0;
    if (!bool) {
      if (paramMarker.getTag() != null && ((Integer)paramMarker.getTag()).intValue() != -1) {
        if (!this.googSearch.getText().toString().equalsIgnoreCase(""))
          this.googSearch.setText(""); 
        String str2 = (String)((HashMap)this.placesListItems.get(((Integer)paramMarker.getTag()).intValue())).get(SearchCompleteTask.KEY_LATITUDE);
        String str1 = (String)((HashMap)this.placesListItems.get(((Integer)paramMarker.getTag()).intValue())).get(SearchCompleteTask.KEY_LONGITUDE);
        LatLng latLng = new LatLng(Double.parseDouble(str2), Double.parseDouble(str1));
        this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0F));
        this.mExpandableListAdapter.notifyDataSetChanged();
        this.listCollapse = true;
        if (!this.typeFloating.equalsIgnoreCase("")) {
          this.mMap.clear();
          byte b1;
          for (b1 = 0; b < this.placesListItems.size(); b1 = b2) {
            String str4 = (String)((HashMap)this.placesListItems.get(b)).get(SearchCompleteTask.KEY_LATITUDE);
            String str3 = (String)((HashMap)this.placesListItems.get(b)).get(SearchCompleteTask.KEY_LONGITUDE);
            this.controller.setMarkers(Double.parseDouble(str4), Double.parseDouble(str3), this.typeFloating, b, (Activity)activity);
            byte b2 = b1;
            if (str4.equalsIgnoreCase(str2)) {
              b2 = b1;
              if (str3.equalsIgnoreCase(str1))
                b2 = b; 
            } 
            b++;
          } 
          showPointDetail(Double.parseDouble(str2), Double.parseDouble(str1), true, false, b1);
          this.controller.setMarkers(Double.parseDouble(str2), Double.parseDouble(str1), "", b1, (Activity)activity);
        } else {
          showPointDetail(Double.parseDouble(str2), Double.parseDouble(str1), true, true, -1);
          this.controller.setMarkers(Double.parseDouble(str2), Double.parseDouble(str1), "", -1, (Activity)activity);
        } 
        return true;
      } 
      return false;
    } 
    setActionButtons();
    return false;
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle) {
    super.onRestoreInstanceState(paramBundle);
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
  }
  
  protected void onResume() {
    super.onResume();
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
    this.currentMarkers.clear();
    this.mCurrLocationMarker = null;
    this.mGoogleSearchResults = new ArrayList<HashMap<String, String>>();
    ArrayList arrayList = new ArrayList();
    ExpandableResultsAdapter expandableResultsAdapter = new ExpandableResultsAdapter((Activity)activity, arrayList, this.mGoogleSearchResults, new TomTomStartNavigation_Interface() {
          final ActivityMapViewerG this$0;
          
          public void onStartNavigation(String param1String1, String param1String2, String param1String3, String param1String4) {
            ActivityMapViewerG.this.saveRoute(param1String1, param1String2, param1String3, param1String4);
          }
        }false, new ExpandableIcon_Interface(this) {
          public void onExpand() {}
        });
    this.expListView.setAdapter((ExpandableListAdapter)expandableResultsAdapter);
    this.expListView.setEnabled(true);
    createNavigationBottons();
  }
  
  public void sendRoute(CustomActionButton paramCustomActionButton1, CustomActionButton paramCustomActionButton2) {
    if (!NetUtilities.validateNetwork((Context)this, false, true))
      return; 
    ArrayList<PointInfo> arrayList = this.currentMarkers;
    if (arrayList != null && arrayList.size() > 0) {
      if (ValidateTheftAuto())
        return; 
      if (!onSendRoute) {
        onSendRoute = true;
        if (this.fab.getVisibility() == 0)
          this.fab.setEnabled(false); 
        if (this.fabLans.getVisibility() == 0)
          this.fabLans.setEnabled(false); 
        imageButtonFavs.setAlpha(CustomActionButton.WATER_MARK_ALPHA);
        imageButtonFavs.setEnabled(false);
        paramCustomActionButton2.setAlpha(CustomActionButton.WATER_MARK_ALPHA);
        paramCustomActionButton2.setEnabled(false);
        this.imageButtonCenterLocation.setEnabled(false);
        this.expListView.setEnabled(false);
        this.googSearch.setEnabled(false);
        arrayList = this.currentMarkers;
        if (arrayList != null && arrayList.size() > 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(((PointInfo)this.currentMarkers.get(0)).getName());
          stringBuilder.append(" - ");
          stringBuilder.append(((PointInfo)this.currentMarkers.get(0)).getName());
          startSendRoute(stringBuilder.toString(), (((PointInfo)this.currentMarkers.get(0)).getLatLng()).latitude, (((PointInfo)this.currentMarkers.get(0)).getLatLng()).longitude, paramCustomActionButton1, paramCustomActionButton2);
        } 
      } else {
        setActionButtons();
      } 
    } else {
      String str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_lbl_busquedaalertamensaje_1, 2131690147);
      Toast.makeText((Context)activity, str, 1).show();
    } 
  }
  
  public void setActionButtons() {
    try {
      Dialog dialog = Utilities.simpleDialog((Context)activity, null, global_popup_lbl_aviso_1, global_popup_lbl_accionencurso_1, true, global_popup_btn_ok_1, false, global_popup_btn_no_1, 20.0F, 20.0F);
      this.buttonOk = (Button)dialog.findViewById(2131296343);
      Button button = this.buttonOk;
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
  
  public void setDisableOrientation() {
    OrientationManager.unlockOrientation((Activity)this);
  }
  
  public void setListCollapse(boolean paramBoolean) {
    this.listCollapse = paramBoolean;
  }
  
  public void setMarkersMap(double paramDouble1, double paramDouble2, String paramString) {
    enableSendRouteShareLocationGoToRoute();
    LatLng latLng = new LatLng(paramDouble1, paramDouble2);
    this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0F));
    PointInfo pointInfo = new PointInfo(paramString, null, latLng, null);
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
  
  public void setShow(ShowViewElement paramShowViewElement) {
    this.show = paramShowViewElement;
  }
  
  public void setStateTutorial(boolean paramBoolean) {}
  
  public void setTypeFloating(String paramString) {
    this.typeFloating = paramString;
  }
  
  public void setVehicleList() {
    this.vehicle = new ArrayList<String>();
    this.vehicleListC.clear();
    for (byte b = 0; b < this.rtApp.getmDeviceUserList().size(); b++) {
      this.vehicle.add(((UserDevicesVO)this.rtApp.getmDeviceUserList().get(b)).getName());
      UserDevicesVO userDevicesVO = this.rtApp.getmDeviceUserList().get(b);
      if (userDevicesVO != null)
        userDevicesVO.setDateLastFindeMe(""); 
      this.vehicleListC.add(userDevicesVO);
    } 
    if (this.vehicle != null) {
      this.dataAdapter = new ShareVehicleListAdapter((Context)activity, this.vehicleListC, Integer.valueOf(Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId()).intValue());
      this.vehicleListShare.setAdapter((ListAdapter)this.dataAdapter);
      this.dataAdapter.notifyDataSetChanged();
    } 
  }
  
  public ClassElements setViewMaps() {
    ClassElements classElements = new ClassElements();
    String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_sigueme, 2131690463);
    ShowViewElement.setValues(classElements, this.mapFragment.getView(), 0, str, "solo", 0, getResources().getInteger(2131361821), false, getResources().getInteger(2131361812), 0, false);
    return classElements;
  }
  
  public ClassElements setViews() {
    ClassElements classElements = new ClassElements();
    int i = this.position;
    if (i == 0) {
      String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_buscardireccion, 2131690441);
      Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_localicevehiculo, 2131690452);
      Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_enviedestino, 2131690446);
      String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_favoritos, 2131690447);
      Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_compartir, 2131690443);
      i = -getResources().getInteger(2131361823);
      ViewGroup viewGroup = this.viewGroupTomTomSearchBar;
      if (viewGroup != null)
        ShowViewElement.setValues(classElements, (View)viewGroup, 0, str3, "centro", -120, getResources().getInteger(2131361814), false, i, getResources().getInteger(2131361805), false); 
      ImageButton imageButton = imageButtonFavs;
      if (imageButton != null)
        ShowViewElement.setValues(classElements, (View)imageButton, 1, str1, "rectaAbajo", 0, getResources().getInteger(2131361812), false, -getResources().getInteger(2131361806), -getResources().getInteger(2131361805), false); 
      str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_actualicelocalizacion, 2131690437);
      ImageView imageView2 = this.imageButtonCenterLocation;
      if (imageView2 != null)
        ShowViewElement.setValues(classElements, (View)imageView2, 2, str1, "izquierdaArriba", 0, getResources().getInteger(2131361812), false, -getResources().getInteger(2131361814), 0, false); 
      String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_modomapa_configurar, 2131690456);
      ImageView imageView1 = this.imageButtonCenterLocation;
      if (imageView1 != null)
        ShowViewElement.setValues(classElements, (View)imageView1, 3, str2, "izquierdaArriba", 0, getResources().getInteger(2131361816), false, getResources().getInteger(2131361805), 0, true); 
    } else if (i == 1) {
      String str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_actualicelocalizacion, 2131690437);
      ShowViewElement.setValues(classElements, (View)this.imageButtonCenterLocation, 0, str, "izquierdaAbajo", 0, getResources().getInteger(2131361805), false, getResources().getInteger(2131361806), 0, false);
      str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.tutorial_lbl_modomapa_configurar, 2131690456);
      ShowViewElement.setValues(classElements, (View)this.imageButtonCenterLocation, 1, str, "izquierdaArriba", 0, getResources().getInteger(2131361812), false, -getResources().getInteger(2131361814), 0, true);
    } 
    return classElements;
  }
  
  public void setmExpandableListAdapter(ExpandableResultsAdapter paramExpandableResultsAdapter) {
    this.mExpandableListAdapter = paramExpandableResultsAdapter;
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
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(d2);
      str5 = stringBuilder.toString();
      stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(d1);
      fakeGoogleSearchResult("REFERENCE", str5, stringBuilder.toString(), str1, str2, false, false);
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
  
  public void showPointDetail(double paramDouble1, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2, int paramInt) {
    if (!isRouteInProgress && this.mapType.equals(Enums.navigationProcess.Navigation)) {
      this.isLongPress = true;
      if (paramBoolean2) {
        functionMarker(paramDouble1, paramDouble2, paramBoolean1, paramInt, paramBoolean2);
      } else {
        functionMarker(paramDouble1, paramDouble2, paramBoolean1, paramInt, paramBoolean2);
      } 
    } 
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.googSearch.getApplicationWindowToken(), 2);
    if (lvSavedEditSearch.getVisibility() == 0) {
      ListView listView = lvSavedEditSearch;
      if (listView != null)
        listView.setVisibility(8); 
    } 
    GlobalMembers.iconFav = false;
  }
  
  public void showTutorial() {
    this.pressTuto = false;
    this.position = 0;
    Enums.navigationProcess navigationProcess1 = this.mapType;
    if (navigationProcess1 != null && navigationProcess1.equals(Enums.navigationProcess.FollowMe)) {
      ClassElements classElements = setViewMaps();
      ActivityMapViewerG activityMapViewerG = activity;
      this.show = new ShowViewElement((Context)activityMapViewerG, (Activity)activityMapViewerG, classElements);
      this.show.setPages(1);
    } else {
      ClassElements classElements = setViews();
      ActivityMapViewerG activityMapViewerG = activity;
      this.show = new ShowViewElement((Context)activityMapViewerG, (Activity)activityMapViewerG, classElements);
      this.show.setPages(1);
    } 
  }
  
  public void startSendRoute(String paramString, double paramDouble1, double paramDouble2, CustomActionButton paramCustomActionButton1, final CustomActionButton initRoute) {
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
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(str1.substring(0, 83));
          stringBuilder1.append("...");
          str3 = stringBuilder1.toString();
        } 
      } 
      this.isActionRunning = true;
      disableBottomButtons(Enums.Services.SendPNDNavigationCommand);
      double[] arrayOfDouble = new double[2];
      arrayOfDouble[0] = paramDouble1;
      arrayOfDouble[1] = paramDouble2;
      str1 = this.rtApp.getSessionKey();
      paramString = this.rtApp.getUserAccessData()[1];
      String str4 = this.rtApp.getAccountID();
      String str6 = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG).getDeviceId();
      String str5 = this.rtApp.getLocatorUserId();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(arrayOfDouble[1]);
      stringBuilder.append("");
      String str8 = stringBuilder.toString();
      stringBuilder = new StringBuilder();
      stringBuilder.append(arrayOfDouble[0]);
      stringBuilder.append("");
      String str7 = String.format("%s|%s|%s|3", new Object[] { str8, stringBuilder.toString(), str3 });
      ActionsProcess actionsProcess = new ActionsProcess((Context)activity, paramCustomActionButton1, str2, str6, (Activity)this);
      actionsProcess.setAddress(str3);
      actionsProcess.setOnPostExecuteListener(new ActionsProcess.OnPostExecuteListener() {
            final ActivityMapViewerG this$0;
            
            final CustomActionButton val$initRoute;
            
            public void onPostExecuteListener(ActionResultVO param1ActionResultVO) {
              ActivityMapViewerG.onSendRoute = false;
              if (ActivityMapViewerG.this.fab.getVisibility() == 0)
                ActivityMapViewerG.this.fab.setEnabled(true); 
              if (ActivityMapViewerG.this.fabLans.getVisibility() == 0)
                ActivityMapViewerG.this.fabLans.setEnabled(true); 
              ActivityMapViewerG.this.googSearch.setEnabled(true);
              ActivityMapViewerG.imageButtonFavs.setAlpha(1.0F);
              ActivityMapViewerG.imageButtonFavs.setEnabled(true);
              initRoute.setAlpha(1.0F);
              initRoute.setEnabled(true);
              ActivityMapViewerG.this.imageButtonCenterLocation.setEnabled(true);
              ActivityMapViewerG.this.expListView.setEnabled(true);
              ActivityMapViewerG.this.enableBottomButtons();
              if (param1ActionResultVO.getIdResponse().toString().equals(Enums.ActionResultCode.Activated.toString()))
                ActivityMapViewerG.this.dialogBackToMainSendToRoute(); 
            }
          });
      actionsProcess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str1, paramString, str4, str5, str7, "" });
    } 
  }
  
  private class LeftSlideMenuClickListener implements AdapterView.OnItemClickListener {
    final ActivityMapViewerG this$0;
    
    private LeftSlideMenuClickListener() {}
    
    public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      if (param1Int != 0) {
        if (param1Int != 1) {
          if (param1Int == 2) {
            ActivityMapViewerG.this.generateEmergencyDialog();
            ActivityMapViewerG.this.closeLeftNavigationDrawer();
          } 
        } else {
          ActivityMapViewerG.this.generateAssistanceDialog();
          ActivityMapViewerG.this.closeLeftNavigationDrawer();
        } 
      } else {
        if (ActivityMapViewerG.this.ValidateTheftAuto())
          return; 
        ActivityMapViewerG.this.startActivityForFavourites();
        ActivityMapViewerG.this.closeLeftNavigationDrawer();
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/googleMaps/ActivityMapViewerG.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */