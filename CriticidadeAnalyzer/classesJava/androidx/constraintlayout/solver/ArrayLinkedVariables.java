package androidx.constraintlayout.solver;

import java.util.Arrays;

public class ArrayLinkedVariables {
  private int ROW_SIZE = 8;
  
  private SolverVariable candidate = null;
  
  int currentSize = 0;
  
  private int[] mArrayIndices;
  
  private int[] mArrayNextIndices;
  
  private float[] mArrayValues;
  
  private final Cache mCache;
  
  private boolean mDidFillOnce;
  
  private int mHead;
  
  private int mLast;
  
  private final ArrayRow mRow;
  
  ArrayLinkedVariables(ArrayRow paramArrayRow, Cache paramCache) {
    int i = this.ROW_SIZE;
    this.mArrayIndices = new int[i];
    this.mArrayNextIndices = new int[i];
    this.mArrayValues = new float[i];
    this.mHead = -1;
    this.mLast = -1;
    this.mDidFillOnce = false;
    this.mRow = paramArrayRow;
    this.mCache = paramCache;
  }
  
  private boolean isNew(SolverVariable paramSolverVariable, LinearSystem paramLinearSystem) {
    int i = paramSolverVariable.usageInRowCount;
    boolean bool = true;
    if (i > 1)
      bool = false; 
    return bool;
  }
  
  final void add(SolverVariable paramSolverVariable, float paramFloat, boolean paramBoolean) {
    if (paramFloat == 0.0F)
      return; 
    int i = this.mHead;
    if (i == -1) {
      this.mHead = 0;
      float[] arrayOfFloat = this.mArrayValues;
      i = this.mHead;
      arrayOfFloat[i] = paramFloat;
      this.mArrayIndices[i] = paramSolverVariable.id;
      this.mArrayNextIndices[i] = -1;
      paramSolverVariable.usageInRowCount++;
      paramSolverVariable.addToRow(this.mRow);
      this.currentSize++;
      if (!this.mDidFillOnce) {
        i = ++this.mLast;
        arrayOfInt1 = this.mArrayIndices;
        if (i >= arrayOfInt1.length) {
          this.mDidFillOnce = true;
          this.mLast = arrayOfInt1.length - 1;
        } 
      } 
      return;
    } 
    int j = 0;
    int k = -1;
    while (i != -1 && j < this.currentSize) {
      int[] arrayOfInt = this.mArrayIndices;
      int n = arrayOfInt[i];
      int m = ((SolverVariable)arrayOfInt1).id;
      if (n == m) {
        float[] arrayOfFloat = this.mArrayValues;
        arrayOfFloat[i] = arrayOfFloat[i] + paramFloat;
        if (arrayOfFloat[i] == 0.0F) {
          if (i == this.mHead) {
            this.mHead = this.mArrayNextIndices[i];
          } else {
            arrayOfInt = this.mArrayNextIndices;
            arrayOfInt[k] = arrayOfInt[i];
          } 
          if (paramBoolean)
            arrayOfInt1.removeFromRow(this.mRow); 
          if (this.mDidFillOnce)
            this.mLast = i; 
          ((SolverVariable)arrayOfInt1).usageInRowCount--;
          this.currentSize--;
        } 
        return;
      } 
      if (arrayOfInt[i] < m)
        k = i; 
      i = this.mArrayNextIndices[i];
      j++;
    } 
    i = this.mLast;
    if (this.mDidFillOnce) {
      int[] arrayOfInt = this.mArrayIndices;
      if (arrayOfInt[i] != -1)
        i = arrayOfInt.length; 
    } else {
      i++;
    } 
    int[] arrayOfInt2 = this.mArrayIndices;
    j = i;
    if (i >= arrayOfInt2.length) {
      j = i;
      if (this.currentSize < arrayOfInt2.length) {
        byte b = 0;
        while (true) {
          arrayOfInt2 = this.mArrayIndices;
          j = i;
          if (b < arrayOfInt2.length) {
            if (arrayOfInt2[b] == -1) {
              j = b;
              break;
            } 
            b++;
            continue;
          } 
          break;
        } 
      } 
    } 
    arrayOfInt2 = this.mArrayIndices;
    i = j;
    if (j >= arrayOfInt2.length) {
      i = arrayOfInt2.length;
      this.ROW_SIZE *= 2;
      this.mDidFillOnce = false;
      this.mLast = i - 1;
      this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
      this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
      this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
    } 
    this.mArrayIndices[i] = ((SolverVariable)arrayOfInt1).id;
    this.mArrayValues[i] = paramFloat;
    if (k != -1) {
      arrayOfInt2 = this.mArrayNextIndices;
      arrayOfInt2[i] = arrayOfInt2[k];
      arrayOfInt2[k] = i;
    } else {
      this.mArrayNextIndices[i] = this.mHead;
      this.mHead = i;
    } 
    ((SolverVariable)arrayOfInt1).usageInRowCount++;
    arrayOfInt1.addToRow(this.mRow);
    this.currentSize++;
    if (!this.mDidFillOnce)
      this.mLast++; 
    i = this.mLast;
    int[] arrayOfInt1 = this.mArrayIndices;
    if (i >= arrayOfInt1.length) {
      this.mDidFillOnce = true;
      this.mLast = arrayOfInt1.length - 1;
    } 
  }
  
  SolverVariable chooseSubject(LinearSystem paramLinearSystem) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mHead : I
    //   4: istore #8
    //   6: aconst_null
    //   7: astore #17
    //   9: iconst_0
    //   10: istore #7
    //   12: aconst_null
    //   13: astore #16
    //   15: fconst_0
    //   16: fstore #6
    //   18: iconst_0
    //   19: istore #12
    //   21: fconst_0
    //   22: fstore #5
    //   24: iconst_0
    //   25: istore #11
    //   27: iload #8
    //   29: iconst_m1
    //   30: if_icmpeq -> 544
    //   33: iload #7
    //   35: aload_0
    //   36: getfield currentSize : I
    //   39: if_icmpge -> 544
    //   42: aload_0
    //   43: getfield mArrayValues : [F
    //   46: astore #14
    //   48: aload #14
    //   50: iload #8
    //   52: faload
    //   53: fstore_3
    //   54: aload_0
    //   55: getfield mCache : Landroidx/constraintlayout/solver/Cache;
    //   58: getfield mIndexedVariables : [Landroidx/constraintlayout/solver/SolverVariable;
    //   61: aload_0
    //   62: getfield mArrayIndices : [I
    //   65: iload #8
    //   67: iaload
    //   68: aaload
    //   69: astore #13
    //   71: fload_3
    //   72: fconst_0
    //   73: fcmpg
    //   74: ifge -> 104
    //   77: fload_3
    //   78: fstore_2
    //   79: fload_3
    //   80: ldc -0.001
    //   82: fcmpl
    //   83: ifle -> 130
    //   86: aload #14
    //   88: iload #8
    //   90: fconst_0
    //   91: fastore
    //   92: aload #13
    //   94: aload_0
    //   95: getfield mRow : Landroidx/constraintlayout/solver/ArrayRow;
    //   98: invokevirtual removeFromRow : (Landroidx/constraintlayout/solver/ArrayRow;)V
    //   101: goto -> 128
    //   104: fload_3
    //   105: fstore_2
    //   106: fload_3
    //   107: ldc 0.001
    //   109: fcmpg
    //   110: ifge -> 130
    //   113: aload #14
    //   115: iload #8
    //   117: fconst_0
    //   118: fastore
    //   119: aload #13
    //   121: aload_0
    //   122: getfield mRow : Landroidx/constraintlayout/solver/ArrayRow;
    //   125: invokevirtual removeFromRow : (Landroidx/constraintlayout/solver/ArrayRow;)V
    //   128: fconst_0
    //   129: fstore_2
    //   130: aload #17
    //   132: astore #14
    //   134: aload #16
    //   136: astore #15
    //   138: fload #6
    //   140: fstore_3
    //   141: iload #12
    //   143: istore #9
    //   145: fload #5
    //   147: fstore #4
    //   149: iload #11
    //   151: istore #10
    //   153: fload_2
    //   154: fconst_0
    //   155: fcmpl
    //   156: ifeq -> 506
    //   159: aload #13
    //   161: getfield mType : Landroidx/constraintlayout/solver/SolverVariable$Type;
    //   164: getstatic androidx/constraintlayout/solver/SolverVariable$Type.UNRESTRICTED : Landroidx/constraintlayout/solver/SolverVariable$Type;
    //   167: if_acmpne -> 309
    //   170: aload #16
    //   172: ifnonnull -> 205
    //   175: aload_0
    //   176: aload #13
    //   178: aload_1
    //   179: invokespecial isNew : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/LinearSystem;)Z
    //   182: istore #9
    //   184: aload #17
    //   186: astore #14
    //   188: aload #13
    //   190: astore #15
    //   192: fload_2
    //   193: fstore_3
    //   194: fload #5
    //   196: fstore #4
    //   198: iload #11
    //   200: istore #10
    //   202: goto -> 506
    //   205: fload #6
    //   207: fload_2
    //   208: fcmpl
    //   209: ifle -> 224
    //   212: aload_0
    //   213: aload #13
    //   215: aload_1
    //   216: invokespecial isNew : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/LinearSystem;)Z
    //   219: istore #9
    //   221: goto -> 184
    //   224: aload #17
    //   226: astore #14
    //   228: aload #16
    //   230: astore #15
    //   232: fload #6
    //   234: fstore_3
    //   235: iload #12
    //   237: istore #9
    //   239: fload #5
    //   241: fstore #4
    //   243: iload #11
    //   245: istore #10
    //   247: iload #12
    //   249: ifne -> 506
    //   252: aload #17
    //   254: astore #14
    //   256: aload #16
    //   258: astore #15
    //   260: fload #6
    //   262: fstore_3
    //   263: iload #12
    //   265: istore #9
    //   267: fload #5
    //   269: fstore #4
    //   271: iload #11
    //   273: istore #10
    //   275: aload_0
    //   276: aload #13
    //   278: aload_1
    //   279: invokespecial isNew : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/LinearSystem;)Z
    //   282: ifeq -> 506
    //   285: iconst_1
    //   286: istore #9
    //   288: aload #17
    //   290: astore #14
    //   292: aload #13
    //   294: astore #15
    //   296: fload_2
    //   297: fstore_3
    //   298: fload #5
    //   300: fstore #4
    //   302: iload #11
    //   304: istore #10
    //   306: goto -> 506
    //   309: aload #17
    //   311: astore #14
    //   313: aload #16
    //   315: astore #15
    //   317: fload #6
    //   319: fstore_3
    //   320: iload #12
    //   322: istore #9
    //   324: fload #5
    //   326: fstore #4
    //   328: iload #11
    //   330: istore #10
    //   332: aload #16
    //   334: ifnonnull -> 506
    //   337: aload #17
    //   339: astore #14
    //   341: aload #16
    //   343: astore #15
    //   345: fload #6
    //   347: fstore_3
    //   348: iload #12
    //   350: istore #9
    //   352: fload #5
    //   354: fstore #4
    //   356: iload #11
    //   358: istore #10
    //   360: fload_2
    //   361: fconst_0
    //   362: fcmpg
    //   363: ifge -> 506
    //   366: aload #17
    //   368: ifnonnull -> 405
    //   371: aload_0
    //   372: aload #13
    //   374: aload_1
    //   375: invokespecial isNew : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/LinearSystem;)Z
    //   378: istore #9
    //   380: iload #9
    //   382: istore #10
    //   384: aload #13
    //   386: astore #14
    //   388: aload #16
    //   390: astore #15
    //   392: fload #6
    //   394: fstore_3
    //   395: iload #12
    //   397: istore #9
    //   399: fload_2
    //   400: fstore #4
    //   402: goto -> 506
    //   405: fload #5
    //   407: fload_2
    //   408: fcmpl
    //   409: ifle -> 424
    //   412: aload_0
    //   413: aload #13
    //   415: aload_1
    //   416: invokespecial isNew : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/LinearSystem;)Z
    //   419: istore #9
    //   421: goto -> 380
    //   424: aload #17
    //   426: astore #14
    //   428: aload #16
    //   430: astore #15
    //   432: fload #6
    //   434: fstore_3
    //   435: iload #12
    //   437: istore #9
    //   439: fload #5
    //   441: fstore #4
    //   443: iload #11
    //   445: istore #10
    //   447: iload #11
    //   449: ifne -> 506
    //   452: aload #17
    //   454: astore #14
    //   456: aload #16
    //   458: astore #15
    //   460: fload #6
    //   462: fstore_3
    //   463: iload #12
    //   465: istore #9
    //   467: fload #5
    //   469: fstore #4
    //   471: iload #11
    //   473: istore #10
    //   475: aload_0
    //   476: aload #13
    //   478: aload_1
    //   479: invokespecial isNew : (Landroidx/constraintlayout/solver/SolverVariable;Landroidx/constraintlayout/solver/LinearSystem;)Z
    //   482: ifeq -> 506
    //   485: iconst_1
    //   486: istore #10
    //   488: fload_2
    //   489: fstore #4
    //   491: iload #12
    //   493: istore #9
    //   495: fload #6
    //   497: fstore_3
    //   498: aload #16
    //   500: astore #15
    //   502: aload #13
    //   504: astore #14
    //   506: aload_0
    //   507: getfield mArrayNextIndices : [I
    //   510: iload #8
    //   512: iaload
    //   513: istore #8
    //   515: iinc #7, 1
    //   518: aload #14
    //   520: astore #17
    //   522: aload #15
    //   524: astore #16
    //   526: fload_3
    //   527: fstore #6
    //   529: iload #9
    //   531: istore #12
    //   533: fload #4
    //   535: fstore #5
    //   537: iload #10
    //   539: istore #11
    //   541: goto -> 27
    //   544: aload #16
    //   546: ifnull -> 552
    //   549: aload #16
    //   551: areturn
    //   552: aload #17
    //   554: areturn
  }
  
  public final void clear() {
    int i = this.mHead;
    for (byte b = 0; i != -1 && b < this.currentSize; b++) {
      SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
      if (solverVariable != null)
        solverVariable.removeFromRow(this.mRow); 
      i = this.mArrayNextIndices[i];
    } 
    this.mHead = -1;
    this.mLast = -1;
    this.mDidFillOnce = false;
    this.currentSize = 0;
  }
  
  final boolean containsKey(SolverVariable paramSolverVariable) {
    int i = this.mHead;
    if (i == -1)
      return false; 
    for (byte b = 0; i != -1 && b < this.currentSize; b++) {
      if (this.mArrayIndices[i] == paramSolverVariable.id)
        return true; 
      i = this.mArrayNextIndices[i];
    } 
    return false;
  }
  
  void divideByAmount(float paramFloat) {
    int i = this.mHead;
    for (byte b = 0; i != -1 && b < this.currentSize; b++) {
      float[] arrayOfFloat = this.mArrayValues;
      arrayOfFloat[i] = arrayOfFloat[i] / paramFloat;
      i = this.mArrayNextIndices[i];
    } 
  }
  
  public final float get(SolverVariable paramSolverVariable) {
    int i = this.mHead;
    for (byte b = 0; i != -1 && b < this.currentSize; b++) {
      if (this.mArrayIndices[i] == paramSolverVariable.id)
        return this.mArrayValues[i]; 
      i = this.mArrayNextIndices[i];
    } 
    return 0.0F;
  }
  
  SolverVariable getPivotCandidate(boolean[] paramArrayOfboolean, SolverVariable paramSolverVariable) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mHead : I
    //   4: istore #7
    //   6: iconst_0
    //   7: istore #6
    //   9: aconst_null
    //   10: astore #8
    //   12: fconst_0
    //   13: fstore #4
    //   15: iload #7
    //   17: iconst_m1
    //   18: if_icmpeq -> 183
    //   21: iload #6
    //   23: aload_0
    //   24: getfield currentSize : I
    //   27: if_icmpge -> 183
    //   30: aload #8
    //   32: astore #9
    //   34: fload #4
    //   36: fstore_3
    //   37: aload_0
    //   38: getfield mArrayValues : [F
    //   41: iload #7
    //   43: faload
    //   44: fconst_0
    //   45: fcmpg
    //   46: ifge -> 161
    //   49: aload_0
    //   50: getfield mCache : Landroidx/constraintlayout/solver/Cache;
    //   53: getfield mIndexedVariables : [Landroidx/constraintlayout/solver/SolverVariable;
    //   56: aload_0
    //   57: getfield mArrayIndices : [I
    //   60: iload #7
    //   62: iaload
    //   63: aaload
    //   64: astore #10
    //   66: aload_1
    //   67: ifnull -> 87
    //   70: aload #8
    //   72: astore #9
    //   74: fload #4
    //   76: fstore_3
    //   77: aload_1
    //   78: aload #10
    //   80: getfield id : I
    //   83: baload
    //   84: ifne -> 161
    //   87: aload #8
    //   89: astore #9
    //   91: fload #4
    //   93: fstore_3
    //   94: aload #10
    //   96: aload_2
    //   97: if_acmpeq -> 161
    //   100: aload #10
    //   102: getfield mType : Landroidx/constraintlayout/solver/SolverVariable$Type;
    //   105: astore #11
    //   107: aload #11
    //   109: getstatic androidx/constraintlayout/solver/SolverVariable$Type.SLACK : Landroidx/constraintlayout/solver/SolverVariable$Type;
    //   112: if_acmpeq -> 130
    //   115: aload #8
    //   117: astore #9
    //   119: fload #4
    //   121: fstore_3
    //   122: aload #11
    //   124: getstatic androidx/constraintlayout/solver/SolverVariable$Type.ERROR : Landroidx/constraintlayout/solver/SolverVariable$Type;
    //   127: if_acmpne -> 161
    //   130: aload_0
    //   131: getfield mArrayValues : [F
    //   134: iload #7
    //   136: faload
    //   137: fstore #5
    //   139: aload #8
    //   141: astore #9
    //   143: fload #4
    //   145: fstore_3
    //   146: fload #5
    //   148: fload #4
    //   150: fcmpg
    //   151: ifge -> 161
    //   154: aload #10
    //   156: astore #9
    //   158: fload #5
    //   160: fstore_3
    //   161: aload_0
    //   162: getfield mArrayNextIndices : [I
    //   165: iload #7
    //   167: iaload
    //   168: istore #7
    //   170: iinc #6, 1
    //   173: aload #9
    //   175: astore #8
    //   177: fload_3
    //   178: fstore #4
    //   180: goto -> 15
    //   183: aload #8
    //   185: areturn
  }
  
  final SolverVariable getVariable(int paramInt) {
    int i = this.mHead;
    for (byte b = 0; i != -1 && b < this.currentSize; b++) {
      if (b == paramInt)
        return this.mCache.mIndexedVariables[this.mArrayIndices[i]]; 
      i = this.mArrayNextIndices[i];
    } 
    return null;
  }
  
  final float getVariableValue(int paramInt) {
    int i = this.mHead;
    for (byte b = 0; i != -1 && b < this.currentSize; b++) {
      if (b == paramInt)
        return this.mArrayValues[i]; 
      i = this.mArrayNextIndices[i];
    } 
    return 0.0F;
  }
  
  void invert() {
    int i = this.mHead;
    for (byte b = 0; i != -1 && b < this.currentSize; b++) {
      float[] arrayOfFloat = this.mArrayValues;
      arrayOfFloat[i] = arrayOfFloat[i] * -1.0F;
      i = this.mArrayNextIndices[i];
    } 
  }
  
  public final void put(SolverVariable paramSolverVariable, float paramFloat) {
    if (paramFloat == 0.0F) {
      remove(paramSolverVariable, true);
      return;
    } 
    int i = this.mHead;
    if (i == -1) {
      this.mHead = 0;
      float[] arrayOfFloat = this.mArrayValues;
      i = this.mHead;
      arrayOfFloat[i] = paramFloat;
      this.mArrayIndices[i] = paramSolverVariable.id;
      this.mArrayNextIndices[i] = -1;
      paramSolverVariable.usageInRowCount++;
      paramSolverVariable.addToRow(this.mRow);
      this.currentSize++;
      if (!this.mDidFillOnce) {
        i = ++this.mLast;
        arrayOfInt1 = this.mArrayIndices;
        if (i >= arrayOfInt1.length) {
          this.mDidFillOnce = true;
          this.mLast = arrayOfInt1.length - 1;
        } 
      } 
      return;
    } 
    int j = 0;
    int k = -1;
    while (i != -1 && j < this.currentSize) {
      int[] arrayOfInt = this.mArrayIndices;
      int n = arrayOfInt[i];
      int m = ((SolverVariable)arrayOfInt1).id;
      if (n == m) {
        this.mArrayValues[i] = paramFloat;
        return;
      } 
      if (arrayOfInt[i] < m)
        k = i; 
      i = this.mArrayNextIndices[i];
      j++;
    } 
    i = this.mLast;
    if (this.mDidFillOnce) {
      int[] arrayOfInt = this.mArrayIndices;
      if (arrayOfInt[i] != -1)
        i = arrayOfInt.length; 
    } else {
      i++;
    } 
    int[] arrayOfInt2 = this.mArrayIndices;
    j = i;
    if (i >= arrayOfInt2.length) {
      j = i;
      if (this.currentSize < arrayOfInt2.length) {
        byte b = 0;
        while (true) {
          arrayOfInt2 = this.mArrayIndices;
          j = i;
          if (b < arrayOfInt2.length) {
            if (arrayOfInt2[b] == -1) {
              j = b;
              break;
            } 
            b++;
            continue;
          } 
          break;
        } 
      } 
    } 
    arrayOfInt2 = this.mArrayIndices;
    i = j;
    if (j >= arrayOfInt2.length) {
      i = arrayOfInt2.length;
      this.ROW_SIZE *= 2;
      this.mDidFillOnce = false;
      this.mLast = i - 1;
      this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
      this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
      this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
    } 
    this.mArrayIndices[i] = ((SolverVariable)arrayOfInt1).id;
    this.mArrayValues[i] = paramFloat;
    if (k != -1) {
      arrayOfInt2 = this.mArrayNextIndices;
      arrayOfInt2[i] = arrayOfInt2[k];
      arrayOfInt2[k] = i;
    } else {
      this.mArrayNextIndices[i] = this.mHead;
      this.mHead = i;
    } 
    ((SolverVariable)arrayOfInt1).usageInRowCount++;
    arrayOfInt1.addToRow(this.mRow);
    this.currentSize++;
    if (!this.mDidFillOnce)
      this.mLast++; 
    if (this.currentSize >= this.mArrayIndices.length)
      this.mDidFillOnce = true; 
    i = this.mLast;
    int[] arrayOfInt1 = this.mArrayIndices;
    if (i >= arrayOfInt1.length) {
      this.mDidFillOnce = true;
      this.mLast = arrayOfInt1.length - 1;
    } 
  }
  
  public final float remove(SolverVariable paramSolverVariable, boolean paramBoolean) {
    if (this.candidate == paramSolverVariable)
      this.candidate = null; 
    int i = this.mHead;
    if (i == -1)
      return 0.0F; 
    byte b = 0;
    int j = -1;
    while (i != -1 && b < this.currentSize) {
      if (this.mArrayIndices[i] == paramSolverVariable.id) {
        if (i == this.mHead) {
          this.mHead = this.mArrayNextIndices[i];
        } else {
          int[] arrayOfInt = this.mArrayNextIndices;
          arrayOfInt[j] = arrayOfInt[i];
        } 
        if (paramBoolean)
          paramSolverVariable.removeFromRow(this.mRow); 
        paramSolverVariable.usageInRowCount--;
        this.currentSize--;
        this.mArrayIndices[i] = -1;
        if (this.mDidFillOnce)
          this.mLast = i; 
        return this.mArrayValues[i];
      } 
      int k = this.mArrayNextIndices[i];
      b++;
      j = i;
      i = k;
    } 
    return 0.0F;
  }
  
  public String toString() {
    int i = this.mHead;
    String str = "";
    for (byte b = 0; i != -1 && b < this.currentSize; b++) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(" -> ");
      str = stringBuilder.toString();
      stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(this.mArrayValues[i]);
      stringBuilder.append(" : ");
      str = stringBuilder.toString();
      stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(this.mCache.mIndexedVariables[this.mArrayIndices[i]]);
      str = stringBuilder.toString();
      i = this.mArrayNextIndices[i];
    } 
    return str;
  }
  
  final void updateFromRow(ArrayRow paramArrayRow1, ArrayRow paramArrayRow2, boolean paramBoolean) {
    int i = this.mHead;
    label23: while (true) {
      for (int j = 0; i != -1 && j < this.currentSize; j++) {
        int k = this.mArrayIndices[i];
        SolverVariable solverVariable = paramArrayRow2.variable;
        if (k == solverVariable.id) {
          float f = this.mArrayValues[i];
          remove(solverVariable, paramBoolean);
          ArrayLinkedVariables arrayLinkedVariables = paramArrayRow2.variables;
          j = arrayLinkedVariables.mHead;
          for (i = 0; j != -1 && i < arrayLinkedVariables.currentSize; i++) {
            add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[j]], arrayLinkedVariables.mArrayValues[j] * f, paramBoolean);
            j = arrayLinkedVariables.mArrayNextIndices[j];
          } 
          paramArrayRow1.constantValue += paramArrayRow2.constantValue * f;
          if (paramBoolean)
            paramArrayRow2.variable.removeFromRow(paramArrayRow1); 
          i = this.mHead;
          continue label23;
        } 
        i = this.mArrayNextIndices[i];
      } 
      break;
    } 
  }
  
  void updateFromSystem(ArrayRow paramArrayRow, ArrayRow[] paramArrayOfArrayRow) {
    int i = this.mHead;
    label22: while (true) {
      for (int j = 0; i != -1 && j < this.currentSize; j++) {
        SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
        if (solverVariable.definitionId != -1) {
          float f = this.mArrayValues[i];
          remove(solverVariable, true);
          ArrayRow arrayRow = paramArrayOfArrayRow[solverVariable.definitionId];
          if (!arrayRow.isSimpleDefinition) {
            ArrayLinkedVariables arrayLinkedVariables = arrayRow.variables;
            j = arrayLinkedVariables.mHead;
            for (i = 0; j != -1 && i < arrayLinkedVariables.currentSize; i++) {
              add(this.mCache.mIndexedVariables[arrayLinkedVariables.mArrayIndices[j]], arrayLinkedVariables.mArrayValues[j] * f, true);
              j = arrayLinkedVariables.mArrayNextIndices[j];
            } 
          } 
          paramArrayRow.constantValue += arrayRow.constantValue * f;
          arrayRow.variable.removeFromRow(paramArrayRow);
          i = this.mHead;
          continue label22;
        } 
        i = this.mArrayNextIndices[i];
      } 
      break;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/ArrayLinkedVariables.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */