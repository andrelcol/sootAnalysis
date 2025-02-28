package androidx.core.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import androidx.core.graphics.TypefaceCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
  public static Drawable getDrawable(Resources paramResources, int paramInt, Resources.Theme paramTheme) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 21) ? paramResources.getDrawable(paramInt, paramTheme) : paramResources.getDrawable(paramInt);
  }
  
  public static Typeface getFont(Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, FontCallback paramFontCallback) throws Resources.NotFoundException {
    return paramContext.isRestricted() ? null : loadFont(paramContext, paramInt1, paramTypedValue, paramInt2, paramFontCallback, null, true);
  }
  
  private static Typeface loadFont(Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, FontCallback paramFontCallback, Handler paramHandler, boolean paramBoolean) {
    Resources resources = paramContext.getResources();
    resources.getValue(paramInt1, paramTypedValue, true);
    Typeface typeface = loadFont(paramContext, resources, paramTypedValue, paramInt1, paramInt2, paramFontCallback, paramHandler, paramBoolean);
    if (typeface != null || paramFontCallback != null)
      return typeface; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Font resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt1));
    stringBuilder.append(" could not be retrieved.");
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  private static Typeface loadFont(Context paramContext, Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, FontCallback paramFontCallback, Handler paramHandler, boolean paramBoolean) {
    String str;
    CharSequence charSequence = paramTypedValue.string;
    if (charSequence != null) {
      str = charSequence.toString();
      if (!str.startsWith("res/")) {
        if (paramFontCallback != null)
          paramFontCallback.callbackFailAsync(-3, paramHandler); 
        return null;
      } 
      Typeface typeface = TypefaceCompat.findFromCache(paramResources, paramInt1, paramInt2);
      if (typeface != null) {
        if (paramFontCallback != null)
          paramFontCallback.callbackSuccessAsync(typeface, paramHandler); 
        return typeface;
      } 
      try {
        if (str.toLowerCase().endsWith(".xml")) {
          FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry = FontResourcesParserCompat.parse((XmlPullParser)paramResources.getXml(paramInt1), paramResources);
          if (familyResourceEntry == null) {
            if (paramFontCallback != null)
              paramFontCallback.callbackFailAsync(-3, paramHandler); 
            return null;
          } 
          return TypefaceCompat.createFromResourcesFamilyXml(paramContext, familyResourceEntry, paramResources, paramInt1, paramInt2, paramFontCallback, paramHandler, paramBoolean);
        } 
        Typeface typeface1 = TypefaceCompat.createFromResourcesFontFile(paramContext, paramResources, paramInt1, str, paramInt2);
        if (paramFontCallback != null)
          if (typeface1 != null) {
            paramFontCallback.callbackSuccessAsync(typeface1, paramHandler);
          } else {
            paramFontCallback.callbackFailAsync(-3, paramHandler);
          }  
        return typeface1;
      } catch (XmlPullParserException xmlPullParserException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to parse xml resource ");
        stringBuilder1.append(str);
        stringBuilder1.toString();
      } catch (IOException iOException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Failed to read xml resource ");
        stringBuilder1.append(str);
        stringBuilder1.toString();
      } 
      if (paramFontCallback != null)
        paramFontCallback.callbackFailAsync(-3, paramHandler); 
      return null;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Resource \"");
    stringBuilder.append(paramResources.getResourceName(paramInt1));
    stringBuilder.append("\" (");
    stringBuilder.append(Integer.toHexString(paramInt1));
    stringBuilder.append(") is not a Font: ");
    stringBuilder.append(str);
    throw new Resources.NotFoundException(stringBuilder.toString());
  }
  
  public static abstract class FontCallback {
    public final void callbackFailAsync(final int reason, Handler param1Handler) {
      Handler handler = param1Handler;
      if (param1Handler == null)
        handler = new Handler(Looper.getMainLooper()); 
      handler.post(new Runnable() {
            final ResourcesCompat.FontCallback this$0;
            
            final int val$reason;
            
            public void run() {
              ResourcesCompat.FontCallback.this.onFontRetrievalFailed(reason);
            }
          });
    }
    
    public final void callbackSuccessAsync(final Typeface typeface, Handler param1Handler) {
      Handler handler = param1Handler;
      if (param1Handler == null)
        handler = new Handler(Looper.getMainLooper()); 
      handler.post(new Runnable() {
            final ResourcesCompat.FontCallback this$0;
            
            final Typeface val$typeface;
            
            public void run() {
              ResourcesCompat.FontCallback.this.onFontRetrieved(typeface);
            }
          });
    }
    
    public abstract void onFontRetrievalFailed(int param1Int);
    
    public abstract void onFontRetrieved(Typeface param1Typeface);
  }
  
  class null implements Runnable {
    final ResourcesCompat.FontCallback this$0;
    
    final Typeface val$typeface;
    
    public void run() {
      this.this$0.onFontRetrieved(typeface);
    }
  }
  
  class null implements Runnable {
    final ResourcesCompat.FontCallback this$0;
    
    final int val$reason;
    
    public void run() {
      this.this$0.onFontRetrievalFailed(reason);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/content/res/ResourcesCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */