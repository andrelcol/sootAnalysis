package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedHashMap;

public class GetPaymentHistoricalTask extends AsyncTask<Void, Void, String> {
  public final String TAG = PaymentCard_Task.class.getSimpleName();
  
  private Base_Interface asyncResponse = null;
  
  private Context context;
  
  private LinkedHashMap<String, Object> params;
  
  private ProgressDialog progressDialog;
  
  public GetPaymentHistoricalTask(Context paramContext, Base_Interface paramBase_Interface, LinkedHashMap<String, Object> paramLinkedHashMap) {
    this.context = paramContext;
    this.asyncResponse = paramBase_Interface;
    this.params = paramLinkedHashMap;
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
    //   36: ldc 'MoTokn'
    //   38: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   41: checkcast java/lang/String
    //   44: astore_2
    //   45: aload_1
    //   46: ldc 'DaTokn'
    //   48: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   51: checkcast java/lang/String
    //   54: astore_3
    //   55: aload_2
    //   56: ifnull -> 75
    //   59: aload_2
    //   60: invokevirtual isEmpty : ()Z
    //   63: ifne -> 75
    //   66: aload_3
    //   67: astore_1
    //   68: aload_3
    //   69: invokestatic isValidToken : (Ljava/lang/String;)Z
    //   72: ifne -> 91
    //   75: aload #4
    //   77: invokevirtual getRenewalToken : ()Lcom/roadtrack/onstar/VO/TokenResponseO;
    //   80: astore_1
    //   81: aload_1
    //   82: invokevirtual getLresp1 : ()Ljava/lang/String;
    //   85: astore_2
    //   86: aload_1
    //   87: invokevirtual getLresp6 : ()Ljava/lang/String;
    //   90: astore_1
    //   91: aload_2
    //   92: ifnull -> 163
    //   95: aload_2
    //   96: invokevirtual isEmpty : ()Z
    //   99: ifeq -> 105
    //   102: goto -> 163
    //   105: aload_2
    //   106: aload_1
    //   107: aload_0
    //   108: getfield context : Landroid/content/Context;
    //   111: invokestatic saveTokenAndDate : (Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
    //   114: aload #4
    //   116: aload_2
    //   117: aload_0
    //   118: getfield params : Ljava/util/LinkedHashMap;
    //   121: invokevirtual getPaymentHistoricalTask : (Ljava/lang/String;Ljava/util/LinkedHashMap;)Ljava/lang/String;
    //   124: astore_3
    //   125: aload_0
    //   126: getfield TAG : Ljava/lang/String;
    //   129: astore_2
    //   130: new java/lang/StringBuilder
    //   133: astore_1
    //   134: aload_1
    //   135: invokespecial <init> : ()V
    //   138: aload_1
    //   139: ldc ':'
    //   141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: pop
    //   145: aload_1
    //   146: aload_3
    //   147: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: pop
    //   151: aload_2
    //   152: ldc 'WS result'
    //   154: aload_1
    //   155: invokevirtual toString : ()Ljava/lang/String;
    //   158: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   161: aload_3
    //   162: areturn
    //   163: aconst_null
    //   164: areturn
    //   165: astore_1
    //   166: aload_0
    //   167: getfield TAG : Ljava/lang/String;
    //   170: ldc 'Error'
    //   172: aload_1
    //   173: invokevirtual getMessage : ()Ljava/lang/String;
    //   176: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   179: aconst_null
    //   180: areturn
    // Exception table:
    //   from	to	target	type
    //   13	55	165	java/lang/Exception
    //   59	66	165	java/lang/Exception
    //   68	75	165	java/lang/Exception
    //   75	91	165	java/lang/Exception
    //   95	102	165	java/lang/Exception
    //   105	161	165	java/lang/Exception
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    try {
      this.progressDialog.dismiss();
    } catch (Exception exception) {
      Utilities.escribeArchivo(this.TAG, "Close Progress Dialog", exception.getMessage());
    } 
    Base_Interface base_Interface = this.asyncResponse;
    if (base_Interface != null)
      base_Interface.onSuccess(paramString); 
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


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetPaymentHistoricalTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */