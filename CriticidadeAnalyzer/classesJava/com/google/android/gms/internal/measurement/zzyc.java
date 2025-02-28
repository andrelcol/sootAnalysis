package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzyc<M extends zzyc<M>> extends zzyi {
  protected zzye zzcev;
  
  public void zza(zzya paramzzya) throws IOException {
    if (this.zzcev == null)
      return; 
    for (byte b = 0; b < this.zzcev.size(); b++)
      this.zzcev.zzcf(b).zza(paramzzya); 
  }
  
  protected final boolean zza(zzxz paramzzxz, int paramInt) throws IOException {
    zzyf zzyf1;
    int j = paramzzxz.getPosition();
    if (!paramzzxz.zzaq(paramInt))
      return false; 
    int i = paramInt >>> 3;
    zzyk zzyk = new zzyk(paramInt, paramzzxz.zzs(j, paramzzxz.getPosition() - j));
    paramzzxz = null;
    zzye zzye1 = this.zzcev;
    if (zzye1 == null) {
      this.zzcev = new zzye();
    } else {
      zzyf1 = zzye1.zzce(i);
    } 
    zzyf zzyf2 = zzyf1;
    if (zzyf1 == null) {
      zzyf2 = new zzyf();
      this.zzcev.zza(i, zzyf2);
    } 
    zzyf2.zza(zzyk);
    return true;
  }
  
  protected int zzf() {
    boolean bool;
    zzye zzye1 = this.zzcev;
    byte b = 0;
    if (zzye1 != null) {
      int i = 0;
      while (true) {
        bool = i;
        if (b < this.zzcev.size()) {
          i += this.zzcev.zzcf(b).zzf();
          b++;
          continue;
        } 
        break;
      } 
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzyc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */