package com.roadtrack.onstar.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.EmailCatalogVO;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import com.roadtrack.onstar.VO.FollowMePointsVO;
import com.roadtrack.onstar.VO.GMTCatalog;
import com.roadtrack.onstar.VO.Historical;
import com.roadtrack.onstar.VO.MapUpdateVO;
import com.roadtrack.onstar.VO.NavigationVO;
import com.roadtrack.onstar.VO.PIDVO;
import com.roadtrack.onstar.VO.PushAlertsVO;
import com.roadtrack.onstar.VO.PushNotificationsVO;
import com.roadtrack.onstar.VO.RemoteDiagnosticVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.SyncVO;
import com.roadtrack.onstar.VO.TomTomStatistics_VO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import com.roadtrack.onstar.db.DBHandler;
import com.roadtrack.onstar.entities.RegisterDevice;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;

@SuppressLint({"SimpleDateFormat"})
public class DBFunctions extends DBHandler {
  public static String DTC_INFO_TABLE;
  
  private static final String KEY_ACCESS;
  
  private static String KEY_CARD_EXPIRATION;
  
  private static String KEY_DTC_SELECTOR;
  
  private static String KEY_EMAIL_MAIL;
  
  private static String KEY_EMAIL_USER;
  
  private static String KEY_ID_VEHICLE;
  
  private static String KEY_LAST_NUMBER_CARD;
  
  private static String KEY_PLAN_APP;
  
  private static String KEY_SEND_ROUTE;
  
  private static String KEY_STATUS_RENEWAL_ACCOUNT;
  
  private static String KEY_STATUS_RENEWAL_DIALOG;
  
  private static String KEY_VALID_CARD;
  
  private static String KEY_VEHICLE_DEVICEID;
  
  private static String KEY_VEHICLE_GTM;
  
  private static String KEY_VEHICLE_IDGTM;
  
  private static String KEY_VEHICLE_ID_ACCOUNT;
  
  private static String KEY_VEHICLE_NAME;
  
  private static String KEY_VEHICLE_SELECTED;
  
  private static String KEY_VEHICLE_THEFT_AUTO_STATUS;
  
  private static String KEY_VEHICLE_USER;
  
  private static final String TAG = DBFunctions.class.getSimpleName();
  
  private static String activity_log = "activity_log";
  
  private static String configTable = "configuration";
  
  private static String emailCatalog;
  
  private static String findmePointsTable;
  
  private static String followmeTable;
  
  private static String historicalTable;
  
  private static String mapUpdateTable;
  
  private static String mapUpdateTableV2;
  
  private static String pushNotificationTable;
  
  private static String vehicleCatalog;
  
  private String GMT_CATALOG_KEY_GMT_TABLE = "gmt_catalog";
  
  public String KEY_ACTIONID = "id_action";
  
  public String KEY_ALERTID = "id_alert";
  
  private String KEY_EVENTDATE = "event_date";
  
  private String KEY_LATEND = "latitude_end";
  
  private String KEY_LATSTART = "latitude_start";
  
  private String KEY_LONEND = "longitude_end";
  
  private String KEY_LONSTART = "longitude_start";
  
  public String KEY_MESSAGEID = "id_message";
  
  public String KEY_NAVIGATIONID = "id_navigation";
  
  private String KEY_RECDATE = "rec_date";
  
  private Context _ctx;
  
  private String navigationTable = "navigation";
  
  static {
    KEY_SEND_ROUTE = "send_route_dialog";
    KEY_ACCESS = Utilities.Decrypt("F5Cv0ba8vjEyw1KNVdC4xQ==");
    vehicleCatalog = "vehicle";
    KEY_ID_VEHICLE = "id_vehicle";
    KEY_VEHICLE_DEVICEID = "id_device";
    KEY_VEHICLE_USER = "id_user";
    KEY_VEHICLE_SELECTED = "selected";
    KEY_VEHICLE_GTM = "vehicle_gtm ";
    KEY_DTC_SELECTOR = "dtc_selector";
    KEY_STATUS_RENEWAL_ACCOUNT = "status_renewal_account";
    KEY_STATUS_RENEWAL_DIALOG = "status_renewal_dialog";
    KEY_VEHICLE_IDGTM = "id_gtm ";
    KEY_VEHICLE_ID_ACCOUNT = "id_account";
    KEY_VEHICLE_THEFT_AUTO_STATUS = "theft_auto_status";
    KEY_VEHICLE_NAME = "vehicle_name";
    KEY_PLAN_APP = "plan_app";
    KEY_VALID_CARD = "valid_card";
    KEY_CARD_EXPIRATION = "card_expiration";
    KEY_LAST_NUMBER_CARD = "last_number_card";
    emailCatalog = "email_catalog";
    KEY_EMAIL_MAIL = "mail";
    KEY_EMAIL_USER = "usere";
    followmeTable = "follow_me";
    pushNotificationTable = "push_notification";
    historicalTable = "historical";
    findmePointsTable = "find_me_point";
    mapUpdateTable = "map_update";
    mapUpdateTableV2 = "map_update";
    DTC_INFO_TABLE = "dtc_info";
  }
  
  public DBFunctions(Context paramContext) {
    super(paramContext);
    this._ctx = paramContext;
  }
  
  private SQLiteStatement fillStmtPid(SQLiteStatement paramSQLiteStatement, PIDVO paramPIDVO, int paramInt) {
    paramSQLiteStatement.bindLong(1, paramPIDVO.getMessageId());
    paramSQLiteStatement.bindLong(2, paramInt);
    paramSQLiteStatement.bindString(3, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getOdometer())));
    paramSQLiteStatement.bindString(4, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getGas())));
    paramSQLiteStatement.bindString(5, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getOilValue())));
    paramSQLiteStatement.bindString(6, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getOilStatus())));
    paramSQLiteStatement.bindString(7, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getBatterylevel())));
    paramSQLiteStatement.bindString(8, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getTire_status_fr())));
    paramSQLiteStatement.bindString(9, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getTire_fr())));
    paramSQLiteStatement.bindString(10, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getTire_status_fl())));
    paramSQLiteStatement.bindString(11, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getTire_fl())));
    paramSQLiteStatement.bindString(12, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getTire_status_rr())));
    paramSQLiteStatement.bindString(13, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getTire_rr())));
    paramSQLiteStatement.bindString(14, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getTire_status_rl())));
    paramSQLiteStatement.bindString(15, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getTire_rl())));
    paramSQLiteStatement.bindString(16, paramPIDVO.getEventDate());
    paramSQLiteStatement.bindString(17, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getModelo())));
    paramSQLiteStatement.bindString(18, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getVersion())));
    paramSQLiteStatement.bindString(19, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getYear())));
    paramSQLiteStatement.bindString(20, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getPlaca())));
    paramSQLiteStatement.bindString(21, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getTPMSText())));
    paramSQLiteStatement.bindString(22, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getIsM300())));
    paramSQLiteStatement.bindString(23, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getAutonomy_km())));
    paramSQLiteStatement.bindString(24, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getAutonomy_status())));
    paramSQLiteStatement.bindString(25, Utilities.Crypt(Utilities.isNullString(paramPIDVO.getAutonomy_text())));
    return paramSQLiteStatement;
  }
  
  private String[] getCreationScript(String paramString) {
    return getScript(paramString);
  }
  
  private String[] getScript(String paramString) {
    InputStream inputStream = DBFunctions.class.getClassLoader().getResourceAsStream(paramString);
    try {
      String[] arrayOfString = Utilities.Decrypt(IOUtils.toString(inputStream)).split(";");
      IOUtils.closeQuietly(inputStream);
      return arrayOfString;
    } catch (IOException iOException) {
      Utilities.escribeArchivo(TAG, "Error: getScript", iOException.toString());
      RuntimeException runtimeException = new RuntimeException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("No fue posible cargar el script  ");
      stringBuilder.append(paramString);
      this(stringBuilder.toString(), iOException);
      throw runtimeException;
    } finally {}
    IOUtils.closeQuietly(inputStream);
    throw paramString;
  }
  
  public boolean ExistCampaing(PushNotificationsVO paramPushNotificationsVO) {
    String str2 = Utilities.Crypt(paramPushNotificationsVO.getIdCampaign_Dialog());
    String str1 = Utilities.Crypt(paramPushNotificationsVO.getDeviceId());
    boolean bool2 = false;
    boolean bool1 = bool2;
    try {
      Cursor cursor = getWritableDatabase().query("generic_dialog", new String[] { "*" }, "id_campaign = ? AND id_device = ?", new String[] { str2, str1 }, null, null, null, null);
      try {
      
      } finally {
        try {
          cursor.close();
        } catch (Exception exception) {
          bool1 = bool2;
          Utilities.escribeArchivo(TAG, "Error:", exception.getMessage());
        } 
        bool1 = bool2;
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error:", exception.getMessage());
    } 
    return bool1;
  }
  
  public void GetDateFollowmeTable(String paramString1, String paramString2) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    String str = getCurentGMT(paramString1);
    try {
      paramString1 = Utilities.Crypt(paramString1.toLowerCase().trim());
      Cursor cursor = sQLiteDatabase.query(followmeTable, new String[] { "id_follow_me", "date" }, "id_device =?", new String[] { paramString1 }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            String str2 = cursor.getString(0);
            String str1 = cursor.getString(1);
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("UPDATE ");
            stringBuilder.append(followmeTable);
            stringBuilder.append(" SET ");
            stringBuilder.append("date");
            stringBuilder.append(" =?  WHERE ");
            stringBuilder.append("id_follow_me");
            stringBuilder.append(" =? ");
            SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
            sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.ChangeHour(str1, str, paramString2, "yyyy-MM-dd HH:mm:ss")));
            sQLiteStatement.bindString(2, Utilities.isNullString(str2));
            sQLiteStatement.executeUpdateDelete();
            sQLiteStatement.clearBindings();
            bool = cursor.moveToNext();
          } while (bool);
        } 
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception1.getMessage());
        } 
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception.getMessage());
    } 
  }
  
  public void GetDateHistoricalTable(String paramString1, String paramString2) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    String str = getCurentGMT(paramString1);
    try {
      String str1 = historicalTable;
      try {
        Cursor cursor = sQLiteDatabase.query(str1, new String[] { "id_history", "date_time", "date_time_execution" }, "vehicle_id =?", new String[] { paramString1 }, null, null, null, null);
        try {
          boolean bool = cursor.moveToFirst();
          if (bool)
            try {
              do {
                paramString1 = cursor.getString(0);
                String str2 = cursor.getString(1);
                String str3 = cursor.getString(2);
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("UPDATE ");
                stringBuilder.append(historicalTable);
                stringBuilder.append(" SET ");
                stringBuilder.append("date_time");
                stringBuilder.append(" =?, ");
                stringBuilder.append("date_time_execution");
                stringBuilder.append(" =?  WHERE ");
                stringBuilder.append("id_history");
                stringBuilder.append(" =? ");
                SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
                sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.ChangeHour(str2, str, paramString2, "yyyy-MM-dd HH:mm:ss")));
                sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.ChangeHour(str3, str, paramString2, "yyyy-MM-dd HH:mm:ss")));
                sQLiteStatement.bindString(3, Utilities.isNullString(paramString1));
                sQLiteStatement.executeUpdateDelete();
                sQLiteStatement.clearBindings();
                bool = cursor.moveToNext();
              } while (bool);
            } finally {} 
        } finally {
          try {
            cursor.close();
          } catch (Exception exception1) {
            Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception1.getMessage());
          } 
        } 
      } catch (Exception null) {}
    } catch (Exception exception) {}
    Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception.getMessage());
  }
  
  public void GetDatePushTable(String paramString1, String paramString2) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    String str = getCurentGMT(paramString1);
    try {
      Cursor cursor = sQLiteDatabase.query(pushNotificationTable, new String[] { "id_push", "time" }, "deviceId =?", new String[] { paramString1 }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            String str2 = cursor.getString(0);
            String str1 = cursor.getString(1);
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("UPDATE ");
            stringBuilder.append(pushNotificationTable);
            stringBuilder.append(" SET ");
            stringBuilder.append("time");
            stringBuilder.append(" =?  WHERE ");
            stringBuilder.append("id_push");
            stringBuilder.append(" =? ");
            SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
            sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.ChangeHour(str1, str, paramString2, "dd-MM-yyyy HH:mm:ss")));
            sQLiteStatement.bindString(2, Utilities.isNullString(str2));
            sQLiteStatement.executeUpdateDelete();
            sQLiteStatement.clearBindings();
            bool = cursor.moveToNext();
          } while (bool);
        } 
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception1.getMessage());
        } 
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception.getMessage());
    } 
  }
  
  public long InsertGenericDialog(PushNotificationsVO paramPushNotificationsVO) {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("");
    stringBuilder1.append(paramPushNotificationsVO.getAcc_Dialog());
    String str3 = Utilities.Crypt(stringBuilder1.toString());
    String str1 = Utilities.Crypt(paramPushNotificationsVO.getIdCampaign_Dialog());
    String str2 = Utilities.Crypt(paramPushNotificationsVO.getTitle_Dialog());
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("");
    stringBuilder2.append(paramPushNotificationsVO.getShowCheckBox_Dialog());
    String str4 = Utilities.Crypt(stringBuilder2.toString());
    String str5 = Utilities.Crypt(paramPushNotificationsVO.getMessage_Dialog());
    String str6 = Utilities.Crypt(paramPushNotificationsVO.getTitleButton_Dialog());
    String str8 = Utilities.Crypt(paramPushNotificationsVO.getUrl_Dialog());
    String str7 = Utilities.Crypt(paramPushNotificationsVO.getExpirationDate_Dialog());
    String str9 = Utilities.Crypt(paramPushNotificationsVO.getDeviceId());
    long l2 = 0L;
    long l1 = l2;
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      l1 = l2;
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("INSERT INTO generic_dialog (acc, id_campaign, title, show_check_box, message, title_button,url,expiration_date,id_device,creation_renewal_date,repeat) VALUES ( ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?);");
      l1 = l2;
      sQLiteStatement.bindString(1, str3);
      l1 = l2;
      sQLiteStatement.bindString(2, str1);
      l1 = l2;
      sQLiteStatement.bindString(3, str2);
      l1 = l2;
      sQLiteStatement.bindString(4, str4);
      l1 = l2;
      sQLiteStatement.bindString(5, str5);
      l1 = l2;
      sQLiteStatement.bindString(6, str6);
      l1 = l2;
      sQLiteStatement.bindString(7, str8);
      l1 = l2;
      sQLiteStatement.bindString(8, str7);
      l1 = l2;
      sQLiteStatement.bindString(9, str9);
      l1 = l2;
      sQLiteStatement.bindString(10, "");
      l1 = l2;
      sQLiteStatement.bindString(11, "1");
      l1 = l2;
      l2 = sQLiteStatement.executeInsert();
      l1 = l2;
      sQLiteStatement.clearBindings();
      l1 = l2;
      sQLiteDatabase.close();
      l1 = l2;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
    } 
    return l1;
  }
  
  public boolean IsDialogShowing(PushNotificationsVO paramPushNotificationsVO) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getIdCampaign_Dialog : ()Ljava/lang/String;
    //   4: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   7: astore #8
    //   9: aload_1
    //   10: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   13: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   16: astore #10
    //   18: iconst_0
    //   19: istore #6
    //   21: iconst_0
    //   22: istore #7
    //   24: iconst_0
    //   25: istore_3
    //   26: iload #7
    //   28: istore #4
    //   30: aload_0
    //   31: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   34: astore #9
    //   36: iconst_1
    //   37: istore #5
    //   39: iload #7
    //   41: istore #4
    //   43: aload #9
    //   45: ldc_w 'generic_dialog'
    //   48: iconst_1
    //   49: anewarray java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: ldc_w '*'
    //   57: aastore
    //   58: ldc_w 'id_campaign = ? AND id_device = ?'
    //   61: iconst_2
    //   62: anewarray java/lang/String
    //   65: dup
    //   66: iconst_0
    //   67: aload #8
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: aload #10
    //   74: aastore
    //   75: aconst_null
    //   76: aconst_null
    //   77: aconst_null
    //   78: aconst_null
    //   79: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   82: astore #8
    //   84: iload #6
    //   86: istore #4
    //   88: aload #8
    //   90: invokeinterface moveToFirst : ()Z
    //   95: ifeq -> 260
    //   98: iload #6
    //   100: istore #4
    //   102: aload #8
    //   104: aload #8
    //   106: ldc_w 'repeat'
    //   109: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   114: invokeinterface getString : (I)Ljava/lang/String;
    //   119: ldc_w '1'
    //   122: invokevirtual equals : (Ljava/lang/Object;)Z
    //   125: istore_3
    //   126: iload_3
    //   127: istore #4
    //   129: new java/text/SimpleDateFormat
    //   132: astore #10
    //   134: iload_3
    //   135: istore #4
    //   137: aload #10
    //   139: ldc_w 'yyyymmdd HH:MM:SS'
    //   142: invokespecial <init> : (Ljava/lang/String;)V
    //   145: iload_3
    //   146: istore #4
    //   148: aload #10
    //   150: aload #8
    //   152: aload #8
    //   154: ldc_w 'expiration_date'
    //   157: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   162: invokeinterface getString : (I)Ljava/lang/String;
    //   167: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   170: invokevirtual parse : (Ljava/lang/String;)Ljava/util/Date;
    //   173: astore #9
    //   175: iload_3
    //   176: istore #4
    //   178: aload #10
    //   180: aload_1
    //   181: invokevirtual getExpirationDate_Dialog : ()Ljava/lang/String;
    //   184: invokevirtual parse : (Ljava/lang/String;)Ljava/util/Date;
    //   187: astore #11
    //   189: iload_3
    //   190: istore #4
    //   192: aload_1
    //   193: invokevirtual getIdCampaign_Dialog : ()Ljava/lang/String;
    //   196: astore #10
    //   198: iload_3
    //   199: istore #4
    //   201: aload_1
    //   202: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   205: astore_1
    //   206: iload_3
    //   207: istore #4
    //   209: aload #9
    //   211: aload #11
    //   213: invokevirtual compareTo : (Ljava/util/Date;)I
    //   216: istore_2
    //   217: iload_2
    //   218: ifge -> 249
    //   221: aload_0
    //   222: ldc_w '1'
    //   225: aload #10
    //   227: aload_1
    //   228: invokevirtual UpdateStatusOfShowGenericDialog : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   231: iload #5
    //   233: istore_3
    //   234: goto -> 249
    //   237: astore_1
    //   238: iconst_1
    //   239: istore_3
    //   240: goto -> 294
    //   243: astore_1
    //   244: iconst_1
    //   245: istore_3
    //   246: goto -> 253
    //   249: goto -> 260
    //   252: astore_1
    //   253: iload_3
    //   254: istore #4
    //   256: aload_1
    //   257: invokevirtual printStackTrace : ()V
    //   260: aload #8
    //   262: invokeinterface close : ()V
    //   267: goto -> 345
    //   270: astore_1
    //   271: iload_3
    //   272: istore #4
    //   274: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   277: ldc_w 'Error:'
    //   280: aload_1
    //   281: invokevirtual getMessage : ()Ljava/lang/String;
    //   284: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   287: goto -> 345
    //   290: astore_1
    //   291: iload #4
    //   293: istore_3
    //   294: aload #8
    //   296: invokeinterface close : ()V
    //   301: goto -> 323
    //   304: astore #8
    //   306: iload_3
    //   307: istore #4
    //   309: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   312: ldc_w 'Error:'
    //   315: aload #8
    //   317: invokevirtual getMessage : ()Ljava/lang/String;
    //   320: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   323: iload_3
    //   324: istore #4
    //   326: aload_1
    //   327: athrow
    //   328: astore_1
    //   329: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   332: ldc_w 'Error:'
    //   335: aload_1
    //   336: invokevirtual getMessage : ()Ljava/lang/String;
    //   339: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   342: iload #4
    //   344: istore_3
    //   345: iload_3
    //   346: ireturn
    // Exception table:
    //   from	to	target	type
    //   30	36	328	java/lang/Exception
    //   43	84	328	java/lang/Exception
    //   88	98	290	finally
    //   102	126	290	finally
    //   129	134	252	java/text/ParseException
    //   129	134	290	finally
    //   137	145	252	java/text/ParseException
    //   137	145	290	finally
    //   148	175	252	java/text/ParseException
    //   148	175	290	finally
    //   178	189	252	java/text/ParseException
    //   178	189	290	finally
    //   192	198	252	java/text/ParseException
    //   192	198	290	finally
    //   201	206	252	java/text/ParseException
    //   201	206	290	finally
    //   209	217	252	java/text/ParseException
    //   209	217	290	finally
    //   221	231	243	java/text/ParseException
    //   221	231	237	finally
    //   256	260	290	finally
    //   260	267	270	java/lang/Exception
    //   274	287	328	java/lang/Exception
    //   294	301	304	java/lang/Exception
    //   309	323	328	java/lang/Exception
    //   326	328	328	java/lang/Exception
  }
  
  public void IsShowDialogBefore(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   4: ldc_w 'generic_dialog'
    //   7: iconst_1
    //   8: anewarray java/lang/String
    //   11: dup
    //   12: iconst_0
    //   13: ldc_w '*'
    //   16: aastore
    //   17: ldc_w 'repeat = ?'
    //   20: iconst_1
    //   21: anewarray java/lang/String
    //   24: dup
    //   25: iconst_0
    //   26: ldc_w '2'
    //   29: aastore
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   37: astore #4
    //   39: aload #4
    //   41: invokeinterface getCount : ()I
    //   46: ifle -> 352
    //   49: aload #4
    //   51: invokeinterface moveToFirst : ()Z
    //   56: ifeq -> 352
    //   59: aload #4
    //   61: aload #4
    //   63: ldc_w 'acc'
    //   66: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   71: invokeinterface getString : (I)Ljava/lang/String;
    //   76: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   79: astore #7
    //   81: aload #4
    //   83: aload #4
    //   85: ldc_w 'id_campaign'
    //   88: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   93: invokeinterface getString : (I)Ljava/lang/String;
    //   98: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   101: astore #5
    //   103: aload #4
    //   105: aload #4
    //   107: ldc_w 'title'
    //   110: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   115: invokeinterface getString : (I)Ljava/lang/String;
    //   120: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   123: astore #12
    //   125: aload #4
    //   127: aload #4
    //   129: ldc_w 'show_check_box'
    //   132: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   137: invokeinterface getString : (I)Ljava/lang/String;
    //   142: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   145: astore #11
    //   147: aload #4
    //   149: aload #4
    //   151: ldc_w 'message'
    //   154: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   159: invokeinterface getString : (I)Ljava/lang/String;
    //   164: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   167: astore #6
    //   169: aload #4
    //   171: aload #4
    //   173: ldc_w 'title_button'
    //   176: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   181: invokeinterface getString : (I)Ljava/lang/String;
    //   186: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   189: astore #10
    //   191: aload #4
    //   193: aload #4
    //   195: ldc_w 'url'
    //   198: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   203: invokeinterface getString : (I)Ljava/lang/String;
    //   208: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   211: astore #9
    //   213: aload #4
    //   215: aload #4
    //   217: ldc_w 'expiration_date'
    //   220: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   225: invokeinterface getString : (I)Ljava/lang/String;
    //   230: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   233: pop
    //   234: aload #4
    //   236: aload #4
    //   238: ldc 'id_device'
    //   240: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   245: invokeinterface getString : (I)Ljava/lang/String;
    //   250: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   253: astore #8
    //   255: aload #7
    //   257: ldc_w '23'
    //   260: invokevirtual equals : (Ljava/lang/Object;)Z
    //   263: ifeq -> 333
    //   266: aload #11
    //   268: ldc_w '1'
    //   271: invokevirtual equals : (Ljava/lang/Object;)Z
    //   274: istore_3
    //   275: aload #9
    //   277: ifnull -> 298
    //   280: aload #9
    //   282: ldc_w ''
    //   285: invokevirtual equals : (Ljava/lang/Object;)Z
    //   288: istore_2
    //   289: iload_2
    //   290: ifne -> 298
    //   293: iconst_1
    //   294: istore_2
    //   295: goto -> 300
    //   298: iconst_0
    //   299: istore_2
    //   300: aload_0
    //   301: ldc_w '1'
    //   304: aload #5
    //   306: aload #8
    //   308: invokevirtual UpdateStatusOfShowGenericDialog : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   311: aload_1
    //   312: aload #12
    //   314: aload #6
    //   316: aload #5
    //   318: iload_3
    //   319: iload_2
    //   320: aload #10
    //   322: aload #9
    //   324: aload #8
    //   326: iconst_1
    //   327: invokestatic ShowGenericDialog : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   330: goto -> 333
    //   333: aload #4
    //   335: invokeinterface moveToNext : ()Z
    //   340: istore_2
    //   341: iload_2
    //   342: ifne -> 59
    //   345: goto -> 352
    //   348: astore_1
    //   349: goto -> 384
    //   352: aload #4
    //   354: invokeinterface close : ()V
    //   359: goto -> 426
    //   362: astore_1
    //   363: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   366: ldc_w 'Error:'
    //   369: aload_1
    //   370: invokevirtual getMessage : ()Ljava/lang/String;
    //   373: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   376: goto -> 426
    //   379: astore_1
    //   380: goto -> 413
    //   383: astore_1
    //   384: aload #4
    //   386: invokeinterface close : ()V
    //   391: goto -> 410
    //   394: astore #4
    //   396: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   399: ldc_w 'Error:'
    //   402: aload #4
    //   404: invokevirtual getMessage : ()Ljava/lang/String;
    //   407: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   410: aload_1
    //   411: athrow
    //   412: astore_1
    //   413: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   416: ldc_w 'Error:'
    //   419: aload_1
    //   420: invokevirtual getMessage : ()Ljava/lang/String;
    //   423: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   426: return
    // Exception table:
    //   from	to	target	type
    //   0	39	412	java/lang/Exception
    //   39	59	383	finally
    //   59	275	383	finally
    //   280	289	383	finally
    //   300	330	348	finally
    //   333	341	348	finally
    //   352	359	362	java/lang/Exception
    //   363	376	379	java/lang/Exception
    //   384	391	394	java/lang/Exception
    //   396	410	379	java/lang/Exception
    //   410	412	379	java/lang/Exception
  }
  
  public Cursor SyncWSFavorites(String paramString1, String paramString2) {
    return getReadableDatabase().query("favorites_history", new String[] { "*" }, "type_poi <> '' ", new String[0], null, null, "date", null);
  }
  
  public void UpdateCampingGenericDialog(PushNotificationsVO paramPushNotificationsVO, boolean paramBoolean) {
    String str1;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("");
    stringBuilder1.append(paramPushNotificationsVO.getAcc_Dialog());
    String str4 = Utilities.Crypt(stringBuilder1.toString());
    String str2 = Utilities.Crypt(paramPushNotificationsVO.getIdCampaign_Dialog());
    String str3 = Utilities.Crypt(paramPushNotificationsVO.getTitle_Dialog());
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("");
    stringBuilder2.append(paramPushNotificationsVO.getShowCheckBox_Dialog());
    String str7 = Utilities.Crypt(stringBuilder2.toString());
    String str8 = Utilities.Crypt(paramPushNotificationsVO.getMessage_Dialog());
    String str6 = Utilities.Crypt(paramPushNotificationsVO.getTitleButton_Dialog());
    String str5 = Utilities.Crypt(paramPushNotificationsVO.getUrl_Dialog());
    String str9 = Utilities.Crypt(paramPushNotificationsVO.getExpirationDate_Dialog());
    String str10 = Utilities.Crypt(paramPushNotificationsVO.getDeviceId());
    if (paramBoolean) {
      str1 = "1";
    } else {
      str1 = "0";
    } 
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("UPDATE generic_dialog SET acc=?, title=?, show_check_box=?, message=?, title_button=?, url=?, expiration_date=?, id_device=?, creation_renewal_date=?, repeat=?  WHERE id_campaign=?");
      sQLiteStatement.bindString(1, str4);
      sQLiteStatement.bindString(2, str3);
      sQLiteStatement.bindString(3, str7);
      sQLiteStatement.bindString(4, str8);
      sQLiteStatement.bindString(5, str6);
      sQLiteStatement.bindString(6, str5);
      sQLiteStatement.bindString(7, str9);
      sQLiteStatement.bindString(8, str10);
      sQLiteStatement.bindString(9, "");
      sQLiteStatement.bindString(10, str1);
      sQLiteStatement.bindString(11, str2);
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
      sQLiteDatabase.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
    } 
  }
  
  public void UpdateDateHistoryAndPush(String paramString1, String paramString2) {
    GetDatePushTable(paramString1, paramString2);
    GetDateFollowmeTable(paramString1, paramString2);
    GetDateHistoricalTable(paramString1, paramString2);
  }
  
  public void UpdateStatusOfShowGenericDialog(String paramString1, String paramString2, String paramString3) {
    paramString2 = Utilities.Crypt(paramString2);
    paramString3 = Utilities.Crypt(paramString3);
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("UPDATE generic_dialog SET repeat =? WHERE id_campaign = ? AND id_device = ?");
      sQLiteStatement.bindString(1, paramString1);
      sQLiteStatement.bindString(2, paramString2);
      sQLiteStatement.bindString(3, paramString3);
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
      sQLiteDatabase.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
    } 
  }
  
  public void addCfg(UserPreferenceVO paramUserPreferenceVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(configTable);
    stringBuilder.append(" (");
    stringBuilder.append("language");
    stringBuilder.append(" , ");
    stringBuilder.append("phone_number");
    stringBuilder.append(" , ");
    stringBuilder.append("notifications");
    stringBuilder.append(" , ");
    stringBuilder.append("user");
    stringBuilder.append(" , ");
    stringBuilder.append(KEY_ACCESS);
    stringBuilder.append(", ");
    stringBuilder.append("terms_conditions");
    stringBuilder.append(" , ");
    stringBuilder.append("save_access");
    stringBuilder.append(" , ");
    stringBuilder.append("city");
    stringBuilder.append(" , ");
    stringBuilder.append("country");
    stringBuilder.append(" , ");
    stringBuilder.append("email");
    stringBuilder.append(" , ");
    stringBuilder.append("remind_download");
    stringBuilder.append(" , ");
    stringBuilder.append("terms_conditions_date");
    stringBuilder.append(" , ");
    stringBuilder.append("TimeOfSet");
    stringBuilder.append(" , ");
    stringBuilder.append("region");
    stringBuilder.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getLanguage())));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getPhone_number())));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramUserPreferenceVO.getNotifications()));
    sQLiteStatement.bindString(4, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getUser().toLowerCase().trim())));
    sQLiteStatement.bindString(5, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getMagic_spell())));
    sQLiteStatement.bindString(6, Utilities.isNullString(paramUserPreferenceVO.getTerms_conditions()));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramUserPreferenceVO.getSave_access()));
    sQLiteStatement.bindString(8, Utilities.isNullString(paramUserPreferenceVO.getCity()));
    sQLiteStatement.bindString(9, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getCountry())));
    sQLiteStatement.bindString(10, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getEMAIL())));
    sQLiteStatement.bindString(11, Utilities.isNullString(paramUserPreferenceVO.getRemindDownload()));
    sQLiteStatement.bindString(12, Utilities.isNullString(paramUserPreferenceVO.getTerms_conditions_date()));
    sQLiteStatement.bindString(13, Utilities.isNullString(paramUserPreferenceVO.getTimeOfSet()));
    sQLiteStatement.bindString(14, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getRegion())));
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
  }
  
  public void addEmailCatalog(EmailCatalogVO paramEmailCatalogVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(emailCatalog);
    stringBuilder.append(" (");
    stringBuilder.append(KEY_EMAIL_MAIL);
    stringBuilder.append(", ");
    stringBuilder.append(KEY_EMAIL_USER);
    stringBuilder.append(") VALUES (?, ?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramEmailCatalogVO.getEmail())));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramEmailCatalogVO.getUser())));
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public void addFavoriteHistory(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9) {
    countFavoritesHist(paramString5, paramString4);
    SQLiteStatement sQLiteStatement = getWritableDatabase().compileStatement("INSERT INTO favorites_history (id_cfg, name, address, date, device_id, type_item, category, latlng, type_poi, id_sync) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString1));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramString2)));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramString3)));
    sQLiteStatement.bindString(4, Utilities.isNullString(Utilities.getDateTime()));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramString4));
    sQLiteStatement.bindString(6, Utilities.isNullString(paramString5));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramString6));
    sQLiteStatement.bindString(8, Utilities.isNullString(Utilities.Crypt(paramString7)));
    sQLiteStatement.bindString(9, Utilities.isNullString(paramString8));
    sQLiteStatement.bindString(10, Utilities.isNullString(paramString9));
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
  }
  
  public void addFavoriteSelectWS(FavoritesHistoryVO paramFavoritesHistoryVO) {
    SQLiteStatement sQLiteStatement = getWritableDatabase().compileStatement("INSERT INTO favorites_history (id_cfg, name, address, date, device_id, type_item, category, latlng, type_poi, id_sync) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    sQLiteStatement.bindString(1, Utilities.isNullString(paramFavoritesHistoryVO.getId_cfg()));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramFavoritesHistoryVO.getName())));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramFavoritesHistoryVO.getAddress())));
    sQLiteStatement.bindString(4, Utilities.isNullString(Utilities.getDateTime()));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramFavoritesHistoryVO.getDevice_id()));
    sQLiteStatement.bindString(6, Utilities.isNullString(paramFavoritesHistoryVO.getType_item()));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramFavoritesHistoryVO.getCategory()));
    sQLiteStatement.bindString(8, Utilities.isNullString(Utilities.Crypt(paramFavoritesHistoryVO.getLatlng())));
    sQLiteStatement.bindString(9, Utilities.isNullString(paramFavoritesHistoryVO.getType_poi()));
    sQLiteStatement.bindString(10, Utilities.isNullString(paramFavoritesHistoryVO.getId_sync()));
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
  }
  
  public void addFindmePoint(String paramString1, String paramString2, String paramString3) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(findmePointsTable);
    stringBuilder.append(" ( ");
    stringBuilder.append("id_device");
    stringBuilder.append(", ");
    stringBuilder.append("latitude");
    stringBuilder.append(", ");
    stringBuilder.append("longitude");
    stringBuilder.append(") VALUES (?, ?, ?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramString1)));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramString2)));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramString3)));
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public long addFollowMePush(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    paramString4 = Utilities.getUTCDateTime("MM/dd/yyyy hh:mm:ss a", paramString4);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(followmeTable);
    stringBuilder.append(" (");
    stringBuilder.append("id_control");
    stringBuilder.append(", ");
    stringBuilder.append("id_device");
    stringBuilder.append(", ");
    stringBuilder.append("date");
    stringBuilder.append(", ");
    stringBuilder.append("in_process");
    stringBuilder.append(", ");
    stringBuilder.append("latitude");
    stringBuilder.append(", ");
    stringBuilder.append("longitude");
    stringBuilder.append(") VALUES (?, ?, ?, ?, ?, ?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindLong(1, paramInt);
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramString1)));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramString4));
    sQLiteStatement.bindLong(4, 1L);
    sQLiteStatement.bindString(5, Utilities.isNullString(Utilities.Crypt(paramString2)));
    sQLiteStatement.bindString(6, Utilities.isNullString(Utilities.Crypt(paramString3)));
    long l = sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
    return l;
  }
  
  public void addGmtRegister(GMTCatalog paramGMTCatalog, SQLiteDatabase... paramVarArgs) {
    SQLiteDatabase sQLiteDatabase;
    if (paramVarArgs.length > 0) {
      sQLiteDatabase = paramVarArgs[0];
      this.GMT_CATALOG_KEY_GMT_TABLE = "gmt_catalog_tmp";
    } else {
      sQLiteDatabase = getWritableDatabase();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(this.GMT_CATALOG_KEY_GMT_TABLE);
    stringBuilder.append(" (");
    stringBuilder.append("id_gmt");
    stringBuilder.append(", ");
    stringBuilder.append("prefix");
    stringBuilder.append(", ");
    stringBuilder.append("value");
    stringBuilder.append(", ");
    stringBuilder.append("name");
    stringBuilder.append(") VALUES (?, ?, ?, ?);");
    String str = stringBuilder.toString();
    try {
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(str);
      sQLiteStatement.bindString(1, paramGMTCatalog.getGmtId());
      sQLiteStatement.bindString(2, paramGMTCatalog.getGmtPrefix());
      sQLiteStatement.bindString(3, paramGMTCatalog.getGmtValue());
      sQLiteStatement.bindString(4, paramGMTCatalog.getGmtName());
      sQLiteStatement.executeInsert();
      sQLiteStatement.clearBindings();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "TOMTOM STATISTICS:", exception.getMessage());
    } 
    this.GMT_CATALOG_KEY_GMT_TABLE = "gmt_catalog";
  }
  
  public long addHistorical(Historical paramHistorical) {
    boolean bool = getHistoricalFromIdAction(paramHistorical.getIdStatus()).booleanValue();
    long l1 = 0L;
    long l2 = l1;
    if (!bool) {
      countHistory(paramHistorical.getUserName(), paramHistorical.getVehicleId());
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("INSERT INTO ");
      stringBuilder.append(historicalTable);
      stringBuilder.append(" ( ");
      stringBuilder.append("category");
      stringBuilder.append(", ");
      stringBuilder.append("name");
      stringBuilder.append(", ");
      stringBuilder.append("description");
      stringBuilder.append(", ");
      stringBuilder.append("aplication_type");
      stringBuilder.append(", ");
      stringBuilder.append("lat");
      stringBuilder.append(", ");
      stringBuilder.append("lng");
      stringBuilder.append(", ");
      stringBuilder.append("id_status");
      stringBuilder.append(", ");
      stringBuilder.append("id_error");
      stringBuilder.append(", ");
      stringBuilder.append("id_action");
      stringBuilder.append(", ");
      stringBuilder.append("vehicle_id");
      stringBuilder.append(", ");
      stringBuilder.append("user_name");
      stringBuilder.append(", ");
      stringBuilder.append("date_time");
      stringBuilder.append(", ");
      stringBuilder.append("completion_code");
      stringBuilder.append(", ");
      stringBuilder.append("requestErroId");
      stringBuilder.append(", ");
      stringBuilder.append("responseErrorId");
      stringBuilder.append(", ");
      stringBuilder.append("messageResponse");
      stringBuilder.append(", ");
      stringBuilder.append("deleted");
      stringBuilder.append(", ");
      stringBuilder.append("speedlimit");
      stringBuilder.append(", ");
      stringBuilder.append("speedexceed");
      stringBuilder.append(", ");
      stringBuilder.append("gpsstatus");
      stringBuilder.append(", ");
      stringBuilder.append("message_id");
      stringBuilder.append(" ,");
      stringBuilder.append("date_time_execution");
      stringBuilder.append(", ");
      stringBuilder.append("readnotification");
      stringBuilder.append(" ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
      sQLiteStatement.bindString(1, Utilities.isNullString(paramHistorical.getCategory()));
      sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramHistorical.getName())));
      sQLiteStatement.bindString(3, Utilities.isNullString(paramHistorical.getDescription()));
      sQLiteStatement.bindString(4, Utilities.isNullString(paramHistorical.getAplicationType()));
      sQLiteStatement.bindString(5, Utilities.isNullString(Utilities.Crypt(paramHistorical.getLatitud())));
      sQLiteStatement.bindString(6, Utilities.isNullString(Utilities.Crypt(paramHistorical.getLongitud())));
      sQLiteStatement.bindLong(7, paramHistorical.getIdStatus());
      sQLiteStatement.bindLong(8, paramHistorical.getIdError());
      sQLiteStatement.bindString(9, Utilities.isNullString(paramHistorical.getIdAction()));
      sQLiteStatement.bindString(10, Utilities.isNullString(paramHistorical.getVehicleId()));
      sQLiteStatement.bindString(11, Utilities.isNullString(Utilities.Crypt(paramHistorical.getUserName())));
      sQLiteStatement.bindString(12, Utilities.isNullString(Utilities.getDateTime()));
      sQLiteStatement.bindString(13, Utilities.isNullString(paramHistorical.getCompletion_code()));
      sQLiteStatement.bindString(14, Utilities.isNullString(paramHistorical.getRequestErroId()));
      sQLiteStatement.bindString(15, Utilities.isNullString(paramHistorical.getResponseErrorId()));
      sQLiteStatement.bindString(16, paramHistorical.getMessageResponseId());
      sQLiteStatement.bindLong(17, paramHistorical.getDeleted());
      sQLiteStatement.bindDouble(18, paramHistorical.getSpeedlimit());
      sQLiteStatement.bindDouble(19, paramHistorical.getSpeedexcedeed());
      sQLiteStatement.bindString(20, paramHistorical.getGPSStatus());
      sQLiteStatement.bindLong(21, paramHistorical.getMessageId());
      sQLiteStatement.bindString(22, Utilities.isNullString(Utilities.getDateTime()));
      sQLiteStatement.bindLong(23, 0L);
      try {
        if (!sQLiteDatabase.isOpen())
          getWritableDatabase(); 
        l2 = sQLiteStatement.executeInsert();
        l1 = l2;
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error: addHistorical 2851", exception.getMessage());
      } 
      sQLiteStatement.clearBindings();
      l2 = l1;
    } 
    return l2;
  }
  
  public long addMapUpdateData(MapUpdateVO paramMapUpdateVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(mapUpdateTableV2);
    stringBuilder.append(" (");
    stringBuilder.append("id_p8_serial");
    stringBuilder.append(", ");
    stringBuilder.append("p8_map_version");
    stringBuilder.append(", ");
    stringBuilder.append("server_map_version");
    stringBuilder.append(", ");
    stringBuilder.append("upgrade_version");
    stringBuilder.append(", ");
    stringBuilder.append("server_file_name");
    stringBuilder.append(", ");
    stringBuilder.append("file_map_status");
    stringBuilder.append(", ");
    stringBuilder.append("file_map_on_parts");
    stringBuilder.append(", ");
    stringBuilder.append("user_wants_upgrade");
    stringBuilder.append(") VALUES (?, ?, ?, ? ,? ,? ,? ,?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, paramMapUpdateVO.getP8IdSerial());
    sQLiteStatement.bindString(2, paramMapUpdateVO.getP8MapVersion());
    sQLiteStatement.bindString(3, paramMapUpdateVO.getServerMapVersion());
    sQLiteStatement.bindString(4, paramMapUpdateVO.getUpgradeOldversionNewversion());
    sQLiteStatement.bindString(5, paramMapUpdateVO.getServerFileName());
    sQLiteStatement.bindLong(6, 0L);
    sQLiteStatement.bindLong(7, 0L);
    sQLiteStatement.bindLong(8, 1L);
    try {
      sQLiteStatement.executeInsert();
      sQLiteStatement.clearBindings();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "MAP UPDATE:", exception.getMessage());
    } 
    return 0L;
  }
  
  public long addMapUpdateData(PushNotificationsVO paramPushNotificationsVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(mapUpdateTable);
    stringBuilder.append(" (");
    stringBuilder.append("id_mapupdate");
    stringBuilder.append(", ");
    stringBuilder.append("oldVersion");
    stringBuilder.append(", ");
    stringBuilder.append("newVersion");
    stringBuilder.append(", ");
    stringBuilder.append("fileName");
    stringBuilder.append(") VALUES (?, ?, ?, ?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindLong(1, 1L);
    sQLiteStatement.bindString(2, Utilities.isNullString(paramPushNotificationsVO.getOldVersion()));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramPushNotificationsVO.getNewVersion()));
    sQLiteStatement.bindString(4, Utilities.isNullString(paramPushNotificationsVO.getFileName()));
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public long addMapUpdateDataPart(PushNotificationsVO paramPushNotificationsVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(mapUpdateTable);
    stringBuilder.append(" (");
    stringBuilder.append("id_mapupdate");
    stringBuilder.append(", ");
    stringBuilder.append("oldVersion");
    stringBuilder.append(", ");
    stringBuilder.append("newVersion");
    stringBuilder.append(", ");
    stringBuilder.append("fileName");
    stringBuilder.append(") VALUES (?, ?, ?, ?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindLong(1, 100L);
    sQLiteStatement.bindString(2, Utilities.isNullString(paramPushNotificationsVO.getOldVersion()));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramPushNotificationsVO.getNewVersion()));
    sQLiteStatement.bindString(4, Utilities.isNullString(paramPushNotificationsVO.getFileName()));
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public long addNavigation(NavigationVO paramNavigationVO, SQLiteDatabase... paramVarArgs) {
    long l2 = 0L;
    if (paramNavigationVO == null)
      return 0L; 
    long l1 = l2;
    try {
      SQLiteDatabase sQLiteDatabase;
      if (paramVarArgs.length > 0) {
        l1 = l2;
        this.navigationTable = "navigation_temp";
        sQLiteDatabase = paramVarArgs[0];
      } else {
        l1 = l2;
        sQLiteDatabase = getWritableDatabase();
      } 
      l1 = l2;
      StringBuilder stringBuilder = new StringBuilder();
      l1 = l2;
      this();
      l1 = l2;
      stringBuilder.append("INSERT INTO ");
      l1 = l2;
      stringBuilder.append(this.navigationTable);
      l1 = l2;
      stringBuilder.append(" ( ");
      l1 = l2;
      stringBuilder.append(this.KEY_LATSTART);
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append(this.KEY_LONSTART);
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append(this.KEY_LATEND);
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append(this.KEY_LONEND);
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append("address");
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append(this.KEY_ACTIONID);
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append(this.KEY_MESSAGEID);
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append(this.KEY_ALERTID);
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append(this.KEY_EVENTDATE);
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append(this.KEY_RECDATE);
      l1 = l2;
      stringBuilder.append(" ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
      l1 = l2;
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
      l1 = l2;
      sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(String.valueOf(paramNavigationVO.getLatStart()))));
      l1 = l2;
      sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(String.valueOf(paramNavigationVO.getLonStart()))));
      l1 = l2;
      sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(String.valueOf(paramNavigationVO.getLatEnd()))));
      l1 = l2;
      sQLiteStatement.bindString(4, Utilities.isNullString(Utilities.Crypt(String.valueOf(paramNavigationVO.getLonEnd()))));
      l1 = l2;
      sQLiteStatement.bindString(5, Utilities.isNullString(paramNavigationVO.getAddress()));
      l1 = l2;
      sQLiteStatement.bindLong(6, paramNavigationVO.getActionId());
      l1 = l2;
      sQLiteStatement.bindLong(7, paramNavigationVO.getMessageId());
      l1 = l2;
      sQLiteStatement.bindLong(8, paramNavigationVO.getAlertId());
      l1 = l2;
      sQLiteStatement.bindString(9, Utilities.isNullString(paramNavigationVO.getEvent_Date()));
      l1 = l2;
      sQLiteStatement.bindString(10, Utilities.isNullString(paramNavigationVO.getRec_Date()));
      l1 = l2;
      l2 = sQLiteStatement.executeInsert();
      l1 = l2;
      sQLiteStatement.clearBindings();
      l1 = l2;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "ERROR", exception.getMessage());
    } 
    this.navigationTable = "navigation";
    return l1;
  }
  
  public void addNotificationDTC(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(pushNotificationTable);
    stringBuilder.append(" (");
    stringBuilder.append("id_response");
    stringBuilder.append(", ");
    stringBuilder.append("acc");
    stringBuilder.append(", ");
    stringBuilder.append("message");
    stringBuilder.append(", ");
    stringBuilder.append("time");
    stringBuilder.append(", ");
    stringBuilder.append("alertId");
    stringBuilder.append(", ");
    stringBuilder.append("actionId");
    stringBuilder.append(", ");
    stringBuilder.append("deviceId");
    stringBuilder.append(", ");
    stringBuilder.append("latitude");
    stringBuilder.append(", ");
    stringBuilder.append("longitude");
    stringBuilder.append(", ");
    stringBuilder.append("type");
    stringBuilder.append(", ");
    stringBuilder.append("title");
    stringBuilder.append(", ");
    stringBuilder.append("n_actionid");
    stringBuilder.append(", ");
    stringBuilder.append("inserted_row");
    stringBuilder.append(", ");
    stringBuilder.append("message_id");
    stringBuilder.append(", ");
    stringBuilder.append("deleted");
    stringBuilder.append(", ");
    stringBuilder.append("alertCodeId");
    stringBuilder.append(", ");
    stringBuilder.append("readnotification");
    stringBuilder.append(" ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    String str = stringBuilder.toString();
    SQLiteStatement sQLiteStatement = getWritableDatabase().compileStatement(str);
    sQLiteStatement.bindString(1, Utilities.isNullString(""));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramString1));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramString2)));
    sQLiteStatement.bindString(4, Utilities.isNullString(paramString3));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramString4));
    sQLiteStatement.bindString(6, Utilities.isNullString(Utilities.Crypt(paramString2)));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramString5));
    sQLiteStatement.bindString(8, Utilities.isNullString(Utilities.Crypt(paramString6)));
    sQLiteStatement.bindString(9, Utilities.isNullString(Utilities.Crypt(paramString7)));
    sQLiteStatement.bindString(10, Utilities.isNullString(""));
    sQLiteStatement.bindString(11, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(12, Utilities.isNullString(""));
    sQLiteStatement.bindString(13, Utilities.isNullString(""));
    sQLiteStatement.bindString(14, Utilities.isNullString(paramString8));
    sQLiteStatement.bindLong(15, 0L);
    sQLiteStatement.bindLong(16, paramInt);
    sQLiteStatement.bindLong(17, 0L);
    sQLiteStatement.executeInsert();
  }
  
  public long addPid(int paramInt, PIDVO paramPIDVO) {
    long l1 = 0L;
    long l2 = l1;
    try {
      SQLiteStatement sQLiteStatement;
      DBHandler dBHandler = new DBHandler();
      l2 = l1;
      this(this._ctx);
      l2 = l1;
      SQLiteDatabase sQLiteDatabase = dBHandler.getWritableDatabase();
      l2 = l1;
      if (!existPID(String.valueOf(paramInt)).booleanValue()) {
        l2 = l1;
        SQLiteStatement sQLiteStatement1 = sQLiteDatabase.compileStatement("INSERT INTO pid_historical (id_message, id_device, odometer, gas, oil, oil_status, battery_level, tire_status_fr, tire_fr, tire_status_fl, tire_fl, tire_status_rr, tire_rr, tire_status_rl, tire_rl, event_date, modelo, version, year, placa, tpms, m300, autonomy_km, autonomy_status, autonomy_text) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        l2 = l1;
        fillStmtPid(sQLiteStatement1, paramPIDVO, paramInt);
        l2 = l1;
        l1 = sQLiteStatement1.executeInsert();
        sQLiteStatement = sQLiteStatement1;
      } else {
        l2 = l1;
        SQLiteStatement sQLiteStatement1 = sQLiteDatabase.compileStatement("UPDATE pid_historical SET id_message =? , id_device =? , odometer =? , gas =? , oil =? , oil_status =? , battery_level =? , tire_status_fr =? , tire_fr =? , tire_status_fl =? , tire_fl =? , tire_status_rr =? , tire_rr =? , tire_status_rl =? , tire_rl =? , event_date =? , modelo =? , version =? , year =? , placa =? , tpms =? , m300 =? , autonomy_km =? , autonomy_status =? , autonomy_text =?   WHERE id_device =? ");
        l2 = l1;
        fillStmtPid(sQLiteStatement1, (PIDVO)sQLiteStatement, paramInt);
        l2 = l1;
        sQLiteStatement1.bindLong(26, paramInt);
        l2 = l1;
        sQLiteStatement1.executeUpdateDelete();
        sQLiteStatement = sQLiteStatement1;
      } 
      l2 = l1;
      sQLiteStatement.clearBindings();
      l2 = l1;
      sQLiteDatabase.close();
      l2 = l1;
      dBHandler.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
      l1 = l2;
    } 
    return l1;
  }
  
  public long addPush(PushNotificationsVO paramPushNotificationsVO, String paramString) {
    countPush(paramPushNotificationsVO.getDeviceId());
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    String str = Utilities.getDateTime(paramPushNotificationsVO.getDeviceId(), this._ctx);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str.substring(8, 10));
    stringBuilder.append("-");
    stringBuilder.append(str.substring(5, 7));
    stringBuilder.append("-");
    stringBuilder.append(str.substring(0, 4));
    stringBuilder.append(" ");
    stringBuilder.append(str.substring(11, 19));
    str = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(pushNotificationTable);
    stringBuilder.append(" (");
    stringBuilder.append("id_response");
    stringBuilder.append(", ");
    stringBuilder.append("acc");
    stringBuilder.append(", ");
    stringBuilder.append("message");
    stringBuilder.append(", ");
    stringBuilder.append("time");
    stringBuilder.append(", ");
    stringBuilder.append("alertId");
    stringBuilder.append(", ");
    stringBuilder.append("actionId");
    stringBuilder.append(", ");
    stringBuilder.append("deviceId");
    stringBuilder.append(", ");
    stringBuilder.append("latitude");
    stringBuilder.append(", ");
    stringBuilder.append("longitude");
    stringBuilder.append(", ");
    stringBuilder.append("type");
    stringBuilder.append(", ");
    stringBuilder.append("title");
    stringBuilder.append(", ");
    stringBuilder.append("n_actionid");
    stringBuilder.append(", ");
    stringBuilder.append("inserted_row");
    stringBuilder.append(", ");
    stringBuilder.append("deleted");
    stringBuilder.append(", ");
    stringBuilder.append("readnotification");
    stringBuilder.append(" ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramPushNotificationsVO.getIdResponse()));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramPushNotificationsVO.getAccNumber()));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramPushNotificationsVO.getMessage())));
    sQLiteStatement.bindString(4, Utilities.isNullString(str));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramPushNotificationsVO.getAlertId()));
    sQLiteStatement.bindString(6, Utilities.isNullString(Utilities.Crypt(paramPushNotificationsVO.getActionId().toString())));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramPushNotificationsVO.getDeviceId()));
    sQLiteStatement.bindString(8, Utilities.isNullString(Utilities.Crypt(paramPushNotificationsVO.getLatitude())));
    sQLiteStatement.bindString(9, Utilities.isNullString(Utilities.Crypt(paramPushNotificationsVO.getLongitude())));
    sQLiteStatement.bindString(10, Utilities.isNullString(paramPushNotificationsVO.getType()));
    sQLiteStatement.bindString(11, Utilities.isNullString(paramPushNotificationsVO.getTitle()));
    sQLiteStatement.bindString(12, Utilities.isNullString(paramPushNotificationsVO.getNumberActionID()));
    sQLiteStatement.bindString(13, Utilities.isNullString(paramString));
    sQLiteStatement.bindLong(14, 0L);
    sQLiteStatement.bindLong(15, 0L);
    long l = sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
    return l;
  }
  
  public long addPush(PushNotificationsVO paramPushNotificationsVO, String paramString1, String paramString2) {
    countPush(paramPushNotificationsVO.getDeviceId());
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(pushNotificationTable);
    stringBuilder.append(" ( ");
    stringBuilder.append("id_response");
    stringBuilder.append(", ");
    stringBuilder.append("acc");
    stringBuilder.append(", ");
    stringBuilder.append("message");
    stringBuilder.append(", ");
    stringBuilder.append("time");
    stringBuilder.append(", ");
    stringBuilder.append("alertId");
    stringBuilder.append(", ");
    stringBuilder.append("actionId");
    stringBuilder.append(", ");
    stringBuilder.append("deviceId");
    stringBuilder.append(", ");
    stringBuilder.append("latitude");
    stringBuilder.append(", ");
    stringBuilder.append("longitude");
    stringBuilder.append(", ");
    stringBuilder.append("type");
    stringBuilder.append(", ");
    stringBuilder.append("title");
    stringBuilder.append(", ");
    stringBuilder.append("n_actionid");
    stringBuilder.append(", ");
    stringBuilder.append("inserted_row");
    stringBuilder.append(", ");
    stringBuilder.append("deleted");
    stringBuilder.append(", ");
    stringBuilder.append("readnotification");
    stringBuilder.append(" ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramPushNotificationsVO.getIdResponse()));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramPushNotificationsVO.getAccNumber()));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramPushNotificationsVO.getMessage())));
    sQLiteStatement.bindString(4, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramPushNotificationsVO.getAlertId()));
    sQLiteStatement.bindString(6, Utilities.isNullString(Utilities.Crypt(paramPushNotificationsVO.getActionId().toString())));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramPushNotificationsVO.getDeviceId()));
    sQLiteStatement.bindString(8, Utilities.isNullString(Utilities.Crypt(paramPushNotificationsVO.getLatitude())));
    sQLiteStatement.bindString(9, Utilities.isNullString(Utilities.Crypt(paramPushNotificationsVO.getLongitude())));
    sQLiteStatement.bindString(10, Utilities.isNullString(paramPushNotificationsVO.getType()));
    sQLiteStatement.bindString(11, Utilities.isNullString(paramPushNotificationsVO.getTitle()));
    sQLiteStatement.bindString(12, Utilities.isNullString(paramPushNotificationsVO.getNumberActionID()));
    sQLiteStatement.bindString(13, Utilities.isNullString(paramString1));
    sQLiteStatement.bindLong(14, 0L);
    sQLiteStatement.bindLong(15, 0L);
    long l = sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
    return l;
  }
  
  public void addVehicleSelected(Context paramContext, String paramString, UserDevicesVO paramUserDevicesVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DELETE  FROM ");
    stringBuilder.append(vehicleCatalog);
    stringBuilder.append("  WHERE ");
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append(" =? AND ");
    stringBuilder.append(KEY_VEHICLE_USER);
    stringBuilder.append("=?");
    String str = stringBuilder.toString();
    try {
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(str);
      sQLiteStatement.bindString(1, "L");
      sQLiteStatement.bindString(2, Utilities.Crypt(paramString));
      sQLiteStatement.executeUpdateDelete();
    } catch (SQLException sQLException) {
      Utilities.escribeArchivo(TAG, "addVehicleSelected:", sQLException.getMessage());
    } 
    try {
      DBFunctions dBFunctions = new DBFunctions();
      this(paramContext);
      for (VehicleCatalogVO vehicleCatalogVO : dBFunctions.getVehiclesCatalog(paramString)) {
        if (paramUserDevicesVO.getDeviceId().equals(vehicleCatalogVO.getDeviceId())) {
          StringBuilder stringBuilder2 = new StringBuilder();
          this();
          stringBuilder2.append("INSERT INTO ");
          stringBuilder2.append(vehicleCatalog);
          stringBuilder2.append(" (");
          stringBuilder2.append(KEY_VEHICLE_DEVICEID);
          stringBuilder2.append(", ");
          stringBuilder2.append(KEY_VEHICLE_USER);
          stringBuilder2.append(", ");
          stringBuilder2.append(KEY_VEHICLE_SELECTED);
          stringBuilder2.append(", ");
          stringBuilder2.append(KEY_VEHICLE_IDGTM);
          stringBuilder2.append(", ");
          stringBuilder2.append(KEY_VEHICLE_GTM);
          stringBuilder2.append(",");
          stringBuilder2.append(KEY_DTC_SELECTOR);
          stringBuilder2.append(",");
          stringBuilder2.append(KEY_STATUS_RENEWAL_ACCOUNT);
          stringBuilder2.append(",");
          stringBuilder2.append(KEY_STATUS_RENEWAL_DIALOG);
          stringBuilder2.append(",");
          stringBuilder2.append(KEY_VEHICLE_NAME);
          stringBuilder2.append(") VALUES (?, ?, ?,?,?,?,?,?,?);");
          SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder2.toString());
          sQLiteStatement.bindString(1, Utilities.isNullString(vehicleCatalogVO.getDeviceId()));
          sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(vehicleCatalogVO.getUser())));
          sQLiteStatement.bindString(3, Utilities.isNullString("L"));
          sQLiteStatement.bindString(4, Utilities.isNullString(vehicleCatalogVO.getVehicle_IDGTM()));
          sQLiteStatement.bindString(5, Utilities.isNullString(vehicleCatalogVO.getVehicle_GTM()));
          sQLiteStatement.bindString(6, Utilities.isNullString(vehicleCatalogVO.getDTC_Selector()));
          sQLiteStatement.bindString(7, Utilities.isNullString(vehicleCatalogVO.getStatus_renewal_account()));
          sQLiteStatement.bindString(8, Utilities.isNullString(vehicleCatalogVO.getStatus_renewal_dialog()));
          sQLiteStatement.bindString(9, Utilities.isNullString(vehicleCatalogVO.getVehicleName()));
          sQLiteStatement.executeInsert();
          sQLiteStatement.clearBindings();
          String str1 = TAG;
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("user: ");
          stringBuilder1.append(vehicleCatalogVO.getUser());
          stringBuilder1.append("\ndeviceId: ");
          stringBuilder1.append(vehicleCatalogVO.getDeviceId());
          stringBuilder1.append("\nID:");
          stringBuilder1.append(vehicleCatalogVO.getId());
          stringBuilder1.append(paramUserDevicesVO.getName());
          Utilities.escribeArchivo(str1, "Last Vehicle Selected:", stringBuilder1.toString());
        } 
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "addVehicleSelected:", exception.getMessage());
    } 
  }
  
  public void addVehiclesCatalog(VehicleCatalogVO paramVehicleCatalogVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(vehicleCatalog);
    stringBuilder.append(" (");
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(", ");
    stringBuilder.append(KEY_VEHICLE_USER);
    stringBuilder.append(", ");
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append(", ");
    stringBuilder.append(KEY_VEHICLE_IDGTM);
    stringBuilder.append(", ");
    stringBuilder.append(KEY_VEHICLE_GTM);
    stringBuilder.append(",");
    stringBuilder.append(KEY_DTC_SELECTOR);
    stringBuilder.append(",");
    stringBuilder.append(KEY_STATUS_RENEWAL_ACCOUNT);
    stringBuilder.append(",");
    stringBuilder.append(KEY_STATUS_RENEWAL_DIALOG);
    stringBuilder.append(",");
    stringBuilder.append(KEY_VEHICLE_NAME);
    stringBuilder.append(",");
    stringBuilder.append(KEY_PLAN_APP);
    stringBuilder.append(",");
    stringBuilder.append(KEY_VALID_CARD);
    stringBuilder.append(",");
    stringBuilder.append(KEY_CARD_EXPIRATION);
    stringBuilder.append(",");
    stringBuilder.append(KEY_LAST_NUMBER_CARD);
    stringBuilder.append(") VALUES (?, ?, ?,?,?,?,?,?,?,?,?,?,?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramVehicleCatalogVO.getDeviceId()));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramVehicleCatalogVO.getUser())));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramVehicleCatalogVO.getSelected()));
    sQLiteStatement.bindString(4, Utilities.isNullString(paramVehicleCatalogVO.getVehicle_IDGTM()));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramVehicleCatalogVO.getVehicle_GTM()));
    sQLiteStatement.bindString(6, Utilities.isNullString(paramVehicleCatalogVO.getDTC_Selector()));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramVehicleCatalogVO.getStatus_renewal_account()));
    sQLiteStatement.bindString(8, Utilities.isNullString(paramVehicleCatalogVO.getStatus_renewal_dialog()));
    sQLiteStatement.bindString(9, Utilities.isNullString(paramVehicleCatalogVO.getVehicleName()));
    sQLiteStatement.bindString(10, Utilities.isNullString(paramVehicleCatalogVO.getPlan_app()));
    sQLiteStatement.bindString(11, Utilities.isNullString(paramVehicleCatalogVO.isValidCard()));
    sQLiteStatement.bindString(12, Utilities.isNullString(paramVehicleCatalogVO.getDateCardExpiration()));
    sQLiteStatement.bindString(13, Utilities.isNullString(paramVehicleCatalogVO.getLastCardNumber()));
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
  }
  
  public boolean alertIdExists(String paramString) {
    boolean bool1;
    boolean bool4 = false;
    boolean bool3 = false;
    boolean bool2 = false;
    Cursor cursor2 = null;
    Cursor cursor1 = null;
    try {
      Cursor cursor = getReadableDatabase().query(pushNotificationTable, new String[] { "*" }, "alertId = ?", new String[] { paramString }, null, null, null, null);
      bool1 = bool2;
      if (cursor != null) {
        cursor1 = cursor;
        cursor2 = cursor;
        boolean bool = cursor.moveToFirst();
        bool1 = bool2;
        if (bool)
          bool1 = true; 
      } 
      bool2 = bool1;
      try {
        cursor.close();
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error: messageIdExists", exception.getMessage());
        bool1 = bool2;
      } 
    } catch (Exception exception) {
      cursor1 = cursor2;
      Utilities.escribeArchivo(TAG, "Error: messageIdExists", exception.getMessage());
      bool2 = bool4;
      cursor2.close();
      bool1 = bool3;
    } finally {}
    return bool1;
  }
  
  public void copyDTCData(SQLiteDatabase paramSQLiteDatabase) {
    ArrayList<RemoteDiagnosticVO> arrayList = new ArrayList();
    try {
      Cursor cursor = paramSQLiteDatabase.query("dtc_info", new String[] { "*" }, null, null, null, null, null, null);
      if (cursor.moveToFirst()) {
        RemoteDiagnosticVO remoteDiagnosticVO = new RemoteDiagnosticVO();
        this();
        do {
          remoteDiagnosticVO.setDeviceId(cursor.getString(1));
          remoteDiagnosticVO.setDtcGroupId(cursor.getInt(2));
          remoteDiagnosticVO.setDtcStatusId(cursor.getInt(3));
          remoteDiagnosticVO.setDtcTitle(Utilities.Decrypt(cursor.getString(4)));
          remoteDiagnosticVO.setDtcDescription(Utilities.Decrypt(cursor.getString(5)));
          remoteDiagnosticVO.setDtcDate(Utilities.Decrypt(cursor.getString(6)));
          arrayList.add(remoteDiagnosticVO);
        } while (cursor.moveToNext());
      } 
      cursor.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo("Copy data of dtc", "ERROR", exception.getMessage());
    } 
    if (!arrayList.isEmpty())
      insertDTC(arrayList, new SQLiteDatabase[] { paramSQLiteDatabase }); 
  }
  
  public void copyGMT(SQLiteDatabase paramSQLiteDatabase) {
    ArrayList<GMTCatalog> arrayList = new ArrayList();
    try {
      Cursor cursor = paramSQLiteDatabase.query("gmt_catalog", new String[] { "*" }, null, null, null, null, null, null);
      if (cursor.getCount() > 0 && cursor.moveToFirst())
        do {
          GMTCatalog gMTCatalog = new GMTCatalog();
          this();
          gMTCatalog.setGmtId(cursor.getString(1));
          gMTCatalog.setGmtPrefix(cursor.getString(2));
          gMTCatalog.setGmtValue(cursor.getString(3));
          gMTCatalog.setGmtName(cursor.getString(4));
          arrayList.add(gMTCatalog);
        } while (cursor.moveToNext()); 
      cursor.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo("DBAuxiliarCopyData", "Error: getAllTomTomStatistics", exception.getMessage());
    } 
    Iterator<GMTCatalog> iterator = arrayList.iterator();
    while (iterator.hasNext()) {
      addGmtRegister(iterator.next(), new SQLiteDatabase[] { paramSQLiteDatabase });
    } 
  }
  
  public void copyNavigationData(SQLiteDatabase paramSQLiteDatabase) {
    ArrayList<NavigationVO> arrayList = new ArrayList();
    try {
      Cursor cursor = paramSQLiteDatabase.query("navigation", new String[] { "*" }, null, null, null, null, null, null);
      if (cursor.moveToFirst()) {
        NavigationVO navigationVO = new NavigationVO();
        this();
        do {
          navigationVO.setNavigationId(cursor.getInt(0));
          navigationVO.setLatStart(cursor.getDouble(1));
          navigationVO.setLonStart(cursor.getDouble(2));
          navigationVO.setLatEnd(cursor.getDouble(3));
          navigationVO.setLonEnd(cursor.getDouble(4));
          navigationVO.setAddress(cursor.getString(5));
          navigationVO.setActionId(cursor.getInt(6));
          navigationVO.setMessageId(cursor.getInt(7));
          navigationVO.setAlertId(cursor.getInt(8));
          navigationVO.setEvent_date(cursor.getString(9));
          navigationVO.setRec_date(cursor.getString(10));
          arrayList.add(navigationVO);
        } while (cursor.moveToNext());
      } 
      cursor.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo("Copy data of navigation", "ERROR", exception.getMessage());
    } 
    if (!arrayList.isEmpty()) {
      Iterator<NavigationVO> iterator = arrayList.iterator();
      while (iterator.hasNext()) {
        addNavigation(iterator.next(), new SQLiteDatabase[] { paramSQLiteDatabase });
      } 
    } 
  }
  
  public int countFavorites(String paramString1, String paramString2) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    byte b = 0;
    Cursor cursor = sQLiteDatabase.query("favorites_history", new String[] { "*" }, "device_id =? AND type_item=?", new String[] { paramString1, paramString2 }, null, null, null, null);
    int i = b;
    if (cursor != null) {
      i = b;
      if (!cursor.isClosed()) {
        i = b;
        if (cursor.moveToLast()) {
          i = cursor.getCount();
          cursor.close();
        } 
      } 
    } 
    return i;
  }
  
  public void countFavoritesHist(String paramString1, String paramString2) {
    ArrayList<FavoritesHistoryVO> arrayList = getFavoritesHistory(paramString1, paramString2);
    if (arrayList != null) {
      int i = arrayList.size();
      if (i >= 20)
        deleteFavorite(Integer.valueOf(((FavoritesHistoryVO)arrayList.get(i - 1)).getId_favs_history()).intValue()); 
    } 
  }
  
  public void countHistory(String paramString1, String paramString2) {
    List<Historical> list = getHistoricalList(paramString1, paramString2);
    if (list != null && list.size() >= 200)
      deleteHistorical(Integer.valueOf(((Historical)list.get(0)).getId()).intValue()); 
  }
  
  public void countPush(String paramString) {
    List<PushAlertsVO> list = getAlertList(paramString, false);
    if (list != null && list.size() >= 50)
      deletePush(Integer.valueOf(((PushAlertsVO)list.get(0)).getId()).intValue()); 
  }
  
  public void deleteAllDevices() {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("DELETE FROM register_device");
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
    } catch (Exception exception) {
      Utilities.escribeArchivo("DEVICE ALL", "Error: deleteDevice", exception.toString());
    } finally {}
    sQLiteDatabase.close();
  }
  
  public void deleteAllFavorite() {
    getWritableDatabase().delete("favorites_history", null, null);
  }
  
  public long deleteAllFollowmePoints() {
    return getWritableDatabase().delete(followmeTable, null, null);
  }
  
  public long deleteAllHistorical(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(historicalTable);
    stringBuilder.append(" SET deleted = 1 WHERE ");
    stringBuilder.append("vehicle_id");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public long deleteAllPush(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(pushNotificationTable);
    stringBuilder.append(" SET deleted = 1  WHERE ");
    stringBuilder.append("deviceId");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public int deleteAllVehicleCatalog(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    int j = 0;
    int i = j;
    try {
      StringBuilder stringBuilder = new StringBuilder();
      i = j;
      this();
      i = j;
      stringBuilder.append("DELETE FROM ");
      i = j;
      stringBuilder.append(vehicleCatalog);
      i = j;
      stringBuilder.append(" WHERE ");
      i = j;
      stringBuilder.append(KEY_VEHICLE_USER);
      i = j;
      stringBuilder.append("=? AND ");
      i = j;
      stringBuilder.append(KEY_VEHICLE_SELECTED);
      i = j;
      stringBuilder.append(" !=?");
      i = j;
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
      i = j;
      sQLiteStatement.bindString(1, Utilities.Crypt(paramString));
      i = j;
      sQLiteStatement.bindString(2, "L");
      i = j;
      j = sQLiteStatement.executeUpdateDelete();
      i = j;
      sQLiteStatement.clearBindings();
      i = j;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: deleteAllVehicleCatalog", exception.toString());
    } finally {}
    return i;
  }
  
  public void deleteAllVehicleFavorite(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("DELETE FROM favorites_history WHERE device_id =?  AND type_item <> 'Historical' AND type_poi <> 0");
      sQLiteStatement.bindString(1, Utilities.isNullString(paramString));
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: deleteAllVehicleFavorite", exception.getMessage());
    } finally {}
  }
  
  public void deleteAllVehicleSyncFavorite(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("DELETE FROM favorites_history WHERE device_id =?  AND type_item <> 'Historical' AND type_poi = 0");
      sQLiteStatement.bindString(1, Utilities.isNullString(paramString));
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: deleteAllVehicleFavorite", exception.getMessage());
    } finally {}
  }
  
  public void deleteDeviceRegister(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("DELETE FROM register_device WHERE udid =? ");
      sQLiteStatement.bindString(1, paramString);
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
    } catch (Exception exception) {
      Utilities.escribeArchivo("DEVICE", "Error: deleteDevice", exception.toString());
    } finally {}
    sQLiteDatabase.close();
  }
  
  public void deleteDownloadPreferece() {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("DELETE FROM ");
      stringBuilder.append(mapUpdateTable);
      stringBuilder.append(" WHERE id_mapupdate = 20");
      sQLiteDatabase.compileStatement(stringBuilder.toString()).executeUpdateDelete();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: deleteMapUpdateData", exception.getMessage());
    } finally {}
  }
  
  public void deleteFavorite(int paramInt) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("DELETE FROM favorites_history WHERE id_favs_history =? ");
      sQLiteStatement.bindLong(1, paramInt);
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: deleteFavorite", exception.toString());
    } finally {}
  }
  
  public void deleteFavorite(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("DELETE FROM favorites_history WHERE id_sync =?  AND type_item <> 'Historical' ");
      sQLiteStatement.bindString(1, Utilities.isNullString(paramString));
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: deleteAllVehicleFavorite", exception.getMessage());
    } finally {}
  }
  
  public long deleteHistorical(int paramInt) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(historicalTable);
    stringBuilder.append(" SET deleted = 1  WHERE ");
    stringBuilder.append("id_history");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindLong(1, paramInt);
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public void deleteMapUpdateData() {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("DELETE FROM ");
      stringBuilder.append(mapUpdateTable);
      sQLiteDatabase.compileStatement(stringBuilder.toString()).executeUpdateDelete();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: deleteMapUpdateData", exception.getMessage());
    } finally {
      Exception exception;
    } 
  }
  
  public void deleteMapUpdateDataPart() {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("DELETE FROM ");
      stringBuilder.append(mapUpdateTable);
      stringBuilder.append("WHERE id_mapupdate = 100");
      sQLiteDatabase.compileStatement(stringBuilder.toString()).executeUpdateDelete();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: deleteMapUpdateData", exception.getMessage());
    } finally {}
  }
  
  public long deleteOldDTCByDevice(String paramString, SQLiteDatabase... paramVarArgs) {
    long l;
    try {
      SQLiteDatabase sQLiteDatabase;
      if (paramVarArgs.length > 0) {
        sQLiteDatabase = paramVarArgs[0];
      } else {
        sQLiteDatabase = getWritableDatabase();
      } 
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("DELETE FROM ");
      stringBuilder.append(DTC_INFO_TABLE);
      stringBuilder.append(" WHERE ");
      stringBuilder.append("id_device");
      stringBuilder.append(" = ? ");
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
      sQLiteStatement.bindString(1, Utilities.Crypt(paramString));
      int i = sQLiteStatement.executeUpdateDelete();
      l = i;
    } catch (SQLException sQLException) {
      Utilities.escribeArchivo(TAG, "Error: delete old DTC ", sQLException.getMessage());
      l = 0L;
    } 
    return l;
  }
  
  public long deletePush(int paramInt) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(pushNotificationTable);
    stringBuilder.append(" SET deleted = 1 WHERE ");
    stringBuilder.append("id_push");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindLong(1, paramInt);
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public long deletePush(String paramString1, String paramString2) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(pushNotificationTable);
    stringBuilder.append(" SET deleted = 1  WHERE ");
    stringBuilder.append("actionId");
    stringBuilder.append(" = ");
    stringBuilder.append(Utilities.Crypt("FollowMe"));
    stringBuilder.append(" AND ");
    stringBuilder.append("deviceId");
    stringBuilder.append(" =?  AND time >=? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString1));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramString2));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public boolean deleteTheftStatusVehicle(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(vehicleCatalog);
    stringBuilder.append(" SET ");
    stringBuilder.append(KEY_VEHICLE_ID_ACCOUNT);
    stringBuilder.append(" = ? , ");
    stringBuilder.append(KEY_VEHICLE_THEFT_AUTO_STATUS);
    stringBuilder.append(" = ? WHERE ");
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(" = ?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    String str = Utilities.isNullString("");
    boolean bool = true;
    sQLiteStatement.bindString(1, str);
    sQLiteStatement.bindString(2, Utilities.isNullString(""));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramString));
    long l = sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    if (l <= 0L)
      bool = false; 
    return bool;
  }
  
  public boolean executeNotificationFollowMeSynch(Context paramContext, String paramString) {
    try {
      boolean bool1;
      boolean bool2;
      String str1;
      String str2;
      SQLiteDatabase sQLiteDatabase1 = getReadableDatabase();
      String str3 = Utilities.Crypt("FollowMe");
      Cursor cursor3 = sQLiteDatabase1.query(historicalTable, new String[] { "id_status", "description" }, "name =? AND vehicle_id =?  AND deleted = 0", new String[] { str3, paramString }, null, null, "date_time DESC", null);
      boolean bool = cursor3.moveToFirst();
      if (bool) {
        str2 = cursor3.getString(0);
        str1 = cursor3.getString(1);
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append(str1.replace("/", "-"));
        stringBuilder1.append(":00");
        str1 = stringBuilder1.toString();
      } else {
        str2 = "";
        str1 = "";
      } 
      cursor3.close();
      if (str1 == null || str1.length() == 0)
        return false; 
      Cursor cursor2 = getReadableDatabase().query(pushNotificationTable, new String[] { "count(*)" }, "actionId =?  AND deviceId =?  AND time >=? AND deleted = 0", new String[] { str3, paramString, str1 }, null, null, null, null);
      if (cursor2.moveToFirst()) {
        bool1 = Integer.valueOf(cursor2.getString(0)).intValue();
      } else {
        bool1 = false;
      } 
      cursor2.close();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(str1.substring(6, 10));
      stringBuilder.append("-");
      stringBuilder.append(str1.substring(3, 5));
      stringBuilder.append("-");
      stringBuilder.append(str1.substring(0, 2));
      stringBuilder.append(" ");
      stringBuilder.append(str1.substring(11, 16));
      stringBuilder.append(":00");
      String str4 = stringBuilder.toString();
      SQLiteDatabase sQLiteDatabase2 = getReadableDatabase();
      String str5 = followmeTable;
      String str6 = Utilities.Crypt(paramString);
      Cursor cursor1 = sQLiteDatabase2.query(str5, new String[] { "count(*)" }, "id_device =?  AND date >=? ", new String[] { str6, str4 }, null, null, null, null);
      if (cursor1.moveToFirst()) {
        bool2 = Integer.valueOf(cursor1.getString(0)).intValue();
      } else {
        bool2 = false;
      } 
      cursor1.close();
      if ((str2.equals("-1") && bool1 != 8 && bool1 != bool2) || (!str2.equals("-1") && bool1 != bool2)) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (bool1) {
        deletePush(paramString, str1);
        SQLiteDatabase sQLiteDatabase = getReadableDatabase();
        str2 = Utilities.Crypt(paramString.toLowerCase().trim());
        Cursor cursor = sQLiteDatabase.query(followmeTable, new String[] { "*" }, "id_device =? AND date >=?  AND latitude != 0 AND longitude != 0 ", new String[] { str2, str1 }, null, null, "date ASC", "8");
        StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
        this();
        str2 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_acciondescinfolocalizacion_1, 2131689864);
        String str = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.asynctask_get_last_incoming_message_notification_title, 2131689678);
        if (cursor.moveToFirst()) {
          PushNotificationsVO pushNotificationsVO = new PushNotificationsVO();
          this();
          pushNotificationsVO.setIdResponse("2");
          pushNotificationsVO.setAcc(Enums.responcesGCM.follow_acc);
          pushNotificationsVO.setAccNumber("12");
          pushNotificationsVO.setMessage(str2);
          pushNotificationsVO.setAlertId("N/A");
          pushNotificationsVO.setDeviceId(paramString);
          pushNotificationsVO.setActionId(Enums.Services.FollowMe);
          pushNotificationsVO.setType("");
          pushNotificationsVO.setTitle(str);
          pushNotificationsVO.setNumberActionID("");
          do {
            pushNotificationsVO.setLatitude(Utilities.Decrypt(cursor.getString(5)));
            pushNotificationsVO.setLongitude(Utilities.Decrypt(cursor.getString(6)));
            str = cursor.getString(3);
            pushNotificationsVO.setTime(str);
            paramString = cursor.getString(0);
            StringBuilder stringBuilder1 = new StringBuilder();
            this();
            stringBuilder1.append(str.substring(8, 10));
            stringBuilder1.append("-");
            stringBuilder1.append(str.substring(5, 7));
            stringBuilder1.append("-");
            stringBuilder1.append(str.substring(0, 4));
            stringBuilder1.append(" ");
            stringBuilder1.append(str.substring(11, 19));
            addPush(pushNotificationsVO, paramString, stringBuilder1.toString());
          } while (cursor.moveToNext());
        } 
        cursor.close();
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: getHistoricFollowMe", exception.getMessage());
    } 
    return false;
  }
  
  public void executeSqlScript(SQLiteDatabase paramSQLiteDatabase, String paramString) {
    try {
      for (String paramString : getCreationScript(paramString)) {
        if (paramString != null && !paramString.trim().equals(""))
          if (paramString.toUpperCase().startsWith("INSERT")) {
            getWritableDatabase().execSQL(paramString);
          } else {
            paramSQLiteDatabase.execSQL(paramString);
          }  
      } 
    } catch (Exception exception) {}
  }
  
  public Boolean existDevice(String paramString) {
    Cursor cursor = getReadableDatabase().query(findmePointsTable, new String[] { "*" }, "id_device =? ", new String[] { paramString }, null, null, null, null);
    boolean bool = cursor.moveToFirst();
    cursor.close();
    return Boolean.valueOf(bool);
  }
  
  public boolean existFavorite(String paramString1, String paramString2, String paramString3) {
    paramString1 = Utilities.Crypt(paramString3);
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query("favorites_history", new String[] { "*" }, "name =?  COLLATE NOCASE AND device_id =? ", new String[] { paramString1, paramString2 }, null, null, null, null);
      try {
        return cursor.moveToFirst();
      } finally {
        try {
          cursor.close();
        } catch (Exception exception) {
          Utilities.escribeArchivo(TAG, "Error: existFavorite", exception.getMessage());
        } 
      } 
    } finally {}
  }
  
  public Boolean existPID(String paramString) {
    Utilities.Crypt(paramString.toLowerCase().trim());
    Cursor cursor = (new DBHandler(this._ctx)).getReadableDatabase().query("pid_historical", new String[] { "id_device" }, "id_device =? ", new String[] { paramString }, null, null, null, null);
    boolean bool = cursor.moveToFirst();
    cursor.close();
    return Boolean.valueOf(bool);
  }
  
  public boolean existRow(SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, String paramString3) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString2);
    stringBuilder.append("= ?");
    paramString2 = stringBuilder.toString();
    boolean bool = true;
    Cursor cursor = paramSQLiteDatabase.query(paramString1, new String[] { "*" }, paramString2, new String[] { paramString3 }, null, null, null);
    if (cursor == null || !cursor.moveToFirst())
      bool = false; 
    return bool;
  }
  
  public Boolean existTheftStatusVehicleDeviceId(String paramString) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(" =? AND ");
    stringBuilder.append(KEY_VEHICLE_THEFT_AUTO_STATUS);
    stringBuilder.append(" != \"\"");
    String str = stringBuilder.toString();
    Cursor cursor = sQLiteDatabase.query(vehicleCatalog, new String[] { KEY_VEHICLE_DEVICEID }, str, new String[] { paramString }, null, null, null, null);
    boolean bool = cursor.moveToFirst();
    cursor.close();
    return Boolean.valueOf(bool);
  }
  
  public Boolean existUser(String paramString) {
    paramString = Utilities.Crypt(paramString.toLowerCase().trim());
    Cursor cursor = getReadableDatabase().query(configTable, new String[] { "user" }, "user =? ", new String[] { paramString }, null, null, null, null);
    boolean bool = cursor.moveToFirst();
    cursor.close();
    return Boolean.valueOf(bool);
  }
  
  public Boolean existUserWithTerms(String paramString) {
    paramString = Utilities.Crypt(paramString.toLowerCase().trim());
    Cursor cursor = getReadableDatabase().query(configTable, new String[] { "user" }, "terms_conditions != ?  AND user =? ", new String[] { "", paramString }, null, null, null, null);
    boolean bool = cursor.moveToFirst();
    cursor.close();
    return Boolean.valueOf(bool);
  }
  
  public List<PushAlertsVO> getAgendamientoAlertList(String paramString) {
    ArrayList<PushAlertsVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(pushNotificationTable, new String[] { "*" }, "deleted = 0 AND deviceId =?  AND alertCodeId =?  AND readnotification !=1", new String[] { paramString, "-1" }, null, null, null, null);
      try {
        boolean bool = cursor.moveToFirst();
        if (bool)
          do {
            boolean bool1;
            try {
              bool1 = Integer.valueOf(cursor.getString(16)).intValue();
            } catch (Exception exception1) {
              Utilities.escribeArchivo(TAG, "Error", exception1.getMessage());
              bool1 = false;
            } 
            PushAlertsVO pushAlertsVO = new PushAlertsVO();
            this("1", Utilities.Decrypt(cursor.getString(3)), cursor.getString(7), cursor.getString(4), Utilities.Decrypt(cursor.getString(8)), Utilities.Decrypt(cursor.getString(9)), Utilities.Decrypt(cursor.getString(6)), cursor.getString(11), cursor.getString(2), cursor.getString(0), cursor.getString(13), false, "0", "0", "0", "0", bool1, cursor.getString(4));
            arrayList.add(pushAlertsVO);
            bool = cursor.moveToNext();
          } while (bool); 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getAlertList", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public List<PushAlertsVO> getAlertList(String paramString, boolean paramBoolean) {
    String str1;
    ArrayList<PushAlertsVO> arrayList = new ArrayList();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("deleted = 0 AND deviceId =? ");
    if (paramBoolean) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(" AND (actionId !='");
      stringBuilder1.append(Utilities.EncryptData("FindMe"));
      stringBuilder1.append("')");
      str1 = stringBuilder1.toString();
    } else {
      str1 = "";
    } 
    stringBuilder.append(str1);
    String str2 = stringBuilder.toString();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(pushNotificationTable, new String[] { "*" }, str2, new String[] { paramString }, null, null, null, null);
      try {
        paramBoolean = cursor.moveToFirst();
        if (paramBoolean)
          do {
            boolean bool;
            try {
              bool = Integer.valueOf(cursor.getString(16)).intValue();
            } catch (Exception exception1) {
              Utilities.escribeArchivo(TAG, "Error", exception1.getMessage());
              bool = false;
            } 
            PushAlertsVO pushAlertsVO = new PushAlertsVO();
            this("1", Utilities.Decrypt(cursor.getString(3)), cursor.getString(7), cursor.getString(4), Utilities.Decrypt(cursor.getString(8)), Utilities.Decrypt(cursor.getString(9)), Utilities.Decrypt(cursor.getString(6)), cursor.getString(11), cursor.getString(2), cursor.getString(0), cursor.getString(13), false, "0", "0", "0", "0", bool, cursor.getString(4));
            arrayList.add(pushAlertsVO);
            paramBoolean = cursor.moveToNext();
          } while (paramBoolean); 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getAlertList", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public List<PushAlertsVO> getAlertListWithoutDTC(String paramString, boolean paramBoolean) {
    String str1;
    ArrayList<PushAlertsVO> arrayList = new ArrayList();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DELETED = 0  AND deviceId =?  AND readnotification !=1 ");
    if (paramBoolean) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(" AND (actionId !='");
      stringBuilder1.append(Utilities.EncryptData("FindMe"));
      stringBuilder1.append("')");
      str1 = stringBuilder1.toString();
    } else {
      str1 = "";
    } 
    stringBuilder.append(str1);
    String str2 = stringBuilder.toString();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(pushNotificationTable, new String[] { "*" }, str2, new String[] { paramString }, null, null, null, null);
      try {
        paramBoolean = cursor.moveToFirst();
        if (paramBoolean)
          do {
            boolean bool;
            try {
              bool = Integer.valueOf(cursor.getString(16)).intValue();
            } catch (Exception exception1) {
              Utilities.escribeArchivo(TAG, "Error", exception1.getMessage());
              bool = false;
            } 
            if (bool != 'Ð' && bool != -1) {
              PushAlertsVO pushAlertsVO = new PushAlertsVO();
              this("1", Utilities.Decrypt(cursor.getString(3)), cursor.getString(7), cursor.getString(4), Utilities.Decrypt(cursor.getString(8)), Utilities.Decrypt(cursor.getString(9)), Utilities.Decrypt(cursor.getString(6)), cursor.getString(11), cursor.getString(2), cursor.getString(0), cursor.getString(13), false, "0", "0", "0", "0", bool, cursor.getString(4));
              arrayList.add(pushAlertsVO);
            } 
            paramBoolean = cursor.moveToNext();
          } while (paramBoolean); 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getAlertList", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public List<FollowMePointsVO> getAllFollowMe(String paramString1, String paramString2) {
    paramString1 = paramString2;
    if (paramString2.trim().length() <= 0)
      paramString1 = GlobalMembers.followMeProcessDeviceId; 
    ArrayList<FollowMePointsVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      paramString1 = Utilities.Crypt(paramString1.toLowerCase().trim());
      Cursor cursor = sQLiteDatabase.query(followmeTable, new String[] { "*" }, "id_device =?  AND latitude != ? AND longitude != ? ", new String[] { paramString1, "0", "0" }, null, null, "date ASC", "8");
      if (cursor != null) {
        int i = cursor.getCount();
        if (i > 0)
          try {
            if (cursor.moveToFirst()) {
              boolean bool;
              do {
                FollowMePointsVO followMePointsVO = new FollowMePointsVO();
                this();
                followMePointsVO.setDeviceId(Utilities.Decrypt(cursor.getString(2)));
                followMePointsVO.setTime(cursor.getString(3));
                followMePointsVO.setLat(Utilities.Decrypt(cursor.getString(5)));
                followMePointsVO.setLng(Utilities.Decrypt(cursor.getString(6)));
                followMePointsVO.setDateTime(cursor.getString(3));
                arrayList.add(followMePointsVO);
                bool = cursor.moveToNext();
              } while (bool);
            } 
          } finally {
            try {
              exception.close();
            } catch (Exception exception1) {
              Utilities.escribeArchivo(TAG, "Error: getAllFollowMe", exception1.getMessage());
            } 
          }  
      } 
      try {
        for (FollowMePointsVO followMePointsVO : arrayList) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(followMePointsVO.getDateTime());
          stringBuilder.append("  ");
          stringBuilder.append(followMePointsVO.getLat());
          stringBuilder.append("  ");
          stringBuilder.append(followMePointsVO.getLng());
          Utilities.escribeArchivo("Sígueme Test", "DBFunctions getallfollowme", stringBuilder.toString());
        } 
      } catch (Exception exception) {}
    } finally {}
  }
  
  public ArrayList<GMTCatalog> getAllGmtCatalog() {
    null = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(this.GMT_CATALOG_KEY_GMT_TABLE, new String[] { "*" }, null, null, null, null, null, null);
      try {
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
          boolean bool;
          do {
            GMTCatalog gMTCatalog = new GMTCatalog();
            this();
            gMTCatalog.setGmtId(cursor.getString(cursor.getColumnIndex("id_gmt")));
            gMTCatalog.setGmtPrefix(cursor.getString(cursor.getColumnIndex("prefix")));
            gMTCatalog.setGmtValue(cursor.getString(cursor.getColumnIndex("value")));
            gMTCatalog.setGmtName(cursor.getString(cursor.getColumnIndex("name")));
            null.add(gMTCatalog);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return null;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getAllTomTomStatistics", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<TomTomStatistics_VO> getAllTomTomStatistics() {
    null = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query("tomtom_statistic", new String[] { "*" }, null, null, null, null, null, null);
      try {
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
          boolean bool;
          do {
            TomTomStatistics_VO tomTomStatistics_VO = new TomTomStatistics_VO();
            this();
            tomTomStatistics_VO.setAccountId(cursor.getString(cursor.getColumnIndex("id_account")));
            tomTomStatistics_VO.setDeviceId(Utilities.Crypt(cursor.getString(cursor.getColumnIndex("id_device"))));
            tomTomStatistics_VO.setFechaHora(cursor.getString(cursor.getColumnIndex("fecha_hora")));
            tomTomStatistics_VO.setLatitudeStart(Utilities.Crypt(cursor.getString(cursor.getColumnIndex("latitude_start"))));
            tomTomStatistics_VO.setLongitudeStart(Utilities.Crypt(cursor.getString(cursor.getColumnIndex("logitude_start"))));
            tomTomStatistics_VO.setLatitudeEnd(Utilities.Crypt(cursor.getString(cursor.getColumnIndex("latitude_end"))));
            tomTomStatistics_VO.setLongitudeEnd(Utilities.Crypt(cursor.getString(cursor.getColumnIndex("longitude_end"))));
            tomTomStatistics_VO.setActionId(cursor.getString(cursor.getColumnIndex("id_action")));
            null.add(tomTomStatistics_VO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return null;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getAllTomTomStatistics", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public String[] getAttemptToPay(String paramString) {
    String[] arrayOfString = new String[4];
    try {
      Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM renewal_dialog_status WHERE deviceId = ?", new String[] { paramString });
      try {
        if (cursor.moveToFirst()) {
          arrayOfString[0] = cursor.getString(5);
          arrayOfString[1] = cursor.getString(6);
          arrayOfString[2] = cursor.getString(7);
          arrayOfString[3] = cursor.getString(8);
          String str = arrayOfString[0];
          if (str == null)
            return null; 
        } 
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getRenewalDialogStatus", exception1.getMessage());
        } 
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: getRenewalDialogStatus", exception.getMessage());
    } 
    return arrayOfString;
  }
  
  public HashMap<String, String> getConfigUser(String paramString) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      String str = Utilities.Crypt(paramString);
      paramString = configTable;
      str = Utilities.Decrypt(str);
      Cursor cursor = sQLiteDatabase.query(paramString, new String[] { "*" }, "user=? ", new String[] { str }, null, null, null, null);
      try {
        cursor.moveToFirst();
        if (cursor.getCount() > 0)
          hashMap.put(KEY_SEND_ROUTE, String.valueOf(cursor.getDouble(18))); 
        return (HashMap)hashMap;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getDataConfigToUpdate", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public String getCurentGMT(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(" = ?");
    String str = stringBuilder.toString();
    try {
      Cursor cursor = sQLiteDatabase.query(vehicleCatalog, new String[] { KEY_VEHICLE_GTM }, str, new String[] { paramString }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          paramString = cursor.getString(0);
        } else {
          paramString = "0";
        } 
        return paramString;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getVehicleCatalog", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public String getCurentIDGMT(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(" = ?");
    String str = stringBuilder.toString();
    try {
      Cursor cursor = sQLiteDatabase.query(vehicleCatalog, new String[] { KEY_VEHICLE_IDGTM }, str, new String[] { paramString }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          paramString = cursor.getString(0);
        } else {
          paramString = "";
        } 
        return paramString;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getVehicleCatalog", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public List<PushAlertsVO> getDTCAlertList(String paramString) {
    ArrayList<PushAlertsVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(pushNotificationTable, new String[] { "*" }, "deleted = ? AND deviceId =?  AND alertCodeId =? AND  readnotification !=?", new String[] { "0", paramString, "208", "1" }, null, null, null, null);
      try {
        boolean bool = cursor.moveToFirst();
        if (bool)
          do {
            boolean bool1;
            try {
              bool1 = Integer.valueOf(cursor.getString(16)).intValue();
            } catch (Exception exception1) {
              Utilities.escribeArchivo(TAG, "Error", exception1.getMessage());
              bool1 = false;
            } 
            PushAlertsVO pushAlertsVO = new PushAlertsVO();
            this("1", Utilities.Decrypt(cursor.getString(3)), cursor.getString(7), cursor.getString(4), Utilities.Decrypt(cursor.getString(8)), Utilities.Decrypt(cursor.getString(9)), Utilities.Decrypt(cursor.getString(6)), cursor.getString(11), cursor.getString(2), cursor.getString(0), cursor.getString(13), false, "0", "0", "0", "0", bool1, cursor.getString(4));
            arrayList.add(pushAlertsVO);
            bool = cursor.moveToNext();
          } while (bool); 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getAlertList", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public List<RemoteDiagnosticVO> getDTCByDeviceId(String paramString) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_3
    //   8: aconst_null
    //   9: astore #5
    //   11: aload_0
    //   12: invokevirtual getReadableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   15: astore #4
    //   17: aload_1
    //   18: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   21: invokevirtual trim : ()Ljava/lang/String;
    //   24: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   27: astore_1
    //   28: aload #4
    //   30: getstatic com/roadtrack/onstar/DAO/DBFunctions.DTC_INFO_TABLE : Ljava/lang/String;
    //   33: iconst_1
    //   34: anewarray java/lang/String
    //   37: dup
    //   38: iconst_0
    //   39: ldc_w '*'
    //   42: aastore
    //   43: ldc_w 'id_device =? '
    //   46: iconst_1
    //   47: anewarray java/lang/String
    //   50: dup
    //   51: iconst_0
    //   52: aload_1
    //   53: aastore
    //   54: aconst_null
    //   55: aconst_null
    //   56: aconst_null
    //   57: aconst_null
    //   58: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   61: astore #4
    //   63: aload #4
    //   65: invokeinterface getCount : ()I
    //   70: ifle -> 251
    //   73: aload #4
    //   75: invokeinterface moveToFirst : ()Z
    //   80: ifeq -> 251
    //   83: new com/roadtrack/onstar/VO/RemoteDiagnosticVO
    //   86: astore_1
    //   87: aload_1
    //   88: invokespecial <init> : ()V
    //   91: aload_1
    //   92: aload #4
    //   94: aload #4
    //   96: ldc 'id_device'
    //   98: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   103: invokeinterface getString : (I)Ljava/lang/String;
    //   108: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   111: invokevirtual setDeviceId : (Ljava/lang/String;)V
    //   114: aload_1
    //   115: aload #4
    //   117: aload #4
    //   119: ldc_w 'id_group'
    //   122: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   127: invokeinterface getInt : (I)I
    //   132: invokevirtual setDtcGroupId : (I)V
    //   135: aload_1
    //   136: aload #4
    //   138: aload #4
    //   140: ldc_w 'id_status'
    //   143: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   148: invokeinterface getInt : (I)I
    //   153: invokevirtual setDtcStatusId : (I)V
    //   156: aload_1
    //   157: aload #4
    //   159: aload #4
    //   161: ldc_w 'title'
    //   164: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   169: invokeinterface getString : (I)Ljava/lang/String;
    //   174: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   177: invokevirtual setDtcTitle : (Ljava/lang/String;)V
    //   180: aload_1
    //   181: aload #4
    //   183: aload #4
    //   185: ldc_w 'description'
    //   188: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   193: invokeinterface getString : (I)Ljava/lang/String;
    //   198: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   201: invokevirtual setDtcDescription : (Ljava/lang/String;)V
    //   204: aload_1
    //   205: aload #4
    //   207: aload #4
    //   209: ldc_w 'date'
    //   212: invokeinterface getColumnIndexOrThrow : (Ljava/lang/String;)I
    //   217: invokeinterface getString : (I)Ljava/lang/String;
    //   222: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   225: invokevirtual setDtcDate : (Ljava/lang/String;)V
    //   228: aload_3
    //   229: aload_1
    //   230: invokeinterface add : (Ljava/lang/Object;)Z
    //   235: pop
    //   236: aload #4
    //   238: invokeinterface moveToNext : ()Z
    //   243: istore_2
    //   244: iload_2
    //   245: ifne -> 83
    //   248: goto -> 253
    //   251: aconst_null
    //   252: astore_3
    //   253: aload #4
    //   255: ifnull -> 268
    //   258: aload #4
    //   260: astore_1
    //   261: aload #4
    //   263: invokeinterface close : ()V
    //   268: aload_3
    //   269: astore_1
    //   270: aload #4
    //   272: ifnull -> 349
    //   275: aload #4
    //   277: invokeinterface close : ()V
    //   282: aload_3
    //   283: astore_1
    //   284: goto -> 349
    //   287: astore_3
    //   288: aload #4
    //   290: ifnull -> 303
    //   293: aload #4
    //   295: astore_1
    //   296: aload #4
    //   298: invokeinterface close : ()V
    //   303: aload #4
    //   305: astore_1
    //   306: aload_3
    //   307: athrow
    //   308: astore #5
    //   310: aload #4
    //   312: astore_3
    //   313: goto -> 327
    //   316: astore_3
    //   317: aload #5
    //   319: astore_1
    //   320: goto -> 352
    //   323: astore #5
    //   325: aconst_null
    //   326: astore_3
    //   327: aload_3
    //   328: astore_1
    //   329: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   332: aload #5
    //   334: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/Exception;)V
    //   337: aload_3
    //   338: ifnull -> 347
    //   341: aload_3
    //   342: invokeinterface close : ()V
    //   347: aconst_null
    //   348: astore_1
    //   349: aload_1
    //   350: areturn
    //   351: astore_3
    //   352: aload_1
    //   353: ifnull -> 362
    //   356: aload_1
    //   357: invokeinterface close : ()V
    //   362: aload_3
    //   363: athrow
    // Exception table:
    //   from	to	target	type
    //   11	63	323	java/lang/Exception
    //   11	63	316	finally
    //   63	83	287	finally
    //   83	244	287	finally
    //   261	268	308	java/lang/Exception
    //   261	268	351	finally
    //   296	303	308	java/lang/Exception
    //   296	303	351	finally
    //   306	308	308	java/lang/Exception
    //   306	308	351	finally
    //   329	337	351	finally
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public ArrayList<FavoritesHistoryVO> getDeletedFavorites(String paramString) {
    ArrayList<FavoritesHistoryVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query("favorites_history", new String[] { "*" }, "type_item = 'Favorites' AND device_id =?  AND type_poi = 2", new String[] { paramString }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            FavoritesHistoryVO favoritesHistoryVO = new FavoritesHistoryVO();
            this(cursor.getInt(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), Utilities.Decrypt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), Utilities.Decrypt(cursor.getString(8)), cursor.getString(9), cursor.getString(10));
            arrayList.add(favoritesHistoryVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getDeletedFavorites", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<RegisterDevice> getDeviceRegister() {
    ArrayList<RegisterDevice> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    Cursor cursor = sQLiteDatabase.query("register_device", new String[] { "*" }, "", null, null, null, null);
    if (cursor != null && cursor.moveToFirst())
      do {
        RegisterDevice registerDevice = new RegisterDevice();
        registerDevice.setUdid(cursor.getString(0));
        registerDevice.setDeviceName(cursor.getString(1));
        registerDevice.setApplicationType(cursor.getString(2));
        registerDevice.setSelect(Boolean.valueOf(false));
        arrayList.add(registerDevice);
      } while (cursor.moveToNext()); 
    cursor.close();
    sQLiteDatabase.close();
    return arrayList;
  }
  
  public int getDownloadMapUpdatePrefrence() {
    null = getReadableDatabase();
    try {
      Cursor cursor = null.query(mapUpdateTable, new String[] { "*" }, "id_mapupdate =?", new String[] { "20" }, null, null, null, null);
      try {
        boolean bool;
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
          bool = Integer.valueOf(cursor.getString(cursor.getColumnIndex("automatic_download"))).intValue();
        } else {
          bool = false;
        } 
        return bool;
      } finally {
        try {
          cursor.close();
        } catch (Exception exception) {
          Utilities.escribeArchivo(TAG, "Error: getMapUpdateMapaPart", exception.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<EmailCatalogVO> getEmailCatalog(String paramString) {
    ArrayList<EmailCatalogVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_EMAIL_USER);
    stringBuilder.append(" =? ");
    String str = stringBuilder.toString();
    try {
      paramString = Utilities.Crypt(paramString.toLowerCase().trim());
      Cursor cursor = sQLiteDatabase.query(emailCatalog, new String[] { "*" }, str, new String[] { paramString }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            EmailCatalogVO emailCatalogVO = new EmailCatalogVO();
            this(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            arrayList.add(emailCatalogVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getEmailCatalog", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public ArrayList<FavoritesHistoryVO> getFavoritesHistory(String paramString1, String paramString2) {
    ArrayList<FavoritesHistoryVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query("favorites_history", new String[] { "*" }, "type_item =? AND device_id =? AND type_poi <> 2 ", new String[] { paramString1, paramString2 }, null, null, "name COLLATE NOCASE", null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            FavoritesHistoryVO favoritesHistoryVO = new FavoritesHistoryVO();
            this(cursor.getInt(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), Utilities.Decrypt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), Utilities.Decrypt(cursor.getString(8)), cursor.getString(9), cursor.getString(10));
            arrayList.add(favoritesHistoryVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getFavoritesHistory", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<GMTCatalog> getGmtCatalogRegisterByGmtId(String paramString) {
    ArrayList<GMTCatalog> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(this.GMT_CATALOG_KEY_GMT_TABLE, new String[] { "*" }, "id_gmt = ?", new String[] { paramString }, null, null, null, null);
      try {
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
          boolean bool;
          do {
            GMTCatalog gMTCatalog = new GMTCatalog();
            this();
            gMTCatalog.setGmtId(cursor.getString(cursor.getColumnIndex("id_gmt")));
            gMTCatalog.setGmtPrefix(cursor.getString(cursor.getColumnIndex("prefix")));
            gMTCatalog.setGmtValue(cursor.getString(cursor.getColumnIndex("value")));
            gMTCatalog.setGmtName(cursor.getString(cursor.getColumnIndex("name")));
            arrayList.add(gMTCatalog);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getAllTomTomStatistics", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public Boolean getHistoricalFromIdAction(int paramInt) {
    Cursor cursor = getReadableDatabase().query(historicalTable, new String[] { "*" }, "id_action =?  AND deleted = 0", new String[] { String.valueOf(paramInt) }, null, null, null, null);
    boolean bool = cursor.moveToFirst();
    cursor.close();
    return Boolean.valueOf(bool);
  }
  
  public List<Historical> getHistoricalList(String paramString1, String paramString2) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #5
    //   9: aload_0
    //   10: invokevirtual getReadableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   13: astore #6
    //   15: aload_1
    //   16: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   19: invokevirtual trim : ()Ljava/lang/String;
    //   22: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   25: astore_1
    //   26: aload_2
    //   27: ifnull -> 71
    //   30: aload #6
    //   32: getstatic com/roadtrack/onstar/DAO/DBFunctions.historicalTable : Ljava/lang/String;
    //   35: iconst_1
    //   36: anewarray java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w '*'
    //   44: aastore
    //   45: ldc_w 'user_name=?  AND vehicle_id=?  AND deleted = 0'
    //   48: iconst_2
    //   49: anewarray java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_1
    //   55: aastore
    //   56: dup
    //   57: iconst_1
    //   58: aload_2
    //   59: aastore
    //   60: aconst_null
    //   61: aconst_null
    //   62: aconst_null
    //   63: aconst_null
    //   64: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   67: astore_1
    //   68: goto -> 105
    //   71: aload #6
    //   73: getstatic com/roadtrack/onstar/DAO/DBFunctions.historicalTable : Ljava/lang/String;
    //   76: iconst_1
    //   77: anewarray java/lang/String
    //   80: dup
    //   81: iconst_0
    //   82: ldc_w '*'
    //   85: aastore
    //   86: ldc_w 'user_name=?  AND deleted = 0'
    //   89: iconst_1
    //   90: anewarray java/lang/String
    //   93: dup
    //   94: iconst_0
    //   95: aload_1
    //   96: aastore
    //   97: aconst_null
    //   98: aconst_null
    //   99: aconst_null
    //   100: aconst_null
    //   101: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   104: astore_1
    //   105: aload_1
    //   106: invokeinterface moveToFirst : ()Z
    //   111: ifeq -> 376
    //   114: aload_1
    //   115: bipush #13
    //   117: invokeinterface getString : (I)Ljava/lang/String;
    //   122: ifnull -> 154
    //   125: aload_1
    //   126: bipush #13
    //   128: invokeinterface getString : (I)Ljava/lang/String;
    //   133: invokevirtual length : ()I
    //   136: ifne -> 142
    //   139: goto -> 154
    //   142: aload_1
    //   143: bipush #13
    //   145: invokeinterface getString : (I)Ljava/lang/String;
    //   150: astore_2
    //   151: goto -> 158
    //   154: ldc_w '0'
    //   157: astore_2
    //   158: aload_2
    //   159: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   162: invokevirtual intValue : ()I
    //   165: istore_3
    //   166: new com/roadtrack/onstar/VO/Historical
    //   169: astore_2
    //   170: aload_2
    //   171: aload_1
    //   172: iconst_1
    //   173: invokeinterface getString : (I)Ljava/lang/String;
    //   178: aload_1
    //   179: iconst_2
    //   180: invokeinterface getString : (I)Ljava/lang/String;
    //   185: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   188: aload_1
    //   189: iconst_3
    //   190: invokeinterface getString : (I)Ljava/lang/String;
    //   195: aload_1
    //   196: iconst_4
    //   197: invokeinterface getString : (I)Ljava/lang/String;
    //   202: aload_1
    //   203: iconst_5
    //   204: invokeinterface getString : (I)Ljava/lang/String;
    //   209: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   212: aload_1
    //   213: bipush #6
    //   215: invokeinterface getString : (I)Ljava/lang/String;
    //   220: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   223: aload_1
    //   224: iconst_0
    //   225: invokeinterface getString : (I)Ljava/lang/String;
    //   230: aload_1
    //   231: bipush #7
    //   233: invokeinterface getInt : (I)I
    //   238: aload_1
    //   239: bipush #8
    //   241: invokeinterface getInt : (I)I
    //   246: aload_1
    //   247: bipush #9
    //   249: invokeinterface getString : (I)Ljava/lang/String;
    //   254: aload_1
    //   255: bipush #10
    //   257: invokeinterface getString : (I)Ljava/lang/String;
    //   262: aload_1
    //   263: bipush #11
    //   265: invokeinterface getString : (I)Ljava/lang/String;
    //   270: aload_1
    //   271: bipush #12
    //   273: invokeinterface getString : (I)Ljava/lang/String;
    //   278: aload_1
    //   279: bipush #14
    //   281: invokeinterface getString : (I)Ljava/lang/String;
    //   286: aload_1
    //   287: bipush #15
    //   289: invokeinterface getString : (I)Ljava/lang/String;
    //   294: aload_1
    //   295: bipush #16
    //   297: invokeinterface getString : (I)Ljava/lang/String;
    //   302: aload_1
    //   303: bipush #17
    //   305: invokeinterface getString : (I)Ljava/lang/String;
    //   310: aload_1
    //   311: bipush #18
    //   313: invokeinterface getInt : (I)I
    //   318: aload_1
    //   319: bipush #19
    //   321: invokeinterface getDouble : (I)D
    //   326: aload_1
    //   327: bipush #20
    //   329: invokeinterface getDouble : (I)D
    //   334: aload_1
    //   335: bipush #21
    //   337: invokeinterface getString : (I)Ljava/lang/String;
    //   342: iload_3
    //   343: aload_1
    //   344: bipush #22
    //   346: invokeinterface getString : (I)Ljava/lang/String;
    //   351: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IDDLjava/lang/String;ILjava/lang/String;)V
    //   354: aload #5
    //   356: aload_2
    //   357: invokeinterface add : (Ljava/lang/Object;)Z
    //   362: pop
    //   363: aload_1
    //   364: invokeinterface moveToNext : ()Z
    //   369: istore #4
    //   371: iload #4
    //   373: ifne -> 114
    //   376: aload_1
    //   377: invokeinterface close : ()V
    //   382: goto -> 399
    //   385: astore_1
    //   386: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   389: ldc_w 'Error: getHistoricalList'
    //   392: aload_1
    //   393: invokevirtual getMessage : ()Ljava/lang/String;
    //   396: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   399: aload #5
    //   401: areturn
    //   402: astore_2
    //   403: aload_1
    //   404: invokeinterface close : ()V
    //   409: goto -> 426
    //   412: astore_1
    //   413: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   416: ldc_w 'Error: getHistoricalList'
    //   419: aload_1
    //   420: invokevirtual getMessage : ()Ljava/lang/String;
    //   423: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   426: aload_2
    //   427: athrow
    //   428: astore_1
    //   429: aload_1
    //   430: athrow
    // Exception table:
    //   from	to	target	type
    //   30	68	428	finally
    //   71	105	428	finally
    //   105	114	402	finally
    //   114	139	402	finally
    //   142	151	402	finally
    //   158	371	402	finally
    //   376	382	385	java/lang/Exception
    //   376	382	428	finally
    //   386	399	428	finally
    //   403	409	412	java/lang/Exception
    //   403	409	428	finally
    //   413	426	428	finally
    //   426	428	428	finally
  }
  
  public List<Historical> getHistoricalListRead(String paramString1, String paramString2) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #5
    //   9: aload_0
    //   10: invokevirtual getReadableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   13: astore #6
    //   15: aload_1
    //   16: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   19: invokevirtual trim : ()Ljava/lang/String;
    //   22: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   25: astore_1
    //   26: aload_2
    //   27: ifnull -> 71
    //   30: aload #6
    //   32: getstatic com/roadtrack/onstar/DAO/DBFunctions.historicalTable : Ljava/lang/String;
    //   35: iconst_1
    //   36: anewarray java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w '*'
    //   44: aastore
    //   45: ldc_w 'user_name=?  AND vehicle_id=?  AND deleted = 0  AND readnotification!=1 '
    //   48: iconst_2
    //   49: anewarray java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_1
    //   55: aastore
    //   56: dup
    //   57: iconst_1
    //   58: aload_2
    //   59: aastore
    //   60: aconst_null
    //   61: aconst_null
    //   62: aconst_null
    //   63: aconst_null
    //   64: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   67: astore_1
    //   68: goto -> 105
    //   71: aload #6
    //   73: getstatic com/roadtrack/onstar/DAO/DBFunctions.historicalTable : Ljava/lang/String;
    //   76: iconst_1
    //   77: anewarray java/lang/String
    //   80: dup
    //   81: iconst_0
    //   82: ldc_w '*'
    //   85: aastore
    //   86: ldc_w 'user_name=?  AND deleted = 0  AND readnotification!=1 '
    //   89: iconst_1
    //   90: anewarray java/lang/String
    //   93: dup
    //   94: iconst_0
    //   95: aload_1
    //   96: aastore
    //   97: aconst_null
    //   98: aconst_null
    //   99: aconst_null
    //   100: aconst_null
    //   101: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   104: astore_1
    //   105: aload_1
    //   106: invokeinterface moveToFirst : ()Z
    //   111: ifeq -> 376
    //   114: aload_1
    //   115: bipush #13
    //   117: invokeinterface getString : (I)Ljava/lang/String;
    //   122: ifnull -> 154
    //   125: aload_1
    //   126: bipush #13
    //   128: invokeinterface getString : (I)Ljava/lang/String;
    //   133: invokevirtual length : ()I
    //   136: ifne -> 142
    //   139: goto -> 154
    //   142: aload_1
    //   143: bipush #13
    //   145: invokeinterface getString : (I)Ljava/lang/String;
    //   150: astore_2
    //   151: goto -> 158
    //   154: ldc_w '0'
    //   157: astore_2
    //   158: aload_2
    //   159: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   162: invokevirtual intValue : ()I
    //   165: istore_3
    //   166: new com/roadtrack/onstar/VO/Historical
    //   169: astore_2
    //   170: aload_2
    //   171: aload_1
    //   172: iconst_1
    //   173: invokeinterface getString : (I)Ljava/lang/String;
    //   178: aload_1
    //   179: iconst_2
    //   180: invokeinterface getString : (I)Ljava/lang/String;
    //   185: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   188: aload_1
    //   189: iconst_3
    //   190: invokeinterface getString : (I)Ljava/lang/String;
    //   195: aload_1
    //   196: iconst_4
    //   197: invokeinterface getString : (I)Ljava/lang/String;
    //   202: aload_1
    //   203: iconst_5
    //   204: invokeinterface getString : (I)Ljava/lang/String;
    //   209: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   212: aload_1
    //   213: bipush #6
    //   215: invokeinterface getString : (I)Ljava/lang/String;
    //   220: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   223: aload_1
    //   224: iconst_0
    //   225: invokeinterface getString : (I)Ljava/lang/String;
    //   230: aload_1
    //   231: bipush #7
    //   233: invokeinterface getInt : (I)I
    //   238: aload_1
    //   239: bipush #8
    //   241: invokeinterface getInt : (I)I
    //   246: aload_1
    //   247: bipush #9
    //   249: invokeinterface getString : (I)Ljava/lang/String;
    //   254: aload_1
    //   255: bipush #10
    //   257: invokeinterface getString : (I)Ljava/lang/String;
    //   262: aload_1
    //   263: bipush #11
    //   265: invokeinterface getString : (I)Ljava/lang/String;
    //   270: aload_1
    //   271: bipush #12
    //   273: invokeinterface getString : (I)Ljava/lang/String;
    //   278: aload_1
    //   279: bipush #14
    //   281: invokeinterface getString : (I)Ljava/lang/String;
    //   286: aload_1
    //   287: bipush #15
    //   289: invokeinterface getString : (I)Ljava/lang/String;
    //   294: aload_1
    //   295: bipush #16
    //   297: invokeinterface getString : (I)Ljava/lang/String;
    //   302: aload_1
    //   303: bipush #17
    //   305: invokeinterface getString : (I)Ljava/lang/String;
    //   310: aload_1
    //   311: bipush #18
    //   313: invokeinterface getInt : (I)I
    //   318: aload_1
    //   319: bipush #19
    //   321: invokeinterface getDouble : (I)D
    //   326: aload_1
    //   327: bipush #20
    //   329: invokeinterface getDouble : (I)D
    //   334: aload_1
    //   335: bipush #21
    //   337: invokeinterface getString : (I)Ljava/lang/String;
    //   342: iload_3
    //   343: aload_1
    //   344: bipush #22
    //   346: invokeinterface getString : (I)Ljava/lang/String;
    //   351: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IDDLjava/lang/String;ILjava/lang/String;)V
    //   354: aload #5
    //   356: aload_2
    //   357: invokeinterface add : (Ljava/lang/Object;)Z
    //   362: pop
    //   363: aload_1
    //   364: invokeinterface moveToNext : ()Z
    //   369: istore #4
    //   371: iload #4
    //   373: ifne -> 114
    //   376: aload_1
    //   377: invokeinterface close : ()V
    //   382: goto -> 399
    //   385: astore_1
    //   386: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   389: ldc_w 'Error: getHistoricalList'
    //   392: aload_1
    //   393: invokevirtual getMessage : ()Ljava/lang/String;
    //   396: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   399: aload #5
    //   401: areturn
    //   402: astore_2
    //   403: aload_1
    //   404: invokeinterface close : ()V
    //   409: goto -> 426
    //   412: astore_1
    //   413: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   416: ldc_w 'Error: getHistoricalList'
    //   419: aload_1
    //   420: invokevirtual getMessage : ()Ljava/lang/String;
    //   423: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   426: aload_2
    //   427: athrow
    //   428: astore_1
    //   429: aload_1
    //   430: athrow
    // Exception table:
    //   from	to	target	type
    //   30	68	428	finally
    //   71	105	428	finally
    //   105	114	402	finally
    //   114	139	402	finally
    //   142	151	402	finally
    //   158	371	402	finally
    //   376	382	385	java/lang/Exception
    //   376	382	428	finally
    //   386	399	428	finally
    //   403	409	412	java/lang/Exception
    //   403	409	428	finally
    //   413	426	428	finally
    //   426	428	428	finally
  }
  
  public String[] getLastDevicePoint(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getReadableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   4: astore_2
    //   5: aload_2
    //   6: getstatic com/roadtrack/onstar/DAO/DBFunctions.findmePointsTable : Ljava/lang/String;
    //   9: iconst_1
    //   10: anewarray java/lang/String
    //   13: dup
    //   14: iconst_0
    //   15: ldc_w '*'
    //   18: aastore
    //   19: ldc_w 'id_device =? '
    //   22: iconst_1
    //   23: anewarray java/lang/String
    //   26: dup
    //   27: iconst_0
    //   28: aload_1
    //   29: aastore
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   37: astore_2
    //   38: aload_2
    //   39: ifnull -> 160
    //   42: aload_2
    //   43: invokeinterface getCount : ()I
    //   48: ifle -> 160
    //   51: aload_2
    //   52: invokeinterface moveToFirst : ()Z
    //   57: pop
    //   58: new java/lang/StringBuilder
    //   61: astore_1
    //   62: aload_1
    //   63: invokespecial <init> : ()V
    //   66: aload_1
    //   67: aload_2
    //   68: aload_2
    //   69: ldc_w 'longitude'
    //   72: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   77: invokeinterface getString : (I)Ljava/lang/String;
    //   82: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   85: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload_1
    //   90: ldc_w ','
    //   93: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: aload_1
    //   98: aload_2
    //   99: aload_2
    //   100: ldc_w 'latitude'
    //   103: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   108: invokeinterface getString : (I)Ljava/lang/String;
    //   113: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: aload_1
    //   121: invokevirtual toString : ()Ljava/lang/String;
    //   124: ldc_w ','
    //   127: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   130: astore_1
    //   131: goto -> 162
    //   134: astore_1
    //   135: aload_2
    //   136: invokeinterface close : ()V
    //   141: goto -> 158
    //   144: astore_2
    //   145: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   148: ldc_w 'Error: getLastDevicePoint'
    //   151: aload_2
    //   152: invokevirtual getMessage : ()Ljava/lang/String;
    //   155: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   158: aload_1
    //   159: athrow
    //   160: aconst_null
    //   161: astore_1
    //   162: aload_2
    //   163: invokeinterface close : ()V
    //   168: goto -> 185
    //   171: astore_2
    //   172: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   175: ldc_w 'Error: getLastDevicePoint'
    //   178: aload_2
    //   179: invokevirtual getMessage : ()Ljava/lang/String;
    //   182: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   185: aload_1
    //   186: areturn
    //   187: astore_1
    //   188: aload_1
    //   189: athrow
    // Exception table:
    //   from	to	target	type
    //   5	38	187	finally
    //   42	131	134	finally
    //   135	141	144	java/lang/Exception
    //   135	141	187	finally
    //   145	158	187	finally
    //   158	160	187	finally
    //   162	168	171	java/lang/Exception
    //   162	168	187	finally
    //   172	185	187	finally
  }
  
  public String[] getLastFindMe(String paramString) {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "0";
    arrayOfString[1] = "0";
    arrayOfString[2] = "";
    arrayOfString[3] = "0";
    try {
      SQLiteDatabase sQLiteDatabase = getReadableDatabase();
      String str1 = Utilities.Crypt("0");
      String str2 = Utilities.Crypt("0.0");
      Cursor cursor = sQLiteDatabase.query(historicalTable, new String[] { "*" }, "vehicle_id=? AND Lat != ? AND Lng != ? AND Lat != ? AND Lng != ? AND Lat != '' AND Lng != '' ", new String[] { paramString, str1, str1, str2, str2 }, null, null, "date_time DESC", null);
      if (cursor.moveToFirst()) {
        arrayOfString[0] = Utilities.Decrypt(cursor.getString(5));
        arrayOfString[1] = Utilities.Decrypt(cursor.getString(6));
        arrayOfString[2] = cursor.getString(12);
        arrayOfString[3] = cursor.getString(21);
      } 
      cursor.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
    } 
    return arrayOfString;
  }
  
  public List<Historical> getLastFindMeFromHistorical(String paramString1, String paramString2) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #4
    //   9: aload_0
    //   10: invokevirtual getReadableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   13: astore #5
    //   15: aload_1
    //   16: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   19: invokevirtual trim : ()Ljava/lang/String;
    //   22: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   25: astore_1
    //   26: aload_2
    //   27: ifnull -> 75
    //   30: aload #5
    //   32: getstatic com/roadtrack/onstar/DAO/DBFunctions.historicalTable : Ljava/lang/String;
    //   35: iconst_1
    //   36: anewarray java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w '*'
    //   44: aastore
    //   45: ldc_w 'user_name =? AND vehicle_id =? AND deleted = 0 AND completion_code = 15'
    //   48: iconst_2
    //   49: anewarray java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_1
    //   55: aastore
    //   56: dup
    //   57: iconst_1
    //   58: aload_2
    //   59: aastore
    //   60: aconst_null
    //   61: aconst_null
    //   62: ldc_w 'date_time'
    //   65: ldc_w '1'
    //   68: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   71: astore_1
    //   72: goto -> 113
    //   75: aload #5
    //   77: getstatic com/roadtrack/onstar/DAO/DBFunctions.historicalTable : Ljava/lang/String;
    //   80: iconst_1
    //   81: anewarray java/lang/String
    //   84: dup
    //   85: iconst_0
    //   86: ldc_w '*'
    //   89: aastore
    //   90: ldc_w 'user_name =? AND deleted = 0 AND completion_code = 15'
    //   93: iconst_1
    //   94: anewarray java/lang/String
    //   97: dup
    //   98: iconst_0
    //   99: aload_1
    //   100: aastore
    //   101: aconst_null
    //   102: aconst_null
    //   103: ldc_w 'date_time'
    //   106: ldc_w '1'
    //   109: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   112: astore_1
    //   113: aload_1
    //   114: invokeinterface moveToFirst : ()Z
    //   119: ifeq -> 371
    //   122: aload_1
    //   123: bipush #13
    //   125: invokeinterface getString : (I)Ljava/lang/String;
    //   130: ifnull -> 162
    //   133: aload_1
    //   134: bipush #13
    //   136: invokeinterface getString : (I)Ljava/lang/String;
    //   141: invokevirtual length : ()I
    //   144: ifne -> 150
    //   147: goto -> 162
    //   150: aload_1
    //   151: bipush #13
    //   153: invokeinterface getString : (I)Ljava/lang/String;
    //   158: astore_2
    //   159: goto -> 166
    //   162: ldc_w '0'
    //   165: astore_2
    //   166: aload_2
    //   167: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   170: invokevirtual intValue : ()I
    //   173: istore_3
    //   174: new com/roadtrack/onstar/VO/Historical
    //   177: astore_2
    //   178: aload_2
    //   179: aload_1
    //   180: iconst_1
    //   181: invokeinterface getString : (I)Ljava/lang/String;
    //   186: aload_1
    //   187: iconst_2
    //   188: invokeinterface getString : (I)Ljava/lang/String;
    //   193: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   196: aload_1
    //   197: iconst_3
    //   198: invokeinterface getString : (I)Ljava/lang/String;
    //   203: aload_1
    //   204: iconst_4
    //   205: invokeinterface getString : (I)Ljava/lang/String;
    //   210: aload_1
    //   211: iconst_5
    //   212: invokeinterface getString : (I)Ljava/lang/String;
    //   217: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   220: aload_1
    //   221: bipush #6
    //   223: invokeinterface getString : (I)Ljava/lang/String;
    //   228: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   231: aload_1
    //   232: iconst_0
    //   233: invokeinterface getString : (I)Ljava/lang/String;
    //   238: aload_1
    //   239: bipush #7
    //   241: invokeinterface getInt : (I)I
    //   246: aload_1
    //   247: bipush #8
    //   249: invokeinterface getInt : (I)I
    //   254: aload_1
    //   255: bipush #9
    //   257: invokeinterface getString : (I)Ljava/lang/String;
    //   262: aload_1
    //   263: bipush #10
    //   265: invokeinterface getString : (I)Ljava/lang/String;
    //   270: aload_1
    //   271: bipush #11
    //   273: invokeinterface getString : (I)Ljava/lang/String;
    //   278: aload_1
    //   279: bipush #12
    //   281: invokeinterface getString : (I)Ljava/lang/String;
    //   286: aload_1
    //   287: bipush #14
    //   289: invokeinterface getString : (I)Ljava/lang/String;
    //   294: aload_1
    //   295: bipush #15
    //   297: invokeinterface getString : (I)Ljava/lang/String;
    //   302: aload_1
    //   303: bipush #16
    //   305: invokeinterface getString : (I)Ljava/lang/String;
    //   310: aload_1
    //   311: bipush #17
    //   313: invokeinterface getString : (I)Ljava/lang/String;
    //   318: aload_1
    //   319: bipush #18
    //   321: invokeinterface getInt : (I)I
    //   326: aload_1
    //   327: bipush #19
    //   329: invokeinterface getDouble : (I)D
    //   334: aload_1
    //   335: bipush #20
    //   337: invokeinterface getDouble : (I)D
    //   342: aload_1
    //   343: bipush #21
    //   345: invokeinterface getString : (I)Ljava/lang/String;
    //   350: iload_3
    //   351: aload_1
    //   352: bipush #12
    //   354: invokeinterface getString : (I)Ljava/lang/String;
    //   359: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IDDLjava/lang/String;ILjava/lang/String;)V
    //   362: aload #4
    //   364: aload_2
    //   365: invokeinterface add : (Ljava/lang/Object;)Z
    //   370: pop
    //   371: aload_1
    //   372: invokeinterface close : ()V
    //   377: goto -> 394
    //   380: astore_1
    //   381: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   384: ldc_w 'Error: getLastFindMeFromHistorical'
    //   387: aload_1
    //   388: invokevirtual getMessage : ()Ljava/lang/String;
    //   391: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   394: aload #4
    //   396: areturn
    //   397: astore_2
    //   398: aload_1
    //   399: invokeinterface close : ()V
    //   404: goto -> 421
    //   407: astore_1
    //   408: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   411: ldc_w 'Error: getLastFindMeFromHistorical'
    //   414: aload_1
    //   415: invokevirtual getMessage : ()Ljava/lang/String;
    //   418: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   421: aload_2
    //   422: athrow
    //   423: astore_1
    //   424: aload_1
    //   425: athrow
    // Exception table:
    //   from	to	target	type
    //   30	72	423	finally
    //   75	113	423	finally
    //   113	147	397	finally
    //   150	159	397	finally
    //   166	371	397	finally
    //   371	377	380	java/lang/Exception
    //   371	377	423	finally
    //   381	394	423	finally
    //   398	404	407	java/lang/Exception
    //   398	404	423	finally
    //   408	421	423	finally
    //   421	423	423	finally
  }
  
  public PIDVO getLastPid(String paramString) {
    Exception exception2;
    PIDVO pIDVO2 = null;
    String str = null;
    PIDVO pIDVO1 = pIDVO2;
    try {
      DBHandler dBHandler = new DBHandler();
      pIDVO1 = pIDVO2;
      this(this._ctx);
      pIDVO1 = pIDVO2;
      SQLiteDatabase sQLiteDatabase = dBHandler.getWritableDatabase();
      pIDVO1 = pIDVO2;
      paramString = String.valueOf(paramString);
      pIDVO1 = pIDVO2;
      Cursor cursor = sQLiteDatabase.query("pid_historical", new String[] { 
            "id_pid", "id_device", "id_message", "odometer", "gas", "oil", "oil_status", "battery_level", "tire_status_fr", "tire_fr", 
            "tire_status_fl", "tire_fl", "tire_status_rr", "tire_rr", "tire_status_rl", "tire_rl", "event_date", "modelo", "version", "year", 
            "placa", "tpms", "m300", "autonomy_km", "autonomy_status", "autonomy_text" }, "id_device = ?", new String[] { paramString }, null, null, null, null);
      paramString = str;
      if (cursor != null) {
        paramString = str;
        pIDVO1 = pIDVO2;
        if (cursor.getCount() > 0L) {
          paramString = str;
          pIDVO1 = pIDVO2;
          if (cursor.moveToFirst()) {
            pIDVO1 = pIDVO2;
            PIDVO pIDVO = new PIDVO();
            pIDVO1 = pIDVO2;
            this(this._ctx);
            try {
              pIDVO.setPidId(Long.valueOf(cursor.getString(0)).longValue());
              pIDVO.setDeviceId(Integer.valueOf(cursor.getString(1)).intValue());
              pIDVO.setMessageId(Integer.valueOf(cursor.getString(2)).intValue());
              pIDVO.setOdometer(Utilities.Decrypt(cursor.getString(3)));
              pIDVO.setGas(Utilities.Decrypt(cursor.getString(4)));
              pIDVO.setOilValue(Utilities.Decrypt(cursor.getString(5)));
              pIDVO.setOilStatus(Utilities.Decrypt(cursor.getString(6)));
              pIDVO.setBatterylevel(Utilities.Decrypt(cursor.getString(7)));
              pIDVO.setTire_status_fr(Utilities.Decrypt(cursor.getString(8)));
              pIDVO.setTire_fr(Utilities.Decrypt(cursor.getString(9)));
              pIDVO.setTire_status_fl(Utilities.Decrypt(cursor.getString(10)));
              pIDVO.setTire_fl(Utilities.Decrypt(cursor.getString(11)));
              pIDVO.setTire_status_rr(Utilities.Decrypt(cursor.getString(12)));
              pIDVO.setTire_rr(Utilities.Decrypt(cursor.getString(13)));
              pIDVO.setTire_status_rl(Utilities.Decrypt(cursor.getString(14)));
              pIDVO.setTire_rl(Utilities.Decrypt(cursor.getString(15)));
              pIDVO.setEventDate(cursor.getString(16));
              pIDVO.setModelo(Utilities.Decrypt(cursor.getString(17)));
              pIDVO.setVersion(Utilities.Decrypt(cursor.getString(18)));
              pIDVO.setYear(Utilities.Decrypt(cursor.getString(19)));
              pIDVO.setPlaca(Utilities.Decrypt(cursor.getString(20)));
              pIDVO.setTPMSText(Utilities.Decrypt(cursor.getString(21)));
              pIDVO.setIsM300(Utilities.Decrypt(cursor.getString(22)));
              pIDVO.setAutonomy_km(Utilities.Decrypt(cursor.getString(23)));
              pIDVO.setAutonomy_status(Utilities.Decrypt(cursor.getString(24)));
              pIDVO.setAutonomy_text(Utilities.Decrypt(cursor.getString(25)));
            } catch (Exception exception) {
              pIDVO1 = pIDVO;
              exception1 = exception;
            } 
          } 
        } 
      } 
      exception2 = exception1;
      cursor.close();
      exception2 = exception1;
      sQLiteDatabase.close();
      exception2 = exception1;
      dBHandler.close();
    } catch (Exception exception1) {
      Utilities.escribeArchivo(TAG, exception1);
      exception1 = exception2;
    } 
    return (PIDVO)exception1;
  }
  
  public String getLastUser() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getReadableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   4: astore_1
    //   5: aload_1
    //   6: ldc_w 'activity_log,configuration'
    //   9: iconst_5
    //   10: anewarray java/lang/String
    //   13: dup
    //   14: iconst_0
    //   15: ldc_w 'activity_log.user'
    //   18: aastore
    //   19: dup
    //   20: iconst_1
    //   21: ldc_w 'activity_log.language'
    //   24: aastore
    //   25: dup
    //   26: iconst_2
    //   27: ldc_w 'activity_log.region'
    //   30: aastore
    //   31: dup
    //   32: iconst_3
    //   33: ldc_w 'configuration.password'
    //   36: aastore
    //   37: dup
    //   38: iconst_4
    //   39: ldc_w 'configuration.save_access'
    //   42: aastore
    //   43: ldc_w 'activity_log.user = configuration.user '
    //   46: aconst_null
    //   47: aconst_null
    //   48: aconst_null
    //   49: ldc_w 'activity_log.id_log DESC'
    //   52: aconst_null
    //   53: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   56: astore_2
    //   57: aload_2
    //   58: ifnull -> 122
    //   61: aload_2
    //   62: invokeinterface getCount : ()I
    //   67: ifle -> 122
    //   70: aload_2
    //   71: invokeinterface moveToFirst : ()Z
    //   76: pop
    //   77: aload_2
    //   78: aload_2
    //   79: ldc_w 'user'
    //   82: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   87: invokeinterface getString : (I)Ljava/lang/String;
    //   92: astore_1
    //   93: goto -> 126
    //   96: astore_1
    //   97: aload_2
    //   98: invokeinterface close : ()V
    //   103: goto -> 120
    //   106: astore_2
    //   107: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   110: ldc_w 'Error: getLastUser'
    //   113: aload_2
    //   114: invokevirtual getMessage : ()Ljava/lang/String;
    //   117: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   120: aload_1
    //   121: athrow
    //   122: ldc_w ''
    //   125: astore_1
    //   126: aload_2
    //   127: invokeinterface close : ()V
    //   132: goto -> 149
    //   135: astore_2
    //   136: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   139: ldc_w 'Error: getLastUser'
    //   142: aload_2
    //   143: invokevirtual getMessage : ()Ljava/lang/String;
    //   146: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   149: aload_1
    //   150: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   153: areturn
    //   154: astore_1
    //   155: aload_1
    //   156: athrow
    // Exception table:
    //   from	to	target	type
    //   5	57	154	finally
    //   61	93	96	finally
    //   97	103	106	java/lang/Exception
    //   97	103	154	finally
    //   107	120	154	finally
    //   120	122	154	finally
    //   126	132	135	java/lang/Exception
    //   126	132	154	finally
    //   136	149	154	finally
  }
  
  public PushNotificationsVO getMapUpdateMapa() {
    null = new PushNotificationsVO();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(mapUpdateTable, new String[] { "*" }, "id_mapupdate =?", new String[] { "1" }, null, null, null, null);
      try {
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
          null.setOldVersion(cursor.getString(cursor.getColumnIndex("oldVersion")));
          null.setNewVersion(cursor.getString(cursor.getColumnIndex("newVersion")));
          null.setFileName(cursor.getString(cursor.getColumnIndex("fileName")));
        } 
        return null;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getMapUpdateMapa", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public MapUpdateVO getMapUpdateMapaData(String paramString) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(mapUpdateTableV2, new String[] { "*" }, "id_p8_serial =? ", new String[] { paramString }, null, null, null, null);
      try {
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
          MapUpdateVO mapUpdateVO = new MapUpdateVO();
          this();
          mapUpdateVO.setIdMapUpdate(cursor.getInt(cursor.getColumnIndex("id_map_update")));
          mapUpdateVO.setP8IdSerial(cursor.getString(cursor.getColumnIndex("id_p8_serial")));
          mapUpdateVO.setP8MapVersion(cursor.getString(cursor.getColumnIndex("p8_map_version")));
          mapUpdateVO.setServerFileName(cursor.getString(cursor.getColumnIndex("server_file_name")));
          mapUpdateVO.setServerMapVersion(cursor.getString(cursor.getColumnIndex("server_map_version")));
          mapUpdateVO.setUpgradeOldversionNewversion(cursor.getString(cursor.getColumnIndex("upgrade_version")));
          mapUpdateVO.setFileMapOnParts(cursor.getInt(cursor.getColumnIndex("file_map_on_parts")));
          mapUpdateVO.setFileMapStatus(cursor.getInt(cursor.getColumnIndex("file_map_status")));
          mapUpdateVO.setUserWantsUpdrade(cursor.getInt(cursor.getColumnIndex("user_wants_upgrade")));
        } else {
          paramString = null;
        } 
        return (MapUpdateVO)paramString;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getMapUpdateMapa", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public PushNotificationsVO getMapUpdateMapaPart() {
    null = new PushNotificationsVO();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(mapUpdateTable, new String[] { "*" }, "id_mapupdate =?", new String[] { "100" }, null, null, null, null);
      try {
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
          null.setOldVersion(cursor.getString(cursor.getColumnIndex("oldVersion")));
          null.setNewVersion(cursor.getString(cursor.getColumnIndex("newVersion")));
          null.setFileName(cursor.getString(cursor.getColumnIndex("fileName")));
        } 
        return null;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getMapUpdateMapaPart", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<FavoritesHistoryVO> getMaxDateFavorites(String paramString1, String paramString2) {
    ArrayList<FavoritesHistoryVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query("favorites_history", new String[] { "*" }, "type_item =? AND device_id =? AND type_poi <> 2  ", new String[] { paramString1, paramString2 }, null, null, "date", null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            FavoritesHistoryVO favoritesHistoryVO = new FavoritesHistoryVO();
            this(cursor.getInt(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), Utilities.Decrypt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), Utilities.Decrypt(cursor.getString(8)), cursor.getString(9), cursor.getString(10));
            arrayList.add(favoritesHistoryVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getMaxDateFavorites", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public ArrayList<FavoritesHistoryVO> getNavFavoriteHistory(String paramString) {
    ArrayList<FavoritesHistoryVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query("favorites_history", new String[] { "*" }, "type_item=? ", new String[] { paramString }, null, null, "name ASC", null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            FavoritesHistoryVO favoritesHistoryVO = new FavoritesHistoryVO();
            this(cursor.getInt(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), Utilities.Decrypt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), Utilities.Decrypt(cursor.getString(8)), cursor.getString(9), cursor.getString(10));
            arrayList.add(favoritesHistoryVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getNavFavoriteHistory", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public ArrayList<FavoritesHistoryVO> getNoSyncFavorites(String paramString) {
    ArrayList<FavoritesHistoryVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query("favorites_history", new String[] { "*" }, "type_item=? AND type_poi<>'0' AND type_poi<>'2'", new String[] { paramString }, null, null, "name COLLATE NOCASE", null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            FavoritesHistoryVO favoritesHistoryVO = new FavoritesHistoryVO();
            this(cursor.getInt(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), Utilities.Decrypt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), Utilities.Decrypt(cursor.getString(8)), cursor.getString(9), cursor.getString(10));
            arrayList.add(favoritesHistoryVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getNoSyncFavorites", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public String getRenewalDialogDateExpire(String paramString) {
    Exception exception2;
    String str2 = null;
    String str3 = null;
    String str1 = str2;
    try {
      Cursor cursor = getWritableDatabase().query("renewal_dialog_status", new String[] { "*" }, "deviceId = ?", new String[] { paramString }, null, null, null, null);
      paramString = str3;
      try {
      
      } finally {
        try {
          cursor.close();
        } catch (Exception exception4) {
          Exception exception3 = exception;
          Utilities.escribeArchivo(TAG, "Error: getRenewalDialogStatus", exception4.getMessage());
        } 
        exception2 = exception;
      } 
    } catch (Exception exception1) {
      Utilities.escribeArchivo(TAG, "Error: getRenewalDialogStatus", exception1.getMessage());
      exception1 = exception2;
    } 
    return (String)exception1;
  }
  
  public String getRenewalDialogDaysExpire(String paramString) {
    Exception exception2;
    String str2 = null;
    String str3 = null;
    String str1 = str2;
    try {
      Cursor cursor = getWritableDatabase().query("renewal_dialog_status", new String[] { "*" }, "deviceId = ?", new String[] { paramString }, null, null, null, null);
      paramString = str3;
      try {
      
      } finally {
        try {
          cursor.close();
        } catch (Exception exception4) {
          Exception exception3 = exception;
          Utilities.escribeArchivo(TAG, "Error: getRenewalDialogStatus", exception4.getMessage());
        } 
        exception2 = exception;
      } 
    } catch (Exception exception1) {
      Utilities.escribeArchivo(TAG, "Error: getRenewalDialogStatus", exception1.getMessage());
      exception1 = exception2;
    } 
    return (String)exception1;
  }
  
  public String getRenewalDialogStatus(String paramString) {
    Exception exception2;
    String str2 = null;
    String str3 = null;
    String str1 = str2;
    try {
      Cursor cursor = getWritableDatabase().query("renewal_dialog_status", new String[] { "*" }, "deviceId = ?", new String[] { paramString }, null, null, null, null);
      paramString = str3;
      try {
      
      } finally {
        try {
          cursor.close();
        } catch (Exception exception4) {
          Exception exception3 = exception;
          Utilities.escribeArchivo(TAG, "Error: getRenewalDialogStatus", exception4.getMessage());
        } 
        exception2 = exception;
      } 
    } catch (Exception exception1) {
      Utilities.escribeArchivo(TAG, "Error: getRenewalDialogStatus", exception1.getMessage());
      exception1 = exception2;
    } 
    return (String)exception1;
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public ArrayList<FavoritesHistoryVO> getSelectedFavorite(String paramString1, String paramString2, String paramString3) {
    null = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      paramString3 = Utilities.Crypt(paramString3);
      Cursor cursor = sQLiteDatabase.query("favorites_history", new String[] { "*" }, "name =?  AND device_id =? ", new String[] { paramString3, paramString2 }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            FavoritesHistoryVO favoritesHistoryVO = new FavoritesHistoryVO();
            this(cursor.getInt(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), Utilities.Decrypt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), Utilities.Decrypt(cursor.getString(8)), cursor.getString(9), cursor.getString(10));
            null.add(favoritesHistoryVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return null;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getSelectedFavorite", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public VehicleCatalogVO getSelectedVehicle(String paramString) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append(" ='T' AND ");
    stringBuilder.append(KEY_VEHICLE_USER);
    stringBuilder.append(" =?  ");
    String str = stringBuilder.toString();
    try {
      paramString = Utilities.Crypt(paramString.toLowerCase().trim());
      Cursor cursor = sQLiteDatabase.query(vehicleCatalog, new String[] { "*" }, str, new String[] { paramString }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          VehicleCatalogVO vehicleCatalogVO = new VehicleCatalogVO();
          this(cursor.getString(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(7), cursor.getString(11));
        } else {
          paramString = null;
        } 
        return (VehicleCatalogVO)paramString;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getSelectedVehicle", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<VehicleCatalogVO> getSelectedVehiclesCatalog(String paramString) {
    ArrayList<VehicleCatalogVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_USER);
    stringBuilder.append(" =?");
    String str = stringBuilder.toString();
    try {
      paramString = Utilities.Crypt(paramString.toLowerCase().trim());
      Cursor cursor = sQLiteDatabase.query(vehicleCatalog, new String[] { "*" }, str, new String[] { paramString }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            VehicleCatalogVO vehicleCatalogVO = new VehicleCatalogVO();
            this(cursor.getString(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(11));
            arrayList.add(vehicleCatalogVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getVehicleCatalog", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public String getStatusVehicleDTCSelector(String paramString) {
    String str1;
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    String str3 = "0";
    String str2 = str3;
    try {
      StringBuilder stringBuilder = new StringBuilder();
      str2 = str3;
      this();
      str2 = str3;
      stringBuilder.append("SELECT * FROM ");
      str2 = str3;
      stringBuilder.append(vehicleCatalog);
      str2 = str3;
      stringBuilder.append(" WHERE ");
      str2 = str3;
      stringBuilder.append(KEY_VEHICLE_DEVICEID);
      str2 = str3;
      stringBuilder.append(" = ?");
      str2 = str3;
      Cursor cursor = sQLiteDatabase.rawQuery(stringBuilder.toString(), new String[] { paramString });
      paramString = str3;
      String str = str3;
      try {
      
      } finally {
        try {
          cursor.close();
        } catch (Exception exception) {
          str2 = str;
          Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception.getMessage());
        } 
        str2 = str;
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception.getMessage());
      str1 = str2;
    } 
    return str1;
  }
  
  public float getTimeGTM(String paramString1, String paramString2, Context paramContext) {
    float f1;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(" =? AND ");
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append(" !=? ");
    String str = stringBuilder.toString();
    Cursor cursor2 = getReadableDatabase().query(vehicleCatalog, new String[] { KEY_VEHICLE_GTM }, str, new String[] { paramString2, "L" }, null, null, null, null);
    boolean bool = cursor2.moveToFirst();
    float f3 = 0.0F;
    if (bool) {
      f1 = Float.parseFloat(cursor2.getString(0));
    } else {
      f1 = 0.0F;
    } 
    cursor2.close();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    paramString1 = Utilities.Crypt(paramString1.toLowerCase().trim());
    Cursor cursor1 = sQLiteDatabase.query("configuration", new String[] { "TimeOfSet" }, " user =? ", new String[] { paramString1 }, null, null, null, null);
    float f2 = f3;
    if (cursor1.moveToFirst()) {
      String str1 = cursor1.getString(0);
      try {
        int i = Integer.parseInt(str1.replace("+", ""));
        f2 = i;
      } catch (Exception exception) {
        f2 = f3;
      } 
    } 
    cursor1.close();
    return f1 - f2;
  }
  
  public UserPreferenceVO getUserPreference(String paramString) {
    SQLiteDatabase sQLiteDatabase;
    null = new UserPreferenceVO();
    String str = Utilities.Crypt(paramString.toLowerCase().trim());
    try {
      sQLiteDatabase = getWritableDatabase();
    } catch (Exception null) {
      sQLiteDatabase = (new DBFunctions(this._ctx)).getWritableDatabase();
    } 
    try {
      Cursor cursor = sQLiteDatabase.query(configTable, new String[] { "*" }, "user =? ", new String[] { str }, null, null, null, null);
    } finally {
      if (exception != null)
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getUserPreference", exception1.getMessage());
        }  
    } 
  }
  
  public UserPreferenceVO getUserPreferenceSaveAcccess(String paramString1, String paramString2) {
    UserPreferenceVO userPreferenceVO = new UserPreferenceVO();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      String str = configTable;
      paramString1 = Utilities.Crypt(paramString1.toLowerCase().trim());
      Cursor cursor = sQLiteDatabase.query(str, new String[] { "*" }, "LTRIM(RTRIM(user)) =? ", new String[] { paramString1 }, null, null, null, null);
      try {
        if (cursor.getCount() > 0) {
          UserPreferenceVO userPreferenceVO1 = userPreferenceVO;
          if (cursor.moveToFirst()) {
            userPreferenceVO.setLanguage(Utilities.Decrypt(cursor.getString(1)));
            userPreferenceVO.setPhone_number(Utilities.Decrypt(cursor.getString(2)));
            userPreferenceVO.setNotifications(cursor.getString(3));
            userPreferenceVO.setUser(Utilities.Decrypt(cursor.getString(4)));
            userPreferenceVO.setMagic_spell(Utilities.Decrypt(cursor.getString(5)));
            userPreferenceVO.setTerms_conditions(cursor.getString(6));
            userPreferenceVO.setSave_access(cursor.getString(7));
            userPreferenceVO.setCity(cursor.getString(8));
            userPreferenceVO.setCountry(Utilities.Decrypt(cursor.getString(9)));
            userPreferenceVO.setTimeOfSet(paramString2);
            userPreferenceVO1 = userPreferenceVO;
          } 
        } else {
          paramString1 = null;
        } 
        return (UserPreferenceVO)paramString1;
      } finally {
        try {
          cursor.close();
        } catch (Exception exception) {
          Utilities.escribeArchivo(TAG, "Error: getUserPfeferenceSaveAccess", exception.getMessage());
        } 
      } 
    } finally {}
  }
  
  public VehicleCatalogVO getVehicleByDeviceID(String paramString1, String paramString2) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_USER);
    stringBuilder.append(" =? AND ");
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(" =? AND ");
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append(" !=?");
    String str = stringBuilder.toString();
    try {
      paramString1 = Utilities.Crypt(paramString1.toLowerCase().trim());
      Cursor cursor = sQLiteDatabase.query(vehicleCatalog, new String[] { "*" }, str, new String[] { paramString1, paramString2, "L" }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          VehicleCatalogVO vehicleCatalogVO = new VehicleCatalogVO();
          this(cursor.getString(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(7), cursor.getString(11), cursor.getString(13), cursor.getString(14), cursor.getString(15));
        } else {
          paramString1 = null;
        } 
        return (VehicleCatalogVO)paramString1;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getVehicleCatalog", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public String getVehicleDtcStatus(String paramString) {
    Exception exception2;
    String str2 = "0";
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(" =? AND ");
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append("!=?");
    String str3 = stringBuilder.toString();
    String str1 = str2;
    try {
      paramString = Utilities.Crypt(paramString.toLowerCase().trim());
      str1 = str2;
      Cursor cursor = sQLiteDatabase.query(vehicleCatalog, new String[] { "*" }, str3, new String[] { paramString, "L" }, null, null, null, null);
      paramString = str2;
      try {
      
      } finally {
        try {
          cursor.close();
        } catch (Exception exception4) {
          Exception exception3 = exception;
          Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception4.getMessage());
        } 
        exception2 = exception;
      } 
    } catch (Exception exception1) {
      Utilities.escribeArchivo(TAG, "Error: getVehicleDtcStatus", exception1.getMessage());
      exception1 = exception2;
    } 
    return (String)exception1;
  }
  
  public ArrayList<VehicleCatalogVO> getVehiclesAlmostExpired(String paramString) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #6
    //   9: aload_0
    //   10: invokevirtual getReadableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   13: astore #7
    //   15: new java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial <init> : ()V
    //   22: astore_3
    //   23: aload_3
    //   24: getstatic com/roadtrack/onstar/DAO/DBFunctions.KEY_STATUS_RENEWAL_ACCOUNT : Ljava/lang/String;
    //   27: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_3
    //   32: ldc_w ' =? AND '
    //   35: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload_3
    //   40: getstatic com/roadtrack/onstar/DAO/DBFunctions.KEY_VEHICLE_USER : Ljava/lang/String;
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload_3
    //   48: ldc_w ' =? AND  '
    //   51: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: pop
    //   55: aload_3
    //   56: getstatic com/roadtrack/onstar/DAO/DBFunctions.KEY_VEHICLE_SELECTED : Ljava/lang/String;
    //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: aload_3
    //   64: ldc_w ' !=? '
    //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_3
    //   72: invokevirtual toString : ()Ljava/lang/String;
    //   75: astore_3
    //   76: aload_1
    //   77: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   80: invokevirtual trim : ()Ljava/lang/String;
    //   83: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   86: astore_1
    //   87: aload #7
    //   89: getstatic com/roadtrack/onstar/DAO/DBFunctions.vehicleCatalog : Ljava/lang/String;
    //   92: iconst_1
    //   93: anewarray java/lang/String
    //   96: dup
    //   97: iconst_0
    //   98: ldc_w '*'
    //   101: aastore
    //   102: aload_3
    //   103: iconst_3
    //   104: anewarray java/lang/String
    //   107: dup
    //   108: iconst_0
    //   109: ldc_w '1'
    //   112: aastore
    //   113: dup
    //   114: iconst_1
    //   115: aload_1
    //   116: aastore
    //   117: dup
    //   118: iconst_2
    //   119: ldc_w 'L'
    //   122: aastore
    //   123: aconst_null
    //   124: aconst_null
    //   125: aconst_null
    //   126: aconst_null
    //   127: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   130: astore_1
    //   131: aload_1
    //   132: astore #4
    //   134: aload_1
    //   135: invokeinterface moveToFirst : ()Z
    //   140: ifeq -> 528
    //   143: aconst_null
    //   144: astore_3
    //   145: aconst_null
    //   146: astore #5
    //   148: aload_1
    //   149: astore #4
    //   151: aload_1
    //   152: iconst_0
    //   153: invokeinterface getString : (I)Ljava/lang/String;
    //   158: astore #19
    //   160: aload_1
    //   161: astore #4
    //   163: aload_1
    //   164: iconst_1
    //   165: invokeinterface getString : (I)Ljava/lang/String;
    //   170: astore #8
    //   172: aload_1
    //   173: astore #4
    //   175: aload_1
    //   176: iconst_2
    //   177: invokeinterface getString : (I)Ljava/lang/String;
    //   182: astore #9
    //   184: aload_1
    //   185: astore #4
    //   187: aload_1
    //   188: iconst_3
    //   189: invokeinterface getString : (I)Ljava/lang/String;
    //   194: astore #15
    //   196: aload_1
    //   197: astore #4
    //   199: aload_1
    //   200: iconst_4
    //   201: invokeinterface getString : (I)Ljava/lang/String;
    //   206: astore #11
    //   208: aload_1
    //   209: astore #4
    //   211: aload_1
    //   212: iconst_5
    //   213: invokeinterface getString : (I)Ljava/lang/String;
    //   218: astore #10
    //   220: aload_1
    //   221: astore #4
    //   223: aload_1
    //   224: bipush #6
    //   226: invokeinterface getString : (I)Ljava/lang/String;
    //   231: astore #14
    //   233: aload_1
    //   234: astore #4
    //   236: aload_1
    //   237: bipush #7
    //   239: invokeinterface getString : (I)Ljava/lang/String;
    //   244: astore #16
    //   246: aload_1
    //   247: astore #4
    //   249: aload_1
    //   250: bipush #8
    //   252: invokeinterface getString : (I)Ljava/lang/String;
    //   257: astore #21
    //   259: aload_1
    //   260: astore #4
    //   262: aload_1
    //   263: bipush #11
    //   265: invokeinterface getString : (I)Ljava/lang/String;
    //   270: astore #18
    //   272: aload_1
    //   273: astore #4
    //   275: aload_1
    //   276: bipush #12
    //   278: invokeinterface getString : (I)Ljava/lang/String;
    //   283: astore #17
    //   285: aload_1
    //   286: astore #4
    //   288: aload_1
    //   289: bipush #13
    //   291: invokeinterface getColumnName : (I)Ljava/lang/String;
    //   296: astore #20
    //   298: aload_1
    //   299: astore #4
    //   301: aload_1
    //   302: bipush #14
    //   304: invokeinterface getColumnName : (I)Ljava/lang/String;
    //   309: astore #12
    //   311: aload_1
    //   312: astore #4
    //   314: aload_1
    //   315: bipush #15
    //   317: invokeinterface getColumnName : (I)Ljava/lang/String;
    //   322: astore #13
    //   324: aload #7
    //   326: ldc_w 'renewal_dialog_status'
    //   329: iconst_1
    //   330: anewarray java/lang/String
    //   333: dup
    //   334: iconst_0
    //   335: ldc_w '*'
    //   338: aastore
    //   339: ldc_w 'deviceId = ?'
    //   342: iconst_1
    //   343: anewarray java/lang/String
    //   346: dup
    //   347: iconst_0
    //   348: aload #8
    //   350: aastore
    //   351: aconst_null
    //   352: aconst_null
    //   353: aconst_null
    //   354: aconst_null
    //   355: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   358: astore #22
    //   360: aload #22
    //   362: invokeinterface moveToFirst : ()Z
    //   367: ifeq -> 392
    //   370: aload #22
    //   372: iconst_3
    //   373: invokeinterface getString : (I)Ljava/lang/String;
    //   378: astore_3
    //   379: aload #22
    //   381: iconst_4
    //   382: invokeinterface getString : (I)Ljava/lang/String;
    //   387: astore #4
    //   389: goto -> 396
    //   392: aload #5
    //   394: astore #4
    //   396: aload #22
    //   398: invokeinterface close : ()V
    //   403: goto -> 422
    //   406: astore #5
    //   408: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   411: ldc_w 'Error: getRenewalStatus'
    //   414: aload #5
    //   416: invokevirtual getMessage : ()Ljava/lang/String;
    //   419: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   422: new com/roadtrack/onstar/VO/VehicleCatalogVO
    //   425: astore #5
    //   427: aload #5
    //   429: aload #19
    //   431: aload #8
    //   433: aload #9
    //   435: aload #15
    //   437: aload #11
    //   439: aload #10
    //   441: aload #14
    //   443: aload #16
    //   445: aload #21
    //   447: aload #18
    //   449: aload_3
    //   450: aload #4
    //   452: aload #17
    //   454: aload #20
    //   456: aload #12
    //   458: aload #13
    //   460: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   463: aload #6
    //   465: aload #5
    //   467: invokevirtual add : (Ljava/lang/Object;)Z
    //   470: pop
    //   471: aload_1
    //   472: invokeinterface moveToNext : ()Z
    //   477: istore_2
    //   478: iload_2
    //   479: ifne -> 485
    //   482: goto -> 528
    //   485: aload #4
    //   487: astore #5
    //   489: goto -> 148
    //   492: astore_3
    //   493: aload #22
    //   495: invokeinterface close : ()V
    //   500: goto -> 519
    //   503: astore #4
    //   505: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   508: ldc_w 'Error: getRenewalStatus'
    //   511: aload #4
    //   513: invokevirtual getMessage : ()Ljava/lang/String;
    //   516: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   519: aload_3
    //   520: athrow
    //   521: astore_3
    //   522: aload_1
    //   523: astore #4
    //   525: goto -> 557
    //   528: aload_1
    //   529: invokeinterface close : ()V
    //   534: goto -> 551
    //   537: astore_1
    //   538: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   541: ldc_w 'Error: getVehicleCatalog'
    //   544: aload_1
    //   545: invokevirtual getMessage : ()Ljava/lang/String;
    //   548: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   551: aload #6
    //   553: areturn
    //   554: astore_1
    //   555: aload_1
    //   556: astore_3
    //   557: aload #4
    //   559: invokeinterface close : ()V
    //   564: goto -> 581
    //   567: astore_1
    //   568: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   571: ldc_w 'Error: getVehicleCatalog'
    //   574: aload_1
    //   575: invokevirtual getMessage : ()Ljava/lang/String;
    //   578: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   581: aload_3
    //   582: athrow
    //   583: astore_1
    //   584: aload_1
    //   585: athrow
    // Exception table:
    //   from	to	target	type
    //   76	131	583	finally
    //   134	143	554	finally
    //   151	160	554	finally
    //   163	172	554	finally
    //   175	184	554	finally
    //   187	196	554	finally
    //   199	208	554	finally
    //   211	220	554	finally
    //   223	233	554	finally
    //   236	246	554	finally
    //   249	259	554	finally
    //   262	272	554	finally
    //   275	285	554	finally
    //   288	298	554	finally
    //   301	311	554	finally
    //   314	324	554	finally
    //   324	360	521	finally
    //   360	389	492	finally
    //   396	403	406	java/lang/Exception
    //   396	403	521	finally
    //   408	422	521	finally
    //   422	478	521	finally
    //   493	500	503	java/lang/Exception
    //   493	500	521	finally
    //   505	519	521	finally
    //   519	521	521	finally
    //   528	534	537	java/lang/Exception
    //   528	534	583	finally
    //   538	551	583	finally
    //   557	564	567	java/lang/Exception
    //   557	564	583	finally
    //   568	581	583	finally
    //   581	583	583	finally
  }
  
  public ArrayList<VehicleCatalogVO> getVehiclesCatalog(String paramString) {
    ArrayList<VehicleCatalogVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_USER);
    stringBuilder.append(" =? AND  ");
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append(" !=? ");
    String str = stringBuilder.toString();
    try {
      paramString = Utilities.Crypt(paramString.toLowerCase().trim());
      Cursor cursor = sQLiteDatabase.query(vehicleCatalog, new String[] { "*" }, str, new String[] { paramString, "L" }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            VehicleCatalogVO vehicleCatalogVO = new VehicleCatalogVO();
            this(cursor.getString(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(11));
            arrayList.add(vehicleCatalogVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getVehicleCatalog", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<VehicleCatalogVO> getVehiclesCatalogIgnoreSelected(String paramString) {
    ArrayList<VehicleCatalogVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(KEY_VEHICLE_USER);
    stringBuilder.append(" =? AND ");
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append(" !=?");
    String str = stringBuilder.toString();
    try {
      paramString = Utilities.Crypt(paramString.toLowerCase().trim());
      Cursor cursor = sQLiteDatabase.query(vehicleCatalog, new String[] { "*" }, str, new String[] { paramString, "L" }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            VehicleCatalogVO vehicleCatalogVO = new VehicleCatalogVO();
            this(cursor.getString(0), cursor.getString(1), Utilities.Decrypt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(11));
            arrayList.add(vehicleCatalogVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: getVehicleCatalog", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<VehicleCatalogVO> getVehiclesExpired(String paramString) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #6
    //   9: aload_0
    //   10: invokevirtual getReadableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   13: astore #7
    //   15: new java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial <init> : ()V
    //   22: astore_3
    //   23: aload_3
    //   24: getstatic com/roadtrack/onstar/DAO/DBFunctions.KEY_STATUS_RENEWAL_ACCOUNT : Ljava/lang/String;
    //   27: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_3
    //   32: ldc_w ' =? AND '
    //   35: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload_3
    //   40: getstatic com/roadtrack/onstar/DAO/DBFunctions.KEY_VEHICLE_USER : Ljava/lang/String;
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload_3
    //   48: ldc_w ' =? AND  '
    //   51: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: pop
    //   55: aload_3
    //   56: getstatic com/roadtrack/onstar/DAO/DBFunctions.KEY_VEHICLE_SELECTED : Ljava/lang/String;
    //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: aload_3
    //   64: ldc_w ' !=? '
    //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_3
    //   72: invokevirtual toString : ()Ljava/lang/String;
    //   75: astore_3
    //   76: aload_1
    //   77: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   80: invokevirtual trim : ()Ljava/lang/String;
    //   83: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   86: astore_1
    //   87: aload #7
    //   89: getstatic com/roadtrack/onstar/DAO/DBFunctions.vehicleCatalog : Ljava/lang/String;
    //   92: iconst_1
    //   93: anewarray java/lang/String
    //   96: dup
    //   97: iconst_0
    //   98: ldc_w '*'
    //   101: aastore
    //   102: aload_3
    //   103: iconst_3
    //   104: anewarray java/lang/String
    //   107: dup
    //   108: iconst_0
    //   109: ldc_w '2'
    //   112: aastore
    //   113: dup
    //   114: iconst_1
    //   115: aload_1
    //   116: aastore
    //   117: dup
    //   118: iconst_2
    //   119: ldc_w 'L'
    //   122: aastore
    //   123: aconst_null
    //   124: aconst_null
    //   125: aconst_null
    //   126: aconst_null
    //   127: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   130: astore_1
    //   131: aload_1
    //   132: astore #4
    //   134: aload_1
    //   135: invokeinterface moveToFirst : ()Z
    //   140: ifeq -> 528
    //   143: aconst_null
    //   144: astore_3
    //   145: aconst_null
    //   146: astore #5
    //   148: aload_1
    //   149: astore #4
    //   151: aload_1
    //   152: iconst_0
    //   153: invokeinterface getString : (I)Ljava/lang/String;
    //   158: astore #13
    //   160: aload_1
    //   161: astore #4
    //   163: aload_1
    //   164: iconst_1
    //   165: invokeinterface getString : (I)Ljava/lang/String;
    //   170: astore #8
    //   172: aload_1
    //   173: astore #4
    //   175: aload_1
    //   176: iconst_2
    //   177: invokeinterface getString : (I)Ljava/lang/String;
    //   182: astore #19
    //   184: aload_1
    //   185: astore #4
    //   187: aload_1
    //   188: iconst_3
    //   189: invokeinterface getString : (I)Ljava/lang/String;
    //   194: astore #12
    //   196: aload_1
    //   197: astore #4
    //   199: aload_1
    //   200: iconst_4
    //   201: invokeinterface getString : (I)Ljava/lang/String;
    //   206: astore #9
    //   208: aload_1
    //   209: astore #4
    //   211: aload_1
    //   212: iconst_5
    //   213: invokeinterface getString : (I)Ljava/lang/String;
    //   218: astore #20
    //   220: aload_1
    //   221: astore #4
    //   223: aload_1
    //   224: bipush #6
    //   226: invokeinterface getString : (I)Ljava/lang/String;
    //   231: astore #18
    //   233: aload_1
    //   234: astore #4
    //   236: aload_1
    //   237: bipush #7
    //   239: invokeinterface getString : (I)Ljava/lang/String;
    //   244: astore #17
    //   246: aload_1
    //   247: astore #4
    //   249: aload_1
    //   250: bipush #8
    //   252: invokeinterface getString : (I)Ljava/lang/String;
    //   257: astore #11
    //   259: aload_1
    //   260: astore #4
    //   262: aload_1
    //   263: bipush #11
    //   265: invokeinterface getString : (I)Ljava/lang/String;
    //   270: astore #10
    //   272: aload_1
    //   273: astore #4
    //   275: aload_1
    //   276: bipush #12
    //   278: invokeinterface getString : (I)Ljava/lang/String;
    //   283: astore #21
    //   285: aload_1
    //   286: astore #4
    //   288: aload_1
    //   289: bipush #13
    //   291: invokeinterface getColumnName : (I)Ljava/lang/String;
    //   296: astore #15
    //   298: aload_1
    //   299: astore #4
    //   301: aload_1
    //   302: bipush #14
    //   304: invokeinterface getColumnName : (I)Ljava/lang/String;
    //   309: astore #14
    //   311: aload_1
    //   312: astore #4
    //   314: aload_1
    //   315: bipush #15
    //   317: invokeinterface getColumnName : (I)Ljava/lang/String;
    //   322: astore #16
    //   324: aload #7
    //   326: ldc_w 'renewal_dialog_status'
    //   329: iconst_1
    //   330: anewarray java/lang/String
    //   333: dup
    //   334: iconst_0
    //   335: ldc_w '*'
    //   338: aastore
    //   339: ldc_w 'deviceId = ?'
    //   342: iconst_1
    //   343: anewarray java/lang/String
    //   346: dup
    //   347: iconst_0
    //   348: aload #8
    //   350: aastore
    //   351: aconst_null
    //   352: aconst_null
    //   353: aconst_null
    //   354: aconst_null
    //   355: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   358: astore #22
    //   360: aload #22
    //   362: invokeinterface moveToFirst : ()Z
    //   367: ifeq -> 392
    //   370: aload #22
    //   372: iconst_3
    //   373: invokeinterface getString : (I)Ljava/lang/String;
    //   378: astore_3
    //   379: aload #22
    //   381: iconst_4
    //   382: invokeinterface getString : (I)Ljava/lang/String;
    //   387: astore #4
    //   389: goto -> 396
    //   392: aload #5
    //   394: astore #4
    //   396: aload #22
    //   398: invokeinterface close : ()V
    //   403: goto -> 422
    //   406: astore #5
    //   408: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   411: ldc_w 'Error: getRenewalStatus'
    //   414: aload #5
    //   416: invokevirtual getMessage : ()Ljava/lang/String;
    //   419: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   422: new com/roadtrack/onstar/VO/VehicleCatalogVO
    //   425: astore #5
    //   427: aload #5
    //   429: aload #13
    //   431: aload #8
    //   433: aload #19
    //   435: aload #12
    //   437: aload #9
    //   439: aload #20
    //   441: aload #18
    //   443: aload #17
    //   445: aload #11
    //   447: aload #10
    //   449: aload_3
    //   450: aload #4
    //   452: aload #21
    //   454: aload #15
    //   456: aload #14
    //   458: aload #16
    //   460: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   463: aload #6
    //   465: aload #5
    //   467: invokevirtual add : (Ljava/lang/Object;)Z
    //   470: pop
    //   471: aload_1
    //   472: invokeinterface moveToNext : ()Z
    //   477: istore_2
    //   478: iload_2
    //   479: ifne -> 485
    //   482: goto -> 528
    //   485: aload #4
    //   487: astore #5
    //   489: goto -> 148
    //   492: astore_3
    //   493: aload #22
    //   495: invokeinterface close : ()V
    //   500: goto -> 519
    //   503: astore #4
    //   505: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   508: ldc_w 'Error: getRenewalStatus'
    //   511: aload #4
    //   513: invokevirtual getMessage : ()Ljava/lang/String;
    //   516: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   519: aload_3
    //   520: athrow
    //   521: astore_3
    //   522: aload_1
    //   523: astore #4
    //   525: goto -> 557
    //   528: aload_1
    //   529: invokeinterface close : ()V
    //   534: goto -> 551
    //   537: astore_1
    //   538: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   541: ldc_w 'Error: getVehicleCatalog'
    //   544: aload_1
    //   545: invokevirtual getMessage : ()Ljava/lang/String;
    //   548: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   551: aload #6
    //   553: areturn
    //   554: astore_1
    //   555: aload_1
    //   556: astore_3
    //   557: aload #4
    //   559: invokeinterface close : ()V
    //   564: goto -> 581
    //   567: astore_1
    //   568: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   571: ldc_w 'Error: getVehicleCatalog'
    //   574: aload_1
    //   575: invokevirtual getMessage : ()Ljava/lang/String;
    //   578: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   581: aload_3
    //   582: athrow
    //   583: astore_1
    //   584: aload_1
    //   585: athrow
    // Exception table:
    //   from	to	target	type
    //   76	131	583	finally
    //   134	143	554	finally
    //   151	160	554	finally
    //   163	172	554	finally
    //   175	184	554	finally
    //   187	196	554	finally
    //   199	208	554	finally
    //   211	220	554	finally
    //   223	233	554	finally
    //   236	246	554	finally
    //   249	259	554	finally
    //   262	272	554	finally
    //   275	285	554	finally
    //   288	298	554	finally
    //   301	311	554	finally
    //   314	324	554	finally
    //   324	360	521	finally
    //   360	389	492	finally
    //   396	403	406	java/lang/Exception
    //   396	403	521	finally
    //   408	422	521	finally
    //   422	478	521	finally
    //   493	500	503	java/lang/Exception
    //   493	500	521	finally
    //   505	519	521	finally
    //   519	521	521	finally
    //   528	534	537	java/lang/Exception
    //   528	534	583	finally
    //   538	551	583	finally
    //   557	564	567	java/lang/Exception
    //   557	564	583	finally
    //   568	581	583	finally
    //   581	583	583	finally
  }
  
  public ArrayList<VehicleCatalogVO> getVehiclesNormal(String paramString) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #6
    //   9: aload_0
    //   10: invokevirtual getReadableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   13: astore #7
    //   15: new java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial <init> : ()V
    //   22: astore_3
    //   23: aload_3
    //   24: getstatic com/roadtrack/onstar/DAO/DBFunctions.KEY_STATUS_RENEWAL_ACCOUNT : Ljava/lang/String;
    //   27: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_3
    //   32: ldc_w ' =? AND '
    //   35: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload_3
    //   40: getstatic com/roadtrack/onstar/DAO/DBFunctions.KEY_VEHICLE_USER : Ljava/lang/String;
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload_3
    //   48: ldc_w ' =? AND  '
    //   51: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: pop
    //   55: aload_3
    //   56: getstatic com/roadtrack/onstar/DAO/DBFunctions.KEY_VEHICLE_SELECTED : Ljava/lang/String;
    //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: aload_3
    //   64: ldc_w ' !=? '
    //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload_3
    //   72: invokevirtual toString : ()Ljava/lang/String;
    //   75: astore_3
    //   76: aload_1
    //   77: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   80: invokevirtual trim : ()Ljava/lang/String;
    //   83: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   86: astore_1
    //   87: aload #7
    //   89: getstatic com/roadtrack/onstar/DAO/DBFunctions.vehicleCatalog : Ljava/lang/String;
    //   92: iconst_1
    //   93: anewarray java/lang/String
    //   96: dup
    //   97: iconst_0
    //   98: ldc_w '*'
    //   101: aastore
    //   102: aload_3
    //   103: iconst_3
    //   104: anewarray java/lang/String
    //   107: dup
    //   108: iconst_0
    //   109: ldc_w '0'
    //   112: aastore
    //   113: dup
    //   114: iconst_1
    //   115: aload_1
    //   116: aastore
    //   117: dup
    //   118: iconst_2
    //   119: ldc_w 'L'
    //   122: aastore
    //   123: aconst_null
    //   124: aconst_null
    //   125: aconst_null
    //   126: aconst_null
    //   127: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   130: astore_1
    //   131: aload_1
    //   132: astore #4
    //   134: aload_1
    //   135: invokeinterface moveToFirst : ()Z
    //   140: ifeq -> 528
    //   143: aconst_null
    //   144: astore_3
    //   145: aconst_null
    //   146: astore #5
    //   148: aload_1
    //   149: astore #4
    //   151: aload_1
    //   152: iconst_0
    //   153: invokeinterface getString : (I)Ljava/lang/String;
    //   158: astore #15
    //   160: aload_1
    //   161: astore #4
    //   163: aload_1
    //   164: iconst_1
    //   165: invokeinterface getString : (I)Ljava/lang/String;
    //   170: astore #10
    //   172: aload_1
    //   173: astore #4
    //   175: aload_1
    //   176: iconst_2
    //   177: invokeinterface getString : (I)Ljava/lang/String;
    //   182: astore #9
    //   184: aload_1
    //   185: astore #4
    //   187: aload_1
    //   188: iconst_3
    //   189: invokeinterface getString : (I)Ljava/lang/String;
    //   194: astore #21
    //   196: aload_1
    //   197: astore #4
    //   199: aload_1
    //   200: iconst_4
    //   201: invokeinterface getString : (I)Ljava/lang/String;
    //   206: astore #14
    //   208: aload_1
    //   209: astore #4
    //   211: aload_1
    //   212: iconst_5
    //   213: invokeinterface getString : (I)Ljava/lang/String;
    //   218: astore #12
    //   220: aload_1
    //   221: astore #4
    //   223: aload_1
    //   224: bipush #6
    //   226: invokeinterface getString : (I)Ljava/lang/String;
    //   231: astore #13
    //   233: aload_1
    //   234: astore #4
    //   236: aload_1
    //   237: bipush #7
    //   239: invokeinterface getString : (I)Ljava/lang/String;
    //   244: astore #17
    //   246: aload_1
    //   247: astore #4
    //   249: aload_1
    //   250: bipush #8
    //   252: invokeinterface getString : (I)Ljava/lang/String;
    //   257: astore #8
    //   259: aload_1
    //   260: astore #4
    //   262: aload_1
    //   263: bipush #11
    //   265: invokeinterface getString : (I)Ljava/lang/String;
    //   270: astore #18
    //   272: aload_1
    //   273: astore #4
    //   275: aload_1
    //   276: bipush #12
    //   278: invokeinterface getString : (I)Ljava/lang/String;
    //   283: astore #11
    //   285: aload_1
    //   286: astore #4
    //   288: aload_1
    //   289: bipush #13
    //   291: invokeinterface getColumnName : (I)Ljava/lang/String;
    //   296: astore #19
    //   298: aload_1
    //   299: astore #4
    //   301: aload_1
    //   302: bipush #14
    //   304: invokeinterface getColumnName : (I)Ljava/lang/String;
    //   309: astore #20
    //   311: aload_1
    //   312: astore #4
    //   314: aload_1
    //   315: bipush #15
    //   317: invokeinterface getColumnName : (I)Ljava/lang/String;
    //   322: astore #16
    //   324: aload #7
    //   326: ldc_w 'renewal_dialog_status'
    //   329: iconst_1
    //   330: anewarray java/lang/String
    //   333: dup
    //   334: iconst_0
    //   335: ldc_w '*'
    //   338: aastore
    //   339: ldc_w 'deviceId = ?'
    //   342: iconst_1
    //   343: anewarray java/lang/String
    //   346: dup
    //   347: iconst_0
    //   348: aload #10
    //   350: aastore
    //   351: aconst_null
    //   352: aconst_null
    //   353: aconst_null
    //   354: aconst_null
    //   355: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   358: astore #22
    //   360: aload #22
    //   362: invokeinterface moveToFirst : ()Z
    //   367: ifeq -> 392
    //   370: aload #22
    //   372: iconst_3
    //   373: invokeinterface getString : (I)Ljava/lang/String;
    //   378: astore_3
    //   379: aload #22
    //   381: iconst_4
    //   382: invokeinterface getString : (I)Ljava/lang/String;
    //   387: astore #4
    //   389: goto -> 396
    //   392: aload #5
    //   394: astore #4
    //   396: aload #22
    //   398: invokeinterface close : ()V
    //   403: goto -> 422
    //   406: astore #5
    //   408: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   411: ldc_w 'Error: getRenewalStatus'
    //   414: aload #5
    //   416: invokevirtual getMessage : ()Ljava/lang/String;
    //   419: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   422: new com/roadtrack/onstar/VO/VehicleCatalogVO
    //   425: astore #5
    //   427: aload #5
    //   429: aload #15
    //   431: aload #10
    //   433: aload #9
    //   435: aload #21
    //   437: aload #14
    //   439: aload #12
    //   441: aload #13
    //   443: aload #17
    //   445: aload #8
    //   447: aload #18
    //   449: aload_3
    //   450: aload #4
    //   452: aload #11
    //   454: aload #19
    //   456: aload #20
    //   458: aload #16
    //   460: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   463: aload #6
    //   465: aload #5
    //   467: invokevirtual add : (Ljava/lang/Object;)Z
    //   470: pop
    //   471: aload_1
    //   472: invokeinterface moveToNext : ()Z
    //   477: istore_2
    //   478: iload_2
    //   479: ifne -> 485
    //   482: goto -> 528
    //   485: aload #4
    //   487: astore #5
    //   489: goto -> 148
    //   492: astore_3
    //   493: aload #22
    //   495: invokeinterface close : ()V
    //   500: goto -> 519
    //   503: astore #4
    //   505: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   508: ldc_w 'Error: getRenewalStatus'
    //   511: aload #4
    //   513: invokevirtual getMessage : ()Ljava/lang/String;
    //   516: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   519: aload_3
    //   520: athrow
    //   521: astore_3
    //   522: aload_1
    //   523: astore #4
    //   525: goto -> 557
    //   528: aload_1
    //   529: invokeinterface close : ()V
    //   534: goto -> 551
    //   537: astore_1
    //   538: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   541: ldc_w 'Error: getVehicleCatalog'
    //   544: aload_1
    //   545: invokevirtual getMessage : ()Ljava/lang/String;
    //   548: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   551: aload #6
    //   553: areturn
    //   554: astore_1
    //   555: aload_1
    //   556: astore_3
    //   557: aload #4
    //   559: invokeinterface close : ()V
    //   564: goto -> 581
    //   567: astore_1
    //   568: getstatic com/roadtrack/onstar/DAO/DBFunctions.TAG : Ljava/lang/String;
    //   571: ldc_w 'Error: getVehicleCatalog'
    //   574: aload_1
    //   575: invokevirtual getMessage : ()Ljava/lang/String;
    //   578: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   581: aload_3
    //   582: athrow
    //   583: astore_1
    //   584: aload_1
    //   585: athrow
    // Exception table:
    //   from	to	target	type
    //   76	131	583	finally
    //   134	143	554	finally
    //   151	160	554	finally
    //   163	172	554	finally
    //   175	184	554	finally
    //   187	196	554	finally
    //   199	208	554	finally
    //   211	220	554	finally
    //   223	233	554	finally
    //   236	246	554	finally
    //   249	259	554	finally
    //   262	272	554	finally
    //   275	285	554	finally
    //   288	298	554	finally
    //   301	311	554	finally
    //   314	324	554	finally
    //   324	360	521	finally
    //   360	389	492	finally
    //   396	403	406	java/lang/Exception
    //   396	403	521	finally
    //   408	422	521	finally
    //   422	478	521	finally
    //   493	500	503	java/lang/Exception
    //   493	500	521	finally
    //   505	519	521	finally
    //   519	521	521	finally
    //   528	534	537	java/lang/Exception
    //   528	534	583	finally
    //   538	551	583	finally
    //   557	564	567	java/lang/Exception
    //   557	564	583	finally
    //   568	581	583	finally
    //   581	583	583	finally
  }
  
  public long insertDTC(List<RemoteDiagnosticVO> paramList, SQLiteDatabase... paramVarArgs) {
    long l3;
    long l2 = 0L;
    long l1 = l2;
    try {
      SQLiteDatabase sQLiteDatabase;
      if (paramVarArgs.length > 0) {
        sQLiteDatabase = paramVarArgs[0];
        l1 = l2;
        DTC_INFO_TABLE = "dtc_info_temp";
      } else {
        l1 = l2;
        sQLiteDatabase = getWritableDatabase();
      } 
      l1 = l2;
      deleteOldDTCByDevice(((RemoteDiagnosticVO)paramList.get(0)).getDeviceId(), new SQLiteDatabase[] { sQLiteDatabase });
      l1 = l2;
      StringBuilder stringBuilder = new StringBuilder();
      l1 = l2;
      this();
      l1 = l2;
      stringBuilder.append("INSERT INTO ");
      l1 = l2;
      stringBuilder.append(DTC_INFO_TABLE);
      l1 = l2;
      stringBuilder.append(" (");
      l1 = l2;
      stringBuilder.append("id_device");
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append("id_group");
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append("id_status");
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append("title");
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append("description");
      l1 = l2;
      stringBuilder.append(", ");
      l1 = l2;
      stringBuilder.append("date");
      l1 = l2;
      stringBuilder.append(") VALUES ( ?, ?, ?, ?, ?, ?);");
      l1 = l2;
      String str = stringBuilder.toString();
      l1 = l2;
      Iterator<RemoteDiagnosticVO> iterator = paramList.iterator();
      while (true) {
        l1 = l2;
        l3 = l2;
        if (iterator.hasNext()) {
          l1 = l2;
          RemoteDiagnosticVO remoteDiagnosticVO = iterator.next();
          l1 = l2;
          SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(str);
          l1 = l2;
          sQLiteStatement.bindString(1, Utilities.Crypt(remoteDiagnosticVO.getDeviceId()));
          l1 = l2;
          sQLiteStatement.bindLong(2, remoteDiagnosticVO.getDtcGroupId());
          l1 = l2;
          sQLiteStatement.bindLong(3, remoteDiagnosticVO.getDtcStatusId());
          l1 = l2;
          sQLiteStatement.bindString(4, Utilities.Crypt(Utilities.isNullString(remoteDiagnosticVO.getDtcTitle())));
          l1 = l2;
          sQLiteStatement.bindString(5, Utilities.Crypt(Utilities.isNullString(remoteDiagnosticVO.getDtcDescription())));
          l1 = l2;
          sQLiteStatement.bindString(6, Utilities.Crypt(Utilities.isNullString(remoteDiagnosticVO.getDtcDate())));
          l1 = l2;
          l2 = sQLiteStatement.executeInsert();
          l1 = l2;
          sQLiteStatement.clearBindings();
          continue;
        } 
        break;
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
      l3 = l1;
    } 
    DTC_INFO_TABLE = "dtc_info";
    return l3;
  }
  
  public void insertOrUpdateDeviceRegister(String paramString1, String paramString2, String paramString3) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("udid", paramString1);
    contentValues.put("device_name", paramString2);
    contentValues.put("application_type", paramString3);
    if (existRow(sQLiteDatabase, "register_device", "udid", paramString1)) {
      sQLiteDatabase.update("register_device", contentValues, "udid = ?", new String[] { paramString1 });
    } else {
      contentValues.put("udid", paramString1);
      sQLiteDatabase.insert("register_device", null, contentValues);
    } 
    sQLiteDatabase.close();
  }
  
  public long insertRenewalDialogStatus(String paramString) {
    String str = getRenewalDialogStatus(paramString);
    long l2 = 0L;
    long l1 = l2;
    if (str == null) {
      l1 = l2;
      try {
        SQLiteDatabase sQLiteDatabase = getWritableDatabase();
        l1 = l2;
        SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("INSERT INTO renewal_dialog_status (deviceId, status_renewal_dialog) VALUES ( ?, ?) ;");
        l1 = l2;
        sQLiteStatement.bindString(1, paramString);
        l1 = l2;
        sQLiteStatement.bindString(2, "0");
        l1 = l2;
        l2 = sQLiteStatement.executeInsert();
        l1 = l2;
        sQLiteStatement.clearBindings();
        l1 = l2;
        sQLiteDatabase.close();
        l1 = l2;
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, exception);
      } 
    } 
    return l1;
  }
  
  public boolean insertTheftStatusVehicle(String paramString1, String paramString2, String paramString3) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(vehicleCatalog);
    stringBuilder.append(" SET ");
    stringBuilder.append(KEY_VEHICLE_ID_ACCOUNT);
    stringBuilder.append(" = ? , ");
    stringBuilder.append(KEY_VEHICLE_THEFT_AUTO_STATUS);
    stringBuilder.append(" = ? WHERE ");
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(" = ?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    paramString1 = Utilities.isNullString(paramString1);
    boolean bool = true;
    sQLiteStatement.bindString(1, paramString1);
    sQLiteStatement.bindString(2, Utilities.isNullString(paramString3));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramString2));
    long l = sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    if (l <= 0L)
      bool = false; 
    return bool;
  }
  
  public boolean isMapUpdateEmpty() {
    boolean bool;
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(mapUpdateTable, new String[] { "*" }, "id_mapupdate =?", new String[] { "1" }, null, null, null, null);
      if (cursor.getCount() > 0) {
        bool = false;
      } else {
        bool = true;
      } 
      cursor.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: isMapUpdateEmpty", exception.getMessage());
      bool = true;
    } 
    return bool;
  }
  
  public boolean isMapUpdateEmptyPart() {
    boolean bool;
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    try {
      Cursor cursor = sQLiteDatabase.query(mapUpdateTable, new String[] { "*" }, "id_mapupdate =?", new String[] { "100" }, null, null, null, null);
      if (cursor.getCount() > 0) {
        bool = false;
      } else {
        bool = true;
      } 
      cursor.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: isMapUpdateEmptyParts", exception.getMessage());
      bool = true;
    } 
    return bool;
  }
  
  public boolean messageIdExists(String paramString) {
    boolean bool1;
    boolean bool4 = false;
    boolean bool3 = false;
    boolean bool2 = false;
    Cursor cursor2 = null;
    Cursor cursor1 = null;
    try {
      Cursor cursor = getReadableDatabase().query(historicalTable, new String[] { "*" }, "message_id = ?", new String[] { paramString }, null, null, null, null);
      bool1 = bool2;
      if (cursor != null) {
        cursor1 = cursor;
        cursor2 = cursor;
        boolean bool = cursor.moveToFirst();
        bool1 = bool2;
        if (bool)
          bool1 = true; 
      } 
      bool2 = bool1;
      try {
        cursor.close();
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Error: messageIdExists", exception.getMessage());
        bool1 = bool2;
      } 
    } catch (Exception exception) {
      cursor1 = cursor2;
      Utilities.escribeArchivo(TAG, "Error: messageIdExists", exception.getMessage());
      bool2 = bool4;
      cursor2.close();
      bool1 = bool3;
    } finally {}
    return bool1;
  }
  
  public String messageIdFromStartFollowMe(String paramString, Context paramContext) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    String str2 = Utilities.Crypt("FollowMe");
    String str1 = historicalTable;
    byte b = 0;
    Cursor cursor = sQLiteDatabase.query(str1, new String[] { "*" }, "NAME =? AND id_error = ? AND vehicle_id =? ", new String[] { str2, "1", paramString }, null, null, " date_time DESC", "1");
    str1 = "0";
    if (cursor != null && cursor.moveToFirst()) {
      String str4 = cursor.getString(12);
      str1 = cursor.getString(13);
      if (!Utilities.Decrypt(cursor.getString(5)).equals("0") || !Utilities.Decrypt(cursor.getString(6)).equals("0"))
        if (Utilities.diffMinutesToActualTime(str4, "", paramContext, paramString) >= 16L) {
          b = 2;
        } else {
          b = 1;
        }  
      if (b == 1) {
        paramString = "corriendo";
      } else if (b == 2) {
        paramString = "no esta corriendo";
      } else {
        paramString = "Nunca se ha ejecutado";
      } 
      String str3 = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fecha Ultimo Follow Me: ");
      stringBuilder.append(str4);
      stringBuilder.append(" status:");
      stringBuilder.append(paramString);
      stringBuilder.append(" messageId: ");
      stringBuilder.append(str1);
      Utilities.escribeArchivo(str3, "FOLLOWME", stringBuilder.toString());
      paramString = str1;
    } else {
      Utilities.escribeArchivo(TAG, "FOLLOWME", "No encontr� ningun registro para follow me");
      paramString = str1;
    } 
    cursor.close();
    return paramString;
  }
  
  public int minutesLeftForFollowMe(String paramString, Context paramContext) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    String str1 = Utilities.Crypt("FollowMe");
    String str2 = historicalTable;
    byte b = 0;
    Cursor cursor = sQLiteDatabase.query(str2, new String[] { "*" }, "NAME =? AND id_error = 1 AND vehicle_id =? ", new String[] { str1, paramString }, null, null, "date_time DESC", "1");
    int i = b;
    if (cursor != null) {
      i = b;
      if (cursor.moveToFirst())
        i = (int)Utilities.diffMinutesToActualTime(cursor.getString(12), "", paramContext, paramString); 
    } 
    cursor.close();
    return i;
  }
  
  public int mustStartFollowMe(String paramString, Context paramContext) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    String str1 = Utilities.Crypt("FollowMe");
    String str2 = historicalTable;
    byte b2 = 2;
    boolean bool = false;
    byte b1 = 0;
    Cursor cursor = sQLiteDatabase.query(str2, new String[] { "*" }, "NAME =? AND id_error = 1 AND vehicle_id =? ", new String[] { str1, paramString }, null, null, "date_time DESC", "1");
    if (cursor != null && cursor.moveToFirst()) {
      str2 = cursor.getString(12);
      String str4 = TAG;
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Fecha Ultimo Follow Me: ");
      stringBuilder2.append(str2);
      stringBuilder2.append(" MessageId:");
      stringBuilder2.append(cursor.getString(13));
      Utilities.escribeArchivo(str4, "FOLLOWME", stringBuilder2.toString());
      if (!Utilities.Decrypt(cursor.getString(5)).equals("0") || !Utilities.Decrypt(cursor.getString(6)).equals("0"))
        if (Utilities.diffMinutesToActualTime(str2, "", paramContext, paramString) >= 16L) {
          b1 = b2;
        } else {
          b1 = 1;
        }  
      String str3 = TAG;
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Status Ultimo Follow Me: ");
      stringBuilder1.append(b1);
      stringBuilder1.append(" Messageid::");
      stringBuilder1.append(cursor.getString(13));
      Utilities.escribeArchivo(str3, "FOLLOWME", stringBuilder1.toString());
    } else {
      Utilities.escribeArchivo(TAG, "FOLLOWME", "No encontr� ningun registro ");
      b1 = bool;
    } 
    cursor.close();
    return b1;
  }
  
  public void processNotifications(String paramString) {
    DBFunctions dBFunctions = this;
    String str = "0";
    onstarApplication onstarApplication = (onstarApplication)GlobalMembers.contexGlobal.getApplicationContext();
    if (paramString != null && paramString.length() != 0) {
      String[] arrayOfString = paramString.split("\\|");
      if (arrayOfString != null && arrayOfString.length > 2 && arrayOfString[0].equals("15")) {
        String str2 = arrayOfString[1];
        String[] arrayOfString1 = arrayOfString[2].split(";");
        String str1 = GlobalMembers.userLogged;
        if (arrayOfString1 != null && arrayOfString1.length > 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("INSERT INTO ");
          stringBuilder.append(historicalTable);
          stringBuilder.append(" ( ");
          stringBuilder.append("category");
          stringBuilder.append(", ");
          stringBuilder.append("name");
          stringBuilder.append(", ");
          stringBuilder.append("description");
          stringBuilder.append(", ");
          stringBuilder.append("aplication_type");
          stringBuilder.append(", ");
          stringBuilder.append("lat");
          stringBuilder.append(", ");
          stringBuilder.append("lng");
          stringBuilder.append(", ");
          stringBuilder.append("id_status");
          stringBuilder.append(", ");
          stringBuilder.append("id_error");
          stringBuilder.append(", ");
          stringBuilder.append("id_action");
          stringBuilder.append(", ");
          stringBuilder.append("vehicle_id");
          stringBuilder.append(", ");
          stringBuilder.append("user_name");
          stringBuilder.append(", ");
          stringBuilder.append("date_time");
          stringBuilder.append(", ");
          stringBuilder.append("message_id");
          stringBuilder.append(", ");
          stringBuilder.append("completion_code");
          stringBuilder.append(", ");
          stringBuilder.append("requestErroId");
          stringBuilder.append(", ");
          stringBuilder.append("responseErrorId");
          stringBuilder.append(", ");
          stringBuilder.append("messageResponse");
          stringBuilder.append(", ");
          stringBuilder.append("deleted");
          stringBuilder.append(", ");
          stringBuilder.append("gpsstatus");
          stringBuilder.append(", ");
          stringBuilder.append("date_time_execution");
          stringBuilder.append(", ");
          stringBuilder.append("readnotification");
          stringBuilder.append(" ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
          String str5 = stringBuilder.toString();
          String str6 = "UPDATE Historical SET category = ?, name = ?, description = ?, aplication_type = ?, lat = ?, lng = ?, id_status = ?, id_error = ?, id_action = ?, vehicle_id = ?, user_name = ?, date_time = ?, message_id = ?, completion_code = ?, requestErroId = ?, responseErrorId = ?, messageResponse = ?, deleted =?, gpsstatus = ?, date_time_execution =?  WHERE message_id = ?";
          stringBuilder = new StringBuilder();
          stringBuilder.append("INSERT INTO ");
          stringBuilder.append(pushNotificationTable);
          stringBuilder.append(" (");
          stringBuilder.append("id_response");
          stringBuilder.append(", ");
          stringBuilder.append("acc");
          stringBuilder.append(", ");
          stringBuilder.append("message");
          stringBuilder.append(", ");
          stringBuilder.append("time");
          stringBuilder.append(", ");
          stringBuilder.append("alertId");
          stringBuilder.append(", ");
          stringBuilder.append("actionId");
          stringBuilder.append(", ");
          stringBuilder.append("deviceId");
          stringBuilder.append(", ");
          stringBuilder.append("latitude");
          stringBuilder.append(", ");
          stringBuilder.append("longitude");
          stringBuilder.append(", ");
          stringBuilder.append("type");
          stringBuilder.append(", ");
          stringBuilder.append("title");
          stringBuilder.append(", ");
          stringBuilder.append("n_actionid");
          stringBuilder.append(", ");
          stringBuilder.append("inserted_row");
          stringBuilder.append(", ");
          stringBuilder.append("message_id");
          stringBuilder.append(", ");
          stringBuilder.append("deleted");
          stringBuilder.append(", ");
          stringBuilder.append("alertCodeId");
          stringBuilder.append(", ");
          stringBuilder.append("readnotification");
          stringBuilder.append(" ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
          String str3 = stringBuilder.toString();
          stringBuilder = new StringBuilder();
          stringBuilder.append("UPDATE ");
          stringBuilder.append(pushNotificationTable);
          stringBuilder.append(" SET ");
          stringBuilder.append("id_response");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("acc");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("message");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("time");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("alertId");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("actionId");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("deviceId");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("latitude");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("longitude");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("type");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("title");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("n_actionid");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("inserted_row");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("message_id");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("deleted");
          stringBuilder.append(" = ?, ");
          stringBuilder.append("alertCodeId");
          stringBuilder.append(" = ? WHERE alertId = ?;");
          String str4 = stringBuilder.toString();
          try {
            SQLiteDatabase sQLiteDatabase2 = getReadableDatabase();
            SQLiteDatabase sQLiteDatabase1 = getWritableDatabase();
            for (byte b = 0; b < arrayOfString1.length; b++) {
              String[] arrayOfString2 = arrayOfString1[b].split(",");
              if (arrayOfString2 != null && arrayOfString2.length > 0) {
                Cursor cursor;
                String str8 = arrayOfString2[0];
                String str9 = arrayOfString2[1];
                int i = Integer.valueOf(arrayOfString2[6]).intValue();
                boolean bool = arrayOfString2[6].equals(str);
                if (!bool) {
                  boolean bool1;
                  boolean bool2;
                  boolean bool3;
                  cursor = sQLiteDatabase2.query("historical", new String[] { "*" }, "message_id = ?", new String[] { str8 }, null, null, null, null);
                  SQLiteStatement sQLiteStatement = null;
                  if (cursor != null && cursor.moveToFirst()) {
                    bool1 = cursor.getString(12).equals(arrayOfString2[8]) ^ true;
                    if (bool1 != 0)
                      sQLiteStatement = sQLiteDatabase1.compileStatement(str6); 
                    bool3 = cursor.getInt(18);
                    bool2 = false;
                  } else {
                    sQLiteStatement = sQLiteDatabase1.compileStatement(str5);
                    bool1 = false;
                    bool2 = true;
                    bool3 = false;
                  } 
                  int k = Integer.valueOf(arrayOfString2[5]).intValue();
                  String str11 = arrayOfString2[2];
                  int j = k;
                  if (i == 1) {
                    j = k;
                    if (str11.equals("3"))
                      j = 3; 
                  } 
                  String str10 = str11;
                  if (i == Enums.Services.pid.GetCode()) {
                    str10 = str11;
                    if (str11.equals("3"))
                      str10 = "15"; 
                  } 
                  if (bool1 || bool2) {
                    sQLiteStatement.bindString(1, Utilities.isNullString("Other"));
                    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(Utilities.parseActionType(i).name())));
                    sQLiteStatement.bindString(3, Utilities.isNullString(""));
                    sQLiteStatement.bindString(4, Utilities.isNullString("Historical"));
                    sQLiteStatement.bindString(5, Utilities.isNullString(Utilities.Crypt(arrayOfString2[3])));
                    sQLiteStatement.bindString(6, Utilities.isNullString(Utilities.Crypt(arrayOfString2[4])));
                    sQLiteStatement.bindLong(7, 0L);
                    if (str10.equals("3")) {
                      k = 2;
                    } else {
                      k = 1;
                    } 
                    sQLiteStatement.bindLong(8, k);
                    sQLiteStatement.bindString(9, Utilities.isNullString(arrayOfString2[6]));
                    sQLiteStatement.bindString(10, Utilities.isNullString(str2));
                    sQLiteStatement.bindString(11, Utilities.isNullString(Utilities.Crypt(str1)));
                    sQLiteStatement.bindString(20, Utilities.isNullString(arrayOfString2[8]));
                    sQLiteStatement.bindString(13, Utilities.isNullString(str8));
                    sQLiteStatement.bindString(14, Utilities.isNullString(str10));
                    sQLiteStatement.bindString(15, Utilities.isNullString(str));
                    sQLiteStatement.bindString(16, Utilities.isNullString(str));
                    sQLiteStatement.bindString(17, Utilities.isNullString(str));
                    sQLiteStatement.bindLong(18, bool3);
                    sQLiteStatement.bindLong(19, j);
                    if (arrayOfString2.length > 9 && arrayOfString2 != null) {
                      sQLiteStatement.bindString(12, Utilities.isNullString(arrayOfString2[9]));
                    } else {
                      sQLiteStatement.bindString(12, Utilities.isNullString(arrayOfString2[8]));
                    } 
                    if (bool1) {
                      sQLiteStatement.bindString(21, str8);
                      sQLiteStatement.executeUpdateDelete();
                    } else if (bool2) {
                      sQLiteStatement.bindLong(21, 0L);
                      sQLiteStatement.executeInsert();
                      StringBuilder stringBuilder1 = new StringBuilder();
                      this();
                      stringBuilder1.append(onstarApplication.getDeviceTypeId());
                      stringBuilder1.append("_ID");
                      PreferenceRT.SetValuePreference(stringBuilder1.toString(), true, dBFunctions._ctx);
                    } 
                    sQLiteStatement.clearBindings();
                  } 
                  SQLiteDatabase sQLiteDatabase = sQLiteDatabase1;
                  cursor.close();
                } else {
                  boolean bool1;
                  boolean bool2;
                  int j;
                  SQLiteStatement sQLiteStatement;
                  Cursor cursor1 = sQLiteDatabase2.query(pushNotificationTable, new String[] { "*" }, "alertId = ?", new String[] { (String)cursor }, null, null, null, null);
                  if (cursor1 != null && cursor1.moveToFirst()) {
                    sQLiteStatement = sQLiteDatabase1.compileStatement(str4);
                    bool1 = cursor1.getInt(15);
                    bool2 = true;
                    j = 0;
                  } else {
                    sQLiteStatement = sQLiteDatabase1.compileStatement(str3);
                    bool1 = false;
                    bool2 = false;
                    j = 1;
                  } 
                  if (bool2 || j) {
                    String str11 = Utilities.parseActionType(i).name();
                    try {
                      j = Integer.valueOf(arrayOfString2[7]).intValue();
                    } catch (Exception exception3) {
                      Utilities.escribeArchivo(TAG, "Error", exception3.getMessage());
                      j = 0;
                    } 
                    String str12 = "6";
                    if (j == 27 || j == 10) {
                      str11 = "FollowMe";
                      str12 = "12";
                    } else if (j == 15) {
                      str11 = "Velocidade";
                    } else if (j == 57 || j == 225) {
                      str11 = "Movimento";
                    } else if (j == 235) {
                      str11 = "Valet";
                    } else if (j == 208) {
                      str11 = "DTC";
                    } else if (j == -1) {
                      str11 = "Mantenimiento";
                    } else {
                      str12 = "";
                    } 
                    String str13 = arrayOfString2[8];
                    StringBuilder stringBuilder1 = new StringBuilder();
                    this();
                    stringBuilder1.append(str13.substring(8, 10));
                    stringBuilder1.append("-");
                    stringBuilder1.append(str13.substring(5, 7));
                    stringBuilder1.append("-");
                    try {
                      stringBuilder1.append(str13.substring(0, 4));
                      stringBuilder1.append(" ");
                      stringBuilder1.append(str13.substring(11, 19));
                      str13 = stringBuilder1.toString();
                      sQLiteStatement.bindString(1, Utilities.isNullString(""));
                      sQLiteStatement.bindString(2, Utilities.isNullString(str12));
                      sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(str11)));
                      sQLiteStatement.bindString(4, Utilities.isNullString(str13));
                      sQLiteStatement.bindString(5, Utilities.isNullString((String)cursor));
                      sQLiteStatement.bindString(6, Utilities.isNullString(Utilities.Crypt(str11)));
                      sQLiteStatement.bindString(7, Utilities.isNullString(str2));
                      sQLiteStatement.bindString(8, Utilities.isNullString(Utilities.Crypt(arrayOfString2[3])));
                      sQLiteStatement.bindString(9, Utilities.isNullString(Utilities.Crypt(arrayOfString2[4])));
                      sQLiteStatement.bindString(10, Utilities.isNullString(""));
                      sQLiteStatement.bindString(11, Utilities.isNullString(str11));
                      sQLiteStatement.bindString(12, Utilities.isNullString(""));
                      sQLiteStatement.bindString(13, Utilities.isNullString(""));
                      sQLiteStatement.bindString(14, Utilities.isNullString(str8));
                      sQLiteStatement.bindLong(15, bool1);
                      sQLiteStatement.bindLong(16, j);
                      if (bool2) {
                        sQLiteStatement.bindString(17, (String)cursor);
                        sQLiteStatement.executeUpdateDelete();
                      } else {
                        sQLiteStatement.bindLong(17, 0L);
                        sQLiteStatement.executeInsert();
                        StringBuilder stringBuilder2 = new StringBuilder();
                        this();
                        stringBuilder2.append(onstarApplication.getDeviceTypeId());
                        stringBuilder2.append("_ID");
                        String str14 = stringBuilder2.toString();
                        PreferenceRT.SetValuePreference(str14, true, this._ctx);
                      } 
                      DBFunctions dBFunctions1 = this;
                      sQLiteStatement.clearBindings();
                      str12 = str3;
                      SQLiteDatabase sQLiteDatabase = sQLiteDatabase1;
                    } catch (Exception exception1) {
                      // Byte code: goto -> 2516
                    } 
                    continue;
                  } 
                  String str10 = str3;
                  Exception exception = exception1;
                } 
              } else {
                Exception exception = exception1;
              } 
              Exception exception2 = exception1;
              String str7 = str3;
              continue;
            } 
          } catch (Exception exception) {
            Utilities.escribeArchivo(TAG, "ERROR", exception.getMessage());
          } 
        } 
      } 
    } 
  }
  
  public long readerNotificationAccions(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(historicalTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("readnotification");
    stringBuilder.append(" = 1 WHERE ");
    stringBuilder.append("vehicle_id");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public long readerNotificationAgendamiento(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(pushNotificationTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("readnotification");
    stringBuilder.append(" = 1 WHERE ");
    stringBuilder.append("deviceId");
    stringBuilder.append(" =?  AND ");
    stringBuilder.append("alertCodeId");
    stringBuilder.append(" =-1 ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public long readerNotificationAlerts(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(pushNotificationTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("readnotification");
    stringBuilder.append(" = 1 WHERE ");
    stringBuilder.append("deviceId");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public long readerNotificationDTC(String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(pushNotificationTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("readnotification");
    stringBuilder.append(" = 1 WHERE ");
    stringBuilder.append("deviceId");
    stringBuilder.append(" =?  AND ");
    stringBuilder.append("alertCodeId");
    stringBuilder.append(" =208 ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return 0L;
  }
  
  public void removeAllGmtCatalog() {
    getWritableDatabase().delete(this.GMT_CATALOG_KEY_GMT_TABLE, null, null);
  }
  
  public void removeAllTomTomStatistics() {
    getWritableDatabase().delete("tomtom_statistic", null, null);
  }
  
  public boolean reviewFavoriteName(String paramString1, String paramString2) {
    paramString2 = Utilities.Crypt(paramString2.replaceAll("'", "''"));
    Cursor cursor = getReadableDatabase().query("favorites_history", new String[] { "*" }, "name =? AND device_id =?  ", new String[] { paramString2, paramString1 }, null, null, null, null);
    boolean bool = cursor.moveToFirst();
    cursor.close();
    return bool;
  }
  
  public boolean rowExist(String paramString1, String paramString2, String paramString3) {
    paramString1 = paramString1.replaceAll("'", "''");
    paramString2 = Utilities.Crypt(paramString2);
    paramString1 = Utilities.Crypt(paramString1);
    Cursor cursor = getReadableDatabase().query("favorites_history", new String[] { "*" }, "address =? AND latlng =? AND device_id =?  ", new String[] { paramString1, paramString2, paramString3 }, null, null, null, null);
    boolean bool = cursor.moveToFirst();
    cursor.close();
    return bool;
  }
  
  public ArrayList<NavigationVO> selectNavigation(String paramString, int paramInt) {
    ArrayList<NavigationVO> arrayList1;
    ArrayList<NavigationVO> arrayList2;
    StringBuilder stringBuilder2 = null;
    String str1 = null;
    if (paramInt == 0)
      return null; 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(paramString);
    stringBuilder1.append(" = ?;");
    paramString = stringBuilder1.toString();
    stringBuilder1 = new StringBuilder();
    stringBuilder1.append(this.KEY_NAVIGATIONID);
    stringBuilder1.append(", ");
    stringBuilder1.append(this.KEY_LATSTART);
    stringBuilder1.append(", ");
    stringBuilder1.append(this.KEY_LONSTART);
    stringBuilder1.append(", ");
    stringBuilder1.append(this.KEY_LATEND);
    stringBuilder1.append(", ");
    stringBuilder1.append(this.KEY_LONEND);
    stringBuilder1.append(", ");
    stringBuilder1.append("address");
    stringBuilder1.append(", ");
    stringBuilder1.append(this.KEY_ACTIONID);
    stringBuilder1.append(", ");
    stringBuilder1.append(this.KEY_MESSAGEID);
    stringBuilder1.append(", ");
    stringBuilder1.append(this.KEY_ALERTID);
    stringBuilder1.append(", ");
    stringBuilder1.append(this.KEY_EVENTDATE);
    stringBuilder1.append(", ");
    stringBuilder1.append(this.KEY_RECDATE);
    String str2 = stringBuilder1.toString();
    stringBuilder1 = stringBuilder2;
    try {
      SQLiteDatabase sQLiteDatabase = getReadableDatabase();
      stringBuilder1 = stringBuilder2;
      String str4 = this.navigationTable;
      stringBuilder1 = stringBuilder2;
      String str3 = Integer.toString(paramInt);
      stringBuilder1 = stringBuilder2;
      Cursor cursor = sQLiteDatabase.query(str4, new String[] { str2 }, paramString, new String[] { str3 }, null, null, null, null);
      paramString = str1;
      stringBuilder1 = stringBuilder2;
      if (cursor.moveToFirst()) {
        stringBuilder1 = stringBuilder2;
        arrayList1 = new ArrayList();
        stringBuilder1 = stringBuilder2;
        this();
        try {
          boolean bool;
          NavigationVO navigationVO = new NavigationVO();
          this();
          do {
            navigationVO.setNavigationId(cursor.getInt(cursor.getColumnIndex(this.KEY_NAVIGATIONID)));
            navigationVO.setLatStart(Double.parseDouble(Utilities.Decrypt(cursor.getString(cursor.getColumnIndex(this.KEY_LATSTART)))));
            navigationVO.setLonStart(Double.parseDouble(Utilities.Decrypt(cursor.getString(cursor.getColumnIndex(this.KEY_LONSTART)))));
            navigationVO.setLatEnd(Double.parseDouble(Utilities.Decrypt(cursor.getString(cursor.getColumnIndex(this.KEY_LATEND)))));
            navigationVO.setLonEnd(Double.parseDouble(Utilities.Decrypt(cursor.getString(cursor.getColumnIndex(this.KEY_LONEND)))));
            navigationVO.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            navigationVO.setActionId(cursor.getInt(cursor.getColumnIndex(this.KEY_ACTIONID)));
            navigationVO.setMessageId(cursor.getInt(cursor.getColumnIndex(this.KEY_MESSAGEID)));
            navigationVO.setAlertId(cursor.getInt(cursor.getColumnIndex(this.KEY_ALERTID)));
            navigationVO.setEvent_date(cursor.getString(cursor.getColumnIndex(this.KEY_EVENTDATE)));
            navigationVO.setRec_date(cursor.getString(cursor.getColumnIndex(this.KEY_RECDATE)));
            arrayList1.add(navigationVO);
            bool = cursor.moveToNext();
          } while (bool);
        } catch (Exception exception) {
          ArrayList<NavigationVO> arrayList = arrayList1;
        } 
      } 
      arrayList2 = arrayList1;
      cursor.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "ERROR", exception.getMessage());
      arrayList1 = arrayList2;
    } 
    return arrayList1;
  }
  
  public ArrayList<SyncVO> selectSyncData(String paramString1, String paramString2) {
    String str1 = Utilities.Crypt(paramString2.toLowerCase().trim());
    ArrayList<SyncVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(configTable);
    stringBuilder.append(",");
    stringBuilder.append("favorites_history");
    String str2 = stringBuilder.toString();
    try {
      Cursor cursor = sQLiteDatabase.query(str2, new String[] { 
            "notifications", "language", "phone_number", "user", "email", "id_favs_history", "name", "address", "date", "device_id", 
            "type_item", "category", "latlng", "type_poi", "id_sync" }, "user =? AND device_id =?  AND type_item=?", new String[] { str1, paramString1, "Favorites" }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            SyncVO syncVO = new SyncVO();
            this(cursor.getString(0), cursor.getString(1), cursor.getString(2), Utilities.Decrypt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), Utilities.Decrypt(cursor.getString(6)), Utilities.Decrypt(cursor.getString(7)), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), Utilities.Decrypt(cursor.getString(12)), cursor.getString(13), cursor.getString(14));
            arrayList.add(syncVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: selectsyncData", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<SyncVO> selectSyncDataEdited(String paramString1, String paramString2) {
    String str1 = Utilities.Crypt(paramString2.toLowerCase().trim());
    ArrayList<SyncVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(configTable);
    stringBuilder.append(",");
    stringBuilder.append("favorites_history");
    String str2 = stringBuilder.toString();
    try {
      Cursor cursor = sQLiteDatabase.query(str2, new String[] { 
            "notifications", "language", "phone_number", "user", "email", "id_favs_history", "name", "address", "date", "device_id", 
            "type_item", "category", "latlng", "type_poi", "id_sync" }, "user =? AND device_id =? AND type_poi = 1", new String[] { str1, paramString1 }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            SyncVO syncVO = new SyncVO();
            this(cursor.getString(0), cursor.getString(1), cursor.getString(2), Utilities.Decrypt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), Utilities.Decrypt(cursor.getString(6)), Utilities.Decrypt(cursor.getString(7)), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), Utilities.Decrypt(cursor.getString(12)), cursor.getString(13), cursor.getString(14));
            arrayList.add(syncVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: selectsyncData", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public ArrayList<SyncVO> selectSyncDataSingle(String paramString1, String paramString2, String paramString3) {
    String str1 = Utilities.Crypt(paramString2.toLowerCase().trim());
    ArrayList<SyncVO> arrayList = new ArrayList();
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(configTable);
    stringBuilder.append(",");
    stringBuilder.append("favorites_history");
    String str2 = stringBuilder.toString();
    try {
      Cursor cursor = sQLiteDatabase.query(str2, new String[] { 
            "notifications", "language", "phone_number", "user", "email", "id_favs_history", "name", "address", "date", "device_id", 
            "type_item", "category", "latlng", "type_poi", "id_sync" }, "user =? AND device_id =?  AND type_item=? AND id_favs_history =?", new String[] { str1, paramString1, "Favorites", paramString3 }, null, null, null, null);
      try {
        if (cursor.moveToFirst()) {
          boolean bool;
          do {
            SyncVO syncVO = new SyncVO();
            this(cursor.getString(0), cursor.getString(1), cursor.getString(2), Utilities.Decrypt(cursor.getString(3)), cursor.getString(4), cursor.getString(5), Utilities.Decrypt(cursor.getString(6)), Utilities.Decrypt(cursor.getString(7)), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), Utilities.Decrypt(cursor.getString(12)), cursor.getString(13), cursor.getString(14));
            arrayList.add(syncVO);
            bool = cursor.moveToNext();
          } while (bool);
        } 
        return arrayList;
      } finally {
        try {
          exception.close();
        } catch (Exception exception1) {
          Utilities.escribeArchivo(TAG, "Error: selectsyncData", exception1.getMessage());
        } 
      } 
    } finally {}
  }
  
  public void setAutomaticMapUpdateDownload() {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(mapUpdateTable);
    stringBuilder.append(" (");
    stringBuilder.append("id_mapupdate");
    stringBuilder.append(", ");
    stringBuilder.append("oldVersion");
    stringBuilder.append(", ");
    stringBuilder.append("newVersion");
    stringBuilder.append(", ");
    stringBuilder.append("fileName");
    stringBuilder.append(", ");
    stringBuilder.append("automatic_download");
    stringBuilder.append(") VALUES (?, ?, ?, ?, ?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindLong(1, 20L);
    sQLiteStatement.bindString(2, "");
    sQLiteStatement.bindString(3, "");
    sQLiteStatement.bindString(4, "");
    sQLiteStatement.bindLong(5, 1L);
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
  }
  
  public long setLastUser(String paramString1, String paramString2, String paramString3) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      StringBuilder stringBuilder2 = new StringBuilder();
      this();
      stringBuilder2.append("DELETE FROM ");
      stringBuilder2.append(activity_log);
      stringBuilder2.append(" WHERE ");
      stringBuilder2.append("user");
      stringBuilder2.append(" =? ");
      SQLiteStatement sQLiteStatement2 = sQLiteDatabase.compileStatement(stringBuilder2.toString());
      sQLiteStatement2.bindString(1, Utilities.isNullString(Utilities.Crypt(paramString1)));
      sQLiteStatement2.executeUpdateDelete();
      sQLiteStatement2.clearBindings();
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append("INSERT INTO ");
      stringBuilder1.append(activity_log);
      stringBuilder1.append(" ( ");
      stringBuilder1.append("user");
      stringBuilder1.append(", ");
      stringBuilder1.append("language");
      stringBuilder1.append(", ");
      stringBuilder1.append("region");
      stringBuilder1.append(") VALUES (?, ?, ?);");
      SQLiteStatement sQLiteStatement1 = sQLiteDatabase.compileStatement(stringBuilder1.toString());
      sQLiteStatement1.bindString(1, Utilities.isNullString(Utilities.Crypt(paramString1)));
      sQLiteStatement1.bindString(2, Utilities.isNullString(Utilities.Crypt(paramString2)));
      sQLiteStatement1.bindString(3, Utilities.isNullString(Utilities.Crypt(paramString3)));
      sQLiteStatement1.executeInsert();
      sQLiteStatement1.clearBindings();
      return 0L;
    } finally {}
  }
  
  public long timeFromStartFollowMe(String paramString, Context paramContext) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    String str1 = Utilities.Crypt("FollowMe");
    String str2 = historicalTable;
    byte b = 0;
    Cursor cursor = sQLiteDatabase.query(str2, new String[] { "*" }, "NAME =? AND id_error = 1 AND vehicle_id =? ", new String[] { str1, paramString }, null, null, "date_time DESC", "1");
    long l = 0L;
    if (cursor != null && cursor.moveToFirst()) {
      str2 = cursor.getString(12);
      if (!Utilities.Decrypt(cursor.getString(5)).equals("0") || !Utilities.Decrypt(cursor.getString(6)).equals("0")) {
        l = Utilities.diffMinutesToActualTime(str2, "", paramContext, paramString);
        if (l >= 16L) {
          b = 2;
        } else {
          b = 1;
        } 
      } 
      if (b == 1) {
        paramString = "corriendo";
      } else if (b == 2) {
        paramString = "no esta corriendo";
      } else {
        paramString = "Nunca se ha ejecutado";
      } 
      String str = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fecha Ultimo Follow Me: ");
      stringBuilder.append(str2);
      stringBuilder.append(" status:");
      stringBuilder.append(paramString);
      stringBuilder.append(" MessageID::");
      stringBuilder.append(cursor.getString(13));
      Utilities.escribeArchivo(str, "timeFromStartFollowMe", stringBuilder.toString());
    } else {
      Utilities.escribeArchivo(TAG, "timeFromStartFollowMe", "No encontr� ningun registro para follow me");
    } 
    cursor.close();
    return l;
  }
  
  public void unSetAutomaticMapUpdateDownload() {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ");
    stringBuilder.append(mapUpdateTable);
    stringBuilder.append(" (");
    stringBuilder.append("id_mapupdate");
    stringBuilder.append(", ");
    stringBuilder.append("oldVersion");
    stringBuilder.append(", ");
    stringBuilder.append("newVersion");
    stringBuilder.append(", ");
    stringBuilder.append("fileName");
    stringBuilder.append(", ");
    stringBuilder.append("automatic_download");
    stringBuilder.append(") VALUES (?, ?, ?, ?,?);");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindLong(1, 20L);
    sQLiteStatement.bindString(2, "");
    sQLiteStatement.bindString(3, "");
    sQLiteStatement.bindString(4, "");
    sQLiteStatement.bindLong(5, 0L);
    sQLiteStatement.executeInsert();
    sQLiteStatement.clearBindings();
  }
  
  public long updateAttemptToPay(String paramString, VehicleCatalogVO paramVehicleCatalogVO) {
    long l2 = 0L;
    long l1 = l2;
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      l1 = l2;
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("UPDATE renewal_dialog_status SET plan_status_inform =?, attempt_status=?, amount_to_pay=?, monetary_currency=?  WHERE deviceId=?");
      l1 = l2;
      sQLiteStatement.bindString(1, paramVehicleCatalogVO.getPlanStatusInform());
      l1 = l2;
      sQLiteStatement.bindString(2, paramVehicleCatalogVO.getPayAttemptStatus());
      l1 = l2;
      sQLiteStatement.bindString(3, paramVehicleCatalogVO.getAmountToPay());
      l1 = l2;
      sQLiteStatement.bindString(4, paramVehicleCatalogVO.getMonetaryCurrency());
      l1 = l2;
      sQLiteStatement.bindString(5, paramString);
      l1 = l2;
      l2 = sQLiteStatement.executeUpdateDelete();
      l1 = l2;
      sQLiteStatement.clearBindings();
      l1 = l2;
      sQLiteDatabase.close();
      l1 = l2;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
    } 
    return l1;
  }
  
  public void updateCfg(UserPreferenceVO paramUserPreferenceVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(configTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("language");
    stringBuilder.append(" =? , ");
    stringBuilder.append("phone_number");
    stringBuilder.append(" =? , ");
    stringBuilder.append("notifications");
    stringBuilder.append(" =? , ");
    stringBuilder.append("user");
    stringBuilder.append(" =? , ");
    stringBuilder.append(KEY_ACCESS);
    stringBuilder.append(" =? , ");
    stringBuilder.append("terms_conditions");
    stringBuilder.append(" =? , ");
    stringBuilder.append("save_access");
    stringBuilder.append(" =? , ");
    stringBuilder.append("city");
    stringBuilder.append(" =? , ");
    stringBuilder.append("country");
    stringBuilder.append(" =? , ");
    stringBuilder.append("email");
    stringBuilder.append(" =? , ");
    stringBuilder.append("remind_download");
    stringBuilder.append(" =? , ");
    stringBuilder.append("terms_conditions_date");
    stringBuilder.append(" =? ,");
    stringBuilder.append("TimeOfSet");
    stringBuilder.append(" =? ,");
    stringBuilder.append("region");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("user");
    stringBuilder.append(" =?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getLanguage())));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getPhone_number())));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramUserPreferenceVO.getNotifications()));
    sQLiteStatement.bindString(4, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getUser())));
    sQLiteStatement.bindString(5, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getMagic_spell())));
    sQLiteStatement.bindString(6, Utilities.isNullString(paramUserPreferenceVO.getTerms_conditions()));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramUserPreferenceVO.getSave_access()));
    sQLiteStatement.bindString(8, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getCity())));
    sQLiteStatement.bindString(9, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getCountry())));
    sQLiteStatement.bindString(10, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getUser())));
    sQLiteStatement.bindString(11, Utilities.isNullString(paramUserPreferenceVO.getRemindDownload()));
    sQLiteStatement.bindString(12, Utilities.isNullString(paramUserPreferenceVO.getTerms_conditions_date()));
    sQLiteStatement.bindString(13, Utilities.isNullString(paramUserPreferenceVO.getTimeOfSet()));
    sQLiteStatement.bindString(14, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getRegion())));
    sQLiteStatement.bindString(15, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getUser().toLowerCase().trim())));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateCfg(UserPreferenceVO paramUserPreferenceVO, String paramString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(configTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("language");
    stringBuilder.append(" =? , ");
    stringBuilder.append("phone_number");
    stringBuilder.append(" =? , ");
    stringBuilder.append("notifications");
    stringBuilder.append(" =? , ");
    stringBuilder.append("user");
    stringBuilder.append(" =? , ");
    stringBuilder.append(KEY_ACCESS);
    stringBuilder.append(" =? , ");
    stringBuilder.append("terms_conditions");
    stringBuilder.append(" =? , ");
    stringBuilder.append("save_access");
    stringBuilder.append(" =? , ");
    stringBuilder.append("city");
    stringBuilder.append(" =? , ");
    stringBuilder.append("country");
    stringBuilder.append(" =? , ");
    stringBuilder.append("email");
    stringBuilder.append(" =? , ");
    stringBuilder.append("remind_download");
    stringBuilder.append(" =? , ");
    stringBuilder.append("region");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("id_cfg");
    stringBuilder.append(" =?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getLanguage())));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getPhone_number())));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramUserPreferenceVO.getNotifications()));
    sQLiteStatement.bindString(4, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getUser())));
    sQLiteStatement.bindString(5, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getMagic_spell())));
    sQLiteStatement.bindString(6, Utilities.isNullString(paramUserPreferenceVO.getTerms_conditions()));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramUserPreferenceVO.getSave_access()));
    sQLiteStatement.bindString(8, Utilities.isNullString(paramUserPreferenceVO.getCity()));
    sQLiteStatement.bindString(9, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getCountry())));
    sQLiteStatement.bindString(10, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getEMAIL())));
    sQLiteStatement.bindString(11, Utilities.isNullString(paramUserPreferenceVO.getRemindDownload()));
    sQLiteStatement.bindString(12, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getRegion())));
    sQLiteStatement.bindString(13, Utilities.isNullString(paramString));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateConfiguration(String paramString, int paramInt) {
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("UPDATE ");
      stringBuilder.append(configTable);
      stringBuilder.append(" SET ");
      stringBuilder.append(KEY_SEND_ROUTE);
      stringBuilder.append(" =? WHERE ");
      stringBuilder.append("user");
      stringBuilder.append(" = ?");
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
      sQLiteStatement.bindDouble(1, paramInt);
      sQLiteStatement.bindString(2, paramString);
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
      sQLiteDatabase.close();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
    } 
  }
  
  public void updateEditFavoriteHistoryMarker(int paramInt, FavoritesHistoryVO paramFavoritesHistoryVO) {
    SQLiteStatement sQLiteStatement = getWritableDatabase().compileStatement("UPDATE favorites_history SET name =?, id_cfg =?, address =?, date =?, device_id =?, type_item =?, category =?, latlng =?, type_poi =?, id_sync =?  WHERE id_favs_history =? ");
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramFavoritesHistoryVO.getName())));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramFavoritesHistoryVO.getId_cfg()));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramFavoritesHistoryVO.getAddress())));
    sQLiteStatement.bindString(4, Utilities.isNullString(paramFavoritesHistoryVO.getDate()));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramFavoritesHistoryVO.getDevice_id()));
    sQLiteStatement.bindString(6, Utilities.isNullString(paramFavoritesHistoryVO.getType_item()));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramFavoritesHistoryVO.getCategory()));
    sQLiteStatement.bindString(8, Utilities.isNullString(Utilities.Crypt(paramFavoritesHistoryVO.getLatlng())));
    sQLiteStatement.bindString(9, Utilities.isNullString(paramFavoritesHistoryVO.getType_poi()));
    sQLiteStatement.bindString(10, Utilities.isNullString(paramFavoritesHistoryVO.getId_sync()));
    sQLiteStatement.bindString(11, Utilities.isNullString(String.valueOf(paramInt)));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateFavoriteHistory(int paramInt, FavoritesHistoryVO paramFavoritesHistoryVO) {
    SQLiteStatement sQLiteStatement = getWritableDatabase().compileStatement("UPDATE favorites_history SET name =?, id_cfg =?, address =?, date =?, device_id =?, type_item =?, category =?, latlng =?, type_poi =?, id_sync =?  WHERE id_favs_history =? ");
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramFavoritesHistoryVO.getName())));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramFavoritesHistoryVO.getId_cfg()));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramFavoritesHistoryVO.getAddress())));
    sQLiteStatement.bindString(4, Utilities.isNullString(paramFavoritesHistoryVO.getDate()));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramFavoritesHistoryVO.getDevice_id()));
    sQLiteStatement.bindString(6, Utilities.isNullString(paramFavoritesHistoryVO.getType_item()));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramFavoritesHistoryVO.getCategory()));
    sQLiteStatement.bindString(8, Utilities.isNullString(Utilities.Crypt(paramFavoritesHistoryVO.getLatlng())));
    sQLiteStatement.bindString(9, Utilities.isNullString(paramFavoritesHistoryVO.getType_poi()));
    sQLiteStatement.bindString(10, Utilities.isNullString(paramFavoritesHistoryVO.getId_sync()));
    sQLiteStatement.bindString(11, Utilities.isNullString(String.valueOf(paramInt)));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateFavoriteHistory(int paramInt, String paramString1, String paramString2) {
    SQLiteStatement sQLiteStatement = getWritableDatabase().compileStatement("UPDATE favorites_history SET name =?, type_poi =?  WHERE id_favs_history =? ");
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramString1)));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(3, Utilities.isNullString(String.valueOf(paramInt)));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateFavoriteHistoryMarker(int paramInt, String paramString1, String paramString2) {
    SQLiteStatement sQLiteStatement = getWritableDatabase().compileStatement("UPDATE favorites_history SET name =?, type_poi =?  WHERE id_favs_history =? ");
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramString1)));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(3, Utilities.isNullString(String.valueOf(paramInt)));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateFindmePoint(String paramString1, String paramString2, String paramString3) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(findmePointsTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("latitude");
    stringBuilder.append(" =?, ");
    stringBuilder.append("longitude");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("id_device");
    stringBuilder.append(" =?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(Utilities.Crypt(paramString2)));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramString3)));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramString1)));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateFollowMeStatus() {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(followmeTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("in_process");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("in_process");
    stringBuilder.append(" =?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindLong(1, 0L);
    sQLiteStatement.bindLong(2, 1L);
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateGTM(UserPreferenceVO paramUserPreferenceVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(configTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("TimeOfSet");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("user");
    stringBuilder.append(" =?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramUserPreferenceVO.getTimeOfSet()));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramUserPreferenceVO.getUser().toLowerCase().trim())));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateHistorical(Boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    paramString5 = Utilities.getDateTime();
    StringBuilder stringBuilder = new StringBuilder();
    if (paramBoolean.booleanValue()) {
      str = "id_history";
    } else {
      str = "id_status";
    } 
    stringBuilder.append(str);
    stringBuilder.append(" =?");
    String str = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(historicalTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("id_error");
    stringBuilder.append(" =?, ");
    stringBuilder.append("lat");
    stringBuilder.append(" =?, ");
    stringBuilder.append("lng");
    stringBuilder.append(" =?, ");
    stringBuilder.append("date_time");
    stringBuilder.append(" =? WHERE ");
    stringBuilder.append(str);
    stringBuilder.append(" AND deleted = 0");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramString3)));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramString4)));
    sQLiteStatement.bindString(4, Utilities.isNullString(paramString5));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramString1));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateHistorical(Boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, int paramInt1, int paramInt2) {
    String str3;
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected((onstarApplication)GlobalMembers.contexGlobal, TAG);
    if (userDevicesVO != null) {
      str3 = Utilities.getDateTime(userDevicesVO.getDeviceId(), GlobalMembers.contexGlobal);
    } else {
      str3 = "";
    } 
    if (paramInt2 != Enums.Services.FindMe.GetCode())
      paramString5 = str3; 
    StringBuilder stringBuilder = new StringBuilder();
    if (paramBoolean.booleanValue()) {
      str1 = "id_history";
    } else {
      str1 = "id_status";
    } 
    stringBuilder.append(str1);
    stringBuilder.append(" =?");
    String str1 = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(historicalTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("id_error");
    stringBuilder.append(" =?, ");
    stringBuilder.append("lat");
    stringBuilder.append(" =?, ");
    stringBuilder.append("lng");
    stringBuilder.append(" =?, ");
    stringBuilder.append("date_time");
    stringBuilder.append(" =?, ");
    stringBuilder.append("message_id");
    stringBuilder.append(" =?, ");
    stringBuilder.append("completion_code");
    stringBuilder.append(" =?, ");
    stringBuilder.append("requestErroId");
    stringBuilder.append(" =?, ");
    stringBuilder.append("responseErrorId");
    stringBuilder.append(" =?, ");
    stringBuilder.append("messageResponse");
    stringBuilder.append(" =?, ");
    stringBuilder.append("gpsstatus");
    stringBuilder.append(" =? WHERE ");
    stringBuilder.append(str1);
    stringBuilder.append(" AND deleted = 0");
    String str2 = stringBuilder.toString();
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(str2);
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramString3)));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramString4)));
    sQLiteStatement.bindString(4, Utilities.isNullString(paramString5));
    sQLiteStatement.bindString(5, Utilities.isNullString(paramString6));
    sQLiteStatement.bindString(6, Utilities.isNullString(paramString7));
    sQLiteStatement.bindString(7, Utilities.isNullString(paramString8));
    sQLiteStatement.bindString(8, Utilities.isNullString(paramString9));
    sQLiteStatement.bindString(9, paramString10);
    sQLiteStatement.bindLong(10, paramInt1);
    sQLiteStatement.bindString(11, Utilities.isNullString(paramString1));
    try {
      if (!sQLiteDatabase.isOpen())
        getWritableDatabase(); 
      sQLiteStatement.executeUpdateDelete();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: updateHistorical 2990", exception.getMessage());
    } 
    sQLiteStatement.clearBindings();
  }
  
  public void updateHistoricalOrphans() {
    try {
      String str1 = getSelectedVehicle(GlobalMembers.userLogged).getDeviceId();
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      String str2 = Utilities.getDateTime(str1, GlobalMembers.contexGlobal);
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("UPDATE ");
      stringBuilder.append(historicalTable);
      stringBuilder.append(" SET ");
      stringBuilder.append("id_error");
      stringBuilder.append(" =?  WHERE datetime(");
      stringBuilder.append("date_time");
      stringBuilder.append(" , '+6 Minute') <= datetime('");
      stringBuilder.append(str2);
      stringBuilder.append(" ') AND ");
      stringBuilder.append("id_error");
      stringBuilder.append(" =? AND deleted = 0");
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
      sQLiteStatement.bindString(1, String.valueOf(2));
      sQLiteStatement.bindString(2, String.valueOf(0));
      sQLiteStatement.executeUpdateDelete();
      sQLiteStatement.clearBindings();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: updateHistoricalOrphans", exception.getMessage());
    } 
  }
  
  public boolean updateMapUpdateData(MapUpdateVO paramMapUpdateVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(mapUpdateTableV2);
    stringBuilder.append(" SET ");
    stringBuilder.append("p8_map_version");
    stringBuilder.append(" =?, ");
    stringBuilder.append("server_map_version");
    stringBuilder.append(" =?, ");
    stringBuilder.append("upgrade_version");
    stringBuilder.append(" =?, ");
    stringBuilder.append("server_file_name");
    stringBuilder.append(" =?, ");
    stringBuilder.append("file_map_status");
    stringBuilder.append(" =?, ");
    stringBuilder.append("file_map_on_parts");
    stringBuilder.append(" =?, ");
    stringBuilder.append("user_wants_upgrade");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("id_map_update");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, paramMapUpdateVO.getP8MapVersion());
    sQLiteStatement.bindString(2, paramMapUpdateVO.getServerMapVersion());
    sQLiteStatement.bindString(3, paramMapUpdateVO.getUpgradeOldversionNewversion());
    sQLiteStatement.bindString(4, paramMapUpdateVO.getServerFileName());
    sQLiteStatement.bindLong(5, paramMapUpdateVO.getFileMapStatus());
    sQLiteStatement.bindLong(6, paramMapUpdateVO.getFileMapOnParts());
    sQLiteStatement.bindLong(7, paramMapUpdateVO.isUserWantsUpdrade());
    sQLiteStatement.bindLong(8, paramMapUpdateVO.getIdMapUpdate());
    long l = sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return (l != 0L);
  }
  
  public boolean updateMapUpdateData(PushNotificationsVO paramPushNotificationsVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(mapUpdateTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("oldVersion");
    stringBuilder.append(" =?, ");
    stringBuilder.append("newVersion");
    stringBuilder.append(" =?, ");
    stringBuilder.append("fileName");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("id_mapupdate");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramPushNotificationsVO.getOldVersion()));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramPushNotificationsVO.getNewVersion()));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramPushNotificationsVO.getFileName()));
    sQLiteStatement.bindLong(4, 1L);
    long l = sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return (l != 0L);
  }
  
  public boolean updateMapUpdateDataPart(PushNotificationsVO paramPushNotificationsVO) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(mapUpdateTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("oldVersion");
    stringBuilder.append(" =?, ");
    stringBuilder.append("newVersion");
    stringBuilder.append(" =?, ");
    stringBuilder.append("fileName");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("id_mapupdate");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramPushNotificationsVO.getOldVersion()));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramPushNotificationsVO.getNewVersion()));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramPushNotificationsVO.getFileName()));
    sQLiteStatement.bindLong(4, 100L);
    long l = sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return (l != 0L);
  }
  
  public long updateRenewalDialogDateExpire(String paramString1, String paramString2) {
    long l2 = 0L;
    long l1 = l2;
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      l1 = l2;
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("UPDATE renewal_dialog_status SET status_renewal_date =?  WHERE deviceId=?");
      l1 = l2;
      sQLiteStatement.bindString(1, paramString2);
      l1 = l2;
      sQLiteStatement.bindString(2, paramString1);
      l1 = l2;
      l2 = sQLiteStatement.executeUpdateDelete();
      l1 = l2;
      sQLiteStatement.clearBindings();
      l1 = l2;
      sQLiteDatabase.close();
      l1 = l2;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
    } 
    return l1;
  }
  
  public long updateRenewalDialogDaysExpire(String paramString1, String paramString2) {
    long l2 = 0L;
    long l1 = l2;
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      l1 = l2;
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("UPDATE renewal_dialog_status SET status_renewal_days =?  WHERE deviceId=?");
      l1 = l2;
      sQLiteStatement.bindString(1, paramString2);
      l1 = l2;
      sQLiteStatement.bindString(2, paramString1);
      l1 = l2;
      l2 = sQLiteStatement.executeUpdateDelete();
      l1 = l2;
      sQLiteStatement.clearBindings();
      l1 = l2;
      sQLiteDatabase.close();
      l1 = l2;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
    } 
    return l1;
  }
  
  public long updateRenewalDialogStatus(String paramString1, String paramString2) {
    long l2 = 0L;
    long l1 = l2;
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      l1 = l2;
      SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement("UPDATE renewal_dialog_status SET status_renewal_dialog =? WHERE deviceId=?");
      l1 = l2;
      sQLiteStatement.bindString(1, paramString2);
      l1 = l2;
      sQLiteStatement.bindString(2, paramString1);
      l1 = l2;
      l2 = sQLiteStatement.executeUpdateDelete();
      l1 = l2;
      sQLiteStatement.clearBindings();
      l1 = l2;
      sQLiteDatabase.close();
      l1 = l2;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, exception);
    } 
    return l1;
  }
  
  public void updateSaveAccess(String paramString1, String paramString2) {
    SQLiteDatabase sQLiteDatabase = getReadableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(configTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("save_access");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("user");
    stringBuilder.append(" =? ");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramString1.toLowerCase().trim())));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateSynchronizedFavoriteHistory(int paramInt1, int paramInt2, String paramString1, String paramString2) {
    SQLiteStatement sQLiteStatement = getWritableDatabase().compileStatement("UPDATE favorites_history SET date =?, type_poi =?, id_sync =?  WHERE id_favs_history =? ");
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramString1));
    sQLiteStatement.bindLong(3, paramInt2);
    sQLiteStatement.bindString(4, Utilities.isNullString(String.valueOf(paramInt1)));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public long updateTermsAndConditionsSent(String paramString1, String paramString2) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(configTable);
    stringBuilder.append(" SET ");
    stringBuilder.append("terms_conditions_sent");
    stringBuilder.append(" =?  WHERE ");
    stringBuilder.append("user");
    stringBuilder.append(" =?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString1));
    sQLiteStatement.bindString(2, Utilities.isNullString(Utilities.Crypt(paramString2.toLowerCase().trim())));
    long l = sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
    return l;
  }
  
  public void updateUser(String paramString1, String paramString2, boolean paramBoolean) {
    SQLiteStatement sQLiteStatement1;
    SQLiteStatement sQLiteStatement2;
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    boolean bool = existUser(paramString1).booleanValue();
    String str = "Y";
    if (!bool) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("INSERT INTO ");
      stringBuilder.append(configTable);
      stringBuilder.append(" (");
      stringBuilder.append(KEY_ACCESS);
      stringBuilder.append(", ");
      stringBuilder.append("save_access");
      stringBuilder.append(", ");
      stringBuilder.append("user");
      stringBuilder.append(") VALUES (?, ?, ?);");
      sQLiteStatement2 = sQLiteDatabase.compileStatement(stringBuilder.toString());
      sQLiteStatement2.bindString(1, Utilities.isNullString(Utilities.Crypt(paramString2)));
      if (!paramBoolean)
        str = "N"; 
      sQLiteStatement2.bindString(2, Utilities.isNullString(str));
      sQLiteStatement2.bindString(3, Utilities.isNullString(Utilities.Crypt(paramString1.toLowerCase().trim())));
      sQLiteStatement2.executeInsert();
      sQLiteStatement1 = sQLiteStatement2;
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("UPDATE ");
      stringBuilder.append(configTable);
      stringBuilder.append(" SET ");
      stringBuilder.append(KEY_ACCESS);
      stringBuilder.append(" =? ,");
      stringBuilder.append("save_access");
      stringBuilder.append(" =?  WHERE ");
      stringBuilder.append("user");
      stringBuilder.append(" =?");
      sQLiteStatement2 = sQLiteStatement2.compileStatement(stringBuilder.toString());
      sQLiteStatement2.bindString(1, Utilities.isNullString(Utilities.Crypt(paramString2)));
      if (!paramBoolean)
        str = "N"; 
      sQLiteStatement2.bindString(2, Utilities.isNullString(str));
      sQLiteStatement2.bindString(3, Utilities.isNullString(Utilities.Crypt(sQLiteStatement1.toLowerCase().trim())));
      sQLiteStatement2.executeUpdateDelete();
      sQLiteStatement1 = sQLiteStatement2;
    } 
    sQLiteStatement1.clearBindings();
  }
  
  public void updateVehicleDTCSelector(String paramString1, String paramString2) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(vehicleCatalog);
    stringBuilder.append(" SET ");
    stringBuilder.append(KEY_DTC_SELECTOR);
    stringBuilder.append(" = ? WHERE ");
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString2));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateVehicleGmtValue(String paramString1, String paramString2, String paramString3) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(vehicleCatalog);
    stringBuilder.append(" SET ");
    stringBuilder.append(KEY_VEHICLE_GTM);
    stringBuilder.append(" = ? , ");
    stringBuilder.append(KEY_VEHICLE_IDGTM);
    stringBuilder.append(" = ? WHERE ");
    stringBuilder.append(KEY_VEHICLE_DEVICEID);
    stringBuilder.append(" = ?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramString3));
    sQLiteStatement.bindString(3, Utilities.isNullString(paramString1));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public void updateVehicleSelected(String paramString1, String paramString2, String paramString3) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UPDATE ");
    stringBuilder.append(vehicleCatalog);
    stringBuilder.append(" SET ");
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append(" =? WHERE ");
    stringBuilder.append(KEY_ID_VEHICLE);
    stringBuilder.append(" =? AND ");
    stringBuilder.append(KEY_VEHICLE_USER);
    stringBuilder.append(" =? AND ");
    stringBuilder.append(KEY_VEHICLE_SELECTED);
    stringBuilder.append(" !=?");
    SQLiteStatement sQLiteStatement = sQLiteDatabase.compileStatement(stringBuilder.toString());
    sQLiteStatement.bindString(1, Utilities.isNullString(paramString3));
    sQLiteStatement.bindString(2, Utilities.isNullString(paramString2));
    sQLiteStatement.bindString(3, Utilities.isNullString(Utilities.Crypt(paramString1)));
    sQLiteStatement.bindString(4, Utilities.isNullString("L"));
    sQLiteStatement.executeUpdateDelete();
    sQLiteStatement.clearBindings();
  }
  
  public boolean userDataTableHandler(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString1 != null) {
      bool1 = bool2;
      if (!paramString1.isEmpty()) {
        bool1 = bool2;
        if (paramString2 != null) {
          bool1 = bool2;
          if (!paramString2.isEmpty())
            if (paramString3 == null) {
              bool1 = bool2;
            } else {
              if (paramString2.equalsIgnoreCase("0"))
                return false; 
              if (paramBoolean) {
                bool1 = existTheftStatusVehicleDeviceId(paramString2).booleanValue();
              } else {
                if (paramString3.equalsIgnoreCase("4000") || paramString3.equalsIgnoreCase("4")) {
                  bool1 = bool2;
                  if (!existTheftStatusVehicleDeviceId(paramString2).booleanValue())
                    bool1 = insertTheftStatusVehicle(paramString1, paramString2, paramString3); 
                  return bool1;
                } 
                deleteTheftStatusVehicle(paramString2);
                bool1 = bool2;
              } 
            }  
        } 
      } 
    } 
    return bool1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/DAO/DBFunctions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */