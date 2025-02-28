package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.zabp;
import com.google.android.gms.common.api.internal.zace;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;

public class GoogleApi<O extends Api.ApiOptions> {
  private final Api<O> mApi;
  
  private final Context mContext;
  
  private final int mId;
  
  private final O zabh;
  
  private final zai<O> zabi;
  
  private final Looper zabj;
  
  protected final GoogleApiManager zabm;
  
  protected GoogleApi(Context paramContext, Api<O> paramApi, Looper paramLooper) {
    Preconditions.checkNotNull(paramContext, "Null context is not permitted.");
    Preconditions.checkNotNull(paramApi, "Api must not be null.");
    Preconditions.checkNotNull(paramLooper, "Looper must not be null.");
    this.mContext = paramContext.getApplicationContext();
    this.mApi = paramApi;
    this.zabh = null;
    this.zabj = paramLooper;
    this.zabi = zai.zaa(paramApi);
    new zabp(this);
    this.zabm = GoogleApiManager.zab(this.mContext);
    this.mId = this.zabm.zabd();
    new ApiExceptionMapper();
  }
  
  private final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zaa(int paramInt, T paramT) {
    paramT.zau();
    this.zabm.zaa(this, paramInt, (BaseImplementation.ApiMethodImpl)paramT);
    return paramT;
  }
  
  protected ClientSettings.Builder createClientSettingsBuilder() {
    // Byte code:
    //   0: new com/google/android/gms/common/internal/ClientSettings$Builder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_2
    //   8: aload_0
    //   9: getfield zabh : Lcom/google/android/gms/common/api/Api$ApiOptions;
    //   12: astore_1
    //   13: aload_1
    //   14: instanceof com/google/android/gms/common/api/Api$ApiOptions$HasGoogleSignInAccountOptions
    //   17: ifeq -> 42
    //   20: aload_1
    //   21: checkcast com/google/android/gms/common/api/Api$ApiOptions$HasGoogleSignInAccountOptions
    //   24: invokeinterface getGoogleSignInAccount : ()Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;
    //   29: astore_1
    //   30: aload_1
    //   31: ifnull -> 42
    //   34: aload_1
    //   35: invokevirtual getAccount : ()Landroid/accounts/Account;
    //   38: astore_1
    //   39: goto -> 69
    //   42: aload_0
    //   43: getfield zabh : Lcom/google/android/gms/common/api/Api$ApiOptions;
    //   46: astore_1
    //   47: aload_1
    //   48: instanceof com/google/android/gms/common/api/Api$ApiOptions$HasAccountOptions
    //   51: ifeq -> 67
    //   54: aload_1
    //   55: checkcast com/google/android/gms/common/api/Api$ApiOptions$HasAccountOptions
    //   58: invokeinterface getAccount : ()Landroid/accounts/Account;
    //   63: astore_1
    //   64: goto -> 69
    //   67: aconst_null
    //   68: astore_1
    //   69: aload_2
    //   70: aload_1
    //   71: invokevirtual setAccount : (Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/ClientSettings$Builder;
    //   74: pop
    //   75: aload_0
    //   76: getfield zabh : Lcom/google/android/gms/common/api/Api$ApiOptions;
    //   79: astore_1
    //   80: aload_1
    //   81: instanceof com/google/android/gms/common/api/Api$ApiOptions$HasGoogleSignInAccountOptions
    //   84: ifeq -> 109
    //   87: aload_1
    //   88: checkcast com/google/android/gms/common/api/Api$ApiOptions$HasGoogleSignInAccountOptions
    //   91: invokeinterface getGoogleSignInAccount : ()Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;
    //   96: astore_1
    //   97: aload_1
    //   98: ifnull -> 109
    //   101: aload_1
    //   102: invokevirtual getRequestedScopes : ()Ljava/util/Set;
    //   105: astore_1
    //   106: goto -> 113
    //   109: invokestatic emptySet : ()Ljava/util/Set;
    //   112: astore_1
    //   113: aload_2
    //   114: aload_1
    //   115: invokevirtual addAllRequiredScopes : (Ljava/util/Collection;)Lcom/google/android/gms/common/internal/ClientSettings$Builder;
    //   118: pop
    //   119: aload_2
    //   120: aload_0
    //   121: getfield mContext : Landroid/content/Context;
    //   124: invokevirtual getClass : ()Ljava/lang/Class;
    //   127: invokevirtual getName : ()Ljava/lang/String;
    //   130: invokevirtual setRealClientClassName : (Ljava/lang/String;)Lcom/google/android/gms/common/internal/ClientSettings$Builder;
    //   133: pop
    //   134: aload_2
    //   135: aload_0
    //   136: getfield mContext : Landroid/content/Context;
    //   139: invokevirtual getPackageName : ()Ljava/lang/String;
    //   142: invokevirtual setRealClientPackageName : (Ljava/lang/String;)Lcom/google/android/gms/common/internal/ClientSettings$Builder;
    //   145: pop
    //   146: aload_2
    //   147: areturn
  }
  
  public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(T paramT) {
    zaa(1, paramT);
    return paramT;
  }
  
  public final Api<O> getApi() {
    return this.mApi;
  }
  
  public final int getInstanceId() {
    return this.mId;
  }
  
  public Looper getLooper() {
    return this.zabj;
  }
  
  public Api.Client zaa(Looper paramLooper, GoogleApiManager.zaa<O> paramzaa) {
    ClientSettings clientSettings = createClientSettingsBuilder().build();
    return this.mApi.zai().buildClient(this.mContext, paramLooper, clientSettings, this.zabh, (GoogleApiClient.ConnectionCallbacks)paramzaa, (GoogleApiClient.OnConnectionFailedListener)paramzaa);
  }
  
  public zace zaa(Context paramContext, Handler paramHandler) {
    return new zace(paramContext, paramHandler, createClientSettingsBuilder().build());
  }
  
  public final zai<O> zak() {
    return this.zabi;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/GoogleApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */