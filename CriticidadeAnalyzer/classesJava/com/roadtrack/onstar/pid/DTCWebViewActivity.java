package com.roadtrack.onstar.pid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.utils.Utilities;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;

public class DTCWebViewActivity extends Activity {
  public static final String TAG = DTCWebViewActivity.class.getSimpleName();
  
  private WebView dtcWebView = null;
  
  private static String validateParam(String paramString) {
    if (paramString.contains("http://") || paramString.contains("/") || paramString.contains("http")) {
      paramString.replace("http://", "");
      paramString.replace("/", "");
      paramString.replace("http", "");
    } 
    return paramString;
  }
  
  private static String validateURL(HashMap<String, String> paramHashMap) {
    if (paramHashMap != null && !paramHashMap.isEmpty() && ((String)paramHashMap.get("URL")).equals(GlobalMembers.URL_DTC_LocatorWebApp))
      try {
        String str2 = paramHashMap.get("EfCve_DeviceId");
        validateParam(str2);
        str2 = URLEncoder.encode(str2, "UTF-8");
        String str3 = paramHashMap.get("tok");
        validateParam(str3);
        str3 = URLEncoder.encode(str3, "UTF-8");
        String str1 = paramHashMap.get("dtcGroup");
        validateParam(str1);
        str1 = URLEncoder.encode(str1, "UTF-8");
        str1 = String.format(GlobalMembers.URL_DTC_LocatorWebApp, new Object[] { str2, str3, str1 });
        boolean bool = URLUtil.isValidUrl(str1);
        if (bool)
          return str1; 
      } catch (MalformedURLException malformedURLException) {
        Utilities.escribeArchivo(TAG, "Error en URL", malformedURLException.getMessage());
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        Utilities.escribeArchivo(TAG, "Error en URL", unsupportedEncodingException.getMessage());
      }  
    return null;
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427405);
    try {
      HashMap<String, String> hashMap = (HashMap)getIntent().getExtras().getSerializable("url");
      String str = validateURL(hashMap);
      this.dtcWebView = (WebView)findViewById(2131297250);
      WebSettings webSettings = this.dtcWebView.getSettings();
      webSettings.setDomStorageEnabled(true);
      this.dtcWebView.getSettings().setJavaScriptEnabled(true);
      this.dtcWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
      webSettings.setAllowContentAccess(false);
      webSettings.setAllowFileAccess(false);
      webSettings.setAllowFileAccessFromFileURLs(false);
      webSettings.setAllowUniversalAccessFromFileURLs(false);
      this.dtcWebView.setWebViewClient(new WebViewClient() {
            final DTCWebViewActivity this$0;
            
            public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
              super.onPageStarted(param1WebView, param1String, param1Bitmap);
            }
            
            public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
              Boolean bool1;
              String[] arrayOfString = DTCWebViewActivity.this.getResources().getStringArray(2130837507);
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
      if (URLUtil.isValidUrl(str)) {
        String str1 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Url: web.loadUrl: ");
        stringBuilder.append(str);
        Utilities.escribeArchivo(str1, "Urls", stringBuilder.toString());
        this.dtcWebView.loadUrl(str);
      } 
      return;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error en URL", exception.getMessage());
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/pid/DTCWebViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */