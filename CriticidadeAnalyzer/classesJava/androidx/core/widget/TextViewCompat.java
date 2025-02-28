package androidx.core.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Paint;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.util.Preconditions;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class TextViewCompat {
  public static int getFirstBaselineToTopHeight(TextView paramTextView) {
    return paramTextView.getPaddingTop() - (paramTextView.getPaint().getFontMetricsInt()).top;
  }
  
  public static int getLastBaselineToBottomHeight(TextView paramTextView) {
    return paramTextView.getPaddingBottom() + (paramTextView.getPaint().getFontMetricsInt()).bottom;
  }
  
  private static int getTextDirection(TextDirectionHeuristic paramTextDirectionHeuristic) {
    return (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) ? 1 : ((paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) ? 1 : ((paramTextDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) ? 2 : ((paramTextDirectionHeuristic == TextDirectionHeuristics.LTR) ? 3 : ((paramTextDirectionHeuristic == TextDirectionHeuristics.RTL) ? 4 : ((paramTextDirectionHeuristic == TextDirectionHeuristics.LOCALE) ? 5 : ((paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) ? 6 : ((paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) ? 7 : 1)))))));
  }
  
  private static TextDirectionHeuristic getTextDirectionHeuristic(TextView paramTextView) {
    if (paramTextView.getTransformationMethod() instanceof android.text.method.PasswordTransformationMethod)
      return TextDirectionHeuristics.LTR; 
    int i = Build.VERSION.SDK_INT;
    byte b = 0;
    if (i >= 28 && (paramTextView.getInputType() & 0xF) == 3) {
      b = Character.getDirectionality(DecimalFormatSymbols.getInstance(paramTextView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
      return (b == 1 || b == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
    } 
    if (paramTextView.getLayoutDirection() == 1)
      b = 1; 
    switch (paramTextView.getTextDirection()) {
      default:
        if (b != 0)
          return TextDirectionHeuristics.FIRSTSTRONG_RTL; 
        break;
      case 7:
        return TextDirectionHeuristics.FIRSTSTRONG_RTL;
      case 6:
        return TextDirectionHeuristics.FIRSTSTRONG_LTR;
      case 5:
        return TextDirectionHeuristics.LOCALE;
      case 4:
        return TextDirectionHeuristics.RTL;
      case 3:
        return TextDirectionHeuristics.LTR;
      case 2:
        return TextDirectionHeuristics.ANYRTL_LTR;
    } 
    return TextDirectionHeuristics.FIRSTSTRONG_LTR;
  }
  
  public static PrecomputedTextCompat.Params getTextMetricsParams(TextView paramTextView) {
    if (Build.VERSION.SDK_INT >= 28)
      return new PrecomputedTextCompat.Params(paramTextView.getTextMetricsParams()); 
    PrecomputedTextCompat.Params.Builder builder = new PrecomputedTextCompat.Params.Builder(new TextPaint((Paint)paramTextView.getPaint()));
    if (Build.VERSION.SDK_INT >= 23) {
      builder.setBreakStrategy(paramTextView.getBreakStrategy());
      builder.setHyphenationFrequency(paramTextView.getHyphenationFrequency());
    } 
    if (Build.VERSION.SDK_INT >= 18)
      builder.setTextDirection(getTextDirectionHeuristic(paramTextView)); 
    return builder.build();
  }
  
  public static void setFirstBaselineToTopHeight(TextView paramTextView, int paramInt) {
    int i;
    Preconditions.checkArgumentNonnegative(paramInt);
    if (Build.VERSION.SDK_INT >= 28) {
      paramTextView.setFirstBaselineToTopHeight(paramInt);
      return;
    } 
    Paint.FontMetricsInt fontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    if (Build.VERSION.SDK_INT < 16 || paramTextView.getIncludeFontPadding()) {
      i = fontMetricsInt.top;
    } else {
      i = fontMetricsInt.ascent;
    } 
    if (paramInt > Math.abs(i)) {
      i = -i;
      paramTextView.setPadding(paramTextView.getPaddingLeft(), paramInt - i, paramTextView.getPaddingRight(), paramTextView.getPaddingBottom());
    } 
  }
  
  public static void setLastBaselineToBottomHeight(TextView paramTextView, int paramInt) {
    int i;
    Preconditions.checkArgumentNonnegative(paramInt);
    Paint.FontMetricsInt fontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    if (Build.VERSION.SDK_INT < 16 || paramTextView.getIncludeFontPadding()) {
      i = fontMetricsInt.bottom;
    } else {
      i = fontMetricsInt.descent;
    } 
    if (paramInt > Math.abs(i))
      paramTextView.setPadding(paramTextView.getPaddingLeft(), paramTextView.getPaddingTop(), paramTextView.getPaddingRight(), paramInt - i); 
  }
  
  public static void setLineHeight(TextView paramTextView, int paramInt) {
    Preconditions.checkArgumentNonnegative(paramInt);
    int i = paramTextView.getPaint().getFontMetricsInt(null);
    if (paramInt != i)
      paramTextView.setLineSpacing((paramInt - i), 1.0F); 
  }
  
  public static void setPrecomputedText(TextView paramTextView, PrecomputedTextCompat paramPrecomputedTextCompat) {
    if (Build.VERSION.SDK_INT >= 28) {
      paramTextView.setText((CharSequence)paramPrecomputedTextCompat.getPrecomputedText());
    } else {
      if (getTextMetricsParams(paramTextView).equals(paramPrecomputedTextCompat.getParams())) {
        paramTextView.setText((CharSequence)paramPrecomputedTextCompat);
        return;
      } 
      throw new IllegalArgumentException("Given text can not be applied to TextView.");
    } 
  }
  
  public static void setTextAppearance(TextView paramTextView, int paramInt) {
    if (Build.VERSION.SDK_INT >= 23) {
      paramTextView.setTextAppearance(paramInt);
    } else {
      paramTextView.setTextAppearance(paramTextView.getContext(), paramInt);
    } 
  }
  
  public static void setTextMetricsParams(TextView paramTextView, PrecomputedTextCompat.Params paramParams) {
    if (Build.VERSION.SDK_INT >= 18)
      paramTextView.setTextDirection(getTextDirection(paramParams.getTextDirection())); 
    if (Build.VERSION.SDK_INT < 23) {
      float f = paramParams.getTextPaint().getTextScaleX();
      paramTextView.getPaint().set(paramParams.getTextPaint());
      if (f == paramTextView.getTextScaleX())
        paramTextView.setTextScaleX(f / 2.0F + 1.0F); 
      paramTextView.setTextScaleX(f);
    } else {
      paramTextView.getPaint().set(paramParams.getTextPaint());
      paramTextView.setBreakStrategy(paramParams.getBreakStrategy());
      paramTextView.setHyphenationFrequency(paramParams.getHyphenationFrequency());
    } 
  }
  
  public static ActionMode.Callback wrapCustomSelectionActionModeCallback(TextView paramTextView, ActionMode.Callback paramCallback) {
    int i = Build.VERSION.SDK_INT;
    return (i < 26 || i > 27 || paramCallback instanceof OreoCallback) ? paramCallback : new OreoCallback(paramCallback, paramTextView);
  }
  
  private static class OreoCallback implements ActionMode.Callback {
    private final ActionMode.Callback mCallback;
    
    private boolean mCanUseMenuBuilderReferences;
    
    private boolean mInitializedMenuBuilderReferences;
    
    private Class mMenuBuilderClass;
    
    private Method mMenuBuilderRemoveItemAtMethod;
    
    private final TextView mTextView;
    
    OreoCallback(ActionMode.Callback param1Callback, TextView param1TextView) {
      this.mCallback = param1Callback;
      this.mTextView = param1TextView;
      this.mInitializedMenuBuilderReferences = false;
    }
    
    private Intent createProcessTextIntent() {
      return (new Intent()).setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
    }
    
    private Intent createProcessTextIntentForResolveInfo(ResolveInfo param1ResolveInfo, TextView param1TextView) {
      return createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", isEditable(param1TextView) ^ true).setClassName(param1ResolveInfo.activityInfo.packageName, param1ResolveInfo.activityInfo.name);
    }
    
    private List<ResolveInfo> getSupportedActivities(Context param1Context, PackageManager param1PackageManager) {
      ArrayList<ResolveInfo> arrayList = new ArrayList();
      if (!(param1Context instanceof android.app.Activity))
        return arrayList; 
      for (ResolveInfo resolveInfo : param1PackageManager.queryIntentActivities(createProcessTextIntent(), 0)) {
        if (isSupportedActivity(resolveInfo, param1Context))
          arrayList.add(resolveInfo); 
      } 
      return arrayList;
    }
    
    private boolean isEditable(TextView param1TextView) {
      boolean bool;
      if (param1TextView instanceof android.text.Editable && param1TextView.onCheckIsTextEditor() && param1TextView.isEnabled()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private boolean isSupportedActivity(ResolveInfo param1ResolveInfo, Context param1Context) {
      boolean bool = param1Context.getPackageName().equals(param1ResolveInfo.activityInfo.packageName);
      boolean bool1 = true;
      if (bool)
        return true; 
      if (!param1ResolveInfo.activityInfo.exported)
        return false; 
      String str = param1ResolveInfo.activityInfo.permission;
      bool = bool1;
      if (str != null)
        if (param1Context.checkSelfPermission(str) == 0) {
          bool = bool1;
        } else {
          bool = false;
        }  
      return bool;
    }
    
    private void recomputeProcessTextMenuItems(Menu param1Menu) {
      Context context = this.mTextView.getContext();
      PackageManager packageManager = context.getPackageManager();
      if (!this.mInitializedMenuBuilderReferences) {
        this.mInitializedMenuBuilderReferences = true;
        try {
          this.mMenuBuilderClass = Class.forName("com.android.internal.view.menu.MenuBuilder");
          this.mMenuBuilderRemoveItemAtMethod = this.mMenuBuilderClass.getDeclaredMethod("removeItemAt", new Class[] { int.class });
          this.mCanUseMenuBuilderReferences = true;
        } catch (ClassNotFoundException|NoSuchMethodException classNotFoundException) {
          this.mMenuBuilderClass = null;
          this.mMenuBuilderRemoveItemAtMethod = null;
          this.mCanUseMenuBuilderReferences = false;
        } 
      } 
      try {
        Method method;
        if (this.mCanUseMenuBuilderReferences && this.mMenuBuilderClass.isInstance(param1Menu)) {
          method = this.mMenuBuilderRemoveItemAtMethod;
        } else {
          method = param1Menu.getClass().getDeclaredMethod("removeItemAt", new Class[] { int.class });
        } 
        int i;
        for (i = param1Menu.size() - 1; i >= 0; i--) {
          MenuItem menuItem = param1Menu.getItem(i);
          if (menuItem.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(menuItem.getIntent().getAction()))
            method.invoke(param1Menu, new Object[] { Integer.valueOf(i) }); 
        } 
        List<ResolveInfo> list = getSupportedActivities(context, packageManager);
        for (i = 0; i < list.size(); i++) {
          ResolveInfo resolveInfo = list.get(i);
          param1Menu.add(0, 0, i + 100, resolveInfo.loadLabel(packageManager)).setIntent(createProcessTextIntentForResolveInfo(resolveInfo, this.mTextView)).setShowAsAction(1);
        } 
      } catch (NoSuchMethodException|IllegalAccessException|java.lang.reflect.InvocationTargetException noSuchMethodException) {}
    }
    
    public boolean onActionItemClicked(ActionMode param1ActionMode, MenuItem param1MenuItem) {
      return this.mCallback.onActionItemClicked(param1ActionMode, param1MenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return this.mCallback.onCreateActionMode(param1ActionMode, param1Menu);
    }
    
    public void onDestroyActionMode(ActionMode param1ActionMode) {
      this.mCallback.onDestroyActionMode(param1ActionMode);
    }
    
    public boolean onPrepareActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      recomputeProcessTextMenuItems(param1Menu);
      return this.mCallback.onPrepareActionMode(param1ActionMode, param1Menu);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/widget/TextViewCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */