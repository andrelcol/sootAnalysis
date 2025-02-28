package com.roadtrack.onstar.async_tasks.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SwitchCompat;
import com.roadtrack.onstar.BO.WsAccess;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.pid.PIDActivity;

public class GetDTCSelector_task extends AsyncTask<Void, Void, String> {
  private final String TAG = PIDActivity.class.getSimpleName();
  
  private Context mContext;
  
  private String mDeviceId;
  
  OnPostExecuteListenerDTCSelector mOnPostExecuteListenerDTCSelector;
  
  OnPreExecuteListenerDTCSelector mOnPreExecuteListenerDTCSelector;
  
  private ProgressBar mProgressbar;
  
  private SwitchCompat mswitch;
  
  public GetDTCSelector_task(Context paramContext, String paramString, ProgressBar paramProgressBar, SwitchCompat paramSwitchCompat) {
    this.mContext = paramContext;
    this.mDeviceId = paramString;
    this.mProgressbar = paramProgressBar;
    this.mswitch = paramSwitchCompat;
  }
  
  protected String doInBackground(Void... paramVarArgs) {
    return (new WsAccess(this.mContext)).getDtcStatus(this.TAG, this.mDeviceId);
  }
  
  protected void onPostExecute(String paramString) {
    super.onPostExecute(paramString);
    this.mProgressbar.setVisibility(8);
    this.mswitch.setEnabled(true);
    OnPostExecuteListenerDTCSelector onPostExecuteListenerDTCSelector = this.mOnPostExecuteListenerDTCSelector;
    if (onPostExecuteListenerDTCSelector != null)
      onPostExecuteListenerDTCSelector.onPostExecuteListener(paramString); 
  }
  
  protected void onPreExecute() {
    super.onPreExecute();
    new DBFunctions(this.mContext);
    this.mProgressbar.setVisibility(0);
    this.mswitch.setEnabled(false);
    OnPreExecuteListenerDTCSelector onPreExecuteListenerDTCSelector = this.mOnPreExecuteListenerDTCSelector;
    if (onPreExecuteListenerDTCSelector != null)
      onPreExecuteListenerDTCSelector.onPreExecuteListener(); 
  }
  
  public void setOnPostExecuteListenerDTCSelector(OnPostExecuteListenerDTCSelector paramOnPostExecuteListenerDTCSelector) {
    this.mOnPostExecuteListenerDTCSelector = paramOnPostExecuteListenerDTCSelector;
  }
  
  public static interface OnPostExecuteListenerDTCSelector {
    void onPostExecuteListener(String param1String);
  }
  
  public static interface OnPreExecuteListenerDTCSelector {
    void onPreExecuteListener();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks/GetDTCSelector_task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */