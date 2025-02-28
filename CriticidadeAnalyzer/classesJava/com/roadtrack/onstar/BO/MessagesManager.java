package com.roadtrack.onstar.BO;

import android.content.Context;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.entities.MessagesDetail;
import com.roadtrack.onstar.entities.OptionMenu;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MessagesManager {
  private static String APP_TYPE = "";
  
  private static String Received = "0";
  
  private static String Save = "1";
  
  private static String Send = "0";
  
  public MessagesManager() {
    new GlobalMembers();
  }
  
  public static String getDateFormatFromString(String paramString) {
    Date date = new Date(paramString);
    return GlobalMembers.DateFormat.format(date);
  }
  
  private boolean removeDuplicates(List<OptionMenu> paramList) {
    int i = GlobalMembers.mHistoryList.size();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (i > 0) {
      Iterator<OptionMenu> iterator = GlobalMembers.mHistoryList.iterator();
      while (true) {
        bool1 = bool2;
        if (iterator.hasNext()) {
          OptionMenu optionMenu = iterator.next();
          if (optionMenu.getAplicationType().equals(((OptionMenu)paramList.get(0)).getAplicationType()) && optionMenu.getDescription().equals(((OptionMenu)paramList.get(0)).getDescription()) && optionMenu.getName().equals(((OptionMenu)paramList.get(0)).getName()) && getDateFormatFromString(optionMenu.getDateMessage()).equals(getDateFormatFromString(((OptionMenu)paramList.get(0)).getDateMessage())) && optionMenu.getId().equals(((OptionMenu)paramList.get(0)).getId())) {
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
  
  public static void saveFile(String paramString, Context paramContext) {}
  
  private void sendingToPlatinum(boolean paramBoolean) {
    ManagerNotificationPlatinum managerNotificationPlatinum = MainActivity.getmNPlatinum();
    if (paramBoolean) {
      managerNotificationPlatinum.ACK_InboundMessage(0, Enums.statusGeneric.Success);
    } else {
      managerNotificationPlatinum.ACK_InboundMessage(0, Enums.statusGeneric.Failure);
    } 
  }
  
  public OptionMenu fillChat(String paramString1, String paramString2, String paramString3) {
    OptionMenu optionMenu = new OptionMenu();
    try {
      optionMenu.Category = APP_TYPE;
      optionMenu.id = paramString1;
      optionMenu.waith = Integer.parseInt(Save);
      optionMenu.send = Integer.parseInt(Send);
      optionMenu.send_complete = Integer.parseInt(Received);
      optionMenu.Name = paramString3.substring(11, 16);
      optionMenu.DateMessage = paramString3;
      optionMenu.Description = paramString2;
      optionMenu.AplicationType = "external";
      return optionMenu;
    } catch (Exception exception) {
      Utilities.escribeArchivo("MessageManager", "Error: optionMenu", exception.getMessage());
      return optionMenu;
    } 
  }
  
  public boolean saveChatMessages(MessagesDetail paramMessagesDetail) {
    APP_TYPE = "Chat";
    try {
      String str1 = paramMessagesDetail.getTextMessage();
      String str2 = paramMessagesDetail.getDateMessage().toString();
      ArrayList<OptionMenu> arrayList = new ArrayList();
      this();
      OptionMenu optionMenu = fillChat(paramMessagesDetail.getIdCode(), str1, str2);
      arrayList.add(optionMenu);
      if (!removeDuplicates(arrayList)) {
        GlobalMembers.mHistoryList.add(optionMenu);
        saveFile("chatmessages", GlobalMembers.contexGlobal);
        boolean bool = true;
      } else {
        boolean bool = false;
      } 
      try {
        sendingToPlatinum(true);
      } catch (Exception null) {}
    } catch (Exception exception) {
      boolean bool = false;
    } 
    Utilities.escribeArchivo("MessageManager", "Error: saveChatMessage", exception.getMessage());
    sendingToPlatinum(false);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/MessagesManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */