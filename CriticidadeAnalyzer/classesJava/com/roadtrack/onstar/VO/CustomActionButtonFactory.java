package com.roadtrack.onstar.VO;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.utils.Utilities;

public class CustomActionButtonFactory {
  public static CustomActionButton getActionButton(Enums.Services paramServices, Context paramContext, CustomActionButton paramCustomActionButton, int paramInt1, int paramInt2) {
    Drawable drawable;
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.newLatestTraceFollowMe, 2131690198);
    String str23 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionlocalizame_1, 2131689877);
    String str33 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionsigueme_1, 2131689885);
    String str26 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionlucesybocinasl_1, 2131689880);
    String str22 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionluces_1, 2131689878);
    String str18 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionlucesybocina_1, 2131689879);
    String str24 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.actions_popup_lbl_usteddeseaaccionar_2, 2131689644);
    String str17 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accioncerrarpuertassl_1, 2131689855);
    String str15 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accioncerrarpuertas_1, 2131689854);
    String str4 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.actions_popup_lbl_usteddeseacerrar_2, 2131689645);
    String str19 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionabrirpuertassl_1, 2131689843);
    String str16 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionabrirpuertas_1, 2131689842);
    String str8 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.actions_popup_lbl_usteddeseaabrir_2, 2131689643);
    String str9 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_acciondesactivarpincodesl_1, 2131689857);
    String str34 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_acciondesactivarpincode_1, 2131689856);
    String str28 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.alertBeforDisPinCode, 2131689660);
    String str29 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertamovsl_1, 2131689846);
    String str20 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertavelsl_1, 2131689852);
    String str11 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertavel_1, 2131689851);
    String str25 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertabocina_1, 2131689844);
    String str13 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertavaletsl_1, 2131689850);
    String str2 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionpidinfovehiculo_1, 2131689883);
    Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_navegacion_1, 2131689929);
    String str30 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_notificaciones_1, 2131689930);
    String str21 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_emergencia_1, 2131689919);
    String str31 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.emergencia_global_popup_lbl_llamadaemergencia_2, 2131689778);
    String str10 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    String str27 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
    String str6 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.MenuIvr, 2131689519);
    String str1 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionpids_1, 2131689884);
    String str32 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.nothing, 2131690207);
    String str7 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_infoonstar_1, 2131689924);
    String str12 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.pid_main_lbl_diagnotico_12, 2131690247);
    Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.pid_main_lbl_agendar_13, 2131690244);
    String str14 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_club, 2131689911);
    String str3 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.renovacion_popup_lbl_renovaciones, 2131690337);
    String str5 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.navigation_lbl_enviarruta, 2131690153);
    switch (paramServices) {
      default:
        return null;
      case ActionRenewal:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.renovaciones_2, 2131165648));
          paramCustomActionButton.set_str_action_title(str3);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
        } 
        return paramCustomActionButton;
      case ActionSchedule:
      case ClubOnStar:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_menu_on_star, 2131165345));
          paramCustomActionButton.set_str_action_title(str14);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_menu_on_star, 2131165345));
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case DTCAction:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.pid_diagnostic, 2131165635));
          paramCustomActionButton.set_str_action_title(str12);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_pid_2, 2131165503));
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case WebPage:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_webpage, 2131165351));
          paramCustomActionButton.set_str_action_title(str7);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          if (paramInt2 == 2)
            paramCustomActionButton.setHideLabel(true); 
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case DTCEnable:
        return null;
      case NavigationWithTraffic:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.nav_icondirections, 2131165567));
          paramCustomActionButton.set_str_action_title(str32);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionShare:
        return null;
      case ActionPID:
        if (paramCustomActionButton != null) {
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_pid_2, 2131165503);
          paramCustomActionButton.set_drw_action_image(drawable);
          paramCustomActionButton.set_str_action_title(str1);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(drawable);
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionIvr:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_ivr_low, 2131165342));
          paramCustomActionButton.set_str_action_title(str6);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionAssistance:
        if (paramCustomActionButton != null) {
          if (paramInt2 == 2) {
            Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_btnasistenciamenu_onstar, 2131165442);
          } else {
            Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_btnasistenciamenu_onstar, 2131165445);
          } 
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_btnasistenciamenu_onstar, 2131165442));
          paramCustomActionButton.set_str_action_title(str10);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str10);
          paramCustomActionButton.set_dialog_message(str27);
          paramCustomActionButton.set_dialog_drawable(2131165443);
          if (paramInt2 == 2)
            paramCustomActionButton.setHideLabel(true); 
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionEmergency:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_btnemergenciamenu_onstar, 2131165446));
          paramCustomActionButton.set_str_action_title(str21);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str21);
          paramCustomActionButton.set_dialog_message(str31);
          if (Utilities.isAndinos().booleanValue()) {
            paramCustomActionButton.set_dialog_drawable(2131165446);
          } else {
            paramCustomActionButton.set_dialog_drawable(2131165286);
          } 
          if (paramInt2 == 2)
            paramCustomActionButton.setHideLabel(true); 
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionSettings:
      case ActionTraffic:
      case ActionWheather:
      case ActionHistorical:
        return null;
      case ActionNotifications:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_notifonstar, 2131165296));
          paramCustomActionButton.set_str_action_title(str30);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionDTC:
        return null;
      case Navigation:
        if (paramCustomActionButton != null) {
          Drawable drawable1 = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_menu_send_route, 2131165490);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.set_drw_action_image(drawable1);
          paramCustomActionButton.set_str_action_title(str5);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case pid:
        if (paramCustomActionButton != null) {
          Drawable drawable1 = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_pid_2, 2131165503);
          paramCustomActionButton.set_drw_action_image(drawable1);
          paramCustomActionButton.set_str_action_title((String)drawable);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(drawable1);
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
          if (paramInt2 == 2)
            paramCustomActionButton.setHideLabel(true); 
        } 
        return paramCustomActionButton;
      case plpParameters:
        return null;
      case valet:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_valet, 2131165304));
          paramCustomActionButton.set_str_action_title(str13);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case SendPNDNavigationCommand:
      case geoFence:
        return null;
      case HornF1:
      case Horn:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_hornlightsonstar, 2131165291));
          paramCustomActionButton.set_str_action_title(str25);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str18);
          paramCustomActionButton.set_dialog_message(str24);
          paramCustomActionButton.set_dialog_drawable(2131165291);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case StartEngine:
      case CarFinder:
      case textToPND:
        return null;
      case Speed:
      case SpeedUUx:
      case SpeedAlways:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_speedonstar, 2131165300));
          paramCustomActionButton.set_str_action_title(str20);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str11);
          paramCustomActionButton.set_dialog_drawable(2131165300);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case Parking:
      case ParkingUUx:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_parkingonstar, 2131165298));
          paramCustomActionButton.set_str_action_title(str29);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case Disarm:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_pincode, 2131165299));
          paramCustomActionButton.set_str_action_title(str9);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str34);
          paramCustomActionButton.set_dialog_message(str28);
          paramCustomActionButton.set_dialog_drawable(2131165299);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case DoorsUnlock:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_unlockonstar, 2131165303));
          paramCustomActionButton.set_str_action_title(str19);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str16);
          paramCustomActionButton.set_dialog_message(str8);
          paramCustomActionButton.set_dialog_drawable(2131165303);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case DoorsLock:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_lockonstar, 2131165293));
          paramCustomActionButton.set_str_action_title(str17);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str15);
          paramCustomActionButton.set_dialog_message(str4);
          paramCustomActionButton.set_dialog_drawable(2131165293);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case HornLigths:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_hornlightsonstar, 2131165291));
          paramCustomActionButton.set_str_action_title(str26);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str18);
          paramCustomActionButton.set_dialog_message(str24);
          paramCustomActionButton.set_dialog_drawable(2131165291);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case Ligths:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_lightsonstar, 2131165292));
          paramCustomActionButton.set_str_action_title(str22);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str18);
          paramCustomActionButton.set_dialog_message(str24);
          paramCustomActionButton.set_dialog_drawable(2131165291);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case FollowMe:
      case FollowMeUUx:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_followmeonstar, 2131165290));
          paramCustomActionButton.set_str_action_title(str33);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case FindMe:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_findmeonstar, 2131165340));
          paramCustomActionButton.set_str_action_title(str23);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatus(0);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_findmeonstar, 2131165560));
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
        } 
        return paramCustomActionButton;
      case None:
        break;
    } 
    return null;
  }
  
  public static CustomActionButton getActionButtonCustom(Enums.Services paramServices, Context paramContext, CustomActionButton paramCustomActionButton, int paramInt1, int paramInt2, int paramInt3) {
    Drawable drawable;
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.newLatestTraceFollowMe, 2131690198);
    String str33 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionlocalizame_1, 2131689877);
    String str8 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionsigueme_1, 2131689885);
    String str10 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionlucesybocinasl_1, 2131689880);
    String str21 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionluces_1, 2131689878);
    String str14 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionlucesybocina_1, 2131689879);
    String str5 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.actions_popup_lbl_usteddeseaaccionar_2, 2131689644);
    String str19 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accioncerrarpuertassl_1, 2131689855);
    String str17 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accioncerrarpuertas_1, 2131689854);
    String str15 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.actions_popup_lbl_usteddeseacerrar_2, 2131689645);
    String str27 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionabrirpuertassl_1, 2131689843);
    String str11 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionabrirpuertas_1, 2131689842);
    String str32 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.actions_popup_lbl_usteddeseaabrir_2, 2131689643);
    String str9 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_acciondesactivarpincodesl_1, 2131689857);
    String str3 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_acciondesactivarpincode_1, 2131689856);
    String str20 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.alertBeforDisPinCode, 2131689660);
    String str25 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertamovsl_1, 2131689846);
    String str31 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertavelsl_1, 2131689852);
    String str24 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertavel_1, 2131689851);
    String str29 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertabocina_1, 2131689844);
    String str7 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionalertavaletsl_1, 2131689850);
    String str1 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionpidinfovehiculo_1, 2131689883);
    Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_navegacion_1, 2131689929);
    String str22 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_notificaciones_1, 2131689930);
    String str26 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_emergencia_1, 2131689919);
    String str28 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.emergencia_global_popup_lbl_llamadaemergencia_2, 2131689778);
    String str16 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    String str12 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.asistencia_global_popup_lbl_llamadaasistencia_2, 2131689676);
    String str23 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.MenuIvr, 2131689519);
    String str2 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionpids_1, 2131689884);
    String str4 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.nothing, 2131690207);
    String str13 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_infoonstar_1, 2131689924);
    String str18 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.pid_main_lbl_diagnotico_12, 2131690247);
    Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.pid_main_lbl_agendar_13, 2131690244);
    String str6 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_club, 2131689911);
    String str30 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.renovacion_popup_lbl_renovaciones, 2131690337);
    String str34 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.navigation_lbl_enviarruta, 2131690153);
    switch (paramServices) {
      default:
        return null;
      case ActionRenewal:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.renovaciones_2, 2131165648));
          paramCustomActionButton.set_str_action_title(str30);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
        } 
        return paramCustomActionButton;
      case ActionSchedule:
      case ClubOnStar:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_menu_on_star, 2131165345));
          paramCustomActionButton.set_str_action_title(str6);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_menu_on_star, 2131165345));
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case DTCAction:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.pid_diagnostic, 2131165635));
          paramCustomActionButton.set_str_action_title(str18);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_pid_2, 2131165503));
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case WebPage:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_webpage, 2131165351));
          paramCustomActionButton.set_str_action_title(str13);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          if (paramInt2 == 2)
            paramCustomActionButton.setHideLabel(true); 
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case DTCEnable:
        return null;
      case NavigationWithTraffic:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.nav_icondirections, 2131165567));
          paramCustomActionButton.set_str_action_title(str4);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionShare:
        return null;
      case ActionPID:
        if (paramCustomActionButton != null) {
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_pid_2, 2131165503);
          paramCustomActionButton.set_drw_action_image(drawable);
          paramCustomActionButton.set_str_action_title(str2);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(drawable);
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionIvr:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_ivr_low, 2131165342));
          paramCustomActionButton.set_str_action_title(str23);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionAssistance:
        if (paramCustomActionButton != null) {
          if (paramInt2 == 2) {
            Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_btnasistenciamenu_onstar, 2131165442);
          } else {
            Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_btnasistenciamenu_onstar, 2131165445);
          } 
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_btnasistenciamenu_onstar, 2131165442));
          paramCustomActionButton.set_str_action_title(str16);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str16);
          paramCustomActionButton.set_dialog_message(str12);
          paramCustomActionButton.set_dialog_drawable(2131165443);
          if (paramInt2 == 2)
            paramCustomActionButton.setHideLabel(true); 
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionEmergency:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_btnemergenciamenu_onstar, 2131165446));
          paramCustomActionButton.set_str_action_title(str26);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str26);
          paramCustomActionButton.set_dialog_message(str28);
          if (Utilities.isAndinos().booleanValue()) {
            paramCustomActionButton.set_dialog_drawable(2131165446);
          } else {
            paramCustomActionButton.set_dialog_drawable(2131165286);
          } 
          if (paramInt2 == 2)
            paramCustomActionButton.setHideLabel(true); 
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionSettings:
      case ActionTraffic:
      case ActionWheather:
      case ActionHistorical:
        return null;
      case ActionNotifications:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_notifonstar, 2131165296));
          paramCustomActionButton.set_str_action_title(str22);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case ActionDTC:
        return null;
      case Navigation:
        if (paramCustomActionButton != null) {
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_menu_send_route, 2131165490);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.set_drw_action_image(drawable);
          paramCustomActionButton.set_str_action_title(str34);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case pid:
        if (paramCustomActionButton != null) {
          Drawable drawable1 = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_pid_2, 2131165503);
          paramCustomActionButton.set_drw_action_image(drawable1);
          paramCustomActionButton.set_str_action_title((String)drawable);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(drawable1);
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
          if (paramInt2 == 2)
            paramCustomActionButton.setHideLabel(true); 
        } 
        return paramCustomActionButton;
      case plpParameters:
        return null;
      case valet:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_valet, 2131165304));
          paramCustomActionButton.set_str_action_title(str7);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case SendPNDNavigationCommand:
      case geoFence:
        return null;
      case HornF1:
      case Horn:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_hornlightsonstar, 2131165291));
          paramCustomActionButton.set_str_action_title(str29);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str14);
          paramCustomActionButton.set_dialog_message(str5);
          paramCustomActionButton.set_dialog_drawable(2131165291);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case StartEngine:
      case CarFinder:
      case textToPND:
        return null;
      case Speed:
      case SpeedUUx:
      case SpeedAlways:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_speedonstar, 2131165300));
          paramCustomActionButton.set_str_action_title(str31);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str24);
          paramCustomActionButton.set_dialog_drawable(2131165300);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case Parking:
      case ParkingUUx:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_parkingonstar, 2131165298));
          paramCustomActionButton.set_str_action_title(str25);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case Disarm:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_pincode, 2131165299));
          paramCustomActionButton.set_str_action_title(str9);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str3);
          paramCustomActionButton.set_dialog_message(str20);
          paramCustomActionButton.set_dialog_drawable(2131165299);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case DoorsUnlock:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_unlockonstar, 2131165303));
          paramCustomActionButton.set_str_action_title(str27);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str11);
          paramCustomActionButton.set_dialog_message(str32);
          paramCustomActionButton.set_dialog_drawable(2131165303);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case DoorsLock:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_lockonstar, 2131165293));
          paramCustomActionButton.set_str_action_title(str19);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str17);
          paramCustomActionButton.set_dialog_message(str15);
          paramCustomActionButton.set_dialog_drawable(2131165293);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case HornLigths:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_hornlightsonstar, 2131165291));
          paramCustomActionButton.set_str_action_title(str10);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str14);
          paramCustomActionButton.set_dialog_message(str5);
          paramCustomActionButton.set_dialog_drawable(2131165291);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case Ligths:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_lightsonstar, 2131165292));
          paramCustomActionButton.set_str_action_title(str21);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(true);
          paramCustomActionButton.set_dialog_title(str14);
          paramCustomActionButton.set_dialog_message(str5);
          paramCustomActionButton.set_dialog_drawable(2131165291);
          paramCustomActionButton.setIntent(false);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case FollowMe:
      case FollowMeUUx:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.actions_followmeonstar, 2131165290));
          paramCustomActionButton.set_str_action_title(str8);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
        } 
        return paramCustomActionButton;
      case FindMe:
        if (paramCustomActionButton != null) {
          paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_findmeonstar, 2131165340));
          paramCustomActionButton.set_str_action_title(str33);
          paramCustomActionButton.set_action_service_status(paramInt1);
          paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034285));
          paramCustomActionButton.showActionStatusNoAni(paramInt3);
          paramCustomActionButton.set_txt_size(10.0F);
          paramCustomActionButton.setMyService(paramServices);
          paramCustomActionButton.set_has_dialog(false);
          paramCustomActionButton.setIntent(true);
          paramCustomActionButton.setStyle(paramInt2);
          if (paramInt2 == 0) {
            paramCustomActionButton.set_drw_action_image(Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_findmeonstar, 2131165560));
            paramCustomActionButton.set_txt_color(ContextCompat.getColor(paramContext, 2131034276));
            paramCustomActionButton.set_txt_size(12.0F);
          } 
        } 
        return paramCustomActionButton;
      case None:
        break;
    } 
    return null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/CustomActionButtonFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */