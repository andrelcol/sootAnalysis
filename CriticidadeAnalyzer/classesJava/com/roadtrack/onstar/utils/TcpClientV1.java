package com.roadtrack.onstar.utils;

import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.MainActivity;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClientV1 {
  private static BufferedWriter bufferedWritter;
  
  private static String data;
  
  public static boolean firstInSocket = false;
  
  private static InputStream inputStream;
  
  private static boolean isConnected = false;
  
  private static boolean isException = false;
  
  public static boolean processSuccessful = false;
  
  private static Socket socket;
  
  private static final boolean connect() {
    Socket socket = socket;
    if (socket == null || !socket.isConnected()) {
      socket = new Socket();
      InetSocketAddress inetSocketAddress = new InetSocketAddress(GlobalMembers.strIPSocketGetCommand, 443);
      byte b = 0;
      while (b < 4) {
        try {
          socket.bind(null);
          socket.setSoTimeout(GlobalMembers.SOCKET_READ_TIMEOUT);
          socket.connect(inetSocketAddress, GlobalMembers.SOCKET_TIMEOUT);
          break;
        } catch (IOException iOException) {
          Utilities.escribeArchivo("TcpClientV1", "Error: connect", iOException.getMessage());
          long l = 5000L;
          try {
            Thread.sleep(l);
          } catch (InterruptedException interruptedException) {
            Utilities.escribeArchivo("TcpClientV1", "Error: connect", iOException.getMessage());
          } 
          try {
            if (bufferedWritter != null) {
              bufferedWritter.close();
              bufferedWritter = null;
            } 
          } catch (IOException iOException1) {
            Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException1.getMessage());
          } catch (Exception exception) {
            Utilities.escribeArchivo("TcpClientV1", "Error: disconnect", exception.getMessage());
          } 
          try {
            if (inputStream != null) {
              inputStream.close();
              inputStream = null;
            } 
          } catch (IOException iOException1) {
            Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException1.getMessage());
          } 
          try {
            if (socket != null) {
              socket.close();
              socket = null;
            } 
          } catch (IOException iOException1) {
            Utilities.escribeArchivo("TcpClientV1", "Error: disconnect", iOException1.getMessage());
          } 
          isConnected = false;
          b++;
        } 
      } 
    } 
    return socket.isConnected();
  }
  
  public static final void disconnect() {
    try {
      if (bufferedWritter != null) {
        bufferedWritter.close();
        bufferedWritter = null;
      } 
    } catch (IOException iOException) {
      Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException.getMessage());
    } catch (Exception exception) {
      Utilities.escribeArchivo("TcpClientV1", "Error: disconnect", exception.getMessage());
    } 
    try {
      if (inputStream != null) {
        inputStream.close();
        inputStream = null;
      } 
    } catch (IOException iOException) {
      Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException.getMessage());
    } 
    try {
      if (socket != null) {
        socket.close();
        socket = null;
      } 
    } catch (IOException iOException) {
      Utilities.escribeArchivo("TcpClientV1", "Error: disconnect", iOException.getMessage());
    } 
    isConnected = false;
  }
  
  public static String[] respuestas(String paramString) {
    paramString = paramString.replace(",", ";");
    return paramString.substring(3, paramString.length()).split(";");
  }
  
  private static void socket(Context paramContext, String paramString1, String paramString2, String paramString3) {
    firstInSocket = false;
    isException = false;
    try {
      if (connect()) {
        isConnected = true;
        BufferedWriter bufferedWriter = new BufferedWriter();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter();
        this(socket.getOutputStream());
        this(outputStreamWriter);
        bufferedWritter = bufferedWriter;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("AC:");
        stringBuilder.append(paramString1);
        String str = Utilities.Crypt(stringBuilder.toString());
        bufferedWritter.write(str);
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("SIZE: ");
        stringBuilder.append(str.length());
        Utilities.escribeArchivo("TcpClientV1", "socket: DATA_REMOTE_SERVICES WRITE SOCKET", stringBuilder.toString());
        bufferedWritter.flush();
        inputStream = socket.getInputStream();
        while (isConnected) {
          byte[] arrayOfByte = new byte[8192];
          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
          this(1);
          String str1 = new String();
          this();
          data = str1;
          byteArrayOutputStream.write(arrayOfByte, 0, inputStream.read(arrayOfByte));
          data = byteArrayOutputStream.toString("UTF-8");
          data = Utilities.Decrypt(data);
          if (data != null && data.length() > 0) {
            StringBuilder stringBuilder1;
            String[] arrayOfString = respuestas(data);
            if (arrayOfString.length > 0 && arrayOfString[0].equals("2002") && arrayOfString[1].equals(paramString3)) {
              if (arrayOfString[2].equals("2")) {
                String str3 = Utilities.getDeviceSerialNumberByDeviceId(paramString2);
                StringBuilder stringBuilder2 = new StringBuilder();
                this();
                stringBuilder2.append("ServiceMobileApp:3002,");
                stringBuilder2.append(str3);
                stringBuilder2.append(",");
                stringBuilder2.append(paramString1);
                stringBuilder2.append(",");
                stringBuilder2.append(arrayOfString[1]);
                String str2 = Utilities.Crypt(stringBuilder2.toString());
                bufferedWritter.write(str2);
                stringBuilder1 = new StringBuilder();
                this();
                stringBuilder1.append("SIZE: ");
                stringBuilder1.append(str2.length());
                Utilities.escribeArchivo("TcpClientV1", "socket: DATA_REMOTE_SERVICES WRITE SOCKET", stringBuilder1.toString());
                bufferedWritter.flush();
                continue;
              } 
              firstInSocket = true;
              processSuccessful = false;
              break;
            } 
            if ((stringBuilder1.length <= 0 || !stringBuilder1[0].equals("1005")) && stringBuilder1.length > 0 && stringBuilder1[1].equals(paramString1)) {
              firstInSocket = true;
              processSuccessful = true;
              break;
            } 
          } 
        } 
      } 
      try {
        if (bufferedWritter != null) {
          bufferedWritter.close();
          bufferedWritter = null;
        } 
      } catch (IOException iOException) {
        Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException.getMessage());
      } 
      try {
        if (inputStream != null) {
          inputStream.close();
          inputStream = null;
        } 
      } catch (IOException iOException) {
        Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException.getMessage());
      } 
      try {
        if (socket != null) {
          socket.close();
          socket = null;
        } 
      } catch (IOException iOException) {
        Utilities.escribeArchivo("TcpClientV1", "Error: disconnect", iOException.getMessage());
      } 
      isConnected = false;
    } catch (IOException iOException) {
      isException = true;
      Utilities.escribeArchivo("TcpClientV1", "Error: socket", iOException.getMessage());
      try {
        if (bufferedWritter != null) {
          bufferedWritter.close();
          bufferedWritter = null;
        } 
      } catch (IOException iOException1) {
        Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException1.getMessage());
      } 
      try {
        if (inputStream != null) {
          inputStream.close();
          inputStream = null;
        } 
      } catch (IOException iOException1) {
        Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException1.getMessage());
      } 
      try {
        if (socket != null) {
          socket.close();
          socket = null;
        } 
        isConnected = false;
      } catch (IOException iOException1) {
        Utilities.escribeArchivo("TcpClientV1", "Error: disconnect", iOException1.getMessage());
      } 
      return;
    } finally {}
    try {
      if (bufferedWritter != null) {
        bufferedWritter.close();
        bufferedWritter = null;
      } 
    } catch (IOException iOException) {
      Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException.getMessage());
    } 
    try {
      if (inputStream != null) {
        inputStream.close();
        inputStream = null;
      } 
    } catch (IOException iOException) {
      Utilities.escribeArchivo("TcpClientV1", "Error: disconnect.IOException", iOException.getMessage());
    } 
    try {
      if (socket != null) {
        socket.close();
        socket = null;
      } 
    } catch (IOException iOException) {
      Utilities.escribeArchivo("TcpClientV1", "Error: disconnect", iOException.getMessage());
    } 
    isConnected = false;
    throw paramContext;
  }
  
  public static class AsyncTaskTcpClientV1 extends AsyncTask<String, Void, Boolean> {
    private Context context;
    
    public AsyncTaskTcpClientV1(Context param1Context) {
      this.context = param1Context;
    }
    
    protected Boolean doInBackground(String... param1VarArgs) {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(AsyncTaskTcpClientV1.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      TcpClientV1.socket(this.context, param1VarArgs[0], param1VarArgs[1], param1VarArgs[2]);
      return Boolean.valueOf(TcpClientV1.processSuccessful);
    }
    
    protected void onPostExecute(Boolean param1Boolean) {
      super.onPostExecute(param1Boolean);
      if (!TcpClientV1.isException && !MainActivity.firstInWcf && MainActivity.listCmdStat.size() == 1)
        GlobalMembers.eventListenerActions.onEvent(); 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/TcpClientV1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */