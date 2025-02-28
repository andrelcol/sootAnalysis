package com.roadtrack.onstar.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.utils.Utilities;

public class ActivityCall extends Activity {
  private static int _iCallIndex = 0;
  
  private static int _iCallStatus = 0;
  
  private static Context mContext;
  
  static String strNombreBoton = "";
  
  private boolean bComandoRecibido = false;
  
  private View.OnClickListener boton = new View.OnClickListener() {
      final ActivityCall this$0;
      
      public void onClick(View param1View) {
        if (param1View.getId() == 2131296414)
          ActivityCall.this.finalizar(); 
      }
    };
  
  private Handler customHandler = new Handler();
  
  private ImageView imgButton;
  
  private ImageView imgButtonEndCall;
  
  private Intent intent;
  
  private BroadcastReceiver mReceiver = new BroadcastReceiver() {
      final ActivityCall this$0;
      
      @SuppressLint({"NewApi"})
      public void onReceive(Context param1Context, Intent param1Intent) {
        // Byte code:
        //   0: aload_2
        //   1: invokevirtual getAction : ()Ljava/lang/String;
        //   4: ldc 'com.roadtrack.onstar.BO.NEW_CALL_STATUS_RESPONSE_OBTAINED_EVENT'
        //   6: invokevirtual equals : (Ljava/lang/Object;)Z
        //   9: ifeq -> 164
        //   12: invokestatic access$100 : ()I
        //   15: ifne -> 25
        //   18: aload_0
        //   19: getfield this$0 : Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;
        //   22: invokestatic access$000 : (Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;)V
        //   25: aload_0
        //   26: getfield this$0 : Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;
        //   29: iconst_1
        //   30: invokestatic access$202 : (Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;Z)Z
        //   33: pop
        //   34: invokestatic values : ()[Lcom/roadtrack/onstar/Enums$BTCallStatus;
        //   37: invokestatic access$300 : ()I
        //   40: aaload
        //   41: astore_1
        //   42: getstatic com/roadtrack/onstar/ui/dialogs/ActivityCall$4.$SwitchMap$com$roadtrack$onstar$Enums$BTCallStatus : [I
        //   45: aload_1
        //   46: invokevirtual ordinal : ()I
        //   49: iaload
        //   50: tableswitch default -> 92, 1 -> 123, 2 -> 164, 3 -> 109, 4 -> 95, 5 -> 164, 6 -> 164, 7 -> 164
        //   92: goto -> 164
        //   95: aload_0
        //   96: getfield this$0 : Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;
        //   99: invokestatic access$400 : (Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;)Landroid/widget/ImageView;
        //   102: iconst_1
        //   103: invokevirtual setEnabled : (Z)V
        //   106: goto -> 164
        //   109: aload_0
        //   110: getfield this$0 : Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;
        //   113: invokestatic access$400 : (Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;)Landroid/widget/ImageView;
        //   116: iconst_1
        //   117: invokevirtual setEnabled : (Z)V
        //   120: goto -> 164
        //   123: aload_0
        //   124: getfield this$0 : Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;
        //   127: invokestatic access$400 : (Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;)Landroid/widget/ImageView;
        //   130: iconst_1
        //   131: invokevirtual setEnabled : (Z)V
        //   134: aload_0
        //   135: getfield this$0 : Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;
        //   138: invokestatic uptimeMillis : ()J
        //   141: invokestatic access$502 : (Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;J)J
        //   144: pop2
        //   145: aload_0
        //   146: getfield this$0 : Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;
        //   149: invokestatic access$700 : (Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;)Landroid/os/Handler;
        //   152: aload_0
        //   153: getfield this$0 : Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;
        //   156: invokestatic access$600 : (Lcom/roadtrack/onstar/ui/dialogs/ActivityCall;)Ljava/lang/Runnable;
        //   159: lconst_0
        //   160: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
        //   163: pop
        //   164: return
      }
    };
  
  private long startTime = 0L;
  
  long timeInMilliseconds = 0L;
  
  long timeSwapBuff = 0L;
  
  private TextView timerValue;
  
  private TextView txtCallButton;
  
  private Runnable updateTimerThread = new Runnable() {
      final ActivityCall this$0;
      
      public void run() {
        ActivityCall.this.timeInMilliseconds = SystemClock.uptimeMillis() - ActivityCall.this.startTime;
        ActivityCall activityCall = ActivityCall.this;
        activityCall.updatedTime = activityCall.timeSwapBuff + activityCall.timeInMilliseconds;
        long l = activityCall.updatedTime;
        int i = (int)(l / 1000L);
        int k = i / 60;
        int j = (int)(l % 1000L);
        TextView textView = activityCall.timerValue;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(k);
        stringBuilder.append(":");
        stringBuilder.append(String.format("%02d", new Object[] { Integer.valueOf(i % 60) }));
        stringBuilder.append(":");
        stringBuilder.append(String.format("%03d", new Object[] { Integer.valueOf(j) }));
        textView.setText(stringBuilder.toString());
        ActivityCall.this.customHandler.postDelayed(this, 0L);
      }
    };
  
  long updatedTime = 0L;
  
  private void finalizar() {
    GlobalMembers.bActivityCallRunning = false;
    GlobalMembers.bStartingCall = false;
    if (strNombreBoton.equals("Asistencia")) {
      MainActivity.CallOrHangUp(Enums.Calls.AssistanceCallAndMessage);
    } else if (strNombreBoton.equals("Emergencia")) {
      MainActivity.CallOrHangUp(Enums.Calls.EmergencyCallAndMessage);
    } else if (strNombreBoton.equals("Agendamiento")) {
      MainActivity.CallOrHangUp(Enums.Calls.AgendamientoCallAndMessage);
    } else {
      MainActivity.CallOrHangUp(Enums.Calls.AssistanceCallAndMessage);
    } 
    setResult(-1, this.intent);
    finish();
  }
  
  public static void sendCallStatusRecived(int paramInt1, int paramInt2) {
    if (strNombreBoton.equals("Asistencia")) {
      _iCallStatus = 2;
      _iCallIndex = 1;
    } else if (strNombreBoton.equals("Emergencia")) {
      _iCallStatus = paramInt1;
      _iCallIndex = paramInt2;
    } else if (strNombreBoton.equals("Agendamiento")) {
      _iCallStatus = paramInt1;
      _iCallIndex = paramInt2;
    } else {
      _iCallStatus = paramInt1;
      _iCallIndex = paramInt2;
    } 
    Intent intent = new Intent("com.roadtrack.onstar.BO.NEW_CALL_STATUS_RESPONSE_OBTAINED_EVENT");
    mContext.sendBroadcast(intent);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427394);
    this.timerValue = (TextView)findViewById(2131297112);
    this.txtCallButton = (TextView)findViewById(2131297194);
    this.imgButton = (ImageView)findViewById(2131296625);
    Drawable drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_call_white_48dp, 2131165449);
    this.imgButton.setImageDrawable(drawable);
    this.imgButtonEndCall = (ImageView)findViewById(2131296414);
    drawable = Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_call_end_white_48dp, 2131165448);
    this.imgButtonEndCall.setImageDrawable(drawable);
    this.txtCallButton.setTextColor(-1);
    this.imgButton.setOnClickListener(this.boton);
    this.imgButtonEndCall.setOnClickListener(this.boton);
    this.imgButtonEndCall.setEnabled(false);
    GlobalMembers.bLlamadaFinalizada = false;
    mContext = getApplicationContext();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("com.roadtrack.onstar.BO.NEW_CALL_STATUS_RESPONSE_OBTAINED_EVENT");
    registerReceiver(this.mReceiver, intentFilter);
    this.intent = getIntent();
    this.intent.getExtras();
    strNombreBoton = this.intent.getStringExtra("Boton");
    this.txtCallButton.setText(strNombreBoton);
    (new Thread(new TimerThread())).start();
    GlobalMembers.bActivityCallRunning = true;
  }
  
  protected void onDestroy() {
    super.onDestroy();
    unregisterReceiver(this.mReceiver);
  }
  
  protected void onStart() {
    super.onStart();
  }
  
  class TimerThread implements Runnable {
    final ActivityCall this$0;
    
    public void run() {
      try {
        Thread.sleep(30000L);
      } catch (InterruptedException interruptedException) {
        Utilities.escribeArchivo("ActivityCall", "Error: runnable", interruptedException.getMessage());
      } 
      ActivityCall.this.runOnUiThread(new Runnable() {
            final ActivityCall.TimerThread this$1;
            
            public void run() {
              Thread thread = Thread.currentThread();
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(null.class.getSimpleName());
              stringBuilder.append(": ");
              stringBuilder.append(Thread.currentThread().getName());
              thread.setName(stringBuilder.toString());
              if (!ActivityCall.this.bComandoRecibido)
                ActivityCall.this.finalizar(); 
            }
          });
    }
  }
  
  class null implements Runnable {
    final ActivityCall.TimerThread this$1;
    
    public void run() {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(null.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      if (!ActivityCall.this.bComandoRecibido)
        ActivityCall.this.finalizar(); 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/dialogs/ActivityCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */