package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.ViewParent;

public final class ViewParentCompat {
  public static boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean) {
    StringBuilder stringBuilder;
    if (Build.VERSION.SDK_INT >= 21) {
      try {
        return paramViewParent.onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
      } catch (AbstractMethodError abstractMethodError) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("ViewParent ");
        stringBuilder.append(paramViewParent);
        stringBuilder.append(" does not implement interface ");
        stringBuilder.append("method onNestedFling");
        stringBuilder.toString();
      } 
    } else if (paramViewParent instanceof NestedScrollingParent) {
      return ((NestedScrollingParent)paramViewParent).onNestedFling((View)stringBuilder, paramFloat1, paramFloat2, paramBoolean);
    } 
    return false;
  }
  
  public static boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2) {
    StringBuilder stringBuilder;
    if (Build.VERSION.SDK_INT >= 21) {
      try {
        return paramViewParent.onNestedPreFling(paramView, paramFloat1, paramFloat2);
      } catch (AbstractMethodError abstractMethodError) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("ViewParent ");
        stringBuilder.append(paramViewParent);
        stringBuilder.append(" does not implement interface ");
        stringBuilder.append("method onNestedPreFling");
        stringBuilder.toString();
      } 
    } else if (paramViewParent instanceof NestedScrollingParent) {
      return ((NestedScrollingParent)paramViewParent).onNestedPreFling((View)stringBuilder, paramFloat1, paramFloat2);
    } 
    return false;
  }
  
  public static void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfint, int paramInt3) {
    if (paramViewParent instanceof NestedScrollingParent2) {
      ((NestedScrollingParent2)paramViewParent).onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfint, paramInt3);
    } else if (paramInt3 == 0) {
      StringBuilder stringBuilder;
      if (Build.VERSION.SDK_INT >= 21) {
        try {
          paramViewParent.onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfint);
        } catch (AbstractMethodError abstractMethodError) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("ViewParent ");
          stringBuilder.append(paramViewParent);
          stringBuilder.append(" does not implement interface ");
          stringBuilder.append("method onNestedPreScroll");
          stringBuilder.toString();
        } 
      } else if (paramViewParent instanceof NestedScrollingParent) {
        ((NestedScrollingParent)paramViewParent).onNestedPreScroll((View)stringBuilder, paramInt1, paramInt2, paramArrayOfint);
      } 
    } 
  }
  
  public static void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramViewParent instanceof NestedScrollingParent2) {
      ((NestedScrollingParent2)paramViewParent).onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    } else if (paramInt5 == 0) {
      StringBuilder stringBuilder;
      if (Build.VERSION.SDK_INT >= 21) {
        try {
          paramViewParent.onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
        } catch (AbstractMethodError abstractMethodError) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("ViewParent ");
          stringBuilder.append(paramViewParent);
          stringBuilder.append(" does not implement interface ");
          stringBuilder.append("method onNestedScroll");
          stringBuilder.toString();
        } 
      } else if (paramViewParent instanceof NestedScrollingParent) {
        ((NestedScrollingParent)paramViewParent).onNestedScroll((View)stringBuilder, paramInt1, paramInt2, paramInt3, paramInt4);
      } 
    } 
  }
  
  public static void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt1, int paramInt2) {
    if (paramViewParent instanceof NestedScrollingParent2) {
      ((NestedScrollingParent2)paramViewParent).onNestedScrollAccepted(paramView1, paramView2, paramInt1, paramInt2);
    } else if (paramInt2 == 0) {
      StringBuilder stringBuilder;
      if (Build.VERSION.SDK_INT >= 21) {
        try {
          paramViewParent.onNestedScrollAccepted(paramView1, paramView2, paramInt1);
        } catch (AbstractMethodError abstractMethodError) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("ViewParent ");
          stringBuilder.append(paramViewParent);
          stringBuilder.append(" does not implement interface ");
          stringBuilder.append("method onNestedScrollAccepted");
          stringBuilder.toString();
        } 
      } else if (paramViewParent instanceof NestedScrollingParent) {
        ((NestedScrollingParent)paramViewParent).onNestedScrollAccepted((View)stringBuilder, paramView2, paramInt1);
      } 
    } 
  }
  
  public static boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt1, int paramInt2) {
    if (paramViewParent instanceof NestedScrollingParent2)
      return ((NestedScrollingParent2)paramViewParent).onStartNestedScroll(paramView1, paramView2, paramInt1, paramInt2); 
    if (paramInt2 == 0) {
      StringBuilder stringBuilder;
      if (Build.VERSION.SDK_INT >= 21) {
        try {
          return paramViewParent.onStartNestedScroll(paramView1, paramView2, paramInt1);
        } catch (AbstractMethodError abstractMethodError) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("ViewParent ");
          stringBuilder.append(paramViewParent);
          stringBuilder.append(" does not implement interface ");
          stringBuilder.append("method onStartNestedScroll");
          stringBuilder.toString();
        } 
      } else if (paramViewParent instanceof NestedScrollingParent) {
        return ((NestedScrollingParent)paramViewParent).onStartNestedScroll((View)stringBuilder, paramView2, paramInt1);
      } 
    } 
    return false;
  }
  
  public static void onStopNestedScroll(ViewParent paramViewParent, View paramView, int paramInt) {
    if (paramViewParent instanceof NestedScrollingParent2) {
      ((NestedScrollingParent2)paramViewParent).onStopNestedScroll(paramView, paramInt);
    } else if (paramInt == 0) {
      StringBuilder stringBuilder;
      if (Build.VERSION.SDK_INT >= 21) {
        try {
          paramViewParent.onStopNestedScroll(paramView);
        } catch (AbstractMethodError abstractMethodError) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("ViewParent ");
          stringBuilder.append(paramViewParent);
          stringBuilder.append(" does not implement interface ");
          stringBuilder.append("method onStopNestedScroll");
          stringBuilder.toString();
        } 
      } else if (paramViewParent instanceof NestedScrollingParent) {
        ((NestedScrollingParent)paramViewParent).onStopNestedScroll((View)stringBuilder);
      } 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/view/ViewParentCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */