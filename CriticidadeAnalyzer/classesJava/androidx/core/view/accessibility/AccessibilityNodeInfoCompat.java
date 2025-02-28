package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

public class AccessibilityNodeInfoCompat {
  private final AccessibilityNodeInfo mInfo;
  
  private AccessibilityNodeInfoCompat(AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    this.mInfo = paramAccessibilityNodeInfo;
  }
  
  private static String getActionSymbolicName(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        switch (paramInt) {
          default:
            return "ACTION_UNKNOWN";
          case 131072:
            return "ACTION_SET_SELECTION";
          case 65536:
            return "ACTION_CUT";
          case 32768:
            return "ACTION_PASTE";
          case 16384:
            return "ACTION_COPY";
          case 8192:
            return "ACTION_SCROLL_BACKWARD";
          case 4096:
            return "ACTION_SCROLL_FORWARD";
          case 2048:
            return "ACTION_PREVIOUS_HTML_ELEMENT";
          case 1024:
            return "ACTION_NEXT_HTML_ELEMENT";
          case 512:
            return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
          case 256:
            return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
          case 128:
            return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
          case 64:
            return "ACTION_ACCESSIBILITY_FOCUS";
          case 32:
            return "ACTION_LONG_CLICK";
          case 16:
            return "ACTION_CLICK";
          case 8:
            return "ACTION_CLEAR_SELECTION";
          case 4:
            break;
        } 
        return "ACTION_SELECT";
      } 
      return "ACTION_CLEAR_FOCUS";
    } 
    return "ACTION_FOCUS";
  }
  
  public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat) {
    return wrap(AccessibilityNodeInfo.obtain(paramAccessibilityNodeInfoCompat.mInfo));
  }
  
  public static AccessibilityNodeInfoCompat wrap(AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    return new AccessibilityNodeInfoCompat(paramAccessibilityNodeInfo);
  }
  
  public void addAction(int paramInt) {
    this.mInfo.addAction(paramInt);
  }
  
  public void addChild(View paramView) {
    this.mInfo.addChild(paramView);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (AccessibilityNodeInfoCompat.class != paramObject.getClass())
      return false; 
    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat)paramObject;
    paramObject = this.mInfo;
    if (paramObject == null) {
      if (accessibilityNodeInfoCompat.mInfo != null)
        return false; 
    } else if (!paramObject.equals(accessibilityNodeInfoCompat.mInfo)) {
      return false;
    } 
    return true;
  }
  
  public int getActions() {
    return this.mInfo.getActions();
  }
  
  public void getBoundsInParent(Rect paramRect) {
    this.mInfo.getBoundsInParent(paramRect);
  }
  
  public void getBoundsInScreen(Rect paramRect) {
    this.mInfo.getBoundsInScreen(paramRect);
  }
  
  public CharSequence getClassName() {
    return this.mInfo.getClassName();
  }
  
  public CharSequence getContentDescription() {
    return this.mInfo.getContentDescription();
  }
  
  public CharSequence getPackageName() {
    return this.mInfo.getPackageName();
  }
  
  public CharSequence getText() {
    return this.mInfo.getText();
  }
  
  public String getViewIdResourceName() {
    return (Build.VERSION.SDK_INT >= 18) ? this.mInfo.getViewIdResourceName() : null;
  }
  
  public int hashCode() {
    int i;
    AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
    if (accessibilityNodeInfo == null) {
      i = 0;
    } else {
      i = accessibilityNodeInfo.hashCode();
    } 
    return i;
  }
  
  public boolean isAccessibilityFocused() {
    return (Build.VERSION.SDK_INT >= 16) ? this.mInfo.isAccessibilityFocused() : false;
  }
  
  public boolean isCheckable() {
    return this.mInfo.isCheckable();
  }
  
  public boolean isChecked() {
    return this.mInfo.isChecked();
  }
  
  public boolean isClickable() {
    return this.mInfo.isClickable();
  }
  
  public boolean isEnabled() {
    return this.mInfo.isEnabled();
  }
  
  public boolean isFocusable() {
    return this.mInfo.isFocusable();
  }
  
  public boolean isFocused() {
    return this.mInfo.isFocused();
  }
  
  public boolean isLongClickable() {
    return this.mInfo.isLongClickable();
  }
  
  public boolean isPassword() {
    return this.mInfo.isPassword();
  }
  
  public boolean isScrollable() {
    return this.mInfo.isScrollable();
  }
  
  public boolean isSelected() {
    return this.mInfo.isSelected();
  }
  
  public boolean isVisibleToUser() {
    return (Build.VERSION.SDK_INT >= 16) ? this.mInfo.isVisibleToUser() : false;
  }
  
  public void recycle() {
    this.mInfo.recycle();
  }
  
  public boolean removeAction(AccessibilityActionCompat paramAccessibilityActionCompat) {
    return (Build.VERSION.SDK_INT >= 21) ? this.mInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction)paramAccessibilityActionCompat.mAction) : false;
  }
  
  public void setAccessibilityFocused(boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 16)
      this.mInfo.setAccessibilityFocused(paramBoolean); 
  }
  
  public void setBoundsInParent(Rect paramRect) {
    this.mInfo.setBoundsInParent(paramRect);
  }
  
  public void setBoundsInScreen(Rect paramRect) {
    this.mInfo.setBoundsInScreen(paramRect);
  }
  
  public void setClassName(CharSequence paramCharSequence) {
    this.mInfo.setClassName(paramCharSequence);
  }
  
  public void setClickable(boolean paramBoolean) {
    this.mInfo.setClickable(paramBoolean);
  }
  
  public void setContentDescription(CharSequence paramCharSequence) {
    this.mInfo.setContentDescription(paramCharSequence);
  }
  
  public void setEnabled(boolean paramBoolean) {
    this.mInfo.setEnabled(paramBoolean);
  }
  
  public void setFocusable(boolean paramBoolean) {
    this.mInfo.setFocusable(paramBoolean);
  }
  
  public void setFocused(boolean paramBoolean) {
    this.mInfo.setFocused(paramBoolean);
  }
  
  public void setLongClickable(boolean paramBoolean) {
    this.mInfo.setLongClickable(paramBoolean);
  }
  
  public void setPackageName(CharSequence paramCharSequence) {
    this.mInfo.setPackageName(paramCharSequence);
  }
  
  public void setParent(View paramView) {
    this.mInfo.setParent(paramView);
  }
  
  public void setScrollable(boolean paramBoolean) {
    this.mInfo.setScrollable(paramBoolean);
  }
  
  public void setSelected(boolean paramBoolean) {
    this.mInfo.setSelected(paramBoolean);
  }
  
  public void setSource(View paramView) {
    this.mInfo.setSource(paramView);
  }
  
  public void setVisibleToUser(boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 16)
      this.mInfo.setVisibleToUser(paramBoolean); 
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(super.toString());
    Rect rect = new Rect();
    getBoundsInParent(rect);
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append("; boundsInParent: ");
    stringBuilder3.append(rect);
    stringBuilder1.append(stringBuilder3.toString());
    getBoundsInScreen(rect);
    stringBuilder3 = new StringBuilder();
    stringBuilder3.append("; boundsInScreen: ");
    stringBuilder3.append(rect);
    stringBuilder1.append(stringBuilder3.toString());
    stringBuilder1.append("; packageName: ");
    stringBuilder1.append(getPackageName());
    stringBuilder1.append("; className: ");
    stringBuilder1.append(getClassName());
    stringBuilder1.append("; text: ");
    stringBuilder1.append(getText());
    stringBuilder1.append("; contentDescription: ");
    stringBuilder1.append(getContentDescription());
    stringBuilder1.append("; viewId: ");
    stringBuilder1.append(getViewIdResourceName());
    stringBuilder1.append("; checkable: ");
    stringBuilder1.append(isCheckable());
    stringBuilder1.append("; checked: ");
    stringBuilder1.append(isChecked());
    stringBuilder1.append("; focusable: ");
    stringBuilder1.append(isFocusable());
    stringBuilder1.append("; focused: ");
    stringBuilder1.append(isFocused());
    stringBuilder1.append("; selected: ");
    stringBuilder1.append(isSelected());
    stringBuilder1.append("; clickable: ");
    stringBuilder1.append(isClickable());
    stringBuilder1.append("; longClickable: ");
    stringBuilder1.append(isLongClickable());
    stringBuilder1.append("; enabled: ");
    stringBuilder1.append(isEnabled());
    stringBuilder1.append("; password: ");
    stringBuilder1.append(isPassword());
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("; scrollable: ");
    stringBuilder2.append(isScrollable());
    stringBuilder1.append(stringBuilder2.toString());
    stringBuilder1.append("; [");
    int i = getActions();
    while (i != 0) {
      int k = 1 << Integer.numberOfTrailingZeros(i);
      int j = i & (k ^ 0xFFFFFFFF);
      stringBuilder1.append(getActionSymbolicName(k));
      i = j;
      if (j != 0) {
        stringBuilder1.append(", ");
        i = j;
      } 
    } 
    stringBuilder1.append("]");
    return stringBuilder1.toString();
  }
  
  public AccessibilityNodeInfo unwrap() {
    return this.mInfo;
  }
  
  public static class AccessibilityActionCompat {
    public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
    
    public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
    
    final Object mAction;
    
    static {
      new AccessibilityActionCompat(4, null);
      new AccessibilityActionCompat(8, null);
      new AccessibilityActionCompat(16, null);
      new AccessibilityActionCompat(32, null);
      new AccessibilityActionCompat(64, null);
      new AccessibilityActionCompat(128, null);
      new AccessibilityActionCompat(256, null);
      new AccessibilityActionCompat(512, null);
      new AccessibilityActionCompat(1024, null);
      new AccessibilityActionCompat(2048, null);
      new AccessibilityActionCompat(4096, null);
      new AccessibilityActionCompat(8192, null);
      new AccessibilityActionCompat(16384, null);
      new AccessibilityActionCompat(32768, null);
      new AccessibilityActionCompat(65536, null);
      new AccessibilityActionCompat(131072, null);
      new AccessibilityActionCompat(262144, null);
      new AccessibilityActionCompat(524288, null);
      new AccessibilityActionCompat(1048576, null);
      new AccessibilityActionCompat(2097152, null);
      if (Build.VERSION.SDK_INT >= 23) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      if (Build.VERSION.SDK_INT >= 23) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      if (Build.VERSION.SDK_INT >= 23) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      if (Build.VERSION.SDK_INT >= 23) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      if (Build.VERSION.SDK_INT >= 23) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      if (Build.VERSION.SDK_INT >= 23) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      if (Build.VERSION.SDK_INT >= 23) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      if (Build.VERSION.SDK_INT >= 24) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      if (Build.VERSION.SDK_INT >= 26) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      if (Build.VERSION.SDK_INT >= 28) {
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
      } else {
        accessibilityAction1 = null;
      } 
      new AccessibilityActionCompat(accessibilityAction1);
      AccessibilityNodeInfo.AccessibilityAction accessibilityAction1 = accessibilityAction2;
      if (Build.VERSION.SDK_INT >= 28)
        accessibilityAction1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP; 
      new AccessibilityActionCompat(accessibilityAction1);
    }
    
    public AccessibilityActionCompat(int param1Int, CharSequence param1CharSequence) {
      this(param1CharSequence);
    }
    
    AccessibilityActionCompat(Object param1Object) {
      this.mAction = param1Object;
    }
    
    static {
      AccessibilityNodeInfo.AccessibilityAction accessibilityAction2 = null;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/view/accessibility/AccessibilityNodeInfoCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */