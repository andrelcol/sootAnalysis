package androidx.appcompat.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.view.ViewConfiguration;
import androidx.appcompat.R;

public class ActionBarPolicy {
  private Context mContext;
  
  private ActionBarPolicy(Context paramContext) {
    this.mContext = paramContext;
  }
  
  public static ActionBarPolicy get(Context paramContext) {
    return new ActionBarPolicy(paramContext);
  }
  
  public boolean enableHomeButtonByDefault() {
    boolean bool;
    if ((this.mContext.getApplicationInfo()).targetSdkVersion < 14) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int getEmbeddedMenuWidthLimit() {
    return (this.mContext.getResources().getDisplayMetrics()).widthPixels / 2;
  }
  
  public int getMaxActionButtons() {
    Configuration configuration = this.mContext.getResources().getConfiguration();
    int j = configuration.screenWidthDp;
    int i = configuration.screenHeightDp;
    return (configuration.smallestScreenWidthDp > 600 || j > 600 || (j > 960 && i > 720) || (j > 720 && i > 960)) ? 5 : ((j >= 500 || (j > 640 && i > 480) || (j > 480 && i > 640)) ? 4 : ((j >= 360) ? 3 : 2));
  }
  
  public int getStackedTabMaxWidth() {
    return this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
  }
  
  public int getTabContainerHeight() {
    TypedArray typedArray = this.mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
    int j = typedArray.getLayoutDimension(R.styleable.ActionBar_height, 0);
    Resources resources = this.mContext.getResources();
    int i = j;
    if (!hasEmbeddedTabs())
      i = Math.min(j, resources.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height)); 
    typedArray.recycle();
    return i;
  }
  
  public boolean hasEmbeddedTabs() {
    return this.mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
  }
  
  public boolean showsOverflowMenuButton() {
    return (Build.VERSION.SDK_INT >= 19) ? true : (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() ^ true);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/view/ActionBarPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */