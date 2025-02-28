package com.roadtrack.onstar.platinum;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.ManagerNotificationPlatinum;
import com.roadtrack.onstar.BO.MessagesObjects;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.entities.displaymenu;
import com.roadtrack.onstar.menuPlatinum;
import com.roadtrack.onstar.menuplat;
import java.util.ArrayList;
import java.util.Hashtable;

public class MultiModalHMI {
  private static MultiModalHMI instance;
  
  private displaymenu menu;
  
  public static MultiModalHMI getInstance() {
    if (instance == null)
      instance = new MultiModalHMI(); 
    return instance;
  }
  
  public void cancelMenuHMI(Fragment paramFragment, Context paramContext) {
    MainActivity mainActivity = (MainActivity)paramContext;
    GlobalMembers.btnpress = 0;
    sendCancelMenuACK(1);
    if (GlobalMembers.iAppNavigationStatus == 0 && GlobalMembers.numNavegacion == 0 && paramFragment != null)
      mainActivity.closeHmiMenu(); 
    int i = GlobalMembers.intmenuplat;
    if (i != 1) {
      if (i != 2) {
        if (i == 3) {
          if ((GlobalMembers.globalActivity.getResources().getConfiguration()).orientation == 2)
            GlobalMembers.intmenuplat = 3; 
          GlobalMembers.ListenerCloseMenu.onevent();
        } 
      } else if ((GlobalMembers.globalActivity.getResources().getConfiguration()).orientation == 2) {
        GlobalMembers.intmenuplat = 2;
      } 
    } else {
      GlobalMembers.bMostrandoMenuHMI = false;
      GlobalMembers.ListenerCloseMenu.onevent();
    } 
    Intent intent = new Intent(paramContext, MainActivity.class);
    intent.addFlags(67108864);
    mainActivity.startActivity(intent);
  }
  
  public void closeMenuHMI(int paramInt) {
    MainActivity.getmNPlatinum().closeMenu(paramInt, MessagesObjects.Device_OpCodes.CloseMenu.GetOpCode());
  }
  
  public void closeMenuHMI(int paramInt1, int paramInt2) {
    MainActivity.getmNPlatinum().closeMenu(paramInt1, paramInt2);
  }
  
  public void displayMenu(ArrayList<Hashtable<String, Object>> paramArrayList, Context paramContext) {
    boolean bool;
    MainActivity mainActivity = (MainActivity)paramContext;
    if (paramArrayList != null)
      GlobalMembers.menuPlatinum = (displaymenu)((Hashtable)paramArrayList.get(0)).get("Message"); 
    if (GlobalMembers.menuPlatinum == null) {
      bool = true;
    } else {
      bool = true;
    } 
    sendDisplayMenuACK(bool);
    if (bool == true) {
      mainActivity.chargedFragment((Fragment)new menuPlatinum());
      executeMenuHMI(paramContext);
    } 
  }
  
  public void executeMenuHMI(Context paramContext) {
    GlobalMembers.numVerFragment = 1;
    if (GlobalMembers.numVerFragment == 1) {
      GlobalMembers.bMostrandoMenuHMI = true;
      GlobalMembers.intmenuplat = getMenuAction();
      startActionHMI(GlobalMembers.intmenuplat, paramContext);
    } 
  }
  
  public int getMenuAction() {
    if (GlobalMembers.intmenuplat == 3 && !GlobalMembers.bshowmenuland && (GlobalMembers.globalActivity.getResources().getConfiguration()).orientation == 1) {
      b1 = 1;
    } else {
      b1 = 0;
    } 
    byte b2 = b1;
    if (GlobalMembers.intmenuplat == 2) {
      b2 = b1;
      if (!GlobalMembers.bshowmenuland) {
        b2 = b1;
        if ((GlobalMembers.globalActivity.getResources().getConfiguration()).orientation == 1)
          b2 = 1; 
      } 
    } 
    byte b1 = b2;
    if (GlobalMembers.intmenuplat == 4) {
      b1 = b2;
      if ((GlobalMembers.globalActivity.getResources().getConfiguration()).orientation == 2)
        b1 = 3; 
    } 
    b2 = b1;
    if (GlobalMembers.intmenuplat == 5) {
      b2 = b1;
      if ((GlobalMembers.globalActivity.getResources().getConfiguration()).orientation == 2)
        b2 = 2; 
    } 
    if (GlobalMembers.intmenuplat >= 5)
      b2 = 0; 
    return b2;
  }
  
  public displaymenu getMenuToDisplay(String[] paramArrayOfString) {
    this.menu = new displaymenu();
    this.menu.setTypeControl(paramArrayOfString[1]);
    this.menu.setTitle(paramArrayOfString[2]);
    paramArrayOfString = paramArrayOfString[3].split(";");
    this.menu.setOptionsMenu(paramArrayOfString);
    return this.menu;
  }
  
  public void sendCancelMenuACK(int paramInt) {
    ManagerNotificationPlatinum managerNotificationPlatinum = MainActivity.getmNPlatinum();
    if (managerNotificationPlatinum != null) {
      managerNotificationPlatinum.cancelMenu_ACK(paramInt);
    } else {
      Toast.makeText(GlobalMembers.contexGlobal, "", 1).show();
    } 
  }
  
  public void sendDisplayMenuACK(int paramInt) {
    ManagerNotificationPlatinum managerNotificationPlatinum = MainActivity.getmNPlatinum();
    if (managerNotificationPlatinum != null) {
      managerNotificationPlatinum.displayMenu_ACK(paramInt);
    } else {
      Toast.makeText(GlobalMembers.contexGlobal, "", 1).show();
    } 
  }
  
  public void startActionHMI(int paramInt, Context paramContext) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt == 3 && (GlobalMembers.globalActivity.getResources().getConfiguration()).orientation == 2) {
            GlobalMembers.EstadoAppGlobal = 3;
            paramContext.startActivity(new Intent(paramContext, menuplat.class));
          } 
        } else if ((GlobalMembers.globalActivity.getResources().getConfiguration()).orientation == 2) {
          GlobalMembers.EstadoAppGlobal = 4;
        } 
      } else {
        GlobalMembers.ListenerRefreshUI.onEvent();
      } 
    } else {
      GlobalMembers.intmenuplat = 1;
      if (GlobalMembers.iAppNavigationStatus == 0)
        paramContext.startActivity(new Intent(paramContext, menuplat.class)); 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/platinum/MultiModalHMI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */