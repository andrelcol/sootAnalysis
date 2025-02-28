package com.roadtrack.onstar.utils;

import android.content.Context;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.Historical;
import com.roadtrack.onstar.VO.NavigationVO;
import com.roadtrack.onstar.VO.PushAlertsVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import java.util.ArrayList;

public class BRInfo {
  private Context _ctx = null;
  
  private String global_lbl_accionlocalizame_1;
  
  private String global_lbl_accionsigueme_1;
  
  private String global_lbl_accionstatusmovimento_1;
  
  private StringsResourcesVO stringsResourcesVO;
  
  public BRInfo(Context paramContext) {
    this._ctx = paramContext;
    this.stringsResourcesVO = new StringsResourcesVO();
    this.global_lbl_accionlocalizame_1 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionlocalizame_1, 2131689877);
    this.global_lbl_accionstatusmovimento_1 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusmovimento_1, 2131689897);
    this.global_lbl_accionsigueme_1 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionsigueme_1, 2131689885);
  }
  
  private String findNameAction(int paramInt) {
    String str5 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionsigueme_1, 2131689885);
    String str8 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionlucesybocinasl_1, 2131689880);
    String str4 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionluces_1, 2131689878);
    String str11 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accioncerrarpuertassl_1, 2131689855);
    String str10 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionabrirpuertassl_1, 2131689843);
    String str15 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_acciondesactivarpincodesl_1, 2131689857);
    String str13 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionalertamovsl_1, 2131689846);
    String str2 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionalertavelsl_1, 2131689852);
    String str12 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionalertabocina_1, 2131689844);
    String str9 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionalertavaletsl_1, 2131689850);
    String str16 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_navegacion_1, 2131689929);
    String str7 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_notificaciones_1, 2131689930);
    String str1 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionpids_1, 2131689884);
    String str3 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionalertavelsl_2, 2131689853);
    String str6 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.navigation_lbl_share_titulocompartirlocalizacion_1, 2131690163);
    String str14 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionenviarruta_1, 2131689874);
    if (paramInt == Enums.Services.DoorsUnlock.GetCode()) {
      str1 = str10;
    } else if (paramInt == Enums.Services.DoorsLock.GetCode()) {
      str1 = str11;
    } else if (paramInt == Enums.Services.Navigation.GetCode()) {
      str1 = str16;
    } else {
      if (paramInt == Enums.Services.Parking.GetCode() || paramInt == Enums.Services.ParkingUUx.GetCode())
        return str13; 
      if (paramInt == Enums.Services.Ligths.GetCode()) {
        str1 = str4;
      } else {
        if (paramInt == Enums.Services.HornLigths.GetCode())
          return str8; 
        if (paramInt == Enums.Services.Horn.GetCode() || paramInt == Enums.Services.HornF1.GetCode())
          return str12; 
        if (paramInt == Enums.Services.Speed.GetCode() || paramInt == Enums.Services.SpeedUUx.GetCode())
          return str2; 
        if (paramInt == Enums.Services.SpeedAlways.GetCode()) {
          str1 = str3;
        } else {
          if (paramInt == Enums.Services.FollowMe.GetCode() || paramInt == Enums.Services.FollowMeUUx.GetCode())
            return str5; 
          if (paramInt == Enums.Services.ActionNotifications.GetCode()) {
            str1 = str7;
          } else if (paramInt == Enums.Services.valet.GetCode()) {
            str1 = str9;
          } else if (paramInt == Enums.Services.Disarm.GetCode()) {
            str1 = str15;
          } else if (paramInt == Enums.Services.ActionHistorical.GetCode() || paramInt == Enums.Services.ActionShare.GetCode()) {
            str1 = str6;
          } else if (paramInt == Enums.Services.FindMe.GetCode()) {
            str1 = this.global_lbl_accionlocalizame_1;
          } else if (paramInt == Enums.Services.SendPNDNavigationCommand.GetCode()) {
            str1 = str14;
          } else {
            if (paramInt == Enums.Services.CarFinder.GetCode())
              return str8; 
            if (paramInt != Enums.Services.pid.GetCode())
              if (paramInt == Enums.Services.DTCAction.GetCode()) {
                str1 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.pid_main_lbl_diagnotico_12, 2131690247);
              } else {
                str1 = "";
              }  
          } 
        } 
      } 
    } 
    return str1;
  }
  
  private String getAddress(Historical paramHistorical) {
    if (paramHistorical != null) {
      new ArrayList();
      DBFunctions dBFunctions = new DBFunctions(this._ctx);
      ArrayList<NavigationVO> arrayList = dBFunctions.selectNavigation(dBFunctions.KEY_MESSAGEID, Integer.valueOf(paramHistorical.getMessageId()).intValue());
      if (arrayList != null && arrayList.size() > 0)
        return ((NavigationVO)arrayList.get(0)).getAddress(); 
    } 
    return "";
  }
  
  private String getStatusAction(Historical paramHistorical, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder1;
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(findNameAction(paramInt1));
    stringBuilder2.append("\n");
    String str6 = stringBuilder2.toString();
    String str2 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusposiblenotificacion_1, 2131689901);
    String str3 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusejecutado_1, 2131689888);
    String str5 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusactivada_1, 2131689886);
    String str8 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusactivado_1, 2131689887);
    String str4 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusnoejecutado_1, 2131689900);
    String str9 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusenmovimiento_1, 2131689890);
    String str10 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusencendido_1, 2131689889);
    String str7 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusnoactivada_1, 2131689898);
    String str11 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusenviadogps_1, 2131689891);
    Context context = this._ctx;
    String str12 = this.stringsResourcesVO.global_lbl_accionstatusproceso_1;
    String str1 = Utilities.getStringFromConfigList(context, str12, 2131689902);
    if (paramInt2 == Enums.actionStatus.None.GetCode()) {
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str6);
      stringBuilder1.append(str1);
      str1 = stringBuilder1.toString();
    } else if (paramInt2 == Enums.actionStatus.Success.GetCode() && !stringBuilder1.getCompletion_code().equals("3")) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str6);
      if (stringBuilder1.getResponseErrorId().equals("408")) {
        str1 = str2;
      } else {
        str1 = str3;
      } 
      stringBuilder.append(str1);
      str1 = stringBuilder.toString();
      if (paramInt1 == Enums.Services.Speed.GetCode() || paramInt1 == Enums.Services.SpeedUUx.GetCode() || paramInt1 == Enums.Services.SpeedAlways.GetCode()) {
        if (stringBuilder1.getResponseErrorId().equals("408") && !stringBuilder1.getCompletion_code().equals("15")) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append(str6);
          stringBuilder1.append(str2);
          str1 = stringBuilder1.toString();
        } else {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append(str6);
          stringBuilder1.append(str5);
          str1 = stringBuilder1.toString().replace(".0", "");
        } 
        return str1.replace("\n,", ",");
      } 
      if (paramInt1 == Enums.Services.FindMe.GetCode()) {
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str6);
        if (!stringBuilder1.getResponseErrorId().equals("408") || stringBuilder1.getCompletion_code().equals("15"))
          str2 = str3; 
        stringBuilder3.append(str2);
        String str = stringBuilder3.toString();
      } else {
        if (paramInt1 == Enums.Services.FollowMe.GetCode() || paramInt1 == Enums.Services.FollowMeUUx.GetCode()) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str6);
          if (!stringBuilder1.getResponseErrorId().equals("408") || stringBuilder1.getCompletion_code().equals("15"))
            str2 = str8; 
          stringBuilder3.append(str2);
          String str = stringBuilder3.toString();
          return str.replace("\n,", ",");
        } 
        if (paramInt1 == Enums.Services.Parking.GetCode() || paramInt1 == Enums.Services.ParkingUUx.GetCode()) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str6);
          if (!stringBuilder1.getResponseErrorId().equals("408") || stringBuilder1.getCompletion_code().equals("15"))
            str2 = str5; 
          stringBuilder3.append(str2);
          String str = stringBuilder3.toString();
          if (((Historical)stringBuilder1).completion_code.equals("16")) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append(str6);
            stringBuilder1.append(str4);
            stringBuilder1.append(" ");
            stringBuilder1.append(this.global_lbl_accionstatusmovimento_1);
            str = stringBuilder1.toString();
          } 
          return str.replace("\n,", ",");
        } 
        if (paramInt1 == Enums.Services.Ligths.GetCode() || paramInt1 == Enums.Services.CarFinder.GetCode()) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str6);
          if (!stringBuilder1.getResponseErrorId().equals("408") || stringBuilder1.getCompletion_code().equals("15"))
            str2 = str3; 
          stringBuilder3.append(str2);
          String str = stringBuilder3.toString();
          if (stringBuilder1.getCompletion_code().equals("16")) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append(str6.replace("\n", ""));
            stringBuilder1.append(str10);
            str = stringBuilder1.toString();
          } 
          return str.replace("\n,", ",");
        } 
        if (paramInt1 == Enums.Services.HornLigths.GetCode() || paramInt1 == Enums.Services.CarFinder.GetCode()) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str6);
          if (!stringBuilder1.getResponseErrorId().equals("408") || stringBuilder1.getCompletion_code().equals("15"))
            str2 = str3; 
          stringBuilder3.append(str2);
          String str = stringBuilder3.toString();
          if (stringBuilder1.getCompletion_code().equals("16")) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append(str6.replace("\n", ""));
            stringBuilder1.append(str10);
            str = stringBuilder1.toString();
          } 
          return str.replace("\n,", ",");
        } 
        if (paramInt1 == Enums.Services.DoorsLock.GetCode()) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str6);
          if (!stringBuilder1.getResponseErrorId().equals("408") || stringBuilder1.getCompletion_code().equals("15"))
            str2 = str3; 
          stringBuilder3.append(str2);
          String str = stringBuilder3.toString();
          if (stringBuilder1.getCompletion_code().equals("16")) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append(str6.replace("\n", ""));
            stringBuilder1.append(str9);
            str = stringBuilder1.toString();
          } 
        } else if (paramInt1 == Enums.Services.DoorsUnlock.GetCode()) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str6);
          if (!stringBuilder1.getResponseErrorId().equals("408") || stringBuilder1.getCompletion_code().equals("15"))
            str2 = str3; 
          stringBuilder3.append(str2);
          String str = stringBuilder3.toString();
          if (stringBuilder1.getCompletion_code().equals("16")) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append(str6.replace("\n", ""));
            stringBuilder1.append(str9);
            str = stringBuilder1.toString();
          } 
        } else if (paramInt1 == Enums.Services.Disarm.GetCode()) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str6);
          if (!stringBuilder1.getResponseErrorId().equals("408") || stringBuilder1.getCompletion_code().equals("15"))
            str2 = str3; 
          stringBuilder3.append(str2);
          String str = stringBuilder3.toString();
          if (stringBuilder1.getCompletion_code().equals("16")) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append(str6.replace("\n", ""));
            stringBuilder1.append(str9);
            str = stringBuilder1.toString();
          } 
        } else if (paramInt1 == Enums.Services.SendPNDNavigationCommand.GetCode()) {
          str1 = String.format(str11, new Object[] { getAddress((Historical)stringBuilder1) });
        } else if (paramInt1 == Enums.Services.valet.GetCode()) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str6);
          if (!stringBuilder1.getResponseErrorId().equals("408") || stringBuilder1.getCompletion_code().equals("15"))
            str2 = str5; 
          stringBuilder3.append(str2);
          String str = stringBuilder3.toString();
        } 
      } 
    } else {
      if (paramInt2 != Enums.actionStatus.Failure.GetCode()) {
        String str;
        str1 = str6;
        if (stringBuilder1.getCompletion_code().equals("3")) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str6);
          stringBuilder3.append(str4);
          str = stringBuilder3.toString();
        } 
        return str.replace("\n,", ",");
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str6);
      stringBuilder.append(str4);
      str1 = stringBuilder.toString();
    } 
    return str1.replace("\n,", ",");
  }
  
  public String getActionName(Historical paramHistorical) {
    int i = Integer.parseInt(paramHistorical.getIdAction());
    Enums.Services.FindMe.GetCode();
    return getStatusAction(paramHistorical, i, paramHistorical.idError);
  }
  
  public String getActionName(PushAlertsVO paramPushAlertsVO) {
    String str2 = paramPushAlertsVO.getnActionID();
    if (paramPushAlertsVO.getnActionID().toLowerCase().contains("velocidade") || paramPushAlertsVO.getnActionID().toLowerCase().contains("velocidad")) {
      str2 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusexcediovelocidad_1, 2131689893);
    } else if (paramPushAlertsVO.getnActionID().toLowerCase().contains("movimento") || paramPushAlertsVO.getnActionID().toLowerCase().contains("movimiento")) {
      str2 = this.global_lbl_accionstatusmovimento_1;
    } else if (paramPushAlertsVO.getnActionID().toLowerCase().contains("valet")) {
      str2 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatusexcediorango_1, 2131689892);
    } else if (paramPushAlertsVO.getnActionID().toLowerCase().contains("dtc")) {
      String str = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_acciondtcerror, 2131689873);
      str2 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_acciondescdtcerror, 2131689860);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(" \n ");
      stringBuilder.append(str2);
      str2 = stringBuilder.toString();
    } else if (paramPushAlertsVO.getnActionID().toLowerCase().contains("mantenimiento")) {
      String str7 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionmantenimiento, 2131689881);
      String str8 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_acciondescsmantemiento, 2131689869);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str7);
      stringBuilder.append(" \n ");
      stringBuilder.append(str8);
      String str6 = stringBuilder.toString();
    } 
    String str4 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatuspuntorecibido_1, 2131689903);
    String str3 = Utilities.getStringFromConfigList(this._ctx, this.stringsResourcesVO.global_lbl_accionstatussiguemefinalizado_1, 2131689905);
    String str5 = paramPushAlertsVO.getActionId().toLowerCase();
    String str1 = paramPushAlertsVO.getAlert().toLowerCase();
    if (str5.contains("followme")) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.global_lbl_accionsigueme_1);
      stringBuilder.append(" ");
      stringBuilder.append(str4);
      str2 = stringBuilder.toString();
      if (str1.contains("end")) {
        str2 = this.global_lbl_accionsigueme_1;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str2);
        stringBuilder1.append(" ");
        stringBuilder1.append(str3);
        str2 = stringBuilder1.toString();
      } 
    } 
    return str2;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/BRInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */