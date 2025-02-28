package androidx.core.view;

import android.os.Build;
import android.view.Gravity;

public final class GravityCompat {
  public static int getAbsoluteGravity(int paramInt1, int paramInt2) {
    return (Build.VERSION.SDK_INT >= 17) ? Gravity.getAbsoluteGravity(paramInt1, paramInt2) : (paramInt1 & 0xFF7FFFFF);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/view/GravityCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */