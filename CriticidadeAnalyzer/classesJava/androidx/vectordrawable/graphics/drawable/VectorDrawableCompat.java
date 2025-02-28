package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.collection.ArrayMap;
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat extends VectorDrawableCommon {
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  
  private boolean mAllowCaching = true;
  
  private ColorFilter mColorFilter;
  
  private boolean mMutated;
  
  private PorterDuffColorFilter mTintFilter;
  
  private final Rect mTmpBounds = new Rect();
  
  private final float[] mTmpFloats = new float[9];
  
  private final Matrix mTmpMatrix = new Matrix();
  
  private VectorDrawableCompatState mVectorState = new VectorDrawableCompatState();
  
  VectorDrawableCompat() {}
  
  VectorDrawableCompat(VectorDrawableCompatState paramVectorDrawableCompatState) {
    this.mTintFilter = updateTintFilter(this.mTintFilter, paramVectorDrawableCompatState.mTint, paramVectorDrawableCompatState.mTintMode);
  }
  
  static int applyAlpha(int paramInt, float paramFloat) {
    return paramInt & 0xFFFFFF | (int)(Color.alpha(paramInt) * paramFloat) << 24;
  }
  
  public static VectorDrawableCompat create(Resources paramResources, int paramInt, Resources.Theme paramTheme) {
    if (Build.VERSION.SDK_INT >= 24) {
      VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
      vectorDrawableCompat.mDelegateDrawable = ResourcesCompat.getDrawable(paramResources, paramInt, paramTheme);
      new VectorDrawableDelegateState(vectorDrawableCompat.mDelegateDrawable.getConstantState());
      return vectorDrawableCompat;
    } 
    try {
      XmlResourceParser xmlResourceParser = paramResources.getXml(paramInt);
      AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
      while (true) {
        paramInt = xmlResourceParser.next();
        if (paramInt != 2 && paramInt != 1)
          continue; 
        break;
      } 
      if (paramInt == 2)
        return createFromXmlInner(paramResources, (XmlPullParser)xmlResourceParser, attributeSet, paramTheme); 
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      this("No start tag found");
      throw xmlPullParserException;
    } catch (XmlPullParserException|IOException xmlPullParserException) {
      return null;
    } 
  }
  
  public static VectorDrawableCompat createFromXmlInner(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
    vectorDrawableCompat.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return vectorDrawableCompat;
  }
  
  private void inflateInternal(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
    VPathRenderer vPathRenderer = vectorDrawableCompatState.mVPathRenderer;
    ArrayDeque<VGroup> arrayDeque = new ArrayDeque();
    arrayDeque.push(vPathRenderer.mRootGroup);
    int j = paramXmlPullParser.getEventType();
    int k = paramXmlPullParser.getDepth();
    int i;
    for (i = 1; j != 1 && (paramXmlPullParser.getDepth() >= k + 1 || j != 3); i = m) {
      int m;
      if (j == 2) {
        VFullPath vFullPath;
        String str = paramXmlPullParser.getName();
        VGroup vGroup = arrayDeque.peek();
        if ("path".equals(str)) {
          vFullPath = new VFullPath();
          vFullPath.inflate(paramResources, paramAttributeSet, paramTheme, paramXmlPullParser);
          vGroup.mChildren.add(vFullPath);
          if (vFullPath.getPathName() != null)
            vPathRenderer.mVGTargetsMap.put(vFullPath.getPathName(), vFullPath); 
          m = 0;
          i = vectorDrawableCompatState.mChangingConfigurations;
          vectorDrawableCompatState.mChangingConfigurations = vFullPath.mChangingConfigurations | i;
        } else {
          VClipPath vClipPath;
          if ("clip-path".equals(vFullPath)) {
            vClipPath = new VClipPath();
            vClipPath.inflate(paramResources, paramAttributeSet, paramTheme, paramXmlPullParser);
            vGroup.mChildren.add(vClipPath);
            if (vClipPath.getPathName() != null)
              vPathRenderer.mVGTargetsMap.put(vClipPath.getPathName(), vClipPath); 
            m = vectorDrawableCompatState.mChangingConfigurations;
            vectorDrawableCompatState.mChangingConfigurations = vClipPath.mChangingConfigurations | m;
            m = i;
          } else {
            m = i;
            if ("group".equals(vClipPath)) {
              VGroup vGroup1 = new VGroup();
              vGroup1.inflate(paramResources, paramAttributeSet, paramTheme, paramXmlPullParser);
              vGroup.mChildren.add(vGroup1);
              arrayDeque.push(vGroup1);
              if (vGroup1.getGroupName() != null)
                vPathRenderer.mVGTargetsMap.put(vGroup1.getGroupName(), vGroup1); 
              m = vectorDrawableCompatState.mChangingConfigurations;
              vectorDrawableCompatState.mChangingConfigurations = vGroup1.mChangingConfigurations | m;
              m = i;
            } 
          } 
        } 
      } else {
        m = i;
        if (j == 3) {
          m = i;
          if ("group".equals(paramXmlPullParser.getName())) {
            arrayDeque.pop();
            m = i;
          } 
        } 
      } 
      j = paramXmlPullParser.next();
    } 
    if (i == 0)
      return; 
    throw new XmlPullParserException("no path defined");
  }
  
  private boolean needMirroring() {
    int i = Build.VERSION.SDK_INT;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (i >= 17) {
      bool1 = bool2;
      if (isAutoMirrored()) {
        bool1 = bool2;
        if (DrawableCompat.getLayoutDirection(this) == 1)
          bool1 = true; 
      } 
    } 
    return bool1;
  }
  
  private static PorterDuff.Mode parseTintModeCompat(int paramInt, PorterDuff.Mode paramMode) {
    if (paramInt != 3) {
      if (paramInt != 5) {
        if (paramInt != 9) {
          switch (paramInt) {
            default:
              return paramMode;
            case 16:
              return PorterDuff.Mode.ADD;
            case 15:
              return PorterDuff.Mode.SCREEN;
            case 14:
              break;
          } 
          return PorterDuff.Mode.MULTIPLY;
        } 
        return PorterDuff.Mode.SRC_ATOP;
      } 
      return PorterDuff.Mode.SRC_IN;
    } 
    return PorterDuff.Mode.SRC_OVER;
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser) throws XmlPullParserException {
    String str;
    VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
    VPathRenderer vPathRenderer = vectorDrawableCompatState.mVPathRenderer;
    vectorDrawableCompatState.mTintMode = parseTintModeCompat(TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
    ColorStateList colorStateList = paramTypedArray.getColorStateList(1);
    if (colorStateList != null)
      vectorDrawableCompatState.mTint = colorStateList; 
    vectorDrawableCompatState.mAutoMirrored = TypedArrayUtils.getNamedBoolean(paramTypedArray, paramXmlPullParser, "autoMirrored", 5, vectorDrawableCompatState.mAutoMirrored);
    vPathRenderer.mViewportWidth = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "viewportWidth", 7, vPathRenderer.mViewportWidth);
    vPathRenderer.mViewportHeight = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "viewportHeight", 8, vPathRenderer.mViewportHeight);
    if (vPathRenderer.mViewportWidth > 0.0F) {
      if (vPathRenderer.mViewportHeight > 0.0F) {
        vPathRenderer.mBaseWidth = paramTypedArray.getDimension(3, vPathRenderer.mBaseWidth);
        vPathRenderer.mBaseHeight = paramTypedArray.getDimension(2, vPathRenderer.mBaseHeight);
        if (vPathRenderer.mBaseWidth > 0.0F) {
          if (vPathRenderer.mBaseHeight > 0.0F) {
            vPathRenderer.setAlpha(TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "alpha", 4, vPathRenderer.getAlpha()));
            str = paramTypedArray.getString(0);
            if (str != null) {
              vPathRenderer.mRootName = str;
              vPathRenderer.mVGTargetsMap.put(str, vPathRenderer);
            } 
            return;
          } 
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append(str.getPositionDescription());
          stringBuilder3.append("<vector> tag requires height > 0");
          throw new XmlPullParserException(stringBuilder3.toString());
        } 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str.getPositionDescription());
        stringBuilder2.append("<vector> tag requires width > 0");
        throw new XmlPullParserException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(str.getPositionDescription());
      stringBuilder1.append("<vector> tag requires viewportHeight > 0");
      throw new XmlPullParserException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str.getPositionDescription());
    stringBuilder.append("<vector> tag requires viewportWidth > 0");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public boolean canApplyTheme() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      DrawableCompat.canApplyTheme(drawable); 
    return false;
  }
  
  public void draw(Canvas paramCanvas) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.draw(paramCanvas);
      return;
    } 
    copyBounds(this.mTmpBounds);
    if (this.mTmpBounds.width() > 0 && this.mTmpBounds.height() > 0) {
      PorterDuffColorFilter porterDuffColorFilter;
      ColorFilter colorFilter2 = this.mColorFilter;
      ColorFilter colorFilter1 = colorFilter2;
      if (colorFilter2 == null)
        porterDuffColorFilter = this.mTintFilter; 
      paramCanvas.getMatrix(this.mTmpMatrix);
      this.mTmpMatrix.getValues(this.mTmpFloats);
      float f2 = Math.abs(this.mTmpFloats[0]);
      float f1 = Math.abs(this.mTmpFloats[4]);
      float f4 = Math.abs(this.mTmpFloats[1]);
      float f3 = Math.abs(this.mTmpFloats[3]);
      if (f4 != 0.0F || f3 != 0.0F) {
        f2 = 1.0F;
        f1 = 1.0F;
      } 
      int i = (int)(this.mTmpBounds.width() * f2);
      int j = (int)(this.mTmpBounds.height() * f1);
      i = Math.min(2048, i);
      int k = Math.min(2048, j);
      if (i > 0 && k > 0) {
        j = paramCanvas.save();
        Rect rect = this.mTmpBounds;
        paramCanvas.translate(rect.left, rect.top);
        if (needMirroring()) {
          paramCanvas.translate(this.mTmpBounds.width(), 0.0F);
          paramCanvas.scale(-1.0F, 1.0F);
        } 
        this.mTmpBounds.offsetTo(0, 0);
        this.mVectorState.createCachedBitmapIfNeeded(i, k);
        if (!this.mAllowCaching) {
          this.mVectorState.updateCachedBitmap(i, k);
        } else if (!this.mVectorState.canReuseCache()) {
          this.mVectorState.updateCachedBitmap(i, k);
          this.mVectorState.updateCacheStates();
        } 
        this.mVectorState.drawCachedBitmapWithRootAlpha(paramCanvas, (ColorFilter)porterDuffColorFilter, this.mTmpBounds);
        paramCanvas.restoreToCount(j);
      } 
    } 
  }
  
  public int getAlpha() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? DrawableCompat.getAlpha(drawable) : this.mVectorState.mVPathRenderer.getRootAlpha();
  }
  
  public int getChangingConfigurations() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getChangingConfigurations() : (super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations());
  }
  
  public Drawable.ConstantState getConstantState() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null && Build.VERSION.SDK_INT >= 24)
      return new VectorDrawableDelegateState(drawable.getConstantState()); 
    this.mVectorState.mChangingConfigurations = getChangingConfigurations();
    return this.mVectorState;
  }
  
  public int getIntrinsicHeight() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getIntrinsicHeight() : (int)this.mVectorState.mVPathRenderer.mBaseHeight;
  }
  
  public int getIntrinsicWidth() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getIntrinsicWidth() : (int)this.mVectorState.mVPathRenderer.mBaseWidth;
  }
  
  public int getOpacity() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getOpacity() : -3;
  }
  
  Object getTargetByName(String paramString) {
    return this.mVectorState.mVPathRenderer.mVGTargetsMap.get(paramString);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws XmlPullParserException, IOException {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
      return;
    } 
    inflate(paramResources, paramXmlPullParser, paramAttributeSet, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.inflate(drawable, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    } 
    VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
    vectorDrawableCompatState.mVPathRenderer = new VPathRenderer();
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY);
    updateStateFromTypedArray(typedArray, paramXmlPullParser);
    typedArray.recycle();
    vectorDrawableCompatState.mChangingConfigurations = getChangingConfigurations();
    vectorDrawableCompatState.mCacheDirty = true;
    inflateInternal(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    this.mTintFilter = updateTintFilter(this.mTintFilter, vectorDrawableCompatState.mTint, vectorDrawableCompatState.mTintMode);
  }
  
  public void invalidateSelf() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.invalidateSelf();
      return;
    } 
    super.invalidateSelf();
  }
  
  public boolean isAutoMirrored() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? DrawableCompat.isAutoMirrored(drawable) : this.mVectorState.mAutoMirrored;
  }
  
  public boolean isStateful() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mDelegateDrawable : Landroid/graphics/drawable/Drawable;
    //   4: astore_2
    //   5: aload_2
    //   6: ifnull -> 14
    //   9: aload_2
    //   10: invokevirtual isStateful : ()Z
    //   13: ireturn
    //   14: aload_0
    //   15: invokespecial isStateful : ()Z
    //   18: ifne -> 64
    //   21: aload_0
    //   22: getfield mVectorState : Landroidx/vectordrawable/graphics/drawable/VectorDrawableCompat$VectorDrawableCompatState;
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull -> 59
    //   30: aload_2
    //   31: invokevirtual isStateful : ()Z
    //   34: ifne -> 64
    //   37: aload_0
    //   38: getfield mVectorState : Landroidx/vectordrawable/graphics/drawable/VectorDrawableCompat$VectorDrawableCompatState;
    //   41: getfield mTint : Landroid/content/res/ColorStateList;
    //   44: astore_2
    //   45: aload_2
    //   46: ifnull -> 59
    //   49: aload_2
    //   50: invokevirtual isStateful : ()Z
    //   53: ifeq -> 59
    //   56: goto -> 64
    //   59: iconst_0
    //   60: istore_1
    //   61: goto -> 66
    //   64: iconst_1
    //   65: istore_1
    //   66: iload_1
    //   67: ireturn
  }
  
  public Drawable mutate() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.mutate();
      return this;
    } 
    if (!this.mMutated && super.mutate() == this) {
      this.mVectorState = new VectorDrawableCompatState(this.mVectorState);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      drawable.setBounds(paramRect); 
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      return drawable.setState(paramArrayOfint); 
    boolean bool2 = false;
    VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
    ColorStateList colorStateList = vectorDrawableCompatState.mTint;
    boolean bool1 = bool2;
    if (colorStateList != null) {
      PorterDuff.Mode mode = vectorDrawableCompatState.mTintMode;
      bool1 = bool2;
      if (mode != null) {
        this.mTintFilter = updateTintFilter(this.mTintFilter, colorStateList, mode);
        invalidateSelf();
        bool1 = true;
      } 
    } 
    bool2 = bool1;
    if (vectorDrawableCompatState.isStateful()) {
      bool2 = bool1;
      if (vectorDrawableCompatState.onStateChanged(paramArrayOfint)) {
        invalidateSelf();
        bool2 = true;
      } 
    } 
    return bool2;
  }
  
  public void scheduleSelf(Runnable paramRunnable, long paramLong) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.scheduleSelf(paramRunnable, paramLong);
      return;
    } 
    super.scheduleSelf(paramRunnable, paramLong);
  }
  
  void setAllowCaching(boolean paramBoolean) {
    this.mAllowCaching = paramBoolean;
  }
  
  public void setAlpha(int paramInt) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.setAlpha(paramInt);
      return;
    } 
    if (this.mVectorState.mVPathRenderer.getRootAlpha() != paramInt) {
      this.mVectorState.mVPathRenderer.setRootAlpha(paramInt);
      invalidateSelf();
    } 
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setAutoMirrored(drawable, paramBoolean);
      return;
    } 
    this.mVectorState.mAutoMirrored = paramBoolean;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.setColorFilter(paramColorFilter);
      return;
    } 
    this.mColorFilter = paramColorFilter;
    invalidateSelf();
  }
  
  public void setTint(int paramInt) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setTint(drawable, paramInt);
      return;
    } 
    setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setTintList(drawable, paramColorStateList);
      return;
    } 
    VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
    if (vectorDrawableCompatState.mTint != paramColorStateList) {
      vectorDrawableCompatState.mTint = paramColorStateList;
      this.mTintFilter = updateTintFilter(this.mTintFilter, paramColorStateList, vectorDrawableCompatState.mTintMode);
      invalidateSelf();
    } 
  }
  
  public void setTintMode(PorterDuff.Mode paramMode) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setTintMode(drawable, paramMode);
      return;
    } 
    VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
    if (vectorDrawableCompatState.mTintMode != paramMode) {
      vectorDrawableCompatState.mTintMode = paramMode;
      this.mTintFilter = updateTintFilter(this.mTintFilter, vectorDrawableCompatState.mTint, paramMode);
      invalidateSelf();
    } 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.setVisible(paramBoolean1, paramBoolean2) : super.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void unscheduleSelf(Runnable paramRunnable) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.unscheduleSelf(paramRunnable);
      return;
    } 
    super.unscheduleSelf(paramRunnable);
  }
  
  PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter paramPorterDuffColorFilter, ColorStateList paramColorStateList, PorterDuff.Mode paramMode) {
    return (paramColorStateList == null || paramMode == null) ? null : new PorterDuffColorFilter(paramColorStateList.getColorForState(getState(), 0), paramMode);
  }
  
  private static class VClipPath extends VPath {
    public VClipPath() {}
    
    public VClipPath(VClipPath param1VClipPath) {
      super(param1VClipPath);
    }
    
    private void updateStateFromTypedArray(TypedArray param1TypedArray) {
      String str2 = param1TypedArray.getString(0);
      if (str2 != null)
        this.mPathName = str2; 
      String str1 = param1TypedArray.getString(1);
      if (str1 != null)
        this.mNodes = PathParser.createNodesFromPathData(str1); 
    }
    
    public void inflate(Resources param1Resources, AttributeSet param1AttributeSet, Resources.Theme param1Theme, XmlPullParser param1XmlPullParser) {
      if (!TypedArrayUtils.hasAttribute(param1XmlPullParser, "pathData"))
        return; 
      TypedArray typedArray = TypedArrayUtils.obtainAttributes(param1Resources, param1Theme, param1AttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    }
    
    public boolean isClipPath() {
      return true;
    }
  }
  
  private static class VFullPath extends VPath {
    float mFillAlpha = 1.0F;
    
    ComplexColorCompat mFillColor;
    
    int mFillRule = 0;
    
    float mStrokeAlpha = 1.0F;
    
    ComplexColorCompat mStrokeColor;
    
    Paint.Cap mStrokeLineCap = Paint.Cap.BUTT;
    
    Paint.Join mStrokeLineJoin = Paint.Join.MITER;
    
    float mStrokeMiterlimit = 4.0F;
    
    float mStrokeWidth = 0.0F;
    
    private int[] mThemeAttrs;
    
    float mTrimPathEnd = 1.0F;
    
    float mTrimPathOffset = 0.0F;
    
    float mTrimPathStart = 0.0F;
    
    public VFullPath() {}
    
    public VFullPath(VFullPath param1VFullPath) {
      super(param1VFullPath);
      this.mThemeAttrs = param1VFullPath.mThemeAttrs;
      this.mStrokeColor = param1VFullPath.mStrokeColor;
      this.mStrokeWidth = param1VFullPath.mStrokeWidth;
      this.mStrokeAlpha = param1VFullPath.mStrokeAlpha;
      this.mFillColor = param1VFullPath.mFillColor;
      this.mFillRule = param1VFullPath.mFillRule;
      this.mFillAlpha = param1VFullPath.mFillAlpha;
      this.mTrimPathStart = param1VFullPath.mTrimPathStart;
      this.mTrimPathEnd = param1VFullPath.mTrimPathEnd;
      this.mTrimPathOffset = param1VFullPath.mTrimPathOffset;
      this.mStrokeLineCap = param1VFullPath.mStrokeLineCap;
      this.mStrokeLineJoin = param1VFullPath.mStrokeLineJoin;
      this.mStrokeMiterlimit = param1VFullPath.mStrokeMiterlimit;
    }
    
    private Paint.Cap getStrokeLineCap(int param1Int, Paint.Cap param1Cap) {
      return (param1Int != 0) ? ((param1Int != 1) ? ((param1Int != 2) ? param1Cap : Paint.Cap.SQUARE) : Paint.Cap.ROUND) : Paint.Cap.BUTT;
    }
    
    private Paint.Join getStrokeLineJoin(int param1Int, Paint.Join param1Join) {
      return (param1Int != 0) ? ((param1Int != 1) ? ((param1Int != 2) ? param1Join : Paint.Join.BEVEL) : Paint.Join.ROUND) : Paint.Join.MITER;
    }
    
    private void updateStateFromTypedArray(TypedArray param1TypedArray, XmlPullParser param1XmlPullParser, Resources.Theme param1Theme) {
      this.mThemeAttrs = null;
      if (!TypedArrayUtils.hasAttribute(param1XmlPullParser, "pathData"))
        return; 
      String str = param1TypedArray.getString(0);
      if (str != null)
        this.mPathName = str; 
      str = param1TypedArray.getString(2);
      if (str != null)
        this.mNodes = PathParser.createNodesFromPathData(str); 
      this.mFillColor = TypedArrayUtils.getNamedComplexColor(param1TypedArray, param1XmlPullParser, param1Theme, "fillColor", 1, 0);
      this.mFillAlpha = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "fillAlpha", 12, this.mFillAlpha);
      this.mStrokeLineCap = getStrokeLineCap(TypedArrayUtils.getNamedInt(param1TypedArray, param1XmlPullParser, "strokeLineCap", 8, -1), this.mStrokeLineCap);
      this.mStrokeLineJoin = getStrokeLineJoin(TypedArrayUtils.getNamedInt(param1TypedArray, param1XmlPullParser, "strokeLineJoin", 9, -1), this.mStrokeLineJoin);
      this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
      this.mStrokeColor = TypedArrayUtils.getNamedComplexColor(param1TypedArray, param1XmlPullParser, param1Theme, "strokeColor", 3, 0);
      this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "strokeAlpha", 11, this.mStrokeAlpha);
      this.mStrokeWidth = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "strokeWidth", 4, this.mStrokeWidth);
      this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "trimPathEnd", 6, this.mTrimPathEnd);
      this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "trimPathOffset", 7, this.mTrimPathOffset);
      this.mTrimPathStart = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "trimPathStart", 5, this.mTrimPathStart);
      this.mFillRule = TypedArrayUtils.getNamedInt(param1TypedArray, param1XmlPullParser, "fillType", 13, this.mFillRule);
    }
    
    float getFillAlpha() {
      return this.mFillAlpha;
    }
    
    int getFillColor() {
      return this.mFillColor.getColor();
    }
    
    float getStrokeAlpha() {
      return this.mStrokeAlpha;
    }
    
    int getStrokeColor() {
      return this.mStrokeColor.getColor();
    }
    
    float getStrokeWidth() {
      return this.mStrokeWidth;
    }
    
    float getTrimPathEnd() {
      return this.mTrimPathEnd;
    }
    
    float getTrimPathOffset() {
      return this.mTrimPathOffset;
    }
    
    float getTrimPathStart() {
      return this.mTrimPathStart;
    }
    
    public void inflate(Resources param1Resources, AttributeSet param1AttributeSet, Resources.Theme param1Theme, XmlPullParser param1XmlPullParser) {
      TypedArray typedArray = TypedArrayUtils.obtainAttributes(param1Resources, param1Theme, param1AttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH);
      updateStateFromTypedArray(typedArray, param1XmlPullParser, param1Theme);
      typedArray.recycle();
    }
    
    public boolean isStateful() {
      return (this.mFillColor.isStateful() || this.mStrokeColor.isStateful());
    }
    
    public boolean onStateChanged(int[] param1ArrayOfint) {
      boolean bool = this.mFillColor.onStateChanged(param1ArrayOfint);
      return this.mStrokeColor.onStateChanged(param1ArrayOfint) | bool;
    }
    
    void setFillAlpha(float param1Float) {
      this.mFillAlpha = param1Float;
    }
    
    void setFillColor(int param1Int) {
      this.mFillColor.setColor(param1Int);
    }
    
    void setStrokeAlpha(float param1Float) {
      this.mStrokeAlpha = param1Float;
    }
    
    void setStrokeColor(int param1Int) {
      this.mStrokeColor.setColor(param1Int);
    }
    
    void setStrokeWidth(float param1Float) {
      this.mStrokeWidth = param1Float;
    }
    
    void setTrimPathEnd(float param1Float) {
      this.mTrimPathEnd = param1Float;
    }
    
    void setTrimPathOffset(float param1Float) {
      this.mTrimPathOffset = param1Float;
    }
    
    void setTrimPathStart(float param1Float) {
      this.mTrimPathStart = param1Float;
    }
  }
  
  private static class VGroup extends VObject {
    int mChangingConfigurations;
    
    final ArrayList<VectorDrawableCompat.VObject> mChildren = new ArrayList<VectorDrawableCompat.VObject>();
    
    private String mGroupName = null;
    
    final Matrix mLocalMatrix = new Matrix();
    
    private float mPivotX = 0.0F;
    
    private float mPivotY = 0.0F;
    
    float mRotate = 0.0F;
    
    private float mScaleX = 1.0F;
    
    private float mScaleY = 1.0F;
    
    final Matrix mStackedMatrix = new Matrix();
    
    private int[] mThemeAttrs;
    
    private float mTranslateX = 0.0F;
    
    private float mTranslateY = 0.0F;
    
    public VGroup() {}
    
    public VGroup(VGroup param1VGroup, ArrayMap<String, Object> param1ArrayMap) {
      this.mRotate = param1VGroup.mRotate;
      this.mPivotX = param1VGroup.mPivotX;
      this.mPivotY = param1VGroup.mPivotY;
      this.mScaleX = param1VGroup.mScaleX;
      this.mScaleY = param1VGroup.mScaleY;
      this.mTranslateX = param1VGroup.mTranslateX;
      this.mTranslateY = param1VGroup.mTranslateY;
      this.mThemeAttrs = param1VGroup.mThemeAttrs;
      this.mGroupName = param1VGroup.mGroupName;
      this.mChangingConfigurations = param1VGroup.mChangingConfigurations;
      String str = this.mGroupName;
      if (str != null)
        param1ArrayMap.put(str, this); 
      this.mLocalMatrix.set(param1VGroup.mLocalMatrix);
      ArrayList<VectorDrawableCompat.VObject> arrayList = param1VGroup.mChildren;
      for (byte b = 0; b < arrayList.size(); b++) {
        param1VGroup = (VGroup)arrayList.get(b);
        if (param1VGroup instanceof VGroup) {
          param1VGroup = param1VGroup;
          this.mChildren.add(new VGroup(param1VGroup, param1ArrayMap));
        } else {
          VectorDrawableCompat.VFullPath vFullPath;
          VectorDrawableCompat.VClipPath vClipPath;
          if (param1VGroup instanceof VectorDrawableCompat.VFullPath) {
            vFullPath = new VectorDrawableCompat.VFullPath((VectorDrawableCompat.VFullPath)param1VGroup);
          } else if (vFullPath instanceof VectorDrawableCompat.VClipPath) {
            vClipPath = new VectorDrawableCompat.VClipPath((VectorDrawableCompat.VClipPath)vFullPath);
          } else {
            throw new IllegalStateException("Unknown object in the tree!");
          } 
          this.mChildren.add(vClipPath);
          String str1 = vClipPath.mPathName;
          if (str1 != null)
            param1ArrayMap.put(str1, vClipPath); 
        } 
      } 
    }
    
    private void updateLocalMatrix() {
      this.mLocalMatrix.reset();
      this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
      this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
      this.mLocalMatrix.postRotate(this.mRotate, 0.0F, 0.0F);
      this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
    }
    
    private void updateStateFromTypedArray(TypedArray param1TypedArray, XmlPullParser param1XmlPullParser) {
      this.mThemeAttrs = null;
      this.mRotate = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "rotation", 5, this.mRotate);
      this.mPivotX = param1TypedArray.getFloat(1, this.mPivotX);
      this.mPivotY = param1TypedArray.getFloat(2, this.mPivotY);
      this.mScaleX = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "scaleX", 3, this.mScaleX);
      this.mScaleY = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "scaleY", 4, this.mScaleY);
      this.mTranslateX = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "translateX", 6, this.mTranslateX);
      this.mTranslateY = TypedArrayUtils.getNamedFloat(param1TypedArray, param1XmlPullParser, "translateY", 7, this.mTranslateY);
      String str = param1TypedArray.getString(0);
      if (str != null)
        this.mGroupName = str; 
      updateLocalMatrix();
    }
    
    public String getGroupName() {
      return this.mGroupName;
    }
    
    public Matrix getLocalMatrix() {
      return this.mLocalMatrix;
    }
    
    public float getPivotX() {
      return this.mPivotX;
    }
    
    public float getPivotY() {
      return this.mPivotY;
    }
    
    public float getRotation() {
      return this.mRotate;
    }
    
    public float getScaleX() {
      return this.mScaleX;
    }
    
    public float getScaleY() {
      return this.mScaleY;
    }
    
    public float getTranslateX() {
      return this.mTranslateX;
    }
    
    public float getTranslateY() {
      return this.mTranslateY;
    }
    
    public void inflate(Resources param1Resources, AttributeSet param1AttributeSet, Resources.Theme param1Theme, XmlPullParser param1XmlPullParser) {
      TypedArray typedArray = TypedArrayUtils.obtainAttributes(param1Resources, param1Theme, param1AttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP);
      updateStateFromTypedArray(typedArray, param1XmlPullParser);
      typedArray.recycle();
    }
    
    public boolean isStateful() {
      for (byte b = 0; b < this.mChildren.size(); b++) {
        if (((VectorDrawableCompat.VObject)this.mChildren.get(b)).isStateful())
          return true; 
      } 
      return false;
    }
    
    public boolean onStateChanged(int[] param1ArrayOfint) {
      byte b = 0;
      boolean bool = false;
      while (b < this.mChildren.size()) {
        bool |= ((VectorDrawableCompat.VObject)this.mChildren.get(b)).onStateChanged(param1ArrayOfint);
        b++;
      } 
      return bool;
    }
    
    public void setPivotX(float param1Float) {
      if (param1Float != this.mPivotX) {
        this.mPivotX = param1Float;
        updateLocalMatrix();
      } 
    }
    
    public void setPivotY(float param1Float) {
      if (param1Float != this.mPivotY) {
        this.mPivotY = param1Float;
        updateLocalMatrix();
      } 
    }
    
    public void setRotation(float param1Float) {
      if (param1Float != this.mRotate) {
        this.mRotate = param1Float;
        updateLocalMatrix();
      } 
    }
    
    public void setScaleX(float param1Float) {
      if (param1Float != this.mScaleX) {
        this.mScaleX = param1Float;
        updateLocalMatrix();
      } 
    }
    
    public void setScaleY(float param1Float) {
      if (param1Float != this.mScaleY) {
        this.mScaleY = param1Float;
        updateLocalMatrix();
      } 
    }
    
    public void setTranslateX(float param1Float) {
      if (param1Float != this.mTranslateX) {
        this.mTranslateX = param1Float;
        updateLocalMatrix();
      } 
    }
    
    public void setTranslateY(float param1Float) {
      if (param1Float != this.mTranslateY) {
        this.mTranslateY = param1Float;
        updateLocalMatrix();
      } 
    }
  }
  
  private static abstract class VObject {
    private VObject() {}
    
    public boolean isStateful() {
      return false;
    }
    
    public boolean onStateChanged(int[] param1ArrayOfint) {
      return false;
    }
  }
  
  private static abstract class VPath extends VObject {
    int mChangingConfigurations;
    
    protected PathParser.PathDataNode[] mNodes = null;
    
    String mPathName;
    
    public VPath() {}
    
    public VPath(VPath param1VPath) {
      this.mPathName = param1VPath.mPathName;
      this.mChangingConfigurations = param1VPath.mChangingConfigurations;
      this.mNodes = PathParser.deepCopyNodes(param1VPath.mNodes);
    }
    
    public PathParser.PathDataNode[] getPathData() {
      return this.mNodes;
    }
    
    public String getPathName() {
      return this.mPathName;
    }
    
    public boolean isClipPath() {
      return false;
    }
    
    public void setPathData(PathParser.PathDataNode[] param1ArrayOfPathDataNode) {
      if (!PathParser.canMorph(this.mNodes, param1ArrayOfPathDataNode)) {
        this.mNodes = PathParser.deepCopyNodes(param1ArrayOfPathDataNode);
      } else {
        PathParser.updateNodes(this.mNodes, param1ArrayOfPathDataNode);
      } 
    }
    
    public void toPath(Path param1Path) {
      param1Path.reset();
      PathParser.PathDataNode[] arrayOfPathDataNode = this.mNodes;
      if (arrayOfPathDataNode != null)
        PathParser.PathDataNode.nodesToPath(arrayOfPathDataNode, param1Path); 
    }
  }
  
  private static class VPathRenderer {
    private static final Matrix IDENTITY_MATRIX = new Matrix();
    
    float mBaseHeight = 0.0F;
    
    float mBaseWidth = 0.0F;
    
    private int mChangingConfigurations;
    
    Paint mFillPaint;
    
    private final Matrix mFinalPathMatrix = new Matrix();
    
    Boolean mIsStateful = null;
    
    private final Path mPath = new Path();
    
    private PathMeasure mPathMeasure;
    
    private final Path mRenderPath = new Path();
    
    int mRootAlpha = 255;
    
    final VectorDrawableCompat.VGroup mRootGroup = new VectorDrawableCompat.VGroup();
    
    String mRootName = null;
    
    Paint mStrokePaint;
    
    final ArrayMap<String, Object> mVGTargetsMap = new ArrayMap();
    
    float mViewportHeight = 0.0F;
    
    float mViewportWidth = 0.0F;
    
    public VPathRenderer() {}
    
    public VPathRenderer(VPathRenderer param1VPathRenderer) {
      this.mBaseWidth = param1VPathRenderer.mBaseWidth;
      this.mBaseHeight = param1VPathRenderer.mBaseHeight;
      this.mViewportWidth = param1VPathRenderer.mViewportWidth;
      this.mViewportHeight = param1VPathRenderer.mViewportHeight;
      this.mChangingConfigurations = param1VPathRenderer.mChangingConfigurations;
      this.mRootAlpha = param1VPathRenderer.mRootAlpha;
      this.mRootName = param1VPathRenderer.mRootName;
      String str = param1VPathRenderer.mRootName;
      if (str != null)
        this.mVGTargetsMap.put(str, this); 
      this.mIsStateful = param1VPathRenderer.mIsStateful;
    }
    
    private static float cross(float param1Float1, float param1Float2, float param1Float3, float param1Float4) {
      return param1Float1 * param1Float4 - param1Float2 * param1Float3;
    }
    
    private void drawGroupTree(VectorDrawableCompat.VGroup param1VGroup, Matrix param1Matrix, Canvas param1Canvas, int param1Int1, int param1Int2, ColorFilter param1ColorFilter) {
      param1VGroup.mStackedMatrix.set(param1Matrix);
      param1VGroup.mStackedMatrix.preConcat(param1VGroup.mLocalMatrix);
      param1Canvas.save();
      for (byte b = 0; b < param1VGroup.mChildren.size(); b++) {
        VectorDrawableCompat.VObject vObject = param1VGroup.mChildren.get(b);
        if (vObject instanceof VectorDrawableCompat.VGroup) {
          drawGroupTree((VectorDrawableCompat.VGroup)vObject, param1VGroup.mStackedMatrix, param1Canvas, param1Int1, param1Int2, param1ColorFilter);
        } else if (vObject instanceof VectorDrawableCompat.VPath) {
          drawPath(param1VGroup, (VectorDrawableCompat.VPath)vObject, param1Canvas, param1Int1, param1Int2, param1ColorFilter);
        } 
      } 
      param1Canvas.restore();
    }
    
    private void drawPath(VectorDrawableCompat.VGroup param1VGroup, VectorDrawableCompat.VPath param1VPath, Canvas param1Canvas, int param1Int1, int param1Int2, ColorFilter param1ColorFilter) {
      float f2 = param1Int1 / this.mViewportWidth;
      float f3 = param1Int2 / this.mViewportHeight;
      float f1 = Math.min(f2, f3);
      Matrix matrix = param1VGroup.mStackedMatrix;
      this.mFinalPathMatrix.set(matrix);
      this.mFinalPathMatrix.postScale(f2, f3);
      f2 = getMatrixScale(matrix);
      if (f2 == 0.0F)
        return; 
      param1VPath.toPath(this.mPath);
      Path path = this.mPath;
      this.mRenderPath.reset();
      if (param1VPath.isClipPath()) {
        this.mRenderPath.addPath(path, this.mFinalPathMatrix);
        param1Canvas.clipPath(this.mRenderPath);
      } else {
        param1VPath = param1VPath;
        if (((VectorDrawableCompat.VFullPath)param1VPath).mTrimPathStart != 0.0F || ((VectorDrawableCompat.VFullPath)param1VPath).mTrimPathEnd != 1.0F) {
          float f4 = ((VectorDrawableCompat.VFullPath)param1VPath).mTrimPathStart;
          float f5 = ((VectorDrawableCompat.VFullPath)param1VPath).mTrimPathOffset;
          float f6 = ((VectorDrawableCompat.VFullPath)param1VPath).mTrimPathEnd;
          if (this.mPathMeasure == null)
            this.mPathMeasure = new PathMeasure(); 
          this.mPathMeasure.setPath(this.mPath, false);
          f3 = this.mPathMeasure.getLength();
          f4 = (f4 + f5) % 1.0F * f3;
          f5 = (f6 + f5) % 1.0F * f3;
          path.reset();
          if (f4 > f5) {
            this.mPathMeasure.getSegment(f4, f3, path, true);
            this.mPathMeasure.getSegment(0.0F, f5, path, true);
          } else {
            this.mPathMeasure.getSegment(f4, f5, path, true);
          } 
          path.rLineTo(0.0F, 0.0F);
        } 
        this.mRenderPath.addPath(path, this.mFinalPathMatrix);
        if (((VectorDrawableCompat.VFullPath)param1VPath).mFillColor.willDraw()) {
          Shader shader;
          Path.FillType fillType;
          ComplexColorCompat complexColorCompat = ((VectorDrawableCompat.VFullPath)param1VPath).mFillColor;
          if (this.mFillPaint == null) {
            this.mFillPaint = new Paint(1);
            this.mFillPaint.setStyle(Paint.Style.FILL);
          } 
          Paint paint = this.mFillPaint;
          if (complexColorCompat.isGradient()) {
            shader = complexColorCompat.getShader();
            shader.setLocalMatrix(this.mFinalPathMatrix);
            paint.setShader(shader);
            paint.setAlpha(Math.round(((VectorDrawableCompat.VFullPath)param1VPath).mFillAlpha * 255.0F));
          } else {
            paint.setColor(VectorDrawableCompat.applyAlpha(shader.getColor(), ((VectorDrawableCompat.VFullPath)param1VPath).mFillAlpha));
          } 
          paint.setColorFilter(param1ColorFilter);
          Path path1 = this.mRenderPath;
          if (((VectorDrawableCompat.VFullPath)param1VPath).mFillRule == 0) {
            fillType = Path.FillType.WINDING;
          } else {
            fillType = Path.FillType.EVEN_ODD;
          } 
          path1.setFillType(fillType);
          param1Canvas.drawPath(this.mRenderPath, paint);
        } 
        if (((VectorDrawableCompat.VFullPath)param1VPath).mStrokeColor.willDraw()) {
          Shader shader;
          ComplexColorCompat complexColorCompat = ((VectorDrawableCompat.VFullPath)param1VPath).mStrokeColor;
          if (this.mStrokePaint == null) {
            this.mStrokePaint = new Paint(1);
            this.mStrokePaint.setStyle(Paint.Style.STROKE);
          } 
          Paint paint = this.mStrokePaint;
          Paint.Join join = ((VectorDrawableCompat.VFullPath)param1VPath).mStrokeLineJoin;
          if (join != null)
            paint.setStrokeJoin(join); 
          Paint.Cap cap = ((VectorDrawableCompat.VFullPath)param1VPath).mStrokeLineCap;
          if (cap != null)
            paint.setStrokeCap(cap); 
          paint.setStrokeMiter(((VectorDrawableCompat.VFullPath)param1VPath).mStrokeMiterlimit);
          if (complexColorCompat.isGradient()) {
            shader = complexColorCompat.getShader();
            shader.setLocalMatrix(this.mFinalPathMatrix);
            paint.setShader(shader);
            paint.setAlpha(Math.round(((VectorDrawableCompat.VFullPath)param1VPath).mStrokeAlpha * 255.0F));
          } else {
            paint.setColor(VectorDrawableCompat.applyAlpha(shader.getColor(), ((VectorDrawableCompat.VFullPath)param1VPath).mStrokeAlpha));
          } 
          paint.setColorFilter(param1ColorFilter);
          paint.setStrokeWidth(((VectorDrawableCompat.VFullPath)param1VPath).mStrokeWidth * f1 * f2);
          param1Canvas.drawPath(this.mRenderPath, paint);
        } 
      } 
    }
    
    private float getMatrixScale(Matrix param1Matrix) {
      float[] arrayOfFloat = new float[4];
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 1.0F;
      arrayOfFloat[2] = 1.0F;
      arrayOfFloat[3] = 0.0F;
      param1Matrix.mapVectors(arrayOfFloat);
      float f1 = (float)Math.hypot(arrayOfFloat[0], arrayOfFloat[1]);
      float f3 = (float)Math.hypot(arrayOfFloat[2], arrayOfFloat[3]);
      float f2 = cross(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
      f3 = Math.max(f1, f3);
      f1 = 0.0F;
      if (f3 > 0.0F)
        f1 = Math.abs(f2) / f3; 
      return f1;
    }
    
    public void draw(Canvas param1Canvas, int param1Int1, int param1Int2, ColorFilter param1ColorFilter) {
      drawGroupTree(this.mRootGroup, IDENTITY_MATRIX, param1Canvas, param1Int1, param1Int2, param1ColorFilter);
    }
    
    public float getAlpha() {
      return getRootAlpha() / 255.0F;
    }
    
    public int getRootAlpha() {
      return this.mRootAlpha;
    }
    
    public boolean isStateful() {
      if (this.mIsStateful == null)
        this.mIsStateful = Boolean.valueOf(this.mRootGroup.isStateful()); 
      return this.mIsStateful.booleanValue();
    }
    
    public boolean onStateChanged(int[] param1ArrayOfint) {
      return this.mRootGroup.onStateChanged(param1ArrayOfint);
    }
    
    public void setAlpha(float param1Float) {
      setRootAlpha((int)(param1Float * 255.0F));
    }
    
    public void setRootAlpha(int param1Int) {
      this.mRootAlpha = param1Int;
    }
  }
  
  private static class VectorDrawableCompatState extends Drawable.ConstantState {
    boolean mAutoMirrored;
    
    boolean mCacheDirty;
    
    boolean mCachedAutoMirrored;
    
    Bitmap mCachedBitmap;
    
    int mCachedRootAlpha;
    
    ColorStateList mCachedTint;
    
    PorterDuff.Mode mCachedTintMode;
    
    int mChangingConfigurations;
    
    Paint mTempPaint;
    
    ColorStateList mTint = null;
    
    PorterDuff.Mode mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
    
    VectorDrawableCompat.VPathRenderer mVPathRenderer;
    
    public VectorDrawableCompatState() {
      this.mVPathRenderer = new VectorDrawableCompat.VPathRenderer();
    }
    
    public VectorDrawableCompatState(VectorDrawableCompatState param1VectorDrawableCompatState) {
      if (param1VectorDrawableCompatState != null) {
        this.mChangingConfigurations = param1VectorDrawableCompatState.mChangingConfigurations;
        this.mVPathRenderer = new VectorDrawableCompat.VPathRenderer(param1VectorDrawableCompatState.mVPathRenderer);
        Paint paint = param1VectorDrawableCompatState.mVPathRenderer.mFillPaint;
        if (paint != null)
          this.mVPathRenderer.mFillPaint = new Paint(paint); 
        paint = param1VectorDrawableCompatState.mVPathRenderer.mStrokePaint;
        if (paint != null)
          this.mVPathRenderer.mStrokePaint = new Paint(paint); 
        this.mTint = param1VectorDrawableCompatState.mTint;
        this.mTintMode = param1VectorDrawableCompatState.mTintMode;
        this.mAutoMirrored = param1VectorDrawableCompatState.mAutoMirrored;
      } 
    }
    
    public boolean canReuseBitmap(int param1Int1, int param1Int2) {
      return (param1Int1 == this.mCachedBitmap.getWidth() && param1Int2 == this.mCachedBitmap.getHeight());
    }
    
    public boolean canReuseCache() {
      return (!this.mCacheDirty && this.mCachedTint == this.mTint && this.mCachedTintMode == this.mTintMode && this.mCachedAutoMirrored == this.mAutoMirrored && this.mCachedRootAlpha == this.mVPathRenderer.getRootAlpha());
    }
    
    public void createCachedBitmapIfNeeded(int param1Int1, int param1Int2) {
      if (this.mCachedBitmap == null || !canReuseBitmap(param1Int1, param1Int2)) {
        this.mCachedBitmap = Bitmap.createBitmap(param1Int1, param1Int2, Bitmap.Config.ARGB_8888);
        this.mCacheDirty = true;
      } 
    }
    
    public void drawCachedBitmapWithRootAlpha(Canvas param1Canvas, ColorFilter param1ColorFilter, Rect param1Rect) {
      Paint paint = getPaint(param1ColorFilter);
      param1Canvas.drawBitmap(this.mCachedBitmap, null, param1Rect, paint);
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConfigurations;
    }
    
    public Paint getPaint(ColorFilter param1ColorFilter) {
      if (!hasTranslucentRoot() && param1ColorFilter == null)
        return null; 
      if (this.mTempPaint == null) {
        this.mTempPaint = new Paint();
        this.mTempPaint.setFilterBitmap(true);
      } 
      this.mTempPaint.setAlpha(this.mVPathRenderer.getRootAlpha());
      this.mTempPaint.setColorFilter(param1ColorFilter);
      return this.mTempPaint;
    }
    
    public boolean hasTranslucentRoot() {
      boolean bool;
      if (this.mVPathRenderer.getRootAlpha() < 255) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isStateful() {
      return this.mVPathRenderer.isStateful();
    }
    
    public Drawable newDrawable() {
      return new VectorDrawableCompat(this);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new VectorDrawableCompat(this);
    }
    
    public boolean onStateChanged(int[] param1ArrayOfint) {
      boolean bool = this.mVPathRenderer.onStateChanged(param1ArrayOfint);
      this.mCacheDirty |= bool;
      return bool;
    }
    
    public void updateCacheStates() {
      this.mCachedTint = this.mTint;
      this.mCachedTintMode = this.mTintMode;
      this.mCachedRootAlpha = this.mVPathRenderer.getRootAlpha();
      this.mCachedAutoMirrored = this.mAutoMirrored;
      this.mCacheDirty = false;
    }
    
    public void updateCachedBitmap(int param1Int1, int param1Int2) {
      this.mCachedBitmap.eraseColor(0);
      Canvas canvas = new Canvas(this.mCachedBitmap);
      this.mVPathRenderer.draw(canvas, param1Int1, param1Int2, null);
    }
  }
  
  private static class VectorDrawableDelegateState extends Drawable.ConstantState {
    private final Drawable.ConstantState mDelegateState;
    
    public VectorDrawableDelegateState(Drawable.ConstantState param1ConstantState) {
      this.mDelegateState = param1ConstantState;
    }
    
    public boolean canApplyTheme() {
      return this.mDelegateState.canApplyTheme();
    }
    
    public int getChangingConfigurations() {
      return this.mDelegateState.getChangingConfigurations();
    }
    
    public Drawable newDrawable() {
      VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
      vectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable();
      return vectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
      vectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(param1Resources);
      return vectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources param1Resources, Resources.Theme param1Theme) {
      VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
      vectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(param1Resources, param1Theme);
      return vectorDrawableCompat;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/vectordrawable/graphics/drawable/VectorDrawableCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */