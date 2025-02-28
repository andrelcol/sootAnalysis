package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

interface zzwk {
  int getTag();
  
  double readDouble() throws IOException;
  
  float readFloat() throws IOException;
  
  String readString() throws IOException;
  
  void readStringList(List<String> paramList) throws IOException;
  
  <T> T zza(zzwl<T> paramzzwl, zzub paramzzub) throws IOException;
  
  <T> void zza(List<T> paramList, zzwl<T> paramzzwl, zzub paramzzub) throws IOException;
  
  @Deprecated
  <T> T zzb(zzwl<T> paramzzwl, zzub paramzzub) throws IOException;
  
  @Deprecated
  <T> void zzb(List<T> paramList, zzwl<T> paramzzwl, zzub paramzzub) throws IOException;
  
  void zzi(List<Double> paramList) throws IOException;
  
  void zzj(List<Float> paramList) throws IOException;
  
  void zzk(List<Long> paramList) throws IOException;
  
  void zzl(List<Long> paramList) throws IOException;
  
  void zzm(List<Integer> paramList) throws IOException;
  
  void zzn(List<Long> paramList) throws IOException;
  
  void zzo(List<Integer> paramList) throws IOException;
  
  void zzp(List<Boolean> paramList) throws IOException;
  
  void zzq(List<String> paramList) throws IOException;
  
  void zzr(List<zzte> paramList) throws IOException;
  
  void zzs(List<Integer> paramList) throws IOException;
  
  void zzt(List<Integer> paramList) throws IOException;
  
  void zzu(List<Integer> paramList) throws IOException;
  
  long zzuk() throws IOException;
  
  long zzul() throws IOException;
  
  int zzum() throws IOException;
  
  long zzun() throws IOException;
  
  int zzuo() throws IOException;
  
  boolean zzup() throws IOException;
  
  String zzuq() throws IOException;
  
  zzte zzur() throws IOException;
  
  int zzus() throws IOException;
  
  int zzut() throws IOException;
  
  int zzuu() throws IOException;
  
  long zzuv() throws IOException;
  
  int zzuw() throws IOException;
  
  long zzux() throws IOException;
  
  void zzv(List<Long> paramList) throws IOException;
  
  int zzvh() throws IOException;
  
  boolean zzvi() throws IOException;
  
  void zzw(List<Integer> paramList) throws IOException;
  
  void zzx(List<Long> paramList) throws IOException;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */