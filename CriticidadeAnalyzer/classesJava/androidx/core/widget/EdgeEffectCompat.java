package androidx.core.widget;

import android.os.Build;
import android.widget.EdgeEffect;

public final class EdgeEffectCompat {
  public static void onPull(EdgeEffect paramEdgeEffect, float paramFloat1, float paramFloat2) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramEdgeEffect.onPull(paramFloat1, paramFloat2);
    } else {
      paramEdgeEffect.onPull(paramFloat1);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/widget/EdgeEffectCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */