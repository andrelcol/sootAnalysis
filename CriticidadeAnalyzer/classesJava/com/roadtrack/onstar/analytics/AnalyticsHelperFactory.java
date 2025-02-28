package com.roadtrack.onstar.analytics;

public class AnalyticsHelperFactory {
  public static AnalyticsHelper getAnalyticsHelper(AnalyticsTypes paramAnalyticsTypes) {
    if (null.$SwitchMap$com$roadtrack$onstar$analytics$AnalyticsTypes[paramAnalyticsTypes.ordinal()] == 1)
      return new FirebaseAnalyticsHelper(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid analytics service: ");
    stringBuilder.append(paramAnalyticsTypes.name());
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/analytics/AnalyticsHelperFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */