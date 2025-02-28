package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedHashMap;

public class GetPaymentTransactionTask extends AsyncTask<Void, Void, String> {
  public static final String TAG = GetPaymentTransactionTask.class.getSimpleName();
  
  private AsyncResponse asyncResponse = null;
  
  private Context context;
  
  private LinkedHashMap<String, Object> params;
  
  private ProgressDialog progressDialog;
  
  public GetPaymentTransactionTask(Context paramContext, AsyncResponse paramAsyncResponse, LinkedHashMap<String, Object> paramLinkedHashMap) {
    this.context = paramContext;
    this.asyncResponse = paramAsyncResponse;
    this.params = paramLinkedHashMap;
  }
  
  private boolean isValidResponseStructure(String paramString) {
    boolean bool;
    if (paramString != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
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
    //   34: astore_2
    //   35: aload_2
    //   36: ldc 'MoTokn'
    //   38: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   41: checkcast java/lang/String
    //   44: astore_1
    //   45: aload_2
    //   46: ldc 'DaTokn'
    //   48: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   51: checkcast java/lang/String
    //   54: astore_3
    //   55: aload_1
    //   56: ifnull -> 75
    //   59: aload_1
    //   60: invokevirtual isEmpty : ()Z
    //   63: ifne -> 75
    //   66: aload_3
    //   67: astore_2
    //   68: aload_3
    //   69: invokestatic isValidToken : (Ljava/lang/String;)Z
    //   72: ifne -> 91
    //   75: aload #4
    //   77: invokevirtual getRenewalToken : ()Lcom/roadtrack/onstar/VO/TokenResponseO;
    //   80: astore_2
    //   81: aload_2
    //   82: invokevirtual getLresp1 : ()Ljava/lang/String;
    //   85: astore_1
    //   86: aload_2
    //   87: invokevirtual getLresp6 : ()Ljava/lang/String;
    //   90: astore_2
    //   91: aload_1
    //   92: invokevirtual isEmpty : ()Z
    //   95: ifne -> 178
    //   98: aload_1
    //   99: ifnonnull -> 105
    //   102: goto -> 178
    //   105: aload_1
    //   106: aload_2
    //   107: aload_0
    //   108: getfield context : Landroid/content/Context;
    //   111: invokestatic saveTokenAndDate : (Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
    //   114: aload_0
    //   115: getfield params : Ljava/util/LinkedHashMap;
    //   118: ldc 'renewalKind'
    //   120: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   123: invokevirtual toString : ()Ljava/lang/String;
    //   126: invokestatic parseInt : (Ljava/lang/String;)I
    //   129: pop
    //   130: aload #4
    //   132: aload_1
    //   133: aload_0
    //   134: getfield params : Ljava/util/LinkedHashMap;
    //   137: invokevirtual getPaymentTransactionSubscription : (Ljava/lang/String;Ljava/util/LinkedHashMap;)Ljava/lang/String;
    //   140: astore_2
    //   141: getstatic com/roadtrack/onstar/async_tasks/tasks/GetPaymentTransactionTask.TAG : Ljava/lang/String;
    //   144: astore_3
    //   145: new java/lang/StringBuilder
    //   148: astore_1
    //   149: aload_1
    //   150: invokespecial <init> : ()V
    //   153: aload_1
    //   154: ldc ':'
    //   156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload_1
    //   161: aload_2
    //   162: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: pop
    //   166: aload_3
    //   167: ldc 'WS result'
    //   169: aload_1
    //   170: invokevirtual toString : ()Ljava/lang/String;
    //   173: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   176: aload_2
    //   177: areturn
    //   178: aconst_null
    //   179: areturn
    //   180: astore_1
    //   181: getstatic com/roadtrack/onstar/async_tasks/tasks/GetPaymentTransactionTask.TAG : Ljava/lang/String;
    //   184: ldc 'Error'
    //   186: aload_1
    //   187: invokevirtual getMessage : ()Ljava/lang/String;
    //   190: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   193: aconst_null
    //   194: areturn
    // Exception table:
    //   from	to	target	type
    //   13	55	180	java/lang/Exception
    //   59	66	180	java/lang/Exception
    //   68	75	180	java/lang/Exception
    //   75	91	180	java/lang/Exception
    //   91	98	180	java/lang/Exception
    //   105	176	180	java/lang/Exception
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    try {
      this.progressDialog.dismiss();
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Close Progress Dialog", exception.getMessage());
    } 
    String str = paramString;
    if (!isValidResponseStructure(paramString))
      str = null; 
    AsyncResponse asyncResponse = this.asyncResponse;
    if (asyncResponse != null)
      asyncResponse.processFinish(str); 
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


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetPaymentTransactionTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */