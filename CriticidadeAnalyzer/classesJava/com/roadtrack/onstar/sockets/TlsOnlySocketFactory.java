package com.roadtrack.onstar.sockets;

import com.roadtrack.onstar.utils.Utilities;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TlsOnlySocketFactory extends SSLSocketFactory {
  private final boolean compatible;
  
  private final SSLSocketFactory delegate;
  
  public TlsOnlySocketFactory(SSLSocketFactory paramSSLSocketFactory) {
    this.delegate = paramSSLSocketFactory;
    this.compatible = false;
  }
  
  private Socket makeSocketSafe(Socket paramSocket) {
    Socket socket = paramSocket;
    if (paramSocket instanceof SSLSocket)
      socket = new TlsOnlySSLSocket((SSLSocket)paramSocket, this.compatible); 
    return socket;
  }
  
  public Socket createSocket(String paramString, int paramInt) throws IOException {
    SSLSocket sSLSocket = (SSLSocket)this.delegate.createSocket(paramString, paramInt);
    sSLSocket.setEnabledProtocols(new String[] { "TLSv1.2" });
    return HttpsURLConnection.getDefaultHostnameVerifier().verify(paramString, sSLSocket.getSession()) ? makeSocketSafe(sSLSocket) : makeSocketSafe(this.delegate.createSocket(paramString, paramInt));
  }
  
  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2) throws IOException {
    Utilities.escribeArchivo("TlsOnlySocketFactory", "TLSOnlySocketFactory", "warninig Método comentado debido a que veracode no valida host cwe 297");
    return null;
  }
  
  public Socket createSocket(InetAddress paramInetAddress, int paramInt) throws IOException {
    Utilities.escribeArchivo("TlsOnlySocketFactory", "TLSOnlySocketFactory", "warninig Método comentado debido a que veracode no valida host cwe 297");
    return null;
  }
  
  public Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2) throws IOException {
    Utilities.escribeArchivo("TlsOnlySocketFactory", "TLSOnlySocketFactory", "warninig Método comentado debido a que veracode no valida host cwe 297");
    return null;
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean) throws IOException {
    SSLSocket sSLSocket = (SSLSocket)this.delegate.createSocket(paramString, paramInt);
    sSLSocket.setEnabledProtocols(new String[] { "TLSv1.2" });
    return HttpsURLConnection.getDefaultHostnameVerifier().verify(paramString, sSLSocket.getSession()) ? makeSocketSafe(sSLSocket) : makeSocketSafe(this.delegate.createSocket(paramSocket, paramString, paramInt, paramBoolean));
  }
  
  public String[] getDefaultCipherSuites() {
    return this.delegate.getDefaultCipherSuites();
  }
  
  public String[] getSupportedCipherSuites() {
    return this.delegate.getSupportedCipherSuites();
  }
  
  public class DelegateSSLSocket extends SSLSocket {
    protected final SSLSocket delegate;
    
    DelegateSSLSocket(TlsOnlySocketFactory this$0, SSLSocket param1SSLSocket) {
      this.delegate = param1SSLSocket;
    }
    
    public void addHandshakeCompletedListener(HandshakeCompletedListener param1HandshakeCompletedListener) {
      this.delegate.addHandshakeCompletedListener(param1HandshakeCompletedListener);
    }
    
    public void bind(SocketAddress param1SocketAddress) throws IOException {
      this.delegate.bind(param1SocketAddress);
    }
    
    public void close() throws IOException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield delegate : Ljavax/net/ssl/SSLSocket;
      //   6: invokevirtual close : ()V
      //   9: aload_0
      //   10: monitorexit
      //   11: return
      //   12: astore_1
      //   13: aload_0
      //   14: monitorexit
      //   15: aload_1
      //   16: athrow
      // Exception table:
      //   from	to	target	type
      //   2	9	12	finally
    }
    
    public void connect(SocketAddress param1SocketAddress) throws IOException {
      this.delegate.connect(param1SocketAddress);
    }
    
    public void connect(SocketAddress param1SocketAddress, int param1Int) throws IOException {
      this.delegate.connect(param1SocketAddress, param1Int);
    }
    
    public boolean equals(Object param1Object) {
      return this.delegate.equals(param1Object);
    }
    
    public SocketChannel getChannel() {
      return this.delegate.getChannel();
    }
    
    public boolean getEnableSessionCreation() {
      return this.delegate.getEnableSessionCreation();
    }
    
    public String[] getEnabledCipherSuites() {
      return this.delegate.getEnabledCipherSuites();
    }
    
    public String[] getEnabledProtocols() {
      return this.delegate.getEnabledProtocols();
    }
    
    public InetAddress getInetAddress() {
      return this.delegate.getInetAddress();
    }
    
    public InputStream getInputStream() throws IOException {
      return this.delegate.getInputStream();
    }
    
    public boolean getKeepAlive() throws SocketException {
      return this.delegate.getKeepAlive();
    }
    
    public InetAddress getLocalAddress() {
      return this.delegate.getLocalAddress();
    }
    
    public int getLocalPort() {
      return this.delegate.getLocalPort();
    }
    
    public SocketAddress getLocalSocketAddress() {
      return this.delegate.getLocalSocketAddress();
    }
    
    public boolean getNeedClientAuth() {
      return this.delegate.getNeedClientAuth();
    }
    
    public boolean getOOBInline() throws SocketException {
      return this.delegate.getOOBInline();
    }
    
    public OutputStream getOutputStream() throws IOException {
      return this.delegate.getOutputStream();
    }
    
    public int getPort() {
      return this.delegate.getPort();
    }
    
    public int getReceiveBufferSize() throws SocketException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield delegate : Ljavax/net/ssl/SSLSocket;
      //   6: invokevirtual getReceiveBufferSize : ()I
      //   9: istore_1
      //   10: aload_0
      //   11: monitorexit
      //   12: iload_1
      //   13: ireturn
      //   14: astore_2
      //   15: aload_0
      //   16: monitorexit
      //   17: aload_2
      //   18: athrow
      // Exception table:
      //   from	to	target	type
      //   2	10	14	finally
    }
    
    public SocketAddress getRemoteSocketAddress() {
      return this.delegate.getRemoteSocketAddress();
    }
    
    public boolean getReuseAddress() throws SocketException {
      return this.delegate.getReuseAddress();
    }
    
    public int getSendBufferSize() throws SocketException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield delegate : Ljavax/net/ssl/SSLSocket;
      //   6: invokevirtual getSendBufferSize : ()I
      //   9: istore_1
      //   10: aload_0
      //   11: monitorexit
      //   12: iload_1
      //   13: ireturn
      //   14: astore_2
      //   15: aload_0
      //   16: monitorexit
      //   17: aload_2
      //   18: athrow
      // Exception table:
      //   from	to	target	type
      //   2	10	14	finally
    }
    
    public SSLSession getSession() {
      return this.delegate.getSession();
    }
    
    public int getSoLinger() throws SocketException {
      return this.delegate.getSoLinger();
    }
    
    public int getSoTimeout() throws SocketException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield delegate : Ljavax/net/ssl/SSLSocket;
      //   6: invokevirtual getSoTimeout : ()I
      //   9: istore_1
      //   10: aload_0
      //   11: monitorexit
      //   12: iload_1
      //   13: ireturn
      //   14: astore_2
      //   15: aload_0
      //   16: monitorexit
      //   17: aload_2
      //   18: athrow
      // Exception table:
      //   from	to	target	type
      //   2	10	14	finally
    }
    
    public String[] getSupportedCipherSuites() {
      return this.delegate.getSupportedCipherSuites();
    }
    
    public String[] getSupportedProtocols() {
      return this.delegate.getSupportedProtocols();
    }
    
    public boolean getTcpNoDelay() throws SocketException {
      return this.delegate.getTcpNoDelay();
    }
    
    public int getTrafficClass() throws SocketException {
      return this.delegate.getTrafficClass();
    }
    
    public boolean getUseClientMode() {
      return this.delegate.getUseClientMode();
    }
    
    public boolean getWantClientAuth() {
      return this.delegate.getWantClientAuth();
    }
    
    public boolean isBound() {
      return this.delegate.isBound();
    }
    
    public boolean isClosed() {
      return this.delegate.isClosed();
    }
    
    public boolean isConnected() {
      return this.delegate.isConnected();
    }
    
    public boolean isInputShutdown() {
      return this.delegate.isInputShutdown();
    }
    
    public boolean isOutputShutdown() {
      return this.delegate.isOutputShutdown();
    }
    
    public void removeHandshakeCompletedListener(HandshakeCompletedListener param1HandshakeCompletedListener) {
      this.delegate.removeHandshakeCompletedListener(param1HandshakeCompletedListener);
    }
    
    public void sendUrgentData(int param1Int) throws IOException {
      this.delegate.sendUrgentData(param1Int);
    }
    
    public void setEnableSessionCreation(boolean param1Boolean) {
      this.delegate.setEnableSessionCreation(param1Boolean);
    }
    
    public void setEnabledCipherSuites(String[] param1ArrayOfString) {
      this.delegate.setEnabledCipherSuites(param1ArrayOfString);
    }
    
    public void setEnabledProtocols(String[] param1ArrayOfString) {
      this.delegate.setEnabledProtocols(param1ArrayOfString);
    }
    
    public void setKeepAlive(boolean param1Boolean) throws SocketException {
      this.delegate.setKeepAlive(param1Boolean);
    }
    
    public void setNeedClientAuth(boolean param1Boolean) {
      this.delegate.setNeedClientAuth(param1Boolean);
    }
    
    public void setOOBInline(boolean param1Boolean) throws SocketException {
      this.delegate.setOOBInline(param1Boolean);
    }
    
    public void setPerformancePreferences(int param1Int1, int param1Int2, int param1Int3) {
      this.delegate.setPerformancePreferences(param1Int1, param1Int2, param1Int3);
    }
    
    public void setReceiveBufferSize(int param1Int) throws SocketException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield delegate : Ljavax/net/ssl/SSLSocket;
      //   6: iload_1
      //   7: invokevirtual setReceiveBufferSize : (I)V
      //   10: aload_0
      //   11: monitorexit
      //   12: return
      //   13: astore_2
      //   14: aload_0
      //   15: monitorexit
      //   16: aload_2
      //   17: athrow
      // Exception table:
      //   from	to	target	type
      //   2	10	13	finally
    }
    
    public void setReuseAddress(boolean param1Boolean) throws SocketException {
      this.delegate.setReuseAddress(param1Boolean);
    }
    
    public void setSendBufferSize(int param1Int) throws SocketException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield delegate : Ljavax/net/ssl/SSLSocket;
      //   6: iload_1
      //   7: invokevirtual setSendBufferSize : (I)V
      //   10: aload_0
      //   11: monitorexit
      //   12: return
      //   13: astore_2
      //   14: aload_0
      //   15: monitorexit
      //   16: aload_2
      //   17: athrow
      // Exception table:
      //   from	to	target	type
      //   2	10	13	finally
    }
    
    public void setSoLinger(boolean param1Boolean, int param1Int) throws SocketException {
      this.delegate.setSoLinger(param1Boolean, param1Int);
    }
    
    public void setSoTimeout(int param1Int) throws SocketException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield delegate : Ljavax/net/ssl/SSLSocket;
      //   6: iload_1
      //   7: invokevirtual setSoTimeout : (I)V
      //   10: aload_0
      //   11: monitorexit
      //   12: return
      //   13: astore_2
      //   14: aload_0
      //   15: monitorexit
      //   16: aload_2
      //   17: athrow
      // Exception table:
      //   from	to	target	type
      //   2	10	13	finally
    }
    
    public void setTcpNoDelay(boolean param1Boolean) throws SocketException {
      this.delegate.setTcpNoDelay(param1Boolean);
    }
    
    public void setTrafficClass(int param1Int) throws SocketException {
      this.delegate.setTrafficClass(param1Int);
    }
    
    public void setUseClientMode(boolean param1Boolean) {
      this.delegate.setUseClientMode(param1Boolean);
    }
    
    public void setWantClientAuth(boolean param1Boolean) {
      this.delegate.setWantClientAuth(param1Boolean);
    }
    
    public void shutdownInput() throws IOException {
      this.delegate.shutdownInput();
    }
    
    public void shutdownOutput() throws IOException {
      this.delegate.shutdownOutput();
    }
    
    public void startHandshake() throws IOException {
      this.delegate.startHandshake();
    }
    
    public String toString() {
      return this.delegate.toString();
    }
  }
  
  private class TlsOnlySSLSocket extends DelegateSSLSocket {
    final boolean compatible;
    
    private TlsOnlySSLSocket(TlsOnlySocketFactory this$0, SSLSocket param1SSLSocket, boolean param1Boolean) {
      super(param1SSLSocket);
      String str;
      this.compatible = param1Boolean;
      int j = 0;
      int i = 0;
      if (param1Boolean) {
        ArrayList<String> arrayList1 = new ArrayList(Arrays.asList((Object[])param1SSLSocket.getEnabledProtocols()));
        arrayList1.remove("SSLv2");
        arrayList1.remove("SSLv3");
        arrayList1.remove("TLSv1");
        arrayList1.remove("TLSv1.1");
        super.setEnabledProtocols((String[])arrayList1.toArray((Object[])new String[arrayList1.size()]));
        arrayList1 = new ArrayList(10);
        Pattern pattern1 = Pattern.compile(".*(EXPORT|NULL).*");
        String[] arrayOfString1 = param1SSLSocket.getEnabledCipherSuites();
        j = arrayOfString1.length;
        while (i < j) {
          str = arrayOfString1[i];
          if (!pattern1.matcher(str).matches())
            arrayList1.add(str); 
          i++;
        } 
        setEnabledCipherSuites(arrayList1.<String>toArray(new String[arrayList1.size()]));
        return;
      } 
      ArrayList<String> arrayList = new ArrayList(Arrays.asList((Object[])str.getSupportedProtocols()));
      arrayList.remove("SSLv2");
      arrayList.remove("SSLv3");
      arrayList.remove("TLSv1");
      arrayList.remove("TLSv1.1");
      super.setEnabledProtocols((String[])arrayList.toArray((Object[])new String[arrayList.size()]));
      arrayList = new ArrayList(10);
      Pattern pattern = Pattern.compile(".*(_DES|DH_|DSS|EXPORT|MD5|NULL|RC4).*");
      String[] arrayOfString = str.getSupportedCipherSuites();
      int k = arrayOfString.length;
      for (i = j; i < k; i++) {
        str = arrayOfString[i];
        if (!pattern.matcher(str).matches())
          arrayList.add(str); 
      } 
      setEnabledCipherSuites(arrayList.<String>toArray(new String[arrayList.size()]));
    }
    
    public void setEnabledProtocols(String[] param1ArrayOfString) {
      String[] arrayOfString = param1ArrayOfString;
      if (param1ArrayOfString != null) {
        arrayOfString = param1ArrayOfString;
        if (param1ArrayOfString.length == 1) {
          arrayOfString = param1ArrayOfString;
          if ("SSLv3".equals(param1ArrayOfString[0])) {
            if (this.compatible) {
              list = Arrays.asList(this.delegate.getEnabledProtocols());
            } else {
              list = Arrays.asList(this.delegate.getSupportedProtocols());
            } 
            List<String> list = new ArrayList<String>(list);
            if (list.size() > 1) {
              list.remove("SSLv2");
              list.remove("SSLv3");
              list.remove("TLSv1");
              list.remove("TLSv1.1");
              Utilities.escribeArchivo("TlsOnlySocketFactory", "socket", "remove protocol");
            } 
            arrayOfString = list.<String>toArray(new String[list.size()]);
          } 
        } 
      } 
      super.setEnabledProtocols(arrayOfString);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/sockets/TlsOnlySocketFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */