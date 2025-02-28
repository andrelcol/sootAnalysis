package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.ViewCompat;
import androidx.core.widget.PopupWindowCompat;
import java.lang.reflect.Method;

public class ListPopupWindow implements ShowableListMenu {
  private static Method sClipToWindowEnabledMethod;
  
  private static Method sGetMaxAvailableHeightMethod;
  
  private static Method sSetEpicenterBoundsMethod;
  
  private ListAdapter mAdapter;
  
  private Context mContext;
  
  private boolean mDropDownAlwaysVisible = false;
  
  private View mDropDownAnchorView;
  
  private int mDropDownGravity = 0;
  
  private int mDropDownHeight = -2;
  
  private int mDropDownHorizontalOffset;
  
  DropDownListView mDropDownList;
  
  private Drawable mDropDownListHighlight;
  
  private int mDropDownVerticalOffset;
  
  private boolean mDropDownVerticalOffsetSet;
  
  private int mDropDownWidth = -2;
  
  private int mDropDownWindowLayoutType = 1002;
  
  private Rect mEpicenterBounds;
  
  private boolean mForceIgnoreOutsideTouch = false;
  
  final Handler mHandler;
  
  private final ListSelectorHider mHideSelector = new ListSelectorHider();
  
  private AdapterView.OnItemClickListener mItemClickListener;
  
  private AdapterView.OnItemSelectedListener mItemSelectedListener;
  
  int mListItemExpandMaximum = Integer.MAX_VALUE;
  
  private boolean mModal;
  
  private DataSetObserver mObserver;
  
  private boolean mOverlapAnchor;
  
  private boolean mOverlapAnchorSet;
  
  PopupWindow mPopup;
  
  private int mPromptPosition = 0;
  
  private View mPromptView;
  
  final ResizePopupRunnable mResizePopupRunnable = new ResizePopupRunnable();
  
  private final PopupScrollListener mScrollListener = new PopupScrollListener();
  
  private final Rect mTempRect = new Rect();
  
  private final PopupTouchInterceptor mTouchInterceptor = new PopupTouchInterceptor();
  
  static {
    try {
      sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[] { boolean.class });
    } catch (NoSuchMethodException noSuchMethodException) {}
    try {
      sGetMaxAvailableHeightMethod = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[] { View.class, int.class, boolean.class });
    } catch (NoSuchMethodException noSuchMethodException) {}
    try {
      sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[] { Rect.class });
    } catch (NoSuchMethodException noSuchMethodException) {}
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    this.mContext = paramContext;
    this.mHandler = new Handler(paramContext.getMainLooper());
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ListPopupWindow, paramInt1, paramInt2);
    this.mDropDownHorizontalOffset = typedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
    this.mDropDownVerticalOffset = typedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
    if (this.mDropDownVerticalOffset != 0)
      this.mDropDownVerticalOffsetSet = true; 
    typedArray.recycle();
    this.mPopup = new AppCompatPopupWindow(paramContext, paramAttributeSet, paramInt1, paramInt2);
    this.mPopup.setInputMethodMode(1);
  }
  
  private int buildDropDown() {
    byte b1;
    byte b2;
    DropDownListView dropDownListView = this.mDropDownList;
    boolean bool = true;
    if (dropDownListView == null) {
      LinearLayout.LayoutParams layoutParams1;
      LinearLayout.LayoutParams layoutParams2;
      Context context = this.mContext;
      new Runnable() {
          final ListPopupWindow this$0;
          
          public void run() {
            View view = ListPopupWindow.this.getAnchorView();
            if (view != null && view.getWindowToken() != null)
              ListPopupWindow.this.show(); 
          }
        };
      this.mDropDownList = createDropDownListView(context, this.mModal ^ true);
      Drawable drawable1 = this.mDropDownListHighlight;
      if (drawable1 != null)
        this.mDropDownList.setSelector(drawable1); 
      this.mDropDownList.setAdapter(this.mAdapter);
      this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
      this.mDropDownList.setFocusable(true);
      this.mDropDownList.setFocusableInTouchMode(true);
      this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final ListPopupWindow this$0;
            
            public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
              if (param1Int != -1) {
                DropDownListView dropDownListView = ListPopupWindow.this.mDropDownList;
                if (dropDownListView != null)
                  dropDownListView.setListSelectionHidden(false); 
              } 
            }
            
            public void onNothingSelected(AdapterView<?> param1AdapterView) {}
          });
      this.mDropDownList.setOnScrollListener(this.mScrollListener);
      AdapterView.OnItemSelectedListener onItemSelectedListener = this.mItemSelectedListener;
      if (onItemSelectedListener != null)
        this.mDropDownList.setOnItemSelectedListener(onItemSelectedListener); 
      DropDownListView dropDownListView1 = this.mDropDownList;
      View view = this.mPromptView;
      if (view != null) {
        StringBuilder stringBuilder;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0F);
        b1 = this.mPromptPosition;
        if (b1 != 0) {
          if (b1 != 1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid hint position ");
            stringBuilder.append(this.mPromptPosition);
            stringBuilder.toString();
          } else {
            linearLayout.addView((View)stringBuilder, (ViewGroup.LayoutParams)layoutParams);
            linearLayout.addView(view);
          } 
        } else {
          linearLayout.addView(view);
          linearLayout.addView((View)stringBuilder, (ViewGroup.LayoutParams)layoutParams);
        } 
        int k = this.mDropDownWidth;
        if (k >= 0) {
          b1 = -2147483648;
        } else {
          k = 0;
          b1 = 0;
        } 
        view.measure(View.MeasureSpec.makeMeasureSpec(k, b1), 0);
        layoutParams2 = (LinearLayout.LayoutParams)view.getLayoutParams();
        b1 = view.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
      } else {
        b1 = 0;
        layoutParams1 = layoutParams2;
      } 
      this.mPopup.setContentView((View)layoutParams1);
    } else {
      ViewGroup viewGroup = (ViewGroup)this.mPopup.getContentView();
      View view = this.mPromptView;
      if (view != null) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)view.getLayoutParams();
        b1 = view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
      } else {
        b1 = 0;
      } 
    } 
    Drawable drawable = this.mPopup.getBackground();
    if (drawable != null) {
      drawable.getPadding(this.mTempRect);
      Rect rect = this.mTempRect;
      int m = rect.top;
      int k = rect.bottom + m;
      b2 = k;
      if (!this.mDropDownVerticalOffsetSet) {
        this.mDropDownVerticalOffset = -m;
        b2 = k;
      } 
    } else {
      this.mTempRect.setEmpty();
      b2 = 0;
    } 
    if (this.mPopup.getInputMethodMode() != 2)
      bool = false; 
    int j = getMaxAvailableHeight(getAnchorView(), this.mDropDownVerticalOffset, bool);
    if (this.mDropDownAlwaysVisible || this.mDropDownHeight == -1)
      return j + b2; 
    int i = this.mDropDownWidth;
    if (i != -2) {
      if (i != -1) {
        i = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
      } else {
        i = (this.mContext.getResources().getDisplayMetrics()).widthPixels;
        Rect rect = this.mTempRect;
        i = View.MeasureSpec.makeMeasureSpec(i - rect.left + rect.right, 1073741824);
      } 
    } else {
      i = (this.mContext.getResources().getDisplayMetrics()).widthPixels;
      Rect rect = this.mTempRect;
      i = View.MeasureSpec.makeMeasureSpec(i - rect.left + rect.right, -2147483648);
    } 
    j = this.mDropDownList.measureHeightOfChildrenCompat(i, 0, -1, j - b1, -1);
    i = b1;
    if (j > 0)
      i = b1 + b2 + this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom(); 
    return j + i;
  }
  
  private int getMaxAvailableHeight(View paramView, int paramInt, boolean paramBoolean) {
    Method method = sGetMaxAvailableHeightMethod;
    if (method != null)
      try {
        return ((Integer)method.invoke(this.mPopup, new Object[] { paramView, Integer.valueOf(paramInt), Boolean.valueOf(paramBoolean) })).intValue();
      } catch (Exception exception) {} 
    return this.mPopup.getMaxAvailableHeight(paramView, paramInt);
  }
  
  private void removePromptView() {
    View view = this.mPromptView;
    if (view != null) {
      ViewParent viewParent = view.getParent();
      if (viewParent instanceof ViewGroup)
        ((ViewGroup)viewParent).removeView(this.mPromptView); 
    } 
  }
  
  private void setPopupClipToScreenEnabled(boolean paramBoolean) {
    Method method = sClipToWindowEnabledMethod;
    if (method != null)
      try {
        method.invoke(this.mPopup, new Object[] { Boolean.valueOf(paramBoolean) });
      } catch (Exception exception) {} 
  }
  
  public void clearListSelection() {
    DropDownListView dropDownListView = this.mDropDownList;
    if (dropDownListView != null) {
      dropDownListView.setListSelectionHidden(true);
      dropDownListView.requestLayout();
    } 
  }
  
  DropDownListView createDropDownListView(Context paramContext, boolean paramBoolean) {
    return new DropDownListView(paramContext, paramBoolean);
  }
  
  public void dismiss() {
    this.mPopup.dismiss();
    removePromptView();
    this.mPopup.setContentView(null);
    this.mDropDownList = null;
    this.mHandler.removeCallbacks(this.mResizePopupRunnable);
  }
  
  public View getAnchorView() {
    return this.mDropDownAnchorView;
  }
  
  public Drawable getBackground() {
    return this.mPopup.getBackground();
  }
  
  public int getHorizontalOffset() {
    return this.mDropDownHorizontalOffset;
  }
  
  public ListView getListView() {
    return this.mDropDownList;
  }
  
  public int getVerticalOffset() {
    return !this.mDropDownVerticalOffsetSet ? 0 : this.mDropDownVerticalOffset;
  }
  
  public int getWidth() {
    return this.mDropDownWidth;
  }
  
  public boolean isInputMethodNotNeeded() {
    boolean bool;
    if (this.mPopup.getInputMethodMode() == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isModal() {
    return this.mModal;
  }
  
  public boolean isShowing() {
    return this.mPopup.isShowing();
  }
  
  public void setAdapter(ListAdapter paramListAdapter) {
    DataSetObserver dataSetObserver = this.mObserver;
    if (dataSetObserver == null) {
      this.mObserver = new PopupDataSetObserver();
    } else {
      ListAdapter listAdapter = this.mAdapter;
      if (listAdapter != null)
        listAdapter.unregisterDataSetObserver(dataSetObserver); 
    } 
    this.mAdapter = paramListAdapter;
    if (paramListAdapter != null)
      paramListAdapter.registerDataSetObserver(this.mObserver); 
    DropDownListView dropDownListView = this.mDropDownList;
    if (dropDownListView != null)
      dropDownListView.setAdapter(this.mAdapter); 
  }
  
  public void setAnchorView(View paramView) {
    this.mDropDownAnchorView = paramView;
  }
  
  public void setAnimationStyle(int paramInt) {
    this.mPopup.setAnimationStyle(paramInt);
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    this.mPopup.setBackgroundDrawable(paramDrawable);
  }
  
  public void setContentWidth(int paramInt) {
    Drawable drawable = this.mPopup.getBackground();
    if (drawable != null) {
      drawable.getPadding(this.mTempRect);
      Rect rect = this.mTempRect;
      this.mDropDownWidth = rect.left + rect.right + paramInt;
    } else {
      setWidth(paramInt);
    } 
  }
  
  public void setDropDownGravity(int paramInt) {
    this.mDropDownGravity = paramInt;
  }
  
  public void setEpicenterBounds(Rect paramRect) {
    this.mEpicenterBounds = paramRect;
  }
  
  public void setHorizontalOffset(int paramInt) {
    this.mDropDownHorizontalOffset = paramInt;
  }
  
  public void setInputMethodMode(int paramInt) {
    this.mPopup.setInputMethodMode(paramInt);
  }
  
  public void setModal(boolean paramBoolean) {
    this.mModal = paramBoolean;
    this.mPopup.setFocusable(paramBoolean);
  }
  
  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener) {
    this.mPopup.setOnDismissListener(paramOnDismissListener);
  }
  
  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener) {
    this.mItemClickListener = paramOnItemClickListener;
  }
  
  public void setOverlapAnchor(boolean paramBoolean) {
    this.mOverlapAnchorSet = true;
    this.mOverlapAnchor = paramBoolean;
  }
  
  public void setPromptPosition(int paramInt) {
    this.mPromptPosition = paramInt;
  }
  
  public void setSelection(int paramInt) {
    DropDownListView dropDownListView = this.mDropDownList;
    if (isShowing() && dropDownListView != null) {
      dropDownListView.setListSelectionHidden(false);
      dropDownListView.setSelection(paramInt);
      if (dropDownListView.getChoiceMode() != 0)
        dropDownListView.setItemChecked(paramInt, true); 
    } 
  }
  
  public void setVerticalOffset(int paramInt) {
    this.mDropDownVerticalOffset = paramInt;
    this.mDropDownVerticalOffsetSet = true;
  }
  
  public void setWidth(int paramInt) {
    this.mDropDownWidth = paramInt;
  }
  
  public void show() {
    int i = buildDropDown();
    boolean bool1 = isInputMethodNotNeeded();
    PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
    boolean bool2 = this.mPopup.isShowing();
    boolean bool = true;
    if (bool2) {
      int j;
      if (!ViewCompat.isAttachedToWindow(getAnchorView()))
        return; 
      int k = this.mDropDownWidth;
      if (k == -1) {
        j = -1;
      } else {
        j = k;
        if (k == -2)
          j = getAnchorView().getWidth(); 
      } 
      k = this.mDropDownHeight;
      if (k == -1) {
        if (!bool1)
          i = -1; 
        if (bool1) {
          PopupWindow popupWindow1 = this.mPopup;
          if (this.mDropDownWidth == -1) {
            k = -1;
          } else {
            k = 0;
          } 
          popupWindow1.setWidth(k);
          this.mPopup.setHeight(0);
        } else {
          PopupWindow popupWindow1 = this.mPopup;
          if (this.mDropDownWidth == -1) {
            k = -1;
          } else {
            k = 0;
          } 
          popupWindow1.setWidth(k);
          this.mPopup.setHeight(-1);
        } 
      } else if (k != -2) {
        i = k;
      } 
      PopupWindow popupWindow = this.mPopup;
      if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible)
        bool = false; 
      popupWindow.setOutsideTouchable(bool);
      popupWindow = this.mPopup;
      View view = getAnchorView();
      k = this.mDropDownHorizontalOffset;
      int m = this.mDropDownVerticalOffset;
      if (j < 0)
        j = -1; 
      if (i < 0)
        i = -1; 
      popupWindow.update(view, k, m, j, i);
    } else {
      int j;
      int k = this.mDropDownWidth;
      if (k == -1) {
        j = -1;
      } else {
        j = k;
        if (k == -2)
          j = getAnchorView().getWidth(); 
      } 
      k = this.mDropDownHeight;
      if (k == -1) {
        i = -1;
      } else if (k != -2) {
        i = k;
      } 
      this.mPopup.setWidth(j);
      this.mPopup.setHeight(i);
      setPopupClipToScreenEnabled(true);
      PopupWindow popupWindow = this.mPopup;
      if (!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible) {
        bool = true;
      } else {
        bool = false;
      } 
      popupWindow.setOutsideTouchable(bool);
      this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
      if (this.mOverlapAnchorSet)
        PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor); 
      Method method = sSetEpicenterBoundsMethod;
      if (method != null)
        try {
          method.invoke(this.mPopup, new Object[] { this.mEpicenterBounds });
        } catch (Exception exception) {} 
      PopupWindowCompat.showAsDropDown(this.mPopup, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
      this.mDropDownList.setSelection(-1);
      if (!this.mModal || this.mDropDownList.isInTouchMode())
        clearListSelection(); 
      if (!this.mModal)
        this.mHandler.post(this.mHideSelector); 
    } 
  }
  
  private class ListSelectorHider implements Runnable {
    final ListPopupWindow this$0;
    
    public void run() {
      ListPopupWindow.this.clearListSelection();
    }
  }
  
  private class PopupDataSetObserver extends DataSetObserver {
    final ListPopupWindow this$0;
    
    public void onChanged() {
      if (ListPopupWindow.this.isShowing())
        ListPopupWindow.this.show(); 
    }
    
    public void onInvalidated() {
      ListPopupWindow.this.dismiss();
    }
  }
  
  private class PopupScrollListener implements AbsListView.OnScrollListener {
    final ListPopupWindow this$0;
    
    public void onScroll(AbsListView param1AbsListView, int param1Int1, int param1Int2, int param1Int3) {}
    
    public void onScrollStateChanged(AbsListView param1AbsListView, int param1Int) {
      if (param1Int == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
        ListPopupWindow listPopupWindow = ListPopupWindow.this;
        listPopupWindow.mHandler.removeCallbacks(listPopupWindow.mResizePopupRunnable);
        ListPopupWindow.this.mResizePopupRunnable.run();
      } 
    }
  }
  
  private class PopupTouchInterceptor implements View.OnTouchListener {
    final ListPopupWindow this$0;
    
    public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
      int j = param1MotionEvent.getAction();
      int i = (int)param1MotionEvent.getX();
      int k = (int)param1MotionEvent.getY();
      if (j == 0) {
        PopupWindow popupWindow = ListPopupWindow.this.mPopup;
        if (popupWindow != null && popupWindow.isShowing() && i >= 0 && i < ListPopupWindow.this.mPopup.getWidth() && k >= 0 && k < ListPopupWindow.this.mPopup.getHeight()) {
          ListPopupWindow listPopupWindow = ListPopupWindow.this;
          listPopupWindow.mHandler.postDelayed(listPopupWindow.mResizePopupRunnable, 250L);
          return false;
        } 
      } 
      if (j == 1) {
        ListPopupWindow listPopupWindow = ListPopupWindow.this;
        listPopupWindow.mHandler.removeCallbacks(listPopupWindow.mResizePopupRunnable);
      } 
      return false;
    }
  }
  
  private class ResizePopupRunnable implements Runnable {
    final ListPopupWindow this$0;
    
    public void run() {
      DropDownListView dropDownListView = ListPopupWindow.this.mDropDownList;
      if (dropDownListView != null && ViewCompat.isAttachedToWindow((View)dropDownListView) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount()) {
        int i = ListPopupWindow.this.mDropDownList.getChildCount();
        ListPopupWindow listPopupWindow = ListPopupWindow.this;
        if (i <= listPopupWindow.mListItemExpandMaximum) {
          listPopupWindow.mPopup.setInputMethodMode(2);
          ListPopupWindow.this.show();
        } 
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/ListPopupWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */