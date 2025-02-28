package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.List;

public final class zzao extends zzf {
  private final zzap zzalo = new zzap(this, getContext(), "google_app_measurement_local.db");
  
  private boolean zzalp;
  
  zzao(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
    if (this.zzalp)
      return null; 
    SQLiteDatabase sQLiteDatabase = this.zzalo.getWritableDatabase();
    if (sQLiteDatabase == null) {
      this.zzalp = true;
      return null;
    } 
    return sQLiteDatabase;
  }
  
  private final boolean zza(int paramInt, byte[] paramArrayOfbyte) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzgg : ()V
    //   4: aload_0
    //   5: invokevirtual zzaf : ()V
    //   8: aload_0
    //   9: getfield zzalp : Z
    //   12: ifeq -> 17
    //   15: iconst_0
    //   16: ireturn
    //   17: new android/content/ContentValues
    //   20: dup
    //   21: invokespecial <init> : ()V
    //   24: astore #13
    //   26: aload #13
    //   28: ldc 'type'
    //   30: iload_1
    //   31: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   34: invokevirtual put : (Ljava/lang/String;Ljava/lang/Integer;)V
    //   37: aload #13
    //   39: ldc 'entry'
    //   41: aload_2
    //   42: invokevirtual put : (Ljava/lang/String;[B)V
    //   45: iconst_0
    //   46: istore #4
    //   48: iconst_5
    //   49: istore_3
    //   50: iload #4
    //   52: iconst_5
    //   53: if_icmpge -> 620
    //   56: aconst_null
    //   57: astore #11
    //   59: aconst_null
    //   60: astore #12
    //   62: aconst_null
    //   63: astore #10
    //   65: aconst_null
    //   66: astore #9
    //   68: aload_0
    //   69: invokespecial getWritableDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   72: astore_2
    //   73: aload_2
    //   74: ifnonnull -> 108
    //   77: aload_2
    //   78: astore #9
    //   80: aload_0
    //   81: iconst_1
    //   82: putfield zzalp : Z
    //   85: aload_2
    //   86: ifnull -> 93
    //   89: aload_2
    //   90: invokevirtual close : ()V
    //   93: iconst_0
    //   94: ireturn
    //   95: astore #9
    //   97: aconst_null
    //   98: astore #10
    //   100: goto -> 345
    //   103: astore #11
    //   105: goto -> 519
    //   108: aload_2
    //   109: invokevirtual beginTransaction : ()V
    //   112: lconst_0
    //   113: lstore #7
    //   115: aload_2
    //   116: ldc 'select count(1) from messages'
    //   118: aconst_null
    //   119: invokevirtual rawQuery : (Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   122: astore #10
    //   124: lload #7
    //   126: lstore #5
    //   128: aload #10
    //   130: ifnull -> 185
    //   133: lload #7
    //   135: lstore #5
    //   137: aload #10
    //   139: invokeinterface moveToFirst : ()Z
    //   144: ifeq -> 185
    //   147: aload #10
    //   149: iconst_0
    //   150: invokeinterface getLong : (I)J
    //   155: lstore #5
    //   157: goto -> 185
    //   160: astore #9
    //   162: aload_2
    //   163: astore #11
    //   165: aload #10
    //   167: astore_2
    //   168: goto -> 597
    //   171: astore #9
    //   173: goto -> 345
    //   176: astore #11
    //   178: aload #10
    //   180: astore #12
    //   182: goto -> 519
    //   185: lload #5
    //   187: ldc2_w 100000
    //   190: lcmp
    //   191: iflt -> 277
    //   194: aload_0
    //   195: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   198: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   201: ldc 'Data loss, local db full'
    //   203: invokevirtual zzby : (Ljava/lang/String;)V
    //   206: ldc2_w 100000
    //   209: lload #5
    //   211: lsub
    //   212: lconst_1
    //   213: ladd
    //   214: lstore #7
    //   216: aload_2
    //   217: ldc 'messages'
    //   219: ldc 'rowid in (select rowid from messages order by rowid asc limit ?)'
    //   221: iconst_1
    //   222: anewarray java/lang/String
    //   225: dup
    //   226: iconst_0
    //   227: lload #7
    //   229: invokestatic toString : (J)Ljava/lang/String;
    //   232: aastore
    //   233: invokevirtual delete : (Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   236: i2l
    //   237: lstore #5
    //   239: lload #5
    //   241: lload #7
    //   243: lcmp
    //   244: ifeq -> 277
    //   247: aload_0
    //   248: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   251: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   254: ldc 'Different delete count than expected in local db. expected, received, difference'
    //   256: lload #7
    //   258: invokestatic valueOf : (J)Ljava/lang/Long;
    //   261: lload #5
    //   263: invokestatic valueOf : (J)Ljava/lang/Long;
    //   266: lload #7
    //   268: lload #5
    //   270: lsub
    //   271: invokestatic valueOf : (J)Ljava/lang/Long;
    //   274: invokevirtual zzd : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   277: aload_2
    //   278: ldc 'messages'
    //   280: aconst_null
    //   281: aload #13
    //   283: invokevirtual insertOrThrow : (Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   286: pop2
    //   287: aload_2
    //   288: invokevirtual setTransactionSuccessful : ()V
    //   291: aload_2
    //   292: invokevirtual endTransaction : ()V
    //   295: aload #10
    //   297: ifnull -> 307
    //   300: aload #10
    //   302: invokeinterface close : ()V
    //   307: aload_2
    //   308: ifnull -> 315
    //   311: aload_2
    //   312: invokevirtual close : ()V
    //   315: iconst_1
    //   316: ireturn
    //   317: astore #9
    //   319: aload #10
    //   321: astore #11
    //   323: goto -> 469
    //   326: astore #9
    //   328: aconst_null
    //   329: astore #10
    //   331: aload_2
    //   332: astore #11
    //   334: aload #10
    //   336: astore_2
    //   337: goto -> 597
    //   340: astore #9
    //   342: aconst_null
    //   343: astore #10
    //   345: aload #9
    //   347: astore #11
    //   349: aload_2
    //   350: astore #9
    //   352: aload #10
    //   354: astore_2
    //   355: goto -> 389
    //   358: astore #9
    //   360: aconst_null
    //   361: astore #11
    //   363: goto -> 469
    //   366: astore #11
    //   368: goto -> 519
    //   371: astore #9
    //   373: aconst_null
    //   374: astore #11
    //   376: aconst_null
    //   377: astore_2
    //   378: goto -> 597
    //   381: astore #10
    //   383: aconst_null
    //   384: astore_2
    //   385: aload #10
    //   387: astore #11
    //   389: aload #9
    //   391: ifnull -> 407
    //   394: aload #9
    //   396: invokevirtual inTransaction : ()Z
    //   399: ifeq -> 407
    //   402: aload #9
    //   404: invokevirtual endTransaction : ()V
    //   407: aload_0
    //   408: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   411: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   414: ldc 'Error writing entry to local database'
    //   416: aload #11
    //   418: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   421: aload_0
    //   422: iconst_1
    //   423: putfield zzalp : Z
    //   426: aload_2
    //   427: ifnull -> 436
    //   430: aload_2
    //   431: invokeinterface close : ()V
    //   436: iload_3
    //   437: istore_1
    //   438: aload #9
    //   440: ifnull -> 576
    //   443: aload #9
    //   445: invokevirtual close : ()V
    //   448: iload_3
    //   449: istore_1
    //   450: goto -> 576
    //   453: astore #10
    //   455: aload #9
    //   457: astore #11
    //   459: aload #10
    //   461: astore #9
    //   463: goto -> 597
    //   466: astore_2
    //   467: aconst_null
    //   468: astore_2
    //   469: iload_3
    //   470: i2l
    //   471: lstore #5
    //   473: aload #11
    //   475: astore #10
    //   477: aload_2
    //   478: astore #9
    //   480: lload #5
    //   482: invokestatic sleep : (J)V
    //   485: iinc #3, 20
    //   488: aload #11
    //   490: ifnull -> 500
    //   493: aload #11
    //   495: invokeinterface close : ()V
    //   500: iload_3
    //   501: istore_1
    //   502: aload_2
    //   503: ifnull -> 576
    //   506: aload_2
    //   507: invokevirtual close : ()V
    //   510: iload_3
    //   511: istore_1
    //   512: goto -> 576
    //   515: astore #11
    //   517: aconst_null
    //   518: astore_2
    //   519: aload #12
    //   521: astore #10
    //   523: aload_2
    //   524: astore #9
    //   526: aload_0
    //   527: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   530: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   533: ldc 'Error writing entry to local database'
    //   535: aload #11
    //   537: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   540: aload #12
    //   542: astore #10
    //   544: aload_2
    //   545: astore #9
    //   547: aload_0
    //   548: iconst_1
    //   549: putfield zzalp : Z
    //   552: aload #12
    //   554: ifnull -> 564
    //   557: aload #12
    //   559: invokeinterface close : ()V
    //   564: iload_3
    //   565: istore_1
    //   566: aload_2
    //   567: ifnull -> 576
    //   570: aload_2
    //   571: invokevirtual close : ()V
    //   574: iload_3
    //   575: istore_1
    //   576: iinc #4, 1
    //   579: iload_1
    //   580: istore_3
    //   581: goto -> 50
    //   584: astore #12
    //   586: aload #10
    //   588: astore_2
    //   589: aload #9
    //   591: astore #11
    //   593: aload #12
    //   595: astore #9
    //   597: aload_2
    //   598: ifnull -> 607
    //   601: aload_2
    //   602: invokeinterface close : ()V
    //   607: aload #11
    //   609: ifnull -> 617
    //   612: aload #11
    //   614: invokevirtual close : ()V
    //   617: aload #9
    //   619: athrow
    //   620: aload_0
    //   621: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   624: invokevirtual zzjj : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   627: ldc 'Failed to write entry to local database'
    //   629: invokevirtual zzby : (Ljava/lang/String;)V
    //   632: iconst_0
    //   633: ireturn
    // Exception table:
    //   from	to	target	type
    //   68	73	515	android/database/sqlite/SQLiteFullException
    //   68	73	466	android/database/sqlite/SQLiteDatabaseLockedException
    //   68	73	381	android/database/sqlite/SQLiteException
    //   68	73	371	finally
    //   80	85	103	android/database/sqlite/SQLiteFullException
    //   80	85	358	android/database/sqlite/SQLiteDatabaseLockedException
    //   80	85	95	android/database/sqlite/SQLiteException
    //   80	85	584	finally
    //   108	112	366	android/database/sqlite/SQLiteFullException
    //   108	112	358	android/database/sqlite/SQLiteDatabaseLockedException
    //   108	112	340	android/database/sqlite/SQLiteException
    //   108	112	326	finally
    //   115	124	366	android/database/sqlite/SQLiteFullException
    //   115	124	358	android/database/sqlite/SQLiteDatabaseLockedException
    //   115	124	340	android/database/sqlite/SQLiteException
    //   115	124	326	finally
    //   137	157	176	android/database/sqlite/SQLiteFullException
    //   137	157	317	android/database/sqlite/SQLiteDatabaseLockedException
    //   137	157	171	android/database/sqlite/SQLiteException
    //   137	157	160	finally
    //   194	206	176	android/database/sqlite/SQLiteFullException
    //   194	206	317	android/database/sqlite/SQLiteDatabaseLockedException
    //   194	206	171	android/database/sqlite/SQLiteException
    //   194	206	160	finally
    //   216	239	176	android/database/sqlite/SQLiteFullException
    //   216	239	317	android/database/sqlite/SQLiteDatabaseLockedException
    //   216	239	171	android/database/sqlite/SQLiteException
    //   216	239	160	finally
    //   247	277	176	android/database/sqlite/SQLiteFullException
    //   247	277	317	android/database/sqlite/SQLiteDatabaseLockedException
    //   247	277	171	android/database/sqlite/SQLiteException
    //   247	277	160	finally
    //   277	295	176	android/database/sqlite/SQLiteFullException
    //   277	295	317	android/database/sqlite/SQLiteDatabaseLockedException
    //   277	295	171	android/database/sqlite/SQLiteException
    //   277	295	160	finally
    //   394	407	453	finally
    //   407	426	453	finally
    //   480	485	584	finally
    //   526	540	584	finally
    //   547	552	584	finally
  }
  
  public final void resetAnalyticsData() {
    zzgg();
    zzaf();
    try {
      int i = getWritableDatabase().delete("messages", null, null) + 0;
      if (i > 0)
        zzgt().zzjo().zzg("Reset local analytics data. records", Integer.valueOf(i)); 
      return;
    } catch (SQLiteException sQLiteException) {
      zzgt().zzjg().zzg("Error resetting local analytics data. error", sQLiteException);
      return;
    } 
  }
  
  public final boolean zza(zzag paramzzag) {
    Parcel parcel = Parcel.obtain();
    paramzzag.writeToParcel(parcel, 0);
    byte[] arrayOfByte = parcel.marshall();
    parcel.recycle();
    if (arrayOfByte.length > 131072) {
      zzgt().zzjj().zzby("Event is too long for local database. Sending event directly to service");
      return false;
    } 
    return zza(0, arrayOfByte);
  }
  
  public final boolean zza(zzfu paramzzfu) {
    Parcel parcel = Parcel.obtain();
    paramzzfu.writeToParcel(parcel, 0);
    byte[] arrayOfByte = parcel.marshall();
    parcel.recycle();
    if (arrayOfByte.length > 131072) {
      zzgt().zzjj().zzby("User property too long for local database. Sending directly to service");
      return false;
    } 
    return zza(1, arrayOfByte);
  }
  
  public final boolean zzc(zzo paramzzo) {
    zzgr();
    byte[] arrayOfByte = zzfx.zza((Parcelable)paramzzo);
    if (arrayOfByte.length > 131072) {
      zzgt().zzjj().zzby("Conditional user property too long for local database. Sending directly to service");
      return false;
    } 
    return zza(2, arrayOfByte);
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  public final List<AbstractSafeParcelable> zzr(int paramInt) {
    zzaf();
    zzgg();
    if (this.zzalp)
      return null; 
    ArrayList<AbstractSafeParcelable> arrayList = new ArrayList();
    if (!getContext().getDatabasePath("google_app_measurement_local.db").exists())
      return arrayList; 
    byte b = 0;
    int i = 5;
    label181: while (true) {
      if (b < 5)
        try {
          Exception exception1;
          SQLiteDatabase sQLiteDatabase = getWritableDatabase();
          if (sQLiteDatabase == null) {
            try {
              return null;
            } catch (SQLiteFullException sQLiteFullException) {
            
            } catch (SQLiteDatabaseLockedException sQLiteDatabaseLockedException) {
            
            } catch (SQLiteException sQLiteException) {
            
            } finally {
              Exception exception3 = null;
              Exception exception4 = null;
              SQLiteDatabase sQLiteDatabase1 = sQLiteDatabase;
              exception1 = exception3;
              exception3 = exception4;
            } 
          } else {
            try {
              Cursor cursor;
              exception1.beginTransaction();
            } catch (SQLiteFullException sQLiteFullException) {
            
            } catch (SQLiteException sQLiteException) {
            
            } finally {
              Exception exception3 = null;
              Exception exception4 = exception1;
              Exception exception5 = null;
              exception1 = exception3;
              exception3 = exception4;
              exception4 = exception5;
            } 
            Object object1 = null;
            Exception exception = exception1;
          } 
        } catch (SQLiteFullException sQLiteFullException) {
        
        } catch (SQLiteDatabaseLockedException sQLiteDatabaseLockedException) {
        
        } catch (SQLiteException sQLiteException) {
        
        } finally {
          Exception exception = null;
          Object object1 = null;
          Object object2 = null;
        }  
      zzgt().zzjj().zzby("Failed to read events from database in reasonable time");
      return null;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */