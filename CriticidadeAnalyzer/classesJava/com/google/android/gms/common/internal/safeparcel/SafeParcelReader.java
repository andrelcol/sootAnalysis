package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class SafeParcelReader {
  public static Bundle createBundle(Parcel paramParcel, int paramInt) {
    paramInt = readSize(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0)
      return null; 
    Bundle bundle = paramParcel.readBundle();
    paramParcel.setDataPosition(i + paramInt);
    return bundle;
  }
  
  public static <T extends Parcelable> T createParcelable(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator) {
    paramInt = readSize(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0)
      return null; 
    Parcelable parcelable = (Parcelable)paramCreator.createFromParcel(paramParcel);
    paramParcel.setDataPosition(i + paramInt);
    return (T)parcelable;
  }
  
  public static String createString(Parcel paramParcel, int paramInt) {
    int i = readSize(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    if (i == 0)
      return null; 
    String str = paramParcel.readString();
    paramParcel.setDataPosition(paramInt + i);
    return str;
  }
  
  public static <T> T[] createTypedArray(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator) {
    int i = readSize(paramParcel, paramInt);
    paramInt = paramParcel.dataPosition();
    if (i == 0)
      return null; 
    Object[] arrayOfObject = paramParcel.createTypedArray(paramCreator);
    paramParcel.setDataPosition(paramInt + i);
    return (T[])arrayOfObject;
  }
  
  public static <T> ArrayList<T> createTypedList(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator) {
    paramInt = readSize(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0)
      return null; 
    ArrayList<T> arrayList = paramParcel.createTypedArrayList(paramCreator);
    paramParcel.setDataPosition(i + paramInt);
    return arrayList;
  }
  
  public static void ensureAtEnd(Parcel paramParcel, int paramInt) {
    if (paramParcel.dataPosition() == paramInt)
      return; 
    StringBuilder stringBuilder = new StringBuilder(37);
    stringBuilder.append("Overread allowed size end=");
    stringBuilder.append(paramInt);
    throw new ParseException(stringBuilder.toString(), paramParcel);
  }
  
  public static int getFieldId(int paramInt) {
    return paramInt & 0xFFFF;
  }
  
  public static boolean readBoolean(Parcel paramParcel, int paramInt) {
    zza(paramParcel, paramInt, 4);
    return (paramParcel.readInt() != 0);
  }
  
  public static byte readByte(Parcel paramParcel, int paramInt) {
    zza(paramParcel, paramInt, 4);
    return (byte)paramParcel.readInt();
  }
  
  public static double readDouble(Parcel paramParcel, int paramInt) {
    zza(paramParcel, paramInt, 8);
    return paramParcel.readDouble();
  }
  
  public static Double readDoubleObject(Parcel paramParcel, int paramInt) {
    int i = readSize(paramParcel, paramInt);
    if (i == 0)
      return null; 
    zza(paramParcel, paramInt, i, 8);
    return Double.valueOf(paramParcel.readDouble());
  }
  
  public static float readFloat(Parcel paramParcel, int paramInt) {
    zza(paramParcel, paramInt, 4);
    return paramParcel.readFloat();
  }
  
  public static Float readFloatObject(Parcel paramParcel, int paramInt) {
    int i = readSize(paramParcel, paramInt);
    if (i == 0)
      return null; 
    zza(paramParcel, paramInt, i, 4);
    return Float.valueOf(paramParcel.readFloat());
  }
  
  public static int readHeader(Parcel paramParcel) {
    return paramParcel.readInt();
  }
  
  public static IBinder readIBinder(Parcel paramParcel, int paramInt) {
    paramInt = readSize(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0)
      return null; 
    IBinder iBinder = paramParcel.readStrongBinder();
    paramParcel.setDataPosition(i + paramInt);
    return iBinder;
  }
  
  public static int readInt(Parcel paramParcel, int paramInt) {
    zza(paramParcel, paramInt, 4);
    return paramParcel.readInt();
  }
  
  public static long readLong(Parcel paramParcel, int paramInt) {
    zza(paramParcel, paramInt, 8);
    return paramParcel.readLong();
  }
  
  public static Long readLongObject(Parcel paramParcel, int paramInt) {
    int i = readSize(paramParcel, paramInt);
    if (i == 0)
      return null; 
    zza(paramParcel, paramInt, i, 8);
    return Long.valueOf(paramParcel.readLong());
  }
  
  public static int readSize(Parcel paramParcel, int paramInt) {
    return ((paramInt & 0xFFFF0000) != -65536) ? (paramInt >> 16 & 0xFFFF) : paramParcel.readInt();
  }
  
  public static void skipUnknownField(Parcel paramParcel, int paramInt) {
    paramInt = readSize(paramParcel, paramInt);
    paramParcel.setDataPosition(paramParcel.dataPosition() + paramInt);
  }
  
  public static int validateObjectHeader(Parcel paramParcel) {
    int k = readHeader(paramParcel);
    int j = readSize(paramParcel, k);
    int i = paramParcel.dataPosition();
    if (getFieldId(k) != 20293) {
      String str = String.valueOf(Integer.toHexString(k));
      if (str.length() != 0) {
        str = "Expected object header. Got 0x".concat(str);
      } else {
        str = new String("Expected object header. Got 0x");
      } 
      throw new ParseException(str, paramParcel);
    } 
    j += i;
    if (j >= i && j <= paramParcel.dataSize())
      return j; 
    StringBuilder stringBuilder = new StringBuilder(54);
    stringBuilder.append("Size read is invalid start=");
    stringBuilder.append(i);
    stringBuilder.append(" end=");
    stringBuilder.append(j);
    throw new ParseException(stringBuilder.toString(), paramParcel);
  }
  
  private static void zza(Parcel paramParcel, int paramInt1, int paramInt2) {
    paramInt1 = readSize(paramParcel, paramInt1);
    if (paramInt1 == paramInt2)
      return; 
    String str = Integer.toHexString(paramInt1);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 46);
    stringBuilder.append("Expected size ");
    stringBuilder.append(paramInt2);
    stringBuilder.append(" got ");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" (0x");
    stringBuilder.append(str);
    stringBuilder.append(")");
    throw new ParseException(stringBuilder.toString(), paramParcel);
  }
  
  private static void zza(Parcel paramParcel, int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt2 == paramInt3)
      return; 
    String str = Integer.toHexString(paramInt2);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 46);
    stringBuilder.append("Expected size ");
    stringBuilder.append(paramInt3);
    stringBuilder.append(" got ");
    stringBuilder.append(paramInt2);
    stringBuilder.append(" (0x");
    stringBuilder.append(str);
    stringBuilder.append(")");
    throw new ParseException(stringBuilder.toString(), paramParcel);
  }
  
  public static class ParseException extends RuntimeException {
    public ParseException(String param1String, Parcel param1Parcel) {
      super(stringBuilder.toString());
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/safeparcel/SafeParcelReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */