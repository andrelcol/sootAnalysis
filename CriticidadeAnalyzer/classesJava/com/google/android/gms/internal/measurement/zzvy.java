package com.google.android.gms.internal.measurement;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

final class zzvy {
  static String zza(zzvv paramzzvv, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("# ");
    stringBuilder.append(paramString);
    zza(paramzzvv, stringBuilder, 0);
    return stringBuilder.toString();
  }
  
  private static void zza(zzvv paramzzvv, StringBuilder paramStringBuilder, int paramInt) {
    HashMap<Object, Object> hashMap1 = new HashMap<Object, Object>();
    HashMap<Object, Object> hashMap2 = new HashMap<Object, Object>();
    TreeSet<String> treeSet = new TreeSet();
    for (Method method : paramzzvv.getClass().getDeclaredMethods()) {
      hashMap2.put(method.getName(), method);
      if ((method.getParameterTypes()).length == 0) {
        hashMap1.put(method.getName(), method);
        if (method.getName().startsWith("get"))
          treeSet.add(method.getName()); 
      } 
    } 
    Iterator<String> iterator = treeSet.iterator();
    while (true) {
      while (true)
        break; 
      if (SYNTHETIC_LOCAL_VARIABLE_5 != null)
        zzb(paramStringBuilder, paramInt, zzgf((String)treeSet), SYNTHETIC_LOCAL_VARIABLE_12); 
    } 
    if (paramzzvv instanceof zzuo.zzc) {
      Iterator<Map.Entry> iterator1 = ((zzuo.zzc)paramzzvv).zzbyl.iterator();
      if (iterator1.hasNext()) {
        ((Map.Entry)iterator1.next()).getKey();
        throw new NoSuchMethodError();
      } 
    } 
    zzxe zzxe = ((zzuo)paramzzvv).zzbyf;
    if (zzxe != null)
      zzxe.zzb(paramStringBuilder, paramInt); 
  }
  
  static final void zzb(StringBuilder paramStringBuilder, int paramInt, String paramString, Object paramObject) {
    if (paramObject instanceof List) {
      paramObject = ((List)paramObject).iterator();
      while (paramObject.hasNext())
        zzb(paramStringBuilder, paramInt, paramString, paramObject.next()); 
      return;
    } 
    if (paramObject instanceof Map) {
      paramObject = ((Map)paramObject).entrySet().iterator();
      while (paramObject.hasNext())
        zzb(paramStringBuilder, paramInt, paramString, paramObject.next()); 
      return;
    } 
    paramStringBuilder.append('\n');
    boolean bool = false;
    byte b = 0;
    int i;
    for (i = 0; i < paramInt; i++)
      paramStringBuilder.append(' '); 
    paramStringBuilder.append(paramString);
    if (paramObject instanceof String) {
      paramStringBuilder.append(": \"");
      paramStringBuilder.append(zzwz.zzd(zzte.zzga((String)paramObject)));
      paramStringBuilder.append('"');
      return;
    } 
    if (paramObject instanceof zzte) {
      paramStringBuilder.append(": \"");
      paramStringBuilder.append(zzwz.zzd((zzte)paramObject));
      paramStringBuilder.append('"');
      return;
    } 
    if (paramObject instanceof zzuo) {
      paramStringBuilder.append(" {");
      zza((zzuo)paramObject, paramStringBuilder, paramInt + 2);
      paramStringBuilder.append("\n");
      for (i = b; i < paramInt; i++)
        paramStringBuilder.append(' '); 
      paramStringBuilder.append("}");
      return;
    } 
    if (paramObject instanceof Map.Entry) {
      paramStringBuilder.append(" {");
      Map.Entry entry = (Map.Entry)paramObject;
      i = paramInt + 2;
      zzb(paramStringBuilder, i, "key", entry.getKey());
      zzb(paramStringBuilder, i, "value", entry.getValue());
      paramStringBuilder.append("\n");
      for (i = bool; i < paramInt; i++)
        paramStringBuilder.append(' '); 
      paramStringBuilder.append("}");
      return;
    } 
    paramStringBuilder.append(": ");
    paramStringBuilder.append(paramObject.toString());
  }
  
  private static final String zzgf(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      if (Character.isUpperCase(c))
        stringBuilder.append("_"); 
      stringBuilder.append(Character.toLowerCase(c));
    } 
    return stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */