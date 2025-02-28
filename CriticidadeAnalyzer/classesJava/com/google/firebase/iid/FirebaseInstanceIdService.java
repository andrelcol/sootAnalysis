package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

@Deprecated
public class FirebaseInstanceIdService extends zzb {
  @Deprecated
  public void onTokenRefresh() {}
  
  protected final Intent zzb(Intent paramIntent) {
    return (zzav.zzai()).zzda.poll();
  }
  
  public final void zzd(Intent paramIntent) {
    if ("com.google.firebase.iid.TOKEN_REFRESH".equals(paramIntent.getAction())) {
      onTokenRefresh();
      return;
    } 
    String str = paramIntent.getStringExtra("CMD");
    if (str != null) {
      if (Log.isLoggable("FirebaseInstanceId", 3)) {
        String str1 = String.valueOf(paramIntent.getExtras());
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str1).length());
        stringBuilder.append("Received command: ");
        stringBuilder.append(str);
        stringBuilder.append(" - ");
        stringBuilder.append(str1);
        stringBuilder.toString();
      } 
      if ("RST".equals(str) || "RST_FULL".equals(str)) {
        FirebaseInstanceId.getInstance().zzm();
        return;
      } 
      if ("SYNC".equals(str))
        FirebaseInstanceId.getInstance().zzq(); 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/FirebaseInstanceIdService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */