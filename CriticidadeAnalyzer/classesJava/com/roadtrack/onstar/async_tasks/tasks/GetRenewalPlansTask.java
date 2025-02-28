package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.VO.RenewalPlanO;
import com.roadtrack.onstar.VO.RenewalPlansListResponseO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.RenewalPlans_Interface;
import com.roadtrack.onstar.gson.GsonC;
import com.roadtrack.onstar.utils.Utilities;
import java.util.List;

public class GetRenewalPlansTask extends AsyncTask<Void, Void, String> {
  public static final String TAG = GetRenewalPlansTask.class.getSimpleName();
  
  private RenewalPlans_Interface asyncResponse = null;
  
  private Context context;
  
  private ProgressDialog progressDialog;
  
  public GetRenewalPlansTask(Context paramContext, RenewalPlans_Interface paramRenewalPlans_Interface) {
    this.context = paramContext;
    this.asyncResponse = paramRenewalPlans_Interface;
  }
  
  private RenewalPlansListResponseO decryptResponse(RenewalPlansListResponseO paramRenewalPlansListResponseO) {
    paramRenewalPlansListResponseO.setCpres1(Utilities.DecryptMoip(paramRenewalPlansListResponseO.getCpres1()));
    paramRenewalPlansListResponseO.setCpres2(Utilities.decodeStringUTF(Utilities.DecryptMoip(paramRenewalPlansListResponseO.getCpres2())));
    paramRenewalPlansListResponseO.setCpres3(Utilities.decodeStringUTF(Utilities.DecryptMoip(paramRenewalPlansListResponseO.getCpres3())));
    paramRenewalPlansListResponseO.setCpres4(Utilities.DecryptMoip(paramRenewalPlansListResponseO.getCpres4()));
    paramRenewalPlansListResponseO.setCpres6(Utilities.DecryptMoip(paramRenewalPlansListResponseO.getCpres6()));
    paramRenewalPlansListResponseO.setCpres7(Utilities.DecryptMoip(paramRenewalPlansListResponseO.getCpres7()));
    paramRenewalPlansListResponseO.setCpres8(Utilities.DecryptMoip(paramRenewalPlansListResponseO.getCpres8()));
    paramRenewalPlansListResponseO.setCpres9(Utilities.DecryptMoip(paramRenewalPlansListResponseO.getCpres9()));
    String str = TAG;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("cpres1 = ");
    stringBuilder.append(paramRenewalPlansListResponseO.getCpres1());
    stringBuilder.append("cpres2 = ");
    stringBuilder.append(paramRenewalPlansListResponseO.getCpres2());
    stringBuilder.append("cpres3 = ");
    stringBuilder.append(paramRenewalPlansListResponseO.getCpres3());
    stringBuilder.append("cpres4 = ");
    stringBuilder.append(paramRenewalPlansListResponseO.getCpres4());
    stringBuilder.append("cpres6 = ");
    stringBuilder.append(paramRenewalPlansListResponseO.getCpres6());
    stringBuilder.append("cpres7 = ");
    stringBuilder.append(paramRenewalPlansListResponseO.getCpres7());
    stringBuilder.append("cpres8 = ");
    stringBuilder.append(paramRenewalPlansListResponseO.getCpres8());
    stringBuilder.append("cpres9 = ");
    stringBuilder.append(paramRenewalPlansListResponseO.getCpres9());
    Utilities.escribeArchivo(str, "Response", stringBuilder.toString());
    if (paramRenewalPlansListResponseO.getCpres5() != null && paramRenewalPlansListResponseO.getCpres5().size() > 0) {
      List<RenewalPlanO> list = paramRenewalPlansListResponseO.getCpres5();
      for (byte b = 0; b < list.size(); b++) {
        ((RenewalPlanO)list.get(b)).setCpapres1(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres1()));
        ((RenewalPlanO)list.get(b)).setCpapres2(Utilities.decodeStringUTF(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres2())));
        ((RenewalPlanO)list.get(b)).setCpapres3(Utilities.decodeStringUTF(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres3())));
        ((RenewalPlanO)list.get(b)).setCpapres4(Utilities.decodeStringUTF(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres4())));
        ((RenewalPlanO)list.get(b)).setCpapres5(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres5()));
        ((RenewalPlanO)list.get(b)).setCpapres6(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres6()));
        ((RenewalPlanO)list.get(b)).setCpapres7(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres7()));
        ((RenewalPlanO)list.get(b)).setCpapres8(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres8()));
        ((RenewalPlanO)list.get(b)).setCpapres9(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres9()));
        ((RenewalPlanO)list.get(b)).setCpapres10(Utilities.decodeStringUTF(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres10())));
        ((RenewalPlanO)list.get(b)).setCpapres11(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres11()));
        ((RenewalPlanO)list.get(b)).setCpapres12(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres12()));
        ((RenewalPlanO)list.get(b)).setCpapres13(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres13()));
        ((RenewalPlanO)list.get(b)).setCpapres14(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres14()));
        ((RenewalPlanO)list.get(b)).setCpapres15(Utilities.DecryptMoip(((RenewalPlanO)list.get(b)).getCpapres15()));
        String str1 = TAG;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("cpapres1 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres1());
        stringBuilder1.append(" cpapres2 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres2());
        stringBuilder1.append(" cpapres3 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres3());
        stringBuilder1.append(" cpapres4 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres4());
        stringBuilder1.append(" cpapres5 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres5());
        stringBuilder1.append(" cpapres6 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres6());
        stringBuilder1.append(" cpapres7 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres7());
        stringBuilder1.append(" cpapres8 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres8());
        stringBuilder1.append(" cpapres9 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres9());
        stringBuilder1.append(" cpapres10 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres10());
        stringBuilder1.append(" cpapres11 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres11());
        stringBuilder1.append(" cpapres12 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres12());
        stringBuilder1.append(" cpapres13 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres13());
        stringBuilder1.append(" cpapres14 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres14());
        stringBuilder1.append(" cpapres15 = ");
        stringBuilder1.append(((RenewalPlanO)list.get(b)).getCpapres15());
        Utilities.escribeArchivo(str1, "Response", stringBuilder1.toString());
      } 
      paramRenewalPlansListResponseO.setCpres5(list);
    } 
    return paramRenewalPlansListResponseO;
  }
  
  private RenewalPlansListResponseO getObjectResult(String paramString) {
    if (isValidResponseStructure(paramString) && paramString != null && !paramString.isEmpty()) {
      paramString = paramString.replace("\\\"", "\"");
      paramString = paramString.substring(1, paramString.length() - 1);
      RenewalPlansListResponseO renewalPlansListResponseO = (new GsonC()).getRenewalObject(paramString);
      decryptResponse(renewalPlansListResponseO);
      if (renewalPlansListResponseO.getCpres3().equalsIgnoreCase("OMS")) {
        renewalPlansListResponseO.isOMSPlan();
        RenewalPlansListResponseO renewalPlansListResponseO1 = renewalPlansListResponseO;
      } else {
        RenewalPlansListResponseO renewalPlansListResponseO1 = renewalPlansListResponseO;
        if (renewalPlansListResponseO.isValidObject())
          return renewalPlansListResponseO; 
      } 
    } else {
      paramString = null;
    } 
    return (RenewalPlansListResponseO)paramString;
  }
  
  private boolean isValidResponseStructure(String paramString) {
    return true;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    // Byte code:
    //   0: aload_0
    //   1: getfield context : Landroid/content/Context;
    //   4: iconst_0
    //   5: invokestatic validateNetwork : (Landroid/content/Context;Z)Z
    //   8: ifne -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: new com/roadtrack/onstar/BO/WsAccess
    //   16: astore #4
    //   18: aload #4
    //   20: aload_0
    //   21: getfield context : Landroid/content/Context;
    //   24: invokespecial <init> : (Landroid/content/Context;)V
    //   27: aload_0
    //   28: getfield context : Landroid/content/Context;
    //   31: invokestatic getTokenAndDate : (Landroid/content/Context;)Ljava/util/HashMap;
    //   34: astore_1
    //   35: aload_1
    //   36: ldc_w 'MoTokn'
    //   39: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   42: checkcast java/lang/String
    //   45: astore_2
    //   46: aload_1
    //   47: ldc_w 'DaTokn'
    //   50: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   53: checkcast java/lang/String
    //   56: astore_3
    //   57: aload_2
    //   58: ifnull -> 77
    //   61: aload_2
    //   62: invokevirtual isEmpty : ()Z
    //   65: ifne -> 77
    //   68: aload_3
    //   69: astore_1
    //   70: aload_3
    //   71: invokestatic isValidToken : (Ljava/lang/String;)Z
    //   74: ifne -> 93
    //   77: aload #4
    //   79: invokevirtual getRenewalToken : ()Lcom/roadtrack/onstar/VO/TokenResponseO;
    //   82: astore_1
    //   83: aload_1
    //   84: invokevirtual getLresp1 : ()Ljava/lang/String;
    //   87: astore_2
    //   88: aload_1
    //   89: invokevirtual getLresp6 : ()Ljava/lang/String;
    //   92: astore_1
    //   93: aload_2
    //   94: invokevirtual isEmpty : ()Z
    //   97: ifne -> 125
    //   100: aload_2
    //   101: ifnonnull -> 107
    //   104: goto -> 125
    //   107: aload_2
    //   108: aload_1
    //   109: aload_0
    //   110: getfield context : Landroid/content/Context;
    //   113: invokestatic saveTokenAndDate : (Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
    //   116: aload #4
    //   118: aload_2
    //   119: invokevirtual getRenewalPlans : (Ljava/lang/String;)Ljava/lang/String;
    //   122: astore_1
    //   123: aload_1
    //   124: areturn
    //   125: aconst_null
    //   126: areturn
    //   127: astore_1
    //   128: getstatic com/roadtrack/onstar/async_tasks/tasks/GetRenewalPlansTask.TAG : Ljava/lang/String;
    //   131: ldc_w 'Error'
    //   134: aload_1
    //   135: invokevirtual getMessage : ()Ljava/lang/String;
    //   138: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   141: aconst_null
    //   142: areturn
    // Exception table:
    //   from	to	target	type
    //   13	57	127	java/lang/Exception
    //   61	68	127	java/lang/Exception
    //   70	77	127	java/lang/Exception
    //   77	93	127	java/lang/Exception
    //   93	100	127	java/lang/Exception
    //   107	123	127	java/lang/Exception
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    try {
      this.progressDialog.dismiss();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Close Progress Dialog", exception.getMessage());
    } 
    RenewalPlansListResponseO renewalPlansListResponseO = getObjectResult(paramString);
    RenewalPlans_Interface renewalPlans_Interface = this.asyncResponse;
    if (renewalPlans_Interface != null)
      renewalPlans_Interface.processFinish(renewalPlansListResponseO); 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.please_wait, 2131690270);
    this.progressDialog = new ProgressDialog(this.context, 2131755173);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setCancelable(false);
    this.progressDialog.setMessage(str);
    this.progressDialog.show();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetRenewalPlansTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */