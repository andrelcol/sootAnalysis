package com.roadtrack.onstar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.legacy.app.ActionBarDrawerToggle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.roadtrack.onstar.BO.ActionsProcess;
import com.roadtrack.onstar.BO.BackGroundRestartHandler;
import com.roadtrack.onstar.BO.BluetoothServiceRT;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.ManagerBluetooth;
import com.roadtrack.onstar.BO.ManagerNotificationPlatinum;
import com.roadtrack.onstar.BO.MapDownload;
import com.roadtrack.onstar.BO.MessagesDispatchService;
import com.roadtrack.onstar.BO.MessagesManager;
import com.roadtrack.onstar.BO.MessagesObjects;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.BO.RequestManager;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.DAO.GetVerificationAccountFile;
import com.roadtrack.onstar.DAO.pushManager;
import com.roadtrack.onstar.VO.ActionResultVO;
import com.roadtrack.onstar.VO.CustomActionButton;
import com.roadtrack.onstar.VO.CustomActionButtonFactory;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.FollowMePointsVO;
import com.roadtrack.onstar.VO.GetCommandStatusVO;
import com.roadtrack.onstar.VO.GoogleMapVO;
import com.roadtrack.onstar.VO.Historical;
import com.roadtrack.onstar.VO.MapUpdateVO;
import com.roadtrack.onstar.VO.PaymentCardResponse;
import com.roadtrack.onstar.VO.PushAlertsVO;
import com.roadtrack.onstar.VO.PushNotificationsVO;
import com.roadtrack.onstar.VO.RenewalPlansListResponseO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import com.roadtrack.onstar.VO.commonQueue;
import com.roadtrack.onstar.activities.GenericWVActivity;
import com.roadtrack.onstar.adapter.DialogAlert;
import com.roadtrack.onstar.adapter.DialogMapSupportDownload;
import com.roadtrack.onstar.adapter.DialogMenu;
import com.roadtrack.onstar.adapter.DialogSpeed;
import com.roadtrack.onstar.adapter.NavigationAdapter;
import com.roadtrack.onstar.adapter.SectionsPagerAdapter;
import com.roadtrack.onstar.analytics.AnalyticsHelper;
import com.roadtrack.onstar.analytics.AnalyticsHelperFactory;
import com.roadtrack.onstar.analytics.AnalyticsTypes;
import com.roadtrack.onstar.async_tasks.TimerAsyncTAsk;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponseList;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.ICounterCallback;
import com.roadtrack.onstar.async_tasks.intefaces.PaymentCard_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.RenewalPlans_Interface;
import com.roadtrack.onstar.async_tasks.tasks.AsyncTaskUnregisterDevice;
import com.roadtrack.onstar.async_tasks.tasks.DeleteDevicesTask;
import com.roadtrack.onstar.async_tasks.tasks.GetLocatorToken;
import com.roadtrack.onstar.async_tasks.tasks.GetMyPlanInformation_Task;
import com.roadtrack.onstar.async_tasks.tasks.GetOnStarClubURL;
import com.roadtrack.onstar.async_tasks.tasks.GetOnstarClubAutenticate;
import com.roadtrack.onstar.async_tasks.tasks.GetPaymentHistoricalTask;
import com.roadtrack.onstar.async_tasks.tasks.GetPaymentProcessTask;
import com.roadtrack.onstar.async_tasks.tasks.GetPaymentsHistoryTask;
import com.roadtrack.onstar.async_tasks.tasks.GetRenewalPlansTask;
import com.roadtrack.onstar.async_tasks.tasks.GetURLPaymentsHistoryTask;
import com.roadtrack.onstar.async_tasks.tasks.PaymentCard_Task;
import com.roadtrack.onstar.async_tasks.tasks_set.TaskSet;
import com.roadtrack.onstar.custom.ListButton;
import com.roadtrack.onstar.custom.adapter.ListButtonAdapter;
import com.roadtrack.onstar.entities.ActiveCallEntity;
import com.roadtrack.onstar.entities.MessagesDetail;
import com.roadtrack.onstar.fragments.GridHolderFragment;
import com.roadtrack.onstar.googleMaps.ActivityMapViewerG;
import com.roadtrack.onstar.googleMaps.BundleActivityToMap;
import com.roadtrack.onstar.googleMaps.MapsFragment;
import com.roadtrack.onstar.interfaces.CloseFragment;
import com.roadtrack.onstar.interfaces.DevicesInterface;
import com.roadtrack.onstar.pid.PIDActivity;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import com.roadtrack.onstar.platinum.MultiModalHMI;
import com.roadtrack.onstar.servicios.PermissionsChecker;
import com.roadtrack.onstar.servicios.ServiceAlertsSocket;
import com.roadtrack.onstar.ui.dialogs.ActivityCall;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.ui.my_plan.MyPlan;
import com.roadtrack.onstar.ui.my_plan.OriginalRenewalActivity;
import com.roadtrack.onstar.ui.my_plan.PaymentCardInfo;
import com.roadtrack.onstar.ui.my_plan.PaymentHistoryActivity;
import com.roadtrack.onstar.ui.my_plan.RenewalMoipTicketWV;
import com.roadtrack.onstar.ui.my_plan.RenewalVehiclesListActivity;
import com.roadtrack.onstar.ui.settings.SettingsNewActivity;
import com.roadtrack.onstar.ui.wakeUpCar.WakeUpCar;
import com.roadtrack.onstar.ui.wakeUpCar.WatterMark;
import com.roadtrack.onstar.utils.Access;
import com.roadtrack.onstar.utils.AsyncTaskGetLastIncomingMessage;
import com.roadtrack.onstar.utils.BRInfo;
import com.roadtrack.onstar.utils.BroadcastReceiverLogOut;
import com.roadtrack.onstar.utils.CallPhone;
import com.roadtrack.onstar.utils.CallSOS;
import com.roadtrack.onstar.utils.DrawableUtils;
import com.roadtrack.onstar.utils.GetHexDumpMap;
import com.roadtrack.onstar.utils.MapUpdateHelper;
import com.roadtrack.onstar.utils.MapUtils;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.TcpClientV1;
import com.roadtrack.onstar.utils.UserInterfaceUtils;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import com.roadtrack.onstar.viewTutorial.ClassElements;
import com.roadtrack.onstar.viewTutorial.ShowViewElement;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class MainActivity extends FragmentActivity implements DialogAlert.DialogAlertListener, ScreenSlidePageFragment$ActionButtonsInterface, DialogMenu.DialogMenuListener, DialogMapSupportDownload.DialogMapSupportDownloadListener, DialogSpeed.DialogSpeedListener, GridHolderFragment.onCustomButonListener, CloseFragment {
  private static final String DATA_PATH;
  
  private static int ERROR_CHUNK;
  
  private static int LAST_SENT_CHUNK;
  
  public static View NotificationView;
  
  private static commonQueue OutQueue;
  
  private static final String PACKAGE_NAME;
  
  private static final String[] PACKAGE_NAMES;
  
  public static boolean Showbanner;
  
  private static ArrayList<Integer> UNCONFIRMED_SENT_CHUNKS;
  
  public static Handler UpdateMessages;
  
  public static Activity _act;
  
  public static int actAlertParking;
  
  public static int actAlertSpeed;
  
  public static int actAlertValet;
  
  private static ActiveCallEntity actCall;
  
  public static int actCloseDoors;
  
  public static int actDisarmPINCODE;
  
  public static int actFindMe;
  
  public static int actFollowMe;
  
  public static int actHorn;
  
  public static int actHornLights;
  
  public static int actLigths;
  
  public static int actOpenDoors;
  
  private static CustomActionButton actionButton;
  
  public static String activeFrag;
  
  public static boolean activityAlive;
  
  private static LinkedHashMap<String, LinkedList<CustomActionButton>> allBottomPanelButtons;
  
  public static BluetoothHeadset audioProfile;
  
  public static boolean bBotonIVRPulsado;
  
  public static boolean bEsperandoP8;
  
  public static boolean bRelayReady;
  
  private static LinkedList<CustomActionButton> bottomPanelButtons;
  
  public static Handler btHandlerChangeConection;
  
  public static LinkedList<CustomActionButton> buttonActions;
  
  private static CountDownTimer count;
  
  private static boolean executeGetNotificationsAfterLogin;
  
  public static boolean firstInWcf;
  
  public static FragmentManager fmff;
  
  private static int getNotificationsAfterLoginCounter;
  
  public static Boolean gmtDownload;
  
  public static int iPreviousCallIndex;
  
  public static Boolean isActionInProcessDialogShown;
  
  public static boolean isAttempToPayDialogShowed;
  
  private static boolean isInitiatingCall;
  
  public static boolean isRenewalpush;
  
  public static boolean isShowingOnFindMe;
  
  private static boolean isWaitingChunkError;
  
  private static UserDevicesVO lastDeviceInMain;
  
  public static String lastFollowMeIdMessage;
  
  public static List<GetCommandStatusVO> listCmdStat;
  
  public static BluetoothServiceRT mChatService;
  
  private static Context mContext;
  
  private static ManagerNotificationPlatinum mNPlatinum;
  
  private static StringBuffer mOutStringBuffer;
  
  public static Context mainContext;
  
  public static BluetoohListener myHeadsetListener;
  
  public static CustomActionButton notificationButton;
  
  private static UserDevicesVO oldDevice;
  
  public static Boolean onAlertParking;
  
  public static Boolean onAlertSpeed;
  
  public static Boolean onAlertSpeedClicked;
  
  public static Boolean onAlertValet;
  
  public static Boolean onCloseDoors;
  
  public static Boolean onCloseDoorsClicked;
  
  public static Boolean onDTC;
  
  public static Boolean onDisarmPINCODE;
  
  public static Boolean onDisarmPINCODEClicked;
  
  public static Boolean onFindMe;
  
  public static Boolean onFollowMe;
  
  public static Boolean onHorn;
  
  public static Boolean onHornClicked;
  
  public static Boolean onHornLights;
  
  public static Boolean onHornLightsClicked;
  
  public static Boolean onLigths;
  
  private static Boolean onNavigation;
  
  public static Boolean onNotification;
  
  public static Boolean onOpenDoors;
  
  public static Boolean onOpenDoorsClicked;
  
  public static Boolean onPID;
  
  public static Boolean onRenewalDialog;
  
  public static HashMap<String, Dialog> pendingDialogs = new HashMap<String, Dialog>();
  
  public static String pushNotificationDeviceId;
  
  private static boolean pushversionactived;
  
  public static boolean showRenewalDialog;
  
  private static boolean stateTutorial;
  
  public static TaskSet taskSet;
  
  private String DRAWER_STATE_OPEN = "drawer_state";
  
  ProgressBar DisarmgenericProgressBar;
  
  View DisarmgenericView;
  
  String DoorsLockgenericActionName = "DoorsLock";
  
  ProgressBar DoorsLockgenericProgressBar;
  
  View DoorsLockgenericView;
  
  String DoorsUnlockgenericActionName = "DoorsUnlock";
  
  ProgressBar DoorsUnlockgenericProgressBar;
  
  View DoorsUnlockgenericView;
  
  ProgressBar FindmegenericProgressBar;
  
  View FindmegenericView;
  
  String FollowMegenericActionName = "FollowMe";
  
  ProgressBar FollowmegenericProgressBar;
  
  View FollowmegenericView;
  
  String HornLigthsgenericActionName = "HornLigths";
  
  ProgressBar HornLigthsgenericProgressBar;
  
  View HornLigthsgenericView;
  
  ProgressBar HorngenericProgressBar;
  
  View HorngenericView;
  
  ImageView ImageResponseFindme;
  
  private LinearLayout LaySpeed;
  
  String LigthsgenericActionName = "Ligths";
  
  ProgressBar LigthsgenericProgressBar;
  
  View LigthsgenericView;
  
  private int NUM_PAGES = 2;
  
  TypedArray NavIcons;
  
  ArrayList<item_objct> NavItems;
  
  @SuppressLint({"HandlerLeak"})
  public Handler NewMessageHandler;
  
  ProgressBar NotificationsProgressBar;
  
  ProgressBar PIDgenericProgressBar;
  
  String ParkinggenericActionName = "Parking";
  
  ProgressBar ParkinggenericProgressBar;
  
  View ParkinggenericView;
  
  private int REQUEST_CODE;
  
  private String SPEED_KEY = "speed";
  
  private String STATE_TUTORIAL;
  
  private String STATE_TUTORIAL_MENU;
  
  String SpeedgenericActionName = "Speed";
  
  ProgressBar SpeedgenericProgressBar;
  
  View SpeedgenericView;
  
  private Handler UpdateMenuLeftBTHandler;
  
  private boolean _bContieneWayPoints = false;
  
  String accountId;
  
  private Activity act;
  
  private CustomActionButton actionButtonL;
  
  String actionCode;
  
  private TextView action_txtSpeedValue;
  
  private Button actions_btnActive;
  
  private Button actions_btnadd;
  
  private Button actions_btnsubs;
  
  private ArrayList<PushAlertsVO> agendamientoAlerts;
  
  private AnalyticsHelper analyticsHelper;
  
  String applicationSourceId;
  
  private List<VehicleCatalogVO> arraylistVehiclesAlmostExpired;
  
  private List<VehicleCatalogVO> arraylistVehiclesExpired;
  
  private List<VehicleCatalogVO> arraylistVehiclesNormal;
  
  private File assembled;
  
  private AsyncTaskUnregisterDevice async;
  
  private String aux_result = "";
  
  private boolean bEsperandoComando = false;
  
  private BroadcastReceiver broadcastReceiverAlertsNotifications;
  
  private Button btnNOK;
  
  private Button btnOK;
  
  private NotificationCompat.Builder builderMapUpdate;
  
  private ImageButton burgerDrawerButton;
  
  private CustomActionButton button;
  
  private CustomActionButton buttonUUX;
  
  private boolean cancelable = true;
  
  private CustomActionButton centerLeftButton;
  
  private CustomActionButton centerRightButton;
  
  private ClassElements classElements;
  
  public View.OnClickListener clickShowActivityDialog;
  
  public View.OnClickListener clickTutorial;
  
  public View.OnClickListener clickTutorialMenu;
  
  private CountDownTimer count1;
  
  private CountDownTimer count2;
  
  private CountDownTimer count3;
  
  private CountDownTimer countDownTimerFollowMe;
  
  String csvParams;
  
  private int currentAction;
  
  private Date dateInFutureForActions;
  
  private DBFunctions dbFun;
  
  String deviceCSVParams;
  
  String deviceId;
  
  private DialogAlert dialogAlert;
  
  private Dialog dialogDICAS;
  
  private DialogMenu dialogMenu;
  
  private boolean dialogMenuID;
  
  private DialogReciever dialogReciever;
  
  private DialogSpeed dialogSpeed;
  
  private int drawableId;
  
  private DrawerLayout drawerLayout;
  
  private LinkedList<ListButton> drawerMenuList;
  
  private ArrayList<PushAlertsVO> dtcAlerts;
  
  private TextView errorLogs;
  
  String filepathTBZ;
  
  private ProgressBar findMeProBar;
  
  private IntentFilter followmefinish;
  
  private BroadcastReceiver followmefinishmyReceiver;
  
  private RelativeLayout footer;
  
  private GetCommandStatusVO genericCmdStat;
  
  private ProgressBar genericProgress;
  
  private String genericSpeedValue;
  
  private View genericView;
  
  private GpsAsyncTask gpsAsync;
  
  private GPSTrackerListener gpstrack;
  
  private ImageView headerLogo;
  
  private int iContadorDeIntentos = 0;
  
  private int iContadorDeWayPoints = 0;
  
  private int iNumeroDeIntentos = 0;
  
  private int iSegundos = 0;
  
  private int iTotalWPMessages = 0;
  
  private int iWayPointsFaltantes = 0;
  
  private int id;
  
  private int idFromLastGetCommand;
  
  private long idInsertControl = -1L;
  
  private LinearLayout idTutorial;
  
  private LinearLayout idTutorialMenu;
  
  private ImageView imageView;
  
  private ImageView imgActionId;
  
  private TextView imgNotification;
  
  private ImageView imgPageLeft;
  
  private ImageView imgPageRight;
  
  private TextView imgResponse;
  
  private TextView imgResponseTemp;
  
  private boolean isFirstPackaget = true;
  
  private boolean isOpenMenu;
  
  private boolean isSpeedDialog = false;
  
  boolean isTryingToExecuteSameAction;
  
  private ImageView iv2PalomaAzulALertTip;
  
  private ImageView iv2PalomaAzulTip;
  
  private ImageView ivCarMovementTip;
  
  private TextView ivPID;
  
  private ImageView ivPalomaGrisTip;
  
  private ImageView ivProgressBarCircularTip;
  
  private ImageView ivTacheTip;
  
  private ImageView ivWaitNotificationTip;
  
  private ImageView iv_icon_wakeup_car;
  
  private long lIDFindMe = -1L;
  
  private TextView lbl_wakeup_car;
  
  private CustomActionButton leftButton;
  
  private ListButtonAdapter listButtonAdapter;
  
  private RelativeLayout llBtnShowDialog;
  
  private LinearLayout llCircles;
  
  private BroadcastReceiverLogOut logOutR;
  
  String login;
  
  private Activity mActivity = (Activity)this;
  
  private BluetoothAdapter mBluetoothAdapter;
  
  private BluetoothDevice mConnectedDeviceName;
  
  private Fragment mContent;
  
  private DrawerLayout mDrawerLayout;
  
  private ListView mDrawerList;
  
  private ActionBarDrawerToggle mDrawerToggle;
  
  @SuppressLint({"HandlerLeak"})
  private final Handler mHandler;
  
  private ManagerBluetooth mManagerBluetooth;
  
  private String[] mOptions;
  
  private ViewPager mPager;
  
  public PagerAdapter mPagerAdapter;
  
  BroadcastReceiver mReceiver;
  
  private BroadcastReceiver mReceiver2;
  
  private AsyncTask<Void, Void, Void> mRegisterTask;
  
  public SectionsPagerAdapter mSectionsPagerAdapter;
  
  public ViewPager mViewPager;
  
  private int mapFileSize;
  
  private MapUpdateHelper mapUpdateHelper;
  
  private MapUpdateVO mapUpdateVO;
  
  public Configuration myConfiguration;
  
  private NotificationReceiver nReceiver;
  
  private BroadcastReceiver networkChangeReceiver;
  
  private NotificationManager notificationManagerMapUpdate;
  
  private ArrayList<PushAlertsVO> objectAlert;
  
  boolean open;
  
  private boolean other;
  
  int p8PrimaVersion;
  
  public int pageGlobal;
  
  String password;
  
  private ProgressBar pb;
  
  private ProgressBar pbAction;
  
  private PowerManager pm;
  
  private int position;
  
  private String prefix = "";
  
  private boolean pressTuto;
  
  private TextView renewaltext;
  
  public int resolution;
  
  private List<Object> responseGetCommandStatus;
  
  private int result_code = -1;
  
  private CustomActionButton rightButton;
  
  private LinearLayout rightRL;
  
  private ImageView rlContainerTriangle;
  
  private onstarApplication rtApp;
  
  private SendCommand sendCommand;
  
  private SendFindMeCommand sendFindMeCmd;
  
  String sessionKey;
  
  private ShowViewElement show;
  
  private int spinnerCount;
  
  private Spinner spinner_menu;
  
  private Date startActionTime;
  
  private boolean stateTutorialMenu;
  
  private String[] straUserData;
  
  private String stringId;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Timer t;
  
  private TimerTask task;
  
  private CustomActionButton tempButton;
  
  private CustomActionButton tempButtonUUx;
  
  private TextView textViewFollowMe;
  
  private int timeBetweenAttemptsResponseActions = 2500;
  
  private String titleId;
  
  private String token;
  
  int totalVehicleExpiredOrAlmost;
  
  private TextView tv2PalomaAzulALertTip;
  
  private TextView tv2PalomaAzulTip;
  
  private TextView tvCarMovementTip;
  
  private TextView tvDescTip;
  
  private TextView tvPalomaGrisTip;
  
  private TextView tvProgressBarCircularTip;
  
  private TextView tvTacheTip;
  
  private TextView tvTitleTip;
  
  private TextView tvWaitNotificationTip;
  
  private EditText txtSpeedValue;
  
  String userId;
  
  private UserPreferenceVO userPreference;
  
  private View v;
  
  ProgressBar valetgenericProgressBar;
  
  View valetgenericView;
  
  private VehicleCatalogVO vehicleCatalogVO;
  
  private View view;
  
  private View viewNotifications;
  
  private PowerManager.WakeLock wl;
  
  static {
    Boolean bool = Boolean.valueOf(false);
    PACKAGE_NAMES = MainActivity.class.getPackage().getName().toString().split("\\.");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(PACKAGE_NAMES[0]);
    stringBuilder.append(".");
    stringBuilder.append(PACKAGE_NAMES[1]);
    stringBuilder.append(".");
    stringBuilder.append(PACKAGE_NAMES[2]);
    PACKAGE_NAME = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(Environment.getExternalStorageDirectory());
    stringBuilder.append("/");
    stringBuilder.append("Android");
    stringBuilder.append("/");
    stringBuilder.append("data");
    stringBuilder.append("/");
    stringBuilder.append(PACKAGE_NAME);
    DATA_PATH = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(DATA_PATH);
    stringBuilder.append("/");
    stringBuilder.append("RoadTrack/map/osm");
    stringBuilder.toString();
    actOpenDoors = -1;
    actCloseDoors = -1;
    actAlertParking = -1;
    actLigths = -1;
    actHornLights = -1;
    actHorn = -1;
    actAlertSpeed = -1;
    actFollowMe = -1;
    actFindMe = -1;
    actAlertValet = -1;
    actDisarmPINCODE = -1;
    onOpenDoors = bool;
    onCloseDoors = bool;
    onAlertParking = bool;
    onLigths = bool;
    onHornLights = bool;
    onHorn = bool;
    onAlertSpeed = bool;
    onFollowMe = bool;
    onFindMe = bool;
    onAlertValet = bool;
    onDisarmPINCODE = bool;
    onNotification = bool;
    onPID = bool;
    onNavigation = bool;
    onDTC = bool;
    onRenewalDialog = bool;
    onOpenDoorsClicked = bool;
    onCloseDoorsClicked = bool;
    onAlertSpeedClicked = bool;
    onHornLightsClicked = bool;
    onHornClicked = bool;
    onDisarmPINCODEClicked = bool;
    isActionInProcessDialogShown = bool;
    gmtDownload = bool;
    bBotonIVRPulsado = false;
    bEsperandoP8 = false;
    bRelayReady = true;
    iPreviousCallIndex = 0;
    LAST_SENT_CHUNK = 0;
    ERROR_CHUNK = 0;
    isWaitingChunkError = false;
    UNCONFIRMED_SENT_CHUNKS = new ArrayList<Integer>();
    listCmdStat = new ArrayList<GetCommandStatusVO>();
    executeGetNotificationsAfterLogin = true;
    getNotificationsAfterLoginCounter = 0;
    showRenewalDialog = true;
    isRenewalpush = false;
    pushNotificationDeviceId = "";
    pushversionactived = false;
    isAttempToPayDialogShowed = false;
    new Handler() {
        public void handleMessage(Message param1Message) {
          boolean bool = param1Message.obj.toString().equals(Utilities.getLastKnownDeviceSelected((onstarApplication)GlobalMembers.contexGlobal, "MainActivity").getDeviceId());
          StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
          if (param1Message.arg1 == -1 && bool) {
            String str = Utilities.getStringFromConfigList(MainActivity.mContext, stringsResourcesVO.main_activity_tracking_update, 2131690063);
            Toast.makeText(MainActivity.mContext, str, 1).show();
          } 
        }
      };
    mChatService = null;
    mNPlatinum = null;
    isInitiatingCall = false;
    actCall = null;
    activeFrag = "";
    btHandlerChangeConection = new Handler() {
        public void handleMessage(Message param1Message) {
          // Byte code:
          //   0: aload_0
          //   1: monitorenter
          //   2: ldc ''
          //   4: astore_3
          //   5: aload_1
          //   6: ifnull -> 17
          //   9: aload_1
          //   10: getfield obj : Ljava/lang/Object;
          //   13: checkcast java/lang/String
          //   16: astore_3
          //   17: aload_3
          //   18: ldc 'setinBackgroud'
          //   20: invokevirtual equals : (Ljava/lang/Object;)Z
          //   23: istore_2
          //   24: iload_2
          //   25: ifeq -> 166
          //   28: getstatic com/roadtrack/onstar/BO/GlobalMembers.globalActivity : Landroid/app/Activity;
          //   31: checkcast com/roadtrack/onstar/MainActivity
          //   34: astore_1
          //   35: getstatic com/roadtrack/onstar/BO/GlobalMembers.isMAPUpdateEnabled : Z
          //   38: ifne -> 47
          //   41: getstatic com/roadtrack/onstar/BO/GlobalMembers.isHMIEnabled : Z
          //   44: ifeq -> 51
          //   47: aload_1
          //   48: invokestatic access$6700 : (Lcom/roadtrack/onstar/MainActivity;)V
          //   51: getstatic com/roadtrack/onstar/BO/GlobalMembers.isAppRunning : Z
          //   54: ifne -> 209
          //   57: getstatic com/roadtrack/onstar/Enums$SettingsPreference.openAppwBT : Lcom/roadtrack/onstar/Enums$SettingsPreference;
          //   60: getstatic com/roadtrack/onstar/BO/GlobalMembers.NoneBoolean : Ljava/lang/Boolean;
          //   63: getstatic com/roadtrack/onstar/BO/GlobalMembers.globalActivity : Landroid/app/Activity;
          //   66: invokestatic GetValuePreference : (Lcom/roadtrack/onstar/Enums$SettingsPreference;Ljava/lang/Object;Landroid/content/Context;)Ljava/lang/Object;
          //   69: checkcast java/lang/Boolean
          //   72: invokevirtual booleanValue : ()Z
          //   75: ifeq -> 209
          //   78: aload_1
          //   79: invokevirtual onAlertClick : ()V
          //   82: goto -> 209
          //   85: astore_1
          //   86: ldc 'MainActivity'
          //   88: ldc 'Error: handleMessage'
          //   90: aload_1
          //   91: invokevirtual getMessage : ()Ljava/lang/String;
          //   94: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   97: getstatic com/roadtrack/onstar/BO/GlobalMembers.isAppRunning : Z
          //   100: ifne -> 209
          //   103: getstatic com/roadtrack/onstar/Enums$SettingsPreference.openAppwBT : Lcom/roadtrack/onstar/Enums$SettingsPreference;
          //   106: getstatic com/roadtrack/onstar/BO/GlobalMembers.NoneBoolean : Ljava/lang/Boolean;
          //   109: getstatic com/roadtrack/onstar/BO/GlobalMembers.globalActivity : Landroid/app/Activity;
          //   112: invokestatic GetValuePreference : (Lcom/roadtrack/onstar/Enums$SettingsPreference;Ljava/lang/Object;Landroid/content/Context;)Ljava/lang/Object;
          //   115: checkcast java/lang/Boolean
          //   118: invokevirtual booleanValue : ()Z
          //   121: ifeq -> 209
          //   124: iconst_1
          //   125: invokestatic setLauncherBT : (Z)V
          //   128: new android/content/Intent
          //   131: astore_1
          //   132: aload_1
          //   133: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
          //   136: ldc com/roadtrack/onstar/MainActivity
          //   138: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
          //   141: aload_1
          //   142: ldc 268435456
          //   144: invokevirtual addFlags : (I)Landroid/content/Intent;
          //   147: pop
          //   148: aload_1
          //   149: ldc 'IsFromBackgroud'
          //   151: iconst_1
          //   152: invokevirtual putExtra : (Ljava/lang/String;Z)Landroid/content/Intent;
          //   155: pop
          //   156: getstatic com/roadtrack/onstar/BO/GlobalMembers.globalActivity : Landroid/app/Activity;
          //   159: aload_1
          //   160: invokevirtual startActivity : (Landroid/content/Intent;)V
          //   163: goto -> 209
          //   166: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
          //   169: checkcast com/roadtrack/onstar/MainActivity
          //   172: invokestatic access$6700 : (Lcom/roadtrack/onstar/MainActivity;)V
          //   175: goto -> 209
          //   178: astore_1
          //   179: ldc 'MainActivity'
          //   181: ldc 'Error: btHandlerChangeConection'
          //   183: aload_1
          //   184: invokevirtual getMessage : ()Ljava/lang/String;
          //   187: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   190: goto -> 209
          //   193: astore_1
          //   194: goto -> 212
          //   197: astore_1
          //   198: ldc 'MainActivity'
          //   200: ldc 'Error: btHandlerChangeConection '
          //   202: aload_1
          //   203: invokevirtual getMessage : ()Ljava/lang/String;
          //   206: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
          //   209: aload_0
          //   210: monitorexit
          //   211: return
          //   212: aload_0
          //   213: monitorexit
          //   214: aload_1
          //   215: athrow
          // Exception table:
          //   from	to	target	type
          //   9	17	197	java/lang/Exception
          //   9	17	193	finally
          //   17	24	197	java/lang/Exception
          //   17	24	193	finally
          //   28	47	85	java/lang/Exception
          //   28	47	193	finally
          //   47	51	85	java/lang/Exception
          //   47	51	193	finally
          //   51	82	85	java/lang/Exception
          //   51	82	193	finally
          //   86	163	197	java/lang/Exception
          //   86	163	193	finally
          //   166	175	178	java/lang/Exception
          //   166	175	193	finally
          //   179	190	197	java/lang/Exception
          //   179	190	193	finally
          //   198	209	193	finally
        }
      };
  }
  
  public MainActivity() {
    new Date();
    new Date();
    this.sessionKey = "";
    this.login = "";
    this.password = "";
    this.accountId = "";
    this.deviceId = "";
    this.actionCode = "";
    this.csvParams = "";
    this.userId = "";
    this.applicationSourceId = "13";
    this.deviceCSVParams = "";
    this.objectAlert = new ArrayList<PushAlertsVO>();
    this.dtcAlerts = new ArrayList<PushAlertsVO>();
    this.agendamientoAlerts = new ArrayList<PushAlertsVO>();
    this.stringsResourcesVO = null;
    this.STATE_TUTORIAL = "state_tutorial";
    this.STATE_TUTORIAL_MENU = "state_tutorial_menu";
    this.pressTuto = true;
    this.arraylistVehiclesAlmostExpired = new ArrayList<VehicleCatalogVO>();
    this.arraylistVehiclesExpired = new ArrayList<VehicleCatalogVO>();
    this.arraylistVehiclesNormal = new ArrayList<VehicleCatalogVO>();
    this.totalVehicleExpiredOrAlmost = 0;
    this.clickShowActivityDialog = new View.OnClickListener() {
        final MainActivity this$0;
        
        public void onClick(View param1View) {
          DBFunctions dBFunctions = MainActivity.this.dbFun;
          String str1 = MainActivity.this.rtApp.getAccountID().toString();
          String str2 = Utilities.getLastKnownDeviceSelected(MainActivity.this.rtApp, "MainActivity").getDeviceId();
          Boolean bool1 = Boolean.valueOf(true);
          boolean bool = dBFunctions.userDataTableHandler(str1, str2, "", true);
          Utilities.showTheftAutoBanner((TextView)MainActivity.this.findViewById(2131297163), (Context)MainActivity.this, bool);
          if (bool)
            return; 
          if (!MainActivity.onRenewalDialog.booleanValue()) {
            MainActivity.onRenewalDialog = bool1;
            if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue() && !MainActivity.onLigths.booleanValue() && !MainActivity.onHornLights.booleanValue() && !MainActivity.onCloseDoors.booleanValue() && !MainActivity.onOpenDoors.booleanValue() && !MainActivity.onDisarmPINCODE.booleanValue() && !MainActivity.onAlertParking.booleanValue() && !MainActivity.onAlertSpeed.booleanValue() && !MainActivity.onAlertValet.booleanValue() && !MainActivity.onNotification.booleanValue() && !MainActivity.onHorn.booleanValue()) {
              MainActivity mainActivity = MainActivity.this;
              if (mainActivity.totalVehicleExpiredOrAlmost > 1) {
                mainActivity.renewalVehiclesListFunction();
              } else if (!GlobalMembers.flagShowWebViews) {
                GlobalMembers.isActionRenewalFromMain = true;
                mainActivity.renewalPlanFunction();
              } else {
                mainActivity.paymentProcessFunction();
              } 
            } else {
              if (!MainActivity.isActionInProcessDialogShown.booleanValue()) {
                MainActivity.isActionInProcessDialogShown = bool1;
                MainActivity.this.noRepeatSameActionDialog();
              } 
              MainActivity.onRenewalDialog = Boolean.valueOf(false);
            } 
          } 
        }
      };
    this.p8PrimaVersion = 0;
    this.other = false;
    this.isTryingToExecuteSameAction = false;
    this.currentAction = 0;
    this.mBluetoothAdapter = null;
    this.NewMessageHandler = new Handler() {
        final MainActivity this$0;
        
        public void handleMessage(Message param1Message) {
          // Byte code:
          //   0: aload_0
          //   1: monitorenter
          //   2: aload_1
          //   3: ifnull -> 129
          //   6: aload_1
          //   7: getfield what : I
          //   10: getstatic com/roadtrack/onstar/Enums$WhatHandler.Platinum_OpCodes : Lcom/roadtrack/onstar/Enums$WhatHandler;
          //   13: invokevirtual ordinal : ()I
          //   16: if_icmpne -> 30
          //   19: aload_0
          //   20: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   23: aload_1
          //   24: invokestatic access$5900 : (Lcom/roadtrack/onstar/MainActivity;Landroid/os/Message;)V
          //   27: goto -> 129
          //   30: aload_1
          //   31: getfield what : I
          //   34: getstatic com/roadtrack/onstar/Enums$WhatHandler.ErrorChekSum : Lcom/roadtrack/onstar/Enums$WhatHandler;
          //   37: invokevirtual ordinal : ()I
          //   40: if_icmpne -> 59
          //   43: invokestatic access$6000 : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
          //   46: aload_1
          //   47: getfield arg1 : I
          //   50: invokestatic valueOf : (I)Ljava/lang/String;
          //   53: invokevirtual errorCheSum : (Ljava/lang/String;)V
          //   56: goto -> 129
          //   59: aload_1
          //   60: getfield what : I
          //   63: getstatic com/roadtrack/onstar/Enums$WhatHandler.SendMessagePlatinum : Lcom/roadtrack/onstar/Enums$WhatHandler;
          //   66: invokevirtual ordinal : ()I
          //   69: if_icmpne -> 92
          //   72: aload_1
          //   73: getfield obj : Ljava/lang/Object;
          //   76: ifnull -> 129
          //   79: aload_1
          //   80: getfield obj : Ljava/lang/Object;
          //   83: checkcast [Ljava/lang/Object;
          //   86: invokestatic sendMessagePlatinum : ([Ljava/lang/Object;)V
          //   89: goto -> 129
          //   92: aload_1
          //   93: getfield what : I
          //   96: getstatic com/roadtrack/onstar/Enums$WhatHandler.SendChunkToP8 : Lcom/roadtrack/onstar/Enums$WhatHandler;
          //   99: invokevirtual ordinal : ()I
          //   102: if_icmpne -> 129
          //   105: aload_1
          //   106: getfield obj : Ljava/lang/Object;
          //   109: checkcast [B
          //   112: astore_1
          //   113: aload_0
          //   114: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   117: aload_1
          //   118: invokestatic access$6100 : (Lcom/roadtrack/onstar/MainActivity;[B)V
          //   121: goto -> 129
          //   124: astore_1
          //   125: aload_0
          //   126: monitorexit
          //   127: aload_1
          //   128: athrow
          //   129: aload_0
          //   130: monitorexit
          //   131: return
          // Exception table:
          //   from	to	target	type
          //   6	27	124	finally
          //   30	56	124	finally
          //   59	89	124	finally
          //   92	121	124	finally
        }
      };
    this.mapFileSize = 0;
    this.id = 0;
    this.mHandler = new Handler() {
        final MainActivity this$0;
        
        public void handleMessage(Message param1Message) {
          /* monitor enter ThisExpression{InnerObjectType{ObjectType{com/roadtrack/onstar/MainActivity}.Lcom/roadtrack/onstar/MainActivity$47;}} */
          try {
            Enums$messageBT enums$messageBT = Enums$messageBT.GetValue(param1Message.what);
            if (!GlobalMembers.isHMIEnabled || !GlobalMembers.isMAPUpdateEnabled) {
              /* monitor exit ThisExpression{InnerObjectType{ObjectType{com/roadtrack/onstar/MainActivity}.Lcom/roadtrack/onstar/MainActivity$47;}} */
              return;
            } 
            int i = MainActivity.null.$SwitchMap$com$roadtrack$onstar$Enums$messageBT[enums$messageBT.ordinal()];
            if (i != 1) {
              if (i != 2)
                if (i != 3) {
                  if (i != 4 && i == 5 && Utilities.isValidDevice(MainActivity.this.mConnectedDeviceName)) {
                    String str = "";
                    if (param1Message.getData().getString("toast") != null)
                      str = param1Message.getData().getString("toast"); 
                    if (!str.equals(""))
                      Toast.makeText(MainActivity.this.getApplicationContext(), str, 1).show(); 
                    if (str.contains("reintento") && (GlobalMembers.isMAPUpdateEnabled || GlobalMembers.isHMIEnabled))
                      MainActivity.this.initBluetooth(); 
                  } 
                } else {
                  byte[] arrayOfByte = (byte[])param1Message.obj;
                  String str = "utf-8";
                  try {
                    if (GlobalMembers.unitTypePlatinum == Enums$UnitType.Platinum_7)
                      str = "Cp1252"; 
                    String str1 = new String();
                    this(arrayOfByte, str);
                    str = str1.trim();
                    MainActivity.this.mManagerBluetooth.aggregateData(str);
                  } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    Utilities.escribeArchivo("MainActivity", "Error: MESSAGE_READ", unsupportedEncodingException.getMessage());
                  } 
                }  
            } else {
              GlobalMembers.isConnectionBT = false;
              i = MainActivity.null.$SwitchMap$com$roadtrack$onstar$Enums$stateBT[Enums$stateBT.GetValue(((Message)unsupportedEncodingException).arg1).ordinal()];
              if (i != 1) {
                if (i != 2)
                  if (i != 3) {
                    if (i == 4) {
                      GlobalMembers.isConnectionBT = false;
                      Utilities.setPlatinumConnection(MainActivity.mContext, "", "");
                    } 
                  } else {
                    GlobalMembers.isConnectionBT = false;
                    Utilities.setPlatinumConnection(MainActivity.mContext, "", "");
                  }  
              } else {
                Utilities.setPlatinumConnection(MainActivity.mContext, MainActivity.this.mConnectedDeviceName.getName(), MainActivity.this.mConnectedDeviceName.getAddress());
                GlobalMembers.isConnectionBT = true;
                try {
                  MainActivity.mNPlatinum.OpenStream();
                } catch (Exception exception) {
                  Utilities.escribeArchivo("MainActivity", "Error: handleMessage", exception.getMessage());
                } 
              } 
            } 
          } catch (Exception exception) {
            Utilities.escribeArchivo("MainActivity", "Error: handleMessage_BT", exception.getMessage());
          } finally {}
          /* monitor exit ThisExpression{InnerObjectType{ObjectType{com/roadtrack/onstar/MainActivity}.Lcom/roadtrack/onstar/MainActivity$47;}} */
        }
      };
    this.mReceiver = new BroadcastReceiver() {
        final MainActivity this$0;
        
        public void onReceive(Context param1Context, Intent param1Intent) {
          if (param1Intent.getAction().equals("com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT")) {
            param1Intent.getExtras();
            String str1 = param1Intent.getStringExtra("Response");
            String str2 = param1Intent.getStringExtra("Response2");
            int j = Integer.parseInt(str1);
            int k = str2.equals("0");
            int i = MainActivity.iPreviousCallIndex;
            boolean bool = false;
            if (i != 0) {
              i = 1;
            } else {
              i = 0;
            } 
            if ((k & i) != 0)
              (new Thread(new Runnable() {
                    final MainActivity.null this$1;
                    
                    public void run() {
                      try {
                        Thread.sleep(5000L);
                      } catch (InterruptedException interruptedException) {
                        Utilities.escribeArchivo("MainActivity", "Error: Timer", interruptedException.toString());
                      } 
                      MainActivity.this.runOnUiThread(new Runnable(this) {
                            public void run() {
                              Thread thread = Thread.currentThread();
                              StringBuilder stringBuilder = new StringBuilder();
                              stringBuilder.append(null.class.getSimpleName());
                              stringBuilder.append(": ");
                              stringBuilder.append(Thread.currentThread().getName());
                              thread.setName(stringBuilder.toString());
                              MainActivity.bRelayReady = true;
                              GlobalMembers.bStartingCall = false;
                            }
                          });
                    }
                  })).start(); 
            MainActivity.iPreviousCallIndex = Integer.parseInt(str2);
            if (GlobalMembers.btnpress != 1)
              if (!GlobalMembers.bActivityCallRunning) {
                boolean bool1 = bool;
                if (MainActivity.iPreviousCallIndex != 0)
                  bool1 = true; 
                if (((GlobalMembers.bStartingCall ^ true) & bool1) != 0) {
                  GlobalMembers.bStartingCall = true;
                  Intent intent = new Intent((Context)MainActivity.this, ActivityCall.class);
                  intent.putExtra("Boton", "Llamada");
                  MainActivity.access$7002(true);
                  MainActivity.iPreviousCallIndex = 1;
                  MainActivity.this.startActivityForResult(intent, 123);
                } 
              } else {
                ActivityCall.sendCallStatusRecived(j, Integer.parseInt(str2));
              }  
          } 
        }
      };
    this.mReceiver2 = new BroadcastReceiver() {
        final MainActivity this$0;
        
        public void onReceive(Context param1Context, Intent param1Intent) {
          // Byte code:
          //   0: aload_2
          //   1: invokevirtual getAction : ()Ljava/lang/String;
          //   4: ldc 'com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT_2'
          //   6: invokevirtual equals : (Ljava/lang/Object;)Z
          //   9: ifeq -> 414
          //   12: aload_2
          //   13: ldc 'extra'
          //   15: invokevirtual getBundleExtra : (Ljava/lang/String;)Landroid/os/Bundle;
          //   18: astore #5
          //   20: aload #5
          //   22: ldc 'Command'
          //   24: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
          //   27: astore_2
          //   28: aload #5
          //   30: ldc 'Subcommand'
          //   32: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
          //   35: astore_1
          //   36: aload #5
          //   38: ldc 'ContieneWayPoints'
          //   40: iconst_0
          //   41: invokevirtual getBoolean : (Ljava/lang/String;Z)Z
          //   44: istore #4
          //   46: aload_2
          //   47: ldc '85'
          //   49: invokevirtual equals : (Ljava/lang/Object;)Z
          //   52: ifeq -> 65
          //   55: aload_0
          //   56: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   59: iload #4
          //   61: invokestatic access$7102 : (Lcom/roadtrack/onstar/MainActivity;Z)Z
          //   64: pop
          //   65: aload #5
          //   67: ldc 'Message'
          //   69: invokevirtual getStringArray : (Ljava/lang/String;)[Ljava/lang/String;
          //   72: pop
          //   73: aload_2
          //   74: invokestatic parseInt : (Ljava/lang/String;)I
          //   77: istore_3
          //   78: invokestatic values : ()[Lcom/roadtrack/onstar/Enums$V2PCommands;
          //   81: iload_3
          //   82: aaload
          //   83: astore_2
          //   84: getstatic com/roadtrack/onstar/MainActivity$92.$SwitchMap$com$roadtrack$onstar$Enums$V2PCommands : [I
          //   87: aload_2
          //   88: invokevirtual ordinal : ()I
          //   91: iaload
          //   92: tableswitch default -> 132, 1 -> 362, 2 -> 296, 3 -> 214, 4 -> 181, 5 -> 135, 6 -> 414
          //   132: goto -> 414
          //   135: iconst_1
          //   136: putstatic com/roadtrack/onstar/BO/GlobalMembers.iRutaRecibida : I
          //   139: aload_0
          //   140: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   143: iconst_0
          //   144: invokestatic access$8002 : (Lcom/roadtrack/onstar/MainActivity;Z)Z
          //   147: pop
          //   148: iconst_3
          //   149: anewarray java/lang/Object
          //   152: dup
          //   153: iconst_0
          //   154: bipush #6
          //   156: invokestatic valueOf : (I)Ljava/lang/Integer;
          //   159: aastore
          //   160: dup
          //   161: iconst_1
          //   162: bipush #85
          //   164: invokestatic valueOf : (I)Ljava/lang/Integer;
          //   167: aastore
          //   168: dup
          //   169: iconst_2
          //   170: iconst_0
          //   171: invokestatic valueOf : (I)Ljava/lang/Integer;
          //   174: aastore
          //   175: invokestatic sendMessagePlatinum : ([Ljava/lang/Object;)V
          //   178: goto -> 414
          //   181: iconst_3
          //   182: anewarray java/lang/Object
          //   185: dup
          //   186: iconst_0
          //   187: bipush #6
          //   189: invokestatic valueOf : (I)Ljava/lang/Integer;
          //   192: aastore
          //   193: dup
          //   194: iconst_1
          //   195: bipush #56
          //   197: invokestatic valueOf : (I)Ljava/lang/Integer;
          //   200: aastore
          //   201: dup
          //   202: iconst_2
          //   203: iconst_0
          //   204: invokestatic valueOf : (I)Ljava/lang/Integer;
          //   207: aastore
          //   208: invokestatic sendMessagePlatinum : ([Ljava/lang/Object;)V
          //   211: goto -> 414
          //   214: aload_0
          //   215: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   218: invokestatic access$7100 : (Lcom/roadtrack/onstar/MainActivity;)Z
          //   221: ifeq -> 414
          //   224: aload_0
          //   225: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   228: invokestatic access$7700 : (Lcom/roadtrack/onstar/MainActivity;)[Ljava/lang/String;
          //   231: astore_1
          //   232: aload_1
          //   233: ifnull -> 414
          //   236: aload_1
          //   237: invokestatic sendMessagePlatinum : ([Ljava/lang/Object;)V
          //   240: aload_1
          //   241: iconst_1
          //   242: aaload
          //   243: ldc '0'
          //   245: invokevirtual equals : (Ljava/lang/Object;)Z
          //   248: ifeq -> 414
          //   251: aload_0
          //   252: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   255: iconst_0
          //   256: invokestatic access$7102 : (Lcom/roadtrack/onstar/MainActivity;Z)Z
          //   259: pop
          //   260: aload_0
          //   261: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   264: iconst_0
          //   265: invokestatic access$7302 : (Lcom/roadtrack/onstar/MainActivity;I)I
          //   268: pop
          //   269: aload_0
          //   270: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   273: iconst_0
          //   274: invokestatic access$7802 : (Lcom/roadtrack/onstar/MainActivity;I)I
          //   277: pop
          //   278: aload_0
          //   279: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   282: iconst_0
          //   283: invokestatic access$7902 : (Lcom/roadtrack/onstar/MainActivity;I)I
          //   286: pop
          //   287: getstatic com/roadtrack/onstar/BO/GlobalMembers.vecWaypoints : Ljava/util/Vector;
          //   290: invokevirtual clear : ()V
          //   293: goto -> 414
          //   296: aload_1
          //   297: invokestatic parseInt : (Ljava/lang/String;)I
          //   300: istore_3
          //   301: invokestatic values : ()[Lcom/roadtrack/onstar/Enums$RoutingStatus;
          //   304: iload_3
          //   305: aaload
          //   306: astore_1
          //   307: getstatic com/roadtrack/onstar/MainActivity$92.$SwitchMap$com$roadtrack$onstar$Enums$RoutingStatus : [I
          //   310: aload_1
          //   311: invokevirtual ordinal : ()I
          //   314: iaload
          //   315: istore_3
          //   316: iload_3
          //   317: iconst_1
          //   318: if_icmpeq -> 414
          //   321: iload_3
          //   322: iconst_2
          //   323: if_icmpeq -> 334
          //   326: iload_3
          //   327: iconst_3
          //   328: if_icmpeq -> 414
          //   331: goto -> 414
          //   334: aload_0
          //   335: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   338: iconst_0
          //   339: invokestatic access$7402 : (Lcom/roadtrack/onstar/MainActivity;I)I
          //   342: pop
          //   343: aload_0
          //   344: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   347: iconst_3
          //   348: invokestatic access$7502 : (Lcom/roadtrack/onstar/MainActivity;I)I
          //   351: pop
          //   352: aload_0
          //   353: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   356: invokestatic access$7600 : (Lcom/roadtrack/onstar/MainActivity;)V
          //   359: goto -> 414
          //   362: aload_0
          //   363: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   366: invokestatic access$7200 : (Lcom/roadtrack/onstar/MainActivity;)[Ljava/lang/String;
          //   369: astore_1
          //   370: aload_0
          //   371: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   374: iconst_0
          //   375: invokestatic access$7302 : (Lcom/roadtrack/onstar/MainActivity;I)I
          //   378: pop
          //   379: aload_1
          //   380: ifnull -> 414
          //   383: iconst_1
          //   384: putstatic com/roadtrack/onstar/BO/GlobalMembers.bSeEnvioRutaAP8 : Z
          //   387: aload_1
          //   388: invokestatic sendMessagePlatinum : ([Ljava/lang/Object;)V
          //   391: iconst_3
          //   392: putstatic com/roadtrack/onstar/BO/GlobalMembers.iRutaRecibida : I
          //   395: aload_0
          //   396: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
          //   399: aload_1
          //   400: bipush #6
          //   402: aaload
          //   403: ldc '0'
          //   405: invokevirtual equals : (Ljava/lang/Object;)Z
          //   408: iconst_1
          //   409: ixor
          //   410: invokestatic access$7102 : (Lcom/roadtrack/onstar/MainActivity;Z)Z
          //   413: pop
          //   414: return
        }
      };
    this.networkChangeReceiver = new BroadcastReceiver() {
        final MainActivity this$0;
        
        public void onReceive(Context param1Context, Intent param1Intent) {
          TextView textView = (TextView)MainActivity.this.findViewById(2131297056);
          MainActivity mainActivity = MainActivity.this;
          Utilities.showNetworkServiceData(textView, (Context)mainActivity, new TextView[] { (TextView)mainActivity.findViewById(2131297057) });
        }
      };
    this.followmefinishmyReceiver = new BroadcastReceiver() {
        final MainActivity this$0;
        
        public void onReceive(Context param1Context, Intent param1Intent) {
          if (param1Intent.getExtras().getString("parameter").equals("show")) {
            MainActivity mainActivity1 = MainActivity.this;
            String str1 = Utilities.getStringFromConfigList((Context)mainActivity1, mainActivity1.stringsResourcesVO.global_lbl_siguemeterminado_1, 2131689934);
            MainActivity mainActivity2 = MainActivity.this;
            String str2 = Utilities.getStringFromConfigList((Context)mainActivity2, mainActivity2.stringsResourcesVO.global_lbl_accionsigueme_1, 2131689885);
            Utilities.getDrawableFromConfigList((Context)MainActivity.this, DrawableResourcesVO.ic_launcher2, 2131165484);
            MainActivity.this.createMenuDialog(true, (Drawable)null, str1, str2, false);
          } 
        }
      };
    this.broadcastReceiverAlertsNotifications = new BroadcastReceiver() {
        final MainActivity this$0;
        
        public void onReceive(Context param1Context, Intent param1Intent) {
          MainActivity.AlertOn();
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(MainActivity.this.rtApp.getDeviceTypeId());
          stringBuilder.append("_ID");
          PreferenceRT.SetValuePreference(stringBuilder.toString(), true, (Context)MainActivity.this);
        }
      };
    this.clickTutorial = new View.OnClickListener() {
        final MainActivity this$0;
        
        public void onClick(View param1View) {
          if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue() && !MainActivity.onLigths.booleanValue() && !MainActivity.onHornLights.booleanValue() && !MainActivity.onCloseDoors.booleanValue() && !MainActivity.onOpenDoors.booleanValue() && !MainActivity.onDisarmPINCODE.booleanValue() && !MainActivity.onAlertParking.booleanValue() && !MainActivity.onAlertSpeed.booleanValue() && !MainActivity.onAlertValet.booleanValue() && !MainActivity.onNotification.booleanValue() && !MainActivity.onHorn.booleanValue()) {
            if (MainActivity.this.pressTuto)
              MainActivity.this.setTuto(); 
          } else {
            if (!MainActivity.isActionInProcessDialogShown.booleanValue()) {
              MainActivity.isActionInProcessDialogShown = Boolean.valueOf(true);
              MainActivity.this.noRepeatSameActionDialog();
            } 
            MainActivity.isRenewalpush = false;
          } 
        }
      };
    this.clickTutorialMenu = new View.OnClickListener() {
        final MainActivity this$0;
        
        public void onClick(View param1View) {
          MainActivity.this.showTutorialMenu();
        }
      };
  }
  
  public static void AlertOn() {
    DBFunctions dBFunctions = new DBFunctions(GlobalMembers.ctxBase);
    onstarApplication onstarApplication1 = (onstarApplication)mContext;
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(onstarApplication1, "MainActivity");
    if (dBFunctions.userDataTableHandler(onstarApplication1.getAccountID().toString(), userDevicesVO.getDeviceId(), "", true))
      return; 
    if (GlobalMembers.notificationSpinner) {
      actionButton = notificationButton;
    } else {
      actionButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.ActionNotifications);
    } 
    if (actionButton != null)
      try {
        CustomActionButton customActionButton;
        GlobalMembers.totalNotificaciones = GlobalMembers.notificaciones + GlobalMembers.notificacionesDTC + GlobalMembers.notificacionesAgendamiento;
        int j = GlobalMembers.totalNotificaciones;
        int i = 50;
        if (j > 50) {
          j = 50;
        } else {
          j = GlobalMembers.totalNotificaciones;
        } 
        GlobalMembers.totalNotificaciones = j;
        if (GlobalMembers.totalNotificaciones != 0) {
          CustomActionButton customActionButton1 = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.ActionNotifications);
          if (customActionButton1 != null)
            customActionButton1.showActionStatus(10); 
          notificationButton.showActionStatus(10);
        } 
        setBadge(mContext, GlobalMembers.totalNotificaciones);
        if (GlobalMembers.redPoint && userDevicesVO.getButtomActions().containsKey(Enums$Services.pid.GetCodeString())) {
          if (GlobalMembers.notificacionesDTC <= 50)
            i = GlobalMembers.notificacionesDTC; 
          GlobalMembers.notificacionesDTC = i;
          if ((GlobalMembers.notificacionesDTC != 0 || GlobalMembers.notificacionesAgendamiento != 0) && Boolean.parseBoolean(GlobalMembers.PIDButton)) {
            i = GlobalMembers.notificacionesDTC;
            i = GlobalMembers.notificacionesAgendamiento;
            customActionButton = UserInterfaceUtils.getActionButton(bottomPanelButtons, Enums$Services.pid);
            if (customActionButton != null)
              customActionButton.showActionStatus(8); 
          } 
        } else if (GlobalMembers.redPoint && customActionButton.getButtomActions().containsKey(Enums$Services.DTCAction.GetCodeString())) {
          if (GlobalMembers.notificacionesDTC <= 50)
            i = GlobalMembers.notificacionesDTC; 
          GlobalMembers.notificacionesDTC = i;
          if ((GlobalMembers.notificacionesDTC != 0 || GlobalMembers.notificacionesAgendamiento != 0) && Boolean.parseBoolean(GlobalMembers.PIDButton)) {
            i = GlobalMembers.notificacionesDTC;
            i = GlobalMembers.notificacionesAgendamiento;
            customActionButton = UserInterfaceUtils.getActionButton(bottomPanelButtons, Enums$Services.DTCAction);
            if (customActionButton != null)
              customActionButton.showActionStatus(8); 
          } 
        } 
      } catch (Exception exception) {
        Utilities.escribeArchivo("MainActivity", "redPoint:", exception.getMessage());
      }  
  }
  
  public static boolean CallOrHangUp(Enums$Calls paramEnums$Calls) {
    // Byte code:
    //   0: ldc com/roadtrack/onstar/MainActivity
    //   2: monitorenter
    //   3: getstatic com/roadtrack/onstar/MainActivity.isInitiatingCall : Z
    //   6: istore_3
    //   7: getstatic com/roadtrack/onstar/MainActivity.iPreviousCallIndex : I
    //   10: ifeq -> 18
    //   13: iconst_1
    //   14: istore_1
    //   15: goto -> 20
    //   18: iconst_0
    //   19: istore_1
    //   20: iload_3
    //   21: iload_1
    //   22: iand
    //   23: istore_2
    //   24: iload_2
    //   25: istore_1
    //   26: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   29: ifnull -> 227
    //   32: iload_2
    //   33: istore_1
    //   34: getstatic com/roadtrack/onstar/MainActivity$92.$SwitchMap$com$roadtrack$onstar$Enums$Calls : [I
    //   37: aload_0
    //   38: invokevirtual ordinal : ()I
    //   41: iaload
    //   42: tableswitch default -> 84, 1 -> 197, 2 -> 166, 3 -> 135, 4 -> 227, 5 -> 89, 6 -> 227, 7 -> 227
    //   84: iload_2
    //   85: istore_1
    //   86: goto -> 227
    //   89: iload_2
    //   90: istore_1
    //   91: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   94: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   97: getstatic com/roadtrack/onstar/Enums$CallNumberType.VR : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   100: if_acmpne -> 227
    //   103: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   106: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   109: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   112: if_acmpeq -> 127
    //   115: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   118: ldc_w 'RTMobile_HMI'
    //   121: invokestatic wakeDevice : (Landroid/content/Context;Ljava/lang/String;)V
    //   124: goto -> 225
    //   127: invokestatic stopWakeDevice : ()V
    //   130: iload_2
    //   131: istore_1
    //   132: goto -> 227
    //   135: iload_2
    //   136: istore_1
    //   137: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   140: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   143: getstatic com/roadtrack/onstar/Enums$CallNumberType.Emergency : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   146: if_acmpne -> 227
    //   149: iload_2
    //   150: istore_1
    //   151: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   154: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   157: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   160: if_acmpeq -> 227
    //   163: goto -> 225
    //   166: iload_2
    //   167: istore_1
    //   168: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   171: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   174: getstatic com/roadtrack/onstar/Enums$CallNumberType.Emergency : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   177: if_acmpne -> 227
    //   180: iload_2
    //   181: istore_1
    //   182: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   185: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   188: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   191: if_acmpeq -> 227
    //   194: goto -> 225
    //   197: iload_2
    //   198: istore_1
    //   199: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   202: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   205: getstatic com/roadtrack/onstar/Enums$CallNumberType.Assistance : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   208: if_acmpne -> 227
    //   211: iload_2
    //   212: istore_1
    //   213: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   216: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   219: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   222: if_acmpeq -> 227
    //   225: iconst_1
    //   226: istore_1
    //   227: iload_1
    //   228: ifeq -> 280
    //   231: new com/roadtrack/onstar/entities/ActiveCallEntity
    //   234: astore #4
    //   236: aload #4
    //   238: invokespecial <init> : ()V
    //   241: aload #4
    //   243: putstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   246: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   249: getstatic com/roadtrack/onstar/Enums$DeviceCall.BT : Lcom/roadtrack/onstar/Enums$DeviceCall;
    //   252: invokevirtual setDevice : (Lcom/roadtrack/onstar/Enums$DeviceCall;)V
    //   255: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   258: iconst_0
    //   259: invokevirtual setCallIndex : (I)V
    //   262: iconst_0
    //   263: putstatic com/roadtrack/onstar/MainActivity.isInitiatingCall : Z
    //   266: aload_0
    //   267: getstatic com/roadtrack/onstar/Enums$Calls.IVR : Lcom/roadtrack/onstar/Enums$Calls;
    //   270: if_acmpne -> 280
    //   273: invokestatic getInstance : ()Lcom/roadtrack/onstar/platinum/MultiModalHMI;
    //   276: iconst_2
    //   277: invokevirtual closeMenuHMI : (I)V
    //   280: getstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   283: ifnonnull -> 292
    //   286: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   289: putstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   292: iload_1
    //   293: ifeq -> 317
    //   296: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   299: getstatic com/roadtrack/onstar/Enums$ActionCall.Hang_Up : Lcom/roadtrack/onstar/Enums$ActionCall;
    //   302: invokevirtual setAction : (Lcom/roadtrack/onstar/Enums$ActionCall;)V
    //   305: getstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   308: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   311: invokevirtual callControl : (Lcom/roadtrack/onstar/entities/ActiveCallEntity;)V
    //   314: goto -> 386
    //   317: getstatic com/roadtrack/onstar/MainActivity.bBotonIVRPulsado : Z
    //   320: ifne -> 328
    //   323: iconst_1
    //   324: istore_1
    //   325: goto -> 330
    //   328: iconst_0
    //   329: istore_1
    //   330: getstatic com/roadtrack/onstar/MainActivity.iPreviousCallIndex : I
    //   333: ifne -> 341
    //   336: iconst_1
    //   337: istore_2
    //   338: goto -> 343
    //   341: iconst_0
    //   342: istore_2
    //   343: iload_1
    //   344: iload_2
    //   345: iand
    //   346: getstatic com/roadtrack/onstar/MainActivity.bRelayReady : Z
    //   349: iand
    //   350: ifeq -> 366
    //   353: getstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   356: aload_0
    //   357: ldc_w ''
    //   360: invokevirtual initiatingCall : (Lcom/roadtrack/onstar/Enums$Calls;Ljava/lang/String;)V
    //   363: goto -> 386
    //   366: getstatic com/roadtrack/onstar/MainActivity.bBotonIVRPulsado : Z
    //   369: ifeq -> 391
    //   372: iconst_0
    //   373: putstatic com/roadtrack/onstar/MainActivity.bBotonIVRPulsado : Z
    //   376: getstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   379: aload_0
    //   380: ldc_w ''
    //   383: invokevirtual initiatingCall : (Lcom/roadtrack/onstar/Enums$Calls;Ljava/lang/String;)V
    //   386: ldc com/roadtrack/onstar/MainActivity
    //   388: monitorexit
    //   389: iconst_1
    //   390: ireturn
    //   391: ldc com/roadtrack/onstar/MainActivity
    //   393: monitorexit
    //   394: iconst_0
    //   395: ireturn
    //   396: astore_0
    //   397: ldc com/roadtrack/onstar/MainActivity
    //   399: monitorexit
    //   400: aload_0
    //   401: athrow
    // Exception table:
    //   from	to	target	type
    //   3	13	396	finally
    //   26	32	396	finally
    //   34	84	396	finally
    //   91	124	396	finally
    //   127	130	396	finally
    //   137	149	396	finally
    //   151	163	396	finally
    //   168	180	396	finally
    //   182	194	396	finally
    //   199	211	396	finally
    //   213	225	396	finally
    //   231	280	396	finally
    //   280	292	396	finally
    //   296	314	396	finally
    //   317	323	396	finally
    //   330	336	396	finally
    //   343	363	396	finally
    //   366	386	396	finally
  }
  
  private void InitManagerBT() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mManagerBluetooth : Lcom/roadtrack/onstar/BO/ManagerBluetooth;
    //   6: ifnonnull -> 30
    //   9: new com/roadtrack/onstar/BO/ManagerBluetooth
    //   12: astore_1
    //   13: aload_1
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: aload_1
    //   19: putfield mManagerBluetooth : Lcom/roadtrack/onstar/BO/ManagerBluetooth;
    //   22: aload_0
    //   23: getfield mManagerBluetooth : Lcom/roadtrack/onstar/BO/ManagerBluetooth;
    //   26: aload_0
    //   27: invokevirtual initializeObjects : (Landroid/content/Context;)V
    //   30: aload_0
    //   31: getfield mManagerBluetooth : Lcom/roadtrack/onstar/BO/ManagerBluetooth;
    //   34: aload_0
    //   35: getfield NewMessageHandler : Landroid/os/Handler;
    //   38: putfield _handlerNewMessage : Landroid/os/Handler;
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	44	finally
    //   30	41	44	finally
  }
  
  private void InitializeEnvironment() {
    this.rtApp = (onstarApplication)getApplicationContext();
    GlobalMembers.globalActivity = (Activity)this;
    GlobalMembers.contexGlobal = mContext;
    GlobalMembers.currentActivity = "MainActivity";
    String str = GlobalMembers.userLogged;
    if (str == null || str.length() == 0)
      GlobalMembers.userLogged = PreferenceRT.GetValuePreference("user", "", (Context)this); 
    UtilitiesFile.isAppDirAvailable();
  }
  
  private void MenuLeftBTUpdate(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_1
    //   3: ifne -> 9
    //   6: aload_0
    //   7: monitorexit
    //   8: return
    //   9: new android/os/Message
    //   12: astore_2
    //   13: aload_2
    //   14: invokespecial <init> : ()V
    //   17: aload_2
    //   18: iload_1
    //   19: putfield arg1 : I
    //   22: iload_1
    //   23: getstatic com/roadtrack/onstar/Enums$CallNumberType.Assistance : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   26: invokevirtual GetOpCode : ()I
    //   29: if_icmpne -> 99
    //   32: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   35: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   38: getstatic com/roadtrack/onstar/Enums$CallState.Active : Lcom/roadtrack/onstar/Enums$CallState;
    //   41: if_acmpeq -> 91
    //   44: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   47: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   50: getstatic com/roadtrack/onstar/Enums$CallState.Alerting_Ringing : Lcom/roadtrack/onstar/Enums$CallState;
    //   53: if_acmpeq -> 91
    //   56: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   59: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   62: getstatic com/roadtrack/onstar/Enums$CallState.Dialing : Lcom/roadtrack/onstar/Enums$CallState;
    //   65: if_acmpne -> 71
    //   68: goto -> 91
    //   71: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   74: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   77: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   80: if_acmpne -> 253
    //   83: aload_2
    //   84: iconst_2
    //   85: putfield arg2 : I
    //   88: goto -> 253
    //   91: aload_2
    //   92: iconst_1
    //   93: putfield arg2 : I
    //   96: goto -> 253
    //   99: iload_1
    //   100: getstatic com/roadtrack/onstar/Enums$CallNumberType.Emergency : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   103: invokevirtual GetOpCode : ()I
    //   106: if_icmpne -> 176
    //   109: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   112: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   115: getstatic com/roadtrack/onstar/Enums$CallState.Active : Lcom/roadtrack/onstar/Enums$CallState;
    //   118: if_acmpeq -> 168
    //   121: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   124: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   127: getstatic com/roadtrack/onstar/Enums$CallState.Alerting_Ringing : Lcom/roadtrack/onstar/Enums$CallState;
    //   130: if_acmpeq -> 168
    //   133: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   136: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   139: getstatic com/roadtrack/onstar/Enums$CallState.Dialing : Lcom/roadtrack/onstar/Enums$CallState;
    //   142: if_acmpne -> 148
    //   145: goto -> 168
    //   148: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   151: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   154: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   157: if_acmpne -> 253
    //   160: aload_2
    //   161: iconst_2
    //   162: putfield arg2 : I
    //   165: goto -> 253
    //   168: aload_2
    //   169: iconst_1
    //   170: putfield arg2 : I
    //   173: goto -> 253
    //   176: iload_1
    //   177: getstatic com/roadtrack/onstar/Enums$CallNumberType.VR : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   180: invokevirtual GetOpCode : ()I
    //   183: if_icmpne -> 253
    //   186: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   189: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   192: getstatic com/roadtrack/onstar/Enums$CallState.Active : Lcom/roadtrack/onstar/Enums$CallState;
    //   195: if_acmpeq -> 248
    //   198: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   201: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   204: getstatic com/roadtrack/onstar/Enums$CallState.Alerting_Ringing : Lcom/roadtrack/onstar/Enums$CallState;
    //   207: if_acmpeq -> 248
    //   210: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   213: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   216: getstatic com/roadtrack/onstar/Enums$CallState.Dialing : Lcom/roadtrack/onstar/Enums$CallState;
    //   219: if_acmpne -> 225
    //   222: goto -> 248
    //   225: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   228: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   231: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   234: if_acmpne -> 253
    //   237: aload_2
    //   238: iconst_2
    //   239: putfield arg2 : I
    //   242: invokestatic stopWakeDevice : ()V
    //   245: goto -> 253
    //   248: aload_2
    //   249: iconst_1
    //   250: putfield arg2 : I
    //   253: iload_1
    //   254: getstatic com/roadtrack/onstar/Enums$CallNumberType.Emergency : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   257: invokevirtual GetOpCode : ()I
    //   260: if_icmpeq -> 273
    //   263: iload_1
    //   264: getstatic com/roadtrack/onstar/Enums$CallNumberType.Assistance : Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   267: invokevirtual GetOpCode : ()I
    //   270: if_icmpne -> 308
    //   273: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   276: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   279: getstatic com/roadtrack/onstar/Enums$CallState.Active : Lcom/roadtrack/onstar/Enums$CallState;
    //   282: if_acmpne -> 288
    //   285: goto -> 308
    //   288: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   291: invokevirtual getState : ()Lcom/roadtrack/onstar/Enums$CallState;
    //   294: getstatic com/roadtrack/onstar/Enums$CallState.Ended : Lcom/roadtrack/onstar/Enums$CallState;
    //   297: if_acmpne -> 308
    //   300: aconst_null
    //   301: putstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   304: iconst_0
    //   305: putstatic com/roadtrack/onstar/MainActivity.isInitiatingCall : Z
    //   308: aload_0
    //   309: getfield UpdateMenuLeftBTHandler : Landroid/os/Handler;
    //   312: ifnull -> 334
    //   315: aload_0
    //   316: getfield UpdateMenuLeftBTHandler : Landroid/os/Handler;
    //   319: aload_2
    //   320: invokevirtual sendMessage : (Landroid/os/Message;)Z
    //   323: pop
    //   324: aload_0
    //   325: getfield UpdateMenuLeftBTHandler : Landroid/os/Handler;
    //   328: invokevirtual obtainMessage : ()Landroid/os/Message;
    //   331: invokevirtual sendToTarget : ()V
    //   334: aload_0
    //   335: monitorexit
    //   336: return
    //   337: astore_2
    //   338: aload_0
    //   339: monitorexit
    //   340: aload_2
    //   341: athrow
    // Exception table:
    //   from	to	target	type
    //   9	68	337	finally
    //   71	88	337	finally
    //   91	96	337	finally
    //   99	145	337	finally
    //   148	165	337	finally
    //   168	173	337	finally
    //   176	222	337	finally
    //   225	245	337	finally
    //   248	253	337	finally
    //   253	273	337	finally
    //   273	285	337	finally
    //   288	308	337	finally
    //   308	334	337	finally
  }
  
  private void NewMessage_Inner(Message paramMessage) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: getfield obj : Ljava/lang/Object;
    //   6: checkcast [Ljava/lang/Object;
    //   9: astore #6
    //   11: new com/roadtrack/onstar/BO/MessagesObjects
    //   14: astore #7
    //   16: aload #7
    //   18: invokespecial <init> : ()V
    //   21: aload #7
    //   23: aload #6
    //   25: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   28: invokevirtual assembleOutMessage : ([Ljava/lang/Object;Landroid/content/Context;)Ljava/lang/String;
    //   31: pop
    //   32: aload_0
    //   33: aload_0
    //   34: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   37: getfield main_activity_map_update_status_message : Ljava/lang/String;
    //   40: ldc_w 2131690044
    //   43: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   46: astore #6
    //   48: aload_0
    //   49: aload_0
    //   50: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   53: getfield main_activity_map_update_transfer_ok : Ljava/lang/String;
    //   56: ldc_w 2131690056
    //   59: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   62: astore #8
    //   64: new com/roadtrack/onstar/BO/MessagesObjects
    //   67: astore #7
    //   69: aload #7
    //   71: invokespecial <init> : ()V
    //   74: aload #7
    //   76: aload_1
    //   77: aload_0
    //   78: invokevirtual getBaseContext : ()Landroid/content/Context;
    //   81: invokevirtual getMessage : (Landroid/os/Message;Landroid/content/Context;)Ljava/util/ArrayList;
    //   84: astore #7
    //   86: aload_1
    //   87: getfield arg1 : I
    //   90: invokestatic GetValue : (I)Lcom/roadtrack/onstar/BO/MessagesObjects$Platinum_OpCodes;
    //   93: astore_1
    //   94: getstatic com/roadtrack/onstar/MainActivity$92.$SwitchMap$com$roadtrack$onstar$BO$MessagesObjects$Platinum_OpCodes : [I
    //   97: aload_1
    //   98: invokevirtual ordinal : ()I
    //   101: iaload
    //   102: istore #4
    //   104: iconst_0
    //   105: istore_3
    //   106: iconst_0
    //   107: istore_2
    //   108: iload #4
    //   110: tableswitch default -> 300, 1 -> 2418, 2 -> 2381, 3 -> 2360, 4 -> 2351, 5 -> 2254, 6 -> 2112, 7 -> 2443, 8 -> 2443, 9 -> 2100, 10 -> 2443, 11 -> 2443, 12 -> 2443, 13 -> 2082, 14 -> 2068, 15 -> 2045, 16 -> 2443, 17 -> 2038, 18 -> 2443, 19 -> 2443, 20 -> 2443, 21 -> 2443, 22 -> 2443, 23 -> 2443, 24 -> 2443, 25 -> 2443, 26 -> 2030, 27 -> 2443, 28 -> 2443, 29 -> 2443, 30 -> 2443, 31 -> 2443, 32 -> 2443, 33 -> 2443, 34 -> 1976, 35 -> 1952, 36 -> 1939, 37 -> 1915, 38 -> 2443, 39 -> 1836, 40 -> 1738, 41 -> 1374, 42 -> 1182, 43 -> 303, 44 -> 2443
    //   300: goto -> 2443
    //   303: getstatic com/roadtrack/onstar/BO/GlobalMembers.updateStatusResponse : Ljava/lang/String;
    //   306: invokevirtual trim : ()Ljava/lang/String;
    //   309: invokestatic parseInt : (Ljava/lang/String;)I
    //   312: invokestatic getUpdateStatus : (I)Ljava/lang/String;
    //   315: astore_1
    //   316: aload_1
    //   317: ldc_w 'EOTARG_Starting_Ota'
    //   320: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   323: pop
    //   324: aload_1
    //   325: ldc_w 'EOTARG_Cancel_Ota'
    //   328: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   331: pop
    //   332: aload_1
    //   333: ldc_w 'EOTARG_Starting_Download'
    //   336: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   339: pop
    //   340: aload_1
    //   341: ldc_w 'EOTARG_Download_Complete'
    //   344: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   347: pop
    //   348: aload_1
    //   349: ldc_w 'EOTARG_Verify_Succeeded'
    //   352: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   355: pop
    //   356: aload_1
    //   357: ldc_w 'EOTARG_Verify_Failed'
    //   360: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   363: pop
    //   364: aload_1
    //   365: ldc_w 'EOTARG_Unpack_Succeeded'
    //   368: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   371: pop
    //   372: aload_1
    //   373: ldc_w 'EOTARG_Unpack_Failed'
    //   376: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   379: pop
    //   380: aload_1
    //   381: ldc_w 'EOTARG_SOC_Ready'
    //   384: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   387: pop
    //   388: aload_1
    //   389: ldc_w 'EOTARG_CRC_Passed'
    //   392: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   395: pop
    //   396: aload_1
    //   397: ldc_w 'EOTARG_CRC_Failed'
    //   400: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   403: pop
    //   404: aload_1
    //   405: ldc_w 'EOTARG_Requested_Confirmation'
    //   408: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   411: pop
    //   412: aload_1
    //   413: ldc_w 'EOTARG_Burn_Postponed'
    //   416: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   419: pop
    //   420: aload_1
    //   421: ldc_w 'EOTARG_Starting_Apply_Normal'
    //   424: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   427: pop
    //   428: aload_1
    //   429: ldc_w 'EOTARG_Starting_Apply_Recovery'
    //   432: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   435: pop
    //   436: aload_1
    //   437: ldc_w 'EOTARG_Starting_Apply_Resources'
    //   440: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   443: pop
    //   444: aload_1
    //   445: ldc_w 'EOTARG_MCU_Update_Available'
    //   448: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   451: pop
    //   452: aload_1
    //   453: ldc_w 'EOTARG_SOC_Update_Complete'
    //   456: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   459: ifeq -> 735
    //   462: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   465: invokevirtual RequestPlatinumVersions : ()V
    //   468: getstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_ON_PARTS : Ljava/lang/Boolean;
    //   471: invokevirtual booleanValue : ()Z
    //   474: ifeq -> 735
    //   477: aload_0
    //   478: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   481: invokevirtual getMapUpdateMapaPart : ()Lcom/roadtrack/onstar/VO/PushNotificationsVO;
    //   484: astore #7
    //   486: aload #7
    //   488: ifnull -> 512
    //   491: aload #7
    //   493: invokevirtual getOldVersion : ()Ljava/lang/String;
    //   496: invokestatic parseInt : (Ljava/lang/String;)I
    //   499: istore_3
    //   500: aload #7
    //   502: invokevirtual getNewVersion : ()Ljava/lang/String;
    //   505: invokestatic parseInt : (Ljava/lang/String;)I
    //   508: istore_2
    //   509: goto -> 516
    //   512: iconst_0
    //   513: istore_3
    //   514: iconst_0
    //   515: istore_2
    //   516: iload_2
    //   517: iload_3
    //   518: if_icmpne -> 688
    //   521: new java/lang/StringBuilder
    //   524: astore #8
    //   526: aload #8
    //   528: invokespecial <init> : ()V
    //   531: aload #8
    //   533: aload #7
    //   535: invokevirtual getFileName : ()Ljava/lang/String;
    //   538: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: pop
    //   542: aload #8
    //   544: ldc_w '.zip'
    //   547: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: pop
    //   551: aload #8
    //   553: invokevirtual toString : ()Ljava/lang/String;
    //   556: astore #8
    //   558: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   561: aload #8
    //   563: invokestatic deleteMapUpdateFile : (Landroid/content/Context;Ljava/lang/String;)V
    //   566: iconst_1
    //   567: istore_2
    //   568: iload_2
    //   569: aload #7
    //   571: invokevirtual getOldVersion : ()Ljava/lang/String;
    //   574: invokestatic parseInt : (Ljava/lang/String;)I
    //   577: if_icmpgt -> 647
    //   580: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   583: astore #9
    //   585: new java/lang/StringBuilder
    //   588: astore #8
    //   590: aload #8
    //   592: invokespecial <init> : ()V
    //   595: aload #8
    //   597: aload #7
    //   599: invokevirtual getFileName : ()Ljava/lang/String;
    //   602: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   605: pop
    //   606: aload #8
    //   608: ldc_w '_Part'
    //   611: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: pop
    //   615: aload #8
    //   617: iload_2
    //   618: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   621: pop
    //   622: aload #8
    //   624: ldc_w '.tbz'
    //   627: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: pop
    //   631: aload #9
    //   633: aload #8
    //   635: invokevirtual toString : ()Ljava/lang/String;
    //   638: invokestatic deleteMapUpdateFile : (Landroid/content/Context;Ljava/lang/String;)V
    //   641: iinc #2, 1
    //   644: goto -> 568
    //   647: aload_0
    //   648: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   651: invokevirtual deleteMapUpdateDataPart : ()V
    //   654: iconst_0
    //   655: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   658: putstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_ON_PARTS : Ljava/lang/Boolean;
    //   661: iconst_1
    //   662: putstatic com/roadtrack/onstar/BO/GlobalMembers.LASTFILE : Z
    //   665: aload_0
    //   666: getfield mapUpdateVO : Lcom/roadtrack/onstar/VO/MapUpdateVO;
    //   669: iconst_5
    //   670: invokevirtual setFileMapStatus : (I)V
    //   673: aload_0
    //   674: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   677: aload_0
    //   678: getfield mapUpdateVO : Lcom/roadtrack/onstar/VO/MapUpdateVO;
    //   681: invokevirtual updateMapUpdateData : (Lcom/roadtrack/onstar/VO/MapUpdateVO;)Z
    //   684: pop
    //   685: goto -> 735
    //   688: iload_2
    //   689: iload_3
    //   690: if_icmpge -> 735
    //   693: aload #7
    //   695: iload_2
    //   696: iconst_1
    //   697: iadd
    //   698: invokestatic valueOf : (I)Ljava/lang/String;
    //   701: invokevirtual setNewVersion : (Ljava/lang/String;)V
    //   704: aload_0
    //   705: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   708: aload #7
    //   710: invokevirtual updateMapUpdateDataPart : (Lcom/roadtrack/onstar/VO/PushNotificationsVO;)Z
    //   713: pop
    //   714: iconst_1
    //   715: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   718: putstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_ON_PARTS : Ljava/lang/Boolean;
    //   721: getstatic com/roadtrack/onstar/BO/GlobalMembers.myListener : Lcom/roadtrack/onstar/MainActivity$OnCustomEventListener;
    //   724: ifnull -> 735
    //   727: getstatic com/roadtrack/onstar/BO/GlobalMembers.myListener : Lcom/roadtrack/onstar/MainActivity$OnCustomEventListener;
    //   730: invokeinterface onEvent : ()V
    //   735: aload_1
    //   736: ldc_w 'EOTARG_Update_Failed'
    //   739: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   742: ifeq -> 781
    //   745: invokestatic isBtAndPlatinumConnected : ()Z
    //   748: ifeq -> 781
    //   751: aload_0
    //   752: aload_0
    //   753: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   756: getfield map_update_update_fail : Ljava/lang/String;
    //   759: ldc_w 2131690092
    //   762: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   765: astore #7
    //   767: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   770: aload #7
    //   772: invokevirtual sendTextForTTS : (Ljava/lang/String;)V
    //   775: aload_0
    //   776: aload #7
    //   778: invokestatic genericAlertDialogOk : (Landroid/content/Context;Ljava/lang/String;)V
    //   781: aload_1
    //   782: ldc_w 'EOTARG_Notified_Burn'
    //   785: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   788: pop
    //   789: aload_1
    //   790: ldc_w 'EOTARG_Set_Wakeup'
    //   793: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   796: pop
    //   797: aload_1
    //   798: ldc_w 'EOTARG_Retry_After_Fail'
    //   801: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   804: ifeq -> 980
    //   807: iconst_4
    //   808: anewarray java/lang/Object
    //   811: dup
    //   812: iconst_0
    //   813: bipush #60
    //   815: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   818: aastore
    //   819: dup
    //   820: iconst_1
    //   821: iconst_0
    //   822: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   825: aastore
    //   826: dup
    //   827: iconst_2
    //   828: iconst_0
    //   829: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   832: aastore
    //   833: dup
    //   834: iconst_3
    //   835: iconst_0
    //   836: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   839: aastore
    //   840: invokestatic sendMessagePlatinum : ([Ljava/lang/Object;)V
    //   843: aload_0
    //   844: aload_0
    //   845: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   848: getfield main_activity_map_update_error : Ljava/lang/String;
    //   851: ldc_w 2131690039
    //   854: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   857: astore #7
    //   859: aload_0
    //   860: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   863: aload #6
    //   865: invokevirtual setContentTitle : (Ljava/lang/CharSequence;)Landroidx/core/app/NotificationCompat$Builder;
    //   868: pop
    //   869: aload_0
    //   870: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   873: astore #6
    //   875: new java/lang/StringBuilder
    //   878: astore #8
    //   880: aload #8
    //   882: invokespecial <init> : ()V
    //   885: aload #8
    //   887: aload #7
    //   889: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   892: pop
    //   893: aload #8
    //   895: ldc_w ' '
    //   898: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   901: pop
    //   902: aload #8
    //   904: aload_1
    //   905: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   908: pop
    //   909: aload #6
    //   911: aload #8
    //   913: invokevirtual toString : ()Ljava/lang/String;
    //   916: invokevirtual setContentText : (Ljava/lang/CharSequence;)Landroidx/core/app/NotificationCompat$Builder;
    //   919: pop
    //   920: aload_0
    //   921: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   924: iconst_0
    //   925: iconst_0
    //   926: iconst_0
    //   927: invokevirtual setProgress : (IIZ)Landroidx/core/app/NotificationCompat$Builder;
    //   930: pop
    //   931: aload_0
    //   932: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   935: iconst_0
    //   936: invokevirtual setOngoing : (Z)Landroidx/core/app/NotificationCompat$Builder;
    //   939: pop
    //   940: aload_0
    //   941: getfield notificationManagerMapUpdate : Landroid/app/NotificationManager;
    //   944: ldc_w 123456789
    //   947: aload_0
    //   948: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   951: invokevirtual build : ()Landroid/app/Notification;
    //   954: invokevirtual notify : (ILandroid/app/Notification;)V
    //   957: new com/roadtrack/onstar/MainActivity$42
    //   960: astore #6
    //   962: aload #6
    //   964: aload_0
    //   965: ldc2_w 30000
    //   968: ldc2_w 1000
    //   971: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity;JJ)V
    //   974: aload #6
    //   976: invokevirtual start : ()Landroid/os/CountDownTimer;
    //   979: pop
    //   980: aload_1
    //   981: ldc_w 'EOTARG_Profile_Update'
    //   984: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   987: pop
    //   988: aload_1
    //   989: ldc_w 'EOTARG_User_Confirmed_Burn'
    //   992: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   995: pop
    //   996: aload_1
    //   997: ldc_w 'EOTARG_User_Postponed_Burn'
    //   1000: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1003: pop
    //   1004: aload_1
    //   1005: ldc_w 'EOTARG_MainBatteryDisconnected'
    //   1008: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1011: pop
    //   1012: aload_1
    //   1013: ldc_w 'EOTARG_RB_Memory_Fail'
    //   1016: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1019: pop
    //   1020: aload_1
    //   1021: ldc_w 'EOTARG_RB_UpdateFail'
    //   1024: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1027: ifeq -> 1107
    //   1030: iconst_4
    //   1031: anewarray java/lang/Object
    //   1034: dup
    //   1035: iconst_0
    //   1036: bipush #60
    //   1038: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1041: aastore
    //   1042: dup
    //   1043: iconst_1
    //   1044: iconst_0
    //   1045: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1048: aastore
    //   1049: dup
    //   1050: iconst_2
    //   1051: iconst_0
    //   1052: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1055: aastore
    //   1056: dup
    //   1057: iconst_3
    //   1058: iconst_0
    //   1059: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1062: aastore
    //   1063: invokestatic sendMessagePlatinum : ([Ljava/lang/Object;)V
    //   1066: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   1069: astore #7
    //   1071: aload_0
    //   1072: aload_0
    //   1073: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   1076: getfield map_update_fail : Ljava/lang/String;
    //   1079: ldc_w 2131690083
    //   1082: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1085: astore #6
    //   1087: aload #7
    //   1089: aload #6
    //   1091: invokevirtual sendTextForTTS : (Ljava/lang/String;)V
    //   1094: aload_0
    //   1095: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   1098: aload #6
    //   1100: iconst_1
    //   1101: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1104: invokevirtual show : ()V
    //   1107: aload_1
    //   1108: ldc_w 'EOTARG_Starting_Apply_Agent_Update'
    //   1111: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1114: pop
    //   1115: aload_1
    //   1116: ldc_w 'EOTARG_Starting_Apply_Resources_Delta'
    //   1119: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1122: pop
    //   1123: aload_1
    //   1124: ldc_w 'EOTARG_Starting_Apply_Maps_Delta'
    //   1127: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1130: pop
    //   1131: aload_1
    //   1132: ldc_w 'EOTARG_Starting_Validation_Test'
    //   1135: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1138: pop
    //   1139: aload_1
    //   1140: ldc_w 'EOTARG_Starting_Validation_Test_Failed'
    //   1143: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1146: pop
    //   1147: aload_1
    //   1148: ldc_w 'EOTARG_Verify_Signature'
    //   1151: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1154: pop
    //   1155: aload_1
    //   1156: ldc_w 'EOTARG_Decrypt_Package'
    //   1159: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1162: pop
    //   1163: aload_1
    //   1164: ldc_w 'EOTARG_PsPrima_Update'
    //   1167: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1170: pop
    //   1171: aload_1
    //   1172: ldc_w 'EOTARG_Modem_Fw_Update'
    //   1175: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1178: pop
    //   1179: goto -> 2443
    //   1182: aload_0
    //   1183: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   1186: aload #6
    //   1188: invokevirtual setContentTitle : (Ljava/lang/CharSequence;)Landroidx/core/app/NotificationCompat$Builder;
    //   1191: pop
    //   1192: aload_0
    //   1193: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   1196: aload #8
    //   1198: invokevirtual setContentText : (Ljava/lang/CharSequence;)Landroidx/core/app/NotificationCompat$Builder;
    //   1201: pop
    //   1202: aload_0
    //   1203: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   1206: iconst_0
    //   1207: iconst_0
    //   1208: iconst_0
    //   1209: invokevirtual setProgress : (IIZ)Landroidx/core/app/NotificationCompat$Builder;
    //   1212: pop
    //   1213: aload_0
    //   1214: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   1217: iconst_0
    //   1218: invokevirtual setOngoing : (Z)Landroidx/core/app/NotificationCompat$Builder;
    //   1221: pop
    //   1222: aload_0
    //   1223: getfield notificationManagerMapUpdate : Landroid/app/NotificationManager;
    //   1226: ldc_w 123456789
    //   1229: aload_0
    //   1230: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   1233: invokevirtual build : ()Landroid/app/Notification;
    //   1236: invokevirtual notify : (ILandroid/app/Notification;)V
    //   1239: invokestatic isBtAndPlatinumConnected : ()Z
    //   1242: ifeq -> 1283
    //   1245: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   1248: astore #6
    //   1250: aload_0
    //   1251: aload_0
    //   1252: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   1255: getfield map_update_transfer_map_done : Ljava/lang/String;
    //   1258: ldc_w 2131690089
    //   1261: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1264: astore_1
    //   1265: aload #6
    //   1267: aload_1
    //   1268: invokevirtual sendTextForTTS : (Ljava/lang/String;)V
    //   1271: aload_0
    //   1272: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   1275: aload_1
    //   1276: iconst_1
    //   1277: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1280: invokevirtual show : ()V
    //   1283: invokestatic isBtAndPlatinumConnected : ()Z
    //   1286: ifeq -> 1353
    //   1289: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   1292: astore #6
    //   1294: aload #6
    //   1296: aload_0
    //   1297: aload_0
    //   1298: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   1301: getfield main_activity_map_update_success_message : Ljava/lang/String;
    //   1304: ldc_w 2131690050
    //   1307: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1310: invokevirtual sendTextForTTS : (Ljava/lang/String;)V
    //   1313: aload_0
    //   1314: getstatic com/roadtrack/onstar/BO/GlobalMembers.downloadResponse : Ljava/lang/String;
    //   1317: invokespecial applyUpdate : (Ljava/lang/String;)V
    //   1320: aload_0
    //   1321: aload_0
    //   1322: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   1325: getfield map_update_apply : Ljava/lang/String;
    //   1328: ldc_w 2131690077
    //   1331: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1334: astore_1
    //   1335: aload #6
    //   1337: aload_1
    //   1338: invokevirtual sendTextForTTS : (Ljava/lang/String;)V
    //   1341: aload_0
    //   1342: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   1345: aload_1
    //   1346: iconst_1
    //   1347: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1350: invokevirtual show : ()V
    //   1353: aload_0
    //   1354: aload_0
    //   1355: aload_0
    //   1356: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   1359: getfield main_activity_map_update_success_message : Ljava/lang/String;
    //   1362: ldc_w 2131690050
    //   1365: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1368: invokestatic genericAlertDialogOk : (Landroid/content/Context;Ljava/lang/String;)V
    //   1371: goto -> 2443
    //   1374: aload #7
    //   1376: iconst_0
    //   1377: invokevirtual get : (I)Ljava/lang/Object;
    //   1380: checkcast java/util/Hashtable
    //   1383: ldc_w 'Message'
    //   1386: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1389: checkcast [Ljava/lang/String;
    //   1392: astore #7
    //   1394: aload #7
    //   1396: iconst_1
    //   1397: aaload
    //   1398: invokestatic parseInt : (Ljava/lang/String;)I
    //   1401: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1404: astore #6
    //   1406: aload #6
    //   1408: astore_1
    //   1409: aload #6
    //   1411: invokevirtual toString : ()Ljava/lang/String;
    //   1414: aload #6
    //   1416: invokevirtual toString : ()Ljava/lang/String;
    //   1419: invokevirtual length : ()I
    //   1422: iconst_1
    //   1423: isub
    //   1424: invokevirtual substring : (I)Ljava/lang/String;
    //   1427: ldc_w '1'
    //   1430: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1433: ifeq -> 1447
    //   1436: aload #6
    //   1438: invokevirtual intValue : ()I
    //   1441: iconst_1
    //   1442: iadd
    //   1443: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1446: astore_1
    //   1447: getstatic com/roadtrack/onstar/MainActivity.isWaitingChunkError : Z
    //   1450: ifeq -> 1595
    //   1453: aload_1
    //   1454: invokevirtual intValue : ()I
    //   1457: getstatic com/roadtrack/onstar/MainActivity.ERROR_CHUNK : I
    //   1460: if_icmpne -> 1595
    //   1463: iconst_0
    //   1464: putstatic com/roadtrack/onstar/MainActivity.isWaitingChunkError : Z
    //   1467: new java/util/ArrayList
    //   1470: astore #6
    //   1472: aload #6
    //   1474: invokespecial <init> : ()V
    //   1477: aload #6
    //   1479: putstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1482: aload #7
    //   1484: iconst_2
    //   1485: aaload
    //   1486: invokestatic parseInt : (Ljava/lang/String;)I
    //   1489: iconst_1
    //   1490: iadd
    //   1491: putstatic com/roadtrack/onstar/BO/GlobalMembers.SLIDING_WINDOW_SIZE : I
    //   1494: getstatic com/roadtrack/onstar/BO/GlobalMembers.SLIDING_WINDOW_SIZE : I
    //   1497: putstatic com/roadtrack/onstar/BO/GlobalMembers.AVAILABLE_WINDOW_SPACES : I
    //   1500: iconst_1
    //   1501: putstatic com/roadtrack/onstar/BO/GlobalMembers.SLIDING_WINDOW_PROCESS_ACTIVE : Z
    //   1504: aload_1
    //   1505: invokevirtual intValue : ()I
    //   1508: sipush #10000
    //   1511: iadd
    //   1512: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1515: astore_1
    //   1516: iload_2
    //   1517: getstatic com/roadtrack/onstar/BO/GlobalMembers.SLIDING_WINDOW_SIZE : I
    //   1520: if_icmpge -> 1578
    //   1523: aload_0
    //   1524: aload_1
    //   1525: invokevirtual intValue : ()I
    //   1528: invokespecial sendChunkToVehicle : (I)V
    //   1531: getstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1534: aload_1
    //   1535: invokevirtual intValue : ()I
    //   1538: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1541: invokevirtual add : (Ljava/lang/Object;)Z
    //   1544: pop
    //   1545: getstatic com/roadtrack/onstar/BO/GlobalMembers.AVAILABLE_WINDOW_SPACES : I
    //   1548: iconst_1
    //   1549: isub
    //   1550: putstatic com/roadtrack/onstar/BO/GlobalMembers.AVAILABLE_WINDOW_SPACES : I
    //   1553: aload_1
    //   1554: invokevirtual intValue : ()I
    //   1557: putstatic com/roadtrack/onstar/MainActivity.LAST_SENT_CHUNK : I
    //   1560: aload_1
    //   1561: invokevirtual intValue : ()I
    //   1564: sipush #10000
    //   1567: iadd
    //   1568: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1571: astore_1
    //   1572: iinc #2, 1
    //   1575: goto -> 1516
    //   1578: getstatic com/roadtrack/onstar/BO/GlobalMembers.ACK_COUNTER : I
    //   1581: iconst_1
    //   1582: iadd
    //   1583: putstatic com/roadtrack/onstar/BO/GlobalMembers.ACK_COUNTER : I
    //   1586: getstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1589: invokestatic sort : (Ljava/util/List;)V
    //   1592: goto -> 2443
    //   1595: getstatic com/roadtrack/onstar/MainActivity.isWaitingChunkError : Z
    //   1598: ifne -> 2443
    //   1601: getstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1604: invokestatic sort : (Ljava/util/List;)V
    //   1607: aload_1
    //   1608: invokevirtual intValue : ()I
    //   1611: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1614: astore_1
    //   1615: getstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1618: aload_1
    //   1619: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   1622: pop
    //   1623: aload_1
    //   1624: getstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1627: getstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1630: invokevirtual size : ()I
    //   1633: iconst_1
    //   1634: isub
    //   1635: invokevirtual get : (I)Ljava/lang/Object;
    //   1638: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1641: ifeq -> 2443
    //   1644: getstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1647: invokevirtual clear : ()V
    //   1650: getstatic com/roadtrack/onstar/BO/GlobalMembers.SLIDING_WINDOW_SIZE : I
    //   1653: putstatic com/roadtrack/onstar/BO/GlobalMembers.AVAILABLE_WINDOW_SPACES : I
    //   1656: getstatic com/roadtrack/onstar/BO/GlobalMembers.AVAILABLE_WINDOW_SPACES : I
    //   1659: ifle -> 2443
    //   1662: getstatic com/roadtrack/onstar/MainActivity.LAST_SENT_CHUNK : I
    //   1665: sipush #10000
    //   1668: iadd
    //   1669: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1672: astore_1
    //   1673: iload_3
    //   1674: istore_2
    //   1675: iload_2
    //   1676: getstatic com/roadtrack/onstar/BO/GlobalMembers.AVAILABLE_WINDOW_SPACES : I
    //   1679: if_icmpge -> 1729
    //   1682: aload_0
    //   1683: aload_1
    //   1684: invokevirtual intValue : ()I
    //   1687: invokespecial sendChunkToVehicle : (I)V
    //   1690: getstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1693: aload_1
    //   1694: invokevirtual intValue : ()I
    //   1697: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1700: invokevirtual add : (Ljava/lang/Object;)Z
    //   1703: pop
    //   1704: aload_1
    //   1705: invokevirtual intValue : ()I
    //   1708: putstatic com/roadtrack/onstar/MainActivity.LAST_SENT_CHUNK : I
    //   1711: aload_1
    //   1712: invokevirtual intValue : ()I
    //   1715: sipush #10000
    //   1718: iadd
    //   1719: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1722: astore_1
    //   1723: iinc #2, 1
    //   1726: goto -> 1675
    //   1729: getstatic com/roadtrack/onstar/MainActivity.UNCONFIRMED_SENT_CHUNKS : Ljava/util/ArrayList;
    //   1732: invokestatic sort : (Ljava/util/List;)V
    //   1735: goto -> 2443
    //   1738: aload #7
    //   1740: iconst_0
    //   1741: invokevirtual get : (I)Ljava/lang/Object;
    //   1744: checkcast java/util/Hashtable
    //   1747: ldc_w 'Message'
    //   1750: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1753: checkcast [Ljava/lang/String;
    //   1756: iconst_1
    //   1757: aaload
    //   1758: invokestatic parseInt : (Ljava/lang/String;)I
    //   1761: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1764: astore #6
    //   1766: aload #6
    //   1768: astore_1
    //   1769: aload #6
    //   1771: invokevirtual toString : ()Ljava/lang/String;
    //   1774: aload #6
    //   1776: invokevirtual toString : ()Ljava/lang/String;
    //   1779: invokevirtual length : ()I
    //   1782: iconst_1
    //   1783: isub
    //   1784: invokevirtual substring : (I)Ljava/lang/String;
    //   1787: ldc_w '1'
    //   1790: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1793: ifeq -> 1807
    //   1796: aload #6
    //   1798: invokevirtual intValue : ()I
    //   1801: iconst_1
    //   1802: iadd
    //   1803: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   1806: astore_1
    //   1807: aload_1
    //   1808: invokevirtual intValue : ()I
    //   1811: putstatic com/roadtrack/onstar/MainActivity.ERROR_CHUNK : I
    //   1814: aload_1
    //   1815: invokevirtual intValue : ()I
    //   1818: putstatic com/roadtrack/onstar/MainActivity.LAST_SENT_CHUNK : I
    //   1821: iconst_1
    //   1822: putstatic com/roadtrack/onstar/MainActivity.isWaitingChunkError : Z
    //   1825: aload_0
    //   1826: aload_1
    //   1827: invokevirtual intValue : ()I
    //   1830: invokespecial sendChunkToVehicle : (I)V
    //   1833: goto -> 2443
    //   1836: getstatic com/roadtrack/onstar/BO/GlobalMembers.platinumBTVersion : I
    //   1839: getstatic com/roadtrack/onstar/BO/GlobalMembers.BT_P8_GMLAN : I
    //   1842: if_icmpne -> 1889
    //   1845: invokestatic isBtAndPlatinumConnected : ()Z
    //   1848: ifeq -> 1889
    //   1851: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   1854: astore #6
    //   1856: aload_0
    //   1857: aload_0
    //   1858: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   1861: getfield map_update_message_p8i : Ljava/lang/String;
    //   1864: ldc_w 2131690085
    //   1867: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1870: astore_1
    //   1871: aload #6
    //   1873: aload_1
    //   1874: invokevirtual sendTextForTTS : (Ljava/lang/String;)V
    //   1877: aload_0
    //   1878: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   1881: aload_1
    //   1882: iconst_1
    //   1883: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1886: invokevirtual show : ()V
    //   1889: getstatic com/roadtrack/onstar/BO/GlobalMembers.platinumBTVersion : I
    //   1892: getstatic com/roadtrack/onstar/BO/GlobalMembers.BT_P8I_DISCRETE : I
    //   1895: if_icmpne -> 2443
    //   1898: getstatic com/roadtrack/onstar/BO/GlobalMembers.myListener : Lcom/roadtrack/onstar/MainActivity$OnCustomEventListener;
    //   1901: ifnull -> 2443
    //   1904: getstatic com/roadtrack/onstar/BO/GlobalMembers.myListener : Lcom/roadtrack/onstar/MainActivity$OnCustomEventListener;
    //   1907: invokeinterface onEvent : ()V
    //   1912: goto -> 2443
    //   1915: aload #7
    //   1917: iconst_0
    //   1918: invokevirtual get : (I)Ljava/lang/Object;
    //   1921: checkcast java/util/Hashtable
    //   1924: ldc_w 'Message'
    //   1927: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1930: checkcast com/roadtrack/onstar/VO/TbtDataVO
    //   1933: putstatic com/roadtrack/onstar/BO/GlobalMembers.tbtListData : Lcom/roadtrack/onstar/VO/TbtDataVO;
    //   1936: goto -> 2443
    //   1939: getstatic com/roadtrack/onstar/BO/GlobalMembers.isTbtWorking : Z
    //   1942: ifne -> 2443
    //   1945: iconst_1
    //   1946: putstatic com/roadtrack/onstar/BO/GlobalMembers.isTbtWorking : Z
    //   1949: goto -> 2443
    //   1952: aload #7
    //   1954: iconst_0
    //   1955: invokevirtual get : (I)Ljava/lang/Object;
    //   1958: checkcast java/util/Hashtable
    //   1961: ldc_w 'Message'
    //   1964: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1967: checkcast java/util/ArrayList
    //   1970: putstatic com/roadtrack/onstar/BO/GlobalMembers.tbtRoutePreviewInfo : Ljava/util/ArrayList;
    //   1973: goto -> 2443
    //   1976: aload #7
    //   1978: iconst_0
    //   1979: invokevirtual get : (I)Ljava/lang/Object;
    //   1982: checkcast java/util/Hashtable
    //   1985: ldc_w 'Message'
    //   1988: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   1991: checkcast com/roadtrack/onstar/VO/TbtDataVO
    //   1994: putstatic com/roadtrack/onstar/BO/GlobalMembers.tbtListData : Lcom/roadtrack/onstar/VO/TbtDataVO;
    //   1997: getstatic com/roadtrack/onstar/BO/GlobalMembers.tbtListData : Lcom/roadtrack/onstar/VO/TbtDataVO;
    //   2000: invokevirtual getCode : ()I
    //   2003: bipush #100
    //   2005: if_icmpne -> 2012
    //   2008: iconst_0
    //   2009: putstatic com/roadtrack/onstar/BO/GlobalMembers.isTbtWorking : Z
    //   2012: getstatic com/roadtrack/onstar/BO/GlobalMembers.tbtListData : Lcom/roadtrack/onstar/VO/TbtDataVO;
    //   2015: invokevirtual getCode : ()I
    //   2018: bipush #104
    //   2020: if_icmpne -> 2443
    //   2023: iconst_0
    //   2024: putstatic com/roadtrack/onstar/BO/GlobalMembers.isTbtWorking : Z
    //   2027: goto -> 2443
    //   2030: getstatic com/roadtrack/onstar/BO/GlobalMembers.isHMIEnabled : Z
    //   2033: istore #5
    //   2035: goto -> 2443
    //   2038: iconst_0
    //   2039: putstatic com/roadtrack/onstar/BO/GlobalMembers.btnpress : I
    //   2042: goto -> 2443
    //   2045: new com/roadtrack/onstar/BO/ManageFavorites
    //   2048: astore_1
    //   2049: aload_1
    //   2050: invokespecial <init> : ()V
    //   2053: aload_1
    //   2054: invokevirtual getFavoritesStringToSend : ()Ljava/lang/String;
    //   2057: astore_1
    //   2058: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   2061: aload_1
    //   2062: invokevirtual syncFavorite : (Ljava/lang/String;)V
    //   2065: goto -> 2443
    //   2068: invokestatic getInstance : ()Lcom/roadtrack/onstar/platinum/MultiModalHMI;
    //   2071: aload_0
    //   2072: getfield mContent : Landroidx/fragment/app/Fragment;
    //   2075: aload_0
    //   2076: invokevirtual cancelMenuHMI : (Landroidx/fragment/app/Fragment;Landroid/content/Context;)V
    //   2079: goto -> 2443
    //   2082: getstatic com/roadtrack/onstar/BO/GlobalMembers.isHMIEnabled : Z
    //   2085: ifeq -> 2443
    //   2088: invokestatic getInstance : ()Lcom/roadtrack/onstar/platinum/MultiModalHMI;
    //   2091: aload #7
    //   2093: aload_0
    //   2094: invokevirtual displayMenu : (Ljava/util/ArrayList;Landroid/content/Context;)V
    //   2097: goto -> 2443
    //   2100: getstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   2103: getstatic com/roadtrack/onstar/Enums$statusGeneric.Success : Lcom/roadtrack/onstar/Enums$statusGeneric;
    //   2106: invokevirtual cancelActiveRouteACK : (Lcom/roadtrack/onstar/Enums$statusGeneric;)V
    //   2109: goto -> 2443
    //   2112: new android/os/Message
    //   2115: astore_1
    //   2116: aload_1
    //   2117: invokespecial <init> : ()V
    //   2120: aload_1
    //   2121: getstatic com/roadtrack/onstar/Enums$WhatHandler.Device_OpCodes : Lcom/roadtrack/onstar/Enums$WhatHandler;
    //   2124: invokevirtual ordinal : ()I
    //   2127: putfield what : I
    //   2130: aload_1
    //   2131: getstatic com/roadtrack/onstar/BO/MessagesObjects$Device_OpCodes.OutboundMsg : Lcom/roadtrack/onstar/BO/MessagesObjects$Device_OpCodes;
    //   2134: invokevirtual GetOpCode : ()I
    //   2137: putfield arg1 : I
    //   2140: aload_1
    //   2141: aload #7
    //   2143: iconst_0
    //   2144: invokevirtual get : (I)Ljava/lang/Object;
    //   2147: checkcast java/util/Hashtable
    //   2150: ldc_w 'Message'
    //   2153: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   2156: checkcast [Ljava/lang/String;
    //   2159: iconst_0
    //   2160: aaload
    //   2161: putfield obj : Ljava/lang/Object;
    //   2164: getstatic com/roadtrack/onstar/BO/GlobalMembers.waitQueueResponceMessages : Lcom/roadtrack/onstar/VO/commonQueue;
    //   2167: invokevirtual count : ()I
    //   2170: ifle -> 2443
    //   2173: getstatic com/roadtrack/onstar/BO/GlobalMembers.waitQueueResponceMessages : Lcom/roadtrack/onstar/VO/commonQueue;
    //   2176: invokevirtual dequeue : ()Ljava/lang/Object;
    //   2179: checkcast java/util/Hashtable
    //   2182: invokevirtual keys : ()Ljava/util/Enumeration;
    //   2185: invokeinterface nextElement : ()Ljava/lang/Object;
    //   2190: checkcast java/lang/String
    //   2193: astore #6
    //   2195: aload #6
    //   2197: ldc_w 'Maintenance'
    //   2200: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2203: ifeq -> 2209
    //   2206: goto -> 2443
    //   2209: aload_0
    //   2210: invokespecial getFragmentActive : ()Ljava/lang/String;
    //   2213: ldc_w com/roadtrack/onstar/Messages
    //   2216: invokevirtual getName : ()Ljava/lang/String;
    //   2219: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2222: ifeq -> 2443
    //   2225: aload_1
    //   2226: aload #6
    //   2228: invokestatic parseInt : (Ljava/lang/String;)I
    //   2231: putfield arg2 : I
    //   2234: getstatic com/roadtrack/onstar/MainActivity.UpdateMessages : Landroid/os/Handler;
    //   2237: aload_1
    //   2238: invokevirtual sendMessage : (Landroid/os/Message;)Z
    //   2241: pop
    //   2242: getstatic com/roadtrack/onstar/MainActivity.UpdateMessages : Landroid/os/Handler;
    //   2245: invokevirtual obtainMessage : ()Landroid/os/Message;
    //   2248: invokevirtual sendToTarget : ()V
    //   2251: goto -> 2443
    //   2254: aload #7
    //   2256: invokevirtual size : ()I
    //   2259: ifle -> 2443
    //   2262: aload #7
    //   2264: iconst_0
    //   2265: invokevirtual get : (I)Ljava/lang/Object;
    //   2268: checkcast java/util/Hashtable
    //   2271: ldc_w 'Message'
    //   2274: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   2277: checkcast com/roadtrack/onstar/entities/MessagesDetail
    //   2280: astore_1
    //   2281: aload_1
    //   2282: invokevirtual getIdOrigin : ()Lcom/roadtrack/onstar/Enums$MessageOrigin;
    //   2285: getstatic com/roadtrack/onstar/Enums$MessageOrigin.TBD : Lcom/roadtrack/onstar/Enums$MessageOrigin;
    //   2288: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2291: ifeq -> 2330
    //   2294: getstatic com/roadtrack/onstar/BO/GlobalMembers.menuPlatinum : Lcom/roadtrack/onstar/entities/displaymenu;
    //   2297: aconst_null
    //   2298: invokevirtual setOptionsMenu : ([Ljava/lang/String;)V
    //   2301: getstatic com/roadtrack/onstar/BO/GlobalMembers.menuPlatinum : Lcom/roadtrack/onstar/entities/displaymenu;
    //   2304: aload_1
    //   2305: invokevirtual getDescripMessage : ()Ljava/lang/String;
    //   2308: invokevirtual setTitle : (Ljava/lang/String;)V
    //   2311: getstatic com/roadtrack/onstar/BO/GlobalMembers.menuPlatinum : Lcom/roadtrack/onstar/entities/displaymenu;
    //   2314: aload_1
    //   2315: invokevirtual getTypeGUI : ()Lcom/roadtrack/onstar/Enums$MessageType;
    //   2318: invokevirtual GetOpCode : ()I
    //   2321: invokestatic valueOf : (I)Ljava/lang/String;
    //   2324: invokevirtual setTypeControl : (Ljava/lang/String;)V
    //   2327: goto -> 2443
    //   2330: aload_0
    //   2331: aload #7
    //   2333: getstatic com/roadtrack/onstar/BO/MessagesObjects$Device_OpCodes.InboundMsg : Lcom/roadtrack/onstar/BO/MessagesObjects$Device_OpCodes;
    //   2336: invokevirtual GetOpCode : ()I
    //   2339: getstatic com/roadtrack/onstar/Enums$WhatHandler.Platinum_OpCodes : Lcom/roadtrack/onstar/Enums$WhatHandler;
    //   2342: invokevirtual ordinal : ()I
    //   2345: invokespecial addMessageComunicationFromCAC : (Ljava/util/ArrayList;II)V
    //   2348: goto -> 2443
    //   2351: getstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   2354: invokevirtual infoResponse : ()V
    //   2357: goto -> 2443
    //   2360: getstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   2363: invokevirtual identificationRequest : ()V
    //   2366: getstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   2369: invokevirtual RequestPlatinumVersions : ()V
    //   2372: getstatic com/roadtrack/onstar/MainActivity.mNPlatinum : Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   2375: invokevirtual requestP8Model : ()V
    //   2378: goto -> 2443
    //   2381: aload #7
    //   2383: iconst_0
    //   2384: invokevirtual get : (I)Ljava/lang/Object;
    //   2387: checkcast java/util/Hashtable
    //   2390: ldc_w 'Message'
    //   2393: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   2396: checkcast com/roadtrack/onstar/entities/ActiveCallEntity
    //   2399: putstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   2402: aload_0
    //   2403: getstatic com/roadtrack/onstar/MainActivity.actCall : Lcom/roadtrack/onstar/entities/ActiveCallEntity;
    //   2406: invokevirtual getNumberType : ()Lcom/roadtrack/onstar/Enums$CallNumberType;
    //   2409: invokevirtual GetOpCode : ()I
    //   2412: invokespecial MenuLeftBTUpdate : (I)V
    //   2415: goto -> 2443
    //   2418: iconst_1
    //   2419: putstatic com/roadtrack/onstar/MainActivity.isInitiatingCall : Z
    //   2422: goto -> 2443
    //   2425: astore_1
    //   2426: ldc_w 'MainActivity'
    //   2429: ldc_w 'Error: messageInner'
    //   2432: aload_1
    //   2433: invokevirtual getMessage : ()Ljava/lang/String;
    //   2436: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2439: aload_1
    //   2440: invokevirtual printStackTrace : ()V
    //   2443: aload_0
    //   2444: monitorexit
    //   2445: return
    //   2446: astore_1
    //   2447: aload_0
    //   2448: monitorexit
    //   2449: aload_1
    //   2450: athrow
    // Exception table:
    //   from	to	target	type
    //   2	64	2446	finally
    //   64	104	2425	java/lang/Exception
    //   64	104	2446	finally
    //   303	486	2425	java/lang/Exception
    //   303	486	2446	finally
    //   491	509	2425	java/lang/Exception
    //   491	509	2446	finally
    //   521	566	2425	java/lang/Exception
    //   521	566	2446	finally
    //   568	641	2425	java/lang/Exception
    //   568	641	2446	finally
    //   647	685	2425	java/lang/Exception
    //   647	685	2446	finally
    //   693	735	2425	java/lang/Exception
    //   693	735	2446	finally
    //   735	781	2425	java/lang/Exception
    //   735	781	2446	finally
    //   781	980	2425	java/lang/Exception
    //   781	980	2446	finally
    //   980	1107	2425	java/lang/Exception
    //   980	1107	2446	finally
    //   1107	1179	2425	java/lang/Exception
    //   1107	1179	2446	finally
    //   1182	1283	2425	java/lang/Exception
    //   1182	1283	2446	finally
    //   1283	1353	2425	java/lang/Exception
    //   1283	1353	2446	finally
    //   1353	1371	2425	java/lang/Exception
    //   1353	1371	2446	finally
    //   1374	1406	2425	java/lang/Exception
    //   1374	1406	2446	finally
    //   1409	1447	2425	java/lang/Exception
    //   1409	1447	2446	finally
    //   1447	1516	2425	java/lang/Exception
    //   1447	1516	2446	finally
    //   1516	1572	2425	java/lang/Exception
    //   1516	1572	2446	finally
    //   1578	1592	2425	java/lang/Exception
    //   1578	1592	2446	finally
    //   1595	1673	2425	java/lang/Exception
    //   1595	1673	2446	finally
    //   1675	1723	2425	java/lang/Exception
    //   1675	1723	2446	finally
    //   1729	1735	2425	java/lang/Exception
    //   1729	1735	2446	finally
    //   1738	1766	2425	java/lang/Exception
    //   1738	1766	2446	finally
    //   1769	1807	2425	java/lang/Exception
    //   1769	1807	2446	finally
    //   1807	1833	2425	java/lang/Exception
    //   1807	1833	2446	finally
    //   1836	1889	2425	java/lang/Exception
    //   1836	1889	2446	finally
    //   1889	1912	2425	java/lang/Exception
    //   1889	1912	2446	finally
    //   1915	1936	2425	java/lang/Exception
    //   1915	1936	2446	finally
    //   1939	1949	2425	java/lang/Exception
    //   1939	1949	2446	finally
    //   1952	1973	2425	java/lang/Exception
    //   1952	1973	2446	finally
    //   1976	2012	2425	java/lang/Exception
    //   1976	2012	2446	finally
    //   2012	2027	2425	java/lang/Exception
    //   2012	2027	2446	finally
    //   2030	2035	2425	java/lang/Exception
    //   2030	2035	2446	finally
    //   2038	2042	2425	java/lang/Exception
    //   2038	2042	2446	finally
    //   2045	2065	2425	java/lang/Exception
    //   2045	2065	2446	finally
    //   2068	2079	2425	java/lang/Exception
    //   2068	2079	2446	finally
    //   2082	2097	2425	java/lang/Exception
    //   2082	2097	2446	finally
    //   2100	2109	2425	java/lang/Exception
    //   2100	2109	2446	finally
    //   2112	2206	2425	java/lang/Exception
    //   2112	2206	2446	finally
    //   2209	2251	2425	java/lang/Exception
    //   2209	2251	2446	finally
    //   2254	2327	2425	java/lang/Exception
    //   2254	2327	2446	finally
    //   2330	2348	2425	java/lang/Exception
    //   2330	2348	2446	finally
    //   2351	2357	2425	java/lang/Exception
    //   2351	2357	2446	finally
    //   2360	2378	2425	java/lang/Exception
    //   2360	2378	2446	finally
    //   2381	2415	2425	java/lang/Exception
    //   2381	2415	2446	finally
    //   2418	2422	2425	java/lang/Exception
    //   2418	2422	2446	finally
    //   2426	2443	2446	finally
  }
  
  private void SendCommand85() {
    if (this.iContadorDeIntentos < this.iNumeroDeIntentos) {
      sendMessagePlatinum(new Object[] { Integer.valueOf(85), Integer.valueOf(0) });
      this.iSegundos = 3;
      (new Thread(new TimerThreadXseconds())).start();
    } 
    this.iContadorDeIntentos++;
  }
  
  private void SetNotificationButton() {
    notificationButton = (CustomActionButton)findViewById(2131296932);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_notifications_on_car_selector, 2131165497);
    notificationButton.set_drw_action_image(drawable);
    notificationButton.setHideLabel(true);
    final ProgressBar progressBell = (ProgressBar)notificationButton.findViewById(2131296940);
    notificationButton.setOnClickListener(new View.OnClickListener() {
          final MainActivity this$0;
          
          final ProgressBar val$progressBell;
          
          public void onClick(View param1View) {
            MainActivity.this.enterNotification(progressBell, param1View, MainActivity.notificationButton);
          }
        });
    if (!GlobalMembers.notificationSpinner)
      notificationButton.setVisibility(4); 
  }
  
  private void addMessageComunicationFromCAC(ArrayList<Hashtable<String, Object>> paramArrayList, int paramInt1, int paramInt2) {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
    try {
      MessagesDetail messagesDetail = (MessagesDetail)((Hashtable)paramArrayList.get(0)).get("Message");
      Message message = new Message();
      this();
      message.what = paramInt2;
      message.arg1 = paramInt1;
      message.obj = messagesDetail;
      MessagesManager messagesManager = new MessagesManager();
      this();
      messagesManager.saveChatMessages(messagesDetail);
      if (messagesDetail.getIdOrigin().equals(Enums$MessageOrigin.PLT_Application) || messagesDetail.getIdOrigin().equals(Enums$MessageOrigin.PLT_Alert)) {
        showAlertMessage(messagesDetail.getDescripMessage(), messagesDetail.getTextMessage());
      } else {
        Toast.makeText((Context)this.rtApp, messagesDetail.getTextMessage(), 1).show();
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: addMessageCommunication", exception.getMessage());
    } finally {}
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
  }
  
  private void adviceFunction() {
    toggleDrawerMenu();
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str4 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_lbl_consejosmayus_1, 2131689915);
    String str6 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_dicas_lbl_entenderconsejos_1, 2131689841);
    String str5 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.wizard_dicas_lbl_enviarcomando_3, 2131690515);
    String str1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.wizard_dicas_lbl_comandorecibido_4, 2131690514);
    String str3 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.wizard_dicas_lbl_comandoejecutado_5, 2131690513);
    String str7 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.wizard_dicas_lbl_alertaactivada_6, 2131690512);
    String str8 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.wakeupcar_estatus_error_header, 2131690496);
    String str9 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.wizard_dicas_lbl_vehiculomovimiento_8, 2131690517);
    String str2 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.wizard_dicas_lbl_aguardenotificacion_9, 2131690511);
    String str10 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.wakeupcar_estatusdicas, 2131690498);
    this.dialogDICAS.setContentView(2131427357);
    this.tvTitleTip = (TextView)this.dialogDICAS.findViewById(2131297155);
    this.tvTitleTip.setText(str4);
    this.tvDescTip = (TextView)this.dialogDICAS.findViewById(2131297146);
    this.tvDescTip.setText(str6);
    this.tvProgressBarCircularTip = (TextView)this.dialogDICAS.findViewById(2131297152);
    this.tvProgressBarCircularTip.setText(str5);
    this.lbl_wakeup_car = (TextView)this.dialogDICAS.findViewById(2131296789);
    this.lbl_wakeup_car.setText(str10);
    this.tvPalomaGrisTip = (TextView)this.dialogDICAS.findViewById(2131297151);
    this.tvPalomaGrisTip.setText(str1);
    this.tv2PalomaAzulTip = (TextView)this.dialogDICAS.findViewById(2131297139);
    this.tv2PalomaAzulTip.setText(str3);
    this.tv2PalomaAzulALertTip = (TextView)this.dialogDICAS.findViewById(2131297138);
    this.tv2PalomaAzulALertTip.setText(str7);
    this.tvTacheTip = (TextView)this.dialogDICAS.findViewById(2131297154);
    this.tvTacheTip.setText(str8);
    this.tvCarMovementTip = (TextView)this.dialogDICAS.findViewById(2131297141);
    this.tvCarMovementTip.setText(str9);
    this.tvWaitNotificationTip = (TextView)this.dialogDICAS.findViewById(2131297156);
    this.tvWaitNotificationTip.setText(str2);
    this.imageView = (ImageView)this.dialogDICAS.findViewById(2131296588);
    if (Utilities.isAndinos().booleanValue()) {
      LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.imageView.getLayoutParams();
      layoutParams.gravity = 5;
      this.imageView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    } 
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    this.imageView.setImageDrawable(DrawableUtils.resizeDrawable((Context)this, drawable, 360));
    this.ivProgressBarCircularTip = (ImageView)this.dialogDICAS.findViewById(2131296665);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.progressbarcircular, 2131165647);
    this.ivProgressBarCircularTip.setImageDrawable(drawable);
    this.iv_icon_wakeup_car = (ImageView)this.dialogDICAS.findViewById(2131296673);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_wake_up_car, 2131165282);
    this.iv_icon_wakeup_car.setImageDrawable(drawable);
    this.ivPalomaGrisTip = (ImageView)this.dialogDICAS.findViewById(2131296661);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_send_wait_azul_wizard, 2131165281);
    this.ivPalomaGrisTip.setImageDrawable(drawable);
    this.iv2PalomaAzulTip = (ImageView)this.dialogDICAS.findViewById(2131296659);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.palomita_2azul_executado_wizard, 2131165621);
    this.iv2PalomaAzulTip.setImageDrawable(drawable);
    this.iv2PalomaAzulALertTip = (ImageView)this.dialogDICAS.findViewById(2131296658);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_alert_activated_azul_wizard, 2131165272);
    this.iv2PalomaAzulALertTip.setImageDrawable(drawable);
    this.ivTacheTip = (ImageView)this.dialogDICAS.findViewById(2131296666);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.action_status_not_executed_azul_wizard, 2131165278);
    this.ivTacheTip.setImageDrawable(drawable);
    this.ivCarMovementTip = (ImageView)this.dialogDICAS.findViewById(2131296660);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.dicas_status_not_available, 2131165402);
    this.ivCarMovementTip.setImageDrawable(drawable);
    this.ivWaitNotificationTip = (ImageView)this.dialogDICAS.findViewById(2131296667);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.timeout_azul_wizard, 2131165688);
    this.ivWaitNotificationTip.setImageDrawable(drawable);
    this.dialogDICAS.show();
  }
  
  private void analyticsButtonBottomSelected(Enums$Services paramEnums$Services) {
    this.analyticsHelper.genericAnalyticsEventBottom((Context)this, paramEnums$Services);
  }
  
  private void analyticsButtonDrawerSelected(Enums$Services paramEnums$Services) {
    this.analyticsHelper.genericAnalyticsEventDrawer((Context)this, paramEnums$Services);
  }
  
  private void analyticsButtonSelected(Enums$Services paramEnums$Services) {
    this.analyticsHelper.genericAnalyticsEventButtonAction((Context)this, paramEnums$Services);
  }
  
  private void applyUpdate(String paramString) {
    GlobalMembers.isLastMapUpdateFile = paramString.equalsIgnoreCase(GlobalMembers.lastMapUpdateFile);
    getmNPlatinum().applyingUpdate(paramString);
  }
  
  private void asignImage(View paramView, int paramInt) {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Imagen: ");
    if (paramInt == 0) {
      str = "TACHE";
    } else {
      str = "PALOMA";
    } 
    stringBuilder.append(str);
    Utilities.escribeArchivo("ACTIONS", "asignImage", stringBuilder.toString());
    try {
      Drawable drawable;
      this.imgResponse = (TextView)paramView.findViewById(2131296611);
      this.imgResponse.setVisibility(0);
      if (paramInt != 0) {
        if (paramInt != 1) {
          paramView = null;
        } else {
          drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.palomita_2azul_executado_fondo, 2131165620);
        } 
      } else {
        drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.no_ejecutado_azul_fondo, 2131165581);
        str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_fallacomunicacion_7, 2131690516);
        Toast.makeText((Context)this.rtApp, str, 1).show();
      } 
      this.imgResponse.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
      Thread thread = new Thread();
      Runnable runnable = new Runnable() {
          final MainActivity this$0;
          
          public void run() {
            try {
              Thread.sleep(7000L);
            } catch (InterruptedException interruptedException) {
              Utilities.escribeArchivo("MainActivity", "Error: asignImage", interruptedException.toString());
            } 
            MainActivity.this.runOnUiThread(new Runnable() {
                  final MainActivity.null this$1;
                  
                  public void run() {
                    Thread thread = Thread.currentThread();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(null.class.getSimpleName());
                    stringBuilder.append(": ");
                    stringBuilder.append(Thread.currentThread().getName());
                    thread.setName(stringBuilder.toString());
                    MainActivity.this.imgResponse.setVisibility(4);
                  }
                });
          }
        };
      super(this);
      this(runnable);
      thread.start();
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: asignImage", exception.getMessage());
    } 
  }
  
  private void assistanceFunction() {
    if (!GlobalMembers.isShowingDialog)
      if (!GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
        String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
        String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
        String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_llamar_1, 2131689948);
        String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
        if (!isBtAndPlatinumConnected()) {
          Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_btnasistenciamenu_onstar_dialog, 2131165443);
          final Dialog dialog = Utilities.simpleDialog((Context)this, null, str4, str2, true, str1, false, str3);
          this.btnOK = (Button)dialog.findViewById(2131296343);
          this.btnNOK = (Button)dialog.findViewById(2131296344);
          this.btnOK.setOnClickListener(new View.OnClickListener() {
                final MainActivity this$0;
                
                final Dialog val$dialog;
                
                public void onClick(View param1View) {
                  MainActivity.this.llamadaAsistencia();
                  dialog.dismiss();
                }
              });
          this.btnNOK.setOnClickListener(new View.OnClickListener(this) {
                final Dialog val$dialog;
                
                public void onClick(View param1View) {
                  dialog.dismiss();
                }
              });
          dialog.setOnCancelListener(new DialogInterface.OnCancelListener(this) {
                public void onCancel(DialogInterface param1DialogInterface) {
                  GlobalMembers.isShowingDialog = false;
                  Utilities.escribeArchivo("MainActivity", "btn_assistance:", "canceled");
                  Intent intent = new Intent();
                  intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
                  GlobalMembers.contexGlobal.sendBroadcast(intent);
                }
              });
          dialog.show();
          GlobalMembers.isShowingDialog = true;
        } else if (((bEsperandoP8 ^ true) & bRelayReady) != 0) {
          llamadaAsistencia();
        } 
      } else {
        String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
        String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
        String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_llamar_1, 2131689948);
        String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
        Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_btnasistenciamenu_onstar_dialog, 2131165443);
        final Dialog dialog = Utilities.simpleDialog((Context)this, null, str1, str2, true, str3, false, str4);
        this.btnOK = (Button)dialog.findViewById(2131296343);
        this.btnNOK = (Button)dialog.findViewById(2131296344);
        this.btnOK.setOnClickListener(new View.OnClickListener() {
              final MainActivity this$0;
              
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                MainActivity.this.llamadaAsistencia();
                dialog.dismiss();
              }
            });
        this.btnNOK.setOnClickListener(new View.OnClickListener(this) {
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                dialog.dismiss();
              }
            });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener(this) {
              public void onCancel(DialogInterface param1DialogInterface) {
                GlobalMembers.isShowingDialog = false;
                Utilities.escribeArchivo("MainActivity", "btn_assistance:", "canceled");
                Intent intent = new Intent();
                intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
                GlobalMembers.contexGlobal.sendBroadcast(intent);
              }
            });
        dialog.show();
        GlobalMembers.isShowingDialog = true;
      }  
  }
  
  private String[] buildCommand85() {
    if (GlobalMembers.vecWaypoints.isEmpty()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(GlobalMembers.destinationFromMap.getLatitude());
      stringBuilder.append("");
      String str1 = stringBuilder.toString();
      stringBuilder = new StringBuilder();
      stringBuilder.append(GlobalMembers.destinationFromMap.getLongitude());
      stringBuilder.append("");
      return new String[] { "85", "1", str1, stringBuilder.toString(), GlobalMembers.strViaRoute, GlobalMembers.strETA, "0" };
    } 
    getTotalWPMessages();
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(GlobalMembers.destinationFromMap.getLatitude());
    stringBuilder1.append("");
    String str = stringBuilder1.toString();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(GlobalMembers.destinationFromMap.getLongitude());
    stringBuilder2.append("");
    return new String[] { "85", "1", str, stringBuilder2.toString(), GlobalMembers.strViaRoute, GlobalMembers.strETA, "1" };
  }
  
  private String[] buildCommand86() {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield iContadorDeWayPoints : I
    //   5: iconst_1
    //   6: iadd
    //   7: putfield iContadorDeWayPoints : I
    //   10: aload_0
    //   11: getfield iTotalWPMessages : I
    //   14: istore_2
    //   15: aload_0
    //   16: getfield iContadorDeWayPoints : I
    //   19: istore_1
    //   20: aload_0
    //   21: iload_2
    //   22: iload_1
    //   23: isub
    //   24: putfield iWayPointsFaltantes : I
    //   27: aload_0
    //   28: getfield iWayPointsFaltantes : I
    //   31: ifge -> 36
    //   34: aconst_null
    //   35: areturn
    //   36: iload_1
    //   37: iconst_5
    //   38: imul
    //   39: istore_1
    //   40: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   43: astore #4
    //   45: aload #4
    //   47: ifnull -> 660
    //   50: aload #4
    //   52: arraylength
    //   53: istore_2
    //   54: iload_1
    //   55: iconst_5
    //   56: isub
    //   57: istore_3
    //   58: iload_2
    //   59: iload_3
    //   60: if_icmple -> 660
    //   63: new java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial <init> : ()V
    //   70: astore #4
    //   72: aload #4
    //   74: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   77: iload_3
    //   78: aaload
    //   79: invokevirtual getLatitude : ()D
    //   82: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload #4
    //   88: ldc_w ''
    //   91: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: pop
    //   95: aload #4
    //   97: invokevirtual toString : ()Ljava/lang/String;
    //   100: invokevirtual isEmpty : ()Z
    //   103: ifne -> 162
    //   106: new java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial <init> : ()V
    //   113: astore #4
    //   115: aload #4
    //   117: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   120: iload_3
    //   121: aaload
    //   122: invokevirtual getLatitude : ()D
    //   125: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   128: pop
    //   129: aload #4
    //   131: ldc_w ''
    //   134: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: pop
    //   138: aload #4
    //   140: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   143: iload_3
    //   144: aaload
    //   145: invokevirtual getLongitude : ()D
    //   148: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: aload #4
    //   154: invokevirtual toString : ()Ljava/lang/String;
    //   157: astore #4
    //   159: goto -> 167
    //   162: ldc_w ''
    //   165: astore #4
    //   167: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   170: arraylength
    //   171: istore_2
    //   172: iload_1
    //   173: iconst_4
    //   174: isub
    //   175: istore_3
    //   176: iload_2
    //   177: iload_3
    //   178: if_icmple -> 657
    //   181: new java/lang/StringBuilder
    //   184: dup
    //   185: invokespecial <init> : ()V
    //   188: astore #5
    //   190: aload #5
    //   192: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   195: iload_3
    //   196: aaload
    //   197: invokevirtual getLatitude : ()D
    //   200: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   203: pop
    //   204: aload #5
    //   206: ldc_w ''
    //   209: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: pop
    //   213: aload #5
    //   215: invokevirtual toString : ()Ljava/lang/String;
    //   218: invokevirtual isEmpty : ()Z
    //   221: ifne -> 280
    //   224: new java/lang/StringBuilder
    //   227: dup
    //   228: invokespecial <init> : ()V
    //   231: astore #5
    //   233: aload #5
    //   235: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   238: iload_3
    //   239: aaload
    //   240: invokevirtual getLatitude : ()D
    //   243: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   246: pop
    //   247: aload #5
    //   249: ldc_w ''
    //   252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: pop
    //   256: aload #5
    //   258: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   261: iload_3
    //   262: aaload
    //   263: invokevirtual getLongitude : ()D
    //   266: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   269: pop
    //   270: aload #5
    //   272: invokevirtual toString : ()Ljava/lang/String;
    //   275: astore #5
    //   277: goto -> 285
    //   280: ldc_w ''
    //   283: astore #5
    //   285: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   288: arraylength
    //   289: istore_2
    //   290: iload_1
    //   291: iconst_3
    //   292: isub
    //   293: istore_3
    //   294: iload_2
    //   295: iload_3
    //   296: if_icmple -> 654
    //   299: new java/lang/StringBuilder
    //   302: dup
    //   303: invokespecial <init> : ()V
    //   306: astore #6
    //   308: aload #6
    //   310: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   313: iload_3
    //   314: aaload
    //   315: invokevirtual getLatitude : ()D
    //   318: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   321: pop
    //   322: aload #6
    //   324: ldc_w ''
    //   327: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: pop
    //   331: aload #6
    //   333: invokevirtual toString : ()Ljava/lang/String;
    //   336: invokevirtual isEmpty : ()Z
    //   339: ifne -> 398
    //   342: new java/lang/StringBuilder
    //   345: dup
    //   346: invokespecial <init> : ()V
    //   349: astore #6
    //   351: aload #6
    //   353: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   356: iload_3
    //   357: aaload
    //   358: invokevirtual getLatitude : ()D
    //   361: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   364: pop
    //   365: aload #6
    //   367: ldc_w ''
    //   370: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   373: pop
    //   374: aload #6
    //   376: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   379: iload_3
    //   380: aaload
    //   381: invokevirtual getLongitude : ()D
    //   384: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   387: pop
    //   388: aload #6
    //   390: invokevirtual toString : ()Ljava/lang/String;
    //   393: astore #7
    //   395: goto -> 403
    //   398: ldc_w ''
    //   401: astore #7
    //   403: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   406: arraylength
    //   407: istore_3
    //   408: iload_1
    //   409: iconst_2
    //   410: isub
    //   411: istore_2
    //   412: iload_3
    //   413: iload_2
    //   414: if_icmple -> 641
    //   417: new java/lang/StringBuilder
    //   420: dup
    //   421: invokespecial <init> : ()V
    //   424: astore #6
    //   426: aload #6
    //   428: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   431: iload_2
    //   432: aaload
    //   433: invokevirtual getLatitude : ()D
    //   436: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   439: pop
    //   440: aload #6
    //   442: ldc_w ''
    //   445: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: pop
    //   449: aload #6
    //   451: invokevirtual toString : ()Ljava/lang/String;
    //   454: invokevirtual isEmpty : ()Z
    //   457: ifne -> 516
    //   460: new java/lang/StringBuilder
    //   463: dup
    //   464: invokespecial <init> : ()V
    //   467: astore #6
    //   469: aload #6
    //   471: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   474: iload_2
    //   475: aaload
    //   476: invokevirtual getLatitude : ()D
    //   479: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   482: pop
    //   483: aload #6
    //   485: ldc_w ''
    //   488: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: pop
    //   492: aload #6
    //   494: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   497: iload_2
    //   498: aaload
    //   499: invokevirtual getLongitude : ()D
    //   502: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   505: pop
    //   506: aload #6
    //   508: invokevirtual toString : ()Ljava/lang/String;
    //   511: astore #8
    //   513: goto -> 521
    //   516: ldc_w ''
    //   519: astore #8
    //   521: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   524: arraylength
    //   525: istore_2
    //   526: iinc #1, -1
    //   529: iload_2
    //   530: iload_1
    //   531: if_icmple -> 633
    //   534: new java/lang/StringBuilder
    //   537: dup
    //   538: invokespecial <init> : ()V
    //   541: astore #6
    //   543: aload #6
    //   545: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   548: iload_1
    //   549: aaload
    //   550: invokevirtual getLatitude : ()D
    //   553: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   556: pop
    //   557: aload #6
    //   559: ldc_w ''
    //   562: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   565: pop
    //   566: aload #6
    //   568: invokevirtual toString : ()Ljava/lang/String;
    //   571: invokevirtual isEmpty : ()Z
    //   574: ifne -> 633
    //   577: new java/lang/StringBuilder
    //   580: dup
    //   581: invokespecial <init> : ()V
    //   584: astore #6
    //   586: aload #6
    //   588: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   591: iload_1
    //   592: aaload
    //   593: invokevirtual getLatitude : ()D
    //   596: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   599: pop
    //   600: aload #6
    //   602: ldc_w ''
    //   605: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   608: pop
    //   609: aload #6
    //   611: getstatic com/roadtrack/onstar/BO/GlobalMembers.viaInfoFromMap : [Lcom/roadtrack/onstar/nav/routing/LocationInfo;
    //   614: iload_1
    //   615: aaload
    //   616: invokevirtual getLongitude : ()D
    //   619: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   622: pop
    //   623: aload #6
    //   625: invokevirtual toString : ()Ljava/lang/String;
    //   628: astore #6
    //   630: goto -> 685
    //   633: ldc_w ''
    //   636: astore #6
    //   638: goto -> 685
    //   641: ldc_w ''
    //   644: astore #6
    //   646: ldc_w ''
    //   649: astore #8
    //   651: goto -> 685
    //   654: goto -> 670
    //   657: goto -> 665
    //   660: ldc_w ''
    //   663: astore #4
    //   665: ldc_w ''
    //   668: astore #5
    //   670: ldc_w ''
    //   673: astore #7
    //   675: ldc_w ''
    //   678: astore #6
    //   680: ldc_w ''
    //   683: astore #8
    //   685: aload_0
    //   686: getfield iWayPointsFaltantes : I
    //   689: ifeq -> 770
    //   692: new java/lang/StringBuilder
    //   695: dup
    //   696: invokespecial <init> : ()V
    //   699: astore #9
    //   701: aload #9
    //   703: aload_0
    //   704: getfield iWayPointsFaltantes : I
    //   707: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   710: pop
    //   711: aload #9
    //   713: ldc_w ''
    //   716: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   719: pop
    //   720: bipush #7
    //   722: anewarray java/lang/String
    //   725: dup
    //   726: iconst_0
    //   727: ldc_w '86'
    //   730: aastore
    //   731: dup
    //   732: iconst_1
    //   733: aload #9
    //   735: invokevirtual toString : ()Ljava/lang/String;
    //   738: aastore
    //   739: dup
    //   740: iconst_2
    //   741: aload #4
    //   743: aastore
    //   744: dup
    //   745: iconst_3
    //   746: aload #5
    //   748: aastore
    //   749: dup
    //   750: iconst_4
    //   751: aload #7
    //   753: aastore
    //   754: dup
    //   755: iconst_5
    //   756: aload #8
    //   758: aastore
    //   759: dup
    //   760: bipush #6
    //   762: aload #6
    //   764: aastore
    //   765: astore #4
    //   767: goto -> 1006
    //   770: aload #5
    //   772: invokevirtual isEmpty : ()Z
    //   775: ifeq -> 811
    //   778: iconst_3
    //   779: anewarray java/lang/String
    //   782: astore #5
    //   784: aload #5
    //   786: iconst_0
    //   787: ldc_w '86'
    //   790: aastore
    //   791: aload #5
    //   793: iconst_1
    //   794: ldc_w '0'
    //   797: aastore
    //   798: aload #5
    //   800: iconst_2
    //   801: aload #4
    //   803: aastore
    //   804: aload #5
    //   806: astore #4
    //   808: goto -> 1006
    //   811: aload #7
    //   813: invokevirtual isEmpty : ()Z
    //   816: ifeq -> 858
    //   819: iconst_4
    //   820: anewarray java/lang/String
    //   823: astore #6
    //   825: aload #6
    //   827: iconst_0
    //   828: ldc_w '86'
    //   831: aastore
    //   832: aload #6
    //   834: iconst_1
    //   835: ldc_w '0'
    //   838: aastore
    //   839: aload #6
    //   841: iconst_2
    //   842: aload #4
    //   844: aastore
    //   845: aload #6
    //   847: iconst_3
    //   848: aload #5
    //   850: aastore
    //   851: aload #6
    //   853: astore #4
    //   855: goto -> 1006
    //   858: aload #8
    //   860: invokevirtual isEmpty : ()Z
    //   863: ifeq -> 911
    //   866: iconst_5
    //   867: anewarray java/lang/String
    //   870: astore #6
    //   872: aload #6
    //   874: iconst_0
    //   875: ldc_w '86'
    //   878: aastore
    //   879: aload #6
    //   881: iconst_1
    //   882: ldc_w '0'
    //   885: aastore
    //   886: aload #6
    //   888: iconst_2
    //   889: aload #4
    //   891: aastore
    //   892: aload #6
    //   894: iconst_3
    //   895: aload #5
    //   897: aastore
    //   898: aload #6
    //   900: iconst_4
    //   901: aload #7
    //   903: aastore
    //   904: aload #6
    //   906: astore #4
    //   908: goto -> 1006
    //   911: aload #6
    //   913: invokevirtual isEmpty : ()Z
    //   916: ifeq -> 961
    //   919: bipush #6
    //   921: anewarray java/lang/String
    //   924: dup
    //   925: iconst_0
    //   926: ldc_w '86'
    //   929: aastore
    //   930: dup
    //   931: iconst_1
    //   932: ldc_w '0'
    //   935: aastore
    //   936: dup
    //   937: iconst_2
    //   938: aload #4
    //   940: aastore
    //   941: dup
    //   942: iconst_3
    //   943: aload #5
    //   945: aastore
    //   946: dup
    //   947: iconst_4
    //   948: aload #7
    //   950: aastore
    //   951: dup
    //   952: iconst_5
    //   953: aload #8
    //   955: aastore
    //   956: astore #4
    //   958: goto -> 1006
    //   961: bipush #7
    //   963: anewarray java/lang/String
    //   966: dup
    //   967: iconst_0
    //   968: ldc_w '86'
    //   971: aastore
    //   972: dup
    //   973: iconst_1
    //   974: ldc_w '0'
    //   977: aastore
    //   978: dup
    //   979: iconst_2
    //   980: aload #4
    //   982: aastore
    //   983: dup
    //   984: iconst_3
    //   985: aload #5
    //   987: aastore
    //   988: dup
    //   989: iconst_4
    //   990: aload #7
    //   992: aastore
    //   993: dup
    //   994: iconst_5
    //   995: aload #8
    //   997: aastore
    //   998: dup
    //   999: bipush #6
    //   1001: aload #6
    //   1003: aastore
    //   1004: astore #4
    //   1006: aload #4
    //   1008: areturn
  }
  
  private void callOnResume() {
    GlobalMembers.bSeEnvioRutaAP8 = false;
    this.rtApp = (onstarApplication)getApplicationContext();
    this.rtApp.setAppStatus(1);
    this.dbFun = new DBFunctions(getApplicationContext());
    this.userPreference = this.dbFun.getUserPreference(GlobalMembers.userLogged);
    if (Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity") == null) {
      Object object = PreferenceRT.GetValuePreference(Enums$SettingsPreference.soapString, "", (Context)this);
      try {
        JSONArray jSONArray = new JSONArray();
        this(object.toString());
        object = new BackGroundRestartHandler();
        super();
        List<UserDevicesVO> list = object.getListUserDevice(jSONArray);
        this.rtApp.setmDeviceUserList(list);
        GlobalMembers.mDeviceUserList = list;
      } catch (Exception exception) {
        Utilities.escribeArchivo("MainActivity", "Error: CallOnResume", exception.toString());
      } 
    } 
    this.imgPageLeft.setOnClickListener(new View.OnClickListener() {
          final MainActivity this$0;
          
          public void onClick(View param1View) {
            int i = MainActivity.this.mPager.getCurrentItem();
            if (i > 0)
              MainActivity.this.mPager.setCurrentItem(i - 1); 
          }
        });
    this.imgPageRight.setOnClickListener(new View.OnClickListener() {
          final MainActivity this$0;
          
          public void onClick(View param1View) {
            int i = MainActivity.this.mPager.getCurrentItem();
            if (i < MainActivity.this.mPager.getChildCount() - 1)
              MainActivity.this.mPager.setCurrentItem(i + 1); 
          }
        });
    NetUtilities.sendTermsAndConditions(mContext, Utilities.getDateTime());
    sendTomTomStatistics();
    if (this.rtApp.isWaterMarkActive()) {
      int i = Utilities.getLastVehicle(getApplicationContext(), this.userPreference.getUser(), this.rtApp);
      if ((UserDevicesVO)this.rtApp.getmDeviceUserList().get(i) != lastDeviceInMain)
        generateBottomPanel(); 
    } 
    generateDrawerMenu();
  }
  
  private void clubFunction() {
    (new GetOnStarClubURL((Context)this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void confirmDialogNewMapUpdate() {
    final Dialog dialog = Utilities.simpleDialog((Context)this, null, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955), Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.map_update_continue_p8i, 2131690079), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949), 20.0F, 16.0F);
    this.btnOK = (Button)dialog.findViewById(2131296343);
    this.btnNOK = (Button)dialog.findViewById(2131296344);
    this.btnOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            if (GlobalMembers.myListener != null) {
              GlobalMembers.MAP_UPDATE_CONFIRMED = true;
              GlobalMembers.myListener.onEvent();
            } 
            dialog.dismiss();
          }
        });
    this.btnNOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            if (GlobalMembers.myListener != null)
              GlobalMembers.MAP_UPDATE_CONFIRMED = false; 
            dialog.dismiss();
          }
        });
    dialog.show();
  }
  
  private void countNotifications() {
    this.objectAlert = (ArrayList<PushAlertsVO>)this.dbFun.getAlertListWithoutDTC(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), true);
    ArrayList<PushAlertsVO> arrayList = new ArrayList();
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity");
    if (this.dbFun.userDataTableHandler(this.rtApp.getAccountID().toString(), userDevicesVO.getDeviceId(), "", true)) {
      Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_lbl_banner_vehiculorobado_1, 2131690066);
    } else {
      userDevicesVO = null;
      new ArrayList();
      List list = this.dbFun.getHistoricalListRead(GlobalMembers.userLogged, Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
      if (list != null && list.size() != 0)
        for (Historical historical : list) {
          String str1;
          try {
            String str = GlobalMembers.pushVOsdf.format(GlobalMembers.historicalsdf.parse(historical.getDateTime()));
            str1 = str;
          } catch (ParseException parseException) {
            Utilities.escribeArchivo("MainActivity", "Error: loadNotifications", parseException.getMessage());
          } 
          String str2 = (new BRInfo(getApplicationContext())).getActionName(historical);
          arrayList.add(new PushAlertsVO(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.notificationsNoRecorder, 2131690223), str2, "", str1, historical.getLatitud(), historical.getLongitud(), historical.getIdAction(), historical.getId(), "", "", "", true, historical.getCompletion_code(), historical.getRequestErroId(), historical.getResponseErrorId(), historical.getMessageResponseId(), 0, historical.getDateTimeExecution()));
        }  
      if (this.objectAlert != null)
        for (byte b = 0; b < this.objectAlert.size(); b++) {
          if ((((PushAlertsVO)this.objectAlert.get(b)).getAcc().equals("6") || ((PushAlertsVO)this.objectAlert.get(b)).getAcc().equals("10") || ((PushAlertsVO)this.objectAlert.get(b)).getAcc().equals("12")) && (!((PushAlertsVO)this.objectAlert.get(b)).getActionId().equals("FollowMe") || !((PushAlertsVO)this.objectAlert.get(b)).getInsertedRow().equals("1") || ((PushAlertsVO)this.objectAlert.get(b)).getAlert().equals("FollowMeEnd"))) {
            BRInfo bRInfo = new BRInfo(getApplicationContext());
            ((PushAlertsVO)this.objectAlert.get(b)).setAlert(bRInfo.getActionName(this.objectAlert.get(b)));
            arrayList.add(this.objectAlert.get(b));
          } 
        }  
      if (arrayList.size() > 50)
        for (int i = arrayList.size() - 1; i >= 50; i--)
          arrayList.remove(i);  
      GlobalMembers.notificaciones = arrayList.size();
    } 
  }
  
  private void countNotificationsAgendamiento() {
    this.agendamientoAlerts = (ArrayList<PushAlertsVO>)this.dbFun.getAgendamientoAlertList(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
    GlobalMembers.notificacionesAgendamiento = this.agendamientoAlerts.size();
  }
  
  private void countNotificationsDTC() {
    this.dtcAlerts = (ArrayList<PushAlertsVO>)this.dbFun.getDTCAlertList(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
    GlobalMembers.notificacionesDTC = this.dtcAlerts.size();
  }
  
  @SuppressLint({"NewApi"})
  private void createExitAppDialog(boolean paramBoolean) {
    try {
      String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
      String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_si_1, 2131689952);
      String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.salir_popup_lbl_cerrarsesion_2, 2131690353);
      String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.onstar_ic_logout, 2131165610);
      drawable.setTint(ContextCompat.getColor((Context)this, 2131034170));
      Dialog dialog = Utilities.simpleDialog((Context)this, drawable, str3, str1, true, str4, true, str2, false);
      this.btnNOK = (Button)dialog.findViewById(2131296344);
      this.btnOK = (Button)dialog.findViewById(2131296343);
      Button button2 = this.btnOK;
      View.OnClickListener onClickListener1 = new View.OnClickListener() {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        };
      super(this, dialog);
      button2.setOnClickListener(onClickListener1);
      Button button1 = this.btnNOK;
      View.OnClickListener onClickListener2 = new View.OnClickListener() {
          final MainActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            String str1 = MainActivity.this.rtApp.getLocatorUserId();
            String str2 = String.valueOf(13);
            Intent intent = new Intent((Context)MainActivity.this, TransparentActivityWithSpinner.class);
            MainActivity.this.startActivity(intent);
            ((NotificationManager)MainActivity.mContext.getSystemService("notification")).cancelAll();
            String str4 = PreferenceRT.GetValuePreference(GlobalMembers.deviceName, "", onstarApplication.getContext());
            String str3 = Utilities.DeviceUuidFactory(MainActivity.mContext);
            (new DeleteDevicesTask(new DevicesInterface(this) {
                  public void getResponseService(String param2String) {}
                },  (Activity)MainActivity.this, str1, str4, str2, str3, true)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
            GlobalMembers.unRegisterDevice = true;
            MainActivity.this.async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "3434", MainActivity.access$100(this.this$0), str1, str2, str4, "exit" });
          }
        };
      super(this, dialog);
      button1.setOnClickListener(onClickListener2);
      dialog.show();
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: createExitAppDialog", exception.getMessage());
    } 
  }
  
  private void createLaterAlertDialog() {
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.mapdownloading_popup_lbl_recuerdequeusted_2, 2131690124);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_mapdownloading_popup_lbl_descargademapa_1, 2131689940);
    this.cancelable = true;
    this.drawableId = 2131165484;
    this.stringId = str2;
    this.titleId = str1;
    Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, this.drawableId);
    createMenuDialog(this.cancelable, (Drawable)null, this.stringId, this.titleId, false);
  }
  
  private void createMenuSpeedDialog(boolean paramBoolean, int paramInt, String paramString) {
    try {
      this.dialogSpeed = DialogSpeed.newInstance();
      this.dialogSpeed.setPreferenceDialogFragment(this, (Context)this, paramBoolean, paramInt, paramString);
      this.dialogSpeed.show(getSupportFragmentManager(), null);
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: createMenuSpeedDialog", exception.getMessage());
    } 
  }
  
  @SuppressLint({"InflateParams"})
  private void createMobileAlertDialog() {
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_acticity_no_wifi, 2131690027);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_mapdownloading_popup_lbl_descargademapa_1, 2131689940);
    this.cancelable = true;
    this.drawableId = 2131165484;
    this.stringId = str1;
    this.titleId = str2;
    Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, this.drawableId);
    createMenuDialog(this.cancelable, (Drawable)null, this.stringId, this.titleId, true);
  }
  
  private void createSpeedDialog() {
    final Dialog dialog = new Dialog((Context)this, 2131755392);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    dialog.setContentView(2131427429);
    dialog.setCancelable(true);
    this.action_txtSpeedValue = (TextView)dialog.findViewById(2131296340);
    this.txtSpeedValue = (EditText)dialog.findViewById(2131296307);
    this.errorLogs = (TextView)dialog.findViewById(2131296518);
    this.LaySpeed = (LinearLayout)dialog.findViewById(2131296688);
    this.imgActionId = (ImageView)dialog.findViewById(2131296619);
    String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionalertavelsl_1, 2131689852);
    this.action_txtSpeedValue.setText(str);
    this.LaySpeed.setVisibility(0);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_dialog_speed, 2131165289);
    this.imgActionId.setImageDrawable(drawable);
    this.actions_btnadd = (Button)dialog.findViewById(2131296348);
    this.actions_btnsubs = (Button)dialog.findViewById(2131296349);
    this.actions_btnadd.setVisibility(0);
    this.actions_btnsubs.setVisibility(0);
    this.txtSpeedValue.setVisibility(0);
    ((InputMethodManager)getApplicationContext().getSystemService("input_method")).toggleSoftInput(2, 0);
    this.actions_btnActive = (Button)dialog.findViewById(2131296343);
    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
          final MainActivity this$0;
          
          public void onCancel(DialogInterface param1DialogInterface) {
            MainActivity.access$2802(MainActivity.this, false);
          }
        });
    this.actions_btnActive.setOnClickListener(new View.OnClickListener() {
          final MainActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            String str = String.format("%S,1", new Object[] { MainActivity.access$2900(this.this$0).getText().toString() }).trim();
            if (MainActivity.this.txtSpeedValue.getText().toString().length() > 0)
              try {
                int i = Integer.parseInt(MainActivity.this.txtSpeedValue.getText().toString());
                char c = '';
                if (i >= 30 && i <= 150) {
                  MainActivity.access$3002(MainActivity.this, str);
                  MainActivity.onAlertSpeed = Boolean.valueOf(true);
                  MainActivity.this.deviceId = Utilities.getLastKnownDeviceSelected(MainActivity.this.rtApp, "MainActivity").getDeviceId();
                  MainActivity.this.actionCode = Utilities.getSpeedCode(MainActivity.this.rtApp, "MainActivity").GetCodeString();
                  MainActivity.this.csvParams = MainActivity.this.genericSpeedValue;
                  MainActivity.this.deviceCSVParams = "";
                  MainActivity.Actions actions = new MainActivity.Actions();
                  this();
                  actions.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.this$0.sessionKey, this.this$0.login, this.this$0.password, this.this$0.accountId, this.this$0.deviceId, this.this$0.actionCode, this.this$0.csvParams, this.this$0.userId, this.this$0.applicationSourceId, this.this$0.deviceCSVParams });
                  dialog.getWindow().setSoftInputMode(3);
                  dialog.dismiss();
                  ((InputMethodManager)MainActivity.this.getApplicationContext().getSystemService("input_method")).toggleSoftInput(1, 0);
                  MainActivity.access$2802(MainActivity.this, false);
                } else {
                  str = String.format(Utilities.getStringFromConfigList((Context)MainActivity.this, MainActivity.this.stringsResourcesVO.speedAlertLimitError, 2131690371), new Object[] { Integer.valueOf(30), Integer.valueOf(150) });
                  MainActivity.this.errorLogs.setText(str);
                  if (i < 30)
                    c = '\036'; 
                  MainActivity.this.txtSpeedValue.setText(String.valueOf(c));
                  MainActivity.this.txtSpeedValue.setSelection(0, MainActivity.this.txtSpeedValue.getText().length());
                } 
              } catch (Exception exception) {
                Utilities.escribeArchivo("MainActivity", "Error: setOnClickListener", exception.getMessage());
              }  
          }
        });
    this.actions_btnadd.setOnClickListener(new View.OnClickListener() {
          final MainActivity this$0;
          
          public void onClick(View param1View) {
            String str2 = MainActivity.this.txtSpeedValue.getText().toString();
            String str1 = str2;
            if (str2.trim().equals(""))
              str1 = String.valueOf(20); 
            int i = Integer.parseInt(str1) + 10;
            if (i <= 150)
              MainActivity.this.txtSpeedValue.setText(String.valueOf(i)); 
          }
        });
    this.actions_btnsubs.setOnClickListener(new View.OnClickListener() {
          final MainActivity this$0;
          
          public void onClick(View param1View) {
            String str2 = MainActivity.this.txtSpeedValue.getText().toString();
            String str1 = str2;
            if (str2.trim().equals(""))
              str1 = String.valueOf(40); 
            int i = Integer.parseInt(str1) - 10;
            if (i >= 30)
              MainActivity.this.txtSpeedValue.setText(String.valueOf(i)); 
          }
        });
    dialog.show();
    this.isSpeedDialog = true;
  }
  
  private void dialogNoGPSActive() {
    final Dialog dialog = Utilities.simpleDialog((Context)this, null, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955), Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.navigation_gps_lbl_alerta_1, 2131690144), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_opciones_1, 2131689951), 20.0F, 16.0F);
    this.btnOK = (Button)dialog.findViewById(2131296343);
    this.btnNOK = (Button)dialog.findViewById(2131296344);
    this.btnOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.btnNOK.setOnClickListener(new View.OnClickListener() {
          final MainActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
            MainActivity.this.startActivity(intent);
          }
        });
    dialog.show();
  }
  
  private void dtcFunction() {
    Intent intent = new Intent((Context)this, RemoteDiagnosticActivity.class);
    Bundle bundle = new Bundle();
    bundle.putString("previousClass", MainActivity.class.getSimpleName());
    intent.putExtras(bundle);
    startActivity(intent);
    GlobalMembers.notificacionesDTC = 0;
    this.dbFun.readerNotificationDTC(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
  }
  
  private void emergencyFunction() {
    if (!GlobalMembers.isShowingDialog) {
      GlobalMembers.btnpress = 2;
      this.cancelable = true;
      this.drawableId = 2131165286;
      String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.emergencia_global_popup_lbl_llamadaemergencia_2, 2131689778);
      String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_emergencia_1, 2131689919);
      this.stringId = str1;
      this.titleId = str2;
      if (GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
        if (!isBtAndPlatinumConnected()) {
          (new CallPhone((Context)this)).Emergency();
        } else {
          if (((true ^ bEsperandoP8) & bRelayReady) != 0) {
            Intent intent = new Intent((Context)this, ActivityCall.class);
            intent.putExtra("Boton", "Emergencia");
            startActivityForResult(intent, 123);
            (new Thread(new TimerThread())).start();
          } 
          CallOrHangUp(Enums$Calls.EmergencyCallAndMessage);
        } 
      } else {
        (new CallPhone((Context)this)).Emergency();
      } 
    } 
  }
  
  private static void enqueuedOutData(String paramString) {
    if (paramString == null)
      return; 
    if (OutQueue == null)
      OutQueue = new commonQueue(); 
    OutQueue.enqueue(paramString);
    sendOutMessage();
  }
  
  private void enterNotification(ProgressBar paramProgressBar, View paramView, final CustomActionButton tempButton) {
    boolean bool = onFindMe.booleanValue();
    Boolean bool1 = Boolean.valueOf(true);
    if (bool || onFollowMe.booleanValue() || onLigths.booleanValue() || onHornLights.booleanValue() || onCloseDoors.booleanValue() || onOpenDoors.booleanValue() || onDisarmPINCODE.booleanValue() || onAlertParking.booleanValue() || onAlertSpeed.booleanValue() || onAlertValet.booleanValue() || onNotification.booleanValue() || onHorn.booleanValue()) {
      if (!isActionInProcessDialogShown.booleanValue()) {
        isActionInProcessDialogShown = bool1;
        noRepeatSameActionDialog();
      } 
      return;
    } 
    if (!onNotification.booleanValue()) {
      this.NotificationsProgressBar = paramProgressBar;
      NotificationView = paramView;
      onNotification = bool1;
      if (!NetUtilities.validateNetwork((Context)this, false)) {
        Intent intent = new Intent((Context)this, NotificationsActivity.class);
        AlertOff();
        startActivityForResult(intent, this.REQUEST_CODE);
        (new Thread(new Runnable() {
              final MainActivity this$0;
              
              final CustomActionButton val$tempButton;
              
              public void run() {
                try {
                  Thread.sleep(1000L);
                } catch (InterruptedException interruptedException) {
                  Utilities.escribeArchivo("MainActivity", "Error: Runnable", interruptedException.toString());
                } 
                MainActivity.this.runOnUiThread(new Runnable() {
                      final MainActivity.null this$1;
                      
                      public void run() {
                        Thread thread = Thread.currentThread();
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(null.class.getSimpleName());
                        stringBuilder2.append(": ");
                        stringBuilder2.append(Thread.currentThread().getName());
                        thread.setName(stringBuilder2.toString());
                        CustomActionButton customActionButton = tempButton;
                        if (customActionButton != null)
                          customActionButton.showActionStatus(0); 
                        customActionButton = UserInterfaceUtils.getActionButton(MainActivity.bottomPanelButtons, Enums$Services.pid);
                        if (customActionButton != null)
                          customActionButton.showActionStatus(0); 
                        StringBuilder stringBuilder1 = new StringBuilder();
                        stringBuilder1.append(MainActivity.this.rtApp.getDeviceTypeId());
                        stringBuilder1.append("_ID");
                        PreferenceRT.SetValuePreference(stringBuilder1.toString(), false, (Context)MainActivity.this);
                        MainActivity.onNotification = Boolean.valueOf(false);
                        Enums$Services.ActionNotifications.GetCodeString();
                      }
                    });
              }
            })).start();
      } else {
        onNotification = bool1;
        paramProgressBar = this.NotificationsProgressBar;
        if (paramProgressBar != null)
          paramProgressBar.setVisibility(0); 
        if (tempButton != null)
          tempButton.showActionStatus(1); 
        String str = Enums$Services.ActionNotifications.GetCodeString();
        this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
        (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { 
              "", this.login, this.password, this.accountId, this.deviceId, str, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams, 
              "1" });
      } 
    } else {
      this.isTryingToExecuteSameAction = true;
    } 
  }
  
  private byte[] escapeSpecialChars(byte[] paramArrayOfbyte, int paramInt) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    for (byte b = 0; b < paramInt; b++) {
      if (paramArrayOfbyte[b] == 93 || paramArrayOfbyte[b] == 91 || paramArrayOfbyte[b] == 124 || paramArrayOfbyte[b] == 126)
        byteArrayOutputStream.write(92); 
      byteArrayOutputStream.write(paramArrayOfbyte[b]);
    } 
    return byteArrayOutputStream.toByteArray();
  }
  
  private void executeGetGmt_Task() {
    taskSet.getGtm_Task((Context)this, new Base_Interface(this) {
          public void onFail(String param1String) {}
          
          public void onSuccess(String param1String) {
            MainActivity.gmtDownload = Boolean.valueOf(true);
          }
        });
  }
  
  private void exitFunction() {
    toggleDrawerMenu();
    createExitAppDialog(true);
  }
  
  private void fillVehicleList(Spinner paramSpinner, Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: checkcast com/roadtrack/onstar/onstarApplication
    //   6: astore #4
    //   8: aload_1
    //   9: ifnonnull -> 15
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: new java/util/ArrayList
    //   18: astore #6
    //   20: aload #6
    //   22: invokespecial <init> : ()V
    //   25: iconst_0
    //   26: istore_3
    //   27: iload_3
    //   28: aload #4
    //   30: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   33: invokeinterface size : ()I
    //   38: if_icmpge -> 72
    //   41: aload #6
    //   43: aload #4
    //   45: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   48: iload_3
    //   49: invokeinterface get : (I)Ljava/lang/Object;
    //   54: checkcast com/roadtrack/onstar/VO/UserDevicesVO
    //   57: invokevirtual getName : ()Ljava/lang/String;
    //   60: invokeinterface add : (Ljava/lang/Object;)Z
    //   65: pop
    //   66: iinc #3, 1
    //   69: goto -> 27
    //   72: aload #6
    //   74: putstatic com/roadtrack/onstar/BO/GlobalMembers.vehicleList : Ljava/util/List;
    //   77: aload_0
    //   78: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   81: aload_0
    //   82: getfield userPreference : Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   85: invokevirtual getUser : ()Ljava/lang/String;
    //   88: aload #4
    //   90: invokestatic getLastVehicle : (Landroid/content/Context;Ljava/lang/String;Lcom/roadtrack/onstar/onstarApplication;)I
    //   93: istore_3
    //   94: new com/roadtrack/onstar/adapter/VehiculeSpinnerAdapter
    //   97: astore #5
    //   99: aload #5
    //   101: aload_2
    //   102: ldc_w 2131427512
    //   105: ldc_w 2131297225
    //   108: ldc_w 2131297226
    //   111: aload_0
    //   112: getfield spinner_menu : Landroid/widget/Spinner;
    //   115: aload #6
    //   117: invokespecial <init> : (Landroid/content/Context;IIILandroid/widget/Spinner;Ljava/util/List;)V
    //   120: getstatic com/roadtrack/onstar/BO/GlobalMembers.renewalfeatures : Z
    //   123: ifne -> 135
    //   126: aload #5
    //   128: iconst_0
    //   129: invokevirtual setSpinnerType : (I)V
    //   132: goto -> 160
    //   135: aload #5
    //   137: iconst_1
    //   138: invokevirtual setSpinnerType : (I)V
    //   141: aload #5
    //   143: aload_0
    //   144: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   147: aload_0
    //   148: getfield userPreference : Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   151: invokevirtual getUser : ()Ljava/lang/String;
    //   154: invokevirtual getVehiclesCatalogIgnoreSelected : (Ljava/lang/String;)Ljava/util/ArrayList;
    //   157: invokevirtual setVehicleCatalogVOs : (Ljava/util/List;)V
    //   160: aload #5
    //   162: ldc_w 17367049
    //   165: invokevirtual setDropDownViewResource : (I)V
    //   168: aload_1
    //   169: aload #5
    //   171: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   174: aload_1
    //   175: iload_3
    //   176: invokevirtual setSelection : (I)V
    //   179: aload #4
    //   181: invokestatic setDeviceType : (Lcom/roadtrack/onstar/onstarApplication;)V
    //   184: getstatic com/roadtrack/onstar/BO/GlobalMembers.isMAPUpdateEnabled : Z
    //   187: ifne -> 196
    //   190: getstatic com/roadtrack/onstar/BO/GlobalMembers.isHMIEnabled : Z
    //   193: ifeq -> 200
    //   196: aload_0
    //   197: invokespecial initBluetooth : ()V
    //   200: new com/roadtrack/onstar/MainActivity$37
    //   203: astore_2
    //   204: aload_2
    //   205: aload_0
    //   206: aload #4
    //   208: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity;Lcom/roadtrack/onstar/onstarApplication;)V
    //   211: aload_1
    //   212: aload_2
    //   213: invokevirtual setOnItemSelectedListener : (Landroid/widget/AdapterView$OnItemSelectedListener;)V
    //   216: goto -> 233
    //   219: astore_1
    //   220: ldc_w 'MainActivity'
    //   223: ldc_w 'Error: setOnItemSelectedItem'
    //   226: aload_1
    //   227: invokevirtual getMessage : ()Ljava/lang/String;
    //   230: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   233: aload_0
    //   234: monitorexit
    //   235: return
    //   236: astore_1
    //   237: aload_0
    //   238: monitorexit
    //   239: aload_1
    //   240: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	236	finally
    //   15	25	219	java/lang/Exception
    //   15	25	236	finally
    //   27	66	219	java/lang/Exception
    //   27	66	236	finally
    //   72	132	219	java/lang/Exception
    //   72	132	236	finally
    //   135	160	219	java/lang/Exception
    //   135	160	236	finally
    //   160	196	219	java/lang/Exception
    //   160	196	236	finally
    //   196	200	219	java/lang/Exception
    //   196	200	236	finally
    //   200	216	219	java/lang/Exception
    //   200	216	236	finally
    //   220	233	236	finally
  }
  
  private void findmeFunction(View paramView) {
    String str;
    boolean bool = onFindMe.booleanValue();
    Boolean bool1 = Boolean.valueOf(true);
    if (!bool)
      setWatterMarks(true, new Enums$Services[] { Enums$Services.FindMe }); 
    final CustomActionButton button = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.FindMe);
    bool = (new DBFunctions(mContext)).userDataTableHandler(this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), "", true);
    Utilities.showTheftAutoBanner((TextView)findViewById(2131297163), (Context)this, bool);
    if (bool)
      return; 
    if (!onFindMe.booleanValue() && !onFollowMe.booleanValue()) {
      if (!NetUtilities.validateNetwork((Context)this, false, true))
        return; 
      if (this.userPreference.getCountry() != null) {
        this.prefix = Utilities.getPrefixFromCountry(this.userPreference.getCountry(), (Context)this);
      } else {
        this.prefix = Utilities.getPrefixFromCountry("BR", (Context)this);
      } 
      onFindMe = bool1;
      this.dateInFutureForActions = Utilities.addSecondsToDate(30);
      String str2 = this.rtApp.getSessionKey();
      String str1 = this.rtApp.getUserAccessData()[1];
      str = this.rtApp.getAccountID();
      String str5 = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
      String str4 = this.rtApp.getLocatorUserId();
      String str3 = Enums$Services.FindMe.GetCodeString();
      BannerAlpha(true);
      ActionsProcess actionsProcess = new ActionsProcess((Context)this, customActionButton, str3, str5, this.act);
      actionsProcess.setOnPostExecuteListener(new ActionsProcess.OnPostExecuteListener() {
            final MainActivity this$0;
            
            final CustomActionButton val$button;
            
            public void onPostExecuteListener(ActionResultVO param1ActionResultVO) {
              MainActivity.this.setWatterMarks(false, new Enums$Services[0]);
              MainActivity.this.BannerAlpha(false);
              MainActivity.this.lockRightDrawer(false);
              MainActivity.access$11402((CountDownTimer)new TimerAsyncTAsk(15000L, 100L, new ICounterCallback() {
                      final MainActivity.null this$1;
                      
                      public void onTickFInish() {
                        MainActivity.onFindMe = Boolean.valueOf(false);
                        if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue() && !MainActivity.onLigths.booleanValue() && !MainActivity.onHornLights.booleanValue() && !MainActivity.onCloseDoors.booleanValue() && !MainActivity.onOpenDoors.booleanValue() && !MainActivity.onDisarmPINCODE.booleanValue() && !MainActivity.onAlertParking.booleanValue() && !MainActivity.onAlertSpeed.booleanValue() && !MainActivity.onAlertValet.booleanValue() && !MainActivity.onNotification.booleanValue() && !MainActivity.onHorn.booleanValue()) {
                          OrientationManager.unlockOrientation(MainActivity.this.act);
                          MainActivity.null  = MainActivity.null.this;
                          MainActivity.this.finishMarker("1", button);
                          MainActivity.this.setWatterMarks(false, new Enums$Services[0]);
                          MainActivity.this.BannerAlpha(false);
                          MainActivity.this.lockRightDrawer(false);
                        } else {
                          MainActivity.this.setPlanandPages();
                        } 
                      }
                    }));
              MainActivity.count.start();
              MainActivity.isShowingOnFindMe = false;
              if (param1ActionResultVO.getLatitude() != 0.0F && param1ActionResultVO.getLongitude() != 0.0F) {
                int i = param1ActionResultVO.getDeviceId();
                String str3 = String.valueOf(Enums$Services.FindMe.GetCode());
                MainActivity mainActivity1 = MainActivity.this;
                String str1 = Utilities.getStringFromConfigList((Context)mainActivity1, mainActivity1.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
                MainActivity mainActivity2 = MainActivity.this;
                String str2 = Utilities.getStringFromConfigList((Context)mainActivity2, mainActivity2.stringsResourcesVO.navigation_lbl_localizame_gpssincobertura_1, 2131690158);
                if (param1ActionResultVO.getGpsStatus() != Enums$GpsStatusCode.Ok.value())
                  str1 = str2; 
                str2 = Utilities.getUTCToNormalDate(param1ActionResultVO.getEventDateTime(), "dd/MM/yyyy HH:mm:ss").replace("/", "-");
                GlobalMembers.lastNavParamsFindMe = str1;
                if (Utilities.isActivityRunning(MainActivity.mContext, WakeUpCar.class)) {
                  GoogleMapVO googleMapVO = new GoogleMapVO();
                  googleMapVO.setKEY_SET_ENGINE_SOURCE(Enums$navigationProcess.External);
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("");
                  stringBuilder.append(i);
                  googleMapVO.setKEY_SET_NAV_DEVICE(stringBuilder.toString());
                  googleMapVO.setKEY_SET_NAV_ACTION(str3);
                  googleMapVO.setKEY_SET_NAV_PARAMS(str1);
                  googleMapVO.setKEY_EXTERNAL_LAT(String.valueOf(param1ActionResultVO.getLatitude()));
                  googleMapVO.setKEY_EXTERNAL_LNG(String.valueOf(param1ActionResultVO.getLongitude()));
                  googleMapVO.setKEY_GPSSTATUS(str1);
                  googleMapVO.setLASTKNOWDATE(str2);
                  GlobalMembers.openMap = true;
                  BundleActivityToMap.getInstanceOfBundleActivityToMap();
                  BundleActivityToMap.setGoogleMapVO(googleMapVO);
                  BundleActivityToMap.getInstanceOfBundleActivityToMap();
                  BundleActivityToMap.setIsFindmePending(Boolean.valueOf(true));
                } else {
                  GoogleMapVO googleMapVO = new GoogleMapVO();
                  googleMapVO.setKEY_SET_ENGINE_SOURCE(Enums$navigationProcess.External);
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("");
                  stringBuilder.append(i);
                  googleMapVO.setKEY_SET_NAV_DEVICE(stringBuilder.toString());
                  googleMapVO.setKEY_SET_NAV_ACTION(str3);
                  googleMapVO.setKEY_SET_NAV_PARAMS(str1);
                  googleMapVO.setKEY_EXTERNAL_LAT(String.valueOf(param1ActionResultVO.getLatitude()));
                  googleMapVO.setKEY_EXTERNAL_LNG(String.valueOf(param1ActionResultVO.getLongitude()));
                  googleMapVO.setKEY_GPSSTATUS(str1);
                  googleMapVO.setLASTKNOWDATE(str2);
                  MainActivity.this.openTomTomMap(googleMapVO);
                } 
              } 
            }
          });
      actionsProcess.executeOnExecutor(Executors.newSingleThreadExecutor(), (Object[])new String[] { str2, str1, str, str4, str3, "" });
      GlobalMembers.lastGetLastIncomingMessageFindMe = null;
    } else if (!isActionInProcessDialogShown.booleanValue()) {
      isActionInProcessDialogShown = (Boolean)str;
      noRepeatSameActionDialog();
    } 
  }
  
  private void finishMarker(String paramString, CustomActionButton paramCustomActionButton) {
    Boolean bool = Boolean.valueOf(false);
    if (paramCustomActionButton != null)
      paramCustomActionButton.showActionStatus(0); 
    if (paramString.equals(Enums$Services.FindMe.GetCodeString())) {
      onFindMe = bool;
    } else if (paramString.equals(Enums$Services.FollowMe.GetCodeString()) || paramString.equals(Enums$Services.FollowMeUUx.GetCodeString())) {
      onFollowMe = bool;
    } else if (paramString.equals(Enums$Services.Ligths.GetCodeString())) {
      onLigths = bool;
    } else if (paramString.equals(Enums$Services.HornLigths.GetCodeString())) {
      onHornLights = bool;
    } else if (paramString.equals(Enums$Services.Horn.GetCodeString()) || paramString.equals(Enums$Services.HornF1.GetCodeString())) {
      onHorn = bool;
    } else if (paramString.equals(Enums$Services.DoorsLock.GetCodeString())) {
      onCloseDoors = bool;
    } else if (paramString.equals(Enums$Services.DoorsUnlock.GetCodeString())) {
      onOpenDoors = bool;
    } else if (paramString.equals(Enums$Services.Disarm.GetCodeString())) {
      onDisarmPINCODE = bool;
    } else if (paramString.equals(Enums$Services.Parking.GetCodeString()) || paramString.equals(Enums$Services.ParkingUUx.GetCodeString())) {
      onAlertParking = bool;
    } else if (paramString.equals(Enums$Services.Speed.GetCodeString()) || paramString.equals(Enums$Services.SpeedUUx.GetCodeString())) {
      onAlertSpeed = bool;
    } else if (paramString.equals(Enums$Services.SpeedAlways.GetCodeString())) {
      onAlertSpeed = bool;
    } else if (paramString.equals(Enums$Services.valet.GetCodeString())) {
      onAlertValet = bool;
    } else if (paramString.equals(Enums$Services.ActionNotifications.GetCodeString())) {
      onNotification = bool;
    } 
    if (!onFindMe.booleanValue() && !onFollowMe.booleanValue() && !onLigths.booleanValue() && !onHornLights.booleanValue() && !onCloseDoors.booleanValue() && !onOpenDoors.booleanValue() && !onDisarmPINCODE.booleanValue() && !onAlertParking.booleanValue() && !onAlertSpeed.booleanValue() && !onAlertValet.booleanValue() && !onNotification.booleanValue() && !onHorn.booleanValue()) {
      OrientationManager.unlockOrientation(this.act);
      setWatterMarks(false, new Enums$Services[0]);
      BannerAlpha(false);
      lockRightDrawer(false);
    } else {
      setPlanandPages();
    } 
  }
  
  private void finishToCreate() {
    this.stringsResourcesVO = new StringsResourcesVO();
    this.async = new AsyncTaskUnregisterDevice(this.mActivity);
    _act = (Activity)this;
    this.rtApp = (onstarApplication)getApplicationContext();
    onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisRegular);
    InitializeEnvironment();
    PreferenceRT.SetValuePreference("EnebledPush", true, getApplicationContext());
    new MapDownload((Context)this, this.prefix, false);
    activityAlive = true;
    mContext = getApplicationContext();
    this.userPreference = new UserPreferenceVO();
    (new MapUtils()).setMapPath(mContext);
    this.followmefinish = new IntentFilter();
    this.followmefinish.addAction("SHOWDIALOGFOLLOWMEFINISH");
    this.logOutR = new BroadcastReceiverLogOut();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("GlobalTouchService");
    registerReceiver((BroadcastReceiver)this.logOutR, intentFilter);
    Intent intent = new Intent();
    intent.setAction("GlobalTouchService");
    intent.putExtra("ACTION_EXTRA", "usuario_activo");
    sendBroadcast(intent);
    fmff = getSupportFragmentManager();
    MapsFragment mapsFragment = (MapsFragment)fmff.findFragmentByTag(MapsFragment.class.getName());
    if (this.wl == null) {
      this.pm = (PowerManager)getSystemService("power");
      this.wl = this.pm.newWakeLock(805306394, "MyWakeLock");
    } 
    try {
      this.wl.release();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    this.wl.acquire();
    this.act = (Activity)this;
    taskSet = new TaskSet();
    mainContext = (Context)this;
    GlobalMembers.mainActivity = (Activity)this;
    this.myConfiguration = getResources().getConfiguration();
    callOnCreate();
    this.dialogDICAS = new Dialog((Context)this);
    this.dialogDICAS.requestWindowFeature(1);
    generateDrawerMenu();
    SetNotificationButton();
    setActionBarAndSpinner();
    getNotifys();
    UtilitiesFile.deleteUnnecessaryFiles();
    if (this.rtApp.isWaterMarkActive())
      generateBottomPanel(); 
    DBFunctions dBFunctions = new DBFunctions(mContext);
    this.vehicleCatalogVO = dBFunctions.getVehicleByDeviceID(this.userPreference.getUser(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
    countNotifications();
    countNotificationsDTC();
    countNotificationsAgendamiento();
    startService(new Intent((Context)this, NotificationService.class));
    ((NotificationManager)mContext.getSystemService("notification")).cancelAll();
    AlertOn();
    if (!gmtDownload.booleanValue())
      executeGetGmt_Task(); 
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actualizarapp_lbl_avisodesactivacion, 2131689648);
    String str2 = GlobalMembers.versionStatus;
    if (str2 != null && str2.equals("1"))
      PushVersion(mContext.getString(2131689666), str1); 
    if (!GlobalMembers.notificationSpinner)
      GlobalMembers.activeTutorial = false; 
    if (GlobalMembers.activeTutorial && this.myConfiguration.orientation == 1) {
      this.idTutorial = (LinearLayout)findViewById(2131296607);
      this.idTutorial.setOnClickListener(this.clickTutorial);
      this.idTutorial.setVisibility(0);
    } 
    this.arraylistVehiclesAlmostExpired = dBFunctions.getVehiclesAlmostExpired(this.userPreference.getUser());
    this.arraylistVehiclesExpired = dBFunctions.getVehiclesExpired(this.userPreference.getUser());
    this.arraylistVehiclesNormal = dBFunctions.getVehiclesNormal(this.userPreference.getUser());
    this.totalVehicleExpiredOrAlmost = this.arraylistVehiclesAlmostExpired.size() + this.arraylistVehiclesExpired.size() + this.arraylistVehiclesNormal.size();
    Utilities.attemptToPayDialog(mainContext, this.vehicleCatalogVO.getDeviceId());
    this.token = FirebaseInstanceId.getInstance().getToken();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FCM Main Token: ");
    stringBuilder.append(this.token);
    stringBuilder.toString();
    (new registerGCM((Context)this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[0]);
  }
  
  private void generateDrawerMenu() {
    this.mDrawerLayout = (DrawerLayout)findViewById(2131296505);
    this.mDrawerList = (ListView)findViewById(2131296793);
    this.spinner_menu = (Spinner)findViewById(2131297230);
    this.drawerLayout = (DrawerLayout)findViewById(2131296505);
    this.burgerDrawerButton = (ImageButton)findViewById(2131296794);
    this.burgerDrawerButton.setOnClickListener(new View.OnClickListener() {
          final MainActivity this$0;
          
          public void onClick(View param1View) {
            if (MainActivity.onFindMe.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onLigths.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onOpenDoors.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onHorn.booleanValue()) {
              if (!MainActivity.isActionInProcessDialogShown.booleanValue()) {
                MainActivity.isActionInProcessDialogShown = Boolean.valueOf(true);
                MainActivity.this.noRepeatSameActionDialog();
              } 
              return;
            } 
            MainActivity.this.toggleDrawerMenu();
          }
        });
    new ArrayList();
    boolean bool = GlobalMembers.activeTutorial;
    byte b = 0;
    if (bool && this.myConfiguration.orientation == 1) {
      this.idTutorialMenu = (LinearLayout)findViewById(2131296608);
      if (this.idTutorial != null) {
        this.idTutorialMenu.setOnClickListener(this.clickTutorialMenu);
        this.idTutorialMenu.setVisibility(0);
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("P");
    stringBuilder.append(this.rtApp.getDeviceTypeId());
    String str = stringBuilder.toString();
    if (this.rtApp.getDeviceTypeId() != null && GlobalMembers.myPlan && (GlobalMembers.deviceTypeP8.equals(str) || this.rtApp.getDeviceTypeId().equals("6"))) {
      this.NavIcons = getResources().obtainTypedArray(2130837505);
      this.mOptions = getResources().getStringArray(2130837519);
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 1);
    } else if (!Boolean.parseBoolean(GlobalMembers.PIDButton)) {
      this.NavIcons = getResources().obtainTypedArray(2130837521);
      this.mOptions = getResources().getStringArray(2130837517);
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 2);
    } else {
      this.NavIcons = getResources().obtainTypedArray(2130837522);
      this.mOptions = getResources().getStringArray(2130837518);
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 3);
    } 
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity");
    if (userDevicesVO != null && userDevicesVO.getButtomActions().containsKey(Enums$Services.WebPage.GetCodeString()))
      if (GlobalMembers.myPlan && (GlobalMembers.deviceTypeP8.equals(str) || this.rtApp.getDeviceTypeId().equals("6"))) {
        this.NavIcons = getResources().obtainTypedArray(2130837506);
        this.mOptions = getResources().getStringArray(2130837520);
        this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 4);
      } else {
        this.NavIcons = getResources().obtainTypedArray(2130837521);
        this.mOptions = getResources().getStringArray(2130837517);
        this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 2);
      }  
    this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(Boolean.valueOf(false), this.rtApp, mContext);
    this.NavItems = new ArrayList<item_objct>();
    while (true) {
      String[] arrayOfString = this.mOptions;
      if (b < arrayOfString.length) {
        this.NavItems.add(new item_objct(arrayOfString[b], this.NavIcons.getResourceId(b, -1)));
        b++;
        continue;
      } 
      new NavigationAdapter((Activity)this, this.NavItems);
      this.listButtonAdapter = new ListButtonAdapter(this.drawerMenuList);
      this.act.runOnUiThread(new Runnable() {
            final MainActivity this$0;
            
            public void run() {
              MainActivity.this.mDrawerList.setAdapter((ListAdapter)MainActivity.this.listButtonAdapter);
            }
          });
      this.mDrawerToggle = new ActionBarDrawerToggle((Activity)this, this.drawerLayout, 2131165565, 2131689666, 2131689666) {
          final MainActivity this$0;
          
          public void onDrawerClosed(View param1View) {
            super.onDrawerClosed(param1View);
            MainActivity.this.spinner_menu.setClickable(true);
            MainActivity.this.spinner_menu.setEnabled(true);
            if (GlobalMembers.activeTutorial) {
              if (MainActivity.this.idTutorial != null) {
                MainActivity.this.idTutorial.setVisibility(0);
                MainActivity.this.animationButtonTuto();
              } 
              MainActivity.access$11902(MainActivity.this, false);
              MainActivity.access$12002(MainActivity.this, false);
            } 
          }
          
          public void onDrawerOpened(View param1View) {
            MainActivity.this.spinner_menu.setClickable(false);
            MainActivity.this.spinner_menu.setEnabled(false);
            if (GlobalMembers.activeTutorial) {
              if (MainActivity.this.idTutorial != null) {
                MainActivity.this.idTutorial.setVisibility(4);
                MainActivity.this.animationDisableButtonTuto();
              } 
              MainActivity.access$11902(MainActivity.this, true);
              MainActivity.access$12002(MainActivity.this, true);
            } 
          }
        };
      this.mDrawerLayout.setDrawerListener((DrawerLayout.DrawerListener)this.mDrawerToggle);
      this.mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            final MainActivity this$0;
            
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
              ListButton listButton = (ListButton)param1View;
              MainActivity.this.optionDrawer(listButton);
            }
          });
      return;
    } 
  }
  
  public static ArrayList<String> getCommands(String paramString) {
    ArrayList<String> arrayList = new ArrayList();
    StringBuilder stringBuilder = new StringBuilder();
    byte b = 0;
    int i = 0;
    while (b < paramString.length()) {
      String str = String.valueOf(paramString.charAt(b));
      int j = i;
      if ("[".equals(str))
        j = i + 1; 
      stringBuilder.append(str);
      i = j;
      StringBuilder stringBuilder1 = stringBuilder;
      if ("]".equals(str)) {
        i = j;
        stringBuilder1 = stringBuilder;
        if (j == 1) {
          arrayList.add(stringBuilder.toString());
          stringBuilder1 = new StringBuilder();
          i = j - 1;
        } 
      } 
      b++;
      stringBuilder = stringBuilder1;
    } 
    return arrayList;
  }
  
  private String getFragmentActive() {
    return activeFrag;
  }
  
  public static String getLauncherClassName(Context paramContext) {
    PackageManager packageManager = paramContext.getPackageManager();
    Intent intent = new Intent("android.intent.action.MAIN");
    intent.addCategory("android.intent.category.LAUNCHER");
    for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
      if (resolveInfo.activityInfo.applicationInfo.packageName.equalsIgnoreCase(paramContext.getPackageName()))
        return resolveInfo.activityInfo.name; 
    } 
    return null;
  }
  
  private void getNotifys() {
    // Byte code:
    //   0: getstatic com/roadtrack/onstar/MainActivity.getNotificationsAfterLoginCounter : I
    //   3: iconst_1
    //   4: iadd
    //   5: putstatic com/roadtrack/onstar/MainActivity.getNotificationsAfterLoginCounter : I
    //   8: getstatic com/roadtrack/onstar/MainActivity.executeGetNotificationsAfterLogin : Z
    //   11: ifeq -> 21
    //   14: getstatic com/roadtrack/onstar/MainActivity.getNotificationsAfterLoginCounter : I
    //   17: iconst_1
    //   18: if_icmpgt -> 52
    //   21: getstatic com/roadtrack/onstar/MainActivity.oldDevice : Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   24: astore_1
    //   25: aload_1
    //   26: ifnull -> 240
    //   29: aload_1
    //   30: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   33: aload_0
    //   34: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   37: ldc_w 'MainActivity'
    //   40: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   43: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   46: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   49: ifne -> 240
    //   52: iconst_0
    //   53: putstatic com/roadtrack/onstar/MainActivity.executeGetNotificationsAfterLogin : Z
    //   56: iconst_0
    //   57: putstatic com/roadtrack/onstar/MainActivity.getNotificationsAfterLoginCounter : I
    //   60: aload_0
    //   61: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   64: ldc_w 'MainActivity'
    //   67: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   70: putstatic com/roadtrack/onstar/MainActivity.oldDevice : Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   73: aload_0
    //   74: aload_0
    //   75: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   78: invokevirtual getAccountID : ()Ljava/lang/String;
    //   81: aload_0
    //   82: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   85: ldc_w 'MainActivity'
    //   88: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   91: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   94: ldc_w ''
    //   97: iconst_1
    //   98: invokestatic IsVehicleTheft : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Z
    //   101: ifne -> 240
    //   104: getstatic com/roadtrack/onstar/Enums$Services.ActionNotifications : Lcom/roadtrack/onstar/Enums$Services;
    //   107: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   110: astore_1
    //   111: aload_0
    //   112: aload_0
    //   113: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   116: ldc_w 'MainActivity'
    //   119: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   122: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   125: putfield deviceId : Ljava/lang/String;
    //   128: new com/roadtrack/onstar/MainActivity$Actions
    //   131: dup
    //   132: aload_0
    //   133: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity;)V
    //   136: astore_3
    //   137: getstatic com/roadtrack/onstar/MainActivity.notificationButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
    //   140: astore_2
    //   141: aload_2
    //   142: ifnull -> 150
    //   145: aload_2
    //   146: iconst_1
    //   147: invokevirtual showActionStatus : (I)V
    //   150: aload_3
    //   151: getstatic android/os/AsyncTask.THREAD_POOL_EXECUTOR : Ljava/util/concurrent/Executor;
    //   154: bipush #11
    //   156: anewarray java/lang/String
    //   159: dup
    //   160: iconst_0
    //   161: ldc_w ''
    //   164: aastore
    //   165: dup
    //   166: iconst_1
    //   167: aload_0
    //   168: getfield login : Ljava/lang/String;
    //   171: aastore
    //   172: dup
    //   173: iconst_2
    //   174: aload_0
    //   175: getfield password : Ljava/lang/String;
    //   178: aastore
    //   179: dup
    //   180: iconst_3
    //   181: aload_0
    //   182: getfield accountId : Ljava/lang/String;
    //   185: aastore
    //   186: dup
    //   187: iconst_4
    //   188: aload_0
    //   189: getfield deviceId : Ljava/lang/String;
    //   192: aastore
    //   193: dup
    //   194: iconst_5
    //   195: aload_1
    //   196: aastore
    //   197: dup
    //   198: bipush #6
    //   200: aload_0
    //   201: getfield csvParams : Ljava/lang/String;
    //   204: aastore
    //   205: dup
    //   206: bipush #7
    //   208: aload_0
    //   209: getfield userId : Ljava/lang/String;
    //   212: aastore
    //   213: dup
    //   214: bipush #8
    //   216: aload_0
    //   217: getfield applicationSourceId : Ljava/lang/String;
    //   220: aastore
    //   221: dup
    //   222: bipush #9
    //   224: aload_0
    //   225: getfield deviceCSVParams : Ljava/lang/String;
    //   228: aastore
    //   229: dup
    //   230: bipush #10
    //   232: ldc_w '0'
    //   235: aastore
    //   236: invokevirtual executeOnExecutor : (Ljava/util/concurrent/Executor;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   239: pop
    //   240: return
  }
  
  private void getPlatinumAndConnection() {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
    try {
      BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      Set set = bluetoothAdapter.getBondedDevices();
      if (set.size() > 0)
        for (BluetoothDevice bluetoothDevice : set) {
          Utilities.escribeArchivo("MainActivity", "DEVICE NAME:", GlobalMembers.nameDevice);
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(bluetoothDevice.getName());
          stringBuilder.append(" ");
          stringBuilder.append(bluetoothDevice.getAddress());
          Utilities.escribeArchivo("MainActivity", "Conectando a:", stringBuilder.toString());
          if (bluetoothDevice.getName() != null && ManagerBluetooth.isValidDeviceName(bluetoothDevice.getName()).booleanValue()) {
            if (GlobalMembers.nameDevice.equals("")) {
              GlobalMembers.nameDevice = bluetoothAdapter.getRemoteDevice(bluetoothDevice.getAddress()).getName();
              if (mChatService == null)
                setupChatPlatinum(); 
              this.mConnectedDeviceName = bluetoothDevice;
              mChatService.connect(bluetoothDevice);
              break;
            } 
            if (mChatService == null)
              setupChatPlatinum(); 
            this.mConnectedDeviceName = bluetoothDevice;
            Utilities.isValidDevice(bluetoothDevice);
            mChatService.connect(bluetoothDevice);
            break;
          } 
        }  
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: getPlatinumConnection", exception.getMessage());
    } finally {
      Exception exception;
    } 
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
  }
  
  private void getTotalWPMessages() {
    int i = GlobalMembers.vecWaypoints.size();
    for (byte b = 1;; b++) {
      int j = b * 10;
      if (i <= j) {
        for (b = 0; b < j - i; b++)
          GlobalMembers.vecWaypoints.add("0"); 
        this.iTotalWPMessages = GlobalMembers.vecWaypoints.size() / 10;
        return;
      } 
    } 
  }
  
  private void getUpdateFile() {
    if (GlobalMembers.MAP_UPDATE_VERSION == GlobalMembers.MAP_UPDATE_WITH_PUSH)
      GlobalMembers.mapFileName = this.dbFun.getMapUpdateMapa().getFileName(); 
    if (GlobalMembers.mapFileName == null)
      return; 
    File file = UtilitiesFile.getFileFromStringFile((new GetHexDumpMap()).getMapUpdateFile(GlobalMembers.mapFileName));
    if (isBtAndPlatinumConnected())
      getmNPlatinum().sendTextForTTS(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_map_update_starting_map_update, 2131690043)); 
    if (file.exists()) {
      Utilities.validateMapUpdate(mContext, this.dbFun);
    } else {
      if (isBtAndPlatinumConnected())
        getmNPlatinum().sendTextForTTS(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_map_update_step_1_message, 2131690045)); 
      downloadUpdateFile();
    } 
  }
  
  public static ManagerNotificationPlatinum getmNPlatinum() {
    return mNPlatinum;
  }
  
  private void handlerResponse(List<Object> paramList, int paramInt, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/roadtrack/onstar/MainActivity.listCmdStat : Ljava/util/List;
    //   5: ifnull -> 2909
    //   8: getstatic com/roadtrack/onstar/MainActivity.listCmdStat : Ljava/util/List;
    //   11: invokeinterface size : ()I
    //   16: istore #4
    //   18: iload #4
    //   20: ifne -> 26
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: aload_0
    //   27: getstatic com/roadtrack/onstar/MainActivity.listCmdStat : Ljava/util/List;
    //   30: aload_0
    //   31: getfield currentAction : I
    //   34: invokeinterface get : (I)Ljava/lang/Object;
    //   39: checkcast com/roadtrack/onstar/VO/GetCommandStatusVO
    //   42: invokevirtual getButtonSelected : ()Landroid/view/View;
    //   45: putfield v : Landroid/view/View;
    //   48: getstatic com/roadtrack/onstar/MainActivity.onFindMe : Ljava/lang/Boolean;
    //   51: invokevirtual booleanValue : ()Z
    //   54: ifne -> 114
    //   57: aload_0
    //   58: getstatic com/roadtrack/onstar/MainActivity.listCmdStat : Ljava/util/List;
    //   61: iload_2
    //   62: invokeinterface get : (I)Ljava/lang/Object;
    //   67: checkcast com/roadtrack/onstar/VO/GetCommandStatusVO
    //   70: invokevirtual getButtonSelected : ()Landroid/view/View;
    //   73: putfield v : Landroid/view/View;
    //   76: aload_0
    //   77: aload_0
    //   78: getfield v : Landroid/view/View;
    //   81: ldc_w 2131296940
    //   84: invokevirtual findViewById : (I)Landroid/view/View;
    //   87: checkcast android/widget/ProgressBar
    //   90: putfield pb : Landroid/widget/ProgressBar;
    //   93: aload_1
    //   94: invokeinterface size : ()I
    //   99: ifne -> 114
    //   102: aload_0
    //   103: getfield pb : Landroid/widget/ProgressBar;
    //   106: bipush #8
    //   108: invokevirtual setVisibility : (I)V
    //   111: aload_0
    //   112: monitorexit
    //   113: return
    //   114: aload_1
    //   115: iconst_0
    //   116: invokeinterface get : (I)Ljava/lang/Object;
    //   121: checkcast java/lang/Integer
    //   124: invokevirtual intValue : ()I
    //   127: istore #4
    //   129: new java/lang/StringBuilder
    //   132: astore #8
    //   134: aload #8
    //   136: invokespecial <init> : ()V
    //   139: aload #8
    //   141: ldc_w 'response: '
    //   144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: pop
    //   148: aload #8
    //   150: iload #4
    //   152: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   155: pop
    //   156: ldc_w 'ACTIONS'
    //   159: ldc_w 'ACTIONS: FINDME GLIM:'
    //   162: aload #8
    //   164: invokevirtual toString : ()Ljava/lang/String;
    //   167: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   170: aload_0
    //   171: iload #4
    //   173: putfield idFromLastGetCommand : I
    //   176: iload #4
    //   178: istore #5
    //   180: getstatic com/roadtrack/onstar/MainActivity.onFollowMe : Ljava/lang/Boolean;
    //   183: invokevirtual booleanValue : ()Z
    //   186: ifeq -> 233
    //   189: iload #4
    //   191: istore #5
    //   193: iload #4
    //   195: ifeq -> 233
    //   198: iload #4
    //   200: istore #5
    //   202: iload #4
    //   204: iconst_3
    //   205: if_icmpeq -> 233
    //   208: iload #4
    //   210: istore #5
    //   212: iload #4
    //   214: bipush #15
    //   216: if_icmpeq -> 233
    //   219: iload #4
    //   221: istore #5
    //   223: iload #4
    //   225: bipush #16
    //   227: if_icmpeq -> 233
    //   230: iconst_0
    //   231: istore #5
    //   233: iload #5
    //   235: bipush #15
    //   237: if_icmpeq -> 1493
    //   240: iload #5
    //   242: iconst_2
    //   243: if_icmpeq -> 1493
    //   246: iload #5
    //   248: ifeq -> 1493
    //   251: getstatic com/roadtrack/onstar/utils/TcpClientV1.firstInSocket : Z
    //   254: ifne -> 1493
    //   257: iconst_1
    //   258: putstatic com/roadtrack/onstar/MainActivity.firstInWcf : Z
    //   261: invokestatic disconnect : ()V
    //   264: getstatic com/roadtrack/onstar/MainActivity.onFindMe : Ljava/lang/Boolean;
    //   267: invokevirtual booleanValue : ()Z
    //   270: ifeq -> 1139
    //   273: aload_0
    //   274: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   277: ldc_w 'MainActivity'
    //   280: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   283: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   286: astore #11
    //   288: aload_0
    //   289: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   292: ldc_w 'MainActivity'
    //   295: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   298: pop
    //   299: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   302: invokevirtual GetCode : ()I
    //   305: istore #7
    //   307: aload_0
    //   308: aload_0
    //   309: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   312: getfield navigation_lbl_localizame_gpssincobertura_1 : Ljava/lang/String;
    //   315: ldc_w 2131690158
    //   318: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   321: astore #9
    //   323: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   326: putstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   329: new com/roadtrack/onstar/BO/FindMePointsHandler
    //   332: invokespecial <init> : ()V
    //   335: aload #11
    //   337: invokestatic getDevicePoints : (Ljava/lang/String;)[Ljava/lang/String;
    //   340: astore #8
    //   342: aload #8
    //   344: iconst_1
    //   345: aaload
    //   346: astore #10
    //   348: aload #8
    //   350: iconst_0
    //   351: aaload
    //   352: astore #12
    //   354: aload #10
    //   356: ldc_w '0'
    //   359: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   362: ifeq -> 417
    //   365: aload #12
    //   367: ldc_w '0'
    //   370: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   373: ifeq -> 417
    //   376: aload_0
    //   377: aload_0
    //   378: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   381: getfield global_lbl_acciondescposiblenotif_1 : Ljava/lang/String;
    //   384: ldc_w 2131689867
    //   387: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   390: astore_1
    //   391: aload_0
    //   392: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   395: aload_1
    //   396: iconst_1
    //   397: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   400: invokevirtual show : ()V
    //   403: aload_0
    //   404: ldc_w '1'
    //   407: ldc_w 'title'
    //   410: aload_1
    //   411: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   414: goto -> 1091
    //   417: iload #5
    //   419: iconst_3
    //   420: if_icmpne -> 607
    //   423: aload_1
    //   424: iconst_4
    //   425: invokeinterface get : (I)Ljava/lang/Object;
    //   430: invokevirtual toString : ()Ljava/lang/String;
    //   433: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   436: invokevirtual intValue : ()I
    //   439: istore #4
    //   441: goto -> 625
    //   444: astore #8
    //   446: new java/lang/StringBuilder
    //   449: astore #13
    //   451: aload #13
    //   453: invokespecial <init> : ()V
    //   456: aload #13
    //   458: ldc_w 'Campo:'
    //   461: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   464: pop
    //   465: aload #13
    //   467: aload_1
    //   468: iconst_4
    //   469: invokeinterface get : (I)Ljava/lang/Object;
    //   474: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   477: pop
    //   478: aload #13
    //   480: ldc_w ' - '
    //   483: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: pop
    //   487: aload #13
    //   489: aload #8
    //   491: invokevirtual getMessage : ()Ljava/lang/String;
    //   494: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   497: pop
    //   498: ldc_w 'MainActivity'
    //   501: ldc_w 'Error gpsstatus'
    //   504: aload #13
    //   506: invokevirtual toString : ()Ljava/lang/String;
    //   509: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   512: aload_1
    //   513: iconst_1
    //   514: invokeinterface get : (I)Ljava/lang/Object;
    //   519: invokevirtual toString : ()Ljava/lang/String;
    //   522: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   525: invokevirtual intValue : ()I
    //   528: istore #4
    //   530: goto -> 625
    //   533: astore #13
    //   535: new java/lang/StringBuilder
    //   538: astore #8
    //   540: aload #8
    //   542: invokespecial <init> : ()V
    //   545: aload #8
    //   547: ldc_w 'Campo:'
    //   550: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: pop
    //   554: aload #8
    //   556: aload_1
    //   557: iconst_1
    //   558: invokeinterface get : (I)Ljava/lang/Object;
    //   563: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   566: pop
    //   567: aload #8
    //   569: ldc_w ' - '
    //   572: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: pop
    //   576: aload #8
    //   578: aload #13
    //   580: invokevirtual getMessage : ()Ljava/lang/String;
    //   583: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   586: pop
    //   587: ldc_w 'MainActivity'
    //   590: ldc_w 'Error gpsstatus'
    //   593: aload #8
    //   595: invokevirtual toString : ()Ljava/lang/String;
    //   598: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   601: iconst_3
    //   602: istore #4
    //   604: goto -> 625
    //   607: aload_1
    //   608: iconst_1
    //   609: invokeinterface get : (I)Ljava/lang/Object;
    //   614: invokevirtual toString : ()Ljava/lang/String;
    //   617: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   620: invokevirtual intValue : ()I
    //   623: istore #4
    //   625: iload #4
    //   627: iconst_2
    //   628: if_icmpne -> 641
    //   631: iload #4
    //   633: istore #6
    //   635: iload #4
    //   637: iconst_3
    //   638: if_icmpeq -> 644
    //   641: iconst_3
    //   642: istore #6
    //   644: aload_1
    //   645: ifnull -> 675
    //   648: aload_1
    //   649: invokeinterface size : ()I
    //   654: ifle -> 675
    //   657: ldc_w 'MainActivity'
    //   660: ldc_w 'Status'
    //   663: aload_1
    //   664: invokeinterface toArray : ()[Ljava/lang/Object;
    //   669: invokestatic toString : ([Ljava/lang/Object;)Ljava/lang/String;
    //   672: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   675: aload_0
    //   676: getfield deviceId : Ljava/lang/String;
    //   679: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   682: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   685: astore #8
    //   687: aload_1
    //   688: iconst_3
    //   689: invokeinterface get : (I)Ljava/lang/Object;
    //   694: invokevirtual toString : ()Ljava/lang/String;
    //   697: invokevirtual length : ()I
    //   700: ifle -> 715
    //   703: aload_1
    //   704: iconst_3
    //   705: invokeinterface get : (I)Ljava/lang/Object;
    //   710: invokevirtual toString : ()Ljava/lang/String;
    //   713: astore #8
    //   715: aload_0
    //   716: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   719: iconst_1
    //   720: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   723: aload_0
    //   724: getfield lIDFindMe : J
    //   727: invokestatic toString : (J)Ljava/lang/String;
    //   730: ldc_w '1'
    //   733: aload_1
    //   734: iconst_1
    //   735: invokeinterface get : (I)Ljava/lang/Object;
    //   740: invokevirtual toString : ()Ljava/lang/String;
    //   743: aload_1
    //   744: iconst_2
    //   745: invokeinterface get : (I)Ljava/lang/Object;
    //   750: invokevirtual toString : ()Ljava/lang/String;
    //   753: aload #8
    //   755: aload_3
    //   756: iload #5
    //   758: invokestatic valueOf : (I)Ljava/lang/String;
    //   761: ldc_w '0'
    //   764: ldc_w '0'
    //   767: ldc_w '0'
    //   770: iload #6
    //   772: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   775: invokevirtual GetCode : ()I
    //   778: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   781: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   784: iconst_0
    //   785: aload_0
    //   786: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   789: ldc_w 'MainActivity'
    //   792: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   795: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   798: aload #10
    //   800: aload #12
    //   802: ldc_w ''
    //   805: invokestatic saveNotification : (Landroid/content/Context;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   808: getstatic com/roadtrack/onstar/Enums$SettingsPreference.statusNotifications : Lcom/roadtrack/onstar/Enums$SettingsPreference;
    //   811: iconst_0
    //   812: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   815: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   818: invokestatic GetValuePreference : (Lcom/roadtrack/onstar/Enums$SettingsPreference;Ljava/lang/Object;Landroid/content/Context;)Ljava/lang/Object;
    //   821: checkcast java/lang/Boolean
    //   824: invokevirtual booleanValue : ()Z
    //   827: ifeq -> 931
    //   830: aload_0
    //   831: aload_0
    //   832: getfield viewNotifications : Landroid/view/View;
    //   835: ldc_w 2131297104
    //   838: invokevirtual findViewById : (I)Landroid/view/View;
    //   841: checkcast android/widget/TextView
    //   844: putfield imgNotification : Landroid/widget/TextView;
    //   847: getstatic com/roadtrack/onstar/BO/GlobalMembers.notificaciones : I
    //   850: getstatic com/roadtrack/onstar/BO/GlobalMembers.notificacionesDTC : I
    //   853: iadd
    //   854: getstatic com/roadtrack/onstar/BO/GlobalMembers.notificacionesAgendamiento : I
    //   857: iadd
    //   858: putstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   861: getstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   864: bipush #50
    //   866: if_icmple -> 876
    //   869: bipush #50
    //   871: istore #4
    //   873: goto -> 881
    //   876: getstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   879: istore #4
    //   881: iload #4
    //   883: putstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   886: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   889: getstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   892: getstatic com/roadtrack/onstar/BO/GlobalMembers.redPoint : Z
    //   895: invokestatic paintRedPoint : (Landroid/content/Context;IZ)Landroid/graphics/Bitmap;
    //   898: astore #13
    //   900: aload_0
    //   901: getfield imgNotification : Landroid/widget/TextView;
    //   904: astore #8
    //   906: new android/graphics/drawable/BitmapDrawable
    //   909: astore_3
    //   910: aload_3
    //   911: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   914: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   917: aload #13
    //   919: invokespecial <init> : (Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
    //   922: aload #8
    //   924: aconst_null
    //   925: aload_3
    //   926: aconst_null
    //   927: aconst_null
    //   928: invokevirtual setCompoundDrawablesWithIntrinsicBounds : (Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V
    //   931: aload_0
    //   932: aload_0
    //   933: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   936: getfield global_popup_btn_ok_1 : Ljava/lang/String;
    //   939: ldc_w 2131689950
    //   942: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   945: astore_3
    //   946: aload #9
    //   948: putstatic com/roadtrack/onstar/BO/GlobalMembers.lastNavParamsFindMe : Ljava/lang/String;
    //   951: new com/roadtrack/onstar/VO/GoogleMapVO
    //   954: astore #8
    //   956: aload #8
    //   958: invokespecial <init> : ()V
    //   961: aload #8
    //   963: getstatic com/roadtrack/onstar/Enums$navigationProcess.External : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   966: invokevirtual setKEY_SET_ENGINE_SOURCE : (Ljava/io/Serializable;)V
    //   969: aload #8
    //   971: aload #11
    //   973: invokevirtual setKEY_SET_NAV_DEVICE : (Ljava/lang/String;)V
    //   976: aload #8
    //   978: iload #7
    //   980: invokestatic valueOf : (I)Ljava/lang/String;
    //   983: invokevirtual setKEY_SET_NAV_ACTION : (Ljava/lang/String;)V
    //   986: aload #8
    //   988: aload #9
    //   990: invokevirtual setKEY_SET_NAV_PARAMS : (Ljava/lang/String;)V
    //   993: aload #8
    //   995: aload #10
    //   997: invokevirtual setKEY_EXTERNAL_LAT : (Ljava/lang/String;)V
    //   1000: aload #8
    //   1002: aload #12
    //   1004: invokevirtual setKEY_EXTERNAL_LNG : (Ljava/lang/String;)V
    //   1007: new java/lang/StringBuilder
    //   1010: astore #10
    //   1012: aload #10
    //   1014: invokespecial <init> : ()V
    //   1017: aload #10
    //   1019: ldc_w ''
    //   1022: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1025: pop
    //   1026: aload #10
    //   1028: aload_1
    //   1029: iconst_3
    //   1030: invokeinterface get : (I)Ljava/lang/Object;
    //   1035: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1038: pop
    //   1039: aload #8
    //   1041: aload #10
    //   1043: invokevirtual toString : ()Ljava/lang/String;
    //   1046: invokevirtual setLASTKNOWDATE : (Ljava/lang/String;)V
    //   1049: iload #6
    //   1051: iconst_2
    //   1052: if_icmpne -> 1060
    //   1055: aload_3
    //   1056: astore_1
    //   1057: goto -> 1063
    //   1060: aload #9
    //   1062: astore_1
    //   1063: aload #8
    //   1065: aload_1
    //   1066: invokevirtual setKEY_GPSSTATUS : (Ljava/lang/String;)V
    //   1069: new com/roadtrack/onstar/MainActivity$32
    //   1072: astore_1
    //   1073: aload_1
    //   1074: aload_0
    //   1075: ldc2_w 1000
    //   1078: ldc2_w 1000
    //   1081: aload #8
    //   1083: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity;JJLcom/roadtrack/onstar/VO/GoogleMapVO;)V
    //   1086: aload_1
    //   1087: invokevirtual start : ()Landroid/os/CountDownTimer;
    //   1090: pop
    //   1091: aload_0
    //   1092: getfield findMeProBar : Landroid/widget/ProgressBar;
    //   1095: ifnull -> 1107
    //   1098: aload_0
    //   1099: getfield findMeProBar : Landroid/widget/ProgressBar;
    //   1102: bipush #8
    //   1104: invokevirtual setVisibility : (I)V
    //   1107: aload_0
    //   1108: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1111: ldc_w 'MainActivity'
    //   1114: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   1117: invokevirtual getName : ()Ljava/lang/String;
    //   1120: ldc_w '1'
    //   1123: ldc_w '2'
    //   1126: invokestatic logActions : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1129: iconst_0
    //   1130: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1133: putstatic com/roadtrack/onstar/MainActivity.onFindMe : Ljava/lang/Boolean;
    //   1136: goto -> 1401
    //   1139: getstatic com/roadtrack/onstar/BO/GlobalMembers.onFollowMeRun : Ljava/lang/Boolean;
    //   1142: invokevirtual booleanValue : ()Z
    //   1145: ifeq -> 1376
    //   1148: aload_1
    //   1149: iconst_4
    //   1150: invokeinterface get : (I)Ljava/lang/Object;
    //   1155: invokevirtual toString : ()Ljava/lang/String;
    //   1158: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   1161: invokevirtual intValue : ()I
    //   1164: istore #4
    //   1166: aload_0
    //   1167: getfield deviceId : Ljava/lang/String;
    //   1170: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   1173: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   1176: astore #10
    //   1178: aload_0
    //   1179: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1182: astore #9
    //   1184: aload_0
    //   1185: getfield idInsertControl : J
    //   1188: invokestatic toString : (J)Ljava/lang/String;
    //   1191: astore #8
    //   1193: aload_1
    //   1194: iconst_2
    //   1195: invokeinterface get : (I)Ljava/lang/Object;
    //   1200: invokevirtual toString : ()Ljava/lang/String;
    //   1203: astore_3
    //   1204: aload_1
    //   1205: iconst_3
    //   1206: invokeinterface get : (I)Ljava/lang/Object;
    //   1211: invokevirtual toString : ()Ljava/lang/String;
    //   1214: astore_1
    //   1215: new java/lang/StringBuilder
    //   1218: astore #11
    //   1220: aload #11
    //   1222: invokespecial <init> : ()V
    //   1225: aload #11
    //   1227: ldc_w ''
    //   1230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1233: pop
    //   1234: aload #11
    //   1236: iload #5
    //   1238: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1241: pop
    //   1242: aload #9
    //   1244: iconst_1
    //   1245: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1248: aload #8
    //   1250: ldc_w '2'
    //   1253: aload_3
    //   1254: aload_1
    //   1255: aload #10
    //   1257: ldc_w ''
    //   1260: aload #11
    //   1262: invokevirtual toString : ()Ljava/lang/String;
    //   1265: ldc_w '0'
    //   1268: ldc_w '0'
    //   1271: ldc_w '0'
    //   1274: iload #4
    //   1276: getstatic com/roadtrack/onstar/Enums$Services.FollowMe : Lcom/roadtrack/onstar/Enums$Services;
    //   1279: invokevirtual GetCode : ()I
    //   1282: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   1285: iconst_0
    //   1286: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1289: putstatic com/roadtrack/onstar/MainActivity.onFollowMe : Ljava/lang/Boolean;
    //   1292: iconst_0
    //   1293: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1296: putstatic com/roadtrack/onstar/BO/GlobalMembers.onFollowMeRun : Ljava/lang/Boolean;
    //   1299: aload_0
    //   1300: aload_0
    //   1301: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   1304: getfield main_activity_unable_to_get_coordinates : Ljava/lang/String;
    //   1307: ldc_w 2131690064
    //   1310: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1313: astore_1
    //   1314: aload_0
    //   1315: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1318: aload_1
    //   1319: iconst_1
    //   1320: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1323: invokevirtual show : ()V
    //   1326: aload_0
    //   1327: getfield FollowmegenericProgressBar : Landroid/widget/ProgressBar;
    //   1330: ifnull -> 1342
    //   1333: aload_0
    //   1334: getfield FollowmegenericProgressBar : Landroid/widget/ProgressBar;
    //   1337: bipush #8
    //   1339: invokevirtual setVisibility : (I)V
    //   1342: aload_0
    //   1343: aload_0
    //   1344: getfield FollowmegenericView : Landroid/view/View;
    //   1347: iconst_0
    //   1348: invokespecial asignImage : (Landroid/view/View;I)V
    //   1351: aload_0
    //   1352: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1355: ldc_w 'MainActivity'
    //   1358: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   1361: invokevirtual getName : ()Ljava/lang/String;
    //   1364: ldc_w '2'
    //   1367: ldc_w '2'
    //   1370: invokestatic logActions : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1373: goto -> 1401
    //   1376: aload_0
    //   1377: getfield pb : Landroid/widget/ProgressBar;
    //   1380: ifnull -> 1392
    //   1383: aload_0
    //   1384: getfield pb : Landroid/widget/ProgressBar;
    //   1387: bipush #8
    //   1389: invokevirtual setVisibility : (I)V
    //   1392: aload_0
    //   1393: aload_0
    //   1394: getfield v : Landroid/view/View;
    //   1397: iconst_0
    //   1398: invokespecial asignImage : (Landroid/view/View;I)V
    //   1401: invokestatic AlertOn : ()V
    //   1404: new java/lang/StringBuilder
    //   1407: astore_1
    //   1408: aload_1
    //   1409: invokespecial <init> : ()V
    //   1412: aload_1
    //   1413: aload_0
    //   1414: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1417: invokevirtual getDeviceTypeId : ()Ljava/lang/String;
    //   1420: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1423: pop
    //   1424: aload_1
    //   1425: ldc_w '_ID'
    //   1428: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1431: pop
    //   1432: aload_1
    //   1433: invokevirtual toString : ()Ljava/lang/String;
    //   1436: iconst_1
    //   1437: aload_0
    //   1438: invokestatic SetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   1441: pop
    //   1442: aload_0
    //   1443: ldc2_w -1
    //   1446: putfield idInsertControl : J
    //   1449: aload_0
    //   1450: getstatic com/roadtrack/onstar/MainActivity.listCmdStat : Ljava/util/List;
    //   1453: iload_2
    //   1454: invokeinterface get : (I)Ljava/lang/Object;
    //   1459: checkcast com/roadtrack/onstar/VO/GetCommandStatusVO
    //   1462: invokevirtual getActionId : ()Ljava/lang/String;
    //   1465: invokespecial valueIdAction : (Ljava/lang/String;)V
    //   1468: iload_2
    //   1469: iconst_m1
    //   1470: if_icmpeq -> 1483
    //   1473: getstatic com/roadtrack/onstar/MainActivity.listCmdStat : Ljava/util/List;
    //   1476: iload_2
    //   1477: invokeinterface remove : (I)Ljava/lang/Object;
    //   1482: pop
    //   1483: aload_0
    //   1484: getfield act : Landroid/app/Activity;
    //   1487: invokestatic unlockOrientation : (Landroid/app/Activity;)V
    //   1490: aload_0
    //   1491: monitorexit
    //   1492: return
    //   1493: iload #5
    //   1495: bipush #15
    //   1497: if_icmpeq -> 1506
    //   1500: iload #5
    //   1502: iconst_2
    //   1503: if_icmpne -> 2906
    //   1506: getstatic com/roadtrack/onstar/utils/TcpClientV1.firstInSocket : Z
    //   1509: ifne -> 2906
    //   1512: iconst_1
    //   1513: putstatic com/roadtrack/onstar/MainActivity.firstInWcf : Z
    //   1516: invokestatic disconnect : ()V
    //   1519: new com/roadtrack/onstar/DAO/DBFunctions
    //   1522: astore #8
    //   1524: aload #8
    //   1526: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   1529: invokespecial <init> : (Landroid/content/Context;)V
    //   1532: aload_0
    //   1533: aload #8
    //   1535: putfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1538: getstatic com/roadtrack/onstar/MainActivity.onFindMe : Ljava/lang/Boolean;
    //   1541: invokevirtual booleanValue : ()Z
    //   1544: ifeq -> 2328
    //   1547: aload_1
    //   1548: invokeinterface size : ()I
    //   1553: iconst_4
    //   1554: if_icmplt -> 1694
    //   1557: aload_1
    //   1558: iconst_4
    //   1559: invokeinterface get : (I)Ljava/lang/Object;
    //   1564: invokevirtual toString : ()Ljava/lang/String;
    //   1567: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   1570: invokevirtual intValue : ()I
    //   1573: istore #4
    //   1575: aload_0
    //   1576: getfield deviceId : Ljava/lang/String;
    //   1579: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   1582: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   1585: astore #8
    //   1587: aload_0
    //   1588: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   1591: iconst_1
    //   1592: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1595: aload_0
    //   1596: getfield lIDFindMe : J
    //   1599: invokestatic toString : (J)Ljava/lang/String;
    //   1602: ldc_w '1'
    //   1605: aload_1
    //   1606: iconst_1
    //   1607: invokeinterface get : (I)Ljava/lang/Object;
    //   1612: invokevirtual toString : ()Ljava/lang/String;
    //   1615: aload_1
    //   1616: iconst_2
    //   1617: invokeinterface get : (I)Ljava/lang/Object;
    //   1622: invokevirtual toString : ()Ljava/lang/String;
    //   1625: aload #8
    //   1627: aload_3
    //   1628: iload #5
    //   1630: invokestatic valueOf : (I)Ljava/lang/String;
    //   1633: ldc_w '0'
    //   1636: ldc_w '0'
    //   1639: ldc_w '0'
    //   1642: iload #4
    //   1644: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   1647: invokevirtual GetCode : ()I
    //   1650: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   1653: invokestatic AlertOn : ()V
    //   1656: new java/lang/StringBuilder
    //   1659: astore_3
    //   1660: aload_3
    //   1661: invokespecial <init> : ()V
    //   1664: aload_3
    //   1665: aload_0
    //   1666: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1669: invokevirtual getDeviceTypeId : ()Ljava/lang/String;
    //   1672: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1675: pop
    //   1676: aload_3
    //   1677: ldc_w '_ID'
    //   1680: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1683: pop
    //   1684: aload_3
    //   1685: invokevirtual toString : ()Ljava/lang/String;
    //   1688: iconst_1
    //   1689: aload_0
    //   1690: invokestatic SetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   1693: pop
    //   1694: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   1697: iconst_0
    //   1698: aload_0
    //   1699: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1702: ldc_w 'MainActivity'
    //   1705: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   1708: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1711: aload_1
    //   1712: iconst_1
    //   1713: invokeinterface get : (I)Ljava/lang/Object;
    //   1718: invokevirtual toString : ()Ljava/lang/String;
    //   1721: aload_1
    //   1722: iconst_2
    //   1723: invokeinterface get : (I)Ljava/lang/Object;
    //   1728: invokevirtual toString : ()Ljava/lang/String;
    //   1731: ldc_w ''
    //   1734: invokestatic saveNotification : (Landroid/content/Context;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1737: getstatic com/roadtrack/onstar/Enums$SettingsPreference.statusNotifications : Lcom/roadtrack/onstar/Enums$SettingsPreference;
    //   1740: iconst_0
    //   1741: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1744: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   1747: invokestatic GetValuePreference : (Lcom/roadtrack/onstar/Enums$SettingsPreference;Ljava/lang/Object;Landroid/content/Context;)Ljava/lang/Object;
    //   1750: checkcast java/lang/Boolean
    //   1753: invokevirtual booleanValue : ()Z
    //   1756: ifeq -> 1864
    //   1759: aload_0
    //   1760: aload_0
    //   1761: getfield viewNotifications : Landroid/view/View;
    //   1764: ldc_w 2131297104
    //   1767: invokevirtual findViewById : (I)Landroid/view/View;
    //   1770: checkcast android/widget/TextView
    //   1773: putfield imgNotification : Landroid/widget/TextView;
    //   1776: getstatic com/roadtrack/onstar/BO/GlobalMembers.notificaciones : I
    //   1779: getstatic com/roadtrack/onstar/BO/GlobalMembers.notificacionesDTC : I
    //   1782: iadd
    //   1783: getstatic com/roadtrack/onstar/BO/GlobalMembers.notificacionesAgendamiento : I
    //   1786: iadd
    //   1787: putstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   1790: getstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   1793: istore #5
    //   1795: bipush #50
    //   1797: istore #4
    //   1799: iload #5
    //   1801: bipush #50
    //   1803: if_icmple -> 1809
    //   1806: goto -> 1814
    //   1809: getstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   1812: istore #4
    //   1814: iload #4
    //   1816: putstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   1819: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   1822: getstatic com/roadtrack/onstar/BO/GlobalMembers.totalNotificaciones : I
    //   1825: getstatic com/roadtrack/onstar/BO/GlobalMembers.redPoint : Z
    //   1828: invokestatic paintRedPoint : (Landroid/content/Context;IZ)Landroid/graphics/Bitmap;
    //   1831: astore #9
    //   1833: aload_0
    //   1834: getfield imgNotification : Landroid/widget/TextView;
    //   1837: astore #8
    //   1839: new android/graphics/drawable/BitmapDrawable
    //   1842: astore_3
    //   1843: aload_3
    //   1844: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   1847: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   1850: aload #9
    //   1852: invokespecial <init> : (Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
    //   1855: aload #8
    //   1857: aconst_null
    //   1858: aload_3
    //   1859: aconst_null
    //   1860: aconst_null
    //   1861: invokevirtual setCompoundDrawablesWithIntrinsicBounds : (Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V
    //   1864: aload_0
    //   1865: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1868: ldc_w 'MainActivity'
    //   1871: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   1874: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1877: astore #10
    //   1879: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
    //   1882: invokevirtual GetCode : ()I
    //   1885: istore #4
    //   1887: ldc_w ''
    //   1890: astore #8
    //   1892: aload_1
    //   1893: iconst_1
    //   1894: invokeinterface get : (I)Ljava/lang/Object;
    //   1899: invokevirtual toString : ()Ljava/lang/String;
    //   1902: astore #9
    //   1904: aload_1
    //   1905: iconst_2
    //   1906: invokeinterface get : (I)Ljava/lang/Object;
    //   1911: invokevirtual toString : ()Ljava/lang/String;
    //   1914: astore #11
    //   1916: getstatic com/roadtrack/onstar/BO/GlobalMembers.bFinalizaronIntentos : Z
    //   1919: ifne -> 1975
    //   1922: getstatic com/roadtrack/onstar/BO/GlobalMembers.findMeGLIM : Z
    //   1925: ifeq -> 1931
    //   1928: goto -> 1975
    //   1931: aload_0
    //   1932: aload_0
    //   1933: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   1936: getfield global_popup_btn_ok_1 : Ljava/lang/String;
    //   1939: ldc_w 2131689950
    //   1942: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1945: astore_3
    //   1946: aload_0
    //   1947: aload_0
    //   1948: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   1951: getfield navigation_lbl_localizame_gpssincobertura_1 : Ljava/lang/String;
    //   1954: ldc_w 2131690158
    //   1957: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   1960: astore #8
    //   1962: getstatic com/roadtrack/onstar/BO/GlobalMembers.findmeCode : I
    //   1965: iconst_2
    //   1966: if_icmpeq -> 2098
    //   1969: aload #8
    //   1971: astore_3
    //   1972: goto -> 2098
    //   1975: getstatic com/roadtrack/onstar/BO/GlobalMembers.findmeCode : I
    //   1978: iconst_2
    //   1979: if_icmpne -> 2044
    //   1982: aload #9
    //   1984: ldc_w '0'
    //   1987: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   1990: ifne -> 2044
    //   1993: aload #11
    //   1995: ldc_w '0'
    //   1998: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   2001: ifne -> 2044
    //   2004: new java/lang/StringBuilder
    //   2007: astore_3
    //   2008: aload_3
    //   2009: invokespecial <init> : ()V
    //   2012: aload_3
    //   2013: ldc_w ''
    //   2016: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2019: pop
    //   2020: aload_3
    //   2021: aload_1
    //   2022: iconst_3
    //   2023: invokeinterface get : (I)Ljava/lang/Object;
    //   2028: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2031: pop
    //   2032: aload_3
    //   2033: invokevirtual toString : ()Ljava/lang/String;
    //   2036: aload_0
    //   2037: invokestatic getRealPositionFindMe : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   2040: astore_3
    //   2041: goto -> 2094
    //   2044: aload #8
    //   2046: astore_3
    //   2047: getstatic com/roadtrack/onstar/BO/GlobalMembers.findmeCode : I
    //   2050: iconst_2
    //   2051: if_icmpne -> 2094
    //   2054: aload #9
    //   2056: ldc_w '0'
    //   2059: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   2062: ifne -> 2079
    //   2065: aload #8
    //   2067: astore_3
    //   2068: aload #11
    //   2070: ldc_w '0'
    //   2073: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   2076: ifeq -> 2094
    //   2079: aload_0
    //   2080: aload_0
    //   2081: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   2084: getfield navigation_lbl_localizame_gpssincobertura_1 : Ljava/lang/String;
    //   2087: ldc_w 2131690158
    //   2090: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   2093: astore_3
    //   2094: iconst_0
    //   2095: putstatic com/roadtrack/onstar/BO/GlobalMembers.findMeGLIM : Z
    //   2098: new com/roadtrack/onstar/VO/GoogleMapVO
    //   2101: astore #8
    //   2103: aload #8
    //   2105: invokespecial <init> : ()V
    //   2108: aload #8
    //   2110: getstatic com/roadtrack/onstar/Enums$navigationProcess.External : Lcom/roadtrack/onstar/Enums$navigationProcess;
    //   2113: invokevirtual setKEY_SET_ENGINE_SOURCE : (Ljava/io/Serializable;)V
    //   2116: aload #8
    //   2118: aload #10
    //   2120: invokevirtual setKEY_SET_NAV_DEVICE : (Ljava/lang/String;)V
    //   2123: aload #8
    //   2125: iload #4
    //   2127: invokestatic valueOf : (I)Ljava/lang/String;
    //   2130: invokevirtual setKEY_SET_NAV_ACTION : (Ljava/lang/String;)V
    //   2133: aload #8
    //   2135: aload_3
    //   2136: invokevirtual setKEY_SET_NAV_PARAMS : (Ljava/lang/String;)V
    //   2139: aload #8
    //   2141: aload #9
    //   2143: invokevirtual setKEY_EXTERNAL_LAT : (Ljava/lang/String;)V
    //   2146: aload #8
    //   2148: aload #11
    //   2150: invokevirtual setKEY_EXTERNAL_LNG : (Ljava/lang/String;)V
    //   2153: new java/lang/StringBuilder
    //   2156: astore #12
    //   2158: aload #12
    //   2160: invokespecial <init> : ()V
    //   2163: aload #12
    //   2165: ldc_w ''
    //   2168: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2171: pop
    //   2172: aload #12
    //   2174: aload_1
    //   2175: iconst_3
    //   2176: invokeinterface get : (I)Ljava/lang/Object;
    //   2181: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2184: pop
    //   2185: aload #8
    //   2187: aload #12
    //   2189: invokevirtual toString : ()Ljava/lang/String;
    //   2192: invokevirtual setLASTKNOWDATE : (Ljava/lang/String;)V
    //   2195: aload #8
    //   2197: aload_3
    //   2198: invokevirtual setKEY_GPSSTATUS : (Ljava/lang/String;)V
    //   2201: aload_3
    //   2202: putstatic com/roadtrack/onstar/BO/GlobalMembers.lastNavParamsFindMe : Ljava/lang/String;
    //   2205: getstatic com/roadtrack/onstar/MainActivity.mContext : Landroid/content/Context;
    //   2208: putstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   2211: new com/roadtrack/onstar/BO/FindMePointsHandler
    //   2214: astore_3
    //   2215: aload_3
    //   2216: invokespecial <init> : ()V
    //   2219: aload_3
    //   2220: aload #10
    //   2222: aload #9
    //   2224: aload #11
    //   2226: invokevirtual insertFindmePoint : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2229: aload #9
    //   2231: ldc_w '0'
    //   2234: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2237: ifeq -> 2258
    //   2240: aload #11
    //   2242: ldc_w '0'
    //   2245: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2248: ifeq -> 2258
    //   2251: aload_0
    //   2252: invokevirtual getUltimeFindme : ()V
    //   2255: goto -> 2280
    //   2258: new com/roadtrack/onstar/MainActivity$33
    //   2261: astore_3
    //   2262: aload_3
    //   2263: aload_0
    //   2264: ldc2_w 1000
    //   2267: ldc2_w 1000
    //   2270: aload #8
    //   2272: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity;JJLcom/roadtrack/onstar/VO/GoogleMapVO;)V
    //   2275: aload_3
    //   2276: invokevirtual start : ()Landroid/os/CountDownTimer;
    //   2279: pop
    //   2280: aload_0
    //   2281: getfield findMeProBar : Landroid/widget/ProgressBar;
    //   2284: ifnull -> 2296
    //   2287: aload_0
    //   2288: getfield findMeProBar : Landroid/widget/ProgressBar;
    //   2291: bipush #8
    //   2293: invokevirtual setVisibility : (I)V
    //   2296: aload_0
    //   2297: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   2300: ldc_w 'MainActivity'
    //   2303: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   2306: invokevirtual getName : ()Ljava/lang/String;
    //   2309: ldc_w '1'
    //   2312: ldc_w '2'
    //   2315: invokestatic logActions : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2318: iconst_0
    //   2319: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   2322: putstatic com/roadtrack/onstar/MainActivity.onFindMe : Ljava/lang/Boolean;
    //   2325: goto -> 2807
    //   2328: getstatic com/roadtrack/onstar/BO/GlobalMembers.onFollowMeRun : Ljava/lang/Boolean;
    //   2331: invokevirtual booleanValue : ()Z
    //   2334: ifeq -> 2782
    //   2337: aload_0
    //   2338: getfield other : Z
    //   2341: ifeq -> 2782
    //   2344: aload_1
    //   2345: invokeinterface size : ()I
    //   2350: iconst_4
    //   2351: if_icmplt -> 2533
    //   2354: aload_1
    //   2355: iconst_4
    //   2356: invokeinterface get : (I)Ljava/lang/Object;
    //   2361: invokevirtual toString : ()Ljava/lang/String;
    //   2364: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   2367: invokevirtual intValue : ()I
    //   2370: istore #4
    //   2372: aload_0
    //   2373: getfield deviceId : Ljava/lang/String;
    //   2376: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
    //   2379: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
    //   2382: astore #12
    //   2384: aload_0
    //   2385: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   2388: astore_3
    //   2389: getstatic com/roadtrack/onstar/BO/GlobalMembers.followMeIdOnDB : J
    //   2392: invokestatic toString : (J)Ljava/lang/String;
    //   2395: astore #11
    //   2397: aload_1
    //   2398: iconst_2
    //   2399: invokeinterface get : (I)Ljava/lang/Object;
    //   2404: invokevirtual toString : ()Ljava/lang/String;
    //   2407: astore #9
    //   2409: aload_1
    //   2410: iconst_3
    //   2411: invokeinterface get : (I)Ljava/lang/Object;
    //   2416: invokevirtual toString : ()Ljava/lang/String;
    //   2419: astore #8
    //   2421: new java/lang/StringBuilder
    //   2424: astore #10
    //   2426: aload #10
    //   2428: invokespecial <init> : ()V
    //   2431: aload #10
    //   2433: ldc_w ''
    //   2436: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2439: pop
    //   2440: aload #10
    //   2442: iload #5
    //   2444: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   2447: pop
    //   2448: aload_3
    //   2449: iconst_1
    //   2450: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   2453: aload #11
    //   2455: ldc_w '1'
    //   2458: aload #9
    //   2460: aload #8
    //   2462: aload #12
    //   2464: ldc_w ''
    //   2467: aload #10
    //   2469: invokevirtual toString : ()Ljava/lang/String;
    //   2472: ldc_w '0'
    //   2475: ldc_w '0'
    //   2478: ldc_w '0'
    //   2481: iload #4
    //   2483: getstatic com/roadtrack/onstar/Enums$Services.FollowMe : Lcom/roadtrack/onstar/Enums$Services;
    //   2486: invokevirtual GetCode : ()I
    //   2489: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
    //   2492: invokestatic AlertOn : ()V
    //   2495: new java/lang/StringBuilder
    //   2498: astore_3
    //   2499: aload_3
    //   2500: invokespecial <init> : ()V
    //   2503: aload_3
    //   2504: aload_0
    //   2505: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   2508: invokevirtual getDeviceTypeId : ()Ljava/lang/String;
    //   2511: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2514: pop
    //   2515: aload_3
    //   2516: ldc_w '_ID'
    //   2519: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2522: pop
    //   2523: aload_3
    //   2524: invokevirtual toString : ()Ljava/lang/String;
    //   2527: iconst_1
    //   2528: aload_0
    //   2529: invokestatic SetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   2532: pop
    //   2533: invokestatic deleteAllFollowmePointsSpecial : ()V
    //   2536: aload_0
    //   2537: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   2540: getstatic com/roadtrack/onstar/BO/GlobalMembers.followMeIdOnDB : J
    //   2543: l2i
    //   2544: aload_0
    //   2545: getfield deviceId : Ljava/lang/String;
    //   2548: aload_1
    //   2549: iconst_2
    //   2550: invokeinterface get : (I)Ljava/lang/Object;
    //   2555: invokevirtual toString : ()Ljava/lang/String;
    //   2558: aload_1
    //   2559: iconst_3
    //   2560: invokeinterface get : (I)Ljava/lang/Object;
    //   2565: invokevirtual toString : ()Ljava/lang/String;
    //   2568: aload_1
    //   2569: iconst_4
    //   2570: invokeinterface get : (I)Ljava/lang/Object;
    //   2575: invokevirtual toString : ()Ljava/lang/String;
    //   2578: invokevirtual addFollowMePush : (ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)J
    //   2581: lconst_0
    //   2582: lcmp
    //   2583: ifle -> 2635
    //   2586: iconst_2
    //   2587: anewarray java/lang/Double
    //   2590: putstatic com/roadtrack/onstar/BO/GlobalMembers.lastFollowMePoint : [Ljava/lang/Double;
    //   2593: getstatic com/roadtrack/onstar/BO/GlobalMembers.lastFollowMePoint : [Ljava/lang/Double;
    //   2596: iconst_0
    //   2597: aload_1
    //   2598: iconst_2
    //   2599: invokeinterface get : (I)Ljava/lang/Object;
    //   2604: invokevirtual toString : ()Ljava/lang/String;
    //   2607: invokestatic parseDouble : (Ljava/lang/String;)D
    //   2610: invokestatic valueOf : (D)Ljava/lang/Double;
    //   2613: aastore
    //   2614: getstatic com/roadtrack/onstar/BO/GlobalMembers.lastFollowMePoint : [Ljava/lang/Double;
    //   2617: iconst_1
    //   2618: aload_1
    //   2619: iconst_3
    //   2620: invokeinterface get : (I)Ljava/lang/Object;
    //   2625: invokevirtual toString : ()Ljava/lang/String;
    //   2628: invokestatic parseDouble : (Ljava/lang/String;)D
    //   2631: invokestatic valueOf : (D)Ljava/lang/Double;
    //   2634: aastore
    //   2635: aload_0
    //   2636: aload_0
    //   2637: getfield v : Landroid/view/View;
    //   2640: ldc_w 2131296611
    //   2643: invokevirtual findViewById : (I)Landroid/view/View;
    //   2646: checkcast android/widget/TextView
    //   2649: putfield imgResponse : Landroid/widget/TextView;
    //   2652: aload_0
    //   2653: getfield imgResponse : Landroid/widget/TextView;
    //   2656: iconst_0
    //   2657: invokevirtual setVisibility : (I)V
    //   2660: aload_0
    //   2661: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.palomita_2azul_executado_fondo : Ljava/lang/String;
    //   2664: ldc_w 2131165620
    //   2667: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   2670: astore_3
    //   2671: aload_0
    //   2672: getfield imgResponse : Landroid/widget/TextView;
    //   2675: aconst_null
    //   2676: aload_3
    //   2677: aconst_null
    //   2678: aconst_null
    //   2679: invokevirtual setCompoundDrawablesWithIntrinsicBounds : (Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V
    //   2682: ldc_w 'ACTIONS'
    //   2685: ldc_w 'FOLLOWME-FINDME asignImage'
    //   2688: ldc_w 'ACTIONS : Imagen: PALOMA2AZUL'
    //   2691: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2694: invokestatic currentTimeMillis : ()J
    //   2697: putstatic com/roadtrack/onstar/BO/GlobalMembers.initFollowMeTime : J
    //   2700: aload_0
    //   2701: getfield pb : Landroid/widget/ProgressBar;
    //   2704: ifnull -> 2715
    //   2707: aload_0
    //   2708: aload_0
    //   2709: getfield pb : Landroid/widget/ProgressBar;
    //   2712: putfield FollowmegenericProgressBar : Landroid/widget/ProgressBar;
    //   2715: aload_0
    //   2716: getfield imgResponse : Landroid/widget/TextView;
    //   2719: ifnull -> 2730
    //   2722: aload_0
    //   2723: aload_0
    //   2724: getfield imgResponse : Landroid/widget/TextView;
    //   2727: putfield textViewFollowMe : Landroid/widget/TextView;
    //   2730: iconst_1
    //   2731: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   2734: putstatic com/roadtrack/onstar/MainActivity.onFollowMe : Ljava/lang/Boolean;
    //   2737: aload_0
    //   2738: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   2741: ldc_w 'MainActivity'
    //   2744: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   2747: invokevirtual getName : ()Ljava/lang/String;
    //   2750: ldc_w '2'
    //   2753: ldc_w '2'
    //   2756: invokestatic logActions : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2759: new com/roadtrack/onstar/MainActivity$34
    //   2762: astore_3
    //   2763: aload_3
    //   2764: aload_0
    //   2765: ldc2_w 2000
    //   2768: ldc2_w 1000
    //   2771: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity;JJ)V
    //   2774: aload_3
    //   2775: invokevirtual start : ()Landroid/os/CountDownTimer;
    //   2778: pop
    //   2779: goto -> 2807
    //   2782: aload_0
    //   2783: getfield pb : Landroid/widget/ProgressBar;
    //   2786: ifnull -> 2798
    //   2789: aload_0
    //   2790: getfield pb : Landroid/widget/ProgressBar;
    //   2793: bipush #8
    //   2795: invokevirtual setVisibility : (I)V
    //   2798: aload_0
    //   2799: aload_0
    //   2800: getfield v : Landroid/view/View;
    //   2803: iconst_1
    //   2804: invokespecial asignImage : (Landroid/view/View;I)V
    //   2807: aload_1
    //   2808: invokeinterface size : ()I
    //   2813: iconst_4
    //   2814: if_icmplt -> 2858
    //   2817: invokestatic AlertOn : ()V
    //   2820: new java/lang/StringBuilder
    //   2823: astore_1
    //   2824: aload_1
    //   2825: invokespecial <init> : ()V
    //   2828: aload_1
    //   2829: aload_0
    //   2830: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   2833: invokevirtual getDeviceTypeId : ()Ljava/lang/String;
    //   2836: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2839: pop
    //   2840: aload_1
    //   2841: ldc_w '_ID'
    //   2844: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2847: pop
    //   2848: aload_1
    //   2849: invokevirtual toString : ()Ljava/lang/String;
    //   2852: iconst_1
    //   2853: aload_0
    //   2854: invokestatic SetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
    //   2857: pop
    //   2858: aload_0
    //   2859: ldc2_w -1
    //   2862: putfield idInsertControl : J
    //   2865: aload_0
    //   2866: getstatic com/roadtrack/onstar/MainActivity.listCmdStat : Ljava/util/List;
    //   2869: iload_2
    //   2870: invokeinterface get : (I)Ljava/lang/Object;
    //   2875: checkcast com/roadtrack/onstar/VO/GetCommandStatusVO
    //   2878: invokevirtual getActionId : ()Ljava/lang/String;
    //   2881: invokespecial valueIdAction : (Ljava/lang/String;)V
    //   2884: iload_2
    //   2885: iconst_m1
    //   2886: if_icmpeq -> 2899
    //   2889: getstatic com/roadtrack/onstar/MainActivity.listCmdStat : Ljava/util/List;
    //   2892: iload_2
    //   2893: invokeinterface remove : (I)Ljava/lang/Object;
    //   2898: pop
    //   2899: aload_0
    //   2900: getfield act : Landroid/app/Activity;
    //   2903: invokestatic unlockOrientation : (Landroid/app/Activity;)V
    //   2906: aload_0
    //   2907: monitorexit
    //   2908: return
    //   2909: aload_0
    //   2910: monitorexit
    //   2911: return
    //   2912: astore_1
    //   2913: aload_0
    //   2914: monitorexit
    //   2915: aload_1
    //   2916: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	2912	finally
    //   26	111	2912	finally
    //   114	176	2912	finally
    //   180	189	2912	finally
    //   251	342	2912	finally
    //   354	414	2912	finally
    //   423	441	444	java/lang/Exception
    //   423	441	2912	finally
    //   446	512	2912	finally
    //   512	530	533	java/lang/Exception
    //   512	530	2912	finally
    //   535	601	2912	finally
    //   607	625	2912	finally
    //   648	675	2912	finally
    //   675	687	2912	finally
    //   687	715	2912	finally
    //   715	869	2912	finally
    //   876	881	2912	finally
    //   881	931	2912	finally
    //   931	1049	2912	finally
    //   1063	1091	2912	finally
    //   1091	1107	2912	finally
    //   1107	1136	2912	finally
    //   1139	1342	2912	finally
    //   1342	1373	2912	finally
    //   1376	1392	2912	finally
    //   1392	1401	2912	finally
    //   1401	1468	2912	finally
    //   1473	1483	2912	finally
    //   1483	1490	2912	finally
    //   1506	1694	2912	finally
    //   1694	1795	2912	finally
    //   1809	1814	2912	finally
    //   1814	1864	2912	finally
    //   1864	1887	2912	finally
    //   1892	1928	2912	finally
    //   1931	1962	2912	finally
    //   1962	1969	2912	finally
    //   1975	2041	2912	finally
    //   2047	2065	2912	finally
    //   2068	2079	2912	finally
    //   2079	2094	2912	finally
    //   2094	2098	2912	finally
    //   2098	2255	2912	finally
    //   2258	2280	2912	finally
    //   2280	2296	2912	finally
    //   2296	2325	2912	finally
    //   2328	2533	2912	finally
    //   2533	2635	2912	finally
    //   2635	2715	2912	finally
    //   2715	2730	2912	finally
    //   2730	2779	2912	finally
    //   2782	2798	2912	finally
    //   2798	2807	2912	finally
    //   2807	2858	2912	finally
    //   2858	2884	2912	finally
    //   2889	2899	2912	finally
    //   2899	2906	2912	finally
  }
  
  private void iniciarFollowMe(int paramInt) {
    CountDownTimer countDownTimer = this.countDownTimerFollowMe;
    if (countDownTimer != null)
      countDownTimer.cancel(); 
    if (paramInt <= 0)
      return; 
    GlobalMembers.onfollowmeActivated = true;
    this.countDownTimerFollowMe = new CountDownTimer((paramInt * 60 * 1000), 30000L) {
        final MainActivity this$0;
        
        public void onFinish() {
          GlobalMembers.onfollowmeActivated = false;
          if (GlobalMembers.onFollowMeRun.booleanValue()) {
            MainActivity mainActivity1 = MainActivity.this;
            String str1 = Utilities.getStringFromConfigList((Context)mainActivity1, mainActivity1.stringsResourcesVO.global_lbl_accionsigueme_1, 2131689885);
            MainActivity mainActivity2 = MainActivity.this;
            String str2 = Utilities.getStringFromConfigList((Context)mainActivity2, mainActivity2.stringsResourcesVO.global_lbl_accionstatussiguemefinalizado_1, 2131689905);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str1);
            stringBuilder.append(" ");
            stringBuilder.append(str2);
            String str3 = stringBuilder.toString();
            Intent intent2 = new Intent((Context)MainActivity.this, NotificationsActivity.class);
            (new Bundle()).putString("PUSH_NOTIFICATION_DEVICE_ID", GlobalMembers.followMeProcessDeviceId);
            Utilities.NotificacionConIntentFindMe(GlobalMembers.contexGlobal, 2131165676, Integer.valueOf(2131165676), str1, str3, intent2);
            PushNotificationsVO pushNotificationsVO = new PushNotificationsVO();
            pushNotificationsVO.setDeviceId(GlobalMembers.followMeProcessDeviceId);
            pushNotificationsVO.setAccNumber(String.valueOf(Enums$responcesGCM.follow_acc.GetResponcesGCM()));
            pushNotificationsVO.setMessage("FollowMeEnd");
            pushNotificationsVO.setTitle("FollowMe");
            pushNotificationsVO.setActionId(Enums$Services.FollowMe);
            pushNotificationsVO.setNumberActionID("");
            pushNotificationsVO.setAlertId("0");
            pushNotificationsVO.setLatitude("0");
            pushNotificationsVO.setLongitude("0");
            MainActivity.this.dbFun.addPush(pushNotificationsVO, "1");
            Intent intent1 = new Intent((Context)MainActivity.this, TransparentActivity.class);
            MainActivity.this.startActivity(intent1);
          } 
          GlobalMembers.onFollowMeRun = Boolean.valueOf(false);
          Utilities.escribeArchivo("MainActivity", "FOLLOWME", "FOLLOWME FINISHED");
          GlobalMembers.needFollowMeReview = Boolean.valueOf(GlobalMembers.currentActivity.equals("MainActivity"));
          GlobalMembers.followMeActivatedPoint = null;
        }
        
        public void onTick(long param1Long) {
          long l = param1Long / 1000L / 60L;
          param1Long = 16L - MainActivity.this.dbFun.timeFromStartFollowMe(MainActivity.this.deviceId, MainActivity.mContext);
          Intent intent = new Intent();
          intent.setAction("GlobalTouchService");
          intent.putExtra("ACTION_EXTRA", "usuario_activo");
          MainActivity.this.sendBroadcast(intent);
          MainActivity mainActivity = MainActivity.this;
          mainActivity.deviceId = Utilities.getLastKnownDeviceSelected(mainActivity.rtApp, "MainActivity").getDeviceId().toString();
          mainActivity = MainActivity.this;
          String str = Utilities.getLastFollowMeMessageId((Activity)mainActivity, mainActivity.deviceId);
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Timer: ");
          stringBuilder.append(l);
          stringBuilder.append(" - Min. restantes: ");
          stringBuilder.append(param1Long);
          stringBuilder.append(" - Corriendo: ");
          stringBuilder.append(GlobalMembers.onFollowMeRun);
          stringBuilder.append(" MessageID:");
          stringBuilder.append(str);
          Utilities.escribeArchivo("MainActivity", "FOLLOWME", stringBuilder.toString());
          (new AsyncTaskGetLastIncomingMessage(GlobalMembers.contexGlobal, false, MainActivity.this.textViewFollowMe)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str, Utilities.getLastKnownDeviceSelected(MainActivity.access$000(this.this$0), "MainActivity").getDeviceId() });
          if (param1Long <= 0L || l <= 0L) {
            cancel();
            onFinish();
          } 
        }
      };
    this.countDownTimerFollowMe.start();
  }
  
  private void initAnalytics() {
    this.analyticsHelper = AnalyticsHelperFactory.getAnalyticsHelper(AnalyticsTypes.FIREBASE);
  }
  
  private void initBluetooth() {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
    try {
      setHandlerManagerBT();
      if (this.mBluetoothAdapter == null) {
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter.getProfileProxy(mContext, myHeadsetListener, 1);
        String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_bluetooth_not_available, 2131690029);
        if (this.mBluetoothAdapter == null) {
          Toast.makeText((Context)this.rtApp, str, 1).show();
          /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
          return;
        } 
      } 
      if (mChatService != null && mChatService.getState() == 0) {
        mChatService.start();
      } else if (actCall != null && actCall.getNumberType() == Enums$CallNumberType.VR && actCall.getState() != Enums$CallState.Ended) {
        Utilities.wakeDevice(GlobalMembers.contexGlobal, "RTMobile_HMI");
      } 
      if (((mChatService != null && mChatService.getState() == 0) || mChatService.getState() == 1) && (GlobalMembers.isMAPUpdateEnabled || GlobalMembers.isHMIEnabled))
        getPlatinumAndConnection(); 
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: initBluetooth", exception.getMessage());
    } finally {
      Exception exception;
    } 
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
  }
  
  private void initProcesBack() {
    GlobalMembers.isAppRunning = true;
    if (mNPlatinum == null) {
      mNPlatinum = new ManagerNotificationPlatinum((Context)this);
      mNPlatinum.handlerSendMessage = this.NewMessageHandler;
      OutQueue = new commonQueue();
    } 
  }
  
  public static boolean isBtAndPlatinumConnected() {
    BluetoothServiceRT bluetoothServiceRT = mChatService;
    boolean bool = false;
    if (bluetoothServiceRT == null)
      return false; 
    if (bluetoothServiceRT.getState() == 3)
      bool = true; 
    return bool;
  }
  
  private boolean itWasOpen() {
    this.rightRL = (LinearLayout)findViewById(2131297110);
    return this.drawerLayout.isDrawerOpen((View)this.rightRL);
  }
  
  private void llamadaAsistencia() {
    GlobalMembers.btnpress = 2;
    if (GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8)) {
      if (!isBtAndPlatinumConnected()) {
        Utilities.sendIntentCallAction((Context)this);
      } else {
        boolean bool1 = bEsperandoP8;
        boolean bool = true;
        if (iPreviousCallIndex != 0)
          bool = false; 
        if (((bool1 ^ true) & bool & bRelayReady) != 0) {
          Intent intent = new Intent((Context)this, ActivityCall.class);
          intent.putExtra("Boton", "Asistencia");
          startActivityForResult(intent, 456);
          (new Thread(new TimerThread())).start();
        } 
        CallOrHangUp(Enums$Calls.AssistanceCallAndMessage);
      } 
    } else {
      Utilities.sendIntentCallAction((Context)this);
    } 
  }
  
  private void loadButtons() {
    this.leftButton = (CustomActionButton)findViewById(2131296792);
    this.centerLeftButton = (CustomActionButton)findViewById(2131296453);
    this.centerRightButton = (CustomActionButton)findViewById(2131296454);
    this.rightButton = (CustomActionButton)findViewById(2131296984);
    bottomPanelButtons = new LinkedList<CustomActionButton>();
    bottomPanelButtons.add(this.leftButton);
    bottomPanelButtons.add(this.centerLeftButton);
    bottomPanelButtons.add(this.centerRightButton);
    bottomPanelButtons.add(this.rightButton);
  }
  
  private void loadFunctions() {
    // Byte code:
    //   0: new java/util/LinkedHashMap
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: pop
    //   8: aload_0
    //   9: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   12: ldc_w 'MainActivity'
    //   15: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   18: astore #4
    //   20: new java/util/LinkedList
    //   23: dup
    //   24: invokespecial <init> : ()V
    //   27: astore_3
    //   28: aload #4
    //   30: ifnull -> 39
    //   33: aload #4
    //   35: invokevirtual getRepeatedActions : ()Ljava/util/LinkedList;
    //   38: astore_3
    //   39: aload_3
    //   40: invokevirtual iterator : ()Ljava/util/Iterator;
    //   43: astore_3
    //   44: iconst_0
    //   45: istore_1
    //   46: aload_3
    //   47: invokeinterface hasNext : ()Z
    //   52: ifeq -> 167
    //   55: aload_3
    //   56: invokeinterface next : ()Ljava/lang/Object;
    //   61: checkcast java/lang/String
    //   64: astore #5
    //   66: aload #5
    //   68: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   71: invokevirtual intValue : ()I
    //   74: iconst_1
    //   75: aload_0
    //   76: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   79: invokestatic tieneWaterMark : (IILcom/roadtrack/onstar/onstarApplication;)I
    //   82: istore_2
    //   83: aload #5
    //   85: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   88: invokevirtual intValue : ()I
    //   91: invokestatic GetValue : (I)Lcom/roadtrack/onstar/Enums$Services;
    //   94: astore #5
    //   96: aload_0
    //   97: aload #5
    //   99: iload_2
    //   100: iload_1
    //   101: invokevirtual setAction : (Lcom/roadtrack/onstar/Enums$Services;II)V
    //   104: aload #5
    //   106: invokevirtual GetCode : ()I
    //   109: getstatic com/roadtrack/onstar/Enums$Services.ActionRenewal : Lcom/roadtrack/onstar/Enums$Services;
    //   112: invokevirtual GetCode : ()I
    //   115: if_icmpne -> 161
    //   118: getstatic com/roadtrack/onstar/MainActivity.lastDeviceInMain : Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   121: invokevirtual getSeguroApp : ()Ljava/lang/String;
    //   124: ifnull -> 161
    //   127: getstatic com/roadtrack/onstar/MainActivity.lastDeviceInMain : Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   130: invokevirtual getSeguroApp : ()Ljava/lang/String;
    //   133: ldc_w '1'
    //   136: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   139: ifne -> 153
    //   142: aload_0
    //   143: aload #5
    //   145: iconst_1
    //   146: iload_1
    //   147: invokevirtual setAction : (Lcom/roadtrack/onstar/Enums$Services;II)V
    //   150: goto -> 161
    //   153: aload_0
    //   154: aload #5
    //   156: iload_2
    //   157: iload_1
    //   158: invokevirtual setAction : (Lcom/roadtrack/onstar/Enums$Services;II)V
    //   161: iinc #1, 1
    //   164: goto -> 46
    //   167: getstatic com/roadtrack/onstar/MainActivity.allBottomPanelButtons : Ljava/util/LinkedHashMap;
    //   170: aload #4
    //   172: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   175: getstatic com/roadtrack/onstar/MainActivity.bottomPanelButtons : Ljava/util/LinkedList;
    //   178: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   181: pop
    //   182: goto -> 199
    //   185: astore_3
    //   186: ldc_w 'MainActivity'
    //   189: ldc_w 'loadFunctions:'
    //   192: aload_3
    //   193: invokevirtual getMessage : ()Ljava/lang/String;
    //   196: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   199: getstatic com/roadtrack/onstar/BO/GlobalMembers.newMain : Z
    //   202: ifeq -> 365
    //   205: aload_0
    //   206: aload_0
    //   207: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   210: getfield renovacion_lbl_alterarrenovar : Ljava/lang/String;
    //   213: ldc_w 2131690279
    //   216: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   219: pop
    //   220: iconst_0
    //   221: istore_1
    //   222: iload_1
    //   223: getstatic com/roadtrack/onstar/MainActivity.bottomPanelButtons : Ljava/util/LinkedList;
    //   226: invokevirtual size : ()I
    //   229: if_icmpge -> 365
    //   232: iload_1
    //   233: iconst_3
    //   234: if_icmpeq -> 240
    //   237: goto -> 359
    //   240: getstatic com/roadtrack/onstar/MainActivity.lastDeviceInMain : Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   243: astore_3
    //   244: aload_3
    //   245: ifnull -> 359
    //   248: aload_3
    //   249: invokevirtual getSeguroApp : ()Ljava/lang/String;
    //   252: ifnull -> 359
    //   255: getstatic com/roadtrack/onstar/MainActivity.lastDeviceInMain : Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   258: invokevirtual getSeguroApp : ()Ljava/lang/String;
    //   261: ldc_w '1'
    //   264: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   267: ifeq -> 321
    //   270: aload_0
    //   271: getfield llBtnShowDialog : Landroid/widget/RelativeLayout;
    //   274: bipush #8
    //   276: invokevirtual setVisibility : (I)V
    //   279: aload_0
    //   280: getfield rlContainerTriangle : Landroid/widget/ImageView;
    //   283: bipush #8
    //   285: invokevirtual setVisibility : (I)V
    //   288: getstatic com/roadtrack/onstar/MainActivity.bottomPanelButtons : Ljava/util/LinkedList;
    //   291: iconst_3
    //   292: invokevirtual get : (I)Ljava/lang/Object;
    //   295: checkcast com/roadtrack/onstar/VO/CustomActionButton
    //   298: iconst_0
    //   299: invokevirtual setEnabled : (Z)V
    //   302: getstatic com/roadtrack/onstar/MainActivity.bottomPanelButtons : Ljava/util/LinkedList;
    //   305: iconst_3
    //   306: invokevirtual get : (I)Ljava/lang/Object;
    //   309: checkcast com/roadtrack/onstar/VO/CustomActionButton
    //   312: getstatic com/roadtrack/onstar/VO/CustomActionButton.WATER_MARK_ALPHA : F
    //   315: invokevirtual setAlpha : (F)V
    //   318: goto -> 359
    //   321: getstatic com/roadtrack/onstar/BO/GlobalMembers.notificationSpinner : Z
    //   324: ifeq -> 331
    //   327: aload_0
    //   328: invokevirtual showRenewal : ()V
    //   331: getstatic com/roadtrack/onstar/MainActivity.bottomPanelButtons : Ljava/util/LinkedList;
    //   334: iconst_3
    //   335: invokevirtual get : (I)Ljava/lang/Object;
    //   338: checkcast com/roadtrack/onstar/VO/CustomActionButton
    //   341: iconst_1
    //   342: invokevirtual setEnabled : (Z)V
    //   345: getstatic com/roadtrack/onstar/MainActivity.bottomPanelButtons : Ljava/util/LinkedList;
    //   348: iconst_3
    //   349: invokevirtual get : (I)Ljava/lang/Object;
    //   352: checkcast com/roadtrack/onstar/VO/CustomActionButton
    //   355: fconst_1
    //   356: invokevirtual setAlpha : (F)V
    //   359: iinc #1, 1
    //   362: goto -> 222
    //   365: return
    // Exception table:
    //   from	to	target	type
    //   33	39	185	java/lang/Exception
    //   39	44	185	java/lang/Exception
    //   46	150	185	java/lang/Exception
    //   153	161	185	java/lang/Exception
    //   167	182	185	java/lang/Exception
  }
  
  private void lockRightDrawer(boolean paramBoolean) {
    this.mDrawerLayout.setDrawerLockMode(paramBoolean);
  }
  
  @SuppressLint({"UseSparseArrays"})
  private void makeWindowToUpdate(String paramString1, String paramString2, final String filePath) {
    new HashMap<Object, Object>();
    (new AsyncTask<Void, Integer, Void>() {
        final MainActivity this$0;
        
        final String val$filePath;
        
        protected Void doInBackground(Void... param1VarArgs) {
          Thread thread = Thread.currentThread();
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(null.class.getSimpleName());
          stringBuilder.append(": ");
          stringBuilder.append(Thread.currentThread().getName());
          thread.setName(stringBuilder.toString());
          try {
            FileInputStream fileInputStream = UtilitiesFile.fileInputStreamFromStringFile(UtilitiesFile.getFileFromStringFile(filePath));
            File file = UtilitiesFile.getFileFromStringFile(filePath);
            MainActivity.access$6202(MainActivity.this, (int)file.length());
            if (fileInputStream != null)
              fileInputStream.close(); 
          } catch (Exception exception) {
            Utilities.escribeArchivo("MainActivity", "Error: doInBackground", exception.toString());
          } 
          return null;
        }
        
        protected void onPostExecute(Void param1Void) {
          ManagerNotificationPlatinum managerNotificationPlatinum = MainActivity.getmNPlatinum();
          if (GlobalMembers.MAP_UPDATE_ON_PARTS.booleanValue()) {
            managerNotificationPlatinum.updateControl(1, GlobalMembers.mapFileName, MainActivity.this.mapFileSize);
            MainActivity mainActivity = MainActivity.this;
            String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.main_activity_map_update_step_3_message, 2131690048);
            if (MainActivity.isBtAndPlatinumConnected()) {
              managerNotificationPlatinum.sendTextForTTS(str);
              Toast.makeText(MainActivity.this.getApplicationContext(), str, 1).show();
            } 
          } else {
            managerNotificationPlatinum.updateControl(1, GlobalMembers.mapFileName, MainActivity.this.mapFileSize);
            if (MainActivity.isBtAndPlatinumConnected()) {
              MainActivity mainActivity = MainActivity.this;
              String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.main_activity_map_update_step_3_message, 2131690048);
              managerNotificationPlatinum.sendTextForTTS(str);
              Toast.makeText(MainActivity.this.getApplicationContext(), str, 1).show();
            } 
          } 
        }
        
        protected void onPreExecute() {
          MainActivity.access$6302(MainActivity.this, (NotificationManager)MainActivity.mContext.getSystemService("notification"));
          MainActivity.access$6402(MainActivity.this, new NotificationCompat.Builder(MainActivity.mContext));
          MainActivity mainActivity1 = MainActivity.this;
          String str1 = Utilities.getStringFromConfigList((Context)mainActivity1, mainActivity1.stringsResourcesVO.main_activity_map_update_step_2_title, 2131690047);
          MainActivity mainActivity2 = MainActivity.this;
          String str2 = Utilities.getStringFromConfigList((Context)mainActivity2, mainActivity2.stringsResourcesVO.main_activity_map_update_step_2_message, 2131690046);
          NotificationCompat.Builder builder = MainActivity.this.builderMapUpdate;
          builder.setContentTitle(str1);
          builder.setContentText(str2);
          builder.setSmallIcon(17301590);
          MainActivity.this.builderMapUpdate.setProgress(0, 0, true);
          MainActivity.this.builderMapUpdate.setOngoing(true);
          MainActivity.this.notificationManagerMapUpdate.notify(123456789, MainActivity.this.builderMapUpdate.build());
        }
        
        protected void onProgressUpdate(Integer... param1VarArgs) {
          super.onProgressUpdate((Object[])param1VarArgs);
          MainActivity.this.builderMapUpdate.setProgress(param1VarArgs[0].intValue(), param1VarArgs[1].intValue(), false);
          MainActivity.this.notificationManagerMapUpdate.notify(123456789, MainActivity.this.builderMapUpdate.build());
        }
      }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
  }
  
  private void mapUpdateDialogOnPush() {
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.map_update_download_preference, 2131690082);
    final Dialog dialog = Utilities.simpleDialogWithCheckBox((Context)this, null, str1, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.messagethreemapdl, 2131690128), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949), 20.0F, 16.0F, true, str2);
    this.btnOK = (Button)dialog.findViewById(2131296343);
    this.btnNOK = (Button)dialog.findViewById(2131296344);
    this.btnOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.btnNOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    ((CheckBox)dialog.findViewById(2131296460)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final MainActivity this$0;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (param1Boolean) {
              MainActivity.this.dbFun.deleteDownloadPreferece();
              MainActivity.this.dbFun.setAutomaticMapUpdateDownload();
            } else {
              MainActivity.this.dbFun.deleteDownloadPreferece();
              MainActivity.this.dbFun.unSetAutomaticMapUpdateDownload();
            } 
          }
        });
    dialog.show();
  }
  
  private void mapUpdateOnAutomatic() {
    final Dialog dialog = Utilities.simpleDialog((Context)this, null, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955), Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.useConfigOrPush, 2131690481), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946), false, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949), 20.0F, 16.0F);
    this.btnOK = (Button)dialog.findViewById(2131296343);
    this.btnNOK = (Button)dialog.findViewById(2131296344);
    this.btnOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.btnNOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
  }
  
  private void moipTicketFunction() {
    toggleDrawerMenu();
    (new GetLocatorToken((Context)this, new AsyncResponse() {
          final MainActivity this$0;
          
          final UserDevicesVO val$last_known_device;
          
          public void processFinish(String param1String) {
            HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
            if (param1String != null && !param1String.equals("")) {
              hashMap.put("URL", GlobalMembers.MoipTicketHistory);
              hashMap.put("lpar1", "2");
              hashMap.put("lpar2", MainActivity.this.rtApp.getUserAccessData()[0]);
              hashMap.put("lpar3", MainActivity.this.rtApp.getUserAccessData()[0]);
              hashMap.put("lpar4", last_known_device.getSerialnumber());
              hashMap.put("t", param1String);
              Intent intent = new Intent((Context)MainActivity.this, RenewalMoipTicketWV.class);
              Bundle bundle = new Bundle();
              intent.addFlags(268435456);
              bundle.putSerializable("url", hashMap);
              intent.putExtras(bundle);
              MainActivity.this.startActivity(intent);
            } else {
              MainActivity mainActivity = MainActivity.this;
              String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)MainActivity.this, str, 1).show();
            } 
          }
        })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void myAccountFunction() {
    byte b = 0;
    boolean bool2 = false;
    boolean bool1 = false;
    while (b < this.drawerMenuList.size()) {
      boolean bool;
      if (((ListButton)this.drawerMenuList.get(b)).getMyService() == Enums$Services.WebPage) {
        bool = true;
      } else {
        bool = bool2;
        if (((ListButton)this.drawerMenuList.get(b)).getMyService() == Enums$Services.ActionPaymenHistory) {
          bool1 = true;
          bool = bool2;
        } 
      } 
      b++;
      bool2 = bool;
    } 
    if (bool2 && !bool1) {
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 5);
    } else if (!bool2 && !bool1) {
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 6);
    } else if (bool2 && bool1) {
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 1);
    } else {
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 4);
    } 
    if (bool1) {
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(Boolean.valueOf(false), this.rtApp, mContext);
    } else {
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(Boolean.valueOf(true), this.rtApp, mContext);
    } 
    this.listButtonAdapter = new ListButtonAdapter(this.drawerMenuList);
    this.mDrawerList.setAdapter((ListAdapter)this.listButtonAdapter);
  }
  
  private void myPlanFunction() {
    toggleDrawerMenu();
    (new GetMyPlanInformation_Task((Context)this, new AsyncResponse() {
          final MainActivity this$0;
          
          public void processFinish(String param1String) {
            if (param1String != null && !param1String.isEmpty()) {
              Intent intent = new Intent((Context)MainActivity.this, MyPlan.class);
              Bundle bundle = new Bundle();
              bundle.putString("service_response", param1String);
              bundle.putString("previous_class", MainActivity.class.getSimpleName());
              bundle.putString("plan_app", MainActivity.lastDeviceInMain.getSeguroApp());
              intent.putExtras(bundle);
              MainActivity.this.startActivity(intent);
            } else {
              MainActivity mainActivity = MainActivity.this;
              String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)MainActivity.this, str, 1).show();
            } 
          }
        })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void newFollowMeProcess(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2) {
    String str = paramString4;
    Utilities.escribeArchivo("MainActivity newFollowMeProcess", "Sigueme Test -- Punto de Activacion - Fecha", str);
    if (str != null && paramString4.length() > 0) {
      paramString4 = str;
    } else {
      paramString4 = Utilities.getDateTime(paramString1, GlobalMembers.contexGlobal);
    } 
    DBFunctions dBFunctions = this.dbFun;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramInt1);
    dBFunctions.updateHistorical(Boolean.valueOf(true), stringBuilder.toString(), "1", paramString2, paramString3, paramString4, "", "15", "0", "0", "0", paramInt2, Enums$Services.FollowMe.GetCode());
    AlertOn();
    stringBuilder = new StringBuilder();
    stringBuilder.append(this.rtApp.getDeviceTypeId());
    stringBuilder.append("_ID");
    PreferenceRT.SetValuePreference(stringBuilder.toString(), true, (Context)this);
    FollowMeHandler.deleteAllFollowmePointsSpecial();
    if (this.dbFun.addFollowMePush(paramInt1, paramString1, paramString2, paramString3, paramString4) > 0L) {
      GlobalMembers.lastFollowMePoint = new Double[2];
      GlobalMembers.lastFollowMePoint[0] = Double.valueOf(Double.parseDouble(paramString2));
      GlobalMembers.lastFollowMePoint[1] = Double.valueOf(Double.parseDouble(paramString3));
      FollowMePointsVO followMePointsVO = new FollowMePointsVO();
      followMePointsVO.setDateTime(paramString4);
      followMePointsVO.setDeviceId(paramString1);
      followMePointsVO.setLat(paramString2);
      followMePointsVO.setLng(paramString3);
      GlobalMembers.followMeArrayListPoints.clear();
      GlobalMembers.followMeArrayListPoints = null;
      GlobalMembers.followMeArrayListPoints = new ArrayList();
      GlobalMembers.followMeArrayListPoints.add(followMePointsVO);
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString4);
      stringBuilder1.append(" ");
      stringBuilder1.append(followMePointsVO.getLat());
      stringBuilder1.append(" ");
      stringBuilder1.append(followMePointsVO.getLng());
      Utilities.escribeArchivo("MainActivity newFollowMeProcess", "Sigueme Test -- Punto de Activacion", stringBuilder1.toString());
    } 
    onFollowMe = Boolean.valueOf(true);
    GlobalMembers.needToOpenMap = false;
    GlobalMembers.eventListenerOpenMap.onEvent();
    iniciarFollowMe(16);
  }
  
  private void noRepeatSameActionDialog() {
    try {
      String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
      String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
      String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
      this.dialogMenu = DialogMenu.newInstance();
      Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, 2131165484);
      this.dialogMenu.setPreferenceDialogFragment(this, (Context)this, true, null, str3, str2, true, str1, false, "");
      this.dialogMenu.show(getSupportFragmentManager(), null);
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: noRepeatSameActionDIalog", exception.getMessage());
    } 
  }
  
  private void notificationsFunction() {
    toggleDrawerMenu();
    Enums$Services.ActionNotifications.GetCode();
    Intent intent = new Intent((Context)this, NotificationsActivity.class);
    AlertOff();
    startActivity(intent);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  private static void notifyPlatinum_Inner(Object paramObject) {
    try {
      sendMessage(String.valueOf(paramObject));
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: notifyPaltinum_Inner", exception.getMessage());
    } 
  }
  
  private void onClick(int paramInt, View paramView) {
    Boolean bool = Boolean.valueOf(true);
    GlobalMembers.tempSocketResponse = true;
    this.tempButton = (CustomActionButton)paramView;
    CustomActionButton customActionButton = this.tempButton;
    if (customActionButton != null && customActionButton instanceof CustomActionButton) {
      DBFunctions dBFunctions = new DBFunctions(mContext);
      boolean bool1 = dBFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), "", true);
      Utilities.showTheftAutoBanner((TextView)findViewById(2131297163), (Context)this, bool1, false);
      if (bool1)
        return; 
      if (UserInterfaceUtils.isActionButton(this.tempButton) && !NetUtilities.validateNetwork((Context)this, false, true))
        return; 
      this.cancelable = true;
      this.sendCommand = new SendCommand();
      this.pbAction = (ProgressBar)paramView.findViewById(2131296940);
      this.isTryingToExecuteSameAction = false;
      CustomActionButton customActionButton1 = this.tempButton;
      if (customActionButton1 != null && customActionButton1.getMyService() != null)
        analyticsButtonSelected(this.tempButton.getMyService()); 
      paramInt = null.$SwitchMap$com$roadtrack$onstar$Enums$Services[this.tempButton.getMyService().ordinal()];
      if (paramInt != 1) {
        Drawable drawable;
        if (paramInt != 2) {
          CustomActionButton customActionButton2;
          ActionsProcess actionsProcess;
          Intent intent;
          Drawable drawable2;
          GoogleMapVO googleMapVO2;
          Drawable drawable1;
          GoogleMapVO googleMapVO1;
          CustomActionButton customActionButton3;
          switch (paramInt) {
            case 38:
              dtcFunction();
              break;
            case 37:
              this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.pid);
              customActionButton2 = this.tempButton;
              if (customActionButton2 != null)
                pidFunction((View)customActionButton2); 
              break;
            case 36:
              starFindMe();
              break;
            case 35:
              if (!onPID.booleanValue()) {
                String str1 = this.rtApp.getAccountID();
                String str4 = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
                final String deviceName = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getName();
                String str3 = Enums$Services.pid.GetCodeString();
                if (Utilities.buttonValidated((Context)this, str1, str4, onPID.booleanValue())) {
                  onPID = bool;
                  this.PIDgenericProgressBar = this.pbAction;
                  this.ivPID = (TextView)customActionButton2.findViewById(2131296611);
                  actionsProcess = new ActionsProcess((Context)this, this.PIDgenericProgressBar, this.ivPID, str3, str4);
                  actionsProcess.setOnPostExecuteListener(new ActionsProcess.OnPostExecuteListener() {
                        final MainActivity this$0;
                        
                        final String val$deviceName;
                        
                        public void onPostExecuteListener(ActionResultVO param1ActionResultVO) {
                          if (param1ActionResultVO.isValidPID()) {
                            Intent intent = new Intent((Context)MainActivity.this, PIDActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("odometer", param1ActionResultVO.getOdometer());
                            bundle.putString("gasoline", param1ActionResultVO.getGas());
                            bundle.putString("oilValue", param1ActionResultVO.getOilValue());
                            bundle.putString("oilStatus", param1ActionResultVO.getOilStatus());
                            bundle.putString("battery", param1ActionResultVO.getBatteryLevel());
                            bundle.putString("tireFL", param1ActionResultVO.getTireFL());
                            bundle.putString("tireStatusFL", param1ActionResultVO.getTireStatusFL());
                            bundle.putString("tireFR", param1ActionResultVO.getTireFR());
                            bundle.putString("tireStatusFR", param1ActionResultVO.getTireStatusFL());
                            bundle.putString("tireRL", param1ActionResultVO.getTireRL());
                            bundle.putString("tireStatusRL", param1ActionResultVO.getTireStatusFL());
                            bundle.putString("tireRR", param1ActionResultVO.getTireRR());
                            bundle.putString("tireStatusRR", param1ActionResultVO.getTireStatusFL());
                            bundle.putString("eventDate", param1ActionResultVO.getEventDateTime());
                            bundle.putString("DeviceId", String.valueOf(param1ActionResultVO.getDeviceId()));
                            bundle.putString("DeviceName", deviceName);
                            bundle.putString("isM300", param1ActionResultVO.getIsM300());
                            intent.putExtras(bundle);
                            MainActivity.this.startActivity(intent);
                          } else {
                            MainActivity mainActivity = MainActivity.this;
                            String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                            Toast.makeText((Context)MainActivity.this, str, 1).show();
                          } 
                          MainActivity.onPID = Boolean.valueOf(false);
                        }
                      });
                  actionsProcess.executeOnExecutor(Executors.newSingleThreadExecutor(), (Object[])new String[] { this.sessionKey, this.password, str1, this.userId, this.csvParams, this.deviceCSVParams });
                } 
                break;
              } 
              this.isTryingToExecuteSameAction = true;
              break;
            case 33:
            case 34:
              if (!onAlertParking.booleanValue()) {
                setWatterMarks(true, new Enums$Services[] { this.tempButton.getMyService() });
                this.ParkinggenericProgressBar = this.pbAction;
                ProgressBar progressBar = this.ParkinggenericProgressBar;
                if (progressBar != null)
                  progressBar.setVisibility(0); 
                this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.Parking);
                customActionButton3 = this.tempButton;
                if (customActionButton3 != null)
                  customActionButton3.showActionStatus(1); 
                this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.ParkingUUx);
                customActionButton3 = this.tempButton;
                if (customActionButton3 != null)
                  customActionButton3.showActionStatus(1); 
                this.ParkinggenericView = (View)actionsProcess;
                this.ParkinggenericActionName = "Parking";
                onAlertParking = bool;
                this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
                this.actionCode = Utilities.getParkingCode(this.rtApp, "MainActivity").GetCodeString();
                this.csvParams = "1";
                this.deviceCSVParams = "";
                (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
                break;
              } 
              this.isTryingToExecuteSameAction = true;
              break;
            case 32:
              if (!onAlertSpeedClicked.booleanValue()) {
                if (!onAlertSpeed.booleanValue() && !onAlertSpeedClicked.booleanValue()) {
                  onAlertSpeedClicked = bool;
                  this.SpeedgenericProgressBar = this.pbAction;
                  this.SpeedgenericActionName = "SpeedAlways";
                  this.SpeedgenericView = (View)actionsProcess;
                  createMenuSpeedDialog(true, this.drawableId, this.stringId);
                  break;
                } 
                this.isTryingToExecuteSameAction = true;
              } 
              break;
            case 30:
            case 31:
              if (!onAlertSpeedClicked.booleanValue()) {
                if (!onAlertSpeed.booleanValue()) {
                  onAlertSpeedClicked = bool;
                  this.SpeedgenericProgressBar = this.pbAction;
                  this.SpeedgenericActionName = "Speed";
                  this.SpeedgenericView = (View)actionsProcess;
                  createMenuSpeedDialog(true, this.drawableId, this.stringId);
                  break;
                } 
                this.isTryingToExecuteSameAction = true;
              } 
              break;
            case 29:
              if (!onAlertValet.booleanValue()) {
                setWatterMarks(true, new Enums$Services[] { this.tempButton.getMyService() });
                this.valetgenericProgressBar = this.pbAction;
                ProgressBar progressBar = this.valetgenericProgressBar;
                if (progressBar != null)
                  progressBar.setVisibility(0); 
                this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.valet);
                customActionButton3 = this.tempButton;
                if (customActionButton3 != null)
                  customActionButton3.showActionStatus(1); 
                this.valetgenericView = (View)actionsProcess;
                onAlertValet = bool;
                this.other = false;
                this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
                this.actionCode = "52";
                this.csvParams = "1";
                this.deviceCSVParams = "";
                (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
                break;
              } 
              this.isTryingToExecuteSameAction = true;
              break;
            case 28:
              intent = new Intent((Context)this, HistoricalTestActivity.class);
              this.REQUEST_CODE = 5;
              startActivityForResult(intent, this.REQUEST_CODE);
              break;
            case 26:
              if (!onDisarmPINCODEClicked.booleanValue()) {
                if (!onDisarmPINCODE.booleanValue()) {
                  onDisarmPINCODEClicked = bool;
                  this.DisarmgenericProgressBar = this.pbAction;
                  this.DisarmgenericView = (View)intent;
                  this.drawableId = 2131165299;
                  drawable2 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_pincode, this.drawableId);
                  this.stringId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.alertBeforDisPinCode, 2131689660);
                  this.titleId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_acciondesactivarpincode_1, 2131689856);
                  createMenuDialog(this.cancelable, drawable2, this.stringId, this.titleId, false);
                  break;
                } 
                this.isTryingToExecuteSameAction = true;
              } 
              break;
            case 23:
            case 24:
              if (!istheftautostolen()) {
                if (!onFollowMe.booleanValue() && !onFindMe.booleanValue()) {
                  this.FollowmegenericProgressBar = this.pbAction;
                  this.FollowmegenericView = (View)drawable2;
                  this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
                  this.actionCode = Utilities.getFollowMeCode(this.rtApp, "MainActivity").GetCodeString();
                  this.csvParams = "";
                  this.deviceCSVParams = "";
                  Actions actions = new Actions();
                  GlobalMembers.followMeProcessDeviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId().toString();
                  Utilities.setLastFollowMeId(mContext, GlobalMembers.followMeProcessDeviceId);
                  GlobalMembers.ifollowMeType = customActionButton3.mustStartFollowMe(GlobalMembers.followMeProcessDeviceId, mContext);
                  if (GlobalMembers.ifollowMeType < 4) {
                    CustomActionButton customActionButton5;
                    GlobalMembers.followMeAccesByAction = bool;
                    paramInt = GlobalMembers.ifollowMeType;
                    if (paramInt == 0 || paramInt == 2) {
                      setWatterMarks(true, new Enums$Services[] { this.tempButton.getMyService() });
                      this.other = true;
                      onFollowMe = bool;
                      GlobalMembers.onFollowMeRun = bool;
                      GlobalMembers.needToOpenMap = true;
                      GlobalMembers.pushNumber = 0L;
                      ProgressBar progressBar = this.FollowmegenericProgressBar;
                      if (progressBar != null)
                        progressBar.setVisibility(0); 
                      this.button = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.FollowMe);
                      customActionButton5 = this.button;
                      if (customActionButton5 != null)
                        customActionButton5.showActionStatus(1); 
                      this.buttonUUX = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.FollowMeUUx);
                      customActionButton5 = this.buttonUUX;
                      if (customActionButton5 != null)
                        customActionButton5.showActionStatus(1); 
                      this.dateInFutureForActions = Utilities.addSecondsToDate(30);
                      actions.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
                      break;
                    } 
                    GlobalMembers.onFollowMeRun = bool;
                    this.button = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.FollowMe);
                    CustomActionButton customActionButton4 = this.button;
                    if (customActionButton4 != null)
                      customActionButton4.showActionStatus(0); 
                    this.buttonUUX = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.FollowMeUUx);
                    customActionButton4 = this.buttonUUX;
                    if (customActionButton4 != null)
                      customActionButton4.showActionStatus(0); 
                    googleMapVO2 = new GoogleMapVO();
                    googleMapVO2.setKEY_SET_ENGINE_SOURCE(Enums$navigationProcess.FollowMe);
                    openTomTomMap(googleMapVO2);
                    iniciarFollowMe(16 - customActionButton5.minutesLeftForFollowMe(GlobalMembers.followMeProcessDeviceId, mContext));
                  } 
                  break;
                } 
                this.isTryingToExecuteSameAction = true;
              } 
              break;
            case 21:
            case 22:
              if (!onHornClicked.booleanValue()) {
                if (!onHorn.booleanValue()) {
                  this.HornLigthsgenericProgressBar = this.pbAction;
                  this.HornLigthsgenericView = (View)googleMapVO2;
                  onHornClicked = bool;
                  this.drawableId = 2131165291;
                  drawable1 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_hornlightsonstar, this.drawableId);
                  this.stringId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_accionarbocina_2, 2131689640);
                  this.titleId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionalertabocina_1, 2131689844);
                  createMenuDialog(this.cancelable, drawable1, this.stringId, this.titleId, false);
                  this.HornLigthsgenericActionName = "Horn";
                  break;
                } 
                this.isTryingToExecuteSameAction = true;
              } 
              break;
            case 20:
              if (!onHornLightsClicked.booleanValue()) {
                if (!onHornLights.booleanValue()) {
                  onHornLightsClicked = bool;
                  this.HornLigthsgenericProgressBar = this.pbAction;
                  this.HornLigthsgenericView = (View)drawable1;
                  this.drawableId = 2131165291;
                  drawable1 = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_hornlightsonstar, this.drawableId);
                  this.stringId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_usteddeseaaccionar_2, 2131689644);
                  this.titleId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionlucesybocina_1, 2131689879);
                  createMenuDialog(this.cancelable, drawable1, this.stringId, this.titleId, false);
                  this.HornLigthsgenericActionName = "HornLights";
                  break;
                } 
                this.isTryingToExecuteSameAction = true;
              } 
              break;
            case 19:
              if (!onLigths.booleanValue()) {
                setWatterMarks(true, new Enums$Services[] { this.tempButton.getMyService() });
                this.LigthsgenericProgressBar = this.pbAction;
                ProgressBar progressBar = this.LigthsgenericProgressBar;
                if (progressBar != null)
                  progressBar.setVisibility(0); 
                this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.Ligths);
                CustomActionButton customActionButton4 = this.tempButton;
                if (customActionButton4 != null)
                  customActionButton4.showActionStatus(1); 
                this.LigthsgenericView = (View)drawable1;
                this.LigthsgenericActionName = "Ligths";
                onLigths = bool;
                this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
                this.actionCode = Enums$Services.Ligths.GetCodeString();
                this.csvParams = "1";
                this.deviceCSVParams = "";
                (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
                break;
              } 
              this.isTryingToExecuteSameAction = true;
              break;
            case 18:
              onNavigation = bool;
              googleMapVO1 = new GoogleMapVO();
              googleMapVO1.setKEY_SET_ENGINE_SOURCE(Enums$navigationProcess.Navigation);
              openTomTomMap(googleMapVO1);
              break;
            case 17:
              if (!onCloseDoorsClicked.booleanValue()) {
                if (!onCloseDoors.booleanValue()) {
                  onCloseDoorsClicked = bool;
                  this.DoorsLockgenericProgressBar = this.pbAction;
                  this.DoorsLockgenericView = (View)googleMapVO1;
                  this.drawableId = 2131165293;
                  drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_lockonstar, this.drawableId);
                  this.stringId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_usteddeseacerrar_2, 2131689645);
                  this.titleId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accioncerrarpuertas_1, 2131689854);
                  createMenuDialog(this.cancelable, drawable, this.stringId, this.titleId, false);
                  this.DoorsLockgenericActionName = "DoorsLock";
                  break;
                } 
                this.isTryingToExecuteSameAction = true;
              } 
              break;
            case 16:
              if (!onOpenDoorsClicked.booleanValue()) {
                if (!onOpenDoors.booleanValue()) {
                  onOpenDoorsClicked = bool;
                  this.DoorsUnlockgenericProgressBar = this.pbAction;
                  this.DoorsUnlockgenericView = (View)drawable;
                  this.drawableId = 2131165303;
                  drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_unlockonstar, this.drawableId);
                  this.stringId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_usteddeseaabrir_2, 2131689643);
                  this.titleId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionabrirpuertas_1, 2131689842);
                  createMenuDialog(this.cancelable, drawable, this.stringId, this.titleId, false);
                  this.DoorsUnlockgenericActionName = "DoorsUnlock";
                  break;
                } 
                this.isTryingToExecuteSameAction = true;
              } 
              break;
          } 
        } else {
          CustomActionButton customActionButton2 = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.ActionNotifications);
          enterNotification(this.pbAction, (View)drawable, customActionButton2);
        } 
      } else {
        this.dialogAlert = null;
        this.dialogMenu = null;
        this.isSpeedDialog = false;
        Intent intent = new Intent((Context)this, SettingsNewActivity.class);
        this.REQUEST_CODE = 3;
        startActivityForResult(intent, this.REQUEST_CODE);
      } 
      if (this.isTryingToExecuteSameAction && !isActionInProcessDialogShown.booleanValue()) {
        isActionInProcessDialogShown = bool;
        noRepeatSameActionDialog();
      } 
    } 
  }
  
  private void onError() {
    boolean bool = onFindMe.booleanValue();
    Boolean bool1 = Boolean.valueOf(false);
    if (bool) {
      onFindMe = bool1;
      getUltimeFindme();
      ProgressBar progressBar = this.findMeProBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } else if (GlobalMembers.onFollowMeRun.booleanValue()) {
      onFollowMe = bool1;
      GlobalMembers.onFollowMeRun = bool1;
      String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_unable_to_get_coordinates, 2131690064);
      Toast.makeText((Context)this.rtApp, str, 1).show();
      this.FollowmegenericView = ((GetCommandStatusVO)listCmdStat.get(0)).getButtonSelected();
      this.FollowmegenericProgressBar = (ProgressBar)this.FollowmegenericView.findViewById(2131296940);
      ProgressBar progressBar = this.FollowmegenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
      asignImage(this.FollowmegenericView, 0);
      Utilities.logActions(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getName(), "2", "3");
    } else if (listCmdStat.size() > 0) {
      this.FollowmegenericView = ((GetCommandStatusVO)listCmdStat.get(0)).getButtonSelected();
      this.FollowmegenericProgressBar = (ProgressBar)this.FollowmegenericView.findViewById(2131296940);
      ProgressBar progressBar = this.FollowmegenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
      asignImage(this.FollowmegenericView, 0);
      Utilities.logActions(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getName(), "2", "3");
    } 
    if (listCmdStat.size() > 0) {
      this.dbFun = new DBFunctions(mContext);
      this.dbFun.updateHistorical(bool1, ((GetCommandStatusVO)listCmdStat.get(this.currentAction)).getCommandStatus(), "2", "0", "0", Utilities.getActualTime());
      AlertOn();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.rtApp.getDeviceTypeId());
      stringBuilder.append("_ID");
      PreferenceRT.SetValuePreference(stringBuilder.toString(), true, (Context)this);
      this.idInsertControl = -1L;
      valueIdAction(((GetCommandStatusVO)listCmdStat.get(0)).getActionId());
      listCmdStat.remove(0);
    } 
  }
  
  private void openTomTomMap(GoogleMapVO paramGoogleMapVO) {
    if (ContextCompat.checkSelfPermission((Context)this, "android.permission.ACCESS_FINE_LOCATION") != 0 && ContextCompat.checkSelfPermission((Context)this, "android.permission.ACCESS_COARSE_LOCATION") != 0)
      return; 
    if (!((LocationManager)getSystemService("location")).isProviderEnabled("gps")) {
      dialogNoGPSActive();
    } else if (!istheftautostolen()) {
      Intent intent = new Intent((Context)this, ActivityMapViewerG.class);
      Bundle bundle = new Bundle();
      bundle.putSerializable("googleObject", (Serializable)paramGoogleMapVO);
      intent.putExtras(bundle);
      startActivity(intent);
    } 
  }
  
  private void openWebViewPaymentsHistory() {
    toggleDrawerMenu();
    String str5 = this.rtApp.getUserAccessData()[0];
    String str1 = this.rtApp.getUserAccessData()[1];
    String str4 = GlobalMembers.codeCountry;
    String str3 = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getSerialnumber();
    String str2 = this.dbFun.getCurentIDGMT(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
    (new GetURLPaymentsHistoryTask((Context)this, new AsyncResponseList() {
          final MainActivity this$0;
          
          public void processFinish(List<String> param1List) {
            if (param1List != null && !param1List.isEmpty()) {
              String str1 = param1List.get(0);
              String str2 = param1List.get(1);
              Intent intent = new Intent((Context)MainActivity.this, GenericWVActivity.class);
              intent.putExtra(GenericWVActivity.EXTRA_TYPE_TAG, 3);
              intent.putExtra(GenericWVActivity.EXTRA_URL, str2);
              intent.putExtra("token", str1);
              MainActivity.this.startActivity(intent);
            } else {
              MainActivity mainActivity = MainActivity.this;
              String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)MainActivity.this, str, 1).show();
            } 
          }
        }str2)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str5, str1, str4, str3 });
  }
  
  private void paymentCardInfoFunction() {
    toggleDrawerMenu();
    (new PaymentCard_Task((Context)this, new PaymentCard_Interface() {
          final MainActivity this$0;
          
          public void processFinish(PaymentCardResponse param1PaymentCardResponse) {
            if (param1PaymentCardResponse != null) {
              String[] arrayOfString = param1PaymentCardResponse.getGcmures3().split("-");
              if (arrayOfString.length > 0) {
                if (arrayOfString[0].trim().equals("0")) {
                  try {
                    if (param1PaymentCardResponse.getGcmures2().contains("https")) {
                      Intent intent = new Intent();
                      this(PaymentCardInfo.class);
                      Bundle bundle = new Bundle();
                      this();
                      bundle.putSerializable("resultObject", (Serializable)param1PaymentCardResponse);
                      intent.putExtras(bundle);
                      MainActivity.this.startActivity(intent);
                    } else {
                      String str = Utilities.getStringFromConfigList((Context)MainActivity.this, MainActivity.this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                      Toast.makeText((Context)MainActivity.this, str, 1).show();
                    } 
                  } catch (MalformedURLException malformedURLException) {
                    Utilities.escribeArchivo("MainActivity", "paymentCardInfoFunction", malformedURLException.getMessage());
                    MainActivity mainActivity = MainActivity.this;
                    String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                    Toast.makeText((Context)MainActivity.this, str, 1).show();
                  } 
                } else {
                  MainActivity mainActivity1 = MainActivity.this;
                  String str1 = Utilities.getStringFromConfigList((Context)mainActivity1, mainActivity1.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
                  MainActivity mainActivity2 = MainActivity.this;
                  String str2 = Utilities.getStringFromConfigList((Context)mainActivity2, mainActivity2.stringsResourcesVO.renovacion_popup_lbl_transaccionincompleta, 2131690340);
                  MainActivity mainActivity3 = MainActivity.this;
                  String str3 = Utilities.getStringFromConfigList((Context)mainActivity3, mainActivity3.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
                  MainActivity mainActivity4 = MainActivity.this;
                  String str4 = Utilities.getStringFromConfigList((Context)mainActivity4, mainActivity4.stringsResourcesVO.global_popup_btn_si_1, 2131689952);
                  final Dialog dialog1 = Utilities.simpleDialog((Context)MainActivity.this, null, str1, str2, true, str3, false, str4, false);
                  MainActivity.access$11202(MainActivity.this, (Button)dialog.findViewById(2131296343));
                  MainActivity.this.btnOK.setOnClickListener(new View.OnClickListener(this) {
                        final Dialog val$dialog1;
                        
                        public void onClick(View param2View) {
                          dialog1.dismiss();
                        }
                      });
                  dialog.show();
                } 
              } else {
                MainActivity mainActivity = MainActivity.this;
                String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText((Context)MainActivity.this, str, 1).show();
              } 
            } else {
              MainActivity mainActivity = MainActivity.this;
              String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)MainActivity.this, str, 1).show();
            } 
          }
        }null)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void paymentHistoricalFunction() {
    (new GetPaymentHistoricalTask((Context)this, new Base_Interface() {
          final MainActivity this$0;
          
          public void onFail(String param1String) {}
          
          public void onSuccess(String param1String) {
            try {
              if (param1String.contains("https")) {
                PaymentCardResponse paymentCardResponse = new PaymentCardResponse();
                this("", param1String, "");
                Intent intent = new Intent();
                this(PaymentCardInfo.class);
                Bundle bundle = new Bundle();
                this();
                bundle.putSerializable("resultObject", (Serializable)paymentCardResponse);
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
              } else {
                param1String = Utilities.getStringFromConfigList((Context)MainActivity.this, MainActivity.this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText((Context)MainActivity.this, param1String, 1).show();
              } 
            } catch (MalformedURLException malformedURLException) {
              MainActivity.onRenewalDialog = Boolean.valueOf(false);
              Utilities.escribeArchivo("MainActivity", "paymentCardInfoFunction", malformedURLException.getMessage());
              MainActivity mainActivity = MainActivity.this;
              String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)MainActivity.this, str, 1).show();
            } 
          }
        }null)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void paymentProcessFunction() {
    (new GetPaymentProcessTask((Context)this, new Base_Interface() {
          final MainActivity this$0;
          
          public void onFail(String param1String) {}
          
          public void onSuccess(String param1String) {
            try {
              if (param1String.contains("https")) {
                PaymentCardResponse paymentCardResponse = new PaymentCardResponse();
                this("", param1String, "");
                Intent intent = new Intent();
                this(PaymentCardInfo.class);
                Bundle bundle = new Bundle();
                this();
                bundle.putSerializable("resultObject", (Serializable)paymentCardResponse);
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
              } else {
                param1String = Utilities.getStringFromConfigList((Context)MainActivity.this, MainActivity.this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText((Context)MainActivity.this, param1String, 1).show();
              } 
            } catch (MalformedURLException malformedURLException) {
              MainActivity.onRenewalDialog = Boolean.valueOf(false);
              Utilities.escribeArchivo("MainActivity", "paymentCardInfoFunction", malformedURLException.getMessage());
              MainActivity mainActivity = MainActivity.this;
              String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)MainActivity.this, str, 1).show();
            } 
          }
        }null)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void paymentsHistoryFunction() {
    toggleDrawerMenu();
    String str = this.dbFun.getCurentIDGMT(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
    (new GetPaymentsHistoryTask((Context)this, new AsyncResponse() {
          final MainActivity this$0;
          
          public void processFinish(String param1String) {
            if (param1String != null && !param1String.isEmpty()) {
              Intent intent = new Intent((Context)MainActivity.this, PaymentHistoryActivity.class);
              Bundle bundle = new Bundle();
              bundle.putString("service_response", param1String);
              intent.putExtras(bundle);
              MainActivity.this.startActivity(intent);
            } else {
              MainActivity mainActivity = MainActivity.this;
              String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)MainActivity.this, str, 1).show();
            } 
          }
        }str)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void pidFunction(View paramView) {
    ((CustomActionButton)paramView).showActionStatus(0);
    boolean bool = (new DBFunctions(mContext)).userDataTableHandler(this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), "", true);
    Utilities.showTheftAutoBanner((TextView)findViewById(2131297163), (Context)this, bool);
    if (bool)
      return; 
    startActivity(new Intent((Context)this, PIDActivity.class));
  }
  
  private void pushVersionHandler() {
    if (GlobalMembers.isHMIEnabled || GlobalMembers.isMAPUpdateEnabled)
      initBluetooth(); 
    if (isBtAndPlatinumConnected()) {
      PushNotificationsVO pushNotificationsVO1;
      Drawable drawable;
      PushNotificationsVO pushNotificationsVO2 = new PushNotificationsVO();
      new PushNotificationsVO();
      if (GlobalMembers.MAP_UPDATE_VERSION == GlobalMembers.MAP_UPDATE_WITH_PUSH) {
        pushNotificationsVO1 = this.dbFun.getMapUpdateMapa();
      } else {
        pushNotificationsVO1 = pushNotificationsVO2;
        if (GlobalMembers.MAP_UPDATE_VERSION == GlobalMembers.MAP_UPDATE_SOCKETS) {
          if (this.mapUpdateVO == null)
            this.mapUpdateVO = this.dbFun.getMapUpdateMapaData(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId()); 
          String[] arrayOfString = this.mapUpdateVO.getUpgradeOldversionNewversion().split("__");
          pushNotificationsVO2.setOldVersion(arrayOfString[0].trim());
          pushNotificationsVO2.setNewVersion(arrayOfString[1].trim());
          pushNotificationsVO2.setFileName(this.mapUpdateVO.getServerFileName().substring(0, this.mapUpdateVO.getServerFileName().length() - 4).trim());
          GlobalMembers.mapFileName = this.mapUpdateVO.getServerFileName().trim();
          pushNotificationsVO1 = pushNotificationsVO2;
        } 
      } 
      File file = UtilitiesFile.getFileFromStringFile((new GetHexDumpMap()).getMapUpdateFile(GlobalMembers.mapFileName));
      file.exists();
      file.isDirectory();
      if (!file.exists() || file.isDirectory()) {
        mapUpdateOnAutomatic();
        getmNPlatinum().sendTextForTTS(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.useConfigOrPush, 2131690481));
        return;
      } 
      if (file.exists() && GlobalMembers.platinumBTVersion == GlobalMembers.BT_P8I_DISCRETE && !GlobalMembers.MAP_UPDATE_CONFIRMED)
        confirmDialogNewMapUpdate(); 
      if (!this.dbFun.isMapUpdateEmptyPart()) {
        PushNotificationsVO pushNotificationsVO = this.dbFun.getMapUpdateMapaPart();
        GlobalMembers.MAP_UPDATE_ON_PARTS = Boolean.valueOf(true);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pushNotificationsVO.getFileName());
        stringBuilder.append("_Part");
        stringBuilder.append(pushNotificationsVO.getOldVersion());
        stringBuilder.append(".tbz");
        GlobalMembers.lastMapUpdateFile = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(pushNotificationsVO.getFileName());
        stringBuilder.append("_Part");
        stringBuilder.append(pushNotificationsVO.getNewVersion());
        stringBuilder.append(".tbz");
        GlobalMembers.CURRENT_NAME = stringBuilder.toString();
        GlobalMembers.downloadComplete = true;
      } 
      if (!GlobalMembers.downloadComplete)
        return; 
      GlobalMembers.mapFileName = pushNotificationsVO1.getFileName();
      if (GlobalMembers.MAP_UPDATE_ON_PARTS.booleanValue())
        GlobalMembers.mapFileName = GlobalMembers.CURRENT_NAME; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("");
      stringBuilder1.append(PreferenceRT.GetValuePreference(Enums$SettingsPreference.p8PrimaVer, "", (Context)this));
      String str1 = stringBuilder1.toString();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("");
      stringBuilder2.append(PreferenceRT.GetValuePreference(Enums$SettingsPreference.p8MapsVer, "", (Context)this));
      String str2 = stringBuilder2.toString();
      this.p8PrimaVersion = 24101;
      if (str1.equalsIgnoreCase("")) {
        this.cancelable = true;
        this.drawableId = 2131165285;
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.BTFirstTime, 2131689479);
        String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_bluetooth_title, 2131690030);
        this.stringId = str1;
        this.titleId = str;
        drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.actions_dialog_bluetooth_pair, this.drawableId);
        createMenuDialog(this.cancelable, drawable, this.stringId, this.titleId, false);
      } else if (!str2.toLowerCase().contains(drawable.getOldVersion().trim().toLowerCase()) && !str2.toLowerCase().contains(drawable.getNewVersion().trim().toLowerCase())) {
        if (!str2.substring(0, 2).equalsIgnoreCase(drawable.getOldVersion().substring(0, 2))) {
          String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_map_update_diferent_country, 2131690037);
          if (isBtAndPlatinumConnected())
            getmNPlatinum().sendTextForTTS(str); 
          Toast.makeText((Context)this, str, 1).show();
        } else {
          String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_map_update_latest_version, 2131690040);
          if (isBtAndPlatinumConnected())
            getmNPlatinum().sendTextForTTS(str); 
          Toast.makeText((Context)this, str, 1).show();
          UtilitiesFile.deleteMapUpdateFile(mContext, GlobalMembers.mapFileName);
        } 
      } else if (GlobalMembers.myListenerDownComplete != null) {
        int i = GlobalMembers.platinumBTVersion;
        i = GlobalMembers.BT_P8I_DISCRETE;
        ManagerNotificationPlatinum managerNotificationPlatinum = getmNPlatinum();
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.map_update_start, 2131690087);
        managerNotificationPlatinum.sendTextForTTS(str1);
        Toast.makeText(getApplicationContext(), str1, 1).show();
        this.mapUpdateVO.setFileMapStatus(4);
        this.dbFun.updateMapUpdateData(this.mapUpdateVO);
        this.isFirstPackaget = true;
        GlobalMembers.myListenerDownComplete.onEvent();
      } 
    } else {
      String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.no_bt_conecction, 2131690201);
      Toast.makeText(getApplicationContext(), str, 1).show();
    } 
  }
  
  private void reSendCommand(Object[] paramArrayOfObject, View paramView, String paramString) {
    boolean bool1 = Utilities.dateCountDown(this.dateInFutureForActions);
    Boolean bool = Boolean.valueOf(false);
    if (!bool1) {
      if (!onFindMe.booleanValue()) {
        this.sendCommand = new SendCommand();
        this.sendCommand.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramArrayOfObject);
      } else {
        this.sendFindMeCmd = new SendFindMeCommand();
        this.sendFindMeCmd.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
      } 
    } else {
      String str1 = (String)paramArrayOfObject[0];
      String str2 = Utilities.getDateTime(this.deviceId, GlobalMembers.contexGlobal);
      this.dbFun = new DBFunctions(mContext);
      this.dbFun.updateHistorical(Boolean.valueOf(true), String.valueOf(this.idInsertControl), "2", "0", "0", str2);
      AlertOn();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.rtApp.getDeviceTypeId());
      stringBuilder.append("_ID");
      PreferenceRT.SetValuePreference(stringBuilder.toString(), true, (Context)this);
      this.idInsertControl = -1L;
      if (str1.equals(Enums$Services.DoorsUnlock.GetCodeString())) {
        onOpenDoors = bool;
        ProgressBar progressBar = this.DoorsUnlockgenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.DoorsUnlockgenericView, 0);
        } 
      } 
      if (str1.equals(Enums$Services.HornLigths.GetCodeString())) {
        onHornLights = bool;
        ProgressBar progressBar = this.HornLigthsgenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.HornLigthsgenericView, 0);
        } 
      } 
      if (str1.equals(Enums$Services.Ligths.GetCodeString())) {
        onLigths = bool;
        ProgressBar progressBar = this.LigthsgenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.LigthsgenericView, 0);
        } 
      } 
      if (str1.equals(Enums$Services.Horn.GetCodeString()) || str1.equals(Enums$Services.HornF1.GetCodeString())) {
        this.HornLigthsgenericProgressBar.setVisibility(8);
        ProgressBar progressBar = this.HorngenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.HorngenericView, 0);
        } 
      } 
      if (str1.equals(Enums$Services.FindMe.GetCodeString())) {
        onFindMe = bool;
        if (this.findMeProBar != null) {
          this.ImageResponseFindme.setVisibility(8);
          this.findMeProBar.setVisibility(8);
        } 
        Utilities.logActions(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getName(), "1", "0");
        if (paramString.equals("408")) {
          this.ImageResponseFindme.setVisibility(0);
          (new CountDownTimer(105000L, 1000L) {
              final MainActivity this$0;
              
              public void onFinish() {
                MainActivity.this.ImageResponseFindme.setVisibility(8);
                MainActivity mainActivity = MainActivity.this;
                String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText(MainActivity.this.getApplicationContext(), str, 1).show();
                Utilities.SendNotify((Context)MainActivity.this, "1", "title", str);
              }
              
              public void onTick(long param1Long) {}
            }).start();
        } else {
          Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.no_ejecutado_azul_sintexto_fondo, 2131165583);
          this.ImageResponseFindme.setImageDrawable(drawable);
          this.ImageResponseFindme.setVisibility(0);
          (new CountDownTimer(15000L, 1000L) {
              final MainActivity this$0;
              
              public void onFinish() {
                MainActivity.this.ImageResponseFindme.setVisibility(8);
                Drawable drawable = Utilities.getDrawableFromConfigList((Context)MainActivity.this, DrawableResourcesVO.palomita_azul_fondo, 2131165626);
                MainActivity.this.ImageResponseFindme.setImageDrawable(drawable);
                MainActivity mainActivity = MainActivity.this;
                String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText(MainActivity.this.getApplicationContext(), str, 1).show();
                Utilities.SendNotify((Context)MainActivity.this, "1", "title", str);
              }
              
              public void onTick(long param1Long) {}
            }).start();
        } 
      } 
      if (str1.equals(Enums$Services.FollowMe.GetCodeString()) || str1.equals(Enums$Services.FollowMeUUx.GetCodeString())) {
        onFollowMe = bool;
        GlobalMembers.onFollowMeRun = bool;
        ProgressBar progressBar = this.FollowmegenericProgressBar;
        if (progressBar != null)
          progressBar.setVisibility(8); 
        if (GlobalMembers.lastSendCommandIdResponse == 10) {
          String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_followme_running, 2131690035);
          Toast.makeText((Context)this.rtApp, str, 1).show();
        } 
        Utilities.logActions(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getName(), "2", "0");
        if (paramString.equals("408")) {
          this.imgResponse = (TextView)this.FollowmegenericView.findViewById(2131296611);
          Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.palomita_azul_fondo, 2131165626);
          this.imgResponse.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
          this.imgResponse.setVisibility(0);
          this.imgResponseTemp = this.imgResponse;
          (new CountDownTimer(105000L, 1000L) {
              final MainActivity this$0;
              
              public void onFinish() {
                MainActivity.this.imgResponseTemp.setVisibility(8);
                MainActivity mainActivity = MainActivity.this;
                String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText(MainActivity.this.getApplicationContext(), str, 1).show();
                Utilities.SendNotify((Context)MainActivity.this, "2", "title", str);
              }
              
              public void onTick(long param1Long) {}
            }).start();
        } else {
          this.imgResponse = null;
          this.view = null;
          this.view = this.FollowmegenericView;
          this.imgResponse = (TextView)this.view.findViewById(2131296611);
          Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.no_ejecutado_azul_fondo, 2131165581);
          this.imgResponse.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
          this.imgResponse.setVisibility(0);
          this.imgResponseTemp = this.imgResponse;
          (new CountDownTimer(15000L, 1000L) {
              final MainActivity this$0;
              
              public void onFinish() {
                MainActivity.this.imgResponseTemp.setVisibility(8);
                MainActivity mainActivity = MainActivity.this;
                String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText(MainActivity.this.getApplicationContext(), str, 1).show();
                Utilities.SendNotify((Context)MainActivity.this, "2", "title", str);
              }
              
              public void onTick(long param1Long) {}
            }).start();
        } 
      } 
      if (str1.equals(Enums$Services.DoorsLock.GetCodeString())) {
        onCloseDoors = bool;
        ProgressBar progressBar = this.DoorsLockgenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.DoorsLockgenericView, 0);
        } 
      } 
      if (str1.equals(Enums$Services.Disarm.GetCodeString())) {
        onDisarmPINCODE = bool;
        ProgressBar progressBar = this.DisarmgenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.DisarmgenericView, 0);
        } 
      } 
      if (str1.equals(Enums$Services.Parking.GetCodeString()) || str1.equals(Enums$Services.ParkingUUx.GetCodeString())) {
        onAlertParking = bool;
        ProgressBar progressBar = this.ParkinggenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.ParkinggenericView, 0);
        } 
      } 
      if (str1.equals(Enums$Services.Speed.GetCodeString()) || str1.equals(Enums$Services.SpeedUUx.GetCodeString())) {
        onAlertSpeed = bool;
        ProgressBar progressBar = this.SpeedgenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.SpeedgenericView, 0);
        } 
      } 
      if (str1.equals(Enums$Services.SpeedAlways.GetCodeString())) {
        onAlertSpeed = bool;
        ProgressBar progressBar = this.SpeedgenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.SpeedgenericView, 0);
        } 
      } 
      if (str1.equals(Enums$Services.valet.GetCodeString())) {
        onAlertValet = bool;
        ProgressBar progressBar = this.valetgenericProgressBar;
        if (progressBar != null) {
          progressBar.setVisibility(8);
          asignImage(this.valetgenericView, 0);
        } 
      } 
    } 
  }
  
  private void reloadFragmentView() {}
  
  private void renewalPlanFunction() {
    GlobalMembers.isActionRenewalFromMain = false;
    (new GetRenewalPlansTask((Context)this, new RenewalPlans_Interface() {
          final MainActivity this$0;
          
          public void processFinish(RenewalPlansListResponseO param1RenewalPlansListResponseO) {
            if (param1RenewalPlansListResponseO != null) {
              if (param1RenewalPlansListResponseO.getCpres5() != null && param1RenewalPlansListResponseO.getCpres5().size() > 0) {
                Intent intent = new Intent((Context)MainActivity.this, OriginalRenewalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("service_response", (Serializable)param1RenewalPlansListResponseO);
                bundle.putString("previous_class", MainActivity.class.getSimpleName());
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
              } else {
                MainActivity mainActivity = MainActivity.this;
                String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText((Context)MainActivity.this, str, 1).show();
              } 
            } else {
              MainActivity mainActivity = MainActivity.this;
              String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText((Context)MainActivity.this, str, 1).show();
            } 
          }
        })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    isRenewalpush = false;
    onRenewalDialog = Boolean.valueOf(false);
  }
  
  private void renewalVehiclesListFunction() {
    PreferenceRT.SetStringPreference(GlobalMembers.valorPO, "Main", onstarApplication.getContext());
    Intent intent = new Intent(getApplicationContext(), RenewalVehiclesListActivity.class);
    Bundle bundle = new Bundle();
    intent.addFlags(268435456);
    bundle.putSerializable("almostExpired", (Serializable)this.arraylistVehiclesAlmostExpired);
    bundle.putSerializable("expired", (Serializable)this.arraylistVehiclesExpired);
    bundle.putSerializable("normal", (Serializable)this.arraylistVehiclesNormal);
    intent.putExtras(bundle);
    startActivity(intent);
  }
  
  private void returnOriginalDrawerList() {
    byte b = 0;
    boolean bool2 = false;
    boolean bool1;
    for (bool1 = false; b < this.drawerMenuList.size(); bool1 = bool) {
      boolean bool;
      if (((ListButton)this.drawerMenuList.get(b)).getMyService() == Enums$Services.WebPage) {
        bool = true;
      } else {
        bool = bool1;
        if (((ListButton)this.drawerMenuList.get(b)).getMyService() == Enums$Services.ActionMyPlan) {
          bool2 = true;
          bool = bool1;
        } 
      } 
      b++;
    } 
    if (bool2) {
      if (bool1) {
        this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 1);
      } else {
        this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(mContext, 4);
      } 
      this.drawerMenuList = UserInterfaceUtils.getDrawerMenu(Boolean.valueOf(false), this.rtApp, mContext);
      this.listButtonAdapter = new ListButtonAdapter(this.drawerMenuList);
      this.mDrawerList.setAdapter((ListAdapter)this.listButtonAdapter);
    } 
  }
  
  private void reviceNotification() {
    if (getIntent().getExtras() != null) {
      if (getIntent().getExtras().getInt("Acc") == Enums$Services.FindMeNotification.GetCode())
        getIntent().removeExtra("Acc"); 
      if (getIntent().getExtras().getInt("Acc") == Enums$Services.DTCNotification.GetCode()) {
        startActivity((new Intent((Context)this, RemoteDiagnosticActivity.class)).putExtra("Acc", getIntent().getExtras().getInt("Acc")).putExtra("DeviceId", getIntent().getExtras().getString("DeviceId")));
        getIntent().removeExtra("Acc");
        getIntent().removeExtra("DeviceId");
      } 
    } 
  }
  
  private void saveCopy(UserDevicesVO paramUserDevicesVO) {
    GlobalMembers.tempMainActionsWakeUpCar = new LinkedHashMap<Object, Object>();
    for (Map.Entry entry : paramUserDevicesVO.getMainActions().entrySet()) {
      LinkedHashMap<Object, Object> linkedHashMap1 = new LinkedHashMap<Object, Object>();
      LinkedHashMap linkedHashMap = (LinkedHashMap)entry.getValue();
      linkedHashMap1.put("water", linkedHashMap.get("water"));
      linkedHashMap1.put("hasmap", linkedHashMap.get("hasmap"));
      GlobalMembers.tempMainActionsWakeUpCar.put(entry.getKey(), linkedHashMap1);
    } 
    GlobalMembers.tempBottomActions = new LinkedHashMap<Object, Object>();
    for (Map.Entry entry : paramUserDevicesVO.getButtomActions().entrySet()) {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      LinkedHashMap linkedHashMap1 = (LinkedHashMap)entry.getValue();
      linkedHashMap.put("water", linkedHashMap1.get("water"));
      linkedHashMap.put("hasmap", linkedHashMap1.get("hasmap"));
      GlobalMembers.tempBottomActions.put(entry.getKey(), linkedHashMap);
    } 
  }
  
  public static void sendBTResponseRecived(String paramString1, String paramString2) {
    Intent intent = new Intent("com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT");
    intent.putExtra("Response", paramString1);
    intent.putExtra("Response2", paramString2);
    mContext.sendBroadcast(intent);
  }
  
  public static void sendBTResponseRecived2(String paramString1, String paramString2, boolean paramBoolean, String[] paramArrayOfString) {
    Intent intent = new Intent("com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT_2");
    Bundle bundle = new Bundle();
    bundle.putString("Command", paramString1);
    bundle.putString("Subcommand", paramString2);
    bundle.putBoolean("ContieneWayPoints", paramBoolean);
    bundle.putStringArray("Message", paramArrayOfString);
    try {
      intent.putExtra("extra", bundle);
      mContext.sendBroadcast(intent);
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error", "ActionNotFound");
    } 
  }
  
  private void sendChunkToVehicle(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield isFirstPackaget : Z
    //   4: ifeq -> 123
    //   7: invokestatic isBtAndPlatinumConnected : ()Z
    //   10: ifeq -> 123
    //   13: iload_1
    //   14: sipush #10000
    //   17: sipush #400
    //   20: aload_0
    //   21: getfield mapFileSize : I
    //   24: invokestatic calculateEstimateTime : (IIII)I
    //   27: istore_2
    //   28: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   31: astore #5
    //   33: iload_2
    //   34: ifne -> 59
    //   37: aload #5
    //   39: aload_0
    //   40: aload_0
    //   41: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   44: getfield main_activity_map_update_total_time_0 : Ljava/lang/String;
    //   47: ldc_w 2131690055
    //   50: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   53: invokevirtual sendTextForTTS : (Ljava/lang/String;)V
    //   56: goto -> 118
    //   59: aload_0
    //   60: aload_0
    //   61: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   64: getfield main_activity_map_update_total_time : Ljava/lang/String;
    //   67: ldc_w 2131690054
    //   70: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   73: astore #7
    //   75: new java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial <init> : ()V
    //   82: astore #6
    //   84: aload #6
    //   86: iload_2
    //   87: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: aload #6
    //   93: ldc_w ''
    //   96: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: aload #5
    //   102: aload #7
    //   104: ldc_w '%s'
    //   107: aload #6
    //   109: invokevirtual toString : ()Ljava/lang/String;
    //   112: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   115: invokevirtual sendTextForTTS : (Ljava/lang/String;)V
    //   118: aload_0
    //   119: iconst_0
    //   120: putfield isFirstPackaget : Z
    //   123: aload_0
    //   124: getfield id : I
    //   127: ifne -> 210
    //   130: aload_0
    //   131: aload_0
    //   132: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   135: getfield main_activity_map_update_step_3_title : Ljava/lang/String;
    //   138: ldc_w 2131690049
    //   141: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   144: astore #5
    //   146: aload_0
    //   147: aload_0
    //   148: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   151: getfield main_activity_map_update_step_3_message : Ljava/lang/String;
    //   154: ldc_w 2131690048
    //   157: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   160: astore #6
    //   162: aload_0
    //   163: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   166: aload #5
    //   168: invokevirtual setContentTitle : (Ljava/lang/CharSequence;)Landroidx/core/app/NotificationCompat$Builder;
    //   171: pop
    //   172: aload_0
    //   173: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   176: aload #6
    //   178: invokevirtual setContentText : (Ljava/lang/CharSequence;)Landroidx/core/app/NotificationCompat$Builder;
    //   181: pop
    //   182: aload_0
    //   183: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   186: iconst_0
    //   187: iconst_0
    //   188: iconst_1
    //   189: invokevirtual setProgress : (IIZ)Landroidx/core/app/NotificationCompat$Builder;
    //   192: pop
    //   193: aload_0
    //   194: getfield notificationManagerMapUpdate : Landroid/app/NotificationManager;
    //   197: ldc_w 123456789
    //   200: aload_0
    //   201: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   204: invokevirtual build : ()Landroid/app/Notification;
    //   207: invokevirtual notify : (ILandroid/app/Notification;)V
    //   210: new com/roadtrack/onstar/VO/ChunkObjectVO
    //   213: dup
    //   214: invokespecial <init> : ()V
    //   217: astore #12
    //   219: aconst_null
    //   220: astore #8
    //   222: aconst_null
    //   223: astore #10
    //   225: aconst_null
    //   226: astore #11
    //   228: aconst_null
    //   229: astore #9
    //   231: sipush #10000
    //   234: newarray byte
    //   236: astore #13
    //   238: aload #10
    //   240: astore #6
    //   242: aload #11
    //   244: astore #7
    //   246: new com/roadtrack/onstar/utils/GetHexDumpMap
    //   249: astore #5
    //   251: aload #10
    //   253: astore #6
    //   255: aload #11
    //   257: astore #7
    //   259: aload #5
    //   261: invokespecial <init> : ()V
    //   264: aload #10
    //   266: astore #6
    //   268: aload #11
    //   270: astore #7
    //   272: getstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_ON_PARTS : Ljava/lang/Boolean;
    //   275: invokevirtual booleanValue : ()Z
    //   278: ifeq -> 302
    //   281: aload #10
    //   283: astore #6
    //   285: aload #11
    //   287: astore #7
    //   289: aload #5
    //   291: getstatic com/roadtrack/onstar/BO/GlobalMembers.mapFileName : Ljava/lang/String;
    //   294: invokevirtual getMapUpdateFile : (Ljava/lang/String;)Ljava/lang/String;
    //   297: astore #5
    //   299: goto -> 320
    //   302: aload #10
    //   304: astore #6
    //   306: aload #11
    //   308: astore #7
    //   310: aload #5
    //   312: getstatic com/roadtrack/onstar/BO/GlobalMembers.mapFileName : Ljava/lang/String;
    //   315: invokevirtual getMapUpdateFile : (Ljava/lang/String;)Ljava/lang/String;
    //   318: astore #5
    //   320: aload #10
    //   322: astore #6
    //   324: aload #11
    //   326: astore #7
    //   328: aload_0
    //   329: aload #5
    //   331: invokestatic getFileFromStringFile : (Ljava/lang/String;)Ljava/io/File;
    //   334: putfield assembled : Ljava/io/File;
    //   337: aload #9
    //   339: astore #6
    //   341: new java/io/RandomAccessFile
    //   344: astore #5
    //   346: aload #9
    //   348: astore #6
    //   350: aload #5
    //   352: aload_0
    //   353: getfield assembled : Ljava/io/File;
    //   356: ldc_w 'r'
    //   359: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   362: iload_1
    //   363: i2l
    //   364: lstore_3
    //   365: aload #5
    //   367: lload_3
    //   368: invokevirtual seek : (J)V
    //   371: aload #5
    //   373: aload #13
    //   375: invokevirtual read : ([B)I
    //   378: istore_2
    //   379: aload #5
    //   381: astore #6
    //   383: aload #5
    //   385: astore #7
    //   387: aload #5
    //   389: invokevirtual close : ()V
    //   392: goto -> 476
    //   395: astore #5
    //   397: goto -> 922
    //   400: astore #5
    //   402: goto -> 722
    //   405: astore #6
    //   407: aload #6
    //   409: astore #8
    //   411: goto -> 686
    //   414: astore #6
    //   416: aload #6
    //   418: astore #7
    //   420: goto -> 438
    //   423: astore #8
    //   425: aload #6
    //   427: astore #5
    //   429: goto -> 686
    //   432: astore #7
    //   434: aload #8
    //   436: astore #5
    //   438: aload #5
    //   440: astore #6
    //   442: ldc_w 'MainActivity'
    //   445: ldc_w 'RandomAccesFile: '
    //   448: aload #7
    //   450: invokevirtual getMessage : ()Ljava/lang/String;
    //   453: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   456: aload #5
    //   458: ifnull -> 474
    //   461: aload #5
    //   463: astore #6
    //   465: aload #5
    //   467: astore #7
    //   469: aload #5
    //   471: invokevirtual close : ()V
    //   474: iconst_0
    //   475: istore_2
    //   476: iload_2
    //   477: ifge -> 555
    //   480: aload #5
    //   482: ifnull -> 525
    //   485: aload #5
    //   487: astore #6
    //   489: aload #5
    //   491: astore #7
    //   493: aload #5
    //   495: invokevirtual close : ()V
    //   498: goto -> 525
    //   501: astore #8
    //   503: aload #5
    //   505: astore #6
    //   507: aload #5
    //   509: astore #7
    //   511: ldc_w 'MainActivity'
    //   514: ldc_w 'sendChunkToVehicle'
    //   517: aload #8
    //   519: invokevirtual getMessage : ()Ljava/lang/String;
    //   522: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   525: aload #5
    //   527: ifnull -> 554
    //   530: aload #5
    //   532: invokevirtual close : ()V
    //   535: goto -> 554
    //   538: astore #5
    //   540: ldc_w 'MainActivity'
    //   543: ldc_w 'sendChunkToVehicle'
    //   546: aload #5
    //   548: invokevirtual getMessage : ()Ljava/lang/String;
    //   551: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   554: return
    //   555: aload #5
    //   557: astore #6
    //   559: aload #5
    //   561: astore #7
    //   563: aload_0
    //   564: aload #13
    //   566: iload_2
    //   567: invokespecial escapeSpecialChars : ([BI)[B
    //   570: astore #8
    //   572: aload #5
    //   574: astore #6
    //   576: aload #5
    //   578: astore #7
    //   580: getstatic com/roadtrack/onstar/BO/GlobalMembers.MAP_UPDATE_ON_PARTS : Ljava/lang/Boolean;
    //   583: invokevirtual booleanValue : ()Z
    //   586: ifeq -> 608
    //   589: aload #5
    //   591: astore #6
    //   593: aload #5
    //   595: astore #7
    //   597: aload #12
    //   599: getstatic com/roadtrack/onstar/BO/GlobalMembers.mapFileName : Ljava/lang/String;
    //   602: invokevirtual setFileID : (Ljava/lang/String;)V
    //   605: goto -> 624
    //   608: aload #5
    //   610: astore #6
    //   612: aload #5
    //   614: astore #7
    //   616: aload #12
    //   618: getstatic com/roadtrack/onstar/BO/GlobalMembers.mapFileName : Ljava/lang/String;
    //   621: invokevirtual setFileID : (Ljava/lang/String;)V
    //   624: aload #5
    //   626: astore #6
    //   628: aload #5
    //   630: astore #7
    //   632: aload #12
    //   634: aload #8
    //   636: invokevirtual setChunk : ([B)V
    //   639: aload #5
    //   641: astore #6
    //   643: aload #5
    //   645: astore #7
    //   647: aload #12
    //   649: iload_2
    //   650: invokestatic toString : (I)Ljava/lang/String;
    //   653: invokevirtual setChunkSize : (Ljava/lang/String;)V
    //   656: aload #5
    //   658: astore #6
    //   660: aload #5
    //   662: astore #7
    //   664: aload #12
    //   666: iload_1
    //   667: invokestatic toString : (I)Ljava/lang/String;
    //   670: invokevirtual setOffset : (Ljava/lang/String;)V
    //   673: aload #5
    //   675: ifnull -> 769
    //   678: aload #5
    //   680: invokevirtual close : ()V
    //   683: goto -> 769
    //   686: aload #5
    //   688: ifnull -> 704
    //   691: aload #5
    //   693: astore #6
    //   695: aload #5
    //   697: astore #7
    //   699: aload #5
    //   701: invokevirtual close : ()V
    //   704: aload #5
    //   706: astore #6
    //   708: aload #5
    //   710: astore #7
    //   712: aload #8
    //   714: athrow
    //   715: astore #5
    //   717: goto -> 922
    //   720: astore #5
    //   722: aload #7
    //   724: astore #6
    //   726: ldc_w 'MainActivity'
    //   729: ldc_w 'sendChunkToVehicle'
    //   732: aload #5
    //   734: invokevirtual getMessage : ()Ljava/lang/String;
    //   737: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   740: aload #7
    //   742: ifnull -> 769
    //   745: aload #7
    //   747: invokevirtual close : ()V
    //   750: goto -> 769
    //   753: astore #5
    //   755: ldc_w 'MainActivity'
    //   758: ldc_w 'sendChunkToVehicle'
    //   761: aload #5
    //   763: invokevirtual getMessage : ()Ljava/lang/String;
    //   766: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   769: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   772: astore #5
    //   774: aload_0
    //   775: getfield p8PrimaVersion : I
    //   778: sipush #24100
    //   781: if_icmple -> 812
    //   784: aload #5
    //   786: aload #12
    //   788: invokevirtual getFileID : ()Ljava/lang/String;
    //   791: aload #12
    //   793: invokevirtual getOffset : ()Ljava/lang/String;
    //   796: aload #12
    //   798: invokevirtual getChunkSize : ()Ljava/lang/String;
    //   801: aload #12
    //   803: invokevirtual getChunkBytes : ()[B
    //   806: invokevirtual downloadUpdateChunk : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[B)V
    //   809: goto -> 837
    //   812: aload #5
    //   814: aload #12
    //   816: invokevirtual getFileID : ()Ljava/lang/String;
    //   819: aload #12
    //   821: invokevirtual getOffset : ()Ljava/lang/String;
    //   824: aload #12
    //   826: invokevirtual getChunkSize : ()Ljava/lang/String;
    //   829: aload #12
    //   831: invokevirtual getChunk : ()Ljava/lang/String;
    //   834: invokevirtual downloadUpdateChunk : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   837: aload_0
    //   838: aload_0
    //   839: getfield id : I
    //   842: iconst_1
    //   843: iadd
    //   844: putfield id : I
    //   847: aload_0
    //   848: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   851: aload_0
    //   852: getfield mapFileSize : I
    //   855: aload #12
    //   857: invokevirtual getOffset : ()Ljava/lang/String;
    //   860: invokestatic parseInt : (Ljava/lang/String;)I
    //   863: iconst_0
    //   864: invokevirtual setProgress : (IIZ)Landroidx/core/app/NotificationCompat$Builder;
    //   867: pop
    //   868: aload_0
    //   869: getfield notificationManagerMapUpdate : Landroid/app/NotificationManager;
    //   872: ldc_w 123456789
    //   875: aload_0
    //   876: getfield builderMapUpdate : Landroidx/core/app/NotificationCompat$Builder;
    //   879: invokevirtual build : ()Landroid/app/Notification;
    //   882: invokevirtual notify : (ILandroid/app/Notification;)V
    //   885: new android/content/Intent
    //   888: dup
    //   889: invokespecial <init> : ()V
    //   892: astore #5
    //   894: aload #5
    //   896: ldc_w 'GlobalTouchService'
    //   899: invokevirtual setAction : (Ljava/lang/String;)Landroid/content/Intent;
    //   902: pop
    //   903: aload #5
    //   905: ldc_w 'ACTION_EXTRA'
    //   908: ldc_w 'usuario_activo'
    //   911: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   914: pop
    //   915: aload_0
    //   916: aload #5
    //   918: invokevirtual sendBroadcast : (Landroid/content/Intent;)V
    //   921: return
    //   922: aload #6
    //   924: ifnull -> 951
    //   927: aload #6
    //   929: invokevirtual close : ()V
    //   932: goto -> 951
    //   935: astore #6
    //   937: ldc_w 'MainActivity'
    //   940: ldc_w 'sendChunkToVehicle'
    //   943: aload #6
    //   945: invokevirtual getMessage : ()Ljava/lang/String;
    //   948: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   951: aload #5
    //   953: athrow
    // Exception table:
    //   from	to	target	type
    //   246	251	720	java/lang/Exception
    //   246	251	715	finally
    //   259	264	720	java/lang/Exception
    //   259	264	715	finally
    //   272	281	720	java/lang/Exception
    //   272	281	715	finally
    //   289	299	720	java/lang/Exception
    //   289	299	715	finally
    //   310	320	720	java/lang/Exception
    //   310	320	715	finally
    //   328	337	720	java/lang/Exception
    //   328	337	715	finally
    //   341	346	432	java/lang/Exception
    //   341	346	423	finally
    //   350	362	432	java/lang/Exception
    //   350	362	423	finally
    //   365	379	414	java/lang/Exception
    //   365	379	405	finally
    //   387	392	400	java/lang/Exception
    //   387	392	395	finally
    //   442	456	423	finally
    //   469	474	720	java/lang/Exception
    //   469	474	715	finally
    //   493	498	501	java/io/IOException
    //   493	498	400	java/lang/Exception
    //   493	498	395	finally
    //   511	525	400	java/lang/Exception
    //   511	525	395	finally
    //   530	535	538	java/io/IOException
    //   563	572	400	java/lang/Exception
    //   563	572	395	finally
    //   580	589	400	java/lang/Exception
    //   580	589	395	finally
    //   597	605	400	java/lang/Exception
    //   597	605	395	finally
    //   616	624	400	java/lang/Exception
    //   616	624	395	finally
    //   632	639	400	java/lang/Exception
    //   632	639	395	finally
    //   647	656	400	java/lang/Exception
    //   647	656	395	finally
    //   664	673	400	java/lang/Exception
    //   664	673	395	finally
    //   678	683	753	java/io/IOException
    //   699	704	720	java/lang/Exception
    //   699	704	715	finally
    //   712	715	720	java/lang/Exception
    //   712	715	715	finally
    //   726	740	715	finally
    //   745	750	753	java/io/IOException
    //   927	932	935	java/io/IOException
  }
  
  private static void sendMessage(String paramString) {
    // Byte code:
    //   0: ldc com/roadtrack/onstar/MainActivity
    //   2: monitorenter
    //   3: aload_0
    //   4: invokevirtual length : ()I
    //   7: istore_1
    //   8: iload_1
    //   9: ifle -> 71
    //   12: getstatic com/roadtrack/onstar/BO/GlobalMembers.unitTypePlatinum : Lcom/roadtrack/onstar/Enums$UnitType;
    //   15: getstatic com/roadtrack/onstar/Enums$UnitType.Platinum_7 : Lcom/roadtrack/onstar/Enums$UnitType;
    //   18: if_acmpne -> 32
    //   21: aload_0
    //   22: ldc_w 'Cp1252'
    //   25: invokevirtual getBytes : (Ljava/lang/String;)[B
    //   28: astore_0
    //   29: goto -> 40
    //   32: aload_0
    //   33: ldc_w 'utf-8'
    //   36: invokevirtual getBytes : (Ljava/lang/String;)[B
    //   39: astore_0
    //   40: getstatic com/roadtrack/onstar/MainActivity.mChatService : Lcom/roadtrack/onstar/BO/BluetoothServiceRT;
    //   43: aload_0
    //   44: invokevirtual write : ([B)V
    //   47: goto -> 64
    //   50: astore_0
    //   51: ldc_w 'MainActivity'
    //   54: ldc_w 'Error: sendMessage'
    //   57: aload_0
    //   58: invokevirtual getMessage : ()Ljava/lang/String;
    //   61: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   64: getstatic com/roadtrack/onstar/MainActivity.mOutStringBuffer : Ljava/lang/StringBuffer;
    //   67: iconst_0
    //   68: invokevirtual setLength : (I)V
    //   71: ldc com/roadtrack/onstar/MainActivity
    //   73: monitorexit
    //   74: return
    //   75: astore_0
    //   76: ldc com/roadtrack/onstar/MainActivity
    //   78: monitorexit
    //   79: aload_0
    //   80: athrow
    // Exception table:
    //   from	to	target	type
    //   3	8	75	finally
    //   12	29	50	java/io/UnsupportedEncodingException
    //   12	29	75	finally
    //   32	40	50	java/io/UnsupportedEncodingException
    //   32	40	75	finally
    //   40	47	50	java/io/UnsupportedEncodingException
    //   40	47	75	finally
    //   51	64	75	finally
    //   64	71	75	finally
  }
  
  private void sendMessageBytes(byte[] paramArrayOfbyte) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: arraylength
    //   4: ifle -> 14
    //   7: getstatic com/roadtrack/onstar/MainActivity.mChatService : Lcom/roadtrack/onstar/BO/BluetoothServiceRT;
    //   10: aload_1
    //   11: invokevirtual write : ([B)V
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	17	finally
  }
  
  public static void sendMessagePlatinum(Object[] paramArrayOfObject) {
    /* monitor enter TypeReferenceDotClassExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
    try {
      if (mChatService == null) {
        MessagesDispatchService.terminate = true;
        /* monitor exit TypeReferenceDotClassExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
        return;
      } 
      if (mChatService.getState() != 3) {
        MessagesDispatchService.terminate = true;
        /* monitor exit TypeReferenceDotClassExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
        return;
      } 
      MessagesObjects messagesObjects = new MessagesObjects();
      this();
      enqueuedOutData(messagesObjects.assembleOutMessage(paramArrayOfObject, GlobalMembers.contexGlobal));
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: sendMessagePlatinum", exception.getMessage());
    } finally {}
    /* monitor exit TypeReferenceDotClassExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
  }
  
  private static void sendOutMessage() {
    if (OutQueue.count() > 0)
      notifyPlatinum_Inner(OutQueue.dequeue()); 
  }
  
  private void sendTomTomStatistics() {
    taskSet.executeSendTomTomStatistics_Task(new Base_Interface(this) {
          public void onFail(String param1String) {}
          
          public void onSuccess(String param1String) {}
        },  (Context)this);
  }
  
  private void setActionBarAndSpinner() {
    this.spinner_menu.setOnTouchListener(new View.OnTouchListener() {
          final MainActivity this$0;
          
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            boolean bool;
            if (MainActivity.onOpenDoors.booleanValue() || MainActivity.onCloseDoors.booleanValue() || MainActivity.onAlertParking.booleanValue() || MainActivity.onLigths.booleanValue() || MainActivity.onHornLights.booleanValue() || MainActivity.onAlertSpeed.booleanValue() || MainActivity.onFollowMe.booleanValue() || MainActivity.onFindMe.booleanValue() || MainActivity.onAlertValet.booleanValue() || MainActivity.onDisarmPINCODE.booleanValue() || MainActivity.onNotification.booleanValue() || MainActivity.onPID.booleanValue() || MainActivity.onHorn.booleanValue() || MainActivity.onDTC.booleanValue() || GlobalMembers.onfollowmeActivated) {
              bool = true;
            } else {
              bool = false;
            } 
            if (param1MotionEvent.getAction() == 1 && bool && !MainActivity.isActionInProcessDialogShown.booleanValue()) {
              MainActivity.isActionInProcessDialogShown = Boolean.valueOf(true);
              MainActivity.this.noRepeatSameActionDialog();
            } 
            return bool;
          }
        });
  }
  
  public static void setBadge(Context paramContext, int paramInt) {
    String str = getLauncherClassName(paramContext);
    if (str == null)
      return; 
    Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
    intent.putExtra("badge_count", paramInt);
    intent.putExtra("badge_count_package_name", paramContext.getPackageName());
    intent.putExtra("badge_count_class_name", str);
    paramContext.sendBroadcast(intent);
  }
  
  private static void setCustomEventListener(OnCustomEventListener paramOnCustomEventListener) {
    GlobalMembers.myListener = paramOnCustomEventListener;
  }
  
  private static void setCustomEventListenerActions(OnCustomEventListener paramOnCustomEventListener) {
    GlobalMembers.eventListenerActions = paramOnCustomEventListener;
  }
  
  private static void setCustomEventListenerDownComplete(OnCustomEventListener paramOnCustomEventListener) {
    GlobalMembers.myListenerDownComplete = paramOnCustomEventListener;
  }
  
  private static void setCustomEventListenerOpenMap(OnCustomEventListener paramOnCustomEventListener) {
    GlobalMembers.eventListenerOpenMap = paramOnCustomEventListener;
  }
  
  private void setHandlerManagerBT() {
    BluetoothServiceRT bluetoothServiceRT = mChatService;
    if (bluetoothServiceRT == null) {
      setupChatPlatinum();
    } else {
      bluetoothServiceRT.setHandler(this.mHandler);
    } 
    InitManagerBT();
  }
  
  public static void setLauncherBT(boolean paramBoolean) {}
  
  private void setPlanandPages() {
    new LinkedHashMap<Object, Object>();
    buttonActions = new LinkedList<CustomActionButton>();
    try {
      this.mViewPager = null;
      this.mViewPager = (ViewPager)findViewById(2131296944);
      LinkedHashMap linkedHashMap = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getMainActions();
      for (String str : linkedHashMap.keySet()) {
        if (!str.equals("-6") || !GlobalMembers.notificationSpinner) {
          boolean bool;
          CustomActionButton customActionButton1;
          CustomActionButton customActionButton2 = new CustomActionButton();
          this(mContext);
          Enums$Services enums$Services = Enums$Services.GetValue(Integer.parseInt(str));
          int i = ((Integer)((LinkedHashMap)linkedHashMap.get(str)).get("water")).intValue();
          if (((Integer)((LinkedHashMap)linkedHashMap.get(str)).get("hasmap")).intValue() == 1) {
            bool = true;
          } else {
            bool = false;
          } 
          if (str.equals(Enums$Services.DoorsLock.GetCodeString()) && onCloseDoors.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom(enums$Services, mContext, customActionButton2, 1, 0, actCloseDoors);
          } else if (str.equals(Enums$Services.DoorsUnlock.GetCodeString()) && onOpenDoors.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actOpenDoors);
          } else if (str.equals(Enums$Services.valet.GetCodeString()) && onAlertValet.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actAlertValet);
          } else if ((str.equals(Enums$Services.Parking.GetCodeString()) || str.equals(Enums$Services.ParkingUUx.GetCodeString())) && onAlertParking.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actAlertParking);
          } else if (str.equals(Enums$Services.Ligths.GetCodeString()) && onLigths.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actLigths);
          } else if (str.equals(Enums$Services.HornLigths.GetCodeString()) && onHornLights.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actHornLights);
          } else if (str.equals(Enums$Services.Horn.GetCodeString()) && onHorn.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actHorn);
          } else if ((str.equals(Enums$Services.Speed.GetCodeString()) || str.equals(Enums$Services.SpeedAlways.GetCodeString()) || str.equals(Enums$Services.SpeedUUx.GetCodeString())) && onAlertSpeed.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actAlertSpeed);
          } else if (str.equals(Enums$Services.FollowMe.GetCodeString()) && onFollowMe.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actFollowMe);
          } else if (str.equals(Enums$Services.FindMe.GetCodeString()) && onFindMe.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actFindMe);
          } else if (str.equals(Enums$Services.Disarm.GetCodeString()) && onDisarmPINCODE.booleanValue()) {
            customActionButton1 = CustomActionButtonFactory.getActionButtonCustom((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0, actDisarmPINCODE);
          } else {
            customActionButton1 = CustomActionButtonFactory.getActionButton((Enums$Services)customActionButton1, mContext, customActionButton2, 1, 0);
          } 
          if (customActionButton1 != null) {
            customActionButton1.set_action_service_status(i);
            customActionButton1.setMapActive(bool);
            buttonActions.add(customActionButton1);
          } 
        } 
      } 
      this.mSectionsPagerAdapter = null;
      SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter();
      this(getSupportFragmentManager(), buttonActions, (Context)this);
      this.mSectionsPagerAdapter = sectionsPagerAdapter;
      this.mViewPager.setAdapter((PagerAdapter)this.mSectionsPagerAdapter);
      if (this.imgPageLeft != null && this.imgPageRight != null) {
        Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.square_graydark, 2131165673);
        this.imgPageLeft.setImageDrawable(drawable);
        drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.square_graylight, 2131165674);
        this.imgPageRight.setImageDrawable(drawable);
      } 
      this.pageGlobal = 0;
      if (this.mSectionsPagerAdapter != null && this.mSectionsPagerAdapter.getCount() > 1) {
        this.llCircles.setVisibility(0);
      } else {
        this.llCircles.setVisibility(4);
      } 
      ViewPager viewPager = this.mViewPager;
      ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
          final MainActivity this$0;
          
          public void onPageScrollStateChanged(int param1Int) {}
          
          public void onPageScrolled(int param1Int1, float param1Float, int param1Int2) {}
          
          public void onPageSelected(int param1Int) {
            Drawable drawable;
            MainActivity mainActivity = MainActivity.this;
            mainActivity.pageGlobal = param1Int;
            if (param1Int == 0) {
              drawable = Utilities.getDrawableFromConfigList((Context)mainActivity, DrawableResourcesVO.square_graydark, 2131165673);
              MainActivity.this.imgPageLeft.setImageDrawable(drawable);
              drawable = Utilities.getDrawableFromConfigList((Context)MainActivity.this, DrawableResourcesVO.square_graylight, 2131165674);
              MainActivity.this.imgPageRight.setImageDrawable(drawable);
            } else if (param1Int > 0 && param1Int < ((MainActivity)drawable).NUM_PAGES - 1) {
              drawable = Utilities.getDrawableFromConfigList((Context)MainActivity.this, DrawableResourcesVO.square_graylight, 2131165674);
              MainActivity.this.imgPageLeft.setImageDrawable(drawable);
              drawable = Utilities.getDrawableFromConfigList((Context)MainActivity.this, DrawableResourcesVO.square_graylight, 2131165674);
              MainActivity.this.imgPageRight.setImageDrawable(drawable);
            } else {
              drawable = Utilities.getDrawableFromConfigList((Context)MainActivity.this, DrawableResourcesVO.square_graylight, 2131165674);
              MainActivity.this.imgPageLeft.setImageDrawable(drawable);
              drawable = Utilities.getDrawableFromConfigList((Context)MainActivity.this, DrawableResourcesVO.square_graydark, 2131165673);
              MainActivity.this.imgPageRight.setImageDrawable(drawable);
            } 
          }
        };
      super(this);
      viewPager.addOnPageChangeListener(onPageChangeListener);
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "setPlanandPages:", exception.getMessage());
    } 
  }
  
  private void setTuto() {
    int i = this.pageGlobal;
    if (i == 0) {
      this.classElements = setViewsFlag();
      i = 3;
    } else if (i == 1) {
      this.classElements = setViewMenuScreen2();
      i = 1;
    } else {
      i = 0;
    } 
    this.position = 0;
    stateTutorial = true;
    this.pressTuto = false;
    this.show = new ShowViewElement(mainContext, (Activity)this, this.classElements);
    this.show.setPages(i);
  }
  
  private void setVehicleSelected(int paramInt) {
    UserDevicesVO userDevicesVO = this.rtApp.getmDeviceUserList().get(paramInt);
    if (onFindMe.booleanValue() || onFollowMe.booleanValue() || onLigths.booleanValue() || onHornLights.booleanValue() || onCloseDoors.booleanValue() || onOpenDoors.booleanValue() || onDisarmPINCODE.booleanValue() || onAlertParking.booleanValue() || onAlertSpeed.booleanValue() || onAlertValet.booleanValue() || onNotification.booleanValue() || onHorn.booleanValue() || onPID.booleanValue() || onDTC.booleanValue() || GlobalMembers.onfollowmeActivated) {
      countNotifications();
      countNotificationsDTC();
      countNotificationsAgendamiento();
      AlertOn();
      getNotifys();
      if (GlobalMembers.onfollowmeActivated && userDevicesVO.equals(oldDevice))
        setPlanandPages(); 
      return;
    } 
    try {
      oldDevice = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity");
      lastDeviceInMain = userDevicesVO;
      this.dbFun.addVehicleSelected(getApplicationContext(), this.userPreference.getUser(), userDevicesVO);
      this.spinnerCount = this.spinner_menu.getCount();
      DBFunctions dBFunctions = new DBFunctions();
      this((Context)this);
      boolean bool = dBFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), "", true);
      if (!userDevicesVO.equals(oldDevice)) {
        GlobalMembers.tempMainActionsWakeUpCar = null;
        GlobalMembers.tempBottomActions = null;
        RemoteDiagnosticActivity.isdialog_action_process_frame_visible = false;
        showRenewalDialog = true;
        String str = dBFunctions.getVehicleByDeviceID(this.userPreference.getUser(), userDevicesVO.getDeviceId()).isValidCard();
        if (!bool && str != null && str.equals("1"))
          showCreditCardChangedDialog(dBFunctions.getVehicleByDeviceID(this.userPreference.getUser(), userDevicesVO.getDeviceId()).getLastCardNumber()); 
      } 
      Utilities.updateVehicleSelected(getApplicationContext(), this.userPreference.getUser(), userDevicesVO);
      this.spinner_menu.setSelection(Utilities.getLastKnownVehicleSelected(getApplicationContext(), this.userPreference.getUser(), this.rtApp));
      if (!bool && showRenewalDialog && GlobalMembers.renewalfeatures) {
        Intent intent;
        this.vehicleCatalogVO = dBFunctions.getVehicleByDeviceID(this.userPreference.getUser(), userDevicesVO.getDeviceId());
        boolean bool1 = this.vehicleCatalogVO.getStatus_renewal_account().equals(Enums$statusRenewalAccount.Expired.toString());
        if (bool1) {
          if (!pendingDialogs.containsKey("displayRenewalExpirationDialog") && lastDeviceInMain != null && lastDeviceInMain.getSeguroApp() != null && lastDeviceInMain.getSeguroApp().equalsIgnoreCase("0")) {
            pendingDialogs.put("displayRenewalExpirationDialog", Utilities.expirationDialog2((Context)this, userDevicesVO.getName(), userDevicesVO.getDeviceId(), this.spinnerCount));
            Utilities.waiting(1);
            intent = new Intent();
            this();
            intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
            mContext.sendBroadcast(intent);
          } 
          if (this.spinnerCount > 1 || !GlobalMembers.notificationSpinner)
            showRenewalDialog = false; 
        } else if (!this.vehicleCatalogVO.getStatus_renewal_account().equals(Enums$statusRenewalAccount.Normal.toString()) && !intent.getRenewalDialogStatus(userDevicesVO.getDeviceId()).equals(Enums$statusRenewalDialog.NeverShowAgain.toString())) {
          if (!pendingDialogs.containsKey("displayRenewalExpirationDialog") && lastDeviceInMain != null && lastDeviceInMain.getSeguroApp() != null && lastDeviceInMain.getSeguroApp().equalsIgnoreCase("0")) {
            pendingDialogs.put("displayRenewalExpirationDialog", Utilities.expirationDialog((Context)this, userDevicesVO.getName(), userDevicesVO.getDeviceId()));
            Utilities.waiting(1);
            intent = new Intent();
            this();
            intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
            mContext.sendBroadcast(intent);
          } 
          showRenewalDialog = false;
        } 
      } 
      setPlanandPages();
      reloadFragmentView();
      if (this.rtApp.isWaterMarkActive())
        generateBottomPanel(); 
      generateDrawerMenu();
      Utilities.showTheftAutoBanner((TextView)findViewById(2131297163), (Context)this, bool);
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: setVehicleSelected", exception.getMessage());
    } 
    countNotifications();
    countNotificationsDTC();
    countNotificationsAgendamiento();
    AlertOn();
    getNotifys();
  }
  
  private void settingsFunction() {
    toggleDrawerMenu();
    startActivity(new Intent((Context)this, SettingsNewActivity.class));
  }
  
  private void setupChatPlatinum() {
    mChatService = new BluetoothServiceRT((Context)this, this.mHandler);
    mOutStringBuffer = new StringBuffer("");
  }
  
  private void showAlertMessage(String paramString1, String paramString2) {
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
    builder.setTitle(paramString1);
    builder.setMessage(paramString2);
    builder.setPositiveButton(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_continuar, 2131689956), new DialogInterface.OnClickListener(this) {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            (new ManagerNotificationPlatinum(GlobalMembers.contexGlobal)).ACK_MessageGeneric(Enums$statusGeneric.Success);
          }
        });
    builder.setNegativeButton(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212), new DialogInterface.OnClickListener(this) {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            param1DialogInterface.cancel();
          }
        });
    builder.show();
  }
  
  public static void showBlurView() {
    if (Utilities.isActivityRunning(mainContext, RemoteDiagnosticActivity.class)) {
      _act.runOnUiThread(new Runnable() {
            public void run() {
              ((RemoteDiagnosticActivity)RemoteDiagnosticActivity._activity).ShowWakeupCar();
            }
          });
    } else {
      Intent intent = new Intent(mainContext, WakeUpCar.class);
      mainContext.startActivity(intent);
    } 
    GlobalMembers.processMessageId = true;
  }
  
  @SuppressLint({"NewApi"})
  private void showCreditCardChangedDialog(String paramString) {
    try {
      String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
      String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
      String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_lbl_changePayment_alert, 2131690287);
      Dialog dialog = Utilities.simpleDialog((Context)this, null, str3, String.format(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.cartao_expirada_alert, 2131689696), new Object[] { paramString }), true, str2, true, str1, false);
      this.btnNOK = (Button)dialog.findViewById(2131296344);
      this.btnOK = (Button)dialog.findViewById(2131296343);
      Button button1 = this.btnOK;
      View.OnClickListener onClickListener2 = new View.OnClickListener() {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        };
      super(this, dialog);
      button1.setOnClickListener(onClickListener2);
      Button button2 = this.btnNOK;
      View.OnClickListener onClickListener1 = new View.OnClickListener() {
          final MainActivity this$0;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            Utilities.getTokenAndURLToAttemptToPay(MainActivity.this.dbFun, (Context)MainActivity.this);
          }
        };
      super(this, dialog);
      button2.setOnClickListener(onClickListener1);
      dialog.show();
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: createExitAppDialog", exception.getMessage());
    } 
  }
  
  private void showSoftKeyboard(View paramView) {
    if (paramView.requestFocus())
      ((InputMethodManager)getSystemService("input_method")).showSoftInput(paramView, 1); 
  }
  
  private void showdDialogFailConectionOnStart() {
    final Dialog dialog = Utilities.simpleDialog((Context)this, null, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955), Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.wizard_dicas_lbl_fallacomunicacion_7, 2131690516), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946), false, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949), 24.0F, 18.0F);
    this.btnOK = (Button)dialog.findViewById(2131296343);
    this.btnNOK = (Button)dialog.findViewById(2131296344);
    this.btnOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    this.btnNOK.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    dialog.show();
  }
  
  private void starFindMe() {
    if (!onFollowMe.booleanValue() && !onFindMe.booleanValue()) {
      this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.FindMe);
      if (this.tempButton != null)
        findmeFunction(this.view); 
    } else {
      this.isTryingToExecuteSameAction = true;
    } 
  }
  
  private void startPendingFollowMeIfExists() {
    GlobalMembers.followMeProcessDeviceId = Utilities.getLastFollowMeDeviceId(mContext);
    GlobalMembers.ifollowMeType = -1;
    String str = GlobalMembers.followMeProcessDeviceId;
    if (str != null && !str.isEmpty())
      GlobalMembers.ifollowMeType = this.dbFun.mustStartFollowMe(GlobalMembers.followMeProcessDeviceId, mContext); 
    if (GlobalMembers.ifollowMeType == 1) {
      GlobalMembers.onFollowMeRun = Boolean.valueOf(true);
      iniciarFollowMe(16 - this.dbFun.minutesLeftForFollowMe(GlobalMembers.followMeProcessDeviceId, mContext));
    } 
  }
  
  private void startingMap(String paramString1, String paramString2, String paramString3) {
    String str2 = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
    int i = Enums$Services.FindMe.GetCode();
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.navigation_lbl_localizame_gpssincobertura_1, 2131690158);
    GoogleMapVO googleMapVO = new GoogleMapVO();
    googleMapVO.setKEY_SET_ENGINE_SOURCE(Enums$navigationProcess.External);
    googleMapVO.setKEY_SET_NAV_DEVICE(str2);
    googleMapVO.setKEY_SET_NAV_ACTION(String.valueOf(i));
    googleMapVO.setKEY_SET_NAV_PARAMS(str1);
    googleMapVO.setKEY_EXTERNAL_LAT(paramString1);
    googleMapVO.setKEY_EXTERNAL_LNG(paramString2);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(paramString3);
    googleMapVO.setLASTKNOWDATE(stringBuilder.toString());
    googleMapVO.setKEY_GPSSTATUS(str1);
    openTomTomMap(googleMapVO);
  }
  
  private void timerGetCommandStatusControler() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: putstatic com/roadtrack/onstar/BO/GlobalMembers.bFinalizaronIntentos : Z
    //   6: new java/util/Timer
    //   9: astore_1
    //   10: aload_1
    //   11: invokespecial <init> : ()V
    //   14: aload_0
    //   15: aload_1
    //   16: putfield t : Ljava/util/Timer;
    //   19: new com/roadtrack/onstar/MainActivity$31
    //   22: astore_1
    //   23: aload_1
    //   24: aload_0
    //   25: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity;)V
    //   28: aload_0
    //   29: aload_1
    //   30: putfield task : Ljava/util/TimerTask;
    //   33: aload_0
    //   34: getfield t : Ljava/util/Timer;
    //   37: aload_0
    //   38: getfield task : Ljava/util/TimerTask;
    //   41: lconst_0
    //   42: aload_0
    //   43: getfield timeBetweenAttemptsResponseActions : I
    //   46: i2l
    //   47: invokevirtual scheduleAtFixedRate : (Ljava/util/TimerTask;JJ)V
    //   50: aload_0
    //   51: monitorexit
    //   52: return
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    // Exception table:
    //   from	to	target	type
    //   2	50	53	finally
  }
  
  private void toggleDrawerMenu() {
    this.rightRL = (LinearLayout)findViewById(2131297110);
    if (!this.drawerLayout.isDrawerOpen((View)this.rightRL)) {
      this.drawerLayout.openDrawer((View)this.rightRL);
      this.spinner_menu.setClickable(false);
      this.spinner_menu.setEnabled(false);
    } else {
      this.drawerLayout.closeDrawer((View)this.rightRL);
      this.spinner_menu.setClickable(true);
      this.spinner_menu.setEnabled(true);
      returnOriginalDrawerList();
    } 
  }
  
  private void validProcessDownload() {}
  
  private void valueIdAction(String paramString) {
    boolean bool = paramString.equals(Enums$Services.DoorsUnlock.GetCodeString());
    Boolean bool1 = Boolean.valueOf(false);
    if (bool) {
      onOpenDoors = bool1;
      ProgressBar progressBar = this.DoorsUnlockgenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.Ligths.GetCodeString())) {
      onLigths = bool1;
      ProgressBar progressBar = this.LigthsgenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.HornLigths.GetCodeString())) {
      onHornLights = bool1;
      ProgressBar progressBar = this.HornLigthsgenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.Horn.GetCodeString()) || paramString.equals(Enums$Services.HornF1.GetCodeString())) {
      onHorn = bool1;
      ProgressBar progressBar = this.HornLigthsgenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.FindMe.GetCodeString())) {
      onFindMe = bool1;
      ProgressBar progressBar = this.findMeProBar;
      if (progressBar != null) {
        progressBar.setVisibility(8);
        this.ImageResponseFindme.setVisibility(8);
        Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.palomita_azul_fondo, 2131165626);
        this.ImageResponseFindme.setImageDrawable(drawable);
      } 
    } 
    if (paramString.equals(Enums$Services.FollowMe.GetCodeString())) {
      if (this.idFromLastGetCommand != 2)
        GlobalMembers.onFollowMeRun = bool1; 
      onFollowMe = bool1;
      ProgressBar progressBar = this.FollowmegenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.DoorsLock.GetCodeString())) {
      onCloseDoors = bool1;
      ProgressBar progressBar = this.DoorsLockgenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.Disarm.GetCodeString())) {
      onDisarmPINCODE = bool1;
      ProgressBar progressBar = this.DisarmgenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.Parking.GetCodeString()) || paramString.equals(Enums$Services.ParkingUUx.GetCodeString())) {
      onAlertParking = bool1;
      ProgressBar progressBar = this.ParkinggenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.Speed.GetCodeString()) || paramString.equals(Enums$Services.SpeedUUx.GetCodeString())) {
      onAlertSpeed = bool1;
      ProgressBar progressBar = this.SpeedgenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.SpeedAlways.GetCodeString())) {
      onAlertSpeed = bool1;
      ProgressBar progressBar = this.genericProgress;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
    if (paramString.equals(Enums$Services.valet.GetCodeString())) {
      onAlertValet = bool1;
      ProgressBar progressBar = this.valetgenericProgressBar;
      if (progressBar != null)
        progressBar.setVisibility(8); 
    } 
  }
  
  private void webFunction(boolean paramBoolean, String paramString) {
    if (!paramBoolean)
      toggleDrawerMenu(); 
    paramBoolean = (new DBFunctions(mContext)).userDataTableHandler(this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), "", true);
    Utilities.showTheftAutoBanner((TextView)findViewById(2131297163), (Context)this, paramBoolean);
    if (paramBoolean)
      return; 
    try {
      Intent intent = new Intent();
      this("android.intent.action.VIEW", Uri.parse(paramString));
      intent.addFlags(268435456);
      getApplication().startActivity(intent);
    } catch (Exception exception) {
      Toast.makeText((Context)this, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862), 1).show();
    } 
  }
  
  public void AlertOff() {
    if (GlobalMembers.notificationSpinner) {
      this.actionButtonL = notificationButton;
    } else {
      this.actionButtonL = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.ActionNotifications);
    } 
    CustomActionButton customActionButton = this.actionButtonL;
    if (customActionButton != null)
      customActionButton.showActionStatus(0); 
  }
  
  public void BannerAlpha(boolean paramBoolean) {
    if (paramBoolean) {
      this.llBtnShowDialog.setAlpha(0.4F);
      this.rlContainerTriangle.setAlpha(0.4F);
      this.burgerDrawerButton.setAlpha(0.4F);
      notificationButton.setAlpha(0.4F);
    } else {
      this.llBtnShowDialog.setAlpha(1.0F);
      this.rlContainerTriangle.setAlpha(1.0F);
      this.burgerDrawerButton.setAlpha(1.0F);
      notificationButton.setAlpha(1.0F);
    } 
  }
  
  public void EditListener(View paramView) {
    if (!paramView.isFocusableInTouchMode()) {
      paramView.setFocusableInTouchMode(true);
      paramView.setSelected(true);
      showSoftKeyboard(paramView);
    } else {
      showSoftKeyboard(paramView);
    } 
  }
  
  public void PushVersion(String paramString1, String paramString2) {
    if (!pushversionactived) {
      pushversionactived = true;
      NotificationManager notificationManager = (NotificationManager)mContext.getSystemService("notification");
      notificationManager.cancelAll();
      PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, new Intent(), 201326592);
      Utilities.isAndinos().booleanValue();
      NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
      builder.setContentText(paramString2);
      NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
      bigTextStyle.bigText(paramString2);
      builder.setStyle((NotificationCompat.Style)bigTextStyle);
      builder.setContentTitle(paramString1);
      builder.setSmallIcon(2131165536);
      builder.setAutoCancel(true);
      builder.setContentIntent(pendingIntent);
      builder.setWhen(System.currentTimeMillis());
      builder.setDefaults(-1);
      builder.setNumber(1);
      if (26 <= Build.VERSION.SDK_INT) {
        NotificationChannel notificationChannel = new NotificationChannel(GlobalMembers.CHANNEL_ID, GlobalMembers.CHANNEL_NAME, GlobalMembers.CHANNEL_IMPORTANCE);
        builder.setChannelId(GlobalMembers.CHANNEL_ID);
        notificationManager.createNotificationChannel(notificationChannel);
      } 
      Notification notification = builder.build();
      notification.flags = 16;
      notificationManager.notify(20, notification);
    } 
  }
  
  public void acceptButtonListener(String paramString) {
    Boolean bool = Boolean.valueOf(true);
    if (!NetUtilities.validateNetwork((Context)this, true))
      return; 
    this.sendCommand = new SendCommand();
    if (!paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_download_in_progress, 2131690033)) && !paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.UpdateMapNeeded, 2131689565)) && !paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_restart_after_download, 2131690061)))
      if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_acticity_no_wifi, 2131690027))) {
        createLaterAlertDialog();
      } else if (!paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_siguemeterminado_1, 2131689934))) {
        ProgressBar progressBar;
        if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.newLatestTraceFollowMe, 2131690198))) {
          onFollowMe = bool;
          GlobalMembers.onFollowMeRun = bool;
          GlobalMembers.needToOpenMap = true;
          GlobalMembers.pushNumber = 0L;
          this.other = true;
          ProgressBar progressBar1 = this.FollowmegenericProgressBar;
          if (progressBar1 != null)
            progressBar1.setVisibility(0); 
          this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.FollowMe);
          CustomActionButton customActionButton = this.tempButton;
          if (customActionButton != null)
            customActionButton.showActionStatus(1); 
          progressBar = this.FollowmegenericProgressBar;
          if (progressBar != null)
            progressBar.setVisibility(0); 
          this.sendCommand.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[] { Utilities.getFollowMeCode(this.rtApp, "MainActivity").GetCodeString() });
        } else if (!progressBar.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.sucessoEmergency, 2131690400)) && !progressBar.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.failEmergency, 2131689787))) {
          ProgressBar progressBar1;
          if (progressBar.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_usteddeseaabrir_2, 2131689643))) {
            setWatterMarks(true, new Enums$Services[] { Enums$Services.DoorsUnlock });
            this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.DoorsUnlock);
            CustomActionButton customActionButton = this.tempButton;
            if (customActionButton != null)
              customActionButton.showActionStatus(1); 
            progressBar1 = this.DoorsUnlockgenericProgressBar;
            if (progressBar1 != null)
              progressBar1.setVisibility(0); 
            this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
            this.actionCode = Enums$Services.DoorsUnlock.GetCodeString();
            this.csvParams = "";
            this.deviceCSVParams = "";
            (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
            onOpenDoors = bool;
            this.other = false;
          } else {
            ProgressBar progressBar2;
            if (progressBar1.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_usteddeseacerrar_2, 2131689645))) {
              setWatterMarks(true, new Enums$Services[] { Enums$Services.DoorsLock });
              this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.DoorsLock);
              CustomActionButton customActionButton = this.tempButton;
              if (customActionButton != null)
                customActionButton.showActionStatus(1); 
              progressBar2 = this.DoorsLockgenericProgressBar;
              if (progressBar2 != null)
                progressBar2.setVisibility(0); 
              onCloseDoors = bool;
              this.other = false;
              this.DoorsLockgenericActionName = "DoorsLock";
              this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
              this.actionCode = Enums$Services.DoorsLock.GetCodeString();
              this.csvParams = "";
              this.deviceCSVParams = "";
              (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
            } else if (progressBar2.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionalertamovsl_1, 2131689846))) {
              setWatterMarks(true, new Enums$Services[] { Enums$Services.Parking });
              this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.Parking);
              this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
              this.actionCode = Utilities.getParkingCode(this.rtApp, "MainActivity").GetCodeString();
              this.csvParams = "";
              this.deviceCSVParams = "";
              (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
              onAlertParking = bool;
            } else if (progressBar2.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionluces_1, 2131689878))) {
              setWatterMarks(true, new Enums$Services[] { Enums$Services.Ligths });
              this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.Ligths);
              this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
              this.actionCode = Enums$Services.Ligths.GetCodeString();
              this.csvParams = "";
              this.deviceCSVParams = "";
              (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
              onLigths = bool;
            } else {
              CustomActionButton customActionButton;
              if (progressBar2.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_usteddeseaaccionar_2, 2131689644))) {
                setWatterMarks(true, new Enums$Services[] { Enums$Services.HornLigths });
                this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.HornLigths);
                this.HornLigthsgenericActionName = "HornLigths";
                onHornLights = bool;
                this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
                this.actionCode = Enums$Services.HornLigths.GetCodeString();
                this.csvParams = "";
                this.deviceCSVParams = "";
                (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
                progressBar2 = this.HornLigthsgenericProgressBar;
                if (progressBar2 != null)
                  progressBar2.setVisibility(0); 
                this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.HornLigths);
                customActionButton = this.tempButton;
                if (customActionButton != null)
                  customActionButton.showActionStatus(1); 
              } else {
                CustomActionButton customActionButton1;
                if (customActionButton.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_accionarbocina_2, 2131689640))) {
                  this.actionCode = Utilities.getHornCode(this.rtApp, "MainActivity").GetCodeString();
                  if (this.actionCode.equals(Enums$Services.HornF1.GetCodeString())) {
                    setWatterMarks(true, new Enums$Services[] { Enums$Services.HornF1 });
                  } else {
                    setWatterMarks(true, new Enums$Services[] { Enums$Services.Horn });
                  } 
                  this.HornLigthsgenericActionName = "Horn";
                  onHorn = bool;
                  this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
                  this.csvParams = "";
                  this.deviceCSVParams = "";
                  (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
                  ProgressBar progressBar3 = this.HornLigthsgenericProgressBar;
                  if (progressBar3 != null)
                    progressBar3.setVisibility(0); 
                  this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.Horn);
                  customActionButton1 = this.tempButton;
                  if (customActionButton1 != null)
                    customActionButton1.showActionStatus(1); 
                  customActionButton1 = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.HornF1);
                  if (customActionButton1 != null)
                    customActionButton1.showActionStatus(1); 
                } else {
                  ProgressBar progressBar3;
                  if (customActionButton1.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.alertBeforDisPinCode, 2131689660))) {
                    this.tempButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.Disarm);
                    customActionButton1 = this.tempButton;
                    if (customActionButton1 != null)
                      customActionButton1.showActionStatus(1); 
                    progressBar3 = this.DisarmgenericProgressBar;
                    if (progressBar3 != null)
                      progressBar3.setVisibility(0); 
                    this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
                    this.actionCode = Enums$Services.Disarm.GetCodeString();
                    this.csvParams = "";
                    this.deviceCSVParams = "";
                    (new Actions()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
                    onDisarmPINCODE = bool;
                    this.other = false;
                  } else if (progressBar3.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.alertBeforEmergencyCallback, 2131689661))) {
                    CallSOS.SendCall(mContext, GlobalMembers.PhoneSOS.toString());
                  } else if (progressBar3.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.BTNoPairpermision, 2131689480))) {
                    (new Thread(new Runnable(this) {
                          public void run() {
                            try {
                              if (Utilities.SupportBluetooth()) {
                                Intent intent = new Intent();
                                this("android.settings.BLUETOOTH_SETTINGS");
                                intent.addFlags(268435456);
                                MainActivity.mContext.startActivity(intent);
                              } 
                            } catch (Exception exception) {
                              Utilities.escribeArchivo("MainActivity", "Error: ThreadBT", exception.getMessage());
                            } 
                          }
                        })).start();
                  } 
                } 
              } 
            } 
          } 
        } 
      }  
    this.dialogMenu = null;
  }
  
  public void acceptSpeedListener(TextView paramTextView, boolean paramBoolean) {
    String str1;
    String str2 = paramTextView.getText().toString();
    this.actionCode = Utilities.getSpeedCode(this.rtApp, "MainActivity").GetCodeString();
    if (this.actionCode.equals(Enums$Services.SpeedUUx.GetCodeString())) {
      str1 = str2.trim();
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("%S,");
      stringBuilder.append(paramBoolean);
      str1 = String.format(stringBuilder.toString(), new Object[] { str2 }).trim();
    } 
    int i = paramTextView.getText().toString().length();
    char c = '\036';
    if (i > 0) {
      try {
        Actions actions;
        i = Integer.parseInt(paramTextView.getText().toString());
        if (i >= 30 && i <= 150) {
          if (this.actionCode.equals(Enums$Services.Speed.GetCodeString())) {
            setWatterMarks(true, new Enums$Services[] { Enums$Services.Speed });
          } else if (this.actionCode.equals(Enums$Services.SpeedUUx.GetCodeString())) {
            setWatterMarks(true, new Enums$Services[] { Enums$Services.SpeedUUx });
          } else if (this.actionCode.equals(Enums$Services.SpeedAlways.GetCodeString())) {
            setWatterMarks(true, new Enums$Services[] { Enums$Services.SpeedAlways });
          } 
          CustomActionButton customActionButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.Speed);
          if (this.SpeedgenericProgressBar != null)
            this.SpeedgenericProgressBar.setVisibility(0); 
          if (customActionButton != null)
            customActionButton.showActionStatus(1); 
          this.tempButtonUUx = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.SpeedUUx);
          if (this.tempButtonUUx != null)
            this.tempButtonUUx.showActionStatus(1); 
          customActionButton = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.SpeedAlways);
          if (customActionButton != null)
            customActionButton.showActionStatus(1); 
          this.genericSpeedValue = str1;
          onAlertSpeed = Boolean.valueOf(true);
          this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId();
          this.csvParams = this.genericSpeedValue;
          this.deviceCSVParams = "";
          actions = new Actions();
          this(this);
          actions.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { this.sessionKey, this.login, this.password, this.accountId, this.deviceId, this.actionCode, this.csvParams, this.userId, this.applicationSourceId, this.deviceCSVParams });
          ((InputMethodManager)getApplicationContext().getSystemService("input_method")).toggleSoftInput(1, 0);
          this.isSpeedDialog = false;
          DialogSpeed.dialog.dismiss();
        } else {
          if (i >= 30)
            c = ''; 
          actions.setText(String.valueOf(c));
          String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_rangovelocidad_5, 2131689642);
          Toast.makeText((Context)this.rtApp, str, 1).show();
        } 
      } catch (Exception exception) {
        Utilities.escribeArchivo("MainActivity", "Error: parse int text change", exception.getMessage());
      } 
    } else {
      exception.setText(String.valueOf(30));
      String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_rangovelocidad_5, 2131689642);
      Toast.makeText((Context)this.rtApp, str, 1).show();
    } 
  }
  
  public void animationButtonTuto() {
    Animation animation = AnimationUtils.loadAnimation(mContext, 2130771983);
    this.idTutorial.setAnimation(animation);
  }
  
  public void animationDisableButtonTuto() {
    Animation animation = AnimationUtils.loadAnimation(mContext, 2130771984);
    this.idTutorial.setAnimation(animation);
  }
  
  protected void callOnCreate() {
    this.dbFun = new DBFunctions(getApplicationContext());
    GlobalMembers.ctxBase = this.rtApp.getBaseContext();
    this.userPreference = this.dbFun.getUserPreference(GlobalMembers.userLogged);
    SharedPreferences.Editor editor = getSharedPreferences("lloged", 0).edit();
    editor.putString("account", Utilities.Crypt(this.userPreference.getUser()));
    editor.commit();
    new LinkedHashMap<Object, Object>();
    allBottomPanelButtons = new LinkedHashMap<String, LinkedList<CustomActionButton>>();
    GlobalMembers.mmsg85 = 2;
    setCustomEventListener(new OnCustomEventListener() {
          final MainActivity this$0;
          
          public void onEvent() {
            MainActivity.this.pushVersionHandler();
          }
        });
    setCustomEventListenerOpenMap(new OnCustomEventListener() {
          final MainActivity this$0;
          
          public void onEvent() {
            MainActivity.onFollowMe = Boolean.valueOf(false);
            GlobalMembers.onFollowMeRun = Boolean.valueOf(true);
            if (MainActivity.this.textViewFollowMe != null && MainActivity.this.textViewFollowMe.getVisibility() == 0)
              MainActivity.this.textViewFollowMe.setVisibility(4); 
            ProgressBar progressBar = MainActivity.this.FollowmegenericProgressBar;
            if (progressBar != null)
              progressBar.setVisibility(8); 
            GoogleMapVO googleMapVO = new GoogleMapVO();
            googleMapVO.setKEY_SET_ENGINE_SOURCE(Enums$navigationProcess.FollowMe);
            if (GlobalMembers.usingPush && !MainActivity.onNavigation.booleanValue())
              MainActivity.this.openTomTomMap(googleMapVO); 
          }
        });
    setCustomEventListenerActions(new OnCustomEventListener() {
          final MainActivity this$0;
          
          public void onEvent() {
            List<GetCommandStatusVO> list = MainActivity.listCmdStat;
            if (list != null) {
              if (list.size() == 0)
                return; 
              MainActivity.access$702(MainActivity.this, ((GetCommandStatusVO)MainActivity.listCmdStat.get(0)).getButtonSelected());
              try {
                MainActivity.access$802(MainActivity.this, (ProgressBar)MainActivity.this.v.findViewById(2131296940));
              } catch (Exception exception) {
                Utilities.escribeArchivo("MainActivity", "Error: onEvent", exception.getMessage());
              } 
              if (TcpClientV1.processSuccessful) {
                if (MainActivity.this.pb != null)
                  MainActivity.this.pb.setVisibility(8); 
                MainActivity mainActivity = MainActivity.this;
                mainActivity.asignImage(mainActivity.v, 1);
                MainActivity.access$1002(MainActivity.this, new DBFunctions(MainActivity.mContext));
                MainActivity.this.dbFun.updateHistorical(Boolean.valueOf(false), ((GetCommandStatusVO)MainActivity.listCmdStat.get(0)).getCommandStatus(), "1", "0", "0", Utilities.getActualTime());
              } else {
                if (MainActivity.this.pb != null)
                  MainActivity.this.pb.setVisibility(8); 
                MainActivity mainActivity = MainActivity.this;
                mainActivity.asignImage(mainActivity.v, 0);
                MainActivity.access$1002(MainActivity.this, new DBFunctions(MainActivity.mContext));
                MainActivity.this.dbFun.updateHistorical(Boolean.valueOf(false), ((GetCommandStatusVO)MainActivity.listCmdStat.get(0)).getCommandStatus(), "2", "0", "0", Utilities.getActualTime());
              } 
              MainActivity.AlertOn();
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(MainActivity.this.rtApp.getDeviceTypeId());
              stringBuilder.append("_ID");
              PreferenceRT.SetValuePreference(stringBuilder.toString(), false, (Context)MainActivity.this);
              MainActivity.access$1202(MainActivity.this, -1L);
              MainActivity.this.valueIdAction(((GetCommandStatusVO)MainActivity.listCmdStat.get(0)).getActionId());
              MainActivity.listCmdStat.remove(0);
              OrientationManager.unlockOrientation(MainActivity.this.act);
              try {
                Utilities.logActions(Utilities.getLastKnownDeviceSelected(MainActivity.this.rtApp, "MainActivity").getName(), ((GetCommandStatusVO)MainActivity.listCmdStat.get(0)).getActionId(), ((GetCommandStatusVO)MainActivity.listCmdStat.get(0)).getCommandStatus());
              } catch (Exception exception) {
                Utilities.escribeArchivo("MainActivity", "Error: setcustomListener", exception.getMessage());
              } 
            } 
          }
        });
    setCustomEventListenerDownComplete(new OnCustomEventListener() {
          final MainActivity this$0;
          
          public void onEvent() {
            String str = (new GetHexDumpMap()).getMapUpdateFile(GlobalMembers.mapFileName);
            if (GlobalMembers.MAP_UPDATE_ON_PARTS.booleanValue()) {
              MainActivity mainActivity1 = MainActivity.this;
              String str1 = Utilities.getStringFromConfigList((Context)mainActivity1, mainActivity1.stringsResourcesVO.main_activity_map_update_step_2_title, 2131690047);
              MainActivity mainActivity2 = MainActivity.this;
              String str2 = Utilities.getStringFromConfigList((Context)mainActivity2, mainActivity2.stringsResourcesVO.main_activity_map_update_step_2_message, 2131690046);
              MainActivity.this.makeWindowToUpdate(str1, str2, str);
            } else {
              MainActivity mainActivity1 = MainActivity.this;
              String str1 = Utilities.getStringFromConfigList((Context)mainActivity1, mainActivity1.stringsResourcesVO.main_activity_map_update_step_2_title, 2131690047);
              MainActivity mainActivity2 = MainActivity.this;
              String str2 = Utilities.getStringFromConfigList((Context)mainActivity2, mainActivity2.stringsResourcesVO.main_activity_map_update_step_2_message, 2131690046);
              MainActivity.this.makeWindowToUpdate(str1, str2, str);
            } 
          }
        });
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(PreferenceRT.GetValuePreference("user", "", (Context)this));
    stringBuilder.append(",");
    stringBuilder.append(PreferenceRT.GetValuePreference("pass", "", (Context)this));
    String[] arrayOfString = stringBuilder.toString().split(",");
    this.rtApp.setUserAccessData(arrayOfString);
    this.sessionKey = this.rtApp.getSessionKey();
    if (arrayOfString.length > 1) {
      this.login = this.rtApp.getUserAccessData()[0];
      this.password = this.rtApp.getUserAccessData()[1];
    } else {
      startActivity(new Intent(mContext, LoginActivity.class));
    } 
    this.accountId = this.rtApp.getAccountID();
    if (Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity") != null)
      this.deviceId = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(); 
    this.userId = this.rtApp.getLocatorUserId();
    this.applicationSourceId = Integer.toString(13);
    this.llCircles = (LinearLayout)findViewById(2131296844);
    this.llBtnShowDialog = (RelativeLayout)findViewById(2131296843);
    this.llBtnShowDialog.setOnClickListener(this.clickShowActivityDialog);
    this.renewaltext = (TextView)findViewById(2131296980);
    this.rlContainerTriangle = (ImageView)findViewById(2131296991);
    this.imgPageLeft = (ImageView)findViewById(2131296942);
    this.headerLogo = (ImageView)findViewById(2131296579);
    this.footer = (RelativeLayout)findViewById(2131296545);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_onstar_logo_header, 2131165499);
    this.headerLogo.setImageDrawable(drawable);
    this.imgPageRight = (ImageView)findViewById(2131296943);
    ProgressBar progressBar = this.findMeProBar;
    if (progressBar != null)
      progressBar.setVisibility(4); 
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
    layoutParams.setMargins(0, 0, 0, 0);
    layoutParams.gravity = 17;
    this.headerLogo.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    Build.MODEL.equals("GT-I8190L");
    if (!GlobalMembers.unRegisterDevice)
      this.async.GCM_Inner(this.mActivity); 
    initProcesBack();
    if (Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity") == null) {
      Object object = PreferenceRT.GetValuePreference(Enums$SettingsPreference.soapString, "", (Context)this);
      try {
        JSONArray jSONArray = new JSONArray();
        this(object.toString());
        object = new BackGroundRestartHandler();
        super();
        List<UserDevicesVO> list = object.getListUserDevice(jSONArray);
        this.rtApp.setmDeviceUserList(list);
        GlobalMembers.mDeviceUserList = list;
      } catch (Exception exception) {
        Utilities.escribeArchivo("MainActivity", "Error: TimerThread", exception.getMessage());
      } 
    } 
    setPlanandPages();
    this.nReceiver = new NotificationReceiver();
    this.dialogReciever = new DialogReciever(this);
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("com.roadtrack.onstar.NOTIFICATION_LISTENER_ALL");
    registerReceiver(this.nReceiver, intentFilter);
    intentFilter = new IntentFilter();
    intentFilter.addAction("com.roadtrack.onstar.DIALOG_RECEIVER");
    registerReceiver(this.dialogReciever, intentFilter);
    intentFilter = new IntentFilter();
    intentFilter.addAction("com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT");
    registerReceiver(this.mReceiver, intentFilter);
    intentFilter = new IntentFilter();
    intentFilter.addAction("com.roadtrack.onstar.BO.NEW_BT_RESPONSE_OBTAINED_EVENT_2");
    registerReceiver(this.mReceiver2, intentFilter);
    GlobalMembers.strAccountID = this.rtApp.getAccountID();
    if (this.straUserData != null) {
      if (!this.rtApp.getUserAccessData().equals(this.straUserData))
        this.straUserData = this.rtApp.getUserAccessData(); 
    } else {
      this.straUserData = this.rtApp.getUserAccessData();
    } 
    GlobalMembers.isSockeActived = true;
    startService(new Intent((Context)this, ServiceAlertsSocket.class));
    startPendingFollowMeIfExists();
    registerReceiver(this.networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    this.mapUpdateHelper = new MapUpdateHelper(getApplicationContext());
    this.mapUpdateHelper.setmRequestCompleteListener(new MapUpdateHelper.OnRequestCompleteListener() {
          final MainActivity this$0;
          
          public void onAfterDialog() {
            MainActivity.this.mapUpdateHelper.retriveServerMapVersion(MainActivity.this.rtApp);
          }
        });
    try {
      this.mapUpdateVO = this.dbFun.getMapUpdateMapaData(Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
    } catch (Exception exception) {
      Utilities.escribeArchivo(exception.getMessage(), "", "");
    } 
    myHeadsetListener = new BluetoohListener();
    try {
      if (!Access.IsVehicleTheft((Context)this, this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), "", true) && GlobalMembers.isMAPUpdateEnabled)
        initMapUpdate((Context)this); 
    } catch (Exception exception) {
      Utilities.escribeArchivo(exception.getMessage(), "", "");
    } 
  }
  
  public void cancelButtonListener(String paramString) {
    if (!paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_acticity_no_wifi, 2131690027)))
      if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_siguemeterminado_1, 2131689934))) {
        this.dialogMenu.dismiss();
      } else if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.newLatestTraceFollowMe, 2131690198))) {
        this.button = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.FollowMe);
        CustomActionButton customActionButton = this.button;
        if (customActionButton != null)
          customActionButton.showActionStatus(0); 
        this.buttonUUX = UserInterfaceUtils.getActionButton(buttonActions, Enums$Services.FollowMeUUx);
        customActionButton = this.buttonUUX;
        if (customActionButton != null)
          customActionButton.showActionStatus(0); 
        GoogleMapVO googleMapVO = new GoogleMapVO();
        googleMapVO.setFLAG_ACTIVITY_CLEAR_TOP(67108864);
        googleMapVO.setFLAG_ACTIVITY_NEW_TASK(268435456);
        googleMapVO.setKEY_SET_ENGINE_SOURCE(Enums$navigationProcess.FollowMeFinished);
        openTomTomMap(googleMapVO);
      }  
    this.dialogMenu = null;
  }
  
  public void chargedFragment(Fragment paramFragment) {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
    try {
      String str = paramFragment.getClass().getName();
      GlobalMembers.fragmentName = str;
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(2131296482, paramFragment, str);
      fragmentTransaction.addToBackStack(str);
      fragmentTransaction.commitAllowingStateLoss();
      this.mContent = paramFragment;
      activeFrag = str;
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: chargedFragment", exception.getMessage());
    } finally {}
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
  }
  
  public void closeHmiMenu() {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
    try {
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.remove(this.mContent);
      fragmentTransaction.addToBackStack("MainActivity");
      fragmentTransaction.commitAllowingStateLoss();
      activeFrag = "MainActivity";
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: closeHMIMenu", exception.getMessage());
    } finally {
      Exception exception;
    } 
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/MainActivity}} */
  }
  
  DialogMenu createMenuDialog(boolean paramBoolean1, int paramInt, String paramString1, String paramString2, boolean paramBoolean2) {
    try {
      this.dialogMenu = DialogMenu.newInstance();
      this.dialogMenu.setPreferenceDialogFragment(this, (Context)this, paramBoolean1, paramInt, paramString1, paramString2, paramBoolean2);
      this.dialogMenu.show(getSupportFragmentManager(), null);
      return this.dialogMenu;
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: createMenuDialog", exception.getMessage());
      return null;
    } 
  }
  
  DialogMenu createMenuDialog(boolean paramBoolean1, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean2) {
    try {
      this.dialogMenu = DialogMenu.newInstance();
      this.dialogMenu.setPreferenceDialogFragment(this, (Context)this, paramBoolean1, paramDrawable, paramString1, paramString2, paramBoolean2);
      this.dialogMenu.show(getSupportFragmentManager(), null);
      return this.dialogMenu;
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: createMenuDialog", exception.getMessage());
      return null;
    } 
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    if (paramMotionEvent.getAction() == 0) {
      Intent intent = new Intent();
      intent.setAction("GlobalTouchService");
      intent.putExtra("ACTION_EXTRA", "usuario_activo");
      sendBroadcast(intent);
    } 
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  protected void downloadUpdateFile() {
    final GetVerificationAccountFile getUpdagteFile = new GetVerificationAccountFile(this.act);
    getVerificationAccountFile.setOnDownloadCompleteListener(new GetVerificationAccountFile.OnDownloadCompleteListener() {
          final MainActivity this$0;
          
          final GetVerificationAccountFile val$getUpdagteFile;
          
          public void onDownloadComplete(String param1String) {
            getUpdagteFile.cancel(true);
            GlobalMembers.downloadComplete = true;
            if (MainActivity.this.mapUpdateVO != null) {
              MainActivity.this.mapUpdateVO.setFileMapStatus(3);
              MainActivity.this.dbFun.updateMapUpdateData(MainActivity.this.mapUpdateVO);
            } 
            MainActivity mainActivity = MainActivity.this;
            String str = Utilities.getStringFromConfigList((Context)mainActivity, mainActivity.stringsResourcesVO.main_activity_map_update_donloaded, 2131690038);
            Toast.makeText((Context)MainActivity.this, str, 1).show();
            MainActivity.this.filepathTBZ = param1String;
            Utilities.validateMapUpdate(MainActivity.mContext, MainActivity.this.dbFun);
          }
          
          public void onDownloadError() {
            getUpdagteFile.cancel(true);
            File file = UtilitiesFile.getFileFromStringFile((new GetHexDumpMap()).getMapUpdateFile(GlobalMembers.mapFileName));
            if (file.exists())
              file.delete(); 
            if (MainActivity.this.mapUpdateVO != null) {
              MainActivity.this.mapUpdateVO.setFileMapStatus(1);
              MainActivity.this.dbFun.updateMapUpdateData(MainActivity.this.mapUpdateVO);
            } 
          }
        });
    Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(GlobalMembers.URLFileUpgrade);
    stringBuilder.append(GlobalMembers.mapFileName);
    getVerificationAccountFile.executeOnExecutor(executor, (Object[])new String[] { stringBuilder.toString() });
    MapUpdateVO mapUpdateVO = this.mapUpdateVO;
    if (mapUpdateVO != null) {
      mapUpdateVO.setFileMapStatus(2);
      this.dbFun.updateMapUpdateData(this.mapUpdateVO);
    } 
  }
  
  public void generateBottomPanel() {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity");
    if (userDevicesVO != null)
      if (!allBottomPanelButtons.containsKey(userDevicesVO.getDeviceId())) {
        loadButtons();
        loadFunctions();
      } else {
        loadButtons();
        loadFunctions();
      }  
  }
  
  public void getNotify(View paramView) {
    // Byte code:
    //   0: aload_1
    //   1: putstatic com/roadtrack/onstar/MainActivity.NotificationView : Landroid/view/View;
    //   4: invokestatic AlertOn : ()V
    //   7: getstatic com/roadtrack/onstar/MainActivity.getNotificationsAfterLoginCounter : I
    //   10: iconst_1
    //   11: iadd
    //   12: putstatic com/roadtrack/onstar/MainActivity.getNotificationsAfterLoginCounter : I
    //   15: getstatic com/roadtrack/onstar/MainActivity.executeGetNotificationsAfterLogin : Z
    //   18: ifeq -> 28
    //   21: getstatic com/roadtrack/onstar/MainActivity.getNotificationsAfterLoginCounter : I
    //   24: iconst_1
    //   25: if_icmpgt -> 59
    //   28: getstatic com/roadtrack/onstar/MainActivity.oldDevice : Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   31: astore_1
    //   32: aload_1
    //   33: ifnull -> 248
    //   36: aload_1
    //   37: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   40: aload_0
    //   41: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   44: ldc_w 'MainActivity'
    //   47: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   50: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   53: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   56: ifne -> 248
    //   59: iconst_0
    //   60: putstatic com/roadtrack/onstar/MainActivity.executeGetNotificationsAfterLogin : Z
    //   63: iconst_0
    //   64: putstatic com/roadtrack/onstar/MainActivity.getNotificationsAfterLoginCounter : I
    //   67: aload_0
    //   68: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   71: ldc_w 'MainActivity'
    //   74: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   77: putstatic com/roadtrack/onstar/MainActivity.oldDevice : Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   80: aload_0
    //   81: aload_0
    //   82: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   85: invokevirtual getAccountID : ()Ljava/lang/String;
    //   88: aload_0
    //   89: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   92: ldc_w 'MainActivity'
    //   95: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   98: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   101: ldc_w ''
    //   104: iconst_1
    //   105: invokestatic IsVehicleTheft : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Z
    //   108: ifne -> 248
    //   111: getstatic com/roadtrack/onstar/MainActivity.NotificationView : Landroid/view/View;
    //   114: ldc_w 2131296940
    //   117: invokevirtual findViewById : (I)Landroid/view/View;
    //   120: checkcast android/widget/ProgressBar
    //   123: iconst_0
    //   124: invokevirtual setVisibility : (I)V
    //   127: getstatic com/roadtrack/onstar/Enums$Services.ActionNotifications : Lcom/roadtrack/onstar/Enums$Services;
    //   130: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   133: astore_1
    //   134: aload_0
    //   135: aload_0
    //   136: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   139: ldc_w 'MainActivity'
    //   142: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
    //   145: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   148: putfield deviceId : Ljava/lang/String;
    //   151: new com/roadtrack/onstar/MainActivity$Actions
    //   154: dup
    //   155: aload_0
    //   156: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity;)V
    //   159: getstatic android/os/AsyncTask.THREAD_POOL_EXECUTOR : Ljava/util/concurrent/Executor;
    //   162: bipush #11
    //   164: anewarray java/lang/String
    //   167: dup
    //   168: iconst_0
    //   169: ldc_w ''
    //   172: aastore
    //   173: dup
    //   174: iconst_1
    //   175: aload_0
    //   176: getfield login : Ljava/lang/String;
    //   179: aastore
    //   180: dup
    //   181: iconst_2
    //   182: aload_0
    //   183: getfield password : Ljava/lang/String;
    //   186: aastore
    //   187: dup
    //   188: iconst_3
    //   189: aload_0
    //   190: getfield accountId : Ljava/lang/String;
    //   193: aastore
    //   194: dup
    //   195: iconst_4
    //   196: aload_0
    //   197: getfield deviceId : Ljava/lang/String;
    //   200: aastore
    //   201: dup
    //   202: iconst_5
    //   203: aload_1
    //   204: aastore
    //   205: dup
    //   206: bipush #6
    //   208: aload_0
    //   209: getfield csvParams : Ljava/lang/String;
    //   212: aastore
    //   213: dup
    //   214: bipush #7
    //   216: aload_0
    //   217: getfield userId : Ljava/lang/String;
    //   220: aastore
    //   221: dup
    //   222: bipush #8
    //   224: aload_0
    //   225: getfield applicationSourceId : Ljava/lang/String;
    //   228: aastore
    //   229: dup
    //   230: bipush #9
    //   232: aload_0
    //   233: getfield deviceCSVParams : Ljava/lang/String;
    //   236: aastore
    //   237: dup
    //   238: bipush #10
    //   240: ldc_w '0'
    //   243: aastore
    //   244: invokevirtual executeOnExecutor : (Ljava/util/concurrent/Executor;[Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   247: pop
    //   248: return
  }
  
  public int getPosition() {
    return this.position;
  }
  
  void getUltimeFindme() {
    DBFunctions dBFunctions = new DBFunctions(mContext);
    new ArrayList();
    List<Historical> list = dBFunctions.getLastFindMeFromHistorical(GlobalMembers.userLogged, Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
    if (list != null && list.size() != 0)
      startingMap(((Historical)list.get(0)).getLatitud(), ((Historical)list.get(0)).getLongitud(), ((Historical)list.get(0)).getDateTime()); 
  }
  
  public void initMapUpdate(Context paramContext) {
    this.mapUpdateVO = this.mapUpdateHelper.verifyPendingDownloads(this.dbFun, Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId());
    MapUpdateVO mapUpdateVO = this.mapUpdateVO;
    if (mapUpdateVO != null) {
      GlobalMembers.mapFileName = mapUpdateVO.getServerFileName();
      if (!pendingDialogs.containsKey("displayDownloadMapDialog")) {
        pendingDialogs.put("displayDownloadMapDialog", this.mapUpdateHelper.displayDownloadMapDialog(paramContext, this.mapUpdateVO));
        Utilities.waiting(1);
        Intent intent = new Intent();
        intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
        mContext.sendBroadcast(intent);
      } 
    } else {
      this.mapUpdateHelper.retriveServerMapVersion(this.rtApp);
    } 
  }
  
  public boolean istheftautostolen() {
    DBFunctions dBFunctions = new DBFunctions(getApplicationContext());
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity");
    return dBFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), userDevicesVO.getDeviceId(), "", true);
  }
  
  public void minusButtonListener(TextView paramTextView) {
    String str2 = paramTextView.getText().toString();
    String str1 = str2;
    if (str2.trim().equals(""))
      str1 = String.valueOf(40); 
    int i = Integer.parseInt(str1) - 10;
    if (i >= 30)
      paramTextView.setText(String.valueOf(i)); 
  }
  
  public void onActionSelected(CustomActionButton paramCustomActionButton) {
    if (paramCustomActionButton != null && paramCustomActionButton.get_action_service_status() == 1)
      onCustomActionButtonClicked(paramCustomActionButton); 
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    if (paramInt2 == -1) {
      int i = this.REQUEST_CODE;
      if (paramInt1 == i && i == 3)
        reloadFragmentView(); 
    } 
    if (paramInt2 == -1 && paramIntent.hasExtra("exitApp"))
      finish(); 
  }
  
  public void onAlertClick() {
    try {
      this.dialogMenu = null;
      this.mPagerAdapter.notifyDataSetChanged();
      this.mPager.setAdapter(null);
      this.mPagerAdapter.notifyDataSetChanged();
      this.token = FirebaseInstanceId.getInstance().getToken();
      Runnable runnable = new Runnable() {
          final MainActivity this$0;
          
          public void run() {
            if (!MainActivity.this.token.equals("")) {
              String str1 = MainActivity.this.rtApp.getLocatorUserId();
              String str2 = String.valueOf(13);
              Intent intent = new Intent((Context)MainActivity.this, TransparentActivityWithSpinner.class);
              MainActivity.this.startActivity(intent);
              if (!GlobalMembers.unRegisterDevice) {
                if (MainActivity.this.token.length() < 1)
                  MainActivity.access$102(MainActivity.this, GlobalMembers.token); 
                String str = PreferenceRT.GetValuePreference(GlobalMembers.deviceName, "", onstarApplication.getContext());
                DevicesInterface devicesInterface = new DevicesInterface(this) {
                    public void getResponseService(String param2String) {}
                  };
                MainActivity mainActivity = MainActivity.this;
                (new DeleteDevicesTask(devicesInterface, (Activity)mainActivity, str1, str, str2, mainActivity.token, true)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
                GlobalMembers.unRegisterDevice = true;
                MainActivity.this.async.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { "3434", MainActivity.access$100(this.this$0), str1, str2, str, "exit" });
              } 
            } 
          }
        };
      super(this);
      runOnUiThread(runnable);
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "Error: onAlertClick", exception.getMessage());
    } 
  }
  
  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    openOptionsMenu();
  }
  
  public void onBackMenuListener(String paramString) {
    DialogMenu dialogMenu = this.dialogMenu;
    Boolean bool = Boolean.valueOf(false);
    if (dialogMenu != null) {
      dialogMenu.dismiss();
      this.dialogMenuID = false;
    } 
    DialogAlert dialogAlert = this.dialogAlert;
    if (dialogAlert != null)
      dialogAlert.dismiss(); 
    this.dialogAlert = null;
    this.dialogMenu = null;
    this.isSpeedDialog = false;
    if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_usteddeseaabrir_2, 2131689643))) {
      onOpenDoorsClicked = bool;
    } else if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_usteddeseacerrar_2, 2131689645))) {
      onCloseDoorsClicked = bool;
    } else if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_usteddeseaaccionar_2, 2131689644))) {
      onHornLightsClicked = bool;
    } else if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.actions_popup_lbl_accionarbocina_2, 2131689640))) {
      onHornClicked = bool;
    } else if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.alertBeforDisPinCode, 2131689660))) {
      onDisarmPINCODEClicked = bool;
    } else if (paramString.equals(Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953))) {
      isActionInProcessDialogShown = bool;
    } 
  }
  
  public void onBackPressed() {
    boolean bool = GlobalMembers.isShowingDialog;
    ShowViewElement showViewElement = this.show;
    if (showViewElement != null)
      showViewElement.removeView(this.act, true); 
  }
  
  public void onBackSpeedListener() {
    DialogSpeed dialogSpeed = this.dialogSpeed;
    if (dialogSpeed != null) {
      dialogSpeed.dismiss();
      this.isSpeedDialog = false;
      onAlertSpeedClicked = Boolean.valueOf(false);
    } 
  }
  
  public void onCancelListener() {
    DialogAlert dialogAlert = this.dialogAlert;
    if (dialogAlert != null)
      dialogAlert.dismiss(); 
    this.dialogAlert = null;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    setContentView(2131427444);
    if (paramConfiguration.orientation == 2) {
      ShowViewElement showViewElement = this.show;
      if (showViewElement != null)
        showViewElement.removeView(this.act, true); 
    } 
    finishToCreate();
    onResume();
  }
  
  @SuppressLint({"NewApi", "Recycle"})
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427444);
    if (paramBundle != null) {
      stateTutorial = paramBundle.getBoolean(this.STATE_TUTORIAL);
      this.stateTutorialMenu = paramBundle.getBoolean(this.STATE_TUTORIAL_MENU);
    } 
    finishToCreate();
    if (!this.dbFun.userDataTableHandler(this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), "", true) && this.vehicleCatalogVO.isValidCard() != null && this.vehicleCatalogVO.isValidCard().equals("1"))
      showCreditCardChangedDialog(this.vehicleCatalogVO.getLastCardNumber()); 
    initAnalytics();
  }
  
  public void onCustomActionButtonClicked(CustomActionButton paramCustomActionButton) {
    paramCustomActionButton.getMyService().GetCode();
    DBFunctions dBFunctions = new DBFunctions((Context)this);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity");
    boolean bool = dBFunctions.userDataTableHandler(this.rtApp.getAccountID().toString(), userDevicesVO.getDeviceId(), "", true);
    Utilities.showTheftAutoBanner((TextView)findViewById(2131297163), (Context)this, bool);
    if (paramCustomActionButton.getMyService().equals(Enums$Services.ActionShare)) {
      onClick(paramCustomActionButton.getMyService().GetCode(), (View)paramCustomActionButton);
      sharelocation();
    } else {
      onClick(paramCustomActionButton.getMyService().GetCode(), (View)paramCustomActionButton);
    } 
  }
  
  protected void onDestroy() {
    BroadcastReceiver broadcastReceiver;
    super.onDestroy();
    this.headerLogo.destroyDrawingCache();
    CustomActionButton customActionButton = notificationButton;
    if (customActionButton != null)
      customActionButton.destroyDrawingCache(); 
    stopService(new Intent((Context)this, PermissionsChecker.class));
    GPSTrackerListener gPSTrackerListener = this.gpstrack;
    if (gPSTrackerListener == null) {
      if (GlobalMembers.onFollowMeRun.booleanValue()) {
        FollowMeHandler.shredFollowMePreference(Boolean.valueOf(true), (Context)this);
      } else {
        FollowMeHandler.shredFollowMePreference(Boolean.valueOf(false), (Context)this);
        FollowMeHandler.nullFollowMePoints();
      } 
      this.rtApp.setAppStatus(0);
      actCall = null;
      AsyncTask<Void, Void, Void> asyncTask = this.mRegisterTask;
      if (asyncTask != null)
        asyncTask.cancel(true); 
      asyncTask = this.gpsAsync;
      if (asyncTask != null)
        asyncTask.cancel(true); 
      NotificationReceiver notificationReceiver = this.nReceiver;
      if (notificationReceiver != null)
        unregisterReceiver(notificationReceiver); 
      DialogReciever dialogReciever = this.dialogReciever;
      if (dialogReciever != null)
        unregisterReceiver(dialogReciever); 
      BroadcastReceiverLogOut broadcastReceiverLogOut = this.logOutR;
      if (broadcastReceiverLogOut != null)
        unregisterReceiver((BroadcastReceiver)broadcastReceiverLogOut); 
      NotificationManager notificationManager = this.notificationManagerMapUpdate;
      if (notificationManager != null) {
        notificationManager.cancel(123456789);
        sendMessagePlatinum(new Object[] { Integer.valueOf(60), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) });
      } 
      TcpClientV1.disconnect();
      broadcastReceiver = this.mReceiver;
      if (broadcastReceiver != null)
        unregisterReceiver(broadcastReceiver); 
      broadcastReceiver = this.mReceiver2;
      if (broadcastReceiver != null)
        unregisterReceiver(broadcastReceiver); 
      broadcastReceiver = this.networkChangeReceiver;
      if (broadcastReceiver != null)
        unregisterReceiver(broadcastReceiver); 
      if (activityAlive) {
        stopService(new Intent((Context)this, ServiceAlertsSocket.class));
        ((NotificationManager)mContext.getSystemService("notification")).cancelAll();
        activityAlive = false;
      } 
      return;
    } 
    broadcastReceiver.stopUsingGPS();
    throw null;
  }
  
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
  }
  
  @SuppressLint({"NewApi"})
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4) {
      GlobalMembers.boolactualizar = true;
      if (activeFrag.equals(BTNavigation.class.getName()))
        GlobalMembers.isTbtWorking = false; 
      if (GlobalMembers.bMostrandoMenuHMI) {
        getmNPlatinum();
        if (Integer.parseInt(GlobalMembers.menuPlatinum.getTypeControl().toString()) == 5) {
          MultiModalHMI.getInstance().closeMenuHMI(1, Enums$MessageOrigin.TBD.GetOpCode());
        } else {
          MultiModalHMI.getInstance().closeMenuHMI(1);
        } 
      } else {
        if (this.mViewPager.getCurrentItem() == 0 && !itWasOpen()) {
          moveTaskToBack(true);
          return true;
        } 
        ViewPager viewPager = this.mViewPager;
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        if (itWasOpen())
          toggleDrawerMenu(); 
      } 
    } 
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  protected void onNewIntent(Intent paramIntent) {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    try {
      Bundle bundle = getIntent().getExtras();
      if (getIntent().hasExtra("isRenewalpush")) {
        isRenewalpush = bundle.getBoolean("isRenewalpush");
        pushNotificationDeviceId = bundle.getString("pushNotificationDeviceId");
        if (bundle.getBoolean("isRenewalpush"))
          if (!onFindMe.booleanValue() && !onFollowMe.booleanValue() && !onLigths.booleanValue() && !onHornLights.booleanValue() && !onCloseDoors.booleanValue() && !onOpenDoors.booleanValue() && !onDisarmPINCODE.booleanValue() && !onAlertParking.booleanValue() && !onAlertSpeed.booleanValue() && !onAlertValet.booleanValue() && !onNotification.booleanValue() && !onHorn.booleanValue() && !GlobalMembers.onfollowmeActivated) {
            int i = Utilities.getDevicePositionByDeviceId(pushNotificationDeviceId);
            if (i != -1) {
              UserDevicesVO userDevicesVO2 = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity");
              UserDevicesVO userDevicesVO1 = this.rtApp.getmDeviceUserList().get(i);
              this.dbFun.addVehicleSelected(getApplicationContext(), this.userPreference.getUser(), userDevicesVO1);
              Utilities.updateVehicleSelected(getApplicationContext(), this.userPreference.getUser(), userDevicesVO1);
              if (!userDevicesVO2.getDeviceId().equals(userDevicesVO1.getDeviceId())) {
                if (this.spinnerCount > 1 || !GlobalMembers.notificationSpinner || !this.vehicleCatalogVO.getStatus_renewal_account().equals(Enums$statusRenewalAccount.Expired.toString()))
                  showRenewalDialog = false; 
                setVehicleSelected(i);
              } 
              renewalPlanFunction();
            } else {
              isRenewalpush = false;
            } 
          } else {
            if (!isActionInProcessDialogShown.booleanValue()) {
              isActionInProcessDialogShown = Boolean.valueOf(true);
              noRepeatSameActionDialog();
            } 
            isRenewalpush = false;
          }  
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("MainActivity", "-no renewal push", exception.getMessage());
      isRenewalpush = false;
    } 
    if (GCMIntentService.isMapUpdatePush) {
      GCMIntentService.isMapUpdatePush = false;
      if (this.dbFun.getDownloadMapUpdatePrefrence() == 1) {
        getUpdateFile();
      } else {
        mapUpdateDialogOnPush();
      } 
    } 
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    RelativeLayout relativeLayout = (RelativeLayout)findViewById(2131297251);
    this.open = this.mDrawerLayout.isDrawerOpen((View)this.rightRL);
    int i = paramMenuItem.getItemId();
    if (i != 16908332) {
      if (i != 2131296913) {
        switch (i) {
          default:
            return super.onOptionsItemSelected(paramMenuItem);
          case 2131296911:
            if (!this.open) {
              this.mDrawerLayout.openDrawer((View)this.rightRL);
            } else {
              this.mDrawerLayout.closeDrawer((View)this.rightRL);
            } 
            this.mDrawerLayout.cancelLongPress();
            return true;
          case 2131296910:
            createExitAppDialog(true);
            return true;
          case 2131296909:
            startActivity(new Intent((Context)this, HistoricalTestActivity.class));
            return true;
          case 2131296908:
            if (GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8) && isBtAndPlatinumConnected()) {
              getmNPlatinum().RequestPlatinumVersions();
            } else if (!GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8) || isBtAndPlatinumConnected()) {
              Toast.makeText((Context)this, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_acticity_p8_funtion, 2131690028), 1).show();
            } 
            return true;
          case 2131296907:
            break;
        } 
        Intent intent = new Intent((Context)this, NotificationsActivity.class);
        AlertOff();
        startActivity(intent);
        return true;
      } 
      startActivity(new Intent((Context)this, SettingsNewActivity.class));
      return true;
    } 
    Toast.makeText((Context)this, "HOME", 1).show();
    return true;
  }
  
  protected void onPause() {
    super.onPause();
    this.rtApp.setAppStatus(2);
    unregisterReceiver(this.followmefinishmyReceiver);
    unregisterReceiver(this.broadcastReceiverAlertsNotifications);
  }
  
  public void onPositiveClick(View paramView) {
    if (NetUtilities.validateNetwork((Context)this, false)) {
      NetUtilities.NetServiceResponseEnum netServiceResponseEnum = NetUtilities.isThereNETService(NetUtilities.NetServiceRequestEnum.CHECKNETSERVICE, getApplicationContext());
      if (netServiceResponseEnum != null) {
        if (netServiceResponseEnum.equals(NetUtilities.NetServiceResponseEnum.SERVICEWIFIOK)) {
          validProcessDownload();
        } else {
          if (netServiceResponseEnum.equals(NetUtilities.NetServiceResponseEnum.SERVICEMOBILEOK)) {
            createMobileAlertDialog();
            return;
          } 
          if (netServiceResponseEnum.equals(NetUtilities.NetServiceResponseEnum.THERESNOSERVICE)) {
            String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_no_network_available, 2131690058);
            Toast.makeText((Context)this.rtApp, str, 1).show();
            createLaterAlertDialog();
            return;
          } 
        } 
      } else {
        return;
      } 
    } else {
      String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.login_global_popup_lbl_sindatos_2, 2131690012);
      Toast.makeText((Context)this.rtApp, str, 1).show();
    } 
  }
  
  protected void onPostCreate(Bundle paramBundle) {
    super.onPostCreate(paramBundle);
    this.mDrawerToggle.syncState();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu) {
    return true;
  }
  
  @SuppressLint({"Override"})
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {}
  
  protected void onRestoreInstanceState(Bundle paramBundle) {
    super.onRestoreInstanceState(paramBundle);
    if (paramBundle != null) {
      this.dialogMenuID = paramBundle.getBoolean("exit");
      this.dialogAlert = (DialogAlert)paramBundle.get("cancelDialog");
      if (this.dialogMenuID) {
        this.cancelable = paramBundle.getBoolean("cancelable");
        this.drawableId = paramBundle.getInt("drawableID");
        this.stringId = paramBundle.getString("stringID");
      } 
      this.isSpeedDialog = paramBundle.getBoolean(this.SPEED_KEY);
      paramBundle.getBoolean(this.DRAWER_STATE_OPEN);
    } 
    AlertOn();
  }
  
  public void onResume() {
    super.onResume();
    boolean bool = GlobalMembers.openMap;
    Integer integer = Integer.valueOf(0);
    if (bool) {
      BundleActivityToMap.getInstanceOfBundleActivityToMap();
      GoogleMapVO googleMapVO = BundleActivityToMap.getGoogleMapVO();
      if (googleMapVO != null)
        openTomTomMap(googleMapVO); 
      GlobalMembers.openMap = false;
      BundleActivityToMap.getInstanceOfBundleActivityToMap();
      BundleActivityToMap.setGoogleMapVO(null);
    } 
    this.resolution = Utilities.getResolution((Context)this);
    GlobalMembers.isShowingDialog = false;
    Intent intent = new Intent();
    intent.setAction("GlobalTouchService");
    intent.putExtra("ACTION_EXTRA", "usuario_activo");
    sendBroadcast(intent);
    onNavigation = Boolean.valueOf(false);
    BluetoothServiceRT bluetoothServiceRT = mChatService;
    if (bluetoothServiceRT != null && bluetoothServiceRT.getState() != 3 && (GlobalMembers.isHMIEnabled || GlobalMembers.isMAPUpdateEnabled))
      getPlatinumAndConnection(); 
    sendMessagePlatinum(new Object[] { Integer.valueOf(80), integer, integer, integer });
    InitializeEnvironment();
    registerReceiver(this.followmefinishmyReceiver, this.followmefinish);
    registerReceiver(this.broadcastReceiverAlertsNotifications, new IntentFilter("BROADCAST_ALERTS_NOTIFACTIONS"));
    if (GlobalMembers.showdialogfollow) {
      Intent intent1 = new Intent();
      intent1.setAction("SHOWDIALOGFOLLOWMEFINISH");
      intent1.putExtra("parameter", "show");
      mContext.sendBroadcast(intent1);
      GlobalMembers.showdialogfollow = false;
    } 
    callOnResume();
    fillVehicleList(this.spinner_menu, getApplicationContext());
    if (!onFindMe.booleanValue() && !onFollowMe.booleanValue() && !onLigths.booleanValue() && !onHornLights.booleanValue() && !onCloseDoors.booleanValue() && !onOpenDoors.booleanValue() && !onDisarmPINCODE.booleanValue() && !onAlertParking.booleanValue() && !onAlertSpeed.booleanValue() && !onAlertValet.booleanValue() && !onNotification.booleanValue() && !onHorn.booleanValue())
      reloadFragmentView(); 
    bool = executeGetNotificationsAfterLogin;
    AlertOff();
    AlertOn();
    registerReceiver(this.networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    reviceNotification();
  }
  
  protected void onResumeFragments() {
    if (this.dialogMenuID) {
      int i = this.drawableId;
      if (i != 0 && this.genericProgress != null)
        createMenuDialog(this.cancelable, i, this.stringId, this.titleId, false); 
    } 
    if (this.dialogAlert != null && this.isSpeedDialog && this.genericProgress != null)
      createSpeedDialog(); 
    super.onResumeFragments();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    if (this.dialogMenu != null) {
      this.dialogMenuID = true;
      if (this.drawableId != 0) {
        paramBundle.putBoolean("exit", this.dialogMenuID);
        paramBundle.putBoolean("cancelable", this.cancelable);
        paramBundle.putInt("drawableID", this.drawableId);
        paramBundle.putString("stringID", this.stringId);
      } 
    } 
    DialogAlert dialogAlert = this.dialogAlert;
    if (dialogAlert != null)
      paramBundle.putParcelable("cancelDialog", (Parcelable)dialogAlert); 
    paramBundle.putBoolean(this.SPEED_KEY, this.isSpeedDialog);
    paramBundle.putBoolean(this.DRAWER_STATE_OPEN, itWasOpen());
    paramBundle.putBoolean(this.STATE_TUTORIAL, stateTutorial);
    paramBundle.putBoolean(this.STATE_TUTORIAL_MENU, this.stateTutorialMenu);
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart() {
    super.onStart();
    if (GlobalMembers.isHMIEnabled || GlobalMembers.isMAPUpdateEnabled) {
      startService(new Intent((Context)this, PermissionsChecker.class));
      ManagerBluetooth.updateContext((Context)this);
    } 
    this.rtApp.setAppStatus(1);
  }
  
  protected void onStop() {
    super.onStop();
    if (this.dialogMenu != null)
      this.dialogMenuID = false; 
  }
  
  public void optionDrawer(ListButton paramListButton) {
    if (paramListButton.getMyService() != Enums$Services.ActionExit && Access.IsVehicleTheft((Context)this, this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), "", true, true))
      return; 
    if (paramListButton.get_action_service_status() != 0) {
      if (paramListButton != null && paramListButton.getMyService() != null)
        analyticsButtonDrawerSelected(paramListButton.getMyService()); 
      switch (paramListButton.getMyService()) {
        default:
          return;
        case null:
          moipTicketFunction();
        case null:
          (new GetOnstarClubAutenticate(mainContext, new AsyncResponse() {
                final MainActivity this$0;
                
                public void processFinish(String param1String) {
                  if (param1String != null) {
                    try {
                      JSONObject jSONObject = new JSONObject();
                      this(param1String);
                      if (jSONObject.get("onstarclub") != null) {
                        Intent intent = new Intent();
                        this("android.intent.action.VIEW");
                        intent.setData(Uri.parse(jSONObject.get("onstarclub").toString()));
                        MainActivity.this.startActivity(intent);
                      } else {
                        MainActivity.this.showdDialogFailConectionOnStart();
                      } 
                    } catch (Exception exception) {
                      MainActivity.this.showdDialogFailConectionOnStart();
                    } 
                  } else {
                    MainActivity.this.showdDialogFailConectionOnStart();
                  } 
                }
              })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
        case null:
          assistanceFunction();
        case null:
          notificationsFunction();
        case null:
          exitFunction();
        case null:
          webFunction(false, GlobalMembers.customer_web_site);
        case null:
          paymentCardInfoFunction();
        case null:
          if (!isRenewalpush && !GlobalMembers.isActionRenewalFromMain)
            toggleDrawerMenu(); 
          renewalPlanFunction();
        case RouteWaypoints:
          Utilities.getTokenAndURLToAttemptToPay(this.dbFun, (Context)this);
        case null:
          if (!onFindMe.booleanValue() && !onFollowMe.booleanValue() && !onLigths.booleanValue() && !onHornLights.booleanValue() && !onCloseDoors.booleanValue() && !onOpenDoors.booleanValue() && !onDisarmPINCODE.booleanValue() && !onAlertParking.booleanValue() && !onAlertSpeed.booleanValue() && !onAlertValet.booleanValue() && !onNotification.booleanValue() && !onHorn.booleanValue()) {
            myAccountFunction();
          } else if (!isActionInProcessDialogShown.booleanValue()) {
            isActionInProcessDialogShown = Boolean.valueOf(true);
            noRepeatSameActionDialog();
          } 
        case StartRouting:
          if (GlobalMembers.IsNewPayment) {
            openWebViewPaymentsHistory();
          } else if (GlobalMembers.notificationSpinner) {
            if (!GlobalMembers.flagShowWebViews) {
              paymentsHistoryFunction();
            } else {
              paymentHistoricalFunction();
            } 
          } else {
            paymentsHistoryFunction();
          } 
        case TurnByTurnManeuverInformation:
          if (!onFindMe.booleanValue() && !onFollowMe.booleanValue() && !onLigths.booleanValue() && !onHornLights.booleanValue() && !onCloseDoors.booleanValue() && !onOpenDoors.booleanValue() && !onDisarmPINCODE.booleanValue() && !onAlertParking.booleanValue() && !onAlertSpeed.booleanValue() && !onAlertValet.booleanValue() && !onNotification.booleanValue() && !onHorn.booleanValue()) {
            myPlanFunction();
          } else if (!isActionInProcessDialogShown.booleanValue()) {
            isActionInProcessDialogShown = Boolean.valueOf(true);
            noRepeatSameActionDialog();
          } 
        case GenericAckResponse:
          adviceFunction();
        case RoutingStatus:
          notificationsFunction();
        case Command0:
          break;
      } 
      settingsFunction();
    } 
  }
  
  public void plusButtonListener(TextView paramTextView) {
    String str2 = paramTextView.getText().toString();
    String str1 = str2;
    if (str2.trim().equals(""))
      str1 = String.valueOf(20); 
    int i = Integer.parseInt(str1) + 10;
    if (i <= 150)
      paramTextView.setText(String.valueOf(i)); 
  }
  
  public void setAction(final Enums$Services service, int paramInt1, int paramInt2) {
    LinkedList<CustomActionButton> linkedList = bottomPanelButtons;
    if (linkedList != null && paramInt2 <= linkedList.size()) {
      linkedList = bottomPanelButtons;
      linkedList.set(paramInt2, CustomActionButtonFactory.getActionButton(service, mContext, linkedList.get(paramInt2), paramInt1, 1));
      if (service == Enums$Services.DTCAction) {
        String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_accionpidinfovehiculo_1, 2131689883);
        Drawable drawable = Utilities.getDrawableFromConfigList(mContext, DrawableResourcesVO.ic_pid_2, 2131165503);
        ((CustomActionButton)bottomPanelButtons.get(paramInt2)).set_drw_action_image(drawable);
        ((CustomActionButton)bottomPanelButtons.get(paramInt2)).set_str_action_title(str);
      } 
      ((CustomActionButton)bottomPanelButtons.get(paramInt2)).setOnClickListener(new View.OnClickListener() {
            final MainActivity this$0;
            
            final Enums$Services val$service;
            
            public void onClick(View param1View) {
              CustomActionButton customActionButton = (CustomActionButton)param1View;
              if (customActionButton != null && customActionButton.get_action_service_status() == 1) {
                Enums$Services enums$Services = service;
                if (enums$Services != null)
                  MainActivity.this.analyticsButtonBottomSelected(enums$Services); 
                int i = MainActivity.null.$SwitchMap$com$roadtrack$onstar$Enums$Services[service.ordinal()];
                if (i != 8) {
                  if (i != 10) {
                    if (i != 13) {
                      if (i != 14) {
                        switch (i) {
                          case 39:
                            MainActivity.this.emergencyFunction();
                            break;
                          case 38:
                            MainActivity.this.dtcFunction();
                            break;
                          case 37:
                            MainActivity.this.pidFunction(param1View);
                            break;
                          case 36:
                            MainActivity.this.findmeFunction(param1View);
                            break;
                        } 
                      } else {
                        MainActivity.this.clubFunction();
                      } 
                    } else {
                      MainActivity.this.assistanceFunction();
                    } 
                  } else {
                    MainActivity.this.webFunction(true, GlobalMembers.customer_web_site);
                  } 
                } else {
                  boolean bool = MainActivity.this.dbFun.userDataTableHandler(MainActivity.this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(MainActivity.this.rtApp, "MainActivity").getDeviceId(), "", true);
                  Utilities.showTheftAutoBanner((TextView)MainActivity.this.findViewById(2131297163), (Context)MainActivity.this, bool);
                  if (bool)
                    return; 
                  if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue() && !MainActivity.onLigths.booleanValue() && !MainActivity.onHornLights.booleanValue() && !MainActivity.onCloseDoors.booleanValue() && !MainActivity.onOpenDoors.booleanValue() && !MainActivity.onDisarmPINCODE.booleanValue() && !MainActivity.onAlertParking.booleanValue() && !MainActivity.onAlertSpeed.booleanValue() && !MainActivity.onAlertValet.booleanValue() && !MainActivity.onNotification.booleanValue() && !MainActivity.onHorn.booleanValue()) {
                    if (GlobalMembers.notificationSpinner) {
                      MainActivity mainActivity = MainActivity.this;
                      if (mainActivity.totalVehicleExpiredOrAlmost > 1) {
                        mainActivity.renewalVehiclesListFunction();
                      } else if (!GlobalMembers.flagShowWebViews) {
                        GlobalMembers.isActionRenewalFromMain = true;
                        mainActivity.renewalPlanFunction();
                      } else {
                        mainActivity.paymentProcessFunction();
                      } 
                    } else {
                      MainActivity.this.renewalPlanFunction();
                    } 
                  } else if (!MainActivity.isActionInProcessDialogShown.booleanValue()) {
                    MainActivity.isActionInProcessDialogShown = Boolean.valueOf(true);
                    MainActivity.this.noRepeatSameActionDialog();
                  } 
                } 
              } 
            }
          });
    } 
  }
  
  public void setDisableOrientation() {
    OrientationManager.unlockOrientation((Activity)this);
  }
  
  public void setPosition(int paramInt) {
    this.position = paramInt;
  }
  
  public void setPressTuto(boolean paramBoolean) {
    this.pressTuto = paramBoolean;
  }
  
  public void setStateTutorial(boolean paramBoolean) {
    stateTutorial = paramBoolean;
  }
  
  public void setStateTutorialMenu(boolean paramBoolean) {
    this.stateTutorialMenu = paramBoolean;
  }
  
  public ClassElements setViewMenuScreen2() {
    ClassElements classElements = new ClassElements();
    String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lb_desactivarPINCODE, 2131690436);
    byte b = 0;
    try {
      while (b < buttonActions.size()) {
        if (b == 9 && buttonActions.get(b) != null)
          ShowViewElement.setValues(classElements, (View)buttonActions.get(b), 0, str, "derechaArriba", 0, getResources().getInteger(2131361815), true, 0, getResources().getInteger(2131361805), false); 
        b++;
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("EXCEPTION", exception);
    } 
    return classElements;
  }
  
  public ClassElements setViewsFlag() {
    ClassElements classElements = new ClassElements();
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_seleccionvehiculo, 2131690461);
    String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_menuprincipal, 2131690454);
    String str6 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_alertasseguridad, 2131690439);
    String str5 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_llamadaemergencia, 2131690450);
    String str8 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_diagnosticovehiculo, 2131690445);
    String str9 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_cerrarabrir, 2131690442);
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_navegar, 2131690458);
    String str7 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_localicevehiculomapa, 2131690453);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_sigavehiculo, 2131690462);
    try {
      int i = this.position;
      if (i == 1) {
        if (GlobalMembers.deviceType.equalsIgnoreCase("P8") || GlobalMembers.deviceType.equalsIgnoreCase("PNG")) {
          if (this.spinner_menu != null)
            ShowViewElement.setValues(classElements, (View)this.spinner_menu, 0, str2, "izquierdaArriba", getResources().getInteger(2131361820), getResources().getInteger(2131361806), false, getResources().getInteger(2131361805), getResources().getInteger(2131361806), false); 
          if (this.burgerDrawerButton != null)
            ShowViewElement.setValues(classElements, (View)this.burgerDrawerButton, 1, str4, "izquierdaArriba", 0, getResources().getInteger(2131361815), false, getResources().getInteger(2131361814), 0, false); 
          for (i = 0; i < buttonActions.size(); i++) {
            if (i == 4 && buttonActions.get(i) != null)
              ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 2, str6, "solo", 50, getResources().getInteger(2131361819), true, getResources().getInteger(2131361816), getResources().getInteger(2131361806), false); 
          } 
          if (this.resolution == 6) {
            if (this.leftButton != null)
              ShowViewElement.setValues(classElements, (View)this.leftButton, 3, str5, "derechaAbajo", 0, getResources().getInteger(2131361816), true, 0, 0, false); 
          } else if (this.leftButton != null) {
            ShowViewElement.setValues(classElements, (View)this.leftButton, 3, str5, "derechaAbajo", 0, getResources().getInteger(2131361816), true, 0, -getResources().getInteger(2131361806), false);
          } 
          if (this.centerRightButton != null)
            ShowViewElement.setValues(classElements, (View)this.centerRightButton, 4, str8, "rectaAbajo", 0, getResources().getInteger(2131361812), true, 0, -getResources().getInteger(2131361805), false); 
          return classElements;
        } 
        if (this.spinner_menu != null)
          ShowViewElement.setValues(classElements, (View)this.spinner_menu, 0, str2, "izquierdaArriba", getResources().getInteger(2131361820), getResources().getInteger(2131361806), false, getResources().getInteger(2131361805), getResources().getInteger(2131361806), false); 
        if (this.burgerDrawerButton != null)
          ShowViewElement.setValues(classElements, (View)this.burgerDrawerButton, 1, str4, "izquierdaArriba", 0, getResources().getInteger(2131361815), false, getResources().getInteger(2131361814), 0, false); 
        for (i = 0; i < buttonActions.size(); i++) {
          if (i == 4 && buttonActions.get(i) != null)
            ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 2, str6, "solo", 50, getResources().getInteger(2131361819), true, getResources().getInteger(2131361816), getResources().getInteger(2131361806), false); 
        } 
        if (this.resolution == 6) {
          if (this.leftButton != null)
            ShowViewElement.setValues(classElements, (View)this.leftButton, 3, str5, "derechaAbajo", 0, getResources().getInteger(2131361816), true, getResources().getInteger(2131361806), 0, false); 
        } else if (this.leftButton != null) {
          ShowViewElement.setValues(classElements, (View)this.leftButton, 3, str5, "derechaAbajo", 0, getResources().getInteger(2131361816), true, getResources().getInteger(2131361806), -getResources().getInteger(2131361806), false);
        } 
      } else if (this.position == 2) {
        if (GlobalMembers.deviceType.equalsIgnoreCase("P8") || GlobalMembers.deviceType.equalsIgnoreCase("PNG")) {
          for (i = 0; i < buttonActions.size(); i++) {
            if (i != 0) {
              if (i != 1) {
                if (i != 2) {
                  if (i != 6) {
                    if (i != 7) {
                      if (i == 8 && buttonActions.get(i) != null)
                        ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 5, "", "izquierdaArriba", 0, getResources().getInteger(2131361812), false, getResources().getInteger(2131361811), getResources().getInteger(2131361814), false); 
                    } else if (this.resolution == 6) {
                      if (buttonActions.get(i) != null)
                        ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 4, str1, "izquierdaAbajo", 0, getResources().getInteger(2131361806), false, 0, 0, false); 
                    } else if (buttonActions.get(i) != null) {
                      ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 4, str1, "izquierdaAbajo", 0, getResources().getInteger(2131361806), false, 0, -getResources().getInteger(2131361806), false);
                    } 
                  } else if (buttonActions.get(i) != null) {
                    ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 3, str7, "derechaArriba", 0, getResources().getInteger(2131361812), true, getResources().getInteger(2131361815), 0, false);
                  } 
                } else if (buttonActions.get(i) != null) {
                  ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 2, str3, "izquierdaArriba", 0, getResources().getInteger(2131361806), false, getResources().getInteger(2131361821), 0, false);
                } 
              } else if (buttonActions.get(i) != null) {
                ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 1, "", "izquierdaAbajo", 0, getResources().getInteger(2131361806), true, 0, 0, false);
              } 
            } else if (this.resolution == 6) {
              if (buttonActions.get(i) != null)
                ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 0, str9, "derechaAbajo", 0, getResources().getInteger(2131361806), true, 0, 0, false); 
            } else if (buttonActions.get(i) != null) {
              ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 0, str9, "derechaAbajo", 0, getResources().getInteger(2131361806), true, 0, -getResources().getInteger(2131361806), false);
            } 
          } 
          return classElements;
        } 
        for (i = 0; i < buttonActions.size(); i++) {
          if (i != 0) {
            if (i != 1) {
              if (i != 2) {
                if (i != 5) {
                  if (i != 6) {
                    if (i == 7 && buttonActions.get(i) != null)
                      ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 5, str7, "derechaArriba", 0, getResources().getInteger(2131361806), false, -getResources().getInteger(2131361806), getResources().getInteger(2131361805), false); 
                  } else if (buttonActions.get(i) != null) {
                    ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 4, str1, "derechaAbajo", 0, getResources().getInteger(2131361806), false, 0, 0, false);
                  } 
                } else if (buttonActions.get(i) != null) {
                  ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 3, "", "izquierdaArriba", 0, getResources().getInteger(2131361817), true, getResources().getInteger(2131361809), 0, false);
                } 
              } else if (buttonActions.get(i) != null) {
                ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 2, str3, "izquierdaArriba", 0, getResources().getInteger(2131361806), false, getResources().getInteger(2131361821), 0, false);
              } 
            } else if (buttonActions.get(i) != null) {
              ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 1, "", "izquierdaAbajo", 0, getResources().getInteger(2131361806), true, 0, 0, false);
            } 
          } else if (this.resolution == 6) {
            if (buttonActions.get(i) != null)
              ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 0, str9, "derechaAbajo", 0, getResources().getInteger(2131361806), true, 0, 0, false); 
          } else if (buttonActions.get(i) != null) {
            ShowViewElement.setValues(classElements, (View)buttonActions.get(i), 0, str9, "derechaAbajo", 0, getResources().getInteger(2131361806), true, 0, -getResources().getInteger(2131361806), false);
          } 
        } 
      } else if (this.position == 0) {
        str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_vernotificaciones, 2131690467);
        str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_renovarplan, 2131690460);
        str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_llamecac, 2131690451);
        if (notificationButton != null)
          ShowViewElement.setValues(classElements, (View)notificationButton, 0, str3, "derechaArriba", 0, getResources().getInteger(2131361812), false, getResources().getInteger(2131361816), 0, false); 
        if (this.rightButton != null)
          ShowViewElement.setValues(classElements, (View)this.rightButton, 1, str1, "izquierdaAbajo", 1, getResources().getInteger(2131361814), false, getResources().getInteger(2131361821), -getResources().getInteger(2131361805), false); 
        if (this.centerLeftButton != null)
          ShowViewElement.setValues(classElements, (View)this.centerLeftButton, 2, str2, "rectaAbajo", 0, getResources().getInteger(2131361819), true, 0, -getResources().getInteger(2131361805), false); 
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("EXCEPTION", exception);
    } 
    return classElements;
  }
  
  public ClassElements setViewsMenuFlag() {
    ClassElements classElements = new ClassElements();
    String str4 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_verificarconfiguracioncuenta, 2131690465);
    String str3 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_procesamientoapp, 2131690459);
    String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_verplancontratado, 2131690468);
    String str2 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.tutorial_lbl_linkwebchevrolet, 2131690448);
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_infoonstar_1, 2131689924);
    try {
      if (this.listButtonAdapter.getCount() > 1) {
        if (this.listButtonAdapter.getView(0, null, null) != null)
          ShowViewElement.setValues(classElements, (View)((ListButton)this.drawerMenuList.get(0)).getIconView(), 0, str4, "derechaAbajo", 0, getResources().getInteger(2131361805), true, getResources().getInteger(2131361809), 0, false); 
        if (GlobalMembers.deviceType.equalsIgnoreCase("P8") || GlobalMembers.deviceType.equalsIgnoreCase("PNG")) {
          if (this.listButtonAdapter.getView(1, null, null) != null)
            ShowViewElement.setValues(classElements, (View)((ListButton)this.drawerMenuList.get(1)).getIconView(), 1, str2, "izquierdaAbajo", 0, getResources().getInteger(2131361805), true, getResources().getInteger(2131361814), 0, false); 
          if (this.listButtonAdapter.getView(2, null, null) != null)
            ShowViewElement.setValues(classElements, (View)((ListButton)this.drawerMenuList.get(2)).getIconView(), 2, str3, "derechaAbajo", 0, getResources().getInteger(2131361805), true, getResources().getInteger(2131361808), 0, false); 
          if (this.listButtonAdapter.getView(3, null, null) != null)
            ShowViewElement.setValues(classElements, (View)((ListButton)this.drawerMenuList.get(3)).getIconView(), 3, str1, "solo", 0, getResources().getInteger(2131361806), true, getResources().getInteger(2131361814), getResources().getInteger(2131361821), false); 
          return classElements;
        } 
        if (this.listButtonAdapter.getView(1, null, null) != null)
          ShowViewElement.setValues(classElements, (View)((ListButton)this.drawerMenuList.get(1)).getIconView(), 1, str3, "izquierdaAbajo", 0, getResources().getInteger(2131361805), true, getResources().getInteger(2131361814), 0, false); 
        if (this.listButtonAdapter.getView(2, null, null) != null)
          ShowViewElement.setValues(classElements, (View)((ListButton)this.drawerMenuList.get(2)).getIconView(), 2, str1, "solo", 0, getResources().getInteger(2131361806), true, getResources().getInteger(2131361814), getResources().getInteger(2131361821), false); 
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("EXCEPTION", exception);
    } 
    return classElements;
  }
  
  public void setWatterMarks(boolean paramBoolean, Enums$Services... paramVarArgs) {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity");
    new LinkedHashMap<Object, Object>();
    LinkedHashMap linkedHashMap1 = userDevicesVO.getMainActions();
    new LinkedHashMap<Object, Object>();
    LinkedHashMap linkedHashMap2 = userDevicesVO.getButtomActions();
    if (paramBoolean) {
      saveCopy(userDevicesVO);
      WatterMark.getWatterMarksFromAccions(linkedHashMap1, paramVarArgs);
      WatterMark.getWatterMarksFromButtonAccions(linkedHashMap2, paramVarArgs);
    } else {
      LinkedHashMap linkedHashMap = GlobalMembers.tempMainActionsWakeUpCar;
      if (linkedHashMap != null) {
        userDevicesVO.setMainActions(linkedHashMap);
        userDevicesVO.setButtomActions(GlobalMembers.tempBottomActions);
      } 
    } 
    if (!GlobalMembers.isWUCRunningAffterSetPlanes) {
      setPlanandPages();
      generateBottomPanel();
      generateDrawerMenu();
    } else if (Utilities.isActivityRunning(mainContext, MainActivity.class)) {
      (new Thread(new Runnable() {
            final MainActivity this$0;
            
            public void run() {
              try {
                Thread.sleep(1000L);
              } catch (InterruptedException interruptedException) {}
              MainActivity.this.runOnUiThread(new Runnable() {
                    final MainActivity.null this$1;
                    
                    public void run() {
                      MainActivity.this.setPlanandPages();
                      MainActivity.this.generateBottomPanel();
                      MainActivity.this.generateDrawerMenu();
                    }
                  });
            }
          })).start();
    } 
    GlobalMembers.isWUCRunningAffterSetPlanes = false;
  }
  
  public void sharelocation() {
    boolean bool = (new DBFunctions(mContext)).userDataTableHandler(this.rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(this.rtApp, "MainActivity").getDeviceId(), "", true);
    Utilities.showTheftAutoBanner((TextView)findViewById(2131297163), (Context)this, bool);
    if (bool)
      return; 
    if (GlobalMembers.isDownloadingMap) {
      this.cancelable = true;
      this.drawableId = 2131165484;
      Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_launcher2, this.drawableId);
      this.stringId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.main_activity_download_in_progress, 2131690033);
      this.titleId = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
      createMenuDialog(this.cancelable, (Drawable)null, this.stringId, this.titleId, false);
    } 
  }
  
  public void showRenewal() {
    // Byte code:
    //   0: aload_0
    //   1: getfield myConfiguration : Landroid/content/res/Configuration;
    //   4: getfield orientation : I
    //   7: iconst_1
    //   8: if_icmpne -> 153
    //   11: aload_0
    //   12: getfield arraylistVehiclesAlmostExpired : Ljava/util/List;
    //   15: astore_1
    //   16: aload_1
    //   17: ifnull -> 29
    //   20: aload_1
    //   21: invokeinterface size : ()I
    //   26: ifne -> 47
    //   29: aload_0
    //   30: getfield arraylistVehiclesExpired : Ljava/util/List;
    //   33: astore_1
    //   34: aload_1
    //   35: ifnull -> 132
    //   38: aload_1
    //   39: invokeinterface size : ()I
    //   44: ifeq -> 132
    //   47: aload_0
    //   48: getfield llBtnShowDialog : Landroid/widget/RelativeLayout;
    //   51: iconst_0
    //   52: invokevirtual setVisibility : (I)V
    //   55: aload_0
    //   56: getfield rlContainerTriangle : Landroid/widget/ImageView;
    //   59: iconst_0
    //   60: invokevirtual setVisibility : (I)V
    //   63: aload_0
    //   64: ldc_w 2131690342
    //   67: invokevirtual getString : (I)Ljava/lang/String;
    //   70: astore #4
    //   72: aload_0
    //   73: ldc_w 2131690343
    //   76: invokevirtual getString : (I)Ljava/lang/String;
    //   79: astore_1
    //   80: aload_0
    //   81: getfield renewaltext : Landroid/widget/TextView;
    //   84: astore_2
    //   85: aload_2
    //   86: ifnull -> 171
    //   89: new java/lang/StringBuilder
    //   92: dup
    //   93: invokespecial <init> : ()V
    //   96: astore_3
    //   97: aload_3
    //   98: aload #4
    //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: pop
    //   104: aload_3
    //   105: ldc_w ' '
    //   108: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: aload_3
    //   113: aload_1
    //   114: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: pop
    //   118: aload_2
    //   119: aload_3
    //   120: invokevirtual toString : ()Ljava/lang/String;
    //   123: invokestatic fromHtml : (Ljava/lang/String;)Landroid/text/Spanned;
    //   126: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   129: goto -> 171
    //   132: aload_0
    //   133: getfield llBtnShowDialog : Landroid/widget/RelativeLayout;
    //   136: bipush #8
    //   138: invokevirtual setVisibility : (I)V
    //   141: aload_0
    //   142: getfield rlContainerTriangle : Landroid/widget/ImageView;
    //   145: bipush #8
    //   147: invokevirtual setVisibility : (I)V
    //   150: goto -> 171
    //   153: aload_0
    //   154: getfield llBtnShowDialog : Landroid/widget/RelativeLayout;
    //   157: bipush #8
    //   159: invokevirtual setVisibility : (I)V
    //   162: aload_0
    //   163: getfield rlContainerTriangle : Landroid/widget/ImageView;
    //   166: bipush #8
    //   168: invokevirtual setVisibility : (I)V
    //   171: return
  }
  
  public void showTutorialMenu() {
    this.classElements = setViewsMenuFlag();
    this.stateTutorialMenu = true;
    this.pressTuto = false;
    this.position = 0;
    this.show = new ShowViewElement(mainContext, (Activity)this, this.classElements);
    this.show.setPages(1);
  }
  
  public class Actions extends AsyncTask<String, String, String> {
    private String actionId;
    
    private double endTime;
    
    private long historicoId;
    
    private String messageId = "0";
    
    private String needOpenNotificationsActivity = "0";
    
    private double palomaTime;
    
    private boolean processFinished;
    
    private String responseNotifications = "";
    
    private String[] resultSocket;
    
    private String[] resultWCF;
    
    private int sendCommandErrorCode = 0;
    
    private int sendCommandErrorCodeId = 408;
    
    private double speedLimit = 0.0D;
    
    private double startTime;
    
    final MainActivity this$0;
    
    private String[] AdjustResults(String param1String) {
      String[] arrayOfString = new String[14];
      arrayOfString[0] = "0";
      arrayOfString[1] = "0";
      arrayOfString[2] = "0";
      arrayOfString[3] = "";
      arrayOfString[4] = "";
      arrayOfString[5] = "";
      arrayOfString[6] = "0";
      arrayOfString[7] = "0";
      arrayOfString[8] = "0";
      arrayOfString[9] = "0";
      arrayOfString[10] = "";
      arrayOfString[11] = "0";
      arrayOfString[12] = "0";
      arrayOfString[13] = "";
      if (this.processFinished) {
        String[] arrayOfString1 = this.resultWCF;
        if (arrayOfString1 != null) {
          arrayOfString[0] = arrayOfString1[0];
          arrayOfString[1] = arrayOfString1[1];
          arrayOfString[2] = arrayOfString1[2];
          arrayOfString[3] = arrayOfString1[3];
          arrayOfString[4] = arrayOfString1[4];
          arrayOfString[5] = arrayOfString1[5];
          arrayOfString[6] = arrayOfString1[6];
          arrayOfString[7] = arrayOfString1[7];
          arrayOfString[8] = arrayOfString1[8];
          arrayOfString[9] = arrayOfString1[9];
          arrayOfString[10] = arrayOfString1[10];
          arrayOfString[11] = param1String;
          arrayOfString[12] = "0";
          arrayOfString[13] = "";
        } else {
          String[] arrayOfString2 = this.resultSocket;
          if (arrayOfString2 != null) {
            arrayOfString[0] = arrayOfString2[1];
            arrayOfString[1] = "0";
            arrayOfString[2] = arrayOfString2[2];
            arrayOfString[3] = "";
            arrayOfString[4] = "";
            arrayOfString[5] = arrayOfString2[5];
            arrayOfString[6] = arrayOfString2[4];
            arrayOfString[7] = arrayOfString2[6];
            arrayOfString[8] = arrayOfString2[7];
            arrayOfString[9] = "";
            arrayOfString[10] = "";
            arrayOfString[11] = arrayOfString2[8];
            arrayOfString[12] = arrayOfString2[3];
            arrayOfString[13] = arrayOfString2[9];
          } 
        } 
      } 
      return arrayOfString;
    }
    
    private String[] GetCommandStatus(String param1String1, int param1Int1, int param1Int2, int param1Int3, String param1String2, String param1String3, String param1String4, String param1String5, LinkedHashMap<String, Object> param1LinkedHashMap) {
      Date date = new Date();
      param1Int1 = 1;
      do {
        long l = param1Int2;
        try {
          Thread.sleep(l);
        } catch (InterruptedException interruptedException) {
          Utilities.escribeArchivo("MainActivity", "Error: Timer", interruptedException.getMessage());
        } 
        if (getTimeDifference(null, date, 13) < param1Int3 && param1Int1 != 0) {
          param1Int1 = 1;
        } else {
          param1Int1 = 0;
        } 
      } while (param1Int1 != 0 && !this.processFinished);
      return new String[] { 
          "0", "0", "0", "", "", "0", "0", "0", "0", "", 
          "0" };
    }
    
    private void GetNotifications(String param1String1, int param1Int1, int param1Int2, int param1Int3, String param1String2, String param1String3, String param1String4, String param1String5, LinkedHashMap<String, Object> param1LinkedHashMap) {
      Date date = new Date();
      boolean bool = true;
      do {
        String str = WS(param1String2, param1String3, param1String4, param1String5, param1Int1, param1LinkedHashMap);
        boolean bool1 = bool;
        if (str != null) {
          bool1 = bool;
          if (str.length() > 0) {
            bool1 = bool;
            if (str.contains("*")) {
              bool1 = bool;
              if (!this.processFinished) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("15|");
                stringBuilder.append(MainActivity.this.deviceId);
                stringBuilder.append("|");
                stringBuilder.append(str.substring(MainActivity.this.deviceId.length() + 1, str.length()).replace("*", ";").replace("|", ","));
                this.responseNotifications = stringBuilder.toString();
                this.processFinished = true;
                bool1 = false;
              } 
            } 
          } 
        } 
        if (str != null && str.length() > 0 && str.equals("0|")) {
          this.responseNotifications = "";
          this.processFinished = true;
          MainActivity.onNotification = Boolean.valueOf(false);
          bool1 = false;
        } 
        if (!this.processFinished) {
          long l = param1Int2;
          try {
            Thread.sleep(l);
          } catch (InterruptedException interruptedException) {
            Utilities.escribeArchivo("MainActivity", "Error: Timer", interruptedException.getMessage());
          } 
        } 
        if (getTimeDifference(null, date, 13) < param1Int3 && bool1) {
          bool = true;
        } else {
          bool = false;
        } 
      } while (bool && !this.processFinished);
    }
    
    private String[] SendCommand(String param1String1, int param1Int1, int param1Int2, int param1Int3, String param1String2, String param1String3, String param1String4, String param1String5, LinkedHashMap<String, Object> param1LinkedHashMap) {
      Date date = new Date();
      String[] arrayOfString = { "0", "0", "0" };
      String str = "0|0|0";
      boolean bool = true;
      while (true) {
        while (true) {
          this.sendCommandErrorCode = 0;
          break;
        } 
        if (!bool)
          return arrayOfString; 
      } 
    }
    
    private void Socket(String param1String1, int param1Int1, int param1Int2, String param1String2, String param1String3, int param1Int3, String param1String4, String param1String5) {
      int i = param1Int2;
      String str5 = ";";
      String str4 = "";
      String str1 = "Error";
      String str3 = "0";
      GlobalMembers.messsageIdGlobal = param1String4;
      Date date = new Date();
      onstarApplication onstarApplication = MainActivity.this.rtApp;
      String str2 = "MainActivity";
      String str6 = Utilities.getLastKnownDeviceSelected(onstarApplication, "MainActivity").getDeviceId();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Knowdevice");
      stringBuilder1.append(str6);
      Utilities.escribeArchivo("MainActivity", "Knowdevice", stringBuilder1.toString());
      String[] arrayOfString = { "", "0", "0", "0", "0", "0", "0", "0", "0" };
      boolean bool = true;
      InetSocketAddress inetSocketAddress = null;
      param1Int1 = 1;
      StringBuilder stringBuilder2 = null;
      int j = 0;
      while (bool) {
        try {
          Socket socket;
        } catch (Exception exception2) {
        
        } finally {
          param1String2 = null;
          param1String1 = str1;
          param1String1 = str2;
          Exception exception1 = exception;
        } 
      } 
      if (!bool && exception != null && exception.isConnected())
        try {
          exception.close();
        } catch (IOException iOException) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(param1String5);
          stringBuilder.append(": Error: SOCKET.IOException");
          Utilities.escribeArchivo(str2, stringBuilder.toString(), iOException.toString());
          Utilities.waiting(1);
        }  
    }
    
    private void SocketKeepAlive(String param1String1, int param1Int1, int param1Int2, String param1String2, String param1String3, int param1Int3, String param1String4, String param1String5) {
      // Byte code:
      //   0: new java/util/Date
      //   3: dup
      //   4: invokespecial <init> : ()V
      //   7: astore #16
      //   9: aconst_null
      //   10: astore #7
      //   12: aconst_null
      //   13: astore_1
      //   14: iconst_1
      //   15: istore_2
      //   16: iload_2
      //   17: ifeq -> 807
      //   20: aload #7
      //   22: astore #15
      //   24: aload_1
      //   25: astore #14
      //   27: new java/net/Socket
      //   30: astore #8
      //   32: aload #7
      //   34: astore #15
      //   36: aload_1
      //   37: astore #14
      //   39: aload #8
      //   41: invokespecial <init> : ()V
      //   44: aload_1
      //   45: astore #14
      //   47: new java/net/InetSocketAddress
      //   50: astore #4
      //   52: iload_2
      //   53: istore #9
      //   55: aload_1
      //   56: astore #7
      //   58: aload_1
      //   59: astore #14
      //   61: aload #4
      //   63: aload #5
      //   65: iload #6
      //   67: invokespecial <init> : (Ljava/lang/String;I)V
      //   70: iload_2
      //   71: istore #9
      //   73: aload_1
      //   74: astore #7
      //   76: aload_1
      //   77: astore #14
      //   79: aload #8
      //   81: aconst_null
      //   82: invokevirtual bind : (Ljava/net/SocketAddress;)V
      //   85: iload_2
      //   86: istore #9
      //   88: aload_1
      //   89: astore #7
      //   91: aload_1
      //   92: astore #14
      //   94: aload #8
      //   96: getstatic com/roadtrack/onstar/BO/GlobalMembers.SOCKET_READ_TIMEOUT : I
      //   99: invokevirtual setSoTimeout : (I)V
      //   102: iload_2
      //   103: istore #9
      //   105: aload_1
      //   106: astore #7
      //   108: aload_1
      //   109: astore #14
      //   111: aload #8
      //   113: aload #4
      //   115: getstatic com/roadtrack/onstar/BO/GlobalMembers.SOCKET_TIMEOUT : I
      //   118: invokevirtual connect : (Ljava/net/SocketAddress;I)V
      //   121: iload_2
      //   122: istore #10
      //   124: aload_1
      //   125: astore #4
      //   127: iload_2
      //   128: istore #9
      //   130: aload_1
      //   131: astore #7
      //   133: aload_1
      //   134: astore #14
      //   136: aload #8
      //   138: invokevirtual isConnected : ()Z
      //   141: ifeq -> 483
      //   144: iload_2
      //   145: istore #9
      //   147: aload_1
      //   148: astore #7
      //   150: aload_1
      //   151: astore #14
      //   153: aload #8
      //   155: iconst_1
      //   156: invokevirtual setKeepAlive : (Z)V
      //   159: iload_2
      //   160: istore #9
      //   162: aload_1
      //   163: astore #7
      //   165: aload_1
      //   166: astore #14
      //   168: ldc_w 'connected'
      //   171: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
      //   174: astore #15
      //   176: iload_2
      //   177: ifeq -> 463
      //   180: iload_2
      //   181: istore #9
      //   183: aload_1
      //   184: astore #7
      //   186: aload_1
      //   187: astore #14
      //   189: new java/io/BufferedWriter
      //   192: astore #4
      //   194: iload_2
      //   195: istore #9
      //   197: aload_1
      //   198: astore #7
      //   200: aload_1
      //   201: astore #14
      //   203: new java/io/OutputStreamWriter
      //   206: astore #17
      //   208: iload_2
      //   209: istore #9
      //   211: aload_1
      //   212: astore #7
      //   214: aload_1
      //   215: astore #14
      //   217: aload #17
      //   219: aload #8
      //   221: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
      //   224: invokespecial <init> : (Ljava/io/OutputStream;)V
      //   227: iload_2
      //   228: istore #9
      //   230: aload_1
      //   231: astore #7
      //   233: aload_1
      //   234: astore #14
      //   236: aload #4
      //   238: aload #17
      //   240: invokespecial <init> : (Ljava/io/Writer;)V
      //   243: aload #4
      //   245: aload #15
      //   247: invokevirtual write : (Ljava/lang/String;)V
      //   250: new java/lang/StringBuilder
      //   253: astore_1
      //   254: aload_1
      //   255: invokespecial <init> : ()V
      //   258: aload_1
      //   259: ldc_w 'SIZE: '
      //   262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   265: pop
      //   266: aload_1
      //   267: aload #15
      //   269: invokevirtual length : ()I
      //   272: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   275: pop
      //   276: ldc 'MainActivity'
      //   278: ldc_w 'SocketKeepAlive_Actions: DATA_REMOTE_SERVICES WRITE SOCKET'
      //   281: aload_1
      //   282: invokevirtual toString : ()Ljava/lang/String;
      //   285: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   288: aload #4
      //   290: invokevirtual flush : ()V
      //   293: new java/io/ByteArrayOutputStream
      //   296: astore #7
      //   298: aload #7
      //   300: iconst_1
      //   301: invokespecial <init> : (I)V
      //   304: sipush #8192
      //   307: newarray byte
      //   309: astore #14
      //   311: aload #8
      //   313: invokevirtual getInputStream : ()Ljava/io/InputStream;
      //   316: astore_1
      //   317: aload #8
      //   319: invokevirtual getKeepAlive : ()Z
      //   322: pop
      //   323: aload #4
      //   325: invokevirtual close : ()V
      //   328: aload #7
      //   330: aload #14
      //   332: iconst_0
      //   333: aload_1
      //   334: aload #14
      //   336: invokevirtual read : ([B)I
      //   339: invokevirtual write : ([BII)V
      //   342: aload #7
      //   344: ldc_w 'UTF-8'
      //   347: invokevirtual toString : (Ljava/lang/String;)Ljava/lang/String;
      //   350: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
      //   353: pop
      //   354: aload_0
      //   355: aconst_null
      //   356: aload #16
      //   358: bipush #13
      //   360: invokespecial getTimeDifference : (Ljava/util/Date;Ljava/util/Date;I)J
      //   363: lstore #11
      //   365: iload_2
      //   366: istore #9
      //   368: lload #11
      //   370: iload_3
      //   371: i2l
      //   372: lcmp
      //   373: iflt -> 396
      //   376: iload_2
      //   377: istore #10
      //   379: aload_0
      //   380: getfield processFinished : Z
      //   383: istore #13
      //   385: iload_2
      //   386: istore #9
      //   388: iload #13
      //   390: ifne -> 396
      //   393: iconst_0
      //   394: istore #9
      //   396: sipush #10000
      //   399: i2l
      //   400: lstore #11
      //   402: iload #9
      //   404: istore #10
      //   406: lload #11
      //   408: invokestatic sleep : (J)V
      //   411: goto -> 442
      //   414: astore_1
      //   415: goto -> 452
      //   418: astore_1
      //   419: iload #10
      //   421: istore_2
      //   422: goto -> 460
      //   425: astore_1
      //   426: iload #9
      //   428: istore #10
      //   430: ldc 'MainActivity'
      //   432: ldc_w 'Error: timer'
      //   435: aload_1
      //   436: invokevirtual toString : ()Ljava/lang/String;
      //   439: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   442: aload #4
      //   444: astore_1
      //   445: iload #9
      //   447: istore_2
      //   448: goto -> 176
      //   451: astore_1
      //   452: aload #4
      //   454: astore #14
      //   456: goto -> 553
      //   459: astore_1
      //   460: goto -> 564
      //   463: iload_2
      //   464: istore #9
      //   466: aload_1
      //   467: astore #7
      //   469: aload_1
      //   470: astore #14
      //   472: aload #8
      //   474: invokevirtual close : ()V
      //   477: aload_1
      //   478: astore #4
      //   480: iload_2
      //   481: istore #10
      //   483: aload #4
      //   485: ifnull -> 509
      //   488: aload #4
      //   490: invokevirtual close : ()V
      //   493: goto -> 509
      //   496: astore_1
      //   497: ldc 'MainActivity'
      //   499: ldc_w 'Error'
      //   502: aload_1
      //   503: invokevirtual toString : ()Ljava/lang/String;
      //   506: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   509: aload #8
      //   511: invokevirtual close : ()V
      //   514: goto -> 530
      //   517: astore_1
      //   518: ldc 'MainActivity'
      //   520: ldc_w 'Error'
      //   523: aload_1
      //   524: invokevirtual toString : ()Ljava/lang/String;
      //   527: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   530: aload #8
      //   532: astore #7
      //   534: aload #4
      //   536: astore #8
      //   538: goto -> 709
      //   541: astore_1
      //   542: iload #9
      //   544: istore_2
      //   545: aload #7
      //   547: astore #4
      //   549: goto -> 564
      //   552: astore_1
      //   553: goto -> 749
      //   556: astore #7
      //   558: aload_1
      //   559: astore #4
      //   561: aload #7
      //   563: astore_1
      //   564: aload_1
      //   565: astore #7
      //   567: aload #8
      //   569: astore_1
      //   570: goto -> 593
      //   573: astore_1
      //   574: aload #15
      //   576: astore #8
      //   578: goto -> 749
      //   581: astore #8
      //   583: aload_1
      //   584: astore #4
      //   586: aload #7
      //   588: astore_1
      //   589: aload #8
      //   591: astore #7
      //   593: aload_1
      //   594: astore #15
      //   596: aload #4
      //   598: astore #14
      //   600: ldc 'MainActivity'
      //   602: ldc_w 'Error: SocketKeepAlive'
      //   605: aload #7
      //   607: invokevirtual toString : ()Ljava/lang/String;
      //   610: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   613: aload_1
      //   614: astore #15
      //   616: aload #4
      //   618: astore #14
      //   620: bipush #10
      //   622: invokestatic waiting : (I)V
      //   625: aload #4
      //   627: ifnull -> 653
      //   630: aload #4
      //   632: invokevirtual close : ()V
      //   635: goto -> 653
      //   638: astore #7
      //   640: ldc 'MainActivity'
      //   642: ldc_w 'Error'
      //   645: aload #7
      //   647: invokevirtual toString : ()Ljava/lang/String;
      //   650: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   653: iload_2
      //   654: istore #10
      //   656: aload_1
      //   657: astore #7
      //   659: aload #4
      //   661: astore #8
      //   663: aload_1
      //   664: ifnull -> 709
      //   667: aload_1
      //   668: invokevirtual close : ()V
      //   671: iload_2
      //   672: istore #10
      //   674: aload_1
      //   675: astore #7
      //   677: aload #4
      //   679: astore #8
      //   681: goto -> 709
      //   684: astore #7
      //   686: ldc 'MainActivity'
      //   688: ldc_w 'Error'
      //   691: aload #7
      //   693: invokevirtual toString : ()Ljava/lang/String;
      //   696: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   699: aload #4
      //   701: astore #8
      //   703: aload_1
      //   704: astore #7
      //   706: iload_2
      //   707: istore #10
      //   709: aload_0
      //   710: aconst_null
      //   711: aload #16
      //   713: bipush #13
      //   715: invokespecial getTimeDifference : (Ljava/util/Date;Ljava/util/Date;I)J
      //   718: iload_3
      //   719: i2l
      //   720: lcmp
      //   721: ifge -> 741
      //   724: iload #10
      //   726: ifeq -> 741
      //   729: aload_0
      //   730: getfield processFinished : Z
      //   733: ifne -> 741
      //   736: iconst_1
      //   737: istore_2
      //   738: goto -> 743
      //   741: iconst_0
      //   742: istore_2
      //   743: aload #8
      //   745: astore_1
      //   746: goto -> 16
      //   749: aload #14
      //   751: ifnull -> 777
      //   754: aload #14
      //   756: invokevirtual close : ()V
      //   759: goto -> 777
      //   762: astore #4
      //   764: ldc 'MainActivity'
      //   766: ldc_w 'Error'
      //   769: aload #4
      //   771: invokevirtual toString : ()Ljava/lang/String;
      //   774: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   777: aload #8
      //   779: ifnull -> 805
      //   782: aload #8
      //   784: invokevirtual close : ()V
      //   787: goto -> 805
      //   790: astore #4
      //   792: ldc 'MainActivity'
      //   794: ldc_w 'Error'
      //   797: aload #4
      //   799: invokevirtual toString : ()Ljava/lang/String;
      //   802: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   805: aload_1
      //   806: athrow
      //   807: iload_2
      //   808: ifne -> 850
      //   811: aload #7
      //   813: ifnull -> 850
      //   816: aload #7
      //   818: invokevirtual isConnected : ()Z
      //   821: ifeq -> 850
      //   824: aload #7
      //   826: invokevirtual close : ()V
      //   829: goto -> 850
      //   832: astore_1
      //   833: ldc 'MainActivity'
      //   835: ldc_w 'Error: SOCKET.IOException'
      //   838: aload_1
      //   839: invokevirtual toString : ()Ljava/lang/String;
      //   842: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   845: bipush #10
      //   847: invokestatic waiting : (I)V
      //   850: return
      // Exception table:
      //   from	to	target	type
      //   27	32	581	java/lang/Exception
      //   27	32	573	finally
      //   39	44	581	java/lang/Exception
      //   39	44	573	finally
      //   47	52	556	java/lang/Exception
      //   47	52	552	finally
      //   61	70	541	java/lang/Exception
      //   61	70	552	finally
      //   79	85	541	java/lang/Exception
      //   79	85	552	finally
      //   94	102	541	java/lang/Exception
      //   94	102	552	finally
      //   111	121	541	java/lang/Exception
      //   111	121	552	finally
      //   136	144	541	java/lang/Exception
      //   136	144	552	finally
      //   153	159	541	java/lang/Exception
      //   153	159	552	finally
      //   168	176	541	java/lang/Exception
      //   168	176	552	finally
      //   189	194	541	java/lang/Exception
      //   189	194	552	finally
      //   203	208	541	java/lang/Exception
      //   203	208	552	finally
      //   217	227	541	java/lang/Exception
      //   217	227	552	finally
      //   236	243	541	java/lang/Exception
      //   236	243	552	finally
      //   243	365	459	java/lang/Exception
      //   243	365	451	finally
      //   379	385	418	java/lang/Exception
      //   379	385	414	finally
      //   406	411	425	java/lang/InterruptedException
      //   406	411	418	java/lang/Exception
      //   406	411	414	finally
      //   430	442	418	java/lang/Exception
      //   430	442	414	finally
      //   472	477	541	java/lang/Exception
      //   472	477	552	finally
      //   488	493	496	java/io/IOException
      //   509	514	517	java/io/IOException
      //   600	613	573	finally
      //   620	625	573	finally
      //   630	635	638	java/io/IOException
      //   667	671	684	java/io/IOException
      //   754	759	762	java/io/IOException
      //   782	787	790	java/io/IOException
      //   824	829	832	java/io/IOException
    }
    
    private void SocketNotifications(String param1String1, int param1Int1, int param1Int2, String param1String2, String param1String3, int param1Int3, String param1String4, String param1String5) {}
    
    private void StartSocket(final String actionCode, final int socketTimeOut, final int socketMaxTime, final String accountId, final String serverIP, final int serverPort, final String messageId, final String deviceId, final boolean isKeepAlive) {
      (new Thread() {
          final MainActivity.Actions this$1;
          
          final String val$accountId;
          
          final String val$actionCode;
          
          final String val$deviceId;
          
          final boolean val$isKeepAlive;
          
          final String val$messageId;
          
          final String val$serverIP;
          
          final int val$serverPort;
          
          final int val$socketMaxTime;
          
          final int val$socketTimeOut;
          
          public void run() {
            if (!MainActivity.Actions.this.processFinished) {
              if (isKeepAlive) {
                Utilities.escribeArchivo("MainActivity", "StartSocket: DATA_REMOTE_SERVICES_STARTING", "");
                MainActivity.Actions.this.SocketKeepAlive(actionCode, socketTimeOut, socketMaxTime, accountId, serverIP, serverPort, messageId, deviceId);
              } else if (!actionCode.equals(Enums$Services.ActionNotifications.GetCodeString())) {
                MainActivity.Actions.this.Socket(actionCode, socketTimeOut, socketMaxTime, accountId, serverIP, serverPort, messageId, deviceId);
              } else {
                MainActivity.Actions.this.SocketNotifications(actionCode, socketTimeOut, socketMaxTime, accountId, serverIP, serverPort, messageId, deviceId);
              } 
            } else {
              MainActivity.Actions.this.stopThread(this);
            } 
          }
        }).start();
    }
    
    private String WS(String param1String1, String param1String2, String param1String3, String param1String4, int param1Int, LinkedHashMap<String, Object> param1LinkedHashMap) {
      // Byte code:
      //   0: ldc ''
      //   2: astore #8
      //   4: aload #8
      //   6: astore #7
      //   8: new com/roadtrack/onstar/BO/SoapRequestObject
      //   11: astore #9
      //   13: aload #8
      //   15: astore #7
      //   17: aload #9
      //   19: aload #6
      //   21: aload #4
      //   23: aload_3
      //   24: aload_2
      //   25: aload_1
      //   26: iload #5
      //   28: iload #5
      //   30: invokespecial <init> : (Ljava/util/LinkedHashMap;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
      //   33: aload #8
      //   35: astore #7
      //   37: aload #9
      //   39: ldc 2131623963
      //   41: getstatic com/roadtrack/onstar/BO/GlobalMembers.nameKeystoreServiceWS : Ljava/lang/String;
      //   44: invokevirtual setKeyStoreId : (ILjava/lang/String;)V
      //   47: aload #8
      //   49: astore #7
      //   51: new com/roadtrack/onstar/BO/RequestManager
      //   54: astore_1
      //   55: aload #8
      //   57: astore #7
      //   59: aload_1
      //   60: aload_0
      //   61: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   64: aload #9
      //   66: invokespecial <init> : (Landroid/app/Activity;Lcom/roadtrack/onstar/BO/SoapRequestObject;)V
      //   69: aload #8
      //   71: astore #7
      //   73: new com/roadtrack/onstar/MainActivity$Actions$4
      //   76: astore_2
      //   77: aload #8
      //   79: astore #7
      //   81: aload_2
      //   82: aload_0
      //   83: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity$Actions;)V
      //   86: aload #8
      //   88: astore #7
      //   90: aload_1
      //   91: aload_2
      //   92: invokevirtual setOnPostExecuteListener : (Lcom/roadtrack/onstar/BO/RequestManager$OnPostExecuteListener;)V
      //   95: aload #8
      //   97: astore #7
      //   99: aload_1
      //   100: iconst_1
      //   101: invokevirtual sendRequest : (I)V
      //   104: aload #8
      //   106: astore #7
      //   108: aload_0
      //   109: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   112: invokestatic access$9600 : (Lcom/roadtrack/onstar/MainActivity;)Ljava/lang/String;
      //   115: astore_1
      //   116: aload_1
      //   117: astore #7
      //   119: aload_0
      //   120: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   123: invokestatic access$9700 : (Lcom/roadtrack/onstar/MainActivity;)I
      //   126: sipush #200
      //   129: if_icmplt -> 150
      //   132: aload_1
      //   133: astore #7
      //   135: aload_1
      //   136: astore_2
      //   137: aload_0
      //   138: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   141: invokestatic access$9700 : (Lcom/roadtrack/onstar/MainActivity;)I
      //   144: sipush #300
      //   147: if_icmple -> 270
      //   150: aload_1
      //   151: astore #7
      //   153: aload_1
      //   154: astore_2
      //   155: aload_3
      //   156: ldc_w 'SendCommand'
      //   159: invokevirtual contains : (Ljava/lang/CharSequence;)Z
      //   162: ifeq -> 270
      //   165: aload_1
      //   166: astore #7
      //   168: aload_1
      //   169: invokevirtual toLowerCase : ()Ljava/lang/String;
      //   172: ldc_w 'timeout'
      //   175: invokevirtual contains : (Ljava/lang/CharSequence;)Z
      //   178: ifeq -> 197
      //   181: aload_1
      //   182: astore #7
      //   184: aload_0
      //   185: aload_0
      //   186: getfield sendCommandErrorCodeId : I
      //   189: putfield sendCommandErrorCode : I
      //   192: aload_1
      //   193: astore_2
      //   194: goto -> 270
      //   197: aload_1
      //   198: astore #7
      //   200: aload_0
      //   201: iconst_m1
      //   202: putfield sendCommandErrorCode : I
      //   205: aload_1
      //   206: astore_2
      //   207: goto -> 270
      //   210: astore_1
      //   211: aload_3
      //   212: ldc_w 'SendCommand'
      //   215: invokevirtual contains : (Ljava/lang/CharSequence;)Z
      //   218: ifeq -> 226
      //   221: aload_0
      //   222: iconst_m1
      //   223: putfield sendCommandErrorCode : I
      //   226: new java/lang/StringBuilder
      //   229: dup
      //   230: invokespecial <init> : ()V
      //   233: astore_2
      //   234: aload_2
      //   235: aload_0
      //   236: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   239: getfield deviceId : Ljava/lang/String;
      //   242: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   245: pop
      //   246: aload_2
      //   247: ldc_w ': Error: WS'
      //   250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   253: pop
      //   254: ldc 'MainActivity'
      //   256: aload_2
      //   257: invokevirtual toString : ()Ljava/lang/String;
      //   260: aload_1
      //   261: invokevirtual toString : ()Ljava/lang/String;
      //   264: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   267: aload #7
      //   269: astore_2
      //   270: aload_2
      //   271: areturn
      // Exception table:
      //   from	to	target	type
      //   8	13	210	java/lang/Exception
      //   17	33	210	java/lang/Exception
      //   37	47	210	java/lang/Exception
      //   51	55	210	java/lang/Exception
      //   59	69	210	java/lang/Exception
      //   73	77	210	java/lang/Exception
      //   81	86	210	java/lang/Exception
      //   90	95	210	java/lang/Exception
      //   99	104	210	java/lang/Exception
      //   108	116	210	java/lang/Exception
      //   119	132	210	java/lang/Exception
      //   137	150	210	java/lang/Exception
      //   155	165	210	java/lang/Exception
      //   168	181	210	java/lang/Exception
      //   184	192	210	java/lang/Exception
      //   200	205	210	java/lang/Exception
    }
    
    private long getTimeDifference(Date param1Date1, Date param1Date2, int param1Int) {
      Date date = param1Date1;
      if (param1Date1 == null)
        date = new Date(); 
      param1Date1 = new Date(date.getTime() - param1Date2.getTime());
      long l2 = param1Date1.getTime() / 1000L / 60L / 60L;
      long l1 = param1Date1.getTime() / 1000L / 60L;
      long l3 = param1Date1.getTime() / 1000L;
      return (param1Int == 11) ? l2 : ((param1Int == 12) ? l1 : l3);
    }
    
    private void stopThread(Thread param1Thread) {
      /* monitor enter ThisExpression{InnerObjectType{ObjectType{com/roadtrack/onstar/MainActivity}.Lcom/roadtrack/onstar/MainActivity$Actions;}} */
      /* monitor exit ThisExpression{InnerObjectType{ObjectType{com/roadtrack/onstar/MainActivity}.Lcom/roadtrack/onstar/MainActivity$Actions;}} */
    }
    
    protected String doInBackground(String... param1VarArgs) {
      StringBuilder stringBuilder1;
      String str11;
      Integer integer = Integer.valueOf(0);
      String str7 = param1VarArgs[0].toString();
      String str8 = param1VarArgs[1].toString();
      String str9 = param1VarArgs[2].toString();
      String str3 = param1VarArgs[3].toString();
      String str2 = param1VarArgs[4].toString();
      String str4 = param1VarArgs[5].toString();
      String str13 = param1VarArgs[6].toString();
      String str14 = param1VarArgs[7].toString();
      String str10 = param1VarArgs[8].toString();
      String str12 = param1VarArgs[9].toString();
      try {
        this.needOpenNotificationsActivity = param1VarArgs[10];
      } catch (Exception exception) {
        this.needOpenNotificationsActivity = "0";
      } 
      int i = Integer.valueOf(GlobalMembers.strPuertoA).intValue();
      String str6 = GlobalMembers.strIPSocketGetCommand;
      String str1 = GlobalMembers.URL_WCF;
      String str5 = GlobalMembers.NAMESPACE_WCF;
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(str5);
      stringBuilder2.append("IService1/");
      stringBuilder2.append("SendCommand");
      String str16 = stringBuilder2.toString();
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(str5);
      stringBuilder2.append("IService1/");
      stringBuilder2.append("GetCommandStatus");
      String str15 = stringBuilder2.toString();
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      this.actionId = str4;
      if (this.actionId.equals(Enums$Services.FindMe.GetCodeString())) {
        MainActivity.actFindMe = 1;
      } else if (this.actionId.equals(Enums$Services.FollowMe.GetCodeString()) || this.actionId.equals(Enums$Services.FollowMeUUx.GetCodeString())) {
        MainActivity.actFollowMe = 1;
      } else if (this.actionId.equals(Enums$Services.Ligths.GetCodeString())) {
        MainActivity.actLigths = 1;
      } else if (this.actionId.equals(Enums$Services.HornLigths.GetCodeString())) {
        MainActivity.actHornLights = 1;
      } else if (this.actionId.equals(Enums$Services.Horn.GetCodeString()) || this.actionId.equals(Enums$Services.HornF1.GetCodeString())) {
        MainActivity.actHorn = 1;
      } else if (this.actionId.equals(Enums$Services.DoorsLock.GetCodeString())) {
        MainActivity.actCloseDoors = 1;
      } else if (this.actionId.equals(Enums$Services.DoorsUnlock.GetCodeString())) {
        MainActivity.actOpenDoors = 1;
      } else if (this.actionId.equals(Enums$Services.Disarm.GetCodeString())) {
        MainActivity.actDisarmPINCODE = 1;
      } else if (this.actionId.equals(Enums$Services.Parking.GetCodeString()) || this.actionId.equals(Enums$Services.ParkingUUx.GetCodeString())) {
        MainActivity.actAlertParking = 1;
      } else if (this.actionId.equals(Enums$Services.Speed.GetCodeString()) || this.actionId.equals(Enums$Services.SpeedUUx.GetCodeString())) {
        MainActivity.actAlertSpeed = 1;
      } else if (this.actionId.equals(Enums$Services.SpeedAlways.GetCodeString())) {
        MainActivity.actAlertSpeed = 1;
      } else if (this.actionId.equals(Enums$Services.valet.GetCodeString())) {
        MainActivity.actAlertValet = 1;
      } 
      MainActivity.access$1002(MainActivity.this, new DBFunctions(MainActivity.mContext));
      String str20 = Enums$Category.Other.toString();
      String str18 = Enums$Services.GetName(Integer.parseInt(str4));
      String str21 = Utilities.getDateTime(str2, GlobalMembers.contexGlobal);
      String str23 = Enums$TypeItem.Historical.toString();
      String str17 = Utilities.getLastKnownDeviceSelected(MainActivity.this.rtApp, "MainActivity").getDeviceId();
      String str22 = GlobalMembers.userLogged;
      String str19 = Utilities.getDateTime(str2, GlobalMembers.contexGlobal);
      Utilities.GCMHeartBeat(MainActivity.this.getApplicationContext());
      if (!str4.equals(Enums$Services.ActionNotifications.GetCodeString())) {
        MainActivity.this.runOnUiThread(new Runnable() {
              final MainActivity.Actions this$1;
              
              public void run() {
                MainActivity.this.BannerAlpha(true);
              }
            });
        if ((str4.equals(Enums$Services.Speed.GetCodeString()) || str4.equals(Enums$Services.SpeedUUx.GetCodeString()) || str4.equals(Enums$Services.SpeedAlways.GetCodeString())) && str13 != null && str13.length() > 0)
          this.speedLimit = Double.valueOf(str13.split(",")[0]).doubleValue(); 
        Historical historical = new Historical(str20, str18, str21, str23, "", "", "", 0, 0, str4, str17, str22, str19, "0", "0", "0", "0", 0, this.speedLimit, 0.0D, "0", Integer.valueOf(this.messageId).intValue(), str19);
        this.historicoId = MainActivity.this.dbFun.addHistorical(historical);
        linkedHashMap.clear();
        linkedHashMap.put("sessionKey", str7);
        linkedHashMap.put("login", str8);
        linkedHashMap.put("password", str9);
        linkedHashMap.put("deviceId", str2);
        linkedHashMap.put("actionCode", str4);
        linkedHashMap.put("csvParams", str13);
        linkedHashMap.put("userId", str14);
        linkedHashMap.put("applicationSourceId", str10);
        linkedHashMap.put("deviceCSVParams", str12);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(": Starting action: ");
        stringBuilder.append(this.actionId);
        Utilities.escribeArchivo("ACTIONS", "SENDCOMMAND", stringBuilder.toString());
        String[] arrayOfString = SendCommand(str4, 30000, 3000, 30, str1, str5, "SendCommand", str16, (LinkedHashMap)linkedHashMap);
        MainActivity.access$8402(MainActivity.this, Utilities.getTime());
        if (arrayOfString == null && this.sendCommandErrorCode != this.sendCommandErrorCodeId)
          return "0"; 
        if (this.sendCommandErrorCode == this.sendCommandErrorCodeId) {
          publishProgress((Object[])new String[] { str4, "1" });
          long l = 150000L;
          try {
            Thread.sleep(l);
          } catch (InterruptedException interruptedException) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append(str2);
            stringBuilder1.append(": Error: Actions.Timer");
            Utilities.escribeArchivo("MainActivity", stringBuilder1.toString(), interruptedException.toString());
          } 
          return "0";
        } 
        this.messageId = arrayOfString[1].toString();
        str11 = arrayOfString[2];
        if (arrayOfString.length >= 3 && !str11.equals("1"))
          return this.messageId; 
        String str = Utilities.getDateTime(str2, GlobalMembers.contexGlobal);
        MainActivity.this.dbFun.updateHistorical(Boolean.valueOf(true), Long.toString(this.historicoId), "0", "0", "0", str, this.messageId, "0", Integer.toString(this.sendCommandErrorCode), "0", "0", 0, Integer.valueOf(this.actionId).intValue());
        publishProgress((Object[])new String[] { str4, str11 });
        StartSocket(str4, 30000, 300, (String)interruptedException, str6, i, this.messageId, str2, false);
        linkedHashMap.clear();
        linkedHashMap.put("sessionKey", str7);
        linkedHashMap.put("login", str8);
        linkedHashMap.put("password", str9);
        linkedHashMap.put("messageId", this.messageId);
        linkedHashMap.put("applicationSourceId", str10);
        GetCommandStatus(str4, 30000, 3000, 150, (String)stringBuilder1, str5, "GetCommandStatus", str15, (LinkedHashMap)linkedHashMap);
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str5);
        stringBuilder.append("IService1/");
        stringBuilder.append("GetLastIncomingMessageHistory2");
        str8 = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(": Starting action: ");
        stringBuilder.append(this.actionId);
        Utilities.escribeArchivo("ACTIONS", "SOCKET", stringBuilder.toString());
        StartSocket(str4, 30000, 30, (String)interruptedException, str6, i, this.messageId, str2, false);
        linkedHashMap.clear();
        linkedHashMap.put("accountId", interruptedException);
        linkedHashMap.put("deviceId", str2);
        linkedHashMap.put("actionId", str11);
        linkedHashMap.put("messageId", str11);
        GetNotifications(str4, 30000, 3000, 30, (String)stringBuilder1, str5, "GetLastIncomingMessageHistory2", str8, (LinkedHashMap)linkedHashMap);
      } 
      return this.messageId;
    }
    
    protected void onPostExecute(String param1String) {
      // Byte code:
      //   0: new com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar
      //   3: dup
      //   4: invokespecial <init> : ()V
      //   7: astore #7
      //   9: getstatic com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar.wakeActivity : Landroid/app/Activity;
      //   12: astore #5
      //   14: aload #5
      //   16: ifnull -> 26
      //   19: aload #5
      //   21: checkcast com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar
      //   24: astore #7
      //   26: ldc ''
      //   28: astore #6
      //   30: ldc ''
      //   32: putstatic com/roadtrack/onstar/BO/GlobalMembers.messsageIdGlobal : Ljava/lang/String;
      //   35: aload_0
      //   36: aload_1
      //   37: invokespecial AdjustResults : (Ljava/lang/String;)[Ljava/lang/String;
      //   40: astore #10
      //   42: aload_0
      //   43: getfield actionId : Ljava/lang/String;
      //   46: invokestatic parseInt : (Ljava/lang/String;)I
      //   49: istore #4
      //   51: aload_0
      //   52: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   55: iconst_0
      //   56: invokevirtual BannerAlpha : (Z)V
      //   59: aload_0
      //   60: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   63: iconst_0
      //   64: invokestatic access$4600 : (Lcom/roadtrack/onstar/MainActivity;Z)V
      //   67: aload_0
      //   68: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   71: iconst_0
      //   72: iconst_0
      //   73: anewarray com/roadtrack/onstar/Enums$Services
      //   76: invokevirtual setWatterMarks : (Z[Lcom/roadtrack/onstar/Enums$Services;)V
      //   79: iload #4
      //   81: getstatic com/roadtrack/onstar/Enums$Services.ActionNotifications : Lcom/roadtrack/onstar/Enums$Services;
      //   84: invokevirtual GetCode : ()I
      //   87: if_icmpne -> 490
      //   90: new com/roadtrack/onstar/DAO/DBFunctions
      //   93: dup
      //   94: aload_0
      //   95: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   98: invokevirtual getApplicationContext : ()Landroid/content/Context;
      //   101: invokespecial <init> : (Landroid/content/Context;)V
      //   104: aload_0
      //   105: getfield responseNotifications : Ljava/lang/String;
      //   108: invokevirtual processNotifications : (Ljava/lang/String;)V
      //   111: new java/lang/StringBuilder
      //   114: dup
      //   115: invokespecial <init> : ()V
      //   118: astore_1
      //   119: aload_1
      //   120: aload_0
      //   121: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   124: invokestatic access$000 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/onstarApplication;
      //   127: invokevirtual getDeviceTypeId : ()Ljava/lang/String;
      //   130: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   133: pop
      //   134: aload_1
      //   135: ldc_w '_ID'
      //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   141: pop
      //   142: aload_1
      //   143: invokevirtual toString : ()Ljava/lang/String;
      //   146: iconst_0
      //   147: aload_0
      //   148: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   151: invokestatic GetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
      //   154: ifeq -> 225
      //   157: aload_0
      //   158: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   161: invokestatic access$8600 : (Lcom/roadtrack/onstar/MainActivity;)V
      //   164: aload_0
      //   165: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   168: invokestatic access$8700 : (Lcom/roadtrack/onstar/MainActivity;)V
      //   171: aload_0
      //   172: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   175: invokestatic access$8800 : (Lcom/roadtrack/onstar/MainActivity;)V
      //   178: invokestatic AlertOn : ()V
      //   181: new java/lang/StringBuilder
      //   184: dup
      //   185: invokespecial <init> : ()V
      //   188: astore_1
      //   189: aload_1
      //   190: aload_0
      //   191: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   194: invokestatic access$000 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/onstarApplication;
      //   197: invokevirtual getDeviceTypeId : ()Ljava/lang/String;
      //   200: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   203: pop
      //   204: aload_1
      //   205: ldc_w '_ID'
      //   208: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   211: pop
      //   212: aload_1
      //   213: invokevirtual toString : ()Ljava/lang/String;
      //   216: iconst_1
      //   217: aload_0
      //   218: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   221: invokestatic SetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
      //   224: pop
      //   225: getstatic com/roadtrack/onstar/BO/GlobalMembers.notificationSpinner : Z
      //   228: ifeq -> 245
      //   231: aload_0
      //   232: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   235: getstatic com/roadtrack/onstar/MainActivity.notificationButton : Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   238: invokestatic access$8502 : (Lcom/roadtrack/onstar/MainActivity;Lcom/roadtrack/onstar/VO/CustomActionButton;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   241: pop
      //   242: goto -> 262
      //   245: aload_0
      //   246: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   249: getstatic com/roadtrack/onstar/MainActivity.buttonActions : Ljava/util/LinkedList;
      //   252: getstatic com/roadtrack/onstar/Enums$Services.ActionNotifications : Lcom/roadtrack/onstar/Enums$Services;
      //   255: invokestatic getActionButton : (Ljava/util/LinkedList;Lcom/roadtrack/onstar/Enums$Services;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   258: invokestatic access$8502 : (Lcom/roadtrack/onstar/MainActivity;Lcom/roadtrack/onstar/VO/CustomActionButton;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   261: pop
      //   262: aload_0
      //   263: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   266: invokestatic access$8500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   269: ifnull -> 283
      //   272: aload_0
      //   273: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   276: invokestatic access$8500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   279: iconst_0
      //   280: invokevirtual showActionStatus : (I)V
      //   283: invokestatic AlertOn : ()V
      //   286: aload_0
      //   287: getfield needOpenNotificationsActivity : Ljava/lang/String;
      //   290: ldc '1'
      //   292: invokevirtual equals : (Ljava/lang/Object;)Z
      //   295: ifeq -> 336
      //   298: new android/content/Intent
      //   301: dup
      //   302: aload_0
      //   303: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   306: ldc_w com/roadtrack/onstar/NotificationsActivity
      //   309: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
      //   312: astore #5
      //   314: aload_0
      //   315: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   318: invokevirtual AlertOff : ()V
      //   321: aload_0
      //   322: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   325: astore_1
      //   326: aload_1
      //   327: aload #5
      //   329: aload_1
      //   330: invokestatic access$8900 : (Lcom/roadtrack/onstar/MainActivity;)I
      //   333: invokevirtual startActivityForResult : (Landroid/content/Intent;I)V
      //   336: iconst_0
      //   337: invokestatic valueOf : (Z)Ljava/lang/Boolean;
      //   340: putstatic com/roadtrack/onstar/MainActivity.onNotification : Ljava/lang/Boolean;
      //   343: getstatic com/roadtrack/onstar/MainActivity.onFindMe : Ljava/lang/Boolean;
      //   346: invokevirtual booleanValue : ()Z
      //   349: ifne -> 489
      //   352: getstatic com/roadtrack/onstar/MainActivity.onFollowMe : Ljava/lang/Boolean;
      //   355: invokevirtual booleanValue : ()Z
      //   358: ifne -> 489
      //   361: getstatic com/roadtrack/onstar/MainActivity.onLigths : Ljava/lang/Boolean;
      //   364: invokevirtual booleanValue : ()Z
      //   367: ifne -> 489
      //   370: getstatic com/roadtrack/onstar/MainActivity.onHornLights : Ljava/lang/Boolean;
      //   373: invokevirtual booleanValue : ()Z
      //   376: ifne -> 489
      //   379: getstatic com/roadtrack/onstar/MainActivity.onCloseDoors : Ljava/lang/Boolean;
      //   382: invokevirtual booleanValue : ()Z
      //   385: ifne -> 489
      //   388: getstatic com/roadtrack/onstar/MainActivity.onOpenDoors : Ljava/lang/Boolean;
      //   391: invokevirtual booleanValue : ()Z
      //   394: ifne -> 489
      //   397: getstatic com/roadtrack/onstar/MainActivity.onDisarmPINCODE : Ljava/lang/Boolean;
      //   400: invokevirtual booleanValue : ()Z
      //   403: ifne -> 489
      //   406: getstatic com/roadtrack/onstar/MainActivity.onAlertParking : Ljava/lang/Boolean;
      //   409: invokevirtual booleanValue : ()Z
      //   412: ifne -> 489
      //   415: getstatic com/roadtrack/onstar/MainActivity.onAlertSpeed : Ljava/lang/Boolean;
      //   418: invokevirtual booleanValue : ()Z
      //   421: ifne -> 489
      //   424: getstatic com/roadtrack/onstar/MainActivity.onAlertValet : Ljava/lang/Boolean;
      //   427: invokevirtual booleanValue : ()Z
      //   430: ifne -> 489
      //   433: getstatic com/roadtrack/onstar/MainActivity.onNotification : Ljava/lang/Boolean;
      //   436: invokevirtual booleanValue : ()Z
      //   439: ifne -> 489
      //   442: getstatic com/roadtrack/onstar/MainActivity.onHorn : Ljava/lang/Boolean;
      //   445: invokevirtual booleanValue : ()Z
      //   448: ifne -> 489
      //   451: aload_0
      //   452: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   455: invokestatic access$1400 : (Lcom/roadtrack/onstar/MainActivity;)Landroid/app/Activity;
      //   458: invokestatic unlockOrientation : (Landroid/app/Activity;)V
      //   461: aload_0
      //   462: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   465: iconst_0
      //   466: iconst_0
      //   467: anewarray com/roadtrack/onstar/Enums$Services
      //   470: invokevirtual setWatterMarks : (Z[Lcom/roadtrack/onstar/Enums$Services;)V
      //   473: aload_0
      //   474: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   477: iconst_0
      //   478: invokevirtual BannerAlpha : (Z)V
      //   481: aload_0
      //   482: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   485: iconst_0
      //   486: invokestatic access$4600 : (Lcom/roadtrack/onstar/MainActivity;Z)V
      //   489: return
      //   490: aload_0
      //   491: getfield processFinished : Z
      //   494: ifne -> 727
      //   497: aload_1
      //   498: ldc '0'
      //   500: invokevirtual equals : (Ljava/lang/Object;)Z
      //   503: ifeq -> 595
      //   506: aload_0
      //   507: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   510: astore #5
      //   512: aload #5
      //   514: aload #5
      //   516: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   519: getfield global_lbl_acciondescfallared_1 : Ljava/lang/String;
      //   522: ldc_w 2131689862
      //   525: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   528: astore #6
      //   530: aload_0
      //   531: getfield sendCommandErrorCode : I
      //   534: aload_0
      //   535: getfield sendCommandErrorCodeId : I
      //   538: if_icmpne -> 572
      //   541: aload_0
      //   542: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   545: astore #5
      //   547: aload #5
      //   549: aload #5
      //   551: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   554: getfield global_lbl_acciondescposiblenotif_1 : Ljava/lang/String;
      //   557: ldc_w 2131689867
      //   560: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   563: astore #6
      //   565: ldc_w 2131165684
      //   568: istore_2
      //   569: goto -> 576
      //   572: ldc_w 2131165277
      //   575: istore_2
      //   576: aload_0
      //   577: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   580: aload_0
      //   581: getfield actionId : Ljava/lang/String;
      //   584: ldc_w 'title'
      //   587: aload #6
      //   589: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   592: goto -> 731
      //   595: aload_0
      //   596: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   599: astore #5
      //   601: aload #5
      //   603: aload #5
      //   605: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   608: getfield wizard_dicas_lbl_fallacomunicacion_7 : Ljava/lang/String;
      //   611: ldc_w 2131690516
      //   614: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   617: astore #6
      //   619: aload_0
      //   620: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   623: aload_0
      //   624: getfield actionId : Ljava/lang/String;
      //   627: ldc_w 'title'
      //   630: aload #6
      //   632: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   635: aload #10
      //   637: iconst_0
      //   638: aaload
      //   639: ldc_w '3'
      //   642: invokevirtual equals : (Ljava/lang/Object;)Z
      //   645: ifne -> 720
      //   648: invokestatic access$1100 : ()Landroid/content/Context;
      //   651: ldc_w com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar
      //   654: invokestatic isActivityRunning : (Landroid/content/Context;Ljava/lang/Class;)Z
      //   657: ifne -> 666
      //   660: getstatic com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar.isOnPause : Z
      //   663: ifeq -> 673
      //   666: aload #7
      //   668: ldc ''
      //   670: invokevirtual setStatus : (Ljava/lang/String;)V
      //   673: aload_0
      //   674: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   677: astore #5
      //   679: aload #5
      //   681: aload #5
      //   683: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   686: getfield global_lbl_acciondescposiblenotif_1 : Ljava/lang/String;
      //   689: ldc_w 2131689867
      //   692: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   695: astore #6
      //   697: aload_0
      //   698: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   701: aload_0
      //   702: getfield actionId : Ljava/lang/String;
      //   705: ldc_w 'title'
      //   708: aload #6
      //   710: invokestatic SendNotify : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   713: ldc_w 2131165684
      //   716: istore_2
      //   717: goto -> 731
      //   720: ldc_w 2131165277
      //   723: istore_2
      //   724: goto -> 731
      //   727: ldc_w 2131165620
      //   730: istore_2
      //   731: aload #10
      //   733: iconst_0
      //   734: aaload
      //   735: ldc_w '2'
      //   738: invokevirtual equals : (Ljava/lang/Object;)Z
      //   741: ifeq -> 957
      //   744: invokestatic access$1100 : ()Landroid/content/Context;
      //   747: ldc_w com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar
      //   750: invokestatic isActivityRunning : (Landroid/content/Context;Ljava/lang/Class;)Z
      //   753: ifne -> 762
      //   756: getstatic com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar.isOnPause : Z
      //   759: ifeq -> 770
      //   762: aload #7
      //   764: ldc_w 'finish'
      //   767: invokevirtual setStatus : (Ljava/lang/String;)V
      //   770: aload_0
      //   771: getfield actionId : Ljava/lang/String;
      //   774: getstatic com/roadtrack/onstar/Enums$Services.Speed : Lcom/roadtrack/onstar/Enums$Services;
      //   777: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   780: invokevirtual equals : (Ljava/lang/Object;)Z
      //   783: ifne -> 802
      //   786: aload_0
      //   787: getfield actionId : Ljava/lang/String;
      //   790: getstatic com/roadtrack/onstar/Enums$Services.SpeedUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   793: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   796: invokevirtual equals : (Ljava/lang/Object;)Z
      //   799: ifeq -> 825
      //   802: aload_0
      //   803: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   806: astore #5
      //   808: aload #5
      //   810: aload #5
      //   812: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   815: getfield actionResultWaitSpeed : Ljava/lang/String;
      //   818: ldc_w 2131689623
      //   821: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   824: pop
      //   825: aload_0
      //   826: getfield actionId : Ljava/lang/String;
      //   829: getstatic com/roadtrack/onstar/Enums$Services.SpeedAlways : Lcom/roadtrack/onstar/Enums$Services;
      //   832: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   835: invokevirtual equals : (Ljava/lang/Object;)Z
      //   838: ifeq -> 868
      //   841: aload_0
      //   842: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   845: astore #5
      //   847: aload #5
      //   849: aload #5
      //   851: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   854: getfield actionResultWaitSpeed : Ljava/lang/String;
      //   857: ldc_w 2131689623
      //   860: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   863: astore #5
      //   865: goto -> 1949
      //   868: aload_0
      //   869: getfield actionId : Ljava/lang/String;
      //   872: getstatic com/roadtrack/onstar/Enums$Services.Parking : Lcom/roadtrack/onstar/Enums$Services;
      //   875: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   878: invokevirtual equals : (Ljava/lang/Object;)Z
      //   881: ifne -> 930
      //   884: aload_0
      //   885: getfield actionId : Ljava/lang/String;
      //   888: getstatic com/roadtrack/onstar/Enums$Services.ParkingUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   891: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   894: invokevirtual equals : (Ljava/lang/Object;)Z
      //   897: ifeq -> 903
      //   900: goto -> 930
      //   903: aload_0
      //   904: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   907: astore #5
      //   909: aload #5
      //   911: aload #5
      //   913: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   916: getfield actionResultWait : Ljava/lang/String;
      //   919: ldc_w 2131689621
      //   922: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   925: astore #5
      //   927: goto -> 1949
      //   930: aload_0
      //   931: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   934: astore #5
      //   936: aload #5
      //   938: aload #5
      //   940: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   943: getfield actionResultWaitParking : Ljava/lang/String;
      //   946: ldc_w 2131689622
      //   949: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   952: astore #5
      //   954: goto -> 1949
      //   957: aload #10
      //   959: iconst_0
      //   960: aaload
      //   961: ldc_w '3'
      //   964: invokevirtual equals : (Ljava/lang/Object;)Z
      //   967: ifeq -> 1027
      //   970: invokestatic access$1100 : ()Landroid/content/Context;
      //   973: ldc_w com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar
      //   976: invokestatic isActivityRunning : (Landroid/content/Context;Ljava/lang/Class;)Z
      //   979: ifne -> 988
      //   982: getstatic com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar.isOnPause : Z
      //   985: ifeq -> 996
      //   988: aload #7
      //   990: ldc_w '3'
      //   993: invokevirtual setStatus : (Ljava/lang/String;)V
      //   996: aload_0
      //   997: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1000: astore #5
      //   1002: aload #5
      //   1004: aload #5
      //   1006: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1009: getfield global_lbl_acciondescfallared_1 : Ljava/lang/String;
      //   1012: ldc_w 2131689862
      //   1015: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1018: astore #6
      //   1020: ldc_w 2131165277
      //   1023: istore_2
      //   1024: goto -> 1953
      //   1027: aload #10
      //   1029: iconst_0
      //   1030: aaload
      //   1031: ldc_w '15'
      //   1034: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1037: ifeq -> 1590
      //   1040: invokestatic access$1100 : ()Landroid/content/Context;
      //   1043: ldc_w com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar
      //   1046: invokestatic isActivityRunning : (Landroid/content/Context;Ljava/lang/Class;)Z
      //   1049: ifne -> 1058
      //   1052: getstatic com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar.isOnPause : Z
      //   1055: ifeq -> 1066
      //   1058: aload #7
      //   1060: ldc_w 'finish'
      //   1063: invokevirtual setStatus : (Ljava/lang/String;)V
      //   1066: aload_0
      //   1067: getfield actionId : Ljava/lang/String;
      //   1070: getstatic com/roadtrack/onstar/Enums$Services.Parking : Lcom/roadtrack/onstar/Enums$Services;
      //   1073: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1076: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1079: ifne -> 1164
      //   1082: aload_0
      //   1083: getfield actionId : Ljava/lang/String;
      //   1086: getstatic com/roadtrack/onstar/Enums$Services.Speed : Lcom/roadtrack/onstar/Enums$Services;
      //   1089: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1092: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1095: ifne -> 1164
      //   1098: aload_0
      //   1099: getfield actionId : Ljava/lang/String;
      //   1102: getstatic com/roadtrack/onstar/Enums$Services.valet : Lcom/roadtrack/onstar/Enums$Services;
      //   1105: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1108: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1111: ifne -> 1164
      //   1114: aload_0
      //   1115: getfield actionId : Ljava/lang/String;
      //   1118: getstatic com/roadtrack/onstar/Enums$Services.SpeedAlways : Lcom/roadtrack/onstar/Enums$Services;
      //   1121: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1124: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1127: ifne -> 1164
      //   1130: aload_0
      //   1131: getfield actionId : Ljava/lang/String;
      //   1134: getstatic com/roadtrack/onstar/Enums$Services.ParkingUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   1137: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1140: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1143: ifne -> 1164
      //   1146: iload_2
      //   1147: istore_3
      //   1148: aload_0
      //   1149: getfield actionId : Ljava/lang/String;
      //   1152: getstatic com/roadtrack/onstar/Enums$Services.SpeedUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   1155: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1158: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1161: ifeq -> 1168
      //   1164: ldc_w 2131165632
      //   1167: istore_3
      //   1168: iload #4
      //   1170: getstatic com/roadtrack/onstar/Enums$Services.Parking : Lcom/roadtrack/onstar/Enums$Services;
      //   1173: invokevirtual GetCode : ()I
      //   1176: if_icmpeq -> 1561
      //   1179: iload #4
      //   1181: getstatic com/roadtrack/onstar/Enums$Services.ParkingUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   1184: invokevirtual GetCode : ()I
      //   1187: if_icmpne -> 1193
      //   1190: goto -> 1561
      //   1193: iload #4
      //   1195: getstatic com/roadtrack/onstar/Enums$Services.Speed : Lcom/roadtrack/onstar/Enums$Services;
      //   1198: invokevirtual GetCode : ()I
      //   1201: if_icmpeq -> 1532
      //   1204: iload #4
      //   1206: getstatic com/roadtrack/onstar/Enums$Services.SpeedUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   1209: invokevirtual GetCode : ()I
      //   1212: if_icmpeq -> 1532
      //   1215: iload #4
      //   1217: getstatic com/roadtrack/onstar/Enums$Services.SpeedAlways : Lcom/roadtrack/onstar/Enums$Services;
      //   1220: invokevirtual GetCode : ()I
      //   1223: if_icmpne -> 1229
      //   1226: goto -> 1532
      //   1229: iload #4
      //   1231: getstatic com/roadtrack/onstar/Enums$Services.valet : Lcom/roadtrack/onstar/Enums$Services;
      //   1234: invokevirtual GetCode : ()I
      //   1237: if_icmpne -> 1363
      //   1240: new java/lang/StringBuilder
      //   1243: dup
      //   1244: invokespecial <init> : ()V
      //   1247: astore #5
      //   1249: aload_0
      //   1250: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1253: astore #6
      //   1255: aload #5
      //   1257: aload #6
      //   1259: aload #6
      //   1261: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1264: getfield global_lbl_accionalertavalet_1 : Ljava/lang/String;
      //   1267: ldc_w 2131689849
      //   1270: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1273: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1276: pop
      //   1277: aload #5
      //   1279: ldc_w ' '
      //   1282: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1285: pop
      //   1286: aload_0
      //   1287: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1290: astore #6
      //   1292: aload #5
      //   1294: aload #6
      //   1296: aload #6
      //   1298: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1301: getfield global_lbl_accionstatusactivada_1 : Ljava/lang/String;
      //   1304: ldc_w 2131689886
      //   1307: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1310: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1313: pop
      //   1314: aload #5
      //   1316: ldc_w '. '
      //   1319: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1322: pop
      //   1323: aload_0
      //   1324: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1327: astore #6
      //   1329: aload #5
      //   1331: aload #6
      //   1333: aload #6
      //   1335: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1338: getfield global_lbl_acciondescesperealertavalet_1 : Ljava/lang/String;
      //   1341: ldc_w 2131689861
      //   1344: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1347: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1350: pop
      //   1351: aload #5
      //   1353: invokevirtual toString : ()Ljava/lang/String;
      //   1356: astore #5
      //   1358: iload_3
      //   1359: istore_2
      //   1360: goto -> 1949
      //   1363: aload #6
      //   1365: astore #5
      //   1367: iload_3
      //   1368: istore_2
      //   1369: iload #4
      //   1371: getstatic com/roadtrack/onstar/Enums$Services.FollowMe : Lcom/roadtrack/onstar/Enums$Services;
      //   1374: invokevirtual GetCode : ()I
      //   1377: if_icmpne -> 1949
      //   1380: aload_1
      //   1381: putstatic com/roadtrack/onstar/BO/GlobalMembers.followMeActionStatus : Ljava/lang/String;
      //   1384: aload_0
      //   1385: getfield historicoId : J
      //   1388: putstatic com/roadtrack/onstar/BO/GlobalMembers.followMeIdOnDB : J
      //   1391: aload_1
      //   1392: putstatic com/roadtrack/onstar/MainActivity.lastFollowMeIdMessage : Ljava/lang/String;
      //   1395: invokestatic access$1100 : ()Landroid/content/Context;
      //   1398: getstatic com/roadtrack/onstar/MainActivity.lastFollowMeIdMessage : Ljava/lang/String;
      //   1401: invokestatic saveLastFollowMeMessageId : (Landroid/content/Context;Ljava/lang/String;)V
      //   1404: aload #10
      //   1406: bipush #8
      //   1408: aaload
      //   1409: invokevirtual toString : ()Ljava/lang/String;
      //   1412: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
      //   1415: invokevirtual intValue : ()I
      //   1418: istore_2
      //   1419: aload #10
      //   1421: bipush #13
      //   1423: aaload
      //   1424: invokevirtual toString : ()Ljava/lang/String;
      //   1427: astore #5
      //   1429: new java/lang/StringBuilder
      //   1432: dup
      //   1433: invokespecial <init> : ()V
      //   1436: astore #8
      //   1438: aload #8
      //   1440: ldc_w 'Latitud:'
      //   1443: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1446: pop
      //   1447: aload #8
      //   1449: aload #10
      //   1451: bipush #6
      //   1453: aaload
      //   1454: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1457: pop
      //   1458: aload #8
      //   1460: ldc_w ', Longitude: '
      //   1463: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1466: pop
      //   1467: aload #8
      //   1469: aload #10
      //   1471: iconst_5
      //   1472: aaload
      //   1473: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1476: pop
      //   1477: ldc 'MainActivity'
      //   1479: ldc_w 'FOLLOWME'
      //   1482: aload #8
      //   1484: invokevirtual toString : ()Ljava/lang/String;
      //   1487: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   1490: aload_0
      //   1491: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1494: astore #8
      //   1496: aload #8
      //   1498: aload_0
      //   1499: getfield historicoId : J
      //   1502: l2i
      //   1503: aload #8
      //   1505: getfield deviceId : Ljava/lang/String;
      //   1508: aload #10
      //   1510: bipush #6
      //   1512: aaload
      //   1513: aload #10
      //   1515: iconst_5
      //   1516: aaload
      //   1517: aload #5
      //   1519: iload_2
      //   1520: invokestatic access$9000 : (Lcom/roadtrack/onstar/MainActivity;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
      //   1523: aload #6
      //   1525: astore #5
      //   1527: iload_3
      //   1528: istore_2
      //   1529: goto -> 1949
      //   1532: aload_0
      //   1533: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1536: astore #5
      //   1538: aload #5
      //   1540: aload #5
      //   1542: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1545: getfield action_speedrequested : Ljava/lang/String;
      //   1548: ldc_w 2131689634
      //   1551: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1554: astore #5
      //   1556: iload_3
      //   1557: istore_2
      //   1558: goto -> 1949
      //   1561: aload_0
      //   1562: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1565: astore #5
      //   1567: aload #5
      //   1569: aload #5
      //   1571: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1574: getfield action_parkingrequested : Ljava/lang/String;
      //   1577: ldc_w 2131689632
      //   1580: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1583: astore #5
      //   1585: iload_3
      //   1586: istore_2
      //   1587: goto -> 1949
      //   1590: aload #6
      //   1592: astore #5
      //   1594: aload #10
      //   1596: iconst_0
      //   1597: aaload
      //   1598: ldc_w '16'
      //   1601: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1604: ifeq -> 1949
      //   1607: invokestatic access$1100 : ()Landroid/content/Context;
      //   1610: ldc_w com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar
      //   1613: invokestatic isActivityRunning : (Landroid/content/Context;Ljava/lang/Class;)Z
      //   1616: ifne -> 1625
      //   1619: getstatic com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar.isOnPause : Z
      //   1622: ifeq -> 1633
      //   1625: aload #7
      //   1627: ldc_w 'finish'
      //   1630: invokevirtual setStatus : (Ljava/lang/String;)V
      //   1633: ldc_w 2131165301
      //   1636: istore_3
      //   1637: aload_0
      //   1638: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1641: astore #5
      //   1643: aload #5
      //   1645: aload #5
      //   1647: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1650: getfield global_lbl_acciondescvehiculomovimiento_1 : Ljava/lang/String;
      //   1653: ldc_w 2131689871
      //   1656: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1659: astore #5
      //   1661: aload_0
      //   1662: getfield actionId : Ljava/lang/String;
      //   1665: getstatic com/roadtrack/onstar/Enums$Services.HornLigths : Lcom/roadtrack/onstar/Enums$Services;
      //   1668: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1671: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1674: ifeq -> 1706
      //   1677: aload_0
      //   1678: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1681: astore #5
      //   1683: aload #5
      //   1685: aload #5
      //   1687: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1690: getfield global_lbl_acciondescvehiculoencendido_1 : Ljava/lang/String;
      //   1693: ldc_w 2131689870
      //   1696: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1699: astore #5
      //   1701: iload_3
      //   1702: istore_2
      //   1703: goto -> 1949
      //   1706: aload_0
      //   1707: getfield actionId : Ljava/lang/String;
      //   1710: getstatic com/roadtrack/onstar/Enums$Services.Ligths : Lcom/roadtrack/onstar/Enums$Services;
      //   1713: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1716: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1719: ifeq -> 1751
      //   1722: aload_0
      //   1723: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1726: astore #5
      //   1728: aload #5
      //   1730: aload #5
      //   1732: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1735: getfield global_lbl_acciondescvehiculoencendido_1 : Ljava/lang/String;
      //   1738: ldc_w 2131689870
      //   1741: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1744: astore #5
      //   1746: iload_3
      //   1747: istore_2
      //   1748: goto -> 1949
      //   1751: aload_0
      //   1752: getfield actionId : Ljava/lang/String;
      //   1755: getstatic com/roadtrack/onstar/Enums$Services.Horn : Lcom/roadtrack/onstar/Enums$Services;
      //   1758: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1761: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1764: ifne -> 1923
      //   1767: aload_0
      //   1768: getfield actionId : Ljava/lang/String;
      //   1771: getstatic com/roadtrack/onstar/Enums$Services.HornF1 : Lcom/roadtrack/onstar/Enums$Services;
      //   1774: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1777: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1780: ifeq -> 1786
      //   1783: goto -> 1923
      //   1786: aload_0
      //   1787: getfield actionId : Ljava/lang/String;
      //   1790: getstatic com/roadtrack/onstar/Enums$Services.DoorsLock : Lcom/roadtrack/onstar/Enums$Services;
      //   1793: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1796: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1799: ifeq -> 1831
      //   1802: aload_0
      //   1803: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1806: astore #5
      //   1808: aload #5
      //   1810: aload #5
      //   1812: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1815: getfield global_lbl_acciondescvehiculomovimiento_1 : Ljava/lang/String;
      //   1818: ldc_w 2131689871
      //   1821: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1824: astore #5
      //   1826: iload_3
      //   1827: istore_2
      //   1828: goto -> 1949
      //   1831: aload_0
      //   1832: getfield actionId : Ljava/lang/String;
      //   1835: getstatic com/roadtrack/onstar/Enums$Services.DoorsUnlock : Lcom/roadtrack/onstar/Enums$Services;
      //   1838: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1841: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1844: ifeq -> 1876
      //   1847: aload_0
      //   1848: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1851: astore #5
      //   1853: aload #5
      //   1855: aload #5
      //   1857: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1860: getfield global_lbl_acciondescvehiculomovimiento_1 : Ljava/lang/String;
      //   1863: ldc_w 2131689871
      //   1866: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1869: astore #5
      //   1871: iload_3
      //   1872: istore_2
      //   1873: goto -> 1949
      //   1876: iload_3
      //   1877: istore_2
      //   1878: aload_0
      //   1879: getfield actionId : Ljava/lang/String;
      //   1882: getstatic com/roadtrack/onstar/Enums$Services.Disarm : Lcom/roadtrack/onstar/Enums$Services;
      //   1885: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   1888: invokevirtual equals : (Ljava/lang/Object;)Z
      //   1891: ifeq -> 1949
      //   1894: aload_0
      //   1895: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1898: astore #5
      //   1900: aload #5
      //   1902: aload #5
      //   1904: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1907: getfield global_lbl_acciondescvehiculomovimiento_1 : Ljava/lang/String;
      //   1910: ldc_w 2131689871
      //   1913: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1916: astore #5
      //   1918: iload_3
      //   1919: istore_2
      //   1920: goto -> 1949
      //   1923: aload_0
      //   1924: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1927: astore #5
      //   1929: aload #5
      //   1931: aload #5
      //   1933: invokestatic access$1500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/StringsResourcesVO;
      //   1936: getfield global_lbl_acciondescvehiculoencendido_1 : Ljava/lang/String;
      //   1939: ldc_w 2131689870
      //   1942: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
      //   1945: astore #5
      //   1947: iload_3
      //   1948: istore_2
      //   1949: aload #5
      //   1951: astore #6
      //   1953: aload #6
      //   1955: invokevirtual length : ()I
      //   1958: ifle -> 1974
      //   1961: aload_0
      //   1962: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1965: aload #6
      //   1967: iconst_1
      //   1968: invokestatic makeText : (Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
      //   1971: invokevirtual show : ()V
      //   1974: aload_0
      //   1975: getfield actionId : Ljava/lang/String;
      //   1978: invokestatic parseInt : (Ljava/lang/String;)I
      //   1981: invokestatic GetValue : (I)Lcom/roadtrack/onstar/Enums$Services;
      //   1984: astore #5
      //   1986: aload #5
      //   1988: ifnonnull -> 1992
      //   1991: return
      //   1992: aload #5
      //   1994: getstatic com/roadtrack/onstar/Enums$Services.HornF1 : Lcom/roadtrack/onstar/Enums$Services;
      //   1997: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2000: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2003: ifeq -> 2026
      //   2006: aload_0
      //   2007: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2010: getstatic com/roadtrack/onstar/MainActivity.buttonActions : Ljava/util/LinkedList;
      //   2013: getstatic com/roadtrack/onstar/Enums$Services.HornF1 : Lcom/roadtrack/onstar/Enums$Services;
      //   2016: invokestatic getActionButton : (Ljava/util/LinkedList;Lcom/roadtrack/onstar/Enums$Services;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   2019: invokestatic access$8502 : (Lcom/roadtrack/onstar/MainActivity;Lcom/roadtrack/onstar/VO/CustomActionButton;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   2022: pop
      //   2023: goto -> 2042
      //   2026: aload_0
      //   2027: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2030: getstatic com/roadtrack/onstar/MainActivity.buttonActions : Ljava/util/LinkedList;
      //   2033: aload #5
      //   2035: invokestatic getActionButton : (Ljava/util/LinkedList;Lcom/roadtrack/onstar/Enums$Services;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   2038: invokestatic access$8502 : (Lcom/roadtrack/onstar/MainActivity;Lcom/roadtrack/onstar/VO/CustomActionButton;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   2041: pop
      //   2042: aload_0
      //   2043: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2046: invokestatic access$8500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   2049: ifnull -> 2066
      //   2052: aload_0
      //   2053: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2056: invokestatic access$8500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   2059: iload_2
      //   2060: invokestatic getStatusFromResource : (I)I
      //   2063: invokevirtual showActionStatus : (I)V
      //   2066: aload_0
      //   2067: getfield actionId : Ljava/lang/String;
      //   2070: getstatic com/roadtrack/onstar/Enums$Services.DoorsLock : Lcom/roadtrack/onstar/Enums$Services;
      //   2073: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2076: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2079: ifeq -> 2092
      //   2082: iload_2
      //   2083: invokestatic getStatusFromResource : (I)I
      //   2086: putstatic com/roadtrack/onstar/MainActivity.actCloseDoors : I
      //   2089: goto -> 2403
      //   2092: aload_0
      //   2093: getfield actionId : Ljava/lang/String;
      //   2096: getstatic com/roadtrack/onstar/Enums$Services.DoorsUnlock : Lcom/roadtrack/onstar/Enums$Services;
      //   2099: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2102: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2105: ifeq -> 2118
      //   2108: iload_2
      //   2109: invokestatic getStatusFromResource : (I)I
      //   2112: putstatic com/roadtrack/onstar/MainActivity.actOpenDoors : I
      //   2115: goto -> 2403
      //   2118: aload_0
      //   2119: getfield actionId : Ljava/lang/String;
      //   2122: getstatic com/roadtrack/onstar/Enums$Services.Parking : Lcom/roadtrack/onstar/Enums$Services;
      //   2125: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2128: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2131: ifne -> 2396
      //   2134: aload_0
      //   2135: getfield actionId : Ljava/lang/String;
      //   2138: getstatic com/roadtrack/onstar/Enums$Services.ParkingUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   2141: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2144: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2147: ifeq -> 2153
      //   2150: goto -> 2396
      //   2153: aload_0
      //   2154: getfield actionId : Ljava/lang/String;
      //   2157: getstatic com/roadtrack/onstar/Enums$Services.Ligths : Lcom/roadtrack/onstar/Enums$Services;
      //   2160: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2163: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2166: ifeq -> 2179
      //   2169: iload_2
      //   2170: invokestatic getStatusFromResource : (I)I
      //   2173: putstatic com/roadtrack/onstar/MainActivity.actLigths : I
      //   2176: goto -> 2403
      //   2179: aload_0
      //   2180: getfield actionId : Ljava/lang/String;
      //   2183: getstatic com/roadtrack/onstar/Enums$Services.HornLigths : Lcom/roadtrack/onstar/Enums$Services;
      //   2186: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2189: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2192: ifeq -> 2205
      //   2195: iload_2
      //   2196: invokestatic getStatusFromResource : (I)I
      //   2199: putstatic com/roadtrack/onstar/MainActivity.actHornLights : I
      //   2202: goto -> 2403
      //   2205: aload_0
      //   2206: getfield actionId : Ljava/lang/String;
      //   2209: getstatic com/roadtrack/onstar/Enums$Services.Horn : Lcom/roadtrack/onstar/Enums$Services;
      //   2212: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2215: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2218: ifeq -> 2231
      //   2221: iload_2
      //   2222: invokestatic getStatusFromResource : (I)I
      //   2225: putstatic com/roadtrack/onstar/MainActivity.actHorn : I
      //   2228: goto -> 2403
      //   2231: aload_0
      //   2232: getfield actionId : Ljava/lang/String;
      //   2235: getstatic com/roadtrack/onstar/Enums$Services.Speed : Lcom/roadtrack/onstar/Enums$Services;
      //   2238: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2241: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2244: ifne -> 2386
      //   2247: aload_0
      //   2248: getfield actionId : Ljava/lang/String;
      //   2251: getstatic com/roadtrack/onstar/Enums$Services.SpeedAlways : Lcom/roadtrack/onstar/Enums$Services;
      //   2254: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2257: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2260: ifne -> 2386
      //   2263: aload_0
      //   2264: getfield actionId : Ljava/lang/String;
      //   2267: getstatic com/roadtrack/onstar/Enums$Services.SpeedUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   2270: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2273: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2276: ifeq -> 2282
      //   2279: goto -> 2386
      //   2282: aload_0
      //   2283: getfield actionId : Ljava/lang/String;
      //   2286: getstatic com/roadtrack/onstar/Enums$Services.FollowMe : Lcom/roadtrack/onstar/Enums$Services;
      //   2289: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2292: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2295: ifeq -> 2308
      //   2298: iload_2
      //   2299: invokestatic getStatusFromResource : (I)I
      //   2302: putstatic com/roadtrack/onstar/MainActivity.actFollowMe : I
      //   2305: goto -> 2403
      //   2308: aload_0
      //   2309: getfield actionId : Ljava/lang/String;
      //   2312: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
      //   2315: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2318: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2321: ifeq -> 2334
      //   2324: iload_2
      //   2325: invokestatic getStatusFromResource : (I)I
      //   2328: putstatic com/roadtrack/onstar/MainActivity.actFindMe : I
      //   2331: goto -> 2403
      //   2334: aload_0
      //   2335: getfield actionId : Ljava/lang/String;
      //   2338: getstatic com/roadtrack/onstar/Enums$Services.valet : Lcom/roadtrack/onstar/Enums$Services;
      //   2341: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2344: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2347: ifeq -> 2360
      //   2350: iload_2
      //   2351: invokestatic getStatusFromResource : (I)I
      //   2354: putstatic com/roadtrack/onstar/MainActivity.actAlertValet : I
      //   2357: goto -> 2403
      //   2360: aload_0
      //   2361: getfield actionId : Ljava/lang/String;
      //   2364: getstatic com/roadtrack/onstar/Enums$Services.Disarm : Lcom/roadtrack/onstar/Enums$Services;
      //   2367: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   2370: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2373: ifeq -> 2403
      //   2376: iload_2
      //   2377: invokestatic getStatusFromResource : (I)I
      //   2380: putstatic com/roadtrack/onstar/MainActivity.actDisarmPINCODE : I
      //   2383: goto -> 2403
      //   2386: iload_2
      //   2387: invokestatic getStatusFromResource : (I)I
      //   2390: putstatic com/roadtrack/onstar/MainActivity.actAlertSpeed : I
      //   2393: goto -> 2403
      //   2396: iload_2
      //   2397: invokestatic getStatusFromResource : (I)I
      //   2400: putstatic com/roadtrack/onstar/MainActivity.actAlertParking : I
      //   2403: aload_0
      //   2404: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2407: iconst_0
      //   2408: iconst_0
      //   2409: anewarray com/roadtrack/onstar/Enums$Services
      //   2412: invokevirtual setWatterMarks : (Z[Lcom/roadtrack/onstar/Enums$Services;)V
      //   2415: aload_0
      //   2416: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2419: iconst_0
      //   2420: invokevirtual BannerAlpha : (Z)V
      //   2423: aload_0
      //   2424: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2427: iconst_0
      //   2428: invokestatic access$4600 : (Lcom/roadtrack/onstar/MainActivity;Z)V
      //   2431: new java/lang/Thread
      //   2434: dup
      //   2435: new com/roadtrack/onstar/MainActivity$Actions$2
      //   2438: dup
      //   2439: aload_0
      //   2440: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity$Actions;)V
      //   2443: invokespecial <init> : (Ljava/lang/Runnable;)V
      //   2446: invokevirtual start : ()V
      //   2449: aload_0
      //   2450: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2453: aconst_null
      //   2454: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   2457: pop
      //   2458: aload_0
      //   2459: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2462: aconst_null
      //   2463: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   2466: pop
      //   2467: aload #10
      //   2469: ifnull -> 2530
      //   2472: aload #10
      //   2474: arraylength
      //   2475: ifle -> 2530
      //   2478: aload #10
      //   2480: iconst_5
      //   2481: aaload
      //   2482: ifnull -> 2530
      //   2485: aload #10
      //   2487: iconst_5
      //   2488: aaload
      //   2489: invokevirtual isEmpty : ()Z
      //   2492: ifne -> 2530
      //   2495: aload #10
      //   2497: bipush #6
      //   2499: aaload
      //   2500: ifnull -> 2530
      //   2503: aload #10
      //   2505: bipush #6
      //   2507: aaload
      //   2508: invokevirtual isEmpty : ()Z
      //   2511: ifne -> 2530
      //   2514: aload #10
      //   2516: bipush #6
      //   2518: aaload
      //   2519: astore #8
      //   2521: aload #10
      //   2523: iconst_5
      //   2524: aaload
      //   2525: astore #9
      //   2527: goto -> 2538
      //   2530: ldc '0'
      //   2532: astore #8
      //   2534: ldc '0'
      //   2536: astore #9
      //   2538: aload_0
      //   2539: getfield processFinished : Z
      //   2542: ifne -> 2572
      //   2545: aload_0
      //   2546: getfield sendCommandErrorCode : I
      //   2549: ifne -> 2564
      //   2552: ldc_w '408'
      //   2555: astore #7
      //   2557: ldc '1'
      //   2559: astore #5
      //   2561: goto -> 2622
      //   2564: ldc_w '2'
      //   2567: astore #5
      //   2569: goto -> 2618
      //   2572: aload #10
      //   2574: iconst_0
      //   2575: aaload
      //   2576: ldc_w '3'
      //   2579: invokevirtual equals : (Ljava/lang/Object;)Z
      //   2582: ifeq -> 2614
      //   2585: invokestatic access$1100 : ()Landroid/content/Context;
      //   2588: ldc_w com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar
      //   2591: invokestatic isActivityRunning : (Landroid/content/Context;Ljava/lang/Class;)Z
      //   2594: ifne -> 2603
      //   2597: getstatic com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar.isOnPause : Z
      //   2600: ifeq -> 2564
      //   2603: aload #7
      //   2605: ldc_w '3'
      //   2608: invokevirtual setStatus : (Ljava/lang/String;)V
      //   2611: goto -> 2564
      //   2614: ldc '1'
      //   2616: astore #5
      //   2618: ldc '0'
      //   2620: astore #7
      //   2622: aload #10
      //   2624: bipush #8
      //   2626: aaload
      //   2627: invokevirtual toString : ()Ljava/lang/String;
      //   2630: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
      //   2633: invokevirtual intValue : ()I
      //   2636: istore_2
      //   2637: aload_0
      //   2638: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2641: getfield deviceId : Ljava/lang/String;
      //   2644: getstatic com/roadtrack/onstar/BO/GlobalMembers.contexGlobal : Landroid/content/Context;
      //   2647: invokestatic getDateTime : (Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
      //   2650: astore #11
      //   2652: aload_0
      //   2653: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2656: new com/roadtrack/onstar/DAO/DBFunctions
      //   2659: dup
      //   2660: invokestatic access$1100 : ()Landroid/content/Context;
      //   2663: invokespecial <init> : (Landroid/content/Context;)V
      //   2666: invokestatic access$1002 : (Lcom/roadtrack/onstar/MainActivity;Lcom/roadtrack/onstar/DAO/DBFunctions;)Lcom/roadtrack/onstar/DAO/DBFunctions;
      //   2669: pop
      //   2670: aload_0
      //   2671: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2674: invokestatic access$1000 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/DAO/DBFunctions;
      //   2677: iconst_1
      //   2678: invokestatic valueOf : (Z)Ljava/lang/Boolean;
      //   2681: aload_0
      //   2682: getfield historicoId : J
      //   2685: invokestatic toString : (J)Ljava/lang/String;
      //   2688: aload #5
      //   2690: aload #8
      //   2692: aload #9
      //   2694: aload #11
      //   2696: aload_1
      //   2697: aload #10
      //   2699: iconst_0
      //   2700: aaload
      //   2701: aload_0
      //   2702: getfield sendCommandErrorCode : I
      //   2705: invokestatic toString : (I)Ljava/lang/String;
      //   2708: aload #7
      //   2710: aload #6
      //   2712: iload_2
      //   2713: aload_0
      //   2714: getfield actionId : Ljava/lang/String;
      //   2717: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
      //   2720: invokevirtual intValue : ()I
      //   2723: invokevirtual updateHistorical : (Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V
      //   2726: getstatic com/roadtrack/onstar/BO/GlobalMembers.notificaciones : I
      //   2729: iconst_1
      //   2730: iadd
      //   2731: putstatic com/roadtrack/onstar/BO/GlobalMembers.notificaciones : I
      //   2734: invokestatic AlertOn : ()V
      //   2737: new java/lang/StringBuilder
      //   2740: dup
      //   2741: invokespecial <init> : ()V
      //   2744: astore_1
      //   2745: aload_1
      //   2746: aload_0
      //   2747: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2750: invokestatic access$000 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/onstarApplication;
      //   2753: invokevirtual getDeviceTypeId : ()Ljava/lang/String;
      //   2756: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2759: pop
      //   2760: aload_1
      //   2761: ldc_w '_ID'
      //   2764: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   2767: pop
      //   2768: aload_1
      //   2769: invokevirtual toString : ()Ljava/lang/String;
      //   2772: iconst_1
      //   2773: aload_0
      //   2774: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2777: invokestatic SetValuePreference : (Ljava/lang/String;ZLandroid/content/Context;)Z
      //   2780: pop
      //   2781: aload_0
      //   2782: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2785: invokestatic access$000 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/onstarApplication;
      //   2788: ldc 'MainActivity'
      //   2790: invokestatic getLastKnownDeviceSelected : (Lcom/roadtrack/onstar/onstarApplication;Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserDevicesVO;
      //   2793: invokevirtual getName : ()Ljava/lang/String;
      //   2796: aload_0
      //   2797: getfield actionId : Ljava/lang/String;
      //   2800: aload #10
      //   2802: iconst_0
      //   2803: aaload
      //   2804: invokestatic logActions : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   2807: aload_0
      //   2808: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   2811: invokevirtual getApplicationContext : ()Landroid/content/Context;
      //   2814: invokestatic GCMHeartBeat : (Landroid/content/Context;)V
      //   2817: getstatic com/roadtrack/onstar/MainActivity.onFindMe : Ljava/lang/Boolean;
      //   2820: invokevirtual booleanValue : ()Z
      //   2823: ifne -> 2943
      //   2826: getstatic com/roadtrack/onstar/MainActivity.onFollowMe : Ljava/lang/Boolean;
      //   2829: invokevirtual booleanValue : ()Z
      //   2832: ifne -> 2943
      //   2835: getstatic com/roadtrack/onstar/MainActivity.onLigths : Ljava/lang/Boolean;
      //   2838: invokevirtual booleanValue : ()Z
      //   2841: ifne -> 2943
      //   2844: getstatic com/roadtrack/onstar/MainActivity.onHornLights : Ljava/lang/Boolean;
      //   2847: invokevirtual booleanValue : ()Z
      //   2850: ifne -> 2943
      //   2853: getstatic com/roadtrack/onstar/MainActivity.onCloseDoors : Ljava/lang/Boolean;
      //   2856: invokevirtual booleanValue : ()Z
      //   2859: ifne -> 2943
      //   2862: getstatic com/roadtrack/onstar/MainActivity.onOpenDoors : Ljava/lang/Boolean;
      //   2865: invokevirtual booleanValue : ()Z
      //   2868: ifne -> 2943
      //   2871: getstatic com/roadtrack/onstar/MainActivity.onDisarmPINCODE : Ljava/lang/Boolean;
      //   2874: invokevirtual booleanValue : ()Z
      //   2877: ifne -> 2943
      //   2880: getstatic com/roadtrack/onstar/MainActivity.onAlertParking : Ljava/lang/Boolean;
      //   2883: invokevirtual booleanValue : ()Z
      //   2886: ifne -> 2943
      //   2889: getstatic com/roadtrack/onstar/MainActivity.onAlertSpeed : Ljava/lang/Boolean;
      //   2892: invokevirtual booleanValue : ()Z
      //   2895: ifne -> 2943
      //   2898: getstatic com/roadtrack/onstar/MainActivity.onAlertValet : Ljava/lang/Boolean;
      //   2901: invokevirtual booleanValue : ()Z
      //   2904: ifne -> 2943
      //   2907: getstatic com/roadtrack/onstar/MainActivity.onNotification : Ljava/lang/Boolean;
      //   2910: invokevirtual booleanValue : ()Z
      //   2913: ifne -> 2943
      //   2916: getstatic com/roadtrack/onstar/MainActivity.onHorn : Ljava/lang/Boolean;
      //   2919: invokevirtual booleanValue : ()Z
      //   2922: ifne -> 2943
      //   2925: new java/lang/Thread
      //   2928: dup
      //   2929: new com/roadtrack/onstar/MainActivity$Actions$3
      //   2932: dup
      //   2933: aload_0
      //   2934: invokespecial <init> : (Lcom/roadtrack/onstar/MainActivity$Actions;)V
      //   2937: invokespecial <init> : (Ljava/lang/Runnable;)V
      //   2940: invokevirtual start : ()V
      //   2943: aload_0
      //   2944: invokestatic nanoTime : ()J
      //   2947: l2d
      //   2948: putfield endTime : D
      //   2951: return
    }
    
    protected void onPreExecute() {
      this.startTime = System.nanoTime();
      OrientationManager.lockOrientation(MainActivity.this.act);
      if (!MainActivity.this.actionCode.equals(Enums$Services.ActionNotifications.GetCodeString()))
        MainActivity.this.lockRightDrawer(true); 
      GlobalMembers.contexGlobal = MainActivity.this.getApplicationContext();
      GlobalMembers.processMessageId = false;
      GlobalMembers.messsageIdGlobal = null;
    }
    
    protected void onProgressUpdate(String... param1VarArgs) {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore #5
      //   5: aload_1
      //   6: iconst_1
      //   7: aaload
      //   8: astore #6
      //   10: aload_0
      //   11: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   14: astore #4
      //   16: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.palomita_azul_fondo : Ljava/lang/String;
      //   19: astore_1
      //   20: ldc_w 2131165626
      //   23: istore_3
      //   24: aload #4
      //   26: aload_1
      //   27: ldc_w 2131165626
      //   30: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
      //   33: astore #4
      //   35: aload #5
      //   37: getstatic com/roadtrack/onstar/Enums$Services.Ligths : Lcom/roadtrack/onstar/Enums$Services;
      //   40: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   43: invokevirtual equals : (Ljava/lang/Object;)Z
      //   46: ifne -> 96
      //   49: aload #5
      //   51: getstatic com/roadtrack/onstar/Enums$Services.HornLigths : Lcom/roadtrack/onstar/Enums$Services;
      //   54: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   57: invokevirtual equals : (Ljava/lang/Object;)Z
      //   60: ifne -> 96
      //   63: aload #5
      //   65: getstatic com/roadtrack/onstar/Enums$Services.DoorsLock : Lcom/roadtrack/onstar/Enums$Services;
      //   68: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   71: invokevirtual equals : (Ljava/lang/Object;)Z
      //   74: ifne -> 96
      //   77: aload #4
      //   79: astore_1
      //   80: iload_3
      //   81: istore_2
      //   82: aload #5
      //   84: getstatic com/roadtrack/onstar/Enums$Services.DoorsUnlock : Lcom/roadtrack/onstar/Enums$Services;
      //   87: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   90: invokevirtual equals : (Ljava/lang/Object;)Z
      //   93: ifeq -> 189
      //   96: aload #4
      //   98: astore_1
      //   99: iload_3
      //   100: istore_2
      //   101: aload #6
      //   103: ldc_w '2'
      //   106: invokevirtual equals : (Ljava/lang/Object;)Z
      //   109: ifeq -> 189
      //   112: ldc_w 2131165620
      //   115: istore_2
      //   116: aload_0
      //   117: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   120: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.palomita_2azul_executado_fondo : Ljava/lang/String;
      //   123: ldc_w 2131165620
      //   126: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
      //   129: astore_1
      //   130: new java/lang/StringBuilder
      //   133: dup
      //   134: invokespecial <init> : ()V
      //   137: astore #4
      //   139: aload #4
      //   141: aload_0
      //   142: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   145: getfield deviceId : Ljava/lang/String;
      //   148: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   151: pop
      //   152: aload #4
      //   154: ldc ': action: '
      //   156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   159: pop
      //   160: aload #4
      //   162: aload #5
      //   164: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   167: pop
      //   168: aload #4
      //   170: ldc_w ': PALOMA 2 GRIS'
      //   173: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   176: pop
      //   177: ldc 'ACTIONS'
      //   179: ldc ''
      //   181: aload #4
      //   183: invokevirtual toString : ()Ljava/lang/String;
      //   186: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   189: aload #5
      //   191: invokestatic parseInt : (Ljava/lang/String;)I
      //   194: invokestatic GetValue : (I)Lcom/roadtrack/onstar/Enums$Services;
      //   197: astore #4
      //   199: aload #4
      //   201: ifnonnull -> 205
      //   204: return
      //   205: aload_0
      //   206: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   209: getstatic com/roadtrack/onstar/MainActivity.buttonActions : Ljava/util/LinkedList;
      //   212: aload #4
      //   214: invokestatic getActionButton : (Ljava/util/LinkedList;Lcom/roadtrack/onstar/Enums$Services;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   217: invokestatic access$8502 : (Lcom/roadtrack/onstar/MainActivity;Lcom/roadtrack/onstar/VO/CustomActionButton;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   220: pop
      //   221: aload #5
      //   223: getstatic com/roadtrack/onstar/Enums$Services.FindMe : Lcom/roadtrack/onstar/Enums$Services;
      //   226: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   229: invokevirtual equals : (Ljava/lang/Object;)Z
      //   232: ifeq -> 279
      //   235: aload_0
      //   236: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   239: astore #4
      //   241: aload #4
      //   243: aload #4
      //   245: getfield FindmegenericProgressBar : Landroid/widget/ProgressBar;
      //   248: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   251: pop
      //   252: aload_0
      //   253: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   256: astore #4
      //   258: aload #4
      //   260: aload #4
      //   262: getfield FindmegenericView : Landroid/view/View;
      //   265: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   268: pop
      //   269: iload_2
      //   270: invokestatic getStatusFromResource : (I)I
      //   273: putstatic com/roadtrack/onstar/MainActivity.actFindMe : I
      //   276: goto -> 1027
      //   279: aload #5
      //   281: getstatic com/roadtrack/onstar/Enums$Services.FollowMe : Lcom/roadtrack/onstar/Enums$Services;
      //   284: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   287: invokevirtual equals : (Ljava/lang/Object;)Z
      //   290: ifne -> 986
      //   293: aload #5
      //   295: getstatic com/roadtrack/onstar/Enums$Services.FollowMeUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   298: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   301: invokevirtual equals : (Ljava/lang/Object;)Z
      //   304: ifeq -> 310
      //   307: goto -> 986
      //   310: aload #5
      //   312: getstatic com/roadtrack/onstar/Enums$Services.Ligths : Lcom/roadtrack/onstar/Enums$Services;
      //   315: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   318: invokevirtual equals : (Ljava/lang/Object;)Z
      //   321: ifeq -> 368
      //   324: aload_0
      //   325: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   328: astore #4
      //   330: aload #4
      //   332: aload #4
      //   334: getfield LigthsgenericProgressBar : Landroid/widget/ProgressBar;
      //   337: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   340: pop
      //   341: aload_0
      //   342: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   345: astore #4
      //   347: aload #4
      //   349: aload #4
      //   351: getfield LigthsgenericView : Landroid/view/View;
      //   354: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   357: pop
      //   358: iload_2
      //   359: invokestatic getStatusFromResource : (I)I
      //   362: putstatic com/roadtrack/onstar/MainActivity.actLigths : I
      //   365: goto -> 1027
      //   368: aload #5
      //   370: getstatic com/roadtrack/onstar/Enums$Services.HornLigths : Lcom/roadtrack/onstar/Enums$Services;
      //   373: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   376: invokevirtual equals : (Ljava/lang/Object;)Z
      //   379: ifeq -> 426
      //   382: aload_0
      //   383: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   386: astore #4
      //   388: aload #4
      //   390: aload #4
      //   392: getfield HornLigthsgenericProgressBar : Landroid/widget/ProgressBar;
      //   395: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   398: pop
      //   399: aload_0
      //   400: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   403: astore #4
      //   405: aload #4
      //   407: aload #4
      //   409: getfield HornLigthsgenericView : Landroid/view/View;
      //   412: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   415: pop
      //   416: iload_2
      //   417: invokestatic getStatusFromResource : (I)I
      //   420: putstatic com/roadtrack/onstar/MainActivity.actHornLights : I
      //   423: goto -> 1027
      //   426: aload #5
      //   428: getstatic com/roadtrack/onstar/Enums$Services.Horn : Lcom/roadtrack/onstar/Enums$Services;
      //   431: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   434: invokevirtual equals : (Ljava/lang/Object;)Z
      //   437: ifne -> 942
      //   440: aload #5
      //   442: getstatic com/roadtrack/onstar/Enums$Services.HornF1 : Lcom/roadtrack/onstar/Enums$Services;
      //   445: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   448: invokevirtual equals : (Ljava/lang/Object;)Z
      //   451: ifeq -> 457
      //   454: goto -> 942
      //   457: aload #5
      //   459: getstatic com/roadtrack/onstar/Enums$Services.DoorsLock : Lcom/roadtrack/onstar/Enums$Services;
      //   462: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   465: invokevirtual equals : (Ljava/lang/Object;)Z
      //   468: ifeq -> 515
      //   471: aload_0
      //   472: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   475: astore #4
      //   477: aload #4
      //   479: aload #4
      //   481: getfield DoorsLockgenericProgressBar : Landroid/widget/ProgressBar;
      //   484: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   487: pop
      //   488: aload_0
      //   489: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   492: astore #4
      //   494: aload #4
      //   496: aload #4
      //   498: getfield DoorsLockgenericView : Landroid/view/View;
      //   501: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   504: pop
      //   505: iload_2
      //   506: invokestatic getStatusFromResource : (I)I
      //   509: putstatic com/roadtrack/onstar/MainActivity.actCloseDoors : I
      //   512: goto -> 1027
      //   515: aload #5
      //   517: getstatic com/roadtrack/onstar/Enums$Services.DoorsUnlock : Lcom/roadtrack/onstar/Enums$Services;
      //   520: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   523: invokevirtual equals : (Ljava/lang/Object;)Z
      //   526: ifeq -> 573
      //   529: aload_0
      //   530: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   533: astore #4
      //   535: aload #4
      //   537: aload #4
      //   539: getfield DoorsUnlockgenericProgressBar : Landroid/widget/ProgressBar;
      //   542: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   545: pop
      //   546: aload_0
      //   547: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   550: astore #4
      //   552: aload #4
      //   554: aload #4
      //   556: getfield DoorsUnlockgenericView : Landroid/view/View;
      //   559: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   562: pop
      //   563: iload_2
      //   564: invokestatic getStatusFromResource : (I)I
      //   567: putstatic com/roadtrack/onstar/MainActivity.actOpenDoors : I
      //   570: goto -> 1027
      //   573: aload #5
      //   575: getstatic com/roadtrack/onstar/Enums$Services.Disarm : Lcom/roadtrack/onstar/Enums$Services;
      //   578: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   581: invokevirtual equals : (Ljava/lang/Object;)Z
      //   584: ifeq -> 631
      //   587: aload_0
      //   588: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   591: astore #4
      //   593: aload #4
      //   595: aload #4
      //   597: getfield DisarmgenericProgressBar : Landroid/widget/ProgressBar;
      //   600: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   603: pop
      //   604: aload_0
      //   605: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   608: astore #4
      //   610: aload #4
      //   612: aload #4
      //   614: getfield DisarmgenericView : Landroid/view/View;
      //   617: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   620: pop
      //   621: iload_2
      //   622: invokestatic getStatusFromResource : (I)I
      //   625: putstatic com/roadtrack/onstar/MainActivity.actDisarmPINCODE : I
      //   628: goto -> 1027
      //   631: aload #5
      //   633: getstatic com/roadtrack/onstar/Enums$Services.Parking : Lcom/roadtrack/onstar/Enums$Services;
      //   636: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   639: invokevirtual equals : (Ljava/lang/Object;)Z
      //   642: ifne -> 898
      //   645: aload #5
      //   647: getstatic com/roadtrack/onstar/Enums$Services.ParkingUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   650: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   653: invokevirtual equals : (Ljava/lang/Object;)Z
      //   656: ifeq -> 662
      //   659: goto -> 898
      //   662: aload #5
      //   664: getstatic com/roadtrack/onstar/Enums$Services.Speed : Lcom/roadtrack/onstar/Enums$Services;
      //   667: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   670: invokevirtual equals : (Ljava/lang/Object;)Z
      //   673: ifne -> 854
      //   676: aload #5
      //   678: getstatic com/roadtrack/onstar/Enums$Services.SpeedUUx : Lcom/roadtrack/onstar/Enums$Services;
      //   681: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   684: invokevirtual equals : (Ljava/lang/Object;)Z
      //   687: ifeq -> 693
      //   690: goto -> 854
      //   693: aload #5
      //   695: getstatic com/roadtrack/onstar/Enums$Services.SpeedAlways : Lcom/roadtrack/onstar/Enums$Services;
      //   698: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   701: invokevirtual equals : (Ljava/lang/Object;)Z
      //   704: ifeq -> 751
      //   707: aload_0
      //   708: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   711: astore #4
      //   713: aload #4
      //   715: aload #4
      //   717: getfield SpeedgenericProgressBar : Landroid/widget/ProgressBar;
      //   720: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   723: pop
      //   724: aload_0
      //   725: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   728: astore #4
      //   730: aload #4
      //   732: aload #4
      //   734: getfield SpeedgenericView : Landroid/view/View;
      //   737: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   740: pop
      //   741: iload_2
      //   742: invokestatic getStatusFromResource : (I)I
      //   745: putstatic com/roadtrack/onstar/MainActivity.actAlertSpeed : I
      //   748: goto -> 1027
      //   751: aload #5
      //   753: getstatic com/roadtrack/onstar/Enums$Services.valet : Lcom/roadtrack/onstar/Enums$Services;
      //   756: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   759: invokevirtual equals : (Ljava/lang/Object;)Z
      //   762: ifeq -> 809
      //   765: aload_0
      //   766: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   769: astore #4
      //   771: aload #4
      //   773: aload #4
      //   775: getfield valetgenericProgressBar : Landroid/widget/ProgressBar;
      //   778: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   781: pop
      //   782: aload_0
      //   783: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   786: astore #4
      //   788: aload #4
      //   790: aload #4
      //   792: getfield valetgenericView : Landroid/view/View;
      //   795: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   798: pop
      //   799: iload_2
      //   800: invokestatic getStatusFromResource : (I)I
      //   803: putstatic com/roadtrack/onstar/MainActivity.actAlertValet : I
      //   806: goto -> 1027
      //   809: aload #5
      //   811: getstatic com/roadtrack/onstar/Enums$Services.ActionNotifications : Lcom/roadtrack/onstar/Enums$Services;
      //   814: invokevirtual GetCodeString : ()Ljava/lang/String;
      //   817: invokevirtual equals : (Ljava/lang/Object;)Z
      //   820: ifeq -> 1027
      //   823: aload_0
      //   824: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   827: astore #4
      //   829: aload #4
      //   831: aload #4
      //   833: getfield NotificationsProgressBar : Landroid/widget/ProgressBar;
      //   836: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   839: pop
      //   840: aload_0
      //   841: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   844: getstatic com/roadtrack/onstar/MainActivity.NotificationView : Landroid/view/View;
      //   847: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   850: pop
      //   851: goto -> 1027
      //   854: aload_0
      //   855: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   858: astore #4
      //   860: aload #4
      //   862: aload #4
      //   864: getfield SpeedgenericProgressBar : Landroid/widget/ProgressBar;
      //   867: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   870: pop
      //   871: aload_0
      //   872: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   875: astore #4
      //   877: aload #4
      //   879: aload #4
      //   881: getfield SpeedgenericView : Landroid/view/View;
      //   884: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   887: pop
      //   888: iload_2
      //   889: invokestatic getStatusFromResource : (I)I
      //   892: putstatic com/roadtrack/onstar/MainActivity.actAlertSpeed : I
      //   895: goto -> 1027
      //   898: aload_0
      //   899: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   902: astore #4
      //   904: aload #4
      //   906: aload #4
      //   908: getfield ParkinggenericProgressBar : Landroid/widget/ProgressBar;
      //   911: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   914: pop
      //   915: aload_0
      //   916: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   919: astore #4
      //   921: aload #4
      //   923: aload #4
      //   925: getfield ParkinggenericView : Landroid/view/View;
      //   928: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   931: pop
      //   932: iload_2
      //   933: invokestatic getStatusFromResource : (I)I
      //   936: putstatic com/roadtrack/onstar/MainActivity.actAlertParking : I
      //   939: goto -> 1027
      //   942: aload_0
      //   943: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   946: astore #4
      //   948: aload #4
      //   950: aload #4
      //   952: getfield HornLigthsgenericProgressBar : Landroid/widget/ProgressBar;
      //   955: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   958: pop
      //   959: aload_0
      //   960: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   963: astore #4
      //   965: aload #4
      //   967: aload #4
      //   969: getfield HornLigthsgenericView : Landroid/view/View;
      //   972: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   975: pop
      //   976: iload_2
      //   977: invokestatic getStatusFromResource : (I)I
      //   980: putstatic com/roadtrack/onstar/MainActivity.actHorn : I
      //   983: goto -> 1027
      //   986: aload_0
      //   987: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   990: astore #4
      //   992: aload #4
      //   994: aload #4
      //   996: getfield FollowmegenericProgressBar : Landroid/widget/ProgressBar;
      //   999: invokestatic access$802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/ProgressBar;)Landroid/widget/ProgressBar;
      //   1002: pop
      //   1003: aload_0
      //   1004: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1007: astore #4
      //   1009: aload #4
      //   1011: aload #4
      //   1013: getfield FollowmegenericView : Landroid/view/View;
      //   1016: invokestatic access$3702 : (Lcom/roadtrack/onstar/MainActivity;Landroid/view/View;)Landroid/view/View;
      //   1019: pop
      //   1020: iload_2
      //   1021: invokestatic getStatusFromResource : (I)I
      //   1024: putstatic com/roadtrack/onstar/MainActivity.actFollowMe : I
      //   1027: aload_0
      //   1028: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1031: invokestatic access$800 : (Lcom/roadtrack/onstar/MainActivity;)Landroid/widget/ProgressBar;
      //   1034: ifnull -> 1100
      //   1037: aload_0
      //   1038: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1041: invokestatic access$800 : (Lcom/roadtrack/onstar/MainActivity;)Landroid/widget/ProgressBar;
      //   1044: bipush #8
      //   1046: invokevirtual setVisibility : (I)V
      //   1049: aload_0
      //   1050: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1053: astore #4
      //   1055: aload #4
      //   1057: aload #4
      //   1059: invokestatic access$3700 : (Lcom/roadtrack/onstar/MainActivity;)Landroid/view/View;
      //   1062: ldc_w 2131296611
      //   1065: invokevirtual findViewById : (I)Landroid/view/View;
      //   1068: checkcast android/widget/TextView
      //   1071: invokestatic access$3802 : (Lcom/roadtrack/onstar/MainActivity;Landroid/widget/TextView;)Landroid/widget/TextView;
      //   1074: pop
      //   1075: aload_0
      //   1076: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1079: invokestatic access$3800 : (Lcom/roadtrack/onstar/MainActivity;)Landroid/widget/TextView;
      //   1082: aconst_null
      //   1083: aload_1
      //   1084: aconst_null
      //   1085: aconst_null
      //   1086: invokevirtual setCompoundDrawablesWithIntrinsicBounds : (Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V
      //   1089: aload_0
      //   1090: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1093: invokestatic access$3800 : (Lcom/roadtrack/onstar/MainActivity;)Landroid/widget/TextView;
      //   1096: iconst_0
      //   1097: invokevirtual setVisibility : (I)V
      //   1100: aload_0
      //   1101: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1104: invokestatic access$8500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   1107: ifnull -> 1124
      //   1110: aload_0
      //   1111: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1114: invokestatic access$8500 : (Lcom/roadtrack/onstar/MainActivity;)Lcom/roadtrack/onstar/VO/CustomActionButton;
      //   1117: iload_2
      //   1118: invokestatic getStatusFromResource : (I)I
      //   1121: invokevirtual showActionStatus : (I)V
      //   1124: new java/lang/StringBuilder
      //   1127: dup
      //   1128: invokespecial <init> : ()V
      //   1131: astore_1
      //   1132: aload_1
      //   1133: aload_0
      //   1134: getfield this$0 : Lcom/roadtrack/onstar/MainActivity;
      //   1137: getfield deviceId : Ljava/lang/String;
      //   1140: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1143: pop
      //   1144: aload_1
      //   1145: ldc ': action: '
      //   1147: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1150: pop
      //   1151: aload_1
      //   1152: aload #5
      //   1154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1157: pop
      //   1158: aload_1
      //   1159: ldc_w ': PALOMA GRIS'
      //   1162: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1165: pop
      //   1166: ldc 'ACTIONS'
      //   1168: ldc ''
      //   1170: aload_1
      //   1171: invokevirtual toString : ()Ljava/lang/String;
      //   1174: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
      //   1177: aload_0
      //   1178: invokestatic nanoTime : ()J
      //   1181: l2d
      //   1182: putfield palomaTime : D
      //   1185: return
    }
  }
  
  class null implements Runnable {
    final MainActivity.Actions this$1;
    
    public void run() {
      MainActivity.this.BannerAlpha(true);
    }
  }
  
  class null implements Runnable {
    final MainActivity.Actions this$1;
    
    public void run() {
      MainActivity.this.act.runOnUiThread(new Runnable() {
            final MainActivity.Actions.null this$2;
            
            public void run() {
              if (MainActivity.this.count1 == null) {
                MainActivity.access$9102(MainActivity.this, (CountDownTimer)new TimerAsyncTAsk(15000L, 1000L, new ICounterCallback() {
                        final MainActivity.Actions.null.null this$3;
                        
                        public void onTickFInish() {
                          MainActivity.Actions actions = this.this$3.this$2.this$1;
                          MainActivity.this.finishMarker(actions.actionId, MainActivity.this.actionButtonL);
                          MainActivity.access$9102(MainActivity.this, null);
                        }
                      }));
                MainActivity.this.count1.start();
              } else if (MainActivity.this.count2 == null) {
                MainActivity.access$9402(MainActivity.this, (CountDownTimer)new TimerAsyncTAsk(15000L, 1000L, new ICounterCallback() {
                        final MainActivity.Actions.null.null this$3;
                        
                        public void onTickFInish() {
                          MainActivity.Actions actions = this.this$3.this$2.this$1;
                          MainActivity.this.finishMarker(actions.actionId, MainActivity.this.actionButtonL);
                          MainActivity.access$9402(MainActivity.this, null);
                        }
                      }));
                MainActivity.this.count2.start();
              } else if (MainActivity.this.count3 == null) {
                MainActivity.access$9502(MainActivity.this, (CountDownTimer)new TimerAsyncTAsk(15000L, 1000L, new ICounterCallback() {
                        final MainActivity.Actions.null.null this$3;
                        
                        public void onTickFInish() {
                          MainActivity.Actions actions = this.this$3.this$2.this$1;
                          MainActivity.this.finishMarker(actions.actionId, MainActivity.this.actionButtonL);
                          MainActivity.access$9502(MainActivity.this, null);
                        }
                      }));
                MainActivity.this.count3.start();
              } 
            }
          });
    }
  }
  
  class null implements Runnable {
    final MainActivity.Actions.null this$2;
    
    public void run() {
      if (MainActivity.this.count1 == null) {
        MainActivity.access$9102(MainActivity.this, (CountDownTimer)new TimerAsyncTAsk(15000L, 1000L, new ICounterCallback() {
                final MainActivity.Actions.null.null this$3;
                
                public void onTickFInish() {
                  MainActivity.Actions actions = this.this$3.this$2.this$1;
                  MainActivity.this.finishMarker(actions.actionId, MainActivity.this.actionButtonL);
                  MainActivity.access$9102(MainActivity.this, null);
                }
              }));
        MainActivity.this.count1.start();
      } else if (MainActivity.this.count2 == null) {
        MainActivity.access$9402(MainActivity.this, (CountDownTimer)new TimerAsyncTAsk(15000L, 1000L, new ICounterCallback() {
                final MainActivity.Actions.null.null this$3;
                
                public void onTickFInish() {
                  MainActivity.Actions actions = this.this$3.this$2.this$1;
                  MainActivity.this.finishMarker(actions.actionId, MainActivity.this.actionButtonL);
                  MainActivity.access$9402(MainActivity.this, null);
                }
              }));
        MainActivity.this.count2.start();
      } else if (MainActivity.this.count3 == null) {
        MainActivity.access$9502(MainActivity.this, (CountDownTimer)new TimerAsyncTAsk(15000L, 1000L, new ICounterCallback() {
                final MainActivity.Actions.null.null this$3;
                
                public void onTickFInish() {
                  MainActivity.Actions actions = this.this$3.this$2.this$1;
                  MainActivity.this.finishMarker(actions.actionId, MainActivity.this.actionButtonL);
                  MainActivity.access$9502(MainActivity.this, null);
                }
              }));
        MainActivity.this.count3.start();
      } 
    }
  }
  
  class null implements ICounterCallback {
    final MainActivity.Actions.null.null this$3;
    
    public void onTickFInish() {
      MainActivity.Actions actions = this.this$3.this$2.this$1;
      MainActivity.this.finishMarker(actions.actionId, MainActivity.this.actionButtonL);
      MainActivity.access$9102(MainActivity.this, null);
    }
  }
  
  class null implements ICounterCallback {
    final MainActivity.Actions.null.null this$3;
    
    public void onTickFInish() {
      MainActivity.Actions actions = this.this$3.this$2.this$1;
      MainActivity.this.finishMarker(actions.actionId, MainActivity.this.actionButtonL);
      MainActivity.access$9402(MainActivity.this, null);
    }
  }
  
  class null implements ICounterCallback {
    final MainActivity.Actions.null.null this$3;
    
    public void onTickFInish() {
      MainActivity.Actions actions = this.this$3.this$2.this$1;
      MainActivity.this.finishMarker(actions.actionId, MainActivity.this.actionButtonL);
      MainActivity.access$9502(MainActivity.this, null);
    }
  }
  
  class null implements Runnable {
    final MainActivity.Actions this$1;
    
    public void run() {
      try {
        Thread.sleep(500L);
      } catch (InterruptedException interruptedException) {
        interruptedException.toString();
      } 
      MainActivity.this.runOnUiThread(new Runnable() {
            final MainActivity.Actions.null this$2;
            
            public void run() {
              OrientationManager.unlockOrientation(MainActivity.this.act);
            }
          });
    }
  }
  
  class null implements Runnable {
    final MainActivity.Actions.null this$2;
    
    public void run() {
      OrientationManager.unlockOrientation(MainActivity.this.act);
    }
  }
  
  class null implements RequestManager.OnPostExecuteListener {
    final MainActivity.Actions this$1;
    
    public void onPostExecuteListener(String param1String, int param1Int) {
      MainActivity.access$9602(MainActivity.this, param1String);
      MainActivity.access$9702(MainActivity.this, param1Int);
    }
  }
  
  class null extends Thread {
    final MainActivity.Actions this$1;
    
    final String val$accountId;
    
    final String val$actionCode;
    
    final String val$deviceId;
    
    final boolean val$isKeepAlive;
    
    final String val$messageId;
    
    final String val$serverIP;
    
    final int val$serverPort;
    
    final int val$socketMaxTime;
    
    final int val$socketTimeOut;
    
    public void run() {
      if (!this.this$1.processFinished) {
        if (isKeepAlive) {
          Utilities.escribeArchivo("MainActivity", "StartSocket: DATA_REMOTE_SERVICES_STARTING", "");
          this.this$1.SocketKeepAlive(actionCode, socketTimeOut, socketMaxTime, accountId, serverIP, serverPort, messageId, deviceId);
        } else if (!actionCode.equals(Enums$Services.ActionNotifications.GetCodeString())) {
          this.this$1.Socket(actionCode, socketTimeOut, socketMaxTime, accountId, serverIP, serverPort, messageId, deviceId);
        } else {
          this.this$1.SocketNotifications(actionCode, socketTimeOut, socketMaxTime, accountId, serverIP, serverPort, messageId, deviceId);
        } 
      } else {
        this.this$1.stopThread(this);
      } 
    }
  }
  
  private class BluetoohListener implements BluetoothProfile.ServiceListener {
    private BluetoohListener(MainActivity this$0) {}
    
    public void onServiceConnected(int param1Int, BluetoothProfile param1BluetoothProfile) {
      if (param1Int == 1) {
        MainActivity.audioProfile = (BluetoothHeadset)param1BluetoothProfile;
        Utilities.escribeArchivo("MainActivity", "HEADSET", "Connected");
      } 
    }
    
    public void onServiceDisconnected(int param1Int) {
      if (param1Int == 1) {
        MainActivity.audioProfile = null;
        Utilities.escribeArchivo("MainActivity", "HEADSET", "Disconected");
      } 
    }
  }
  
  public class DialogReciever extends BroadcastReceiver {
    public DialogReciever(MainActivity this$0) {}
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      Utilities.escribeArchivo("MainActivity", "DialogReciever", "Recieving");
      if (!GlobalMembers.isShowingDialog) {
        param1Context = null;
        if (MainActivity.pendingDialogs.size() > 0) {
          Dialog dialog;
          Iterator<String> iterator = MainActivity.pendingDialogs.keySet().iterator();
          if (iterator.hasNext()) {
            String str = iterator.next();
            dialog = MainActivity.pendingDialogs.get(str);
            MainActivity.pendingDialogs.remove(str);
          } 
          if (dialog != null) {
            dialog.show();
            GlobalMembers.isShowingDialog = true;
          } 
        } 
      } 
    }
  }
  
  private class GPSTrackerListener implements LocationListener {
    public void stopUsingGPS() {
      throw null;
    }
  }
  
  private class GpsAsyncTask extends AsyncTask<Void, Void, Location> {}
  
  private class NotificationReceiver extends BroadcastReceiver {
    final MainActivity this$0;
    
    private NotificationReceiver() {}
    
    public void onReceive(Context param1Context, Intent param1Intent) {
      boolean bool;
      String str1 = param1Intent.getStringExtra("notification_title");
      String str3 = param1Intent.getStringExtra("notification_message");
      String str4 = param1Intent.getStringExtra("notification_source");
      String str2 = param1Intent.getStringExtra("notification_id");
      if (str4.contains("com.whatsapp") || str4.contains("com.kpbird")) {
        bool = true;
      } else {
        bool = false;
      } 
      if (Boolean.valueOf(bool).booleanValue() && GlobalMembers.isConnectionBT) {
        if (str2 != null) {
          int i = Integer.parseInt(str2);
          ((NotificationManager)MainActivity.this.getSystemService("notification")).cancel(i);
        } 
        ManagerNotificationPlatinum managerNotificationPlatinum = MainActivity.getmNPlatinum();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Mensaje recibido de whatsapp, ");
        stringBuilder.append(str1);
        stringBuilder.append(str3);
        managerNotificationPlatinum.sendTextForTTS(stringBuilder.toString());
      } 
    }
  }
  
  public static interface OnCustomEventListener {
    void onEvent();
  }
  
  public class SendCommand extends AsyncTask<Object, Void, String> {
    private String actionId;
    
    private Object[] params;
    
    final MainActivity this$0;
    
    protected String doInBackground(Object... param1VarArgs) {
      String str;
      Boolean bool1;
      this.params = param1VarArgs;
      WsAccess wsAccess = new WsAccess((Context)MainActivity.this);
      this.actionId = (String)param1VarArgs[0];
      boolean bool = this.actionId.equals(Enums$Services.valet.GetCodeString());
      Boolean bool2 = Boolean.valueOf(true);
      if (bool) {
        str = wsAccess.SendCommand(this.actionId, "1", "", 0);
        bool1 = bool2;
      } else if (this.actionId.equals(Enums$Services.Parking.GetCodeString()) || this.actionId.equals(Enums$Services.ParkingUUx.GetCodeString())) {
        String str1 = bool1.SendCommand(this.actionId, "1", "", 0);
        str = str1;
        bool1 = bool2;
        if (!str1.equals("-1")) {
          MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
          MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.ParkinggenericView);
          MainActivity.this.genericCmdStat.setNameButton(MainActivity.this.ParkinggenericActionName);
          MainActivity.this.genericCmdStat.setActionId(this.actionId);
          MainActivity.this.genericCmdStat.setCommandStatus(str1);
          MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
          bool1 = bool2;
          str = str1;
        } 
      } else if (this.actionId.equals(Enums$Services.Speed.GetCodeString()) || this.actionId.equals(Enums$Services.SpeedUUx.GetCodeString())) {
        String str1 = bool1.SendCommand(this.actionId, MainActivity.this.genericSpeedValue, "", 0);
        str = str1;
        bool1 = bool2;
        if (!str1.equals("-1")) {
          MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
          MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.SpeedgenericView);
          MainActivity.this.genericCmdStat.setNameButton(MainActivity.this.SpeedgenericActionName);
          MainActivity.this.genericCmdStat.setActionId(this.actionId);
          MainActivity.this.genericCmdStat.setCommandStatus(str1);
          MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
          str = str1;
          bool1 = bool2;
        } 
      } else if (this.actionId.equals(Enums$Services.SpeedAlways.GetCodeString())) {
        String str1 = bool1.SendCommand(Enums$Services.SpeedAlways.GetCodeString(), MainActivity.this.genericSpeedValue, "", 0);
        str = str1;
        bool1 = bool2;
        if (!str1.equals("-1")) {
          MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
          MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.SpeedgenericView);
          MainActivity.this.genericCmdStat.setNameButton(MainActivity.this.SpeedgenericActionName);
          MainActivity.this.genericCmdStat.setActionId(Enums$Services.SpeedAlways.GetCodeString());
          MainActivity.this.genericCmdStat.setCommandStatus(str1);
          MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
          str = str1;
          bool1 = bool2;
        } 
      } else if (this.actionId.equals(Enums$Services.DoorsLock.GetCodeString())) {
        String str1 = bool1.SendCommand(Enums$Services.DoorsLock.GetCodeString(), "", "", 0);
        str = str1;
        bool1 = bool2;
        if (!str1.equals("-1")) {
          MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
          MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.DoorsLockgenericView);
          MainActivity.this.genericCmdStat.setNameButton(MainActivity.this.DoorsLockgenericActionName);
          MainActivity.this.genericCmdStat.setActionId(Enums$Services.DoorsLock.GetCodeString());
          MainActivity.this.genericCmdStat.setCommandStatus(str1);
          MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
          str = str1;
          bool1 = bool2;
        } 
      } else if (this.actionId.equals(Enums$Services.DoorsUnlock.GetCodeString())) {
        String str1 = bool1.SendCommand(Enums$Services.DoorsUnlock.GetCodeString(), "", "", 0);
        str = str1;
        bool1 = bool2;
        if (!str1.equals("-1")) {
          MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
          MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.DoorsUnlockgenericView);
          MainActivity.this.genericCmdStat.setNameButton(MainActivity.this.DoorsUnlockgenericActionName);
          MainActivity.this.genericCmdStat.setActionId(Enums$Services.DoorsUnlock.GetCodeString());
          MainActivity.this.genericCmdStat.setCommandStatus(str1);
          MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
          str = str1;
          bool1 = bool2;
        } 
      } else if (this.actionId.equals(Enums$Services.Ligths.GetCodeString())) {
        String str1 = bool1.SendCommand(Enums$Services.Ligths.GetCodeString(), "", "", 0);
        str = str1;
        bool1 = bool2;
        if (!str1.equals("-1")) {
          MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
          MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.LigthsgenericView);
          MainActivity.this.genericCmdStat.setNameButton(MainActivity.this.LigthsgenericActionName);
          MainActivity.this.genericCmdStat.setActionId(Enums$Services.Ligths.GetCodeString());
          MainActivity.this.genericCmdStat.setCommandStatus(str1);
          MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
          str = str1;
          bool1 = bool2;
        } 
      } else if (this.actionId.equals(Enums$Services.HornLigths.GetCodeString())) {
        String str1 = bool1.SendCommand(Enums$Services.HornLigths.GetCodeString(), "", "", 0);
        str = str1;
        bool1 = bool2;
        if (!str1.equals("-1")) {
          MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
          MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.HornLigthsgenericView);
          MainActivity.this.genericCmdStat.setNameButton(MainActivity.this.HornLigthsgenericActionName);
          MainActivity.this.genericCmdStat.setActionId(Enums$Services.HornLigths.GetCodeString());
          MainActivity.this.genericCmdStat.setCommandStatus(str1);
          MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
          str = str1;
          bool1 = bool2;
        } 
      } else if (this.actionId.equals(Enums$Services.Horn.GetCodeString()) || this.actionId.equals(Enums$Services.HornF1.GetCodeString())) {
        String str1 = bool1.SendCommand(this.actionId, "", "", 0);
        str = str1;
        bool1 = bool2;
        if (!str1.equals("-1")) {
          MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
          MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.HornLigthsgenericView);
          MainActivity.this.genericCmdStat.setNameButton(MainActivity.this.HornLigthsgenericActionName);
          MainActivity.this.genericCmdStat.setActionId(this.actionId);
          MainActivity.this.genericCmdStat.setCommandStatus(str1);
          MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
          str = str1;
          bool1 = bool2;
        } 
      } else if (this.actionId.equals(Enums$Services.FollowMe.GetCodeString()) || this.actionId.equals(Enums$Services.FollowMeUUx.GetCodeString())) {
        String str1 = bool1.SendCommand(this.actionId, "", "", 0);
        str = str1;
        bool1 = bool2;
        if (!str1.equals("-1")) {
          MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
          MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.FollowmegenericView);
          MainActivity.this.genericCmdStat.setNameButton(MainActivity.this.FollowMegenericActionName);
          MainActivity.this.genericCmdStat.setActionId(this.actionId);
          MainActivity.this.genericCmdStat.setCommandStatus(str1);
          MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
          str = str1;
          bool1 = bool2;
        } 
      } else {
        str = bool1.SendCommand(this.actionId, "", "", 0);
        bool1 = Boolean.valueOf(false);
      } 
      MainActivity.access$1002(MainActivity.this, new DBFunctions(MainActivity.mContext));
      if (MainActivity.this.idInsertControl == -1L) {
        boolean bool3;
        String str5 = Enums$Category.Other.toString();
        String str2 = Enums$Services.GetName(Integer.parseInt(this.actionId));
        String str3 = Utilities.getDateTime(MainActivity.this.deviceId, GlobalMembers.contexGlobal);
        String str1 = Enums$TypeItem.Historical.toString();
        int i = Integer.parseInt(str.trim());
        String str4 = this.actionId;
        String str7 = Utilities.getLastKnownDeviceSelected(MainActivity.this.rtApp, "MainActivity").getDeviceId();
        String str6 = GlobalMembers.userLogged;
        if (str != null && str.length() > 0) {
          bool3 = Integer.valueOf(str).intValue();
        } else {
          bool3 = false;
        } 
        Historical historical = new Historical(str5, str2, str3, str1, "", "", "", i, 0, str4, str7, str6, "", "0", "0", "0", "0", 0, 0.0D, 0.0D, "0", bool3, "");
        MainActivity mainActivity = MainActivity.this;
        MainActivity.access$1202(mainActivity, mainActivity.dbFun.addHistorical(historical));
        if (GlobalMembers.onFollowMeRun.booleanValue()) {
          GlobalMembers.followMeActionStatus = str.trim();
          GlobalMembers.followMeIdOnDB = MainActivity.this.idInsertControl;
        } 
        if (bool1.booleanValue())
          GlobalMembers.listAlerts.add(str.trim()); 
      } 
      return str;
    }
    
    protected void onPostExecute(String param1String) {
      super.onPostExecute(param1String);
      if (!param1String.equals("-1") && !param1String.equals("408")) {
        if (MainActivity.onFollowMe.booleanValue()) {
          MainActivity mainActivity = MainActivity.this;
          MainActivity.access$802(mainActivity, mainActivity.FollowmegenericProgressBar);
          mainActivity = MainActivity.this;
          MainActivity.access$3702(mainActivity, mainActivity.FollowmegenericView);
          if (MainActivity.this.pb != null) {
            MainActivity.this.pb.setVisibility(8);
            mainActivity = MainActivity.this;
            MainActivity.access$3802(mainActivity, (TextView)mainActivity.view.findViewById(2131296611));
            Drawable drawable = Utilities.getDrawableFromConfigList((Context)MainActivity.this, DrawableResourcesVO.palomita_azul_fondo, 2131165626);
            MainActivity.this.imgResponse.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            MainActivity.this.imgResponse.setVisibility(0);
          } 
          Utilities.escribeArchivo("ACTIONS", "FOLLOWME asignImage", "ACTIONS : Imagen: PALOMAGRIS");
          MainActivity.lastFollowMeIdMessage = param1String;
          Utilities.saveLastFollowMeMessageId(MainActivity.mContext, MainActivity.lastFollowMeIdMessage);
        } else {
          (new TcpClientV1.AsyncTaskTcpClientV1(MainActivity.mContext)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { MainActivity.access$000(this.this$0).getAccountID(), Utilities.getLastKnownDeviceSelected(MainActivity.access$000(this.this$0), "MainActivity").getDeviceId(), param1String });
        } 
        MainActivity.firstInWcf = false;
        MainActivity.access$3902(MainActivity.this, Utilities.addSecondsToDate(150));
        if (MainActivity.this.t == null) {
          MainActivity.this.timerGetCommandStatusControler();
        } else {
          MainActivity.this.t.cancel();
          MainActivity.this.t.purge();
          MainActivity.access$4002(MainActivity.this, null);
          MainActivity.this.timerGetCommandStatusControler();
        } 
      } else {
        if (MainActivity.onFollowMe.booleanValue() && GlobalMembers.lastSendCommandIdResponse == 10)
          MainActivity.access$3902(MainActivity.this, Utilities.addSecondsToDate(0)); 
        MainActivity mainActivity = MainActivity.this;
        mainActivity.reSendCommand(this.params, mainActivity.genericView, param1String);
      } 
    }
    
    protected void onPreExecute() {
      super.onPreExecute();
      OrientationManager.lockOrientation(MainActivity.this.act);
    }
  }
  
  private class SendFindMeCommand extends AsyncTask<Void, Void, String> {
    final MainActivity this$0;
    
    private SendFindMeCommand() {}
    
    protected String doInBackground(Void... param1VarArgs) {
      String str;
      WsAccess wsAccess = new WsAccess((Context)MainActivity.this);
      if (!NetUtilities.setUpHttpsConnection(GlobalMembers.URL_WCF, MainActivity.this.getApplicationContext(), 2131623963, GlobalMembers.nameKeystoreServiceWS)) {
        str = "-1";
      } else {
        str = str.SendCommand(Enums$Services.FindMe.GetCodeString(), "", "", 0);
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("resultado: ");
      stringBuilder.append(str);
      stringBuilder.append(" messageId: ");
      stringBuilder.append(0);
      Utilities.escribeArchivo("ACTIONS", "ACTIONS: FINDME SENDCOMMAND:", stringBuilder.toString());
      if (!str.equals("-1")) {
        MainActivity.access$3602(MainActivity.this, new GetCommandStatusVO());
        MainActivity.this.genericCmdStat.setButtonSelected(MainActivity.this.FindmegenericView);
        MainActivity.this.genericCmdStat.setNameButton("Findme");
        MainActivity.this.genericCmdStat.setActionId(Enums$Services.FindMe.GetCodeString());
        MainActivity.this.genericCmdStat.setCommandStatus(str);
        MainActivity.listCmdStat.add(MainActivity.this.genericCmdStat);
      } 
      MainActivity.access$1002(MainActivity.this, new DBFunctions(MainActivity.mContext));
      if (MainActivity.this.idInsertControl == -1L) {
        boolean bool;
        String str4 = Enums$Category.Other.toString();
        String str2 = Enums$Services.GetName(1);
        String str6 = Utilities.getDateTime(MainActivity.this.deviceId, GlobalMembers.contexGlobal);
        String str3 = Enums$TypeItem.Historical.toString();
        int i = Integer.parseInt(str.trim());
        String str5 = Enums$Services.FindMe.GetCodeString();
        String str1 = Utilities.getLastKnownDeviceSelected(MainActivity.this.rtApp, "MainActivity").getDeviceId();
        String str7 = GlobalMembers.userLogged;
        if (str != null && str.length() > 0) {
          bool = Integer.valueOf(str).intValue();
        } else {
          bool = false;
        } 
        Historical historical = new Historical(str4, str2, str6, str3, "", "", "", i, 0, str5, str1, str7, "", "0", "0", "0", "0", 0, 0.0D, 0.0D, "0", bool, "");
        MainActivity mainActivity = MainActivity.this;
        MainActivity.access$1202(mainActivity, mainActivity.dbFun.addHistorical(historical));
        if (MainActivity.onFindMe.booleanValue()) {
          mainActivity = MainActivity.this;
          MainActivity.access$4502(mainActivity, mainActivity.idInsertControl);
        } 
      } 
      return str;
    }
    
    protected void onPostExecute(String param1String) {
      super.onPostExecute(param1String);
      if (!param1String.equals("-1") && !param1String.equals("408")) {
        MainActivity.this.findMeProBar.setVisibility(8);
        MainActivity.isShowingOnFindMe = false;
        MainActivity.access$3902(MainActivity.this, Utilities.addSecondsToDate(150));
        MainActivity.this.ImageResponseFindme.setVisibility(0);
        Utilities.logActions(Utilities.getLastKnownDeviceSelected(MainActivity.this.rtApp, "MainActivity").getName(), "1", "1");
        Utilities.escribeArchivo("ACTIONS", "FINDME asignImage", "ACTIONS : Imagen: PALOMAGRIS");
        if (MainActivity.this.t == null) {
          MainActivity.this.timerGetCommandStatusControler();
        } else {
          MainActivity.this.t.cancel();
          MainActivity.this.t.purge();
          MainActivity.access$4002(MainActivity.this, null);
          MainActivity.this.timerGetCommandStatusControler();
        } 
      } else {
        MainActivity mainActivity = MainActivity.this;
        View view = mainActivity.FindmegenericView;
        mainActivity.reSendCommand(new Object[] { "1" }, view, param1String);
      } 
      if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue() && !MainActivity.onLigths.booleanValue() && !MainActivity.onHornLights.booleanValue() && !MainActivity.onCloseDoors.booleanValue() && !MainActivity.onOpenDoors.booleanValue() && !MainActivity.onDisarmPINCODE.booleanValue() && !MainActivity.onAlertParking.booleanValue() && !MainActivity.onAlertSpeed.booleanValue() && !MainActivity.onAlertValet.booleanValue() && !MainActivity.onNotification.booleanValue() && !MainActivity.onHorn.booleanValue()) {
        OrientationManager.unlockOrientation(MainActivity.this.act);
        MainActivity.this.setWatterMarks(false, new Enums$Services[0]);
        MainActivity.this.BannerAlpha(false);
        MainActivity.this.lockRightDrawer(false);
      } 
    }
    
    protected void onPreExecute() {
      super.onPreExecute();
      GlobalMembers.findmeCode = 0;
      GlobalMembers.ifollowMeType = 0;
      GlobalMembers.followMeAccesByAction = Boolean.valueOf(false);
      OrientationManager.lockOrientation(MainActivity.this.act);
      if (MainActivity.this.findMeProBar != null)
        MainActivity.this.findMeProBar.setVisibility(0); 
    }
  }
  
  private class TimerThread implements Runnable {
    final MainActivity this$0;
    
    private TimerThread() {}
    
    public void run() {
      byte b = 0;
      while (true) {
        boolean bool1;
        boolean bool2 = true;
        if (b < 30) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        if (MainActivity.iPreviousCallIndex != 0)
          bool2 = false; 
        if ((bool1 & bool2) != 0) {
          try {
            Thread.sleep(1000L);
            b++;
          } catch (InterruptedException interruptedException) {
            Utilities.escribeArchivo("MainActivity", "Error: timer", interruptedException.toString());
          } 
          continue;
        } 
        MainActivity.this.runOnUiThread(new Runnable(this) {
              public void run() {
                Thread thread = Thread.currentThread();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(null.class.getSimpleName());
                stringBuilder.append(": ");
                stringBuilder.append(Thread.currentThread().getName());
                thread.setName(stringBuilder.toString());
                MainActivity.bEsperandoP8 = false;
                GlobalMembers.bStartingCall = false;
              }
            });
        return;
      } 
    }
  }
  
  class null implements Runnable {
    null(MainActivity this$0) {}
    
    public void run() {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(null.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      MainActivity.bEsperandoP8 = false;
      GlobalMembers.bStartingCall = false;
    }
  }
  
  private class TimerThreadXseconds implements Runnable {
    final MainActivity this$0;
    
    private TimerThreadXseconds() {}
    
    public void run() {
      MainActivity.access$8002(MainActivity.this, true);
      byte b = 0;
      while (true) {
        boolean bool;
        if (b < MainActivity.this.iSegundos) {
          bool = true;
        } else {
          bool = false;
        } 
        if (bool & MainActivity.this.bEsperandoComando) {
          try {
            Thread.sleep(1000L);
            b++;
          } catch (InterruptedException interruptedException) {
            Utilities.escribeArchivo("MainActivity", "Error: timer", interruptedException.toString());
          } 
          continue;
        } 
        MainActivity.this.runOnUiThread(new Runnable() {
              final MainActivity.TimerThreadXseconds this$1;
              
              public void run() {
                Thread thread = Thread.currentThread();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(null.class.getSimpleName());
                stringBuilder.append(": ");
                stringBuilder.append(Thread.currentThread().getName());
                thread.setName(stringBuilder.toString());
                if (MainActivity.this.bEsperandoComando) {
                  MainActivity.this.SendCommand85();
                  MainActivity.access$8002(MainActivity.this, false);
                } 
              }
            });
        return;
      } 
    }
  }
  
  class null implements Runnable {
    final MainActivity.TimerThreadXseconds this$1;
    
    public void run() {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(null.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      if (MainActivity.this.bEsperandoComando) {
        MainActivity.this.SendCommand85();
        MainActivity.access$8002(MainActivity.this, false);
      } 
    }
  }
  
  private class registerGCM extends AsyncTask<String, Void, String> {
    private Context context;
    
    final MainActivity this$0;
    
    public registerGCM(Context param1Context) {
      this.context = param1Context;
    }
    
    protected String doInBackground(String... param1VarArgs) {
      pushManager pushManager = new pushManager((Activity)this.context);
      MainActivity mainActivity = MainActivity.this;
      MainActivity.access$002(mainActivity, (onstarApplication)mainActivity.getApplicationContext());
      String str2 = MainActivity.this.rtApp.getLocatorUserId();
      String str3 = Utilities.DeviceUuidFactory(this.context);
      String str1 = PreferenceRT.GetValuePreference(GlobalMembers.deviceName, "", onstarApplication.getContext());
      pushManager.deviceRegister("3434", MainActivity.this.token, str2, str3, String.valueOf(13), "1", str1, false);
      return null;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */