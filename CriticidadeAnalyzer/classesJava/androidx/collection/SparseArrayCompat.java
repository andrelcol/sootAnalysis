package androidx.collection;

public class SparseArrayCompat<E> implements Cloneable {
  private static final Object DELETED = new Object();
  
  private boolean mGarbage = false;
  
  private int[] mKeys;
  
  private int mSize;
  
  private Object[] mValues;
  
  public SparseArrayCompat() {
    this(10);
  }
  
  public SparseArrayCompat(int paramInt) {
    if (paramInt == 0) {
      this.mKeys = ContainerHelpers.EMPTY_INTS;
      this.mValues = ContainerHelpers.EMPTY_OBJECTS;
    } else {
      paramInt = ContainerHelpers.idealIntArraySize(paramInt);
      this.mKeys = new int[paramInt];
      this.mValues = new Object[paramInt];
    } 
    this.mSize = 0;
  }
  
  private void gc() {
    int k = this.mSize;
    int[] arrayOfInt = this.mKeys;
    Object[] arrayOfObject = this.mValues;
    int j = 0;
    int i;
    for (i = 0; j < k; i = m) {
      Object object = arrayOfObject[j];
      int m = i;
      if (object != DELETED) {
        if (j != i) {
          arrayOfInt[i] = arrayOfInt[j];
          arrayOfObject[i] = object;
          arrayOfObject[j] = null;
        } 
        m = i + 1;
      } 
      j++;
    } 
    this.mGarbage = false;
    this.mSize = i;
  }
  
  public void append(int paramInt, E paramE) {
    int i = this.mSize;
    if (i != 0 && paramInt <= this.mKeys[i - 1]) {
      put(paramInt, paramE);
      return;
    } 
    if (this.mGarbage && this.mSize >= this.mKeys.length)
      gc(); 
    i = this.mSize;
    if (i >= this.mKeys.length) {
      int j = ContainerHelpers.idealIntArraySize(i + 1);
      int[] arrayOfInt1 = new int[j];
      Object[] arrayOfObject1 = new Object[j];
      int[] arrayOfInt2 = this.mKeys;
      System.arraycopy(arrayOfInt2, 0, arrayOfInt1, 0, arrayOfInt2.length);
      Object[] arrayOfObject2 = this.mValues;
      System.arraycopy(arrayOfObject2, 0, arrayOfObject1, 0, arrayOfObject2.length);
      this.mKeys = arrayOfInt1;
      this.mValues = arrayOfObject1;
    } 
    this.mKeys[i] = paramInt;
    this.mValues[i] = paramE;
    this.mSize = i + 1;
  }
  
  public void clear() {
    int i = this.mSize;
    Object[] arrayOfObject = this.mValues;
    for (byte b = 0; b < i; b++)
      arrayOfObject[b] = null; 
    this.mSize = 0;
    this.mGarbage = false;
  }
  
  public SparseArrayCompat<E> clone() {
    try {
      SparseArrayCompat<E> sparseArrayCompat = (SparseArrayCompat)super.clone();
      sparseArrayCompat.mKeys = (int[])this.mKeys.clone();
      sparseArrayCompat.mValues = (Object[])this.mValues.clone();
      return sparseArrayCompat;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError(cloneNotSupportedException);
    } 
  }
  
  public void delete(int paramInt) {
    paramInt = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt);
    if (paramInt >= 0) {
      Object[] arrayOfObject = this.mValues;
      Object object2 = arrayOfObject[paramInt];
      Object object1 = DELETED;
      if (object2 != object1) {
        arrayOfObject[paramInt] = object1;
        this.mGarbage = true;
      } 
    } 
  }
  
  public E get(int paramInt) {
    return get(paramInt, null);
  }
  
  public E get(int paramInt, E paramE) {
    paramInt = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt);
    if (paramInt >= 0) {
      Object[] arrayOfObject = this.mValues;
      if (arrayOfObject[paramInt] != DELETED)
        return (E)arrayOfObject[paramInt]; 
    } 
    return paramE;
  }
  
  public int indexOfKey(int paramInt) {
    if (this.mGarbage)
      gc(); 
    return ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt);
  }
  
  public int keyAt(int paramInt) {
    if (this.mGarbage)
      gc(); 
    return this.mKeys[paramInt];
  }
  
  public void put(int paramInt, E paramE) {
    int i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt);
    if (i >= 0) {
      this.mValues[i] = paramE;
    } else {
      int j = i ^ 0xFFFFFFFF;
      if (j < this.mSize) {
        Object[] arrayOfObject = this.mValues;
        if (arrayOfObject[j] == DELETED) {
          this.mKeys[j] = paramInt;
          arrayOfObject[j] = paramE;
          return;
        } 
      } 
      i = j;
      if (this.mGarbage) {
        i = j;
        if (this.mSize >= this.mKeys.length) {
          gc();
          i = ContainerHelpers.binarySearch(this.mKeys, this.mSize, paramInt) ^ 0xFFFFFFFF;
        } 
      } 
      j = this.mSize;
      if (j >= this.mKeys.length) {
        j = ContainerHelpers.idealIntArraySize(j + 1);
        int[] arrayOfInt1 = new int[j];
        Object[] arrayOfObject1 = new Object[j];
        int[] arrayOfInt2 = this.mKeys;
        System.arraycopy(arrayOfInt2, 0, arrayOfInt1, 0, arrayOfInt2.length);
        Object[] arrayOfObject2 = this.mValues;
        System.arraycopy(arrayOfObject2, 0, arrayOfObject1, 0, arrayOfObject2.length);
        this.mKeys = arrayOfInt1;
        this.mValues = arrayOfObject1;
      } 
      int k = this.mSize;
      if (k - i != 0) {
        int[] arrayOfInt = this.mKeys;
        j = i + 1;
        System.arraycopy(arrayOfInt, i, arrayOfInt, j, k - i);
        Object[] arrayOfObject = this.mValues;
        System.arraycopy(arrayOfObject, i, arrayOfObject, j, this.mSize - i);
      } 
      this.mKeys[i] = paramInt;
      this.mValues[i] = paramE;
      this.mSize++;
    } 
  }
  
  public void remove(int paramInt) {
    delete(paramInt);
  }
  
  public int size() {
    if (this.mGarbage)
      gc(); 
    return this.mSize;
  }
  
  public String toString() {
    if (size() <= 0)
      return "{}"; 
    StringBuilder stringBuilder = new StringBuilder(this.mSize * 28);
    stringBuilder.append('{');
    for (byte b = 0; b < this.mSize; b++) {
      if (b > 0)
        stringBuilder.append(", "); 
      stringBuilder.append(keyAt(b));
      stringBuilder.append('=');
      E e = valueAt(b);
      if (e != this) {
        stringBuilder.append(e);
      } else {
        stringBuilder.append("(this Map)");
      } 
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public E valueAt(int paramInt) {
    if (this.mGarbage)
      gc(); 
    return (E)this.mValues[paramInt];
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/collection/SparseArrayCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */