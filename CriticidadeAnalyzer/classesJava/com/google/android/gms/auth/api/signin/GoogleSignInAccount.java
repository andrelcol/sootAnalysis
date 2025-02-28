package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zab();
  
  private static Clock zae = DefaultClock.getInstance();
  
  private String mId;
  
  private final int versionCode;
  
  private String zaf;
  
  private String zag;
  
  private String zah;
  
  private Uri zai;
  
  private String zaj;
  
  private long zak;
  
  private String zal;
  
  private List<Scope> zam;
  
  private String zan;
  
  private String zao;
  
  private Set<Scope> zap = new HashSet<Scope>();
  
  GoogleSignInAccount(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, Uri paramUri, String paramString5, long paramLong, String paramString6, List<Scope> paramList, String paramString7, String paramString8) {
    this.versionCode = paramInt;
    this.mId = paramString1;
    this.zaf = paramString2;
    this.zag = paramString3;
    this.zah = paramString4;
    this.zai = paramUri;
    this.zaj = paramString5;
    this.zak = paramLong;
    this.zal = paramString6;
    this.zam = paramList;
    this.zan = paramString7;
    this.zao = paramString8;
  }
  
  public static GoogleSignInAccount zaa(String paramString) throws JSONException {
    if (TextUtils.isEmpty(paramString))
      return null; 
    JSONObject jSONObject = new JSONObject(paramString);
    paramString = jSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {
      Uri uri = Uri.parse(paramString);
    } else {
      paramString = null;
    } 
    long l = Long.parseLong(jSONObject.getString("expirationTime"));
    HashSet<Scope> hashSet = new HashSet();
    JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
    int i = jSONArray.length();
    for (byte b = 0; b < i; b++)
      hashSet.add(new Scope(jSONArray.getString(b))); 
    GoogleSignInAccount googleSignInAccount = zaa(jSONObject.optString("id"), jSONObject.optString("tokenId", null), jSONObject.optString("email", null), jSONObject.optString("displayName", null), jSONObject.optString("givenName", null), jSONObject.optString("familyName", null), (Uri)paramString, Long.valueOf(l), jSONObject.getString("obfuscatedIdentifier"), hashSet);
    googleSignInAccount.zaj = jSONObject.optString("serverAuthCode", null);
    return googleSignInAccount;
  }
  
  private static GoogleSignInAccount zaa(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Uri paramUri, Long paramLong, String paramString7, Set<Scope> paramSet) {
    if (paramLong == null)
      paramLong = Long.valueOf(zae.currentTimeMillis() / 1000L); 
    long l = paramLong.longValue();
    Preconditions.checkNotEmpty(paramString7);
    Preconditions.checkNotNull(paramSet);
    return new GoogleSignInAccount(3, paramString1, paramString2, paramString3, paramString4, paramUri, null, l, paramString7, new ArrayList<Scope>(paramSet), paramString5, paramString6);
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof GoogleSignInAccount))
      return false; 
    paramObject = paramObject;
    return (((GoogleSignInAccount)paramObject).zal.equals(this.zal) && paramObject.getRequestedScopes().equals(getRequestedScopes()));
  }
  
  public Account getAccount() {
    String str = this.zag;
    return (str == null) ? null : new Account(str, "com.google");
  }
  
  public String getDisplayName() {
    return this.zah;
  }
  
  public String getEmail() {
    return this.zag;
  }
  
  public String getFamilyName() {
    return this.zao;
  }
  
  public String getGivenName() {
    return this.zan;
  }
  
  public String getId() {
    return this.mId;
  }
  
  public String getIdToken() {
    return this.zaf;
  }
  
  public Uri getPhotoUrl() {
    return this.zai;
  }
  
  public Set<Scope> getRequestedScopes() {
    HashSet<Scope> hashSet = new HashSet<Scope>(this.zam);
    hashSet.addAll(this.zap);
    return hashSet;
  }
  
  public String getServerAuthCode() {
    return this.zaj;
  }
  
  public int hashCode() {
    return (this.zal.hashCode() + 527) * 31 + getRequestedScopes().hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.versionCode);
    SafeParcelWriter.writeString(paramParcel, 2, getId(), false);
    SafeParcelWriter.writeString(paramParcel, 3, getIdToken(), false);
    SafeParcelWriter.writeString(paramParcel, 4, getEmail(), false);
    SafeParcelWriter.writeString(paramParcel, 5, getDisplayName(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 6, (Parcelable)getPhotoUrl(), paramInt, false);
    SafeParcelWriter.writeString(paramParcel, 7, getServerAuthCode(), false);
    SafeParcelWriter.writeLong(paramParcel, 8, this.zak);
    SafeParcelWriter.writeString(paramParcel, 9, this.zal, false);
    SafeParcelWriter.writeTypedList(paramParcel, 10, this.zam, false);
    SafeParcelWriter.writeString(paramParcel, 11, getGivenName(), false);
    SafeParcelWriter.writeString(paramParcel, 12, getFamilyName(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/auth/api/signin/GoogleSignInAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */