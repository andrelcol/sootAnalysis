package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableCompat {
  private static Method sGetLayoutDirectionMethod;
  
  private static boolean sGetLayoutDirectionMethodFetched;
  
  private static Method sSetLayoutDirectionMethod;
  
  private static boolean sSetLayoutDirectionMethodFetched;
  
  public static void applyTheme(Drawable paramDrawable, Resources.Theme paramTheme) {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.applyTheme(paramTheme); 
  }
  
  public static boolean canApplyTheme(Drawable paramDrawable) {
    return (Build.VERSION.SDK_INT >= 21) ? paramDrawable.canApplyTheme() : false;
  }
  
  public static int getAlpha(Drawable paramDrawable) {
    return (Build.VERSION.SDK_INT >= 19) ? paramDrawable.getAlpha() : 0;
  }
  
  public static ColorFilter getColorFilter(Drawable paramDrawable) {
    return (Build.VERSION.SDK_INT >= 21) ? paramDrawable.getColorFilter() : null;
  }
  
  public static int getLayoutDirection(Drawable paramDrawable) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23)
      return paramDrawable.getLayoutDirection(); 
    if (i >= 17) {
      if (!sGetLayoutDirectionMethodFetched) {
        try {
          sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
          sGetLayoutDirectionMethod.setAccessible(true);
        } catch (NoSuchMethodException noSuchMethodException) {}
        sGetLayoutDirectionMethodFetched = true;
      } 
      Method method = sGetLayoutDirectionMethod;
      if (method != null)
        try {
          return ((Integer)method.invoke(paramDrawable, new Object[0])).intValue();
        } catch (Exception exception) {
          sGetLayoutDirectionMethod = null;
        }  
    } 
    return 0;
  }
  
  public static void inflate(Drawable paramDrawable, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    } else {
      paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
    } 
  }
  
  public static boolean isAutoMirrored(Drawable paramDrawable) {
    return (Build.VERSION.SDK_INT >= 19) ? paramDrawable.isAutoMirrored() : false;
  }
  
  @Deprecated
  public static void jumpToCurrentState(Drawable paramDrawable) {
    paramDrawable.jumpToCurrentState();
  }
  
  public static void setAutoMirrored(Drawable paramDrawable, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 19)
      paramDrawable.setAutoMirrored(paramBoolean); 
  }
  
  public static void setHotspot(Drawable paramDrawable, float paramFloat1, float paramFloat2) {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.setHotspot(paramFloat1, paramFloat2); 
  }
  
  public static void setHotspotBounds(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (Build.VERSION.SDK_INT >= 21)
      paramDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4); 
  }
  
  public static boolean setLayoutDirection(Drawable paramDrawable, int paramInt) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23)
      return paramDrawable.setLayoutDirection(paramInt); 
    if (i >= 17) {
      if (!sSetLayoutDirectionMethodFetched) {
        try {
          sSetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", new Class[] { int.class });
          sSetLayoutDirectionMethod.setAccessible(true);
        } catch (NoSuchMethodException noSuchMethodException) {}
        sSetLayoutDirectionMethodFetched = true;
      } 
      Method method = sSetLayoutDirectionMethod;
      if (method != null)
        try {
          method.invoke(paramDrawable, new Object[] { Integer.valueOf(paramInt) });
          return true;
        } catch (Exception exception) {
          sSetLayoutDirectionMethod = null;
        }  
    } 
    return false;
  }
  
  public static void setTint(Drawable paramDrawable, int paramInt) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setTint(paramInt);
    } else if (paramDrawable instanceof TintAwareDrawable) {
      ((TintAwareDrawable)paramDrawable).setTint(paramInt);
    } 
  }
  
  public static void setTintList(Drawable paramDrawable, ColorStateList paramColorStateList) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setTintList(paramColorStateList);
    } else if (paramDrawable instanceof TintAwareDrawable) {
      ((TintAwareDrawable)paramDrawable).setTintList(paramColorStateList);
    } 
  }
  
  public static void setTintMode(Drawable paramDrawable, PorterDuff.Mode paramMode) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setTintMode(paramMode);
    } else if (paramDrawable instanceof TintAwareDrawable) {
      ((TintAwareDrawable)paramDrawable).setTintMode(paramMode);
    } 
  }
  
  public static <T extends Drawable> T unwrap(Drawable paramDrawable) {
    Drawable drawable = paramDrawable;
    if (paramDrawable instanceof WrappedDrawable)
      drawable = ((WrappedDrawable)paramDrawable).getWrappedDrawable(); 
    return (T)drawable;
  }
  
  public static Drawable wrap(Drawable paramDrawable) {
    int i = Build.VERSION.SDK_INT;
    return (i >= 23) ? paramDrawable : ((i >= 21) ? (!(paramDrawable instanceof TintAwareDrawable) ? new WrappedDrawableApi21(paramDrawable) : paramDrawable) : (!(paramDrawable instanceof TintAwareDrawable) ? new WrappedDrawableApi14(paramDrawable) : paramDrawable));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/graphics/drawable/DrawableCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */