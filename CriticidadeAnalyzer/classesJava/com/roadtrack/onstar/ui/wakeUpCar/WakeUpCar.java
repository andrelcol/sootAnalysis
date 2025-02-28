package com.roadtrack.onstar.ui.wakeUpCar;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.circularProgress.AnimDownloadProgressButton;
import com.roadtrack.onstar.progressAnimation.DotProgressBar;
import com.roadtrack.onstar.utils.Utilities;

public class WakeUpCar extends AppCompatActivity {
  private static boolean flagProgress = false;
  
  private static Handler handler;
  
  public static boolean isOnPause = false;
  
  private static Runnable runnable;
  
  private static StringsResourcesVO stringsResourcesVO;
  
  public static Activity wakeActivity;
  
  private double contCircular = 0.0D;
  
  private DotProgressBar dotProgressBar;
  
  private ImageView ivIconWake;
  
  private LinearLayout ll_container_head;
  
  RelativeLayout malla_gris;
  
  private AnimDownloadProgressButton progeress;
  
  private TextView tvProgress;
  
  private TextView tvTextWake;
  
  private TextView tvTextWakeReintentar;
  
  private void actualiceView() {
    (new Handler()).postDelayed(new Runnable() {
          final WakeUpCar this$0;
          
          public void run() {
            WakeUpCar.this.progeress.setVisibility(8);
            WakeUpCar.this.tvProgress.setVisibility(8);
          }
        },  2000L);
  }
  
  private void resetView() {
    if (flagProgress) {
      handler.removeCallbacks(runnable);
      if (Build.VERSION.SDK_INT >= 23) {
        this.tvProgress.setTextColor(wakeActivity.getResources().getColor(2131034285, wakeActivity.getTheme()));
      } else {
        this.tvProgress.setTextColor(wakeActivity.getResources().getColor(2131034285));
      } 
      this.tvProgress.setText("100%");
      this.progeress.setText("100%");
      this.progeress.setProgress(100.0F);
      this.contCircular = 1.0D;
    } else {
      this.dotProgressBar.setVisibility(8);
    } 
  }
  
  public void changeText(Drawable paramDrawable, String paramString) {
    this.ivIconWake.setImageDrawable(paramDrawable);
    this.tvTextWake.setText(paramString);
    String str = Utilities.getStringFromConfigList((Context)wakeActivity, stringsResourcesVO.wakeupcar_estatus_error_txt, 2131690497);
    this.tvTextWakeReintentar.setVisibility(0);
    this.tvTextWakeReintentar.setText(str);
    this.ivIconWake.setVisibility(0);
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
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427383);
    isOnPause = false;
    ((NotificationManager)getApplicationContext().getSystemService("notification")).cancelAll();
    GlobalMembers.isWUCRunningAffterSetPlanes = true;
    getWindow().addFlags(128);
    GlobalMembers.wakeUpCar = true;
    stringsResourcesVO = new StringsResourcesVO();
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_login_icon_static, 2131165486);
    this.ivIconWake = (ImageView)findViewById(2131296672);
    this.ivIconWake.setImageDrawable(drawable);
    this.dotProgressBar = (DotProgressBar)findViewById(2131296502);
    String str = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.wakeupcar_estatus, 2131690494);
    this.tvTextWake = (TextView)findViewById(2131297167);
    this.tvTextWake.setText(str);
    this.tvTextWakeReintentar = (TextView)findViewById(2131297161);
    this.progeress = (AnimDownloadProgressButton)findViewById(2131296965);
    this.tvProgress = (TextView)findViewById(2131297164);
    if (flagProgress) {
      this.progeress.setState(1);
      handler = new Handler();
      runnable = new Runnable() {
          final WakeUpCar this$0;
          
          public void run() {
            double d = WakeUpCar.this.contCircular * 0.8D;
            if (d <= 98.0D) {
              if (d >= 48.0D) {
                if (Build.VERSION.SDK_INT >= 23) {
                  WakeUpCar.this.tvProgress.setTextColor(WakeUpCar.this.getResources().getColor(2131034285, WakeUpCar.this.getTheme()));
                } else {
                  WakeUpCar.this.tvProgress.setTextColor(WakeUpCar.this.getResources().getColor(2131034285));
                } 
              } else if (Build.VERSION.SDK_INT >= 23) {
                WakeUpCar.this.tvProgress.setTextColor(WakeUpCar.this.getResources().getColor(2131034170, WakeUpCar.this.getTheme()));
              } else {
                WakeUpCar.this.tvProgress.setTextColor(WakeUpCar.this.getResources().getColor(2131034170));
              } 
              TextView textView = WakeUpCar.this.tvProgress;
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append((int)d);
              stringBuilder.append("%");
              textView.setText(stringBuilder.toString());
              WakeUpCar.this.progeress.setText("");
              WakeUpCar.this.progeress.setProgress((float)d);
              WakeUpCar.access$008(WakeUpCar.this);
            } 
            WakeUpCar.handler.postDelayed(this, 1200L);
          }
        };
      handler.post(runnable);
    } else {
      this.dotProgressBar.setVisibility(0);
      this.progeress.setVisibility(8);
      this.tvProgress.setVisibility(8);
    } 
    this.malla_gris = (RelativeLayout)findViewById(2131296879);
    this.malla_gris.setOnClickListener(new View.OnClickListener() {
          final WakeUpCar this$0;
          
          public void onClick(View param1View) {
            if (!GlobalMembers.wakeUpCar)
              (new Thread(new Runnable() {
                    final WakeUpCar.null this$1;
                    
                    public void run() {
                      try {
                        Thread.sleep(1000L);
                      } catch (InterruptedException interruptedException) {
                        interruptedException.toString();
                      } 
                      WakeUpCar.this.runOnUiThread(new Runnable() {
                            final WakeUpCar.null.null this$2;
                            
                            public void run() {
                              WakeUpCar.this.finish();
                            }
                          });
                    }
                  })).start(); 
          }
        });
    this.ll_container_head = (LinearLayout)findViewById(2131296847);
    this.ll_container_head.setOnClickListener(new View.OnClickListener(this) {
          public void onClick(View param1View) {
            GlobalMembers.tempSocketResponse = false;
          }
        });
    ImageView imageView = (ImageView)findViewById(2131296674);
    wakeActivity = (Activity)this;
  }
  
  protected void onPause() {
    super.onPause();
    isOnPause = true;
  }
  
  public void setStatus(String paramString) {
    GlobalMembers.wakeUpCar = false;
    if (paramString.equalsIgnoreCase("finish")) {
      if (flagProgress) {
        resetView();
        (new Handler()).postDelayed(new Runnable(this) {
              public void run() {
                WakeUpCar.wakeActivity.finish();
              }
            },  3000L);
      } else {
        resetView();
        wakeActivity.finish();
      } 
    } else if (paramString.equals("3")) {
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)wakeActivity, DrawableResourcesVO.falla_wakeup, 2131165411);
      paramString = Utilities.getStringFromConfigList((Context)wakeActivity, stringsResourcesVO.wakeupcar_estatus_error_header, 2131690496);
      if (flagProgress)
        actualiceView(); 
      changeText(drawable, paramString);
      resetView();
    } else {
      Drawable drawable = Utilities.getDrawableFromConfigList((Context)wakeActivity, DrawableResourcesVO.ic_timeout_wakeup, 2131165544);
      paramString = Utilities.getStringFromConfigList((Context)wakeActivity, stringsResourcesVO.wizard_dicas_lbl_aguardenotificacion_9, 2131690511);
      if (flagProgress)
        actualiceView(); 
      changeText(drawable, paramString);
      resetView();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/wakeUpCar/WakeUpCar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */