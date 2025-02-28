package com.roadtrack.onstar.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DrawableUtils {
  public static Drawable resizeDrawable(Context paramContext, Drawable paramDrawable, int paramInt) {
    if (paramDrawable == null)
      return null; 
    try {
      int i = paramDrawable.getIntrinsicWidth();
      int j = paramDrawable.getIntrinsicHeight();
      Bitmap bitmap = Bitmap.createBitmap((int)(paramInt / j * i), paramInt, Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas();
      this(bitmap);
      paramDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
      paramDrawable.draw(canvas);
      return (Drawable)new BitmapDrawable(paramContext.getResources(), bitmap);
    } catch (Exception exception) {
      return null;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/DrawableUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */