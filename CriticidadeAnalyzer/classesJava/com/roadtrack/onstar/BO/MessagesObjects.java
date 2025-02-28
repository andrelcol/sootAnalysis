package com.roadtrack.onstar.BO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Message;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.OtaResponsePlatinumVersionVO;
import com.roadtrack.onstar.VO.TbtDataVO;
import com.roadtrack.onstar.VO.TbtManeuverInfoVO;
import com.roadtrack.onstar.VO.TbtRoutePrevioInfoVO;
import com.roadtrack.onstar.entities.CommandPlatinumEntity;
import com.roadtrack.onstar.entities.MessageRTMobile;
import com.roadtrack.onstar.entities.MessagesDetail;
import com.roadtrack.onstar.entities.PlayingInformationEntity;
import com.roadtrack.onstar.entities.SwitchActiveNavigationEntity;
import com.roadtrack.onstar.entities.SyncFavoriteEntity;
import com.roadtrack.onstar.entities.SyncSettingEntity;
import com.roadtrack.onstar.platinum.MultiModalHMI;
import com.roadtrack.onstar.utils.Utilities;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

@SuppressLint({"DefaultLocale"})
public class MessagesObjects {
  private ManagerNotificationPlatinum mnPlatinum;
  
  public static String calculateChecksum(byte[] paramArrayOfbyte) {
    byte b = 0;
    int i = 0;
    while (b < paramArrayOfbyte.length) {
      i += paramArrayOfbyte[b];
      b++;
    } 
    String str = Integer.toHexString(i);
    if (str.length() >= 2) {
      str = str.substring(str.length() - 2).toUpperCase();
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0");
      stringBuilder.append(str.toUpperCase());
      str = stringBuilder.toString();
    } 
    return str;
  }
  
  public String assembleOutMessage(Object[] paramArrayOfObject, Context paramContext) {
    StringBuilder stringBuilder2 = new StringBuilder();
    byte b;
    for (b = 0; b < paramArrayOfObject.length - 1; b++) {
      stringBuilder2.append(paramArrayOfObject[b].toString());
      stringBuilder2.append("|");
    } 
    stringBuilder2.append(paramArrayOfObject[b].toString());
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append('[');
    stringBuilder1.append(stringBuilder2.toString());
    stringBuilder1.append('~');
    stringBuilder1.append(calculateChecksum(stringBuilder2.toString()));
    stringBuilder1.append(']');
    return stringBuilder1.toString();
  }
  
  public String calculateChecksum(String paramString) {
    boolean bool;
    byte b = 0;
    String str = "utf-8";
    try {
      if (GlobalMembers.unitTypePlatinum == Enums.UnitType.Platinum_7)
        str = "Cp1252"; 
      byte[] arrayOfByte = paramString.getBytes(str);
      int i = 0;
      while (true) {
        bool = i;
        try {
          if (b < arrayOfByte.length) {
            bool = arrayOfByte[b];
            i += bool;
            b++;
            continue;
          } 
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
          // Byte code: goto -> 60
        } 
        break;
      } 
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      boolean bool1 = false;
      Utilities.escribeArchivo("MessagesObjects", "Error: calculateChecksum", unsupportedEncodingException.getMessage());
      bool = bool1;
    } 
    paramString = Integer.toHexString(bool);
    return paramString.substring(paramString.length() - 2).toUpperCase();
  }
  
  public ArrayList<Hashtable<String, Object>> getMessage(Message paramMessage, Context paramContext) {
    try {
      ManagerNotificationPlatinum managerNotificationPlatinum = new ManagerNotificationPlatinum();
      this(paramContext);
      this.mnPlatinum = managerNotificationPlatinum;
      MessageRTMobile messageRTMobile = new MessageRTMobile();
      this(paramContext);
      ArrayList<Hashtable<Object, Object>> arrayList = new ArrayList();
      this();
      try {
        StringBuilder stringBuilder;
        OtaResponsePlatinumVersionVO otaResponsePlatinumVersionVO;
        TbtDataVO tbtDataVO1;
        TbtManeuverInfoVO tbtManeuverInfoVO;
        String str1;
        SyncSettingEntity syncSettingEntity;
        SyncFavoriteEntity syncFavoriteEntity;
        SwitchActiveNavigationEntity switchActiveNavigationEntity;
        Enums.statusGeneric statusGeneric;
        ActiveCall activeCall;
        TbtDataVO tbtDataVO2;
        String str3;
        Date date;
        Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
        this();
        Platinum_OpCodes platinum_OpCodes = Platinum_OpCodes.GetValue(paramMessage.arg1);
        String[] arrayOfString = (String[])paramMessage.obj;
        int j = null.$SwitchMap$com$roadtrack$onstar$BO$MessagesObjects$Platinum_OpCodes[platinum_OpCodes.ordinal()];
        String str2 = "";
        int i = 0;
        ArrayList<Hashtable<Object, Object>> arrayList1 = arrayList;
        switch (j) {
          default:
            arrayList1 = arrayList;
            break;
          case 36:
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", "");
            arrayList.add(hashtable);
            i = GlobalMembers.mmsg85;
            arrayList1 = arrayList;
            if (i != 1) {
              arrayList1 = arrayList;
              if (i != 2)
                arrayList1 = arrayList; 
            } 
            break;
          case 35:
            GlobalMembers.updateStatusResponse = arrayOfString[0];
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", "");
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 34:
            GlobalMembers.downloadResponse = arrayOfString[0];
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", "");
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 33:
            stringBuilder = new StringBuilder();
            this();
            stringBuilder.append(arrayOfString[0]);
            stringBuilder.append(",");
            stringBuilder.append(arrayOfString[1]);
            stringBuilder.append(",");
            stringBuilder.append(arrayOfString[2]);
            GlobalMembers.tbtDataChunkAck = stringBuilder.toString().split(",");
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", GlobalMembers.tbtDataChunkAck);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 32:
            stringBuilder = new StringBuilder();
            this();
            stringBuilder.append(arrayOfString[0]);
            stringBuilder.append(",");
            stringBuilder.append(arrayOfString[1]);
            GlobalMembers.tbtDataChunk = stringBuilder.toString().split(",");
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", GlobalMembers.tbtDataChunk);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 31:
            GlobalMembers.platinumBTVersion = Integer.parseInt(arrayOfString[0]);
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", "");
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 30:
            otaResponsePlatinumVersionVO = new OtaResponsePlatinumVersionVO();
            this();
            otaResponsePlatinumVersionVO.setPrimaVer(arrayOfString[0]);
            otaResponsePlatinumVersionVO.setResourceVer(arrayOfString[1]);
            otaResponsePlatinumVersionVO.setMapsVer(arrayOfString[2]);
            otaResponsePlatinumVersionVO.setUpdateAgentVer(arrayOfString[3]);
            otaResponsePlatinumVersionVO.setModemVer(arrayOfString[4]);
            otaResponsePlatinumVersionVO.setMcuVer(arrayOfString[5]);
            otaResponsePlatinumVersionVO.setKeylesVer(arrayOfString[6]);
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", otaResponsePlatinumVersionVO);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            if (GlobalMembers.myListener != null) {
              PreferenceRT.SetValuePreference(Enums.SettingsPreference.p8PrimaVer, arrayOfString[0], paramContext);
              PreferenceRT.SetValuePreference(Enums.SettingsPreference.p8ResouVer, arrayOfString[1], paramContext);
              PreferenceRT.SetValuePreference(Enums.SettingsPreference.p8MapsVer, arrayOfString[2], paramContext);
              PreferenceRT.SetValuePreference(Enums.SettingsPreference.p8UpdateVer, arrayOfString[3], paramContext);
              PreferenceRT.SetValuePreference(Enums.SettingsPreference.p8ModemVer, arrayOfString[4], paramContext);
              PreferenceRT.SetValuePreference(Enums.SettingsPreference.p8McuVer, arrayOfString[5], paramContext);
              PreferenceRT.SetValuePreference(Enums.SettingsPreference.p8KeyLessVer, arrayOfString[6], paramContext);
              arrayList1 = arrayList;
            } 
            break;
          case 29:
            tbtDataVO1 = new TbtDataVO();
            this();
            tbtDataVO1.setOption(54);
            tbtDataVO1.setCode(Integer.parseInt(arrayOfString[0]));
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", tbtDataVO1);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 28:
            GlobalMembers.tbtManouverInfoHead = arrayOfString[0].split(";");
            for (i = 1; i < arrayOfString.length; i++) {
              String[] arrayOfString1 = arrayOfString[i].split(";");
              TbtRoutePrevioInfoVO tbtRoutePrevioInfoVO = new TbtRoutePrevioInfoVO();
              this();
              tbtRoutePrevioInfoVO.setSignType(Integer.parseInt(arrayOfString1[1]));
              GlobalMembers.tbtRoutePreviewInfo.add(tbtRoutePrevioInfoVO);
            } 
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", "");
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 27:
            tbtManeuverInfoVO = new TbtManeuverInfoVO();
            this();
            tbtManeuverInfoVO.setIsUrgentManeuver(Integer.parseInt(arrayOfString[0]));
            tbtManeuverInfoVO.setIsNewManeuver(Integer.parseInt(arrayOfString[1]));
            tbtManeuverInfoVO.setDistanceManeuver(arrayOfString[2]);
            tbtManeuverInfoVO.setSingType(Integer.parseInt(arrayOfString[3]));
            tbtManeuverInfoVO.setCurrentStreetName(arrayOfString[4]);
            tbtManeuverInfoVO.setNextStreetName(arrayOfString[5]);
            tbtManeuverInfoVO.setDistanceToDestination(arrayOfString[6]);
            tbtManeuverInfoVO.setTimeToDestination(arrayOfString[7]);
            GlobalMembers.tbtManeouverInfo = tbtManeuverInfoVO;
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", tbtManeuverInfoVO);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 26:
            tbtDataVO2 = new TbtDataVO();
            this();
            tbtDataVO2.setOption(58);
            tbtDataVO2.setCode(0);
            while (i < arrayOfString.length) {
              StringBuilder stringBuilder1 = new StringBuilder();
              this();
              stringBuilder1.append((String)tbtManeuverInfoVO);
              stringBuilder1.append(arrayOfString[i]);
              stringBuilder1.append(";");
              str1 = stringBuilder1.toString();
              i++;
            } 
            tbtDataVO2.setListOptions(str1.split(";"));
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", tbtDataVO2);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 25:
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", arrayOfString[0]);
            arrayList.add(hashtable);
            GlobalMembers.bMostrandoMenuHMI = false;
            arrayList1 = arrayList;
            break;
          case 24:
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", MultiModalHMI.getInstance().getMenuToDisplay(arrayOfString));
            arrayList.add(hashtable);
            GlobalMembers.bMostrandoMenuHMI = true;
            arrayList1 = arrayList;
            break;
          case 23:
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", arrayOfString[0]);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 22:
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", "");
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 21:
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", "");
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 18:
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", arrayOfString);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 17:
            syncSettingEntity = new SyncSettingEntity();
            this(Enums.statusGeneric.GetValue(Integer.valueOf(arrayOfString[0]).intValue()));
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", syncSettingEntity);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 16:
            syncFavoriteEntity = new SyncFavoriteEntity();
            this(Enums.statusGeneric.GetValue(Integer.valueOf(arrayOfString[0]).intValue()));
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", syncFavoriteEntity);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 15:
            hashtable.put("opCode", platinum_OpCodes);
            switchActiveNavigationEntity = new SwitchActiveNavigationEntity();
            this(Enums.actionSwitchNavigation.GetValue(Integer.valueOf(arrayOfString[0]).intValue()), Enums.statusSwitchNavigation.Failure);
            hashtable.put("Message", switchActiveNavigationEntity);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 14:
            hashtable.put("opCode", platinum_OpCodes);
            switchActiveNavigationEntity = new SwitchActiveNavigationEntity();
            this(Enums.actionSwitchNavigation.CancelRoute, Enums.statusSwitchNavigation.GetValue(Integer.valueOf(arrayOfString[0]).intValue()));
            hashtable.put("Message", switchActiveNavigationEntity);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 13:
            statusGeneric = Enums.statusGeneric.GetValue(Integer.valueOf(arrayOfString[0].toString()).intValue());
            this.mnPlatinum.ACK_MessageGeneric(statusGeneric);
            arrayList1 = arrayList;
            break;
          case 12:
            this.mnPlatinum.goToHomeStatus(Enums.GoToHomeStatus.ACK);
            arrayList1 = arrayList;
            break;
          case 11:
            try {
              PlayingInformationEntity playingInformationEntity = new PlayingInformationEntity();
              this();
              if (arrayOfString != null && arrayOfString.length > 7) {
                i = arrayOfString[0].length();
                if (i == 0)
                  arrayOfString[0] = "0"; 
                playingInformationEntity.setStatus(Enums.playingStatus.GetValue(Integer.valueOf(arrayOfString[0].toString()).intValue()));
                playingInformationEntity.setTitle(arrayOfString[1].toString());
                playingInformationEntity.setArtist(arrayOfString[2].toString());
                playingInformationEntity.setAlbum(arrayOfString[3].toString());
                if (arrayOfString[4].length() == 0)
                  arrayOfString[4] = "0"; 
                playingInformationEntity.setTrackNumber(Integer.valueOf(arrayOfString[4].toString()).intValue());
                playingInformationEntity.setGender(arrayOfString[5]);
                if (arrayOfString[6].length() == 0)
                  arrayOfString[6] = "0"; 
                playingInformationEntity.setTrackIndex(Integer.valueOf(arrayOfString[6].toString()).intValue());
                playingInformationEntity.setComposer(arrayOfString[7].toString());
              } 
              hashtable.put("opCode", platinum_OpCodes);
              hashtable.put("Message", playingInformationEntity);
              arrayList.add(hashtable);
              arrayList1 = arrayList;
            } catch (Exception exception1) {
              Utilities.escribeArchivo("MessagesObjects", "Error: PlayingInformation - Messages", exception1.toString());
              this.mnPlatinum.playingInformationStatus(Enums.statusGeneric.Failure);
              arrayList1 = arrayList;
            } 
            break;
          case 10:
            if (((CommandPlatinumEntity)((Hashtable)arrayList.get(0)).get("Message")).getStatus() == Enums.statusGeneric.Success) {
              messageRTMobile.MessageToast((Context)tbtDataVO2, "Comando", "Ejecutado satisfactoriamente");
              arrayList1 = arrayList;
              break;
            } 
            messageRTMobile.MessageToast((Context)tbtDataVO2, "Comando", "No se pudo ejecutar");
            arrayList1 = arrayList;
            break;
          case 8:
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", arrayOfString);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 6:
            activeCall = new ActiveCall();
            this((Context)tbtDataVO2);
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", activeCall.getActiveCall(arrayOfString));
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 5:
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", arrayOfString);
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 4:
            str3 = arrayOfString[3].toString();
            i = str3.length();
            arrayList1 = arrayList;
            if (i > 19) {
              String str4;
              String str6;
              String str5 = str3.substring(19, i).trim();
              if (arrayOfString[0].toString().equals(String.valueOf(Enums.MessageOrigin.PLT_Application.GetOpCode())) || arrayOfString[0].toString().equals(String.valueOf(Enums.MessageOrigin.PLT_Alert.GetOpCode())) || arrayOfString[0].toString().equals(String.valueOf(Enums.MessageOrigin.TBD.GetOpCode()))) {
                if (arrayOfString[0].toString().equals(String.valueOf(Enums.MessageOrigin.TBD.GetOpCode()))) {
                  str3 = str3.trim();
                  str4 = "";
                } else {
                  str3 = str3.substring(0, 16);
                  str4 = str5;
                } 
                String str = null;
                str6 = str3;
                str5 = str4;
                str3 = str;
              } else {
                date = GlobalMembers.DateTimeFormat.parse(str3.substring(0, 16));
                str6 = str4;
              } 
              MessagesDetail messagesDetail = new MessagesDetail();
              this();
              messagesDetail.setIdCode("1234");
              messagesDetail.setIdOrigin(Enums.MessageOrigin.GetValue(Integer.valueOf(arrayOfString[0]).intValue()));
              messagesDetail.setTypeGUI(Enums.MessageType.GetValue(Integer.valueOf(arrayOfString[1]).intValue()));
              messagesDetail.setPhoneNumber(arrayOfString[2]);
              messagesDetail.setTextMessage(str5);
              messagesDetail.setDateMessage(date);
              messagesDetail.setDescripMessage(str6);
              hashtable.put("opCode", platinum_OpCodes);
              hashtable.put("Message", messagesDetail);
              arrayList.add(hashtable);
              ArrayList<Hashtable<Object, Object>> arrayList2 = arrayList;
            } 
            break;
          case 3:
            PreferenceRT.SetValuePreference(Enums.SettingsPreference.IgnitionSwitchNotification, String.format("%s|%s|%s|%s", new Object[] { arrayOfString[0], arrayOfString[1], arrayOfString[2], arrayOfString[3] }), (Context)date);
            hashtable.put("opCode", platinum_OpCodes);
            hashtable.put("Message", String.format("%s|%s|%s|%s", new Object[] { arrayOfString[0], arrayOfString[1], arrayOfString[2], arrayOfString[3] }));
            arrayList.add(hashtable);
            arrayList1 = arrayList;
            break;
          case 1:
            PreferenceRT.SetValuePreference(Enums.SettingsPreference.IDSimCard, arrayOfString[0], (Context)date);
            if (arrayOfString.length > 1) {
              GlobalMembers.unitTypePlatinum = Enums.UnitType.PLatinum_8;
              arrayList1 = arrayList;
              break;
            } 
            GlobalMembers.unitTypePlatinum = Enums.UnitType.Platinum_7;
            arrayList1 = arrayList;
            break;
          case 2:
          case 7:
          case 9:
          case 19:
          case 20:
            break;
        } 
      } catch (Exception null) {
        ArrayList<Hashtable<Object, Object>> arrayList1 = arrayList;
      } 
    } catch (Exception exception) {
      paramMessage = null;
    } 
    Utilities.escribeArchivo("MessagesObjects", "Error: NewMessage_Inner", exception.getMessage());
    Message message = paramMessage;
  }
  
  public Boolean messageIsValid(String paramString) {
    String str = paramString;
    if (paramString.substring(0, 1).equals("["))
      str = paramString.substring(1, paramString.length()); 
    paramString = str;
    if (str.substring(str.length() - 1, str.length()).equals("]"))
      paramString = str.substring(0, str.length() - 1); 
    str = paramString.substring(paramString.trim().length() - 2).trim();
    return calculateChecksum(paramString.trim().substring(0, paramString.trim().length() - 3).getBytes()).equalsIgnoreCase(str) ? Boolean.valueOf(true) : Boolean.valueOf(false);
  }
  
  public enum Device_OpCodes {
    ApplyUpdate,
    CallControl,
    CallLogReport,
    CallLog_Request,
    CancelMenuACK,
    ChecksumError,
    CloseMenu,
    DisplayMenuACK,
    DownloadUpdateChunk,
    GenericMsg,
    GoToHome,
    GoToHomeReply,
    IdentificatioRequest,
    InboundMsg,
    InitiatingCall,
    LenguajePlatinum,
    MusicControl,
    NotifiesP8AboutActiveRoute,
    OpenStreamTest(0),
    OptionSelected(0),
    OutboundMsg(0),
    P8CancelActiveRouteACK(0),
    P8SwitchActiveNavigationPhoneStatus(0),
    PLTCommands(0),
    PhoneBook_ContactsRequest(0),
    PhoneInfoResponse(0),
    PhoneNotifiesAboutNewMap(0),
    PhoneSwitchActiveNavigationP8(0),
    PlayingInformationStatus(0),
    RequestPlatinumVersions(0),
    ResponseInfo(0),
    RoutingStatus(0),
    SendDTMF(0),
    SendP8Model(0),
    SendRouteACK(0),
    SendsTextForTTS(0),
    StartOrCancelUpdate(0),
    StopNavigation(0),
    SyncFavorites(0),
    SyncSettings(0),
    TurnByTurnAction(0),
    TurnByTurnMuteState(0),
    TurnByTurnRoutePreviewACK(0),
    VRSelectedOption(0),
    VolumeUpdate(0),
    WeatherRequest(0),
    WeatherResponse(0);
    
    private static final Device_OpCodes[] $VALUES;
    
    private int opcode;
    
    static {
      CallControl = new Device_OpCodes("CallControl", 3, 3);
      InitiatingCall = new Device_OpCodes("InitiatingCall", 4, 4);
      InboundMsg = new Device_OpCodes("InboundMsg", 5, 5);
      ChecksumError = new Device_OpCodes("ChecksumError", 6, 7);
      VolumeUpdate = new Device_OpCodes("VolumeUpdate", 7, 8);
      CallLogReport = new Device_OpCodes("CallLogReport", 8, 9);
      OutboundMsg = new Device_OpCodes("OutboundMsg", 9, 10);
      SendDTMF = new Device_OpCodes("SendDTMF", 10, 12);
      PhoneBook_ContactsRequest = new Device_OpCodes("PhoneBook_ContactsRequest", 11, 13);
      MusicControl = new Device_OpCodes("MusicControl", 12, 15);
      PLTCommands = new Device_OpCodes("PLTCommands", 13, 16);
      PlayingInformationStatus = new Device_OpCodes("PlayingInformationStatus", 14, 17);
      GoToHomeReply = new Device_OpCodes("GoToHomeReply", 15, 18);
      LenguajePlatinum = new Device_OpCodes("LenguajePlatinum", 16, 19);
      ResponseInfo = new Device_OpCodes("ResponseInfo", 17, 21);
      GoToHome = new Device_OpCodes("GoToHome", 18, 23);
      CallLog_Request = new Device_OpCodes("CallLog_Request", 19, 25);
      WeatherRequest = new Device_OpCodes("WeatherRequest", 20, 28);
      WeatherResponse = new Device_OpCodes("WeatherResponse", 21, 29);
      VRSelectedOption = new Device_OpCodes("VRSelectedOption", 22, 31);
      PhoneSwitchActiveNavigationP8 = new Device_OpCodes("PhoneSwitchActiveNavigationP8", 23, 33);
      P8SwitchActiveNavigationPhoneStatus = new Device_OpCodes("P8SwitchActiveNavigationPhoneStatus", 24, 34);
      SyncFavorites = new Device_OpCodes("SyncFavorites", 25, 35);
      SyncSettings = new Device_OpCodes("SyncSettings", 26, 36);
      SendRouteACK = new Device_OpCodes("SendRouteACK", 27, 38);
      NotifiesP8AboutActiveRoute = new Device_OpCodes("NotifiesP8AboutActiveRoute", 28, 40);
      P8CancelActiveRouteACK = new Device_OpCodes("P8CancelActiveRouteACK", 29, 41);
      SendsTextForTTS = new Device_OpCodes("SendsTextForTTS", 30, 42);
      PhoneNotifiesAboutNewMap = new Device_OpCodes("PhoneNotifiesAboutNewMap", 31, 43);
      GenericMsg = new Device_OpCodes("GenericMsg", 32, 99);
      PhoneInfoResponse = new Device_OpCodes("PhoneInfoResponse", 33, 210);
      OptionSelected = new Device_OpCodes("OptionSelected", 34, 53);
      DisplayMenuACK = new Device_OpCodes("DisplayMenuACK", 35, 50);
      CancelMenuACK = new Device_OpCodes("CancelMenuACK", 36, 51);
      CloseMenu = new Device_OpCodes("CloseMenu", 37, 52);
      SendP8Model = new Device_OpCodes("SendP8Model", 38, 102);
      TurnByTurnRoutePreviewACK = new Device_OpCodes("TurnByTurnRoutePreviewACK", 39, 55);
      TurnByTurnAction = new Device_OpCodes("TurnByTurnAction", 40, 57);
      RequestPlatinumVersions = new Device_OpCodes("RequestPlatinumVersions", 41, 59);
      StartOrCancelUpdate = new Device_OpCodes("StartOrCancelUpdate", 42, 60);
      DownloadUpdateChunk = new Device_OpCodes("DownloadUpdateChunk", 43, 61);
      ApplyUpdate = new Device_OpCodes("ApplyUpdate", 44, 63);
      TurnByTurnMuteState = new Device_OpCodes("TurnByTurnMuteState", 45, 65);
      StopNavigation = new Device_OpCodes("StopNavigation", 46, 88);
      $VALUES = new Device_OpCodes[] { 
          OpenStreamTest, IdentificatioRequest, RoutingStatus, CallControl, InitiatingCall, InboundMsg, ChecksumError, VolumeUpdate, CallLogReport, OutboundMsg, 
          SendDTMF, PhoneBook_ContactsRequest, MusicControl, PLTCommands, PlayingInformationStatus, GoToHomeReply, LenguajePlatinum, ResponseInfo, GoToHome, CallLog_Request, 
          WeatherRequest, WeatherResponse, VRSelectedOption, PhoneSwitchActiveNavigationP8, P8SwitchActiveNavigationPhoneStatus, SyncFavorites, SyncSettings, SendRouteACK, NotifiesP8AboutActiveRoute, P8CancelActiveRouteACK, 
          SendsTextForTTS, PhoneNotifiesAboutNewMap, GenericMsg, PhoneInfoResponse, OptionSelected, DisplayMenuACK, CancelMenuACK, CloseMenu, SendP8Model, TurnByTurnRoutePreviewACK, 
          TurnByTurnAction, RequestPlatinumVersions, StartOrCancelUpdate, DownloadUpdateChunk, ApplyUpdate, TurnByTurnMuteState, StopNavigation };
    }
    
    Device_OpCodes(int param1Int1) {
      this.opcode = param1Int1;
    }
    
    public int GetOpCode() {
      return this.opcode;
    }
  }
  
  public enum Platinum_OpCodes {
    AckWindowSize(0),
    ActiveCallStatus(0),
    CancelMenu(0),
    ChecksumError(0),
    CloseMenu(0),
    Connection(0),
    DestinationWayPoint(0),
    DisplayMenu(0),
    DownloadComplete(0),
    GenericMsgACK(0),
    GetChunk(0),
    GoToHomeRequest(0),
    IVR_GoToHome(0),
    IdentificationReply(1),
    IgnitionOnOff(1),
    IgnitionOnOffNotification(1),
    InboundMsg(1),
    InitiatingCallStatus(1),
    LenguajePlatinum(1),
    NotifiesAboutActiveRouteACK(1),
    OptionSelected(1),
    P8CancelActiveRoute(1),
    P8SwitchActiveNavigationPhone(1),
    PLTCommands(1),
    PhoneNotifiesAboutNewMap_ACK(1),
    PhoneSendsTextForTTS_ACK(1),
    PhoneSwitchActiveNavigationP8Status(1),
    PlayingInformation(1),
    RequestInfo(1),
    ResponseP8Version(1),
    ResponsePlatinumVersions(1),
    SendRouteToPhone(1),
    StartRouting(2),
    SyncFavoritesACK(2),
    SyncSettingsACK(2),
    TextMessageFromDeviceStatus(2),
    TurnByTurnDestination(2),
    TurnByTurnManeuverInfo(2),
    TurnByTurnRoutePreviewInfo(2),
    TurnByTurnStatus(2),
    UpdateStatus(2),
    Volume_Status(2),
    WeatherRequest(2),
    WeatherResponse(2);
    
    private static final Platinum_OpCodes[] $VALUES;
    
    private int opcode;
    
    static {
      InboundMsg = new Platinum_OpCodes("InboundMsg", 4, 5);
      IgnitionOnOff = new Platinum_OpCodes("IgnitionOnOff", 5, 6);
      ChecksumError = new Platinum_OpCodes("ChecksumError", 6, 7);
      TextMessageFromDeviceStatus = new Platinum_OpCodes("TextMessageFromDeviceStatus", 7, 10);
      ActiveCallStatus = new Platinum_OpCodes("ActiveCallStatus", 8, 11);
      PLTCommands = new Platinum_OpCodes("PLTCommands", 9, 16);
      PlayingInformation = new Platinum_OpCodes("PlayingInformation", 10, 17);
      GoToHomeRequest = new Platinum_OpCodes("GoToHomeRequest", 11, 18);
      LenguajePlatinum = new Platinum_OpCodes("LenguajePlatinum", 12, 19);
      RequestInfo = new Platinum_OpCodes("RequestInfo", 13, 21);
      IVR_GoToHome = new Platinum_OpCodes("IVR_GoToHome", 14, 23);
      Volume_Status = new Platinum_OpCodes("Volume_Status", 15, 24);
      WeatherRequest = new Platinum_OpCodes("WeatherRequest", 16, 28);
      WeatherResponse = new Platinum_OpCodes("WeatherResponse", 17, 29);
      PhoneSwitchActiveNavigationP8Status = new Platinum_OpCodes("PhoneSwitchActiveNavigationP8Status", 18, 33);
      P8SwitchActiveNavigationPhone = new Platinum_OpCodes("P8SwitchActiveNavigationPhone", 19, 34);
      SyncFavoritesACK = new Platinum_OpCodes("SyncFavoritesACK", 20, 35);
      SyncSettingsACK = new Platinum_OpCodes("SyncSettingsACK", 21, 36);
      SendRouteToPhone = new Platinum_OpCodes("SendRouteToPhone", 22, 38);
      NotifiesAboutActiveRouteACK = new Platinum_OpCodes("NotifiesAboutActiveRouteACK", 23, 40);
      P8CancelActiveRoute = new Platinum_OpCodes("P8CancelActiveRoute", 24, 41);
      PhoneSendsTextForTTS_ACK = new Platinum_OpCodes("PhoneSendsTextForTTS_ACK", 25, 42);
      PhoneNotifiesAboutNewMap_ACK = new Platinum_OpCodes("PhoneNotifiesAboutNewMap_ACK", 26, 43);
      GenericMsgACK = new Platinum_OpCodes("GenericMsgACK", 27, 99);
      IgnitionOnOffNotification = new Platinum_OpCodes("IgnitionOnOffNotification", 28, 600);
      DisplayMenu = new Platinum_OpCodes("DisplayMenu", 29, 50);
      CancelMenu = new Platinum_OpCodes("CancelMenu", 30, 51);
      CloseMenu = new Platinum_OpCodes("CloseMenu", 31, 52);
      OptionSelected = new Platinum_OpCodes("OptionSelected", 32, 53);
      TurnByTurnStatus = new Platinum_OpCodes("TurnByTurnStatus", 33, 54);
      TurnByTurnRoutePreviewInfo = new Platinum_OpCodes("TurnByTurnRoutePreviewInfo", 34, 55);
      TurnByTurnManeuverInfo = new Platinum_OpCodes("TurnByTurnManeuverInfo", 35, 56);
      TurnByTurnDestination = new Platinum_OpCodes("TurnByTurnDestination", 36, 58);
      ResponsePlatinumVersions = new Platinum_OpCodes("ResponsePlatinumVersions", 37, 59);
      ResponseP8Version = new Platinum_OpCodes("ResponseP8Version", 38, 102);
      GetChunk = new Platinum_OpCodes("GetChunk", 39, 61);
      DownloadComplete = new Platinum_OpCodes("DownloadComplete", 40, 62);
      UpdateStatus = new Platinum_OpCodes("UpdateStatus", 41, 64);
      DestinationWayPoint = new Platinum_OpCodes("DestinationWayPoint", 42, 85);
      AckWindowSize = new Platinum_OpCodes("AckWindowSize", 43, 69);
      $VALUES = new Platinum_OpCodes[] { 
          Connection, IdentificationReply, StartRouting, InitiatingCallStatus, InboundMsg, IgnitionOnOff, ChecksumError, TextMessageFromDeviceStatus, ActiveCallStatus, PLTCommands, 
          PlayingInformation, GoToHomeRequest, LenguajePlatinum, RequestInfo, IVR_GoToHome, Volume_Status, WeatherRequest, WeatherResponse, PhoneSwitchActiveNavigationP8Status, P8SwitchActiveNavigationPhone, 
          SyncFavoritesACK, SyncSettingsACK, SendRouteToPhone, NotifiesAboutActiveRouteACK, P8CancelActiveRoute, PhoneSendsTextForTTS_ACK, PhoneNotifiesAboutNewMap_ACK, GenericMsgACK, IgnitionOnOffNotification, DisplayMenu, 
          CancelMenu, CloseMenu, OptionSelected, TurnByTurnStatus, TurnByTurnRoutePreviewInfo, TurnByTurnManeuverInfo, TurnByTurnDestination, ResponsePlatinumVersions, ResponseP8Version, GetChunk, 
          DownloadComplete, UpdateStatus, DestinationWayPoint, AckWindowSize };
    }
    
    Platinum_OpCodes(int param1Int1) {
      this.opcode = param1Int1;
    }
    
    public static Platinum_OpCodes GetValue(int param1Int) {
      Platinum_OpCodes[] arrayOfPlatinum_OpCodes = values();
      for (byte b = 0; b < arrayOfPlatinum_OpCodes.length; b++) {
        if (arrayOfPlatinum_OpCodes[b].GetOpCode() == param1Int)
          return arrayOfPlatinum_OpCodes[b]; 
      } 
      return Connection;
    }
    
    public int GetOpCode() {
      return this.opcode;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/MessagesObjects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */