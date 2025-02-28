package com.roadtrack.onstar.ui.settings;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.Enums;

@TargetApi(9)
public class DownloadReceiver extends BroadcastReceiver {
  private DownloadManager downloadManager;
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    paramContext = GlobalMembers.contexGlobal;
    paramContext.getPackageName().toString();
    this.downloadManager = GlobalMembers.globalDownload;
    if (this.downloadManager != null) {
      DownloadManager.Query query = new DownloadManager.Query();
      Cursor cursor = this.downloadManager.query(query);
      if (cursor.moveToFirst()) {
        int j = cursor.getInt(cursor.getColumnIndex("status"));
        int i = cursor.getInt(cursor.getColumnIndex("reason"));
        if (j == 8) {
          boolean bool = GlobalMembers.isMapZipped;
          GlobalMembers.isDownloadingMap = false;
          if (GlobalMembers.isMapZipped)
            PreferenceRT.SetValuePreference(Enums.SettingsPreference.colombiaMap, Boolean.valueOf(true), paramContext); 
        } else if (j == 16) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("FAILED!\nreason of ");
          stringBuilder.append(i);
          Toast.makeText(paramContext, stringBuilder.toString(), 1).show();
          GlobalMembers.isDownloadingMap = false;
        } else if (j == 4) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("PAUSED!\na causa de ");
          stringBuilder.append(i);
          Toast.makeText(paramContext, stringBuilder.toString(), 1).show();
        } else if (j == 1) {
          Toast.makeText(GlobalMembers.contexGlobal, "PENDING!", 1).show();
        } else if (j == 2) {
          Toast.makeText(GlobalMembers.contexGlobal, "RUNNING!", 1).show();
        } 
      } 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/settings/DownloadReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */