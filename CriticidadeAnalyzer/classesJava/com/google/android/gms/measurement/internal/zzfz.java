package com.google.android.gms.measurement.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

final class zzfz extends SSLSocket {
  private final SSLSocket zzauv;
  
  zzfz(zzfy paramzzfy, SSLSocket paramSSLSocket) {
    this.zzauv = paramSSLSocket;
  }
  
  public final void addHandshakeCompletedListener(HandshakeCompletedListener paramHandshakeCompletedListener) {
    this.zzauv.addHandshakeCompletedListener(paramHandshakeCompletedListener);
  }
  
  public final void bind(SocketAddress paramSocketAddress) throws IOException {
    this.zzauv.bind(paramSocketAddress);
  }
  
  public final void close() throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzauv : Ljavax/net/ssl/SSLSocket;
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
  
  public final void connect(SocketAddress paramSocketAddress) throws IOException {
    this.zzauv.connect(paramSocketAddress);
  }
  
  public final void connect(SocketAddress paramSocketAddress, int paramInt) throws IOException {
    this.zzauv.connect(paramSocketAddress, paramInt);
  }
  
  public final boolean equals(Object paramObject) {
    return this.zzauv.equals(paramObject);
  }
  
  public final SocketChannel getChannel() {
    return this.zzauv.getChannel();
  }
  
  public final boolean getEnableSessionCreation() {
    return this.zzauv.getEnableSessionCreation();
  }
  
  public final String[] getEnabledCipherSuites() {
    return this.zzauv.getEnabledCipherSuites();
  }
  
  public final String[] getEnabledProtocols() {
    return this.zzauv.getEnabledProtocols();
  }
  
  public final InetAddress getInetAddress() {
    return this.zzauv.getInetAddress();
  }
  
  public final InputStream getInputStream() throws IOException {
    return this.zzauv.getInputStream();
  }
  
  public final boolean getKeepAlive() throws SocketException {
    return this.zzauv.getKeepAlive();
  }
  
  public final InetAddress getLocalAddress() {
    return this.zzauv.getLocalAddress();
  }
  
  public final int getLocalPort() {
    return this.zzauv.getLocalPort();
  }
  
  public final SocketAddress getLocalSocketAddress() {
    return this.zzauv.getLocalSocketAddress();
  }
  
  public final boolean getNeedClientAuth() {
    return this.zzauv.getNeedClientAuth();
  }
  
  public final boolean getOOBInline() throws SocketException {
    return this.zzauv.getOOBInline();
  }
  
  public final OutputStream getOutputStream() throws IOException {
    return this.zzauv.getOutputStream();
  }
  
  public final int getPort() {
    return this.zzauv.getPort();
  }
  
  public final int getReceiveBufferSize() throws SocketException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzauv : Ljavax/net/ssl/SSLSocket;
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
  
  public final SocketAddress getRemoteSocketAddress() {
    return this.zzauv.getRemoteSocketAddress();
  }
  
  public final boolean getReuseAddress() throws SocketException {
    return this.zzauv.getReuseAddress();
  }
  
  public final int getSendBufferSize() throws SocketException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzauv : Ljavax/net/ssl/SSLSocket;
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
  
  public final SSLSession getSession() {
    return this.zzauv.getSession();
  }
  
  public final int getSoLinger() throws SocketException {
    return this.zzauv.getSoLinger();
  }
  
  public final int getSoTimeout() throws SocketException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzauv : Ljavax/net/ssl/SSLSocket;
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
  
  public final String[] getSupportedCipherSuites() {
    return this.zzauv.getSupportedCipherSuites();
  }
  
  public final String[] getSupportedProtocols() {
    return this.zzauv.getSupportedProtocols();
  }
  
  public final boolean getTcpNoDelay() throws SocketException {
    return this.zzauv.getTcpNoDelay();
  }
  
  public final int getTrafficClass() throws SocketException {
    return this.zzauv.getTrafficClass();
  }
  
  public final boolean getUseClientMode() {
    return this.zzauv.getUseClientMode();
  }
  
  public final boolean getWantClientAuth() {
    return this.zzauv.getWantClientAuth();
  }
  
  public final boolean isBound() {
    return this.zzauv.isBound();
  }
  
  public final boolean isClosed() {
    return this.zzauv.isClosed();
  }
  
  public final boolean isConnected() {
    return this.zzauv.isConnected();
  }
  
  public final boolean isInputShutdown() {
    return this.zzauv.isInputShutdown();
  }
  
  public final boolean isOutputShutdown() {
    return this.zzauv.isOutputShutdown();
  }
  
  public final void removeHandshakeCompletedListener(HandshakeCompletedListener paramHandshakeCompletedListener) {
    this.zzauv.removeHandshakeCompletedListener(paramHandshakeCompletedListener);
  }
  
  public final void sendUrgentData(int paramInt) throws IOException {
    this.zzauv.sendUrgentData(paramInt);
  }
  
  public final void setEnableSessionCreation(boolean paramBoolean) {
    this.zzauv.setEnableSessionCreation(paramBoolean);
  }
  
  public final void setEnabledCipherSuites(String[] paramArrayOfString) {
    this.zzauv.setEnabledCipherSuites(paramArrayOfString);
  }
  
  public final void setEnabledProtocols(String[] paramArrayOfString) {
    String[] arrayOfString = paramArrayOfString;
    if (paramArrayOfString != null) {
      arrayOfString = paramArrayOfString;
      if (Arrays.<String>asList(paramArrayOfString).contains("SSLv3")) {
        ArrayList arrayList = new ArrayList(Arrays.asList((Object[])this.zzauv.getEnabledProtocols()));
        if (arrayList.size() > 1)
          arrayList.remove("SSLv3"); 
        arrayOfString = (String[])arrayList.toArray((Object[])new String[arrayList.size()]);
      } 
    } 
    this.zzauv.setEnabledProtocols(arrayOfString);
  }
  
  public final void setKeepAlive(boolean paramBoolean) throws SocketException {
    this.zzauv.setKeepAlive(paramBoolean);
  }
  
  public final void setNeedClientAuth(boolean paramBoolean) {
    this.zzauv.setNeedClientAuth(paramBoolean);
  }
  
  public final void setOOBInline(boolean paramBoolean) throws SocketException {
    this.zzauv.setOOBInline(paramBoolean);
  }
  
  public final void setPerformancePreferences(int paramInt1, int paramInt2, int paramInt3) {
    this.zzauv.setPerformancePreferences(paramInt1, paramInt2, paramInt3);
  }
  
  public final void setReceiveBufferSize(int paramInt) throws SocketException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzauv : Ljavax/net/ssl/SSLSocket;
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
  
  public final void setReuseAddress(boolean paramBoolean) throws SocketException {
    this.zzauv.setReuseAddress(paramBoolean);
  }
  
  public final void setSendBufferSize(int paramInt) throws SocketException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzauv : Ljavax/net/ssl/SSLSocket;
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
  
  public final void setSoLinger(boolean paramBoolean, int paramInt) throws SocketException {
    this.zzauv.setSoLinger(paramBoolean, paramInt);
  }
  
  public final void setSoTimeout(int paramInt) throws SocketException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzauv : Ljavax/net/ssl/SSLSocket;
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
  
  public final void setTcpNoDelay(boolean paramBoolean) throws SocketException {
    this.zzauv.setTcpNoDelay(paramBoolean);
  }
  
  public final void setTrafficClass(int paramInt) throws SocketException {
    this.zzauv.setTrafficClass(paramInt);
  }
  
  public final void setUseClientMode(boolean paramBoolean) {
    this.zzauv.setUseClientMode(paramBoolean);
  }
  
  public final void setWantClientAuth(boolean paramBoolean) {
    this.zzauv.setWantClientAuth(paramBoolean);
  }
  
  public final void shutdownInput() throws IOException {
    this.zzauv.shutdownInput();
  }
  
  public final void shutdownOutput() throws IOException {
    this.zzauv.shutdownOutput();
  }
  
  public final void startHandshake() throws IOException {
    this.zzauv.startHandshake();
  }
  
  public final String toString() {
    return this.zzauv.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzfz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */