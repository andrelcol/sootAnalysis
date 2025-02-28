package com.roadtrack.onstar.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.sockets.TlsOnlySocketFactory;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class NetUtilities {
  private static String TAG = "NetUtilities";
  
  private static String dummy;
  
  static {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(FindObject.obj1);
    stringBuilder.append(UtilitiesFile.segundo);
    stringBuilder.append(DeviceUtils.ultimo);
    dummy = stringBuilder.toString();
  }
  
  public static NetServiceResponseEnum isThereNETService(NetServiceRequestEnum paramNetServiceRequestEnum, Context paramContext) {
    NetServiceResponseEnum netServiceResponseEnum1;
    NetServiceResponseEnum netServiceResponseEnum2 = NetServiceResponseEnum.THERESNOSERVICE;
    try {
      ConnectivityManager connectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(1);
      NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
      if (networkInfo1.isConnected()) {
        netServiceResponseEnum1 = NetServiceResponseEnum.SERVICEWIFIOK;
      } else {
        netServiceResponseEnum1 = netServiceResponseEnum2;
        if (networkInfo2.isConnected())
          netServiceResponseEnum1 = NetServiceResponseEnum.SERVICEMOBILEOK; 
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: NetServiceResponseEnum", exception.getMessage());
      netServiceResponseEnum1 = netServiceResponseEnum2;
    } 
    return netServiceResponseEnum1;
  }
  
  public static void sendTermsAndConditions(Context paramContext, String paramString) {
    DBFunctions dBFunctions = new DBFunctions(paramContext);
    UserPreferenceVO userPreferenceVO = dBFunctions.getUserPreference(GlobalMembers.userLogged);
    if (userPreferenceVO != null && userPreferenceVO.getTermsAndConditionsSent() != null && !userPreferenceVO.getTermsAndConditionsSent().isEmpty() && userPreferenceVO.getTermsAndConditionsSent().equals("0"))
      (new SendTermsAndConditionsAsyncTask(paramContext, paramString, dBFunctions)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]); 
  }
  
  @SuppressLint({"SdCardPath"})
  public static boolean setUpHttpsConnection(String paramString1, Context paramContext, int paramInt, String paramString2) {
    try {
      TlsOnlySocketFactory tlsOnlySocketFactory;
      KeyStore keyStore = KeyStore.getInstance("bks");
      InputStream inputStream = Utilities.getKeyStoreFromConfigList(paramContext, paramString2, paramInt);
      try {
        keyStore.load(inputStream, dummy.toCharArray());
        inputStream.close();
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        sSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
        tlsOnlySocketFactory = new TlsOnlySocketFactory();
        this(sSLContext.getSocketFactory());
        URL uRL = new URL();
        this(paramString1);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection)uRL.openConnection();
        httpsURLConnection.setSSLSocketFactory((SSLSocketFactory)tlsOnlySocketFactory);
        httpsURLConnection.setConnectTimeout(20000);
        httpsURLConnection.setReadTimeout(20000);
        httpsURLConnection.getInputStream().toString();
        return true;
      } finally {
        tlsOnlySocketFactory.close();
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: Exception", "Exception");
      return false;
    } 
  }
  
  public static boolean validateNetwork(Context paramContext) {
    return validateNetwork(paramContext, true);
  }
  
  public static boolean validateNetwork(Context paramContext, boolean paramBoolean) {
    boolean bool1;
    NetServiceResponseEnum netServiceResponseEnum2 = isThereNETService(NetServiceRequestEnum.CHECKNETSERVICE, paramContext);
    NetServiceResponseEnum netServiceResponseEnum1 = NetServiceResponseEnum.SERVICEMOBILEOK;
    boolean bool3 = false;
    if (netServiceResponseEnum2 == netServiceResponseEnum1 || netServiceResponseEnum2 == NetServiceResponseEnum.SERVICEWIFIOK) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    NetworkInfo networkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    boolean bool2 = bool3;
    if (bool1) {
      bool2 = bool3;
      if (networkInfo.isConnectedOrConnecting())
        bool2 = true; 
    } 
    if (!bool2 && paramBoolean)
      Toast.makeText(paramContext, Utilities.getStringFromConfigList(paramContext, (new StringsResourcesVO()).login_global_popup_lbl_sindatos_2, 2131690012), 1).show(); 
    return bool2;
  }
  
  public static boolean validateNetwork(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) {
    paramBoolean1 = validateNetwork(paramContext, paramBoolean1);
    if (!paramBoolean1 && paramBoolean2) {
      DialogManager.getInstance().setContext(paramContext);
      DialogManager.getInstance().showDialog();
    } 
    if (paramBoolean1 && paramBoolean2) {
      DialogManager.getInstance().setContext(paramContext);
      DialogManager.getInstance().dismissDialog();
    } 
    return paramBoolean1;
  }
  
  public enum NetServiceRequestEnum {
    CHECKNETSERVICE;
    
    private static final NetServiceRequestEnum[] $VALUES = new NetServiceRequestEnum[] { CHECKNETSERVICE };
    
    static {
    
    }
  }
  
  public enum NetServiceResponseEnum {
    COULDNOTPROCESSREQUEST, SERVICEMOBILEOK, SERVICEWIFIOK, THERESNOSERVICE;
    
    private static final NetServiceResponseEnum[] $VALUES;
    
    static {
      $VALUES = new NetServiceResponseEnum[] { COULDNOTPROCESSREQUEST, SERVICEWIFIOK, SERVICEMOBILEOK, THERESNOSERVICE };
    }
  }
  
  public static class SendTermsAndConditionsAsyncTask extends AsyncTask<Void, Void, String> {
    String agreementDate;
    
    Context context;
    
    DBFunctions dbFunctions;
    
    WsAccess wsAccess;
    
    private SendTermsAndConditionsAsyncTask(Context param1Context, String param1String, DBFunctions param1DBFunctions) {
      this.context = param1Context;
      this.dbFunctions = param1DBFunctions;
      this.agreementDate = param1String;
    }
    
    protected String doInBackground(Void... param1VarArgs) {
      return this.wsAccess.sendTermsAndConditionsWs(this.agreementDate);
    }
    
    protected void onPostExecute(String param1String) {
      super.onPostExecute(param1String);
      if (param1String != null && !param1String.isEmpty() && param1String.equals("0|"))
        this.dbFunctions.updateTermsAndConditionsSent("1", GlobalMembers.userLogged); 
    }
    
    protected void onPreExecute() {
      super.onPreExecute();
      this.wsAccess = new WsAccess(this.context);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/NetUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */