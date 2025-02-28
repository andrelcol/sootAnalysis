package com.roadtrack.onstar.ui.wakeUpCar;

import com.roadtrack.onstar.Enums;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class WatterMark {
  public static ArrayList<String> getWatterMarksFromAccions(LinkedHashMap<String, LinkedHashMap<String, Integer>> paramLinkedHashMap, Enums.Services... paramVarArgs) {
    ArrayList<String> arrayList = new ArrayList(paramLinkedHashMap.size());
    for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : paramLinkedHashMap.entrySet()) {
      LinkedHashMap<String, Integer> linkedHashMap = (LinkedHashMap)entry.getValue();
      Enums.Services services = Enums.Services.GetValue(Integer.parseInt((String)entry.getKey()));
      if (paramVarArgs.length == 0 || services.GetCode() != paramVarArgs[0].GetCode())
        linkedHashMap.put("water", Integer.valueOf(Boolean.valueOf(isVehicleAction((String)entry.getKey())).booleanValue() ^ true)); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append((String)entry.getKey());
      stringBuilder.append(",");
      stringBuilder.append(linkedHashMap.get("water"));
      stringBuilder.append(",");
      stringBuilder.append(linkedHashMap.get("hasmap"));
      stringBuilder.append(";");
      arrayList.add(stringBuilder.toString());
    } 
    return arrayList;
  }
  
  public static LinkedHashMap<String, LinkedHashMap<String, Integer>> getWatterMarksFromButtonAccions(LinkedHashMap<String, LinkedHashMap<String, Integer>> paramLinkedHashMap, Enums.Services... paramVarArgs) {
    for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : paramLinkedHashMap.entrySet()) {
      LinkedHashMap<String, Integer> linkedHashMap = (LinkedHashMap)entry.getValue();
      Enums.Services services = Enums.Services.GetValue(Integer.parseInt((String)entry.getKey()));
      if (paramVarArgs.length == 0 || services.GetCode() != paramVarArgs[0].GetCode())
        linkedHashMap.put("water", Integer.valueOf(Boolean.valueOf(isVehicleAction((String)entry.getKey())).booleanValue() ^ true)); 
    } 
    return paramLinkedHashMap;
  }
  
  private static boolean isVehicleAction(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual hashCode : ()I
    //   4: istore_1
    //   5: iload_1
    //   6: bipush #49
    //   8: if_icmpeq -> 602
    //   11: iload_1
    //   12: bipush #50
    //   14: if_icmpeq -> 587
    //   17: iload_1
    //   18: bipush #54
    //   20: if_icmpeq -> 572
    //   23: iload_1
    //   24: sipush #1444
    //   27: if_icmpeq -> 557
    //   30: iload_1
    //   31: sipush #1662
    //   34: if_icmpeq -> 542
    //   37: iload_1
    //   38: sipush #1665
    //   41: if_icmpeq -> 527
    //   44: iload_1
    //   45: sipush #1699
    //   48: if_icmpeq -> 512
    //   51: iload_1
    //   52: sipush #1724
    //   55: if_icmpeq -> 497
    //   58: iload_1
    //   59: ldc 44812
    //   61: if_icmpeq -> 482
    //   64: iload_1
    //   65: ldc 48659
    //   67: if_icmpeq -> 467
    //   70: iload_1
    //   71: sipush #1446
    //   74: if_icmpeq -> 452
    //   77: iload_1
    //   78: sipush #1447
    //   81: if_icmpeq -> 437
    //   84: iload_1
    //   85: sipush #1693
    //   88: if_icmpeq -> 423
    //   91: iload_1
    //   92: sipush #1694
    //   95: if_icmpeq -> 408
    //   98: iload_1
    //   99: tableswitch default -> 124, 1449 -> 393, 1450 -> 378, 1451 -> 363
    //   124: iload_1
    //   125: tableswitch default -> 164, 1633 -> 349, 1634 -> 335, 1635 -> 321, 1636 -> 306, 1637 -> 292, 1638 -> 278
    //   164: iload_1
    //   165: tableswitch default -> 200, 44816 -> 263, 44817 -> 248, 44818 -> 233, 44819 -> 218, 44820 -> 203
    //   200: goto -> 617
    //   203: aload_0
    //   204: ldc '-18'
    //   206: invokevirtual equals : (Ljava/lang/Object;)Z
    //   209: ifeq -> 617
    //   212: bipush #21
    //   214: istore_1
    //   215: goto -> 619
    //   218: aload_0
    //   219: ldc '-17'
    //   221: invokevirtual equals : (Ljava/lang/Object;)Z
    //   224: ifeq -> 617
    //   227: bipush #20
    //   229: istore_1
    //   230: goto -> 619
    //   233: aload_0
    //   234: ldc '-16'
    //   236: invokevirtual equals : (Ljava/lang/Object;)Z
    //   239: ifeq -> 617
    //   242: bipush #19
    //   244: istore_1
    //   245: goto -> 619
    //   248: aload_0
    //   249: ldc '-15'
    //   251: invokevirtual equals : (Ljava/lang/Object;)Z
    //   254: ifeq -> 617
    //   257: bipush #18
    //   259: istore_1
    //   260: goto -> 619
    //   263: aload_0
    //   264: ldc '-14'
    //   266: invokevirtual equals : (Ljava/lang/Object;)Z
    //   269: ifeq -> 617
    //   272: bipush #17
    //   274: istore_1
    //   275: goto -> 619
    //   278: aload_0
    //   279: ldc '39'
    //   281: invokevirtual equals : (Ljava/lang/Object;)Z
    //   284: ifeq -> 617
    //   287: iconst_3
    //   288: istore_1
    //   289: goto -> 619
    //   292: aload_0
    //   293: ldc '38'
    //   295: invokevirtual equals : (Ljava/lang/Object;)Z
    //   298: ifeq -> 617
    //   301: iconst_2
    //   302: istore_1
    //   303: goto -> 619
    //   306: aload_0
    //   307: ldc '37'
    //   309: invokevirtual equals : (Ljava/lang/Object;)Z
    //   312: ifeq -> 617
    //   315: bipush #8
    //   317: istore_1
    //   318: goto -> 619
    //   321: aload_0
    //   322: ldc '36'
    //   324: invokevirtual equals : (Ljava/lang/Object;)Z
    //   327: ifeq -> 617
    //   330: iconst_0
    //   331: istore_1
    //   332: goto -> 619
    //   335: aload_0
    //   336: ldc '35'
    //   338: invokevirtual equals : (Ljava/lang/Object;)Z
    //   341: ifeq -> 617
    //   344: iconst_1
    //   345: istore_1
    //   346: goto -> 619
    //   349: aload_0
    //   350: ldc '34'
    //   352: invokevirtual equals : (Ljava/lang/Object;)Z
    //   355: ifeq -> 617
    //   358: iconst_5
    //   359: istore_1
    //   360: goto -> 619
    //   363: aload_0
    //   364: ldc '-8'
    //   366: invokevirtual equals : (Ljava/lang/Object;)Z
    //   369: ifeq -> 617
    //   372: bipush #13
    //   374: istore_1
    //   375: goto -> 619
    //   378: aload_0
    //   379: ldc '-7'
    //   381: invokevirtual equals : (Ljava/lang/Object;)Z
    //   384: ifeq -> 617
    //   387: bipush #11
    //   389: istore_1
    //   390: goto -> 619
    //   393: aload_0
    //   394: ldc '-6'
    //   396: invokevirtual equals : (Ljava/lang/Object;)Z
    //   399: ifeq -> 617
    //   402: bipush #7
    //   404: istore_1
    //   405: goto -> 619
    //   408: aload_0
    //   409: ldc '53'
    //   411: invokevirtual equals : (Ljava/lang/Object;)Z
    //   414: ifeq -> 617
    //   417: bipush #15
    //   419: istore_1
    //   420: goto -> 619
    //   423: aload_0
    //   424: ldc '52'
    //   426: invokevirtual equals : (Ljava/lang/Object;)Z
    //   429: ifeq -> 617
    //   432: iconst_4
    //   433: istore_1
    //   434: goto -> 619
    //   437: aload_0
    //   438: ldc '-4'
    //   440: invokevirtual equals : (Ljava/lang/Object;)Z
    //   443: ifeq -> 617
    //   446: bipush #27
    //   448: istore_1
    //   449: goto -> 619
    //   452: aload_0
    //   453: ldc '-3'
    //   455: invokevirtual equals : (Ljava/lang/Object;)Z
    //   458: ifeq -> 617
    //   461: bipush #26
    //   463: istore_1
    //   464: goto -> 619
    //   467: aload_0
    //   468: ldc '113'
    //   470: invokevirtual equals : (Ljava/lang/Object;)Z
    //   473: ifeq -> 617
    //   476: bipush #24
    //   478: istore_1
    //   479: goto -> 619
    //   482: aload_0
    //   483: ldc '-10'
    //   485: invokevirtual equals : (Ljava/lang/Object;)Z
    //   488: ifeq -> 617
    //   491: bipush #16
    //   493: istore_1
    //   494: goto -> 619
    //   497: aload_0
    //   498: ldc '62'
    //   500: invokevirtual equals : (Ljava/lang/Object;)Z
    //   503: ifeq -> 617
    //   506: bipush #23
    //   508: istore_1
    //   509: goto -> 619
    //   512: aload_0
    //   513: ldc '58'
    //   515: invokevirtual equals : (Ljava/lang/Object;)Z
    //   518: ifeq -> 617
    //   521: bipush #25
    //   523: istore_1
    //   524: goto -> 619
    //   527: aload_0
    //   528: ldc '45'
    //   530: invokevirtual equals : (Ljava/lang/Object;)Z
    //   533: ifeq -> 617
    //   536: bipush #12
    //   538: istore_1
    //   539: goto -> 619
    //   542: aload_0
    //   543: ldc '42'
    //   545: invokevirtual equals : (Ljava/lang/Object;)Z
    //   548: ifeq -> 617
    //   551: bipush #9
    //   553: istore_1
    //   554: goto -> 619
    //   557: aload_0
    //   558: ldc '-1'
    //   560: invokevirtual equals : (Ljava/lang/Object;)Z
    //   563: ifeq -> 617
    //   566: bipush #14
    //   568: istore_1
    //   569: goto -> 619
    //   572: aload_0
    //   573: ldc '6'
    //   575: invokevirtual equals : (Ljava/lang/Object;)Z
    //   578: ifeq -> 617
    //   581: bipush #22
    //   583: istore_1
    //   584: goto -> 619
    //   587: aload_0
    //   588: ldc '2'
    //   590: invokevirtual equals : (Ljava/lang/Object;)Z
    //   593: ifeq -> 617
    //   596: bipush #6
    //   598: istore_1
    //   599: goto -> 619
    //   602: aload_0
    //   603: ldc '1'
    //   605: invokevirtual equals : (Ljava/lang/Object;)Z
    //   608: ifeq -> 617
    //   611: bipush #10
    //   613: istore_1
    //   614: goto -> 619
    //   617: iconst_m1
    //   618: istore_1
    //   619: iload_1
    //   620: tableswitch default -> 748, 0 -> 756, 1 -> 756, 2 -> 756, 3 -> 756, 4 -> 756, 5 -> 756, 6 -> 756, 7 -> 756, 8 -> 756, 9 -> 756, 10 -> 756, 11 -> 756, 12 -> 756, 13 -> 756, 14 -> 756, 15 -> 756, 16 -> 756, 17 -> 756, 18 -> 756, 19 -> 756, 20 -> 756, 21 -> 756, 22 -> 756, 23 -> 756, 24 -> 756, 25 -> 756, 26 -> 756, 27 -> 756
    //   748: iconst_0
    //   749: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   752: astore_0
    //   753: goto -> 761
    //   756: iconst_1
    //   757: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   760: astore_0
    //   761: aload_0
    //   762: invokevirtual booleanValue : ()Z
    //   765: ireturn
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/wakeUpCar/WatterMark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */