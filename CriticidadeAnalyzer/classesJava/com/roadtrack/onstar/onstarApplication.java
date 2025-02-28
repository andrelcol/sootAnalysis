package com.roadtrack.onstar;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.UserPreferenceVO;
import com.roadtrack.onstar.VO.ValidActionsVO;
import com.roadtrack.onstar.analytics.AnalyticsHelper;
import com.roadtrack.onstar.analytics.AnalyticsHelperFactory;
import com.roadtrack.onstar.analytics.AnalyticsTypes;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.List;

public class onstarApplication extends Application {
  private static Context ctx;
  
  public static String fontPath;
  
  private String SessionKey;
  
  private String accountID;
  
  private String deviceTypeId;
  
  public String fontPathChalkduster = "fonts/Chalkduster.ttf";
  
  public String fontPathCourierbold = "fonts/courierbold.otf";
  
  public String fontPathLouisBold = "fonts/PFBeauSansProBold.ttf";
  
  public String fontPathLouisCondensedDemi = "fonts/LouisCondensedDemi.otf";
  
  public String fontPathLouisItalic = "fonts/LouisItalic.otf";
  
  public String fontPathLouisRegular = "fonts/PFBeauSansProRegular.ttf";
  
  private String locatorUserId;
  
  private List<UserDevicesVO> mDeviceUserList = new ArrayList<UserDevicesVO>();
  
  private String[] userAccessData;
  
  private UserPreferenceVO userPreference;
  
  private boolean waterMarkActive;
  
  public static Context getContext() {
    return ctx;
  }
  
  public static Typeface getTypeface(Context paramContext, String paramString) {
    fontPath = paramString;
    return Typeface.createFromAsset(paramContext.getAssets(), fontPath);
  }
  
  private void initFirebaseApp(Context paramContext) {
    try {
      AnalyticsHelper analyticsHelper = AnalyticsHelperFactory.getAnalyticsHelper(AnalyticsTypes.FIREBASE);
      analyticsHelper.initializeService(paramContext);
      analyticsHelper.getInstance(paramContext);
    } catch (Exception exception) {
      Utilities.escribeArchivo("onstarApplication", exception);
    } 
  }
  
  public void clearMdeviceUserList() {
    this.mDeviceUserList.clear();
  }
  
  public String getAccountID() {
    return this.accountID;
  }
  
  public String getDeviceTypeId() {
    return this.deviceTypeId;
  }
  
  public String getLocatorUserId() {
    return this.locatorUserId;
  }
  
  public String getSessionKey() {
    return this.SessionKey;
  }
  
  public String[] getUserAccessData() {
    return this.userAccessData;
  }
  
  public UserPreferenceVO getUserPreference() {
    return this.userPreference;
  }
  
  public List<UserDevicesVO> getmDeviceUserList() {
    return this.mDeviceUserList;
  }
  
  public boolean isWaterMarkActive() {
    return this.waterMarkActive;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    Utilities.setLanguageAndRegion();
  }
  
  public void onCreate() {
    super.onCreate();
    ctx = getApplicationContext();
    Utilities.setLanguageAndRegion();
    GlobalMembers.contexGlobal = getBaseContext();
    initFirebaseApp(ctx);
  }
  
  public void setAccountID(String paramString) {
    this.accountID = paramString;
  }
  
  public void setAppStatus(int paramInt) {}
  
  public void setDeviceTypeId(String paramString) {
    this.deviceTypeId = paramString;
  }
  
  public void setGeneralContext(Context paramContext) {}
  
  public void setLocatorUserId(String paramString) {
    this.locatorUserId = paramString;
  }
  
  public void setSessionKey(String paramString) {
    this.SessionKey = paramString;
  }
  
  public void setUserAccessData(String[] paramArrayOfString) {
    this.userAccessData = paramArrayOfString;
  }
  
  public void setUserPreference(UserPreferenceVO paramUserPreferenceVO) {
    this.userPreference = paramUserPreferenceVO;
  }
  
  public void setValidActionsPlan(List<ValidActionsVO> paramList) {}
  
  public void setValidActionsWS(List<ValidActionsVO> paramList) {}
  
  public void setWaterMarkActive(boolean paramBoolean) {
    this.waterMarkActive = paramBoolean;
  }
  
  public void setmDeviceUserList(List<UserDevicesVO> paramList) {
    this.mDeviceUserList = paramList;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/onstarApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */