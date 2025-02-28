package com.roadtrack.onstar.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CreateDialogGlobal extends BroadcastReceiver {
  private static Context mContext;
  
  private void GeneratePushFolowme() {}
  
  private void sendBroadCast(Context paramContext) {
    Intent intent = new Intent();
    intent.setAction("SHOWDIALOGFOLLOWMEFINISH");
    intent.putExtra("parameter", "show");
    paramContext.sendBroadcast(intent);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    mContext = paramContext;
    if (paramIntent.getAction().equals("FollowmeFinished")) {
      Utilities.escribeArchivo("CreateDialogGlobal", "FOLLOWME", "FOLLOWME FINISHED: onReceive");
      sendBroadCast(mContext);
      GeneratePushFolowme();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/CreateDialogGlobal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */