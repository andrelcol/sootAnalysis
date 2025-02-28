package com.roadtrack.onstar;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.roadtrack.onstar.BO.BluetoothServiceRT;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.adapter.optionMenuPlatinumAdapter;
import com.roadtrack.onstar.platinum.MultiModalHMI;
import com.roadtrack.onstar.utils.Utilities;

public class HmiMenu extends Activity {
  private ProgressBar barStartFragment;
  
  private Button btnClose;
  
  private OptionMenuPLAsyncTask controlViewsHist = null;
  
  private int genericDisplayType;
  
  private LinearLayout layButtons;
  
  private ListView lvDatos;
  
  private Button menuButton1;
  
  private Button menuButton2;
  
  private TextView txtTitle;
  
  View vista;
  
  private optionMenuPlatinumAdapter CreateMenuListData() {
    optionMenuPlatinumAdapter optionMenuPlatinumAdapter = new optionMenuPlatinumAdapter((Context)this, GlobalMembers.menuPlatinum.getOptionsMenu());
    optionMenuPlatinumAdapter.notifyDataSetChanged();
    return optionMenuPlatinumAdapter;
  }
  
  private void clossingMenu(int paramInt) {
    MainActivity.getmNPlatinum();
    if (paramInt == 5) {
      MultiModalHMI.getInstance().closeMenuHMI(1, Enums$MessageOrigin.TBD.GetOpCode());
    } else {
      MultiModalHMI.getInstance().closeMenuHMI(2);
    } 
    finish();
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
  
  private void setButtonsText(int paramInt) {
    String str2;
    this.menuButton1 = (Button)this.vista.findViewById(2131296408);
    this.menuButton2 = (Button)this.vista.findViewById(2131296409);
    String str1 = "No options";
    if (paramInt == 1) {
      str1 = GlobalMembers.menuPlatinum.getOptionsMenu()[0].toString();
      str2 = GlobalMembers.menuPlatinum.getOptionsMenu()[1].toString();
    } else if (paramInt == 3) {
      str1 = GlobalMembers.menuPlatinum.getOptionsMenu()[0].toString();
      str2 = "";
    } else {
      str2 = "No options";
    } 
    this.menuButton1.setText(str1);
    if (paramInt == 3) {
      this.menuButton2.setVisibility(8);
    } else {
      this.menuButton2.setText(str2);
    } 
    this.menuButton1.setOnClickListener(new View.OnClickListener() {
          final HmiMenu this$0;
          
          public void onClick(View param1View) {
            HmiMenu.this.sendingToPlatinum(0);
          }
        });
    this.menuButton2.setOnClickListener(new View.OnClickListener() {
          final HmiMenu this$0;
          
          public void onClick(View param1View) {
            HmiMenu.this.sendingToPlatinum(1);
          }
        });
  }
  
  public void RefreshCallBack() {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/HmiMenu}} */
    try {
      this.lvDatos.setAdapter((ListAdapter)CreateMenuListData());
    } catch (Exception exception) {
      Utilities.escribeArchivo("HmiMenu", "Error: RefreshCallBack", exception.getMessage());
    } finally {
      Exception exception;
    } 
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/HmiMenu}} */
  }
  
  public void onBackPressed() {
    MainActivity.getmNPlatinum();
    int i = Integer.parseInt(GlobalMembers.menuPlatinum.getTypeControl().toString());
    this.genericDisplayType = i;
    if (i == 5) {
      MultiModalHMI.getInstance().closeMenuHMI(1, Enums$MessageOrigin.TBD.GetOpCode());
    } else {
      MultiModalHMI.getInstance().closeMenuHMI(1);
    } 
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2131427451);
    this.vista = findViewById(2131427451);
    this.barStartFragment = (ProgressBar)findViewById(2131296959);
    this.txtTitle = (TextView)findViewById(2131297191);
    this.lvDatos = (ListView)findViewById(2131296298);
    this.layButtons = (LinearLayout)findViewById(2131296297);
    this.btnClose = (Button)findViewById(2131296395);
  }
  
  public void onDestroy() {
    super.onDestroy();
    clossingMenu(this.genericDisplayType);
    Debug.stopMethodTracing();
    Utilities.stopWakeDevice();
  }
  
  public void onResume() {
    super.onResume();
    Utilities.wakeDevice(GlobalMembers.contexGlobal, "RTMobile_HMI");
    OptionMenuPLAsyncTask optionMenuPLAsyncTask = this.controlViewsHist;
    if (optionMenuPLAsyncTask == null) {
      this.controlViewsHist = new OptionMenuPLAsyncTask(this);
      this.controlViewsHist.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[0]);
    } else {
      optionMenuPLAsyncTask.attach(this);
      if (this.controlViewsHist.getProgress() < 100) {
        this.barStartFragment.setVisibility(0);
      } else {
        this.barStartFragment.setVisibility(8);
        RefreshCallBack();
        this.controlViewsHist.detach();
      } 
    } 
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
  }
  
  public void onStop() {
    super.onStop();
    Debug.stopMethodTracing();
  }
  
  private class OptionMenuPLAsyncTask extends AsyncTask<String, Void, String> {
    optionMenuPlatinumAdapter adapter;
    
    int displayType = 0;
    
    int progress = 0;
    
    String response = "";
    
    final HmiMenu this$0;
    
    public OptionMenuPLAsyncTask(HmiMenu param1HmiMenu1) {
      attach(param1HmiMenu1);
    }
    
    void attach(HmiMenu param1HmiMenu) {}
    
    void detach() {}
    
    protected String doInBackground(String... param1VarArgs) {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(OptionMenuPLAsyncTask.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      try {
        this.displayType = Integer.parseInt(GlobalMembers.menuPlatinum.getTypeControl().toString());
        HmiMenu.access$102(HmiMenu.this, this.displayType);
        if (this.displayType == 2)
          this.adapter = HmiMenu.this.CreateMenuListData(); 
        this.response = "Success";
      } catch (Exception exception) {
        this.response = "Failed";
        Utilities.escribeArchivo("HmiMenu", "Error: doInBackground method", exception.getMessage());
      } 
      return this.response;
    }
    
    int getProgress() {
      return this.progress;
    }
    
    protected void onPostExecute(String param1String) {
      HmiMenu.this.btnClose.setOnClickListener(new View.OnClickListener() {
            final HmiMenu.OptionMenuPLAsyncTask this$1;
            
            public void onClick(View param2View) {
              HmiMenu.OptionMenuPLAsyncTask optionMenuPLAsyncTask = HmiMenu.OptionMenuPLAsyncTask.this;
              HmiMenu.this.clossingMenu(optionMenuPLAsyncTask.displayType);
            }
          });
      int i = this.displayType;
      if (i == 2) {
        param1String = GlobalMembers.menuPlatinum.getTitle();
        HmiMenu.this.lvDatos.setVisibility(0);
        HmiMenu.this.lvDatos.setAdapter((ListAdapter)this.adapter);
        HmiMenu.this.layButtons.setVisibility(8);
        HmiMenu.this.lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              final HmiMenu.OptionMenuPLAsyncTask this$1;
              
              public void onItemClick(AdapterView<?> param2AdapterView, View param2View, int param2Int, long param2Long) {
                HmiMenu.this.sendingToPlatinum(param2Int);
              }
            });
      } else if (i == 5) {
        HmiMenu.this.lvDatos.setVisibility(8);
        HmiMenu.this.layButtons.setVisibility(8);
        param1String = GlobalMembers.menuPlatinum.getTitle();
      } else {
        param1String = GlobalMembers.menuPlatinum.getTitle();
        HmiMenu.this.lvDatos.setVisibility(8);
        HmiMenu.this.layButtons.setVisibility(0);
        HmiMenu.this.setButtonsText(this.displayType);
      } 
      if (param1String != null && param1String.contains("0;"))
        param1String.replace("0;", ""); 
      HmiMenu.access$102(HmiMenu.this, this.displayType);
      HmiMenu.this.txtTitle.setText(param1String);
      HmiMenu.this.barStartFragment.setVisibility(8);
      this.progress = 100;
    }
    
    protected void onPreExecute() {
      HmiMenu.this.barStartFragment.setVisibility(0);
    }
  }
  
  class null implements View.OnClickListener {
    final HmiMenu.OptionMenuPLAsyncTask this$1;
    
    public void onClick(View param1View) {
      HmiMenu.OptionMenuPLAsyncTask optionMenuPLAsyncTask = this.this$1;
      HmiMenu.this.clossingMenu(optionMenuPLAsyncTask.displayType);
    }
  }
  
  class null implements AdapterView.OnItemClickListener {
    final HmiMenu.OptionMenuPLAsyncTask this$1;
    
    public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      HmiMenu.this.sendingToPlatinum(param1Int);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/HmiMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */