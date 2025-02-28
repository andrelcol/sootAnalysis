package com.google.firebase.iid;

import androidx.annotation.Keep;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import java.util.Arrays;
import java.util.List;

@Keep
public final class Registrar implements ComponentRegistrar {
  @Keep
  public final List<Component<?>> getComponents() {
    Component.Builder builder = Component.builder(FirebaseInstanceId.class);
    builder.add(Dependency.required(FirebaseApp.class));
    builder.add(Dependency.required(Subscriber.class));
    builder.factory(zzao.zzcm);
    builder.alwaysEager();
    Component component = builder.build();
    builder = Component.builder(FirebaseInstanceIdInternal.class);
    builder.add(Dependency.required(FirebaseInstanceId.class));
    builder.factory(zzap.zzcm);
    return Arrays.asList((Component<?>[])new Component[] { component, builder.build() });
  }
  
  private static final class zza implements FirebaseInstanceIdInternal {
    public zza(FirebaseInstanceId param1FirebaseInstanceId) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/Registrar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */