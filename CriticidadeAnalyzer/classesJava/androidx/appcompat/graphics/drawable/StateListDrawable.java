package androidx.appcompat.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;

class StateListDrawable extends DrawableContainer {
  private boolean mMutated;
  
  private StateListState mStateListState;
  
  StateListDrawable(StateListState paramStateListState) {
    if (paramStateListState != null)
      setConstantState(paramStateListState); 
  }
  
  StateListDrawable(StateListState paramStateListState, Resources paramResources) {
    setConstantState(new StateListState(paramStateListState, this, paramResources));
    onStateChange(getState());
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    onStateChange(getState());
  }
  
  StateListState cloneConstantState() {
    return new StateListState(this.mStateListState, this, null);
  }
  
  int[] extractStateSet(AttributeSet paramAttributeSet) {
    int j = paramAttributeSet.getAttributeCount();
    int[] arrayOfInt = new int[j];
    byte b = 0;
    int i;
    for (i = 0; b < j; i = k) {
      int m = paramAttributeSet.getAttributeNameResource(b);
      int k = i;
      if (m != 0) {
        k = i;
        if (m != 16842960) {
          k = i;
          if (m != 16843161) {
            if (paramAttributeSet.getAttributeBooleanValue(b, false)) {
              k = m;
            } else {
              k = -m;
            } 
            arrayOfInt[i] = k;
            k = i + 1;
          } 
        } 
      } 
      b++;
    } 
    return StateSet.trimStateSet(arrayOfInt, i);
  }
  
  public boolean isStateful() {
    return true;
  }
  
  public Drawable mutate() {
    if (!this.mMutated) {
      super.mutate();
      this.mStateListState.mutate();
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    null = super.onStateChange(paramArrayOfint);
    int j = this.mStateListState.indexOfStateSet(paramArrayOfint);
    int i = j;
    if (j < 0)
      i = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD); 
    return (selectDrawable(i) || null);
  }
  
  protected void setConstantState(DrawableContainer.DrawableContainerState paramDrawableContainerState) {
    super.setConstantState(paramDrawableContainerState);
    if (paramDrawableContainerState instanceof StateListState)
      this.mStateListState = (StateListState)paramDrawableContainerState; 
  }
  
  static class StateListState extends DrawableContainer.DrawableContainerState {
    int[][] mStateSets;
    
    StateListState(StateListState param1StateListState, StateListDrawable param1StateListDrawable, Resources param1Resources) {
      super(param1StateListState, param1StateListDrawable, param1Resources);
      if (param1StateListState != null) {
        this.mStateSets = param1StateListState.mStateSets;
      } else {
        this.mStateSets = new int[getCapacity()][];
      } 
    }
    
    int addStateSet(int[] param1ArrayOfint, Drawable param1Drawable) {
      int i = addChild(param1Drawable);
      this.mStateSets[i] = param1ArrayOfint;
      return i;
    }
    
    public void growArray(int param1Int1, int param1Int2) {
      super.growArray(param1Int1, param1Int2);
      int[][] arrayOfInt = new int[param1Int2][];
      System.arraycopy(this.mStateSets, 0, arrayOfInt, 0, param1Int1);
      this.mStateSets = arrayOfInt;
    }
    
    int indexOfStateSet(int[] param1ArrayOfint) {
      int[][] arrayOfInt = this.mStateSets;
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        if (StateSet.stateSetMatches(arrayOfInt[b], param1ArrayOfint))
          return b; 
      } 
      return -1;
    }
    
    void mutate() {
      int[][] arrayOfInt1 = this.mStateSets;
      int[][] arrayOfInt2 = new int[arrayOfInt1.length][];
      for (int i = arrayOfInt1.length - 1; i >= 0; i--) {
        arrayOfInt1 = this.mStateSets;
        if (arrayOfInt1[i] != null) {
          int[] arrayOfInt = (int[])arrayOfInt1[i].clone();
        } else {
          arrayOfInt1 = null;
        } 
        arrayOfInt2[i] = (int[])arrayOfInt1;
      } 
      this.mStateSets = arrayOfInt2;
    }
    
    public Drawable newDrawable() {
      return new StateListDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new StateListDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/graphics/drawable/StateListDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */