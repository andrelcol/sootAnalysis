package com.roadtrack.onstar.utils;

import android.content.Context;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.CustomActionButton;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.custom.ListButton;
import com.roadtrack.onstar.custom.ListButtonFactory;
import com.roadtrack.onstar.onstarApplication;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class UserInterfaceUtils {
  public static String TAG = "UserInterfaceUtils";
  
  public static CustomActionButton getActionButton(LinkedList<CustomActionButton> paramLinkedList, Enums.Services paramServices) {
    for (CustomActionButton customActionButton : paramLinkedList) {
      if (customActionButton != null && customActionButton.getMyService() != null && customActionButton.getMyService().equals(paramServices))
        return customActionButton; 
    } 
    return null;
  }
  
  public static int getActionPosition(int paramInt1, int paramInt2, onstarApplication paramonstarApplication) {
    Enums.Services.GetValue(paramInt1);
    LinkedHashMap<Object, Object> linkedHashMap2 = new LinkedHashMap<Object, Object>();
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(paramonstarApplication, TAG);
    byte b = 2;
    int i = 1;
    LinkedHashMap<Object, Object> linkedHashMap1 = linkedHashMap2;
    if (userDevicesVO != null)
      if (paramInt2 != 0) {
        if (paramInt2 != 1) {
          if (paramInt2 != 2) {
            linkedHashMap1 = linkedHashMap2;
          } else {
            linkedHashMap1 = userDevicesVO.getNavigationActions();
          } 
        } else {
          linkedHashMap1 = userDevicesVO.getButtomActions();
        } 
      } else {
        linkedHashMap1 = userDevicesVO.getMainActions();
      }  
    try {
      Iterator<String> iterator = linkedHashMap1.keySet().iterator();
      for (paramInt2 = i; iterator.hasNext(); paramInt2++) {
        i = Integer.parseInt(iterator.next());
        if (i == paramInt1)
          break; 
      } 
      paramInt1 = paramInt2;
    } catch (Exception exception) {
      paramInt1 = b;
    } 
    return paramInt1;
  }
  
  public static LinkedList<ListButton> getDrawerMenu(Context paramContext, int paramInt) {
    LinkedList<ListButton> linkedList = new LinkedList();
    Enums.Services[] arrayOfServices1 = new Enums.Services[8];
    Enums.Services services = Enums.Services.ActionSettings;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    int j = 0;
    boolean bool4 = false;
    int i = 0;
    arrayOfServices1[0] = services;
    arrayOfServices1[1] = Enums.Services.ActionNotificationsLateral;
    arrayOfServices1[2] = Enums.Services.ActionDicas;
    arrayOfServices1[3] = Enums.Services.WebPage;
    arrayOfServices1[4] = Enums.Services.ActionMyPlan;
    arrayOfServices1[5] = Enums.Services.ActionMyAccount;
    arrayOfServices1[6] = Enums.Services.ActionRenewal;
    arrayOfServices1[7] = Enums.Services.ActionExit;
    Enums.Services[] arrayOfServices5 = new Enums.Services[7];
    arrayOfServices5[0] = Enums.Services.ActionSettings;
    arrayOfServices5[1] = Enums.Services.ActionNotificationsLateral;
    arrayOfServices5[2] = Enums.Services.ActionDicas;
    arrayOfServices5[3] = Enums.Services.ActionMyPlan;
    arrayOfServices5[4] = Enums.Services.ActionMyAccount;
    arrayOfServices5[5] = Enums.Services.ActionRenewal;
    arrayOfServices5[6] = Enums.Services.ActionExit;
    Enums.Services[] arrayOfServices3 = new Enums.Services[4];
    arrayOfServices3[0] = Enums.Services.ActionSettings;
    arrayOfServices3[1] = Enums.Services.ActionNotificationsLateral;
    arrayOfServices3[2] = Enums.Services.ActionDicas;
    arrayOfServices3[3] = Enums.Services.ActionExit;
    Enums.Services[] arrayOfServices2 = new Enums.Services[5];
    arrayOfServices2[0] = Enums.Services.ActionSettings;
    arrayOfServices2[1] = Enums.Services.ActionNotificationsLateral;
    arrayOfServices2[2] = Enums.Services.ActionDicas;
    arrayOfServices2[3] = Enums.Services.WebPage;
    arrayOfServices2[4] = Enums.Services.ActionExit;
    Enums.Services[] arrayOfServices6 = new Enums.Services[9];
    arrayOfServices6[0] = Enums.Services.ActionSettings;
    arrayOfServices6[1] = Enums.Services.ActionNotificationsLateral;
    arrayOfServices6[2] = Enums.Services.ActionDicas;
    arrayOfServices6[3] = Enums.Services.ActionMyPlan;
    arrayOfServices6[4] = Enums.Services.ActionMyAccount;
    arrayOfServices6[5] = Enums.Services.OptionMyCreditCard;
    arrayOfServices6[6] = Enums.Services.ActionPaymenHistory;
    arrayOfServices6[7] = Enums.Services.ActionRenewal;
    arrayOfServices6[8] = Enums.Services.ActionExit;
    Enums.Services[] arrayOfServices4 = new Enums.Services[10];
    arrayOfServices4[0] = Enums.Services.ActionSettings;
    arrayOfServices4[1] = Enums.Services.ActionNotificationsLateral;
    arrayOfServices4[2] = Enums.Services.ActionDicas;
    arrayOfServices4[3] = Enums.Services.WebPage;
    arrayOfServices4[4] = Enums.Services.ActionMyPlan;
    arrayOfServices4[5] = Enums.Services.ActionMyAccount;
    arrayOfServices4[6] = Enums.Services.OptionMyCreditCard;
    arrayOfServices4[7] = Enums.Services.ActionPaymenHistory;
    arrayOfServices4[8] = Enums.Services.ActionRenewal;
    arrayOfServices4[9] = Enums.Services.ActionExit;
    switch (paramInt) {
      default:
        return linkedList;
      case 6:
        j = arrayOfServices6.length;
        for (paramInt = i; paramInt < j; paramInt++)
          linkedList.add(ListButtonFactory.getListButton(arrayOfServices6[paramInt], paramContext, new ListButton(paramContext), 1)); 
      case 5:
        i = arrayOfServices4.length;
        for (paramInt = bool2; paramInt < i; paramInt++)
          linkedList.add(ListButtonFactory.getListButton(arrayOfServices4[paramInt], paramContext, new ListButton(paramContext), 1)); 
      case 4:
        i = arrayOfServices5.length;
        for (paramInt = bool3; paramInt < i; paramInt++)
          linkedList.add(ListButtonFactory.getListButton(arrayOfServices5[paramInt], paramContext, new ListButton(paramContext), 1)); 
      case 3:
        i = arrayOfServices2.length;
        for (paramInt = bool1; paramInt < i; paramInt++)
          linkedList.add(ListButtonFactory.getListButton(arrayOfServices2[paramInt], paramContext, new ListButton(paramContext), 1)); 
      case 2:
        i = arrayOfServices3.length;
        for (paramInt = j; paramInt < i; paramInt++)
          linkedList.add(ListButtonFactory.getListButton(arrayOfServices3[paramInt], paramContext, new ListButton(paramContext), 1)); 
      case 1:
        break;
    } 
    i = arrayOfServices1.length;
    for (paramInt = bool4; paramInt < i; paramInt++)
      linkedList.add(ListButtonFactory.getListButton(arrayOfServices1[paramInt], paramContext, new ListButton(paramContext), 1)); 
  }
  
  public static LinkedList<ListButton> getDrawerMenu(Boolean paramBoolean, onstarApplication paramonstarApplication, Context paramContext) {
    LinkedList<ListButton> linkedList = new LinkedList();
    new LinkedHashMap<Object, Object>();
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(paramonstarApplication, TAG);
    if (userDevicesVO != null) {
      LinkedHashMap linkedHashMap = userDevicesVO.getLateralActions();
      ArrayList<Enums.Services> arrayList = new ArrayList();
      for (String str : linkedHashMap.keySet()) {
        if (((Integer)((LinkedHashMap)linkedHashMap.get(str)).get("submenu")).equals(Integer.valueOf(1))) {
          if (paramBoolean.booleanValue())
            arrayList.add(Enums.Services.GetValue(Integer.parseInt(str))); 
          continue;
        } 
        arrayList.add(Enums.Services.GetValue(Integer.parseInt(str)));
      } 
      for (Enums.Services services : arrayList) {
        ListButton listButton = ListButtonFactory.getListButton(services, paramContext, new ListButton(paramContext), ((Integer)((LinkedHashMap)linkedHashMap.get(services.GetCodeString())).get("water")).intValue());
        if (listButton != null)
          linkedList.add(listButton); 
      } 
    } 
    return linkedList;
  }
  
  public static boolean isActionButton(CustomActionButton paramCustomActionButton) {
    boolean bool = false;
    if (paramCustomActionButton != null) {
      int i = null.$SwitchMap$com$roadtrack$onstar$Enums$Services[paramCustomActionButton.getMyService().ordinal()];
      boolean bool1 = bool;
      if (i != 1) {
        if (i != 2) {
          bool1 = bool;
          if (i != 3) {
            bool1 = bool;
            if (i != 4) {
              bool1 = bool;
              if (i != 5)
                return true; 
            } 
          } 
          return bool1;
        } 
      } else {
        return bool1;
      } 
    } 
    return true;
  }
  
  public static boolean someActionHasMap(onstarApplication paramonstarApplication) {
    new LinkedHashMap<Object, Object>();
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(paramonstarApplication, TAG);
    boolean bool1 = false;
    boolean bool2 = false;
    if (userDevicesVO != null) {
      boolean bool;
      LinkedHashMap linkedHashMap2 = userDevicesVO.getMainActions();
      Iterator<String> iterator = linkedHashMap2.keySet().iterator();
      while (true) {
        bool = bool2;
        if (iterator.hasNext()) {
          if (((Integer)((LinkedHashMap)linkedHashMap2.get(iterator.next())).get("hasmap")).intValue() == 1) {
            bool = true;
            break;
          } 
          continue;
        } 
        break;
      } 
      linkedHashMap2 = userDevicesVO.getButtomActions();
      iterator = linkedHashMap2.keySet().iterator();
      while (true) {
        bool1 = bool;
        if (iterator.hasNext()) {
          if (((Integer)((LinkedHashMap)linkedHashMap2.get(iterator.next())).get("hasmap")).intValue() == 1) {
            bool1 = true;
            break;
          } 
          continue;
        } 
        break;
      } 
      linkedHashMap2 = userDevicesVO.getNavigationActions();
      iterator = linkedHashMap2.keySet().iterator();
      while (true) {
        bool = bool1;
        if (iterator.hasNext()) {
          if (((Integer)((LinkedHashMap)linkedHashMap2.get(iterator.next())).get("hasmap")).intValue() == 1) {
            bool = true;
            break;
          } 
          continue;
        } 
        break;
      } 
      LinkedHashMap linkedHashMap1 = userDevicesVO.getLateralActions();
      iterator = linkedHashMap1.keySet().iterator();
      while (true) {
        bool1 = bool;
        if (iterator.hasNext()) {
          if (((Integer)((LinkedHashMap)linkedHashMap1.get(iterator.next())).get("hasmap")).intValue() == 1) {
            bool1 = true;
            break;
          } 
          continue;
        } 
        break;
      } 
    } 
    return bool1;
  }
  
  public static int tieneWaterMark(int paramInt1, int paramInt2, onstarApplication paramonstarApplication) {
    Enums.Services services = Enums.Services.GetValue(paramInt1);
    LinkedHashMap<Object, Object> linkedHashMap2 = new LinkedHashMap<Object, Object>();
    UserDevicesVO userDevicesVO = Utilities.getLastKnownDeviceSelected(paramonstarApplication, TAG);
    byte b = 2;
    LinkedHashMap<Object, Object> linkedHashMap1 = linkedHashMap2;
    if (userDevicesVO != null)
      if (paramInt2 != 0) {
        if (paramInt2 != 1) {
          if (paramInt2 != 2) {
            if (paramInt2 != 3) {
              linkedHashMap1 = linkedHashMap2;
            } else {
              linkedHashMap1 = userDevicesVO.getPidActions();
            } 
          } else {
            linkedHashMap1 = userDevicesVO.getNavigationActions();
          } 
        } else {
          linkedHashMap1 = userDevicesVO.getButtomActions();
        } 
      } else {
        linkedHashMap1 = userDevicesVO.getMainActions();
      }  
    try {
      paramInt1 = ((Integer)((LinkedHashMap)linkedHashMap1.get(String.valueOf(paramInt1))).get("water")).intValue();
    } catch (Exception exception) {
      paramInt1 = b;
    } 
    if (services.equals(Enums.Services.Favs))
      paramInt1 = 1; 
    return paramInt1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/UserInterfaceUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */