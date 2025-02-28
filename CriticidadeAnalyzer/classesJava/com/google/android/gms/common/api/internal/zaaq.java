package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

final class zaaq extends zaau {
  private final zaak zagj;
  
  private final ArrayList<Api.Client> zagp;
  
  public zaaq(zaak paramzaak, ArrayList<Api.Client> paramArrayList) {
    super(paramzaak, null);
    this.zagp = paramArrayList;
  }
  
  public final void zaan() {
    (zaak.zad(this.zagj)).zaee.zaha = zaak.zag(this.zagj);
    ArrayList<Api.Client> arrayList = this.zagp;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      Api.Client client = (Api.Client)arrayList.get(b);
      b++;
      ((Api.Client)client).getRemoteService(zaak.zah(this.zagj), (zaak.zad(this.zagj)).zaee.zaha);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaaq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */