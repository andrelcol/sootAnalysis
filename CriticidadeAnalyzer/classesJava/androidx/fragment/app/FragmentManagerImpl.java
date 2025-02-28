package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.collection.ArraySet;
import androidx.core.util.DebugUtils;
import androidx.core.util.LogWriter;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelStore;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
  static boolean DEBUG = false;
  
  static final Interpolator DECELERATE_CUBIC;
  
  static final Interpolator DECELERATE_QUINT = (Interpolator)new DecelerateInterpolator(2.5F);
  
  static Field sAnimationListenerField;
  
  SparseArray<Fragment> mActive;
  
  final ArrayList<Fragment> mAdded = new ArrayList<Fragment>();
  
  ArrayList<Integer> mAvailBackStackIndices;
  
  ArrayList<BackStackRecord> mBackStack;
  
  ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
  
  ArrayList<BackStackRecord> mBackStackIndices;
  
  FragmentContainer mContainer;
  
  ArrayList<Fragment> mCreatedMenus;
  
  int mCurState = 0;
  
  boolean mDestroyed;
  
  Runnable mExecCommit = new Runnable() {
      final FragmentManagerImpl this$0;
      
      public void run() {
        FragmentManagerImpl.this.execPendingActions();
      }
    };
  
  boolean mExecutingActions;
  
  boolean mHavePendingDeferredStart;
  
  FragmentHostCallback mHost;
  
  private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks = new CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder>();
  
  boolean mNeedMenuInvalidate;
  
  int mNextFragmentIndex = 0;
  
  String mNoTransactionsBecause;
  
  Fragment mParent;
  
  ArrayList<OpGenerator> mPendingActions;
  
  ArrayList<StartEnterTransitionListener> mPostponedTransactions;
  
  Fragment mPrimaryNav;
  
  FragmentManagerNonConfig mSavedNonConfig;
  
  SparseArray<Parcelable> mStateArray = null;
  
  Bundle mStateBundle = null;
  
  boolean mStateSaved;
  
  boolean mStopped;
  
  ArrayList<Fragment> mTmpAddedFragments;
  
  ArrayList<Boolean> mTmpIsPop;
  
  ArrayList<BackStackRecord> mTmpRecords;
  
  static {
    DECELERATE_CUBIC = (Interpolator)new DecelerateInterpolator(1.5F);
    new AccelerateInterpolator(2.5F);
    new AccelerateInterpolator(1.5F);
  }
  
  private void addAddedFragments(ArraySet<Fragment> paramArraySet) {
    int i = this.mCurState;
    if (i < 1)
      return; 
    int j = Math.min(i, 3);
    int k = this.mAdded.size();
    for (i = 0; i < k; i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment.mState < j) {
        moveToState(fragment, j, fragment.getNextAnim(), fragment.getNextTransition(), false);
        if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded)
          paramArraySet.add(fragment); 
      } 
    } 
  }
  
  private void animateRemoveFragment(final Fragment fragment, AnimationOrAnimator paramAnimationOrAnimator, int paramInt) {
    final View viewToAnimate = fragment.mView;
    final ViewGroup container = fragment.mContainer;
    viewGroup.startViewTransition(view);
    fragment.setStateAfterAnimating(paramInt);
    Animation animation = paramAnimationOrAnimator.animation;
    if (animation != null) {
      EndViewTransitionAnimator endViewTransitionAnimator = new EndViewTransitionAnimator(animation, viewGroup, view);
      fragment.setAnimatingAway(fragment.mView);
      endViewTransitionAnimator.setAnimationListener(new AnimationListenerWrapper(getAnimationListener((Animation)endViewTransitionAnimator)) {
            final FragmentManagerImpl this$0;
            
            final ViewGroup val$container;
            
            final Fragment val$fragment;
            
            public void onAnimationEnd(Animation param1Animation) {
              super.onAnimationEnd(param1Animation);
              container.post(new Runnable() {
                    final FragmentManagerImpl.null this$1;
                    
                    public void run() {
                      if (fragment.getAnimatingAway() != null) {
                        fragment.setAnimatingAway(null);
                        FragmentManagerImpl.null  = FragmentManagerImpl.null.this;
                        FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
                        Fragment fragment = fragment;
                        fragmentManagerImpl.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                      } 
                    }
                  });
            }
          });
      setHWLayerAnimListenerIfAlpha(view, paramAnimationOrAnimator);
      fragment.mView.startAnimation((Animation)endViewTransitionAnimator);
    } else {
      Animator animator = paramAnimationOrAnimator.animator;
      fragment.setAnimator(animator);
      animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
            final FragmentManagerImpl this$0;
            
            final ViewGroup val$container;
            
            final Fragment val$fragment;
            
            final View val$viewToAnimate;
            
            public void onAnimationEnd(Animator param1Animator) {
              container.endViewTransition(viewToAnimate);
              param1Animator = fragment.getAnimator();
              fragment.setAnimator(null);
              if (param1Animator != null && container.indexOfChild(viewToAnimate) < 0) {
                FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
                Fragment fragment = fragment;
                fragmentManagerImpl.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
              } 
            }
          });
      animator.setTarget(fragment.mView);
      setHWLayerAnimListenerIfAlpha(fragment.mView, paramAnimationOrAnimator);
      animator.start();
    } 
  }
  
  private void burpActive() {
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null)
      for (int i = sparseArray.size() - 1; i >= 0; i--) {
        if (this.mActive.valueAt(i) == null) {
          sparseArray = this.mActive;
          sparseArray.delete(sparseArray.keyAt(i));
        } 
      }  
  }
  
  private void checkStateLoss() {
    if (!isStateSaved()) {
      if (this.mNoTransactionsBecause == null)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can not perform this action inside of ");
      stringBuilder.append(this.mNoTransactionsBecause);
      throw new IllegalStateException(stringBuilder.toString());
    } 
    throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
  }
  
  private void cleanupExec() {
    this.mExecutingActions = false;
    this.mTmpIsPop.clear();
    this.mTmpRecords.clear();
  }
  
  private void dispatchStateChange(int paramInt) {
    try {
      this.mExecutingActions = true;
      moveToState(paramInt, false);
      this.mExecutingActions = false;
      return;
    } finally {
      this.mExecutingActions = false;
    } 
  }
  
  private void endAnimatingAwayFragments() {
    int i;
    SparseArray<Fragment> sparseArray = this.mActive;
    byte b = 0;
    if (sparseArray == null) {
      i = 0;
    } else {
      i = sparseArray.size();
    } 
    while (b < i) {
      Fragment fragment = (Fragment)this.mActive.valueAt(b);
      if (fragment != null)
        if (fragment.getAnimatingAway() != null) {
          int j = fragment.getStateAfterAnimating();
          View view = fragment.getAnimatingAway();
          Animation animation = view.getAnimation();
          if (animation != null) {
            animation.cancel();
            view.clearAnimation();
          } 
          fragment.setAnimatingAway(null);
          moveToState(fragment, j, 0, 0, false);
        } else if (fragment.getAnimator() != null) {
          fragment.getAnimator().end();
        }  
      b++;
    } 
  }
  
  private void ensureExecReady(boolean paramBoolean) {
    if (!this.mExecutingActions) {
      if (this.mHost != null) {
        if (Looper.myLooper() == this.mHost.getHandler().getLooper()) {
          if (!paramBoolean)
            checkStateLoss(); 
          if (this.mTmpRecords == null) {
            this.mTmpRecords = new ArrayList<BackStackRecord>();
            this.mTmpIsPop = new ArrayList<Boolean>();
          } 
          this.mExecutingActions = true;
          try {
            executePostponedTransaction(null, null);
            return;
          } finally {
            this.mExecutingActions = false;
          } 
        } 
        throw new IllegalStateException("Must be called from main thread of fragment host");
      } 
      throw new IllegalStateException("Fragment host has been destroyed");
    } 
    throw new IllegalStateException("FragmentManager is already executing transactions");
  }
  
  private static void executeOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    while (paramInt1 < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt1);
      boolean bool1 = ((Boolean)paramArrayList1.get(paramInt1)).booleanValue();
      boolean bool = true;
      if (bool1) {
        backStackRecord.bumpBackStackNesting(-1);
        if (paramInt1 != paramInt2 - 1)
          bool = false; 
        backStackRecord.executePopOps(bool);
      } else {
        backStackRecord.bumpBackStackNesting(1);
        backStackRecord.executeOps();
      } 
      paramInt1++;
    } 
  }
  
  private void executeOpsTogether(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    int k;
    int i = paramInt1;
    boolean bool1 = ((BackStackRecord)paramArrayList.get(i)).mReorderingAllowed;
    ArrayList<Fragment> arrayList = this.mTmpAddedFragments;
    if (arrayList == null) {
      this.mTmpAddedFragments = new ArrayList<Fragment>();
    } else {
      arrayList.clear();
    } 
    this.mTmpAddedFragments.addAll(this.mAdded);
    Fragment fragment = getPrimaryNavigationFragment();
    int j = i;
    boolean bool = false;
    while (j < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(j);
      if (!((Boolean)paramArrayList1.get(j)).booleanValue()) {
        fragment = backStackRecord.expandOps(this.mTmpAddedFragments, fragment);
      } else {
        fragment = backStackRecord.trackAddedFragmentsInPop(this.mTmpAddedFragments, fragment);
      } 
      if (bool || backStackRecord.mAddToBackStack) {
        bool = true;
      } else {
        bool = false;
      } 
      j++;
    } 
    this.mTmpAddedFragments.clear();
    if (!bool1)
      FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, paramInt2, false); 
    executeOps(paramArrayList, paramArrayList1, paramInt1, paramInt2);
    if (bool1) {
      ArraySet<Fragment> arraySet = new ArraySet();
      addAddedFragments(arraySet);
      k = postponePostponableTransactions(paramArrayList, paramArrayList1, paramInt1, paramInt2, arraySet);
      makeRemovedFragmentsInvisible(arraySet);
    } else {
      k = paramInt2;
    } 
    j = i;
    if (k != i) {
      j = i;
      if (bool1) {
        FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, k, true);
        moveToState(this.mCurState, true);
        j = i;
      } 
    } 
    while (j < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(j);
      if (((Boolean)paramArrayList1.get(j)).booleanValue()) {
        paramInt1 = backStackRecord.mIndex;
        if (paramInt1 >= 0) {
          freeBackStackIndex(paramInt1);
          backStackRecord.mIndex = -1;
        } 
      } 
      backStackRecord.runOnCommitRunnables();
      j++;
    } 
    if (bool)
      reportBackStackChanged(); 
  }
  
  private void executePostponedTransaction(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   4: astore #7
    //   6: aload #7
    //   8: ifnonnull -> 16
    //   11: iconst_0
    //   12: istore_3
    //   13: goto -> 22
    //   16: aload #7
    //   18: invokevirtual size : ()I
    //   21: istore_3
    //   22: iconst_0
    //   23: istore #5
    //   25: iload_3
    //   26: istore #4
    //   28: iload #5
    //   30: istore_3
    //   31: iload_3
    //   32: iload #4
    //   34: if_icmpge -> 238
    //   37: aload_0
    //   38: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   41: iload_3
    //   42: invokevirtual get : (I)Ljava/lang/Object;
    //   45: checkcast androidx/fragment/app/FragmentManagerImpl$StartEnterTransitionListener
    //   48: astore #7
    //   50: aload_1
    //   51: ifnull -> 109
    //   54: aload #7
    //   56: getfield mIsBack : Z
    //   59: ifne -> 109
    //   62: aload_1
    //   63: aload #7
    //   65: getfield mRecord : Landroidx/fragment/app/BackStackRecord;
    //   68: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   71: istore #5
    //   73: iload #5
    //   75: iconst_m1
    //   76: if_icmpeq -> 109
    //   79: aload_2
    //   80: iload #5
    //   82: invokevirtual get : (I)Ljava/lang/Object;
    //   85: checkcast java/lang/Boolean
    //   88: invokevirtual booleanValue : ()Z
    //   91: ifeq -> 109
    //   94: aload #7
    //   96: invokevirtual cancelTransaction : ()V
    //   99: iload_3
    //   100: istore #6
    //   102: iload #4
    //   104: istore #5
    //   106: goto -> 226
    //   109: aload #7
    //   111: invokevirtual isReady : ()Z
    //   114: ifne -> 152
    //   117: iload_3
    //   118: istore #6
    //   120: iload #4
    //   122: istore #5
    //   124: aload_1
    //   125: ifnull -> 226
    //   128: iload_3
    //   129: istore #6
    //   131: iload #4
    //   133: istore #5
    //   135: aload #7
    //   137: getfield mRecord : Landroidx/fragment/app/BackStackRecord;
    //   140: aload_1
    //   141: iconst_0
    //   142: aload_1
    //   143: invokevirtual size : ()I
    //   146: invokevirtual interactsWith : (Ljava/util/ArrayList;II)Z
    //   149: ifeq -> 226
    //   152: aload_0
    //   153: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   156: iload_3
    //   157: invokevirtual remove : (I)Ljava/lang/Object;
    //   160: pop
    //   161: iload_3
    //   162: iconst_1
    //   163: isub
    //   164: istore #6
    //   166: iload #4
    //   168: iconst_1
    //   169: isub
    //   170: istore #5
    //   172: aload_1
    //   173: ifnull -> 221
    //   176: aload #7
    //   178: getfield mIsBack : Z
    //   181: ifne -> 221
    //   184: aload_1
    //   185: aload #7
    //   187: getfield mRecord : Landroidx/fragment/app/BackStackRecord;
    //   190: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   193: istore_3
    //   194: iload_3
    //   195: iconst_m1
    //   196: if_icmpeq -> 221
    //   199: aload_2
    //   200: iload_3
    //   201: invokevirtual get : (I)Ljava/lang/Object;
    //   204: checkcast java/lang/Boolean
    //   207: invokevirtual booleanValue : ()Z
    //   210: ifeq -> 221
    //   213: aload #7
    //   215: invokevirtual cancelTransaction : ()V
    //   218: goto -> 226
    //   221: aload #7
    //   223: invokevirtual completeTransaction : ()V
    //   226: iload #6
    //   228: iconst_1
    //   229: iadd
    //   230: istore_3
    //   231: iload #5
    //   233: istore #4
    //   235: goto -> 31
    //   238: return
  }
  
  private Fragment findFragmentUnder(Fragment paramFragment) {
    ViewGroup viewGroup = paramFragment.mContainer;
    View view = paramFragment.mView;
    if (viewGroup != null && view != null)
      for (int i = this.mAdded.indexOf(paramFragment) - 1; i >= 0; i--) {
        paramFragment = this.mAdded.get(i);
        if (paramFragment.mContainer == viewGroup && paramFragment.mView != null)
          return paramFragment; 
      }  
    return null;
  }
  
  private void forcePostponedTransactions() {
    if (this.mPostponedTransactions != null)
      while (!this.mPostponedTransactions.isEmpty())
        ((StartEnterTransitionListener)this.mPostponedTransactions.remove(0)).completeTransaction();  
  }
  
  private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mPendingActions : Ljava/util/ArrayList;
    //   6: astore #6
    //   8: iconst_0
    //   9: istore_3
    //   10: aload #6
    //   12: ifnull -> 101
    //   15: aload_0
    //   16: getfield mPendingActions : Ljava/util/ArrayList;
    //   19: invokevirtual size : ()I
    //   22: ifne -> 28
    //   25: goto -> 101
    //   28: aload_0
    //   29: getfield mPendingActions : Ljava/util/ArrayList;
    //   32: invokevirtual size : ()I
    //   35: istore #4
    //   37: iconst_0
    //   38: istore #5
    //   40: iload_3
    //   41: iload #4
    //   43: if_icmpge -> 75
    //   46: iload #5
    //   48: aload_0
    //   49: getfield mPendingActions : Ljava/util/ArrayList;
    //   52: iload_3
    //   53: invokevirtual get : (I)Ljava/lang/Object;
    //   56: checkcast androidx/fragment/app/FragmentManagerImpl$OpGenerator
    //   59: aload_1
    //   60: aload_2
    //   61: invokeinterface generateOps : (Ljava/util/ArrayList;Ljava/util/ArrayList;)Z
    //   66: ior
    //   67: istore #5
    //   69: iinc #3, 1
    //   72: goto -> 40
    //   75: aload_0
    //   76: getfield mPendingActions : Ljava/util/ArrayList;
    //   79: invokevirtual clear : ()V
    //   82: aload_0
    //   83: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   86: invokevirtual getHandler : ()Landroid/os/Handler;
    //   89: aload_0
    //   90: getfield mExecCommit : Ljava/lang/Runnable;
    //   93: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   96: aload_0
    //   97: monitorexit
    //   98: iload #5
    //   100: ireturn
    //   101: aload_0
    //   102: monitorexit
    //   103: iconst_0
    //   104: ireturn
    //   105: astore_1
    //   106: aload_0
    //   107: monitorexit
    //   108: aload_1
    //   109: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	105	finally
    //   15	25	105	finally
    //   28	37	105	finally
    //   46	69	105	finally
    //   75	98	105	finally
    //   101	103	105	finally
    //   106	108	105	finally
  }
  
  private static Animation.AnimationListener getAnimationListener(Animation paramAnimation) {
    try {
      if (sAnimationListenerField == null) {
        sAnimationListenerField = Animation.class.getDeclaredField("mListener");
        sAnimationListenerField.setAccessible(true);
      } 
      Animation.AnimationListener animationListener = (Animation.AnimationListener)sAnimationListenerField.get(paramAnimation);
    } catch (NoSuchFieldException|IllegalAccessException noSuchFieldException) {
      noSuchFieldException = null;
    } 
    return (Animation.AnimationListener)noSuchFieldException;
  }
  
  static AnimationOrAnimator makeFadeAnimation(Context paramContext, float paramFloat1, float paramFloat2) {
    AlphaAnimation alphaAnimation = new AlphaAnimation(paramFloat1, paramFloat2);
    alphaAnimation.setInterpolator(DECELERATE_CUBIC);
    alphaAnimation.setDuration(220L);
    return new AnimationOrAnimator((Animation)alphaAnimation);
  }
  
  static AnimationOrAnimator makeOpenCloseAnimation(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    AnimationSet animationSet = new AnimationSet(false);
    ScaleAnimation scaleAnimation = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    scaleAnimation.setInterpolator(DECELERATE_QUINT);
    scaleAnimation.setDuration(220L);
    animationSet.addAnimation((Animation)scaleAnimation);
    AlphaAnimation alphaAnimation = new AlphaAnimation(paramFloat3, paramFloat4);
    alphaAnimation.setInterpolator(DECELERATE_CUBIC);
    alphaAnimation.setDuration(220L);
    animationSet.addAnimation((Animation)alphaAnimation);
    return new AnimationOrAnimator((Animation)animationSet);
  }
  
  private void makeRemovedFragmentsInvisible(ArraySet<Fragment> paramArraySet) {
    int i = paramArraySet.size();
    for (byte b = 0; b < i; b++) {
      Fragment fragment = (Fragment)paramArraySet.valueAt(b);
      if (!fragment.mAdded) {
        View view = fragment.getView();
        fragment.mPostponedAlpha = view.getAlpha();
        view.setAlpha(0.0F);
      } 
    } 
  }
  
  static boolean modifiesAlpha(Animator paramAnimator) {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder;
    if (paramAnimator == null)
      return false; 
    if (paramAnimator instanceof ValueAnimator) {
      arrayOfPropertyValuesHolder = ((ValueAnimator)paramAnimator).getValues();
      for (byte b = 0; b < arrayOfPropertyValuesHolder.length; b++) {
        if ("alpha".equals(arrayOfPropertyValuesHolder[b].getPropertyName()))
          return true; 
      } 
    } else if (arrayOfPropertyValuesHolder instanceof AnimatorSet) {
      ArrayList<Animator> arrayList = ((AnimatorSet)arrayOfPropertyValuesHolder).getChildAnimations();
      for (byte b = 0; b < arrayList.size(); b++) {
        if (modifiesAlpha(arrayList.get(b)))
          return true; 
      } 
    } 
    return false;
  }
  
  static boolean modifiesAlpha(AnimationOrAnimator paramAnimationOrAnimator) {
    List list;
    Animation animation = paramAnimationOrAnimator.animation;
    if (animation instanceof AlphaAnimation)
      return true; 
    if (animation instanceof AnimationSet) {
      list = ((AnimationSet)animation).getAnimations();
      for (byte b = 0; b < list.size(); b++) {
        if (list.get(b) instanceof AlphaAnimation)
          return true; 
      } 
      return false;
    } 
    return modifiesAlpha(((AnimationOrAnimator)list).animator);
  }
  
  private boolean popBackStackImmediate(String paramString, int paramInt1, int paramInt2) {
    execPendingActions();
    ensureExecReady(true);
    Fragment fragment = this.mPrimaryNav;
    if (fragment != null && paramInt1 < 0 && paramString == null) {
      FragmentManager fragmentManager = fragment.peekChildFragmentManager();
      if (fragmentManager != null && fragmentManager.popBackStackImmediate())
        return true; 
    } 
    boolean bool = popBackStackState(this.mTmpRecords, this.mTmpIsPop, paramString, paramInt1, paramInt2);
    if (bool) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  private int postponePostponableTransactions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, ArraySet<Fragment> paramArraySet) {
    int j = paramInt2 - 1;
    int i;
    for (i = paramInt2; j >= paramInt1; i = k) {
      boolean bool;
      BackStackRecord backStackRecord = paramArrayList.get(j);
      boolean bool1 = ((Boolean)paramArrayList1.get(j)).booleanValue();
      if (backStackRecord.isPostponed() && !backStackRecord.interactsWith(paramArrayList, j + 1, paramInt2)) {
        bool = true;
      } else {
        bool = false;
      } 
      int k = i;
      if (bool) {
        if (this.mPostponedTransactions == null)
          this.mPostponedTransactions = new ArrayList<StartEnterTransitionListener>(); 
        StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, bool1);
        this.mPostponedTransactions.add(startEnterTransitionListener);
        backStackRecord.setOnStartPostponedListener(startEnterTransitionListener);
        if (bool1) {
          backStackRecord.executeOps();
        } else {
          backStackRecord.executePopOps(false);
        } 
        k = i - 1;
        if (j != k) {
          paramArrayList.remove(j);
          paramArrayList.add(k, backStackRecord);
        } 
        addAddedFragments(paramArraySet);
      } 
      j--;
    } 
    return i;
  }
  
  private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    if (paramArrayList == null || paramArrayList.isEmpty())
      return; 
    if (paramArrayList1 != null && paramArrayList.size() == paramArrayList1.size()) {
      executePostponedTransaction(paramArrayList, paramArrayList1);
      int k = paramArrayList.size();
      int i = 0;
      int j;
      for (j = 0; i < k; j = m) {
        int n = i;
        int m = j;
        if (!((BackStackRecord)paramArrayList.get(i)).mReorderingAllowed) {
          if (j != i)
            executeOpsTogether(paramArrayList, paramArrayList1, j, i); 
          j = i + 1;
          m = j;
          if (((Boolean)paramArrayList1.get(i)).booleanValue())
            while (true) {
              m = j;
              if (j < k) {
                m = j;
                if (((Boolean)paramArrayList1.get(j)).booleanValue()) {
                  m = j;
                  if (!((BackStackRecord)paramArrayList.get(j)).mReorderingAllowed) {
                    j++;
                    continue;
                  } 
                } 
              } 
              break;
            }  
          executeOpsTogether(paramArrayList, paramArrayList1, i, m);
          n = m - 1;
        } 
        i = n + 1;
      } 
      if (j != k)
        executeOpsTogether(paramArrayList, paramArrayList1, j, k); 
      return;
    } 
    throw new IllegalStateException("Internal error with the back stack records");
  }
  
  public static int reverseTransit(int paramInt) {
    char c = ' ';
    if (paramInt != 4097)
      if (paramInt != 4099) {
        if (paramInt != 8194) {
          c = Character.MIN_VALUE;
        } else {
          c = 'ခ';
        } 
      } else {
        c = 'ဃ';
      }  
    return c;
  }
  
  private static void setHWLayerAnimListenerIfAlpha(View paramView, AnimationOrAnimator paramAnimationOrAnimator) {
    if (paramView != null && paramAnimationOrAnimator != null && shouldRunOnHWLayer(paramView, paramAnimationOrAnimator)) {
      Animator animator = paramAnimationOrAnimator.animator;
      if (animator != null) {
        animator.addListener((Animator.AnimatorListener)new AnimatorOnHWLayerIfNeededListener(paramView));
      } else {
        Animation.AnimationListener animationListener = getAnimationListener(paramAnimationOrAnimator.animation);
        paramView.setLayerType(2, null);
        paramAnimationOrAnimator.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramView, animationListener));
      } 
    } 
  }
  
  private static void setRetaining(FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    if (paramFragmentManagerNonConfig == null)
      return; 
    List<Fragment> list1 = paramFragmentManagerNonConfig.getFragments();
    if (list1 != null) {
      Iterator<Fragment> iterator = list1.iterator();
      while (iterator.hasNext())
        ((Fragment)iterator.next()).mRetaining = true; 
    } 
    List<FragmentManagerNonConfig> list = paramFragmentManagerNonConfig.getChildNonConfigs();
    if (list != null) {
      Iterator<FragmentManagerNonConfig> iterator = list.iterator();
      while (iterator.hasNext())
        setRetaining(iterator.next()); 
    } 
  }
  
  static boolean shouldRunOnHWLayer(View paramView, AnimationOrAnimator paramAnimationOrAnimator) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramView != null)
      if (paramAnimationOrAnimator == null) {
        bool1 = bool2;
      } else {
        bool1 = bool2;
        if (Build.VERSION.SDK_INT >= 19) {
          bool1 = bool2;
          if (paramView.getLayerType() == 0) {
            bool1 = bool2;
            if (ViewCompat.hasOverlappingRendering(paramView)) {
              bool1 = bool2;
              if (modifiesAlpha(paramAnimationOrAnimator))
                bool1 = true; 
            } 
          } 
        } 
      }  
    return bool1;
  }
  
  private void throwException(RuntimeException paramRuntimeException) {
    paramRuntimeException.getMessage();
    PrintWriter printWriter = new PrintWriter((Writer)new LogWriter("FragmentManager"));
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      try {
        fragmentHostCallback.onDump("  ", null, printWriter, new String[0]);
      } catch (Exception exception) {}
    } else {
      dump("  ", null, (PrintWriter)exception, new String[0]);
    } 
    throw paramRuntimeException;
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean) {
    if (paramInt != 4097) {
      if (paramInt != 4099) {
        if (paramInt != 8194) {
          paramInt = -1;
        } else if (paramBoolean) {
          paramInt = 3;
        } else {
          paramInt = 4;
        } 
      } else if (paramBoolean) {
        paramInt = 5;
      } else {
        paramInt = 6;
      } 
    } else if (paramBoolean) {
      paramInt = 1;
    } else {
      paramInt = 2;
    } 
    return paramInt;
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord) {
    if (this.mBackStack == null)
      this.mBackStack = new ArrayList<BackStackRecord>(); 
    this.mBackStack.add(paramBackStackRecord);
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("add: ");
      stringBuilder.append(paramFragment);
      stringBuilder.toString();
    } 
    makeActive(paramFragment);
    if (!paramFragment.mDetached)
      if (!this.mAdded.contains(paramFragment)) {
        synchronized (this.mAdded) {
          this.mAdded.add(paramFragment);
          paramFragment.mAdded = true;
          paramFragment.mRemoving = false;
          if (paramFragment.mView == null)
            paramFragment.mHiddenChanged = false; 
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
            this.mNeedMenuInvalidate = true; 
          if (paramBoolean)
            moveToState(paramFragment); 
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Fragment already added: ");
        stringBuilder.append(paramFragment);
        throw new IllegalStateException(stringBuilder.toString());
      }  
  }
  
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   6: ifnull -> 106
    //   9: aload_0
    //   10: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   13: invokevirtual size : ()I
    //   16: ifgt -> 22
    //   19: goto -> 106
    //   22: aload_0
    //   23: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   26: aload_0
    //   27: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   30: invokevirtual size : ()I
    //   33: iconst_1
    //   34: isub
    //   35: invokevirtual remove : (I)Ljava/lang/Object;
    //   38: checkcast java/lang/Integer
    //   41: invokevirtual intValue : ()I
    //   44: istore_2
    //   45: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   48: ifeq -> 92
    //   51: new java/lang/StringBuilder
    //   54: astore_3
    //   55: aload_3
    //   56: invokespecial <init> : ()V
    //   59: aload_3
    //   60: ldc_w 'Adding back stack index '
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload_3
    //   68: iload_2
    //   69: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload_3
    //   74: ldc_w ' with '
    //   77: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: pop
    //   81: aload_3
    //   82: aload_1
    //   83: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: aload_3
    //   88: invokevirtual toString : ()Ljava/lang/String;
    //   91: pop
    //   92: aload_0
    //   93: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   96: iload_2
    //   97: aload_1
    //   98: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   101: pop
    //   102: aload_0
    //   103: monitorexit
    //   104: iload_2
    //   105: ireturn
    //   106: aload_0
    //   107: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   110: ifnonnull -> 126
    //   113: new java/util/ArrayList
    //   116: astore_3
    //   117: aload_3
    //   118: invokespecial <init> : ()V
    //   121: aload_0
    //   122: aload_3
    //   123: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   126: aload_0
    //   127: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   130: invokevirtual size : ()I
    //   133: istore_2
    //   134: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   137: ifeq -> 181
    //   140: new java/lang/StringBuilder
    //   143: astore_3
    //   144: aload_3
    //   145: invokespecial <init> : ()V
    //   148: aload_3
    //   149: ldc_w 'Setting back stack index '
    //   152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: pop
    //   156: aload_3
    //   157: iload_2
    //   158: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   161: pop
    //   162: aload_3
    //   163: ldc_w ' to '
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: aload_3
    //   171: aload_1
    //   172: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   175: pop
    //   176: aload_3
    //   177: invokevirtual toString : ()Ljava/lang/String;
    //   180: pop
    //   181: aload_0
    //   182: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   185: aload_1
    //   186: invokevirtual add : (Ljava/lang/Object;)Z
    //   189: pop
    //   190: aload_0
    //   191: monitorexit
    //   192: iload_2
    //   193: ireturn
    //   194: astore_1
    //   195: aload_0
    //   196: monitorexit
    //   197: aload_1
    //   198: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	194	finally
    //   22	92	194	finally
    //   92	104	194	finally
    //   106	126	194	finally
    //   126	181	194	finally
    //   181	192	194	finally
    //   195	197	194	finally
  }
  
  public void attachController(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment) {
    if (this.mHost == null) {
      this.mHost = paramFragmentHostCallback;
      this.mContainer = paramFragmentContainer;
      this.mParent = paramFragment;
      return;
    } 
    throw new IllegalStateException("Already attached");
  }
  
  public void attachFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("attach: ");
      stringBuilder.append(paramFragment);
      stringBuilder.toString();
    } 
    if (paramFragment.mDetached) {
      paramFragment.mDetached = false;
      if (!paramFragment.mAdded)
        if (!this.mAdded.contains(paramFragment)) {
          if (DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("add from attach: ");
            stringBuilder.append(paramFragment);
            stringBuilder.toString();
          } 
          synchronized (this.mAdded) {
            this.mAdded.add(paramFragment);
            paramFragment.mAdded = true;
            if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
              this.mNeedMenuInvalidate = true; 
          } 
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Fragment already added: ");
          stringBuilder.append(paramFragment);
          throw new IllegalStateException(stringBuilder.toString());
        }  
    } 
  }
  
  public FragmentTransaction beginTransaction() {
    return new BackStackRecord(this);
  }
  
  void completeExecute(BackStackRecord paramBackStackRecord, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    if (paramBoolean1) {
      paramBackStackRecord.executePopOps(paramBoolean3);
    } else {
      paramBackStackRecord.executeOps();
    } 
    ArrayList<BackStackRecord> arrayList = new ArrayList(1);
    ArrayList<Boolean> arrayList1 = new ArrayList(1);
    arrayList.add(paramBackStackRecord);
    arrayList1.add(Boolean.valueOf(paramBoolean1));
    if (paramBoolean2)
      FragmentTransition.startTransitions(this, arrayList, arrayList1, 0, 1, true); 
    if (paramBoolean3)
      moveToState(this.mCurState, true); 
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null) {
      int i = sparseArray.size();
      for (byte b = 0; b < i; b++) {
        Fragment fragment = (Fragment)this.mActive.valueAt(b);
        if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && paramBackStackRecord.interactsWith(fragment.mContainerId)) {
          float f = fragment.mPostponedAlpha;
          if (f > 0.0F)
            fragment.mView.setAlpha(f); 
          if (paramBoolean3) {
            fragment.mPostponedAlpha = 0.0F;
          } else {
            fragment.mPostponedAlpha = -1.0F;
            fragment.mIsNewlyAdded = false;
          } 
        } 
      } 
    } 
  }
  
  void completeShowHideFragment(Fragment paramFragment) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mView : Landroid/view/View;
    //   4: ifnull -> 213
    //   7: aload_0
    //   8: aload_1
    //   9: aload_1
    //   10: invokevirtual getNextTransition : ()I
    //   13: aload_1
    //   14: getfield mHidden : Z
    //   17: iconst_1
    //   18: ixor
    //   19: aload_1
    //   20: invokevirtual getNextTransitionStyle : ()I
    //   23: invokevirtual loadAnimation : (Landroidx/fragment/app/Fragment;IZI)Landroidx/fragment/app/FragmentManagerImpl$AnimationOrAnimator;
    //   26: astore_3
    //   27: aload_3
    //   28: ifnull -> 141
    //   31: aload_3
    //   32: getfield animator : Landroid/animation/Animator;
    //   35: astore #4
    //   37: aload #4
    //   39: ifnull -> 141
    //   42: aload #4
    //   44: aload_1
    //   45: getfield mView : Landroid/view/View;
    //   48: invokevirtual setTarget : (Ljava/lang/Object;)V
    //   51: aload_1
    //   52: getfield mHidden : Z
    //   55: ifeq -> 115
    //   58: aload_1
    //   59: invokevirtual isHideReplaced : ()Z
    //   62: ifeq -> 73
    //   65: aload_1
    //   66: iconst_0
    //   67: invokevirtual setHideReplaced : (Z)V
    //   70: goto -> 123
    //   73: aload_1
    //   74: getfield mContainer : Landroid/view/ViewGroup;
    //   77: astore #4
    //   79: aload_1
    //   80: getfield mView : Landroid/view/View;
    //   83: astore #5
    //   85: aload #4
    //   87: aload #5
    //   89: invokevirtual startViewTransition : (Landroid/view/View;)V
    //   92: aload_3
    //   93: getfield animator : Landroid/animation/Animator;
    //   96: new androidx/fragment/app/FragmentManagerImpl$4
    //   99: dup
    //   100: aload_0
    //   101: aload #4
    //   103: aload #5
    //   105: aload_1
    //   106: invokespecial <init> : (Landroidx/fragment/app/FragmentManagerImpl;Landroid/view/ViewGroup;Landroid/view/View;Landroidx/fragment/app/Fragment;)V
    //   109: invokevirtual addListener : (Landroid/animation/Animator$AnimatorListener;)V
    //   112: goto -> 123
    //   115: aload_1
    //   116: getfield mView : Landroid/view/View;
    //   119: iconst_0
    //   120: invokevirtual setVisibility : (I)V
    //   123: aload_1
    //   124: getfield mView : Landroid/view/View;
    //   127: aload_3
    //   128: invokestatic setHWLayerAnimListenerIfAlpha : (Landroid/view/View;Landroidx/fragment/app/FragmentManagerImpl$AnimationOrAnimator;)V
    //   131: aload_3
    //   132: getfield animator : Landroid/animation/Animator;
    //   135: invokevirtual start : ()V
    //   138: goto -> 213
    //   141: aload_3
    //   142: ifnull -> 171
    //   145: aload_1
    //   146: getfield mView : Landroid/view/View;
    //   149: aload_3
    //   150: invokestatic setHWLayerAnimListenerIfAlpha : (Landroid/view/View;Landroidx/fragment/app/FragmentManagerImpl$AnimationOrAnimator;)V
    //   153: aload_1
    //   154: getfield mView : Landroid/view/View;
    //   157: aload_3
    //   158: getfield animation : Landroid/view/animation/Animation;
    //   161: invokevirtual startAnimation : (Landroid/view/animation/Animation;)V
    //   164: aload_3
    //   165: getfield animation : Landroid/view/animation/Animation;
    //   168: invokevirtual start : ()V
    //   171: aload_1
    //   172: getfield mHidden : Z
    //   175: ifeq -> 191
    //   178: aload_1
    //   179: invokevirtual isHideReplaced : ()Z
    //   182: ifne -> 191
    //   185: bipush #8
    //   187: istore_2
    //   188: goto -> 193
    //   191: iconst_0
    //   192: istore_2
    //   193: aload_1
    //   194: getfield mView : Landroid/view/View;
    //   197: iload_2
    //   198: invokevirtual setVisibility : (I)V
    //   201: aload_1
    //   202: invokevirtual isHideReplaced : ()Z
    //   205: ifeq -> 213
    //   208: aload_1
    //   209: iconst_0
    //   210: invokevirtual setHideReplaced : (Z)V
    //   213: aload_1
    //   214: getfield mAdded : Z
    //   217: ifeq -> 239
    //   220: aload_1
    //   221: getfield mHasMenu : Z
    //   224: ifeq -> 239
    //   227: aload_1
    //   228: getfield mMenuVisible : Z
    //   231: ifeq -> 239
    //   234: aload_0
    //   235: iconst_1
    //   236: putfield mNeedMenuInvalidate : Z
    //   239: aload_1
    //   240: iconst_0
    //   241: putfield mHiddenChanged : Z
    //   244: aload_1
    //   245: aload_1
    //   246: getfield mHidden : Z
    //   249: invokevirtual onHiddenChanged : (Z)V
    //   252: return
  }
  
  public void detachFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("detach: ");
      stringBuilder.append(paramFragment);
      stringBuilder.toString();
    } 
    if (!paramFragment.mDetached) {
      paramFragment.mDetached = true;
      if (paramFragment.mAdded) {
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("remove from detach: ");
          stringBuilder.append(paramFragment);
          stringBuilder.toString();
        } 
        synchronized (this.mAdded) {
          this.mAdded.remove(paramFragment);
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
            this.mNeedMenuInvalidate = true; 
          paramFragment.mAdded = false;
        } 
      } 
    } 
  }
  
  public void dispatchActivityCreated() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(2);
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration) {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performConfigurationChanged(paramConfiguration); 
    } 
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem) {
    if (this.mCurState < 1)
      return false; 
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null && fragment.performContextItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  public void dispatchCreate() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(1);
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
    int i = this.mCurState;
    boolean bool1 = false;
    if (i < 1)
      return false; 
    ArrayList<Fragment> arrayList = null;
    i = 0;
    boolean bool2;
    for (bool2 = false; i < this.mAdded.size(); bool2 = bool) {
      Fragment fragment = this.mAdded.get(i);
      ArrayList<Fragment> arrayList1 = arrayList;
      boolean bool = bool2;
      if (fragment != null) {
        arrayList1 = arrayList;
        bool = bool2;
        if (fragment.performCreateOptionsMenu(paramMenu, paramMenuInflater)) {
          arrayList1 = arrayList;
          if (arrayList == null)
            arrayList1 = new ArrayList(); 
          arrayList1.add(fragment);
          bool = true;
        } 
      } 
      i++;
      arrayList = arrayList1;
    } 
    if (this.mCreatedMenus != null)
      for (i = bool1; i < this.mCreatedMenus.size(); i++) {
        Fragment fragment = this.mCreatedMenus.get(i);
        if (arrayList == null || !arrayList.contains(fragment))
          fragment.onDestroyOptionsMenu(); 
      }  
    this.mCreatedMenus = arrayList;
    return bool2;
  }
  
  public void dispatchDestroy() {
    this.mDestroyed = true;
    execPendingActions();
    dispatchStateChange(0);
    this.mHost = null;
    this.mContainer = null;
    this.mParent = null;
  }
  
  public void dispatchDestroyView() {
    dispatchStateChange(1);
  }
  
  public void dispatchLowMemory() {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performLowMemory(); 
    } 
  }
  
  public void dispatchMultiWindowModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performMultiWindowModeChanged(paramBoolean); 
    } 
  }
  
  void dispatchOnFragmentActivityCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentActivityCreated(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentActivityCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentAttached(paramFragment, paramContext, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentCreated(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentDestroyed(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDestroyed(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentDestroyed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentDetached(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDetached(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentDetached(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPaused(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPaused(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentPaused(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPreAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreAttached(paramFragment, paramContext, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentPreAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentPreCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreCreated(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentPreCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentResumed(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentResumed(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentResumed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentSaveInstanceState(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentSaveInstanceState(paramFragment, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentSaveInstanceState(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentStarted(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStarted(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentStarted(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentStopped(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStopped(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentStopped(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentViewCreated(Fragment paramFragment, View paramView, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewCreated(paramFragment, paramView, paramBundle, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentViewCreated(this, paramFragment, paramView, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentViewDestroyed(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewDestroyed(paramFragment, true); 
    } 
    for (FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksHolder : this.mLifecycleCallbacks) {
      if (!paramBoolean || fragmentLifecycleCallbacksHolder.mRecursive)
        fragmentLifecycleCallbacksHolder.mCallback.onFragmentViewDestroyed(this, paramFragment); 
    } 
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem) {
    if (this.mCurState < 1)
      return false; 
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null && fragment.performOptionsItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu) {
    if (this.mCurState < 1)
      return; 
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performOptionsMenuClosed(paramMenu); 
    } 
  }
  
  public void dispatchPause() {
    dispatchStateChange(3);
  }
  
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performPictureInPictureModeChanged(paramBoolean); 
    } 
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu) {
    int i = this.mCurState;
    byte b = 0;
    if (i < 1)
      return false; 
    boolean bool;
    for (bool = false; b < this.mAdded.size(); bool = bool1) {
      Fragment fragment = this.mAdded.get(b);
      boolean bool1 = bool;
      if (fragment != null) {
        bool1 = bool;
        if (fragment.performPrepareOptionsMenu(paramMenu))
          bool1 = true; 
      } 
      b++;
    } 
    return bool;
  }
  
  public void dispatchResume() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(4);
  }
  
  public void dispatchStart() {
    this.mStateSaved = false;
    this.mStopped = false;
    dispatchStateChange(3);
  }
  
  public void dispatchStop() {
    this.mStopped = true;
    dispatchStateChange(2);
  }
  
  void doPendingDeferredStart() {
    if (this.mHavePendingDeferredStart) {
      this.mHavePendingDeferredStart = false;
      startPendingDeferredFragments();
    } 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #8
    //   9: aload #8
    //   11: aload_1
    //   12: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: pop
    //   16: aload #8
    //   18: ldc_w '    '
    //   21: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload #8
    //   27: invokevirtual toString : ()Ljava/lang/String;
    //   30: astore #8
    //   32: aload_0
    //   33: getfield mActive : Landroid/util/SparseArray;
    //   36: astore #9
    //   38: iconst_0
    //   39: istore #6
    //   41: aload #9
    //   43: ifnull -> 165
    //   46: aload #9
    //   48: invokevirtual size : ()I
    //   51: istore #7
    //   53: iload #7
    //   55: ifle -> 165
    //   58: aload_3
    //   59: aload_1
    //   60: invokevirtual print : (Ljava/lang/String;)V
    //   63: aload_3
    //   64: ldc_w 'Active Fragments in '
    //   67: invokevirtual print : (Ljava/lang/String;)V
    //   70: aload_3
    //   71: aload_0
    //   72: invokestatic identityHashCode : (Ljava/lang/Object;)I
    //   75: invokestatic toHexString : (I)Ljava/lang/String;
    //   78: invokevirtual print : (Ljava/lang/String;)V
    //   81: aload_3
    //   82: ldc_w ':'
    //   85: invokevirtual println : (Ljava/lang/String;)V
    //   88: iconst_0
    //   89: istore #5
    //   91: iload #5
    //   93: iload #7
    //   95: if_icmpge -> 165
    //   98: aload_0
    //   99: getfield mActive : Landroid/util/SparseArray;
    //   102: iload #5
    //   104: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   107: checkcast androidx/fragment/app/Fragment
    //   110: astore #9
    //   112: aload_3
    //   113: aload_1
    //   114: invokevirtual print : (Ljava/lang/String;)V
    //   117: aload_3
    //   118: ldc_w '  #'
    //   121: invokevirtual print : (Ljava/lang/String;)V
    //   124: aload_3
    //   125: iload #5
    //   127: invokevirtual print : (I)V
    //   130: aload_3
    //   131: ldc_w ': '
    //   134: invokevirtual print : (Ljava/lang/String;)V
    //   137: aload_3
    //   138: aload #9
    //   140: invokevirtual println : (Ljava/lang/Object;)V
    //   143: aload #9
    //   145: ifnull -> 159
    //   148: aload #9
    //   150: aload #8
    //   152: aload_2
    //   153: aload_3
    //   154: aload #4
    //   156: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   159: iinc #5, 1
    //   162: goto -> 91
    //   165: aload_0
    //   166: getfield mAdded : Ljava/util/ArrayList;
    //   169: invokevirtual size : ()I
    //   172: istore #7
    //   174: iload #7
    //   176: ifle -> 255
    //   179: aload_3
    //   180: aload_1
    //   181: invokevirtual print : (Ljava/lang/String;)V
    //   184: aload_3
    //   185: ldc_w 'Added Fragments:'
    //   188: invokevirtual println : (Ljava/lang/String;)V
    //   191: iconst_0
    //   192: istore #5
    //   194: iload #5
    //   196: iload #7
    //   198: if_icmpge -> 255
    //   201: aload_0
    //   202: getfield mAdded : Ljava/util/ArrayList;
    //   205: iload #5
    //   207: invokevirtual get : (I)Ljava/lang/Object;
    //   210: checkcast androidx/fragment/app/Fragment
    //   213: astore #9
    //   215: aload_3
    //   216: aload_1
    //   217: invokevirtual print : (Ljava/lang/String;)V
    //   220: aload_3
    //   221: ldc_w '  #'
    //   224: invokevirtual print : (Ljava/lang/String;)V
    //   227: aload_3
    //   228: iload #5
    //   230: invokevirtual print : (I)V
    //   233: aload_3
    //   234: ldc_w ': '
    //   237: invokevirtual print : (Ljava/lang/String;)V
    //   240: aload_3
    //   241: aload #9
    //   243: invokevirtual toString : ()Ljava/lang/String;
    //   246: invokevirtual println : (Ljava/lang/String;)V
    //   249: iinc #5, 1
    //   252: goto -> 194
    //   255: aload_0
    //   256: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   259: astore #9
    //   261: aload #9
    //   263: ifnull -> 354
    //   266: aload #9
    //   268: invokevirtual size : ()I
    //   271: istore #7
    //   273: iload #7
    //   275: ifle -> 354
    //   278: aload_3
    //   279: aload_1
    //   280: invokevirtual print : (Ljava/lang/String;)V
    //   283: aload_3
    //   284: ldc_w 'Fragments Created Menus:'
    //   287: invokevirtual println : (Ljava/lang/String;)V
    //   290: iconst_0
    //   291: istore #5
    //   293: iload #5
    //   295: iload #7
    //   297: if_icmpge -> 354
    //   300: aload_0
    //   301: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   304: iload #5
    //   306: invokevirtual get : (I)Ljava/lang/Object;
    //   309: checkcast androidx/fragment/app/Fragment
    //   312: astore #9
    //   314: aload_3
    //   315: aload_1
    //   316: invokevirtual print : (Ljava/lang/String;)V
    //   319: aload_3
    //   320: ldc_w '  #'
    //   323: invokevirtual print : (Ljava/lang/String;)V
    //   326: aload_3
    //   327: iload #5
    //   329: invokevirtual print : (I)V
    //   332: aload_3
    //   333: ldc_w ': '
    //   336: invokevirtual print : (Ljava/lang/String;)V
    //   339: aload_3
    //   340: aload #9
    //   342: invokevirtual toString : ()Ljava/lang/String;
    //   345: invokevirtual println : (Ljava/lang/String;)V
    //   348: iinc #5, 1
    //   351: goto -> 293
    //   354: aload_0
    //   355: getfield mBackStack : Ljava/util/ArrayList;
    //   358: astore #9
    //   360: aload #9
    //   362: ifnull -> 464
    //   365: aload #9
    //   367: invokevirtual size : ()I
    //   370: istore #7
    //   372: iload #7
    //   374: ifle -> 464
    //   377: aload_3
    //   378: aload_1
    //   379: invokevirtual print : (Ljava/lang/String;)V
    //   382: aload_3
    //   383: ldc_w 'Back Stack:'
    //   386: invokevirtual println : (Ljava/lang/String;)V
    //   389: iconst_0
    //   390: istore #5
    //   392: iload #5
    //   394: iload #7
    //   396: if_icmpge -> 464
    //   399: aload_0
    //   400: getfield mBackStack : Ljava/util/ArrayList;
    //   403: iload #5
    //   405: invokevirtual get : (I)Ljava/lang/Object;
    //   408: checkcast androidx/fragment/app/BackStackRecord
    //   411: astore #9
    //   413: aload_3
    //   414: aload_1
    //   415: invokevirtual print : (Ljava/lang/String;)V
    //   418: aload_3
    //   419: ldc_w '  #'
    //   422: invokevirtual print : (Ljava/lang/String;)V
    //   425: aload_3
    //   426: iload #5
    //   428: invokevirtual print : (I)V
    //   431: aload_3
    //   432: ldc_w ': '
    //   435: invokevirtual print : (Ljava/lang/String;)V
    //   438: aload_3
    //   439: aload #9
    //   441: invokevirtual toString : ()Ljava/lang/String;
    //   444: invokevirtual println : (Ljava/lang/String;)V
    //   447: aload #9
    //   449: aload #8
    //   451: aload_2
    //   452: aload_3
    //   453: aload #4
    //   455: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   458: iinc #5, 1
    //   461: goto -> 392
    //   464: aload_0
    //   465: monitorenter
    //   466: aload_0
    //   467: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   470: ifnull -> 558
    //   473: aload_0
    //   474: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   477: invokevirtual size : ()I
    //   480: istore #7
    //   482: iload #7
    //   484: ifle -> 558
    //   487: aload_3
    //   488: aload_1
    //   489: invokevirtual print : (Ljava/lang/String;)V
    //   492: aload_3
    //   493: ldc_w 'Back Stack Indices:'
    //   496: invokevirtual println : (Ljava/lang/String;)V
    //   499: iconst_0
    //   500: istore #5
    //   502: iload #5
    //   504: iload #7
    //   506: if_icmpge -> 558
    //   509: aload_0
    //   510: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   513: iload #5
    //   515: invokevirtual get : (I)Ljava/lang/Object;
    //   518: checkcast androidx/fragment/app/BackStackRecord
    //   521: astore_2
    //   522: aload_3
    //   523: aload_1
    //   524: invokevirtual print : (Ljava/lang/String;)V
    //   527: aload_3
    //   528: ldc_w '  #'
    //   531: invokevirtual print : (Ljava/lang/String;)V
    //   534: aload_3
    //   535: iload #5
    //   537: invokevirtual print : (I)V
    //   540: aload_3
    //   541: ldc_w ': '
    //   544: invokevirtual print : (Ljava/lang/String;)V
    //   547: aload_3
    //   548: aload_2
    //   549: invokevirtual println : (Ljava/lang/Object;)V
    //   552: iinc #5, 1
    //   555: goto -> 502
    //   558: aload_0
    //   559: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   562: ifnull -> 601
    //   565: aload_0
    //   566: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   569: invokevirtual size : ()I
    //   572: ifle -> 601
    //   575: aload_3
    //   576: aload_1
    //   577: invokevirtual print : (Ljava/lang/String;)V
    //   580: aload_3
    //   581: ldc_w 'mAvailBackStackIndices: '
    //   584: invokevirtual print : (Ljava/lang/String;)V
    //   587: aload_3
    //   588: aload_0
    //   589: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   592: invokevirtual toArray : ()[Ljava/lang/Object;
    //   595: invokestatic toString : ([Ljava/lang/Object;)Ljava/lang/String;
    //   598: invokevirtual println : (Ljava/lang/String;)V
    //   601: aload_0
    //   602: monitorexit
    //   603: aload_0
    //   604: getfield mPendingActions : Ljava/util/ArrayList;
    //   607: astore_2
    //   608: aload_2
    //   609: ifnull -> 695
    //   612: aload_2
    //   613: invokevirtual size : ()I
    //   616: istore #7
    //   618: iload #7
    //   620: ifle -> 695
    //   623: aload_3
    //   624: aload_1
    //   625: invokevirtual print : (Ljava/lang/String;)V
    //   628: aload_3
    //   629: ldc_w 'Pending Actions:'
    //   632: invokevirtual println : (Ljava/lang/String;)V
    //   635: iload #6
    //   637: istore #5
    //   639: iload #5
    //   641: iload #7
    //   643: if_icmpge -> 695
    //   646: aload_0
    //   647: getfield mPendingActions : Ljava/util/ArrayList;
    //   650: iload #5
    //   652: invokevirtual get : (I)Ljava/lang/Object;
    //   655: checkcast androidx/fragment/app/FragmentManagerImpl$OpGenerator
    //   658: astore_2
    //   659: aload_3
    //   660: aload_1
    //   661: invokevirtual print : (Ljava/lang/String;)V
    //   664: aload_3
    //   665: ldc_w '  #'
    //   668: invokevirtual print : (Ljava/lang/String;)V
    //   671: aload_3
    //   672: iload #5
    //   674: invokevirtual print : (I)V
    //   677: aload_3
    //   678: ldc_w ': '
    //   681: invokevirtual print : (Ljava/lang/String;)V
    //   684: aload_3
    //   685: aload_2
    //   686: invokevirtual println : (Ljava/lang/Object;)V
    //   689: iinc #5, 1
    //   692: goto -> 639
    //   695: aload_3
    //   696: aload_1
    //   697: invokevirtual print : (Ljava/lang/String;)V
    //   700: aload_3
    //   701: ldc_w 'FragmentManager misc state:'
    //   704: invokevirtual println : (Ljava/lang/String;)V
    //   707: aload_3
    //   708: aload_1
    //   709: invokevirtual print : (Ljava/lang/String;)V
    //   712: aload_3
    //   713: ldc_w '  mHost='
    //   716: invokevirtual print : (Ljava/lang/String;)V
    //   719: aload_3
    //   720: aload_0
    //   721: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   724: invokevirtual println : (Ljava/lang/Object;)V
    //   727: aload_3
    //   728: aload_1
    //   729: invokevirtual print : (Ljava/lang/String;)V
    //   732: aload_3
    //   733: ldc_w '  mContainer='
    //   736: invokevirtual print : (Ljava/lang/String;)V
    //   739: aload_3
    //   740: aload_0
    //   741: getfield mContainer : Landroidx/fragment/app/FragmentContainer;
    //   744: invokevirtual println : (Ljava/lang/Object;)V
    //   747: aload_0
    //   748: getfield mParent : Landroidx/fragment/app/Fragment;
    //   751: ifnull -> 774
    //   754: aload_3
    //   755: aload_1
    //   756: invokevirtual print : (Ljava/lang/String;)V
    //   759: aload_3
    //   760: ldc_w '  mParent='
    //   763: invokevirtual print : (Ljava/lang/String;)V
    //   766: aload_3
    //   767: aload_0
    //   768: getfield mParent : Landroidx/fragment/app/Fragment;
    //   771: invokevirtual println : (Ljava/lang/Object;)V
    //   774: aload_3
    //   775: aload_1
    //   776: invokevirtual print : (Ljava/lang/String;)V
    //   779: aload_3
    //   780: ldc_w '  mCurState='
    //   783: invokevirtual print : (Ljava/lang/String;)V
    //   786: aload_3
    //   787: aload_0
    //   788: getfield mCurState : I
    //   791: invokevirtual print : (I)V
    //   794: aload_3
    //   795: ldc_w ' mStateSaved='
    //   798: invokevirtual print : (Ljava/lang/String;)V
    //   801: aload_3
    //   802: aload_0
    //   803: getfield mStateSaved : Z
    //   806: invokevirtual print : (Z)V
    //   809: aload_3
    //   810: ldc_w ' mStopped='
    //   813: invokevirtual print : (Ljava/lang/String;)V
    //   816: aload_3
    //   817: aload_0
    //   818: getfield mStopped : Z
    //   821: invokevirtual print : (Z)V
    //   824: aload_3
    //   825: ldc_w ' mDestroyed='
    //   828: invokevirtual print : (Ljava/lang/String;)V
    //   831: aload_3
    //   832: aload_0
    //   833: getfield mDestroyed : Z
    //   836: invokevirtual println : (Z)V
    //   839: aload_0
    //   840: getfield mNeedMenuInvalidate : Z
    //   843: ifeq -> 866
    //   846: aload_3
    //   847: aload_1
    //   848: invokevirtual print : (Ljava/lang/String;)V
    //   851: aload_3
    //   852: ldc_w '  mNeedMenuInvalidate='
    //   855: invokevirtual print : (Ljava/lang/String;)V
    //   858: aload_3
    //   859: aload_0
    //   860: getfield mNeedMenuInvalidate : Z
    //   863: invokevirtual println : (Z)V
    //   866: aload_0
    //   867: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   870: ifnull -> 893
    //   873: aload_3
    //   874: aload_1
    //   875: invokevirtual print : (Ljava/lang/String;)V
    //   878: aload_3
    //   879: ldc_w '  mNoTransactionsBecause='
    //   882: invokevirtual print : (Ljava/lang/String;)V
    //   885: aload_3
    //   886: aload_0
    //   887: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   890: invokevirtual println : (Ljava/lang/String;)V
    //   893: return
    //   894: astore_1
    //   895: aload_0
    //   896: monitorexit
    //   897: aload_1
    //   898: athrow
    // Exception table:
    //   from	to	target	type
    //   466	482	894	finally
    //   487	499	894	finally
    //   509	552	894	finally
    //   558	601	894	finally
    //   601	603	894	finally
    //   895	897	894	finally
  }
  
  public void enqueueAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    // Byte code:
    //   0: iload_2
    //   1: ifne -> 8
    //   4: aload_0
    //   5: invokespecial checkStateLoss : ()V
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield mDestroyed : Z
    //   14: ifne -> 63
    //   17: aload_0
    //   18: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   21: ifnonnull -> 27
    //   24: goto -> 63
    //   27: aload_0
    //   28: getfield mPendingActions : Ljava/util/ArrayList;
    //   31: ifnonnull -> 47
    //   34: new java/util/ArrayList
    //   37: astore_3
    //   38: aload_3
    //   39: invokespecial <init> : ()V
    //   42: aload_0
    //   43: aload_3
    //   44: putfield mPendingActions : Ljava/util/ArrayList;
    //   47: aload_0
    //   48: getfield mPendingActions : Ljava/util/ArrayList;
    //   51: aload_1
    //   52: invokevirtual add : (Ljava/lang/Object;)Z
    //   55: pop
    //   56: aload_0
    //   57: invokevirtual scheduleCommit : ()V
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: iload_2
    //   64: ifeq -> 70
    //   67: aload_0
    //   68: monitorexit
    //   69: return
    //   70: new java/lang/IllegalStateException
    //   73: astore_1
    //   74: aload_1
    //   75: ldc_w 'Activity has been destroyed'
    //   78: invokespecial <init> : (Ljava/lang/String;)V
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   10	24	83	finally
    //   27	47	83	finally
    //   47	62	83	finally
    //   67	69	83	finally
    //   70	83	83	finally
    //   84	86	83	finally
  }
  
  void ensureInflatedFragmentView(Fragment paramFragment) {
    if (paramFragment.mFromLayout && !paramFragment.mPerformedCreateView) {
      paramFragment.performCreateView(paramFragment.performGetLayoutInflater(paramFragment.mSavedFragmentState), null, paramFragment.mSavedFragmentState);
      View view = paramFragment.mView;
      if (view != null) {
        paramFragment.mInnerView = view;
        view.setSaveFromParentEnabled(false);
        if (paramFragment.mHidden)
          paramFragment.mView.setVisibility(8); 
        paramFragment.onViewCreated(paramFragment.mView, paramFragment.mSavedFragmentState);
        dispatchOnFragmentViewCreated(paramFragment, paramFragment.mView, paramFragment.mSavedFragmentState, false);
      } else {
        paramFragment.mInnerView = null;
      } 
    } 
  }
  
  public boolean execPendingActions() {
    ensureExecReady(true);
    boolean bool = false;
    while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
        cleanupExec();
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  public void execSingleAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    if (paramBoolean && (this.mHost == null || this.mDestroyed))
      return; 
    ensureExecReady(paramBoolean);
    if (paramOpGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
  }
  
  public Fragment findFragmentById(int paramInt) {
    int i;
    for (i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null && fragment.mFragmentId == paramInt)
        return fragment; 
    } 
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null)
      for (i = sparseArray.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && fragment.mFragmentId == paramInt)
          return fragment; 
      }  
    return null;
  }
  
  public Fragment findFragmentByTag(String paramString) {
    if (paramString != null)
      for (int i = this.mAdded.size() - 1; i >= 0; i--) {
        Fragment fragment = this.mAdded.get(i);
        if (fragment != null && paramString.equals(fragment.mTag))
          return fragment; 
      }  
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null && paramString != null)
      for (int i = sparseArray.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && paramString.equals(fragment.mTag))
          return fragment; 
      }  
    return null;
  }
  
  public Fragment findFragmentByWho(String paramString) {
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null && paramString != null)
      for (int i = sparseArray.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null) {
          fragment = fragment.findFragmentByWho(paramString);
          if (fragment != null)
            return fragment; 
        } 
      }  
    return null;
  }
  
  public void freeBackStackIndex(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: iload_1
    //   7: aconst_null
    //   8: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   11: pop
    //   12: aload_0
    //   13: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   16: ifnonnull -> 32
    //   19: new java/util/ArrayList
    //   22: astore_2
    //   23: aload_2
    //   24: invokespecial <init> : ()V
    //   27: aload_0
    //   28: aload_2
    //   29: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   32: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   35: ifeq -> 65
    //   38: new java/lang/StringBuilder
    //   41: astore_2
    //   42: aload_2
    //   43: invokespecial <init> : ()V
    //   46: aload_2
    //   47: ldc_w 'Freeing back stack index '
    //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload_2
    //   55: iload_1
    //   56: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload_2
    //   61: invokevirtual toString : ()Ljava/lang/String;
    //   64: pop
    //   65: aload_0
    //   66: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   69: iload_1
    //   70: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   73: invokevirtual add : (Ljava/lang/Object;)Z
    //   76: pop
    //   77: aload_0
    //   78: monitorexit
    //   79: return
    //   80: astore_2
    //   81: aload_0
    //   82: monitorexit
    //   83: aload_2
    //   84: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	80	finally
    //   32	65	80	finally
    //   65	79	80	finally
    //   81	83	80	finally
  }
  
  public Fragment getFragment(Bundle paramBundle, String paramString) {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1)
      return null; 
    Fragment fragment = (Fragment)this.mActive.get(i);
    if (fragment != null)
      return fragment; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment no longer exists for key ");
    stringBuilder.append(paramString);
    stringBuilder.append(": index ");
    stringBuilder.append(i);
    throwException(new IllegalStateException(stringBuilder.toString()));
    throw null;
  }
  
  public List<Fragment> getFragments() {
    if (this.mAdded.isEmpty())
      return Collections.emptyList(); 
    synchronized (this.mAdded) {
      return (List)this.mAdded.clone();
    } 
  }
  
  LayoutInflater.Factory2 getLayoutInflaterFactory() {
    return this;
  }
  
  public Fragment getPrimaryNavigationFragment() {
    return this.mPrimaryNav;
  }
  
  public void hideFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("hide: ");
      stringBuilder.append(paramFragment);
      stringBuilder.toString();
    } 
    if (!paramFragment.mHidden) {
      paramFragment.mHidden = true;
      paramFragment.mHiddenChanged = true ^ paramFragment.mHiddenChanged;
    } 
  }
  
  boolean isStateAtLeast(int paramInt) {
    boolean bool;
    if (this.mCurState >= paramInt) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStateSaved() {
    return (this.mStateSaved || this.mStopped);
  }
  
  AnimationOrAnimator loadAnimation(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2) {
    int i = paramFragment.getNextAnim();
    Animation animation = paramFragment.onCreateAnimation(paramInt1, paramBoolean, i);
    if (animation != null)
      return new AnimationOrAnimator(animation); 
    Animator animator = paramFragment.onCreateAnimator(paramInt1, paramBoolean, i);
    if (animator != null)
      return new AnimationOrAnimator(animator); 
    if (i != 0) {
      boolean bool = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(i));
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (bool)
        try {
          Animation animation1 = AnimationUtils.loadAnimation(this.mHost.getContext(), i);
          if (animation1 != null)
            return new AnimationOrAnimator(animation1); 
          bool1 = true;
        } catch (android.content.res.Resources.NotFoundException notFoundException) {
          throw notFoundException;
        } catch (RuntimeException runtimeException) {
          bool1 = bool2;
        }  
      if (!bool1)
        try {
          animator = AnimatorInflater.loadAnimator(this.mHost.getContext(), i);
          if (animator != null)
            return new AnimationOrAnimator(animator); 
        } catch (RuntimeException runtimeException) {
          Animation animation1;
          if (!bool) {
            animation1 = AnimationUtils.loadAnimation(this.mHost.getContext(), i);
            if (animation1 != null)
              return new AnimationOrAnimator(animation1); 
          } else {
            throw animation1;
          } 
        }  
    } 
    if (paramInt1 == 0)
      return null; 
    paramInt1 = transitToStyleIndex(paramInt1, paramBoolean);
    if (paramInt1 < 0)
      return null; 
    switch (paramInt1) {
      default:
        paramInt1 = paramInt2;
        if (paramInt2 == 0) {
          paramInt1 = paramInt2;
          if (this.mHost.onHasWindowAnimations())
            paramInt1 = this.mHost.onGetWindowAnimations(); 
        } 
        break;
      case 6:
        return makeFadeAnimation(this.mHost.getContext(), 1.0F, 0.0F);
      case 5:
        return makeFadeAnimation(this.mHost.getContext(), 0.0F, 1.0F);
      case 4:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);
      case 3:
        return makeOpenCloseAnimation(this.mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);
      case 2:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);
      case 1:
        return makeOpenCloseAnimation(this.mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);
    } 
    if (paramInt1 == 0);
    return null;
  }
  
  void makeActive(Fragment paramFragment) {
    if (paramFragment.mIndex >= 0)
      return; 
    int i = this.mNextFragmentIndex;
    this.mNextFragmentIndex = i + 1;
    paramFragment.setIndex(i, this.mParent);
    if (this.mActive == null)
      this.mActive = new SparseArray(); 
    this.mActive.put(paramFragment.mIndex, paramFragment);
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Allocated fragment index ");
      stringBuilder.append(paramFragment);
      stringBuilder.toString();
    } 
  }
  
  void makeInactive(Fragment paramFragment) {
    if (paramFragment.mIndex < 0)
      return; 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Freeing fragment index ");
      stringBuilder.append(paramFragment);
      stringBuilder.toString();
    } 
    this.mActive.put(paramFragment.mIndex, null);
    paramFragment.initState();
  }
  
  void moveFragmentToExpectedState(Fragment paramFragment) {
    if (paramFragment == null)
      return; 
    int j = this.mCurState;
    int i = j;
    if (paramFragment.mRemoving)
      if (paramFragment.isInBackStack()) {
        i = Math.min(j, 1);
      } else {
        i = Math.min(j, 0);
      }  
    moveToState(paramFragment, i, paramFragment.getNextTransition(), paramFragment.getNextTransitionStyle(), false);
    if (paramFragment.mView != null) {
      Fragment fragment = findFragmentUnder(paramFragment);
      if (fragment != null) {
        View view = fragment.mView;
        ViewGroup viewGroup = paramFragment.mContainer;
        i = viewGroup.indexOfChild(view);
        j = viewGroup.indexOfChild(paramFragment.mView);
        if (j < i) {
          viewGroup.removeViewAt(j);
          viewGroup.addView(paramFragment.mView, i);
        } 
      } 
      if (paramFragment.mIsNewlyAdded && paramFragment.mContainer != null) {
        float f = paramFragment.mPostponedAlpha;
        if (f > 0.0F)
          paramFragment.mView.setAlpha(f); 
        paramFragment.mPostponedAlpha = 0.0F;
        paramFragment.mIsNewlyAdded = false;
        AnimationOrAnimator animationOrAnimator = loadAnimation(paramFragment, paramFragment.getNextTransition(), true, paramFragment.getNextTransitionStyle());
        if (animationOrAnimator != null) {
          setHWLayerAnimListenerIfAlpha(paramFragment.mView, animationOrAnimator);
          Animation animation = animationOrAnimator.animation;
          if (animation != null) {
            paramFragment.mView.startAnimation(animation);
          } else {
            animationOrAnimator.animator.setTarget(paramFragment.mView);
            animationOrAnimator.animator.start();
          } 
        } 
      } 
    } 
    if (paramFragment.mHiddenChanged)
      completeShowHideFragment(paramFragment); 
  }
  
  void moveToState(int paramInt, boolean paramBoolean) {
    if (this.mHost != null || paramInt == 0) {
      if (!paramBoolean && paramInt == this.mCurState)
        return; 
      this.mCurState = paramInt;
      if (this.mActive != null) {
        int i = this.mAdded.size();
        for (paramInt = 0; paramInt < i; paramInt++)
          moveFragmentToExpectedState(this.mAdded.get(paramInt)); 
        i = this.mActive.size();
        for (paramInt = 0; paramInt < i; paramInt++) {
          Fragment fragment = (Fragment)this.mActive.valueAt(paramInt);
          if (fragment != null && (fragment.mRemoving || fragment.mDetached) && !fragment.mIsNewlyAdded)
            moveFragmentToExpectedState(fragment); 
        } 
        startPendingDeferredFragments();
        if (this.mNeedMenuInvalidate) {
          FragmentHostCallback fragmentHostCallback = this.mHost;
          if (fragmentHostCallback != null && this.mCurState == 4) {
            fragmentHostCallback.onSupportInvalidateOptionsMenu();
            this.mNeedMenuInvalidate = false;
          } 
        } 
      } 
      return;
    } 
    throw new IllegalStateException("No activity");
  }
  
  void moveToState(Fragment paramFragment) {
    moveToState(paramFragment, this.mCurState, 0, 0, false);
  }
  
  void moveToState(Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mAdded : Z
    //   4: istore #10
    //   6: iconst_1
    //   7: istore #7
    //   9: iconst_1
    //   10: istore #9
    //   12: iload #10
    //   14: ifeq -> 30
    //   17: aload_1
    //   18: getfield mDetached : Z
    //   21: ifeq -> 27
    //   24: goto -> 30
    //   27: goto -> 44
    //   30: iload_2
    //   31: istore #6
    //   33: iload #6
    //   35: istore_2
    //   36: iload #6
    //   38: iconst_1
    //   39: if_icmple -> 44
    //   42: iconst_1
    //   43: istore_2
    //   44: iload_2
    //   45: istore #6
    //   47: aload_1
    //   48: getfield mRemoving : Z
    //   51: ifeq -> 93
    //   54: aload_1
    //   55: getfield mState : I
    //   58: istore #8
    //   60: iload_2
    //   61: istore #6
    //   63: iload_2
    //   64: iload #8
    //   66: if_icmple -> 93
    //   69: iload #8
    //   71: ifne -> 87
    //   74: aload_1
    //   75: invokevirtual isInBackStack : ()Z
    //   78: ifeq -> 87
    //   81: iconst_1
    //   82: istore #6
    //   84: goto -> 93
    //   87: aload_1
    //   88: getfield mState : I
    //   91: istore #6
    //   93: aload_1
    //   94: getfield mDeferStart : Z
    //   97: ifeq -> 119
    //   100: aload_1
    //   101: getfield mState : I
    //   104: iconst_3
    //   105: if_icmpge -> 119
    //   108: iload #6
    //   110: iconst_2
    //   111: if_icmple -> 119
    //   114: iconst_2
    //   115: istore_2
    //   116: goto -> 122
    //   119: iload #6
    //   121: istore_2
    //   122: aload_1
    //   123: getfield mState : I
    //   126: istore #8
    //   128: iload #8
    //   130: iload_2
    //   131: if_icmpgt -> 1358
    //   134: aload_1
    //   135: getfield mFromLayout : Z
    //   138: ifeq -> 149
    //   141: aload_1
    //   142: getfield mInLayout : Z
    //   145: ifne -> 149
    //   148: return
    //   149: aload_1
    //   150: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   153: ifnonnull -> 163
    //   156: aload_1
    //   157: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   160: ifnull -> 185
    //   163: aload_1
    //   164: aconst_null
    //   165: invokevirtual setAnimatingAway : (Landroid/view/View;)V
    //   168: aload_1
    //   169: aconst_null
    //   170: invokevirtual setAnimator : (Landroid/animation/Animator;)V
    //   173: aload_0
    //   174: aload_1
    //   175: aload_1
    //   176: invokevirtual getStateAfterAnimating : ()I
    //   179: iconst_0
    //   180: iconst_0
    //   181: iconst_1
    //   182: invokevirtual moveToState : (Landroidx/fragment/app/Fragment;IIIZ)V
    //   185: aload_1
    //   186: getfield mState : I
    //   189: istore #6
    //   191: iload #6
    //   193: ifeq -> 227
    //   196: iload_2
    //   197: istore_3
    //   198: iload #6
    //   200: iconst_1
    //   201: if_icmpeq -> 776
    //   204: iload_2
    //   205: istore #4
    //   207: iload #6
    //   209: iconst_2
    //   210: if_icmpeq -> 1228
    //   213: iload_2
    //   214: istore_3
    //   215: iload #6
    //   217: iconst_3
    //   218: if_icmpeq -> 1287
    //   221: iload_2
    //   222: istore #6
    //   224: goto -> 1942
    //   227: iload_2
    //   228: istore_3
    //   229: iload_2
    //   230: ifle -> 776
    //   233: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   236: ifeq -> 270
    //   239: new java/lang/StringBuilder
    //   242: dup
    //   243: invokespecial <init> : ()V
    //   246: astore #11
    //   248: aload #11
    //   250: ldc_w 'moveto CREATED: '
    //   253: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: pop
    //   257: aload #11
    //   259: aload_1
    //   260: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   263: pop
    //   264: aload #11
    //   266: invokevirtual toString : ()Ljava/lang/String;
    //   269: pop
    //   270: aload_1
    //   271: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   274: astore #11
    //   276: iload_2
    //   277: istore_3
    //   278: aload #11
    //   280: ifnull -> 415
    //   283: aload #11
    //   285: aload_0
    //   286: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   289: invokevirtual getContext : ()Landroid/content/Context;
    //   292: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   295: invokevirtual setClassLoader : (Ljava/lang/ClassLoader;)V
    //   298: aload_1
    //   299: aload_1
    //   300: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   303: ldc_w 'android:view_state'
    //   306: invokevirtual getSparseParcelableArray : (Ljava/lang/String;)Landroid/util/SparseArray;
    //   309: putfield mSavedViewState : Landroid/util/SparseArray;
    //   312: aload_1
    //   313: aload_0
    //   314: aload_1
    //   315: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   318: ldc_w 'android:target_state'
    //   321: invokevirtual getFragment : (Landroid/os/Bundle;Ljava/lang/String;)Landroidx/fragment/app/Fragment;
    //   324: putfield mTarget : Landroidx/fragment/app/Fragment;
    //   327: aload_1
    //   328: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   331: ifnull -> 349
    //   334: aload_1
    //   335: aload_1
    //   336: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   339: ldc_w 'android:target_req_state'
    //   342: iconst_0
    //   343: invokevirtual getInt : (Ljava/lang/String;I)I
    //   346: putfield mTargetRequestCode : I
    //   349: aload_1
    //   350: getfield mSavedUserVisibleHint : Ljava/lang/Boolean;
    //   353: astore #11
    //   355: aload #11
    //   357: ifnull -> 377
    //   360: aload_1
    //   361: aload #11
    //   363: invokevirtual booleanValue : ()Z
    //   366: putfield mUserVisibleHint : Z
    //   369: aload_1
    //   370: aconst_null
    //   371: putfield mSavedUserVisibleHint : Ljava/lang/Boolean;
    //   374: goto -> 392
    //   377: aload_1
    //   378: aload_1
    //   379: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   382: ldc_w 'android:user_visible_hint'
    //   385: iconst_1
    //   386: invokevirtual getBoolean : (Ljava/lang/String;Z)Z
    //   389: putfield mUserVisibleHint : Z
    //   392: iload_2
    //   393: istore_3
    //   394: aload_1
    //   395: getfield mUserVisibleHint : Z
    //   398: ifne -> 415
    //   401: aload_1
    //   402: iconst_1
    //   403: putfield mDeferStart : Z
    //   406: iload_2
    //   407: istore_3
    //   408: iload_2
    //   409: iconst_2
    //   410: if_icmple -> 415
    //   413: iconst_2
    //   414: istore_3
    //   415: aload_0
    //   416: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   419: astore #12
    //   421: aload_1
    //   422: aload #12
    //   424: putfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   427: aload_0
    //   428: getfield mParent : Landroidx/fragment/app/Fragment;
    //   431: astore #11
    //   433: aload_1
    //   434: aload #11
    //   436: putfield mParentFragment : Landroidx/fragment/app/Fragment;
    //   439: aload #11
    //   441: ifnull -> 454
    //   444: aload #11
    //   446: getfield mChildFragmentManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   449: astore #11
    //   451: goto -> 461
    //   454: aload #12
    //   456: invokevirtual getFragmentManagerImpl : ()Landroidx/fragment/app/FragmentManagerImpl;
    //   459: astore #11
    //   461: aload_1
    //   462: aload #11
    //   464: putfield mFragmentManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   467: aload_1
    //   468: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   471: astore #11
    //   473: aload #11
    //   475: ifnull -> 593
    //   478: aload_0
    //   479: getfield mActive : Landroid/util/SparseArray;
    //   482: aload #11
    //   484: getfield mIndex : I
    //   487: invokevirtual get : (I)Ljava/lang/Object;
    //   490: astore #11
    //   492: aload_1
    //   493: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   496: astore #12
    //   498: aload #11
    //   500: aload #12
    //   502: if_acmpne -> 527
    //   505: aload #12
    //   507: getfield mState : I
    //   510: iconst_1
    //   511: if_icmpge -> 593
    //   514: aload_0
    //   515: aload #12
    //   517: iconst_1
    //   518: iconst_0
    //   519: iconst_0
    //   520: iconst_1
    //   521: invokevirtual moveToState : (Landroidx/fragment/app/Fragment;IIIZ)V
    //   524: goto -> 593
    //   527: new java/lang/StringBuilder
    //   530: dup
    //   531: invokespecial <init> : ()V
    //   534: astore #11
    //   536: aload #11
    //   538: ldc_w 'Fragment '
    //   541: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   544: pop
    //   545: aload #11
    //   547: aload_1
    //   548: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   551: pop
    //   552: aload #11
    //   554: ldc_w ' declared target fragment '
    //   557: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: pop
    //   561: aload #11
    //   563: aload_1
    //   564: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   567: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   570: pop
    //   571: aload #11
    //   573: ldc_w ' that does not belong to this FragmentManager!'
    //   576: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   579: pop
    //   580: new java/lang/IllegalStateException
    //   583: dup
    //   584: aload #11
    //   586: invokevirtual toString : ()Ljava/lang/String;
    //   589: invokespecial <init> : (Ljava/lang/String;)V
    //   592: athrow
    //   593: aload_0
    //   594: aload_1
    //   595: aload_0
    //   596: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   599: invokevirtual getContext : ()Landroid/content/Context;
    //   602: iconst_0
    //   603: invokevirtual dispatchOnFragmentPreAttached : (Landroidx/fragment/app/Fragment;Landroid/content/Context;Z)V
    //   606: aload_1
    //   607: iconst_0
    //   608: putfield mCalled : Z
    //   611: aload_1
    //   612: aload_0
    //   613: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   616: invokevirtual getContext : ()Landroid/content/Context;
    //   619: invokevirtual onAttach : (Landroid/content/Context;)V
    //   622: aload_1
    //   623: getfield mCalled : Z
    //   626: ifeq -> 729
    //   629: aload_1
    //   630: getfield mParentFragment : Landroidx/fragment/app/Fragment;
    //   633: astore #11
    //   635: aload #11
    //   637: ifnonnull -> 651
    //   640: aload_0
    //   641: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   644: aload_1
    //   645: invokevirtual onAttachFragment : (Landroidx/fragment/app/Fragment;)V
    //   648: goto -> 657
    //   651: aload #11
    //   653: aload_1
    //   654: invokevirtual onAttachFragment : (Landroidx/fragment/app/Fragment;)V
    //   657: aload_0
    //   658: aload_1
    //   659: aload_0
    //   660: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   663: invokevirtual getContext : ()Landroid/content/Context;
    //   666: iconst_0
    //   667: invokevirtual dispatchOnFragmentAttached : (Landroidx/fragment/app/Fragment;Landroid/content/Context;Z)V
    //   670: aload_1
    //   671: getfield mIsCreated : Z
    //   674: ifne -> 708
    //   677: aload_0
    //   678: aload_1
    //   679: aload_1
    //   680: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   683: iconst_0
    //   684: invokevirtual dispatchOnFragmentPreCreated : (Landroidx/fragment/app/Fragment;Landroid/os/Bundle;Z)V
    //   687: aload_1
    //   688: aload_1
    //   689: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   692: invokevirtual performCreate : (Landroid/os/Bundle;)V
    //   695: aload_0
    //   696: aload_1
    //   697: aload_1
    //   698: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   701: iconst_0
    //   702: invokevirtual dispatchOnFragmentCreated : (Landroidx/fragment/app/Fragment;Landroid/os/Bundle;Z)V
    //   705: goto -> 721
    //   708: aload_1
    //   709: aload_1
    //   710: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   713: invokevirtual restoreChildFragmentState : (Landroid/os/Bundle;)V
    //   716: aload_1
    //   717: iconst_1
    //   718: putfield mState : I
    //   721: aload_1
    //   722: iconst_0
    //   723: putfield mRetaining : Z
    //   726: goto -> 776
    //   729: new java/lang/StringBuilder
    //   732: dup
    //   733: invokespecial <init> : ()V
    //   736: astore #11
    //   738: aload #11
    //   740: ldc_w 'Fragment '
    //   743: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   746: pop
    //   747: aload #11
    //   749: aload_1
    //   750: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   753: pop
    //   754: aload #11
    //   756: ldc_w ' did not call through to super.onAttach()'
    //   759: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: pop
    //   763: new androidx/fragment/app/SuperNotCalledException
    //   766: dup
    //   767: aload #11
    //   769: invokevirtual toString : ()Ljava/lang/String;
    //   772: invokespecial <init> : (Ljava/lang/String;)V
    //   775: athrow
    //   776: aload_0
    //   777: aload_1
    //   778: invokevirtual ensureInflatedFragmentView : (Landroidx/fragment/app/Fragment;)V
    //   781: iload_3
    //   782: istore #4
    //   784: iload_3
    //   785: iconst_1
    //   786: if_icmple -> 1228
    //   789: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   792: ifeq -> 826
    //   795: new java/lang/StringBuilder
    //   798: dup
    //   799: invokespecial <init> : ()V
    //   802: astore #11
    //   804: aload #11
    //   806: ldc_w 'moveto ACTIVITY_CREATED: '
    //   809: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   812: pop
    //   813: aload #11
    //   815: aload_1
    //   816: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   819: pop
    //   820: aload #11
    //   822: invokevirtual toString : ()Ljava/lang/String;
    //   825: pop
    //   826: aload_1
    //   827: getfield mFromLayout : Z
    //   830: ifne -> 1187
    //   833: aload_1
    //   834: getfield mContainerId : I
    //   837: istore_2
    //   838: iload_2
    //   839: ifeq -> 1040
    //   842: iload_2
    //   843: iconst_m1
    //   844: if_icmpeq -> 988
    //   847: aload_0
    //   848: getfield mContainer : Landroidx/fragment/app/FragmentContainer;
    //   851: iload_2
    //   852: invokevirtual onFindViewById : (I)Landroid/view/View;
    //   855: checkcast android/view/ViewGroup
    //   858: astore #12
    //   860: aload #12
    //   862: astore #11
    //   864: aload #12
    //   866: ifnonnull -> 1043
    //   869: aload_1
    //   870: getfield mRestored : Z
    //   873: ifeq -> 883
    //   876: aload #12
    //   878: astore #11
    //   880: goto -> 1043
    //   883: aload_1
    //   884: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   887: aload_1
    //   888: getfield mContainerId : I
    //   891: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   894: astore #11
    //   896: goto -> 906
    //   899: astore #11
    //   901: ldc_w 'unknown'
    //   904: astore #11
    //   906: new java/lang/StringBuilder
    //   909: dup
    //   910: invokespecial <init> : ()V
    //   913: astore #12
    //   915: aload #12
    //   917: ldc_w 'No view found for id 0x'
    //   920: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   923: pop
    //   924: aload #12
    //   926: aload_1
    //   927: getfield mContainerId : I
    //   930: invokestatic toHexString : (I)Ljava/lang/String;
    //   933: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   936: pop
    //   937: aload #12
    //   939: ldc_w ' ('
    //   942: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   945: pop
    //   946: aload #12
    //   948: aload #11
    //   950: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   953: pop
    //   954: aload #12
    //   956: ldc_w ') for fragment '
    //   959: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   962: pop
    //   963: aload #12
    //   965: aload_1
    //   966: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   969: pop
    //   970: aload_0
    //   971: new java/lang/IllegalArgumentException
    //   974: dup
    //   975: aload #12
    //   977: invokevirtual toString : ()Ljava/lang/String;
    //   980: invokespecial <init> : (Ljava/lang/String;)V
    //   983: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   986: aconst_null
    //   987: athrow
    //   988: new java/lang/StringBuilder
    //   991: dup
    //   992: invokespecial <init> : ()V
    //   995: astore #11
    //   997: aload #11
    //   999: ldc_w 'Cannot create fragment '
    //   1002: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1005: pop
    //   1006: aload #11
    //   1008: aload_1
    //   1009: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1012: pop
    //   1013: aload #11
    //   1015: ldc_w ' for a container view with no id'
    //   1018: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1021: pop
    //   1022: aload_0
    //   1023: new java/lang/IllegalArgumentException
    //   1026: dup
    //   1027: aload #11
    //   1029: invokevirtual toString : ()Ljava/lang/String;
    //   1032: invokespecial <init> : (Ljava/lang/String;)V
    //   1035: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   1038: aconst_null
    //   1039: athrow
    //   1040: aconst_null
    //   1041: astore #11
    //   1043: aload_1
    //   1044: aload #11
    //   1046: putfield mContainer : Landroid/view/ViewGroup;
    //   1049: aload_1
    //   1050: aload_1
    //   1051: aload_1
    //   1052: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1055: invokevirtual performGetLayoutInflater : (Landroid/os/Bundle;)Landroid/view/LayoutInflater;
    //   1058: aload #11
    //   1060: aload_1
    //   1061: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1064: invokevirtual performCreateView : (Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)V
    //   1067: aload_1
    //   1068: getfield mView : Landroid/view/View;
    //   1071: astore #12
    //   1073: aload #12
    //   1075: ifnull -> 1182
    //   1078: aload_1
    //   1079: aload #12
    //   1081: putfield mInnerView : Landroid/view/View;
    //   1084: aload #12
    //   1086: iconst_0
    //   1087: invokevirtual setSaveFromParentEnabled : (Z)V
    //   1090: aload #11
    //   1092: ifnull -> 1104
    //   1095: aload #11
    //   1097: aload_1
    //   1098: getfield mView : Landroid/view/View;
    //   1101: invokevirtual addView : (Landroid/view/View;)V
    //   1104: aload_1
    //   1105: getfield mHidden : Z
    //   1108: ifeq -> 1120
    //   1111: aload_1
    //   1112: getfield mView : Landroid/view/View;
    //   1115: bipush #8
    //   1117: invokevirtual setVisibility : (I)V
    //   1120: aload_1
    //   1121: aload_1
    //   1122: getfield mView : Landroid/view/View;
    //   1125: aload_1
    //   1126: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1129: invokevirtual onViewCreated : (Landroid/view/View;Landroid/os/Bundle;)V
    //   1132: aload_0
    //   1133: aload_1
    //   1134: aload_1
    //   1135: getfield mView : Landroid/view/View;
    //   1138: aload_1
    //   1139: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1142: iconst_0
    //   1143: invokevirtual dispatchOnFragmentViewCreated : (Landroidx/fragment/app/Fragment;Landroid/view/View;Landroid/os/Bundle;Z)V
    //   1146: aload_1
    //   1147: getfield mView : Landroid/view/View;
    //   1150: invokevirtual getVisibility : ()I
    //   1153: ifne -> 1170
    //   1156: aload_1
    //   1157: getfield mContainer : Landroid/view/ViewGroup;
    //   1160: ifnull -> 1170
    //   1163: iload #9
    //   1165: istore #5
    //   1167: goto -> 1173
    //   1170: iconst_0
    //   1171: istore #5
    //   1173: aload_1
    //   1174: iload #5
    //   1176: putfield mIsNewlyAdded : Z
    //   1179: goto -> 1187
    //   1182: aload_1
    //   1183: aconst_null
    //   1184: putfield mInnerView : Landroid/view/View;
    //   1187: aload_1
    //   1188: aload_1
    //   1189: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1192: invokevirtual performActivityCreated : (Landroid/os/Bundle;)V
    //   1195: aload_0
    //   1196: aload_1
    //   1197: aload_1
    //   1198: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1201: iconst_0
    //   1202: invokevirtual dispatchOnFragmentActivityCreated : (Landroidx/fragment/app/Fragment;Landroid/os/Bundle;Z)V
    //   1205: aload_1
    //   1206: getfield mView : Landroid/view/View;
    //   1209: ifnull -> 1220
    //   1212: aload_1
    //   1213: aload_1
    //   1214: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1217: invokevirtual restoreViewState : (Landroid/os/Bundle;)V
    //   1220: aload_1
    //   1221: aconst_null
    //   1222: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1225: iload_3
    //   1226: istore #4
    //   1228: iload #4
    //   1230: istore_3
    //   1231: iload #4
    //   1233: iconst_2
    //   1234: if_icmple -> 1287
    //   1237: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1240: ifeq -> 1274
    //   1243: new java/lang/StringBuilder
    //   1246: dup
    //   1247: invokespecial <init> : ()V
    //   1250: astore #11
    //   1252: aload #11
    //   1254: ldc_w 'moveto STARTED: '
    //   1257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1260: pop
    //   1261: aload #11
    //   1263: aload_1
    //   1264: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1267: pop
    //   1268: aload #11
    //   1270: invokevirtual toString : ()Ljava/lang/String;
    //   1273: pop
    //   1274: aload_1
    //   1275: invokevirtual performStart : ()V
    //   1278: aload_0
    //   1279: aload_1
    //   1280: iconst_0
    //   1281: invokevirtual dispatchOnFragmentStarted : (Landroidx/fragment/app/Fragment;Z)V
    //   1284: iload #4
    //   1286: istore_3
    //   1287: iload_3
    //   1288: istore #6
    //   1290: iload_3
    //   1291: iconst_3
    //   1292: if_icmple -> 1942
    //   1295: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1298: ifeq -> 1332
    //   1301: new java/lang/StringBuilder
    //   1304: dup
    //   1305: invokespecial <init> : ()V
    //   1308: astore #11
    //   1310: aload #11
    //   1312: ldc_w 'moveto RESUMED: '
    //   1315: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1318: pop
    //   1319: aload #11
    //   1321: aload_1
    //   1322: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1325: pop
    //   1326: aload #11
    //   1328: invokevirtual toString : ()Ljava/lang/String;
    //   1331: pop
    //   1332: aload_1
    //   1333: invokevirtual performResume : ()V
    //   1336: aload_0
    //   1337: aload_1
    //   1338: iconst_0
    //   1339: invokevirtual dispatchOnFragmentResumed : (Landroidx/fragment/app/Fragment;Z)V
    //   1342: aload_1
    //   1343: aconst_null
    //   1344: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1347: aload_1
    //   1348: aconst_null
    //   1349: putfield mSavedViewState : Landroid/util/SparseArray;
    //   1352: iload_3
    //   1353: istore #6
    //   1355: goto -> 1942
    //   1358: iload_2
    //   1359: istore #6
    //   1361: iload #8
    //   1363: iload_2
    //   1364: if_icmple -> 1942
    //   1367: iload #8
    //   1369: iconst_1
    //   1370: if_icmpeq -> 1731
    //   1373: iload #8
    //   1375: iconst_2
    //   1376: if_icmpeq -> 1501
    //   1379: iload #8
    //   1381: iconst_3
    //   1382: if_icmpeq -> 1449
    //   1385: iload #8
    //   1387: iconst_4
    //   1388: if_icmpeq -> 1397
    //   1391: iload_2
    //   1392: istore #6
    //   1394: goto -> 1942
    //   1397: iload_2
    //   1398: iconst_4
    //   1399: if_icmpge -> 1449
    //   1402: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1405: ifeq -> 1439
    //   1408: new java/lang/StringBuilder
    //   1411: dup
    //   1412: invokespecial <init> : ()V
    //   1415: astore #11
    //   1417: aload #11
    //   1419: ldc_w 'movefrom RESUMED: '
    //   1422: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1425: pop
    //   1426: aload #11
    //   1428: aload_1
    //   1429: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1432: pop
    //   1433: aload #11
    //   1435: invokevirtual toString : ()Ljava/lang/String;
    //   1438: pop
    //   1439: aload_1
    //   1440: invokevirtual performPause : ()V
    //   1443: aload_0
    //   1444: aload_1
    //   1445: iconst_0
    //   1446: invokevirtual dispatchOnFragmentPaused : (Landroidx/fragment/app/Fragment;Z)V
    //   1449: iload_2
    //   1450: iconst_3
    //   1451: if_icmpge -> 1501
    //   1454: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1457: ifeq -> 1491
    //   1460: new java/lang/StringBuilder
    //   1463: dup
    //   1464: invokespecial <init> : ()V
    //   1467: astore #11
    //   1469: aload #11
    //   1471: ldc_w 'movefrom STARTED: '
    //   1474: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1477: pop
    //   1478: aload #11
    //   1480: aload_1
    //   1481: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1484: pop
    //   1485: aload #11
    //   1487: invokevirtual toString : ()Ljava/lang/String;
    //   1490: pop
    //   1491: aload_1
    //   1492: invokevirtual performStop : ()V
    //   1495: aload_0
    //   1496: aload_1
    //   1497: iconst_0
    //   1498: invokevirtual dispatchOnFragmentStopped : (Landroidx/fragment/app/Fragment;Z)V
    //   1501: iload_2
    //   1502: iconst_2
    //   1503: if_icmpge -> 1731
    //   1506: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1509: ifeq -> 1543
    //   1512: new java/lang/StringBuilder
    //   1515: dup
    //   1516: invokespecial <init> : ()V
    //   1519: astore #11
    //   1521: aload #11
    //   1523: ldc_w 'movefrom ACTIVITY_CREATED: '
    //   1526: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1529: pop
    //   1530: aload #11
    //   1532: aload_1
    //   1533: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1536: pop
    //   1537: aload #11
    //   1539: invokevirtual toString : ()Ljava/lang/String;
    //   1542: pop
    //   1543: aload_1
    //   1544: getfield mView : Landroid/view/View;
    //   1547: ifnull -> 1573
    //   1550: aload_0
    //   1551: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   1554: aload_1
    //   1555: invokevirtual onShouldSaveFragmentState : (Landroidx/fragment/app/Fragment;)Z
    //   1558: ifeq -> 1573
    //   1561: aload_1
    //   1562: getfield mSavedViewState : Landroid/util/SparseArray;
    //   1565: ifnonnull -> 1573
    //   1568: aload_0
    //   1569: aload_1
    //   1570: invokevirtual saveFragmentViewState : (Landroidx/fragment/app/Fragment;)V
    //   1573: aload_1
    //   1574: invokevirtual performDestroyView : ()V
    //   1577: aload_0
    //   1578: aload_1
    //   1579: iconst_0
    //   1580: invokevirtual dispatchOnFragmentViewDestroyed : (Landroidx/fragment/app/Fragment;Z)V
    //   1583: aload_1
    //   1584: getfield mView : Landroid/view/View;
    //   1587: astore #11
    //   1589: aload #11
    //   1591: ifnull -> 1698
    //   1594: aload_1
    //   1595: getfield mContainer : Landroid/view/ViewGroup;
    //   1598: astore #12
    //   1600: aload #12
    //   1602: ifnull -> 1698
    //   1605: aload #12
    //   1607: aload #11
    //   1609: invokevirtual endViewTransition : (Landroid/view/View;)V
    //   1612: aload_1
    //   1613: getfield mView : Landroid/view/View;
    //   1616: invokevirtual clearAnimation : ()V
    //   1619: aload_0
    //   1620: getfield mCurState : I
    //   1623: ifle -> 1666
    //   1626: aload_0
    //   1627: getfield mDestroyed : Z
    //   1630: ifne -> 1666
    //   1633: aload_1
    //   1634: getfield mView : Landroid/view/View;
    //   1637: invokevirtual getVisibility : ()I
    //   1640: ifne -> 1666
    //   1643: aload_1
    //   1644: getfield mPostponedAlpha : F
    //   1647: fconst_0
    //   1648: fcmpl
    //   1649: iflt -> 1666
    //   1652: aload_0
    //   1653: aload_1
    //   1654: iload_3
    //   1655: iconst_0
    //   1656: iload #4
    //   1658: invokevirtual loadAnimation : (Landroidx/fragment/app/Fragment;IZI)Landroidx/fragment/app/FragmentManagerImpl$AnimationOrAnimator;
    //   1661: astore #11
    //   1663: goto -> 1669
    //   1666: aconst_null
    //   1667: astore #11
    //   1669: aload_1
    //   1670: fconst_0
    //   1671: putfield mPostponedAlpha : F
    //   1674: aload #11
    //   1676: ifnull -> 1687
    //   1679: aload_0
    //   1680: aload_1
    //   1681: aload #11
    //   1683: iload_2
    //   1684: invokespecial animateRemoveFragment : (Landroidx/fragment/app/Fragment;Landroidx/fragment/app/FragmentManagerImpl$AnimationOrAnimator;I)V
    //   1687: aload_1
    //   1688: getfield mContainer : Landroid/view/ViewGroup;
    //   1691: aload_1
    //   1692: getfield mView : Landroid/view/View;
    //   1695: invokevirtual removeView : (Landroid/view/View;)V
    //   1698: aload_1
    //   1699: aconst_null
    //   1700: putfield mContainer : Landroid/view/ViewGroup;
    //   1703: aload_1
    //   1704: aconst_null
    //   1705: putfield mView : Landroid/view/View;
    //   1708: aload_1
    //   1709: aconst_null
    //   1710: putfield mViewLifecycleOwner : Landroidx/lifecycle/LifecycleOwner;
    //   1713: aload_1
    //   1714: getfield mViewLifecycleOwnerLiveData : Landroidx/lifecycle/MutableLiveData;
    //   1717: aconst_null
    //   1718: invokevirtual setValue : (Ljava/lang/Object;)V
    //   1721: aload_1
    //   1722: aconst_null
    //   1723: putfield mInnerView : Landroid/view/View;
    //   1726: aload_1
    //   1727: iconst_0
    //   1728: putfield mInLayout : Z
    //   1731: iload_2
    //   1732: istore #6
    //   1734: iload_2
    //   1735: iconst_1
    //   1736: if_icmpge -> 1942
    //   1739: aload_0
    //   1740: getfield mDestroyed : Z
    //   1743: ifeq -> 1795
    //   1746: aload_1
    //   1747: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1750: ifnull -> 1772
    //   1753: aload_1
    //   1754: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1757: astore #11
    //   1759: aload_1
    //   1760: aconst_null
    //   1761: invokevirtual setAnimatingAway : (Landroid/view/View;)V
    //   1764: aload #11
    //   1766: invokevirtual clearAnimation : ()V
    //   1769: goto -> 1795
    //   1772: aload_1
    //   1773: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1776: ifnull -> 1795
    //   1779: aload_1
    //   1780: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1783: astore #11
    //   1785: aload_1
    //   1786: aconst_null
    //   1787: invokevirtual setAnimator : (Landroid/animation/Animator;)V
    //   1790: aload #11
    //   1792: invokevirtual cancel : ()V
    //   1795: aload_1
    //   1796: invokevirtual getAnimatingAway : ()Landroid/view/View;
    //   1799: ifnonnull -> 1931
    //   1802: aload_1
    //   1803: invokevirtual getAnimator : ()Landroid/animation/Animator;
    //   1806: ifnull -> 1812
    //   1809: goto -> 1931
    //   1812: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   1815: ifeq -> 1849
    //   1818: new java/lang/StringBuilder
    //   1821: dup
    //   1822: invokespecial <init> : ()V
    //   1825: astore #11
    //   1827: aload #11
    //   1829: ldc_w 'movefrom CREATED: '
    //   1832: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1835: pop
    //   1836: aload #11
    //   1838: aload_1
    //   1839: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1842: pop
    //   1843: aload #11
    //   1845: invokevirtual toString : ()Ljava/lang/String;
    //   1848: pop
    //   1849: aload_1
    //   1850: getfield mRetaining : Z
    //   1853: ifne -> 1869
    //   1856: aload_1
    //   1857: invokevirtual performDestroy : ()V
    //   1860: aload_0
    //   1861: aload_1
    //   1862: iconst_0
    //   1863: invokevirtual dispatchOnFragmentDestroyed : (Landroidx/fragment/app/Fragment;Z)V
    //   1866: goto -> 1874
    //   1869: aload_1
    //   1870: iconst_0
    //   1871: putfield mState : I
    //   1874: aload_1
    //   1875: invokevirtual performDetach : ()V
    //   1878: aload_0
    //   1879: aload_1
    //   1880: iconst_0
    //   1881: invokevirtual dispatchOnFragmentDetached : (Landroidx/fragment/app/Fragment;Z)V
    //   1884: iload_2
    //   1885: istore #6
    //   1887: iload #5
    //   1889: ifne -> 1942
    //   1892: aload_1
    //   1893: getfield mRetaining : Z
    //   1896: ifne -> 1910
    //   1899: aload_0
    //   1900: aload_1
    //   1901: invokevirtual makeInactive : (Landroidx/fragment/app/Fragment;)V
    //   1904: iload_2
    //   1905: istore #6
    //   1907: goto -> 1942
    //   1910: aload_1
    //   1911: aconst_null
    //   1912: putfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   1915: aload_1
    //   1916: aconst_null
    //   1917: putfield mParentFragment : Landroidx/fragment/app/Fragment;
    //   1920: aload_1
    //   1921: aconst_null
    //   1922: putfield mFragmentManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   1925: iload_2
    //   1926: istore #6
    //   1928: goto -> 1942
    //   1931: aload_1
    //   1932: iload_2
    //   1933: invokevirtual setStateAfterAnimating : (I)V
    //   1936: iload #7
    //   1938: istore_2
    //   1939: goto -> 1945
    //   1942: iload #6
    //   1944: istore_2
    //   1945: aload_1
    //   1946: getfield mState : I
    //   1949: iload_2
    //   1950: if_icmpeq -> 2033
    //   1953: new java/lang/StringBuilder
    //   1956: dup
    //   1957: invokespecial <init> : ()V
    //   1960: astore #11
    //   1962: aload #11
    //   1964: ldc_w 'moveToState: Fragment state for '
    //   1967: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1970: pop
    //   1971: aload #11
    //   1973: aload_1
    //   1974: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1977: pop
    //   1978: aload #11
    //   1980: ldc_w ' not updated inline; '
    //   1983: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1986: pop
    //   1987: aload #11
    //   1989: ldc_w 'expected state '
    //   1992: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1995: pop
    //   1996: aload #11
    //   1998: iload_2
    //   1999: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   2002: pop
    //   2003: aload #11
    //   2005: ldc_w ' found '
    //   2008: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2011: pop
    //   2012: aload #11
    //   2014: aload_1
    //   2015: getfield mState : I
    //   2018: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   2021: pop
    //   2022: aload #11
    //   2024: invokevirtual toString : ()Ljava/lang/String;
    //   2027: pop
    //   2028: aload_1
    //   2029: iload_2
    //   2030: putfield mState : I
    //   2033: return
    // Exception table:
    //   from	to	target	type
    //   883	896	899	android/content/res/Resources$NotFoundException
  }
  
  public void noteStateNotSaved() {
    this.mSavedNonConfig = null;
    byte b = 0;
    this.mStateSaved = false;
    this.mStopped = false;
    int i = this.mAdded.size();
    while (b < i) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.noteStateNotSaved(); 
      b++;
    } 
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    if (!"fragment".equals(paramString))
      return null; 
    paramString = paramAttributeSet.getAttributeValue(null, "class");
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, FragmentTag.Fragment);
    int i = 0;
    String str1 = paramString;
    if (paramString == null)
      str1 = typedArray.getString(0); 
    int j = typedArray.getResourceId(1, -1);
    String str2 = typedArray.getString(2);
    typedArray.recycle();
    if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), str1))
      return null; 
    if (paramView != null)
      i = paramView.getId(); 
    if (i != -1 || j != -1 || str2 != null) {
      Fragment fragment2;
      if (j != -1) {
        Fragment fragment = findFragmentById(j);
      } else {
        paramView = null;
      } 
      View view2 = paramView;
      if (paramView == null) {
        view2 = paramView;
        if (str2 != null)
          fragment2 = findFragmentByTag(str2); 
      } 
      Fragment fragment1 = fragment2;
      if (fragment2 == null) {
        fragment1 = fragment2;
        if (i != -1)
          fragment1 = findFragmentById(i); 
      } 
      if (DEBUG) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("onCreateView: id=0x");
        stringBuilder2.append(Integer.toHexString(j));
        stringBuilder2.append(" fname=");
        stringBuilder2.append(str1);
        stringBuilder2.append(" existing=");
        stringBuilder2.append(fragment1);
        stringBuilder2.toString();
      } 
      if (fragment1 == null) {
        int k;
        fragment1 = this.mContainer.instantiate(paramContext, str1, null);
        fragment1.mFromLayout = true;
        if (j != 0) {
          k = j;
        } else {
          k = i;
        } 
        fragment1.mFragmentId = k;
        fragment1.mContainerId = i;
        fragment1.mTag = str2;
        fragment1.mInLayout = true;
        fragment1.mFragmentManager = this;
        FragmentHostCallback fragmentHostCallback = this.mHost;
        fragment1.mHost = fragmentHostCallback;
        fragment1.onInflate(fragmentHostCallback.getContext(), paramAttributeSet, fragment1.mSavedFragmentState);
        addFragment(fragment1, true);
      } else if (!fragment1.mInLayout) {
        fragment1.mInLayout = true;
        FragmentHostCallback fragmentHostCallback = this.mHost;
        fragment1.mHost = fragmentHostCallback;
        if (!fragment1.mRetaining)
          fragment1.onInflate(fragmentHostCallback.getContext(), paramAttributeSet, fragment1.mSavedFragmentState); 
      } else {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramAttributeSet.getPositionDescription());
        stringBuilder1.append(": Duplicate id 0x");
        stringBuilder1.append(Integer.toHexString(j));
        stringBuilder1.append(", tag ");
        stringBuilder1.append(str2);
        stringBuilder1.append(", or parent id 0x");
        stringBuilder1.append(Integer.toHexString(i));
        stringBuilder1.append(" with another fragment for ");
        stringBuilder1.append(str1);
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      if (this.mCurState < 1 && ((Fragment)stringBuilder1).mFromLayout) {
        moveToState((Fragment)stringBuilder1, 1, 0, 0, false);
      } else {
        moveToState((Fragment)stringBuilder1);
      } 
      View view1 = ((Fragment)stringBuilder1).mView;
      if (view1 != null) {
        if (j != 0)
          view1.setId(j); 
        if (((Fragment)stringBuilder1).mView.getTag() == null)
          ((Fragment)stringBuilder1).mView.setTag(str2); 
        return ((Fragment)stringBuilder1).mView;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Fragment ");
      stringBuilder1.append(str1);
      stringBuilder1.append(" did not create a view.");
      throw new IllegalStateException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramAttributeSet.getPositionDescription());
    stringBuilder.append(": Must specify unique android:id, android:tag, or have a parent with an id for ");
    stringBuilder.append(str1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  public void performPendingDeferredStart(Fragment paramFragment) {
    if (paramFragment.mDeferStart) {
      if (this.mExecutingActions) {
        this.mHavePendingDeferredStart = true;
        return;
      } 
      paramFragment.mDeferStart = false;
      moveToState(paramFragment, this.mCurState, 0, 0, false);
    } 
  }
  
  public void popBackStack(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0) {
      enqueueAction(new PopBackStackState(null, paramInt1, paramInt2), false);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Bad id: ");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean popBackStackImmediate() {
    checkStateLoss();
    return popBackStackImmediate(null, -1, 0);
  }
  
  boolean popBackStackState(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, String paramString, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mBackStack : Ljava/util/ArrayList;
    //   4: astore #8
    //   6: aload #8
    //   8: ifnonnull -> 13
    //   11: iconst_0
    //   12: ireturn
    //   13: aload_3
    //   14: ifnonnull -> 71
    //   17: iload #4
    //   19: ifge -> 71
    //   22: iload #5
    //   24: iconst_1
    //   25: iand
    //   26: ifne -> 71
    //   29: aload #8
    //   31: invokevirtual size : ()I
    //   34: iconst_1
    //   35: isub
    //   36: istore #4
    //   38: iload #4
    //   40: ifge -> 45
    //   43: iconst_0
    //   44: ireturn
    //   45: aload_1
    //   46: aload_0
    //   47: getfield mBackStack : Ljava/util/ArrayList;
    //   50: iload #4
    //   52: invokevirtual remove : (I)Ljava/lang/Object;
    //   55: invokevirtual add : (Ljava/lang/Object;)Z
    //   58: pop
    //   59: aload_2
    //   60: iconst_1
    //   61: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   64: invokevirtual add : (Ljava/lang/Object;)Z
    //   67: pop
    //   68: goto -> 322
    //   71: aload_3
    //   72: ifnonnull -> 89
    //   75: iload #4
    //   77: iflt -> 83
    //   80: goto -> 89
    //   83: iconst_m1
    //   84: istore #7
    //   86: goto -> 259
    //   89: aload_0
    //   90: getfield mBackStack : Ljava/util/ArrayList;
    //   93: invokevirtual size : ()I
    //   96: iconst_1
    //   97: isub
    //   98: istore #6
    //   100: iload #6
    //   102: iflt -> 162
    //   105: aload_0
    //   106: getfield mBackStack : Ljava/util/ArrayList;
    //   109: iload #6
    //   111: invokevirtual get : (I)Ljava/lang/Object;
    //   114: checkcast androidx/fragment/app/BackStackRecord
    //   117: astore #8
    //   119: aload_3
    //   120: ifnull -> 138
    //   123: aload_3
    //   124: aload #8
    //   126: invokevirtual getName : ()Ljava/lang/String;
    //   129: invokevirtual equals : (Ljava/lang/Object;)Z
    //   132: ifeq -> 138
    //   135: goto -> 162
    //   138: iload #4
    //   140: iflt -> 156
    //   143: iload #4
    //   145: aload #8
    //   147: getfield mIndex : I
    //   150: if_icmpne -> 156
    //   153: goto -> 162
    //   156: iinc #6, -1
    //   159: goto -> 100
    //   162: iload #6
    //   164: ifge -> 169
    //   167: iconst_0
    //   168: ireturn
    //   169: iload #6
    //   171: istore #7
    //   173: iload #5
    //   175: iconst_1
    //   176: iand
    //   177: ifeq -> 259
    //   180: iload #6
    //   182: iconst_1
    //   183: isub
    //   184: istore #5
    //   186: iload #5
    //   188: istore #7
    //   190: iload #5
    //   192: iflt -> 259
    //   195: aload_0
    //   196: getfield mBackStack : Ljava/util/ArrayList;
    //   199: iload #5
    //   201: invokevirtual get : (I)Ljava/lang/Object;
    //   204: checkcast androidx/fragment/app/BackStackRecord
    //   207: astore #8
    //   209: aload_3
    //   210: ifnull -> 229
    //   213: iload #5
    //   215: istore #6
    //   217: aload_3
    //   218: aload #8
    //   220: invokevirtual getName : ()Ljava/lang/String;
    //   223: invokevirtual equals : (Ljava/lang/Object;)Z
    //   226: ifne -> 180
    //   229: iload #5
    //   231: istore #7
    //   233: iload #4
    //   235: iflt -> 259
    //   238: iload #5
    //   240: istore #7
    //   242: iload #4
    //   244: aload #8
    //   246: getfield mIndex : I
    //   249: if_icmpne -> 259
    //   252: iload #5
    //   254: istore #6
    //   256: goto -> 180
    //   259: iload #7
    //   261: aload_0
    //   262: getfield mBackStack : Ljava/util/ArrayList;
    //   265: invokevirtual size : ()I
    //   268: iconst_1
    //   269: isub
    //   270: if_icmpne -> 275
    //   273: iconst_0
    //   274: ireturn
    //   275: aload_0
    //   276: getfield mBackStack : Ljava/util/ArrayList;
    //   279: invokevirtual size : ()I
    //   282: iconst_1
    //   283: isub
    //   284: istore #4
    //   286: iload #4
    //   288: iload #7
    //   290: if_icmple -> 322
    //   293: aload_1
    //   294: aload_0
    //   295: getfield mBackStack : Ljava/util/ArrayList;
    //   298: iload #4
    //   300: invokevirtual remove : (I)Ljava/lang/Object;
    //   303: invokevirtual add : (Ljava/lang/Object;)Z
    //   306: pop
    //   307: aload_2
    //   308: iconst_1
    //   309: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   312: invokevirtual add : (Ljava/lang/Object;)Z
    //   315: pop
    //   316: iinc #4, -1
    //   319: goto -> 286
    //   322: iconst_1
    //   323: ireturn
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment) {
    int i = paramFragment.mIndex;
    if (i >= 0) {
      paramBundle.putInt(paramString, i);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(paramFragment);
    stringBuilder.append(" is not currently in the FragmentManager");
    throwException(new IllegalStateException(stringBuilder.toString()));
    throw null;
  }
  
  public void removeFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("remove: ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" nesting=");
      stringBuilder.append(paramFragment.mBackStackNesting);
      stringBuilder.toString();
    } 
    boolean bool = paramFragment.isInBackStack();
    if (!paramFragment.mDetached || (bool ^ true) != 0)
      synchronized (this.mAdded) {
        this.mAdded.remove(paramFragment);
        if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
          this.mNeedMenuInvalidate = true; 
        paramFragment.mAdded = false;
        paramFragment.mRemoving = true;
        return;
      }  
  }
  
  void reportBackStackChanged() {
    if (this.mBackStackChangeListeners != null)
      for (byte b = 0; b < this.mBackStackChangeListeners.size(); b++)
        ((FragmentManager.OnBackStackChangedListener)this.mBackStackChangeListeners.get(b)).onBackStackChanged();  
  }
  
  void restoreAllState(Parcelable<ViewModelStore> paramParcelable, FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull -> 5
    //   4: return
    //   5: aload_1
    //   6: checkcast androidx/fragment/app/FragmentManagerState
    //   9: astore #9
    //   11: aload #9
    //   13: getfield mActive : [Landroidx/fragment/app/FragmentState;
    //   16: ifnonnull -> 20
    //   19: return
    //   20: aload_2
    //   21: ifnull -> 317
    //   24: aload_2
    //   25: invokevirtual getFragments : ()Ljava/util/List;
    //   28: astore #10
    //   30: aload_2
    //   31: invokevirtual getChildNonConfigs : ()Ljava/util/List;
    //   34: astore #8
    //   36: aload_2
    //   37: invokevirtual getViewModelStores : ()Ljava/util/List;
    //   40: astore #7
    //   42: aload #10
    //   44: ifnull -> 58
    //   47: aload #10
    //   49: invokeinterface size : ()I
    //   54: istore_3
    //   55: goto -> 60
    //   58: iconst_0
    //   59: istore_3
    //   60: iconst_0
    //   61: istore #4
    //   63: aload #8
    //   65: astore #6
    //   67: aload #7
    //   69: astore_1
    //   70: iload #4
    //   72: iload_3
    //   73: if_icmpge -> 322
    //   76: aload #10
    //   78: iload #4
    //   80: invokeinterface get : (I)Ljava/lang/Object;
    //   85: checkcast androidx/fragment/app/Fragment
    //   88: astore_1
    //   89: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   92: ifeq -> 126
    //   95: new java/lang/StringBuilder
    //   98: dup
    //   99: invokespecial <init> : ()V
    //   102: astore #6
    //   104: aload #6
    //   106: ldc_w 'restoreAllState: re-attaching retained '
    //   109: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload #6
    //   115: aload_1
    //   116: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: aload #6
    //   122: invokevirtual toString : ()Ljava/lang/String;
    //   125: pop
    //   126: iconst_0
    //   127: istore #5
    //   129: aload #9
    //   131: getfield mActive : [Landroidx/fragment/app/FragmentState;
    //   134: astore #6
    //   136: iload #5
    //   138: aload #6
    //   140: arraylength
    //   141: if_icmpge -> 165
    //   144: aload #6
    //   146: iload #5
    //   148: aaload
    //   149: getfield mIndex : I
    //   152: aload_1
    //   153: getfield mIndex : I
    //   156: if_icmpeq -> 165
    //   159: iinc #5, 1
    //   162: goto -> 129
    //   165: aload #9
    //   167: getfield mActive : [Landroidx/fragment/app/FragmentState;
    //   170: astore #6
    //   172: iload #5
    //   174: aload #6
    //   176: arraylength
    //   177: if_icmpeq -> 275
    //   180: aload #6
    //   182: iload #5
    //   184: aaload
    //   185: astore #11
    //   187: aload #11
    //   189: aload_1
    //   190: putfield mInstance : Landroidx/fragment/app/Fragment;
    //   193: aload_1
    //   194: aconst_null
    //   195: putfield mSavedViewState : Landroid/util/SparseArray;
    //   198: aload_1
    //   199: iconst_0
    //   200: putfield mBackStackNesting : I
    //   203: aload_1
    //   204: iconst_0
    //   205: putfield mInLayout : Z
    //   208: aload_1
    //   209: iconst_0
    //   210: putfield mAdded : Z
    //   213: aload_1
    //   214: aconst_null
    //   215: putfield mTarget : Landroidx/fragment/app/Fragment;
    //   218: aload #11
    //   220: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   223: astore #6
    //   225: aload #6
    //   227: ifnull -> 269
    //   230: aload #6
    //   232: aload_0
    //   233: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   236: invokevirtual getContext : ()Landroid/content/Context;
    //   239: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   242: invokevirtual setClassLoader : (Ljava/lang/ClassLoader;)V
    //   245: aload_1
    //   246: aload #11
    //   248: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   251: ldc_w 'android:view_state'
    //   254: invokevirtual getSparseParcelableArray : (Ljava/lang/String;)Landroid/util/SparseArray;
    //   257: putfield mSavedViewState : Landroid/util/SparseArray;
    //   260: aload_1
    //   261: aload #11
    //   263: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   266: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   269: iinc #4, 1
    //   272: goto -> 63
    //   275: new java/lang/StringBuilder
    //   278: dup
    //   279: invokespecial <init> : ()V
    //   282: astore_2
    //   283: aload_2
    //   284: ldc_w 'Could not find active fragment with index '
    //   287: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: pop
    //   291: aload_2
    //   292: aload_1
    //   293: getfield mIndex : I
    //   296: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   299: pop
    //   300: aload_0
    //   301: new java/lang/IllegalStateException
    //   304: dup
    //   305: aload_2
    //   306: invokevirtual toString : ()Ljava/lang/String;
    //   309: invokespecial <init> : (Ljava/lang/String;)V
    //   312: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   315: aconst_null
    //   316: athrow
    //   317: aconst_null
    //   318: astore #6
    //   320: aconst_null
    //   321: astore_1
    //   322: aload_0
    //   323: new android/util/SparseArray
    //   326: dup
    //   327: aload #9
    //   329: getfield mActive : [Landroidx/fragment/app/FragmentState;
    //   332: arraylength
    //   333: invokespecial <init> : (I)V
    //   336: putfield mActive : Landroid/util/SparseArray;
    //   339: iconst_0
    //   340: istore_3
    //   341: aload #9
    //   343: getfield mActive : [Landroidx/fragment/app/FragmentState;
    //   346: astore #7
    //   348: iload_3
    //   349: aload #7
    //   351: arraylength
    //   352: if_icmpge -> 536
    //   355: aload #7
    //   357: iload_3
    //   358: aaload
    //   359: astore #10
    //   361: aload #10
    //   363: ifnull -> 530
    //   366: aload #6
    //   368: ifnull -> 398
    //   371: iload_3
    //   372: aload #6
    //   374: invokeinterface size : ()I
    //   379: if_icmpge -> 398
    //   382: aload #6
    //   384: iload_3
    //   385: invokeinterface get : (I)Ljava/lang/Object;
    //   390: checkcast androidx/fragment/app/FragmentManagerNonConfig
    //   393: astore #7
    //   395: goto -> 401
    //   398: aconst_null
    //   399: astore #7
    //   401: aload_1
    //   402: ifnull -> 430
    //   405: iload_3
    //   406: aload_1
    //   407: invokeinterface size : ()I
    //   412: if_icmpge -> 430
    //   415: aload_1
    //   416: iload_3
    //   417: invokeinterface get : (I)Ljava/lang/Object;
    //   422: checkcast androidx/lifecycle/ViewModelStore
    //   425: astore #8
    //   427: goto -> 433
    //   430: aconst_null
    //   431: astore #8
    //   433: aload #10
    //   435: aload_0
    //   436: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   439: aload_0
    //   440: getfield mContainer : Landroidx/fragment/app/FragmentContainer;
    //   443: aload_0
    //   444: getfield mParent : Landroidx/fragment/app/Fragment;
    //   447: aload #7
    //   449: aload #8
    //   451: invokevirtual instantiate : (Landroidx/fragment/app/FragmentHostCallback;Landroidx/fragment/app/FragmentContainer;Landroidx/fragment/app/Fragment;Landroidx/fragment/app/FragmentManagerNonConfig;Landroidx/lifecycle/ViewModelStore;)Landroidx/fragment/app/Fragment;
    //   454: astore #7
    //   456: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   459: ifeq -> 510
    //   462: new java/lang/StringBuilder
    //   465: dup
    //   466: invokespecial <init> : ()V
    //   469: astore #8
    //   471: aload #8
    //   473: ldc_w 'restoreAllState: active #'
    //   476: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: pop
    //   480: aload #8
    //   482: iload_3
    //   483: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   486: pop
    //   487: aload #8
    //   489: ldc_w ': '
    //   492: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: pop
    //   496: aload #8
    //   498: aload #7
    //   500: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   503: pop
    //   504: aload #8
    //   506: invokevirtual toString : ()Ljava/lang/String;
    //   509: pop
    //   510: aload_0
    //   511: getfield mActive : Landroid/util/SparseArray;
    //   514: aload #7
    //   516: getfield mIndex : I
    //   519: aload #7
    //   521: invokevirtual put : (ILjava/lang/Object;)V
    //   524: aload #10
    //   526: aconst_null
    //   527: putfield mInstance : Landroidx/fragment/app/Fragment;
    //   530: iinc #3, 1
    //   533: goto -> 341
    //   536: aload_2
    //   537: ifnull -> 672
    //   540: aload_2
    //   541: invokevirtual getFragments : ()Ljava/util/List;
    //   544: astore_1
    //   545: aload_1
    //   546: ifnull -> 559
    //   549: aload_1
    //   550: invokeinterface size : ()I
    //   555: istore_3
    //   556: goto -> 561
    //   559: iconst_0
    //   560: istore_3
    //   561: iconst_0
    //   562: istore #4
    //   564: iload #4
    //   566: iload_3
    //   567: if_icmpge -> 672
    //   570: aload_1
    //   571: iload #4
    //   573: invokeinterface get : (I)Ljava/lang/Object;
    //   578: checkcast androidx/fragment/app/Fragment
    //   581: astore #6
    //   583: aload #6
    //   585: getfield mTargetIndex : I
    //   588: istore #5
    //   590: iload #5
    //   592: iflt -> 666
    //   595: aload #6
    //   597: aload_0
    //   598: getfield mActive : Landroid/util/SparseArray;
    //   601: iload #5
    //   603: invokevirtual get : (I)Ljava/lang/Object;
    //   606: checkcast androidx/fragment/app/Fragment
    //   609: putfield mTarget : Landroidx/fragment/app/Fragment;
    //   612: aload #6
    //   614: getfield mTarget : Landroidx/fragment/app/Fragment;
    //   617: ifnonnull -> 666
    //   620: new java/lang/StringBuilder
    //   623: dup
    //   624: invokespecial <init> : ()V
    //   627: astore_2
    //   628: aload_2
    //   629: ldc_w 'Re-attaching retained fragment '
    //   632: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   635: pop
    //   636: aload_2
    //   637: aload #6
    //   639: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   642: pop
    //   643: aload_2
    //   644: ldc_w ' target no longer exists: '
    //   647: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   650: pop
    //   651: aload_2
    //   652: aload #6
    //   654: getfield mTargetIndex : I
    //   657: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   660: pop
    //   661: aload_2
    //   662: invokevirtual toString : ()Ljava/lang/String;
    //   665: pop
    //   666: iinc #4, 1
    //   669: goto -> 564
    //   672: aload_0
    //   673: getfield mAdded : Ljava/util/ArrayList;
    //   676: invokevirtual clear : ()V
    //   679: aload #9
    //   681: getfield mAdded : [I
    //   684: ifnull -> 867
    //   687: iconst_0
    //   688: istore_3
    //   689: aload #9
    //   691: getfield mAdded : [I
    //   694: astore_1
    //   695: iload_3
    //   696: aload_1
    //   697: arraylength
    //   698: if_icmpge -> 867
    //   701: aload_0
    //   702: getfield mActive : Landroid/util/SparseArray;
    //   705: aload_1
    //   706: iload_3
    //   707: iaload
    //   708: invokevirtual get : (I)Ljava/lang/Object;
    //   711: checkcast androidx/fragment/app/Fragment
    //   714: astore_2
    //   715: aload_2
    //   716: ifnull -> 822
    //   719: aload_2
    //   720: iconst_1
    //   721: putfield mAdded : Z
    //   724: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   727: ifeq -> 771
    //   730: new java/lang/StringBuilder
    //   733: dup
    //   734: invokespecial <init> : ()V
    //   737: astore_1
    //   738: aload_1
    //   739: ldc_w 'restoreAllState: added #'
    //   742: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   745: pop
    //   746: aload_1
    //   747: iload_3
    //   748: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   751: pop
    //   752: aload_1
    //   753: ldc_w ': '
    //   756: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   759: pop
    //   760: aload_1
    //   761: aload_2
    //   762: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   765: pop
    //   766: aload_1
    //   767: invokevirtual toString : ()Ljava/lang/String;
    //   770: pop
    //   771: aload_0
    //   772: getfield mAdded : Ljava/util/ArrayList;
    //   775: aload_2
    //   776: invokevirtual contains : (Ljava/lang/Object;)Z
    //   779: ifne -> 811
    //   782: aload_0
    //   783: getfield mAdded : Ljava/util/ArrayList;
    //   786: astore_1
    //   787: aload_1
    //   788: monitorenter
    //   789: aload_0
    //   790: getfield mAdded : Ljava/util/ArrayList;
    //   793: aload_2
    //   794: invokevirtual add : (Ljava/lang/Object;)Z
    //   797: pop
    //   798: aload_1
    //   799: monitorexit
    //   800: iinc #3, 1
    //   803: goto -> 689
    //   806: astore_2
    //   807: aload_1
    //   808: monitorexit
    //   809: aload_2
    //   810: athrow
    //   811: new java/lang/IllegalStateException
    //   814: dup
    //   815: ldc_w 'Already added!'
    //   818: invokespecial <init> : (Ljava/lang/String;)V
    //   821: athrow
    //   822: new java/lang/StringBuilder
    //   825: dup
    //   826: invokespecial <init> : ()V
    //   829: astore_1
    //   830: aload_1
    //   831: ldc_w 'No instantiated fragment for index #'
    //   834: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   837: pop
    //   838: aload_1
    //   839: aload #9
    //   841: getfield mAdded : [I
    //   844: iload_3
    //   845: iaload
    //   846: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   849: pop
    //   850: aload_0
    //   851: new java/lang/IllegalStateException
    //   854: dup
    //   855: aload_1
    //   856: invokevirtual toString : ()Ljava/lang/String;
    //   859: invokespecial <init> : (Ljava/lang/String;)V
    //   862: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   865: aconst_null
    //   866: athrow
    //   867: aload #9
    //   869: getfield mBackStack : [Landroidx/fragment/app/BackStackState;
    //   872: astore_1
    //   873: aload_1
    //   874: ifnull -> 1040
    //   877: aload_0
    //   878: new java/util/ArrayList
    //   881: dup
    //   882: aload_1
    //   883: arraylength
    //   884: invokespecial <init> : (I)V
    //   887: putfield mBackStack : Ljava/util/ArrayList;
    //   890: iconst_0
    //   891: istore_3
    //   892: aload #9
    //   894: getfield mBackStack : [Landroidx/fragment/app/BackStackState;
    //   897: astore_1
    //   898: iload_3
    //   899: aload_1
    //   900: arraylength
    //   901: if_icmpge -> 1045
    //   904: aload_1
    //   905: iload_3
    //   906: aaload
    //   907: aload_0
    //   908: invokevirtual instantiate : (Landroidx/fragment/app/FragmentManagerImpl;)Landroidx/fragment/app/BackStackRecord;
    //   911: astore_1
    //   912: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   915: ifeq -> 1007
    //   918: new java/lang/StringBuilder
    //   921: dup
    //   922: invokespecial <init> : ()V
    //   925: astore_2
    //   926: aload_2
    //   927: ldc_w 'restoreAllState: back stack #'
    //   930: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   933: pop
    //   934: aload_2
    //   935: iload_3
    //   936: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   939: pop
    //   940: aload_2
    //   941: ldc_w ' (index '
    //   944: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   947: pop
    //   948: aload_2
    //   949: aload_1
    //   950: getfield mIndex : I
    //   953: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   956: pop
    //   957: aload_2
    //   958: ldc_w '): '
    //   961: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   964: pop
    //   965: aload_2
    //   966: aload_1
    //   967: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   970: pop
    //   971: aload_2
    //   972: invokevirtual toString : ()Ljava/lang/String;
    //   975: pop
    //   976: new java/io/PrintWriter
    //   979: dup
    //   980: new androidx/core/util/LogWriter
    //   983: dup
    //   984: ldc_w 'FragmentManager'
    //   987: invokespecial <init> : (Ljava/lang/String;)V
    //   990: invokespecial <init> : (Ljava/io/Writer;)V
    //   993: astore_2
    //   994: aload_1
    //   995: ldc_w '  '
    //   998: aload_2
    //   999: iconst_0
    //   1000: invokevirtual dump : (Ljava/lang/String;Ljava/io/PrintWriter;Z)V
    //   1003: aload_2
    //   1004: invokevirtual close : ()V
    //   1007: aload_0
    //   1008: getfield mBackStack : Ljava/util/ArrayList;
    //   1011: aload_1
    //   1012: invokevirtual add : (Ljava/lang/Object;)Z
    //   1015: pop
    //   1016: aload_1
    //   1017: getfield mIndex : I
    //   1020: istore #4
    //   1022: iload #4
    //   1024: iflt -> 1034
    //   1027: aload_0
    //   1028: iload #4
    //   1030: aload_1
    //   1031: invokevirtual setBackStackIndex : (ILandroidx/fragment/app/BackStackRecord;)V
    //   1034: iinc #3, 1
    //   1037: goto -> 892
    //   1040: aload_0
    //   1041: aconst_null
    //   1042: putfield mBackStack : Ljava/util/ArrayList;
    //   1045: aload #9
    //   1047: getfield mPrimaryNavActiveIndex : I
    //   1050: istore_3
    //   1051: iload_3
    //   1052: iflt -> 1070
    //   1055: aload_0
    //   1056: aload_0
    //   1057: getfield mActive : Landroid/util/SparseArray;
    //   1060: iload_3
    //   1061: invokevirtual get : (I)Ljava/lang/Object;
    //   1064: checkcast androidx/fragment/app/Fragment
    //   1067: putfield mPrimaryNav : Landroidx/fragment/app/Fragment;
    //   1070: aload_0
    //   1071: aload #9
    //   1073: getfield mNextFragmentIndex : I
    //   1076: putfield mNextFragmentIndex : I
    //   1079: return
    // Exception table:
    //   from	to	target	type
    //   789	800	806	finally
    //   807	809	806	finally
  }
  
  FragmentManagerNonConfig retainNonConfig() {
    setRetaining(this.mSavedNonConfig);
    return this.mSavedNonConfig;
  }
  
  Parcelable saveAllState() {
    StringBuilder stringBuilder;
    forcePostponedTransactions();
    endAnimatingAwayFragments();
    execPendingActions();
    this.mStateSaved = true;
    BackStackState[] arrayOfBackStackState2 = null;
    this.mSavedNonConfig = null;
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray == null || sparseArray.size() <= 0)
      return null; 
    int j = this.mActive.size();
    FragmentState[] arrayOfFragmentState = new FragmentState[j];
    boolean bool = false;
    byte b = 0;
    int i = 0;
    while (b < j) {
      Fragment fragment1 = (Fragment)this.mActive.valueAt(b);
      if (fragment1 != null)
        if (fragment1.mIndex >= 0) {
          FragmentState fragmentState = new FragmentState(fragment1);
          arrayOfFragmentState[b] = fragmentState;
          if (fragment1.mState > 0 && fragmentState.mSavedFragmentState == null) {
            fragmentState.mSavedFragmentState = saveFragmentBasicState(fragment1);
            Fragment fragment2 = fragment1.mTarget;
            if (fragment2 != null)
              if (fragment2.mIndex >= 0) {
                if (fragmentState.mSavedFragmentState == null)
                  fragmentState.mSavedFragmentState = new Bundle(); 
                putFragment(fragmentState.mSavedFragmentState, "android:target_state", fragment1.mTarget);
                i = fragment1.mTargetRequestCode;
                if (i != 0)
                  fragmentState.mSavedFragmentState.putInt("android:target_req_state", i); 
              } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Failure saving state: ");
                stringBuilder.append(fragment1);
                stringBuilder.append(" has target not in fragment manager: ");
                stringBuilder.append(fragment1.mTarget);
                throwException(new IllegalStateException(stringBuilder.toString()));
                throw null;
              }  
          } else {
            ((FragmentState)stringBuilder).mSavedFragmentState = fragment1.mSavedFragmentState;
          } 
          if (DEBUG) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Saved state of ");
            stringBuilder1.append(fragment1);
            stringBuilder1.append(": ");
            stringBuilder1.append(((FragmentState)stringBuilder).mSavedFragmentState);
            stringBuilder1.toString();
          } 
          i = 1;
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Failure saving state: active ");
          stringBuilder.append(fragment1);
          stringBuilder.append(" has cleared index: ");
          stringBuilder.append(fragment1.mIndex);
          throwException(new IllegalStateException(stringBuilder.toString()));
          throw null;
        }  
      b++;
    } 
    if (i == 0) {
      boolean bool1 = DEBUG;
      return null;
    } 
    i = this.mAdded.size();
    if (i > 0) {
      int[] arrayOfInt = new int[i];
      b = 0;
      while (true) {
        int[] arrayOfInt1 = arrayOfInt;
        if (b < i) {
          arrayOfInt[b] = ((Fragment)this.mAdded.get(b)).mIndex;
          if (arrayOfInt[b] >= 0) {
            if (DEBUG) {
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("saveAllState: adding fragment #");
              stringBuilder2.append(b);
              stringBuilder2.append(": ");
              stringBuilder2.append(this.mAdded.get(b));
              stringBuilder2.toString();
            } 
            b++;
            continue;
          } 
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Failure saving state: active ");
          stringBuilder1.append(this.mAdded.get(b));
          stringBuilder1.append(" has cleared index: ");
          stringBuilder1.append(arrayOfInt[b]);
          throwException(new IllegalStateException(stringBuilder1.toString()));
          throw null;
        } 
        break;
      } 
    } else {
      sparseArray = null;
    } 
    ArrayList<BackStackRecord> arrayList = this.mBackStack;
    BackStackState[] arrayOfBackStackState1 = arrayOfBackStackState2;
    if (arrayList != null) {
      i = arrayList.size();
      arrayOfBackStackState1 = arrayOfBackStackState2;
      if (i > 0) {
        arrayOfBackStackState2 = new BackStackState[i];
        b = bool;
        while (true) {
          arrayOfBackStackState1 = arrayOfBackStackState2;
          if (b < i) {
            arrayOfBackStackState2[b] = new BackStackState(this.mBackStack.get(b));
            if (DEBUG) {
              stringBuilder = new StringBuilder();
              stringBuilder.append("saveAllState: adding back stack #");
              stringBuilder.append(b);
              stringBuilder.append(": ");
              stringBuilder.append(this.mBackStack.get(b));
              stringBuilder.toString();
            } 
            b++;
            continue;
          } 
          break;
        } 
      } 
    } 
    FragmentManagerState fragmentManagerState = new FragmentManagerState();
    fragmentManagerState.mActive = arrayOfFragmentState;
    fragmentManagerState.mAdded = (int[])sparseArray;
    fragmentManagerState.mBackStack = (BackStackState[])stringBuilder;
    Fragment fragment = this.mPrimaryNav;
    if (fragment != null)
      fragmentManagerState.mPrimaryNavActiveIndex = fragment.mIndex; 
    fragmentManagerState.mNextFragmentIndex = this.mNextFragmentIndex;
    saveNonConfig();
    return fragmentManagerState;
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment) {
    if (this.mStateBundle == null)
      this.mStateBundle = new Bundle(); 
    paramFragment.performSaveInstanceState(this.mStateBundle);
    dispatchOnFragmentSaveInstanceState(paramFragment, this.mStateBundle, false);
    if (!this.mStateBundle.isEmpty()) {
      bundle2 = this.mStateBundle;
      this.mStateBundle = null;
    } else {
      bundle2 = null;
    } 
    if (paramFragment.mView != null)
      saveFragmentViewState(paramFragment); 
    Bundle bundle1 = bundle2;
    if (paramFragment.mSavedViewState != null) {
      bundle1 = bundle2;
      if (bundle2 == null)
        bundle1 = new Bundle(); 
      bundle1.putSparseParcelableArray("android:view_state", paramFragment.mSavedViewState);
    } 
    Bundle bundle2 = bundle1;
    if (!paramFragment.mUserVisibleHint) {
      bundle2 = bundle1;
      if (bundle1 == null)
        bundle2 = new Bundle(); 
      bundle2.putBoolean("android:user_visible_hint", paramFragment.mUserVisibleHint);
    } 
    return bundle2;
  }
  
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment) {
    Bundle bundle;
    int i = paramFragment.mIndex;
    Fragment.SavedState savedState = null;
    if (i >= 0) {
      Fragment.SavedState savedState1 = savedState;
      if (paramFragment.mState > 0) {
        bundle = saveFragmentBasicState(paramFragment);
        savedState1 = savedState;
        if (bundle != null)
          savedState1 = new Fragment.SavedState(bundle); 
      } 
      return savedState1;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(bundle);
    stringBuilder.append(" is not currently in the FragmentManager");
    throwException(new IllegalStateException(stringBuilder.toString()));
    throw null;
  }
  
  void saveFragmentViewState(Fragment paramFragment) {
    if (paramFragment.mInnerView == null)
      return; 
    SparseArray<Parcelable> sparseArray = this.mStateArray;
    if (sparseArray == null) {
      this.mStateArray = new SparseArray();
    } else {
      sparseArray.clear();
    } 
    paramFragment.mInnerView.saveHierarchyState(this.mStateArray);
    if (this.mStateArray.size() > 0) {
      paramFragment.mSavedViewState = this.mStateArray;
      this.mStateArray = null;
    } 
  }
  
  void saveNonConfig() {
    List<ViewModelStore> list1;
    List<ViewModelStore> list2;
    List list;
    if (this.mActive != null) {
      ArrayList<Fragment> arrayList1 = null;
      ArrayList<Fragment> arrayList3 = null;
      ArrayList<Fragment> arrayList2 = arrayList3;
      byte b = 0;
      while (true) {
        list = arrayList1;
        list2 = (List)arrayList3;
        list1 = (List)arrayList2;
        if (b < this.mActive.size()) {
          ArrayList<Fragment> arrayList4;
          Fragment fragment = (Fragment)this.mActive.valueAt(b);
          list = arrayList1;
          ArrayList<Fragment> arrayList5 = arrayList3;
          list2 = (List)arrayList2;
          if (fragment != null) {
            FragmentManagerNonConfig fragmentManagerNonConfig;
            list1 = (List)arrayList1;
            if (fragment.mRetainInstance) {
              byte b1;
              list2 = (List)arrayList1;
              if (arrayList1 == null)
                list2 = new ArrayList(); 
              list2.add(fragment);
              Fragment fragment1 = fragment.mTarget;
              if (fragment1 != null) {
                b1 = fragment1.mIndex;
              } else {
                b1 = -1;
              } 
              fragment.mTargetIndex = b1;
              list1 = list2;
              if (DEBUG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("retainNonConfig: keeping retained ");
                stringBuilder.append(fragment);
                stringBuilder.toString();
                list1 = list2;
              } 
            } 
            FragmentManagerImpl fragmentManagerImpl = fragment.mChildFragmentManager;
            if (fragmentManagerImpl != null) {
              fragmentManagerImpl.saveNonConfig();
              fragmentManagerNonConfig = fragment.mChildFragmentManager.mSavedNonConfig;
            } else {
              fragmentManagerNonConfig = fragment.mChildNonConfig;
            } 
            ArrayList<Fragment> arrayList = arrayList3;
            if (arrayList3 == null) {
              arrayList = arrayList3;
              if (fragmentManagerNonConfig != null) {
                arrayList3 = new ArrayList<Fragment>(this.mActive.size());
                byte b1 = 0;
                while (true) {
                  arrayList = arrayList3;
                  if (b1 < b) {
                    arrayList3.add(null);
                    b1++;
                    continue;
                  } 
                  break;
                } 
              } 
            } 
            if (arrayList != null)
              arrayList.add(fragmentManagerNonConfig); 
            arrayList3 = arrayList2;
            if (arrayList2 == null) {
              arrayList3 = arrayList2;
              if (fragment.mViewModelStore != null) {
                arrayList2 = new ArrayList<Fragment>(this.mActive.size());
                byte b1 = 0;
                while (true) {
                  arrayList3 = arrayList2;
                  if (b1 < b) {
                    arrayList2.add(null);
                    b1++;
                    continue;
                  } 
                  break;
                } 
              } 
            } 
            list = list1;
            arrayList5 = arrayList;
            arrayList4 = arrayList3;
            if (arrayList3 != null) {
              arrayList3.add(fragment.mViewModelStore);
              arrayList4 = arrayList3;
              arrayList5 = arrayList;
              list = list1;
            } 
          } 
          b++;
          arrayList1 = (ArrayList<Fragment>)list;
          arrayList3 = arrayList5;
          arrayList2 = arrayList4;
          continue;
        } 
        break;
      } 
    } else {
      list = null;
      list2 = null;
      list1 = list2;
    } 
    if (list == null && list2 == null && list1 == null) {
      this.mSavedNonConfig = null;
    } else {
      this.mSavedNonConfig = new FragmentManagerNonConfig(list, (List)list2, list1);
    } 
  }
  
  void scheduleCommit() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   6: astore #4
    //   8: iconst_0
    //   9: istore_3
    //   10: aload #4
    //   12: ifnull -> 30
    //   15: aload_0
    //   16: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   19: invokevirtual isEmpty : ()Z
    //   22: ifne -> 30
    //   25: iconst_1
    //   26: istore_1
    //   27: goto -> 32
    //   30: iconst_0
    //   31: istore_1
    //   32: iload_3
    //   33: istore_2
    //   34: aload_0
    //   35: getfield mPendingActions : Ljava/util/ArrayList;
    //   38: ifnull -> 56
    //   41: iload_3
    //   42: istore_2
    //   43: aload_0
    //   44: getfield mPendingActions : Ljava/util/ArrayList;
    //   47: invokevirtual size : ()I
    //   50: iconst_1
    //   51: if_icmpne -> 56
    //   54: iconst_1
    //   55: istore_2
    //   56: iload_1
    //   57: ifne -> 64
    //   60: iload_2
    //   61: ifeq -> 93
    //   64: aload_0
    //   65: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   68: invokevirtual getHandler : ()Landroid/os/Handler;
    //   71: aload_0
    //   72: getfield mExecCommit : Ljava/lang/Runnable;
    //   75: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   78: aload_0
    //   79: getfield mHost : Landroidx/fragment/app/FragmentHostCallback;
    //   82: invokevirtual getHandler : ()Landroid/os/Handler;
    //   85: aload_0
    //   86: getfield mExecCommit : Ljava/lang/Runnable;
    //   89: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   92: pop
    //   93: aload_0
    //   94: monitorexit
    //   95: return
    //   96: astore #4
    //   98: aload_0
    //   99: monitorexit
    //   100: aload #4
    //   102: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	96	finally
    //   15	25	96	finally
    //   34	41	96	finally
    //   43	54	96	finally
    //   64	93	96	finally
    //   93	95	96	finally
    //   98	100	96	finally
  }
  
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: ifnonnull -> 25
    //   9: new java/util/ArrayList
    //   12: astore #5
    //   14: aload #5
    //   16: invokespecial <init> : ()V
    //   19: aload_0
    //   20: aload #5
    //   22: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   25: aload_0
    //   26: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   29: invokevirtual size : ()I
    //   32: istore #4
    //   34: iload #4
    //   36: istore_3
    //   37: iload_1
    //   38: iload #4
    //   40: if_icmpge -> 110
    //   43: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   46: ifeq -> 97
    //   49: new java/lang/StringBuilder
    //   52: astore #5
    //   54: aload #5
    //   56: invokespecial <init> : ()V
    //   59: aload #5
    //   61: ldc_w 'Setting back stack index '
    //   64: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload #5
    //   70: iload_1
    //   71: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   74: pop
    //   75: aload #5
    //   77: ldc_w ' to '
    //   80: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: aload #5
    //   86: aload_2
    //   87: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: aload #5
    //   93: invokevirtual toString : ()Ljava/lang/String;
    //   96: pop
    //   97: aload_0
    //   98: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   101: iload_1
    //   102: aload_2
    //   103: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   106: pop
    //   107: goto -> 266
    //   110: iload_3
    //   111: iload_1
    //   112: if_icmpge -> 203
    //   115: aload_0
    //   116: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   119: aconst_null
    //   120: invokevirtual add : (Ljava/lang/Object;)Z
    //   123: pop
    //   124: aload_0
    //   125: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   128: ifnonnull -> 147
    //   131: new java/util/ArrayList
    //   134: astore #5
    //   136: aload #5
    //   138: invokespecial <init> : ()V
    //   141: aload_0
    //   142: aload #5
    //   144: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   147: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   150: ifeq -> 185
    //   153: new java/lang/StringBuilder
    //   156: astore #5
    //   158: aload #5
    //   160: invokespecial <init> : ()V
    //   163: aload #5
    //   165: ldc_w 'Adding available back stack index '
    //   168: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: pop
    //   172: aload #5
    //   174: iload_3
    //   175: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: aload #5
    //   181: invokevirtual toString : ()Ljava/lang/String;
    //   184: pop
    //   185: aload_0
    //   186: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   189: iload_3
    //   190: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   193: invokevirtual add : (Ljava/lang/Object;)Z
    //   196: pop
    //   197: iinc #3, 1
    //   200: goto -> 110
    //   203: getstatic androidx/fragment/app/FragmentManagerImpl.DEBUG : Z
    //   206: ifeq -> 257
    //   209: new java/lang/StringBuilder
    //   212: astore #5
    //   214: aload #5
    //   216: invokespecial <init> : ()V
    //   219: aload #5
    //   221: ldc_w 'Adding back stack index '
    //   224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: pop
    //   228: aload #5
    //   230: iload_1
    //   231: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload #5
    //   237: ldc_w ' with '
    //   240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: pop
    //   244: aload #5
    //   246: aload_2
    //   247: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   250: pop
    //   251: aload #5
    //   253: invokevirtual toString : ()Ljava/lang/String;
    //   256: pop
    //   257: aload_0
    //   258: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   261: aload_2
    //   262: invokevirtual add : (Ljava/lang/Object;)Z
    //   265: pop
    //   266: aload_0
    //   267: monitorexit
    //   268: return
    //   269: astore_2
    //   270: aload_0
    //   271: monitorexit
    //   272: aload_2
    //   273: athrow
    // Exception table:
    //   from	to	target	type
    //   2	25	269	finally
    //   25	34	269	finally
    //   43	97	269	finally
    //   97	107	269	finally
    //   115	147	269	finally
    //   147	185	269	finally
    //   185	197	269	finally
    //   203	257	269	finally
    //   257	266	269	finally
    //   266	268	269	finally
    //   270	272	269	finally
  }
  
  public void setPrimaryNavigationFragment(Fragment paramFragment) {
    if (paramFragment == null || (this.mActive.get(paramFragment.mIndex) == paramFragment && (paramFragment.mHost == null || paramFragment.getFragmentManager() == this))) {
      this.mPrimaryNav = paramFragment;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(paramFragment);
    stringBuilder.append(" is not an active fragment of FragmentManager ");
    stringBuilder.append(this);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void showFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("show: ");
      stringBuilder.append(paramFragment);
      stringBuilder.toString();
    } 
    if (paramFragment.mHidden) {
      paramFragment.mHidden = false;
      paramFragment.mHiddenChanged ^= 0x1;
    } 
  }
  
  void startPendingDeferredFragments() {
    if (this.mActive == null)
      return; 
    for (byte b = 0; b < this.mActive.size(); b++) {
      Fragment fragment = (Fragment)this.mActive.valueAt(b);
      if (fragment != null)
        performPendingDeferredStart(fragment); 
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("FragmentManager{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" in ");
    Fragment fragment = this.mParent;
    if (fragment != null) {
      DebugUtils.buildShortClassTag(fragment, stringBuilder);
    } else {
      DebugUtils.buildShortClassTag(this.mHost, stringBuilder);
    } 
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
  
  private static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
    View mView;
    
    AnimateOnHWLayerIfNeededListener(View param1View, Animation.AnimationListener param1AnimationListener) {
      super(param1AnimationListener);
      this.mView = param1View;
    }
    
    public void onAnimationEnd(Animation param1Animation) {
      if (ViewCompat.isAttachedToWindow(this.mView) || Build.VERSION.SDK_INT >= 24) {
        this.mView.post(new Runnable() {
              final FragmentManagerImpl.AnimateOnHWLayerIfNeededListener this$0;
              
              public void run() {
                FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.this.mView.setLayerType(0, null);
              }
            });
      } else {
        this.mView.setLayerType(0, null);
      } 
      super.onAnimationEnd(param1Animation);
    }
  }
  
  class null implements Runnable {
    final FragmentManagerImpl.AnimateOnHWLayerIfNeededListener this$0;
    
    public void run() {
      this.this$0.mView.setLayerType(0, null);
    }
  }
  
  private static class AnimationListenerWrapper implements Animation.AnimationListener {
    private final Animation.AnimationListener mWrapped;
    
    AnimationListenerWrapper(Animation.AnimationListener param1AnimationListener) {
      this.mWrapped = param1AnimationListener;
    }
    
    public void onAnimationEnd(Animation param1Animation) {
      Animation.AnimationListener animationListener = this.mWrapped;
      if (animationListener != null)
        animationListener.onAnimationEnd(param1Animation); 
    }
    
    public void onAnimationRepeat(Animation param1Animation) {
      Animation.AnimationListener animationListener = this.mWrapped;
      if (animationListener != null)
        animationListener.onAnimationRepeat(param1Animation); 
    }
    
    public void onAnimationStart(Animation param1Animation) {
      Animation.AnimationListener animationListener = this.mWrapped;
      if (animationListener != null)
        animationListener.onAnimationStart(param1Animation); 
    }
  }
  
  private static class AnimationOrAnimator {
    public final Animation animation = null;
    
    public final Animator animator;
    
    AnimationOrAnimator(Animator param1Animator) {
      this.animator = param1Animator;
      if (param1Animator != null)
        return; 
      throw new IllegalStateException("Animator cannot be null");
    }
    
    AnimationOrAnimator(Animation param1Animation) {
      this.animator = null;
      if (param1Animation != null)
        return; 
      throw new IllegalStateException("Animation cannot be null");
    }
  }
  
  private static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
    View mView;
    
    AnimatorOnHWLayerIfNeededListener(View param1View) {
      this.mView = param1View;
    }
    
    public void onAnimationEnd(Animator param1Animator) {
      this.mView.setLayerType(0, null);
      param1Animator.removeListener((Animator.AnimatorListener)this);
    }
    
    public void onAnimationStart(Animator param1Animator) {
      this.mView.setLayerType(2, null);
    }
  }
  
  private static class EndViewTransitionAnimator extends AnimationSet implements Runnable {
    private boolean mAnimating = true;
    
    private final View mChild;
    
    private boolean mEnded;
    
    private final ViewGroup mParent;
    
    private boolean mTransitionEnded;
    
    EndViewTransitionAnimator(Animation param1Animation, ViewGroup param1ViewGroup, View param1View) {
      super(false);
      this.mParent = param1ViewGroup;
      this.mChild = param1View;
      addAnimation(param1Animation);
      this.mParent.post(this);
    }
    
    public boolean getTransformation(long param1Long, Transformation param1Transformation) {
      this.mAnimating = true;
      if (this.mEnded)
        return this.mTransitionEnded ^ true; 
      if (!super.getTransformation(param1Long, param1Transformation)) {
        this.mEnded = true;
        OneShotPreDrawListener.add((View)this.mParent, this);
      } 
      return true;
    }
    
    public boolean getTransformation(long param1Long, Transformation param1Transformation, float param1Float) {
      this.mAnimating = true;
      if (this.mEnded)
        return this.mTransitionEnded ^ true; 
      if (!super.getTransformation(param1Long, param1Transformation, param1Float)) {
        this.mEnded = true;
        OneShotPreDrawListener.add((View)this.mParent, this);
      } 
      return true;
    }
    
    public void run() {
      if (!this.mEnded && this.mAnimating) {
        this.mAnimating = false;
        this.mParent.post(this);
      } else {
        this.mParent.endViewTransition(this.mChild);
        this.mTransitionEnded = true;
      } 
    }
  }
  
  private static final class FragmentLifecycleCallbacksHolder {
    final FragmentManager.FragmentLifecycleCallbacks mCallback;
    
    final boolean mRecursive;
  }
  
  static class FragmentTag {
    public static final int[] Fragment = new int[] { 16842755, 16842960, 16842961 };
  }
  
  static interface OpGenerator {
    boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1);
  }
  
  private class PopBackStackState implements OpGenerator {
    final int mFlags;
    
    final int mId;
    
    final String mName;
    
    final FragmentManagerImpl this$0;
    
    PopBackStackState(String param1String, int param1Int1, int param1Int2) {
      this.mName = param1String;
      this.mId = param1Int1;
      this.mFlags = param1Int2;
    }
    
    public boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1) {
      Fragment fragment = FragmentManagerImpl.this.mPrimaryNav;
      if (fragment != null && this.mId < 0 && this.mName == null) {
        FragmentManager fragmentManager = fragment.peekChildFragmentManager();
        if (fragmentManager != null && fragmentManager.popBackStackImmediate())
          return false; 
      } 
      return FragmentManagerImpl.this.popBackStackState(param1ArrayList, param1ArrayList1, this.mName, this.mId, this.mFlags);
    }
  }
  
  static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
    final boolean mIsBack;
    
    private int mNumPostponed;
    
    final BackStackRecord mRecord;
    
    StartEnterTransitionListener(BackStackRecord param1BackStackRecord, boolean param1Boolean) {
      this.mIsBack = param1Boolean;
      this.mRecord = param1BackStackRecord;
    }
    
    public void cancelTransaction() {
      BackStackRecord backStackRecord = this.mRecord;
      backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, false, false);
    }
    
    public void completeTransaction() {
      int i = this.mNumPostponed;
      byte b = 0;
      if (i > 0) {
        i = 1;
      } else {
        i = 0;
      } 
      FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
      int j = fragmentManagerImpl.mAdded.size();
      while (b < j) {
        Fragment fragment = fragmentManagerImpl.mAdded.get(b);
        fragment.setOnStartEnterTransitionListener(null);
        if (i != 0 && fragment.isPostponed())
          fragment.startPostponedEnterTransition(); 
        b++;
      } 
      BackStackRecord backStackRecord = this.mRecord;
      backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, i ^ 0x1, true);
    }
    
    public boolean isReady() {
      boolean bool;
      if (this.mNumPostponed == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onStartEnterTransition() {
      this.mNumPostponed--;
      if (this.mNumPostponed != 0)
        return; 
      this.mRecord.mManager.scheduleCommit();
    }
    
    public void startListening() {
      this.mNumPostponed++;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/fragment/app/FragmentManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */