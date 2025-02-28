package com.roadtrack.onstar.servicios;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import com.roadtrack.onstar.utils.PermissionsManagerMarshmallow;

@SuppressLint({"HandlerLeak"})
public class PermissionsChecker extends Service {
  Handler mHandler;
  
  private Runnable mRunnable = new Runnable() {
      final PermissionsChecker this$0;
      
      public void run() {
        PermissionsChecker.this.Notificar();
        PermissionsChecker permissionsChecker = PermissionsChecker.this;
        permissionsChecker.mHandler.postDelayed(permissionsChecker.mRunnable, 1000L);
      }
    };
  
  public void Notificar() {
    (new PermissionsManagerMarshmallow()).checkAllPermissions(getBaseContext(), 100, true);
  }
  
  public IBinder onBind(Intent paramIntent) {
    return null;
  }
  
  public void onCreate() {
    useHandler();
  }
  
  public void onDestroy() {
    super.onDestroy();
    this.mHandler.removeCallbacks(this.mRunnable);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    return 1;
  }
  
  public void useHandler() {
    this.mHandler = new Handler();
    this.mHandler.postDelayed(this.mRunnable, 1000L);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/servicios/PermissionsChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */