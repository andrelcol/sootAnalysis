package com.roadtrack.onstar.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import java.util.HashMap;

public class GenericWVActivity extends Activity {
  public static String EXTRA_DEVICE_ID;
  
  public static String EXTRA_TYPE_TAG;
  
  public static String EXTRA_URL;
  
  private static final String TAG = GenericWVActivity.class.getName();
  
  private Bundle extras;
  
  private TextView tvCarName;
  
  private String urlSite;
  
  private WebView wvGeneric;
  
  static {
    EXTRA_TYPE_TAG = "EXTRA_TYPE_WV_GEN";
    EXTRA_URL = "EXTRA_URL";
    EXTRA_DEVICE_ID = "EXTRA_DEVICE_ID";
  }
  
  private void checkTypes() {
    this.urlSite = this.extras.getString(EXTRA_URL);
    int i = this.extras.getInt(EXTRA_TYPE_TAG);
    if (i != 0)
      if (i != 1) {
        if (i != 2) {
          if (i == 3) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Url: web.loadUrl: ");
            stringBuilder.append(this.urlSite);
            Utilities.escribeArchivo(str, "Urls", stringBuilder.toString());
            loadUrlPaymentHistory(this.urlSite);
          } 
        } else {
          String str = TAG;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Url: web.loadUrl: ");
          stringBuilder.append(this.urlSite);
          Utilities.escribeArchivo(str, "Urls", stringBuilder.toString());
          loadUrlCreditCard(this.urlSite);
        } 
      } else {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Url: web.loadUrl: ");
        stringBuilder.append(this.urlSite);
        Utilities.escribeArchivo(str, "Urls", stringBuilder.toString());
        loadUrl(this.urlSite);
      }  
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void loadUrl(String paramString) {
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Url: web.loadUrl: ");
    stringBuilder.append(paramString);
    Utilities.escribeArchivo(str, "Urls", stringBuilder.toString());
    str = this.extras.getString("token");
    if (paramString != null && !paramString.isEmpty()) {
      this.wvGeneric.getSettings().setJavaScriptEnabled(true);
      this.wvGeneric.getSettings().setDomStorageEnabled(true);
      this.wvGeneric.getSettings().setMediaPlaybackRequiresUserGesture(true);
      this.wvGeneric.getSettings().setAllowContentAccess(false);
      this.wvGeneric.getSettings().setAllowFileAccess(false);
      this.wvGeneric.getSettings().setAllowFileAccessFromFileURLs(false);
      this.wvGeneric.getSettings().setAllowUniversalAccessFromFileURLs(false);
      this.wvGeneric.setWebViewClient(new WebViewClient() {
            final GenericWVActivity this$0;
            
            public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
              super.onPageStarted(param1WebView, param1String, param1Bitmap);
            }
            
            public WebResourceResponse shouldInterceptRequest(WebView param1WebView, WebResourceRequest param1WebResourceRequest) {
              return super.shouldInterceptRequest(param1WebView, param1WebResourceRequest);
            }
            
            public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
              Boolean bool1;
              String[] arrayOfString = GenericWVActivity.this.getResources().getStringArray(2130837507);
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
      if (URLUtil.isValidUrl(paramString)) {
        if (!NetUtilities.validateNetwork((Context)this, false)) {
          System.out.println("Error al cargar URL en generic");
        } else {
          HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Bearer ");
          stringBuilder1.append(str);
          hashMap.put("Authorization", stringBuilder1.toString());
          str = TAG;
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Url: web.loadUrl: ");
          stringBuilder1.append(paramString);
          Utilities.escribeArchivo(str, "Urls", stringBuilder1.toString());
          this.wvGeneric.loadUrl(UtilitiesFile.validatePath(paramString), hashMap);
        } 
      } else {
        System.out.println("Error al cargar URL en generic");
      } 
    } else {
      System.out.println("Error al cargar URL en generic");
    } 
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void loadUrlCreditCard(String paramString) {
    String str = this.extras.getString("token");
    if (paramString != null && !paramString.isEmpty()) {
      this.wvGeneric.getSettings().setJavaScriptEnabled(true);
      this.wvGeneric.getSettings().setDomStorageEnabled(true);
      this.wvGeneric.getSettings().setMediaPlaybackRequiresUserGesture(true);
      WebSettings webSettings = this.wvGeneric.getSettings();
      byte b = 0;
      webSettings.setAllowContentAccess(false);
      this.wvGeneric.getSettings().setAllowFileAccess(false);
      this.wvGeneric.getSettings().setAllowFileAccessFromFileURLs(false);
      this.wvGeneric.getSettings().setAllowUniversalAccessFromFileURLs(false);
      this.wvGeneric.setWebViewClient(new WebViewClient() {
            final GenericWVActivity this$0;
            
            public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
              super.onPageStarted(param1WebView, param1String, param1Bitmap);
            }
            
            public WebResourceResponse shouldInterceptRequest(WebView param1WebView, WebResourceRequest param1WebResourceRequest) {
              return super.shouldInterceptRequest(param1WebView, param1WebResourceRequest);
            }
            
            public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
              Boolean bool1;
              String[] arrayOfString = GenericWVActivity.this.getResources().getStringArray(2130837507);
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
      if (URLUtil.isValidUrl(paramString)) {
        if (!NetUtilities.validateNetwork((Context)this, false)) {
          System.out.println("Error al cargar URL en generic");
        } else {
          HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Bearer ");
          stringBuilder2.append(str);
          hashMap.put("Authorization", stringBuilder2.toString());
          String[] arrayOfString = getResources().getStringArray(2130837507);
          int i = arrayOfString.length;
          while (b < i && !paramString.contains(Utilities.Decrypt(arrayOfString[b])))
            b++; 
          String str1 = TAG;
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Url: web.loadUrl: ");
          stringBuilder1.append(paramString);
          Utilities.escribeArchivo(str1, "Urls", stringBuilder1.toString());
          this.wvGeneric.loadUrl(UtilitiesFile.validatePath(paramString), hashMap);
        } 
      } else {
        System.out.println("Error al cargar URL en generic");
      } 
    } else {
      System.out.println("Error al cargar URL en generic");
    } 
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void loadUrlPaymentHistory(String paramString) {
    if (paramString != null && !paramString.isEmpty()) {
      this.wvGeneric.getSettings().setJavaScriptEnabled(true);
      this.wvGeneric.getSettings().setDomStorageEnabled(true);
      this.wvGeneric.getSettings().setMediaPlaybackRequiresUserGesture(true);
      this.wvGeneric.getSettings().setAllowContentAccess(false);
      this.wvGeneric.getSettings().setAllowFileAccess(false);
      this.wvGeneric.getSettings().setAllowFileAccessFromFileURLs(false);
      this.wvGeneric.getSettings().setAllowUniversalAccessFromFileURLs(false);
      this.wvGeneric.setWebViewClient(new WebViewClient() {
            final GenericWVActivity this$0;
            
            public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
              super.onPageStarted(param1WebView, param1String, param1Bitmap);
            }
            
            public WebResourceResponse shouldInterceptRequest(WebView param1WebView, WebResourceRequest param1WebResourceRequest) {
              return super.shouldInterceptRequest(param1WebView, param1WebResourceRequest);
            }
            
            public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
              Boolean bool1;
              String[] arrayOfString = GenericWVActivity.this.getResources().getStringArray(2130837507);
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
      if (URLUtil.isValidUrl(paramString)) {
        if (!NetUtilities.validateNetwork((Context)this, false)) {
          System.out.println("Error al cargar URL en generic");
        } else {
          String str = TAG;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Url: web.loadUrl: ");
          stringBuilder.append(paramString);
          Utilities.escribeArchivo(str, "Urls", stringBuilder.toString());
          this.wvGeneric.loadUrl(UtilitiesFile.validatePath(paramString));
        } 
      } else {
        System.out.println("Error al cargar URL en generic");
      } 
    } else {
      System.out.println("Error al cargar URL en generic");
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427363);
    this.wvGeneric = (WebView)findViewById(2131297257);
    this.tvCarName = (TextView)findViewById(2131297143);
    UserDevicesVO userDevicesVO2 = Utilities.getLastKnownDeviceSelected((onstarApplication)getApplicationContext(), TAG);
    this.extras = getIntent().getExtras();
    Bundle bundle2 = this.extras;
    UserDevicesVO userDevicesVO1 = userDevicesVO2;
    if (bundle2 != null) {
      userDevicesVO1 = userDevicesVO2;
      if (bundle2.containsKey(EXTRA_DEVICE_ID))
        userDevicesVO1 = Utilities.getDeviceFromPush((onstarApplication)getApplicationContext(), this.extras.getString(EXTRA_DEVICE_ID), TAG); 
    } 
    if (userDevicesVO1 != null && userDevicesVO1.getName() != null)
      this.tvCarName.setText(userDevicesVO1.getName()); 
    Bundle bundle1 = this.extras;
    if (bundle1 != null && bundle1.containsKey(EXTRA_TYPE_TAG) && this.extras.containsKey(EXTRA_URL))
      checkTypes(); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/activities/GenericWVActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */