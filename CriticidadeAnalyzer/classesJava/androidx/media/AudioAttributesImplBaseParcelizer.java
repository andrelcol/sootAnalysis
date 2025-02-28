package androidx.media;

import androidx.versionedparcelable.VersionedParcel;

public final class AudioAttributesImplBaseParcelizer {
  public static AudioAttributesImplBase read(VersionedParcel paramVersionedParcel) {
    AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
    audioAttributesImplBase.mUsage = paramVersionedParcel.readInt(audioAttributesImplBase.mUsage, 1);
    audioAttributesImplBase.mContentType = paramVersionedParcel.readInt(audioAttributesImplBase.mContentType, 2);
    audioAttributesImplBase.mFlags = paramVersionedParcel.readInt(audioAttributesImplBase.mFlags, 3);
    audioAttributesImplBase.mLegacyStream = paramVersionedParcel.readInt(audioAttributesImplBase.mLegacyStream, 4);
    return audioAttributesImplBase;
  }
  
  public static void write(AudioAttributesImplBase paramAudioAttributesImplBase, VersionedParcel paramVersionedParcel) {
    paramVersionedParcel.setSerializationFlags(false, false);
    paramVersionedParcel.writeInt(paramAudioAttributesImplBase.mUsage, 1);
    paramVersionedParcel.writeInt(paramAudioAttributesImplBase.mContentType, 2);
    paramVersionedParcel.writeInt(paramAudioAttributesImplBase.mFlags, 3);
    paramVersionedParcel.writeInt(paramAudioAttributesImplBase.mLegacyStream, 4);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/media/AudioAttributesImplBaseParcelizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */