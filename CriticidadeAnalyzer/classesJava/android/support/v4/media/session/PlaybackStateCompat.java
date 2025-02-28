package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PlaybackStateCompat implements Parcelable {
  public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new Parcelable.Creator<PlaybackStateCompat>() {
      public PlaybackStateCompat createFromParcel(Parcel param1Parcel) {
        return new PlaybackStateCompat(param1Parcel);
      }
      
      public PlaybackStateCompat[] newArray(int param1Int) {
        return new PlaybackStateCompat[param1Int];
      }
    };
  
  final long mActions;
  
  final long mActiveItemId;
  
  final long mBufferedPosition;
  
  List<CustomAction> mCustomActions;
  
  final int mErrorCode;
  
  final CharSequence mErrorMessage;
  
  final Bundle mExtras;
  
  final long mPosition;
  
  final float mSpeed;
  
  final int mState;
  
  final long mUpdateTime;
  
  PlaybackStateCompat(int paramInt1, long paramLong1, long paramLong2, float paramFloat, long paramLong3, int paramInt2, CharSequence paramCharSequence, long paramLong4, List<CustomAction> paramList, long paramLong5, Bundle paramBundle) {
    this.mState = paramInt1;
    this.mPosition = paramLong1;
    this.mBufferedPosition = paramLong2;
    this.mSpeed = paramFloat;
    this.mActions = paramLong3;
    this.mErrorCode = paramInt2;
    this.mErrorMessage = paramCharSequence;
    this.mUpdateTime = paramLong4;
    this.mCustomActions = new ArrayList<CustomAction>(paramList);
    this.mActiveItemId = paramLong5;
    this.mExtras = paramBundle;
  }
  
  PlaybackStateCompat(Parcel paramParcel) {
    this.mState = paramParcel.readInt();
    this.mPosition = paramParcel.readLong();
    this.mSpeed = paramParcel.readFloat();
    this.mUpdateTime = paramParcel.readLong();
    this.mBufferedPosition = paramParcel.readLong();
    this.mActions = paramParcel.readLong();
    this.mErrorMessage = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mCustomActions = paramParcel.createTypedArrayList(CustomAction.CREATOR);
    this.mActiveItemId = paramParcel.readLong();
    this.mExtras = paramParcel.readBundle(MediaSessionCompat.class.getClassLoader());
    this.mErrorCode = paramParcel.readInt();
  }
  
  public static PlaybackStateCompat fromPlaybackState(Object paramObject) {
    PlaybackStateCompat playbackStateCompat;
    List<Object> list2 = null;
    Bundle bundle = null;
    List<Object> list1 = list2;
    if (paramObject != null) {
      list1 = list2;
      if (Build.VERSION.SDK_INT >= 21) {
        list2 = PlaybackStateCompatApi21.getCustomActions(paramObject);
        if (list2 != null) {
          list1 = new ArrayList(list2.size());
          Iterator iterator = list2.iterator();
          while (iterator.hasNext())
            list1.add(CustomAction.fromCustomAction(iterator.next())); 
        } else {
          list1 = null;
        } 
        if (Build.VERSION.SDK_INT >= 22)
          bundle = PlaybackStateCompatApi22.getExtras(paramObject); 
        playbackStateCompat = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(paramObject), PlaybackStateCompatApi21.getPosition(paramObject), PlaybackStateCompatApi21.getBufferedPosition(paramObject), PlaybackStateCompatApi21.getPlaybackSpeed(paramObject), PlaybackStateCompatApi21.getActions(paramObject), 0, PlaybackStateCompatApi21.getErrorMessage(paramObject), PlaybackStateCompatApi21.getLastPositionUpdateTime(paramObject), (List)list1, PlaybackStateCompatApi21.getActiveQueueItemId(paramObject), bundle);
      } 
    } 
    return playbackStateCompat;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("PlaybackState {");
    stringBuilder.append("state=");
    stringBuilder.append(this.mState);
    stringBuilder.append(", position=");
    stringBuilder.append(this.mPosition);
    stringBuilder.append(", buffered position=");
    stringBuilder.append(this.mBufferedPosition);
    stringBuilder.append(", speed=");
    stringBuilder.append(this.mSpeed);
    stringBuilder.append(", updated=");
    stringBuilder.append(this.mUpdateTime);
    stringBuilder.append(", actions=");
    stringBuilder.append(this.mActions);
    stringBuilder.append(", error code=");
    stringBuilder.append(this.mErrorCode);
    stringBuilder.append(", error message=");
    stringBuilder.append(this.mErrorMessage);
    stringBuilder.append(", custom actions=");
    stringBuilder.append(this.mCustomActions);
    stringBuilder.append(", active item id=");
    stringBuilder.append(this.mActiveItemId);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mState);
    paramParcel.writeLong(this.mPosition);
    paramParcel.writeFloat(this.mSpeed);
    paramParcel.writeLong(this.mUpdateTime);
    paramParcel.writeLong(this.mBufferedPosition);
    paramParcel.writeLong(this.mActions);
    TextUtils.writeToParcel(this.mErrorMessage, paramParcel, paramInt);
    paramParcel.writeTypedList(this.mCustomActions);
    paramParcel.writeLong(this.mActiveItemId);
    paramParcel.writeBundle(this.mExtras);
    paramParcel.writeInt(this.mErrorCode);
  }
  
  public static final class CustomAction implements Parcelable {
    public static final Parcelable.Creator<CustomAction> CREATOR = new Parcelable.Creator<CustomAction>() {
        public PlaybackStateCompat.CustomAction createFromParcel(Parcel param2Parcel) {
          return new PlaybackStateCompat.CustomAction(param2Parcel);
        }
        
        public PlaybackStateCompat.CustomAction[] newArray(int param2Int) {
          return new PlaybackStateCompat.CustomAction[param2Int];
        }
      };
    
    private final String mAction;
    
    private final Bundle mExtras;
    
    private final int mIcon;
    
    private final CharSequence mName;
    
    CustomAction(Parcel param1Parcel) {
      this.mAction = param1Parcel.readString();
      this.mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(param1Parcel);
      this.mIcon = param1Parcel.readInt();
      this.mExtras = param1Parcel.readBundle(MediaSessionCompat.class.getClassLoader());
    }
    
    CustomAction(String param1String, CharSequence param1CharSequence, int param1Int, Bundle param1Bundle) {
      this.mAction = param1String;
      this.mName = param1CharSequence;
      this.mIcon = param1Int;
      this.mExtras = param1Bundle;
    }
    
    public static CustomAction fromCustomAction(Object param1Object) {
      return (param1Object == null || Build.VERSION.SDK_INT < 21) ? null : new CustomAction(PlaybackStateCompatApi21.CustomAction.getAction(param1Object), PlaybackStateCompatApi21.CustomAction.getName(param1Object), PlaybackStateCompatApi21.CustomAction.getIcon(param1Object), PlaybackStateCompatApi21.CustomAction.getExtras(param1Object));
    }
    
    public int describeContents() {
      return 0;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Action:mName='");
      stringBuilder.append(this.mName);
      stringBuilder.append(", mIcon=");
      stringBuilder.append(this.mIcon);
      stringBuilder.append(", mExtras=");
      stringBuilder.append(this.mExtras);
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeString(this.mAction);
      TextUtils.writeToParcel(this.mName, param1Parcel, param1Int);
      param1Parcel.writeInt(this.mIcon);
      param1Parcel.writeBundle(this.mExtras);
    }
  }
  
  static final class null implements Parcelable.Creator<CustomAction> {
    public PlaybackStateCompat.CustomAction createFromParcel(Parcel param1Parcel) {
      return new PlaybackStateCompat.CustomAction(param1Parcel);
    }
    
    public PlaybackStateCompat.CustomAction[] newArray(int param1Int) {
      return new PlaybackStateCompat.CustomAction[param1Int];
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/android/support/v4/media/session/PlaybackStateCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */