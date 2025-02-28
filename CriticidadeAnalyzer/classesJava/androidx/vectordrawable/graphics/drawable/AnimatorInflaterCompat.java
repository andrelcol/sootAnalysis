package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatorInflaterCompat {
  private static Animator createAnimatorFromXml(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, float paramFloat) throws XmlPullParserException, IOException {
    return createAnimatorFromXml(paramContext, paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser), null, 0, paramFloat);
  }
  
  private static Animator createAnimatorFromXml(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, AnimatorSet paramAnimatorSet, int paramInt, float paramFloat) throws XmlPullParserException, IOException {
    byte b;
    int i = paramXmlPullParser.getDepth();
    TypedArray typedArray = null;
    ArrayList<TypedArray> arrayList = null;
    while (true) {
      int j = paramXmlPullParser.next();
      b = 0;
      boolean bool = false;
      if ((j != 3 || paramXmlPullParser.getDepth() > i) && j != 1) {
        ObjectAnimator objectAnimator;
        TypedArray typedArray1;
        if (j != 2)
          continue; 
        String str = paramXmlPullParser.getName();
        if (str.equals("objectAnimator")) {
          objectAnimator = loadObjectAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, paramFloat, paramXmlPullParser);
        } else {
          ValueAnimator valueAnimator;
          if (objectAnimator.equals("animator")) {
            valueAnimator = loadAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, null, paramFloat, paramXmlPullParser);
          } else {
            AnimatorSet animatorSet;
            if (valueAnimator.equals("set")) {
              animatorSet = new AnimatorSet();
              typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_ANIMATOR_SET);
              createAnimatorFromXml(paramContext, paramResources, paramTheme, paramXmlPullParser, paramAttributeSet, animatorSet, TypedArrayUtils.getNamedInt(typedArray, paramXmlPullParser, "ordering", 0, 0), paramFloat);
              typedArray.recycle();
            } else if (animatorSet.equals("propertyValuesHolder")) {
              PropertyValuesHolder[] arrayOfPropertyValuesHolder = loadValues(paramContext, paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser));
              if (arrayOfPropertyValuesHolder != null && typedArray != null && typedArray instanceof ValueAnimator)
                ((ValueAnimator)typedArray).setValues(arrayOfPropertyValuesHolder); 
              bool = true;
              typedArray1 = typedArray;
            } else {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Unknown animator name: ");
              stringBuilder.append(paramXmlPullParser.getName());
              throw new RuntimeException(stringBuilder.toString());
            } 
          } 
        } 
        typedArray = typedArray1;
        if (paramAnimatorSet != null) {
          typedArray = typedArray1;
          if (!bool) {
            ArrayList<TypedArray> arrayList1 = arrayList;
            if (arrayList == null)
              arrayList1 = new ArrayList(); 
            arrayList1.add(typedArray1);
            typedArray = typedArray1;
            arrayList = arrayList1;
          } 
        } 
        continue;
      } 
      break;
    } 
    if (paramAnimatorSet != null && arrayList != null) {
      Animator[] arrayOfAnimator = new Animator[arrayList.size()];
      Iterator<TypedArray> iterator = arrayList.iterator();
      for (byte b1 = b; iterator.hasNext(); b1++)
        arrayOfAnimator[b1] = (Animator)iterator.next(); 
      if (paramInt == 0) {
        paramAnimatorSet.playTogether(arrayOfAnimator);
      } else {
        paramAnimatorSet.playSequentially(arrayOfAnimator);
      } 
    } 
    return (Animator)typedArray;
  }
  
  private static Keyframe createNewKeyframe(Keyframe paramKeyframe, float paramFloat) {
    if (paramKeyframe.getType() == float.class) {
      paramKeyframe = Keyframe.ofFloat(paramFloat);
    } else if (paramKeyframe.getType() == int.class) {
      paramKeyframe = Keyframe.ofInt(paramFloat);
    } else {
      paramKeyframe = Keyframe.ofObject(paramFloat);
    } 
    return paramKeyframe;
  }
  
  private static void distributeKeyframes(Keyframe[] paramArrayOfKeyframe, float paramFloat, int paramInt1, int paramInt2) {
    paramFloat /= (paramInt2 - paramInt1 + 2);
    while (paramInt1 <= paramInt2) {
      paramArrayOfKeyframe[paramInt1].setFraction(paramArrayOfKeyframe[paramInt1 - 1].getFraction() + paramFloat);
      paramInt1++;
    } 
  }
  
  private static PropertyValuesHolder getPVH(TypedArray paramTypedArray, int paramInt1, int paramInt2, int paramInt3, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: iload_2
    //   2: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   5: astore #12
    //   7: aload #12
    //   9: ifnull -> 18
    //   12: iconst_1
    //   13: istore #8
    //   15: goto -> 21
    //   18: iconst_0
    //   19: istore #8
    //   21: iload #8
    //   23: ifeq -> 36
    //   26: aload #12
    //   28: getfield type : I
    //   31: istore #10
    //   33: goto -> 39
    //   36: iconst_0
    //   37: istore #10
    //   39: aload_0
    //   40: iload_3
    //   41: invokevirtual peekValue : (I)Landroid/util/TypedValue;
    //   44: astore #12
    //   46: aload #12
    //   48: ifnull -> 57
    //   51: iconst_1
    //   52: istore #9
    //   54: goto -> 60
    //   57: iconst_0
    //   58: istore #9
    //   60: iload #9
    //   62: ifeq -> 75
    //   65: aload #12
    //   67: getfield type : I
    //   70: istore #11
    //   72: goto -> 78
    //   75: iconst_0
    //   76: istore #11
    //   78: iload_1
    //   79: istore #7
    //   81: iload_1
    //   82: iconst_4
    //   83: if_icmpne -> 121
    //   86: iload #8
    //   88: ifeq -> 99
    //   91: iload #10
    //   93: invokestatic isColorType : (I)Z
    //   96: ifne -> 112
    //   99: iload #9
    //   101: ifeq -> 118
    //   104: iload #11
    //   106: invokestatic isColorType : (I)Z
    //   109: ifeq -> 118
    //   112: iconst_3
    //   113: istore #7
    //   115: goto -> 121
    //   118: iconst_0
    //   119: istore #7
    //   121: iload #7
    //   123: ifne -> 131
    //   126: iconst_1
    //   127: istore_1
    //   128: goto -> 133
    //   131: iconst_0
    //   132: istore_1
    //   133: aconst_null
    //   134: astore #12
    //   136: aconst_null
    //   137: astore #14
    //   139: iload #7
    //   141: iconst_2
    //   142: if_icmpne -> 338
    //   145: aload_0
    //   146: iload_2
    //   147: invokevirtual getString : (I)Ljava/lang/String;
    //   150: astore #13
    //   152: aload_0
    //   153: iload_3
    //   154: invokevirtual getString : (I)Ljava/lang/String;
    //   157: astore #14
    //   159: aload #13
    //   161: invokestatic createNodesFromPathData : (Ljava/lang/String;)[Landroidx/core/graphics/PathParser$PathDataNode;
    //   164: astore #16
    //   166: aload #14
    //   168: invokestatic createNodesFromPathData : (Ljava/lang/String;)[Landroidx/core/graphics/PathParser$PathDataNode;
    //   171: astore #15
    //   173: aload #16
    //   175: ifnonnull -> 186
    //   178: aload #12
    //   180: astore_0
    //   181: aload #15
    //   183: ifnull -> 726
    //   186: aload #16
    //   188: ifnull -> 305
    //   191: new androidx/vectordrawable/graphics/drawable/AnimatorInflaterCompat$PathDataEvaluator
    //   194: dup
    //   195: invokespecial <init> : ()V
    //   198: astore_0
    //   199: aload #15
    //   201: ifnull -> 286
    //   204: aload #16
    //   206: aload #15
    //   208: invokestatic canMorph : ([Landroidx/core/graphics/PathParser$PathDataNode;[Landroidx/core/graphics/PathParser$PathDataNode;)Z
    //   211: ifeq -> 238
    //   214: aload #4
    //   216: aload_0
    //   217: iconst_2
    //   218: anewarray java/lang/Object
    //   221: dup
    //   222: iconst_0
    //   223: aload #16
    //   225: aastore
    //   226: dup
    //   227: iconst_1
    //   228: aload #15
    //   230: aastore
    //   231: invokestatic ofObject : (Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/PropertyValuesHolder;
    //   234: astore_0
    //   235: goto -> 302
    //   238: new java/lang/StringBuilder
    //   241: dup
    //   242: invokespecial <init> : ()V
    //   245: astore_0
    //   246: aload_0
    //   247: ldc ' Can't morph from '
    //   249: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: pop
    //   253: aload_0
    //   254: aload #13
    //   256: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: pop
    //   260: aload_0
    //   261: ldc ' to '
    //   263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: pop
    //   267: aload_0
    //   268: aload #14
    //   270: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: pop
    //   274: new android/view/InflateException
    //   277: dup
    //   278: aload_0
    //   279: invokevirtual toString : ()Ljava/lang/String;
    //   282: invokespecial <init> : (Ljava/lang/String;)V
    //   285: athrow
    //   286: aload #4
    //   288: aload_0
    //   289: iconst_1
    //   290: anewarray java/lang/Object
    //   293: dup
    //   294: iconst_0
    //   295: aload #16
    //   297: aastore
    //   298: invokestatic ofObject : (Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/PropertyValuesHolder;
    //   301: astore_0
    //   302: goto -> 726
    //   305: aload #12
    //   307: astore_0
    //   308: aload #15
    //   310: ifnull -> 726
    //   313: aload #4
    //   315: new androidx/vectordrawable/graphics/drawable/AnimatorInflaterCompat$PathDataEvaluator
    //   318: dup
    //   319: invokespecial <init> : ()V
    //   322: iconst_1
    //   323: anewarray java/lang/Object
    //   326: dup
    //   327: iconst_0
    //   328: aload #15
    //   330: aastore
    //   331: invokestatic ofObject : (Ljava/lang/String;Landroid/animation/TypeEvaluator;[Ljava/lang/Object;)Landroid/animation/PropertyValuesHolder;
    //   334: astore_0
    //   335: goto -> 726
    //   338: iload #7
    //   340: iconst_3
    //   341: if_icmpne -> 352
    //   344: invokestatic getInstance : ()Landroidx/vectordrawable/graphics/drawable/ArgbEvaluator;
    //   347: astore #13
    //   349: goto -> 355
    //   352: aconst_null
    //   353: astore #13
    //   355: iload_1
    //   356: ifeq -> 503
    //   359: iload #8
    //   361: ifeq -> 458
    //   364: iload #10
    //   366: iconst_5
    //   367: if_icmpne -> 381
    //   370: aload_0
    //   371: iload_2
    //   372: fconst_0
    //   373: invokevirtual getDimension : (IF)F
    //   376: fstore #5
    //   378: goto -> 389
    //   381: aload_0
    //   382: iload_2
    //   383: fconst_0
    //   384: invokevirtual getFloat : (IF)F
    //   387: fstore #5
    //   389: iload #9
    //   391: ifeq -> 441
    //   394: iload #11
    //   396: iconst_5
    //   397: if_icmpne -> 411
    //   400: aload_0
    //   401: iload_3
    //   402: fconst_0
    //   403: invokevirtual getDimension : (IF)F
    //   406: fstore #6
    //   408: goto -> 419
    //   411: aload_0
    //   412: iload_3
    //   413: fconst_0
    //   414: invokevirtual getFloat : (IF)F
    //   417: fstore #6
    //   419: aload #4
    //   421: iconst_2
    //   422: newarray float
    //   424: dup
    //   425: iconst_0
    //   426: fload #5
    //   428: fastore
    //   429: dup
    //   430: iconst_1
    //   431: fload #6
    //   433: fastore
    //   434: invokestatic ofFloat : (Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;
    //   437: astore_0
    //   438: goto -> 497
    //   441: aload #4
    //   443: iconst_1
    //   444: newarray float
    //   446: dup
    //   447: iconst_0
    //   448: fload #5
    //   450: fastore
    //   451: invokestatic ofFloat : (Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;
    //   454: astore_0
    //   455: goto -> 497
    //   458: iload #11
    //   460: iconst_5
    //   461: if_icmpne -> 475
    //   464: aload_0
    //   465: iload_3
    //   466: fconst_0
    //   467: invokevirtual getDimension : (IF)F
    //   470: fstore #5
    //   472: goto -> 483
    //   475: aload_0
    //   476: iload_3
    //   477: fconst_0
    //   478: invokevirtual getFloat : (IF)F
    //   481: fstore #5
    //   483: aload #4
    //   485: iconst_1
    //   486: newarray float
    //   488: dup
    //   489: iconst_0
    //   490: fload #5
    //   492: fastore
    //   493: invokestatic ofFloat : (Ljava/lang/String;[F)Landroid/animation/PropertyValuesHolder;
    //   496: astore_0
    //   497: aload_0
    //   498: astore #12
    //   500: goto -> 700
    //   503: iload #8
    //   505: ifeq -> 635
    //   508: iload #10
    //   510: iconst_5
    //   511: if_icmpne -> 525
    //   514: aload_0
    //   515: iload_2
    //   516: fconst_0
    //   517: invokevirtual getDimension : (IF)F
    //   520: f2i
    //   521: istore_1
    //   522: goto -> 550
    //   525: iload #10
    //   527: invokestatic isColorType : (I)Z
    //   530: ifeq -> 543
    //   533: aload_0
    //   534: iload_2
    //   535: iconst_0
    //   536: invokevirtual getColor : (II)I
    //   539: istore_1
    //   540: goto -> 550
    //   543: aload_0
    //   544: iload_2
    //   545: iconst_0
    //   546: invokevirtual getInt : (II)I
    //   549: istore_1
    //   550: iload #9
    //   552: ifeq -> 618
    //   555: iload #11
    //   557: iconst_5
    //   558: if_icmpne -> 572
    //   561: aload_0
    //   562: iload_3
    //   563: fconst_0
    //   564: invokevirtual getDimension : (IF)F
    //   567: f2i
    //   568: istore_2
    //   569: goto -> 597
    //   572: iload #11
    //   574: invokestatic isColorType : (I)Z
    //   577: ifeq -> 590
    //   580: aload_0
    //   581: iload_3
    //   582: iconst_0
    //   583: invokevirtual getColor : (II)I
    //   586: istore_2
    //   587: goto -> 597
    //   590: aload_0
    //   591: iload_3
    //   592: iconst_0
    //   593: invokevirtual getInt : (II)I
    //   596: istore_2
    //   597: aload #4
    //   599: iconst_2
    //   600: newarray int
    //   602: dup
    //   603: iconst_0
    //   604: iload_1
    //   605: iastore
    //   606: dup
    //   607: iconst_1
    //   608: iload_2
    //   609: iastore
    //   610: invokestatic ofInt : (Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;
    //   613: astore #12
    //   615: goto -> 700
    //   618: aload #4
    //   620: iconst_1
    //   621: newarray int
    //   623: dup
    //   624: iconst_0
    //   625: iload_1
    //   626: iastore
    //   627: invokestatic ofInt : (Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;
    //   630: astore #12
    //   632: goto -> 700
    //   635: aload #14
    //   637: astore #12
    //   639: iload #9
    //   641: ifeq -> 700
    //   644: iload #11
    //   646: iconst_5
    //   647: if_icmpne -> 661
    //   650: aload_0
    //   651: iload_3
    //   652: fconst_0
    //   653: invokevirtual getDimension : (IF)F
    //   656: f2i
    //   657: istore_1
    //   658: goto -> 686
    //   661: iload #11
    //   663: invokestatic isColorType : (I)Z
    //   666: ifeq -> 679
    //   669: aload_0
    //   670: iload_3
    //   671: iconst_0
    //   672: invokevirtual getColor : (II)I
    //   675: istore_1
    //   676: goto -> 686
    //   679: aload_0
    //   680: iload_3
    //   681: iconst_0
    //   682: invokevirtual getInt : (II)I
    //   685: istore_1
    //   686: aload #4
    //   688: iconst_1
    //   689: newarray int
    //   691: dup
    //   692: iconst_0
    //   693: iload_1
    //   694: iastore
    //   695: invokestatic ofInt : (Ljava/lang/String;[I)Landroid/animation/PropertyValuesHolder;
    //   698: astore #12
    //   700: aload #12
    //   702: astore_0
    //   703: aload #12
    //   705: ifnull -> 726
    //   708: aload #12
    //   710: astore_0
    //   711: aload #13
    //   713: ifnull -> 726
    //   716: aload #12
    //   718: aload #13
    //   720: invokevirtual setEvaluator : (Landroid/animation/TypeEvaluator;)V
    //   723: aload #12
    //   725: astore_0
    //   726: aload_0
    //   727: areturn
  }
  
  private static int inferValueTypeFromValues(TypedArray paramTypedArray, int paramInt1, int paramInt2) {
    boolean bool1;
    TypedValue typedValue2 = paramTypedArray.peekValue(paramInt1);
    int i = 1;
    boolean bool2 = false;
    if (typedValue2 != null) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    if (paramInt1 != 0) {
      bool1 = typedValue2.type;
    } else {
      bool1 = false;
    } 
    TypedValue typedValue1 = paramTypedArray.peekValue(paramInt2);
    if (typedValue1 != null) {
      paramInt2 = i;
    } else {
      paramInt2 = 0;
    } 
    if (paramInt2 != 0) {
      i = typedValue1.type;
    } else {
      i = 0;
    } 
    if (paramInt1 == 0 || !isColorType(bool1)) {
      paramInt1 = bool2;
      if (paramInt2 != 0) {
        paramInt1 = bool2;
        if (isColorType(i))
          return 3; 
      } 
      return paramInt1;
    } 
    return 3;
  }
  
  private static int inferValueTypeOfKeyframe(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser) {
    boolean bool;
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_KEYFRAME);
    byte b2 = 0;
    TypedValue typedValue = TypedArrayUtils.peekNamedValue(typedArray, paramXmlPullParser, "value", 0);
    if (typedValue != null) {
      bool = true;
    } else {
      bool = false;
    } 
    byte b1 = b2;
    if (bool) {
      b1 = b2;
      if (isColorType(typedValue.type))
        b1 = 3; 
    } 
    typedArray.recycle();
    return b1;
  }
  
  private static boolean isColorType(int paramInt) {
    boolean bool;
    if (paramInt >= 28 && paramInt <= 31) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static Animator loadAnimator(Context paramContext, int paramInt) throws Resources.NotFoundException {
    Animator animator;
    if (Build.VERSION.SDK_INT >= 24) {
      animator = AnimatorInflater.loadAnimator(paramContext, paramInt);
    } else {
      animator = loadAnimator((Context)animator, animator.getResources(), animator.getTheme(), paramInt);
    } 
    return animator;
  }
  
  public static Animator loadAnimator(Context paramContext, Resources paramResources, Resources.Theme paramTheme, int paramInt) throws Resources.NotFoundException {
    return loadAnimator(paramContext, paramResources, paramTheme, paramInt, 1.0F);
  }
  
  public static Animator loadAnimator(Context paramContext, Resources paramResources, Resources.Theme paramTheme, int paramInt, float paramFloat) throws Resources.NotFoundException {
    XmlResourceParser xmlResourceParser2 = null;
    XmlResourceParser xmlResourceParser3 = null;
    XmlResourceParser xmlResourceParser1 = null;
    try {
      XmlResourceParser xmlResourceParser = paramResources.getAnimation(paramInt);
      xmlResourceParser1 = xmlResourceParser;
      xmlResourceParser2 = xmlResourceParser;
      xmlResourceParser3 = xmlResourceParser;
      Animator animator = createAnimatorFromXml(paramContext, paramResources, paramTheme, (XmlPullParser)xmlResourceParser, paramFloat);
      if (xmlResourceParser != null)
        xmlResourceParser.close(); 
      return animator;
    } catch (XmlPullParserException xmlPullParserException) {
      xmlResourceParser1 = xmlResourceParser3;
      Resources.NotFoundException notFoundException = new Resources.NotFoundException();
      xmlResourceParser1 = xmlResourceParser3;
      StringBuilder stringBuilder = new StringBuilder();
      xmlResourceParser1 = xmlResourceParser3;
      this();
      xmlResourceParser1 = xmlResourceParser3;
      stringBuilder.append("Can't load animation resource ID #0x");
      xmlResourceParser1 = xmlResourceParser3;
      stringBuilder.append(Integer.toHexString(paramInt));
      xmlResourceParser1 = xmlResourceParser3;
      this(stringBuilder.toString());
      xmlResourceParser1 = xmlResourceParser3;
      notFoundException.initCause((Throwable)xmlPullParserException);
      xmlResourceParser1 = xmlResourceParser3;
      throw notFoundException;
    } catch (IOException iOException) {
      xmlResourceParser1 = xmlResourceParser2;
      Resources.NotFoundException notFoundException = new Resources.NotFoundException();
      xmlResourceParser1 = xmlResourceParser2;
      StringBuilder stringBuilder = new StringBuilder();
      xmlResourceParser1 = xmlResourceParser2;
      this();
      xmlResourceParser1 = xmlResourceParser2;
      stringBuilder.append("Can't load animation resource ID #0x");
      xmlResourceParser1 = xmlResourceParser2;
      stringBuilder.append(Integer.toHexString(paramInt));
      xmlResourceParser1 = xmlResourceParser2;
      this(stringBuilder.toString());
      xmlResourceParser1 = xmlResourceParser2;
      notFoundException.initCause(iOException);
      xmlResourceParser1 = xmlResourceParser2;
      throw notFoundException;
    } finally {}
    if (xmlResourceParser1 != null)
      xmlResourceParser1.close(); 
    throw paramContext;
  }
  
  private static ValueAnimator loadAnimator(Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, ValueAnimator paramValueAnimator, float paramFloat, XmlPullParser paramXmlPullParser) throws Resources.NotFoundException {
    TypedArray typedArray2 = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_ANIMATOR);
    TypedArray typedArray1 = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
    ValueAnimator valueAnimator = paramValueAnimator;
    if (paramValueAnimator == null)
      valueAnimator = new ValueAnimator(); 
    parseAnimatorFromTypeArray(valueAnimator, typedArray2, typedArray1, paramFloat, paramXmlPullParser);
    int i = TypedArrayUtils.getNamedResourceId(typedArray2, paramXmlPullParser, "interpolator", 0, 0);
    if (i > 0)
      valueAnimator.setInterpolator((TimeInterpolator)AnimationUtilsCompat.loadInterpolator(paramContext, i)); 
    typedArray2.recycle();
    if (typedArray1 != null)
      typedArray1.recycle(); 
    return valueAnimator;
  }
  
  private static Keyframe loadKeyframe(Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int paramInt, XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    Keyframe keyframe;
    boolean bool;
    TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_KEYFRAME);
    float f = TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "fraction", 3, -1.0F);
    TypedValue typedValue = TypedArrayUtils.peekNamedValue(typedArray, paramXmlPullParser, "value", 0);
    if (typedValue != null) {
      bool = true;
    } else {
      bool = false;
    } 
    int i = paramInt;
    if (paramInt == 4)
      if (bool && isColorType(typedValue.type)) {
        i = 3;
      } else {
        i = 0;
      }  
    if (bool) {
      if (i != 0) {
        if (i != 1 && i != 3) {
          typedValue = null;
        } else {
          keyframe = Keyframe.ofInt(f, TypedArrayUtils.getNamedInt(typedArray, paramXmlPullParser, "value", 0, 0));
        } 
      } else {
        keyframe = Keyframe.ofFloat(f, TypedArrayUtils.getNamedFloat(typedArray, paramXmlPullParser, "value", 0, 0.0F));
      } 
    } else if (i == 0) {
      keyframe = Keyframe.ofFloat(f);
    } else {
      keyframe = Keyframe.ofInt(f);
    } 
    paramInt = TypedArrayUtils.getNamedResourceId(typedArray, paramXmlPullParser, "interpolator", 1, 0);
    if (paramInt > 0)
      keyframe.setInterpolator((TimeInterpolator)AnimationUtilsCompat.loadInterpolator(paramContext, paramInt)); 
    typedArray.recycle();
    return keyframe;
  }
  
  private static ObjectAnimator loadObjectAnimator(Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, float paramFloat, XmlPullParser paramXmlPullParser) throws Resources.NotFoundException {
    ObjectAnimator objectAnimator = new ObjectAnimator();
    loadAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, (ValueAnimator)objectAnimator, paramFloat, paramXmlPullParser);
    return objectAnimator;
  }
  
  private static PropertyValuesHolder loadPvh(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, String paramString, int paramInt) throws XmlPullParserException, IOException {
    PropertyValuesHolder propertyValuesHolder;
    Context context = null;
    ArrayList<Keyframe> arrayList = null;
    int i = paramInt;
    while (true) {
      paramInt = paramXmlPullParser.next();
      if (paramInt != 3 && paramInt != 1) {
        if (paramXmlPullParser.getName().equals("keyframe")) {
          paramInt = i;
          if (i == 4)
            paramInt = inferValueTypeOfKeyframe(paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser), paramXmlPullParser); 
          Keyframe keyframe = loadKeyframe(paramContext, paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser), paramInt, paramXmlPullParser);
          ArrayList<Keyframe> arrayList1 = arrayList;
          if (keyframe != null) {
            arrayList1 = arrayList;
            if (arrayList == null)
              arrayList1 = new ArrayList(); 
            arrayList1.add(keyframe);
          } 
          paramXmlPullParser.next();
          i = paramInt;
          arrayList = arrayList1;
        } 
        continue;
      } 
      break;
    } 
    paramContext = context;
    if (arrayList != null) {
      int j = arrayList.size();
      paramContext = context;
      if (j > 0) {
        int k = 0;
        Keyframe keyframe2 = arrayList.get(0);
        Keyframe keyframe1 = arrayList.get(j - 1);
        float f = keyframe1.getFraction();
        paramInt = j;
        if (f < 1.0F)
          if (f < 0.0F) {
            keyframe1.setFraction(1.0F);
            paramInt = j;
          } else {
            arrayList.add(arrayList.size(), createNewKeyframe(keyframe1, 1.0F));
            paramInt = j + 1;
          }  
        f = keyframe2.getFraction();
        j = paramInt;
        if (f != 0.0F)
          if (f < 0.0F) {
            keyframe2.setFraction(0.0F);
            j = paramInt;
          } else {
            arrayList.add(0, createNewKeyframe(keyframe2, 0.0F));
            j = paramInt + 1;
          }  
        Keyframe[] arrayOfKeyframe = new Keyframe[j];
        arrayList.toArray(arrayOfKeyframe);
        for (paramInt = k; paramInt < j; paramInt++) {
          keyframe2 = arrayOfKeyframe[paramInt];
          if (keyframe2.getFraction() < 0.0F)
            if (paramInt == 0) {
              keyframe2.setFraction(0.0F);
            } else {
              int m = j - 1;
              if (paramInt == m) {
                keyframe2.setFraction(1.0F);
              } else {
                k = paramInt + 1;
                int n = paramInt;
                while (k < m && arrayOfKeyframe[k].getFraction() < 0.0F) {
                  n = k;
                  k++;
                } 
                distributeKeyframes(arrayOfKeyframe, arrayOfKeyframe[n + 1].getFraction() - arrayOfKeyframe[paramInt - 1].getFraction(), paramInt, n);
              } 
            }  
        } 
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofKeyframe(paramString, arrayOfKeyframe);
        propertyValuesHolder = propertyValuesHolder1;
        if (i == 3) {
          propertyValuesHolder1.setEvaluator(ArgbEvaluator.getInstance());
          propertyValuesHolder = propertyValuesHolder1;
        } 
      } 
    } 
    return propertyValuesHolder;
  }
  
  private static PropertyValuesHolder[] loadValues(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet) throws XmlPullParserException, IOException {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder;
    int i;
    ArrayList<PropertyValuesHolder> arrayList;
    Context context = null;
    PropertyValuesHolder propertyValuesHolder = null;
    while (true) {
      int j = paramXmlPullParser.getEventType();
      i = 0;
      if (j != 3 && j != 1) {
        if (j != 2) {
          paramXmlPullParser.next();
          continue;
        } 
        if (paramXmlPullParser.getName().equals("propertyValuesHolder")) {
          ArrayList<PropertyValuesHolder> arrayList1;
          TypedArray typedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
          String str = TypedArrayUtils.getNamedString(typedArray, paramXmlPullParser, "propertyName", 3);
          i = TypedArrayUtils.getNamedInt(typedArray, paramXmlPullParser, "valueType", 2, 4);
          PropertyValuesHolder propertyValuesHolder1 = loadPvh(paramContext, paramResources, paramTheme, paramXmlPullParser, str, i);
          PropertyValuesHolder propertyValuesHolder2 = propertyValuesHolder1;
          if (propertyValuesHolder1 == null)
            propertyValuesHolder2 = getPVH(typedArray, i, 0, 1, str); 
          propertyValuesHolder1 = propertyValuesHolder;
          if (propertyValuesHolder2 != null) {
            propertyValuesHolder1 = propertyValuesHolder;
            if (propertyValuesHolder == null)
              arrayList1 = new ArrayList(); 
            arrayList1.add(propertyValuesHolder2);
          } 
          typedArray.recycle();
          arrayList = arrayList1;
        } 
        paramXmlPullParser.next();
        continue;
      } 
      break;
    } 
    paramContext = context;
    if (arrayList != null) {
      int j = arrayList.size();
      PropertyValuesHolder[] arrayOfPropertyValuesHolder1 = new PropertyValuesHolder[j];
      while (true) {
        arrayOfPropertyValuesHolder = arrayOfPropertyValuesHolder1;
        if (i < j) {
          arrayOfPropertyValuesHolder1[i] = arrayList.get(i);
          i++;
          continue;
        } 
        break;
      } 
    } 
    return arrayOfPropertyValuesHolder;
  }
  
  private static void parseAnimatorFromTypeArray(ValueAnimator paramValueAnimator, TypedArray paramTypedArray1, TypedArray paramTypedArray2, float paramFloat, XmlPullParser paramXmlPullParser) {
    long l1 = TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "duration", 1, 300);
    long l2 = TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "startOffset", 2, 0);
    int j = TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "valueType", 7, 4);
    int i = j;
    if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "valueFrom")) {
      i = j;
      if (TypedArrayUtils.hasAttribute(paramXmlPullParser, "valueTo")) {
        int k = j;
        if (j == 4)
          k = inferValueTypeFromValues(paramTypedArray1, 5, 6); 
        PropertyValuesHolder propertyValuesHolder = getPVH(paramTypedArray1, k, 5, 6, "");
        i = k;
        if (propertyValuesHolder != null) {
          paramValueAnimator.setValues(new PropertyValuesHolder[] { propertyValuesHolder });
          i = k;
        } 
      } 
    } 
    paramValueAnimator.setDuration(l1);
    paramValueAnimator.setStartDelay(l2);
    paramValueAnimator.setRepeatCount(TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "repeatCount", 3, 0));
    paramValueAnimator.setRepeatMode(TypedArrayUtils.getNamedInt(paramTypedArray1, paramXmlPullParser, "repeatMode", 4, 1));
    if (paramTypedArray2 != null)
      setupObjectAnimator(paramValueAnimator, paramTypedArray2, i, paramFloat, paramXmlPullParser); 
  }
  
  private static void setupObjectAnimator(ValueAnimator paramValueAnimator, TypedArray paramTypedArray, int paramInt, float paramFloat, XmlPullParser paramXmlPullParser) {
    // Byte code:
    //   0: aload_0
    //   1: checkcast android/animation/ObjectAnimator
    //   4: astore #5
    //   6: aload_1
    //   7: aload #4
    //   9: ldc_w 'pathData'
    //   12: iconst_1
    //   13: invokestatic getNamedString : (Landroid/content/res/TypedArray;Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)Ljava/lang/String;
    //   16: astore #6
    //   18: aload #6
    //   20: ifnull -> 121
    //   23: aload_1
    //   24: aload #4
    //   26: ldc_w 'propertyXName'
    //   29: iconst_2
    //   30: invokestatic getNamedString : (Landroid/content/res/TypedArray;Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)Ljava/lang/String;
    //   33: astore_0
    //   34: aload_1
    //   35: aload #4
    //   37: ldc_w 'propertyYName'
    //   40: iconst_3
    //   41: invokestatic getNamedString : (Landroid/content/res/TypedArray;Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)Ljava/lang/String;
    //   44: astore #4
    //   46: iload_2
    //   47: iconst_2
    //   48: if_icmpeq -> 51
    //   51: aload_0
    //   52: ifnonnull -> 100
    //   55: aload #4
    //   57: ifnull -> 63
    //   60: goto -> 100
    //   63: new java/lang/StringBuilder
    //   66: dup
    //   67: invokespecial <init> : ()V
    //   70: astore_0
    //   71: aload_0
    //   72: aload_1
    //   73: invokevirtual getPositionDescription : ()Ljava/lang/String;
    //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload_0
    //   81: ldc_w ' propertyXName or propertyYName is needed for PathData'
    //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: new android/view/InflateException
    //   91: dup
    //   92: aload_0
    //   93: invokevirtual toString : ()Ljava/lang/String;
    //   96: invokespecial <init> : (Ljava/lang/String;)V
    //   99: athrow
    //   100: aload #6
    //   102: invokestatic createPathFromPathData : (Ljava/lang/String;)Landroid/graphics/Path;
    //   105: aload #5
    //   107: fload_3
    //   108: ldc_w 0.5
    //   111: fmul
    //   112: aload_0
    //   113: aload #4
    //   115: invokestatic setupPathMotion : (Landroid/graphics/Path;Landroid/animation/ObjectAnimator;FLjava/lang/String;Ljava/lang/String;)V
    //   118: goto -> 136
    //   121: aload #5
    //   123: aload_1
    //   124: aload #4
    //   126: ldc_w 'propertyName'
    //   129: iconst_0
    //   130: invokestatic getNamedString : (Landroid/content/res/TypedArray;Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;I)Ljava/lang/String;
    //   133: invokevirtual setPropertyName : (Ljava/lang/String;)V
    //   136: return
  }
  
  private static void setupPathMotion(Path paramPath, ObjectAnimator paramObjectAnimator, float paramFloat, String paramString1, String paramString2) {
    PathMeasure pathMeasure = new PathMeasure(paramPath, false);
    ArrayList<Float> arrayList = new ArrayList();
    arrayList.add(Float.valueOf(0.0F));
    float f = 0.0F;
    while (true) {
      float f1 = f + pathMeasure.getLength();
      arrayList.add(Float.valueOf(f1));
      f = f1;
      if (!pathMeasure.nextContour()) {
        PathMeasure pathMeasure1 = new PathMeasure(paramPath, false);
        int j = Math.min(100, (int)(f1 / paramFloat) + 1);
        float[] arrayOfFloat3 = new float[j];
        float[] arrayOfFloat2 = new float[j];
        float[] arrayOfFloat1 = new float[2];
        f = f1 / (j - 1);
        byte b = 0;
        paramFloat = 0.0F;
        int i = 0;
        while (true) {
          PropertyValuesHolder propertyValuesHolder;
          pathMeasure = null;
          if (b < j) {
            pathMeasure1.getPosTan(paramFloat - ((Float)arrayList.get(i)).floatValue(), arrayOfFloat1, null);
            arrayOfFloat3[b] = arrayOfFloat1[0];
            arrayOfFloat2[b] = arrayOfFloat1[1];
            paramFloat += f;
            int m = i + 1;
            int k = i;
            if (m < arrayList.size()) {
              k = i;
              if (paramFloat > ((Float)arrayList.get(m)).floatValue()) {
                pathMeasure1.nextContour();
                k = m;
              } 
            } 
            b++;
            i = k;
            continue;
          } 
          if (paramString1 != null) {
            PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat(paramString1, arrayOfFloat3);
          } else {
            arrayOfFloat1 = null;
          } 
          PathMeasure pathMeasure2 = pathMeasure;
          if (paramString2 != null)
            propertyValuesHolder = PropertyValuesHolder.ofFloat(paramString2, arrayOfFloat2); 
          if (arrayOfFloat1 == null) {
            paramObjectAnimator.setValues(new PropertyValuesHolder[] { propertyValuesHolder });
          } else if (propertyValuesHolder == null) {
            paramObjectAnimator.setValues(new PropertyValuesHolder[] { (PropertyValuesHolder)arrayOfFloat1 });
          } else {
            paramObjectAnimator.setValues(new PropertyValuesHolder[] { (PropertyValuesHolder)arrayOfFloat1, propertyValuesHolder });
          } 
          return;
        } 
        break;
      } 
    } 
  }
  
  private static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {
    private PathParser.PathDataNode[] mNodeArray;
    
    public PathParser.PathDataNode[] evaluate(float param1Float, PathParser.PathDataNode[] param1ArrayOfPathDataNode1, PathParser.PathDataNode[] param1ArrayOfPathDataNode2) {
      if (PathParser.canMorph(param1ArrayOfPathDataNode1, param1ArrayOfPathDataNode2)) {
        PathParser.PathDataNode[] arrayOfPathDataNode = this.mNodeArray;
        if (arrayOfPathDataNode == null || !PathParser.canMorph(arrayOfPathDataNode, param1ArrayOfPathDataNode1))
          this.mNodeArray = PathParser.deepCopyNodes(param1ArrayOfPathDataNode1); 
        for (byte b = 0; b < param1ArrayOfPathDataNode1.length; b++)
          this.mNodeArray[b].interpolatePathDataNode(param1ArrayOfPathDataNode1[b], param1ArrayOfPathDataNode2[b], param1Float); 
        return this.mNodeArray;
      } 
      throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/vectordrawable/graphics/drawable/AnimatorInflaterCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */