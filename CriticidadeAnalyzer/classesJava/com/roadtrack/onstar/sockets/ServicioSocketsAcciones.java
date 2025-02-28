package com.roadtrack.onstar.sockets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.utils.Utilities;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServicioSocketsAcciones extends Service {
  private static boolean bHiloEnEjecucion = false;
  
  private boolean bTiempoMenorA30Seg = true;
  
  private int iContador2 = 0;
  
  private Socket socket = null;
  
  private String strToken = "";
  
  private boolean ConectaSocket(String paramString) {
    boolean bool;
    try {
      Socket socket = new Socket();
      this();
      this.socket = socket;
      InetSocketAddress inetSocketAddress = new InetSocketAddress();
      this(GlobalMembers.strIPSocketGetCommand, Integer.parseInt(paramString));
      this.socket.bind(null);
      this.socket.setSoTimeout(GlobalMembers.SOCKET_READ_TIMEOUT);
      this.socket.connect(inetSocketAddress, GlobalMembers.SOCKET_TIMEOUT);
      bool = EscuchaRespuesta();
    } catch (UnknownHostException unknownHostException) {
      Utilities.escribeArchivo("ServicioSocketsAcciones", "Error: ConectaSocket.UnknownHostException", unknownHostException.getMessage());
      bool = false;
    } catch (IOException iOException) {
      Utilities.escribeArchivo("ServicioSocketsAcciones", "Error: ConectaSocket.IOException", iOException.getMessage());
    } 
    return bool;
  }
  
  private boolean EscuchaRespuesta() {
    boolean bool2;
    boolean bool1 = false;
    try {
      if (this.socket.isConnected()) {
        Thread thread = new Thread();
        TimerThread timerThread = new TimerThread();
        this(this);
        this(timerThread);
        thread.start();
        BufferedWriter bufferedWriter = new BufferedWriter();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter();
        this(this.socket.getOutputStream());
        this(outputStreamWriter);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("AC:");
        stringBuilder.append(this.strToken);
        String str = Utilities.Crypt(stringBuilder.toString());
        bufferedWriter.write(str);
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("SIZE: ");
        stringBuilder.append(str.length());
        Utilities.escribeArchivo("ServicioSocketsAcciones", "EscuchaRespuesta: DATA_REMOTE_SERVICES WRITE SOCKET", stringBuilder.toString());
        bufferedWriter.flush();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this(8192);
        byte[] arrayOfByte = new byte[8192];
        InputStream inputStream = this.socket.getInputStream();
        bool1 = false;
        while (true) {
          boolean bool = bool1;
          bool2 = bool1;
          try {
            if (this.bTiempoMenorA30Seg) {
              bool = bool1;
              bool2 = bool1;
              byteArrayOutputStream.write(arrayOfByte, 0, inputStream.read(arrayOfByte));
              bool = bool1;
              bool2 = bool1;
              byteArrayOutputStream.reset();
              bool = true;
              bool2 = true;
              bool1 = true;
              stopSelf();
              continue;
            } 
            // Byte code: goto -> 256
          } catch (UnknownHostException null) {
            break;
          } catch (IOException null) {
            // Byte code: goto -> 265
          } 
        } 
      } else {
        return bool1;
      } 
    } catch (UnknownHostException null) {
      bool2 = false;
    } catch (IOException iOException) {
      boolean bool = false;
      Utilities.escribeArchivo("ServicioSocketsAcciones", "Error: EscucharRespuesta.IOException", iOException.getMessage());
      this.iContador2 = 0;
      bool2 = bool;
    } 
    Utilities.escribeArchivo("ServicioSocketsAcciones", "Error: EscucharRespuesta.UnknownHostException", iOException.getMessage());
    this.iContador2 = 0;
    return bool2;
  }
  
  public IBinder onBind(Intent paramIntent) {
    return null;
  }
  
  public void onCreate() {
    super.onCreate();
    this.iContador2 = 0;
    this.strToken = GlobalMembers.strAccountID;
  }
  
  public void onDestroy() {
    super.onDestroy();
    this.iContador2 = 99;
    bHiloEnEjecucion = false;
    this.bTiempoMenorA30Seg = false;
    GlobalMembers.bServicioSocketsCorriendo = false;
    try {
      this.socket.close();
      this.socket = null;
    } catch (IOException iOException) {
      Utilities.escribeArchivo("ServicioSocketsAcciones", "Error: onDestroy", iOException.getMessage());
    } 
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    GlobalMembers.bServicioSocketsCorriendo = true;
    if (!bHiloEnEjecucion)
      (new Thread(new ClientThread())).start(); 
    return 2;
  }
  
  class ClientThread implements Runnable {
    final ServicioSocketsAcciones this$0;
    
    public void run() {
      ServicioSocketsAcciones.access$002(true);
      GlobalMembers.bRespuestaNoRecibida = false;
      boolean bool = false;
      int i = 0;
      while (!bool) {
        bool = ServicioSocketsAcciones.this.ConectaSocket(GlobalMembers.strPuertoA);
        if (bool)
          break; 
        try {
          Thread.sleep(1000L);
          int j = i + 1;
          i = j;
          if (j == 30)
            break; 
        } catch (InterruptedException interruptedException) {
          ServicioSocketsAcciones.access$002(false);
          Utilities.escribeArchivo("ServicioSocketsAcciones", "Error: SOCKETS: No fue posible dormir el hilo", interruptedException.getMessage());
        } 
      } 
      ServicioSocketsAcciones.access$002(false);
      ServicioSocketsAcciones.this.stopSelf();
    }
  }
  
  class TimerThread implements Runnable {
    final ServicioSocketsAcciones this$0;
    
    public void run() {
      ServicioSocketsAcciones.access$202(ServicioSocketsAcciones.this, true);
      while (ServicioSocketsAcciones.this.iContador2 < 30) {
        try {
          Thread.sleep(1000L);
          ServicioSocketsAcciones.access$308(ServicioSocketsAcciones.this);
        } catch (InterruptedException interruptedException) {
          Utilities.escribeArchivo("ServicioSocketsAcciones", "Error: TimerThread", interruptedException.getMessage());
        } 
      } 
      ServicioSocketsAcciones.access$202(ServicioSocketsAcciones.this, false);
      GlobalMembers.bRespuestaNoRecibida = true;
      ServicioSocketsAcciones.this.stopSelf();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/sockets/ServicioSocketsAcciones.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */