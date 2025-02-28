package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Helper;
import java.util.Arrays;

public abstract class ConstraintHelper extends View {
  protected int mCount;
  
  protected Helper mHelperWidget;
  
  protected int[] mIds = new int[32];
  
  private String mReferenceIds;
  
  protected boolean mUseViewMeasure = false;
  
  protected Context myContext;
  
  public ConstraintHelper(Context paramContext) {
    super(paramContext);
    this.myContext = paramContext;
    init(null);
  }
  
  private void addID(String paramString) {
    if (paramString == null)
      return; 
    if (this.myContext == null)
      return; 
    paramString = paramString.trim();
    try {
      j = R$id.class.getField(paramString).getInt(null);
    } catch (Exception exception) {
      j = 0;
    } 
    int i = j;
    if (!j)
      i = this.myContext.getResources().getIdentifier(paramString, "id", this.myContext.getPackageName()); 
    int j = i;
    if (i == 0) {
      j = i;
      if (isInEditMode()) {
        j = i;
        if (getParent() instanceof ConstraintLayout) {
          Object object = ((ConstraintLayout)getParent()).getDesignInformation(0, paramString);
          j = i;
          if (object != null) {
            j = i;
            if (object instanceof Integer)
              j = ((Integer)object).intValue(); 
          } 
        } 
      } 
    } 
    if (j != 0) {
      setTag(j, null);
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not find id of \"");
      stringBuilder.append(paramString);
      stringBuilder.append("\"");
      stringBuilder.toString();
    } 
  }
  
  private void setIds(String paramString) {
    if (paramString == null)
      return; 
    for (int i = 0;; i = j + 1) {
      int j = paramString.indexOf(',', i);
      if (j == -1) {
        addID(paramString.substring(i));
        return;
      } 
      addID(paramString.substring(i, j));
    } 
  }
  
  public int[] getReferencedIds() {
    return Arrays.copyOf(this.mIds, this.mCount);
  }
  
  protected void init(AttributeSet paramAttributeSet) {
    if (paramAttributeSet != null) {
      TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R$styleable.ConstraintLayout_Layout);
      int i = typedArray.getIndexCount();
      for (byte b = 0; b < i; b++) {
        int j = typedArray.getIndex(b);
        if (j == R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
          this.mReferenceIds = typedArray.getString(j);
          setIds(this.mReferenceIds);
        } 
      } 
    } 
  }
  
  public void onDraw(Canvas paramCanvas) {}
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (this.mUseViewMeasure) {
      super.onMeasure(paramInt1, paramInt2);
    } else {
      setMeasuredDimension(0, 0);
    } 
  }
  
  public void setReferencedIds(int[] paramArrayOfint) {
    byte b = 0;
    this.mCount = 0;
    while (b < paramArrayOfint.length) {
      setTag(paramArrayOfint[b], null);
      b++;
    } 
  }
  
  public void setTag(int paramInt, Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mCount : I
    //   4: istore_3
    //   5: aload_0
    //   6: getfield mIds : [I
    //   9: astore_2
    //   10: iload_3
    //   11: iconst_1
    //   12: iadd
    //   13: aload_2
    //   14: arraylength
    //   15: if_icmple -> 30
    //   18: aload_0
    //   19: aload_2
    //   20: aload_2
    //   21: arraylength
    //   22: iconst_2
    //   23: imul
    //   24: invokestatic copyOf : ([II)[I
    //   27: putfield mIds : [I
    //   30: aload_0
    //   31: getfield mIds : [I
    //   34: astore_2
    //   35: aload_0
    //   36: getfield mCount : I
    //   39: istore_3
    //   40: aload_2
    //   41: iload_3
    //   42: iload_1
    //   43: iastore
    //   44: aload_0
    //   45: iload_3
    //   46: iconst_1
    //   47: iadd
    //   48: putfield mCount : I
    //   51: return
  }
  
  public void updatePostLayout(ConstraintLayout paramConstraintLayout) {}
  
  public void updatePostMeasure(ConstraintLayout paramConstraintLayout) {}
  
  public void updatePreLayout(ConstraintLayout paramConstraintLayout) {
    if (isInEditMode())
      setIds(this.mReferenceIds); 
    Helper helper = this.mHelperWidget;
    if (helper == null)
      return; 
    helper.removeAllIds();
    for (byte b = 0; b < this.mCount; b++) {
      View view = paramConstraintLayout.getViewById(this.mIds[b]);
      if (view != null)
        this.mHelperWidget.add(paramConstraintLayout.getViewWidget(view)); 
    } 
  }
  
  public void validateParams() {
    if (this.mHelperWidget == null)
      return; 
    ViewGroup.LayoutParams layoutParams = getLayoutParams();
    if (layoutParams instanceof ConstraintLayout.LayoutParams)
      ((ConstraintLayout.LayoutParams)layoutParams).widget = (ConstraintWidget)this.mHelperWidget; 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/widget/ConstraintHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */