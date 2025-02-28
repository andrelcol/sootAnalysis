package androidx.core.graphics;

import android.graphics.Path;
import java.util.ArrayList;

public class PathParser {
  private static void addNode(ArrayList<PathDataNode> paramArrayList, char paramChar, float[] paramArrayOffloat) {
    paramArrayList.add(new PathDataNode(paramChar, paramArrayOffloat));
  }
  
  public static boolean canMorph(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2) {
    if (paramArrayOfPathDataNode1 == null || paramArrayOfPathDataNode2 == null)
      return false; 
    if (paramArrayOfPathDataNode1.length != paramArrayOfPathDataNode2.length)
      return false; 
    for (byte b = 0; b < paramArrayOfPathDataNode1.length; b++) {
      if ((paramArrayOfPathDataNode1[b]).mType != (paramArrayOfPathDataNode2[b]).mType || (paramArrayOfPathDataNode1[b]).mParams.length != (paramArrayOfPathDataNode2[b]).mParams.length)
        return false; 
    } 
    return true;
  }
  
  static float[] copyOfRange(float[] paramArrayOffloat, int paramInt1, int paramInt2) {
    if (paramInt1 <= paramInt2) {
      int i = paramArrayOffloat.length;
      if (paramInt1 >= 0 && paramInt1 <= i) {
        paramInt2 -= paramInt1;
        i = Math.min(paramInt2, i - paramInt1);
        float[] arrayOfFloat = new float[paramInt2];
        System.arraycopy(paramArrayOffloat, paramInt1, arrayOfFloat, 0, i);
        return arrayOfFloat;
      } 
      throw new ArrayIndexOutOfBoundsException();
    } 
    throw new IllegalArgumentException();
  }
  
  public static PathDataNode[] createNodesFromPathData(String paramString) {
    if (paramString == null)
      return null; 
    ArrayList<PathDataNode> arrayList = new ArrayList();
    int j = 1;
    int i = 0;
    while (j < paramString.length()) {
      j = nextStart(paramString, j);
      String str = paramString.substring(i, j).trim();
      if (str.length() > 0) {
        float[] arrayOfFloat = getFloats(str);
        addNode(arrayList, str.charAt(0), arrayOfFloat);
      } 
      i = j;
      j++;
    } 
    if (j - i == 1 && i < paramString.length())
      addNode(arrayList, paramString.charAt(i), new float[0]); 
    return arrayList.<PathDataNode>toArray(new PathDataNode[arrayList.size()]);
  }
  
  public static Path createPathFromPathData(String paramString) {
    Path path = new Path();
    PathDataNode[] arrayOfPathDataNode = createNodesFromPathData(paramString);
    if (arrayOfPathDataNode != null)
      try {
        PathDataNode.nodesToPath(arrayOfPathDataNode, path);
        return path;
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error in parsing ");
        stringBuilder.append(paramString);
        throw new RuntimeException(stringBuilder.toString(), runtimeException);
      }  
    return null;
  }
  
  public static PathDataNode[] deepCopyNodes(PathDataNode[] paramArrayOfPathDataNode) {
    if (paramArrayOfPathDataNode == null)
      return null; 
    PathDataNode[] arrayOfPathDataNode = new PathDataNode[paramArrayOfPathDataNode.length];
    for (byte b = 0; b < paramArrayOfPathDataNode.length; b++)
      arrayOfPathDataNode[b] = new PathDataNode(paramArrayOfPathDataNode[b]); 
    return arrayOfPathDataNode;
  }
  
  private static void extract(String paramString, int paramInt, ExtractFloatResult paramExtractFloatResult) {
    paramExtractFloatResult.mEndWithNegOrDot = false;
    int i = paramInt;
    boolean bool1 = false;
    boolean bool3 = false;
    boolean bool2 = false;
    while (i < paramString.length()) {
      char c = paramString.charAt(i);
      if (c != ' ') {
        if (c != 'E' && c != 'e') {
          switch (c) {
            default:
              bool1 = false;
              break;
            case '.':
              if (!bool3) {
                bool1 = false;
                bool3 = true;
                break;
              } 
              paramExtractFloatResult.mEndWithNegOrDot = true;
            case '-':
            
            case ',':
              bool1 = false;
              bool2 = true;
              break;
          } 
        } else {
          bool1 = true;
        } 
        if (bool2)
          break; 
        continue;
      } 
      i++;
    } 
    paramExtractFloatResult.mEndPosition = i;
  }
  
  private static float[] getFloats(String paramString) {
    if (paramString.charAt(0) == 'z' || paramString.charAt(0) == 'Z')
      return new float[0]; 
    try {
      float[] arrayOfFloat = new float[paramString.length()];
      ExtractFloatResult extractFloatResult = new ExtractFloatResult();
      this();
      int k = paramString.length();
      int i = 1;
      int j;
      for (j = 0; i < k; j = m) {
        extract(paramString, i, extractFloatResult);
        int n = extractFloatResult.mEndPosition;
        int m = j;
        if (i < n) {
          arrayOfFloat[j] = Float.parseFloat(paramString.substring(i, n));
          m = j + 1;
        } 
        if (extractFloatResult.mEndWithNegOrDot) {
          i = n;
          j = m;
          continue;
        } 
        i = n + 1;
      } 
      return copyOfRange(arrayOfFloat, 0, j);
    } catch (NumberFormatException numberFormatException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("error in parsing \"");
      stringBuilder.append(paramString);
      stringBuilder.append("\"");
      throw new RuntimeException(stringBuilder.toString(), numberFormatException);
    } 
  }
  
  private static int nextStart(String paramString, int paramInt) {
    while (paramInt < paramString.length()) {
      char c = paramString.charAt(paramInt);
      if (((c - 65) * (c - 90) <= 0 || (c - 97) * (c - 122) <= 0) && c != 'e' && c != 'E')
        return paramInt; 
      paramInt++;
    } 
    return paramInt;
  }
  
  public static void updateNodes(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2) {
    for (byte b = 0; b < paramArrayOfPathDataNode2.length; b++) {
      (paramArrayOfPathDataNode1[b]).mType = (paramArrayOfPathDataNode2[b]).mType;
      for (byte b1 = 0; b1 < (paramArrayOfPathDataNode2[b]).mParams.length; b1++)
        (paramArrayOfPathDataNode1[b]).mParams[b1] = (paramArrayOfPathDataNode2[b]).mParams[b1]; 
    } 
  }
  
  private static class ExtractFloatResult {
    int mEndPosition;
    
    boolean mEndWithNegOrDot;
  }
  
  public static class PathDataNode {
    public float[] mParams;
    
    public char mType;
    
    PathDataNode(char param1Char, float[] param1ArrayOffloat) {
      this.mType = param1Char;
      this.mParams = param1ArrayOffloat;
    }
    
    PathDataNode(PathDataNode param1PathDataNode) {
      this.mType = param1PathDataNode.mType;
      float[] arrayOfFloat = param1PathDataNode.mParams;
      this.mParams = PathParser.copyOfRange(arrayOfFloat, 0, arrayOfFloat.length);
    }
    
    private static void addCommand(Path param1Path, float[] param1ArrayOffloat1, char param1Char1, char param1Char2, float[] param1ArrayOffloat2) {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: faload
      //   3: fstore #12
      //   5: aload_1
      //   6: iconst_1
      //   7: faload
      //   8: fstore #13
      //   10: aload_1
      //   11: iconst_2
      //   12: faload
      //   13: fstore #14
      //   15: aload_1
      //   16: iconst_3
      //   17: faload
      //   18: fstore #11
      //   20: aload_1
      //   21: iconst_4
      //   22: faload
      //   23: fstore #10
      //   25: aload_1
      //   26: iconst_5
      //   27: faload
      //   28: fstore #9
      //   30: fload #12
      //   32: fstore #5
      //   34: fload #13
      //   36: fstore #6
      //   38: fload #14
      //   40: fstore #7
      //   42: fload #11
      //   44: fstore #8
      //   46: iload_3
      //   47: lookupswitch default -> 216, 65 -> 336, 67 -> 313, 72 -> 291, 76 -> 232, 77 -> 232, 81 -> 269, 83 -> 269, 84 -> 232, 86 -> 291, 90 -> 238, 97 -> 336, 99 -> 313, 104 -> 291, 108 -> 232, 109 -> 232, 113 -> 269, 115 -> 269, 116 -> 232, 118 -> 291, 122 -> 238
      //   216: fload #11
      //   218: fstore #8
      //   220: fload #14
      //   222: fstore #7
      //   224: fload #13
      //   226: fstore #6
      //   228: fload #12
      //   230: fstore #5
      //   232: iconst_2
      //   233: istore #15
      //   235: goto -> 356
      //   238: aload_0
      //   239: invokevirtual close : ()V
      //   242: aload_0
      //   243: fload #10
      //   245: fload #9
      //   247: invokevirtual moveTo : (FF)V
      //   250: fload #10
      //   252: fstore #5
      //   254: fload #5
      //   256: fstore #7
      //   258: fload #9
      //   260: fstore #6
      //   262: fload #6
      //   264: fstore #8
      //   266: goto -> 232
      //   269: iconst_4
      //   270: istore #15
      //   272: fload #12
      //   274: fstore #5
      //   276: fload #13
      //   278: fstore #6
      //   280: fload #14
      //   282: fstore #7
      //   284: fload #11
      //   286: fstore #8
      //   288: goto -> 356
      //   291: iconst_1
      //   292: istore #15
      //   294: fload #12
      //   296: fstore #5
      //   298: fload #13
      //   300: fstore #6
      //   302: fload #14
      //   304: fstore #7
      //   306: fload #11
      //   308: fstore #8
      //   310: goto -> 356
      //   313: bipush #6
      //   315: istore #15
      //   317: fload #12
      //   319: fstore #5
      //   321: fload #13
      //   323: fstore #6
      //   325: fload #14
      //   327: fstore #7
      //   329: fload #11
      //   331: fstore #8
      //   333: goto -> 356
      //   336: bipush #7
      //   338: istore #15
      //   340: fload #11
      //   342: fstore #8
      //   344: fload #14
      //   346: fstore #7
      //   348: fload #13
      //   350: fstore #6
      //   352: fload #12
      //   354: fstore #5
      //   356: iconst_0
      //   357: istore #17
      //   359: iload_2
      //   360: istore #16
      //   362: iload #17
      //   364: istore_2
      //   365: iload_2
      //   366: aload #4
      //   368: arraylength
      //   369: if_icmpge -> 2100
      //   372: iload_3
      //   373: bipush #65
      //   375: if_icmpeq -> 1956
      //   378: iload_3
      //   379: bipush #67
      //   381: if_icmpeq -> 1849
      //   384: iload_3
      //   385: bipush #72
      //   387: if_icmpeq -> 1823
      //   390: iload_3
      //   391: bipush #81
      //   393: if_icmpeq -> 1736
      //   396: iload_3
      //   397: bipush #86
      //   399: if_icmpeq -> 1710
      //   402: iload_3
      //   403: bipush #97
      //   405: if_icmpeq -> 1570
      //   408: iload_3
      //   409: bipush #99
      //   411: if_icmpeq -> 1427
      //   414: iload_3
      //   415: bipush #104
      //   417: if_icmpeq -> 1399
      //   420: iload_3
      //   421: bipush #113
      //   423: if_icmpeq -> 1299
      //   426: iload_3
      //   427: bipush #118
      //   429: if_icmpeq -> 1274
      //   432: iload_3
      //   433: bipush #76
      //   435: if_icmpeq -> 1229
      //   438: iload_3
      //   439: bipush #77
      //   441: if_icmpeq -> 1159
      //   444: iload_3
      //   445: bipush #83
      //   447: if_icmpeq -> 1014
      //   450: iload_3
      //   451: bipush #84
      //   453: if_icmpeq -> 903
      //   456: iload_3
      //   457: bipush #108
      //   459: if_icmpeq -> 848
      //   462: iload_3
      //   463: bipush #109
      //   465: if_icmpeq -> 780
      //   468: iload_3
      //   469: bipush #115
      //   471: if_icmpeq -> 614
      //   474: iload_3
      //   475: bipush #116
      //   477: if_icmpeq -> 483
      //   480: goto -> 2089
      //   483: iload #16
      //   485: bipush #113
      //   487: if_icmpeq -> 523
      //   490: iload #16
      //   492: bipush #116
      //   494: if_icmpeq -> 523
      //   497: iload #16
      //   499: bipush #81
      //   501: if_icmpeq -> 523
      //   504: iload #16
      //   506: bipush #84
      //   508: if_icmpne -> 514
      //   511: goto -> 523
      //   514: fconst_0
      //   515: fstore #8
      //   517: fconst_0
      //   518: fstore #7
      //   520: goto -> 537
      //   523: fload #5
      //   525: fload #7
      //   527: fsub
      //   528: fstore #7
      //   530: fload #6
      //   532: fload #8
      //   534: fsub
      //   535: fstore #8
      //   537: iload_2
      //   538: iconst_0
      //   539: iadd
      //   540: istore #17
      //   542: aload #4
      //   544: iload #17
      //   546: faload
      //   547: fstore #11
      //   549: iload_2
      //   550: iconst_1
      //   551: iadd
      //   552: istore #16
      //   554: aload_0
      //   555: fload #7
      //   557: fload #8
      //   559: fload #11
      //   561: aload #4
      //   563: iload #16
      //   565: faload
      //   566: invokevirtual rQuadTo : (FFFF)V
      //   569: fload #5
      //   571: aload #4
      //   573: iload #17
      //   575: faload
      //   576: fadd
      //   577: fstore #11
      //   579: fload #6
      //   581: aload #4
      //   583: iload #16
      //   585: faload
      //   586: fadd
      //   587: fstore #12
      //   589: fload #8
      //   591: fload #6
      //   593: fadd
      //   594: fstore #8
      //   596: fload #7
      //   598: fload #5
      //   600: fadd
      //   601: fstore #7
      //   603: fload #12
      //   605: fstore #6
      //   607: fload #11
      //   609: fstore #5
      //   611: goto -> 480
      //   614: iload #16
      //   616: bipush #99
      //   618: if_icmpeq -> 654
      //   621: iload #16
      //   623: bipush #115
      //   625: if_icmpeq -> 654
      //   628: iload #16
      //   630: bipush #67
      //   632: if_icmpeq -> 654
      //   635: iload #16
      //   637: bipush #83
      //   639: if_icmpne -> 645
      //   642: goto -> 654
      //   645: fconst_0
      //   646: fstore #8
      //   648: fconst_0
      //   649: fstore #7
      //   651: goto -> 676
      //   654: fload #6
      //   656: fload #8
      //   658: fsub
      //   659: fstore #8
      //   661: fload #5
      //   663: fload #7
      //   665: fsub
      //   666: fstore #11
      //   668: fload #8
      //   670: fstore #7
      //   672: fload #11
      //   674: fstore #8
      //   676: iload_2
      //   677: iconst_0
      //   678: iadd
      //   679: istore #18
      //   681: aload #4
      //   683: iload #18
      //   685: faload
      //   686: fstore #12
      //   688: iload_2
      //   689: iconst_1
      //   690: iadd
      //   691: istore #19
      //   693: aload #4
      //   695: iload #19
      //   697: faload
      //   698: fstore #13
      //   700: iload_2
      //   701: iconst_2
      //   702: iadd
      //   703: istore #17
      //   705: aload #4
      //   707: iload #17
      //   709: faload
      //   710: fstore #11
      //   712: iload_2
      //   713: iconst_3
      //   714: iadd
      //   715: istore #16
      //   717: aload_0
      //   718: fload #8
      //   720: fload #7
      //   722: fload #12
      //   724: fload #13
      //   726: fload #11
      //   728: aload #4
      //   730: iload #16
      //   732: faload
      //   733: invokevirtual rCubicTo : (FFFFFF)V
      //   736: aload #4
      //   738: iload #18
      //   740: faload
      //   741: fload #5
      //   743: fadd
      //   744: fstore #12
      //   746: aload #4
      //   748: iload #19
      //   750: faload
      //   751: fload #6
      //   753: fadd
      //   754: fstore #7
      //   756: fload #5
      //   758: aload #4
      //   760: iload #17
      //   762: faload
      //   763: fadd
      //   764: fstore #8
      //   766: aload #4
      //   768: iload #16
      //   770: faload
      //   771: fstore #11
      //   773: fload #12
      //   775: fstore #5
      //   777: goto -> 1544
      //   780: iload_2
      //   781: iconst_0
      //   782: iadd
      //   783: istore #16
      //   785: fload #5
      //   787: aload #4
      //   789: iload #16
      //   791: faload
      //   792: fadd
      //   793: fstore #5
      //   795: iload_2
      //   796: iconst_1
      //   797: iadd
      //   798: istore #17
      //   800: fload #6
      //   802: aload #4
      //   804: iload #17
      //   806: faload
      //   807: fadd
      //   808: fstore #6
      //   810: iload_2
      //   811: ifle -> 831
      //   814: aload_0
      //   815: aload #4
      //   817: iload #16
      //   819: faload
      //   820: aload #4
      //   822: iload #17
      //   824: faload
      //   825: invokevirtual rLineTo : (FF)V
      //   828: goto -> 480
      //   831: aload_0
      //   832: aload #4
      //   834: iload #16
      //   836: faload
      //   837: aload #4
      //   839: iload #17
      //   841: faload
      //   842: invokevirtual rMoveTo : (FF)V
      //   845: goto -> 1218
      //   848: iload_2
      //   849: iconst_0
      //   850: iadd
      //   851: istore #17
      //   853: aload #4
      //   855: iload #17
      //   857: faload
      //   858: fstore #11
      //   860: iload_2
      //   861: iconst_1
      //   862: iadd
      //   863: istore #16
      //   865: aload_0
      //   866: fload #11
      //   868: aload #4
      //   870: iload #16
      //   872: faload
      //   873: invokevirtual rLineTo : (FF)V
      //   876: fload #5
      //   878: aload #4
      //   880: iload #17
      //   882: faload
      //   883: fadd
      //   884: fstore #5
      //   886: aload #4
      //   888: iload #16
      //   890: faload
      //   891: fstore #11
      //   893: fload #6
      //   895: fload #11
      //   897: fadd
      //   898: fstore #6
      //   900: goto -> 480
      //   903: iload #16
      //   905: bipush #113
      //   907: if_icmpeq -> 939
      //   910: iload #16
      //   912: bipush #116
      //   914: if_icmpeq -> 939
      //   917: iload #16
      //   919: bipush #81
      //   921: if_icmpeq -> 939
      //   924: fload #6
      //   926: fstore #12
      //   928: fload #5
      //   930: fstore #11
      //   932: iload #16
      //   934: bipush #84
      //   936: if_icmpne -> 957
      //   939: fload #5
      //   941: fconst_2
      //   942: fmul
      //   943: fload #7
      //   945: fsub
      //   946: fstore #11
      //   948: fload #6
      //   950: fconst_2
      //   951: fmul
      //   952: fload #8
      //   954: fsub
      //   955: fstore #12
      //   957: iload_2
      //   958: iconst_0
      //   959: iadd
      //   960: istore #16
      //   962: aload #4
      //   964: iload #16
      //   966: faload
      //   967: fstore #5
      //   969: iload_2
      //   970: iconst_1
      //   971: iadd
      //   972: istore #17
      //   974: aload_0
      //   975: fload #11
      //   977: fload #12
      //   979: fload #5
      //   981: aload #4
      //   983: iload #17
      //   985: faload
      //   986: invokevirtual quadTo : (FFFF)V
      //   989: aload #4
      //   991: iload #16
      //   993: faload
      //   994: fstore #5
      //   996: aload #4
      //   998: iload #17
      //   1000: faload
      //   1001: fstore #6
      //   1003: fload #12
      //   1005: fstore #8
      //   1007: fload #11
      //   1009: fstore #7
      //   1011: goto -> 2089
      //   1014: iload #16
      //   1016: bipush #99
      //   1018: if_icmpeq -> 1050
      //   1021: iload #16
      //   1023: bipush #115
      //   1025: if_icmpeq -> 1050
      //   1028: iload #16
      //   1030: bipush #67
      //   1032: if_icmpeq -> 1050
      //   1035: fload #6
      //   1037: fstore #12
      //   1039: fload #5
      //   1041: fstore #11
      //   1043: iload #16
      //   1045: bipush #83
      //   1047: if_icmpne -> 1068
      //   1050: fload #5
      //   1052: fconst_2
      //   1053: fmul
      //   1054: fload #7
      //   1056: fsub
      //   1057: fstore #11
      //   1059: fload #6
      //   1061: fconst_2
      //   1062: fmul
      //   1063: fload #8
      //   1065: fsub
      //   1066: fstore #12
      //   1068: iload_2
      //   1069: iconst_0
      //   1070: iadd
      //   1071: istore #18
      //   1073: aload #4
      //   1075: iload #18
      //   1077: faload
      //   1078: fstore #5
      //   1080: iload_2
      //   1081: iconst_1
      //   1082: iadd
      //   1083: istore #19
      //   1085: aload #4
      //   1087: iload #19
      //   1089: faload
      //   1090: fstore #6
      //   1092: iload_2
      //   1093: iconst_2
      //   1094: iadd
      //   1095: istore #16
      //   1097: aload #4
      //   1099: iload #16
      //   1101: faload
      //   1102: fstore #7
      //   1104: iload_2
      //   1105: iconst_3
      //   1106: iadd
      //   1107: istore #17
      //   1109: aload_0
      //   1110: fload #11
      //   1112: fload #12
      //   1114: fload #5
      //   1116: fload #6
      //   1118: fload #7
      //   1120: aload #4
      //   1122: iload #17
      //   1124: faload
      //   1125: invokevirtual cubicTo : (FFFFFF)V
      //   1128: aload #4
      //   1130: iload #18
      //   1132: faload
      //   1133: fstore #5
      //   1135: aload #4
      //   1137: iload #19
      //   1139: faload
      //   1140: fstore #7
      //   1142: aload #4
      //   1144: iload #16
      //   1146: faload
      //   1147: fstore #11
      //   1149: aload #4
      //   1151: iload #17
      //   1153: faload
      //   1154: fstore #6
      //   1156: goto -> 1555
      //   1159: iload_2
      //   1160: iconst_0
      //   1161: iadd
      //   1162: istore #17
      //   1164: aload #4
      //   1166: iload #17
      //   1168: faload
      //   1169: fstore #5
      //   1171: iload_2
      //   1172: iconst_1
      //   1173: iadd
      //   1174: istore #16
      //   1176: aload #4
      //   1178: iload #16
      //   1180: faload
      //   1181: fstore #6
      //   1183: iload_2
      //   1184: ifle -> 1204
      //   1187: aload_0
      //   1188: aload #4
      //   1190: iload #17
      //   1192: faload
      //   1193: aload #4
      //   1195: iload #16
      //   1197: faload
      //   1198: invokevirtual lineTo : (FF)V
      //   1201: goto -> 480
      //   1204: aload_0
      //   1205: aload #4
      //   1207: iload #17
      //   1209: faload
      //   1210: aload #4
      //   1212: iload #16
      //   1214: faload
      //   1215: invokevirtual moveTo : (FF)V
      //   1218: fload #6
      //   1220: fstore #9
      //   1222: fload #5
      //   1224: fstore #10
      //   1226: goto -> 480
      //   1229: iload_2
      //   1230: iconst_0
      //   1231: iadd
      //   1232: istore #16
      //   1234: aload #4
      //   1236: iload #16
      //   1238: faload
      //   1239: fstore #5
      //   1241: iload_2
      //   1242: iconst_1
      //   1243: iadd
      //   1244: istore #17
      //   1246: aload_0
      //   1247: fload #5
      //   1249: aload #4
      //   1251: iload #17
      //   1253: faload
      //   1254: invokevirtual lineTo : (FF)V
      //   1257: aload #4
      //   1259: iload #16
      //   1261: faload
      //   1262: fstore #5
      //   1264: aload #4
      //   1266: iload #17
      //   1268: faload
      //   1269: fstore #6
      //   1271: goto -> 480
      //   1274: iload_2
      //   1275: iconst_0
      //   1276: iadd
      //   1277: istore #16
      //   1279: aload_0
      //   1280: fconst_0
      //   1281: aload #4
      //   1283: iload #16
      //   1285: faload
      //   1286: invokevirtual rLineTo : (FF)V
      //   1289: aload #4
      //   1291: iload #16
      //   1293: faload
      //   1294: fstore #11
      //   1296: goto -> 893
      //   1299: iload_2
      //   1300: iconst_0
      //   1301: iadd
      //   1302: istore #18
      //   1304: aload #4
      //   1306: iload #18
      //   1308: faload
      //   1309: fstore #8
      //   1311: iload_2
      //   1312: iconst_1
      //   1313: iadd
      //   1314: istore #17
      //   1316: aload #4
      //   1318: iload #17
      //   1320: faload
      //   1321: fstore #11
      //   1323: iload_2
      //   1324: iconst_2
      //   1325: iadd
      //   1326: istore #19
      //   1328: aload #4
      //   1330: iload #19
      //   1332: faload
      //   1333: fstore #7
      //   1335: iload_2
      //   1336: iconst_3
      //   1337: iadd
      //   1338: istore #16
      //   1340: aload_0
      //   1341: fload #8
      //   1343: fload #11
      //   1345: fload #7
      //   1347: aload #4
      //   1349: iload #16
      //   1351: faload
      //   1352: invokevirtual rQuadTo : (FFFF)V
      //   1355: aload #4
      //   1357: iload #18
      //   1359: faload
      //   1360: fload #5
      //   1362: fadd
      //   1363: fstore #12
      //   1365: aload #4
      //   1367: iload #17
      //   1369: faload
      //   1370: fload #6
      //   1372: fadd
      //   1373: fstore #7
      //   1375: fload #5
      //   1377: aload #4
      //   1379: iload #19
      //   1381: faload
      //   1382: fadd
      //   1383: fstore #8
      //   1385: aload #4
      //   1387: iload #16
      //   1389: faload
      //   1390: fstore #11
      //   1392: fload #12
      //   1394: fstore #5
      //   1396: goto -> 1544
      //   1399: iload_2
      //   1400: iconst_0
      //   1401: iadd
      //   1402: istore #16
      //   1404: aload_0
      //   1405: aload #4
      //   1407: iload #16
      //   1409: faload
      //   1410: fconst_0
      //   1411: invokevirtual rLineTo : (FF)V
      //   1414: fload #5
      //   1416: aload #4
      //   1418: iload #16
      //   1420: faload
      //   1421: fadd
      //   1422: fstore #5
      //   1424: goto -> 480
      //   1427: aload #4
      //   1429: iload_2
      //   1430: iconst_0
      //   1431: iadd
      //   1432: faload
      //   1433: fstore #12
      //   1435: aload #4
      //   1437: iload_2
      //   1438: iconst_1
      //   1439: iadd
      //   1440: faload
      //   1441: fstore #7
      //   1443: iload_2
      //   1444: iconst_2
      //   1445: iadd
      //   1446: istore #17
      //   1448: aload #4
      //   1450: iload #17
      //   1452: faload
      //   1453: fstore #13
      //   1455: iload_2
      //   1456: iconst_3
      //   1457: iadd
      //   1458: istore #18
      //   1460: aload #4
      //   1462: iload #18
      //   1464: faload
      //   1465: fstore #8
      //   1467: iload_2
      //   1468: iconst_4
      //   1469: iadd
      //   1470: istore #19
      //   1472: aload #4
      //   1474: iload #19
      //   1476: faload
      //   1477: fstore #11
      //   1479: iload_2
      //   1480: iconst_5
      //   1481: iadd
      //   1482: istore #16
      //   1484: aload_0
      //   1485: fload #12
      //   1487: fload #7
      //   1489: fload #13
      //   1491: fload #8
      //   1493: fload #11
      //   1495: aload #4
      //   1497: iload #16
      //   1499: faload
      //   1500: invokevirtual rCubicTo : (FFFFFF)V
      //   1503: aload #4
      //   1505: iload #17
      //   1507: faload
      //   1508: fload #5
      //   1510: fadd
      //   1511: fstore #12
      //   1513: aload #4
      //   1515: iload #18
      //   1517: faload
      //   1518: fload #6
      //   1520: fadd
      //   1521: fstore #7
      //   1523: fload #5
      //   1525: aload #4
      //   1527: iload #19
      //   1529: faload
      //   1530: fadd
      //   1531: fstore #8
      //   1533: aload #4
      //   1535: iload #16
      //   1537: faload
      //   1538: fstore #11
      //   1540: fload #12
      //   1542: fstore #5
      //   1544: fload #6
      //   1546: fload #11
      //   1548: fadd
      //   1549: fstore #6
      //   1551: fload #8
      //   1553: fstore #11
      //   1555: fload #7
      //   1557: fstore #8
      //   1559: fload #5
      //   1561: fstore #7
      //   1563: fload #11
      //   1565: fstore #5
      //   1567: goto -> 480
      //   1570: iload_2
      //   1571: iconst_5
      //   1572: iadd
      //   1573: istore #16
      //   1575: aload #4
      //   1577: iload #16
      //   1579: faload
      //   1580: fstore #7
      //   1582: iload_2
      //   1583: bipush #6
      //   1585: iadd
      //   1586: istore #17
      //   1588: aload #4
      //   1590: iload #17
      //   1592: faload
      //   1593: fstore #12
      //   1595: aload #4
      //   1597: iload_2
      //   1598: iconst_0
      //   1599: iadd
      //   1600: faload
      //   1601: fstore #11
      //   1603: aload #4
      //   1605: iload_2
      //   1606: iconst_1
      //   1607: iadd
      //   1608: faload
      //   1609: fstore #8
      //   1611: aload #4
      //   1613: iload_2
      //   1614: iconst_2
      //   1615: iadd
      //   1616: faload
      //   1617: fstore #13
      //   1619: aload #4
      //   1621: iload_2
      //   1622: iconst_3
      //   1623: iadd
      //   1624: faload
      //   1625: fconst_0
      //   1626: fcmpl
      //   1627: ifeq -> 1636
      //   1630: iconst_1
      //   1631: istore #20
      //   1633: goto -> 1639
      //   1636: iconst_0
      //   1637: istore #20
      //   1639: aload #4
      //   1641: iload_2
      //   1642: iconst_4
      //   1643: iadd
      //   1644: faload
      //   1645: fconst_0
      //   1646: fcmpl
      //   1647: ifeq -> 1656
      //   1650: iconst_1
      //   1651: istore #21
      //   1653: goto -> 1659
      //   1656: iconst_0
      //   1657: istore #21
      //   1659: aload_0
      //   1660: fload #5
      //   1662: fload #6
      //   1664: fload #7
      //   1666: fload #5
      //   1668: fadd
      //   1669: fload #12
      //   1671: fload #6
      //   1673: fadd
      //   1674: fload #11
      //   1676: fload #8
      //   1678: fload #13
      //   1680: iload #20
      //   1682: iload #21
      //   1684: invokestatic drawArc : (Landroid/graphics/Path;FFFFFFFZZ)V
      //   1687: fload #5
      //   1689: aload #4
      //   1691: iload #16
      //   1693: faload
      //   1694: fadd
      //   1695: fstore #5
      //   1697: fload #6
      //   1699: aload #4
      //   1701: iload #17
      //   1703: faload
      //   1704: fadd
      //   1705: fstore #6
      //   1707: goto -> 2081
      //   1710: iload_2
      //   1711: iconst_0
      //   1712: iadd
      //   1713: istore #16
      //   1715: aload_0
      //   1716: fload #5
      //   1718: aload #4
      //   1720: iload #16
      //   1722: faload
      //   1723: invokevirtual lineTo : (FF)V
      //   1726: aload #4
      //   1728: iload #16
      //   1730: faload
      //   1731: fstore #6
      //   1733: goto -> 2089
      //   1736: iload_2
      //   1737: iconst_0
      //   1738: iadd
      //   1739: istore #17
      //   1741: aload #4
      //   1743: iload #17
      //   1745: faload
      //   1746: fstore #5
      //   1748: iload_2
      //   1749: iconst_1
      //   1750: iadd
      //   1751: istore #19
      //   1753: aload #4
      //   1755: iload #19
      //   1757: faload
      //   1758: fstore #6
      //   1760: iload_2
      //   1761: iconst_2
      //   1762: iadd
      //   1763: istore #18
      //   1765: aload #4
      //   1767: iload #18
      //   1769: faload
      //   1770: fstore #7
      //   1772: iload_2
      //   1773: iconst_3
      //   1774: iadd
      //   1775: istore #16
      //   1777: aload_0
      //   1778: fload #5
      //   1780: fload #6
      //   1782: fload #7
      //   1784: aload #4
      //   1786: iload #16
      //   1788: faload
      //   1789: invokevirtual quadTo : (FFFF)V
      //   1792: aload #4
      //   1794: iload #17
      //   1796: faload
      //   1797: fstore #7
      //   1799: aload #4
      //   1801: iload #19
      //   1803: faload
      //   1804: fstore #8
      //   1806: aload #4
      //   1808: iload #18
      //   1810: faload
      //   1811: fstore #5
      //   1813: aload #4
      //   1815: iload #16
      //   1817: faload
      //   1818: fstore #6
      //   1820: goto -> 2089
      //   1823: iload_2
      //   1824: iconst_0
      //   1825: iadd
      //   1826: istore #16
      //   1828: aload_0
      //   1829: aload #4
      //   1831: iload #16
      //   1833: faload
      //   1834: fload #6
      //   1836: invokevirtual lineTo : (FF)V
      //   1839: aload #4
      //   1841: iload #16
      //   1843: faload
      //   1844: fstore #5
      //   1846: goto -> 2089
      //   1849: aload #4
      //   1851: iload_2
      //   1852: iconst_0
      //   1853: iadd
      //   1854: faload
      //   1855: fstore #6
      //   1857: aload #4
      //   1859: iload_2
      //   1860: iconst_1
      //   1861: iadd
      //   1862: faload
      //   1863: fstore #8
      //   1865: iload_2
      //   1866: iconst_2
      //   1867: iadd
      //   1868: istore #19
      //   1870: aload #4
      //   1872: iload #19
      //   1874: faload
      //   1875: fstore #7
      //   1877: iload_2
      //   1878: iconst_3
      //   1879: iadd
      //   1880: istore #16
      //   1882: aload #4
      //   1884: iload #16
      //   1886: faload
      //   1887: fstore #5
      //   1889: iload_2
      //   1890: iconst_4
      //   1891: iadd
      //   1892: istore #18
      //   1894: aload #4
      //   1896: iload #18
      //   1898: faload
      //   1899: fstore #11
      //   1901: iload_2
      //   1902: iconst_5
      //   1903: iadd
      //   1904: istore #17
      //   1906: aload_0
      //   1907: fload #6
      //   1909: fload #8
      //   1911: fload #7
      //   1913: fload #5
      //   1915: fload #11
      //   1917: aload #4
      //   1919: iload #17
      //   1921: faload
      //   1922: invokevirtual cubicTo : (FFFFFF)V
      //   1925: aload #4
      //   1927: iload #18
      //   1929: faload
      //   1930: fstore #5
      //   1932: aload #4
      //   1934: iload #17
      //   1936: faload
      //   1937: fstore #6
      //   1939: aload #4
      //   1941: iload #19
      //   1943: faload
      //   1944: fstore #7
      //   1946: aload #4
      //   1948: iload #16
      //   1950: faload
      //   1951: fstore #8
      //   1953: goto -> 2089
      //   1956: iload_2
      //   1957: iconst_5
      //   1958: iadd
      //   1959: istore #16
      //   1961: aload #4
      //   1963: iload #16
      //   1965: faload
      //   1966: fstore #13
      //   1968: iload_2
      //   1969: bipush #6
      //   1971: iadd
      //   1972: istore #17
      //   1974: aload #4
      //   1976: iload #17
      //   1978: faload
      //   1979: fstore #11
      //   1981: aload #4
      //   1983: iload_2
      //   1984: iconst_0
      //   1985: iadd
      //   1986: faload
      //   1987: fstore #8
      //   1989: aload #4
      //   1991: iload_2
      //   1992: iconst_1
      //   1993: iadd
      //   1994: faload
      //   1995: fstore #7
      //   1997: aload #4
      //   1999: iload_2
      //   2000: iconst_2
      //   2001: iadd
      //   2002: faload
      //   2003: fstore #12
      //   2005: aload #4
      //   2007: iload_2
      //   2008: iconst_3
      //   2009: iadd
      //   2010: faload
      //   2011: fconst_0
      //   2012: fcmpl
      //   2013: ifeq -> 2022
      //   2016: iconst_1
      //   2017: istore #20
      //   2019: goto -> 2025
      //   2022: iconst_0
      //   2023: istore #20
      //   2025: aload #4
      //   2027: iload_2
      //   2028: iconst_4
      //   2029: iadd
      //   2030: faload
      //   2031: fconst_0
      //   2032: fcmpl
      //   2033: ifeq -> 2042
      //   2036: iconst_1
      //   2037: istore #21
      //   2039: goto -> 2045
      //   2042: iconst_0
      //   2043: istore #21
      //   2045: aload_0
      //   2046: fload #5
      //   2048: fload #6
      //   2050: fload #13
      //   2052: fload #11
      //   2054: fload #8
      //   2056: fload #7
      //   2058: fload #12
      //   2060: iload #20
      //   2062: iload #21
      //   2064: invokestatic drawArc : (Landroid/graphics/Path;FFFFFFFZZ)V
      //   2067: aload #4
      //   2069: iload #16
      //   2071: faload
      //   2072: fstore #5
      //   2074: aload #4
      //   2076: iload #17
      //   2078: faload
      //   2079: fstore #6
      //   2081: fload #6
      //   2083: fstore #8
      //   2085: fload #5
      //   2087: fstore #7
      //   2089: iload_2
      //   2090: iload #15
      //   2092: iadd
      //   2093: istore_2
      //   2094: iload_3
      //   2095: istore #16
      //   2097: goto -> 365
      //   2100: aload_1
      //   2101: iconst_0
      //   2102: fload #5
      //   2104: fastore
      //   2105: aload_1
      //   2106: iconst_1
      //   2107: fload #6
      //   2109: fastore
      //   2110: aload_1
      //   2111: iconst_2
      //   2112: fload #7
      //   2114: fastore
      //   2115: aload_1
      //   2116: iconst_3
      //   2117: fload #8
      //   2119: fastore
      //   2120: aload_1
      //   2121: iconst_4
      //   2122: fload #10
      //   2124: fastore
      //   2125: aload_1
      //   2126: iconst_5
      //   2127: fload #9
      //   2129: fastore
      //   2130: return
    }
    
    private static void arcToBezier(Path param1Path, double param1Double1, double param1Double2, double param1Double3, double param1Double4, double param1Double5, double param1Double6, double param1Double7, double param1Double8, double param1Double9) {
      int i = (int)Math.ceil(Math.abs(param1Double9 * 4.0D / Math.PI));
      double d2 = Math.cos(param1Double7);
      double d4 = Math.sin(param1Double7);
      double d3 = Math.cos(param1Double8);
      param1Double7 = Math.sin(param1Double8);
      double d1 = -param1Double3;
      double d8 = d1 * d2;
      double d7 = param1Double4 * d4;
      double d5 = d1 * d4;
      double d9 = param1Double4 * d2;
      double d6 = param1Double9 / i;
      d1 = param1Double7 * d5 + d3 * d9;
      param1Double9 = d8 * param1Double7 - d7 * d3;
      byte b = 0;
      param1Double7 = param1Double5;
      d3 = param1Double8;
      param1Double4 = d5;
      param1Double5 = d6;
      param1Double8 = d4;
      while (b < i) {
        d6 = d3 + param1Double5;
        double d10 = Math.sin(d6);
        double d12 = Math.cos(d6);
        d4 = param1Double1 + param1Double3 * d2 * d12 - d7 * d10;
        double d11 = param1Double2 + param1Double3 * param1Double8 * d12 + d9 * d10;
        d5 = d8 * d10 - d7 * d12;
        d10 = d10 * param1Double4 + d12 * d9;
        d12 = d6 - d3;
        d3 = Math.tan(d12 / 2.0D);
        d3 = Math.sin(d12) * (Math.sqrt(d3 * 3.0D * d3 + 4.0D) - 1.0D) / 3.0D;
        param1Path.rLineTo(0.0F, 0.0F);
        param1Path.cubicTo((float)(param1Double7 + param1Double9 * d3), (float)(param1Double6 + d1 * d3), (float)(d4 - d3 * d5), (float)(d11 - d3 * d10), (float)d4, (float)d11);
        b++;
        param1Double6 = d11;
        d3 = d6;
        d1 = d10;
        param1Double9 = d5;
        param1Double7 = d4;
      } 
    }
    
    private static void drawArc(Path param1Path, float param1Float1, float param1Float2, float param1Float3, float param1Float4, float param1Float5, float param1Float6, float param1Float7, boolean param1Boolean1, boolean param1Boolean2) {
      double d6 = Math.toRadians(param1Float7);
      double d9 = Math.cos(d6);
      double d10 = Math.sin(d6);
      double d7 = param1Float1;
      double d11 = param1Float2;
      double d8 = param1Float5;
      double d1 = (d7 * d9 + d11 * d10) / d8;
      double d2 = -param1Float1;
      double d5 = param1Float6;
      double d12 = (d2 * d10 + d11 * d9) / d5;
      double d3 = param1Float3;
      d2 = param1Float4;
      double d4 = (d3 * d9 + d2 * d10) / d8;
      double d13 = (-param1Float3 * d10 + d2 * d9) / d5;
      double d14 = d1 - d4;
      double d15 = d12 - d13;
      d3 = (d1 + d4) / 2.0D;
      d2 = (d12 + d13) / 2.0D;
      double d17 = d14 * d14 + d15 * d15;
      if (d17 == 0.0D)
        return; 
      double d16 = 1.0D / d17 - 0.25D;
      if (d16 < 0.0D) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Points are too far apart ");
        stringBuilder.append(d17);
        stringBuilder.toString();
        float f = (float)(Math.sqrt(d17) / 1.99999D);
        drawArc(param1Path, param1Float1, param1Float2, param1Float3, param1Float4, param1Float5 * f, param1Float6 * f, param1Float7, param1Boolean1, param1Boolean2);
        return;
      } 
      d16 = Math.sqrt(d16);
      d14 *= d16;
      d15 = d16 * d15;
      if (param1Boolean1 == param1Boolean2) {
        d3 -= d15;
        d2 += d14;
      } else {
        d3 += d15;
        d2 -= d14;
      } 
      d12 = Math.atan2(d12 - d2, d1 - d3);
      d4 = Math.atan2(d13 - d2, d4 - d3) - d12;
      int i = d4 cmp 0.0D;
      if (i >= 0) {
        param1Boolean1 = true;
      } else {
        param1Boolean1 = false;
      } 
      d1 = d4;
      if (param1Boolean2 != param1Boolean1)
        if (i > 0) {
          d1 = d4 - 6.283185307179586D;
        } else {
          d1 = d4 + 6.283185307179586D;
        }  
      d3 *= d8;
      d2 *= d5;
      arcToBezier(param1Path, d3 * d9 - d2 * d10, d3 * d10 + d2 * d9, d8, d5, d7, d11, d6, d12, d1);
    }
    
    public static void nodesToPath(PathDataNode[] param1ArrayOfPathDataNode, Path param1Path) {
      float[] arrayOfFloat = new float[6];
      char c = 'm';
      for (byte b = 0; b < param1ArrayOfPathDataNode.length; b++) {
        addCommand(param1Path, arrayOfFloat, c, (param1ArrayOfPathDataNode[b]).mType, (param1ArrayOfPathDataNode[b]).mParams);
        c = (param1ArrayOfPathDataNode[b]).mType;
      } 
    }
    
    public void interpolatePathDataNode(PathDataNode param1PathDataNode1, PathDataNode param1PathDataNode2, float param1Float) {
      byte b = 0;
      while (true) {
        float[] arrayOfFloat = param1PathDataNode1.mParams;
        if (b < arrayOfFloat.length) {
          this.mParams[b] = arrayOfFloat[b] * (1.0F - param1Float) + param1PathDataNode2.mParams[b] * param1Float;
          b++;
          continue;
        } 
        break;
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/graphics/PathParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */