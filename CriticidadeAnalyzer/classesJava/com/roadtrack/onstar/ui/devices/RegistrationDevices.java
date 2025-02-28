package com.roadtrack.onstar.ui.devices;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.adapter.AdapterRegistrationDevices;
import com.roadtrack.onstar.async_tasks.tasks.DeleteDevicesTask;
import com.roadtrack.onstar.async_tasks.tasks.GetDevicesTask;
import com.roadtrack.onstar.entities.RegisterDevice;
import com.roadtrack.onstar.interfaces.DevicesInterface;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class RegistrationDevices extends Activity implements DevicesInterface {
  public static Activity _activity;
  
  public static onstarApplication application;
  
  AdapterRegistrationDevices adapter;
  
  public View.OnClickListener clickRemove = new View.OnClickListener() {
      final RegistrationDevices this$0;
      
      public void onClick(View param1View) {
        RegistrationDevices.this.confirmDelet();
      }
    };
  
  private DBFunctions dbFunctions;
  
  public AsyncTask deleteDevicesTask;
  
  public AsyncTask getDevicesTask;
  
  ArrayList<RegisterDevice> list;
  
  LinearLayout llBtnRemove;
  
  ListView lvDevices;
  
  public boolean network;
  
  private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
      final RegistrationDevices this$0;
      
      public void onReceive(Context param1Context, Intent param1Intent) {
        RegistrationDevices registrationDevices1;
        RegistrationDevices registrationDevices4 = RegistrationDevices.this;
        TextView textView = (TextView)registrationDevices4.findViewById(2131297056);
        RegistrationDevices registrationDevices3 = RegistrationDevices.this;
        registrationDevices4.network = Utilities.showNetworkServiceData(textView, (Context)registrationDevices3, new TextView[] { (TextView)registrationDevices3.findViewById(2131297163) });
        RegistrationDevices registrationDevices2 = RegistrationDevices.this;
        if (registrationDevices2.network) {
          registrationDevices2.progress.setVisibility(0);
          RegistrationDevices.this.progress.getIndeterminateDrawable().setColorFilter(RegistrationDevices.this.getResources().getColor(2131034155), PorterDuff.Mode.MULTIPLY);
          RegistrationDevices.application = (onstarApplication)RegistrationDevices.this.getApplicationContext();
          registrationDevices1 = RegistrationDevices.this;
          registrationDevices1.getDevicesTask = (new GetDevicesTask(registrationDevices1, registrationDevices1, RegistrationDevices.application.getAccountID())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
        } else {
          AsyncTask asyncTask = registrationDevices2.getDevicesTask;
          if (asyncTask != null)
            asyncTask.cancel(true); 
          asyncTask = RegistrationDevices.this.deleteDevicesTask;
          if (asyncTask != null)
            asyncTask.cancel(true); 
          DBFunctions dBFunctions = new DBFunctions((Context)registrationDevices1);
          RegistrationDevices.this.list = dBFunctions.getDeviceRegister();
          RegistrationDevices registrationDevices = RegistrationDevices.this;
          registrationDevices.adapter = new AdapterRegistrationDevices((Context)registrationDevices1, registrationDevices.list, registrationDevices.tvDelete, registrationDevices.llBtnRemove, registrationDevices.network);
          registrationDevices1 = RegistrationDevices.this;
          registrationDevices1.lvDevices.setAdapter((ListAdapter)registrationDevices1.adapter);
          RegistrationDevices.this.llBtnRemove.setClickable(false);
          RegistrationDevices.this.tvDelete.setAlpha(0.5F);
        } 
      }
    };
  
  public ProgressBar progress;
  
  TextView tvDelete;
  
  private void confirmDelet() {
    ArrayList<Boolean> arrayList = new ArrayList();
    for (byte b = 0; b < this.list.size(); b++) {
      if (((RegisterDevice)this.list.get(b)).getSelect().booleanValue())
        arrayList.add(((RegisterDevice)this.list.get(b)).getSelect()); 
    } 
    if (arrayList.size() != 0) {
      StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
      String str2 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
      String str1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.BTCancel, 2131689476);
      String[] arrayOfString = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.text_remover, 2131690418).split("\\n");
      final Dialog dialog = Utilities.simpleDialog((Context)this, null, arrayOfString[0], arrayOfString[1], true, str1, true, str2, 20.0F, 16.0F);
      ((Button)dialog.findViewById(2131296344)).setOnClickListener(new View.OnClickListener() {
            final RegistrationDevices this$0;
            
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              ArrayList<Boolean> arrayList = new ArrayList();
              Iterator<RegisterDevice> iterator = RegistrationDevices.this.list.iterator();
              while (iterator.hasNext())
                arrayList.add(((RegisterDevice)iterator.next()).getSelect()); 
              for (byte b = 0; b < arrayList.size(); b++) {
                if (((Boolean)arrayList.get(b)).booleanValue())
                  RegistrationDevices.this.deleteDevicesTask = (new DeleteDevicesTask(new DevicesInterface() {
                        final RegistrationDevices.null this$1;
                        
                        public void getResponseService(String param2String) {
                          if (param2String != null) {
                            StringTokenizer stringTokenizer = new StringTokenizer(param2String, "|");
                            ArrayList<String> arrayList = new ArrayList();
                            while (stringTokenizer.hasMoreTokens())
                              arrayList.add(stringTokenizer.nextToken()); 
                            for (byte b = 0; b < arrayList.size(); b++) {
                              if (((String)arrayList.get(b)).equalsIgnoreCase("0"))
                                RegistrationDevices.this.dbFunctions.deleteDeviceRegister(((RegisterDevice)RegistrationDevices.this.list.get(b)).getUdid()); 
                            } 
                            RegistrationDevices registrationDevices = RegistrationDevices.this;
                            registrationDevices.list = registrationDevices.dbFunctions.getDeviceRegister();
                            registrationDevices = RegistrationDevices.this;
                            registrationDevices.adapter = new AdapterRegistrationDevices((Context)RegistrationDevices._activity, registrationDevices.list, registrationDevices.tvDelete, registrationDevices.llBtnRemove, registrationDevices.network);
                            registrationDevices = RegistrationDevices.this;
                            registrationDevices.lvDevices.setAdapter((ListAdapter)registrationDevices.adapter);
                            RegistrationDevices.this.llBtnRemove.setClickable(false);
                            RegistrationDevices.this.tvDelete.setAlpha(0.5F);
                          } 
                        }
                      }RegistrationDevices.this, RegistrationDevices.application.getAccountID(), ((RegisterDevice)RegistrationDevices.this.list.get(b)).getDeviceName(), ((RegisterDevice)RegistrationDevices.this.list.get(b)).getApplicationType(), ((RegisterDevice)RegistrationDevices.this.list.get(b)).getUdid(), false)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]); 
              } 
              dialog.dismiss();
            }
          });
      ((Button)dialog.findViewById(2131296343)).setOnClickListener(new View.OnClickListener(this) {
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              dialog.dismiss();
            }
          });
      dialog.show();
    } 
  }
  
  public static void getListService() {
    Activity activity = _activity;
    (new GetDevicesTask((DevicesInterface)activity, activity, application.getAccountID())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  public void getResponseService(String paramString) {
    if (paramString != null)
      try {
        parseResultListDevices(paramString);
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error ");
        stringBuilder.append(exception);
        Utilities.escribeArchivo("ERROR", "Error", stringBuilder.toString());
      }  
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (getActionBar() != null)
      getActionBar().hide(); 
    setContentView(2131427438);
    _activity = this;
    registerReceiver(this.networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    this.dbFunctions = new DBFunctions((Context)this);
    this.progress = (ProgressBar)findViewById(2131296961);
    this.lvDevices = (ListView)findViewById(2131296867);
    this.tvDelete = (TextView)findViewById(2131297158);
    this.llBtnRemove = (LinearLayout)findViewById(2131296842);
    this.llBtnRemove.setOnClickListener(this.clickRemove);
  }
  
  protected void onDestroy() {
    super.onDestroy();
    BroadcastReceiver broadcastReceiver = this.networkChangeReceiver;
    if (broadcastReceiver != null)
      unregisterReceiver(broadcastReceiver); 
  }
  
  public void parseResultListDevices(String paramString) {
    StringTokenizer stringTokenizer = new StringTokenizer(paramString, "|");
    ArrayList<String> arrayList2 = new ArrayList();
    while (stringTokenizer.hasMoreTokens())
      arrayList2.add(stringTokenizer.nextToken()); 
    ArrayList<String> arrayList1 = new ArrayList();
    byte b;
    for (b = 0; b < arrayList2.size(); b++) {
      char[] arrayOfChar = ((String)arrayList2.get(b)).toCharArray();
      int k = arrayOfChar.length;
      int i = 0;
      int j;
      for (j = 0; i < k; j = m) {
        int m = j;
        if (arrayOfChar[i] == ',')
          m = j + 1; 
        i++;
      } 
      if (j == 2) {
        StringTokenizer stringTokenizer1 = new StringTokenizer(arrayList2.get(b), ",");
        while (stringTokenizer1.hasMoreTokens())
          arrayList1.add(stringTokenizer1.nextToken()); 
      } else {
        String[] arrayOfString = ((String)arrayList2.get(b)).split(",");
        ArrayList<String> arrayList3 = new ArrayList();
        for (i = arrayOfString.length - 1; i >= 0; i--)
          arrayList3.add(arrayOfString[i]); 
        ArrayList<String> arrayList4 = new ArrayList();
        ArrayList<String> arrayList5 = new ArrayList();
        for (i = 0; i < arrayList3.size(); i++) {
          if (i >= 2) {
            arrayList4.add(arrayList3.get(i));
          } else {
            arrayList5.add(arrayList3.get(i));
          } 
        } 
        i = arrayList4.size() - 1;
        String str = "";
        while (i >= 0) {
          if (i == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(arrayList4.get(i));
            str = stringBuilder.toString();
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(arrayList4.get(i));
            stringBuilder.append(",");
            str = stringBuilder.toString();
          } 
          i--;
        } 
        arrayList1.add(str);
        for (i = arrayList5.size() - 1; i >= 0; i--)
          arrayList1.add(arrayList5.get(i)); 
      } 
    } 
    arrayList2 = new ArrayList<String>();
    if (arrayList1.size() > 1) {
      int i = 0;
      byte b1;
      for (b1 = 0; i < arrayList1.size(); b1 = b) {
        int j = i;
        b = b1;
        if (!((String)arrayList1.get(0)).equalsIgnoreCase("0"))
          if (b1 < 3) {
            RegisterDevice registerDevice = new RegisterDevice();
            registerDevice.setDeviceName(arrayList1.get(i));
            registerDevice.setApplicationType(arrayList1.get(i + 1));
            registerDevice.setUdid(arrayList1.get(i + 2));
            arrayList2.add(registerDevice);
            b = 3;
            j = i;
          } else {
            j = i + 1;
            b = 0;
          }  
        i = j + 1;
      } 
      saveVehicle((ArrayList)arrayList2);
    } 
  }
  
  public void saveVehicle(ArrayList<RegisterDevice> paramArrayList) {
    this.dbFunctions.deleteAllDevices();
    for (byte b = 0; b < paramArrayList.size(); b++)
      this.dbFunctions.insertOrUpdateDeviceRegister(((RegisterDevice)paramArrayList.get(b)).getUdid(), ((RegisterDevice)paramArrayList.get(b)).getDeviceName(), ((RegisterDevice)paramArrayList.get(b)).getApplicationType()); 
    this.progress.setVisibility(8);
    this.list = this.dbFunctions.getDeviceRegister();
    this.adapter = new AdapterRegistrationDevices((Context)_activity, this.list, this.tvDelete, this.llBtnRemove, this.network);
    this.lvDevices.setAdapter((ListAdapter)this.adapter);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/devices/RegistrationDevices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */