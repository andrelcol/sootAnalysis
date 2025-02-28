package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.appcompat.R;
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.collection.LruCache;
import androidx.collection.SparseArrayCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class AppCompatDrawableManager {
  private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
  
  private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
  
  private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
  
  private static final ColorFilterLruCache COLOR_FILTER_CACHE;
  
  private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
  
  private static AppCompatDrawableManager INSTANCE;
  
  private static final int[] TINT_CHECKABLE_BUTTON_LIST;
  
  private static final int[] TINT_COLOR_CONTROL_NORMAL;
  
  private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
  
  private ArrayMap<String, InflateDelegate> mDelegates;
  
  private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>>(0);
  
  private boolean mHasCheckedVectorDrawableSetup;
  
  private SparseArrayCompat<String> mKnownDrawableIdTags;
  
  private WeakHashMap<Context, SparseArrayCompat<ColorStateList>> mTintLists;
  
  private TypedValue mTypedValue;
  
  static {
    COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha };
    TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha };
    COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light };
    COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult };
    TINT_COLOR_CONTROL_STATE_LIST = new int[] { R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material };
    TINT_CHECKABLE_BUTTON_LIST = new int[] { R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material };
  }
  
  private void addDelegate(String paramString, InflateDelegate paramInflateDelegate) {
    if (this.mDelegates == null)
      this.mDelegates = new ArrayMap(); 
    this.mDelegates.put(paramString, paramInflateDelegate);
  }
  
  private boolean addDrawableToCache(Context paramContext, long paramLong, Drawable paramDrawable) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload #4
    //   4: invokevirtual getConstantState : ()Landroid/graphics/drawable/Drawable$ConstantState;
    //   7: astore #6
    //   9: aload #6
    //   11: ifnull -> 78
    //   14: aload_0
    //   15: getfield mDrawableCaches : Ljava/util/WeakHashMap;
    //   18: aload_1
    //   19: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast androidx/collection/LongSparseArray
    //   25: astore #5
    //   27: aload #5
    //   29: astore #4
    //   31: aload #5
    //   33: ifnonnull -> 57
    //   36: new androidx/collection/LongSparseArray
    //   39: astore #4
    //   41: aload #4
    //   43: invokespecial <init> : ()V
    //   46: aload_0
    //   47: getfield mDrawableCaches : Ljava/util/WeakHashMap;
    //   50: aload_1
    //   51: aload #4
    //   53: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   56: pop
    //   57: new java/lang/ref/WeakReference
    //   60: astore_1
    //   61: aload_1
    //   62: aload #6
    //   64: invokespecial <init> : (Ljava/lang/Object;)V
    //   67: aload #4
    //   69: lload_2
    //   70: aload_1
    //   71: invokevirtual put : (JLjava/lang/Object;)V
    //   74: aload_0
    //   75: monitorexit
    //   76: iconst_1
    //   77: ireturn
    //   78: aload_0
    //   79: monitorexit
    //   80: iconst_0
    //   81: ireturn
    //   82: astore_1
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_1
    //   86: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	82	finally
    //   14	27	82	finally
    //   36	57	82	finally
    //   57	74	82	finally
  }
  
  private void addTintListToCache(Context paramContext, int paramInt, ColorStateList paramColorStateList) {
    if (this.mTintLists == null)
      this.mTintLists = new WeakHashMap<Context, SparseArrayCompat<ColorStateList>>(); 
    SparseArrayCompat<ColorStateList> sparseArrayCompat2 = this.mTintLists.get(paramContext);
    SparseArrayCompat<ColorStateList> sparseArrayCompat1 = sparseArrayCompat2;
    if (sparseArrayCompat2 == null) {
      sparseArrayCompat1 = new SparseArrayCompat();
      this.mTintLists.put(paramContext, sparseArrayCompat1);
    } 
    sparseArrayCompat1.append(paramInt, paramColorStateList);
  }
  
  private static boolean arrayContains(int[] paramArrayOfint, int paramInt) {
    int i = paramArrayOfint.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfint[b] == paramInt)
        return true; 
    } 
    return false;
  }
  
  private void checkVectorDrawableSetup(Context paramContext) {
    if (this.mHasCheckedVectorDrawableSetup)
      return; 
    this.mHasCheckedVectorDrawableSetup = true;
    Drawable drawable = getDrawable(paramContext, R.drawable.abc_vector_test);
    if (drawable != null && isVectorDrawable(drawable))
      return; 
    this.mHasCheckedVectorDrawableSetup = false;
    throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
  }
  
  private ColorStateList createBorderlessButtonColorStateList(Context paramContext) {
    return createButtonColorStateList(paramContext, 0);
  }
  
  private ColorStateList createButtonColorStateList(Context paramContext, int paramInt) {
    int k = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlHighlight);
    int j = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorButtonNormal);
    int[] arrayOfInt1 = ThemeUtils.DISABLED_STATE_SET;
    int[] arrayOfInt3 = ThemeUtils.PRESSED_STATE_SET;
    int i = ColorUtils.compositeColors(k, paramInt);
    int[] arrayOfInt2 = ThemeUtils.FOCUSED_STATE_SET;
    k = ColorUtils.compositeColors(k, paramInt);
    return new ColorStateList(new int[][] { arrayOfInt1, arrayOfInt3, arrayOfInt2, ThemeUtils.EMPTY_STATE_SET }, new int[] { j, i, k, paramInt });
  }
  
  private static long createCacheKey(TypedValue paramTypedValue) {
    return paramTypedValue.assetCookie << 32L | paramTypedValue.data;
  }
  
  private ColorStateList createColoredButtonColorStateList(Context paramContext) {
    return createButtonColorStateList(paramContext, ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorAccent));
  }
  
  private ColorStateList createDefaultButtonColorStateList(Context paramContext) {
    return createButtonColorStateList(paramContext, ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorButtonNormal));
  }
  
  private Drawable createDrawableIfNeeded(Context paramContext, int paramInt) {
    LayerDrawable layerDrawable;
    if (this.mTypedValue == null)
      this.mTypedValue = new TypedValue(); 
    TypedValue typedValue = this.mTypedValue;
    paramContext.getResources().getValue(paramInt, typedValue, true);
    long l = createCacheKey(typedValue);
    Drawable drawable = getCachedDrawable(paramContext, l);
    if (drawable != null)
      return drawable; 
    if (paramInt == R.drawable.abc_cab_background_top_material)
      layerDrawable = new LayerDrawable(new Drawable[] { getDrawable(paramContext, R.drawable.abc_cab_background_internal_bg), getDrawable(paramContext, R.drawable.abc_cab_background_top_mtrl_alpha) }); 
    if (layerDrawable != null) {
      layerDrawable.setChangingConfigurations(typedValue.changingConfigurations);
      addDrawableToCache(paramContext, l, (Drawable)layerDrawable);
    } 
    return (Drawable)layerDrawable;
  }
  
  private ColorStateList createSwitchThumbColorStateList(Context paramContext) {
    int[][] arrayOfInt1 = new int[3][];
    int[] arrayOfInt = new int[3];
    ColorStateList colorStateList = ThemeUtils.getThemeAttrColorStateList(paramContext, R.attr.colorSwitchThumbNormal);
    if (colorStateList != null && colorStateList.isStateful()) {
      arrayOfInt1[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt[0] = colorStateList.getColorForState(arrayOfInt1[0], 0);
      arrayOfInt1[1] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt[1] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      arrayOfInt1[2] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt[2] = colorStateList.getDefaultColor();
    } else {
      arrayOfInt1[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
      arrayOfInt1[1] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt[1] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      arrayOfInt1[2] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt[2] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
    } 
    return new ColorStateList(arrayOfInt1, arrayOfInt);
  }
  
  private static PorterDuffColorFilter createTintFilter(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int[] paramArrayOfint) {
    return (paramColorStateList == null || paramMode == null) ? null : getPorterDuffColorFilter(paramColorStateList.getColorForState(paramArrayOfint, 0), paramMode);
  }
  
  public static AppCompatDrawableManager get() {
    // Byte code:
    //   0: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   2: monitorenter
    //   3: getstatic androidx/appcompat/widget/AppCompatDrawableManager.INSTANCE : Landroidx/appcompat/widget/AppCompatDrawableManager;
    //   6: ifnonnull -> 27
    //   9: new androidx/appcompat/widget/AppCompatDrawableManager
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic androidx/appcompat/widget/AppCompatDrawableManager.INSTANCE : Landroidx/appcompat/widget/AppCompatDrawableManager;
    //   21: getstatic androidx/appcompat/widget/AppCompatDrawableManager.INSTANCE : Landroidx/appcompat/widget/AppCompatDrawableManager;
    //   24: invokestatic installDefaultInflateDelegates : (Landroidx/appcompat/widget/AppCompatDrawableManager;)V
    //   27: getstatic androidx/appcompat/widget/AppCompatDrawableManager.INSTANCE : Landroidx/appcompat/widget/AppCompatDrawableManager;
    //   30: astore_0
    //   31: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   33: monitorexit
    //   34: aload_0
    //   35: areturn
    //   36: astore_0
    //   37: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   39: monitorexit
    //   40: aload_0
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   3	27	36	finally
    //   27	31	36	finally
  }
  
  private Drawable getCachedDrawable(Context paramContext, long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mDrawableCaches : Ljava/util/WeakHashMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast androidx/collection/LongSparseArray
    //   13: astore #4
    //   15: aload #4
    //   17: ifnonnull -> 24
    //   20: aload_0
    //   21: monitorexit
    //   22: aconst_null
    //   23: areturn
    //   24: aload #4
    //   26: lload_2
    //   27: invokevirtual get : (J)Ljava/lang/Object;
    //   30: checkcast java/lang/ref/WeakReference
    //   33: astore #5
    //   35: aload #5
    //   37: ifnull -> 75
    //   40: aload #5
    //   42: invokevirtual get : ()Ljava/lang/Object;
    //   45: checkcast android/graphics/drawable/Drawable$ConstantState
    //   48: astore #5
    //   50: aload #5
    //   52: ifnull -> 69
    //   55: aload #5
    //   57: aload_1
    //   58: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   61: invokevirtual newDrawable : (Landroid/content/res/Resources;)Landroid/graphics/drawable/Drawable;
    //   64: astore_1
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: areturn
    //   69: aload #4
    //   71: lload_2
    //   72: invokevirtual delete : (J)V
    //   75: aload_0
    //   76: monitorexit
    //   77: aconst_null
    //   78: areturn
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	79	finally
    //   24	35	79	finally
    //   40	50	79	finally
    //   55	65	79	finally
    //   69	75	79	finally
  }
  
  public static PorterDuffColorFilter getPorterDuffColorFilter(int paramInt, PorterDuff.Mode paramMode) {
    // Byte code:
    //   0: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   2: monitorenter
    //   3: getstatic androidx/appcompat/widget/AppCompatDrawableManager.COLOR_FILTER_CACHE : Landroidx/appcompat/widget/AppCompatDrawableManager$ColorFilterLruCache;
    //   6: iload_0
    //   7: aload_1
    //   8: invokevirtual get : (ILandroid/graphics/PorterDuff$Mode;)Landroid/graphics/PorterDuffColorFilter;
    //   11: astore_3
    //   12: aload_3
    //   13: astore_2
    //   14: aload_3
    //   15: ifnonnull -> 38
    //   18: new android/graphics/PorterDuffColorFilter
    //   21: astore_2
    //   22: aload_2
    //   23: iload_0
    //   24: aload_1
    //   25: invokespecial <init> : (ILandroid/graphics/PorterDuff$Mode;)V
    //   28: getstatic androidx/appcompat/widget/AppCompatDrawableManager.COLOR_FILTER_CACHE : Landroidx/appcompat/widget/AppCompatDrawableManager$ColorFilterLruCache;
    //   31: iload_0
    //   32: aload_1
    //   33: aload_2
    //   34: invokevirtual put : (ILandroid/graphics/PorterDuff$Mode;Landroid/graphics/PorterDuffColorFilter;)Landroid/graphics/PorterDuffColorFilter;
    //   37: pop
    //   38: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   40: monitorexit
    //   41: aload_2
    //   42: areturn
    //   43: astore_1
    //   44: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   3	12	43	finally
    //   18	38	43	finally
  }
  
  private ColorStateList getTintListFromCache(Context paramContext, int paramInt) {
    WeakHashMap<Context, SparseArrayCompat<ColorStateList>> weakHashMap = this.mTintLists;
    ColorStateList colorStateList2 = null;
    ColorStateList colorStateList1 = colorStateList2;
    if (weakHashMap != null) {
      SparseArrayCompat sparseArrayCompat = weakHashMap.get(paramContext);
      colorStateList1 = colorStateList2;
      if (sparseArrayCompat != null)
        colorStateList1 = (ColorStateList)sparseArrayCompat.get(paramInt); 
    } 
    return colorStateList1;
  }
  
  static PorterDuff.Mode getTintMode(int paramInt) {
    PorterDuff.Mode mode;
    if (paramInt == R.drawable.abc_switch_thumb_material) {
      mode = PorterDuff.Mode.MULTIPLY;
    } else {
      mode = null;
    } 
    return mode;
  }
  
  private static void installDefaultInflateDelegates(AppCompatDrawableManager paramAppCompatDrawableManager) {
    if (Build.VERSION.SDK_INT < 24) {
      paramAppCompatDrawableManager.addDelegate("vector", new VdcInflateDelegate());
      paramAppCompatDrawableManager.addDelegate("animated-vector", new AvdcInflateDelegate());
      paramAppCompatDrawableManager.addDelegate("animated-selector", new AsldcInflateDelegate());
    } 
  }
  
  private static boolean isVectorDrawable(Drawable paramDrawable) {
    return (paramDrawable instanceof VectorDrawableCompat || "android.graphics.drawable.VectorDrawable".equals(paramDrawable.getClass().getName()));
  }
  
  private Drawable loadDrawableFromDelegates(Context paramContext, int paramInt) {
    ArrayMap<String, InflateDelegate> arrayMap = this.mDelegates;
    if (arrayMap != null && !arrayMap.isEmpty()) {
      SparseArrayCompat<String> sparseArrayCompat = this.mKnownDrawableIdTags;
      if (sparseArrayCompat != null) {
        String str = (String)sparseArrayCompat.get(paramInt);
        if ("appcompat_skip_skip".equals(str) || (str != null && this.mDelegates.get(str) == null))
          return null; 
      } else {
        this.mKnownDrawableIdTags = new SparseArrayCompat();
      } 
      if (this.mTypedValue == null)
        this.mTypedValue = new TypedValue(); 
      TypedValue typedValue = this.mTypedValue;
      Resources resources = paramContext.getResources();
      resources.getValue(paramInt, typedValue, true);
      long l = createCacheKey(typedValue);
      Drawable drawable2 = getCachedDrawable(paramContext, l);
      if (drawable2 != null)
        return drawable2; 
      CharSequence charSequence = typedValue.string;
      Drawable drawable1 = drawable2;
      if (charSequence != null) {
        drawable1 = drawable2;
        if (charSequence.toString().endsWith(".xml")) {
          drawable1 = drawable2;
          try {
            int i;
            XmlResourceParser xmlResourceParser = resources.getXml(paramInt);
            drawable1 = drawable2;
            AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
            while (true) {
              drawable1 = drawable2;
              i = xmlResourceParser.next();
              if (i != 2 && i != 1)
                continue; 
              break;
            } 
            if (i == 2) {
              drawable1 = drawable2;
              String str = xmlResourceParser.getName();
              drawable1 = drawable2;
              this.mKnownDrawableIdTags.append(paramInt, str);
              drawable1 = drawable2;
              InflateDelegate inflateDelegate = (InflateDelegate)this.mDelegates.get(str);
              Drawable drawable = drawable2;
              if (inflateDelegate != null) {
                drawable1 = drawable2;
                drawable = inflateDelegate.createFromXmlInner(paramContext, (XmlPullParser)xmlResourceParser, attributeSet, paramContext.getTheme());
              } 
              drawable1 = drawable;
              if (drawable != null) {
                drawable1 = drawable;
                drawable.setChangingConfigurations(typedValue.changingConfigurations);
                drawable1 = drawable;
                addDrawableToCache(paramContext, l, drawable);
                drawable1 = drawable;
              } 
            } else {
              drawable1 = drawable2;
              XmlPullParserException xmlPullParserException = new XmlPullParserException();
              drawable1 = drawable2;
              this("No start tag found");
              drawable1 = drawable2;
              throw xmlPullParserException;
            } 
          } catch (Exception exception) {}
        } 
      } 
      if (drawable1 == null)
        this.mKnownDrawableIdTags.append(paramInt, "appcompat_skip_skip"); 
      return drawable1;
    } 
    return null;
  }
  
  private static void setPorterDuffColorFilter(Drawable paramDrawable, int paramInt, PorterDuff.Mode paramMode) {
    Drawable drawable = paramDrawable;
    if (DrawableUtils.canSafelyMutateDrawable(paramDrawable))
      drawable = paramDrawable.mutate(); 
    PorterDuff.Mode mode = paramMode;
    if (paramMode == null)
      mode = DEFAULT_MODE; 
    drawable.setColorFilter((ColorFilter)getPorterDuffColorFilter(paramInt, mode));
  }
  
  private Drawable tintDrawable(Context paramContext, int paramInt, boolean paramBoolean, Drawable paramDrawable) {
    Drawable drawable;
    PorterDuff.Mode mode1;
    PorterDuff.Mode mode2;
    ColorStateList colorStateList = getTintList(paramContext, paramInt);
    if (colorStateList != null) {
      drawable = paramDrawable;
      if (DrawableUtils.canSafelyMutateDrawable(paramDrawable))
        drawable = paramDrawable.mutate(); 
      drawable = DrawableCompat.wrap(drawable);
      DrawableCompat.setTintList(drawable, colorStateList);
      mode1 = getTintMode(paramInt);
      Drawable drawable1 = drawable;
      if (mode1 != null) {
        DrawableCompat.setTintMode(drawable, mode1);
        drawable1 = drawable;
      } 
    } else if (paramInt == R.drawable.abc_seekbar_track_material) {
      LayerDrawable layerDrawable = (LayerDrawable)mode1;
      setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlNormal), DEFAULT_MODE);
      setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlNormal), DEFAULT_MODE);
      setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlActivated), DEFAULT_MODE);
      mode2 = mode1;
    } else {
      if (paramInt == R.drawable.abc_ratingbar_material || paramInt == R.drawable.abc_ratingbar_indicator_material || paramInt == R.drawable.abc_ratingbar_small_material) {
        LayerDrawable layerDrawable = (LayerDrawable)mode1;
        setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor((Context)drawable, R.attr.colorControlNormal), DEFAULT_MODE);
        setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlActivated), DEFAULT_MODE);
        setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlActivated), DEFAULT_MODE);
        return (Drawable)mode1;
      } 
      mode2 = mode1;
      if (!tintDrawableUsingColorFilter((Context)drawable, paramInt, (Drawable)mode1)) {
        mode2 = mode1;
        if (paramBoolean)
          mode2 = null; 
      } 
    } 
    return (Drawable)mode2;
  }
  
  static void tintDrawable(Drawable paramDrawable, TintInfo paramTintInfo, int[] paramArrayOfint) {
    if (DrawableUtils.canSafelyMutateDrawable(paramDrawable) && paramDrawable.mutate() != paramDrawable)
      return; 
    if (paramTintInfo.mHasTintList || paramTintInfo.mHasTintMode) {
      PorterDuff.Mode mode;
      ColorStateList colorStateList;
      if (paramTintInfo.mHasTintList) {
        colorStateList = paramTintInfo.mTintList;
      } else {
        colorStateList = null;
      } 
      if (paramTintInfo.mHasTintMode) {
        mode = paramTintInfo.mTintMode;
      } else {
        mode = DEFAULT_MODE;
      } 
      paramDrawable.setColorFilter((ColorFilter)createTintFilter(colorStateList, mode, paramArrayOfint));
    } else {
      paramDrawable.clearColorFilter();
    } 
    if (Build.VERSION.SDK_INT <= 23)
      paramDrawable.invalidateSelf(); 
  }
  
  static boolean tintDrawableUsingColorFilter(Context paramContext, int paramInt, Drawable paramDrawable) {
    // Byte code:
    //   0: getstatic androidx/appcompat/widget/AppCompatDrawableManager.DEFAULT_MODE : Landroid/graphics/PorterDuff$Mode;
    //   3: astore #6
    //   5: getstatic androidx/appcompat/widget/AppCompatDrawableManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL : [I
    //   8: iload_1
    //   9: invokestatic arrayContains : ([II)Z
    //   12: istore #5
    //   14: ldc_w 16842801
    //   17: istore_3
    //   18: iload #5
    //   20: ifeq -> 35
    //   23: getstatic androidx/appcompat/R$attr.colorControlNormal : I
    //   26: istore_1
    //   27: iconst_1
    //   28: istore #4
    //   30: iconst_m1
    //   31: istore_3
    //   32: goto -> 115
    //   35: getstatic androidx/appcompat/widget/AppCompatDrawableManager.COLORFILTER_COLOR_CONTROL_ACTIVATED : [I
    //   38: iload_1
    //   39: invokestatic arrayContains : ([II)Z
    //   42: ifeq -> 52
    //   45: getstatic androidx/appcompat/R$attr.colorControlActivated : I
    //   48: istore_1
    //   49: goto -> 27
    //   52: getstatic androidx/appcompat/widget/AppCompatDrawableManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY : [I
    //   55: iload_1
    //   56: invokestatic arrayContains : ([II)Z
    //   59: ifeq -> 72
    //   62: getstatic android/graphics/PorterDuff$Mode.MULTIPLY : Landroid/graphics/PorterDuff$Mode;
    //   65: astore #6
    //   67: iload_3
    //   68: istore_1
    //   69: goto -> 27
    //   72: iload_1
    //   73: getstatic androidx/appcompat/R$drawable.abc_list_divider_mtrl_alpha : I
    //   76: if_icmpne -> 96
    //   79: ldc_w 16842800
    //   82: istore_1
    //   83: ldc_w 40.8
    //   86: invokestatic round : (F)I
    //   89: istore_3
    //   90: iconst_1
    //   91: istore #4
    //   93: goto -> 115
    //   96: iload_1
    //   97: getstatic androidx/appcompat/R$drawable.abc_dialog_material_background : I
    //   100: if_icmpne -> 108
    //   103: iload_3
    //   104: istore_1
    //   105: goto -> 27
    //   108: iconst_0
    //   109: istore #4
    //   111: iconst_m1
    //   112: istore_3
    //   113: iconst_0
    //   114: istore_1
    //   115: iload #4
    //   117: ifeq -> 164
    //   120: aload_2
    //   121: astore #7
    //   123: aload_2
    //   124: invokestatic canSafelyMutateDrawable : (Landroid/graphics/drawable/Drawable;)Z
    //   127: ifeq -> 136
    //   130: aload_2
    //   131: invokevirtual mutate : ()Landroid/graphics/drawable/Drawable;
    //   134: astore #7
    //   136: aload #7
    //   138: aload_0
    //   139: iload_1
    //   140: invokestatic getThemeAttrColor : (Landroid/content/Context;I)I
    //   143: aload #6
    //   145: invokestatic getPorterDuffColorFilter : (ILandroid/graphics/PorterDuff$Mode;)Landroid/graphics/PorterDuffColorFilter;
    //   148: invokevirtual setColorFilter : (Landroid/graphics/ColorFilter;)V
    //   151: iload_3
    //   152: iconst_m1
    //   153: if_icmpeq -> 162
    //   156: aload #7
    //   158: iload_3
    //   159: invokevirtual setAlpha : (I)V
    //   162: iconst_1
    //   163: ireturn
    //   164: iconst_0
    //   165: ireturn
  }
  
  public Drawable getDrawable(Context paramContext, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_2
    //   5: iconst_0
    //   6: invokevirtual getDrawable : (Landroid/content/Context;IZ)Landroid/graphics/drawable/Drawable;
    //   9: astore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: aload_1
    //   13: areturn
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	14	finally
  }
  
  Drawable getDrawable(Context paramContext, int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokespecial checkVectorDrawableSetup : (Landroid/content/Context;)V
    //   7: aload_0
    //   8: aload_1
    //   9: iload_2
    //   10: invokespecial loadDrawableFromDelegates : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   13: astore #4
    //   15: aload #4
    //   17: astore #5
    //   19: aload #4
    //   21: ifnonnull -> 32
    //   24: aload_0
    //   25: aload_1
    //   26: iload_2
    //   27: invokespecial createDrawableIfNeeded : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   30: astore #5
    //   32: aload #5
    //   34: astore #4
    //   36: aload #5
    //   38: ifnonnull -> 48
    //   41: aload_1
    //   42: iload_2
    //   43: invokestatic getDrawable : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   46: astore #4
    //   48: aload #4
    //   50: astore #5
    //   52: aload #4
    //   54: ifnull -> 68
    //   57: aload_0
    //   58: aload_1
    //   59: iload_2
    //   60: iload_3
    //   61: aload #4
    //   63: invokespecial tintDrawable : (Landroid/content/Context;IZLandroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;
    //   66: astore #5
    //   68: aload #5
    //   70: ifnull -> 78
    //   73: aload #5
    //   75: invokestatic fixDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   78: aload_0
    //   79: monitorexit
    //   80: aload #5
    //   82: areturn
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	83	finally
    //   24	32	83	finally
    //   41	48	83	finally
    //   57	68	83	finally
    //   73	78	83	finally
  }
  
  ColorStateList getTintList(Context paramContext, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_2
    //   5: invokespecial getTintListFromCache : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   8: astore_3
    //   9: aload_3
    //   10: astore #4
    //   12: aload_3
    //   13: ifnonnull -> 239
    //   16: iload_2
    //   17: getstatic androidx/appcompat/R$drawable.abc_edit_text_material : I
    //   20: if_icmpne -> 34
    //   23: aload_1
    //   24: getstatic androidx/appcompat/R$color.abc_tint_edittext : I
    //   27: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   30: astore_3
    //   31: goto -> 222
    //   34: iload_2
    //   35: getstatic androidx/appcompat/R$drawable.abc_switch_track_mtrl_alpha : I
    //   38: if_icmpne -> 52
    //   41: aload_1
    //   42: getstatic androidx/appcompat/R$color.abc_tint_switch_track : I
    //   45: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   48: astore_3
    //   49: goto -> 222
    //   52: iload_2
    //   53: getstatic androidx/appcompat/R$drawable.abc_switch_thumb_material : I
    //   56: if_icmpne -> 68
    //   59: aload_0
    //   60: aload_1
    //   61: invokespecial createSwitchThumbColorStateList : (Landroid/content/Context;)Landroid/content/res/ColorStateList;
    //   64: astore_3
    //   65: goto -> 222
    //   68: iload_2
    //   69: getstatic androidx/appcompat/R$drawable.abc_btn_default_mtrl_shape : I
    //   72: if_icmpne -> 84
    //   75: aload_0
    //   76: aload_1
    //   77: invokespecial createDefaultButtonColorStateList : (Landroid/content/Context;)Landroid/content/res/ColorStateList;
    //   80: astore_3
    //   81: goto -> 222
    //   84: iload_2
    //   85: getstatic androidx/appcompat/R$drawable.abc_btn_borderless_material : I
    //   88: if_icmpne -> 100
    //   91: aload_0
    //   92: aload_1
    //   93: invokespecial createBorderlessButtonColorStateList : (Landroid/content/Context;)Landroid/content/res/ColorStateList;
    //   96: astore_3
    //   97: goto -> 222
    //   100: iload_2
    //   101: getstatic androidx/appcompat/R$drawable.abc_btn_colored_material : I
    //   104: if_icmpne -> 116
    //   107: aload_0
    //   108: aload_1
    //   109: invokespecial createColoredButtonColorStateList : (Landroid/content/Context;)Landroid/content/res/ColorStateList;
    //   112: astore_3
    //   113: goto -> 222
    //   116: iload_2
    //   117: getstatic androidx/appcompat/R$drawable.abc_spinner_mtrl_am_alpha : I
    //   120: if_icmpeq -> 214
    //   123: iload_2
    //   124: getstatic androidx/appcompat/R$drawable.abc_spinner_textfield_background_material : I
    //   127: if_icmpne -> 133
    //   130: goto -> 214
    //   133: getstatic androidx/appcompat/widget/AppCompatDrawableManager.TINT_COLOR_CONTROL_NORMAL : [I
    //   136: iload_2
    //   137: invokestatic arrayContains : ([II)Z
    //   140: ifeq -> 154
    //   143: aload_1
    //   144: getstatic androidx/appcompat/R$attr.colorControlNormal : I
    //   147: invokestatic getThemeAttrColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   150: astore_3
    //   151: goto -> 222
    //   154: getstatic androidx/appcompat/widget/AppCompatDrawableManager.TINT_COLOR_CONTROL_STATE_LIST : [I
    //   157: iload_2
    //   158: invokestatic arrayContains : ([II)Z
    //   161: ifeq -> 175
    //   164: aload_1
    //   165: getstatic androidx/appcompat/R$color.abc_tint_default : I
    //   168: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   171: astore_3
    //   172: goto -> 222
    //   175: getstatic androidx/appcompat/widget/AppCompatDrawableManager.TINT_CHECKABLE_BUTTON_LIST : [I
    //   178: iload_2
    //   179: invokestatic arrayContains : ([II)Z
    //   182: ifeq -> 196
    //   185: aload_1
    //   186: getstatic androidx/appcompat/R$color.abc_tint_btn_checkable : I
    //   189: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   192: astore_3
    //   193: goto -> 222
    //   196: iload_2
    //   197: getstatic androidx/appcompat/R$drawable.abc_seekbar_thumb_material : I
    //   200: if_icmpne -> 222
    //   203: aload_1
    //   204: getstatic androidx/appcompat/R$color.abc_tint_seek_thumb : I
    //   207: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   210: astore_3
    //   211: goto -> 222
    //   214: aload_1
    //   215: getstatic androidx/appcompat/R$color.abc_tint_spinner : I
    //   218: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   221: astore_3
    //   222: aload_3
    //   223: astore #4
    //   225: aload_3
    //   226: ifnull -> 239
    //   229: aload_0
    //   230: aload_1
    //   231: iload_2
    //   232: aload_3
    //   233: invokespecial addTintListToCache : (Landroid/content/Context;ILandroid/content/res/ColorStateList;)V
    //   236: aload_3
    //   237: astore #4
    //   239: aload_0
    //   240: monitorexit
    //   241: aload #4
    //   243: areturn
    //   244: astore_1
    //   245: aload_0
    //   246: monitorexit
    //   247: aload_1
    //   248: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	244	finally
    //   16	31	244	finally
    //   34	49	244	finally
    //   52	65	244	finally
    //   68	81	244	finally
    //   84	97	244	finally
    //   100	113	244	finally
    //   116	130	244	finally
    //   133	151	244	finally
    //   154	172	244	finally
    //   175	193	244	finally
    //   196	211	244	finally
    //   214	222	244	finally
    //   229	236	244	finally
  }
  
  public void onConfigurationChanged(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mDrawableCaches : Ljava/util/WeakHashMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast androidx/collection/LongSparseArray
    //   13: astore_1
    //   14: aload_1
    //   15: ifnull -> 22
    //   18: aload_1
    //   19: invokevirtual clear : ()V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	25	finally
    //   18	22	25	finally
  }
  
  Drawable onDrawableLoadedFromResources(Context paramContext, VectorEnabledTintResources paramVectorEnabledTintResources, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_3
    //   5: invokespecial loadDrawableFromDelegates : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   8: astore #5
    //   10: aload #5
    //   12: astore #4
    //   14: aload #5
    //   16: ifnonnull -> 26
    //   19: aload_2
    //   20: iload_3
    //   21: invokevirtual superGetDrawable : (I)Landroid/graphics/drawable/Drawable;
    //   24: astore #4
    //   26: aload #4
    //   28: ifnull -> 45
    //   31: aload_0
    //   32: aload_1
    //   33: iload_3
    //   34: iconst_0
    //   35: aload #4
    //   37: invokespecial tintDrawable : (Landroid/content/Context;IZLandroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: areturn
    //   45: aload_0
    //   46: monitorexit
    //   47: aconst_null
    //   48: areturn
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	49	finally
    //   19	26	49	finally
    //   31	41	49	finally
  }
  
  static class AsldcInflateDelegate implements InflateDelegate {
    public Drawable createFromXmlInner(Context param1Context, XmlPullParser param1XmlPullParser, AttributeSet param1AttributeSet, Resources.Theme param1Theme) {
      try {
        return (Drawable)AnimatedStateListDrawableCompat.createFromXmlInner(param1Context, param1Context.getResources(), param1XmlPullParser, param1AttributeSet, param1Theme);
      } catch (Exception exception) {
        return null;
      } 
    }
  }
  
  private static class AvdcInflateDelegate implements InflateDelegate {
    public Drawable createFromXmlInner(Context param1Context, XmlPullParser param1XmlPullParser, AttributeSet param1AttributeSet, Resources.Theme param1Theme) {
      try {
        return (Drawable)AnimatedVectorDrawableCompat.createFromXmlInner(param1Context, param1Context.getResources(), param1XmlPullParser, param1AttributeSet, param1Theme);
      } catch (Exception exception) {
        return null;
      } 
    }
  }
  
  private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
    public ColorFilterLruCache(int param1Int) {
      super(param1Int);
    }
    
    private static int generateCacheKey(int param1Int, PorterDuff.Mode param1Mode) {
      return (param1Int + 31) * 31 + param1Mode.hashCode();
    }
    
    PorterDuffColorFilter get(int param1Int, PorterDuff.Mode param1Mode) {
      return (PorterDuffColorFilter)get(Integer.valueOf(generateCacheKey(param1Int, param1Mode)));
    }
    
    PorterDuffColorFilter put(int param1Int, PorterDuff.Mode param1Mode, PorterDuffColorFilter param1PorterDuffColorFilter) {
      return (PorterDuffColorFilter)put(Integer.valueOf(generateCacheKey(param1Int, param1Mode)), param1PorterDuffColorFilter);
    }
  }
  
  private static interface InflateDelegate {
    Drawable createFromXmlInner(Context param1Context, XmlPullParser param1XmlPullParser, AttributeSet param1AttributeSet, Resources.Theme param1Theme);
  }
  
  private static class VdcInflateDelegate implements InflateDelegate {
    public Drawable createFromXmlInner(Context param1Context, XmlPullParser param1XmlPullParser, AttributeSet param1AttributeSet, Resources.Theme param1Theme) {
      try {
        return (Drawable)VectorDrawableCompat.createFromXmlInner(param1Context.getResources(), param1XmlPullParser, param1AttributeSet, param1Theme);
      } catch (Exception exception) {
        return null;
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/AppCompatDrawableManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */