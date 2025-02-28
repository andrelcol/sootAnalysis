package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.measurement.zzfj;
import com.google.android.gms.internal.measurement.zzfk;
import com.google.android.gms.internal.measurement.zzfl;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzfz;
import com.google.android.gms.internal.measurement.zzya;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class zzft extends zzfm {
  zzft(zzfn paramzzfn) {
    super(paramzzfn);
  }
  
  static zzfu zza(com.google.android.gms.internal.measurement.zzft paramzzft, String paramString) {
    for (zzfu zzfu : paramzzft.zzaxc) {
      if (zzfu.name.equals(paramString))
        return zzfu; 
    } 
    return null;
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt) {
    for (byte b = 0; b < paramInt; b++)
      paramStringBuilder.append("  "); 
  }
  
  private final void zza(StringBuilder paramStringBuilder, int paramInt, zzfk paramzzfk) {
    if (paramzzfk == null)
      return; 
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("filter {\n");
    zza(paramStringBuilder, paramInt, "complement", paramzzfk.zzavu);
    zza(paramStringBuilder, paramInt, "param_name", zzgq().zzbu(paramzzfk.zzavv));
    int i = paramInt + 1;
    zzfn zzfn = paramzzfk.zzavs;
    if (zzfn != null) {
      zza(paramStringBuilder, i);
      paramStringBuilder.append("string_filter");
      paramStringBuilder.append(" {\n");
      Integer integer = zzfn.zzawe;
      if (integer != null) {
        String str;
        switch (integer.intValue()) {
          default:
            str = "UNKNOWN_MATCH_TYPE";
            break;
          case 6:
            str = "IN_LIST";
            break;
          case 5:
            str = "EXACT";
            break;
          case 4:
            str = "PARTIAL";
            break;
          case 3:
            str = "ENDS_WITH";
            break;
          case 2:
            str = "BEGINS_WITH";
            break;
          case 1:
            str = "REGEXP";
            break;
        } 
        zza(paramStringBuilder, i, "match_type", str);
      } 
      zza(paramStringBuilder, i, "expression", zzfn.zzawf);
      zza(paramStringBuilder, i, "case_sensitive", zzfn.zzawg);
      if (zzfn.zzawh.length > 0) {
        zza(paramStringBuilder, i + 1);
        paramStringBuilder.append("expression_list {\n");
        for (String str : zzfn.zzawh) {
          zza(paramStringBuilder, i + 2);
          paramStringBuilder.append(str);
          paramStringBuilder.append("\n");
        } 
        paramStringBuilder.append("}\n");
      } 
      zza(paramStringBuilder, i);
      paramStringBuilder.append("}\n");
    } 
    zza(paramStringBuilder, i, "number_filter", paramzzfk.zzavt);
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("}\n");
  }
  
  private final void zza(StringBuilder paramStringBuilder, int paramInt, String paramString, zzfl paramzzfl) {
    if (paramzzfl == null)
      return; 
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(" {\n");
    Integer integer = paramzzfl.zzavw;
    if (integer != null) {
      String str;
      int i = integer.intValue();
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              str = "UNKNOWN_COMPARISON_TYPE";
            } else {
              str = "BETWEEN";
            } 
          } else {
            str = "EQUAL";
          } 
        } else {
          str = "GREATER_THAN";
        } 
      } else {
        str = "LESS_THAN";
      } 
      zza(paramStringBuilder, paramInt, "comparison_type", str);
    } 
    zza(paramStringBuilder, paramInt, "match_as_float", paramzzfl.zzavx);
    zza(paramStringBuilder, paramInt, "comparison_value", paramzzfl.zzavy);
    zza(paramStringBuilder, paramInt, "min_comparison_value", paramzzfl.zzavz);
    zza(paramStringBuilder, paramInt, "max_comparison_value", paramzzfl.zzawa);
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("}\n");
  }
  
  private final void zza(StringBuilder paramStringBuilder, int paramInt, String paramString1, zzfx paramzzfx, String paramString2) {
    if (paramzzfx == null)
      return; 
    zza(paramStringBuilder, 3);
    paramStringBuilder.append(paramString1);
    paramStringBuilder.append(" {\n");
    if (paramzzfx.zzayq != null) {
      zza(paramStringBuilder, 4);
      paramStringBuilder.append("results: ");
      long[] arrayOfLong = paramzzfx.zzayq;
      int i = arrayOfLong.length;
      byte b = 0;
      for (paramInt = 0; b < i; paramInt++) {
        long l = arrayOfLong[b];
        if (paramInt != 0)
          paramStringBuilder.append(", "); 
        paramStringBuilder.append(Long.valueOf(l));
        b++;
      } 
      paramStringBuilder.append('\n');
    } 
    if (paramzzfx.zzayp != null) {
      zza(paramStringBuilder, 4);
      paramStringBuilder.append("status: ");
      long[] arrayOfLong = paramzzfx.zzayp;
      int i = arrayOfLong.length;
      byte b = 0;
      for (paramInt = 0; b < i; paramInt++) {
        long l = arrayOfLong[b];
        if (paramInt != 0)
          paramStringBuilder.append(", "); 
        paramStringBuilder.append(Long.valueOf(l));
        b++;
      } 
      paramStringBuilder.append('\n');
    } 
    if (zzgv().zzbb(paramString2)) {
      if (paramzzfx.zzayr != null) {
        zza(paramStringBuilder, 4);
        paramStringBuilder.append("dynamic_filter_timestamps: {");
        zzfs[] arrayOfZzfs = paramzzfx.zzayr;
        int i = arrayOfZzfs.length;
        byte b = 0;
        for (paramInt = 0; b < i; paramInt++) {
          zzfs zzfs = arrayOfZzfs[b];
          if (paramInt != 0)
            paramStringBuilder.append(", "); 
          paramStringBuilder.append(zzfs.zzawz);
          paramStringBuilder.append(":");
          paramStringBuilder.append(zzfs.zzaxa);
          b++;
        } 
        paramStringBuilder.append("}\n");
      } 
      if (paramzzfx.zzays != null) {
        zza(paramStringBuilder, 4);
        paramStringBuilder.append("sequence_filter_timestamps: {");
        zzfy[] arrayOfZzfy = paramzzfx.zzays;
        int i = arrayOfZzfy.length;
        byte b = 0;
        for (paramInt = 0; b < i; paramInt++) {
          zzfy zzfy = arrayOfZzfy[b];
          if (paramInt != 0)
            paramStringBuilder.append(", "); 
          paramStringBuilder.append(zzfy.zzawz);
          paramStringBuilder.append(": [");
          long[] arrayOfLong = zzfy.zzayu;
          int j = arrayOfLong.length;
          byte b2 = 0;
          for (byte b1 = 0; b2 < j; b1++) {
            long l = arrayOfLong[b2];
            if (b1)
              paramStringBuilder.append(", "); 
            paramStringBuilder.append(l);
            b2++;
          } 
          paramStringBuilder.append("]");
          b++;
        } 
        paramStringBuilder.append("}\n");
      } 
    } 
    zza(paramStringBuilder, 3);
    paramStringBuilder.append("}\n");
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, String paramString, Object paramObject) {
    if (paramObject == null)
      return; 
    zza(paramStringBuilder, paramInt + 1);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(": ");
    paramStringBuilder.append(paramObject);
    paramStringBuilder.append('\n');
  }
  
  static boolean zza(long[] paramArrayOflong, int paramInt) {
    return (paramInt >= paramArrayOflong.length << 6) ? false : (((1L << paramInt % 64 & paramArrayOflong[paramInt / 64]) != 0L));
  }
  
  static long[] zza(BitSet paramBitSet) {
    int i = (paramBitSet.length() + 63) / 64;
    long[] arrayOfLong = new long[i];
    for (byte b = 0; b < i; b++) {
      arrayOfLong[b] = 0L;
      byte b1 = 0;
      while (b1 < 64) {
        int j = (b << 6) + b1;
        if (j < paramBitSet.length()) {
          if (paramBitSet.get(j))
            arrayOfLong[b] = arrayOfLong[b] | 1L << b1; 
          b1++;
        } 
      } 
    } 
    return arrayOfLong;
  }
  
  static zzfu[] zza(zzfu[] paramArrayOfzzfu, String paramString, Object paramObject) {
    int i = paramArrayOfzzfu.length;
    for (byte b = 0; b < i; b++) {
      zzfu zzfu2 = paramArrayOfzzfu[b];
      if (paramString.equals(zzfu2.name)) {
        zzfu2.zzaxg = null;
        zzfu2.zzamn = null;
        zzfu2.zzaup = null;
        if (paramObject instanceof Long) {
          zzfu2.zzaxg = (Long)paramObject;
        } else if (paramObject instanceof String) {
          zzfu2.zzamn = (String)paramObject;
        } else if (paramObject instanceof Double) {
          zzfu2.zzaup = (Double)paramObject;
        } 
        return paramArrayOfzzfu;
      } 
    } 
    zzfu[] arrayOfZzfu = new zzfu[paramArrayOfzzfu.length + 1];
    System.arraycopy(paramArrayOfzzfu, 0, arrayOfZzfu, 0, paramArrayOfzzfu.length);
    zzfu zzfu1 = new zzfu();
    zzfu1.name = paramString;
    if (paramObject instanceof Long) {
      zzfu1.zzaxg = (Long)paramObject;
    } else if (paramObject instanceof String) {
      zzfu1.zzamn = (String)paramObject;
    } else if (paramObject instanceof Double) {
      zzfu1.zzaup = (Double)paramObject;
    } 
    arrayOfZzfu[paramArrayOfzzfu.length] = zzfu1;
    return arrayOfZzfu;
  }
  
  static Object zzb(com.google.android.gms.internal.measurement.zzft paramzzft, String paramString) {
    zzfu zzfu = zza(paramzzft, paramString);
    if (zzfu != null) {
      paramString = zzfu.zzamn;
      if (paramString != null)
        return paramString; 
      Long long_ = zzfu.zzaxg;
      if (long_ != null)
        return long_; 
      Double double_ = zzfu.zzaup;
      if (double_ != null)
        return double_; 
    } 
    return null;
  }
  
  static boolean zzcs(String paramString) {
    return (paramString != null && paramString.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && paramString.length() <= 310);
  }
  
  final <T extends Parcelable> T zza(byte[] paramArrayOfbyte, Parcelable.Creator<T> paramCreator) {
    if (paramArrayOfbyte == null)
      return null; 
    Parcel parcel = Parcel.obtain();
    try {
      parcel.unmarshall(paramArrayOfbyte, 0, paramArrayOfbyte.length);
      parcel.setDataPosition(0);
      Parcelable parcelable = (Parcelable)paramCreator.createFromParcel(parcel);
      parcel.recycle();
      return (T)parcelable;
    } catch (com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException parseException) {
      zzgt().zzjg().zzby("Failed to load parcelable from buffer");
      parcel.recycle();
      return null;
    } finally {}
    parcel.recycle();
    throw paramArrayOfbyte;
  }
  
  final String zza(zzfj paramzzfj) {
    if (paramzzfj == null)
      return "null"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\nevent_filter {\n");
    Integer integer = paramzzfj.zzavm;
    byte b = 0;
    zza(stringBuilder, 0, "filter_id", integer);
    zza(stringBuilder, 0, "event_name", zzgq().zzbt(paramzzfj.zzavn));
    zza(stringBuilder, 1, "event_count_filter", paramzzfj.zzavq);
    stringBuilder.append("  filters {\n");
    zzfk[] arrayOfZzfk = paramzzfj.zzavo;
    int i = arrayOfZzfk.length;
    while (b < i) {
      zza(stringBuilder, 2, arrayOfZzfk[b]);
      b++;
    } 
    zza(stringBuilder, 1);
    stringBuilder.append("}\n}\n");
    return stringBuilder.toString();
  }
  
  final String zza(zzfm paramzzfm) {
    if (paramzzfm == null)
      return "null"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\nproperty_filter {\n");
    zza(stringBuilder, 0, "filter_id", paramzzfm.zzavm);
    zza(stringBuilder, 0, "property_name", zzgq().zzbv(paramzzfm.zzawc));
    zza(stringBuilder, 1, paramzzfm.zzawd);
    stringBuilder.append("}\n");
    return stringBuilder.toString();
  }
  
  final void zza(zzfu paramzzfu, Object paramObject) {
    Preconditions.checkNotNull(paramObject);
    paramzzfu.zzamn = null;
    paramzzfu.zzaxg = null;
    paramzzfu.zzaup = null;
    if (paramObject instanceof String) {
      paramzzfu.zzamn = (String)paramObject;
      return;
    } 
    if (paramObject instanceof Long) {
      paramzzfu.zzaxg = (Long)paramObject;
      return;
    } 
    if (paramObject instanceof Double) {
      paramzzfu.zzaup = (Double)paramObject;
      return;
    } 
    zzgt().zzjg().zzg("Ignoring invalid (type) event param value", paramObject);
  }
  
  final void zza(zzfz paramzzfz, Object paramObject) {
    Preconditions.checkNotNull(paramObject);
    paramzzfz.zzamn = null;
    paramzzfz.zzaxg = null;
    paramzzfz.zzaup = null;
    if (paramObject instanceof String) {
      paramzzfz.zzamn = (String)paramObject;
      return;
    } 
    if (paramObject instanceof Long) {
      paramzzfz.zzaxg = (Long)paramObject;
      return;
    } 
    if (paramObject instanceof Double) {
      paramzzfz.zzaup = (Double)paramObject;
      return;
    } 
    zzgt().zzjg().zzg("Ignoring invalid (type) user attribute value", paramObject);
  }
  
  final byte[] zza(zzfv paramzzfv) {
    try {
      byte[] arrayOfByte = new byte[paramzzfv.zzvx()];
      zzya zzya = zzya.zzk(arrayOfByte, 0, arrayOfByte.length);
      paramzzfv.zza(zzya);
      zzya.zzza();
      return arrayOfByte;
    } catch (IOException iOException) {
      zzgt().zzjg().zzg("Data loss. Failed to serialize batch", iOException);
      return null;
    } 
  }
  
  final byte[] zza(byte[] paramArrayOfbyte) throws IOException {
    try {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
      this(paramArrayOfbyte);
      GZIPInputStream gZIPInputStream = new GZIPInputStream();
      this(byteArrayInputStream);
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      byte[] arrayOfByte = new byte[1024];
      while (true) {
        int i = gZIPInputStream.read(arrayOfByte);
        if (i > 0) {
          byteArrayOutputStream.write(arrayOfByte, 0, i);
          continue;
        } 
        gZIPInputStream.close();
        byteArrayInputStream.close();
        return byteArrayOutputStream.toByteArray();
      } 
    } catch (IOException iOException) {
      zzgt().zzjg().zzg("Failed to ungzip content", iOException);
      throw iOException;
    } 
  }
  
  final String zzb(zzfv paramzzfv) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\nbatch {\n");
    zzfw[] arrayOfZzfw = paramzzfv.zzaxh;
    if (arrayOfZzfw != null) {
      int i = arrayOfZzfw.length;
      for (byte b = 0; b < i; b++) {
        zzfw zzfw = arrayOfZzfw[b];
        if (zzfw != null && zzfw != null) {
          zzfr zzfr2;
          zza(stringBuilder, 1);
          stringBuilder.append("bundle {\n");
          zza(stringBuilder, 1, "protocol_version", zzfw.zzaxj);
          zza(stringBuilder, 1, "platform", zzfw.zzaxr);
          zza(stringBuilder, 1, "gmp_version", zzfw.zzaxv);
          zza(stringBuilder, 1, "uploading_gmp_version", zzfw.zzaxw);
          zza(stringBuilder, 1, "config_version", zzfw.zzayh);
          zza(stringBuilder, 1, "gmp_app_id", zzfw.zzafi);
          zza(stringBuilder, 1, "admob_app_id", zzfw.zzawr);
          zza(stringBuilder, 1, "app_id", zzfw.zztt);
          zza(stringBuilder, 1, "app_version", zzfw.zzts);
          zza(stringBuilder, 1, "app_version_major", zzfw.zzayd);
          zza(stringBuilder, 1, "firebase_instance_id", zzfw.zzafk);
          zza(stringBuilder, 1, "dev_cert_hash", zzfw.zzaxz);
          zza(stringBuilder, 1, "app_store", zzfw.zzafp);
          zza(stringBuilder, 1, "upload_timestamp_millis", zzfw.zzaxm);
          zza(stringBuilder, 1, "start_timestamp_millis", zzfw.zzaxn);
          zza(stringBuilder, 1, "end_timestamp_millis", zzfw.zzaxo);
          zza(stringBuilder, 1, "previous_bundle_start_timestamp_millis", zzfw.zzaxp);
          zza(stringBuilder, 1, "previous_bundle_end_timestamp_millis", zzfw.zzaxq);
          zza(stringBuilder, 1, "app_instance_id", zzfw.zzafh);
          zza(stringBuilder, 1, "resettable_device_id", zzfw.zzaxx);
          zza(stringBuilder, 1, "device_id", zzfw.zzayg);
          zza(stringBuilder, 1, "ds_id", zzfw.zzayj);
          zza(stringBuilder, 1, "limited_ad_tracking", zzfw.zzaxy);
          zza(stringBuilder, 1, "os_version", zzfw.zzaxs);
          zza(stringBuilder, 1, "device_model", zzfw.zzaxt);
          zza(stringBuilder, 1, "user_default_language", zzfw.zzahr);
          zza(stringBuilder, 1, "time_zone_offset_minutes", zzfw.zzaxu);
          zza(stringBuilder, 1, "bundle_sequential_index", zzfw.zzaya);
          zza(stringBuilder, 1, "service_upload", zzfw.zzayb);
          zza(stringBuilder, 1, "health_monitor", zzfw.zzagm);
          Long long_ = zzfw.zzayi;
          if (long_ != null && long_.longValue() != 0L)
            zza(stringBuilder, 1, "android_id", zzfw.zzayi); 
          Integer integer = zzfw.zzayl;
          if (integer != null)
            zza(stringBuilder, 1, "retry_counter", integer); 
          zzfz[] arrayOfZzfz = zzfw.zzaxl;
          String str2 = "string_value";
          String str1 = "name";
          if (arrayOfZzfz != null) {
            int j = arrayOfZzfz.length;
            for (byte b2 = 0; b2 < j; b2++) {
              zzfz zzfz = arrayOfZzfz[b2];
              if (zzfz != null) {
                zza(stringBuilder, 2);
                stringBuilder.append("user_property {\n");
                zza(stringBuilder, 2, "set_timestamp_millis", zzfz.zzayw);
                zza(stringBuilder, 2, "name", zzgq().zzbv(zzfz.name));
                zza(stringBuilder, 2, "string_value", zzfz.zzamn);
                zza(stringBuilder, 2, "int_value", zzfz.zzaxg);
                zza(stringBuilder, 2, "double_value", zzfz.zzaup);
                zza(stringBuilder, 2);
                stringBuilder.append("}\n");
              } 
            } 
          } 
          zzfr[] arrayOfZzfr = zzfw.zzayc;
          String str5 = zzfw.zztt;
          String str3 = str1;
          String str4 = str2;
          zzfw[] arrayOfZzfw1 = arrayOfZzfw;
          if (arrayOfZzfr != null) {
            int j = arrayOfZzfr.length;
            byte b2 = 0;
            while (true) {
              str3 = str1;
              str4 = str2;
              arrayOfZzfw1 = arrayOfZzfw;
              if (b2 < j) {
                zzfr2 = arrayOfZzfr[b2];
                if (zzfr2 != null) {
                  zza(stringBuilder, 2);
                  stringBuilder.append("audience_membership {\n");
                  zza(stringBuilder, 2, "audience_id", zzfr2.zzavg);
                  zza(stringBuilder, 2, "new_audience", zzfr2.zzawx);
                  zza(stringBuilder, 2, "current_data", zzfr2.zzawv, str5);
                  zza(stringBuilder, 2, "previous_data", zzfr2.zzaww, str5);
                  zza(stringBuilder, 2);
                  stringBuilder.append("}\n");
                } 
                b2++;
                continue;
              } 
              break;
            } 
          } 
          str2 = str3;
          zzfr zzfr3 = zzfr2;
          byte b1 = 2;
          com.google.android.gms.internal.measurement.zzft[] arrayOfZzft = zzfw.zzaxk;
          if (arrayOfZzft != null) {
            int j = arrayOfZzft.length;
            for (byte b2 = 0; b2 < j; b2++) {
              com.google.android.gms.internal.measurement.zzft zzft1 = arrayOfZzft[b2];
              if (zzft1 != null) {
                zzfu zzfu2;
                zza(stringBuilder, b1);
                stringBuilder.append("event {\n");
                String str = zzgq().zzbt(zzft1.name);
                zza(stringBuilder, b1, str2, str);
                zza(stringBuilder, b1, "timestamp_millis", zzft1.zzaxd);
                zza(stringBuilder, b1, "previous_timestamp_millis", zzft1.zzaxe);
                zza(stringBuilder, b1, "count", zzft1.count);
                zzfu[] arrayOfZzfu = zzft1.zzaxc;
                byte b3 = b1;
                com.google.android.gms.internal.measurement.zzft[] arrayOfZzft1 = arrayOfZzft;
                if (arrayOfZzfu != null) {
                  int k = arrayOfZzfu.length;
                  byte b4 = 0;
                  while (true) {
                    b3 = b1;
                    arrayOfZzft1 = arrayOfZzft;
                    if (b4 < k) {
                      zzfu2 = arrayOfZzfu[b4];
                      if (zzfu2 != null) {
                        zza(stringBuilder, 3);
                        stringBuilder.append("param {\n");
                        zza(stringBuilder, 3, str2, zzgq().zzbu(zzfu2.name));
                        zza(stringBuilder, 3, str4, zzfu2.zzamn);
                        zza(stringBuilder, 3, "int_value", zzfu2.zzaxg);
                        zza(stringBuilder, 3, "double_value", zzfu2.zzaup);
                        zza(stringBuilder, 3);
                        stringBuilder.append("}\n");
                      } 
                      b4++;
                      b1 = 2;
                      continue;
                    } 
                    break;
                  } 
                } 
                zzfu zzfu1 = zzfu2;
                zza(stringBuilder, b3);
                stringBuilder.append("}\n");
                b1 = b3;
              } 
            } 
          } 
          zza(stringBuilder, 1);
          stringBuilder.append("}\n");
          zzfr zzfr1 = zzfr3;
        } 
      } 
    } 
    stringBuilder.append("}\n");
    return stringBuilder.toString();
  }
  
  final boolean zzb(long paramLong1, long paramLong2) {
    return (paramLong1 == 0L || paramLong2 <= 0L) ? true : ((Math.abs(zzbx().currentTimeMillis() - paramLong1) > paramLong2));
  }
  
  final byte[] zzb(byte[] paramArrayOfbyte) throws IOException {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      GZIPOutputStream gZIPOutputStream = new GZIPOutputStream();
      this(byteArrayOutputStream);
      gZIPOutputStream.write(paramArrayOfbyte);
      gZIPOutputStream.close();
      byteArrayOutputStream.close();
      return byteArrayOutputStream.toByteArray();
    } catch (IOException iOException) {
      zzgt().zzjg().zzg("Failed to gzip content", iOException);
      throw iOException;
    } 
  }
  
  final boolean zze(zzag paramzzag, zzk paramzzk) {
    Preconditions.checkNotNull(paramzzag);
    Preconditions.checkNotNull(paramzzk);
    if (TextUtils.isEmpty(paramzzk.zzafi) && TextUtils.isEmpty(paramzzk.zzafv)) {
      zzgw();
      return false;
    } 
    return true;
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  final int[] zzmi() {
    Map<String, String> map = zzai.zzm(this.zzamx.getContext());
    if (map == null || map.size() == 0)
      return null; 
    ArrayList<Integer> arrayList = new ArrayList();
    int i = ((Integer)zzai.zzakg.get()).intValue();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      if (((String)entry.getKey()).startsWith("measurement.id."))
        try {
          int k = Integer.parseInt((String)entry.getValue());
          if (k != 0) {
            arrayList.add(Integer.valueOf(k));
            if (arrayList.size() >= i) {
              zzgt().zzjj().zzg("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
              break;
            } 
          } 
        } catch (NumberFormatException numberFormatException) {
          zzgt().zzjj().zzg("Experiment ID NumberFormatException", numberFormatException);
        }  
    } 
    if (arrayList.size() == 0)
      return null; 
    int[] arrayOfInt = new int[arrayList.size()];
    int j = arrayList.size();
    i = 0;
    for (byte b = 0; i < j; b++) {
      map = (Map<String, String>)arrayList.get(i);
      i++;
      arrayOfInt[b] = ((Integer)map).intValue();
    } 
    return arrayOfInt;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */