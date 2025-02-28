package com.roadtrack.onstar.BO;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Environment;
import com.roadtrack.onstar.BTNavigation;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.CustomActionButton;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import com.roadtrack.onstar.VO.FollowMePointsVO;
import com.roadtrack.onstar.VO.Historical;
import com.roadtrack.onstar.VO.MarkerOrFavoritePointVO;
import com.roadtrack.onstar.VO.OtaResponsePlatinumVersionVO;
import com.roadtrack.onstar.VO.PushAlertsVO;
import com.roadtrack.onstar.VO.TbtDataVO;
import com.roadtrack.onstar.VO.TbtManeuverInfoVO;
import com.roadtrack.onstar.VO.TbtRoutePrevioInfoVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.VersionsVO;
import com.roadtrack.onstar.VO.commonQueue;
import com.roadtrack.onstar.adapter.optionMenuPlatinumAdapter;
import com.roadtrack.onstar.entities.OptionMenu;
import com.roadtrack.onstar.entities.displaymenu;
import com.roadtrack.onstar.menuPlatinum;
import com.roadtrack.onstar.menuplat;
import com.roadtrack.onstar.nav.routing.LocationInfo;
import com.roadtrack.onstar.nav.routing.Point;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class GlobalMembers {
  public static int ACK_COUNTER;
  
  public static String ACTIONS_HISTORICAL;
  
  public static final String AUTOCOMPLETE_URL;
  
  public static int AVAILABLE_WINDOW_SPACES;
  
  public static BTNavigation.Iupdatetitle BTNavListenerTitle;
  
  public static int BT_P8I_DISCRETE;
  
  public static int BT_P8_GMLAN;
  
  public static String CHANNEL_ID;
  
  public static int CHANNEL_IMPORTANCE;
  
  public static CharSequence CHANNEL_NAME;
  
  public static String COMPLETE_HISTORICAL;
  
  public static String CURRENT_NAME;
  
  public static int CodeVersion;
  
  private static final String DATA_PATH;
  
  public static String DOMAIN_CHEVROLET;
  
  public static CustomActionButton DTCCustomActionButton;
  
  public static SimpleDateFormat DateFormat;
  
  public static SimpleDateFormat DateTimeFormat;
  
  public static boolean DeviceConectedReceiverStatus;
  
  public static int EstadoAppGlobal;
  
  public static String FORGET_ACCESS;
  
  public static List<FavoritesHistoryVO> FavoriteHistoricalList;
  
  public static final String GOOGLE_API_KEY;
  
  public static int HTTP_TRANSPORT_TIMEOUT;
  
  public static int HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND;
  
  public static int HTTP_TRANSPORT_TIMEOUT_ACTIONS_SENDCOMMAND;
  
  public static int HTTP_TRANSPORT_TIMEOUT_WCF;
  
  public static boolean IsNewPayment;
  
  public static boolean LASTFILE;
  
  public static menuplat.CloseMenuPlat ListenerCloseMenu;
  
  public static menuplat.RefreshUIListener ListenerRefreshUI;
  
  public static BTNavigation.IupdateguiListener ListenerUUI;
  
  public static menuplat.UpdateTitle ListenerUpdateTitle;
  
  public static menuPlatinum.On555Listener Listenerg;
  
  public static boolean MAP_UPDATE_CONFIRMED;
  
  public static Boolean MAP_UPDATE_DEBUG;
  
  public static Boolean MAP_UPDATE_ON_PARTS;
  
  public static int MAP_UPDATE_SOCKETS;
  
  public static int MAP_UPDATE_VERSION;
  
  public static int MAP_UPDATE_WITH_PUSH;
  
  public static String MARKET_ADDRESS;
  
  public static String MoipAvailableTicket;
  
  public static String MoipCardsManagementSuffix;
  
  public static String MoipCreditCard;
  
  public static String MoipGetPlansSuffix;
  
  public static String MoipHistorySuffix;
  
  public static String MoipSecuritySuffix;
  
  public static String MoipSuscriptionSuffix;
  
  public static String MoipTicketHistory;
  
  public static String NAMESPACE_WCF;
  
  public static String NAMESPACE_WCF_RT;
  
  public static String NAVIGATION_HISTORICAL;
  
  public static final String NEARBY_URL;
  
  public static final Boolean NoneBoolean;
  
  private static final String PACKAGE_NAME;
  
  private static final String[] PACKAGE_NAMES;
  
  public static String PIDButton;
  
  public static final String PLACES_DETAILS_URL;
  
  public static final String PLACES_NERBY_SEARCH_URL;
  
  public static final String PLACES_SEARCH_URL;
  
  public static final String PLACES_TEXT_SEARCH_URL;
  
  public static String PhoneSOS;
  
  public static String PhoneSchedule;
  
  public static int PointsNumber;
  
  public static String RegistrationWeb;
  
  public static boolean SLIDING_WINDOW_PROCESS_ACTIVE;
  
  public static int SLIDING_WINDOW_SIZE;
  
  public static int SOCKET_READ_TIMEOUT;
  
  public static int SOCKET_TIMEOUT;
  
  public static String STORE_ADDRESS;
  
  public static String SVN_REVISION_NUMBER;
  
  public static final String Sender_IDGCM;
  
  public static int SessionTimeout;
  
  public static String TermConditionsData;
  
  public static SimpleDateFormat TimeFormat;
  
  public static boolean ToExitApp;
  
  public static String URLFileUpgrade;
  
  public static String URLGCMServer;
  
  public static String URLMapsDownload;
  
  public static String URLMoipPrefix;
  
  public static String URLSync;
  
  public static String URL_ANDROID_AUTO;
  
  public static String URL_ChangeEmail;
  
  public static String URL_CreateAccount;
  
  public static String URL_DTC_ChevroletCOAgendar;
  
  public static String URL_DTC_LocatorWebApiAgendamiento;
  
  public static String URL_DTC_LocatorWebApiLogin;
  
  public static String URL_DTC_LocatorWebApp;
  
  public static String URL_Historical;
  
  public static String URL_PID_CarInfo;
  
  public static String URL_Renewal;
  
  public static String URL_UNREGISTER_DEVICE;
  
  public static String URL_WCF;
  
  public static final String WEB_PROTOCOL;
  
  public static final String WEB_PROTOCOL_SEC;
  
  public static String WebviewHistoryPrefix;
  
  public static String WebviewHistorySuffix;
  
  public static String ZIP_TYPE;
  
  public static String actionSocketCode;
  
  public static boolean activeSession;
  
  public static boolean activeTutorial;
  
  public static optionMenuPlatinumAdapter adapternav;
  
  public static String appSDPath;
  
  public static final String auxCity;
  
  public static boolean bActivityCallRunning;
  
  public static boolean bFinalizaronIntentos;
  
  public static boolean bLlamadaFinalizada;
  
  public static boolean bMostrandoMenuHMI;
  
  public static boolean bRequestForSendNavigation;
  
  public static boolean bRespuestaNoRecibida;
  
  public static boolean bSeEnvioRutaAP8;
  
  public static boolean bServicioSocketsCorriendo;
  
  public static boolean bStartingCall;
  
  public static boolean bWCFFInishLogin;
  
  public static boolean bmenuitemport;
  
  public static boolean bmenuitempressed;
  
  public static boolean boolactualizar;
  
  public static boolean boolorientacion;
  
  public static boolean bshowmenuland;
  
  public static int btnpress;
  
  public static String codeCountry;
  
  public static Context contexGlobal;
  
  public static final String countryName;
  
  public static Context ctxBase;
  
  public static String currentActivity;
  
  public static String customer_web_site;
  
  public static boolean deleteAll;
  
  public static boolean deleteIndividual;
  
  public static LocationInfo destinationFromMap;
  
  public static String deviceName;
  
  public static String deviceType;
  
  public static String deviceTypeP7;
  
  public static String deviceTypeP8;
  
  public static boolean downloadComplete;
  
  public static String downloadResponse;
  
  public static String emedecinco;
  
  public static boolean enableTTS;
  
  public static String entidadFederativa;
  
  public static MainActivity.OnCustomEventListener eventListenerActions;
  
  public static MainActivity.OnCustomEventListener eventListenerOpenMap;
  
  public static FavoritesHistoryVO favSelected;
  
  public static ArrayList<Integer> favoritesSelected;
  
  public static String fileMapsDownload;
  
  public static menuplat.On555ListenerFinalizar finalizarObtenerDatosNavegacionHMI;
  
  public static boolean findMeGLIM;
  
  public static int findmeCode;
  
  public static boolean flagShowWebViews;
  
  public static Boolean followMeAccesByAction;
  
  public static String followMeActionStatus;
  
  public static FollowMePointsVO followMeActivatedPoint;
  
  public static ArrayList<FollowMePointsVO> followMeArrayListPoints;
  
  public static long followMeIdOnDB;
  
  public static String followMeProcessDeviceId;
  
  public static boolean forceNavigationLeftDrawer;
  
  public static String fragmentName;
  
  public static Activity globalActivity;
  
  public static DownloadManager globalDownload;
  
  public static String googleSearchTitleToFavorite;
  
  public static LinkedHashMap<String, String> hashmapCerts;
  
  public static LinkedHashMap<String, String> hashmapParams = new LinkedHashMap<String, String>();
  
  public static LinkedHashMap<String, String> hashmapStrings = new LinkedHashMap<String, String>();
  
  public static boolean hideButtonNavigation;
  
  public static SimpleDateFormat historicalsdf;
  
  public static int iAppNavigationStatus;
  
  public static int iRutaRecibida;
  
  public static boolean iconFav;
  
  public static int ifollowMeType;
  
  public static menuplat.On555ListenerIniciar iniciarObtenerDatosNavegacionHMI;
  
  public static long initFollowMeTime;
  
  public static int intmenuplat;
  
  public static boolean isActionRenewalFromMain;
  
  public static boolean isAppRunning;
  
  public static boolean isConnectionBT;
  
  public static boolean isDownloadingMap;
  
  public static final boolean isF1;
  
  public static boolean isFromShareFavActivity;
  
  public static boolean isFromShareMarkerActivity;
  
  public static boolean isHMIEnabled;
  
  public static boolean isLastMapUpdateFile;
  
  public static boolean isMAPUpdateEnabled;
  
  public static boolean isMapZipped;
  
  public static boolean isMarker;
  
  public static boolean isShowingDialog;
  
  public static boolean isSockeActived;
  
  public static boolean isTbtWorking;
  
  public static boolean isUUxAvailable;
  
  public static boolean isWUCRunningAffterSetPlanes;
  
  public static final String langGooglePlaces;
  
  public static Double[] lastFollowMePoint;
  
  public static String[] lastGetLastIncomingMessageFindMe;
  
  public static String lastMapUpdateFile;
  
  public static String lastNavParamsFindMe;
  
  public static int lastSendCommandIdMessage;
  
  public static int lastSendCommandIdResponse;
  
  public static final List<String> listAlerts;
  
  public static int locatorErrorGetStatus_1;
  
  public static List<UserDevicesVO> mDeviceUserList;
  
  public static List<OptionMenu> mHistoryList;
  
  public static List<Historical> mTodayList;
  
  public static Activity mainActivity;
  
  public static String mapFileName;
  
  public static String mapUpdateSocketAction;
  
  public static MarkerOrFavoritePointVO markerOrFavoritePointVO;
  
  public static displaymenu menuPlatinum;
  
  public static int menu_level;
  
  public static String messsageIdGlobal;
  
  public static int minDistance;
  
  public static ArrayList<PushAlertsVO> mixedNotificationsPoints;
  
  public static int mmsg85;
  
  public static boolean moipCapLine;
  
  public static MainActivity.OnCustomEventListener myListener;
  
  public static MainActivity.OnCustomEventListener myListenerDownComplete;
  
  public static boolean myPlan;
  
  public static String nameDevice;
  
  public static String nameKeystoreMoip;
  
  public static String nameKeystoreService;
  
  public static String nameKeystoreServiceDTC;
  
  public static String nameKeystoreServiceWS;
  
  public static List<FavoritesHistoryVO> navigationHistoricalList;
  
  public static Boolean needFollowMeReview;
  
  public static boolean needToOpenMap;
  
  public static boolean newMain;
  
  public static boolean newMaps;
  
  public static int notificaciones;
  
  public static int notificacionesAgendamiento;
  
  public static int notificacionesDTC;
  
  public static boolean notificationSpinner;
  
  public static int numNavegacion;
  
  public static int numVerFragment;
  
  public static Object objSendFavoritesMessage;
  
  public static Boolean onFollowMeRun;
  
  public static boolean onfollowmeActivated;
  
  public static boolean openMap;
  
  public static String pathCloudmadeMap;
  
  public static int platinumBTVersion;
  
  public static boolean processMessageId;
  
  public static long pushNumber;
  
  public static SimpleDateFormat pushVOsdf;
  
  public static boolean redPoint;
  
  public static String renewalPhone;
  
  public static boolean renewalfeatures;
  
  public static boolean responseLoginOK;
  
  public static String servicePlanRenewalPhone;
  
  public static boolean showButtonNavigation;
  
  public static boolean showLog;
  
  public static boolean showdialogfollow;
  
  public static String str555tmp;
  
  public static String strAccountID;
  
  public static String strETA;
  
  public static String strIPSocketGetCommand;
  
  public static String strMensaje;
  
  public static String strPassword;
  
  public static final String strPuertoA;
  
  public static String strUrl1;
  
  public static String strUrl2;
  
  public static String strUser;
  
  public static String strViaRoute;
  
  public static String stringToAccess;
  
  public static String stringToAccessUser;
  
  public static String[] tbtDataChunk;
  
  public static String[] tbtDataChunkAck;
  
  public static TbtDataVO tbtListData;
  
  public static TbtManeuverInfoVO tbtManeouverInfo;
  
  public static String[] tbtManouverInfoHead;
  
  public static int tbtMuteStatus;
  
  public static ArrayList<TbtRoutePrevioInfoVO> tbtRoutePreviewInfo;
  
  public static LinkedHashMap<String, LinkedHashMap<String, Integer>> tempBottomActions;
  
  public static LinkedHashMap<String, LinkedHashMap<String, Integer>> tempMainActionsWakeUpCar;
  
  public static boolean tempSocketResponse;
  
  public static String token;
  
  public static int totalNotificaciones;
  
  public static boolean unRegisterDevice;
  
  public static Enums.UnitType unitTypePlatinum;
  
  public static String updateStatusResponse;
  
  public static String userLogged;
  
  public static boolean usingPush;
  
  public static String valorPO;
  
  public static String valuePoi1;
  
  public static String valuePoi2;
  
  public static Vector<String> vecWaypoints;
  
  public static List<String> vehicleList;
  
  public static String version;
  
  public static String versionStatus;
  
  public static LocationInfo[] viaInfoFromMap;
  
  public static commonQueue waitQueueResponceMessages;
  
  public static boolean wakeUpCar;
  
  public static String[] ws_responceFindMe;
  
  static {
    new LinkedHashMap<Object, Object>();
    hashmapCerts = new LinkedHashMap<String, String>();
    nameKeystoreService = "services_all";
    nameKeystoreServiceDTC = "services_dtc";
    nameKeystoreServiceWS = "services_only";
    nameKeystoreMoip = "moip_preprod";
    Boolean bool = Boolean.valueOf(false);
    unRegisterDevice = false;
    CodeVersion = 6001;
    showLog = false;
    IsNewPayment = false;
    codeCountry = null;
    SVN_REVISION_NUMBER = "170120221409";
    notificaciones = 0;
    notificacionesDTC = 0;
    notificacionesAgendamiento = 0;
    totalNotificaciones = 0;
    COMPLETE_HISTORICAL = "complete_historical";
    ACTIONS_HISTORICAL = "actions_historical";
    NAVIGATION_HISTORICAL = "navigation_historical";
    deleteAll = false;
    deleteIndividual = false;
    BT_P8_GMLAN = 0;
    BT_P8I_DISCRETE = 1;
    ZIP_TYPE = "ZIP";
    MAP_UPDATE_CONFIRMED = false;
    downloadComplete = false;
    MAP_UPDATE_ON_PARTS = bool;
    MAP_UPDATE_DEBUG = bool;
    CURRENT_NAME = "";
    SLIDING_WINDOW_SIZE = 0;
    AVAILABLE_WINDOW_SPACES = 0;
    ACK_COUNTER = 0;
    MAP_UPDATE_WITH_PUSH = 0;
    MAP_UPDATE_SOCKETS = 1;
    MAP_UPDATE_VERSION = MAP_UPDATE_SOCKETS;
    entidadFederativa = "";
    SOCKET_TIMEOUT = 10000;
    SOCKET_READ_TIMEOUT = 15000;
    HTTP_TRANSPORT_TIMEOUT_WCF = 30000;
    HTTP_TRANSPORT_TIMEOUT = 10000;
    HTTP_TRANSPORT_TIMEOUT_ACTIONS_SENDCOMMAND = 30000;
    HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND = 30000;
    new LinkedHashMap<Object, Object>();
    lastSendCommandIdResponse = 0;
    responseLoginOK = false;
    isLastMapUpdateFile = false;
    lastMapUpdateFile = "";
    isShowingDialog = false;
    emedecinco = Utilities.Decrypt("0FL23ES6xKywtnQ1E8lOQw==");
    Sender_IDGCM = Utilities.Decrypt("CsoSMJhETi4J7wIdXXu1QA==");
    WEB_PROTOCOL = Utilities.Decrypt("drvJ7HYGY9rZbwVANCBOlA==");
    WEB_PROTOCOL_SEC = Utilities.Decrypt("s/6Xe4axsAd8HrNLz12MQA==");
    strPuertoA = Utilities.Decrypt("l9lQ3xZaQnXN0fS/y8m6cw==");
    mapUpdateSocketAction = Utilities.Decrypt("+Yo7pSEratujqPxjDVDHqA==");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL);
    stringBuilder.append(Utilities.Decrypt("2cmOYC1D0MBXB5zc8gAXTA=="));
    NAMESPACE_WCF = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL);
    stringBuilder.append(Utilities.Decrypt("Cri/1bSP6rn8qVCamgsgRw=="));
    NAMESPACE_WCF_RT = stringBuilder.toString();
    Utilities.Decrypt("ZGoniPM+EZO2l2Hj6xuA7A==");
    Utilities.Decrypt("FGCkLNaQC41ac2JavdSBRGu1BTV/l/vlyRxJ+SXqN+TFbqgToDInJtl+ULgbKl3M");
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL);
    stringBuilder.append(Utilities.Decrypt("0pwqdeeVUKI7LWx5zpeiKsTvuaED9uPfCCgigSR+mQhojfg/vvrYxuunFwrwIuJe"));
    stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL);
    stringBuilder.append(Utilities.Decrypt("0pwqdeeVUKI7LWx5zpeiKsTvuaED9uPfCCgigSR+mQhojfg/vvrYxuunFwrwIuJe"));
    stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL);
    stringBuilder.append(Utilities.Decrypt("snn7EXAVwibzrNb++bV/EcxJMe8g/GTD2XM3+9uZ3iUGC2Ctrx7qQTcYns1+bRww"));
    stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL);
    stringBuilder.append(Utilities.Decrypt("Xe5YyjOjk5GcFBMSFGAchoSWVdoyyoOVXAuslIzyeN7lqHvaVcpMdGfuIIBJif61"));
    stringBuilder.toString();
    GOOGLE_API_KEY = UtilitiesFile.ReadCfg("MAPAACCESS");
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL_SEC);
    stringBuilder.append(Utilities.Decrypt("IkPCsUwioQ2mwYdmC65u3TEq6nGtztXgXmVsYjKWoTLVDNl2tb9ZE7HqbxHs8TOr"));
    PLACES_SEARCH_URL = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL_SEC);
    stringBuilder.append(Utilities.Decrypt("IkPCsUwioQ2mwYdmC65u3TEq6nGtztXgXmVsYjKWoTJ5UShJHcnJ01Sjj4n19Pw2s5FRoI0NLTYxp87sIK7y+Q=="));
    PLACES_TEXT_SEARCH_URL = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL_SEC);
    stringBuilder.append(Utilities.Decrypt("IkPCsUwioQ2mwYdmC65u3TEq6nGtztXgXmVsYjKWoTLlBBOwV0J7MV7GTE0l2d3xwUAy5HbjFVxPMQMp0hNbew=="));
    PLACES_NERBY_SEARCH_URL = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL_SEC);
    stringBuilder.append(Utilities.Decrypt("IkPCsUwioQ2mwYdmC65u3TEq6nGtztXgXmVsYjKWoTKmZ2nImXxsX8KR5eewHJHV/sBZL1Z+8rNPgn2+HgxxqA=="));
    AUTOCOMPLETE_URL = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL_SEC);
    stringBuilder.append(Utilities.Decrypt("IkPCsUwioQ2mwYdmC65u3TEq6nGtztXgXmVsYjKWoTLlBBOwV0J7MV7GTE0l2d3xwUAy5HbjFVxPMQMp0hNbew=="));
    NEARBY_URL = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL_SEC);
    stringBuilder.append(Utilities.Decrypt("IkPCsUwioQ2mwYdmC65u3TEq6nGtztXgXmVsYjKWoTKrUlP76BOZB111HmrZfXzP6hl7JC64QE+keh4qWsTbEg=="));
    PLACES_DETAILS_URL = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL_SEC);
    stringBuilder.append(Utilities.Decrypt("WOXM/Gy/iTittS32NPUL39TuFMvLIsCwGn60abytCsc="));
    stringBuilder.toString();
    Utilities.Decrypt("ZbWJaRrjFmQyfePm71Axzw==");
    stringBuilder = new StringBuilder();
    stringBuilder.append(WEB_PROTOCOL_SEC);
    stringBuilder.append(Utilities.Decrypt("v79a77gweF5mZozigkUO+pYKgCzoIeoX6pvHlFLk73yqhhNfoB/81rac1TpaADcY"));
    stringBuilder.toString();
    PACKAGE_NAMES = GlobalMembers.class.getPackage().getName().toString().split("\\.");
    stringBuilder = new StringBuilder();
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
    stringBuilder.append(Utilities.Decrypt("gCtyLBBSv7usq9WQNzpQDg=="));
    appSDPath = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(appSDPath);
    stringBuilder.append(Utilities.Decrypt("2OlUZA2aC0j4f7wqIbClqg=="));
    pathCloudmadeMap = stringBuilder.toString();
    auxCity = null;
    countryName = UtilitiesFile.ReadCfg("countryName");
    isF1 = Boolean.valueOf(Utilities.getParamsFromConfigList("isF1")).booleanValue();
    langGooglePlaces = UtilitiesFile.ReadCfg("langGooglePlaces");
    DOMAIN_CHEVROLET = Utilities.getParamsFromConfigList("DOMAIN_CHEVROLET");
    strUrl1 = Utilities.getParamsFromConfigList("strUrl1");
    strUrl2 = Utilities.getParamsFromConfigList("strUrl2");
    showButtonNavigation = Boolean.valueOf(Utilities.getParamsFromConfigList("showButtonNavigation")).booleanValue();
    hideButtonNavigation = Boolean.valueOf(Utilities.getParamsFromConfigList("hideButtonNavigation")).booleanValue();
    forceNavigationLeftDrawer = Boolean.valueOf(Utilities.getParamsFromConfigList("forceNavigationLeftDrawer")).booleanValue();
    URL_DTC_LocatorWebApiLogin = Utilities.getParamsFromConfigList("URL_DTC_LocatorWebApiLogin");
    URL_DTC_LocatorWebApp = Utilities.getParamsFromConfigList("URL_DTC_LocatorWebApp");
    URL_DTC_LocatorWebApiAgendamiento = Utilities.getParamsFromConfigList("URL_DTC_LocatorWebApiAgendamiento");
    URL_PID_CarInfo = Utilities.getParamsFromConfigList("URL_PID_CarInfo");
    URL_DTC_ChevroletCOAgendar = Utilities.getParamsFromConfigList("URL_DTC_ChevroletCOAgendar");
    URL_WCF = Utilities.getParamsFromConfigList("URL_WCF");
    Utilities.getParamsFromConfigList("URL_ServiceConfigFiles");
    Utilities.getParamsFromConfigList("URL_DownloadFiles");
    URL_CreateAccount = Utilities.getParamsFromConfigList("URL_CreateAccount");
    strIPSocketGetCommand = Utilities.getParamsFromConfigList("strIPSocketGetCommand");
    URLSync = Utilities.getParamsFromConfigList("URLSync");
    URLGCMServer = Utilities.getParamsFromConfigList("URLGCMServer");
    URL_ANDROID_AUTO = Utilities.getParamsFromConfigList("URL_ANDROID_AUTO");
    URL_UNREGISTER_DEVICE = Utilities.getParamsFromConfigList("URL_UNREGISTER_DEVICE");
    Utilities.getParamsFromConfigList("UrlActivateNotificationEmail");
    URLMapsDownload = Utilities.getParamsFromConfigList("URLMapsDownload");
    fileMapsDownload = Utilities.getParamsFromConfigList("fileMapsDownload");
    Utilities.getParamsFromConfigList("URLVersion");
    URLFileUpgrade = Utilities.getParamsFromConfigList("URLFileUpgrade");
    Utilities.getParamsFromConfigList("URLGoogleSearchCounter");
    Utilities.getParamsFromConfigList("URL_TERMS_AND_CONDITIONS");
    customer_web_site = Utilities.getParamsFromConfigList("customer_web_site");
    Utilities.getParamsFromConfigList("diagnosticPID_web_site");
    Utilities.getParamsFromConfigList("schedule_web_site");
    PIDButton = Utilities.getParamsFromConfigList("pid");
    FORGET_ACCESS = Utilities.getParamsFromConfigList("FORGET_ACCESS");
    STORE_ADDRESS = Utilities.getParamsFromConfigList("STORE_ADDRESS");
    MARKET_ADDRESS = UtilitiesFile.ReadCfg("MARKET_ADDRESS");
    renewalPhone = Utilities.getParamsFromConfigList("renewalPhone");
    PhoneSOS = Utilities.getParamsFromConfigList("PhoneSOS");
    PhoneSchedule = Utilities.getParamsFromConfigList("PhoneSchedule");
    Integer.valueOf(Utilities.getParamsFromConfigList("phoneLenMin")).intValue();
    Integer.valueOf(Utilities.getParamsFromConfigList("phoneLenMax")).intValue();
    Boolean.valueOf(Utilities.getParamsFromConfigList("isTrafficAvailable")).booleanValue();
    isHMIEnabled = Boolean.valueOf(Utilities.getParamsFromConfigList("isHMIEnabled")).booleanValue();
    isMAPUpdateEnabled = Boolean.valueOf(Utilities.getParamsFromConfigList("isMAPUpdateEnabled")).booleanValue();
    isMapZipped = Boolean.valueOf(Utilities.getParamsFromConfigList("isMapZipped")).booleanValue();
    myPlan = Boolean.valueOf(Utilities.getParamsFromConfigList("myPlan")).booleanValue();
    isUUxAvailable = Boolean.valueOf(Utilities.getParamsFromConfigList("isUUxAvailable")).booleanValue();
    redPoint = Boolean.valueOf(Utilities.getParamsFromConfigList("redPoint")).booleanValue();
    URLMoipPrefix = Utilities.getParamsFromConfigList("URLMoipPrefix");
    MoipSecuritySuffix = Utilities.getParamsFromConfigList("MoipSecuritySuffix");
    Utilities.getParamsFromConfigList("MoipOneShotSuffix");
    MoipHistorySuffix = Utilities.getParamsFromConfigList("MoipHistorySuffix");
    MoipCreditCard = Utilities.getParamsFromConfigList("MoipCreditCard");
    WebviewHistoryPrefix = Utilities.getParamsFromConfigList("WebviewHistoryPrefix");
    WebviewHistorySuffix = Utilities.getParamsFromConfigList("WebviewHistorySuffix");
    MoipGetPlansSuffix = Utilities.getParamsFromConfigList("MoipGetPlansSuffix");
    renewalfeatures = Boolean.valueOf(Utilities.getParamsFromConfigList("renewalfeatures")).booleanValue();
    MoipCardsManagementSuffix = Utilities.getParamsFromConfigList("MoipCardsManagementSuffix");
    RegistrationWeb = Utilities.getParamsFromConfigList("RegistrationWeb");
    MoipSuscriptionSuffix = Utilities.getParamsFromConfigList("MoipSuscriptionSuffix");
    servicePlanRenewalPhone = Utilities.getParamsFromConfigList("servicePlanRenewalPhone");
    Utilities.getParamsFromConfigList("actionsAnalytics");
    URL_ChangeEmail = Utilities.getParamsFromConfigList("URL_ChangeEmail");
    URL_Renewal = Utilities.getParamsFromConfigList("URL_Renewal");
    Utilities.getParamsFromConfigList("URL_CreditCart");
    URL_Historical = Utilities.getParamsFromConfigList("URL_Historical");
    Utilities.getParamsFromConfigList("URL_MyPlan");
    notificationSpinner = Boolean.valueOf(Utilities.getParamsFromConfigList("notificationSpinner")).booleanValue();
    Boolean.valueOf(Utilities.getParamsFromConfigList("isFirebaseEnabled")).booleanValue();
    Utilities.getParamsFromConfigList("urlONSTARCLUB");
    moipCapLine = Boolean.valueOf(Utilities.getParamsFromConfigList("moipCapLine")).booleanValue();
    MoipAvailableTicket = Utilities.getParamsFromConfigList("MoipAvailableTicket");
    MoipTicketHistory = Utilities.getParamsFromConfigList("MoipTicketHistory");
    flagShowWebViews = true;
    vehicleList = null;
    new ArrayList();
    globalActivity = null;
    listAlerts = new ArrayList<String>();
    NoneBoolean = bool;
    usingPush = true;
    mHistoryList = new ArrayList<OptionMenu>();
    pushVOsdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
    historicalsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    DateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    DateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
    TimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    new ArrayList();
    mTodayList = new ArrayList<Historical>();
    new ArrayList();
    locatorErrorGetStatus_1 = -2;
    nameDevice = "";
    isConnectionBT = false;
    waitQueueResponceMessages = new commonQueue();
    isAppRunning = false;
    new ArrayList();
    new VersionsVO();
    menuPlatinum = new displaymenu();
    new displaymenu();
    tbtListData = new TbtDataVO();
    tbtMuteStatus = 1;
    tbtDataChunk = null;
    tbtDataChunkAck = null;
    tbtRoutePreviewInfo = new ArrayList<TbtRoutePrevioInfoVO>();
    tbtManeouverInfo = new TbtManeuverInfoVO();
    downloadResponse = "";
    updateStatusResponse = null;
    new OtaResponsePlatinumVersionVO();
    objSendFavoritesMessage = null;
    markerOrFavoritePointVO = new MarkerOrFavoritePointVO(0, 0, "", "");
    PointsNumber = 15;
    Point[] arrayOfPoint = new Point[PointsNumber];
    followMeArrayListPoints = new ArrayList<FollowMePointsVO>();
    followMeIdOnDB = -1L;
    followMeProcessDeviceId = "";
    initFollowMeTime = 0L;
    needFollowMeReview = bool;
    currentActivity = "";
    onFollowMeRun = bool;
    minDistance = 50;
    deviceTypeP7 = "P7";
    deviceTypeP8 = "P8";
    deviceType = "";
    isDownloadingMap = false;
    new ArrayList();
    mDeviceUserList = new ArrayList<UserDevicesVO>();
    googleSearchTitleToFavorite = "";
    numNavegacion = 0;
    menu_level = 0;
    numVerFragment = 0;
    intmenuplat = 0;
    btnpress = 0;
    isTbtWorking = false;
    bshowmenuland = false;
    EstadoAppGlobal = 0;
    str555tmp = "";
    bmenuitempressed = false;
    bmenuitemport = false;
    mmsg85 = 0;
    ifollowMeType = 0;
    iAppNavigationStatus = 0;
    vecWaypoints = new Vector<String>();
    bFinalizaronIntentos = false;
    strAccountID = "";
    new ArrayList();
    strUser = "";
    strPassword = "";
    findmeCode = 0;
    iRutaRecibida = 0;
    strETA = "";
    strViaRoute = "";
    bMostrandoMenuHMI = false;
    bSeEnvioRutaAP8 = false;
    bActivityCallRunning = false;
    bStartingCall = false;
    ws_responceFindMe = null;
    findMeGLIM = false;
    mixedNotificationsPoints = new ArrayList<PushAlertsVO>();
    showdialogfollow = false;
    versionStatus = "0";
    ToExitApp = false;
    stringToAccess = Utilities.Decrypt("24pTA4mtnBkJct9fXL5Thg==");
    stringToAccessUser = Utilities.Decrypt("7/SCQdlCBHAx1uTsObo/lA==");
    SessionTimeout = 15;
    favoritesSelected = new ArrayList<Integer>();
    isSockeActived = false;
    isFromShareFavActivity = false;
    actionSocketCode = "3005";
    activeTutorial = Boolean.valueOf(Utilities.getParamsFromConfigList("flagTutorial")).booleanValue();
    openMap = false;
    isActionRenewalFromMain = false;
    tempSocketResponse = true;
    wakeUpCar = false;
    messsageIdGlobal = "";
    processMessageId = false;
    newMain = true;
    newMaps = true;
    onfollowmeActivated = false;
    valuePoi1 = "valuePoi1";
    valuePoi2 = "valuePoi2";
    CHANNEL_ID = "channelOnStar";
    CHANNEL_NAME = "onstar";
    CHANNEL_IMPORTANCE = 4;
    token = "";
    deviceName = "deviceName";
    enableTTS = false;
    isWUCRunningAffterSetPlanes = false;
    valorPO = "valorPO";
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/GlobalMembers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */