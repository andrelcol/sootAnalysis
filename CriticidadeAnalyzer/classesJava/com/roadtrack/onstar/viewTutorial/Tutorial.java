package com.roadtrack.onstar.viewTutorial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.roadtrack.onstar.onstarApplication;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tutorial extends View {
  public String ABAJODERECHA = "abajoDerecha";
  
  public String ABAJOIZQUIERDA = "abajoIzquierda";
  
  public String ARRIBADERECHA = "arribaDerecha";
  
  public String ARRIBAIZQUIERDA = "arribaIzquierda";
  
  public String CENTRO = "centro";
  
  public String DERECHA = "derecha";
  
  public String DERECHAABAJO = "derechaAbajo";
  
  public String DERECHAARRIBA = "derechaArriba";
  
  public String IZQUIERDA = "izquierda";
  
  public String IZQUIERDAABAJO = "izquierdaAbajo";
  
  public String IZQUIERDAARRIBA = "izquierdaArriba";
  
  private int NUM0 = 0;
  
  private int NUM1 = 1;
  
  private int NUM10 = 10;
  
  private int NUM120 = 120;
  
  private int NUM180 = 180;
  
  private int NUM2 = 2;
  
  private int NUM20 = 20;
  
  private int NUM25 = 25;
  
  private int NUM4 = 4;
  
  private int NUM40 = 40;
  
  private int NUM5 = 5;
  
  private int NUM50 = 50;
  
  private int NUM60 = 60;
  
  public String RECTAAARRIBA = "rectaArriba";
  
  public String RECTAABAJO = "rectaAbajo";
  
  public String SOLOTEXTO = "solo";
  
  private int arrowHead;
  
  private ClassElements classElements;
  
  private int fontSize;
  
  private int height;
  
  private int numPantalla;
  
  private int pages;
  
  private Paint paint;
  
  private Paint paintCircle = new Paint();
  
  private Path path;
  
  private int position;
  
  private float radio = 60.0F;
  
  private int radioCirculo = getResources().getInteger(2131361804);
  
  private onstarApplication rtApp;
  
  private int stroke;
  
  private TextPaint textPaint;
  
  private Typeface tfChalkduster;
  
  private float x;
  
  private float y;
  
  public Tutorial(Context paramContext, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, ClassElements paramClassElements, Activity paramActivity) {
    this(paramContext, null, paramActivity);
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.numPantalla = paramInt1;
    this.position = paramInt2;
    this.pages = paramInt3;
    this.classElements = paramClassElements;
  }
  
  public Tutorial(Context paramContext, AttributeSet paramAttributeSet, Activity paramActivity) {
    super(paramContext, paramAttributeSet);
    this.paintCircle.setColor(-1);
    this.paintCircle.setStyle(Paint.Style.STROKE);
    this.paintCircle.setStrokeWidth(this.radioCirculo);
    DisplayMetrics displayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    this.height = displayMetrics.heightPixels;
    this.arrowHead = getResources().getInteger(2131361802);
    this.fontSize = getResources().getInteger(2131361827);
    this.stroke = getResources().getInteger(2131361824);
    this.textPaint = new TextPaint();
    if (this.height > 2400)
      this.fontSize = 38; 
    this.textPaint.setTextSize(this.fontSize);
    this.paint = new Paint();
    this.paint.setColor(-1);
    this.paint.setStrokeWidth(this.stroke);
    this.paint.setStyle(Paint.Style.STROKE);
    this.rtApp = (onstarApplication)paramContext.getApplicationContext();
    this.tfChalkduster = onstarApplication.getTypeface(paramContext, this.rtApp.fontPathChalkduster);
    this.textPaint.setTypeface(this.tfChalkduster);
  }
  
  public void drawCircles(Canvas paramCanvas, int paramInt) {
    int i = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
    double d = getResources().getDimensionPixelSize(i);
    long l = getHeight() - Math.round(d) - 30L;
    Paint paint1 = new Paint();
    paint1.setColor(getResources().getColor(2131034168));
    paint1.setStrokeWidth(this.NUM10);
    paint1.setStyle(Paint.Style.STROKE);
    Paint paint2 = new Paint();
    paint2.setColor(paramInt);
    paint2.setStrokeWidth(this.NUM10);
    paint2.setStyle(Paint.Style.STROKE);
    paramInt = getWidth() / this.NUM2;
    i = getWidth() / this.NUM2;
    int j = this.NUM0;
    ArrayList<Long> arrayList = new ArrayList();
    while (true) {
      long l1;
      int k = this.pages;
      if (j < k) {
        int n = this.NUM2;
        int m = this.NUM0;
        if (k % n == m) {
          if (j % n == m) {
            l1 = (paramInt - this.NUM25);
            paramInt -= this.NUM50;
          } else {
            l1 = (this.NUM25 + i);
            i += this.NUM50;
          } 
        } else {
          if (k == this.NUM1) {
            m = getWidth() / this.NUM2;
          } else {
            long l2;
            m = k / n;
            if (m < j) {
              m = this.NUM50;
              l2 = (paramInt - m);
              paramInt -= m;
            } else if (m > j) {
              m = this.NUM50;
              l2 = (i + m);
              i += m;
            } else {
              m = getWidth() / this.NUM2;
              l2 = m;
            } 
            arrayList.add(Long.valueOf(l2));
            paramCanvas.drawCircle((float)l2, (float)l, this.radioCirculo, paint1);
            j++;
          } 
          l1 = m;
        } 
      } else {
        Collections.sort(arrayList);
        paramCanvas.drawCircle((float)((Long)arrayList.get(this.numPantalla)).longValue(), (float)l, this.radioCirculo, paint2);
        return;
      } 
      arrayList.add(Long.valueOf(l1));
      paramCanvas.drawCircle((float)l1, (float)l, this.radioCirculo, paint1);
      j++;
    } 
  }
  
  public void drawLineBottom(Canvas paramCanvas, boolean paramBoolean) {
    float f1 = ((Integer)this.classElements.getListLargeDica().get(this.position)).intValue();
    String str = this.classElements.getListString().get(this.position);
    boolean bool = ((Boolean)this.classElements.getListFlag().get(this.position)).booleanValue();
    float f2 = this.x;
    float f3 = this.y;
    paramCanvas.drawLine(f2, f3, f2, f3 - this.arrowHead, this.paint);
    ArrayList<Float> arrayList = new ArrayList();
    if (paramBoolean) {
      f3 = this.x;
      f2 = this.y;
      paramCanvas.drawLine(f3, f2, f3 - this.arrowHead, f2, this.paint);
      this.path.moveTo(this.x, this.y);
      this.path.lineTo(this.x - f1, this.y - f1);
      f3 = this.x - f1;
      float f = this.y - f1;
      if (!bool) {
        Path path = this.path;
        int i = this.arrowHead;
        path.lineTo(f3 - i, i + f);
        i = this.arrowHead;
        f2 = i;
        float f4 = i;
        this.path.lineTo(f3 - f2 - getResources().getInteger(2131361796), f4 + f);
        arrayList.add(Float.valueOf(f3 - f1));
        arrayList.add(Float.valueOf(f));
      } else {
        arrayList.add(Float.valueOf(f3 - f1));
        arrayList.add(Float.valueOf(f));
      } 
    } else {
      f2 = this.x;
      f3 = this.y;
      paramCanvas.drawLine(f2, f3, f2 + this.arrowHead, f3, this.paint);
      this.path.moveTo(this.x, this.y);
      this.path.lineTo(this.x + f1, this.y - f1);
      f2 = this.x + f1;
      float f = this.y - f1;
      if (!bool) {
        Path path = this.path;
        int i = this.arrowHead;
        path.lineTo(i + f2, i + f);
        i = this.arrowHead;
        f3 = i;
        float f4 = i;
        this.path.lineTo(f3 + f2 + getResources().getInteger(2131361796), f4 + f);
        arrayList.add(Float.valueOf(f2 - f1));
        arrayList.add(Float.valueOf(f));
      } else {
        arrayList.add(Float.valueOf(f2 - f1));
        arrayList.add(Float.valueOf(f));
      } 
    } 
    textArrowBottom(((Float)arrayList.get(this.NUM0)).floatValue(), ((Float)arrayList.get(this.NUM1)).floatValue(), str, paramCanvas);
  }
  
  public void drawLineCenter(Canvas paramCanvas) {
    String str = this.classElements.getListString().get(this.position);
    int i = ((Integer)this.classElements.getListLargeDica().get(this.position)).intValue();
    float f1 = this.x;
    float f2 = this.y;
    paramCanvas.drawLine(f1, f2, f1, f2 - this.arrowHead, this.paint);
    f2 = this.x;
    f1 = this.y;
    paramCanvas.drawLine(f2, f1, f2 + this.arrowHead, f1, this.paint);
    this.path.moveTo(this.x, this.y);
    Path path = this.path;
    f2 = this.x;
    f1 = i;
    path.lineTo(f2 + f1, this.y - f1);
    f2 = this.x + f1;
    f1 = this.y - f1;
    this.path.lineTo(this.arrowHead + f2, f1);
    float f3 = f2 + this.arrowHead;
    this.path.lineTo(f3 - getResources().getInteger(2131361796), getResources().getInteger(2131361801) + f1);
    float f4 = getResources().getInteger(2131361796);
    f2 = getResources().getInteger(2131361801);
    if (this.height < 1000);
    textArrow((f3 + f4 + ((Integer)this.classElements.getListPositionText().get(this.position)).intValue()) / 2.0F, f1 + f2 + ((Integer)this.classElements.getListWidthArea().get(this.position)).intValue(), str, paramCanvas);
  }
  
  public void drawLineLeft(Canvas paramCanvas) {
    String str = this.classElements.getListString().get(this.position);
    int i = ((Integer)this.classElements.getListLargeDica().get(this.position)).intValue();
    float f2 = this.x;
    float f1 = this.y;
    paramCanvas.drawLine(f2, f1, f2, f1 + this.arrowHead, this.paint);
    f2 = this.x;
    f1 = this.y;
    paramCanvas.drawLine(f2, f1, f2 + this.arrowHead, f1, this.paint);
    this.path.moveTo(this.x, this.y);
    Path path = this.path;
    f1 = this.x;
    f2 = i;
    path.lineTo(f1 + f2, this.y + f2);
    f1 = this.x + f2;
    f2 = this.y + f2;
    this.path.lineTo(getResources().getInteger(2131361797) + f1, f2);
    f2 -= getResources().getInteger(2131361798);
    this.path.lineTo(getResources().getInteger(2131361798) + f1, f2);
    f1 += getResources().getInteger(2131361798);
    this.path.lineTo(getResources().getInteger(2131361798) + f1, f2);
    float f3 = getResources().getInteger(2131361798);
    String[] arrayOfString = str.split("\n");
    int k = arrayOfString.length;
    i = 0;
    int j = 0;
    while (i < k) {
      String str1 = arrayOfString[i];
      int m = this.NUM0;
      Rect rect = new Rect(m, m, getWidth() / this.NUM2, getHeight() / this.NUM2);
      this.textPaint.getTextBounds(str, this.NUM0, str.length(), rect);
      this.textPaint.setColor(-1);
      paramCanvas.drawText(str1, this.NUM5 + f1 + f3, j + f2, (Paint)this.textPaint);
      j += this.NUM40;
      i++;
    } 
  }
  
  public void drawLineRect(Canvas paramCanvas, int paramInt1, int paramInt2) {
    float f5;
    this.paint.setStyle(Paint.Style.STROKE);
    String str = this.classElements.getListString().get(this.position);
    int j = ((Integer)this.classElements.getListLargeDica().get(this.position)).intValue();
    int i = ((Integer)this.classElements.getListPosiciones().get(this.position)).intValue();
    this.textPaint.setColor(paramInt2);
    switch (paramInt1) {
      default:
        return;
      case 6:
        f1 = this.x;
        f2 = this.y;
        paramCanvas.drawLine(f1, f2, f1, f2 + this.arrowHead, this.paint);
        f2 = this.x;
        f1 = this.y;
        paramCanvas.drawLine(f2, f1, f2 - this.arrowHead, f1, this.paint);
        this.path.moveTo(this.x, this.y);
        path = this.path;
        f2 = this.x;
        f1 = j;
        path.lineTo(f2 - f1, this.y + f1);
        f2 = this.y;
        path = this.path;
        f3 = this.x;
        f4 = getResources().getInteger(2131361798);
        f2 = f2 + f1 + f1;
        path.lineTo(f3 - f4, f2);
        f3 = this.x - getResources().getInteger(2131361798);
        path = this.path;
        f4 = getResources().getInteger(2131361798);
        f2 += f1;
        path.lineTo(f3 - f4, f2);
        path = this.path;
        f4 = getResources().getInteger(2131361798);
        f1 += f2;
        path.lineTo(f3 - f4, f1);
        f3 -= getResources().getInteger(2131361798);
        f4 = f3 / this.NUM4;
        paramInt1 = this.height;
        if (paramInt1 < 1000) {
          paramInt1 = this.NUM0;
        } else if (paramInt1 > 2000) {
          paramInt1 = this.NUM20;
        } else {
          paramInt1 = this.NUM10;
        } 
        textArrow(f3 + f4 - f2 + ((Integer)this.classElements.getListPositionText().get(this.position)).intValue(), f1 + ((Integer)this.classElements.getListWidthArea().get(this.position)).intValue() + paramInt1, str, paramCanvas);
      case 5:
        f2 = this.x;
        f1 = this.y;
        paramCanvas.drawLine(f2, f1, f2, f1 + this.arrowHead, this.paint);
        f1 = this.x;
        f2 = this.y;
        paramCanvas.drawLine(f1, f2, f1 + this.arrowHead, f2, this.paint);
        this.path.moveTo(this.x, this.y);
        path = this.path;
        f2 = this.x;
        f1 = j;
        path.lineTo(f2 + f1, this.y + f1);
        f3 = this.y;
        path = this.path;
        f2 = this.x;
        f4 = getResources().getInteger(2131361798);
        f3 = f3 + f1 + f1;
        path.lineTo(f2 + f4, f3);
        f2 = this.x + getResources().getInteger(2131361798);
        path = this.path;
        f4 = getResources().getInteger(2131361798);
        f5 = f3 + f1;
        path.lineTo(f4 + f2, f5);
        path = this.path;
        f3 = getResources().getInteger(2131361798);
        f1 = f5 + f1;
        path.lineTo(f3 + f2, f1);
        f2 += getResources().getInteger(2131361798);
        f3 = f2 / this.NUM4;
        paramInt1 = this.height;
        if (paramInt1 < 1000) {
          paramInt1 = this.NUM0;
        } else if (paramInt1 > 2000) {
          paramInt1 = this.NUM20;
        } else {
          paramInt1 = this.NUM10;
        } 
        textArrow((f2 + f3 - i + ((Integer)this.classElements.getListPositionText().get(this.position)).intValue()) / 2.0F, f1 + ((Integer)this.classElements.getListWidthArea().get(this.position)).intValue() + paramInt1, str, paramCanvas);
      case 4:
        f1 = this.x;
        f2 = this.y;
        paramCanvas.drawLine(f1, f2, f1, f2 - this.arrowHead, this.paint);
        f1 = this.x;
        f2 = this.y;
        paramCanvas.drawLine(f1, f2, f1 - this.arrowHead, f2, this.paint);
        this.path.moveTo(this.x, this.y);
        path = this.path;
        f2 = this.x;
        f1 = j;
        path.lineTo(f2 - f1, this.y - f1);
        f3 = this.y;
        path = this.path;
        f2 = this.x;
        f4 = getResources().getInteger(2131361798);
        f3 = f3 - f1 - f1;
        path.lineTo(f2 - f4, f3);
        f2 = this.x - getResources().getInteger(2131361799);
        path = this.path;
        f4 = getResources().getInteger(2131361798);
        f5 = f3 - f1;
        path.lineTo(f2 - f4, f5);
        path = this.path;
        f3 = getResources().getInteger(2131361798);
        f1 = f5 - f1;
        path.lineTo(f2 - f3, f1);
        f3 = f2 - getResources().getInteger(2131361798);
        f2 = f3 / this.NUM4;
        paramInt1 = this.height;
        if (paramInt1 < 1000) {
          paramInt1 = this.NUM0;
        } else if (paramInt1 > 2000) {
          paramInt1 = -this.NUM10;
        } else {
          paramInt1 = this.NUM10;
        } 
        textArrowBottom((f3 - f2 - i + ((Integer)this.classElements.getListPositionText().get(this.position)).intValue()) / 2.0F, f1 + ((Integer)this.classElements.getListWidthArea().get(this.position)).intValue() + paramInt1, str, paramCanvas);
      case 3:
        f2 = this.x;
        f1 = this.y;
        paramCanvas.drawLine(f2, f1, f2, f1 - this.arrowHead, this.paint);
        f1 = this.x;
        f2 = this.y;
        paramCanvas.drawLine(f1, f2, f1 + this.arrowHead, f2, this.paint);
        this.path.moveTo(this.x, this.y);
        path = this.path;
        f2 = this.x;
        f1 = j;
        path.lineTo(f2 + f1, this.y - f1);
        f3 = this.y;
        path = this.path;
        f4 = this.x;
        f2 = getResources().getInteger(2131361798);
        f3 = f3 - f1 - f1;
        path.lineTo(f4 + f2, f3);
        f2 = this.x + getResources().getInteger(2131361799);
        path = this.path;
        f5 = getResources().getInteger(2131361798);
        f4 = f3 - f1;
        path.lineTo(f5 + f2, f4);
        path = this.path;
        f3 = getResources().getInteger(2131361798);
        f1 = f4 - f1;
        path.lineTo(f3 + f2, f1);
        f3 = f2 + getResources().getInteger(2131361798);
        f2 = f3 / this.NUM4;
        paramInt1 = this.height;
        if (paramInt1 < 1000) {
          paramInt1 = this.NUM0;
        } else if (paramInt1 > 2000) {
          paramInt1 = -this.NUM10;
        } else {
          paramInt1 = this.NUM10;
        } 
        textArrowBottom((f3 - f2 + i + ((Integer)this.classElements.getListPositionText().get(this.position)).intValue()) / 2.0F, f1 + ((Integer)this.classElements.getListWidthArea().get(this.position)).intValue() + paramInt1, str, paramCanvas);
      case 2:
        f1 = this.x;
        f2 = this.y;
        paramInt1 = this.arrowHead;
        paramCanvas.drawLine(f1, f2, paramInt1 + f1, f2 + paramInt1, this.paint);
        f2 = this.x;
        f1 = this.y;
        paramInt1 = this.arrowHead;
        paramCanvas.drawLine(f2, f1, f2 - paramInt1, f1 + paramInt1, this.paint);
        this.path.moveTo(this.x, this.y);
        path = this.path;
        f2 = this.x;
        f3 = this.y;
        f1 = j;
        path.lineTo(f2, f3 + f1);
        f3 = this.y;
        path = this.path;
        f2 = this.x;
        f4 = getResources().getInteger(2131361798);
        f3 = f3 + f1 + f1;
        path.lineTo(f2 + f4, f3);
        f2 = this.x + getResources().getInteger(2131361798);
        path = this.path;
        f4 = getResources().getInteger(2131361798);
        f3 += f1;
        path.lineTo(f2 - f4, f3);
        path = this.path;
        f4 = getResources().getInteger(2131361798);
        f1 = f3 + f1;
        path.lineTo(f2 - f4, f1);
        f2 -= getResources().getInteger(2131361798);
        f3 = f2 / this.NUM4;
        paramInt1 = this.height;
        if (paramInt1 < 1000) {
          paramInt1 = this.NUM0;
        } else if (paramInt1 > 2000) {
          paramInt1 = this.NUM20;
        } else {
          paramInt1 = this.NUM10;
        } 
        textArrow(f2 + f3 - i, f1 + ((Integer)this.classElements.getListWidthArea().get(this.position)).intValue() + paramInt1, str, paramCanvas);
      case 1:
        break;
    } 
    float f2 = this.x;
    float f1 = this.y;
    paramInt1 = this.arrowHead;
    paramCanvas.drawLine(f2, f1, f2 - paramInt1, f1 - paramInt1, this.paint);
    f2 = this.x;
    f1 = this.y;
    paramInt1 = this.arrowHead;
    paramCanvas.drawLine(f2, f1, paramInt1 + f2, f1 - paramInt1, this.paint);
    this.path.moveTo(this.x, this.y);
    Path path = this.path;
    float f3 = this.x;
    f2 = this.y;
    f1 = j;
    path.lineTo(f3, f2 - f1);
    f3 = this.y;
    path = this.path;
    f2 = this.x;
    float f4 = getResources().getInteger(2131361798);
    f3 = f3 - f1 - f1;
    path.lineTo(f2 - f4, f3);
    f2 = this.x - getResources().getInteger(2131361798);
    path = this.path;
    f4 = getResources().getInteger(2131361798);
    f3 -= f1;
    path.lineTo(f4 + f2, f3);
    path = this.path;
    f4 = getResources().getInteger(2131361798);
    f1 = f3 - f1;
    path.lineTo(f4 + f2, f1);
    f2 += getResources().getInteger(2131361798);
    f3 = f2 / this.NUM4;
    paramInt1 = this.height;
    if (paramInt1 < 1000) {
      paramInt1 = this.NUM0;
    } else if (paramInt1 > 2000) {
      paramInt1 = -this.NUM10;
    } else {
      paramInt1 = this.NUM10;
    } 
    textArrowBottom(f2 - f3 + ((Integer)this.classElements.getListPositionText().get(this.position)).intValue(), f1 + ((Integer)this.classElements.getListWidthArea().get(this.position)).intValue() + paramInt1, str, paramCanvas);
  }
  
  public void drawLineRight(Canvas paramCanvas) {
    String str = this.classElements.getListString().get(this.position);
    int i = ((Integer)this.classElements.getListLargeDica().get(this.position)).intValue();
    float f2 = this.x;
    float f1 = this.y;
    paramCanvas.drawLine(f2, f1, f2, f1 + this.arrowHead, this.paint);
    f2 = this.x;
    f1 = this.y;
    paramCanvas.drawLine(f2, f1, f2 - this.arrowHead, f1, this.paint);
    this.path.moveTo(this.x, this.y);
    Path path = this.path;
    f1 = this.x;
    f2 = i;
    path.lineTo(f1 - f2, this.y + f2);
    f1 = this.x - f2;
    f2 = this.y + f2;
    this.path.lineTo(f1 - getResources().getInteger(2131361797), f2);
    f2 -= this.arrowHead;
    this.path.lineTo(f1 - getResources().getInteger(2131361799), f2);
    f1 -= getResources().getInteger(2131361799);
    this.path.lineTo(f1 - this.arrowHead, f2);
    float f3 = this.arrowHead;
    i = this.NUM0;
    Rect rect = new Rect(i, i, getWidth() / this.NUM2, getHeight() / this.NUM2);
    String[] arrayOfString = str.split("\n");
    int j = arrayOfString.length;
    byte b = 0;
    i = 0;
    while (b < j) {
      str = arrayOfString[b];
      this.textPaint.getTextBounds(str, this.NUM0, str.length(), rect);
      this.textPaint.setColor(-1);
      paramCanvas.drawText(str, f1 - f3 - rect.width() - this.NUM20, i + f2, (Paint)this.textPaint);
      i += this.NUM40;
      b++;
    } 
  }
  
  public void drawLineTop(Canvas paramCanvas, boolean paramBoolean) {
    float f1 = ((Integer)this.classElements.getListLargeDica().get(this.position)).intValue();
    String str = this.classElements.getListString().get(this.position);
    boolean bool = ((Boolean)this.classElements.getListFlag().get(this.position)).booleanValue();
    float f2 = this.x;
    float f3 = this.y;
    paramCanvas.drawLine(f2, f3, f2, f3 + this.arrowHead, this.paint);
    ArrayList<Float> arrayList = new ArrayList();
    if (paramBoolean) {
      f3 = this.x;
      f2 = this.y;
      paramCanvas.drawLine(f3, f2, f3 - this.arrowHead, f2, this.paint);
      this.path.moveTo(this.x, this.y);
      this.path.lineTo(this.x - f1, this.y + f1);
      f3 = this.x - f1;
      f2 = this.y + f1;
      if (!bool) {
        Path path = this.path;
        int i = this.arrowHead;
        path.lineTo(f3 - i, f2 - i);
        i = this.arrowHead;
        float f = f3 - i;
        f3 = i;
        this.path.lineTo(f - getResources().getInteger(2131361796), f2 - f3);
        arrayList.add(Float.valueOf(f - f1));
        arrayList.add(Float.valueOf(f2 + ((Integer)this.classElements.getListPosiciones().get(this.position)).intValue()));
      } else {
        arrayList.add(Float.valueOf(f3));
        arrayList.add(Float.valueOf(f2 - f1));
      } 
    } else {
      f3 = this.x;
      f2 = this.y;
      paramCanvas.drawLine(f3, f2, f3 + this.arrowHead, f2, this.paint);
      this.path.moveTo(this.x, this.y);
      this.path.lineTo(this.x + f1, this.y + f1);
      f3 = this.x + f1;
      f2 = this.y + f1;
      if (!bool) {
        Path path = this.path;
        int i = this.arrowHead;
        path.lineTo(i + f3, i + f2);
        i = this.arrowHead;
        f3 -= i;
        f2 += i;
        this.path.lineTo(getResources().getInteger(2131361796) + f3, f2);
        arrayList.add(Float.valueOf(f3 + f1));
        arrayList.add(Float.valueOf(f2 + ((Integer)this.classElements.getListPosiciones().get(this.position)).intValue()));
      } else {
        arrayList.add(Float.valueOf(f3));
        arrayList.add(Float.valueOf(f2 + f1));
      } 
    } 
    textArrow(((Float)arrayList.get(this.NUM0)).floatValue(), ((Float)arrayList.get(this.NUM1)).floatValue(), str, paramCanvas);
  }
  
  public void drawTextDicas(Canvas paramCanvas, int paramInt, List<Float> paramList) {
    str = this.classElements.getListString().get(this.position);
    boolean bool = ((Boolean)this.classElements.getListFlag().get(this.position)).booleanValue();
    int j = this.NUM0;
    this.textPaint.setColor(paramInt);
    ArrayList<Float> arrayList = new ArrayList();
    int i = this.NUM0;
    Rect rect = new Rect(i, i, getWidth() / this.NUM2, getHeight() / this.NUM2);
    for (String str : str.split("\n")) {
      this.textPaint.getTextBounds(str, this.NUM0, str.length(), rect);
      paramCanvas.drawText(str, ((Float)paramList.get(this.NUM0)).floatValue() - ((Integer)this.classElements.getListPositionText().get(this.position)).intValue(), ((Float)paramList.get(this.NUM1)).floatValue() + j, (Paint)this.textPaint);
      paramInt = this.height;
      if (paramInt < 1000) {
        paramInt = this.NUM20;
      } else if (paramInt < 2000) {
        paramInt = this.NUM40;
      } else {
        paramInt = this.NUM50;
      } 
      j += paramInt;
      if (i == this.NUM0) {
        arrayList.add(paramList.get(this.NUM1));
      } else {
        arrayList.add(Float.valueOf(((Float)paramList.get(this.NUM1)).floatValue() + j));
      } 
      i++;
    } 
    if (bool) {
      float f2 = (((View)this.classElements.getListTarget().get(this.position)).getHeight() / this.NUM2);
      float f1 = ((Float)arrayList.get(this.NUM0)).floatValue() - f2;
      paramInt = arrayList.size() - this.NUM1;
      if (Build.VERSION.SDK_INT >= 21) {
        float f3 = ((Integer)this.classElements.getListWidthArea().get(this.position)).intValue();
        float f4 = (getWidth() - this.NUM40);
        float f5 = ((Float)arrayList.get(paramInt)).floatValue();
        paramInt = this.NUM40;
        paramCanvas.drawRoundRect(f3, f1, f4, f5 + f2, paramInt, paramInt, this.paint);
      } else {
        paramCanvas.drawRect(((Integer)this.classElements.getListWidthArea().get(this.position)).intValue(), f1, (getWidth() - this.NUM40), (int)(((Float)arrayList.get(paramInt)).floatValue() + f2), this.paint);
      } 
      CornerPathEffect cornerPathEffect = new CornerPathEffect(this.radio);
      this.paint.setPathEffect((PathEffect)cornerPathEffect);
      paramCanvas.drawPath(this.path, this.paint);
      Path path = new Path();
      path.addArc(new RectF(), this.NUM0, this.NUM180);
      paramCanvas.clipPath(path, Region.Op.DIFFERENCE);
    } 
  }
  
  protected void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    this.path = new Path();
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), 2131165543);
    Shader.TileMode tileMode = Shader.TileMode.REPEAT;
    BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
    this.paint.setShader((Shader)bitmapShader);
    if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.IZQUIERDA)) {
      drawLineLeft(paramCanvas);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.CENTRO)) {
      drawLineCenter(paramCanvas);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.DERECHA)) {
      drawLineRight(paramCanvas);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.ABAJODERECHA)) {
      drawLineBottom(paramCanvas, false);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.ABAJOIZQUIERDA)) {
      drawLineBottom(paramCanvas, true);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.RECTAABAJO)) {
      drawLineRect(paramCanvas, 1, -1);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.RECTAAARRIBA)) {
      drawLineRect(paramCanvas, 2, -1);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.ARRIBAIZQUIERDA)) {
      drawLineTop(paramCanvas, true);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.ARRIBADERECHA)) {
      drawLineTop(paramCanvas, false);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.DERECHAABAJO)) {
      drawLineRect(paramCanvas, 3, -1);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.IZQUIERDAABAJO)) {
      drawLineRect(paramCanvas, 4, -1);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.DERECHAARRIBA)) {
      drawLineRect(paramCanvas, 5, -1);
    } else if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.IZQUIERDAARRIBA)) {
      drawLineRect(paramCanvas, 6, -1);
    } 
    if (((String)this.classElements.getListTipo().get(this.position)).equalsIgnoreCase(this.SOLOTEXTO)) {
      ArrayList<Float> arrayList = new ArrayList();
      arrayList.add(Float.valueOf(this.x - ((Integer)this.classElements.getListLargeDica().get(this.position)).intValue()));
      arrayList.add(Float.valueOf(this.y - ((Integer)this.classElements.getListPosiciones().get(this.position)).intValue()));
      drawTextDicas(paramCanvas, -1, arrayList);
    } 
    drawCircles(paramCanvas, -1);
    CornerPathEffect cornerPathEffect = new CornerPathEffect(this.radio);
    this.paint.setPathEffect((PathEffect)cornerPathEffect);
    paramCanvas.drawPath(this.path, this.paint);
  }
  
  public void textArrow(float paramFloat1, float paramFloat2, String paramString, Canvas paramCanvas) {
    ArrayList<Float> arrayList = new ArrayList();
    int i = this.NUM0;
    Rect rect = new Rect(i, i, getWidth() / this.NUM2, getHeight() / this.NUM2);
    String[] arrayOfString2 = paramString.split("\n");
    int j = arrayOfString2.length;
    boolean bool = false;
    byte b = 0;
    float f = 0.0F;
    while (b < j) {
      String str = arrayOfString2[b];
      arrayList.add(Float.valueOf(paramFloat2 + f));
      i = this.height;
      if (i < 1000) {
        i = this.NUM20;
      } else if (i < 2000) {
        i = this.NUM40;
      } else {
        i = this.NUM50;
      } 
      f += i;
      b++;
    } 
    String[] arrayOfString1 = paramString.split("\n");
    j = arrayOfString1.length;
    b = 0;
    for (i = bool; i < j; i++) {
      String str = arrayOfString1[i];
      this.textPaint.getTextBounds(str, this.NUM0, str.length(), rect);
      this.textPaint.setColor(-1);
      paramCanvas.drawText(str, paramFloat1, ((Float)arrayList.get(b)).floatValue(), (Paint)this.textPaint);
      b++;
    } 
  }
  
  public void textArrowBottom(float paramFloat1, float paramFloat2, String paramString, Canvas paramCanvas) {
    ArrayList<Float> arrayList = new ArrayList();
    int i = this.NUM0;
    Rect rect = new Rect(i, i, getWidth() / this.NUM2, getHeight() / this.NUM2);
    String[] arrayOfString2 = paramString.split("\n");
    int k = arrayOfString2.length;
    boolean bool = false;
    int j = 0;
    float f = 0.0F;
    while (j < k) {
      String str = arrayOfString2[j];
      arrayList.add(Float.valueOf(paramFloat2 - f));
      i = this.height;
      if (i < 1000) {
        i = this.NUM20;
      } else if (i < 2000) {
        i = this.NUM40;
      } else {
        i = this.NUM50;
      } 
      f += i;
      j++;
    } 
    j = arrayList.size() - this.NUM1;
    String[] arrayOfString1 = paramString.split("\n");
    k = arrayOfString1.length;
    for (i = bool; i < k; i++) {
      String str = arrayOfString1[i];
      this.textPaint.getTextBounds(str, this.NUM0, str.length(), rect);
      this.textPaint.setColor(-1);
      paramCanvas.drawText(str, paramFloat1, ((Float)arrayList.get(j)).floatValue(), (Paint)this.textPaint);
      j--;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/viewTutorial/Tutorial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */