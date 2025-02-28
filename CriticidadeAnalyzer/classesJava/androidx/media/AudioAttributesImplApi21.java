package androidx.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;

@TargetApi(21)
class AudioAttributesImplApi21 implements AudioAttributesImpl {
  AudioAttributes mAudioAttributes;
  
  int mLegacyStreamType = -1;
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof AudioAttributesImplApi21))
      return false; 
    paramObject = paramObject;
    return this.mAudioAttributes.equals(((AudioAttributesImplApi21)paramObject).mAudioAttributes);
  }
  
  public int hashCode() {
    return this.mAudioAttributes.hashCode();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AudioAttributesCompat: audioattributes=");
    stringBuilder.append(this.mAudioAttributes);
    return stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/media/AudioAttributesImplApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */