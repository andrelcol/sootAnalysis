package com.roadtrack.onstar.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.MapUpdateVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.onstarApplication;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

public class MapUpdateHelper {
  private Context context;
  
  private DBFunctions dbFun;
  
  private String deviceP8Id;
  
  OnRequestCompleteListener mRequestCompleteListener;
  
  private boolean processFinished;
  
  public MapUpdateHelper(Context paramContext) {
    this.context = paramContext;
    this.dbFun = new DBFunctions(this.context);
  }
  
  private MapUpdateVO Socket(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, int paramInt3, String paramString4, String paramString5) {
    Exception exception1;
    Date date = new Date();
    String[] arrayOfString = new String[9];
    arrayOfString[0] = "";
    arrayOfString[1] = "0";
    arrayOfString[2] = "0";
    arrayOfString[3] = "0";
    arrayOfString[4] = "0";
    arrayOfString[5] = "0";
    arrayOfString[6] = "0";
    arrayOfString[7] = "0";
    arrayOfString[8] = "0";
    int i = 1;
    InetSocketAddress inetSocketAddress = null;
    paramString4 = null;
    paramInt1 = 0;
    label139: while (true) {
      if (i) {
        try {
          Socket socket = new Socket();
          this();
          try {
            inetSocketAddress = new InetSocketAddress();
            try {
              this(paramString3, paramInt3);
              socket.bind(null);
              socket.setSoTimeout(GlobalMembers.SOCKET_READ_TIMEOUT);
              socket.connect(inetSocketAddress, GlobalMembers.SOCKET_TIMEOUT);
              if (socket.isConnected()) {
                BufferedWriter bufferedWriter = new BufferedWriter();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter();
                this(socket.getOutputStream());
                this(outputStreamWriter);
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("ServiceMobileApp:");
                stringBuilder.append(paramString1);
                stringBuilder.append(",");
                stringBuilder.append(paramString5);
                stringBuilder.append(",");
                stringBuilder.append(GlobalMembers.entidadFederativa);
                String str = Utilities.Crypt(stringBuilder.toString());
                bufferedWriter.write(str);
                try {
                  StringBuilder stringBuilder1 = new StringBuilder();
                  this();
                  Socket socket1 = socket;
                  int k = paramInt1;
                  String str1 = paramString4;
                  int j = i;
                  try {
                    String str2;
                    stringBuilder1.append("SIZE: ");
                    socket1 = socket;
                    k = paramInt1;
                    str1 = paramString4;
                    j = i;
                    stringBuilder1.append(str.length());
                    socket1 = socket;
                    k = paramInt1;
                    str1 = paramString4;
                    j = i;
                    Utilities.escribeArchivo("MapUpdateHelper", "MapUpdateVO: DATA_REMOTE_SERVICES WRITE SOCKET", stringBuilder1.toString());
                    socket1 = socket;
                    k = paramInt1;
                    str1 = paramString4;
                    j = i;
                    bufferedWriter.flush();
                    socket1 = socket;
                    k = paramInt1;
                    str1 = paramString4;
                    j = i;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                      this(1);
                      socket1 = socket;
                      k = paramInt1;
                      str1 = paramString4;
                      j = i;
                      InputStream inputStream = socket.getInputStream();
                      while (true) {
                        String str3 = "SOCKET: ";
                        if (i) {
                          String str5;
                          Socket socket2 = socket;
                          String str4 = paramString4;
                          int m = paramInt1;
                          k = i;
                          try {
                            if (!this.processFinished) {
                              String str6;
                              socket2 = socket;
                              str4 = paramString4;
                              m = paramInt1;
                              k = i;
                              byte[] arrayOfByte = new byte[8192];
                              Socket socket3 = socket;
                              k = paramInt1;
                              str1 = paramString4;
                              j = i;
                              byteArrayOutputStream.write(arrayOfByte, 0, inputStream.read(arrayOfByte));
                              socket3 = socket;
                              k = paramInt1;
                              str1 = paramString4;
                              j = i;
                              String[] arrayOfString1 = byteArrayOutputStream.toString("UTF-8").split("\n");
                              socket3 = socket;
                              k = paramInt1;
                              str1 = paramString4;
                              j = i;
                              long l = getTimeDifference(null, date, 13);
                              j = 0;
                              str1 = paramString4;
                              while (true) {
                                str5 = str6;
                                str4 = str1;
                                m = paramInt1;
                                k = i;
                                int n = arrayOfString1.length;
                                paramString4 = str6;
                                str6 = paramString4;
                                paramString4 = str1;
                                i = m;
                              } 
                              continue label139;
                            } 
                          } catch (Exception null) {
                            str1 = str5;
                            paramString4 = str4;
                            paramInt1 = m;
                            i = k;
                            continue label139;
                          } 
                        } 
                        exception2 = exception3;
                        k = paramInt1;
                        str2 = paramString4;
                        j = i;
                        Exception exception5 = exception2;
                        exception2.close();
                        k = paramInt1;
                        str2 = paramString4;
                        j = i;
                        exception5 = exception2;
                        Utilities.escribeArchivo("MapUpdateHelper", "SOCKET: ", "CLOSE SOCKET MAPUPDATE");
                        str2 = paramString4;
                        exception1 = exception2;
                        break;
                      } 
                    } catch (Exception exception4) {
                      str1 = str2;
                      exception3 = exception1;
                      String str3 = str1;
                    } 
                  } catch (Exception exception3) {
                    exception1 = exception4;
                    paramInt1 = k;
                    i = j;
                  } 
                } catch (Exception null) {
                  Exception exception4 = exception3;
                  exception3 = exception2;
                  exception2 = exception1;
                  exception1 = exception4;
                } 
              } else {
                exception2 = exception3;
                exception3 = exception1;
                exception1 = exception2;
                if (getTimeDifference(null, date, 13) < paramInt2 && i != 0 && !this.processFinished) {
                  i = 1;
                } else {
                  i = 0;
                } 
              } 
            } catch (Exception null) {
              Exception exception4 = exception3;
              exception3 = exception2;
              exception2 = exception1;
              exception1 = exception4;
            } 
          } catch (Exception exception4) {
            exception2 = exception3;
            exception3 = exception4;
            exception4 = exception1;
            exception1 = exception2;
            exception2 = exception4;
          } 
          exception = exception3;
          exception3 = exception2;
        } catch (Exception exception) {
          Exception exception3 = exception1;
          exception1 = exception2;
        } 
      } else {
        break;
      } 
      Utilities.escribeArchivo("MapUpdateHelper", "Error: SOCKET", exception.toString());
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException exception2) {
        Utilities.escribeArchivo("MapUpdateHelper", "Error: timer", exception2.toString());
      } 
    } 
    if (i == 0 && exception2 != null && exception2.isConnected())
      try {
        exception2.close();
      } catch (IOException iOException) {
        Utilities.escribeArchivo("MapUpdateHelper", "Error: SOCKET.IOException", iOException.toString());
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException interruptedException) {
          Utilities.escribeArchivo("MapUpdateHelper", "Error: timer", interruptedException.toString());
        } 
      }  
    return (MapUpdateVO)exception1;
  }
  
  private long getTimeDifference(Date paramDate1, Date paramDate2, int paramInt) {
    Date date = paramDate1;
    if (paramDate1 == null)
      date = new Date(); 
    paramDate1 = new Date(date.getTime() - paramDate2.getTime());
    long l1 = paramDate1.getTime() / 1000L / 60L / 60L;
    long l3 = paramDate1.getTime() / 1000L / 60L;
    long l2 = paramDate1.getTime() / 1000L;
    return (paramInt == 11) ? l1 : ((paramInt == 12) ? l3 : l2);
  }
  
  public Dialog displayDownloadMapDialog(Context paramContext, final MapUpdateVO versionMapa) {
    final DBFunctions dbFun = new DBFunctions(paramContext);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    final Dialog dialog = Utilities.simpleDialogWithCheckBox(paramContext, null, Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955), Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.mapdownloading_popup_lbl_actualizacionencontradaAPP_2, 2131690119), true, Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.mapdownloading_popup_btn_despuesmayus_4, 2131690117), true, Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_popup_btn_si_1, 2131689952), 20.0F, 16.0F, true, Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.mapdownloading_popup_checbox_norecordar_5, 2131690118));
    Button button2 = (Button)dialog.findViewById(2131296343);
    Button button1 = (Button)dialog.findViewById(2131296344);
    button2.setOnClickListener(new View.OnClickListener() {
          final MapUpdateHelper this$0;
          
          final DBFunctions val$dbFun;
          
          final Dialog val$dialog;
          
          final MapUpdateVO val$versionMapa;
          
          public void onClick(View param1View) {
            versionMapa.setFileMapStatus(1);
            dbFun.updateMapUpdateData(versionMapa);
            dialog.dismiss();
            MapUpdateHelper.OnRequestCompleteListener onRequestCompleteListener = MapUpdateHelper.this.mRequestCompleteListener;
            if (onRequestCompleteListener != null)
              onRequestCompleteListener.onAfterDialog(); 
          }
        });
    button1.setOnClickListener(new View.OnClickListener(this) {
          final Dialog val$dialog;
          
          public void onClick(View param1View) {
            dialog.dismiss();
          }
        });
    ((CheckBox)dialog.findViewById(2131296460)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) {
          final DBFunctions val$dbFun;
          
          final MapUpdateVO val$versionMapa;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (param1Boolean) {
              versionMapa.setUserWantsUpdrade(0);
              dbFun.updateMapUpdateData(versionMapa);
            } else {
              versionMapa.setUserWantsUpdrade(1);
              dbFun.updateMapUpdateData(versionMapa);
            } 
          }
        });
    dialog.setOnCancelListener(new DialogInterface.OnCancelListener(this) {
          public void onCancel(DialogInterface param1DialogInterface) {
            GlobalMembers.isShowingDialog = false;
            Utilities.escribeArchivo("MapUpdateHelper", "displayDownloadMapDialog:", "canceled");
            Intent intent = new Intent();
            intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
            GlobalMembers.contexGlobal.sendBroadcast(intent);
          }
        });
    dialog.setOnDismissListener(new DialogInterface.OnDismissListener(this) {
          public void onDismiss(DialogInterface param1DialogInterface) {
            GlobalMembers.isShowingDialog = false;
            Utilities.escribeArchivo("MapUpdateHelper", "displayDownloadMapDialog:", "canceled");
            Intent intent = new Intent();
            intent.setAction("com.roadtrack.onstar.DIALOG_RECEIVER");
            GlobalMembers.contexGlobal.sendBroadcast(intent);
          }
        });
    return dialog;
  }
  
  public void retriveServerMapVersion(onstarApplication paramonstarApplication) {
    String str2 = paramonstarApplication.getAccountID();
    this.deviceP8Id = Utilities.getLastKnownDeviceSelected(paramonstarApplication, "MapUpdateHelper").getDeviceId();
    String str1 = GlobalMembers.mapUpdateSocketAction;
    (new AsyntaskGetVersionMap()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { str1, "10000", "30", str2, GlobalMembers.strIPSocketGetCommand, GlobalMembers.strPuertoA, "0", this.deviceP8Id });
  }
  
  public void setmRequestCompleteListener(OnRequestCompleteListener paramOnRequestCompleteListener) {
    this.mRequestCompleteListener = paramOnRequestCompleteListener;
  }
  
  public MapUpdateVO verifyPendingDownloads(DBFunctions paramDBFunctions, String paramString) {
    new MapUpdateVO();
    MapUpdateVO mapUpdateVO = paramDBFunctions.getMapUpdateMapaData(paramString);
    if (mapUpdateVO == null || mapUpdateVO.isUserWantsUpdrade() == 0 || (mapUpdateVO.getFileMapStatus() != 1 && mapUpdateVO.getFileMapStatus() != 0))
      return null; 
    GlobalMembers.mapFileName = mapUpdateVO.getServerFileName();
    return mapUpdateVO;
  }
  
  private class AsyntaskGetVersionMap extends AsyncTask<String, Void, MapUpdateVO> {
    final MapUpdateHelper this$0;
    
    private AsyntaskGetVersionMap() {}
    
    protected MapUpdateVO doInBackground(String... param1VarArgs) {
      return MapUpdateHelper.this.Socket(param1VarArgs[0], Integer.parseInt(param1VarArgs[1]), Integer.parseInt(param1VarArgs[2]), param1VarArgs[3], param1VarArgs[4], Integer.parseInt(param1VarArgs[5]), param1VarArgs[6], param1VarArgs[7]);
    }
    
    protected void onPostExecute(MapUpdateVO param1MapUpdateVO) {
      super.onPostExecute(param1MapUpdateVO);
      if (param1MapUpdateVO != null) {
        StringBuilder stringBuilder;
        MapUpdateVO mapUpdateVO = MapUpdateHelper.this.dbFun.getMapUpdateMapaData(param1MapUpdateVO.getP8IdSerial());
        if (mapUpdateVO == null) {
          MapUpdateHelper.this.dbFun.addMapUpdateData(param1MapUpdateVO);
          stringBuilder = new StringBuilder();
          stringBuilder.append("FIRST NEW MAP");
          stringBuilder.append(param1MapUpdateVO.getUpgradeOldversionNewversion());
          Utilities.escribeArchivo("MapUpdateHelper", "SEVERMAP:", stringBuilder.toString());
        } else if (stringBuilder.getServerMapVersion().equals("")) {
          param1MapUpdateVO.setP8MapVersion(stringBuilder.getP8MapVersion());
          param1MapUpdateVO.setFileMapStatus(1);
          MapUpdateHelper.this.dbFun.updateMapUpdateData(param1MapUpdateVO);
          stringBuilder = new StringBuilder();
          stringBuilder.append("PLATINUM FIRST");
          stringBuilder.append(param1MapUpdateVO.getUpgradeOldversionNewversion());
          Utilities.escribeArchivo("MapUpdateHelper", "SEVERMAP:", stringBuilder.toString());
        } else if (stringBuilder.getP8MapVersion().equals("") && !stringBuilder.getServerMapVersion().equals(param1MapUpdateVO.getServerMapVersion())) {
          MapUpdateHelper.this.dbFun.updateMapUpdateData(param1MapUpdateVO);
          stringBuilder = new StringBuilder();
          stringBuilder.append("NEW MAP");
          stringBuilder.append(param1MapUpdateVO.getUpgradeOldversionNewversion());
          Utilities.escribeArchivo("MapUpdateHelper", "SEVERMAP:", stringBuilder.toString());
        } else if (stringBuilder.isUserWantsUpdrade() == 0 && !stringBuilder.getServerMapVersion().equals(param1MapUpdateVO.getP8MapVersion())) {
          param1MapUpdateVO.setP8MapVersion(stringBuilder.getP8MapVersion());
          param1MapUpdateVO.setFileMapStatus(1);
          MapUpdateHelper.this.dbFun.updateMapUpdateData(param1MapUpdateVO);
          stringBuilder = new StringBuilder();
          stringBuilder.append("OVERRIDE UNWANTED");
          stringBuilder.append(param1MapUpdateVO.getUpgradeOldversionNewversion());
          Utilities.escribeArchivo("MapUpdateHelper", "SEVERMAP:", stringBuilder.toString());
        } else if (stringBuilder.getFileMapStatus() == 5 && !param1MapUpdateVO.getServerMapVersion().equals(stringBuilder.getServerMapVersion())) {
          param1MapUpdateVO.setP8MapVersion(stringBuilder.getP8MapVersion());
          param1MapUpdateVO.setFileMapStatus(1);
          MapUpdateHelper.this.dbFun.updateMapUpdateData(param1MapUpdateVO);
          stringBuilder = new StringBuilder();
          stringBuilder.append("PREVIOUS UPDATED");
          stringBuilder.append(param1MapUpdateVO.getUpgradeOldversionNewversion());
          Utilities.escribeArchivo("MapUpdateHelper", "SEVERMAP:", stringBuilder.toString());
        } 
      } 
    }
  }
  
  public static interface OnRequestCompleteListener {
    void onAfterDialog();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/MapUpdateHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */