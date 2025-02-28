package com.roadtrack.onstar.utils;

import android.app.Activity;
import android.view.WindowManager;

public class OrientationManager {
  public static void lockOrientation(Activity paramActivity) {
    int j = (paramActivity.getResources().getConfiguration()).orientation;
    int i = ((WindowManager)paramActivity.getSystemService("window")).getDefaultDisplay().getRotation();
    if (i == 0 || i == 1) {
      if (j == 1) {
        paramActivity.setRequestedOrientation(1);
      } else if (j == 2) {
        paramActivity.setRequestedOrientation(0);
      } 
      return;
    } 
    if (i == 2 || i == 3)
      if (j == 1) {
        paramActivity.setRequestedOrientation(9);
      } else if (j == 2) {
        paramActivity.setRequestedOrientation(8);
      }  
  }
  
  public static void unlockOrientation(Activity paramActivity) {
    paramActivity.setRequestedOrientation(2);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/OrientationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */