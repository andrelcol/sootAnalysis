package com.roadtrack.onstar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.Utilities;

public class testhmi extends Activity {
  private StringsResourcesVO stringsResourcesVO;
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427493);
    this.stringsResourcesVO = new StringsResourcesVO();
    GlobalMembers.btnpress = 1;
    GlobalMembers.intmenuplat = 0;
    GlobalMembers.numVerFragment = 0;
    Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.BTNoPairpermision, 2131689480);
    if (MainActivity.isBtAndPlatinumConnected()) {
      Utilities.wakeDevice(getApplicationContext(), "RTMobile_HMI");
      MainActivity.CallOrHangUp(Enums$Calls.IVR);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/testhmi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */