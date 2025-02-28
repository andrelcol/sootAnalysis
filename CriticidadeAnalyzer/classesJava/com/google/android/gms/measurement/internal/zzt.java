package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfi;
import com.google.android.gms.internal.measurement.zzfj;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzya;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzt extends zzfm {
  private static final String[] zzagz = new String[] { "last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;" };
  
  private static final String[] zzaha = new String[] { "origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;" };
  
  private static final String[] zzahb = new String[] { 
      "app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", 
      "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", 
      "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", 
      "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", 
      "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;" };
  
  private static final String[] zzahc = new String[] { "realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;" };
  
  private static final String[] zzahd = new String[] { "has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;" };
  
  private static final String[] zzahe = new String[] { "previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;" };
  
  private final zzw zzahf = new zzw(this, getContext(), "google_app_measurement.db");
  
  private final zzfi zzahg = new zzfi(zzbx());
  
  zzt(zzfn paramzzfn) {
    super(paramzzfn);
  }
  
  private final long zza(String paramString, String[] paramArrayOfString) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    Cursor cursor2 = null;
    Cursor cursor1 = null;
    try {
      Cursor cursor = sQLiteDatabase.rawQuery(paramString, paramArrayOfString);
      cursor1 = cursor;
      cursor2 = cursor;
      if (cursor.moveToFirst()) {
        cursor1 = cursor;
        cursor2 = cursor;
        long l = cursor.getLong(0);
        if (cursor != null)
          cursor.close(); 
        return l;
      } 
      cursor1 = cursor;
      cursor2 = cursor;
      SQLiteException sQLiteException = new SQLiteException();
      cursor1 = cursor;
      cursor2 = cursor;
      this("Database returned empty set");
      cursor1 = cursor;
      cursor2 = cursor;
      throw sQLiteException;
    } catch (SQLiteException sQLiteException) {
      cursor1 = cursor2;
      zzgt().zzjg().zze("Database error", paramString, sQLiteException);
      cursor1 = cursor2;
      throw sQLiteException;
    } finally {}
    if (cursor1 != null)
      cursor1.close(); 
    throw paramString;
  }
  
  private final long zza(String paramString, String[] paramArrayOfString, long paramLong) {
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    Cursor cursor2 = null;
    Cursor cursor1 = null;
    try {
      Cursor cursor = sQLiteDatabase.rawQuery(paramString, paramArrayOfString);
      cursor1 = cursor;
      cursor2 = cursor;
      if (cursor.moveToFirst()) {
        cursor1 = cursor;
        cursor2 = cursor;
        paramLong = cursor.getLong(0);
        if (cursor != null)
          cursor.close(); 
        return paramLong;
      } 
      if (cursor != null)
        cursor.close(); 
      return paramLong;
    } catch (SQLiteException sQLiteException) {
      cursor1 = cursor2;
      zzgt().zzjg().zze("Database error", paramString, sQLiteException);
      cursor1 = cursor2;
      throw sQLiteException;
    } finally {}
    if (cursor1 != null)
      cursor1.close(); 
    throw paramString;
  }
  
  private final Object zza(Cursor paramCursor, int paramInt) {
    int i = paramCursor.getType(paramInt);
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              zzgt().zzjg().zzg("Loaded invalid unknown value type, ignoring it", Integer.valueOf(i));
              return null;
            } 
            zzgt().zzjg().zzby("Loaded invalid blob type value, ignoring it");
            return null;
          } 
          return paramCursor.getString(paramInt);
        } 
        return Double.valueOf(paramCursor.getDouble(paramInt));
      } 
      return Long.valueOf(paramCursor.getLong(paramInt));
    } 
    zzgt().zzjg().zzby("Loaded invalid null value from database");
    return null;
  }
  
  private static void zza(ContentValues paramContentValues, String paramString, Object paramObject) {
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramObject);
    if (paramObject instanceof String) {
      paramContentValues.put(paramString, (String)paramObject);
      return;
    } 
    if (paramObject instanceof Long) {
      paramContentValues.put(paramString, (Long)paramObject);
      return;
    } 
    if (paramObject instanceof Double) {
      paramContentValues.put(paramString, (Double)paramObject);
      return;
    } 
    throw new IllegalArgumentException("Invalid value type");
  }
  
  private final boolean zza(String paramString, int paramInt, zzfj paramzzfj) {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramzzfj);
    if (TextUtils.isEmpty(paramzzfj.zzavn)) {
      zzgt().zzjj().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzas.zzbw(paramString), Integer.valueOf(paramInt), String.valueOf(paramzzfj.zzavm));
      return false;
    } 
    try {
      byte[] arrayOfByte = new byte[paramzzfj.zzvx()];
      zzya zzya = zzya.zzk(arrayOfByte, 0, arrayOfByte.length);
      paramzzfj.zza(zzya);
      zzya.zzza();
      ContentValues contentValues = new ContentValues();
      contentValues.put("app_id", paramString);
      contentValues.put("audience_id", Integer.valueOf(paramInt));
      contentValues.put("filter_id", paramzzfj.zzavm);
      contentValues.put("event_name", paramzzfj.zzavn);
      contentValues.put("data", arrayOfByte);
      try {
        if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1L)
          zzgt().zzjg().zzg("Failed to insert event filter (got -1). appId", zzas.zzbw(paramString)); 
        return true;
      } catch (SQLiteException sQLiteException) {
        zzgt().zzjg().zze("Error storing event filter. appId", zzas.zzbw(paramString), sQLiteException);
        return false;
      } 
    } catch (IOException iOException) {
      zzgt().zzjg().zze("Configuration loss. Failed to serialize event filter. appId", zzas.zzbw(paramString), iOException);
      return false;
    } 
  }
  
  private final boolean zza(String paramString, int paramInt, zzfm paramzzfm) {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramzzfm);
    if (TextUtils.isEmpty(paramzzfm.zzawc)) {
      zzgt().zzjj().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzas.zzbw(paramString), Integer.valueOf(paramInt), String.valueOf(paramzzfm.zzavm));
      return false;
    } 
    try {
      byte[] arrayOfByte = new byte[paramzzfm.zzvx()];
      zzya zzya = zzya.zzk(arrayOfByte, 0, arrayOfByte.length);
      paramzzfm.zza(zzya);
      zzya.zzza();
      ContentValues contentValues = new ContentValues();
      contentValues.put("app_id", paramString);
      contentValues.put("audience_id", Integer.valueOf(paramInt));
      contentValues.put("filter_id", paramzzfm.zzavm);
      contentValues.put("property_name", paramzzfm.zzawc);
      contentValues.put("data", arrayOfByte);
      try {
        if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) == -1L) {
          zzgt().zzjg().zzg("Failed to insert property filter (got -1). appId", zzas.zzbw(paramString));
          return false;
        } 
        return true;
      } catch (SQLiteException sQLiteException) {
        zzgt().zzjg().zze("Error storing property filter. appId", zzas.zzbw(paramString), sQLiteException);
        return false;
      } 
    } catch (IOException iOException) {
      zzgt().zzjg().zze("Configuration loss. Failed to serialize property filter. appId", zzas.zzbw(paramString), iOException);
      return false;
    } 
  }
  
  private final boolean zza(String paramString, List<Integer> paramList) {
    Preconditions.checkNotEmpty(paramString);
    zzcl();
    zzaf();
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    try {
      long l = zza("select count(1) from audience_filter_values where app_id=?", new String[] { paramString });
      int i = Math.max(0, Math.min(2000, zzgv().zzb(paramString, zzai.zzajz)));
      if (l <= i)
        return false; 
      ArrayList<String> arrayList = new ArrayList();
      for (byte b = 0; b < paramList.size(); b++) {
        Integer integer = paramList.get(b);
        if (integer == null || !(integer instanceof Integer))
          return false; 
        arrayList.add(Integer.toString(integer.intValue()));
      } 
      String str = TextUtils.join(",", arrayList);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 2);
      stringBuilder.append("(");
      stringBuilder.append(str);
      stringBuilder.append(")");
      str = stringBuilder.toString();
      stringBuilder = new StringBuilder(String.valueOf(str).length() + 140);
      stringBuilder.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
      stringBuilder.append(str);
      stringBuilder.append(" order by rowid desc limit -1 offset ?)");
      return (sQLiteDatabase.delete("audience_filter_values", stringBuilder.toString(), new String[] { paramString, Integer.toString(i) }) > 0);
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zze("Database error querying filters. appId", zzas.zzbw(paramString), sQLiteException);
      return false;
    } 
  }
  
  private final boolean zzip() {
    return getContext().getDatabasePath("google_app_measurement.db").exists();
  }
  
  public final void beginTransaction() {
    zzcl();
    getWritableDatabase().beginTransaction();
  }
  
  public final void endTransaction() {
    zzcl();
    getWritableDatabase().endTransaction();
  }
  
  final SQLiteDatabase getWritableDatabase() {
    zzaf();
    try {
      return this.zzahf.getWritableDatabase();
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjj().zzg("Error opening database", sQLiteException);
      throw sQLiteException;
    } 
  }
  
  public final void setTransactionSuccessful() {
    zzcl();
    getWritableDatabase().setTransactionSuccessful();
  }
  
  public final long zza(zzfw paramzzfw) throws IOException {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramzzfw);
    Preconditions.checkNotEmpty(paramzzfw.zztt);
    try {
      long l;
      byte[] arrayOfByte = new byte[paramzzfw.zzvx()];
      zzya zzya = zzya.zzk(arrayOfByte, 0, arrayOfByte.length);
      paramzzfw.zza(zzya);
      zzya.zzza();
      zzft zzft = zzjr();
      Preconditions.checkNotNull(arrayOfByte);
      zzft.zzgr().zzaf();
      MessageDigest messageDigest = zzfx.getMessageDigest();
      if (messageDigest == null) {
        zzft.zzgt().zzjg().zzby("Failed to get MD5");
        l = 0L;
      } else {
        l = zzfx.zzc(messageDigest.digest(arrayOfByte));
      } 
      ContentValues contentValues = new ContentValues();
      contentValues.put("app_id", paramzzfw.zztt);
      contentValues.put("metadata_fingerprint", Long.valueOf(l));
      contentValues.put("metadata", arrayOfByte);
      try {
        getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
        return l;
      } catch (SQLiteException sQLiteException) {
        zzgt().zzjg().zze("Error storing raw event metadata. appId", zzas.zzbw(paramzzfw.zztt), sQLiteException);
        throw sQLiteException;
      } 
    } catch (IOException iOException) {
      zzgt().zzjg().zze("Data loss. Failed to serialize event metadata. appId", zzas.zzbw(paramzzfw.zztt), iOException);
      throw iOException;
    } 
  }
  
  public final Pair<zzft, Long> zza(String paramString, Long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzaf : ()V
    //   4: aload_0
    //   5: invokevirtual zzcl : ()V
    //   8: aload_0
    //   9: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   12: ldc_w 'select main_event, children_to_process from main_event_params where app_id=? and event_id=?'
    //   15: iconst_2
    //   16: anewarray java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: aload_1
    //   22: aastore
    //   23: dup
    //   24: iconst_1
    //   25: aload_2
    //   26: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   29: aastore
    //   30: invokevirtual rawQuery : (Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   33: astore #6
    //   35: aload #6
    //   37: astore #5
    //   39: aload #6
    //   41: invokeinterface moveToFirst : ()Z
    //   46: ifne -> 80
    //   49: aload #6
    //   51: astore #5
    //   53: aload_0
    //   54: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   57: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   60: ldc_w 'Main event not found'
    //   63: invokevirtual zzby : (Ljava/lang/String;)V
    //   66: aload #6
    //   68: ifnull -> 78
    //   71: aload #6
    //   73: invokeinterface close : ()V
    //   78: aconst_null
    //   79: areturn
    //   80: aload #6
    //   82: astore #5
    //   84: aload #6
    //   86: iconst_0
    //   87: invokeinterface getBlob : (I)[B
    //   92: astore #7
    //   94: aload #6
    //   96: astore #5
    //   98: aload #6
    //   100: iconst_1
    //   101: invokeinterface getLong : (I)J
    //   106: lstore_3
    //   107: aload #6
    //   109: astore #5
    //   111: aload #7
    //   113: iconst_0
    //   114: aload #7
    //   116: arraylength
    //   117: invokestatic zzj : ([BII)Lcom/google/android/gms/internal/measurement/zzxz;
    //   120: astore #8
    //   122: aload #6
    //   124: astore #5
    //   126: new com/google/android/gms/internal/measurement/zzft
    //   129: astore #7
    //   131: aload #6
    //   133: astore #5
    //   135: aload #7
    //   137: invokespecial <init> : ()V
    //   140: aload #6
    //   142: astore #5
    //   144: aload #7
    //   146: aload #8
    //   148: invokevirtual zza : (Lcom/google/android/gms/internal/measurement/zzxz;)Lcom/google/android/gms/internal/measurement/zzyi;
    //   151: pop
    //   152: aload #6
    //   154: astore #5
    //   156: aload #7
    //   158: lload_3
    //   159: invokestatic valueOf : (J)Ljava/lang/Long;
    //   162: invokestatic create : (Ljava/lang/Object;Ljava/lang/Object;)Landroid/util/Pair;
    //   165: astore_1
    //   166: aload #6
    //   168: ifnull -> 178
    //   171: aload #6
    //   173: invokeinterface close : ()V
    //   178: aload_1
    //   179: areturn
    //   180: astore #7
    //   182: aload #6
    //   184: astore #5
    //   186: aload_0
    //   187: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   190: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   193: ldc_w 'Failed to merge main event. appId, eventId'
    //   196: aload_1
    //   197: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   200: aload_2
    //   201: aload #7
    //   203: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   206: aload #6
    //   208: ifnull -> 218
    //   211: aload #6
    //   213: invokeinterface close : ()V
    //   218: aconst_null
    //   219: areturn
    //   220: astore_2
    //   221: aload #6
    //   223: astore_1
    //   224: goto -> 237
    //   227: astore_1
    //   228: aconst_null
    //   229: astore #5
    //   231: goto -> 267
    //   234: astore_2
    //   235: aconst_null
    //   236: astore_1
    //   237: aload_1
    //   238: astore #5
    //   240: aload_0
    //   241: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   244: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   247: ldc_w 'Error selecting main event'
    //   250: aload_2
    //   251: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   254: aload_1
    //   255: ifnull -> 264
    //   258: aload_1
    //   259: invokeinterface close : ()V
    //   264: aconst_null
    //   265: areturn
    //   266: astore_1
    //   267: aload #5
    //   269: ifnull -> 279
    //   272: aload #5
    //   274: invokeinterface close : ()V
    //   279: aload_1
    //   280: athrow
    // Exception table:
    //   from	to	target	type
    //   8	35	234	android/database/sqlite/SQLiteException
    //   8	35	227	finally
    //   39	49	220	android/database/sqlite/SQLiteException
    //   39	49	266	finally
    //   53	66	220	android/database/sqlite/SQLiteException
    //   53	66	266	finally
    //   84	94	220	android/database/sqlite/SQLiteException
    //   84	94	266	finally
    //   98	107	220	android/database/sqlite/SQLiteException
    //   98	107	266	finally
    //   111	122	220	android/database/sqlite/SQLiteException
    //   111	122	266	finally
    //   126	131	220	android/database/sqlite/SQLiteException
    //   126	131	266	finally
    //   135	140	220	android/database/sqlite/SQLiteException
    //   135	140	266	finally
    //   144	152	180	java/io/IOException
    //   144	152	220	android/database/sqlite/SQLiteException
    //   144	152	266	finally
    //   156	166	220	android/database/sqlite/SQLiteException
    //   156	166	266	finally
    //   186	206	220	android/database/sqlite/SQLiteException
    //   186	206	266	finally
    //   240	254	266	finally
  }
  
  public final zzu zza(long paramLong, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5) {
    Preconditions.checkNotEmpty(paramString);
    zzaf();
    zzcl();
    zzu zzu = new zzu();
    Cursor cursor3 = null;
    Cursor cursor4 = null;
    Cursor cursor1 = cursor4;
    Cursor cursor2 = cursor3;
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      cursor1 = cursor4;
      cursor2 = cursor3;
      cursor3 = sQLiteDatabase.query("apps", new String[] { "day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count" }, "app_id=?", new String[] { paramString }, null, null, null);
      cursor1 = cursor3;
      cursor2 = cursor3;
      if (!cursor3.moveToFirst()) {
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzgt().zzjj().zzg("Not updating daily counts, app is not known. appId", zzas.zzbw(paramString));
        if (cursor3 != null)
          cursor3.close(); 
        return zzu;
      } 
      cursor1 = cursor3;
      cursor2 = cursor3;
      if (cursor3.getLong(0) == paramLong) {
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahi = cursor3.getLong(1);
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahh = cursor3.getLong(2);
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahj = cursor3.getLong(3);
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahk = cursor3.getLong(4);
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahl = cursor3.getLong(5);
      } 
      if (paramBoolean1) {
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahi++;
      } 
      if (paramBoolean2) {
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahh++;
      } 
      if (paramBoolean3) {
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahj++;
      } 
      if (paramBoolean4) {
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahk++;
      } 
      if (paramBoolean5) {
        cursor1 = cursor3;
        cursor2 = cursor3;
        zzu.zzahl++;
      } 
      cursor1 = cursor3;
      cursor2 = cursor3;
      ContentValues contentValues = new ContentValues();
      cursor1 = cursor3;
      cursor2 = cursor3;
      this();
      cursor1 = cursor3;
      cursor2 = cursor3;
      contentValues.put("day", Long.valueOf(paramLong));
      cursor1 = cursor3;
      cursor2 = cursor3;
      contentValues.put("daily_public_events_count", Long.valueOf(zzu.zzahh));
      cursor1 = cursor3;
      cursor2 = cursor3;
      contentValues.put("daily_events_count", Long.valueOf(zzu.zzahi));
      cursor1 = cursor3;
      cursor2 = cursor3;
      contentValues.put("daily_conversions_count", Long.valueOf(zzu.zzahj));
      cursor1 = cursor3;
      cursor2 = cursor3;
      contentValues.put("daily_error_events_count", Long.valueOf(zzu.zzahk));
      cursor1 = cursor3;
      cursor2 = cursor3;
      contentValues.put("daily_realtime_events_count", Long.valueOf(zzu.zzahl));
      cursor1 = cursor3;
      cursor2 = cursor3;
      sQLiteDatabase.update("apps", contentValues, "app_id=?", new String[] { paramString });
      if (cursor3 != null)
        cursor3.close(); 
      return zzu;
    } catch (SQLiteException sQLiteException) {
      cursor1 = cursor2;
      zzgt().zzjg().zze("Error updating daily counts. appId", zzas.zzbw(paramString), sQLiteException);
      if (cursor2 != null)
        cursor2.close(); 
      return zzu;
    } finally {}
    if (cursor1 != null)
      cursor1.close(); 
    throw paramString;
  }
  
  public final void zza(zzac paramzzac) {
    Preconditions.checkNotNull(paramzzac);
    zzaf();
    zzcl();
    ContentValues contentValues = new ContentValues();
    contentValues.put("app_id", paramzzac.zztt);
    contentValues.put("name", paramzzac.name);
    contentValues.put("lifetime_count", Long.valueOf(paramzzac.zzahv));
    contentValues.put("current_bundle_count", Long.valueOf(paramzzac.zzahw));
    contentValues.put("last_fire_timestamp", Long.valueOf(paramzzac.zzahx));
    contentValues.put("last_bundled_timestamp", Long.valueOf(paramzzac.zzahy));
    contentValues.put("last_bundled_day", paramzzac.zzahz);
    contentValues.put("last_sampled_complex_event_id", paramzzac.zzaia);
    contentValues.put("last_sampling_rate", paramzzac.zzaib);
    Boolean bool = paramzzac.zzaic;
    if (bool != null && bool.booleanValue()) {
      Long long_ = Long.valueOf(1L);
    } else {
      bool = null;
    } 
    contentValues.put("last_exempt_from_sampling", (Long)bool);
    try {
      if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1L)
        zzgt().zzjg().zzg("Failed to insert/update event aggregates (got -1). appId", zzas.zzbw(paramzzac.zztt)); 
      return;
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zze("Error storing event aggregates. appId", zzas.zzbw(paramzzac.zztt), sQLiteException);
      return;
    } 
  }
  
  public final void zza(zzg paramzzg) {
    Preconditions.checkNotNull(paramzzg);
    zzaf();
    zzcl();
    ContentValues contentValues = new ContentValues();
    contentValues.put("app_id", paramzzg.zzal());
    contentValues.put("app_instance_id", paramzzg.getAppInstanceId());
    contentValues.put("gmp_app_id", paramzzg.getGmpAppId());
    contentValues.put("resettable_device_id_hash", paramzzg.zzhc());
    contentValues.put("last_bundle_index", Long.valueOf(paramzzg.zzhj()));
    contentValues.put("last_bundle_start_timestamp", Long.valueOf(paramzzg.zzhd()));
    contentValues.put("last_bundle_end_timestamp", Long.valueOf(paramzzg.zzhe()));
    contentValues.put("app_version", paramzzg.zzak());
    contentValues.put("app_store", paramzzg.zzhg());
    contentValues.put("gmp_version", Long.valueOf(paramzzg.zzhh()));
    contentValues.put("dev_cert_hash", Long.valueOf(paramzzg.zzhi()));
    contentValues.put("measurement_enabled", Boolean.valueOf(paramzzg.isMeasurementEnabled()));
    contentValues.put("day", Long.valueOf(paramzzg.zzhn()));
    contentValues.put("daily_public_events_count", Long.valueOf(paramzzg.zzho()));
    contentValues.put("daily_events_count", Long.valueOf(paramzzg.zzhp()));
    contentValues.put("daily_conversions_count", Long.valueOf(paramzzg.zzhq()));
    contentValues.put("config_fetched_time", Long.valueOf(paramzzg.zzhk()));
    contentValues.put("failed_config_fetch_time", Long.valueOf(paramzzg.zzhl()));
    contentValues.put("app_version_int", Long.valueOf(paramzzg.zzhf()));
    contentValues.put("firebase_instance_id", paramzzg.getFirebaseInstanceId());
    contentValues.put("daily_error_events_count", Long.valueOf(paramzzg.zzhs()));
    contentValues.put("daily_realtime_events_count", Long.valueOf(paramzzg.zzhr()));
    contentValues.put("health_monitor_sample", paramzzg.zzht());
    contentValues.put("android_id", Long.valueOf(paramzzg.zzhv()));
    contentValues.put("adid_reporting_enabled", Boolean.valueOf(paramzzg.zzhw()));
    contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(paramzzg.zzhx()));
    contentValues.put("admob_app_id", paramzzg.zzhb());
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      if (sQLiteDatabase.update("apps", contentValues, "app_id = ?", new String[] { paramzzg.zzal() }) == 0L && sQLiteDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1L)
        zzgt().zzjg().zzg("Failed to insert/update app (got -1). appId", zzas.zzbw(paramzzg.zzal())); 
      return;
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zze("Error storing app. appId", zzas.zzbw(paramzzg.zzal()), sQLiteException);
      return;
    } 
  }
  
  final void zza(String paramString, zzfi[] paramArrayOfzzfi) {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramArrayOfzzfi);
    SQLiteDatabase sQLiteDatabase = getWritableDatabase();
    sQLiteDatabase.beginTransaction();
    try {
      zzcl();
      zzaf();
      Preconditions.checkNotEmpty(paramString);
      SQLiteDatabase sQLiteDatabase1 = getWritableDatabase();
      byte b2 = 0;
      sQLiteDatabase1.delete("property_filters", "app_id=?", new String[] { paramString });
      sQLiteDatabase1.delete("event_filters", "app_id=?", new String[] { paramString });
      int j = paramArrayOfzzfi.length;
      int i;
      label61: for (i = 0; i < j; i++) {
        zzfi zzfi1 = paramArrayOfzzfi[i];
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(paramString);
        Preconditions.checkNotNull(zzfi1);
        Preconditions.checkNotNull(zzfi1.zzavi);
        Preconditions.checkNotNull(zzfi1.zzavh);
        if (zzfi1.zzavg == null) {
          zzgt().zzjj().zzg("Audience with no ID. appId", zzas.zzbw(paramString));
        } else {
          int m = zzfi1.zzavg.intValue();
          zzfj[] arrayOfZzfj2 = zzfi1.zzavi;
          int k = arrayOfZzfj2.length;
          byte b3;
          for (b3 = 0; b3 < k; b3++) {
            if ((arrayOfZzfj2[b3]).zzavm == null) {
              zzgt().zzjj().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzas.zzbw(paramString), zzfi1.zzavg);
              continue label61;
            } 
          } 
          zzfm[] arrayOfZzfm = zzfi1.zzavh;
          k = arrayOfZzfm.length;
          for (b3 = 0; b3 < k; b3++) {
            if ((arrayOfZzfm[b3]).zzavm == null) {
              zzgt().zzjj().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzas.zzbw(paramString), zzfi1.zzavg);
              continue label61;
            } 
          } 
          zzfj[] arrayOfZzfj1 = zzfi1.zzavi;
          k = arrayOfZzfj1.length;
          b3 = 0;
          while (true) {
            if (b3 < k) {
              if (!zza(paramString, m, arrayOfZzfj1[b3])) {
                b3 = 0;
                break;
              } 
              b3++;
              continue;
            } 
            b3 = 1;
            break;
          } 
          byte b4 = b3;
          if (b3 != 0) {
            zzfm[] arrayOfZzfm1 = zzfi1.zzavh;
            int n = arrayOfZzfm1.length;
            k = 0;
            while (true) {
              b4 = b3;
              if (k < n) {
                if (!zza(paramString, m, arrayOfZzfm1[k])) {
                  b4 = 0;
                  break;
                } 
                k++;
                continue;
              } 
              break;
            } 
          } 
          if (b4 == 0) {
            zzcl();
            zzaf();
            Preconditions.checkNotEmpty(paramString);
            SQLiteDatabase sQLiteDatabase2 = getWritableDatabase();
            sQLiteDatabase2.delete("property_filters", "app_id=? and audience_id=?", new String[] { paramString, String.valueOf(m) });
            sQLiteDatabase2.delete("event_filters", "app_id=? and audience_id=?", new String[] { paramString, String.valueOf(m) });
          } 
        } 
      } 
      ArrayList<Integer> arrayList = new ArrayList();
      this();
      i = paramArrayOfzzfi.length;
      for (byte b1 = b2; b1 < i; b1++)
        arrayList.add((paramArrayOfzzfi[b1]).zzavg); 
      zza(paramString, arrayList);
      sQLiteDatabase.setTransactionSuccessful();
      return;
    } finally {
      sQLiteDatabase.endTransaction();
    } 
  }
  
  public final boolean zza(zzfw paramzzfw, boolean paramBoolean) {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramzzfw);
    Preconditions.checkNotEmpty(paramzzfw.zztt);
    Preconditions.checkNotNull(paramzzfw.zzaxo);
    zzij();
    long l = zzbx().currentTimeMillis();
    if (paramzzfw.zzaxo.longValue() < l - zzq.zzib() || paramzzfw.zzaxo.longValue() > zzq.zzib() + l)
      zzgt().zzjj().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzas.zzbw(paramzzfw.zztt), Long.valueOf(l), paramzzfw.zzaxo); 
    try {
      byte[] arrayOfByte1 = new byte[paramzzfw.zzvx()];
      zzya zzya = zzya.zzk(arrayOfByte1, 0, arrayOfByte1.length);
      paramzzfw.zza(zzya);
      zzya.zzza();
      byte[] arrayOfByte2 = zzjr().zzb(arrayOfByte1);
      zzgt().zzjo().zzg("Saving bundle, size", Integer.valueOf(arrayOfByte2.length));
      ContentValues contentValues = new ContentValues();
      contentValues.put("app_id", paramzzfw.zztt);
      contentValues.put("bundle_end_timestamp", paramzzfw.zzaxo);
      contentValues.put("data", arrayOfByte2);
      contentValues.put("has_realtime", Integer.valueOf(paramBoolean));
      Integer integer = paramzzfw.zzayl;
      if (integer != null)
        contentValues.put("retry_count", integer); 
      try {
        if (getWritableDatabase().insert("queue", null, contentValues) == -1L) {
          zzgt().zzjg().zzg("Failed to insert bundle (got -1). appId", zzas.zzbw(paramzzfw.zztt));
          return false;
        } 
        return true;
      } catch (SQLiteException sQLiteException) {
        zzgt().zzjg().zze("Error storing bundle. appId", zzas.zzbw(paramzzfw.zztt), sQLiteException);
        return false;
      } 
    } catch (IOException iOException) {
      zzgt().zzjg().zze("Data loss. Failed to serialize bundle. appId", zzas.zzbw(paramzzfw.zztt), iOException);
      return false;
    } 
  }
  
  public final boolean zza(zzab paramzzab, long paramLong, boolean paramBoolean) {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramzzab);
    Preconditions.checkNotEmpty(paramzzab.zztt);
    zzft zzft = new zzft();
    zzft.zzaxe = Long.valueOf(paramzzab.zzaht);
    zzft.zzaxc = new zzfu[paramzzab.zzahu.size()];
    Iterator<String> iterator = paramzzab.zzahu.iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      String str = iterator.next();
      zzfu zzfu = new zzfu();
      zzft.zzaxc[b] = zzfu;
      zzfu.name = str;
      Object object = paramzzab.zzahu.get(str);
      zzjr().zza(zzfu, object);
    } 
    try {
      byte[] arrayOfByte = new byte[zzft.zzvx()];
      zzya zzya = zzya.zzk(arrayOfByte, 0, arrayOfByte.length);
      zzft.zza(zzya);
      zzya.zzza();
      zzgt().zzjo().zze("Saving event, name, data size", zzgq().zzbt(paramzzab.name), Integer.valueOf(arrayOfByte.length));
      ContentValues contentValues = new ContentValues();
      contentValues.put("app_id", paramzzab.zztt);
      contentValues.put("name", paramzzab.name);
      contentValues.put("timestamp", Long.valueOf(paramzzab.timestamp));
      contentValues.put("metadata_fingerprint", Long.valueOf(paramLong));
      contentValues.put("data", arrayOfByte);
      contentValues.put("realtime", Integer.valueOf(paramBoolean));
      try {
        if (getWritableDatabase().insert("raw_events", null, contentValues) == -1L) {
          zzgt().zzjg().zzg("Failed to insert raw event (got -1). appId", zzas.zzbw(paramzzab.zztt));
          return false;
        } 
        return true;
      } catch (SQLiteException sQLiteException) {
        zzgt().zzjg().zze("Error storing raw event. appId", zzas.zzbw(paramzzab.zztt), sQLiteException);
        return false;
      } 
    } catch (IOException iOException) {
      zzgt().zzjg().zze("Data loss. Failed to serialize event params/data. appId", zzas.zzbw(paramzzab.zztt), iOException);
      return false;
    } 
  }
  
  public final boolean zza(zzfw paramzzfw) {
    Preconditions.checkNotNull(paramzzfw);
    zzaf();
    zzcl();
    if (zzi(paramzzfw.zztt, paramzzfw.name) == null)
      if (zzfx.zzct(paramzzfw.name)) {
        if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[] { paramzzfw.zztt }) >= 25L)
          return false; 
      } else if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[] { paramzzfw.zztt, paramzzfw.origin }) >= 25L) {
        return false;
      }  
    ContentValues contentValues = new ContentValues();
    contentValues.put("app_id", paramzzfw.zztt);
    contentValues.put("origin", paramzzfw.origin);
    contentValues.put("name", paramzzfw.name);
    contentValues.put("set_timestamp", Long.valueOf(paramzzfw.zzaum));
    zza(contentValues, "value", paramzzfw.value);
    try {
      if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1L)
        zzgt().zzjg().zzg("Failed to insert/update user property (got -1). appId", zzas.zzbw(paramzzfw.zztt)); 
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zze("Error storing user property. appId", zzas.zzbw(paramzzfw.zztt), sQLiteException);
    } 
    return true;
  }
  
  public final boolean zza(zzo paramzzo) {
    Preconditions.checkNotNull(paramzzo);
    zzaf();
    zzcl();
    if (zzi(paramzzo.packageName, paramzzo.zzags.name) == null && zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[] { paramzzo.packageName }) >= 1000L)
      return false; 
    ContentValues contentValues = new ContentValues();
    contentValues.put("app_id", paramzzo.packageName);
    contentValues.put("origin", paramzzo.origin);
    contentValues.put("name", paramzzo.zzags.name);
    zza(contentValues, "value", paramzzo.zzags.getValue());
    contentValues.put("active", Boolean.valueOf(paramzzo.active));
    contentValues.put("trigger_event_name", paramzzo.triggerEventName);
    contentValues.put("trigger_timeout", Long.valueOf(paramzzo.triggerTimeout));
    zzgr();
    contentValues.put("timed_out_event", zzfx.zza((Parcelable)paramzzo.zzagt));
    contentValues.put("creation_timestamp", Long.valueOf(paramzzo.creationTimestamp));
    zzgr();
    contentValues.put("triggered_event", zzfx.zza((Parcelable)paramzzo.zzagu));
    contentValues.put("triggered_timestamp", Long.valueOf(paramzzo.zzags.zzaum));
    contentValues.put("time_to_live", Long.valueOf(paramzzo.timeToLive));
    zzgr();
    contentValues.put("expired_event", zzfx.zza((Parcelable)paramzzo.zzagv));
    try {
      if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1L)
        zzgt().zzjg().zzg("Failed to insert/update conditional user property (got -1)", zzas.zzbw(paramzzo.packageName)); 
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zze("Error storing conditional user property", zzas.zzbw(paramzzo.packageName), sQLiteException);
    } 
    return true;
  }
  
  public final boolean zza(String paramString, Long paramLong, long paramLong1, zzft paramzzft) {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramzzft);
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramLong);
    try {
      byte[] arrayOfByte = new byte[paramzzft.zzvx()];
      zzya zzya = zzya.zzk(arrayOfByte, 0, arrayOfByte.length);
      paramzzft.zza(zzya);
      zzya.zzza();
      zzgt().zzjo().zze("Saving complex main event, appId, data size", zzgq().zzbt(paramString), Integer.valueOf(arrayOfByte.length));
      ContentValues contentValues = new ContentValues();
      contentValues.put("app_id", paramString);
      contentValues.put("event_id", paramLong);
      contentValues.put("children_to_process", Long.valueOf(paramLong1));
      contentValues.put("main_event", arrayOfByte);
      try {
        if (getWritableDatabase().insertWithOnConflict("main_event_params", null, contentValues, 5) == -1L) {
          zzgt().zzjg().zzg("Failed to insert complex main event (got -1). appId", zzas.zzbw(paramString));
          return false;
        } 
        return true;
      } catch (SQLiteException sQLiteException) {
        zzgt().zzjg().zze("Error storing complex main event. appId", zzas.zzbw(paramString), sQLiteException);
        return false;
      } 
    } catch (IOException iOException) {
      zzgt().zzjg().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzas.zzbw(paramString), sQLiteException, iOException);
      return false;
    } 
  }
  
  public final String zzad(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzaf : ()V
    //   4: aload_0
    //   5: invokevirtual zzcl : ()V
    //   8: aload_0
    //   9: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   12: ldc_w 'select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;'
    //   15: iconst_1
    //   16: anewarray java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: lload_1
    //   22: invokestatic valueOf : (J)Ljava/lang/String;
    //   25: aastore
    //   26: invokevirtual rawQuery : (Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   29: astore #4
    //   31: aload #4
    //   33: astore_3
    //   34: aload #4
    //   36: invokeinterface moveToFirst : ()Z
    //   41: ifne -> 74
    //   44: aload #4
    //   46: astore_3
    //   47: aload_0
    //   48: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   51: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   54: ldc_w 'No expired configs for apps with pending events'
    //   57: invokevirtual zzby : (Ljava/lang/String;)V
    //   60: aload #4
    //   62: ifnull -> 72
    //   65: aload #4
    //   67: invokeinterface close : ()V
    //   72: aconst_null
    //   73: areturn
    //   74: aload #4
    //   76: astore_3
    //   77: aload #4
    //   79: iconst_0
    //   80: invokeinterface getString : (I)Ljava/lang/String;
    //   85: astore #5
    //   87: aload #4
    //   89: ifnull -> 99
    //   92: aload #4
    //   94: invokeinterface close : ()V
    //   99: aload #5
    //   101: areturn
    //   102: astore #5
    //   104: goto -> 119
    //   107: astore #4
    //   109: aconst_null
    //   110: astore_3
    //   111: goto -> 153
    //   114: astore #5
    //   116: aconst_null
    //   117: astore #4
    //   119: aload #4
    //   121: astore_3
    //   122: aload_0
    //   123: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   126: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   129: ldc_w 'Error selecting expired configs'
    //   132: aload #5
    //   134: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   137: aload #4
    //   139: ifnull -> 149
    //   142: aload #4
    //   144: invokeinterface close : ()V
    //   149: aconst_null
    //   150: areturn
    //   151: astore #4
    //   153: aload_3
    //   154: ifnull -> 163
    //   157: aload_3
    //   158: invokeinterface close : ()V
    //   163: aload #4
    //   165: athrow
    // Exception table:
    //   from	to	target	type
    //   8	31	114	android/database/sqlite/SQLiteException
    //   8	31	107	finally
    //   34	44	102	android/database/sqlite/SQLiteException
    //   34	44	151	finally
    //   47	60	102	android/database/sqlite/SQLiteException
    //   47	60	151	finally
    //   77	87	102	android/database/sqlite/SQLiteException
    //   77	87	151	finally
    //   122	137	151	finally
  }
  
  public final List<Pair<zzfw, Long>> zzb(String paramString, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzaf : ()V
    //   4: aload_0
    //   5: invokevirtual zzcl : ()V
    //   8: iload_2
    //   9: ifle -> 18
    //   12: iconst_1
    //   13: istore #5
    //   15: goto -> 21
    //   18: iconst_0
    //   19: istore #5
    //   21: iload #5
    //   23: invokestatic checkArgument : (Z)V
    //   26: iload_3
    //   27: ifle -> 36
    //   30: iconst_1
    //   31: istore #5
    //   33: goto -> 39
    //   36: iconst_0
    //   37: istore #5
    //   39: iload #5
    //   41: invokestatic checkArgument : (Z)V
    //   44: aload_1
    //   45: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   48: pop
    //   49: aconst_null
    //   50: astore #9
    //   52: aconst_null
    //   53: astore #8
    //   55: aload_0
    //   56: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   59: ldc_w 'queue'
    //   62: iconst_3
    //   63: anewarray java/lang/String
    //   66: dup
    //   67: iconst_0
    //   68: ldc_w 'rowid'
    //   71: aastore
    //   72: dup
    //   73: iconst_1
    //   74: ldc_w 'data'
    //   77: aastore
    //   78: dup
    //   79: iconst_2
    //   80: ldc 'retry_count'
    //   82: aastore
    //   83: ldc_w 'app_id=?'
    //   86: iconst_1
    //   87: anewarray java/lang/String
    //   90: dup
    //   91: iconst_0
    //   92: aload_1
    //   93: aastore
    //   94: aconst_null
    //   95: aconst_null
    //   96: ldc_w 'rowid'
    //   99: iload_2
    //   100: invokestatic valueOf : (I)Ljava/lang/String;
    //   103: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   106: astore #10
    //   108: aload #10
    //   110: astore #8
    //   112: aload #10
    //   114: astore #9
    //   116: aload #10
    //   118: invokeinterface moveToFirst : ()Z
    //   123: ifne -> 154
    //   126: aload #10
    //   128: astore #8
    //   130: aload #10
    //   132: astore #9
    //   134: invokestatic emptyList : ()Ljava/util/List;
    //   137: astore #11
    //   139: aload #10
    //   141: ifnull -> 151
    //   144: aload #10
    //   146: invokeinterface close : ()V
    //   151: aload #11
    //   153: areturn
    //   154: aload #10
    //   156: astore #8
    //   158: aload #10
    //   160: astore #9
    //   162: new java/util/ArrayList
    //   165: astore #11
    //   167: aload #10
    //   169: astore #8
    //   171: aload #10
    //   173: astore #9
    //   175: aload #11
    //   177: invokespecial <init> : ()V
    //   180: iconst_0
    //   181: istore_2
    //   182: aload #10
    //   184: astore #8
    //   186: aload #10
    //   188: astore #9
    //   190: aload #10
    //   192: iconst_0
    //   193: invokeinterface getLong : (I)J
    //   198: lstore #6
    //   200: aload #10
    //   202: astore #8
    //   204: aload #10
    //   206: astore #9
    //   208: aload #10
    //   210: iconst_1
    //   211: invokeinterface getBlob : (I)[B
    //   216: astore #12
    //   218: aload #10
    //   220: astore #8
    //   222: aload #10
    //   224: astore #9
    //   226: aload_0
    //   227: invokevirtual zzjr : ()Lcom/google/android/gms/measurement/internal/zzft;
    //   230: aload #12
    //   232: invokevirtual zza : ([B)[B
    //   235: astore #14
    //   237: aload #10
    //   239: astore #8
    //   241: aload #10
    //   243: astore #9
    //   245: aload #11
    //   247: invokeinterface isEmpty : ()Z
    //   252: ifne -> 272
    //   255: aload #10
    //   257: astore #8
    //   259: aload #10
    //   261: astore #9
    //   263: aload #14
    //   265: arraylength
    //   266: iload_2
    //   267: iadd
    //   268: iload_3
    //   269: if_icmpgt -> 518
    //   272: aload #10
    //   274: astore #8
    //   276: aload #10
    //   278: astore #9
    //   280: aload #14
    //   282: iconst_0
    //   283: aload #14
    //   285: arraylength
    //   286: invokestatic zzj : ([BII)Lcom/google/android/gms/internal/measurement/zzxz;
    //   289: astore #12
    //   291: aload #10
    //   293: astore #8
    //   295: aload #10
    //   297: astore #9
    //   299: new com/google/android/gms/internal/measurement/zzfw
    //   302: astore #13
    //   304: aload #10
    //   306: astore #8
    //   308: aload #10
    //   310: astore #9
    //   312: aload #13
    //   314: invokespecial <init> : ()V
    //   317: aload #10
    //   319: astore #8
    //   321: aload #10
    //   323: astore #9
    //   325: aload #13
    //   327: aload #12
    //   329: invokevirtual zza : (Lcom/google/android/gms/internal/measurement/zzxz;)Lcom/google/android/gms/internal/measurement/zzyi;
    //   332: pop
    //   333: aload #10
    //   335: astore #8
    //   337: aload #10
    //   339: astore #9
    //   341: aload #10
    //   343: iconst_2
    //   344: invokeinterface isNull : (I)Z
    //   349: ifne -> 376
    //   352: aload #10
    //   354: astore #8
    //   356: aload #10
    //   358: astore #9
    //   360: aload #13
    //   362: aload #10
    //   364: iconst_2
    //   365: invokeinterface getInt : (I)I
    //   370: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   373: putfield zzayl : Ljava/lang/Integer;
    //   376: aload #10
    //   378: astore #8
    //   380: aload #10
    //   382: astore #9
    //   384: iload_2
    //   385: aload #14
    //   387: arraylength
    //   388: iadd
    //   389: istore #4
    //   391: aload #10
    //   393: astore #8
    //   395: aload #10
    //   397: astore #9
    //   399: aload #11
    //   401: aload #13
    //   403: lload #6
    //   405: invokestatic valueOf : (J)Ljava/lang/Long;
    //   408: invokestatic create : (Ljava/lang/Object;Ljava/lang/Object;)Landroid/util/Pair;
    //   411: invokeinterface add : (Ljava/lang/Object;)Z
    //   416: pop
    //   417: goto -> 487
    //   420: astore #12
    //   422: aload #10
    //   424: astore #8
    //   426: aload #10
    //   428: astore #9
    //   430: aload_0
    //   431: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   434: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   437: ldc_w 'Failed to merge queued bundle. appId'
    //   440: aload_1
    //   441: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   444: aload #12
    //   446: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   449: iload_2
    //   450: istore #4
    //   452: goto -> 487
    //   455: astore #12
    //   457: aload #10
    //   459: astore #8
    //   461: aload #10
    //   463: astore #9
    //   465: aload_0
    //   466: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   469: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   472: ldc_w 'Failed to unzip queued bundle. appId'
    //   475: aload_1
    //   476: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   479: aload #12
    //   481: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   484: iload_2
    //   485: istore #4
    //   487: aload #10
    //   489: astore #8
    //   491: aload #10
    //   493: astore #9
    //   495: aload #10
    //   497: invokeinterface moveToNext : ()Z
    //   502: istore #5
    //   504: iload #5
    //   506: ifeq -> 518
    //   509: iload #4
    //   511: istore_2
    //   512: iload #4
    //   514: iload_3
    //   515: if_icmple -> 182
    //   518: aload #10
    //   520: ifnull -> 530
    //   523: aload #10
    //   525: invokeinterface close : ()V
    //   530: aload #11
    //   532: areturn
    //   533: astore_1
    //   534: goto -> 584
    //   537: astore #10
    //   539: aload #9
    //   541: astore #8
    //   543: aload_0
    //   544: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   547: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   550: ldc_w 'Error querying bundles. appId'
    //   553: aload_1
    //   554: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   557: aload #10
    //   559: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   562: aload #9
    //   564: astore #8
    //   566: invokestatic emptyList : ()Ljava/util/List;
    //   569: astore_1
    //   570: aload #9
    //   572: ifnull -> 582
    //   575: aload #9
    //   577: invokeinterface close : ()V
    //   582: aload_1
    //   583: areturn
    //   584: aload #8
    //   586: ifnull -> 596
    //   589: aload #8
    //   591: invokeinterface close : ()V
    //   596: aload_1
    //   597: athrow
    // Exception table:
    //   from	to	target	type
    //   55	108	537	android/database/sqlite/SQLiteException
    //   55	108	533	finally
    //   116	126	537	android/database/sqlite/SQLiteException
    //   116	126	533	finally
    //   134	139	537	android/database/sqlite/SQLiteException
    //   134	139	533	finally
    //   162	167	537	android/database/sqlite/SQLiteException
    //   162	167	533	finally
    //   175	180	537	android/database/sqlite/SQLiteException
    //   175	180	533	finally
    //   190	200	537	android/database/sqlite/SQLiteException
    //   190	200	533	finally
    //   208	218	455	java/io/IOException
    //   208	218	537	android/database/sqlite/SQLiteException
    //   208	218	533	finally
    //   226	237	455	java/io/IOException
    //   226	237	537	android/database/sqlite/SQLiteException
    //   226	237	533	finally
    //   245	255	537	android/database/sqlite/SQLiteException
    //   245	255	533	finally
    //   263	272	537	android/database/sqlite/SQLiteException
    //   263	272	533	finally
    //   280	291	537	android/database/sqlite/SQLiteException
    //   280	291	533	finally
    //   299	304	537	android/database/sqlite/SQLiteException
    //   299	304	533	finally
    //   312	317	537	android/database/sqlite/SQLiteException
    //   312	317	533	finally
    //   325	333	420	java/io/IOException
    //   325	333	537	android/database/sqlite/SQLiteException
    //   325	333	533	finally
    //   341	352	537	android/database/sqlite/SQLiteException
    //   341	352	533	finally
    //   360	376	537	android/database/sqlite/SQLiteException
    //   360	376	533	finally
    //   384	391	537	android/database/sqlite/SQLiteException
    //   384	391	533	finally
    //   399	417	537	android/database/sqlite/SQLiteException
    //   399	417	533	finally
    //   430	449	537	android/database/sqlite/SQLiteException
    //   430	449	533	finally
    //   465	484	537	android/database/sqlite/SQLiteException
    //   465	484	533	finally
    //   495	504	537	android/database/sqlite/SQLiteException
    //   495	504	533	finally
    //   543	562	533	finally
    //   566	570	533	finally
  }
  
  public final List<zzfw> zzb(String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual zzaf : ()V
    //   9: aload_0
    //   10: invokevirtual zzcl : ()V
    //   13: new java/util/ArrayList
    //   16: dup
    //   17: invokespecial <init> : ()V
    //   20: astore #10
    //   22: aconst_null
    //   23: astore #9
    //   25: new java/util/ArrayList
    //   28: astore #11
    //   30: aload #11
    //   32: iconst_3
    //   33: invokespecial <init> : (I)V
    //   36: aload #11
    //   38: aload_1
    //   39: invokeinterface add : (Ljava/lang/Object;)Z
    //   44: pop
    //   45: new java/lang/StringBuilder
    //   48: astore #7
    //   50: aload #7
    //   52: ldc_w 'app_id=?'
    //   55: invokespecial <init> : (Ljava/lang/String;)V
    //   58: aload_2
    //   59: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   62: istore #4
    //   64: iload #4
    //   66: ifne -> 90
    //   69: aload #11
    //   71: aload_2
    //   72: invokeinterface add : (Ljava/lang/Object;)Z
    //   77: pop
    //   78: aload #7
    //   80: ldc_w ' and origin=?'
    //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: goto -> 90
    //   90: aload_2
    //   91: astore #8
    //   93: aload_3
    //   94: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   97: ifne -> 127
    //   100: aload #11
    //   102: aload_3
    //   103: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   106: ldc_w '*'
    //   109: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   112: invokeinterface add : (Ljava/lang/Object;)Z
    //   117: pop
    //   118: aload #7
    //   120: ldc_w ' and name glob ?'
    //   123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: pop
    //   127: aload #11
    //   129: aload #11
    //   131: invokeinterface size : ()I
    //   136: anewarray java/lang/String
    //   139: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   144: checkcast [Ljava/lang/String;
    //   147: astore #11
    //   149: aload_0
    //   150: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   153: astore #12
    //   155: aload #7
    //   157: invokevirtual toString : ()Ljava/lang/String;
    //   160: astore #7
    //   162: aload #12
    //   164: ldc_w 'user_attributes'
    //   167: iconst_4
    //   168: anewarray java/lang/String
    //   171: dup
    //   172: iconst_0
    //   173: ldc_w 'name'
    //   176: aastore
    //   177: dup
    //   178: iconst_1
    //   179: ldc_w 'set_timestamp'
    //   182: aastore
    //   183: dup
    //   184: iconst_2
    //   185: ldc_w 'value'
    //   188: aastore
    //   189: dup
    //   190: iconst_3
    //   191: ldc 'origin'
    //   193: aastore
    //   194: aload #7
    //   196: aload #11
    //   198: aconst_null
    //   199: aconst_null
    //   200: ldc_w 'rowid'
    //   203: ldc_w '1001'
    //   206: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   209: astore #7
    //   211: aload #8
    //   213: astore #9
    //   215: aload #7
    //   217: invokeinterface moveToFirst : ()Z
    //   222: istore #4
    //   224: aload #8
    //   226: astore_2
    //   227: iload #4
    //   229: ifne -> 247
    //   232: aload #7
    //   234: ifnull -> 244
    //   237: aload #7
    //   239: invokeinterface close : ()V
    //   244: aload #10
    //   246: areturn
    //   247: aload_2
    //   248: astore #9
    //   250: aload #10
    //   252: invokeinterface size : ()I
    //   257: sipush #1000
    //   260: if_icmplt -> 288
    //   263: aload_2
    //   264: astore #9
    //   266: aload_0
    //   267: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   270: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   273: ldc_w 'Read more than the max allowed user properties, ignoring excess'
    //   276: sipush #1000
    //   279: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   282: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   285: goto -> 429
    //   288: aload_2
    //   289: astore #9
    //   291: aload #7
    //   293: iconst_0
    //   294: invokeinterface getString : (I)Ljava/lang/String;
    //   299: astore #11
    //   301: aload_2
    //   302: astore #9
    //   304: aload #7
    //   306: iconst_1
    //   307: invokeinterface getLong : (I)J
    //   312: lstore #5
    //   314: aload #7
    //   316: astore #8
    //   318: aload_0
    //   319: aload #7
    //   321: iconst_2
    //   322: invokespecial zza : (Landroid/database/Cursor;I)Ljava/lang/Object;
    //   325: astore #12
    //   327: aload #7
    //   329: astore #8
    //   331: aload #7
    //   333: iconst_3
    //   334: invokeinterface getString : (I)Ljava/lang/String;
    //   339: astore #9
    //   341: aload #12
    //   343: ifnonnull -> 373
    //   346: aload #7
    //   348: astore #8
    //   350: aload_0
    //   351: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   354: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   357: ldc_w '(2)Read invalid user property value, ignoring it'
    //   360: aload_1
    //   361: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   364: aload #9
    //   366: aload_3
    //   367: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   370: goto -> 411
    //   373: aload #7
    //   375: astore #8
    //   377: new com/google/android/gms/measurement/internal/zzfw
    //   380: astore_2
    //   381: aload #7
    //   383: astore #8
    //   385: aload_2
    //   386: aload_1
    //   387: aload #9
    //   389: aload #11
    //   391: lload #5
    //   393: aload #12
    //   395: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   398: aload #7
    //   400: astore #8
    //   402: aload #10
    //   404: aload_2
    //   405: invokeinterface add : (Ljava/lang/Object;)Z
    //   410: pop
    //   411: aload #7
    //   413: astore #8
    //   415: aload #7
    //   417: invokeinterface moveToNext : ()Z
    //   422: istore #4
    //   424: iload #4
    //   426: ifne -> 444
    //   429: aload #7
    //   431: ifnull -> 441
    //   434: aload #7
    //   436: invokeinterface close : ()V
    //   441: aload #10
    //   443: areturn
    //   444: aload #9
    //   446: astore_2
    //   447: goto -> 247
    //   450: astore_3
    //   451: aload #9
    //   453: astore_2
    //   454: goto -> 491
    //   457: astore_3
    //   458: goto -> 491
    //   461: astore_1
    //   462: goto -> 533
    //   465: astore_3
    //   466: aload #9
    //   468: astore_2
    //   469: goto -> 491
    //   472: astore_3
    //   473: goto -> 488
    //   476: astore_3
    //   477: goto -> 488
    //   480: astore_1
    //   481: aload #9
    //   483: astore_2
    //   484: goto -> 536
    //   487: astore_3
    //   488: aconst_null
    //   489: astore #7
    //   491: aload #7
    //   493: astore #8
    //   495: aload_0
    //   496: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   499: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   502: ldc_w '(2)Error querying user properties'
    //   505: aload_1
    //   506: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   509: aload_2
    //   510: aload_3
    //   511: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   514: aload #7
    //   516: ifnull -> 526
    //   519: aload #7
    //   521: invokeinterface close : ()V
    //   526: aconst_null
    //   527: areturn
    //   528: astore_1
    //   529: aload #8
    //   531: astore #7
    //   533: aload #7
    //   535: astore_2
    //   536: aload_2
    //   537: ifnull -> 546
    //   540: aload_2
    //   541: invokeinterface close : ()V
    //   546: aload_1
    //   547: athrow
    // Exception table:
    //   from	to	target	type
    //   25	36	487	android/database/sqlite/SQLiteException
    //   25	36	480	finally
    //   36	64	476	android/database/sqlite/SQLiteException
    //   36	64	480	finally
    //   69	87	472	android/database/sqlite/SQLiteException
    //   69	87	480	finally
    //   93	127	472	android/database/sqlite/SQLiteException
    //   93	127	480	finally
    //   127	211	472	android/database/sqlite/SQLiteException
    //   127	211	480	finally
    //   215	224	465	android/database/sqlite/SQLiteException
    //   215	224	461	finally
    //   250	263	465	android/database/sqlite/SQLiteException
    //   250	263	461	finally
    //   266	285	465	android/database/sqlite/SQLiteException
    //   266	285	461	finally
    //   291	301	465	android/database/sqlite/SQLiteException
    //   291	301	461	finally
    //   304	314	465	android/database/sqlite/SQLiteException
    //   304	314	461	finally
    //   318	327	457	android/database/sqlite/SQLiteException
    //   318	327	528	finally
    //   331	341	457	android/database/sqlite/SQLiteException
    //   331	341	528	finally
    //   350	370	450	android/database/sqlite/SQLiteException
    //   350	370	528	finally
    //   377	381	450	android/database/sqlite/SQLiteException
    //   377	381	528	finally
    //   385	398	450	android/database/sqlite/SQLiteException
    //   385	398	528	finally
    //   402	411	450	android/database/sqlite/SQLiteException
    //   402	411	528	finally
    //   415	424	450	android/database/sqlite/SQLiteException
    //   415	424	528	finally
    //   495	514	528	finally
  }
  
  public final List<zzo> zzb(String paramString, String[] paramArrayOfString) {
    zzaf();
    zzcl();
    ArrayList<zzo> arrayList = new ArrayList();
    Cursor cursor2 = null;
    Cursor cursor1 = null;
    try {
      Cursor cursor = getWritableDatabase().query("conditional_properties", new String[] { 
            "app_id", "origin", "name", "value", "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", 
            "triggered_timestamp", "time_to_live", "expired_event" }, paramString, paramArrayOfString, null, null, "rowid", "1001");
      cursor1 = cursor;
      cursor2 = cursor;
      boolean bool = cursor.moveToFirst();
      if (!bool) {
        if (cursor != null)
          cursor.close(); 
        return arrayList;
      } 
      do {
        cursor1 = cursor;
        cursor2 = cursor;
        if (arrayList.size() >= 1000) {
          cursor1 = cursor;
          cursor2 = cursor;
          zzgt().zzjg().zzg("Read more than the max allowed conditional properties, ignoring extra", Integer.valueOf(1000));
          break;
        } 
        bool = false;
        cursor1 = cursor;
        cursor2 = cursor;
        String str3 = cursor.getString(0);
        cursor1 = cursor;
        cursor2 = cursor;
        String str1 = cursor.getString(1);
        cursor1 = cursor;
        cursor2 = cursor;
        String str4 = cursor.getString(2);
        cursor1 = cursor;
        cursor2 = cursor;
        Object object = zza(cursor, 3);
        cursor1 = cursor;
        cursor2 = cursor;
        if (cursor.getInt(4) != 0)
          bool = true; 
        cursor1 = cursor;
        cursor2 = cursor;
        String str2 = cursor.getString(5);
        cursor1 = cursor;
        cursor2 = cursor;
        long l4 = cursor.getLong(6);
        cursor1 = cursor;
        cursor2 = cursor;
        zzag zzag2 = zzjr().<zzag>zza(cursor.getBlob(7), zzag.CREATOR);
        cursor1 = cursor;
        cursor2 = cursor;
        long l1 = cursor.getLong(8);
        cursor1 = cursor;
        cursor2 = cursor;
        zzag zzag1 = zzjr().<zzag>zza(cursor.getBlob(9), zzag.CREATOR);
        cursor1 = cursor;
        cursor2 = cursor;
        long l2 = cursor.getLong(10);
        cursor1 = cursor;
        cursor2 = cursor;
        long l3 = cursor.getLong(11);
        cursor1 = cursor;
        cursor2 = cursor;
        zzag zzag3 = zzjr().<zzag>zza(cursor.getBlob(12), zzag.CREATOR);
        cursor1 = cursor;
        cursor2 = cursor;
        zzfu zzfu = new zzfu();
        cursor1 = cursor;
        cursor2 = cursor;
        this(str4, l2, object, str1);
        cursor1 = cursor;
        cursor2 = cursor;
        object = new zzo();
        cursor1 = cursor;
        cursor2 = cursor;
        super(str3, str1, zzfu, l1, bool, str2, zzag2, l4, zzag1, l3, zzag3);
        cursor1 = cursor;
        cursor2 = cursor;
        arrayList.add(object);
        cursor1 = cursor;
        cursor2 = cursor;
        bool = cursor.moveToNext();
      } while (bool);
      if (cursor != null)
        cursor.close(); 
      return arrayList;
    } catch (SQLiteException sQLiteException) {
      cursor1 = cursor2;
      zzgt().zzjg().zzg("Error querying conditional user property value", sQLiteException);
      cursor1 = cursor2;
      List<?> list = Collections.emptyList();
      if (cursor2 != null)
        cursor2.close(); 
      return (List)list;
    } finally {}
    if (cursor1 != null)
      cursor1.close(); 
    throw paramString;
  }
  
  public final List<zzfw> zzbl(String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual zzaf : ()V
    //   9: aload_0
    //   10: invokevirtual zzcl : ()V
    //   13: new java/util/ArrayList
    //   16: dup
    //   17: invokespecial <init> : ()V
    //   20: astore #10
    //   22: aload_0
    //   23: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   26: ldc_w 'user_attributes'
    //   29: iconst_4
    //   30: anewarray java/lang/String
    //   33: dup
    //   34: iconst_0
    //   35: ldc_w 'name'
    //   38: aastore
    //   39: dup
    //   40: iconst_1
    //   41: ldc 'origin'
    //   43: aastore
    //   44: dup
    //   45: iconst_2
    //   46: ldc_w 'set_timestamp'
    //   49: aastore
    //   50: dup
    //   51: iconst_3
    //   52: ldc_w 'value'
    //   55: aastore
    //   56: ldc_w 'app_id=?'
    //   59: iconst_1
    //   60: anewarray java/lang/String
    //   63: dup
    //   64: iconst_0
    //   65: aload_1
    //   66: aastore
    //   67: aconst_null
    //   68: aconst_null
    //   69: ldc_w 'rowid'
    //   72: ldc_w '1000'
    //   75: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   78: astore #6
    //   80: aload #6
    //   82: astore #5
    //   84: aload #6
    //   86: invokeinterface moveToFirst : ()Z
    //   91: istore #4
    //   93: iload #4
    //   95: ifne -> 113
    //   98: aload #6
    //   100: ifnull -> 110
    //   103: aload #6
    //   105: invokeinterface close : ()V
    //   110: aload #10
    //   112: areturn
    //   113: aload #6
    //   115: astore #5
    //   117: aload #6
    //   119: iconst_0
    //   120: invokeinterface getString : (I)Ljava/lang/String;
    //   125: astore #9
    //   127: aload #6
    //   129: astore #5
    //   131: aload #6
    //   133: iconst_1
    //   134: invokeinterface getString : (I)Ljava/lang/String;
    //   139: astore #8
    //   141: aload #8
    //   143: astore #7
    //   145: aload #8
    //   147: ifnonnull -> 155
    //   150: ldc_w ''
    //   153: astore #7
    //   155: aload #6
    //   157: astore #5
    //   159: aload #6
    //   161: iconst_2
    //   162: invokeinterface getLong : (I)J
    //   167: lstore_2
    //   168: aload #6
    //   170: astore #5
    //   172: aload_0
    //   173: aload #6
    //   175: iconst_3
    //   176: invokespecial zza : (Landroid/database/Cursor;I)Ljava/lang/Object;
    //   179: astore #8
    //   181: aload #8
    //   183: ifnonnull -> 210
    //   186: aload #6
    //   188: astore #5
    //   190: aload_0
    //   191: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   194: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   197: ldc_w 'Read invalid user property value, ignoring it. appId'
    //   200: aload_1
    //   201: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   204: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   207: goto -> 250
    //   210: aload #6
    //   212: astore #5
    //   214: new com/google/android/gms/measurement/internal/zzfw
    //   217: astore #11
    //   219: aload #6
    //   221: astore #5
    //   223: aload #11
    //   225: aload_1
    //   226: aload #7
    //   228: aload #9
    //   230: lload_2
    //   231: aload #8
    //   233: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   236: aload #6
    //   238: astore #5
    //   240: aload #10
    //   242: aload #11
    //   244: invokeinterface add : (Ljava/lang/Object;)Z
    //   249: pop
    //   250: aload #6
    //   252: astore #5
    //   254: aload #6
    //   256: invokeinterface moveToNext : ()Z
    //   261: istore #4
    //   263: iload #4
    //   265: ifne -> 113
    //   268: aload #6
    //   270: ifnull -> 280
    //   273: aload #6
    //   275: invokeinterface close : ()V
    //   280: aload #10
    //   282: areturn
    //   283: astore #7
    //   285: goto -> 300
    //   288: astore_1
    //   289: aconst_null
    //   290: astore #5
    //   292: goto -> 338
    //   295: astore #7
    //   297: aconst_null
    //   298: astore #6
    //   300: aload #6
    //   302: astore #5
    //   304: aload_0
    //   305: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   308: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   311: ldc_w 'Error querying user properties. appId'
    //   314: aload_1
    //   315: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   318: aload #7
    //   320: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   323: aload #6
    //   325: ifnull -> 335
    //   328: aload #6
    //   330: invokeinterface close : ()V
    //   335: aconst_null
    //   336: areturn
    //   337: astore_1
    //   338: aload #5
    //   340: ifnull -> 350
    //   343: aload #5
    //   345: invokeinterface close : ()V
    //   350: aload_1
    //   351: athrow
    // Exception table:
    //   from	to	target	type
    //   22	80	295	android/database/sqlite/SQLiteException
    //   22	80	288	finally
    //   84	93	283	android/database/sqlite/SQLiteException
    //   84	93	337	finally
    //   117	127	283	android/database/sqlite/SQLiteException
    //   117	127	337	finally
    //   131	141	283	android/database/sqlite/SQLiteException
    //   131	141	337	finally
    //   159	168	283	android/database/sqlite/SQLiteException
    //   159	168	337	finally
    //   172	181	283	android/database/sqlite/SQLiteException
    //   172	181	337	finally
    //   190	207	283	android/database/sqlite/SQLiteException
    //   190	207	337	finally
    //   214	219	283	android/database/sqlite/SQLiteException
    //   214	219	337	finally
    //   223	236	283	android/database/sqlite/SQLiteException
    //   223	236	337	finally
    //   240	250	283	android/database/sqlite/SQLiteException
    //   240	250	337	finally
    //   254	263	283	android/database/sqlite/SQLiteException
    //   254	263	337	finally
    //   304	323	337	finally
  }
  
  public final zzg zzbm(String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual zzaf : ()V
    //   9: aload_0
    //   10: invokevirtual zzcl : ()V
    //   13: aload_0
    //   14: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   17: astore #6
    //   19: iconst_1
    //   20: istore_3
    //   21: aload #6
    //   23: ldc_w 'apps'
    //   26: bipush #26
    //   28: anewarray java/lang/String
    //   31: dup
    //   32: iconst_0
    //   33: ldc_w 'app_instance_id'
    //   36: aastore
    //   37: dup
    //   38: iconst_1
    //   39: ldc_w 'gmp_app_id'
    //   42: aastore
    //   43: dup
    //   44: iconst_2
    //   45: ldc_w 'resettable_device_id_hash'
    //   48: aastore
    //   49: dup
    //   50: iconst_3
    //   51: ldc_w 'last_bundle_index'
    //   54: aastore
    //   55: dup
    //   56: iconst_4
    //   57: ldc 'last_bundle_start_timestamp'
    //   59: aastore
    //   60: dup
    //   61: iconst_5
    //   62: ldc_w 'last_bundle_end_timestamp'
    //   65: aastore
    //   66: dup
    //   67: bipush #6
    //   69: ldc 'app_version'
    //   71: aastore
    //   72: dup
    //   73: bipush #7
    //   75: ldc 'app_store'
    //   77: aastore
    //   78: dup
    //   79: bipush #8
    //   81: ldc 'gmp_version'
    //   83: aastore
    //   84: dup
    //   85: bipush #9
    //   87: ldc 'dev_cert_hash'
    //   89: aastore
    //   90: dup
    //   91: bipush #10
    //   93: ldc 'measurement_enabled'
    //   95: aastore
    //   96: dup
    //   97: bipush #11
    //   99: ldc 'day'
    //   101: aastore
    //   102: dup
    //   103: bipush #12
    //   105: ldc 'daily_public_events_count'
    //   107: aastore
    //   108: dup
    //   109: bipush #13
    //   111: ldc 'daily_events_count'
    //   113: aastore
    //   114: dup
    //   115: bipush #14
    //   117: ldc 'daily_conversions_count'
    //   119: aastore
    //   120: dup
    //   121: bipush #15
    //   123: ldc 'config_fetched_time'
    //   125: aastore
    //   126: dup
    //   127: bipush #16
    //   129: ldc 'failed_config_fetch_time'
    //   131: aastore
    //   132: dup
    //   133: bipush #17
    //   135: ldc 'app_version_int'
    //   137: aastore
    //   138: dup
    //   139: bipush #18
    //   141: ldc 'firebase_instance_id'
    //   143: aastore
    //   144: dup
    //   145: bipush #19
    //   147: ldc 'daily_error_events_count'
    //   149: aastore
    //   150: dup
    //   151: bipush #20
    //   153: ldc 'daily_realtime_events_count'
    //   155: aastore
    //   156: dup
    //   157: bipush #21
    //   159: ldc 'health_monitor_sample'
    //   161: aastore
    //   162: dup
    //   163: bipush #22
    //   165: ldc 'android_id'
    //   167: aastore
    //   168: dup
    //   169: bipush #23
    //   171: ldc 'adid_reporting_enabled'
    //   173: aastore
    //   174: dup
    //   175: bipush #24
    //   177: ldc 'ssaid_reporting_enabled'
    //   179: aastore
    //   180: dup
    //   181: bipush #25
    //   183: ldc 'admob_app_id'
    //   185: aastore
    //   186: ldc_w 'app_id=?'
    //   189: iconst_1
    //   190: anewarray java/lang/String
    //   193: dup
    //   194: iconst_0
    //   195: aload_1
    //   196: aastore
    //   197: aconst_null
    //   198: aconst_null
    //   199: aconst_null
    //   200: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   203: astore #6
    //   205: aload #6
    //   207: invokeinterface moveToFirst : ()Z
    //   212: istore_2
    //   213: iload_2
    //   214: ifne -> 231
    //   217: aload #6
    //   219: ifnull -> 229
    //   222: aload #6
    //   224: invokeinterface close : ()V
    //   229: aconst_null
    //   230: areturn
    //   231: new com/google/android/gms/measurement/internal/zzg
    //   234: astore #8
    //   236: aload #6
    //   238: astore #7
    //   240: aload #8
    //   242: aload_0
    //   243: getfield zzamx : Lcom/google/android/gms/measurement/internal/zzfn;
    //   246: invokevirtual zzmh : ()Lcom/google/android/gms/measurement/internal/zzbw;
    //   249: aload_1
    //   250: invokespecial <init> : (Lcom/google/android/gms/measurement/internal/zzbw;Ljava/lang/String;)V
    //   253: aload #6
    //   255: astore #7
    //   257: aload #8
    //   259: aload #6
    //   261: iconst_0
    //   262: invokeinterface getString : (I)Ljava/lang/String;
    //   267: invokevirtual zzaj : (Ljava/lang/String;)V
    //   270: aload #6
    //   272: astore #7
    //   274: aload #8
    //   276: aload #6
    //   278: iconst_1
    //   279: invokeinterface getString : (I)Ljava/lang/String;
    //   284: invokevirtual zzak : (Ljava/lang/String;)V
    //   287: aload #6
    //   289: astore #7
    //   291: aload #8
    //   293: aload #6
    //   295: iconst_2
    //   296: invokeinterface getString : (I)Ljava/lang/String;
    //   301: invokevirtual zzam : (Ljava/lang/String;)V
    //   304: aload #6
    //   306: astore #7
    //   308: aload #8
    //   310: aload #6
    //   312: iconst_3
    //   313: invokeinterface getLong : (I)J
    //   318: invokevirtual zzt : (J)V
    //   321: aload #6
    //   323: astore #7
    //   325: aload #8
    //   327: aload #6
    //   329: iconst_4
    //   330: invokeinterface getLong : (I)J
    //   335: invokevirtual zzo : (J)V
    //   338: aload #6
    //   340: astore #7
    //   342: aload #8
    //   344: aload #6
    //   346: iconst_5
    //   347: invokeinterface getLong : (I)J
    //   352: invokevirtual zzp : (J)V
    //   355: aload #6
    //   357: astore #7
    //   359: aload #8
    //   361: aload #6
    //   363: bipush #6
    //   365: invokeinterface getString : (I)Ljava/lang/String;
    //   370: invokevirtual setAppVersion : (Ljava/lang/String;)V
    //   373: aload #6
    //   375: astore #7
    //   377: aload #8
    //   379: aload #6
    //   381: bipush #7
    //   383: invokeinterface getString : (I)Ljava/lang/String;
    //   388: invokevirtual zzao : (Ljava/lang/String;)V
    //   391: aload #6
    //   393: astore #7
    //   395: aload #8
    //   397: aload #6
    //   399: bipush #8
    //   401: invokeinterface getLong : (I)J
    //   406: invokevirtual zzr : (J)V
    //   409: aload #6
    //   411: astore #7
    //   413: aload #8
    //   415: aload #6
    //   417: bipush #9
    //   419: invokeinterface getLong : (I)J
    //   424: invokevirtual zzs : (J)V
    //   427: aload #6
    //   429: astore #7
    //   431: aload #6
    //   433: bipush #10
    //   435: invokeinterface isNull : (I)Z
    //   440: ifne -> 467
    //   443: aload #6
    //   445: astore #7
    //   447: aload #6
    //   449: bipush #10
    //   451: invokeinterface getInt : (I)I
    //   456: ifeq -> 462
    //   459: goto -> 467
    //   462: iconst_0
    //   463: istore_2
    //   464: goto -> 469
    //   467: iconst_1
    //   468: istore_2
    //   469: aload #6
    //   471: astore #7
    //   473: aload #8
    //   475: iload_2
    //   476: invokevirtual setMeasurementEnabled : (Z)V
    //   479: aload #6
    //   481: astore #7
    //   483: aload #8
    //   485: aload #6
    //   487: bipush #11
    //   489: invokeinterface getLong : (I)J
    //   494: invokevirtual zzw : (J)V
    //   497: aload #6
    //   499: astore #7
    //   501: aload #8
    //   503: aload #6
    //   505: bipush #12
    //   507: invokeinterface getLong : (I)J
    //   512: invokevirtual zzx : (J)V
    //   515: aload #6
    //   517: astore #7
    //   519: aload #8
    //   521: aload #6
    //   523: bipush #13
    //   525: invokeinterface getLong : (I)J
    //   530: invokevirtual zzy : (J)V
    //   533: aload #6
    //   535: astore #7
    //   537: aload #8
    //   539: aload #6
    //   541: bipush #14
    //   543: invokeinterface getLong : (I)J
    //   548: invokevirtual zzz : (J)V
    //   551: aload #6
    //   553: astore #7
    //   555: aload #8
    //   557: aload #6
    //   559: bipush #15
    //   561: invokeinterface getLong : (I)J
    //   566: invokevirtual zzu : (J)V
    //   569: aload #6
    //   571: astore #7
    //   573: aload #8
    //   575: aload #6
    //   577: bipush #16
    //   579: invokeinterface getLong : (I)J
    //   584: invokevirtual zzv : (J)V
    //   587: aload #6
    //   589: astore #7
    //   591: aload #6
    //   593: bipush #17
    //   595: invokeinterface isNull : (I)Z
    //   600: ifeq -> 611
    //   603: ldc2_w -2147483648
    //   606: lstore #4
    //   608: goto -> 627
    //   611: aload #6
    //   613: astore #7
    //   615: aload #6
    //   617: bipush #17
    //   619: invokeinterface getInt : (I)I
    //   624: i2l
    //   625: lstore #4
    //   627: aload #6
    //   629: astore #7
    //   631: aload #8
    //   633: lload #4
    //   635: invokevirtual zzq : (J)V
    //   638: aload #6
    //   640: astore #7
    //   642: aload #8
    //   644: aload #6
    //   646: bipush #18
    //   648: invokeinterface getString : (I)Ljava/lang/String;
    //   653: invokevirtual zzan : (Ljava/lang/String;)V
    //   656: aload #6
    //   658: astore #7
    //   660: aload #8
    //   662: aload #6
    //   664: bipush #19
    //   666: invokeinterface getLong : (I)J
    //   671: invokevirtual zzab : (J)V
    //   674: aload #6
    //   676: astore #7
    //   678: aload #8
    //   680: aload #6
    //   682: bipush #20
    //   684: invokeinterface getLong : (I)J
    //   689: invokevirtual zzaa : (J)V
    //   692: aload #6
    //   694: astore #7
    //   696: aload #8
    //   698: aload #6
    //   700: bipush #21
    //   702: invokeinterface getString : (I)Ljava/lang/String;
    //   707: invokevirtual zzap : (Ljava/lang/String;)V
    //   710: aload #6
    //   712: astore #7
    //   714: aload #6
    //   716: bipush #22
    //   718: invokeinterface isNull : (I)Z
    //   723: ifeq -> 732
    //   726: lconst_0
    //   727: lstore #4
    //   729: goto -> 747
    //   732: aload #6
    //   734: astore #7
    //   736: aload #6
    //   738: bipush #22
    //   740: invokeinterface getLong : (I)J
    //   745: lstore #4
    //   747: aload #6
    //   749: astore #7
    //   751: aload #8
    //   753: lload #4
    //   755: invokevirtual zzac : (J)V
    //   758: aload #6
    //   760: astore #7
    //   762: aload #6
    //   764: bipush #23
    //   766: invokeinterface isNull : (I)Z
    //   771: ifne -> 798
    //   774: aload #6
    //   776: astore #7
    //   778: aload #6
    //   780: bipush #23
    //   782: invokeinterface getInt : (I)I
    //   787: ifeq -> 793
    //   790: goto -> 798
    //   793: iconst_0
    //   794: istore_2
    //   795: goto -> 800
    //   798: iconst_1
    //   799: istore_2
    //   800: aload #6
    //   802: astore #7
    //   804: aload #8
    //   806: iload_2
    //   807: invokevirtual zze : (Z)V
    //   810: iload_3
    //   811: istore_2
    //   812: aload #6
    //   814: astore #7
    //   816: aload #6
    //   818: bipush #24
    //   820: invokeinterface isNull : (I)Z
    //   825: ifne -> 851
    //   828: aload #6
    //   830: astore #7
    //   832: aload #6
    //   834: bipush #24
    //   836: invokeinterface getInt : (I)I
    //   841: ifeq -> 849
    //   844: iload_3
    //   845: istore_2
    //   846: goto -> 851
    //   849: iconst_0
    //   850: istore_2
    //   851: aload #6
    //   853: astore #7
    //   855: aload #8
    //   857: iload_2
    //   858: invokevirtual zzf : (Z)V
    //   861: aload #6
    //   863: astore #7
    //   865: aload #8
    //   867: aload #6
    //   869: bipush #25
    //   871: invokeinterface getString : (I)Ljava/lang/String;
    //   876: invokevirtual zzal : (Ljava/lang/String;)V
    //   879: aload #6
    //   881: astore #7
    //   883: aload #8
    //   885: invokevirtual zzha : ()V
    //   888: aload #6
    //   890: astore #7
    //   892: aload #6
    //   894: invokeinterface moveToNext : ()Z
    //   899: ifeq -> 923
    //   902: aload #6
    //   904: astore #7
    //   906: aload_0
    //   907: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   910: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   913: ldc_w 'Got multiple records for app, expected one. appId'
    //   916: aload_1
    //   917: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   920: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   923: aload #6
    //   925: ifnull -> 935
    //   928: aload #6
    //   930: invokeinterface close : ()V
    //   935: aload #8
    //   937: areturn
    //   938: astore #8
    //   940: goto -> 964
    //   943: astore_1
    //   944: goto -> 1006
    //   947: astore #8
    //   949: goto -> 964
    //   952: astore_1
    //   953: aconst_null
    //   954: astore #6
    //   956: goto -> 1006
    //   959: astore #8
    //   961: aconst_null
    //   962: astore #6
    //   964: aload #6
    //   966: astore #7
    //   968: aload_0
    //   969: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   972: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   975: ldc_w 'Error querying app. appId'
    //   978: aload_1
    //   979: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   982: aload #8
    //   984: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   987: aload #6
    //   989: ifnull -> 999
    //   992: aload #6
    //   994: invokeinterface close : ()V
    //   999: aconst_null
    //   1000: areturn
    //   1001: astore_1
    //   1002: aload #7
    //   1004: astore #6
    //   1006: aload #6
    //   1008: ifnull -> 1018
    //   1011: aload #6
    //   1013: invokeinterface close : ()V
    //   1018: aload_1
    //   1019: athrow
    // Exception table:
    //   from	to	target	type
    //   13	19	959	android/database/sqlite/SQLiteException
    //   13	19	952	finally
    //   21	205	959	android/database/sqlite/SQLiteException
    //   21	205	952	finally
    //   205	213	947	android/database/sqlite/SQLiteException
    //   205	213	943	finally
    //   231	236	947	android/database/sqlite/SQLiteException
    //   231	236	943	finally
    //   240	253	938	android/database/sqlite/SQLiteException
    //   240	253	1001	finally
    //   257	270	938	android/database/sqlite/SQLiteException
    //   257	270	1001	finally
    //   274	287	938	android/database/sqlite/SQLiteException
    //   274	287	1001	finally
    //   291	304	938	android/database/sqlite/SQLiteException
    //   291	304	1001	finally
    //   308	321	938	android/database/sqlite/SQLiteException
    //   308	321	1001	finally
    //   325	338	938	android/database/sqlite/SQLiteException
    //   325	338	1001	finally
    //   342	355	938	android/database/sqlite/SQLiteException
    //   342	355	1001	finally
    //   359	373	938	android/database/sqlite/SQLiteException
    //   359	373	1001	finally
    //   377	391	938	android/database/sqlite/SQLiteException
    //   377	391	1001	finally
    //   395	409	938	android/database/sqlite/SQLiteException
    //   395	409	1001	finally
    //   413	427	938	android/database/sqlite/SQLiteException
    //   413	427	1001	finally
    //   431	443	938	android/database/sqlite/SQLiteException
    //   431	443	1001	finally
    //   447	459	938	android/database/sqlite/SQLiteException
    //   447	459	1001	finally
    //   473	479	938	android/database/sqlite/SQLiteException
    //   473	479	1001	finally
    //   483	497	938	android/database/sqlite/SQLiteException
    //   483	497	1001	finally
    //   501	515	938	android/database/sqlite/SQLiteException
    //   501	515	1001	finally
    //   519	533	938	android/database/sqlite/SQLiteException
    //   519	533	1001	finally
    //   537	551	938	android/database/sqlite/SQLiteException
    //   537	551	1001	finally
    //   555	569	938	android/database/sqlite/SQLiteException
    //   555	569	1001	finally
    //   573	587	938	android/database/sqlite/SQLiteException
    //   573	587	1001	finally
    //   591	603	938	android/database/sqlite/SQLiteException
    //   591	603	1001	finally
    //   615	627	938	android/database/sqlite/SQLiteException
    //   615	627	1001	finally
    //   631	638	938	android/database/sqlite/SQLiteException
    //   631	638	1001	finally
    //   642	656	938	android/database/sqlite/SQLiteException
    //   642	656	1001	finally
    //   660	674	938	android/database/sqlite/SQLiteException
    //   660	674	1001	finally
    //   678	692	938	android/database/sqlite/SQLiteException
    //   678	692	1001	finally
    //   696	710	938	android/database/sqlite/SQLiteException
    //   696	710	1001	finally
    //   714	726	938	android/database/sqlite/SQLiteException
    //   714	726	1001	finally
    //   736	747	938	android/database/sqlite/SQLiteException
    //   736	747	1001	finally
    //   751	758	938	android/database/sqlite/SQLiteException
    //   751	758	1001	finally
    //   762	774	938	android/database/sqlite/SQLiteException
    //   762	774	1001	finally
    //   778	790	938	android/database/sqlite/SQLiteException
    //   778	790	1001	finally
    //   804	810	938	android/database/sqlite/SQLiteException
    //   804	810	1001	finally
    //   816	828	938	android/database/sqlite/SQLiteException
    //   816	828	1001	finally
    //   832	844	938	android/database/sqlite/SQLiteException
    //   832	844	1001	finally
    //   855	861	938	android/database/sqlite/SQLiteException
    //   855	861	1001	finally
    //   865	879	938	android/database/sqlite/SQLiteException
    //   865	879	1001	finally
    //   883	888	938	android/database/sqlite/SQLiteException
    //   883	888	1001	finally
    //   892	902	938	android/database/sqlite/SQLiteException
    //   892	902	1001	finally
    //   906	923	938	android/database/sqlite/SQLiteException
    //   906	923	1001	finally
    //   968	987	1001	finally
  }
  
  public final long zzbn(String paramString) {
    Preconditions.checkNotEmpty(paramString);
    zzaf();
    zzcl();
    try {
      int i = getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[] { paramString, String.valueOf(Math.max(0, Math.min(1000000, zzgv().zzb(paramString, zzai.zzajj)))) });
      return i;
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zze("Error deleting over the limit events. appId", zzas.zzbw(paramString), sQLiteException);
      return 0L;
    } 
  }
  
  public final byte[] zzbo(String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual zzaf : ()V
    //   9: aload_0
    //   10: invokevirtual zzcl : ()V
    //   13: aload_0
    //   14: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   17: ldc_w 'apps'
    //   20: iconst_1
    //   21: anewarray java/lang/String
    //   24: dup
    //   25: iconst_0
    //   26: ldc 'remote_config'
    //   28: aastore
    //   29: ldc_w 'app_id=?'
    //   32: iconst_1
    //   33: anewarray java/lang/String
    //   36: dup
    //   37: iconst_0
    //   38: aload_1
    //   39: aastore
    //   40: aconst_null
    //   41: aconst_null
    //   42: aconst_null
    //   43: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   46: astore #4
    //   48: aload #4
    //   50: astore_3
    //   51: aload #4
    //   53: invokeinterface moveToFirst : ()Z
    //   58: istore_2
    //   59: iload_2
    //   60: ifne -> 77
    //   63: aload #4
    //   65: ifnull -> 75
    //   68: aload #4
    //   70: invokeinterface close : ()V
    //   75: aconst_null
    //   76: areturn
    //   77: aload #4
    //   79: astore_3
    //   80: aload #4
    //   82: iconst_0
    //   83: invokeinterface getBlob : (I)[B
    //   88: astore #5
    //   90: aload #4
    //   92: astore_3
    //   93: aload #4
    //   95: invokeinterface moveToNext : ()Z
    //   100: ifeq -> 123
    //   103: aload #4
    //   105: astore_3
    //   106: aload_0
    //   107: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   110: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   113: ldc_w 'Got multiple records for app config, expected one. appId'
    //   116: aload_1
    //   117: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   120: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   123: aload #4
    //   125: ifnull -> 135
    //   128: aload #4
    //   130: invokeinterface close : ()V
    //   135: aload #5
    //   137: areturn
    //   138: astore #5
    //   140: goto -> 154
    //   143: astore_1
    //   144: aconst_null
    //   145: astore_3
    //   146: goto -> 191
    //   149: astore #5
    //   151: aconst_null
    //   152: astore #4
    //   154: aload #4
    //   156: astore_3
    //   157: aload_0
    //   158: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   161: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   164: ldc_w 'Error querying remote config. appId'
    //   167: aload_1
    //   168: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   171: aload #5
    //   173: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   176: aload #4
    //   178: ifnull -> 188
    //   181: aload #4
    //   183: invokeinterface close : ()V
    //   188: aconst_null
    //   189: areturn
    //   190: astore_1
    //   191: aload_3
    //   192: ifnull -> 201
    //   195: aload_3
    //   196: invokeinterface close : ()V
    //   201: aload_1
    //   202: athrow
    // Exception table:
    //   from	to	target	type
    //   13	48	149	android/database/sqlite/SQLiteException
    //   13	48	143	finally
    //   51	59	138	android/database/sqlite/SQLiteException
    //   51	59	190	finally
    //   80	90	138	android/database/sqlite/SQLiteException
    //   80	90	190	finally
    //   93	103	138	android/database/sqlite/SQLiteException
    //   93	103	190	finally
    //   106	123	138	android/database/sqlite/SQLiteException
    //   106	123	190	finally
    //   157	176	190	finally
  }
  
  final Map<Integer, zzfx> zzbp(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzcl : ()V
    //   4: aload_0
    //   5: invokevirtual zzaf : ()V
    //   8: aload_1
    //   9: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   12: pop
    //   13: aload_0
    //   14: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   17: astore #4
    //   19: aload #4
    //   21: ldc_w 'audience_filter_values'
    //   24: iconst_2
    //   25: anewarray java/lang/String
    //   28: dup
    //   29: iconst_0
    //   30: ldc_w 'audience_id'
    //   33: aastore
    //   34: dup
    //   35: iconst_1
    //   36: ldc_w 'current_results'
    //   39: aastore
    //   40: ldc_w 'app_id=?'
    //   43: iconst_1
    //   44: anewarray java/lang/String
    //   47: dup
    //   48: iconst_0
    //   49: aload_1
    //   50: aastore
    //   51: aconst_null
    //   52: aconst_null
    //   53: aconst_null
    //   54: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   57: astore #5
    //   59: aload #5
    //   61: astore #4
    //   63: aload #5
    //   65: invokeinterface moveToFirst : ()Z
    //   70: istore_3
    //   71: iload_3
    //   72: ifne -> 89
    //   75: aload #5
    //   77: ifnull -> 87
    //   80: aload #5
    //   82: invokeinterface close : ()V
    //   87: aconst_null
    //   88: areturn
    //   89: aload #5
    //   91: astore #4
    //   93: new androidx/collection/ArrayMap
    //   96: astore #6
    //   98: aload #5
    //   100: astore #4
    //   102: aload #6
    //   104: invokespecial <init> : ()V
    //   107: aload #5
    //   109: astore #4
    //   111: aload #5
    //   113: iconst_0
    //   114: invokeinterface getInt : (I)I
    //   119: istore_2
    //   120: aload #5
    //   122: astore #4
    //   124: aload #5
    //   126: iconst_1
    //   127: invokeinterface getBlob : (I)[B
    //   132: astore #7
    //   134: aload #5
    //   136: astore #4
    //   138: aload #7
    //   140: iconst_0
    //   141: aload #7
    //   143: arraylength
    //   144: invokestatic zzj : ([BII)Lcom/google/android/gms/internal/measurement/zzxz;
    //   147: astore #7
    //   149: aload #5
    //   151: astore #4
    //   153: new com/google/android/gms/internal/measurement/zzfx
    //   156: astore #8
    //   158: aload #5
    //   160: astore #4
    //   162: aload #8
    //   164: invokespecial <init> : ()V
    //   167: aload #5
    //   169: astore #4
    //   171: aload #8
    //   173: aload #7
    //   175: invokevirtual zza : (Lcom/google/android/gms/internal/measurement/zzxz;)Lcom/google/android/gms/internal/measurement/zzyi;
    //   178: pop
    //   179: aload #5
    //   181: astore #4
    //   183: aload #6
    //   185: iload_2
    //   186: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   189: aload #8
    //   191: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   196: pop
    //   197: goto -> 229
    //   200: astore #7
    //   202: aload #5
    //   204: astore #4
    //   206: aload_0
    //   207: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   210: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   213: ldc_w 'Failed to merge filter results. appId, audienceId, error'
    //   216: aload_1
    //   217: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   220: iload_2
    //   221: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   224: aload #7
    //   226: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   229: aload #5
    //   231: astore #4
    //   233: aload #5
    //   235: invokeinterface moveToNext : ()Z
    //   240: istore_3
    //   241: iload_3
    //   242: ifne -> 107
    //   245: aload #5
    //   247: ifnull -> 257
    //   250: aload #5
    //   252: invokeinterface close : ()V
    //   257: aload #6
    //   259: areturn
    //   260: astore #6
    //   262: goto -> 277
    //   265: astore_1
    //   266: aconst_null
    //   267: astore #4
    //   269: goto -> 315
    //   272: astore #6
    //   274: aconst_null
    //   275: astore #5
    //   277: aload #5
    //   279: astore #4
    //   281: aload_0
    //   282: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   285: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   288: ldc_w 'Database error querying filter results. appId'
    //   291: aload_1
    //   292: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   295: aload #6
    //   297: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   300: aload #5
    //   302: ifnull -> 312
    //   305: aload #5
    //   307: invokeinterface close : ()V
    //   312: aconst_null
    //   313: areturn
    //   314: astore_1
    //   315: aload #4
    //   317: ifnull -> 327
    //   320: aload #4
    //   322: invokeinterface close : ()V
    //   327: aload_1
    //   328: athrow
    // Exception table:
    //   from	to	target	type
    //   19	59	272	android/database/sqlite/SQLiteException
    //   19	59	265	finally
    //   63	71	260	android/database/sqlite/SQLiteException
    //   63	71	314	finally
    //   93	98	260	android/database/sqlite/SQLiteException
    //   93	98	314	finally
    //   102	107	260	android/database/sqlite/SQLiteException
    //   102	107	314	finally
    //   111	120	260	android/database/sqlite/SQLiteException
    //   111	120	314	finally
    //   124	134	260	android/database/sqlite/SQLiteException
    //   124	134	314	finally
    //   138	149	260	android/database/sqlite/SQLiteException
    //   138	149	314	finally
    //   153	158	260	android/database/sqlite/SQLiteException
    //   153	158	314	finally
    //   162	167	260	android/database/sqlite/SQLiteException
    //   162	167	314	finally
    //   171	179	200	java/io/IOException
    //   171	179	260	android/database/sqlite/SQLiteException
    //   171	179	314	finally
    //   183	197	260	android/database/sqlite/SQLiteException
    //   183	197	314	finally
    //   206	229	260	android/database/sqlite/SQLiteException
    //   206	229	314	finally
    //   233	241	260	android/database/sqlite/SQLiteException
    //   233	241	314	finally
    //   281	300	314	finally
  }
  
  public final long zzbq(String paramString) {
    Preconditions.checkNotEmpty(paramString);
    return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[] { paramString }, 0L);
  }
  
  public final List<zzo> zzc(String paramString1, String paramString2, String paramString3) {
    Preconditions.checkNotEmpty(paramString1);
    zzaf();
    zzcl();
    ArrayList<String> arrayList = new ArrayList(3);
    arrayList.add(paramString1);
    StringBuilder stringBuilder = new StringBuilder("app_id=?");
    if (!TextUtils.isEmpty(paramString2)) {
      arrayList.add(paramString2);
      stringBuilder.append(" and origin=?");
    } 
    if (!TextUtils.isEmpty(paramString3)) {
      arrayList.add(String.valueOf(paramString3).concat("*"));
      stringBuilder.append(" and name glob ?");
    } 
    String[] arrayOfString = arrayList.<String>toArray(new String[arrayList.size()]);
    return zzb(stringBuilder.toString(), arrayOfString);
  }
  
  final void zzc(List<Long> paramList) {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramList);
    Preconditions.checkNotZero(paramList.size());
    if (!zzip())
      return; 
    String str = TextUtils.join(",", paramList);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 2);
    stringBuilder.append("(");
    stringBuilder.append(str);
    stringBuilder.append(")");
    str = stringBuilder.toString();
    stringBuilder = new StringBuilder(String.valueOf(str).length() + 80);
    stringBuilder.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
    stringBuilder.append(str);
    stringBuilder.append(" AND retry_count =  2147483647 LIMIT 1");
    if (zza(stringBuilder.toString(), (String[])null) > 0L)
      zzgt().zzjj().zzby("The number of upload retries exceeds the limit. Will remain unchanged."); 
    try {
      SQLiteDatabase sQLiteDatabase = getWritableDatabase();
      int i = String.valueOf(str).length();
      StringBuilder stringBuilder1 = new StringBuilder();
      this(i + 127);
      stringBuilder1.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
      stringBuilder1.append(str);
      stringBuilder1.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
      sQLiteDatabase.execSQL(stringBuilder1.toString());
      return;
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zzg("Error incrementing retry count. error", sQLiteException);
      return;
    } 
  }
  
  public final zzac zzg(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_2
    //   6: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   9: pop
    //   10: aload_0
    //   11: invokevirtual zzaf : ()V
    //   14: aload_0
    //   15: invokevirtual zzcl : ()V
    //   18: aload_0
    //   19: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   22: astore #13
    //   24: iconst_0
    //   25: istore #11
    //   27: aload #13
    //   29: ldc_w 'events'
    //   32: bipush #8
    //   34: anewarray java/lang/String
    //   37: dup
    //   38: iconst_0
    //   39: ldc_w 'lifetime_count'
    //   42: aastore
    //   43: dup
    //   44: iconst_1
    //   45: ldc_w 'current_bundle_count'
    //   48: aastore
    //   49: dup
    //   50: iconst_2
    //   51: ldc_w 'last_fire_timestamp'
    //   54: aastore
    //   55: dup
    //   56: iconst_3
    //   57: ldc 'last_bundled_timestamp'
    //   59: aastore
    //   60: dup
    //   61: iconst_4
    //   62: ldc 'last_bundled_day'
    //   64: aastore
    //   65: dup
    //   66: iconst_5
    //   67: ldc 'last_sampled_complex_event_id'
    //   69: aastore
    //   70: dup
    //   71: bipush #6
    //   73: ldc 'last_sampling_rate'
    //   75: aastore
    //   76: dup
    //   77: bipush #7
    //   79: ldc 'last_exempt_from_sampling'
    //   81: aastore
    //   82: ldc_w 'app_id=? and name=?'
    //   85: iconst_2
    //   86: anewarray java/lang/String
    //   89: dup
    //   90: iconst_0
    //   91: aload_1
    //   92: aastore
    //   93: dup
    //   94: iconst_1
    //   95: aload_2
    //   96: aastore
    //   97: aconst_null
    //   98: aconst_null
    //   99: aconst_null
    //   100: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   103: astore #13
    //   105: aload #13
    //   107: invokeinterface moveToFirst : ()Z
    //   112: istore #12
    //   114: iload #12
    //   116: ifne -> 133
    //   119: aload #13
    //   121: ifnull -> 131
    //   124: aload #13
    //   126: invokeinterface close : ()V
    //   131: aconst_null
    //   132: areturn
    //   133: aload #13
    //   135: iconst_0
    //   136: invokeinterface getLong : (I)J
    //   141: lstore #7
    //   143: aload #13
    //   145: iconst_1
    //   146: invokeinterface getLong : (I)J
    //   151: lstore #5
    //   153: aload #13
    //   155: iconst_2
    //   156: invokeinterface getLong : (I)J
    //   161: lstore #9
    //   163: aload #13
    //   165: iconst_3
    //   166: invokeinterface isNull : (I)Z
    //   171: ifeq -> 179
    //   174: lconst_0
    //   175: lstore_3
    //   176: goto -> 188
    //   179: aload #13
    //   181: iconst_3
    //   182: invokeinterface getLong : (I)J
    //   187: lstore_3
    //   188: aload #13
    //   190: iconst_4
    //   191: invokeinterface isNull : (I)Z
    //   196: ifeq -> 205
    //   199: aconst_null
    //   200: astore #15
    //   202: goto -> 218
    //   205: aload #13
    //   207: iconst_4
    //   208: invokeinterface getLong : (I)J
    //   213: invokestatic valueOf : (J)Ljava/lang/Long;
    //   216: astore #15
    //   218: aload #13
    //   220: iconst_5
    //   221: invokeinterface isNull : (I)Z
    //   226: ifeq -> 235
    //   229: aconst_null
    //   230: astore #16
    //   232: goto -> 248
    //   235: aload #13
    //   237: iconst_5
    //   238: invokeinterface getLong : (I)J
    //   243: invokestatic valueOf : (J)Ljava/lang/Long;
    //   246: astore #16
    //   248: aload #13
    //   250: bipush #6
    //   252: invokeinterface isNull : (I)Z
    //   257: ifeq -> 266
    //   260: aconst_null
    //   261: astore #17
    //   263: goto -> 280
    //   266: aload #13
    //   268: bipush #6
    //   270: invokeinterface getLong : (I)J
    //   275: invokestatic valueOf : (J)Ljava/lang/Long;
    //   278: astore #17
    //   280: aload #13
    //   282: bipush #7
    //   284: invokeinterface isNull : (I)Z
    //   289: ifne -> 319
    //   292: aload #13
    //   294: bipush #7
    //   296: invokeinterface getLong : (I)J
    //   301: lconst_1
    //   302: lcmp
    //   303: ifne -> 309
    //   306: iconst_1
    //   307: istore #11
    //   309: iload #11
    //   311: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   314: astore #18
    //   316: goto -> 322
    //   319: aconst_null
    //   320: astore #18
    //   322: new com/google/android/gms/measurement/internal/zzac
    //   325: astore #19
    //   327: aload #13
    //   329: astore #14
    //   331: aload #19
    //   333: aload_1
    //   334: aload_2
    //   335: lload #7
    //   337: lload #5
    //   339: lload #9
    //   341: lload_3
    //   342: aload #15
    //   344: aload #16
    //   346: aload #17
    //   348: aload #18
    //   350: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;JJJJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;)V
    //   353: aload #13
    //   355: astore #14
    //   357: aload #13
    //   359: invokeinterface moveToNext : ()Z
    //   364: ifeq -> 388
    //   367: aload #13
    //   369: astore #14
    //   371: aload_0
    //   372: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   375: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   378: ldc_w 'Got multiple records for event aggregates, expected one. appId'
    //   381: aload_1
    //   382: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   385: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   388: aload #13
    //   390: ifnull -> 400
    //   393: aload #13
    //   395: invokeinterface close : ()V
    //   400: aload #19
    //   402: areturn
    //   403: astore #15
    //   405: goto -> 433
    //   408: astore_1
    //   409: aload #13
    //   411: astore #14
    //   413: goto -> 479
    //   416: astore #15
    //   418: goto -> 433
    //   421: astore_1
    //   422: aconst_null
    //   423: astore #14
    //   425: goto -> 479
    //   428: astore #15
    //   430: aconst_null
    //   431: astore #13
    //   433: aload #13
    //   435: astore #14
    //   437: aload_0
    //   438: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   441: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   444: ldc_w 'Error querying events. appId'
    //   447: aload_1
    //   448: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   451: aload_0
    //   452: invokevirtual zzgq : ()Lcom/google/android/gms/measurement/internal/zzaq;
    //   455: aload_2
    //   456: invokevirtual zzbt : (Ljava/lang/String;)Ljava/lang/String;
    //   459: aload #15
    //   461: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   464: aload #13
    //   466: ifnull -> 476
    //   469: aload #13
    //   471: invokeinterface close : ()V
    //   476: aconst_null
    //   477: areturn
    //   478: astore_1
    //   479: aload #14
    //   481: ifnull -> 491
    //   484: aload #14
    //   486: invokeinterface close : ()V
    //   491: aload_1
    //   492: athrow
    // Exception table:
    //   from	to	target	type
    //   18	24	428	android/database/sqlite/SQLiteException
    //   18	24	421	finally
    //   27	105	428	android/database/sqlite/SQLiteException
    //   27	105	421	finally
    //   105	114	416	android/database/sqlite/SQLiteException
    //   105	114	408	finally
    //   133	174	416	android/database/sqlite/SQLiteException
    //   133	174	408	finally
    //   179	188	416	android/database/sqlite/SQLiteException
    //   179	188	408	finally
    //   188	199	416	android/database/sqlite/SQLiteException
    //   188	199	408	finally
    //   205	218	416	android/database/sqlite/SQLiteException
    //   205	218	408	finally
    //   218	229	416	android/database/sqlite/SQLiteException
    //   218	229	408	finally
    //   235	248	416	android/database/sqlite/SQLiteException
    //   235	248	408	finally
    //   248	260	416	android/database/sqlite/SQLiteException
    //   248	260	408	finally
    //   266	280	416	android/database/sqlite/SQLiteException
    //   266	280	408	finally
    //   280	292	416	android/database/sqlite/SQLiteException
    //   280	292	408	finally
    //   292	306	416	android/database/sqlite/SQLiteException
    //   292	306	408	finally
    //   309	316	416	android/database/sqlite/SQLiteException
    //   309	316	408	finally
    //   322	327	416	android/database/sqlite/SQLiteException
    //   322	327	408	finally
    //   331	353	403	android/database/sqlite/SQLiteException
    //   331	353	478	finally
    //   357	367	403	android/database/sqlite/SQLiteException
    //   357	367	478	finally
    //   371	388	403	android/database/sqlite/SQLiteException
    //   371	388	478	finally
    //   437	464	478	finally
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  public final void zzh(String paramString1, String paramString2) {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString2);
    zzaf();
    zzcl();
    try {
      int i = getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[] { paramString1, paramString2 });
      zzgt().zzjo().zzg("Deleted user attribute rows", Integer.valueOf(i));
      return;
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zzd("Error deleting user attribute. appId", zzas.zzbw(paramString1), zzgq().zzbv(paramString2), sQLiteException);
      return;
    } 
  }
  
  public final zzfw zzi(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_2
    //   6: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   9: pop
    //   10: aload_0
    //   11: invokevirtual zzaf : ()V
    //   14: aload_0
    //   15: invokevirtual zzcl : ()V
    //   18: aload_0
    //   19: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   22: ldc_w 'user_attributes'
    //   25: iconst_3
    //   26: anewarray java/lang/String
    //   29: dup
    //   30: iconst_0
    //   31: ldc_w 'set_timestamp'
    //   34: aastore
    //   35: dup
    //   36: iconst_1
    //   37: ldc_w 'value'
    //   40: aastore
    //   41: dup
    //   42: iconst_2
    //   43: ldc 'origin'
    //   45: aastore
    //   46: ldc_w 'app_id=? and name=?'
    //   49: iconst_2
    //   50: anewarray java/lang/String
    //   53: dup
    //   54: iconst_0
    //   55: aload_1
    //   56: aastore
    //   57: dup
    //   58: iconst_1
    //   59: aload_2
    //   60: aastore
    //   61: aconst_null
    //   62: aconst_null
    //   63: aconst_null
    //   64: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   67: astore #6
    //   69: aload #6
    //   71: invokeinterface moveToFirst : ()Z
    //   76: istore_3
    //   77: iload_3
    //   78: ifne -> 95
    //   81: aload #6
    //   83: ifnull -> 93
    //   86: aload #6
    //   88: invokeinterface close : ()V
    //   93: aconst_null
    //   94: areturn
    //   95: aload #6
    //   97: iconst_0
    //   98: invokeinterface getLong : (I)J
    //   103: lstore #4
    //   105: aload #6
    //   107: astore #7
    //   109: aload_0
    //   110: aload #6
    //   112: iconst_1
    //   113: invokespecial zza : (Landroid/database/Cursor;I)Ljava/lang/Object;
    //   116: astore #9
    //   118: aload #6
    //   120: astore #7
    //   122: aload #6
    //   124: iconst_2
    //   125: invokeinterface getString : (I)Ljava/lang/String;
    //   130: astore #8
    //   132: aload #6
    //   134: astore #7
    //   136: new com/google/android/gms/measurement/internal/zzfw
    //   139: astore #10
    //   141: aload #6
    //   143: astore #7
    //   145: aload #10
    //   147: aload_1
    //   148: aload #8
    //   150: aload_2
    //   151: lload #4
    //   153: aload #9
    //   155: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   158: aload #6
    //   160: astore #7
    //   162: aload #6
    //   164: invokeinterface moveToNext : ()Z
    //   169: ifeq -> 193
    //   172: aload #6
    //   174: astore #7
    //   176: aload_0
    //   177: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   180: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   183: ldc_w 'Got multiple records for user property, expected one. appId'
    //   186: aload_1
    //   187: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   190: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   193: aload #6
    //   195: ifnull -> 205
    //   198: aload #6
    //   200: invokeinterface close : ()V
    //   205: aload #10
    //   207: areturn
    //   208: astore #8
    //   210: goto -> 234
    //   213: astore_1
    //   214: goto -> 284
    //   217: astore #8
    //   219: goto -> 234
    //   222: astore_1
    //   223: aconst_null
    //   224: astore #6
    //   226: goto -> 284
    //   229: astore #8
    //   231: aconst_null
    //   232: astore #6
    //   234: aload #6
    //   236: astore #7
    //   238: aload_0
    //   239: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   242: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   245: ldc_w 'Error querying user property. appId'
    //   248: aload_1
    //   249: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   252: aload_0
    //   253: invokevirtual zzgq : ()Lcom/google/android/gms/measurement/internal/zzaq;
    //   256: aload_2
    //   257: invokevirtual zzbv : (Ljava/lang/String;)Ljava/lang/String;
    //   260: aload #8
    //   262: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   265: aload #6
    //   267: ifnull -> 277
    //   270: aload #6
    //   272: invokeinterface close : ()V
    //   277: aconst_null
    //   278: areturn
    //   279: astore_1
    //   280: aload #7
    //   282: astore #6
    //   284: aload #6
    //   286: ifnull -> 296
    //   289: aload #6
    //   291: invokeinterface close : ()V
    //   296: aload_1
    //   297: athrow
    // Exception table:
    //   from	to	target	type
    //   18	69	229	android/database/sqlite/SQLiteException
    //   18	69	222	finally
    //   69	77	217	android/database/sqlite/SQLiteException
    //   69	77	213	finally
    //   95	105	217	android/database/sqlite/SQLiteException
    //   95	105	213	finally
    //   109	118	208	android/database/sqlite/SQLiteException
    //   109	118	279	finally
    //   122	132	208	android/database/sqlite/SQLiteException
    //   122	132	279	finally
    //   136	141	208	android/database/sqlite/SQLiteException
    //   136	141	279	finally
    //   145	158	208	android/database/sqlite/SQLiteException
    //   145	158	279	finally
    //   162	172	208	android/database/sqlite/SQLiteException
    //   162	172	279	finally
    //   176	193	208	android/database/sqlite/SQLiteException
    //   176	193	279	finally
    //   238	265	279	finally
  }
  
  public final String zzih() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   4: astore_1
    //   5: aload_1
    //   6: ldc_w 'select app_id from queue order by has_realtime desc, rowid asc limit 1;'
    //   9: aconst_null
    //   10: invokevirtual rawQuery : (Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   13: astore_2
    //   14: aload_2
    //   15: astore_1
    //   16: aload_2
    //   17: invokeinterface moveToFirst : ()Z
    //   22: ifeq -> 47
    //   25: aload_2
    //   26: astore_1
    //   27: aload_2
    //   28: iconst_0
    //   29: invokeinterface getString : (I)Ljava/lang/String;
    //   34: astore_3
    //   35: aload_2
    //   36: ifnull -> 45
    //   39: aload_2
    //   40: invokeinterface close : ()V
    //   45: aload_3
    //   46: areturn
    //   47: aload_2
    //   48: ifnull -> 57
    //   51: aload_2
    //   52: invokeinterface close : ()V
    //   57: aconst_null
    //   58: areturn
    //   59: astore_3
    //   60: goto -> 72
    //   63: astore_2
    //   64: aconst_null
    //   65: astore_1
    //   66: goto -> 101
    //   69: astore_3
    //   70: aconst_null
    //   71: astore_2
    //   72: aload_2
    //   73: astore_1
    //   74: aload_0
    //   75: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   78: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   81: ldc_w 'Database error getting next bundle app id'
    //   84: aload_3
    //   85: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   88: aload_2
    //   89: ifnull -> 98
    //   92: aload_2
    //   93: invokeinterface close : ()V
    //   98: aconst_null
    //   99: areturn
    //   100: astore_2
    //   101: aload_1
    //   102: ifnull -> 111
    //   105: aload_1
    //   106: invokeinterface close : ()V
    //   111: aload_2
    //   112: athrow
    // Exception table:
    //   from	to	target	type
    //   5	14	69	android/database/sqlite/SQLiteException
    //   5	14	63	finally
    //   16	25	59	android/database/sqlite/SQLiteException
    //   16	25	100	finally
    //   27	35	59	android/database/sqlite/SQLiteException
    //   27	35	100	finally
    //   74	88	100	finally
  }
  
  public final boolean zzii() {
    return (zza("select count(1) > 0 from queue where has_realtime = 1", (String[])null) != 0L);
  }
  
  final void zzij() {
    zzaf();
    zzcl();
    if (!zzip())
      return; 
    long l2 = (zzgu()).zzanf.get();
    long l1 = zzbx().elapsedRealtime();
    if (Math.abs(l1 - l2) > ((Long)zzai.zzajs.get()).longValue()) {
      (zzgu()).zzanf.set(l1);
      zzaf();
      zzcl();
      if (zzip()) {
        int i = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[] { String.valueOf(zzbx().currentTimeMillis()), String.valueOf(zzq.zzib()) });
        if (i > 0)
          zzgt().zzjo().zzg("Deleted stale rows. rowsDeleted", Integer.valueOf(i)); 
      } 
    } 
  }
  
  public final long zzik() {
    return zza("select max(bundle_end_timestamp) from queue", (String[])null, 0L);
  }
  
  public final long zzil() {
    return zza("select max(timestamp) from raw_events", (String[])null, 0L);
  }
  
  public final boolean zzim() {
    return (zza("select count(1) > 0 from raw_events", (String[])null) != 0L);
  }
  
  public final boolean zzin() {
    return (zza("select count(1) > 0 from raw_events where realtime = 1", (String[])null) != 0L);
  }
  
  public final long zzio() {
    Cursor cursor2 = null;
    Cursor cursor1 = null;
    try {
      Cursor cursor = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
      cursor1 = cursor;
      cursor2 = cursor;
      boolean bool = cursor.moveToFirst();
      if (!bool) {
        if (cursor != null)
          cursor.close(); 
        return -1L;
      } 
      cursor1 = cursor;
      cursor2 = cursor;
      long l = cursor.getLong(0);
      if (cursor != null)
        cursor.close(); 
      return l;
    } catch (SQLiteException sQLiteException) {
      cursor1 = cursor2;
      zzgt().zzjg().zzg("Error querying raw events", sQLiteException);
      if (cursor2 != null)
        cursor2.close(); 
      return -1L;
    } finally {}
    if (cursor1 != null)
      cursor1.close(); 
    throw cursor2;
  }
  
  public final zzo zzj(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_2
    //   6: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   9: pop
    //   10: aload_0
    //   11: invokevirtual zzaf : ()V
    //   14: aload_0
    //   15: invokevirtual zzcl : ()V
    //   18: aload_0
    //   19: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   22: ldc_w 'conditional_properties'
    //   25: bipush #11
    //   27: anewarray java/lang/String
    //   30: dup
    //   31: iconst_0
    //   32: ldc 'origin'
    //   34: aastore
    //   35: dup
    //   36: iconst_1
    //   37: ldc_w 'value'
    //   40: aastore
    //   41: dup
    //   42: iconst_2
    //   43: ldc_w 'active'
    //   46: aastore
    //   47: dup
    //   48: iconst_3
    //   49: ldc_w 'trigger_event_name'
    //   52: aastore
    //   53: dup
    //   54: iconst_4
    //   55: ldc_w 'trigger_timeout'
    //   58: aastore
    //   59: dup
    //   60: iconst_5
    //   61: ldc_w 'timed_out_event'
    //   64: aastore
    //   65: dup
    //   66: bipush #6
    //   68: ldc_w 'creation_timestamp'
    //   71: aastore
    //   72: dup
    //   73: bipush #7
    //   75: ldc_w 'triggered_event'
    //   78: aastore
    //   79: dup
    //   80: bipush #8
    //   82: ldc_w 'triggered_timestamp'
    //   85: aastore
    //   86: dup
    //   87: bipush #9
    //   89: ldc_w 'time_to_live'
    //   92: aastore
    //   93: dup
    //   94: bipush #10
    //   96: ldc_w 'expired_event'
    //   99: aastore
    //   100: ldc_w 'app_id=? and name=?'
    //   103: iconst_2
    //   104: anewarray java/lang/String
    //   107: dup
    //   108: iconst_0
    //   109: aload_1
    //   110: aastore
    //   111: dup
    //   112: iconst_1
    //   113: aload_2
    //   114: aastore
    //   115: aconst_null
    //   116: aconst_null
    //   117: aconst_null
    //   118: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   121: astore #12
    //   123: aload #12
    //   125: invokeinterface moveToFirst : ()Z
    //   130: istore #11
    //   132: iload #11
    //   134: ifne -> 151
    //   137: aload #12
    //   139: ifnull -> 149
    //   142: aload #12
    //   144: invokeinterface close : ()V
    //   149: aconst_null
    //   150: areturn
    //   151: aload #12
    //   153: iconst_0
    //   154: invokeinterface getString : (I)Ljava/lang/String;
    //   159: astore #19
    //   161: aload #12
    //   163: astore #13
    //   165: aload_0
    //   166: aload #12
    //   168: iconst_1
    //   169: invokespecial zza : (Landroid/database/Cursor;I)Ljava/lang/Object;
    //   172: astore #20
    //   174: aload #12
    //   176: astore #13
    //   178: aload #12
    //   180: iconst_2
    //   181: invokeinterface getInt : (I)I
    //   186: ifeq -> 195
    //   189: iconst_1
    //   190: istore #11
    //   192: goto -> 198
    //   195: iconst_0
    //   196: istore #11
    //   198: aload #12
    //   200: astore #13
    //   202: aload #12
    //   204: iconst_3
    //   205: invokeinterface getString : (I)Ljava/lang/String;
    //   210: astore #15
    //   212: aload #12
    //   214: astore #13
    //   216: aload #12
    //   218: iconst_4
    //   219: invokeinterface getLong : (I)J
    //   224: lstore #5
    //   226: aload #12
    //   228: astore #13
    //   230: aload_0
    //   231: invokevirtual zzjr : ()Lcom/google/android/gms/measurement/internal/zzft;
    //   234: aload #12
    //   236: iconst_5
    //   237: invokeinterface getBlob : (I)[B
    //   242: getstatic com/google/android/gms/measurement/internal/zzag.CREATOR : Landroid/os/Parcelable$Creator;
    //   245: invokevirtual zza : ([BLandroid/os/Parcelable$Creator;)Landroid/os/Parcelable;
    //   248: checkcast com/google/android/gms/measurement/internal/zzag
    //   251: astore #18
    //   253: aload #12
    //   255: astore #13
    //   257: aload #12
    //   259: bipush #6
    //   261: invokeinterface getLong : (I)J
    //   266: lstore #9
    //   268: aload #12
    //   270: astore #13
    //   272: aload_0
    //   273: invokevirtual zzjr : ()Lcom/google/android/gms/measurement/internal/zzft;
    //   276: aload #12
    //   278: bipush #7
    //   280: invokeinterface getBlob : (I)[B
    //   285: getstatic com/google/android/gms/measurement/internal/zzag.CREATOR : Landroid/os/Parcelable$Creator;
    //   288: invokevirtual zza : ([BLandroid/os/Parcelable$Creator;)Landroid/os/Parcelable;
    //   291: checkcast com/google/android/gms/measurement/internal/zzag
    //   294: astore #14
    //   296: aload #12
    //   298: astore #13
    //   300: aload #12
    //   302: bipush #8
    //   304: invokeinterface getLong : (I)J
    //   309: lstore_3
    //   310: aload #12
    //   312: astore #13
    //   314: aload #12
    //   316: bipush #9
    //   318: invokeinterface getLong : (I)J
    //   323: lstore #7
    //   325: aload #12
    //   327: astore #13
    //   329: aload_0
    //   330: invokevirtual zzjr : ()Lcom/google/android/gms/measurement/internal/zzft;
    //   333: aload #12
    //   335: bipush #10
    //   337: invokeinterface getBlob : (I)[B
    //   342: getstatic com/google/android/gms/measurement/internal/zzag.CREATOR : Landroid/os/Parcelable$Creator;
    //   345: invokevirtual zza : ([BLandroid/os/Parcelable$Creator;)Landroid/os/Parcelable;
    //   348: checkcast com/google/android/gms/measurement/internal/zzag
    //   351: astore #17
    //   353: aload #12
    //   355: astore #13
    //   357: new com/google/android/gms/measurement/internal/zzfu
    //   360: astore #16
    //   362: aload #12
    //   364: astore #13
    //   366: aload #16
    //   368: aload_2
    //   369: lload_3
    //   370: aload #20
    //   372: aload #19
    //   374: invokespecial <init> : (Ljava/lang/String;JLjava/lang/Object;Ljava/lang/String;)V
    //   377: aload #12
    //   379: astore #13
    //   381: new com/google/android/gms/measurement/internal/zzo
    //   384: astore #20
    //   386: aload #12
    //   388: astore #13
    //   390: aload #20
    //   392: aload_1
    //   393: aload #19
    //   395: aload #16
    //   397: lload #9
    //   399: iload #11
    //   401: aload #15
    //   403: aload #18
    //   405: lload #5
    //   407: aload #14
    //   409: lload #7
    //   411: aload #17
    //   413: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzfu;JZLjava/lang/String;Lcom/google/android/gms/measurement/internal/zzag;JLcom/google/android/gms/measurement/internal/zzag;JLcom/google/android/gms/measurement/internal/zzag;)V
    //   416: aload #12
    //   418: astore #13
    //   420: aload #12
    //   422: invokeinterface moveToNext : ()Z
    //   427: ifeq -> 459
    //   430: aload #12
    //   432: astore #13
    //   434: aload_0
    //   435: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   438: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   441: ldc_w 'Got multiple records for conditional property, expected one'
    //   444: aload_1
    //   445: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   448: aload_0
    //   449: invokevirtual zzgq : ()Lcom/google/android/gms/measurement/internal/zzaq;
    //   452: aload_2
    //   453: invokevirtual zzbv : (Ljava/lang/String;)Ljava/lang/String;
    //   456: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   459: aload #12
    //   461: ifnull -> 471
    //   464: aload #12
    //   466: invokeinterface close : ()V
    //   471: aload #20
    //   473: areturn
    //   474: astore #14
    //   476: goto -> 500
    //   479: astore_1
    //   480: goto -> 550
    //   483: astore #14
    //   485: goto -> 500
    //   488: astore_1
    //   489: aconst_null
    //   490: astore #12
    //   492: goto -> 550
    //   495: astore #14
    //   497: aconst_null
    //   498: astore #12
    //   500: aload #12
    //   502: astore #13
    //   504: aload_0
    //   505: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   508: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   511: ldc_w 'Error querying conditional property'
    //   514: aload_1
    //   515: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   518: aload_0
    //   519: invokevirtual zzgq : ()Lcom/google/android/gms/measurement/internal/zzaq;
    //   522: aload_2
    //   523: invokevirtual zzbv : (Ljava/lang/String;)Ljava/lang/String;
    //   526: aload #14
    //   528: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   531: aload #12
    //   533: ifnull -> 543
    //   536: aload #12
    //   538: invokeinterface close : ()V
    //   543: aconst_null
    //   544: areturn
    //   545: astore_1
    //   546: aload #13
    //   548: astore #12
    //   550: aload #12
    //   552: ifnull -> 562
    //   555: aload #12
    //   557: invokeinterface close : ()V
    //   562: aload_1
    //   563: athrow
    // Exception table:
    //   from	to	target	type
    //   18	123	495	android/database/sqlite/SQLiteException
    //   18	123	488	finally
    //   123	132	483	android/database/sqlite/SQLiteException
    //   123	132	479	finally
    //   151	161	483	android/database/sqlite/SQLiteException
    //   151	161	479	finally
    //   165	174	474	android/database/sqlite/SQLiteException
    //   165	174	545	finally
    //   178	189	474	android/database/sqlite/SQLiteException
    //   178	189	545	finally
    //   202	212	474	android/database/sqlite/SQLiteException
    //   202	212	545	finally
    //   216	226	474	android/database/sqlite/SQLiteException
    //   216	226	545	finally
    //   230	253	474	android/database/sqlite/SQLiteException
    //   230	253	545	finally
    //   257	268	474	android/database/sqlite/SQLiteException
    //   257	268	545	finally
    //   272	296	474	android/database/sqlite/SQLiteException
    //   272	296	545	finally
    //   300	310	474	android/database/sqlite/SQLiteException
    //   300	310	545	finally
    //   314	325	474	android/database/sqlite/SQLiteException
    //   314	325	545	finally
    //   329	353	474	android/database/sqlite/SQLiteException
    //   329	353	545	finally
    //   357	362	474	android/database/sqlite/SQLiteException
    //   357	362	545	finally
    //   366	377	474	android/database/sqlite/SQLiteException
    //   366	377	545	finally
    //   381	386	474	android/database/sqlite/SQLiteException
    //   381	386	545	finally
    //   390	416	474	android/database/sqlite/SQLiteException
    //   390	416	545	finally
    //   420	430	474	android/database/sqlite/SQLiteException
    //   420	430	545	finally
    //   434	459	474	android/database/sqlite/SQLiteException
    //   434	459	545	finally
    //   504	531	545	finally
  }
  
  public final int zzk(String paramString1, String paramString2) {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString2);
    zzaf();
    zzcl();
    try {
      return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[] { paramString1, paramString2 });
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zzd("Error deleting conditional property", zzas.zzbw(paramString1), zzgq().zzbv(paramString2), sQLiteException);
      return 0;
    } 
  }
  
  final Map<Integer, List<zzfj>> zzl(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzcl : ()V
    //   4: aload_0
    //   5: invokevirtual zzaf : ()V
    //   8: aload_1
    //   9: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   12: pop
    //   13: aload_2
    //   14: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   17: pop
    //   18: new androidx/collection/ArrayMap
    //   21: dup
    //   22: invokespecial <init> : ()V
    //   25: astore #8
    //   27: aload_0
    //   28: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   31: astore #5
    //   33: aload #5
    //   35: ldc_w 'event_filters'
    //   38: iconst_2
    //   39: anewarray java/lang/String
    //   42: dup
    //   43: iconst_0
    //   44: ldc_w 'audience_id'
    //   47: aastore
    //   48: dup
    //   49: iconst_1
    //   50: ldc_w 'data'
    //   53: aastore
    //   54: ldc_w 'app_id=? AND event_name=?'
    //   57: iconst_2
    //   58: anewarray java/lang/String
    //   61: dup
    //   62: iconst_0
    //   63: aload_1
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: aload_2
    //   68: aastore
    //   69: aconst_null
    //   70: aconst_null
    //   71: aconst_null
    //   72: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   75: astore #5
    //   77: aload #5
    //   79: astore_2
    //   80: aload #5
    //   82: invokeinterface moveToFirst : ()Z
    //   87: ifne -> 113
    //   90: aload #5
    //   92: astore_2
    //   93: invokestatic emptyMap : ()Ljava/util/Map;
    //   96: astore #6
    //   98: aload #5
    //   100: ifnull -> 110
    //   103: aload #5
    //   105: invokeinterface close : ()V
    //   110: aload #6
    //   112: areturn
    //   113: aload #5
    //   115: astore_2
    //   116: aload #5
    //   118: iconst_1
    //   119: invokeinterface getBlob : (I)[B
    //   124: astore #6
    //   126: aload #5
    //   128: astore_2
    //   129: aload #6
    //   131: iconst_0
    //   132: aload #6
    //   134: arraylength
    //   135: invokestatic zzj : ([BII)Lcom/google/android/gms/internal/measurement/zzxz;
    //   138: astore #6
    //   140: aload #5
    //   142: astore_2
    //   143: new com/google/android/gms/internal/measurement/zzfj
    //   146: astore #9
    //   148: aload #5
    //   150: astore_2
    //   151: aload #9
    //   153: invokespecial <init> : ()V
    //   156: aload #5
    //   158: astore_2
    //   159: aload #9
    //   161: aload #6
    //   163: invokevirtual zza : (Lcom/google/android/gms/internal/measurement/zzxz;)Lcom/google/android/gms/internal/measurement/zzyi;
    //   166: pop
    //   167: aload #5
    //   169: astore_2
    //   170: aload #5
    //   172: iconst_0
    //   173: invokeinterface getInt : (I)I
    //   178: istore_3
    //   179: aload #5
    //   181: astore_2
    //   182: aload #8
    //   184: iload_3
    //   185: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   188: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   193: checkcast java/util/List
    //   196: astore #7
    //   198: aload #7
    //   200: astore #6
    //   202: aload #7
    //   204: ifnonnull -> 240
    //   207: aload #5
    //   209: astore_2
    //   210: new java/util/ArrayList
    //   213: astore #6
    //   215: aload #5
    //   217: astore_2
    //   218: aload #6
    //   220: invokespecial <init> : ()V
    //   223: aload #5
    //   225: astore_2
    //   226: aload #8
    //   228: iload_3
    //   229: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   232: aload #6
    //   234: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   239: pop
    //   240: aload #5
    //   242: astore_2
    //   243: aload #6
    //   245: aload #9
    //   247: invokeinterface add : (Ljava/lang/Object;)Z
    //   252: pop
    //   253: goto -> 280
    //   256: astore #6
    //   258: aload #5
    //   260: astore_2
    //   261: aload_0
    //   262: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   265: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   268: ldc_w 'Failed to merge filter. appId'
    //   271: aload_1
    //   272: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   275: aload #6
    //   277: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   280: aload #5
    //   282: astore_2
    //   283: aload #5
    //   285: invokeinterface moveToNext : ()Z
    //   290: istore #4
    //   292: iload #4
    //   294: ifne -> 113
    //   297: aload #5
    //   299: ifnull -> 309
    //   302: aload #5
    //   304: invokeinterface close : ()V
    //   309: aload #8
    //   311: areturn
    //   312: astore #6
    //   314: goto -> 328
    //   317: astore_1
    //   318: aconst_null
    //   319: astore_2
    //   320: goto -> 365
    //   323: astore #6
    //   325: aconst_null
    //   326: astore #5
    //   328: aload #5
    //   330: astore_2
    //   331: aload_0
    //   332: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   335: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   338: ldc_w 'Database error querying filters. appId'
    //   341: aload_1
    //   342: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   345: aload #6
    //   347: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   350: aload #5
    //   352: ifnull -> 362
    //   355: aload #5
    //   357: invokeinterface close : ()V
    //   362: aconst_null
    //   363: areturn
    //   364: astore_1
    //   365: aload_2
    //   366: ifnull -> 375
    //   369: aload_2
    //   370: invokeinterface close : ()V
    //   375: aload_1
    //   376: athrow
    // Exception table:
    //   from	to	target	type
    //   33	77	323	android/database/sqlite/SQLiteException
    //   33	77	317	finally
    //   80	90	312	android/database/sqlite/SQLiteException
    //   80	90	364	finally
    //   93	98	312	android/database/sqlite/SQLiteException
    //   93	98	364	finally
    //   116	126	312	android/database/sqlite/SQLiteException
    //   116	126	364	finally
    //   129	140	312	android/database/sqlite/SQLiteException
    //   129	140	364	finally
    //   143	148	312	android/database/sqlite/SQLiteException
    //   143	148	364	finally
    //   151	156	312	android/database/sqlite/SQLiteException
    //   151	156	364	finally
    //   159	167	256	java/io/IOException
    //   159	167	312	android/database/sqlite/SQLiteException
    //   159	167	364	finally
    //   170	179	312	android/database/sqlite/SQLiteException
    //   170	179	364	finally
    //   182	198	312	android/database/sqlite/SQLiteException
    //   182	198	364	finally
    //   210	215	312	android/database/sqlite/SQLiteException
    //   210	215	364	finally
    //   218	223	312	android/database/sqlite/SQLiteException
    //   218	223	364	finally
    //   226	240	312	android/database/sqlite/SQLiteException
    //   226	240	364	finally
    //   243	253	312	android/database/sqlite/SQLiteException
    //   243	253	364	finally
    //   261	280	312	android/database/sqlite/SQLiteException
    //   261	280	364	finally
    //   283	292	312	android/database/sqlite/SQLiteException
    //   283	292	364	finally
    //   331	350	364	finally
  }
  
  final Map<Integer, List<zzfm>> zzm(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzcl : ()V
    //   4: aload_0
    //   5: invokevirtual zzaf : ()V
    //   8: aload_1
    //   9: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   12: pop
    //   13: aload_2
    //   14: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   17: pop
    //   18: new androidx/collection/ArrayMap
    //   21: dup
    //   22: invokespecial <init> : ()V
    //   25: astore #8
    //   27: aload_0
    //   28: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   31: astore #5
    //   33: aload #5
    //   35: ldc_w 'property_filters'
    //   38: iconst_2
    //   39: anewarray java/lang/String
    //   42: dup
    //   43: iconst_0
    //   44: ldc_w 'audience_id'
    //   47: aastore
    //   48: dup
    //   49: iconst_1
    //   50: ldc_w 'data'
    //   53: aastore
    //   54: ldc_w 'app_id=? AND property_name=?'
    //   57: iconst_2
    //   58: anewarray java/lang/String
    //   61: dup
    //   62: iconst_0
    //   63: aload_1
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: aload_2
    //   68: aastore
    //   69: aconst_null
    //   70: aconst_null
    //   71: aconst_null
    //   72: invokevirtual query : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   75: astore #5
    //   77: aload #5
    //   79: astore_2
    //   80: aload #5
    //   82: invokeinterface moveToFirst : ()Z
    //   87: ifne -> 113
    //   90: aload #5
    //   92: astore_2
    //   93: invokestatic emptyMap : ()Ljava/util/Map;
    //   96: astore #6
    //   98: aload #5
    //   100: ifnull -> 110
    //   103: aload #5
    //   105: invokeinterface close : ()V
    //   110: aload #6
    //   112: areturn
    //   113: aload #5
    //   115: astore_2
    //   116: aload #5
    //   118: iconst_1
    //   119: invokeinterface getBlob : (I)[B
    //   124: astore #6
    //   126: aload #5
    //   128: astore_2
    //   129: aload #6
    //   131: iconst_0
    //   132: aload #6
    //   134: arraylength
    //   135: invokestatic zzj : ([BII)Lcom/google/android/gms/internal/measurement/zzxz;
    //   138: astore #6
    //   140: aload #5
    //   142: astore_2
    //   143: new com/google/android/gms/internal/measurement/zzfm
    //   146: astore #9
    //   148: aload #5
    //   150: astore_2
    //   151: aload #9
    //   153: invokespecial <init> : ()V
    //   156: aload #5
    //   158: astore_2
    //   159: aload #9
    //   161: aload #6
    //   163: invokevirtual zza : (Lcom/google/android/gms/internal/measurement/zzxz;)Lcom/google/android/gms/internal/measurement/zzyi;
    //   166: pop
    //   167: aload #5
    //   169: astore_2
    //   170: aload #5
    //   172: iconst_0
    //   173: invokeinterface getInt : (I)I
    //   178: istore_3
    //   179: aload #5
    //   181: astore_2
    //   182: aload #8
    //   184: iload_3
    //   185: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   188: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   193: checkcast java/util/List
    //   196: astore #7
    //   198: aload #7
    //   200: astore #6
    //   202: aload #7
    //   204: ifnonnull -> 240
    //   207: aload #5
    //   209: astore_2
    //   210: new java/util/ArrayList
    //   213: astore #6
    //   215: aload #5
    //   217: astore_2
    //   218: aload #6
    //   220: invokespecial <init> : ()V
    //   223: aload #5
    //   225: astore_2
    //   226: aload #8
    //   228: iload_3
    //   229: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   232: aload #6
    //   234: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   239: pop
    //   240: aload #5
    //   242: astore_2
    //   243: aload #6
    //   245: aload #9
    //   247: invokeinterface add : (Ljava/lang/Object;)Z
    //   252: pop
    //   253: goto -> 280
    //   256: astore #6
    //   258: aload #5
    //   260: astore_2
    //   261: aload_0
    //   262: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   265: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   268: ldc_w 'Failed to merge filter'
    //   271: aload_1
    //   272: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   275: aload #6
    //   277: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   280: aload #5
    //   282: astore_2
    //   283: aload #5
    //   285: invokeinterface moveToNext : ()Z
    //   290: istore #4
    //   292: iload #4
    //   294: ifne -> 113
    //   297: aload #5
    //   299: ifnull -> 309
    //   302: aload #5
    //   304: invokeinterface close : ()V
    //   309: aload #8
    //   311: areturn
    //   312: astore #6
    //   314: goto -> 328
    //   317: astore_1
    //   318: aconst_null
    //   319: astore_2
    //   320: goto -> 365
    //   323: astore #6
    //   325: aconst_null
    //   326: astore #5
    //   328: aload #5
    //   330: astore_2
    //   331: aload_0
    //   332: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   335: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   338: ldc_w 'Database error querying filters. appId'
    //   341: aload_1
    //   342: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   345: aload #6
    //   347: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   350: aload #5
    //   352: ifnull -> 362
    //   355: aload #5
    //   357: invokeinterface close : ()V
    //   362: aconst_null
    //   363: areturn
    //   364: astore_1
    //   365: aload_2
    //   366: ifnull -> 375
    //   369: aload_2
    //   370: invokeinterface close : ()V
    //   375: aload_1
    //   376: athrow
    // Exception table:
    //   from	to	target	type
    //   33	77	323	android/database/sqlite/SQLiteException
    //   33	77	317	finally
    //   80	90	312	android/database/sqlite/SQLiteException
    //   80	90	364	finally
    //   93	98	312	android/database/sqlite/SQLiteException
    //   93	98	364	finally
    //   116	126	312	android/database/sqlite/SQLiteException
    //   116	126	364	finally
    //   129	140	312	android/database/sqlite/SQLiteException
    //   129	140	364	finally
    //   143	148	312	android/database/sqlite/SQLiteException
    //   143	148	364	finally
    //   151	156	312	android/database/sqlite/SQLiteException
    //   151	156	364	finally
    //   159	167	256	java/io/IOException
    //   159	167	312	android/database/sqlite/SQLiteException
    //   159	167	364	finally
    //   170	179	312	android/database/sqlite/SQLiteException
    //   170	179	364	finally
    //   182	198	312	android/database/sqlite/SQLiteException
    //   182	198	364	finally
    //   210	215	312	android/database/sqlite/SQLiteException
    //   210	215	364	finally
    //   218	223	312	android/database/sqlite/SQLiteException
    //   218	223	364	finally
    //   226	240	312	android/database/sqlite/SQLiteException
    //   226	240	364	finally
    //   243	253	312	android/database/sqlite/SQLiteException
    //   243	253	364	finally
    //   261	280	312	android/database/sqlite/SQLiteException
    //   261	280	364	finally
    //   283	292	312	android/database/sqlite/SQLiteException
    //   283	292	364	finally
    //   331	350	364	finally
  }
  
  protected final long zzn(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_2
    //   6: invokestatic checkNotEmpty : (Ljava/lang/String;)Ljava/lang/String;
    //   9: pop
    //   10: aload_0
    //   11: invokevirtual zzaf : ()V
    //   14: aload_0
    //   15: invokevirtual zzcl : ()V
    //   18: aload_0
    //   19: invokevirtual getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   22: astore #9
    //   24: aload #9
    //   26: invokevirtual beginTransaction : ()V
    //   29: aload_2
    //   30: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   33: invokevirtual length : ()I
    //   36: istore_3
    //   37: new java/lang/StringBuilder
    //   40: astore #8
    //   42: aload #8
    //   44: iload_3
    //   45: bipush #32
    //   47: iadd
    //   48: invokespecial <init> : (I)V
    //   51: aload #8
    //   53: ldc_w 'select '
    //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload #8
    //   62: aload_2
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload #8
    //   69: ldc_w ' from app2 where app_id=?'
    //   72: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: aload #8
    //   78: invokevirtual toString : ()Ljava/lang/String;
    //   81: astore #8
    //   83: aload_0
    //   84: aload #8
    //   86: iconst_1
    //   87: anewarray java/lang/String
    //   90: dup
    //   91: iconst_0
    //   92: aload_1
    //   93: aastore
    //   94: ldc2_w -1
    //   97: invokespecial zza : (Ljava/lang/String;[Ljava/lang/String;J)J
    //   100: lstore #6
    //   102: lload #6
    //   104: lstore #4
    //   106: lload #6
    //   108: ldc2_w -1
    //   111: lcmp
    //   112: ifne -> 206
    //   115: new android/content/ContentValues
    //   118: astore #8
    //   120: aload #8
    //   122: invokespecial <init> : ()V
    //   125: aload #8
    //   127: ldc_w 'app_id'
    //   130: aload_1
    //   131: invokevirtual put : (Ljava/lang/String;Ljava/lang/String;)V
    //   134: aload #8
    //   136: ldc_w 'first_open_count'
    //   139: iconst_0
    //   140: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   143: invokevirtual put : (Ljava/lang/String;Ljava/lang/Integer;)V
    //   146: aload #8
    //   148: ldc 'previous_install_count'
    //   150: iconst_0
    //   151: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   154: invokevirtual put : (Ljava/lang/String;Ljava/lang/Integer;)V
    //   157: aload #9
    //   159: ldc_w 'app2'
    //   162: aconst_null
    //   163: aload #8
    //   165: iconst_5
    //   166: invokevirtual insertWithOnConflict : (Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
    //   169: ldc2_w -1
    //   172: lcmp
    //   173: ifne -> 203
    //   176: aload_0
    //   177: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   180: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   183: ldc_w 'Failed to insert column (got -1). appId'
    //   186: aload_1
    //   187: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   190: aload_2
    //   191: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   194: aload #9
    //   196: invokevirtual endTransaction : ()V
    //   199: ldc2_w -1
    //   202: lreturn
    //   203: lconst_0
    //   204: lstore #4
    //   206: new android/content/ContentValues
    //   209: astore #8
    //   211: aload #8
    //   213: invokespecial <init> : ()V
    //   216: aload #8
    //   218: ldc_w 'app_id'
    //   221: aload_1
    //   222: invokevirtual put : (Ljava/lang/String;Ljava/lang/String;)V
    //   225: aload #8
    //   227: aload_2
    //   228: lconst_1
    //   229: lload #4
    //   231: ladd
    //   232: invokestatic valueOf : (J)Ljava/lang/Long;
    //   235: invokevirtual put : (Ljava/lang/String;Ljava/lang/Long;)V
    //   238: aload #9
    //   240: ldc_w 'app2'
    //   243: aload #8
    //   245: ldc_w 'app_id = ?'
    //   248: iconst_1
    //   249: anewarray java/lang/String
    //   252: dup
    //   253: iconst_0
    //   254: aload_1
    //   255: aastore
    //   256: invokevirtual update : (Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   259: i2l
    //   260: lconst_0
    //   261: lcmp
    //   262: ifne -> 292
    //   265: aload_0
    //   266: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   269: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   272: ldc_w 'Failed to update column (got 0). appId'
    //   275: aload_1
    //   276: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   279: aload_2
    //   280: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   283: aload #9
    //   285: invokevirtual endTransaction : ()V
    //   288: ldc2_w -1
    //   291: lreturn
    //   292: aload #9
    //   294: invokevirtual setTransactionSuccessful : ()V
    //   297: aload #9
    //   299: invokevirtual endTransaction : ()V
    //   302: goto -> 349
    //   305: astore #8
    //   307: goto -> 324
    //   310: astore #8
    //   312: goto -> 321
    //   315: astore_1
    //   316: goto -> 353
    //   319: astore #8
    //   321: lconst_0
    //   322: lstore #4
    //   324: aload_0
    //   325: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   328: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   331: ldc_w 'Error inserting column. appId'
    //   334: aload_1
    //   335: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   338: aload_2
    //   339: aload #8
    //   341: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   344: aload #9
    //   346: invokevirtual endTransaction : ()V
    //   349: lload #4
    //   351: lreturn
    //   352: astore_1
    //   353: aload #9
    //   355: invokevirtual endTransaction : ()V
    //   358: aload_1
    //   359: athrow
    // Exception table:
    //   from	to	target	type
    //   29	83	319	android/database/sqlite/SQLiteException
    //   29	83	315	finally
    //   83	102	310	android/database/sqlite/SQLiteException
    //   83	102	352	finally
    //   115	194	310	android/database/sqlite/SQLiteException
    //   115	194	352	finally
    //   206	283	305	android/database/sqlite/SQLiteException
    //   206	283	352	finally
    //   292	297	305	android/database/sqlite/SQLiteException
    //   292	297	352	finally
    //   324	344	352	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */