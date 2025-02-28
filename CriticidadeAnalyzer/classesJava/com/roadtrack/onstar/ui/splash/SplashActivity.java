package com.roadtrack.onstar.ui.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.roadtrack.onstar.ui.login.LoginActivity;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if ((getIntent().getFlags() & 0x400000) != 0) {
      finish();
      return;
    } 
    getWindow().setFlags(1024, 1024);
    requestWindowFeature(1);
    setContentView(2131427381);
    TimerTask timerTask = new TimerTask() {
        final SplashActivity this$0;
        
        public void run() {
          Thread thread = Thread.currentThread();
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(null.class.getSimpleName());
          stringBuilder.append(": ");
          stringBuilder.append(Thread.currentThread().getName());
          thread.setName(stringBuilder.toString());
          Intent intent = (new Intent()).setClass((Context)SplashActivity.this, LoginActivity.class);
          SplashActivity.this.startActivity(intent);
          SplashActivity.this.finish();
        }
      };
    (new Timer()).schedule(timerTask, 1000L);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/splash/SplashActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */