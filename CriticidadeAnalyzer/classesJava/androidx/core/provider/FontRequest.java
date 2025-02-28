package androidx.core.provider;

import android.util.Base64;
import androidx.core.util.Preconditions;
import java.util.List;

public final class FontRequest {
  private final List<List<byte[]>> mCertificates;
  
  private final int mCertificatesArray;
  
  private final String mIdentifier;
  
  private final String mProviderAuthority;
  
  private final String mProviderPackage;
  
  private final String mQuery;
  
  public FontRequest(String paramString1, String paramString2, String paramString3, List<List<byte[]>> paramList) {
    Preconditions.checkNotNull(paramString1);
    this.mProviderAuthority = paramString1;
    Preconditions.checkNotNull(paramString2);
    this.mProviderPackage = paramString2;
    Preconditions.checkNotNull(paramString3);
    this.mQuery = paramString3;
    Preconditions.checkNotNull(paramList);
    this.mCertificates = paramList;
    this.mCertificatesArray = 0;
    StringBuilder stringBuilder = new StringBuilder(this.mProviderAuthority);
    stringBuilder.append("-");
    stringBuilder.append(this.mProviderPackage);
    stringBuilder.append("-");
    stringBuilder.append(this.mQuery);
    this.mIdentifier = stringBuilder.toString();
  }
  
  public List<List<byte[]>> getCertificates() {
    return this.mCertificates;
  }
  
  public int getCertificatesArrayResId() {
    return this.mCertificatesArray;
  }
  
  public String getIdentifier() {
    return this.mIdentifier;
  }
  
  public String getProviderAuthority() {
    return this.mProviderAuthority;
  }
  
  public String getProviderPackage() {
    return this.mProviderPackage;
  }
  
  public String getQuery() {
    return this.mQuery;
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("FontRequest {mProviderAuthority: ");
    stringBuilder2.append(this.mProviderAuthority);
    stringBuilder2.append(", mProviderPackage: ");
    stringBuilder2.append(this.mProviderPackage);
    stringBuilder2.append(", mQuery: ");
    stringBuilder2.append(this.mQuery);
    stringBuilder2.append(", mCertificates:");
    stringBuilder1.append(stringBuilder2.toString());
    for (byte b = 0; b < this.mCertificates.size(); b++) {
      stringBuilder1.append(" [");
      List<byte[]> list = this.mCertificates.get(b);
      for (byte b1 = 0; b1 < list.size(); b1++) {
        stringBuilder1.append(" \"");
        stringBuilder1.append(Base64.encodeToString(list.get(b1), 0));
        stringBuilder1.append("\"");
      } 
      stringBuilder1.append(" ]");
    } 
    stringBuilder1.append("}");
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append("mCertificatesArray: ");
    stringBuilder2.append(this.mCertificatesArray);
    stringBuilder1.append(stringBuilder2.toString());
    return stringBuilder1.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/provider/FontRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */