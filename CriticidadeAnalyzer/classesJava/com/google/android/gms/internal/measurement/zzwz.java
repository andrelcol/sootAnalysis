package com.google.android.gms.internal.measurement;

final class zzwz {
  static String zzd(zzte paramzzte) {
    zzxa zzxa = new zzxa(paramzzte);
    StringBuilder stringBuilder = new StringBuilder(zzxa.size());
    for (byte b = 0; b < zzxa.size(); b++) {
      byte b1 = zzxa.zzam(b);
      if (b1 != 34) {
        if (b1 != 39) {
          if (b1 != 92) {
            switch (b1) {
              default:
                if (b1 >= 32 && b1 <= 126) {
                  stringBuilder.append((char)b1);
                  break;
                } 
                stringBuilder.append('\\');
                stringBuilder.append((char)((b1 >>> 6 & 0x3) + 48));
                stringBuilder.append((char)((b1 >>> 3 & 0x7) + 48));
                stringBuilder.append((char)((b1 & 0x7) + 48));
                break;
              case 13:
                stringBuilder.append("\\r");
                break;
              case 12:
                stringBuilder.append("\\f");
                break;
              case 11:
                stringBuilder.append("\\v");
                break;
              case 10:
                stringBuilder.append("\\n");
                break;
              case 9:
                stringBuilder.append("\\t");
                break;
              case 8:
                stringBuilder.append("\\b");
                break;
              case 7:
                stringBuilder.append("\\a");
                break;
            } 
          } else {
            stringBuilder.append("\\\\");
          } 
        } else {
          stringBuilder.append("\\'");
        } 
      } else {
        stringBuilder.append("\\\"");
      } 
    } 
    return stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */