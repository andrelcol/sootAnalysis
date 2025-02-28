package com.google.firebase.iid;

import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public class zzl implements Parcelable {
  public static final Parcelable.Creator<zzl> CREATOR = new zzm();
  
  private Messenger zzag;
  
  private zzv zzah;
  
  public zzl(IBinder paramIBinder) {
    if (Build.VERSION.SDK_INT >= 21) {
      this.zzag = new Messenger(paramIBinder);
      return;
    } 
    this.zzah = new zzw(paramIBinder);
  }
  
  private final IBinder getBinder() {
    Messenger messenger = this.zzag;
    return (messenger != null) ? messenger.getBinder() : this.zzah.asBinder();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    try {
      return getBinder().equals(((zzl)paramObject).getBinder());
    } catch (ClassCastException classCastException) {
      return false;
    } 
  }
  
  public int hashCode() {
    return getBinder().hashCode();
  }
  
  public final void send(Message paramMessage) throws RemoteException {
    Messenger messenger = this.zzag;
    if (messenger != null) {
      messenger.send(paramMessage);
      return;
    } 
    this.zzah.send(paramMessage);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    Messenger messenger = this.zzag;
    if (messenger != null) {
      paramParcel.writeStrongBinder(messenger.getBinder());
      return;
    } 
    paramParcel.writeStrongBinder(this.zzah.asBinder());
  }
  
  public static final class zza extends ClassLoader {
    protected final Class<?> loadClass(String param1String, boolean param1Boolean) throws ClassNotFoundException {
      if ("com.google.android.gms.iid.MessengerCompat".equals(param1String)) {
        FirebaseInstanceId.zzl();
        return zzl.class;
      } 
      return super.loadClass(param1String, param1Boolean);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */