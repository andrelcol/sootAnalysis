package com.roadtrack.onstar.nav.routing;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
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
import com.roadtrack.onstar.adapter.ConfirmEraseDialog;
import com.roadtrack.onstar.adapter.listExpandibleAdapter;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

public class FavoritesHistory extends Fragment {
  private static String EDIT_FAV;
  
  private static String KEY_ACTION_VIEW;
  
  private static String KEY_GET_TYPE = "TypeItem";
  
  private static String KEY_GET_TYPE_SINGLE_FAVORITES = "SingleFavorites";
  
  private static String KEY_ID_FAVS;
  
  private static String KEY_ID_SYNC;
  
  private static String KEY_SET_ADRESS;
  
  private static String KEY_SET_DEVICEID;
  
  private static String KEY_SET_LATITUDE;
  
  private static String KEY_SET_LONGITUDE;
  
  private static String KEY_SET_NAME;
  
  private static String KEY_SET_TYPE;
  
  private static String SAVE_FAV;
  
  private static final String TAG = FavoritesHistory.class.getSimpleName();
  
  private static Context context;
  
  private static ImageView imageResponseSync;
  
  private static boolean isPrimeraVez = true;
  
  public static boolean isSyncRunning;
  
  private static FavoritesHistoryListener listener;
  
  private String ISTODAY_VALUE = "isTodayValue";
  
  private TextView actionValueMessage;
  
  private Button btnContinueEdit;
  
  private Button btnDeleteItem;
  
  private Button btnOk;
  
  private DBFunctions dbFun;
  
  LinearLayout edit_favoritespopup = null;
  
  private LinearLayout edit_favoritespopup1;
  
  private TextView editroutechooseloc;
  
  private TextView editroutelocation;
  
  private ExpandableListView expListView;
  
  @SuppressLint({"HandlerLeak"})
  public Handler handlerHistory = new Handler() {
      final FavoritesHistory this$0;
      
      public void handleMessage(Message param1Message) {
        // Byte code:
        //   0: aload_0
        //   1: monitorenter
        //   2: aload_1
        //   3: getfield arg1 : I
        //   6: istore_3
        //   7: aload_1
        //   8: getfield arg2 : I
        //   11: istore_2
        //   12: iload_3
        //   13: tableswitch default -> 52, 1 -> 361, 2 -> 279, 3 -> 264, 4 -> 250, 5 -> 161, 6 -> 55
        //   52: goto -> 368
        //   55: aload_0
        //   56: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   59: invokestatic access$1500 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)V
        //   62: aload_0
        //   63: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   66: astore #4
        //   68: new com/roadtrack/onstar/adapter/listExpandibleAdapter
        //   71: astore_1
        //   72: aload_1
        //   73: invokestatic access$1000 : ()Landroid/content/Context;
        //   76: aload_0
        //   77: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   80: invokestatic access$000 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Ljava/util/List;
        //   83: aload_0
        //   84: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   87: invokestatic access$100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Ljava/util/HashMap;
        //   90: getstatic com/roadtrack/onstar/Enums$TypeItem.NaviFavoritesHist : Lcom/roadtrack/onstar/Enums$TypeItem;
        //   93: iconst_1
        //   94: invokespecial <init> : (Landroid/content/Context;Ljava/util/List;Ljava/util/HashMap;Lcom/roadtrack/onstar/Enums$TypeItem;Z)V
        //   97: aload #4
        //   99: aload_1
        //   100: invokestatic access$902 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;Lcom/roadtrack/onstar/adapter/listExpandibleAdapter;)Lcom/roadtrack/onstar/adapter/listExpandibleAdapter;
        //   103: pop
        //   104: aload_0
        //   105: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   108: invokestatic access$1100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Landroid/widget/ExpandableListView;
        //   111: aload_0
        //   112: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   115: invokestatic access$900 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Lcom/roadtrack/onstar/adapter/listExpandibleAdapter;
        //   118: invokevirtual setAdapter : (Landroid/widget/ExpandableListAdapter;)V
        //   121: aload_0
        //   122: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   125: invokestatic access$1600 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Z
        //   128: ifeq -> 146
        //   131: aload_0
        //   132: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   135: invokestatic access$1100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Landroid/widget/ExpandableListView;
        //   138: iconst_0
        //   139: invokevirtual expandGroup : (I)Z
        //   142: pop
        //   143: goto -> 368
        //   146: aload_0
        //   147: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   150: invokestatic access$1100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Landroid/widget/ExpandableListView;
        //   153: iconst_1
        //   154: invokevirtual expandGroup : (I)Z
        //   157: pop
        //   158: goto -> 368
        //   161: aload_0
        //   162: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   165: aload_1
        //   166: getfield obj : Ljava/lang/Object;
        //   169: checkcast java/util/ArrayList
        //   172: invokestatic access$1400 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;Ljava/util/ArrayList;)V
        //   175: aload_0
        //   176: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   179: astore_1
        //   180: new com/roadtrack/onstar/adapter/listExpandibleAdapter
        //   183: astore #4
        //   185: aload #4
        //   187: invokestatic access$1000 : ()Landroid/content/Context;
        //   190: aload_0
        //   191: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   194: invokestatic access$000 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Ljava/util/List;
        //   197: aload_0
        //   198: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   201: invokestatic access$100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Ljava/util/HashMap;
        //   204: getstatic com/roadtrack/onstar/Enums$TypeItem.NaviFavoritesHist : Lcom/roadtrack/onstar/Enums$TypeItem;
        //   207: iconst_1
        //   208: invokespecial <init> : (Landroid/content/Context;Ljava/util/List;Ljava/util/HashMap;Lcom/roadtrack/onstar/Enums$TypeItem;Z)V
        //   211: aload_1
        //   212: aload #4
        //   214: invokestatic access$902 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;Lcom/roadtrack/onstar/adapter/listExpandibleAdapter;)Lcom/roadtrack/onstar/adapter/listExpandibleAdapter;
        //   217: pop
        //   218: aload_0
        //   219: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   222: invokestatic access$1100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Landroid/widget/ExpandableListView;
        //   225: aload_0
        //   226: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   229: invokestatic access$900 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Lcom/roadtrack/onstar/adapter/listExpandibleAdapter;
        //   232: invokevirtual setAdapter : (Landroid/widget/ExpandableListAdapter;)V
        //   235: aload_0
        //   236: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   239: invokestatic access$1100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Landroid/widget/ExpandableListView;
        //   242: iconst_0
        //   243: invokevirtual expandGroup : (I)Z
        //   246: pop
        //   247: goto -> 368
        //   250: aload_0
        //   251: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   254: aload_1
        //   255: getfield obj : Ljava/lang/Object;
        //   258: invokestatic access$1300 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;Ljava/lang/Object;)V
        //   261: goto -> 368
        //   264: aload_0
        //   265: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   268: aload_1
        //   269: getfield obj : Ljava/lang/Object;
        //   272: iload_2
        //   273: invokestatic access$1200 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;Ljava/lang/Object;I)V
        //   276: goto -> 368
        //   279: aload_0
        //   280: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   283: invokestatic access$800 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)V
        //   286: aload_0
        //   287: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   290: astore_1
        //   291: new com/roadtrack/onstar/adapter/listExpandibleAdapter
        //   294: astore #4
        //   296: aload #4
        //   298: invokestatic access$1000 : ()Landroid/content/Context;
        //   301: aload_0
        //   302: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   305: invokestatic access$000 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Ljava/util/List;
        //   308: aload_0
        //   309: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   312: invokestatic access$100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Ljava/util/HashMap;
        //   315: getstatic com/roadtrack/onstar/Enums$TypeItem.NaviFavoritesHist : Lcom/roadtrack/onstar/Enums$TypeItem;
        //   318: iconst_1
        //   319: invokespecial <init> : (Landroid/content/Context;Ljava/util/List;Ljava/util/HashMap;Lcom/roadtrack/onstar/Enums$TypeItem;Z)V
        //   322: aload_1
        //   323: aload #4
        //   325: invokestatic access$902 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;Lcom/roadtrack/onstar/adapter/listExpandibleAdapter;)Lcom/roadtrack/onstar/adapter/listExpandibleAdapter;
        //   328: pop
        //   329: aload_0
        //   330: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   333: invokestatic access$1100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Landroid/widget/ExpandableListView;
        //   336: aload_0
        //   337: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   340: invokestatic access$900 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Lcom/roadtrack/onstar/adapter/listExpandibleAdapter;
        //   343: invokevirtual setAdapter : (Landroid/widget/ExpandableListAdapter;)V
        //   346: aload_0
        //   347: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   350: invokestatic access$1100 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)Landroid/widget/ExpandableListView;
        //   353: iconst_0
        //   354: invokevirtual expandGroup : (I)Z
        //   357: pop
        //   358: goto -> 368
        //   361: aload_0
        //   362: getfield this$0 : Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
        //   365: invokestatic access$700 : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;)V
        //   368: aload_0
        //   369: monitorexit
        //   370: return
        //   371: astore_1
        //   372: aload_0
        //   373: monitorexit
        //   374: aload_1
        //   375: athrow
        // Exception table:
        //   from	to	target	type
        //   2	12	371	finally
        //   55	143	371	finally
        //   146	158	371	finally
        //   161	247	371	finally
        //   250	261	371	finally
        //   264	276	371	finally
        //   279	358	371	finally
        //   361	368	371	finally
      }
    };
  
  private int id;
  
  private boolean isFavorites;
  
  private boolean isToday = true;
  
  private LinearLayout layoutSpinnerSection;
  
  private listExpandibleAdapter listAdapter;
  
  private HashMap<String, List<FavoritesHistoryVO>> listDataChild;
  
  private List<String> listDataHeader;
  
  protected ActionMode mActionMode;
  
  private BroadcastReceiver networkChangeReceiver;
  
  private NotificationManager notificationManager;
  
  private ProgressBar progressBar;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Button sync_button;
  
  private AsynchCheckDataSync synchro;
  
  private Timer t;
  
  EditText titleEdit_ = null;
  
  private TextView tvMessage;
  
  private TextView tvTitle;
  
  private Spinner vehiclelist_spinner;
  
  private View vista;
  
  static {
    KEY_SET_DEVICEID = "deviceId";
    KEY_SET_NAME = "name";
    KEY_SET_ADRESS = "adress";
    KEY_SET_LATITUDE = "latitude";
    KEY_SET_LONGITUDE = "longitude";
    KEY_SET_TYPE = "TypeItem";
    KEY_ACTION_VIEW = "confirm_dialog";
    EDIT_FAV = "editFavorites";
    SAVE_FAV = "saveFavorites";
    KEY_ID_FAVS = "_IdFavs";
    KEY_ID_SYNC = "_IdSync";
  }
  
  public FavoritesHistory() {
    new ArrayList();
    new ArrayList();
    new String();
    new String();
    this.id = 123456789;
    this.networkChangeReceiver = new BroadcastReceiver() {
        final FavoritesHistory this$0;
        
        public void onReceive(Context param1Context, Intent param1Intent) {
          Utilities.showNetworkServiceData((TextView)FavoritesHistory.this.getActivity().findViewById(2131296532), (Context)FavoritesHistory.this.getActivity(), new TextView[0]);
        }
      };
  }
  
  private boolean doSync() {
    if (!NetUtilities.validateNetwork(getActivity().getApplicationContext())) {
      ImageView imageView1 = imageResponseSync;
      if (imageView1 != null)
        imageView1.setVisibility(0); 
      return false;
    } 
    ImageView imageView = imageResponseSync;
    if (imageView != null && imageView.getVisibility() == 0)
      imageResponseSync.setVisibility(8); 
    if (!isSyncRunning) {
      isSyncRunning = true;
      String str = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("AsynchCheckDataSync: starting: ");
      stringBuilder.append(this.progressBar.getVisibility());
      Utilities.escribeArchivo(str, "FAVORITES", stringBuilder.toString());
      if (this.synchro != null)
        this.synchro = null; 
      this.synchro = new AsynchCheckDataSync();
      this.synchro.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    } 
    return false;
  }
  
  private void editPopupControls(Object paramObject, int paramInt) {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/nav/routing/FavoritesHistory}} */
    try {
      Hashtable hashtable = (Hashtable)paramObject;
      hashtable.get(KEY_ACTION_VIEW);
      paramObject = hashtable.get(KEY_SET_DEVICEID).toString();
      String str1 = hashtable.get(KEY_SET_NAME).toString();
      String str3 = hashtable.get(KEY_SET_ADRESS).toString();
      String str6 = hashtable.get(KEY_SET_LATITUDE).toString();
      String str4 = hashtable.get(KEY_ID_FAVS).toString();
      String str5 = hashtable.get(KEY_ID_SYNC).toString();
      String str2 = hashtable.get(KEY_SET_LONGITUDE).toString();
      Enums.TypeItem typeItem = Enums.TypeItem.valueOf((String)hashtable.get(KEY_SET_TYPE));
      this.edit_favoritespopup = (LinearLayout)this.vista.findViewById(2131296507);
      this.edit_favoritespopup.setVisibility(0);
      this.titleEdit_ = (EditText)this.vista.findViewById(2131297217);
      this.titleEdit_.setText(str1);
      this.titleEdit_.requestFocus();
      ((InputMethodManager)context.getSystemService("input_method")).showSoftInput((View)this.titleEdit_, 2);
      LinearLayout linearLayout = this.edit_favoritespopup;
      View.OnKeyListener onKeyListener = new View.OnKeyListener() {
          public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
            return false;
          }
        };
      super(this);
      linearLayout.setOnKeyListener(onKeyListener);
      this.btnContinueEdit = (Button)this.vista.findViewById(2131296343);
      Button button2 = this.btnContinueEdit;
      View.OnClickListener onClickListener2 = new View.OnClickListener() {
          final FavoritesHistory this$0;
          
          final Enums.TypeItem val$TypeEvent;
          
          final String val$adress;
          
          final String val$deviceId;
          
          final String val$id_favs;
          
          final String val$id_sync;
          
          final String val$latitude;
          
          final String val$longitude;
          
          final String val$name;
          
          public void onClick(View param1View) {
            Integer integer1;
            String str4 = deviceId;
            EditText editText = FavoritesHistory.this.titleEdit_;
            String str3 = adress;
            double d1 = Double.parseDouble(latitude.split(",")[0]);
            double d2 = Double.parseDouble(longitude.split(",")[0]);
            Enums.TypeItem typeItem = TypeEvent;
            Enums.Category category = Enums.Category.Other;
            String str2 = name;
            Integer integer2 = Integer.valueOf(id_favs);
            if (editText.getText().toString().trim().isEmpty()) {
              String str = Utilities.getStringFromConfigList((Context)FavoritesHistory.this.getActivity(), FavoritesHistory.this.stringsResourcesVO.favoritos_lbl_nombrevalidofavoritos_4, 2131689821);
              Toast.makeText(FavoritesHistory.context, str, 0).show();
              return;
            } 
            String str1 = id_sync;
            if (str1 == null || str1.isEmpty()) {
              integer1 = Integer.valueOf(0);
            } else {
              integer1 = Integer.valueOf(id_sync);
            } 
            FavoritesHistory.this.actionEditFavorite(str4, editText, str3, Double.valueOf(d1).doubleValue(), Double.valueOf(d2).doubleValue(), typeItem, category, str2, integer2.intValue(), integer1.intValue());
          }
        };
      super(this, (String)paramObject, str3, str6, str2, typeItem, str1, str4, str5);
      button2.setOnClickListener(onClickListener2);
      this.btnDeleteItem = (Button)this.vista.findViewById(2131296345);
      Button button1 = this.btnDeleteItem;
      View.OnClickListener onClickListener1 = new View.OnClickListener() {
          final FavoritesHistory this$0;
          
          final Enums.TypeItem val$TypeEvent;
          
          final String val$deviceId;
          
          final String val$name;
          
          public void onClick(View param1View) {
            String str2 = deviceId;
            Enums.TypeItem typeItem = TypeEvent;
            String str1 = name;
            FavoritesHistory.this.edit_favoritespopup.setVisibility(8);
            FavoritesHistory.this.actionDeleteFavorite(str2, typeItem, str1);
          }
        };
      super(this, (String)paramObject, typeItem, str1);
      button1.setOnClickListener(onClickListener1);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: setOnClickListener", exception.getMessage());
    } finally {}
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/nav/routing/FavoritesHistory}} */
  }
  
  private void fillVehicleList(Spinner paramSpinner, Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual getActivity : ()Landroidx/fragment/app/FragmentActivity;
    //   6: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   9: checkcast com/roadtrack/onstar/onstarApplication
    //   12: astore #5
    //   14: aload_1
    //   15: astore #4
    //   17: aload_1
    //   18: ifnonnull -> 34
    //   21: aload_0
    //   22: getfield vehiclelist_spinner : Landroid/widget/Spinner;
    //   25: astore #4
    //   27: goto -> 34
    //   30: astore_1
    //   31: goto -> 188
    //   34: aload #4
    //   36: ifnonnull -> 42
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: new java/util/ArrayList
    //   45: astore #6
    //   47: aload #6
    //   49: invokespecial <init> : ()V
    //   52: iconst_0
    //   53: istore_3
    //   54: iload_3
    //   55: aload #5
    //   57: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   60: invokeinterface size : ()I
    //   65: if_icmpge -> 99
    //   68: aload #6
    //   70: aload #5
    //   72: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   75: iload_3
    //   76: invokeinterface get : (I)Ljava/lang/Object;
    //   81: checkcast com/roadtrack/onstar/VO/UserDevicesVO
    //   84: invokevirtual getName : ()Ljava/lang/String;
    //   87: invokeinterface add : (Ljava/lang/Object;)Z
    //   92: pop
    //   93: iinc #3, 1
    //   96: goto -> 54
    //   99: aload #6
    //   101: putstatic com/roadtrack/onstar/BO/GlobalMembers.vehicleList : Ljava/util/List;
    //   104: new com/roadtrack/onstar/adapter/VehicleSpinnerAdapterWhite
    //   107: astore_1
    //   108: aload_1
    //   109: aload_2
    //   110: ldc_w 2131427513
    //   113: ldc_w 2131297225
    //   116: aload #4
    //   118: aload #6
    //   120: invokespecial <init> : (Landroid/content/Context;IILandroid/widget/Spinner;Ljava/util/List;)V
    //   123: aload #4
    //   125: aload_1
    //   126: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   129: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   132: aload_0
    //   133: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   136: getstatic com/roadtrack/onstar/BO/GlobalMembers.userLogged : Ljava/lang/String;
    //   139: invokevirtual getUserPreference : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   142: invokevirtual getUser : ()Ljava/lang/String;
    //   145: aload #5
    //   147: invokestatic getLastKnownVehicleSelected : (Landroid/content/Context;Ljava/lang/String;Lcom/roadtrack/onstar/onstarApplication;)I
    //   150: istore_3
    //   151: aload #4
    //   153: iload_3
    //   154: invokevirtual setSelection : (I)V
    //   157: aload #4
    //   159: iload_3
    //   160: invokevirtual setSelection : (I)V
    //   163: aload #5
    //   165: invokestatic setDeviceType : (Lcom/roadtrack/onstar/onstarApplication;)V
    //   168: new com/roadtrack/onstar/nav/routing/FavoritesHistory$11
    //   171: astore_1
    //   172: aload_1
    //   173: aload_0
    //   174: aload #5
    //   176: invokespecial <init> : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;Lcom/roadtrack/onstar/onstarApplication;)V
    //   179: aload #4
    //   181: aload_1
    //   182: invokevirtual setOnItemSelectedListener : (Landroid/widget/AdapterView$OnItemSelectedListener;)V
    //   185: goto -> 201
    //   188: getstatic com/roadtrack/onstar/nav/routing/FavoritesHistory.TAG : Ljava/lang/String;
    //   191: ldc_w 'Error: setOnItemSelectedListener'
    //   194: aload_1
    //   195: invokevirtual getMessage : ()Ljava/lang/String;
    //   198: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   201: aload_0
    //   202: monitorexit
    //   203: return
    //   204: astore_1
    //   205: aload_0
    //   206: monitorexit
    //   207: aload_1
    //   208: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	204	finally
    //   21	27	30	java/lang/Exception
    //   21	27	204	finally
    //   42	52	30	java/lang/Exception
    //   42	52	204	finally
    //   54	93	30	java/lang/Exception
    //   54	93	204	finally
    //   99	185	30	java/lang/Exception
    //   99	185	204	finally
    //   188	201	204	finally
  }
  
  public static List<FavoritesHistoryVO> getFavorites(Context paramContext) {
    DBFunctions dBFunctions = new DBFunctions(paramContext);
    VehicleCatalogVO vehicleCatalogVO = dBFunctions.getSelectedVehicle(dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
    ArrayList<FavoritesHistoryVO> arrayList = dBFunctions.getFavoritesHistory(Enums.TypeItem.Favorites.toString(), vehicleCatalogVO.getDeviceId());
    sort(arrayList);
    if (arrayList.size() > 0)
      Collections.sort(arrayList, new Comparator<FavoritesHistoryVO>() {
            public int compare(FavoritesHistoryVO param1FavoritesHistoryVO1, FavoritesHistoryVO param1FavoritesHistoryVO2) {
              return param1FavoritesHistoryVO1.getAddress().compareTo(param1FavoritesHistoryVO2.getAddress());
            }
          }); 
    return arrayList;
  }
  
  public static List<FavoritesHistoryVO> getFavoritesCloud(ArrayList<SyncVO> paramArrayList) {
    ArrayList<FavoritesHistoryVO> arrayList = new ArrayList();
    for (byte b = 0; b < paramArrayList.size(); b++)
      arrayList.add(new FavoritesHistoryVO(Integer.valueOf(((SyncVO)paramArrayList.get(b)).getId_favs_history()).intValue(), "", ((SyncVO)paramArrayList.get(b)).getName(), ((SyncVO)paramArrayList.get(b)).getAddress(), ((SyncVO)paramArrayList.get(b)).getDate(), ((SyncVO)paramArrayList.get(b)).getDeviceId(), ((SyncVO)paramArrayList.get(b)).getType_item(), ((SyncVO)paramArrayList.get(b)).getCategory(), ((SyncVO)paramArrayList.get(b)).getLatlng(), ((SyncVO)paramArrayList.get(b)).getType_poi(), ((SyncVO)paramArrayList.get(b)).getType_poi())); 
    return arrayList;
  }
  
  public static List<FavoritesHistoryVO> getHistory(Context paramContext) {
    ArrayList<FavoritesHistoryVO> arrayList = (new DBFunctions(paramContext)).getNavFavoriteHistory(Enums.TypeItem.Historical.toString());
    sort(arrayList);
    return arrayList;
  }
  
  public static boolean insertSyncData(DBFunctions paramDBFunctions, Context paramContext) {
    VehicleCatalogVO vehicleCatalogVO = paramDBFunctions.getSelectedVehicle(paramDBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (vehicleCatalogVO != null) {
      ArrayList arrayList1 = paramDBFunctions.selectSyncData(vehicleCatalogVO.getDeviceId(), vehicleCatalogVO.getUser());
      ArrayList<SyncVO> arrayList = new ArrayList();
      for (SyncVO syncVO : arrayList1) {
        if (syncVO.getType_poi() != null && (!syncVO.getType_poi().equals("0") || !syncVO.getType_poi().equals("")))
          arrayList.add(syncVO); 
      } 
      bool1 = bool2;
      if (arrayList.size() > 0) {
        ArrayList arrayList2;
        String[] arrayOfString = Utilities.getCommIdAndSerialNumber(vehicleCatalogVO.getDeviceId());
        vehicleCatalogVO = null;
        if (NetUtilities.setUpHttpsConnection(GlobalMembers.URLSync, paramContext, 2131623963, GlobalMembers.nameKeystoreServiceWS))
          arrayList2 = (new WsAccess(paramContext)).syncInsertWcf(arrayList, arrayOfString[0], arrayOfString[1], arrayOfString[2]); 
        if (arrayList2 != null) {
          Iterator<SyncInsertResponse> iterator = arrayList2.iterator();
          bool1 = true;
          while (iterator.hasNext()) {
            SyncInsertResponse syncInsertResponse = iterator.next();
            if (syncInsertResponse != null) {
              if (syncInsertResponse.getStatus() != 0) {
                ImageView imageView = imageResponseSync;
                if (imageView != null)
                  imageView.setVisibility(0); 
                bool1 = false;
              } else {
                bool1 = true;
              } 
              paramDBFunctions.updateSynchronizedFavoriteHistory(syncInsertResponse.getId_favs(), Integer.valueOf(syncInsertResponse.getId_sync()).intValue(), String.valueOf(syncInsertResponse.getStatus()), syncInsertResponse.getDate());
            } 
          } 
        } else {
          bool1 = false;
        } 
      } 
    } 
    return bool1;
  }
  
  public static FavoritesHistory newInstance() {
    return new FavoritesHistory();
  }
  
  private void onListItemSelect(int paramInt1, int paramInt2) {
    this.listAdapter.toggleSelection(paramInt1, paramInt2);
    if (this.listAdapter.getGroupCount() > 0) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    if (paramInt1 != 0 && this.mActionMode == null)
      this.mActionMode = getActivity().startActionMode(new ActionModeCallback(paramInt2)); 
    if (paramInt1 == 0) {
      ActionMode actionMode = this.mActionMode;
      if (actionMode != null)
        actionMode.finish(); 
    } 
    if (this.mActionMode != null) {
      String str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_main_lbl_seleccionado_1, 2131689938);
      this.mActionMode.setTitle(String.format("%s %s", new Object[] { String.valueOf(this.listAdapter.getSelectedCount()), str }));
      if (this.listAdapter.getSelectedCount() > 1 || this.listAdapter.getSelectedCount() == 0) {
        this.mActionMode.getMenu().findItem(2131296896).setEnabled(false);
        return;
      } 
      this.mActionMode.getMenu().findItem(2131296896).setEnabled(true);
    } 
  }
  
  private void prepareCloudData(ArrayList<SyncVO> paramArrayList) {
    try {
      ArrayList<String> arrayList = new ArrayList();
      this();
      this.listDataHeader = arrayList;
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      this();
      this.listDataChild = (HashMap)hashMap;
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      this();
      String str1 = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_lbl_fav_1, 2131689923);
      String str2 = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_navigation_fav_lbl_historial_1, 2131689942);
      this.listDataHeader.add(str1);
      this.listDataHeader.add(str2);
      List<FavoritesHistoryVO> list1 = getFavoritesCloud(paramArrayList);
      DBFunctions dBFunctions = new DBFunctions();
      this(context);
      for (FavoritesHistoryVO favoritesHistoryVO : list1) {
        if (!dBFunctions.rowExist(favoritesHistoryVO.getAddress(), favoritesHistoryVO.getLatlng(), favoritesHistoryVO.getDevice_id()))
          dBFunctions.addFavoriteHistory("", favoritesHistoryVO.getName(), favoritesHistoryVO.getAddress(), favoritesHistoryVO.getDevice_id(), favoritesHistoryVO.getType_item(), favoritesHistoryVO.getCategory(), favoritesHistoryVO.getLatlng(), "0", favoritesHistoryVO.getType_poi()); 
      } 
      List<FavoritesHistoryVO> list2 = getHistory(context);
      this.listDataChild.put(this.listDataHeader.get(0), list1);
      this.listDataChild.put(this.listDataHeader.get(1), list2);
      if (getActivity() != null && getActivity().getCurrentFocus() != null)
        ((InputMethodManager)context.getSystemService("input_method")).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0); 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: prepareCloudData", exception.getMessage());
    } 
  }
  
  private void prepareListData() {
    try {
      ArrayList<String> arrayList = new ArrayList();
      this();
      this.listDataHeader = arrayList;
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      this();
      this.listDataChild = (HashMap)hashMap;
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      this();
      String str1 = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_lbl_fav_1, 2131689923);
      String str2 = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_navigation_fav_lbl_historial_1, 2131689942);
      this.listDataHeader.add(str1);
      this.listDataHeader.add(str2);
      List<FavoritesHistoryVO> list1 = getFavorites(getActivity().getApplicationContext());
      List<FavoritesHistoryVO> list2 = getHistory(getActivity().getApplicationContext());
      this.listDataChild.put(this.listDataHeader.get(0), list1);
      this.listDataChild.put(this.listDataHeader.get(1), list2);
      if (getActivity().getCurrentFocus() != null)
        ((InputMethodManager)context.getSystemService("input_method")).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0); 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: prepareListData", exception.getMessage());
    } 
  }
  
  private void prepareOrderData() {
    try {
      ArrayList<String> arrayList = new ArrayList();
      this();
      this.listDataHeader = arrayList;
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      this();
      this.listDataChild = (HashMap)hashMap;
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      this();
      String str1 = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_lbl_fav_1, 2131689923);
      String str2 = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_navigation_fav_lbl_historial_1, 2131689942);
      this.listDataHeader.add(str1);
      this.listDataHeader.add(str2);
      List<FavoritesHistoryVO> list2 = getFavorites(getActivity().getApplicationContext());
      List<FavoritesHistoryVO> list1 = getHistory(getActivity().getApplicationContext());
      this.listDataChild.put(this.listDataHeader.get(0), list2);
      this.listDataChild.put(this.listDataHeader.get(1), list1);
      if (getActivity().getCurrentFocus() != null)
        ((InputMethodManager)context.getSystemService("input_method")).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0); 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: prepareOrderData", exception.getMessage());
    } 
  }
  
  private void prepareSingleListData() {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/nav/routing/FavoritesHistory}} */
    try {
      ArrayList<String> arrayList = new ArrayList();
      this();
      this.listDataHeader = arrayList;
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      this();
      this.listDataChild = (HashMap)hashMap;
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      this();
      String str = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_lbl_fav_1, 2131689923);
      this.listDataHeader.add(str);
      this.listDataChild.put(this.listDataHeader.get(0), getFavorites(context));
      listExpandibleAdapter listExpandibleAdapter1 = new listExpandibleAdapter();
      this(context, this.listDataHeader, this.listDataChild, Enums.TypeItem.FavoritesSilgleHist, true);
      this.listAdapter = listExpandibleAdapter1;
      this.expListView.setOnItemLongClickListener(null);
      this.expListView.setOnChildClickListener(null);
      this.expListView.setAdapter((ExpandableListAdapter)this.listAdapter);
      this.expListView.expandGroup(0);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: prepareSingleListData", exception.getMessage());
    } finally {
      Exception exception;
    } 
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/nav/routing/FavoritesHistory}} */
  }
  
  private static void setDeviceType(onstarApplication paramonstarApplication) {
    String str = Utilities.getLastKnownDeviceSelected(paramonstarApplication, TAG).getDeviceTypeId();
    if (str != null && !str.equals("")) {
      GlobalMembers.deviceType = str;
    } else {
      GlobalMembers.deviceType = GlobalMembers.deviceTypeP7;
    } 
  }
  
  private void setReachedFavorite(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield expListView : Landroid/widget/ExpandableListView;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnonnull -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: getfield expListView : Landroid/widget/ExpandableListView;
    //   18: aconst_null
    //   19: invokevirtual setOnItemLongClickListener : (Landroid/widget/AdapterView$OnItemLongClickListener;)V
    //   22: aload_0
    //   23: getfield expListView : Landroid/widget/ExpandableListView;
    //   26: astore_3
    //   27: new com/roadtrack/onstar/nav/routing/FavoritesHistory$6
    //   30: astore_2
    //   31: aload_2
    //   32: aload_0
    //   33: aload_1
    //   34: invokespecial <init> : (Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;Ljava/lang/Object;)V
    //   37: aload_3
    //   38: aload_2
    //   39: invokevirtual setOnChildClickListener : (Landroid/widget/ExpandableListView$OnChildClickListener;)V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	45	finally
    //   14	42	45	finally
  }
  
  @SuppressLint({"ResourceAsColor"})
  private void setVehicleSelected(onstarApplication paramonstarApplication) {
    boolean bool;
    if (MainActivity.onOpenDoors.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onFindMe.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onPID.booleanValue() || MainActivity.onDTC.booleanValue()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool)
      return; 
    UserDevicesVO userDevicesVO2 = Utilities.getLastKnownDeviceSelected(paramonstarApplication, TAG);
    UserDevicesVO userDevicesVO1 = paramonstarApplication.getmDeviceUserList().get(this.vehiclelist_spinner.getSelectedItemPosition());
    this.dbFun.addVehicleSelected(getActivity().getApplicationContext(), this.dbFun.getUserPreference(GlobalMembers.userLogged).getUser(), userDevicesVO1);
    if (!userDevicesVO1.equals(userDevicesVO2)) {
      Utilities.updateVehicleSelected(getActivity().getApplicationContext(), this.dbFun.getUserPreference(GlobalMembers.userLogged).getUser(), userDevicesVO1);
      this.vehiclelist_spinner.setSelection(Utilities.getLastKnownVehicleSelected((Context)paramonstarApplication, this.dbFun.getUserPreference(GlobalMembers.userLogged).getUser(), paramonstarApplication));
      RemoteDiagnosticActivity.isdialog_action_process_frame_visible = false;
    } 
  }
  
  public static void sort(List<FavoritesHistoryVO> paramList) {
    if (paramList != null)
      Collections.sort(paramList, new Comparator<FavoritesHistoryVO>() {
            public int compare(FavoritesHistoryVO param1FavoritesHistoryVO1, FavoritesHistoryVO param1FavoritesHistoryVO2) {
              return param1FavoritesHistoryVO1.getName().compareToIgnoreCase(param1FavoritesHistoryVO2.getName());
            }
          }); 
  }
  
  private void syncDeletedFavorites() {
    DBFunctions dBFunctions = new DBFunctions((Context)getActivity());
    VehicleCatalogVO vehicleCatalogVO = dBFunctions.getSelectedVehicle(dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
    ArrayList<FavoritesHistoryVO> arrayList = dBFunctions.getDeletedFavorites(vehicleCatalogVO.getDeviceId());
    for (byte b = 0; b < arrayList.size(); b++) {
      if (deleteSyncData(((FavoritesHistoryVO)arrayList.get(b)).getId_sync(), vehicleCatalogVO.getUser(), dBFunctions, vehicleCatalogVO.getDeviceId()))
        dBFunctions.deleteFavorite(Integer.valueOf(((FavoritesHistoryVO)arrayList.get(b)).getId_favs_history()).intValue()); 
    } 
  }
  
  private void syncEditedFavorites() {
    DBFunctions dBFunctions = new DBFunctions((Context)getActivity());
    UserPreferenceVO userPreferenceVO = dBFunctions.getUserPreference(GlobalMembers.userLogged);
    ArrayList<SyncVO> arrayList = dBFunctions.selectSyncDataEdited(dBFunctions.getSelectedVehicle(userPreferenceVO.getUser()).getDeviceId(), userPreferenceVO.getUser());
    for (byte b = 0; b < arrayList.size(); b++) {
      if (updateSyncData(((SyncVO)arrayList.get(b)).getId_sync(), dBFunctions, arrayList.get(b), ((SyncVO)arrayList.get(b)).getName()))
        dBFunctions.updateFavoriteHistory(Integer.valueOf(((SyncVO)arrayList.get(b)).getId_favs_history()).intValue(), ((SyncVO)arrayList.get(b)).getName(), "0"); 
    } 
  }
  
  protected void actionDeleteFavorite(final String selected, Enums.TypeItem paramTypeItem, String paramString2) {
    if (getActivity().getCurrentFocus() != null)
      ((InputMethodManager)context.getSystemService("input_method")).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0); 
    final DBFunctions dbfun = new DBFunctions(context);
    ArrayList<FavoritesHistoryVO> arrayList = dBFunctions.getSelectedFavorite(paramTypeItem.toString(), selected, paramString2);
    if (arrayList.size() > 0) {
      FavoritesHistoryVO favoritesHistoryVO = arrayList.get(0);
    } else {
      selected = null;
    } 
    View view = View.inflate(context, 2131427470, null);
    this.tvTitle = (TextView)view.findViewById(2131297124);
    this.tvMessage = (TextView)view.findViewById(2131296478);
    String str2 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_latmenu_lbl_favoritosehistorico_1, 2131690145);
    this.btnOk = (Button)view.findViewById(2131296321);
    this.tvTitle.setText(str2);
    Drawable drawable = Utilities.getDrawableFromConfigList(context, DrawableResourcesVO.favorite_title_delete, 2131165413);
    this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    String str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_lbl_eliminarregistros_1, 2131689958);
    String str3 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.global_popup_lbl_eliminarregistro_1, 2131689957);
    if (arrayList.size() > 1) {
      this.tvMessage.setText(str1);
    } else {
      this.tvMessage.setText(str3);
    } 
    str1 = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.DeleteData, 2131689489);
    final Dialog builder = new Dialog(context, 16973840);
    dialog.setTitle(str1);
    dialog.setContentView(view);
    dialog.setCancelable(true);
    this.btnOk.setOnClickListener(new View.OnClickListener() {
          final FavoritesHistory this$0;
          
          final Dialog val$builder;
          
          final DBFunctions val$dbfun;
          
          final FavoritesHistoryVO val$selected;
          
          public void onClick(View param1View) {
            (new Thread() {
                final FavoritesHistory.null this$1;
                
                public void run() {
                  try {
                    if (selected != null) {
                      SyncVO syncVO;
                      UserPreferenceVO userPreferenceVO = dbfun.getUserPreference(GlobalMembers.userLogged);
                      VehicleCatalogVO vehicleCatalogVO = dbfun.getSelectedVehicle(userPreferenceVO.getUser());
                      ArrayList arrayList1 = dbfun.selectSyncData(vehicleCatalogVO.getDeviceId(), vehicleCatalogVO.getUser());
                      ArrayList arrayList2 = null;
                      Iterator<SyncVO> iterator = arrayList1.iterator();
                      while (true) {
                        arrayList1 = arrayList2;
                        if (iterator.hasNext()) {
                          syncVO = iterator.next();
                          boolean bool = syncVO.getId_favs_history().equals(String.valueOf(selected.getId_favs_history()));
                          if (bool)
                            break; 
                          continue;
                        } 
                        break;
                      } 
                      if (syncVO != null) {
                        if (FavoritesHistory.this.deleteSyncData(selected.getId_sync(), vehicleCatalogVO.getUser(), dbfun, syncVO.getDeviceId())) {
                          dbfun.deleteFavorite(Integer.valueOf(selected.getId_favs_history()).intValue());
                        } else {
                          dbfun.updateFavoriteHistory(Integer.valueOf(selected.getId_favs_history()).intValue(), selected.getName(), "2");
                        } 
                      } else {
                        dbfun.updateFavoriteHistory(Integer.valueOf(selected.getId_favs_history()).intValue(), selected.getName(), "2");
                      } 
                    } else {
                      String str = Utilities.getStringFromConfigList((Context)FavoritesHistory.this.getActivity(), FavoritesHistory.this.stringsResourcesVO.msgCantDelete, 2131690135);
                      Toast.makeText(FavoritesHistory.this.getActivity().getApplicationContext(), str, 0).show();
                    } 
                    Message message = new Message();
                    this();
                    message.arg1 = 6;
                    NavigateCommonDialogActivity.updateFavoritesListHandler.sendMessage(message);
                    FavoritesHistory.this.edit_favoritespopup.setVisibility(8);
                  } catch (Exception exception) {
                    exception.printStackTrace();
                  } 
                }
              }).start();
            builder.dismiss();
          }
        });
    dialog.show();
  }
  
  protected void actionEditFavorite(String paramString1, EditText paramEditText, String paramString2, double paramDouble1, double paramDouble2, Enums.TypeItem paramTypeItem, Enums.Category paramCategory, String paramString3, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: new com/roadtrack/onstar/DAO/DBFunctions
    //   3: dup
    //   4: getstatic com/roadtrack/onstar/nav/routing/FavoritesHistory.context : Landroid/content/Context;
    //   7: invokespecial <init> : (Landroid/content/Context;)V
    //   10: astore #9
    //   12: aload #9
    //   14: aload #8
    //   16: invokevirtual toString : ()Ljava/lang/String;
    //   19: aload_1
    //   20: aload #10
    //   22: invokevirtual getSelectedFavorite : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/ArrayList;
    //   25: astore_3
    //   26: aload_3
    //   27: invokevirtual size : ()I
    //   30: ifle -> 45
    //   33: aload_3
    //   34: iconst_0
    //   35: invokevirtual get : (I)Ljava/lang/Object;
    //   38: checkcast com/roadtrack/onstar/VO/FavoritesHistoryVO
    //   41: astore_3
    //   42: goto -> 47
    //   45: aconst_null
    //   46: astore_3
    //   47: aload_3
    //   48: ifnull -> 271
    //   51: aload #9
    //   53: aload #8
    //   55: invokevirtual toString : ()Ljava/lang/String;
    //   58: aload_1
    //   59: aload_2
    //   60: invokevirtual getText : ()Landroid/text/Editable;
    //   63: invokevirtual toString : ()Ljava/lang/String;
    //   66: invokevirtual existFavorite : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   69: ifeq -> 115
    //   72: aload_0
    //   73: invokevirtual getActivity : ()Landroidx/fragment/app/FragmentActivity;
    //   76: aload_0
    //   77: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   80: getfield navigatio_favoritos_yaexisteunpunto_1 : Ljava/lang/String;
    //   83: ldc_w 2131690141
    //   86: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   89: ldc_w '%s'
    //   92: aload_2
    //   93: invokevirtual getText : ()Landroid/text/Editable;
    //   96: invokevirtual toString : ()Ljava/lang/String;
    //   99: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   102: astore_1
    //   103: getstatic com/roadtrack/onstar/nav/routing/FavoritesHistory.context : Landroid/content/Context;
    //   106: aload_1
    //   107: iconst_0
    //   108: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   111: invokevirtual show : ()V
    //   114: return
    //   115: aload #9
    //   117: aload_3
    //   118: invokevirtual getId_favs_history : ()I
    //   121: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   124: invokevirtual intValue : ()I
    //   127: aload_2
    //   128: invokevirtual getText : ()Landroid/text/Editable;
    //   131: invokevirtual toString : ()Ljava/lang/String;
    //   134: ldc_w '1'
    //   137: invokevirtual updateFavoriteHistory : (ILjava/lang/String;Ljava/lang/String;)V
    //   140: aload #9
    //   142: aload #9
    //   144: getstatic com/roadtrack/onstar/BO/GlobalMembers.userLogged : Ljava/lang/String;
    //   147: invokevirtual getUserPreference : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   150: invokevirtual getUser : ()Ljava/lang/String;
    //   153: invokevirtual getSelectedVehicle : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/VehicleCatalogVO;
    //   156: astore_1
    //   157: aload #9
    //   159: aload_1
    //   160: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   163: aload_1
    //   164: invokevirtual getUser : ()Ljava/lang/String;
    //   167: invokevirtual selectSyncData : (Ljava/lang/String;Ljava/lang/String;)Ljava/util/ArrayList;
    //   170: invokevirtual iterator : ()Ljava/util/Iterator;
    //   173: astore #8
    //   175: aload #8
    //   177: invokeinterface hasNext : ()Z
    //   182: ifeq -> 216
    //   185: aload #8
    //   187: invokeinterface next : ()Ljava/lang/Object;
    //   192: checkcast com/roadtrack/onstar/VO/SyncVO
    //   195: astore_1
    //   196: aload_1
    //   197: invokevirtual getId_favs_history : ()Ljava/lang/String;
    //   200: aload_3
    //   201: invokevirtual getId_favs_history : ()I
    //   204: invokestatic valueOf : (I)Ljava/lang/String;
    //   207: invokevirtual equals : (Ljava/lang/Object;)Z
    //   210: ifeq -> 175
    //   213: goto -> 218
    //   216: aconst_null
    //   217: astore_1
    //   218: aload_1
    //   219: ifnull -> 307
    //   222: aload_0
    //   223: aload_3
    //   224: invokevirtual getId_sync : ()Ljava/lang/String;
    //   227: aload #9
    //   229: aload_1
    //   230: aload_2
    //   231: invokevirtual getText : ()Landroid/text/Editable;
    //   234: invokevirtual toString : ()Ljava/lang/String;
    //   237: invokevirtual updateSyncData : (Ljava/lang/String;Lcom/roadtrack/onstar/DAO/DBFunctions;Lcom/roadtrack/onstar/VO/SyncVO;Ljava/lang/String;)Z
    //   240: ifeq -> 307
    //   243: aload #9
    //   245: aload_3
    //   246: invokevirtual getId_favs_history : ()I
    //   249: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   252: invokevirtual intValue : ()I
    //   255: aload_2
    //   256: invokevirtual getText : ()Landroid/text/Editable;
    //   259: invokevirtual toString : ()Ljava/lang/String;
    //   262: ldc_w '0'
    //   265: invokevirtual updateFavoriteHistory : (ILjava/lang/String;Ljava/lang/String;)V
    //   268: goto -> 307
    //   271: aload_0
    //   272: invokevirtual getActivity : ()Landroidx/fragment/app/FragmentActivity;
    //   275: aload_0
    //   276: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   279: getfield msgCantUpdate : Ljava/lang/String;
    //   282: ldc_w 2131690136
    //   285: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   288: astore_1
    //   289: aload_0
    //   290: invokevirtual getActivity : ()Landroidx/fragment/app/FragmentActivity;
    //   293: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   296: checkcast com/roadtrack/onstar/onstarApplication
    //   299: aload_1
    //   300: iconst_0
    //   301: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   304: invokevirtual show : ()V
    //   307: new android/os/Message
    //   310: dup
    //   311: invokespecial <init> : ()V
    //   314: astore_1
    //   315: aload_1
    //   316: bipush #6
    //   318: putfield arg1 : I
    //   321: getstatic com/roadtrack/onstar/nav/routing/NavigateCommonDialogActivity.updateFavoritesListHandler : Landroid/os/Handler;
    //   324: aload_1
    //   325: invokevirtual sendMessage : (Landroid/os/Message;)Z
    //   328: pop
    //   329: aload_0
    //   330: getfield edit_favoritespopup : Landroid/widget/LinearLayout;
    //   333: bipush #8
    //   335: invokevirtual setVisibility : (I)V
    //   338: return
  }
  
  public boolean deleteSyncData(String paramString1, String paramString2, DBFunctions paramDBFunctions, String paramString3) {
    VehicleCatalogVO vehicleCatalogVO = paramDBFunctions.getSelectedVehicle(paramString2);
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (vehicleCatalogVO != null) {
      String[] arrayOfString = Utilities.getCommIdAndSerialNumber(vehicleCatalogVO.getDeviceId());
      paramDBFunctions.selectSyncData(vehicleCatalogVO.getDeviceId(), vehicleCatalogVO.getUser());
      bool1 = bool2;
      if (NetUtilities.setUpHttpsConnection(GlobalMembers.URLSync, context, 2131623963, GlobalMembers.nameKeystoreServiceWS))
        bool1 = (new WsAccess(context)).deleteSyncDataWSWcf(paramString1, paramString2, paramString3, arrayOfString[0], arrayOfString[1]); 
    } 
    return bool1;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    context = (Context)getActivity();
    this.vista = paramLayoutInflater.inflate(2131427410, paramViewGroup, false);
    this.stringsResourcesVO = new StringsResourcesVO();
    this.progressBar = (ProgressBar)this.vista.findViewById(2131296968);
    this.actionValueMessage = (TextView)this.vista.findViewById(2131296257);
    String str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.navigation_latmenu_lbl_favoritosehistorico_1, 2131690145);
    this.actionValueMessage.setText(str);
    this.vehiclelist_spinner = (Spinner)this.vista.findViewById(2131297230);
    this.sync_button = (Button)this.vista.findViewById(2131297065);
    str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.fav_hist_sync_sync_button, 2131689808);
    this.sync_button.setText(str);
    this.layoutSpinnerSection = (LinearLayout)this.vista.findViewById(2131296699);
    imageResponseSync = (ImageView)this.vista.findViewById(2131296618);
    this.editroutechooseloc = (TextView)this.vista.findViewById(2131296511);
    str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.choosedest, 2131689698);
    this.editroutechooseloc.setHint(str);
    this.editroutelocation = (TextView)this.vista.findViewById(2131296512);
    str = Utilities.getStringFromConfigList((Context)getActivity(), this.stringsResourcesVO.currentloc, 2131689741);
    this.editroutelocation.setHint(str);
    this.expListView = (ExpandableListView)this.vista.findViewById(2131296489);
    this.expListView.setDividerHeight(2);
    this.expListView.setGroupIndicator(null);
    this.expListView.setClickable(true);
    this.dbFun = new DBFunctions(GlobalMembers.contexGlobal);
    this.layoutSpinnerSection.setVisibility(0);
    LinearLayout linearLayout2 = (LinearLayout)this.vista.findViewById(2131296531);
    LinearLayout linearLayout1 = (LinearLayout)this.vista.findViewById(2131296507);
    fillVehicleList(this.vehiclelist_spinner, context);
    if (getArguments().getInt(KEY_GET_TYPE_SINGLE_FAVORITES) != 0) {
      linearLayout2.setVisibility(8);
      linearLayout1.setVisibility(8);
      this.layoutSpinnerSection.setVisibility(8);
      prepareSingleListData();
    } else {
      Enums.TypeItem typeItem = (Enums.TypeItem)getArguments().get(KEY_GET_TYPE);
      linearLayout2.setVisibility(0);
      linearLayout1.setVisibility(8);
      prepareListData();
      this.listAdapter = new listExpandibleAdapter(context, this.listDataHeader, this.listDataChild, Enums.TypeItem.NaviFavoritesHist, true);
      this.expListView.setAdapter((ExpandableListAdapter)this.listAdapter);
      int i = null.$SwitchMap$com$roadtrack$onstar$Enums$TypeItem[typeItem.ordinal()];
      if (i != 1) {
        if (i != 2) {
          if (i == 3) {
            this.expListView.expandGroup(0);
            this.isFavorites = true;
            doSync();
          } 
        } else {
          this.expListView.expandGroup(1);
          this.isFavorites = false;
        } 
      } else {
        this.expListView.expandGroup(0);
        this.isFavorites = true;
        doSync();
      } 
      this.expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            final FavoritesHistory this$0;
            
            public boolean onChildClick(ExpandableListView param1ExpandableListView, View param1View, int param1Int1, int param1Int2, long param1Long) {
              try {
                if (FavoritesHistory.this.mActionMode == null) {
                  FavoritesHistoryVO favoritesHistoryVO = ((List<FavoritesHistoryVO>)FavoritesHistory.this.listDataChild.get(FavoritesHistory.this.listDataHeader.get(param1Int1))).get(param1Int2);
                  GlobalMembers.favSelected = favoritesHistoryVO;
                  FavoritesHistory.listener.onChildClick(favoritesHistoryVO);
                } else if (param1Int1 == 0) {
                  FavoritesHistory.this.onListItemSelect(param1Int1, param1Int2);
                } 
              } catch (Exception exception) {
                Utilities.escribeArchivo(FavoritesHistory.TAG, "Error: setOnChildClickListener", exception.getMessage());
              } 
              return false;
            }
          });
    } 
    this.sync_button.setOnClickListener(new View.OnClickListener() {
          final FavoritesHistory this$0;
          
          public void onClick(View param1View) {
            FavoritesHistory.this.doSync();
          }
        });
    return this.vista;
  }
  
  public void onDestroy() {
    super.onDestroy();
    Timer timer = this.t;
    if (timer != null) {
      timer.cancel();
      this.t.purge();
      this.t = null;
      try {
        this.notificationManager.cancel(this.id);
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error: onDestroy", exception.getMessage());
      } 
    } 
    isPrimeraVez = true;
    Debug.stopMethodTracing();
    if (this.networkChangeReceiver != null)
      getActivity().unregisterReceiver(this.networkChangeReceiver); 
  }
  
  public void onResume() {
    super.onResume();
    NavigateCommonDialogActivity.updateFavoritesListHandler = this.handlerHistory;
    getActivity().registerReceiver(this.networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBoolean(this.ISTODAY_VALUE, this.isToday);
    Utilities.escribeArchivo(TAG, "FAVORITES", "saving instance");
  }
  
  public void onStop() {
    super.onStop();
    Debug.stopMethodTracing();
  }
  
  public void setListener(MapsCommonFragment paramMapsCommonFragment) {
    listener = paramMapsCommonFragment;
  }
  
  public boolean updateSyncData(String paramString1, DBFunctions paramDBFunctions, SyncVO paramSyncVO, String paramString2) {
    VehicleCatalogVO vehicleCatalogVO = paramDBFunctions.getSelectedVehicle(paramDBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (vehicleCatalogVO != null) {
      String[] arrayOfString = Utilities.getCommIdAndSerialNumber(vehicleCatalogVO.getDeviceId());
      paramDBFunctions.selectSyncData(paramSyncVO.getDeviceId(), vehicleCatalogVO.getUser());
      if (NetUtilities.setUpHttpsConnection(GlobalMembers.URLSync, context, 2131623963, GlobalMembers.nameKeystoreServiceWS)) {
        ArrayList arrayList = (new WsAccess(context)).updateSyncDataWSWcf(paramString1, paramSyncVO.getUser(), paramString2, paramSyncVO, arrayOfString[0], arrayOfString[1], arrayOfString[2]);
      } else {
        paramString1 = null;
      } 
      bool1 = bool2;
      if (paramString1 != null) {
        Iterator<SyncInsertResponse> iterator = paramString1.iterator();
        label26: while (true) {
          bool1 = false;
          while (iterator.hasNext()) {
            SyncInsertResponse syncInsertResponse = iterator.next();
            if (syncInsertResponse != null) {
              if (syncInsertResponse.getStatus() != 0) {
                ImageView imageView = imageResponseSync;
                if (imageView != null) {
                  imageView.setVisibility(0);
                  continue label26;
                } 
                continue label26;
              } 
              bool1 = true;
            } 
          } 
          break;
        } 
      } 
    } 
    return bool1;
  }
  
  private class ActionModeCallback implements ActionMode.Callback {
    int position = -1;
    
    final FavoritesHistory this$0;
    
    public ActionModeCallback(int param1Int) {
      this.position = param1Int;
    }
    
    public boolean onActionItemClicked(ActionMode param1ActionMode, MenuItem param1MenuItem) {
      Boolean bool;
      Message message;
      SparseBooleanArray sparseBooleanArray = FavoritesHistory.this.listAdapter.getSelectedIds();
      int i = param1MenuItem.getItemId();
      if (i != 2131296894) {
        if (i != 2131296896) {
          bool = Boolean.valueOf(false);
        } else {
          FavoritesHistoryVO favoritesHistoryVO = (FavoritesHistoryVO)FavoritesHistory.this.listAdapter.getChildObject(0, sparseBooleanArray.keyAt(0));
          Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
          hashtable.put(FavoritesHistory.KEY_ACTION_VIEW, FavoritesHistory.EDIT_FAV);
          hashtable.put(FavoritesHistory.KEY_SET_DEVICEID, favoritesHistoryVO.getDevice_id());
          hashtable.put(FavoritesHistory.KEY_SET_NAME, favoritesHistoryVO.getName());
          hashtable.put(FavoritesHistory.KEY_SET_ADRESS, favoritesHistoryVO.getAddress());
          hashtable.put(FavoritesHistory.KEY_SET_LATITUDE, favoritesHistoryVO.getLatlng());
          hashtable.put(FavoritesHistory.KEY_SET_LONGITUDE, favoritesHistoryVO.getLatlng());
          hashtable.put(FavoritesHistory.KEY_SET_TYPE, favoritesHistoryVO.getType_item());
          hashtable.put("_TypeItem", favoritesHistoryVO.getType_item());
          message = new Message();
          message.arg1 = 3;
          message.arg2 = this.position;
          message.obj = hashtable;
          NavigateCommonDialogActivity.updateFavoritesListHandler.sendMessage(message);
          bool = Boolean.valueOf(true);
        } 
      } else {
        String str = Utilities.getStringFromConfigList((Context)FavoritesHistory.this.getActivity(), FavoritesHistory.this.stringsResourcesVO.favoriteConfirm, 2131689809);
        ConfirmEraseDialog confirmEraseDialog = ConfirmEraseDialog.newInstance();
        confirmEraseDialog.setValues(str, sparseBooleanArray, (MenuItem)message, (ActionMode)bool, FavoritesHistory.this.listAdapter);
        confirmEraseDialog.show(FavoritesHistory.this.getFragmentManager(), null);
        bool = Boolean.valueOf(true);
      } 
      return bool.booleanValue();
    }
    
    public boolean onCreateActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      param1ActionMode.getMenuInflater().inflate(2131492865, param1Menu);
      FavoritesHistory.this.listAdapter.setActionModeIndicator(true);
      return true;
    }
    
    public void onDestroyActionMode(ActionMode param1ActionMode) {
      FavoritesHistory.this.listAdapter.setActionModeIndicator(false);
      FavoritesHistory.this.listAdapter.removeSelection();
      FavoritesHistory favoritesHistory = FavoritesHistory.this;
      favoritesHistory.mActionMode = null;
      FavoritesHistory.access$3502(favoritesHistory, (LinearLayout)favoritesHistory.vista.findViewById(2131296507));
      if (FavoritesHistory.this.edit_favoritespopup1 != null && FavoritesHistory.this.edit_favoritespopup1.getVisibility() == 0)
        FavoritesHistory.this.edit_favoritespopup1.setVisibility(8); 
    }
    
    public boolean onPrepareActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return false;
    }
  }
  
  private class AsynchCheckDataSync extends AsyncTask<Void, Void, Void> {
    private boolean noRemoteFavorites = false;
    
    private boolean successfulSync = false;
    
    final FavoritesHistory this$0;
    
    private AsynchCheckDataSync() {}
    
    private ArrayList<SyncVO> selectData(DBFunctions param1DBFunctions) {
      null = param1DBFunctions.getSelectedVehicle(param1DBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
      if (null != null) {
        String[] arrayOfString = Utilities.getCommIdAndSerialNumber(null.getDeviceId());
        if (NetUtilities.setUpHttpsConnection(GlobalMembers.URLSync, FavoritesHistory.context, 2131623963, GlobalMembers.nameKeystoreServiceWS))
          return (new WsAccess(FavoritesHistory.context)).syncSelectWcf(null.getUser(), null.getDeviceId(), arrayOfString[2]); 
      } 
      return null;
    }
    
    private boolean selectNewData(DBFunctions param1DBFunctions) {
      VehicleCatalogVO vehicleCatalogVO = param1DBFunctions.getSelectedVehicle(param1DBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
      if (vehicleCatalogVO != null) {
        String[] arrayOfString = Utilities.getCommIdAndSerialNumber(vehicleCatalogVO.getDeviceId());
        Context context = GlobalMembers.contexGlobal;
        if (FavoritesHistory.this.getActivity() != null)
          context = FavoritesHistory.this.getActivity().getApplicationContext(); 
        ArrayList<SyncVO> arrayList2 = null;
        if (NetUtilities.setUpHttpsConnection(GlobalMembers.URLSync, FavoritesHistory.context, 2131623963, GlobalMembers.nameKeystoreServiceWS))
          arrayList2 = (new WsAccess(context)).syncSelectWcf(vehicleCatalogVO.getUser(), vehicleCatalogVO.getDeviceId(), arrayOfString[2]); 
        ArrayList<SyncVO> arrayList1 = new ArrayList();
        if (arrayList2 != null && arrayList2.size() > 0)
          for (byte b = 0; b < arrayList2.size(); b++) {
            byte b1 = 0;
            boolean bool = false;
            while (b1 < arrayList1.size()) {
              if (((SyncVO)arrayList1.get(b1)).getName().equals(((SyncVO)arrayList2.get(b)).getName()))
                bool = true; 
              b1++;
            } 
            if (!bool)
              arrayList1.add(arrayList2.get(b)); 
          }  
        for (SyncVO syncVO : arrayList1) {
          if (syncVO != null && !param1DBFunctions.reviewFavoriteName(syncVO.getDeviceId(), syncVO.getName())) {
            Message message = new Message();
            message.arg1 = 5;
            message.obj = arrayList1;
            NavigateCommonDialogActivity.updateFavoritesListHandler.sendMessage(message);
          } 
        } 
      } 
      return true;
    }
    
    protected Void doInBackground(Void... param1VarArgs) {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(AsynchCheckDataSync.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      FavoritesHistory.this.syncDeletedFavorites();
      FavoritesHistory.this.syncEditedFavorites();
      DBFunctions dBFunctions = new DBFunctions((Context)FavoritesHistory.this.getActivity());
      ArrayList<SyncVO> arrayList = dBFunctions.getNoSyncFavorites(Enums.TypeItem.Favorites.toString());
      boolean bool3 = false;
      boolean bool2 = false;
      boolean bool1 = bool3;
      if (arrayList != null)
        if (arrayList.size() > 0) {
          Iterator<FavoritesHistoryVO> iterator = arrayList.iterator();
          while (true) {
            bool1 = bool2;
            if (iterator.hasNext()) {
              FavoritesHistoryVO favoritesHistoryVO = iterator.next();
              if (!favoritesHistoryVO.getType_poi().equals("0") || favoritesHistoryVO.getType_poi().equals(""))
                bool2 = true; 
              continue;
            } 
            break;
          } 
        } else {
          VehicleCatalogVO vehicleCatalogVO = dBFunctions.getSelectedVehicle(dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
          ArrayList arrayList1 = dBFunctions.getMaxDateFavorites(Enums.TypeItem.Favorites.toString(), vehicleCatalogVO.getDeviceId());
          arrayList = selectData(dBFunctions);
          if (arrayList1 != null) {
            if (arrayList != null && arrayList.size() > 0) {
              dBFunctions.deleteAllVehicleFavorite(vehicleCatalogVO.getDeviceId());
              this.successfulSync = selectNewData(dBFunctions);
              bool1 = bool3;
            } else if (arrayList1 != null && arrayList1.size() > 0) {
              this.noRemoteFavorites = true;
              dBFunctions.deleteAllVehicleFavorite(vehicleCatalogVO.getDeviceId());
              this.successfulSync = true;
              Message message = new Message();
              message.arg1 = 6;
              NavigateCommonDialogActivity.updateFavoritesListHandler.sendMessage(message);
              bool1 = true;
            } else {
              dBFunctions.deleteAllVehicleFavorite(vehicleCatalogVO.getDeviceId());
              this.successfulSync = true;
              Message message = new Message();
              message.arg1 = 6;
              NavigateCommonDialogActivity.updateFavoritesListHandler.sendMessage(message);
              bool1 = bool3;
            } 
          } else {
            this.successfulSync = selectNewData(dBFunctions);
            bool1 = bool3;
          } 
        }  
      if (bool1) {
        VehicleCatalogVO vehicleCatalogVO = dBFunctions.getSelectedVehicle(dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser());
        arrayList = dBFunctions.getMaxDateFavorites(Enums.TypeItem.Favorites.toString(), vehicleCatalogVO.getDeviceId());
        if (arrayList != null && arrayList.size() > 0 && FavoritesHistory.insertSyncData(dBFunctions, FavoritesHistory.context) && selectData(dBFunctions) != null) {
          dBFunctions.deleteAllVehicleFavorite(vehicleCatalogVO.getDeviceId());
          this.successfulSync = selectNewData(dBFunctions);
        } 
      } 
      return null;
    }
    
    protected void onPostExecute(Void param1Void) {
      super.onPostExecute(param1Void);
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      String str2 = Utilities.getStringFromConfigList(FavoritesHistory.context, stringsResourcesVO.fav_hist_sync_no_favorites_available, 2131689807);
      String str3 = Utilities.getStringFromConfigList(FavoritesHistory.context, stringsResourcesVO.navigation_lbl_fav_sinccompletada_1, 2131690154);
      String str1 = Utilities.getStringFromConfigList(FavoritesHistory.context, stringsResourcesVO.main_lbl_popup_servicionodisponible_1, 2131690067);
      String str4 = Utilities.getStringFromConfigList(FavoritesHistory.context, stringsResourcesVO.navigation_latmenu_lbl_favoritosehistorico_1, 2131690145);
      if (FavoritesHistory.this.isAdded()) {
        if (this.noRemoteFavorites) {
          Toast.makeText(FavoritesHistory.context, str2, 0).show();
        } else if (this.successfulSync) {
          Toast.makeText(FavoritesHistory.context, str3, 0).show();
        } else {
          Toast.makeText(FavoritesHistory.context, str1, 0).show();
        } 
        FavoritesHistory.this.progressBar.setVisibility(8);
        FavoritesHistory.this.actionValueMessage.setText(str4);
      } 
      FavoritesHistory.isSyncRunning = false;
    }
    
    protected void onPreExecute() {
      super.onPreExecute();
      this.noRemoteFavorites = false;
      FavoritesHistory.this.progressBar.setVisibility(0);
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      String str = Utilities.getStringFromConfigList(FavoritesHistory.context, stringsResourcesVO.navigation_lbl_fav_sincdatos_1, 2131690155);
      FavoritesHistory.this.actionValueMessage.setText(str);
    }
  }
  
  public static interface FavoritesHistoryListener {
    void onChildClick(FavoritesHistoryVO param1FavoritesHistoryVO);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/nav/routing/FavoritesHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */