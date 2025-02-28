package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class zzx {
  static void zza(zzas paramzzas, SQLiteDatabase paramSQLiteDatabase) {
    if (paramzzas != null) {
      File file = new File(paramSQLiteDatabase.getPath());
      if (!file.setReadable(false, false))
        paramzzas.zzjj().zzby("Failed to turn off database read permission"); 
      if (!file.setWritable(false, false))
        paramzzas.zzjj().zzby("Failed to turn off database write permission"); 
      if (!file.setReadable(true, true))
        paramzzas.zzjj().zzby("Failed to turn on database read permission for owner"); 
      if (!file.setWritable(true, true))
        paramzzas.zzjj().zzby("Failed to turn on database write permission for owner"); 
      return;
    } 
    throw new IllegalArgumentException("Monitor must not be null");
  }
  
  static void zza(zzas paramzzas, SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) throws SQLiteException {
    if (paramzzas != null) {
      if (!zza(paramzzas, paramSQLiteDatabase, paramString1))
        paramSQLiteDatabase.execSQL(paramString2); 
      if (paramzzas != null)
        try {
          SQLiteException sQLiteException;
          Set<String> set = zzb(paramSQLiteDatabase, paramString1);
          String[] arrayOfString = paramString3.split(",");
          int k = arrayOfString.length;
          int j = 0;
          int i = 0;
          while (i < k) {
            paramString2 = arrayOfString[i];
            if (set.remove(paramString2)) {
              i++;
              continue;
            } 
            sQLiteException = new SQLiteException();
            i = String.valueOf(paramString1).length();
            j = String.valueOf(paramString2).length();
            StringBuilder stringBuilder = new StringBuilder();
            this(i + 35 + j);
            stringBuilder.append("Table ");
            stringBuilder.append(paramString1);
            stringBuilder.append(" is missing required column: ");
            stringBuilder.append(paramString2);
            this(stringBuilder.toString());
            throw sQLiteException;
          } 
          if (paramArrayOfString != null)
            for (i = j; i < paramArrayOfString.length; i += 2) {
              if (!set.remove(paramArrayOfString[i]))
                sQLiteException.execSQL(paramArrayOfString[i + 1]); 
            }  
          if (!set.isEmpty())
            paramzzas.zzjj().zze("Table has extra columns. table, columns", paramString1, TextUtils.join(", ", set)); 
          return;
        } catch (SQLiteException sQLiteException) {
          paramzzas.zzjg().zzg("Failed to verify columns on table that was just created", paramString1);
          throw sQLiteException;
        }  
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("Monitor must not be null");
      throw illegalArgumentException;
    } 
    throw new IllegalArgumentException("Monitor must not be null");
  }
  
  private static boolean zza(zzas paramzzas, SQLiteDatabase paramSQLiteDatabase, String paramString) {
    if (paramzzas != null) {
      Cursor cursor2 = null;
      Cursor cursor1 = null;
      try {
        Cursor cursor = paramSQLiteDatabase.query("SQLITE_MASTER", new String[] { "name" }, "name=?", new String[] { paramString }, null, null, null);
        cursor1 = cursor;
        cursor2 = cursor;
        boolean bool = cursor.moveToFirst();
        if (cursor != null)
          cursor.close(); 
        return bool;
      } catch (SQLiteException sQLiteException) {
        cursor1 = cursor2;
        paramzzas.zzjj().zze("Error querying for table", paramString, sQLiteException);
        if (cursor2 != null)
          cursor2.close(); 
        return false;
      } finally {}
      if (cursor1 != null)
        cursor1.close(); 
      throw paramzzas;
    } 
    throw new IllegalArgumentException("Monitor must not be null");
  }
  
  private static Set<String> zzb(SQLiteDatabase paramSQLiteDatabase, String paramString) {
    HashSet<? super String> hashSet = new HashSet();
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 22);
    stringBuilder.append("SELECT * FROM ");
    stringBuilder.append(paramString);
    stringBuilder.append(" LIMIT 0");
    Cursor cursor = paramSQLiteDatabase.rawQuery(stringBuilder.toString(), null);
    try {
      Collections.addAll(hashSet, cursor.getColumnNames());
      return (Set)hashSet;
    } finally {
      cursor.close();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */