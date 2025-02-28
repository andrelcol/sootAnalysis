package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.Utilities;

public class ClearableEditTextPassword extends RelativeLayout {
  private Button btn_clear;
  
  private EditText edit_text;
  
  private LayoutInflater inflater = null;
  
  private OnAddTextChangedCETListener mOnAfterAddTextChangedCET;
  
  OnClearableEditTextListener mOnKeyEditTextListener;
  
  public ClearableEditTextPassword(Context paramContext) {
    super(paramContext);
    initViews();
  }
  
  public ClearableEditTextPassword(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initViews();
  }
  
  public ClearableEditTextPassword(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    initViews();
  }
  
  private void showHideClearButton() {
    this.edit_text.addTextChangedListener(new TextWatcher() {
          final ClearableEditTextPassword this$0;
          
          public void afterTextChanged(Editable param1Editable) {
            if (ClearableEditTextPassword.this.mOnAfterAddTextChangedCET != null)
              ClearableEditTextPassword.this.mOnAfterAddTextChangedCET.onAddTextChangedCET(param1Editable); 
          }
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (param1CharSequence.length() > 0) {
              ClearableEditTextPassword.this.btn_clear.setVisibility(0);
            } else {
              ClearableEditTextPassword.this.btn_clear.setVisibility(4);
            } 
          }
        });
    this.edit_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          final ClearableEditTextPassword this$0;
          
          public void onFocusChange(View param1View, boolean param1Boolean) {
            if (param1Boolean)
              if (ClearableEditTextPassword.this.edit_text.length() > 0 && ClearableEditTextPassword.this.edit_text.length() < 23) {
                ClearableEditTextPassword.this.btn_clear.setVisibility(0);
              } else {
                ClearableEditTextPassword.this.btn_clear.setVisibility(4);
              }  
          }
        });
  }
  
  void clearText() {
    this.edit_text.setOnKeyListener(new View.OnKeyListener() {
          final ClearableEditTextPassword this$0;
          
          public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
            boolean bool;
            ClearableEditTextPassword.OnClearableEditTextListener onClearableEditTextListener = ClearableEditTextPassword.this.mOnKeyEditTextListener;
            if (onClearableEditTextListener != null) {
              bool = onClearableEditTextListener.onKeyEditText(param1View, param1Int, param1KeyEvent);
            } else {
              bool = false;
            } 
            return bool;
          }
        });
    this.btn_clear.setOnClickListener(new View.OnClickListener() {
          final ClearableEditTextPassword this$0;
          
          public void onClick(View param1View) {
            HideReturnsTransformationMethod hideReturnsTransformationMethod;
            Drawable drawable;
            TransformationMethod transformationMethod = ClearableEditTextPassword.this.edit_text.getTransformationMethod();
            int i = ClearableEditTextPassword.this.edit_text.getSelectionStart();
            if (transformationMethod != PasswordTransformationMethod.getInstance()) {
              PasswordTransformationMethod passwordTransformationMethod = PasswordTransformationMethod.getInstance();
              drawable = Utilities.getDrawableFromConfigList(ClearableEditTextPassword.this.getContext(), DrawableResourcesVO.btn_eye_opened, 2131165339);
            } else {
              hideReturnsTransformationMethod = HideReturnsTransformationMethod.getInstance();
              drawable = Utilities.getDrawableFromConfigList(ClearableEditTextPassword.this.getContext(), DrawableResourcesVO.btn_eye_closed, 2131165338);
            } 
            ClearableEditTextPassword.this.edit_text.setTransformationMethod((TransformationMethod)hideReturnsTransformationMethod);
            ClearableEditTextPassword.this.edit_text.setSelection(i);
            Utilities.setBackgroundDrawable((View)ClearableEditTextPassword.this.btn_clear, drawable);
          }
        });
  }
  
  public int getSelectionStart() {
    return this.edit_text.getSelectionStart();
  }
  
  public Editable getText() {
    return this.edit_text.getText();
  }
  
  public TransformationMethod getTransformationMethod() {
    return this.edit_text.getTransformationMethod();
  }
  
  void initViews() {
    this.inflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
    this.inflater.inflate(2131427395, (ViewGroup)this, true);
    this.edit_text = (EditText)findViewById(2131296473);
    this.btn_clear = (Button)findViewById(2131296471);
    this.btn_clear.setVisibility(0);
    setFocusable(true);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(getContext(), stringsResourcesVO.login_main_txt_contrasena_3, 2131690020);
    this.edit_text.setHint(str);
    clearText();
    showHideClearButton();
  }
  
  public int lenght() {
    return this.edit_text.length();
  }
  
  public void setAddTextChangedClearableEditTextEventListener(OnAddTextChangedCETListener paramOnAddTextChangedCETListener) {
    this.mOnAfterAddTextChangedCET = paramOnAddTextChangedCETListener;
  }
  
  public void setClearableEditTextEventListener(OnClearableEditTextListener paramOnClearableEditTextListener) {
    this.mOnKeyEditTextListener = paramOnClearableEditTextListener;
  }
  
  public void setClickClearableEditTextEventListener(OnClickClearableEditTextListener paramOnClickClearableEditTextListener) {}
  
  public void setEnabledText(boolean paramBoolean) {
    this.edit_text.setEnabled(paramBoolean);
  }
  
  public void setFilters(InputFilter[] paramArrayOfInputFilter) {
    this.edit_text.setFilters(paramArrayOfInputFilter);
  }
  
  public void setFocusableInTouchMode(boolean paramBoolean) {
    this.edit_text.setFocusableInTouchMode(paramBoolean);
  }
  
  public void setMaxLength(int paramInt) {
    this.edit_text.setFilters(new InputFilter[] { (InputFilter)new InputFilter.LengthFilter(paramInt) });
  }
  
  public void setMaxLines(int paramInt) {
    this.edit_text.setMaxLines(paramInt);
  }
  
  public void setSelection(int paramInt) {
    this.edit_text.setSelection(paramInt);
  }
  
  public void setText(String paramString) {
    this.edit_text.setText(paramString);
  }
  
  public void setTransformationMethod(TransformationMethod paramTransformationMethod) {
    this.edit_text.setTransformationMethod(paramTransformationMethod);
  }
  
  public void setVisibilityEyed(int paramInt) {
    this.btn_clear.setVisibility(paramInt);
  }
  
  public static interface OnAddTextChangedCETListener {
    void onAddTextChangedCET(Editable param1Editable);
  }
  
  public static interface OnClearableEditTextListener {
    boolean onKeyEditText(View param1View, int param1Int, KeyEvent param1KeyEvent);
  }
  
  public static interface OnClickClearableEditTextListener {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/ClearableEditTextPassword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */