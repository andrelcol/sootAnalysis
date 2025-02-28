package com.roadtrack.onstar.ui.my_plan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.SuscriptionResponse;
import com.roadtrack.onstar.async_tasks.TaskWb;
import com.roadtrack.onstar.gson.GsonC;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.RenewalDialogManager;
import com.roadtrack.onstar.utils.Utilities;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

public class PaymentActivity extends Activity {
  public final String TAG = PaymentActivity.class.getSimpleName();
  
  private boolean blockBackPressed = false;
  
  private Button btnOk;
  
  private Button btn_ap_close;
  
  private Boolean isKnown;
  
  private boolean isPageFullyLoaded = false;
  
  private String[] knownDomains;
  
  private TextView lbl_ap_device_name;
  
  final Timer myTimer = new Timer();
  
  private ProgressDialog progressDialog;
  
  private onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private TaskWb taskWb;
  
  private Typeface tfLouis;
  
  private WebView wv_ap_pay;
  
  private SuscriptionResponse decryptResponse(SuscriptionResponse paramSuscriptionResponse) {
    paramSuscriptionResponse.setSubscres1(Utilities.DecryptMoip(paramSuscriptionResponse.getSubscres1()));
    paramSuscriptionResponse.setSubscres2(paramSuscriptionResponse.getSubscres2());
    paramSuscriptionResponse.setSubscres3(Utilities.DecryptMoip(paramSuscriptionResponse.getSubscres3()));
    return paramSuscriptionResponse;
  }
  
  private void dismissDialog() {
    if (this.progressDialog.isShowing())
      this.progressDialog.dismiss(); 
  }
  
  private void formattedFont() {
    this.rtApp = (onstarApplication)getApplicationContext();
    this.tfLouis = onstarApplication.getTypeface((Context)this, this.rtApp.fontPathLouisRegular);
    this.lbl_ap_device_name.setTypeface(this.tfLouis);
    this.btn_ap_close.setTypeface(this.tfLouis);
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void loadWebView(String paramString) {
    String str = validateURL(paramString);
    if (str != null && !str.isEmpty()) {
      this.myTimer.schedule(new loaderTask(), 30000L);
      this.wv_ap_pay.clearCache(true);
      this.wv_ap_pay.getSettings().setJavaScriptEnabled(true);
      this.wv_ap_pay.addJavascriptInterface(new LoadListener(), "HTMLOUT");
      this.wv_ap_pay.getSettings().setAllowContentAccess(false);
      this.wv_ap_pay.getSettings().setAllowFileAccess(false);
      this.wv_ap_pay.getSettings().setAllowFileAccessFromFileURLs(false);
      this.wv_ap_pay.getSettings().setAllowUniversalAccessFromFileURLs(false);
      this.wv_ap_pay.getSettings().setCacheMode(1);
      this.wv_ap_pay.setWebViewClient(new WebViewClient() {
            final PaymentActivity this$0;
            
            public void onLoadResource(WebView param1WebView, String param1String) {
              super.onLoadResource(param1WebView, param1String);
              if (PaymentActivity.this.wv_ap_pay.getProgress() == 100) {
                PaymentActivity.this.progressDialog.dismiss();
                PaymentActivity.access$302(PaymentActivity.this, true);
                PaymentActivity.this.myTimer.cancel();
              } 
            }
            
            public void onPageFinished(WebView param1WebView, String param1String) {
              super.onPageFinished(param1WebView, param1String);
              Utilities.escribeArchivo(PaymentActivity.this.TAG, "OnPageFinished", "*************************************");
            }
            
            public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
              super.onPageStarted(param1WebView, param1String, param1Bitmap);
              Utilities.escribeArchivo(PaymentActivity.this.TAG, "onPageStarted", "*************************************");
            }
            
            public void onReceivedError(WebView param1WebView, WebResourceRequest param1WebResourceRequest, WebResourceError param1WebResourceError) {
              super.onReceivedError(param1WebView, param1WebResourceRequest, param1WebResourceError);
              Utilities.escribeArchivo(PaymentActivity.this.TAG, "onReceivedError", "*************************************");
              PaymentActivity.this.myTimer.cancel();
              PaymentActivity.this.onResponseError();
            }
            
            public WebResourceResponse shouldInterceptRequest(final WebView wv_aux, WebResourceRequest param1WebResourceRequest) {
              Utilities.escribeArchivo(PaymentActivity.this.TAG, "shouldInterceptRequest", "*************************************");
              (new Handler(PaymentActivity.this.getMainLooper())).post(new Runnable(this) {
                    final WebView val$wv_aux;
                    
                    public void run() {
                      wv_aux.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                    }
                  });
              return super.shouldInterceptRequest(wv_aux, param1WebResourceRequest);
            }
            
            public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
              Boolean bool1;
              Utilities.escribeArchivo(PaymentActivity.this.TAG, "shouldOverrideUrlLoading", "*************************************");
              String[] arrayOfString = PaymentActivity.this.getResources().getStringArray(2130837507);
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
      } else if (URLUtil.isValidUrl(str)) {
        this.isKnown = Boolean.valueOf(false);
        String[] arrayOfString = this.knownDomains;
        int i = arrayOfString.length;
        for (byte b = 0; b < i; b++) {
          if (str.contains(Utilities.Decrypt(arrayOfString[b]))) {
            this.isKnown = Boolean.valueOf(true);
            break;
          } 
        } 
        if (this.isKnown.booleanValue()) {
          this.taskWb = new TaskWb(this.wv_ap_pay, str);
          this.taskWb.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
        } else {
          Utilities.escribeArchivo(this.TAG, "Error en URL", "URL No Valida");
          this.myTimer.cancel();
          onResponseError();
        } 
      } else {
        Utilities.escribeArchivo(this.TAG, "Error en URL", "Exception");
        this.myTimer.cancel();
        onResponseError();
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
  
  private void parseResponse(String paramString1, String paramString2) {
    if (paramString1 != null && !paramString1.isEmpty()) {
      paramString1 = paramString1.replace("\\\"", "\"");
      paramString1 = paramString1.substring(1, paramString1.length() - 1);
      GsonC gsonC = new GsonC();
      SuscriptionResponse suscriptionResponse = new SuscriptionResponse();
      gsonC.toListObject(paramString1, suscriptionResponse);
      decryptResponse(suscriptionResponse);
      if (suscriptionResponse.isValidObject()) {
        loadWebView(suscriptionResponse.getSubscres2());
      } else {
        onResponseError();
      } 
    } 
  }
  
  private void showEndTransactionDialog() {
    Dialog dialog = Utilities.simpleDialog((Context)this, null, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955), Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.renovacion_popup_lbl_finalizandoproceso, 2131690333), true, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946), false, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949));
    dialog.setCancelable(false);
    this.btnOk = (Button)dialog.findViewById(2131296343);
    this.btnOk.setOnClickListener(new View.OnClickListener() {
          final PaymentActivity this$0;
          
          public void onClick(View param1View) {
            PaymentActivity.this.dismissDialog();
            Intent intent = new Intent((Context)PaymentActivity.this, MainActivity.class);
            MainActivity.Showbanner = true;
            PaymentActivity.this.startActivity(intent);
          }
        });
    dialog.show();
  }
  
  private void showFinalizeButton() {
    this.blockBackPressed = true;
    runOnUiThread(new Runnable() {
          final PaymentActivity this$0;
          
          public void run() {
            Animation animation = AnimationUtils.loadAnimation(PaymentActivity.this.getApplicationContext(), 2130771986);
            PaymentActivity.this.btn_ap_close.startAnimation(animation);
            PaymentActivity.this.btn_ap_close.setVisibility(0);
          }
        });
  }
  
  private static String validateURL(String paramString) {
    if (paramString != null && !paramString.isEmpty())
      try {
        boolean bool = URLUtil.isValidUrl(paramString);
        if (bool)
          return paramString; 
      } catch (MalformedURLException malformedURLException) {
        Utilities.escribeArchivo("PaymentActivity", "Error en URL", malformedURLException.getMessage());
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
  
  public void onBackPressed() {
    if (!this.blockBackPressed)
      super.onBackPressed(); 
  }
  
  @SuppressLint({"JavascriptInterface"})
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    this.knownDomains = getResources().getStringArray(2130837507);
    setContentView(2131427373);
    this.stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.dtc_popup_lbl_cliqueparafinalizar, 2131689769);
    this.lbl_ap_device_name = (TextView)findViewById(2131296722);
    this.wv_ap_pay = (WebView)findViewById(2131297258);
    this.btn_ap_close = (Button)findViewById(2131296413);
    this.btn_ap_close.setText(str);
    str = Utilities.getLastKnownDeviceSelected((onstarApplication)getApplicationContext(), this.TAG).getName();
    this.lbl_ap_device_name.setText(str);
    formattedFont();
    this.btn_ap_close.setOnClickListener(new View.OnClickListener() {
          final PaymentActivity this$0;
          
          public void onClick(View param1View) {
            PaymentActivity.this.showEndTransactionDialog();
          }
        });
    try {
      Bundle bundle = getIntent().getExtras();
      str = bundle.getString("service_response");
      String str2 = bundle.getString("renewalKind");
      String str1 = Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.please_wait, 2131690270);
      ProgressDialog progressDialog = new ProgressDialog();
      this((Context)this, 2131755173);
      this.progressDialog = progressDialog;
      this.progressDialog.setIndeterminate(true);
      this.progressDialog.setCancelable(false);
      this.progressDialog.setMessage(str1);
      this.progressDialog.show();
      parseResponse(str, str2);
    } catch (Exception exception) {
      onResponseError();
    } 
  }
  
  class LoadListener {
    final PaymentActivity this$0;
    
    @JavascriptInterface
    public void processHTML(String param1String) {
      Utilities.escribeArchivo(PaymentActivity.this.TAG, "processHTML", "Entrando a método");
      if (param1String.contains("seu pedido foi recebido!")) {
        Utilities.escribeArchivo(PaymentActivity.this.TAG, "result éxito", param1String);
        PaymentActivity.this.showFinalizeButton();
      } 
    }
  }
  
  class loaderTask extends TimerTask {
    final PaymentActivity this$0;
    
    public void run() {
      if (PaymentActivity.this.isPageFullyLoaded) {
        PaymentActivity.this.dismissDialog();
      } else {
        PaymentActivity.this.dismissDialog();
        PaymentActivity.this.runOnUiThread(new Runnable() {
              final PaymentActivity.loaderTask this$1;
              
              public void run() {
                PaymentActivity.this.onResponseError();
              }
            });
      } 
      Utilities.escribeArchivo(PaymentActivity.this.TAG, "timer", "Timer-----------------------------");
    }
  }
  
  class null implements Runnable {
    final PaymentActivity.loaderTask this$1;
    
    public void run() {
      PaymentActivity.this.onResponseError();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/my_plan/PaymentActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */