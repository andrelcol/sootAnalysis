package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

final class zztt implements zzwk {
  private int tag;
  
  private final zztq zzbuk;
  
  private int zzbul;
  
  private int zzbum = 0;
  
  private zztt(zztq paramzztq) {
    zzuq.zza(paramzztq, "input");
    this.zzbuk = paramzztq;
    this.zzbuk.zzbud = this;
  }
  
  public static zztt zza(zztq paramzztq) {
    zztt zztt1 = paramzztq.zzbud;
    return (zztt1 != null) ? zztt1 : new zztt(paramzztq);
  }
  
  private final void zza(List<String> paramList, boolean paramBoolean) throws IOException {
    if ((this.tag & 0x7) == 2) {
      if (paramList instanceof zzve && !paramBoolean) {
        paramList = paramList;
        while (true) {
          paramList.zzc(zzur());
          if (this.zzbuk.zzuz())
            return; 
          int i = this.zzbuk.zzuj();
          if (i != this.tag) {
            this.zzbum = i;
            return;
          } 
        } 
      } 
      while (true) {
        String str;
        if (paramBoolean) {
          str = zzuq();
        } else {
          str = readString();
        } 
        paramList.add(str);
        if (this.zzbuk.zzuz())
          return; 
        int i = this.zzbuk.zzuj();
        if (i != this.tag) {
          this.zzbum = i;
          return;
        } 
      } 
    } 
    throw zzuv.zzwu();
  }
  
  private final void zzav(int paramInt) throws IOException {
    if ((this.tag & 0x7) == paramInt)
      return; 
    throw zzuv.zzwu();
  }
  
  private static void zzaw(int paramInt) throws IOException {
    if ((paramInt & 0x7) == 0)
      return; 
    throw zzuv.zzww();
  }
  
  private static void zzax(int paramInt) throws IOException {
    if ((paramInt & 0x3) == 0)
      return; 
    throw zzuv.zzww();
  }
  
  private final void zzay(int paramInt) throws IOException {
    if (this.zzbuk.zzva() == paramInt)
      return; 
    throw zzuv.zzwq();
  }
  
  private final <T> T zzc(zzwl<T> paramzzwl, zzub paramzzub) throws IOException {
    int i = this.zzbuk.zzus();
    zztq zztq1 = this.zzbuk;
    if (zztq1.zzbua < zztq1.zzbub) {
      i = zztq1.zzas(i);
      zztq1 = (zztq)paramzzwl.newInstance();
      zztq zztq3 = this.zzbuk;
      zztq3.zzbua++;
      paramzzwl.zza((T)zztq1, this, paramzzub);
      paramzzwl.zzy((T)zztq1);
      this.zzbuk.zzap(0);
      zztq zztq2 = this.zzbuk;
      zztq2.zzbua--;
      zztq2.zzat(i);
      return (T)zztq1;
    } 
    throw zzuv.zzwv();
  }
  
  private final <T> T zzd(zzwl<T> paramzzwl, zzub paramzzub) throws IOException {
    int i = this.zzbul;
    this.zzbul = this.tag >>> 3 << 3 | 0x4;
    try {
      T t = paramzzwl.newInstance();
      paramzzwl.zza(t, this, paramzzub);
      paramzzwl.zzy(t);
      int k = this.tag;
      int j = this.zzbul;
      if (k == j)
        return t; 
      throw zzuv.zzww();
    } finally {
      this.zzbul = i;
    } 
  }
  
  public final int getTag() {
    return this.tag;
  }
  
  public final double readDouble() throws IOException {
    zzav(1);
    return this.zzbuk.readDouble();
  }
  
  public final float readFloat() throws IOException {
    zzav(5);
    return this.zzbuk.readFloat();
  }
  
  public final String readString() throws IOException {
    zzav(2);
    return this.zzbuk.readString();
  }
  
  public final void readStringList(List<String> paramList) throws IOException {
    zza(paramList, false);
  }
  
  public final <T> T zza(zzwl<T> paramzzwl, zzub paramzzub) throws IOException {
    zzav(2);
    return zzc(paramzzwl, paramzzub);
  }
  
  public final <T> void zza(List<T> paramList, zzwl<T> paramzzwl, zzub paramzzub) throws IOException {
    int i = this.tag;
    if ((i & 0x7) == 2) {
      while (true) {
        paramList.add(zzc(paramzzwl, paramzzub));
        if (this.zzbuk.zzuz() || this.zzbum != 0)
          break; 
        int j = this.zzbuk.zzuj();
        if (j != i) {
          this.zzbum = j;
          break;
        } 
      } 
      return;
    } 
    throw zzuv.zzwu();
  }
  
  public final <T> T zzb(zzwl<T> paramzzwl, zzub paramzzub) throws IOException {
    zzav(3);
    return zzd(paramzzwl, paramzzub);
  }
  
  public final <T> void zzb(List<T> paramList, zzwl<T> paramzzwl, zzub paramzzub) throws IOException {
    int i = this.tag;
    if ((i & 0x7) == 3) {
      while (true) {
        paramList.add(zzd(paramzzwl, paramzzub));
        if (this.zzbuk.zzuz() || this.zzbum != 0)
          break; 
        int j = this.zzbuk.zzuj();
        if (j != i) {
          this.zzbum = j;
          break;
        } 
      } 
      return;
    } 
    throw zzuv.zzwu();
  }
  
  public final void zzi(List<Double> paramList) throws IOException {
    if (paramList instanceof zzty) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 1) {
        if (j == 2) {
          int k = this.zzbuk.zzus();
          zzaw(k);
          j = this.zzbuk.zzva();
          do {
            paramList.zzd(this.zzbuk.readDouble());
          } while (this.zzbuk.zzva() < j + k);
          return;
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzd(this.zzbuk.readDouble());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 1) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        zzaw(i);
        int j = this.zzbuk.zzva();
        do {
          paramList.add(Double.valueOf(this.zzbuk.readDouble()));
        } while (this.zzbuk.zzva() < j + i);
        return;
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Double.valueOf(this.zzbuk.readDouble()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final void zzj(List<Float> paramList) throws IOException {
    if (paramList instanceof zzul) {
      paramList = paramList;
      int k = this.tag & 0x7;
      if (k != 2) {
        if (k == 5)
          while (true) {
            paramList.zzc(this.zzbuk.readFloat());
            if (this.zzbuk.zzuz())
              return; 
            k = this.zzbuk.zzuj();
            if (k != this.tag) {
              this.zzbum = k;
              return;
            } 
          }  
        throw zzuv.zzwu();
      } 
      k = this.zzbuk.zzus();
      zzax(k);
      int m = this.zzbuk.zzva();
      do {
        paramList.zzc(this.zzbuk.readFloat());
      } while (this.zzbuk.zzva() < m + k);
      return;
    } 
    int i = this.tag & 0x7;
    if (i != 2) {
      if (i == 5)
        while (true) {
          paramList.add(Float.valueOf(this.zzbuk.readFloat()));
          if (this.zzbuk.zzuz())
            return; 
          i = this.zzbuk.zzuj();
          if (i != this.tag) {
            this.zzbum = i;
            return;
          } 
        }  
      throw zzuv.zzwu();
    } 
    int j = this.zzbuk.zzus();
    zzax(j);
    i = this.zzbuk.zzva();
    do {
      paramList.add(Float.valueOf(this.zzbuk.readFloat()));
    } while (this.zzbuk.zzva() < i + j);
  }
  
  public final void zzk(List<Long> paramList) throws IOException {
    if (paramList instanceof zzvj) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 0) {
        if (j == 2) {
          j = this.zzbuk.zzus();
          j = this.zzbuk.zzva() + j;
          while (true) {
            paramList.zzbe(this.zzbuk.zzuk());
            if (this.zzbuk.zzva() >= j) {
              zzay(j);
              return;
            } 
          } 
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzbe(this.zzbuk.zzuk());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 0) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        i = this.zzbuk.zzva() + i;
        while (true) {
          paramList.add(Long.valueOf(this.zzbuk.zzuk()));
          if (this.zzbuk.zzva() >= i) {
            zzay(i);
            return;
          } 
        } 
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Long.valueOf(this.zzbuk.zzuk()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final void zzl(List<Long> paramList) throws IOException {
    if (paramList instanceof zzvj) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 0) {
        if (j == 2) {
          j = this.zzbuk.zzus();
          j = this.zzbuk.zzva() + j;
          while (true) {
            paramList.zzbe(this.zzbuk.zzul());
            if (this.zzbuk.zzva() >= j) {
              zzay(j);
              return;
            } 
          } 
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzbe(this.zzbuk.zzul());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 0) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        i = this.zzbuk.zzva() + i;
        while (true) {
          paramList.add(Long.valueOf(this.zzbuk.zzul()));
          if (this.zzbuk.zzva() >= i) {
            zzay(i);
            return;
          } 
        } 
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Long.valueOf(this.zzbuk.zzul()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final void zzm(List<Integer> paramList) throws IOException {
    if (paramList instanceof zzup) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 0) {
        if (j == 2) {
          j = this.zzbuk.zzus();
          j = this.zzbuk.zzva() + j;
          while (true) {
            paramList.zzbo(this.zzbuk.zzum());
            if (this.zzbuk.zzva() >= j) {
              zzay(j);
              return;
            } 
          } 
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzbo(this.zzbuk.zzum());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 0) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        i = this.zzbuk.zzva() + i;
        while (true) {
          paramList.add(Integer.valueOf(this.zzbuk.zzum()));
          if (this.zzbuk.zzva() >= i) {
            zzay(i);
            return;
          } 
        } 
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Integer.valueOf(this.zzbuk.zzum()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final void zzn(List<Long> paramList) throws IOException {
    if (paramList instanceof zzvj) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 1) {
        if (j == 2) {
          int k = this.zzbuk.zzus();
          zzaw(k);
          j = this.zzbuk.zzva();
          do {
            paramList.zzbe(this.zzbuk.zzun());
          } while (this.zzbuk.zzva() < j + k);
          return;
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzbe(this.zzbuk.zzun());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 1) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        zzaw(i);
        int j = this.zzbuk.zzva();
        do {
          paramList.add(Long.valueOf(this.zzbuk.zzun()));
        } while (this.zzbuk.zzva() < j + i);
        return;
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Long.valueOf(this.zzbuk.zzun()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final void zzo(List<Integer> paramList) throws IOException {
    if (paramList instanceof zzup) {
      paramList = paramList;
      int k = this.tag & 0x7;
      if (k != 2) {
        if (k == 5)
          while (true) {
            paramList.zzbo(this.zzbuk.zzuo());
            if (this.zzbuk.zzuz())
              return; 
            k = this.zzbuk.zzuj();
            if (k != this.tag) {
              this.zzbum = k;
              return;
            } 
          }  
        throw zzuv.zzwu();
      } 
      k = this.zzbuk.zzus();
      zzax(k);
      int m = this.zzbuk.zzva();
      do {
        paramList.zzbo(this.zzbuk.zzuo());
      } while (this.zzbuk.zzva() < m + k);
      return;
    } 
    int i = this.tag & 0x7;
    if (i != 2) {
      if (i == 5)
        while (true) {
          paramList.add(Integer.valueOf(this.zzbuk.zzuo()));
          if (this.zzbuk.zzuz())
            return; 
          i = this.zzbuk.zzuj();
          if (i != this.tag) {
            this.zzbum = i;
            return;
          } 
        }  
      throw zzuv.zzwu();
    } 
    int j = this.zzbuk.zzus();
    zzax(j);
    i = this.zzbuk.zzva();
    do {
      paramList.add(Integer.valueOf(this.zzbuk.zzuo()));
    } while (this.zzbuk.zzva() < i + j);
  }
  
  public final void zzp(List<Boolean> paramList) throws IOException {
    if (paramList instanceof zztc) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 0) {
        if (j == 2) {
          j = this.zzbuk.zzus();
          j = this.zzbuk.zzva() + j;
          while (true) {
            paramList.addBoolean(this.zzbuk.zzup());
            if (this.zzbuk.zzva() >= j) {
              zzay(j);
              return;
            } 
          } 
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.addBoolean(this.zzbuk.zzup());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 0) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        i = this.zzbuk.zzva() + i;
        while (true) {
          paramList.add(Boolean.valueOf(this.zzbuk.zzup()));
          if (this.zzbuk.zzva() >= i) {
            zzay(i);
            return;
          } 
        } 
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Boolean.valueOf(this.zzbuk.zzup()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final void zzq(List<String> paramList) throws IOException {
    zza(paramList, true);
  }
  
  public final void zzr(List<zzte> paramList) throws IOException {
    if ((this.tag & 0x7) == 2)
      while (true) {
        paramList.add(zzur());
        if (this.zzbuk.zzuz())
          return; 
        int i = this.zzbuk.zzuj();
        if (i != this.tag) {
          this.zzbum = i;
          return;
        } 
      }  
    throw zzuv.zzwu();
  }
  
  public final void zzs(List<Integer> paramList) throws IOException {
    if (paramList instanceof zzup) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 0) {
        if (j == 2) {
          j = this.zzbuk.zzus();
          j = this.zzbuk.zzva() + j;
          while (true) {
            paramList.zzbo(this.zzbuk.zzus());
            if (this.zzbuk.zzva() >= j) {
              zzay(j);
              return;
            } 
          } 
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzbo(this.zzbuk.zzus());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 0) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        i = this.zzbuk.zzva() + i;
        while (true) {
          paramList.add(Integer.valueOf(this.zzbuk.zzus()));
          if (this.zzbuk.zzva() >= i) {
            zzay(i);
            return;
          } 
        } 
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Integer.valueOf(this.zzbuk.zzus()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final void zzt(List<Integer> paramList) throws IOException {
    if (paramList instanceof zzup) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 0) {
        if (j == 2) {
          j = this.zzbuk.zzus();
          j = this.zzbuk.zzva() + j;
          while (true) {
            paramList.zzbo(this.zzbuk.zzut());
            if (this.zzbuk.zzva() >= j) {
              zzay(j);
              return;
            } 
          } 
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzbo(this.zzbuk.zzut());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 0) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        i = this.zzbuk.zzva() + i;
        while (true) {
          paramList.add(Integer.valueOf(this.zzbuk.zzut()));
          if (this.zzbuk.zzva() >= i) {
            zzay(i);
            return;
          } 
        } 
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Integer.valueOf(this.zzbuk.zzut()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final void zzu(List<Integer> paramList) throws IOException {
    if (paramList instanceof zzup) {
      paramList = paramList;
      int k = this.tag & 0x7;
      if (k != 2) {
        if (k == 5)
          while (true) {
            paramList.zzbo(this.zzbuk.zzuu());
            if (this.zzbuk.zzuz())
              return; 
            k = this.zzbuk.zzuj();
            if (k != this.tag) {
              this.zzbum = k;
              return;
            } 
          }  
        throw zzuv.zzwu();
      } 
      int m = this.zzbuk.zzus();
      zzax(m);
      k = this.zzbuk.zzva();
      do {
        paramList.zzbo(this.zzbuk.zzuu());
      } while (this.zzbuk.zzva() < k + m);
      return;
    } 
    int i = this.tag & 0x7;
    if (i != 2) {
      if (i == 5)
        while (true) {
          paramList.add(Integer.valueOf(this.zzbuk.zzuu()));
          if (this.zzbuk.zzuz())
            return; 
          i = this.zzbuk.zzuj();
          if (i != this.tag) {
            this.zzbum = i;
            return;
          } 
        }  
      throw zzuv.zzwu();
    } 
    i = this.zzbuk.zzus();
    zzax(i);
    int j = this.zzbuk.zzva();
    do {
      paramList.add(Integer.valueOf(this.zzbuk.zzuu()));
    } while (this.zzbuk.zzva() < j + i);
  }
  
  public final long zzuk() throws IOException {
    zzav(0);
    return this.zzbuk.zzuk();
  }
  
  public final long zzul() throws IOException {
    zzav(0);
    return this.zzbuk.zzul();
  }
  
  public final int zzum() throws IOException {
    zzav(0);
    return this.zzbuk.zzum();
  }
  
  public final long zzun() throws IOException {
    zzav(1);
    return this.zzbuk.zzun();
  }
  
  public final int zzuo() throws IOException {
    zzav(5);
    return this.zzbuk.zzuo();
  }
  
  public final boolean zzup() throws IOException {
    zzav(0);
    return this.zzbuk.zzup();
  }
  
  public final String zzuq() throws IOException {
    zzav(2);
    return this.zzbuk.zzuq();
  }
  
  public final zzte zzur() throws IOException {
    zzav(2);
    return this.zzbuk.zzur();
  }
  
  public final int zzus() throws IOException {
    zzav(0);
    return this.zzbuk.zzus();
  }
  
  public final int zzut() throws IOException {
    zzav(0);
    return this.zzbuk.zzut();
  }
  
  public final int zzuu() throws IOException {
    zzav(5);
    return this.zzbuk.zzuu();
  }
  
  public final long zzuv() throws IOException {
    zzav(1);
    return this.zzbuk.zzuv();
  }
  
  public final int zzuw() throws IOException {
    zzav(0);
    return this.zzbuk.zzuw();
  }
  
  public final long zzux() throws IOException {
    zzav(0);
    return this.zzbuk.zzux();
  }
  
  public final void zzv(List<Long> paramList) throws IOException {
    if (paramList instanceof zzvj) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 1) {
        if (j == 2) {
          int k = this.zzbuk.zzus();
          zzaw(k);
          j = this.zzbuk.zzva();
          do {
            paramList.zzbe(this.zzbuk.zzuv());
          } while (this.zzbuk.zzva() < j + k);
          return;
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzbe(this.zzbuk.zzuv());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 1) {
      if (i == 2) {
        int j = this.zzbuk.zzus();
        zzaw(j);
        i = this.zzbuk.zzva();
        do {
          paramList.add(Long.valueOf(this.zzbuk.zzuv()));
        } while (this.zzbuk.zzva() < i + j);
        return;
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Long.valueOf(this.zzbuk.zzuv()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final int zzvh() throws IOException {
    int i = this.zzbum;
    if (i != 0) {
      this.tag = i;
      this.zzbum = 0;
    } else {
      this.tag = this.zzbuk.zzuj();
    } 
    i = this.tag;
    return (i == 0 || i == this.zzbul) ? Integer.MAX_VALUE : (i >>> 3);
  }
  
  public final boolean zzvi() throws IOException {
    if (!this.zzbuk.zzuz()) {
      int i = this.tag;
      if (i != this.zzbul)
        return this.zzbuk.zzaq(i); 
    } 
    return false;
  }
  
  public final void zzw(List<Integer> paramList) throws IOException {
    if (paramList instanceof zzup) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 0) {
        if (j == 2) {
          j = this.zzbuk.zzus();
          j = this.zzbuk.zzva() + j;
          while (true) {
            paramList.zzbo(this.zzbuk.zzuw());
            if (this.zzbuk.zzva() >= j) {
              zzay(j);
              return;
            } 
          } 
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzbo(this.zzbuk.zzuw());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 0) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        i = this.zzbuk.zzva() + i;
        while (true) {
          paramList.add(Integer.valueOf(this.zzbuk.zzuw()));
          if (this.zzbuk.zzva() >= i) {
            zzay(i);
            return;
          } 
        } 
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Integer.valueOf(this.zzbuk.zzuw()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
  
  public final void zzx(List<Long> paramList) throws IOException {
    if (paramList instanceof zzvj) {
      paramList = paramList;
      int j = this.tag & 0x7;
      if (j != 0) {
        if (j == 2) {
          j = this.zzbuk.zzus();
          j = this.zzbuk.zzva() + j;
          while (true) {
            paramList.zzbe(this.zzbuk.zzux());
            if (this.zzbuk.zzva() >= j) {
              zzay(j);
              return;
            } 
          } 
        } 
        throw zzuv.zzwu();
      } 
      while (true) {
        paramList.zzbe(this.zzbuk.zzux());
        if (this.zzbuk.zzuz())
          return; 
        j = this.zzbuk.zzuj();
        if (j != this.tag) {
          this.zzbum = j;
          return;
        } 
      } 
    } 
    int i = this.tag & 0x7;
    if (i != 0) {
      if (i == 2) {
        i = this.zzbuk.zzus();
        i = this.zzbuk.zzva() + i;
        while (true) {
          paramList.add(Long.valueOf(this.zzbuk.zzux()));
          if (this.zzbuk.zzva() >= i) {
            zzay(i);
            return;
          } 
        } 
      } 
      throw zzuv.zzwu();
    } 
    while (true) {
      paramList.add(Long.valueOf(this.zzbuk.zzux()));
      if (this.zzbuk.zzuz())
        return; 
      i = this.zzbuk.zzuj();
      if (i != this.tag) {
        this.zzbum = i;
        return;
      } 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zztt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */