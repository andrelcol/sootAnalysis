package com.roadtrack.onstar.async_tasks.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.utils.Utilities;

public class GetPdfCipherKeyTask extends AsyncTask<Void, Void, String> {
  public static final String TAG = GetPdfCipherKeyTask.class.getName();
  
  private AsyncResponse asyncResponse;
  
  private Context context;
  
  private ProgressDialog progressDialog;
  
  private Boolean showProgress;
  
  public GetPdfCipherKeyTask(Context paramContext, AsyncResponse paramAsyncResponse, boolean paramBoolean) {
    this.context = paramContext;
    this.asyncResponse = paramAsyncResponse;
    this.showProgress = Boolean.valueOf(paramBoolean);
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
    //   95: ifne -> 158
    //   98: aload_2
    //   99: ifnonnull -> 105
    //   102: goto -> 158
    //   105: aload_2
    //   106: aload_1
    //   107: aload_0
    //   108: getfield context : Landroid/content/Context;
    //   111: invokestatic saveTokenAndDate : (Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
    //   114: aload #4
    //   116: aload_2
    //   117: invokevirtual getAvailableTicket : (Ljava/lang/String;)Ljava/lang/String;
    //   120: astore_2
    //   121: getstatic com/roadtrack/onstar/async_tasks/tasks/GetPdfCipherKeyTask.TAG : Ljava/lang/String;
    //   124: astore_3
    //   125: new java/lang/StringBuilder
    //   128: astore_1
    //   129: aload_1
    //   130: invokespecial <init> : ()V
    //   133: aload_1
    //   134: ldc ':'
    //   136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload_1
    //   141: aload_2
    //   142: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: aload_3
    //   147: ldc 'WS result'
    //   149: aload_1
    //   150: invokevirtual toString : ()Ljava/lang/String;
    //   153: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   156: aload_2
    //   157: areturn
    //   158: aconst_null
    //   159: areturn
    //   160: astore_1
    //   161: getstatic com/roadtrack/onstar/async_tasks/tasks/GetPdfCipherKeyTask.TAG : Ljava/lang/String;
    //   164: ldc 'Error'
    //   166: aload_1
    //   167: invokevirtual getMessage : ()Ljava/lang/String;
    //   170: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   173: aconst_null
    //   174: areturn
    // Exception table:
    //   from	to	target	type
    //   13	55	160	java/lang/Exception
    //   59	66	160	java/lang/Exception
    //   68	75	160	java/lang/Exception
    //   75	91	160	java/lang/Exception
    //   91	98	160	java/lang/Exception
    //   105	156	160	java/lang/Exception
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    if (this.showProgress.booleanValue())
      try {
        this.progressDialog.dismiss();
      } catch (Exception exception) {
        Utilities.escribeArchivo(TAG, "Close Progress Dialog", exception.getMessage());
      }  
    AsyncResponse asyncResponse = this.asyncResponse;
    if (asyncResponse != null)
      asyncResponse.processFinish(paramString); 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(this.context, stringsResourcesVO.please_wait, 2131690270);
    if (this.showProgress.booleanValue()) {
      this.progressDialog = new ProgressDialog(this.context, 2131755173);
      this.progressDialog.setIndeterminate(true);
      this.progressDialog.setCancelable(false);
      this.progressDialog.setMessage(str);
      this.progressDialog.show();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetPdfCipherKeyTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */