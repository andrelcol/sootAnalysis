package com.roadtrack.onstar.BO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.roadtrack.onstar.DAO.ManagerConnectionWS;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.entities.ActiveCallEntity;
import com.roadtrack.onstar.entities.DeviceInfo;
import com.roadtrack.onstar.entities.MessageRTMobile;
import com.roadtrack.onstar.utils.GetHexDumpMap;
import com.roadtrack.onstar.utils.Utilities;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

@SuppressLint({"HandlerLeak"})
public class ManagerNotificationPlatinum {
  @SuppressLint({"HandlerLeak"})
  public Handler NoConnectionBTHandler = new Handler(this) {
      public void handleMessage(Message param1Message) {
        // Byte code:
        //   0: aload_0
        //   1: monitorenter
        //   2: aload_1
        //   3: getfield what : I
        //   6: getstatic com/roadtrack/onstar/Enums$WhatHandler.NoConnectionBTAndGCM : Lcom/roadtrack/onstar/Enums$WhatHandler;
        //   9: invokevirtual ordinal : ()I
        //   12: if_icmpne -> 19
        //   15: iconst_1
        //   16: putstatic com/roadtrack/onstar/BO/MessagesDispatchService.terminate : Z
        //   19: aload_0
        //   20: monitorexit
        //   21: return
        //   22: astore_1
        //   23: aload_0
        //   24: monitorexit
        //   25: aload_1
        //   26: athrow
        // Exception table:
        //   from	to	target	type
        //   2	19	22	finally
      }
    };
  
  private Context _context;
  
  private Object[] _message;
  
  public Handler handlerSendMessage;
  
  public Handler handlerStatusMessage;
  
  public ManagerNotificationPlatinum(Context paramContext) {
    this._context = paramContext;
    this.handlerStatusMessage = this.NoConnectionBTHandler;
  }
  
  private void notify_Inner(Object[] paramArrayOfObject, Boolean paramBoolean, MessagesObjects.Device_OpCodes paramDevice_OpCodes, int paramInt) {
    String str = "";
    byte b = 0;
    try {
      while (b < paramArrayOfObject.length) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(str);
        stringBuilder.append(paramArrayOfObject[b]);
        stringBuilder.append(",");
        str = stringBuilder.toString();
        b++;
      } 
      NotifyPlatinumThread notifyPlatinumThread = new NotifyPlatinumThread();
      this(this, paramArrayOfObject, paramBoolean.booleanValue(), paramDevice_OpCodes, paramInt);
      Thread thread = new Thread();
      this(notifyPlatinumThread);
      thread.start();
      str.contains("51,1,");
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: notify_Inner", exception.getMessage());
    } 
  }
  
  public void ACK_InboundMessage(int paramInt, Enums.statusGeneric paramstatusGeneric) {
    int j = MessagesObjects.Device_OpCodes.InboundMsg.GetOpCode();
    int i = paramstatusGeneric.GetOpCode();
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.InboundMsg;
    notify_Inner(new Object[] { Integer.valueOf(j), Integer.valueOf(i) }, Boolean.valueOf(true), device_OpCodes, paramInt);
  }
  
  public void ACK_MessageGeneric(Enums.statusGeneric paramstatusGeneric) {
    boolean bool;
    if (paramstatusGeneric == Enums.statusGeneric.Failure) {
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      String str1 = Utilities.getStringFromConfigList(this._context, stringsResourcesVO.cannot_report_traffic, 2131689695);
      String str2 = Utilities.getStringFromConfigList(this._context, stringsResourcesVO.report, 2131690346);
      Context context = GlobalMembers.contexGlobal;
      (new MessageRTMobile(context)).MessageToast(context, str2, str1);
      bool = false;
    } else {
      bool = true;
    } 
    this._message = new Object[] { Integer.valueOf(MessagesObjects.Device_OpCodes.GenericMsg.GetOpCode()), Boolean.valueOf(bool) };
    notify_Inner(this._message, Boolean.valueOf(true), MessagesObjects.Device_OpCodes.GenericMsg, 0);
  }
  
  public void OpenStream() {
    this._message = new Object[] { Integer.valueOf(MessagesObjects.Device_OpCodes.OpenStreamTest.GetOpCode()) };
    notify_Inner(this._message, Boolean.valueOf(true), MessagesObjects.Device_OpCodes.OpenStreamTest, 0);
  }
  
  public void RequestPlatinumVersions() {
    try {
      int i = MessagesObjects.Device_OpCodes.RequestPlatinumVersions.GetOpCode();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.RequestPlatinumVersions;
      notify_Inner(new Object[] { Integer.valueOf(i) }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: RequestPlatinumVersions", exception.getMessage());
    } 
  }
  
  public void applyingUpdate(String paramString) {
    try {
      String str;
      int i = MessagesObjects.Device_OpCodes.ApplyUpdate.GetOpCode();
      if (GlobalMembers.isLastMapUpdateFile) {
        str = "1";
      } else {
        str = "0";
      } 
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.ApplyUpdate;
      notify_Inner(new Object[] { Integer.valueOf(i), str, paramString }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: applyingUpdate", exception.getMessage());
    } 
  }
  
  public void callControl(ActiveCallEntity paramActiveCallEntity) {
    try {
      int k = MessagesObjects.Device_OpCodes.CallControl.GetOpCode();
      int j = paramActiveCallEntity.getAction().GetOpCode();
      int m = paramActiveCallEntity.getDevice().GetOpCode();
      int i = paramActiveCallEntity.getCallIndex();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.CallControl;
      notify_Inner(new Object[] { Integer.valueOf(k), Integer.valueOf(j), Integer.valueOf(m), Integer.valueOf(i) }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: callControl", exception.getMessage());
    } 
  }
  
  public void cancelActiveRouteACK(Enums.statusGeneric paramstatusGeneric) {
    try {
      int i = MessagesObjects.Device_OpCodes.P8CancelActiveRouteACK.GetOpCode();
      int j = paramstatusGeneric.GetOpCode();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.P8CancelActiveRouteACK;
      notify_Inner(new Object[] { Integer.valueOf(i), Integer.valueOf(j) }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: cancelActiveRouteACK", exception.getMessage());
    } 
  }
  
  public void cancelMenu_ACK(int paramInt) {
    int i = MessagesObjects.Device_OpCodes.CancelMenuACK.GetOpCode();
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.CancelMenuACK;
    notify_Inner(new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt) }, Boolean.valueOf(true), device_OpCodes, 0);
  }
  
  public void closeMenu(int paramInt1, int paramInt2) {
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.CloseMenu;
    notify_Inner(new Object[] { Integer.valueOf(paramInt2), Integer.valueOf(paramInt1) }, Boolean.valueOf(true), device_OpCodes, 0);
  }
  
  public void displayMenu_ACK(int paramInt) {
    int i = MessagesObjects.Device_OpCodes.DisplayMenuACK.GetOpCode();
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.DisplayMenuACK;
    notify_Inner(new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt) }, Boolean.valueOf(true), device_OpCodes, 0);
  }
  
  public void downloadUpdateChunk(String paramString1, String paramString2, String paramString3, String paramString4) {
    try {
      int i = MessagesObjects.Device_OpCodes.DownloadUpdateChunk.GetOpCode();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.DownloadUpdateChunk;
      notify_Inner(new Object[] { Integer.valueOf(i), paramString1, paramString2, paramString3, paramString4 }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: downloadUpdateChunk", exception.getMessage());
    } 
  }
  
  public void downloadUpdateChunk(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfbyte) {
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("61|");
      stringBuilder.append(paramString1);
      stringBuilder.append("|");
      stringBuilder.append(paramString2);
      stringBuilder.append("|");
      stringBuilder.append(paramString3);
      stringBuilder.append("|");
      byteArrayOutputStream2.write(stringBuilder.toString().getBytes());
      byteArrayOutputStream2.write(paramArrayOfbyte);
      byte[] arrayOfByte1 = MessagesObjects.calculateChecksum(byteArrayOutputStream2.toByteArray()).getBytes();
      byteArrayOutputStream2.write(126);
      byteArrayOutputStream2.write(arrayOfByte1);
      byteArrayOutputStream1.write(91);
      byteArrayOutputStream1.write(byteArrayOutputStream2.toByteArray());
      byteArrayOutputStream1.write(93);
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "CHK", byteArrayOutputStream1.toString());
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
    byte[] arrayOfByte = byteArrayOutputStream1.toByteArray();
    try {
      if (this.handlerSendMessage != null) {
        Enums.WhatHandler.SendChunkToP8.ordinal();
        this.handlerSendMessage.obtainMessage(Enums.WhatHandler.SendChunkToP8.ordinal(), arrayOfByte).sendToTarget();
      } 
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("BT: handlerSendMessage notifyPlatinumBT de ManagerNotificationPlatinum");
      stringBuilder.append(exception);
      Utilities.escribeArchivo("ManagerNotificationPlatinum", stringBuilder.toString(), null);
    } 
  }
  
  public void errorCheSum(String paramString) {
    try {
      int i = MessagesObjects.Device_OpCodes.ChecksumError.GetOpCode();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.ChecksumError;
      notify_Inner(new Object[] { Integer.valueOf(i), paramString }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: errorCheckSum", exception.getMessage());
    } 
  }
  
  public void goToHomeStatus(Enums.GoToHomeStatus paramGoToHomeStatus) {
    try {
      int j = MessagesObjects.Device_OpCodes.GoToHome.GetOpCode();
      int i = paramGoToHomeStatus.GetOpCode();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.GoToHome;
      notify_Inner(new Object[] { Integer.valueOf(j), Integer.valueOf(i) }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: goToHomeStatus", exception.getMessage());
    } 
  }
  
  public void identificationRequest() {
    this._message = new Object[] { Integer.valueOf(MessagesObjects.Device_OpCodes.IdentificatioRequest.GetOpCode()) };
    notify_Inner(this._message, Boolean.valueOf(true), MessagesObjects.Device_OpCodes.IdentificatioRequest, 0);
  }
  
  public void infoResponse() {
    DeviceInfo deviceInfo = new DeviceInfo(this._context);
    Enums.UnitType unitType1 = GlobalMembers.unitTypePlatinum;
    Enums.UnitType unitType2 = Enums.UnitType.PLatinum_8;
    Boolean bool = Boolean.valueOf(true);
    if (unitType1 == unitType2) {
      this._message = new Object[] { Integer.valueOf(MessagesObjects.Device_OpCodes.PhoneInfoResponse.GetOpCode()), deviceInfo.getDeviceInfo().getDeviceID(), deviceInfo.getDeviceInfo().getShellVersion(), deviceInfo.getDeviceInfo().getInstallMaps(), Integer.valueOf(deviceInfo.getDeviceInfo().getVersionSDK()) };
      notify_Inner(this._message, bool, MessagesObjects.Device_OpCodes.PhoneInfoResponse, 0);
    } else {
      this._message = new Object[] { Integer.valueOf(MessagesObjects.Device_OpCodes.ResponseInfo.GetOpCode()), deviceInfo.getDeviceInfoP7().getDeviceID(), deviceInfo.getDeviceInfoP7().getShellVersion(), deviceInfo.getDeviceInfoP7().getInstallMaps() };
      notify_Inner(this._message, bool, MessagesObjects.Device_OpCodes.ResponseInfo, 0);
    } 
  }
  
  public void initiatingCall(Enums.Calls paramCalls, String paramString) {
    int i = MessagesObjects.Device_OpCodes.InitiatingCall.GetOpCode();
    int j = paramCalls.GetOpCode();
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.InitiatingCall;
    notify_Inner(new Object[] { Integer.valueOf(i), Integer.valueOf(j), paramString }, Boolean.valueOf(true), device_OpCodes, 0);
  }
  
  public void optionSelected(int paramInt) {
    int i = MessagesObjects.Device_OpCodes.OptionSelected.GetOpCode();
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.OptionSelected;
    notify_Inner(new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt) }, Boolean.valueOf(true), device_OpCodes, 0);
  }
  
  public void playingInformationStatus(Enums.statusGeneric paramstatusGeneric) {
    try {
      int j = MessagesObjects.Device_OpCodes.PlayingInformationStatus.GetOpCode();
      int i = paramstatusGeneric.GetOpCode();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.PlayingInformationStatus;
      notify_Inner(new Object[] { Integer.valueOf(j), Integer.valueOf(i) }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: playininformationstatus", exception.getMessage());
    } 
  }
  
  public void requestP8Model() {
    this._message = new Object[] { Integer.valueOf(MessagesObjects.Device_OpCodes.SendP8Model.GetOpCode()) };
    notify_Inner(this._message, Boolean.valueOf(true), MessagesObjects.Device_OpCodes.SendP8Model, 0);
  }
  
  public void sendTextForTTS(String paramString) {
    try {
      int i = MessagesObjects.Device_OpCodes.SendsTextForTTS.GetOpCode();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.SendsTextForTTS;
      notify_Inner(new Object[] { Integer.valueOf(i), paramString }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: sendTextForTTS", exception.getMessage());
    } 
  }
  
  public void syncFavorite(String paramString) {
    try {
      int i = MessagesObjects.Device_OpCodes.SyncFavorites.GetOpCode();
      String str = paramString.toString();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.SyncFavorites;
      notify_Inner(new Object[] { Integer.valueOf(i), str }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: syncFavorite", exception.getMessage());
    } 
  }
  
  public void tbtDetenerNavegagion(int paramInt) {
    int i = MessagesObjects.Device_OpCodes.OptionSelected.GetOpCode();
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.OptionSelected;
    notify_Inner(new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt) }, Boolean.valueOf(true), device_OpCodes, 0);
  }
  
  public void tbtMuteRouteInstructions(int paramInt) {
    int i = MessagesObjects.Device_OpCodes.TurnByTurnMuteState.GetOpCode();
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.TurnByTurnMuteState;
    notify_Inner(new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt) }, Boolean.valueOf(true), device_OpCodes, 0);
  }
  
  public void tbtStopNav(int paramInt) {
    int i = MessagesObjects.Device_OpCodes.OptionSelected.GetOpCode();
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.OptionSelected;
    notify_Inner(new Object[] { Integer.valueOf(i), Integer.valueOf(paramInt) }, Boolean.valueOf(true), device_OpCodes, 0);
  }
  
  public void textMessage(String paramString, int paramInt) {
    int i = MessagesObjects.Device_OpCodes.OutboundMsg.GetOpCode();
    MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.OutboundMsg;
    notify_Inner(new Object[] { Integer.valueOf(i), Integer.valueOf(1), "", paramString }, Boolean.valueOf(true), device_OpCodes, paramInt);
  }
  
  public void updateControl(int paramInt1, String paramString, int paramInt2) {
    long l2 = (new GetHexDumpMap()).getMapUpdateFileSize(paramString);
    long l1 = l2;
    if (l2 == 0L)
      l1 = paramInt2; 
    try {
      paramInt2 = MessagesObjects.Device_OpCodes.StartOrCancelUpdate.GetOpCode();
      MessagesObjects.Device_OpCodes device_OpCodes = MessagesObjects.Device_OpCodes.ChecksumError;
      notify_Inner(new Object[] { Integer.valueOf(paramInt2), Integer.valueOf(paramInt1), Long.valueOf(l1), paramString }, Boolean.valueOf(true), device_OpCodes, 0);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: updateControl", exception.getMessage());
    } 
  }
  
  class NotifyPlatinumThread implements Runnable {
    private int _codeMessage;
    
    private boolean _isBluetooth;
    
    private MessagesObjects.Device_OpCodes _opCode;
    
    private Object[] _paramArray;
    
    private ManagerDataConnection connection;
    
    final ManagerNotificationPlatinum this$0;
    
    public NotifyPlatinumThread(Object[] param1ArrayOfObject, boolean param1Boolean, MessagesObjects.Device_OpCodes param1Device_OpCodes, int param1Int) {
      this._paramArray = param1ArrayOfObject;
      this._isBluetooth = param1Boolean;
      this._opCode = param1Device_OpCodes;
      this._codeMessage = param1Int;
    }
    
    private boolean notifyPlatinumGCM(Object[] param1ArrayOfObject, String param1String) {
      String str = (new MessagesObjects()).assembleOutMessage(param1ArrayOfObject, GlobalMembers.contexGlobal);
      try {
        String str1 = GlobalMembers.URLGCMServer;
        ManagerConnectionWS managerConnectionWS = new ManagerConnectionWS();
        this();
        managerConnectionWS.set_methodName("SendMessage");
        managerConnectionWS.set_namespace(GlobalMembers.NAMESPACE_WCF_RT);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(GlobalMembers.NAMESPACE_WCF_RT);
        stringBuilder.append("SendMessage");
        managerConnectionWS.set_soapAction(stringBuilder.toString());
        managerConnectionWS.set_url(str1);
        Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
        this();
        hashtable.put("cve_usuario", param1String);
        hashtable.put("message", str);
        Integer.valueOf((String)managerConnectionWS.GetDataWSNet(hashtable, ManagerNotificationPlatinum.this._context));
        return true;
      } catch (Exception exception) {
        Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: notifyPlatinumGCM", exception.getMessage());
        return false;
      } 
    }
    
    public boolean notifyPlatinumBT() {
      try {
        if (ManagerNotificationPlatinum.this.handlerSendMessage != null)
          ManagerNotificationPlatinum.this.handlerSendMessage.obtainMessage(Enums.WhatHandler.SendMessagePlatinum.ordinal(), this._paramArray).sendToTarget(); 
      } catch (Exception exception) {
        Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: notifyPlatinumBT", exception.getMessage());
      } 
      return true;
    }
    
    public boolean notifyPlatinumGCM() {
      boolean bool;
      this.connection = new ManagerDataConnection(GlobalMembers.contexGlobal);
      if ((this.connection.CanSendData() & false) != 0) {
        bool = notifyPlatinumGCM(this._paramArray, "");
      } else {
        bool = notifyPlatinumBT();
      } 
      return bool;
    }
    
    public void run() {
      boolean bool;
      /* monitor enter ThisExpression{InnerObjectType{ObjectType{com/roadtrack/onstar/BO/ManagerNotificationPlatinum}.Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum$NotifyPlatinumThread;}} */
      try {
        if (this._isBluetooth) {
          bool = notifyPlatinumBT();
        } else {
          bool = notifyPlatinumGCM();
        } 
      } catch (Exception exception) {
        Utilities.escribeArchivo("ManagerNotificationPlatinum", "Error: NotifyPlatinum_Inner.run", exception.getMessage());
        bool = true;
      } finally {
        Exception exception;
      } 
      if (bool) {
        ManagerNotificationPlatinum.this.handlerStatusMessage.obtainMessage(Enums.WhatHandler.Device_OpCodes.ordinal(), this._opCode.GetOpCode(), 0, new String[] { String.valueOf(this._codeMessage) }).sendToTarget();
      } else {
        ManagerNotificationPlatinum.this.handlerStatusMessage.obtainMessage(Enums.WhatHandler.NoConnectionBTAndGCM.ordinal()).sendToTarget();
      } 
      /* monitor exit ThisExpression{InnerObjectType{ObjectType{com/roadtrack/onstar/BO/ManagerNotificationPlatinum}.Lcom/roadtrack/onstar/BO/ManagerNotificationPlatinum$NotifyPlatinumThread;}} */
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/ManagerNotificationPlatinum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */