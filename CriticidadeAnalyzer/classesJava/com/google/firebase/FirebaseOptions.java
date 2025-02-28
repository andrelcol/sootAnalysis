package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

public final class FirebaseOptions {
  private final String zza;
  
  private final String zzb;
  
  private final String zzc;
  
  private final String zzd;
  
  private final String zze;
  
  private final String zzf;
  
  private final String zzg;
  
  private FirebaseOptions(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7) {
    Preconditions.checkState(Strings.isEmptyOrWhitespace(paramString1) ^ true, "ApplicationId must be set.");
    this.zzb = paramString1;
    this.zza = paramString2;
    this.zzc = paramString3;
    this.zzd = paramString4;
    this.zze = paramString5;
    this.zzf = paramString6;
    this.zzg = paramString7;
  }
  
  public static FirebaseOptions fromResource(Context paramContext) {
    StringResourceValueReader stringResourceValueReader = new StringResourceValueReader(paramContext);
    String str = stringResourceValueReader.getString("google_app_id");
    return TextUtils.isEmpty(str) ? null : new FirebaseOptions(str, stringResourceValueReader.getString("google_api_key"), stringResourceValueReader.getString("firebase_database_url"), stringResourceValueReader.getString("ga_trackingId"), stringResourceValueReader.getString("gcm_defaultSenderId"), stringResourceValueReader.getString("google_storage_bucket"), stringResourceValueReader.getString("project_id"));
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof FirebaseOptions))
      return false; 
    paramObject = paramObject;
    return (Objects.equal(this.zzb, ((FirebaseOptions)paramObject).zzb) && Objects.equal(this.zza, ((FirebaseOptions)paramObject).zza) && Objects.equal(this.zzc, ((FirebaseOptions)paramObject).zzc) && Objects.equal(this.zzd, ((FirebaseOptions)paramObject).zzd) && Objects.equal(this.zze, ((FirebaseOptions)paramObject).zze) && Objects.equal(this.zzf, ((FirebaseOptions)paramObject).zzf) && Objects.equal(this.zzg, ((FirebaseOptions)paramObject).zzg));
  }
  
  public final String getApplicationId() {
    return this.zzb;
  }
  
  public final String getGcmSenderId() {
    return this.zze;
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { this.zzb, this.zza, this.zzc, this.zzd, this.zze, this.zzf, this.zzg });
  }
  
  public final String toString() {
    Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this);
    toStringHelper.add("applicationId", this.zzb);
    toStringHelper.add("apiKey", this.zza);
    toStringHelper.add("databaseUrl", this.zzc);
    toStringHelper.add("gcmSenderId", this.zze);
    toStringHelper.add("storageBucket", this.zzf);
    toStringHelper.add("projectId", this.zzg);
    return toStringHelper.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/FirebaseOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */