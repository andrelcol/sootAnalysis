package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import androidx.appcompat.R;

public class AlertDialog extends AppCompatDialog implements DialogInterface {
  final AlertController mAlert = new AlertController(getContext(), this, getWindow());
  
  protected AlertDialog(Context paramContext, int paramInt) {
    super(paramContext, resolveDialogTheme(paramContext, paramInt));
  }
  
  static int resolveDialogTheme(Context paramContext, int paramInt) {
    if ((paramInt >>> 24 & 0xFF) >= 1)
      return paramInt; 
    TypedValue typedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
    return typedValue.resourceId;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.mAlert.installContent();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    return this.mAlert.onKeyDown(paramInt, paramKeyEvent) ? true : super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    return this.mAlert.onKeyUp(paramInt, paramKeyEvent) ? true : super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public void setTitle(CharSequence paramCharSequence) {
    super.setTitle(paramCharSequence);
    this.mAlert.setTitle(paramCharSequence);
  }
  
  public static class Builder {
    private final AlertController.AlertParams P;
    
    private final int mTheme;
    
    public Builder(Context param1Context) {
      this(param1Context, AlertDialog.resolveDialogTheme(param1Context, 0));
    }
    
    public Builder(Context param1Context, int param1Int) {
      this.P = new AlertController.AlertParams((Context)new ContextThemeWrapper(param1Context, AlertDialog.resolveDialogTheme(param1Context, param1Int)));
      this.mTheme = param1Int;
    }
    
    public AlertDialog create() {
      AlertDialog alertDialog = new AlertDialog(this.P.mContext, this.mTheme);
      this.P.apply(alertDialog.mAlert);
      alertDialog.setCancelable(this.P.mCancelable);
      if (this.P.mCancelable)
        alertDialog.setCanceledOnTouchOutside(true); 
      alertDialog.setOnCancelListener(this.P.mOnCancelListener);
      alertDialog.setOnDismissListener(this.P.mOnDismissListener);
      DialogInterface.OnKeyListener onKeyListener = this.P.mOnKeyListener;
      if (onKeyListener != null)
        alertDialog.setOnKeyListener(onKeyListener); 
      return alertDialog;
    }
    
    public Context getContext() {
      return this.P.mContext;
    }
    
    public Builder setAdapter(ListAdapter param1ListAdapter, DialogInterface.OnClickListener param1OnClickListener) {
      AlertController.AlertParams alertParams = this.P;
      alertParams.mAdapter = param1ListAdapter;
      alertParams.mOnClickListener = param1OnClickListener;
      return this;
    }
    
    public Builder setCustomTitle(View param1View) {
      this.P.mCustomTitleView = param1View;
      return this;
    }
    
    public Builder setIcon(Drawable param1Drawable) {
      this.P.mIcon = param1Drawable;
      return this;
    }
    
    public Builder setOnKeyListener(DialogInterface.OnKeyListener param1OnKeyListener) {
      this.P.mOnKeyListener = param1OnKeyListener;
      return this;
    }
    
    public Builder setTitle(CharSequence param1CharSequence) {
      this.P.mTitle = param1CharSequence;
      return this;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/app/AlertDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */