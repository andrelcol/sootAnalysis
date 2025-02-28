package com.roadtrack.onstar.tomtom.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.SyncInsertResponse;
import com.roadtrack.onstar.VO.SyncVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.tomtom.utilities.AdapterFavHist;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class ActivityFavoritesHistory extends Activity {
  private static String KEY_GET_TYPE = "TypeItem";
  
  public static MenuItem MenuEdit;
  
  static boolean ShowCheckBox = false;
  
  static String TAG = "FavoritesHistory";
  
  static Activity act;
  
  static boolean isSyncRunning = false;
  
  static boolean isSyncRunningFirstTime = true;
  
  static AdapterFavHist listAdapter;
  
  static HashMap<String, List<FavoritesHistoryVO>> listDataChild;
  
  static ArrayList<String> listDataHeader;
  
  public static ActionMode mActionMode;
  
  static Context mcontext;
  
  public static RotateAnimation rotateAnimation1;
  
  static String type_item = "Favorites";
  
  private TextView ActionValueMessage;
  
  private ImageView Sync;
  
  private Button btnAceptar;
  
  private Button btnCancelar;
  
  private Button btnNok;
  
  private Button btnOk;
  
  private ExpandableListView expListView;
  
  public String favoritos_lbl_existefavorito_2;
  
  private ImageView ivFavorites;
  
  private ImageView ivHeader;
  
  private ImageView ivVehicle;
  
  public ActionMode.Callback modeCallBack = new ActionMode.Callback() {
      final ActivityFavoritesHistory this$0;
      
      public boolean onActionItemClicked(ActionMode param1ActionMode, MenuItem param1MenuItem) {
        final String latitude;
        final int idselected = param1MenuItem.getItemId();
        StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
        if (i != 2131296894) {
          if (i != 2131296896)
            return false; 
          final Dialog dialog = Utilities.DialogEditFav((Context)ActivityFavoritesHistory.act);
          final FavoritesHistoryVO edit = ActivityFavoritesHistory.listAdapter.getEditFavorites();
          favoritesHistoryVO.getName();
          final String deviceId = favoritesHistoryVO.getDevice_id();
          String[] arrayOfString = favoritesHistoryVO.getLatlng().split(",");
          str = arrayOfString[0];
          final String longitude = arrayOfString[1];
          final String adress = favoritesHistoryVO.getAddress();
          i = favoritesHistoryVO.getId_favs_history();
          final String idsync = favoritesHistoryVO.getId_sync();
          ActivityFavoritesHistory.access$302(ActivityFavoritesHistory.this, (EditText)dialog.findViewById(2131297182));
          ActivityFavoritesHistory.access$402(ActivityFavoritesHistory.this, (Button)dialog.findViewById(2131296347));
          ActivityFavoritesHistory.access$502(ActivityFavoritesHistory.this, (Button)dialog.findViewById(2131296344));
          ActivityFavoritesHistory.this.txtEditFav.setText(favoritesHistoryVO.getName());
          ActivityFavoritesHistory.this.txtEditFav.setOnKeyListener(new View.OnKeyListener() {
                final ActivityFavoritesHistory.null this$1;
                
                final String val$adress;
                
                final String val$deviceId;
                
                final Dialog val$dialogEdit;
                
                final FavoritesHistoryVO val$edit;
                
                final int val$idselected;
                
                final String val$idsync;
                
                final String val$latitude;
                
                final String val$longitude;
                
                public boolean onKey(View param2View, int param2Int, KeyEvent param2KeyEvent) {
                  if (param2KeyEvent.getAction() == 0 && param2Int == 66)
                    if (!ActivityFavoritesHistory.searchFavoriteToEdit(edit, ActivityFavoritesHistory.this.txtEditFav)) {
                      ActivityFavoritesHistory activityFavoritesHistory = ActivityFavoritesHistory.this;
                      if (activityFavoritesHistory.editfavorite(activityFavoritesHistory.txtEditFav, latitude, longitude, adress, deviceId, idselected, idsync)) {
                        ActivityFavoritesHistory.mActionMode.finish();
                        dialogEdit.dismiss();
                      } 
                    } else {
                      Toast.makeText(ActivityFavoritesHistory.mcontext, ActivityFavoritesHistory.this.favoritos_lbl_existefavorito_2, 0).show();
                    }  
                  return false;
                }
              });
          ActivityFavoritesHistory.this.btnOk.setOnClickListener(new View.OnClickListener() {
                final ActivityFavoritesHistory.null this$1;
                
                final String val$adress;
                
                final String val$deviceId;
                
                final Dialog val$dialogEdit;
                
                final FavoritesHistoryVO val$edit;
                
                final int val$idselected;
                
                final String val$idsync;
                
                final String val$latitude;
                
                final String val$longitude;
                
                public void onClick(View param2View) {
                  if (!ActivityFavoritesHistory.searchFavoriteToEdit(edit, ActivityFavoritesHistory.this.txtEditFav)) {
                    ActivityFavoritesHistory activityFavoritesHistory = ActivityFavoritesHistory.this;
                    if (activityFavoritesHistory.editfavorite(activityFavoritesHistory.txtEditFav, latitude, longitude, adress, deviceId, idselected, idsync)) {
                      ActivityFavoritesHistory.mActionMode.finish();
                      dialogEdit.dismiss();
                    } 
                  } else {
                    String str = ActivityFavoritesHistory.this.favoritos_lbl_existefavorito_2;
                    Toast.makeText(ActivityFavoritesHistory.mcontext, str, 0).show();
                  } 
                }
              });
          ActivityFavoritesHistory.this.btnNok.setOnClickListener(new View.OnClickListener(this) {
                final Dialog val$dialogEdit;
                
                public void onClick(View param2View) {
                  dialogEdit.dismiss();
                }
              });
          dialog.show();
        } else {
          final String deviceId = Utilities.getStringFromConfigList(ActivityFavoritesHistory.mcontext, ((StringsResourcesVO)str).global_popup_lbl_aviso_1, 2131689955);
          final String longitude = Utilities.getStringFromConfigList(ActivityFavoritesHistory.mcontext, ((StringsResourcesVO)str).favoritos_global_popup_lbl_confirmaeliminarfavoritos_1, 2131689812);
          final String idsync = Utilities.getStringFromConfigList(ActivityFavoritesHistory.mcontext, ((StringsResourcesVO)str).global_popup_btn_aceptar_1, 2131689946);
          final String adress = Utilities.getStringFromConfigList(ActivityFavoritesHistory.mcontext, ((StringsResourcesVO)str).global_popup_btn_no_1, 2131689949);
          final Dialog dialog = Utilities.simpleDialog((Context)ActivityFavoritesHistory.act, null, str1, str3, true, str2, true, str4, 20.0F, 16.0F);
          ActivityFavoritesHistory.access$102(ActivityFavoritesHistory.this, (Button)dialog.findViewById(2131296343));
          ActivityFavoritesHistory.access$202(ActivityFavoritesHistory.this, (Button)dialog.findViewById(2131296344));
          str = Utilities.getStringFromConfigList(ActivityFavoritesHistory.mcontext, ((StringsResourcesVO)str).global_popup_btn_si_1, 2131689952);
          ActivityFavoritesHistory.this.btnAceptar.setText(str);
          ActivityFavoritesHistory.this.btnAceptar.setOnClickListener(new View.OnClickListener() {
                final ActivityFavoritesHistory.null this$1;
                
                final Dialog val$dialog;
                
                public void onClick(View param2View) {
                  new ArrayList();
                  ArrayList<FavoritesHistoryVO> arrayList = ActivityFavoritesHistory.listAdapter.getDelectedFavorites();
                  for (byte b = 0; b < arrayList.size(); b++) {
                    String str3 = ((FavoritesHistoryVO)arrayList.get(b)).getDevice_id();
                    String str2 = ((FavoritesHistoryVO)arrayList.get(b)).getType_item();
                    String str1 = ((FavoritesHistoryVO)arrayList.get(b)).getName();
                    ActivityFavoritesHistory.this.actionDeleteFavorite(str3, str2, str1);
                  } 
                  ActivityFavoritesHistory.this.doSync();
                  dialog.dismiss();
                  ActivityFavoritesHistory.mActionMode.finish();
                }
              });
          ActivityFavoritesHistory.this.btnCancelar.setOnClickListener(new View.OnClickListener(this) {
                final Dialog val$dialog;
                
                public void onClick(View param2View) {
                  dialog.dismiss();
                  ActivityFavoritesHistory.mActionMode.finish();
                }
              });
          dialog.show();
        } 
        return true;
      }
      
      public boolean onCreateActionMode(ActionMode param1ActionMode, Menu param1Menu) {
        param1ActionMode.getMenuInflater().inflate(2131492864, param1Menu);
        ActivityFavoritesHistory.MenuEdit = param1Menu.findItem(2131296896);
        ActivityFavoritesHistory.MenuEdit.setVisible(false);
        OrientationManager.lockOrientation(ActivityFavoritesHistory.act);
        return true;
      }
      
      public void onDestroyActionMode(ActionMode param1ActionMode) {
        if (ActivityFavoritesHistory.ShowCheckBox) {
          ActivityFavoritesHistory.ShowCheckBox = false;
          ActivityFavoritesHistory.mActionMode.finish();
          ActivityFavoritesHistory.prepareListData();
          ActivityFavoritesHistory.this.setlist();
          ActivityFavoritesHistory.this.expListView.expandGroup(0);
          ActivityFavoritesHistory.listAdapter.clearmListDataDelete();
        } 
        OrientationManager.unlockOrientation(ActivityFavoritesHistory.act);
      }
      
      public boolean onPrepareActionMode(ActionMode param1ActionMode, Menu param1Menu) {
        return false;
      }
    };
  
  onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private TextView text_edit;
  
  private TextView tvVehicle;
  
  private EditText txtEditFav;
  
  private void ClickEdtittext() {
    this.text_edit.setOnClickListener(new View.OnClickListener() {
          final ActivityFavoritesHistory this$0;
          
          public void onClick(View param1View) {
            if (ActivityFavoritesHistory.this.ValidateTheftAuto())
              return; 
            ActivityFavoritesHistory.listAdapter.getNumberOfCheckedItemsInGroup(0);
            if (!ActivityFavoritesHistory.ShowCheckBox) {
              ActivityFavoritesHistory.ShowCheckBox = true;
              ActivityFavoritesHistory.mActionMode = ActivityFavoritesHistory.act.startActionMode(ActivityFavoritesHistory.this.modeCallBack);
              ActivityFavoritesHistory.prepareListData();
              ActivityFavoritesHistory.this.setlist();
              ActivityFavoritesHistory.this.expListView.expandGroup(0);
            } else {
              ActivityFavoritesHistory.ShowCheckBox = false;
              ActivityFavoritesHistory.mActionMode.finish();
              ActivityFavoritesHistory.prepareListData();
              ActivityFavoritesHistory.this.setlist();
              ActivityFavoritesHistory.this.expListView.expandGroup(0);
            } 
          }
        });
  }
  
  private void SyncButton() {
    this.Sync.setOnClickListener(new View.OnClickListener() {
          final ActivityFavoritesHistory this$0;
          
          public void onClick(View param1View) {
            if (ActivityFavoritesHistory.this.ValidateTheftAuto())
              return; 
            ActivityFavoritesHistory.this.doSync();
          }
        });
  }
  
  private boolean ValidateTheftAuto() {
    DBFunctions dBFunctions = new DBFunctions((Context)this);
    Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, TAG);
    boolean bool = dBFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), userDevicesVO.getDeviceId(), "", true);
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Mostrando1: ");
    stringBuilder.append(bool);
    Utilities.escribeArchivo(str, "THEFT", stringBuilder.toString());
    if (bool) {
      Utilities.getStringFromConfigList(mcontext, this.stringsResourcesVO.main_lbl_banner_vehiculorobado_1, 2131690066);
      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
      MainActivity.Showbanner = true;
      startActivity(intent);
    } 
    return bool;
  }
  
  private void fillVehicleList() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/roadtrack/onstar/DAO/DBFunctions
    //   5: astore_3
    //   6: aload_3
    //   7: aload_0
    //   8: invokespecial <init> : (Landroid/content/Context;)V
    //   11: aload_0
    //   12: getstatic com/roadtrack/onstar/tomtom/activities/ActivityFavoritesHistory.mcontext : Landroid/content/Context;
    //   15: checkcast com/roadtrack/onstar/onstarApplication
    //   18: putfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   21: new java/util/ArrayList
    //   24: astore_2
    //   25: aload_2
    //   26: invokespecial <init> : ()V
    //   29: iconst_0
    //   30: istore_1
    //   31: iload_1
    //   32: aload_0
    //   33: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   36: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   39: invokeinterface size : ()I
    //   44: if_icmpge -> 79
    //   47: aload_2
    //   48: aload_0
    //   49: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   52: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   55: iload_1
    //   56: invokeinterface get : (I)Ljava/lang/Object;
    //   61: checkcast com/roadtrack/onstar/VO/UserDevicesVO
    //   64: invokevirtual getName : ()Ljava/lang/String;
    //   67: invokeinterface add : (Ljava/lang/Object;)Z
    //   72: pop
    //   73: iinc #1, 1
    //   76: goto -> 31
    //   79: aload_2
    //   80: putstatic com/roadtrack/onstar/BO/GlobalMembers.vehicleList : Ljava/util/List;
    //   83: getstatic com/roadtrack/onstar/tomtom/activities/ActivityFavoritesHistory.mcontext : Landroid/content/Context;
    //   86: aload_3
    //   87: getstatic com/roadtrack/onstar/BO/GlobalMembers.userLogged : Ljava/lang/String;
    //   90: invokevirtual getUserPreference : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   93: invokevirtual getUser : ()Ljava/lang/String;
    //   96: aload_0
    //   97: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   100: invokestatic getLastKnownVehicleSelected : (Landroid/content/Context;Ljava/lang/String;Lcom/roadtrack/onstar/onstarApplication;)I
    //   103: istore_1
    //   104: getstatic com/roadtrack/onstar/tomtom/activities/ActivityFavoritesHistory.mcontext : Landroid/content/Context;
    //   107: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.vehicle_leftspinner : Ljava/lang/String;
    //   110: ldc_w 2131165704
    //   113: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   116: astore_3
    //   117: aload_0
    //   118: getfield ivVehicle : Landroid/widget/ImageView;
    //   121: aload_3
    //   122: invokevirtual setImageDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   125: aload_0
    //   126: getfield tvVehicle : Landroid/widget/TextView;
    //   129: aload_2
    //   130: iload_1
    //   131: invokeinterface get : (I)Ljava/lang/Object;
    //   136: checkcast java/lang/CharSequence
    //   139: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   142: aload_0
    //   143: aload_0
    //   144: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   147: invokespecial setDeviceType : (Lcom/roadtrack/onstar/onstarApplication;)V
    //   150: goto -> 167
    //   153: astore_2
    //   154: getstatic com/roadtrack/onstar/tomtom/activities/ActivityFavoritesHistory.TAG : Ljava/lang/String;
    //   157: ldc_w 'Error: setOnItemSelectedListener'
    //   160: aload_2
    //   161: invokevirtual getMessage : ()Ljava/lang/String;
    //   164: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   167: aload_0
    //   168: monitorexit
    //   169: return
    //   170: astore_2
    //   171: aload_0
    //   172: monitorexit
    //   173: aload_2
    //   174: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	170	finally
    //   21	29	153	java/lang/Exception
    //   21	29	170	finally
    //   31	73	153	java/lang/Exception
    //   31	73	170	finally
    //   79	150	153	java/lang/Exception
    //   79	150	170	finally
    //   154	167	170	finally
  }
  
  private String[] getCommIdAndSerialNumber(String paramString) {
    String[] arrayOfString = new String[3];
    for (byte b = 0; b < GlobalMembers.mDeviceUserList.size(); b++) {
      if (((UserDevicesVO)GlobalMembers.mDeviceUserList.get(b)).getDeviceId().equals(paramString)) {
        arrayOfString[0] = ((UserDevicesVO)GlobalMembers.mDeviceUserList.get(b)).getCommserverid();
        arrayOfString[1] = ((UserDevicesVO)GlobalMembers.mDeviceUserList.get(b)).getSerialnumber();
        arrayOfString[2] = ((UserDevicesVO)GlobalMembers.mDeviceUserList.get(b)).getPhone();
        break;
      } 
    } 
    return arrayOfString;
  }
  
  public static List<FavoritesHistoryVO> getFavorites(Context paramContext) {
    DBFunctions dBFunctions = new DBFunctions(paramContext);
    VehicleCatalogVO vehicleCatalogVO = dBFunctions.getSelectedVehicle(dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
    ArrayList<FavoritesHistoryVO> arrayList = dBFunctions.getFavoritesHistory(Enums.TypeItem.Favorites.toString(), vehicleCatalogVO.getDeviceId());
    sort(arrayList);
    return arrayList;
  }
  
  public static List<FavoritesHistoryVO> getHistory(Context paramContext) {
    ArrayList<FavoritesHistoryVO> arrayList = (new DBFunctions(paramContext)).getNavFavoriteHistory(Enums.TypeItem.Historical.toString());
    sort(arrayList);
    return arrayList;
  }
  
  public static void insertData(DBFunctions paramDBFunctions, Context paramContext) {
    Utilities.escribeArchivo(TAG, "FAVORITES", "asyncTaskSaveFavorite: starting");
    (new asyncTaskSaveFavorite(paramDBFunctions, paramContext)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public static void prepareListData() {
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str1 = Utilities.getStringFromConfigList(mcontext, stringsResourcesVO.global_lbl_fav_1, 2131689923);
    String str2 = Utilities.getStringFromConfigList(mcontext, stringsResourcesVO.global_navigation_fav_lbl_historial_1, 2131689942);
    try {
      ArrayList<String> arrayList = new ArrayList();
      this();
      listDataHeader = arrayList;
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      this();
      listDataChild = (HashMap)hashMap;
      listDataHeader.add(str1);
      listDataHeader.add(str2);
      List<FavoritesHistoryVO> list1 = getFavorites(mcontext);
      List<FavoritesHistoryVO> list2 = getHistory(mcontext);
      listDataChild.put(listDataHeader.get(0), list1);
      listDataChild.put(listDataHeader.get(1), list2);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: prepareListData", exception.getMessage());
    } 
  }
  
  public static boolean searchFavoriteToEdit(FavoritesHistoryVO paramFavoritesHistoryVO, EditText paramEditText) {
    new Hashtable<Object, Object>();
    String str = paramEditText.getText().toString();
    ArrayList arrayList = (new DBFunctions(mcontext)).getFavoritesHistory(Enums.TypeItem.Favorites.toString(), paramFavoritesHistoryVO.getDevice_id());
    boolean bool = arrayList.isEmpty();
    boolean bool2 = false;
    boolean bool1 = false;
    if (!bool) {
      Iterator<FavoritesHistoryVO> iterator = arrayList.iterator();
      while (true) {
        bool2 = bool1;
        if (iterator.hasNext()) {
          FavoritesHistoryVO favoritesHistoryVO = iterator.next();
          if (favoritesHistoryVO.getType_item().equals(Enums.TypeItem.Favorites.toString()) && favoritesHistoryVO.getName().equals(str))
            bool1 = true; 
          continue;
        } 
        break;
      } 
    } 
    return bool2;
  }
  
  private void selectsyncWS() {
    DBFunctions dBFunctions = new DBFunctions(mcontext);
    VehicleCatalogVO vehicleCatalogVO = dBFunctions.getSelectedVehicle(dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
    String[] arrayOfString = getCommIdAndSerialNumber(vehicleCatalogVO.getDeviceId());
    ArrayList<SyncVO> arrayList = (new WsAccess(mcontext)).syncSelectWcf(vehicleCatalogVO.getUser(), vehicleCatalogVO.getDeviceId(), arrayOfString[2]);
    if (arrayList != null && arrayList.size() >= 0) {
      dBFunctions.deleteAllVehicleSyncFavorite(vehicleCatalogVO.getDeviceId());
      dBFunctions.deleteAllVehicleFavorite(vehicleCatalogVO.getDeviceId());
      for (byte b = 0; b < arrayList.size(); b++)
        dBFunctions.addFavoriteSelectWS(new FavoritesHistoryVO(Integer.parseInt(((SyncVO)arrayList.get(b)).getId_favs_history()), "", ((SyncVO)arrayList.get(b)).getName(), ((SyncVO)arrayList.get(b)).getAddress(), ((SyncVO)arrayList.get(b)).getDate(), ((SyncVO)arrayList.get(b)).getDeviceId(), ((SyncVO)arrayList.get(b)).getType_item(), ((SyncVO)arrayList.get(b)).getCategory(), ((SyncVO)arrayList.get(b)).getLatlng(), "", ((SyncVO)arrayList.get(b)).getId_sync())); 
    } 
  }
  
  private void setControls() {
    this.ivVehicle = (ImageView)findViewById(2131296677);
    this.tvVehicle = (TextView)findViewById(2131297225);
    this.Sync = (ImageView)findViewById(2131296612);
    Drawable drawable = Utilities.getDrawableFromConfigList(mcontext, DrawableResourcesVO.ic_sync_white_24dp, 2131165542);
    this.Sync.setImageDrawable(drawable);
    this.expListView = (ExpandableListView)findViewById(2131296489);
    this.expListView.setDividerHeight(2);
    this.expListView.setGroupIndicator(null);
    this.expListView.setClickable(true);
    this.text_edit = (TextView)findViewById(2131297107);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(mcontext, stringsResourcesVO.global_lbl_editar_1, 2131689918);
    this.text_edit.setText(str);
  }
  
  private void setDeviceType(onstarApplication paramonstarApplication) {
    String str = Utilities.getLastKnownDeviceSelected(paramonstarApplication, TAG).getDeviceTypeId();
    if (str != null && !str.equals("")) {
      GlobalMembers.deviceType = str;
    } else {
      GlobalMembers.deviceType = GlobalMembers.deviceTypeP7;
    } 
  }
  
  private void setView() {
    setContentView(2131427370);
    mcontext = getApplicationContext();
    act = this;
  }
  
  private void setspinner() {
    fillVehicleList();
  }
  
  private void showlist() {
    Bundle bundle = getIntent().getExtras();
    if (isSyncRunningFirstTime) {
      Enums.TypeItem typeItem = (Enums.TypeItem)bundle.get(KEY_GET_TYPE);
      isSyncRunningFirstTime = false;
    } else {
      bundle = null;
    } 
    if (bundle != null) {
      int i = null.$SwitchMap$com$roadtrack$onstar$Enums$TypeItem[bundle.ordinal()];
      if (i != 1) {
        if (i != 2) {
          if (i == 3) {
            this.expListView.expandGroup(0);
            doSync();
          } 
        } else {
          this.expListView.expandGroup(1);
        } 
      } else {
        this.expListView.expandGroup(0);
        doSync();
      } 
    } 
  }
  
  public static void showmarkers(FavoritesHistoryVO paramFavoritesHistoryVO, int paramInt) {
    GlobalMembers.objSendFavoritesMessage = new Object();
    GlobalMembers.objSendFavoritesMessage = paramFavoritesHistoryVO;
    Intent intent = new Intent();
    intent.putExtra("Type", paramInt);
    act.setResult(-1, intent);
    act.finish();
  }
  
  public static void sort(List<FavoritesHistoryVO> paramList) {
    if (paramList != null)
      Collections.sort(paramList, new Comparator<FavoritesHistoryVO>() {
            public int compare(FavoritesHistoryVO param1FavoritesHistoryVO1, FavoritesHistoryVO param1FavoritesHistoryVO2) {
              return param1FavoritesHistoryVO1.getName().compareToIgnoreCase(param1FavoritesHistoryVO2.getName());
            }
          }); 
  }
  
  protected void actionDeleteFavorite(String paramString1, String paramString2, String paramString3) {
    if (act.getCurrentFocus() != null)
      ((InputMethodManager)mcontext.getSystemService("input_method")).hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), 0); 
    DBFunctions dBFunctions = new DBFunctions(mcontext);
    ArrayList<FavoritesHistoryVO> arrayList = dBFunctions.getSelectedFavorite(paramString2.toString(), paramString1, paramString3);
    if (arrayList.size() > 0) {
      FavoritesHistoryVO favoritesHistoryVO = arrayList.get(0);
    } else {
      arrayList = null;
    } 
    dBFunctions.updateFavoriteHistoryMarker(Integer.valueOf(arrayList.getId_favs_history()).intValue(), arrayList.getName(), "2");
  }
  
  protected boolean doSync() {
    if (!isSyncRunning) {
      (new SyncDataCloud()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    } else {
      RotateAnimation rotateAnimation = rotateAnimation1;
      if (rotateAnimation != null)
        this.Sync.startAnimation((Animation)rotateAnimation); 
    } 
    return false;
  }
  
  public boolean editfavorite(EditText paramEditText, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5) {
    String str3;
    DBFunctions dBFunctions = new DBFunctions(mcontext);
    dBFunctions.getSelectedVehicle(dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str2 = Utilities.getStringFromConfigList(mcontext, stringsResourcesVO.Iaccent, 2131689503);
    String str1 = paramEditText.getText().toString().replace(str2.toString(), str2.toString());
    str2 = paramString3.replace(str2.toString(), str2.toString());
    if (!str1.equals("") || !str1.isEmpty()) {
      FavoritesHistoryVO favoritesHistoryVO;
      DBFunctions dBFunctions1 = new DBFunctions(act.getApplicationContext());
      GlobalMembers.FavoriteHistoricalList = dBFunctions1.getFavoritesHistory(Enums.TypeItem.Favorites.toString(), paramString4);
      if (paramString5 != null && paramString5.length() > 0) {
        str3 = Enums.TypeItem.Favorites.toString();
        String str = Enums.Category.Other.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString1);
        stringBuilder.append(",");
        stringBuilder.append(paramString2);
        favoritesHistoryVO = new FavoritesHistoryVO(0, "", str1, str2, "", paramString4, str3, str, stringBuilder.toString(), "1", paramString5);
      } else {
        str3 = Enums.TypeItem.Favorites.toString();
        paramString5 = Enums.Category.Other.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramString1);
        stringBuilder.append(",");
        stringBuilder.append(paramString2);
        favoritesHistoryVO = new FavoritesHistoryVO(0, "", (String)favoritesHistoryVO, str2, "", paramString4, str3, paramString5, stringBuilder.toString(), "0", "");
      } 
      dBFunctions1.updateEditFavoriteHistoryMarker(paramInt, favoritesHistoryVO);
      prepareListData();
      setlist();
      listAdapter.notifyDataSetChanged();
      this.expListView.expandGroup(0);
      (new EditDataCloudWS()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
      return true;
    } 
    str1 = Utilities.getStringFromConfigList(mcontext, ((StringsResourcesVO)str3).favoritos_lbl_nombrevalidofavoritos_4, 2131689821);
    Toast.makeText(mcontext, str1, 0).show();
    return false;
  }
  
  public void finish() {
    isSyncRunningFirstTime = true;
    super.finish();
  }
  
  protected void onCreate(Bundle paramBundle) {
    setView();
    setControls();
    setspinner();
    prepareListData();
    setlist();
    showlist();
    ClickEdtittext();
    SyncButton();
    super.onCreate(paramBundle);
    this.stringsResourcesVO = new StringsResourcesVO();
    Utilities.getStringFromConfigList(mcontext, this.stringsResourcesVO.favoritos_lbl_guardofavoritos_3, 2131689820);
    Utilities.getStringFromConfigList(mcontext, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    Utilities.getStringFromConfigList(mcontext, this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
    Utilities.getStringFromConfigList(mcontext, this.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
    Utilities.getStringFromConfigList(mcontext, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    this.favoritos_lbl_existefavorito_2 = Utilities.getStringFromConfigList(mcontext, this.stringsResourcesVO.favoritos_lbl_existefavorito_2, 2131689818);
    String str = Utilities.getStringFromConfigList(mcontext, this.stringsResourcesVO.navigation_latmenu_lbl_favoritosehistorico_1, 2131690145);
    this.ActionValueMessage = (TextView)findViewById(2131296257);
    this.ActionValueMessage.setText(str);
    this.ivFavorites = (ImageView)findViewById(2131296619);
    Drawable drawable = Utilities.getDrawableFromConfigList(mcontext, DrawableResourcesVO.favorite_title2, 2131165412);
    this.ivFavorites.setImageDrawable(drawable);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    getMenuInflater().inflate(2131492873, paramMenu);
    ActionBar actionBar = getActionBar();
    actionBar.setBackgroundDrawable((Drawable)new ColorDrawable(-1));
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    View view = getLayoutInflater().inflate(2131427419, null);
    if (Utilities.isAndinos().booleanValue()) {
      actionBar.setCustomView(getLayoutInflater().inflate(2131427419, null), new ActionBar.LayoutParams(-2, -1, 5));
    } else {
      actionBar.setCustomView(view, new ActionBar.LayoutParams(-2, -1, 17));
    } 
    this.ivHeader = (ImageView)view.findViewById(2131296591);
    this.ivHeader.setImageDrawable(drawable);
    actionBar.setDisplayOptions(18);
    actionBar.setDisplayShowHomeEnabled(false);
    actionBar.setDisplayShowTitleEnabled(false);
    return true;
  }
  
  public void onDestroy() {
    super.onDestroy();
  }
  
  protected void onResume() {
    ValidateTheftAuto();
    super.onResume();
  }
  
  public void setlist() {
    listAdapter = new AdapterFavHist(act, mcontext, listDataHeader, listDataChild, ShowCheckBox);
    this.expListView.setAdapter((ExpandableListAdapter)listAdapter);
  }
  
  public class EditDataCloudWS extends AsyncTask<Void, Void, Void> {
    final ActivityFavoritesHistory this$0;
    
    public EditDataCloudWS() {
      new RotateAnimation(1500.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    }
    
    protected Void doInBackground(Void... param1VarArgs) {
      Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAVORITES", "DoinBack Edit");
      DBFunctions dBFunctions = new DBFunctions((Context)ActivityFavoritesHistory.act);
      UserPreferenceVO userPreferenceVO = dBFunctions.getUserPreference(GlobalMembers.userLogged);
      ArrayList<SyncVO> arrayList = dBFunctions.selectSyncDataEdited(dBFunctions.getSelectedVehicle(userPreferenceVO.getUser()).getDeviceId(), userPreferenceVO.getUser());
      for (byte b = 0; b < arrayList.size(); b++)
        updateSyncData(((SyncVO)arrayList.get(b)).getId_sync(), dBFunctions, arrayList.get(b), ((SyncVO)arrayList.get(b)).getName()); 
      return null;
    }
    
    protected void onPostExecute(Void param1Void) {
      super.onPostExecute(param1Void);
      Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAVORITES", "PostExecute");
      ActivityFavoritesHistory.prepareListData();
      ActivityFavoritesHistory.this.setlist();
      ActivityFavoritesHistory.listAdapter.notifyDataSetChanged();
      ActivityFavoritesHistory.this.expListView.expandGroup(0);
      ActivityFavoritesHistory.this.text_edit.setVisibility(0);
      ActivityFavoritesHistory.isSyncRunning = false;
      OrientationManager.unlockOrientation(ActivityFavoritesHistory.act);
      Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAVORITES", "FIN");
      ActivityFavoritesHistory.this.doSync();
      ActivityFavoritesHistory.mActionMode.finish();
      GlobalMembers.favoritesSelected.clear();
    }
    
    protected void onPreExecute() {
      super.onPreExecute();
      ActivityFavoritesHistory.access$702(ActivityFavoritesHistory.this, (ImageView)ActivityFavoritesHistory.act.findViewById(2131296612));
      ActivityFavoritesHistory.this.expListView.setClickable(false);
      ActivityFavoritesHistory.access$802(ActivityFavoritesHistory.this, (TextView)ActivityFavoritesHistory.act.findViewById(2131297107));
      ActivityFavoritesHistory.listAdapter.notifyDataSetChanged();
      ActivityFavoritesHistory.mActionMode.finish();
      ActivityFavoritesHistory.this.text_edit.setVisibility(8);
      OrientationManager.lockOrientation(ActivityFavoritesHistory.act);
    }
    
    public void updateSyncData(String param1String1, DBFunctions param1DBFunctions, SyncVO param1SyncVO, String param1String2) {
      VehicleCatalogVO vehicleCatalogVO = param1DBFunctions.getSelectedVehicle(param1DBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
      if (vehicleCatalogVO != null) {
        String[] arrayOfString = Utilities.getCommIdAndSerialNumber(vehicleCatalogVO.getDeviceId());
        param1DBFunctions.selectSyncData(param1SyncVO.getDeviceId(), vehicleCatalogVO.getUser());
        (new WsAccess(ActivityFavoritesHistory.mcontext)).updateSyncDataWSWcf(param1String1, param1SyncVO.getUser(), param1String2, param1SyncVO, arrayOfString[0], arrayOfString[1], arrayOfString[2]);
      } 
    }
  }
  
  public class SyncDataCloud extends AsyncTask<Void, Void, Void> {
    DBFunctions dbFun = new DBFunctions(ActivityFavoritesHistory.mcontext);
    
    RotateAnimation rotateAnimation;
    
    final ActivityFavoritesHistory this$0;
    
    private String[] getCommIdAndSerialNumber(String param1String) {
      String[] arrayOfString = new String[3];
      for (byte b = 0; b < GlobalMembers.mDeviceUserList.size(); b++) {
        if (((UserDevicesVO)GlobalMembers.mDeviceUserList.get(b)).getDeviceId().equals(param1String)) {
          arrayOfString[0] = ((UserDevicesVO)GlobalMembers.mDeviceUserList.get(b)).getCommserverid();
          arrayOfString[1] = ((UserDevicesVO)GlobalMembers.mDeviceUserList.get(b)).getSerialnumber();
          arrayOfString[2] = ((UserDevicesVO)GlobalMembers.mDeviceUserList.get(b)).getPhone();
          break;
        } 
      } 
      return arrayOfString;
    }
    
    public void UpdateAndDeletWS() {
      DBFunctions dBFunctions = new DBFunctions(ActivityFavoritesHistory.mcontext);
      VehicleCatalogVO vehicleCatalogVO = dBFunctions.getSelectedVehicle(dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
      String[] arrayOfString = getCommIdAndSerialNumber(vehicleCatalogVO.getDeviceId());
      Cursor cursor = dBFunctions.SyncWSFavorites(ActivityFavoritesHistory.type_item, vehicleCatalogVO.getDeviceId());
      if (cursor.moveToFirst())
        do {
          boolean bool = cursor.getString(9).equals("1");
          byte b = 0;
          if (bool) {
            Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAV", "INSERTANDO ANTES DE WS ACTUALIZAR");
            ArrayList<SyncVO> arrayList = (new WsAccess(ActivityFavoritesHistory.mcontext)).syncSelectWcf(vehicleCatalogVO.getUser(), vehicleCatalogVO.getDeviceId(), arrayOfString[2]);
            if (arrayList != null && arrayList.size() > 0) {
              dBFunctions.deleteAllVehicleFavorite(vehicleCatalogVO.getDeviceId());
              Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAV", "INSERTANDO DESPUES DE ACTUALIZARDE WS");
              while (b < arrayList.size()) {
                dBFunctions.addFavoriteSelectWS(new FavoritesHistoryVO(Integer.parseInt(((SyncVO)arrayList.get(b)).getId_favs_history()), "", ((SyncVO)arrayList.get(b)).getName(), ((SyncVO)arrayList.get(b)).getAddress(), ((SyncVO)arrayList.get(b)).getDate(), ((SyncVO)arrayList.get(b)).getDeviceId(), ((SyncVO)arrayList.get(b)).getType_item(), ((SyncVO)arrayList.get(b)).getCategory(), ((SyncVO)arrayList.get(b)).getLatlng(), "", ((SyncVO)arrayList.get(b)).getId_sync()));
                b++;
              } 
            } 
          } else if (cursor.getString(9).equals("2")) {
            dBFunctions.selectSyncData(vehicleCatalogVO.getDeviceId(), vehicleCatalogVO.getUser());
            Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAV", "INSERTANDO ANTES DE WS ELIMINAR");
            if ((new WsAccess(ActivityFavoritesHistory.mcontext)).deleteSyncDataWSWcf(cursor.getString(10), vehicleCatalogVO.getUser(), vehicleCatalogVO.getDeviceId(), arrayOfString[0], arrayOfString[1])) {
              Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAV", "INSERTANDO DESPUES DE WS ELIMINAR");
              dBFunctions.deleteFavorite(cursor.getString(10));
            } 
          } else if (cursor.getString(9).equals("0") || cursor.getString(9).equals("")) {
            new ArrayList();
            ArrayList arrayList = dBFunctions.selectSyncDataSingle(vehicleCatalogVO.getDeviceId(), vehicleCatalogVO.getUser(), cursor.getColumnName(0));
            Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAV", "INSERTANDO ANTES DE WS SUBIR");
            if (arrayList != null && arrayList.size() > 0 && (new WsAccess(ActivityFavoritesHistory.mcontext)).syncInsertWcf(arrayList, arrayOfString[0], arrayOfString[1], arrayOfString[2]) != null)
              dBFunctions.deleteAllVehicleSyncFavorite(vehicleCatalogVO.getDeviceId()); 
          } 
        } while (cursor.moveToNext()); 
    }
    
    protected Void doInBackground(Void... param1VarArgs) {
      ActivityFavoritesHistory.insertData(this.dbFun, ActivityFavoritesHistory.mcontext);
      Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAV", "DOINGBACKGROUND..1");
      UpdateAndDeletWS();
      Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAV", "DOINGBACKGROUND..2");
      ActivityFavoritesHistory.this.selectsyncWS();
      return null;
    }
    
    protected void onPostExecute(Void param1Void) {
      super.onPostExecute(param1Void);
      Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAV", "POSTESECUTE");
      ActivityFavoritesHistory.prepareListData();
      ActivityFavoritesHistory.this.setlist();
      ActivityFavoritesHistory.this.expListView.expandGroup(0);
      ActivityFavoritesHistory.this.expListView.setClickable(true);
      ActivityFavoritesHistory.this.text_edit.setVisibility(0);
      ActivityFavoritesHistory.this.expListView.deferNotifyDataSetChanged();
      rotateSyncImage(false);
      OrientationManager.unlockOrientation(ActivityFavoritesHistory.act);
    }
    
    protected void onPreExecute() {
      super.onPreExecute();
      ActivityFavoritesHistory.access$702(ActivityFavoritesHistory.this, (ImageView)ActivityFavoritesHistory.act.findViewById(2131296612));
      Drawable drawable = Utilities.getDrawableFromConfigList(ActivityFavoritesHistory.mcontext, DrawableResourcesVO.ic_sync_white_24dp, 2131165542);
      ActivityFavoritesHistory.this.Sync.setImageDrawable(drawable);
      this.rotateAnimation = new RotateAnimation(1500.0F, 0.0F, 1, 0.5F, 1, 0.5F);
      Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAV", "ONPREEXECUTE");
      ActivityFavoritesHistory.this.expListView.setClickable(false);
      ActivityFavoritesHistory.listAdapter.notifyDataSetChanged();
      ActionMode actionMode = ActivityFavoritesHistory.mActionMode;
      if (actionMode != null)
        actionMode.finish(); 
      ActivityFavoritesHistory.access$802(ActivityFavoritesHistory.this, (TextView)ActivityFavoritesHistory.act.findViewById(2131297107));
      ActivityFavoritesHistory.this.text_edit.setVisibility(8);
      rotateSyncImage(true);
      OrientationManager.lockOrientation(ActivityFavoritesHistory.act);
    }
    
    public void rotateSyncImage(boolean param1Boolean) {
      if (param1Boolean) {
        this.rotateAnimation = new RotateAnimation(1500.0F, 0.0F, 1, 0.5F, 1, 0.5F);
        this.rotateAnimation.setInterpolator((Interpolator)new LinearInterpolator());
        this.rotateAnimation.setDuration(4000L);
        this.rotateAnimation.setRepeatCount(-1);
        this.rotateAnimation.setRepeatMode(1);
        Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAVORITES", "STARTING ANIMATION 2");
        ActivityFavoritesHistory.this.Sync.startAnimation((Animation)this.rotateAnimation);
      } else if (ActivityFavoritesHistory.this.Sync != null && ActivityFavoritesHistory.this.Sync.getAnimation() != null) {
        Utilities.escribeArchivo(ActivityFavoritesHistory.TAG, "FAVORITES", "STOPING ANIMATION 2");
        ActivityFavoritesHistory.this.Sync.clearAnimation();
      } 
    }
  }
  
  public static class asyncTaskSaveFavorite extends AsyncTask<Void, Void, ArrayList<SyncInsertResponse>> {
    Context context;
    
    DBFunctions dbFunctions;
    
    UserPreferenceVO userPreferenceVo;
    
    VehicleCatalogVO vehicleCatalogVo;
    
    private asyncTaskSaveFavorite(DBFunctions param1DBFunctions, Context param1Context) {
      this.context = param1Context;
      this.dbFunctions = param1DBFunctions;
    }
    
    protected ArrayList<SyncInsertResponse> doInBackground(Void... param1VarArgs) {
      null = this.vehicleCatalogVo;
      if (null != null) {
        ArrayList arrayList1 = this.dbFunctions.selectSyncData(null.getDeviceId(), this.vehicleCatalogVo.getUser());
        ArrayList<SyncVO> arrayList = new ArrayList();
        for (SyncVO syncVO : arrayList1) {
          if (syncVO.getType_poi() != null && syncVO.getType_poi().equals("0"))
            arrayList.add(syncVO); 
        } 
        if (!arrayList.isEmpty()) {
          String[] arrayOfString = Utilities.getCommIdAndSerialNumber(this.vehicleCatalogVo.getDeviceId());
          return (new WsAccess(this.context)).syncInsertWcf(arrayList, arrayOfString[0], arrayOfString[1], arrayOfString[2]);
        } 
      } 
      return null;
    }
    
    protected void onPostExecute(ArrayList<SyncInsertResponse> param1ArrayList) {
      super.onPostExecute(param1ArrayList);
      if (param1ArrayList != null)
        for (SyncInsertResponse syncInsertResponse : param1ArrayList) {
          String str;
          DBFunctions dBFunctions = this.dbFunctions;
          int j = syncInsertResponse.getId_favs();
          int i = Integer.valueOf(syncInsertResponse.getId_sync()).intValue();
          if (syncInsertResponse.getStatus() == 0) {
            str = "";
          } else {
            str = "0";
          } 
          dBFunctions.updateSynchronizedFavoriteHistory(j, i, str, syncInsertResponse.getDate());
        }  
    }
    
    protected void onPreExecute() {
      super.onPreExecute();
      this.userPreferenceVo = this.dbFunctions.getUserPreference(GlobalMembers.userLogged);
      this.vehicleCatalogVo = this.dbFunctions.getSelectedVehicle(this.userPreferenceVo.getUser());
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/tomtom/activities/ActivityFavoritesHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */