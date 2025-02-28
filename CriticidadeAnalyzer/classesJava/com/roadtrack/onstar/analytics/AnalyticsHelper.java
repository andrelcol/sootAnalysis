package com.roadtrack.onstar.analytics;

import android.content.Context;
import com.roadtrack.onstar.Enums;

public interface AnalyticsHelper {
  void analyticsEventGeneric(Context paramContext, AnalyticsEventModel paramAnalyticsEventModel);
  
  void genericAnalyticsEventBottom(Context paramContext, Enums.Services paramServices);
  
  void genericAnalyticsEventButtonAction(Context paramContext, Enums.Services paramServices);
  
  void genericAnalyticsEventDrawer(Context paramContext, Enums.Services paramServices);
  
  Object getInstance(Context paramContext) throws RuntimeException;
  
  void initializeService(Context paramContext);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/analytics/AnalyticsHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */