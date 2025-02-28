package com.roadtrack.onstar.tomtom.nvdrwrs;

import android.graphics.drawable.Drawable;

public class TomTomMapLeftNavDrawerItem {
  private String count = "0";
  
  private Drawable iconImage;
  
  private boolean isCounterVisible = false;
  
  private String title;
  
  public TomTomMapLeftNavDrawerItem() {}
  
  public TomTomMapLeftNavDrawerItem(String paramString, Drawable paramDrawable) {
    this.title = paramString;
    this.iconImage = paramDrawable;
  }
  
  public TomTomMapLeftNavDrawerItem(boolean paramBoolean) {}
  
  public String getCount() {
    return this.count;
  }
  
  public boolean getCounterVisibility() {
    return this.isCounterVisible;
  }
  
  public Drawable getIconImage() {
    return this.iconImage;
  }
  
  public String getTitle() {
    return this.title;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/tomtom/nvdrwrs/TomTomMapLeftNavDrawerItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */