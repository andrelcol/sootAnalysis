package com.roadtrack.onstar.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.roadtrack.onstar.utils.SocketListener;
import com.roadtrack.onstar.utils.Utilities;

public class ServiceAlertsSocket extends Service {
  public IBinder onBind(Intent paramIntent) {
    return null;
  }
  
  public void onCreate() {
    (new Thread(this) {
        public void run() {
          try {
            SocketListener.StartSocketListener();
          } catch (Exception exception) {
            Utilities.escribeArchivo("Service1", "Error: onCreate", exception.getMessage());
          } 
        }
      }).start();
  }
  
  public void onDestroy() {
    SocketListener.SocketListenerCloses();
  }
  
  public void onStart(Intent paramIntent, int paramInt) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/servicios/ServiceAlertsSocket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */