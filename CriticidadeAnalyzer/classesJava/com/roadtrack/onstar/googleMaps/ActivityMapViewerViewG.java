package com.roadtrack.onstar.googleMaps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
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
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.floatingActionButton.FloatingActionButton;
import com.roadtrack.onstar.floatingActionButton.FloatingActionsMenu;
import com.roadtrack.onstar.mapData.MapController;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;

public class ActivityMapViewerViewG {
  private final ActivityMapViewerG activity;
  
  private MapController controller;
  
  private FloatingActionButton floating1;
  
  private FloatingActionButton floating2;
  
  private FloatingActionButton floatingDayNight;
  
  private FloatingActionButton floatingSettings;
  
  private FloatingActionButton floatingTraffic;
  
  private ImageView imageMap;
  
  private ImageView imageMapLand;
  
  private ImageView iv_share;
  
  private LatLng latLng;
  
  private TextView lblVehicleListHeader;
  
  private ArrayList<FloatingActionButton> listButtons;
  
  private ArrayList<FloatingActionButton> listButtonsLans;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private TextView txt_compartir;
  
  private TextView txt_enviarruta;
  
  private TextView txt_localizame;
  
  private TextView txt_shareFavorites;
  
  private TextView txt_shareheader;
  
  @SuppressLint({"NewApi"})
  public ActivityMapViewerViewG(ActivityMapViewerG paramActivityMapViewerG) {
    this.activity = paramActivityMapViewerG;
    paramActivityMapViewerG.setContentView(2131427367);
    this.stringsResourcesVO = new StringsResourcesVO();
    this.txt_localizame = (TextView)paramActivityMapViewerG.findViewById(2131297200);
    String str = Utilities.getStringFromConfigList((Context)paramActivityMapViewerG, this.stringsResourcesVO.global_lbl_accionlocalizame_1, 2131689877);
    this.txt_localizame.setText(str);
    this.txt_enviarruta = (TextView)paramActivityMapViewerG.findViewById(2131297197);
    str = Utilities.getStringFromConfigList((Context)paramActivityMapViewerG, this.stringsResourcesVO.navigation_lbl_enviarruta, 2131690153);
    this.txt_enviarruta.setText(str);
    this.txt_shareFavorites = (TextView)paramActivityMapViewerG.findViewById(2131297208);
    str = Utilities.getStringFromConfigList((Context)paramActivityMapViewerG, this.stringsResourcesVO.global_lbl_fav_1, 2131689923);
    this.txt_shareFavorites.setText(str);
    this.txt_compartir = (TextView)paramActivityMapViewerG.findViewById(2131297195);
    str = Utilities.getStringFromConfigList((Context)paramActivityMapViewerG, this.stringsResourcesVO.navigation_lbl_compartir, 2131690149);
    this.txt_compartir.setText(str);
    this.txt_shareheader = (TextView)paramActivityMapViewerG.findViewById(2131297209);
    str = Utilities.getStringFromConfigList((Context)paramActivityMapViewerG, this.stringsResourcesVO.navigation_share_lbl_compartirubicacionvehic_1, 2131690191);
    this.txt_shareheader.setText(str);
    this.lblVehicleListHeader = (TextView)paramActivityMapViewerG.findViewById(2131296716);
    str = Utilities.getStringFromConfigList((Context)paramActivityMapViewerG, this.stringsResourcesVO.navigation_share_lbl_vehiculos_2, 2131690197);
    this.lblVehicleListHeader.setText(str);
    this.iv_share = (ImageView)paramActivityMapViewerG.findViewById(2131296676);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)paramActivityMapViewerG, DrawableResourcesVO.ic_menu_share, 2131165491);
    this.iv_share.setImageDrawable(drawable);
    this.imageMap = (ImageView)paramActivityMapViewerG.findViewById(2131296670);
    drawable = Utilities.getDrawableFromConfigList((Context)paramActivityMapViewerG, DrawableResourcesVO.map_settings, 2131165563);
    this.imageMap.setImageDrawable(drawable);
    this.imageMapLand = (ImageView)paramActivityMapViewerG.findViewById(2131296671);
    this.imageMapLand.setImageDrawable(drawable);
  }
  
  private Drawable getIconPoi(String paramString) {
    Drawable drawable;
    this.stringsResourcesVO = new StringsResourcesVO();
    String str4 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_1_cafeterias, 2131689612);
    String str5 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_2_restaurantes, 2131689613);
    String str1 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_3_gasolineria, 2131689614);
    String str3 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_4_atm, 2131689615);
    String str6 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_5_supermercados, 2131689616);
    String str2 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_6_farmacias, 2131689617);
    if (paramString.equalsIgnoreCase(str4)) {
      drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.icon_coffeshop, 2131165551);
    } else if (drawable.equalsIgnoreCase(str5)) {
      drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.icon_restaurant, 2131165555);
    } else if (drawable.equalsIgnoreCase(str1)) {
      drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.icon_restaurant, 2131165552);
    } else if (drawable.equalsIgnoreCase(str3)) {
      drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.icon_atm, 2131165549);
    } else if (drawable.equalsIgnoreCase(str6)) {
      drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.icon_supermarket, 2131165556);
    } else if (drawable.equalsIgnoreCase(str2)) {
      drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.icon_pharmacy, 2131165554);
    } else {
      drawable = null;
    } 
    return drawable;
  }
  
  public LinearLayout getAllMenu() {
    return (LinearLayout)this.activity.findViewById(2131296596);
  }
  
  public LinearLayout getBottomContainer() {
    return (LinearLayout)this.activity.findViewById(2131296390);
  }
  
  public RelativeLayout getContainerFloatingMenu() {
    return (RelativeLayout)this.activity.findViewById(2131296992);
  }
  
  public RelativeLayout getContainerFloatingMenuLans() {
    return (RelativeLayout)this.activity.findViewById(2131296993);
  }
  
  public DrawerLayout getDrawerLayout() {
    return (DrawerLayout)this.activity.findViewById(2131297133);
  }
  
  public EditText getEditTextGoogleSearch() {
    return (EditText)this.activity.findViewById(2131297027);
  }
  
  public ExpandableListView getExpandableListViewSearch() {
    return (ExpandableListView)this.activity.findViewById(2131297020);
  }
  
  public RelativeLayout getFavsGroup() {
    return (RelativeLayout)this.activity.findViewById(2131296533);
  }
  
  public RelativeLayout getFindMeGroup() {
    return (RelativeLayout)this.activity.findViewById(2131296540);
  }
  
  public FloatingActionsMenu getFloating() {
    return (FloatingActionsMenu)this.activity.findViewById(2131296526);
  }
  
  public FloatingActionsMenu getFloatingLans() {
    return (FloatingActionsMenu)this.activity.findViewById(2131296529);
  }
  
  public FrameLayout getFrameContainer() {
    return (FrameLayout)this.activity.findViewById(2131296552);
  }
  
  public RelativeLayout getGoTorouteGroup() {
    return (RelativeLayout)this.activity.findViewById(2131296569);
  }
  
  public ImageView getImageButtonCenterLocation() {
    return (ImageView)this.activity.findViewById(2131296616);
  }
  
  public ImageButton getImageButtonFavs() {
    return (ImageButton)this.activity.findViewById(2131296431);
  }
  
  public ImageButton getImageButtonFindMe() {
    return (ImageButton)this.activity.findViewById(2131296432);
  }
  
  public ImageButton getImageButtonGoToRoute() {
    return (ImageButton)this.activity.findViewById(2131296570);
  }
  
  public ImageButton getImageButtonSendRoute() {
    return (ImageButton)this.activity.findViewById(2131296433);
  }
  
  public ImageButton getImageButtonShareLocation() {
    return (ImageButton)this.activity.findViewById(2131296434);
  }
  
  public ImageView getImageViewFindMe() {
    return (ImageView)this.activity.findViewById(2131296277);
  }
  
  public ImageView getImageViewGoToRoute() {
    return (ImageView)this.activity.findViewById(2131296278);
  }
  
  public ImageView getImageViewSendRoute() {
    return (ImageView)this.activity.findViewById(2131296279);
  }
  
  public ImageView getImageViewShare() {
    return (ImageView)this.activity.findViewById(2131296280);
  }
  
  public ImageView getImageViewfavs() {
    return (ImageView)this.activity.findViewById(2131296281);
  }
  
  public LinearLayout getLinearLayoutBottomMenu() {
    return (LinearLayout)this.activity.findViewById(2131296596);
  }
  
  public LinearLayout getLinearShareTopMenuLand() {
    return (LinearLayout)this.activity.findViewById(2131296814);
  }
  
  public LinearLayout getLinearShareTopMenuPortrait() {
    return (LinearLayout)this.activity.findViewById(2131296815);
  }
  
  public ListView getListViewSavedItems() {
    return (ListView)this.activity.findViewById(2131296833);
  }
  
  public ListView getListViewVehicles() {
    return (ListView)this.activity.findViewById(2131297227);
  }
  
  public LinearLayout getParentLinearLayout() {
    return (LinearLayout)this.activity.findViewById(2131296808);
  }
  
  public ProgressBar getProgressBarFavs() {
    return (ProgressBar)this.activity.findViewById(2131296530);
  }
  
  public ProgressBar getProgressBarFindMe() {
    return (ProgressBar)this.activity.findViewById(2131296881);
  }
  
  public ProgressBar getProgressBarGoToRoute() {
    return (ProgressBar)this.activity.findViewById(2131296882);
  }
  
  public ProgressBar getProgressBarSendRoute() {
    return (ProgressBar)this.activity.findViewById(2131296883);
  }
  
  public ProgressBar getProgressBarShare() {
    return (ProgressBar)this.activity.findViewById(2131296884);
  }
  
  public ProgressBar getProgressBarShareHeader() {
    return (ProgressBar)this.activity.findViewById(2131296960);
  }
  
  public RelativeLayout getRelativeLayoutVehiclesHeader() {
    return (RelativeLayout)this.activity.findViewById(2131296692);
  }
  
  public RelativeLayout getSendRouteGroup() {
    return (RelativeLayout)this.activity.findViewById(2131296997);
  }
  
  public RelativeLayout getShareGroup() {
    return (RelativeLayout)this.activity.findViewById(2131297031);
  }
  
  public ImageView getShareImageViewFindMe() {
    return (ImageView)this.activity.findViewById(2131297246);
  }
  
  public LinearLayout getShareLocationBottomList() {
    return (LinearLayout)this.activity.findViewById(2131296889);
  }
  
  public LinearLayout getShareLocationTopMenu() {
    return (LinearLayout)this.activity.findViewById(2131297033);
  }
  
  public LinearLayout getShareLocationTopMenu2() {
    return (LinearLayout)this.activity.findViewById(2131297034);
  }
  
  public ImageView getSplashScreen() {
    return (ImageView)this.activity.findViewById(2131297046);
  }
  
  public TextView getTextViewShareHeader() {
    return (TextView)this.activity.findViewById(2131297209);
  }
  
  public ViewGroup getViewGroupTomTomSearchBar() {
    return (ViewGroup)this.activity.findViewById(2131297132);
  }
  
  public void resetFloating(String paramString1, String paramString2) {
    Drawable drawable = getIconPoi(paramString1);
    ((FloatingActionButton)this.listButtons.get(2)).setIconDrawable(drawable);
    drawable = getIconPoi(paramString2);
    ((FloatingActionButton)this.listButtons.get(3)).setIconDrawable(drawable);
  }
  
  public void resetFloatingLans(String paramString1, String paramString2) {
    Drawable drawable = getIconPoi(paramString1);
    ((FloatingActionButton)this.listButtonsLans.get(2)).setIconDrawable(drawable);
    drawable = getIconPoi(paramString2);
    ((FloatingActionButton)this.listButtonsLans.get(3)).setIconDrawable(drawable);
  }
  
  public FloatingActionButton setDataMaps(String paramString, int paramInt) {
    Drawable drawable = getIconPoi(paramString);
    return this.controller.setButtons((Context)this.activity, paramInt, drawable);
  }
  
  public void setFloating(GoogleMap paramGoogleMap, ActivityMapViewerG paramActivityMapViewerG) {
    this.controller = new MapController(paramGoogleMap, (Activity)this.activity, this, getExpandableListViewSearch());
    this.controller.setLatLng(this.latLng);
    this.listButtons = new ArrayList<FloatingActionButton>();
    String str6 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_3_gasolineria, 2131689614);
    String str5 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_4_atm, 2131689615);
    String str4 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi1, "", onstarApplication.getContext());
    String str3 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi2, "", onstarApplication.getContext());
    String str2 = str4;
    String str1 = str3;
    if (str4.equalsIgnoreCase("")) {
      str2 = str4;
      str1 = str3;
      if (str3.equalsIgnoreCase("")) {
        boolean bool1 = PreferenceRT.SetStringPreference(GlobalMembers.valuePoi1, str6, onstarApplication.getContext());
        boolean bool2 = PreferenceRT.SetStringPreference(GlobalMembers.valuePoi2, str5, onstarApplication.getContext());
        str2 = str4;
        str1 = str3;
        if (bool1) {
          str2 = str4;
          str1 = str3;
          if (bool2) {
            str2 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi1, str6, onstarApplication.getContext());
            str1 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi2, str5, onstarApplication.getContext());
          } 
        } 
      } 
    } 
    this.controller.setValueFind1(str2);
    this.controller.setValueFind2(str1);
    Drawable drawable2 = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.ic_settings, 2131165516);
    drawable2.setColorFilter((ColorFilter)new LightingColorFilter(this.activity.getResources().getColor(2131034173), this.activity.getResources().getColor(2131034173)));
    this.floatingSettings = this.controller.setButtons((Context)paramActivityMapViewerG, 5, drawable2);
    this.floatingSettings.setOnClickListener(this.controller.clickListener);
    getFloating().addButton(this.floatingSettings);
    this.floating1 = setDataMaps(str1, 4);
    this.floating1.setOnClickListener(this.controller.clickListener);
    getFloating().addButton(this.floating1);
    this.floating2 = setDataMaps(str2, 3);
    this.floating2.setOnClickListener(this.controller.clickListener);
    getFloating().addButton(this.floating2);
    Drawable drawable1 = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.ic_night, 2131165496);
    this.floatingDayNight = this.controller.setButtons((Context)paramActivityMapViewerG, 2, drawable1);
    this.floatingDayNight.setOnClickListener(this.controller.clickListener);
    getFloating().addButton(this.floatingDayNight);
    drawable1 = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.icon_traffic_enable, 2131165557);
    this.floatingTraffic = this.controller.setButtons((Context)paramActivityMapViewerG, 1, drawable1);
    this.floatingTraffic.setOnClickListener(this.controller.clickListener);
    getFloating().addButton(this.floatingTraffic);
    this.listButtons.add(this.floatingTraffic);
    this.listButtons.add(this.floatingDayNight);
    this.listButtons.add(this.floating2);
    this.listButtons.add(this.floating1);
    this.listButtons.add(this.floatingSettings);
  }
  
  public void setFloatingLans(GoogleMap paramGoogleMap, ActivityMapViewerG paramActivityMapViewerG) {
    this.controller = new MapController(paramGoogleMap, (Activity)this.activity, this, getExpandableListViewSearch());
    this.controller.setLatLng(this.latLng);
    this.listButtonsLans = new ArrayList<FloatingActionButton>();
    String str6 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_3_gasolineria, 2131689614);
    String str5 = Utilities.getStringFromConfigList((Context)this.activity, this.stringsResourcesVO.accesos_rapidos_mapa_txt_4_atm, 2131689615);
    String str3 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi1, "", onstarApplication.getContext());
    String str4 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi2, "", onstarApplication.getContext());
    String str2 = str3;
    String str1 = str4;
    if (str3.equalsIgnoreCase("")) {
      str2 = str3;
      str1 = str4;
      if (str4.equalsIgnoreCase("")) {
        boolean bool1 = PreferenceRT.SetStringPreference(GlobalMembers.valuePoi1, str6, onstarApplication.getContext());
        boolean bool2 = PreferenceRT.SetStringPreference(GlobalMembers.valuePoi2, str5, onstarApplication.getContext());
        str2 = str3;
        str1 = str4;
        if (bool1) {
          str2 = str3;
          str1 = str4;
          if (bool2) {
            str2 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi1, str6, onstarApplication.getContext());
            str1 = PreferenceRT.GetValuePreference(GlobalMembers.valuePoi2, str5, onstarApplication.getContext());
          } 
        } 
      } 
    } 
    this.controller.setValueFind1(str2);
    this.controller.setValueFind2(str1);
    Drawable drawable2 = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.ic_settings, 2131165516);
    drawable2.setColorFilter((ColorFilter)new LightingColorFilter(this.activity.getResources().getColor(2131034170), this.activity.getResources().getColor(2131034170)));
    this.floatingSettings = this.controller.setButtons((Context)paramActivityMapViewerG, 5, drawable2);
    this.floatingSettings.setOnClickListener(this.controller.clickListener);
    getFloatingLans().addButton(this.floatingSettings);
    this.floating1 = setDataMaps(str1, 4);
    this.floating1.setOnClickListener(this.controller.clickListener);
    getFloatingLans().addButton(this.floating1);
    this.floating2 = setDataMaps(str2, 3);
    this.floating2.setOnClickListener(this.controller.clickListener);
    getFloatingLans().addButton(this.floating2);
    Drawable drawable1 = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.ic_night, 2131165496);
    this.floatingDayNight = this.controller.setButtons((Context)paramActivityMapViewerG, 2, drawable1);
    this.floatingDayNight.setOnClickListener(this.controller.clickListener);
    getFloatingLans().addButton(this.floatingDayNight);
    drawable1 = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.icon_traffic_enable, 2131165557);
    this.floatingTraffic = this.controller.setButtons((Context)paramActivityMapViewerG, 1, drawable1);
    this.floatingTraffic.setOnClickListener(this.controller.clickListener);
    getFloatingLans().addButton(this.floatingTraffic);
    this.listButtonsLans.add(this.floatingTraffic);
    this.listButtonsLans.add(this.floatingDayNight);
    this.listButtonsLans.add(this.floating2);
    this.listButtonsLans.add(this.floating1);
    this.listButtonsLans.add(this.floatingSettings);
  }
  
  public void setLatLng(LatLng paramLatLng) {
    this.latLng = paramLatLng;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/googleMaps/ActivityMapViewerViewG.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */