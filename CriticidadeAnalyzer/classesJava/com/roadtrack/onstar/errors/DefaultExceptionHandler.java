package com.roadtrack.onstar.errors;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.roadtrack.onstar.ui.login.LoginActivity;
import com.roadtrack.onstar.utils.Utilities;
import java.io.PrintWriter;
import java.io.StringWriter;

public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {
  Activity activity;
  
  Context ctx;
  
  public DefaultExceptionHandler(Context paramContext, Activity paramActivity) {
    this.ctx = paramContext;
    this.activity = paramActivity;
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
    String str;
    StringWriter stringWriter = new StringWriter();
    new PrintWriter(stringWriter);
    try {
      Intent intent = new Intent();
      this((Context)this.activity, LoginActivity.class);
      intent.addFlags(335577088);
      PendingIntent pendingIntent = PendingIntent.getActivity(this.ctx, 0, intent, intent.getFlags() | 0x4000000 | 0x8000000);
      ((AlarmManager)this.ctx.getSystemService("alarm")).set(1, System.currentTimeMillis() + 1000L, pendingIntent);
      this.activity.finish();
      str = paramThrowable.getMessage();
      StringBuilder stringBuilder = new StringBuilder();
    } catch (Exception exception) {
      str = exception.getMessage();
      StringBuilder stringBuilder = new StringBuilder();
    } finally {}
    paramThrowable.append(str);
    paramThrowable.append("\n");
    paramThrowable.append(stringWriter.toString());
    Utilities.escribeArchivo("DefaultExceptionHandler", "Error", paramThrowable.toString());
    System.exit(2);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/errors/DefaultExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */