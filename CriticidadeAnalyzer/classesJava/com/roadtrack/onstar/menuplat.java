package com.roadtrack.onstar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.roadtrack.onstar.BO.BluetoothServiceRT;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.adapter.optionMenuPlatinumAdapter;
import com.roadtrack.onstar.platinum.MultiModalHMI;
import com.roadtrack.onstar.utils.Utilities;

public class menuplat extends Activity {
  private Button btnBack;
  
  private Button btnClose;
  
  private TextView changecolor;
  
  private TextView changecolor1;
  
  private GridView gvDatos;
  
  Handler handler;
  
  private ListView lvDatos;
  
  private int morientacion;
  
  private TextView txtTitle;
  
  private optionMenuPlatinumAdapter CreateMenuListData() {
    optionMenuPlatinumAdapter optionMenuPlatinumAdapter = new optionMenuPlatinumAdapter(GlobalMembers.contexGlobal, GlobalMembers.menuPlatinum.getOptionsMenu());
    optionMenuPlatinumAdapter.notifyDataSetChanged();
    GlobalMembers.bmenuitemport = false;
    return optionMenuPlatinumAdapter;
  }
  
  private void refreshlvdatos() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Titulo en refresh");
    stringBuilder.append(GlobalMembers.str555tmp);
    Utilities.escribeArchivo("menuplat", "HMI", stringBuilder.toString());
    this.txtTitle.setText(GlobalMembers.str555tmp);
    this.lvDatos.setAdapter((ListAdapter)CreateMenuListData());
    this.gvDatos.setAdapter((ListAdapter)CreateMenuListData());
  }
  
  private void sendingToPlatinum(int paramInt) {
    boolean bool;
    BluetoothServiceRT bluetoothServiceRT = MainActivity.mChatService;
    if (bluetoothServiceRT != null && bluetoothServiceRT.getState() == 3) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool)
      MainActivity.getmNPlatinum().optionSelected(paramInt); 
  }
  
  public static void setCloseMenuListener(CloseMenuPlat paramCloseMenuPlat) {
    GlobalMembers.ListenerCloseMenu = paramCloseMenuPlat;
  }
  
  public static void setCustomEventListener(RefreshUIListener paramRefreshUIListener) {
    GlobalMembers.ListenerRefreshUI = paramRefreshUIListener;
  }
  
  public static void setDatosNavegacionObtenidos(On555ListenerFinalizar paramOn555ListenerFinalizar) {
    GlobalMembers.finalizarObtenerDatosNavegacionHMI = paramOn555ListenerFinalizar;
  }
  
  public static void setObtenerDatosNavegacion(On555ListenerIniciar paramOn555ListenerIniciar) {
    GlobalMembers.iniciarObtenerDatosNavegacionHMI = paramOn555ListenerIniciar;
  }
  
  public static void setUpdateTitle(UpdateTitle paramUpdateTitle) {
    GlobalMembers.ListenerUpdateTitle = paramUpdateTitle;
  }
  
  private void startTimeConsumingTask() {
    (new Thread() {
        final menuplat this$0;
        
        public void run() {
          for (byte b = 0; b < 'ߐ'; b++);
          menuplat.this.handler.post(new Runnable() {
                final menuplat.null this$1;
                
                public void run() {
                  menuplat.this.refreshlvdatos();
                }
              });
        }
      }).start();
  }
  
  public void launchRingDialog(View paramView) {
    final ProgressDialog ringProgressDialog = ProgressDialog.show((Context)this, "Please wait ...", "Waiting for menu ...", true);
    progressDialog.setCancelable(true);
    (new Thread(new Runnable() {
          final menuplat this$0;
          
          final ProgressDialog val$ringProgressDialog;
          
          public void run() {
            try {
              Thread.sleep(3000L);
            } catch (Exception exception) {
              Utilities.escribeArchivo("menuplat", "Error: timer", exception.toString());
            } 
            ringProgressDialog.dismiss();
            menuplat.this.refreshlvdatos();
          }
        })).start();
  }
  
  public void onBackPressed() {
    MultiModalHMI.getInstance().closeMenuHMI(1);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2) {
      GlobalMembers.boolorientacion = true;
      finish();
    } 
  }
  
  @SuppressLint({"NewApi"})
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(262144, 262144);
    setContentView(2131427450);
    setFinishOnTouchOutside(false);
    this.morientacion = (GlobalMembers.globalActivity.getResources().getConfiguration()).orientation;
    this.handler = new Handler();
    startTimeConsumingTask();
    this.lvDatos = (ListView)findViewById(2131296294);
    this.gvDatos = (GridView)findViewById(2131296293);
    this.btnClose = (Button)findViewById(2131296402);
    this.txtTitle = (TextView)findViewById(2131297191);
    ProgressBar progressBar = (ProgressBar)findViewById(2131296951);
    String str = GlobalMembers.menuPlatinum.getTitle();
    if (str != null && str.contains("0;"))
      str.replace("0;", ""); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Titulo en oncreate: ");
    stringBuilder.append(str);
    Utilities.escribeArchivo("menuplat", "HMI", stringBuilder.toString());
    this.txtTitle.setText(str);
    this.btnBack = (Button)findViewById(2131296392);
    this.btnBack.setOnClickListener(new View.OnClickListener() {
          final menuplat this$0;
          
          public void onClick(View param1View) {
            String str = GlobalMembers.menuPlatinum.getTitle();
            if (str != null && str.contains("0;"))
              str.replace("0;", ""); 
            str = GlobalMembers.str555tmp;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Titulo en onclick: ");
            stringBuilder.append(str);
            Utilities.escribeArchivo("menuplat", "HMI", stringBuilder.toString());
            int i = GlobalMembers.menu_level;
            if (i > 0)
              GlobalMembers.menu_level = i - 1; 
            Utilities.escribeArchivo("menuplat", "HMI", "Menu Level: ");
            menuplat.this.txtTitle.setText(str);
            MultiModalHMI.getInstance().closeMenuHMI(1);
          }
        });
    this.btnClose.setOnClickListener(new View.OnClickListener() {
          final menuplat this$0;
          
          public void onClick(View param1View) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Titulo en onclickclose: ");
            stringBuilder.append(GlobalMembers.menuPlatinum.getTitle());
            Utilities.escribeArchivo("menuplat", "HMI", stringBuilder.toString());
            menuplat.this.txtTitle.setText(GlobalMembers.menuPlatinum.getTitle());
            MultiModalHMI.getInstance().closeMenuHMI(2);
            GlobalMembers.intmenuplat = 0;
            GlobalMembers.bshowmenuland = false;
            GlobalMembers.bMostrandoMenuHMI = false;
            menuplat.this.finish();
          }
        });
    this.lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          final menuplat this$0;
          
          @SuppressLint({"ResourceAsColor"})
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            if (!GlobalMembers.bmenuitemport) {
              GlobalMembers.bmenuitemport = true;
              int i = param1Int - param1AdapterView.getFirstVisiblePosition();
              View view = menuplat.this.lvDatos.getChildAt(i);
              menuplat.access$202(menuplat.this, (TextView)view.findViewById(2131297175));
              menuplat.this.changecolor.setTextColor(-1);
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Titulo en onitemclick: ");
              stringBuilder.append(menuplat.this.changecolor.getText());
              Utilities.escribeArchivo("menuplat", "HMI", stringBuilder.toString());
              GlobalMembers.menu_level++;
              stringBuilder = new StringBuilder();
              stringBuilder.append("Menu Level: ");
              stringBuilder.append(GlobalMembers.menu_level);
              Utilities.escribeArchivo("menuplat", "HMI", stringBuilder.toString());
              menuplat.this.txtTitle.setText(menuplat.this.changecolor.getText());
              Drawable drawable = Utilities.getDrawableFromConfigList((Context)menuplat.this, DrawableResourcesVO.container_ivr_blue, 2131165391);
              menuplat.this.lvDatos.getChildAt(i).setBackground(drawable);
              menuplat.this.sendingToPlatinum(param1Int);
            } else {
              Toast.makeText(GlobalMembers.contexGlobal, "Menu Presionado ", 0).show();
            } 
          }
        });
    this.gvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          final menuplat this$0;
          
          @SuppressLint({"ResourceAsColor"})
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            if (!GlobalMembers.bmenuitemport) {
              GlobalMembers.bmenuitemport = true;
              int i = param1Int - param1AdapterView.getFirstVisiblePosition();
              View view = menuplat.this.gvDatos.getChildAt(i);
              menuplat.access$502(menuplat.this, (TextView)view.findViewById(2131297175));
              menuplat.this.changecolor1.setTextColor(-1);
              menuplat.this.txtTitle.setTextColor(-16777216);
              menuplat.this.txtTitle.setTypeface(null, 1);
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Titulo en onitemclick2: ");
              stringBuilder.append(menuplat.this.changecolor1.getText());
              Utilities.escribeArchivo("menuplat", "HMI", stringBuilder.toString());
              menuplat.this.txtTitle.setText(menuplat.this.changecolor1.getText());
              Drawable drawable = Utilities.getDrawableFromConfigList((Context)menuplat.this, DrawableResourcesVO.container_ivr_blue, 2131165391);
              menuplat.this.gvDatos.getChildAt(i).setBackground(drawable);
              menuplat.this.sendingToPlatinum(param1Int);
            } else {
              Toast.makeText(GlobalMembers.contexGlobal, "Menu Presionado ", 0).show();
            } 
          }
        });
    setCustomEventListener(new RefreshUIListener() {
          final menuplat this$0;
          
          public void onEvent() {
            menuplat.this.refreshlvdatos();
          }
        });
    setCloseMenuListener(new CloseMenuPlat() {
          final menuplat this$0;
          
          public void onevent() {
            MultiModalHMI.getInstance().closeMenuHMI(2);
            GlobalMembers.intmenuplat = 0;
            menuplat.this.finish();
          }
        });
    setUpdateTitle(new UpdateTitle() {
          final menuplat this$0;
          
          public void onevent() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Titulo en event: ");
            stringBuilder.append(GlobalMembers.str555tmp);
            Utilities.escribeArchivo("menuplat", "HMI", stringBuilder.toString());
            menuplat.this.txtTitle.setText(GlobalMembers.str555tmp);
            menuplat.this.txtTitle.setTextColor(-16777216);
            menuplat.this.txtTitle.setTypeface(null, 1);
          }
        });
    setObtenerDatosNavegacion(new On555ListenerIniciar(this) {
        
        });
    setDatosNavegacionObtenidos(new On555ListenerFinalizar(this) {
        
        });
    GlobalMembers.EstadoAppGlobal = 1;
  }
  
  public void onDestroy() {
    super.onDestroy();
    Debug.stopMethodTracing();
    Utilities.stopWakeDevice();
    GlobalMembers.EstadoAppGlobal = 0;
  }
  
  public void onResume() {
    super.onResume();
    if (this.morientacion == 1) {
      this.lvDatos.setAdapter((ListAdapter)CreateMenuListData());
      this.lvDatos.setVisibility(0);
      this.gvDatos.setVisibility(8);
    } else {
      this.gvDatos.setAdapter((ListAdapter)CreateMenuListData());
      this.gvDatos.setVisibility(0);
      this.lvDatos.setVisibility(8);
    } 
    if (GlobalMembers.menuPlatinum.getTypeControl() != null)
      Integer.parseInt(GlobalMembers.menuPlatinum.getTypeControl().toString()); 
    Utilities.wakeDevice(GlobalMembers.contexGlobal, "RTMobile_HMI");
  }
  
  public void onStop() {
    super.onStop();
    Debug.stopMethodTracing();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return (4 == paramMotionEvent.getAction()) ? true : super.onTouchEvent(paramMotionEvent);
  }
  
  public static interface CloseMenuPlat {
    void onevent();
  }
  
  public static interface On555ListenerFinalizar {}
  
  public static interface On555ListenerIniciar {}
  
  public static interface RefreshUIListener {
    void onEvent();
  }
  
  public static interface UpdateTitle {
    void onevent();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/menuplat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */