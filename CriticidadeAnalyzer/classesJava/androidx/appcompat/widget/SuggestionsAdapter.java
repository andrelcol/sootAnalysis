package androidx.appcompat.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.ResourceCursorAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter extends ResourceCursorAdapter implements View.OnClickListener {
  private boolean mClosed = false;
  
  private final int mCommitIconResId;
  
  private int mFlagsCol = -1;
  
  private int mIconName1Col = -1;
  
  private int mIconName2Col = -1;
  
  private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
  
  private final Context mProviderContext;
  
  private int mQueryRefinement = 1;
  
  private final SearchView mSearchView;
  
  private final SearchableInfo mSearchable;
  
  private int mText1Col = -1;
  
  private int mText2Col = -1;
  
  private int mText2UrlCol = -1;
  
  private ColorStateList mUrlColor;
  
  public SuggestionsAdapter(Context paramContext, SearchView paramSearchView, SearchableInfo paramSearchableInfo, WeakHashMap<String, Drawable.ConstantState> paramWeakHashMap) {
    super(paramContext, paramSearchView.getSuggestionRowLayout(), null, true);
    SearchManager searchManager = (SearchManager)((CursorAdapter)this).mContext.getSystemService("search");
    this.mSearchView = paramSearchView;
    this.mSearchable = paramSearchableInfo;
    this.mCommitIconResId = paramSearchView.getSuggestionCommitIconResId();
    this.mProviderContext = paramContext;
    this.mOutsideDrawablesCache = paramWeakHashMap;
  }
  
  private Drawable checkIconCache(String paramString) {
    Drawable.ConstantState constantState = this.mOutsideDrawablesCache.get(paramString);
    return (constantState == null) ? null : constantState.newDrawable();
  }
  
  private CharSequence formatUrl(CharSequence paramCharSequence) {
    if (this.mUrlColor == null) {
      TypedValue typedValue = new TypedValue();
      ((CursorAdapter)this).mContext.getTheme().resolveAttribute(R.attr.textColorSearchUrl, typedValue, true);
      this.mUrlColor = ((CursorAdapter)this).mContext.getResources().getColorStateList(typedValue.resourceId);
    } 
    SpannableString spannableString = new SpannableString(paramCharSequence);
    spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.mUrlColor, null), 0, paramCharSequence.length(), 33);
    return (CharSequence)spannableString;
  }
  
  private Drawable getActivityIcon(ComponentName paramComponentName) {
    PackageManager packageManager = ((CursorAdapter)this).mContext.getPackageManager();
    try {
      StringBuilder stringBuilder;
      ActivityInfo activityInfo = packageManager.getActivityInfo(paramComponentName, 128);
      int i = activityInfo.getIconResource();
      if (i == 0)
        return null; 
      Drawable drawable = packageManager.getDrawable(paramComponentName.getPackageName(), i, activityInfo.applicationInfo);
      if (drawable == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid icon resource ");
        stringBuilder.append(i);
        stringBuilder.append(" for ");
        stringBuilder.append(paramComponentName.flattenToShortString());
        stringBuilder.toString();
        return null;
      } 
      return (Drawable)stringBuilder;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      nameNotFoundException.toString();
      return null;
    } 
  }
  
  private Drawable getActivityIconWithCache(ComponentName paramComponentName) {
    Drawable drawable1;
    Drawable.ConstantState constantState1;
    String str = paramComponentName.flattenToShortString();
    boolean bool = this.mOutsideDrawablesCache.containsKey(str);
    Drawable drawable2 = null;
    Drawable.ConstantState constantState2 = null;
    if (bool) {
      constantState1 = this.mOutsideDrawablesCache.get(str);
      if (constantState1 == null) {
        constantState1 = constantState2;
      } else {
        drawable1 = constantState1.newDrawable(this.mProviderContext.getResources());
      } 
      return drawable1;
    } 
    Drawable drawable3 = getActivityIcon((ComponentName)drawable1);
    if (drawable3 == null) {
      drawable1 = drawable2;
    } else {
      constantState1 = drawable3.getConstantState();
    } 
    this.mOutsideDrawablesCache.put(str, constantState1);
    return drawable3;
  }
  
  public static String getColumnString(Cursor paramCursor, String paramString) {
    return getStringOrNull(paramCursor, paramCursor.getColumnIndex(paramString));
  }
  
  private Drawable getDefaultIcon1(Cursor paramCursor) {
    Drawable drawable = getActivityIconWithCache(this.mSearchable.getSearchActivity());
    return (drawable != null) ? drawable : ((CursorAdapter)this).mContext.getPackageManager().getDefaultActivityIcon();
  }
  
  private Drawable getDrawable(Uri paramUri) {
    try {
      boolean bool = "android.resource".equals(paramUri.getScheme());
      if (bool)
        try {
          return getDrawableFromResourceUri(paramUri);
        } catch (android.content.res.Resources.NotFoundException notFoundException) {
          FileNotFoundException fileNotFoundException1 = new FileNotFoundException();
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("Resource does not exist: ");
          stringBuilder1.append(paramUri);
          this(stringBuilder1.toString());
          throw fileNotFoundException1;
        }  
      InputStream inputStream = this.mProviderContext.getContentResolver().openInputStream(paramUri);
      if (inputStream != null) {
        StringBuilder stringBuilder1;
        try {
          return Drawable.createFromStream(inputStream, null);
        } finally {
          try {
            stringBuilder1.close();
          } catch (IOException iOException) {
            StringBuilder stringBuilder2 = new StringBuilder();
            this();
            stringBuilder2.append("Error closing icon stream for ");
            stringBuilder2.append(paramUri);
            stringBuilder2.toString();
          } 
        } 
      } 
      FileNotFoundException fileNotFoundException = new FileNotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Failed to open ");
      stringBuilder.append(paramUri);
      this(stringBuilder.toString());
      throw fileNotFoundException;
    } catch (FileNotFoundException fileNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Icon not found: ");
      stringBuilder.append(paramUri);
      stringBuilder.append(", ");
      stringBuilder.append(fileNotFoundException.getMessage());
      stringBuilder.toString();
      return null;
    } 
  }
  
  private Drawable getDrawableFromResourceValue(String paramString) {
    StringBuilder stringBuilder2 = null;
    StringBuilder stringBuilder1 = stringBuilder2;
    if (paramString != null) {
      stringBuilder1 = stringBuilder2;
      if (!paramString.isEmpty())
        if ("0".equals(paramString)) {
          stringBuilder1 = stringBuilder2;
        } else {
          try {
            int i = Integer.parseInt(paramString);
            stringBuilder1 = new StringBuilder();
            this();
            stringBuilder1.append("android.resource://");
            stringBuilder1.append(this.mProviderContext.getPackageName());
            stringBuilder1.append("/");
            stringBuilder1.append(i);
            String str = stringBuilder1.toString();
            Drawable drawable = checkIconCache(str);
            if (drawable != null)
              return drawable; 
            drawable = ContextCompat.getDrawable(this.mProviderContext, i);
            storeInIconCache(str, drawable);
            return drawable;
          } catch (NumberFormatException numberFormatException) {
            Drawable drawable = checkIconCache(paramString);
            if (drawable != null)
              return drawable; 
            drawable = getDrawable(Uri.parse(paramString));
            storeInIconCache(paramString, drawable);
          } catch (android.content.res.Resources.NotFoundException notFoundException) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Icon resource not found: ");
            stringBuilder1.append(paramString);
            stringBuilder1.toString();
            return null;
          } 
        }  
    } 
    return (Drawable)stringBuilder1;
  }
  
  private Drawable getIcon1(Cursor paramCursor) {
    int i = this.mIconName1Col;
    if (i == -1)
      return null; 
    Drawable drawable = getDrawableFromResourceValue(paramCursor.getString(i));
    return (drawable != null) ? drawable : getDefaultIcon1(paramCursor);
  }
  
  private Drawable getIcon2(Cursor paramCursor) {
    int i = this.mIconName2Col;
    return (i == -1) ? null : getDrawableFromResourceValue(paramCursor.getString(i));
  }
  
  private static String getStringOrNull(Cursor paramCursor, int paramInt) {
    if (paramInt == -1)
      return null; 
    try {
      return paramCursor.getString(paramInt);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private void setViewDrawable(ImageView paramImageView, Drawable paramDrawable, int paramInt) {
    paramImageView.setImageDrawable(paramDrawable);
    if (paramDrawable == null) {
      paramImageView.setVisibility(paramInt);
    } else {
      paramImageView.setVisibility(0);
      paramDrawable.setVisible(false, false);
      paramDrawable.setVisible(true, false);
    } 
  }
  
  private void setViewText(TextView paramTextView, CharSequence paramCharSequence) {
    paramTextView.setText(paramCharSequence);
    if (TextUtils.isEmpty(paramCharSequence)) {
      paramTextView.setVisibility(8);
    } else {
      paramTextView.setVisibility(0);
    } 
  }
  
  private void storeInIconCache(String paramString, Drawable paramDrawable) {
    if (paramDrawable != null)
      this.mOutsideDrawablesCache.put(paramString, paramDrawable.getConstantState()); 
  }
  
  private void updateSpinnerState(Cursor paramCursor) {
    if (paramCursor != null) {
      Bundle bundle = paramCursor.getExtras();
    } else {
      paramCursor = null;
    } 
    if (paramCursor == null || paramCursor.getBoolean("in_progress"));
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor) {
    ChildViewCache childViewCache = (ChildViewCache)paramView.getTag();
    int i = this.mFlagsCol;
    if (i != -1) {
      i = paramCursor.getInt(i);
    } else {
      i = 0;
    } 
    if (childViewCache.mText1 != null) {
      String str = getStringOrNull(paramCursor, this.mText1Col);
      setViewText(childViewCache.mText1, str);
    } 
    if (childViewCache.mText2 != null) {
      String str = getStringOrNull(paramCursor, this.mText2UrlCol);
      if (str != null) {
        CharSequence charSequence = formatUrl(str);
      } else {
        str = getStringOrNull(paramCursor, this.mText2Col);
      } 
      if (TextUtils.isEmpty(str)) {
        TextView textView = childViewCache.mText1;
        if (textView != null) {
          textView.setSingleLine(false);
          childViewCache.mText1.setMaxLines(2);
        } 
      } else {
        TextView textView = childViewCache.mText1;
        if (textView != null) {
          textView.setSingleLine(true);
          childViewCache.mText1.setMaxLines(1);
        } 
      } 
      setViewText(childViewCache.mText2, str);
    } 
    ImageView imageView = childViewCache.mIcon1;
    if (imageView != null)
      setViewDrawable(imageView, getIcon1(paramCursor), 4); 
    imageView = childViewCache.mIcon2;
    if (imageView != null)
      setViewDrawable(imageView, getIcon2(paramCursor), 8); 
    int j = this.mQueryRefinement;
    if (j == 2 || (j == 1 && (i & 0x1) != 0)) {
      childViewCache.mIconRefine.setVisibility(0);
      childViewCache.mIconRefine.setTag(childViewCache.mText1.getText());
      childViewCache.mIconRefine.setOnClickListener(this);
      return;
    } 
    childViewCache.mIconRefine.setVisibility(8);
  }
  
  public void changeCursor(Cursor paramCursor) {
    if (this.mClosed) {
      if (paramCursor != null)
        paramCursor.close(); 
      return;
    } 
    try {
      super.changeCursor(paramCursor);
      if (paramCursor != null) {
        this.mText1Col = paramCursor.getColumnIndex("suggest_text_1");
        this.mText2Col = paramCursor.getColumnIndex("suggest_text_2");
        this.mText2UrlCol = paramCursor.getColumnIndex("suggest_text_2_url");
        this.mIconName1Col = paramCursor.getColumnIndex("suggest_icon_1");
        this.mIconName2Col = paramCursor.getColumnIndex("suggest_icon_2");
        this.mFlagsCol = paramCursor.getColumnIndex("suggest_flags");
      } 
    } catch (Exception exception) {}
  }
  
  public CharSequence convertToString(Cursor paramCursor) {
    if (paramCursor == null)
      return null; 
    String str = getColumnString(paramCursor, "suggest_intent_query");
    if (str != null)
      return str; 
    if (this.mSearchable.shouldRewriteQueryFromData()) {
      str = getColumnString(paramCursor, "suggest_intent_data");
      if (str != null)
        return str; 
    } 
    if (this.mSearchable.shouldRewriteQueryFromText()) {
      String str1 = getColumnString(paramCursor, "suggest_text_1");
      if (str1 != null)
        return str1; 
    } 
    return null;
  }
  
  Drawable getDrawableFromResourceUri(Uri paramUri) throws FileNotFoundException {
    String str = paramUri.getAuthority();
    if (!TextUtils.isEmpty(str))
      try {
        Resources resources = ((CursorAdapter)this).mContext.getPackageManager().getResourcesForApplication(str);
        List<String> list = paramUri.getPathSegments();
        if (list != null) {
          int i = list.size();
          if (i == 1) {
            try {
              i = Integer.parseInt(list.get(0));
            } catch (NumberFormatException numberFormatException) {
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append("Single path segment is not a resource ID: ");
              stringBuilder2.append(paramUri);
              throw new FileNotFoundException(stringBuilder2.toString());
            } 
          } else if (i == 2) {
            i = resources.getIdentifier(list.get(1), list.get(0), (String)stringBuilder2);
          } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("More than two path segments: ");
            stringBuilder2.append(paramUri);
            throw new FileNotFoundException(stringBuilder2.toString());
          } 
          if (i != 0)
            return resources.getDrawable(i); 
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("No resource found for: ");
          stringBuilder2.append(paramUri);
          throw new FileNotFoundException(stringBuilder2.toString());
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("No path: ");
        stringBuilder1.append(paramUri);
        throw new FileNotFoundException(stringBuilder1.toString());
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("No package found for authority: ");
        stringBuilder1.append(paramUri);
        throw new FileNotFoundException(stringBuilder1.toString());
      }  
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("No authority: ");
    stringBuilder.append(paramUri);
    throw new FileNotFoundException(stringBuilder.toString());
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    try {
      return super.getDropDownView(paramInt, paramView, paramViewGroup);
    } catch (RuntimeException runtimeException) {
      View view = newDropDownView(((CursorAdapter)this).mContext, ((CursorAdapter)this).mCursor, paramViewGroup);
      if (view != null)
        ((ChildViewCache)view.getTag()).mText1.setText(runtimeException.toString()); 
      return view;
    } 
  }
  
  Cursor getSearchManagerSuggestions(SearchableInfo paramSearchableInfo, String paramString, int paramInt) {
    SearchableInfo searchableInfo = null;
    if (paramSearchableInfo == null)
      return null; 
    String str1 = paramSearchableInfo.getSuggestAuthority();
    if (str1 == null)
      return null; 
    Uri.Builder builder = (new Uri.Builder()).scheme("content").authority(str1).query("").fragment("");
    String str2 = paramSearchableInfo.getSuggestPath();
    if (str2 != null)
      builder.appendEncodedPath(str2); 
    builder.appendPath("search_suggest_query");
    str2 = paramSearchableInfo.getSuggestSelection();
    if (str2 != null) {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramString;
    } else {
      builder.appendPath(paramString);
      paramSearchableInfo = searchableInfo;
    } 
    if (paramInt > 0)
      builder.appendQueryParameter("limit", String.valueOf(paramInt)); 
    Uri uri = builder.build();
    return ((CursorAdapter)this).mContext.getContentResolver().query(uri, null, str2, (String[])paramSearchableInfo, null);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    try {
      return super.getView(paramInt, paramView, paramViewGroup);
    } catch (RuntimeException runtimeException) {
      View view = newView(((CursorAdapter)this).mContext, ((CursorAdapter)this).mCursor, paramViewGroup);
      if (view != null)
        ((ChildViewCache)view.getTag()).mText1.setText(runtimeException.toString()); 
      return view;
    } 
  }
  
  public boolean hasStableIds() {
    return false;
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup) {
    View view = super.newView(paramContext, paramCursor, paramViewGroup);
    view.setTag(new ChildViewCache(view));
    ((ImageView)view.findViewById(R.id.edit_query)).setImageResource(this.mCommitIconResId);
    return view;
  }
  
  public void notifyDataSetChanged() {
    super.notifyDataSetChanged();
    updateSpinnerState(getCursor());
  }
  
  public void notifyDataSetInvalidated() {
    super.notifyDataSetInvalidated();
    updateSpinnerState(getCursor());
  }
  
  public void onClick(View paramView) {
    Object object = paramView.getTag();
    if (object instanceof CharSequence)
      this.mSearchView.onQueryRefine((CharSequence)object); 
  }
  
  public Cursor runQueryOnBackgroundThread(CharSequence paramCharSequence) {
    if (paramCharSequence == null) {
      paramCharSequence = "";
    } else {
      paramCharSequence = paramCharSequence.toString();
    } 
    if (this.mSearchView.getVisibility() == 0 && this.mSearchView.getWindowVisibility() == 0)
      try {
        Cursor cursor = getSearchManagerSuggestions(this.mSearchable, (String)paramCharSequence, 50);
        if (cursor != null) {
          cursor.getCount();
          return cursor;
        } 
      } catch (RuntimeException runtimeException) {} 
    return null;
  }
  
  public void setQueryRefinement(int paramInt) {
    this.mQueryRefinement = paramInt;
  }
  
  private static final class ChildViewCache {
    public final ImageView mIcon1;
    
    public final ImageView mIcon2;
    
    public final ImageView mIconRefine;
    
    public final TextView mText1;
    
    public final TextView mText2;
    
    public ChildViewCache(View param1View) {
      this.mText1 = (TextView)param1View.findViewById(16908308);
      this.mText2 = (TextView)param1View.findViewById(16908309);
      this.mIcon1 = (ImageView)param1View.findViewById(16908295);
      this.mIcon2 = (ImageView)param1View.findViewById(16908296);
      this.mIconRefine = (ImageView)param1View.findViewById(R.id.edit_query);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/widget/SuggestionsAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */