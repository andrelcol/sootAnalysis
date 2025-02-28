package com.roadtrack.onstar.sockets;

import android.content.Context;
import com.roadtrack.onstar.utils.DeviceUtils;
import com.roadtrack.onstar.utils.FindObject;
import com.roadtrack.onstar.utils.Utilities;
import com.roadtrack.onstar.utils.UtilitiesFile;
import java.io.InputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSLSocketFactoryInstance {
  private static String dummy;
  
  Context ctx;
  
  private int keyStoreId = 2131623963;
  
  private String nameKeyStore = null;
  
  static {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(FindObject.obj1);
    stringBuilder.append(UtilitiesFile.segundo);
    stringBuilder.append(DeviceUtils.ultimo);
    dummy = stringBuilder.toString();
  }
  
  public SSLSocketFactoryInstance(Context paramContext) {
    this.ctx = paramContext;
  }
  
  public SSLSocketFactoryInstance(Context paramContext, int paramInt, String paramString) {
    this.ctx = paramContext;
    this.keyStoreId = paramInt;
    this.nameKeyStore = paramString;
  }
  
  public SSLSocketFactory getSSLFactory() {
    try {
      TrustManagerFactory trustManagerFactory;
      null = KeyStore.getInstance("bks");
      InputStream inputStream = Utilities.getKeyStoreFromConfigList(this.ctx, this.nameKeyStore, this.keyStoreId);
      try {
        null.load(inputStream, dummy.toCharArray());
        inputStream.close();
        trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(null);
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        return new TlsOnlySocketFactory(sSLContext.getSocketFactory());
      } finally {
        trustManagerFactory.close();
      } 
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("error ");
      stringBuilder.append(exception.getMessage());
      Utilities.escribeArchivo("SSLSocketFactoryInstance", "SSLSocketFactory", stringBuilder.toString());
      return null;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/sockets/SSLSocketFactoryInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */