package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

final class zzap extends SQLiteOpenHelper {
  private final zzao zzalq;
  
  zzap(zzao paramzzao, Context paramContext, String paramString) {
    super(paramContext, paramString, null, 1);
  }
  
  public final SQLiteDatabase getWritableDatabase() throws SQLiteException {
    try {
      return super.getWritableDatabase();
    } catch (SQLiteDatabaseLockedException sQLiteDatabaseLockedException) {
      throw sQLiteDatabaseLockedException;
    } catch (SQLiteException sQLiteException) {
      this.zzalq.zzgt().zzjg().zzby("Opening the local database failed, dropping and recreating it");
      if (!this.zzalq.getContext().getDatabasePath("google_app_measurement_local.db").delete())
        this.zzalq.zzgt().zzjg().zzg("Failed to delete corrupted local db file", "google_app_measurement_local.db"); 
      try {
        return super.getWritableDatabase();
      } catch (SQLiteException sQLiteException1) {
        this.zzalq.zzgt().zzjg().zzg("Failed to open local database. Events will bypass local storage", sQLiteException1);
        return null;
      } 
    } 
  }
  
  public final void onCreate(SQLiteDatabase paramSQLiteDatabase) {
    zzx.zza(this.zzalq.zzgt(), paramSQLiteDatabase);
  }
  
  public final void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  
  public final void onOpen(SQLiteDatabase paramSQLiteDatabase) {
    if (Build.VERSION.SDK_INT < 15) {
      Cursor cursor = null;
      try {
        Cursor cursor1 = paramSQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
        cursor = cursor1;
        cursor1.moveToFirst();
      } finally {
        if (cursor != null)
          cursor.close(); 
      } 
    } 
    zzx.zza(this.zzalq.zzgt(), paramSQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
  }
  
  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */