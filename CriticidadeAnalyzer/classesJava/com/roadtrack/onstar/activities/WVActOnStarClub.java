package com.roadtrack.onstar.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.roadtrack.onstar.utils.Utilities;

public class WVActOnStarClub extends Activity {
  private static final String TAG = WVActOnStarClub.class.getName();
  
  private ImageView arrow_left_onStart;
  
  private Bundle extras;
  
  private WebView web;
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427384);
    this.extras = getIntent().getExtras();
    this.web = (WebView)findViewById(2131297261);
    this.arrow_left_onStart = (ImageView)findViewById(2131296378);
    this.arrow_left_onStart.setVisibility(0);
    this.arrow_left_onStart.setOnClickListener(new View.OnClickListener() {
          final WVActOnStarClub this$0;
          
          public void onClick(View param1View) {
            WVActOnStarClub.this.finish();
          }
        });
    this.web.setWebViewClient(new myWebClient(this));
    this.web.getSettings().setJavaScriptEnabled(true);
    if (this.extras.getString("URL") != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("URL ONSTAR CLUB:");
      stringBuilder1.append(this.extras.getString("URL"));
      Utilities.escribeArchivo("URL ONSTAR CLUB", "URL ONSTAR CLUB", stringBuilder1.toString());
      this.web.getSettings().setJavaScriptEnabled(true);
      this.web.getSettings().setDomStorageEnabled(true);
      this.web.getSettings().setAllowContentAccess(false);
      this.web.getSettings().setAllowFileAccess(false);
      this.web.getSettings().setAllowFileAccessFromFileURLs(false);
      this.web.getSettings().setAllowUniversalAccessFromFileURLs(false);
      String str = TAG;
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("Url: web.loadUrl: ");
      stringBuilder2.append(this.extras.getString("URL"));
      Utilities.escribeArchivo(str, "Urls", stringBuilder2.toString());
      this.web.loadUrl(this.extras.getString("URL"));
    } else {
      finish();
    } 
  }
  
  public class myWebClient extends WebViewClient {
    public myWebClient(WVActOnStarClub this$0) {}
    
    public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
      super.onPageStarted(param1WebView, param1String, param1Bitmap);
    }
    
    public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
      String str = WVActOnStarClub.TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Url: web.loadUrl: ");
      stringBuilder.append(param1String);
      Utilities.escribeArchivo(str, "Urls", stringBuilder.toString());
      param1WebView.loadUrl(param1String);
      return true;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/activities/WVActOnStarClub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */