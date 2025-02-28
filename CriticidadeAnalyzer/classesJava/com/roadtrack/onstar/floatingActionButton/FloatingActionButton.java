package com.roadtrack.onstar.floatingActionButton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.TextView;
import com.roadtrack.onstar.R;

@SuppressLint({"AppCompatCustomView"})
public class FloatingActionButton extends ImageButton {
  private float mCircleSize;
  
  int mColorDisabled;
  
  int mColorNormal;
  
  int mColorPressed;
  
  private int mDrawableSize;
  
  private int mIcon;
  
  private Drawable mIconDrawable;
  
  private float mShadowOffset;
  
  private float mShadowRadius;
  
  private int mSize;
  
  boolean mStrokeVisible;
  
  String mTitle;
  
  public FloatingActionButton(Context paramContext) {
    this(paramContext, null);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  private int adjustColorBrightness(int paramInt, float paramFloat) {
    float[] arrayOfFloat = new float[3];
    Color.colorToHSV(paramInt, arrayOfFloat);
    arrayOfFloat[2] = Math.min(arrayOfFloat[2] * paramFloat, 1.0F);
    return Color.HSVToColor(Color.alpha(paramInt), arrayOfFloat);
  }
  
  private Drawable createCircleDrawable(int paramInt, float paramFloat) {
    int i = Color.alpha(paramInt);
    paramInt = opaque(paramInt);
    ShapeDrawable shapeDrawable = new ShapeDrawable((Shape)new OvalShape());
    Paint paint = shapeDrawable.getPaint();
    paint.setAntiAlias(true);
    paint.setColor(paramInt);
    Drawable[] arrayOfDrawable = new Drawable[2];
    arrayOfDrawable[0] = (Drawable)shapeDrawable;
    arrayOfDrawable[1] = createInnerStrokesDrawable(paramInt, paramFloat);
    if (i == 255 || !this.mStrokeVisible) {
      LayerDrawable layerDrawable = new LayerDrawable(arrayOfDrawable);
      paramInt = (int)(paramFloat / 2.0F);
      layerDrawable.setLayerInset(1, paramInt, paramInt, paramInt, paramInt);
      return (Drawable)layerDrawable;
    } 
    TranslucentLayerDrawable translucentLayerDrawable = new TranslucentLayerDrawable(i, arrayOfDrawable);
    paramInt = (int)(paramFloat / 2.0F);
    translucentLayerDrawable.setLayerInset(1, paramInt, paramInt, paramInt, paramInt);
    return (Drawable)translucentLayerDrawable;
  }
  
  private StateListDrawable createFillDrawable(float paramFloat) {
    StateListDrawable stateListDrawable = new StateListDrawable();
    Drawable drawable = createCircleDrawable(this.mColorDisabled, paramFloat);
    stateListDrawable.addState(new int[] { -16842910 }, drawable);
    drawable = createCircleDrawable(this.mColorPressed, paramFloat);
    stateListDrawable.addState(new int[] { 16842919 }, drawable);
    drawable = createCircleDrawable(this.mColorNormal, paramFloat);
    stateListDrawable.addState(new int[0], drawable);
    return stateListDrawable;
  }
  
  private Drawable createInnerStrokesDrawable(final int color, float paramFloat) {
    if (!this.mStrokeVisible)
      return (Drawable)new ColorDrawable(0); 
    ShapeDrawable shapeDrawable = new ShapeDrawable((Shape)new OvalShape());
    final int bottomStrokeColor = darkenColor(color);
    final int bottomStrokeColorHalfTransparent = halfTransparent(j);
    final int topStrokeColor = lightenColor(color);
    final int topStrokeColorHalfTransparent = halfTransparent(i);
    Paint paint = shapeDrawable.getPaint();
    paint.setAntiAlias(true);
    paint.setStrokeWidth(paramFloat);
    paint.setStyle(Paint.Style.STROKE);
    shapeDrawable.setShaderFactory(new ShapeDrawable.ShaderFactory(this) {
          final int val$bottomStrokeColor;
          
          final int val$bottomStrokeColorHalfTransparent;
          
          final int val$color;
          
          final int val$topStrokeColor;
          
          final int val$topStrokeColorHalfTransparent;
          
          public Shader resize(int param1Int1, int param1Int2) {
            float f2 = (param1Int1 / 2);
            float f1 = param1Int2;
            int i = topStrokeColor;
            param1Int2 = topStrokeColorHalfTransparent;
            param1Int1 = color;
            int j = bottomStrokeColorHalfTransparent;
            int k = bottomStrokeColor;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            return (Shader)new LinearGradient(f2, 0.0F, f2, f1, new int[] { i, param1Int2, param1Int1, j, k }, new float[] { 0.0F, 0.2F, 0.5F, 0.8F, 1.0F }, tileMode);
          }
        });
    return (Drawable)shapeDrawable;
  }
  
  private Drawable createOuterStrokeDrawable(float paramFloat) {
    ShapeDrawable shapeDrawable = new ShapeDrawable((Shape)new OvalShape());
    Paint paint = shapeDrawable.getPaint();
    paint.setAntiAlias(true);
    paint.setStrokeWidth(paramFloat);
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(-16777216);
    paint.setAlpha(opacityToAlpha(0.02F));
    return (Drawable)shapeDrawable;
  }
  
  private int darkenColor(int paramInt) {
    return adjustColorBrightness(paramInt, 0.9F);
  }
  
  private int halfTransparent(int paramInt) {
    return Color.argb(Color.alpha(paramInt) / 2, Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt));
  }
  
  private int lightenColor(int paramInt) {
    return adjustColorBrightness(paramInt, 1.1F);
  }
  
  private int opacityToAlpha(float paramFloat) {
    return (int)(paramFloat * 255.0F);
  }
  
  private int opaque(int paramInt) {
    return Color.rgb(Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt));
  }
  
  @SuppressLint({"NewApi"})
  private void setBackgroundCompat(Drawable paramDrawable) {
    if (Build.VERSION.SDK_INT >= 16) {
      setBackground(paramDrawable);
    } else {
      setBackgroundDrawable(paramDrawable);
    } 
  }
  
  private void updateCircleSize() {
    int i;
    if (this.mSize == 0) {
      i = 2131099759;
    } else {
      i = 2131099758;
    } 
    this.mCircleSize = getDimension(i);
  }
  
  private void updateDrawableSize() {
    this.mDrawableSize = (int)(this.mCircleSize + this.mShadowRadius * 2.0F);
  }
  
  int getColor(int paramInt) {
    return getResources().getColor(paramInt);
  }
  
  public int getColorDisabled() {
    return this.mColorDisabled;
  }
  
  public int getColorNormal() {
    return this.mColorNormal;
  }
  
  public int getColorPressed() {
    return this.mColorPressed;
  }
  
  float getDimension(int paramInt) {
    return getResources().getDimension(paramInt);
  }
  
  Drawable getIconDrawable() {
    Drawable drawable = this.mIconDrawable;
    return (Drawable)((drawable != null) ? drawable : ((this.mIcon != 0) ? getResources().getDrawable(this.mIcon) : new ColorDrawable(0)));
  }
  
  TextView getLabelView() {
    return (TextView)getTag(2131296528);
  }
  
  public int getSize() {
    return this.mSize;
  }
  
  public String getTitle() {
    return this.mTitle;
  }
  
  void init(Context paramContext, AttributeSet paramAttributeSet) {
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FloatingActionButtonCircular, 0, 0);
    this.mColorNormal = typedArray.getColor(1, getColor(17170443));
    this.mColorPressed = typedArray.getColor(2, getColor(17170443));
    this.mColorDisabled = typedArray.getColor(0, getColor(17170443));
    this.mSize = typedArray.getInt(4, 0);
    this.mIcon = typedArray.getResourceId(3, 0);
    this.mTitle = typedArray.getString(6);
    this.mStrokeVisible = typedArray.getBoolean(5, true);
    typedArray.recycle();
    updateCircleSize();
    this.mShadowRadius = getDimension(2131099757);
    this.mShadowOffset = getDimension(2131099756);
    updateDrawableSize();
    updateBackground();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    paramInt1 = this.mDrawableSize;
    setMeasuredDimension(paramInt1, paramInt1);
  }
  
  public void setColorDisabled(int paramInt) {
    if (this.mColorDisabled != paramInt) {
      this.mColorDisabled = paramInt;
      updateBackground();
    } 
  }
  
  public void setColorDisabledResId(int paramInt) {
    setColorDisabled(getColor(paramInt));
  }
  
  public void setColorNormal(int paramInt) {
    if (this.mColorNormal != paramInt) {
      this.mColorNormal = paramInt;
      updateBackground();
    } 
  }
  
  public void setColorNormalResId(int paramInt) {
    setColorNormal(getColor(paramInt));
  }
  
  public void setColorPressed(int paramInt) {
    if (this.mColorPressed != paramInt) {
      this.mColorPressed = paramInt;
      updateBackground();
    } 
  }
  
  public void setColorPressedResId(int paramInt) {
    setColorPressed(getColor(paramInt));
  }
  
  public void setIcon(int paramInt) {
    if (this.mIcon != paramInt) {
      this.mIcon = paramInt;
      this.mIconDrawable = null;
      updateBackground();
    } 
  }
  
  public void setIconDrawable(Drawable paramDrawable) {
    if (this.mIconDrawable != paramDrawable) {
      this.mIcon = 0;
      this.mIconDrawable = paramDrawable;
      updateBackground();
    } 
  }
  
  public void setSize(int paramInt) {
    if (paramInt == 1 || paramInt == 0) {
      if (this.mSize != paramInt) {
        this.mSize = paramInt;
        updateCircleSize();
        updateDrawableSize();
        updateBackground();
      } 
      return;
    } 
    throw new IllegalArgumentException("Use @FAB_SIZE constants only!");
  }
  
  public void setStrokeVisible(boolean paramBoolean) {
    if (this.mStrokeVisible != paramBoolean) {
      this.mStrokeVisible = paramBoolean;
      updateBackground();
    } 
  }
  
  public void setTitle(String paramString) {
    this.mTitle = paramString;
    TextView textView = getLabelView();
    if (textView != null)
      textView.setText(paramString); 
  }
  
  public void setVisibility(int paramInt) {
    TextView textView = getLabelView();
    if (textView != null)
      textView.setVisibility(paramInt); 
    super.setVisibility(paramInt);
  }
  
  void updateBackground() {
    float f2 = getDimension(2131099760);
    float f1 = f2 / 2.0F;
    Resources resources = getResources();
    if (this.mSize == 0) {
      i = 2131165410;
    } else {
      i = 2131165409;
    } 
    LayerDrawable layerDrawable = new LayerDrawable(new Drawable[] { resources.getDrawable(i), (Drawable)createFillDrawable(f2), createOuterStrokeDrawable(f2), getIconDrawable() });
    int i = (int)(this.mCircleSize - getDimension(2131099752)) / 2;
    f2 = this.mShadowRadius;
    int n = (int)f2;
    float f3 = this.mShadowOffset;
    int k = (int)(f2 - f3);
    int j = (int)(f2 + f3);
    layerDrawable.setLayerInset(1, n, k, n, j);
    int m = (int)(n - f1);
    layerDrawable.setLayerInset(2, m, (int)(k - f1), m, (int)(j - f1));
    m = n + i;
    layerDrawable.setLayerInset(3, m, k + i, m, j + i);
    setBackgroundCompat((Drawable)layerDrawable);
  }
  
  private static class TranslucentLayerDrawable extends LayerDrawable {
    private final int mAlpha;
    
    public TranslucentLayerDrawable(int param1Int, Drawable... param1VarArgs) {
      super(param1VarArgs);
      this.mAlpha = param1Int;
    }
    
    public void draw(Canvas param1Canvas) {
      Rect rect = getBounds();
      param1Canvas.saveLayerAlpha(rect.left, rect.top, rect.right, rect.bottom, this.mAlpha, 31);
      super.draw(param1Canvas);
      param1Canvas.restore();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/floatingActionButton/FloatingActionButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */