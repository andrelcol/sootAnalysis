package androidx.core.text;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;

public class PrecomputedTextCompat implements Spannable {
  private final Params mParams;
  
  private final Spannable mText;
  
  private final PrecomputedText mWrapped;
  
  public char charAt(int paramInt) {
    return this.mText.charAt(paramInt);
  }
  
  public Params getParams() {
    return this.mParams;
  }
  
  public PrecomputedText getPrecomputedText() {
    Spannable spannable = this.mText;
    return (spannable instanceof PrecomputedText) ? (PrecomputedText)spannable : null;
  }
  
  public int getSpanEnd(Object paramObject) {
    return this.mText.getSpanEnd(paramObject);
  }
  
  public int getSpanFlags(Object paramObject) {
    return this.mText.getSpanFlags(paramObject);
  }
  
  public int getSpanStart(Object paramObject) {
    return this.mText.getSpanStart(paramObject);
  }
  
  public <T> T[] getSpans(int paramInt1, int paramInt2, Class<T> paramClass) {
    return (T[])((Build.VERSION.SDK_INT >= 28) ? this.mWrapped.getSpans(paramInt1, paramInt2, paramClass) : this.mText.getSpans(paramInt1, paramInt2, paramClass));
  }
  
  public int length() {
    return this.mText.length();
  }
  
  public int nextSpanTransition(int paramInt1, int paramInt2, Class paramClass) {
    return this.mText.nextSpanTransition(paramInt1, paramInt2, paramClass);
  }
  
  public void removeSpan(Object paramObject) {
    if (!(paramObject instanceof android.text.style.MetricAffectingSpan)) {
      if (Build.VERSION.SDK_INT >= 28) {
        this.mWrapped.removeSpan(paramObject);
      } else {
        this.mText.removeSpan(paramObject);
      } 
      return;
    } 
    throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
  }
  
  public void setSpan(Object paramObject, int paramInt1, int paramInt2, int paramInt3) {
    if (!(paramObject instanceof android.text.style.MetricAffectingSpan)) {
      if (Build.VERSION.SDK_INT >= 28) {
        this.mWrapped.setSpan(paramObject, paramInt1, paramInt2, paramInt3);
      } else {
        this.mText.setSpan(paramObject, paramInt1, paramInt2, paramInt3);
      } 
      return;
    } 
    throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2) {
    return this.mText.subSequence(paramInt1, paramInt2);
  }
  
  public String toString() {
    return this.mText.toString();
  }
  
  public static final class Params {
    private final int mBreakStrategy;
    
    private final int mHyphenationFrequency;
    
    private final TextPaint mPaint;
    
    private final TextDirectionHeuristic mTextDir;
    
    final PrecomputedText.Params mWrapped;
    
    public Params(PrecomputedText.Params param1Params) {
      this.mPaint = param1Params.getTextPaint();
      this.mTextDir = param1Params.getTextDirection();
      this.mBreakStrategy = param1Params.getBreakStrategy();
      this.mHyphenationFrequency = param1Params.getHyphenationFrequency();
      this.mWrapped = param1Params;
    }
    
    Params(TextPaint param1TextPaint, TextDirectionHeuristic param1TextDirectionHeuristic, int param1Int1, int param1Int2) {
      if (Build.VERSION.SDK_INT >= 28) {
        this.mWrapped = (new PrecomputedText.Params.Builder(param1TextPaint)).setBreakStrategy(param1Int1).setHyphenationFrequency(param1Int2).setTextDirection(param1TextDirectionHeuristic).build();
      } else {
        this.mWrapped = null;
      } 
      this.mPaint = param1TextPaint;
      this.mTextDir = param1TextDirectionHeuristic;
      this.mBreakStrategy = param1Int1;
      this.mHyphenationFrequency = param1Int2;
    }
    
    public boolean equals(Object param1Object) {
      if (param1Object == this)
        return true; 
      if (param1Object == null || !(param1Object instanceof Params))
        return false; 
      param1Object = param1Object;
      PrecomputedText.Params params = this.mWrapped;
      if (params != null)
        return params.equals(((Params)param1Object).mWrapped); 
      if (Build.VERSION.SDK_INT >= 23) {
        if (this.mBreakStrategy != param1Object.getBreakStrategy())
          return false; 
        if (this.mHyphenationFrequency != param1Object.getHyphenationFrequency())
          return false; 
      } 
      if (Build.VERSION.SDK_INT >= 18 && this.mTextDir != param1Object.getTextDirection())
        return false; 
      if (this.mPaint.getTextSize() != param1Object.getTextPaint().getTextSize())
        return false; 
      if (this.mPaint.getTextScaleX() != param1Object.getTextPaint().getTextScaleX())
        return false; 
      if (this.mPaint.getTextSkewX() != param1Object.getTextPaint().getTextSkewX())
        return false; 
      if (Build.VERSION.SDK_INT >= 21) {
        if (this.mPaint.getLetterSpacing() != param1Object.getTextPaint().getLetterSpacing())
          return false; 
        if (!TextUtils.equals(this.mPaint.getFontFeatureSettings(), param1Object.getTextPaint().getFontFeatureSettings()))
          return false; 
      } 
      if (this.mPaint.getFlags() != param1Object.getTextPaint().getFlags())
        return false; 
      int i = Build.VERSION.SDK_INT;
      if (i >= 24) {
        if (!this.mPaint.getTextLocales().equals(param1Object.getTextPaint().getTextLocales()))
          return false; 
      } else if (i >= 17 && !this.mPaint.getTextLocale().equals(param1Object.getTextPaint().getTextLocale())) {
        return false;
      } 
      if (this.mPaint.getTypeface() == null) {
        if (param1Object.getTextPaint().getTypeface() != null)
          return false; 
      } else if (!this.mPaint.getTypeface().equals(param1Object.getTextPaint().getTypeface())) {
        return false;
      } 
      return true;
    }
    
    public int getBreakStrategy() {
      return this.mBreakStrategy;
    }
    
    public int getHyphenationFrequency() {
      return this.mHyphenationFrequency;
    }
    
    public TextDirectionHeuristic getTextDirection() {
      return this.mTextDir;
    }
    
    public TextPaint getTextPaint() {
      return this.mPaint;
    }
    
    public int hashCode() {
      int i = Build.VERSION.SDK_INT;
      return (i >= 24) ? ObjectsCompat.hash(new Object[] { 
            Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), 
            Integer.valueOf(this.mHyphenationFrequency) }) : ((i >= 21) ? ObjectsCompat.hash(new Object[] { 
            Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), 
            Integer.valueOf(this.mHyphenationFrequency) }) : ((i >= 18) ? ObjectsCompat.hash(new Object[] { Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency) }) : ((i >= 17) ? ObjectsCompat.hash(new Object[] { Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocale(), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency) }) : ObjectsCompat.hash(new Object[] { Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTypeface(), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency) }))));
    }
    
    public String toString() {
      StringBuilder stringBuilder1 = new StringBuilder("{");
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("textSize=");
      stringBuilder2.append(this.mPaint.getTextSize());
      stringBuilder1.append(stringBuilder2.toString());
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(", textScaleX=");
      stringBuilder2.append(this.mPaint.getTextScaleX());
      stringBuilder1.append(stringBuilder2.toString());
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(", textSkewX=");
      stringBuilder2.append(this.mPaint.getTextSkewX());
      stringBuilder1.append(stringBuilder2.toString());
      if (Build.VERSION.SDK_INT >= 21) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(", letterSpacing=");
        stringBuilder2.append(this.mPaint.getLetterSpacing());
        stringBuilder1.append(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(", elegantTextHeight=");
        stringBuilder2.append(this.mPaint.isElegantTextHeight());
        stringBuilder1.append(stringBuilder2.toString());
      } 
      int i = Build.VERSION.SDK_INT;
      if (i >= 24) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(", textLocale=");
        stringBuilder2.append(this.mPaint.getTextLocales());
        stringBuilder1.append(stringBuilder2.toString());
      } else if (i >= 17) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(", textLocale=");
        stringBuilder2.append(this.mPaint.getTextLocale());
        stringBuilder1.append(stringBuilder2.toString());
      } 
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(", typeface=");
      stringBuilder2.append(this.mPaint.getTypeface());
      stringBuilder1.append(stringBuilder2.toString());
      if (Build.VERSION.SDK_INT >= 26) {
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(", variationSettings=");
        stringBuilder2.append(this.mPaint.getFontVariationSettings());
        stringBuilder1.append(stringBuilder2.toString());
      } 
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(", textDir=");
      stringBuilder2.append(this.mTextDir);
      stringBuilder1.append(stringBuilder2.toString());
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(", breakStrategy=");
      stringBuilder2.append(this.mBreakStrategy);
      stringBuilder1.append(stringBuilder2.toString());
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(", hyphenationFrequency=");
      stringBuilder2.append(this.mHyphenationFrequency);
      stringBuilder1.append(stringBuilder2.toString());
      stringBuilder1.append("}");
      return stringBuilder1.toString();
    }
    
    public static class Builder {
      private int mBreakStrategy;
      
      private int mHyphenationFrequency;
      
      private final TextPaint mPaint;
      
      private TextDirectionHeuristic mTextDir;
      
      public Builder(TextPaint param2TextPaint) {
        this.mPaint = param2TextPaint;
        if (Build.VERSION.SDK_INT >= 23) {
          this.mBreakStrategy = 1;
          this.mHyphenationFrequency = 1;
        } else {
          this.mHyphenationFrequency = 0;
          this.mBreakStrategy = 0;
        } 
        if (Build.VERSION.SDK_INT >= 18) {
          this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        } else {
          this.mTextDir = null;
        } 
      }
      
      public PrecomputedTextCompat.Params build() {
        return new PrecomputedTextCompat.Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
      }
      
      public Builder setBreakStrategy(int param2Int) {
        this.mBreakStrategy = param2Int;
        return this;
      }
      
      public Builder setHyphenationFrequency(int param2Int) {
        this.mHyphenationFrequency = param2Int;
        return this;
      }
      
      public Builder setTextDirection(TextDirectionHeuristic param2TextDirectionHeuristic) {
        this.mTextDir = param2TextDirectionHeuristic;
        return this;
      }
    }
  }
  
  public static class Builder {
    private int mBreakStrategy;
    
    private int mHyphenationFrequency;
    
    private final TextPaint mPaint;
    
    private TextDirectionHeuristic mTextDir;
    
    public Builder(TextPaint param1TextPaint) {
      this.mPaint = param1TextPaint;
      if (Build.VERSION.SDK_INT >= 23) {
        this.mBreakStrategy = 1;
        this.mHyphenationFrequency = 1;
      } else {
        this.mHyphenationFrequency = 0;
        this.mBreakStrategy = 0;
      } 
      if (Build.VERSION.SDK_INT >= 18) {
        this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
      } else {
        this.mTextDir = null;
      } 
    }
    
    public PrecomputedTextCompat.Params build() {
      return new PrecomputedTextCompat.Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
    }
    
    public Builder setBreakStrategy(int param1Int) {
      this.mBreakStrategy = param1Int;
      return this;
    }
    
    public Builder setHyphenationFrequency(int param1Int) {
      this.mHyphenationFrequency = param1Int;
      return this;
    }
    
    public Builder setTextDirection(TextDirectionHeuristic param1TextDirectionHeuristic) {
      this.mTextDir = param1TextDirectionHeuristic;
      return this;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/text/PrecomputedTextCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */