package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzda extends zzf {
  protected zzdu zzara;
  
  private zzcx zzarb;
  
  private final Set<zzcy> zzarc = new CopyOnWriteArraySet<zzcy>();
  
  private boolean zzard;
  
  private final AtomicReference<String> zzare = new AtomicReference<String>();
  
  protected boolean zzarf = true;
  
  protected zzda(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private final void zza(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty) {
    long l = zzbx().currentTimeMillis();
    Preconditions.checkNotNull(paramConditionalUserProperty);
    Preconditions.checkNotEmpty(paramConditionalUserProperty.mName);
    Preconditions.checkNotEmpty(paramConditionalUserProperty.mOrigin);
    Preconditions.checkNotNull(paramConditionalUserProperty.mValue);
    paramConditionalUserProperty.mCreationTimestamp = l;
    String str = paramConditionalUserProperty.mName;
    Object object1 = paramConditionalUserProperty.mValue;
    if (zzgr().zzcv(str) != 0) {
      zzgt().zzjg().zzg("Invalid conditional user property name", zzgq().zzbv(str));
      return;
    } 
    if (zzgr().zzi(str, object1) != 0) {
      zzgt().zzjg().zze("Invalid conditional user property value", zzgq().zzbv(str), object1);
      return;
    } 
    Object object2 = zzgr().zzj(str, object1);
    if (object2 == null) {
      zzgt().zzjg().zze("Unable to normalize conditional user property value", zzgq().zzbv(str), object1);
      return;
    } 
    paramConditionalUserProperty.mValue = object2;
    l = paramConditionalUserProperty.mTriggerTimeout;
    if (!TextUtils.isEmpty(paramConditionalUserProperty.mTriggerEventName) && (l > 15552000000L || l < 1L)) {
      zzgt().zzjg().zze("Invalid conditional user property timeout", zzgq().zzbv(str), Long.valueOf(l));
      return;
    } 
    l = paramConditionalUserProperty.mTimeToLive;
    if (l > 15552000000L || l < 1L) {
      zzgt().zzjg().zze("Invalid conditional user property time to live", zzgq().zzbv(str), Long.valueOf(l));
      return;
    } 
    zzgs().zzc(new zzdi(this, paramConditionalUserProperty));
  }
  
  private final void zza(String paramString1, String paramString2, long paramLong, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString3) {
    zzdx zzdx1;
    Preconditions.checkNotEmpty(paramString1);
    if (!zzgv().zze(paramString3, zzai.zzali))
      Preconditions.checkNotEmpty(paramString2); 
    Preconditions.checkNotNull(paramBundle);
    zzaf();
    zzcl();
    if (!this.zzada.isEnabled()) {
      zzgt().zzjn().zzby("Event not sent since app measurement is disabled");
      return;
    } 
    boolean bool1 = this.zzard;
    int j = 0;
    if (!bool1) {
      this.zzard = true;
      try {
        Class<?> clazz = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
        try {
          clazz.getDeclaredMethod("initialize", new Class[] { Context.class }).invoke(null, new Object[] { getContext() });
        } catch (Exception exception) {
          zzgt().zzjj().zzg("Failed to invoke Tag Manager's initialize() method", exception);
        } 
      } catch (ClassNotFoundException classNotFoundException) {
        zzgt().zzjm().zzby("Tag Manager is not found and thus will not be used");
      } 
    } 
    if (paramBoolean3) {
      zzgw();
      if (!"_iap".equals(paramString2)) {
        zzfx zzfx1 = this.zzada.zzgr();
        bool1 = zzfx1.zzs("event", paramString2);
        byte b = 2;
        if (bool1)
          if (!zzfx1.zza("event", zzcu.zzaqt, paramString2)) {
            b = 13;
          } else if (zzfx1.zza("event", 40, paramString2)) {
            b = 0;
          }  
        if (b != 0) {
          zzgt().zzji().zzg("Invalid public event name. Event will not be logged (FE)", zzgq().zzbt(paramString2));
          this.zzada.zzgr();
          paramString1 = zzfx.zza(paramString2, 40, true);
          if (paramString2 != null) {
            j = paramString2.length();
          } else {
            j = 0;
          } 
          this.zzada.zzgr().zza(b, "_ev", paramString1, j);
          return;
        } 
      } 
    } 
    zzgw();
    zzdx zzdx2 = zzgm().zzle();
    if (zzdx2 != null && !paramBundle.containsKey("_sc"))
      zzdx2.zzars = true; 
    if (paramBoolean1 && paramBoolean3) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    zzdy.zza(zzdx2, paramBundle, bool1);
    bool1 = "am".equals(paramString1);
    boolean bool2 = zzfx.zzcy(paramString2);
    if (paramBoolean1 && this.zzarb != null && !bool2 && !bool1) {
      zzgt().zzjn().zze("Passing event to registered event handler (FE)", zzgq().zzbt(paramString2), zzgq().zzd(paramBundle));
      this.zzarb.interceptEvent(paramString1, paramString2, paramBundle, paramLong);
      return;
    } 
    if (!this.zzada.zzkv())
      return; 
    int k = zzgr().zzcu(paramString2);
    if (k != 0) {
      zzgt().zzji().zzg("Invalid event name. Event will not be logged (FE)", zzgq().zzbt(paramString2));
      zzgr();
      paramString1 = zzfx.zza(paramString2, 40, true);
      int m = j;
      if (paramString2 != null)
        m = paramString2.length(); 
      this.zzada.zzgr().zza(paramString3, k, "_ev", paramString1, m);
      return;
    } 
    List<String> list = CollectionUtils.listOf((Object[])new String[] { "_o", "_sn", "_sc", "_si" });
    zzfx zzfx = zzgr();
    String str2 = "_o";
    Bundle bundle = zzfx.zza(paramString3, paramString2, paramBundle, list, paramBoolean3, true);
    if (bundle == null || !bundle.containsKey("_sc") || !bundle.containsKey("_si")) {
      zzfx = null;
    } else {
      zzdx1 = new zzdx(bundle.getString("_sn"), bundle.getString("_sc"), Long.valueOf(bundle.getLong("_si")).longValue());
    } 
    if (zzdx1 != null)
      zzdx2 = zzdx1; 
    paramBoolean1 = zzgv().zzbk(paramString3);
    String str1 = "_ae";
    if (paramBoolean1) {
      zzgw();
      if (zzgm().zzle() != null && "_ae".equals(paramString2)) {
        long l1 = zzgo().zzlp();
        if (l1 > 0L)
          zzgr().zza(bundle, l1); 
      } 
    } 
    ArrayList<Bundle> arrayList2 = new ArrayList();
    arrayList2.add(bundle);
    long l = zzgr().zzmk().nextLong();
    if (zzgv().zze(zzgk().zzal(), zzai.zzaky) && (zzgu()).zzanq.get() > 0L && zzgu().zzaf(paramLong) && (zzgu()).zzant.get()) {
      zzgt().zzjo().zzby("Current session is expired, remove the session number and Id");
      if (zzgv().zze(zzgk().zzal(), zzai.zzaku))
        zza("auto", "_sid", (Object)null, zzbx().currentTimeMillis()); 
      if (zzgv().zze(zzgk().zzal(), zzai.zzakv))
        zza("auto", "_sno", (Object)null, zzbx().currentTimeMillis()); 
    } 
    if (zzgv().zzbj(zzgk().zzal()) && bundle.getLong("extend_session", 0L) == 1L) {
      zzgt().zzjo().zzby("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
      this.zzada.zzgo().zza(paramLong, true);
    } 
    String[] arrayOfString2 = (String[])bundle.keySet().toArray((Object[])new String[paramBundle.size()]);
    Arrays.sort((Object[])arrayOfString2);
    int i = arrayOfString2.length;
    j = 0;
    k = 0;
    ArrayList<Bundle> arrayList1 = arrayList2;
    String[] arrayOfString1 = arrayOfString2;
    while (true) {
      zzdx zzdx;
      String str5 = "_eid";
      if (j < i) {
        String str6;
        zzdx zzdx3;
        zzdx zzdx4;
        ArrayList<Bundle> arrayList3;
        ArrayList<Bundle> arrayList5;
        String str7;
        String str8 = arrayOfString1[j];
        Object object = bundle.get(str8);
        zzgr();
        object = zzfx.zzf(object);
        if (object != null) {
          bundle.putInt(str8, object.length);
          for (byte b = 0; b < object.length; b++) {
            Object object1 = object[b];
            zzdy.zza(zzdx2, (Bundle)object1, true);
            object1 = zzgr().zza(paramString3, "_ep", (Bundle)object1, list, paramBoolean3, false);
            object1.putString("_en", paramString2);
            object1.putLong(str5, l);
            object1.putString("_gn", str8);
            object1.putInt("_ll", object.length);
            object1.putInt("_i", b);
            arrayList1.add(object1);
          } 
          zzdx zzdx5 = zzdx2;
          arrayList5 = arrayList1;
          str6 = str1;
          k += object.length;
          zzdx4 = zzdx5;
        } else {
          str5 = str6;
          zzdx3 = zzdx4;
          arrayList3 = arrayList5;
          str7 = str5;
        } 
        j++;
        str5 = str7;
        ArrayList<Bundle> arrayList4 = arrayList3;
        zzdx = zzdx3;
        str3 = str5;
        continue;
      } 
      String str4 = str3;
      if (k != 0) {
        bundle.putLong("_eid", l);
        bundle.putInt("_epc", k);
      } 
      i = 0;
      String str3 = str2;
      while (i < str4.size()) {
        String str;
        Bundle bundle1 = str4.get(i);
        if (i != 0) {
          j = 1;
        } else {
          j = 0;
        } 
        if (j != 0) {
          str = "_ep";
        } else {
          str = paramString2;
        } 
        bundle1.putString(str3, paramString1);
        bundle = bundle1;
        if (paramBoolean2)
          bundle = zzgr().zze(bundle1); 
        zzgt().zzjn().zze("Logging event (FE)", zzgq().zzbt(paramString2), zzgq().zzd(bundle));
        zzag zzag = new zzag(str, new zzad(bundle), paramString1, paramLong);
        zzgl().zzc(zzag, paramString3);
        if (!bool1) {
          Iterator<zzcy> iterator = this.zzarc.iterator();
          while (iterator.hasNext())
            ((zzcy)iterator.next()).onEvent(paramString1, paramString2, new Bundle(bundle), paramLong); 
        } 
        i++;
      } 
      zzgw();
      if (zzgm().zzle() != null && zzdx.equals(paramString2))
        zzgo().zza(true, true); 
      return;
    } 
  }
  
  private final void zza(String paramString1, String paramString2, long paramLong, Object paramObject) {
    zzgs().zzc(new zzdd(this, paramString1, paramString2, paramObject, paramLong));
  }
  
  private final void zza(String paramString1, String paramString2, String paramString3, Bundle paramBundle) {
    long l = zzbx().currentTimeMillis();
    Preconditions.checkNotEmpty(paramString2);
    AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
    conditionalUserProperty.mAppId = paramString1;
    conditionalUserProperty.mName = paramString2;
    conditionalUserProperty.mCreationTimestamp = l;
    if (paramString3 != null) {
      conditionalUserProperty.mExpiredEventName = paramString3;
      conditionalUserProperty.mExpiredEventParams = paramBundle;
    } 
    zzgs().zzc(new zzdj(this, conditionalUserProperty));
  }
  
  private final Map<String, Object> zzb(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    if (zzgs().zzkf()) {
      zzgt().zzjg().zzby("Cannot get user properties from analytics worker thread");
      return Collections.emptyMap();
    } 
    if (zzn.isMainThread()) {
      zzgt().zzjg().zzby("Cannot get user properties from main thread");
      return Collections.emptyMap();
    } 
    synchronized (new AtomicReference()) {
      zzbr zzbr = this.zzada.zzgs();
      zzdm zzdm = new zzdm();
      this(this, null, paramString1, paramString2, paramString3, paramBoolean);
      zzbr.zzc(zzdm);
      try {
        null.wait(5000L);
      } catch (InterruptedException interruptedException) {
        zzgt().zzjj().zzg("Interrupted waiting for get user properties", interruptedException);
      } 
      List list = null.get();
      if (list == null) {
        zzgt().zzjj().zzby("Timed out waiting for get user properties");
        return Collections.emptyMap();
      } 
      ArrayMap<String, Object> arrayMap = new ArrayMap(list.size());
      for (zzfu zzfu : list)
        arrayMap.put(zzfu.name, zzfu.getValue()); 
      return (Map<String, Object>)arrayMap;
    } 
  }
  
  private final void zzb(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty) {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramConditionalUserProperty);
    Preconditions.checkNotEmpty(paramConditionalUserProperty.mName);
    Preconditions.checkNotEmpty(paramConditionalUserProperty.mOrigin);
    Preconditions.checkNotNull(paramConditionalUserProperty.mValue);
    if (!this.zzada.isEnabled()) {
      zzgt().zzjn().zzby("Conditional property not sent since collection is disabled");
      return;
    } 
    zzfu zzfu = new zzfu(paramConditionalUserProperty.mName, paramConditionalUserProperty.mTriggeredTimestamp, paramConditionalUserProperty.mValue, paramConditionalUserProperty.mOrigin);
    try {
      zzag zzag3 = zzgr().zza(paramConditionalUserProperty.mAppId, paramConditionalUserProperty.mTriggeredEventName, paramConditionalUserProperty.mTriggeredEventParams, paramConditionalUserProperty.mOrigin, 0L, true, false);
      zzag zzag1 = zzgr().zza(paramConditionalUserProperty.mAppId, paramConditionalUserProperty.mTimedOutEventName, paramConditionalUserProperty.mTimedOutEventParams, paramConditionalUserProperty.mOrigin, 0L, true, false);
      zzag zzag2 = zzgr().zza(paramConditionalUserProperty.mAppId, paramConditionalUserProperty.mExpiredEventName, paramConditionalUserProperty.mExpiredEventParams, paramConditionalUserProperty.mOrigin, 0L, true, false);
      zzo zzo = new zzo(paramConditionalUserProperty.mAppId, paramConditionalUserProperty.mOrigin, zzfu, paramConditionalUserProperty.mCreationTimestamp, false, paramConditionalUserProperty.mTriggerEventName, zzag1, paramConditionalUserProperty.mTriggerTimeout, zzag3, paramConditionalUserProperty.mTimeToLive, zzag2);
      zzgl().zzd(zzo);
    } catch (IllegalArgumentException illegalArgumentException) {}
  }
  
  private final void zzb(String paramString1, String paramString2, long paramLong, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString3) {
    paramBundle = zzfx.zzf(paramBundle);
    zzgs().zzc(new zzdc(this, paramString1, paramString2, paramLong, paramBundle, paramBoolean1, paramBoolean2, paramBoolean3, paramString3));
  }
  
  private final void zzc(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty) {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramConditionalUserProperty);
    Preconditions.checkNotEmpty(paramConditionalUserProperty.mName);
    if (!this.zzada.isEnabled()) {
      zzgt().zzjn().zzby("Conditional property not cleared since collection is disabled");
      return;
    } 
    zzfu zzfu = new zzfu(paramConditionalUserProperty.mName, 0L, null, null);
    try {
      zzag zzag = zzgr().zza(paramConditionalUserProperty.mAppId, paramConditionalUserProperty.mExpiredEventName, paramConditionalUserProperty.mExpiredEventParams, paramConditionalUserProperty.mOrigin, paramConditionalUserProperty.mCreationTimestamp, true, false);
      zzo zzo = new zzo(paramConditionalUserProperty.mAppId, paramConditionalUserProperty.mOrigin, zzfu, paramConditionalUserProperty.mCreationTimestamp, paramConditionalUserProperty.mActive, paramConditionalUserProperty.mTriggerEventName, null, paramConditionalUserProperty.mTriggerTimeout, null, paramConditionalUserProperty.mTimeToLive, zzag);
      zzgl().zzd(zzo);
    } catch (IllegalArgumentException illegalArgumentException) {}
  }
  
  private final List<AppMeasurement.ConditionalUserProperty> zzf(String paramString1, String paramString2, String paramString3) {
    AtomicReference<List> atomicReference;
    zzad zzad;
    if (zzgs().zzkf()) {
      zzgt().zzjg().zzby("Cannot get conditional user properties from analytics worker thread");
      return Collections.emptyList();
    } 
    if (zzn.isMainThread()) {
      zzgt().zzjg().zzby("Cannot get conditional user properties from main thread");
      return Collections.emptyList();
    } 
    synchronized (new AtomicReference()) {
      zzbr zzbr = this.zzada.zzgs();
      zzdk zzdk = new zzdk();
      this(this, atomicReference, paramString1, paramString2, paramString3);
      zzbr.zzc(zzdk);
      try {
        atomicReference.wait(5000L);
      } catch (InterruptedException interruptedException) {
        zzgt().zzjj().zze("Interrupted waiting for get conditional user properties", paramString1, interruptedException);
      } 
      List list = atomicReference.get();
      if (list == null) {
        zzgt().zzjj().zzg("Timed out waiting for get conditional user properties", paramString1);
        return Collections.emptyList();
      } 
      ArrayList<AppMeasurement.ConditionalUserProperty> arrayList = new ArrayList(list.size());
      for (zzo zzo : list) {
        AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
        conditionalUserProperty.mAppId = zzo.packageName;
        conditionalUserProperty.mOrigin = zzo.origin;
        conditionalUserProperty.mCreationTimestamp = zzo.creationTimestamp;
        zzfu zzfu = zzo.zzags;
        conditionalUserProperty.mName = zzfu.name;
        conditionalUserProperty.mValue = zzfu.getValue();
        conditionalUserProperty.mActive = zzo.active;
        conditionalUserProperty.mTriggerEventName = zzo.triggerEventName;
        zzag zzag2 = zzo.zzagt;
        if (zzag2 != null) {
          conditionalUserProperty.mTimedOutEventName = zzag2.name;
          zzad zzad1 = zzag2.zzahu;
          if (zzad1 != null)
            conditionalUserProperty.mTimedOutEventParams = zzad1.zziy(); 
        } 
        conditionalUserProperty.mTriggerTimeout = zzo.triggerTimeout;
        zzag2 = zzo.zzagu;
        if (zzag2 != null) {
          conditionalUserProperty.mTriggeredEventName = zzag2.name;
          zzad zzad1 = zzag2.zzahu;
          if (zzad1 != null)
            conditionalUserProperty.mTriggeredEventParams = zzad1.zziy(); 
        } 
        conditionalUserProperty.mTriggeredTimestamp = zzo.zzags.zzaum;
        conditionalUserProperty.mTimeToLive = zzo.timeToLive;
        zzag zzag1 = zzo.zzagv;
        if (zzag1 != null) {
          conditionalUserProperty.mExpiredEventName = zzag1.name;
          zzad = zzag1.zzahu;
          if (zzad != null)
            conditionalUserProperty.mExpiredEventParams = zzad.zziy(); 
        } 
        arrayList.add(conditionalUserProperty);
      } 
      return arrayList;
    } 
  }
  
  private final void zzlc() {
    if (zzgv().zzba(zzgk().zzal()) && this.zzada.isEnabled() && this.zzarf) {
      zzgt().zzjn().zzby("Recording app launch after enabling measurement for the first time (FE)");
      zzld();
      return;
    } 
    zzgt().zzjn().zzby("Updating Scion state (FE)");
    zzgl().zzlg();
  }
  
  public final void clearConditionalUserProperty(String paramString1, String paramString2, Bundle paramBundle) {
    zzgg();
    zza((String)null, paramString1, paramString2, paramBundle);
  }
  
  public final void clearConditionalUserPropertyAs(String paramString1, String paramString2, String paramString3, Bundle paramBundle) {
    Preconditions.checkNotEmpty(paramString1);
    zzgf();
    throw null;
  }
  
  public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserProperties(String paramString1, String paramString2) {
    zzgg();
    return zzf((String)null, paramString1, paramString2);
  }
  
  public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserPropertiesAs(String paramString1, String paramString2, String paramString3) {
    Preconditions.checkNotEmpty(paramString1);
    zzgf();
    throw null;
  }
  
  public final String getCurrentScreenClass() {
    zzdx zzdx = this.zzada.zzgm().zzlf();
    return (zzdx != null) ? zzdx.zzarq : null;
  }
  
  public final String getCurrentScreenName() {
    zzdx zzdx = this.zzada.zzgm().zzlf();
    return (zzdx != null) ? zzdx.zzuw : null;
  }
  
  public final String getGmpAppId() {
    if (this.zzada.zzko() != null)
      return this.zzada.zzko(); 
    try {
      return GoogleServices.getGoogleAppId();
    } catch (IllegalStateException illegalStateException) {
      this.zzada.zzgt().zzjg().zzg("getGoogleAppId failed with exception", illegalStateException);
      return null;
    } 
  }
  
  public final Map<String, Object> getUserProperties(String paramString1, String paramString2, boolean paramBoolean) {
    zzgg();
    return zzb((String)null, paramString1, paramString2, paramBoolean);
  }
  
  public final Map<String, Object> getUserPropertiesAs(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    Preconditions.checkNotEmpty(paramString1);
    zzgf();
    throw null;
  }
  
  public final void logEvent(String paramString1, String paramString2, Bundle paramBundle) {
    logEvent(paramString1, paramString2, paramBundle, true, true, zzbx().currentTimeMillis());
  }
  
  public final void logEvent(String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, long paramLong) {
    boolean bool;
    zzgg();
    if (paramString1 == null)
      paramString1 = "app"; 
    if (paramBundle == null)
      paramBundle = new Bundle(); 
    if (!paramBoolean2 || this.zzarb == null || zzfx.zzcy(paramString2)) {
      bool = true;
    } else {
      bool = false;
    } 
    zzb(paramString1, paramString2, paramLong, paramBundle, paramBoolean2, bool, paramBoolean1 ^ true, (String)null);
  }
  
  public final void setConditionalUserProperty(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty) {
    Preconditions.checkNotNull(paramConditionalUserProperty);
    zzgg();
    paramConditionalUserProperty = new AppMeasurement.ConditionalUserProperty(paramConditionalUserProperty);
    if (!TextUtils.isEmpty(paramConditionalUserProperty.mAppId))
      zzgt().zzjj().zzby("Package name should be null when calling setConditionalUserProperty"); 
    paramConditionalUserProperty.mAppId = null;
    zza(paramConditionalUserProperty);
  }
  
  public final void setConditionalUserPropertyAs(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty) {
    Preconditions.checkNotNull(paramConditionalUserProperty);
    Preconditions.checkNotEmpty(paramConditionalUserProperty.mAppId);
    zzgf();
    throw null;
  }
  
  final void zza(String paramString1, String paramString2, long paramLong, Bundle paramBundle) {
    boolean bool;
    zzgg();
    zzaf();
    if (this.zzarb == null || zzfx.zzcy(paramString2)) {
      bool = true;
    } else {
      bool = false;
    } 
    zza(paramString1, paramString2, paramLong, paramBundle, true, bool, false, (String)null);
  }
  
  final void zza(String paramString1, String paramString2, Bundle paramBundle) {
    zzgg();
    zzaf();
    zza(paramString1, paramString2, zzbx().currentTimeMillis(), paramBundle);
  }
  
  public final void zza(String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean) {
    logEvent(paramString1, paramString2, paramBundle, false, true, zzbx().currentTimeMillis());
  }
  
  final void zza(String paramString1, String paramString2, Object paramObject, long paramLong) {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString2);
    zzaf();
    zzgg();
    zzcl();
    if (!this.zzada.isEnabled()) {
      zzgt().zzjn().zzby("User property not set since app measurement is disabled");
      return;
    } 
    if (!this.zzada.zzkv())
      return; 
    zzgt().zzjn().zze("Setting user property (FE)", zzgq().zzbt(paramString2), paramObject);
    zzfu zzfu = new zzfu(paramString2, paramLong, paramObject, paramString1);
    zzgl().zzb(zzfu);
  }
  
  public final void zza(String paramString1, String paramString2, Object paramObject, boolean paramBoolean, long paramLong) {
    // Byte code:
    //   0: aload_1
    //   1: astore #10
    //   3: aload_1
    //   4: ifnonnull -> 12
    //   7: ldc_w 'app'
    //   10: astore #10
    //   12: bipush #6
    //   14: istore #7
    //   16: iconst_0
    //   17: istore #8
    //   19: iconst_0
    //   20: istore #9
    //   22: iload #4
    //   24: ifeq -> 40
    //   27: aload_0
    //   28: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   31: aload_2
    //   32: invokevirtual zzcv : (Ljava/lang/String;)I
    //   35: istore #7
    //   37: goto -> 99
    //   40: aload_0
    //   41: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   44: astore_1
    //   45: aload_1
    //   46: ldc_w 'user property'
    //   49: aload_2
    //   50: invokevirtual zzs : (Ljava/lang/String;Ljava/lang/String;)Z
    //   53: ifne -> 59
    //   56: goto -> 99
    //   59: aload_1
    //   60: ldc_w 'user property'
    //   63: getstatic com/google/android/gms/measurement/internal/zzcw.zzaqx : [Ljava/lang/String;
    //   66: aload_2
    //   67: invokevirtual zza : (Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Z
    //   70: ifne -> 80
    //   73: bipush #15
    //   75: istore #7
    //   77: goto -> 99
    //   80: aload_1
    //   81: ldc_w 'user property'
    //   84: bipush #24
    //   86: aload_2
    //   87: invokevirtual zza : (Ljava/lang/String;ILjava/lang/String;)Z
    //   90: ifne -> 96
    //   93: goto -> 99
    //   96: iconst_0
    //   97: istore #7
    //   99: iload #7
    //   101: ifeq -> 150
    //   104: aload_0
    //   105: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   108: pop
    //   109: aload_2
    //   110: bipush #24
    //   112: iconst_1
    //   113: invokestatic zza : (Ljava/lang/String;IZ)Ljava/lang/String;
    //   116: astore_1
    //   117: iload #9
    //   119: istore #8
    //   121: aload_2
    //   122: ifnull -> 131
    //   125: aload_2
    //   126: invokevirtual length : ()I
    //   129: istore #8
    //   131: aload_0
    //   132: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   135: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   138: iload #7
    //   140: ldc_w '_ev'
    //   143: aload_1
    //   144: iload #8
    //   146: invokevirtual zza : (ILjava/lang/String;Ljava/lang/String;I)V
    //   149: return
    //   150: aload_3
    //   151: ifnull -> 254
    //   154: aload_0
    //   155: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   158: aload_2
    //   159: aload_3
    //   160: invokevirtual zzi : (Ljava/lang/String;Ljava/lang/Object;)I
    //   163: istore #9
    //   165: iload #9
    //   167: ifeq -> 229
    //   170: aload_0
    //   171: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   174: pop
    //   175: aload_2
    //   176: bipush #24
    //   178: iconst_1
    //   179: invokestatic zza : (Ljava/lang/String;IZ)Ljava/lang/String;
    //   182: astore_1
    //   183: aload_3
    //   184: instanceof java/lang/String
    //   187: ifne -> 201
    //   190: iload #8
    //   192: istore #7
    //   194: aload_3
    //   195: instanceof java/lang/CharSequence
    //   198: ifeq -> 210
    //   201: aload_3
    //   202: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   205: invokevirtual length : ()I
    //   208: istore #7
    //   210: aload_0
    //   211: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   214: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   217: iload #9
    //   219: ldc_w '_ev'
    //   222: aload_1
    //   223: iload #7
    //   225: invokevirtual zza : (ILjava/lang/String;Ljava/lang/String;I)V
    //   228: return
    //   229: aload_0
    //   230: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   233: aload_2
    //   234: aload_3
    //   235: invokevirtual zzj : (Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   238: astore_1
    //   239: aload_1
    //   240: ifnull -> 253
    //   243: aload_0
    //   244: aload #10
    //   246: aload_2
    //   247: lload #5
    //   249: aload_1
    //   250: invokespecial zza : (Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   253: return
    //   254: aload_0
    //   255: aload #10
    //   257: aload_2
    //   258: lload #5
    //   260: aconst_null
    //   261: invokespecial zza : (Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   264: return
  }
  
  public final void zzb(String paramString1, String paramString2, Object paramObject, boolean paramBoolean) {
    zza(paramString1, paramString2, paramObject, paramBoolean, zzbx().currentTimeMillis());
  }
  
  final void zzcp(String paramString) {
    this.zzare.set(paramString);
  }
  
  public final void zzd(boolean paramBoolean) {
    zzcl();
    zzgg();
    zzgs().zzc(new zzdr(this, paramBoolean));
  }
  
  public final String zzgc() {
    zzgg();
    return this.zzare.get();
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  public final void zzld() {
    zzaf();
    zzgg();
    zzcl();
    if (!this.zzada.zzkv())
      return; 
    zzgl().zzld();
    this.zzarf = false;
    String str = zzgu().zzka();
    if (!TextUtils.isEmpty(str)) {
      zzgp().zzcl();
      if (!str.equals(Build.VERSION.RELEASE)) {
        Bundle bundle = new Bundle();
        bundle.putString("_po", str);
        logEvent("auto", "_ou", bundle);
      } 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzda.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */