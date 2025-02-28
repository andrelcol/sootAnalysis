package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class DynamiteModule {
  public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING;
  
  public static final VersionPolicy PREFER_REMOTE;
  
  private static Boolean zzif;
  
  private static zzi zzig;
  
  private static zzk zzih;
  
  private static String zzii;
  
  private static int zzij = -1;
  
  private static final ThreadLocal<zza> zzik = new ThreadLocal<zza>();
  
  private static final VersionPolicy.zza zzil = new zza();
  
  private final Context zzin;
  
  static {
    PREFER_REMOTE = new zzb();
    new zzc();
    new zzd();
    PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    new zzf();
    new zzg();
  }
  
  private DynamiteModule(Context paramContext) {
    Preconditions.checkNotNull(paramContext);
    this.zzin = paramContext;
  }
  
  public static int getLocalVersion(Context paramContext, String paramString) {
    try {
      String str;
      ClassLoader classLoader = paramContext.getApplicationContext().getClassLoader();
      null = String.valueOf(paramString).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(null + 61);
      stringBuilder.append("com.google.android.gms.dynamite.descriptors.");
      stringBuilder.append(paramString);
      stringBuilder.append(".ModuleDescriptor");
      Class<?> clazz = classLoader.loadClass(stringBuilder.toString());
      Field field1 = clazz.getDeclaredField("MODULE_ID");
      Field field2 = clazz.getDeclaredField("MODULE_VERSION");
      if (!field1.get(null).equals(paramString)) {
        str = String.valueOf(field1.get(null));
        null = String.valueOf(str).length();
        int i = String.valueOf(paramString).length();
        StringBuilder stringBuilder1 = new StringBuilder();
        this(null + 51 + i);
        stringBuilder1.append("Module descriptor id '");
        stringBuilder1.append(str);
        stringBuilder1.append("' didn't match expected id '");
        stringBuilder1.append(paramString);
        stringBuilder1.append("'");
        stringBuilder1.toString();
        return 0;
      } 
      return str.getInt(null);
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString).length() + 45);
      stringBuilder.append("Local module descriptor class for ");
      stringBuilder.append(paramString);
      stringBuilder.append(" not found.");
      stringBuilder.toString();
    } catch (Exception exception) {
      String str = String.valueOf(exception.getMessage());
      if (str.length() != 0) {
        "Failed to load module descriptor class: ".concat(str);
      } else {
        new String("Failed to load module descriptor class: ");
      } 
    } 
    return 0;
  }
  
  public static DynamiteModule load(Context paramContext, VersionPolicy paramVersionPolicy, String paramString) throws LoadingException {
    zza zza1 = zzik.get();
    zza zza2 = new zza(null);
    zzik.set(zza2);
    try {
      VersionPolicy.zzb zzb = paramVersionPolicy.zza(paramContext, paramString, zzil);
      int j = zzb.zzir;
      int k = zzb.zzis;
      int i = String.valueOf(paramString).length();
      int m = String.valueOf(paramString).length();
      StringBuilder stringBuilder2 = new StringBuilder();
      this(i + 68 + m);
      stringBuilder2.append("Considering local module ");
      stringBuilder2.append(paramString);
      stringBuilder2.append(":");
      stringBuilder2.append(j);
      stringBuilder2.append(" and remote module ");
      stringBuilder2.append(paramString);
      stringBuilder2.append(":");
      stringBuilder2.append(k);
      stringBuilder2.toString();
      if (zzb.zzit != 0 && (zzb.zzit != -1 || zzb.zzir != 0) && (zzb.zzit != 1 || zzb.zzis != 0)) {
        DynamiteModule dynamiteModule;
        Cursor cursor;
        if (zzb.zzit == -1) {
          dynamiteModule = zze(paramContext, paramString);
          return dynamiteModule;
        } 
        i = zzb.zzit;
        if (i == 1) {
          Cursor cursor1;
          try {
            return zza((Context)dynamiteModule, paramString, zzb.zzis);
          } catch (LoadingException loadingException3) {
            String str = String.valueOf(loadingException3.getMessage());
            if (str.length() != 0) {
              "Failed to load remote module: ".concat(str);
            } else {
              new String("Failed to load remote module: ");
            } 
            if (zzb.zzir != 0) {
              zzb zzb1 = new zzb();
              this(zzb.zzir, 0);
              if ((cursor.zza((Context)cursor1, paramString, zzb1)).zzit == -1)
                return zze((Context)cursor1, paramString); 
            } 
            LoadingException loadingException2 = new LoadingException();
            this("Remote load failed. No local fallback found.", loadingException3, null);
            throw loadingException2;
          } 
        } 
        LoadingException loadingException1 = new LoadingException();
        i = zzb.zzit;
        StringBuilder stringBuilder = new StringBuilder();
        this(47);
        stringBuilder.append("VersionPolicy returned invalid code:");
        stringBuilder.append(i);
        this(stringBuilder.toString(), (zza)null);
        throw loadingException1;
      } 
      LoadingException loadingException = new LoadingException();
      i = zzb.zzir;
      j = zzb.zzis;
      StringBuilder stringBuilder1 = new StringBuilder();
      this(91);
      stringBuilder1.append("No acceptable module found. Local version is ");
      stringBuilder1.append(i);
      stringBuilder1.append(" and remote version is ");
      stringBuilder1.append(j);
      stringBuilder1.append(".");
      this(stringBuilder1.toString(), (zza)null);
      throw loadingException;
    } finally {
      Cursor cursor = zza2.zzio;
      if (cursor != null)
        cursor.close(); 
      zzik.set(zza1);
    } 
  }
  
  public static int zza(Context paramContext, String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzif : Ljava/lang/Boolean;
    //   6: astore #6
    //   8: aload #6
    //   10: astore #5
    //   12: aload #6
    //   14: ifnonnull -> 306
    //   17: aload_0
    //   18: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   21: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   24: ldc com/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader
    //   26: invokevirtual getName : ()Ljava/lang/String;
    //   29: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   32: astore #6
    //   34: aload #6
    //   36: ldc_w 'sClassLoader'
    //   39: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   42: astore #5
    //   44: aload #6
    //   46: monitorenter
    //   47: aload #5
    //   49: aconst_null
    //   50: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   53: checkcast java/lang/ClassLoader
    //   56: astore #7
    //   58: aload #7
    //   60: ifnull -> 92
    //   63: aload #7
    //   65: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   68: if_acmpne -> 79
    //   71: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   74: astore #5
    //   76: goto -> 217
    //   79: aload #7
    //   81: invokestatic zza : (Ljava/lang/ClassLoader;)V
    //   84: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   87: astore #5
    //   89: goto -> 217
    //   92: ldc_w 'com.google.android.gms'
    //   95: aload_0
    //   96: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   99: invokevirtual getPackageName : ()Ljava/lang/String;
    //   102: invokevirtual equals : (Ljava/lang/Object;)Z
    //   105: ifeq -> 125
    //   108: aload #5
    //   110: aconst_null
    //   111: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   114: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   117: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   120: astore #5
    //   122: goto -> 217
    //   125: aload_0
    //   126: aload_1
    //   127: iload_2
    //   128: invokestatic zzc : (Landroid/content/Context;Ljava/lang/String;Z)I
    //   131: istore_3
    //   132: getstatic com/google/android/gms/dynamite/DynamiteModule.zzii : Ljava/lang/String;
    //   135: ifnull -> 193
    //   138: getstatic com/google/android/gms/dynamite/DynamiteModule.zzii : Ljava/lang/String;
    //   141: invokevirtual isEmpty : ()Z
    //   144: ifeq -> 150
    //   147: goto -> 193
    //   150: new com/google/android/gms/dynamite/zzh
    //   153: astore #7
    //   155: aload #7
    //   157: getstatic com/google/android/gms/dynamite/DynamiteModule.zzii : Ljava/lang/String;
    //   160: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   163: invokespecial <init> : (Ljava/lang/String;Ljava/lang/ClassLoader;)V
    //   166: aload #7
    //   168: invokestatic zza : (Ljava/lang/ClassLoader;)V
    //   171: aload #5
    //   173: aconst_null
    //   174: aload #7
    //   176: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   179: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   182: putstatic com/google/android/gms/dynamite/DynamiteModule.zzif : Ljava/lang/Boolean;
    //   185: aload #6
    //   187: monitorexit
    //   188: ldc com/google/android/gms/dynamite/DynamiteModule
    //   190: monitorexit
    //   191: iload_3
    //   192: ireturn
    //   193: aload #6
    //   195: monitorexit
    //   196: ldc com/google/android/gms/dynamite/DynamiteModule
    //   198: monitorexit
    //   199: iload_3
    //   200: ireturn
    //   201: astore #7
    //   203: aload #5
    //   205: aconst_null
    //   206: invokestatic getSystemClassLoader : ()Ljava/lang/ClassLoader;
    //   209: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   212: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   215: astore #5
    //   217: aload #6
    //   219: monitorexit
    //   220: goto -> 301
    //   223: astore #5
    //   225: aload #6
    //   227: monitorexit
    //   228: aload #5
    //   230: athrow
    //   231: astore #5
    //   233: goto -> 243
    //   236: astore #5
    //   238: goto -> 243
    //   241: astore #5
    //   243: aload #5
    //   245: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   248: astore #5
    //   250: aload #5
    //   252: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   255: invokevirtual length : ()I
    //   258: istore_3
    //   259: new java/lang/StringBuilder
    //   262: astore #6
    //   264: aload #6
    //   266: iload_3
    //   267: bipush #30
    //   269: iadd
    //   270: invokespecial <init> : (I)V
    //   273: aload #6
    //   275: ldc_w 'Failed to load module via V2: '
    //   278: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: pop
    //   282: aload #6
    //   284: aload #5
    //   286: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: pop
    //   290: aload #6
    //   292: invokevirtual toString : ()Ljava/lang/String;
    //   295: pop
    //   296: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   299: astore #5
    //   301: aload #5
    //   303: putstatic com/google/android/gms/dynamite/DynamiteModule.zzif : Ljava/lang/Boolean;
    //   306: ldc com/google/android/gms/dynamite/DynamiteModule
    //   308: monitorexit
    //   309: aload #5
    //   311: invokevirtual booleanValue : ()Z
    //   314: istore #4
    //   316: iload #4
    //   318: ifeq -> 370
    //   321: aload_0
    //   322: aload_1
    //   323: iload_2
    //   324: invokestatic zzc : (Landroid/content/Context;Ljava/lang/String;Z)I
    //   327: istore_3
    //   328: iload_3
    //   329: ireturn
    //   330: astore_1
    //   331: aload_1
    //   332: invokevirtual getMessage : ()Ljava/lang/String;
    //   335: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   338: astore_1
    //   339: aload_1
    //   340: invokevirtual length : ()I
    //   343: ifeq -> 357
    //   346: ldc_w 'Failed to retrieve remote module version: '
    //   349: aload_1
    //   350: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   353: pop
    //   354: goto -> 368
    //   357: new java/lang/String
    //   360: dup
    //   361: ldc_w 'Failed to retrieve remote module version: '
    //   364: invokespecial <init> : (Ljava/lang/String;)V
    //   367: pop
    //   368: iconst_0
    //   369: ireturn
    //   370: aload_0
    //   371: aload_1
    //   372: iload_2
    //   373: invokestatic zzb : (Landroid/content/Context;Ljava/lang/String;Z)I
    //   376: istore_3
    //   377: iload_3
    //   378: ireturn
    //   379: astore_1
    //   380: ldc com/google/android/gms/dynamite/DynamiteModule
    //   382: monitorexit
    //   383: aload_1
    //   384: athrow
    //   385: astore_1
    //   386: aload_0
    //   387: aload_1
    //   388: invokestatic addDynamiteErrorToDropBox : (Landroid/content/Context;Ljava/lang/Throwable;)Z
    //   391: pop
    //   392: aload_1
    //   393: athrow
    //   394: astore #5
    //   396: goto -> 84
    // Exception table:
    //   from	to	target	type
    //   0	3	385	finally
    //   3	8	379	finally
    //   17	47	241	java/lang/ClassNotFoundException
    //   17	47	236	java/lang/IllegalAccessException
    //   17	47	231	java/lang/NoSuchFieldException
    //   17	47	379	finally
    //   47	58	223	finally
    //   63	76	223	finally
    //   79	84	394	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   79	84	223	finally
    //   84	89	223	finally
    //   92	122	223	finally
    //   125	147	201	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   125	147	223	finally
    //   150	185	201	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   150	185	223	finally
    //   185	188	223	finally
    //   188	191	379	finally
    //   193	196	223	finally
    //   196	199	379	finally
    //   203	217	223	finally
    //   217	220	223	finally
    //   225	228	223	finally
    //   228	231	241	java/lang/ClassNotFoundException
    //   228	231	236	java/lang/IllegalAccessException
    //   228	231	231	java/lang/NoSuchFieldException
    //   228	231	379	finally
    //   243	301	379	finally
    //   301	306	379	finally
    //   306	309	379	finally
    //   309	316	385	finally
    //   321	328	330	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   321	328	385	finally
    //   331	354	385	finally
    //   357	368	385	finally
    //   370	377	385	finally
    //   380	383	379	finally
    //   383	385	385	finally
  }
  
  private static DynamiteModule zza(Context paramContext, String paramString, int paramInt) throws LoadingException {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzif : Ljava/lang/Boolean;
    //   6: astore #4
    //   8: ldc com/google/android/gms/dynamite/DynamiteModule
    //   10: monitorexit
    //   11: aload #4
    //   13: ifnull -> 194
    //   16: aload #4
    //   18: invokevirtual booleanValue : ()Z
    //   21: ifeq -> 31
    //   24: aload_0
    //   25: aload_1
    //   26: iload_2
    //   27: invokestatic zzb : (Landroid/content/Context;Ljava/lang/String;I)Lcom/google/android/gms/dynamite/DynamiteModule;
    //   30: areturn
    //   31: aload_1
    //   32: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   35: invokevirtual length : ()I
    //   38: istore_3
    //   39: new java/lang/StringBuilder
    //   42: astore #4
    //   44: aload #4
    //   46: iload_3
    //   47: bipush #51
    //   49: iadd
    //   50: invokespecial <init> : (I)V
    //   53: aload #4
    //   55: ldc_w 'Selected remote version of '
    //   58: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: aload #4
    //   64: aload_1
    //   65: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload #4
    //   71: ldc_w ', version >= '
    //   74: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: aload #4
    //   80: iload_2
    //   81: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: aload #4
    //   87: invokevirtual toString : ()Ljava/lang/String;
    //   90: pop
    //   91: aload_0
    //   92: invokestatic zzj : (Landroid/content/Context;)Lcom/google/android/gms/dynamite/zzi;
    //   95: astore #4
    //   97: aload #4
    //   99: ifnull -> 180
    //   102: aload #4
    //   104: invokeinterface zzak : ()I
    //   109: iconst_2
    //   110: if_icmplt -> 130
    //   113: aload #4
    //   115: aload_0
    //   116: invokestatic wrap : (Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   119: aload_1
    //   120: iload_2
    //   121: invokeinterface zzb : (Lcom/google/android/gms/dynamic/IObjectWrapper;Ljava/lang/String;I)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   126: astore_1
    //   127: goto -> 144
    //   130: aload #4
    //   132: aload_0
    //   133: invokestatic wrap : (Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   136: aload_1
    //   137: iload_2
    //   138: invokeinterface zza : (Lcom/google/android/gms/dynamic/IObjectWrapper;Ljava/lang/String;I)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   143: astore_1
    //   144: aload_1
    //   145: invokestatic unwrap : (Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
    //   148: ifnull -> 166
    //   151: new com/google/android/gms/dynamite/DynamiteModule
    //   154: dup
    //   155: aload_1
    //   156: invokestatic unwrap : (Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
    //   159: checkcast android/content/Context
    //   162: invokespecial <init> : (Landroid/content/Context;)V
    //   165: areturn
    //   166: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   169: astore_1
    //   170: aload_1
    //   171: ldc_w 'Failed to load remote module.'
    //   174: aconst_null
    //   175: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   178: aload_1
    //   179: athrow
    //   180: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   183: astore_1
    //   184: aload_1
    //   185: ldc_w 'Failed to create IDynamiteLoader.'
    //   188: aconst_null
    //   189: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   192: aload_1
    //   193: athrow
    //   194: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   197: astore_1
    //   198: aload_1
    //   199: ldc_w 'Failed to determine which loading route to use.'
    //   202: aconst_null
    //   203: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   206: aload_1
    //   207: athrow
    //   208: astore_1
    //   209: ldc com/google/android/gms/dynamite/DynamiteModule
    //   211: monitorexit
    //   212: aload_1
    //   213: athrow
    //   214: astore_1
    //   215: aload_0
    //   216: aload_1
    //   217: invokestatic addDynamiteErrorToDropBox : (Landroid/content/Context;Ljava/lang/Throwable;)Z
    //   220: pop
    //   221: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   224: dup
    //   225: ldc_w 'Failed to load remote module.'
    //   228: aload_1
    //   229: aconst_null
    //   230: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/dynamite/zza;)V
    //   233: athrow
    //   234: astore_0
    //   235: aload_0
    //   236: athrow
    //   237: astore_0
    //   238: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   241: dup
    //   242: ldc_w 'Failed to load remote module.'
    //   245: aload_0
    //   246: aconst_null
    //   247: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/dynamite/zza;)V
    //   250: athrow
    // Exception table:
    //   from	to	target	type
    //   0	3	237	android/os/RemoteException
    //   0	3	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   0	3	214	finally
    //   3	11	208	finally
    //   16	31	237	android/os/RemoteException
    //   16	31	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   16	31	214	finally
    //   31	97	237	android/os/RemoteException
    //   31	97	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   31	97	214	finally
    //   102	127	237	android/os/RemoteException
    //   102	127	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   102	127	214	finally
    //   130	144	237	android/os/RemoteException
    //   130	144	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   130	144	214	finally
    //   144	166	237	android/os/RemoteException
    //   144	166	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   144	166	214	finally
    //   166	180	237	android/os/RemoteException
    //   166	180	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   166	180	214	finally
    //   180	194	237	android/os/RemoteException
    //   180	194	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   180	194	214	finally
    //   194	208	237	android/os/RemoteException
    //   194	208	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   194	208	214	finally
    //   209	212	208	finally
    //   212	214	237	android/os/RemoteException
    //   212	214	234	com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   212	214	214	finally
  }
  
  private static void zza(ClassLoader paramClassLoader) throws LoadingException {
    try {
      zzk zzk1;
      IBinder iBinder = paramClassLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
      if (iBinder == null) {
        iBinder = null;
      } else {
        IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
        if (iInterface instanceof zzk) {
          zzk1 = (zzk)iInterface;
        } else {
          zzk1 = new zzl((IBinder)zzk1);
        } 
      } 
      zzih = zzk1;
      return;
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InstantiationException instantiationException) {
    
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {}
    throw new LoadingException("Failed to instantiate dynamite loader", noSuchMethodException, null);
  }
  
  private static Boolean zzaj() {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzij : I
    //   6: iconst_2
    //   7: if_icmplt -> 15
    //   10: iconst_1
    //   11: istore_0
    //   12: goto -> 17
    //   15: iconst_0
    //   16: istore_0
    //   17: ldc com/google/android/gms/dynamite/DynamiteModule
    //   19: monitorexit
    //   20: iload_0
    //   21: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   24: areturn
    //   25: astore_1
    //   26: ldc com/google/android/gms/dynamite/DynamiteModule
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   3	10	25	finally
    //   17	25	25	finally
    //   26	29	25	finally
  }
  
  private static int zzb(Context paramContext, String paramString, boolean paramBoolean) {
    zzi zzi1 = zzj(paramContext);
    if (zzi1 == null)
      return 0; 
    try {
      return (zzi1.zzak() >= 2) ? zzi1.zzb(ObjectWrapper.wrap(paramContext), paramString, paramBoolean) : zzi1.zza(ObjectWrapper.wrap(paramContext), paramString, paramBoolean);
    } catch (RemoteException remoteException) {
      String str = String.valueOf(remoteException.getMessage());
      if (str.length() != 0) {
        "Failed to retrieve remote module version: ".concat(str);
      } else {
        new String("Failed to retrieve remote module version: ");
      } 
      return 0;
    } 
  }
  
  private static DynamiteModule zzb(Context paramContext, String paramString, int paramInt) throws LoadingException, RemoteException {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: aload_1
    //   5: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   8: invokevirtual length : ()I
    //   11: bipush #51
    //   13: iadd
    //   14: invokespecial <init> : (I)V
    //   17: astore_3
    //   18: aload_3
    //   19: ldc_w 'Selected remote version of '
    //   22: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload_3
    //   27: aload_1
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload_3
    //   33: ldc_w ', version >= '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload_3
    //   41: iload_2
    //   42: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: aload_3
    //   47: invokevirtual toString : ()Ljava/lang/String;
    //   50: pop
    //   51: ldc com/google/android/gms/dynamite/DynamiteModule
    //   53: monitorenter
    //   54: getstatic com/google/android/gms/dynamite/DynamiteModule.zzih : Lcom/google/android/gms/dynamite/zzk;
    //   57: astore_3
    //   58: ldc com/google/android/gms/dynamite/DynamiteModule
    //   60: monitorexit
    //   61: aload_3
    //   62: ifnull -> 199
    //   65: getstatic com/google/android/gms/dynamite/DynamiteModule.zzik : Ljava/lang/ThreadLocal;
    //   68: invokevirtual get : ()Ljava/lang/Object;
    //   71: checkcast com/google/android/gms/dynamite/DynamiteModule$zza
    //   74: astore #4
    //   76: aload #4
    //   78: ifnull -> 187
    //   81: aload #4
    //   83: getfield zzio : Landroid/database/Cursor;
    //   86: ifnull -> 187
    //   89: aload_0
    //   90: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   93: astore_0
    //   94: aload #4
    //   96: getfield zzio : Landroid/database/Cursor;
    //   99: astore #4
    //   101: aconst_null
    //   102: invokestatic wrap : (Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   105: pop
    //   106: invokestatic zzaj : ()Ljava/lang/Boolean;
    //   109: invokevirtual booleanValue : ()Z
    //   112: ifeq -> 136
    //   115: aload_3
    //   116: aload_0
    //   117: invokestatic wrap : (Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   120: aload_1
    //   121: iload_2
    //   122: aload #4
    //   124: invokestatic wrap : (Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   127: invokeinterface zzb : (Lcom/google/android/gms/dynamic/IObjectWrapper;Ljava/lang/String;ILcom/google/android/gms/dynamic/IObjectWrapper;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   132: astore_0
    //   133: goto -> 154
    //   136: aload_3
    //   137: aload_0
    //   138: invokestatic wrap : (Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   141: aload_1
    //   142: iload_2
    //   143: aload #4
    //   145: invokestatic wrap : (Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   148: invokeinterface zza : (Lcom/google/android/gms/dynamic/IObjectWrapper;Ljava/lang/String;ILcom/google/android/gms/dynamic/IObjectWrapper;)Lcom/google/android/gms/dynamic/IObjectWrapper;
    //   153: astore_0
    //   154: aload_0
    //   155: invokestatic unwrap : (Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
    //   158: checkcast android/content/Context
    //   161: astore_0
    //   162: aload_0
    //   163: ifnull -> 175
    //   166: new com/google/android/gms/dynamite/DynamiteModule
    //   169: dup
    //   170: aload_0
    //   171: invokespecial <init> : (Landroid/content/Context;)V
    //   174: areturn
    //   175: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   178: dup
    //   179: ldc_w 'Failed to get module context'
    //   182: aconst_null
    //   183: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   186: athrow
    //   187: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   190: dup
    //   191: ldc_w 'No result cursor'
    //   194: aconst_null
    //   195: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   198: athrow
    //   199: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   202: dup
    //   203: ldc_w 'DynamiteLoaderV2 was not cached.'
    //   206: aconst_null
    //   207: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   210: athrow
    //   211: astore_0
    //   212: ldc com/google/android/gms/dynamite/DynamiteModule
    //   214: monitorexit
    //   215: aload_0
    //   216: athrow
    // Exception table:
    //   from	to	target	type
    //   54	61	211	finally
    //   212	215	211	finally
  }
  
  private static int zzc(Context paramContext, String paramString, boolean paramBoolean) throws LoadingException {
    // Byte code:
    //   0: aconst_null
    //   1: astore #5
    //   3: aload_0
    //   4: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   7: astore #6
    //   9: iload_2
    //   10: ifeq -> 20
    //   13: ldc_w 'api_force_staging'
    //   16: astore_0
    //   17: goto -> 24
    //   20: ldc_w 'api'
    //   23: astore_0
    //   24: aload_0
    //   25: invokevirtual length : ()I
    //   28: istore_3
    //   29: aload_1
    //   30: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   33: invokevirtual length : ()I
    //   36: istore #4
    //   38: new java/lang/StringBuilder
    //   41: astore #7
    //   43: aload #7
    //   45: iload_3
    //   46: bipush #42
    //   48: iadd
    //   49: iload #4
    //   51: iadd
    //   52: invokespecial <init> : (I)V
    //   55: aload #7
    //   57: ldc_w 'content://com.google.android.gms.chimera/'
    //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: pop
    //   64: aload #7
    //   66: aload_0
    //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: pop
    //   71: aload #7
    //   73: ldc_w '/'
    //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload #7
    //   82: aload_1
    //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: aload #6
    //   89: aload #7
    //   91: invokevirtual toString : ()Ljava/lang/String;
    //   94: invokestatic parse : (Ljava/lang/String;)Landroid/net/Uri;
    //   97: aconst_null
    //   98: aconst_null
    //   99: aconst_null
    //   100: aconst_null
    //   101: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   104: astore_0
    //   105: aload_0
    //   106: ifnull -> 240
    //   109: aload_0
    //   110: invokeinterface moveToFirst : ()Z
    //   115: ifeq -> 240
    //   118: aload_0
    //   119: iconst_0
    //   120: invokeinterface getInt : (I)I
    //   125: istore #4
    //   127: aload_0
    //   128: astore_1
    //   129: iload #4
    //   131: ifle -> 219
    //   134: ldc com/google/android/gms/dynamite/DynamiteModule
    //   136: monitorenter
    //   137: aload_0
    //   138: iconst_2
    //   139: invokeinterface getString : (I)Ljava/lang/String;
    //   144: putstatic com/google/android/gms/dynamite/DynamiteModule.zzii : Ljava/lang/String;
    //   147: aload_0
    //   148: ldc_w 'loaderVersion'
    //   151: invokeinterface getColumnIndex : (Ljava/lang/String;)I
    //   156: istore_3
    //   157: iload_3
    //   158: iflt -> 171
    //   161: aload_0
    //   162: iload_3
    //   163: invokeinterface getInt : (I)I
    //   168: putstatic com/google/android/gms/dynamite/DynamiteModule.zzij : I
    //   171: ldc com/google/android/gms/dynamite/DynamiteModule
    //   173: monitorexit
    //   174: getstatic com/google/android/gms/dynamite/DynamiteModule.zzik : Ljava/lang/ThreadLocal;
    //   177: invokevirtual get : ()Ljava/lang/Object;
    //   180: checkcast com/google/android/gms/dynamite/DynamiteModule$zza
    //   183: astore #5
    //   185: aload_0
    //   186: astore_1
    //   187: aload #5
    //   189: ifnull -> 219
    //   192: aload_0
    //   193: astore_1
    //   194: aload #5
    //   196: getfield zzio : Landroid/database/Cursor;
    //   199: ifnonnull -> 219
    //   202: aload #5
    //   204: aload_0
    //   205: putfield zzio : Landroid/database/Cursor;
    //   208: aconst_null
    //   209: astore_1
    //   210: goto -> 219
    //   213: astore_1
    //   214: ldc com/google/android/gms/dynamite/DynamiteModule
    //   216: monitorexit
    //   217: aload_1
    //   218: athrow
    //   219: aload_1
    //   220: ifnull -> 229
    //   223: aload_1
    //   224: invokeinterface close : ()V
    //   229: iload #4
    //   231: ireturn
    //   232: astore_1
    //   233: goto -> 292
    //   236: astore_1
    //   237: goto -> 264
    //   240: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   243: astore_1
    //   244: aload_1
    //   245: ldc_w 'Failed to connect to dynamite module ContentResolver.'
    //   248: aconst_null
    //   249: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/dynamite/zza;)V
    //   252: aload_1
    //   253: athrow
    //   254: astore_1
    //   255: aload #5
    //   257: astore_0
    //   258: goto -> 292
    //   261: astore_1
    //   262: aconst_null
    //   263: astore_0
    //   264: aload_1
    //   265: instanceof com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   268: ifeq -> 273
    //   271: aload_1
    //   272: athrow
    //   273: new com/google/android/gms/dynamite/DynamiteModule$LoadingException
    //   276: astore #5
    //   278: aload #5
    //   280: ldc_w 'V2 version check failed'
    //   283: aload_1
    //   284: aconst_null
    //   285: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;Lcom/google/android/gms/dynamite/zza;)V
    //   288: aload #5
    //   290: athrow
    //   291: astore_1
    //   292: aload_0
    //   293: ifnull -> 302
    //   296: aload_0
    //   297: invokeinterface close : ()V
    //   302: aload_1
    //   303: athrow
    // Exception table:
    //   from	to	target	type
    //   3	9	261	java/lang/Exception
    //   3	9	254	finally
    //   24	105	261	java/lang/Exception
    //   24	105	254	finally
    //   109	127	236	java/lang/Exception
    //   109	127	232	finally
    //   134	137	236	java/lang/Exception
    //   134	137	232	finally
    //   137	157	213	finally
    //   161	171	213	finally
    //   171	174	213	finally
    //   174	185	236	java/lang/Exception
    //   174	185	232	finally
    //   194	208	236	java/lang/Exception
    //   194	208	232	finally
    //   214	217	213	finally
    //   217	219	236	java/lang/Exception
    //   217	219	232	finally
    //   240	254	236	java/lang/Exception
    //   240	254	232	finally
    //   264	273	291	finally
    //   273	291	291	finally
  }
  
  private static DynamiteModule zze(Context paramContext, String paramString) {
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      "Selected local version of ".concat(paramString);
    } else {
      new String("Selected local version of ");
    } 
    return new DynamiteModule(paramContext.getApplicationContext());
  }
  
  private static zzi zzj(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/dynamite/DynamiteModule
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/dynamite/DynamiteModule.zzig : Lcom/google/android/gms/dynamite/zzi;
    //   6: ifnull -> 18
    //   9: getstatic com/google/android/gms/dynamite/DynamiteModule.zzig : Lcom/google/android/gms/dynamite/zzi;
    //   12: astore_0
    //   13: ldc com/google/android/gms/dynamite/DynamiteModule
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: invokestatic getInstance : ()Lcom/google/android/gms/common/GoogleApiAvailabilityLight;
    //   21: aload_0
    //   22: invokevirtual isGooglePlayServicesAvailable : (Landroid/content/Context;)I
    //   25: ifeq -> 33
    //   28: ldc com/google/android/gms/dynamite/DynamiteModule
    //   30: monitorexit
    //   31: aconst_null
    //   32: areturn
    //   33: aload_0
    //   34: ldc_w 'com.google.android.gms'
    //   37: iconst_3
    //   38: invokevirtual createPackageContext : (Ljava/lang/String;I)Landroid/content/Context;
    //   41: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   44: ldc_w 'com.google.android.gms.chimera.container.DynamiteLoaderImpl'
    //   47: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   50: invokevirtual newInstance : ()Ljava/lang/Object;
    //   53: checkcast android/os/IBinder
    //   56: astore_1
    //   57: aload_1
    //   58: ifnonnull -> 66
    //   61: aconst_null
    //   62: astore_0
    //   63: goto -> 100
    //   66: aload_1
    //   67: ldc_w 'com.google.android.gms.dynamite.IDynamiteLoader'
    //   70: invokeinterface queryLocalInterface : (Ljava/lang/String;)Landroid/os/IInterface;
    //   75: astore_0
    //   76: aload_0
    //   77: instanceof com/google/android/gms/dynamite/zzi
    //   80: ifeq -> 91
    //   83: aload_0
    //   84: checkcast com/google/android/gms/dynamite/zzi
    //   87: astore_0
    //   88: goto -> 100
    //   91: new com/google/android/gms/dynamite/zzj
    //   94: dup
    //   95: aload_1
    //   96: invokespecial <init> : (Landroid/os/IBinder;)V
    //   99: astore_0
    //   100: aload_0
    //   101: ifnull -> 151
    //   104: aload_0
    //   105: putstatic com/google/android/gms/dynamite/DynamiteModule.zzig : Lcom/google/android/gms/dynamite/zzi;
    //   108: ldc com/google/android/gms/dynamite/DynamiteModule
    //   110: monitorexit
    //   111: aload_0
    //   112: areturn
    //   113: astore_0
    //   114: aload_0
    //   115: invokevirtual getMessage : ()Ljava/lang/String;
    //   118: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   121: astore_0
    //   122: aload_0
    //   123: invokevirtual length : ()I
    //   126: ifeq -> 140
    //   129: ldc_w 'Failed to load IDynamiteLoader from GmsCore: '
    //   132: aload_0
    //   133: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   136: pop
    //   137: goto -> 151
    //   140: new java/lang/String
    //   143: dup
    //   144: ldc_w 'Failed to load IDynamiteLoader from GmsCore: '
    //   147: invokespecial <init> : (Ljava/lang/String;)V
    //   150: pop
    //   151: ldc com/google/android/gms/dynamite/DynamiteModule
    //   153: monitorexit
    //   154: aconst_null
    //   155: areturn
    //   156: astore_0
    //   157: ldc com/google/android/gms/dynamite/DynamiteModule
    //   159: monitorexit
    //   160: aload_0
    //   161: athrow
    // Exception table:
    //   from	to	target	type
    //   3	16	156	finally
    //   18	31	156	finally
    //   33	57	113	java/lang/Exception
    //   33	57	156	finally
    //   66	88	113	java/lang/Exception
    //   66	88	156	finally
    //   91	100	113	java/lang/Exception
    //   91	100	156	finally
    //   104	108	113	java/lang/Exception
    //   104	108	156	finally
    //   108	111	156	finally
    //   114	137	156	finally
    //   140	151	156	finally
    //   151	154	156	finally
    //   157	160	156	finally
  }
  
  public final Context getModuleContext() {
    return this.zzin;
  }
  
  public final IBinder instantiate(String paramString) throws LoadingException {
    try {
      return (IBinder)this.zzin.getClassLoader().loadClass(paramString).newInstance();
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (InstantiationException instantiationException) {
    
    } catch (IllegalAccessException illegalAccessException) {}
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "Failed to instantiate module class: ".concat(paramString);
    } else {
      paramString = new String("Failed to instantiate module class: ");
    } 
    throw new LoadingException(paramString, illegalAccessException, null);
  }
  
  @DynamiteApi
  public static class DynamiteLoaderClassLoader {
    public static ClassLoader sClassLoader;
  }
  
  public static class LoadingException extends Exception {
    private LoadingException(String param1String) {
      super(param1String);
    }
    
    private LoadingException(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
  }
  
  public static interface VersionPolicy {
    zzb zza(Context param1Context, String param1String, zza param1zza) throws DynamiteModule.LoadingException;
    
    public static interface zza {
      int getLocalVersion(Context param2Context, String param2String);
      
      int zza(Context param2Context, String param2String, boolean param2Boolean) throws DynamiteModule.LoadingException;
    }
    
    public static final class zzb {
      public int zzir = 0;
      
      public int zzis = 0;
      
      public int zzit = 0;
    }
  }
  
  public static interface zza {
    int getLocalVersion(Context param1Context, String param1String);
    
    int zza(Context param1Context, String param1String, boolean param1Boolean) throws DynamiteModule.LoadingException;
  }
  
  public static final class zzb {
    public int zzir = 0;
    
    public int zzis = 0;
    
    public int zzit = 0;
  }
  
  private static final class zza {
    public Cursor zzio;
    
    private zza() {}
  }
  
  private static final class zzb implements VersionPolicy.zza {
    private final int zzip;
    
    public zzb(int param1Int1, int param1Int2) {
      this.zzip = param1Int1;
    }
    
    public final int getLocalVersion(Context param1Context, String param1String) {
      return this.zzip;
    }
    
    public final int zza(Context param1Context, String param1String, boolean param1Boolean) {
      return 0;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamite/DynamiteModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */