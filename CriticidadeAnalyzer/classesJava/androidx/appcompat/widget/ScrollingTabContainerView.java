package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionBarPolicy;

public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
  private boolean mAllowCollapse;
  
  private int mContentHeight;
  
  int mMaxTabWidth;
  
  private int mSelectedTabIndex;
  
  int mStackedTabMaxWidth;
  
  private TabClickListener mTabClickListener;
  
  LinearLayoutCompat mTabLayout;
  
  Runnable mTabSelector;
  
  private Spinner mTabSpinner;
  
  static {
    new DecelerateInterpolator();
  }
  
  private Spinner createSpinner() {
    AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, R.attr.actionDropDownStyle);
    appCompatSpinner.setLayoutParams((ViewGroup.LayoutParams)new LinearLayoutCompat.LayoutParams(-2, -1));
    appCompatSpinner.setOnItemSelectedListener(this);
    return appCompatSpinner;
  }
  
  private boolean isCollapsed() {
    boolean bool;
    Spinner spinner = this.mTabSpinner;
    if (spinner != null && spinner.getParent() == this) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void performCollapse() {
    if (isCollapsed())
      return; 
    if (this.mTabSpinner == null)
      this.mTabSpinner = createSpinner(); 
    removeView((View)this.mTabLayout);
    addView((View)this.mTabSpinner, new ViewGroup.LayoutParams(-2, -1));
    if (this.mTabSpinner.getAdapter() == null)
      this.mTabSpinner.setAdapter((SpinnerAdapter)new TabAdapter()); 
    Runnable runnable = this.mTabSelector;
    if (runnable != null) {
      removeCallbacks(runnable);
      this.mTabSelector = null;
    } 
    this.mTabSpinner.setSelection(this.mSelectedTabIndex);
  }
  
  private boolean performExpand() {
    if (!isCollapsed())
      return false; 
    removeView((View)this.mTabSpinner);
    addView((View)this.mTabLayout, new ViewGroup.LayoutParams(-2, -1));
    setTabSelected(this.mTabSpinner.getSelectedItemPosition());
    return false;
  }
  
  public void animateToTab(int paramInt) {
    final View tabView = this.mTabLayout.getChildAt(paramInt);
    Runnable runnable = this.mTabSelector;
    if (runnable != null)
      removeCallbacks(runnable); 
    this.mTabSelector = new Runnable() {
        final ScrollingTabContainerView this$0;
        
        final View val$tabView;
        
        public void run() {
          int i = tabView.getLeft();
          int j = (ScrollingTabContainerView.this.getWidth() - tabView.getWidth()) / 2;
          ScrollingTabContainerView.this.smoothScrollTo(i - j, 0);
          ScrollingTabContainerView.this.mTabSelector = null;
        }
      };
    post(this.mTabSelector);
  }
  
  TabView createTabView(ActionBar.Tab paramTab, boolean paramBoolean) {
    TabView tabView = new TabView(getContext(), paramTab, paramBoolean);
    if (paramBoolean) {
      tabView.setBackgroundDrawable(null);
      tabView.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, this.mContentHeight));
    } else {
      tabView.setFocusable(true);
      if (this.mTabClickListener == null)
        this.mTabClickListener = new TabClickListener(); 
      tabView.setOnClickListener(this.mTabClickListener);
    } 
    return tabView;
  }
  
  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    Runnable runnable = this.mTabSelector;
    if (runnable != null)
      post(runnable); 
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(getContext());
    setContentHeight(actionBarPolicy.getTabContainerHeight());
    this.mStackedTabMaxWidth = actionBarPolicy.getStackedTabMaxWidth();
  }
  
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    Runnable runnable = this.mTabSelector;
    if (runnable != null)
      removeCallbacks(runnable); 
  }
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    ((TabView)paramView).getTab().select();
  }
  
  public void onMeasure(int paramInt1, int paramInt2) {
    boolean bool;
    int j = View.MeasureSpec.getMode(paramInt1);
    paramInt2 = 1;
    if (j == 1073741824) {
      bool = true;
    } else {
      bool = false;
    } 
    setFillViewport(bool);
    int i = this.mTabLayout.getChildCount();
    if (i > 1 && (j == 1073741824 || j == Integer.MIN_VALUE)) {
      if (i > 2) {
        this.mMaxTabWidth = (int)(View.MeasureSpec.getSize(paramInt1) * 0.4F);
      } else {
        this.mMaxTabWidth = View.MeasureSpec.getSize(paramInt1) / 2;
      } 
      this.mMaxTabWidth = Math.min(this.mMaxTabWidth, this.mStackedTabMaxWidth);
    } else {
      this.mMaxTabWidth = -1;
    } 
    i = View.MeasureSpec.makeMeasureSpec(this.mContentHeight, 1073741824);
    if (bool || !this.mAllowCollapse)
      paramInt2 = 0; 
    if (paramInt2 != 0) {
      this.mTabLayout.measure(0, i);
      if (this.mTabLayout.getMeasuredWidth() > View.MeasureSpec.getSize(paramInt1)) {
        performCollapse();
      } else {
        performExpand();
      } 
    } else {
      performExpand();
    } 
    paramInt2 = getMeasuredWidth();
    super.onMeasure(paramInt1, i);
    paramInt1 = getMeasuredWidth();
    if (bool && paramInt2 != paramInt1)
      setTabSelected(this.mSelectedTabIndex); 
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
  
  public void setAllowCollapse(boolean paramBoolean) {
    this.mAllowCollapse = paramBoolean;
  }
  
  public void setContentHeight(int paramInt) {
    this.mContentHeight = paramInt;
    requestLayout();
  }
  
  public void setTabSelected(int paramInt) {
    this.mSelectedTabIndex = paramInt;
    int i = this.mTabLayout.getChildCount();
    for (byte b = 0; b < i; b++) {
      boolean bool;
      View view = this.mTabLayout.getChildAt(b);
      if (b == paramInt) {
        bool = true;
      } else {
        bool = false;
      } 
      view.setSelected(bool);
      if (bool)
        animateToTab(paramInt); 
    } 
    Spinner spinner = this.mTabSpinner;
    if (spinner != null && paramInt >= 0)
      spinner.setSelection(paramInt); 
  }
  
  private class TabAdapter extends BaseAdapter {
    final ScrollingTabContainerView this$0;
    
    public int getCount() {
      return ScrollingTabContainerView.this.mTabLayout.getChildCount();
    }
    
    public Object getItem(int param1Int) {
      return ((ScrollingTabContainerView.TabView)ScrollingTabContainerView.this.mTabLayout.getChildAt(param1Int)).getTab();
    }
    
    public long getItemId(int param1Int) {
      return param1Int;
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      ScrollingTabContainerView.TabView tabView;
      if (param1View == null) {
        tabView = ScrollingTabContainerView.this.createTabView((ActionBar.Tab)getItem(param1Int), true);
      } else {
        tabView.bindTab((ActionBar.Tab)getItem(param1Int));
      } 
      return (View)tabView;
    }
  }
  
  private class TabClickListener implements View.OnClickListener {
    final ScrollingTabContainerView this$0;
    
    public void onClick(View param1View) {
      ((ScrollingTabContainerView.TabView)param1View).getTab().select();
      int i = ScrollingTabContainerView.this.mTabLayout.getChildCount();
      for (byte b = 0; b < i; b++) {
        boolean bool;
        View view = ScrollingTabContainerView.this.mTabLayout.getChildAt(b);
        if (view == param1View) {
          bool = true;
        } else {
          bool = false;
        } 
        view.setSelected(bool);
      } 
    }
  }
  
  private class TabView extends LinearLayout {
    private final int[] BG_ATTRS = new int[] { 16842964 };
    
    private View mCustomView;
    
    private ImageView mIconView;
    
    private ActionBar.Tab mTab;
    
    private TextView mTextView;
    
    final ScrollingTabContainerView this$0;
    
    public TabView(Context param1Context, ActionBar.Tab param1Tab, boolean param1Boolean) {
      super(param1Context, null, R.attr.actionBarTabStyle);
      this.mTab = param1Tab;
      TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(param1Context, null, this.BG_ATTRS, R.attr.actionBarTabStyle, 0);
      if (tintTypedArray.hasValue(0))
        setBackgroundDrawable(tintTypedArray.getDrawable(0)); 
      tintTypedArray.recycle();
      if (param1Boolean)
        setGravity(8388627); 
      update();
    }
    
    public void bindTab(ActionBar.Tab param1Tab) {
      this.mTab = param1Tab;
      update();
    }
    
    public ActionBar.Tab getTab() {
      return this.mTab;
    }
    
    public void onInitializeAccessibilityEvent(AccessibilityEvent param1AccessibilityEvent) {
      super.onInitializeAccessibilityEvent(param1AccessibilityEvent);
      param1AccessibilityEvent.setClassName(ActionBar.Tab.class.getName());
    }
    
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo param1AccessibilityNodeInfo) {
      super.onInitializeAccessibilityNodeInfo(param1AccessibilityNodeInfo);
      param1AccessibilityNodeInfo.setClassName(ActionBar.Tab.class.getName());
    }
    
    public void onMeasure(int param1Int1, int param1Int2) {
      super.onMeasure(param1Int1, param1Int2);
      if (ScrollingTabContainerView.this.mMaxTabWidth > 0) {
        param1Int1 = getMeasuredWidth();
        int i = ScrollingTabContainerView.this.mMaxTabWidth;
        if (param1Int1 > i)
          super.onMeasure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), param1Int2); 
      } 
    }
    
    public void setSelected(boolean param1Boolean) {
      boolean bool;
      if (isSelected() != param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      super.setSelected(param1Boolean);
      if (bool && param1Boolean)
        sendAccessibilityEvent(4); 
    }
    
    public void update() {
      ActionBar.Tab tab = this.mTab;
      View view = tab.getCustomView();
      ViewParent viewParent = null;
      if (view != null) {
        viewParent = view.getParent();
        if (viewParent != this) {
          if (viewParent != null)
            ((ViewGroup)viewParent).removeView(view); 
          addView(view);
        } 
        this.mCustomView = view;
        TextView textView = this.mTextView;
        if (textView != null)
          textView.setVisibility(8); 
        ImageView imageView = this.mIconView;
        if (imageView != null) {
          imageView.setVisibility(8);
          this.mIconView.setImageDrawable(null);
        } 
      } else {
        CharSequence charSequence1;
        view = this.mCustomView;
        if (view != null) {
          removeView(view);
          this.mCustomView = null;
        } 
        Drawable drawable = tab.getIcon();
        CharSequence charSequence2 = tab.getText();
        if (drawable != null) {
          if (this.mIconView == null) {
            AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            appCompatImageView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            addView((View)appCompatImageView, 0);
            this.mIconView = appCompatImageView;
          } 
          this.mIconView.setImageDrawable(drawable);
          this.mIconView.setVisibility(0);
        } else {
          ImageView imageView1 = this.mIconView;
          if (imageView1 != null) {
            imageView1.setVisibility(8);
            this.mIconView.setImageDrawable(null);
          } 
        } 
        int i = TextUtils.isEmpty(charSequence2) ^ true;
        if (i != 0) {
          if (this.mTextView == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, R.attr.actionBarTabTextStyle);
            appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            appCompatTextView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            addView((View)appCompatTextView);
            this.mTextView = appCompatTextView;
          } 
          this.mTextView.setText(charSequence2);
          this.mTextView.setVisibility(0);
        } else {
          TextView textView = this.mTextView;
          if (textView != null) {
            textView.setVisibility(8);
            this.mTextView.setText(null);
          } 
        } 
        ImageView imageView = this.mIconView;
        if (imageView != null)
          imageView.setContentDescription(tab.getContentDescription()); 
        if (i == 0)
          charSequence1 = tab.getContentDescription(); 
        TooltipCompat.setTooltipText((View)this, charSequence1);
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/ScrollingTabContainerView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */