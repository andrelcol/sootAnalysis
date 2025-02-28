package com.google.firebase.iid;

import com.google.android.gms.tasks.Task;

public interface MessagingChannel {
  Task<Void> buildChannel(String paramString1, String paramString2);
  
  Task<String> getToken(String paramString1, String paramString2, String paramString3, String paramString4);
  
  boolean isAvailable();
  
  boolean isChannelBuilt();
  
  Task<Void> subscribeToTopic(String paramString1, String paramString2, String paramString3);
  
  Task<Void> unsubscribeFromTopic(String paramString1, String paramString2, String paramString3);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/MessagingChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */