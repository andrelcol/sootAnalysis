package com.google.android.gms.internal.measurement;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class zzyj {
  private static void zza(String paramString, Object paramObject, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2) throws IllegalAccessException, InvocationTargetException {
    if (paramObject != null) {
      if (paramObject instanceof zzyi) {
        int j = paramStringBuffer1.length();
        if (paramString != null) {
          paramStringBuffer2.append(paramStringBuffer1);
          paramStringBuffer2.append(zzgg(paramString));
          paramStringBuffer2.append(" <\n");
          paramStringBuffer1.append("  ");
        } 
        Class<?> clazz = paramObject.getClass();
        for (Field field : clazz.getFields()) {
          int k = field.getModifiers();
          String str = field.getName();
          if (!"cachedSize".equals(str) && (k & 0x1) == 1 && (k & 0x8) != 8 && !str.startsWith("_") && !str.endsWith("_")) {
            Class<?> clazz1 = field.getType();
            Object object = field.get(paramObject);
            if (clazz1.isArray() && clazz1.getComponentType() != byte.class) {
              if (object == null) {
                k = 0;
              } else {
                k = Array.getLength(object);
              } 
              for (byte b1 = 0; b1 < k; b1++)
                zza(str, Array.get(object, b1), paramStringBuffer1, paramStringBuffer2); 
            } else {
              zza(str, object, paramStringBuffer1, paramStringBuffer2);
            } 
          } 
        } 
        Method[] arrayOfMethod = clazz.getMethods();
        int i = arrayOfMethod.length;
        byte b = 0;
        while (true) {
          if (b < i) {
            String str = arrayOfMethod[b].getName();
            if (str.startsWith("set")) {
              String str1 = str.substring(3);
              try {
                str = String.valueOf(str1);
                if (str.length() != 0) {
                  str = "has".concat(str);
                } else {
                  str = new String("has");
                } 
                Method method = clazz.getMethod(str, new Class[0]);
                if (((Boolean)method.invoke(paramObject, new Object[0])).booleanValue()) {
                  String str2 = String.valueOf(str1);
                  if (str2.length() != 0) {
                    str2 = "get".concat(str2);
                  } else {
                    str2 = new String("get");
                  } 
                  Method method1 = clazz.getMethod(str2, new Class[0]);
                  zza(str1, method1.invoke(paramObject, new Object[0]), paramStringBuffer1, paramStringBuffer2);
                } 
              } catch (NoSuchMethodException noSuchMethodException) {}
            } 
            b++;
            continue;
          } 
          if (paramString != null) {
            paramStringBuffer1.setLength(j);
            paramStringBuffer2.append(paramStringBuffer1);
            paramStringBuffer2.append(">\n");
          } 
          return;
        } 
      } 
      paramString = zzgg(paramString);
      paramStringBuffer2.append(paramStringBuffer1);
      paramStringBuffer2.append(paramString);
      paramStringBuffer2.append(": ");
      if (paramObject instanceof String) {
        paramObject = paramObject;
        Object object = paramObject;
        if (!paramObject.startsWith("http")) {
          object = paramObject;
          if (paramObject.length() > 200)
            object = String.valueOf(paramObject.substring(0, 200)).concat("[...]"); 
        } 
        int i = object.length();
        paramObject = new StringBuilder(i);
        for (byte b = 0; b < i; b++) {
          char c = object.charAt(b);
          if (c >= ' ' && c <= '~' && c != '"' && c != '\'') {
            paramObject.append(c);
          } else {
            paramObject.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c) }));
          } 
        } 
        object = paramObject.toString();
        paramStringBuffer2.append("\"");
        paramStringBuffer2.append((String)object);
        paramStringBuffer2.append("\"");
      } else if (paramObject instanceof byte[]) {
        byte[] arrayOfByte = (byte[])paramObject;
        if (arrayOfByte == null) {
          paramStringBuffer2.append("\"\"");
        } else {
          paramStringBuffer2.append('"');
          for (byte b = 0; b < arrayOfByte.length; b++) {
            int i = arrayOfByte[b] & 0xFF;
            if (i == 92 || i == 34) {
              paramStringBuffer2.append('\\');
              paramStringBuffer2.append((char)i);
            } else if (i >= 32 && i < 127) {
              paramStringBuffer2.append((char)i);
            } else {
              paramStringBuffer2.append(String.format("\\%03o", new Object[] { Integer.valueOf(i) }));
            } 
          } 
          paramStringBuffer2.append('"');
        } 
      } else {
        paramStringBuffer2.append(paramObject);
      } 
      paramStringBuffer2.append("\n");
    } 
  }
  
  public static <T extends zzyi> String zzc(T paramT) {
    if (paramT == null)
      return ""; 
    StringBuffer stringBuffer = new StringBuffer();
    try {
      StringBuffer stringBuffer1 = new StringBuffer();
      this();
      zza(null, paramT, stringBuffer1, stringBuffer);
      return stringBuffer.toString();
    } catch (IllegalAccessException illegalAccessException) {
      String str = String.valueOf(illegalAccessException.getMessage());
      return (str.length() != 0) ? "Error printing proto: ".concat(str) : new String("Error printing proto: ");
    } catch (InvocationTargetException invocationTargetException) {
      String str = String.valueOf(invocationTargetException.getMessage());
      return (str.length() != 0) ? "Error printing proto: ".concat(str) : new String("Error printing proto: ");
    } 
  }
  
  private static String zzgg(String paramString) {
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      if (b == 0) {
        stringBuffer.append(Character.toLowerCase(c));
      } else if (Character.isUpperCase(c)) {
        stringBuffer.append('_');
        stringBuffer.append(Character.toLowerCase(c));
      } else {
        stringBuffer.append(c);
      } 
    } 
    return stringBuffer.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzyj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */