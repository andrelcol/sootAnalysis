package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.widget.TextViewCompat;

public class AppCompatCheckedTextView extends CheckedTextView {
  private static final int[] TINT_ATTRS = new int[] { 16843016 };
  
  private final AppCompatTextHelper mTextHelper = new AppCompatTextHelper((TextView)this);
  
  public AppCompatCheckedTextView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 16843720);
  }
  
  public AppCompatCheckedTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    this.mTextHelper.loadFromAttributes(paramAttributeSet, paramInt);
    this.mTextHelper.applyCompoundDrawablesTints();
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
    setCheckMarkDrawable(tintTypedArray.getDrawable(0));
    tintTypedArray.recycle();
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    if (appCompatTextHelper != null)
      appCompatTextHelper.applyCompoundDrawablesTints(); 
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo) {
    InputConnection inputConnection = super.onCreateInputConnection(paramEditorInfo);
    AppCompatHintHelper.onCreateInputConnection(inputConnection, paramEditorInfo, (View)this);
    return inputConnection;
  }
  
  public void setCheckMarkDrawable(int paramInt) {
    setCheckMarkDrawable(AppCompatResources.getDrawable(getContext(), paramInt));
  }
  
  public void setCustomSelectionActionModeCallback(ActionMode.Callback paramCallback) {
    super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback((TextView)this, paramCallback));
  }
  
  public void setTextAppearance(Context paramContext, int paramInt) {
    super.setTextAppearance(paramContext, paramInt);
    AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
    if (appCompatTextHelper != null)
      appCompatTextHelper.onSetTextAppearance(paramContext, paramInt); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/AppCompatCheckedTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */