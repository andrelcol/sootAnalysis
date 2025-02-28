package androidx.fragment.app;

import android.animation.Animator;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.util.DebugUtils;
import androidx.core.view.LayoutInflaterCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner {
  static final Object USE_DEFAULT_TRANSITION;
  
  private static final SimpleArrayMap<String, Class<?>> sClassMap = new SimpleArrayMap();
  
  boolean mAdded;
  
  AnimationInfo mAnimationInfo;
  
  Bundle mArguments;
  
  int mBackStackNesting;
  
  boolean mCalled;
  
  FragmentManagerImpl mChildFragmentManager;
  
  FragmentManagerNonConfig mChildNonConfig;
  
  ViewGroup mContainer;
  
  int mContainerId;
  
  boolean mDeferStart;
  
  boolean mDetached;
  
  int mFragmentId;
  
  FragmentManagerImpl mFragmentManager;
  
  boolean mFromLayout;
  
  boolean mHasMenu;
  
  boolean mHidden;
  
  boolean mHiddenChanged;
  
  FragmentHostCallback mHost;
  
  boolean mInLayout;
  
  int mIndex = -1;
  
  View mInnerView;
  
  boolean mIsCreated;
  
  boolean mIsNewlyAdded;
  
  LayoutInflater mLayoutInflater;
  
  LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
  
  boolean mMenuVisible = true;
  
  Fragment mParentFragment;
  
  boolean mPerformedCreateView;
  
  float mPostponedAlpha;
  
  boolean mRemoving;
  
  boolean mRestored;
  
  boolean mRetainInstance;
  
  boolean mRetaining;
  
  Bundle mSavedFragmentState;
  
  Boolean mSavedUserVisibleHint;
  
  SparseArray<Parcelable> mSavedViewState;
  
  int mState = 0;
  
  String mTag;
  
  Fragment mTarget;
  
  int mTargetIndex = -1;
  
  int mTargetRequestCode;
  
  boolean mUserVisibleHint = true;
  
  View mView;
  
  LifecycleOwner mViewLifecycleOwner;
  
  MutableLiveData<LifecycleOwner> mViewLifecycleOwnerLiveData = new MutableLiveData();
  
  LifecycleRegistry mViewLifecycleRegistry;
  
  ViewModelStore mViewModelStore;
  
  String mWho;
  
  static {
    USE_DEFAULT_TRANSITION = new Object();
  }
  
  private AnimationInfo ensureAnimationInfo() {
    if (this.mAnimationInfo == null)
      this.mAnimationInfo = new AnimationInfo(); 
    return this.mAnimationInfo;
  }
  
  public static Fragment instantiate(Context paramContext, String paramString, Bundle paramBundle) {
    try {
      Class<?> clazz2 = (Class)sClassMap.get(paramString);
      Class<?> clazz1 = clazz2;
      if (clazz2 == null) {
        clazz1 = paramContext.getClassLoader().loadClass(paramString);
        sClassMap.put(paramString, clazz1);
      } 
      Fragment fragment = clazz1.getConstructor(new Class[0]).newInstance(new Object[0]);
      if (paramBundle != null) {
        paramBundle.setClassLoader(fragment.getClass().getClassLoader());
        fragment.setArguments(paramBundle);
      } 
      return fragment;
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": make sure class name exists, is public, and has an");
      stringBuilder.append(" empty constructor that is public");
      throw new InstantiationException(stringBuilder.toString(), classNotFoundException);
    } catch (InstantiationException instantiationException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": make sure class name exists, is public, and has an");
      stringBuilder.append(" empty constructor that is public");
      throw new InstantiationException(stringBuilder.toString(), instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": make sure class name exists, is public, and has an");
      stringBuilder.append(" empty constructor that is public");
      throw new InstantiationException(stringBuilder.toString(), illegalAccessException);
    } catch (NoSuchMethodException noSuchMethodException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": could not find Fragment constructor");
      throw new InstantiationException(stringBuilder.toString(), noSuchMethodException);
    } catch (InvocationTargetException invocationTargetException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": calling Fragment constructor caused an exception");
      throw new InstantiationException(stringBuilder.toString(), invocationTargetException);
    } 
  }
  
  static boolean isSupportFragmentClass(Context paramContext, String paramString) {
    try {
      Class<?> clazz2 = (Class)sClassMap.get(paramString);
      Class<?> clazz1 = clazz2;
      if (clazz2 == null) {
        clazz1 = paramContext.getClassLoader().loadClass(paramString);
        sClassMap.put(paramString, clazz1);
      } 
      return Fragment.class.isAssignableFrom(clazz1);
    } catch (ClassNotFoundException classNotFoundException) {
      return false;
    } 
  }
  
  void callStartTransitionListener() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    OnStartEnterTransitionListener onStartEnterTransitionListener = null;
    if (animationInfo != null) {
      animationInfo.mEnterTransitionPostponed = false;
      onStartEnterTransitionListener = animationInfo.mStartEnterTransitionListener;
      animationInfo.mStartEnterTransitionListener = null;
    } 
    if (onStartEnterTransitionListener != null)
      onStartEnterTransitionListener.onStartEnterTransition(); 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mFragmentId=#");
    paramPrintWriter.print(Integer.toHexString(this.mFragmentId));
    paramPrintWriter.print(" mContainerId=#");
    paramPrintWriter.print(Integer.toHexString(this.mContainerId));
    paramPrintWriter.print(" mTag=");
    paramPrintWriter.println(this.mTag);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mState=");
    paramPrintWriter.print(this.mState);
    paramPrintWriter.print(" mIndex=");
    paramPrintWriter.print(this.mIndex);
    paramPrintWriter.print(" mWho=");
    paramPrintWriter.print(this.mWho);
    paramPrintWriter.print(" mBackStackNesting=");
    paramPrintWriter.println(this.mBackStackNesting);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mAdded=");
    paramPrintWriter.print(this.mAdded);
    paramPrintWriter.print(" mRemoving=");
    paramPrintWriter.print(this.mRemoving);
    paramPrintWriter.print(" mFromLayout=");
    paramPrintWriter.print(this.mFromLayout);
    paramPrintWriter.print(" mInLayout=");
    paramPrintWriter.println(this.mInLayout);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mHidden=");
    paramPrintWriter.print(this.mHidden);
    paramPrintWriter.print(" mDetached=");
    paramPrintWriter.print(this.mDetached);
    paramPrintWriter.print(" mMenuVisible=");
    paramPrintWriter.print(this.mMenuVisible);
    paramPrintWriter.print(" mHasMenu=");
    paramPrintWriter.println(this.mHasMenu);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mRetainInstance=");
    paramPrintWriter.print(this.mRetainInstance);
    paramPrintWriter.print(" mRetaining=");
    paramPrintWriter.print(this.mRetaining);
    paramPrintWriter.print(" mUserVisibleHint=");
    paramPrintWriter.println(this.mUserVisibleHint);
    if (this.mFragmentManager != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mFragmentManager=");
      paramPrintWriter.println(this.mFragmentManager);
    } 
    if (this.mHost != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mHost=");
      paramPrintWriter.println(this.mHost);
    } 
    if (this.mParentFragment != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mParentFragment=");
      paramPrintWriter.println(this.mParentFragment);
    } 
    if (this.mArguments != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mArguments=");
      paramPrintWriter.println(this.mArguments);
    } 
    if (this.mSavedFragmentState != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedFragmentState=");
      paramPrintWriter.println(this.mSavedFragmentState);
    } 
    if (this.mSavedViewState != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedViewState=");
      paramPrintWriter.println(this.mSavedViewState);
    } 
    if (this.mTarget != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mTarget=");
      paramPrintWriter.print(this.mTarget);
      paramPrintWriter.print(" mTargetRequestCode=");
      paramPrintWriter.println(this.mTargetRequestCode);
    } 
    if (getNextAnim() != 0) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mNextAnim=");
      paramPrintWriter.println(getNextAnim());
    } 
    if (this.mContainer != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mContainer=");
      paramPrintWriter.println(this.mContainer);
    } 
    if (this.mView != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mView=");
      paramPrintWriter.println(this.mView);
    } 
    if (this.mInnerView != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mInnerView=");
      paramPrintWriter.println(this.mView);
    } 
    if (getAnimatingAway() != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mAnimatingAway=");
      paramPrintWriter.println(getAnimatingAway());
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStateAfterAnimating=");
      paramPrintWriter.println(getStateAfterAnimating());
    } 
    if (getContext() != null)
      LoaderManager.getInstance(this).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString); 
    if (this.mChildFragmentManager != null) {
      paramPrintWriter.print(paramString);
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Child ");
      stringBuilder1.append(this.mChildFragmentManager);
      stringBuilder1.append(":");
      paramPrintWriter.println(stringBuilder1.toString());
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append(paramString);
      stringBuilder2.append("  ");
      fragmentManagerImpl.dump(stringBuilder2.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    return super.equals(paramObject);
  }
  
  Fragment findFragmentByWho(String paramString) {
    if (paramString.equals(this.mWho))
      return this; 
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    return (fragmentManagerImpl != null) ? fragmentManagerImpl.findFragmentByWho(paramString) : null;
  }
  
  public final FragmentActivity getActivity() {
    FragmentActivity fragmentActivity;
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback == null) {
      fragmentHostCallback = null;
    } else {
      fragmentActivity = (FragmentActivity)fragmentHostCallback.getActivity();
    } 
    return fragmentActivity;
  }
  
  public boolean getAllowEnterTransitionOverlap() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo != null) {
      Boolean bool = animationInfo.mAllowEnterTransitionOverlap;
      return (bool == null) ? true : bool.booleanValue();
    } 
    return true;
  }
  
  public boolean getAllowReturnTransitionOverlap() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo != null) {
      Boolean bool = animationInfo.mAllowReturnTransitionOverlap;
      return (bool == null) ? true : bool.booleanValue();
    } 
    return true;
  }
  
  View getAnimatingAway() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mAnimatingAway;
  }
  
  Animator getAnimator() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mAnimator;
  }
  
  public final Bundle getArguments() {
    return this.mArguments;
  }
  
  public final FragmentManager getChildFragmentManager() {
    if (this.mChildFragmentManager == null) {
      instantiateChildFragmentManager();
      int i = this.mState;
      if (i >= 4) {
        this.mChildFragmentManager.dispatchResume();
      } else if (i >= 3) {
        this.mChildFragmentManager.dispatchStart();
      } else if (i >= 2) {
        this.mChildFragmentManager.dispatchActivityCreated();
      } else if (i >= 1) {
        this.mChildFragmentManager.dispatchCreate();
      } 
    } 
    return this.mChildFragmentManager;
  }
  
  public Context getContext() {
    Context context;
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback == null) {
      fragmentHostCallback = null;
    } else {
      context = fragmentHostCallback.getContext();
    } 
    return context;
  }
  
  public Object getEnterTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mEnterTransition;
  }
  
  SharedElementCallback getEnterTransitionCallback() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mEnterTransitionCallback;
  }
  
  public Object getExitTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mExitTransition;
  }
  
  SharedElementCallback getExitTransitionCallback() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mExitTransitionCallback;
  }
  
  public final FragmentManager getFragmentManager() {
    return this.mFragmentManager;
  }
  
  @Deprecated
  public LayoutInflater getLayoutInflater(Bundle paramBundle) {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      LayoutInflater layoutInflater = fragmentHostCallback.onGetLayoutInflater();
      getChildFragmentManager();
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      fragmentManagerImpl.getLayoutInflaterFactory();
      LayoutInflaterCompat.setFactory2(layoutInflater, fragmentManagerImpl);
      return layoutInflater;
    } 
    throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
  }
  
  public Lifecycle getLifecycle() {
    return (Lifecycle)this.mLifecycleRegistry;
  }
  
  int getNextAnim() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? 0 : animationInfo.mNextAnim;
  }
  
  int getNextTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? 0 : animationInfo.mNextTransition;
  }
  
  int getNextTransitionStyle() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? 0 : animationInfo.mNextTransitionStyle;
  }
  
  public Object getReenterTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo == null)
      return null; 
    Object object2 = animationInfo.mReenterTransition;
    Object object1 = object2;
    if (object2 == USE_DEFAULT_TRANSITION)
      object1 = getExitTransition(); 
    return object1;
  }
  
  public final Resources getResources() {
    return requireContext().getResources();
  }
  
  public final boolean getRetainInstance() {
    return this.mRetainInstance;
  }
  
  public Object getReturnTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo == null)
      return null; 
    Object object2 = animationInfo.mReturnTransition;
    Object object1 = object2;
    if (object2 == USE_DEFAULT_TRANSITION)
      object1 = getEnterTransition(); 
    return object1;
  }
  
  public Object getSharedElementEnterTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mSharedElementEnterTransition;
  }
  
  public Object getSharedElementReturnTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo == null)
      return null; 
    Object object2 = animationInfo.mSharedElementReturnTransition;
    Object object1 = object2;
    if (object2 == USE_DEFAULT_TRANSITION)
      object1 = getSharedElementEnterTransition(); 
    return object1;
  }
  
  int getStateAfterAnimating() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? 0 : animationInfo.mStateAfterAnimating;
  }
  
  public View getView() {
    return this.mView;
  }
  
  public ViewModelStore getViewModelStore() {
    if (getContext() != null) {
      if (this.mViewModelStore == null)
        this.mViewModelStore = new ViewModelStore(); 
      return this.mViewModelStore;
    } 
    throw new IllegalStateException("Can't access ViewModels from detached fragment");
  }
  
  public final int hashCode() {
    return super.hashCode();
  }
  
  void initState() {
    this.mIndex = -1;
    this.mWho = null;
    this.mAdded = false;
    this.mRemoving = false;
    this.mFromLayout = false;
    this.mInLayout = false;
    this.mRestored = false;
    this.mBackStackNesting = 0;
    this.mFragmentManager = null;
    this.mChildFragmentManager = null;
    this.mHost = null;
    this.mFragmentId = 0;
    this.mContainerId = 0;
    this.mTag = null;
    this.mHidden = false;
    this.mDetached = false;
    this.mRetaining = false;
  }
  
  void instantiateChildFragmentManager() {
    if (this.mHost != null) {
      this.mChildFragmentManager = new FragmentManagerImpl();
      this.mChildFragmentManager.attachController(this.mHost, new FragmentContainer() {
            final Fragment this$0;
            
            public Fragment instantiate(Context param1Context, String param1String, Bundle param1Bundle) {
              return Fragment.this.mHost.instantiate(param1Context, param1String, param1Bundle);
            }
            
            public View onFindViewById(int param1Int) {
              View view = Fragment.this.mView;
              if (view != null)
                return view.findViewById(param1Int); 
              throw new IllegalStateException("Fragment does not have a view");
            }
            
            public boolean onHasView() {
              boolean bool;
              if (Fragment.this.mView != null) {
                bool = true;
              } else {
                bool = false;
              } 
              return bool;
            }
          }this);
      return;
    } 
    throw new IllegalStateException("Fragment has not been attached yet.");
  }
  
  public final boolean isAdded() {
    boolean bool;
    if (this.mHost != null && this.mAdded) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isHidden() {
    return this.mHidden;
  }
  
  boolean isHideReplaced() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? false : animationInfo.mIsHideReplaced;
  }
  
  final boolean isInBackStack() {
    boolean bool;
    if (this.mBackStackNesting > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  boolean isPostponed() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? false : animationInfo.mEnterTransitionPostponed;
  }
  
  public final boolean isStateSaved() {
    FragmentManagerImpl fragmentManagerImpl = this.mFragmentManager;
    return (fragmentManagerImpl == null) ? false : fragmentManagerImpl.isStateSaved();
  }
  
  void noteStateNotSaved() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.noteStateNotSaved(); 
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    this.mCalled = true;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  @Deprecated
  public void onAttach(Activity paramActivity) {
    this.mCalled = true;
  }
  
  public void onAttach(Context paramContext) {
    Activity activity;
    this.mCalled = true;
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback == null) {
      fragmentHostCallback = null;
    } else {
      activity = fragmentHostCallback.getActivity();
    } 
    if (activity != null) {
      this.mCalled = false;
      onAttach(activity);
    } 
  }
  
  public void onAttachFragment(Fragment paramFragment) {}
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    this.mCalled = true;
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem) {
    return false;
  }
  
  public void onCreate(Bundle paramBundle) {
    this.mCalled = true;
    restoreChildFragmentState(paramBundle);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null && !fragmentManagerImpl.isStateAtLeast(1))
      this.mChildFragmentManager.dispatchCreate(); 
  }
  
  public Animation onCreateAnimation(int paramInt1, boolean paramBoolean, int paramInt2) {
    return null;
  }
  
  public Animator onCreateAnimator(int paramInt1, boolean paramBoolean, int paramInt2) {
    return null;
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {
    getActivity().onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    return null;
  }
  
  public void onDestroy() {
    boolean bool = true;
    this.mCalled = true;
    FragmentActivity fragmentActivity = getActivity();
    if (fragmentActivity == null || !fragmentActivity.isChangingConfigurations())
      bool = false; 
    ViewModelStore viewModelStore = this.mViewModelStore;
    if (viewModelStore != null && !bool)
      viewModelStore.clear(); 
  }
  
  public void onDestroyOptionsMenu() {}
  
  public void onDestroyView() {
    this.mCalled = true;
  }
  
  public void onDetach() {
    this.mCalled = true;
  }
  
  public LayoutInflater onGetLayoutInflater(Bundle paramBundle) {
    return getLayoutInflater(paramBundle);
  }
  
  public void onHiddenChanged(boolean paramBoolean) {}
  
  @Deprecated
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle) {
    this.mCalled = true;
  }
  
  public void onInflate(Context paramContext, AttributeSet paramAttributeSet, Bundle paramBundle) {
    Activity activity;
    this.mCalled = true;
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback == null) {
      fragmentHostCallback = null;
    } else {
      activity = fragmentHostCallback.getActivity();
    } 
    if (activity != null) {
      this.mCalled = false;
      onInflate(activity, paramAttributeSet, paramBundle);
    } 
  }
  
  public void onLowMemory() {
    this.mCalled = true;
  }
  
  public void onMultiWindowModeChanged(boolean paramBoolean) {}
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    return false;
  }
  
  public void onOptionsMenuClosed(Menu paramMenu) {}
  
  public void onPause() {
    this.mCalled = true;
  }
  
  public void onPictureInPictureModeChanged(boolean paramBoolean) {}
  
  public void onPrepareOptionsMenu(Menu paramMenu) {}
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {}
  
  public void onResume() {
    this.mCalled = true;
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  public void onStart() {
    this.mCalled = true;
  }
  
  public void onStop() {
    this.mCalled = true;
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle) {}
  
  public void onViewStateRestored(Bundle paramBundle) {
    this.mCalled = true;
  }
  
  FragmentManager peekChildFragmentManager() {
    return this.mChildFragmentManager;
  }
  
  void performActivityCreated(Bundle paramBundle) {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.noteStateNotSaved(); 
    this.mState = 2;
    this.mCalled = false;
    onActivityCreated(paramBundle);
    if (this.mCalled) {
      FragmentManagerImpl fragmentManagerImpl1 = this.mChildFragmentManager;
      if (fragmentManagerImpl1 != null)
        fragmentManagerImpl1.dispatchActivityCreated(); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onActivityCreated()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performConfigurationChanged(Configuration paramConfiguration) {
    onConfigurationChanged(paramConfiguration);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchConfigurationChanged(paramConfiguration); 
  }
  
  boolean performContextItemSelected(MenuItem paramMenuItem) {
    if (!this.mHidden) {
      if (onContextItemSelected(paramMenuItem))
        return true; 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null && fragmentManagerImpl.dispatchContextItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  void performCreate(Bundle paramBundle) {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.noteStateNotSaved(); 
    this.mState = 1;
    this.mCalled = false;
    onCreate(paramBundle);
    this.mIsCreated = true;
    if (this.mCalled) {
      this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onCreate()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  boolean performCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
    boolean bool2 = this.mHidden;
    boolean bool1 = false;
    boolean bool = false;
    if (!bool2) {
      bool2 = bool;
      if (this.mHasMenu) {
        bool2 = bool;
        if (this.mMenuVisible) {
          onCreateOptionsMenu(paramMenu, paramMenuInflater);
          bool2 = true;
        } 
      } 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      bool1 = bool2;
      if (fragmentManagerImpl != null)
        bool1 = bool2 | fragmentManagerImpl.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater); 
    } 
    return bool1;
  }
  
  void performCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.noteStateNotSaved(); 
    this.mPerformedCreateView = true;
    this.mViewLifecycleOwner = new LifecycleOwner() {
        final Fragment this$0;
        
        public Lifecycle getLifecycle() {
          Fragment fragment = Fragment.this;
          if (fragment.mViewLifecycleRegistry == null)
            fragment.mViewLifecycleRegistry = new LifecycleRegistry(fragment.mViewLifecycleOwner); 
          return (Lifecycle)Fragment.this.mViewLifecycleRegistry;
        }
      };
    this.mViewLifecycleRegistry = null;
    this.mView = onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (this.mView != null) {
      this.mViewLifecycleOwner.getLifecycle();
      this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
    } else {
      if (this.mViewLifecycleRegistry == null) {
        this.mViewLifecycleOwner = null;
        return;
      } 
      throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
    } 
  }
  
  void performDestroy() {
    this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchDestroy(); 
    this.mState = 0;
    this.mCalled = false;
    this.mIsCreated = false;
    onDestroy();
    if (this.mCalled) {
      this.mChildFragmentManager = null;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onDestroy()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performDestroyView() {
    if (this.mView != null)
      this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY); 
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchDestroyView(); 
    this.mState = 1;
    this.mCalled = false;
    onDestroyView();
    if (this.mCalled) {
      LoaderManager.getInstance(this).markForRedelivery();
      this.mPerformedCreateView = false;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onDestroyView()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performDetach() {
    this.mCalled = false;
    onDetach();
    this.mLayoutInflater = null;
    if (this.mCalled) {
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null)
        if (this.mRetaining) {
          fragmentManagerImpl.dispatchDestroy();
          this.mChildFragmentManager = null;
        } else {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Child FragmentManager of ");
          stringBuilder1.append(this);
          stringBuilder1.append(" was not ");
          stringBuilder1.append(" destroyed and this fragment is not retaining instance");
          throw new IllegalStateException(stringBuilder1.toString());
        }  
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onDetach()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  LayoutInflater performGetLayoutInflater(Bundle paramBundle) {
    this.mLayoutInflater = onGetLayoutInflater(paramBundle);
    return this.mLayoutInflater;
  }
  
  void performLowMemory() {
    onLowMemory();
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchLowMemory(); 
  }
  
  void performMultiWindowModeChanged(boolean paramBoolean) {
    onMultiWindowModeChanged(paramBoolean);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchMultiWindowModeChanged(paramBoolean); 
  }
  
  boolean performOptionsItemSelected(MenuItem paramMenuItem) {
    if (!this.mHidden) {
      if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(paramMenuItem))
        return true; 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null && fragmentManagerImpl.dispatchOptionsItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  void performOptionsMenuClosed(Menu paramMenu) {
    if (!this.mHidden) {
      if (this.mHasMenu && this.mMenuVisible)
        onOptionsMenuClosed(paramMenu); 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null)
        fragmentManagerImpl.dispatchOptionsMenuClosed(paramMenu); 
    } 
  }
  
  void performPause() {
    if (this.mView != null)
      this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE); 
    this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchPause(); 
    this.mState = 3;
    this.mCalled = false;
    onPause();
    if (this.mCalled)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onPause()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performPictureInPictureModeChanged(boolean paramBoolean) {
    onPictureInPictureModeChanged(paramBoolean);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchPictureInPictureModeChanged(paramBoolean); 
  }
  
  boolean performPrepareOptionsMenu(Menu paramMenu) {
    boolean bool2 = this.mHidden;
    boolean bool1 = false;
    boolean bool = false;
    if (!bool2) {
      bool2 = bool;
      if (this.mHasMenu) {
        bool2 = bool;
        if (this.mMenuVisible) {
          onPrepareOptionsMenu(paramMenu);
          bool2 = true;
        } 
      } 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      bool1 = bool2;
      if (fragmentManagerImpl != null)
        bool1 = bool2 | fragmentManagerImpl.dispatchPrepareOptionsMenu(paramMenu); 
    } 
    return bool1;
  }
  
  void performResume() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null) {
      fragmentManagerImpl.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions();
    } 
    this.mState = 4;
    this.mCalled = false;
    onResume();
    if (this.mCalled) {
      fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null) {
        fragmentManagerImpl.dispatchResume();
        this.mChildFragmentManager.execPendingActions();
      } 
      this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
      if (this.mView != null)
        this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onResume()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performSaveInstanceState(Bundle paramBundle) {
    onSaveInstanceState(paramBundle);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null) {
      Parcelable parcelable = fragmentManagerImpl.saveAllState();
      if (parcelable != null)
        paramBundle.putParcelable("android:support:fragments", parcelable); 
    } 
  }
  
  void performStart() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null) {
      fragmentManagerImpl.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions();
    } 
    this.mState = 3;
    this.mCalled = false;
    onStart();
    if (this.mCalled) {
      fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null)
        fragmentManagerImpl.dispatchStart(); 
      this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
      if (this.mView != null)
        this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onStart()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performStop() {
    if (this.mView != null)
      this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP); 
    this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchStop(); 
    this.mState = 2;
    this.mCalled = false;
    onStop();
    if (this.mCalled)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onStop()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  public final FragmentActivity requireActivity() {
    FragmentActivity fragmentActivity = getActivity();
    if (fragmentActivity != null)
      return fragmentActivity; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to an activity.");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public final Context requireContext() {
    Context context = getContext();
    if (context != null)
      return context; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to a context.");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  void restoreChildFragmentState(Bundle paramBundle) {
    if (paramBundle != null) {
      Parcelable parcelable = paramBundle.getParcelable("android:support:fragments");
      if (parcelable != null) {
        if (this.mChildFragmentManager == null)
          instantiateChildFragmentManager(); 
        this.mChildFragmentManager.restoreAllState(parcelable, this.mChildNonConfig);
        this.mChildNonConfig = null;
        this.mChildFragmentManager.dispatchCreate();
      } 
    } 
  }
  
  final void restoreViewState(Bundle paramBundle) {
    SparseArray<Parcelable> sparseArray = this.mSavedViewState;
    if (sparseArray != null) {
      this.mInnerView.restoreHierarchyState(sparseArray);
      this.mSavedViewState = null;
    } 
    this.mCalled = false;
    onViewStateRestored(paramBundle);
    if (this.mCalled) {
      if (this.mView != null)
        this.mViewLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onViewStateRestored()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void setAnimatingAway(View paramView) {
    (ensureAnimationInfo()).mAnimatingAway = paramView;
  }
  
  void setAnimator(Animator paramAnimator) {
    (ensureAnimationInfo()).mAnimator = paramAnimator;
  }
  
  public void setArguments(Bundle paramBundle) {
    if (this.mIndex < 0 || !isStateSaved()) {
      this.mArguments = paramBundle;
      return;
    } 
    throw new IllegalStateException("Fragment already active and state has been saved");
  }
  
  void setHideReplaced(boolean paramBoolean) {
    (ensureAnimationInfo()).mIsHideReplaced = paramBoolean;
  }
  
  final void setIndex(int paramInt, Fragment paramFragment) {
    this.mIndex = paramInt;
    if (paramFragment != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramFragment.mWho);
      stringBuilder.append(":");
      stringBuilder.append(this.mIndex);
      this.mWho = stringBuilder.toString();
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("android:fragment:");
      stringBuilder.append(this.mIndex);
      this.mWho = stringBuilder.toString();
    } 
  }
  
  public void setInitialSavedState(SavedState paramSavedState) {
    if (this.mIndex < 0) {
      if (paramSavedState != null) {
        Bundle bundle = paramSavedState.mState;
        if (bundle != null) {
          this.mSavedFragmentState = bundle;
          return;
        } 
      } 
      paramSavedState = null;
    } else {
      throw new IllegalStateException("Fragment already active");
    } 
    this.mSavedFragmentState = (Bundle)paramSavedState;
  }
  
  public void setMenuVisibility(boolean paramBoolean) {
    if (this.mMenuVisible != paramBoolean) {
      this.mMenuVisible = paramBoolean;
      if (this.mHasMenu && isAdded() && !isHidden())
        this.mHost.onSupportInvalidateOptionsMenu(); 
    } 
  }
  
  void setNextAnim(int paramInt) {
    if (this.mAnimationInfo == null && paramInt == 0)
      return; 
    (ensureAnimationInfo()).mNextAnim = paramInt;
  }
  
  void setNextTransition(int paramInt1, int paramInt2) {
    if (this.mAnimationInfo == null && paramInt1 == 0 && paramInt2 == 0)
      return; 
    ensureAnimationInfo();
    AnimationInfo animationInfo = this.mAnimationInfo;
    animationInfo.mNextTransition = paramInt1;
    animationInfo.mNextTransitionStyle = paramInt2;
  }
  
  void setOnStartEnterTransitionListener(OnStartEnterTransitionListener paramOnStartEnterTransitionListener) {
    ensureAnimationInfo();
    OnStartEnterTransitionListener onStartEnterTransitionListener = this.mAnimationInfo.mStartEnterTransitionListener;
    if (paramOnStartEnterTransitionListener == onStartEnterTransitionListener)
      return; 
    if (paramOnStartEnterTransitionListener == null || onStartEnterTransitionListener == null) {
      AnimationInfo animationInfo = this.mAnimationInfo;
      if (animationInfo.mEnterTransitionPostponed)
        animationInfo.mStartEnterTransitionListener = paramOnStartEnterTransitionListener; 
      if (paramOnStartEnterTransitionListener != null)
        paramOnStartEnterTransitionListener.startListening(); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Trying to set a replacement startPostponedEnterTransition on ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void setRetainInstance(boolean paramBoolean) {
    this.mRetainInstance = paramBoolean;
  }
  
  void setStateAfterAnimating(int paramInt) {
    (ensureAnimationInfo()).mStateAfterAnimating = paramInt;
  }
  
  public void setUserVisibleHint(boolean paramBoolean) {
    boolean bool;
    if (!this.mUserVisibleHint && paramBoolean && this.mState < 3 && this.mFragmentManager != null && isAdded() && this.mIsCreated)
      this.mFragmentManager.performPendingDeferredStart(this); 
    this.mUserVisibleHint = paramBoolean;
    if (this.mState < 3 && !paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mDeferStart = bool;
    if (this.mSavedFragmentState != null)
      this.mSavedUserVisibleHint = Boolean.valueOf(paramBoolean); 
  }
  
  public void startActivity(Intent paramIntent) {
    startActivity(paramIntent, null);
  }
  
  public void startActivity(Intent paramIntent, Bundle paramBundle) {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      fragmentHostCallback.onStartActivityFromFragment(this, paramIntent, -1, paramBundle);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to Activity");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt) {
    startActivityForResult(paramIntent, paramInt, null);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt, Bundle paramBundle) {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      fragmentHostCallback.onStartActivityFromFragment(this, paramIntent, paramInt, paramBundle);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to Activity");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void startPostponedEnterTransition() {
    FragmentManagerImpl fragmentManagerImpl = this.mFragmentManager;
    if (fragmentManagerImpl == null || fragmentManagerImpl.mHost == null) {
      (ensureAnimationInfo()).mEnterTransitionPostponed = false;
      return;
    } 
    if (Looper.myLooper() != this.mFragmentManager.mHost.getHandler().getLooper()) {
      this.mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new Runnable() {
            final Fragment this$0;
            
            public void run() {
              Fragment.this.callStartTransitionListener();
            }
          });
    } else {
      callStartTransitionListener();
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    DebugUtils.buildShortClassTag(this, stringBuilder);
    if (this.mIndex >= 0) {
      stringBuilder.append(" #");
      stringBuilder.append(this.mIndex);
    } 
    if (this.mFragmentId != 0) {
      stringBuilder.append(" id=0x");
      stringBuilder.append(Integer.toHexString(this.mFragmentId));
    } 
    if (this.mTag != null) {
      stringBuilder.append(" ");
      stringBuilder.append(this.mTag);
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  static class AnimationInfo {
    Boolean mAllowEnterTransitionOverlap;
    
    Boolean mAllowReturnTransitionOverlap;
    
    View mAnimatingAway;
    
    Animator mAnimator;
    
    Object mEnterTransition = null;
    
    SharedElementCallback mEnterTransitionCallback;
    
    boolean mEnterTransitionPostponed;
    
    Object mExitTransition;
    
    SharedElementCallback mExitTransitionCallback;
    
    boolean mIsHideReplaced;
    
    int mNextAnim;
    
    int mNextTransition;
    
    int mNextTransitionStyle;
    
    Object mReenterTransition;
    
    Object mReturnTransition;
    
    Object mSharedElementEnterTransition;
    
    Object mSharedElementReturnTransition;
    
    Fragment.OnStartEnterTransitionListener mStartEnterTransitionListener;
    
    int mStateAfterAnimating;
    
    AnimationInfo() {
      Object object = Fragment.USE_DEFAULT_TRANSITION;
      this.mReturnTransition = object;
      this.mExitTransition = null;
      this.mReenterTransition = object;
      this.mSharedElementEnterTransition = null;
      this.mSharedElementReturnTransition = object;
      this.mEnterTransitionCallback = null;
      this.mExitTransitionCallback = null;
    }
  }
  
  public static class InstantiationException extends RuntimeException {
    public InstantiationException(String param1String, Exception param1Exception) {
      super(param1String, param1Exception);
    }
  }
  
  static interface OnStartEnterTransitionListener {
    void onStartEnterTransition();
    
    void startListening();
  }
  
  public static class SavedState implements Parcelable {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public Fragment.SavedState createFromParcel(Parcel param2Parcel) {
          return new Fragment.SavedState(param2Parcel, null);
        }
        
        public Fragment.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new Fragment.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public Fragment.SavedState[] newArray(int param2Int) {
          return new Fragment.SavedState[param2Int];
        }
      };
    
    final Bundle mState;
    
    SavedState(Bundle param1Bundle) {
      this.mState = param1Bundle;
    }
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      this.mState = param1Parcel.readBundle();
      if (param1ClassLoader != null) {
        Bundle bundle = this.mState;
        if (bundle != null)
          bundle.setClassLoader(param1ClassLoader); 
      } 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeBundle(this.mState);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public Fragment.SavedState createFromParcel(Parcel param1Parcel) {
      return new Fragment.SavedState(param1Parcel, null);
    }
    
    public Fragment.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new Fragment.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public Fragment.SavedState[] newArray(int param1Int) {
      return new Fragment.SavedState[param1Int];
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/fragment/app/Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */