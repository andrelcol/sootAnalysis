package com.roadtrack.onstar.BO;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Message;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.utils.Utilities;

public class StateBTReceiver extends BroadcastReceiver {
  private BluetoothDevice mConnectedHeadset;
  
  private CountDownTimer mCountDown = new CountDownTimer(3000L, 1000L) {
      final StateBTReceiver this$0;
      
      public void onFinish() {
        StateBTReceiver.access$002(StateBTReceiver.this, false);
        GlobalMembers.nameDevice = StateBTReceiver.this.mConnectedHeadset.getName();
        StateBTReceiver.this.sendValidDevice();
      }
      
      @TargetApi(11)
      public void onTick(long param1Long) {}
    };
  
  private boolean mIsCountDownOn;
  
  private void BroadcastHeadset(Context paramContext, Intent paramIntent) {
    this.mConnectedHeadset = (BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
    BluetoothClass bluetoothClass = this.mConnectedHeadset.getBluetoothClass();
    if (bluetoothClass != null) {
      int i = bluetoothClass.getDeviceClass();
      if (i == 1032 || i == 1028) {
        this.mIsCountDownOn = true;
        this.mCountDown.start();
      } 
    } 
  }
  
  private void sendValidDevice() {
    if (ManagerBluetooth.isValidDeviceName(GlobalMembers.nameDevice).booleanValue())
      if (MainActivity.btHandlerChangeConection != null) {
        Message message = new Message();
        message.obj = "setinBackgroud";
        MainActivity.btHandlerChangeConection.sendMessage(message);
      } else if (((Boolean)PreferenceRT.GetValuePreference(Enums.SettingsPreference.openAppwBT, GlobalMembers.NoneBoolean, GlobalMembers.contexGlobal)).booleanValue()) {
        MainActivity.setLauncherBT(true);
        Intent intent = new Intent(GlobalMembers.contexGlobal, MainActivity.class);
        intent.addFlags(268435456);
        GlobalMembers.contexGlobal.startActivity(intent);
      }  
  }
  
  @SuppressLint({"NewApi"})
  public void onReceive(Context paramContext, Intent paramIntent) {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/BO/StateBTReceiver}} */
    try {
      String str = paramIntent.getAction();
      BluetoothDevice bluetoothDevice = (BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
      if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(str)) {
        bluetoothDevice.getBondState();
      } else if (!"android.bluetooth.device.action.FOUND".equals(str)) {
        if (str.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
          BroadcastHeadset(paramContext, paramIntent);
          GlobalMembers.DeviceConectedReceiverStatus = true;
        } else if (!"android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED".equals(str) && "android.bluetooth.device.action.ACL_DISCONNECTED".equals(str)) {
          if (this.mIsCountDownOn) {
            this.mIsCountDownOn = false;
            this.mCountDown.cancel();
          } 
          GlobalMembers.DeviceConectedReceiverStatus = false;
        } 
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("StateBTReceiver", "Error: onReceive.stateBTReceiver", exception.getMessage());
    } finally {}
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/BO/StateBTReceiver}} */
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/StateBTReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */