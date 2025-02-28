package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.utils.Utilities;

public class GetPaymentsHistoryTask extends AsyncTask<Void, Void, String> {
  public static final String TAG = GetPaymentsHistoryTask.class.getSimpleName();
  
  private String GMTID;
  
  private AsyncResponse asyncResponse = null;
  
  private Context context;
  
  private ProgressDialog progressDialog;
  
  public GetPaymentsHistoryTask(Context paramContext, AsyncResponse paramAsyncResponse, String paramString) {
    this.context = paramContext;
    this.asyncResponse = paramAsyncResponse;
    this.GMTID = paramString;
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
    //   92: invokevirtual isEmpty : ()Z
    //   95: ifne -> 162
    //   98: aload_2
    //   99: ifnonnull -> 105
    //   102: goto -> 162
    //   105: aload_2
    //   106: aload_1
    //   107: aload_0
    //   108: getfield context : Landroid/content/Context;
    //   111: invokestatic saveTokenAndDate : (Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
    //   114: aload #4
    //   116: aload_2
    //   117: aload_0
    //   118: getfield GMTID : Ljava/lang/String;
    //   121: invokevirtual getPaymentHistory : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   124: astore_3
    //   125: getstatic com/roadtrack/onstar/async_tasks/tasks/GetPaymentsHistoryTask.TAG : Ljava/lang/String;
    //   128: astore_1
    //   129: new java/lang/StringBuilder
    //   132: astore_2
    //   133: aload_2
    //   134: invokespecial <init> : ()V
    //   137: aload_2
    //   138: ldc ':'
    //   140: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: aload_2
    //   145: aload_3
    //   146: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: pop
    //   150: aload_1
    //   151: ldc 'WS result'
    //   153: aload_2
    //   154: invokevirtual toString : ()Ljava/lang/String;
    //   157: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   160: aload_3
    //   161: areturn
    //   162: aconst_null
    //   163: areturn
    //   164: astore_1
    //   165: getstatic com/roadtrack/onstar/async_tasks/tasks/GetPaymentsHistoryTask.TAG : Ljava/lang/String;
    //   168: ldc 'Error'
    //   170: aload_1
    //   171: invokevirtual getMessage : ()Ljava/lang/String;
    //   174: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   177: aconst_null
    //   178: areturn
    // Exception table:
    //   from	to	target	type
    //   13	55	164	java/lang/Exception
    //   59	66	164	java/lang/Exception
    //   68	75	164	java/lang/Exception
    //   75	91	164	java/lang/Exception
    //   91	98	164	java/lang/Exception
    //   105	160	164	java/lang/Exception
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


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetPaymentsHistoryTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */