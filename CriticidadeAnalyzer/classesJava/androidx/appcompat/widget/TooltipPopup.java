package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.R;

class TooltipPopup {
  private final View mContentView;
  
  private final Context mContext;
  
  private final WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
  
  private final TextView mMessageView;
  
  private final int[] mTmpAnchorPos = new int[2];
  
  private final int[] mTmpAppPos = new int[2];
  
  private final Rect mTmpDisplayFrame = new Rect();
  
  TooltipPopup(Context paramContext) {
    this.mContext = paramContext;
    this.mContentView = LayoutInflater.from(this.mContext).inflate(R.layout.abc_tooltip, null);
    this.mMessageView = (TextView)this.mContentView.findViewById(R.id.message);
    this.mLayoutParams.setTitle(TooltipPopup.class.getSimpleName());
    this.mLayoutParams.packageName = this.mContext.getPackageName();
    WindowManager.LayoutParams layoutParams = this.mLayoutParams;
    layoutParams.type = 1002;
    layoutParams.width = -2;
    layoutParams.height = -2;
    layoutParams.format = -3;
    layoutParams.windowAnimations = R.style.Animation_AppCompat_Tooltip;
    layoutParams.flags = 24;
  }
  
  private void computePosition(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, WindowManager.LayoutParams paramLayoutParams) {
    paramLayoutParams.token = paramView.getApplicationWindowToken();
    int i = this.mContext.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_threshold);
    if (paramView.getWidth() < i)
      paramInt1 = paramView.getWidth() / 2; 
    if (paramView.getHeight() >= i) {
      j = this.mContext.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_extra_offset);
      i = paramInt2 + j;
      j = paramInt2 - j;
      paramInt2 = i;
      i = j;
    } else {
      paramInt2 = paramView.getHeight();
      i = 0;
    } 
    paramLayoutParams.gravity = 49;
    Resources resources = this.mContext.getResources();
    if (paramBoolean) {
      j = R.dimen.tooltip_y_offset_touch;
    } else {
      j = R.dimen.tooltip_y_offset_non_touch;
    } 
    int k = resources.getDimensionPixelOffset(j);
    View view = getAppRootView(paramView);
    if (view == null)
      return; 
    view.getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
    Rect rect = this.mTmpDisplayFrame;
    if (rect.left < 0 && rect.top < 0) {
      Resources resources1 = this.mContext.getResources();
      j = resources1.getIdentifier("status_bar_height", "dimen", "android");
      if (j != 0) {
        j = resources1.getDimensionPixelSize(j);
      } else {
        j = 0;
      } 
      DisplayMetrics displayMetrics = resources1.getDisplayMetrics();
      this.mTmpDisplayFrame.set(0, j, displayMetrics.widthPixels, displayMetrics.heightPixels);
    } 
    view.getLocationOnScreen(this.mTmpAppPos);
    paramView.getLocationOnScreen(this.mTmpAnchorPos);
    int[] arrayOfInt2 = this.mTmpAnchorPos;
    int j = arrayOfInt2[0];
    int[] arrayOfInt1 = this.mTmpAppPos;
    arrayOfInt2[0] = j - arrayOfInt1[0];
    arrayOfInt2[1] = arrayOfInt2[1] - arrayOfInt1[1];
    paramLayoutParams.x = arrayOfInt2[0] + paramInt1 - view.getWidth() / 2;
    paramInt1 = View.MeasureSpec.makeMeasureSpec(0, 0);
    this.mContentView.measure(paramInt1, paramInt1);
    paramInt1 = this.mContentView.getMeasuredHeight();
    arrayOfInt1 = this.mTmpAnchorPos;
    i = arrayOfInt1[1] + i - k - paramInt1;
    paramInt2 = arrayOfInt1[1] + paramInt2 + k;
    if (paramBoolean) {
      if (i >= 0) {
        paramLayoutParams.y = i;
      } else {
        paramLayoutParams.y = paramInt2;
      } 
    } else if (paramInt1 + paramInt2 <= this.mTmpDisplayFrame.height()) {
      paramLayoutParams.y = paramInt2;
    } else {
      paramLayoutParams.y = i;
    } 
  }
  
  private static View getAppRootView(View paramView) {
    View view = paramView.getRootView();
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    if (layoutParams instanceof WindowManager.LayoutParams && ((WindowManager.LayoutParams)layoutParams).type == 2)
      return view; 
    for (Context context = paramView.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper)context).getBaseContext()) {
      if (context instanceof Activity)
        return ((Activity)context).getWindow().getDecorView(); 
    } 
    return view;
  }
  
  void hide() {
    if (!isShowing())
      return; 
    ((WindowManager)this.mContext.getSystemService("window")).removeView(this.mContentView);
  }
  
  boolean isShowing() {
    boolean bool;
    if (this.mContentView.getParent() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void show(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, CharSequence paramCharSequence) {
    if (isShowing())
      hide(); 
    this.mMessageView.setText(paramCharSequence);
    computePosition(paramView, paramInt1, paramInt2, paramBoolean, this.mLayoutParams);
    ((WindowManager)this.mContext.getSystemService("window")).addView(this.mContentView, (ViewGroup.LayoutParams)this.mLayoutParams);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/TooltipPopup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */