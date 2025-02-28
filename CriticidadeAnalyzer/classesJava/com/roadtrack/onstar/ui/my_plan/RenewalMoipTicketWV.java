package com.roadtrack.onstar.ui.my_plan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.MotionEvent;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.RenewalDialogManager;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class RenewalMoipTicketWV extends Activity {
  public static final String TAG = RenewalMoipTicketWV.class.getName();
  
  private boolean isPageFullyLoaded = false;
  
  private TextView lbl_armt_device_name;
  
  final Timer myTimer = new Timer();
  
  private ProgressDialog progressDialog;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private WebView wv_armt_pay;
  
  private void dismissDialog() {
    if (this.progressDialog.isShowing())
      this.progressDialog.dismiss(); 
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void loadURL(HashMap<String, String> paramHashMap) {
    String str = validateURL(paramHashMap);
    if (str != null && !str.isEmpty() && URLUtil.isValidUrl(str)) {
      this.myTimer.schedule(new loaderTask(), 30000L);
      this.wv_armt_pay.getSettings().setJavaScriptEnabled(true);
      this.wv_armt_pay.getSettings().setDomStorageEnabled(true);
      this.wv_armt_pay.getSettings().setMediaPlaybackRequiresUserGesture(true);
      this.wv_armt_pay.addJavascriptInterface(new JavaScriptInterface((Context)this), "Android");
      this.wv_armt_pay.getSettings().setAllowContentAccess(false);
      this.wv_armt_pay.getSettings().setAllowFileAccess(false);
      this.wv_armt_pay.getSettings().setAllowFileAccessFromFileURLs(false);
      this.wv_armt_pay.getSettings().setAllowUniversalAccessFromFileURLs(false);
      this.wv_armt_pay.setWebViewClient(new WebViewClient() {
            final RenewalMoipTicketWV this$0;
            
            public void onLoadResource(WebView param1WebView, String param1String) {
              super.onLoadResource(param1WebView, param1String);
              if (RenewalMoipTicketWV.this.wv_armt_pay.getProgress() == 100) {
                RenewalMoipTicketWV.this.progressDialog.dismiss();
                RenewalMoipTicketWV.access$202(RenewalMoipTicketWV.this, true);
                RenewalMoipTicketWV.this.myTimer.cancel();
              } 
            }
            
            public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
              super.onPageStarted(param1WebView, param1String, param1Bitmap);
            }
            
            public WebResourceResponse shouldInterceptRequest(WebView param1WebView, WebResourceRequest param1WebResourceRequest) {
              Utilities.escribeArchivo(RenewalMoipTicketWV.TAG, "shouldInterceptRequest", "*************************************");
              return super.shouldInterceptRequest(param1WebView, param1WebResourceRequest);
            }
            
            public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
              Boolean bool1;
              String[] arrayOfString = RenewalMoipTicketWV.this.getResources().getStringArray(2130837507);
              byte b = 0;
              Boolean bool2 = Boolean.valueOf(false);
              int i = arrayOfString.length;
              while (true) {
                bool1 = bool2;
                if (b < i) {
                  if (param1String.contains(Utilities.Decrypt(arrayOfString[b]))) {
                    bool1 = Boolean.valueOf(true);
                    break;
                  } 
                  b++;
                  continue;
                } 
                break;
              } 
              if (!bool1.booleanValue())
                param1String = GlobalMembers.DOMAIN_CHEVROLET; 
              return super.shouldOverrideUrlLoading(param1WebView, param1String);
            }
          });
      this.wv_armt_pay.setDownloadListener(new DownloadListener() {
            final RenewalMoipTicketWV this$0;
            
            public void onDownloadStart(String param1String1, String param1String2, String param1String3, String param1String4, long param1Long) {
              param1String2 = RenewalMoipTicketWV.TAG;
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Url: web.loadUrl: ");
              stringBuilder.append(param1String1);
              Utilities.escribeArchivo(param1String2, "Urls", stringBuilder.toString());
              RenewalMoipTicketWV.this.wv_armt_pay.loadUrl(RenewalMoipTicketWV.JavaScriptInterface.getBase64StringFromBlobUrl(param1String1));
              Utilities.escribeArchivo(RenewalMoipTicketWV.TAG, "onDownloadStart", "------------------------>***");
              StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
              String str = Utilities.getStringFromConfigList((Context)RenewalMoipTicketWV.this, stringsResourcesVO.renovacion_lbl_notificacionarchivodescargado, 2131690303);
              Toast.makeText(RenewalMoipTicketWV.this.getApplicationContext(), str, 1).show();
            }
          });
      if (URLUtil.isValidUrl(str)) {
        String str1 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Url: web.loadUrl: ");
        stringBuilder.append(str);
        Utilities.escribeArchivo(str1, "Urls", stringBuilder.toString());
        if (!NetUtilities.validateNetwork((Context)this, false)) {
          onResponseError();
        } else {
          this.wv_armt_pay.loadUrl(UtilitiesFile.validatePath(str));
        } 
      } else {
        onResponseError();
      } 
    } else {
      onResponseError();
    } 
  }
  
  private void onResponseError() {
    dismissDialog();
    this.myTimer.cancel();
    RenewalDialogManager.getInstance().setDialog_type(4);
    RenewalDialogManager.getInstance().setContext((Context)this);
    RenewalDialogManager.getInstance().showDialog();
  }
  
  private static String validateParam(String paramString) {
    if (paramString.contains("http://") || paramString.contains("/") || paramString.contains("http")) {
      paramString.replace("http://", "");
      paramString.replace("/", "");
      paramString.replace("http", "");
    } 
    return Utilities.EncryptData(paramString);
  }
  
  private static String validateURL(HashMap<String, String> paramHashMap) {
    if (paramHashMap != null && !paramHashMap.isEmpty() && ((String)paramHashMap.get("URL")).equals(GlobalMembers.MoipTicketHistory))
      try {
        String str3 = URLEncoder.encode(validateParam(paramHashMap.get("lpar1")), "UTF-8");
        String str5 = URLEncoder.encode(validateParam(paramHashMap.get("lpar2")), "UTF-8");
        String str2 = URLEncoder.encode(validateParam(paramHashMap.get("lpar3")), "UTF-8");
        String str4 = URLEncoder.encode(validateParam(paramHashMap.get("lpar4")), "UTF-8");
        String str1 = URLEncoder.encode(paramHashMap.get("t"), "UTF-8");
        str1 = String.format(GlobalMembers.MoipTicketHistory, new Object[] { str3, str5, str2, str4, str1 });
        Utilities.escribeArchivo(TAG, "URL de boletos: ", str1);
        boolean bool = URLUtil.isValidUrl(str1);
        if (bool)
          return str1; 
      } catch (MalformedURLException|java.io.UnsupportedEncodingException malformedURLException) {
        Utilities.escribeArchivo(TAG, "Error en URL", "MalformedURLException | UnsupportedEncodingException");
      }  
    return null;
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
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427378);
    try {
      HashMap hashMap = (HashMap)getIntent().getExtras().getSerializable("url");
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error en URL", exception.getMessage());
      onBackPressed();
      exception = null;
    } 
    this.stringsResourcesVO = new StringsResourcesVO();
    this.lbl_armt_device_name = (TextView)findViewById(2131296728);
    this.wv_armt_pay = (WebView)findViewById(2131297260);
    String str = Utilities.getLastKnownDeviceSelected((onstarApplication)getApplicationContext(), TAG).getName();
    this.lbl_armt_device_name.setText(str);
    str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.please_wait, 2131690270);
    this.progressDialog = new ProgressDialog((Context)this, 2131755173);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setCancelable(false);
    this.progressDialog.setMessage(str);
    this.progressDialog.show();
    str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Url: web.loadUrl: ");
    stringBuilder.append(exception);
    Utilities.escribeArchivo(str, "Urls", stringBuilder.toString());
    loadURL((HashMap<String, String>)exception);
  }
  
  public static class JavaScriptInterface {
    private Context context;
    
    public JavaScriptInterface(Context param1Context) {
      this.context = param1Context;
    }
    
    private String decodePFD(String param1String) {
      Utilities.escribeArchivo(RenewalMoipTicketWV.TAG, "decodePFD", "Javascript Interface");
      if (param1String != null && !param1String.equals("")) {
        byte[] arrayOfByte = Base64.decode(param1String.replaceFirst("^data:application/pdf;base64,", ""), 0);
        File file = new File("/sdcard/Download/");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BoletoOnstar_");
        stringBuilder.append(Utilities.getActualTime().replace("/", "_").replace(":", "").replace(" ", "_"));
        stringBuilder.append(".pdf");
        String str = stringBuilder.toString();
        try {
          file.mkdirs();
          File file1 = new File();
          this(file, str);
          new FileOutputStream(file1);
          try {
            StringBuilder stringBuilder1 = new StringBuilder();
            this();
            stringBuilder1.append("/sdcard/Download/");
            stringBuilder1.append(str);
            String str1 = stringBuilder1.toString();
            FileOutputStream fileOutputStream = new FileOutputStream();
            this(str1);
            fileOutputStream.write(arrayOfByte);
            fileOutputStream.flush();
            fileOutputStream.close();
            SendNotify(str1);
            return "service_success";
          } catch (IOException iOException) {
            iOException.printStackTrace();
            return "service_error";
          } 
        } catch (FileNotFoundException fileNotFoundException) {
          fileNotFoundException.printStackTrace();
        } 
      } 
      return "service_error";
    }
    
    public static String getBase64StringFromBlobUrl(String param1String) {
      Utilities.escribeArchivo(RenewalMoipTicketWV.TAG, "getBase64StringFromBlobUrl", "Javascript Interface");
      if (param1String.startsWith("blob")) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript: var xhr = new XMLHttpRequest();xhr.open('GET', '");
        stringBuilder.append(param1String);
        stringBuilder.append("', true);xhr.setRequestHeader('Content-type','application/pdf');xhr.responseType = 'blob';xhr.onload = function(e) {    if (this.status == 200) {        var blobPdf = this.response;        var reader = new FileReader();        reader.readAsDataURL(blobPdf);        reader.onloadend = function() {            base64data = reader.result;            window.Android.getBase64FromBlobData(base64data);        }    }};xhr.send();");
        return stringBuilder.toString();
      } 
      return "javascript: console.log('It is not a Blob URL');";
    }
    
    public void SendNotify(String param1String) {
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      String str1 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.renovacion_lbl_notificacionarchivodescargado, 2131690303);
      String str2 = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.renovacion_lbl_archivodescargado, 2131690280);
      Utilities.isAndinos().booleanValue();
      String str3 = RenewalMoipTicketWV.TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("TITLE:");
      stringBuilder.append(str1);
      stringBuilder.append(" MESSAGE:");
      stringBuilder.append(str2);
      Utilities.escribeArchivo(str3, "PUSH-LOCAL", stringBuilder.toString());
      Uri uri = Uri.fromFile(new File(param1String));
      Intent intent = new Intent("android.intent.action.VIEW", uri);
      intent.setDataAndType(uri, "application/pdf");
      PendingIntent pendingIntent = PendingIntent.getActivity(this.context, 0, intent, 201326592);
      NotificationManager notificationManager = (NotificationManager)this.context.getSystemService("notification");
      NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context);
      builder.setContentText(str2);
      NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
      bigTextStyle.bigText(str2);
      builder.setStyle((NotificationCompat.Style)bigTextStyle);
      builder.setContentTitle(str1);
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
      notificationManager.notify(2131165536, builder.build());
    }
    
    @JavascriptInterface
    public void getBase64FromBlobData(String param1String) throws IOException {
      Utilities.escribeArchivo(RenewalMoipTicketWV.TAG, "getBase64FromBlobData", "Javascript Interface");
      decodePFD(param1String);
    }
  }
  
  class loaderTask extends TimerTask {
    final RenewalMoipTicketWV this$0;
    
    public void run() {
      if (RenewalMoipTicketWV.this.isPageFullyLoaded) {
        RenewalMoipTicketWV.this.dismissDialog();
      } else {
        RenewalMoipTicketWV.this.dismissDialog();
        RenewalMoipTicketWV.this.runOnUiThread(new Runnable() {
              final RenewalMoipTicketWV.loaderTask this$1;
              
              public void run() {
                RenewalMoipTicketWV.this.onResponseError();
              }
            });
      } 
      Utilities.escribeArchivo(RenewalMoipTicketWV.TAG, "timer", "Timer-----------------------------");
    }
  }
  
  class null implements Runnable {
    final RenewalMoipTicketWV.loaderTask this$1;
    
    public void run() {
      RenewalMoipTicketWV.this.onResponseError();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/my_plan/RenewalMoipTicketWV.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */