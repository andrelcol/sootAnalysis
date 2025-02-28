package com.roadtrack.onstar.nav.routing;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import com.roadtrack.onstar.VO.LocationInfoRT;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.googleMaps.ActivityMapViewerG;
import com.roadtrack.onstar.tomtom.activities.ActivityFavoritesHistory;
import com.roadtrack.onstar.utils.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class NavigateCommonDialogActivity extends FragmentActivity {
  private static String ADRESS_EXISTS = "adress_exists";
  
  private static String EDIT_FAV = "editFavorites";
  
  private static String EXISTS_AT_POSITION_INT = "existPosition";
  
  private static String EXISTS_BOOL = "existBool";
  
  private static String EXISTS_WITHNAME = "existswithname";
  
  private static String KEY_ACTION_VIEW = "confirm_dialog";
  
  private static String KEY_ACTION_VIEW_PARAMS_MESSAGE = "confirm_dialog_params_message";
  
  public static String KEY_GET_ADRESS = "adress";
  
  public static String KEY_GET_DEVICEID = "deviceId";
  
  public static String KEY_GET_LATITUDE = "latitude";
  
  public static String KEY_GET_LONGITUDE = "longitude";
  
  public static String KEY_GET_NAME = "name";
  
  public static String KEY_GET_TYPE = "TypeItem";
  
  private static String KEY_SET_ADRESS = "adress";
  
  private static String KEY_SET_DEVICEID = "deviceId";
  
  private static String KEY_SET_LATITUDE = "latitude";
  
  private static String KEY_SET_LONGITUDE = "longitude";
  
  private static String KEY_SET_NAME = "name";
  
  private static String KEY_SET_TYPE = "TypeItem";
  
  private static String NUMBER_OF_OTHERS = "otherscount";
  
  private static String OTHER_FAV = "otherFav";
  
  private static String REPLACE_FAV = "replaceFavorites";
  
  private static String SAVE_FAV = "saveFavorites";
  
  private static String SEARCH_RESULTS = "search_result";
  
  public static String TAG = "NavigateCommonDialogActivity";
  
  public static Handler updateFavoritesListHandler;
  
  private TextView ActionValueMessage;
  
  private TextView ActionValueMessage1;
  
  private TextView ActionValueMessage2;
  
  private TextView ActionValueMessage_;
  
  private TextView ActionValueMessage_1;
  
  private TextView ActionValueTitle;
  
  private TextView ActionValueTitle_;
  
  private TextView ActionValueTitle_1;
  
  private String Iaccent;
  
  private Button btnCancel;
  
  private Button btnCancel1;
  
  Button btnContinue = null;
  
  private Button btnContinue1;
  
  private Button btnContinue2;
  
  private Button btnContinue3;
  
  private Button btnContinue4;
  
  private Button btnOk;
  
  boolean closeView = true;
  
  Context context;
  
  private String favoritesSaveandRemplace;
  
  private String favoritesSaved;
  
  private String favoritos_lbl_existefavorito_2;
  
  private String favoritos_lbl_nombrevalidofavoritos_4;
  
  private Button favs_home;
  
  private Button favs_work;
  
  private String global_popup_btn_aceptar_1;
  
  private String global_popup_btn_no_1;
  
  private String global_popup_lbl_aviso_1;
  
  private String navigatio_favoritos_yaexisteunpunto_1;
  
  private FrameLayout separatorhorizontal;
  
  private FrameLayout separatorhorizontal1;
  
  private FrameLayout separatorhorizontal2;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private EditText titleEdit;
  
  EditText txtNewFavorite = null;
  
  private void addItemLocation(FavoritesHistoryVO paramFavoritesHistoryVO, Enums.Category paramCategory) {
    new Hashtable<Object, Object>();
    DBFunctions dBFunctions = new DBFunctions(getApplicationContext());
    ArrayList arrayList = dBFunctions.getFavoritesHistory(Enums.TypeItem.Favorites.toString(), paramFavoritesHistoryVO.getDevice_id());
    if (arrayList.size() < 10)
      if (!Boolean.valueOf(searchExistsAddress(paramFavoritesHistoryVO).get(EXISTS_BOOL).toString()).booleanValue()) {
        Object[] arrayOfObject = (Object[])searchRowListData(arrayList, paramFavoritesHistoryVO.getName()).get(SEARCH_RESULTS);
        if (arrayList.size() > 0 && ((Boolean)arrayOfObject[0]).booleanValue())
          arrayList.remove(Integer.parseInt(arrayOfObject[1].toString())); 
        dBFunctions.addFavoriteHistory("", paramFavoritesHistoryVO.getName().replace(this.Iaccent.toString(), this.Iaccent.toString()), paramFavoritesHistoryVO.getAddress().replace(this.Iaccent.toString(), this.Iaccent.toString()), paramFavoritesHistoryVO.getDevice_id(), paramFavoritesHistoryVO.getType_item(), paramCategory.toString(), paramFavoritesHistoryVO.getLatlng(), "0", "");
        GlobalMembers.objSendFavoritesMessage = new Object();
        GlobalMembers.objSendFavoritesMessage = paramFavoritesHistoryVO;
        GlobalMembers.googleSearchTitleToFavorite = "";
        ActivityFavoritesHistory.insertData(new DBFunctions(getApplicationContext()), getApplicationContext());
      } else {
        final Dialog dialog = Utilities.simpleDialog((Context)ActivityMapViewerG.getActivity(), null, this.global_popup_lbl_aviso_1, this.favoritos_lbl_existefavorito_2, true, this.global_popup_btn_aceptar_1, false, this.global_popup_btn_no_1, 20.0F, 16.0F);
        this.btnOk = (Button)dialog.findViewById(2131296343);
        this.btnOk.setOnClickListener(new View.OnClickListener(this) {
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                dialog.dismiss();
              }
            });
        ((InputMethodManager)getSystemService("input_method")).toggleSoftInput(1, 0);
        dialog.show();
      }  
  }
  
  private void saveDataInfo(String paramString, FavoritesHistoryVO paramFavoritesHistoryVO) {
    try {
      Enums.Category category = Enums.Category.Other;
      paramString.replace(this.Iaccent.toString(), this.Iaccent.toString());
      paramFavoritesHistoryVO.setName(paramString);
      addItemLocation(paramFavoritesHistoryVO, category);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: saveDataInfo", exception.getMessage());
    } 
  }
  
  private Hashtable<String, Object> searchExistsAddress(FavoritesHistoryVO paramFavoritesHistoryVO) {
    String str1;
    boolean bool;
    Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
    ArrayList arrayList = (new DBFunctions(getApplicationContext())).getFavoritesHistory(Enums.TypeItem.Favorites.toString(), paramFavoritesHistoryVO.getDevice_id());
    boolean bool1 = arrayList.isEmpty();
    String str2 = "";
    if (!bool1) {
      Iterator<FavoritesHistoryVO> iterator = arrayList.iterator();
      for (bool = false;; bool = b) {
        boolean bool2 = iterator.hasNext();
        bool1 = true;
        byte b = bool;
        if (bool2) {
          FavoritesHistoryVO favoritesHistoryVO = iterator.next();
          if (favoritesHistoryVO.getType_item().equals(Enums.TypeItem.Favorites.toString()) && favoritesHistoryVO.getAddress().equals(paramFavoritesHistoryVO.getAddress())) {
            String str;
            if (favoritesHistoryVO.getLatlng().split(",")[0].equals(paramFavoritesHistoryVO.getLatlng().split(",")[0]) && favoritesHistoryVO.getLatlng().split("")[1].equals(paramFavoritesHistoryVO.getLatlng().split(",")[1])) {
              str = favoritesHistoryVO.getName();
              break;
            } 
            Location location2 = new Location("point A");
            location2.setLatitude(Double.valueOf(favoritesHistoryVO.getLatlng().split(",")[0]).doubleValue());
            location2.setLongitude(Double.valueOf(favoritesHistoryVO.getLatlng().split(",")[1]).doubleValue());
            Location location1 = new Location("point B");
            location1.setLatitude(Double.valueOf(str.getLatlng().split(",")[0]).doubleValue());
            location1.setLongitude(Double.valueOf(str.getLatlng().split(",")[1]).doubleValue());
            if (location2.distanceTo(location1) <= GlobalMembers.minDistance) {
              str = favoritesHistoryVO.getName();
              break;
            } 
          } 
          bool++;
          continue;
        } 
        bool1 = false;
        str1 = str2;
      } 
    } else {
      boolean bool2 = false;
      bool1 = false;
      str1 = str2;
      bool = bool2;
    } 
    hashtable.put(EXISTS_BOOL, Boolean.valueOf(bool1));
    hashtable.put(EXISTS_AT_POSITION_INT, Integer.valueOf(bool));
    hashtable.put(NUMBER_OF_OTHERS, Integer.valueOf(0));
    hashtable.put(EXISTS_WITHNAME, str1);
    return (Hashtable)hashtable;
  }
  
  private static Hashtable<String, Object> searchRowListData(Object paramObject, String paramString) {
    Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
    Object[] arrayOfObject = new Object[2];
    paramObject = ((List)paramObject).iterator();
    for (byte b = 0; paramObject.hasNext(); b++) {
      if (((FavoritesHistoryVO)paramObject.next()).getName().equals(paramString)) {
        arrayOfObject[0] = Boolean.valueOf(true);
        arrayOfObject[1] = Integer.valueOf(b);
        break;
      } 
      arrayOfObject[0] = Boolean.valueOf(false);
    } 
    hashtable.put(SEARCH_RESULTS, arrayOfObject);
    return (Hashtable)hashtable;
  }
  
  protected void actionEditFavorite(String paramString1, EditText paramEditText, String paramString2, double paramDouble1, double paramDouble2, Enums.TypeItem paramTypeItem, Enums.Category paramCategory, String paramString3) {
    for (FavoritesHistoryVO favoritesHistoryVO : GlobalMembers.FavoriteHistoricalList) {
      if (favoritesHistoryVO.getName().equals(paramString3)) {
        String[] arrayOfString = favoritesHistoryVO.getLatlng().split(",");
        if (arrayOfString != null && arrayOfString[0].equals(Double.valueOf(paramDouble1)) && arrayOfString[1].equals(Double.valueOf(paramDouble2)) && favoritesHistoryVO.getAddress().equals(paramString2)) {
          favoritesHistoryVO.setName(paramEditText.getText().toString());
          break;
        } 
      } 
    } 
    Message message = new Message();
    message.arg1 = 1;
    if (getCurrentFocus() != null)
      ((InputMethodManager)getApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); 
    updateFavoritesListHandler.sendMessage(message);
    finish();
  }
  
  @TargetApi(11)
  protected void onCreate(Bundle paramBundle) {
    final FavoritesHistoryVO info;
    Enums.TypeItem typeItem;
    final String TypeEvent;
    final FavoritesHistoryVO deviceId;
    ArrayList<FavoritesHistoryVO> arrayList;
    this.context = (Context)this;
    this.stringsResourcesVO = new StringsResourcesVO();
    this.Iaccent = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.Iaccent, 2131689503);
    this.favoritos_lbl_existefavorito_2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.favoritos_lbl_existefavorito_2, 2131689818);
    this.global_popup_btn_aceptar_1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    this.global_popup_btn_no_1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    this.favoritesSaveandRemplace = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.favoritesSaveandRemplace, 2131689810);
    this.favoritesSaved = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.favoritesSaved, 2131689811);
    this.navigatio_favoritos_yaexisteunpunto_1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.navigatio_favoritos_yaexisteunpunto_1, 2131690141);
    this.favoritos_lbl_nombrevalidofavoritos_4 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.favoritos_lbl_nombrevalidofavoritos_4, 2131689821);
    Bundle bundle = getIntent().getBundleExtra("extra");
    new Handler();
    if (bundle == null)
      finish(); 
    String str5 = bundle.getString(KEY_GET_DEVICEID);
    final String name = bundle.getString(KEY_GET_NAME);
    final String adress = bundle.getString(KEY_GET_ADRESS);
    final double latitude = bundle.getDouble(KEY_GET_LATITUDE);
    final double longitude = bundle.getDouble(KEY_GET_LONGITUDE);
    str4.replace(this.Iaccent.toString(), this.Iaccent.toString());
    str6.replace(this.Iaccent.toString(), this.Iaccent.toString());
    try {
      typeItem = (Enums.TypeItem)bundle.getSerializable(KEY_GET_TYPE);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: onCreate", exception.toString());
      typeItem = (Enums.TypeItem)bundle.get("_TypeItem");
    } 
    if (typeItem == Enums.TypeItem.Historical) {
      LocationInfoRT locationInfoRT = new LocationInfoRT();
      locationInfoRT.setDeviceID(str5);
      locationInfoRT.setName(str4);
      locationInfoRT.setAddress(str6);
      locationInfoRT.setLatitude(d2);
      locationInfoRT.setLongitude(d1);
      locationInfoRT.setTypeItem(typeItem);
      finish();
    } 
    String str3 = bundle.getString(KEY_ACTION_VIEW);
    final String nameInfo = str3;
    if (str3 == null)
      str2 = OTHER_FAV; 
    super.onCreate(paramBundle);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(d2);
    stringBuilder.append(",");
    stringBuilder.append(d1);
    final String latlng = stringBuilder.toString();
    final DBFunctions dbFun = new DBFunctions(getApplicationContext());
    if (typeItem != null) {
      favoritesHistoryVO1 = new FavoritesHistoryVO(0, "", str4, str6, "", str5, Enums.TypeItem.Favorites.toString(), Enums.Category.Other.toString(), str7, "", "");
    } else {
      favoritesHistoryVO1 = new FavoritesHistoryVO(0, "", str4, str6, "", str5, Enums.TypeItem.Favorites.toString(), Enums.Category.Other.toString(), str7, "", "");
    } 
    GlobalMembers.objSendFavoritesMessage = new Object();
    GlobalMembers.objSendFavoritesMessage = favoritesHistoryVO1;
    if (str2.equals(SAVE_FAV)) {
      setContentView(2131427432);
      str6 = bundle.getString(KEY_GET_DEVICEID);
      str2 = bundle.getString(KEY_GET_NAME);
      str1 = bundle.getString(KEY_GET_ADRESS);
      d2 = bundle.getDouble(KEY_GET_LATITUDE);
      d1 = bundle.getDouble(KEY_GET_LONGITUDE);
      int i = bundle.getInt("removeItem");
      this.ActionValueMessage_ = (TextView)findViewById(2131296259);
      arrayList = dBFunctions.getFavoritesHistory(Enums.TypeItem.Favorites.toString(), str5);
      favoritesHistoryVO2 = arrayList.get(i);
      str4 = String.format(this.favoritesSaveandRemplace, new Object[] { ((FavoritesHistoryVO)arrayList.get(i)).getName(), str4 });
      this.ActionValueMessage_.setText(str4);
      this.ActionValueTitle_ = (TextView)findViewById(2131296260);
      this.ActionValueTitle_.setVisibility(8);
      this.separatorhorizontal = (FrameLayout)findViewById(2131297029);
      this.separatorhorizontal.setVisibility(8);
      this.btnContinue1 = (Button)findViewById(2131296343);
      str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
      this.btnContinue1.setText(str4);
      this.btnContinue1.setOnClickListener(new View.OnClickListener() {
            final NavigateCommonDialogActivity this$0;
            
            final String val$address;
            
            final DBFunctions val$dbFun;
            
            final String val$deviceIdInfo;
            
            final FavoritesHistoryVO val$info;
            
            final Double val$lat;
            
            final Double val$lon;
            
            final String val$nameInfo;
            
            final FavoritesHistoryVO val$selected;
            
            public void onClick(View param1View) {
              Message message = new Message();
              message.arg1 = 1;
              NavigateCommonDialogActivity.updateFavoritesListHandler.sendMessage(message);
              Toast.makeText(NavigateCommonDialogActivity.this.getApplicationContext(), NavigateCommonDialogActivity.this.favoritesSaved, 0).show();
              GlobalMembers.objSendFavoritesMessage = new Object();
              GlobalMembers.objSendFavoritesMessage = info;
              int i = selected.getId_favs_history();
              String str3 = nameInfo;
              String str1 = address;
              String str5 = deviceIdInfo;
              String str4 = Enums.TypeItem.Favorites.toString();
              String str2 = Enums.Category.Other.toString();
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(lat);
              stringBuilder.append(",");
              stringBuilder.append(lon);
              FavoritesHistoryVO favoritesHistoryVO = new FavoritesHistoryVO(0, "", str3, str1, "", str5, str4, str2, stringBuilder.toString(), "0", "");
              dbFun.updateFavoriteHistory(i, favoritesHistoryVO);
              ((InputMethodManager)NavigateCommonDialogActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(NavigateCommonDialogActivity.this.txtNewFavorite.getWindowToken(), 2);
              NavigateCommonDialogActivity.this.finish();
            }
          });
    } else if (str2.equals(EDIT_FAV)) {
      final String info = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.txtEditNameFavorite, 2131690472);
      setContentView(2131427431);
      this.ActionValueMessage = (TextView)findViewById(2131296257);
      this.ActionValueMessage.setText(str);
      this.titleEdit = (EditText)findViewById(2131297217);
      this.titleEdit.setText(str4);
      GlobalMembers.FavoriteHistoricalList = dBFunctions.getFavoritesHistory(Enums.TypeItem.Favorites.toString(), (String)favoritesHistoryVO2);
      this.titleEdit.setOnKeyListener(new View.OnKeyListener() {
            final NavigateCommonDialogActivity this$0;
            
            final Enums.TypeItem val$TypeEvent;
            
            final String val$adress;
            
            final String val$deviceId;
            
            final double val$latitude;
            
            final double val$longitude;
            
            final String val$name;
            
            public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
              if (param1KeyEvent.getAction() == 0 && param1Int == 66) {
                NavigateCommonDialogActivity navigateCommonDialogActivity = NavigateCommonDialogActivity.this;
                navigateCommonDialogActivity.actionEditFavorite(deviceId, navigateCommonDialogActivity.titleEdit, adress, latitude, longitude, TypeEvent, Enums.Category.Other, name);
                return true;
              } 
              return false;
            }
          });
      this.btnContinue2 = (Button)findViewById(2131296343);
      str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_actualizar_1, 2131689954);
      this.btnContinue2.setText(str);
      this.btnContinue2.setOnClickListener(new View.OnClickListener() {
            final NavigateCommonDialogActivity this$0;
            
            final Enums.TypeItem val$TypeEvent;
            
            final String val$adress;
            
            final String val$deviceId;
            
            final double val$latitude;
            
            final double val$longitude;
            
            final String val$name;
            
            public void onClick(View param1View) {
              NavigateCommonDialogActivity navigateCommonDialogActivity = NavigateCommonDialogActivity.this;
              navigateCommonDialogActivity.actionEditFavorite(deviceId, navigateCommonDialogActivity.titleEdit, adress, latitude, longitude, TypeEvent, Enums.Category.Other, name);
            }
          });
      this.btnCancel = (Button)findViewById(2131296394);
      str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
      this.btnCancel.setText(str);
      this.btnCancel.setOnClickListener(new View.OnClickListener() {
            final NavigateCommonDialogActivity this$0;
            
            final Enums.TypeItem val$TypeEvent;
            
            public void onClick(View param1View) {
              Intent intent = new Intent(NavigateCommonDialogActivity.this.getApplicationContext(), MapsCommonFragment.class);
              if (TypeEvent == Enums.TypeItem.FavoritesSilgleHist) {
                intent.putExtra(NavigateCommonDialogActivity.KEY_SET_TYPE, (Serializable)Enums.TypeItem.FavoritesHistory);
              } else {
                intent.putExtra(NavigateCommonDialogActivity.KEY_SET_TYPE, (Serializable)Enums.TypeItem.NaviFavoritesHist);
              } 
              NavigateCommonDialogActivity.this.startActivity(intent);
              NavigateCommonDialogActivity.this.finish();
            }
          });
    } else if (str2.equals(REPLACE_FAV)) {
      setContentView(2131427432);
      this.ActionValueMessage_1 = (TextView)findViewById(2131296259);
      this.ActionValueMessage_1.setText(arrayList.getString(KEY_ACTION_VIEW_PARAMS_MESSAGE));
      this.ActionValueTitle_1 = (TextView)findViewById(2131296260);
      this.ActionValueTitle_1.setVisibility(8);
      this.separatorhorizontal1 = (FrameLayout)findViewById(2131297029);
      this.separatorhorizontal1.setVisibility(8);
      this.btnContinue4 = (Button)findViewById(2131296343);
      final String info = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
      this.btnContinue4.setText(str);
      this.btnContinue4.setOnClickListener(new View.OnClickListener() {
            final NavigateCommonDialogActivity this$0;
            
            final Enums.TypeItem val$TypeEvent;
            
            final String val$adress;
            
            final String val$deviceId;
            
            final double val$latitude;
            
            final String val$latlng;
            
            final double val$longitude;
            
            final String val$name;
            
            public void onClick(View param1View) {
              Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
              hashtable.put(NavigateCommonDialogActivity.KEY_SET_DEVICEID, deviceId);
              hashtable.put(NavigateCommonDialogActivity.KEY_SET_NAME, name);
              hashtable.put(NavigateCommonDialogActivity.KEY_SET_ADRESS, adress);
              hashtable.put(NavigateCommonDialogActivity.KEY_SET_LATITUDE, Double.valueOf(latitude));
              hashtable.put(NavigateCommonDialogActivity.KEY_SET_LONGITUDE, Double.valueOf(longitude));
              hashtable.put(NavigateCommonDialogActivity.KEY_SET_TYPE, TypeEvent);
              Message message = new Message();
              message.arg1 = 4;
              message.obj = hashtable;
              try {
                NavigateCommonDialogActivity.updateFavoritesListHandler.sendMessage(message);
                FavoritesHistoryVO favoritesHistoryVO = new FavoritesHistoryVO();
                this(0, "", name, adress, "", deviceId, Enums.TypeItem.Favorites.toString(), Enums.Category.Other.toString(), latlng, "", "");
                Object object = new Object();
                this();
                GlobalMembers.objSendFavoritesMessage = object;
                GlobalMembers.objSendFavoritesMessage = favoritesHistoryVO;
              } catch (Exception exception) {
                Utilities.escribeArchivo(NavigateCommonDialogActivity.TAG, "Error: onClick", exception.toString());
              } 
              NavigateCommonDialogActivity.this.finish();
            }
          });
    } else {
      final String info;
      if (str2.equals(ADRESS_EXISTS)) {
        str = this.favoritos_lbl_existefavorito_2;
        Toast.makeText(getApplicationContext(), str, 1).show();
        setContentView(2131427432);
        this.ActionValueMessage1 = (TextView)findViewById(2131296259);
        this.ActionValueMessage1.setText(arrayList.getString(KEY_ACTION_VIEW_PARAMS_MESSAGE));
        this.ActionValueTitle = (TextView)findViewById(2131296260);
        this.ActionValueTitle.setVisibility(8);
        this.separatorhorizontal2 = (FrameLayout)findViewById(2131297029);
        this.separatorhorizontal2.setVisibility(8);
        this.btnContinue3 = (Button)findViewById(2131296343);
        str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
        this.btnContinue3.setText(str);
        this.btnContinue3.setOnClickListener(new View.OnClickListener() {
              final NavigateCommonDialogActivity this$0;
              
              public void onClick(View param1View) {
                GlobalMembers.objSendFavoritesMessage = new Object();
                GlobalMembers.objSendFavoritesMessage = null;
                NavigateCommonDialogActivity.this.finish();
              }
            });
      } else {
        setContentView(2131427433);
        LocationInfoRT locationInfoRT = new LocationInfoRT();
        locationInfoRT.setDeviceID((String)favoritesHistoryVO2);
        locationInfoRT.setLatitude(d2);
        locationInfoRT.setLongitude(d1);
        locationInfoRT.setAddress(str6);
        locationInfoRT.setTypeItem((Enums.TypeItem)str1);
        locationInfoRT.setName(str4);
        this.btnCancel1 = (Button)findViewById(2131296394);
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
        this.btnCancel1.setText(str1);
        this.btnCancel1.setOnClickListener(new View.OnClickListener() {
              final NavigateCommonDialogActivity this$0;
              
              public void onClick(View param1View) {
                NavigateCommonDialogActivity.this.finish();
              }
            });
        this.ActionValueMessage2 = (TextView)findViewById(2131296257);
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.favoritos_lbl_agregarfavoritos_3, 2131689815);
        this.ActionValueMessage2.setText(str1);
        this.txtNewFavorite = (EditText)findViewById(2131296535);
        this.favs_home = (Button)findViewById(2131296534);
        this.favs_work = (Button)findViewById(2131296536);
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.txtHome, 2131690473);
        this.favs_home.setText(str1);
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.txtWork, 2131690475);
        this.favs_work.setText(str1);
        EditText editText = this.txtNewFavorite;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("");
        stringBuilder1.append(GlobalMembers.googleSearchTitleToFavorite.replaceAll("'", ""));
        editText.setText(stringBuilder1.toString());
        int i = this.txtNewFavorite.getText().toString().length();
        this.txtNewFavorite.setSelection(0, i);
        this.txtNewFavorite.requestFocus();
        if (getCurrentFocus() != null)
          ((InputMethodManager)getApplicationContext().getSystemService("input_method")).toggleSoftInput(2, 0); 
        TextView textView = (TextView)findViewById(2131296518);
        this.btnContinue = (Button)findViewById(2131296396);
        String str8 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.navigation_fav_btn_guardar_3, 2131690143);
        this.btnContinue.setText(str8);
        this.btnContinue.setOnClickListener(new View.OnClickListener() {
              final NavigateCommonDialogActivity this$0;
              
              final FavoritesHistoryVO val$info;
              
              public void onClick(View param1View) {
                ((InputMethodManager)NavigateCommonDialogActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(NavigateCommonDialogActivity.this.txtNewFavorite.getWindowToken(), 2);
                if (!NavigateCommonDialogActivity.this.txtNewFavorite.getText().toString().trim().equals("")) {
                  DBFunctions dBFunctions = new DBFunctions((Context)NavigateCommonDialogActivity.this);
                  String str = NavigateCommonDialogActivity.this.navigatio_favoritos_yaexisteunpunto_1.replace("%s", NavigateCommonDialogActivity.this.txtNewFavorite.getText().toString());
                  if (dBFunctions.existFavorite(info.getType_item().toString(), info.getDevice_id(), NavigateCommonDialogActivity.this.txtNewFavorite.getText().toString())) {
                    Toast.makeText((Context)NavigateCommonDialogActivity.this, str, 0).show();
                    return;
                  } 
                  NavigateCommonDialogActivity navigateCommonDialogActivity = NavigateCommonDialogActivity.this;
                  navigateCommonDialogActivity.saveDataInfo(navigateCommonDialogActivity.txtNewFavorite.getText().toString().trim(), info);
                  navigateCommonDialogActivity = NavigateCommonDialogActivity.this;
                  if (navigateCommonDialogActivity.closeView)
                    navigateCommonDialogActivity.finish(); 
                } else {
                  NavigateCommonDialogActivity navigateCommonDialogActivity = NavigateCommonDialogActivity.this;
                  Toast.makeText((Context)navigateCommonDialogActivity, navigateCommonDialogActivity.favoritos_lbl_nombrevalidofavoritos_4, 0).show();
                } 
              }
            });
        this.txtNewFavorite.setOnKeyListener(new View.OnKeyListener() {
              final NavigateCommonDialogActivity this$0;
              
              final FavoritesHistoryVO val$info;
              
              public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
                if (param1KeyEvent.getAction() == 0 && param1Int == 66) {
                  ((InputMethodManager)NavigateCommonDialogActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(NavigateCommonDialogActivity.this.txtNewFavorite.getWindowToken(), 2);
                  if (!NavigateCommonDialogActivity.this.txtNewFavorite.getText().toString().trim().equals("")) {
                    DBFunctions dBFunctions = new DBFunctions((Context)NavigateCommonDialogActivity.this);
                    String str = NavigateCommonDialogActivity.this.navigatio_favoritos_yaexisteunpunto_1.replace("%s", NavigateCommonDialogActivity.this.txtNewFavorite.getText().toString());
                    if (dBFunctions.existFavorite(info.getType_item().toString(), info.getDevice_id(), NavigateCommonDialogActivity.this.txtNewFavorite.getText().toString())) {
                      Toast.makeText((Context)NavigateCommonDialogActivity.this, str, 0).show();
                      return false;
                    } 
                    NavigateCommonDialogActivity navigateCommonDialogActivity = NavigateCommonDialogActivity.this;
                    navigateCommonDialogActivity.saveDataInfo(navigateCommonDialogActivity.txtNewFavorite.getText().toString().trim(), info);
                    navigateCommonDialogActivity = NavigateCommonDialogActivity.this;
                    if (navigateCommonDialogActivity.closeView)
                      navigateCommonDialogActivity.finish(); 
                  } else {
                    NavigateCommonDialogActivity navigateCommonDialogActivity = NavigateCommonDialogActivity.this;
                    Toast.makeText((Context)navigateCommonDialogActivity, navigateCommonDialogActivity.favoritos_lbl_nombrevalidofavoritos_4, 0).show();
                  } 
                  return true;
                } 
                return false;
              }
            });
      } 
    } 
  }
  
  protected void onDestroy() {
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.txtNewFavorite.getWindowToken(), 2);
    super.onDestroy();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/nav/routing/NavigateCommonDialogActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */