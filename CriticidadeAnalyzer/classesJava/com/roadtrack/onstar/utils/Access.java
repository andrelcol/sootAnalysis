package com.roadtrack.onstar.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import com.roadtrack.onstar.DAO.DBFunctions;

public class Access {
  public static boolean IsVehicleTheft(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    return IsVehicleTheft(paramContext, paramString1, paramString2, paramString3, paramBoolean, false);
  }
  
  public static boolean IsVehicleTheft(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2) {
    Activity activity = (Activity)paramContext;
    paramBoolean1 = (new DBFunctions(paramContext)).userDataTableHandler(paramString1, paramString2, paramString3, paramBoolean1);
    if (paramBoolean1)
      Utilities.showTheftAutoBanner((TextView)activity.findViewById(2131297163), paramContext, paramBoolean1, paramBoolean2); 
    return paramBoolean1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/Access.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */