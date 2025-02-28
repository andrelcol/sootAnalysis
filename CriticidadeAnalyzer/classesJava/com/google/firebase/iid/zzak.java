package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzak<T> {
  final int what;
  
  final int zzcf;
  
  final TaskCompletionSource<T> zzcg = new TaskCompletionSource();
  
  final Bundle zzch;
  
  zzak(int paramInt1, int paramInt2, Bundle paramBundle) {
    this.zzcf = paramInt1;
    this.what = paramInt2;
    this.zzch = paramBundle;
  }
  
  final void finish(T paramT) {
    if (Log.isLoggable("MessengerIpcClient", 3)) {
      String str2 = String.valueOf(this);
      String str1 = String.valueOf(paramT);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str2).length() + 16 + String.valueOf(str1).length());
      stringBuilder.append("Finishing ");
      stringBuilder.append(str2);
      stringBuilder.append(" with ");
      stringBuilder.append(str1);
      stringBuilder.toString();
    } 
    this.zzcg.setResult(paramT);
  }
  
  public String toString() {
    int j = this.what;
    int i = this.zzcf;
    boolean bool = zzab();
    StringBuilder stringBuilder = new StringBuilder(55);
    stringBuilder.append("Request { what=");
    stringBuilder.append(j);
    stringBuilder.append(" id=");
    stringBuilder.append(i);
    stringBuilder.append(" oneWay=");
    stringBuilder.append(bool);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  final void zza(zzal paramzzal) {
    if (Log.isLoggable("MessengerIpcClient", 3)) {
      String str2 = String.valueOf(this);
      String str1 = String.valueOf(paramzzal);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str2).length() + 14 + String.valueOf(str1).length());
      stringBuilder.append("Failing ");
      stringBuilder.append(str2);
      stringBuilder.append(" with ");
      stringBuilder.append(str1);
      stringBuilder.toString();
    } 
    this.zzcg.setException(paramzzal);
  }
  
  abstract boolean zzab();
  
  abstract void zzb(Bundle paramBundle);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */