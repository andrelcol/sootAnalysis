package com.roadtrack.onstar;

import java.io.Serializable;

public enum Enums$navigationProcess implements Serializable {
  External, Favorites_history, FindMe, FollowMe, FollowMeFinished, Geofence, Mixed, Navigation, Notification;
  
  private static final Enums$navigationProcess[] $VALUES;
  
  static {
    FindMe = new Enums$navigationProcess("FindMe", 1);
    FollowMe = new Enums$navigationProcess("FollowMe", 2);
    Geofence = new Enums$navigationProcess("Geofence", 3);
    FollowMeFinished = new Enums$navigationProcess("FollowMeFinished", 4);
    External = new Enums$navigationProcess("External", 5);
    Mixed = new Enums$navigationProcess("Mixed", 6);
    Notification = new Enums$navigationProcess("Notification", 7);
    Favorites_history = new Enums$navigationProcess("Favorites_history", 8);
    $VALUES = new Enums$navigationProcess[] { Navigation, FindMe, FollowMe, Geofence, FollowMeFinished, External, Mixed, Notification, Favorites_history };
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$navigationProcess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */