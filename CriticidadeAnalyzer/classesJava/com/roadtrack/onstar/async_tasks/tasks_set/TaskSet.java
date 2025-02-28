package com.roadtrack.onstar.async_tasks.tasks_set;

import android.content.Context;
import android.os.AsyncTask;
import com.roadtrack.onstar.async_tasks.intefaces.Base_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.DtcStatus_Interface;
import com.roadtrack.onstar.async_tasks.intefaces.Dtc_Interface;
import com.roadtrack.onstar.async_tasks.tasks.GetDtcs_Task;
import com.roadtrack.onstar.async_tasks.tasks.GetGmt_Task;
import com.roadtrack.onstar.async_tasks.tasks.SendTomTomStatistics_Task;
import com.roadtrack.onstar.async_tasks.tasks.SetGmt_Task;

public class TaskSet {
  private AsyncTask getDtcs_Task;
  
  public void executeDtcs_Task(Dtc_Interface paramDtc_Interface, Context paramContext, String paramString) {
    AsyncTask asyncTask = this.getDtcs_Task;
    if (asyncTask != null && asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
      paramDtc_Interface.onRunning();
    } else {
      this.getDtcs_Task = (new GetDtcs_Task(paramDtc_Interface, paramContext, paramString)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    } 
  }
  
  public void executeSendTomTomStatistics_Task(Base_Interface paramBase_Interface, Context paramContext) {
    (new SendTomTomStatistics_Task(paramBase_Interface, paramContext)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public void getDtcs_Task_Status(DtcStatus_Interface paramDtcStatus_Interface) {
    AsyncTask asyncTask = this.getDtcs_Task;
    if (asyncTask != null && asyncTask.getStatus() == AsyncTask.Status.RUNNING)
      paramDtcStatus_Interface.onRunning(); 
  }
  
  public void getGtm_Task(Context paramContext, Base_Interface paramBase_Interface) {
    (new GetGmt_Task(paramContext, paramBase_Interface)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public void setGtm_Task(Context paramContext, String paramString1, Base_Interface paramBase_Interface, String paramString2) {
    if (paramString2 == null) {
      (new SetGmt_Task(paramContext, paramBase_Interface, paramString1)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    } else {
      (new SetGmt_Task(paramContext, paramBase_Interface, paramString1, paramString2)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/tasks_set/TaskSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */