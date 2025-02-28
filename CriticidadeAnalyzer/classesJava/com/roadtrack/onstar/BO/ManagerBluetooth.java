package com.roadtrack.onstar.BO;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.commonQueue;
import com.roadtrack.onstar.utils.Utilities;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Pattern;

public class ManagerBluetooth {
  private static boolean bCalculandoRuta = false;
  
  private static boolean bContieneWayPoints = false;
  
  private static int iNumeroDeWayPoints;
  
  private static Context mContext;
  
  private static Vector<String> vecMensajesIncompletos = new Vector<String>();
  
  private commonQueue InQueue;
  
  public Handler _handlerNewMessage;
  
  private MessagesObjects msgObj;
  
  private void ProcessCommands(int paramInt, String[] paramArrayOfString) {
    String str;
    int j;
    Enums.V2PCommands v2PCommands = Enums.V2PCommands.values()[paramInt];
    int i = null.$SwitchMap$com$roadtrack$onstar$Enums$V2PCommands[v2PCommands.ordinal()];
    paramInt = 1;
    switch (i) {
      case 7:
        MainActivity.sendBTResponseRecived2(paramArrayOfString[0], paramArrayOfString[1], bContieneWayPoints, paramArrayOfString);
        break;
      case 6:
        iNumeroDeWayPoints = Integer.parseInt(paramArrayOfString[6]);
        if (iNumeroDeWayPoints != 0) {
          bContieneWayPoints = true;
        } else {
          bContieneWayPoints = false;
        } 
        MainActivity.sendBTResponseRecived2(paramArrayOfString[0], paramArrayOfString[6], bContieneWayPoints, paramArrayOfString);
        break;
      case 5:
        GlobalMembers.bMostrandoMenuHMI = false;
        break;
      case 3:
        if (paramArrayOfString.length >= 12) {
          paramInt = Integer.parseInt(paramArrayOfString[8]);
          i = Integer.parseInt(paramArrayOfString[5]);
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(paramInt);
          stringBuilder1.append("");
          str = stringBuilder1.toString();
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append(i);
          stringBuilder2.append("");
          MainActivity.sendBTResponseRecived(str, stringBuilder2.toString());
        } 
        break;
      case 2:
        MainActivity.sendBTResponseRecived2(str[0], str[1], false, (String[])str);
        break;
      case 1:
        j = str[1].equals("1");
        if (GlobalMembers.iRutaRecibida == 3)
          paramInt = 0; 
        if ((j & paramInt) != 0 && !GlobalMembers.bSeEnvioRutaAP8)
          createNavigationAlertDialog((String[])str); 
        if (GlobalMembers.iRutaRecibida == 3)
          GlobalMembers.iRutaRecibida = 0; 
        GlobalMembers.bSeEnvioRutaAP8 = false;
        break;
    } 
  }
  
  @SuppressLint({"NewApi"})
  private String[] RecoverMessages(String paramString) {
    int i;
    String str;
    Vector<String> vector = new Vector();
    while (true) {
      boolean bool = paramString.isEmpty();
      byte b = 0;
      String str1 = "";
      i = b;
      str = str1;
      if (!bool) {
        i = paramString.indexOf("~");
        StringBuilder stringBuilder1 = new StringBuilder();
        int j = i + 3;
        stringBuilder1.append(paramString.charAt(j));
        stringBuilder1.append("");
        if (stringBuilder1.toString().equals("]")) {
          String str3 = paramString.substring(1, j).replace("[", "{").replace("]", "}");
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("[");
          stringBuilder1.append(str3);
          stringBuilder1.append("]");
          vector.add(stringBuilder1.toString());
          j = paramString.length();
          int k = i + 4;
          i = b;
          String str2 = str1;
          if (j > k) {
            paramString = paramString.substring(k, paramString.length());
            continue;
          } 
          break;
        } 
        str = paramString.substring(0, ++i);
        paramString = paramString.substring(i, paramString.length());
        str = str.replace("~", "^");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(paramString);
        paramString = stringBuilder2.toString();
        continue;
      } 
      break;
    } 
    while (i < vector.size()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(vector.get(i));
      str = stringBuilder.toString();
      i++;
    } 
    return str.split("\\]");
  }
  
  private String[] SeparateMessages(String paramString) {
    String str = paramString;
    if (!paramString.endsWith("]")) {
      int i = paramString.lastIndexOf("[");
      str = paramString;
      if (i > 0) {
        str = paramString.substring(i);
        vecMensajesIncompletos.add(str);
        str = paramString.substring(0, i);
      } 
    } 
    paramString = str;
    if (!str.startsWith("[")) {
      int i = str.indexOf("]");
      paramString = str;
      if (i > 0) {
        paramString = str.substring(0, ++i);
        vecMensajesIncompletos.add(paramString);
        paramString = str.substring(i, str.length());
      } 
    } 
    return RecoverMessages(paramString);
  }
  
  public static void createNavigationAlertDialog(final String[] param) {
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str1 = Utilities.getStringFromConfigList(mContext, stringsResourcesVO.continue_navigation, 2131689737);
    String str2 = Utilities.getStringFromConfigList(mContext, stringsResourcesVO.global_lbl_navegacion_1, 2131689929);
    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
    builder.setMessage(str1).setTitle(str2.toUpperCase());
    builder.setPositiveButton("App", new DialogInterface.OnClickListener() {
          final String[] val$param;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            if (GlobalMembers.iAppNavigationStatus == 0) {
              String[] arrayOfString = param;
              MainActivity.sendBTResponseRecived2(arrayOfString[0], arrayOfString[1], false, arrayOfString);
            } 
            param1DialogInterface.dismiss();
          }
        });
    builder.setNegativeButton(Utilities.getStringFromConfigList(mContext, stringsResourcesVO.notificationsVehicle, 2131690224), new DialogInterface.OnClickListener() {
          StringsResourcesVO _stringsResourcesVO = new StringsResourcesVO();
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            if (GlobalMembers.iAppNavigationStatus != 0) {
              MainActivity.getmNPlatinum().sendTextForTTS(Utilities.getStringFromConfigList(ManagerBluetooth.mContext, this._stringsResourcesVO.sendrutetovehiclehmi, 2131690362));
              GlobalMembers.bRequestForSendNavigation = true;
            } 
            param1DialogInterface.dismiss();
          }
        });
    builder.create().show();
  }
  
  @SuppressLint({"NewApi"})
  private void enqueuedData(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual parseMsg : (Ljava/lang/String;)V
    //   5: aload_1
    //   6: invokevirtual trim : ()Ljava/lang/String;
    //   9: invokevirtual toCharArray : ()[C
    //   12: astore #9
    //   14: aload #9
    //   16: arraylength
    //   17: istore #6
    //   19: ldc ''
    //   21: astore #7
    //   23: iconst_0
    //   24: istore_3
    //   25: iconst_0
    //   26: istore #4
    //   28: iload_3
    //   29: iload #6
    //   31: if_icmpge -> 311
    //   34: aload #9
    //   36: iload_3
    //   37: caload
    //   38: istore_2
    //   39: iload #4
    //   41: iconst_1
    //   42: iadd
    //   43: istore #5
    //   45: new java/lang/StringBuilder
    //   48: dup
    //   49: invokespecial <init> : ()V
    //   52: astore #8
    //   54: aload #8
    //   56: aload #7
    //   58: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: aload #8
    //   64: iload_2
    //   65: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload #8
    //   71: invokevirtual toString : ()Ljava/lang/String;
    //   74: astore #8
    //   76: aload #8
    //   78: astore #7
    //   80: iload_2
    //   81: bipush #93
    //   83: if_icmpne -> 301
    //   86: aload #8
    //   88: astore #7
    //   90: aload_1
    //   91: invokevirtual trim : ()Ljava/lang/String;
    //   94: invokevirtual length : ()I
    //   97: iload #5
    //   99: if_icmpne -> 301
    //   102: aload_0
    //   103: getfield InQueue : Lcom/roadtrack/onstar/VO/commonQueue;
    //   106: invokevirtual isEmpty : ()Z
    //   109: ifeq -> 172
    //   112: aload_0
    //   113: getfield msgObj : Lcom/roadtrack/onstar/BO/MessagesObjects;
    //   116: aload #8
    //   118: invokevirtual messageIsValid : (Ljava/lang/String;)Ljava/lang/Boolean;
    //   121: invokevirtual booleanValue : ()Z
    //   124: ifeq -> 136
    //   127: aload_0
    //   128: aload #8
    //   130: invokevirtual newMessage : (Ljava/lang/String;)V
    //   133: goto -> 165
    //   136: aload_0
    //   137: aload_0
    //   138: aload #8
    //   140: invokespecial getOpcode : (Ljava/lang/String;)Ljava/lang/String;
    //   143: invokespecial queueBuffer_MessageNotValid : (Ljava/lang/String;)V
    //   146: goto -> 165
    //   149: astore #7
    //   151: ldc_w 'ManagerBluetooth'
    //   154: ldc_w 'Error: enqueuedData'
    //   157: aload #7
    //   159: invokevirtual getMessage : ()Ljava/lang/String;
    //   162: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   165: ldc ''
    //   167: astore #7
    //   169: goto -> 301
    //   172: aload_0
    //   173: getfield InQueue : Lcom/roadtrack/onstar/VO/commonQueue;
    //   176: aload #8
    //   178: invokevirtual enqueue : (Ljava/lang/Object;)V
    //   181: ldc ''
    //   183: astore #7
    //   185: iconst_0
    //   186: istore #4
    //   188: iload #4
    //   190: aload_0
    //   191: getfield InQueue : Lcom/roadtrack/onstar/VO/commonQueue;
    //   194: invokevirtual count : ()I
    //   197: iconst_1
    //   198: isub
    //   199: if_icmpge -> 245
    //   202: new java/lang/StringBuilder
    //   205: dup
    //   206: invokespecial <init> : ()V
    //   209: astore #8
    //   211: aload #8
    //   213: aload #7
    //   215: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: pop
    //   219: aload #8
    //   221: aload_0
    //   222: getfield InQueue : Lcom/roadtrack/onstar/VO/commonQueue;
    //   225: invokevirtual dequeue : ()Ljava/lang/Object;
    //   228: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: aload #8
    //   234: invokevirtual toString : ()Ljava/lang/String;
    //   237: astore #7
    //   239: iinc #4, 1
    //   242: goto -> 188
    //   245: aload_0
    //   246: getfield msgObj : Lcom/roadtrack/onstar/BO/MessagesObjects;
    //   249: aload #7
    //   251: invokevirtual messageIsValid : (Ljava/lang/String;)Ljava/lang/Boolean;
    //   254: invokevirtual booleanValue : ()Z
    //   257: ifeq -> 269
    //   260: aload_0
    //   261: aload #7
    //   263: invokevirtual newMessage : (Ljava/lang/String;)V
    //   266: goto -> 165
    //   269: aload_0
    //   270: aload_0
    //   271: aload #7
    //   273: invokespecial getOpcode : (Ljava/lang/String;)Ljava/lang/String;
    //   276: invokespecial queueBuffer_MessageNotValid : (Ljava/lang/String;)V
    //   279: goto -> 165
    //   282: astore #7
    //   284: ldc_w 'ManagerBluetooth'
    //   287: ldc_w 'Error: EnqueuedData'
    //   290: aload #7
    //   292: invokevirtual getMessage : ()Ljava/lang/String;
    //   295: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   298: goto -> 165
    //   301: iinc #3, 1
    //   304: iload #5
    //   306: istore #4
    //   308: goto -> 28
    //   311: aload #7
    //   313: ifnull -> 333
    //   316: aload #7
    //   318: invokevirtual length : ()I
    //   321: ifle -> 333
    //   324: aload_0
    //   325: getfield InQueue : Lcom/roadtrack/onstar/VO/commonQueue;
    //   328: aload #7
    //   330: invokevirtual enqueue : (Ljava/lang/Object;)V
    //   333: return
    // Exception table:
    //   from	to	target	type
    //   136	146	149	java/lang/Exception
    //   269	279	282	java/lang/Exception
  }
  
  private String getOpcode(String paramString) {
    paramString = paramString.replace("[", " ").trim().replace("]", " ").trim();
    return (paramString.indexOf("|") > 0) ? paramString.split("\\|")[0].trim() : paramString.substring(0, paramString.indexOf("~"));
  }
  
  public static Boolean isValidDeviceName(String paramString) {
    return Boolean.valueOf(true);
  }
  
  private void queueBuffer_MessageNotValid(String paramString) {
    this._handlerNewMessage.obtainMessage(Enums.WhatHandler.ErrorChekSum.ordinal(), Integer.valueOf(paramString).intValue(), 0, "").sendToTarget();
  }
  
  public static void updateContext(Context paramContext) {
    mContext = paramContext;
  }
  
  @SuppressLint({"NewApi"})
  public void aggregateData(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull -> 150
    //   6: aload_1
    //   7: invokevirtual isEmpty : ()Z
    //   10: ifne -> 150
    //   13: aload_0
    //   14: aload_1
    //   15: invokespecial SeparateMessages : (Ljava/lang/String;)[Ljava/lang/String;
    //   18: astore_1
    //   19: iconst_0
    //   20: istore_2
    //   21: iload_2
    //   22: aload_1
    //   23: arraylength
    //   24: if_icmpge -> 150
    //   27: aload_1
    //   28: iload_2
    //   29: aaload
    //   30: ldc ']'
    //   32: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   35: ifne -> 43
    //   38: iconst_1
    //   39: istore_3
    //   40: goto -> 45
    //   43: iconst_0
    //   44: istore_3
    //   45: iload_3
    //   46: iconst_1
    //   47: aload_1
    //   48: iload_2
    //   49: aaload
    //   50: invokevirtual isEmpty : ()Z
    //   53: ixor
    //   54: iand
    //   55: ifeq -> 139
    //   58: new java/lang/StringBuilder
    //   61: astore #4
    //   63: aload #4
    //   65: invokespecial <init> : ()V
    //   68: aload #4
    //   70: aload_1
    //   71: iload_2
    //   72: aaload
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: aload #4
    //   79: ldc ']'
    //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: aload_1
    //   86: iload_2
    //   87: aload #4
    //   89: invokevirtual toString : ()Ljava/lang/String;
    //   92: aastore
    //   93: aload_1
    //   94: iload_2
    //   95: aload_1
    //   96: iload_2
    //   97: aaload
    //   98: ldc '{'
    //   100: ldc '['
    //   102: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   105: aastore
    //   106: aload_1
    //   107: iload_2
    //   108: aload_1
    //   109: iload_2
    //   110: aaload
    //   111: ldc '}'
    //   113: ldc ']'
    //   115: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   118: aastore
    //   119: aload_1
    //   120: iload_2
    //   121: aload_1
    //   122: iload_2
    //   123: aaload
    //   124: ldc '^'
    //   126: ldc '~'
    //   128: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   131: aastore
    //   132: aload_0
    //   133: aload_1
    //   134: iload_2
    //   135: aaload
    //   136: invokespecial enqueuedData : (Ljava/lang/String;)V
    //   139: iinc #2, 1
    //   142: goto -> 21
    //   145: astore_1
    //   146: aload_0
    //   147: monitorexit
    //   148: aload_1
    //   149: athrow
    //   150: aload_0
    //   151: monitorexit
    //   152: return
    // Exception table:
    //   from	to	target	type
    //   6	19	145	finally
    //   21	38	145	finally
    //   45	139	145	finally
  }
  
  public void initializeObjects(Context paramContext) {
    this.msgObj = new MessagesObjects();
    this.InQueue = new commonQueue();
    mContext = paramContext;
  }
  
  @SuppressLint({"NewApi"})
  public void newMessage(String paramString) {
    if (paramString.contains("5|5|5")) {
      GlobalMembers.strMensaje = paramString;
      GlobalMembers.str555tmp = paramString.substring(paramString.indexOf(";") + 1, paramString.lastIndexOf("~"));
      if (GlobalMembers.str555tmp.contains("Calculando ruta")) {
        bCalculandoRuta = true;
      } else if (bCalculandoRuta) {
        bCalculandoRuta = false;
      } 
      int i = GlobalMembers.EstadoAppGlobal;
      if (i != 1) {
        if (i != 2) {
          if (i != 3 && i != 4 && i == 5)
            GlobalMembers.Listenerg.onEvent(); 
        } else {
          GlobalMembers.BTNavListenerTitle.onEvent();
        } 
      } else {
        GlobalMembers.ListenerUpdateTitle.onevent();
      } 
    } 
    if (paramString != null && paramString.contains("50|3|2")) {
      String[] arrayOfString = paramString.split("\\|");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(arrayOfString[3]);
      stringBuilder.append(":");
      GlobalMembers.str555tmp = stringBuilder.toString();
      GlobalMembers.ListenerUpdateTitle.onevent();
    } else if (paramString != null && paramString.contains("50|1")) {
      String[] arrayOfString = paramString.split("\\|");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(arrayOfString[3]);
      stringBuilder.append(":");
      GlobalMembers.str555tmp = stringBuilder.toString();
      GlobalMembers.ListenerUpdateTitle.onevent();
    } else if (paramString != null && paramString.contains("50|0")) {
      String[] arrayOfString = paramString.split("\\|");
      Utilities.escribeArchivo("ManagerBluetooth", "HMI: titulo:", arrayOfString[3]);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(arrayOfString[3]);
      stringBuilder.append(":");
      GlobalMembers.str555tmp = stringBuilder.toString();
    } else if (paramString.contains("Enviar;") || paramString.contains("Send;") || paramString.contains("Enviar;")) {
      String[] arrayOfString = paramString.split("\\|");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Mensaje: ");
      stringBuilder.append(arrayOfString[3]);
      GlobalMembers.str555tmp = stringBuilder.toString();
      GlobalMembers.ListenerUpdateTitle.onevent();
    } 
    if (paramString != null && paramString.equalsIgnoreCase("[601~97]")) {
      BluetoothServiceRT.retries = 4;
      BluetoothServiceRT bluetoothServiceRT = MainActivity.mChatService;
      if (bluetoothServiceRT != null)
        bluetoothServiceRT.stop(); 
    } 
    try {
      Hashtable hashtable = Utilities.GetMessage(paramString);
      this._handlerNewMessage.obtainMessage(Enums.WhatHandler.Platinum_OpCodes.ordinal(), ((Integer)hashtable.get("OpCode")).intValue(), 0, hashtable.get("messageBody")).sendToTarget();
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerBluetooth", "Error: newMessage", exception.getMessage());
    } 
  }
  
  void parseMsg(String paramString) {
    boolean bool1;
    boolean bool2 = true;
    if (paramString != null) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramString.length() <= 0)
      bool2 = false; 
    if ((bool2 & bool1) != 0) {
      String[] arrayOfString = paramString.replace("[", "").replace("]", "").replace("~", "|").split("\\|");
    } else {
      paramString = null;
    } 
    if (paramString != null && paramString.length > 0 && Pattern.compile("[0-9]{1,2}").matcher(paramString[0]).matches())
      ProcessCommands(Integer.parseInt(paramString[0]), (String[])paramString); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/ManagerBluetooth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */