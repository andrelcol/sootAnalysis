package com.roadtrack.onstar.circularProgress;

import android.graphics.Color;

public class DefaultButtonController implements ButtonController {
  private boolean enableGradient;
  
  private boolean enablePress;
  
  public boolean enableGradient() {
    return this.enableGradient;
  }
  
  public boolean enablePress() {
    return this.enablePress;
  }
  
  public int getLighterColor(int paramInt) {
    float[] arrayOfFloat = new float[3];
    Color.colorToHSV(paramInt, arrayOfFloat);
    arrayOfFloat[1] = arrayOfFloat[1] - 0.3F;
    arrayOfFloat[2] = arrayOfFloat[2] + 0.3F;
    return Color.HSVToColor(arrayOfFloat);
  }
  
  public int getPressedColor(int paramInt) {
    float[] arrayOfFloat = new float[3];
    Color.colorToHSV(paramInt, arrayOfFloat);
    arrayOfFloat[2] = arrayOfFloat[2] - 0.1F;
    return Color.HSVToColor(arrayOfFloat);
  }
  
  public DefaultButtonController setEnableGradient(boolean paramBoolean) {
    this.enableGradient = paramBoolean;
    return this;
  }
  
  public DefaultButtonController setEnablePress(boolean paramBoolean) {
    this.enablePress = paramBoolean;
    return this;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/circularProgress/DefaultButtonController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */