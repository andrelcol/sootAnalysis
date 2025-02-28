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
import java.net.MalformedURLException;

public class CarInfoWebViewActivity extends Activity {
  public static final String TAG = CarInfoWebViewActivity.class.getSimpleName();
  
  private WebView carInfoWebView = null;
  
  private static String validateURL(String paramString) {
    if (paramString != null && !paramString.isEmpty() && paramString.equals(GlobalMembers.URL_PID_CarInfo))
      try {
        paramString = String.format(GlobalMembers.URL_PID_CarInfo, new Object[0]);
        boolean bool = URLUtil.isValidUrl(paramString);
        if (bool)
          return paramString; 
      } catch (MalformedURLException malformedURLException) {
        Utilities.escribeArchivo(TAG, "Error en URL", malformedURLException.getMessage());
      }  
    return null;
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427405);
    try {
      String str = getIntent().getExtras().getString("url");
      str = validateURL(str);
      this.carInfoWebView = (WebView)findViewById(2131297250);
      WebSettings webSettings = this.carInfoWebView.getSettings();
      webSettings.setDomStorageEnabled(true);
      this.carInfoWebView.getSettings().setJavaScriptEnabled(true);
      this.carInfoWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
      webSettings.setAllowContentAccess(false);
      webSettings.setAllowFileAccess(false);
      webSettings.setAllowFileAccessFromFileURLs(false);
      webSettings.setAllowUniversalAccessFromFileURLs(false);
      this.carInfoWebView.setWebViewClient(new WebViewClient() {
            final CarInfoWebViewActivity this$0;
            
            public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
              super.onPageStarted(param1WebView, param1String, param1Bitmap);
            }
            
            public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
              Boolean bool1;
              String[] arrayOfString = CarInfoWebViewActivity.this.getResources().getStringArray(2130837507);
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
        this.carInfoWebView.loadUrl(str);
      } 
      return;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error en URL", exception.getMessage());
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/pid/CarInfoWebViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */