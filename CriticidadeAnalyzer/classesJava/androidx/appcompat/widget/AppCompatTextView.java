package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.AutoSizeableTextView;
import androidx.core.widget.TextViewCompat;
import java.util.concurrent.Future;

public class AppCompatTextView extends TextView implements TintableBackgroundView, AutoSizeableTextView {
  private final AppCompatBackgroundHelper mBackgroundTintHelper = new AppCompatBackgroundHelper((View)this);
  
  private Future<PrecomputedTextCompat> mPrecomputedTextFuture;
  
  private final AppCompatTextHelper mTextHelper;
  
  public AppCompatTextView(Context paramContext) {
    this(paramContext, null);
  }
  
  public AppCompatTextView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 16842884);
  }
  
  public AppCompatTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    this.mBackgroundTintHelper.loadFromAttributes(paramAttributeSet, paramInt);
    this.mTextHelper = new AppCompatTextHelper(this);
    this.mTextHelper.loadFromAttributes(paramAttributeSet, paramInt);
    this.mTextHelper.applyCompoundDrawablesTints();
  }
  
  private void consumeTextFutureAndSetBlocking() {
    Future<PrecomputedTextCompat> future = this.mPrecomputedTextFuture;
    if (future != null)
      try {
        this.mPrecomputedTextFuture = null;
        TextViewCompat.setPrecomputedText(this, future.get());
      } catch (InterruptedException|java.util.concurrent.ExecutionException interruptedException) {} 
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.applySupportBackgroundTint(); 
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    if (appCompatTextHelper != null)
      appCompatTextHelper.applyCompoundDrawablesTints(); 
  }
  
  public int getAutoSizeMaxTextSize() {
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE)
      return super.getAutoSizeMaxTextSize(); 
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    return (appCompatTextHelper != null) ? appCompatTextHelper.getAutoSizeMaxTextSize() : -1;
  }
  
  public int getAutoSizeMinTextSize() {
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE)
      return super.getAutoSizeMinTextSize(); 
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    return (appCompatTextHelper != null) ? appCompatTextHelper.getAutoSizeMinTextSize() : -1;
  }
  
  public int getAutoSizeStepGranularity() {
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE)
      return super.getAutoSizeStepGranularity(); 
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    return (appCompatTextHelper != null) ? appCompatTextHelper.getAutoSizeStepGranularity() : -1;
  }
  
  public int[] getAutoSizeTextAvailableSizes() {
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE)
      return super.getAutoSizeTextAvailableSizes(); 
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    return (appCompatTextHelper != null) ? appCompatTextHelper.getAutoSizeTextAvailableSizes() : new int[0];
  }
  
  public int getAutoSizeTextType() {
    boolean bool1 = AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE;
    boolean bool = false;
    if (bool1) {
      if (super.getAutoSizeTextType() == 1)
        bool = true; 
      return bool;
    } 
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    return (appCompatTextHelper != null) ? appCompatTextHelper.getAutoSizeTextType() : 0;
  }
  
  public int getFirstBaselineToTopHeight() {
    return TextViewCompat.getFirstBaselineToTopHeight(this);
  }
  
  public int getLastBaselineToBottomHeight() {
    return TextViewCompat.getLastBaselineToBottomHeight(this);
  }
  
  public ColorStateList getSupportBackgroundTintList() {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null) {
      ColorStateList colorStateList = appCompatBackgroundHelper.getSupportBackgroundTintList();
    } else {
      appCompatBackgroundHelper = null;
    } 
    return (ColorStateList)appCompatBackgroundHelper;
  }
  
  public PorterDuff.Mode getSupportBackgroundTintMode() {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null) {
      PorterDuff.Mode mode = appCompatBackgroundHelper.getSupportBackgroundTintMode();
    } else {
      appCompatBackgroundHelper = null;
    } 
    return (PorterDuff.Mode)appCompatBackgroundHelper;
  }
  
  public CharSequence getText() {
    consumeTextFutureAndSetBlocking();
    return super.getText();
  }
  
  public PrecomputedTextCompat.Params getTextMetricsParamsCompat() {
    return TextViewCompat.getTextMetricsParams(this);
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo) {
    InputConnection inputConnection = super.onCreateInputConnection(paramEditorInfo);
    AppCompatHintHelper.onCreateInputConnection(inputConnection, paramEditorInfo, (View)this);
    return inputConnection;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    if (appCompatTextHelper != null)
      appCompatTextHelper.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4); 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    consumeTextFutureAndSetBlocking();
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
    super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    if (appCompatTextHelper != null && !AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && appCompatTextHelper.isAutoSizeEnabled())
      this.mTextHelper.autoSizeText(); 
  }
  
  public void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException {
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
      super.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
      if (appCompatTextHelper != null)
        appCompatTextHelper.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4); 
    } 
  }
  
  public void setAutoSizeTextTypeUniformWithPresetSizes(int[] paramArrayOfint, int paramInt) throws IllegalArgumentException {
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
      super.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfint, paramInt);
    } else {
      AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
      if (appCompatTextHelper != null)
        appCompatTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfint, paramInt); 
    } 
  }
  
  public void setAutoSizeTextTypeWithDefaults(int paramInt) {
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
      super.setAutoSizeTextTypeWithDefaults(paramInt);
    } else {
      AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
      if (appCompatTextHelper != null)
        appCompatTextHelper.setAutoSizeTextTypeWithDefaults(paramInt); 
    } 
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    super.setBackgroundDrawable(paramDrawable);
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.onSetBackgroundDrawable(paramDrawable); 
  }
  
  public void setBackgroundResource(int paramInt) {
    super.setBackgroundResource(paramInt);
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.onSetBackgroundResource(paramInt); 
  }
  
  public void setCustomSelectionActionModeCallback(ActionMode.Callback paramCallback) {
    super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, paramCallback));
  }
  
  public void setFirstBaselineToTopHeight(int paramInt) {
    if (Build.VERSION.SDK_INT >= 28) {
      super.setFirstBaselineToTopHeight(paramInt);
    } else {
      TextViewCompat.setFirstBaselineToTopHeight(this, paramInt);
    } 
  }
  
  public void setLastBaselineToBottomHeight(int paramInt) {
    if (Build.VERSION.SDK_INT >= 28) {
      super.setLastBaselineToBottomHeight(paramInt);
    } else {
      TextViewCompat.setLastBaselineToBottomHeight(this, paramInt);
    } 
  }
  
  public void setLineHeight(int paramInt) {
    TextViewCompat.setLineHeight(this, paramInt);
  }
  
  public void setPrecomputedText(PrecomputedTextCompat paramPrecomputedTextCompat) {
    TextViewCompat.setPrecomputedText(this, paramPrecomputedTextCompat);
  }
  
  public void setSupportBackgroundTintList(ColorStateList paramColorStateList) {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.setSupportBackgroundTintList(paramColorStateList); 
  }
  
  public void setSupportBackgroundTintMode(PorterDuff.Mode paramMode) {
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.setSupportBackgroundTintMode(paramMode); 
  }
  
  public void setTextAppearance(Context paramContext, int paramInt) {
    super.setTextAppearance(paramContext, paramInt);
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    if (appCompatTextHelper != null)
      appCompatTextHelper.onSetTextAppearance(paramContext, paramInt); 
  }
  
  public void setTextFuture(Future<PrecomputedTextCompat> paramFuture) {
    this.mPrecomputedTextFuture = paramFuture;
    requestLayout();
  }
  
  public void setTextMetricsParamsCompat(PrecomputedTextCompat.Params paramParams) {
    TextViewCompat.setTextMetricsParams(this, paramParams);
  }
  
  public void setTextSize(int paramInt, float paramFloat) {
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
      super.setTextSize(paramInt, paramFloat);
    } else {
      AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
      if (appCompatTextHelper != null)
        appCompatTextHelper.setTextSize(paramInt, paramFloat); 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/AppCompatTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */