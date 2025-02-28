package com.roadtrack.onstar.analytics;

import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.utils.Utilities;

public class AnalyticsUsesCase {
  private final String TAG = AnalyticsUsesCase.class.getSimpleName();
  
  public AnalyticsEventModel getModelForButtonActions(Enums.Services paramServices) {
    String str1;
    int i = null.$SwitchMap$com$roadtrack$onstar$Enums$Services[paramServices.ordinal()];
    if (i != 3) {
      if (i != 6) {
        if (i != 7) {
          String str;
          StringBuilder stringBuilder1;
          switch (i) {
            default:
              str1 = "NOT_FOUND getModelForButtonActions";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 31:
              str1 = "buttons_pid_action";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 29:
            case 30:
              str1 = "buttons_parking";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 28:
              str1 = "buttons_speed_always";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 26:
            case 27:
              str1 = "buttons_speed";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 25:
              str1 = "buttons_valet";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 24:
              str1 = "buttons_historical";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 23:
              str1 = "buttons_geo_fence";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 22:
              str1 = "buttons_disarm";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 21:
              str1 = "button_dtc";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 20:
              str1 = "button_settings";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 19:
              str1 = "button_notifications";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 17:
            case 18:
              str1 = "button_follow_me";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 15:
            case 16:
              str1 = "button_horn";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 14:
              str1 = "button_horn_ligths";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 13:
              str1 = "button_ligths";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 12:
              str1 = "button_navigation";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 11:
              str1 = "button_close_doors";
              str = this.TAG;
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append(" name: ");
              stringBuilder1.append(str1);
              Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
              return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
            case 10:
              break;
          } 
          str1 = "button_open_doors";
        } else {
          str1 = "buttons_dtc_action";
        } 
      } else {
        str1 = "buttons_find_me";
      } 
    } else {
      str1 = "buttons_pid";
    } 
    String str2 = this.TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" name: ");
    stringBuilder.append(str1);
    Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
  }
  
  public AnalyticsEventModel getModelForEventBottom(Enums.Services paramServices) {
    switch (paramServices) {
      default:
        str1 = "NOT_FOUND getModelForEventBottom";
        str2 = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" name: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
        return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
      case ActionRenewal:
        str1 = "bottom_action_renewal";
        str2 = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" name: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
        return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
      case ClubOnStar:
        str1 = "bottom_action_club_ituran";
        str2 = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" name: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
        return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
      case DTCAction:
        str1 = "bottom_action_dtc";
        str2 = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" name: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
        return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
      case FindMe:
        str1 = "bottom_action_find_me";
        str2 = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" name: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
        return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
      case WebPage:
        str1 = "bottom_action_web_page";
        str2 = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" name: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
        return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
      case ActionIvr:
        str1 = "bottom_action_ivr";
        str2 = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" name: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
        return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
      case pid:
        str1 = "bottom_action_pid";
        str2 = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" name: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
        return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
      case ActionAssistance:
        str1 = "bottom_action_assistance";
        str2 = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append(" name: ");
        stringBuilder.append(str1);
        Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
        return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
      case ActionEmergency:
        break;
    } 
    String str1 = "bottom_action_emergency";
    String str2 = this.TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" name: ");
    stringBuilder.append(str1);
    Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
  }
  
  public AnalyticsEventModel getModelForEventDrawer(Enums.Services paramServices) {
    String str1;
    int i = null.$SwitchMap$com$roadtrack$onstar$Enums$Services[paramServices.ordinal()];
    if (i != 2) {
      if (i != 5) {
        if (i != 8) {
          if (i != 9) {
            if (i != 19) {
              if (i != 20) {
                StringBuilder stringBuilder1;
                String str;
                switch (i) {
                  default:
                    str1 = "NOT_FOUND getModelForEventDrawer";
                    str = this.TAG;
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(" name: ");
                    stringBuilder1.append(str1);
                    Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
                    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
                  case 40:
                    str1 = "drawer_action_moip_ticket";
                    str = this.TAG;
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(" name: ");
                    stringBuilder1.append(str1);
                    Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
                    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
                  case 39:
                    str1 = "drawer_action_notifications_lateral";
                    str = this.TAG;
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(" name: ");
                    stringBuilder1.append(str1);
                    Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
                    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
                  case 38:
                    str1 = "drawer_action_exit";
                    str = this.TAG;
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(" name: ");
                    stringBuilder1.append(str1);
                    Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
                    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
                  case 37:
                    str1 = "drawer_action_my_credit_card";
                    str = this.TAG;
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(" name: ");
                    stringBuilder1.append(str1);
                    Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
                    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
                  case 35:
                  case 36:
                    str1 = "drawer_action_my_account";
                    str = this.TAG;
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(" name: ");
                    stringBuilder1.append(str1);
                    Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
                    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
                  case 34:
                    str1 = "drawer_action_paymen_history";
                    str = this.TAG;
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(" name: ");
                    stringBuilder1.append(str1);
                    Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
                    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
                  case 33:
                    str1 = "drawer_action_my_plan";
                    str = this.TAG;
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(" name: ");
                    stringBuilder1.append(str1);
                    Utilities.escribeArchivo(str, "AnalyticsEventModel", stringBuilder1.toString());
                    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
                  case 32:
                    break;
                } 
                str1 = "drawer_action_dicas";
              } else {
                str1 = "drawer_action_settings";
              } 
            } else {
              str1 = "drawer_action_notifications";
            } 
          } else {
            str1 = "drawer_action_renewal";
          } 
        } else {
          str1 = "drawer_action_club_onstar";
        } 
      } else {
        str1 = "drawer_action_web_page";
      } 
    } else {
      str1 = "drawer_action_assistance";
    } 
    String str2 = this.TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(" name: ");
    stringBuilder.append(str1);
    Utilities.escribeArchivo(str2, "AnalyticsEventModel", stringBuilder.toString());
    return new AnalyticsEventModel(paramServices.GetCodeString(), str1, paramServices.name());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/analytics/AnalyticsUsesCase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */