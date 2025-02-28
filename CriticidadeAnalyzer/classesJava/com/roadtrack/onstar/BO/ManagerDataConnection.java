package com.roadtrack.onstar.BO;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.Utilities;

public class ManagerDataConnection {
  String DataConnection = "wif";
  
  private Context _contex;
  
  public ManagerDataConnection(Context paramContext) {
    this._contex = paramContext;
  }
  
  public boolean CanSendData() {
    return HaveNetworkConnection(this._contex);
  }
  
  public boolean HaveNetworkConnection(Context paramContext) {
    ConnectivityManager connectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    null = true;
    NetworkInfo networkInfo1 = connectivityManager.getNetworkInfo(1);
    NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
    String str2 = this.DataConnection;
    String str1 = Utilities.getStringFromConfigList(paramContext, (new StringsResourcesVO()).main_lbl_popup_servicionodisponible_1, 2131690067);
    if (!str2.equalsIgnoreCase("nin"))
      if ((str2.equalsIgnoreCase("wif") | str2.equalsIgnoreCase("wmo")) != 0) {
        if (networkInfo1.isConnected())
          return null; 
        if (networkInfo2 != null) {
          if (networkInfo2.isConnected())
            return null; 
        } else {
          Toast.makeText(this._contex, str1, 1).show();
        } 
      } else if (str2.equalsIgnoreCase("mov")) {
        if (networkInfo2 != null) {
          if (networkInfo2.isConnected())
            return null; 
        } else {
          Toast.makeText(this._contex, str1, 1).show();
        } 
      }  
    return false;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/ManagerDataConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */