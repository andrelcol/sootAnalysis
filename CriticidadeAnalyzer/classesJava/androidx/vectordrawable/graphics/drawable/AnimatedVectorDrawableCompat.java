package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.collection.ArrayMap;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable2Compat {
  private AnimatedVectorDrawableCompatState mAnimatedVectorState;
  
  private ArgbEvaluator mArgbEvaluator = null;
  
  final Drawable.Callback mCallback = new Drawable.Callback() {
      final AnimatedVectorDrawableCompat this$0;
      
      public void invalidateDrawable(Drawable param1Drawable) {
        AnimatedVectorDrawableCompat.this.invalidateSelf();
      }
      
      public void scheduleDrawable(Drawable param1Drawable, Runnable param1Runnable, long param1Long) {
        AnimatedVectorDrawableCompat.this.scheduleSelf(param1Runnable, param1Long);
      }
      
      public void unscheduleDrawable(Drawable param1Drawable, Runnable param1Runnable) {
        AnimatedVectorDrawableCompat.this.unscheduleSelf(param1Runnable);
      }
    };
  
  private Context mContext;
  
  AnimatedVectorDrawableCompat() {
    this(null, null, null);
  }
  
  private AnimatedVectorDrawableCompat(Context paramContext) {
    this(paramContext, null, null);
  }
  
  private AnimatedVectorDrawableCompat(Context paramContext, AnimatedVectorDrawableCompatState paramAnimatedVectorDrawableCompatState, Resources paramResources) {
    this.mContext = paramContext;
    if (paramAnimatedVectorDrawableCompatState != null) {
      this.mAnimatedVectorState = paramAnimatedVectorDrawableCompatState;
    } else {
      this.mAnimatedVectorState = new AnimatedVectorDrawableCompatState(paramContext, paramAnimatedVectorDrawableCompatState, this.mCallback, paramResources);
    } 
  }
  
  public static AnimatedVectorDrawableCompat createFromXmlInner(Context paramContext, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(paramContext);
    animatedVectorDrawableCompat.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return animatedVectorDrawableCompat;
  }
  
  private void setupAnimatorsForTarget(String paramString, Animator paramAnimator) {
    paramAnimator.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(paramString));
    if (Build.VERSION.SDK_INT < 21)
      setupColorAnimator(paramAnimator); 
    AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState = this.mAnimatedVectorState;
    if (animatedVectorDrawableCompatState.mAnimators == null) {
      animatedVectorDrawableCompatState.mAnimators = new ArrayList<Animator>();
      this.mAnimatedVectorState.mTargetNameMap = new ArrayMap();
    } 
    this.mAnimatedVectorState.mAnimators.add(paramAnimator);
    this.mAnimatedVectorState.mTargetNameMap.put(paramAnimator, paramString);
  }
  
  private void setupColorAnimator(Animator paramAnimator) {
    if (paramAnimator instanceof AnimatorSet) {
      ArrayList<Animator> arrayList = ((AnimatorSet)paramAnimator).getChildAnimations();
      if (arrayList != null)
        for (byte b = 0; b < arrayList.size(); b++)
          setupColorAnimator(arrayList.get(b));  
    } 
    if (paramAnimator instanceof ObjectAnimator) {
      ObjectAnimator objectAnimator = (ObjectAnimator)paramAnimator;
      String str = objectAnimator.getPropertyName();
      if ("fillColor".equals(str) || "strokeColor".equals(str)) {
        if (this.mArgbEvaluator == null)
          this.mArgbEvaluator = new ArgbEvaluator(); 
        objectAnimator.setEvaluator((TypeEvaluator)this.mArgbEvaluator);
      } 
    } 
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      DrawableCompat.applyTheme(drawable, paramTheme); 
  }
  
  public boolean canApplyTheme() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? DrawableCompat.canApplyTheme(drawable) : false;
  }
  
  public void draw(Canvas paramCanvas) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.draw(paramCanvas);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.draw(paramCanvas);
    if (this.mAnimatedVectorState.mAnimatorSet.isStarted())
      invalidateSelf(); 
  }
  
  public int getAlpha() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? DrawableCompat.getAlpha(drawable) : this.mAnimatedVectorState.mVectorDrawable.getAlpha();
  }
  
  public int getChangingConfigurations() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getChangingConfigurations() : (super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations);
  }
  
  public Drawable.ConstantState getConstantState() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null && Build.VERSION.SDK_INT >= 24) ? new AnimatedVectorDrawableDelegateState(drawable.getConstantState()) : null;
  }
  
  public int getIntrinsicHeight() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getIntrinsicHeight() : this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getIntrinsicWidth() : this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
  }
  
  public int getOpacity() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.getOpacity() : this.mAnimatedVectorState.mVectorDrawable.getOpacity();
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws XmlPullParserException, IOException {
    inflate(paramResources, paramXmlPullParser, paramAttributeSet, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.inflate(drawable, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    } 
    int i = paramXmlPullParser.getEventType();
    int j = paramXmlPullParser.getDepth();
    while (i != 1 && (paramXmlPullParser.getDepth() >= j + 1 || i != 3)) {
      if (i == 2) {
        TypedArray typedArray;
        String str = paramXmlPullParser.getName();
        if ("animated-vector".equals(str)) {
          typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
          i = typedArray.getResourceId(0, 0);
          if (i != 0) {
            VectorDrawableCompat vectorDrawableCompat2 = VectorDrawableCompat.create(paramResources, i, paramTheme);
            vectorDrawableCompat2.setAllowCaching(false);
            vectorDrawableCompat2.setCallback(this.mCallback);
            VectorDrawableCompat vectorDrawableCompat1 = this.mAnimatedVectorState.mVectorDrawable;
            if (vectorDrawableCompat1 != null)
              vectorDrawableCompat1.setCallback(null); 
            this.mAnimatedVectorState.mVectorDrawable = vectorDrawableCompat2;
          } 
          typedArray.recycle();
        } else if ("target".equals(typedArray)) {
          typedArray = paramResources.obtainAttributes(paramAttributeSet, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
          String str1 = typedArray.getString(0);
          i = typedArray.getResourceId(1, 0);
          if (i != 0) {
            Context context = this.mContext;
            if (context != null) {
              setupAnimatorsForTarget(str1, AnimatorInflaterCompat.loadAnimator(context, i));
            } else {
              typedArray.recycle();
              throw new IllegalStateException("Context can't be null when inflating animators");
            } 
          } 
          typedArray.recycle();
        } 
      } 
      i = paramXmlPullParser.next();
    } 
    this.mAnimatedVectorState.setupAnimatorSet();
  }
  
  public boolean isAutoMirrored() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? DrawableCompat.isAutoMirrored(drawable) : this.mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
  }
  
  public boolean isRunning() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? ((AnimatedVectorDrawable)drawable).isRunning() : this.mAnimatedVectorState.mAnimatorSet.isRunning();
  }
  
  public boolean isStateful() {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.isStateful() : this.mAnimatedVectorState.mVectorDrawable.isStateful();
  }
  
  public Drawable mutate() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      drawable.mutate(); 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.setBounds(paramRect);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setBounds(paramRect);
  }
  
  protected boolean onLevelChange(int paramInt) {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.setLevel(paramInt) : this.mAnimatedVectorState.mVectorDrawable.setLevel(paramInt);
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    Drawable drawable = this.mDelegateDrawable;
    return (drawable != null) ? drawable.setState(paramArrayOfint) : this.mAnimatedVectorState.mVectorDrawable.setState(paramArrayOfint);
  }
  
  public void setAlpha(int paramInt) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.setAlpha(paramInt);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setAlpha(paramInt);
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setAutoMirrored(drawable, paramBoolean);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setAutoMirrored(paramBoolean);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      drawable.setColorFilter(paramColorFilter);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setColorFilter(paramColorFilter);
  }
  
  public void setTint(int paramInt) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setTint(drawable, paramInt);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setTint(paramInt);
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setTintList(drawable, paramColorStateList);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setTintList(paramColorStateList);
  }
  
  public void setTintMode(PorterDuff.Mode paramMode) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      DrawableCompat.setTintMode(drawable, paramMode);
      return;
    } 
    this.mAnimatedVectorState.mVectorDrawable.setTintMode(paramMode);
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null)
      return drawable.setVisible(paramBoolean1, paramBoolean2); 
    this.mAnimatedVectorState.mVectorDrawable.setVisible(paramBoolean1, paramBoolean2);
    return super.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void start() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      ((AnimatedVectorDrawable)drawable).start();
      return;
    } 
    if (this.mAnimatedVectorState.mAnimatorSet.isStarted())
      return; 
    this.mAnimatedVectorState.mAnimatorSet.start();
    invalidateSelf();
  }
  
  public void stop() {
    Drawable drawable = this.mDelegateDrawable;
    if (drawable != null) {
      ((AnimatedVectorDrawable)drawable).stop();
      return;
    } 
    this.mAnimatedVectorState.mAnimatorSet.end();
  }
  
  private static class AnimatedVectorDrawableCompatState extends Drawable.ConstantState {
    AnimatorSet mAnimatorSet;
    
    ArrayList<Animator> mAnimators;
    
    int mChangingConfigurations;
    
    ArrayMap<Animator, String> mTargetNameMap;
    
    VectorDrawableCompat mVectorDrawable;
    
    public AnimatedVectorDrawableCompatState(Context param1Context, AnimatedVectorDrawableCompatState param1AnimatedVectorDrawableCompatState, Drawable.Callback param1Callback, Resources param1Resources) {
      if (param1AnimatedVectorDrawableCompatState != null) {
        this.mChangingConfigurations = param1AnimatedVectorDrawableCompatState.mChangingConfigurations;
        VectorDrawableCompat vectorDrawableCompat = param1AnimatedVectorDrawableCompatState.mVectorDrawable;
        byte b = 0;
        if (vectorDrawableCompat != null) {
          Drawable.ConstantState constantState = vectorDrawableCompat.getConstantState();
          if (param1Resources != null) {
            this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable(param1Resources);
          } else {
            this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable();
          } 
          VectorDrawableCompat vectorDrawableCompat1 = this.mVectorDrawable;
          vectorDrawableCompat1.mutate();
          this.mVectorDrawable = vectorDrawableCompat1;
          this.mVectorDrawable.setCallback(param1Callback);
          this.mVectorDrawable.setBounds(param1AnimatedVectorDrawableCompatState.mVectorDrawable.getBounds());
          this.mVectorDrawable.setAllowCaching(false);
        } 
        ArrayList<Animator> arrayList = param1AnimatedVectorDrawableCompatState.mAnimators;
        if (arrayList != null) {
          int i = arrayList.size();
          this.mAnimators = new ArrayList<Animator>(i);
          this.mTargetNameMap = new ArrayMap(i);
          while (b < i) {
            Animator animator2 = param1AnimatedVectorDrawableCompatState.mAnimators.get(b);
            Animator animator1 = animator2.clone();
            String str = (String)param1AnimatedVectorDrawableCompatState.mTargetNameMap.get(animator2);
            animator1.setTarget(this.mVectorDrawable.getTargetByName(str));
            this.mAnimators.add(animator1);
            this.mTargetNameMap.put(animator1, str);
            b++;
          } 
          setupAnimatorSet();
        } 
      } 
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConfigurations;
    }
    
    public Drawable newDrawable() {
      throw new IllegalStateException("No constant state support for SDK < 24.");
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      throw new IllegalStateException("No constant state support for SDK < 24.");
    }
    
    public void setupAnimatorSet() {
      if (this.mAnimatorSet == null)
        this.mAnimatorSet = new AnimatorSet(); 
      this.mAnimatorSet.playTogether(this.mAnimators);
    }
  }
  
  private static class AnimatedVectorDrawableDelegateState extends Drawable.ConstantState {
    private final Drawable.ConstantState mDelegateState;
    
    public AnimatedVectorDrawableDelegateState(Drawable.ConstantState param1ConstantState) {
      this.mDelegateState = param1ConstantState;
    }
    
    public boolean canApplyTheme() {
      return this.mDelegateState.canApplyTheme();
    }
    
    public int getChangingConfigurations() {
      return this.mDelegateState.getChangingConfigurations();
    }
    
    public Drawable newDrawable() {
      AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
      animatedVectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable();
      animatedVectorDrawableCompat.mDelegateDrawable.setCallback(animatedVectorDrawableCompat.mCallback);
      return animatedVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
      animatedVectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(param1Resources);
      animatedVectorDrawableCompat.mDelegateDrawable.setCallback(animatedVectorDrawableCompat.mCallback);
      return animatedVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources param1Resources, Resources.Theme param1Theme) {
      AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
      animatedVectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(param1Resources, param1Theme);
      animatedVectorDrawableCompat.mDelegateDrawable.setCallback(animatedVectorDrawableCompat.mCallback);
      return animatedVectorDrawableCompat;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/vectordrawable/graphics/drawable/AnimatedVectorDrawableCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */