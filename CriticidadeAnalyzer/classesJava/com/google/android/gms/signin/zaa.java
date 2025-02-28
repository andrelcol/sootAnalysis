package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.SignInClientImpl;

public final class zaa {
  public static final Api<SignInOptions> API;
  
  private static final Api.ClientKey<SignInClientImpl> CLIENT_KEY = new Api.ClientKey();
  
  public static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> zaph;
  
  private static final Api.ClientKey<SignInClientImpl> zars = new Api.ClientKey();
  
  private static final Api.AbstractClientBuilder<SignInClientImpl, Object> zart;
  
  static {
    zaph = new zab();
    zart = new zac();
    new Scope("profile");
    new Scope("email");
    API = new Api("SignIn.API", zaph, CLIENT_KEY);
    new Api("SignIn.INTERNAL_API", zart, zars);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/signin/zaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */