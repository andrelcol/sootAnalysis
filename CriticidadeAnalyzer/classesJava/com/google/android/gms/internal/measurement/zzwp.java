package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzwp extends zzwo<FieldDescriptorType, Object> {
  zzwp(int paramInt) {
    super(paramInt, null);
  }
  
  public final void zzsw() {
    if (!isImmutable()) {
      for (byte b = 0; b < zzyc(); b++) {
        Map.Entry<K, V> entry = zzbx(b);
        if (((zzuh)entry.getKey()).zzwb())
          entry.setValue((V)Collections.unmodifiableList((List)entry.getValue())); 
      } 
      for (Map.Entry<K, V> entry : zzyd()) {
        if (((zzuh)entry.getKey()).zzwb())
          entry.setValue(Collections.unmodifiableList((List)entry.getValue())); 
      } 
    } 
    super.zzsw();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */