package androidx.core.view.accessibility;

import android.os.Build;
import android.view.accessibility.AccessibilityRecord;

public class AccessibilityRecordCompat {
  public static void setMaxScrollX(AccessibilityRecord paramAccessibilityRecord, int paramInt) {
    if (Build.VERSION.SDK_INT >= 15)
      paramAccessibilityRecord.setMaxScrollX(paramInt); 
  }
  
  public static void setMaxScrollY(AccessibilityRecord paramAccessibilityRecord, int paramInt) {
    if (Build.VERSION.SDK_INT >= 15)
      paramAccessibilityRecord.setMaxScrollY(paramInt); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/view/accessibility/AccessibilityRecordCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */