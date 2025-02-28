package com.roadtrack.onstar.analytics;

public enum AnalyticsTypes {
  FACEBOOK, FIREBASE;
  
  private static final AnalyticsTypes[] $VALUES;
  
  static {
    FACEBOOK = new AnalyticsTypes("FACEBOOK", 1);
    $VALUES = new AnalyticsTypes[] { FIREBASE, FACEBOOK };
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/analytics/AnalyticsTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */