package com.google.android.gms.internal.measurement;

public enum zzux {
  zzbzg(Void.class, Void.class, null),
  zzbzh(int.class, Integer.class, Integer.valueOf(0)),
  zzbzi(long.class, Long.class, Long.valueOf(0L)),
  zzbzj(float.class, Float.class, Float.valueOf(0.0F)),
  zzbzk(double.class, Double.class, Double.valueOf(0.0D)),
  zzbzl(boolean.class, Boolean.class, Boolean.valueOf(false)),
  zzbzm(String.class, String.class, ""),
  zzbzn(zzte.class, zzte.class, zzte.zzbts),
  zzbzo(int.class, Integer.class, null),
  zzbzp(Object.class, Object.class, null);
  
  private static final zzux[] zzbzt = new zzux[] { zzbzg, zzbzh, zzbzi, zzbzj, zzbzk, zzbzl, zzbzm, zzbzn, zzbzo, zzbzp };
  
  private final Class<?> zzbzr;
  
  zzux(Class<?> paramClass1, Class<?> paramClass2, Object paramObject) {
    this.zzbzr = paramClass2;
  }
  
  public final Class<?> zzwy() {
    return this.zzbzr;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzux.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */