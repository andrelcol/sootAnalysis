package com.roadtrack.onstar.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ListButtonFactory {
  public static ListButton getListButton(Enums.Services paramServices, Context paramContext, ListButton paramListButton, int paramInt) {
    LinkedHashMap linkedHashMap;
    Drawable drawable;
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str2 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_notificaciones_1, 2131689930);
    String str14 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_consejos_1, 2131689914);
    String str3 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_configuracion_1, 2131689913);
    String str8 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_infoonstar_1, 2131689924);
    String str7 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_accionmiplan_1, 2131689882);
    String str13 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.lateral_menu_lbl_salir_4, 2131690005);
    String str5 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.renovacion_lbl_alterarrenovar, 2131690279);
    String str12 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.renovacion_lbl_micuenta, 2131690300);
    String str15 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_navigation_fav_lbl_historial_1, 2131689942);
    String str9 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_navigation_credit_card_lateral, 2131689941);
    String str10 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.renovacion_lbl_informaciontarjeta, 2131690297);
    String str6 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_asistencia_1, 2131689908);
    String str11 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.global_lbl_club, 2131689911);
    String str1 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.renovacion_lbl_viaboletos, 2131690325);
    String str4 = Utilities.getStringFromConfigList(paramContext, stringsResourcesVO.renovacion_lbl_viaboletos_2, 2131690326);
    switch (paramServices) {
      default:
        return null;
      case OptionMoipTicket:
        if (paramListButton != null) {
          paramListButton.setMyService(paramServices);
          Drawable drawable1 = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_submenu, 2131165540);
          paramListButton.getIconView().setImageDrawable(drawable1);
          UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected((onstarApplication)GlobalMembers.contexGlobal, "ListButtonFactory");
          new LinkedHashMap<Object, Object>();
          if (userDevicesVO != null) {
            linkedHashMap = userDevicesVO.getLateralActions();
            new ArrayList();
            if (linkedHashMap.containsKey(String.valueOf(Enums.Services.ActionPaymenHistory.GetCode()))) {
              paramListButton.getTitleText().setText(str1);
            } else {
              paramListButton.getTitleText().setText(str4);
            } 
          } 
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ClubOnStar:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)linkedHashMap);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_launcher2, 2131165498);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getIconView().setScaleX(1.3F);
          paramListButton.getIconView().setScaleY(1.3F);
          paramListButton.getTitleText().setText(str11);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionAssistance:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_btnasistenciamenu_onstar, 2131165442);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str6);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case OptionMyCreditCard:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_submenu, 2131165540);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str10);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionCreditCardChanged:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_submenu, 2131165540);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str9);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionPaymenHistory:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_submenu, 2131165540);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str15);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionMyAccount:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_webpage_account, 2131165352);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str12);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionRenewal:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_webpage, 2131165351);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str5);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionExit:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.onstar_ic_logout, 2131165610);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str13);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionMyPlan:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_plan_white, 2131165512);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str7);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case WebPage:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.btn_webpage, 2131165351);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str8);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionDicas:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_tips_white, 2131165545);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str14);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionNotificationsLateral:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.onstar_ic_notificaciones, 2131165611);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str2);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case ActionNotifications:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.onstar_ic_notificaciones, 2131165611);
          paramListButton.getIconView().setImageDrawable(drawable);
          paramListButton.getTitleText().setText(str2);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        return paramListButton;
      case None:
        if (paramListButton != null) {
          paramListButton.setMyService((Enums.Services)drawable);
          Drawable drawable1 = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_keyboard_arrow_right_white_48dp, 2131165482);
          paramListButton.getIconView().setImageDrawable(drawable1);
          paramListButton.getTitleText().setText(str2);
          paramListButton.set_action_service_status(paramInt);
          setStyle(paramListButton, paramContext);
        } 
        break;
      case ActionSettings:
        break;
    } 
    if (paramListButton != null) {
      paramListButton.setMyService((Enums.Services)drawable);
      drawable = Utilities.getDrawableFromConfigList(paramContext, DrawableResourcesVO.ic_settings_white, 2131165517);
      paramListButton.getIconView().setImageDrawable(drawable);
      paramListButton.getTitleText().setText(str3);
      paramListButton.set_action_service_status(paramInt);
      setStyle(paramListButton, paramContext);
    } 
    return paramListButton;
  }
  
  private static void setStyle(ListButton paramListButton, Context paramContext) {
    paramListButton.getTitleText().setTextColor(ContextCompat.getColor(paramContext, 2131034199));
    paramListButton.getTitleText().setTextSize(2, 12.0F);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/custom/ListButtonFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */