package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzyh extends IOException {
  public zzyh(String paramString) {
    super(paramString);
  }
  
  public zzyh(String paramString, Exception paramException) {
    super(paramString, paramException);
  }
  
  static zzyh zzzd() {
    return new zzyh("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
  }
  
  static zzyh zzze() {
    return new zzyh("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
  }
  
  static zzyh zzzf() {
    return new zzyh("CodedInputStream encountered a malformed varint.");
  }
  
  static zzyh zzzg() {
    return new zzyh("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzyh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */