package com.roadtrack.onstar.BO;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.DAO.ManagerConnectionWS;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.AvailableTickeRequest;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.SyncInsertResponse;
import com.roadtrack.onstar.VO.SyncVO;
import com.roadtrack.onstar.VO.TokenRequestO;
import com.roadtrack.onstar.VO.TokenResponseO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.VO.ValidActionsVO;
import com.roadtrack.onstar.VO.renewalTransactionSubscription;
import com.roadtrack.onstar.gson.GsonC;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.CryptedSoapGenerator;
import com.roadtrack.onstar.utils.NetUtilities;
import com.roadtrack.onstar.utils.Utilities;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class WsAccess {
  private static final String NAMESPACE_PRODUCTIVO;
  
  private static final String SOAP_ACTION_PRODUCTIVO;
  
  private static final String URL_ANDROID_AUTO;
  
  private static final String URL_PRODUCTIVA = GlobalMembers.URL_WCF;
  
  static Context ctx;
  
  static onstarApplication rtApp;
  
  private String ALRT_Error;
  
  private String MenuIvr;
  
  private String MenuTrafic;
  
  private final String UrlUrlActivateNotificationEmail = GlobalMembers.URL_WCF;
  
  private String aux_result = "";
  
  private String diagnostic;
  
  private String global_lbl_accionabrirpuertassl_1;
  
  private String global_lbl_accionalertabocina_1;
  
  private String global_lbl_accionalertamovsl_1;
  
  private String global_lbl_accionalertavaletsl_1;
  
  private String global_lbl_accionalertavelsl_1;
  
  private String global_lbl_accionalertavelsl_2;
  
  private String global_lbl_accioncerrarpuertassl_1;
  
  private String global_lbl_acciondesactivarpincodesl_1;
  
  private String global_lbl_accionlocalizame_1;
  
  private String global_lbl_accionluces_1;
  
  private String global_lbl_accionlucesybocinasl_1;
  
  private String global_lbl_accionsigueme_1;
  
  private String global_lbl_asistencia_1;
  
  private String global_lbl_configuracion_1;
  
  private String global_lbl_emergencia_1;
  
  private String global_lbl_navegacion_1;
  
  private String global_lbl_notificaciones_1;
  
  private String global_navigation_fav_lbl_historial_1;
  
  private int locatorErrorGetStatus1 = 3;
  
  private int locatorErrorGetStatus2 = 5;
  
  private String login_global_popup_lbl_sindatos_2;
  
  private String navigation_lbl_calculandoruta_1;
  
  private String nothing;
  
  private StringsResourcesVO stringsResourcesVO;
  
  Vector<String> vecDeviceId = new Vector<String>();
  
  ManagerConnectionWS wservice = new ManagerConnectionWS();
  
  static {
    URL_ANDROID_AUTO = GlobalMembers.URL_ANDROID_AUTO;
    NAMESPACE_PRODUCTIVO = GlobalMembers.NAMESPACE_WCF;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(NAMESPACE_PRODUCTIVO);
    stringBuilder.append("IService1/%1$s");
    SOAP_ACTION_PRODUCTIVO = stringBuilder.toString();
    stringBuilder = new StringBuilder();
    stringBuilder.append(NAMESPACE_PRODUCTIVO);
    stringBuilder.append("%1$s");
    stringBuilder.toString();
  }
  
  public WsAccess(Context paramContext) {
    ctx = paramContext;
    rtApp = (onstarApplication)paramContext.getApplicationContext();
    rtApp.setGeneralContext(paramContext);
    this.stringsResourcesVO = new StringsResourcesVO();
    this.global_lbl_accionsigueme_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionsigueme_1, 2131689885);
    this.global_lbl_accionluces_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionluces_1, 2131689878);
    this.global_lbl_accionlucesybocinasl_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionlucesybocinasl_1, 2131689880);
    this.global_lbl_accioncerrarpuertassl_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accioncerrarpuertassl_1, 2131689855);
    this.global_lbl_accionabrirpuertassl_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionabrirpuertassl_1, 2131689843);
    this.global_lbl_acciondesactivarpincodesl_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_acciondesactivarpincodesl_1, 2131689857);
    this.global_lbl_accionalertamovsl_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionalertamovsl_1, 2131689846);
    this.global_lbl_accionalertavelsl_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionalertavelsl_1, 2131689852);
    this.global_lbl_accionalertavaletsl_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionalertavaletsl_1, 2131689850);
    this.global_lbl_navegacion_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_navegacion_1, 2131689929);
    this.diagnostic = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.diagnostic, 2131689753);
    this.global_lbl_notificaciones_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_notificaciones_1, 2131689930);
    this.global_lbl_configuracion_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_configuracion_1, 2131689913);
    this.MenuTrafic = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.MenuTrafic, 2131689523);
    this.global_navigation_fav_lbl_historial_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_navigation_fav_lbl_historial_1, 2131689942);
    this.global_lbl_emergencia_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_emergencia_1, 2131689919);
    this.global_lbl_asistencia_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    this.MenuIvr = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.MenuIvr, 2131689519);
    this.global_lbl_accionalertabocina_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionalertabocina_1, 2131689844);
    this.navigation_lbl_calculandoruta_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.navigation_lbl_calculandoruta_1, 2131690148);
    this.global_lbl_accionalertavelsl_2 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionalertavelsl_2, 2131689853);
    this.global_lbl_accionlocalizame_1 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.global_lbl_accionlocalizame_1, 2131689877);
    this.login_global_popup_lbl_sindatos_2 = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.login_global_popup_lbl_sindatos_2, 2131690012);
    this.ALRT_Error = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.ALRT_Error, 2131689472);
    this.nothing = Utilities.getStringFromConfigList(paramContext, this.stringsResourcesVO.nothing, 2131690207);
    createValidApplicactionList();
    createValidActionsList();
  }
  
  public static ArrayList<Integer> RetrieveDeleteFromWcfSyncDelete(String paramString) {
    ArrayList<Integer> arrayList = new ArrayList();
    String[] arrayOfString = favoritos(paramString, 1);
    for (byte b = 0; b < arrayOfString.length; b++)
      arrayList.add(Integer.valueOf(Integer.valueOf(arrayOfString[0]).intValue())); 
    return arrayList;
  }
  
  public static ArrayList<SyncVO> RetrieveFromWcfSyncSelect(String paramString1, String paramString2, String paramString3, String paramString4) {
    ArrayList<SyncVO> arrayList = new ArrayList();
    String[] arrayOfString = favoritos(paramString1, 0);
    for (byte b = 0; b < arrayOfString.length; b++) {
      String[] arrayOfString1 = arrayOfString[b].split("\\|");
      String str1 = arrayOfString1[1];
      String str2 = arrayOfString1[4];
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(arrayOfString1[2]);
      stringBuilder.append(",");
      stringBuilder.append(arrayOfString1[3]);
      arrayList.add(new SyncVO("1", "", paramString4, paramString2, null, "0", str1, str2, "date", paramString3, "Favorites", "Other", stringBuilder.toString(), arrayOfString1[0], arrayOfString1[0]));
    } 
    if (arrayList.size() > 0)
      Collections.sort(arrayList, new Comparator<SyncVO>() {
            public int compare(SyncVO param1SyncVO1, SyncVO param1SyncVO2) {
              return param1SyncVO1.getName().toLowerCase().compareTo(param1SyncVO2.getName().toLowerCase());
            }
          }); 
    return arrayList;
  }
  
  public static ArrayList<SyncInsertResponse> RetrieveSyncInsertFromWcf(String paramString) {
    ArrayList<SyncInsertResponse> arrayList = new ArrayList();
    String[] arrayOfString = favoritos(paramString, 1);
    for (byte b = 0; b < arrayOfString.length; b++)
      arrayList.add(new SyncInsertResponse(Integer.valueOf(arrayOfString[0]).intValue(), Integer.valueOf(arrayOfString[1]).intValue(), arrayOfString[2], 0)); 
    return arrayList;
  }
  
  private void createValidActionsList() {
    ArrayList<ValidActionsVO> arrayList = new ArrayList();
    ValidActionsVO validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.FindMe.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionlocalizame_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.FollowMe.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionsigueme_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.HornLigths.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionlucesybocinasl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.DoorsLock.GetCode());
    validActionsVO.setActionName(this.global_lbl_accioncerrarpuertassl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.DoorsUnlock.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionabrirpuertassl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Disarm.GetCode());
    validActionsVO.setActionName(this.global_lbl_acciondesactivarpincodesl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Parking.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertamovsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Speed.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertavelsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.textToPND.GetCode());
    validActionsVO.setActionName(ctx.getString(2131690207));
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Horn.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertabocina_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.SendPNDNavigationCommand.GetCode());
    validActionsVO.setActionName(ctx.getString(2131690207));
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.StartEngine.GetCode());
    validActionsVO.setActionName(ctx.getString(2131690207));
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.valet.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertavaletsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.SpeedAlways.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertavelsl_2);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.FollowMeUUx.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionsigueme_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ParkingUUx.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertamovsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.SpeedUUx.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertavelsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Ligths.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionluces_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.HornF1.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertabocina_1);
    arrayList.add(validActionsVO);
    rtApp.setValidActionsWS(arrayList);
  }
  
  private void createValidApplicactionList() {
    ArrayList<ValidActionsVO> arrayList = new ArrayList();
    ValidActionsVO validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.FollowMe.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionsigueme_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.HornLigths.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionlucesybocinasl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.DoorsLock.GetCode());
    validActionsVO.setActionName(this.global_lbl_accioncerrarpuertassl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.DoorsUnlock.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionabrirpuertassl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Disarm.GetCode());
    validActionsVO.setActionName(this.global_lbl_acciondesactivarpincodesl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Parking.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertamovsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Speed.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertavelsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.textToPND.GetCode());
    validActionsVO.setActionName(this.nothing);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.SendPNDNavigationCommand.GetCode());
    validActionsVO.setActionName(this.nothing);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.StartEngine.GetCode());
    validActionsVO.setActionName(this.nothing);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.valet.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertavaletsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Navigation.GetCode());
    validActionsVO.setActionName(this.global_lbl_navegacion_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ActionDTC.GetCode());
    validActionsVO.setActionName(this.diagnostic);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ActionNotifications.GetCode());
    validActionsVO.setActionName(this.global_lbl_notificaciones_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ActionSettings.GetCode());
    validActionsVO.setActionName(this.global_lbl_configuracion_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ActionTraffic.GetCode());
    validActionsVO.setActionName(this.MenuTrafic);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ActionHistorical.GetCode());
    validActionsVO.setActionName(this.global_navigation_fav_lbl_historial_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ActionSettings.GetCode());
    validActionsVO.setActionName(this.global_lbl_emergencia_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ActionAssistance.GetCode());
    validActionsVO.setActionName(this.global_lbl_asistencia_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ActionIvr.GetCode());
    validActionsVO.setActionName(this.MenuIvr);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Horn.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertabocina_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.NavigationWithTraffic.GetCode());
    validActionsVO.setActionName(this.navigation_lbl_calculandoruta_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.SpeedAlways.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertavelsl_2);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.FollowMeUUx.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionsigueme_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.ParkingUUx.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertamovsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.SpeedUUx.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertavelsl_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.Ligths.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionluces_1);
    arrayList.add(validActionsVO);
    validActionsVO = new ValidActionsVO();
    validActionsVO.setActionCode(Enums.Services.HornF1.GetCode());
    validActionsVO.setActionName(this.global_lbl_accionalertabocina_1);
    arrayList.add(validActionsVO);
    rtApp.setValidActionsPlan(arrayList);
  }
  
  private static String[] favoritos(String paramString, int paramInt) {
    String[] arrayOfString;
    if (paramInt != 0) {
      if (paramInt != 1) {
        paramString = null;
      } else {
        arrayOfString = paramString.substring(3, paramString.length()).split("\\|");
      } 
    } else {
      arrayOfString = arrayOfString.substring(5, arrayOfString.length()).split("\\*\\|");
    } 
    return arrayOfString;
  }
  
  private String[] getFollowMePoints(String paramString) {
    return paramString.split("\\|\\*\\|");
  }
  
  private String replaceCharacter(String paramString) {
    return paramString.replace("\n", "").trim().replace("+", "-").replace("/", "_");
  }
  
  public static final String unRegisterDevice(String paramString1, String paramString2, String paramString3, String paramString4, Activity paramActivity) {
    String str = Utilities.Crypt(paramString3).replace("\n", "").trim();
    try {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      this();
      linkedHashMap.put("h", paramString1);
      linkedHashMap.put("t", paramString2);
      linkedHashMap.put("id", str);
      linkedHashMap.put("appid", paramString4);
      SimpleRequestObject simpleRequestObject = new SimpleRequestObject();
      this((LinkedHashMap)linkedHashMap, GlobalMembers.URL_UNREGISTER_DEVICE, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF);
      simpleRequestObject.set_keyStoreId(2131623950, GlobalMembers.nameKeystoreService);
      RequestManager requestManager = new RequestManager();
      this(paramActivity, simpleRequestObject);
      requestManager.sendRequest(2);
      String str1 = requestManager.getRespuesta();
    } catch (IOException null) {
      Utilities.escribeArchivo("WsAccess", "GCM: ERROR: DEVICEUNREGISTER IOException", exception.getMessage());
      exception = null;
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "GCM: ERROR: DEVICEUNREGISTER Exception", exception.getMessage());
    } 
    Utilities.escribeArchivo("WsAccess", "EXITAPP", "After unregisterDevice");
    return (String)exception;
  }
  
  private TokenResponseO validateResponseToken(TokenResponseO paramTokenResponseO) {
    if (paramTokenResponseO.getLresp1().equals("null") || paramTokenResponseO.getLresp1() == null)
      paramTokenResponseO.setLresp1(""); 
    if (paramTokenResponseO.getLresp2().equals("null") || paramTokenResponseO.getLresp2() == null) {
      paramTokenResponseO.setLresp2("");
    } else {
      paramTokenResponseO.setLresp2(Utilities.DecryptMoip(paramTokenResponseO.getLresp2()));
    } 
    if (paramTokenResponseO.getLresp3().equals("null") || paramTokenResponseO.getLresp3() == null) {
      paramTokenResponseO.setLresp3("");
    } else {
      paramTokenResponseO.setLresp3(Utilities.DecryptMoip(paramTokenResponseO.getLresp3()));
    } 
    if (paramTokenResponseO.getLresp4().equals("null") || paramTokenResponseO.getLresp4() == null) {
      paramTokenResponseO.setLresp4("");
    } else {
      paramTokenResponseO.setLresp4(Utilities.DecryptMoip(paramTokenResponseO.getLresp4()));
    } 
    if (paramTokenResponseO.getLresp5().equals("null") || paramTokenResponseO.getLresp5() == null) {
      paramTokenResponseO.setLresp5("");
    } else {
      paramTokenResponseO.setLresp5(Utilities.DecryptMoip(paramTokenResponseO.getLresp5()));
    } 
    if (paramTokenResponseO.getLresp6().equals("null") || paramTokenResponseO.getLresp6() == null) {
      paramTokenResponseO.setLresp6("");
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("lresp1 = ");
      stringBuilder1.append(paramTokenResponseO.getLresp1());
      stringBuilder1.append(" lresp2 = ");
      stringBuilder1.append(paramTokenResponseO.getLresp2());
      stringBuilder1.append(" lresp3 = ");
      stringBuilder1.append(paramTokenResponseO.getLresp3());
      stringBuilder1.append(" lresp4 = ");
      stringBuilder1.append(paramTokenResponseO.getLresp4());
      stringBuilder1.append(" lresp5 = ");
      stringBuilder1.append(paramTokenResponseO.getLresp5());
      stringBuilder1.append(" lresp6 = ");
      stringBuilder1.append(paramTokenResponseO.getLresp6());
      Utilities.escribeArchivo("WsAccess", "TokenResponse", stringBuilder1.toString());
      return paramTokenResponseO;
    } 
    paramTokenResponseO.setLresp6(Utilities.DecryptMoip(paramTokenResponseO.getLresp6()));
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("lresp1 = ");
    stringBuilder.append(paramTokenResponseO.getLresp1());
    stringBuilder.append(" lresp2 = ");
    stringBuilder.append(paramTokenResponseO.getLresp2());
    stringBuilder.append(" lresp3 = ");
    stringBuilder.append(paramTokenResponseO.getLresp3());
    stringBuilder.append(" lresp4 = ");
    stringBuilder.append(paramTokenResponseO.getLresp4());
    stringBuilder.append(" lresp5 = ");
    stringBuilder.append(paramTokenResponseO.getLresp5());
    stringBuilder.append(" lresp6 = ");
    stringBuilder.append(paramTokenResponseO.getLresp6());
    Utilities.escribeArchivo("WsAccess", "TokenResponse", stringBuilder.toString());
    return paramTokenResponseO;
  }
  
  public List<Object> GetCommandStatus(String paramString, int paramInt) {
    if (paramString.equals("-1"))
      return null; 
    ArrayList<Integer> arrayList = new ArrayList();
    GlobalMembers.findMeGLIM = false;
    try {
      boolean bool;
      int i;
      int j;
      String[] arrayOfString;
      String str3;
      this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "GetCommandStatus" }));
      this.wservice.set_methodName("GetCommandStatus");
      this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
      this.wservice.set_url(URL_PRODUCTIVA);
      String str1 = rtApp.getSessionKey();
      String str2 = rtApp.getUserAccessData()[0];
      String str4 = rtApp.getUserAccessData()[1];
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      this();
      hashtable.put("sessionKey", str1);
      hashtable.put("login", str2);
      hashtable.put("password", str4);
      hashtable.put("messageId", Integer.valueOf(Integer.parseInt(paramString)));
      hashtable.put("applicationSourceId", Integer.valueOf(13));
      boolean bool1 = GlobalMembers.bFinalizaronIntentos;
      if (!bool1 && MainActivity.onFindMe.booleanValue()) {
        GlobalMembers.findMeGLIM = true;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(NAMESPACE_PRODUCTIVO);
        stringBuilder.append("IService1/GetLastIncomingMessage");
        String str = stringBuilder.toString();
        str4 = rtApp.getAccountID();
        str3 = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess").getDeviceId();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("accountId", str4);
        linkedHashMap.put("deviceId", str3);
        linkedHashMap.put("actionId", "1");
        linkedHashMap.put("messageId", paramString);
        try {
          SoapRequestObject soapRequestObject = new SoapRequestObject();
          this((LinkedHashMap)linkedHashMap, str, "GetLastIncomingMessage", NAMESPACE_PRODUCTIVO, GlobalMembers.URL_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND);
          soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
          RequestManager requestManager = new RequestManager();
          this(ctx, soapRequestObject);
          RequestManager.OnPostExecuteListener onPostExecuteListener = new RequestManager.OnPostExecuteListener() {
              final WsAccess this$0;
              
              public void onPostExecuteListener(String param1String, int param1Int) {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("response: ");
                stringBuilder1.append(param1String);
                String str = stringBuilder1.toString();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("responseCode: ");
                stringBuilder2.append(param1Int);
                Utilities.escribeArchivo("WsAccess", str, stringBuilder2.toString());
                WsAccess.access$002(WsAccess.this, param1String);
              }
            };
          super(this);
          requestManager.setOnPostExecuteListener(onPostExecuteListener);
          requestManager.sendRequest(1);
          arrayOfString = this.aux_result.toString().split("\\|");
          try {
            GlobalMembers.lastGetLastIncomingMessageFindMe = arrayOfString;
          } catch (Exception null) {}
        } catch (Exception exception1) {
          str = null;
        } 
        Utilities.escribeArchivo("WsAccess", "Error: GetCommandStatus():", exception1.getMessage());
      } 
      bool1 = GlobalMembers.bFinalizaronIntentos;
      if (!bool1) {
        String[] arrayOfString1 = this.wservice.GetDataWSNet((Hashtable)str3, ctx).toString().split("\\|");
        GlobalMembers.ws_responceFindMe = arrayOfString1;
        if (arrayOfString1 != null) {
          bool = true;
        } else {
          bool = false;
        } 
        if (arrayOfString1.length > 2) {
          i = 1;
        } else {
          i = 0;
        } 
        if ((bool & i) != 0) {
          DBFunctions dBFunctions = new DBFunctions();
          this(ctx);
          arrayOfString = arrayOfString1;
          if (dBFunctions.userDataTableHandler(rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(ctx, rtApp).getDeviceId(), arrayOfString1[2], false)) {
            arrayOfString1[0] = "3";
            arrayOfString = arrayOfString1;
          } 
        } else {
          arrayOfString = arrayOfString1;
        } 
      } else {
        GlobalMembers.findMeGLIM = true;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(NAMESPACE_PRODUCTIVO);
        stringBuilder.append("IService1/GetLastIncomingMessage");
        String str = stringBuilder.toString();
        str3 = rtApp.getAccountID();
        str4 = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess").getDeviceId();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("accountId", str3);
        linkedHashMap.put("deviceId", str4);
        linkedHashMap.put("actionId", "1");
        linkedHashMap.put("messageId", paramString);
        try {
          String str6;
          SoapRequestObject soapRequestObject = new SoapRequestObject();
          this((LinkedHashMap)linkedHashMap, str, "GetLastIncomingMessage", GlobalMembers.NAMESPACE_WCF, GlobalMembers.URL_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND);
          soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
          RequestManager requestManager = new RequestManager();
          this(ctx, soapRequestObject);
          RequestManager.OnPostExecuteListener onPostExecuteListener = new RequestManager.OnPostExecuteListener() {
              final WsAccess this$0;
              
              public void onPostExecuteListener(String param1String, int param1Int) {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("response: ");
                stringBuilder1.append(param1String);
                String str = stringBuilder1.toString();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("responseCode: ");
                stringBuilder2.append(param1Int);
                Utilities.escribeArchivo("WsAccess", str, stringBuilder2.toString());
                WsAccess.access$002(WsAccess.this, param1String);
              }
            };
          super(this);
          requestManager.setOnPostExecuteListener(onPostExecuteListener);
          requestManager.sendRequest(1);
          if (this.aux_result != null && this.aux_result.toString().equalsIgnoreCase("0|")) {
            if (GlobalMembers.lastGetLastIncomingMessageFindMe != null) {
              String[] arrayOfString1 = GlobalMembers.lastGetLastIncomingMessageFindMe;
            } else {
              str6 = "3|0|1|0|0|0|2|0";
            } 
          } else {
            str6 = "";
          } 
          arrayOfString = str6.toString().split("\\|");
          if (arrayOfString != null)
            try {
              if (arrayOfString.length > 0 && arrayOfString[1].equalsIgnoreCase("0") && MainActivity.onFindMe.booleanValue())
                arrayOfString[0] = "3"; 
            } catch (Exception exception1) {} 
        } catch (Exception exception) {
          exception = null;
        } 
      } 
      byte b = 2;
      if (exception.length < 4 && GlobalMembers.bFinalizaronIntentos) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool) {
        arrayOfString = GlobalMembers.ws_responceFindMe;
        arrayOfString[0] = "3";
      } 
      try {
        if (!GlobalMembers.bFinalizaronIntentos || bool) {
          GlobalMembers.findmeCode = Integer.parseInt(arrayOfString[8]);
        } else {
          GlobalMembers.findmeCode = Integer.parseInt(arrayOfString[6]);
        } 
      } catch (Exception exception1) {
        Utilities.escribeArchivo("WsAccess", "Error: getCommandStatus", exception1.getMessage());
      } 
      if (!GlobalMembers.bFinalizaronIntentos || bool) {
        i = Integer.parseInt(arrayOfString[0]);
        j = Integer.parseInt(arrayOfString[1]);
      } else {
        i = Integer.parseInt(arrayOfString[0]);
        j = Integer.parseInt(arrayOfString[0]);
      } 
      if (!GlobalMembers.bFinalizaronIntentos || bool || MainActivity.onFindMe.booleanValue()) {
        arrayList.add(Integer.valueOf(i));
      } else {
        if (i <= 0)
          b = 0; 
        arrayList.add(Integer.valueOf(b));
      } 
      arrayList.add(Integer.valueOf(j));
      if (!GlobalMembers.bFinalizaronIntentos || bool) {
        str2 = arrayOfString[10];
      } else {
        str2 = "12345678910";
      } 
      if (!str2.equals(""))
        rtApp.setSessionKey(str2); 
      if ((i != this.locatorErrorGetStatus1 || i != this.locatorErrorGetStatus2) && paramInt != 1)
        errorsDescrip("GetCommandStatus", i, Integer.parseInt(arrayOfString[1]), String.valueOf(paramString)); 
      if (!GlobalMembers.bFinalizaronIntentos || bool) {
        if ((arrayOfString != null && arrayOfString.length >= 6) || (paramInt == 1 && i > 0)) {
          arrayList.add(String.valueOf(arrayOfString[6]));
          arrayList.add(String.valueOf(arrayOfString[5]));
          str2 = String.valueOf(arrayOfString[6]);
          str3 = String.valueOf(arrayOfString[5]);
          str4 = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess").getDeviceId();
          GlobalMembers.contexGlobal = ctx;
          FindMePointsHandler findMePointsHandler1 = new FindMePointsHandler();
          this();
          if (!str4.equals(""))
            findMePointsHandler1.insertFindmePoint(str4, str2, str3); 
        } 
        arrayList.add(arrayOfString[4]);
        return (List)arrayList;
      } 
      GlobalMembers.bFinalizaronIntentos = false;
      arrayList.add(String.valueOf(arrayOfString[3]));
      arrayList.add(String.valueOf(arrayOfString[4]));
      str2 = Utilities.getUTCDateTime("MM/dd/yyyy hh:mm:ss a", arrayOfString[7].toString());
      str4 = String.valueOf(arrayOfString[3]);
      paramString = String.valueOf(arrayOfString[4]);
      String str5 = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess").getDeviceId();
      GlobalMembers.contexGlobal = ctx;
      FindMePointsHandler findMePointsHandler = new FindMePointsHandler();
      this();
      if (!str5.equals(""))
        findMePointsHandler.insertFindmePoint(str5, str4, paramString); 
      if (arrayOfString[1].equals("0")) {
        arrayList.add(Utilities.getDateTime(10));
      } else {
        arrayList.add(str2);
      } 
      return (List)arrayList;
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: GetCommandStatus()", exception.getMessage());
      return null;
    } 
  }
  
  public String[] GetLastIncomingMessage(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(NAMESPACE_PRODUCTIVO);
    stringBuilder.append("IService1/GetLastIncomingMessage");
    String str2 = stringBuilder.toString();
    String str3 = rtApp.getAccountID();
    String str1 = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess").getDeviceId();
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    linkedHashMap.put("accountId", str3);
    linkedHashMap.put("deviceId", str1);
    linkedHashMap.put("actionId", "2");
    linkedHashMap.put("messageId", paramString);
    try {
      SoapRequestObject soapRequestObject = new SoapRequestObject();
      this((LinkedHashMap)linkedHashMap, str2, "GetLastIncomingMessage", GlobalMembers.NAMESPACE_WCF, GlobalMembers.URL_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager();
      this(ctx, soapRequestObject);
      RequestManager.OnPostExecuteListener onPostExecuteListener = new RequestManager.OnPostExecuteListener() {
          final WsAccess this$0;
          
          final String val$deviceId;
          
          final String val$messageId;
          
          public void onPostExecuteListener(String param1String, int param1Int) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MessageID");
            stringBuilder.append(messageId);
            stringBuilder.append(" DeviceID");
            stringBuilder.append(deviceId);
            stringBuilder.append(" response: ");
            stringBuilder.append(param1String);
            String str = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append("responseCode: ");
            stringBuilder.append(param1Int);
            Utilities.escribeArchivo("WsAccess", str, stringBuilder.toString());
            WsAccess.access$002(WsAccess.this, param1String);
          }
        };
      super(this, paramString, str1);
      requestManager.setOnPostExecuteListener(onPostExecuteListener);
      requestManager.sendRequest(1);
      String[] arrayOfString = getFollowMePoints(this.aux_result);
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: GetLastIncomingMessage()", exception.getMessage());
      exception = null;
    } 
    return (String[])exception;
  }
  
  public String[] GetLastIncomingMessageHistory(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(GlobalMembers.NAMESPACE_WCF);
    stringBuilder.append("IService1/GetLastIncomingMessageHistory");
    String str1 = stringBuilder.toString();
    String str3 = rtApp.getAccountID();
    String str2 = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess").getDeviceId();
    try {
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      this();
      linkedHashMap.put("accountId", str3);
      linkedHashMap.put("deviceId", str2);
      linkedHashMap.put("actionId", "2");
      linkedHashMap.put("messageId", paramString);
      SoapRequestObject soapRequestObject = new SoapRequestObject();
      this((LinkedHashMap)linkedHashMap, str1, "GetLastIncomingMessageHistory", GlobalMembers.NAMESPACE_WCF, GlobalMembers.URL_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_WCF);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager();
      this(ctx, soapRequestObject);
      RequestManager.OnPostExecuteListener onPostExecuteListener = new RequestManager.OnPostExecuteListener() {
          final WsAccess this$0;
          
          public void onPostExecuteListener(String param1String, int param1Int) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("response: ");
            stringBuilder1.append(param1String);
            String str = stringBuilder1.toString();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("responseCode: ");
            stringBuilder2.append(param1Int);
            Utilities.escribeArchivo("WsAccess", str, stringBuilder2.toString());
            WsAccess.access$002(WsAccess.this, param1String);
          }
        };
      super(this);
      requestManager.setOnPostExecuteListener(onPostExecuteListener);
      requestManager.sendRequest(1);
      String[] arrayOfString = getFollowMePoints(this.aux_result);
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: GetLastIncomingMessageHistory()", exception.getMessage());
      exception = null;
    } 
    return (String[])exception;
  }
  
  @SuppressLint({"NewApi"})
  public boolean GetUserDeviceShortG2_WS() {
    // Byte code:
    //   0: ldc_w 'GetUserDeviceShortG2'
    //   3: astore #10
    //   5: new java/util/ArrayList
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: astore #15
    //   14: getstatic com/roadtrack/onstar/BO/WsAccess.rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   17: invokevirtual clearMdeviceUserList : ()V
    //   20: getstatic com/roadtrack/onstar/ui/login/LoginActivity.strVehicleList : Ljava/lang/String;
    //   23: astore #8
    //   25: aload_0
    //   26: getfield vecDeviceId : Ljava/util/Vector;
    //   29: invokevirtual clear : ()V
    //   32: aload #8
    //   34: invokevirtual length : ()I
    //   37: ifle -> 129
    //   40: aload #8
    //   42: ldc_w '\\r'
    //   45: invokevirtual indexOf : (Ljava/lang/String;)I
    //   48: istore_3
    //   49: iload_3
    //   50: istore_2
    //   51: iload_3
    //   52: ifge -> 61
    //   55: aload #8
    //   57: invokevirtual length : ()I
    //   60: istore_2
    //   61: iload_2
    //   62: ifge -> 68
    //   65: goto -> 129
    //   68: aload #8
    //   70: iconst_0
    //   71: iload_2
    //   72: invokevirtual substring : (II)Ljava/lang/String;
    //   75: astore #9
    //   77: aload_0
    //   78: getfield vecDeviceId : Ljava/util/Vector;
    //   81: aload #9
    //   83: invokevirtual add : (Ljava/lang/Object;)Z
    //   86: pop
    //   87: iload_2
    //   88: aload #8
    //   90: invokevirtual length : ()I
    //   93: if_icmpne -> 107
    //   96: aload #8
    //   98: iload_2
    //   99: invokevirtual substring : (I)Ljava/lang/String;
    //   102: astore #8
    //   104: goto -> 32
    //   107: iload_2
    //   108: aload #8
    //   110: invokevirtual length : ()I
    //   113: if_icmpge -> 32
    //   116: aload #8
    //   118: iload_2
    //   119: iconst_1
    //   120: iadd
    //   121: invokevirtual substring : (I)Ljava/lang/String;
    //   124: astore #8
    //   126: goto -> 32
    //   129: aload_0
    //   130: getfield vecDeviceId : Ljava/util/Vector;
    //   133: invokevirtual size : ()I
    //   136: iconst_1
    //   137: isub
    //   138: istore_2
    //   139: iload_2
    //   140: iflt -> 178
    //   143: aload_0
    //   144: getfield vecDeviceId : Ljava/util/Vector;
    //   147: iload_2
    //   148: invokevirtual get : (I)Ljava/lang/Object;
    //   151: checkcast java/lang/String
    //   154: ldc_w '|'
    //   157: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   160: ifne -> 172
    //   163: aload_0
    //   164: getfield vecDeviceId : Ljava/util/Vector;
    //   167: iload_2
    //   168: invokevirtual remove : (I)Ljava/lang/Object;
    //   171: pop
    //   172: iinc #2, -1
    //   175: goto -> 139
    //   178: new com/roadtrack/onstar/DAO/DBFunctions
    //   181: astore #16
    //   183: aload #16
    //   185: getstatic com/roadtrack/onstar/BO/WsAccess.ctx : Landroid/content/Context;
    //   188: invokespecial <init> : (Landroid/content/Context;)V
    //   191: getstatic com/roadtrack/onstar/BO/WsAccess.rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   194: invokevirtual getUserAccessData : ()[Ljava/lang/String;
    //   197: iconst_0
    //   198: aaload
    //   199: invokevirtual toString : ()Ljava/lang/String;
    //   202: astore #12
    //   204: aload #16
    //   206: aload #12
    //   208: invokevirtual getSelectedVehicle : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/VehicleCatalogVO;
    //   211: astore #11
    //   213: aload #16
    //   215: aload #12
    //   217: invokevirtual deleteAllVehicleCatalog : (Ljava/lang/String;)I
    //   220: pop
    //   221: ldc ''
    //   223: astore #8
    //   225: ldc ''
    //   227: astore #9
    //   229: iconst_0
    //   230: istore_3
    //   231: iconst_1
    //   232: istore #6
    //   234: iconst_0
    //   235: istore_2
    //   236: iload_3
    //   237: aload_0
    //   238: getfield vecDeviceId : Ljava/util/Vector;
    //   241: invokevirtual size : ()I
    //   244: if_icmpge -> 1784
    //   247: aload_0
    //   248: getfield vecDeviceId : Ljava/util/Vector;
    //   251: iload_3
    //   252: invokevirtual get : (I)Ljava/lang/Object;
    //   255: checkcast java/lang/String
    //   258: ldc_w '\|'
    //   261: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   264: astore #17
    //   266: aload #17
    //   268: iconst_0
    //   269: aload #17
    //   271: iconst_0
    //   272: aaload
    //   273: aload #17
    //   275: iconst_0
    //   276: aaload
    //   277: ldc_w '='
    //   280: invokevirtual indexOf : (Ljava/lang/String;)I
    //   283: iconst_1
    //   284: iadd
    //   285: invokevirtual substring : (I)Ljava/lang/String;
    //   288: aastore
    //   289: aload #17
    //   291: iconst_0
    //   292: aaload
    //   293: astore #8
    //   295: aload #17
    //   297: bipush #17
    //   299: aaload
    //   300: astore #13
    //   302: aload #17
    //   304: arraylength
    //   305: bipush #18
    //   307: if_icmple -> 320
    //   310: aload #17
    //   312: bipush #18
    //   314: aaload
    //   315: astore #9
    //   317: goto -> 324
    //   320: ldc ''
    //   322: astore #9
    //   324: aload #8
    //   326: ldc_w 'OK'
    //   329: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   332: istore #7
    //   334: iload #7
    //   336: ifeq -> 342
    //   339: goto -> 1777
    //   342: aload #8
    //   344: invokestatic parseInt : (Ljava/lang/String;)I
    //   347: istore #4
    //   349: iload #6
    //   351: istore #7
    //   353: iload #4
    //   355: iconst_1
    //   356: if_icmpeq -> 382
    //   359: aload_0
    //   360: aload #10
    //   362: aload #8
    //   364: invokestatic parseInt : (Ljava/lang/String;)I
    //   367: iconst_0
    //   368: ldc ''
    //   370: invokevirtual errorsDescrip : (Ljava/lang/String;IILjava/lang/String;)Ljava/lang/String;
    //   373: pop
    //   374: goto -> 339
    //   377: astore #8
    //   379: goto -> 1752
    //   382: new com/roadtrack/onstar/VO/UserDevicesVO
    //   385: astore #18
    //   387: aload #18
    //   389: invokespecial <init> : ()V
    //   392: aload #18
    //   394: aload #8
    //   396: invokevirtual setIdResponse : (Ljava/lang/String;)V
    //   399: aload #18
    //   401: aload #17
    //   403: iconst_1
    //   404: aaload
    //   405: invokevirtual setDeviceId : (Ljava/lang/String;)V
    //   408: aload #18
    //   410: aload #17
    //   412: iconst_2
    //   413: aaload
    //   414: invokevirtual setSerialnumber : (Ljava/lang/String;)V
    //   417: aload #18
    //   419: aload #17
    //   421: iconst_3
    //   422: aaload
    //   423: invokevirtual setCommserverid : (Ljava/lang/String;)V
    //   426: aload #18
    //   428: aload #17
    //   430: iconst_4
    //   431: aaload
    //   432: invokevirtual setName : (Ljava/lang/String;)V
    //   435: aload #18
    //   437: aload #17
    //   439: iconst_5
    //   440: aaload
    //   441: invokevirtual setPhone : (Ljava/lang/String;)V
    //   444: aload #18
    //   446: aload #17
    //   448: bipush #6
    //   450: aaload
    //   451: invokevirtual setDriverName : (Ljava/lang/String;)V
    //   454: aload #18
    //   456: aload #17
    //   458: bipush #7
    //   460: aaload
    //   461: invokevirtual setSpeedLimit : (Ljava/lang/String;)V
    //   464: aload #18
    //   466: aload #17
    //   468: bipush #8
    //   470: aaload
    //   471: invokevirtual setCurrentEnergyModeId : (Ljava/lang/String;)V
    //   474: aload #18
    //   476: aload #17
    //   478: bipush #9
    //   480: aaload
    //   481: invokevirtual setDistanceUnit : (Ljava/lang/String;)V
    //   484: aload #18
    //   486: aload #17
    //   488: bipush #10
    //   490: aaload
    //   491: invokevirtual setTheftAlertActive : (Ljava/lang/String;)V
    //   494: aload #18
    //   496: aload #17
    //   498: bipush #11
    //   500: aaload
    //   501: invokevirtual setSpeedAlertActive : (Ljava/lang/String;)V
    //   504: aload #18
    //   506: aload #17
    //   508: bipush #12
    //   510: aaload
    //   511: invokevirtual setPurchasingdealership : (Ljava/lang/String;)V
    //   514: aload #18
    //   516: aload #17
    //   518: bipush #13
    //   520: aaload
    //   521: invokevirtual setBranchdealership : (Ljava/lang/String;)V
    //   524: aload #18
    //   526: aload #17
    //   528: bipush #14
    //   530: aaload
    //   531: invokevirtual setCve_brand : (Ljava/lang/String;)V
    //   534: aload #17
    //   536: bipush #19
    //   538: aaload
    //   539: astore #19
    //   541: aload #18
    //   543: aload #19
    //   545: invokevirtual setStolenCode : (Ljava/lang/String;)V
    //   548: aload #17
    //   550: bipush #15
    //   552: aaload
    //   553: astore #8
    //   555: aload #17
    //   557: bipush #15
    //   559: aaload
    //   560: ldc_w '8I'
    //   563: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   566: ifeq -> 577
    //   569: ldc_w 'D-MAX'
    //   572: astore #8
    //   574: goto -> 709
    //   577: aload #17
    //   579: bipush #15
    //   581: aaload
    //   582: ldc_w 'AC'
    //   585: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   588: ifeq -> 599
    //   591: ldc_w 'SPARK-GT'
    //   594: astore #8
    //   596: goto -> 574
    //   599: aload #17
    //   601: bipush #15
    //   603: aaload
    //   604: ldc_w 'GK'
    //   607: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   610: ifeq -> 621
    //   613: ldc_w 'SAIL'
    //   616: astore #8
    //   618: goto -> 574
    //   621: aload #17
    //   623: bipush #15
    //   625: aaload
    //   626: ldc_w 'WW'
    //   629: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   632: ifeq -> 643
    //   635: ldc_w 'COBALT'
    //   638: astore #8
    //   640: goto -> 574
    //   643: aload #17
    //   645: bipush #15
    //   647: aaload
    //   648: ldc_w 'ZF'
    //   651: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   654: ifeq -> 665
    //   657: ldc_w 'SONIC'
    //   660: astore #8
    //   662: goto -> 574
    //   665: aload #17
    //   667: bipush #15
    //   669: aaload
    //   670: ldc_w '96'
    //   673: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   676: ifeq -> 687
    //   679: ldc_w 'TRAVERSE'
    //   682: astore #8
    //   684: goto -> 574
    //   687: aload #17
    //   689: bipush #15
    //   691: aaload
    //   692: ldc_w '5W'
    //   695: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   698: ifeq -> 574
    //   701: ldc_w 'TRACKER'
    //   704: astore #8
    //   706: goto -> 574
    //   709: aload #18
    //   711: aload #8
    //   713: invokevirtual setCve_model : (Ljava/lang/String;)V
    //   716: new com/roadtrack/onstar/utils/Utilities
    //   719: astore #8
    //   721: aload #8
    //   723: invokespecial <init> : ()V
    //   726: aload #9
    //   728: ldc ''
    //   730: invokevirtual equals : (Ljava/lang/Object;)Z
    //   733: ifne -> 757
    //   736: aload #18
    //   738: aload #8
    //   740: aload #9
    //   742: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   745: invokevirtual intValue : ()I
    //   748: invokevirtual defineDeviceTypeId : (I)Ljava/lang/String;
    //   751: invokevirtual setDeviceTypeId : (Ljava/lang/String;)V
    //   754: goto -> 765
    //   757: aload #18
    //   759: ldc_w '0'
    //   762: invokevirtual setDeviceTypeId : (Ljava/lang/String;)V
    //   765: aload #17
    //   767: bipush #16
    //   769: aaload
    //   770: ldc_w '*'
    //   773: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   776: ifeq -> 868
    //   779: getstatic com/roadtrack/onstar/BO/WsAccess.rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   782: iconst_1
    //   783: invokevirtual setWaterMarkActive : (Z)V
    //   786: aload_0
    //   787: aload #18
    //   789: aload #17
    //   791: bipush #16
    //   793: aaload
    //   794: invokevirtual fillMenuActions : (Lcom/roadtrack/onstar/VO/UserDevicesVO;Ljava/lang/String;)V
    //   797: aload #18
    //   799: invokevirtual getMainActions : ()Ljava/util/LinkedHashMap;
    //   802: invokevirtual keySet : ()Ljava/util/Set;
    //   805: invokeinterface size : ()I
    //   810: anewarray java/lang/String
    //   813: astore #14
    //   815: aload #18
    //   817: invokevirtual getMainActions : ()Ljava/util/LinkedHashMap;
    //   820: invokevirtual keySet : ()Ljava/util/Set;
    //   823: invokeinterface iterator : ()Ljava/util/Iterator;
    //   828: astore #20
    //   830: iconst_0
    //   831: istore #4
    //   833: aload #14
    //   835: astore #8
    //   837: aload #20
    //   839: invokeinterface hasNext : ()Z
    //   844: ifeq -> 973
    //   847: aload #14
    //   849: iload #4
    //   851: aload #20
    //   853: invokeinterface next : ()Ljava/lang/Object;
    //   858: checkcast java/lang/String
    //   861: aastore
    //   862: iinc #4, 1
    //   865: goto -> 833
    //   868: getstatic com/roadtrack/onstar/BO/WsAccess.rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   871: iconst_1
    //   872: invokevirtual setWaterMarkActive : (Z)V
    //   875: aload #17
    //   877: bipush #16
    //   879: aaload
    //   880: ldc_w ';'
    //   883: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   886: pop
    //   887: new java/util/LinkedHashMap
    //   890: astore #8
    //   892: aload #8
    //   894: invokespecial <init> : ()V
    //   897: aload #18
    //   899: aload #8
    //   901: invokevirtual setMainActions : (Ljava/util/LinkedHashMap;)V
    //   904: new java/util/LinkedHashMap
    //   907: astore #8
    //   909: aload #8
    //   911: invokespecial <init> : ()V
    //   914: aload #18
    //   916: aload #8
    //   918: invokevirtual setButtomActions : (Ljava/util/LinkedHashMap;)V
    //   921: new java/util/LinkedHashMap
    //   924: astore #8
    //   926: aload #8
    //   928: invokespecial <init> : ()V
    //   931: aload #18
    //   933: aload #8
    //   935: invokevirtual setNavigationActions : (Ljava/util/LinkedHashMap;)V
    //   938: new java/util/LinkedHashMap
    //   941: astore #8
    //   943: aload #8
    //   945: invokespecial <init> : ()V
    //   948: aload #18
    //   950: aload #8
    //   952: invokevirtual setLateralActions : (Ljava/util/LinkedHashMap;)V
    //   955: aload #18
    //   957: invokevirtual getMainActions : ()Ljava/util/LinkedHashMap;
    //   960: invokevirtual keySet : ()Ljava/util/Set;
    //   963: invokeinterface size : ()I
    //   968: anewarray java/lang/String
    //   971: astore #8
    //   973: new java/util/ArrayList
    //   976: astore #14
    //   978: aload #14
    //   980: invokespecial <init> : ()V
    //   983: aload #8
    //   985: arraylength
    //   986: istore #4
    //   988: iconst_0
    //   989: istore #5
    //   991: iload #5
    //   993: iload #4
    //   995: if_icmpge -> 1015
    //   998: aload #14
    //   1000: aload #8
    //   1002: iload #5
    //   1004: aaload
    //   1005: invokevirtual add : (Ljava/lang/Object;)Z
    //   1008: pop
    //   1009: iinc #5, 1
    //   1012: goto -> 991
    //   1015: aload #18
    //   1017: aload #14
    //   1019: invokevirtual setActions : (Ljava/util/ArrayList;)V
    //   1022: aload #15
    //   1024: aload #18
    //   1026: invokeinterface add : (Ljava/lang/Object;)Z
    //   1031: pop
    //   1032: new com/roadtrack/onstar/VO/VehicleCatalogVO
    //   1035: astore #20
    //   1037: aload #20
    //   1039: invokespecial <init> : ()V
    //   1042: aload #20
    //   1044: aload #18
    //   1046: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1049: invokevirtual setDeviceId : (Ljava/lang/String;)V
    //   1052: aload #20
    //   1054: aload #12
    //   1056: invokevirtual setUser : (Ljava/lang/String;)V
    //   1059: aload #11
    //   1061: ifnonnull -> 1081
    //   1064: iload_3
    //   1065: ifne -> 1078
    //   1068: ldc_w 'T'
    //   1071: astore #8
    //   1073: iconst_1
    //   1074: istore_2
    //   1075: goto -> 1105
    //   1078: goto -> 1100
    //   1081: aload #18
    //   1083: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1086: aload #11
    //   1088: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1091: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1094: ifeq -> 1100
    //   1097: goto -> 1068
    //   1100: ldc_w 'F'
    //   1103: astore #8
    //   1105: iload_3
    //   1106: aload_0
    //   1107: getfield vecDeviceId : Ljava/util/Vector;
    //   1110: invokevirtual size : ()I
    //   1113: iconst_1
    //   1114: isub
    //   1115: if_icmpne -> 1131
    //   1118: iload_2
    //   1119: ifne -> 1131
    //   1122: aload #8
    //   1124: ldc_w 'T'
    //   1127: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1130: pop
    //   1131: aload #20
    //   1133: aload #8
    //   1135: invokevirtual setSelected : (Ljava/lang/String;)V
    //   1138: ldc_w '1'
    //   1141: astore #8
    //   1143: aload #17
    //   1145: ifnull -> 1248
    //   1148: aload #17
    //   1150: arraylength
    //   1151: istore #4
    //   1153: iload #4
    //   1155: bipush #20
    //   1157: if_icmple -> 1248
    //   1160: aload #17
    //   1162: bipush #21
    //   1164: aaload
    //   1165: invokestatic parseFloat : (Ljava/lang/String;)F
    //   1168: fstore_1
    //   1169: fload_1
    //   1170: ldc_w 60.0
    //   1173: fdiv
    //   1174: fstore_1
    //   1175: aload #17
    //   1177: bipush #20
    //   1179: aaload
    //   1180: astore #8
    //   1182: goto -> 1203
    //   1185: astore #14
    //   1187: fconst_0
    //   1188: fstore_1
    //   1189: ldc_w 'WsAccess'
    //   1192: ldc_w 'GetUserDeviceShortG2_WS()'
    //   1195: aload #14
    //   1197: invokevirtual getMessage : ()Ljava/lang/String;
    //   1200: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1203: new java/lang/StringBuilder
    //   1206: astore #14
    //   1208: aload #14
    //   1210: invokespecial <init> : ()V
    //   1213: aload #14
    //   1215: ldc ''
    //   1217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1220: pop
    //   1221: aload #14
    //   1223: fload_1
    //   1224: invokevirtual append : (F)Ljava/lang/StringBuilder;
    //   1227: pop
    //   1228: aload #20
    //   1230: aload #14
    //   1232: invokevirtual toString : ()Ljava/lang/String;
    //   1235: invokevirtual setVehicle_GTM : (Ljava/lang/String;)V
    //   1238: aload #20
    //   1240: aload #8
    //   1242: invokevirtual setVehicle_IDGTM : (Ljava/lang/String;)V
    //   1245: goto -> 1248
    //   1248: aload #17
    //   1250: ifnull -> 1333
    //   1253: aload #17
    //   1255: arraylength
    //   1256: istore #4
    //   1258: iload #4
    //   1260: bipush #22
    //   1262: if_icmple -> 1333
    //   1265: aload #17
    //   1267: bipush #22
    //   1269: aaload
    //   1270: invokestatic parseInt : (Ljava/lang/String;)I
    //   1273: istore #4
    //   1275: goto -> 1297
    //   1278: astore #8
    //   1280: ldc_w 'WsAccess'
    //   1283: ldc_w 'GetUserDeviceShortG2_WS()'
    //   1286: aload #8
    //   1288: invokevirtual getMessage : ()Ljava/lang/String;
    //   1291: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1294: iconst_1
    //   1295: istore #4
    //   1297: new java/lang/StringBuilder
    //   1300: astore #8
    //   1302: aload #8
    //   1304: invokespecial <init> : ()V
    //   1307: aload #8
    //   1309: ldc ''
    //   1311: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1314: pop
    //   1315: aload #8
    //   1317: iload #4
    //   1319: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1322: pop
    //   1323: aload #20
    //   1325: aload #8
    //   1327: invokevirtual toString : ()Ljava/lang/String;
    //   1330: invokevirtual setDTC_Selector : (Ljava/lang/String;)V
    //   1333: ldc_w '1'
    //   1336: astore #14
    //   1338: aload #17
    //   1340: ifnull -> 1369
    //   1343: aload #17
    //   1345: arraylength
    //   1346: istore #4
    //   1348: iload #4
    //   1350: bipush #23
    //   1352: if_icmple -> 1369
    //   1355: aload #17
    //   1357: bipush #23
    //   1359: aaload
    //   1360: astore #8
    //   1362: iload #7
    //   1364: istore #6
    //   1366: goto -> 1390
    //   1369: aload #14
    //   1371: astore #8
    //   1373: iload #7
    //   1375: istore #6
    //   1377: getstatic com/roadtrack/onstar/BO/GlobalMembers.renewalfeatures : Z
    //   1380: ifeq -> 1390
    //   1383: iconst_0
    //   1384: istore #6
    //   1386: aload #14
    //   1388: astore #8
    //   1390: aload #20
    //   1392: aload #8
    //   1394: invokevirtual setStatus_renewal_account : (Ljava/lang/String;)V
    //   1397: aload #20
    //   1399: aload #17
    //   1401: iconst_4
    //   1402: aaload
    //   1403: invokevirtual setVehicleName : (Ljava/lang/String;)V
    //   1406: aload #16
    //   1408: aload #20
    //   1410: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1413: invokevirtual insertRenewalDialogStatus : (Ljava/lang/String;)J
    //   1416: pop2
    //   1417: aload #20
    //   1419: invokevirtual getStatus_renewal_account : ()Ljava/lang/String;
    //   1422: ifnull -> 1459
    //   1425: aload #20
    //   1427: invokevirtual getStatus_renewal_account : ()Ljava/lang/String;
    //   1430: getstatic com/roadtrack/onstar/Enums$statusRenewalAccount.Normal : Lcom/roadtrack/onstar/Enums$statusRenewalAccount;
    //   1433: invokevirtual toString : ()Ljava/lang/String;
    //   1436: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1439: ifeq -> 1459
    //   1442: aload #16
    //   1444: aload #20
    //   1446: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1449: getstatic com/roadtrack/onstar/Enums$statusRenewalDialog.ShowAlways : Lcom/roadtrack/onstar/Enums$statusRenewalDialog;
    //   1452: invokevirtual toString : ()Ljava/lang/String;
    //   1455: invokevirtual updateRenewalDialogStatus : (Ljava/lang/String;Ljava/lang/String;)J
    //   1458: pop2
    //   1459: aload #16
    //   1461: getstatic com/roadtrack/onstar/BO/WsAccess.rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1464: invokevirtual getAccountID : ()Ljava/lang/String;
    //   1467: invokevirtual toString : ()Ljava/lang/String;
    //   1470: aload #20
    //   1472: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1475: aload #19
    //   1477: iconst_0
    //   1478: invokevirtual userDataTableHandler : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Z
    //   1481: pop
    //   1482: aload #17
    //   1484: ifnull -> 1511
    //   1487: aload #17
    //   1489: arraylength
    //   1490: bipush #25
    //   1492: if_icmple -> 1511
    //   1495: aload #16
    //   1497: aload #20
    //   1499: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1502: aload #17
    //   1504: bipush #25
    //   1506: aaload
    //   1507: invokevirtual updateRenewalDialogDateExpire : (Ljava/lang/String;Ljava/lang/String;)J
    //   1510: pop2
    //   1511: aload #17
    //   1513: ifnull -> 1543
    //   1516: aload #17
    //   1518: arraylength
    //   1519: bipush #26
    //   1521: if_icmple -> 1543
    //   1524: aload #16
    //   1526: aload #20
    //   1528: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1531: aload #17
    //   1533: bipush #26
    //   1535: aaload
    //   1536: invokevirtual updateRenewalDialogDaysExpire : (Ljava/lang/String;Ljava/lang/String;)J
    //   1539: pop2
    //   1540: goto -> 1556
    //   1543: aload #16
    //   1545: aload #20
    //   1547: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1550: ldc ''
    //   1552: invokevirtual updateRenewalDialogDaysExpire : (Ljava/lang/String;Ljava/lang/String;)J
    //   1555: pop2
    //   1556: aload #17
    //   1558: ifnull -> 1589
    //   1561: aload #17
    //   1563: arraylength
    //   1564: bipush #27
    //   1566: if_icmple -> 1589
    //   1569: aload #18
    //   1571: aload #17
    //   1573: bipush #27
    //   1575: aaload
    //   1576: invokevirtual setSeguroApp : (Ljava/lang/String;)V
    //   1579: aload #20
    //   1581: aload #17
    //   1583: bipush #27
    //   1585: aaload
    //   1586: invokevirtual setPlan_app : (Ljava/lang/String;)V
    //   1589: aload #17
    //   1591: ifnull -> 1677
    //   1594: aload #17
    //   1596: arraylength
    //   1597: bipush #30
    //   1599: if_icmple -> 1677
    //   1602: aload #20
    //   1604: aload #17
    //   1606: bipush #28
    //   1608: aaload
    //   1609: invokevirtual setPlanStatusInform : (Ljava/lang/String;)V
    //   1612: aload #20
    //   1614: aload #17
    //   1616: bipush #29
    //   1618: aaload
    //   1619: invokevirtual setPayAttemptStatus : (Ljava/lang/String;)V
    //   1622: aload #17
    //   1624: bipush #30
    //   1626: aaload
    //   1627: ldc_w ','
    //   1630: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   1633: ifeq -> 1677
    //   1636: aload #17
    //   1638: bipush #30
    //   1640: aaload
    //   1641: ldc_w ','
    //   1644: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   1647: astore #8
    //   1649: aload #8
    //   1651: arraylength
    //   1652: iconst_1
    //   1653: if_icmple -> 1677
    //   1656: aload #20
    //   1658: aload #8
    //   1660: iconst_0
    //   1661: aaload
    //   1662: invokevirtual setAmountToPay : (Ljava/lang/String;)V
    //   1665: aload #20
    //   1667: aload #8
    //   1669: iconst_1
    //   1670: aaload
    //   1671: invokevirtual setMonetaryCurrency : (Ljava/lang/String;)V
    //   1674: goto -> 1677
    //   1677: aload #17
    //   1679: ifnull -> 1720
    //   1682: aload #17
    //   1684: arraylength
    //   1685: bipush #32
    //   1687: if_icmple -> 1720
    //   1690: aload #20
    //   1692: aload #17
    //   1694: bipush #31
    //   1696: aaload
    //   1697: invokevirtual setValidCard : (Ljava/lang/String;)V
    //   1700: aload #20
    //   1702: aload #17
    //   1704: bipush #32
    //   1706: aaload
    //   1707: invokevirtual setDateCardExpiration : (Ljava/lang/String;)V
    //   1710: aload #20
    //   1712: aload #17
    //   1714: bipush #33
    //   1716: aaload
    //   1717: invokevirtual setLastCardNumber : (Ljava/lang/String;)V
    //   1720: aload #16
    //   1722: aload #20
    //   1724: invokevirtual getDeviceId : ()Ljava/lang/String;
    //   1727: aload #20
    //   1729: invokevirtual updateAttemptToPay : (Ljava/lang/String;Lcom/roadtrack/onstar/VO/VehicleCatalogVO;)J
    //   1732: pop2
    //   1733: aload #16
    //   1735: aload #20
    //   1737: invokevirtual addVehiclesCatalog : (Lcom/roadtrack/onstar/VO/VehicleCatalogVO;)V
    //   1740: iinc #3, 1
    //   1743: aload #13
    //   1745: astore #8
    //   1747: goto -> 236
    //   1750: astore #8
    //   1752: ldc_w 'WsAccess'
    //   1755: ldc_w 'Error: GetUserDeviceShortG2_WS'
    //   1758: aload #8
    //   1760: invokevirtual getMessage : ()Ljava/lang/String;
    //   1763: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1766: aload_0
    //   1767: aload #10
    //   1769: iconst_3
    //   1770: iconst_0
    //   1771: ldc ''
    //   1773: invokevirtual errorsDescrip : (Ljava/lang/String;IILjava/lang/String;)Ljava/lang/String;
    //   1776: pop
    //   1777: aload #13
    //   1779: astore #8
    //   1781: goto -> 1784
    //   1784: new com/roadtrack/onstar/gson/GsonC
    //   1787: astore #10
    //   1789: aload #10
    //   1791: invokespecial <init> : ()V
    //   1794: invokestatic currentTimeMillis : ()J
    //   1797: pop2
    //   1798: aload #10
    //   1800: aload #15
    //   1802: invokevirtual toJsonString : (Ljava/util/List;)Ljava/lang/String;
    //   1805: astore #10
    //   1807: invokestatic currentTimeMillis : ()J
    //   1810: pop2
    //   1811: getstatic com/roadtrack/onstar/Enums$SettingsPreference.soapString : Lcom/roadtrack/onstar/Enums$SettingsPreference;
    //   1814: aload #10
    //   1816: getstatic com/roadtrack/onstar/BO/WsAccess.ctx : Landroid/content/Context;
    //   1819: invokestatic SetValuePreference : (Lcom/roadtrack/onstar/Enums$SettingsPreference;Ljava/lang/Object;Landroid/content/Context;)V
    //   1822: aload #8
    //   1824: ldc ''
    //   1826: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1829: ifne -> 1853
    //   1832: getstatic com/roadtrack/onstar/BO/WsAccess.rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1835: aload #8
    //   1837: invokevirtual setSessionKey : (Ljava/lang/String;)V
    //   1840: getstatic com/roadtrack/onstar/BO/WsAccess.rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1843: aload #15
    //   1845: invokevirtual setmDeviceUserList : (Ljava/util/List;)V
    //   1848: aload #15
    //   1850: putstatic com/roadtrack/onstar/BO/GlobalMembers.mDeviceUserList : Ljava/util/List;
    //   1853: iload #6
    //   1855: istore #7
    //   1857: aload #9
    //   1859: ldc ''
    //   1861: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1864: ifne -> 1906
    //   1867: getstatic com/roadtrack/onstar/BO/WsAccess.rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   1870: aload #9
    //   1872: invokevirtual setDeviceTypeId : (Ljava/lang/String;)V
    //   1875: iload #6
    //   1877: istore #7
    //   1879: goto -> 1906
    //   1882: astore #8
    //   1884: goto -> 1889
    //   1887: astore #8
    //   1889: ldc_w 'WsAccess'
    //   1892: ldc_w 'Error: GetUserDeviceShortG2_WS'
    //   1895: aload #8
    //   1897: invokevirtual getMessage : ()Ljava/lang/String;
    //   1900: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1903: iconst_0
    //   1904: istore #7
    //   1906: iload #7
    //   1908: ireturn
    // Exception table:
    //   from	to	target	type
    //   14	32	1887	java/lang/Exception
    //   32	49	1887	java/lang/Exception
    //   55	61	1887	java/lang/Exception
    //   68	104	1887	java/lang/Exception
    //   107	126	1887	java/lang/Exception
    //   129	139	1887	java/lang/Exception
    //   143	172	1887	java/lang/Exception
    //   178	221	1887	java/lang/Exception
    //   236	289	1887	java/lang/Exception
    //   302	310	1887	java/lang/Exception
    //   324	334	1887	java/lang/Exception
    //   342	349	1750	java/lang/Exception
    //   359	374	377	java/lang/Exception
    //   382	534	1887	java/lang/Exception
    //   541	548	1887	java/lang/Exception
    //   555	569	1887	java/lang/Exception
    //   577	591	1887	java/lang/Exception
    //   599	613	1887	java/lang/Exception
    //   621	635	1887	java/lang/Exception
    //   643	657	1887	java/lang/Exception
    //   665	679	1887	java/lang/Exception
    //   687	701	1887	java/lang/Exception
    //   709	754	1887	java/lang/Exception
    //   757	765	1887	java/lang/Exception
    //   765	830	1887	java/lang/Exception
    //   837	862	1887	java/lang/Exception
    //   868	973	1887	java/lang/Exception
    //   973	988	1887	java/lang/Exception
    //   998	1009	1887	java/lang/Exception
    //   1015	1059	1887	java/lang/Exception
    //   1081	1097	1887	java/lang/Exception
    //   1105	1118	1887	java/lang/Exception
    //   1122	1131	1887	java/lang/Exception
    //   1131	1138	1887	java/lang/Exception
    //   1148	1153	1887	java/lang/Exception
    //   1160	1169	1185	java/lang/Exception
    //   1189	1203	1887	java/lang/Exception
    //   1203	1245	1887	java/lang/Exception
    //   1253	1258	1887	java/lang/Exception
    //   1265	1275	1278	java/lang/Exception
    //   1280	1294	1887	java/lang/Exception
    //   1297	1333	1887	java/lang/Exception
    //   1343	1348	1887	java/lang/Exception
    //   1377	1383	1887	java/lang/Exception
    //   1390	1459	1887	java/lang/Exception
    //   1459	1482	1887	java/lang/Exception
    //   1487	1511	1887	java/lang/Exception
    //   1516	1540	1887	java/lang/Exception
    //   1543	1556	1887	java/lang/Exception
    //   1561	1589	1887	java/lang/Exception
    //   1594	1674	1887	java/lang/Exception
    //   1682	1720	1887	java/lang/Exception
    //   1720	1740	1887	java/lang/Exception
    //   1752	1766	1887	java/lang/Exception
    //   1766	1777	1882	java/lang/Exception
    //   1784	1853	1882	java/lang/Exception
    //   1857	1875	1882	java/lang/Exception
  }
  
  public String SendCommand(String paramString1, String paramString2, String paramString3, int paramInt) {
    GlobalMembers.lastSendCommandIdResponse = 0;
    boolean bool = NetUtilities.validateNetwork(rtApp.getApplicationContext(), false);
    String str2 = "-1";
    String str1 = str2;
    if (bool)
      try {
        this.wservice.set_methodName("SendCommand");
        this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
        this.wservice.set_url(URL_PRODUCTIVA);
        this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "SendCommand" }));
        String str5 = rtApp.getSessionKey();
        String str6 = rtApp.getUserAccessData()[0];
        String str7 = rtApp.getUserAccessData()[1];
        int i = Integer.parseInt(Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess").getDeviceId());
        str1 = rtApp.getLocatorUserId();
        String str4 = "0";
        if (str1 != null) {
          str1 = rtApp.getLocatorUserId();
        } else {
          str1 = "0";
        } 
        paramInt = Integer.parseInt(str1);
        Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
        this();
        hashtable.put("sessionKey", str5);
        hashtable.put("login", str6);
        hashtable.put("password", str7);
        hashtable.put("deviceId", Integer.valueOf(i));
        hashtable.put("actionCode", paramString1);
        hashtable.put("csvParams", paramString2);
        hashtable.put("userId", Integer.valueOf(paramInt));
        hashtable.put("applicationSourceId", Integer.valueOf(13));
        hashtable.put("deviceCSVParams", paramString3);
        paramString2 = (String)this.wservice.GetDataWSNetWcf(hashtable, ctx);
        paramString2 = CryptedSoapGenerator.getInstance().getDecryptedResult(paramString2);
        if (paramString2 == null || paramString2.length() == 0) {
          Utilities.escribeArchivo("ACTIONS", "ACTIONS: FINDME", "response: -1 null or empty");
          return "-1";
        } 
        String[] arrayOfString = paramString2.split("\\|");
        if (arrayOfString == null || arrayOfString.length < 3 || arrayOfString[0].isEmpty() || arrayOfString[2].isEmpty()) {
          Utilities.escribeArchivo("ACTIONS", "ACTIONS: FINDME", "response: -1 diiferent than expected");
          return "-1";
        } 
        paramInt = Integer.parseInt(arrayOfString[2]);
        Integer.parseInt(arrayOfString[1]);
        GlobalMembers.lastSendCommandIdResponse = paramInt;
        DBFunctions dBFunctions = new DBFunctions();
        this(ctx);
        if (dBFunctions.userDataTableHandler(rtApp.getAccountID().toString(), Utilities.getLastKnownDeviceSelected(ctx, rtApp).getDeviceId(), arrayOfString[2], false)) {
          arrayOfString[0] = "0";
          arrayOfString[1] = "0";
          arrayOfString[2] = "0";
          paramInt = 1;
        } 
        if (paramInt == 1) {
          String str9 = arrayOfString[0];
          if (!str9.equals(""))
            rtApp.setSessionKey(str9); 
          if (arrayOfString[1].equals("")) {
            str8 = str4;
          } else {
            str8 = str8[1];
          } 
          paramInt = Integer.parseInt(str8);
          GlobalMembers.lastSendCommandIdMessage = paramInt;
          if (paramInt == 0)
            return "-1"; 
          if (paramInt == 9)
            return "-1"; 
          String str8 = String.valueOf(paramInt);
          paramString1 = str8;
        } else {
          String str = str2;
          if (paramInt == 408) {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("");
            stringBuilder.append(paramInt);
            String str8 = stringBuilder.toString();
            paramString1 = str8;
          } else {
            return str;
          } 
        } 
        String str3 = paramString1;
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: sendCommand", exception.getMessage());
        paramString1.equals("1");
        str1 = str2;
      }  
    return str1;
  }
  
  public List<Object> activeNotificationEmail(String paramString1, String paramString2) {
    ArrayList<String> arrayList = new ArrayList();
    String str1 = rtApp.getUserAccessData()[0];
    String str3 = rtApp.getUserAccessData()[1];
    String str2 = rtApp.getAccountID();
    String str4 = rtApp.getLocatorUserId();
    try {
      this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "ActiveNotificationEmail" }));
      this.wservice.set_methodName("ActiveNotificationEmail");
      this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
      this.wservice.set_url(this.UrlUrlActivateNotificationEmail);
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      this();
      hashtable.put("sessionKey", rtApp.getSessionKey());
      hashtable.put("login", str1);
      hashtable.put("password", str3);
      hashtable.put("userId", str4);
      hashtable.put("accountId", str2);
      hashtable.put("devices", paramString2);
      hashtable.put("enabled", paramString1);
      hashtable.put("applicationSourceId", Integer.toString(13));
      String[] arrayOfString = this.wservice.GetDataWSNetActiveNotificationEmail(hashtable, ctx).toString().split("\\|");
      paramString2 = arrayOfString[0];
      int i = Integer.parseInt(arrayOfString[1]);
      arrayList.add(paramString2);
      arrayList.add(Integer.valueOf(i));
      return (List)arrayList;
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: activeNotificationeMail", exception.getMessage());
      return null;
    } 
  }
  
  public List<Object> activePushNotificationAppG2(String paramString) {
    ArrayList<String> arrayList = new ArrayList();
    String str5 = rtApp.getSessionKey();
    String str3 = rtApp.getUserAccessData()[0];
    String str1 = rtApp.getUserAccessData()[1];
    String str4 = rtApp.getAccountID();
    String str2 = rtApp.getLocatorUserId();
    try {
      this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "ActivePushNotificationAppG2" }));
      this.wservice.set_methodName("ActivePushNotificationAppG2");
      this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
      this.wservice.set_url(this.UrlUrlActivateNotificationEmail);
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      this();
      hashtable.put("sessionKey", str5);
      hashtable.put("login", str3);
      hashtable.put("password", str1);
      hashtable.put("accountId", str4);
      hashtable.put("userId", str2);
      hashtable.put("status", paramString);
      hashtable.put("applicationSourceId", Integer.toString(13));
      String[] arrayOfString = this.wservice.GetDataWsNetActivePushNotificationAppG2(hashtable, ctx).toString().split("\\|");
      int i = Integer.parseInt(arrayOfString[1]);
      Utilities.escribeArchivo("WsAccess", "activePushNotificationV2", arrayOfString.toString());
      arrayList.add(arrayOfString[0]);
      arrayList.add(Integer.valueOf(i));
      return (List)arrayList;
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: activePushNotificationV2", exception.getMessage());
      return null;
    } 
  }
  
  public String deleteDevices(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    try {
      this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "DeleteDeviceNameApp" }));
      this.wservice.set_methodName("DeleteDeviceNameApp");
      this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
      this.wservice.set_url(URL_ANDROID_AUTO);
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      this();
      linkedHashMap.put("accountId", paramString2);
      linkedHashMap.put("devicename", paramString3);
      linkedHashMap.put("applicationId", paramString4);
      linkedHashMap.put("cellphoneId", paramString5);
      return this.wservice.DeleteDataWSNetDevice(linkedHashMap, ctx);
    } catch (Exception exception) {
      Utilities.escribeArchivo(paramString1, "Error: ", exception.getMessage());
      return null;
    } 
  }
  
  public boolean deleteSyncDataWSWcf(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    boolean bool = false;
    try {
      ManagerConnectionWS managerConnectionWS = new ManagerConnectionWS();
      this();
      String str = GlobalMembers.URLSync;
      managerConnectionWS.set_namespace(GlobalMembers.NAMESPACE_WCF);
      managerConnectionWS.set_url(str);
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(GlobalMembers.NAMESPACE_WCF);
      stringBuilder.append("IFavorites/%s");
      managerConnectionWS.set_soapAction(String.format(stringBuilder.toString(), new Object[] { "deletefav_Ids" }));
      managerConnectionWS.set_methodName("deletefav_Ids");
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      this();
      hashtable.put("ids", paramString1);
      hashtable.put("user", paramString2);
      hashtable.put("device", paramString3);
      hashtable.put("commId", paramString4);
      hashtable.put("serialNumber", paramString5);
      ArrayList<Integer> arrayList = RetrieveDeleteFromWcfSyncDelete(managerConnectionWS.GetDataWSNetWcfSyncDelete(hashtable, ctx).toString());
      boolean bool1 = bool;
      if (arrayList != null) {
        bool1 = bool;
        if (arrayList.get(0) != null) {
          int i = ((Integer)arrayList.get(0)).intValue();
          bool1 = bool;
          if (i == 1)
            bool1 = true; 
        } 
      } 
      return bool1;
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: deleteSyncDataWS", exception.getMessage());
      return false;
    } 
  }
  
  public String errorsDescrip(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    String.format("Locator %1$s IdError %2$s  %3$s", new Object[] { paramString1, Integer.valueOf(paramInt1), "%1$s" });
    boolean bool = paramString1.equals("StartSession");
    String str = null;
    paramString2 = null;
    if (bool) {
      paramString2 = str;
      if (paramInt1 != 1)
        if (paramInt1 != 2) {
          if (paramInt1 != 3) {
            paramString2 = str;
          } else {
            paramString2 = this.ALRT_Error;
          } 
        } else {
          paramString2 = this.login_global_popup_lbl_sindatos_2;
        }  
    } else if (paramString1.equals("GetUserDeviceShort")) {
      paramString2 = str;
      if (paramInt1 != 1)
        if (paramInt1 != 2) {
          if (paramInt1 != 3) {
            paramString2 = str;
          } else {
            paramString2 = this.ALRT_Error;
          } 
        } else {
          paramString2 = this.login_global_popup_lbl_sindatos_2;
        }  
    } else if (paramString1.equals("SendCommand")) {
      paramString2 = str;
      switch (paramInt1) {
        default:
          paramString2 = str;
          break;
        case 9:
          paramString2 = this.ALRT_Error;
          break;
        case 8:
          paramString2 = this.ALRT_Error;
          break;
        case 7:
          paramString2 = this.ALRT_Error;
          break;
        case 6:
          paramString2 = this.ALRT_Error;
          break;
        case 5:
          paramString2 = this.ALRT_Error;
          break;
        case 4:
          paramString2 = this.ALRT_Error;
          break;
        case 3:
          paramString2 = this.ALRT_Error;
          break;
        case 2:
          paramString2 = this.ALRT_Error;
          break;
        case 1:
          break;
      } 
    } else if (paramString1.equals("GetCommandStatus")) {
      str = paramString2;
      if (paramInt1 != 0) {
        str = paramString2;
        if (paramInt1 != 1) {
          str = paramString2;
          if (paramInt1 != 2)
            if (paramInt1 != 3) {
              str = paramString2;
            } else {
              str = this.ALRT_Error;
            }  
        } 
      } 
      paramString2 = str;
      if (paramInt2 != 0)
        if (paramInt2 != 1) {
          if (paramInt2 != 2) {
            if (paramInt2 != 7) {
              if (paramInt2 != 8) {
                if (paramInt2 != 9) {
                  paramString2 = str;
                } else {
                  paramString2 = this.ALRT_Error;
                } 
              } else {
                paramString2 = this.ALRT_Error;
              } 
            } else {
              paramString2 = this.ALRT_Error;
            } 
          } else {
            paramString2 = this.ALRT_Error;
          } 
        } else {
          paramString2 = this.ALRT_Error;
        }  
    } else {
      paramString2 = str;
      if (paramString1.equals("GetUserPendingAlertShort")) {
        paramString2 = str;
        if (paramInt1 != 1)
          if (paramInt1 != 2) {
            if (paramInt1 != 3) {
              paramString2 = str;
            } else {
              paramString2 = this.ALRT_Error;
            } 
          } else {
            paramString2 = this.login_global_popup_lbl_sindatos_2;
          }  
      } 
    } 
    if (paramString2 != null) {
      if (paramString1.equals("GetCommandStatus") || paramString1.equals("SendCommand"))
        paramString2 = "-1"; 
    } else {
      paramString2 = "";
    } 
    return paramString2;
  }
  
  public void fillMenuActions(UserDevicesVO paramUserDevicesVO, String paramString) {
    // Byte code:
    //   0: ldc_w '-19'
    //   3: astore #11
    //   5: new java/util/LinkedHashMap
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: astore #15
    //   14: new java/util/LinkedHashMap
    //   17: dup
    //   18: invokespecial <init> : ()V
    //   21: astore #18
    //   23: new java/util/LinkedHashMap
    //   26: dup
    //   27: invokespecial <init> : ()V
    //   30: astore #19
    //   32: new java/util/LinkedHashMap
    //   35: dup
    //   36: invokespecial <init> : ()V
    //   39: astore #16
    //   41: new java/util/LinkedHashMap
    //   44: dup
    //   45: invokespecial <init> : ()V
    //   48: astore #14
    //   50: new java/util/LinkedList
    //   53: dup
    //   54: invokespecial <init> : ()V
    //   57: astore #17
    //   59: aload_2
    //   60: ldc_w '\*'
    //   63: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   66: astore #10
    //   68: ldc_w 'ERROR GETTING ACTIONS'
    //   71: astore #9
    //   73: ldc_w 'WsAccess'
    //   76: astore #12
    //   78: aload #10
    //   80: ifnull -> 771
    //   83: aload #10
    //   85: arraylength
    //   86: istore #4
    //   88: iconst_0
    //   89: istore #5
    //   91: iconst_0
    //   92: istore_3
    //   93: aload #12
    //   95: astore_2
    //   96: ldc_w 'hasmap'
    //   99: astore #12
    //   101: iload #5
    //   103: iload #4
    //   105: if_icmpge -> 471
    //   108: aload #10
    //   110: iload #5
    //   112: aaload
    //   113: ldc_w ';'
    //   116: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   119: astore #13
    //   121: aload #13
    //   123: arraylength
    //   124: istore #7
    //   126: iconst_0
    //   127: istore #6
    //   129: iload #6
    //   131: iload #7
    //   133: if_icmpge -> 448
    //   136: aload #13
    //   138: iload #6
    //   140: aaload
    //   141: ldc_w ','
    //   144: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   147: astore #21
    //   149: aload #21
    //   151: iconst_0
    //   152: aaload
    //   153: invokevirtual trim : ()Ljava/lang/String;
    //   156: getstatic com/roadtrack/onstar/Enums$Services.Navigation : Lcom/roadtrack/onstar/Enums$Services;
    //   159: invokevirtual GetCode : ()I
    //   162: invokestatic valueOf : (I)Ljava/lang/String;
    //   165: invokevirtual equals : (Ljava/lang/Object;)Z
    //   168: pop
    //   169: aload #21
    //   171: ifnull -> 442
    //   174: aload #21
    //   176: arraylength
    //   177: iconst_3
    //   178: if_icmplt -> 442
    //   181: new java/util/LinkedHashMap
    //   184: astore #20
    //   186: aload #20
    //   188: invokespecial <init> : ()V
    //   191: aload #20
    //   193: ldc_w 'water'
    //   196: aload #21
    //   198: iconst_1
    //   199: aaload
    //   200: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   203: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   206: pop
    //   207: aload #20
    //   209: aload #12
    //   211: aload #21
    //   213: iconst_2
    //   214: aaload
    //   215: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   218: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   221: pop
    //   222: iload_3
    //   223: ifeq -> 424
    //   226: iload_3
    //   227: iconst_1
    //   228: if_icmpeq -> 388
    //   231: iload_3
    //   232: iconst_2
    //   233: if_icmpeq -> 354
    //   236: iload_3
    //   237: iconst_3
    //   238: if_icmpeq -> 336
    //   241: iload_3
    //   242: iconst_4
    //   243: if_icmpeq -> 249
    //   246: goto -> 442
    //   249: aload #21
    //   251: iconst_0
    //   252: aaload
    //   253: ldc_w '#'
    //   256: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   259: ifeq -> 305
    //   262: aload #21
    //   264: iconst_0
    //   265: aaload
    //   266: ldc_w '#'
    //   269: ldc ''
    //   271: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   274: astore #21
    //   276: aload #20
    //   278: ldc_w 'submenu'
    //   281: iconst_1
    //   282: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   285: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   288: pop
    //   289: aload #14
    //   291: aload #21
    //   293: invokevirtual trim : ()Ljava/lang/String;
    //   296: aload #20
    //   298: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   301: pop
    //   302: goto -> 442
    //   305: aload #20
    //   307: ldc_w 'submenu'
    //   310: iconst_0
    //   311: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   314: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   317: pop
    //   318: aload #14
    //   320: aload #21
    //   322: iconst_0
    //   323: aaload
    //   324: invokevirtual trim : ()Ljava/lang/String;
    //   327: aload #20
    //   329: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   332: pop
    //   333: goto -> 442
    //   336: aload #16
    //   338: aload #21
    //   340: iconst_0
    //   341: aaload
    //   342: invokevirtual trim : ()Ljava/lang/String;
    //   345: aload #20
    //   347: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   350: pop
    //   351: goto -> 442
    //   354: aload #21
    //   356: iconst_0
    //   357: aaload
    //   358: invokevirtual trim : ()Ljava/lang/String;
    //   361: ldc_w '-1'
    //   364: invokevirtual equals : (Ljava/lang/Object;)Z
    //   367: ifne -> 442
    //   370: aload #19
    //   372: aload #21
    //   374: iconst_0
    //   375: aaload
    //   376: invokevirtual trim : ()Ljava/lang/String;
    //   379: aload #20
    //   381: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   384: pop
    //   385: goto -> 442
    //   388: getstatic com/roadtrack/onstar/BO/GlobalMembers.forceNavigationLeftDrawer : Z
    //   391: istore #8
    //   393: aload #18
    //   395: aload #21
    //   397: iconst_0
    //   398: aaload
    //   399: invokevirtual trim : ()Ljava/lang/String;
    //   402: aload #20
    //   404: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   407: pop
    //   408: aload #17
    //   410: aload #21
    //   412: iconst_0
    //   413: aaload
    //   414: invokevirtual trim : ()Ljava/lang/String;
    //   417: invokevirtual add : (Ljava/lang/Object;)Z
    //   420: pop
    //   421: goto -> 442
    //   424: aload #15
    //   426: aload #21
    //   428: iconst_0
    //   429: aaload
    //   430: invokevirtual trim : ()Ljava/lang/String;
    //   433: aload #20
    //   435: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   438: pop
    //   439: goto -> 442
    //   442: iinc #6, 1
    //   445: goto -> 129
    //   448: iinc #3, 1
    //   451: iinc #5, 1
    //   454: goto -> 96
    //   457: astore_1
    //   458: aload #9
    //   460: astore #10
    //   462: aload_2
    //   463: astore #9
    //   465: aload #10
    //   467: astore_2
    //   468: goto -> 798
    //   471: new java/util/LinkedHashMap
    //   474: astore #12
    //   476: aload #12
    //   478: invokespecial <init> : ()V
    //   481: new java/util/LinkedHashMap
    //   484: astore #10
    //   486: aload #10
    //   488: invokespecial <init> : ()V
    //   491: aload #12
    //   493: ldc_w 'water'
    //   496: iconst_1
    //   497: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   500: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   503: pop
    //   504: aload #12
    //   506: ldc_w 'hasmap'
    //   509: iconst_0
    //   510: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   513: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   516: pop
    //   517: aload #12
    //   519: ldc_w 'submenu'
    //   522: iconst_0
    //   523: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   526: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   529: pop
    //   530: aload #14
    //   532: aload #11
    //   534: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   537: ifnull -> 705
    //   540: aload #14
    //   542: aload #11
    //   544: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   547: checkcast java/util/LinkedHashMap
    //   550: ldc_w 'water'
    //   553: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   556: checkcast java/lang/Integer
    //   559: ldc_w '0'
    //   562: invokevirtual equals : (Ljava/lang/Object;)Z
    //   565: ifeq -> 624
    //   568: aload #10
    //   570: ldc_w 'water'
    //   573: iconst_0
    //   574: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   577: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   580: pop
    //   581: aload #10
    //   583: ldc_w 'hasmap'
    //   586: iconst_0
    //   587: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   590: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   593: pop
    //   594: aload #10
    //   596: ldc_w 'submenu'
    //   599: iconst_0
    //   600: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   603: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   606: pop
    //   607: aload #14
    //   609: getstatic com/roadtrack/onstar/Enums$Services.ClubOnStar : Lcom/roadtrack/onstar/Enums$Services;
    //   612: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   615: aload #10
    //   617: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   620: pop
    //   621: goto -> 705
    //   624: aload #14
    //   626: aload #11
    //   628: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   631: checkcast java/util/LinkedHashMap
    //   634: ldc_w 'water'
    //   637: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   640: checkcast java/lang/Integer
    //   643: ldc_w '1'
    //   646: invokevirtual equals : (Ljava/lang/Object;)Z
    //   649: ifeq -> 705
    //   652: aload #10
    //   654: ldc_w 'water'
    //   657: iconst_1
    //   658: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   661: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   664: pop
    //   665: aload #10
    //   667: ldc_w 'hasmap'
    //   670: iconst_0
    //   671: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   674: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   677: pop
    //   678: aload #10
    //   680: ldc_w 'submenu'
    //   683: iconst_0
    //   684: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   687: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   690: pop
    //   691: aload #14
    //   693: getstatic com/roadtrack/onstar/Enums$Services.ClubOnStar : Lcom/roadtrack/onstar/Enums$Services;
    //   696: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   699: aload #10
    //   701: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   704: pop
    //   705: aload #14
    //   707: getstatic com/roadtrack/onstar/Enums$Services.ActionExit : Lcom/roadtrack/onstar/Enums$Services;
    //   710: invokevirtual GetCodeString : ()Ljava/lang/String;
    //   713: aload #12
    //   715: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   718: pop
    //   719: aload_1
    //   720: aload #15
    //   722: invokevirtual setMainActions : (Ljava/util/LinkedHashMap;)V
    //   725: aload_1
    //   726: aload #18
    //   728: invokevirtual setButtomActions : (Ljava/util/LinkedHashMap;)V
    //   731: aload_1
    //   732: aload #19
    //   734: invokevirtual setNavigationActions : (Ljava/util/LinkedHashMap;)V
    //   737: aload_1
    //   738: aload #16
    //   740: invokevirtual setPidActions : (Ljava/util/LinkedHashMap;)V
    //   743: aload_1
    //   744: aload #17
    //   746: invokevirtual setRepeatedActions : (Ljava/util/LinkedList;)V
    //   749: aload_1
    //   750: aload #14
    //   752: invokevirtual setLateralActions : (Ljava/util/LinkedHashMap;)V
    //   755: goto -> 808
    //   758: astore_1
    //   759: ldc_w 'ERROR GETTING ACTIONS'
    //   762: astore_2
    //   763: ldc_w 'WsAccess'
    //   766: astore #9
    //   768: goto -> 798
    //   771: ldc_w 'ERROR GETTING ACTIONS'
    //   774: astore #10
    //   776: ldc_w 'WsAccess'
    //   779: astore #9
    //   781: ldc_w 'WsAccess'
    //   784: ldc_w 'ERROR GETTING ACTIONS'
    //   787: aload_2
    //   788: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   791: goto -> 808
    //   794: astore_1
    //   795: aload #10
    //   797: astore_2
    //   798: aload #9
    //   800: aload_2
    //   801: aload_1
    //   802: invokevirtual getMessage : ()Ljava/lang/String;
    //   805: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   808: return
    // Exception table:
    //   from	to	target	type
    //   83	88	758	java/lang/Exception
    //   108	126	457	java/lang/Exception
    //   136	169	457	java/lang/Exception
    //   174	222	457	java/lang/Exception
    //   249	302	457	java/lang/Exception
    //   305	333	457	java/lang/Exception
    //   336	351	457	java/lang/Exception
    //   354	385	457	java/lang/Exception
    //   388	421	457	java/lang/Exception
    //   424	439	457	java/lang/Exception
    //   471	621	457	java/lang/Exception
    //   624	705	457	java/lang/Exception
    //   705	755	457	java/lang/Exception
    //   781	791	794	java/lang/Exception
  }
  
  public String getAvailableTicket(String paramString) {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    if (userDevicesVO != null) {
      String str = userDevicesVO.getSerialnumber();
      try {
        this.wservice.set_url(GlobalMembers.URLMoipPrefix);
        this.wservice.set_namespace(GlobalMembers.MoipAvailableTicket);
        ArrayList<AvailableTickeRequest> arrayList = new ArrayList();
        this();
        AvailableTickeRequest availableTickeRequest = new AvailableTickeRequest();
        this();
        availableTickeRequest.setLpar1(Utilities.CryptMoip(str));
        availableTickeRequest.setLpar2(Utilities.CryptMoip("2"));
        availableTickeRequest.setLpar3(Utilities.CryptMoip(""));
        arrayList.add(availableTickeRequest);
        return this.wservice.GetDataWSAvailableTicket(paramString, arrayList, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getAvailableTicket", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getDeviceList(String paramString1, String paramString2) {
    try {
      this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "GetListDeviceAvaibleApp" }));
      this.wservice.set_methodName("GetListDeviceAvaibleApp");
      this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
      this.wservice.set_url(URL_ANDROID_AUTO);
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      this();
      linkedHashMap.put("accountId", paramString2);
      return this.wservice.GetDataWSNetDevice(linkedHashMap, ctx);
    } catch (Exception exception) {
      Utilities.escribeArchivo(paramString1, "Error: ", exception.getMessage());
      return null;
    } 
  }
  
  public String getDtcStatus(String paramString1, String paramString2) {
    String str2 = rtApp.getSessionKey();
    String str1 = rtApp.getUserAccessData()[0];
    String str3 = rtApp.getUserAccessData()[1];
    str3 = (new Utilities()).getCrypt(str3);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, paramString1);
    if (userDevicesVO != null) {
      if (paramString2 == null)
        paramString2 = userDevicesVO.getDeviceId(); 
      try {
        this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "GetDTCAlertStatusByDevice" }));
        this.wservice.set_methodName("GetDTCAlertStatusByDevice");
        this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
        this.wservice.set_url(URL_PRODUCTIVA);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("sessionKey", str2);
        linkedHashMap.put("login", str1);
        linkedHashMap.put("password", str3);
        linkedHashMap.put("deviceId", paramString2);
        linkedHashMap.put("applicationSourceId", Integer.toString(13));
        return this.wservice.GetDataWSNetGetDtcStatus(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo(paramString1, "Error: ", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getDtcs(String paramString1, String paramString2) {
    String str2 = rtApp.getSessionKey();
    String str1 = rtApp.getUserAccessData()[0];
    String str3 = rtApp.getUserAccessData()[1];
    str3 = (new Utilities()).getCrypt(str3);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, paramString1);
    if (userDevicesVO != null) {
      if (paramString2 == null)
        paramString2 = userDevicesVO.getDeviceId(); 
      try {
        this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "GetDTCInformationByDeviceId" }));
        this.wservice.set_methodName("GetDTCInformationByDeviceId");
        this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
        this.wservice.set_url(URL_PRODUCTIVA);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("sessionKey", str2);
        linkedHashMap.put("login", str1);
        linkedHashMap.put("password", str3);
        linkedHashMap.put("deviceId", paramString2);
        linkedHashMap.put("applicationSourceId", Integer.toString(13));
        return this.wservice.GetDataWSNetGetDtcs(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo(paramString1, "Error: ", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public List<Object> getEmailNotificationStatus() {
    Utilities utilities = new Utilities();
    ArrayList<String> arrayList = new ArrayList();
    String str2 = rtApp.getSessionKey();
    String str1 = rtApp.getUserAccessData()[0];
    String str5 = utilities.getCrypt(rtApp.getUserAccessData()[1]);
    String str3 = rtApp.getAccountID();
    String str4 = rtApp.getLocatorUserId();
    try {
      this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "GetActiveNotificationEmail" }));
      this.wservice.set_methodName("GetActiveNotificationEmail");
      this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
      this.wservice.set_url(GlobalMembers.URL_WCF);
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      this();
      hashtable.put("sessionKey", str2);
      hashtable.put("login", str1);
      hashtable.put("password", str5);
      hashtable.put("userId", str4);
      hashtable.put("accountId", str3);
      hashtable.put("applicationSourceId", Integer.toString(13));
      String[] arrayOfString = this.wservice.GetDataWSNetNotificationEmailStatus(hashtable, ctx).toString().split("\\|");
      str2 = arrayOfString[0];
      int i = Integer.parseInt(arrayOfString[1]);
      arrayList.add(str2);
      arrayList.add(Integer.valueOf(i));
      return (List)arrayList;
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: activeNotificationeMail", exception.getMessage());
      return null;
    } 
  }
  
  public String getGmt(String paramString1, String paramString2) {
    String str1 = rtApp.getSessionKey();
    String str2 = rtApp.getUserAccessData()[0];
    String str3 = rtApp.getUserAccessData()[1];
    str3 = (new Utilities()).getCrypt(str3);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, paramString1);
    if (userDevicesVO != null) {
      if (paramString2 == null)
        userDevicesVO.getDeviceId(); 
      try {
        this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "CatalogTimeZone" }));
        this.wservice.set_methodName("CatalogTimeZone");
        this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
        this.wservice.set_url(URL_PRODUCTIVA);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("sessionKey", str1);
        linkedHashMap.put("login", str2);
        linkedHashMap.put("password", str3);
        linkedHashMap.put("applicationSourceId", Integer.toString(13));
        return this.wservice.GetDataWSNetGetGmt(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo(paramString1, "Error: ", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getMyPlanInfo() {
    Utilities utilities = new Utilities();
    String str1 = rtApp.getSessionKey();
    String str2 = rtApp.getUserAccessData()[0];
    String str3 = utilities.getCrypt(rtApp.getUserAccessData()[1]);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    if (userDevicesVO != null) {
      String str = userDevicesVO.getDeviceId();
      try {
        this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "GetInfoMenuPlan" }));
        this.wservice.set_methodName("GetInfoMenuPlan");
        this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
        this.wservice.set_url(GlobalMembers.URL_WCF);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("sessionKey", str1);
        linkedHashMap.put("login", str2);
        linkedHashMap.put("password", str3);
        linkedHashMap.put("applicationSourceId", Integer.toString(13));
        linkedHashMap.put("deviceId", str);
        str2 = this.wservice.GetDataWSNetMyPlanInfo(linkedHashMap, ctx);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("DeviceId: ");
        stringBuilder.append(str);
        Utilities.escribeArchivo("WsAccess", "GetInfoMenuPlan", stringBuilder.toString());
        return str2;
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getMyPlanInfo", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getOnStartClubURL() {
    Utilities utilities = new Utilities();
    String str2 = rtApp.getSessionKey();
    String str1 = rtApp.getUserAccessData()[0];
    String str3 = utilities.getCrypt(rtApp.getUserAccessData()[1]);
    rtApp.getAccountID();
    rtApp.getLocatorUserId();
    try {
      this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "GetURLOnstarClub" }));
      this.wservice.set_methodName("GetURLOnstarClub");
      this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
      this.wservice.set_url(GlobalMembers.URL_WCF);
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      this();
      hashtable.put("sessionKey", str2);
      hashtable.put("login", str1);
      hashtable.put("password", str3);
      hashtable.put("applicationSourceId", Integer.toString(13));
      return this.wservice.GetOnStarClUBURL(hashtable, ctx).toString();
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: activeNotificationeMail", exception.getMessage());
      return null;
    } 
  }
  
  public String getOnstarClubAutenticate() {
    String str3 = rtApp.getSessionKey();
    String str1 = rtApp.getUserAccessData()[0];
    String str2 = rtApp.getAccountID();
    try {
      this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "GetOnstarClubAutenticate" }));
      this.wservice.set_methodName("GetOnstarClubAutenticate");
      this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
      this.wservice.set_url(GlobalMembers.URL_WCF);
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      this();
      linkedHashMap.put("sessionKey", str3);
      linkedHashMap.put("login", str1);
      linkedHashMap.put("applicationSourceId", Integer.toString(13));
      linkedHashMap.put("accountId", str2);
      return this.wservice.GetDataWSNetGetDtcs(linkedHashMap, ctx);
    } catch (Exception exception) {
      Utilities.escribeArchivo("ONSTARCLUB", "Error: ", exception.getMessage());
      return null;
    } 
  }
  
  public String getPaymentCardInfo(String paramString, LinkedHashMap<String, Object> paramLinkedHashMap) {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    if (userDevicesVO != null) {
      String str = userDevicesVO.getSerialnumber();
      try {
        this.wservice.set_namespace(GlobalMembers.MoipCardsManagementSuffix);
        this.wservice.set_url(GlobalMembers.URLMoipPrefix);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("Authorization", paramString);
        linkedHashMap.put("gcmupar1", Utilities.EncryptMoipData(str));
        return this.wservice.GetDataWSPaymentCardInfo(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getPaymentTransaction", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getPaymentHistoricalTask(String paramString, LinkedHashMap<String, Object> paramLinkedHashMap) {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    String str1 = rtApp.getUserAccessData()[1];
    String str2 = rtApp.getUserAccessData()[0];
    if (userDevicesVO != null) {
      String str = userDevicesVO.getSerialnumber();
      try {
        this.wservice.set_namespace(GlobalMembers.MoipCardsManagementSuffix);
        this.wservice.set_url(GlobalMembers.URL_Historical);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("Authorization", paramString);
        linkedHashMap.put("par1", Utilities.EncryptMoipData(str2));
        linkedHashMap.put("par2", Utilities.EncryptMoipData(str1));
        linkedHashMap.put("par3", Utilities.EncryptMoipData(paramString));
        linkedHashMap.put("par4", Utilities.EncryptMoipData(String.valueOf(13)));
        linkedHashMap.put("par5", Utilities.EncryptMoipData(str));
        return this.wservice.GetDataWSPaymentHistorical(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getPaymentHistoricalTask", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getPaymentHistory(String paramString1, String paramString2) {
    new ArrayList();
    String str = rtApp.getUserAccessData()[0];
    str = rtApp.getUserAccessData()[1];
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    if (userDevicesVO != null) {
      String str1 = userDevicesVO.getSerialnumber();
      try {
        this.wservice.set_namespace(GlobalMembers.MoipHistorySuffix);
        this.wservice.set_url(GlobalMembers.URLMoipPrefix);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("deviceId: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo("WsAccess", "getPaymentHistory", stringBuilder.toString());
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("Authorization", paramString1);
        linkedHashMap.put("chppar1", Utilities.EncryptMoipData(str1));
        linkedHashMap.put("chppar2", Utilities.EncryptMoipData(paramString2));
        return this.wservice.GetDataWSPaymentHistory(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getPaymentHistory", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getPaymentProcessTask(String paramString, LinkedHashMap<String, Object> paramLinkedHashMap) {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    String str2 = rtApp.getUserAccessData()[1];
    String str1 = rtApp.getUserAccessData()[0];
    if (userDevicesVO != null) {
      String str = userDevicesVO.getSerialnumber();
      try {
        this.wservice.set_namespace(GlobalMembers.MoipCardsManagementSuffix);
        this.wservice.set_url(GlobalMembers.URL_Renewal);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("Authorization", paramString);
        linkedHashMap.put("par1", Utilities.EncryptMoipData(str1));
        linkedHashMap.put("par2", Utilities.EncryptMoipData(str2));
        linkedHashMap.put("par3", Utilities.EncryptMoipData(paramString));
        linkedHashMap.put("par4", Utilities.EncryptMoipData(String.valueOf(13)));
        linkedHashMap.put("par5", Utilities.EncryptMoipData(str));
        return this.wservice.GetDataWSPaymentProcess(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getPaymentProcessTask", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getPaymentTransactionSubscription(String paramString, LinkedHashMap<String, Object> paramLinkedHashMap) {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    if (userDevicesVO != null) {
      String str = userDevicesVO.getSerialnumber();
      try {
        this.wservice.set_url(GlobalMembers.URLMoipPrefix);
        this.wservice.set_namespace(GlobalMembers.MoipSuscriptionSuffix);
        ArrayList<renewalTransactionSubscription> arrayList = new ArrayList();
        this();
        renewalTransactionSubscription renewalTransactionSubscription = new renewalTransactionSubscription();
        this(Utilities.CryptMoip(str), Utilities.CryptMoip(paramLinkedHashMap.get("currentPlan").toString()), Utilities.CryptMoip(paramLinkedHashMap.get("newPlan").toString()), Utilities.CryptMoip(paramLinkedHashMap.get("service").toString()), Utilities.CryptMoip("2"), Utilities.CryptMoip(paramLinkedHashMap.get("salesPolitic").toString()), Utilities.CryptMoip(paramLinkedHashMap.get("TypeToRenewal").toString()), Utilities.CryptMoip("0"));
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("requestItem:");
        stringBuilder.append(renewalTransactionSubscription.getSuspar2());
        stringBuilder.toString();
        arrayList.add(renewalTransactionSubscription);
        stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("requestItem_2:");
        stringBuilder.append(((renewalTransactionSubscription)arrayList.get(0)).getSuspar2());
        stringBuilder.toString();
        ((renewalTransactionSubscription)arrayList.get(0)).getSuspar1();
        ((renewalTransactionSubscription)arrayList.get(0)).getSuspar2();
        ((renewalTransactionSubscription)arrayList.get(0)).getSuspar3();
        ((renewalTransactionSubscription)arrayList.get(0)).getSuspar4();
        ((renewalTransactionSubscription)arrayList.get(0)).getSuspar5();
        ((renewalTransactionSubscription)arrayList.get(0)).getSuspar6();
        ((renewalTransactionSubscription)arrayList.get(0)).getSuspar7();
        ((renewalTransactionSubscription)arrayList.get(0)).getSuspar8();
        return this.wservice.GetDataWSRenewalTransaction(paramString, arrayList, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getPaymentTransaction", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getRegistrationInfoToken() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(rtApp.getUserAccessData()[0]);
    stringBuilder.append("[]");
    stringBuilder.append(rtApp.getUserAccessData()[1]);
    stringBuilder.append(GlobalMembers.stringToAccess);
    String str = replaceCharacter(Utilities.Crypt(stringBuilder.toString()));
    try {
      this.wservice.set_url(GlobalMembers.URL_DTC_LocatorWebApiAgendamiento);
      LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
      this();
      linkedHashMap.put("grant_type", "password");
      linkedHashMap.put("clientId", str);
      return this.wservice.GetRegistrationInfoToken(linkedHashMap, ctx);
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: getPaymentTransaction", exception.getMessage());
      return null;
    } 
  }
  
  public String getRenewalPlans(String paramString) {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    if (userDevicesVO != null) {
      String str = userDevicesVO.getSerialnumber();
      try {
        this.wservice.set_namespace(GlobalMembers.MoipGetPlansSuffix);
        this.wservice.set_url(GlobalMembers.URLMoipPrefix);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("Authorization", paramString);
        linkedHashMap.put("cppar1", Utilities.EncryptMoipData(str));
        String str1 = this.wservice.GetDataWSRenewalPlans(linkedHashMap, ctx);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("DeviceId: ");
        stringBuilder.append(str);
        Utilities.escribeArchivo("WsAccess", "getRenewalPlans", stringBuilder.toString());
        return str1;
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getRenewalPlans", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public TokenResponseO getRenewalToken() {
    TokenResponseO tokenResponseO = new TokenResponseO();
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(rtApp.getUserAccessData()[0]);
    stringBuilder1.append(".");
    stringBuilder1.append(GlobalMembers.stringToAccessUser);
    String str1 = stringBuilder1.toString();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(rtApp.getUserAccessData()[1]);
    stringBuilder2.append(GlobalMembers.stringToAccess);
    String str2 = stringBuilder2.toString();
    try {
      this.wservice.set_namespace(GlobalMembers.MoipSecuritySuffix);
      this.wservice.set_url(GlobalMembers.URLMoipPrefix);
      ArrayList<TokenRequestO> arrayList = new ArrayList();
      this();
      TokenRequestO tokenRequestO = new TokenRequestO();
      this(str1, str2);
      StringBuilder stringBuilder4 = new StringBuilder();
      this();
      stringBuilder4.append("url:");
      stringBuilder4.append(GlobalMembers.URLMoipPrefix);
      stringBuilder4.toString();
      stringBuilder4 = new StringBuilder();
      this();
      stringBuilder4.append("compar:");
      stringBuilder4.append(tokenRequestO.getLpar1());
      stringBuilder4.append(" > ");
      stringBuilder4.append(Utilities.EncryptMoipData(str1));
      stringBuilder4.toString();
      StringBuilder stringBuilder3 = new StringBuilder();
      this();
      stringBuilder3.append("compar:");
      stringBuilder3.append(tokenRequestO.getLpar2());
      stringBuilder3.append(" > ");
      stringBuilder3.append(Utilities.EncryptMoipData(str2));
      stringBuilder3.toString();
      arrayList.add(tokenRequestO);
      String str = this.wservice.GetDataWSRenewalToken(arrayList, ctx);
      if (str != null && !str.isEmpty()) {
        str = str.replace("\\\"", "\"");
        String str3 = str.substring(1, str.length() - 1);
        GsonC gsonC = new GsonC();
        this();
        gsonC.toListObject(str3, tokenResponseO);
        validateResponseToken(tokenResponseO);
      } 
      return tokenResponseO;
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: getRenewalToken", exception.getMessage());
      return null;
    } 
  }
  
  public String getURLChangeCreditCard(String paramString1, String paramString2) {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    DBFunctions dBFunctions = new DBFunctions(ctx);
    GlobalMembers.ctxBase = rtApp.getBaseContext();
    UserPreferenceVO userPreferenceVO = dBFunctions.getUserPreference(GlobalMembers.userLogged);
    if (userDevicesVO != null) {
      String str = userDevicesVO.getDeviceId();
      try {
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("deviceId: ");
        stringBuilder1.append(str);
        Utilities.escribeArchivo("WsAccess", "getURLCreditCard", stringBuilder1.toString());
        String str4 = Utilities.EncryptMoipData("");
        String str1 = Utilities.EncryptMoipData("3");
        String str2 = Utilities.EncryptMoipData(userPreferenceVO.getUser());
        String str3 = Utilities.EncryptMoipData(str);
        str = URLEncoder.encode(str4, "UTF-8");
        str1 = URLEncoder.encode(str1, "UTF-8");
        str2 = URLEncoder.encode(str2, "UTF-8");
        str3 = URLEncoder.encode(str3, "UTF-8");
        ArrayList<String> arrayList = new ArrayList();
        this();
        StringBuilder stringBuilder3 = new StringBuilder();
        this();
        stringBuilder3.append(GlobalMembers.URLMoipPrefix);
        stringBuilder3.append(GlobalMembers.MoipCreditCard);
        arrayList.add(stringBuilder3.toString());
        arrayList.add(str);
        arrayList.add(str1);
        arrayList.add(str2);
        arrayList.add(str3);
        StringBuilder stringBuilder2 = new StringBuilder();
        this();
        stringBuilder2.append(GlobalMembers.URLMoipPrefix);
        stringBuilder2.append(GlobalMembers.MoipCreditCard);
        str = String.format(stringBuilder2.toString(), new Object[] { str, str1, str2, str3 });
        Utilities.escribeArchivo("PMM AttemptToPay", "fullUrl", str);
        return str;
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getURLCreditCard", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String getURLPaymentHistory(String paramString1, String paramString2) {
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    paramString2 = rtApp.getUserAccessData()[0];
    if (userDevicesVO != null) {
      String str = userDevicesVO.getSerialnumber();
      try {
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("deviceId: ");
        stringBuilder1.append(str);
        Utilities.escribeArchivo("WsAccess", "getURLPaymentHistory", stringBuilder1.toString());
        String str3 = Utilities.EncryptData(String.valueOf(13));
        String str1 = Utilities.EncryptData(paramString2);
        String str2 = Utilities.EncryptData(paramString2);
        str = Utilities.EncryptData(str);
        paramString2 = URLEncoder.encode(str3, "UTF-8");
        str1 = URLEncoder.encode(str1, "UTF-8");
        str2 = URLEncoder.encode(str2, "UTF-8");
        str = URLEncoder.encode(str, "UTF-8");
        paramString1 = URLEncoder.encode(paramString1, "UTF-8");
        StringBuilder stringBuilder2 = new StringBuilder();
        this();
        stringBuilder2.append(GlobalMembers.WebviewHistoryPrefix);
        stringBuilder2.append(GlobalMembers.WebviewHistorySuffix);
        return String.format(stringBuilder2.toString(), new Object[] { paramString2, str1, str2, str, paramString1 });
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getURLPaymentHistory", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String saveStatisticsNavigationIn(String paramString) {
    Utilities utilities = new Utilities();
    new ArrayList();
    String str2 = rtApp.getSessionKey();
    String str1 = rtApp.getUserAccessData()[0];
    String str3 = utilities.getCrypt(rtApp.getUserAccessData()[1]);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, "WsAccess");
    if (userDevicesVO != null) {
      String str = userDevicesVO.getDeviceId();
      try {
        this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "SaveStadisticsNavigation" }));
        this.wservice.set_methodName("SaveStadisticsNavigation");
        this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
        this.wservice.set_url(GlobalMembers.URL_WCF);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("sessionKey", str2);
        linkedHashMap.put("login", str1);
        linkedHashMap.put("password", str3);
        linkedHashMap.put("deviceId", str);
        linkedHashMap.put("applicationSourceId", Integer.toString(13));
        linkedHashMap.put("stadistics", paramString);
        return this.wservice.GetDataWSNetSaveStatisticsNavigationIn(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: getMyPlanInfo", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public String sendTermsAndConditionsWs(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(GlobalMembers.NAMESPACE_WCF);
    stringBuilder.append("IService1/");
    stringBuilder.append("SaveDateTermsAndConditions");
    String str1 = stringBuilder.toString();
    String str2 = rtApp.getSessionKey();
    String str3 = rtApp.getLocatorUserId();
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    linkedHashMap.clear();
    linkedHashMap.put("sessionKey", str2);
    linkedHashMap.put("userId", str3);
    linkedHashMap.put("created", paramString);
    linkedHashMap.put("applicationSourceId", Integer.toString(13));
    linkedHashMap.put("cellphoneId", Utilities.DeviceUuidFactory(GlobalMembers.contexGlobal));
    try {
      SoapRequestObject soapRequestObject = new SoapRequestObject();
      this((LinkedHashMap)linkedHashMap, str1, "SaveDateTermsAndConditions", GlobalMembers.NAMESPACE_WCF, GlobalMembers.URL_WCF, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND, GlobalMembers.HTTP_TRANSPORT_TIMEOUT_ACTIONS_GETCOMMAND);
      soapRequestObject.setKeyStoreId(2131623963, GlobalMembers.nameKeystoreServiceWS);
      RequestManager requestManager = new RequestManager();
      this(ctx, soapRequestObject);
      RequestManager.OnPostExecuteListener onPostExecuteListener = new RequestManager.OnPostExecuteListener() {
          final WsAccess this$0;
          
          public void onPostExecuteListener(String param1String, int param1Int) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("response: ");
            stringBuilder.append(param1String);
            String str = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append("responseCode: ");
            stringBuilder.append(param1Int);
            Utilities.escribeArchivo("WsAccess", str, stringBuilder.toString());
            WsAccess.access$002(WsAccess.this, param1String);
          }
        };
      super(this);
      requestManager.setOnPostExecuteListener(onPostExecuteListener);
      requestManager.sendRequest(1);
      String str = this.aux_result;
    } catch (Exception exception) {
      exception.printStackTrace();
      exception = null;
    } 
    return (String)exception;
  }
  
  public String setGmt(String paramString1, String paramString2, String paramString3) {
    String str1 = rtApp.getSessionKey();
    String str2 = rtApp.getUserAccessData()[0];
    String str3 = rtApp.getUserAccessData()[1];
    str3 = (new Utilities()).getCrypt(str3);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, paramString1);
    if (userDevicesVO != null) {
      if (paramString2 == null)
        paramString2 = userDevicesVO.getDeviceId(); 
      try {
        this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "UpdateTimeZoneDevice" }));
        this.wservice.set_methodName("UpdateTimeZoneDevice");
        this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
        this.wservice.set_url(URL_PRODUCTIVA);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("sessionKey", str1);
        linkedHashMap.put("login", str2);
        linkedHashMap.put("password", str3);
        linkedHashMap.put("deviceId", paramString2);
        linkedHashMap.put("timezoneId", paramString3);
        linkedHashMap.put("applicationSourceId", Integer.toString(13));
        return this.wservice.GetDataWSNetSetGmt(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo(paramString1, "Error: ", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public ArrayList<SyncInsertResponse> syncInsertWcf(ArrayList<SyncVO> paramArrayList, String paramString1, String paramString2, String paramString3) {
    String str = "insertfav";
    if (paramArrayList != null)
      try {
        if (paramArrayList.size() > 0) {
          ArrayList<SyncInsertResponse> arrayList = new ArrayList();
          this();
          Iterator<SyncVO> iterator = paramArrayList.iterator();
          String str1 = str;
          while (iterator.hasNext()) {
            SyncVO syncVO = iterator.next();
            String[] arrayOfString = syncVO.getLatlng().split(",");
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            this(Locale.getDefault());
            decimalFormatSymbols.setDecimalSeparator('.');
            DecimalFormat decimalFormat2 = new DecimalFormat();
            this("####.###############", decimalFormatSymbols);
            DecimalFormat decimalFormat1 = new DecimalFormat();
            this("####.###############", decimalFormatSymbols);
            double d2 = Double.parseDouble(arrayOfString[0]);
            double d1 = Double.parseDouble(arrayOfString[1]);
            arrayOfString[0] = decimalFormat2.format(Double.valueOf(d2));
            arrayOfString[1] = decimalFormat1.format(Double.valueOf(d1));
            String str4 = syncVO.getUser();
            String str5 = syncVO.getDeviceId();
            String str2 = syncVO.getName();
            String str6 = syncVO.getAddress();
            StringBuilder stringBuilder1 = new StringBuilder();
            this();
            stringBuilder1.append(arrayOfString[0]);
            stringBuilder1.append(",");
            stringBuilder1.append(arrayOfString[1]);
            String str7 = stringBuilder1.toString();
            String str3 = syncVO.getId_favs_history();
            ManagerConnectionWS managerConnectionWS = new ManagerConnectionWS();
            this();
            String str8 = GlobalMembers.URLSync;
            managerConnectionWS.set_namespace(GlobalMembers.NAMESPACE_WCF);
            managerConnectionWS.set_url(str8);
            StringBuilder stringBuilder2 = new StringBuilder();
            this();
            stringBuilder2.append(GlobalMembers.NAMESPACE_WCF);
            stringBuilder2.append("IFavorites/%s");
            managerConnectionWS.set_soapAction(String.format(stringBuilder2.toString(), new Object[] { str1 }));
            managerConnectionWS.set_methodName(str1);
            if (str2 != null) {
              Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
              this();
              hashtable.put("user", str4);
              hashtable.put("phone", paramString3);
              hashtable.put("device", str5);
              hashtable.put("name", str2);
              hashtable.put("latlng", str7);
              hashtable.put("commId", paramString1);
              hashtable.put("serialNumber", paramString2);
              hashtable.put("id_favs", Integer.valueOf(Integer.parseInt(str3)));
              hashtable.put("address_syc", str6);
              Object object = managerConnectionWS.GetDataWSNetWcfSyncInsert(hashtable, ctx);
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("INSERTANDO: ");
              stringBuilder.append(str3);
              Utilities.escribeArchivo("WsAccess", "FAVORITES", stringBuilder.toString());
              if (object.equals("ERR_NAME_NOT_FOUND|")) {
                arrayList.addAll(RetrieveSyncInsertFromWcf("0"));
              } else {
                arrayList.addAll(RetrieveSyncInsertFromWcf(object.toString()));
              } 
              if (object != null && ((String)object).contains("OK")) {
                DBFunctions dBFunctions = new DBFunctions();
                this(ctx);
                dBFunctions.updateFavoriteHistory(Integer.valueOf(syncVO.getId_favs_history()).intValue(), str2, "");
              } 
            } 
          } 
          return arrayList;
        } 
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: INSERT FAVS", exception.getMessage());
        return null;
      }  
    return null;
  }
  
  public ArrayList<SyncVO> syncSelectWcf(String paramString1, String paramString2, String paramString3) {
    try {
      ArrayList<SyncVO> arrayList = new ArrayList();
      this();
      ManagerConnectionWS managerConnectionWS = new ManagerConnectionWS();
      this();
      String str = GlobalMembers.URLSync;
      managerConnectionWS.set_namespace(GlobalMembers.NAMESPACE_WCF);
      managerConnectionWS.set_url(str);
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(GlobalMembers.NAMESPACE_WCF);
      stringBuilder.append("IFavorites/%s");
      managerConnectionWS.set_soapAction(String.format(stringBuilder.toString(), new Object[] { "favlist" }));
      managerConnectionWS.set_methodName("favlist");
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      this();
      hashtable.put("user_Sync", paramString1);
      hashtable.put("deviceId", paramString2);
      Object object = managerConnectionWS.GetDataWSNetWcfSyncSelect(hashtable, ctx);
      if (object != null && !object.equals("OK"))
        arrayList.addAll(RetrieveFromWcfSyncSelect(object.toString(), paramString1, paramString2, paramString3)); 
      return arrayList;
    } catch (Exception exception) {
      Utilities.escribeArchivo("WsAccess", "Error: syncSelectWCF", exception.getMessage());
      return null;
    } 
  }
  
  public String updateDtcStatus(String paramString1, String paramString2, String paramString3) {
    String str4 = rtApp.getSessionKey();
    String str1 = rtApp.getUserAccessData()[0];
    String str2 = rtApp.getUserAccessData()[1];
    String str3 = rtApp.getAccountID();
    (new Utilities()).getCrypt(str2);
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(rtApp, paramString1);
    if (userDevicesVO != null) {
      if (paramString2 == null)
        paramString2 = userDevicesVO.getDeviceId(); 
      try {
        this.wservice.set_soapAction(String.format(SOAP_ACTION_PRODUCTIVO, new Object[] { "UpdateStatusDTCAlertNotification" }));
        this.wservice.set_methodName("UpdateStatusDTCAlertNotification");
        this.wservice.set_namespace(NAMESPACE_PRODUCTIVO);
        this.wservice.set_url(URL_PRODUCTIVA);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        this();
        linkedHashMap.put("sessionKey", str4);
        linkedHashMap.put("login", str1);
        linkedHashMap.put("password", str2);
        linkedHashMap.put("deviceId", paramString2);
        linkedHashMap.put("accountId", str3);
        linkedHashMap.put("statusAlert", paramString3);
        linkedHashMap.put("applicationSourceId", Integer.toString(13));
        return this.wservice.UpdateDataWSNetGetDtcStatus(linkedHashMap, ctx);
      } catch (Exception exception) {
        Utilities.escribeArchivo(paramString1, "Error: ", exception.getMessage());
      } 
    } 
    return null;
  }
  
  public ArrayList<SyncInsertResponse> updateSyncDataWSWcf(String paramString1, String paramString2, String paramString3, SyncVO paramSyncVO, String paramString4, String paramString5, String paramString6) {
    String str = null;
    paramString2 = str;
    if (paramSyncVO != null)
      try {
        ArrayList<SyncInsertResponse> arrayList = new ArrayList();
        this();
        String str4 = paramSyncVO.getUser();
        paramSyncVO.getUser();
        String str1 = paramSyncVO.getDeviceId();
        String str2 = paramSyncVO.getAddress();
        String str3 = paramSyncVO.getLatlng();
        String str5 = paramSyncVO.getId_favs_history();
        ManagerConnectionWS managerConnectionWS = new ManagerConnectionWS();
        this();
        String str6 = GlobalMembers.URLSync;
        managerConnectionWS.set_namespace(GlobalMembers.NAMESPACE_WCF);
        managerConnectionWS.set_url(str6);
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(GlobalMembers.NAMESPACE_WCF);
        stringBuilder.append("IFavorites/%s");
        managerConnectionWS.set_soapAction(String.format(stringBuilder.toString(), new Object[] { "updatefav" }));
        managerConnectionWS.set_methodName("updatefav");
        Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
        this();
        hashtable.put("id", paramString1);
        hashtable.put("user", str4);
        hashtable.put("phone", paramString6);
        hashtable.put("device", str1);
        hashtable.put("name", paramString3);
        hashtable.put("latlng", str3);
        hashtable.put("id_favs", str5);
        hashtable.put("commId", paramString4);
        hashtable.put("serialNumber", paramString5);
        hashtable.put("address_syc", str2);
        arrayList.addAll(RetrieveSyncInsertFromWcf(managerConnectionWS.GetDataWSNetWcfSyncUpdate(hashtable, ctx).toString()));
      } catch (Exception exception) {
        Utilities.escribeArchivo("WsAccess", "Error: updatesyncWCF", exception.getMessage());
        paramString2 = str;
      }  
    return (ArrayList<SyncInsertResponse>)paramString2;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/WsAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */