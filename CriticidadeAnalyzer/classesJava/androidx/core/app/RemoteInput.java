package androidx.core.app;

public final class RemoteInput {
  static android.app.RemoteInput fromCompat(RemoteInput paramRemoteInput) {
    android.app.RemoteInput.Builder builder = new android.app.RemoteInput.Builder();
    paramRemoteInput.getResultKey();
    throw null;
  }
  
  static android.app.RemoteInput[] fromCompat(RemoteInput[] paramArrayOfRemoteInput) {
    if (paramArrayOfRemoteInput == null)
      return null; 
    android.app.RemoteInput[] arrayOfRemoteInput = new android.app.RemoteInput[paramArrayOfRemoteInput.length];
    if (paramArrayOfRemoteInput.length <= 0)
      return arrayOfRemoteInput; 
    fromCompat(paramArrayOfRemoteInput[0]);
    throw null;
  }
  
  public String getResultKey() {
    throw null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/app/RemoteInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */