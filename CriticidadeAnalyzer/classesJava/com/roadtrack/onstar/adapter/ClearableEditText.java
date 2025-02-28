package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.Utilities;

public class ClearableEditText extends RelativeLayout {
  private Button btn_clear;
  
  private EditText edit_text;
  
  private LayoutInflater inflater = null;
  
  private OnAddTextChangedCETListener mOnAfterAddTextChangedCET;
  
  OnClickClearableEditTextListener mOnClickEditTextListener;
  
  OnClearableEditTextListener mOnKeyEditTextListener;
  
  public ClearableEditText(Context paramContext) {
    super(paramContext);
    initViews();
  }
  
  public ClearableEditText(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initViews();
  }
  
  public ClearableEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    initViews();
  }
  
  private void showHideClearButton() {
    this.edit_text.addTextChangedListener(new TextWatcher() {
          final ClearableEditText this$0;
          
          public void afterTextChanged(Editable param1Editable) {
            if (ClearableEditText.this.mOnAfterAddTextChangedCET != null)
              ClearableEditText.this.mOnAfterAddTextChangedCET.onAddTextChangedCET(param1Editable); 
          }
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            if (param1CharSequence.length() > 0) {
              ClearableEditText.this.btn_clear.setVisibility(4);
            } else {
              ClearableEditText.this.btn_clear.setVisibility(4);
            } 
          }
        });
    this.edit_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          final ClearableEditText this$0;
          
          public void onFocusChange(View param1View, boolean param1Boolean) {
            if (param1Boolean)
              if (ClearableEditText.this.edit_text.length() > 0 && ClearableEditText.this.edit_text.length() < 23) {
                ClearableEditText.this.btn_clear.setVisibility(4);
              } else {
                ClearableEditText.this.btn_clear.setVisibility(4);
              }  
          }
        });
  }
  
  public void clearFocusClearableText() {
    this.edit_text.clearFocus();
  }
  
  void clearText() {
    this.edit_text.setOnKeyListener(new View.OnKeyListener() {
          final ClearableEditText this$0;
          
          public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
            boolean bool;
            ClearableEditText.OnClearableEditTextListener onClearableEditTextListener = ClearableEditText.this.mOnKeyEditTextListener;
            if (onClearableEditTextListener != null) {
              bool = onClearableEditTextListener.onKeyEditText(param1View, param1Int, param1KeyEvent);
            } else {
              bool = false;
            } 
            return bool;
          }
        });
    this.btn_clear.setOnClickListener(new View.OnClickListener() {
          final ClearableEditText this$0;
          
          public void onClick(View param1View) {
            ClearableEditText.this.edit_text.setText("");
            ClearableEditText.OnClickClearableEditTextListener onClickClearableEditTextListener = ClearableEditText.this.mOnClickEditTextListener;
            if (onClickClearableEditTextListener != null)
              onClickClearableEditTextListener.onClickEditText(param1View); 
          }
        });
  }
  
  public Editable getText() {
    return this.edit_text.getText();
  }
  
  void initViews() {
    this.inflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
    this.inflater.inflate(2131427397, (ViewGroup)this, true);
    this.edit_text = (EditText)findViewById(2131296473);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(getContext(), stringsResourcesVO.login_main_txt_alguien_2, 2131690019);
    this.edit_text.setHint(str);
    this.btn_clear = (Button)findViewById(2131296471);
    this.btn_clear.setVisibility(4);
    setFocusable(true);
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
  
  public void setClickClearableEditTextEventListener(OnClickClearableEditTextListener paramOnClickClearableEditTextListener) {
    this.mOnClickEditTextListener = paramOnClickClearableEditTextListener;
  }
  
  public void setEnabledText(boolean paramBoolean) {
    this.edit_text.setEnabled(paramBoolean);
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
  
  public void setText(String paramString) {
    this.edit_text.setText(paramString);
  }
  
  public void setTypeInputEditText(int paramInt) {
    try {
      this.edit_text.setInputType(paramInt);
    } catch (Exception exception) {
      this.edit_text.setInputType(1);
    } 
  }
  
  public static interface OnAddTextChangedCETListener {
    void onAddTextChangedCET(Editable param1Editable);
  }
  
  public static interface OnClearableEditTextListener {
    boolean onKeyEditText(View param1View, int param1Int, KeyEvent param1KeyEvent);
  }
  
  public static interface OnClickClearableEditTextListener {
    void onClickEditText(View param1View);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/ClearableEditText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */