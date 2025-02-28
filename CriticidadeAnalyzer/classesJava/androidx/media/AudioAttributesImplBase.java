package androidx.media;

import java.util.Arrays;

class AudioAttributesImplBase implements AudioAttributesImpl {
  int mContentType = 0;
  
  int mFlags = 0;
  
  int mLegacyStream = -1;
  
  int mUsage = 0;
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof AudioAttributesImplBase;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.mContentType == paramObject.getContentType()) {
      bool = bool1;
      if (this.mFlags == paramObject.getFlags()) {
        bool = bool1;
        if (this.mUsage == paramObject.getUsage()) {
          bool = bool1;
          if (this.mLegacyStream == ((AudioAttributesImplBase)paramObject).mLegacyStream)
            bool = true; 
        } 
      } 
    } 
    return bool;
  }
  
  public int getContentType() {
    return this.mContentType;
  }
  
  public int getFlags() {
    int i;
    int j = this.mFlags;
    int k = getLegacyStreamType();
    if (k == 6) {
      i = j | 0x4;
    } else {
      i = j;
      if (k == 7)
        i = j | 0x1; 
    } 
    return i & 0x111;
  }
  
  public int getLegacyStreamType() {
    int i = this.mLegacyStream;
    return (i != -1) ? i : AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
  }
  
  public int getUsage() {
    return this.mUsage;
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream) });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("AudioAttributesCompat:");
    if (this.mLegacyStream != -1) {
      stringBuilder.append(" stream=");
      stringBuilder.append(this.mLegacyStream);
      stringBuilder.append(" derived");
    } 
    stringBuilder.append(" usage=");
    stringBuilder.append(AudioAttributesCompat.usageToString(this.mUsage));
    stringBuilder.append(" content=");
    stringBuilder.append(this.mContentType);
    stringBuilder.append(" flags=0x");
    stringBuilder.append(Integer.toHexString(this.mFlags).toUpperCase());
    return stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/media/AudioAttributesImplBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */