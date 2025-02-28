package com.roadtrack.onstar;

import java.io.Serializable;

public enum Enums$TypeItem implements Serializable {
  Favorites, FavoritesHistory, FavoritesSilgleHist, Historical, NaviFavoritesHist, NavigationHistory, POI, shareNaviFavoritesHist;
  
  private static final Enums$TypeItem[] $VALUES;
  
  static {
    FavoritesHistory = new Enums$TypeItem("FavoritesHistory", 3);
    NaviFavoritesHist = new Enums$TypeItem("NaviFavoritesHist", 4);
    FavoritesSilgleHist = new Enums$TypeItem("FavoritesSilgleHist", 5);
    shareNaviFavoritesHist = new Enums$TypeItem("shareNaviFavoritesHist", 6);
    POI = new Enums$TypeItem("POI", 7);
    $VALUES = new Enums$TypeItem[] { Favorites, Historical, NavigationHistory, FavoritesHistory, NaviFavoritesHist, FavoritesSilgleHist, shareNaviFavoritesHist, POI };
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$TypeItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */