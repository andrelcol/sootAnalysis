package com.roadtrack.onstar.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.MessagesObjects;
import com.roadtrack.onstar.BO.Needed;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.CryptLibPackage.CryptLib;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.PaymentCardResponse;
import com.roadtrack.onstar.VO.PushNotificationsVO;
import com.roadtrack.onstar.VO.RenewalPlansListResponseO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import com.roadtrack.onstar.activities.GenericWVActivity;
import com.roadtrack.onstar.adapter.AdapterDialogMultiCheckBox;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponseList;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.RenewalPlans_Interface;
import com.roadtrack.onstar.async_tasks.tasks.GetPaymentProcessTask;
import com.roadtrack.onstar.async_tasks.tasks.GetRenewalPlansTask;
import com.roadtrack.onstar.async_tasks.tasks.GetURLChangeCreditCard;
import com.roadtrack.onstar.entities.MessageRTMobile;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.ui.my_plan.OriginalRenewalActivity;
import com.roadtrack.onstar.ui.my_plan.PaymentCardInfo;
import com.roadtrack.onstar.ui.my_plan.RenewalVehiclesListActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
  private static final String IV;
  
  private static final String IV_2;
  
  private static final String IV_3;
  
  private static final String IV_Account;
  
  private static final String PACKAGE_NAME;
  
  private static final String[] PACKAGE_NAMES = Utilities.class.getPackage().getName().toString().split("\\.");
  
  private static PowerManager.WakeLock PowerManagerWakelock;
  
  private static PowerManager _PowerManager;
  
  static Dialog dialogTheft;
  
  static boolean isTheftDialogRunning;
  
  private static final String leite;
  
  private static final String leiteAccount;
  
  private static final String leiteV2;
  
  private static final String leiteV3;
  
  static {
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
    stringBuilder.toString();
    dialogTheft = null;
    isTheftDialogRunning = false;
    _PowerManager = null;
    PowerManagerWakelock = null;
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(71));
    stringBuilder.append(toAscii(72));
    stringBuilder.append(toAscii(102));
    stringBuilder.append(toAscii(119));
    stringBuilder.append(toAscii(48));
    stringBuilder.append(toAscii(66));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(100));
    stringBuilder.append(toAscii(50));
    stringBuilder.append(toAscii(48));
    stringBuilder.append(toAscii(54));
    stringBuilder.append(toAscii(67));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(86));
    stringBuilder.append(toAscii(81));
    stringBuilder.append(toAscii(51));
    IV = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(48));
    stringBuilder.append(toAscii(50));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(67));
    stringBuilder.append(toAscii(77));
    stringBuilder.append(toAscii(55));
    stringBuilder.append(toAscii(100));
    stringBuilder.append(toAscii(57));
    stringBuilder.append(toAscii(48));
    stringBuilder.append(toAscii(77));
    stringBuilder.append(toAscii(56));
    stringBuilder.append(toAscii(80));
    stringBuilder.append(toAscii(81));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(86));
    stringBuilder.append(toAscii(76));
    IV_2 = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(66));
    stringBuilder.append(toAscii(83));
    stringBuilder.append(toAscii(68));
    stringBuilder.append(toAscii(73));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(107));
    stringBuilder.append(toAscii(74));
    stringBuilder.append(toAscii(73));
    stringBuilder.append(toAscii(57));
    stringBuilder.append(toAscii(50));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(72));
    stringBuilder.append(toAscii(68));
    stringBuilder.append(toAscii(85));
    stringBuilder.append(toAscii(56));
    stringBuilder.append(toAscii(78));
    stringBuilder.append(toAscii(48));
    stringBuilder.append(toAscii(102));
    stringBuilder.append(toAscii(114));
    stringBuilder.append(toAscii(85));
    stringBuilder.append(toAscii(57));
    stringBuilder.append(toAscii(110));
    stringBuilder.append(toAscii(115));
    stringBuilder.append(toAscii(50));
    stringBuilder.append(toAscii(85));
    stringBuilder.append(toAscii(113));
    stringBuilder.append(toAscii(116));
    stringBuilder.append(toAscii(55));
    stringBuilder.append(toAscii(53));
    stringBuilder.append(toAscii(87));
    stringBuilder.append(toAscii(122));
    stringBuilder.append(toAscii(88));
    IV_3 = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(117));
    stringBuilder.append(toAscii(56));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(98));
    stringBuilder.append(toAscii(54));
    stringBuilder.append(toAscii(100));
    stringBuilder.append(toAscii(50));
    stringBuilder.append(toAscii(104));
    stringBuilder.append(toAscii(71));
    stringBuilder.append(toAscii(50));
    stringBuilder.append(toAscii(48));
    stringBuilder.append(toAscii(66));
    stringBuilder.append(toAscii(57));
    stringBuilder.append(toAscii(72));
    stringBuilder.append(toAscii(100));
    stringBuilder.append(toAscii(55));
    stringBuilder.append(toAscii(50));
    stringBuilder.append(toAscii(71));
    IV_Account = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(40));
    stringBuilder.append(toAscii(70));
    stringBuilder.append(toAscii(119));
    stringBuilder.append(toAscii(108));
    stringBuilder.append(toAscii(128));
    stringBuilder.append(toAscii(116));
    stringBuilder.append(toAscii(79));
    stringBuilder.append(toAscii(75));
    stringBuilder.append(toAscii(53));
    stringBuilder.append(toAscii(44));
    stringBuilder.append(toAscii(114));
    stringBuilder.append(toAscii(82));
    stringBuilder.append(toAscii(101));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(43));
    stringBuilder.append(toAscii(78));
    stringBuilder.append(toAscii(118));
    stringBuilder.append(toAscii(48));
    stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(42));
    stringBuilder.append(toAscii(69));
    stringBuilder.append(toAscii(83));
    stringBuilder.append(toAscii(116));
    stringBuilder.append(toAscii(107));
    stringBuilder.append(toAscii(53));
    stringBuilder.append(toAscii(54));
    stringBuilder.append(toAscii(56));
    stringBuilder.append(toAscii(43));
    leite = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(42));
    stringBuilder.append(toAscii(78));
    stringBuilder.append(toAscii(69));
    stringBuilder.append(toAscii(80));
    stringBuilder.append(toAscii(116));
    stringBuilder.append(toAscii(107));
    stringBuilder.append(toAscii(50));
    stringBuilder.append(toAscii(57));
    stringBuilder.append(toAscii(53));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(43));
    leiteV2 = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(85));
    stringBuilder.append(toAscii(54));
    stringBuilder.append(toAscii(107));
    stringBuilder.append(toAscii(56));
    stringBuilder.append(toAscii(55));
    stringBuilder.append(toAscii(48));
    stringBuilder.append(toAscii(72));
    stringBuilder.append(toAscii(101));
    stringBuilder.append(toAscii(55));
    stringBuilder.append(toAscii(79));
    stringBuilder.append(toAscii(99));
    stringBuilder.append(toAscii(74));
    stringBuilder.append(toAscii(105));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(117));
    stringBuilder.append(toAscii(117));
    leiteV3 = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(64));
    stringBuilder.append(toAscii(113));
    stringBuilder.append(toAscii(65));
    stringBuilder.append(toAscii(49));
    stringBuilder.append(toAscii(50));
    stringBuilder.append(toAscii(103));
    stringBuilder.append(toAscii(53));
    stringBuilder.append(toAscii(75));
    stringBuilder.append(toAscii(54));
    stringBuilder.append(toAscii(108));
    stringBuilder.append(toAscii(51));
    stringBuilder.append(toAscii(100));
    stringBuilder.append(toAscii(55));
    stringBuilder.append(toAscii(117));
    stringBuilder.append(toAscii(106));
    stringBuilder.append(toAscii(61));
    leiteAccount = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(toAscii(69));
    stringBuilder.append(toAscii(109));
    stringBuilder.append(toAscii(116));
    stringBuilder.append(toAscii(55));
    stringBuilder.append(toAscii(80));
    stringBuilder.append(toAscii(97));
    stringBuilder.append(toAscii(99));
    stringBuilder.append(toAscii(90));
    stringBuilder.append(toAscii(57));
    stringBuilder.append(toAscii(71));
    stringBuilder.append(toAscii(79));
    stringBuilder.append(toAscii(52));
    stringBuilder.append(toAscii(72));
    stringBuilder.append(toAscii(56));
    stringBuilder.append(toAscii(119));
    stringBuilder.append(toAscii(50));
    stringBuilder.toString();
  }
  
  public static boolean ActivityRunning(String paramString, Context paramContext) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'activity'
    //   3: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   6: checkcast android/app/ActivityManager
    //   9: astore_1
    //   10: iconst_1
    //   11: istore_2
    //   12: aload_1
    //   13: iconst_1
    //   14: invokevirtual getRunningTasks : (I)Ljava/util/List;
    //   17: invokeinterface iterator : ()Ljava/util/Iterator;
    //   22: astore_1
    //   23: aload_1
    //   24: invokeinterface hasNext : ()Z
    //   29: ifeq -> 57
    //   32: aload_1
    //   33: invokeinterface next : ()Ljava/lang/Object;
    //   38: checkcast android/app/ActivityManager$RunningTaskInfo
    //   41: getfield topActivity : Landroid/content/ComponentName;
    //   44: invokevirtual getClassName : ()Ljava/lang/String;
    //   47: aload_0
    //   48: invokevirtual equals : (Ljava/lang/Object;)Z
    //   51: ifeq -> 23
    //   54: goto -> 59
    //   57: iconst_0
    //   58: istore_2
    //   59: iload_2
    //   60: ireturn
  }
  
  public static String ChangeHour(String paramString1, String paramString2, String paramString3, String paramString4) {
    try {
      long l = formatMillis(FormatHourMinutesSeconds(Float.parseFloat(paramString3) - Float.parseFloat(paramString2)));
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
      this(paramString4);
      Date date = simpleDateFormat.parse(paramString1);
      date.setTime(date.getTime() + l);
      return simpleDateFormat.format(date);
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(exception);
      escribeArchivo("Utilities", "CATCH", stringBuilder.toString());
      return "";
    } 
  }
  
  public static String Crypt(String paramString) {
    if (paramString != null && paramString.length() > 0) {
      paramString = EncryptData(paramString);
    } else {
      paramString = "";
    } 
    return paramString;
  }
  
  public static String CryptAccount(String paramString) {
    if (paramString != null && paramString.length() > 0) {
      paramString = EncryptAccountData(paramString);
    } else {
      paramString = "";
    } 
    return paramString;
  }
  
  public static String CryptDTC(String paramString) {
    if (paramString != null && paramString.length() > 0) {
      paramString = EncryptDtcData(paramString);
    } else {
      paramString = "";
    } 
    return paramString;
  }
  
  public static String CryptMoip(String paramString) {
    if (paramString != null && paramString.length() > 0) {
      paramString = EncryptMoipData(paramString);
    } else {
      paramString = "";
    } 
    return paramString;
  }
  
  public static Date DateStringToDate(String paramString) {
    SimpleDateFormat simpleDateFormat2 = null;
    SimpleDateFormat simpleDateFormat1 = simpleDateFormat2;
    if (paramString != null) {
      simpleDateFormat1 = simpleDateFormat2;
      if (!paramString.isEmpty())
        try {
          paramString = paramString.replace(",", "");
          simpleDateFormat1 = new SimpleDateFormat();
          this("dd-MM-yyyy", Locale.ENGLISH);
          Date date = simpleDateFormat1.parse(paramString);
        } catch (Exception exception) {
          simpleDateFormat1 = simpleDateFormat2;
        }  
    } 
    return (Date)simpleDateFormat1;
  }
  
  public static String Decrypt(String paramString) {
    if (paramString != null && paramString.length() > 0) {
      paramString = DesEncryptData(paramString);
    } else {
      paramString = "";
    } 
    return paramString;
  }
  
  public static String DecryptMoip(String paramString) {
    if (paramString != null && paramString.length() > 0) {
      paramString = DesEncryptMoipData(paramString);
    } else {
      paramString = "";
    } 
    return paramString;
  }
  
  public static String DesEncryptData(String paramString) {
    String str;
    try {
      CryptLib cryptLib = new CryptLib();
      this();
      String str1 = leite;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(Needed.crosstrash);
      stringBuilder.append(str1);
      paramString = cryptLib.decrypt(paramString, CryptLib.SHA256(stringBuilder.toString(), 31), IV);
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: DesEncryptData", exception.toString());
      str = "";
    } 
    return str;
  }
  
  public static String DesEncryptMoipData(String paramString) {
    String str;
    try {
      CryptLib cryptLib = new CryptLib();
      this();
      String str1 = leiteV3;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(Needed.crosstrashV3);
      stringBuilder.append(str1);
      paramString = cryptLib.decrypt(paramString, stringBuilder.toString(), IV_3);
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: DesEncryptDtcData", exception.toString());
      str = "";
    } 
    return str;
  }
  
  public static String DeviceUuidFactory(Context paramContext) {
    return "123456789";
  }
  
  public static Dialog DialogEditFav(Context paramContext) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.deviceName, 2131689751);
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_lbl_actualizar_1, 2131689954);
    String str3 = getStringFromConfigList(paramContext, stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    dialog.setContentView(View.inflate(paramContext, 2131427385, null));
    dialog.setCancelable(true);
    ((TextView)dialog.findViewById(2131297182)).setText(str1);
    ((Button)dialog.findViewById(2131296347)).setText(str2);
    ((Button)dialog.findViewById(2131296344)).setText(str3);
    return dialog;
  }
  
  public static Dialog DialogGTM(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, String paramString4, boolean paramBoolean2, String paramString5, boolean paramBoolean3, String paramString6) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    dialog.setContentView(View.inflate(paramContext, 2131427416, null));
    dialog.setCancelable(true);
    ((TextView)dialog.findViewById(2131296260)).setTextSize(16.0F);
    ((TextView)dialog.findViewById(2131296257)).setTextSize(16.0F);
    ((TextView)dialog.findViewById(2131296260)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296260)).setText(paramString1);
    ((TextView)dialog.findViewById(2131296257)).setText(paramString2);
    ((TextView)dialog.findViewById(2131296916)).setText(paramString4);
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296343)).setVisibility(0);
      ((Button)dialog.findViewById(2131296343)).setText(paramString5);
    } else {
      ((Button)dialog.findViewById(2131296343)).setVisibility(8);
    } 
    if (paramBoolean3) {
      ((Button)dialog.findViewById(2131296344)).setVisibility(0);
      ((Button)dialog.findViewById(2131296344)).setText(paramString6);
    } else {
      ((Button)dialog.findViewById(2131296344)).setVisibility(8);
    } 
    if (paramBoolean1) {
      ((CheckBox)dialog.findViewById(2131296461)).setText(paramString3);
      ((CheckBox)dialog.findViewById(2131296461)).setVisibility(0);
    } else {
      ((CheckBox)dialog.findViewById(2131296461)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static String EncryptAccountData(String paramString) {
    String str;
    try {
      CryptLib cryptLib = new CryptLib();
      this();
      String str1 = leiteAccount;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(Needed.crosstrashAccount);
      stringBuilder.append(str1);
      paramString = cryptLib.encrypt(paramString, CryptLib.SHA256(stringBuilder.toString(), 31), IV_Account);
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: EncryptAccountData", exception.toString());
      str = "";
    } 
    return str;
  }
  
  public static String EncryptData(String paramString) {
    String str;
    try {
      CryptLib cryptLib = new CryptLib();
      this();
      String str1 = leite;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(Needed.crosstrash);
      stringBuilder.append(str1);
      paramString = cryptLib.encrypt(paramString, CryptLib.SHA256(stringBuilder.toString(), 31), IV);
    } catch (Exception exception) {
      str = "";
    } 
    return str;
  }
  
  public static String EncryptDtcData(String paramString) {
    String str;
    try {
      CryptLib cryptLib = new CryptLib();
      this();
      String str1 = leiteV2;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(Needed.crosstrashV2);
      stringBuilder.append(str1);
      paramString = cryptLib.encrypt(paramString, CryptLib.SHA256(stringBuilder.toString(), 31), IV_2);
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: EncryptDtcData", exception.toString());
      str = "";
    } 
    return str;
  }
  
  public static String EncryptMoipData(String paramString) {
    String str;
    try {
      CryptLib cryptLib = new CryptLib();
      this();
      String str1 = leiteV3;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(Needed.crosstrashV3);
      stringBuilder.append(str1);
      paramString = cryptLib.encrypt(paramString, stringBuilder.toString(), IV_3);
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: EncryptDtcData", exception.toString());
      str = "";
    } 
    return str;
  }
  
  public static String FormatHour(float paramFloat) {
    int i = (int)(paramFloat * 60.0F);
    TimeUnit timeUnit = TimeUnit.MINUTES;
    long l1 = i;
    long l3 = timeUnit.toHours(l1);
    long l2 = TimeUnit.MINUTES.toMinutes(l1) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(l1));
    l1 = l2;
    if (l2 < 0L)
      l1 = l2 * -1L; 
    return String.format("%02d:%02d", new Object[] { Long.valueOf(l3), Long.valueOf(l1) });
  }
  
  public static String FormatHourMinutesSeconds(float paramFloat) {
    int i = (int)(paramFloat * 60.0F);
    TimeUnit timeUnit = TimeUnit.MINUTES;
    long l1 = i;
    long l3 = timeUnit.toHours(l1);
    long l2 = TimeUnit.MINUTES.toMinutes(l1) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(l1));
    l1 = l2;
    if (l2 < 0L)
      l1 = l2 * -1L; 
    return String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(l3), Long.valueOf(l1), Integer.valueOf(0) });
  }
  
  public static void GCMHeartBeat(Context paramContext) {
    if (paramContext != null)
      try {
        Intent intent = new Intent();
        this("com.google.android.intent.action.GTALK_HEARTBEAT");
        paramContext.sendBroadcast(intent);
        intent = new Intent();
        this("com.google.android.intent.action.MCS_HEARTBEAT");
        paramContext.sendBroadcast(intent);
      } catch (Exception exception) {
        escribeArchivo("GCMHeartBeat", "Error", exception.getMessage());
      }  
  }
  
  public static Location[] GetCentralGeoCoordinate(Location[] paramArrayOfLocation) {
    long l1;
    Location location1 = new Location("CentralLocation");
    Location location3 = new Location("MinLocation");
    Location location2 = new Location("MaxLocation");
    Location[] arrayOfLocation = new Location[3];
    Bundle bundle = new Bundle();
    int i = paramArrayOfLocation.length;
    long l2 = 0L;
    if (i > 1) {
      double d6 = paramArrayOfLocation[0].getLatitude();
      double d3 = paramArrayOfLocation[0].getLongitude();
      double d1 = paramArrayOfLocation[0].getLatitude();
      double d2 = paramArrayOfLocation[0].getLongitude();
      i = 0;
      while (i < paramArrayOfLocation.length) {
        double d7 = d6;
        if (paramArrayOfLocation[i].getLatitude() < d6)
          d7 = paramArrayOfLocation[i].getLatitude(); 
        double d8 = d3;
        if (paramArrayOfLocation[i].getLongitude() < d3)
          d8 = paramArrayOfLocation[i].getLongitude(); 
        double d9 = d1;
        if (paramArrayOfLocation[i].getLatitude() > d1)
          d9 = paramArrayOfLocation[i].getLatitude(); 
        d1 = d2;
        if (paramArrayOfLocation[i].getLongitude() > d2)
          d1 = paramArrayOfLocation[i].getLongitude(); 
        i++;
        d2 = d1;
        d6 = d7;
        d3 = d8;
        d1 = d9;
      } 
      double d5 = (d1 - d6) / 2.0D;
      double d4 = (d2 - d3) / 2.0D;
      location3.setLatitude(d6);
      location3.setLongitude(d3);
      location2.setLatitude(d1);
      location2.setLongitude(d2);
      l1 = (long)location3.distanceTo(location2);
      bundle.putLong("MAXDISTANCE", l1);
      location1.setExtras(bundle);
      location1.setLatitude(d6 + d5);
      location1.setLongitude(d3 + d4);
    } else {
      Location location = location1;
      location1 = location;
      l1 = l2;
      if (paramArrayOfLocation.length > 0) {
        bundle.putLong("MAXDISTANCE", 0L);
        location.setExtras(bundle);
        location1 = paramArrayOfLocation[0];
        location3 = paramArrayOfLocation[0];
        location2 = paramArrayOfLocation[0];
        l1 = l2;
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Distancia: ");
    stringBuilder.append(l1);
    escribeArchivo("Utilities", "MAPZOOM", stringBuilder.toString());
    arrayOfLocation[0] = location1;
    arrayOfLocation[1] = location3;
    arrayOfLocation[2] = location2;
    return arrayOfLocation;
  }
  
  public static int GetIdentResourceImage(String paramString, Context paramContext) {
    return paramContext.getResources().getIdentifier(paramString.toLowerCase(), "drawable", paramContext.getPackageName());
  }
  
  public static Hashtable<String, Object> GetMessage(String paramString) {
    String str = paramString;
    try {
      String[] arrayOfString;
      if (paramString.substring(0, 1).equals("["))
        str = paramString.substring(1, paramString.length()); 
      paramString = str;
      if (str.substring(str.length() - 1, str.length()).equals("]"))
        paramString = str.substring(0, str.length() - 4); 
      if (paramString.indexOf("|") > 0) {
        arrayOfString = paramString.split("\\|");
      } else {
        arrayOfString = new String[] { (String)arrayOfString };
      } 
      MessagesObjects.Platinum_OpCodes platinum_OpCodes = GetOpcode(arrayOfString[0]);
      if (arrayOfString.length >= 2) {
        String[] arrayOfString1 = new String[arrayOfString.length - 1];
        System.arraycopy(arrayOfString, 1, arrayOfString1, 0, arrayOfString.length - 1);
        arrayOfString = arrayOfString1;
      } else {
        arrayOfString = new String[] { " " };
      } 
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      this();
      hashtable.put("OpCode", Integer.valueOf(platinum_OpCodes.GetOpCode()));
      hashtable.put("messageBody", arrayOfString);
      return (Hashtable)hashtable;
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: NewMessage", exception.getMessage());
      return null;
    } 
  }
  
  private static MessagesObjects.Platinum_OpCodes GetOpcode(String paramString) {
    return MessagesObjects.Platinum_OpCodes.GetValue(Integer.parseInt(paramString));
  }
  
  public static boolean GetTaskInBackGround(Context paramContext) {
    boolean bool2;
    int i = Build.VERSION.SDK_INT;
    byte b = 0;
    boolean bool1 = false;
    if (i >= 21) {
      Iterator iterator = ((ActivityManager)paramContext.getSystemService("activity")).getAppTasks().iterator();
      while (iterator.hasNext()) {
        if ((((ActivityManager.AppTask)iterator.next()).getTaskInfo()).baseIntent.getComponent().getPackageName().equals(paramContext.getPackageName()))
          bool1 = true; 
      } 
      bool2 = bool1;
    } else {
      List list = ((ActivityManager)paramContext.getSystemService("activity")).getRecentTasks(2147483647, 2);
      list.size();
      bool1 = false;
      while (true) {
        bool2 = bool1;
        if (b < list.size()) {
          if (((ActivityManager.RecentTaskInfo)list.get(b)).baseIntent.getComponent().getPackageName().equals(paramContext.getPackageName()))
            bool1 = true; 
          b++;
          continue;
        } 
        break;
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("activiti es:");
    stringBuilder.append(bool2);
    escribeArchivo("Utilities", "Utilities", stringBuilder.toString());
    return bool2;
  }
  
  public static boolean GetTaskInBackGround(Context paramContext, Class<?> paramClass) {
    boolean bool4 = false;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = bool4;
    try {
      if (Build.VERSION.SDK_INT >= 21) {
        bool3 = bool4;
        Iterator<ActivityManager.AppTask> iterator = ((ActivityManager)paramContext.getSystemService("activity")).getAppTasks().iterator();
        while (true) {
          bool3 = bool2;
          bool1 = bool2;
          if (iterator.hasNext()) {
            bool3 = bool2;
            ActivityManager.RecentTaskInfo recentTaskInfo = ((ActivityManager.AppTask)iterator.next()).getTaskInfo();
            bool3 = bool2;
            if (recentTaskInfo.baseIntent.getComponent().getPackageName().equals(paramContext.getPackageName())) {
              bool3 = bool2;
              if (recentTaskInfo.topActivity.getClassName().equals(paramClass.getName()))
                bool2 = true; 
            } 
            continue;
          } 
          break;
        } 
      } else {
        bool3 = bool4;
        bool2 = isActivityRunning(paramContext, paramClass);
        if (bool2)
          bool1 = true; 
      } 
    } catch (Exception exception) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Error");
      stringBuilder1.append(exception.getMessage());
      escribeArchivo("Utilities", "GetTaskInBackGround", stringBuilder1.toString());
      bool1 = bool3;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("activiti es:");
    stringBuilder.append(bool1);
    escribeArchivo("Utilities", "Utilities", stringBuilder.toString());
    return bool1;
  }
  
  @TargetApi(9)
  public static String GetUUID(Context paramContext) {
    String str = "";
    try {
      String str1 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      str = str1;
      try {
        if ("9774d56d682e549c".equals(str1))
          str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId(); 
      } catch (Exception exception) {
        str = str1;
        escribeArchivo("Utilities", "Error: UnsupportedEncodingException", exception.getMessage());
        str = str1;
        if (Build.SERIAL.equals("unknown")) {
          str = str1;
          str1 = UUIDRandom(paramContext);
          str = str1;
        } else {
          str = str1;
          str1 = Build.SERIAL;
          str = str1;
        } 
      } 
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: GetUUID", exception.getMessage());
      UUIDRandom(paramContext);
    } 
    return str;
  }
  
  public static final void NotificacionConIntentFindMe(Context paramContext, int paramInt, Integer paramInteger, String paramString1, String paramString2, Intent paramIntent) {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(paramContext);
    builder.setSmallIcon(paramInt);
    builder.setContentTitle(paramString1);
    builder.setContentText(paramString2);
    builder.setAutoCancel(true);
    builder.setSound(RingtoneManager.getDefaultUri(2));
    builder.setContentIntent(PendingIntent.getActivity(paramContext, 0, paramIntent, 469762048));
    NotificationManager notificationManager = (NotificationManager)paramContext.getSystemService("notification");
    if (26 <= Build.VERSION.SDK_INT) {
      NotificationChannel notificationChannel = new NotificationChannel(GlobalMembers.CHANNEL_ID, GlobalMembers.CHANNEL_NAME, GlobalMembers.CHANNEL_IMPORTANCE);
      builder.setChannelId(GlobalMembers.CHANNEL_ID);
      notificationManager.createNotificationChannel(notificationChannel);
    } 
    notificationManager.notify(paramInteger.intValue(), builder.build());
  }
  
  public static void SendNotify(Context paramContext, String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: new com/roadtrack/onstar/VO/StringsResourcesVO
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #16
    //   9: aload_0
    //   10: aload #16
    //   12: getfield global_lbl_accionlocalizame_1 : Ljava/lang/String;
    //   15: ldc_w 2131689877
    //   18: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   21: astore #19
    //   23: aload_0
    //   24: aload #16
    //   26: getfield global_lbl_accionsigueme_1 : Ljava/lang/String;
    //   29: ldc_w 2131689885
    //   32: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   35: astore #15
    //   37: aload_0
    //   38: aload #16
    //   40: getfield global_lbl_accionlucesybocina_1 : Ljava/lang/String;
    //   43: ldc_w 2131689879
    //   46: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   49: astore #18
    //   51: aload_0
    //   52: aload #16
    //   54: getfield global_lbl_accionluces_1 : Ljava/lang/String;
    //   57: ldc_w 2131689878
    //   60: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   63: astore #10
    //   65: aload_0
    //   66: aload #16
    //   68: getfield global_lbl_accioncerrarpuertas_1 : Ljava/lang/String;
    //   71: ldc_w 2131689854
    //   74: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   77: astore #8
    //   79: aload_0
    //   80: aload #16
    //   82: getfield global_lbl_accionabrirpuertas_1 : Ljava/lang/String;
    //   85: ldc_w 2131689842
    //   88: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   91: astore #6
    //   93: aload_0
    //   94: aload #16
    //   96: getfield global_lbl_acciondesactivarpincode_1 : Ljava/lang/String;
    //   99: ldc_w 2131689856
    //   102: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   105: astore #9
    //   107: aload_0
    //   108: aload #16
    //   110: getfield global_lbl_accionalertavel_1 : Ljava/lang/String;
    //   113: ldc_w 2131689851
    //   116: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   119: astore #14
    //   121: aload_0
    //   122: aload #16
    //   124: getfield global_lbl_accionalertabocina_1 : Ljava/lang/String;
    //   127: ldc_w 2131689844
    //   130: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   133: astore #17
    //   135: aload_0
    //   136: aload #16
    //   138: getfield global_lbl_accionpids_1 : Ljava/lang/String;
    //   141: ldc_w 2131689884
    //   144: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   147: astore #7
    //   149: aload_0
    //   150: aload #16
    //   152: getfield global_lbl_accionalertamov_1 : Ljava/lang/String;
    //   155: ldc_w 2131689845
    //   158: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   161: astore #13
    //   163: aload_0
    //   164: aload #16
    //   166: getfield global_lbl_accionalertavalet_1 : Ljava/lang/String;
    //   169: ldc_w 2131689849
    //   172: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   175: astore #11
    //   177: aload_0
    //   178: aload #16
    //   180: getfield global_lbl_accionenviarruta_1 : Ljava/lang/String;
    //   183: ldc_w 2131689874
    //   186: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   189: astore #12
    //   191: aload_0
    //   192: aload #16
    //   194: getfield pid_main_lbl_diagnotico_12 : Ljava/lang/String;
    //   197: ldc_w 2131690247
    //   200: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   203: astore #16
    //   205: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   208: invokevirtual booleanValue : ()Z
    //   211: pop
    //   212: aload_1
    //   213: ldc_w '1'
    //   216: invokevirtual equals : (Ljava/lang/Object;)Z
    //   219: ifeq -> 250
    //   222: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   225: invokevirtual booleanValue : ()Z
    //   228: ifeq -> 239
    //   231: ldc_w 2131165522
    //   234: istore #4
    //   236: goto -> 244
    //   239: ldc_w 2131165521
    //   242: istore #4
    //   244: aload #19
    //   246: astore_1
    //   247: goto -> 807
    //   250: aload_1
    //   251: ldc_w '2'
    //   254: invokevirtual equals : (Ljava/lang/Object;)Z
    //   257: ifne -> 799
    //   260: aload_1
    //   261: getstatic com/roadtrack/onstar/Enums$Services.FollowMeUUx : Lcom/roadtrack/onstar/Enums$Services;
    //   264: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   267: invokevirtual equals : (Ljava/lang/Object;)Z
    //   270: ifeq -> 276
    //   273: goto -> 799
    //   276: aload_1
    //   277: ldc_w '34'
    //   280: invokevirtual equals : (Ljava/lang/Object;)Z
    //   283: istore #5
    //   285: ldc_w 2131165524
    //   288: istore #4
    //   290: iload #5
    //   292: ifeq -> 318
    //   295: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   298: invokevirtual booleanValue : ()Z
    //   301: ifeq -> 307
    //   304: goto -> 312
    //   307: ldc_w 2131165523
    //   310: istore #4
    //   312: aload #18
    //   314: astore_1
    //   315: goto -> 807
    //   318: aload_1
    //   319: getstatic com/roadtrack/onstar/Enums$Services.Ligths : Lcom/roadtrack/onstar/Enums$Services;
    //   322: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   325: invokevirtual equals : (Ljava/lang/Object;)Z
    //   328: ifeq -> 342
    //   331: ldc_w 2131165525
    //   334: istore #4
    //   336: aload #10
    //   338: astore_1
    //   339: goto -> 807
    //   342: aload_1
    //   343: getstatic com/roadtrack/onstar/Enums$Services.Horn : Lcom/roadtrack/onstar/Enums$Services;
    //   346: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   349: invokevirtual equals : (Ljava/lang/Object;)Z
    //   352: ifne -> 776
    //   355: aload_1
    //   356: getstatic com/roadtrack/onstar/Enums$Services.HornF1 : Lcom/roadtrack/onstar/Enums$Services;
    //   359: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   362: invokevirtual equals : (Ljava/lang/Object;)Z
    //   365: ifeq -> 371
    //   368: goto -> 776
    //   371: aload_1
    //   372: ldc_w '35'
    //   375: invokevirtual equals : (Ljava/lang/Object;)Z
    //   378: ifeq -> 414
    //   381: aload_0
    //   382: invokestatic getResolution : (Landroid/content/Context;)I
    //   385: pop
    //   386: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   389: invokevirtual booleanValue : ()Z
    //   392: ifeq -> 403
    //   395: ldc_w 2131165527
    //   398: istore #4
    //   400: goto -> 408
    //   403: ldc_w 2131165526
    //   406: istore #4
    //   408: aload #8
    //   410: astore_1
    //   411: goto -> 807
    //   414: aload_1
    //   415: ldc_w '36'
    //   418: invokevirtual equals : (Ljava/lang/Object;)Z
    //   421: ifeq -> 452
    //   424: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   427: invokevirtual booleanValue : ()Z
    //   430: ifeq -> 441
    //   433: ldc_w 2131165533
    //   436: istore #4
    //   438: goto -> 446
    //   441: ldc_w 2131165532
    //   444: istore #4
    //   446: aload #6
    //   448: astore_1
    //   449: goto -> 807
    //   452: aload_1
    //   453: ldc_w '37'
    //   456: invokevirtual equals : (Ljava/lang/Object;)Z
    //   459: ifeq -> 473
    //   462: ldc_w 2131165675
    //   465: istore #4
    //   467: aload #9
    //   469: astore_1
    //   470: goto -> 807
    //   473: aload_1
    //   474: ldc_w '38'
    //   477: invokevirtual equals : (Ljava/lang/Object;)Z
    //   480: ifne -> 748
    //   483: aload_1
    //   484: getstatic com/roadtrack/onstar/Enums$Services.ParkingUUx : Lcom/roadtrack/onstar/Enums$Services;
    //   487: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   490: invokevirtual equals : (Ljava/lang/Object;)Z
    //   493: ifeq -> 499
    //   496: goto -> 748
    //   499: aload_1
    //   500: ldc_w '39'
    //   503: invokevirtual equals : (Ljava/lang/Object;)Z
    //   506: ifne -> 736
    //   509: aload_1
    //   510: getstatic com/roadtrack/onstar/Enums$Services.SpeedUUx : Lcom/roadtrack/onstar/Enums$Services;
    //   513: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   516: invokevirtual equals : (Ljava/lang/Object;)Z
    //   519: ifeq -> 525
    //   522: goto -> 736
    //   525: aload_1
    //   526: getstatic com/roadtrack/onstar/Enums$Services.SpeedAlways : Lcom/roadtrack/onstar/Enums$Services;
    //   529: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   532: invokevirtual equals : (Ljava/lang/Object;)Z
    //   535: ifeq -> 569
    //   538: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   541: invokevirtual booleanValue : ()Z
    //   544: ifeq -> 558
    //   547: ldc_w 2131165531
    //   550: istore #4
    //   552: aload #14
    //   554: astore_1
    //   555: goto -> 807
    //   558: ldc_w 2131165530
    //   561: istore #4
    //   563: aload #14
    //   565: astore_1
    //   566: goto -> 807
    //   569: aload_1
    //   570: ldc_w '52'
    //   573: invokevirtual equals : (Ljava/lang/Object;)Z
    //   576: ifeq -> 607
    //   579: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   582: invokevirtual booleanValue : ()Z
    //   585: ifeq -> 596
    //   588: ldc_w 2131165535
    //   591: istore #4
    //   593: goto -> 601
    //   596: ldc_w 2131165534
    //   599: istore #4
    //   601: aload #11
    //   603: astore_1
    //   604: goto -> 807
    //   607: aload_1
    //   608: getstatic com/roadtrack/onstar/Enums$Services.pid : Lcom/roadtrack/onstar/Enums$Services;
    //   611: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   614: invokevirtual equals : (Ljava/lang/Object;)Z
    //   617: ifeq -> 631
    //   620: ldc_w 2131165678
    //   623: istore #4
    //   625: aload #7
    //   627: astore_1
    //   628: goto -> 807
    //   631: aload_1
    //   632: getstatic com/roadtrack/onstar/Enums$Services.SendPNDNavigationCommand : Lcom/roadtrack/onstar/Enums$Services;
    //   635: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   638: invokevirtual equals : (Ljava/lang/Object;)Z
    //   641: ifeq -> 655
    //   644: ldc_w 2131165677
    //   647: istore #4
    //   649: aload #12
    //   651: astore_1
    //   652: goto -> 807
    //   655: aload_1
    //   656: getstatic com/roadtrack/onstar/Enums$Services.DTCAction : Lcom/roadtrack/onstar/Enums$Services;
    //   659: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   662: invokevirtual equals : (Ljava/lang/Object;)Z
    //   665: ifeq -> 726
    //   668: aload_2
    //   669: ldc_w 'title'
    //   672: invokevirtual equals : (Ljava/lang/Object;)Z
    //   675: ifeq -> 684
    //   678: aload #16
    //   680: astore_1
    //   681: goto -> 718
    //   684: new java/lang/StringBuilder
    //   687: dup
    //   688: invokespecial <init> : ()V
    //   691: astore_1
    //   692: aload_1
    //   693: aload #16
    //   695: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   698: pop
    //   699: aload_1
    //   700: ldc_w ' '
    //   703: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   706: pop
    //   707: aload_1
    //   708: aload_2
    //   709: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   712: pop
    //   713: aload_1
    //   714: invokevirtual toString : ()Ljava/lang/String;
    //   717: astore_1
    //   718: ldc_w 2131165635
    //   721: istore #4
    //   723: goto -> 807
    //   726: aload_2
    //   727: astore_1
    //   728: ldc_w 2131165536
    //   731: istore #4
    //   733: goto -> 807
    //   736: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   739: invokevirtual booleanValue : ()Z
    //   742: ifeq -> 558
    //   745: goto -> 547
    //   748: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   751: invokevirtual booleanValue : ()Z
    //   754: ifeq -> 765
    //   757: ldc_w 2131165529
    //   760: istore #4
    //   762: goto -> 770
    //   765: ldc_w 2131165528
    //   768: istore #4
    //   770: aload #13
    //   772: astore_1
    //   773: goto -> 807
    //   776: invokestatic isAndinos : ()Ljava/lang/Boolean;
    //   779: invokevirtual booleanValue : ()Z
    //   782: ifeq -> 788
    //   785: goto -> 793
    //   788: ldc_w 2131165523
    //   791: istore #4
    //   793: aload #17
    //   795: astore_1
    //   796: goto -> 807
    //   799: ldc_w 2131165676
    //   802: istore #4
    //   804: aload #15
    //   806: astore_1
    //   807: new java/lang/StringBuilder
    //   810: dup
    //   811: invokespecial <init> : ()V
    //   814: astore_2
    //   815: aload_2
    //   816: ldc_w 'TITLE:'
    //   819: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   822: pop
    //   823: aload_2
    //   824: aload_1
    //   825: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   828: pop
    //   829: aload_2
    //   830: ldc_w ' MESSAGE:'
    //   833: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   836: pop
    //   837: aload_2
    //   838: aload_3
    //   839: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   842: pop
    //   843: ldc 'Utilities'
    //   845: ldc_w 'PUSH-LOCAL'
    //   848: aload_2
    //   849: invokevirtual toString : ()Ljava/lang/String;
    //   852: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   855: aload_0
    //   856: iconst_0
    //   857: new android/content/Intent
    //   860: dup
    //   861: aload_0
    //   862: ldc_w com/roadtrack/onstar/MainActivity
    //   865: invokespecial <init> : (Landroid/content/Context;Ljava/lang/Class;)V
    //   868: ldc_w 469762048
    //   871: invokestatic getActivity : (Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   874: astore #6
    //   876: aload_0
    //   877: ldc_w 'notification'
    //   880: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   883: checkcast android/app/NotificationManager
    //   886: astore_2
    //   887: new androidx/core/app/NotificationCompat$Builder
    //   890: dup
    //   891: aload_0
    //   892: invokespecial <init> : (Landroid/content/Context;)V
    //   895: astore_0
    //   896: aload_0
    //   897: aload_3
    //   898: invokevirtual setContentText : (Ljava/lang/CharSequence;)Landroidx/core/app/NotificationCompat$Builder;
    //   901: pop
    //   902: new androidx/core/app/NotificationCompat$BigTextStyle
    //   905: dup
    //   906: invokespecial <init> : ()V
    //   909: astore #7
    //   911: aload #7
    //   913: aload_3
    //   914: invokevirtual bigText : (Ljava/lang/CharSequence;)Landroidx/core/app/NotificationCompat$BigTextStyle;
    //   917: pop
    //   918: aload_0
    //   919: aload #7
    //   921: invokevirtual setStyle : (Landroidx/core/app/NotificationCompat$Style;)Landroidx/core/app/NotificationCompat$Builder;
    //   924: pop
    //   925: aload_0
    //   926: aload_1
    //   927: invokevirtual setContentTitle : (Ljava/lang/CharSequence;)Landroidx/core/app/NotificationCompat$Builder;
    //   930: pop
    //   931: aload_0
    //   932: iload #4
    //   934: invokevirtual setSmallIcon : (I)Landroidx/core/app/NotificationCompat$Builder;
    //   937: pop
    //   938: aload_0
    //   939: iconst_1
    //   940: invokevirtual setAutoCancel : (Z)Landroidx/core/app/NotificationCompat$Builder;
    //   943: pop
    //   944: aload_0
    //   945: aload #6
    //   947: invokevirtual setContentIntent : (Landroid/app/PendingIntent;)Landroidx/core/app/NotificationCompat$Builder;
    //   950: pop
    //   951: aload_0
    //   952: invokestatic currentTimeMillis : ()J
    //   955: invokevirtual setWhen : (J)Landroidx/core/app/NotificationCompat$Builder;
    //   958: pop
    //   959: aload_0
    //   960: iconst_m1
    //   961: invokevirtual setDefaults : (I)Landroidx/core/app/NotificationCompat$Builder;
    //   964: pop
    //   965: aload_0
    //   966: iconst_1
    //   967: invokevirtual setNumber : (I)Landroidx/core/app/NotificationCompat$Builder;
    //   970: pop
    //   971: bipush #26
    //   973: getstatic android/os/Build$VERSION.SDK_INT : I
    //   976: if_icmpgt -> 1009
    //   979: new android/app/NotificationChannel
    //   982: dup
    //   983: getstatic com/roadtrack/onstar/BO/GlobalMembers.CHANNEL_ID : Ljava/lang/String;
    //   986: getstatic com/roadtrack/onstar/BO/GlobalMembers.CHANNEL_NAME : Ljava/lang/CharSequence;
    //   989: getstatic com/roadtrack/onstar/BO/GlobalMembers.CHANNEL_IMPORTANCE : I
    //   992: invokespecial <init> : (Ljava/lang/String;Ljava/lang/CharSequence;I)V
    //   995: astore_1
    //   996: aload_0
    //   997: getstatic com/roadtrack/onstar/BO/GlobalMembers.CHANNEL_ID : Ljava/lang/String;
    //   1000: invokevirtual setChannelId : (Ljava/lang/String;)Landroidx/core/app/NotificationCompat$Builder;
    //   1003: pop
    //   1004: aload_2
    //   1005: aload_1
    //   1006: invokevirtual createNotificationChannel : (Landroid/app/NotificationChannel;)V
    //   1009: aload_2
    //   1010: iload #4
    //   1012: aload_0
    //   1013: invokevirtual build : ()Landroid/app/Notification;
    //   1016: invokevirtual notify : (ILandroid/app/Notification;)V
    //   1019: return
  }
  
  public static boolean ServiceRunning(String paramString, Context paramContext) {
    Iterator iterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(2147483647).iterator();
    while (iterator.hasNext()) {
      if (((ActivityManager.RunningServiceInfo)iterator.next()).service.getClassName().equals(paramString))
        return true; 
    } 
    return false;
  }
  
  public static void ShowGenericDialog(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, String paramString4, String paramString5, String paramString6, boolean paramBoolean3) {
    Intent intent = new Intent(paramContext, GenericDialog.class);
    Bundle bundle = new Bundle();
    bundle.putString("IdCampaign_Dialog", paramString3);
    bundle.putString("Title", paramString1);
    bundle.putString("Content", paramString2);
    bundle.putBoolean("NotShowAgain", paramBoolean1);
    bundle.putBoolean("MoreInfo", paramBoolean2);
    bundle.putBoolean("CancelButton", false);
    bundle.putString("TitleButton", paramString4);
    bundle.putString("URL_Dialog", paramString5);
    bundle.putString("deviceID", paramString6);
    bundle.putBoolean("forceDialog", paramBoolean3);
    intent.putExtras(bundle);
    if (!GetTaskInBackGround(paramContext)) {
      intent = new Intent(paramContext, LoginActivity.class);
      bundle = new Bundle();
      bundle.putString("IdCampaign_Dialog", paramString3);
      bundle.putString("Title", paramString1);
      bundle.putString("Content", paramString2);
      bundle.putBoolean("NotShowAgain", paramBoolean1);
      bundle.putBoolean("MoreInfo", paramBoolean2);
      bundle.putBoolean("CancelButton", false);
      bundle.putString("TitleButton", paramString4);
      bundle.putString("URL_Dialog", paramString5);
      bundle.putString("deviceID", paramString6);
      bundle.putBoolean("App_Close", true);
      bundle.putBoolean("forceDialog", paramBoolean3);
      intent.putExtras(bundle);
    } 
    paramContext.startActivity(intent);
  }
  
  public static boolean SupportBluetooth() {
    boolean bool = false;
    try {
      BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if (bluetoothAdapter != null)
        bool = true; 
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: SupportBluetooth", exception.toString());
    } 
    return bool;
  }
  
  private static String UUIDRandom(Context paramContext) {
    return UUID.randomUUID().toString();
  }
  
  public static final Date addSecondsToDate(int paramInt) {
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(13, paramInt);
    return calendar.getTime();
  }
  
  public static void attemptToPayDialog(final Context context, String paramString) {
    if (!MainActivity.isAttempToPayDialogShowed) {
      MainActivity.isAttempToPayDialogShowed = true;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(MainActivity.isAttempToPayDialogShowed);
      stringBuilder.append("");
      escribeArchivo("PMM AttemptToPayFlag", stringBuilder.toString(), "");
      final DBFunctions dbFunctions = new DBFunctions(context);
      String[] arrayOfString = dBFunctions.getAttemptToPay(paramString);
      if (arrayOfString != null && arrayOfString[0].equals("1")) {
        String str1;
        final Dialog dialog;
        String str2;
        final String phoneCall;
        StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
        if (arrayOfString[1].equals("1")) {
          str1 = getStringFromConfigList(context, stringsResourcesVO.attempt_to_pay_1, 2131689679);
          str2 = "";
          str3 = "";
        } else if (arrayOfString[1].equals("2")) {
          str3 = getStringFromConfigList(context, ((StringsResourcesVO)str1).attempt_to_pay_2, 2131689680);
          stringBuilder = new StringBuilder();
          stringBuilder.append(arrayOfString[3]);
          stringBuilder.append(arrayOfString[2]);
          String str5 = String.format(str3, new Object[] { stringBuilder.toString() });
          str3 = getStringFromConfigList(context, ((StringsResourcesVO)str1).change_credit_card_phone, 2131689697);
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("<a href=");
          stringBuilder2.append(str3);
          stringBuilder2.append(">");
          stringBuilder2.append(str3);
          stringBuilder2.append("</a>");
          String str4 = stringBuilder2.toString();
          stringBuilder = new StringBuilder();
          stringBuilder.append("<center>");
          stringBuilder.append(str4);
          stringBuilder.append("</center>");
          str2 = stringBuilder.toString();
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("<center>");
          stringBuilder1.append(str5);
          stringBuilder1.append("</center>");
          str1 = stringBuilder1.toString();
        } else if (arrayOfString[1].equals("3")) {
          stringBuilder = new StringBuilder();
          stringBuilder.append(getStringFromConfigList(context, ((StringsResourcesVO)str1).attempt_to_pay_3, 2131689681));
          stringBuilder.append(" ");
          String str5 = stringBuilder.toString();
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(arrayOfString[3]);
          stringBuilder3.append(arrayOfString[2]);
          String str6 = String.format(str5, new Object[] { stringBuilder3.toString() });
          str3 = getStringFromConfigList(context, ((StringsResourcesVO)str1).change_credit_card_phone, 2131689697);
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("<a href=");
          stringBuilder2.append(str3);
          stringBuilder2.append(">");
          stringBuilder2.append(str3);
          stringBuilder2.append("</a>");
          str2 = stringBuilder2.toString();
          StringBuilder stringBuilder4 = new StringBuilder();
          stringBuilder4.append("<center>");
          stringBuilder4.append(str2);
          stringBuilder4.append("</center>");
          str2 = stringBuilder4.toString();
          stringBuilder4 = new StringBuilder();
          stringBuilder4.append(" ");
          stringBuilder4.append(getStringFromConfigList(context, ((StringsResourcesVO)str1).attempt_to_pay_3_1, 2131689682));
          String str7 = stringBuilder4.toString();
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("<center>");
          stringBuilder1.append(str6);
          stringBuilder1.append(str2);
          stringBuilder1.append(str7);
          stringBuilder1.append("</center>");
          String str4 = stringBuilder1.toString();
        } else {
          str1 = "";
          str2 = "";
          str3 = str2;
        } 
        if (arrayOfString[1].equals("2")) {
          dialog = simpleDialogHiper(context, null, "Prezado cliente da OnStar", str1, true, "Verificar Pagamento ", true, "Mais Tarde", 22.0F, 16.0F, str2);
          ((TextView)dialog.findViewById(2131296258)).setOnClickListener(new View.OnClickListener() {
                final Context val$context;
                
                final String val$phoneCall;
                
                public void onClick(View param1View) {
                  Utilities.sendIntentCallAction(context, phoneCall);
                }
              });
        } else if (arrayOfString[1].equals("3")) {
          dialog = simpleDialogHiper(context, null, "Prezado cliente da OnStar", (String)dialog, true, "Verificar Pagamento ", true, "Mais Tarde", 22.0F, 16.0F, "");
          ((TextView)dialog.findViewById(2131296257)).setOnClickListener(new View.OnClickListener() {
                final Context val$context;
                
                final String val$phoneCall;
                
                public void onClick(View param1View) {
                  Utilities.sendIntentCallAction(context, phoneCall);
                }
              });
        } else {
          dialog = simpleDialog(context, (Drawable)null, "Prezado cliente da OnStar", (String)dialog, true, "Verificar Pagamento ", true, "Mais Tarde");
        } 
        dialog.findViewById(2131296343).setOnClickListener(new View.OnClickListener() {
              final Context val$context;
              
              final DBFunctions val$dbFunctions;
              
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                dialog.dismiss();
                Utilities.getTokenAndURLToAttemptToPay(dbFunctions, context);
              }
            });
        dialog.findViewById(2131296344).setOnClickListener(new View.OnClickListener() {
              final Dialog val$dialog;
              
              public void onClick(View param1View) {
                dialog.dismiss();
              }
            });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
              public void onDismiss(DialogInterface param1DialogInterface) {
                MainActivity.isAttempToPayDialogShowed = false;
              }
            });
        dialog.show();
      } 
    } 
  }
  
  public static boolean buttonValidated(Context paramContext, String paramString1, String paramString2, boolean paramBoolean) {
    int i;
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_lbl_accionencurso_1, 2131689953);
    String str5 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str6 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    String str3 = getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_conexiondered_1, 2131689912);
    String str4 = getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    if (!paramBoolean) {
      str1 = null;
      str2 = null;
    } 
    boolean bool = false;
    if (!NetUtilities.validateNetwork(paramContext, false)) {
      str1 = str3;
      paramBoolean = false;
      str2 = str4;
    } else {
      i = paramBoolean ^ true;
    } 
    if (i == 0) {
      final Dialog dialog = simpleDialog(paramContext, (Drawable)null, str1, str2, true, str5, false, str6);
      ((Button)dialog.findViewById(2131296343)).setOnClickListener(new View.OnClickListener() {
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              dialog.dismiss();
            }
          });
      dialog.show();
    } 
    boolean bool1 = (new DBFunctions(paramContext)).userDataTableHandler(paramString1, paramString2, "", true);
    if (bool1)
      i = bool; 
    showTheftAutoBanner(paramContext, bool1);
    return i;
  }
  
  public static int calculateEstimateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return (paramInt4 - paramInt1) / paramInt2 * paramInt3 / 1000 / 60;
  }
  
  public static final boolean dateCountDown(Date paramDate) {
    return (new Date()).after(paramDate);
  }
  
  public static String decodeStringUTF(String paramString) {
    if (!paramString.isEmpty()) {
      try {
        String str = new String();
        this(paramString.getBytes("ISO-8859-1"), "UTF-8");
        paramString = str;
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        escribeArchivo("Utilities", "Error:Decode", unsupportedEncodingException.getMessage());
      } 
    } else {
      paramString = "";
    } 
    return paramString;
  }
  
  public static void deleteAllMapUpdateFiles(String paramString) {
    try {
      GetHexDumpMap getHexDumpMap = new GetHexDumpMap();
      this();
      File file = UtilitiesFile.getFileFromStringFile(getHexDumpMap.getMapUpdateFile(paramString));
      int i = paramString.length();
      int j = 0;
      byte b = 0;
      paramString = paramString.substring(0, i - 4);
      i = j;
      if (file.exists()) {
        file = file.getParentFile();
        i = j;
        if (file.isDirectory()) {
          File[] arrayOfFile = file.listFiles();
          int k = arrayOfFile.length;
          for (i = 0; b < k; i = j) {
            file = arrayOfFile[b];
            j = i;
            if (file.getName().contains(paramString)) {
              file.delete();
              j = i + 1;
            } 
            b++;
          } 
        } 
      } 
      escribeArchivo("Utilities", "MapUpdate Archivos borrados : ", String.valueOf(i));
    } catch (Exception exception) {}
  }
  
  public static final void deleteRecursive(File paramFile) {
    if (paramFile.exists()) {
      if (paramFile.isDirectory()) {
        File[] arrayOfFile = paramFile.listFiles();
        int i = arrayOfFile.length;
        for (byte b = 0; b < i; b++)
          deleteRecursive(arrayOfFile[b]); 
      } 
      paramFile.delete();
    } 
  }
  
  @SuppressLint({"NewApi"})
  public static long diffMinutesToActualTime(String paramString1, String paramString2, Context paramContext, String paramString3) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    String str = paramString2;
    if (paramString2.isEmpty())
      str = getDateTime(paramString3, paramContext); 
    paramContext = null;
    try {
      Date date = simpleDateFormat.parse(paramString1);
      try {
        Date date1 = simpleDateFormat.parse(str);
      } catch (ParseException parseException) {
        escribeArchivo("Utilities", "Error: diffMinutes.ParseException", parseException.getMessage());
        context = paramContext;
      } catch (Exception exception) {}
    } catch (ParseException parseException) {
      paramString1 = null;
    } catch (Exception exception) {
      paramString1 = null;
      escribeArchivo("Utilities", "Error: diffMinutes", exception.getMessage());
      context = paramContext;
    } 
    escribeArchivo("Utilities", "Error: diffMinutes.ParseException", context.getMessage());
    Context context = paramContext;
  }
  
  public static void escribeArchivo(Exception paramException, String paramString1, String paramString2, String paramString3) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\t");
    stringBuilder.append(paramString1);
    stringBuilder.append("\t");
    stringBuilder.append(paramString2);
    stringBuilder.append("\t");
    stringBuilder.append(paramString3);
    String str = stringBuilder.toString().replace("\t", " - ");
    if (!paramString2.contains("DATA_REMOTE_SERVICES"))
      paramString2.contains("hideSplash"); 
    if (GlobalMembers.showLog)
      str.toLowerCase().contains("error"); 
  }
  
  public static void escribeArchivo(String paramString, Exception paramException) {
    escribeArchivo(paramException, paramString, "ERROR", paramException.getMessage());
  }
  
  public static void escribeArchivo(String paramString1, String paramString2, String paramString3) {
    escribeArchivo(null, paramString1, paramString2, paramString3);
  }
  
  public static Dialog expirationDialog(final Context context, String paramString1, final String deviceIdF) {
    boolean bool1;
    boolean bool2;
    DBFunctions dBFunctions = new DBFunctions(context);
    new UserPreferenceVO();
    UserPreferenceVO userPreferenceVO = dBFunctions.getUserPreference(GlobalMembers.userLogged);
    onstarApplication onstarApplication = (onstarApplication)GlobalMembers.contexGlobal;
    VehicleCatalogVO vehicleCatalogVO = dBFunctions.getVehicleByDeviceID(userPreferenceVO.getUser(), deviceIdF);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str4 = getStringFromConfigList(context, stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str5 = getStringFromConfigList(context, stringsResourcesVO.renovacion_popup_lbl_renovar, 2131690338);
    String str3 = getStringFromConfigList(context, stringsResourcesVO.renovacion_popup_lbl_ahorano, 2131690328);
    String str6 = getStringFromConfigList(context, stringsResourcesVO.mapdownloading_popup_lbl_nomostrarmensaje_3, 2131690123);
    String str2 = getStringFromConfigList(context, stringsResourcesVO.renovacion_lbl_validez, 2131690324);
    String str7 = getStringFromConfigList(context, stringsResourcesVO.renovacion_lbl_diasparaexpirar, 2131690288);
    String str1 = dBFunctions.getRenewalDialogDaysExpire(deviceIdF);
    if (str1 != null && !str1.equals("")) {
      byte b;
      try {
        b = Integer.parseInt(str1);
      } catch (NumberFormatException numberFormatException) {
        escribeArchivo("Utilities", "Error ", numberFormatException.getMessage());
        b = -1;
      } 
      if (b == 1) {
        String str = getStringFromConfigList(context, stringsResourcesVO.global_lbl_diasingular, 2131689917);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>");
        stringBuilder.append(str7);
        stringBuilder.append(" ");
        stringBuilder.append(str1);
        stringBuilder.append(" ");
        stringBuilder.append(str);
        stringBuilder.append("</b>");
        str1 = stringBuilder.toString();
      } else {
        String str = getStringFromConfigList(context, stringsResourcesVO.global_lbl_dias, 2131689916);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>");
        stringBuilder.append(str7);
        stringBuilder.append(" ");
        stringBuilder.append(str1);
        stringBuilder.append(" ");
        stringBuilder.append(str);
        stringBuilder.append("</b>");
        str1 = stringBuilder.toString();
      } 
    } else {
      str1 = null;
    } 
    String str8 = dBFunctions.getRenewalDialogDateExpire(deviceIdF);
    if (str8 != null && !str8.equals("")) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("<b>");
      stringBuilder.append(str2);
      stringBuilder.append(" ");
      stringBuilder.append(str8);
      stringBuilder.append("</b>");
      str2 = stringBuilder.toString();
    } else {
      str2 = null;
    } 
    getStringFromConfigList(context, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
    paramString1 = getStringFromConfigList(context, stringsResourcesVO.renovacion_popup_lbl_expiracion, 2131690330).replace("%s", paramString1);
    getDrawableFromConfigList(context, DrawableResourcesVO.ic_spinner_renewal_dialog, 2131165520);
    if (GlobalMembers.notificationSpinner || vehicleCatalogVO.getStatus_renewal_account().equals(Enums.statusRenewalAccount.Expired.toString())) {
      bool1 = false;
    } else {
      bool1 = true;
    } 
    if (GlobalMembers.notificationSpinner && !vehicleCatalogVO.getStatus_renewal_account().equals(Enums.statusRenewalAccount.Expired.toString()) && str2 != null && !str2.equals("") && str1 != null && !str1.equals("")) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    final Dialog dialog = simpleDialogCheckBoxBottomButon(context, null, str4, paramString1, true, str5, true, str3, 20.0F, 15.0F, bool1, str6, false, bool2, str2, str1);
    final ArrayList arraylistVehiclesAlmostExpired = dBFunctions.getVehiclesAlmostExpired(userPreferenceVO.getUser());
    final ArrayList arraylistVehiclesExpired = dBFunctions.getVehiclesExpired(userPreferenceVO.getUser());
    final ArrayList arraylistVehiclesNormal = dBFunctions.getVehiclesNormal(userPreferenceVO.getUser());
    int k = arrayList2.size();
    int i = arrayList1.size();
    int j = arrayList3.size();
    ((Button)dialog.findViewById(2131296421)).setOnClickListener(new View.OnClickListener() {
          final List val$arraylistVehiclesAlmostExpired;
          
          final List val$arraylistVehiclesExpired;
          
          final List val$arraylistVehiclesNormal;
          
          final Context val$cxt;
          
          final Dialog val$dialog;
          
          final int val$totalVehicleExpiredOrAlmost;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            if (GlobalMembers.notificationSpinner) {
              if (totalVehicleExpiredOrAlmost > 1) {
                Utilities.renewalVehiclesListFunction(cxt, arraylistVehiclesAlmostExpired, arraylistVehiclesExpired, arraylistVehiclesNormal);
              } else if (!GlobalMembers.flagShowWebViews) {
                Utilities.openOriginalRenewalActivity(cxt);
              } else {
                Utilities.paymentProcessFunction(cxt);
              } 
            } else {
              Utilities.openOriginalRenewalActivity(cxt);
            } 
          }
        });
    ((Button)dialog.findViewById(2131296420)).setOnClickListener(new View.OnClickListener() {
          final Context val$context;
          
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
            Intent intent = new Intent();
            intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
            context.sendBroadcast(intent);
          }
        });
    ((CheckBox)dialog.findViewById(2131296468)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final Context val$context;
          
          final String val$deviceIdF;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            DBFunctions dBFunctions = new DBFunctions(context);
            if (param1Boolean) {
              dBFunctions.updateRenewalDialogStatus(deviceIdF, Enums.statusRenewalDialog.NeverShowAgain.toString());
            } else {
              dBFunctions.updateRenewalDialogStatus(deviceIdF, Enums.statusRenewalDialog.ShowAlways.toString());
            } 
          }
        });
    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
          public void onDismiss(DialogInterface param1DialogInterface) {
            GlobalMembers.isShowingDialog = false;
          }
        });
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(context, 2131034153))); 
    return dialog;
  }
  
  public static Dialog expirationDialog2(final Context context, String paramString1, String paramString2, final int numVehicle) {
    DBFunctions dBFunctions = new DBFunctions(context);
    new UserPreferenceVO();
    UserPreferenceVO userPreferenceVO = dBFunctions.getUserPreference(GlobalMembers.userLogged);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str3 = getStringFromConfigList(context, stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str2 = getStringFromConfigList(context, stringsResourcesVO.renovacion_popup_lbl_renovar, 2131690338);
    String str6 = getStringFromConfigList(context, stringsResourcesVO.renovacion_popup_lbl_ahorano, 2131690328);
    paramString2 = getStringFromConfigList(context, stringsResourcesVO.renovacion_popup_lbl_planexpirado, 2131690334);
    String str1 = getStringFromConfigList(context, stringsResourcesVO.renovacion_popup_lbl_planexpirado2, 2131690335);
    String str4 = getStringFromConfigList(context, stringsResourcesVO.nothing, 2131690207);
    String str5 = getStringFromConfigList(context, stringsResourcesVO.global_lbl_asistencia, 2131689907);
    if (GlobalMembers.notificationSpinner)
      paramString2 = str1; 
    str1 = paramString2.replace("%s", paramString1);
    getDrawableFromConfigList(context, DrawableResourcesVO.ic_spinner_renewal_dialog, 2131165520);
    Dialog dialog2 = simpleDialogCheckBoxBottomButon(context, null, str3, str1, true, str2, true, str6, 20.0F, 15.0F, false, str4, true, false, null, null);
    final Dialog finalDialog1 = dialog2;
    if (GlobalMembers.notificationSpinner) {
      dialog1 = dialog2;
      if (numVehicle < 2)
        dialog1 = simpleDialogCheckBoxBottomButon(context, null, str3, str1, true, str2, true, str5, 20.0F, 15.0F, false, str4, false, false, null, null); 
    } 
    final ArrayList arraylistVehiclesAlmostExpired = dBFunctions.getVehiclesAlmostExpired(userPreferenceVO.getUser());
    final ArrayList arraylistVehiclesExpired = dBFunctions.getVehiclesExpired(userPreferenceVO.getUser());
    final ArrayList arraylistVehiclesNormal = dBFunctions.getVehiclesNormal(userPreferenceVO.getUser());
    int i = arrayList1.size();
    int k = arrayList2.size();
    int j = arrayList3.size();
    ((Button)dialog1.findViewById(2131296421)).setOnClickListener(new View.OnClickListener() {
          final List val$arraylistVehiclesAlmostExpired;
          
          final List val$arraylistVehiclesExpired;
          
          final List val$arraylistVehiclesNormal;
          
          final Context val$cxt;
          
          final Dialog val$finalDialog;
          
          final int val$numVehicle;
          
          final int val$totalVehicleExpiredOrAlmost;
          
          public void onClick(View param1View) {
            if (!GlobalMembers.notificationSpinner || numVehicle > 1)
              finalDialog.dismiss(); 
            if (GlobalMembers.notificationSpinner) {
              if (totalVehicleExpiredOrAlmost > 1) {
                Utilities.renewalVehiclesListFunction(cxt, arraylistVehiclesAlmostExpired, arraylistVehiclesExpired, arraylistVehiclesNormal);
              } else if (!GlobalMembers.flagShowWebViews) {
                Utilities.openOriginalRenewalActivity(cxt);
              } else {
                Utilities.paymentProcessFunction(cxt);
              } 
            } else {
              Utilities.openOriginalRenewalActivity(cxt);
            } 
          }
        });
    ((Button)dialog1.findViewById(2131296420)).setOnClickListener(new View.OnClickListener() {
          final Context val$context;
          
          final Dialog val$finalDialog1;
          
          final int val$numVehicle;
          
          public void onClick(View param1View) {
            finalDialog1.dismiss();
            if (GlobalMembers.notificationSpinner && numVehicle < 2) {
              Utilities.sendIntentCallAction(context);
            } else {
              Intent intent = new Intent();
              intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
              context.sendBroadcast(intent);
            } 
          }
        });
    dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
          public void onDismiss(DialogInterface param1DialogInterface) {
            GlobalMembers.isShowingDialog = false;
          }
        });
    if (dialog1.getWindow() != null)
      dialog1.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(context, 2131034153))); 
    return dialog1;
  }
  
  public static int factorOdometer(double paramDouble) {
    // Byte code:
    //   0: dload_0
    //   1: ldc2_w 5000.0
    //   4: ddiv
    //   5: d2i
    //   6: istore #5
    //   8: iload #5
    //   10: sipush #5000
    //   13: imul
    //   14: istore #4
    //   16: iload #4
    //   18: sipush #320
    //   21: isub
    //   22: istore #8
    //   24: iload #8
    //   26: i2d
    //   27: dstore_2
    //   28: iconst_0
    //   29: istore #7
    //   31: iconst_1
    //   32: istore #6
    //   34: dload_0
    //   35: dload_2
    //   36: dcmpl
    //   37: iflt -> 77
    //   40: dload_0
    //   41: iload #4
    //   43: sipush #1000
    //   46: iadd
    //   47: i2d
    //   48: dcmpg
    //   49: ifle -> 55
    //   52: goto -> 77
    //   55: iload #5
    //   57: istore #4
    //   59: iload #8
    //   61: ifle -> 71
    //   64: iload #5
    //   66: istore #4
    //   68: goto -> 124
    //   71: iconst_0
    //   72: istore #6
    //   74: goto -> 124
    //   77: iinc #5, 1
    //   80: iload #5
    //   82: sipush #5000
    //   85: imul
    //   86: istore #8
    //   88: iload #5
    //   90: istore #4
    //   92: dload_0
    //   93: iload #8
    //   95: sipush #320
    //   98: isub
    //   99: i2d
    //   100: dcmpl
    //   101: iflt -> 71
    //   104: iload #5
    //   106: istore #4
    //   108: dload_0
    //   109: iload #8
    //   111: sipush #1000
    //   114: iadd
    //   115: i2d
    //   116: dcmpg
    //   117: ifgt -> 71
    //   120: iload #5
    //   122: istore #4
    //   124: iload #6
    //   126: ifne -> 136
    //   129: iload #7
    //   131: istore #4
    //   133: goto -> 142
    //   136: iload #4
    //   138: iconst_5
    //   139: imul
    //   140: istore #4
    //   142: new java/lang/StringBuilder
    //   145: dup
    //   146: invokespecial <init> : ()V
    //   149: astore #9
    //   151: aload #9
    //   153: ldc_w 'factorValor: '
    //   156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload #9
    //   162: iload #4
    //   164: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: ldc 'Utilities'
    //   170: ldc_w 'ODOMETRO'
    //   173: aload #9
    //   175: invokevirtual toString : ()Ljava/lang/String;
    //   178: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   181: iload #4
    //   183: ireturn
  }
  
  public static boolean fileExists(String paramString) {
    boolean bool;
    try {
      GetHexDumpMap getHexDumpMap = new GetHexDumpMap();
      this();
      bool = UtilitiesFile.getFileFromStringFile(getHexDumpMap.getMapUpdateFile(paramString)).exists();
    } catch (Exception exception) {
      bool = false;
    } 
    return bool;
  }
  
  public static long formatMillis(String paramString) {
    String[] arrayOfString = paramString.split(":");
    Long long_2 = Long.valueOf(0L);
    Long long_1 = long_2;
    if (!arrayOfString[0].equals("00")) {
      long_1 = long_2;
      if (arrayOfString[0] != null)
        long_1 = Long.valueOf(long_2.longValue() + Long.parseLong(arrayOfString[0]) * 3600000L); 
    } 
    long_2 = long_1;
    if (!arrayOfString[1].equals("00")) {
      long_2 = long_1;
      if (arrayOfString[1] != null)
        long_2 = Long.valueOf(long_1.longValue() + Long.parseLong(arrayOfString[1]) * 60000L); 
    } 
    long_1 = long_2;
    if (!arrayOfString[2].equals("00")) {
      long_1 = long_2;
      if (arrayOfString[2] != null)
        long_1 = Long.valueOf(long_2.longValue() + Long.parseLong(arrayOfString[2]) * 1000L); 
    } 
    return long_1.longValue();
  }
  
  public static final void genericAlertDialogOk(Context paramContext, String paramString) {
    AlertDialog.Builder builder = new AlertDialog.Builder(paramContext);
    String str = getStringFromConfigList(paramContext, (new StringsResourcesVO()).global_popup_btn_ok_1, 2131689950);
    builder.setMessage(paramString);
    builder.setPositiveButton(str, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {}
        });
    builder.create();
    builder.show();
  }
  
  public static Dialog genericDialog(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, String paramString4, boolean paramBoolean2, String paramString5, float paramFloat1, float paramFloat2, boolean paramBoolean3, int paramInt, boolean paramBoolean4) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    dialog.setContentView(View.inflate(paramContext, 2131427360, null));
    dialog.setCancelable(false);
    ((TextView)dialog.findViewById(2131296734)).setTextSize(paramFloat1);
    ((TextView)dialog.findViewById(2131297153)).setText(paramString2);
    ((TextView)dialog.findViewById(2131296733)).setTextSize(paramFloat2);
    ((TextView)dialog.findViewById(2131296734)).setText((CharSequence)Html.fromHtml(paramString1));
    ((TextView)dialog.findViewById(2131296733)).setText(paramString3);
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296421)).setVisibility(0);
      ((Button)dialog.findViewById(2131296421)).setText(paramString4);
    } else {
      ((Button)dialog.findViewById(2131296421)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296420)).setVisibility(0);
      ((Button)dialog.findViewById(2131296420)).setText(paramString5);
    } else {
      ((Button)dialog.findViewById(2131296420)).setVisibility(8);
    } 
    if (paramBoolean3) {
      if (paramBoolean4) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<u>");
        stringBuilder.append(paramInt);
        stringBuilder.append("</u>");
        String str = stringBuilder.toString();
        ((CheckBox)dialog.findViewById(2131296468)).setText((CharSequence)Html.fromHtml(str));
      } else {
        ((CheckBox)dialog.findViewById(2131296468)).setText(paramInt);
      } 
      ((CheckBox)dialog.findViewById(2131296468)).setVisibility(0);
    } else {
      ((CheckBox)dialog.findViewById(2131296468)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static String getActualTime() {
    Date date = new Date();
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm", Locale.getDefault());
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(simpleDateFormat2.format(date).toString());
    stringBuilder.append(" ");
    stringBuilder.append(simpleDateFormat1.format(date).toString());
    return stringBuilder.toString();
  }
  
  public static final String[] getCommIdAndSerialNumber(String paramString) {
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
  
  public static final int getCurrentScreenOrientation(Activity paramActivity) {
    byte b;
    Display display = paramActivity.getWindowManager().getDefaultDisplay();
    if (display.getWidth() == display.getHeight()) {
      b = 3;
    } else if (display.getWidth() < display.getHeight()) {
      b = 1;
    } else {
      b = 2;
    } 
    return b;
  }
  
  public static String getDateTime() {
    return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())).format(new Date());
  }
  
  public static String getDateTime(int paramInt) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(12, -paramInt);
    return simpleDateFormat.format(calendar.getTime());
  }
  
  public static String getDateTime(String paramString, Context paramContext) {
    return getDateTime(paramString, paramContext, "yyyy-MM-dd HH:mm:ss");
  }
  
  public static String getDateTime(String paramString1, Context paramContext, String paramString2) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(paramString2, Locale.getDefault());
    DBFunctions dBFunctions = new DBFunctions(paramContext);
    Calendar calendar = Calendar.getInstance();
    calendar.add(10, (int)dBFunctions.getTimeGTM(GlobalMembers.userLogged, paramString1, paramContext));
    return simpleDateFormat.format(calendar.getTime());
  }
  
  public static UserDevicesVO getDeviceFromPush(onstarApplication paramonstarApplication, String paramString1, String paramString2) {
    onstarApplication onstarApplication1;
    onstarApplication onstarApplication2 = null;
    try {
      DBFunctions dBFunctions = new DBFunctions();
      this(GlobalMembers.ctxBase);
      String str = dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser();
      ArrayList<UserDevicesVO> arrayList = (ArrayList)paramonstarApplication.getmDeviceUserList();
      if (GlobalMembers.mDeviceUserList == null) {
        paramonstarApplication = onstarApplication2;
      } else {
        VehicleCatalogVO vehicleCatalogVO;
        int i = GlobalMembers.mDeviceUserList.size();
        Iterator<VehicleCatalogVO> iterator1 = dBFunctions.getVehiclesCatalog(str).iterator();
        paramonstarApplication = null;
        while (iterator1.hasNext()) {
          VehicleCatalogVO vehicleCatalogVO1 = iterator1.next();
          if (vehicleCatalogVO1.getDeviceId().contentEquals(paramString1))
            vehicleCatalogVO = vehicleCatalogVO1; 
        } 
        Iterator<UserDevicesVO> iterator = arrayList.iterator();
        byte b;
        for (b = 0; iterator.hasNext() && !((UserDevicesVO)iterator.next()).getDeviceId().equals(vehicleCatalogVO.getDeviceId()); b++);
        if (b >= i) {
          UserDevicesVO userDevicesVO = arrayList.get(0);
        } else {
          UserDevicesVO userDevicesVO = arrayList.get(b);
        } 
      } 
    } catch (Exception exception) {
      escribeArchivo(paramString2, "getLastKnownDeciveSelected", exception.getMessage());
      onstarApplication1 = onstarApplication2;
    } 
    return (UserDevicesVO)onstarApplication1;
  }
  
  public static String getDeviceName() {
    String str2 = Build.MODEL;
    String str1 = Build.MANUFACTURER;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str1.toUpperCase());
    stringBuilder.append(" ");
    stringBuilder.append(str2);
    return stringBuilder.toString();
  }
  
  public static int getDevicePositionByDeviceId(String paramString) {
    byte b1;
    boolean bool1;
    List list = GlobalMembers.mDeviceUserList;
    byte b3 = -1;
    boolean bool2 = false;
    if (paramString != null && list != null && list.size() > 0) {
      Iterator<UserDevicesVO> iterator = list.iterator();
      int i = -1;
      while (true) {
        bool1 = bool2;
        b1 = i;
        if (iterator.hasNext()) {
          UserDevicesVO userDevicesVO = iterator.next();
          b1 = i + 1;
          i = b1;
          if (paramString.equals(userDevicesVO.getDeviceId())) {
            bool1 = true;
            break;
          } 
          continue;
        } 
        break;
      } 
    } else {
      b1 = -1;
      bool1 = bool2;
    } 
    byte b2 = b3;
    if (bool1)
      b2 = b1; 
    return b2;
  }
  
  public static String getDeviceSerialNumberByDeviceId(String paramString) {
    UserDevicesVO userDevicesVO;
    List list = GlobalMembers.mDeviceUserList;
    Iterator<UserDevicesVO> iterator3 = null;
    Iterator<UserDevicesVO> iterator1 = null;
    Iterator<UserDevicesVO> iterator2 = iterator3;
    if (list != null) {
      iterator2 = iterator3;
      if (list.size() > 0) {
        iterator3 = list.iterator();
        while (true) {
          iterator2 = iterator1;
          if (iterator3.hasNext()) {
            userDevicesVO = iterator3.next();
            if (paramString.equals(userDevicesVO.getDeviceId()))
              String str = userDevicesVO.getSerialnumber(); 
            continue;
          } 
          break;
        } 
      } 
    } 
    return (String)userDevicesVO;
  }
  
  public static Drawable getDrawableFromConfigList(Context paramContext, String paramString, int paramInt) {
    return paramContext.getResources().getDrawable(paramInt);
  }
  
  public static Enums.Services getFollowMeCode(onstarApplication paramonstarApplication, String paramString) {
    UserDevicesVO userDevicesVO = getLastKnownDeviceSelected(paramonstarApplication, paramString);
    new LinkedHashMap<Object, Object>();
    for (String str : userDevicesVO.getMainActions().keySet()) {
      if (str.equals(Enums.Services.FollowMe.GetCodeString()))
        return Enums.Services.FollowMe; 
      if (str.equals(Enums.Services.FollowMeUUx.GetCodeString()))
        return Enums.Services.FollowMeUUx; 
    } 
    return Enums.Services.None;
  }
  
  public static Enums.Services getHornCode(onstarApplication paramonstarApplication, String paramString) {
    for (String str : getLastKnownDeviceSelected(paramonstarApplication, paramString).getMainActions().keySet()) {
      if (str.equals(Enums.Services.Horn.GetCodeString()))
        return Enums.Services.Horn; 
      if (str.equals(Enums.Services.HornF1.GetCodeString()))
        return Enums.Services.HornF1; 
    } 
    return Enums.Services.None;
  }
  
  public static InputStream getKeyStoreFromConfigList(Context paramContext, String paramString, int paramInt) {
    InputStream inputStream2 = paramContext.getResources().openRawResource(paramInt);
    String str = (String)GlobalMembers.hashmapCerts.get(paramString);
    paramString = str;
    if (str != null) {
      paramString = str;
      if (str.length() > 0) {
        paramString = str;
        if (str.contains("/"))
          paramString = str.split("/")[3]; 
      } 
    } 
    InputStream inputStream1 = inputStream2;
    if (paramString != null)
      try {
        inputStream1 = paramContext.getApplicationContext().openFileInput(paramString);
      } catch (FileNotFoundException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
        inputStream1 = inputStream2;
      }  
    return inputStream1;
  }
  
  public static String getLastFollowMeDeviceId(Context paramContext) {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("com.roadtrack.last.follow.me.device.id", null);
  }
  
  public static final String getLastFollowMeMessageId(Activity paramActivity, String paramString) {
    Context context = paramActivity.getApplicationContext();
    return (new DBFunctions(context)).messageIdFromStartFollowMe(paramString, context);
  }
  
  public static UserDevicesVO getLastKnownDeviceSelected(Context paramContext, onstarApplication paramonstarApplication) {
    UserDevicesVO userDevicesVO;
    DBFunctions dBFunctions = new DBFunctions(paramContext);
    String str = dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser();
    ArrayList<UserDevicesVO> arrayList = (ArrayList)paramonstarApplication.getmDeviceUserList();
    List list = GlobalMembers.vehicleList;
    paramonstarApplication = null;
    paramContext = null;
    if (list == null) {
      onstarApplication onstarApplication1 = paramonstarApplication;
    } else {
      VehicleCatalogVO vehicleCatalogVO;
      int i = list.size();
      for (VehicleCatalogVO vehicleCatalogVO1 : dBFunctions.getVehiclesCatalog(str)) {
        if (vehicleCatalogVO1.getSelected().equalsIgnoreCase("T"))
          vehicleCatalogVO = vehicleCatalogVO1; 
      } 
      if (vehicleCatalogVO != null) {
        Iterator<UserDevicesVO> iterator = arrayList.iterator();
        byte b;
        for (b = 0; iterator.hasNext() && !((UserDevicesVO)iterator.next()).getDeviceId().equals(vehicleCatalogVO.getDeviceId()); b++);
        if (b >= i) {
          userDevicesVO = arrayList.get(0);
        } else {
          userDevicesVO = arrayList.get(b);
        } 
      } else {
        userDevicesVO = arrayList.get(0);
      } 
    } 
    return userDevicesVO;
  }
  
  public static UserDevicesVO getLastKnownDeviceSelected(onstarApplication paramonstarApplication, String paramString) {
    onstarApplication onstarApplication1;
    onstarApplication onstarApplication2 = null;
    try {
      DBFunctions dBFunctions = new DBFunctions();
      this(GlobalMembers.ctxBase);
      String str = dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser();
      ArrayList<UserDevicesVO> arrayList = (ArrayList)paramonstarApplication.getmDeviceUserList();
      if (GlobalMembers.mDeviceUserList == null) {
        paramonstarApplication = onstarApplication2;
      } else {
        VehicleCatalogVO vehicleCatalogVO;
        byte b;
        int i = GlobalMembers.mDeviceUserList.size();
        Iterator<VehicleCatalogVO> iterator = dBFunctions.getVehiclesCatalog(str).iterator();
        paramonstarApplication = null;
        while (iterator.hasNext()) {
          VehicleCatalogVO vehicleCatalogVO1 = iterator.next();
          if (vehicleCatalogVO1.getSelected().equalsIgnoreCase("T"))
            vehicleCatalogVO = vehicleCatalogVO1; 
        } 
        if (vehicleCatalogVO != null) {
          Iterator<UserDevicesVO> iterator1 = arrayList.iterator();
          byte b1 = 0;
          while (true) {
            b = b1;
            if (iterator1.hasNext()) {
              if (((UserDevicesVO)iterator1.next()).getDeviceId().equals(vehicleCatalogVO.getDeviceId())) {
                b = b1;
                break;
              } 
              b1++;
              continue;
            } 
            break;
          } 
        } else {
          b = 0;
        } 
        if (b >= i) {
          UserDevicesVO userDevicesVO = arrayList.get(0);
        } else {
          UserDevicesVO userDevicesVO = arrayList.get(b);
        } 
      } 
    } catch (Exception exception) {
      escribeArchivo(paramString, "getLastKnownDeciveSelected", exception.getMessage());
      onstarApplication1 = onstarApplication2;
    } 
    return (UserDevicesVO)onstarApplication1;
  }
  
  public static int getLastKnownVehicleSelected(Context paramContext, String paramString, onstarApplication paramonstarApplication) {
    byte b;
    ArrayList arrayList = (ArrayList)paramonstarApplication.getmDeviceUserList();
    List list = GlobalMembers.vehicleList;
    boolean bool = false;
    if (list == null) {
      b = bool;
    } else {
      VehicleCatalogVO vehicleCatalogVO;
      int i = list.size();
      Iterator<VehicleCatalogVO> iterator1 = (new DBFunctions(paramContext)).getVehiclesCatalog(paramString).iterator();
      paramContext = null;
      while (iterator1.hasNext()) {
        VehicleCatalogVO vehicleCatalogVO1 = iterator1.next();
        if (vehicleCatalogVO1.getSelected().equalsIgnoreCase("T"))
          vehicleCatalogVO = vehicleCatalogVO1; 
      } 
      Iterator<UserDevicesVO> iterator = arrayList.iterator();
      for (b = 0; iterator.hasNext() && !((UserDevicesVO)iterator.next()).getDeviceId().equals(vehicleCatalogVO.getDeviceId()); b++);
      if (b >= i)
        b = bool; 
    } 
    return b;
  }
  
  public static int getLastVehicle(Context paramContext, String paramString, onstarApplication paramonstarApplication) {
    byte b1;
    ArrayList arrayList = (ArrayList)paramonstarApplication.getmDeviceUserList();
    List list = GlobalMembers.vehicleList;
    byte b2 = 0;
    if (list == null) {
      b1 = b2;
    } else {
      VehicleCatalogVO vehicleCatalogVO;
      int i = list.size();
      Iterator<VehicleCatalogVO> iterator = (new DBFunctions(paramContext)).getSelectedVehiclesCatalog(paramString).iterator();
      paramContext = null;
      while (iterator.hasNext()) {
        VehicleCatalogVO vehicleCatalogVO1 = iterator.next();
        if (vehicleCatalogVO1.getSelected().equalsIgnoreCase("L"))
          vehicleCatalogVO = vehicleCatalogVO1; 
      } 
      b1 = b2;
      if (vehicleCatalogVO != null) {
        Iterator<UserDevicesVO> iterator1 = arrayList.iterator();
        for (b1 = 0; iterator1.hasNext() && !((UserDevicesVO)iterator1.next()).getDeviceId().equals(vehicleCatalogVO.getDeviceId()); b1++);
        if (b1 >= i)
          b1 = b2; 
      } 
    } 
    return b1;
  }
  
  public static String getParamsFromConfigList(String paramString) {
    String str1;
    String str2 = (String)GlobalMembers.hashmapParams.get(paramString);
    if (str2 != null) {
      str1 = Decrypt((String)GlobalMembers.hashmapParams.get(paramString));
    } else if (str2 == null) {
      str1 = UtilitiesFile.ReadCfg(paramString);
    } else {
      str1 = str2;
      if (str2.isEmpty())
        str1 = UtilitiesFile.ReadCfg(paramString); 
    } 
    return str1;
  }
  
  public static Enums.Services getParkingCode(onstarApplication paramonstarApplication, String paramString) {
    UserDevicesVO userDevicesVO = getLastKnownDeviceSelected(paramonstarApplication, paramString);
    new LinkedHashMap<Object, Object>();
    for (String paramString : userDevicesVO.getMainActions().keySet()) {
      if (paramString.equals(Enums.Services.Parking.GetCodeString()))
        return Enums.Services.Parking; 
      if (paramString.equals(Enums.Services.ParkingUUx.GetCodeString()))
        return Enums.Services.ParkingUUx; 
    } 
    return Enums.Services.None;
  }
  
  public static String getPrefixFromCountry(String paramString, Context paramContext) {
    return "BR_";
  }
  
  public static String getPushNotTableTitle(int paramInt) {
    String str;
    if (paramInt == 27 || paramInt == 10)
      return "FollowMe"; 
    if (paramInt == 15) {
      str = "Velocidade";
    } else {
      if (paramInt == 57 || paramInt == 225)
        return "Movimento"; 
      if (paramInt == 235) {
        str = "Valet";
      } else if (paramInt == 208) {
        str = "DTC";
      } else if (paramInt == -1) {
        str = "Mantenimiento";
      } else {
        str = "";
      } 
    } 
    return str;
  }
  
  public static String getRealPositionFindMe(String paramString, Context paramContext) {
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.previousposition, 2131690271);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.currentposition, 2131689742);
    String[] arrayOfString = paramString.split(" ");
    paramString = str2;
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
      paramString = str2;
      this("yyy-MM-dd hh:mm:ss", Locale.getDefault());
      paramString = str2;
      StringBuilder stringBuilder = new StringBuilder();
      paramString = str2;
      this();
      paramString = str2;
      stringBuilder.append(arrayOfString[0]);
      paramString = str2;
      stringBuilder.append(" ");
      paramString = str2;
      stringBuilder.append(arrayOfString[1]);
      paramString = str2;
      Date date1 = simpleDateFormat.parse(stringBuilder.toString());
      paramString = str2;
      Date date2 = simpleDateFormat.parse(simpleDateFormat.format(Calendar.getInstance().getTime()));
      paramString = str2;
      Calendar calendar = Calendar.getInstance();
      paramString = str2;
      calendar.setTime(date2);
      paramString = str2;
      calendar.add(12, -3);
      paramString = str2;
      if (!date1.before(calendar.getTime()))
        str2 = str1; 
      paramString = "";
    } catch (ParseException parseException) {
      escribeArchivo("Utilities", "Error: getRealPositionFindMe", parseException.getMessage());
    } 
    return paramString;
  }
  
  public static int getResolution(Context paramContext) {
    new DisplayMetrics();
    int i = (paramContext.getResources().getDisplayMetrics()).densityDpi;
    switch (i) {
      default:
        str = Integer.toString(i);
        i = 12;
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 640:
        i = 6;
        str = "DENSITY_XXXHIGH";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 560:
        i = 11;
        str = "DENSITY_560";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 480:
        i = 5;
        str = "DENSITY_XXHIGH";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 420:
        i = 10;
        str = "DENSITY_420";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 400:
        i = 9;
        str = "DENSITY_400";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 360:
        i = 8;
        str = "DENSITY_360";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 320:
        i = 4;
        str = "DENSITY_XHIGH";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 280:
        i = 7;
        str = "DENSITY_280";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 240:
        i = 3;
        str = "DENSITY_HIGH";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 160:
        i = 2;
        str = "DENSITY_MEDIUM";
        escribeArchivo("Utilities", "SCREEN_DENSITY", str);
        return i;
      case 120:
        break;
    } 
    i = 1;
    String str = "DENSITY_LOW";
    escribeArchivo("Utilities", "SCREEN_DENSITY", str);
    return i;
  }
  
  public static Enums.Services getSpeedCode(onstarApplication paramonstarApplication, String paramString) {
    for (String paramString : getLastKnownDeviceSelected(paramonstarApplication, paramString).getMainActions().keySet()) {
      if (paramString.equals(Enums.Services.Speed.GetCodeString()))
        return Enums.Services.Speed; 
      if (paramString.equals(Enums.Services.SpeedUUx.GetCodeString()))
        return Enums.Services.SpeedUUx; 
      if (paramString.equals(Enums.Services.SpeedAlways.GetCodeString()))
        return Enums.Services.SpeedAlways; 
    } 
    return Enums.Services.None;
  }
  
  public static String getStringFromConfigList(Context paramContext, String paramString, int paramInt) {
    String str = (String)GlobalMembers.hashmapStrings.get(paramString);
    if (str == null) {
      paramString = paramContext.getResources().getString(paramInt);
    } else {
      paramString = str;
      if (str.isEmpty())
        paramString = paramContext.getResources().getString(paramInt); 
    } 
    return paramString;
  }
  
  public static Date getTime() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar calendar = Calendar.getInstance();
    Date date = new Date();
    try {
      Date date1 = simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
      date = date1;
    } catch (ParseException parseException) {
      parseException.printStackTrace();
    } 
    return date;
  }
  
  public static HashMap<String, String> getTokenAndDate(Context paramContext) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("MoTokn", PreferenceRT.GetValuePreference("MoTokn", "", paramContext));
    hashMap.put("DaTokn", PreferenceRT.GetValuePreference("DaTokn", "", paramContext));
    return (HashMap)hashMap;
  }
  
  public static void getTokenAndURLToAttemptToPay(DBFunctions paramDBFunctions, final Context context) {
    String str = paramDBFunctions.getCurentIDGMT(getLastKnownDeviceSelected((onstarApplication)GlobalMembers.contexGlobal.getApplicationContext(), "Utilities").getDeviceId());
    (new GetURLChangeCreditCard(context, new AsyncResponseList() {
          final Context val$context;
          
          public void processFinish(List<String> param1List) {
            if (param1List != null && !param1List.isEmpty()) {
              String str1 = param1List.get(0);
              String str2 = param1List.get(1);
              if (((String)param1List.get(2)).equals("-1")) {
                final Dialog dialog = Utilities.simpleDialog(context, (Drawable)null, "Aviso", "Sua data de cobrança está muito próxima, por favor ligue para 0800 0111095, opção 3 opção 4 para atualizar seu cartão.", true, "Aceitar", false, "");
                dialog.findViewById(2131296343).setOnClickListener(new View.OnClickListener(this) {
                      final Dialog val$dialog;
                      
                      public void onClick(View param2View) {
                        dialog.dismiss();
                      }
                    });
                dialog.show();
              } else if (str2.equals("")) {
                StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
                String str = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText(context, str, 1).show();
              } else {
                Intent intent = new Intent(context.getApplicationContext(), GenericWVActivity.class);
                intent.putExtra(GenericWVActivity.EXTRA_TYPE_TAG, 2);
                intent.putExtra(GenericWVActivity.EXTRA_URL, str2);
                intent.putExtra("token", str1);
                context.startActivity(intent);
              } 
            } else {
              StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
              String str = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText(context, str, 1).show();
            } 
          }
        }str)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public static String getUTCDateTime(String paramString1, String paramString2) {
    String str = paramString2;
    if (paramString1 != null) {
      SimpleDateFormat simpleDateFormat = null;
      if (paramString1.equals("MM/dd/yyyy hh:mm:ss a")) {
        simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.US);
      } else if (paramString1.equals("dd/MM/yyyy HH:mm:ss")) {
        simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.FRENCH);
      } 
      str = paramString2;
      if (paramString2 != null)
        try {
          Date date = simpleDateFormat.parse(paramString2);
          SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
          this("yyyy-MM-dd HH:mm:ss");
          str = simpleDateFormat1.format(date);
        } catch (ParseException parseException) {
          escribeArchivo("Utilities", "Error: getUTCDateTime", parseException.getMessage());
          str = paramString2;
        }  
    } 
    return str;
  }
  
  public static String getUTCToNormalDate(String paramString1, String paramString2) {
    SimpleDateFormat simpleDateFormat;
    if (paramString2.equals("MM/dd/yyyy hh:mm:ss a")) {
      simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    } else if (paramString2.equals("dd/MM/yyyy HH:mm:ss") || paramString2.equals("dd/MM/yyyy HH:mm")) {
      simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRENCH);
    } else {
      simpleDateFormat = null;
    } 
    String str = paramString1;
    try {
      Date date = simpleDateFormat.parse(paramString1);
      str = paramString1;
      SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
      str = paramString1;
      this(paramString2);
      str = paramString1;
      paramString1 = simpleDateFormat1.format(date);
      str = paramString1;
      paramString1 = paramString1.substring(0, paramString2.length());
      str = paramString1;
    } catch (ParseException parseException) {
      escribeArchivo("Utilities", "Error: getUTCDateTime", parseException.getMessage());
    } 
    return str;
  }
  
  public static final String getUpdateStatus(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 136:
        return "EOTARG_Modem_Fw_Update";
      case 135:
        return "EOTARG_PsPrima_Update";
      case 134:
        return "EOTARG_Decrypt_Package";
      case 133:
        return "EOTARG_Verify_Signature";
      case 132:
        return "EOTARG_Starting_Validation_Test_Failed";
      case 131:
        return "EOTARG_Starting_Validation_Test";
      case 130:
        return "EOTARG_Starting_Apply_Maps_Delta";
      case 129:
        return "EOTARG_Starting_Apply_Resources_Delta";
      case 128:
        return "EOTARG_Starting_Apply_Agent_Update";
      case 127:
        return "EOTARG_RB_UpdateFail";
      case 126:
        return "EOTARG_RB_Memory_Fail";
      case 125:
        return "EOTARG_MainBatteryDisconnected";
      case 124:
        return "EOTARG_User_Postponed_Burn";
      case 123:
        return "EOTARG_User_Confirmed_Burn";
      case 122:
        return "EOTARG_Profile_Update";
      case 121:
        return "EOTARG_Retry_After_Fail";
      case 120:
        return "EOTARG_Set_Wakeup";
      case 119:
        return "EOTARG_Notified_Burn";
      case 118:
        return "EOTARG_Update_Failed";
      case 117:
        return "EOTARG_SOC_Update_Complete";
      case 116:
        return "EOTARG_MCU_Update_Available";
      case 115:
        return "EOTARG_Starting_Apply_Resources";
      case 114:
        return "EOTARG_Starting_Apply_Recovery";
      case 113:
        return "EOTARG_Starting_Apply_Normal";
      case 112:
        return "EOTARG_Burn_Postponed";
      case 111:
        return "EOTARG_Requested_Confirmation";
      case 110:
        return "EOTARG_CRC_Failed";
      case 109:
        return "EOTARG_CRC_Passed";
      case 108:
        return "EOTARG_SOC_Ready";
      case 107:
        return "EOTARG_Unpack_Failed";
      case 106:
        return "EOTARG_Unpack_Succeeded";
      case 105:
        return "EOTARG_Verify_Failed";
      case 104:
        return "EOTARG_Verify_Succeeded";
      case 103:
        return "EOTARG_Download_Complete";
      case 102:
        return "EOTARG_Starting_Download";
      case 101:
        return "EOTARG_Cancel_Ota";
      case 100:
        break;
    } 
    return "EOTARG_Starting_Ota";
  }
  
  public static String getpath(Activity paramActivity) {
    return paramActivity.getExternalCacheDir().getPath();
  }
  
  public static String googleNormalizeAddress(String paramString1, String paramString2) {
    String str1 = paramString2;
    if (paramString2 == null)
      str1 = ""; 
    String str2 = str1.trim();
    paramString2 = str2;
    if (!str1.isEmpty()) {
      paramString2 = str2;
      if (paramString1 != null)
        if (paramString1.isEmpty()) {
          paramString2 = str2;
        } else {
          paramString1 = paramString1.trim();
          paramString2 = str2;
          try {
            if (str1.contains(paramString1))
              if (str1.contains("-")) {
                String[] arrayOfString = str1.split("-");
                paramString2 = str2;
                if (arrayOfString.length > 1)
                  paramString2 = arrayOfString[1].toString(); 
              } else {
                paramString2 = str2;
                if (str1.contains(",")) {
                  String[] arrayOfString = str1.split(",");
                  paramString2 = str2;
                  if (arrayOfString.length > 2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    this();
                    stringBuilder.append(arrayOfString[1].toString());
                    stringBuilder.append(",");
                    stringBuilder.append(arrayOfString[2].toString());
                    String str = stringBuilder.toString();
                  } 
                } 
              }  
          } catch (Exception exception) {
            escribeArchivo("Utilities", "Error", exception.getMessage());
            paramString2 = str2;
          } 
        }  
    } 
    return paramString2;
  }
  
  public static boolean isActivityRunning(Context paramContext, Class<?> paramClass) {
    String str = "";
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null) {
      String str1 = str;
      try {
        String str3 = paramClass.getName();
        str1 = str;
        String str2 = paramClass.getSimpleName();
        str1 = str2;
        Iterator iterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).iterator();
        while (true) {
          str1 = str2;
          bool1 = bool2;
          if (iterator.hasNext()) {
            str1 = str2;
            bool1 = ((ActivityManager.RunningTaskInfo)iterator.next()).topActivity.getClassName().equals(str3);
            if (bool1) {
              bool1 = true;
              break;
            } 
            continue;
          } 
          break;
        } 
      } catch (Exception exception) {
        escribeArchivo(str1, "Error: ", exception.getMessage());
        bool1 = bool2;
      } 
    } 
    return bool1;
  }
  
  public static Boolean isAndinos() {
    return Boolean.valueOf(false);
  }
  
  public static String isNullString(String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    return str;
  }
  
  public static boolean isNumeric(String paramString) {
    try {
      Integer.parseInt(paramString);
      return true;
    } catch (NumberFormatException numberFormatException) {
      return false;
    } 
  }
  
  public static boolean isStringNullOrWhiteSpace(String paramString) {
    if (paramString == null)
      return true; 
    for (byte b = 0; b < paramString.length(); b++) {
      if (!Character.isWhitespace(paramString.charAt(b)))
        return false; 
    } 
    return true;
  }
  
  public static boolean isUUx(onstarApplication paramonstarApplication) {
    boolean bool3 = GlobalMembers.isUUxAvailable;
    boolean bool2 = false;
    boolean bool1 = false;
    if (!bool3)
      return false; 
    if (paramonstarApplication != null) {
      UserDevicesVO userDevicesVO = getLastKnownDeviceSelected(paramonstarApplication, "Utilities");
      try {
        bool2 = userDevicesVO.getDeviceTypeId().equals("UUx");
        try {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("");
          stringBuilder.append(userDevicesVO.getDeviceTypeId());
          escribeArchivo("Utilities", "DeviceTypeId", stringBuilder.toString());
          return bool2;
        } catch (Exception null) {
          bool1 = bool2;
        } 
      } catch (Exception exception) {}
      escribeArchivo("Utilities", "isUUx(rtApp)", exception.getMessage());
    } else {
      escribeArchivo("Utilities", "DeviceTypeId", "rtApp null");
      bool1 = bool2;
    } 
    return bool1;
  }
  
  public static boolean isValidActionVehicle(onstarApplication paramonstarApplication, String paramString) {
    UserDevicesVO userDevicesVO = getLastKnownDeviceSelected(paramonstarApplication, paramString);
    return (userDevicesVO.getMainActions().containsKey("53") || userDevicesVO.getButtomActions().containsKey("53") || userDevicesVO.getNavigationActions().containsKey("53") || userDevicesVO.getPidActions().containsKey("53"));
  }
  
  public static boolean isValidDevice(BluetoothDevice paramBluetoothDevice) {
    int i = paramBluetoothDevice.getBluetoothClass().getMajorDeviceClass();
    int j = paramBluetoothDevice.getType();
    paramBluetoothDevice.getName();
    boolean bool = true;
    if (j != 1 || i != 1024)
      bool = false; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(i);
    escribeArchivo("Utilities", "Clase p8", stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(j);
    escribeArchivo("Utilities", "Tipo p8", stringBuilder.toString());
    return bool;
  }
  
  public static boolean isValidToken(String paramString) {
    Date date1 = tokenDateStringToDate(paramString);
    Date date2 = new Date(System.currentTimeMillis());
    boolean bool = true;
    if (date1 == null || date1.compareTo(date2) != 1)
      bool = false; 
    return bool;
  }
  
  public static void logActions(String paramString1, String paramString2, String paramString3) {
    int i = 0;
    try {
      int j = Integer.valueOf(paramString2).intValue();
      try {
        int k = Integer.valueOf(paramString3).intValue();
        i = k;
      } catch (Exception null) {}
    } catch (Exception exception) {
      boolean bool = false;
    } 
    escribeArchivo("Utilities", "Error: logActions", exception.getMessage());
  }
  
  public static String niceDate(String paramString, int paramInt) {
    try {
      String str2;
      String str3;
      String[] arrayOfString2 = paramString.split(" ");
      if (paramInt == 0) {
        String[] arrayOfString = arrayOfString2[0].split("-");
        if (arrayOfString[2].length() == 1) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("0");
          stringBuilder1.append(arrayOfString[2]);
          str2 = stringBuilder1.toString();
        } else {
          str2 = arrayOfString[2];
        } 
        if (arrayOfString[1].length() == 1) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("0");
          stringBuilder1.append(arrayOfString[1]);
          str3 = stringBuilder1.toString();
        } else {
          str3 = arrayOfString[1];
        } 
        if (arrayOfString[0].length() == 1) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("0");
          stringBuilder1.append(arrayOfString[0]);
          str1 = stringBuilder1.toString();
        } else {
          str1 = str1[0];
        } 
      } else if (paramInt == 1) {
        String[] arrayOfString = arrayOfString2[0].split("\\/");
        if (arrayOfString[1].length() == 1) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("0");
          stringBuilder1.append(arrayOfString[1]);
          str2 = stringBuilder1.toString();
        } else {
          str2 = arrayOfString[1];
        } 
        if (arrayOfString[0].length() == 1) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("0");
          stringBuilder1.append(arrayOfString[0]);
          str3 = stringBuilder1.toString();
        } else {
          str3 = arrayOfString[0];
        } 
        if (arrayOfString[2].length() == 1) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("0");
          stringBuilder1.append(arrayOfString[2]);
          str1 = stringBuilder1.toString();
        } else {
          str1 = str1[2];
        } 
      } else {
        str1 = "";
        str2 = "";
        str3 = str2;
      } 
      String[] arrayOfString1 = arrayOfString2[1].split(":");
      String str5 = arrayOfString1[0];
      String str6 = arrayOfString1[1];
      String str7 = arrayOfString1[2];
      String str4 = str5;
      if (paramInt == 1) {
        boolean bool = arrayOfString2[2].equalsIgnoreCase("PM");
        if (bool && !str5.equalsIgnoreCase("12")) {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("");
          stringBuilder1.append(Integer.valueOf(str5).intValue() + 12);
          String str = stringBuilder1.toString();
        } else {
          str4 = str5;
          if (arrayOfString2[2].equalsIgnoreCase("AM")) {
            str4 = str5;
            if (str5.equalsIgnoreCase("12"))
              str4 = "00"; 
          } 
        } 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(str2);
      stringBuilder.append("-");
      stringBuilder.append(str3);
      stringBuilder.append("-");
      stringBuilder.append(str1);
      stringBuilder.append(" ");
      stringBuilder.append(str4);
      stringBuilder.append(":");
      stringBuilder.append(str6);
      stringBuilder.append(":");
      stringBuilder.append(str7);
      String str1 = stringBuilder.toString();
      paramString = str1;
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: niceData", exception.getMessage());
    } 
    return paramString;
  }
  
  public static void openOriginalRenewalActivity(final Context context) {
    (new GetRenewalPlansTask(context, new RenewalPlans_Interface() {
          final Context val$context;
          
          final String val$global_lbl_acciondescfallared_1;
          
          public void processFinish(RenewalPlansListResponseO param1RenewalPlansListResponseO) {
            if (param1RenewalPlansListResponseO != null) {
              if (param1RenewalPlansListResponseO.getCpres5() != null && param1RenewalPlansListResponseO.getCpres5().size() > 0) {
                Intent intent = new Intent(context, OriginalRenewalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("service_response", (Serializable)param1RenewalPlansListResponseO);
                bundle.putString("previous_class", MainActivity.class.getSimpleName());
                intent.putExtras(bundle);
                context.startActivity(intent);
              } else {
                Toast.makeText(context, global_lbl_acciondescfallared_1, 1).show();
                Intent intent = new Intent();
                intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
                context.sendBroadcast(intent);
              } 
            } else {
              Toast.makeText(context, global_lbl_acciondescfallared_1, 1).show();
              Intent intent = new Intent();
              intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
              context.sendBroadcast(intent);
            } 
          }
        })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public static Bitmap paintRedPoint(Context paramContext, int paramInt, boolean paramBoolean) {
    Bitmap bitmap = BitmapFactory.decodeResource(paramContext.getResources(), 2131165513);
    int m = bitmap.getWidth();
    int j = bitmap.getHeight();
    bitmap = Bitmap.createBitmap(m, j, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawBitmap(bitmap, 0.0F, 0.0F, null);
    Paint paint = new Paint();
    paint.setColor(paramContext.getResources().getColor(2131034259));
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    int k = m / 4;
    int i = k * 3;
    j /= 4;
    canvas.drawCircle(i, j, k, paint);
    if (paramBoolean) {
      Paint paint1 = new Paint(1);
      paint1.setTextAlign(Paint.Align.CENTER);
      float f = (m / 8 * 2);
      paint1.setTextSize(f);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramInt);
      stringBuilder.append("");
      String str = stringBuilder.toString();
      Rect rect = new Rect();
      paint1.getTextBounds(str, 0, str.length(), rect);
      k = rect.height();
      paramInt = rect.width();
      paint.setTextSize(f);
      paint.setColor(-1);
      canvas.drawText(str, (i - paramInt / 2), (j + k / 2), paint);
    } 
    return bitmap;
  }
  
  public static Bitmap paintRedPointDTC(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean) {
    Bitmap bitmap2 = BitmapFactory.decodeResource(paramContext.getResources(), paramInt2);
    Bitmap.Config config = bitmap2.getConfig();
    int i = bitmap2.getWidth();
    int j = bitmap2.getHeight();
    Bitmap bitmap1 = Bitmap.createBitmap(i, j, config);
    Canvas canvas = new Canvas(bitmap1);
    canvas.drawBitmap(bitmap2, 0.0F, 0.0F, null);
    Paint paint = new Paint();
    paint.setColor(paramContext.getResources().getColor(2131034259));
    paint.setStyle(Paint.Style.FILL);
    float f = (paramContext.getResources().getDisplayMetrics()).density;
    paramInt2 = (int)(15.0F * f + 0.5F);
    j /= 4;
    int k = i / 5;
    canvas.drawCircle((i - paramInt2), j, k, paint);
    if (paramBoolean) {
      paint.setTextSize((int)(12.0F * f + 0.5F));
      paint.setColor(-1);
      paramInt2 = paramInt1;
      if (paramInt1 > 50)
        paramInt2 = 50; 
      if (paramInt2 >= 10) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramInt2);
        stringBuilder.append("");
        canvas.drawText(stringBuilder.toString(), (i - (int)(22.0F * f + 0.5F)), (j + (int)(f * 3.0F + 0.5F)), paint);
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramInt2);
        stringBuilder.append("");
        canvas.drawText(stringBuilder.toString(), (i - (int)(18.0F * f + 0.5F)), (j + (int)(f * 3.0F + 0.5F)), paint);
      } 
    } 
    return bitmap1;
  }
  
  public static Enums.Services parseActionType(int paramInt) {
    Enums.Services services1;
    Enums.Services services2 = Enums.Services.None;
    Enums.Services[] arrayOfServices = Enums.Services.values();
    int i = arrayOfServices.length;
    byte b = 0;
    while (true) {
      services1 = services2;
      if (b < i) {
        services1 = arrayOfServices[b];
        if (services1.GetCode() == paramInt)
          break; 
        b++;
        continue;
      } 
      break;
    } 
    return services1;
  }
  
  public static int parseIntPIDValue(String paramString) {
    if (paramString != null && !paramString.isEmpty()) {
      boolean bool1;
      try {
        if (paramString.contains(".")) {
          bool1 = Double.valueOf(paramString).intValue();
        } else {
          bool1 = Integer.parseInt(paramString);
        } 
      } catch (NumberFormatException numberFormatException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error al parsear String : strValue");
        stringBuilder.append(numberFormatException.toString());
        escribeArchivo("Utilities", "THEFT", stringBuilder.toString());
        bool1 = false;
      } 
      return bool1;
    } 
    boolean bool = false;
  }
  
  public static void paymentProcessFunction(final Context context) {
    (new GetPaymentProcessTask(context, new Base_Interface() {
          final Context val$context;
          
          final StringsResourcesVO val$stringsResourcesVO;
          
          public void onFail(String param1String) {}
          
          public void onSuccess(String param1String) {
            try {
              if (param1String.contains("https")) {
                PaymentCardResponse paymentCardResponse = new PaymentCardResponse();
                this("", param1String, "");
                Intent intent = new Intent();
                this(context, PaymentCardInfo.class);
                Bundle bundle = new Bundle();
                this();
                bundle.putSerializable("resultObject", (Serializable)paymentCardResponse);
                intent.putExtras(bundle);
                context.startActivity(intent);
              } else {
                param1String = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
                Toast.makeText(context, param1String, 1).show();
              } 
            } catch (MalformedURLException malformedURLException) {
              Utilities.escribeArchivo("Utilities", "paymentCardInfoFunction", malformedURLException.getMessage());
              String str = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862);
              Toast.makeText(context, str, 1).show();
            } 
          }
        }null)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public static void renewalVehiclesListFunction(Context paramContext, List<VehicleCatalogVO> paramList1, List<VehicleCatalogVO> paramList2, List<VehicleCatalogVO> paramList3) {
    Intent intent = new Intent(paramContext, RenewalVehiclesListActivity.class);
    Bundle bundle = new Bundle();
    intent.addFlags(268435456);
    bundle.putSerializable("almostExpired", (Serializable)paramList1);
    bundle.putSerializable("expired", (Serializable)paramList2);
    bundle.putSerializable("normal", (Serializable)paramList3);
    intent.putExtras(bundle);
    paramContext.startActivity(intent);
  }
  
  public static final void saveLastFollowMeMessageId(Context paramContext, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("MESSAGEID:::");
    stringBuilder.append(paramString);
    escribeArchivo("SAVE_MESSAGEID", "SAVE_MESSAGEID", stringBuilder.toString());
    PreferenceRT.SetValuePreference("com.roadtrack.onstar.FOLLOWME_MESSAGE_ID", paramString, paramContext);
  }
  
  public static void saveLocale(String paramString, Context paramContext) {
    PreferenceRT.SetValuePreference("Language", paramString, paramContext);
  }
  
  public static final void saveNotification(Context paramContext, boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4) {
    Enums.responcesGCM responcesGCM1;
    Enums.Services services2;
    DBFunctions dBFunctions = new DBFunctions(paramContext);
    PushNotificationsVO pushNotificationsVO = new PushNotificationsVO();
    Enums.responcesGCM responcesGCM2 = Enums.responcesGCM.none_acc;
    new String();
    new String();
    Enums.Services services1 = Enums.Services.None;
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.asynctask_get_last_incoming_message_notification_title, 2131689678);
    String str3 = getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_acciondescinfolocalizacion_1, 2131689864);
    String str4 = getStringFromConfigList(paramContext, stringsResourcesVO.push_findme_title, 2131690274);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionlocalizame_1, 2131689877);
    if (paramBoolean) {
      Enums.responcesGCM responcesGCM = Enums.responcesGCM.follow_acc;
      services2 = Enums.Services.FollowMe;
      str1 = "12";
      str4 = str2;
      responcesGCM1 = responcesGCM;
    } else {
      responcesGCM1 = Enums.responcesGCM.commands_acc;
      services2 = Enums.Services.FindMe;
      String str = "10";
      str3 = str1;
      str1 = str;
    } 
    pushNotificationsVO.setIdResponse("2");
    pushNotificationsVO.setAcc(responcesGCM1);
    pushNotificationsVO.setAccNumber(str1);
    pushNotificationsVO.setTitle(str4);
    pushNotificationsVO.setMessage(str3);
    pushNotificationsVO.setActionId(services2);
    pushNotificationsVO.setNumberActionID(services2.toString());
    pushNotificationsVO.setDeviceId(paramString1);
    pushNotificationsVO.setAlertId("N/A");
    pushNotificationsVO.setLatitude(paramString2);
    pushNotificationsVO.setLongitude(paramString3);
    dBFunctions.addPush(pushNotificationsVO, paramString4);
  }
  
  public static void saveTokenAndDate(String paramString1, String paramString2, Context paramContext) {
    PreferenceRT.SetValuePreference("MoTokn", paramString1, paramContext);
    PreferenceRT.SetValuePreference("DaTokn", paramString2, paramContext);
  }
  
  public static final void sendIntentCallAction(Context paramContext) {
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.nothing, 2131690207);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.noCalls, 2131690199);
    try {
      String str = GlobalMembers.renewalPhone;
      Intent intent = new Intent();
      this("android.intent.action.CALL");
      intent.addFlags(268435456);
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("tel:");
      stringBuilder.append(str);
      intent.setData(Uri.parse(stringBuilder.toString()));
      GlobalMembers.contexGlobal.startActivity(intent);
    } catch (Exception exception) {
      (new MessageRTMobile(GlobalMembers.contexGlobal)).MessageToast(GlobalMembers.contexGlobal, str2, str1);
      escribeArchivo("Utilities", "Error: sentIntentCallAction", exception.getMessage());
    } 
  }
  
  public static final void sendIntentCallAction(Context paramContext, String paramString) {
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.nothing, 2131690207);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.noCalls, 2131690199);
    try {
      Intent intent = new Intent();
      this("android.intent.action.CALL");
      intent.addFlags(268435456);
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("tel:");
      stringBuilder.append(paramString);
      intent.setData(Uri.parse(stringBuilder.toString()));
      GlobalMembers.contexGlobal.startActivity(intent);
    } catch (Exception exception) {
      (new MessageRTMobile(GlobalMembers.contexGlobal)).MessageToast(GlobalMembers.contexGlobal, str2, str1);
      escribeArchivo("Utilities", "Error: sentIntentCallAction", exception.getMessage());
    } 
  }
  
  @SuppressLint({"NewApi"})
  public static void setBackgroundDrawable(View paramView, Drawable paramDrawable) {
    if (Build.VERSION.SDK_INT < 16) {
      paramView.setBackgroundDrawable(paramDrawable);
    } else {
      paramView.setBackground(paramDrawable);
    } 
  }
  
  public static final void setDeviceType(onstarApplication paramonstarApplication) {
    String str = getLastKnownDeviceSelected(paramonstarApplication, "Utilities").getDeviceTypeId();
    if (str != null && !str.equals("")) {
      GlobalMembers.deviceType = str;
    } else {
      GlobalMembers.deviceType = GlobalMembers.deviceTypeP7;
    } 
  }
  
  public static void setLanguageAndRegion() {
    Context context = onstarApplication.getContext();
    Locale locale = new Locale("pt", "BR");
    saveLocale("pt", context);
    Configuration configuration = new Configuration();
    configuration.locale = locale;
    context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("setting language and region: ");
    stringBuilder.append("pt");
    stringBuilder.append(" - ");
    stringBuilder.append("BR");
    escribeArchivo("Utilities", "LANGUAGE", stringBuilder.toString());
  }
  
  public static boolean setLastFollowMeId(Context paramContext, String paramString) {
    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    editor.putString("com.roadtrack.last.follow.me.device.id", paramString);
    return editor.commit();
  }
  
  public static final void setPlatinumConnection(Context paramContext, String paramString1, String paramString2) {
    PreferenceRT.SetValuePreference(Enums.SettingsPreference.PlatinumConnection, paramString1, paramContext);
    PreferenceRT.SetValuePreference(Enums.SettingsPreference.MacAddressPlatinum, paramString2, paramContext);
  }
  
  public static boolean showNetworkServiceData(final TextView view, final Context ctx, TextView... oView) {
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str1 = getStringFromConfigList(ctx, stringsResourcesVO.global_lbl_conexiondered_1, 2131689912);
    String str2 = getStringFromConfigList(ctx, stringsResourcesVO.global_lbl_rednodisponible_1, 2131689932);
    boolean bool = NetUtilities.validateNetwork(ctx, false, false);
    if (bool) {
      view.setText(str1);
      if (oView.length > 0 && oView[0] != null)
        oView[0].setText(str1); 
      (new Thread(new Runnable() {
            final Context val$ctx;
            
            final TextView[] val$oView;
            
            final TextView val$view;
            
            public void run() {
              try {
                Thread.sleep(2000L);
              } catch (InterruptedException interruptedException) {
                Utilities.escribeArchivo("Utilities", "Error: runnable", interruptedException.toString());
              } 
              ((Activity)ctx).runOnUiThread(new Runnable() {
                    final Utilities.null this$0;
                    
                    public void run() {
                      view.setVisibility(8);
                      TextView[] arrayOfTextView = oView;
                      if (arrayOfTextView.length > 0 && arrayOfTextView[0] != null)
                        arrayOfTextView[0].setVisibility(8); 
                    }
                  });
            }
          })).start();
    } else {
      view.setVisibility(0);
      view.setText(str2);
      if (oView.length > 0 && oView[0] != null) {
        oView[0].setVisibility(0);
        oView[0].setText(str2);
      } 
    } 
    return bool;
  }
  
  public static void showTheftAutoBanner(Context paramContext, boolean paramBoolean) {
    showTheftAutoBanner((TextView)((Activity)paramContext).findViewById(2131297032), paramContext, paramBoolean);
  }
  
  public static boolean showTheftAutoBanner(TextView paramTextView, Context paramContext, boolean paramBoolean) {
    Dialog dialog;
    if (paramTextView == null)
      return false; 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str3 = getStringFromConfigList(paramContext, stringsResourcesVO.main_lbl_popup_vehiculorobado_2, 2131690068);
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str4 = getStringFromConfigList(paramContext, stringsResourcesVO.main_lbl_banner_vehiculorobado_1, 2131690066);
    paramTextView.setBackgroundColor(-65536);
    if (paramBoolean) {
      paramTextView.setVisibility(0);
      paramTextView.setText(str4);
      escribeArchivo("Utilities", "THEFT", "Mostrando THEFT DIALOG");
      if (dialogTheft == null) {
        dialogTheft = simpleDialog(paramContext, (Drawable)null, str1, str3, true, str2, false, "0");
        dialogTheft.setCancelable(false);
      } 
      ((Button)dialogTheft.findViewById(2131296343)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              Utilities.isTheftDialogRunning = false;
              Utilities.dialogTheft.dismiss();
              Utilities.dialogTheft = null;
            }
          });
      dialog = dialogTheft;
      if (dialog != null && !isTheftDialogRunning) {
        isTheftDialogRunning = true;
        dialog.show();
      } 
      return true;
    } 
    dialog.setVisibility(8);
    return false;
  }
  
  public static boolean showTheftAutoBanner(TextView paramTextView, Context paramContext, boolean paramBoolean1, boolean paramBoolean2) {
    Dialog dialog;
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955);
    String str3 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.main_lbl_popup_vehiculorobado_2, 2131690068);
    String str4 = getStringFromConfigList(paramContext, stringsResourcesVO.main_lbl_banner_vehiculorobado_1, 2131690066);
    if (paramTextView == null)
      return false; 
    paramTextView.setBackgroundColor(-65536);
    if (paramBoolean1) {
      paramTextView.setVisibility(0);
      paramTextView.setText(str4);
      if (paramBoolean2) {
        if (dialogTheft == null) {
          dialogTheft = simpleDialog(paramContext, (Drawable)null, str2, str1, true, str3, false, "0");
          dialogTheft.setCancelable(false);
        } 
        ((Button)dialogTheft.findViewById(2131296343)).setOnClickListener(new View.OnClickListener() {
              public void onClick(View param1View) {
                Utilities.isTheftDialogRunning = false;
                Utilities.dialogTheft.dismiss();
                Utilities.dialogTheft = null;
              }
            });
        dialog = dialogTheft;
        if (dialog != null && !isTheftDialogRunning) {
          isTheftDialogRunning = true;
          dialog.show();
        } 
      } 
      return true;
    } 
    dialog.setVisibility(8);
    return false;
  }
  
  public static Dialog simpleDialog(Context paramContext, int paramInt, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4) {
    return simpleDialog(paramContext, paramInt, paramString1, paramString2, paramBoolean1, paramString3, paramBoolean2, paramString4, true);
  }
  
  public static Dialog simpleDialog(Context paramContext, int paramInt, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4, boolean paramBoolean3) {
    // Byte code:
    //   0: new android/app/Dialog
    //   3: dup
    //   4: aload_0
    //   5: ldc_w 16973840
    //   8: invokespecial <init> : (Landroid/content/Context;I)V
    //   11: astore #10
    //   13: aload #10
    //   15: invokevirtual getWindow : ()Landroid/view/Window;
    //   18: pop
    //   19: aload #10
    //   21: iconst_1
    //   22: invokevirtual requestWindowFeature : (I)Z
    //   25: pop
    //   26: aload #10
    //   28: invokevirtual getWindow : ()Landroid/view/Window;
    //   31: ifnull -> 56
    //   34: aload #10
    //   36: invokevirtual getWindow : ()Landroid/view/Window;
    //   39: new android/graphics/drawable/ColorDrawable
    //   42: dup
    //   43: aload_0
    //   44: ldc_w 2131034153
    //   47: invokestatic getColor : (Landroid/content/Context;I)I
    //   50: invokespecial <init> : (I)V
    //   53: invokevirtual setBackgroundDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   56: aload #10
    //   58: aload_0
    //   59: ldc_w 2131427473
    //   62: aconst_null
    //   63: invokestatic inflate : (Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;
    //   66: invokevirtual setContentView : (Landroid/view/View;)V
    //   69: aload #10
    //   71: iload #8
    //   73: invokevirtual setCancelable : (Z)V
    //   76: iload_1
    //   77: ldc_w 2131165443
    //   80: if_icmpeq -> 93
    //   83: iload_1
    //   84: istore #9
    //   86: iload_1
    //   87: ldc_w 2131165484
    //   90: if_icmpne -> 96
    //   93: iconst_0
    //   94: istore #9
    //   96: iload #9
    //   98: ifeq -> 130
    //   101: aload #10
    //   103: ldc_w 2131296260
    //   106: invokevirtual findViewById : (I)Landroid/view/View;
    //   109: checkcast android/widget/TextView
    //   112: aload_0
    //   113: aload_0
    //   114: iload #9
    //   116: invokestatic getDrawable : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   119: bipush #100
    //   121: invokestatic resizeDrawable : (Landroid/content/Context;Landroid/graphics/drawable/Drawable;I)Landroid/graphics/drawable/Drawable;
    //   124: aconst_null
    //   125: aconst_null
    //   126: aconst_null
    //   127: invokevirtual setCompoundDrawablesWithIntrinsicBounds : (Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V
    //   130: aload #10
    //   132: ldc_w 2131296260
    //   135: invokevirtual findViewById : (I)Landroid/view/View;
    //   138: checkcast android/widget/TextView
    //   141: aload_2
    //   142: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   145: aload #10
    //   147: ldc_w 2131296257
    //   150: invokevirtual findViewById : (I)Landroid/view/View;
    //   153: checkcast android/widget/TextView
    //   156: aload_3
    //   157: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   160: new com/roadtrack/onstar/VO/StringsResourcesVO
    //   163: dup
    //   164: invokespecial <init> : ()V
    //   167: astore_3
    //   168: aload_0
    //   169: aload_3
    //   170: getfield global_configuracion_map_lbl_descargarmayus_1 : Ljava/lang/String;
    //   173: ldc_w 2131689840
    //   176: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   179: astore_2
    //   180: aload #10
    //   182: ldc_w 2131296343
    //   185: invokevirtual findViewById : (I)Landroid/view/View;
    //   188: checkcast android/widget/Button
    //   191: aload_2
    //   192: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   195: aload_0
    //   196: aload_3
    //   197: getfield mapdownloading_popup_btn_despues_4 : Ljava/lang/String;
    //   200: ldc_w 2131690116
    //   203: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   206: astore_0
    //   207: aload #10
    //   209: ldc_w 2131296344
    //   212: invokevirtual findViewById : (I)Landroid/view/View;
    //   215: checkcast android/widget/Button
    //   218: aload_0
    //   219: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   222: iload #4
    //   224: ifeq -> 261
    //   227: aload #10
    //   229: ldc_w 2131296343
    //   232: invokevirtual findViewById : (I)Landroid/view/View;
    //   235: checkcast android/widget/Button
    //   238: iconst_0
    //   239: invokevirtual setVisibility : (I)V
    //   242: aload #10
    //   244: ldc_w 2131296343
    //   247: invokevirtual findViewById : (I)Landroid/view/View;
    //   250: checkcast android/widget/Button
    //   253: aload #5
    //   255: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   258: goto -> 277
    //   261: aload #10
    //   263: ldc_w 2131296343
    //   266: invokevirtual findViewById : (I)Landroid/view/View;
    //   269: checkcast android/widget/Button
    //   272: bipush #8
    //   274: invokevirtual setVisibility : (I)V
    //   277: iload #6
    //   279: ifeq -> 316
    //   282: aload #10
    //   284: ldc_w 2131296344
    //   287: invokevirtual findViewById : (I)Landroid/view/View;
    //   290: checkcast android/widget/Button
    //   293: iconst_0
    //   294: invokevirtual setVisibility : (I)V
    //   297: aload #10
    //   299: ldc_w 2131296344
    //   302: invokevirtual findViewById : (I)Landroid/view/View;
    //   305: checkcast android/widget/Button
    //   308: aload #7
    //   310: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   313: goto -> 332
    //   316: aload #10
    //   318: ldc_w 2131296344
    //   321: invokevirtual findViewById : (I)Landroid/view/View;
    //   324: checkcast android/widget/Button
    //   327: bipush #8
    //   329: invokevirtual setVisibility : (I)V
    //   332: aload #10
    //   334: areturn
  }
  
  public static Dialog simpleDialog(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4) {
    return simpleDialog(paramContext, paramDrawable, paramString1, paramString2, paramBoolean1, paramString3, paramBoolean2, paramString4, true);
  }
  
  public static Dialog simpleDialog(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4, float paramFloat1, float paramFloat2) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    getStringFromConfigList(paramContext, stringsResourcesVO.deviceName, 2131689751);
    getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_lbl_actualizar_1, 2131689954);
    getStringFromConfigList(paramContext, stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    dialog.setContentView(View.inflate(paramContext, 2131427473, null));
    dialog.setCancelable(true);
    ((TextView)dialog.findViewById(2131296260)).setTextSize(paramFloat1);
    ((TextView)dialog.findViewById(2131296257)).setTextSize(16.0F);
    ((TextView)dialog.findViewById(2131296260)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296260)).setText(paramString1);
    ((TextView)dialog.findViewById(2131296257)).setText(paramString2);
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_configuracion_map_lbl_descargarmayus_1, 2131689840);
    ((Button)dialog.findViewById(2131296343)).setText(str2);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.mapdownloading_popup_btn_despues_4, 2131690116);
    ((Button)dialog.findViewById(2131296344)).setText(str1);
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296343)).setVisibility(0);
      ((Button)dialog.findViewById(2131296343)).setText(paramString3);
    } else {
      ((Button)dialog.findViewById(2131296343)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296344)).setVisibility(0);
      ((Button)dialog.findViewById(2131296344)).setText(paramString4);
    } else {
      ((Button)dialog.findViewById(2131296344)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static Dialog simpleDialog(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4, float paramFloat1, float paramFloat2, int paramInt) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    dialog.setContentView(View.inflate(paramContext, 2131427473, null));
    dialog.setCancelable(true);
    ((TextView)dialog.findViewById(2131296260)).setTextSize(paramFloat1);
    ((TextView)dialog.findViewById(2131296257)).setTextSize(16.0F);
    ((TextView)dialog.findViewById(2131296260)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296260)).setText(paramString1);
    ((TextView)dialog.findViewById(2131296257)).setTextColor(ContextCompat.getColor(paramContext, paramInt));
    ((TextView)dialog.findViewById(2131296257)).setText(paramString2);
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_configuracion_map_lbl_descargarmayus_1, 2131689840);
    ((Button)dialog.findViewById(2131296343)).setText(str2);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.mapdownloading_popup_btn_despues_4, 2131690116);
    ((Button)dialog.findViewById(2131296344)).setText(str1);
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296343)).setVisibility(0);
      ((Button)dialog.findViewById(2131296343)).setText(paramString3);
    } else {
      ((Button)dialog.findViewById(2131296343)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296344)).setVisibility(0);
      ((Button)dialog.findViewById(2131296344)).setText(paramString4);
    } else {
      ((Button)dialog.findViewById(2131296344)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static Dialog simpleDialog(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4, boolean paramBoolean3) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    dialog.setContentView(View.inflate(paramContext, 2131427473, null));
    dialog.setCancelable(paramBoolean3);
    ((TextView)dialog.findViewById(2131296260)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296260)).setText(paramString1);
    ((TextView)dialog.findViewById(2131296257)).setText(paramString2);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_configuracion_map_lbl_descargarmayus_1, 2131689840);
    ((Button)dialog.findViewById(2131296343)).setText(str2);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.mapdownloading_popup_btn_despues_4, 2131690116);
    ((Button)dialog.findViewById(2131296344)).setText(str1);
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296343)).setVisibility(0);
      ((Button)dialog.findViewById(2131296343)).setText(paramString3);
    } else {
      ((Button)dialog.findViewById(2131296343)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296344)).setVisibility(0);
      ((Button)dialog.findViewById(2131296344)).setText(paramString4);
    } else {
      ((Button)dialog.findViewById(2131296344)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static Dialog simpleDialogCheckBoxBottomButon(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4, float paramFloat1, float paramFloat2, boolean paramBoolean3, String paramString5, boolean paramBoolean4, boolean paramBoolean5, String paramString6, String paramString7) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    dialog.setContentView(View.inflate(paramContext, 2131427362, null));
    dialog.setCancelable(false);
    ((TextView)dialog.findViewById(2131296734)).setTextSize(paramFloat1);
    ((TextView)dialog.findViewById(2131296733)).setTextSize(16.0F);
    ((TextView)dialog.findViewById(2131296734)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296734)).setText((CharSequence)Html.fromHtml(paramString1));
    ((TextView)dialog.findViewById(2131296733)).setText(paramString2);
    if (!paramBoolean3) {
      ((CheckBox)dialog.findViewById(2131296468)).setVisibility(8);
    } else {
      ((CheckBox)dialog.findViewById(2131296468)).setVisibility(0);
      if (paramBoolean4) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<u>");
        stringBuilder.append(paramString5);
        stringBuilder.append("</u>");
        String str = stringBuilder.toString();
        ((CheckBox)dialog.findViewById(2131296468)).setText((CharSequence)Html.fromHtml(str));
      } else {
        ((CheckBox)dialog.findViewById(2131296468)).setText(paramString5);
      } 
    } 
    if (paramBoolean5) {
      ((TextView)dialog.findViewById(2131296731)).setVisibility(0);
      ((TextView)dialog.findViewById(2131296732)).setVisibility(0);
      ((TextView)dialog.findViewById(2131296731)).setText((CharSequence)Html.fromHtml(paramString6));
      ((TextView)dialog.findViewById(2131296732)).setText((CharSequence)Html.fromHtml(paramString7));
    } else {
      ((TextView)dialog.findViewById(2131296731)).setVisibility(8);
      ((TextView)dialog.findViewById(2131296732)).setVisibility(8);
    } 
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296421)).setVisibility(0);
      ((Button)dialog.findViewById(2131296421)).setText(paramString3);
    } else {
      ((Button)dialog.findViewById(2131296421)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296420)).setVisibility(0);
      ((Button)dialog.findViewById(2131296420)).setText(paramString4);
    } else {
      ((Button)dialog.findViewById(2131296420)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static Dialog simpleDialogCheckBoxBottomButonMap(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4, float paramFloat1, float paramFloat2, boolean paramBoolean3, String paramString5, boolean paramBoolean4, boolean paramBoolean5, String paramString6, String paramString7) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    dialog.setContentView(View.inflate(paramContext, 2131427359, null));
    dialog.setCancelable(false);
    ((TextView)dialog.findViewById(2131296734)).setTextSize(paramFloat1);
    ((TextView)dialog.findViewById(2131296733)).setTextSize(16.0F);
    ((TextView)dialog.findViewById(2131296734)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296734)).setText((CharSequence)Html.fromHtml(paramString1));
    ((TextView)dialog.findViewById(2131296733)).setText(paramString2);
    if (!paramBoolean3) {
      ((CheckBox)dialog.findViewById(2131296468)).setVisibility(8);
    } else {
      ((CheckBox)dialog.findViewById(2131296468)).setVisibility(0);
      if (paramBoolean4) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<u>");
        stringBuilder.append(paramString5);
        stringBuilder.append("</u>");
        String str = stringBuilder.toString();
        ((CheckBox)dialog.findViewById(2131296468)).setText((CharSequence)Html.fromHtml(str));
      } else {
        ((CheckBox)dialog.findViewById(2131296468)).setText(paramString5);
      } 
    } 
    if (paramBoolean5) {
      ((TextView)dialog.findViewById(2131296731)).setVisibility(0);
      ((TextView)dialog.findViewById(2131296732)).setVisibility(0);
      ((TextView)dialog.findViewById(2131296731)).setText((CharSequence)Html.fromHtml(paramString6));
      ((TextView)dialog.findViewById(2131296732)).setText((CharSequence)Html.fromHtml(paramString7));
    } else {
      ((TextView)dialog.findViewById(2131296731)).setVisibility(8);
      ((TextView)dialog.findViewById(2131296732)).setVisibility(8);
    } 
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296421)).setVisibility(0);
      ((Button)dialog.findViewById(2131296421)).setText(paramString3);
    } else {
      ((Button)dialog.findViewById(2131296421)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296420)).setVisibility(0);
      ((Button)dialog.findViewById(2131296420)).setText(paramString4);
    } else {
      ((Button)dialog.findViewById(2131296420)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static Dialog simpleDialogEdit(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4, float paramFloat1, float paramFloat2) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    getStringFromConfigList(paramContext, stringsResourcesVO.deviceName, 2131689751);
    getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_lbl_actualizar_1, 2131689954);
    getStringFromConfigList(paramContext, stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    dialog.setContentView(View.inflate(paramContext, 2131427476, null));
    dialog.setCancelable(true);
    ((TextView)dialog.findViewById(2131296260)).setTextSize(paramFloat1);
    ((TextView)dialog.findViewById(2131296257)).setTextSize(16.0F);
    ((TextView)dialog.findViewById(2131296260)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296260)).setText(paramString1);
    ((TextView)dialog.findViewById(2131296257)).setText(paramString2);
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_configuracion_map_lbl_descargarmayus_1, 2131689840);
    ((Button)dialog.findViewById(2131296343)).setText(str2);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.mapdownloading_popup_btn_despues_4, 2131690116);
    ((Button)dialog.findViewById(2131296344)).setText(str1);
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296343)).setVisibility(0);
      ((Button)dialog.findViewById(2131296343)).setText(paramString3);
    } else {
      ((Button)dialog.findViewById(2131296343)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296344)).setVisibility(0);
      ((Button)dialog.findViewById(2131296344)).setText(paramString4);
    } else {
      ((Button)dialog.findViewById(2131296344)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static Dialog simpleDialogHiper(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4, float paramFloat1, float paramFloat2, String paramString5) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    getStringFromConfigList(paramContext, stringsResourcesVO.deviceName, 2131689751);
    getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_lbl_actualizar_1, 2131689954);
    getStringFromConfigList(paramContext, stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    dialog.setContentView(View.inflate(paramContext, 2131427473, null));
    dialog.setCancelable(true);
    ((TextView)dialog.findViewById(2131296260)).setTextSize(paramFloat1);
    ((TextView)dialog.findViewById(2131296257)).setTextSize(16.0F);
    ((TextView)dialog.findViewById(2131296260)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296260)).setText(paramString1);
    ((TextView)dialog.findViewById(2131296257)).setText((CharSequence)Html.fromHtml(paramString2));
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
    layoutParams.setMargins(0, 2, 0, 0);
    ((TextView)dialog.findViewById(2131296257)).setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    ((TextView)dialog.findViewById(2131296258)).setVisibility(0);
    ((TextView)dialog.findViewById(2131296258)).setText((CharSequence)Html.fromHtml(paramString5));
    ((TextView)dialog.findViewById(2131296258)).setTextSize(16.0F);
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_configuracion_map_lbl_descargarmayus_1, 2131689840);
    ((Button)dialog.findViewById(2131296343)).setText(str2);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.mapdownloading_popup_btn_despues_4, 2131690116);
    ((Button)dialog.findViewById(2131296344)).setText(str1);
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296343)).setVisibility(0);
      ((Button)dialog.findViewById(2131296343)).setText(paramString3);
    } else {
      ((Button)dialog.findViewById(2131296343)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296344)).setVisibility(0);
      ((Button)dialog.findViewById(2131296344)).setText(paramString4);
    } else {
      ((Button)dialog.findViewById(2131296344)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static Dialog simpleDialogMultiCheckBox(Context paramContext, Drawable paramDrawable, String paramString1, ArrayList<String> paramArrayList, boolean paramBoolean1, String paramString2, boolean paramBoolean2, String paramString3, float paramFloat, AdapterDialogMultiCheckBox paramAdapterDialogMultiCheckBox) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    dialog.setContentView(View.inflate(paramContext, 2131427471, null));
    dialog.setCancelable(true);
    ((ListView)dialog.findViewById(2131296869)).setAdapter((ListAdapter)paramAdapterDialogMultiCheckBox);
    ((TextView)dialog.findViewById(2131296260)).setTextSize(paramFloat);
    ((TextView)dialog.findViewById(2131296260)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296260)).setText(paramString1);
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.global_configuracion_map_lbl_descargarmayus_1, 2131689840);
    ((Button)dialog.findViewById(2131296343)).setText(str2);
    str2 = getStringFromConfigList(paramContext, stringsResourcesVO.mapdownloading_popup_btn_despues_4, 2131690116);
    ((Button)dialog.findViewById(2131296344)).setText(str2);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.accesos_rapidos_mapa_seleccioneopciones, 2131689611);
    ((TextView)dialog.findViewById(2131297165)).setText(str1);
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296343)).setVisibility(0);
      ((Button)dialog.findViewById(2131296343)).setText(paramString2);
    } else {
      ((Button)dialog.findViewById(2131296343)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296344)).setVisibility(0);
      ((Button)dialog.findViewById(2131296344)).setText(paramString3);
    } else {
      ((Button)dialog.findViewById(2131296344)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static Dialog simpleDialogWithCheckBox(Context paramContext, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, String paramString4, float paramFloat1, float paramFloat2, boolean paramBoolean3, String paramString5) {
    Dialog dialog = new Dialog(paramContext, 16973840);
    dialog.getWindow();
    dialog.requestWindowFeature(1);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(paramContext, 2131034153))); 
    dialog.setContentView(View.inflate(paramContext, 2131427474, null));
    dialog.setCancelable(true);
    ((TextView)dialog.findViewById(2131296260)).setTextSize(paramFloat1);
    ((TextView)dialog.findViewById(2131296257)).setTextSize(16.0F);
    ((TextView)dialog.findViewById(2131296260)).setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(paramContext, paramDrawable, 100), null, null, null);
    ((TextView)dialog.findViewById(2131296260)).setText(paramString1);
    ((TextView)dialog.findViewById(2131296257)).setText(paramString2);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.actions_popup_btn_activar_1, 2131689638);
    ((Button)dialog.findViewById(2131296343)).setText(str2);
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    ((Button)dialog.findViewById(2131296344)).setText(str1);
    if (paramBoolean1) {
      ((Button)dialog.findViewById(2131296343)).setVisibility(0);
      ((Button)dialog.findViewById(2131296343)).setText(paramString3);
    } else {
      ((Button)dialog.findViewById(2131296343)).setVisibility(8);
    } 
    if (paramBoolean2) {
      ((Button)dialog.findViewById(2131296344)).setVisibility(0);
      ((Button)dialog.findViewById(2131296344)).setText(paramString4);
    } else {
      ((Button)dialog.findViewById(2131296344)).setVisibility(8);
    } 
    if (paramBoolean3) {
      ((CheckBox)dialog.findViewById(2131296460)).setText(paramString5);
      ((CheckBox)dialog.findViewById(2131296460)).setVisibility(0);
    } else {
      ((CheckBox)dialog.findViewById(2131296460)).setVisibility(8);
    } 
    return dialog;
  }
  
  public static void stopWakeDevice() {
    if (PowerManagerWakelock != null)
      while (PowerManagerWakelock.isHeld())
        PowerManagerWakelock.release();  
  }
  
  public static String toAscii(int paramInt) {
    return Character.toString((char)paramInt);
  }
  
  public static Date tokenDateStringToDate(String paramString) {
    String str2 = null;
    String str1 = str2;
    if (paramString != null) {
      str1 = str2;
      if (!paramString.isEmpty())
        try {
          str1 = paramString.replace(",", "");
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
          this("EEE dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
          Date date = simpleDateFormat.parse(str1);
        } catch (Exception exception) {
          str1 = str2;
        }  
    } 
    return (Date)str1;
  }
  
  public static String unicodeToString(String paramString) {
    if (paramString == null || paramString.isEmpty())
      return ""; 
    Matcher matcher = Pattern.compile("\\\\u(\\p{XDigit}{4})").matcher(paramString);
    StringBuffer stringBuffer = new StringBuffer(paramString.length());
    while (matcher.find())
      matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(String.valueOf((char)Integer.parseInt(matcher.group(1), 16)))); 
    matcher.appendTail(stringBuffer);
    return stringBuffer.toString();
  }
  
  public static void updateVehicleSelected(Context paramContext, String paramString, UserDevicesVO paramUserDevicesVO) {
    DBFunctions dBFunctions = new DBFunctions(paramContext);
    for (VehicleCatalogVO vehicleCatalogVO : dBFunctions.getVehiclesCatalog(paramString)) {
      if (!"L".equalsIgnoreCase(vehicleCatalogVO.getSelected())) {
        if (paramUserDevicesVO.getDeviceId().equals(vehicleCatalogVO.getDeviceId())) {
          dBFunctions.updateVehicleSelected(vehicleCatalogVO.getUser(), vehicleCatalogVO.getId(), "T");
          continue;
        } 
        dBFunctions.updateVehicleSelected(vehicleCatalogVO.getUser(), vehicleCatalogVO.getId(), "F");
      } 
    } 
  }
  
  public static boolean validateCharSequence(String paramString) {
    String str = Decrypt(Crypt(paramString));
    return (!paramString.contains(",") && !paramString.contains("<") && !paramString.contains(">") && !paramString.contains("|") && paramString.equalsIgnoreCase(str));
  }
  
  public static final void validateMapUpdate(Context paramContext, DBFunctions paramDBFunctions) {
    DBFunctions dBFunctions = paramDBFunctions;
    if (paramDBFunctions == null)
      dBFunctions = new DBFunctions(paramContext); 
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str1 = getStringFromConfigList(paramContext, stringsResourcesVO.main_activity_no_bluetooth, 2131690057);
    String str2 = getStringFromConfigList(paramContext, stringsResourcesVO.main_acticity_p8_funtion, 2131690028);
    if (!dBFunctions.isMapUpdateEmpty())
      if (GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8) && MainActivity.isBtAndPlatinumConnected()) {
        MainActivity.getmNPlatinum().RequestPlatinumVersions();
      } else if (GlobalMembers.deviceType.equals(GlobalMembers.deviceTypeP8) && !MainActivity.isBtAndPlatinumConnected()) {
        Toast.makeText(paramContext, str1, 1).show();
      } else {
        Toast.makeText(paramContext, str2, 1).show();
      }  
  }
  
  public static void waiting(int paramInt) {
    long l = (paramInt * 1000);
    try {
      Thread.sleep(l);
    } catch (Exception exception) {
      escribeArchivo("Utilities", "ERROR WAIT", exception.getMessage());
    } 
  }
  
  public static void wakeDevice(Context paramContext, String paramString) {
    try {
      _PowerManager = (PowerManager)paramContext.getSystemService("power");
      PowerManagerWakelock = _PowerManager.newWakeLock(268435466, paramString);
      PowerManagerWakelock.setReferenceCounted(false);
      if (PowerManagerWakelock.isHeld())
        PowerManagerWakelock.release(); 
      PowerManagerWakelock.acquire();
    } catch (Exception exception) {
      escribeArchivo("Utilities", "Error: wakeDevice", exception.getMessage());
    } 
  }
  
  public String defineDeviceTypeId(int paramInt) {
    String str = "P7";
    if (paramInt != 6)
      if (paramInt == 8) {
        str = "P8";
      } else if (paramInt == 7) {
        str = "UUx";
      } else if (paramInt == 13) {
        str = "PNG";
      }  
    return str;
  }
  
  public String getCrypt(String paramString) {
    return Crypt(paramString);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/Utilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */