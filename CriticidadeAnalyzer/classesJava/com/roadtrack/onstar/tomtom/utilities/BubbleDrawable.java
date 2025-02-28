package com.roadtrack.onstar.tomtom.utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BubbleDrawable extends Drawable {
  public static String BUBBLES_PATH;
  
  private Activity activity;
  
  private FrameLayout frameLayout;
  
  private ImageView image;
  
  private ImageView imagepin;
  
  private int mBoxHeight;
  
  private Rect mBoxPadding = new Rect();
  
  private RectF mBoxRect;
  
  private int mBoxWidth;
  
  private int mColor;
  
  private float mCornerRad;
  
  private Paint mPaint;
  
  private Path mPointer;
  
  private int mPointerHeight;
  
  private int mPointerWidth;
  
  private TextView myTextView1;
  
  private TextView myTextView2;
  
  private RelativeLayout relativeLayout;
  
  private int weight = 4;
  
  public BubbleDrawable(Activity paramActivity, int paramInt) {
    this.activity = paramActivity;
    BUBBLES_PATH = Utilities.getpath(paramActivity);
    setPointerAlignment(paramInt);
    setBubble();
  }
  
  public static void onDestroy() {
    File file = UtilitiesFile.getFileFromStringFile(BUBBLES_PATH);
    if (file.isDirectory() && file.exists()) {
      String[] arrayOfString = file.list();
      for (byte b = 0; b < arrayOfString.length; b++)
        UtilitiesFile.getFileFromStringFile(file, arrayOfString[b]).delete(); 
    } 
  }
  
  private void setBubble() {
    this.mPaint = new Paint();
    this.mPaint.setAntiAlias(true);
    this.mColor = -1;
    this.mPaint.setColor(this.mColor);
    this.mCornerRad = 0.0F;
    setPointerWidth(40);
    setPointerHeight(40);
  }
  
  private void updatePointerPath() {
    this.mPointer = new Path();
    this.mPointer.setFillType(Path.FillType.EVEN_ODD);
    this.mPointer.moveTo((this.mBoxWidth + this.mCornerRad - this.mPointerWidth + this.weight) / 2.0F, this.mBoxHeight);
    Path path = this.mPointer;
    int i = this.mPointerWidth;
    path.rLineTo(i + this.mCornerRad - i + this.weight, 0.0F);
    this.mPointer.rLineTo(-(this.mPointerWidth / 2), this.mPointerHeight);
    this.mPointer.rLineTo(-(this.mPointerWidth / 2), -this.mPointerHeight);
    this.mPointer.close();
  }
  
  public void createAndSaveBubble(BubbleDrawable paramBubbleDrawable, String paramString) {
    this.relativeLayout.setBackgroundDrawable(paramBubbleDrawable);
    this.frameLayout = (FrameLayout)this.activity.findViewById(2131296553);
    FrameLayout frameLayout = this.frameLayout;
    boolean bool = true;
    frameLayout.setDrawingCacheEnabled(true);
    this.frameLayout.buildDrawingCache();
    this.frameLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    frameLayout = this.frameLayout;
    frameLayout.layout(0, 0, frameLayout.getMeasuredWidth(), this.frameLayout.getMeasuredHeight());
    this.frameLayout.buildDrawingCache(true);
    Bitmap bitmap = Bitmap.createBitmap(this.frameLayout.getDrawingCache());
    this.frameLayout.setDrawingCacheEnabled(false);
    File file2 = UtilitiesFile.getFileFromStringFile(BUBBLES_PATH);
    if (!file2.exists())
      bool = file2.mkdirs(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(BUBBLES_PATH);
    stringBuilder.append("/");
    stringBuilder.append(paramString);
    stringBuilder.append(".png");
    File file1 = UtilitiesFile.getFileFromStringFile(stringBuilder.toString());
    if (file1.exists())
      file1.delete(); 
    if (bool)
      try {
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append(BUBBLES_PATH);
        stringBuilder1.append("/");
        stringBuilder1.append(paramString);
        stringBuilder1.append(".png");
        FileOutputStream fileOutputStream = UtilitiesFile.fileOutputStreamFromStringFile(UtilitiesFile.getFileFromStringFile(stringBuilder1.toString()));
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
      } catch (FileNotFoundException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
      }  
  }
  
  public void draw(Canvas paramCanvas) {
    this.mBoxRect = new RectF(0.0F, 0.0F, this.mBoxWidth, this.mBoxHeight);
    RectF rectF = this.mBoxRect;
    float f = this.mCornerRad;
    paramCanvas.drawRoundRect(rectF, f, f, this.mPaint);
    updatePointerPath();
    paramCanvas.drawPath(this.mPointer, this.mPaint);
  }
  
  public int getOpacity() {
    return 255;
  }
  
  public boolean getPadding(Rect paramRect) {
    paramRect.set(this.mBoxPadding);
    paramRect.bottom += this.mPointerHeight;
    return true;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    this.mBoxWidth = paramRect.width();
    this.mBoxHeight = getBounds().height() - this.mPointerHeight;
    super.onBoundsChange(paramRect);
  }
  
  public void setAlpha(int paramInt) {}
  
  public void setColorFilter(ColorFilter paramColorFilter) {}
  
  public void setCornerRadius(float paramFloat) {
    this.mCornerRad = paramFloat;
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    Rect rect = this.mBoxPadding;
    rect.left = paramInt1;
    rect.top = paramInt2;
    rect.right = paramInt3;
    rect.bottom = paramInt4;
  }
  
  public void setPointerAlignment(int paramInt) {
    if (paramInt < 0 || paramInt > 3)
      Utilities.escribeArchivo("BubbleDrawable", "BubbleDrawable", "Invalid pointerAlignment argument"); 
  }
  
  public void setPointerHeight(int paramInt) {
    this.mPointerHeight = paramInt;
  }
  
  public void setPointerWidth(int paramInt) {
    this.mPointerWidth = paramInt;
  }
  
  public void setText(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2, boolean paramBoolean3, int paramInt) {
    Drawable drawable;
    this.relativeLayout = (RelativeLayout)this.activity.findViewById(2131296919);
    this.myTextView1 = (TextView)this.activity.findViewById(2131297104);
    this.myTextView2 = (TextView)this.activity.findViewById(2131297105);
    this.image = (ImageView)this.activity.findViewById(2131296610);
    this.imagepin = (ImageView)this.activity.findViewById(2131296955);
    this.relativeLayout.setVisibility(0);
    if (Utilities.isAndinos().booleanValue()) {
      drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.ic_iconmapalocalizame_andinos, 2131165479);
    } else {
      drawable = Utilities.getDrawableFromConfigList((Context)this.activity, DrawableResourcesVO.ic_iconmapalocalizame_onstar, 2131165480);
    } 
    this.imagepin.setImageDrawable(drawable);
    if (paramBoolean1) {
      this.myTextView1.setText((CharSequence)Html.fromHtml(paramString1));
      this.myTextView1.setVisibility(0);
      this.myTextView1.setTextSize(10.0F);
    } else {
      this.myTextView1.setVisibility(8);
    } 
    if (paramBoolean2) {
      this.myTextView2.setText((CharSequence)Html.fromHtml(paramString2));
      this.myTextView2.setVisibility(0);
    } else {
      this.myTextView2.setVisibility(8);
    } 
    if (paramBoolean3) {
      this.image.setImageResource(paramInt);
      this.image.setVisibility(0);
    } else {
      ImageView imageView = this.image;
      if (imageView != null)
        imageView.setVisibility(8); 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/tomtom/utilities/BubbleDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */