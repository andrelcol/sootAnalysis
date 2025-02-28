package com.roadtrack.onstar.floatingActionButton;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import java.util.ArrayList;

public class TouchDelegateGroup extends TouchDelegate {
  private static final Rect USELESS_HACKY_RECT = new Rect();
  
  private TouchDelegate mCurrentTouchDelegate;
  
  private boolean mEnabled;
  
  private final ArrayList<TouchDelegate> mTouchDelegates = new ArrayList<TouchDelegate>();
  
  public TouchDelegateGroup(View paramView) {
    super(USELESS_HACKY_RECT, paramView);
  }
  
  public void addTouchDelegate(TouchDelegate paramTouchDelegate) {
    this.mTouchDelegates.add(paramTouchDelegate);
  }
  
  public void clearTouchDelegates() {
    this.mTouchDelegates.clear();
    this.mCurrentTouchDelegate = null;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mEnabled : Z
    //   4: istore_3
    //   5: iconst_0
    //   6: istore #4
    //   8: iload_3
    //   9: ifne -> 14
    //   12: iconst_0
    //   13: ireturn
    //   14: aload_1
    //   15: invokevirtual getAction : ()I
    //   18: istore_2
    //   19: aconst_null
    //   20: astore #6
    //   22: iload_2
    //   23: ifeq -> 71
    //   26: iload_2
    //   27: iconst_1
    //   28: if_icmpeq -> 57
    //   31: iload_2
    //   32: iconst_2
    //   33: if_icmpeq -> 48
    //   36: iload_2
    //   37: iconst_3
    //   38: if_icmpeq -> 57
    //   41: aload #6
    //   43: astore #5
    //   45: goto -> 124
    //   48: aload_0
    //   49: getfield mCurrentTouchDelegate : Landroid/view/TouchDelegate;
    //   52: astore #5
    //   54: goto -> 124
    //   57: aload_0
    //   58: getfield mCurrentTouchDelegate : Landroid/view/TouchDelegate;
    //   61: astore #5
    //   63: aload_0
    //   64: aconst_null
    //   65: putfield mCurrentTouchDelegate : Landroid/view/TouchDelegate;
    //   68: goto -> 124
    //   71: iconst_0
    //   72: istore_2
    //   73: aload #6
    //   75: astore #5
    //   77: iload_2
    //   78: aload_0
    //   79: getfield mTouchDelegates : Ljava/util/ArrayList;
    //   82: invokevirtual size : ()I
    //   85: if_icmpge -> 124
    //   88: aload_0
    //   89: getfield mTouchDelegates : Ljava/util/ArrayList;
    //   92: iload_2
    //   93: invokevirtual get : (I)Ljava/lang/Object;
    //   96: checkcast android/view/TouchDelegate
    //   99: astore #5
    //   101: aload #5
    //   103: aload_1
    //   104: invokevirtual onTouchEvent : (Landroid/view/MotionEvent;)Z
    //   107: ifeq -> 118
    //   110: aload_0
    //   111: aload #5
    //   113: putfield mCurrentTouchDelegate : Landroid/view/TouchDelegate;
    //   116: iconst_1
    //   117: ireturn
    //   118: iinc #2, 1
    //   121: goto -> 73
    //   124: iload #4
    //   126: istore_3
    //   127: aload #5
    //   129: ifnull -> 146
    //   132: iload #4
    //   134: istore_3
    //   135: aload #5
    //   137: aload_1
    //   138: invokevirtual onTouchEvent : (Landroid/view/MotionEvent;)Z
    //   141: ifeq -> 146
    //   144: iconst_1
    //   145: istore_3
    //   146: iload_3
    //   147: ireturn
  }
  
  public void setEnabled(boolean paramBoolean) {
    this.mEnabled = paramBoolean;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/floatingActionButton/TouchDelegateGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */