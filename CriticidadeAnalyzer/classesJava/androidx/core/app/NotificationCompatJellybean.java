package androidx.core.app;

import android.app.Notification;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.List;

class NotificationCompatJellybean {
  private static Field sExtrasField;
  
  private static boolean sExtrasFieldAccessFailed;
  
  private static final Object sExtrasLock = new Object();
  
  public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> paramList) {
    int i = paramList.size();
    SparseArray<Bundle> sparseArray = null;
    byte b = 0;
    while (b < i) {
      Bundle bundle = paramList.get(b);
      SparseArray<Bundle> sparseArray1 = sparseArray;
      if (bundle != null) {
        sparseArray1 = sparseArray;
        if (sparseArray == null)
          sparseArray1 = new SparseArray(); 
        sparseArray1.put(b, bundle);
      } 
      b++;
      sparseArray = sparseArray1;
    } 
    return sparseArray;
  }
  
  static Bundle getBundleForAction(NotificationCompat.Action paramAction) {
    Bundle bundle1;
    Bundle bundle2 = new Bundle();
    bundle2.putInt("icon", paramAction.getIcon());
    bundle2.putCharSequence("title", paramAction.getTitle());
    bundle2.putParcelable("actionIntent", (Parcelable)paramAction.getActionIntent());
    if (paramAction.getExtras() != null) {
      bundle1 = new Bundle(paramAction.getExtras());
    } else {
      bundle1 = new Bundle();
    } 
    bundle1.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    bundle2.putBundle("extras", bundle1);
    bundle2.putParcelableArray("remoteInputs", (Parcelable[])toBundleArray(paramAction.getRemoteInputs()));
    bundle2.putBoolean("showsUserInterface", paramAction.getShowsUserInterface());
    bundle2.putInt("semanticAction", paramAction.getSemanticAction());
    return bundle2;
  }
  
  public static Bundle getExtras(Notification paramNotification) {
    synchronized (sExtrasLock) {
      if (sExtrasFieldAccessFailed)
        return null; 
      try {
        if (sExtrasField == null) {
          Field field = Notification.class.getDeclaredField("extras");
          if (!Bundle.class.isAssignableFrom(field.getType())) {
            sExtrasFieldAccessFailed = true;
            return null;
          } 
          field.setAccessible(true);
          sExtrasField = field;
        } 
        Bundle bundle2 = (Bundle)sExtrasField.get(paramNotification);
        Bundle bundle1 = bundle2;
        if (bundle2 == null) {
          bundle1 = new Bundle();
          this();
          sExtrasField.set(paramNotification, bundle1);
        } 
        return bundle1;
      } catch (IllegalAccessException|NoSuchFieldException illegalAccessException) {
        sExtrasFieldAccessFailed = true;
        return null;
      } 
    } 
  }
  
  private static Bundle toBundle(RemoteInput paramRemoteInput) {
    new Bundle();
    paramRemoteInput.getResultKey();
    throw null;
  }
  
  private static Bundle[] toBundleArray(RemoteInput[] paramArrayOfRemoteInput) {
    if (paramArrayOfRemoteInput == null)
      return null; 
    Bundle[] arrayOfBundle = new Bundle[paramArrayOfRemoteInput.length];
    if (paramArrayOfRemoteInput.length <= 0)
      return arrayOfBundle; 
    toBundle(paramArrayOfRemoteInput[0]);
    throw null;
  }
  
  public static Bundle writeActionAndGetExtras(Notification.Builder paramBuilder, NotificationCompat.Action paramAction) {
    paramBuilder.addAction(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    Bundle bundle = new Bundle(paramAction.getExtras());
    if (paramAction.getRemoteInputs() != null)
      bundle.putParcelableArray("android.support.remoteInputs", (Parcelable[])toBundleArray(paramAction.getRemoteInputs())); 
    if (paramAction.getDataOnlyRemoteInputs() != null)
      bundle.putParcelableArray("android.support.dataRemoteInputs", (Parcelable[])toBundleArray(paramAction.getDataOnlyRemoteInputs())); 
    bundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
    return bundle;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/app/NotificationCompatJellybean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */