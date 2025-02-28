package androidx.collection;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ArraySet<E> implements Collection<E>, Set<E> {
  private static final int[] INT = new int[0];
  
  private static final Object[] OBJECT = new Object[0];
  
  private static Object[] sBaseCache;
  
  private static int sBaseCacheSize;
  
  private static Object[] sTwiceBaseCache;
  
  private static int sTwiceBaseCacheSize;
  
  Object[] mArray;
  
  private MapCollections<E, E> mCollections;
  
  private int[] mHashes;
  
  int mSize;
  
  public ArraySet() {
    this(0);
  }
  
  public ArraySet(int paramInt) {
    if (paramInt == 0) {
      this.mHashes = INT;
      this.mArray = OBJECT;
    } else {
      allocArrays(paramInt);
    } 
    this.mSize = 0;
  }
  
  private void allocArrays(int paramInt) {
    // Byte code:
    //   0: iload_1
    //   1: bipush #8
    //   3: if_icmpne -> 75
    //   6: ldc androidx/collection/ArraySet
    //   8: monitorenter
    //   9: getstatic androidx/collection/ArraySet.sTwiceBaseCache : [Ljava/lang/Object;
    //   12: ifnull -> 63
    //   15: getstatic androidx/collection/ArraySet.sTwiceBaseCache : [Ljava/lang/Object;
    //   18: astore_2
    //   19: aload_0
    //   20: aload_2
    //   21: putfield mArray : [Ljava/lang/Object;
    //   24: aload_2
    //   25: iconst_0
    //   26: aaload
    //   27: checkcast [Ljava/lang/Object;
    //   30: putstatic androidx/collection/ArraySet.sTwiceBaseCache : [Ljava/lang/Object;
    //   33: aload_0
    //   34: aload_2
    //   35: iconst_1
    //   36: aaload
    //   37: checkcast [I
    //   40: putfield mHashes : [I
    //   43: aload_2
    //   44: iconst_1
    //   45: aconst_null
    //   46: aastore
    //   47: aload_2
    //   48: iconst_0
    //   49: aconst_null
    //   50: aastore
    //   51: getstatic androidx/collection/ArraySet.sTwiceBaseCacheSize : I
    //   54: iconst_1
    //   55: isub
    //   56: putstatic androidx/collection/ArraySet.sTwiceBaseCacheSize : I
    //   59: ldc androidx/collection/ArraySet
    //   61: monitorexit
    //   62: return
    //   63: ldc androidx/collection/ArraySet
    //   65: monitorexit
    //   66: goto -> 149
    //   69: astore_2
    //   70: ldc androidx/collection/ArraySet
    //   72: monitorexit
    //   73: aload_2
    //   74: athrow
    //   75: iload_1
    //   76: iconst_4
    //   77: if_icmpne -> 149
    //   80: ldc androidx/collection/ArraySet
    //   82: monitorenter
    //   83: getstatic androidx/collection/ArraySet.sBaseCache : [Ljava/lang/Object;
    //   86: ifnull -> 137
    //   89: getstatic androidx/collection/ArraySet.sBaseCache : [Ljava/lang/Object;
    //   92: astore_2
    //   93: aload_0
    //   94: aload_2
    //   95: putfield mArray : [Ljava/lang/Object;
    //   98: aload_2
    //   99: iconst_0
    //   100: aaload
    //   101: checkcast [Ljava/lang/Object;
    //   104: putstatic androidx/collection/ArraySet.sBaseCache : [Ljava/lang/Object;
    //   107: aload_0
    //   108: aload_2
    //   109: iconst_1
    //   110: aaload
    //   111: checkcast [I
    //   114: putfield mHashes : [I
    //   117: aload_2
    //   118: iconst_1
    //   119: aconst_null
    //   120: aastore
    //   121: aload_2
    //   122: iconst_0
    //   123: aconst_null
    //   124: aastore
    //   125: getstatic androidx/collection/ArraySet.sBaseCacheSize : I
    //   128: iconst_1
    //   129: isub
    //   130: putstatic androidx/collection/ArraySet.sBaseCacheSize : I
    //   133: ldc androidx/collection/ArraySet
    //   135: monitorexit
    //   136: return
    //   137: ldc androidx/collection/ArraySet
    //   139: monitorexit
    //   140: goto -> 149
    //   143: astore_2
    //   144: ldc androidx/collection/ArraySet
    //   146: monitorexit
    //   147: aload_2
    //   148: athrow
    //   149: aload_0
    //   150: iload_1
    //   151: newarray int
    //   153: putfield mHashes : [I
    //   156: aload_0
    //   157: iload_1
    //   158: anewarray java/lang/Object
    //   161: putfield mArray : [Ljava/lang/Object;
    //   164: return
    // Exception table:
    //   from	to	target	type
    //   9	43	69	finally
    //   51	62	69	finally
    //   63	66	69	finally
    //   70	73	69	finally
    //   83	117	143	finally
    //   125	136	143	finally
    //   137	140	143	finally
    //   144	147	143	finally
  }
  
  private static void freeArrays(int[] paramArrayOfint, Object[] paramArrayOfObject, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: arraylength
    //   2: bipush #8
    //   4: if_icmpne -> 70
    //   7: ldc androidx/collection/ArraySet
    //   9: monitorenter
    //   10: getstatic androidx/collection/ArraySet.sTwiceBaseCacheSize : I
    //   13: bipush #10
    //   15: if_icmpge -> 58
    //   18: aload_1
    //   19: iconst_0
    //   20: getstatic androidx/collection/ArraySet.sTwiceBaseCache : [Ljava/lang/Object;
    //   23: aastore
    //   24: aload_1
    //   25: iconst_1
    //   26: aload_0
    //   27: aastore
    //   28: iinc #2, -1
    //   31: iload_2
    //   32: iconst_2
    //   33: if_icmplt -> 46
    //   36: aload_1
    //   37: iload_2
    //   38: aconst_null
    //   39: aastore
    //   40: iinc #2, -1
    //   43: goto -> 31
    //   46: aload_1
    //   47: putstatic androidx/collection/ArraySet.sTwiceBaseCache : [Ljava/lang/Object;
    //   50: getstatic androidx/collection/ArraySet.sTwiceBaseCacheSize : I
    //   53: iconst_1
    //   54: iadd
    //   55: putstatic androidx/collection/ArraySet.sTwiceBaseCacheSize : I
    //   58: ldc androidx/collection/ArraySet
    //   60: monitorexit
    //   61: goto -> 139
    //   64: astore_0
    //   65: ldc androidx/collection/ArraySet
    //   67: monitorexit
    //   68: aload_0
    //   69: athrow
    //   70: aload_0
    //   71: arraylength
    //   72: iconst_4
    //   73: if_icmpne -> 139
    //   76: ldc androidx/collection/ArraySet
    //   78: monitorenter
    //   79: getstatic androidx/collection/ArraySet.sBaseCacheSize : I
    //   82: bipush #10
    //   84: if_icmpge -> 127
    //   87: aload_1
    //   88: iconst_0
    //   89: getstatic androidx/collection/ArraySet.sBaseCache : [Ljava/lang/Object;
    //   92: aastore
    //   93: aload_1
    //   94: iconst_1
    //   95: aload_0
    //   96: aastore
    //   97: iinc #2, -1
    //   100: iload_2
    //   101: iconst_2
    //   102: if_icmplt -> 115
    //   105: aload_1
    //   106: iload_2
    //   107: aconst_null
    //   108: aastore
    //   109: iinc #2, -1
    //   112: goto -> 100
    //   115: aload_1
    //   116: putstatic androidx/collection/ArraySet.sBaseCache : [Ljava/lang/Object;
    //   119: getstatic androidx/collection/ArraySet.sBaseCacheSize : I
    //   122: iconst_1
    //   123: iadd
    //   124: putstatic androidx/collection/ArraySet.sBaseCacheSize : I
    //   127: ldc androidx/collection/ArraySet
    //   129: monitorexit
    //   130: goto -> 139
    //   133: astore_0
    //   134: ldc androidx/collection/ArraySet
    //   136: monitorexit
    //   137: aload_0
    //   138: athrow
    //   139: return
    // Exception table:
    //   from	to	target	type
    //   10	24	64	finally
    //   46	58	64	finally
    //   58	61	64	finally
    //   65	68	64	finally
    //   79	93	133	finally
    //   115	127	133	finally
    //   127	130	133	finally
    //   134	137	133	finally
  }
  
  private MapCollections<E, E> getCollection() {
    if (this.mCollections == null)
      this.mCollections = new MapCollections<E, E>() {
          final ArraySet this$0;
          
          protected void colClear() {
            ArraySet.this.clear();
          }
          
          protected Object colGetEntry(int param1Int1, int param1Int2) {
            return ArraySet.this.mArray[param1Int1];
          }
          
          protected Map<E, E> colGetMap() {
            throw new UnsupportedOperationException("not a map");
          }
          
          protected int colGetSize() {
            return ArraySet.this.mSize;
          }
          
          protected int colIndexOfKey(Object param1Object) {
            return ArraySet.this.indexOf(param1Object);
          }
          
          protected int colIndexOfValue(Object param1Object) {
            return ArraySet.this.indexOf(param1Object);
          }
          
          protected void colPut(E param1E1, E param1E2) {
            ArraySet.this.add(param1E1);
          }
          
          protected void colRemoveAt(int param1Int) {
            ArraySet.this.removeAt(param1Int);
          }
          
          protected E colSetValue(int param1Int, E param1E) {
            throw new UnsupportedOperationException("not a map");
          }
        }; 
    return this.mCollections;
  }
  
  private int indexOf(Object paramObject, int paramInt) {
    int k = this.mSize;
    if (k == 0)
      return -1; 
    int j = ContainerHelpers.binarySearch(this.mHashes, k, paramInt);
    if (j < 0)
      return j; 
    if (paramObject.equals(this.mArray[j]))
      return j; 
    int i;
    for (i = j + 1; i < k && this.mHashes[i] == paramInt; i++) {
      if (paramObject.equals(this.mArray[i]))
        return i; 
    } 
    while (--j >= 0 && this.mHashes[j] == paramInt) {
      if (paramObject.equals(this.mArray[j]))
        return j; 
      j--;
    } 
    return i ^ 0xFFFFFFFF;
  }
  
  private int indexOfNull() {
    int k = this.mSize;
    if (k == 0)
      return -1; 
    int j = ContainerHelpers.binarySearch(this.mHashes, k, 0);
    if (j < 0)
      return j; 
    if (this.mArray[j] == null)
      return j; 
    int i;
    for (i = j + 1; i < k && this.mHashes[i] == 0; i++) {
      if (this.mArray[i] == null)
        return i; 
    } 
    while (--j >= 0 && this.mHashes[j] == 0) {
      if (this.mArray[j] == null)
        return j; 
      j--;
    } 
    return i ^ 0xFFFFFFFF;
  }
  
  public boolean add(E paramE) {
    int j;
    if (paramE == null) {
      i = indexOfNull();
      j = 0;
    } else {
      j = paramE.hashCode();
      i = indexOf(paramE, j);
    } 
    if (i >= 0)
      return false; 
    int k = i ^ 0xFFFFFFFF;
    int m = this.mSize;
    if (m >= this.mHashes.length) {
      i = 4;
      if (m >= 8) {
        i = (m >> 1) + m;
      } else if (m >= 4) {
        i = 8;
      } 
      int[] arrayOfInt1 = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      allocArrays(i);
      int[] arrayOfInt2 = this.mHashes;
      if (arrayOfInt2.length > 0) {
        System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, arrayOfInt1.length);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, arrayOfObject.length);
      } 
      freeArrays(arrayOfInt1, arrayOfObject, this.mSize);
    } 
    int i = this.mSize;
    if (k < i) {
      int[] arrayOfInt = this.mHashes;
      m = k + 1;
      System.arraycopy(arrayOfInt, k, arrayOfInt, m, i - k);
      Object[] arrayOfObject = this.mArray;
      System.arraycopy(arrayOfObject, k, arrayOfObject, m, this.mSize - k);
    } 
    this.mHashes[k] = j;
    this.mArray[k] = paramE;
    this.mSize++;
    return true;
  }
  
  public boolean addAll(Collection<? extends E> paramCollection) {
    ensureCapacity(this.mSize + paramCollection.size());
    Iterator<? extends E> iterator = paramCollection.iterator();
    boolean bool;
    for (bool = false; iterator.hasNext(); bool |= add(iterator.next()));
    return bool;
  }
  
  public void clear() {
    int i = this.mSize;
    if (i != 0) {
      freeArrays(this.mHashes, this.mArray, i);
      this.mHashes = INT;
      this.mArray = OBJECT;
      this.mSize = 0;
    } 
  }
  
  public boolean contains(Object paramObject) {
    boolean bool;
    if (indexOf(paramObject) >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean containsAll(Collection<?> paramCollection) {
    Iterator<?> iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      if (!contains(iterator.next()))
        return false; 
    } 
    return true;
  }
  
  public void ensureCapacity(int paramInt) {
    int[] arrayOfInt = this.mHashes;
    if (arrayOfInt.length < paramInt) {
      Object[] arrayOfObject = this.mArray;
      allocArrays(paramInt);
      paramInt = this.mSize;
      if (paramInt > 0) {
        System.arraycopy(arrayOfInt, 0, this.mHashes, 0, paramInt);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, this.mSize);
      } 
      freeArrays(arrayOfInt, arrayOfObject, this.mSize);
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject instanceof Set) {
      paramObject = paramObject;
      if (size() != paramObject.size())
        return false; 
      byte b = 0;
      try {
        while (b < this.mSize) {
          boolean bool = paramObject.contains(valueAt(b));
          if (!bool)
            return false; 
          b++;
        } 
        return true;
      } catch (NullPointerException|ClassCastException nullPointerException) {}
    } 
    return false;
  }
  
  public int hashCode() {
    int[] arrayOfInt = this.mHashes;
    int j = this.mSize;
    byte b = 0;
    int i = 0;
    while (b < j) {
      i += arrayOfInt[b];
      b++;
    } 
    return i;
  }
  
  public int indexOf(Object paramObject) {
    int i;
    if (paramObject == null) {
      i = indexOfNull();
    } else {
      i = indexOf(paramObject, paramObject.hashCode());
    } 
    return i;
  }
  
  public boolean isEmpty() {
    boolean bool;
    if (this.mSize <= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Iterator<E> iterator() {
    return getCollection().getKeySet().iterator();
  }
  
  public boolean remove(Object paramObject) {
    int i = indexOf(paramObject);
    if (i >= 0) {
      removeAt(i);
      return true;
    } 
    return false;
  }
  
  public boolean removeAll(Collection<?> paramCollection) {
    Iterator<?> iterator = paramCollection.iterator();
    boolean bool;
    for (bool = false; iterator.hasNext(); bool |= remove(iterator.next()));
    return bool;
  }
  
  public E removeAt(int paramInt) {
    Object[] arrayOfObject = this.mArray;
    Object object = arrayOfObject[paramInt];
    int i = this.mSize;
    if (i <= 1) {
      freeArrays(this.mHashes, arrayOfObject, i);
      this.mHashes = INT;
      this.mArray = OBJECT;
      this.mSize = 0;
    } else {
      int[] arrayOfInt = this.mHashes;
      int k = arrayOfInt.length;
      int j = 8;
      if (k > 8 && i < arrayOfInt.length / 3) {
        if (i > 8)
          j = i + (i >> 1); 
        arrayOfInt = this.mHashes;
        Object[] arrayOfObject1 = this.mArray;
        allocArrays(j);
        this.mSize--;
        if (paramInt > 0) {
          System.arraycopy(arrayOfInt, 0, this.mHashes, 0, paramInt);
          System.arraycopy(arrayOfObject1, 0, this.mArray, 0, paramInt);
        } 
        i = this.mSize;
        if (paramInt < i) {
          j = paramInt + 1;
          System.arraycopy(arrayOfInt, j, this.mHashes, paramInt, i - paramInt);
          System.arraycopy(arrayOfObject1, j, this.mArray, paramInt, this.mSize - paramInt);
        } 
      } else {
        i = --this.mSize;
        if (paramInt < i) {
          arrayOfInt = this.mHashes;
          j = paramInt + 1;
          System.arraycopy(arrayOfInt, j, arrayOfInt, paramInt, i - paramInt);
          Object[] arrayOfObject1 = this.mArray;
          System.arraycopy(arrayOfObject1, j, arrayOfObject1, paramInt, this.mSize - paramInt);
        } 
        this.mArray[this.mSize] = null;
      } 
    } 
    return (E)object;
  }
  
  public boolean retainAll(Collection<?> paramCollection) {
    int i = this.mSize - 1;
    boolean bool = false;
    while (i >= 0) {
      if (!paramCollection.contains(this.mArray[i])) {
        removeAt(i);
        bool = true;
      } 
      i--;
    } 
    return bool;
  }
  
  public int size() {
    return this.mSize;
  }
  
  public Object[] toArray() {
    int i = this.mSize;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(this.mArray, 0, arrayOfObject, 0, i);
    return arrayOfObject;
  }
  
  public <T> T[] toArray(T[] paramArrayOfT) {
    T[] arrayOfT = paramArrayOfT;
    if (paramArrayOfT.length < this.mSize)
      arrayOfT = (T[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), this.mSize); 
    System.arraycopy(this.mArray, 0, arrayOfT, 0, this.mSize);
    int i = arrayOfT.length;
    int j = this.mSize;
    if (i > j)
      arrayOfT[j] = null; 
    return arrayOfT;
  }
  
  public String toString() {
    if (isEmpty())
      return "{}"; 
    StringBuilder stringBuilder = new StringBuilder(this.mSize * 14);
    stringBuilder.append('{');
    for (byte b = 0; b < this.mSize; b++) {
      if (b > 0)
        stringBuilder.append(", "); 
      E e = valueAt(b);
      if (e != this) {
        stringBuilder.append(e);
      } else {
        stringBuilder.append("(this Set)");
      } 
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public E valueAt(int paramInt) {
    return (E)this.mArray[paramInt];
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/collection/ArraySet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */