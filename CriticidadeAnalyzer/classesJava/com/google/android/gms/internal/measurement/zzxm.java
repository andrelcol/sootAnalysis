package com.google.android.gms.internal.measurement;

final class zzxm {
  private static void zza(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, char[] paramArrayOfchar, int paramInt) throws zzuv {
    if (!zzg(paramByte2) && (paramByte1 << 28) + paramByte2 + 112 >> 30 == 0 && !zzg(paramByte3) && !zzg(paramByte4)) {
      int i = (paramByte1 & 0x7) << 18 | (paramByte2 & 0x3F) << 12 | (paramByte3 & 0x3F) << 6 | paramByte4 & 0x3F;
      paramArrayOfchar[paramInt] = (char)((i >>> 10) + 55232);
      paramArrayOfchar[paramInt + 1] = (char)((i & 0x3FF) + 56320);
      return;
    } 
    throw zzuv.zzwx();
  }
  
  private static void zza(byte paramByte1, byte paramByte2, byte paramByte3, char[] paramArrayOfchar, int paramInt) throws zzuv {
    if (!zzg(paramByte2) && (paramByte1 != -32 || paramByte2 >= -96) && (paramByte1 != -19 || paramByte2 < -96) && !zzg(paramByte3)) {
      paramArrayOfchar[paramInt] = (char)((paramByte1 & 0xF) << 12 | (paramByte2 & 0x3F) << 6 | paramByte3 & 0x3F);
      return;
    } 
    throw zzuv.zzwx();
  }
  
  private static void zza(byte paramByte1, byte paramByte2, char[] paramArrayOfchar, int paramInt) throws zzuv {
    if (paramByte1 >= -62 && !zzg(paramByte2)) {
      paramArrayOfchar[paramInt] = (char)((paramByte1 & 0x1F) << 6 | paramByte2 & 0x3F);
      return;
    } 
    throw zzuv.zzwx();
  }
  
  private static void zza(byte paramByte, char[] paramArrayOfchar, int paramInt) {
    paramArrayOfchar[paramInt] = (char)paramByte;
  }
  
  private static boolean zzd(byte paramByte) {
    return (paramByte >= 0);
  }
  
  private static boolean zze(byte paramByte) {
    return (paramByte < -32);
  }
  
  private static boolean zzf(byte paramByte) {
    return (paramByte < -16);
  }
  
  private static boolean zzg(byte paramByte) {
    return (paramByte > -65);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */