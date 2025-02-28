package com.google.firebase.iid;

import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.util.concurrent.Executor;

final class zzr implements MessagingChannel {
  private final FirebaseApp zzam;
  
  private final zzan zzan;
  
  private final zzat zzbi;
  
  private final Executor zzbj;
  
  zzr(FirebaseApp paramFirebaseApp, zzan paramzzan, Executor paramExecutor) {
    this(paramFirebaseApp, paramzzan, paramExecutor, new zzat(paramFirebaseApp.getApplicationContext(), paramzzan));
  }
  
  private zzr(FirebaseApp paramFirebaseApp, zzan paramzzan, Executor paramExecutor, zzat paramzzat) {
    this.zzam = paramFirebaseApp;
    this.zzan = paramzzan;
    this.zzbi = paramzzat;
    this.zzbj = paramExecutor;
  }
  
  private final Task<Bundle> zza(String paramString1, String paramString2, String paramString3, Bundle paramBundle) {
    paramBundle.putString("scope", paramString3);
    paramBundle.putString("sender", paramString2);
    paramBundle.putString("subtype", paramString2);
    paramBundle.putString("appid", paramString1);
    paramBundle.putString("gmp_app_id", this.zzam.getOptions().getApplicationId());
    paramBundle.putString("gmsv", Integer.toString(this.zzan.zzaf()));
    paramBundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
    paramBundle.putString("app_ver", this.zzan.zzad());
    paramBundle.putString("app_ver_name", this.zzan.zzae());
    paramBundle.putString("cliv", "fiid-12451000");
    TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
    this.zzbj.execute(new zzs(this, paramBundle, taskCompletionSource));
    return taskCompletionSource.getTask();
  }
  
  private static String zza(Bundle paramBundle) throws IOException {
    if (paramBundle != null) {
      String str = paramBundle.getString("registration_id");
      if (str != null)
        return str; 
      str = paramBundle.getString("unregistered");
      if (str != null)
        return str; 
      str = paramBundle.getString("error");
      if (!"RST".equals(str)) {
        if (str != null)
          throw new IOException(str); 
        String str1 = String.valueOf(paramBundle);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 21);
        stringBuilder.append("Unexpected response: ");
        stringBuilder.append(str1);
        stringBuilder.toString();
        new Throwable();
        throw new IOException("SERVICE_NOT_AVAILABLE");
      } 
      throw new IOException("INSTANCE_ID_RESET");
    } 
    throw new IOException("SERVICE_NOT_AVAILABLE");
  }
  
  private final <T> Task<Void> zzb(Task<T> paramTask) {
    return paramTask.continueWith(zzi.zze(), new zzt(this));
  }
  
  private final Task<String> zzc(Task<Bundle> paramTask) {
    return paramTask.continueWith(this.zzbj, new zzu(this));
  }
  
  public final Task<Void> buildChannel(String paramString1, String paramString2) {
    return Tasks.forResult(null);
  }
  
  public final Task<String> getToken(String paramString1, String paramString2, String paramString3, String paramString4) {
    return zzc(zza(paramString1, paramString3, paramString4, new Bundle()));
  }
  
  public final boolean isAvailable() {
    return (this.zzan.zzac() != 0);
  }
  
  public final boolean isChannelBuilt() {
    return true;
  }
  
  public final Task<Void> subscribeToTopic(String paramString1, String paramString2, String paramString3) {
    Bundle bundle = new Bundle();
    String str = String.valueOf(paramString3);
    if (str.length() != 0) {
      str = "/topics/".concat(str);
    } else {
      str = new String("/topics/");
    } 
    bundle.putString("gcm.topic", str);
    paramString3 = String.valueOf(paramString3);
    if (paramString3.length() != 0) {
      paramString3 = "/topics/".concat(paramString3);
    } else {
      paramString3 = new String("/topics/");
    } 
    return zzb(zzc(zza(paramString1, paramString2, paramString3, bundle)));
  }
  
  public final Task<Void> unsubscribeFromTopic(String paramString1, String paramString2, String paramString3) {
    Bundle bundle = new Bundle();
    String str = String.valueOf(paramString3);
    if (str.length() != 0) {
      str = "/topics/".concat(str);
    } else {
      str = new String("/topics/");
    } 
    bundle.putString("gcm.topic", str);
    bundle.putString("delete", "1");
    paramString3 = String.valueOf(paramString3);
    if (paramString3.length() != 0) {
      paramString3 = "/topics/".concat(paramString3);
    } else {
      paramString3 = new String("/topics/");
    } 
    return zzb(zzc(zza(paramString1, paramString2, paramString3, bundle)));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */