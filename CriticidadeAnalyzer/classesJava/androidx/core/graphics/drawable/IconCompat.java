package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  
  public byte[] mData;
  
  public int mInt1;
  
  public int mInt2;
  
  Object mObj1;
  
  public Parcelable mParcelable;
  
  public ColorStateList mTintList = null;
  
  PorterDuff.Mode mTintMode = DEFAULT_TINT_MODE;
  
  public String mTintModeStr;
  
  public int mType;
  
  private static int getResId(Icon paramIcon) {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getResId(); 
    try {
      return ((Integer)paramIcon.getClass().getMethod("getResId", new Class[0]).invoke(paramIcon, new Object[0])).intValue();
    } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException|NoSuchMethodException illegalAccessException) {
      return 0;
    } 
  }
  
  private static String getResPackage(Icon paramIcon) {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getResPackage(); 
    try {
      return (String)paramIcon.getClass().getMethod("getResPackage", new Class[0]).invoke(paramIcon, new Object[0]);
    } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException|NoSuchMethodException illegalAccessException) {
      return null;
    } 
  }
  
  private static String typeToString(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? "UNKNOWN" : "BITMAP_MASKABLE") : "URI") : "DATA") : "RESOURCE") : "BITMAP";
  }
  
  public int getResId() {
    if (this.mType == -1 && Build.VERSION.SDK_INT >= 23)
      return getResId((Icon)this.mObj1); 
    if (this.mType == 2)
      return this.mInt1; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("called getResId() on ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public String getResPackage() {
    if (this.mType == -1 && Build.VERSION.SDK_INT >= 23)
      return getResPackage((Icon)this.mObj1); 
    if (this.mType == 2)
      return ((String)this.mObj1).split(":", -1)[0]; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("called getResPackage() on ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void onPostParceling() {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield mTintModeStr : Ljava/lang/String;
    //   5: invokestatic valueOf : (Ljava/lang/String;)Landroid/graphics/PorterDuff$Mode;
    //   8: putfield mTintMode : Landroid/graphics/PorterDuff$Mode;
    //   11: aload_0
    //   12: getfield mType : I
    //   15: istore_1
    //   16: iload_1
    //   17: iconst_m1
    //   18: if_icmpeq -> 129
    //   21: iload_1
    //   22: iconst_1
    //   23: if_icmpeq -> 83
    //   26: iload_1
    //   27: iconst_2
    //   28: if_icmpeq -> 60
    //   31: iload_1
    //   32: iconst_3
    //   33: if_icmpeq -> 49
    //   36: iload_1
    //   37: iconst_4
    //   38: if_icmpeq -> 60
    //   41: iload_1
    //   42: iconst_5
    //   43: if_icmpeq -> 83
    //   46: goto -> 143
    //   49: aload_0
    //   50: aload_0
    //   51: getfield mData : [B
    //   54: putfield mObj1 : Ljava/lang/Object;
    //   57: goto -> 143
    //   60: aload_0
    //   61: new java/lang/String
    //   64: dup
    //   65: aload_0
    //   66: getfield mData : [B
    //   69: ldc 'UTF-16'
    //   71: invokestatic forName : (Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   74: invokespecial <init> : ([BLjava/nio/charset/Charset;)V
    //   77: putfield mObj1 : Ljava/lang/Object;
    //   80: goto -> 143
    //   83: aload_0
    //   84: getfield mParcelable : Landroid/os/Parcelable;
    //   87: astore_2
    //   88: aload_2
    //   89: ifnull -> 100
    //   92: aload_0
    //   93: aload_2
    //   94: putfield mObj1 : Ljava/lang/Object;
    //   97: goto -> 143
    //   100: aload_0
    //   101: getfield mData : [B
    //   104: astore_2
    //   105: aload_0
    //   106: aload_2
    //   107: putfield mObj1 : Ljava/lang/Object;
    //   110: aload_0
    //   111: iconst_3
    //   112: putfield mType : I
    //   115: aload_0
    //   116: iconst_0
    //   117: putfield mInt1 : I
    //   120: aload_0
    //   121: aload_2
    //   122: arraylength
    //   123: putfield mInt2 : I
    //   126: goto -> 143
    //   129: aload_0
    //   130: getfield mParcelable : Landroid/os/Parcelable;
    //   133: astore_2
    //   134: aload_2
    //   135: ifnull -> 144
    //   138: aload_0
    //   139: aload_2
    //   140: putfield mObj1 : Ljava/lang/Object;
    //   143: return
    //   144: new java/lang/IllegalArgumentException
    //   147: dup
    //   148: ldc 'Invalid icon'
    //   150: invokespecial <init> : (Ljava/lang/String;)V
    //   153: athrow
  }
  
  public void onPreParceling(boolean paramBoolean) {
    this.mTintModeStr = this.mTintMode.name();
    int i = this.mType;
    if (i != -1) {
      if (i != 1)
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i != 5)
                return; 
            } else {
              this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
              return;
            } 
          } else {
            this.mData = (byte[])this.mObj1;
            return;
          } 
        } else {
          this.mData = ((String)this.mObj1).getBytes(Charset.forName("UTF-16"));
          return;
        }  
      if (paramBoolean) {
        Bitmap bitmap = (Bitmap)this.mObj1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
        this.mData = byteArrayOutputStream.toByteArray();
      } else {
        this.mParcelable = (Parcelable)this.mObj1;
      } 
    } else {
      if (!paramBoolean) {
        this.mParcelable = (Parcelable)this.mObj1;
        return;
      } 
      throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
    } 
  }
  
  public String toString() {
    if (this.mType == -1)
      return String.valueOf(this.mObj1); 
    StringBuilder stringBuilder = new StringBuilder("Icon(typ=");
    stringBuilder.append(typeToString(this.mType));
    int i = this.mType;
    if (i != 1)
      if (i != 2) {
        if (i != 3) {
          if (i != 4) {
            if (i != 5)
              if (this.mTintList != null) {
                stringBuilder.append(" tint=");
                stringBuilder.append(this.mTintList);
              }  
          } else {
            stringBuilder.append(" uri=");
            stringBuilder.append(this.mObj1);
            if (this.mTintList != null) {
              stringBuilder.append(" tint=");
              stringBuilder.append(this.mTintList);
            } 
          } 
        } else {
          stringBuilder.append(" len=");
          stringBuilder.append(this.mInt1);
          if (this.mInt2 != 0) {
            stringBuilder.append(" off=");
            stringBuilder.append(this.mInt2);
          } 
          if (this.mTintList != null) {
            stringBuilder.append(" tint=");
            stringBuilder.append(this.mTintList);
          } 
        } 
      } else {
        stringBuilder.append(" pkg=");
        stringBuilder.append(getResPackage());
        stringBuilder.append(" id=");
        stringBuilder.append(String.format("0x%08x", new Object[] { Integer.valueOf(getResId()) }));
        if (this.mTintList != null) {
          stringBuilder.append(" tint=");
          stringBuilder.append(this.mTintList);
        } 
      }  
    stringBuilder.append(" size=");
    stringBuilder.append(((Bitmap)this.mObj1).getWidth());
    stringBuilder.append("x");
    stringBuilder.append(((Bitmap)this.mObj1).getHeight());
    if (this.mTintList != null) {
      stringBuilder.append(" tint=");
      stringBuilder.append(this.mTintList);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/graphics/drawable/IconCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */