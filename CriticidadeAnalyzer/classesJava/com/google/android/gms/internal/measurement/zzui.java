package com.google.android.gms.internal.measurement;

public enum zzui {
  zzbvn(0, zzuk.zzbxv, zzux.zzbzk),
  zzbvo(1, zzuk.zzbxv, zzux.zzbzj),
  zzbvp(2, zzuk.zzbxv, zzux.zzbzi),
  zzbvq(3, zzuk.zzbxv, zzux.zzbzi),
  zzbvr(4, zzuk.zzbxv, zzux.zzbzh),
  zzbvs(5, zzuk.zzbxv, zzux.zzbzi),
  zzbvt(6, zzuk.zzbxv, zzux.zzbzh),
  zzbvu(7, zzuk.zzbxv, zzux.zzbzl),
  zzbvv(8, zzuk.zzbxv, zzux.zzbzm),
  zzbvw(9, zzuk.zzbxv, zzux.zzbzp),
  zzbvx(10, zzuk.zzbxv, zzux.zzbzn),
  zzbvy(11, zzuk.zzbxv, zzux.zzbzh),
  zzbvz(12, zzuk.zzbxv, zzux.zzbzo),
  zzbwa(13, zzuk.zzbxv, zzux.zzbzh),
  zzbwb(14, zzuk.zzbxv, zzux.zzbzi),
  zzbwc(15, zzuk.zzbxv, zzux.zzbzh),
  zzbwd(16, zzuk.zzbxv, zzux.zzbzi),
  zzbwe(17, zzuk.zzbxv, zzux.zzbzp),
  zzbwf(18, zzuk.zzbxw, zzux.zzbzk),
  zzbwg(19, zzuk.zzbxw, zzux.zzbzj),
  zzbwh(20, zzuk.zzbxw, zzux.zzbzi),
  zzbwi(21, zzuk.zzbxw, zzux.zzbzi),
  zzbwj(22, zzuk.zzbxw, zzux.zzbzh),
  zzbwk(23, zzuk.zzbxw, zzux.zzbzi),
  zzbwl(24, zzuk.zzbxw, zzux.zzbzh),
  zzbwm(25, zzuk.zzbxw, zzux.zzbzl),
  zzbwn(26, zzuk.zzbxw, zzux.zzbzm),
  zzbwo(27, zzuk.zzbxw, zzux.zzbzp),
  zzbwp(28, zzuk.zzbxw, zzux.zzbzn),
  zzbwq(29, zzuk.zzbxw, zzux.zzbzh),
  zzbwr(30, zzuk.zzbxw, zzux.zzbzo),
  zzbws(31, zzuk.zzbxw, zzux.zzbzh),
  zzbwt(32, zzuk.zzbxw, zzux.zzbzi),
  zzbwu(33, zzuk.zzbxw, zzux.zzbzh),
  zzbwv(34, zzuk.zzbxw, zzux.zzbzi),
  zzbww(35, zzuk.zzbxx, zzux.zzbzk),
  zzbwx(36, zzuk.zzbxx, zzux.zzbzj),
  zzbwy(37, zzuk.zzbxx, zzux.zzbzi),
  zzbwz(38, zzuk.zzbxx, zzux.zzbzi),
  zzbxa(39, zzuk.zzbxx, zzux.zzbzh),
  zzbxb(40, zzuk.zzbxx, zzux.zzbzi),
  zzbxc(41, zzuk.zzbxx, zzux.zzbzh),
  zzbxd(42, zzuk.zzbxx, zzux.zzbzl),
  zzbxe(43, zzuk.zzbxx, zzux.zzbzh),
  zzbxf(44, zzuk.zzbxx, zzux.zzbzo),
  zzbxg(45, zzuk.zzbxx, zzux.zzbzh),
  zzbxh(46, zzuk.zzbxx, zzux.zzbzi),
  zzbxi(47, zzuk.zzbxx, zzux.zzbzh),
  zzbxj(48, zzuk.zzbxx, zzux.zzbzi),
  zzbxk(49, zzuk.zzbxw, zzux.zzbzp),
  zzbxl(50, zzuk.zzbxy, zzux.zzbzg);
  
  private static final zzui[] zzbxq;
  
  private static final zzui[] zzbxs;
  
  private final int id;
  
  static {
    zzui zzui1 = zzbvn;
    byte b = 0;
    zzbxs = new zzui[] { 
        zzui1, zzbvo, zzbvp, zzbvq, zzbvr, zzbvs, zzbvt, zzbvu, zzbvv, zzbvw, 
        zzbvx, zzbvy, zzbvz, zzbwa, zzbwb, zzbwc, zzbwd, zzbwe, zzbwf, zzbwg, 
        zzbwh, zzbwi, zzbwj, zzbwk, zzbwl, zzbwm, zzbwn, zzbwo, zzbwp, zzbwq, 
        zzbwr, zzbws, zzbwt, zzbwu, zzbwv, zzbww, zzbwx, zzbwy, zzbwz, zzbxa, 
        zzbxb, zzbxc, zzbxd, zzbxe, zzbxf, zzbxg, zzbxh, zzbxi, zzbxj, zzbxk, 
        zzbxl };
    zzui[] arrayOfZzui = values();
    zzbxq = new zzui[arrayOfZzui.length];
    int i = arrayOfZzui.length;
    while (b < i) {
      zzui1 = arrayOfZzui[b];
      zzbxq[zzui1.id] = zzui1;
      b++;
    } 
  }
  
  zzui(int paramInt1, zzuk paramzzuk, zzux paramzzux) {
    this.id = paramInt1;
    this$enum$index = zzuj.zzbxt[paramzzuk.ordinal()];
    if (this$enum$index != 1) {
      if (this$enum$index == 2)
        paramzzux.zzwy(); 
    } else {
      paramzzux.zzwy();
    } 
    if (paramzzuk == zzuk.zzbxv) {
      this$enum$index = zzuj.zzbxu[paramzzux.ordinal()];
      if (this$enum$index == 1 || this$enum$index != 2);
    } 
  }
  
  public final int id() {
    return this.id;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */