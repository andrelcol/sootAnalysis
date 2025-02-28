package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GoogleApiManager implements Handler.Callback {
  private static final Object lock;
  
  public static final Status zahx = new Status(4, "Sign-out occurred while this API call was in progress.");
  
  private static final Status zahy = new Status(4, "The user must be signed in to make this API call.");
  
  private static GoogleApiManager zaic;
  
  private final Handler handler;
  
  private long zahz = 5000L;
  
  private long zaia = 120000L;
  
  private long zaib = 10000L;
  
  private final Context zaid;
  
  private final GoogleApiAvailability zaie;
  
  private final GoogleApiAvailabilityCache zaif;
  
  private final AtomicInteger zaig = new AtomicInteger(1);
  
  private final AtomicInteger zaih = new AtomicInteger(0);
  
  private final Map<zai<?>, zaa<?>> zaii = new ConcurrentHashMap<zai<?>, zaa<?>>(5, 0.75F, 1);
  
  private zaae zaij = null;
  
  private final Set<zai<?>> zaik = (Set<zai<?>>)new ArraySet();
  
  private final Set<zai<?>> zail = (Set<zai<?>>)new ArraySet();
  
  static {
    lock = new Object();
  }
  
  private GoogleApiManager(Context paramContext, Looper paramLooper, GoogleApiAvailability paramGoogleApiAvailability) {
    this.zaid = paramContext;
    this.handler = (Handler)new zap(paramLooper, this);
    this.zaie = paramGoogleApiAvailability;
    this.zaif = new GoogleApiAvailabilityCache((GoogleApiAvailabilityLight)paramGoogleApiAvailability);
    Handler handler = this.handler;
    handler.sendMessage(handler.obtainMessage(6));
  }
  
  public static GoogleApiManager zab(Context paramContext) {
    synchronized (lock) {
      if (zaic == null) {
        HandlerThread handlerThread = new HandlerThread();
        this("GoogleApiHandler", 9);
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        GoogleApiManager googleApiManager = new GoogleApiManager();
        this(paramContext.getApplicationContext(), looper, GoogleApiAvailability.getInstance());
        zaic = googleApiManager;
      } 
      return zaic;
    } 
  }
  
  private final void zab(GoogleApi<?> paramGoogleApi) {
    zai<?> zai = paramGoogleApi.zak();
    zaa<?> zaa2 = this.zaii.get(zai);
    zaa<?> zaa1 = zaa2;
    if (zaa2 == null) {
      zaa1 = new zaa(this, paramGoogleApi);
      this.zaii.put(zai, zaa1);
    } 
    if (zaa1.requiresSignIn())
      this.zail.add(zai); 
    zaa1.connect();
  }
  
  public static GoogleApiManager zabc() {
    synchronized (lock) {
      Preconditions.checkNotNull(zaic, "Must guarantee manager is non-null before using getInstance");
      return zaic;
    } 
  }
  
  public boolean handleMessage(Message paramMessage) {
    // Byte code:
    //   0: aload_1
    //   1: getfield what : I
    //   4: istore_2
    //   5: ldc2_w 300000
    //   8: lstore #4
    //   10: iload_2
    //   11: tableswitch default -> 88, 1 -> 1086, 2 -> 935, 3 -> 887, 4 -> 766, 5 -> 545, 6 -> 479, 7 -> 465, 8 -> 766, 9 -> 427, 10 -> 365, 11 -> 327, 12 -> 288, 13 -> 766, 14 -> 212, 15 -> 165, 16 -> 118
    //   88: new java/lang/StringBuilder
    //   91: dup
    //   92: bipush #31
    //   94: invokespecial <init> : (I)V
    //   97: astore_1
    //   98: aload_1
    //   99: ldc 'Unknown message id: '
    //   101: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: aload_1
    //   106: iload_2
    //   107: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   110: pop
    //   111: aload_1
    //   112: invokevirtual toString : ()Ljava/lang/String;
    //   115: pop
    //   116: iconst_0
    //   117: ireturn
    //   118: aload_1
    //   119: getfield obj : Ljava/lang/Object;
    //   122: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zab
    //   125: astore_1
    //   126: aload_0
    //   127: getfield zaii : Ljava/util/Map;
    //   130: aload_1
    //   131: invokestatic zac : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zab;)Lcom/google/android/gms/common/api/internal/zai;
    //   134: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   139: ifeq -> 1182
    //   142: aload_0
    //   143: getfield zaii : Ljava/util/Map;
    //   146: aload_1
    //   147: invokestatic zac : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zab;)Lcom/google/android/gms/common/api/internal/zai;
    //   150: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   155: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   158: aload_1
    //   159: invokestatic zab : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zaa;Lcom/google/android/gms/common/api/internal/GoogleApiManager$zab;)V
    //   162: goto -> 1182
    //   165: aload_1
    //   166: getfield obj : Ljava/lang/Object;
    //   169: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zab
    //   172: astore_1
    //   173: aload_0
    //   174: getfield zaii : Ljava/util/Map;
    //   177: aload_1
    //   178: invokestatic zac : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zab;)Lcom/google/android/gms/common/api/internal/zai;
    //   181: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   186: ifeq -> 1182
    //   189: aload_0
    //   190: getfield zaii : Ljava/util/Map;
    //   193: aload_1
    //   194: invokestatic zac : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zab;)Lcom/google/android/gms/common/api/internal/zai;
    //   197: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   202: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   205: aload_1
    //   206: invokestatic zaa : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zaa;Lcom/google/android/gms/common/api/internal/GoogleApiManager$zab;)V
    //   209: goto -> 1182
    //   212: aload_1
    //   213: getfield obj : Ljava/lang/Object;
    //   216: checkcast com/google/android/gms/common/api/internal/zaaf
    //   219: astore #6
    //   221: aload #6
    //   223: invokevirtual zak : ()Lcom/google/android/gms/common/api/internal/zai;
    //   226: astore_1
    //   227: aload_0
    //   228: getfield zaii : Ljava/util/Map;
    //   231: aload_1
    //   232: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   237: ifne -> 255
    //   240: aload #6
    //   242: invokevirtual zaal : ()Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   245: iconst_0
    //   246: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   249: invokevirtual setResult : (Ljava/lang/Object;)V
    //   252: goto -> 1182
    //   255: aload_0
    //   256: getfield zaii : Ljava/util/Map;
    //   259: aload_1
    //   260: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   265: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   268: iconst_0
    //   269: invokestatic zaa : (Lcom/google/android/gms/common/api/internal/GoogleApiManager$zaa;Z)Z
    //   272: istore_3
    //   273: aload #6
    //   275: invokevirtual zaal : ()Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   278: iload_3
    //   279: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   282: invokevirtual setResult : (Ljava/lang/Object;)V
    //   285: goto -> 1182
    //   288: aload_0
    //   289: getfield zaii : Ljava/util/Map;
    //   292: aload_1
    //   293: getfield obj : Ljava/lang/Object;
    //   296: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   301: ifeq -> 1182
    //   304: aload_0
    //   305: getfield zaii : Ljava/util/Map;
    //   308: aload_1
    //   309: getfield obj : Ljava/lang/Object;
    //   312: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   317: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   320: invokevirtual zabp : ()Z
    //   323: pop
    //   324: goto -> 1182
    //   327: aload_0
    //   328: getfield zaii : Ljava/util/Map;
    //   331: aload_1
    //   332: getfield obj : Ljava/lang/Object;
    //   335: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   340: ifeq -> 1182
    //   343: aload_0
    //   344: getfield zaii : Ljava/util/Map;
    //   347: aload_1
    //   348: getfield obj : Ljava/lang/Object;
    //   351: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   356: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   359: invokevirtual zaav : ()V
    //   362: goto -> 1182
    //   365: aload_0
    //   366: getfield zail : Ljava/util/Set;
    //   369: invokeinterface iterator : ()Ljava/util/Iterator;
    //   374: astore_1
    //   375: aload_1
    //   376: invokeinterface hasNext : ()Z
    //   381: ifeq -> 415
    //   384: aload_1
    //   385: invokeinterface next : ()Ljava/lang/Object;
    //   390: checkcast com/google/android/gms/common/api/internal/zai
    //   393: astore #6
    //   395: aload_0
    //   396: getfield zaii : Ljava/util/Map;
    //   399: aload #6
    //   401: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   406: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   409: invokevirtual zabj : ()V
    //   412: goto -> 375
    //   415: aload_0
    //   416: getfield zail : Ljava/util/Set;
    //   419: invokeinterface clear : ()V
    //   424: goto -> 1182
    //   427: aload_0
    //   428: getfield zaii : Ljava/util/Map;
    //   431: aload_1
    //   432: getfield obj : Ljava/lang/Object;
    //   435: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   440: ifeq -> 1182
    //   443: aload_0
    //   444: getfield zaii : Ljava/util/Map;
    //   447: aload_1
    //   448: getfield obj : Ljava/lang/Object;
    //   451: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   456: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   459: invokevirtual resume : ()V
    //   462: goto -> 1182
    //   465: aload_0
    //   466: aload_1
    //   467: getfield obj : Ljava/lang/Object;
    //   470: checkcast com/google/android/gms/common/api/GoogleApi
    //   473: invokespecial zab : (Lcom/google/android/gms/common/api/GoogleApi;)V
    //   476: goto -> 1182
    //   479: invokestatic isAtLeastIceCreamSandwich : ()Z
    //   482: ifeq -> 1182
    //   485: aload_0
    //   486: getfield zaid : Landroid/content/Context;
    //   489: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   492: instanceof android/app/Application
    //   495: ifeq -> 1182
    //   498: aload_0
    //   499: getfield zaid : Landroid/content/Context;
    //   502: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   505: checkcast android/app/Application
    //   508: invokestatic initialize : (Landroid/app/Application;)V
    //   511: invokestatic getInstance : ()Lcom/google/android/gms/common/api/internal/BackgroundDetector;
    //   514: new com/google/android/gms/common/api/internal/zabi
    //   517: dup
    //   518: aload_0
    //   519: invokespecial <init> : (Lcom/google/android/gms/common/api/internal/GoogleApiManager;)V
    //   522: invokevirtual addListener : (Lcom/google/android/gms/common/api/internal/BackgroundDetector$BackgroundStateChangeListener;)V
    //   525: invokestatic getInstance : ()Lcom/google/android/gms/common/api/internal/BackgroundDetector;
    //   528: iconst_1
    //   529: invokevirtual readCurrentStateIfPossible : (Z)Z
    //   532: ifne -> 1182
    //   535: aload_0
    //   536: ldc2_w 300000
    //   539: putfield zaib : J
    //   542: goto -> 1182
    //   545: aload_1
    //   546: getfield arg1 : I
    //   549: istore_2
    //   550: aload_1
    //   551: getfield obj : Ljava/lang/Object;
    //   554: checkcast com/google/android/gms/common/ConnectionResult
    //   557: astore #6
    //   559: aload_0
    //   560: getfield zaii : Ljava/util/Map;
    //   563: invokeinterface values : ()Ljava/util/Collection;
    //   568: invokeinterface iterator : ()Ljava/util/Iterator;
    //   573: astore #7
    //   575: aload #7
    //   577: invokeinterface hasNext : ()Z
    //   582: ifeq -> 607
    //   585: aload #7
    //   587: invokeinterface next : ()Ljava/lang/Object;
    //   592: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   595: astore_1
    //   596: aload_1
    //   597: invokevirtual getInstanceId : ()I
    //   600: iload_2
    //   601: if_icmpne -> 575
    //   604: goto -> 609
    //   607: aconst_null
    //   608: astore_1
    //   609: aload_1
    //   610: ifnull -> 718
    //   613: aload_0
    //   614: getfield zaie : Lcom/google/android/gms/common/GoogleApiAvailability;
    //   617: aload #6
    //   619: invokevirtual getErrorCode : ()I
    //   622: invokevirtual getErrorString : (I)Ljava/lang/String;
    //   625: astore #7
    //   627: aload #6
    //   629: invokevirtual getErrorMessage : ()Ljava/lang/String;
    //   632: astore #6
    //   634: new java/lang/StringBuilder
    //   637: dup
    //   638: aload #7
    //   640: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   643: invokevirtual length : ()I
    //   646: bipush #69
    //   648: iadd
    //   649: aload #6
    //   651: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   654: invokevirtual length : ()I
    //   657: iadd
    //   658: invokespecial <init> : (I)V
    //   661: astore #8
    //   663: aload #8
    //   665: ldc_w 'Error resolution was canceled by the user, original error message: '
    //   668: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   671: pop
    //   672: aload #8
    //   674: aload #7
    //   676: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   679: pop
    //   680: aload #8
    //   682: ldc_w ': '
    //   685: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: pop
    //   689: aload #8
    //   691: aload #6
    //   693: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   696: pop
    //   697: aload_1
    //   698: new com/google/android/gms/common/api/Status
    //   701: dup
    //   702: bipush #17
    //   704: aload #8
    //   706: invokevirtual toString : ()Ljava/lang/String;
    //   709: invokespecial <init> : (ILjava/lang/String;)V
    //   712: invokevirtual zac : (Lcom/google/android/gms/common/api/Status;)V
    //   715: goto -> 1182
    //   718: new java/lang/StringBuilder
    //   721: dup
    //   722: bipush #76
    //   724: invokespecial <init> : (I)V
    //   727: astore_1
    //   728: aload_1
    //   729: ldc_w 'Could not find API instance '
    //   732: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   735: pop
    //   736: aload_1
    //   737: iload_2
    //   738: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   741: pop
    //   742: aload_1
    //   743: ldc_w ' while trying to fail enqueued calls.'
    //   746: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: pop
    //   750: aload_1
    //   751: invokevirtual toString : ()Ljava/lang/String;
    //   754: pop
    //   755: new java/lang/Exception
    //   758: dup
    //   759: invokespecial <init> : ()V
    //   762: pop
    //   763: goto -> 1182
    //   766: aload_1
    //   767: getfield obj : Ljava/lang/Object;
    //   770: checkcast com/google/android/gms/common/api/internal/zabv
    //   773: astore #7
    //   775: aload_0
    //   776: getfield zaii : Ljava/util/Map;
    //   779: aload #7
    //   781: getfield zajt : Lcom/google/android/gms/common/api/GoogleApi;
    //   784: invokevirtual zak : ()Lcom/google/android/gms/common/api/internal/zai;
    //   787: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   792: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   795: astore #6
    //   797: aload #6
    //   799: astore_1
    //   800: aload #6
    //   802: ifnonnull -> 835
    //   805: aload_0
    //   806: aload #7
    //   808: getfield zajt : Lcom/google/android/gms/common/api/GoogleApi;
    //   811: invokespecial zab : (Lcom/google/android/gms/common/api/GoogleApi;)V
    //   814: aload_0
    //   815: getfield zaii : Ljava/util/Map;
    //   818: aload #7
    //   820: getfield zajt : Lcom/google/android/gms/common/api/GoogleApi;
    //   823: invokevirtual zak : ()Lcom/google/android/gms/common/api/internal/zai;
    //   826: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   831: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   834: astore_1
    //   835: aload_1
    //   836: invokevirtual requiresSignIn : ()Z
    //   839: ifeq -> 875
    //   842: aload_0
    //   843: getfield zaih : Ljava/util/concurrent/atomic/AtomicInteger;
    //   846: invokevirtual get : ()I
    //   849: aload #7
    //   851: getfield zajs : I
    //   854: if_icmpeq -> 875
    //   857: aload #7
    //   859: getfield zajr : Lcom/google/android/gms/common/api/internal/zab;
    //   862: getstatic com/google/android/gms/common/api/internal/GoogleApiManager.zahx : Lcom/google/android/gms/common/api/Status;
    //   865: invokevirtual zaa : (Lcom/google/android/gms/common/api/Status;)V
    //   868: aload_1
    //   869: invokevirtual zabj : ()V
    //   872: goto -> 1182
    //   875: aload_1
    //   876: aload #7
    //   878: getfield zajr : Lcom/google/android/gms/common/api/internal/zab;
    //   881: invokevirtual zaa : (Lcom/google/android/gms/common/api/internal/zab;)V
    //   884: goto -> 1182
    //   887: aload_0
    //   888: getfield zaii : Ljava/util/Map;
    //   891: invokeinterface values : ()Ljava/util/Collection;
    //   896: invokeinterface iterator : ()Ljava/util/Iterator;
    //   901: astore #6
    //   903: aload #6
    //   905: invokeinterface hasNext : ()Z
    //   910: ifeq -> 1182
    //   913: aload #6
    //   915: invokeinterface next : ()Ljava/lang/Object;
    //   920: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   923: astore_1
    //   924: aload_1
    //   925: invokevirtual zabl : ()V
    //   928: aload_1
    //   929: invokevirtual connect : ()V
    //   932: goto -> 903
    //   935: aload_1
    //   936: getfield obj : Ljava/lang/Object;
    //   939: checkcast com/google/android/gms/common/api/internal/zak
    //   942: astore #8
    //   944: aload #8
    //   946: invokevirtual zap : ()Ljava/util/Set;
    //   949: invokeinterface iterator : ()Ljava/util/Iterator;
    //   954: astore_1
    //   955: aload_1
    //   956: invokeinterface hasNext : ()Z
    //   961: ifeq -> 1182
    //   964: aload_1
    //   965: invokeinterface next : ()Ljava/lang/Object;
    //   970: checkcast com/google/android/gms/common/api/internal/zai
    //   973: astore #7
    //   975: aload_0
    //   976: getfield zaii : Ljava/util/Map;
    //   979: aload #7
    //   981: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   986: checkcast com/google/android/gms/common/api/internal/GoogleApiManager$zaa
    //   989: astore #6
    //   991: aload #6
    //   993: ifnonnull -> 1016
    //   996: aload #8
    //   998: aload #7
    //   1000: new com/google/android/gms/common/ConnectionResult
    //   1003: dup
    //   1004: bipush #13
    //   1006: invokespecial <init> : (I)V
    //   1009: aconst_null
    //   1010: invokevirtual zaa : (Lcom/google/android/gms/common/api/internal/zai;Lcom/google/android/gms/common/ConnectionResult;Ljava/lang/String;)V
    //   1013: goto -> 1182
    //   1016: aload #6
    //   1018: invokevirtual isConnected : ()Z
    //   1021: ifeq -> 1047
    //   1024: aload #8
    //   1026: aload #7
    //   1028: getstatic com/google/android/gms/common/ConnectionResult.RESULT_SUCCESS : Lcom/google/android/gms/common/ConnectionResult;
    //   1031: aload #6
    //   1033: invokevirtual zaab : ()Lcom/google/android/gms/common/api/Api$Client;
    //   1036: invokeinterface getEndpointPackageName : ()Ljava/lang/String;
    //   1041: invokevirtual zaa : (Lcom/google/android/gms/common/api/internal/zai;Lcom/google/android/gms/common/ConnectionResult;Ljava/lang/String;)V
    //   1044: goto -> 955
    //   1047: aload #6
    //   1049: invokevirtual zabm : ()Lcom/google/android/gms/common/ConnectionResult;
    //   1052: ifnull -> 1071
    //   1055: aload #8
    //   1057: aload #7
    //   1059: aload #6
    //   1061: invokevirtual zabm : ()Lcom/google/android/gms/common/ConnectionResult;
    //   1064: aconst_null
    //   1065: invokevirtual zaa : (Lcom/google/android/gms/common/api/internal/zai;Lcom/google/android/gms/common/ConnectionResult;Ljava/lang/String;)V
    //   1068: goto -> 955
    //   1071: aload #6
    //   1073: aload #8
    //   1075: invokevirtual zaa : (Lcom/google/android/gms/common/api/internal/zak;)V
    //   1078: aload #6
    //   1080: invokevirtual connect : ()V
    //   1083: goto -> 955
    //   1086: aload_1
    //   1087: getfield obj : Ljava/lang/Object;
    //   1090: checkcast java/lang/Boolean
    //   1093: invokevirtual booleanValue : ()Z
    //   1096: ifeq -> 1104
    //   1099: ldc2_w 10000
    //   1102: lstore #4
    //   1104: aload_0
    //   1105: lload #4
    //   1107: putfield zaib : J
    //   1110: aload_0
    //   1111: getfield handler : Landroid/os/Handler;
    //   1114: bipush #12
    //   1116: invokevirtual removeMessages : (I)V
    //   1119: aload_0
    //   1120: getfield zaii : Ljava/util/Map;
    //   1123: invokeinterface keySet : ()Ljava/util/Set;
    //   1128: invokeinterface iterator : ()Ljava/util/Iterator;
    //   1133: astore_1
    //   1134: aload_1
    //   1135: invokeinterface hasNext : ()Z
    //   1140: ifeq -> 1182
    //   1143: aload_1
    //   1144: invokeinterface next : ()Ljava/lang/Object;
    //   1149: checkcast com/google/android/gms/common/api/internal/zai
    //   1152: astore #6
    //   1154: aload_0
    //   1155: getfield handler : Landroid/os/Handler;
    //   1158: astore #7
    //   1160: aload #7
    //   1162: aload #7
    //   1164: bipush #12
    //   1166: aload #6
    //   1168: invokevirtual obtainMessage : (ILjava/lang/Object;)Landroid/os/Message;
    //   1171: aload_0
    //   1172: getfield zaib : J
    //   1175: invokevirtual sendMessageDelayed : (Landroid/os/Message;J)Z
    //   1178: pop
    //   1179: goto -> 1134
    //   1182: iconst_1
    //   1183: ireturn
  }
  
  final PendingIntent zaa(zai<?> paramzai, int paramInt) {
    zaa zaa = this.zaii.get(paramzai);
    if (zaa == null)
      return null; 
    zad zad = zaa.zabq();
    if (zad == null)
      return null; 
    zad.getSignInIntent();
    throw null;
  }
  
  public final Task<Map<zai<?>, String>> zaa(Iterable<? extends GoogleApi<?>> paramIterable) {
    zak zak = new zak(paramIterable);
    Handler handler = this.handler;
    handler.sendMessage(handler.obtainMessage(2, zak));
    return zak.getTask();
  }
  
  public final void zaa(ConnectionResult paramConnectionResult, int paramInt) {
    if (!zac(paramConnectionResult, paramInt)) {
      Handler handler = this.handler;
      handler.sendMessage(handler.obtainMessage(5, paramInt, 0, paramConnectionResult));
    } 
  }
  
  public final void zaa(GoogleApi<?> paramGoogleApi) {
    Handler handler = this.handler;
    handler.sendMessage(handler.obtainMessage(7, paramGoogleApi));
  }
  
  public final <O extends Api.ApiOptions> void zaa(GoogleApi<O> paramGoogleApi, int paramInt, BaseImplementation$ApiMethodImpl<? extends Result, Api.AnyClient> paramBaseImplementation$ApiMethodImpl) {
    zae<BaseImplementation$ApiMethodImpl<? extends Result, Api.AnyClient>> zae = new zae<BaseImplementation$ApiMethodImpl<? extends Result, Api.AnyClient>>(paramInt, paramBaseImplementation$ApiMethodImpl);
    Handler handler = this.handler;
    handler.sendMessage(handler.obtainMessage(4, new zabv(zae, this.zaih.get(), paramGoogleApi)));
  }
  
  public final int zabd() {
    return this.zaig.getAndIncrement();
  }
  
  final boolean zac(ConnectionResult paramConnectionResult, int paramInt) {
    return this.zaie.zaa(this.zaid, paramConnectionResult, paramInt);
  }
  
  public final void zao() {
    Handler handler = this.handler;
    handler.sendMessage(handler.obtainMessage(3));
  }
  
  public final class zaa<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zar {
    private final zai<O> zafq;
    
    final GoogleApiManager zaim;
    
    private final Queue<zab> zain = new LinkedList<zab>();
    
    private final Api.Client zaio;
    
    private final Api.AnyClient zaip;
    
    private final zaab zaiq;
    
    private final Set<zak> zair = new HashSet<zak>();
    
    private final Map<ListenerHolder.ListenerKey<?>, zabw> zais = new HashMap<ListenerHolder.ListenerKey<?>, zabw>();
    
    private final int zait;
    
    private final zace zaiu;
    
    private boolean zaiv;
    
    private final List<GoogleApiManager.zab> zaiw = new ArrayList<GoogleApiManager.zab>();
    
    private ConnectionResult zaix = null;
    
    public zaa(GoogleApiManager this$0, GoogleApi<O> param1GoogleApi) {
      this.zaio = param1GoogleApi.zaa(GoogleApiManager.zaa(this$0).getLooper(), this);
      Api.Client client = this.zaio;
      if (client instanceof SimpleClientAdapter) {
        this.zaip = (Api.AnyClient)((SimpleClientAdapter)client).getClient();
      } else {
        this.zaip = (Api.AnyClient)client;
      } 
      this.zafq = param1GoogleApi.zak();
      this.zaiq = new zaab();
      this.zait = param1GoogleApi.getInstanceId();
      if (this.zaio.requiresSignIn()) {
        this.zaiu = param1GoogleApi.zaa(GoogleApiManager.zab(this$0), GoogleApiManager.zaa(this$0));
        return;
      } 
      this.zaiu = null;
    }
    
    private final Feature zaa(Feature[] param1ArrayOfFeature) {
      if (param1ArrayOfFeature != null && param1ArrayOfFeature.length != 0) {
        Feature[] arrayOfFeature2 = this.zaio.getAvailableFeatures();
        boolean bool = false;
        Feature[] arrayOfFeature1 = arrayOfFeature2;
        if (arrayOfFeature2 == null)
          arrayOfFeature1 = new Feature[0]; 
        ArrayMap<String, Long> arrayMap = new ArrayMap(arrayOfFeature1.length);
        int i = arrayOfFeature1.length;
        byte b;
        for (b = 0; b < i; b++) {
          Feature feature = arrayOfFeature1[b];
          arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
        } 
        i = param1ArrayOfFeature.length;
        for (b = bool; b < i; b++) {
          Feature feature = param1ArrayOfFeature[b];
          if (!arrayMap.containsKey(feature.getName()) || ((Long)arrayMap.get(feature.getName())).longValue() < feature.getVersion())
            return feature; 
        } 
      } 
      return null;
    }
    
    private final void zaa(GoogleApiManager.zab param1zab) {
      if (!this.zaiw.contains(param1zab))
        return; 
      if (!this.zaiv) {
        if (!this.zaio.isConnected()) {
          connect();
          return;
        } 
        zabi();
      } 
    }
    
    private final void zab(GoogleApiManager.zab param1zab) {
      if (this.zaiw.remove(param1zab)) {
        GoogleApiManager.zaa(this.zaim).removeMessages(15, param1zab);
        GoogleApiManager.zaa(this.zaim).removeMessages(16, param1zab);
        Feature feature = GoogleApiManager.zab.zad(param1zab);
        ArrayList<zab> arrayList = new ArrayList(this.zain.size());
        for (zab zab1 : this.zain) {
          if (zab1 instanceof zac) {
            Feature[] arrayOfFeature = ((zac)zab1).zab(this);
            if (arrayOfFeature != null && ArrayUtils.contains((Object[])arrayOfFeature, feature))
              arrayList.add(zab1); 
          } 
        } 
        int i = arrayList.size();
        byte b = 0;
        while (b < i) {
          zab zab1 = (zab)arrayList.get(b);
          b++;
          zab1 = zab1;
          this.zain.remove(zab1);
          zab1.zaa((RuntimeException)new UnsupportedApiCallException(feature));
        } 
      } 
    }
    
    private final boolean zab(zab param1zab) {
      if (!(param1zab instanceof zac)) {
        zac(param1zab);
        return true;
      } 
      zac zac = (zac)param1zab;
      Feature feature = zaa(zac.zab(this));
      if (feature == null) {
        zac(param1zab);
        return true;
      } 
      if (zac.zac(this)) {
        GoogleApiManager.zab zab1 = new GoogleApiManager.zab(this.zafq, feature, null);
        int i = this.zaiw.indexOf(zab1);
        if (i >= 0) {
          zab1 = this.zaiw.get(i);
          GoogleApiManager.zaa(this.zaim).removeMessages(15, zab1);
          GoogleApiManager.zaa(this.zaim).sendMessageDelayed(Message.obtain(GoogleApiManager.zaa(this.zaim), 15, zab1), GoogleApiManager.zac(this.zaim));
        } else {
          this.zaiw.add(zab1);
          GoogleApiManager.zaa(this.zaim).sendMessageDelayed(Message.obtain(GoogleApiManager.zaa(this.zaim), 15, zab1), GoogleApiManager.zac(this.zaim));
          GoogleApiManager.zaa(this.zaim).sendMessageDelayed(Message.obtain(GoogleApiManager.zaa(this.zaim), 16, zab1), GoogleApiManager.zad(this.zaim));
          ConnectionResult connectionResult = new ConnectionResult(2, null);
          if (!zah(connectionResult))
            this.zaim.zac(connectionResult, this.zait); 
        } 
      } else {
        zac.zaa((RuntimeException)new UnsupportedApiCallException(feature));
      } 
      return false;
    }
    
    private final void zabg() {
      zabl();
      zai(ConnectionResult.RESULT_SUCCESS);
      zabn();
      Iterator<zabw> iterator = this.zais.values().iterator();
      while (iterator.hasNext()) {
        zabw zabw = iterator.next();
        if (zaa(zabw.zajx.getRequiredFeatures()) != null) {
          iterator.remove();
          continue;
        } 
        try {
          RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod = zabw.zajx;
          Api.AnyClient anyClient = this.zaip;
          TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource();
          this();
          registerListenerMethod.registerListener(anyClient, taskCompletionSource);
        } catch (DeadObjectException deadObjectException) {
          onConnectionSuspended(1);
          this.zaio.disconnect();
          break;
        } catch (RemoteException remoteException) {
          deadObjectException.remove();
        } 
      } 
      zabi();
      zabo();
    }
    
    private final void zabh() {
      zabl();
      this.zaiv = true;
      this.zaiq.zaai();
      GoogleApiManager.zaa(this.zaim).sendMessageDelayed(Message.obtain(GoogleApiManager.zaa(this.zaim), 9, this.zafq), GoogleApiManager.zac(this.zaim));
      GoogleApiManager.zaa(this.zaim).sendMessageDelayed(Message.obtain(GoogleApiManager.zaa(this.zaim), 11, this.zafq), GoogleApiManager.zad(this.zaim));
      GoogleApiManager.zae(this.zaim).flush();
    }
    
    private final void zabi() {
      ArrayList<zab> arrayList = new ArrayList<zab>(this.zain);
      int j = arrayList.size();
      int i = 0;
      while (i < j) {
        zab zab = (zab)arrayList.get(i);
        int k = i + 1;
        zab = zab;
        if (this.zaio.isConnected()) {
          i = k;
          if (zab(zab)) {
            this.zain.remove(zab);
            i = k;
          } 
        } 
      } 
    }
    
    private final void zabn() {
      if (this.zaiv) {
        GoogleApiManager.zaa(this.zaim).removeMessages(11, this.zafq);
        GoogleApiManager.zaa(this.zaim).removeMessages(9, this.zafq);
        this.zaiv = false;
      } 
    }
    
    private final void zabo() {
      GoogleApiManager.zaa(this.zaim).removeMessages(12, this.zafq);
      GoogleApiManager.zaa(this.zaim).sendMessageDelayed(GoogleApiManager.zaa(this.zaim).obtainMessage(12, this.zafq), GoogleApiManager.zai(this.zaim));
    }
    
    private final void zac(zab param1zab) {
      param1zab.zaa(this.zaiq, requiresSignIn());
      try {
        param1zab.zaa(this);
        return;
      } catch (DeadObjectException deadObjectException) {
        onConnectionSuspended(1);
        this.zaio.disconnect();
        return;
      } 
    }
    
    private final boolean zac(boolean param1Boolean) {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      if (this.zaio.isConnected() && this.zais.size() == 0) {
        if (this.zaiq.zaag()) {
          if (param1Boolean)
            zabo(); 
          return false;
        } 
        this.zaio.disconnect();
        return true;
      } 
      return false;
    }
    
    private final boolean zah(ConnectionResult param1ConnectionResult) {
      synchronized (GoogleApiManager.zabe()) {
        if (GoogleApiManager.zaf(this.zaim) != null && GoogleApiManager.zag(this.zaim).contains(this.zafq)) {
          GoogleApiManager.zaf(this.zaim).zab(param1ConnectionResult, this.zait);
          return true;
        } 
        return false;
      } 
    }
    
    private final void zai(ConnectionResult param1ConnectionResult) {
      for (zak zak : this.zair) {
        String str = null;
        if (Objects.equal(param1ConnectionResult, ConnectionResult.RESULT_SUCCESS))
          str = this.zaio.getEndpointPackageName(); 
        zak.zaa(this.zafq, param1ConnectionResult, str);
      } 
      this.zair.clear();
    }
    
    public final void connect() {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      if (!this.zaio.isConnected() && !this.zaio.isConnecting()) {
        int i = GoogleApiManager.zae(this.zaim).getClientAvailability(GoogleApiManager.zab(this.zaim), this.zaio);
        if (i != 0) {
          onConnectionFailed(new ConnectionResult(i, null));
          return;
        } 
        GoogleApiManager.zac zac = new GoogleApiManager.zac(this.zaim, this.zaio, this.zafq);
        if (this.zaio.requiresSignIn())
          this.zaiu.zaa(zac); 
        this.zaio.connect(zac);
      } 
    }
    
    public final int getInstanceId() {
      return this.zait;
    }
    
    final boolean isConnected() {
      return this.zaio.isConnected();
    }
    
    public final void onConnected(Bundle param1Bundle) {
      if (Looper.myLooper() == GoogleApiManager.zaa(this.zaim).getLooper()) {
        zabg();
        return;
      } 
      GoogleApiManager.zaa(this.zaim).post(new zabj(this));
    }
    
    public final void onConnectionFailed(ConnectionResult param1ConnectionResult) {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      zace zace1 = this.zaiu;
      if (zace1 != null)
        zace1.zabs(); 
      zabl();
      GoogleApiManager.zae(this.zaim).flush();
      zai(param1ConnectionResult);
      if (param1ConnectionResult.getErrorCode() == 4) {
        zac(GoogleApiManager.zabf());
        return;
      } 
      if (this.zain.isEmpty()) {
        this.zaix = param1ConnectionResult;
        return;
      } 
      if (zah(param1ConnectionResult))
        return; 
      if (!this.zaim.zac(param1ConnectionResult, this.zait)) {
        if (param1ConnectionResult.getErrorCode() == 18)
          this.zaiv = true; 
        if (this.zaiv) {
          GoogleApiManager.zaa(this.zaim).sendMessageDelayed(Message.obtain(GoogleApiManager.zaa(this.zaim), 9, this.zafq), GoogleApiManager.zac(this.zaim));
          return;
        } 
        String str = this.zafq.zan();
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 38);
        stringBuilder.append("API: ");
        stringBuilder.append(str);
        stringBuilder.append(" is not available on this device.");
        zac(new Status(17, stringBuilder.toString()));
      } 
    }
    
    public final void onConnectionSuspended(int param1Int) {
      if (Looper.myLooper() == GoogleApiManager.zaa(this.zaim).getLooper()) {
        zabh();
        return;
      } 
      GoogleApiManager.zaa(this.zaim).post(new zabk(this));
    }
    
    public final boolean requiresSignIn() {
      return this.zaio.requiresSignIn();
    }
    
    public final void resume() {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      if (this.zaiv)
        connect(); 
    }
    
    public final void zaa(ConnectionResult param1ConnectionResult, Api<?> param1Api, boolean param1Boolean) {
      if (Looper.myLooper() == GoogleApiManager.zaa(this.zaim).getLooper()) {
        onConnectionFailed(param1ConnectionResult);
        return;
      } 
      GoogleApiManager.zaa(this.zaim).post(new zabl(this, param1ConnectionResult));
    }
    
    public final void zaa(zab param1zab) {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      if (this.zaio.isConnected()) {
        if (zab(param1zab)) {
          zabo();
          return;
        } 
        this.zain.add(param1zab);
        return;
      } 
      this.zain.add(param1zab);
      ConnectionResult connectionResult = this.zaix;
      if (connectionResult != null && connectionResult.hasResolution()) {
        onConnectionFailed(this.zaix);
        return;
      } 
      connect();
    }
    
    public final void zaa(zak param1zak) {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      this.zair.add(param1zak);
    }
    
    public final Api.Client zaab() {
      return this.zaio;
    }
    
    public final void zaav() {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      if (this.zaiv) {
        Status status;
        zabn();
        if (GoogleApiManager.zah(this.zaim).isGooglePlayServicesAvailable(GoogleApiManager.zab(this.zaim)) == 18) {
          status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
        } else {
          status = new Status(8, "API failed to connect while resuming due to an unknown error.");
        } 
        zac(status);
        this.zaio.disconnect();
      } 
    }
    
    public final void zabj() {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      zac(GoogleApiManager.zahx);
      this.zaiq.zaah();
      ListenerHolder.ListenerKey[] arrayOfListenerKey = (ListenerHolder.ListenerKey[])this.zais.keySet().toArray((Object[])new ListenerHolder.ListenerKey[this.zais.size()]);
      int i = arrayOfListenerKey.length;
      for (byte b = 0; b < i; b++)
        zaa(new zah(arrayOfListenerKey[b], new TaskCompletionSource())); 
      zai(new ConnectionResult(4));
      if (this.zaio.isConnected())
        this.zaio.onUserSignOut(new zabm(this)); 
    }
    
    public final Map<ListenerHolder.ListenerKey<?>, zabw> zabk() {
      return this.zais;
    }
    
    public final void zabl() {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      this.zaix = null;
    }
    
    public final ConnectionResult zabm() {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      return this.zaix;
    }
    
    public final boolean zabp() {
      return zac(true);
    }
    
    final zad zabq() {
      zace zace1 = this.zaiu;
      return (zace1 == null) ? null : zace1.zabq();
    }
    
    public final void zac(Status param1Status) {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      Iterator<zab> iterator = this.zain.iterator();
      while (iterator.hasNext())
        ((zab)iterator.next()).zaa(param1Status); 
      this.zain.clear();
    }
    
    public final void zag(ConnectionResult param1ConnectionResult) {
      Preconditions.checkHandlerThread(GoogleApiManager.zaa(this.zaim));
      this.zaio.disconnect();
      onConnectionFailed(param1ConnectionResult);
    }
  }
  
  private static final class zab {
    private final zai<?> zajb;
    
    private final Feature zajc;
    
    private zab(zai<?> param1zai, Feature param1Feature) {
      this.zajb = param1zai;
      this.zajc = param1Feature;
    }
    
    public final boolean equals(Object param1Object) {
      if (param1Object != null && param1Object instanceof zab) {
        param1Object = param1Object;
        if (Objects.equal(this.zajb, ((zab)param1Object).zajb) && Objects.equal(this.zajc, ((zab)param1Object).zajc))
          return true; 
      } 
      return false;
    }
    
    public final int hashCode() {
      return Objects.hashCode(new Object[] { this.zajb, this.zajc });
    }
    
    public final String toString() {
      Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this);
      toStringHelper.add("key", this.zajb);
      toStringHelper.add("feature", this.zajc);
      return toStringHelper.toString();
    }
  }
  
  private final class zac implements zach, BaseGmsClient.ConnectionProgressReportCallbacks {
    private final zai<?> zafq;
    
    final GoogleApiManager zaim;
    
    private final Api.Client zaio;
    
    private IAccountAccessor zajd = null;
    
    private Set<Scope> zaje = null;
    
    private boolean zajf = false;
    
    public zac(GoogleApiManager this$0, Api.Client param1Client, zai<?> param1zai) {
      this.zaio = param1Client;
      this.zafq = param1zai;
    }
    
    private final void zabr() {
      if (this.zajf) {
        IAccountAccessor iAccountAccessor = this.zajd;
        if (iAccountAccessor != null)
          this.zaio.getRemoteService(iAccountAccessor, this.zaje); 
      } 
    }
    
    public final void onReportServiceBinding(ConnectionResult param1ConnectionResult) {
      GoogleApiManager.zaa(this.zaim).post(new zabo(this, param1ConnectionResult));
    }
    
    public final void zaa(IAccountAccessor param1IAccountAccessor, Set<Scope> param1Set) {
      if (param1IAccountAccessor == null || param1Set == null) {
        new Exception();
        zag(new ConnectionResult(4));
        return;
      } 
      this.zajd = param1IAccountAccessor;
      this.zaje = param1Set;
      zabr();
    }
    
    public final void zag(ConnectionResult param1ConnectionResult) {
      ((GoogleApiManager.zaa)GoogleApiManager.zaj(this.zaim).get(this.zafq)).zag(param1ConnectionResult);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/GoogleApiManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */