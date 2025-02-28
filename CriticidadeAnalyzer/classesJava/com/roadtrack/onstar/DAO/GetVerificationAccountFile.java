package com.roadtrack.onstar.DAO;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import androidx.core.app.NotificationCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.PushNotificationsVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.GetHexDumpMap;
import com.roadtrack.onstar.utils.Unzipper;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.FilenameUtils;

@SuppressLint({"SdCardPath", "DefaultLocale"})
public class GetVerificationAccountFile extends AsyncTask<String, Integer, String> {
  private static final String DATA_PATH;
  
  private static final String DATA_PATH2;
  
  private static final String PACKAGE_NAME;
  
  private static final String[] PACKAGE_NAMES = GetVerificationAccountFile.class.getPackage().getName().toString().split("\\.");
  
  private Context context;
  
  private FileOutputStream fileOutput;
  
  private String filePath;
  
  String filetodownload = "";
  
  int id = 1;
  
  private InputStream inputStream;
  
  NotificationCompat.Builder mBuilder;
  
  NotificationManager mNotifyManager;
  
  OnDownloadCompleteListener mOnDownloadCompleteListener;
  
  private File root;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private HttpsURLConnection urlConnection;
  
  static {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(PACKAGE_NAMES[0]);
    stringBuilder.append(".");
    stringBuilder.append(PACKAGE_NAMES[1]);
    stringBuilder.append(".");
    stringBuilder.append(PACKAGE_NAMES[2]);
    PACKAGE_NAME = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append("/Android/data/");
    stringBuilder.append(PACKAGE_NAME);
    DATA_PATH = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(Environment.getExternalStorageDirectory());
    stringBuilder.append("/");
    stringBuilder.append("Android");
    stringBuilder.append("/");
    stringBuilder.append("data");
    stringBuilder.append("/");
    stringBuilder.append(PACKAGE_NAME);
    DATA_PATH2 = stringBuilder.toString();
  }
  
  public GetVerificationAccountFile(Activity paramActivity) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(DATA_PATH);
    stringBuilder.append("/");
    stringBuilder.append("RoadTrack/");
    this.filePath = stringBuilder.toString();
    this.root = Environment.getExternalStorageDirectory();
    this.context = (Context)paramActivity;
    this.mNotifyManager = (NotificationManager)paramActivity.getSystemService("notification");
    this.mBuilder = new NotificationCompat.Builder((Context)paramActivity);
    this.stringsResourcesVO = new StringsResourcesVO();
    Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.global_lbl_acciondescinfolocalizacion_1, 2131689864);
    String str1 = Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.global_configuracion_map_lbl_descargarmayus_1, 2131689840);
    String str2 = Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.state_downloading, 2131690381);
    String str3 = Utilities.getStringFromConfigList((Context)paramActivity, this.stringsResourcesVO.general_ticker, 2131689830);
    NotificationCompat.Builder builder = this.mBuilder;
    builder.setContentTitle(str1);
    builder.setContentText(str2);
    builder.setSmallIcon(2131165484);
    this.mBuilder.setTicker(str3);
    this.mBuilder.setProgress(100, 0, false);
    if (26 <= Build.VERSION.SDK_INT) {
      NotificationChannel notificationChannel = new NotificationChannel(GlobalMembers.CHANNEL_ID, GlobalMembers.CHANNEL_NAME, GlobalMembers.CHANNEL_IMPORTANCE);
      this.mBuilder.setChannelId(GlobalMembers.CHANNEL_ID);
      this.mNotifyManager.createNotificationChannel(notificationChannel);
    } 
    this.mNotifyManager.notify(this.id, this.mBuilder.build());
  }
  
  public static void unzipFile() {
    String str = (new GetHexDumpMap()).getMapUpdateFile(GlobalMembers.mapFileName);
    try {
      String str1 = FilenameUtils.getExtension(str);
      if (str1 == null)
        return; 
      if (GlobalMembers.ZIP_TYPE.equalsIgnoreCase(str1)) {
        String[] arrayOfString;
        GetHexDumpMap getHexDumpMap = new GetHexDumpMap();
        this();
        String str2 = getHexDumpMap.getMapUpdateFile("_temp");
        str2 = Unzipper.getInstance().unzip(str, str2);
        if (str2 != null) {
          GlobalMembers.MAP_UPDATE_ON_PARTS = Boolean.valueOf(true);
          arrayOfString = str2.split("\\|");
          DBFunctions dBFunctions = new DBFunctions();
          this(GlobalMembers.contexGlobal);
          PushNotificationsVO pushNotificationsVO = new PushNotificationsVO();
          this();
          pushNotificationsVO.setFileName(arrayOfString[0]);
          pushNotificationsVO.setOldVersion(arrayOfString[1]);
          pushNotificationsVO.setNewVersion("1");
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(arrayOfString[0]);
          stringBuilder.append("_Part");
          stringBuilder.append(arrayOfString[1]);
          stringBuilder.append(".tbz");
          GlobalMembers.lastMapUpdateFile = stringBuilder.toString();
          if (dBFunctions.isMapUpdateEmptyPart()) {
            dBFunctions.addMapUpdateDataPart(pushNotificationsVO);
          } else {
            dBFunctions.updateMapUpdateDataPart(pushNotificationsVO);
          } 
        } else {
          try {
            File file = UtilitiesFile.getFileFromStringFile((String)arrayOfString);
            if (file.exists())
              file.delete(); 
          } catch (Exception exception) {
            Utilities.escribeArchivo("GetVerificationAccountFile", "UNZIP ERROR", exception.getMessage());
          } 
        } 
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("UNZIPED", "UNZIP", exception.getMessage());
    } 
  }
  
  private void updateProgress(int paramInt1, int paramInt2) {}
  
  @SuppressLint({"NewApi", "DefaultLocale"})
  protected String doInBackground(String... paramVarArgs) {
    Thread thread = Thread.currentThread();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(GetVerificationAccountFile.class.getSimpleName());
    stringBuilder.append(": ");
    stringBuilder.append(Thread.currentThread().getName());
    thread.setName(stringBuilder.toString());
    try {
      URL uRL = new URL();
      this(paramVarArgs[0]);
      StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder();
      this();
      StrictMode.setThreadPolicy(builder.permitAll().build());
      this.urlConnection = (HttpsURLConnection)uRL.openConnection();
      this.urlConnection.setRequestMethod("GET");
      this.urlConnection.setConnectTimeout(20000);
      this.urlConnection.connect();
      String str1 = paramVarArgs[0].toString();
      UtilitiesFile.reviewPathFile(str1);
      this.filetodownload = str1.substring(str1.lastIndexOf("/") + 1);
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append(DATA_PATH2);
      stringBuilder1.append("/");
      stringBuilder1.append("RoadTrack/");
      this.fileOutput = UtilitiesFile.fileOutputStreamFromStringFile(UtilitiesFile.getFileFromStringFile(stringBuilder1.toString(), this.filetodownload));
      this.inputStream = this.urlConnection.getInputStream();
      int j = this.urlConnection.getContentLength();
      long l = j / 100L;
      byte[] arrayOfByte = new byte[1024];
      int i = 0;
      label144: while (true) {
        long l1 = 0L;
        int k = i;
        while (true) {
          int m = this.inputStream.read(arrayOfByte);
          if (m > 0) {
            this.fileOutput.write(arrayOfByte, 0, m);
            FileOutputStream fileOutputStream1 = this.fileOutput;
            if (fileOutputStream1 == null)
              try {
                this.fileOutput.close();
              } catch (Exception exception) {} 
            i = k + m;
            long l2 = l1 + m;
            updateProgress(i, j);
            k = i;
            l1 = l2;
            if (l2 >= l) {
              publishProgress((Object[])new Integer[] { Integer.valueOf(j), Integer.valueOf(i) });
              Intent intent = new Intent();
              this();
              intent.setAction("GlobalTouchService");
              intent.putExtra("ACTION_EXTRA", "usuario_activo");
              GlobalMembers.contexGlobal.sendBroadcast(intent);
              continue label144;
            } 
            continue;
          } 
          this.inputStream.close();
          this.fileOutput.close();
          this.urlConnection.disconnect();
          StringBuilder stringBuilder2 = new StringBuilder();
          this();
          stringBuilder2.append(this.root);
          stringBuilder2.append(this.filePath);
          stringBuilder2.append(this.filetodownload);
          String str3 = stringBuilder2.toString();
          InputStream inputStream = this.inputStream;
          if (inputStream != null)
            try {
              inputStream.close();
            } catch (IOException iOException) {
              Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.inputStreamClose", iOException.getMessage());
            }  
          FileOutputStream fileOutputStream = this.fileOutput;
          if (fileOutputStream != null)
            try {
              fileOutputStream.close();
            } catch (IOException iOException) {
              Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.fileOutputClose", iOException.getMessage());
            }  
          HttpsURLConnection httpsURLConnection = this.urlConnection;
          String str2 = str3;
          if (httpsURLConnection != null)
            try {
              httpsURLConnection.disconnect();
              str2 = str3;
            } catch (Exception exception) {
              Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.urlConnectionDisconnect", exception.getMessage());
              str2 = str3;
            }  
          if (!str2.equals("ERROR"))
            unzipFile(); 
          return str2;
        } 
        break;
      } 
    } catch (MalformedURLException malformedURLException) {
      Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.MalformedURLException", malformedURLException.getMessage());
      InputStream inputStream = this.inputStream;
      if (inputStream != null)
        try {
          inputStream.close();
        } catch (IOException iOException) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.inputStreamClose", iOException.getMessage());
        }  
      FileOutputStream fileOutputStream = this.fileOutput;
      if (fileOutputStream != null)
        try {
          fileOutputStream.close();
        } catch (IOException iOException) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.fileOutputClose", iOException.getMessage());
        }  
      HttpsURLConnection httpsURLConnection = this.urlConnection;
      if (httpsURLConnection != null)
        try {
          httpsURLConnection.disconnect();
        } catch (Exception exception) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.urlConnectionDisconnect", exception.getMessage());
        }  
    } catch (SocketTimeoutException socketTimeoutException) {
      Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.SocketTimeoutException", socketTimeoutException.getMessage());
      InputStream inputStream = this.inputStream;
      if (inputStream != null)
        try {
          inputStream.close();
        } catch (IOException iOException) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.inputStreamClose", iOException.getMessage());
        }  
      FileOutputStream fileOutputStream = this.fileOutput;
      if (fileOutputStream != null)
        try {
          fileOutputStream.close();
        } catch (IOException iOException) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.fileOutputClose", iOException.getMessage());
        }  
      HttpsURLConnection httpsURLConnection = this.urlConnection;
      if (httpsURLConnection != null)
        try {
          httpsURLConnection.disconnect();
        } catch (Exception exception) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.urlConnectionDisconnect", exception.getMessage());
        }  
    } catch (IOException iOException) {
      Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.IOException", iOException.getMessage());
      InputStream inputStream = this.inputStream;
      if (inputStream != null)
        try {
          inputStream.close();
        } catch (IOException iOException1) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.inputStreamClose", iOException1.getMessage());
        }  
      FileOutputStream fileOutputStream = this.fileOutput;
      if (fileOutputStream != null)
        try {
          fileOutputStream.close();
        } catch (IOException iOException1) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.fileOutputClose", iOException1.getMessage());
        }  
      HttpsURLConnection httpsURLConnection = this.urlConnection;
      if (httpsURLConnection != null)
        try {
          httpsURLConnection.disconnect();
        } catch (Exception exception) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.urlConnectionDisconnect", exception.getMessage());
        }  
    } catch (Exception exception) {
      Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.Exception", exception.getMessage());
      InputStream inputStream = this.inputStream;
      if (inputStream != null)
        try {
          inputStream.close();
        } catch (IOException iOException) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.inputStreamClose", iOException.getMessage());
        }  
      FileOutputStream fileOutputStream = this.fileOutput;
      if (fileOutputStream != null)
        try {
          fileOutputStream.close();
        } catch (IOException iOException) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.fileOutputClose", iOException.getMessage());
        }  
      HttpsURLConnection httpsURLConnection = this.urlConnection;
      if (httpsURLConnection != null)
        try {
          httpsURLConnection.disconnect();
        } catch (Exception exception1) {
          Utilities.escribeArchivo("GetVerificationAccountFile", "Error: doInBackground.urlConnectionDisconnect", exception1.getMessage());
        }  
    } finally {}
    String str = "ERROR";
    if (!str.equals("ERROR"))
      unzipFile(); 
    return str;
  }
  
  protected void onPostExecute(String paramString) {
    String str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.mapdownloading_popup_lbl_descargaincompleta_1, 2131690121);
    String str2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.general_dowload_complete, 2131689827);
    if (paramString.equals("ERROR")) {
      this.mBuilder.setContentText(str1);
      this.mBuilder.setTicker(str1);
      this.mBuilder.setProgress(0, 0, false);
      this.mNotifyManager.notify(this.id, this.mBuilder.build());
      this.mNotifyManager.cancel(this.id);
      this.mOnDownloadCompleteListener.onDownloadError();
    } else {
      this.mBuilder.setContentText(str2);
      this.mBuilder.setTicker(str2);
      this.mBuilder.setProgress(0, 0, false);
      this.mNotifyManager.notify(this.id, this.mBuilder.build());
      this.mNotifyManager.cancel(this.id);
      GlobalMembers.downloadComplete = true;
      Utilities.escribeArchivo("GetVerificationAccountFile", "SERVER MAP DOWNLOAD", "CORRECTO");
      this.mOnDownloadCompleteListener.onDownloadComplete(paramString);
    } 
    super.onPostExecute(paramString);
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
  }
  
  protected void onProgressUpdate(Integer... paramVarArgs) {
    this.mBuilder.setProgress(paramVarArgs[0].intValue(), paramVarArgs[1].intValue(), false);
    this.mNotifyManager.notify(this.id, this.mBuilder.build());
    super.onProgressUpdate((Object[])paramVarArgs);
  }
  
  public void setOnDownloadCompleteListener(OnDownloadCompleteListener paramOnDownloadCompleteListener) {
    this.mOnDownloadCompleteListener = paramOnDownloadCompleteListener;
  }
  
  public static interface OnDownloadCompleteListener {
    void onDownloadComplete(String param1String);
    
    void onDownloadError();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/DAO/GetVerificationAccountFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */