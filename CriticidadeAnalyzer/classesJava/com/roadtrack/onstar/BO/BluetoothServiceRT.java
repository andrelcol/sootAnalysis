package com.roadtrack.onstar.BO;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.Utilities;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class BluetoothServiceRT {
  private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
  
  private static Handler mHandler;
  
  private static int mState;
  
  public static Context mcontext;
  
  public static int retries = 4;
  
  private final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
  
  private ConnectThread mConnectThread;
  
  private ConnectedThread mConnectedThread;
  
  private StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
  
  public BluetoothServiceRT(Context paramContext, Handler paramHandler) {
    mState = 0;
    mHandler = paramHandler;
    mcontext = paramContext;
  }
  
  private void connectionFailed() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mHandler : Landroid/os/Handler;
    //   5: iconst_5
    //   6: invokevirtual obtainMessage : (I)Landroid/os/Message;
    //   9: astore_3
    //   10: new android/os/Bundle
    //   13: astore_2
    //   14: aload_2
    //   15: invokespecial <init> : ()V
    //   18: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.retries : I
    //   21: istore_1
    //   22: iload_1
    //   23: ifeq -> 68
    //   26: iload_1
    //   27: iconst_1
    //   28: if_icmpeq -> 60
    //   31: iload_1
    //   32: iconst_2
    //   33: if_icmpeq -> 52
    //   36: iload_1
    //   37: iconst_3
    //   38: if_icmpeq -> 44
    //   41: goto -> 103
    //   44: aload_0
    //   45: iconst_2
    //   46: invokespecial setState : (I)V
    //   49: goto -> 103
    //   52: aload_0
    //   53: iconst_2
    //   54: invokespecial setState : (I)V
    //   57: goto -> 103
    //   60: aload_0
    //   61: iconst_2
    //   62: invokespecial setState : (I)V
    //   65: goto -> 103
    //   68: aload_0
    //   69: iconst_1
    //   70: invokespecial setState : (I)V
    //   73: aload_2
    //   74: ldc 'toast'
    //   76: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mcontext : Landroid/content/Context;
    //   79: aload_0
    //   80: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   83: getfield not_connected : Ljava/lang/String;
    //   86: ldc 2131690206
    //   88: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   91: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   94: aload_3
    //   95: aload_2
    //   96: invokevirtual setData : (Landroid/os/Bundle;)V
    //   99: iconst_4
    //   100: putstatic com/roadtrack/onstar/BO/BluetoothServiceRT.retries : I
    //   103: aload_0
    //   104: monitorexit
    //   105: return
    //   106: astore_2
    //   107: aload_0
    //   108: monitorexit
    //   109: aload_2
    //   110: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	106	finally
    //   44	49	106	finally
    //   52	57	106	finally
    //   60	65	106	finally
    //   68	103	106	finally
  }
  
  private void setState(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new java/lang/String
    //   5: astore_2
    //   6: aload_2
    //   7: invokespecial <init> : ()V
    //   10: iload_1
    //   11: ifeq -> 81
    //   14: iload_1
    //   15: iconst_1
    //   16: if_icmpeq -> 75
    //   19: iload_1
    //   20: iconst_2
    //   21: if_icmpeq -> 69
    //   24: iload_1
    //   25: iconst_3
    //   26: if_icmpeq -> 32
    //   29: goto -> 84
    //   32: ldc 'CONNECTED'
    //   34: astore_3
    //   35: aload_3
    //   36: astore_2
    //   37: getstatic com/roadtrack/onstar/BO/GlobalMembers.isHMIEnabled : Z
    //   40: ifeq -> 84
    //   43: invokestatic getmNPlatinum : ()Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum;
    //   46: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mcontext : Landroid/content/Context;
    //   49: aload_0
    //   50: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   53: getfield conectedtohmi : Ljava/lang/String;
    //   56: ldc 2131689718
    //   58: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   61: invokevirtual sendTextForTTS : (Ljava/lang/String;)V
    //   64: aload_3
    //   65: astore_2
    //   66: goto -> 84
    //   69: ldc 'CONNECTING'
    //   71: astore_2
    //   72: goto -> 84
    //   75: ldc 'LISTEN'
    //   77: astore_2
    //   78: goto -> 84
    //   81: ldc 'NONE'
    //   83: astore_2
    //   84: new java/lang/StringBuilder
    //   87: astore_3
    //   88: aload_3
    //   89: invokespecial <init> : ()V
    //   92: aload_3
    //   93: ldc 'BLUETOOTH > setState() > Bluetooth State: '
    //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: aload_3
    //   100: aload_2
    //   101: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: ldc 'BluetoothServiceRT'
    //   107: ldc 'BluetoothServiceRT'
    //   109: aload_3
    //   110: invokevirtual toString : ()Ljava/lang/String;
    //   113: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   116: iload_1
    //   117: putstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mState : I
    //   120: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mHandler : Landroid/os/Handler;
    //   123: iconst_1
    //   124: iload_1
    //   125: iconst_m1
    //   126: invokevirtual obtainMessage : (III)Landroid/os/Message;
    //   129: invokevirtual sendToTarget : ()V
    //   132: aload_0
    //   133: monitorexit
    //   134: return
    //   135: astore_2
    //   136: aload_0
    //   137: monitorexit
    //   138: aload_2
    //   139: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	135	finally
    //   37	64	135	finally
    //   84	132	135	finally
  }
  
  public void connect(BluetoothDevice paramBluetoothDevice) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mState : I
    //   5: iconst_2
    //   6: if_icmpne -> 28
    //   9: aload_0
    //   10: getfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   13: ifnull -> 28
    //   16: aload_0
    //   17: getfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   20: invokevirtual Cancel : ()V
    //   23: aload_0
    //   24: aconst_null
    //   25: putfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   28: aload_0
    //   29: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   32: ifnull -> 47
    //   35: aload_0
    //   36: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   39: invokevirtual Cancel : ()V
    //   42: aload_0
    //   43: aconst_null
    //   44: putfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   47: new com/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread
    //   50: astore_2
    //   51: aload_2
    //   52: aload_0
    //   53: aload_1
    //   54: invokespecial <init> : (Lcom/roadtrack/onstar/BO/BluetoothServiceRT;Landroid/bluetooth/BluetoothDevice;)V
    //   57: aload_0
    //   58: aload_2
    //   59: putfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   62: aload_0
    //   63: getfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   66: invokevirtual start : ()V
    //   69: aload_0
    //   70: iconst_2
    //   71: invokespecial setState : (I)V
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: astore_1
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_1
    //   81: athrow
    // Exception table:
    //   from	to	target	type
    //   2	28	77	finally
    //   28	47	77	finally
    //   47	74	77	finally
  }
  
  public void connected(BluetoothSocket paramBluetoothSocket, BluetoothDevice paramBluetoothDevice) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   6: ifnull -> 21
    //   9: aload_0
    //   10: getfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   13: invokevirtual Cancel : ()V
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   21: aload_0
    //   22: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   25: ifnull -> 40
    //   28: aload_0
    //   29: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   32: invokevirtual Cancel : ()V
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   40: new com/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread
    //   43: astore_3
    //   44: aload_3
    //   45: aload_0
    //   46: aload_1
    //   47: invokespecial <init> : (Lcom/roadtrack/onstar/BO/BluetoothServiceRT;Landroid/bluetooth/BluetoothSocket;)V
    //   50: aload_0
    //   51: aload_3
    //   52: putfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   55: aload_0
    //   56: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   59: invokevirtual start : ()V
    //   62: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mHandler : Landroid/os/Handler;
    //   65: iconst_4
    //   66: invokevirtual obtainMessage : (I)Landroid/os/Message;
    //   69: astore_3
    //   70: new android/os/Bundle
    //   73: astore_1
    //   74: aload_1
    //   75: invokespecial <init> : ()V
    //   78: aload_1
    //   79: ldc 'device_name'
    //   81: aload_2
    //   82: invokevirtual getName : ()Ljava/lang/String;
    //   85: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   88: aload_3
    //   89: aload_1
    //   90: invokevirtual setData : (Landroid/os/Bundle;)V
    //   93: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mHandler : Landroid/os/Handler;
    //   96: aload_3
    //   97: invokevirtual sendMessage : (Landroid/os/Message;)Z
    //   100: pop
    //   101: aload_0
    //   102: iconst_3
    //   103: invokespecial setState : (I)V
    //   106: aload_0
    //   107: monitorexit
    //   108: return
    //   109: astore_1
    //   110: aload_0
    //   111: monitorexit
    //   112: aload_1
    //   113: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	109	finally
    //   21	40	109	finally
    //   40	106	109	finally
  }
  
  public void connectionLost() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: invokespecial setState : (I)V
    //   7: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mHandler : Landroid/os/Handler;
    //   10: iconst_5
    //   11: invokevirtual obtainMessage : (I)Landroid/os/Message;
    //   14: astore_1
    //   15: new android/os/Bundle
    //   18: astore_2
    //   19: aload_2
    //   20: invokespecial <init> : ()V
    //   23: aload_2
    //   24: getstatic com/roadtrack/onstar/Enums$messageBT.MESSAGE_TOAST : Lcom/roadtrack/onstar/Enums$messageBT;
    //   27: invokevirtual toString : ()Ljava/lang/String;
    //   30: ldc 'Se perdió la conexi�n con el P8, intentanto recuperar la conexi�n.'
    //   32: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   35: aload_1
    //   36: aload_2
    //   37: invokevirtual setData : (Landroid/os/Bundle;)V
    //   40: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mHandler : Landroid/os/Handler;
    //   43: aload_1
    //   44: invokevirtual sendMessage : (Landroid/os/Message;)Z
    //   47: pop
    //   48: aload_0
    //   49: monitorexit
    //   50: return
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    // Exception table:
    //   from	to	target	type
    //   2	48	51	finally
  }
  
  public int getState() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mState : I
    //   5: istore_1
    //   6: aload_0
    //   7: monitorexit
    //   8: iload_1
    //   9: ireturn
    //   10: astore_2
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_2
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	6	10	finally
  }
  
  public void setHandler(Handler paramHandler) {
    synchronized (mHandler) {
      mHandler = paramHandler;
      return;
    } 
  }
  
  public void start() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   6: ifnull -> 21
    //   9: aload_0
    //   10: getfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   13: invokevirtual Cancel : ()V
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   21: aload_0
    //   22: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   25: ifnull -> 40
    //   28: aload_0
    //   29: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   32: invokevirtual Cancel : ()V
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   40: aload_0
    //   41: iconst_1
    //   42: invokespecial setState : (I)V
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	48	finally
    //   21	40	48	finally
    //   40	45	48	finally
  }
  
  public void stop() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   6: ifnull -> 21
    //   9: aload_0
    //   10: getfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   13: invokevirtual Cancel : ()V
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield mConnectThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectThread;
    //   21: aload_0
    //   22: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   25: ifnull -> 40
    //   28: aload_0
    //   29: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   32: invokevirtual Cancel : ()V
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   40: aload_0
    //   41: iconst_0
    //   42: invokespecial setState : (I)V
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	48	finally
    //   21	40	48	finally
    //   40	45	48	finally
  }
  
  public void write(byte[] paramArrayOfbyte) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: monitorenter
    //   4: getstatic com/roadtrack/onstar/BO/BluetoothServiceRT.mState : I
    //   7: iconst_3
    //   8: if_icmpeq -> 16
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: aload_0
    //   17: getfield mConnectedThread : Lcom/roadtrack/onstar/BO/BluetoothServiceRT$ConnectedThread;
    //   20: astore_2
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_2
    //   24: aload_1
    //   25: invokevirtual Write : ([B)V
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   2	4	36	finally
    //   4	13	31	finally
    //   16	23	31	finally
    //   23	28	36	finally
    //   32	34	31	finally
    //   34	36	36	finally
  }
  
  private class ConnectThread extends Thread {
    private final BluetoothDevice mmDevice;
    
    private final BluetoothSocket mmSocket;
    
    final BluetoothServiceRT this$0;
    
    public ConnectThread(BluetoothDevice param1BluetoothDevice) {
      this.mmDevice = param1BluetoothDevice;
      try {
        BluetoothSocket bluetoothSocket = param1BluetoothDevice.createRfcommSocketToServiceRecord(BluetoothServiceRT.MY_UUID);
      } catch (IOException iOException) {
        Utilities.escribeArchivo("BluetoothServiceRT", "Error: ConnectThread()", iOException.getMessage());
        iOException = null;
      } 
      this.mmSocket = (BluetoothSocket)iOException;
    }
    
    public void Cancel() {
      try {
        this.mmSocket.close();
      } catch (IOException iOException) {
        Utilities.escribeArchivo("BluetoothServiceRT", "Error: cancel", iOException.getMessage());
      } 
    }
    
    public void run() {
      setName("ConnectThread");
      BluetoothServiceRT.this.mBluetoothAdapter.cancelDiscovery();
      if (BluetoothServiceRT.retries != 4)
        try {
          Thread.sleep(10000L);
        } catch (InterruptedException interruptedException) {
          Utilities.escribeArchivo("BluetoothServiceRT", "Error: run", interruptedException.getMessage());
        }  
      try {
        this.mmSocket.connect();
        BluetoothServiceRT.retries = 4;
        synchronized (BluetoothServiceRT.this) {
          BluetoothServiceRT.access$302(BluetoothServiceRT.this, null);
          BluetoothServiceRT.this.connected(this.mmSocket, this.mmDevice);
          return;
        } 
      } catch (IOException iOException) {
        BluetoothServiceRT.retries--;
        Utilities.escribeArchivo("BluetoothServiceRT", "Error: run", iOException.getMessage());
        BluetoothServiceRT.this.connectionFailed();
        try {
          this.mmSocket.close();
        } catch (IOException iOException1) {
          Utilities.escribeArchivo("BluetoothServiceRT", "Error: run()", iOException1.getMessage());
        } 
        BluetoothServiceRT.this.start();
        return;
      } 
    }
  }
  
  private class ConnectedThread extends Thread {
    private boolean mRestartServiceOnConnectionLost = true;
    
    private final InputStream mmInStream;
    
    private final OutputStream mmOutStream;
    
    private final BluetoothSocket mmSocket;
    
    final BluetoothServiceRT this$0;
    
    public ConnectedThread(BluetoothSocket param1BluetoothSocket) {
      this.mmSocket = param1BluetoothSocket;
      IOException iOException2 = null;
      try {
        InputStream inputStream = param1BluetoothSocket.getInputStream();
        try {
          OutputStream outputStream = param1BluetoothSocket.getOutputStream();
        } catch (IOException null) {}
      } catch (IOException iOException1) {
        BluetoothServiceRT.this = null;
      } 
      Utilities.escribeArchivo("BluetoothServiceRT", "Error: ConnectedThread", iOException1.getMessage());
      iOException1 = iOException2;
    }
    
    public void Cancel() {
      this.mRestartServiceOnConnectionLost = false;
      try {
        this.mmSocket.close();
      } catch (IOException iOException) {
        Utilities.escribeArchivo("BluetoothServiceRT", "Error: Cancel", iOException.getMessage());
      } 
    }
    
    public void Write(byte[] param1ArrayOfbyte) {
      try {
        String str = new String();
        this(param1ArrayOfbyte, "ISO-8859-1");
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        StringBuilder stringBuilder2 = new StringBuilder();
        this();
        int i = param1ArrayOfbyte.length;
        for (byte b = 0; b < i; b++) {
          byte b1 = param1ArrayOfbyte[b];
          stringBuilder1.append(byteToHex(b1));
          stringBuilder2.append(byteToAsc(b1));
          stringBuilder1.append(" ");
          stringBuilder2.append(" ");
        } 
        Utilities.escribeArchivo("BluetoothServiceRT", "P2V: WRITE: ", str);
        this.mmOutStream.write(param1ArrayOfbyte);
        BluetoothServiceRT.mHandler.obtainMessage(3, -1, -1, param1ArrayOfbyte).sendToTarget();
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BLUETOOTH > ConnectedThread.class > Write() > ERROR: ");
        stringBuilder.append(iOException.getMessage());
        Utilities.escribeArchivo("BluetoothServiceRT", "Error", stringBuilder.toString());
      } 
    }
    
    public String byteToAsc(byte param1Byte) {
      return String.valueOf(param1Byte & 0xFF);
    }
    
    public String byteToHex(byte param1Byte) {
      return Integer.toHexString(param1Byte & 0xFF);
    }
    
    public void run() {
      try {
        while (true) {
          byte[] arrayOfByte = new byte[1024];
          int i = this.mmInStream.read(arrayOfByte);
          String str = new String();
          this(arrayOfByte, "ISO-8859-1");
          Utilities.escribeArchivo("BluetoothServiceRT", "V2P : READ: ", str.trim());
          ArrayList arrayList = MainActivity.getCommands(str);
          if (arrayList.size() > 1) {
            for (String str1 : arrayList) {
              Utilities.escribeArchivo("BluetoothServiceRT", "V2P SPLIT", str1);
              BluetoothServiceRT.mHandler.obtainMessage(2, (str1.getBytes()).length, -1, str1.getBytes()).sendToTarget();
            } 
            continue;
          } 
          BluetoothServiceRT.mHandler.obtainMessage(2, i, -1, arrayOfByte).sendToTarget();
        } 
      } catch (IOException iOException) {
        Utilities.escribeArchivo("BluetoothServiceRT", "Error: run()", iOException.getMessage());
        BluetoothServiceRT.this.connectionLost();
        if (this.mRestartServiceOnConnectionLost)
          BluetoothServiceRT.this.start(); 
        return;
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/BluetoothServiceRT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */