package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import androidx.collection.ArraySet;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ClientSettings {
  private final Set<Scope> zabr;
  
  private final String zabv;
  
  private final String zabw;
  
  private final boolean zaby;
  
  private final Set<Scope> zaob;
  
  private final Map<Api<?>, OptionalApiSettings> zaoc;
  
  private final SignInOptions zaod;
  
  private Integer zaoe;
  
  private final Account zax;
  
  public ClientSettings(Account paramAccount, Set<Scope> paramSet, Map<Api<?>, OptionalApiSettings> paramMap, int paramInt, View paramView, String paramString1, String paramString2, SignInOptions paramSignInOptions, boolean paramBoolean) {
    Set<Scope> set;
    this.zax = paramAccount;
    if (paramSet == null) {
      set = Collections.EMPTY_SET;
    } else {
      set = Collections.unmodifiableSet(paramSet);
    } 
    this.zabr = set;
    Map<Api<?>, OptionalApiSettings> map = paramMap;
    if (paramMap == null)
      map = Collections.EMPTY_MAP; 
    this.zaoc = map;
    this.zabv = paramString1;
    this.zabw = paramString2;
    this.zaod = paramSignInOptions;
    this.zaby = paramBoolean;
    HashSet<Scope> hashSet = new HashSet<Scope>(this.zabr);
    Iterator iterator = this.zaoc.values().iterator();
    while (iterator.hasNext())
      hashSet.addAll(((OptionalApiSettings)iterator.next()).mScopes); 
    this.zaob = Collections.unmodifiableSet(hashSet);
  }
  
  public final Account getAccount() {
    return this.zax;
  }
  
  public final Account getAccountOrDefault() {
    Account account = this.zax;
    return (account != null) ? account : new Account("<<default account>>", "com.google");
  }
  
  public final Set<Scope> getAllRequestedScopes() {
    return this.zaob;
  }
  
  public final Integer getClientSessionId() {
    return this.zaoe;
  }
  
  public final Map<Api<?>, OptionalApiSettings> getOptionalApiSettings() {
    return this.zaoc;
  }
  
  public final String getRealClientClassName() {
    return this.zabw;
  }
  
  public final String getRealClientPackageName() {
    return this.zabv;
  }
  
  public final Set<Scope> getRequiredScopes() {
    return this.zabr;
  }
  
  public final SignInOptions getSignInOptions() {
    return this.zaod;
  }
  
  public final boolean isSignInClientDisconnectFixEnabled() {
    return this.zaby;
  }
  
  public final void setClientSessionId(Integer paramInteger) {
    this.zaoe = paramInteger;
  }
  
  public static final class Builder {
    private int zabt = 0;
    
    private View zabu;
    
    private String zabv;
    
    private String zabw;
    
    private boolean zaby;
    
    private Map<Api<?>, ClientSettings.OptionalApiSettings> zaoc;
    
    private SignInOptions zaod = SignInOptions.DEFAULT;
    
    private ArraySet<Scope> zaof;
    
    private Account zax;
    
    public final Builder addAllRequiredScopes(Collection<Scope> param1Collection) {
      if (this.zaof == null)
        this.zaof = new ArraySet(); 
      this.zaof.addAll(param1Collection);
      return this;
    }
    
    public final ClientSettings build() {
      return new ClientSettings(this.zax, (Set<Scope>)this.zaof, this.zaoc, this.zabt, this.zabu, this.zabv, this.zabw, this.zaod, this.zaby);
    }
    
    public final Builder setAccount(Account param1Account) {
      this.zax = param1Account;
      return this;
    }
    
    public final Builder setRealClientClassName(String param1String) {
      this.zabw = param1String;
      return this;
    }
    
    public final Builder setRealClientPackageName(String param1String) {
      this.zabv = param1String;
      return this;
    }
  }
  
  public static final class OptionalApiSettings {
    public final Set<Scope> mScopes;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/ClientSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */