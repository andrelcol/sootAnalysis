package androidx.media;

import androidx.versionedparcelable.VersionedParcel;

public final class AudioAttributesCompatParcelizer {
  public static AudioAttributesCompat read(VersionedParcel paramVersionedParcel) {
    AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
    audioAttributesCompat.mImpl = (AudioAttributesImpl)paramVersionedParcel.readVersionedParcelable(audioAttributesCompat.mImpl, 1);
    return audioAttributesCompat;
  }
  
  public static void write(AudioAttributesCompat paramAudioAttributesCompat, VersionedParcel paramVersionedParcel) {
    paramVersionedParcel.setSerializationFlags(false, false);
    paramVersionedParcel.writeVersionedParcelable(paramAudioAttributesCompat.mImpl, 1);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/media/AudioAttributesCompatParcelizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */