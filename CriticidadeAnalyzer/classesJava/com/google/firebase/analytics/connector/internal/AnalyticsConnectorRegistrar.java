package com.google.firebase.analytics.connector.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Keep;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.events.Subscriber;
import java.util.Collections;
import java.util.List;

@Keep
public class AnalyticsConnectorRegistrar implements ComponentRegistrar {
  @SuppressLint({"MissingPermission"})
  @Keep
  public List<Component<?>> getComponents() {
    Component.Builder builder = Component.builder(AnalyticsConnector.class);
    builder.add(Dependency.required(FirebaseApp.class));
    builder.add(Dependency.required(Context.class));
    builder.add(Dependency.required(Subscriber.class));
    builder.factory(zzb.zzbsx);
    builder.eagerInDefaultApp();
    return Collections.singletonList(builder.build());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/analytics/connector/internal/AnalyticsConnectorRegistrar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */