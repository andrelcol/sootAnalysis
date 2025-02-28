package com.google.android.gms.internal.measurement;

final class zzvk implements zzwm {
  private static final zzvu zzcai = new zzvl();
  
  private final zzvu zzcah;
  
  public zzvk() {
    this(new zzvm(new zzvu[] { zzun.zzwe(), zzxf() }));
  }
  
  private zzvk(zzvu paramzzvu) {
    zzuq.zza(paramzzvu, "messageInfoFactory");
    this.zzcah = paramzzvu;
  }
  
  private static boolean zza(zzvt paramzzvt) {
    return (paramzzvt.zzxm() == zzuo.zze.zzbyu);
  }
  
  private static zzvu zzxf() {
    try {
      return (zzvu)Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
    } catch (Exception exception) {
      return zzcai;
    } 
  }
  
  public final <T> zzwl<T> zzh(Class<T> paramClass) {
    zzwn.zzj(paramClass);
    zzvt zzvt = this.zzcah.zzf(paramClass);
    return (zzwl<T>)(zzvt.zzxn() ? (zzuo.class.isAssignableFrom(paramClass) ? zzwa.zza(zzwn.zzxz(), zzue.zzvu(), zzvt.zzxo()) : zzwa.zza(zzwn.zzxx(), zzue.zzvv(), zzvt.zzxo())) : (zzuo.class.isAssignableFrom(paramClass) ? (zza(zzvt) ? zzvz.zza(paramClass, zzvt, zzwe.zzxr(), zzvf.zzxe(), zzwn.zzxz(), zzue.zzvu(), zzvs.zzxk()) : zzvz.zza(paramClass, zzvt, zzwe.zzxr(), zzvf.zzxe(), zzwn.zzxz(), null, zzvs.zzxk())) : (zza(zzvt) ? zzvz.zza(paramClass, zzvt, zzwe.zzxq(), zzvf.zzxd(), zzwn.zzxx(), zzue.zzvv(), zzvs.zzxj()) : zzvz.zza(paramClass, zzvt, zzwe.zzxq(), zzvf.zzxd(), zzwn.zzxy(), null, zzvs.zzxj()))));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */