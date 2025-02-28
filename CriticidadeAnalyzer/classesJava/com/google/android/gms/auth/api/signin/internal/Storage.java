package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public class Storage {
  private static final Lock zaaj = new ReentrantLock();
  
  private static Storage zaak;
  
  private final Lock zaal = new ReentrantLock();
  
  private final SharedPreferences zaam;
  
  private Storage(Context paramContext) {
    this.zaam = paramContext.getSharedPreferences("com.google.android.gms.signin", 0);
  }
  
  public static Storage getInstance(Context paramContext) {
    Preconditions.checkNotNull(paramContext);
    zaaj.lock();
    try {
      if (zaak == null) {
        Storage storage = new Storage();
        this(paramContext.getApplicationContext());
        zaak = storage;
      } 
      return zaak;
    } finally {
      zaaj.unlock();
    } 
  }
  
  private static String zab(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 1 + String.valueOf(paramString2).length());
    stringBuilder.append(paramString1);
    stringBuilder.append(":");
    stringBuilder.append(paramString2);
    return stringBuilder.toString();
  }
  
  private final GoogleSignInAccount zad(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return null; 
    paramString = zaf(zab("googleSignInAccount", paramString));
    if (paramString != null)
      try {
        return GoogleSignInAccount.zaa(paramString);
      } catch (JSONException jSONException) {} 
    return null;
  }
  
  private final String zaf(String paramString) {
    this.zaal.lock();
    try {
      paramString = this.zaam.getString(paramString, null);
      return paramString;
    } finally {
      this.zaal.unlock();
    } 
  }
  
  public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
    return zad(zaf("defaultGoogleSignInAccount"));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/auth/api/signin/internal/Storage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */