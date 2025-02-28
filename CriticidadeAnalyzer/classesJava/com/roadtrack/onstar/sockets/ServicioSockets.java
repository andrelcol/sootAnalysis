package com.roadtrack.onstar.sockets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.utils.Utilities;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServicioSockets extends Service {
  private static boolean bHiloEnEjecucion = false;
  
  private boolean bTiempoMenorA30Seg = true;
  
  private int iContador2 = 0;
  
  BufferedWriter out = null;
  
  private String response1 = "";
  
  private Socket socket = null;
  
  private String strPassword_ = "";
  
  private String strToken = "";
  
  private String strUser_ = "";
  
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
      Utilities.escribeArchivo("ServicioSockets", "Error: ConectaSocket.UnknownHostException", unknownHostException.getMessage());
      bool = false;
    } catch (IOException iOException) {
      Utilities.escribeArchivo("ServicioSockets", "Error: ConectaSocket.IOException", iOException.getMessage());
    } 
    return bool;
  }
  
  private boolean EscuchaRespuesta() {
    // Byte code:
    //   0: aload_0
    //   1: getfield socket : Ljava/net/Socket;
    //   4: ifnull -> 1204
    //   7: aload_0
    //   8: getfield socket : Ljava/net/Socket;
    //   11: invokevirtual isConnected : ()Z
    //   14: ifeq -> 1204
    //   17: new java/lang/Thread
    //   20: astore #6
    //   22: new com/roadtrack/onstar/sockets/ServicioSockets$TimerThread
    //   25: astore #5
    //   27: aload #5
    //   29: aload_0
    //   30: aconst_null
    //   31: invokespecial <init> : (Lcom/roadtrack/onstar/sockets/ServicioSockets;Lcom/roadtrack/onstar/sockets/ServicioSockets$1;)V
    //   34: aload #6
    //   36: aload #5
    //   38: invokespecial <init> : (Ljava/lang/Runnable;)V
    //   41: aload #6
    //   43: invokevirtual start : ()V
    //   46: new java/io/BufferedWriter
    //   49: astore #6
    //   51: new java/io/OutputStreamWriter
    //   54: astore #5
    //   56: aload #5
    //   58: aload_0
    //   59: getfield socket : Ljava/net/Socket;
    //   62: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
    //   65: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   68: aload #6
    //   70: aload #5
    //   72: invokespecial <init> : (Ljava/io/Writer;)V
    //   75: aload_0
    //   76: aload #6
    //   78: putfield out : Ljava/io/BufferedWriter;
    //   81: new java/lang/StringBuilder
    //   84: astore #5
    //   86: aload #5
    //   88: invokespecial <init> : ()V
    //   91: aload #5
    //   93: ldc 'AC:'
    //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: aload #5
    //   101: aload_0
    //   102: getfield strToken : Ljava/lang/String;
    //   105: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: pop
    //   109: aload #5
    //   111: invokevirtual toString : ()Ljava/lang/String;
    //   114: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   117: astore #6
    //   119: aload_0
    //   120: getfield out : Ljava/io/BufferedWriter;
    //   123: aload #6
    //   125: invokevirtual write : (Ljava/lang/String;)V
    //   128: new java/lang/StringBuilder
    //   131: astore #5
    //   133: aload #5
    //   135: invokespecial <init> : ()V
    //   138: aload #5
    //   140: ldc 'SIZE: '
    //   142: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: aload #5
    //   148: aload #6
    //   150: invokevirtual length : ()I
    //   153: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   156: pop
    //   157: ldc 'ServicioSockets'
    //   159: ldc 'EscuchaRespuesta: DATA_REMOTE_SERVICES WRITE SOCKET'
    //   161: aload #5
    //   163: invokevirtual toString : ()Ljava/lang/String;
    //   166: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   169: aload_0
    //   170: getfield out : Ljava/io/BufferedWriter;
    //   173: invokevirtual flush : ()V
    //   176: aload_0
    //   177: getfield out : Ljava/io/BufferedWriter;
    //   180: astore #5
    //   182: aload #5
    //   184: ifnonnull -> 428
    //   187: aload_0
    //   188: getfield out : Ljava/io/BufferedWriter;
    //   191: invokevirtual close : ()V
    //   194: aload_0
    //   195: getfield socket : Ljava/net/Socket;
    //   198: astore #5
    //   200: aload #5
    //   202: ifnonnull -> 428
    //   205: aload_0
    //   206: getfield socket : Ljava/net/Socket;
    //   209: invokevirtual close : ()V
    //   212: goto -> 428
    //   215: astore #5
    //   217: ldc 'ServicioSockets'
    //   219: ldc 'Error'
    //   221: ldc 'IO'
    //   223: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   226: goto -> 428
    //   229: astore #5
    //   231: goto -> 266
    //   234: astore #5
    //   236: ldc 'ServicioSockets'
    //   238: ldc 'Error'
    //   240: ldc 'IOException'
    //   242: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   245: aload_0
    //   246: getfield socket : Ljava/net/Socket;
    //   249: astore #5
    //   251: aload #5
    //   253: ifnonnull -> 428
    //   256: aload_0
    //   257: getfield socket : Ljava/net/Socket;
    //   260: invokevirtual close : ()V
    //   263: goto -> 428
    //   266: aload_0
    //   267: getfield socket : Ljava/net/Socket;
    //   270: astore #6
    //   272: aload #6
    //   274: ifnonnull -> 298
    //   277: aload_0
    //   278: getfield socket : Ljava/net/Socket;
    //   281: invokevirtual close : ()V
    //   284: goto -> 298
    //   287: astore #6
    //   289: ldc 'ServicioSockets'
    //   291: ldc 'Error'
    //   293: ldc 'IO'
    //   295: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   298: aload #5
    //   300: athrow
    //   301: astore #5
    //   303: goto -> 1076
    //   306: astore #5
    //   308: ldc 'ServicioSockets'
    //   310: ldc 'Error'
    //   312: ldc 'IOException'
    //   314: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   317: aload_0
    //   318: getfield out : Ljava/io/BufferedWriter;
    //   321: astore #5
    //   323: aload #5
    //   325: ifnonnull -> 428
    //   328: aload_0
    //   329: getfield out : Ljava/io/BufferedWriter;
    //   332: invokevirtual close : ()V
    //   335: aload_0
    //   336: getfield socket : Ljava/net/Socket;
    //   339: astore #5
    //   341: aload #5
    //   343: ifnonnull -> 428
    //   346: aload_0
    //   347: getfield socket : Ljava/net/Socket;
    //   350: invokevirtual close : ()V
    //   353: goto -> 428
    //   356: astore #5
    //   358: goto -> 393
    //   361: astore #5
    //   363: ldc 'ServicioSockets'
    //   365: ldc 'Error'
    //   367: ldc 'IOException'
    //   369: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   372: aload_0
    //   373: getfield socket : Ljava/net/Socket;
    //   376: astore #5
    //   378: aload #5
    //   380: ifnonnull -> 428
    //   383: aload_0
    //   384: getfield socket : Ljava/net/Socket;
    //   387: invokevirtual close : ()V
    //   390: goto -> 428
    //   393: aload_0
    //   394: getfield socket : Ljava/net/Socket;
    //   397: astore #6
    //   399: aload #6
    //   401: ifnonnull -> 425
    //   404: aload_0
    //   405: getfield socket : Ljava/net/Socket;
    //   408: invokevirtual close : ()V
    //   411: goto -> 425
    //   414: astore #6
    //   416: ldc 'ServicioSockets'
    //   418: ldc 'Error'
    //   420: ldc 'IO'
    //   422: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   425: aload #5
    //   427: athrow
    //   428: new java/lang/StringBuilder
    //   431: astore #5
    //   433: aload #5
    //   435: invokespecial <init> : ()V
    //   438: aload #5
    //   440: ldc 'ServiceMobileApp:1015,'
    //   442: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: pop
    //   446: aload #5
    //   448: aload_0
    //   449: getfield strUser_ : Ljava/lang/String;
    //   452: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   455: pop
    //   456: aload #5
    //   458: ldc ','
    //   460: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: pop
    //   464: aload #5
    //   466: aload_0
    //   467: getfield strPassword_ : Ljava/lang/String;
    //   470: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: pop
    //   474: aload #5
    //   476: ldc ','
    //   478: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   481: pop
    //   482: aload #5
    //   484: bipush #13
    //   486: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   489: pop
    //   490: aload #5
    //   492: ldc ','
    //   494: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   497: pop
    //   498: aload #5
    //   500: aload_0
    //   501: getfield strToken : Ljava/lang/String;
    //   504: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: pop
    //   508: aload #5
    //   510: invokevirtual toString : ()Ljava/lang/String;
    //   513: invokestatic Crypt : (Ljava/lang/String;)Ljava/lang/String;
    //   516: astore #6
    //   518: aload_0
    //   519: getfield out : Ljava/io/BufferedWriter;
    //   522: aload #6
    //   524: invokevirtual write : (Ljava/lang/String;)V
    //   527: new java/lang/StringBuilder
    //   530: astore #5
    //   532: aload #5
    //   534: invokespecial <init> : ()V
    //   537: aload #5
    //   539: ldc 'SIZE: '
    //   541: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   544: pop
    //   545: aload #5
    //   547: aload #6
    //   549: invokevirtual length : ()I
    //   552: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   555: pop
    //   556: ldc 'ServicioSockets'
    //   558: ldc 'EscuchaRespuesta: DATA_REMOTE_SERVICES WRITE SOCKET'
    //   560: aload #5
    //   562: invokevirtual toString : ()Ljava/lang/String;
    //   565: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   568: aload_0
    //   569: getfield out : Ljava/io/BufferedWriter;
    //   572: invokevirtual flush : ()V
    //   575: aload_0
    //   576: getfield out : Ljava/io/BufferedWriter;
    //   579: astore #5
    //   581: aload #5
    //   583: ifnonnull -> 827
    //   586: aload_0
    //   587: getfield out : Ljava/io/BufferedWriter;
    //   590: invokevirtual close : ()V
    //   593: aload_0
    //   594: getfield socket : Ljava/net/Socket;
    //   597: astore #5
    //   599: aload #5
    //   601: ifnonnull -> 827
    //   604: aload_0
    //   605: getfield socket : Ljava/net/Socket;
    //   608: invokevirtual close : ()V
    //   611: goto -> 827
    //   614: astore #5
    //   616: ldc 'ServicioSockets'
    //   618: ldc 'Error'
    //   620: ldc 'IOException'
    //   622: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   625: goto -> 827
    //   628: astore #5
    //   630: goto -> 665
    //   633: astore #5
    //   635: ldc 'ServicioSockets'
    //   637: ldc 'Error'
    //   639: ldc 'IOException'
    //   641: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   644: aload_0
    //   645: getfield socket : Ljava/net/Socket;
    //   648: astore #5
    //   650: aload #5
    //   652: ifnonnull -> 827
    //   655: aload_0
    //   656: getfield socket : Ljava/net/Socket;
    //   659: invokevirtual close : ()V
    //   662: goto -> 827
    //   665: aload_0
    //   666: getfield socket : Ljava/net/Socket;
    //   669: astore #6
    //   671: aload #6
    //   673: ifnonnull -> 697
    //   676: aload_0
    //   677: getfield socket : Ljava/net/Socket;
    //   680: invokevirtual close : ()V
    //   683: goto -> 697
    //   686: astore #6
    //   688: ldc 'ServicioSockets'
    //   690: ldc 'Error'
    //   692: ldc 'IOException'
    //   694: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   697: aload #5
    //   699: athrow
    //   700: astore #5
    //   702: goto -> 948
    //   705: astore #5
    //   707: ldc 'ServicioSockets'
    //   709: ldc 'Error'
    //   711: ldc 'IOException'
    //   713: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   716: aload_0
    //   717: getfield out : Ljava/io/BufferedWriter;
    //   720: astore #5
    //   722: aload #5
    //   724: ifnonnull -> 827
    //   727: aload_0
    //   728: getfield out : Ljava/io/BufferedWriter;
    //   731: invokevirtual close : ()V
    //   734: aload_0
    //   735: getfield socket : Ljava/net/Socket;
    //   738: astore #5
    //   740: aload #5
    //   742: ifnonnull -> 827
    //   745: aload_0
    //   746: getfield socket : Ljava/net/Socket;
    //   749: invokevirtual close : ()V
    //   752: goto -> 827
    //   755: astore #5
    //   757: goto -> 792
    //   760: astore #5
    //   762: ldc 'ServicioSockets'
    //   764: ldc 'Error'
    //   766: ldc 'IOException'
    //   768: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   771: aload_0
    //   772: getfield socket : Ljava/net/Socket;
    //   775: astore #5
    //   777: aload #5
    //   779: ifnonnull -> 827
    //   782: aload_0
    //   783: getfield socket : Ljava/net/Socket;
    //   786: invokevirtual close : ()V
    //   789: goto -> 827
    //   792: aload_0
    //   793: getfield socket : Ljava/net/Socket;
    //   796: astore #6
    //   798: aload #6
    //   800: ifnonnull -> 824
    //   803: aload_0
    //   804: getfield socket : Ljava/net/Socket;
    //   807: invokevirtual close : ()V
    //   810: goto -> 824
    //   813: astore #6
    //   815: ldc 'ServicioSockets'
    //   817: ldc 'Error'
    //   819: ldc 'IOException'
    //   821: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   824: aload #5
    //   826: athrow
    //   827: new java/io/ByteArrayOutputStream
    //   830: astore #7
    //   832: aload #7
    //   834: sipush #8192
    //   837: invokespecial <init> : (I)V
    //   840: sipush #8192
    //   843: newarray byte
    //   845: astore #6
    //   847: aload_0
    //   848: getfield socket : Ljava/net/Socket;
    //   851: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   854: astore #5
    //   856: iconst_0
    //   857: istore_1
    //   858: iload_1
    //   859: istore_2
    //   860: iload_1
    //   861: istore_3
    //   862: iload_1
    //   863: istore #4
    //   865: aload_0
    //   866: getfield bTiempoMenorA30Seg : Z
    //   869: ifeq -> 1206
    //   872: iload_1
    //   873: istore_3
    //   874: iload_1
    //   875: istore #4
    //   877: aload #7
    //   879: aload #6
    //   881: iconst_0
    //   882: aload #5
    //   884: aload #6
    //   886: invokevirtual read : ([B)I
    //   889: invokevirtual write : ([BII)V
    //   892: iload_1
    //   893: istore_3
    //   894: iload_1
    //   895: istore #4
    //   897: aload_0
    //   898: aload #7
    //   900: ldc 'UTF-8'
    //   902: invokevirtual toString : (Ljava/lang/String;)Ljava/lang/String;
    //   905: putfield response1 : Ljava/lang/String;
    //   908: iload_1
    //   909: istore_3
    //   910: iload_1
    //   911: istore #4
    //   913: aload_0
    //   914: aload_0
    //   915: getfield response1 : Ljava/lang/String;
    //   918: invokestatic Decrypt : (Ljava/lang/String;)Ljava/lang/String;
    //   921: putfield response1 : Ljava/lang/String;
    //   924: iload_1
    //   925: istore_3
    //   926: iload_1
    //   927: istore #4
    //   929: aload #7
    //   931: invokevirtual reset : ()V
    //   934: iconst_1
    //   935: istore_3
    //   936: iconst_1
    //   937: istore #4
    //   939: iconst_1
    //   940: istore_1
    //   941: aload_0
    //   942: invokevirtual stopSelf : ()V
    //   945: goto -> 858
    //   948: aload_0
    //   949: getfield out : Ljava/io/BufferedWriter;
    //   952: astore #6
    //   954: aload #6
    //   956: ifnonnull -> 1073
    //   959: aload_0
    //   960: getfield out : Ljava/io/BufferedWriter;
    //   963: invokevirtual close : ()V
    //   966: aload_0
    //   967: getfield socket : Ljava/net/Socket;
    //   970: astore #6
    //   972: aload #6
    //   974: ifnonnull -> 1073
    //   977: aload_0
    //   978: getfield socket : Ljava/net/Socket;
    //   981: invokevirtual close : ()V
    //   984: goto -> 1073
    //   987: astore #6
    //   989: ldc 'ServicioSockets'
    //   991: ldc 'Error'
    //   993: ldc 'IOException'
    //   995: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   998: goto -> 1073
    //   1001: astore #5
    //   1003: goto -> 1038
    //   1006: astore #6
    //   1008: ldc 'ServicioSockets'
    //   1010: ldc 'Error'
    //   1012: ldc 'IOException'
    //   1014: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1017: aload_0
    //   1018: getfield socket : Ljava/net/Socket;
    //   1021: astore #6
    //   1023: aload #6
    //   1025: ifnonnull -> 1073
    //   1028: aload_0
    //   1029: getfield socket : Ljava/net/Socket;
    //   1032: invokevirtual close : ()V
    //   1035: goto -> 1073
    //   1038: aload_0
    //   1039: getfield socket : Ljava/net/Socket;
    //   1042: astore #6
    //   1044: aload #6
    //   1046: ifnonnull -> 1070
    //   1049: aload_0
    //   1050: getfield socket : Ljava/net/Socket;
    //   1053: invokevirtual close : ()V
    //   1056: goto -> 1070
    //   1059: astore #6
    //   1061: ldc 'ServicioSockets'
    //   1063: ldc 'Error'
    //   1065: ldc 'IOException'
    //   1067: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1070: aload #5
    //   1072: athrow
    //   1073: aload #5
    //   1075: athrow
    //   1076: aload_0
    //   1077: getfield out : Ljava/io/BufferedWriter;
    //   1080: astore #6
    //   1082: aload #6
    //   1084: ifnonnull -> 1201
    //   1087: aload_0
    //   1088: getfield out : Ljava/io/BufferedWriter;
    //   1091: invokevirtual close : ()V
    //   1094: aload_0
    //   1095: getfield socket : Ljava/net/Socket;
    //   1098: astore #6
    //   1100: aload #6
    //   1102: ifnonnull -> 1201
    //   1105: aload_0
    //   1106: getfield socket : Ljava/net/Socket;
    //   1109: invokevirtual close : ()V
    //   1112: goto -> 1201
    //   1115: astore #6
    //   1117: ldc 'ServicioSockets'
    //   1119: ldc 'Error'
    //   1121: ldc 'IO'
    //   1123: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1126: goto -> 1201
    //   1129: astore #5
    //   1131: goto -> 1166
    //   1134: astore #6
    //   1136: ldc 'ServicioSockets'
    //   1138: ldc 'Error'
    //   1140: ldc 'IOException'
    //   1142: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1145: aload_0
    //   1146: getfield socket : Ljava/net/Socket;
    //   1149: astore #6
    //   1151: aload #6
    //   1153: ifnonnull -> 1201
    //   1156: aload_0
    //   1157: getfield socket : Ljava/net/Socket;
    //   1160: invokevirtual close : ()V
    //   1163: goto -> 1201
    //   1166: aload_0
    //   1167: getfield socket : Ljava/net/Socket;
    //   1170: astore #6
    //   1172: aload #6
    //   1174: ifnonnull -> 1198
    //   1177: aload_0
    //   1178: getfield socket : Ljava/net/Socket;
    //   1181: invokevirtual close : ()V
    //   1184: goto -> 1198
    //   1187: astore #6
    //   1189: ldc 'ServicioSockets'
    //   1191: ldc 'Error'
    //   1193: ldc 'IO'
    //   1195: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1198: aload #5
    //   1200: athrow
    //   1201: aload #5
    //   1203: athrow
    //   1204: iconst_0
    //   1205: istore_2
    //   1206: aload_0
    //   1207: getfield out : Ljava/io/BufferedWriter;
    //   1210: astore #5
    //   1212: iload_2
    //   1213: istore_1
    //   1214: aload #5
    //   1216: ifnonnull -> 1575
    //   1219: aload #5
    //   1221: invokevirtual close : ()V
    //   1224: aload_0
    //   1225: getfield socket : Ljava/net/Socket;
    //   1228: astore #5
    //   1230: iload_2
    //   1231: istore_1
    //   1232: aload #5
    //   1234: ifnonnull -> 1575
    //   1237: iload_2
    //   1238: istore_1
    //   1239: aload #5
    //   1241: invokevirtual close : ()V
    //   1244: goto -> 1575
    //   1247: astore #5
    //   1249: ldc 'ServicioSockets'
    //   1251: ldc 'Error'
    //   1253: ldc 'IOException'
    //   1255: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1258: goto -> 1575
    //   1261: astore #5
    //   1263: goto -> 1295
    //   1266: astore #5
    //   1268: ldc 'ServicioSockets'
    //   1270: ldc 'Error'
    //   1272: ldc 'IOException'
    //   1274: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1277: aload_0
    //   1278: getfield socket : Ljava/net/Socket;
    //   1281: astore #5
    //   1283: iload_2
    //   1284: istore_1
    //   1285: aload #5
    //   1287: ifnonnull -> 1575
    //   1290: iload_2
    //   1291: istore_1
    //   1292: goto -> 1239
    //   1295: aload_0
    //   1296: getfield socket : Ljava/net/Socket;
    //   1299: astore #6
    //   1301: aload #6
    //   1303: ifnonnull -> 1325
    //   1306: aload #6
    //   1308: invokevirtual close : ()V
    //   1311: goto -> 1325
    //   1314: astore #6
    //   1316: ldc 'ServicioSockets'
    //   1318: ldc 'Error'
    //   1320: ldc 'IOException'
    //   1322: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1325: aload #5
    //   1327: athrow
    //   1328: astore #6
    //   1330: goto -> 1577
    //   1333: astore #5
    //   1335: iconst_0
    //   1336: istore_2
    //   1337: ldc 'ServicioSockets'
    //   1339: ldc 'Error: EscuchaRespuesta.IOException'
    //   1341: ldc 'IOException'
    //   1343: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1346: aload_0
    //   1347: iconst_0
    //   1348: putfield iContador2 : I
    //   1351: aload_0
    //   1352: getfield out : Ljava/io/BufferedWriter;
    //   1355: astore #5
    //   1357: iload_2
    //   1358: istore_1
    //   1359: aload #5
    //   1361: ifnonnull -> 1575
    //   1364: aload #5
    //   1366: invokevirtual close : ()V
    //   1369: aload_0
    //   1370: getfield socket : Ljava/net/Socket;
    //   1373: astore #5
    //   1375: iload_2
    //   1376: istore_1
    //   1377: aload #5
    //   1379: ifnonnull -> 1575
    //   1382: iload_2
    //   1383: istore_1
    //   1384: goto -> 1239
    //   1387: astore #5
    //   1389: goto -> 1421
    //   1392: astore #5
    //   1394: ldc 'ServicioSockets'
    //   1396: ldc 'Error'
    //   1398: ldc 'IOException'
    //   1400: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1403: aload_0
    //   1404: getfield socket : Ljava/net/Socket;
    //   1407: astore #5
    //   1409: iload_2
    //   1410: istore_1
    //   1411: aload #5
    //   1413: ifnonnull -> 1575
    //   1416: iload_2
    //   1417: istore_1
    //   1418: goto -> 1239
    //   1421: aload_0
    //   1422: getfield socket : Ljava/net/Socket;
    //   1425: astore #6
    //   1427: aload #6
    //   1429: ifnonnull -> 1451
    //   1432: aload #6
    //   1434: invokevirtual close : ()V
    //   1437: goto -> 1451
    //   1440: astore #6
    //   1442: ldc 'ServicioSockets'
    //   1444: ldc 'Error'
    //   1446: ldc 'IOException'
    //   1448: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1451: aload #5
    //   1453: athrow
    //   1454: astore #5
    //   1456: iconst_0
    //   1457: istore_2
    //   1458: ldc 'ServicioSockets'
    //   1460: ldc 'Error: EscuchaRespuesta.UnknownHostException'
    //   1462: ldc 'UnknowHostException'
    //   1464: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1467: aload_0
    //   1468: iconst_0
    //   1469: putfield iContador2 : I
    //   1472: aload_0
    //   1473: getfield out : Ljava/io/BufferedWriter;
    //   1476: astore #5
    //   1478: iload_2
    //   1479: istore_1
    //   1480: aload #5
    //   1482: ifnonnull -> 1575
    //   1485: aload #5
    //   1487: invokevirtual close : ()V
    //   1490: aload_0
    //   1491: getfield socket : Ljava/net/Socket;
    //   1494: astore #5
    //   1496: iload_2
    //   1497: istore_1
    //   1498: aload #5
    //   1500: ifnonnull -> 1575
    //   1503: iload_2
    //   1504: istore_1
    //   1505: goto -> 1239
    //   1508: astore #5
    //   1510: goto -> 1542
    //   1513: astore #5
    //   1515: ldc 'ServicioSockets'
    //   1517: ldc 'Error'
    //   1519: ldc 'IOException'
    //   1521: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1524: aload_0
    //   1525: getfield socket : Ljava/net/Socket;
    //   1528: astore #5
    //   1530: iload_2
    //   1531: istore_1
    //   1532: aload #5
    //   1534: ifnonnull -> 1575
    //   1537: iload_2
    //   1538: istore_1
    //   1539: goto -> 1239
    //   1542: aload_0
    //   1543: getfield socket : Ljava/net/Socket;
    //   1546: astore #6
    //   1548: aload #6
    //   1550: ifnonnull -> 1572
    //   1553: aload #6
    //   1555: invokevirtual close : ()V
    //   1558: goto -> 1572
    //   1561: astore #6
    //   1563: ldc 'ServicioSockets'
    //   1565: ldc 'Error'
    //   1567: ldc 'IOException'
    //   1569: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1572: aload #5
    //   1574: athrow
    //   1575: iload_1
    //   1576: ireturn
    //   1577: aload_0
    //   1578: getfield out : Ljava/io/BufferedWriter;
    //   1581: astore #5
    //   1583: aload #5
    //   1585: ifnonnull -> 1689
    //   1588: aload #5
    //   1590: invokevirtual close : ()V
    //   1593: aload_0
    //   1594: getfield socket : Ljava/net/Socket;
    //   1597: astore #5
    //   1599: aload #5
    //   1601: ifnonnull -> 1689
    //   1604: aload #5
    //   1606: invokevirtual close : ()V
    //   1609: goto -> 1689
    //   1612: astore #5
    //   1614: ldc 'ServicioSockets'
    //   1616: ldc 'Error'
    //   1618: ldc 'IOException'
    //   1620: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1623: goto -> 1689
    //   1626: astore #5
    //   1628: goto -> 1656
    //   1631: astore #5
    //   1633: ldc 'ServicioSockets'
    //   1635: ldc 'Error'
    //   1637: ldc 'IOException'
    //   1639: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1642: aload_0
    //   1643: getfield socket : Ljava/net/Socket;
    //   1646: astore #5
    //   1648: aload #5
    //   1650: ifnonnull -> 1689
    //   1653: goto -> 1604
    //   1656: aload_0
    //   1657: getfield socket : Ljava/net/Socket;
    //   1660: astore #6
    //   1662: aload #6
    //   1664: ifnonnull -> 1686
    //   1667: aload #6
    //   1669: invokevirtual close : ()V
    //   1672: goto -> 1686
    //   1675: astore #6
    //   1677: ldc 'ServicioSockets'
    //   1679: ldc 'Error'
    //   1681: ldc 'IOException'
    //   1683: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1686: aload #5
    //   1688: athrow
    //   1689: aload #6
    //   1691: athrow
    //   1692: astore #5
    //   1694: iload_3
    //   1695: istore_2
    //   1696: goto -> 1458
    //   1699: astore #5
    //   1701: iload #4
    //   1703: istore_2
    //   1704: goto -> 1337
    // Exception table:
    //   from	to	target	type
    //   0	119	1454	java/net/UnknownHostException
    //   0	119	1333	java/io/IOException
    //   0	119	1328	finally
    //   119	176	306	java/lang/Exception
    //   119	176	301	finally
    //   176	182	1454	java/net/UnknownHostException
    //   176	182	1333	java/io/IOException
    //   176	182	1328	finally
    //   187	194	234	java/io/IOException
    //   187	194	229	finally
    //   194	200	1454	java/net/UnknownHostException
    //   194	200	1333	java/io/IOException
    //   194	200	1328	finally
    //   205	212	215	java/io/IOException
    //   205	212	1328	finally
    //   217	226	1454	java/net/UnknownHostException
    //   217	226	1333	java/io/IOException
    //   217	226	1328	finally
    //   236	245	229	finally
    //   245	251	1454	java/net/UnknownHostException
    //   245	251	1333	java/io/IOException
    //   245	251	1328	finally
    //   256	263	215	java/io/IOException
    //   256	263	1328	finally
    //   266	272	1454	java/net/UnknownHostException
    //   266	272	1333	java/io/IOException
    //   266	272	1328	finally
    //   277	284	287	java/io/IOException
    //   277	284	1328	finally
    //   289	298	1454	java/net/UnknownHostException
    //   289	298	1333	java/io/IOException
    //   289	298	1328	finally
    //   298	301	1454	java/net/UnknownHostException
    //   298	301	1333	java/io/IOException
    //   298	301	1328	finally
    //   308	317	301	finally
    //   317	323	1454	java/net/UnknownHostException
    //   317	323	1333	java/io/IOException
    //   317	323	1328	finally
    //   328	335	361	java/io/IOException
    //   328	335	356	finally
    //   335	341	1454	java/net/UnknownHostException
    //   335	341	1333	java/io/IOException
    //   335	341	1328	finally
    //   346	353	215	java/io/IOException
    //   346	353	1328	finally
    //   363	372	356	finally
    //   372	378	1454	java/net/UnknownHostException
    //   372	378	1333	java/io/IOException
    //   372	378	1328	finally
    //   383	390	215	java/io/IOException
    //   383	390	1328	finally
    //   393	399	1454	java/net/UnknownHostException
    //   393	399	1333	java/io/IOException
    //   393	399	1328	finally
    //   404	411	414	java/io/IOException
    //   404	411	1328	finally
    //   416	425	1454	java/net/UnknownHostException
    //   416	425	1333	java/io/IOException
    //   416	425	1328	finally
    //   425	428	1454	java/net/UnknownHostException
    //   425	428	1333	java/io/IOException
    //   425	428	1328	finally
    //   428	518	1454	java/net/UnknownHostException
    //   428	518	1333	java/io/IOException
    //   428	518	1328	finally
    //   518	575	705	java/io/IOException
    //   518	575	700	finally
    //   575	581	1454	java/net/UnknownHostException
    //   575	581	1333	java/io/IOException
    //   575	581	1328	finally
    //   586	593	633	java/io/IOException
    //   586	593	628	finally
    //   593	599	1454	java/net/UnknownHostException
    //   593	599	1333	java/io/IOException
    //   593	599	1328	finally
    //   604	611	614	java/io/IOException
    //   604	611	1328	finally
    //   616	625	1454	java/net/UnknownHostException
    //   616	625	1333	java/io/IOException
    //   616	625	1328	finally
    //   635	644	628	finally
    //   644	650	1454	java/net/UnknownHostException
    //   644	650	1333	java/io/IOException
    //   644	650	1328	finally
    //   655	662	614	java/io/IOException
    //   655	662	1328	finally
    //   665	671	1454	java/net/UnknownHostException
    //   665	671	1333	java/io/IOException
    //   665	671	1328	finally
    //   676	683	686	java/io/IOException
    //   676	683	1328	finally
    //   688	697	1454	java/net/UnknownHostException
    //   688	697	1333	java/io/IOException
    //   688	697	1328	finally
    //   697	700	1454	java/net/UnknownHostException
    //   697	700	1333	java/io/IOException
    //   697	700	1328	finally
    //   707	716	700	finally
    //   716	722	1454	java/net/UnknownHostException
    //   716	722	1333	java/io/IOException
    //   716	722	1328	finally
    //   727	734	760	java/io/IOException
    //   727	734	755	finally
    //   734	740	1454	java/net/UnknownHostException
    //   734	740	1333	java/io/IOException
    //   734	740	1328	finally
    //   745	752	614	java/io/IOException
    //   745	752	1328	finally
    //   762	771	755	finally
    //   771	777	1454	java/net/UnknownHostException
    //   771	777	1333	java/io/IOException
    //   771	777	1328	finally
    //   782	789	614	java/io/IOException
    //   782	789	1328	finally
    //   792	798	1454	java/net/UnknownHostException
    //   792	798	1333	java/io/IOException
    //   792	798	1328	finally
    //   803	810	813	java/io/IOException
    //   803	810	1328	finally
    //   815	824	1454	java/net/UnknownHostException
    //   815	824	1333	java/io/IOException
    //   815	824	1328	finally
    //   824	827	1454	java/net/UnknownHostException
    //   824	827	1333	java/io/IOException
    //   824	827	1328	finally
    //   827	856	1454	java/net/UnknownHostException
    //   827	856	1333	java/io/IOException
    //   827	856	1328	finally
    //   865	872	1692	java/net/UnknownHostException
    //   865	872	1699	java/io/IOException
    //   865	872	1328	finally
    //   877	892	1692	java/net/UnknownHostException
    //   877	892	1699	java/io/IOException
    //   877	892	1328	finally
    //   897	908	1692	java/net/UnknownHostException
    //   897	908	1699	java/io/IOException
    //   897	908	1328	finally
    //   913	924	1692	java/net/UnknownHostException
    //   913	924	1699	java/io/IOException
    //   913	924	1328	finally
    //   929	934	1692	java/net/UnknownHostException
    //   929	934	1699	java/io/IOException
    //   929	934	1328	finally
    //   941	945	1692	java/net/UnknownHostException
    //   941	945	1699	java/io/IOException
    //   941	945	1328	finally
    //   948	954	1454	java/net/UnknownHostException
    //   948	954	1333	java/io/IOException
    //   948	954	1328	finally
    //   959	966	1006	java/io/IOException
    //   959	966	1001	finally
    //   966	972	1454	java/net/UnknownHostException
    //   966	972	1333	java/io/IOException
    //   966	972	1328	finally
    //   977	984	987	java/io/IOException
    //   977	984	1328	finally
    //   989	998	1454	java/net/UnknownHostException
    //   989	998	1333	java/io/IOException
    //   989	998	1328	finally
    //   1008	1017	1001	finally
    //   1017	1023	1454	java/net/UnknownHostException
    //   1017	1023	1333	java/io/IOException
    //   1017	1023	1328	finally
    //   1028	1035	987	java/io/IOException
    //   1028	1035	1328	finally
    //   1038	1044	1454	java/net/UnknownHostException
    //   1038	1044	1333	java/io/IOException
    //   1038	1044	1328	finally
    //   1049	1056	1059	java/io/IOException
    //   1049	1056	1328	finally
    //   1061	1070	1454	java/net/UnknownHostException
    //   1061	1070	1333	java/io/IOException
    //   1061	1070	1328	finally
    //   1070	1073	1454	java/net/UnknownHostException
    //   1070	1073	1333	java/io/IOException
    //   1070	1073	1328	finally
    //   1073	1076	1454	java/net/UnknownHostException
    //   1073	1076	1333	java/io/IOException
    //   1073	1076	1328	finally
    //   1076	1082	1454	java/net/UnknownHostException
    //   1076	1082	1333	java/io/IOException
    //   1076	1082	1328	finally
    //   1087	1094	1134	java/io/IOException
    //   1087	1094	1129	finally
    //   1094	1100	1454	java/net/UnknownHostException
    //   1094	1100	1333	java/io/IOException
    //   1094	1100	1328	finally
    //   1105	1112	1115	java/io/IOException
    //   1105	1112	1328	finally
    //   1117	1126	1454	java/net/UnknownHostException
    //   1117	1126	1333	java/io/IOException
    //   1117	1126	1328	finally
    //   1136	1145	1129	finally
    //   1145	1151	1454	java/net/UnknownHostException
    //   1145	1151	1333	java/io/IOException
    //   1145	1151	1328	finally
    //   1156	1163	1115	java/io/IOException
    //   1156	1163	1328	finally
    //   1166	1172	1454	java/net/UnknownHostException
    //   1166	1172	1333	java/io/IOException
    //   1166	1172	1328	finally
    //   1177	1184	1187	java/io/IOException
    //   1177	1184	1328	finally
    //   1189	1198	1454	java/net/UnknownHostException
    //   1189	1198	1333	java/io/IOException
    //   1189	1198	1328	finally
    //   1198	1201	1454	java/net/UnknownHostException
    //   1198	1201	1333	java/io/IOException
    //   1198	1201	1328	finally
    //   1201	1204	1454	java/net/UnknownHostException
    //   1201	1204	1333	java/io/IOException
    //   1201	1204	1328	finally
    //   1219	1224	1266	java/io/IOException
    //   1219	1224	1261	finally
    //   1239	1244	1247	java/io/IOException
    //   1268	1277	1261	finally
    //   1306	1311	1314	java/io/IOException
    //   1337	1351	1328	finally
    //   1364	1369	1392	java/io/IOException
    //   1364	1369	1387	finally
    //   1394	1403	1387	finally
    //   1432	1437	1440	java/io/IOException
    //   1458	1472	1328	finally
    //   1485	1490	1513	java/io/IOException
    //   1485	1490	1508	finally
    //   1515	1524	1508	finally
    //   1553	1558	1561	java/io/IOException
    //   1588	1593	1631	java/io/IOException
    //   1588	1593	1626	finally
    //   1604	1609	1612	java/io/IOException
    //   1633	1642	1626	finally
    //   1667	1672	1675	java/io/IOException
  }
  
  public IBinder onBind(Intent paramIntent) {
    return null;
  }
  
  public void onCreate() {
    super.onCreate();
    this.strUser_ = GlobalMembers.strUser;
    this.strPassword_ = GlobalMembers.strPassword;
    this.iContador2 = 0;
    this.strToken = ((TelephonyManager)getSystemService("phone")).getDeviceId();
  }
  
  public void onDestroy() {
    super.onDestroy();
    this.iContador2 = 99;
    bHiloEnEjecucion = false;
    this.bTiempoMenorA30Seg = false;
    GlobalMembers.bServicioSocketsCorriendo = false;
    try {
      if (this.out != null)
        this.out.close(); 
      if (this.socket != null && this.socket.isConnected())
        this.socket.close(); 
      this.out = null;
      this.socket = null;
    } catch (IOException iOException) {
      Utilities.escribeArchivo("ServicioSockets", "Error: onDestroy", iOException.getMessage());
    } 
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    GlobalMembers.bServicioSocketsCorriendo = true;
    if (!bHiloEnEjecucion)
      (new Thread(new ClientThread())).start(); 
    return 2;
  }
  
  private class ClientThread implements Runnable {
    final ServicioSockets this$0;
    
    private ClientThread() {}
    
    public void run() {
      ServicioSockets.access$102(true);
      GlobalMembers.bRespuestaNoRecibida = false;
      boolean bool = false;
      int i = 0;
      while (!bool) {
        bool = ServicioSockets.this.ConectaSocket(GlobalMembers.strPuertoA);
        if (bool) {
          ServicioSockets.access$302(ServicioSockets.this, 30);
          break;
        } 
        try {
          Thread.sleep(3000L);
          int j = i + 1;
          i = j;
          if (j >= 30)
            break; 
        } catch (InterruptedException interruptedException) {
          ServicioSockets.access$102(false);
          Utilities.escribeArchivo("ServicioSockets", "Error: ClientThread", interruptedException.getMessage());
        } 
      } 
      ServicioSockets.access$102(false);
      ServicioSockets.this.stopSelf();
    }
  }
  
  private class TimerThread implements Runnable {
    final ServicioSockets this$0;
    
    private TimerThread() {}
    
    public void run() {
      ServicioSockets.access$502(ServicioSockets.this, true);
      while (ServicioSockets.this.iContador2 < 30) {
        try {
          Thread.sleep(1000L);
          ServicioSockets.access$308(ServicioSockets.this);
        } catch (InterruptedException interruptedException) {
          Utilities.escribeArchivo("ServicioSockets", "Error: TimerThread", interruptedException.getMessage());
        } 
      } 
      ServicioSockets.access$502(ServicioSockets.this, false);
      GlobalMembers.bRespuestaNoRecibida = true;
      ServicioSockets.this.stopSelf();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/sockets/ServicioSockets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */