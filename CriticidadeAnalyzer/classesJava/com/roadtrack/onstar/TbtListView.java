package com.roadtrack.onstar;

import android.os.Bundle;
import android.os.Debug;
import androidx.fragment.app.FragmentActivity;
import com.roadtrack.onstar.utils.Utilities;

public class TbtListView extends FragmentActivity {
  public void onDestroy() {
    super.onDestroy();
    Debug.stopMethodTracing();
    Utilities.stopWakeDevice();
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


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/TbtListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */