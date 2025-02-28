package com.roadtrack.onstar.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.CustomActionButton;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.ExpandableIcon_Interface;
import com.roadtrack.onstar.googleMaps.ActivityMapViewerG;
import com.roadtrack.onstar.interfaces.TomTomStartNavigation_Interface;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExpandableResultsAdapter extends BaseExpandableListAdapter {
  public static String KEY_ADDRESS = "address";
  
  public static String KEY_DISTANCE = "distance";
  
  public static String KEY_DISTANCESTRING = "distanceString";
  
  public static String KEY_LATITUDE = "location";
  
  public static String KEY_LONGITUDE = "vicinity";
  
  public static String KEY_NAME = "name";
  
  private Button btn_left;
  
  private Button btn_right;
  
  private CustomActionButton cabSendRoute;
  
  private CheckBox check;
  
  public View.OnClickListener clickEnviarRuta = new View.OnClickListener() {
      final ExpandableResultsAdapter this$0;
      
      public void onClick(View param1View) {
        ActivityMapViewerG activityMapViewerG = (ActivityMapViewerG)ExpandableResultsAdapter.this.context;
        activityMapViewerG.sendRoute((CustomActionButton)param1View, ExpandableResultsAdapter.this.iconSearchResultCar);
        activityMapViewerG.lockRighDrawer(true);
      }
    };
  
  private Activity context;
  
  private ArrayList<HashMap<String, String>> data;
  
  private ExpandableIcon_Interface expandableIcon_interface;
  
  private CustomActionButton iconSearchResultCar;
  
  private ImageView image;
  
  private TextView item_address;
  
  private TextView item_distance;
  
  private TextView item_latitude;
  
  private TextView item_longitude;
  
  private TextView item_name;
  
  private String latitude;
  
  private LinearLayout llContainerInitRoute;
  
  private LinearLayout llContainerSendRoute;
  
  private String longitude;
  
  private TomTomStartNavigation_Interface mTomTomStartNavigation_interface;
  
  private onstarApplication rtApp;
  
  private TextView sendRoute;
  
  private boolean showExpandableIcon;
  
  private TextView startNavigationLabel;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Typeface tf;
  
  private List<String> title;
  
  private int valorCheck;
  
  public ExpandableResultsAdapter(Activity paramActivity, List<String> paramList, ArrayList<HashMap<String, String>> paramArrayList, TomTomStartNavigation_Interface paramTomTomStartNavigation_Interface, boolean paramBoolean, ExpandableIcon_Interface paramExpandableIcon_Interface) {
    this.context = paramActivity;
    this.title = paramList;
    this.data = paramArrayList;
    this.mTomTomStartNavigation_interface = paramTomTomStartNavigation_Interface;
    this.showExpandableIcon = paramBoolean;
    this.expandableIcon_interface = paramExpandableIcon_Interface;
    this.stringsResourcesVO = new StringsResourcesVO();
    this.rtApp = (onstarApplication)paramActivity.getApplicationContext();
  }
  
  public Object getChild(int paramInt1, int paramInt2) {
    return this.data.get(paramInt2);
  }
  
  public long getChildId(int paramInt1, int paramInt2) {
    return paramInt2;
  }
  
  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup) {
    HashMap hashMap = (HashMap)getChild(paramInt1, paramInt2);
    final String name = (String)hashMap.get(KEY_NAME);
    final String address = (String)hashMap.get(KEY_ADDRESS);
    this.latitude = (String)hashMap.get(KEY_LATITUDE);
    this.longitude = (String)hashMap.get(KEY_LONGITUDE);
    String str1 = (String)hashMap.get(KEY_DISTANCE);
    String str2 = (String)hashMap.get(KEY_DISTANCESTRING);
    LayoutInflater layoutInflater = this.context.getLayoutInflater();
    View view = paramView;
    if (paramView == null)
      view = layoutInflater.inflate(2131427443, null); 
    ArrayList<HashMap<String, String>> arrayList = this.data;
    if (arrayList != null && arrayList.size() == 1) {
      this.llContainerSendRoute = (LinearLayout)view.findViewById(2131296846);
      this.llContainerSendRoute.setVisibility(0);
      this.llContainerInitRoute = (LinearLayout)view.findViewById(2131296848);
      this.llContainerInitRoute.setVisibility(0);
      this.cabSendRoute = (CustomActionButton)view.findViewById(2131296447);
      Drawable drawable2 = Utilities.getDrawableFromConfigList((Context)this.context, DrawableResourcesVO.button_start_route, 2131165363);
      new LinkedHashMap<Object, Object>();
      LinkedHashMap linkedHashMap = Utilities.getLastKnownDeviceSelected((Context)this.context, this.rtApp).getNavigationActions();
      for (String str5 : linkedHashMap.keySet()) {
        if (str5.equals(String.valueOf(Enums.Services.SendPNDNavigationCommand.GetCode()))) {
          paramInt1 = ((Integer)((LinkedHashMap)linkedHashMap.get(str5)).get("water")).intValue();
          this.cabSendRoute.setVisibility(0);
          this.cabSendRoute.set_action_service_status(paramInt1);
          this.cabSendRoute.set_drw_action_image(drawable2);
          this.cabSendRoute.setHideLabel(true);
          if (paramInt1 == 1)
            this.cabSendRoute.setOnClickListener(this.clickEnviarRuta); 
          StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
          this.sendRoute = (TextView)view.findViewById(2131297166);
          if (paramInt1 == 0)
            this.sendRoute.setAlpha(CustomActionButton.WATER_MARK_ALPHA); 
          String str6 = Utilities.getStringFromConfigList((Context)this.context, stringsResourcesVO.navigation_lbl_enviarruta, 2131690153);
          this.sendRoute.setText(str6);
          this.sendRoute.setVisibility(0);
        } 
      } 
      this.iconSearchResultCar = (CustomActionButton)view.findViewById(2131297024);
      Drawable drawable1 = Utilities.getDrawableFromConfigList((Context)this.context, DrawableResourcesVO.button_start_navigation, 2131165362);
      this.iconSearchResultCar.set_drw_action_image(drawable1);
      this.iconSearchResultCar.setHideLabel(true);
      this.iconSearchResultCar.setVisibility(0);
      this.iconSearchResultCar.setOnClickListener(new View.OnClickListener() {
            final ExpandableResultsAdapter this$0;
            
            final String val$address;
            
            final String val$name;
            
            public void onClick(View param1View) {
              ExpandableResultsAdapter.this.mTomTomStartNavigation_interface.onStartNavigation(name, address, ExpandableResultsAdapter.this.latitude, ExpandableResultsAdapter.this.longitude);
              ExpandableResultsAdapter.this.intentRoute();
            }
          });
      this.startNavigationLabel = (TextView)view.findViewById(2131297055);
      String str = Utilities.getStringFromConfigList((Context)this.context, this.stringsResourcesVO.navigation_lbl_iniciarnavegacion, 2131690156);
      this.startNavigationLabel.setText(str);
      this.startNavigationLabel.setVisibility(0);
    } 
    this.item_name = (TextView)view.findViewById(2131296921);
    this.item_address = (TextView)view.findViewById(2131296363);
    this.item_latitude = (TextView)view.findViewById(2131296683);
    this.item_longitude = (TextView)view.findViewById(2131296862);
    this.item_distance = (TextView)view.findViewById(2131296501);
    onstarApplication onstarApplication1 = this.rtApp;
    this.tf = onstarApplication.getTypeface((Context)this.context, onstarApplication1.fontPathLouisRegular);
    this.item_name.setTypeface(this.tf);
    this.item_address.setTypeface(this.tf);
    this.item_latitude.setTypeface(this.tf);
    this.item_longitude.setTypeface(this.tf);
    this.item_distance.setTypeface(this.tf);
    this.item_name.setText(str4);
    this.item_address.setText(str3);
    this.item_latitude.setText(this.latitude);
    this.item_longitude.setText(this.longitude);
    str3 = Utilities.getStringFromConfigList((Context)this.context, this.stringsResourcesVO.navigation_lbl_distancia2, 2131690151);
    TextView textView = this.item_distance;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str3);
    stringBuilder.append(" ");
    stringBuilder.append(str1);
    stringBuilder.append(" ");
    stringBuilder.append(str2);
    textView.setText(stringBuilder.toString());
    return view;
  }
  
  public int getChildrenCount(int paramInt) {
    return this.data.size();
  }
  
  public Object getGroup(int paramInt) {
    return this.title.get(paramInt);
  }
  
  public int getGroupCount() {
    return this.title.size();
  }
  
  public long getGroupId(int paramInt) {
    return paramInt;
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup) {
    View view = paramView;
    if (paramView == null)
      view = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2131427418, null); 
    this.image = (ImageView)view.findViewById(2131296524);
    if (paramBoolean) {
      paramInt = 2131165360;
    } else {
      paramInt = 2131165361;
    } 
    this.image.setImageResource(paramInt);
    this.image.setVisibility(0);
    ArrayList<HashMap<String, String>> arrayList = this.data;
    if (arrayList != null && arrayList.size() == 1 && this.showExpandableIcon) {
      this.image.setImageResource(2131165361);
      view.setVisibility(0);
      this.image.setOnClickListener(new View.OnClickListener() {
            final ExpandableResultsAdapter this$0;
            
            public void onClick(View param1View) {
              ActivityMapViewerG activityMapViewerG = (ActivityMapViewerG)ExpandableResultsAdapter.this.context;
              if (!ActivityMapViewerG.onSendRoute) {
                if (ExpandableResultsAdapter.this.data != null && ExpandableResultsAdapter.this.data.size() == 1) {
                  if (activityMapViewerG.getPlacesList().size() > 1)
                    ExpandableResultsAdapter.this.expandableIcon_interface.onExpand(); 
                  if (!activityMapViewerG.getTypeFloating().equalsIgnoreCase("")) {
                    activityMapViewerG.getMap().clear();
                    for (byte b = 0; b < activityMapViewerG.getPlacesList().size(); b++) {
                      String str1 = (String)((HashMap)activityMapViewerG.getPlacesList().get(b)).get(SearchCompleteTask.KEY_LATITUDE);
                      String str2 = (String)((HashMap)activityMapViewerG.getPlacesList().get(b)).get(SearchCompleteTask.KEY_LONGITUDE);
                      activityMapViewerG.getController().setMarkers(Double.parseDouble(str1), Double.parseDouble(str2), activityMapViewerG.getTypeFloating(), b, ExpandableResultsAdapter.this.context);
                    } 
                  } else {
                    activityMapViewerG.getController().setMarkers(Double.parseDouble(ExpandableResultsAdapter.this.latitude), Double.parseDouble(ExpandableResultsAdapter.this.longitude), "", -1, ExpandableResultsAdapter.this.context);
                  } 
                } 
                activityMapViewerG.setListCollapse(false);
              } else {
                activityMapViewerG.setActionButtons();
              } 
            }
          });
    } else {
      arrayList = this.data;
      if (arrayList != null && arrayList.size() == 1 && !this.showExpandableIcon) {
        this.image.setVisibility(8);
        view.setVisibility(8);
      } 
    } 
    return view;
  }
  
  public boolean hasStableIds() {
    return true;
  }
  
  public void intentRoute() {
    for (Map.Entry entry : (new DBFunctions((Context)this.context)).getConfigUser(Utilities.EncryptData(this.rtApp.getUserAccessData()[0])).entrySet()) {
      if (((String)entry.getValue()).equalsIgnoreCase("") || ((String)entry.getValue()).equalsIgnoreCase("0.0")) {
        showDialogEnvio();
        continue;
      } 
      sendMaps();
    } 
  }
  
  public boolean isChildSelectable(int paramInt1, int paramInt2) {
    return true;
  }
  
  public void sendMaps() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("geo:0,0?q=");
    stringBuilder.append(this.latitude);
    stringBuilder.append(",");
    stringBuilder.append(this.longitude);
    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString()));
    String str = Utilities.getStringFromConfigList((Context)this.context, this.stringsResourcesVO.global_lbl_navegacion_1, 2131689929);
    this.context.startActivity(Intent.createChooser(intent, str));
  }
  
  public void setData(ArrayList<HashMap<String, String>> paramArrayList) {
    this.data = paramArrayList;
  }
  
  public void setShowExpandableIcon(boolean paramBoolean) {
    this.showExpandableIcon = paramBoolean;
  }
  
  public void showDialogEnvio() {
    String str3 = Utilities.getStringFromConfigList((Context)this.context, this.stringsResourcesVO.navegacion_mapa_prevencion, 2131690140);
    String str2 = Utilities.getStringFromConfigList((Context)this.context, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str1 = Utilities.getStringFromConfigList((Context)this.context, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    Utilities.getStringFromConfigList((Context)this.context, this.stringsResourcesVO.global_btn_rechazar, 2131689832);
    String str4 = Utilities.getStringFromConfigList((Context)this.context, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    final Dialog dialog = Utilities.simpleDialogCheckBoxBottomButonMap((Context)this.context, null, str2, str3, true, str1, true, str4, 18.0F, 15.0F, true, "Não mostre esta mensagem novamente", true, false, null, null);
    this.btn_left = (Button)dialog.findViewById(2131296421);
    this.btn_left.setOnClickListener(new View.OnClickListener() {
          final ExpandableResultsAdapter this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            (new DBFunctions((Context)ExpandableResultsAdapter.this.context)).updateConfiguration(Utilities.EncryptData(ExpandableResultsAdapter.this.rtApp.getUserAccessData()[0]), ExpandableResultsAdapter.this.valorCheck);
            ExpandableResultsAdapter.this.sendMaps();
            dialog.dismiss();
          }
        });
    this.btn_right = (Button)dialog.findViewById(2131296420);
    this.btn_right.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.check = (CheckBox)dialog.findViewById(2131296468);
    this.check.setVisibility(8);
    this.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final ExpandableResultsAdapter this$0;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (param1Boolean) {
              ExpandableResultsAdapter.access$802(ExpandableResultsAdapter.this, 1);
            } else {
              ExpandableResultsAdapter.access$802(ExpandableResultsAdapter.this, 0);
            } 
          }
        });
    dialog.show();
    dialog.show();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/ExpandableResultsAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */