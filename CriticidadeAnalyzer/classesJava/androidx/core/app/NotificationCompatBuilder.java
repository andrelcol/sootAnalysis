package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
  private final List<Bundle> mActionExtrasList;
  
  private RemoteViews mBigContentView;
  
  private final Notification.Builder mBuilder;
  
  private final NotificationCompat.Builder mBuilderCompat;
  
  private RemoteViews mContentView;
  
  private final Bundle mExtras;
  
  private int mGroupAlertBehavior;
  
  private RemoteViews mHeadsUpContentView;
  
  NotificationCompatBuilder(NotificationCompat.Builder paramBuilder) {
    boolean bool;
    this.mActionExtrasList = new ArrayList<Bundle>();
    this.mExtras = new Bundle();
    this.mBuilderCompat = paramBuilder;
    if (Build.VERSION.SDK_INT >= 26) {
      this.mBuilder = new Notification.Builder(paramBuilder.mContext, paramBuilder.mChannelId);
    } else {
      this.mBuilder = new Notification.Builder(paramBuilder.mContext);
    } 
    Notification notification = paramBuilder.mNotification;
    Notification.Builder builder = this.mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, paramBuilder.mTickerView).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
    if ((notification.flags & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    builder = builder.setOngoing(bool);
    if ((notification.flags & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    builder = builder.setOnlyAlertOnce(bool);
    if ((notification.flags & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    builder = builder.setAutoCancel(bool).setDefaults(notification.defaults).setContentTitle(paramBuilder.mContentTitle).setContentText(paramBuilder.mContentText).setContentInfo(paramBuilder.mContentInfo).setContentIntent(paramBuilder.mContentIntent).setDeleteIntent(notification.deleteIntent);
    PendingIntent pendingIntent = paramBuilder.mFullScreenIntent;
    if ((notification.flags & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    builder.setFullScreenIntent(pendingIntent, bool).setLargeIcon(paramBuilder.mLargeIcon).setNumber(paramBuilder.mNumber).setProgress(paramBuilder.mProgressMax, paramBuilder.mProgress, paramBuilder.mProgressIndeterminate);
    if (Build.VERSION.SDK_INT < 21)
      this.mBuilder.setSound(notification.sound, notification.audioStreamType); 
    if (Build.VERSION.SDK_INT >= 16) {
      this.mBuilder.setSubText(paramBuilder.mSubText).setUsesChronometer(paramBuilder.mUseChronometer).setPriority(paramBuilder.mPriority);
      Iterator<NotificationCompat.Action> iterator = paramBuilder.mActions.iterator();
      while (iterator.hasNext())
        addAction(iterator.next()); 
      Bundle bundle = paramBuilder.mExtras;
      if (bundle != null)
        this.mExtras.putAll(bundle); 
      if (Build.VERSION.SDK_INT < 20) {
        if (paramBuilder.mLocalOnly)
          this.mExtras.putBoolean("android.support.localOnly", true); 
        String str = paramBuilder.mGroupKey;
        if (str != null) {
          this.mExtras.putString("android.support.groupKey", str);
          if (paramBuilder.mGroupSummary) {
            this.mExtras.putBoolean("android.support.isGroupSummary", true);
          } else {
            this.mExtras.putBoolean("android.support.useSideChannel", true);
          } 
        } 
        str = paramBuilder.mSortKey;
        if (str != null)
          this.mExtras.putString("android.support.sortKey", str); 
      } 
      this.mContentView = paramBuilder.mContentView;
      this.mBigContentView = paramBuilder.mBigContentView;
    } 
    if (Build.VERSION.SDK_INT >= 19) {
      this.mBuilder.setShowWhen(paramBuilder.mShowWhen);
      if (Build.VERSION.SDK_INT < 21) {
        ArrayList<String> arrayList = paramBuilder.mPeople;
        if (arrayList != null && !arrayList.isEmpty()) {
          Bundle bundle = this.mExtras;
          arrayList = paramBuilder.mPeople;
          bundle.putStringArray("android.people", arrayList.<String>toArray(new String[arrayList.size()]));
        } 
      } 
    } 
    if (Build.VERSION.SDK_INT >= 20) {
      this.mBuilder.setLocalOnly(paramBuilder.mLocalOnly).setGroup(paramBuilder.mGroupKey).setGroupSummary(paramBuilder.mGroupSummary).setSortKey(paramBuilder.mSortKey);
      this.mGroupAlertBehavior = paramBuilder.mGroupAlertBehavior;
    } 
    if (Build.VERSION.SDK_INT >= 21) {
      this.mBuilder.setCategory(paramBuilder.mCategory).setColor(paramBuilder.mColor).setVisibility(paramBuilder.mVisibility).setPublicVersion(paramBuilder.mPublicVersion).setSound(notification.sound, notification.audioAttributes);
      for (String str : paramBuilder.mPeople)
        this.mBuilder.addPerson(str); 
      this.mHeadsUpContentView = paramBuilder.mHeadsUpContentView;
      if (paramBuilder.mInvisibleActions.size() > 0) {
        Bundle bundle2 = paramBuilder.getExtras().getBundle("android.car.EXTENSIONS");
        Bundle bundle1 = bundle2;
        if (bundle2 == null)
          bundle1 = new Bundle(); 
        bundle2 = new Bundle();
        for (byte b = 0; b < paramBuilder.mInvisibleActions.size(); b++)
          bundle2.putBundle(Integer.toString(b), NotificationCompatJellybean.getBundleForAction(paramBuilder.mInvisibleActions.get(b))); 
        bundle1.putBundle("invisible_actions", bundle2);
        paramBuilder.getExtras().putBundle("android.car.EXTENSIONS", bundle1);
        this.mExtras.putBundle("android.car.EXTENSIONS", bundle1);
      } 
    } 
    if (Build.VERSION.SDK_INT >= 24) {
      this.mBuilder.setExtras(paramBuilder.mExtras).setRemoteInputHistory(paramBuilder.mRemoteInputHistory);
      RemoteViews remoteViews = paramBuilder.mContentView;
      if (remoteViews != null)
        this.mBuilder.setCustomContentView(remoteViews); 
      remoteViews = paramBuilder.mBigContentView;
      if (remoteViews != null)
        this.mBuilder.setCustomBigContentView(remoteViews); 
      remoteViews = paramBuilder.mHeadsUpContentView;
      if (remoteViews != null)
        this.mBuilder.setCustomHeadsUpContentView(remoteViews); 
    } 
    if (Build.VERSION.SDK_INT >= 26) {
      this.mBuilder.setBadgeIconType(paramBuilder.mBadgeIcon).setShortcutId(paramBuilder.mShortcutId).setTimeoutAfter(paramBuilder.mTimeout).setGroupAlertBehavior(paramBuilder.mGroupAlertBehavior);
      if (paramBuilder.mColorizedSet)
        this.mBuilder.setColorized(paramBuilder.mColorized); 
      if (!TextUtils.isEmpty(paramBuilder.mChannelId))
        this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null); 
    } 
  }
  
  private void addAction(NotificationCompat.Action paramAction) {
    int i = Build.VERSION.SDK_INT;
    if (i >= 20) {
      Bundle bundle;
      Notification.Action.Builder builder = new Notification.Action.Builder(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
      if (paramAction.getRemoteInputs() != null) {
        RemoteInput[] arrayOfRemoteInput = RemoteInput.fromCompat(paramAction.getRemoteInputs());
        int j = arrayOfRemoteInput.length;
        for (i = 0; i < j; i++)
          builder.addRemoteInput(arrayOfRemoteInput[i]); 
      } 
      if (paramAction.getExtras() != null) {
        bundle = new Bundle(paramAction.getExtras());
      } else {
        bundle = new Bundle();
      } 
      bundle.putBoolean("android.support.allowGeneratedReplies", paramAction.getAllowGeneratedReplies());
      if (Build.VERSION.SDK_INT >= 24)
        builder.setAllowGeneratedReplies(paramAction.getAllowGeneratedReplies()); 
      bundle.putInt("android.support.action.semanticAction", paramAction.getSemanticAction());
      if (Build.VERSION.SDK_INT >= 28)
        builder.setSemanticAction(paramAction.getSemanticAction()); 
      bundle.putBoolean("android.support.action.showsUserInterface", paramAction.getShowsUserInterface());
      builder.addExtras(bundle);
      this.mBuilder.addAction(builder.build());
    } else if (i >= 16) {
      this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.mBuilder, paramAction));
    } 
  }
  
  private void removeSoundAndVibration(Notification paramNotification) {
    paramNotification.sound = null;
    paramNotification.vibrate = null;
    paramNotification.defaults &= 0xFFFFFFFE;
    paramNotification.defaults &= 0xFFFFFFFD;
  }
  
  public Notification build() {
    RemoteViews remoteViews;
    NotificationCompat.Style style = this.mBuilderCompat.mStyle;
    if (style != null)
      style.apply(this); 
    if (style != null) {
      remoteViews = style.makeContentView(this);
    } else {
      remoteViews = null;
    } 
    Notification notification = buildInternal();
    if (remoteViews != null) {
      notification.contentView = remoteViews;
    } else {
      remoteViews = this.mBuilderCompat.mContentView;
      if (remoteViews != null)
        notification.contentView = remoteViews; 
    } 
    if (Build.VERSION.SDK_INT >= 16 && style != null) {
      remoteViews = style.makeBigContentView(this);
      if (remoteViews != null)
        notification.bigContentView = remoteViews; 
    } 
    if (Build.VERSION.SDK_INT >= 21 && style != null) {
      remoteViews = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this);
      if (remoteViews != null)
        notification.headsUpContentView = remoteViews; 
    } 
    if (Build.VERSION.SDK_INT >= 16 && style != null) {
      Bundle bundle = NotificationCompat.getExtras(notification);
      if (bundle != null)
        style.addCompatExtras(bundle); 
    } 
    return notification;
  }
  
  protected Notification buildInternal() {
    int i = Build.VERSION.SDK_INT;
    if (i >= 26)
      return this.mBuilder.build(); 
    if (i >= 24) {
      Notification notification = this.mBuilder.build();
      if (this.mGroupAlertBehavior != 0) {
        if (notification.getGroup() != null && (notification.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2)
          removeSoundAndVibration(notification); 
        if (notification.getGroup() != null && (notification.flags & 0x200) == 0 && this.mGroupAlertBehavior == 1)
          removeSoundAndVibration(notification); 
      } 
      return notification;
    } 
    if (i >= 21) {
      this.mBuilder.setExtras(this.mExtras);
      Notification notification = this.mBuilder.build();
      RemoteViews remoteViews = this.mContentView;
      if (remoteViews != null)
        notification.contentView = remoteViews; 
      remoteViews = this.mBigContentView;
      if (remoteViews != null)
        notification.bigContentView = remoteViews; 
      remoteViews = this.mHeadsUpContentView;
      if (remoteViews != null)
        notification.headsUpContentView = remoteViews; 
      if (this.mGroupAlertBehavior != 0) {
        if (notification.getGroup() != null && (notification.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2)
          removeSoundAndVibration(notification); 
        if (notification.getGroup() != null && (notification.flags & 0x200) == 0 && this.mGroupAlertBehavior == 1)
          removeSoundAndVibration(notification); 
      } 
      return notification;
    } 
    if (i >= 20) {
      this.mBuilder.setExtras(this.mExtras);
      Notification notification = this.mBuilder.build();
      RemoteViews remoteViews = this.mContentView;
      if (remoteViews != null)
        notification.contentView = remoteViews; 
      remoteViews = this.mBigContentView;
      if (remoteViews != null)
        notification.bigContentView = remoteViews; 
      if (this.mGroupAlertBehavior != 0) {
        if (notification.getGroup() != null && (notification.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2)
          removeSoundAndVibration(notification); 
        if (notification.getGroup() != null && (notification.flags & 0x200) == 0 && this.mGroupAlertBehavior == 1)
          removeSoundAndVibration(notification); 
      } 
      return notification;
    } 
    if (i >= 19) {
      SparseArray<Bundle> sparseArray = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
      if (sparseArray != null)
        this.mExtras.putSparseParcelableArray("android.support.actionExtras", sparseArray); 
      this.mBuilder.setExtras(this.mExtras);
      Notification notification = this.mBuilder.build();
      RemoteViews remoteViews = this.mContentView;
      if (remoteViews != null)
        notification.contentView = remoteViews; 
      remoteViews = this.mBigContentView;
      if (remoteViews != null)
        notification.bigContentView = remoteViews; 
      return notification;
    } 
    if (i >= 16) {
      Notification notification = this.mBuilder.build();
      Bundle bundle2 = NotificationCompat.getExtras(notification);
      Bundle bundle1 = new Bundle(this.mExtras);
      for (String str : this.mExtras.keySet()) {
        if (bundle2.containsKey(str))
          bundle1.remove(str); 
      } 
      bundle2.putAll(bundle1);
      SparseArray<Bundle> sparseArray = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
      if (sparseArray != null)
        NotificationCompat.getExtras(notification).putSparseParcelableArray("android.support.actionExtras", sparseArray); 
      RemoteViews remoteViews = this.mContentView;
      if (remoteViews != null)
        notification.contentView = remoteViews; 
      remoteViews = this.mBigContentView;
      if (remoteViews != null)
        notification.bigContentView = remoteViews; 
      return notification;
    } 
    return this.mBuilder.getNotification();
  }
  
  public Notification.Builder getBuilder() {
    return this.mBuilder;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/app/NotificationCompatBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */