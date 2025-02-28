package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Looper;
import android.os.Message;
import android.util.TypedValue;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.base.R;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.zabq;
import com.google.android.gms.common.api.internal.zabr;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zap;

public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
  private static final Object mLock = new Object();
  
  private static final GoogleApiAvailability zaao = new GoogleApiAvailability();
  
  private String zaap;
  
  public static GoogleApiAvailability getInstance() {
    return zaao;
  }
  
  static Dialog zaa(Context paramContext, int paramInt, DialogRedirect paramDialogRedirect, DialogInterface.OnCancelListener paramOnCancelListener) {
    AlertDialog.Builder builder1 = null;
    if (paramInt == 0)
      return null; 
    TypedValue typedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(16843529, typedValue, true);
    if ("Theme.Dialog.Alert".equals(paramContext.getResources().getResourceEntryName(typedValue.resourceId)))
      builder1 = new AlertDialog.Builder(paramContext, 5); 
    AlertDialog.Builder builder2 = builder1;
    if (builder1 == null)
      builder2 = new AlertDialog.Builder(paramContext); 
    builder2.setMessage(ConnectionErrorMessages.getErrorMessage(paramContext, paramInt));
    if (paramOnCancelListener != null)
      builder2.setOnCancelListener(paramOnCancelListener); 
    String str2 = ConnectionErrorMessages.getErrorDialogButtonMessage(paramContext, paramInt);
    if (str2 != null)
      builder2.setPositiveButton(str2, (DialogInterface.OnClickListener)paramDialogRedirect); 
    String str1 = ConnectionErrorMessages.getErrorTitle(paramContext, paramInt);
    if (str1 != null)
      builder2.setTitle(str1); 
    return (Dialog)builder2.create();
  }
  
  static void zaa(Activity paramActivity, Dialog paramDialog, String paramString, DialogInterface.OnCancelListener paramOnCancelListener) {
    FragmentManager fragmentManager1;
    if (paramActivity instanceof FragmentActivity) {
      fragmentManager1 = ((FragmentActivity)paramActivity).getSupportFragmentManager();
      SupportErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(fragmentManager1, paramString);
      return;
    } 
    FragmentManager fragmentManager = fragmentManager1.getFragmentManager();
    ErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(fragmentManager, paramString);
  }
  
  @TargetApi(20)
  private final void zaa(Context paramContext, int paramInt, String paramString, PendingIntent paramPendingIntent) {
    if (paramInt == 18) {
      zaa(paramContext);
      return;
    } 
    if (paramPendingIntent == null)
      return; 
    String str2 = ConnectionErrorMessages.getErrorNotificationTitle(paramContext, paramInt);
    String str1 = ConnectionErrorMessages.getErrorNotificationMessage(paramContext, paramInt);
    Resources resources = paramContext.getResources();
    NotificationManager notificationManager = (NotificationManager)paramContext.getSystemService("notification");
    NotificationCompat.Builder builder = new NotificationCompat.Builder(paramContext);
    builder.setLocalOnly(true);
    builder.setAutoCancel(true);
    builder.setContentTitle(str2);
    NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
    bigTextStyle.bigText(str1);
    builder.setStyle((NotificationCompat.Style)bigTextStyle);
    if (DeviceProperties.isWearable(paramContext)) {
      Preconditions.checkState(PlatformVersion.isAtLeastKitKatWatch());
      builder.setSmallIcon((paramContext.getApplicationInfo()).icon);
      builder.setPriority(2);
      if (DeviceProperties.isWearableWithoutPlayStore(paramContext)) {
        builder.addAction(R.drawable.common_full_open_on_phone, resources.getString(R.string.common_open_on_phone), paramPendingIntent);
      } else {
        builder.setContentIntent(paramPendingIntent);
      } 
    } else {
      builder.setSmallIcon(17301642);
      builder.setTicker(resources.getString(R.string.common_google_play_services_notification_ticker));
      builder.setWhen(System.currentTimeMillis());
      builder.setContentIntent(paramPendingIntent);
      builder.setContentText(str1);
    } 
    if (PlatformVersion.isAtLeastO()) {
      Preconditions.checkState(PlatformVersion.isAtLeastO());
      String str4 = zag();
      String str3 = str4;
      if (str4 == null) {
        str4 = "com.google.android.gms.availability";
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
        String str = ConnectionErrorMessages.getDefaultNotificationChannelName(paramContext);
        if (notificationChannel == null) {
          notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", str, 4));
          str3 = str4;
        } else {
          str3 = str4;
          if (!str.contentEquals(notificationChannel.getName())) {
            notificationChannel.setName(str);
            notificationManager.createNotificationChannel(notificationChannel);
            str3 = str4;
          } 
        } 
      } 
      builder.setChannelId(str3);
    } 
    Notification notification = builder.build();
    if (paramInt != 1 && paramInt != 2 && paramInt != 3) {
      paramInt = 39789;
    } else {
      paramInt = 10436;
      GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
    } 
    notificationManager.notify(paramInt, notification);
  }
  
  private final String zag() {
    synchronized (mLock) {
      return this.zaap;
    } 
  }
  
  public Dialog getErrorDialog(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    return zaa((Context)paramActivity, paramInt1, DialogRedirect.getInstance(paramActivity, getErrorResolutionIntent((Context)paramActivity, paramInt1, "d"), paramInt2), paramOnCancelListener);
  }
  
  public Intent getErrorResolutionIntent(Context paramContext, int paramInt, String paramString) {
    return super.getErrorResolutionIntent(paramContext, paramInt, paramString);
  }
  
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, int paramInt1, int paramInt2) {
    return super.getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  public PendingIntent getErrorResolutionPendingIntent(Context paramContext, ConnectionResult paramConnectionResult) {
    return paramConnectionResult.hasResolution() ? paramConnectionResult.getResolution() : getErrorResolutionPendingIntent(paramContext, paramConnectionResult.getErrorCode(), 0);
  }
  
  public final String getErrorString(int paramInt) {
    return super.getErrorString(paramInt);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext) {
    return super.isGooglePlayServicesAvailable(paramContext);
  }
  
  public int isGooglePlayServicesAvailable(Context paramContext, int paramInt) {
    return super.isGooglePlayServicesAvailable(paramContext, paramInt);
  }
  
  public final boolean isUserResolvableError(int paramInt) {
    return super.isUserResolvableError(paramInt);
  }
  
  public boolean showErrorDialogFragment(Activity paramActivity, int paramInt1, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener) {
    Dialog dialog = getErrorDialog(paramActivity, paramInt1, paramInt2, paramOnCancelListener);
    if (dialog == null)
      return false; 
    zaa(paramActivity, dialog, "GooglePlayServicesErrorDialog", paramOnCancelListener);
    return true;
  }
  
  public void showErrorNotification(Context paramContext, int paramInt) {
    zaa(paramContext, paramInt, (String)null, getErrorResolutionPendingIntent(paramContext, paramInt, 0, "n"));
  }
  
  public final zabq zaa(Context paramContext, zabr paramzabr) {
    IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    intentFilter.addDataScheme("package");
    zabq zabq = new zabq(paramzabr);
    paramContext.registerReceiver((BroadcastReceiver)zabq, intentFilter);
    zabq.zac(paramContext);
    if (!isUninstalledAppPossiblyUpdating(paramContext, "com.google.android.gms")) {
      paramzabr.zas();
      zabq.unregister();
      return null;
    } 
    return zabq;
  }
  
  final void zaa(Context paramContext) {
    (new zaa(this, paramContext)).sendEmptyMessageDelayed(1, 120000L);
  }
  
  public final boolean zaa(Context paramContext, ConnectionResult paramConnectionResult, int paramInt) {
    PendingIntent pendingIntent = getErrorResolutionPendingIntent(paramContext, paramConnectionResult);
    if (pendingIntent != null) {
      zaa(paramContext, paramConnectionResult.getErrorCode(), (String)null, GoogleApiActivity.zaa(paramContext, pendingIntent, paramInt));
      return true;
    } 
    return false;
  }
  
  @SuppressLint({"HandlerLeak"})
  private final class zaa extends zap {
    private final Context zaaq;
    
    private final GoogleApiAvailability zaar;
    
    public zaa(GoogleApiAvailability this$0, Context param1Context) {
      super(looper);
      Looper looper;
      this.zaaq = param1Context.getApplicationContext();
    }
    
    public final void handleMessage(Message param1Message) {
      int i = param1Message.what;
      if (i != 1) {
        StringBuilder stringBuilder = new StringBuilder(50);
        stringBuilder.append("Don't know how to handle this message: ");
        stringBuilder.append(i);
        stringBuilder.toString();
      } else {
        i = this.zaar.isGooglePlayServicesAvailable(this.zaaq);
        if (this.zaar.isUserResolvableError(i))
          this.zaar.showErrorNotification(this.zaaq, i); 
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/GoogleApiAvailability.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */