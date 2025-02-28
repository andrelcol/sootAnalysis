package com.roadtrack.onstar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.roadtrack.onstar.BO.BluetoothServiceRT;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.adapter.optionMenuPlatinumAdapter;
import com.roadtrack.onstar.platinum.MultiModalHMI;
import com.roadtrack.onstar.utils.Utilities;

@SuppressLint({"NewApi"})
public class menuPlatinum extends Fragment {
  private static Typeface tf_LouisRegular;
  
  private ProgressBar barStartFragment;
  
  private Button btnBackMenu;
  
  private Button btnClose;
  
  private Context context;
  
  private OptionMenuPLAsyncTask controlViewsHist = null;
  
  private LinearLayout layButtons;
  
  private ListView lvDatos;
  
  private Button menuButton1;
  
  private Button menuButton2;
  
  private TextView txtTitle;
  
  private View vista;
  
  public menuPlatinum() {
    new Handler() {
        final menuPlatinum this$0;
        
        public void handleMessage(Message param1Message) {
          menuPlatinum.this.RefreshCallBack();
        }
      };
  }
  
  @SuppressLint({"NewApi"})
  private optionMenuPlatinumAdapter CreateMenuListData() {
    optionMenuPlatinumAdapter optionMenuPlatinumAdapter = new optionMenuPlatinumAdapter((Context)getActivity(), GlobalMembers.menuPlatinum.getOptionsMenu());
    optionMenuPlatinumAdapter.notifyDataSetChanged();
    GlobalMembers.bmenuitempressed = false;
    return optionMenuPlatinumAdapter;
  }
  
  private void clossingMenu(int paramInt) {
    MainActivity.getmNPlatinum();
    if (paramInt == 5) {
      MultiModalHMI.getInstance().closeMenuHMI(1, Enums$MessageOrigin.TBD.GetOpCode());
    } else {
      MultiModalHMI.getInstance().closeMenuHMI(2);
    } 
    ((MainActivity)GlobalMembers.contexGlobal).closeHmiMenu();
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
          final menuPlatinum this$0;
          
          public void onClick(View param1View) {
            menuPlatinum.this.sendingToPlatinum(0);
          }
        });
    this.menuButton2.setOnClickListener(new View.OnClickListener() {
          final menuPlatinum this$0;
          
          public void onClick(View param1View) {
            menuPlatinum.this.sendingToPlatinum(1);
          }
        });
  }
  
  public static void setCustomEventListener(On555Listener paramOn555Listener) {
    GlobalMembers.Listenerg = paramOn555Listener;
  }
  
  public void RefreshCallBack() {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/menuPlatinum}} */
    try {
      this.lvDatos.setAdapter((ListAdapter)CreateMenuListData());
      GlobalMembers.adapternav = CreateMenuListData();
    } catch (Exception exception) {
      Utilities.escribeArchivo("menuPlatinum", "Error: RefreshCallBack", exception.getMessage());
    } finally {
      Exception exception;
    } 
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/menuPlatinum}} */
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    this.context = GlobalMembers.contexGlobal;
    onstarApplication onstarApplication = (onstarApplication)getActivity().getApplicationContext();
    this.vista = paramLayoutInflater.inflate(2131427451, paramViewGroup, false);
    this.barStartFragment = (ProgressBar)this.vista.findViewById(2131296959);
    this.txtTitle = (TextView)this.vista.findViewById(2131297191);
    this.lvDatos = (ListView)this.vista.findViewById(2131296298);
    this.layButtons = (LinearLayout)this.vista.findViewById(2131296297);
    this.btnClose = (Button)this.vista.findViewById(2131296395);
    this.btnBackMenu = (Button)this.vista.findViewById(2131296392);
    tf_LouisRegular = onstarApplication.getTypeface(this.context, onstarApplication.fontPathLouisRegular);
    this.btnClose.setTypeface(tf_LouisRegular);
    this.btnBackMenu.setTypeface(tf_LouisRegular);
    GlobalMembers.EstadoAppGlobal = 5;
    this.vista.setVisibility(8);
    setCustomEventListener(new On555Listener() {
          final menuPlatinum this$0;
          
          public void onEvent() {
            menuPlatinum.this.txtTitle.setText(GlobalMembers.str555tmp);
            menuPlatinum.this.txtTitle.setTextColor(-16777216);
            menuPlatinum.this.txtTitle.setTypeface(null, 1);
          }
        });
    setRetainInstance(true);
    return this.vista;
  }
  
  public void onDestroy() {
    super.onDestroy();
    Debug.stopMethodTracing();
    Utilities.stopWakeDevice();
  }
  
  public void onResume() {
    super.onResume();
    Context context = GlobalMembers.contexGlobal;
    this.context = context;
    Utilities.wakeDevice(context, "RTMobile_HMI");
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
  
  @SuppressLint({"NewApi"})
  public void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
  }
  
  public void onStop() {
    super.onStop();
    Debug.stopMethodTracing();
  }
  
  public void setUserVisibleHint(boolean paramBoolean) {
    super.setUserVisibleHint(paramBoolean);
    if (paramBoolean) {
      FragmentActivity fragmentActivity = getActivity();
      if (fragmentActivity != null)
        fragmentActivity.setRequestedOrientation(10); 
    } 
  }
  
  public static interface On555Listener {
    void onEvent();
  }
  
  private class OptionMenuPLAsyncTask extends AsyncTask<String, Void, String> {
    optionMenuPlatinumAdapter adapter;
    
    int displayType = 0;
    
    int progress = 0;
    
    String response = "";
    
    final menuPlatinum this$0;
    
    public OptionMenuPLAsyncTask(menuPlatinum param1menuPlatinum1) {
      attach(param1menuPlatinum1);
    }
    
    void attach(menuPlatinum param1menuPlatinum) {}
    
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
        if (this.displayType == 2)
          this.adapter = menuPlatinum.this.CreateMenuListData(); 
        this.response = "Success";
      } catch (Exception exception) {
        this.response = "Failed";
        Utilities.escribeArchivo("menuPlatinum", "Error: doInBackground method", exception.getMessage());
      } 
      return this.response;
    }
    
    int getProgress() {
      return this.progress;
    }
    
    protected void onPostExecute(String param1String) {
      menuPlatinum.this.btnClose.setOnClickListener(new View.OnClickListener() {
            final menuPlatinum.OptionMenuPLAsyncTask this$1;
            
            public void onClick(View param2View) {
              GlobalMembers.intmenuplat = 0;
              GlobalMembers.EstadoAppGlobal = 0;
              menuPlatinum.OptionMenuPLAsyncTask optionMenuPLAsyncTask = menuPlatinum.OptionMenuPLAsyncTask.this;
              menuPlatinum.this.clossingMenu(optionMenuPLAsyncTask.displayType);
            }
          });
      menuPlatinum.this.btnBackMenu.setOnClickListener(new View.OnClickListener(this) {
            public void onClick(View param2View) {
              MultiModalHMI.getInstance().closeMenuHMI(1);
            }
          });
      int i = this.displayType;
      if (i == 2) {
        param1String = GlobalMembers.menuPlatinum.getTitle();
        if (param1String != null && param1String.contains("0;"))
          param1String.replace("0;", ""); 
        menuPlatinum.this.txtTitle.setText(param1String);
        menuPlatinum.this.lvDatos.setVisibility(0);
        menuPlatinum.this.lvDatos.setAdapter((ListAdapter)this.adapter);
        GlobalMembers.adapternav = this.adapter;
        menuPlatinum.this.layButtons.setVisibility(8);
        menuPlatinum.this.lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              final menuPlatinum.OptionMenuPLAsyncTask this$1;
              
              @SuppressLint({"ResourceAsColor"})
              public void onItemClick(AdapterView<?> param2AdapterView, View param2View, int param2Int, long param2Long) {
                if (!GlobalMembers.bmenuitempressed) {
                  GlobalMembers.bmenuitempressed = true;
                  int i = param2AdapterView.getFirstVisiblePosition();
                  menuPlatinum.this.lvDatos.getChildAt(param2Int - i).setBackgroundColor(2131034189);
                  menuPlatinum.this.sendingToPlatinum(param2Int);
                } else {
                  Toast.makeText(GlobalMembers.contexGlobal, "Menu Presionado ", 0).show();
                } 
              }
            });
      } else if (i == 5) {
        menuPlatinum.this.lvDatos.setVisibility(8);
        menuPlatinum.this.layButtons.setVisibility(8);
        param1String = GlobalMembers.menuPlatinum.getTitle();
      } else {
        param1String = GlobalMembers.menuPlatinum.getTitle();
        menuPlatinum.this.lvDatos.setVisibility(8);
        menuPlatinum.this.layButtons.setVisibility(0);
        menuPlatinum.this.setButtonsText(this.displayType);
      } 
      if (param1String != null && param1String.contains("0;"))
        param1String.replace("0;", ""); 
      menuPlatinum.this.txtTitle.setText(param1String);
      menuPlatinum.this.barStartFragment.setVisibility(8);
      this.progress = 100;
    }
    
    protected void onPreExecute() {
      menuPlatinum menuPlatinum1 = menuPlatinum.this;
      menuPlatinum.access$102(menuPlatinum1, (ProgressBar)menuPlatinum1.vista.findViewById(2131296959));
      menuPlatinum.this.barStartFragment.setVisibility(0);
    }
  }
  
  class null implements View.OnClickListener {
    final menuPlatinum.OptionMenuPLAsyncTask this$1;
    
    public void onClick(View param1View) {
      GlobalMembers.intmenuplat = 0;
      GlobalMembers.EstadoAppGlobal = 0;
      menuPlatinum.OptionMenuPLAsyncTask optionMenuPLAsyncTask = this.this$1;
      menuPlatinum.this.clossingMenu(optionMenuPLAsyncTask.displayType);
    }
  }
  
  class null implements View.OnClickListener {
    null(menuPlatinum this$0) {}
    
    public void onClick(View param1View) {
      MultiModalHMI.getInstance().closeMenuHMI(1);
    }
  }
  
  class null implements AdapterView.OnItemClickListener {
    final menuPlatinum.OptionMenuPLAsyncTask this$1;
    
    @SuppressLint({"ResourceAsColor"})
    public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      if (!GlobalMembers.bmenuitempressed) {
        GlobalMembers.bmenuitempressed = true;
        int i = param1AdapterView.getFirstVisiblePosition();
        menuPlatinum.this.lvDatos.getChildAt(param1Int - i).setBackgroundColor(2131034189);
        menuPlatinum.this.sendingToPlatinum(param1Int);
      } else {
        Toast.makeText(GlobalMembers.contexGlobal, "Menu Presionado ", 0).show();
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/menuPlatinum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */