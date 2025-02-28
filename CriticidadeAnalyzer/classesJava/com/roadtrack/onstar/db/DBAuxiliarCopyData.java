package com.roadtrack.onstar.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.roadtrack.onstar.DAO.DBFunctions;

public class DBAuxiliarCopyData extends DBHandler {
  Context ctx;
  
  DBFunctions dbFun;
  
  SQLiteDatabase sqLiteDataBase;
  
  public DBAuxiliarCopyData(Context paramContext) {
    super(paramContext);
    this.ctx = paramContext;
    this.dbFun = new DBFunctions(this.ctx);
  }
  
  private void copyDTCData() {
    this.dbFun.copyDTCData(this.sqLiteDataBase);
  }
  
  private void copyGMT() {
    this.dbFun.copyGMT(this.sqLiteDataBase);
  }
  
  private void copyNavigationData() {
    this.dbFun.copyNavigationData(this.sqLiteDataBase);
  }
  
  public void copyData(SQLiteDatabase paramSQLiteDatabase) {
    this.sqLiteDataBase = paramSQLiteDatabase;
    copyNavigationData();
    copyDTCData();
    copyGMT();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/db/DBAuxiliarCopyData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */