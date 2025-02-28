package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

final class zzxq extends zzxn {
  private static int zza(byte[] paramArrayOfbyte, int paramInt1, long paramLong, int paramInt2) {
    if (paramInt2 != 0) {
      if (paramInt2 != 1) {
        if (paramInt2 == 2)
          return zzxl.zzd(paramInt1, zzxj.zza(paramArrayOfbyte, paramLong), zzxj.zza(paramArrayOfbyte, paramLong + 1L)); 
        throw new AssertionError();
      } 
      return zzxl.zzr(paramInt1, zzxj.zza(paramArrayOfbyte, paramLong));
    } 
    return zzxl.zzca(paramInt1);
  }
  
  final int zzb(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: iload_3
    //   1: iload #4
    //   3: ior
    //   4: aload_2
    //   5: arraylength
    //   6: iload #4
    //   8: isub
    //   9: ior
    //   10: iflt -> 387
    //   13: iload_3
    //   14: i2l
    //   15: lstore #7
    //   17: iload #4
    //   19: i2l
    //   20: lload #7
    //   22: lsub
    //   23: l2i
    //   24: istore_3
    //   25: iload_3
    //   26: bipush #16
    //   28: if_icmpge -> 36
    //   31: iconst_0
    //   32: istore_1
    //   33: goto -> 73
    //   36: lload #7
    //   38: lstore #5
    //   40: iconst_0
    //   41: istore_1
    //   42: iload_1
    //   43: iload_3
    //   44: if_icmpge -> 71
    //   47: aload_2
    //   48: lload #5
    //   50: invokestatic zza : ([BJ)B
    //   53: ifge -> 59
    //   56: goto -> 73
    //   59: iinc #1, 1
    //   62: lload #5
    //   64: lconst_1
    //   65: ladd
    //   66: lstore #5
    //   68: goto -> 42
    //   71: iload_3
    //   72: istore_1
    //   73: iload_3
    //   74: iload_1
    //   75: isub
    //   76: istore_3
    //   77: lload #7
    //   79: iload_1
    //   80: i2l
    //   81: ladd
    //   82: lstore #5
    //   84: iload_3
    //   85: istore_1
    //   86: iconst_0
    //   87: istore #4
    //   89: iload_1
    //   90: istore_3
    //   91: iload #4
    //   93: istore_1
    //   94: iload_3
    //   95: ifle -> 137
    //   98: lload #5
    //   100: lconst_1
    //   101: ladd
    //   102: lstore #9
    //   104: aload_2
    //   105: lload #5
    //   107: invokestatic zza : ([BJ)B
    //   110: istore #4
    //   112: iload #4
    //   114: istore_1
    //   115: lload #9
    //   117: lstore #7
    //   119: iload #4
    //   121: iflt -> 141
    //   124: iinc #3, -1
    //   127: lload #9
    //   129: lstore #5
    //   131: iload #4
    //   133: istore_1
    //   134: goto -> 94
    //   137: lload #5
    //   139: lstore #7
    //   141: iload_3
    //   142: ifne -> 147
    //   145: iconst_0
    //   146: ireturn
    //   147: iinc #3, -1
    //   150: iload_1
    //   151: bipush #-32
    //   153: if_icmpge -> 198
    //   156: iload_3
    //   157: ifne -> 162
    //   160: iload_1
    //   161: ireturn
    //   162: iinc #3, -1
    //   165: iload_1
    //   166: bipush #-62
    //   168: if_icmplt -> 196
    //   171: lload #7
    //   173: lconst_1
    //   174: ladd
    //   175: lstore #5
    //   177: iload_3
    //   178: istore_1
    //   179: aload_2
    //   180: lload #7
    //   182: invokestatic zza : ([BJ)B
    //   185: bipush #-65
    //   187: if_icmple -> 193
    //   190: goto -> 196
    //   193: goto -> 86
    //   196: iconst_m1
    //   197: ireturn
    //   198: iload_1
    //   199: bipush #-16
    //   201: if_icmpge -> 295
    //   204: iload_3
    //   205: iconst_2
    //   206: if_icmpge -> 218
    //   209: aload_2
    //   210: iload_1
    //   211: lload #7
    //   213: iload_3
    //   214: invokestatic zza : ([BIJI)I
    //   217: ireturn
    //   218: iinc #3, -2
    //   221: lload #7
    //   223: lconst_1
    //   224: ladd
    //   225: lstore #5
    //   227: aload_2
    //   228: lload #7
    //   230: invokestatic zza : ([BJ)B
    //   233: istore #4
    //   235: iload #4
    //   237: bipush #-65
    //   239: if_icmpgt -> 293
    //   242: iload_1
    //   243: bipush #-32
    //   245: if_icmpne -> 255
    //   248: iload #4
    //   250: bipush #-96
    //   252: if_icmplt -> 293
    //   255: iload_1
    //   256: bipush #-19
    //   258: if_icmpne -> 268
    //   261: iload #4
    //   263: bipush #-96
    //   265: if_icmpge -> 293
    //   268: aload_2
    //   269: lload #5
    //   271: invokestatic zza : ([BJ)B
    //   274: bipush #-65
    //   276: if_icmple -> 282
    //   279: goto -> 293
    //   282: lload #5
    //   284: lconst_1
    //   285: ladd
    //   286: lstore #5
    //   288: iload_3
    //   289: istore_1
    //   290: goto -> 86
    //   293: iconst_m1
    //   294: ireturn
    //   295: iload_3
    //   296: iconst_3
    //   297: if_icmpge -> 309
    //   300: aload_2
    //   301: iload_1
    //   302: lload #7
    //   304: iload_3
    //   305: invokestatic zza : ([BIJI)I
    //   308: ireturn
    //   309: iinc #3, -3
    //   312: lload #7
    //   314: lconst_1
    //   315: ladd
    //   316: lstore #5
    //   318: aload_2
    //   319: lload #7
    //   321: invokestatic zza : ([BJ)B
    //   324: istore #4
    //   326: iload #4
    //   328: bipush #-65
    //   330: if_icmpgt -> 385
    //   333: iload_1
    //   334: bipush #28
    //   336: ishl
    //   337: iload #4
    //   339: bipush #112
    //   341: iadd
    //   342: iadd
    //   343: bipush #30
    //   345: ishr
    //   346: ifne -> 385
    //   349: lload #5
    //   351: lconst_1
    //   352: ladd
    //   353: lstore #7
    //   355: aload_2
    //   356: lload #5
    //   358: invokestatic zza : ([BJ)B
    //   361: bipush #-65
    //   363: if_icmpgt -> 385
    //   366: lload #7
    //   368: lconst_1
    //   369: ladd
    //   370: lstore #5
    //   372: iload_3
    //   373: istore_1
    //   374: aload_2
    //   375: lload #7
    //   377: invokestatic zza : ([BJ)B
    //   380: bipush #-65
    //   382: if_icmple -> 193
    //   385: iconst_m1
    //   386: ireturn
    //   387: new java/lang/ArrayIndexOutOfBoundsException
    //   390: dup
    //   391: ldc 'Array length=%d, index=%d, limit=%d'
    //   393: iconst_3
    //   394: anewarray java/lang/Object
    //   397: dup
    //   398: iconst_0
    //   399: aload_2
    //   400: arraylength
    //   401: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   404: aastore
    //   405: dup
    //   406: iconst_1
    //   407: iload_3
    //   408: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   411: aastore
    //   412: dup
    //   413: iconst_2
    //   414: iload #4
    //   416: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   419: aastore
    //   420: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   423: invokespecial <init> : (Ljava/lang/String;)V
    //   426: athrow
  }
  
  final int zzb(CharSequence paramCharSequence, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    long l1 = paramInt1;
    long l2 = paramInt2 + l1;
    int i = paramCharSequence.length();
    if (i <= paramInt2 && paramArrayOfbyte.length - paramInt2 >= paramInt1) {
      long l3;
      paramInt2 = 0;
      while (true) {
        l3 = 1L;
        if (paramInt2 < i) {
          paramInt1 = paramCharSequence.charAt(paramInt2);
          if (paramInt1 < 128) {
            zzxj.zza(paramArrayOfbyte, l1, (byte)paramInt1);
            paramInt2++;
            l1 = 1L + l1;
            continue;
          } 
        } 
        break;
      } 
      paramInt1 = paramInt2;
      long l4 = l1;
      if (paramInt2 == i)
        return (int)l1; 
      while (paramInt1 < i) {
        char c1 = paramCharSequence.charAt(paramInt1);
        if (c1 < '' && l4 < l2) {
          zzxj.zza(paramArrayOfbyte, l4, (byte)c1);
          long l = l3;
          l1 = l4 + l3;
          l3 = l;
        } else if (c1 < 'ࠀ' && l4 <= l2 - 2L) {
          l1 = l4 + l3;
          zzxj.zza(paramArrayOfbyte, l4, (byte)(c1 >>> 6 | 0x3C0));
          zzxj.zza(paramArrayOfbyte, l1, (byte)(c1 & 0x3F | 0x80));
          l1 += l3;
        } else if ((c1 < '?' || '?' < c1) && l4 <= l2 - 3L) {
          l1 = l4 + l3;
          zzxj.zza(paramArrayOfbyte, l4, (byte)(c1 >>> 12 | 0x1E0));
          l3 = l1 + l3;
          zzxj.zza(paramArrayOfbyte, l1, (byte)(c1 >>> 6 & 0x3F | 0x80));
          zzxj.zza(paramArrayOfbyte, l3, (byte)(c1 & 0x3F | 0x80));
          l1 = l3 + 1L;
          l3 = 1L;
        } else if (l4 <= l2 - 4L) {
          paramInt2 = paramInt1 + 1;
          if (paramInt2 != i) {
            char c2 = paramCharSequence.charAt(paramInt2);
            if (Character.isSurrogatePair(c1, c2)) {
              paramInt1 = Character.toCodePoint(c1, c2);
              l3 = l4 + 1L;
              zzxj.zza(paramArrayOfbyte, l4, (byte)(paramInt1 >>> 18 | 0xF0));
              l1 = l3 + 1L;
              zzxj.zza(paramArrayOfbyte, l3, (byte)(paramInt1 >>> 12 & 0x3F | 0x80));
              l4 = l1 + 1L;
              zzxj.zza(paramArrayOfbyte, l1, (byte)(paramInt1 >>> 6 & 0x3F | 0x80));
              l3 = 1L;
              l1 = l4 + 1L;
              zzxj.zza(paramArrayOfbyte, l4, (byte)(paramInt1 & 0x3F | 0x80));
              paramInt1 = paramInt2;
            } else {
              paramInt1 = paramInt2;
              throw new zzxp(paramInt1 - 1, i);
            } 
          } else {
            throw new zzxp(paramInt1 - 1, i);
          } 
        } else {
          if ('?' <= c1 && c1 <= '?') {
            paramInt2 = paramInt1 + 1;
            if (paramInt2 == i || !Character.isSurrogatePair(c1, paramCharSequence.charAt(paramInt2)))
              throw new zzxp(paramInt1, i); 
          } 
          paramCharSequence = new StringBuilder(46);
          paramCharSequence.append("Failed writing ");
          paramCharSequence.append(c1);
          paramCharSequence.append(" at index ");
          paramCharSequence.append(l4);
          throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
        } 
        paramInt1++;
        l4 = l1;
      } 
      return (int)l4;
    } 
    char c = paramCharSequence.charAt(i - 1);
    paramCharSequence = new StringBuilder(37);
    paramCharSequence.append("Failed writing ");
    paramCharSequence.append(c);
    paramCharSequence.append(" at index ");
    paramCharSequence.append(paramInt1 + paramInt2);
    throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
  }
  
  final void zzb(CharSequence paramCharSequence, ByteBuffer paramByteBuffer) {
    long l2 = zzxj.zzb(paramByteBuffer);
    long l1 = paramByteBuffer.position() + l2;
    long l3 = paramByteBuffer.limit() + l2;
    int j = paramCharSequence.length();
    if (j <= l3 - l1) {
      int m = 0;
      while (m < j) {
        char c1 = paramCharSequence.charAt(m);
        if (c1 < '') {
          zzxj.zza(l1, (byte)c1);
          m++;
          l1++;
        } 
      } 
      long l4 = l2;
      long l5 = l1;
      int k = m;
      if (m == j) {
        paramByteBuffer.position((int)(l1 - l2));
        return;
      } 
      while (k < j) {
        char c1 = paramCharSequence.charAt(k);
        if (c1 < '' && l5 < l3) {
          zzxj.zza(l5, (byte)c1);
          l1 = l5 + 1L;
        } else if (c1 < 'ࠀ' && l5 <= l3 - 2L) {
          l1 = l5 + 1L;
          zzxj.zza(l5, (byte)(c1 >>> 6 | 0x3C0));
          zzxj.zza(l1, (byte)(c1 & 0x3F | 0x80));
          l1++;
        } else if ((c1 < '?' || '?' < c1) && l5 <= l3 - 3L) {
          l1 = l5 + 1L;
          zzxj.zza(l5, (byte)(c1 >>> 12 | 0x1E0));
          l5 = l1 + 1L;
          zzxj.zza(l1, (byte)(c1 >>> 6 & 0x3F | 0x80));
          zzxj.zza(l5, (byte)(c1 & 0x3F | 0x80));
          l1 = l5 + 1L;
        } else if (l5 <= l3 - 4L) {
          m = k + 1;
          if (m != j) {
            char c2 = paramCharSequence.charAt(m);
            k = m;
            if (Character.isSurrogatePair(c1, c2)) {
              k = Character.toCodePoint(c1, c2);
              l1 = l5 + 1L;
              zzxj.zza(l5, (byte)(k >>> 18 | 0xF0));
              l5 = l1 + 1L;
              zzxj.zza(l1, (byte)(k >>> 12 & 0x3F | 0x80));
              l1 = l5 + 1L;
              zzxj.zza(l5, (byte)(k >>> 6 & 0x3F | 0x80));
              zzxj.zza(l1, (byte)(k & 0x3F | 0x80));
              k = m;
              l1++;
            } else {
              throw new zzxp(k - 1, j);
            } 
          } else {
            throw new zzxp(k - 1, j);
          } 
        } else {
          if ('?' <= c1 && c1 <= '?') {
            m = k + 1;
            if (m == j || !Character.isSurrogatePair(c1, paramCharSequence.charAt(m)))
              throw new zzxp(k, j); 
          } 
          paramCharSequence = new StringBuilder(46);
          paramCharSequence.append("Failed writing ");
          paramCharSequence.append(c1);
          paramCharSequence.append(" at index ");
          paramCharSequence.append(l5);
          throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
        } 
        k++;
        l5 = l1;
      } 
      paramByteBuffer.position((int)(l5 - l4));
      return;
    } 
    char c = paramCharSequence.charAt(j - 1);
    int i = paramByteBuffer.limit();
    paramCharSequence = new StringBuilder(37);
    paramCharSequence.append("Failed writing ");
    paramCharSequence.append(c);
    paramCharSequence.append(" at index ");
    paramCharSequence.append(i);
    throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
  }
  
  final String zzh(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws zzuv {
    if ((paramInt1 | paramInt2 | paramArrayOfbyte.length - paramInt1 - paramInt2) >= 0) {
      int j = paramInt1 + paramInt2;
      char[] arrayOfChar = new char[paramInt2];
      paramInt2 = 0;
      while (paramInt1 < j) {
        byte b = zzxj.zza(paramArrayOfbyte, paramInt1);
        if (zzxm.zzh(b)) {
          paramInt1++;
          zzxm.zzb(b, arrayOfChar, paramInt2);
          paramInt2++;
        } 
      } 
      int i = paramInt2;
      paramInt2 = paramInt1;
      paramInt1 = i;
      while (paramInt2 < j) {
        i = paramInt2 + 1;
        byte b = zzxj.zza(paramArrayOfbyte, paramInt2);
        if (zzxm.zzh(b)) {
          paramInt2 = paramInt1 + 1;
          zzxm.zzb(b, arrayOfChar, paramInt1);
          paramInt1 = paramInt2;
          paramInt2 = i;
          while (paramInt2 < j) {
            b = zzxj.zza(paramArrayOfbyte, paramInt2);
            if (zzxm.zzh(b)) {
              paramInt2++;
              zzxm.zzb(b, arrayOfChar, paramInt1);
              paramInt1++;
            } 
          } 
          continue;
        } 
        if (zzxm.zzi(b)) {
          if (i < j) {
            zzxm.zzb(b, zzxj.zza(paramArrayOfbyte, i), arrayOfChar, paramInt1);
            paramInt2 = i + 1;
            paramInt1++;
            continue;
          } 
          throw zzuv.zzwx();
        } 
        if (zzxm.zzj(b)) {
          if (i < j - 1) {
            paramInt2 = i + 1;
            zzxm.zzb(b, zzxj.zza(paramArrayOfbyte, i), zzxj.zza(paramArrayOfbyte, paramInt2), arrayOfChar, paramInt1);
            paramInt2++;
            paramInt1++;
            continue;
          } 
          throw zzuv.zzwx();
        } 
        if (i < j - 2) {
          paramInt2 = i + 1;
          byte b1 = zzxj.zza(paramArrayOfbyte, i);
          i = paramInt2 + 1;
          zzxm.zzb(b, b1, zzxj.zza(paramArrayOfbyte, paramInt2), zzxj.zza(paramArrayOfbyte, i), arrayOfChar, paramInt1);
          paramInt2 = i + 1;
          paramInt1 = paramInt1 + 1 + 1;
          continue;
        } 
        throw zzuv.zzwx();
      } 
      return new String(arrayOfChar, 0, paramInt1);
    } 
    throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[] { Integer.valueOf(paramArrayOfbyte.length), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */