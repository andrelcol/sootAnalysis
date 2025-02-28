package com.roadtrack.onstar;

import android.os.Bundle;
import android.os.Debug;
import androidx.fragment.app.FragmentActivity;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.utils.Utilities;

public class TbtManeuverInfo extends FragmentActivity {
  public void onBackPressed() {
    super.onBackPressed();
  }
  
  public void onDestroy() {
    super.onDestroy();
    Debug.stopMethodTracing();
    Utilities.stopWakeDevice();
    GlobalMembers.tbtMuteStatus = 1;
  }
  
  public void onResume() {
    super.onResume();
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
  }
  
  public void onStop() {
    super.onStop();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/TbtManeuverInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */