package androidx.core.graphics;

import android.graphics.Typeface;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TypefaceCompatApi28Impl extends TypefaceCompatApi26Impl {
  protected Typeface createFromFamiliesWithDefault(Object paramObject) {
    try {
      Object object = Array.newInstance(this.mFontFamily, 1);
      Array.set(object, 0, paramObject);
      return (Typeface)this.mCreateFromFamiliesWithDefault.invoke(null, new Object[] { object, "sans-serif", Integer.valueOf(-1), Integer.valueOf(-1) });
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  protected Method obtainCreateFromFamiliesWithDefaultMethod(Class<?> paramClass) throws NoSuchMethodException {
    paramClass = Array.newInstance(paramClass, 1).getClass();
    Class<int> clazz = int.class;
    Method method = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] { paramClass, String.class, clazz, clazz });
    method.setAccessible(true);
    return method;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/graphics/TypefaceCompatApi28Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */