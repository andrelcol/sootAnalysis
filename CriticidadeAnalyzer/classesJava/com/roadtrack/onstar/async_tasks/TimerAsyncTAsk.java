package com.roadtrack.onstar.async_tasks;

import android.os.CountDownTimer;
import com.roadtrack.onstar.async_tasks.intefaces.ICounterCallback;

public class TimerAsyncTAsk extends CountDownTimer {
  private ICounterCallback callback;
  
  public TimerAsyncTAsk(long paramLong1, long paramLong2, ICounterCallback paramICounterCallback) {
    super(paramLong1, paramLong2);
    this.callback = paramICounterCallback;
  }
  
  public void onFinish() {
    this.callback.onTickFInish();
  }
  
  public void onTick(long paramLong) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/TimerAsyncTAsk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */