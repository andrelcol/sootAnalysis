package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.core.view.ViewCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.customview.view.AbsSavedState;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
  static final AutoCompleteTextViewReflector HIDDEN_METHOD_INVOKER = new AutoCompleteTextViewReflector();
  
  private Bundle mAppSearchData;
  
  private boolean mClearingFocus;
  
  final ImageView mCloseButton;
  
  private final ImageView mCollapsedIcon;
  
  private int mCollapsedImeOptions;
  
  private final CharSequence mDefaultQueryHint;
  
  private final View mDropDownAnchor;
  
  private boolean mExpandedInActionView;
  
  final ImageView mGoButton;
  
  private boolean mIconified;
  
  private boolean mIconifiedByDefault;
  
  private int mMaxWidth;
  
  private CharSequence mOldQueryText;
  
  private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
      final SearchView this$0;
      
      public void onClick(View param1View) {
        SearchView searchView = SearchView.this;
        if (param1View == searchView.mSearchButton) {
          searchView.onSearchClicked();
        } else if (param1View == searchView.mCloseButton) {
          searchView.onCloseClicked();
        } else if (param1View == searchView.mGoButton) {
          searchView.onSubmitQuery();
        } else if (param1View == searchView.mVoiceButton) {
          searchView.onVoiceClicked();
        } else if (param1View == searchView.mSearchSrcTextView) {
          searchView.forceSuggestionQuery();
        } 
      }
    };
  
  private OnCloseListener mOnCloseListener;
  
  private final TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
      final SearchView this$0;
      
      public boolean onEditorAction(TextView param1TextView, int param1Int, KeyEvent param1KeyEvent) {
        SearchView.this.onSubmitQuery();
        return true;
      }
    };
  
  private final AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
      final SearchView this$0;
      
      public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
        SearchView.this.onItemClicked(param1Int, 0, null);
      }
    };
  
  private final AdapterView.OnItemSelectedListener mOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
      final SearchView this$0;
      
      public void onItemSelected(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
        SearchView.this.onItemSelected(param1Int);
      }
      
      public void onNothingSelected(AdapterView<?> param1AdapterView) {}
    };
  
  private OnQueryTextListener mOnQueryChangeListener;
  
  View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
  
  private View.OnClickListener mOnSearchClickListener;
  
  private OnSuggestionListener mOnSuggestionListener;
  
  private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache = new WeakHashMap<String, Drawable.ConstantState>();
  
  private CharSequence mQueryHint;
  
  private boolean mQueryRefinement;
  
  private Runnable mReleaseCursorRunnable = new Runnable() {
      final SearchView this$0;
      
      public void run() {
        CursorAdapter cursorAdapter = SearchView.this.mSuggestionsAdapter;
        if (cursorAdapter != null && cursorAdapter instanceof SuggestionsAdapter)
          cursorAdapter.changeCursor(null); 
      }
    };
  
  final ImageView mSearchButton;
  
  private final View mSearchEditFrame;
  
  private final Drawable mSearchHintIcon;
  
  private final View mSearchPlate;
  
  final SearchAutoComplete mSearchSrcTextView;
  
  private Rect mSearchSrcTextViewBounds = new Rect();
  
  private Rect mSearchSrtTextViewBoundsExpanded = new Rect();
  
  SearchableInfo mSearchable;
  
  private final View mSubmitArea;
  
  private boolean mSubmitButtonEnabled;
  
  private final int mSuggestionCommitIconResId;
  
  private final int mSuggestionRowLayout;
  
  CursorAdapter mSuggestionsAdapter;
  
  private int[] mTemp = new int[2];
  
  private int[] mTemp2 = new int[2];
  
  View.OnKeyListener mTextKeyListener = new View.OnKeyListener() {
      final SearchView this$0;
      
      public boolean onKey(View param1View, int param1Int, KeyEvent param1KeyEvent) {
        SearchView searchView = SearchView.this;
        if (searchView.mSearchable == null)
          return false; 
        if (searchView.mSearchSrcTextView.isPopupShowing() && SearchView.this.mSearchSrcTextView.getListSelection() != -1)
          return SearchView.this.onSuggestionsKey(param1View, param1Int, param1KeyEvent); 
        if (!SearchView.this.mSearchSrcTextView.isEmpty() && param1KeyEvent.hasNoModifiers() && param1KeyEvent.getAction() == 1 && param1Int == 66) {
          param1View.cancelLongPress();
          SearchView searchView1 = SearchView.this;
          searchView1.launchQuerySearch(0, null, searchView1.mSearchSrcTextView.getText().toString());
          return true;
        } 
        return false;
      }
    };
  
  private TextWatcher mTextWatcher = new TextWatcher() {
      final SearchView this$0;
      
      public void afterTextChanged(Editable param1Editable) {}
      
      public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
      
      public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
        SearchView.this.onTextChanged(param1CharSequence);
      }
    };
  
  private UpdatableTouchDelegate mTouchDelegate;
  
  private final Runnable mUpdateDrawableStateRunnable = new Runnable() {
      final SearchView this$0;
      
      public void run() {
        SearchView.this.updateFocusedState();
      }
    };
  
  private CharSequence mUserQuery;
  
  private final Intent mVoiceAppSearchIntent;
  
  final ImageView mVoiceButton;
  
  private boolean mVoiceButtonEnabled;
  
  private final Intent mVoiceWebSearchIntent;
  
  public SearchView(Context paramContext) {
    this(paramContext, null);
  }
  
  public SearchView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.searchViewStyle);
  }
  
  public SearchView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.SearchView, paramInt, 0);
    LayoutInflater.from(paramContext).inflate(tintTypedArray.getResourceId(R.styleable.SearchView_layout, R.layout.abc_search_view), this, true);
    this.mSearchSrcTextView = (SearchAutoComplete)findViewById(R.id.search_src_text);
    this.mSearchSrcTextView.setSearchView(this);
    this.mSearchEditFrame = findViewById(R.id.search_edit_frame);
    this.mSearchPlate = findViewById(R.id.search_plate);
    this.mSubmitArea = findViewById(R.id.submit_area);
    this.mSearchButton = (ImageView)findViewById(R.id.search_button);
    this.mGoButton = (ImageView)findViewById(R.id.search_go_btn);
    this.mCloseButton = (ImageView)findViewById(R.id.search_close_btn);
    this.mVoiceButton = (ImageView)findViewById(R.id.search_voice_btn);
    this.mCollapsedIcon = (ImageView)findViewById(R.id.search_mag_icon);
    ViewCompat.setBackground(this.mSearchPlate, tintTypedArray.getDrawable(R.styleable.SearchView_queryBackground));
    ViewCompat.setBackground(this.mSubmitArea, tintTypedArray.getDrawable(R.styleable.SearchView_submitBackground));
    this.mSearchButton.setImageDrawable(tintTypedArray.getDrawable(R.styleable.SearchView_searchIcon));
    this.mGoButton.setImageDrawable(tintTypedArray.getDrawable(R.styleable.SearchView_goIcon));
    this.mCloseButton.setImageDrawable(tintTypedArray.getDrawable(R.styleable.SearchView_closeIcon));
    this.mVoiceButton.setImageDrawable(tintTypedArray.getDrawable(R.styleable.SearchView_voiceIcon));
    this.mCollapsedIcon.setImageDrawable(tintTypedArray.getDrawable(R.styleable.SearchView_searchIcon));
    this.mSearchHintIcon = tintTypedArray.getDrawable(R.styleable.SearchView_searchHintIcon);
    TooltipCompat.setTooltipText((View)this.mSearchButton, getResources().getString(R.string.abc_searchview_description_search));
    this.mSuggestionRowLayout = tintTypedArray.getResourceId(R.styleable.SearchView_suggestionRowLayout, R.layout.abc_search_dropdown_item_icons_2line);
    this.mSuggestionCommitIconResId = tintTypedArray.getResourceId(R.styleable.SearchView_commitIcon, 0);
    this.mSearchButton.setOnClickListener(this.mOnClickListener);
    this.mCloseButton.setOnClickListener(this.mOnClickListener);
    this.mGoButton.setOnClickListener(this.mOnClickListener);
    this.mVoiceButton.setOnClickListener(this.mOnClickListener);
    this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
    this.mSearchSrcTextView.addTextChangedListener(this.mTextWatcher);
    this.mSearchSrcTextView.setOnEditorActionListener(this.mOnEditorActionListener);
    this.mSearchSrcTextView.setOnItemClickListener(this.mOnItemClickListener);
    this.mSearchSrcTextView.setOnItemSelectedListener(this.mOnItemSelectedListener);
    this.mSearchSrcTextView.setOnKeyListener(this.mTextKeyListener);
    this.mSearchSrcTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          final SearchView this$0;
          
          public void onFocusChange(View param1View, boolean param1Boolean) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.mOnQueryTextFocusChangeListener;
            if (onFocusChangeListener != null)
              onFocusChangeListener.onFocusChange((View)searchView, param1Boolean); 
          }
        });
    setIconifiedByDefault(tintTypedArray.getBoolean(R.styleable.SearchView_iconifiedByDefault, true));
    paramInt = tintTypedArray.getDimensionPixelSize(R.styleable.SearchView_android_maxWidth, -1);
    if (paramInt != -1)
      setMaxWidth(paramInt); 
    this.mDefaultQueryHint = tintTypedArray.getText(R.styleable.SearchView_defaultQueryHint);
    this.mQueryHint = tintTypedArray.getText(R.styleable.SearchView_queryHint);
    paramInt = tintTypedArray.getInt(R.styleable.SearchView_android_imeOptions, -1);
    if (paramInt != -1)
      setImeOptions(paramInt); 
    paramInt = tintTypedArray.getInt(R.styleable.SearchView_android_inputType, -1);
    if (paramInt != -1)
      setInputType(paramInt); 
    setFocusable(tintTypedArray.getBoolean(R.styleable.SearchView_android_focusable, true));
    tintTypedArray.recycle();
    this.mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH");
    this.mVoiceWebSearchIntent.addFlags(268435456);
    this.mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
    this.mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    this.mVoiceAppSearchIntent.addFlags(268435456);
    this.mDropDownAnchor = findViewById(this.mSearchSrcTextView.getDropDownAnchor());
    View view = this.mDropDownAnchor;
    if (view != null)
      view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            final SearchView this$0;
            
            public void onLayoutChange(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, int param1Int7, int param1Int8) {
              SearchView.this.adjustDropDownSizeAndPosition();
            }
          }); 
    updateViewsVisibility(this.mIconifiedByDefault);
    updateQueryHint();
  }
  
  private Intent createIntent(String paramString1, Uri paramUri, String paramString2, String paramString3, int paramInt, String paramString4) {
    Intent intent = new Intent(paramString1);
    intent.addFlags(268435456);
    if (paramUri != null)
      intent.setData(paramUri); 
    intent.putExtra("user_query", this.mUserQuery);
    if (paramString3 != null)
      intent.putExtra("query", paramString3); 
    if (paramString2 != null)
      intent.putExtra("intent_extra_data_key", paramString2); 
    Bundle bundle = this.mAppSearchData;
    if (bundle != null)
      intent.putExtra("app_data", bundle); 
    if (paramInt != 0) {
      intent.putExtra("action_key", paramInt);
      intent.putExtra("action_msg", paramString4);
    } 
    intent.setComponent(this.mSearchable.getSearchActivity());
    return intent;
  }
  
  private Intent createIntentFromSuggestion(Cursor paramCursor, int paramInt, String paramString) {
    try {
      Uri uri;
      String str2 = SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_action");
      String str1 = str2;
      if (str2 == null)
        str1 = this.mSearchable.getSuggestIntentAction(); 
      str2 = str1;
      if (str1 == null)
        str2 = "android.intent.action.SEARCH"; 
      String str3 = SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_data");
      str1 = str3;
      if (str3 == null)
        str1 = this.mSearchable.getSuggestIntentData(); 
      str3 = str1;
      if (str1 != null) {
        String str = SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_data_id");
        str3 = str1;
        if (str != null) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(str1);
          stringBuilder.append("/");
          stringBuilder.append(Uri.encode(str));
          str3 = stringBuilder.toString();
        } 
      } 
      if (str3 == null) {
        str1 = null;
      } else {
        uri = Uri.parse(str3);
      } 
      str3 = SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_query");
      return createIntent(str2, uri, SuggestionsAdapter.getColumnString(paramCursor, "suggest_intent_extra_data"), str3, paramInt, paramString);
    } catch (RuntimeException runtimeException) {
      try {
        paramInt = paramCursor.getPosition();
      } catch (RuntimeException runtimeException1) {
        paramInt = -1;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Search suggestions cursor at row ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" returned exception.");
      stringBuilder.toString();
      return null;
    } 
  }
  
  private Intent createVoiceAppSearchIntent(Intent paramIntent, SearchableInfo paramSearchableInfo) {
    String str1;
    ComponentName componentName = paramSearchableInfo.getSearchActivity();
    Intent intent1 = new Intent("android.intent.action.SEARCH");
    intent1.setComponent(componentName);
    PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent1, 1073741824);
    Bundle bundle2 = new Bundle();
    Bundle bundle1 = this.mAppSearchData;
    if (bundle1 != null)
      bundle2.putParcelable("app_data", (Parcelable)bundle1); 
    Intent intent2 = new Intent(paramIntent);
    int i = 1;
    Resources resources = getResources();
    if (paramSearchableInfo.getVoiceLanguageModeId() != 0) {
      str1 = resources.getString(paramSearchableInfo.getVoiceLanguageModeId());
    } else {
      str1 = "free_form";
    } 
    int j = paramSearchableInfo.getVoicePromptTextId();
    String str2 = null;
    if (j != 0) {
      String str = resources.getString(paramSearchableInfo.getVoicePromptTextId());
    } else {
      bundle1 = null;
    } 
    if (paramSearchableInfo.getVoiceLanguageId() != 0) {
      String str = resources.getString(paramSearchableInfo.getVoiceLanguageId());
    } else {
      resources = null;
    } 
    if (paramSearchableInfo.getVoiceMaxResults() != 0)
      i = paramSearchableInfo.getVoiceMaxResults(); 
    intent2.putExtra("android.speech.extra.LANGUAGE_MODEL", str1);
    intent2.putExtra("android.speech.extra.PROMPT", (String)bundle1);
    intent2.putExtra("android.speech.extra.LANGUAGE", (String)resources);
    intent2.putExtra("android.speech.extra.MAX_RESULTS", i);
    if (componentName == null) {
      str1 = str2;
    } else {
      str1 = componentName.flattenToShortString();
    } 
    intent2.putExtra("calling_package", str1);
    intent2.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", (Parcelable)pendingIntent);
    intent2.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle2);
    return intent2;
  }
  
  private Intent createVoiceWebSearchIntent(Intent paramIntent, SearchableInfo paramSearchableInfo) {
    String str;
    Intent intent = new Intent(paramIntent);
    ComponentName componentName = paramSearchableInfo.getSearchActivity();
    if (componentName == null) {
      componentName = null;
    } else {
      str = componentName.flattenToShortString();
    } 
    intent.putExtra("calling_package", str);
    return intent;
  }
  
  private void dismissSuggestions() {
    this.mSearchSrcTextView.dismissDropDown();
  }
  
  private void getChildBoundsWithinSearchView(View paramView, Rect paramRect) {
    paramView.getLocationInWindow(this.mTemp);
    getLocationInWindow(this.mTemp2);
    int[] arrayOfInt2 = this.mTemp;
    int i = arrayOfInt2[1];
    int[] arrayOfInt1 = this.mTemp2;
    int j = i - arrayOfInt1[1];
    i = arrayOfInt2[0] - arrayOfInt1[0];
    paramRect.set(i, j, paramView.getWidth() + i, paramView.getHeight() + j);
  }
  
  private CharSequence getDecoratedHint(CharSequence paramCharSequence) {
    if (!this.mIconifiedByDefault || this.mSearchHintIcon == null)
      return paramCharSequence; 
    int i = (int)(this.mSearchSrcTextView.getTextSize() * 1.25D);
    this.mSearchHintIcon.setBounds(0, 0, i, i);
    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
    spannableStringBuilder.setSpan(new ImageSpan(this.mSearchHintIcon), 1, 2, 33);
    spannableStringBuilder.append(paramCharSequence);
    return (CharSequence)spannableStringBuilder;
  }
  
  private int getPreferredHeight() {
    return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_height);
  }
  
  private int getPreferredWidth() {
    return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
  }
  
  private boolean hasVoiceSearch() {
    SearchableInfo searchableInfo = this.mSearchable;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (searchableInfo != null) {
      bool1 = bool2;
      if (searchableInfo.getVoiceSearchEnabled()) {
        Intent intent;
        searchableInfo = null;
        if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
          intent = this.mVoiceWebSearchIntent;
        } else if (this.mSearchable.getVoiceSearchLaunchRecognizer()) {
          intent = this.mVoiceAppSearchIntent;
        } 
        bool1 = bool2;
        if (intent != null) {
          bool1 = bool2;
          if (getContext().getPackageManager().resolveActivity(intent, 65536) != null)
            bool1 = true; 
        } 
      } 
    } 
    return bool1;
  }
  
  static boolean isLandscapeMode(Context paramContext) {
    boolean bool;
    if ((paramContext.getResources().getConfiguration()).orientation == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isSubmitAreaEnabled() {
    boolean bool;
    if ((this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !isIconified()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void launchIntent(Intent paramIntent) {
    if (paramIntent == null)
      return; 
    try {
      getContext().startActivity(paramIntent);
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed launch activity: ");
      stringBuilder.append(paramIntent);
      stringBuilder.toString();
    } 
  }
  
  private boolean launchSuggestion(int paramInt1, int paramInt2, String paramString) {
    Cursor cursor = this.mSuggestionsAdapter.getCursor();
    if (cursor != null && cursor.moveToPosition(paramInt1)) {
      launchIntent(createIntentFromSuggestion(cursor, paramInt2, paramString));
      return true;
    } 
    return false;
  }
  
  private void postUpdateFocusedState() {
    post(this.mUpdateDrawableStateRunnable);
  }
  
  private void rewriteQueryFromSuggestion(int paramInt) {
    Editable editable = this.mSearchSrcTextView.getText();
    Cursor cursor = this.mSuggestionsAdapter.getCursor();
    if (cursor == null)
      return; 
    if (cursor.moveToPosition(paramInt)) {
      CharSequence charSequence = this.mSuggestionsAdapter.convertToString(cursor);
      if (charSequence != null) {
        setQuery(charSequence);
      } else {
        setQuery((CharSequence)editable);
      } 
    } else {
      setQuery((CharSequence)editable);
    } 
  }
  
  private void setQuery(CharSequence paramCharSequence) {
    int i;
    this.mSearchSrcTextView.setText(paramCharSequence);
    SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
    if (TextUtils.isEmpty(paramCharSequence)) {
      i = 0;
    } else {
      i = paramCharSequence.length();
    } 
    searchAutoComplete.setSelection(i);
  }
  
  private void updateCloseButton() {
    boolean bool = TextUtils.isEmpty((CharSequence)this.mSearchSrcTextView.getText());
    byte b3 = 1;
    int i = bool ^ true;
    byte b2 = 0;
    byte b1 = b3;
    if (i == 0)
      if (this.mIconifiedByDefault && !this.mExpandedInActionView) {
        b1 = b3;
      } else {
        b1 = 0;
      }  
    ImageView imageView = this.mCloseButton;
    if (b1) {
      b1 = b2;
    } else {
      b1 = 8;
    } 
    imageView.setVisibility(b1);
    Drawable drawable = this.mCloseButton.getDrawable();
    if (drawable != null) {
      int[] arrayOfInt;
      if (i != 0) {
        arrayOfInt = ViewGroup.ENABLED_STATE_SET;
      } else {
        arrayOfInt = ViewGroup.EMPTY_STATE_SET;
      } 
      drawable.setState(arrayOfInt);
    } 
  }
  
  private void updateQueryHint() {
    CharSequence charSequence2 = getQueryHint();
    SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
    CharSequence charSequence1 = charSequence2;
    if (charSequence2 == null)
      charSequence1 = ""; 
    searchAutoComplete.setHint(getDecoratedHint(charSequence1));
  }
  
  private void updateSearchAutoComplete() {
    this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
    this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
    int j = this.mSearchable.getInputType();
    boolean bool = true;
    int i = j;
    if ((j & 0xF) == 1) {
      j &= 0xFFFEFFFF;
      i = j;
      if (this.mSearchable.getSuggestAuthority() != null)
        i = j | 0x10000 | 0x80000; 
    } 
    this.mSearchSrcTextView.setInputType(i);
    CursorAdapter cursorAdapter = this.mSuggestionsAdapter;
    if (cursorAdapter != null)
      cursorAdapter.changeCursor(null); 
    if (this.mSearchable.getSuggestAuthority() != null) {
      this.mSuggestionsAdapter = (CursorAdapter)new SuggestionsAdapter(getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
      this.mSearchSrcTextView.setAdapter((ListAdapter)this.mSuggestionsAdapter);
      SuggestionsAdapter suggestionsAdapter = (SuggestionsAdapter)this.mSuggestionsAdapter;
      i = bool;
      if (this.mQueryRefinement)
        i = 2; 
      suggestionsAdapter.setQueryRefinement(i);
    } 
  }
  
  private void updateSubmitArea() {
    byte b;
    if (isSubmitAreaEnabled() && (this.mGoButton.getVisibility() == 0 || this.mVoiceButton.getVisibility() == 0)) {
      b = 0;
    } else {
      b = 8;
    } 
    this.mSubmitArea.setVisibility(b);
  }
  
  private void updateSubmitButton(boolean paramBoolean) {
    byte b;
    if (this.mSubmitButtonEnabled && isSubmitAreaEnabled() && hasFocus() && (paramBoolean || !this.mVoiceButtonEnabled)) {
      b = 0;
    } else {
      b = 8;
    } 
    this.mGoButton.setVisibility(b);
  }
  
  private void updateViewsVisibility(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: putfield mIconified : Z
    //   5: iconst_0
    //   6: istore_3
    //   7: iload_1
    //   8: ifeq -> 16
    //   11: iconst_0
    //   12: istore_2
    //   13: goto -> 19
    //   16: bipush #8
    //   18: istore_2
    //   19: aload_0
    //   20: getfield mSearchSrcTextView : Landroidx/appcompat/widget/SearchView$SearchAutoComplete;
    //   23: invokevirtual getText : ()Landroid/text/Editable;
    //   26: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   29: iconst_1
    //   30: ixor
    //   31: istore #4
    //   33: aload_0
    //   34: getfield mSearchButton : Landroid/widget/ImageView;
    //   37: iload_2
    //   38: invokevirtual setVisibility : (I)V
    //   41: aload_0
    //   42: iload #4
    //   44: invokespecial updateSubmitButton : (Z)V
    //   47: aload_0
    //   48: getfield mSearchEditFrame : Landroid/view/View;
    //   51: astore #5
    //   53: iload_1
    //   54: ifeq -> 63
    //   57: bipush #8
    //   59: istore_2
    //   60: goto -> 65
    //   63: iconst_0
    //   64: istore_2
    //   65: aload #5
    //   67: iload_2
    //   68: invokevirtual setVisibility : (I)V
    //   71: aload_0
    //   72: getfield mCollapsedIcon : Landroid/widget/ImageView;
    //   75: invokevirtual getDrawable : ()Landroid/graphics/drawable/Drawable;
    //   78: ifnull -> 90
    //   81: iload_3
    //   82: istore_2
    //   83: aload_0
    //   84: getfield mIconifiedByDefault : Z
    //   87: ifeq -> 93
    //   90: bipush #8
    //   92: istore_2
    //   93: aload_0
    //   94: getfield mCollapsedIcon : Landroid/widget/ImageView;
    //   97: iload_2
    //   98: invokevirtual setVisibility : (I)V
    //   101: aload_0
    //   102: invokespecial updateCloseButton : ()V
    //   105: aload_0
    //   106: iload #4
    //   108: iconst_1
    //   109: ixor
    //   110: invokespecial updateVoiceButton : (Z)V
    //   113: aload_0
    //   114: invokespecial updateSubmitArea : ()V
    //   117: return
  }
  
  private void updateVoiceButton(boolean paramBoolean) {
    byte b;
    if (this.mVoiceButtonEnabled && !isIconified() && paramBoolean) {
      b = 0;
      this.mGoButton.setVisibility(8);
    } else {
      b = 8;
    } 
    this.mVoiceButton.setVisibility(b);
  }
  
  void adjustDropDownSizeAndPosition() {
    if (this.mDropDownAnchor.getWidth() > 1) {
      byte b;
      Resources resources = getContext().getResources();
      int j = this.mSearchPlate.getPaddingLeft();
      Rect rect = new Rect();
      boolean bool = ViewUtils.isLayoutRtl((View)this);
      if (this.mIconifiedByDefault) {
        b = resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_text_padding_left);
      } else {
        b = 0;
      } 
      this.mSearchSrcTextView.getDropDownBackground().getPadding(rect);
      if (bool) {
        i = -rect.left;
      } else {
        i = j - rect.left + b;
      } 
      this.mSearchSrcTextView.setDropDownHorizontalOffset(i);
      int m = this.mDropDownAnchor.getWidth();
      int k = rect.left;
      int i = rect.right;
      this.mSearchSrcTextView.setDropDownWidth(m + k + i + b - j);
    } 
  }
  
  public void clearFocus() {
    this.mClearingFocus = true;
    super.clearFocus();
    this.mSearchSrcTextView.clearFocus();
    this.mSearchSrcTextView.setImeVisibility(false);
    this.mClearingFocus = false;
  }
  
  void forceSuggestionQuery() {
    HIDDEN_METHOD_INVOKER.doBeforeTextChanged(this.mSearchSrcTextView);
    HIDDEN_METHOD_INVOKER.doAfterTextChanged(this.mSearchSrcTextView);
  }
  
  public int getImeOptions() {
    return this.mSearchSrcTextView.getImeOptions();
  }
  
  public int getInputType() {
    return this.mSearchSrcTextView.getInputType();
  }
  
  public int getMaxWidth() {
    return this.mMaxWidth;
  }
  
  public CharSequence getQuery() {
    return (CharSequence)this.mSearchSrcTextView.getText();
  }
  
  public CharSequence getQueryHint() {
    CharSequence charSequence = this.mQueryHint;
    if (charSequence == null) {
      SearchableInfo searchableInfo = this.mSearchable;
      if (searchableInfo != null && searchableInfo.getHintId() != 0) {
        CharSequence charSequence1 = getContext().getText(this.mSearchable.getHintId());
      } else {
        charSequence = this.mDefaultQueryHint;
      } 
    } 
    return charSequence;
  }
  
  int getSuggestionCommitIconResId() {
    return this.mSuggestionCommitIconResId;
  }
  
  int getSuggestionRowLayout() {
    return this.mSuggestionRowLayout;
  }
  
  public CursorAdapter getSuggestionsAdapter() {
    return this.mSuggestionsAdapter;
  }
  
  public boolean isIconified() {
    return this.mIconified;
  }
  
  void launchQuerySearch(int paramInt, String paramString1, String paramString2) {
    Intent intent = createIntent("android.intent.action.SEARCH", null, null, paramString2, paramInt, paramString1);
    getContext().startActivity(intent);
  }
  
  public void onActionViewCollapsed() {
    setQuery("", false);
    clearFocus();
    updateViewsVisibility(true);
    this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
    this.mExpandedInActionView = false;
  }
  
  public void onActionViewExpanded() {
    if (this.mExpandedInActionView)
      return; 
    this.mExpandedInActionView = true;
    this.mCollapsedImeOptions = this.mSearchSrcTextView.getImeOptions();
    this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions | 0x2000000);
    this.mSearchSrcTextView.setText("");
    setIconified(false);
  }
  
  void onCloseClicked() {
    if (TextUtils.isEmpty((CharSequence)this.mSearchSrcTextView.getText())) {
      if (this.mIconifiedByDefault) {
        OnCloseListener onCloseListener = this.mOnCloseListener;
        if (onCloseListener == null || !onCloseListener.onClose()) {
          clearFocus();
          updateViewsVisibility(true);
        } 
      } 
    } else {
      this.mSearchSrcTextView.setText("");
      this.mSearchSrcTextView.requestFocus();
      this.mSearchSrcTextView.setImeVisibility(true);
    } 
  }
  
  protected void onDetachedFromWindow() {
    removeCallbacks(this.mUpdateDrawableStateRunnable);
    post(this.mReleaseCursorRunnable);
    super.onDetachedFromWindow();
  }
  
  boolean onItemClicked(int paramInt1, int paramInt2, String paramString) {
    OnSuggestionListener onSuggestionListener = this.mOnSuggestionListener;
    if (onSuggestionListener == null || !onSuggestionListener.onSuggestionClick(paramInt1)) {
      launchSuggestion(paramInt1, 0, null);
      this.mSearchSrcTextView.setImeVisibility(false);
      dismissSuggestions();
      return true;
    } 
    return false;
  }
  
  boolean onItemSelected(int paramInt) {
    OnSuggestionListener onSuggestionListener = this.mOnSuggestionListener;
    if (onSuggestionListener == null || !onSuggestionListener.onSuggestionSelect(paramInt)) {
      rewriteQueryFromSuggestion(paramInt);
      return true;
    } 
    return false;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramBoolean) {
      getChildBoundsWithinSearchView((View)this.mSearchSrcTextView, this.mSearchSrcTextViewBounds);
      Rect rect2 = this.mSearchSrtTextViewBoundsExpanded;
      Rect rect1 = this.mSearchSrcTextViewBounds;
      rect2.set(rect1.left, 0, rect1.right, paramInt4 - paramInt2);
      UpdatableTouchDelegate updatableTouchDelegate = this.mTouchDelegate;
      if (updatableTouchDelegate == null) {
        this.mTouchDelegate = new UpdatableTouchDelegate(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds, (View)this.mSearchSrcTextView);
        setTouchDelegate(this.mTouchDelegate);
      } else {
        updatableTouchDelegate.setBounds(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds);
      } 
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (isIconified()) {
      super.onMeasure(paramInt1, paramInt2);
      return;
    } 
    int j = View.MeasureSpec.getMode(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt1);
    if (j != Integer.MIN_VALUE) {
      if (j != 0) {
        if (j != 1073741824) {
          paramInt1 = i;
        } else {
          j = this.mMaxWidth;
          paramInt1 = i;
          if (j > 0)
            paramInt1 = Math.min(j, i); 
        } 
      } else {
        paramInt1 = this.mMaxWidth;
        if (paramInt1 <= 0)
          paramInt1 = getPreferredWidth(); 
      } 
    } else {
      paramInt1 = this.mMaxWidth;
      if (paramInt1 > 0) {
        paramInt1 = Math.min(paramInt1, i);
      } else {
        paramInt1 = Math.min(getPreferredWidth(), i);
      } 
    } 
    i = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    if (i != Integer.MIN_VALUE) {
      if (i == 0)
        paramInt2 = getPreferredHeight(); 
    } else {
      paramInt2 = Math.min(getPreferredHeight(), paramInt2);
    } 
    super.onMeasure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824));
  }
  
  void onQueryRefine(CharSequence paramCharSequence) {
    setQuery(paramCharSequence);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    updateViewsVisibility(savedState.isIconified);
    requestLayout();
  }
  
  protected Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    savedState.isIconified = isIconified();
    return (Parcelable)savedState;
  }
  
  void onSearchClicked() {
    updateViewsVisibility(false);
    this.mSearchSrcTextView.requestFocus();
    this.mSearchSrcTextView.setImeVisibility(true);
    View.OnClickListener onClickListener = this.mOnSearchClickListener;
    if (onClickListener != null)
      onClickListener.onClick((View)this); 
  }
  
  void onSubmitQuery() {
    Editable editable = this.mSearchSrcTextView.getText();
    if (editable != null && TextUtils.getTrimmedLength((CharSequence)editable) > 0) {
      OnQueryTextListener onQueryTextListener = this.mOnQueryChangeListener;
      if (onQueryTextListener == null || !onQueryTextListener.onQueryTextSubmit(editable.toString())) {
        if (this.mSearchable != null)
          launchQuerySearch(0, null, editable.toString()); 
        this.mSearchSrcTextView.setImeVisibility(false);
        dismissSuggestions();
      } 
    } 
  }
  
  boolean onSuggestionsKey(View paramView, int paramInt, KeyEvent paramKeyEvent) {
    if (this.mSearchable == null)
      return false; 
    if (this.mSuggestionsAdapter == null)
      return false; 
    if (paramKeyEvent.getAction() == 0 && paramKeyEvent.hasNoModifiers()) {
      if (paramInt == 66 || paramInt == 84 || paramInt == 61)
        return onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, null); 
      if (paramInt == 21 || paramInt == 22) {
        if (paramInt == 21) {
          paramInt = 0;
        } else {
          paramInt = this.mSearchSrcTextView.length();
        } 
        this.mSearchSrcTextView.setSelection(paramInt);
        this.mSearchSrcTextView.setListSelection(0);
        this.mSearchSrcTextView.clearListSelection();
        HIDDEN_METHOD_INVOKER.ensureImeVisible(this.mSearchSrcTextView, true);
        return true;
      } 
      if (paramInt == 19 && this.mSearchSrcTextView.getListSelection() == 0)
        return false; 
    } 
    return false;
  }
  
  void onTextChanged(CharSequence paramCharSequence) {
    Editable editable = this.mSearchSrcTextView.getText();
    this.mUserQuery = (CharSequence)editable;
    int i = TextUtils.isEmpty((CharSequence)editable) ^ true;
    updateSubmitButton(i);
    updateVoiceButton(i ^ 0x1);
    updateCloseButton();
    updateSubmitArea();
    if (this.mOnQueryChangeListener != null && !TextUtils.equals(paramCharSequence, this.mOldQueryText))
      this.mOnQueryChangeListener.onQueryTextChange(paramCharSequence.toString()); 
    this.mOldQueryText = paramCharSequence.toString();
  }
  
  void onTextFocusChanged() {
    updateViewsVisibility(isIconified());
    postUpdateFocusedState();
    if (this.mSearchSrcTextView.hasFocus())
      forceSuggestionQuery(); 
  }
  
  void onVoiceClicked() {
    SearchableInfo searchableInfo = this.mSearchable;
    if (searchableInfo == null)
      return; 
    try {
      Intent intent;
      if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
        intent = createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchableInfo);
        getContext().startActivity(intent);
      } else if (intent.getVoiceSearchLaunchRecognizer()) {
        intent = createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, (SearchableInfo)intent);
        getContext().startActivity(intent);
      } 
    } catch (ActivityNotFoundException activityNotFoundException) {}
  }
  
  public void onWindowFocusChanged(boolean paramBoolean) {
    super.onWindowFocusChanged(paramBoolean);
    postUpdateFocusedState();
  }
  
  public boolean requestFocus(int paramInt, Rect paramRect) {
    if (this.mClearingFocus)
      return false; 
    if (!isFocusable())
      return false; 
    if (!isIconified()) {
      boolean bool = this.mSearchSrcTextView.requestFocus(paramInt, paramRect);
      if (bool)
        updateViewsVisibility(false); 
      return bool;
    } 
    return super.requestFocus(paramInt, paramRect);
  }
  
  public void setAppSearchData(Bundle paramBundle) {
    this.mAppSearchData = paramBundle;
  }
  
  public void setIconified(boolean paramBoolean) {
    if (paramBoolean) {
      onCloseClicked();
    } else {
      onSearchClicked();
    } 
  }
  
  public void setIconifiedByDefault(boolean paramBoolean) {
    if (this.mIconifiedByDefault == paramBoolean)
      return; 
    this.mIconifiedByDefault = paramBoolean;
    updateViewsVisibility(paramBoolean);
    updateQueryHint();
  }
  
  public void setImeOptions(int paramInt) {
    this.mSearchSrcTextView.setImeOptions(paramInt);
  }
  
  public void setInputType(int paramInt) {
    this.mSearchSrcTextView.setInputType(paramInt);
  }
  
  public void setMaxWidth(int paramInt) {
    this.mMaxWidth = paramInt;
    requestLayout();
  }
  
  public void setOnCloseListener(OnCloseListener paramOnCloseListener) {
    this.mOnCloseListener = paramOnCloseListener;
  }
  
  public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener paramOnFocusChangeListener) {
    this.mOnQueryTextFocusChangeListener = paramOnFocusChangeListener;
  }
  
  public void setOnQueryTextListener(OnQueryTextListener paramOnQueryTextListener) {
    this.mOnQueryChangeListener = paramOnQueryTextListener;
  }
  
  public void setOnSearchClickListener(View.OnClickListener paramOnClickListener) {
    this.mOnSearchClickListener = paramOnClickListener;
  }
  
  public void setOnSuggestionListener(OnSuggestionListener paramOnSuggestionListener) {
    this.mOnSuggestionListener = paramOnSuggestionListener;
  }
  
  public void setQuery(CharSequence paramCharSequence, boolean paramBoolean) {
    this.mSearchSrcTextView.setText(paramCharSequence);
    if (paramCharSequence != null) {
      SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
      searchAutoComplete.setSelection(searchAutoComplete.length());
      this.mUserQuery = paramCharSequence;
    } 
    if (paramBoolean && !TextUtils.isEmpty(paramCharSequence))
      onSubmitQuery(); 
  }
  
  public void setQueryHint(CharSequence paramCharSequence) {
    this.mQueryHint = paramCharSequence;
    updateQueryHint();
  }
  
  public void setQueryRefinementEnabled(boolean paramBoolean) {
    this.mQueryRefinement = paramBoolean;
    CursorAdapter cursorAdapter = this.mSuggestionsAdapter;
    if (cursorAdapter instanceof SuggestionsAdapter) {
      boolean bool;
      SuggestionsAdapter suggestionsAdapter = (SuggestionsAdapter)cursorAdapter;
      if (paramBoolean) {
        bool = true;
      } else {
        bool = true;
      } 
      suggestionsAdapter.setQueryRefinement(bool);
    } 
  }
  
  public void setSearchableInfo(SearchableInfo paramSearchableInfo) {
    this.mSearchable = paramSearchableInfo;
    if (this.mSearchable != null) {
      updateSearchAutoComplete();
      updateQueryHint();
    } 
    this.mVoiceButtonEnabled = hasVoiceSearch();
    if (this.mVoiceButtonEnabled)
      this.mSearchSrcTextView.setPrivateImeOptions("nm"); 
    updateViewsVisibility(isIconified());
  }
  
  public void setSubmitButtonEnabled(boolean paramBoolean) {
    this.mSubmitButtonEnabled = paramBoolean;
    updateViewsVisibility(isIconified());
  }
  
  public void setSuggestionsAdapter(CursorAdapter paramCursorAdapter) {
    this.mSuggestionsAdapter = paramCursorAdapter;
    this.mSearchSrcTextView.setAdapter((ListAdapter)this.mSuggestionsAdapter);
  }
  
  void updateFocusedState() {
    int[] arrayOfInt;
    if (this.mSearchSrcTextView.hasFocus()) {
      arrayOfInt = ViewGroup.FOCUSED_STATE_SET;
    } else {
      arrayOfInt = ViewGroup.EMPTY_STATE_SET;
    } 
    Drawable drawable = this.mSearchPlate.getBackground();
    if (drawable != null)
      drawable.setState(arrayOfInt); 
    drawable = this.mSubmitArea.getBackground();
    if (drawable != null)
      drawable.setState(arrayOfInt); 
    invalidate();
  }
  
  private static class AutoCompleteTextViewReflector {
    private Method doAfterTextChanged;
    
    private Method doBeforeTextChanged;
    
    private Method ensureImeVisible;
    
    AutoCompleteTextViewReflector() {
      try {
        this.doBeforeTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
        this.doBeforeTextChanged.setAccessible(true);
      } catch (NoSuchMethodException noSuchMethodException) {}
      try {
        this.doAfterTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
        this.doAfterTextChanged.setAccessible(true);
      } catch (NoSuchMethodException noSuchMethodException) {}
      try {
        this.ensureImeVisible = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[] { boolean.class });
        this.ensureImeVisible.setAccessible(true);
      } catch (NoSuchMethodException noSuchMethodException) {}
    }
    
    void doAfterTextChanged(AutoCompleteTextView param1AutoCompleteTextView) {
      Method method = this.doAfterTextChanged;
      if (method != null)
        try {
          method.invoke(param1AutoCompleteTextView, new Object[0]);
        } catch (Exception exception) {} 
    }
    
    void doBeforeTextChanged(AutoCompleteTextView param1AutoCompleteTextView) {
      Method method = this.doBeforeTextChanged;
      if (method != null)
        try {
          method.invoke(param1AutoCompleteTextView, new Object[0]);
        } catch (Exception exception) {} 
    }
    
    void ensureImeVisible(AutoCompleteTextView param1AutoCompleteTextView, boolean param1Boolean) {
      Method method = this.ensureImeVisible;
      if (method != null)
        try {
          method.invoke(param1AutoCompleteTextView, new Object[] { Boolean.valueOf(param1Boolean) });
        } catch (Exception exception) {} 
    }
  }
  
  public static interface OnCloseListener {
    boolean onClose();
  }
  
  public static interface OnQueryTextListener {
    boolean onQueryTextChange(String param1String);
    
    boolean onQueryTextSubmit(String param1String);
  }
  
  public static interface OnSuggestionListener {
    boolean onSuggestionClick(int param1Int);
    
    boolean onSuggestionSelect(int param1Int);
  }
  
  static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public SearchView.SavedState createFromParcel(Parcel param2Parcel) {
          return new SearchView.SavedState(param2Parcel, null);
        }
        
        public SearchView.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new SearchView.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public SearchView.SavedState[] newArray(int param2Int) {
          return new SearchView.SavedState[param2Int];
        }
      };
    
    boolean isIconified;
    
    public SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      this.isIconified = ((Boolean)param1Parcel.readValue(null)).booleanValue();
    }
    
    SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("SearchView.SavedState{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" isIconified=");
      stringBuilder.append(this.isIconified);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeValue(Boolean.valueOf(this.isIconified));
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public SearchView.SavedState createFromParcel(Parcel param1Parcel) {
      return new SearchView.SavedState(param1Parcel, null);
    }
    
    public SearchView.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new SearchView.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public SearchView.SavedState[] newArray(int param1Int) {
      return new SearchView.SavedState[param1Int];
    }
  }
  
  public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
    private boolean mHasPendingShowSoftInputRequest;
    
    final Runnable mRunShowSoftInputIfNecessary = new Runnable() {
        final SearchView.SearchAutoComplete this$0;
        
        public void run() {
          SearchView.SearchAutoComplete.this.showSoftInputIfNecessary();
        }
      };
    
    private SearchView mSearchView;
    
    private int mThreshold = getThreshold();
    
    public SearchAutoComplete(Context param1Context) {
      this(param1Context, null);
    }
    
    public SearchAutoComplete(Context param1Context, AttributeSet param1AttributeSet) {
      this(param1Context, param1AttributeSet, R.attr.autoCompleteTextViewStyle);
    }
    
    public SearchAutoComplete(Context param1Context, AttributeSet param1AttributeSet, int param1Int) {
      super(param1Context, param1AttributeSet, param1Int);
    }
    
    private int getSearchViewTextMinWidthDp() {
      Configuration configuration = getResources().getConfiguration();
      int i = configuration.screenWidthDp;
      int j = configuration.screenHeightDp;
      return (i >= 960 && j >= 720 && configuration.orientation == 2) ? 256 : ((i >= 600 || (i >= 640 && j >= 480)) ? 192 : 160);
    }
    
    public boolean enoughToFilter() {
      return (this.mThreshold <= 0 || super.enoughToFilter());
    }
    
    boolean isEmpty() {
      boolean bool;
      if (TextUtils.getTrimmedLength((CharSequence)getText()) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public InputConnection onCreateInputConnection(EditorInfo param1EditorInfo) {
      InputConnection inputConnection = super.onCreateInputConnection(param1EditorInfo);
      if (this.mHasPendingShowSoftInputRequest) {
        removeCallbacks(this.mRunShowSoftInputIfNecessary);
        post(this.mRunShowSoftInputIfNecessary);
      } 
      return inputConnection;
    }
    
    protected void onFinishInflate() {
      super.onFinishInflate();
      DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
      setMinWidth((int)TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), displayMetrics));
    }
    
    protected void onFocusChanged(boolean param1Boolean, int param1Int, Rect param1Rect) {
      super.onFocusChanged(param1Boolean, param1Int, param1Rect);
      this.mSearchView.onTextFocusChanged();
    }
    
    public boolean onKeyPreIme(int param1Int, KeyEvent param1KeyEvent) {
      if (param1Int == 4) {
        if (param1KeyEvent.getAction() == 0 && param1KeyEvent.getRepeatCount() == 0) {
          KeyEvent.DispatcherState dispatcherState = getKeyDispatcherState();
          if (dispatcherState != null)
            dispatcherState.startTracking(param1KeyEvent, this); 
          return true;
        } 
        if (param1KeyEvent.getAction() == 1) {
          KeyEvent.DispatcherState dispatcherState = getKeyDispatcherState();
          if (dispatcherState != null)
            dispatcherState.handleUpEvent(param1KeyEvent); 
          if (param1KeyEvent.isTracking() && !param1KeyEvent.isCanceled()) {
            this.mSearchView.clearFocus();
            setImeVisibility(false);
            return true;
          } 
        } 
      } 
      return super.onKeyPreIme(param1Int, param1KeyEvent);
    }
    
    public void onWindowFocusChanged(boolean param1Boolean) {
      super.onWindowFocusChanged(param1Boolean);
      if (param1Boolean && this.mSearchView.hasFocus() && getVisibility() == 0) {
        this.mHasPendingShowSoftInputRequest = true;
        if (SearchView.isLandscapeMode(getContext()))
          SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this, true); 
      } 
    }
    
    public void performCompletion() {}
    
    protected void replaceText(CharSequence param1CharSequence) {}
    
    void setImeVisibility(boolean param1Boolean) {
      InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService("input_method");
      if (!param1Boolean) {
        this.mHasPendingShowSoftInputRequest = false;
        removeCallbacks(this.mRunShowSoftInputIfNecessary);
        inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        return;
      } 
      if (inputMethodManager.isActive((View)this)) {
        this.mHasPendingShowSoftInputRequest = false;
        removeCallbacks(this.mRunShowSoftInputIfNecessary);
        inputMethodManager.showSoftInput((View)this, 0);
        return;
      } 
      this.mHasPendingShowSoftInputRequest = true;
    }
    
    void setSearchView(SearchView param1SearchView) {
      this.mSearchView = param1SearchView;
    }
    
    public void setThreshold(int param1Int) {
      super.setThreshold(param1Int);
      this.mThreshold = param1Int;
    }
    
    void showSoftInputIfNecessary() {
      if (this.mHasPendingShowSoftInputRequest) {
        ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput((View)this, 0);
        this.mHasPendingShowSoftInputRequest = false;
      } 
    }
  }
  
  class null implements Runnable {
    final SearchView.SearchAutoComplete this$0;
    
    public void run() {
      this.this$0.showSoftInputIfNecessary();
    }
  }
  
  private static class UpdatableTouchDelegate extends TouchDelegate {
    private final Rect mActualBounds;
    
    private boolean mDelegateTargeted;
    
    private final View mDelegateView;
    
    private final int mSlop;
    
    private final Rect mSlopBounds;
    
    private final Rect mTargetBounds;
    
    public UpdatableTouchDelegate(Rect param1Rect1, Rect param1Rect2, View param1View) {
      super(param1Rect1, param1View);
      this.mSlop = ViewConfiguration.get(param1View.getContext()).getScaledTouchSlop();
      this.mTargetBounds = new Rect();
      this.mSlopBounds = new Rect();
      this.mActualBounds = new Rect();
      setBounds(param1Rect1, param1Rect2);
      this.mDelegateView = param1View;
    }
    
    public boolean onTouchEvent(MotionEvent param1MotionEvent) {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual getX : ()F
      //   4: f2i
      //   5: istore #5
      //   7: aload_1
      //   8: invokevirtual getY : ()F
      //   11: f2i
      //   12: istore #4
      //   14: aload_1
      //   15: invokevirtual getAction : ()I
      //   18: istore_2
      //   19: iconst_1
      //   20: istore_3
      //   21: iconst_0
      //   22: istore #7
      //   24: iload_2
      //   25: ifeq -> 108
      //   28: iload_2
      //   29: iconst_1
      //   30: if_icmpeq -> 62
      //   33: iload_2
      //   34: iconst_2
      //   35: if_icmpeq -> 62
      //   38: iload_2
      //   39: iconst_3
      //   40: if_icmpeq -> 46
      //   43: goto -> 135
      //   46: aload_0
      //   47: getfield mDelegateTargeted : Z
      //   50: istore #6
      //   52: aload_0
      //   53: iconst_0
      //   54: putfield mDelegateTargeted : Z
      //   57: iload_3
      //   58: istore_2
      //   59: goto -> 140
      //   62: aload_0
      //   63: getfield mDelegateTargeted : Z
      //   66: istore #8
      //   68: iload #8
      //   70: istore #6
      //   72: iload_3
      //   73: istore_2
      //   74: iload #8
      //   76: ifeq -> 140
      //   79: iload #8
      //   81: istore #6
      //   83: iload_3
      //   84: istore_2
      //   85: aload_0
      //   86: getfield mSlopBounds : Landroid/graphics/Rect;
      //   89: iload #5
      //   91: iload #4
      //   93: invokevirtual contains : (II)Z
      //   96: ifne -> 140
      //   99: iconst_0
      //   100: istore_2
      //   101: iload #8
      //   103: istore #6
      //   105: goto -> 140
      //   108: aload_0
      //   109: getfield mTargetBounds : Landroid/graphics/Rect;
      //   112: iload #5
      //   114: iload #4
      //   116: invokevirtual contains : (II)Z
      //   119: ifeq -> 135
      //   122: aload_0
      //   123: iconst_1
      //   124: putfield mDelegateTargeted : Z
      //   127: iconst_1
      //   128: istore #6
      //   130: iload_3
      //   131: istore_2
      //   132: goto -> 140
      //   135: iconst_0
      //   136: istore #6
      //   138: iload_3
      //   139: istore_2
      //   140: iload #6
      //   142: ifeq -> 228
      //   145: iload_2
      //   146: ifeq -> 190
      //   149: aload_0
      //   150: getfield mActualBounds : Landroid/graphics/Rect;
      //   153: iload #5
      //   155: iload #4
      //   157: invokevirtual contains : (II)Z
      //   160: ifne -> 190
      //   163: aload_1
      //   164: aload_0
      //   165: getfield mDelegateView : Landroid/view/View;
      //   168: invokevirtual getWidth : ()I
      //   171: iconst_2
      //   172: idiv
      //   173: i2f
      //   174: aload_0
      //   175: getfield mDelegateView : Landroid/view/View;
      //   178: invokevirtual getHeight : ()I
      //   181: iconst_2
      //   182: idiv
      //   183: i2f
      //   184: invokevirtual setLocation : (FF)V
      //   187: goto -> 218
      //   190: aload_0
      //   191: getfield mActualBounds : Landroid/graphics/Rect;
      //   194: astore #9
      //   196: aload_1
      //   197: iload #5
      //   199: aload #9
      //   201: getfield left : I
      //   204: isub
      //   205: i2f
      //   206: iload #4
      //   208: aload #9
      //   210: getfield top : I
      //   213: isub
      //   214: i2f
      //   215: invokevirtual setLocation : (FF)V
      //   218: aload_0
      //   219: getfield mDelegateView : Landroid/view/View;
      //   222: aload_1
      //   223: invokevirtual dispatchTouchEvent : (Landroid/view/MotionEvent;)Z
      //   226: istore #7
      //   228: iload #7
      //   230: ireturn
    }
    
    public void setBounds(Rect param1Rect1, Rect param1Rect2) {
      this.mTargetBounds.set(param1Rect1);
      this.mSlopBounds.set(param1Rect1);
      param1Rect1 = this.mSlopBounds;
      int i = this.mSlop;
      param1Rect1.inset(-i, -i);
      this.mActualBounds.set(param1Rect2);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/SearchView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */