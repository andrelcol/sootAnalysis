package com.roadtrack.onstar.adapter;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.Utilities;

@TargetApi(11)
public class DialogAlert extends DialogFragment implements Parcelable {
  public static final Parcelable.Creator<DialogAlert> CREATOR = new Parcelable.Creator<DialogAlert>() {
      public DialogAlert createFromParcel(Parcel param1Parcel) {
        DialogAlert dialogAlert = new DialogAlert();
        DialogAlert.access$002(dialogAlert, (Dialog)param1Parcel.readValue(DialogAlert.class.getClassLoader()));
        return dialogAlert;
      }
      
      public DialogAlert[] newArray(int param1Int) {
        return new DialogAlert[param1Int];
      }
    };
  
  private static boolean cancelable;
  
  private static Context contex;
  
  public static DialogAlertListener listener;
  
  private static String title;
  
  private static View view;
  
  private boolean back = false;
  
  private boolean buttonShowing = false;
  
  private Dialog dialog;
  
  private Dialog CreateDialog_Inner() {
    view = View.inflate(contex, 2131427470, null);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    ((TextView)view.findViewById(2131297124)).setText(Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.global_popup_lbl_aviso_1, 2131689955));
    ((TextView)view.findViewById(2131296478)).setText(Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.salir_popup_lbl_cerrarsesion_2, 2131690353));
    Button button1 = (Button)view.findViewById(2131296394);
    button1.setOnClickListener(new View.OnClickListener() {
          final DialogAlert this$0;
          
          public void onClick(View param1View) {
            DialogAlert.this.dismisDialog();
          }
        });
    Button button2 = (Button)view.findViewById(2131296321);
    button2.setText(Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946));
    button2.setOnClickListener(new View.OnClickListener() {
          final DialogAlert this$0;
          
          public void onClick(View param1View) {
            DialogAlert.listener.onAlertClick();
            DialogAlert.this.back;
            DialogAlert.this.dismisDialog();
          }
        });
    if (this.buttonShowing) {
      button1.setVisibility(0);
      button1.setText(Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.global_popup_btn_no_1, 2131689949));
      button2.setText(Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.global_popup_btn_si_1, 2131689952));
    } else {
      button1.setVisibility(8);
    } 
    Dialog dialog = new Dialog(contex, 16973840);
    dialog.setTitle(title);
    dialog.setContentView(view);
    dialog.setCancelable(cancelable);
    Window window = dialog.getWindow();
    window.setFlags(32, 32);
    window.clearFlags(2);
    return dialog;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dismisDialog() {
    this.dialog.dismiss();
  }
  
  public void onCancel(DialogInterface paramDialogInterface) {
    listener.onCancelListener();
  }
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    this.dialog = CreateDialog_Inner();
    return this.dialog;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    try {
      paramParcel.writeValue(this.dialog);
    } catch (Exception exception) {
      Utilities.escribeArchivo("DialogAlert", "writeToParcel", exception.getMessage());
    } 
  }
  
  public static interface DialogAlertListener {
    void onAlertClick();
    
    void onCancelListener();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/DialogAlert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */