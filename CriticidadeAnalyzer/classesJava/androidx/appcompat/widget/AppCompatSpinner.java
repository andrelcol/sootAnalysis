package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.TintableBackgroundView;
import androidx.core.view.ViewCompat;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
  private static final int[] ATTRS_ANDROID_SPINNERMODE = new int[] { 16843505 };
  
  private final AppCompatBackgroundHelper mBackgroundTintHelper;
  
  int mDropDownWidth;
  
  private ForwardingListener mForwardingListener;
  
  DropdownPopup mPopup;
  
  private final Context mPopupContext;
  
  private final boolean mPopupSet;
  
  private SpinnerAdapter mTempAdapter;
  
  final Rect mTempRect;
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.spinnerStyle);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    this(paramContext, paramAttributeSet, paramInt, -1);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    this(paramContext, paramAttributeSet, paramInt1, paramInt2, null);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, Resources.Theme paramTheme) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: iload_3
    //   4: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   7: aload_0
    //   8: new android/graphics/Rect
    //   11: dup
    //   12: invokespecial <init> : ()V
    //   15: putfield mTempRect : Landroid/graphics/Rect;
    //   18: aload_1
    //   19: aload_2
    //   20: getstatic androidx/appcompat/R$styleable.Spinner : [I
    //   23: iload_3
    //   24: iconst_0
    //   25: invokestatic obtainStyledAttributes : (Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroidx/appcompat/widget/TintTypedArray;
    //   28: astore #8
    //   30: aload_0
    //   31: new androidx/appcompat/widget/AppCompatBackgroundHelper
    //   34: dup
    //   35: aload_0
    //   36: invokespecial <init> : (Landroid/view/View;)V
    //   39: putfield mBackgroundTintHelper : Landroidx/appcompat/widget/AppCompatBackgroundHelper;
    //   42: aload #5
    //   44: ifnull -> 64
    //   47: aload_0
    //   48: new androidx/appcompat/view/ContextThemeWrapper
    //   51: dup
    //   52: aload_1
    //   53: aload #5
    //   55: invokespecial <init> : (Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
    //   58: putfield mPopupContext : Landroid/content/Context;
    //   61: goto -> 120
    //   64: aload #8
    //   66: getstatic androidx/appcompat/R$styleable.Spinner_popupTheme : I
    //   69: iconst_0
    //   70: invokevirtual getResourceId : (II)I
    //   73: istore #6
    //   75: iload #6
    //   77: ifeq -> 97
    //   80: aload_0
    //   81: new androidx/appcompat/view/ContextThemeWrapper
    //   84: dup
    //   85: aload_1
    //   86: iload #6
    //   88: invokespecial <init> : (Landroid/content/Context;I)V
    //   91: putfield mPopupContext : Landroid/content/Context;
    //   94: goto -> 120
    //   97: getstatic android/os/Build$VERSION.SDK_INT : I
    //   100: bipush #23
    //   102: if_icmpge -> 111
    //   105: aload_1
    //   106: astore #5
    //   108: goto -> 114
    //   111: aconst_null
    //   112: astore #5
    //   114: aload_0
    //   115: aload #5
    //   117: putfield mPopupContext : Landroid/content/Context;
    //   120: aload_0
    //   121: getfield mPopupContext : Landroid/content/Context;
    //   124: ifnull -> 336
    //   127: iload #4
    //   129: istore #7
    //   131: iload #4
    //   133: iconst_m1
    //   134: if_icmpne -> 233
    //   137: aload_1
    //   138: aload_2
    //   139: getstatic androidx/appcompat/widget/AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE : [I
    //   142: iload_3
    //   143: iconst_0
    //   144: invokevirtual obtainStyledAttributes : (Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
    //   147: astore #5
    //   149: iload #4
    //   151: istore #6
    //   153: aload #5
    //   155: iconst_0
    //   156: invokevirtual hasValue : (I)Z
    //   159: ifeq -> 171
    //   162: aload #5
    //   164: iconst_0
    //   165: iconst_0
    //   166: invokevirtual getInt : (II)I
    //   169: istore #6
    //   171: iload #6
    //   173: istore #7
    //   175: aload #5
    //   177: ifnull -> 233
    //   180: iload #6
    //   182: istore #4
    //   184: aload #5
    //   186: invokevirtual recycle : ()V
    //   189: iload #4
    //   191: istore #7
    //   193: goto -> 233
    //   196: astore_1
    //   197: goto -> 204
    //   200: astore_1
    //   201: aconst_null
    //   202: astore #5
    //   204: aload #5
    //   206: ifnull -> 214
    //   209: aload #5
    //   211: invokevirtual recycle : ()V
    //   214: aload_1
    //   215: athrow
    //   216: astore #5
    //   218: aconst_null
    //   219: astore #5
    //   221: iload #4
    //   223: istore #7
    //   225: aload #5
    //   227: ifnull -> 233
    //   230: goto -> 184
    //   233: iload #7
    //   235: iconst_1
    //   236: if_icmpne -> 336
    //   239: new androidx/appcompat/widget/AppCompatSpinner$DropdownPopup
    //   242: dup
    //   243: aload_0
    //   244: aload_0
    //   245: getfield mPopupContext : Landroid/content/Context;
    //   248: aload_2
    //   249: iload_3
    //   250: invokespecial <init> : (Landroidx/appcompat/widget/AppCompatSpinner;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   253: astore #5
    //   255: aload_0
    //   256: getfield mPopupContext : Landroid/content/Context;
    //   259: aload_2
    //   260: getstatic androidx/appcompat/R$styleable.Spinner : [I
    //   263: iload_3
    //   264: iconst_0
    //   265: invokestatic obtainStyledAttributes : (Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroidx/appcompat/widget/TintTypedArray;
    //   268: astore #9
    //   270: aload_0
    //   271: aload #9
    //   273: getstatic androidx/appcompat/R$styleable.Spinner_android_dropDownWidth : I
    //   276: bipush #-2
    //   278: invokevirtual getLayoutDimension : (II)I
    //   281: putfield mDropDownWidth : I
    //   284: aload #5
    //   286: aload #9
    //   288: getstatic androidx/appcompat/R$styleable.Spinner_android_popupBackground : I
    //   291: invokevirtual getDrawable : (I)Landroid/graphics/drawable/Drawable;
    //   294: invokevirtual setBackgroundDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   297: aload #5
    //   299: aload #8
    //   301: getstatic androidx/appcompat/R$styleable.Spinner_android_prompt : I
    //   304: invokevirtual getString : (I)Ljava/lang/String;
    //   307: invokevirtual setPromptText : (Ljava/lang/CharSequence;)V
    //   310: aload #9
    //   312: invokevirtual recycle : ()V
    //   315: aload_0
    //   316: aload #5
    //   318: putfield mPopup : Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;
    //   321: aload_0
    //   322: new androidx/appcompat/widget/AppCompatSpinner$1
    //   325: dup
    //   326: aload_0
    //   327: aload_0
    //   328: aload #5
    //   330: invokespecial <init> : (Landroidx/appcompat/widget/AppCompatSpinner;Landroid/view/View;Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;)V
    //   333: putfield mForwardingListener : Landroidx/appcompat/widget/ForwardingListener;
    //   336: aload #8
    //   338: getstatic androidx/appcompat/R$styleable.Spinner_android_entries : I
    //   341: invokevirtual getTextArray : (I)[Ljava/lang/CharSequence;
    //   344: astore #5
    //   346: aload #5
    //   348: ifnull -> 376
    //   351: new android/widget/ArrayAdapter
    //   354: dup
    //   355: aload_1
    //   356: ldc 17367048
    //   358: aload #5
    //   360: invokespecial <init> : (Landroid/content/Context;I[Ljava/lang/Object;)V
    //   363: astore_1
    //   364: aload_1
    //   365: getstatic androidx/appcompat/R$layout.support_simple_spinner_dropdown_item : I
    //   368: invokevirtual setDropDownViewResource : (I)V
    //   371: aload_0
    //   372: aload_1
    //   373: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   376: aload #8
    //   378: invokevirtual recycle : ()V
    //   381: aload_0
    //   382: iconst_1
    //   383: putfield mPopupSet : Z
    //   386: aload_0
    //   387: getfield mTempAdapter : Landroid/widget/SpinnerAdapter;
    //   390: astore_1
    //   391: aload_1
    //   392: ifnull -> 405
    //   395: aload_0
    //   396: aload_1
    //   397: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   400: aload_0
    //   401: aconst_null
    //   402: putfield mTempAdapter : Landroid/widget/SpinnerAdapter;
    //   405: aload_0
    //   406: getfield mBackgroundTintHelper : Landroidx/appcompat/widget/AppCompatBackgroundHelper;
    //   409: aload_2
    //   410: iload_3
    //   411: invokevirtual loadFromAttributes : (Landroid/util/AttributeSet;I)V
    //   414: return
    //   415: astore #9
    //   417: goto -> 221
    // Exception table:
    //   from	to	target	type
    //   137	149	216	java/lang/Exception
    //   137	149	200	finally
    //   153	171	415	java/lang/Exception
    //   153	171	196	finally
  }
  
  int compatMeasureContentWidth(SpinnerAdapter paramSpinnerAdapter, Drawable paramDrawable) {
    int k = 0;
    if (paramSpinnerAdapter == null)
      return 0; 
    int i1 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
    int n = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
    int i = Math.max(0, getSelectedItemPosition());
    int m = Math.min(paramSpinnerAdapter.getCount(), i + 15);
    int j = Math.max(0, i - 15 - m - i);
    View view = null;
    i = 0;
    while (j < m) {
      int i3 = paramSpinnerAdapter.getItemViewType(j);
      int i2 = k;
      if (i3 != k) {
        view = null;
        i2 = i3;
      } 
      view = paramSpinnerAdapter.getView(j, view, (ViewGroup)this);
      if (view.getLayoutParams() == null)
        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2)); 
      view.measure(i1, n);
      i = Math.max(i, view.getMeasuredWidth());
      j++;
      k = i2;
    } 
    j = i;
    if (paramDrawable != null) {
      paramDrawable.getPadding(this.mTempRect);
      Rect rect = this.mTempRect;
      j = i + rect.left + rect.right;
    } 
    return j;
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
    if (appCompatBackgroundHelper != null)
      appCompatBackgroundHelper.applySupportBackgroundTint(); 
  }
  
  public int getDropDownHorizontalOffset() {
    DropdownPopup dropdownPopup = this.mPopup;
    return (dropdownPopup != null) ? dropdownPopup.getHorizontalOffset() : ((Build.VERSION.SDK_INT >= 16) ? super.getDropDownHorizontalOffset() : 0);
  }
  
  public int getDropDownVerticalOffset() {
    DropdownPopup dropdownPopup = this.mPopup;
    return (dropdownPopup != null) ? dropdownPopup.getVerticalOffset() : ((Build.VERSION.SDK_INT >= 16) ? super.getDropDownVerticalOffset() : 0);
  }
  
  public int getDropDownWidth() {
    return (this.mPopup != null) ? this.mDropDownWidth : ((Build.VERSION.SDK_INT >= 16) ? super.getDropDownWidth() : 0);
  }
  
  public Drawable getPopupBackground() {
    DropdownPopup dropdownPopup = this.mPopup;
    return (dropdownPopup != null) ? dropdownPopup.getBackground() : ((Build.VERSION.SDK_INT >= 16) ? super.getPopupBackground() : null);
  }
  
  public Context getPopupContext() {
    return (this.mPopup != null) ? this.mPopupContext : ((Build.VERSION.SDK_INT >= 23) ? super.getPopupContext() : null);
  }
  
  public CharSequence getPrompt() {
    CharSequence charSequence;
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      charSequence = dropdownPopup.getHintText();
    } else {
      charSequence = super.getPrompt();
    } 
    return charSequence;
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
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null && dropdownPopup.isShowing())
      this.mPopup.dismiss(); 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mPopup != null && View.MeasureSpec.getMode(paramInt1) == Integer.MIN_VALUE)
      setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(paramInt1)), getMeasuredHeight()); 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    ForwardingListener forwardingListener = this.mForwardingListener;
    return (forwardingListener != null && forwardingListener.onTouch((View)this, paramMotionEvent)) ? true : super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean performClick() {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      if (!dropdownPopup.isShowing())
        this.mPopup.show(); 
      return true;
    } 
    return super.performClick();
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter) {
    if (!this.mPopupSet) {
      this.mTempAdapter = paramSpinnerAdapter;
      return;
    } 
    super.setAdapter(paramSpinnerAdapter);
    if (this.mPopup != null) {
      Context context2 = this.mPopupContext;
      Context context1 = context2;
      if (context2 == null)
        context1 = getContext(); 
      this.mPopup.setAdapter(new DropDownAdapter(paramSpinnerAdapter, context1.getTheme()));
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
  
  public void setDropDownHorizontalOffset(int paramInt) {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      dropdownPopup.setHorizontalOffset(paramInt);
    } else if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownHorizontalOffset(paramInt);
    } 
  }
  
  public void setDropDownVerticalOffset(int paramInt) {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      dropdownPopup.setVerticalOffset(paramInt);
    } else if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownVerticalOffset(paramInt);
    } 
  }
  
  public void setDropDownWidth(int paramInt) {
    if (this.mPopup != null) {
      this.mDropDownWidth = paramInt;
    } else if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownWidth(paramInt);
    } 
  }
  
  public void setPopupBackgroundDrawable(Drawable paramDrawable) {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      dropdownPopup.setBackgroundDrawable(paramDrawable);
    } else if (Build.VERSION.SDK_INT >= 16) {
      super.setPopupBackgroundDrawable(paramDrawable);
    } 
  }
  
  public void setPopupBackgroundResource(int paramInt) {
    setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), paramInt));
  }
  
  public void setPrompt(CharSequence paramCharSequence) {
    DropdownPopup dropdownPopup = this.mPopup;
    if (dropdownPopup != null) {
      dropdownPopup.setPromptText(paramCharSequence);
    } else {
      super.setPrompt(paramCharSequence);
    } 
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
  
  private static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
    private SpinnerAdapter mAdapter;
    
    private ListAdapter mListAdapter;
    
    public DropDownAdapter(SpinnerAdapter param1SpinnerAdapter, Resources.Theme param1Theme) {
      this.mAdapter = param1SpinnerAdapter;
      if (param1SpinnerAdapter instanceof ListAdapter)
        this.mListAdapter = (ListAdapter)param1SpinnerAdapter; 
      if (param1Theme != null) {
        ThemedSpinnerAdapter themedSpinnerAdapter;
        if (Build.VERSION.SDK_INT >= 23 && param1SpinnerAdapter instanceof ThemedSpinnerAdapter) {
          themedSpinnerAdapter = (ThemedSpinnerAdapter)param1SpinnerAdapter;
          if (themedSpinnerAdapter.getDropDownViewTheme() != param1Theme)
            themedSpinnerAdapter.setDropDownViewTheme(param1Theme); 
        } else if (themedSpinnerAdapter instanceof ThemedSpinnerAdapter) {
          ThemedSpinnerAdapter themedSpinnerAdapter1 = (ThemedSpinnerAdapter)themedSpinnerAdapter;
          if (themedSpinnerAdapter1.getDropDownViewTheme() == null)
            themedSpinnerAdapter1.setDropDownViewTheme(param1Theme); 
        } 
      } 
    }
    
    public boolean areAllItemsEnabled() {
      ListAdapter listAdapter = this.mListAdapter;
      return (listAdapter != null) ? listAdapter.areAllItemsEnabled() : true;
    }
    
    public int getCount() {
      int i;
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter == null) {
        i = 0;
      } else {
        i = spinnerAdapter.getCount();
      } 
      return i;
    }
    
    public View getDropDownView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter == null) {
        param1View = null;
      } else {
        param1View = spinnerAdapter.getDropDownView(param1Int, param1View, param1ViewGroup);
      } 
      return param1View;
    }
    
    public Object getItem(int param1Int) {
      Object object = this.mAdapter;
      if (object == null) {
        object = null;
      } else {
        object = object.getItem(param1Int);
      } 
      return object;
    }
    
    public long getItemId(int param1Int) {
      long l;
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter == null) {
        l = -1L;
      } else {
        l = spinnerAdapter.getItemId(param1Int);
      } 
      return l;
    }
    
    public int getItemViewType(int param1Int) {
      return 0;
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      return getDropDownView(param1Int, param1View, param1ViewGroup);
    }
    
    public int getViewTypeCount() {
      return 1;
    }
    
    public boolean hasStableIds() {
      boolean bool;
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter != null && spinnerAdapter.hasStableIds()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isEmpty() {
      boolean bool;
      if (getCount() == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isEnabled(int param1Int) {
      ListAdapter listAdapter = this.mListAdapter;
      return (listAdapter != null) ? listAdapter.isEnabled(param1Int) : true;
    }
    
    public void registerDataSetObserver(DataSetObserver param1DataSetObserver) {
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter != null)
        spinnerAdapter.registerDataSetObserver(param1DataSetObserver); 
    }
    
    public void unregisterDataSetObserver(DataSetObserver param1DataSetObserver) {
      SpinnerAdapter spinnerAdapter = this.mAdapter;
      if (spinnerAdapter != null)
        spinnerAdapter.unregisterDataSetObserver(param1DataSetObserver); 
    }
  }
  
  private class DropdownPopup extends ListPopupWindow {
    ListAdapter mAdapter;
    
    private CharSequence mHintText;
    
    private final Rect mVisibleRect = new Rect();
    
    final AppCompatSpinner this$0;
    
    public DropdownPopup(Context param1Context, AttributeSet param1AttributeSet, int param1Int) {
      super(param1Context, param1AttributeSet, param1Int);
      setAnchorView((View)AppCompatSpinner.this);
      setModal(true);
      setPromptPosition(0);
      setOnItemClickListener(new AdapterView.OnItemClickListener(AppCompatSpinner.this) {
            final AppCompatSpinner.DropdownPopup this$1;
            
            public void onItemClick(AdapterView<?> param2AdapterView, View param2View, int param2Int, long param2Long) {
              AppCompatSpinner.this.setSelection(param2Int);
              if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                AppCompatSpinner.DropdownPopup dropdownPopup = AppCompatSpinner.DropdownPopup.this;
                AppCompatSpinner.this.performItemClick(param2View, param2Int, dropdownPopup.mAdapter.getItemId(param2Int));
              } 
              AppCompatSpinner.DropdownPopup.this.dismiss();
            }
          });
    }
    
    void computeContentWidth() {
      Drawable drawable = getBackground();
      int i = 0;
      if (drawable != null) {
        drawable.getPadding(AppCompatSpinner.this.mTempRect);
        if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
          i = AppCompatSpinner.this.mTempRect.right;
        } else {
          i = -AppCompatSpinner.this.mTempRect.left;
        } 
      } else {
        Rect rect = AppCompatSpinner.this.mTempRect;
        rect.right = 0;
        rect.left = 0;
      } 
      int n = AppCompatSpinner.this.getPaddingLeft();
      int k = AppCompatSpinner.this.getPaddingRight();
      int m = AppCompatSpinner.this.getWidth();
      AppCompatSpinner appCompatSpinner = AppCompatSpinner.this;
      int j = appCompatSpinner.mDropDownWidth;
      if (j == -2) {
        int i1 = appCompatSpinner.compatMeasureContentWidth((SpinnerAdapter)this.mAdapter, getBackground());
        j = (AppCompatSpinner.this.getContext().getResources().getDisplayMetrics()).widthPixels;
        Rect rect = AppCompatSpinner.this.mTempRect;
        int i2 = j - rect.left - rect.right;
        j = i1;
        if (i1 > i2)
          j = i2; 
        setContentWidth(Math.max(j, m - n - k));
      } else if (j == -1) {
        setContentWidth(m - n - k);
      } else {
        setContentWidth(j);
      } 
      if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
        i += m - k - getWidth();
      } else {
        i += n;
      } 
      setHorizontalOffset(i);
    }
    
    public CharSequence getHintText() {
      return this.mHintText;
    }
    
    boolean isVisibleToUser(View param1View) {
      boolean bool;
      if (ViewCompat.isAttachedToWindow(param1View) && param1View.getGlobalVisibleRect(this.mVisibleRect)) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void setAdapter(ListAdapter param1ListAdapter) {
      super.setAdapter(param1ListAdapter);
      this.mAdapter = param1ListAdapter;
    }
    
    public void setPromptText(CharSequence param1CharSequence) {
      this.mHintText = param1CharSequence;
    }
    
    public void show() {
      boolean bool = isShowing();
      computeContentWidth();
      setInputMethodMode(2);
      super.show();
      getListView().setChoiceMode(1);
      setSelection(AppCompatSpinner.this.getSelectedItemPosition());
      if (bool)
        return; 
      ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
      if (viewTreeObserver != null) {
        final ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            final AppCompatSpinner.DropdownPopup this$1;
            
            public void onGlobalLayout() {
              AppCompatSpinner.DropdownPopup dropdownPopup = AppCompatSpinner.DropdownPopup.this;
              if (!dropdownPopup.isVisibleToUser((View)AppCompatSpinner.this)) {
                AppCompatSpinner.DropdownPopup.this.dismiss();
              } else {
                AppCompatSpinner.DropdownPopup.this.computeContentWidth();
                AppCompatSpinner.DropdownPopup.this.show();
              } 
            }
          };
        viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
        setOnDismissListener(new PopupWindow.OnDismissListener() {
              final AppCompatSpinner.DropdownPopup this$1;
              
              final ViewTreeObserver.OnGlobalLayoutListener val$layoutListener;
              
              public void onDismiss() {
                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null)
                  viewTreeObserver.removeGlobalOnLayoutListener(layoutListener); 
              }
            });
      } 
    }
  }
  
  class null implements AdapterView.OnItemClickListener {
    final AppCompatSpinner.DropdownPopup this$1;
    
    null(AppCompatSpinner param1AppCompatSpinner) {}
    
    public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      AppCompatSpinner.this.setSelection(param1Int);
      if (AppCompatSpinner.this.getOnItemClickListener() != null) {
        AppCompatSpinner.DropdownPopup dropdownPopup = this.this$1;
        AppCompatSpinner.this.performItemClick(param1View, param1Int, dropdownPopup.mAdapter.getItemId(param1Int));
      } 
      this.this$1.dismiss();
    }
  }
  
  class null implements ViewTreeObserver.OnGlobalLayoutListener {
    final AppCompatSpinner.DropdownPopup this$1;
    
    public void onGlobalLayout() {
      AppCompatSpinner.DropdownPopup dropdownPopup = this.this$1;
      if (!dropdownPopup.isVisibleToUser((View)AppCompatSpinner.this)) {
        this.this$1.dismiss();
      } else {
        this.this$1.computeContentWidth();
        this.this$1.show();
      } 
    }
  }
  
  class null implements PopupWindow.OnDismissListener {
    final AppCompatSpinner.DropdownPopup this$1;
    
    final ViewTreeObserver.OnGlobalLayoutListener val$layoutListener;
    
    public void onDismiss() {
      ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
      if (viewTreeObserver != null)
        viewTreeObserver.removeGlobalOnLayoutListener(layoutListener); 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/AppCompatSpinner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */