package com.roadtrack.onstar.gson;

import com.roadtrack.onstar.VO.GMTCatalog;
import com.roadtrack.onstar.VO.PaymentCardResponse;
import com.roadtrack.onstar.VO.PaymentHistoryResponseO;
import com.roadtrack.onstar.VO.RenewalPlansListResponseO;
import com.roadtrack.onstar.VO.SuscriptionResponse;
import com.roadtrack.onstar.VO.TokenResponseO;
import com.roadtrack.onstar.VO.TransactionResponse;
import com.roadtrack.onstar.nav.poi.Place;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GsonC {
  public static String FOMATTED_PHONE = "formatted_phone_number";
  
  public static String FORMATTED_ADRESS = "formatted_address";
  
  public static String GEOMETRY = "geometry";
  
  public static String ICON = "icon";
  
  public static String KEY_RESULTS = "results";
  
  public static String NAME = "name";
  
  public static String PLACE_ID = "place_id";
  
  public static String REFERENCE = "reference";
  
  public static String VICINITY = "vicinity";
  
  private String CLOSE_ARRAY = "]";
  
  private String CLOSE_OBJECT = "}";
  
  private String COMA = ",";
  
  private String COMILLA = "\"";
  
  private String GET = "get";
  
  private String OPEN_ARRAY = "[";
  
  private String OPEN_OBJECT = "{";
  
  private String TAG = "GsonC";
  
  private String VALOR = ":";
  
  private JSONArray jsonArrayPlaces;
  
  private JSONObject jsonObjectResult;
  
  private String next_page_token;
  
  private Place parseObject(JSONObject paramJSONObject) {
    Place place = new Place();
    Place.Geometry geometry = new Place.Geometry();
    Place.Location location = new Place.Location();
    try {
      place.setFormatted_address(paramJSONObject.optString(FORMATTED_ADRESS));
      place.setFormatted_phone_number(paramJSONObject.optString(FOMATTED_PHONE));
      JSONObject jSONObject2 = new JSONObject();
      this(paramJSONObject.optString(GEOMETRY));
      JSONObject jSONObject1 = new JSONObject();
      this(jSONObject2.optString("location"));
      location.setLat(Double.parseDouble(jSONObject1.optString("lat")));
      location.setLng(Double.parseDouble(jSONObject1.optString("lng")));
      geometry.setLocation(location);
      place.setGeometry(geometry);
      place.setIcon(paramJSONObject.optString(ICON));
      place.setName(paramJSONObject.optString(NAME));
      place.setReference(paramJSONObject.optString(REFERENCE));
      place.setVicinity(paramJSONObject.optString(VICINITY));
      place.setId(paramJSONObject.optString(PLACE_ID));
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, "parseObject:", exception.getMessage());
    } 
    return place;
  }
  
  public String getNext_page_token() {
    return this.next_page_token;
  }
  
  public RenewalPlansListResponseO getRenewalObject(String paramString) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_3
    //   8: new com/roadtrack/onstar/VO/RenewalPlansListResponseO
    //   11: dup
    //   12: invokespecial <init> : ()V
    //   15: astore #7
    //   17: new org/json/JSONObject
    //   20: astore #4
    //   22: aload #4
    //   24: aload_1
    //   25: invokespecial <init> : (Ljava/lang/String;)V
    //   28: aload_0
    //   29: aload #4
    //   31: putfield jsonObjectResult : Lorg/json/JSONObject;
    //   34: aload #7
    //   36: aload_0
    //   37: getfield jsonObjectResult : Lorg/json/JSONObject;
    //   40: ldc 'cpres1'
    //   42: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   45: invokevirtual setCpres1 : (Ljava/lang/String;)V
    //   48: aload #7
    //   50: aload_0
    //   51: getfield jsonObjectResult : Lorg/json/JSONObject;
    //   54: ldc 'cpres2'
    //   56: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   59: invokevirtual setCpres2 : (Ljava/lang/String;)V
    //   62: aload #7
    //   64: aload_0
    //   65: getfield jsonObjectResult : Lorg/json/JSONObject;
    //   68: ldc 'cpres3'
    //   70: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   73: invokevirtual setCpres3 : (Ljava/lang/String;)V
    //   76: aload #7
    //   78: aload_0
    //   79: getfield jsonObjectResult : Lorg/json/JSONObject;
    //   82: ldc 'cpres4'
    //   84: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   87: invokevirtual setCpres4 : (Ljava/lang/String;)V
    //   90: aload_0
    //   91: getfield jsonObjectResult : Lorg/json/JSONObject;
    //   94: ldc 'cpres5'
    //   96: invokevirtual getJSONArray : (Ljava/lang/String;)Lorg/json/JSONArray;
    //   99: astore #4
    //   101: aload #4
    //   103: invokevirtual length : ()I
    //   106: istore_2
    //   107: ldc 'cpapres13'
    //   109: astore #5
    //   111: ldc 'cpapres12'
    //   113: astore #6
    //   115: aload_3
    //   116: astore_1
    //   117: iload_2
    //   118: iconst_1
    //   119: if_icmple -> 362
    //   122: iconst_0
    //   123: istore_2
    //   124: aload #6
    //   126: astore_3
    //   127: iload_2
    //   128: aload #4
    //   130: invokevirtual length : ()I
    //   133: if_icmpge -> 359
    //   136: new com/roadtrack/onstar/VO/RenewalPlanO
    //   139: astore #8
    //   141: aload #8
    //   143: invokespecial <init> : ()V
    //   146: aload #4
    //   148: iload_2
    //   149: invokevirtual getJSONObject : (I)Lorg/json/JSONObject;
    //   152: astore #6
    //   154: aload #8
    //   156: aload #6
    //   158: ldc 'cpapres1'
    //   160: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   163: invokevirtual setCpapres1 : (Ljava/lang/String;)V
    //   166: aload #8
    //   168: aload #6
    //   170: ldc 'cpapres2'
    //   172: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   175: invokevirtual setCpapres2 : (Ljava/lang/String;)V
    //   178: aload #8
    //   180: aload #6
    //   182: ldc_w 'cpapres3'
    //   185: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   188: invokevirtual setCpapres3 : (Ljava/lang/String;)V
    //   191: aload #8
    //   193: aload #6
    //   195: ldc_w 'cpapres4'
    //   198: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   201: invokevirtual setCpapres4 : (Ljava/lang/String;)V
    //   204: aload #8
    //   206: aload #6
    //   208: ldc_w 'cpapres5'
    //   211: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   214: invokevirtual setCpapres5 : (Ljava/lang/String;)V
    //   217: aload #8
    //   219: aload #6
    //   221: ldc_w 'cpapres6'
    //   224: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   227: invokevirtual setCpapres6 : (Ljava/lang/String;)V
    //   230: aload #8
    //   232: aload #6
    //   234: ldc_w 'cpapres7'
    //   237: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   240: invokevirtual setCpapres7 : (Ljava/lang/String;)V
    //   243: aload #8
    //   245: aload #6
    //   247: ldc_w 'cpapres8'
    //   250: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   253: invokevirtual setCpapres8 : (Ljava/lang/String;)V
    //   256: aload #8
    //   258: aload #6
    //   260: ldc_w 'cpapres9'
    //   263: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   266: invokevirtual setCpapres9 : (Ljava/lang/String;)V
    //   269: aload #8
    //   271: aload #6
    //   273: ldc_w 'cpapres10'
    //   276: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   279: invokevirtual setCpapres10 : (Ljava/lang/String;)V
    //   282: aload #8
    //   284: aload #6
    //   286: ldc_w 'cpapres11'
    //   289: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   292: invokevirtual setCpapres11 : (Ljava/lang/String;)V
    //   295: aload #8
    //   297: aload #6
    //   299: aload_3
    //   300: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   303: invokevirtual setCpapres12 : (Ljava/lang/String;)V
    //   306: aload #8
    //   308: aload #6
    //   310: aload #5
    //   312: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   315: invokevirtual setCpapres13 : (Ljava/lang/String;)V
    //   318: aload #8
    //   320: aload #6
    //   322: ldc_w 'cpapres14'
    //   325: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   328: invokevirtual setCpapres14 : (Ljava/lang/String;)V
    //   331: aload #8
    //   333: aload #6
    //   335: ldc_w 'cpapres15'
    //   338: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   341: invokevirtual setCpapres15 : (Ljava/lang/String;)V
    //   344: aload_1
    //   345: aload #8
    //   347: invokeinterface add : (Ljava/lang/Object;)Z
    //   352: pop
    //   353: iinc #2, 1
    //   356: goto -> 127
    //   359: goto -> 565
    //   362: new com/roadtrack/onstar/VO/RenewalPlanO
    //   365: astore_3
    //   366: aload_3
    //   367: invokespecial <init> : ()V
    //   370: aload #4
    //   372: iconst_0
    //   373: invokevirtual getJSONObject : (I)Lorg/json/JSONObject;
    //   376: astore #4
    //   378: aload_3
    //   379: aload #4
    //   381: ldc 'cpapres1'
    //   383: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   386: invokevirtual setCpapres1 : (Ljava/lang/String;)V
    //   389: aload_3
    //   390: aload #4
    //   392: ldc 'cpapres2'
    //   394: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   397: invokevirtual setCpapres2 : (Ljava/lang/String;)V
    //   400: aload_3
    //   401: aload #4
    //   403: ldc_w 'cpapres3'
    //   406: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   409: invokevirtual setCpapres3 : (Ljava/lang/String;)V
    //   412: aload_3
    //   413: aload #4
    //   415: ldc_w 'cpapres4'
    //   418: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   421: invokevirtual setCpapres4 : (Ljava/lang/String;)V
    //   424: aload_3
    //   425: aload #4
    //   427: ldc_w 'cpapres5'
    //   430: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   433: invokevirtual setCpapres5 : (Ljava/lang/String;)V
    //   436: aload_3
    //   437: aload #4
    //   439: ldc_w 'cpapres6'
    //   442: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   445: invokevirtual setCpapres6 : (Ljava/lang/String;)V
    //   448: aload_3
    //   449: aload #4
    //   451: ldc_w 'cpapres7'
    //   454: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   457: invokevirtual setCpapres7 : (Ljava/lang/String;)V
    //   460: aload_3
    //   461: aload #4
    //   463: ldc_w 'cpapres8'
    //   466: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   469: invokevirtual setCpapres8 : (Ljava/lang/String;)V
    //   472: aload_3
    //   473: aload #4
    //   475: ldc_w 'cpapres9'
    //   478: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   481: invokevirtual setCpapres9 : (Ljava/lang/String;)V
    //   484: aload_3
    //   485: aload #4
    //   487: ldc_w 'cpapres10'
    //   490: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   493: invokevirtual setCpapres10 : (Ljava/lang/String;)V
    //   496: aload_3
    //   497: aload #4
    //   499: ldc_w 'cpapres11'
    //   502: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   505: invokevirtual setCpapres11 : (Ljava/lang/String;)V
    //   508: aload_3
    //   509: aload #4
    //   511: ldc 'cpapres12'
    //   513: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   516: invokevirtual setCpapres12 : (Ljava/lang/String;)V
    //   519: aload_3
    //   520: aload #4
    //   522: ldc 'cpapres13'
    //   524: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   527: invokevirtual setCpapres13 : (Ljava/lang/String;)V
    //   530: aload_3
    //   531: aload #4
    //   533: ldc_w 'cpapres14'
    //   536: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   539: invokevirtual setCpapres14 : (Ljava/lang/String;)V
    //   542: aload_3
    //   543: aload #4
    //   545: ldc_w 'cpapres15'
    //   548: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   551: invokevirtual setCpapres15 : (Ljava/lang/String;)V
    //   554: aload_1
    //   555: aload_3
    //   556: invokeinterface add : (Ljava/lang/Object;)Z
    //   561: pop
    //   562: goto -> 359
    //   565: aload #7
    //   567: aload_1
    //   568: invokevirtual setCpres5 : (Ljava/util/List;)V
    //   571: aload #7
    //   573: aload_0
    //   574: getfield jsonObjectResult : Lorg/json/JSONObject;
    //   577: ldc_w 'cpres6'
    //   580: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   583: invokevirtual setCpres6 : (Ljava/lang/String;)V
    //   586: aload #7
    //   588: aload_0
    //   589: getfield jsonObjectResult : Lorg/json/JSONObject;
    //   592: ldc_w 'cpres7'
    //   595: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   598: invokevirtual setCpres7 : (Ljava/lang/String;)V
    //   601: aload #7
    //   603: aload_0
    //   604: getfield jsonObjectResult : Lorg/json/JSONObject;
    //   607: ldc_w 'cpres8'
    //   610: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   613: invokevirtual setCpres8 : (Ljava/lang/String;)V
    //   616: aload #7
    //   618: aload_0
    //   619: getfield jsonObjectResult : Lorg/json/JSONObject;
    //   622: ldc_w 'cpres9'
    //   625: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   628: invokevirtual setCpres9 : (Ljava/lang/String;)V
    //   631: goto -> 661
    //   634: astore_1
    //   635: goto -> 647
    //   638: astore_1
    //   639: goto -> 647
    //   642: astore_1
    //   643: goto -> 647
    //   646: astore_1
    //   647: aload_0
    //   648: getfield TAG : Ljava/lang/String;
    //   651: ldc_w 'Error in getRenewalObject:'
    //   654: aload_1
    //   655: invokevirtual getMessage : ()Ljava/lang/String;
    //   658: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   661: aload #7
    //   663: areturn
    // Exception table:
    //   from	to	target	type
    //   17	107	646	org/json/JSONException
    //   127	353	642	org/json/JSONException
    //   362	562	642	org/json/JSONException
    //   565	571	638	org/json/JSONException
    //   571	631	634	org/json/JSONException
  }
  
  public List<GMTCatalog> parseGTMCatalogJsonString(String paramString) {
    ArrayList<GMTCatalog> arrayList = new ArrayList();
    try {
      JSONArray jSONArray = new JSONArray();
      this(paramString);
      this.jsonArrayPlaces = jSONArray;
      int i = this.jsonArrayPlaces.length();
      byte b = 0;
      if (i > 1) {
        while (b < i) {
          JSONObject jSONObject = this.jsonArrayPlaces.getJSONObject(b);
          GMTCatalog gMTCatalog = new GMTCatalog();
          this();
          gMTCatalog.setGmtId(jSONObject.getString("GmtId"));
          gMTCatalog.setGmtName(jSONObject.getString("GmtName"));
          gMTCatalog.setGmtPrefix(jSONObject.getString("GmtPrefix"));
          gMTCatalog.setGmtValue(jSONObject.getString("GmtValue"));
          arrayList.add(gMTCatalog);
          b++;
        } 
      } else {
        JSONObject jSONObject = this.jsonArrayPlaces.getJSONObject(0);
        GMTCatalog gMTCatalog = new GMTCatalog();
        this();
        gMTCatalog.setGmtId(jSONObject.getString("GmtId"));
        gMTCatalog.setGmtName(jSONObject.getString("GmtName"));
        gMTCatalog.setGmtPrefix(jSONObject.getString("GmtPrefix"));
        gMTCatalog.setGmtValue(jSONObject.getString("GmtValue"));
        arrayList.add(gMTCatalog);
      } 
    } catch (JSONException jSONException) {
      Utilities.escribeArchivo(this.TAG, "Error in toListObject:", jSONException.getMessage());
    } 
    return arrayList;
  }
  
  public String toJsonString(List paramList) {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #7
    //   9: new java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: astore #9
    //   18: aload_1
    //   19: ifnull -> 65
    //   22: aload_1
    //   23: invokeinterface size : ()I
    //   28: iconst_1
    //   29: if_icmplt -> 65
    //   32: aload_1
    //   33: iconst_0
    //   34: invokeinterface get : (I)Ljava/lang/Object;
    //   39: invokevirtual getClass : ()Ljava/lang/Class;
    //   42: astore #6
    //   44: aload_1
    //   45: iconst_0
    //   46: invokeinterface get : (I)Ljava/lang/Object;
    //   51: pop
    //   52: aload #9
    //   54: aload_0
    //   55: getfield OPEN_ARRAY : Ljava/lang/String;
    //   58: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: goto -> 68
    //   65: aconst_null
    //   66: astore #6
    //   68: aload #6
    //   70: invokevirtual getDeclaredFields : ()[Ljava/lang/reflect/Field;
    //   73: astore #10
    //   75: aload #6
    //   77: invokevirtual getDeclaredMethods : ()[Ljava/lang/reflect/Method;
    //   80: astore #8
    //   82: aload #7
    //   84: astore #6
    //   86: aload #8
    //   88: ifnull -> 154
    //   91: new java/util/ArrayList
    //   94: astore #7
    //   96: aload #7
    //   98: invokespecial <init> : ()V
    //   101: aload #8
    //   103: arraylength
    //   104: istore_3
    //   105: iconst_0
    //   106: istore_2
    //   107: aload #7
    //   109: astore #6
    //   111: iload_2
    //   112: iload_3
    //   113: if_icmpge -> 154
    //   116: aload #8
    //   118: iload_2
    //   119: aaload
    //   120: astore #6
    //   122: aload #6
    //   124: invokevirtual getName : ()Ljava/lang/String;
    //   127: invokevirtual trim : ()Ljava/lang/String;
    //   130: aload_0
    //   131: getfield GET : Ljava/lang/String;
    //   134: invokevirtual contains : (Ljava/lang/CharSequence;)Z
    //   137: ifeq -> 148
    //   140: aload #7
    //   142: aload #6
    //   144: invokevirtual add : (Ljava/lang/Object;)Z
    //   147: pop
    //   148: iinc #2, 1
    //   151: goto -> 107
    //   154: aload_1
    //   155: invokeinterface size : ()I
    //   160: istore_2
    //   161: iconst_0
    //   162: istore_3
    //   163: iload_3
    //   164: iload_2
    //   165: if_icmpge -> 715
    //   168: aload_1
    //   169: iload_3
    //   170: invokeinterface get : (I)Ljava/lang/Object;
    //   175: astore #11
    //   177: aload #9
    //   179: aload_0
    //   180: getfield OPEN_OBJECT : Ljava/lang/String;
    //   183: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: aload #10
    //   189: arraylength
    //   190: istore #5
    //   192: iconst_0
    //   193: istore #4
    //   195: iload #4
    //   197: iload #5
    //   199: if_icmpge -> 676
    //   202: aload #10
    //   204: iload #4
    //   206: aaload
    //   207: astore #12
    //   209: aload #12
    //   211: invokevirtual getName : ()Ljava/lang/String;
    //   214: astore #7
    //   216: aload #9
    //   218: aload_0
    //   219: getfield COMILLA : Ljava/lang/String;
    //   222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: aload #9
    //   228: aload #7
    //   230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: pop
    //   234: aload #9
    //   236: aload_0
    //   237: getfield COMILLA : Ljava/lang/String;
    //   240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: pop
    //   244: aload #9
    //   246: aload_0
    //   247: getfield VALOR : Ljava/lang/String;
    //   250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: pop
    //   254: aload #12
    //   256: invokevirtual getGenericType : ()Ljava/lang/reflect/Type;
    //   259: astore #14
    //   261: aload #6
    //   263: invokevirtual iterator : ()Ljava/util/Iterator;
    //   266: astore #13
    //   268: aload #13
    //   270: invokeinterface hasNext : ()Z
    //   275: ifeq -> 660
    //   278: aload #13
    //   280: invokeinterface next : ()Ljava/lang/Object;
    //   285: checkcast java/lang/reflect/Method
    //   288: astore #15
    //   290: aload #15
    //   292: invokevirtual getName : ()Ljava/lang/String;
    //   295: astore #8
    //   297: new java/lang/StringBuilder
    //   300: astore #7
    //   302: aload #7
    //   304: invokespecial <init> : ()V
    //   307: aload #7
    //   309: aload_0
    //   310: getfield GET : Ljava/lang/String;
    //   313: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: pop
    //   317: aload #7
    //   319: aload #12
    //   321: invokevirtual getName : ()Ljava/lang/String;
    //   324: invokevirtual trim : ()Ljava/lang/String;
    //   327: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: pop
    //   331: aload #8
    //   333: aload #7
    //   335: invokevirtual toString : ()Ljava/lang/String;
    //   338: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   341: ifeq -> 657
    //   344: aload #14
    //   346: invokevirtual toString : ()Ljava/lang/String;
    //   349: ldc_w 'java.lang.String'
    //   352: ldc_w 'String'
    //   355: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   358: astore #8
    //   360: aload #8
    //   362: ldc_w '\.'
    //   365: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   368: astore #7
    //   370: aload #7
    //   372: ifnull -> 395
    //   375: aload #7
    //   377: arraylength
    //   378: iconst_1
    //   379: if_icmple -> 395
    //   382: aload #7
    //   384: aload #7
    //   386: arraylength
    //   387: iconst_1
    //   388: isub
    //   389: aaload
    //   390: astore #7
    //   392: goto -> 435
    //   395: aload #8
    //   397: ldc_w ' '
    //   400: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   403: astore #16
    //   405: aload #8
    //   407: astore #7
    //   409: aload #16
    //   411: ifnull -> 435
    //   414: aload #8
    //   416: astore #7
    //   418: aload #16
    //   420: arraylength
    //   421: iconst_1
    //   422: if_icmple -> 435
    //   425: aload #16
    //   427: aload #16
    //   429: arraylength
    //   430: iconst_1
    //   431: isub
    //   432: aaload
    //   433: astore #7
    //   435: aload #15
    //   437: aload #11
    //   439: iconst_0
    //   440: anewarray java/lang/Object
    //   443: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   446: astore #8
    //   448: aload #7
    //   450: ldc_w 'String'
    //   453: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   456: ifeq -> 510
    //   459: aload #8
    //   461: ifnull -> 474
    //   464: aload #8
    //   466: invokevirtual toString : ()Ljava/lang/String;
    //   469: astore #7
    //   471: goto -> 479
    //   474: ldc_w ''
    //   477: astore #7
    //   479: aload #9
    //   481: aload_0
    //   482: getfield COMILLA : Ljava/lang/String;
    //   485: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   488: pop
    //   489: aload #9
    //   491: aload #7
    //   493: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: pop
    //   497: aload #9
    //   499: aload_0
    //   500: getfield COMILLA : Ljava/lang/String;
    //   503: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: pop
    //   507: goto -> 657
    //   510: aload #7
    //   512: ldc_w 'ArrayList<String>'
    //   515: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   518: ifeq -> 634
    //   521: aload #8
    //   523: checkcast java/util/ArrayList
    //   526: astore #7
    //   528: aload #9
    //   530: aload_0
    //   531: getfield OPEN_ARRAY : Ljava/lang/String;
    //   534: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   537: pop
    //   538: aload #7
    //   540: invokevirtual iterator : ()Ljava/util/Iterator;
    //   543: astore #7
    //   545: aload #7
    //   547: invokeinterface hasNext : ()Z
    //   552: ifeq -> 608
    //   555: aload #7
    //   557: invokeinterface next : ()Ljava/lang/Object;
    //   562: checkcast java/lang/String
    //   565: astore #8
    //   567: aload #9
    //   569: aload_0
    //   570: getfield COMILLA : Ljava/lang/String;
    //   573: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   576: pop
    //   577: aload #9
    //   579: aload #8
    //   581: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: pop
    //   585: aload #9
    //   587: aload_0
    //   588: getfield COMILLA : Ljava/lang/String;
    //   591: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   594: pop
    //   595: aload #9
    //   597: aload_0
    //   598: getfield COMA : Ljava/lang/String;
    //   601: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   604: pop
    //   605: goto -> 545
    //   608: aload #9
    //   610: aload #9
    //   612: invokevirtual length : ()I
    //   615: iconst_1
    //   616: isub
    //   617: invokevirtual deleteCharAt : (I)Ljava/lang/StringBuilder;
    //   620: pop
    //   621: aload #9
    //   623: aload_0
    //   624: getfield CLOSE_ARRAY : Ljava/lang/String;
    //   627: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: pop
    //   631: goto -> 657
    //   634: aload #9
    //   636: aload_0
    //   637: getfield COMILLA : Ljava/lang/String;
    //   640: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   643: pop
    //   644: aload #9
    //   646: aload_0
    //   647: getfield COMILLA : Ljava/lang/String;
    //   650: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   653: pop
    //   654: goto -> 657
    //   657: goto -> 268
    //   660: aload #9
    //   662: aload_0
    //   663: getfield COMA : Ljava/lang/String;
    //   666: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   669: pop
    //   670: iinc #4, 1
    //   673: goto -> 195
    //   676: aload #9
    //   678: aload #9
    //   680: invokevirtual length : ()I
    //   683: iconst_1
    //   684: isub
    //   685: invokevirtual deleteCharAt : (I)Ljava/lang/StringBuilder;
    //   688: pop
    //   689: aload #9
    //   691: aload_0
    //   692: getfield CLOSE_OBJECT : Ljava/lang/String;
    //   695: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   698: pop
    //   699: aload #9
    //   701: aload_0
    //   702: getfield COMA : Ljava/lang/String;
    //   705: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   708: pop
    //   709: iinc #3, 1
    //   712: goto -> 163
    //   715: aload #9
    //   717: aload #9
    //   719: invokevirtual length : ()I
    //   722: iconst_1
    //   723: isub
    //   724: invokevirtual deleteCharAt : (I)Ljava/lang/StringBuilder;
    //   727: pop
    //   728: aload #9
    //   730: aload_0
    //   731: getfield CLOSE_ARRAY : Ljava/lang/String;
    //   734: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   737: pop
    //   738: goto -> 756
    //   741: astore_1
    //   742: aload_0
    //   743: getfield TAG : Ljava/lang/String;
    //   746: ldc_w 'NOMBRE'
    //   749: aload_1
    //   750: invokevirtual getMessage : ()Ljava/lang/String;
    //   753: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   756: aload #9
    //   758: invokevirtual toString : ()Ljava/lang/String;
    //   761: areturn
    // Exception table:
    //   from	to	target	type
    //   22	62	741	java/lang/Exception
    //   68	82	741	java/lang/Exception
    //   91	105	741	java/lang/Exception
    //   122	148	741	java/lang/Exception
    //   154	161	741	java/lang/Exception
    //   168	192	741	java/lang/Exception
    //   209	268	741	java/lang/Exception
    //   268	370	741	java/lang/Exception
    //   375	392	741	java/lang/Exception
    //   395	405	741	java/lang/Exception
    //   418	435	741	java/lang/Exception
    //   435	459	741	java/lang/Exception
    //   464	471	741	java/lang/Exception
    //   479	507	741	java/lang/Exception
    //   510	545	741	java/lang/Exception
    //   545	605	741	java/lang/Exception
    //   608	631	741	java/lang/Exception
    //   634	654	741	java/lang/Exception
    //   660	670	741	java/lang/Exception
    //   676	709	741	java/lang/Exception
    //   715	738	741	java/lang/Exception
  }
  
  public PaymentCardResponse toListObject(String paramString, PaymentCardResponse paramPaymentCardResponse) {
    try {
      JSONObject jSONObject = new JSONObject();
      this(paramString);
      this.jsonObjectResult = jSONObject;
      paramPaymentCardResponse.setGcmures1(this.jsonObjectResult.getString("gcmures1"));
      paramPaymentCardResponse.setGcmures2(this.jsonObjectResult.getString("gcmures2"));
      paramPaymentCardResponse.setGcmures3(this.jsonObjectResult.getString("gcmures3"));
    } catch (JSONException jSONException) {
      Utilities.escribeArchivo(this.TAG, "Error in toListObject:", jSONException.getMessage());
    } 
    return paramPaymentCardResponse;
  }
  
  public SuscriptionResponse toListObject(String paramString, SuscriptionResponse paramSuscriptionResponse) {
    try {
      JSONObject jSONObject = new JSONObject();
      this(paramString);
      this.jsonObjectResult = jSONObject;
      paramSuscriptionResponse.setSubscres1(this.jsonObjectResult.getString("subscres1"));
      paramSuscriptionResponse.setSubscres2(this.jsonObjectResult.getString("subscres2"));
      paramSuscriptionResponse.setSubscres3(this.jsonObjectResult.getString("subscres3"));
    } catch (JSONException jSONException) {
      Utilities.escribeArchivo(this.TAG, "Error in toListObject:", jSONException.getMessage());
    } 
    return paramSuscriptionResponse;
  }
  
  public TokenResponseO toListObject(String paramString, TokenResponseO paramTokenResponseO) {
    new ArrayList();
    new ArrayList();
    try {
      JSONObject jSONObject = new JSONObject();
      this(paramString);
      this.jsonObjectResult = jSONObject;
      paramTokenResponseO.setLresp1(this.jsonObjectResult.getString("lresp1"));
      paramTokenResponseO.setLresp2(this.jsonObjectResult.getString("lresp2"));
      paramTokenResponseO.setLresp3(this.jsonObjectResult.getString("lresp3"));
      paramTokenResponseO.setLresp4(this.jsonObjectResult.getString("lresp4"));
      paramTokenResponseO.setLresp5(this.jsonObjectResult.getString("lresp5"));
      paramTokenResponseO.setLresp6(this.jsonObjectResult.getString("lresp6"));
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, "toListObject:TokenResponse", exception.getMessage());
    } 
    return paramTokenResponseO;
  }
  
  public TransactionResponse toListObject(String paramString, TransactionResponse paramTransactionResponse) {
    try {
      JSONObject jSONObject = new JSONObject();
      this(paramString);
      this.jsonObjectResult = jSONObject;
      paramTransactionResponse.setCposres1(this.jsonObjectResult.getString("cposres1"));
      paramTransactionResponse.setCposres2(this.jsonObjectResult.getString("cposres2"));
      paramTransactionResponse.setCposres3(this.jsonObjectResult.getString("cposres3"));
    } catch (JSONException jSONException) {
      Utilities.escribeArchivo(this.TAG, "Error in toListObject:", jSONException.getMessage());
    } 
    return paramTransactionResponse;
  }
  
  public List<Place> toListObject(String paramString) {
    ArrayList<Object> arrayList1 = new ArrayList();
    ArrayList<Place> arrayList = new ArrayList();
    try {
      JSONObject jSONObject = new JSONObject();
      this(paramString);
      this.jsonObjectResult = jSONObject;
      this.next_page_token = this.jsonObjectResult.optString("next_page_token");
      this.jsonArrayPlaces = this.jsonObjectResult.getJSONArray(KEY_RESULTS);
      int i = this.jsonArrayPlaces.length();
      ArrayList<Place> arrayList2 = arrayList;
      if (i > 0) {
        byte b = 0;
        while (true) {
          arrayList2 = arrayList;
          if (b < i) {
            arrayList1.add(this.jsonArrayPlaces.get(b));
            arrayList.add(parseObject(this.jsonArrayPlaces.getJSONObject(b)));
            b++;
            continue;
          } 
          break;
        } 
      } 
    } catch (JSONException jSONException) {
      Utilities.escribeArchivo(this.TAG, "toListObject", jSONException.getMessage());
      jSONException = null;
    } 
    return (List<Place>)jSONException;
  }
  
  public List<PaymentHistoryResponseO> toListObject(String paramString, Class paramClass) {
    ArrayList<PaymentHistoryResponseO> arrayList = new ArrayList();
    try {
      JSONArray jSONArray = new JSONArray();
      this(paramString);
      this.jsonArrayPlaces = jSONArray;
      int i = this.jsonArrayPlaces.length();
      byte b = 0;
      if (i > 1) {
        while (b < i) {
          JSONObject jSONObject = this.jsonArrayPlaces.getJSONObject(b);
          PaymentHistoryResponseO paymentHistoryResponseO = new PaymentHistoryResponseO();
          this();
          paymentHistoryResponseO.setChpres1(jSONObject.getString("chpres1"));
          paymentHistoryResponseO.setChpres2(jSONObject.getString("chpres2"));
          paymentHistoryResponseO.setChpres3(jSONObject.getString("chpres3"));
          paymentHistoryResponseO.setChpres4(jSONObject.getString("chpres4"));
          paymentHistoryResponseO.setChpres5(jSONObject.getString("chpres5"));
          paymentHistoryResponseO.setChpres6(jSONObject.getString("chpres6"));
          arrayList.add(paymentHistoryResponseO);
          b++;
        } 
      } else {
        JSONObject jSONObject = this.jsonArrayPlaces.getJSONObject(0);
        PaymentHistoryResponseO paymentHistoryResponseO = new PaymentHistoryResponseO();
        this();
        paymentHistoryResponseO.setChpres1(jSONObject.getString("chpres1"));
        paymentHistoryResponseO.setChpres2(jSONObject.getString("chpres2"));
        paymentHistoryResponseO.setChpres3(jSONObject.getString("chpres3"));
        paymentHistoryResponseO.setChpres4(jSONObject.getString("chpres4"));
        paymentHistoryResponseO.setChpres5(jSONObject.getString("chpres5"));
        paymentHistoryResponseO.setChpres6(jSONObject.getString("chpres6"));
        arrayList.add(paymentHistoryResponseO);
      } 
    } catch (JSONException jSONException) {
      Utilities.escribeArchivo(this.TAG, "Error in toListObject:", jSONException.getMessage());
    } 
    return arrayList;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/gson/GsonC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */