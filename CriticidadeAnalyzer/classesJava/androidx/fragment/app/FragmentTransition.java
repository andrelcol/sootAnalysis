package androidx.fragment.app;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class FragmentTransition {
  private static final int[] INVERSE_OPS = new int[] { 0, 3, 0, 1, 5, 4, 7, 6, 9, 8 };
  
  private static final FragmentTransitionImpl PLATFORM_IMPL;
  
  private static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();
  
  private static void addSharedElementsWithMatchingNames(ArrayList<View> paramArrayList, ArrayMap<String, View> paramArrayMap, Collection<String> paramCollection) {
    for (int i = paramArrayMap.size() - 1; i >= 0; i--) {
      View view = (View)paramArrayMap.valueAt(i);
      if (paramCollection.contains(ViewCompat.getTransitionName(view)))
        paramArrayList.add(view); 
    } 
  }
  
  private static void addToFirstInLastOut(BackStackRecord paramBackStackRecord, BackStackRecord.Op paramOp, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_1
    //   1: getfield fragment : Landroidx/fragment/app/Fragment;
    //   4: astore #12
    //   6: aload #12
    //   8: ifnonnull -> 12
    //   11: return
    //   12: aload #12
    //   14: getfield mContainerId : I
    //   17: istore #8
    //   19: iload #8
    //   21: ifne -> 25
    //   24: return
    //   25: iload_3
    //   26: ifeq -> 42
    //   29: getstatic androidx/fragment/app/FragmentTransition.INVERSE_OPS : [I
    //   32: aload_1
    //   33: getfield cmd : I
    //   36: iaload
    //   37: istore #5
    //   39: goto -> 48
    //   42: aload_1
    //   43: getfield cmd : I
    //   46: istore #5
    //   48: iconst_0
    //   49: istore #9
    //   51: iconst_0
    //   52: istore #10
    //   54: iload #5
    //   56: iconst_1
    //   57: if_icmpeq -> 285
    //   60: iload #5
    //   62: iconst_3
    //   63: if_icmpeq -> 201
    //   66: iload #5
    //   68: iconst_4
    //   69: if_icmpeq -> 150
    //   72: iload #5
    //   74: iconst_5
    //   75: if_icmpeq -> 108
    //   78: iload #5
    //   80: bipush #6
    //   82: if_icmpeq -> 201
    //   85: iload #5
    //   87: bipush #7
    //   89: if_icmpeq -> 285
    //   92: iconst_0
    //   93: istore #5
    //   95: iload #10
    //   97: istore #9
    //   99: iconst_0
    //   100: istore #6
    //   102: iconst_0
    //   103: istore #7
    //   105: goto -> 331
    //   108: iload #4
    //   110: ifeq -> 140
    //   113: aload #12
    //   115: getfield mHiddenChanged : Z
    //   118: ifeq -> 322
    //   121: aload #12
    //   123: getfield mHidden : Z
    //   126: ifne -> 322
    //   129: aload #12
    //   131: getfield mAdded : Z
    //   134: ifeq -> 322
    //   137: goto -> 316
    //   140: aload #12
    //   142: getfield mHidden : Z
    //   145: istore #9
    //   147: goto -> 325
    //   150: iload #4
    //   152: ifeq -> 182
    //   155: aload #12
    //   157: getfield mHiddenChanged : Z
    //   160: ifeq -> 247
    //   163: aload #12
    //   165: getfield mAdded : Z
    //   168: ifeq -> 247
    //   171: aload #12
    //   173: getfield mHidden : Z
    //   176: ifeq -> 247
    //   179: goto -> 241
    //   182: aload #12
    //   184: getfield mAdded : Z
    //   187: ifeq -> 247
    //   190: aload #12
    //   192: getfield mHidden : Z
    //   195: ifne -> 247
    //   198: goto -> 179
    //   201: iload #4
    //   203: ifeq -> 253
    //   206: aload #12
    //   208: getfield mAdded : Z
    //   211: ifne -> 247
    //   214: aload #12
    //   216: getfield mView : Landroid/view/View;
    //   219: astore_1
    //   220: aload_1
    //   221: ifnull -> 247
    //   224: aload_1
    //   225: invokevirtual getVisibility : ()I
    //   228: ifne -> 247
    //   231: aload #12
    //   233: getfield mPostponedAlpha : F
    //   236: fconst_0
    //   237: fcmpl
    //   238: iflt -> 247
    //   241: iconst_1
    //   242: istore #5
    //   244: goto -> 272
    //   247: iconst_0
    //   248: istore #5
    //   250: goto -> 272
    //   253: aload #12
    //   255: getfield mAdded : Z
    //   258: ifeq -> 247
    //   261: aload #12
    //   263: getfield mHidden : Z
    //   266: ifne -> 247
    //   269: goto -> 241
    //   272: iload #5
    //   274: istore #7
    //   276: iconst_0
    //   277: istore #5
    //   279: iconst_1
    //   280: istore #6
    //   282: goto -> 331
    //   285: iload #4
    //   287: ifeq -> 300
    //   290: aload #12
    //   292: getfield mIsNewlyAdded : Z
    //   295: istore #9
    //   297: goto -> 325
    //   300: aload #12
    //   302: getfield mAdded : Z
    //   305: ifne -> 322
    //   308: aload #12
    //   310: getfield mHidden : Z
    //   313: ifne -> 322
    //   316: iconst_1
    //   317: istore #9
    //   319: goto -> 325
    //   322: iconst_0
    //   323: istore #9
    //   325: iconst_1
    //   326: istore #5
    //   328: goto -> 99
    //   331: aload_2
    //   332: iload #8
    //   334: invokevirtual get : (I)Ljava/lang/Object;
    //   337: checkcast androidx/fragment/app/FragmentTransition$FragmentContainerTransition
    //   340: astore #11
    //   342: aload #11
    //   344: astore_1
    //   345: iload #9
    //   347: ifeq -> 375
    //   350: aload #11
    //   352: aload_2
    //   353: iload #8
    //   355: invokestatic ensureContainer : (Landroidx/fragment/app/FragmentTransition$FragmentContainerTransition;Landroid/util/SparseArray;I)Landroidx/fragment/app/FragmentTransition$FragmentContainerTransition;
    //   358: astore_1
    //   359: aload_1
    //   360: aload #12
    //   362: putfield lastIn : Landroidx/fragment/app/Fragment;
    //   365: aload_1
    //   366: iload_3
    //   367: putfield lastInIsPop : Z
    //   370: aload_1
    //   371: aload_0
    //   372: putfield lastInTransaction : Landroidx/fragment/app/BackStackRecord;
    //   375: iload #4
    //   377: ifne -> 452
    //   380: iload #5
    //   382: ifeq -> 452
    //   385: aload_1
    //   386: ifnull -> 403
    //   389: aload_1
    //   390: getfield firstOut : Landroidx/fragment/app/Fragment;
    //   393: aload #12
    //   395: if_acmpne -> 403
    //   398: aload_1
    //   399: aconst_null
    //   400: putfield firstOut : Landroidx/fragment/app/Fragment;
    //   403: aload_0
    //   404: getfield mManager : Landroidx/fragment/app/FragmentManagerImpl;
    //   407: astore #11
    //   409: aload #12
    //   411: getfield mState : I
    //   414: iconst_1
    //   415: if_icmpge -> 452
    //   418: aload #11
    //   420: getfield mCurState : I
    //   423: iconst_1
    //   424: if_icmplt -> 452
    //   427: aload_0
    //   428: getfield mReorderingAllowed : Z
    //   431: ifne -> 452
    //   434: aload #11
    //   436: aload #12
    //   438: invokevirtual makeActive : (Landroidx/fragment/app/Fragment;)V
    //   441: aload #11
    //   443: aload #12
    //   445: iconst_1
    //   446: iconst_0
    //   447: iconst_0
    //   448: iconst_0
    //   449: invokevirtual moveToState : (Landroidx/fragment/app/Fragment;IIIZ)V
    //   452: aload_1
    //   453: astore #11
    //   455: iload #7
    //   457: ifeq -> 502
    //   460: aload_1
    //   461: ifnull -> 474
    //   464: aload_1
    //   465: astore #11
    //   467: aload_1
    //   468: getfield firstOut : Landroidx/fragment/app/Fragment;
    //   471: ifnonnull -> 502
    //   474: aload_1
    //   475: aload_2
    //   476: iload #8
    //   478: invokestatic ensureContainer : (Landroidx/fragment/app/FragmentTransition$FragmentContainerTransition;Landroid/util/SparseArray;I)Landroidx/fragment/app/FragmentTransition$FragmentContainerTransition;
    //   481: astore #11
    //   483: aload #11
    //   485: aload #12
    //   487: putfield firstOut : Landroidx/fragment/app/Fragment;
    //   490: aload #11
    //   492: iload_3
    //   493: putfield firstOutIsPop : Z
    //   496: aload #11
    //   498: aload_0
    //   499: putfield firstOutTransaction : Landroidx/fragment/app/BackStackRecord;
    //   502: iload #4
    //   504: ifne -> 533
    //   507: iload #6
    //   509: ifeq -> 533
    //   512: aload #11
    //   514: ifnull -> 533
    //   517: aload #11
    //   519: getfield lastIn : Landroidx/fragment/app/Fragment;
    //   522: aload #12
    //   524: if_acmpne -> 533
    //   527: aload #11
    //   529: aconst_null
    //   530: putfield lastIn : Landroidx/fragment/app/Fragment;
    //   533: return
  }
  
  public static void calculateFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean) {
    int i = paramBackStackRecord.mOps.size();
    for (byte b = 0; b < i; b++)
      addToFirstInLastOut(paramBackStackRecord, paramBackStackRecord.mOps.get(b), paramSparseArray, false, paramBoolean); 
  }
  
  private static ArrayMap<String, String> calculateNameOverrides(int paramInt1, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt2, int paramInt3) {
    ArrayMap<String, String> arrayMap = new ArrayMap();
    while (--paramInt3 >= paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt3);
      if (backStackRecord.interactsWith(paramInt1)) {
        boolean bool = ((Boolean)paramArrayList1.get(paramInt3)).booleanValue();
        ArrayList<String> arrayList = backStackRecord.mSharedElementSourceNames;
        if (arrayList != null) {
          ArrayList<String> arrayList1;
          int i = arrayList.size();
          if (bool) {
            arrayList = backStackRecord.mSharedElementSourceNames;
            arrayList1 = backStackRecord.mSharedElementTargetNames;
          } else {
            arrayList1 = backStackRecord.mSharedElementSourceNames;
            arrayList = backStackRecord.mSharedElementTargetNames;
          } 
          for (byte b = 0; b < i; b++) {
            String str1 = arrayList1.get(b);
            String str3 = arrayList.get(b);
            String str2 = (String)arrayMap.remove(str3);
            if (str2 != null) {
              arrayMap.put(str1, str2);
            } else {
              arrayMap.put(str1, str3);
            } 
          } 
        } 
      } 
      paramInt3--;
    } 
    return arrayMap;
  }
  
  public static void calculatePopFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean) {
    if (!paramBackStackRecord.mManager.mContainer.onHasView())
      return; 
    for (int i = paramBackStackRecord.mOps.size() - 1; i >= 0; i--)
      addToFirstInLastOut(paramBackStackRecord, paramBackStackRecord.mOps.get(i), paramSparseArray, true, paramBoolean); 
  }
  
  static void callSharedElementStartEnd(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean1, ArrayMap<String, View> paramArrayMap, boolean paramBoolean2) {
    SharedElementCallback sharedElementCallback;
    if (paramBoolean1) {
      sharedElementCallback = paramFragment2.getEnterTransitionCallback();
    } else {
      sharedElementCallback = sharedElementCallback.getEnterTransitionCallback();
    } 
    if (sharedElementCallback != null) {
      int i;
      ArrayList<Object> arrayList2 = new ArrayList();
      ArrayList<Object> arrayList1 = new ArrayList();
      byte b = 0;
      if (paramArrayMap == null) {
        i = 0;
      } else {
        i = paramArrayMap.size();
      } 
      while (b < i) {
        arrayList1.add(paramArrayMap.keyAt(b));
        arrayList2.add(paramArrayMap.valueAt(b));
        b++;
      } 
      if (paramBoolean2) {
        sharedElementCallback.onSharedElementStart(arrayList1, arrayList2, null);
      } else {
        sharedElementCallback.onSharedElementEnd(arrayList1, arrayList2, null);
      } 
    } 
  }
  
  private static boolean canHandleAll(FragmentTransitionImpl paramFragmentTransitionImpl, List<Object> paramList) {
    int i = paramList.size();
    for (byte b = 0; b < i; b++) {
      if (!paramFragmentTransitionImpl.canHandle(paramList.get(b)))
        return false; 
    } 
    return true;
  }
  
  static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition) {
    ArrayList<String> arrayList;
    Fragment fragment = paramFragmentContainerTransition.lastIn;
    View view = fragment.getView();
    if (paramArrayMap.isEmpty() || paramObject == null || view == null) {
      paramArrayMap.clear();
      return null;
    } 
    ArrayMap<String, View> arrayMap = new ArrayMap();
    paramFragmentTransitionImpl.findNamedViews((Map<String, View>)arrayMap, view);
    BackStackRecord backStackRecord = paramFragmentContainerTransition.lastInTransaction;
    if (paramFragmentContainerTransition.lastInIsPop) {
      paramObject = fragment.getExitTransitionCallback();
      arrayList = backStackRecord.mSharedElementSourceNames;
    } else {
      paramObject = fragment.getEnterTransitionCallback();
      arrayList = ((BackStackRecord)arrayList).mSharedElementTargetNames;
    } 
    if (arrayList != null) {
      arrayMap.retainAll(arrayList);
      arrayMap.retainAll(paramArrayMap.values());
    } 
    if (paramObject != null) {
      paramObject.onMapSharedElements(arrayList, (Map)arrayMap);
      for (int i = arrayList.size() - 1; i >= 0; i--) {
        String str = arrayList.get(i);
        paramObject = arrayMap.get(str);
        if (paramObject == null) {
          paramObject = findKeyForValue(paramArrayMap, str);
          if (paramObject != null)
            paramArrayMap.remove(paramObject); 
        } else if (!str.equals(ViewCompat.getTransitionName((View)paramObject))) {
          str = findKeyForValue(paramArrayMap, str);
          if (str != null)
            paramArrayMap.put(str, ViewCompat.getTransitionName((View)paramObject)); 
        } 
      } 
    } else {
      retainValues(paramArrayMap, arrayMap);
    } 
    return arrayMap;
  }
  
  private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition) {
    ArrayList<String> arrayList;
    if (paramArrayMap.isEmpty() || paramObject == null) {
      paramArrayMap.clear();
      return null;
    } 
    paramObject = paramFragmentContainerTransition.firstOut;
    ArrayMap<String, View> arrayMap = new ArrayMap();
    paramFragmentTransitionImpl.findNamedViews((Map<String, View>)arrayMap, paramObject.getView());
    BackStackRecord backStackRecord = paramFragmentContainerTransition.firstOutTransaction;
    if (paramFragmentContainerTransition.firstOutIsPop) {
      paramObject = paramObject.getEnterTransitionCallback();
      arrayList = backStackRecord.mSharedElementTargetNames;
    } else {
      paramObject = paramObject.getExitTransitionCallback();
      arrayList = ((BackStackRecord)arrayList).mSharedElementSourceNames;
    } 
    arrayMap.retainAll(arrayList);
    if (paramObject != null) {
      paramObject.onMapSharedElements(arrayList, (Map)arrayMap);
      for (int i = arrayList.size() - 1; i >= 0; i--) {
        String str = arrayList.get(i);
        paramObject = arrayMap.get(str);
        if (paramObject == null) {
          paramArrayMap.remove(str);
        } else if (!str.equals(ViewCompat.getTransitionName((View)paramObject))) {
          str = (String)paramArrayMap.remove(str);
          paramArrayMap.put(ViewCompat.getTransitionName((View)paramObject), str);
        } 
      } 
    } else {
      paramArrayMap.retainAll(arrayMap.keySet());
    } 
    return arrayMap;
  }
  
  private static FragmentTransitionImpl chooseImpl(Fragment paramFragment1, Fragment paramFragment2) {
    ArrayList<Object> arrayList = new ArrayList();
    if (paramFragment1 != null) {
      Object object2 = paramFragment1.getExitTransition();
      if (object2 != null)
        arrayList.add(object2); 
      object2 = paramFragment1.getReturnTransition();
      if (object2 != null)
        arrayList.add(object2); 
      Object object1 = paramFragment1.getSharedElementReturnTransition();
      if (object1 != null)
        arrayList.add(object1); 
    } 
    if (paramFragment2 != null) {
      Object object = paramFragment2.getEnterTransition();
      if (object != null)
        arrayList.add(object); 
      object = paramFragment2.getReenterTransition();
      if (object != null)
        arrayList.add(object); 
      object = paramFragment2.getSharedElementEnterTransition();
      if (object != null)
        arrayList.add(object); 
    } 
    if (arrayList.isEmpty())
      return null; 
    FragmentTransitionImpl fragmentTransitionImpl = PLATFORM_IMPL;
    if (fragmentTransitionImpl != null && canHandleAll(fragmentTransitionImpl, arrayList))
      return PLATFORM_IMPL; 
    fragmentTransitionImpl = SUPPORT_IMPL;
    if (fragmentTransitionImpl != null && canHandleAll(fragmentTransitionImpl, arrayList))
      return SUPPORT_IMPL; 
    if (PLATFORM_IMPL == null && SUPPORT_IMPL == null)
      return null; 
    throw new IllegalArgumentException("Invalid Transition types");
  }
  
  static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList<View> paramArrayList, View paramView) {
    if (paramObject != null) {
      ArrayList<View> arrayList2 = new ArrayList();
      View view = paramFragment.getView();
      if (view != null)
        paramFragmentTransitionImpl.captureTransitioningViews(arrayList2, view); 
      if (paramArrayList != null)
        arrayList2.removeAll(paramArrayList); 
      ArrayList<View> arrayList1 = arrayList2;
      if (!arrayList2.isEmpty()) {
        arrayList2.add(paramView);
        paramFragmentTransitionImpl.addTargets(paramObject, arrayList2);
        arrayList1 = arrayList2;
      } 
    } else {
      paramFragment = null;
    } 
    return (ArrayList<View>)paramFragment;
  }
  
  private static Object configureSharedElementsOrdered(final FragmentTransitionImpl impl, ViewGroup paramViewGroup, final View nonExistentView, final ArrayMap<String, String> nameOverrides, final FragmentContainerTransition fragments, final ArrayList<View> sharedElementsOut, final ArrayList<View> sharedElementsIn, final Object enterTransition, final Object inEpicenter) {
    final Object finalSharedElementTransition;
    final Fragment inFragment = fragments.lastIn;
    final Fragment outFragment = fragments.firstOut;
    if (fragment1 == null || fragment2 == null)
      return null; 
    final boolean inIsPop = fragments.lastInIsPop;
    if (nameOverrides.isEmpty()) {
      object = null;
    } else {
      object = getSharedElementTransition(impl, fragment1, fragment2, bool);
    } 
    ArrayMap<String, View> arrayMap = captureOutSharedElements(impl, nameOverrides, object, fragments);
    if (nameOverrides.isEmpty()) {
      object = null;
    } else {
      sharedElementsOut.addAll(arrayMap.values());
    } 
    if (enterTransition == null && inEpicenter == null && object == null)
      return null; 
    callSharedElementStartEnd(fragment1, fragment2, bool, arrayMap, true);
    if (object != null) {
      Rect rect = new Rect();
      impl.setSharedElementTargets(object, nonExistentView, sharedElementsOut);
      setOutEpicenter(impl, object, inEpicenter, arrayMap, fragments.firstOutIsPop, fragments.firstOutTransaction);
      inEpicenter = rect;
      if (enterTransition != null) {
        impl.setEpicenter(enterTransition, rect);
        inEpicenter = rect;
      } 
    } else {
      inEpicenter = null;
    } 
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          final Object val$enterTransition;
          
          final Object val$finalSharedElementTransition;
          
          final FragmentTransition.FragmentContainerTransition val$fragments;
          
          final FragmentTransitionImpl val$impl;
          
          final Rect val$inEpicenter;
          
          final Fragment val$inFragment;
          
          final boolean val$inIsPop;
          
          final ArrayMap val$nameOverrides;
          
          final View val$nonExistentView;
          
          final Fragment val$outFragment;
          
          final ArrayList val$sharedElementsIn;
          
          final ArrayList val$sharedElementsOut;
          
          public void run() {
            ArrayMap<String, View> arrayMap = FragmentTransition.captureInSharedElements(impl, nameOverrides, finalSharedElementTransition, fragments);
            if (arrayMap != null) {
              sharedElementsIn.addAll(arrayMap.values());
              sharedElementsIn.add(nonExistentView);
            } 
            FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, arrayMap, false);
            Object object = finalSharedElementTransition;
            if (object != null) {
              impl.swapSharedElementTargets(object, sharedElementsOut, sharedElementsIn);
              object = FragmentTransition.getInEpicenterView(arrayMap, fragments, enterTransition, inIsPop);
              if (object != null)
                impl.getBoundsOnScreen((View)object, inEpicenter); 
            } 
          }
        });
    return object;
  }
  
  private static Object configureSharedElementsReordered(final FragmentTransitionImpl impl, ViewGroup paramViewGroup, final View epicenter, ArrayMap<String, String> paramArrayMap, final FragmentContainerTransition epicenterView, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2, Object paramObject1, Object paramObject2) {
    Object object1;
    Object object2;
    final Fragment inFragment = epicenterView.lastIn;
    final Fragment outFragment = epicenterView.firstOut;
    if (fragment1 != null)
      fragment1.getView().setVisibility(0); 
    if (fragment1 == null || fragment2 == null)
      return null; 
    final boolean inIsPop = epicenterView.lastInIsPop;
    if (paramArrayMap.isEmpty()) {
      object2 = null;
    } else {
      object2 = getSharedElementTransition(impl, fragment1, fragment2, bool);
    } 
    ArrayMap<String, View> arrayMap2 = captureOutSharedElements(impl, paramArrayMap, object2, epicenterView);
    final ArrayMap<String, View> inSharedElements = captureInSharedElements(impl, paramArrayMap, object2, epicenterView);
    if (paramArrayMap.isEmpty()) {
      if (arrayMap2 != null)
        arrayMap2.clear(); 
      if (arrayMap1 != null)
        arrayMap1.clear(); 
      paramArrayMap = null;
    } else {
      addSharedElementsWithMatchingNames(paramArrayList1, arrayMap2, paramArrayMap.keySet());
      addSharedElementsWithMatchingNames(paramArrayList2, arrayMap1, paramArrayMap.values());
      object1 = object2;
    } 
    if (paramObject1 == null && paramObject2 == null && object1 == null)
      return null; 
    callSharedElementStartEnd(fragment1, fragment2, bool, arrayMap2, true);
    if (object1 != null) {
      paramArrayList2.add(epicenter);
      impl.setSharedElementTargets(object1, epicenter, paramArrayList1);
      setOutEpicenter(impl, object1, paramObject2, arrayMap2, epicenterView.firstOutIsPop, epicenterView.firstOutTransaction);
      Rect rect = new Rect();
      View view = getInEpicenterView(arrayMap1, epicenterView, paramObject1, bool);
      if (view != null)
        impl.setEpicenter(paramObject1, rect); 
    } else {
      epicenterView = null;
      epicenter = null;
    } 
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          final Rect val$epicenter;
          
          final View val$epicenterView;
          
          final FragmentTransitionImpl val$impl;
          
          final Fragment val$inFragment;
          
          final boolean val$inIsPop;
          
          final ArrayMap val$inSharedElements;
          
          final Fragment val$outFragment;
          
          public void run() {
            FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, inSharedElements, false);
            View view = epicenterView;
            if (view != null)
              impl.getBoundsOnScreen(view, epicenter); 
          }
        });
    return object1;
  }
  
  private static void configureTransitionsOrdered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap) {
    if (paramFragmentManagerImpl.mContainer.onHasView()) {
      ViewGroup viewGroup = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    } 
    if (paramFragmentManagerImpl == null)
      return; 
    Fragment fragment1 = paramFragmentContainerTransition.lastIn;
    Fragment fragment2 = paramFragmentContainerTransition.firstOut;
    FragmentTransitionImpl fragmentTransitionImpl = chooseImpl(fragment2, fragment1);
    if (fragmentTransitionImpl == null)
      return; 
    boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
    boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
    Object object3 = getEnterTransition(fragmentTransitionImpl, fragment1, bool1);
    Object object2 = getExitTransition(fragmentTransitionImpl, fragment2, bool2);
    ArrayList<View> arrayList3 = new ArrayList();
    ArrayList<View> arrayList1 = new ArrayList();
    Object object4 = configureSharedElementsOrdered(fragmentTransitionImpl, (ViewGroup)paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, arrayList3, arrayList1, object3, object2);
    if (object3 == null && object4 == null && object2 == null)
      return; 
    ArrayList<View> arrayList2 = configureEnteringExitingViews(fragmentTransitionImpl, object2, fragment2, arrayList3, paramView);
    if (arrayList2 == null || arrayList2.isEmpty())
      object2 = null; 
    fragmentTransitionImpl.addTarget(object3, paramView);
    Object object1 = mergeTransitions(fragmentTransitionImpl, object3, object2, object4, fragment1, paramFragmentContainerTransition.lastInIsPop);
    if (object1 != null) {
      arrayList3 = new ArrayList<View>();
      fragmentTransitionImpl.scheduleRemoveTargets(object1, object3, arrayList3, object2, arrayList2, object4, arrayList1);
      scheduleTargetChange(fragmentTransitionImpl, (ViewGroup)paramFragmentManagerImpl, fragment1, paramView, arrayList1, object3, arrayList3, object2, arrayList2);
      fragmentTransitionImpl.setNameOverridesOrdered((View)paramFragmentManagerImpl, arrayList1, (Map<String, String>)paramArrayMap);
      fragmentTransitionImpl.beginDelayedTransition((ViewGroup)paramFragmentManagerImpl, object1);
      fragmentTransitionImpl.scheduleNameReset((ViewGroup)paramFragmentManagerImpl, arrayList1, (Map<String, String>)paramArrayMap);
    } 
  }
  
  private static void configureTransitionsReordered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap) {
    if (paramFragmentManagerImpl.mContainer.onHasView()) {
      ViewGroup viewGroup = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    } 
    if (paramFragmentManagerImpl == null)
      return; 
    Fragment fragment2 = paramFragmentContainerTransition.lastIn;
    Fragment fragment1 = paramFragmentContainerTransition.firstOut;
    FragmentTransitionImpl fragmentTransitionImpl = chooseImpl(fragment1, fragment2);
    if (fragmentTransitionImpl == null)
      return; 
    boolean bool2 = paramFragmentContainerTransition.lastInIsPop;
    boolean bool1 = paramFragmentContainerTransition.firstOutIsPop;
    ArrayList<View> arrayList3 = new ArrayList();
    ArrayList<View> arrayList2 = new ArrayList();
    Object object2 = getEnterTransition(fragmentTransitionImpl, fragment2, bool2);
    Object object3 = getExitTransition(fragmentTransitionImpl, fragment1, bool1);
    Object object1 = configureSharedElementsReordered(fragmentTransitionImpl, (ViewGroup)paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, arrayList2, arrayList3, object2, object3);
    if (object2 == null && object1 == null && object3 == null)
      return; 
    ArrayList<View> arrayList4 = configureEnteringExitingViews(fragmentTransitionImpl, object3, fragment1, arrayList2, paramView);
    ArrayList<View> arrayList1 = configureEnteringExitingViews(fragmentTransitionImpl, object2, fragment2, arrayList3, paramView);
    setViewVisibility(arrayList1, 4);
    Object object4 = mergeTransitions(fragmentTransitionImpl, object2, object3, object1, fragment2, bool2);
    if (object4 != null) {
      replaceHide(fragmentTransitionImpl, object3, fragment1, arrayList4);
      ArrayList<String> arrayList = fragmentTransitionImpl.prepareSetNameOverridesReordered(arrayList3);
      fragmentTransitionImpl.scheduleRemoveTargets(object4, object2, arrayList1, object3, arrayList4, object1, arrayList3);
      fragmentTransitionImpl.beginDelayedTransition((ViewGroup)paramFragmentManagerImpl, object4);
      fragmentTransitionImpl.setNameOverridesReordered((View)paramFragmentManagerImpl, arrayList2, arrayList3, arrayList, (Map<String, String>)paramArrayMap);
      setViewVisibility(arrayList1, 0);
      fragmentTransitionImpl.swapSharedElementTargets(object1, arrayList2, arrayList3);
    } 
  }
  
  private static FragmentContainerTransition ensureContainer(FragmentContainerTransition paramFragmentContainerTransition, SparseArray<FragmentContainerTransition> paramSparseArray, int paramInt) {
    FragmentContainerTransition fragmentContainerTransition = paramFragmentContainerTransition;
    if (paramFragmentContainerTransition == null) {
      fragmentContainerTransition = new FragmentContainerTransition();
      paramSparseArray.put(paramInt, fragmentContainerTransition);
    } 
    return fragmentContainerTransition;
  }
  
  private static String findKeyForValue(ArrayMap<String, String> paramArrayMap, String paramString) {
    int i = paramArrayMap.size();
    for (byte b = 0; b < i; b++) {
      if (paramString.equals(paramArrayMap.valueAt(b)))
        return (String)paramArrayMap.keyAt(b); 
    } 
    return null;
  }
  
  private static Object getEnterTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean) {
    Object object;
    if (paramFragment == null)
      return null; 
    if (paramBoolean) {
      object = paramFragment.getReenterTransition();
    } else {
      object = object.getEnterTransition();
    } 
    return paramFragmentTransitionImpl.cloneTransition(object);
  }
  
  private static Object getExitTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean) {
    Object object;
    if (paramFragment == null)
      return null; 
    if (paramBoolean) {
      object = paramFragment.getReturnTransition();
    } else {
      object = object.getExitTransition();
    } 
    return paramFragmentTransitionImpl.cloneTransition(object);
  }
  
  static View getInEpicenterView(ArrayMap<String, View> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, Object<String> paramObject, boolean paramBoolean) {
    BackStackRecord backStackRecord = paramFragmentContainerTransition.lastInTransaction;
    if (paramObject != null && paramArrayMap != null) {
      paramObject = (Object<String>)backStackRecord.mSharedElementSourceNames;
      if (paramObject != null && !paramObject.isEmpty()) {
        String str;
        if (paramBoolean) {
          str = backStackRecord.mSharedElementSourceNames.get(0);
        } else {
          str = ((BackStackRecord)str).mSharedElementTargetNames.get(0);
        } 
        return (View)paramArrayMap.get(str);
      } 
    } 
    return null;
  }
  
  private static Object getSharedElementTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean) {
    Object object;
    if (paramFragment1 == null || paramFragment2 == null)
      return null; 
    if (paramBoolean) {
      object = paramFragment2.getSharedElementReturnTransition();
    } else {
      object = object.getSharedElementEnterTransition();
    } 
    return paramFragmentTransitionImpl.wrapTransitionInSet(paramFragmentTransitionImpl.cloneTransition(object));
  }
  
  private static Object mergeTransitions(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, Object paramObject3, Fragment paramFragment, boolean paramBoolean) {
    Object object;
    if (paramObject1 != null && paramObject2 != null && paramFragment != null) {
      if (paramBoolean) {
        paramBoolean = paramFragment.getAllowReturnTransitionOverlap();
      } else {
        paramBoolean = paramFragment.getAllowEnterTransitionOverlap();
      } 
    } else {
      paramBoolean = true;
    } 
    if (paramBoolean) {
      object = paramFragmentTransitionImpl.mergeTransitionsTogether(paramObject2, paramObject1, paramObject3);
    } else {
      object = object.mergeTransitionsInSequence(paramObject2, paramObject1, paramObject3);
    } 
    return object;
  }
  
  private static void replaceHide(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, final ArrayList<View> exitingViews) {
    if (paramFragment != null && paramObject != null && paramFragment.mAdded && paramFragment.mHidden && paramFragment.mHiddenChanged) {
      paramFragment.setHideReplaced(true);
      paramFragmentTransitionImpl.scheduleHideFragmentView(paramObject, paramFragment.getView(), exitingViews);
      OneShotPreDrawListener.add((View)paramFragment.mContainer, new Runnable() {
            final ArrayList val$exitingViews;
            
            public void run() {
              FragmentTransition.setViewVisibility(exitingViews, 4);
            }
          });
    } 
  }
  
  private static FragmentTransitionImpl resolveSupportImpl() {
    try {
      return Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private static void retainValues(ArrayMap<String, String> paramArrayMap, ArrayMap<String, View> paramArrayMap1) {
    for (int i = paramArrayMap.size() - 1; i >= 0; i--) {
      if (!paramArrayMap1.containsKey(paramArrayMap.valueAt(i)))
        paramArrayMap.removeAt(i); 
    } 
  }
  
  private static void scheduleTargetChange(final FragmentTransitionImpl impl, ViewGroup paramViewGroup, final Fragment inFragment, final View nonExistentView, final ArrayList<View> sharedElementsIn, final Object enterTransition, final ArrayList<View> enteringViews, final Object exitTransition, final ArrayList<View> exitingViews) {
    OneShotPreDrawListener.add((View)paramViewGroup, new Runnable() {
          final Object val$enterTransition;
          
          final ArrayList val$enteringViews;
          
          final Object val$exitTransition;
          
          final ArrayList val$exitingViews;
          
          final FragmentTransitionImpl val$impl;
          
          final Fragment val$inFragment;
          
          final View val$nonExistentView;
          
          final ArrayList val$sharedElementsIn;
          
          public void run() {
            Object<View> object = (Object<View>)enterTransition;
            if (object != null) {
              impl.removeTarget(object, nonExistentView);
              object = (Object<View>)FragmentTransition.configureEnteringExitingViews(impl, enterTransition, inFragment, sharedElementsIn, nonExistentView);
              enteringViews.addAll((Collection<? extends View>)object);
            } 
            if (exitingViews != null) {
              if (exitTransition != null) {
                object = (Object<View>)new ArrayList();
                object.add(nonExistentView);
                impl.replaceTargets(exitTransition, exitingViews, (ArrayList<View>)object);
              } 
              exitingViews.clear();
              exitingViews.add(nonExistentView);
            } 
          }
        });
  }
  
  private static void setOutEpicenter(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, ArrayMap<String, View> paramArrayMap, boolean paramBoolean, BackStackRecord paramBackStackRecord) {
    ArrayList<String> arrayList = paramBackStackRecord.mSharedElementSourceNames;
    if (arrayList != null && !arrayList.isEmpty()) {
      String str;
      if (paramBoolean) {
        str = paramBackStackRecord.mSharedElementTargetNames.get(0);
      } else {
        str = ((BackStackRecord)str).mSharedElementSourceNames.get(0);
      } 
      View view = (View)paramArrayMap.get(str);
      paramFragmentTransitionImpl.setEpicenter(paramObject1, view);
      if (paramObject2 != null)
        paramFragmentTransitionImpl.setEpicenter(paramObject2, view); 
    } 
  }
  
  static void setViewVisibility(ArrayList<View> paramArrayList, int paramInt) {
    if (paramArrayList == null)
      return; 
    for (int i = paramArrayList.size() - 1; i >= 0; i--)
      ((View)paramArrayList.get(i)).setVisibility(paramInt); 
  }
  
  static void startTransitions(FragmentManagerImpl paramFragmentManagerImpl, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramFragmentManagerImpl.mCurState < 1)
      return; 
    SparseArray<FragmentContainerTransition> sparseArray = new SparseArray();
    int i;
    for (i = paramInt1; i < paramInt2; i++) {
      BackStackRecord backStackRecord = paramArrayList.get(i);
      if (((Boolean)paramArrayList1.get(i)).booleanValue()) {
        calculatePopFragments(backStackRecord, sparseArray, paramBoolean);
      } else {
        calculateFragments(backStackRecord, sparseArray, paramBoolean);
      } 
    } 
    if (sparseArray.size() != 0) {
      View view = new View(paramFragmentManagerImpl.mHost.getContext());
      int j = sparseArray.size();
      for (i = 0; i < j; i++) {
        int k = sparseArray.keyAt(i);
        ArrayMap<String, String> arrayMap = calculateNameOverrides(k, paramArrayList, paramArrayList1, paramInt1, paramInt2);
        FragmentContainerTransition fragmentContainerTransition = (FragmentContainerTransition)sparseArray.valueAt(i);
        if (paramBoolean) {
          configureTransitionsReordered(paramFragmentManagerImpl, k, fragmentContainerTransition, view, arrayMap);
        } else {
          configureTransitionsOrdered(paramFragmentManagerImpl, k, fragmentContainerTransition, view, arrayMap);
        } 
      } 
    } 
  }
  
  static {
    FragmentTransitionImpl fragmentTransitionImpl;
  }
  
  static {
    if (Build.VERSION.SDK_INT >= 21) {
      fragmentTransitionImpl = new FragmentTransitionCompat21();
    } else {
      fragmentTransitionImpl = null;
    } 
    PLATFORM_IMPL = fragmentTransitionImpl;
  }
  
  static class FragmentContainerTransition {
    public Fragment firstOut;
    
    public boolean firstOutIsPop;
    
    public BackStackRecord firstOutTransaction;
    
    public Fragment lastIn;
    
    public boolean lastInIsPop;
    
    public BackStackRecord lastInTransaction;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/fragment/app/FragmentTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */