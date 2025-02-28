package com.roadtrack.onstar.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.analytics.AnalyticsEventModel;
import com.roadtrack.onstar.analytics.AnalyticsHelper;
import com.roadtrack.onstar.analytics.AnalyticsHelperFactory;
import com.roadtrack.onstar.analytics.AnalyticsTypes;
import com.roadtrack.onstar.utils.DrawableUtils;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.Utilities;

@SuppressLint({"NewApi"})
public class DialogMenu extends DialogFragment implements Parcelable {
  public static final Parcelable.Creator<DialogMenu> CREATOR = new Parcelable.Creator<DialogMenu>() {
      public DialogMenu createFromParcel(Parcel param1Parcel) {
        return null;
      }
      
      public DialogMenu[] newArray(int param1Int) {
        return new DialogMenu[param1Int];
      }
    };
  
  private static boolean cancelable = false;
  
  private static Context context;
  
  private static Drawable imgDrawable;
  
  public static DialogMenuListener listener;
  
  private static String message;
  
  private static String msgButtonNOK;
  
  private static String msgButtonOK;
  
  private static boolean showButtonCancel = false;
  
  private static boolean showButtonOK = true;
  
  private static String title;
  
  private static View view;
  
  private AnalyticsHelper analyticsHelper;
  
  private Dialog dialog;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private void analyticsEvent(String paramString) {
    AnalyticsEventModel analyticsEventModel = new AnalyticsEventModel("-1", "execute_action", paramString);
    this.analyticsHelper.analyticsEventGeneric((Context)requireActivity(), analyticsEventModel);
  }
  
  private Dialog createDialog_Inner() {
    view = LayoutInflater.from(context).inflate(2131427473, null);
    this.stringsResourcesVO = new StringsResourcesVO();
    Button button2 = (Button)view.findViewById(2131296343);
    String str1 = Utilities.getStringFromConfigList(getContext(), this.stringsResourcesVO.global_configuracion_map_lbl_descargarmayus_1, 2131689840);
    button2.setText(str1);
    Button button1 = (Button)view.findViewById(2131296344);
    button1.setText(Utilities.getStringFromConfigList(getContext(), this.stringsResourcesVO.mapdownloading_popup_btn_despues_4, 2131690116));
    if (showButtonCancel) {
      button1.setVisibility(0);
      button1.setText(msgButtonNOK);
    } else {
      button1.setVisibility(8);
    } 
    if (showButtonOK) {
      button2.setVisibility(0);
      button2.setText(msgButtonOK);
    } else {
      button2.setVisibility(8);
    } 
    TextView textView1 = (TextView)view.findViewById(2131296917);
    textView1.setText("");
    textView1.setVisibility(8);
    TextView textView2 = (TextView)view.findViewById(2131296260);
    textView2.setText(title);
    textView2.setCompoundDrawablesWithIntrinsicBounds(imgDrawable, null, null, null);
    ((TextView)view.findViewById(2131296257)).setText(message);
    String str6 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_btn_aceptar_1, 2131689946);
    String str3 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_lbl_continuar, 2131689956);
    String str2 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_btn_ok_1, 2131689950);
    String str4 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_btn_si_1, 2131689952);
    String str10 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_btn_llamar_1, 2131689948);
    String str5 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.actions_popup_btn_activar_1, 2131689638);
    String str7 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.actions_popup_lbl_elcierreaut_4, 2131689641);
    String str11 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.deshabilitarPinCode, 2131689749);
    String str8 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    String str9 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.notificaciones_main_lbl_cancelar_1, 2131690212);
    if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.main_activity_download_in_progress, 2131690033))) {
      button2.setText(str6);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.UpdateMapNeeded, 2131689565))) {
      button2.setText(str3);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.main_activity_restart_after_download, 2131690061))) {
      button2.setText(str3);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.main_acticity_no_wifi, 2131690027))) {
      button2.setText(str8);
      button1.setText(str1);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_lbl_siguemeterminado_1, 2131689934))) {
      button2.setText(str2);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_lbl_configuracion_1, 2131689913))) {
      button2.setText(str10);
      button1.setText(str9);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_lbl_acciondescfallared_1, 2131689862))) {
      button2.setText(str6);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.MsgStatusNetwork, 2131689527))) {
      button2.setText(str6);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.newLatestTraceFollowMe, 2131690198))) {
      button2.setText(str4);
      button1.setText(str8);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.mapdownloading_popup_lbl_recuerdequeusted_2, 2131690124))) {
      button2.setText(str3);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.sucessoEmergency, 2131690400))) {
      button2.setText(str6);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.failEmergency, 2131689787))) {
      button2.setText(str6);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.actions_popup_lbl_usteddeseaaccionar_2, 2131689644))) {
      button2.setText(str4);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.actions_popup_lbl_usteddeseaabrir_2, 2131689643))) {
      button2.setText(str4);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.actions_popup_lbl_usteddeseacerrar_2, 2131689645))) {
      button2.setText(str4);
      textView1.setText(str7);
      textView1.setVisibility(0);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.alertBeforDisPinCode, 2131689660))) {
      button2.setText(str11);
    } else if (message.equals(Utilities.getStringFromConfigList(context, this.stringsResourcesVO.alertBeforEmergencyCallback, 2131689661))) {
      button2.setText(str5);
    } 
    button2.setOnClickListener(new View.OnClickListener() {
          final DialogMenu this$0;
          
          public void onClick(View param1View) {
            DialogMenu.this.analyticsEvent(DialogMenu.title);
            DialogMenu.listener.acceptButtonListener(DialogMenu.message);
            DialogMenu.this.dismiss();
          }
        });
    button1.setOnClickListener(new View.OnClickListener() {
          final DialogMenu this$0;
          
          public void onClick(View param1View) {
            DialogMenu.this.analyticsEvent(DialogMenu.title);
            DialogMenu.listener.cancelButtonListener(DialogMenu.message);
            DialogMenu.this.dismiss();
          }
        });
    Dialog dialog = new Dialog(context, 16973840);
    dialog.setTitle(null);
    dialog.setContentView(view);
    dialog.setCancelable(cancelable);
    Window window = dialog.getWindow();
    window.setFlags(32, 32);
    window.clearFlags(2);
    return dialog;
  }
  
  private void initAnalytics() {
    this.analyticsHelper = AnalyticsHelperFactory.getAnalyticsHelper(AnalyticsTypes.FIREBASE);
  }
  
  public static DialogMenu newInstance() {
    return new DialogMenu();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void onCancel(DialogInterface paramDialogInterface) {
    listener.onBackMenuListener(message);
  }
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    OrientationManager.lockOrientation((Activity)getActivity());
    this.dialog = createDialog_Inner();
    setRetainInstance(true);
    if (this.dialog.getWindow() != null)
      this.dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(context, 2131034153))); 
    initAnalytics();
    return this.dialog;
  }
  
  public void onDestroyView() {
    if (getDialog() != null && getRetainInstance())
      getDialog().setDismissMessage(null); 
    if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue() && !MainActivity.onHornLights.booleanValue() && !MainActivity.onCloseDoors.booleanValue() && !MainActivity.onOpenDoors.booleanValue() && !MainActivity.onDisarmPINCODE.booleanValue() && !MainActivity.onAlertParking.booleanValue() && !MainActivity.onAlertSpeed.booleanValue() && !MainActivity.onAlertValet.booleanValue() && !MainActivity.onNotification.booleanValue() && !MainActivity.onHorn.booleanValue())
      OrientationManager.unlockOrientation((Activity)getActivity()); 
    super.onDestroyView();
  }
  
  public void onDismiss(DialogInterface paramDialogInterface) {
    listener.onBackMenuListener(message);
  }
  
  public void setPreferenceDialogFragment(DialogMenuListener paramDialogMenuListener, Context paramContext, boolean paramBoolean1, int paramInt, String paramString1, String paramString2, boolean paramBoolean2) {
    listener = paramDialogMenuListener;
    context = paramContext;
    cancelable = paramBoolean1;
    message = paramString1;
    title = paramString2;
    showButtonOK = true;
    showButtonCancel = paramBoolean2;
    this.stringsResourcesVO = new StringsResourcesVO();
    String str1 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.actions_popup_btn_activar_1, 2131689638);
    String str2 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    msgButtonOK = str1;
    msgButtonNOK = str2;
  }
  
  public void setPreferenceDialogFragment(DialogMenuListener paramDialogMenuListener, Context paramContext, boolean paramBoolean1, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean2) {
    listener = paramDialogMenuListener;
    context = paramContext;
    cancelable = paramBoolean1;
    message = paramString1;
    title = paramString2;
    showButtonOK = true;
    showButtonCancel = paramBoolean2;
    this.stringsResourcesVO = new StringsResourcesVO();
    String str1 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.actions_popup_btn_activar_1, 2131689638);
    String str2 = Utilities.getStringFromConfigList(context, this.stringsResourcesVO.global_popup_btn_no_1, 2131689949);
    msgButtonOK = str1;
    msgButtonNOK = str2;
    try {
      imgDrawable = DrawableUtils.resizeDrawable(context, paramDrawable, 100);
    } catch (Exception exception) {
      imgDrawable = null;
    } 
  }
  
  public void setPreferenceDialogFragment(DialogMenuListener paramDialogMenuListener, Context paramContext, boolean paramBoolean1, Drawable paramDrawable, String paramString1, String paramString2, boolean paramBoolean2, String paramString3, boolean paramBoolean3, String paramString4) {
    listener = paramDialogMenuListener;
    context = paramContext;
    cancelable = paramBoolean1;
    message = paramString1;
    title = paramString2;
    showButtonOK = paramBoolean2;
    showButtonCancel = paramBoolean3;
    msgButtonOK = paramString3;
    msgButtonNOK = paramString4;
    try {
      imgDrawable = DrawableUtils.resizeDrawable(context, paramDrawable, 100);
    } catch (Exception exception) {
      imgDrawable = null;
    } 
  }
  
  public void show(FragmentManager paramFragmentManager, Object paramObject) {}
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {}
  
  public static interface DialogMenuListener {
    void acceptButtonListener(String param1String);
    
    void cancelButtonListener(String param1String);
    
    void onBackMenuListener(String param1String);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/DialogMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */