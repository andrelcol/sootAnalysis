package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zztv extends zztd {
  private static final Logger logger = Logger.getLogger(zztv.class.getName());
  
  private static final boolean zzbuo = zzxj.zzyo();
  
  zztx zzbup;
  
  private zztv() {}
  
  public static int zza(int paramInt, zzvc paramzzvc) {
    paramInt = zzbd(paramInt);
    int i = paramzzvc.zzvx();
    return paramInt + zzbf(i) + i;
  }
  
  public static int zza(zzvc paramzzvc) {
    int i = paramzzvc.zzvx();
    return zzbf(i) + i;
  }
  
  public static zztv zza(ByteBuffer paramByteBuffer) {
    if (paramByteBuffer.hasArray())
      return new zzb(paramByteBuffer); 
    if (paramByteBuffer.isDirect() && !paramByteBuffer.isReadOnly())
      return (zztv)(zzxj.zzyp() ? new zze(paramByteBuffer) : new zzd(paramByteBuffer)); 
    throw new IllegalArgumentException("ByteBuffer is read-only");
  }
  
  public static int zzaw(long paramLong) {
    return zzax(paramLong);
  }
  
  public static int zzax(long paramLong) {
    if ((0xFFFFFFFFFFFFFF80L & paramLong) == 0L)
      return 1; 
    if (paramLong < 0L)
      return 10; 
    if ((0xFFFFFFF800000000L & paramLong) != 0L) {
      j = 6;
      paramLong >>>= 28L;
    } else {
      j = 2;
    } 
    int i = j;
    long l = paramLong;
    if ((0xFFFFFFFFFFE00000L & paramLong) != 0L) {
      i = j + 2;
      l = paramLong >>> 14L;
    } 
    int j = i;
    if ((l & 0xFFFFFFFFFFFFC000L) != 0L)
      j = i + 1; 
    return j;
  }
  
  public static int zzay(long paramLong) {
    return zzax(zzbb(paramLong));
  }
  
  public static int zzaz(long paramLong) {
    return 8;
  }
  
  public static int zzb(float paramFloat) {
    return 4;
  }
  
  public static int zzb(int paramInt, double paramDouble) {
    return zzbd(paramInt) + 8;
  }
  
  public static int zzb(int paramInt, float paramFloat) {
    return zzbd(paramInt) + 4;
  }
  
  public static int zzb(int paramInt, zzvc paramzzvc) {
    return (zzbd(1) << 1) + zzi(2, paramInt) + zza(3, paramzzvc);
  }
  
  static int zzb(int paramInt, zzvv paramzzvv, zzwl paramzzwl) {
    return zzbd(paramInt) + zzb(paramzzvv, paramzzwl);
  }
  
  public static int zzb(zzte paramzzte) {
    int i = paramzzte.size();
    return zzbf(i) + i;
  }
  
  static int zzb(zzvv paramzzvv, zzwl<zzvv> paramzzwl) {
    paramzzvv = paramzzvv;
    int j = paramzzvv.zztx();
    int i = j;
    if (j == -1) {
      i = paramzzwl.zzai(paramzzvv);
      paramzzvv.zzai(i);
    } 
    return zzbf(i) + i;
  }
  
  public static int zzba(long paramLong) {
    return 8;
  }
  
  private static long zzbb(long paramLong) {
    return paramLong >> 63L ^ paramLong << 1L;
  }
  
  public static int zzbd(int paramInt) {
    return zzbf(paramInt << 3);
  }
  
  public static int zzbe(int paramInt) {
    return (paramInt >= 0) ? zzbf(paramInt) : 10;
  }
  
  public static int zzbf(int paramInt) {
    return ((paramInt & 0xFFFFFF80) == 0) ? 1 : (((paramInt & 0xFFFFC000) == 0) ? 2 : (((0xFFE00000 & paramInt) == 0) ? 3 : (((paramInt & 0xF0000000) == 0) ? 4 : 5)));
  }
  
  public static int zzbg(int paramInt) {
    return zzbf(zzbk(paramInt));
  }
  
  public static int zzbh(int paramInt) {
    return 4;
  }
  
  public static int zzbi(int paramInt) {
    return 4;
  }
  
  public static int zzbj(int paramInt) {
    return zzbe(paramInt);
  }
  
  private static int zzbk(int paramInt) {
    return paramInt >> 31 ^ paramInt << 1;
  }
  
  @Deprecated
  public static int zzbl(int paramInt) {
    return zzbf(paramInt);
  }
  
  public static int zzc(double paramDouble) {
    return 8;
  }
  
  public static int zzc(int paramInt, zzte paramzzte) {
    paramInt = zzbd(paramInt);
    int i = paramzzte.size();
    return paramInt + zzbf(i) + i;
  }
  
  public static int zzc(int paramInt, zzvv paramzzvv) {
    return zzbd(paramInt) + zzc(paramzzvv);
  }
  
  @Deprecated
  static int zzc(int paramInt, zzvv paramzzvv, zzwl<zzvv> paramzzwl) {
    int j = zzbd(paramInt);
    paramzzvv = paramzzvv;
    int i = paramzzvv.zztx();
    paramInt = i;
    if (i == -1) {
      paramInt = paramzzwl.zzai(paramzzvv);
      paramzzvv.zzai(paramInt);
    } 
    return (j << 1) + paramInt;
  }
  
  public static int zzc(int paramInt, String paramString) {
    return zzbd(paramInt) + zzgc(paramString);
  }
  
  public static int zzc(int paramInt, boolean paramBoolean) {
    return zzbd(paramInt) + 1;
  }
  
  public static int zzc(zzvv paramzzvv) {
    int i = paramzzvv.zzvx();
    return zzbf(i) + i;
  }
  
  public static int zzd(int paramInt, long paramLong) {
    return zzbd(paramInt) + zzax(paramLong);
  }
  
  public static int zzd(int paramInt, zzte paramzzte) {
    return (zzbd(1) << 1) + zzi(2, paramInt) + zzc(3, paramzzte);
  }
  
  public static int zzd(int paramInt, zzvv paramzzvv) {
    return (zzbd(1) << 1) + zzi(2, paramInt) + zzc(3, paramzzvv);
  }
  
  @Deprecated
  public static int zzd(zzvv paramzzvv) {
    return paramzzvv.zzvx();
  }
  
  public static int zze(int paramInt, long paramLong) {
    return zzbd(paramInt) + zzax(paramLong);
  }
  
  public static int zzf(int paramInt, long paramLong) {
    return zzbd(paramInt) + zzax(zzbb(paramLong));
  }
  
  public static int zzg(int paramInt, long paramLong) {
    return zzbd(paramInt) + 8;
  }
  
  public static int zzgc(String paramString) {
    int i;
    try {
      i = zzxl.zza(paramString);
    } catch (zzxp zzxp) {
      i = (paramString.getBytes(zzuq.UTF_8)).length;
    } 
    return zzbf(i) + i;
  }
  
  public static int zzh(int paramInt1, int paramInt2) {
    return zzbd(paramInt1) + zzbe(paramInt2);
  }
  
  public static int zzh(int paramInt, long paramLong) {
    return zzbd(paramInt) + 8;
  }
  
  public static int zzi(int paramInt1, int paramInt2) {
    return zzbd(paramInt1) + zzbf(paramInt2);
  }
  
  public static int zzj(int paramInt1, int paramInt2) {
    return zzbd(paramInt1) + zzbf(zzbk(paramInt2));
  }
  
  public static zztv zzj(byte[] paramArrayOfbyte) {
    return new zza(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static int zzk(int paramInt1, int paramInt2) {
    return zzbd(paramInt1) + 4;
  }
  
  public static int zzk(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    return zzbf(i) + i;
  }
  
  public static int zzl(int paramInt1, int paramInt2) {
    return zzbd(paramInt1) + 4;
  }
  
  public static int zzm(int paramInt1, int paramInt2) {
    return zzbd(paramInt1) + zzbe(paramInt2);
  }
  
  public static int zzt(boolean paramBoolean) {
    return 1;
  }
  
  public abstract void flush() throws IOException;
  
  public abstract void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public final void zza(float paramFloat) throws IOException {
    zzbc(Float.floatToRawIntBits(paramFloat));
  }
  
  public final void zza(int paramInt, double paramDouble) throws IOException {
    zzc(paramInt, Double.doubleToRawLongBits(paramDouble));
  }
  
  public final void zza(int paramInt, float paramFloat) throws IOException {
    zzg(paramInt, Float.floatToRawIntBits(paramFloat));
  }
  
  public abstract void zza(int paramInt, long paramLong) throws IOException;
  
  public abstract void zza(int paramInt, zzte paramzzte) throws IOException;
  
  public abstract void zza(int paramInt, zzvv paramzzvv) throws IOException;
  
  abstract void zza(int paramInt, zzvv paramzzvv, zzwl paramzzwl) throws IOException;
  
  final void zza(String paramString, zzxp paramzzxp) throws IOException {
    logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", paramzzxp);
    byte[] arrayOfByte = paramString.getBytes(zzuq.UTF_8);
    try {
      zzba(arrayOfByte.length);
      zza(arrayOfByte, 0, arrayOfByte.length);
      return;
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      throw new zzc(indexOutOfBoundsException);
    } catch (zzc zzc) {
      throw zzc;
    } 
  }
  
  public abstract void zzat(long paramLong) throws IOException;
  
  public final void zzau(long paramLong) throws IOException {
    zzat(zzbb(paramLong));
  }
  
  public abstract void zzav(long paramLong) throws IOException;
  
  public abstract void zzaz(int paramInt) throws IOException;
  
  public final void zzb(double paramDouble) throws IOException {
    zzav(Double.doubleToRawLongBits(paramDouble));
  }
  
  public final void zzb(int paramInt, long paramLong) throws IOException {
    zza(paramInt, zzbb(paramLong));
  }
  
  public abstract void zzb(int paramInt, zzte paramzzte) throws IOException;
  
  public abstract void zzb(int paramInt, zzvv paramzzvv) throws IOException;
  
  public abstract void zzb(int paramInt, String paramString) throws IOException;
  
  public abstract void zzb(int paramInt, boolean paramBoolean) throws IOException;
  
  public abstract void zzba(int paramInt) throws IOException;
  
  public final void zzbb(int paramInt) throws IOException {
    zzba(zzbk(paramInt));
  }
  
  public abstract void zzbc(int paramInt) throws IOException;
  
  public abstract void zzc(byte paramByte) throws IOException;
  
  public abstract void zzc(int paramInt1, int paramInt2) throws IOException;
  
  public abstract void zzc(int paramInt, long paramLong) throws IOException;
  
  public abstract void zzd(int paramInt1, int paramInt2) throws IOException;
  
  public abstract void zze(int paramInt1, int paramInt2) throws IOException;
  
  public final void zzf(int paramInt1, int paramInt2) throws IOException {
    zze(paramInt1, zzbk(paramInt2));
  }
  
  public abstract void zzg(int paramInt1, int paramInt2) throws IOException;
  
  public final void zzs(boolean paramBoolean) throws IOException {
    zzc((byte)paramBoolean);
  }
  
  public abstract int zzvj();
  
  static class zza extends zztv {
    private final byte[] buffer;
    
    private final int limit;
    
    private final int offset;
    
    private int position;
    
    zza(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      super(null);
      if (param1ArrayOfbyte != null) {
        int i = param1ArrayOfbyte.length;
        int j = param1Int1 + param1Int2;
        if ((param1Int1 | param1Int2 | i - j) >= 0) {
          this.buffer = param1ArrayOfbyte;
          this.offset = param1Int1;
          this.position = param1Int1;
          this.limit = j;
          return;
        } 
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[] { Integer.valueOf(param1ArrayOfbyte.length), Integer.valueOf(param1Int1), Integer.valueOf(param1Int2) }));
      } 
      throw new NullPointerException("buffer");
    }
    
    public void flush() {}
    
    public final void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      try {
        System.arraycopy(param1ArrayOfbyte, param1Int1, this.buffer, this.position, param1Int2);
        this.position += param1Int2;
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(param1Int2) }), indexOutOfBoundsException);
      } 
    }
    
    public final void zza(int param1Int, long param1Long) throws IOException {
      zzc(param1Int, 0);
      zzat(param1Long);
    }
    
    public final void zza(int param1Int, zzte param1zzte) throws IOException {
      zzc(param1Int, 2);
      zza(param1zzte);
    }
    
    public final void zza(int param1Int, zzvv param1zzvv) throws IOException {
      zzc(param1Int, 2);
      zzb(param1zzvv);
    }
    
    final void zza(int param1Int, zzvv param1zzvv, zzwl<zzsx> param1zzwl) throws IOException {
      zzc(param1Int, 2);
      zzsx zzsx = (zzsx)param1zzvv;
      int i = zzsx.zztx();
      param1Int = i;
      if (i == -1) {
        param1Int = param1zzwl.zzai(zzsx);
        zzsx.zzai(param1Int);
      } 
      zzba(param1Int);
      param1zzwl.zza(param1zzvv, this.zzbup);
    }
    
    public final void zza(zzte param1zzte) throws IOException {
      zzba(param1zzte.size());
      param1zzte.zza(this);
    }
    
    public final void zza(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public final void zzat(long param1Long) throws IOException {
      long l = param1Long;
      if (zztv.zzvk()) {
        l = param1Long;
        if (zzvj() >= 10)
          while (true) {
            if ((param1Long & 0xFFFFFFFFFFFFFF80L) == 0L) {
              byte[] arrayOfByte1 = this.buffer;
              int j = this.position;
              this.position = j + 1;
              zzxj.zza(arrayOfByte1, j, (byte)(int)param1Long);
              return;
            } 
            byte[] arrayOfByte = this.buffer;
            int i = this.position;
            this.position = i + 1;
            zzxj.zza(arrayOfByte, i, (byte)((int)param1Long & 0x7F | 0x80));
            param1Long >>>= 7L;
          }  
      } 
      while (true) {
        if ((l & 0xFFFFFFFFFFFFFF80L) == 0L)
          try {
            byte[] arrayOfByte1 = this.buffer;
            int j = this.position;
            this.position = j + 1;
            arrayOfByte1[j] = (byte)(int)l;
            return;
          } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
          }  
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)((int)l & 0x7F | 0x80);
        l >>>= 7L;
      } 
    }
    
    public final void zzav(long param1Long) throws IOException {
      try {
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(int)param1Long;
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(int)(param1Long >> 8L);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(int)(param1Long >> 16L);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(int)(param1Long >> 24L);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(int)(param1Long >> 32L);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(int)(param1Long >> 40L);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(int)(param1Long >> 48L);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(int)(param1Long >> 56L);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
      } 
    }
    
    public final void zzaz(int param1Int) throws IOException {
      if (param1Int >= 0) {
        zzba(param1Int);
        return;
      } 
      zzat(param1Int);
    }
    
    public final void zzb(int param1Int, zzte param1zzte) throws IOException {
      zzc(1, 3);
      zze(2, param1Int);
      zza(3, param1zzte);
      zzc(1, 4);
    }
    
    public final void zzb(int param1Int, zzvv param1zzvv) throws IOException {
      zzc(1, 3);
      zze(2, param1Int);
      zza(3, param1zzvv);
      zzc(1, 4);
    }
    
    public final void zzb(int param1Int, String param1String) throws IOException {
      zzc(param1Int, 2);
      zzgb(param1String);
    }
    
    public final void zzb(int param1Int, boolean param1Boolean) throws IOException {
      zzc(param1Int, 0);
      zzc((byte)param1Boolean);
    }
    
    public final void zzb(zzvv param1zzvv) throws IOException {
      zzba(param1zzvv.zzvx());
      param1zzvv.zzb(this);
    }
    
    public final void zzba(int param1Int) throws IOException {
      int i = param1Int;
      if (zztv.zzvk()) {
        i = param1Int;
        if (zzvj() >= 10)
          while (true) {
            if ((param1Int & 0xFFFFFF80) == 0) {
              byte[] arrayOfByte1 = this.buffer;
              i = this.position;
              this.position = i + 1;
              zzxj.zza(arrayOfByte1, i, (byte)param1Int);
              return;
            } 
            byte[] arrayOfByte = this.buffer;
            i = this.position;
            this.position = i + 1;
            zzxj.zza(arrayOfByte, i, (byte)(param1Int & 0x7F | 0x80));
            param1Int >>>= 7;
          }  
      } 
      while (true) {
        if ((i & 0xFFFFFF80) == 0)
          try {
            byte[] arrayOfByte1 = this.buffer;
            param1Int = this.position;
            this.position = param1Int + 1;
            arrayOfByte1[param1Int] = (byte)i;
            return;
          } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
          }  
        byte[] arrayOfByte = this.buffer;
        param1Int = this.position;
        this.position = param1Int + 1;
        arrayOfByte[param1Int] = (byte)(i & 0x7F | 0x80);
        i >>>= 7;
      } 
    }
    
    public final void zzbc(int param1Int) throws IOException {
      try {
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)param1Int;
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(param1Int >> 8);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(param1Int >> 16);
        arrayOfByte = this.buffer;
        i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = (byte)(param1Int >>> 24);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
      } 
    }
    
    public final void zzc(byte param1Byte) throws IOException {
      try {
        byte[] arrayOfByte = this.buffer;
        int i = this.position;
        this.position = i + 1;
        arrayOfByte[i] = param1Byte;
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1) }), indexOutOfBoundsException);
      } 
    }
    
    public final void zzc(int param1Int1, int param1Int2) throws IOException {
      zzba(param1Int1 << 3 | param1Int2);
    }
    
    public final void zzc(int param1Int, long param1Long) throws IOException {
      zzc(param1Int, 1);
      zzav(param1Long);
    }
    
    public final void zzd(int param1Int1, int param1Int2) throws IOException {
      zzc(param1Int1, 0);
      zzaz(param1Int2);
    }
    
    public final void zze(int param1Int1, int param1Int2) throws IOException {
      zzc(param1Int1, 0);
      zzba(param1Int2);
    }
    
    public final void zzg(int param1Int1, int param1Int2) throws IOException {
      zzc(param1Int1, 5);
      zzbc(param1Int2);
    }
    
    public final void zzgb(String param1String) throws IOException {
      int i = this.position;
      try {
        int k = zztv.zzbf(param1String.length() * 3);
        int j = zztv.zzbf(param1String.length());
        if (j == k) {
          this.position = i + j;
          k = zzxl.zza(param1String, this.buffer, this.position, zzvj());
          this.position = i;
          zzba(k - i - j);
          this.position = k;
          return;
        } 
        zzba(zzxl.zza(param1String));
        this.position = zzxl.zza(param1String, this.buffer, this.position, zzvj());
        return;
      } catch (zzxp zzxp) {
        this.position = i;
        zza(param1String, zzxp);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zztv.zzc(indexOutOfBoundsException);
      } 
    }
    
    public final int zzvj() {
      return this.limit - this.position;
    }
    
    public final int zzvl() {
      return this.position - this.offset;
    }
  }
  
  static final class zzb extends zza {
    private final ByteBuffer zzbuq;
    
    private int zzbur;
    
    zzb(ByteBuffer param1ByteBuffer) {
      super(param1ByteBuffer.array(), param1ByteBuffer.arrayOffset() + param1ByteBuffer.position(), param1ByteBuffer.remaining());
      this.zzbuq = param1ByteBuffer;
      this.zzbur = param1ByteBuffer.position();
    }
    
    public final void flush() {
      this.zzbuq.position(this.zzbur + zzvl());
    }
  }
  
  public static final class zzc extends IOException {
    zzc(String param1String) {
      super(param1String);
    }
    
    zzc(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
    
    zzc(Throwable param1Throwable) {
      super("CodedOutputStream was writing to a flat byte array and ran out of space.", param1Throwable);
    }
  }
  
  static final class zzd extends zztv {
    private final ByteBuffer zzbus;
    
    private final ByteBuffer zzbut;
    
    zzd(ByteBuffer param1ByteBuffer) {
      super(null);
      this.zzbus = param1ByteBuffer;
      this.zzbut = param1ByteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
      param1ByteBuffer.position();
    }
    
    private final void zzgd(String param1String) throws IOException {
      try {
        zzxl.zza(param1String, this.zzbut);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zztv.zzc(indexOutOfBoundsException);
      } 
    }
    
    public final void flush() {
      this.zzbus.position(this.zzbut.position());
    }
    
    public final void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      try {
        this.zzbut.put(param1ArrayOfbyte, param1Int1, param1Int2);
        return;
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zztv.zzc(indexOutOfBoundsException);
      } catch (BufferOverflowException bufferOverflowException) {
        throw new zztv.zzc(bufferOverflowException);
      } 
    }
    
    public final void zza(int param1Int, long param1Long) throws IOException {
      zzc(param1Int, 0);
      zzat(param1Long);
    }
    
    public final void zza(int param1Int, zzte param1zzte) throws IOException {
      zzc(param1Int, 2);
      zza(param1zzte);
    }
    
    public final void zza(int param1Int, zzvv param1zzvv) throws IOException {
      zzc(param1Int, 2);
      zzb(param1zzvv);
    }
    
    final void zza(int param1Int, zzvv param1zzvv, zzwl param1zzwl) throws IOException {
      zzc(param1Int, 2);
      zza(param1zzvv, param1zzwl);
    }
    
    public final void zza(zzte param1zzte) throws IOException {
      zzba(param1zzte.size());
      param1zzte.zza(this);
    }
    
    final void zza(zzvv param1zzvv, zzwl<zzsx> param1zzwl) throws IOException {
      zzsx zzsx = (zzsx)param1zzvv;
      int j = zzsx.zztx();
      int i = j;
      if (j == -1) {
        i = param1zzwl.zzai(zzsx);
        zzsx.zzai(i);
      } 
      zzba(i);
      param1zzwl.zza(param1zzvv, this.zzbup);
    }
    
    public final void zza(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public final void zzat(long param1Long) throws IOException {
      while (true) {
        if ((0xFFFFFFFFFFFFFF80L & param1Long) == 0L)
          try {
            this.zzbut.put((byte)(int)param1Long);
            return;
          } catch (BufferOverflowException bufferOverflowException) {
            throw new zztv.zzc(bufferOverflowException);
          }  
        this.zzbut.put((byte)((int)param1Long & 0x7F | 0x80));
        param1Long >>>= 7L;
      } 
    }
    
    public final void zzav(long param1Long) throws IOException {
      try {
        this.zzbut.putLong(param1Long);
        return;
      } catch (BufferOverflowException bufferOverflowException) {
        throw new zztv.zzc(bufferOverflowException);
      } 
    }
    
    public final void zzaz(int param1Int) throws IOException {
      if (param1Int >= 0) {
        zzba(param1Int);
        return;
      } 
      zzat(param1Int);
    }
    
    public final void zzb(int param1Int, zzte param1zzte) throws IOException {
      zzc(1, 3);
      zze(2, param1Int);
      zza(3, param1zzte);
      zzc(1, 4);
    }
    
    public final void zzb(int param1Int, zzvv param1zzvv) throws IOException {
      zzc(1, 3);
      zze(2, param1Int);
      zza(3, param1zzvv);
      zzc(1, 4);
    }
    
    public final void zzb(int param1Int, String param1String) throws IOException {
      zzc(param1Int, 2);
      zzgb(param1String);
    }
    
    public final void zzb(int param1Int, boolean param1Boolean) throws IOException {
      zzc(param1Int, 0);
      zzc((byte)param1Boolean);
    }
    
    public final void zzb(zzvv param1zzvv) throws IOException {
      zzba(param1zzvv.zzvx());
      param1zzvv.zzb(this);
    }
    
    public final void zzba(int param1Int) throws IOException {
      while (true) {
        if ((param1Int & 0xFFFFFF80) == 0)
          try {
            this.zzbut.put((byte)param1Int);
            return;
          } catch (BufferOverflowException bufferOverflowException) {
            throw new zztv.zzc(bufferOverflowException);
          }  
        this.zzbut.put((byte)(param1Int & 0x7F | 0x80));
        param1Int >>>= 7;
      } 
    }
    
    public final void zzbc(int param1Int) throws IOException {
      try {
        this.zzbut.putInt(param1Int);
        return;
      } catch (BufferOverflowException bufferOverflowException) {
        throw new zztv.zzc(bufferOverflowException);
      } 
    }
    
    public final void zzc(byte param1Byte) throws IOException {
      try {
        this.zzbut.put(param1Byte);
        return;
      } catch (BufferOverflowException bufferOverflowException) {
        throw new zztv.zzc(bufferOverflowException);
      } 
    }
    
    public final void zzc(int param1Int1, int param1Int2) throws IOException {
      zzba(param1Int1 << 3 | param1Int2);
    }
    
    public final void zzc(int param1Int, long param1Long) throws IOException {
      zzc(param1Int, 1);
      zzav(param1Long);
    }
    
    public final void zzd(int param1Int1, int param1Int2) throws IOException {
      zzc(param1Int1, 0);
      zzaz(param1Int2);
    }
    
    public final void zze(int param1Int1, int param1Int2) throws IOException {
      zzc(param1Int1, 0);
      zzba(param1Int2);
    }
    
    public final void zzg(int param1Int1, int param1Int2) throws IOException {
      zzc(param1Int1, 5);
      zzbc(param1Int2);
    }
    
    public final void zzgb(String param1String) throws IOException {
      int i = this.zzbut.position();
      try {
        int j = zztv.zzbf(param1String.length() * 3);
        int k = zztv.zzbf(param1String.length());
        if (k == j) {
          j = this.zzbut.position() + k;
          this.zzbut.position(j);
          zzgd(param1String);
          k = this.zzbut.position();
          this.zzbut.position(i);
          zzba(k - j);
          this.zzbut.position(k);
          return;
        } 
        zzba(zzxl.zza(param1String));
        zzgd(param1String);
        return;
      } catch (zzxp zzxp) {
        this.zzbut.position(i);
        zza(param1String, zzxp);
        return;
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new zztv.zzc(illegalArgumentException);
      } 
    }
    
    public final int zzvj() {
      return this.zzbut.remaining();
    }
  }
  
  static final class zze extends zztv {
    private final ByteBuffer zzbus;
    
    private final ByteBuffer zzbut;
    
    private final long zzbuu;
    
    private final long zzbuv;
    
    private final long zzbuw;
    
    private final long zzbux;
    
    private long zzbuy;
    
    zze(ByteBuffer param1ByteBuffer) {
      super(null);
      this.zzbus = param1ByteBuffer;
      this.zzbut = param1ByteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
      this.zzbuu = zzxj.zzb(param1ByteBuffer);
      this.zzbuv = this.zzbuu + param1ByteBuffer.position();
      this.zzbuw = this.zzbuu + param1ByteBuffer.limit();
      this.zzbux = this.zzbuw - 10L;
      this.zzbuy = this.zzbuv;
    }
    
    private final void zzbc(long param1Long) {
      this.zzbut.position((int)(param1Long - this.zzbuu));
    }
    
    public final void flush() {
      this.zzbus.position((int)(this.zzbuy - this.zzbuu));
    }
    
    public final void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      if (param1ArrayOfbyte != null && param1Int1 >= 0 && param1Int2 >= 0 && param1ArrayOfbyte.length - param1Int2 >= param1Int1) {
        long l1 = this.zzbuw;
        long l2 = param1Int2;
        long l3 = this.zzbuy;
        if (l1 - l2 >= l3) {
          zzxj.zza(param1ArrayOfbyte, param1Int1, l3, l2);
          this.zzbuy += l2;
          return;
        } 
      } 
      if (param1ArrayOfbyte == null)
        throw new NullPointerException("value"); 
      throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(this.zzbuy), Long.valueOf(this.zzbuw), Integer.valueOf(param1Int2) }));
    }
    
    public final void zza(int param1Int, long param1Long) throws IOException {
      zzc(param1Int, 0);
      zzat(param1Long);
    }
    
    public final void zza(int param1Int, zzte param1zzte) throws IOException {
      zzc(param1Int, 2);
      zza(param1zzte);
    }
    
    public final void zza(int param1Int, zzvv param1zzvv) throws IOException {
      zzc(param1Int, 2);
      zzb(param1zzvv);
    }
    
    final void zza(int param1Int, zzvv param1zzvv, zzwl param1zzwl) throws IOException {
      zzc(param1Int, 2);
      zza(param1zzvv, param1zzwl);
    }
    
    public final void zza(zzte param1zzte) throws IOException {
      zzba(param1zzte.size());
      param1zzte.zza(this);
    }
    
    final void zza(zzvv param1zzvv, zzwl<zzsx> param1zzwl) throws IOException {
      zzsx zzsx = (zzsx)param1zzvv;
      int j = zzsx.zztx();
      int i = j;
      if (j == -1) {
        i = param1zzwl.zzai(zzsx);
        zzsx.zzai(i);
      } 
      zzba(i);
      param1zzwl.zza(param1zzvv, this.zzbup);
    }
    
    public final void zza(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public final void zzat(long param1Long) throws IOException {
      long l = param1Long;
      if (this.zzbuy <= this.zzbux)
        while (true) {
          if ((param1Long & 0xFFFFFFFFFFFFFF80L) == 0L) {
            l = this.zzbuy;
            this.zzbuy = 1L + l;
            zzxj.zza(l, (byte)(int)param1Long);
            return;
          } 
          l = this.zzbuy;
          this.zzbuy = l + 1L;
          zzxj.zza(l, (byte)((int)param1Long & 0x7F | 0x80));
          param1Long >>>= 7L;
        }  
      while (true) {
        param1Long = this.zzbuy;
        if (param1Long < this.zzbuw) {
          if ((l & 0xFFFFFFFFFFFFFF80L) == 0L) {
            this.zzbuy = 1L + param1Long;
            zzxj.zza(param1Long, (byte)(int)l);
            return;
          } 
          this.zzbuy = param1Long + 1L;
          zzxj.zza(param1Long, (byte)((int)l & 0x7F | 0x80));
          l >>>= 7L;
          continue;
        } 
        throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(param1Long), Long.valueOf(this.zzbuw), Integer.valueOf(1) }));
      } 
    }
    
    public final void zzav(long param1Long) throws IOException {
      this.zzbut.putLong((int)(this.zzbuy - this.zzbuu), param1Long);
      this.zzbuy += 8L;
    }
    
    public final void zzaz(int param1Int) throws IOException {
      if (param1Int >= 0) {
        zzba(param1Int);
        return;
      } 
      zzat(param1Int);
    }
    
    public final void zzb(int param1Int, zzte param1zzte) throws IOException {
      zzc(1, 3);
      zze(2, param1Int);
      zza(3, param1zzte);
      zzc(1, 4);
    }
    
    public final void zzb(int param1Int, zzvv param1zzvv) throws IOException {
      zzc(1, 3);
      zze(2, param1Int);
      zza(3, param1zzvv);
      zzc(1, 4);
    }
    
    public final void zzb(int param1Int, String param1String) throws IOException {
      zzc(param1Int, 2);
      zzgb(param1String);
    }
    
    public final void zzb(int param1Int, boolean param1Boolean) throws IOException {
      zzc(param1Int, 0);
      zzc((byte)param1Boolean);
    }
    
    public final void zzb(zzvv param1zzvv) throws IOException {
      zzba(param1zzvv.zzvx());
      param1zzvv.zzb(this);
    }
    
    public final void zzba(int param1Int) throws IOException {
      int i = param1Int;
      if (this.zzbuy <= this.zzbux)
        while (true) {
          if ((param1Int & 0xFFFFFF80) == 0) {
            long l1 = this.zzbuy;
            this.zzbuy = 1L + l1;
            zzxj.zza(l1, (byte)param1Int);
            return;
          } 
          long l = this.zzbuy;
          this.zzbuy = l + 1L;
          zzxj.zza(l, (byte)(param1Int & 0x7F | 0x80));
          param1Int >>>= 7;
        }  
      while (true) {
        long l = this.zzbuy;
        if (l < this.zzbuw) {
          if ((i & 0xFFFFFF80) == 0) {
            this.zzbuy = 1L + l;
            zzxj.zza(l, (byte)i);
            return;
          } 
          this.zzbuy = l + 1L;
          zzxj.zza(l, (byte)(i & 0x7F | 0x80));
          i >>>= 7;
          continue;
        } 
        throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(l), Long.valueOf(this.zzbuw), Integer.valueOf(1) }));
      } 
    }
    
    public final void zzbc(int param1Int) throws IOException {
      this.zzbut.putInt((int)(this.zzbuy - this.zzbuu), param1Int);
      this.zzbuy += 4L;
    }
    
    public final void zzc(byte param1Byte) throws IOException {
      long l = this.zzbuy;
      if (l < this.zzbuw) {
        this.zzbuy = 1L + l;
        zzxj.zza(l, param1Byte);
        return;
      } 
      throw new zztv.zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[] { Long.valueOf(l), Long.valueOf(this.zzbuw), Integer.valueOf(1) }));
    }
    
    public final void zzc(int param1Int1, int param1Int2) throws IOException {
      zzba(param1Int1 << 3 | param1Int2);
    }
    
    public final void zzc(int param1Int, long param1Long) throws IOException {
      zzc(param1Int, 1);
      zzav(param1Long);
    }
    
    public final void zzd(int param1Int1, int param1Int2) throws IOException {
      zzc(param1Int1, 0);
      zzaz(param1Int2);
    }
    
    public final void zze(int param1Int1, int param1Int2) throws IOException {
      zzc(param1Int1, 0);
      zzba(param1Int2);
    }
    
    public final void zzg(int param1Int1, int param1Int2) throws IOException {
      zzc(param1Int1, 5);
      zzbc(param1Int2);
    }
    
    public final void zzgb(String param1String) throws IOException {
      long l = this.zzbuy;
      try {
        int j = zztv.zzbf(param1String.length() * 3);
        int i = zztv.zzbf(param1String.length());
        if (i == j) {
          i = (int)(this.zzbuy - this.zzbuu) + i;
          this.zzbut.position(i);
          zzxl.zza(param1String, this.zzbut);
          i = this.zzbut.position() - i;
          zzba(i);
          this.zzbuy += i;
          return;
        } 
        i = zzxl.zza(param1String);
        zzba(i);
        zzbc(this.zzbuy);
        zzxl.zza(param1String, this.zzbut);
        this.zzbuy += i;
        return;
      } catch (zzxp zzxp) {
        this.zzbuy = l;
        zzbc(this.zzbuy);
        zza(param1String, zzxp);
        return;
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new zztv.zzc(illegalArgumentException);
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        throw new zztv.zzc(indexOutOfBoundsException);
      } 
    }
    
    public final int zzvj() {
      return (int)(this.zzbuw - this.zzbuy);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zztv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */