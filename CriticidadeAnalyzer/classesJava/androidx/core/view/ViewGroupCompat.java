package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.R;

public final class ViewGroupCompat {
  public static boolean isTransitionGroup(ViewGroup paramViewGroup) {
    if (Build.VERSION.SDK_INT >= 21)
      return paramViewGroup.isTransitionGroup(); 
    Boolean bool = (Boolean)paramViewGroup.getTag(R.id.tag_transition_group);
    return ((bool != null && bool.booleanValue()) || paramViewGroup.getBackground() != null || ViewCompat.getTransitionName((View)paramViewGroup) != null);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/view/ViewGroupCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */