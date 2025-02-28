package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzbw;
import com.google.android.gms.measurement.internal.zzdw;
import java.util.List;
import java.util.Map;

@Deprecated
public class AppMeasurement {
  private final zzbw zzada;
  
  public AppMeasurement(zzbw paramzzbw) {
    Preconditions.checkNotNull(paramzzbw);
    this.zzada = paramzzbw;
  }
  
  @Deprecated
  @Keep
  public static AppMeasurement getInstance(Context paramContext) {
    return zzbw.zza(paramContext, null).zzkm();
  }
  
  @Keep
  public void beginAdUnitExposure(String paramString) {
    this.zzada.zzgi().beginAdUnitExposure(paramString, this.zzada.zzbx().elapsedRealtime());
  }
  
  @Keep
  public void clearConditionalUserProperty(String paramString1, String paramString2, Bundle paramBundle) {
    this.zzada.zzgj().clearConditionalUserProperty(paramString1, paramString2, paramBundle);
  }
  
  @Keep
  protected void clearConditionalUserPropertyAs(String paramString1, String paramString2, String paramString3, Bundle paramBundle) {
    this.zzada.zzgj().clearConditionalUserPropertyAs(paramString1, paramString2, paramString3, paramBundle);
    throw null;
  }
  
  @Keep
  public void endAdUnitExposure(String paramString) {
    this.zzada.zzgi().endAdUnitExposure(paramString, this.zzada.zzbx().elapsedRealtime());
  }
  
  @Keep
  public long generateEventId() {
    return this.zzada.zzgr().zzmj();
  }
  
  @Keep
  public String getAppInstanceId() {
    return this.zzada.zzgj().zzgc();
  }
  
  @Keep
  public List<ConditionalUserProperty> getConditionalUserProperties(String paramString1, String paramString2) {
    return this.zzada.zzgj().getConditionalUserProperties(paramString1, paramString2);
  }
  
  @Keep
  protected List<ConditionalUserProperty> getConditionalUserPropertiesAs(String paramString1, String paramString2, String paramString3) {
    this.zzada.zzgj().getConditionalUserPropertiesAs(paramString1, paramString2, paramString3);
    throw null;
  }
  
  @Keep
  public String getCurrentScreenClass() {
    return this.zzada.zzgj().getCurrentScreenClass();
  }
  
  @Keep
  public String getCurrentScreenName() {
    return this.zzada.zzgj().getCurrentScreenName();
  }
  
  @Keep
  public String getGmpAppId() {
    return this.zzada.zzgj().getGmpAppId();
  }
  
  @Keep
  public int getMaxUserProperties(String paramString) {
    this.zzada.zzgj();
    Preconditions.checkNotEmpty(paramString);
    return 25;
  }
  
  @Keep
  protected Map<String, Object> getUserProperties(String paramString1, String paramString2, boolean paramBoolean) {
    return this.zzada.zzgj().getUserProperties(paramString1, paramString2, paramBoolean);
  }
  
  @Keep
  protected Map<String, Object> getUserPropertiesAs(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    this.zzada.zzgj().getUserPropertiesAs(paramString1, paramString2, paramString3, paramBoolean);
    throw null;
  }
  
  public final void logEvent(String paramString, Bundle paramBundle) {
    this.zzada.zzgj().zza("app", paramString, paramBundle, true);
  }
  
  @Keep
  public void logEventInternal(String paramString1, String paramString2, Bundle paramBundle) {
    this.zzada.zzgj().logEvent(paramString1, paramString2, paramBundle);
  }
  
  @Keep
  public void setConditionalUserProperty(ConditionalUserProperty paramConditionalUserProperty) {
    this.zzada.zzgj().setConditionalUserProperty(paramConditionalUserProperty);
  }
  
  @Keep
  protected void setConditionalUserPropertyAs(ConditionalUserProperty paramConditionalUserProperty) {
    this.zzada.zzgj().setConditionalUserPropertyAs(paramConditionalUserProperty);
    throw null;
  }
  
  public final void zzd(boolean paramBoolean) {
    this.zzada.zzgj().zzd(paramBoolean);
  }
  
  public static class ConditionalUserProperty {
    @Keep
    public boolean mActive;
    
    @Keep
    public String mAppId;
    
    @Keep
    public long mCreationTimestamp;
    
    @Keep
    public String mExpiredEventName;
    
    @Keep
    public Bundle mExpiredEventParams;
    
    @Keep
    public String mName;
    
    @Keep
    public String mOrigin;
    
    @Keep
    public long mTimeToLive;
    
    @Keep
    public String mTimedOutEventName;
    
    @Keep
    public Bundle mTimedOutEventParams;
    
    @Keep
    public String mTriggerEventName;
    
    @Keep
    public long mTriggerTimeout;
    
    @Keep
    public String mTriggeredEventName;
    
    @Keep
    public Bundle mTriggeredEventParams;
    
    @Keep
    public long mTriggeredTimestamp;
    
    @Keep
    public Object mValue;
    
    public ConditionalUserProperty() {}
    
    public ConditionalUserProperty(ConditionalUserProperty param1ConditionalUserProperty) {
      Preconditions.checkNotNull(param1ConditionalUserProperty);
      this.mAppId = param1ConditionalUserProperty.mAppId;
      this.mOrigin = param1ConditionalUserProperty.mOrigin;
      this.mCreationTimestamp = param1ConditionalUserProperty.mCreationTimestamp;
      this.mName = param1ConditionalUserProperty.mName;
      Object object = param1ConditionalUserProperty.mValue;
      if (object != null) {
        this.mValue = zzdw.zze(object);
        if (this.mValue == null)
          this.mValue = param1ConditionalUserProperty.mValue; 
      } 
      this.mActive = param1ConditionalUserProperty.mActive;
      this.mTriggerEventName = param1ConditionalUserProperty.mTriggerEventName;
      this.mTriggerTimeout = param1ConditionalUserProperty.mTriggerTimeout;
      this.mTimedOutEventName = param1ConditionalUserProperty.mTimedOutEventName;
      object = param1ConditionalUserProperty.mTimedOutEventParams;
      if (object != null)
        this.mTimedOutEventParams = new Bundle((Bundle)object); 
      this.mTriggeredEventName = param1ConditionalUserProperty.mTriggeredEventName;
      object = param1ConditionalUserProperty.mTriggeredEventParams;
      if (object != null)
        this.mTriggeredEventParams = new Bundle((Bundle)object); 
      this.mTriggeredTimestamp = param1ConditionalUserProperty.mTriggeredTimestamp;
      this.mTimeToLive = param1ConditionalUserProperty.mTimeToLive;
      this.mExpiredEventName = param1ConditionalUserProperty.mExpiredEventName;
      Bundle bundle = param1ConditionalUserProperty.mExpiredEventParams;
      if (bundle != null)
        this.mExpiredEventParams = new Bundle(bundle); 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/AppMeasurement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */