package com.roadtrack.onstar.ui.my_plan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.PaymentCardResponse;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.tasks.ValidateUrlOnStarWebView;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.RenewalDialogManager;
import com.roadtrack.onstar.utils.Utilities;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

public class PaymentCardInfo extends Activity {
  public final String TAG = PaymentCardInfo.class.getSimpleName();
  
  boolean isPageFullyLoaded = false;
  
  private TextView lbl_apci_device_name;
  
  Context mContext;
  
  final Timer myTimer = new Timer();
  
  ProgressDialog progressDialog;
  
  private onstarApplication rtApp;
  
  private WebView wv_apci_pay;
  
  private void dismissDialog() {
    ProgressDialog progressDialog = this.progressDialog;
    if (progressDialog != null && progressDialog.isShowing())
      this.progressDialog.dismiss(); 
  }
  
  private void formattedFont() {
    Typeface typeface = onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisRegular);
    this.lbl_apci_device_name.setTypeface(typeface);
  }
  
  private void loadWebView(String paramString) {
    if (paramString != null && !paramString.isEmpty()) {
      this.myTimer.schedule(new loaderTask(), 30000L);
      WebSettings webSettings = this.wv_apci_pay.getSettings();
      webSettings.setJavaScriptEnabled(true);
      webSettings.setDomStorageEnabled(true);
      webSettings.setAllowContentAccess(false);
      webSettings.setAllowFileAccess(false);
      webSettings.setAllowFileAccessFromFileURLs(false);
      webSettings.setAllowUniversalAccessFromFileURLs(false);
      this.wv_apci_pay.setWebViewClient(new WebViewClient() {
            final PaymentCardInfo this$0;
            
            public void onLoadResource(WebView param1WebView, String param1String) {
              super.onLoadResource(param1WebView, param1String);
              if (PaymentCardInfo.this.wv_apci_pay.getProgress() == 100) {
                PaymentCardInfo.this.progressDialog.dismiss();
                PaymentCardInfo paymentCardInfo = PaymentCardInfo.this;
                paymentCardInfo.isPageFullyLoaded = true;
                paymentCardInfo.myTimer.cancel();
              } 
            }
            
            public void onPageFinished(WebView param1WebView, String param1String) {
              super.onPageFinished(param1WebView, param1String);
              Utilities.escribeArchivo(PaymentCardInfo.this.TAG, "OnPageFinished", "*************************************");
            }
            
            public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
              super.onPageStarted(param1WebView, param1String, param1Bitmap);
              Utilities.escribeArchivo(PaymentCardInfo.this.TAG, "onPageStarted", "*************************************");
            }
            
            public void onReceivedError(WebView param1WebView, WebResourceRequest param1WebResourceRequest, WebResourceError param1WebResourceError) {
              super.onReceivedError(param1WebView, param1WebResourceRequest, param1WebResourceError);
              Utilities.escribeArchivo(PaymentCardInfo.this.TAG, "onReceivedError", "*************************************");
              PaymentCardInfo.this.myTimer.cancel();
              PaymentCardInfo.this.onResponseError();
            }
            
            public WebResourceResponse shouldInterceptRequest(WebView param1WebView, WebResourceRequest param1WebResourceRequest) {
              Utilities.escribeArchivo(PaymentCardInfo.this.TAG, "shouldInterceptRequest", "*************************************");
              return super.shouldInterceptRequest(param1WebView, param1WebResourceRequest);
            }
            
            public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
              Boolean bool1;
              Utilities.escribeArchivo(PaymentCardInfo.this.TAG, "shouldOverrideUrlLoading", "*************************************");
              String[] arrayOfString = PaymentCardInfo.this.getResources().getStringArray(2130837507);
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
      if (!NetUtilities.validateNetwork((Context)this, false)) {
        onResponseError();
      } else {
        ValidateUrlOnStarWebView validateUrlOnStarWebView = new ValidateUrlOnStarWebView(this.mContext, paramString, this.wv_apci_pay);
        try {
          validateUrlOnStarWebView.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
        } catch (MalformedURLException malformedURLException) {
          Utilities.escribeArchivo(this.TAG, "Error en URL", malformedURLException.getMessage());
          this.myTimer.cancel();
          onResponseError();
        } 
      } 
    } else {
      onResponseError();
    } 
  }
  
  private void onResponseError() {
    dismissDialog();
    this.myTimer.cancel();
    RenewalDialogManager.getInstance().setDialog_type(2);
    RenewalDialogManager.getInstance().setContext((Context)this);
    RenewalDialogManager.getInstance().showDialog();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    setContentView(2131427374);
    this.lbl_apci_device_name = (TextView)findViewById(2131296723);
    this.wv_apci_pay = (WebView)findViewById(2131297259);
    this.rtApp = (onstarApplication)getApplicationContext();
    String str = Utilities.getLastKnownDeviceSelected((onstarApplication)getApplicationContext(), this.TAG).getName();
    this.lbl_apci_device_name.setText(str);
    formattedFont();
    this.mContext = (Context)this;
    MainActivity.onRenewalDialog = Boolean.valueOf(false);
    try {
      String str1;
      PaymentCardResponse paymentCardResponse = (PaymentCardResponse)getIntent().getExtras().getSerializable("resultObject");
      if (paymentCardResponse != null) {
        str1 = paymentCardResponse.getGcmures2();
      } else {
        str1 = "";
      } 
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      this();
      String str2 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.please_wait, 2131690270);
      ProgressDialog progressDialog = new ProgressDialog();
      this((Context)this, 2131755173);
      this.progressDialog = progressDialog;
      this.progressDialog.setIndeterminate(true);
      this.progressDialog.setCancelable(false);
      this.progressDialog.setMessage(str2);
      this.progressDialog.show();
      loadWebView(str1);
    } catch (Exception exception) {
      onResponseError();
    } 
  }
  
  class loaderTask extends TimerTask {
    final PaymentCardInfo this$0;
    
    public void run() {
      PaymentCardInfo paymentCardInfo = PaymentCardInfo.this;
      if (paymentCardInfo.isPageFullyLoaded) {
        paymentCardInfo.dismissDialog();
      } else {
        paymentCardInfo.dismissDialog();
        PaymentCardInfo.this.runOnUiThread(new Runnable() {
              final PaymentCardInfo.loaderTask this$1;
              
              public void run() {
                PaymentCardInfo.this.onResponseError();
              }
            });
      } 
      Utilities.escribeArchivo(PaymentCardInfo.this.TAG, "timer", "Timer-----------------------------");
    }
  }
  
  class null implements Runnable {
    final PaymentCardInfo.loaderTask this$1;
    
    public void run() {
      PaymentCardInfo.this.onResponseError();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/my_plan/PaymentCardInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */