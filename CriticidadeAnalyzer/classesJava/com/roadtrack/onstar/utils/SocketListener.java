package com.roadtrack.onstar.utils;

import android.app.Activity;
import android.content.Intent;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.TransparentActivityExpirationDialog;
import com.roadtrack.onstar.VO.GMTCatalog;
import com.roadtrack.onstar.VO.Historical;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.pid.PIDActivity;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import com.roadtrack.onstar.ui.devices.RegistrationDevices;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.ui.settings.SettingsNewActivity;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class SocketListener {
  private static InputStream inputStream;
  
  private static BufferedWriter out;
  
  private static String serverIP = GlobalMembers.strIPSocketGetCommand;
  
  private static int serverPort;
  
  private static Socket socket;
  
  private static String[] socketResponses = null;
  
  static {
    serverPort = Integer.valueOf(GlobalMembers.strPuertoA).intValue();
  }
  
  public static void SocketListenerCloses() {
    try {
      if (out != null) {
        out.close();
        out = null;
      } 
    } catch (IOException|Exception iOException) {}
    try {
      if (inputStream != null) {
        inputStream.close();
        inputStream = null;
      } 
    } catch (IOException iOException) {}
    try {
      if (socket != null) {
        socket.close();
        socket = null;
      } 
    } catch (IOException iOException) {}
  }
  
  public static void SocketListenerConected() {
    // Byte code:
    //   0: ldc com/roadtrack/onstar/utils/SocketListener
    //   2: monitorenter
    //   3: getstatic com/roadtrack/onstar/BO/GlobalMembers.strAccountID : Ljava/lang/String;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull -> 609
    //   11: aload_2
    //   12: invokevirtual length : ()I
    //   15: ifeq -> 609
    //   18: getstatic com/roadtrack/onstar/BO/GlobalMembers.isSockeActived : Z
    //   21: ifne -> 27
    //   24: goto -> 609
    //   27: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   30: ifnull -> 492
    //   33: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   36: invokevirtual isConnected : ()Z
    //   39: ifeq -> 492
    //   42: aload_2
    //   43: ifnull -> 492
    //   46: aload_2
    //   47: invokevirtual length : ()I
    //   50: ifle -> 492
    //   53: new java/io/BufferedWriter
    //   56: astore_3
    //   57: new java/io/OutputStreamWriter
    //   60: astore #4
    //   62: aload #4
    //   64: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   67: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   70: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   73: aload_3
    //   74: aload #4
    //   76: invokespecial <init> : (Ljava/io/Writer;)V
    //   79: aload_3
    //   80: putstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   83: new java/lang/StringBuilder
    //   86: astore_3
    //   87: aload_3
    //   88: invokespecial <init> : ()V
    //   91: aload_3
    //   92: ldc 'AC:'
    //   94: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: pop
    //   98: aload_3
    //   99: aload_2
    //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: pop
    //   104: aload_3
    //   105: invokevirtual toString : ()Ljava/lang/String;
    //   108: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   111: astore_3
    //   112: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   115: aload_3
    //   116: invokevirtual write : (Ljava/lang/String;)V
    //   119: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   122: invokevirtual flush : ()V
    //   125: sipush #8192
    //   128: newarray byte
    //   130: astore_3
    //   131: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   134: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   137: putstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   140: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   143: invokevirtual isConnected : ()Z
    //   146: ifeq -> 543
    //   149: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   152: aload_3
    //   153: invokevirtual read : ([B)I
    //   156: istore_1
    //   157: new java/io/ByteArrayOutputStream
    //   160: astore #4
    //   162: aload #4
    //   164: invokespecial <init> : ()V
    //   167: iconst_0
    //   168: istore_0
    //   169: aload #4
    //   171: aload_3
    //   172: iconst_0
    //   173: iload_1
    //   174: invokevirtual write : ([BII)V
    //   177: aload #4
    //   179: ldc 'UTF-8'
    //   181: invokevirtual toString : (Ljava/lang/String;)Ljava/lang/String;
    //   184: astore #4
    //   186: aconst_null
    //   187: putstatic com/roadtrack/onstar/utils/SocketListener.socketResponses : [Ljava/lang/String;
    //   190: aload #4
    //   192: ldc '\r?\n'
    //   194: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   197: putstatic com/roadtrack/onstar/utils/SocketListener.socketResponses : [Ljava/lang/String;
    //   200: iload_0
    //   201: getstatic com/roadtrack/onstar/utils/SocketListener.socketResponses : [Ljava/lang/String;
    //   204: arraylength
    //   205: if_icmpge -> 140
    //   208: new java/lang/String
    //   211: invokespecial <init> : ()V
    //   214: getstatic com/roadtrack/onstar/utils/SocketListener.socketResponses : [Ljava/lang/String;
    //   217: iload_0
    //   218: aaload
    //   219: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   222: astore #4
    //   224: aload #4
    //   226: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   229: aload_2
    //   230: invokestatic socketFindMe : (Ljava/lang/String;Ljava/net/Socket;Ljava/lang/String;)V
    //   233: aload #4
    //   235: ldc 'KeepAlive'
    //   237: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   240: ifne -> 334
    //   243: new java/lang/StringBuilder
    //   246: astore #5
    //   248: aload #5
    //   250: invokespecial <init> : ()V
    //   253: aload #5
    //   255: ldc 'SocketListenerConected: DATA_REMOTE_SERVICES READ SOCKET '
    //   257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: pop
    //   261: aload #5
    //   263: iload_0
    //   264: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload #5
    //   270: invokevirtual toString : ()Ljava/lang/String;
    //   273: astore #6
    //   275: new java/lang/StringBuilder
    //   278: astore #5
    //   280: aload #5
    //   282: invokespecial <init> : ()V
    //   285: aload #5
    //   287: ldc 'MESSAGE: '
    //   289: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: pop
    //   293: aload #5
    //   295: aload #4
    //   297: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: pop
    //   301: aload #5
    //   303: ldc ' - '
    //   305: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: pop
    //   309: aload #5
    //   311: getstatic com/roadtrack/onstar/utils/SocketListener.socketResponses : [Ljava/lang/String;
    //   314: arraylength
    //   315: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   318: pop
    //   319: ldc 'SocketListener'
    //   321: aload #6
    //   323: aload #5
    //   325: invokevirtual toString : ()Ljava/lang/String;
    //   328: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   331: goto -> 422
    //   334: new java/lang/StringBuilder
    //   337: astore #5
    //   339: aload #5
    //   341: invokespecial <init> : ()V
    //   344: aload #5
    //   346: ldc 'SocketListenerConected: DATA_REMOTE_SERVICES READ SOCKET '
    //   348: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: pop
    //   352: aload #5
    //   354: iload_0
    //   355: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   358: pop
    //   359: aload #5
    //   361: invokevirtual toString : ()Ljava/lang/String;
    //   364: astore #6
    //   366: new java/lang/StringBuilder
    //   369: astore #5
    //   371: aload #5
    //   373: invokespecial <init> : ()V
    //   376: aload #5
    //   378: ldc 'MESSAGE: '
    //   380: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: pop
    //   384: aload #5
    //   386: aload #4
    //   388: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: pop
    //   392: aload #5
    //   394: ldc ' - '
    //   396: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: pop
    //   400: aload #5
    //   402: getstatic com/roadtrack/onstar/utils/SocketListener.socketResponses : [Ljava/lang/String;
    //   405: arraylength
    //   406: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   409: pop
    //   410: ldc 'SocketListener'
    //   412: aload #6
    //   414: aload #5
    //   416: invokevirtual toString : ()Ljava/lang/String;
    //   419: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   422: aload #4
    //   424: ldc 'PI:'
    //   426: ldc ''
    //   428: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   431: ldc ','
    //   433: ldc ';'
    //   435: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   438: astore #4
    //   440: aload #4
    //   442: invokestatic socketBadgeNumberForAction : (Ljava/lang/String;)V
    //   445: aload #4
    //   447: invokestatic socketBadgeNumberForTracking : (Ljava/lang/String;)V
    //   450: aload #4
    //   452: invokestatic socketBadgeNumberForTrigger : (Ljava/lang/String;)V
    //   455: aload #4
    //   457: invokestatic socketBadgeNumberForAlert : (Ljava/lang/String;)V
    //   460: aload #4
    //   462: invokestatic socketUpdateDeviceGmtValue : (Ljava/lang/String;)V
    //   465: aload #4
    //   467: invokestatic socketDTCSelector : (Ljava/lang/String;)V
    //   470: aload #4
    //   472: invokestatic socketAndroidAuto : (Ljava/lang/String;)V
    //   475: getstatic com/roadtrack/onstar/BO/GlobalMembers.renewalfeatures : Z
    //   478: ifeq -> 486
    //   481: aload #4
    //   483: invokestatic socketRenewalPaymentResult : (Ljava/lang/String;)V
    //   486: iinc #0, 1
    //   489: goto -> 200
    //   492: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   495: ifnull -> 508
    //   498: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   501: invokevirtual close : ()V
    //   504: aconst_null
    //   505: putstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   508: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   511: ifnull -> 524
    //   514: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   517: invokevirtual close : ()V
    //   520: aconst_null
    //   521: putstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   524: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   527: ifnull -> 540
    //   530: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   533: invokevirtual close : ()V
    //   536: aconst_null
    //   537: putstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   540: invokestatic StartSocketListener : ()V
    //   543: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   546: ifnull -> 574
    //   549: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   552: invokevirtual close : ()V
    //   555: aconst_null
    //   556: putstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   559: goto -> 574
    //   562: astore_2
    //   563: ldc 'SocketListener'
    //   565: ldc 'Error: SocketListenerCloses.IOException'
    //   567: aload_2
    //   568: invokevirtual getMessage : ()Ljava/lang/String;
    //   571: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   574: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   577: ifnull -> 590
    //   580: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   583: invokevirtual close : ()V
    //   586: aconst_null
    //   587: putstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   590: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   593: ifnull -> 797
    //   596: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   599: invokevirtual close : ()V
    //   602: aconst_null
    //   603: putstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   606: goto -> 797
    //   609: invokestatic SocketListenerCloses : ()V
    //   612: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   615: ifnull -> 643
    //   618: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   621: invokevirtual close : ()V
    //   624: aconst_null
    //   625: putstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   628: goto -> 643
    //   631: astore_2
    //   632: ldc 'SocketListener'
    //   634: ldc 'Error: SocketListenerCloses.IOException'
    //   636: aload_2
    //   637: invokevirtual getMessage : ()Ljava/lang/String;
    //   640: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   643: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   646: ifnull -> 659
    //   649: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   652: invokevirtual close : ()V
    //   655: aconst_null
    //   656: putstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   659: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   662: ifnull -> 675
    //   665: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   668: invokevirtual close : ()V
    //   671: aconst_null
    //   672: putstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   675: ldc com/roadtrack/onstar/utils/SocketListener
    //   677: monitorexit
    //   678: return
    //   679: astore_2
    //   680: goto -> 801
    //   683: astore_2
    //   684: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   687: ifnull -> 700
    //   690: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   693: invokevirtual close : ()V
    //   696: aconst_null
    //   697: putstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   700: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   703: ifnull -> 716
    //   706: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   709: invokevirtual close : ()V
    //   712: aconst_null
    //   713: putstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   716: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   719: ifnull -> 732
    //   722: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   725: invokevirtual close : ()V
    //   728: aconst_null
    //   729: putstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   732: invokestatic SocketListenerRestart : ()V
    //   735: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   738: ifnull -> 766
    //   741: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   744: invokevirtual close : ()V
    //   747: aconst_null
    //   748: putstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   751: goto -> 766
    //   754: astore_2
    //   755: ldc 'SocketListener'
    //   757: ldc 'Error: SocketListenerCloses.IOException'
    //   759: aload_2
    //   760: invokevirtual getMessage : ()Ljava/lang/String;
    //   763: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   766: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   769: ifnull -> 782
    //   772: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   775: invokevirtual close : ()V
    //   778: aconst_null
    //   779: putstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   782: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   785: ifnull -> 797
    //   788: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   791: invokevirtual close : ()V
    //   794: goto -> 602
    //   797: ldc com/roadtrack/onstar/utils/SocketListener
    //   799: monitorexit
    //   800: return
    //   801: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   804: ifnull -> 836
    //   807: getstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   810: invokevirtual close : ()V
    //   813: aconst_null
    //   814: putstatic com/roadtrack/onstar/utils/SocketListener.out : Ljava/io/BufferedWriter;
    //   817: goto -> 836
    //   820: astore_2
    //   821: goto -> 870
    //   824: astore_3
    //   825: ldc 'SocketListener'
    //   827: ldc 'Error: SocketListenerCloses.IOException'
    //   829: aload_3
    //   830: invokevirtual getMessage : ()Ljava/lang/String;
    //   833: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   836: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   839: ifnull -> 852
    //   842: getstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   845: invokevirtual close : ()V
    //   848: aconst_null
    //   849: putstatic com/roadtrack/onstar/utils/SocketListener.inputStream : Ljava/io/InputStream;
    //   852: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   855: ifnull -> 868
    //   858: getstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   861: invokevirtual close : ()V
    //   864: aconst_null
    //   865: putstatic com/roadtrack/onstar/utils/SocketListener.socket : Ljava/net/Socket;
    //   868: aload_2
    //   869: athrow
    //   870: ldc com/roadtrack/onstar/utils/SocketListener
    //   872: monitorexit
    //   873: aload_2
    //   874: athrow
    //   875: astore_2
    //   876: goto -> 508
    //   879: astore_2
    //   880: goto -> 524
    //   883: astore_2
    //   884: goto -> 540
    //   887: astore_2
    //   888: goto -> 574
    //   891: astore_2
    //   892: goto -> 590
    //   895: astore_2
    //   896: goto -> 797
    //   899: astore_2
    //   900: goto -> 643
    //   903: astore_2
    //   904: goto -> 659
    //   907: astore_2
    //   908: goto -> 675
    //   911: astore_2
    //   912: goto -> 700
    //   915: astore_2
    //   916: goto -> 716
    //   919: astore_2
    //   920: goto -> 732
    //   923: astore_2
    //   924: goto -> 766
    //   927: astore_2
    //   928: goto -> 782
    //   931: astore_3
    //   932: goto -> 836
    //   935: astore_3
    //   936: goto -> 852
    //   939: astore_3
    //   940: goto -> 868
    // Exception table:
    //   from	to	target	type
    //   3	7	683	java/lang/Exception
    //   3	7	679	finally
    //   11	24	683	java/lang/Exception
    //   11	24	679	finally
    //   27	42	683	java/lang/Exception
    //   27	42	679	finally
    //   46	140	683	java/lang/Exception
    //   46	140	679	finally
    //   140	167	683	java/lang/Exception
    //   140	167	679	finally
    //   169	200	683	java/lang/Exception
    //   169	200	679	finally
    //   200	331	683	java/lang/Exception
    //   200	331	679	finally
    //   334	422	683	java/lang/Exception
    //   334	422	679	finally
    //   422	486	683	java/lang/Exception
    //   422	486	679	finally
    //   492	508	875	java/io/IOException
    //   492	508	875	java/lang/Exception
    //   492	508	679	finally
    //   508	524	879	java/io/IOException
    //   508	524	683	java/lang/Exception
    //   508	524	679	finally
    //   524	540	883	java/io/IOException
    //   524	540	683	java/lang/Exception
    //   524	540	679	finally
    //   540	543	683	java/lang/Exception
    //   540	543	679	finally
    //   543	559	562	java/io/IOException
    //   543	559	887	java/lang/Exception
    //   543	559	820	finally
    //   563	574	820	finally
    //   574	590	891	java/io/IOException
    //   574	590	820	finally
    //   590	602	895	java/io/IOException
    //   590	602	820	finally
    //   602	606	895	java/io/IOException
    //   602	606	820	finally
    //   609	612	683	java/lang/Exception
    //   609	612	679	finally
    //   612	628	631	java/io/IOException
    //   612	628	899	java/lang/Exception
    //   612	628	820	finally
    //   632	643	820	finally
    //   643	659	903	java/io/IOException
    //   643	659	820	finally
    //   659	675	907	java/io/IOException
    //   659	675	820	finally
    //   684	700	911	java/io/IOException
    //   684	700	911	java/lang/Exception
    //   684	700	679	finally
    //   700	716	915	java/io/IOException
    //   700	716	679	finally
    //   716	732	919	java/io/IOException
    //   716	732	679	finally
    //   732	735	679	finally
    //   735	751	754	java/io/IOException
    //   735	751	923	java/lang/Exception
    //   735	751	820	finally
    //   755	766	820	finally
    //   766	782	927	java/io/IOException
    //   766	782	820	finally
    //   782	794	895	java/io/IOException
    //   782	794	820	finally
    //   801	817	824	java/io/IOException
    //   801	817	931	java/lang/Exception
    //   801	817	820	finally
    //   825	836	820	finally
    //   836	852	935	java/io/IOException
    //   836	852	820	finally
    //   852	868	939	java/io/IOException
    //   852	868	820	finally
    //   868	870	820	finally
  }
  
  static void SocketListenerRestart() {
    (new Thread() {
        public void run() {
          try {
            Thread.sleep(10000L);
          } catch (InterruptedException interruptedException) {}
          try {
            SocketListener.StartSocketListener();
          } catch (Exception exception) {}
        }
      }).start();
  }
  
  public static void StartSocketListener() {
    try {
      if (socket != null) {
        if (socket.isConnected())
          socket.close(); 
        socket = null;
      } 
      Socket socket = new Socket();
      this();
      socket = socket;
      InetSocketAddress inetSocketAddress = new InetSocketAddress();
      this(serverIP, serverPort);
      socket.bind(null);
      socket.setSoTimeout(GlobalMembers.SOCKET_READ_TIMEOUT);
      socket.connect(inetSocketAddress, GlobalMembers.SOCKET_TIMEOUT);
      SocketListenerConected();
    } catch (Exception exception) {
      SocketListenerCloses();
      SocketListenerRestart();
    } 
  }
  
  private static String getDeviceIdBySerialNumber(String paramString) {
    UserDevicesVO userDevicesVO;
    List list = GlobalMembers.mDeviceUserList;
    String str1 = "";
    String str2 = str1;
    if (list != null) {
      str2 = str1;
      if (list.size() > 0) {
        Iterator<UserDevicesVO> iterator = list.iterator();
        while (true) {
          str2 = str1;
          if (iterator.hasNext()) {
            userDevicesVO = iterator.next();
            if (paramString.equals(userDevicesVO.getSerialnumber()))
              str1 = userDevicesVO.getDeviceId(); 
            continue;
          } 
          break;
        } 
      } 
    } 
    return (String)userDevicesVO;
  }
  
  private static void sendActionAck(String paramString1, String paramString2, String paramString3, String paramString4) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ServiceMobileApp:");
    stringBuilder.append(paramString1);
    stringBuilder.append(",");
    stringBuilder.append(paramString2);
    stringBuilder.append(",");
    stringBuilder.append(paramString3);
    stringBuilder.append(",");
    stringBuilder.append(paramString4);
    paramString1 = Utilities.Crypt(stringBuilder.toString());
    try {
      out.write(paramString1);
      out.flush();
    } catch (IOException iOException) {
    
    } finally {
      BufferedWriter bufferedWriter = out;
      if (bufferedWriter == null)
        try {
          bufferedWriter.close();
        } catch (IOException iOException) {} 
    } 
  }
  
  private static void sendAlertAck(String paramString1, String paramString2, String paramString3, String paramString4) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ServiceMobileApp:");
    stringBuilder.append(paramString1);
    stringBuilder.append(",");
    stringBuilder.append(paramString2);
    stringBuilder.append(",");
    stringBuilder.append(paramString3);
    stringBuilder.append(",");
    stringBuilder.append(paramString4);
    paramString1 = Utilities.Crypt(stringBuilder.toString());
    try {
      out.write(paramString1);
      out.flush();
    } catch (IOException iOException) {
    
    } finally {
      BufferedWriter bufferedWriter = out;
      if (bufferedWriter == null)
        try {
          bufferedWriter.close();
        } catch (IOException iOException) {} 
    } 
  }
  
  private static void sendTriggerAck(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ServiceMobileApp:");
    stringBuilder.append(paramString1);
    stringBuilder.append(",");
    stringBuilder.append(paramString2);
    stringBuilder.append(",");
    stringBuilder.append(paramString3);
    stringBuilder.append(",");
    stringBuilder.append(paramString4);
    stringBuilder.append(",");
    stringBuilder.append(paramString5);
    paramString1 = Utilities.Crypt(stringBuilder.toString());
    try {
      out.write(paramString1);
      out.flush();
    } catch (IOException iOException) {
    
    } finally {
      BufferedWriter bufferedWriter = out;
      if (bufferedWriter == null)
        try {
          bufferedWriter.close();
        } catch (IOException iOException) {} 
    } 
  }
  
  private static void socketAndroidAuto(String paramString) {
    boolean bool = Utilities.ActivityRunning("com.roadtrack.onstar.ui.devices.RegistrationDevices", GlobalMembers.contexGlobal);
    String[] arrayOfString = paramString.replaceAll("\\s+", "").split(";");
    if (arrayOfString != null && arrayOfString.length > 1) {
      paramString = arrayOfString[0];
      String str = arrayOfString[1];
      if (paramString != null && !paramString.isEmpty() && paramString.equals("3014") && str.equalsIgnoreCase(((onstarApplication)GlobalMembers.contexGlobal).getAccountID()) && bool)
        RegistrationDevices.getListService(); 
    } 
  }
  
  private static void socketBadgeNumberForAction(String paramString) {
    if (MainActivity.mainContext != null) {
      String[] arrayOfString2 = paramString.split(";");
      String[] arrayOfString1 = paramString.replaceAll("\\s+", "").split(";");
      if (arrayOfString1 != null && arrayOfString1.length > 1) {
        boolean bool;
        String str2 = arrayOfString1[0];
        if (str2 != null && !str2.isEmpty() && !str2.equals("2002"))
          return; 
        DBFunctions dBFunctions = new DBFunctions(MainActivity.mainContext);
        onstarApplication onstarApplication = (onstarApplication)GlobalMembers.contexGlobal;
        String str5 = arrayOfString1[1];
        int i = Integer.parseInt(arrayOfString1[2]);
        String str8 = arrayOfString1[3];
        String str3 = arrayOfString1[4];
        String str4 = arrayOfString1[5];
        String str6 = arrayOfString1[6];
        String str1 = arrayOfString2[9];
        sendActionAck("3002", str8, onstarApplication.getAccountID(), str5);
        if (str3 != null && !str3.isEmpty() && str3.equals(Enums.Services.DTCAction.GetCodeString()))
          return; 
        if (str5 != null && !str5.isEmpty() && dBFunctions.messageIdExists(str5))
          return; 
        String str9 = getDeviceIdBySerialNumber(str8);
        if (str9 == null)
          return; 
        String str12 = Enums.Category.Other.toString();
        String str10 = Enums.Services.GetName(Integer.parseInt(str3));
        str8 = Utilities.getDateTime(str9, MainActivity.mainContext);
        String str11 = Enums.TypeItem.Historical.toString();
        if (i == 3) {
          bool = true;
        } else {
          bool = true;
        } 
        String str7 = GlobalMembers.userLogged;
        try {
          SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat();
          this("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
          SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
          this("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
          str1 = simpleDateFormat1.format(simpleDateFormat2.parse(str1));
        } catch (Exception exception) {
          str1 = "00-00-0000 00:00:00";
        } 
        dBFunctions.addHistorical(new Historical(str12, str10, str8, str11, str4, str6, "", i, bool, str3, str9, str7, str1, "0", "0", "0", "0", 0, 0.0D, 0.0D, "0", Integer.parseInt(str5), str1));
        if (str9.equals(Utilities.getLastKnownDeviceSelected(onstarApplication, "SocketListener").getDeviceId()))
          GlobalMembers.notificaciones++; 
        if (Utilities.isActivityRunning(MainActivity.mainContext, MainActivity.class))
          ((Activity)MainActivity.mainContext).runOnUiThread(new Runnable() {
                public void run() {
                  MainActivity.AlertOn();
                }
              }); 
      } 
    } 
  }
  
  private static void socketBadgeNumberForAlert(String paramString) {
    if (MainActivity.mainContext != null) {
      String[] arrayOfString2 = paramString.replaceAll("\\s+", "").split(";");
      String[] arrayOfString1 = paramString.split(";");
      if (arrayOfString2 != null && arrayOfString2.length > 1) {
        String str = arrayOfString2[0];
        if ((str != null && !str.isEmpty() && str.equals("1001")) || (str != null && !str.isEmpty() && str.equals("1002")) || (str != null && !str.isEmpty() && str.equals("1010"))) {
          String str1;
          DBFunctions dBFunctions = new DBFunctions(MainActivity.mainContext);
          onstarApplication onstarApplication = (onstarApplication)GlobalMembers.contexGlobal;
          String str3 = arrayOfString2[1];
          String str2 = arrayOfString2[2];
          String str4 = arrayOfString2[4];
          String str5 = arrayOfString2[5];
          String str6 = arrayOfString1[9];
          sendAlertAck("3002", Utilities.getDeviceSerialNumberByDeviceId(str3), onstarApplication.getAccountID(), str2);
          if (str2 != null && !str2.isEmpty() && dBFunctions.alertIdExists(str2))
            return; 
          try {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat();
            this("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
            this("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
            str1 = simpleDateFormat1.format(simpleDateFormat2.parse(str6));
          } catch (Exception exception) {
            str1 = "00-00-0000 00:00:00";
          } 
          int i = Integer.parseInt(str);
          if (i != 1001) {
            if (i != 1002) {
              if (i != 1010)
                return; 
              str = Utilities.getPushNotTableTitle(235);
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("");
              stringBuilder.append("6");
              dBFunctions.addNotificationDTC(stringBuilder.toString(), str, str1, str2, str3, str4, str5, "0", 235);
              if (str3.equals(Utilities.getLastKnownDeviceSelected(onstarApplication, "SocketListener").getDeviceId()))
                GlobalMembers.notificaciones++; 
            } else {
              str6 = Utilities.getPushNotTableTitle(225);
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("");
              stringBuilder.append("6");
              dBFunctions.addNotificationDTC(stringBuilder.toString(), str6, str1, str2, str3, str4, str5, "0", 225);
              if (str3.equals(Utilities.getLastKnownDeviceSelected(onstarApplication, "SocketListener").getDeviceId()))
                GlobalMembers.notificaciones++; 
            } 
          } else {
            str = Utilities.getPushNotTableTitle(15);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append("6");
            dBFunctions.addNotificationDTC(stringBuilder.toString(), str, str1, str2, str3, str4, str5, "0", 15);
            if (str3.equals(Utilities.getLastKnownDeviceSelected(onstarApplication, "SocketListener").getDeviceId()))
              GlobalMembers.notificaciones++; 
          } 
          if (Utilities.isActivityRunning(MainActivity.mainContext, MainActivity.class))
            ((Activity)MainActivity.mainContext).runOnUiThread(new Runnable() {
                  public void run() {
                    MainActivity.AlertOn();
                  }
                }); 
        } 
      } 
    } 
  }
  
  private static void socketBadgeNumberForTracking(String paramString) {
    if (MainActivity.mainContext != null) {
      String[] arrayOfString2 = paramString.split(";");
      String[] arrayOfString1 = paramString.replaceAll("\\s+", "").split(";");
      if (arrayOfString1 != null && arrayOfString1.length > 1) {
        String str2 = arrayOfString1[0];
        if (str2 != null && !str2.isEmpty() && !str2.equals("1005"))
          return; 
        DBFunctions dBFunctions = new DBFunctions(MainActivity.mainContext);
        onstarApplication onstarApplication = (onstarApplication)GlobalMembers.contexGlobal;
        String str3 = arrayOfString1[1];
        str2 = arrayOfString1[2];
        String str6 = arrayOfString1[3];
        String str4 = arrayOfString1[4];
        String str5 = arrayOfString1[5];
        String str1 = arrayOfString2[9];
        if (str2 != null && !str2.isEmpty() && dBFunctions.alertIdExists(str2))
          return; 
        if (null.$SwitchMap$com$roadtrack$onstar$Enums$Services[Enums.Services.GetValue(Integer.parseInt(str6)).ordinal()] != 1)
          return; 
        try {
          SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat();
          this("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
          SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
          this("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
          str1 = simpleDateFormat1.format(simpleDateFormat2.parse(str1));
        } catch (Exception exception) {
          str1 = "00-00-0000 00:00:00";
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append("12");
        dBFunctions.addNotificationDTC(stringBuilder.toString(), "", str1, str2, str3, str4, str5, "", 27);
        if (str3.equals(Utilities.getLastKnownDeviceSelected(onstarApplication, "SocketListener").getDeviceId()))
          GlobalMembers.notificaciones++; 
        if (Utilities.isActivityRunning(MainActivity.mainContext, MainActivity.class))
          ((Activity)MainActivity.mainContext).runOnUiThread(new Runnable() {
                public void run() {
                  MainActivity.AlertOn();
                }
              }); 
      } 
    } 
  }
  
  private static void socketBadgeNumberForTrigger(String paramString) {
    if (MainActivity.mainContext != null) {
      String[] arrayOfString = paramString.replaceAll("\\s+", "").split(";");
      if (arrayOfString != null && arrayOfString.length > 1) {
        String str1;
        paramString = arrayOfString[0];
        if (paramString != null && !paramString.isEmpty() && paramString.equals("3006")) {
          paramString = "53";
          str1 = "3008";
        } else if (paramString != null && !paramString.isEmpty() && paramString.equals("3007")) {
          paramString = "-10";
          str1 = "3009";
        } else {
          return;
        } 
        DBFunctions dBFunctions = new DBFunctions(MainActivity.mainContext);
        onstarApplication onstarApplication = (onstarApplication)GlobalMembers.contexGlobal;
        String str3 = arrayOfString[1];
        String str4 = arrayOfString[2];
        String str2 = arrayOfString[3];
        sendTriggerAck(str1, str3, str4, onstarApplication.getAccountID(), str2);
        if (str4 != null && !str4.isEmpty() && dBFunctions.alertIdExists(str4))
          return; 
        int i = null.$SwitchMap$com$roadtrack$onstar$Enums$Services[Enums.Services.GetValue(Integer.parseInt(paramString)).ordinal()];
        if (i != 2) {
          if (i != 3)
            return; 
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("");
          stringBuilder.append("8");
          dBFunctions.addNotificationDTC(stringBuilder.toString(), "", str2, str4, str3, "", "", "", 208);
          if (str3.equals(Utilities.getLastKnownDeviceSelected(onstarApplication, "SocketListener").getDeviceId()))
            GlobalMembers.notificacionesDTC++; 
          if (Utilities.isActivityRunning(MainActivity.mainContext, RemoteDiagnosticActivity.class) && str3.equals(Utilities.getLastKnownDeviceSelected(onstarApplication, "SocketListener").getDeviceId())) {
            RemoteDiagnosticActivity.setSwipeGesture(Boolean.valueOf(false));
            ((RemoteDiagnosticActivity)RemoteDiagnosticActivity._activity).swipeRefreshListener.onRefresh();
          } else {
            RemoteDiagnosticActivity.executeGetDtcs_Task(MainActivity.mainContext, str3);
          } 
        } 
        if (Utilities.isActivityRunning(MainActivity.mainContext, MainActivity.class))
          ((Activity)MainActivity.mainContext).runOnUiThread(new Runnable() {
                public void run() {
                  Utilities.escribeArchivo("SocketListener", "Punto Rojo Socket", "punto rojo en main");
                  MainActivity.AlertOn();
                }
              }); 
        if (PIDActivity._context != null && Utilities.isActivityRunning(MainActivity.mainContext, PIDActivity.class))
          ((Activity)PIDActivity._context).runOnUiThread(new Runnable() {
                public void run() {
                  Utilities.escribeArchivo("SocketListener", "Punto Rojo socket", "punto rojo en PID");
                  Activity activity = PIDActivity.Act;
                  if (activity != null)
                    ((PIDActivity)activity).AlertOn(); 
                }
              }); 
      } 
    } 
  }
  
  private static void socketDTCSelector(final String socketDeviceId) {
    if (MainActivity.mainContext != null) {
      String[] arrayOfString = socketDeviceId.replaceAll("\\s+", "").split(";");
      if (arrayOfString != null && arrayOfString.length > 1) {
        socketDeviceId = arrayOfString[0];
        if (socketDeviceId != null && !socketDeviceId.isEmpty() && socketDeviceId.equals("3010")) {
          new DBFunctions(MainActivity.mainContext);
          onstarApplication onstarApplication = (onstarApplication)GlobalMembers.contexGlobal;
          socketDeviceId = arrayOfString[1];
          String str3 = arrayOfString[2];
          final String statusAlertDTC = arrayOfString[3];
          final String deviceIdActived = Utilities.getLastKnownDeviceSelected(onstarApplication, "SocketListener").getDeviceId();
          if (str1 != null)
            if (Utilities.isActivityRunning(MainActivity.mainContext, PIDActivity.class)) {
              ((Activity)MainActivity.mainContext).runOnUiThread(new Runnable() {
                    final String val$deviceIdActived;
                    
                    final String val$socketDeviceId;
                    
                    final String val$statusAlertDTC;
                    
                    public void run() {
                      if (deviceIdActived.equals(socketDeviceId)) {
                        Activity activity = PIDActivity.Act;
                        if (activity != null) {
                          PIDActivity pIDActivity = (PIDActivity)activity;
                          if (!statusAlertDTC.equals("0")) {
                            pIDActivity.ChangedStatusSocketSwitch(statusAlertDTC);
                          } else {
                            pIDActivity.ChangedStatusSocketSwitch(statusAlertDTC);
                          } 
                        } 
                      } 
                    }
                  });
            } else if (Utilities.isActivityRunning(MainActivity.mainContext, RemoteDiagnosticActivity.class)) {
              ((Activity)MainActivity.mainContext).runOnUiThread(new Runnable() {
                    final String val$statusAlertDTC;
                    
                    public void run() {
                      if (!statusAlertDTC.equals("0")) {
                        Activity activity = RemoteDiagnosticActivity._activity;
                        if (activity != null)
                          ((RemoteDiagnosticActivity)activity).ChangedStatusSocketSwitch(statusAlertDTC); 
                      } else {
                        Activity activity = RemoteDiagnosticActivity._activity;
                        if (activity != null)
                          ((RemoteDiagnosticActivity)activity).ChangedStatusSocketSwitch(statusAlertDTC); 
                      } 
                    }
                  });
            }  
        } 
      } 
    } 
  }
  
  private static void socketFindMe(String paramString1, Socket paramSocket, String paramString2) {
    boolean bool = GlobalMembers.tempSocketResponse;
    String[] arrayOfString = paramString1.split(";");
    String str = arrayOfString[0];
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PI:3013,");
    stringBuilder.append(Utilities.getLastKnownDeviceSelected((onstarApplication)GlobalMembers.contexGlobal, "SocketListener").getDeviceId());
    if (str.contains(stringBuilder.toString()) && arrayOfString[1].toString().equals(GlobalMembers.messsageIdGlobal)) {
      String str1 = Utilities.getLastKnownDeviceSelected((onstarApplication)GlobalMembers.contexGlobal, "SocketListener").getDeviceId();
      String str2 = Utilities.getDeviceSerialNumberByDeviceId(str1);
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("ServiceMobileApp:3014,");
      stringBuilder1.append(str2);
      stringBuilder1.append(str1);
      stringBuilder1.append(",");
      stringBuilder1.append(paramString2);
      stringBuilder1.append(",");
      stringBuilder1.append(GlobalMembers.messsageIdGlobal);
      stringBuilder1.append(";");
      paramString2 = stringBuilder1.toString();
      try {
        BufferedWriter bufferedWriter = new BufferedWriter();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter();
        this(paramSocket.getOutputStream());
        this(outputStreamWriter);
        out = bufferedWriter;
        out.write(paramString2);
        out.flush();
      } catch (IOException iOException) {
        Utilities.escribeArchivo("SocketListener", "IOException", "IOException");
      } 
      new DBFunctions(GlobalMembers.contexGlobal);
      if (arrayOfString[2].equals("7") && arrayOfString[1].toString().equals(GlobalMembers.messsageIdGlobal) && !GlobalMembers.processMessageId)
        MainActivity.showBlurView(); 
    } 
  }
  
  private static void socketRenewalPaymentResult(String paramString) {
    if (MainActivity.mainContext != null) {
      String[] arrayOfString = paramString.replaceAll("\\s+", "").split(";");
      if (arrayOfString != null && arrayOfString.length > 1) {
        String str = arrayOfString[0];
        if (str != null && !str.isEmpty() && str.equals("3012")) {
          new DBFunctions(MainActivity.mainContext);
          onstarApplication onstarApplication = (onstarApplication)GlobalMembers.contexGlobal;
          String str2 = arrayOfString[1];
          String str1 = arrayOfString[2];
          if (!Utilities.isActivityRunning(MainActivity.mainContext, LoginActivity.class) && !Utilities.isActivityRunning(MainActivity.mainContext, TransparentActivityExpirationDialog.class)) {
            Intent intent = new Intent(MainActivity.mainContext, TransparentActivityExpirationDialog.class);
            MainActivity.mainContext.startActivity(intent);
          } 
        } 
      } 
    } 
  }
  
  private static void socketUpdateDeviceGmtValue(String paramString) {
    if (MainActivity.mainContext != null) {
      String[] arrayOfString = paramString.replaceAll("\\s+", "").split(";");
      if (arrayOfString != null && arrayOfString.length > 1) {
        paramString = arrayOfString[0];
        if (paramString != null && !paramString.isEmpty() && paramString.equals("3011")) {
          DBFunctions dBFunctions = new DBFunctions(MainActivity.mainContext);
          onstarApplication onstarApplication = (onstarApplication)GlobalMembers.contexGlobal;
          String str1 = arrayOfString[1];
          String str2 = arrayOfString[3];
          try {
            ArrayList<GMTCatalog> arrayList = dBFunctions.getGmtCatalogRegisterByGmtId(str2);
            if (str1.equals("0")) {
              for (byte b = 0; b < onstarApplication.getmDeviceUserList().size(); b++) {
                String str = ((UserDevicesVO)onstarApplication.getmDeviceUserList().get(b)).getDeviceId().toString();
                dBFunctions.UpdateDateHistoryAndPush(str, ((GMTCatalog)arrayList.get(0)).getGmtValue());
                dBFunctions.updateVehicleGmtValue(str, ((GMTCatalog)arrayList.get(0)).getGmtValue(), str2);
              } 
            } else {
              dBFunctions.UpdateDateHistoryAndPush(str1, ((GMTCatalog)arrayList.get(0)).getGmtValue());
              dBFunctions.updateVehicleGmtValue(str1, ((GMTCatalog)arrayList.get(0)).getGmtValue(), str2);
            } 
            if (Utilities.isActivityRunning(MainActivity.mainContext, SettingsNewActivity.class) && SettingsNewActivity.mActivity != null) {
              SettingsNewActivity settingsNewActivity = (SettingsNewActivity)SettingsNewActivity.mActivity;
              Activity activity = SettingsNewActivity.mActivity;
              Runnable runnable = new Runnable() {
                  final SettingsNewActivity val$activity;
                  
                  final ArrayList val$gmtRegister;
                  
                  final onstarApplication val$rtApp;
                  
                  final String val$socketDeviceId;
                  
                  public void run() {
                    if (socketDeviceId.equals(Utilities.getLastKnownDeviceSelected(rtApp, "SocketListener").getDeviceId())) {
                      SettingsNewActivity settingsNewActivity = activity;
                      StringBuilder stringBuilder = new StringBuilder();
                      stringBuilder.append("GMT ");
                      stringBuilder.append(Utilities.FormatHour(Float.parseFloat(((GMTCatalog)gmtRegister.get(0)).getGmtValue())));
                      settingsNewActivity.setStringGmt(stringBuilder.toString());
                    } else if (socketDeviceId.equals("0")) {
                      SettingsNewActivity settingsNewActivity = activity;
                      StringBuilder stringBuilder = new StringBuilder();
                      stringBuilder.append("GMT ");
                      stringBuilder.append(Utilities.FormatHour(Float.parseFloat(((GMTCatalog)gmtRegister.get(0)).getGmtValue())));
                      settingsNewActivity.setStringGmt(stringBuilder.toString());
                    } 
                  }
                };
              super(str1, onstarApplication, settingsNewActivity, arrayList);
              activity.runOnUiThread(runnable);
            } 
          } catch (Exception exception) {}
        } 
      } 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/SocketListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */