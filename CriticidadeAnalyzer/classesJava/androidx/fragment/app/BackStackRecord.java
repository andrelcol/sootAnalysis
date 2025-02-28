package androidx.fragment.app;

import androidx.core.util.LogWriter;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator {
  boolean mAddToBackStack;
  
  boolean mAllowAddToBackStack = true;
  
  int mBreadCrumbShortTitleRes;
  
  CharSequence mBreadCrumbShortTitleText;
  
  int mBreadCrumbTitleRes;
  
  CharSequence mBreadCrumbTitleText;
  
  ArrayList<Runnable> mCommitRunnables;
  
  boolean mCommitted;
  
  int mEnterAnim;
  
  int mExitAnim;
  
  int mIndex = -1;
  
  final FragmentManagerImpl mManager;
  
  String mName;
  
  ArrayList<Op> mOps = new ArrayList<Op>();
  
  int mPopEnterAnim;
  
  int mPopExitAnim;
  
  boolean mReorderingAllowed = false;
  
  ArrayList<String> mSharedElementSourceNames;
  
  ArrayList<String> mSharedElementTargetNames;
  
  int mTransition;
  
  int mTransitionStyle;
  
  public BackStackRecord(FragmentManagerImpl paramFragmentManagerImpl) {
    this.mManager = paramFragmentManagerImpl;
  }
  
  private void doAddOp(int paramInt1, Fragment paramFragment, String paramString, int paramInt2) {
    StringBuilder stringBuilder2;
    Class<?> clazz = paramFragment.getClass();
    int i = clazz.getModifiers();
    if (!clazz.isAnonymousClass() && Modifier.isPublic(i) && (!clazz.isMemberClass() || Modifier.isStatic(i))) {
      paramFragment.mFragmentManager = this.mManager;
      if (paramString != null) {
        String str = paramFragment.mTag;
        if (str == null || paramString.equals(str)) {
          paramFragment.mTag = paramString;
        } else {
          stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Can't change tag of fragment ");
          stringBuilder2.append(paramFragment);
          stringBuilder2.append(": was ");
          stringBuilder2.append(paramFragment.mTag);
          stringBuilder2.append(" now ");
          stringBuilder2.append(paramString);
          throw new IllegalStateException(stringBuilder2.toString());
        } 
      } 
      if (paramInt1 != 0) {
        StringBuilder stringBuilder;
        if (paramInt1 != -1) {
          i = paramFragment.mFragmentId;
          if (i == 0 || i == paramInt1) {
            paramFragment.mFragmentId = paramInt1;
            paramFragment.mContainerId = paramInt1;
          } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Can't change container ID of fragment ");
            stringBuilder.append(paramFragment);
            stringBuilder.append(": was ");
            stringBuilder.append(paramFragment.mFragmentId);
            stringBuilder.append(" now ");
            stringBuilder.append(paramInt1);
            throw new IllegalStateException(stringBuilder.toString());
          } 
        } else {
          stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Can't add fragment ");
          stringBuilder2.append(paramFragment);
          stringBuilder2.append(" with tag ");
          stringBuilder2.append((String)stringBuilder);
          stringBuilder2.append(" to container view with no id");
          throw new IllegalArgumentException(stringBuilder2.toString());
        } 
      } 
      addOp(new Op(paramInt2, paramFragment));
      return;
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Fragment ");
    stringBuilder1.append(stringBuilder2.getCanonicalName());
    stringBuilder1.append(" must be a public static class to be  properly recreated from");
    stringBuilder1.append(" instance state.");
    throw new IllegalStateException(stringBuilder1.toString());
  }
  
  private static boolean isFragmentPostponed(Op paramOp) {
    boolean bool;
    Fragment fragment = paramOp.fragment;
    if (fragment != null && fragment.mAdded && fragment.mView != null && !fragment.mDetached && !fragment.mHidden && fragment.isPostponed()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public FragmentTransaction add(int paramInt, Fragment paramFragment) {
    doAddOp(paramInt, paramFragment, null, 1);
    return this;
  }
  
  public FragmentTransaction add(Fragment paramFragment, String paramString) {
    doAddOp(0, paramFragment, paramString, 1);
    return this;
  }
  
  void addOp(Op paramOp) {
    this.mOps.add(paramOp);
    paramOp.enterAnim = this.mEnterAnim;
    paramOp.exitAnim = this.mExitAnim;
    paramOp.popEnterAnim = this.mPopEnterAnim;
    paramOp.popExitAnim = this.mPopExitAnim;
  }
  
  public FragmentTransaction addToBackStack(String paramString) {
    if (this.mAllowAddToBackStack) {
      this.mAddToBackStack = true;
      this.mName = paramString;
      return this;
    } 
    throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
  }
  
  void bumpBackStackNesting(int paramInt) {
    if (!this.mAddToBackStack)
      return; 
    if (FragmentManagerImpl.DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bump nesting in ");
      stringBuilder.append(this);
      stringBuilder.append(" by ");
      stringBuilder.append(paramInt);
      stringBuilder.toString();
    } 
    int i = this.mOps.size();
    for (byte b = 0; b < i; b++) {
      Op op = this.mOps.get(b);
      Fragment fragment = op.fragment;
      if (fragment != null) {
        fragment.mBackStackNesting += paramInt;
        if (FragmentManagerImpl.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Bump nesting of ");
          stringBuilder.append(op.fragment);
          stringBuilder.append(" to ");
          stringBuilder.append(op.fragment.mBackStackNesting);
          stringBuilder.toString();
        } 
      } 
    } 
  }
  
  public int commit() {
    return commitInternal(false);
  }
  
  public int commitAllowingStateLoss() {
    return commitInternal(true);
  }
  
  int commitInternal(boolean paramBoolean) {
    if (!this.mCommitted) {
      if (FragmentManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Commit: ");
        stringBuilder.append(this);
        stringBuilder.toString();
        PrintWriter printWriter = new PrintWriter((Writer)new LogWriter("FragmentManager"));
        dump("  ", null, printWriter, null);
        printWriter.close();
      } 
      this.mCommitted = true;
      if (this.mAddToBackStack) {
        this.mIndex = this.mManager.allocBackStackIndex(this);
      } else {
        this.mIndex = -1;
      } 
      this.mManager.enqueueAction(this, paramBoolean);
      return this.mIndex;
    } 
    throw new IllegalStateException("commit already called");
  }
  
  public void commitNowAllowingStateLoss() {
    disallowAddToBackStack();
    this.mManager.execSingleAction(this, true);
  }
  
  public FragmentTransaction disallowAddToBackStack() {
    if (!this.mAddToBackStack) {
      this.mAllowAddToBackStack = false;
      return this;
    } 
    throw new IllegalStateException("This transaction is already being added to the back stack");
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    dump(paramString, paramPrintWriter, true);
  }
  
  public void dump(String paramString, PrintWriter paramPrintWriter, boolean paramBoolean) {
    if (paramBoolean) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mName=");
      paramPrintWriter.print(this.mName);
      paramPrintWriter.print(" mIndex=");
      paramPrintWriter.print(this.mIndex);
      paramPrintWriter.print(" mCommitted=");
      paramPrintWriter.println(this.mCommitted);
      if (this.mTransition != 0) {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mTransition=#");
        paramPrintWriter.print(Integer.toHexString(this.mTransition));
        paramPrintWriter.print(" mTransitionStyle=#");
        paramPrintWriter.println(Integer.toHexString(this.mTransitionStyle));
      } 
      if (this.mEnterAnim != 0 || this.mExitAnim != 0) {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mEnterAnim=#");
        paramPrintWriter.print(Integer.toHexString(this.mEnterAnim));
        paramPrintWriter.print(" mExitAnim=#");
        paramPrintWriter.println(Integer.toHexString(this.mExitAnim));
      } 
      if (this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mPopEnterAnim=#");
        paramPrintWriter.print(Integer.toHexString(this.mPopEnterAnim));
        paramPrintWriter.print(" mPopExitAnim=#");
        paramPrintWriter.println(Integer.toHexString(this.mPopExitAnim));
      } 
      if (this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mBreadCrumbTitleRes=#");
        paramPrintWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
        paramPrintWriter.print(" mBreadCrumbTitleText=");
        paramPrintWriter.println(this.mBreadCrumbTitleText);
      } 
      if (this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mBreadCrumbShortTitleRes=#");
        paramPrintWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
        paramPrintWriter.print(" mBreadCrumbShortTitleText=");
        paramPrintWriter.println(this.mBreadCrumbShortTitleText);
      } 
    } 
    if (!this.mOps.isEmpty()) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Operations:");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("    ");
      stringBuilder.toString();
      int i = this.mOps.size();
      for (byte b = 0; b < i; b++) {
        String str;
        Op op = this.mOps.get(b);
        switch (op.cmd) {
          default:
            stringBuilder = new StringBuilder();
            stringBuilder.append("cmd=");
            stringBuilder.append(op.cmd);
            str = stringBuilder.toString();
            break;
          case 9:
            str = "UNSET_PRIMARY_NAV";
            break;
          case 8:
            str = "SET_PRIMARY_NAV";
            break;
          case 7:
            str = "ATTACH";
            break;
          case 6:
            str = "DETACH";
            break;
          case 5:
            str = "SHOW";
            break;
          case 4:
            str = "HIDE";
            break;
          case 3:
            str = "REMOVE";
            break;
          case 2:
            str = "REPLACE";
            break;
          case 1:
            str = "ADD";
            break;
          case 0:
            str = "NULL";
            break;
        } 
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  Op #");
        paramPrintWriter.print(b);
        paramPrintWriter.print(": ");
        paramPrintWriter.print(str);
        paramPrintWriter.print(" ");
        paramPrintWriter.println(op.fragment);
        if (paramBoolean) {
          if (op.enterAnim != 0 || op.exitAnim != 0) {
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("enterAnim=#");
            paramPrintWriter.print(Integer.toHexString(op.enterAnim));
            paramPrintWriter.print(" exitAnim=#");
            paramPrintWriter.println(Integer.toHexString(op.exitAnim));
          } 
          if (op.popEnterAnim != 0 || op.popExitAnim != 0) {
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("popEnterAnim=#");
            paramPrintWriter.print(Integer.toHexString(op.popEnterAnim));
            paramPrintWriter.print(" popExitAnim=#");
            paramPrintWriter.println(Integer.toHexString(op.popExitAnim));
          } 
        } 
      } 
    } 
  }
  
  void executeOps() {
    int i = this.mOps.size();
    for (byte b = 0; b < i; b++) {
      StringBuilder stringBuilder;
      Op op = this.mOps.get(b);
      Fragment fragment = op.fragment;
      if (fragment != null)
        fragment.setNextTransition(this.mTransition, this.mTransitionStyle); 
      switch (op.cmd) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown cmd: ");
          stringBuilder.append(op.cmd);
          throw new IllegalArgumentException(stringBuilder.toString());
        case 9:
          this.mManager.setPrimaryNavigationFragment(null);
          break;
        case 8:
          this.mManager.setPrimaryNavigationFragment((Fragment)stringBuilder);
          break;
        case 7:
          stringBuilder.setNextAnim(op.enterAnim);
          this.mManager.attachFragment((Fragment)stringBuilder);
          break;
        case 6:
          stringBuilder.setNextAnim(op.exitAnim);
          this.mManager.detachFragment((Fragment)stringBuilder);
          break;
        case 5:
          stringBuilder.setNextAnim(op.enterAnim);
          this.mManager.showFragment((Fragment)stringBuilder);
          break;
        case 4:
          stringBuilder.setNextAnim(op.exitAnim);
          this.mManager.hideFragment((Fragment)stringBuilder);
          break;
        case 3:
          stringBuilder.setNextAnim(op.exitAnim);
          this.mManager.removeFragment((Fragment)stringBuilder);
          break;
        case 1:
          stringBuilder.setNextAnim(op.enterAnim);
          this.mManager.addFragment((Fragment)stringBuilder, false);
          break;
      } 
      if (!this.mReorderingAllowed && op.cmd != 1 && stringBuilder != null)
        this.mManager.moveFragmentToExpectedState((Fragment)stringBuilder); 
    } 
    if (!this.mReorderingAllowed) {
      FragmentManagerImpl fragmentManagerImpl = this.mManager;
      fragmentManagerImpl.moveToState(fragmentManagerImpl.mCurState, true);
    } 
  }
  
  void executePopOps(boolean paramBoolean) {
    for (int i = this.mOps.size() - 1; i >= 0; i--) {
      StringBuilder stringBuilder;
      Op op = this.mOps.get(i);
      Fragment fragment = op.fragment;
      if (fragment != null)
        fragment.setNextTransition(FragmentManagerImpl.reverseTransit(this.mTransition), this.mTransitionStyle); 
      switch (op.cmd) {
        default:
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown cmd: ");
          stringBuilder.append(op.cmd);
          throw new IllegalArgumentException(stringBuilder.toString());
        case 9:
          this.mManager.setPrimaryNavigationFragment((Fragment)stringBuilder);
          break;
        case 8:
          this.mManager.setPrimaryNavigationFragment(null);
          break;
        case 7:
          stringBuilder.setNextAnim(op.popExitAnim);
          this.mManager.detachFragment((Fragment)stringBuilder);
          break;
        case 6:
          stringBuilder.setNextAnim(op.popEnterAnim);
          this.mManager.attachFragment((Fragment)stringBuilder);
          break;
        case 5:
          stringBuilder.setNextAnim(op.popExitAnim);
          this.mManager.hideFragment((Fragment)stringBuilder);
          break;
        case 4:
          stringBuilder.setNextAnim(op.popEnterAnim);
          this.mManager.showFragment((Fragment)stringBuilder);
          break;
        case 3:
          stringBuilder.setNextAnim(op.popEnterAnim);
          this.mManager.addFragment((Fragment)stringBuilder, false);
          break;
        case 1:
          stringBuilder.setNextAnim(op.popExitAnim);
          this.mManager.removeFragment((Fragment)stringBuilder);
          break;
      } 
      if (!this.mReorderingAllowed && op.cmd != 3 && stringBuilder != null)
        this.mManager.moveFragmentToExpectedState((Fragment)stringBuilder); 
    } 
    if (!this.mReorderingAllowed && paramBoolean) {
      FragmentManagerImpl fragmentManagerImpl = this.mManager;
      fragmentManagerImpl.moveToState(fragmentManagerImpl.mCurState, true);
    } 
  }
  
  Fragment expandOps(ArrayList<Fragment> paramArrayList, Fragment paramFragment) {
    int i = 0;
    Fragment fragment;
    for (fragment = paramFragment; i < this.mOps.size(); fragment = paramFragment) {
      Fragment fragment1;
      Op op = this.mOps.get(i);
      int j = op.cmd;
      if (j != 1)
        if (j != 2) {
          if (j != 3 && j != 6) {
            if (j != 7) {
              if (j != 8) {
                j = i;
                paramFragment = fragment;
              } else {
                this.mOps.add(i, new Op(9, fragment));
                j = i + 1;
                paramFragment = op.fragment;
              } 
              continue;
            } 
          } else {
            paramArrayList.remove(op.fragment);
            fragment1 = op.fragment;
            j = i;
            paramFragment = fragment;
            if (fragment1 == fragment) {
              this.mOps.add(i, new Op(9, fragment1));
              j = i + 1;
              paramFragment = null;
            } 
            continue;
          } 
        } else {
          Fragment fragment2 = ((Op)fragment1).fragment;
          int m = fragment2.mContainerId;
          j = paramArrayList.size() - 1;
          paramFragment = fragment;
          int k = 0;
          while (j >= 0) {
            Fragment fragment3 = paramArrayList.get(j);
            int n = k;
            int i1 = i;
            fragment = paramFragment;
            if (fragment3.mContainerId == m)
              if (fragment3 == fragment2) {
                n = 1;
                i1 = i;
                fragment = paramFragment;
              } else {
                n = i;
                fragment = paramFragment;
                if (fragment3 == paramFragment) {
                  this.mOps.add(i, new Op(9, fragment3));
                  n = i + 1;
                  fragment = null;
                } 
                Op op1 = new Op(3, fragment3);
                op1.enterAnim = ((Op)fragment1).enterAnim;
                op1.popEnterAnim = ((Op)fragment1).popEnterAnim;
                op1.exitAnim = ((Op)fragment1).exitAnim;
                op1.popExitAnim = ((Op)fragment1).popExitAnim;
                this.mOps.add(n, op1);
                paramArrayList.remove(fragment3);
                i1 = n + 1;
                n = k;
              }  
            j--;
            k = n;
            i = i1;
            paramFragment = fragment;
          } 
          if (k != 0) {
            this.mOps.remove(i);
            i--;
          } else {
            ((Op)fragment1).cmd = 1;
            paramArrayList.add(fragment2);
          } 
          j = i;
          continue;
        }  
      paramArrayList.add(((Op)fragment1).fragment);
      paramFragment = fragment;
      j = i;
      continue;
      i = SYNTHETIC_LOCAL_VARIABLE_4 + 1;
    } 
    return fragment;
  }
  
  public boolean generateOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    if (FragmentManagerImpl.DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Run: ");
      stringBuilder.append(this);
      stringBuilder.toString();
    } 
    paramArrayList.add(this);
    paramArrayList1.add(Boolean.valueOf(false));
    if (this.mAddToBackStack)
      this.mManager.addBackStackState(this); 
    return true;
  }
  
  public String getName() {
    return this.mName;
  }
  
  boolean interactsWith(int paramInt) {
    int i = this.mOps.size();
    for (byte b = 0; b < i; b++) {
      int j;
      Fragment fragment = ((Op)this.mOps.get(b)).fragment;
      if (fragment != null) {
        j = fragment.mContainerId;
      } else {
        j = 0;
      } 
      if (j && j == paramInt)
        return true; 
    } 
    return false;
  }
  
  boolean interactsWith(ArrayList<BackStackRecord> paramArrayList, int paramInt1, int paramInt2) {
    if (paramInt2 == paramInt1)
      return false; 
    int j = this.mOps.size();
    byte b = 0;
    int i;
    for (i = -1; b < j; i = m) {
      int k;
      Fragment fragment = ((Op)this.mOps.get(b)).fragment;
      if (fragment != null) {
        k = fragment.mContainerId;
      } else {
        k = 0;
      } 
      int m = i;
      if (k) {
        m = i;
        if (k != i) {
          for (i = paramInt1; i < paramInt2; i++) {
            BackStackRecord backStackRecord = paramArrayList.get(i);
            int n = backStackRecord.mOps.size();
            for (m = 0; m < n; m++) {
              int i1;
              Fragment fragment1 = ((Op)backStackRecord.mOps.get(m)).fragment;
              if (fragment1 != null) {
                i1 = fragment1.mContainerId;
              } else {
                i1 = 0;
              } 
              if (i1 == k)
                return true; 
            } 
          } 
          m = k;
        } 
      } 
      b++;
    } 
    return false;
  }
  
  boolean isPostponed() {
    for (byte b = 0; b < this.mOps.size(); b++) {
      if (isFragmentPostponed(this.mOps.get(b)))
        return true; 
    } 
    return false;
  }
  
  public FragmentTransaction remove(Fragment paramFragment) {
    addOp(new Op(3, paramFragment));
    return this;
  }
  
  public FragmentTransaction replace(int paramInt, Fragment paramFragment) {
    replace(paramInt, paramFragment, null);
    return this;
  }
  
  public FragmentTransaction replace(int paramInt, Fragment paramFragment, String paramString) {
    if (paramInt != 0) {
      doAddOp(paramInt, paramFragment, paramString, 2);
      return this;
    } 
    throw new IllegalArgumentException("Must use non-zero containerViewId");
  }
  
  public void runOnCommitRunnables() {
    ArrayList<Runnable> arrayList = this.mCommitRunnables;
    if (arrayList != null) {
      byte b = 0;
      int i = arrayList.size();
      while (b < i) {
        ((Runnable)this.mCommitRunnables.get(b)).run();
        b++;
      } 
      this.mCommitRunnables = null;
    } 
  }
  
  void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener paramOnStartEnterTransitionListener) {
    for (byte b = 0; b < this.mOps.size(); b++) {
      Op op = this.mOps.get(b);
      if (isFragmentPostponed(op))
        op.fragment.setOnStartEnterTransitionListener(paramOnStartEnterTransitionListener); 
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("BackStackEntry{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    if (this.mIndex >= 0) {
      stringBuilder.append(" #");
      stringBuilder.append(this.mIndex);
    } 
    if (this.mName != null) {
      stringBuilder.append(" ");
      stringBuilder.append(this.mName);
    } 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  Fragment trackAddedFragmentsInPop(ArrayList<Fragment> paramArrayList, Fragment paramFragment) {
    byte b = 0;
    while (b < this.mOps.size()) {
      Op op = this.mOps.get(b);
      int i = op.cmd;
      if (i != 1)
        if (i != 3) {
          switch (i) {
            case 9:
              paramFragment = op.fragment;
              break;
            case 8:
              paramFragment = null;
              break;
            case 6:
              paramArrayList.add(op.fragment);
              break;
            case 7:
              paramArrayList.remove(op.fragment);
              break;
          } 
          b++;
        }  
    } 
    return paramFragment;
  }
  
  static final class Op {
    int cmd;
    
    int enterAnim;
    
    int exitAnim;
    
    Fragment fragment;
    
    int popEnterAnim;
    
    int popExitAnim;
    
    Op() {}
    
    Op(int param1Int, Fragment param1Fragment) {
      this.cmd = param1Int;
      this.fragment = param1Fragment;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/fragment/app/BackStackRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */