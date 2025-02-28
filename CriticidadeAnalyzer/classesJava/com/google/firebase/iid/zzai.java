package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

final class zzai {
  private final Messenger zzag;
  
  private final zzl zzce;
  
  zzai(IBinder paramIBinder) throws RemoteException {
    String str2 = paramIBinder.getInterfaceDescriptor();
    if ("android.os.IMessenger".equals(str2)) {
      this.zzag = new Messenger(paramIBinder);
      this.zzce = null;
      return;
    } 
    if ("com.google.android.gms.iid.IMessengerCompat".equals(str2)) {
      this.zzce = new zzl(paramIBinder);
      this.zzag = null;
      return;
    } 
    String str1 = String.valueOf(str2);
    if (str1.length() != 0) {
      "Invalid interface descriptor: ".concat(str1);
    } else {
      new String("Invalid interface descriptor: ");
    } 
    throw new RemoteException();
  }
  
  final void send(Message paramMessage) throws RemoteException {
    Messenger messenger = this.zzag;
    if (messenger != null) {
      messenger.send(paramMessage);
      return;
    } 
    zzl zzl1 = this.zzce;
    if (zzl1 != null) {
      zzl1.send(paramMessage);
      return;
    } 
    throw new IllegalStateException("Both messengers are null");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */