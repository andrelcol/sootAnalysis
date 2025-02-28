package com.roadtrack.onstar.googleMaps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.floatingActionButton.FloatingActionButton;
import com.roadtrack.onstar.floatingActionButton.FloatingActionsMenu;
import com.roadtrack.onstar.mapData.MapController;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;

public class MapsFragmentView {
  private final MainActivity activity;
  
  private MapController controller;
  
  private FloatingActionsMenu fab;
  
  private FloatingActionButton floating1;
  
  private FloatingActionButton floating2;
  
  private FloatingActionButton floatingDayNight;
  
  private FloatingActionButton floatingTraffic;
  
  private ImageView iv_share;
  
  private LatLng latLng;
  
  private TextView lblVehicleListHeader;
  
  private MapsFragment mapsFragment;
  
  private ImageView pinOverMap;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private TextView txt_compartir;
  
  private TextView txt_enviarruta;
  
  private TextView txt_localizame;
  
  private TextView txt_shareFavorites;
  
  private TextView txt_shareheader;
  
  private View view;
  
  @SuppressLint({"NewApi"})
  public MapsFragmentView(MapsFragment paramMapsFragment, View paramView) {
    this.mapsFragment = paramMapsFragment;
    this.activity = (MainActivity)paramMapsFragment.getActivity();
    this.view = paramView;
    this.stringsResourcesVO = new StringsResourcesVO();
    FrameLayout frameLayout = (FrameLayout)paramView.findViewById(2131296550);
    this.txt_localizame = (TextView)paramView.findViewById(2131297200);
    String str = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.global_lbl_accionlocalizame_1, 2131689877);
    this.txt_localizame.setText(str);
    this.txt_enviarruta = (TextView)paramView.findViewById(2131297197);
    str = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.navigation_lbl_enviarruta, 2131690153);
    this.txt_enviarruta.setText(str);
    this.txt_shareFavorites = (TextView)paramView.findViewById(2131297208);
    str = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.global_lbl_fav_1, 2131689923);
    this.txt_shareFavorites.setText(str);
    this.txt_compartir = (TextView)paramView.findViewById(2131297195);
    str = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.navigation_lbl_compartir, 2131690149);
    this.txt_compartir.setText(str);
    this.txt_shareheader = (TextView)paramView.findViewById(2131297209);
    str = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.navigation_share_lbl_compartirubicacionvehic_1, 2131690191);
    this.txt_shareheader.setText(str);
    this.lblVehicleListHeader = (TextView)paramView.findViewById(2131296716);
    str = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.navigation_share_lbl_vehiculos_2, 2131690197);
    this.lblVehicleListHeader.setText(str);
    this.iv_share = (ImageView)paramView.findViewById(2131296676);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.ic_menu_share, 2131165491);
    this.iv_share.setImageDrawable(drawable);
    if (Utilities.isAndinos().booleanValue()) {
      this.pinOverMap = (ImageView)paramView.findViewById(2131296955);
      drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.pin_ubication_medium_andinos, 2131165645);
      this.pinOverMap.setImageDrawable(drawable);
    } 
  }
  
  public LinearLayout getAllMenu() {
    return (LinearLayout)this.view.findViewById(2131296596);
  }
  
  public LinearLayout getBottomContainer() {
    return (LinearLayout)this.view.findViewById(2131296390);
  }
  
  public RelativeLayout getContainerFloatingMenu() {
    return (RelativeLayout)this.view.findViewById(2131296992);
  }
  
  public DrawerLayout getDrawerLayout() {
    return (DrawerLayout)this.view.findViewById(2131297133);
  }
  
  public EditText getEditTextGoogleSearch() {
    return (EditText)this.view.findViewById(2131297027);
  }
  
  public ExpandableListView getExpandableListViewData() {
    return (ExpandableListView)this.view.findViewById(2131296489);
  }
  
  public ExpandableListView getExpandableListViewSearch() {
    return (ExpandableListView)this.view.findViewById(2131297020);
  }
  
  public RelativeLayout getFavsGroup() {
    return (RelativeLayout)this.view.findViewById(2131296533);
  }
  
  public RelativeLayout getFindMeGroup() {
    return (RelativeLayout)this.view.findViewById(2131296540);
  }
  
  public FloatingActionsMenu getFloating() {
    if (this.fab == null)
      this.fab = (FloatingActionsMenu)this.view.findViewById(2131296526); 
    return this.fab;
  }
  
  public FrameLayout getFrameContainer() {
    return (FrameLayout)this.view.findViewById(2131296552);
  }
  
  public RelativeLayout getGoTorouteGroup() {
    return (RelativeLayout)this.view.findViewById(2131296569);
  }
  
  public ImageButton getImageButtonCenterLocation() {
    return (ImageButton)this.view.findViewById(2131296616);
  }
  
  public ImageButton getImageButtonFavs() {
    return (ImageButton)this.view.findViewById(2131296431);
  }
  
  public ImageButton getImageButtonFindMe() {
    return (ImageButton)this.view.findViewById(2131296432);
  }
  
  public ImageButton getImageButtonGoToRoute() {
    return (ImageButton)this.view.findViewById(2131296570);
  }
  
  public ImageButton getImageButtonSendRoute() {
    return (ImageButton)this.view.findViewById(2131296433);
  }
  
  public ImageButton getImageButtonShareLocation() {
    return (ImageButton)this.view.findViewById(2131296434);
  }
  
  public ImageView getImageViewFindMe() {
    return (ImageView)this.view.findViewById(2131296277);
  }
  
  public ImageView getImageViewGoToRoute() {
    return (ImageView)this.view.findViewById(2131296278);
  }
  
  public ImageView getImageViewSendRoute() {
    return (ImageView)this.view.findViewById(2131296279);
  }
  
  public ImageView getImageViewShare() {
    return (ImageView)this.view.findViewById(2131296280);
  }
  
  public ImageView getImageViewfavs() {
    return (ImageView)this.view.findViewById(2131296281);
  }
  
  public LinearLayout getLinearLayoutBottomMenu() {
    return (LinearLayout)this.view.findViewById(2131296596);
  }
  
  public LinearLayout getLinearLayoutRoutenavheader() {
    return (LinearLayout)this.view.findViewById(2131296998);
  }
  
  public ListView getListViewSavedItems() {
    return (ListView)this.view.findViewById(2131296833);
  }
  
  public ListView getListViewVehicles() {
    return (ListView)this.view.findViewById(2131297227);
  }
  
  public ProgressBar getProgessBarVehicles() {
    return (ProgressBar)this.view.findViewById(2131296541);
  }
  
  public ProgressBar getProgressBarFavs() {
    return (ProgressBar)this.view.findViewById(2131296530);
  }
  
  public ProgressBar getProgressBarFindMe() {
    return (ProgressBar)this.view.findViewById(2131296881);
  }
  
  public ProgressBar getProgressBarGoToRoute() {
    return (ProgressBar)this.view.findViewById(2131296882);
  }
  
  public ProgressBar getProgressBarSendRoute() {
    return (ProgressBar)this.view.findViewById(2131296883);
  }
  
  public ProgressBar getProgressBarShare() {
    return (ProgressBar)this.view.findViewById(2131296884);
  }
  
  public ProgressBar getProgressBarShareHeader() {
    return (ProgressBar)this.view.findViewById(2131296960);
  }
  
  public RelativeLayout getRelativeLayoutVehiclesHeader() {
    return (RelativeLayout)this.view.findViewById(2131296692);
  }
  
  public RelativeLayout getSendRouteGroup() {
    return (RelativeLayout)this.view.findViewById(2131296997);
  }
  
  public RelativeLayout getShareGroup() {
    return (RelativeLayout)this.view.findViewById(2131297031);
  }
  
  public ImageView getShareImageViewFindMe() {
    return (ImageView)this.view.findViewById(2131297246);
  }
  
  public LinearLayout getShareLocationBottomList() {
    return (LinearLayout)this.view.findViewById(2131296889);
  }
  
  public LinearLayout getShareLocationTopMenu() {
    return (LinearLayout)this.view.findViewById(2131297033);
  }
  
  public ImageView getSplashScreen() {
    return (ImageView)this.view.findViewById(2131297046);
  }
  
  public ViewGroup getViewGroupTomTomSearchBar() {
    return (ViewGroup)this.view.findViewById(2131297132);
  }
  
  public ViewGroup getViewGroupTopInstructions() {
    return (ViewGroup)this.view.findViewById(2131296319);
  }
  
  public FloatingActionButton setDataMaps(String paramString, int paramInt) {
    FloatingActionButton floatingActionButton;
    String str1 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_1_cafeterias, 2131689612);
    String str6 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_2_restaurantes, 2131689613);
    String str3 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_3_gasolineria, 2131689614);
    String str4 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_4_atm, 2131689615);
    String str5 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_5_supermercados, 2131689616);
    String str2 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_6_farmacias, 2131689617);
    if (paramString.equalsIgnoreCase(str1)) {
      MapController mapController = this.controller;
      MainActivity mainActivity = this.activity;
      floatingActionButton = mapController.setButtons((Context)mainActivity, paramInt, Utilities.getDrawableFromConfigList((Context)mainActivity, DrawableResourcesVO.icon_coffeshop, 2131165551));
    } else {
      FloatingActionButton floatingActionButton1;
      if (floatingActionButton.equalsIgnoreCase(str6)) {
        MapController mapController = this.controller;
        MainActivity mainActivity = this.activity;
        floatingActionButton1 = mapController.setButtons((Context)mainActivity, paramInt, Utilities.getDrawableFromConfigList((Context)mainActivity, DrawableResourcesVO.icon_restaurant, 2131165555));
      } else {
        FloatingActionButton floatingActionButton2;
        if (floatingActionButton1.equalsIgnoreCase(str3)) {
          MapController mapController = this.controller;
          MainActivity mainActivity = this.activity;
          floatingActionButton2 = mapController.setButtons((Context)mainActivity, paramInt, Utilities.getDrawableFromConfigList((Context)mainActivity, DrawableResourcesVO.icon_restaurant, 2131165552));
        } else {
          FloatingActionButton floatingActionButton3;
          if (floatingActionButton2.equalsIgnoreCase(str4)) {
            MapController mapController = this.controller;
            MainActivity mainActivity = this.activity;
            floatingActionButton3 = mapController.setButtons((Context)mainActivity, paramInt, Utilities.getDrawableFromConfigList((Context)mainActivity, DrawableResourcesVO.icon_atm, 2131165549));
          } else if (floatingActionButton3.equalsIgnoreCase(str5)) {
            MapController mapController = this.controller;
            MainActivity mainActivity = this.activity;
            floatingActionButton = mapController.setButtons((Context)mainActivity, paramInt, Utilities.getDrawableFromConfigList((Context)mainActivity, DrawableResourcesVO.icon_supermarket, 2131165556));
          } else if (floatingActionButton.equalsIgnoreCase(str2)) {
            MapController mapController = this.controller;
            MainActivity mainActivity = this.activity;
            FloatingActionButton floatingActionButton4 = mapController.setButtons((Context)mainActivity, paramInt, Utilities.getDrawableFromConfigList((Context)mainActivity, DrawableResourcesVO.icon_pharmacy, 2131165554));
          } else {
            floatingActionButton = null;
          } 
        } 
      } 
    } 
    return floatingActionButton;
  }
  
  public void setFloating(GoogleMap paramGoogleMap) {
    this.controller = new MapController(paramGoogleMap, (Activity)this.activity, this, this.mapsFragment, getExpandableListViewSearch(), this.view);
    this.controller.setLatLng(this.latLng);
    String str6 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_3_gasolineria, 2131689614);
    String str5 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_4_atm, 2131689615);
    String str3 = PreferenceRT.GetValuePreference("valor1Mapa", "", onstarApplication.getContext());
    String str4 = PreferenceRT.GetValuePreference("valor2Mapa", "", onstarApplication.getContext());
    String str2 = str3;
    String str1 = str4;
    if (str3.equalsIgnoreCase("")) {
      str2 = str3;
      str1 = str4;
      if (str4.equalsIgnoreCase("")) {
        boolean bool1 = PreferenceRT.SetStringPreference("valor1Mapa", str6, onstarApplication.getContext());
        boolean bool2 = PreferenceRT.SetStringPreference("valor2Mapa", str5, onstarApplication.getContext());
        str2 = str3;
        str1 = str4;
        if (bool1) {
          str2 = str3;
          str1 = str4;
          if (bool2) {
            str2 = PreferenceRT.GetValuePreference("valor1Mapa", str6, onstarApplication.getContext());
            str1 = PreferenceRT.GetValuePreference("valor2Mapa", str5, onstarApplication.getContext());
          } 
        } 
      } 
    } 
    ImageView imageView = (ImageView)this.view.findViewById(2131296670);
    if (Build.VERSION.SDK_INT >= 21) {
      imageView.setImageDrawable(this.activity.getApplication().getResources().getDrawable(2131165563, this.activity.getApplication().getTheme()));
    } else {
      imageView.setImageDrawable(this.activity.getApplication().getResources().getDrawable(2131165563));
    } 
    this.floating1 = setDataMaps(str1, 4);
    this.floating1.setOnClickListener(this.controller.clickListener);
    getFloating().addButton(this.floating1);
    this.floating2 = setDataMaps(str2, 3);
    this.floating2.setOnClickListener(this.controller.clickListener);
    getFloating().addButton(this.floating2);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.ic_night, 2131165496);
    this.floatingDayNight = this.controller.setButtons((Context)this.activity, 2, drawable);
    this.floatingDayNight.setOnClickListener(this.controller.clickListener);
    getFloating().addButton(this.floatingDayNight);
    drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.icon_traffic_enable, 2131165557);
    this.floatingTraffic = this.controller.setButtons((Context)this.activity, 1, drawable);
    this.floatingTraffic.setOnClickListener(this.controller.clickListener);
    getFloating().addButton(this.floatingTraffic);
  }
  
  public void setLatLng(LatLng paramLatLng) {
    this.latLng = paramLatLng;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/googleMaps/MapsFragmentView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */