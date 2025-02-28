package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionMode;

public abstract class ActionBar {
  public boolean closeOptionsMenu() {
    return false;
  }
  
  public abstract boolean collapseActionView();
  
  public abstract void dispatchMenuVisibilityChanged(boolean paramBoolean);
  
  public abstract int getDisplayOptions();
  
  public abstract Context getThemedContext();
  
  public boolean invalidateOptionsMenu() {
    return false;
  }
  
  public abstract void onConfigurationChanged(Configuration paramConfiguration);
  
  void onDestroy() {}
  
  public abstract boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent);
  
  public boolean onMenuKeyEvent(KeyEvent paramKeyEvent) {
    return false;
  }
  
  public boolean openOptionsMenu() {
    return false;
  }
  
  public abstract void setDefaultDisplayHomeAsUpEnabled(boolean paramBoolean);
  
  public abstract void setShowHideAnimationEnabled(boolean paramBoolean);
  
  public abstract void setWindowTitle(CharSequence paramCharSequence);
  
  public abstract ActionMode startActionMode(ActionMode.Callback paramCallback);
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public int gravity = 0;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.gravity = 8388627;
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.ActionBarLayout);
      this.gravity = typedArray.getInt(R.styleable.ActionBarLayout_android_layout_gravity, 0);
      typedArray.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.gravity = param1LayoutParams.gravity;
    }
  }
  
  public static interface OnMenuVisibilityListener {
    void onMenuVisibilityChanged(boolean param1Boolean);
  }
  
  @Deprecated
  public static abstract class Tab {
    public abstract CharSequence getContentDescription();
    
    public abstract View getCustomView();
    
    public abstract Drawable getIcon();
    
    public abstract CharSequence getText();
    
    public abstract void select();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/app/ActionBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */