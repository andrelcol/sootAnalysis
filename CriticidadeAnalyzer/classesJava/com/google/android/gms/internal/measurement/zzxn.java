package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

abstract class zzxn {
  static void zzc(CharSequence paramCharSequence, ByteBuffer paramByteBuffer) {
    // Byte code:
    //   0: aload_0
    //   1: invokeinterface length : ()I
    //   6: istore #10
    //   8: aload_1
    //   9: invokevirtual position : ()I
    //   12: istore #6
    //   14: iconst_0
    //   15: istore #5
    //   17: iload #5
    //   19: iload #10
    //   21: if_icmpge -> 77
    //   24: iload #6
    //   26: istore #8
    //   28: iload #5
    //   30: istore #7
    //   32: aload_0
    //   33: iload #5
    //   35: invokeinterface charAt : (I)C
    //   40: istore #9
    //   42: iload #9
    //   44: sipush #128
    //   47: if_icmpge -> 77
    //   50: iload #6
    //   52: istore #8
    //   54: iload #5
    //   56: istore #7
    //   58: aload_1
    //   59: iload #6
    //   61: iload #5
    //   63: iadd
    //   64: iload #9
    //   66: i2b
    //   67: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   70: pop
    //   71: iinc #5, 1
    //   74: goto -> 17
    //   77: iload #5
    //   79: iload #10
    //   81: if_icmpne -> 103
    //   84: iload #6
    //   86: istore #8
    //   88: iload #5
    //   90: istore #7
    //   92: aload_1
    //   93: iload #6
    //   95: iload #5
    //   97: iadd
    //   98: invokevirtual position : (I)Ljava/nio/Buffer;
    //   101: pop
    //   102: return
    //   103: iload #6
    //   105: iload #5
    //   107: iadd
    //   108: istore #6
    //   110: iload #5
    //   112: iload #10
    //   114: if_icmpge -> 590
    //   117: iload #6
    //   119: istore #8
    //   121: iload #5
    //   123: istore #7
    //   125: aload_0
    //   126: iload #5
    //   128: invokeinterface charAt : (I)C
    //   133: istore #4
    //   135: iload #4
    //   137: sipush #128
    //   140: if_icmpge -> 164
    //   143: iload #6
    //   145: istore #8
    //   147: iload #5
    //   149: istore #7
    //   151: aload_1
    //   152: iload #6
    //   154: iload #4
    //   156: i2b
    //   157: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   160: pop
    //   161: goto -> 581
    //   164: iload #4
    //   166: sipush #2048
    //   169: if_icmpge -> 238
    //   172: iload #6
    //   174: iconst_1
    //   175: iadd
    //   176: istore #8
    //   178: iload #4
    //   180: bipush #6
    //   182: iushr
    //   183: sipush #192
    //   186: ior
    //   187: i2b
    //   188: istore_2
    //   189: iload #8
    //   191: istore #7
    //   193: aload_1
    //   194: iload #6
    //   196: iload_2
    //   197: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   200: pop
    //   201: iload #8
    //   203: istore #7
    //   205: aload_1
    //   206: iload #8
    //   208: iload #4
    //   210: bipush #63
    //   212: iand
    //   213: sipush #128
    //   216: ior
    //   217: i2b
    //   218: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   221: pop
    //   222: iload #8
    //   224: istore #6
    //   226: goto -> 581
    //   229: astore #12
    //   231: iload #7
    //   233: istore #8
    //   235: goto -> 606
    //   238: iload #4
    //   240: ldc 55296
    //   242: if_icmplt -> 491
    //   245: ldc 57343
    //   247: iload #4
    //   249: if_icmpge -> 255
    //   252: goto -> 491
    //   255: iload #5
    //   257: iconst_1
    //   258: iadd
    //   259: istore #7
    //   261: iload #7
    //   263: iload #10
    //   265: if_icmpeq -> 450
    //   268: iload #6
    //   270: istore #5
    //   272: aload_0
    //   273: iload #7
    //   275: invokeinterface charAt : (I)C
    //   280: istore_3
    //   281: iload #6
    //   283: istore #5
    //   285: iload #4
    //   287: iload_3
    //   288: invokestatic isSurrogatePair : (CC)Z
    //   291: ifeq -> 432
    //   294: iload #6
    //   296: istore #5
    //   298: iload #4
    //   300: iload_3
    //   301: invokestatic toCodePoint : (CC)I
    //   304: istore #11
    //   306: iload #6
    //   308: iconst_1
    //   309: iadd
    //   310: istore #8
    //   312: iload #11
    //   314: bipush #18
    //   316: iushr
    //   317: sipush #240
    //   320: ior
    //   321: i2b
    //   322: istore_2
    //   323: iload #8
    //   325: istore #5
    //   327: aload_1
    //   328: iload #6
    //   330: iload_2
    //   331: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   334: pop
    //   335: iload #8
    //   337: iconst_1
    //   338: iadd
    //   339: istore #9
    //   341: iload #11
    //   343: bipush #12
    //   345: iushr
    //   346: bipush #63
    //   348: iand
    //   349: sipush #128
    //   352: ior
    //   353: i2b
    //   354: istore_2
    //   355: iload #9
    //   357: istore #5
    //   359: aload_1
    //   360: iload #8
    //   362: iload_2
    //   363: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   366: pop
    //   367: iload #9
    //   369: iconst_1
    //   370: iadd
    //   371: istore #6
    //   373: iload #11
    //   375: bipush #6
    //   377: iushr
    //   378: bipush #63
    //   380: iand
    //   381: sipush #128
    //   384: ior
    //   385: i2b
    //   386: istore_2
    //   387: iload #6
    //   389: istore #5
    //   391: aload_1
    //   392: iload #9
    //   394: iload_2
    //   395: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   398: pop
    //   399: iload #6
    //   401: istore #5
    //   403: aload_1
    //   404: iload #6
    //   406: iload #11
    //   408: bipush #63
    //   410: iand
    //   411: sipush #128
    //   414: ior
    //   415: i2b
    //   416: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   419: pop
    //   420: iload #7
    //   422: istore #5
    //   424: goto -> 581
    //   427: astore #12
    //   429: goto -> 439
    //   432: iload #7
    //   434: istore #5
    //   436: goto -> 450
    //   439: iload #5
    //   441: istore #8
    //   443: iload #7
    //   445: istore #5
    //   447: goto -> 606
    //   450: iload #6
    //   452: istore #8
    //   454: iload #5
    //   456: istore #7
    //   458: new com/google/android/gms/internal/measurement/zzxp
    //   461: astore #12
    //   463: iload #6
    //   465: istore #8
    //   467: iload #5
    //   469: istore #7
    //   471: aload #12
    //   473: iload #5
    //   475: iload #10
    //   477: invokespecial <init> : (II)V
    //   480: iload #6
    //   482: istore #8
    //   484: iload #5
    //   486: istore #7
    //   488: aload #12
    //   490: athrow
    //   491: iload #6
    //   493: iconst_1
    //   494: iadd
    //   495: istore #9
    //   497: iload #4
    //   499: bipush #12
    //   501: iushr
    //   502: sipush #224
    //   505: ior
    //   506: i2b
    //   507: istore_2
    //   508: iload #9
    //   510: istore #7
    //   512: aload_1
    //   513: iload #6
    //   515: iload_2
    //   516: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   519: pop
    //   520: iload #9
    //   522: iconst_1
    //   523: iadd
    //   524: istore #6
    //   526: iload #4
    //   528: bipush #6
    //   530: iushr
    //   531: bipush #63
    //   533: iand
    //   534: sipush #128
    //   537: ior
    //   538: i2b
    //   539: istore_2
    //   540: iload #6
    //   542: istore #8
    //   544: iload #5
    //   546: istore #7
    //   548: aload_1
    //   549: iload #9
    //   551: iload_2
    //   552: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   555: pop
    //   556: iload #6
    //   558: istore #8
    //   560: iload #5
    //   562: istore #7
    //   564: aload_1
    //   565: iload #6
    //   567: iload #4
    //   569: bipush #63
    //   571: iand
    //   572: sipush #128
    //   575: ior
    //   576: i2b
    //   577: invokevirtual put : (IB)Ljava/nio/ByteBuffer;
    //   580: pop
    //   581: iinc #5, 1
    //   584: iinc #6, 1
    //   587: goto -> 110
    //   590: iload #6
    //   592: istore #8
    //   594: iload #5
    //   596: istore #7
    //   598: aload_1
    //   599: iload #6
    //   601: invokevirtual position : (I)Ljava/nio/Buffer;
    //   604: pop
    //   605: return
    //   606: aload_1
    //   607: invokevirtual position : ()I
    //   610: istore #6
    //   612: iload #5
    //   614: iload #8
    //   616: aload_1
    //   617: invokevirtual position : ()I
    //   620: isub
    //   621: iconst_1
    //   622: iadd
    //   623: invokestatic max : (II)I
    //   626: istore #7
    //   628: aload_0
    //   629: iload #5
    //   631: invokeinterface charAt : (I)C
    //   636: istore_3
    //   637: new java/lang/StringBuilder
    //   640: dup
    //   641: bipush #37
    //   643: invokespecial <init> : (I)V
    //   646: astore_0
    //   647: aload_0
    //   648: ldc 'Failed writing '
    //   650: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   653: pop
    //   654: aload_0
    //   655: iload_3
    //   656: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   659: pop
    //   660: aload_0
    //   661: ldc ' at index '
    //   663: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   666: pop
    //   667: aload_0
    //   668: iload #6
    //   670: iload #7
    //   672: iadd
    //   673: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   676: pop
    //   677: new java/lang/ArrayIndexOutOfBoundsException
    //   680: dup
    //   681: aload_0
    //   682: invokevirtual toString : ()Ljava/lang/String;
    //   685: invokespecial <init> : (Ljava/lang/String;)V
    //   688: athrow
    //   689: astore #12
    //   691: iload #7
    //   693: istore #5
    //   695: goto -> 606
    //   698: astore #12
    //   700: goto -> 439
    // Exception table:
    //   from	to	target	type
    //   32	42	689	java/lang/IndexOutOfBoundsException
    //   58	71	689	java/lang/IndexOutOfBoundsException
    //   92	102	689	java/lang/IndexOutOfBoundsException
    //   125	135	689	java/lang/IndexOutOfBoundsException
    //   151	161	689	java/lang/IndexOutOfBoundsException
    //   193	201	229	java/lang/IndexOutOfBoundsException
    //   205	222	229	java/lang/IndexOutOfBoundsException
    //   272	281	698	java/lang/IndexOutOfBoundsException
    //   285	294	698	java/lang/IndexOutOfBoundsException
    //   298	306	698	java/lang/IndexOutOfBoundsException
    //   327	335	427	java/lang/IndexOutOfBoundsException
    //   359	367	698	java/lang/IndexOutOfBoundsException
    //   391	399	427	java/lang/IndexOutOfBoundsException
    //   403	420	427	java/lang/IndexOutOfBoundsException
    //   458	463	689	java/lang/IndexOutOfBoundsException
    //   471	480	689	java/lang/IndexOutOfBoundsException
    //   488	491	689	java/lang/IndexOutOfBoundsException
    //   512	520	229	java/lang/IndexOutOfBoundsException
    //   548	556	689	java/lang/IndexOutOfBoundsException
    //   564	581	689	java/lang/IndexOutOfBoundsException
    //   598	605	689	java/lang/IndexOutOfBoundsException
  }
  
  abstract int zzb(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3);
  
  abstract int zzb(CharSequence paramCharSequence, byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  abstract void zzb(CharSequence paramCharSequence, ByteBuffer paramByteBuffer);
  
  final boolean zzf(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return (zzb(0, paramArrayOfbyte, paramInt1, paramInt2) == 0);
  }
  
  abstract String zzh(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws zzuv;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */