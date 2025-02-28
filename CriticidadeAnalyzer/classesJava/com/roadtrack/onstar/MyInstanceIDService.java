package com.roadtrack.onstar;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyInstanceIDService extends FirebaseInstanceIdService {
  public void onTokenRefresh() {
    super.onTokenRefresh();
    FirebaseInstanceId.getInstance().getToken();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/MyInstanceIDService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */