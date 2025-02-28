package com.roadtrack.onstar.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.Settings;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;

@SuppressLint({"DefaultLocale"})
public class DBHandler extends SQLiteOpenHelper {
  private static String DB_NAME = "rt.db";
  
  private static String DB_NAME_SCRIPT = "rt.sql";
  
  private static int DB_VERSION = 34;
  
  private static int DB_VERSION_MIN = 9;
  
  Context mContext;
  
  public DBHandler(Context paramContext) {
    super(paramContext, DB_NAME, null, DB_VERSION);
    this.mContext = paramContext;
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
    (new DBFunctions(this.mContext)).executeSqlScript(paramSQLiteDatabase, DB_NAME_SCRIPT);
    onUpgrade(paramSQLiteDatabase, DB_VERSION_MIN, DB_VERSION);
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
    DBFunctions dBFunctions = new DBFunctions(this.mContext);
    while (paramInt1 <= paramInt2) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("rt_");
      stringBuilder1.append(String.valueOf(paramInt1));
      stringBuilder1.append(".sql");
      String str = stringBuilder1.toString();
      dBFunctions.executeSqlScript(paramSQLiteDatabase, str);
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("SQL script: ");
      stringBuilder2.append(str);
      Utilities.escribeArchivo("SQLiteOpenHelper", "onUpgrade", stringBuilder2.toString());
      if (paramInt1 == 26) {
        (new DBAuxiliarCopyData(this.mContext)).copyData(paramSQLiteDatabase);
      } else if (paramInt1 == 30) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory());
        stringBuilder.append("/Android/data/");
        stringBuilder.append(this.mContext.getPackageName());
        stringBuilder.append("/ttndata");
        Utilities.deleteRecursive(UtilitiesFile.getFileFromStringFile(stringBuilder.toString()));
      } else if (paramInt1 == 31 && PreferenceRT.GetValuePreference(GlobalMembers.deviceName, "", onstarApplication.getContext()).equalsIgnoreCase("")) {
        str = Settings.System.getString(this.mContext.getContentResolver(), "device_name");
        if (str == null) {
          PreferenceRT.SetStringPreference(GlobalMembers.deviceName, Utilities.getDeviceName(), onstarApplication.getContext());
        } else {
          PreferenceRT.SetStringPreference(GlobalMembers.deviceName, str, onstarApplication.getContext());
        } 
      } 
      paramInt1++;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/db/DBHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */