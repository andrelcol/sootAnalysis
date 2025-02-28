package com.google.android.gms.common.api.internal;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public interface zabs {
  void connect();
  
  void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  <A extends com.google.android.gms.common.api.Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(T paramT);
  
  boolean isConnected();
  
  void zaw();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */