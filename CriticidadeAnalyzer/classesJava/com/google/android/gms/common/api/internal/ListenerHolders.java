package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class ListenerHolders {
  private final Set<ListenerHolder<?>> zajo = Collections.newSetFromMap(new WeakHashMap<ListenerHolder<?>, Boolean>());
  
  public static <L> ListenerHolder<L> createListenerHolder(L paramL, Looper paramLooper, String paramString) {
    Preconditions.checkNotNull(paramL, "Listener must not be null");
    Preconditions.checkNotNull(paramLooper, "Looper must not be null");
    Preconditions.checkNotNull(paramString, "Listener type must not be null");
    return new ListenerHolder<L>(paramLooper, paramL, paramString);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/ListenerHolders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */