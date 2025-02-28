package com.roadtrack.onstar.async_tasks.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SwitchCompat;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.pid.PIDActivity;

public class UpdateStatusDTCSelector_task extends AsyncTask<Void, Void, String> {
  private final String TAG = PIDActivity.class.getSimpleName();
  
  private Context mContext;
  
  private String mDeviceId;
  
  OnPostExecuteListenerUpdateDTCSelector mOnPostExecuteListenerUpdateDTCSelector;
  
  private ProgressBar mProgressbar;
  
  private String mstatusAlert = "0";
  
  private SwitchCompat mswitch;
  
  public UpdateStatusDTCSelector_task(Context paramContext, String paramString, ProgressBar paramProgressBar, SwitchCompat paramSwitchCompat) {
    this.mContext = paramContext;
    this.mDeviceId = paramString;
    this.mProgressbar = paramProgressBar;
    this.mswitch = paramSwitchCompat;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    return (new WsAccess(this.mContext)).updateDtcStatus(this.TAG, this.mDeviceId, this.mstatusAlert);
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    this.mProgressbar.setVisibility(8);
    this.mswitch.setEnabled(true);
    OnPostExecuteListenerUpdateDTCSelector onPostExecuteListenerUpdateDTCSelector = this.mOnPostExecuteListenerUpdateDTCSelector;
    if (onPostExecuteListenerUpdateDTCSelector != null)
      onPostExecuteListenerUpdateDTCSelector.onPostExecuteListener(paramString); 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    new DBFunctions(this.mContext);
    this.mProgressbar.setVisibility(0);
    this.mswitch.setEnabled(false);
    if (!this.mswitch.isChecked()) {
      this.mstatusAlert = "0";
    } else {
      this.mstatusAlert = "1";
    } 
  }
  
  public void setOnPostExecuteListenerUpdateDTCSelector(OnPostExecuteListenerUpdateDTCSelector paramOnPostExecuteListenerUpdateDTCSelector) {
    this.mOnPostExecuteListenerUpdateDTCSelector = paramOnPostExecuteListenerUpdateDTCSelector;
  }
  
  public static interface OnPostExecuteListenerUpdateDTCSelector {
    void onPostExecuteListener(String param1String);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/UpdateStatusDTCSelector_task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */